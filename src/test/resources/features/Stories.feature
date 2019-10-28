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
  Scenario Outline: Verify story_types for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to my project"/projects/{Project.id}/stories" with "<name>" & "<story_type>"
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "story_type" as "<story_type>"
    And I send a DELETE request to "/projects/{Project.id}"
    Examples:
      | name       | story_type     |
      | Story Test | feature        |
      | Story Test | bug            |
      | Story Test | chore          |
      | Story Test | release        |


  @cleanProjects
  Scenario Outline: Verify current_state for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "Project for testing"
    }
    """
    And I save response as "Project"
    When I send a POST request to my project"/projects/{Project.id}/stories" with "<name>" & "<estimate>" & "<current_state>"
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "current_state" as "<current_state>"
    And I send a DELETE request to "/projects/{Project.id}"
    Examples:
      | name       | estimate       | current_state  |
      | Story Test | 1.00           | accepted       |
      | Story Test | 1.00           | delivered      |
      | Story Test | 1.00           | finished       |
      | Story Test | 1.00           | started        |
      | Story Test | 1.00           | rejected       |
      | Story Test | 1.00           | unstarted      |
      | Story Test | 1.00           | unscheduled    |

# Negative Tests
  @cleanProjects
  Scenario: Verify no value for current_state for story endpoint
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
    "current_state": "None"
    }
    """
    And I save response as "S"
    Then I should see the status code as 400
    And I should see the "error" as "One or more request parameters was missing or invalid."
    And I send a DELETE request to "/projects/{Project.id}"

  @cleanProjects
  Scenario: Verify error for no estimate value with accepted current_state for story endpoint
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
    "current_state": "accepted"
    }
    """
    And I save response as "S"
    Then I should see the status code as 400
    And I should see the "general_problem" as "Stories in the accepted state must be estimated."
    And I send a DELETE request to "/projects/{Project.id}"

