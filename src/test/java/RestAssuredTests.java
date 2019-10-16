import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.fundacionjala.pivotal.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class RestAssuredTests {

    private RestAssured restAssured = new RestAssured();
    private Response response = given(restAssured.getRequestSpecification())
            .when()
            .get("/projects/2406102");

    @Test
    public void test_Pivotal_Tracker_GetProjectsWithSpecifications() {
        Assert.assertEquals(response.jsonPath().getString("name"), "New name from Rest Assured");
    }







































































    @Test
    public void test_Pivotal_Tracker_Get_Specific_Project() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
                given().
                header("X-TrackerToken",token).
                when().
                get("https://www.pivotaltracker.com/services/v5/projects/2406102").
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                and().
                contentType(ContentType.JSON).
                and().
                body("name", equalTo("New name from Rest Assured")).
                and().
                body("iteration_length", equalTo(1))
        ;
    }

    @Test
    public void test_Pivotal_Tracker_GetProjects() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
        header("X-TrackerToken",token).
        when().
        get("https://www.pivotaltracker.com/services/v5/projects").
        then().
        assertThat().
        statusCode(200).
        and().
        contentType(ContentType.JSON)
        ;
    }



    @Test
    public void test_Pivotal_Tracker_GetEpics() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
        header("X-TrackerToken",token).
        when().
        get("https://www.pivotaltracker.com/services/v5/epics").
        then().
        assertThat().
        statusCode(404);
    }

    @Test
    public void test_Pivotal_Tracker_GetWorkspaces() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
                header("X-TrackerToken",token).
                when().
                get("https://www.pivotaltracker.com/services/v5/my/workspaces").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_Pivotal_Tracker_GetOwners() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
                header("X-TrackerToken",token).
                when().
                get("https://www.pivotaltracker.com/services/v5/projects/2406102/stories/169178777/owners").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_Pivotal_Tracker_GetStories() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
                header("X-TrackerToken",token).
                when().
                get("https://www.pivotaltracker.com/services/v5/projects/2406102/stories?date_format=millis&with_state=unstarted").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void UpdateProject() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
                header("X-TrackerToken",token).
                param("name", "New name from Rest Assured").
                when().
                put("https://www.pivotaltracker.com/services/v5/projects/2406102").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void CreateProject() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
        header("X-TrackerToken",token).
        param("name", "New project created from Rest Assured").
        when().
        post("https://www.pivotaltracker.com/services/v5/projects").
        then().
        assertThat().
        statusCode(200);
    }

    @Test
    public void DeleteProject() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
        header("X-TrackerToken",token).
        header("Content-Type","application/json").
        when().
        delete("https://www.pivotaltracker.com/services/v5/projects/2406361").
        then().
        assertThat().
        statusCode(204);
    }

    @Test
    public void CreateStoryGivesSuceesfull() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
        header("X-TrackerToken",token).
        header("Content-Type","application/json").
        param("name", "New Story created from Rest Assured").
        when().
        post("https://www.pivotaltracker.com/services/v5/projects/2406102/stories").
        then().
        assertThat().
        statusCode(200);
    }
}
