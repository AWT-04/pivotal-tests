package org.fundacionjala.pivotal.steps;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.ScenarioContext;
import org.testng.Assert;

public class ResponseStepDefs {

    private ScenarioContext context;
    private Response response;
    private String endPoint;
    private String projectId;
    private String storyId;
    private static final int OK1 = 204;
    private static final String PROJECT_ID = "{ProjectId}";

    public ResponseStepDefs(final ScenarioContext context) {

        this.context = context;
    }

    @Then("Validate status code {int} of response {string}")
    public void validateStatusCodeOfResponse(int statusCode, String resp) {
        response = context.getContext(resp);
        int status = response.statusCode();
        Assert.assertEquals(status,statusCode);
    }

    @Then("I should see the status code as {int}")
    public void iShouldSeeTheStatusCode(int statusCode) {
        response = context.getContext("LAST_RESPONSE");
        Assert.assertEquals(this.response.statusCode(), statusCode);
    }
}
