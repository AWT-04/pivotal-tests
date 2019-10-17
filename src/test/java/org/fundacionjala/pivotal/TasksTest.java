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
 * Tests tasks from pivotal tracker.
 *
 * @author Raul Laredo
 * @version 1.0
 */
public class TasksTest {
    private RestAssured restAssured = new RestAssured();
    private Response response;

    /*
    Sets for get Tasks
     */
    public void setForTest() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139/stories/169196012/tasks/67913037");
    }

    /*
    Tests values get from Tasks
     */
    @Test
    public void getTaskTypeFromStory() throws IOException, ParseException {
        setForTest();
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
       // Assert.assertEquals(this.response.jsonPath().getString("complete"), false);
        // Assert.assertEquals(this.response.jsonPath().getString("position"), 1);
    }

    /*
    Sets for post tasks
     */
    public void setForPost() throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .post("/projects/2406139/stories/169196012/tasks");
    }

    /*
    Tests post tasks
     */
    @Test
    public void postTaskTypeFromStory() throws IOException, ParseException {
        setForPost();
        Assert.assertEquals(this.response.statusCode(), 200);
    }


}