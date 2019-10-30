package org.fundacionjala.pivotal.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.EndpointHelper;
import org.fundacionjala.pivotal.RequestManager;
import org.fundacionjala.pivotal.ScenarioContext;
import org.json.simple.JSONObject;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RequestStepDefs {

    private ScenarioContext context;
    private Response response;

    public RequestStepDefs(final ScenarioContext context) {
        this.context = context;
    }

    @Given("I send a POST request to {string} with body json:")
    public void iSendAPOSTRequestToEndpointWithBodyJson(final String endPoint, final String body) {
        response = RequestManager.post(EndpointHelper.buildEndpoint(endPoint, context), body);
    }

    @Given("I send a POST request to {string} with body:")
    public void iSendAPOSTRequestToEndpointWithBody(final String endPoint, final Map<String, String> body) {
        response = RequestManager.post(EndpointHelper.buildEndpoint(endPoint, context), body);
    }

    @Given("I send a POST request to {string} with json file {string}")
    public void iSendAPOSTRequestToEndpointWithBodyJsonFile(final String endPoint,
                                                            final String jsonPath) {
        JSONObject body = RequestManager.getJsonObject("src/test/resources/".concat(jsonPath));
        response = RequestManager.post(EndpointHelper.buildEndpoint(endPoint, context), body);
    }

    @Given("I send a PUT request to {string} with body json:")
    public void iSendAPUTRequestToEndpointWithBodyJson(final String endPoint, final String body) {
        response = RequestManager.put(EndpointHelper.buildEndpoint(endPoint, context), body);
    }

    @And("I save response as {string}")
    public void iSaveResponseAs(final String key) {
        context.setContext(key, response);
        context.setContext("LAST_RESPONSE", response);
    }


    @Then("I should see the {string} as {string}")
    public void iShouldSeeTheKindAs(final String attribute, final String value) {
        Assert.assertEquals(this.response.jsonPath().getString(attribute), value);
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(final String endPoint) {
        response = RequestManager.delete(EndpointHelper.buildEndpoint(endPoint, context));
    }

    @And("I should see the size of {string} in {string} as {int}")
    public void iShouldSeeTheSizeOfInAs(String field, String EndPoint, int size) {
        response = context.getContext(EndPoint);
        Assert.assertEquals(this.response.jsonPath().getList(field).size(), size);
    }

    @And("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endPoint) {
        response = RequestManager.setGet(EndpointHelper.buildEndpoint(endPoint, context));
    }

    @And("I should see the size of type {string} in {string} of {string} as {int}")
    public void iShouldSeeTheSizeOfTypeInOfAs(String kind, String field, String EndPoint, int size) {
        response = context.getContext(EndPoint);
        List<String> storiesKind = response.jsonPath().getList(field);
        System.out.println("storieskind = " + storiesKind );
        int count = 0;
        for (int i = 0; i < storiesKind.size(); i++) {
            System.out.println("storieKind = " + storiesKind.get(i));
            if(storiesKind.get(i).equals(kind)){
                count++;
            }
        }
        Assert.assertEquals(count, size);
    }
}
