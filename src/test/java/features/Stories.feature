Feature:
  Verify stories functionality for pivotal tracker

  Scenario: Verify name of a stories
    Given I perform GET for "/stories"
    Then I should see the requested_by_id as "3294402"