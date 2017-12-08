Feature: Refund item

  Background:
    Given a $100 microwave was sold on 2015-11-03
    And today is 2015-11-18
  
  Scenario: Jeff returns a faulty microwave
    Given Jeff has bought a microwave for $100
    And he has a receipt
    When he returns the microwave
    And spoke with seller
    Then Jeff should be refunded $100
    And get back the money from "Sparkasse"
    
