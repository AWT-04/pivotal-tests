package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.ManageController;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class MyStepdefs {
    private ManageController restAssured = new ManageController();
    private Response response;
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String arg0) {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139/stories/169196012/tasks/67913037");

    }

    @Then("I should see the kind as {string}")
    public void iShouldSeeTheKindAs(String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
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


    //Post operation
    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
        HashMap<String, String> profileContent = new HashMap<>();
        profileContent.put("name", "New Task from Rest-Assured");
        response = restAssured.setPost("/projects/2406139/stories/169196012/tasks", profileContent);
    }
    @Then("I should see the status code as {string}")
    public void iShouldSeeTheStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 200);
    }


    @Given("I perform POST  for {string}")
    public void iPerformPOSTFor(String arg0) {
        HashMap<String, String> profileContent = new HashMap<>();
        profileContent.put("name", "New Story from Rest-Assured");
        response = restAssured.setPost("/projects/2406139/stories", profileContent);
    }

    @Then("I should see status code as {string}")
    public void iShouldSeeStatusCodeAs(String arg0) {
        Assert.assertEquals(this.response.statusCode(), 200);
    }
}
