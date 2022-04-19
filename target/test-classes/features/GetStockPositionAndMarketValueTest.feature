Feature: Get Stock Position and MarketValue

  Scenario: Get Stock Position and Market Value
    Given I have in database a fakeStockPosition
    When I want to retrieve by API my Stock Position and Market Value associated
    Then Value retrieved are corresponding to the value of the fake stock position
    And Market value in the object is filled

  Scenario: Get Stock Position and Market Value With Generated API
    Given I have in database a fakeStockPosition
    When I want to retrieve by generated API my Stock Position and Market Value associated
    Then Value retrieved in response body are corresponding to the value of the fake stock position
    And Market value in the object in response body is filled