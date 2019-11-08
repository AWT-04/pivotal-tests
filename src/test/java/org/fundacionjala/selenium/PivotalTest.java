package org.fundacionjala.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.fundacionjala.selenium.pages.DashboardPage;
import org.fundacionjala.selenium.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PivotalTest {
    private WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://www.pivotaltracker.com/signin/");
        LoginPage pageLogin = new LoginPage(webDriver);
        pageLogin.login("fernando.hinojosa@live.com", "0123456789");
    }

    @Test
    public void testLogin() {
        Assert.assertTrue(webDriver.findElement(By.cssSelector(".projectPaneSection__header__heading--name"))
                .getText().contains("My Projects"));
    }

    @Test
    public void testCreateProjectWithNewAccount() {
        DashboardPage dashboardPage = new DashboardPage(webDriver);
        dashboardPage.createProjectWithNewAccount("Testss", "01543");
        Assert.assertTrue(webDriver.findElement(By.cssSelector(".projectPaneSection__header__heading--name"))
                .getText().contains("My Projects"));
    }

    @Test
    public void createProjectWithAlreadyExistAccount() {
        DashboardPage dashboardPage = new DashboardPage(webDriver);
        dashboardPage.createProjectWithAlreadyExistAccount("Testss", "01543");
        Assert.assertTrue(webDriver.findElement(By.cssSelector(".projectPaneSection__header__heading--name"))
                .getText().contains("My Projects"));
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

}
