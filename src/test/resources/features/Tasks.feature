Feature:
  Verify tasks functionality for pivotal tracker

  Scenario: Verify name of a task
    Given A project created
    And A Story created
    When I perform GET operation for "/task"
    Then I should see the kind as "task"

  Scenario: Verify post operation
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
