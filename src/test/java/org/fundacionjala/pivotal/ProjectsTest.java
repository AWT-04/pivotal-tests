package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectsTest {
    private ManageController restAssured = new ManageController();
    private Response response;
    private static final String PROJECTNAME = "LisaII";

    @Test
    public void post() {
        String endPoint = "/projects";
        String myJson = "{\"name\":\"LisaII\" }";
        response = restAssured.setPost(endPoint, myJson);
        Assert.assertEquals(response.jsonPath().getString("name"), PROJECTNAME);
    }

    @Test
    public void get() {
        String endPoint = "/projects";
        response = restAssured.setGet(endPoint);
        Assert.assertEquals(response.jsonPath().getString("name[0]"), "Lisa");
    }
}
