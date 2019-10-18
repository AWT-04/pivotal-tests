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
 * Tests stories from pivotal tracker.
 *
 * @author Raul Laredo
 * @version 1.0
 */
public class StoriesTests {
    private Response response;
    private static final int VALUEOK = 200;
    private static final int VALUEOK1 = 204;

    /*
    Tests values get from stories
     */
    @Test
    public void getKindTypeFromStory()  {
        response = RequestManager.setGet("/projects/2406102/stories/169156513");
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "story");
        Assert.assertEquals(this.response.jsonPath().getString("current_state"), "unstarted");
    }

    /*
    Tests post tasks
     */
    @Test
    public void postStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New Story created from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories", profileContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    /*
   Tests put stories
    */
    @Test
    public void putTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New name updated from Rest Assured");
        response = RequestManager.setPut("/projects/2406102/stories/169156513", profileContent);
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    /*
   Tests delete tasks
    */
    @Test
    public void deleteTaskTypeFromStory() {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New Task from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories", profileContent);
        String taskId = this.response.jsonPath().getString("id");
        System.out.println("ID created:" + taskId);
        response = RequestManager.setDelete("/projects/2406102/stories/" + taskId);
        Assert.assertEquals(this.response.statusCode(), VALUEOK1);
    }
}
