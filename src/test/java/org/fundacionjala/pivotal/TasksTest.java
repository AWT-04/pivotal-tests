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

import java.time.LocalTime;

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

    public String createProject(JSONObject body){
        response = RequestManager.setPost("/projects", body);
        String projectId = this.response.jsonPath().getString("id");
        return projectId;
    }

    public String createStory(String projectID, JSONObject bodyStory){
        response = RequestManager.setPost("/projects/"+ projectID+"/stories", bodyStory);
        String storyId = this.response.jsonPath().getString("id");
        return storyId;
    }

    public String createTask(String projectID, String storyID, JSONObject bodyTask){
        response = RequestManager.setPost("/projects/"+ projectID+"/stories/"+storyID+"/tasks", bodyTask);
        String taskId = this.response.jsonPath().getString("id");
        return taskId;
    }

    /*
    Tests values get from Tasks
     */
    @Test
    public void getTaskTypeFromStory()  {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        JSONObject taskContent = new JSONObject();
        taskContent.put("description", "Task description " + LocalTime.now());
        String taskId = createTask(projectId, storyId, taskContent);

        response = RequestManager.setGet("/projects/"+projectId+"/stories/" + storyId+"/tasks/" + taskId);
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
        Assert.assertEquals(this.response.jsonPath().getBoolean("complete"), false);

        RequestManager.setDelete("/projects/" + projectId);
    }

    /*
    Tests post tasks
     */
    @Test
    public void postTaskTypeFromStory() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        JSONObject profileContent = new JSONObject();
        profileContent.put(DESCRIPTION, "New Task from Rest Assured"+ LocalTime.now());
        response = RequestManager.setPost("/projects/"+projectId+"/stories/" + storyId+"/tasks" , profileContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
        RequestManager.setDelete("/projects/" + projectId);
    }

    /*
   Tests put tasks
    */
    @Test
    public void putTaskTypeFromStory() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        JSONObject taskContent = new JSONObject();
        taskContent.put("description", "Task description " + LocalTime.now());
        String taskId = createTask(projectId, storyId, taskContent);
        JSONObject newStoryContent = new JSONObject();
        newStoryContent.put("description", "New task description" + LocalTime.now());
        response = RequestManager.setPut("/projects/"+ projectId +"/stories/" + storyId+"/tasks/" + taskId, newStoryContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
        RequestManager.setDelete("/projects/" + projectId);
    }

    /*
   Tests delete tasks
    */
    @Test
    public void deleteTaskTypeFromStory() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        JSONObject taskContent = new JSONObject();
        taskContent.put("description", "Task description " + LocalTime.now());
        String taskId = createTask(projectId, storyId, taskContent);
        JSONObject newStoryContent = new JSONObject();
        newStoryContent.put("description", "New task description" + LocalTime.now());
        response = RequestManager.setDelete("/projects/"+ projectId +"/stories/" + storyId+"/tasks/" + taskId);
        Assert.assertEquals(this.response.statusCode(), VALUEOK1);
        RequestManager.setDelete("/projects/" + projectId);
    }
}
