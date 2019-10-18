package steps;

import gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.ManageController;
import org.testng.Assert;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;


public class MyStepdefs {
    private ManageController restAssured = new ManageController();
    private Response response;


    /*Cucumber operations for Tasks*/
    /*GET*/
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String arg0) {
        response = restAssured.setGet("/projects/2406102/stories/169156513/tasks/67928912");
    }

    @Then("I should see the kind as {string}")
    public void iShouldSeeTheKindAs(String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
        Assert.assertEquals(this.response.jsonPath().getBoolean("complete"), false);
        Assert.assertEquals(this.response.jsonPath().getInt("position"), 3);
    }
    /*POST*/
     @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
         JSONObject profileContent = new JSONObject();
         profileContent.put("description", "New Task from Rest Assured");
         response = restAssured.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
    }

    @Then("I should see the status code as {string}")
    public void iShouldSeeTheStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 200);
    }

    /*PUT*/
    @Given("I perform PUT operation for {string}")
    public void iPerformPUTOperationFor(String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("description", "New name for task from Rest Assured");
        response = restAssured.setPut("/projects/2406102/stories/169156513/tasks/67890506", profileContent);
    }

    @Then("I should verify the status code as {string}")
    public void iShouldVerifyTheStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 200);
    }



/*DELETE*/
    @Given("I perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("description", "New Task from Rest Assured");
        response = restAssured.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
        String taskId = this.response.jsonPath().getString("id");
        System.out.println("ID created:" + taskId);
        response = restAssured.setDelete("/projects/2406102/stories/169156513/tasks/" + taskId);
    }

    @Then("I should have status code as {string}")
    public void iShouldHaveStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 204);
    }




















































































    /*Stories*/





    @Given("I perform POST  for {string}")
    public void iPerformPOSTFor(String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New Story from Rest-Assured");
        response = restAssured.setPost("/projects/2406139/stories", profileContent);
    }

    @Then("I should see status code as {string}")
    public void iShouldSeeStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 200);
    }




    @Given("I perform GET for {string}")
    public void iPerformGETFor(String arg0) {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139/stories/169196012");
    }

    @Then("I should see the requested_by_id as {string}")
    public void iShouldSeeTheRequested_by_idAs(String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("story_type"), "feature");
        Assert.assertEquals(this.response.jsonPath().getInt("requested_by_id"), 3294402);
    }



}
