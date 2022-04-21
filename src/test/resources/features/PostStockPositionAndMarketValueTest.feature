Feature: Add Stock Position

Scenario: Fix the reference currency
Given I have am empty Database
When i want to insert by API a Stock Position
Then MarketValue in the object is filled
And cost is equal to marketValue
