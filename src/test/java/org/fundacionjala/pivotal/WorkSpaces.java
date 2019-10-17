package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkSpaces {
    private ManageController restAssured = new ManageController();
    private Response response;
    private static final int OK = 200;

    @Test
    public void getWorkSpaces() {
        String path = "/my/workspaces";
        response = restAssured.setGet(path);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getString("[0].name"), "A new workspace");
    }

    @Test
    public void getWorkSpace() {
        String path0 = "/my/workspaces";
        response = restAssured.setGet(path0);
        int id = response.jsonPath().getInt("[0].id");
        String path = "/my/workspaces" + "/" + id;
        response = restAssured.setGet(path);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
    }
    
    @Test
    public void putWorkSpace() {
        String path0 = "/my/workspaces";
        response = restAssured.setGet(path0);
        int id = response.jsonPath().getInt("[0].id");
        String path = "/my/workspaces" + "/" + id;

        response = restAssured.setPut(path);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
    }

    @Test
    public void createWorkSpace() {
        String path = "/my/workspaces";
        String myJson = "{\"name\":\"alfa\" }";
        response = restAssured.setPost(path, myJson);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getString("name"), "alfa");
    }
}
