package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.RequestManager;
import org.testng.Assert;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;


public class MyStepdefs {
    private Response response;


    /*Cucumber operations for Tasks*/
    /*GET*/
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String arg0) {
        response = RequestManager.setGet("/projects/2406102/stories/169156513/tasks/67928912");
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
         response = RequestManager.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
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
        response = RequestManager.setPut("/projects/2406102/stories/169156513/tasks/67890506", profileContent);
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
        response = RequestManager.setPost("/projects/2406102/stories/169156513/tasks", profileContent);
        String taskId = this.response.jsonPath().getString("id");
        System.out.println("ID created:" + taskId);
        response = RequestManager.setDelete("/projects/2406102/stories/169156513/tasks/" + taskId);
    }

    @Then("I should have status code as {string}")
    public void iShouldHaveStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 204);
    }










    /*Stories*/

    @Given("I perform GET for {string}")
    public void iPerformGETFor(String arg0) {
        response = RequestManager.setGet("/projects/2406102/stories/169156513");
    }

    @Then("I should see the requested_by_id as {string}")
    public void iShouldSeeTheRequested_by_idAs(String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "story");
        Assert.assertEquals(this.response.jsonPath().getString("current_state"), "unstarted");
    }

    @Given("I perform POST  for {string}")
    public void iPerformPOSTFor(String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New Story created from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories", profileContent);
    }

    @Then("I should see status code as {string}")
    public void iShouldSeeStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 200);
    }

    @Given("I perform PUT stories operation for {string}")
    public void iPerformPUTStoriesOperationFor(String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New name updated from Rest Assured");
        response = RequestManager.setPut("/projects/2406102/stories/169156513", profileContent);

    }

    @Then("I should verify the story status code as {string}")
    public void iShouldVerifyTheStoryStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 200);
    }



    @Given("I perform DELETE story operation for {string}")
    public void iPerformDELETEStoryOperationFor(String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New Task from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories", profileContent);
        String taskId = this.response.jsonPath().getString("id");
        System.out.println("ID created:" + taskId);
        response = RequestManager.setDelete("/projects/2406102/stories/" + taskId);

    }

    @Then("I should have story status code as {string}")
    public void iShouldHaveStoryStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 204);
    }
}
