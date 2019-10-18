package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkSpaces {
    private Response response;
    private static final int OK = 200;
    private int id;

    //@Before
    @Test
    public void getInitialData() {
        String path = "/my/workspaces";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "test 0");
        response = RequestManager.setPost(path, profileContent);
        response = RequestManager.setGet(path);
        id = response.jsonPath().getInt("[0].id");
        System.out.println(id + " extract int");
    }

    @Test
    public void cleanAll() {
        String path = "/my/workspaces";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "test 0");
        response = RequestManager.setPost(path, profileContent);
        response = RequestManager.setGet(path);
        id = response.jsonPath().getInt("[0].id");
        System.out.println(id + " extract int");
    }

    @Test
    public void getWorkSpaces() {
        String path = "/my/workspaces";
        response = RequestManager.setGet(path);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getString("[0].name"), "A new workspace");
    }

    @Test
    public void getWorkSpace() {
        String path0 = "/my/workspaces";
        response = RequestManager.setGet(path0);
        int id = response.jsonPath().getInt("[0].id");
        String path = "/my/workspaces" + "/" + id;
        response = RequestManager.setGet(path);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
    }

    @Test
    public void putWorkSpace() {
        String path0 = "/my/workspaces";
        response = RequestManager.setGet(path0);
        int id = response.jsonPath().getInt("[0].id");
        String path = "/my/workspaces" + "/" + id;

        //response = restAssured.setPut(path);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
    }

    @Test
    public void createWorkSpace() {
        String path = "/my/workspaces";
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "alfa");
        response = RequestManager.setPost(path, profileContent);
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertEquals(response.jsonPath().getString("name"), "alfa");
    }
}
