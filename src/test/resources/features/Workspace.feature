Feature: Workspaces tests

  Scenario: Verify post request for workspace endpoint
    Given I send a GET request to "/my/workspaces"
    When I save response as "workspace"
    Then Validate status code 200 of response "workspace"

  Scenario: