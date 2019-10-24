package org.fundacionjala.pivotal.steps;

import io.restassured.response.Response;
import org.fundacionjala.pivotal.ScenarioContext;

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
}
