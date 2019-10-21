package org.fundacionjala.pivotal.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.RequestManager;
import org.testng.Assert;
import org.json.simple.JSONObject;

import java.time.LocalTime;

public class MyStepdefs {
    private Response response;
    private static final int POSITION = 3;
    private static final int VALUEOK = 200;
    private static final int VALUEOK1 = 204;
    private static final String DESCRIPTION = "description";
    public String projectId;
    public String storyId;
    public String taskId;

    public String createProject(JSONObject body){
        response = RequestManager.setPost("/projects", body);
        projectId = this.response.jsonPath().getString("id");
        return projectId;
    }

    public String createStory(String projectID, JSONObject bodyStory){
        response = RequestManager.setPost("/projects/"+ projectID+"/stories", bodyStory);
        storyId = this.response.jsonPath().getString("id");
        return storyId;
    }

    public String createTask(String projectID, String storyID, JSONObject bodyTask){
        response = RequestManager.setPost("/projects/"+ projectID+"/stories/"+storyID+"/tasks", bodyTask);
        taskId = this.response.jsonPath().getString("id");
        return taskId;
    }

    /*Cucumber operations for Tasks*/
    /*GET*/
    @Given("A project created")
    public void aProjectCreated() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        projectId = createProject(projectContent);
    }

    @And("A Story created")
    public void aStoryCreated() {
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        storyId = createStory(projectId, storyContent);
    }

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(final String arg0) {


        JSONObject taskContent = new JSONObject();
        taskContent.put("description", "Task description " + LocalTime.now());
        taskId = createTask(projectId, storyId, taskContent);
        response = RequestManager.setGet("/projects/"+projectId+"/stories/" + storyId+"/tasks/" + taskId);
    }

    @Then("I should see the kind as {string}")
    public void iShouldSeeTheKindAs(final String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "task");
        Assert.assertEquals(this.response.jsonPath().getBoolean("complete"), false);
    }
    /*POST*/
     @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(final String arg0) {
         JSONObject projectContent = new JSONObject();
         projectContent.put("name", "Project " + LocalTime.now() );
         String projectId = createProject(projectContent);
         JSONObject storyContent = new JSONObject();
         storyContent.put("name", "Story name " + LocalTime.now());
         String storyId = createStory(projectId, storyContent);
         JSONObject profileContent = new JSONObject();
         profileContent.put(DESCRIPTION, "New Task from Rest Assured"+ LocalTime.now());
         response = RequestManager.setPost("/projects/"+projectId+"/stories/" + storyId+"/tasks" , profileContent);
    }

    @Then("I should see the status code as {string}")
    public void iShouldSeeTheStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    /*PUT*/
    @Given("I perform PUT operation for {string}")
    public void iPerformPUTOperationFor(final String arg0) {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        JSONObject taskContent = new JSONObject();
        taskContent.put("description", "Task description " + LocalTime.now());
        String taskId = createTask(projectId, storyId, taskContent);
        JSONObject newStoryContent = new JSONObject();
        newStoryContent.put("description", "New task description" + LocalTime.now());
        response = RequestManager.setPut("/projects/"+ projectId +"/stories/" + storyId+"/tasks/" + taskId, newStoryContent);
    }

    @Then("I should verify the status code as {string}")
    public void iShouldVerifyTheStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

/*DELETE*/
    @Given("I perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(final String arg0) {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", "Project " + LocalTime.now() );
        String projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", "Story name " + LocalTime.now());
        String storyId = createStory(projectId, storyContent);
        JSONObject taskContent = new JSONObject();
        taskContent.put("description", "Task description " + LocalTime.now());
        String taskId = createTask(projectId, storyId, taskContent);
        JSONObject newStoryContent = new JSONObject();
        newStoryContent.put("description", "New task description" + LocalTime.now());
        response = RequestManager.setDelete("/projects/"+ projectId +"/stories/" + storyId+"/tasks/" + taskId);
    }

    @Then("I should have status code as {string}")
    public void iShouldHaveStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK1);
    }

    /*Stories*/
    @Given("I perform GET for {string}")
    public void iPerformGETFor(final String arg0) {
        response = RequestManager.setGet("/projects/2406102/stories/169156513");
    }

    @Then("I should see the requested_by_id as {string}")
    public void iShouldSeeTheRequestedByIdAs(final String arg0) {
        Assert.assertEquals(this.response.jsonPath().getString("kind"), "story");
        Assert.assertEquals(this.response.jsonPath().getString("current_state"), "unstarted");
    }

    @Given("I perform POST  for {string}")
    public void iPerformPOSTFor(final String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New Story created from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories", profileContent);
    }

    @Then("I should see status code as {string}")
    public void iShouldSeeStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    @Given("I perform PUT stories operation for {string}")
    public void iPerformPUTStoriesOperationFor(final String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New name updated from Rest Assured");
        response = RequestManager.setPut("/projects/2406102/stories/169156513", profileContent);

    }

    @Then("I should verify the story status code as {string}")
    public void iShouldVerifyTheStoryStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    @Given("I perform DELETE story operation for {string}")
    public void iPerformDELETEStoryOperationFor(final String arg0) {
        JSONObject profileContent = new JSONObject();
        profileContent.put("name", "New Task delete from Rest Assured");
        response = RequestManager.setPost("/projects/2406102/stories", profileContent);
        String taskId = this.response.jsonPath().getString("id");
        response = RequestManager.setDelete("/projects/2406102/stories/" + taskId);
    }

    @Then("I should have story status code as {string}")
    public void iShouldHaveStoryStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK1);
    }


}
