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
import java.util.Map;


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
        RequestSpecification requestSpecification = null;
        final String fileName = "./configJson/config.json";
        JSONObject jsonObject = getJsonObject(fileName);

        //Reading the String
        String token = (String) jsonObject.get("x-trackerToken");
        requestSpecification = new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri("https://www.pivotaltracker.com/services/v5")
                .addHeader("X-TrackerToken", token)
                .build();

         requestSpecification
                .log().all();
        return requestSpecification;
    }

    public static JSONObject getJsonObject(final String fileName) {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new AWT04exception(1, e.getMessage());
        }
        return jsonObject;
    }

    /**
     * Sets get method path.
     * @param path string path to use url.
     * @return response object.
     */
    public static Response get(final String path) {
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

    public static Response post(final String path, final String json) {
        Response response = given(getRequestSpecification())
                .when()
                .contentType(ContentType.JSON)
                .body(json)
                .post(path);
        response.then().log().all();
        return response;
    }

    public static Response post(final String path, final Map<String, String> json) {
        Response response = given(getRequestSpecification())
                .params(json)
                .when()
                .post(path);
        response.then().log().all();
        return response;
    }

    /**
     * Sets put method path.
     * @param path string path to use url.
     * @param json json object in string format.
     * @return response object.
     */

    public static Response put(final String path, final String json) {
        Response response = given(getRequestSpecification())
                .when()
                .body(json)
                .put(path);
        response.then().log().all();
        return response;
    }

    /**
     * Sets delete method path.
     * @param path string path to use url.
     * @return response object.
     */
    public static Response delete(final String path) {
        Response response = given(getRequestSpecification())
                .when()
                .delete(path);
        response.then().log().all();
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
        response.then().log().all();
        return response;
    }
}
