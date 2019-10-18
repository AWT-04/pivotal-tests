package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommentsTest {
    private Response response;
    private static final String PROJECTNAME = "LisaI";
    private static final String ENDPOINT = "/projects/";
    private static final String COMMENT = "Firs comment";
    private String projectID;
    private String storyID;

    @BeforeClass
    public void preConditions() {
        //Create project
        String endPointProject = ENDPOINT;
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", PROJECTNAME);
        response = RequestManager.setPost(endPointProject, profileContent);
        projectID = response.jsonPath().getString("id");

        //Create Stories
        String endPointStories = ENDPOINT + projectID + "/stories";
        JSONObject profileContentStory = new JSONObject();
        profileContentStory.put("name", "Hello, first story");
        Response responseStory = RequestManager.setPost(endPointStories, profileContentStory);
        storyID = responseStory.jsonPath().getString("id");

    }

    @Test
    public void createComments() {
        String endPoint = ENDPOINT + projectID + "/stories/" + storyID + "/comments";
        JSONObject profileContentComments = new JSONObject();
        profileContentComments.put("text", COMMENT);
        Response responseComment = RequestManager.setPost(endPoint, profileContentComments);
        Assert.assertEquals(responseComment.jsonPath().getString("text"), COMMENT);
        Assert.assertNotEquals(responseComment.jsonPath().getString("text"), "null");
        Assert.assertNotEquals(responseComment.jsonPath().getString("text"), " Firs comment");
        Assert.assertNotEquals(responseComment.jsonPath().getString("text"), "Firs comment  ");
    }

    @Test
    public void getComments() {
        String endPoint = ENDPOINT + projectID + "/stories/" + storyID + "/comments";
        Response responseComment = RequestManager.setGet(endPoint);
        Assert.assertEquals(responseComment.jsonPath().getString("text[0]"), COMMENT);
    }

    @AfterClass
    public void removeConditions() {
        String endPoint = ENDPOINT + projectID;
        response = RequestManager.setDelete(endPoint);
    }
}
