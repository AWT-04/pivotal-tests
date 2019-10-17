package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Test2 {
    private ManageController restAssured = new ManageController();
    private Response response;


    @Test(expectedExceptions = AssertionError.class)
    public void getProjectsWithSpecifications() {
        String path = "/projects/2406139";
        response = restAssured.setGet(path);
        Assert.assertEquals(response.jsonPath().getString("name"), "AWT04");
    }
}
