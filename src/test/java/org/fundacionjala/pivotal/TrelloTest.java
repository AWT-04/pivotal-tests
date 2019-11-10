package org.fundacionjala.pivotal;

import org.fundacionjala.core.ui.pages.BoardPage;
import org.fundacionjala.core.ui.pages.DashboardPage;
import org.fundacionjala.core.ui.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrelloTest {
    private DashboardPage dashboardPage;
    private BoardPage boardPage;

    @BeforeMethod
    public void setUpEnvironment() {
        dashboardPage = new LoginPage().login("fernando.hinojosa@live.com", "0123456");
    }

    @Test
    public void testLogin() {

    }

    @Test
    public void testCreateBoard() {
        boardPage = dashboardPage.createBoard("Test");
    }

    @Test
    public void testDeleteBoard() {
      dashboardPage.deleteBoard("Test");    }
}
