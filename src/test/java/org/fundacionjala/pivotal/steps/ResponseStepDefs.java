package org.fundacionjala.pivotal.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import org.fundacionjala.pivotal.ScenarioContext;

public class ResponseStepDefs {

    private ScenarioContext context;
    private Response response;

    public ResponseStepDefs(final ScenarioContext context) {
        this.context = context;
    }

    @Then("Validate status code {int} of response {string}")
    public void validateStatusCodeOfResponse(int statusCode, final String resp) {
        response = ((Response) context.getContext(resp));
        int status = response.statusCode();
        Assert.assertEquals(status, statusCode);
    }

    @Then("I should see the status code as {int}")
    public void iShouldSeeTheStatusCode(int statusCode) {
        response = ((Response) context.getContext(RequestStepDefs.KEY_LAST_RESPONSE));
        Assert.assertEquals(this.response.statusCode(), statusCode);

    }

    @And("I should see the kind as {string}")
    public void iShouldSeeTheKindAs(final String kind) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), kind);
    }

    @And("I should see the complete as {string}")
    public void iShouldSeeTheCompleteAs(final boolean completed) {
        Assert.assertEquals(this.response.jsonPath().getBoolean("complete"), completed);
    }

    @And("I should see {string} is not null")
    public void iShouldSeeIsNotNull(final String varName) {
        response = ((Response) context.getContext(RequestStepDefs.KEY_LAST_RESPONSE));
        Assert.assertNotNull(this.response.jsonPath().getString(varName));
    }

}
