package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;


public class Test2 {
    private RestAssured restAssured = new RestAssured();
    private Response response;

    public void setForTest() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139");
    }

    @Test
    public void getProjectsWithSpecifications() throws IOException, ParseException {
        setForTest();
        Assert.assertEquals(this.response.jsonPath().getString("name"), "AWT04");
    }
}
