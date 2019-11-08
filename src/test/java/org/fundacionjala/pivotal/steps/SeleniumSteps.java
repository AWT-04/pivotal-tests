package org.fundacionjala.pivotal.steps;

import Base.BaseUtil;
import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SeleniumSteps {

    private BaseUtil base;

    public SeleniumSteps(BaseUtil base) {
        this.base = base;
    }

    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        base.driver.get("https://www.pivotaltracker.com/signin");
    }

    @And("I enter {string} as userName")
    public void iEnterAsUserName(String userName) {
        LoginPage loginPage = new LoginPage(base.driver);
        loginPage.txtUserName.sendKeys(userName);
    }

    @And("I click login button")
    public void iClickLoginButton() {
        LoginPage loginPage = new LoginPage(base.driver);
        loginPage.ClickLogin();
    }

    @Then("I should see the dashboard page")
    public void iShouldSeeTheDashboardPage() {

    }

    @And("I enter {string} as password")
    public void iEnterAsPassword(String password) {
        LoginPage loginPage = new LoginPage(base.driver);
        loginPage.txtPassword.sendKeys(password);
    }
}
