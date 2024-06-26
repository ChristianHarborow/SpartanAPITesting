Feature: As a user, I need to be able to get a list of all spartans

  Scenario: The user makes an authorised get spartans request
    Given the request is authorised
    When the get spartans request is made
    Then a 200 status code is given
    And a list of spartan objects is returned

  Scenario: The user makes an unauthorised get spartans request
    Given the request is unauthorised
    When the get spartans request is made
    Then a 401 status code is given