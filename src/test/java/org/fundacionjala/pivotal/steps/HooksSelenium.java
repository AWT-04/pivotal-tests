package org.fundacionjala.pivotal.steps;

import Base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

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
        base.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void TearDownTest(){
        System.out.println("Closing the navigator");
        base.driver.close();
    }
}
