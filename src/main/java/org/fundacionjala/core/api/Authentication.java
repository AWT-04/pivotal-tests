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
 */
public final class Authentication {

    private static final String TOKEN_HEADER = "x-trackerToken";

    private Authentication() {
    }

    /**
     * Gets the request specification.
     *
     * @param account (username, password)
     * @return request specification.
     */
    public static RequestSpecification getRequestSpecification(final String account) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getValue("url.api"))
                .addHeader(TOKEN_HEADER, Environment.getInstance()
                        .getValue(String.format("credentials.%s.apiToken", account)))
                .build();
        return requestSpecification.log().all();
    }
}
