/**
 * Copyright (c) 2019 Jalasoft.
 * <p>
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.core.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.fundacionjala.pivotal.Environment;

/**
 * This classes permit the authentication, using a singleton pattern.
 *
 * @author Fernando Hinojosa on 11/02/2019.
 * @version v1.0
 */
public final class Authentication {
    private static final String TOKEN_HEADER = "x-trackerToken";
    private static Authentication authenticationInstance;
    private RequestSpecification requestSpecification;

    /**
     * This is the constructor of the class.
     */
    private Authentication() {
        initApi();
    }

    /**
     * Verify the exists a instances of authentication classes.
     *
     * @return authentication.
     */
    public static Authentication getAuthenticationInstance() {
        if (authenticationInstance == null) {
            authenticationInstance = new Authentication();
        }
        return authenticationInstance;
    }

    /**
     * This method init the authentication class.
     */
    private void initApi() {
        this.requestSpecification = new RequestSpecBuilder()
                .addHeader(TOKEN_HEADER, Environment.getInstance()
                        .getValue("credentials.owner.apiToken"))
                .build();
        this.requestSpecification.baseUri(Environment.getInstance()
                .getValue("url.api"));
        this.requestSpecification.log().all();
    }

    /**
     * Gets the request specification.
     *
     * @return request specification.
     */
    public RequestSpecification getRequestSpecification() {
        return this.requestSpecification;
    }
}
