Feature:
  Verify stories functionality for pivotal tracker

  Scenario: Verify post operation
    Given I perform POST operation for "/projects"
    And I fill the body with:
    """
    {
    "name": "Project for testing POST"
    }
    """
    And I save response as "Project"
    When I perform POST operation for other "/projects/{ProjectId}/stories"
    And I fill the story body with:
    """
    {
    "name": "Story Test"
    }
    """
    Then I should see the kind as "story"
    And Clean environment


  Scenario: Verify put operation
    Given I perform POST operation for "/projects"
    And I fill the body with:
    """
    {
    "name": "Project for testing PUT4"
    }
    """
    And I save response as "P"
    And I perform POST operation for other "/projects/{ProjectId}/stories"
    And I fill the story body with:
    """
    {
    "name": "Story Test"
    }
    """
    And I save response too as "S"
    When I perform PUT story operation for a "/projects/{ProjectId}/stories/{SId}"
    And I fill the task body with new name:
    """
    {
    "name": "New task name"
    }
    """
    Then I should see the status code as 200
    And Clean environment

  Scenario: Verify delete operation
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
    When Delete project for "/projects/"
    Then I should see the status code "204"