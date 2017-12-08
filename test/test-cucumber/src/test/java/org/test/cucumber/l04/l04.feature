Feature: Data Tables are handy for passing a list of values to a step definition

  Scenario: Data Tables
    Given the following users
      | Aslak  | aslak@cucumber.io  | @aslak_hellesoy |
      | Julien | julien@cucumber.io | @jbpros         |
      | Matt   | matt@cucumber.io   | @mattwynne      |
    When we count the list
    Then the size is 3
