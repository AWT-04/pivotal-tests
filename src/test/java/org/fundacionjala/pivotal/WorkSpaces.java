package org.fundacionjala.pivotal;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkSpaces {
    private ManageController restAssured = new ManageController();
    private Response response;
    private static final int OK = 200;


    @Test
    public void getWorkSpacesResponse() {

        Assert.assertEquals(this.response.statusCode(), OK);
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "");
        Assert.assertEquals(this.response.jsonPath().getString("id"), "AWT04");
        Assert.assertEquals(this.response.jsonPath().getString("name"), "AWT04");
        Assert.assertEquals(this.response.jsonPath().getString("person_id"), "AWT04");
    }
    public void postNewWorkSpace() {
    }
}
