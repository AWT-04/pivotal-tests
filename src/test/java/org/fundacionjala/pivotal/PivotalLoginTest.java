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


/**
 * @author lazaro on 11/6/2019.
 * AWT-04_pivotal-selenium-tests.
 */
public class PivotalLoginTest {
    private final String username = "andybazualdo1";
    private final String password = "a5203032BB";
    private WebDriver browser;
    private final int timeOutInSeconds = 30;


//    @BeforeTest
//    public void startWebDriver() {
//        WebDriverManager.chromedriver().setup();
//        browser = new ChromeDriver();
//    }

//    @Test
//    public void login() {
//
//        browser.get("https://www.pivotaltracker.com/signin/");
//        browser.findElement(By.cssSelector("#credentials_username")).sendKeys(username);
//        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
//        browser.findElement(By.cssSelector("#credentials_password")).sendKeys(password);
//        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
//        String account = browser.findElement(By.xpath("//button[@class="
//        + "\"zWDds__Button TtSTu__Button--header Dropdown__button\" and @aria-label=\"Profile Dropdown\"]")).getText();
//        Assert.assertEquals(account.toUpperCase(), username.toUpperCase());
//    }

    @Test
    public void createProjectWithNewAcount() {
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
        browser.get("https://www.pivotaltracker.com/signin/");
        browser.findElement(By.cssSelector("#credentials_username")).sendKeys(username);
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        browser.findElement(By.cssSelector("#credentials_password")).sendKeys(password);
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        String account = browser.findElement(By.xpath("//button[@class="
                + "\"zWDds__Button TtSTu__Button--header Dropdown__button\" and @aria-label=\"Profile Dropdown\"]"))
                .getText();
        Assert.assertEquals(account.toUpperCase(), username.toUpperCase());


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
        browser.findElement(By.cssSelector(String.format("input[name='project_type'][value='%s']", projectType)))
                .click();
//        browser.findElement(By.xpath("//button [@class='zWDds__Button pvXpn__Button--positive']")).click();
//        browser.findElement(By.cssSelector(".zWDds__Button.pvXpn__Button--positive")).click();
        browser.findElement(By.cssSelector(".pvXpn__Button--positive")).click();

        // explicit wait - to wait for the compose button to be click-able
        WebDriverWait wait = new WebDriverWait(browser, timeOutInSeconds);
//    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span [@class='raw_context_name public']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".raw_context_name.public")));


        String project = browser.findElement(By.cssSelector(".raw_context_name.public")).getText();
        String projectPrivacy = browser.findElement(By.cssSelector(".public_project_label")).getText();

        //initial validation
        Assert.assertEquals(project.toUpperCase(), "Test1".toUpperCase());
        Assert.assertEquals(projectPrivacy.toUpperCase(), "(public)".toUpperCase());

        // Navigation
        browser.findElement(By.cssSelector(".public_project_label")).click();
        String currentURL = browser.getCurrentUrl();
        project = browser.findElement(By.cssSelector(String.format("a[class='tc_projects_menu_item_link']"
                + "[href='%s'] span[class='raw_project_name public']", currentURL))).getText();

        // second validations
        Assert.assertEquals(project.toUpperCase(), "Test1".toUpperCase());
        projectPrivacy = browser.findElement(By.cssSelector(".public_project_label")).getText();

        browser.findElement(By.cssSelector("a[class='tc_projects_menu_footer']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//a[@class='projectTileHeader__projectName projectTileHeader__projectName--active' "
                + "and text()='Test1']")));
        browser.findElement(By.xpath("//a[@class='projectTileHeader__projectName "
                + "projectTileHeader__projectName--active' and text()='" + project + "']")).getText();

        //third validation
        Assert.assertEquals(project.toUpperCase(), "Test1".toUpperCase());

        //navigation
        browser.findElement(By.cssSelector("button[class='zWDds__Button TtSTu__Button--header Dropdown__button']"
                + "[aria-label='Profile Dropdown']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='Dropdown__option selected "
                + "Dropdown__option--link']")));
        browser.findElement(By.xpath("//a[@class='Dropdown__option selected Dropdown__option--link']")).click();
        browser.findElement(By.cssSelector("div[class='section'] a[href='/accounts']")).click();
        String cadene = browser.findElement(By.xpath("//a[@class='project_name' and text()='" + project
                + " (Public)']")).getText();
        Assert.assertEquals(cadene, project.toUpperCase() + " (Public)".toUpperCase());

        browser.close();

    }

    @Test
    public void createProjectWithExistingAccount() {
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
        browser.get("https://www.pivotaltracker.com/signin/");
        browser.findElement(By.cssSelector("#credentials_username")).sendKeys(username);
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        browser.findElement(By.cssSelector("#credentials_password")).sendKeys(password);
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        String account = browser.findElement(By.xpath("//button[@class="
                + "\"zWDds__Button TtSTu__Button--header Dropdown__button\" and @aria-label=\"Profile Dropdown\"]"))
                .getText();
        Assert.assertEquals(account.toUpperCase(), username.toUpperCase());


        WebDriverWait wait = new WebDriverWait(browser, timeOutInSeconds);
        browser.findElement(By.cssSelector("#create-project-button")).click();
        browser.findElement(By.cssSelector("input[name='project_name']")).sendKeys("Test2");
        browser.findElement(By.cssSelector(".tc-account-selector__header")).click();
        // explicit wait - to wait for the element to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='tc-account-selector__option-account-name']")));
        browser.findElement(By.xpath("//div[@class='tc-account-selector__option-account-name' and text()='Default']"))
                .click();
  //browser.findElement(By.cssSelector(".'tc-account-selector__option-account-name' and :contains('Default')")).click();
        String projectType = "public";
        browser.findElement(By.cssSelector(String.format("input[name='project_type'][value='%s']", projectType)))
                .click();
        browser.findElement(By.cssSelector(".pvXpn__Button--positive")).click();

        // explicit wait - to wait for the element to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".raw_context_name.public")));


        String project = browser.findElement(By.cssSelector(".raw_context_name.public")).getText();
        String projectPrivacy = browser.findElement(By.cssSelector(".public_project_label")).getText();

        //initial validation
        Assert.assertEquals(project.toUpperCase(), "Test2".toUpperCase());
        Assert.assertEquals(projectPrivacy.toUpperCase(), "(public)".toUpperCase());

        // Navigation
        browser.findElement(By.cssSelector(".public_project_label")).click();
        String currentURL = browser.getCurrentUrl();
        project = browser.findElement(By.cssSelector(String.format("a[class='tc_projects_menu_item_link']"
                + "[href='%s'] span[class='raw_project_name public']", currentURL))).getText();


        // second validations
        Assert.assertEquals(project.toUpperCase(), "Test2".toUpperCase());
        projectPrivacy = browser.findElement(By.cssSelector(".public_project_label")).getText();

        browser.findElement(By.cssSelector("a[class='tc_projects_menu_footer']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='projectTileHeader__projectName "
               + "projectTileHeader__projectName--active' and text()='Test2']")));
        browser.findElement(By.xpath("//a[@class='projectTileHeader__projectName project"
                + "TileHeader__projectName--active' and text()='" + project + "']")).getText();

        //third validation
        Assert.assertEquals(project.toUpperCase(), "Test2".toUpperCase());

        //navigation
        browser.findElement(By.cssSelector("button[class='zWDds__Button TtSTu__Button--header Dropdown__button']"
                + "[aria-label='Profile Dropdown']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@class='Dropdown__option selected Dropdown__option--link']")));
        browser.findElement(By.xpath("//a[@class='Dropdown__option selected Dropdown__option--link']")).click();
        browser.findElement(By.cssSelector("div[class='section'] a[href='/accounts']")).click();
        String cadene = browser.findElement(By.xpath("//a[@class='project_name' and text()='" + project
                + " (Public)']")).getText();
        Assert.assertEquals(cadene, project.toUpperCase() + " (Public)".toUpperCase());

        browser.close();
    }

//    @AfterTest
//    public void closeWebDriver() {
//        browser.close();
//    }
}
