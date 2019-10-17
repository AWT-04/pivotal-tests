Feature:
  Verify tasks functionality for pivotal tracker

  Scenario: Verify name of a task
    Given I perform GET operation for "/task"
    Then I should see the kind as "task"
