package org.fundacionjala.core.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.fundacionjala.pivotal.Environment;

public final class Authentication {
    private static final String TOKEN_HEADER = "x-trackerToken";
    private static Authentication authenticationInstance;
    private RequestSpecification requestSpecification;


    private Authentication() {
        initApi();
    }

    public static Authentication getAuthenticationInstance() {
        if (authenticationInstance == null) {
            authenticationInstance = new Authentication();
        }
        return authenticationInstance;
    }

    private void initApi() {
        this.requestSpecification = new RequestSpecBuilder()
                .addHeader(TOKEN_HEADER, Environment.getInstance()
                .getValue("credentials.owner.apiToken"))
                .build();
        this.requestSpecification.baseUri(Environment.getInstance()
                .getValue("url.api"));
        this.requestSpecification.log().all();
    }
    public RequestSpecification getRequestSpecification() {
        return this.requestSpecification;
    }
}
