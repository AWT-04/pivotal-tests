Feature: Tasks in stories

  Scenario: Verify post request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing POST411"
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
    When I send a POST request to "/projects/{Project.id}/stories/{S.id}/tasks" with body json:
    """
    {
    "description": "Tasks Test"
    }
    """
    Then I should see the status code as 200
    And Clean project to "/projects/{Project.id}"

  Scenario: Verify put request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing PUT4"
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
    And Clean project to "/projects/{Project.id}"


  Scenario: Verify delete request for task endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing DELETE111"
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
    Then I should see the status code as 204
    And Clean project to "/projects/{Project.id}"
