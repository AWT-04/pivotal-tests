package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class Test2 {
    private RestAssured restAssured = new RestAssured();
    private Response response;

    {
        try {
            response = given(restAssured.getRequestSpecification())
                        .when()
                        .get("/projects/2406139");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_Pivotal_Tracker_GetProjectsWithSpecifications() {
        Assert.assertEquals(this.response.jsonPath().getString("name"), "AWT04");
    }
}
