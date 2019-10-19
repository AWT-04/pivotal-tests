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
 * Tests stories from pivotal tracker.
 *
 * @author Raul Laredo
 * @version 1.0
 */
public class StoriesTests {
    private Response response;
    private static final int VALUEOK = 200;
    private static final int VALUEOK1 = 204;

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

    /*
    Tests values get from stories
     */
    @Test
    public void getKindTypeFromStory()  {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);

        response = RequestManager.setGet("/projects/"+projectId+"/stories/" + storyId);
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "story");
        Assert.assertEquals(this.response.jsonPath().getString("current_state"), "unscheduled");

        RequestManager.setDelete("/projects/" + projectId);
        RequestManager.setDelete("/projects/"+ projectId +"/stories/" + storyId);

    }

    /*
    Tests post tasks
     */
    @Test
    public void postStory() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "New Project "+ LocalTime.now());
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "New Story created " + LocalTime.now());
        response = RequestManager.setPost("/projects/" + projectId + "/stories", storyContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
        RequestManager.setDelete("/projects/" + projectId);
    }

    /*
   Tests put stories
    */
    @Test
    public void putTaskTypeFromStory() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now());
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        JSONObject newStoryContent = new JSONObject();
        newStoryContent.put("name", "New story name" + LocalTime.now());
        response = RequestManager.setPut("/projects/"+ projectId +"/stories/" + storyId, newStoryContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
        RequestManager.setDelete("/projects/" + projectId);
        RequestManager.setDelete("/projects/"+ projectId +"/stories/" + storyId);
    }

    /*
   Tests delete tasks
    */
    @Test
    public void deleteTaskTypeFromStory() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now());
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        response = RequestManager.setDelete("/projects/"+ projectId +"/stories/" + storyId);
        Assert.assertEquals(this.response.statusCode(), VALUEOK1);
        RequestManager.setDelete("/projects/" + projectId);
    }
}
