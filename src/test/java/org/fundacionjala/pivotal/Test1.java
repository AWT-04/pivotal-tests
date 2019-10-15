package org.fundacionjala.pivotal;

import io.cucumber.java.en.Given;
import io.restassured.http.Header;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Test1 {
    @Test
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {
        String token = "f8293edefc0ac04e6faa07ac6b5ac52a";
        given().
                header("x-trackerToken",token);
        when().
                get("https://www.pivotaltracker.com/services/v5/projects").
                then().
                statusCode(200);
    }
}
