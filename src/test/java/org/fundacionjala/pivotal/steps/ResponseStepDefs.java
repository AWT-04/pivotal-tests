package org.fundacionjala.pivotal.steps;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.ScenarioContext;
import org.testng.Assert;

public class ResponseStepDefs {
    private ScenarioContext context;
    private Response response;

    public ResponseStepDefs(final ScenarioContext context) {
        this.context = context;
    }

    @Then("I should see the status code as {int}")
    public void iShouldSeeTheStatusCode(int statusCode) {
        response = context.getContext("LAST_RESPONSE");
        Assert.assertEquals(this.response.statusCode(), statusCode);
    }
}
