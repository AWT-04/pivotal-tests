package org.fundacionjala.pivotal.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.RequestManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.util.HashMap;

public class MyStepdefs {
    private HashMap<String, Response> data = new HashMap<>();
    private Response response;
    private String endPoint;
    private String projectId;
    private String storyId;
    private static final int OK1 = 204;
    private static final String PROJECT_ID = "{ProjectId}";

    public JSONObject getJson(final String body) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(body);
    }

    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(final String arg0) {
        endPoint = arg0;
    }

    @And("I fill the body with:")
    public void iFillTheBodyWith(final String body) throws ParseException {
        response = RequestManager.setPost(endPoint, getJson(body));
    }

    @And("I save response as {string}")
    public void iSaveResponseAs(final String arg0) {
        data.put(arg0, response);
    }

    @And("I perform POST operation for other {string}")
    public void iPerformPOSTOperationForOther(final String arg0) {
        projectId = response.jsonPath().getString("id");
        endPoint = arg0.replace(PROJECT_ID, projectId);
    }

    @And("I fill the story body with:")
    public void iFillTheStoryBodyWith(final String body) throws ParseException {
        response = RequestManager.setPost(endPoint, getJson(body));
    }

    @And("I save response too as {string}")
    public void iSaveResponseTooAs(final String arg0) {
        data.put("Story", response);
    }

    @When("I perform POST operation for a {string}")
    public void iPerformPOSTOperationForA(final String arg0) {
        storyId = response.jsonPath().getString("id");
        endPoint = arg0.replace("{SId}", storyId);
        endPoint = endPoint.replace(PROJECT_ID, projectId);
    }

    @And("I fill the task body with:")
    public void iFillTheTaskBodyWith(final String body) throws ParseException {
        response = RequestManager.setPost(endPoint, getJson(body));
    }

    @Then("I should see the status code as {string}")
    public void iShouldSeeTheStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
    }

    @And("Clean environment")
    public void cleanEnvironment() {
        RequestManager.setDelete("/projects/" + projectId);
    }

    @When("I perform PUT operation for a {string}")
    public void iPerformPUTOperationForA(final String arg0) {
        String taskId = response.jsonPath().getString("id");
        endPoint = arg0.replace("{SId}", storyId);
        endPoint = endPoint.replace(PROJECT_ID, projectId);
        endPoint = endPoint.replace("{TaskId}", taskId);

    }

    @And("I fill the task body with new name:")
    public void iFillTheTaskBodyWithNewName(final String body) throws ParseException {
        response = RequestManager.setPut(endPoint, getJson(body));
    }

    @When("Delete project for {string}")
    public void deleteProjectFor(final String arg0) {
        projectId = data.get("Project").jsonPath().getString("id");
        response = RequestManager.setDelete(arg0 + projectId);
    }

    @Then("I should see the status code {string}")
    public void iShouldSeeTheStatusCode(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), OK1);
    }

    @Then("I should see the kind as {string}")
    public void iShouldSeeTheKindAs(String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "story");
    }

    @Then("I should see the status code as {int}")
    public void iShouldSeeTheStatusCodeAs(int arg0) {
        Assert.assertEquals(this.response.statusCode(), arg0);
    }

    @When("I perform PUT story operation for a {string}")
    public void iPerformPUTStoryOperationForA(String arg0) {
        endPoint = arg0.replace("{SId}", data.get("Story").jsonPath().getString("id"));
        endPoint = endPoint.replace(PROJECT_ID, data.get("Project").jsonPath().getString("id"));
    }

    public static class StoriesStepdefs {

    }
}
