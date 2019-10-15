import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTests {
    @Test
    public void test_Pivotal_Tracker_GetProjects() {
        String token = "bfb2dcd6dd5650981b0147da1f9301d1";
        given().
        header("X-TrackerToken",token).
        when().
        get("https://www.pivotaltracker.com/services/v5/projects").
        then().
        assertThat().
        statusCode(200);
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
                statusCode(200);
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




}
