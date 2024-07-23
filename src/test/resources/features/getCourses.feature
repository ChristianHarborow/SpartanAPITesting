@Course
@Get
Feature: As a user, I need to be able to get a list of all courses

  Scenario: The user makes an authorised get courses request
    Given the request is authorised
    When the get courses request is made
    Then a 200 status code is given
    And a list of course objects is returned

  Scenario: The user makes an unauthorised get courses request
    Given the request is unauthorised
    When the get courses request is made
    Then a 401 status code is given