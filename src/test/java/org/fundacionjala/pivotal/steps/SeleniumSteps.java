package org.fundacionjala.pivotal.steps;

import Base.BaseUtil;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.ProjectPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class SeleniumSteps {

    private BaseUtil base;
    final String title = LocalDateTime.now().toString();

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

    @When("I click create project button")
    public void iClickCreateProjectButton() {
        DashboardPage dashboardPage = new DashboardPage(base.driver);
        dashboardPage.btnCreateProject.click();
    }

    @And("I enter {string} the name of the project")
    public void iEnterTheNameOfTheProject(String nameProject) {
        DashboardPage dashboardPage = new DashboardPage(base.driver);
        dashboardPage.txtProjectName.sendKeys(title);
    }

    @And("I click the account options")
    public void iClickTheAccountOptions() {
        DashboardPage dashboardPage = new DashboardPage(base.driver);
        dashboardPage.btnSelection.click();
    }

    @And("I select the account")
    public void iSelectTheAccount() {
        DashboardPage dashboardPage = new DashboardPage(base.driver);
//        dashboardPage.optAccount.click();
    }

    @And("I click the accept button")
    public void iClickTheAcceptButton() {
        DashboardPage dashboardPage = new DashboardPage(base.driver);
        dashboardPage.btnAcceptCreateProject.click();
    }

    @Then("I should see the title {string} in the navigator")
    public void iShouldSeeTheTitleInTheNavigator(String titleProject) {
        (new WebDriverWait(base.driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".scrimVisible")));
        //Validate the title in the project page
        Assert.assertTrue(base.driver.getTitle().contains(title));
//        ProjectPage projectPage = new ProjectPage(base.driver);
//        projectPage.btnAllProjects.click();
//        Assert.assertTrue(projectPage.itemProject.isDisplayed());
    }

    @And("I set the project form:")
    public void iSetTheProjectForm(final Map<String, String> data) {
        DashboardPage dashboardPage = new DashboardPage(base.driver);
        dashboardPage.txtProjectName.sendKeys(title);
        dashboardPage.btnSelection.click();
        dashboardPage.btnAccount.click();
        //dashboardPage.SelectAccount(data.get("account")).click();
        dashboardPage.SelectPrivacity(Boolean.parseBoolean(data.get("privacy")));
    }

    @Given("I login as user:")
    public void iLoginAsUser(final Map<String, String> user) {
        base.driver.get("https://www.pivotaltracker.com/signin");
        LoginPage loginPage = new LoginPage(base.driver);
        loginPage.txtUserName.sendKeys(user.get("name"));
        loginPage.ClickLogin();
        loginPage.txtPassword.sendKeys(user.get("password"));
        loginPage.ClickLogin();
    }

    @And("I should not see int the title {string} in the navigator")
    public void iShouldNotSeeIntTheTitleInTheNavigator(String privacity) {
        Assert.assertFalse(base.driver.getTitle().contains(privacity));
    }

    @And("I should see the project in the list of projects")
    public void iShouldSeeTheProjectInTheListOfProjects() throws InterruptedException {
        ProjectPage projectPage = new ProjectPage(base.driver);
        projectPage.btnProjects.click();
        String XPath = String.format("//*[@class='raw_project_name public' and contains(text(),'%s')]", title);
//        (new WebDriverWait(base.driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tc_menu_header.tc_menu_header_projects")));
        Assert.assertEquals(base.driver.findElement(By.xpath(XPath)).getText(), title);
//        projectPage.ProjectInList(title).click();
    }
}
