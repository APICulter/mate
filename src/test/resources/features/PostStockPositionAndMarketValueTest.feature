Feature: Add Stock Position

  Scenario: Add the reference currency
    Given I have am empty Database
    When I want to insert by API a Stock Position
    Then Market value in the object is filled
    And Cost is equal to marketValue
