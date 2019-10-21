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

    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
        EndPoint = arg0;
        System.out.println("arg0 = " + arg0);
    }
    @And("I fill the body with:")
    public void iFillTheBodyWith(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        response = RequestManager.setPost(EndPoint, json);
        System.out.println("response = " + response.prettyPrint());
    }

    @And("I save response as {string}")
    public void iSaveResponseAs(String arg0) {
        data.put(arg0, response);
        System.out.println("data: " + data);
    }

    @And("I perform POST operation for other {string}")
    public void iPerformPOSTOperationForOther(String arg0) {
        ProjectId = response.jsonPath().getString("id");
        EndPoint = arg0;
        System.out.println("arg0 = " + EndPoint);
    }

    @And("I fill the story body with:")
    public void iFillTheStoryBodyWith(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        response = RequestManager.setPost(EndPoint, json);
        System.out.println("response = " + response.prettyPrint());
    }

    @And("I save response too as {string}")
    public void iSaveResponseTooAs(String arg0) {
        data.put(arg0, response);
    }

    @When("I perform POST operation for a {string}")
    public void iPerformPOSTOperationForA(String arg0) {
        JSONObject taskContent = new JSONObject();
        taskContent.put("description", LocalTime.now());
        response = RequestManager.setPost(arg0, taskContent);
    }

    @Then("I should see the status code as {string}")
    public void iShouldSeeTheStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
    }



}
