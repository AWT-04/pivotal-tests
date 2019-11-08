package org.fundacionjala.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    private WebDriver webDriver;
    private static final int IMPLICIT_TIME = 15;

    public LoginPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login(final String username, final String password) {
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIME, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("#credentials_username"))
                .sendKeys(username);
        webDriver.findElement(By.cssSelector(".app_signin_action_button")).click();
        webDriver.findElement(By.cssSelector("#credentials_password")).sendKeys(password);
        webDriver.findElement(By.cssSelector(".app_signin_action_button")).click();
    }
}
