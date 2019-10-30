package org.fundacionjala.pivotal;

import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public final class Authentication {
    private Authentication() {
    }

    /**
     * The method load a config.json file and return Request specification.
     *
     * @return returns a RequestSpecification object.
     * @throws IOException    throws input /output exception.
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

}
