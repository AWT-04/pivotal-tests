import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.ManageController;
import org.testng.Assert;

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
}
