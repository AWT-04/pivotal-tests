Feature: Tasks in stories

  Background:
    Given I use "owner" user
    And I send a POST request to "/projects" with body json:
    """
    {
    "name": "{RANDOM}",
    "new_account_name": "Test"
    }
    """
    And I save response as "Project"
    And I save the request endpoint for deleting
    And I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test"
    }
    """
    And I save response as "S"

  @cleanData
  Scenario: Verify post request for task endpoint
    When I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    Then I should see the status code as 200
    And  I should see "id" is not null
    And I should see the kind as "task"
    And I should see the "complete" as "false"
    And I should see the "position" as "1"

  @cleanData
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
    And  I should see "id" is not null
    And I should see the kind as "task"
    And I should see the "complete" as "false"
    And I should see the "position" as "1"

  @cleanData
  Scenario: Verify delete request for task endpoint
    Given I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks"
    }
    """
    And I save response as "Task"
    When I send a DELETE request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    Then I should see the status code as 204
    And I send a GET request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    And I should see the status code as 404
    And I should see the "kind" as "error"
    And I should see the "code" as "unfound_resource"

  @cleanData
  Scenario: Verify error request for task endpoint
    When I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    }
    """
    Then I should see the status code as 400
    And I should see the "error" as "One or more request parameters was missing or invalid."

  @cleanData
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

  @cleanData
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
  @cleanData
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