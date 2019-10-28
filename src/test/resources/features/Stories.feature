Feature: Stories in tasks
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
