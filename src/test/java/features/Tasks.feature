Feature:
  Verify tasks functionality for pivotal tracker

  Scenario: Verify name of a task
    Given I perform GET operation for "/task"
    Then I should see the kind as "task"

  Scenario: Verify post operation
    Given I perform POST operation for "/task"
    Then I should see the status code as "200"

  Scenario: Verify put operation
    Given I perform PUT operation for "/task"
    Then I should verify the status code as "200"

  Scenario: Verify delete operation
    Given I perform DELETE operation for "/task"
    Then I should have status code as "200"



