Feature:
  Verify tasks functionality for pivotal tracker

  Scenario: Verify post operation
    Given I perform POST operation for "/projects"
    And I fill the body with:
    """
    {
    "name": "Project for testing POST"
    }
    """
    And I save response as "Project"
    And I perform POST operation for other "/projects/{ProjectId}/stories"
    And I fill the story body with:
    """
    {
    "name": "Story Test"
    }
    """
    And I save response too as "S"
    When I perform POST operation for a "/projects/{ProjectId}/stories/{SId}/tasks"
    And I fill the task body with:
    """
    {
    "description": "Tasks Test"
    }
    """
    Then I should see the status code as "200"
    And Clean environment



  Scenario: Verify put operation
    Given I perform POST operation for "/projects"
    And I fill the body with:
    """
    {
    "name": "Project for testing PUT"
    }
    """
    And I save response as "Project"
    And I perform POST operation for other "/projects/{ProjectId}/stories"
    And I fill the story body with:
    """
    {
    "name": "Story Test"
    }
    """
    And I save response too as "S"
    And I perform POST operation for a "/projects/{ProjectId}/stories/{SId}/tasks"
    And I fill the task body with:
    """
    {
    "description": "Tasks Test"
    }
    """
    When I perform PUT operation for a "/projects/{ProjectId}/stories/{SId}/tasks/{TaskId}"
    And I fill the task body with new name:
    """
    {
    "description": "New Tasks Test name"
    }
    """
    Then I should see the status code as "200"
    And Clean environment

  Scenario: Verify delete operation
    Given I perform POST operation for "/projects"
    And I fill the body with:
    """
    {
    "name": "Project for testing DELETE5"
    }
    """
    And I save response as "Project"
    When Delete project for "/projects/"
    Then I should see the status code "204"