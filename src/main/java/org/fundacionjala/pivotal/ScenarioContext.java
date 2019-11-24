package org.fundacionjala.pivotal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScenarioContext {

    private HashMap<String, Object> context;
    private List<String> endpoints;

    public ScenarioContext() {
        context = new HashMap<>();
        endpoints = new ArrayList<>();
    }

    public void setContext(final String key, final Object anyData) {
        this.context.put(key, anyData);
    }

    public Object getContext(final String key) {
        return context.get(key);
    }

    public void addEndpoint(final String endpoint) {
        endpoints.add(endpoint);
    }

    public List<String> getEndpoints() {
        return endpoints;
    }
}
