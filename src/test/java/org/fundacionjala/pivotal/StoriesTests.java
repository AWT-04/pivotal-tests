package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StoriesTests {
    private RestAssured restAssured = new RestAssured();
    private Response response;


    public void setForTest() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139/stories/169196012");
    }

    @Test
    public void getStoryTypeFromStory() throws IOException, ParseException {
        setForTest();
        Assert.assertEquals(this.response.jsonPath().getString("story_type"), "feature");
        Assert.assertEquals(this.response.jsonPath().getString("name"), "Story created by Raul for testing \uD83D\uDC79");
        Assert.assertEquals(this.response.jsonPath().getString("requested_by_id"), 3294402);
    }

    public void setForPost() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .post("/projects/2406139/stories");
    }

    @Test
    public void postStoryTypeFromStory() throws IOException, ParseException {
        setForPost();
        Assert.assertEquals(this.response.statusCode(), 200);
    }
}
