@Spartan
@Post
Feature: As a user I need to be able to add new spartans

  Scenario: Create spartan using full valid body
    Given the request is authorised
    When the post spartan request is made using "validSpartanBody.json"
    Then a 200 status code is given
    And a SpartanDTO object is returned matching the request body

  Scenario: Create spartan with unauthorised request
    Given the request is unauthorised
    When the post spartan request is made using "validSpartanBody.json"
    Then a 401 status code is given

  Scenario: Create spartan using existing course
    Given the request is authorised
    When the post spartan request is made using "spartanWithExistingCourse.json"
    Then a 200 status code is given
    And a SpartanDTO object is returned matching the request body

  Scenario: Create spartan using existing stream
    Given the request is authorised
    When the post spartan request is made using "spartanWithExistingStream.json"
    Then a 200 status code is given
    And a SpartanDTO object is returned matching the request body

  Scenario: Create spartan using empty json object
    Given the request is authorised
    When the post spartan request is made using "emptyJson.json"
    Then a 400 status code is given
    And a message is returned describing the missing fields

  Scenario: Create spartan using no body
    Given the request is authorised
    When the post spartan request is made using "noJson.json"
    Then a 400 status code is given
    And a message is returned describing the missing body