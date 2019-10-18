/**
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.pivotal;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


import static io.restassured.RestAssured.given;

/**
 * Rest assured initial framework.
 *
 * @author Andy Bazualdo
 * @version 1.0
 */
public final class RequestManager {
    private RequestManager() { }

    /**
     * The method load a config.json file and return Request specification.
     * @return returns a RequestSpecification object.
     * @throws IOException throws input /output exception.
     * @throws ParseException throws parse exception.
     */
    public static RequestSpecification getRequestSpecification() {
        JSONParser parser = new JSONParser();
        RequestSpecification requestSpecification = null;
        Object obj = null;
        try (FileReader reader = new FileReader("./configJson/config.json")) {
            obj = parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new AWT04exception(1, e.getMessage());
        }

        JSONObject jsonObject = (JSONObject) obj;
        //Reading the String
        String token = (String) jsonObject.get("x-trackerToken");
        requestSpecification = new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri("https://www.pivotaltracker.com/services/v5")
                .addHeader("X-TrackerToken", token)
                .setContentType(ContentType.JSON)
                .build();

        return requestSpecification;
    }

    /**
     * Sets get method path.
     * @param path string path to use url.
     * @return response object.
     */
    public static Response setGet(final String path) {
        Response response = given(getRequestSpecification())
                .log().all()
                .when()
                .get(path);
        response.then().log().all();
        return response;
    }

    /**
     * Sets post method path.
     * @param path string path to use url.
     * @param json json object in string format.
     * @return response object.
     */

    public static Response setPost(final String path, final JSONObject json) {
        Response response = given(getRequestSpecification())
                .when()
                .contentType(ContentType.JSON)
                .body(json)
                .post(path);
        return response;
    }

    /**
     * Sets put method path.
     * @param path string path to use url.
     * @param json json object in string format.
     * @return response object.
     */

    public static Response setPut(final String path, final JSONObject json) {
        Response response = given(getRequestSpecification())
                .when()
                .body(json)
                .put(path);
        return response;
    }

    /**
     * Sets delete method path.
     * @param path string path to use url.
     * @return response object.
     */
    public static Response setDelete(final String path) {
        Response response = given(getRequestSpecification())
                .when()
                .delete(path);
        return response;
    }

    /**
     * Sets patch method path.
     * @param path string path to use url.
     * @return response object.
     */
    public static Response setPatch(final String path) {
        Response response = given(getRequestSpecification())
                .when()
                .patch(path);
        return response;
    }
}
