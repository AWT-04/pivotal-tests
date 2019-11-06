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
 */
public final class RequestManager {

    private RequestManager() {
    }

    /**
     * Sets get method path.
     *
     * @param path string path to use url.
     * @return response object.
     */
    public static Response get(final RequestSpecification requestSpec, final String path) {
        Response response = given()
                .spec(requestSpec)
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

    public static Response post(final RequestSpecification requestSpec, final String path,
                                final String json) {
        Response response = given()
                .spec(requestSpec)
                .when()
                .contentType(ContentType.JSON)
                .body(json)
                .post(path);
        response.then().log().all();
        return response;
    }

    public static Response post(final RequestSpecification requestSpec, final String path,
                                final Map<String, String> json) {
        Response response = given()
                .spec(requestSpec)
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

    public static Response put(final RequestSpecification requestSpec, final String path, final String json) {
        Response response = given()
                .spec(requestSpec)
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
    public static Response delete(final RequestSpecification requestSpec, final String path) {
        Response response = given()
                .spec(requestSpec)
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
    public static Response patch(final RequestSpecification requestSpec, final String path) {
        Response response = given()
                .spec(requestSpec)
                .when()
                .patch(path);
        response.then().log().all();
        return response;
    }
}
