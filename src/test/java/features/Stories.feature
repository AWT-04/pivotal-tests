Feature:
  Verify stories functionality for pivotal tracker

  Scenario: Verify name of a stories
    Given I perform GET for "/stories"
    Then I should see the requested_by_id as "3294402"

  Scenario: Verify post operation
    Given I perform POST  for "/stories"
    Then I should see status code as "200"

  Scenario: Verify put operation for stories
    Given I perform PUT stories operation for "/task"
    Then I should verify the story status code as "200"

  Scenario: Verify delete operation
    Given I perform DELETE story operation for "/task"
    Then I should have story status code as "200"