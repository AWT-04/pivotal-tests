Feature:
  Verify tasks functionality for pivotal tracker

  Scenario: Verify post operation
    Given I perform POST operation for "/projects"
    And I fill the body with:
    """
    {
    "name": "Project Test211"
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
    When I perform POST operation for a "/projects/{Project.id}/stories/{S.id}/tasks"
    """
    {
    "description": "Tasks Test"
    }
    """
    Then I should see the status code as "200"

  Scenario: Verify put operation
    Given I perform POST operation for "/projects"
    """
    {
    "name": "Project Test"
    }
    """
    And I save response as "Project"
    And I perform POST operation for "/projects/{Project.id}/stories"
    """
    {
    "name": "Story Test"
    }
    """
    And I save response as "S"
    When I perform POST operation for "/projects/{Project.id}/stories/{S.id}/tasks"
    """
    {
    "description": "Tasks Test"
    }
    """
    And I save response as "MyKey"
    When I perform PUT operation for "/projects/{Project.id}/stories/{S.id}/tasks/{MyKey.id}"
    Then I should verify the status code as "200"

  Scenario: Verify delete operation
    Given I perform DELETE operation for "/task"
    Then I should have status code as "200"
