import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.RestAssured;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import cucumber.api.java.en.When;


import java.io.IOException;

import static io.restassured.RestAssured.given;

public class MyStepdefs {
    private RestAssured restAssured = new RestAssured();
    private Response response;

    @Given("I perform GET operation for {string}")
    public void i_perform_GET_operation_for(String string) throws IOException, ParseException {
        response = given(restAssured.getRequestSpecification())
                .when()
                .get("/projects/2406139/stories/169196012/tasks/67913037");
    }

    @Then("I should see the kind as {string}")
    public void i_should_see_the_kind_as(String string) throws Throwable{
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
    }
}
