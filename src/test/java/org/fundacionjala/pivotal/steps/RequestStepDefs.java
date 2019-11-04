package org.fundacionjala.pivotal.steps;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.utils.EndpointHelper;
import org.fundacionjala.core.utils.VariableNameHandler;
import org.fundacionjala.pivotal.JSONHelper;
import org.fundacionjala.pivotal.ScenarioContext;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class RequestStepDefs {

    private static final String KEY_LAST_RESPONSE = "LAST_RESPONSE";
    private ScenarioContext context;
    private Response response;

    public RequestStepDefs(final ScenarioContext context) {
        this.context = context;
    }

    @Given("I send a POST request to {string} with body json:")
    public void iSendAPOSTRequestToEndpointWithBodyJson(final String endPoint, final String body) {
        response = RequestManager.post(EndpointHelper.buildEndpoint(endPoint, context),
         VariableNameHandler.replaceRandom(body, context));
        context.setContext(KEY_LAST_RESPONSE, response);
    }

    @Given("I send a POST request to {string} with body:")
    public void iSendAPOSTRequestToEndpointWithBody(final String endPoint, final Map<String, String> body) {
        response = RequestManager.post(EndpointHelper.buildEndpoint(endPoint, context), body);
        context.setContext(KEY_LAST_RESPONSE, response);
    }

    @Given("I send a POST request to {string} with body list:")
    public void iSendAPOSTRequestToEndpointWithBodyList(final String endPoint,
                                                        final List<Map<String, String>> bodyList) {
        for (Map<String, String> body: bodyList) {
            response = RequestManager.post(EndpointHelper.buildEndpoint(endPoint, context), body);
        }
    }

    @Given("I send a POST request to {string} with json file {string}")
    public void iSendAPOSTRequestToEndpointWithBodyJsonFile(final String endPoint,
                                                            final String jsonPath) {
        JSONObject body = JSONHelper.getJsonObject("src/test/resources/".concat(jsonPath));
        response = RequestManager.post(EndpointHelper.buildEndpoint(endPoint, context), body);
        context.setContext(KEY_LAST_RESPONSE, response);
    }

    @Given("I send a PUT request to {string} with body json:")
    public void iSendAPUTRequestToEndpointWithBodyJson(final String endPoint, final String body) {
        response = RequestManager.put(EndpointHelper.buildEndpoint(endPoint, context),
                VariableNameHandler.replaceRandom(body, context));
        context.setContext(KEY_LAST_RESPONSE, response);
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(final String endPoint) {
        response = RequestManager.delete(EndpointHelper.buildEndpoint(endPoint, context));
        context.setContext(KEY_LAST_RESPONSE, response);
    }

    @And("I send a GET request to {string}")
    public void iSendAGETRequestTo(final String endPoint) {
        response = RequestManager.get(EndpointHelper.buildEndpoint(endPoint, context));
        context.setContext(KEY_LAST_RESPONSE, response);

    }

    @And("I save response as {string}")
    public void giiSaveResponseAs(final String key) {
        context.setContext(key, response);
    }

    @Then("I should see the {string} as {string}")
    public void iShouldSeeTheKindAs(final String attribute, final String value) {
        String fistElement;
        if (this.response.jsonPath().getString(attribute).contains(",")) {
            fistElement = "[0].";
            Assert.assertEquals(this.response.jsonPath().getString(fistElement + attribute), value);
        } else {
            Assert.assertEquals(this.response.jsonPath().getString(attribute), value);
        }

    }

    @And("I should see the size of {string} in {string} as {int}")
    public void iShouldSeeTheSizeOfInAs(final String field, final String endPoint, final int size) {
        response = context.getContext(endPoint);
        assertEquals(this.response.jsonPath().getList(field).size(), size);
    }

    @And("I should see the size of type {string} in {string} of {string} as {int}")
    public void iShouldSeeTheSizeOfTypeInOfAs(final String kind, final String field,
                                              final String endPoint, final int size) {
        response = context.getContext(endPoint);
        assertEquals((int) response.jsonPath().getList(field).stream()
                .filter(x -> x.equals(kind))
                .count(), size);
    }

    @Then("I should see the size of type {string} in {string} of {string} as <size>")
    public void iShouldSeeTheSizeOfTypeInOfAsSize(final String kind, final String field,
                                                  final String endPoint, final int size) {
        response = context.getContext(endPoint);
        assertEquals((int) response.jsonPath().getList(field).stream()
                .filter(x -> x.equals(kind))
                .count(), size);
    }

    @When("I send delete all to")
    public void iSendDeleteAllTo() {
        response = RequestManager.get("/projects");
        response.jsonPath().getString("id");
    }

    @And("I send a GET request to {string}")
    public void iSendAGETRequestToContext(final String endPoint) {
        response = RequestManager.get(EndpointHelper.buildEndpoint(endPoint, context));
    }
}
