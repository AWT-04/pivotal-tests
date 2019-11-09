package org.fundacionjala.selenium;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fundacionjala.selenium.pages.DashboardPage;
import org.fundacionjala.selenium.pages.LoginPage;


public class PivotalTest {

    private DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp() {
        LoginPage pageLogin = new LoginPage();
        dashboardPage = pageLogin.login("fernando.hinojosa@live.com", "0123456789");
    }

    @Test
    public void testCreateProjectWithNewAccount() {
        dashboardPage.createProjectWithNewAccount("Testsss", "015434");
    }
}
