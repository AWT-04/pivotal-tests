Feature: Tasks in stories

  Background:
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    And I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test"
    }
    """
    And I save response as "S"

  @cleanProjects
  Scenario: Verify post request for task endpoint
    When I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    Then I should see the status code as 200
    # Missing validations on body response

  @cleanProjects
  Scenario: Verify put request for task endpoint
    Given I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    When I send a PUT request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}" with body json:
    """
    {
    "description": "New Task Test name"
    }
    """
    Then I should see the status code as 200
    # Missing validations on body response

  @cleanProjects
  Scenario: Verify delete request for task endpoint
    Given I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    When I send a DELETE request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    Then I should see the status code as 204
    # Missing validations
    # GET

  @cleanProjects
  Scenario: Verify error request for task endpoint
    When I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    }
    """
    Then I should see the status code as 400
    And I should see the "error" as "One or more request parameters was missing or invalid."

  @cleanProjects
  Scenario: Verify GET request for task endpoint
    Given I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    When I send a GET request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    Then I should see the status code as 200
    And I should see the "kind" as "task"
    And I should see the "description" as "Tasks Test"
    And I should see the "complete" as "false"
    And I should see the "position" as "1"

  @cleanProjects
  Scenario: Verify GET complete parameter request for task endpoint
    Given I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test position 1"
    }
    """
    And I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test",
    "complete": true,
    "position": 1
    }
    """
    And I save response as "Task"
    When I send a GET request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    Then I should see the status code as 200
    And I should see the "kind" as "task"
    And I should see the "description" as "Tasks Test"
    And I should see the "complete" as "true"
    And I should see the "position" as "1"

    # Join this scenario with the previous scenario
  @cleanProjects
  Scenario: Verify GET position parameter request for task endpoint
    Given I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test",
    "position": 1
    }
    """
    And I save response as "Task"
    When I send a GET request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    Then I should see the status code as 200
    And I should see the "position" as "1"