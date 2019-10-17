package org.fundacionjala.pivotal;


import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TasksTest {
    private RestAssured restAssured = new RestAssured();
    private Response response;


    public void setForTest() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139/stories/169196012/tasks/67913037");
    }

    @Test
    public void getTaskTypeFromStory() throws IOException, ParseException {
        setForTest();
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
       // Assert.assertEquals(this.response.jsonPath().getString("complete"), false);
        // Assert.assertEquals(this.response.jsonPath().getString("position"), 1);
    }

    public void setForPost() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .post("/projects/2406139/stories/169196012/tasks");
    }

    @Test
    public void postTaskTypeFromStory() throws IOException, ParseException {
        setForPost();
        Assert.assertEquals(this.response.statusCode(), 200);
    }


}