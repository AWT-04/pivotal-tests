Feature: Stories in projects

  @cleanProjects
  Scenario: Verify post request for story endpoint
    Given I send a POST request to "/projects" with body json:
    """
    {
    "name": "{PREFIX} Project for testing {RANDOM}"
    }
    """
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
    "name": "Project for testing PUT"
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
    "name": "Project for testing DELETE"
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

#Scenario outline tests
  @cleanProjects
  Scenario Outline: Verify story_types for story endpoint
    Given I send a POST request to "/projects" with body:
      | name | "Project for testing" |
    And I save response as "Project"
    When I send a POST request to "/projects/{Project.id}/stories" with body:
      | name       | <name>       |
      | story_type | <story_type> |
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "story_type" as "<story_type>"
    And I send a DELETE request to "/projects/{Project.id}"
    Examples:
      | name       | story_type |
      | Story Test | feature    |
      | Story Test | bug        |
      | Story Test | chore      |
      | Story Test | release    |

  @cleanProjects
  Scenario Outline: Verify current_state for story endpoint
    Given I send a POST request to "/projects" with body:
      | name | "Project for testing" |
    And I save response as "Project"
#    When I send a POST request to my project"/projects/{Project.id}/stories" with "<name>" & "<estimate>" & "<current_state>"
    When I send a POST request to "/projects/{Project.id}/stories" with body:
      | name          | <name>          |
      | estimate      | <estimate>      |
      | current_state | <current_state> |
    And I save response as "S"
    Then I should see the status code as 200
    And I should see the "current_state" as "<current_state>"
    And I send a DELETE request to "/projects/{Project.id}"
    Examples:
      | name       | estimate | current_state |
      | Story Test | 1.00     | accepted      |
      | Story Test | 1.00     | delivered     |
      | Story Test | 1.00     | finished      |
      | Story Test | 1.00     | started       |
      | Story Test | 1.00     | rejected      |
      | Story Test | 1.00     | unstarted     |
      | Story Test | 1.00     | unscheduled   |

  @cleanProjects
  Scenario: Verify the size for story endpoint when adding stories
    Given I send a POST request to "/projects" with body:
      | name | "Project for testing" |
    And I save response as "Project"
    And I send a POST request to "/projects/{Project.id}/stories" with body list:
      | name         | story_type |
      | Story Test 1 | feature    |
      | Story Test 2 | feature    |
      | Story Test 3 | feature    |
    When I send a GET request to "/projects/{Project.id}/stories"
    And I save response as "Stories"
    Then I should see the size of "id" in "Stories" as 3

  @cleanProjects
  Scenario: Verify the size of bugs for story endpoint when adding stories
    Given I send a POST request to "/projects" with body:
      | name | "Project for testing" |
    And I save response as "Project"
    And I send a POST request to "/projects/{Project.id}/stories" with body list:
      | name         | story_type |
      | Story Test 1 | feature    |
      | Story Test 2 | bug        |
      | Story Test 3 | bug        |
      | Story Test 4 | chore      |
      | Story Test 5 | bug        |
      | Story Test 6 | chore      |
      | Story Test 7 | bug        |
    When I send a GET request to "/projects/{Project.id}/stories"
    And I save response as "Stories"
    Then I should see the size of type "bug" in "story_type" of "Stories" as 4

  @cleanProjects
  Scenario Outline: Verify the size of bugs with story_type query param
    Given I send a POST request to "/projects" with body:
      | name | "Project for testing" |
    And I save response as "Project"
    And I send a POST request to "/projects/{Project.id}/stories" with body list:
      | name         | story_type |
      | Story Test 1 | feature    |
      | Story Test 2 | bug        |
      | Story Test 3 | bug        |
      | Story Test 4 | chore      |
      | Story Test 5 | bug        |
      | Story Test 6 | chore      |
      | Story Test 7 | bug        |
    When I send a GET request to "/projects/{Project.id}/stories?with_story_type=<story_t>"
    And I save response as "Stories"
    Then I should see the size of type "<story_t>" in "story_type" of "Stories" as <size>
    Examples:
      | story_t | size |
      | bug     | 4    |
      | feature | 1    |
      | chore   | 2    |



  @cleanProjects
  Scenario Outline: Verify the size of bugs with state query param
    Given I send a POST request to "/projects" with body:
      | name | "Project for testing" |
    And I save response as "Project"
    And I send a POST request to "/projects/{Project.id}/stories" with body list:
      | name       | estimate | current_state |
      | Story Test | 1.00     | accepted      |
      | Story Test | 1.00     | delivered     |
      | Story Test | 1.00     | finished      |
      | Story Test | 1.00     | started       |
      | Story Test | 1.00     | rejected      |
      | Story Test | 1.00     | unstarted     |
      | Story Test | 1.00     | unscheduled   |
      | Story Test | 1.00     | accepted      |
      | Story Test | 1.00     | delivered     |
      | Story Test | 1.00     | finished      |
      | Story Test | 1.00     | started       |
      | Story Test | 1.00     | rejected      |
      | Story Test | 1.00     | unstarted     |
      | Story Test | 1.00     | unscheduled   |
      | Story Test | 1.00     | accepted      |
      | Story Test | 1.00     | delivered     |
      | Story Test | 1.00     | finished      |
    When I send a GET request to "/projects/{Project.id}/stories?with_state=<state>"
    And I save response as "Stories"
    Then I should see the size of type "<state>" in "current_state" of "Stories" as <size>
    Examples:
      | state       | size |
      | accepted    | 3    |
      | delivered   | 3    |
      | finished    | 3    |
      | started     | 2    |
      | rejected    | 2    |
      | unstarted   | 2    |
      | unscheduled | 2    |