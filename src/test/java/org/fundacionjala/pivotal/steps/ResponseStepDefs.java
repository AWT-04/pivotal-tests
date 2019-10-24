package org.fundacionjala.pivotal.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.RequestManager;
import org.fundacionjala.pivotal.ScenarioContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.util.HashMap;

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
}
