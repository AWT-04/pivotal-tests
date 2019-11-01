Feature: Workspaces tests
  # Happy path
  Scenario: Confirm a workspace creation
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """
    When I save response as "myWorkspace"
    Then I should see the status code as 200
    And I should see "id" is not null
    And I should see "kind" is not null

  @cleanWorkspaces
  Scenario Outline: Verify get request for workspace
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"AT_workspaceTest"
        }
      """
    And I should see the status code as 200
    When I send a GET request to "/my/workspaces"
    And I save response as "myWorkspace"
    And I should see the status code as 200
    Then I should see the size of type "<variable>" in "story_type" of "myWorkspace" as <value>
    Examples:
      | variable | value              |
      | name     | "AT_workspaceTest" |
      | kind     | "workspace"        |

  Scenario: Verify get request for workspace endpoint
    Given I send a GET request to "/my/workspaces"
    When I save response as "workspace"
    Then I should see the status code as 200
    And I should see "id" is not null

  # negative tests
  Scenario: Negative workspace creation with invalid parameters
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":""
        }
      """
    When I save response as "myWorkspace"
    Then I should see the status code as 400
    And I should see the "kind" as "error"
    And I should see the "general_problem" as "Name can't be blank"
