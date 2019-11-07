package org.fundacionjala.pivotal.steps;

import Base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HooksSelenium {

    private BaseUtil base;

    public HooksSelenium(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(){
        System.out.println("Oopening the browser: Google Chromer");
        WebDriverManager.chromedriver().setup();
        base.driver = new ChromeDriver();
    }

    @After
    public void TearDownTest(){
        System.out.println("Closing the navigator");
        base.driver.close();
    }
}
