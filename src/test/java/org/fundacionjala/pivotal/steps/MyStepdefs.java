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
    private static final int VALUEOK = 200;
    private static final int VALUEOK1 = 204;
    private static final String DESCRIPTION = "description";
    private static final String TASK_DESCRIPTION = "Task description";
    private static final String STORY_NAME = "Story name ";
    private static final String PROJECT = "Project";
    private static final String TASKS = "/tasks/";
    private static final String STORIES = "/stories/";
    private static final String PROJECTS = "/projects/";
    private String projectId;
    private String storyId;
    private String taskId;

    public String createProject(final JSONObject body) {
        response = RequestManager.setPost("/projects", body);
        projectId = this.response.jsonPath().getString("id");
        return projectId;
    }

    public String createStory(final String projectID, final JSONObject bodyStory) {
        response = RequestManager.setPost(PROJECTS + projectID + STORIES, bodyStory);
        storyId = this.response.jsonPath().getString("id");
        return storyId;
    }

    public String createTask(final String projectID, final String storyID, final JSONObject bodyTask) {
        response = RequestManager.setPost(PROJECTS + projectID + STORIES + storyID + TASKS, bodyTask);
        taskId = this.response.jsonPath().getString("id");
        return taskId;
    }

    /*Cucumber operations for Tasks*/
    /*GET*/
    @Given("A project created")
    public void aProjectCreated() {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", PROJECT + LocalTime.now());
        projectId = createProject(projectContent);
    }

    @And("A Story created")
    public void aStoryCreated() {
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", STORY_NAME + LocalTime.now());
        storyId = createStory(projectId, storyContent);
    }

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(final String arg0) {


        JSONObject taskContent = new JSONObject();
        taskContent.put(DESCRIPTION, TASK_DESCRIPTION + LocalTime.now());
        taskId = createTask(projectId, storyId, taskContent);
        response = RequestManager.setGet(PROJECTS + projectId + STORIES + storyId + TASKS + taskId);
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
         projectContent.put("name", PROJECT + LocalTime.now());
         projectId = createProject(projectContent);
         JSONObject storyContent = new JSONObject();
         storyContent.put("name", STORY_NAME + LocalTime.now());
         String storyId1 = createStory(projectId, storyContent);
         JSONObject profileContent = new JSONObject();
         profileContent.put(DESCRIPTION, "New Task from Rest Assured" + LocalTime.now());
         response = RequestManager.setPost(PROJECTS + projectId + STORIES + storyId1 + TASKS, profileContent);
    }

    @Then("I should see the status code as {string}")
    public void iShouldSeeTheStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

    /*PUT*/
    @Given("I perform PUT operation for {string}")
    public void iPerformPUTOperationFor(final String arg0) {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", PROJECT + LocalTime.now());
        projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", STORY_NAME + LocalTime.now());
        String storyId2 = createStory(projectId, storyContent);
        JSONObject taskContent = new JSONObject();
        taskContent.put(DESCRIPTION, TASK_DESCRIPTION + LocalTime.now());
        String taskId1 = createTask(projectId, storyId2, taskContent);
        JSONObject newStoryContent = new JSONObject();
        newStoryContent.put(DESCRIPTION, "New task description" + LocalTime.now());
        response = RequestManager.setPut(PROJECTS + projectId + STORIES
                + storyId + TASKS + taskId1, newStoryContent);
    }

    @Then("I should verify the status code as {string}")
    public void iShouldVerifyTheStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK);
    }

/*DELETE*/
    @Given("I perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(final String arg0) {
        JSONObject projectContent = new JSONObject();
        projectContent.put("name", PROJECT + LocalTime.now());
        projectId = createProject(projectContent);
        JSONObject storyContent = new JSONObject();
        storyContent.put("name", STORY_NAME + LocalTime.now());
        String storyId3 = createStory(projectId, storyContent);
        JSONObject taskContent = new JSONObject();
        taskContent.put(DESCRIPTION, "Task description " + LocalTime.now());
        String taskId2 = createTask(projectId, storyId3, taskContent);
        JSONObject newStoryContent = new JSONObject();
        newStoryContent.put(DESCRIPTION, "New task description" + LocalTime.now());
        response = RequestManager.setDelete(PROJECTS + projectId + STORIES + storyId + TASKS + taskId2);
    }

    @Then("I should have status code as {string}")
    public void iShouldHaveStatusCodeAs(final String arg0) {
        Assert.assertEquals(this.response.statusCode(), VALUEOK1);
    }
}
