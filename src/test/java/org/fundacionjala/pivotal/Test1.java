package org.fundacionjala.pivotal;

import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class Test1 {
    @Test
    public void projectGet1() {
        String token = "f8293edefc0ac04e6faa07ac6b5ac52a";
        given().
                header("x-trackerToken",token)
                .contentType(ContentType.JSON)
                .pathParam("id", 2406139);
        when().
                get("https://www.pivotaltracker.com/services/v5/projects").

        then().
                statusCode(200);
    }
    @Test
    public void projectGet2() {
        String token = "f8293edefc0ac04e6faa07ac6b5ac52a";
        given().
                header("x-trackerToken",token);

        when().
                get("https://www.pivotaltracker.com/services/v5/projects").
        then().
                statusCode(200)
                .and()
                .body("id", response -> notNullValue());
    }
}
