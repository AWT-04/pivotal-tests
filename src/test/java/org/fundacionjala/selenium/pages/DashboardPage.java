package org.fundacionjala.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DashboardPage {
    private WebDriver webDriver;
    private static final int IMPLICIT_TIME = 15;

    public DashboardPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void createProjectWizard(final String nameProject) {
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIME, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector(".wizard__input")).sendKeys(nameProject);
        webDriver.findElement(By.xpath("//button[@aria-label='Create project']")).click();
    }

    public void createProjectWithNewAccount(final String nameProject, final String newAccount) {
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIME, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("#create-project-button")).click();
        webDriver.findElement(By.cssSelector(".tc-form__input")).sendKeys(nameProject);
        webDriver.findElement(By.cssSelector(".tc-account-selector__header")).click();
        webDriver.findElement(By.cssSelector(".tc-account-selector__create-account-icon")).click();
        webDriver.findElement(By.cssSelector(".pvXpn__Button--positive")).click();
        webDriver.findElement(By.cssSelector(".tc-account-creator__name")).sendKeys(newAccount);
        webDriver.findElement(By.cssSelector(".pvXpn__Button--positive")).click();
    }

    public void createProjectWithAlreadyExistAccount(final String nameProject, final String existAccount) {
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIME, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("#create-project-button")).click();
        webDriver.findElement(By.cssSelector(".tc-form__input")).sendKeys(nameProject);
        webDriver.findElement(By.cssSelector(".tc-account-selector__header")).click();
        webDriver.findElement(By.cssSelector(".tc-account-selector__list-header-name")).click();
        webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/form/div/div/fieldset/"
                + "div/div[2]/div[1]/ul/li[1]/span[1]/div")).click();
        webDriver.findElement(By.cssSelector(".pvXpn__Button--positive")).click();
    }
}
