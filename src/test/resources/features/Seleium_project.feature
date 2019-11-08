Feature: Project creation
  As an owner user, I want to add projects to a specific pivotal account,
  so that I can register my tasks, stories, workspaces in my projects.

  Scenario: Login with correct username and password
    Given I navigate to the login page
    And I enter "raullaredo" as userName
    And I click login button
    And I enter "r4514812L*" as password
    And I click login button
    Then I should see the dashboard page