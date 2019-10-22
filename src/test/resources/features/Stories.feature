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