package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ProjectsTest {
    private Response response;
    private static final String PROJECTNAME = "LisaII";
    private String id;

    @Test
    public void post() {
        String endPoint = "/projects";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", PROJECTNAME);
        response = RequestManager.setPost(endPoint, profileContent);
        id = response.jsonPath().getString("id");
        Assert.assertEquals(response.jsonPath().getString("name"), PROJECTNAME);
    }

    @AfterClass
    public void removeProject() {
        String endPoint = "/projects/" + id;
        response = RequestManager.setDelete(endPoint);
    }

    @Test
    public void get() {
        String endPoint = "/projects";
        response = RequestManager.setGet(endPoint);
        Assert.assertEquals(response.jsonPath().getString("name[0]"), "Project ramalaso");
    }
}
