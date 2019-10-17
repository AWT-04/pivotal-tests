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

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

/**
 * Tests Story from pivotal tracker.
 *
 * @author Raul Laredo
 * @version 1.0
 */
public class StoriesTests {
    private ManageController restAssured = new ManageController();
    private Response response;

    /*
    Sets for get Stories
     */
    public void setForTest() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139/stories/169196012");
    }

    /*
    Tests values get from Story
     */
    @Test
    public void getStoryTypeFromStory() throws IOException, ParseException {
        setForTest();
        Assert.assertEquals(this.response.jsonPath().getString("story_type"), "feature");
        Assert.assertEquals(this.response.jsonPath().getString("name"), "Story created by Raul for testing \uD83D\uDC79");
        Assert.assertEquals(this.response.jsonPath().getInt("requested_by_id"), 3294402);
    }

    /*
    Sets for post stories
     */
    public void setForPost() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .post("/projects/2406139/stories");
    }

    /*
    Tests post stories
     */
    @Test
    public void postStoryTypeFromStory() throws IOException, ParseException {
        setForPost();
        Assert.assertEquals(this.response.statusCode(), 200);
    }
}
