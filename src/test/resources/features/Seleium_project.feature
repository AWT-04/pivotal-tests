Feature: Project creation
  As an owner user, I want to add projects to a specific pivotal account,
  so that I can register my tasks, stories, workspaces in my projects.

  @InitializeDriver
  Scenario: Login with correct username and password
    Given I navigate to the login page
    And I enter "raullaredo" as userName
    And I click login button
    And I enter "r4514812L*" as password
    And I click login button
    When I click create project button
    And I enter "My project" the name of the project
    And I click the account options
    And I select the account
    And I click the accept button
    Then I should see the title "My project" in the navigator
