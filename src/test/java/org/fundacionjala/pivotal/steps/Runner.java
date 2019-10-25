package org.fundacionjala.pivotal.steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.RequestManager;
import org.testng.annotations.BeforeTest;

import java.util.List;

@CucumberOptions(
        glue = {"org.fundacionjala.pivotal"},
        features = "src/test/resources/features/"
)
public class Runner extends AbstractTestNGCucumberTests {
    private Response response;

    @BeforeTest
    public void beforeAllScenaries() {
        System.out.println("beforeAllScenaries");
        response = RequestManager.setGet("/projects");
        List<Integer> allID = response.jsonPath().getList("id");
        for (Integer id : allID) {
            RequestManager.delete("/projects/" + id);
        }
    }
}
