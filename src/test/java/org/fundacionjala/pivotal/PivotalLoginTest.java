package org.fundacionjala.pivotal;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author lazaro on 11/6/2019.
 * AWT-04_pivotal-selenium-tests.
 */
public class PivotalLoginTest {
    private final String username = "andybazualdo1";
    private final String password = "a5203032BB";
    private WebDriver browser;


    @BeforeTest
    public void startWebDriver() {
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
    }

    @Test
    public void login() {

        browser.get("https://www.pivotaltracker.com/signin/");
        browser.findElement(By.cssSelector("#credentials_username")).sendKeys(username);
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        browser.findElement(By.cssSelector("#credentials_password")).sendKeys(password);
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        String account = browser.findElement(By.xpath("//button[@class="
        + "\"zWDds__Button TtSTu__Button--header Dropdown__button\" and @aria-label=\"Profile Dropdown\"]")).getText();
        Assert.assertEquals(account.toUpperCase(), username.toUpperCase());
    }

    @Test
    public void createProjectWithNewAcount() {
        login();
//        browser.findElement(By.xpath("//button[@id=\"create-project-button\"]")).click();
        browser.findElement(By.cssSelector("#create-project-button")).click();
//        browser.findElement(By.xpath("//input[@name='project_name']")).sendKeys("Test1");
        browser.findElement(By.cssSelector("input[name='project_name']")).sendKeys("Test1");
//        browser.findElement(By.xpath("//div [@ class=\"tc-account-selector__header\"]")).click();
        browser.findElement(By.cssSelector(".tc-account-selector__header")).click();
        browser.findElement(By.cssSelector(".tc-account-selector__create-account-icon")).click();
        browser.findElement(By.cssSelector(".tc-account-creator__name")).sendKeys("test_account");
//        browser.findElement(By.xpath("//input [@name='project_type' and @value='public']")).click();
        String projectType = "public";
        browser.findElement(By.cssSelector(String.format("input[name='project_type'][value='%s']", projectType))).click();
//        browser.findElement(By.xpath("//button [@class='zWDds__Button pvXpn__Button--positive']")).click();
//        browser.findElement(By.cssSelector(".zWDds__Button.pvXpn__Button--positive")).click();
        browser.findElement(By.cssSelector(".pvXpn__Button--positive")).click();

        // explicit wait - to wait for the compose button to be click-able
        WebDriverWait wait = new WebDriverWait(browser,30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span [@class='raw_context_name public']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".raw_context_name.public")));
        // click on the compose button as soon as the "compose" button is visible

        String project = browser.findElement(By.xpath("//span [@class = 'raw_context_name public']")).getText();
        String projectPrivacy = browser.findElement(By.xpath("//span [@class='public_project_label']")).getText();

        //initial validation
        Assert.assertEquals(project.toUpperCase(), "Test1".toUpperCase());
        Assert.assertEquals(projectPrivacy.toUpperCase(), "(public)".toUpperCase());

        // Navigation
        browser.findElement(By.xpath("//span [@class='public_project_label']")).click();

        // second validations

        // Navigation

        // third validations

        // .....

    }

    @Test
    public void createProjectWithExistingAccount() {

        login();
        browser.findElement(By.xpath("//button[@id=\"create-project-button\"]")).click();
        browser.findElement(By.xpath("//input[@name='project_name']")).sendKeys("Test1");
        browser.findElement(By.xpath("//div [@ class=\"tc-account-selector__header\"]")).click();
        browser.findElement(By.cssSelector(".tc-account-selector__create-account-icon")).click();
        browser.findElement(By.cssSelector(".tc-account-creator__name")).sendKeys("test_account");
        browser.findElement(By.xpath("//input [@name='project_type' and @value='public']")).click();

    }

//    @AfterTest
//    public void closeWebDriver() {
//        browser.close();
//    }
}
