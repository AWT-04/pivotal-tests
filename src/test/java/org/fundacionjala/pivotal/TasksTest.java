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
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

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
        response = restAssured.setGet("/projects/2406102/stories/169156513/tasks/67928912");
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
       Assert.assertEquals(this.response.jsonPath().getBoolean("complete"), false);
       Assert.assertEquals(this.response.jsonPath().getInt("position"), 3);
    }

    /*
    Tests post tasks
     */
    @Test
    public void postTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put("description", "New Task from Rest Assured");
        response = restAssured.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
        Assert.assertEquals(this.response.statusCode(), 200);
    }

    /*
   Tests put tasks
    */
    @Test
    public void putTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put("description", "New name for task from Rest Assured");

        response = restAssured.setPut("/projects/2406102/stories/169156513/tasks/67890506", profileContent);
        Assert.assertEquals(this.response.statusCode(), 200);
    }

    /*
   Tests delete tasks
    */
    @Test
    public void deleteTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put("description", "New Task from Rest Assured");
        response = restAssured.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
        String taskId = this.response.jsonPath().getString("id");
        System.out.println("ID created:" + taskId);
        response = restAssured.setDelete("/projects/2406102/stories/169156513/tasks/" + taskId);
        Assert.assertEquals(this.response.statusCode(), 204);
    }
}