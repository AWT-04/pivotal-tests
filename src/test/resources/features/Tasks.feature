Feature: Tasks in stories
  @cleanProjects
  Scenario: Verify post request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "{PREFIX} Project for testing {RANDOM}"
    }
    """
#    Given I send a POST request to "/projects" with body:
#      | name   | PREFIX Project for testing RANDOM |
#      | public | true                              |
    Given I send a POST request to "/projects" with json file "json/ProjectJsonBody.json"
    And I save response as "Project"
    And I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test {CURRENT_DATE}"
    }
    """
    And I save response as "S"
    When I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    Then I should see the status code as 200
#    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify put request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing PUT456"
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
    And I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
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
    And I send a DELETE request to "/projects/{Project.id}"


  @cleanProjects
  Scenario: Verify delete request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing DELETE124"
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
    And I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    When I send a DELETE request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    Then I should see the status code as 200
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify error request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "{PREFIX} Project for testing {RANDOM}"
    }
    """
#    Given I send a POST request to "/projects" with body:
#      | name   | PREFIX Project for testing RANDOM |
#      | public | true                              |
    Given I send a POST request to "/projects" with json file "json/ProjectJsonBody.json"
    And I save response as "Project"
    And I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test {CURRENT_DATE}"
    }
    """
    And I save response as "S"
    When I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    }
    """
    And I save response as "Task"
    Then I should see the status code as 400
    And I should see the "error" as "One or more request parameters was missing or invalid."
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify GET request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing DELETE124"
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
    And I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "Task"
    When I send a GET request to "/projects/{Project.id}/stories/{S.id}/tasks/{Task.id}"
    Then I should see the status code as 200
    And I should see the "kind" as "task"
