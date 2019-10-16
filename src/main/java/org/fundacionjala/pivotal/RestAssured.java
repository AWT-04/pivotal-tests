package org.fundacionjala.pivotal;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class RestAssured {
    public RequestSpecification getRequestSpecification() {
        return  new RequestSpecBuilder()
                .setBaseUri("https://www.pivotaltracker.com/services/v5")
                .addHeader("X-TrackerToken", "bfb2dcd6dd5650981b0147da1f9301d1")
                .setContentType(ContentType.JSON)
                .build();
    }
}

   /*JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("config.json"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject)obj;
        //Reading the String
        String token = (String) jsonObject.get("token");*/