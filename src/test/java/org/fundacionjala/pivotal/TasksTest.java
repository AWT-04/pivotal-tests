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
    private ManageController restAssured = new ManageController();
    private Response response;

    /*
    Tests values get from Tasks
     */
    @Test
    public void getTaskTypeFromStory()  {
        response = restAssured.setGet("/projects/2406139/stories/169196012/tasks/67913037");
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
       Assert.assertEquals(this.response.jsonPath().getBoolean("complete"), false);
       Assert.assertEquals(this.response.jsonPath().getInt("position"), 1);
    }

    /*
    Tests post tasks
     */
    @Test
    public void postTaskTypeFromStory() {
        response = restAssured.setPost("/projects/2406139/stories/169196012/tasks");
                //response.body("{'name':'New task'}");
        Assert.assertEquals(this.response.statusCode(), 200);
    }


}