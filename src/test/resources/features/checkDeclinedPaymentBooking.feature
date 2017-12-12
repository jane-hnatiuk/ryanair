@payments
Feature: Get declined message of failed payment
Scenario: Book flight with fake card

Given I make a booking from “Dublin” to “Wroclaw” on 13/03/2018 for 2 adults and 1 child
When I pay for booking with card details “5555 5555 5555 5557”, “10/18” and “265”
Then I should get payment declined message