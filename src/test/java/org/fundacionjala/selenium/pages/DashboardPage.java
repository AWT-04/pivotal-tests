package org.fundacionjala.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DashboardPage {
    private WebDriver webDriver;

    public DashboardPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void createProjectWizard(final String nameProject) {
        webDriver.findElement(By.cssSelector(".wizard__input")).sendKeys(nameProject);
        webDriver.findElement(By.xpath("//button[@aria-label='Create project']")).click();
        webDriver.close();
    }

    public void createProjectWithNewAccount(final String nameProject, final String newAccount) {
        webDriver.findElement(By.cssSelector("#create-project-button")).click();
        webDriver.findElement(By.cssSelector(".tc-form__input")).sendKeys(nameProject);
        webDriver.findElement(By.cssSelector(".tc-account-selector__header")).click();
        webDriver.findElement(By.cssSelector(".tc-account-selector__create-account-icon")).click();
        webDriver.findElement(By.cssSelector(".pvXpn__Button--positive")).click();
        webDriver.findElement(By.cssSelector(".tc-account-creator__name")).sendKeys(newAccount);
        webDriver.findElement(By.cssSelector(".pvXpn__Button--positive")).click();
        webDriver.close();
    }

    public void createProjectWithAlreadyExistAccount(final String nameProject, final String existAccount) {
        webDriver.findElement(By.cssSelector("#create-project-button")).click();
        webDriver.findElement(By.cssSelector(".tc-form__input")).sendKeys(nameProject);
        webDriver.findElement(By.cssSelector(".tc-account-selector__header")).click();
        webDriver.findElement(By.cssSelector(".tc-account-selector__list-header-name")).click();
        String existingAccount = "Fernando";
        webDriver.findElement(By.xpath(String.format(
                "//div[@class='tc-account-selector__option-account-name' and contains(text(), '%s')]",
                existingAccount))).click();
        webDriver.findElement(By.cssSelector(".pvXpn__Button--positive")).click();
        webDriver.close();
    }

    public String getHeaderTitle() {
        return webDriver.findElement(By.cssSelector(".projectPaneSection__header__heading--name"))
                .getText();
    }
}
