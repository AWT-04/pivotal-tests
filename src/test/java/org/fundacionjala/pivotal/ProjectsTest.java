package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ProjectsTest {
    private ManageController restAssured = new ManageController();
    private Response response;
    private static final String PROJECTNAME = "LisaII";
    private String id;

    @Test
    public void post() {
        String endPoint = "/projects";
        String myJson = "{\"name\":\"LisaII\" }";
        response = restAssured.setPost(endPoint, myJson);
        id = response.jsonPath().getString("id");
        Assert.assertEquals(response.jsonPath().getString("name"), PROJECTNAME);
    }

    @AfterClass
    public void removeProject() {
        String endPoint = "/projects/" + id;
        response = restAssured.setDelete(endPoint);
    }

    @Test
    public void get() {
        String endPoint = "/projects";
        response = restAssured.setGet(endPoint);
        Assert.assertEquals(response.jsonPath().getString("name[0]"), "Lisa");
    }
}
