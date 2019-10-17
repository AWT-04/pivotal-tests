package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkSpaces {
    private ManageController restAssured = new ManageController();
    private Response response;
    private static final int OK = 200;

    @Test(expectedExceptions = AssertionError.class)
    public void getProjectsWithSpecifications() {
        String path = "/projects/2406139";
        response = restAssured.setGet(path);
        Assert.assertEquals(response.jsonPath().getString("name"), "AWT04");
    }
}
