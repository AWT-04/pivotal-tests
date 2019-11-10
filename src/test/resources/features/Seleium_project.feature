Feature: Project creation
  As an owner user, I want to add projects to a specific pivotal account,
  so that I can register my tasks, stories, workspaces in my projects.

  @InitializeDriver @CloseDriver
  Scenario: Create public project
    Given I login as user:
      | name     | raullaredo |
      | password | r4514812L* |
    When I click create project button
    And I enter "My project" the name of the project
    And I click the account options
    And I select the account
    And I click the accept button
    Then I should see the title "NAME" in the navigator
    And I should see the title "Public" in the navigator
    And I should see the project in the list of projects

  @InitializeDriver @CloseDriver
  Scenario: Create private project
    Given I login as user:
      | name     | raullaredo |
      | password | r4514812L* |
    When I click create project button
    And I set the project form:
      | name    | NAME       |
      | account | ramalaso   |
      | privacy | true      |
    And I click the accept button
    Then I should see the title "NAME" in the navigator
    And I should not see int the title "Public" in the navigator
    And I should see the project in the list of projects