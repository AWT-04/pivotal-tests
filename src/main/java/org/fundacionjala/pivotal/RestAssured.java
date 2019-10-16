/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.pivotal;

import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Rest assured initial framework
 *
 * @author Andy Bazualdo
 * @version 1.0
 */
public class RestAssured {
    /**
     * The method load a config.json file and return Request specification
     * @return returns a RequestSpecification object
     * @throws IOException throws input /output exception
     */
    public RequestSpecification getRequestSpecification() throws IOException {
        JSONParser parser = new JSONParser();
        RequestSpecification requestSpecification = null;
        try {
            Object obj = null;
            obj = parser.parse(new FileReader("./configJson/config.json"));

        JSONObject jsonObject = (JSONObject)obj;
        //Reading the String
        String token = (String) jsonObject.get("x-trackerToken");
        requestSpecification =  new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri("https://www.pivotaltracker.com/services/v5")
                .addHeader("X-TrackerToken", token)
                .setContentType(io.restassured.http.ContentType.JSON)
                .build();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }
}
