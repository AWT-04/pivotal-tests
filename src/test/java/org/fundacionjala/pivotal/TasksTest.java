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
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests tasks from pivotal tracker.
 *
 * @author Raul Laredo
 * @version 1.0
 */
public class TasksTest {
    private Response response;
    private static final int POSITION = 3;
    private static final int VALUEOK = 200;
    private static final int VALUEOK1 = 204;
    private static final String DESCRIPTION = "description";

    /*
    Tests values get from Tasks
     */
    @Test
    public void getTaskTypeFromStory()  {
        response = RequestManager.setGet("/projects/2406102/stories/169156513/tasks/67928912");
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
       Assert.assertEquals(this.response.jsonPath().getBoolean("complete"), false);
       Assert.assertEquals(this.response.jsonPath().getInt("position"), POSITION);
    }

    /*
    Tests post tasks
     */
    @Test
    public void postTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put(DESCRIPTION, "New Task from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    /*
   Tests put tasks
    */
    @Test
    public void putTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put(DESCRIPTION, "New name for task from Rest Assured");

        response = RequestManager.setPut("/projects/2406102/stories/169156513/tasks/67890506", profileContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    /*
   Tests delete tasks
    */
    @Test
    public void deleteTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put(DESCRIPTION, "Task created and deleted from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
        String taskId = this.response.jsonPath().getString("id");
        response = RequestManager.setDelete("/projects/2406102/stories/169156513/tasks/" + taskId);
        Assert.assertEquals(this.response.statusCode(), VALUEOK1);
    }
}
