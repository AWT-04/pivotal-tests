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

public class StoriesStepdefs {
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

    @Given("I perform POST operation for {string} stories")
    public void iPerformPOSTOperationForStories(String arg0) {
        endPoint = arg0;
    }
}
