package org.fundacionjala.pivotal;

import io.restassured.response.Response;

import java.util.HashMap;

public class ScenarioContext {

    private HashMap<String, Response> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public void setContext(final String key, final Response response) {
        this.context.put(key, response);
    }

    public Response getContext(final String key) {
        return context.get(key);
    }
}
