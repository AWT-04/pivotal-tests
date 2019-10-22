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

import java.time.LocalTime;
import java.util.HashMap;

public class MyStepdefs {
    HashMap<String, Response> data = new HashMap<>();
    private Response response;
    private String EndPoint;
    private String ProjectId;
    private String StoryId;
    private String TaskId;

    public JSONObject getJson(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        return json;
    }

    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
        EndPoint = arg0;
    }

    @And("I fill the body with:")
    public void iFillTheBodyWith(String body) throws ParseException {
        response = RequestManager.setPost(EndPoint, getJson(body));
    }

    @And("I save response as {string}")
    public void iSaveResponseAs(String arg0) {
        data.put(arg0, response);
        System.out.println(data);
    }

    @And("I perform POST operation for other {string}")
    public void iPerformPOSTOperationForOther(String arg0) {
        ProjectId = response.jsonPath().getString("id");
        EndPoint = arg0.replace("{ProjectId}", ProjectId);
        System.out.println("arg0 = " + EndPoint);
    }

    @And("I fill the story body with:")
    public void iFillTheStoryBodyWith(String body) throws ParseException {
        response = RequestManager.setPost(EndPoint, getJson(body));
        System.out.println("response = " + response.prettyPrint());
    }

    @And("I save response too as {string}")
    public void iSaveResponseTooAs(String arg0) {
        data.put("story", response);
        System.out.println("data = " + data);
    }

    @When("I perform POST operation for a {string}")
    public void iPerformPOSTOperationForA(String arg0) {
        StoryId = response.jsonPath().getString("id");
        System.out.println("Project Id = " + ProjectId);
        EndPoint = arg0.replace("{SId}", StoryId);
        EndPoint = EndPoint.replace("{ProjectId}", ProjectId);
        System.out.println("arg0 = " + EndPoint);
    }

    @And("I fill the task body with:")
    public void iFillTheTaskBodyWith(String body) throws ParseException {
        response = RequestManager.setPost(EndPoint, getJson(body));
        System.out.println("response = " + response.prettyPrint());
    }

    @Then("I should see the status code as {string}")
    public void iShouldSeeTheStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
    }

    @And("Clean environment")
    public void cleanEnvironment() {
        RequestManager.setDelete("/projects/" + ProjectId);
    }

    @When("I perform PUT operation for a {string}")
    public void iPerformPUTOperationForA(String arg0) {
        TaskId = response.jsonPath().getString("id");
        EndPoint = arg0.replace("{SId}", StoryId);
        EndPoint = EndPoint.replace("{ProjectId}", ProjectId);
        EndPoint = EndPoint.replace("{TaskId}", TaskId);

    }

    @And("I fill the task body with new name:")
    public void iFillTheTaskBodyWithNewName(String body) throws ParseException {
        response = RequestManager.setPut(EndPoint, getJson(body));
        System.out.println("response PUT = " + response.prettyPrint());
    }

    @When("Delete project for {string}")
    public void deleteProjectFor(String arg0) {
        ProjectId = data.get("Project").jsonPath().getString("id");
        System.out.println("data = " + data);
        System.out.println(arg0 + ProjectId);
        response = RequestManager.setDelete(arg0 + ProjectId);
    }

    @Then("I should see the status code {string}")
    public void iShouldSeeTheStatusCode(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 204);
    }
}
