package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ProjectsTest {
    private Response response;
    private static final String PROJECTNAME = "Lisa";
    private String id;

    @Test
    public void createProject() {
        String endPoint = "/projects";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", PROJECTNAME);
        response = RequestManager.setPost(endPoint, profileContent);
        id = response.jsonPath().getString("id");
        Assert.assertEquals(response.jsonPath().getString("name"), PROJECTNAME);
    }

    @Test
    public void getProject() {
        String endPoint = "/projects";
        response = RequestManager.setGet(endPoint);
        Assert.assertEquals(response.jsonPath().getString("name[0]"), PROJECTNAME);
    }

    @AfterClass
    public void removeProject() {
        String endPoint = "/projects/" + id;
        response = RequestManager.setDelete(endPoint);
    }
}
