Feature: Workspaces tests
# Happy path

  Scenario: I want confirm a workspace creation without projects
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """
    When I should see the status code as 200
    Then I should see "id" is not null
    And I should see "person_id" is not null
    And I should see the kind as "workspace"


  Scenario: I want confirm a workspace creation with a project
    Given I send a POST request to "/projects" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """
    And I save response as "newProject"
    And I send a GET request to "/projects/{newProject.id}"
    And I save response as "p"
    And I should see the status code as 200
    And I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}",
        "project_ids": [{p.id}]
        }
      """
    Then I should see the status code as 200
    And I should see "id" is not null
    And I should see "person_id" is not null
    And I should see the "kind" as "workspace"
    And I should see "id" is not null
    And I should see "person_id" is not null

  @cleanProjectsBefore
  Scenario: I want confirm a workspace creation with projects
    Given I send a POST request to "/projects" with body json:
      """
        {
        "name":"{RANDOM}",
        "name":"{RANDOM}",
        "name":"{RANDOM}",
        "name":"{RANDOM}"
        }
      """
    And I send a GET request to "/projects"
    And I save response as "p"
    And I should see the status code as 200
    And I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}",
        "project_ids": {p.id}
        }
      """
    Then I should see the status code as 200
    And I should see "id" is not null
    And I should see "person_id" is not null
    And I should see the "kind" as "workspace"
    And I should see "id" is not null
    And I should see "person_id" is not null

  @cleanProjectsBefore
  Scenario: I want to validate GET method execution for workspaces
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """
    And I should see the status code as 200
    And I send a GET request to "/my/workspaces"
    And I save response as "myWorkspace"
    When I should see the status code as 200
    Then I should see "id" is not null
    And I should see "person_id" is not null
    And I should see the "kind" as "workspace"
    And I should see "id" is not null
    And I should see "person_id" is not null

  @cleanProjects
  @cleanWorkspaces
  Scenario: I want confirm a workspaces creation with projects in a single request
    Given I send a POST request to "/projects" with body json:
      """
        {
        "name":"{RANDOM}",
        "name":"{RANDOM}",
        "name":"{RANDOM}",
        "name":"{RANDOM}"
        }
      """
    And I send a GET request to "/projects"
    And I save response as "p"
    And I should see the status code as 200
    And I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}",
        "project_ids": {p.id},
        "name":"{RANDOM}",
        "project_ids": {p.id},
        "name":"{RANDOM}",
        "project_ids": {p.id}
        }
      """
    Then I should see the status code as 200
    And I should see "id" is not null
    And I should see "person_id" is not null
    And I should see the "kind" as "workspace"
    And I should see "id" is not null
    And I should see "person_id" is not null

  @cleanWorkspacesBefore
  Scenario: I want to validate GET method execution for workspaces by specific ID
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """
    And I save response as "myWorkspace"
    When I should see the status code as 200
    And I send a GET request to "/my/workspaces/{myWorkspace.id}"
    Then I should see the status code as 200
    And I should see the kind as "workspace"
    And I should see "id" is not null
    And I should see "person_id" is not null

  @cleanProjects
  Scenario: I want to validate a PUT request
    Given I send a POST request to "/projects" with body json:
      """
        {
        "name":"{RANDOM}",
        "name":"{RANDOM}"
        }
      """
    And I save response as "pro"
    And I should see the status code as 200
    And I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """

    And I save response as "ws"
    And I should see the status code as 200
    And I send a PUT request to "/my/workspaces/{ws.id}" with body json:
      """
        {
        "project_ids": [{pro.id}]
        }
      """
    When I save response as "newWorkspace"
    Then I should see the status code as 200
    And I should see the "kind" as "workspace"
    And I should see "id" is not null
    And I should see "person_id" is not null


  @cleanWorkspacesBefore
  Scenario: I want to validate DELETE method execution for workspaces by specific ID
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """
    And I save response as "myWorkspace"
    When I should see the status code as 200
    And I send a DELETE request to "/my/workspaces/{myWorkspace.id}"
    Then I save response as "ws"
    And I should see the kind as "workspace"

  # negative tests
  @cleanWorkspaces
  Scenario: I want validate error message of workspace creation with empty name
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

  @cleanWorkspaces
  Scenario: I want validate error message of workspace creation with invalid project ids
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}",
        "project_ids":[99]
        }
      """
    When I save response as "myWorkspace"
    Then I should see the status code as 400
    And I should see the "kind" as "error"
    And I should see the "error" as "One or more request parameters was missing or invalid."

  @cleanProjects
  Scenario: I want validate error message of workspace creation with invalid project ids
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        }
      """
    When I save response as "myWorkspace"
    Then I should see the status code as 400
    And I should see the "kind" as "error"
    And I should see the "error" as "One or more request parameters was missing or invalid."

  @cleanProjects
  Scenario: I want validate error message for not existing workspace
    Given I send a GET request to "/my/workspaces/ABC"
    Then I should see the status code as 404
    And I should see the "kind" as "error"
    And I should see the "code" as "unfound_resource"

  @cleanWorkspacesBefore
  Scenario: I want to validate error control of DELETE request for un existing workspace
    Given I send a DELETE request to "/my/workspaces/123"
    And I save response as "delete"
    When I should see the status code as 404
    And I should see the "kind" as "error"
    And I should see the "code" as "unfound_resource"

  @cleanProjects
  Scenario: I want to validate an error sending parameters to workspaces PUT request
    Given I send a POST request to "/projects" with body json:
      """
        {
        "name":"{RANDOM}",
        "name":"{RANDOM}"
        }
      """
    And I save response as "pro"
    And I should see the status code as 200
    And I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"{RANDOM}"
        }
      """
    And I save response as "ws"
    And I should see the status code as 200
    And I send a PUT request to "/my/workspaces/{ws.id}" with body json:
      """
        {
        "project_ids": {pro.id}
        }
      """
    When I save response as "newWorkspace"
    Then I should see the status code as 400
    And I should see the "kind" as "error"
    And I should see "error" is not null
    And I should see the "code" as "invalid_parameter"
    And I should see the "general_problem" as "'project_ids' must be an array of int values"

  @cleanWorkspaces
  Scenario: I want validate error message of workspace creation with invalid comma at the end of parameter
    Given I send a POST request to "/my/workspaces" with body json:
      """
        {
        "name":"ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890",
        }
      """
    When I save response as "myWorkspace"
    Then I should see the status code as 500
    And I should see the "kind" as "error"
    And I should see the "error" as "An unexpected condition occurred."