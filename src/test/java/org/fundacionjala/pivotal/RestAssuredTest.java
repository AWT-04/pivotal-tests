import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
    @Test
    public void getProjects() {
        String token = "2b599528475e32f3814cbfe78838272e";
        given().
                header("X-TrackerToken",token).
        when().
                get("https://www.pivotaltracker.com/services/v5/projects").
        then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void getWorkspaces() {
        String token = "2b599528475e32f3814cbfe78838272e";
        given().
                header("X-TrackerToken",token).
        when().
                get("https://www.pivotaltracker.com/services/v5/my/workspaces").
        then().
                assertThat().
                statusCode(200);
    }

}
