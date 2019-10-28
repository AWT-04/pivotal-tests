Feature: Workspaces tests

  @cleanWorkspaces
  Scenario: Confirm a workspace creation
    Given I send a POST request to "/my/workspaces" with body json:
    """
    {
    "name":"{RANDOM}"
    }
    """
    When I save response as "myWorkspace"
    Then I should see the status code as 200

 # Scenario: Verify post request for workspace endpoint
  #  Given I send a GET request to "/my/workspaces"
  #  When I save response as "workspace"
   # Then I should see the status code as 200
