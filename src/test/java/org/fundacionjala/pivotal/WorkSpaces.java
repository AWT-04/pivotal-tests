package org.fundacionjala.pivotal;


import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WorkSpaces {
    private Response response;
    private static final int OK = 200;
    private int id;
    private String name;

    @BeforeTest
    public void getInitialData() {
        String path = "/my/workspaces";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "test 0");
        response = RequestManager.setPost(path, profileContent);
        response = RequestManager.setGet(path);
        id = response.jsonPath().getInt("[0].id");
        name = response.jsonPath().getString("[0].name");
    }

    @Test
    public void getWorkSpaces() {
        String path = "/my/workspaces";
        response = RequestManager.setGet(path);
        Assert.assertEquals(response.statusCode(), OK);
        //Assert.assertEquals(response.jsonPath().getString("name"), name);
        System.out.println(response.jsonPath().getString("name") + "   **************************  " + name);
    }

    @Test
    public void getWorkSpace() {
        String path = "/my/workspaces" + "/" + id;
        response = RequestManager.setGet(path);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        System.out.println(response.jsonPath().getInt("id") + "   **************************  " + id);
    }

    @Test
    public void putWorkSpace() {
        String path0 = "/my/workspaces";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "test 0");
        String path = "/my/workspaces" + "/" + id;
        response = RequestManager.setPut(path,profileContent);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
    }
/*
    @Test
    public void createWorkSpace() {
        String path = "/my/workspaces";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "alfa");
        response = RequestManager.setPost(path, profileContent);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getString("name"), "alfa");
    }

    @Test
    public void cleanAll() {
        String path = "/my/workspaces";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "test 0");
        response = RequestManager.setPost(path, profileContent);
        response = RequestManager.setGet(path);
        id = response.jsonPath().getInt("[0].id");
        name = response.jsonPath().getString("[0].name");
    }*/
}
