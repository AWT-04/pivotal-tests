package org.fundacionjala.selenium.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    private WebDriver webDriver;
    private static final int IMPLICIT_TIME = 15;

    public LoginPage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIME, TimeUnit.SECONDS);
        webDriver.navigate().to("https://www.pivotaltracker.com/signin/");
    }

    public DashboardPage login(final String username, final String password) {
        webDriver.findElement(By.cssSelector("#credentials_username"))
                .sendKeys(username);
        webDriver.findElement(By.cssSelector(".app_signin_action_button")).click();
        webDriver.findElement(By.cssSelector("#credentials_password")).sendKeys(password);
        webDriver.findElement(By.cssSelector(".app_signin_action_button")).click();
        return new DashboardPage(webDriver);
    }
}
