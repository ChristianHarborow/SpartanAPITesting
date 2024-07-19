Feature: As a user, I need to be able to get a specific spartan

  Scenario: The user makes a get spartan request to an existing id
    Given the request is authorised
    When the get spartan request is made to id "1"
    Then a 200 status code is given
    And a SpartanDTO object is returned matching "existingSpartan.json"

  Scenario: The user makes an unauthorised get spartan request
    Given the request is unauthorised
    When the get spartan request is made to id "1"
    Then a 401 status code is given

  Scenario: The user makes a get spartan request to a non existent id
    Given the request is authorised
    When the get spartan request is made to id "999"
    Then a 404 status code is given

  Scenario: The user makes a get spartan request to an invalid id
    Given the request is authorised
    When the get spartan request is made to id "one"
    Then a 400 status code is given
    And a message is returned describing the invalid id "one"