/**
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.core.api;

import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Rest assured initial framework.
 *
 * @author Andy Bazualdo
 * @version 1.0
 */
public final class RequestManager {
    private static final RequestSpecification
            REQUEST_SPECIFICATION = Authentication.getAuthenticationInstance().getRequestSpecification();

    private RequestManager() {
    }

    /**
     * Sets get method path.
     *
     * @param path string path to use url.
     * @return response object.
     */
    public static Response get(final String path) {
        Response response = given().spec(REQUEST_SPECIFICATION)
                .log().all()
                .when()
                .get(path);
        response.then().log().all();
        return response;
    }

    /**
     * Sets post method path.
     *
     * @param path string path to use url.
     * @param json json object in string format.
     * @return response object.
     */

    public static Response post(final String path, final String json) {
        Response response = given().spec(REQUEST_SPECIFICATION)
                .when()
                .contentType(ContentType.JSON)
                .body(json)
                .post(path);
        response.then().log().all();
        return response;
    }

    public static Response post(final String path, final Map<String, String> json) {
        Response response = given().spec(REQUEST_SPECIFICATION)
                .params(json)
                .when()
                .post(path);
        response.then().log().all();
        return response;
    }

    /**
     * Sets put method path.
     *
     * @param path string path to use url.
     * @param json json object in string format.
     * @return response object.
     */

    public static Response put(final String path, final String json) {
        Response response = given().spec(REQUEST_SPECIFICATION)
                .when()
                .contentType(ContentType.JSON)
                .body(json)
                .put(path);
        response.then().log().all();
        return response;
    }

    /**
     * Sets delete method path.
     *
     * @param path string path to use url.
     * @return response object.
     */
    public static Response delete(final String path) {
        Response response = given().spec(REQUEST_SPECIFICATION)
                .when()
                .delete(path);
        response.then().log().all();
        return response;
    }

    /**
     * Sets patch method path.
     *
     * @param path string path to use url.
     * @return response object.
     */
    public static Response patch(final String path) {
        Response response = given().spec(REQUEST_SPECIFICATION)
                .when()
                .patch(path);
        response.then().log().all();
        return response;
    }
}
