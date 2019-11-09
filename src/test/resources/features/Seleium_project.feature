Feature: Project creation
  As an owner user, I want to add projects to a specific pivotal account,
  so that I can register my tasks, stories, workspaces in my projects.

  @InitializeDriver @CloseDriver
  Scenario: Login with correct username and password
    Given I login as user:
      | name     | raullaredo |
      | password | r4514812L* |
    #Given I log in as "owner" account
    When I click create project button
    And I enter "My project" the name of the project
    And I click the account options
    And I select the account
    And I click the accept button
    Then I should see the title "NAME" in the navigator
