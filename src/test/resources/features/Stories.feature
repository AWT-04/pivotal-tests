Feature: Stories in projects
  @cleanProjects
  Scenario: Verify post request for story endpoint
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
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test {CURRENT_DATE}"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200

  @cleanProjects
  Scenario: Verify put request for story endpoint
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
    When I send a PUT request to "/projects/{Project.id}/stories/{S.id}" with body json:
     """
    {
    "name": "New Story Test name"
    }
    """
    Then I should see the status code as 200
    And I send a DELETE request to "/projects/{Project.id}"


  @cleanProjects
  Scenario: Verify delete request for story endpoint
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
    When I send a DELETE request to "/projects/{Project.id}/stories/{S.id}"
    Then I should see the status code as 200
    And I send a DELETE request to "/projects/{Project.id}"


  @cleanProjects
  Scenario: Verify feature story_type for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test",
    "story_type": "feature"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "story_type" as "feature"
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify bug story_type for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test",
    "story_type": "bug"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "story_type" as "bug"
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify chore story_type for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test",
    "story_type": "chore"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "story_type" as "chore"
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify release story_type for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test",
    "story_type": "release"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "story_type" as "release"
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify accepted current_state for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test",
    "estimate": 1.00 ,
    "current_state": "accepted"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "current_state" as "accepted"
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify delivered current_state for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test",
    "estimate": 1.00 ,
    "current_state": "delivered"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "current_state" as "delivered"
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify finished current_state for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body json:
    """
    {
    "name": "Story Test",
    "estimate": 1.00 ,
    "current_state": "finished"
    }
    """
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "current_state" as "finished"
    And I send a DELETE request to "/projects/{Project.id}"
