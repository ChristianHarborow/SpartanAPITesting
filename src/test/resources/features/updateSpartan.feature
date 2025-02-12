@Spartan
@Put
Feature: As a user I need to be able to update existing spartans

  Scenario: Update spartan request made with full valid body
    Given the request is authorised
    When the put spartan request is made to spartan id "10" using "updateSpartan.json"
    Then a 200 status code is given
    And a SpartanDTO object is returned matching the request body

  Scenario: Unauthorised update request
    Given the request is unauthorised
    When the put spartan request is made to spartan id "10" using "updateSpartan.json"
    Then a 401 status code is given

  Scenario: Update non existent spartan
    Given the request is authorised
    When the put spartan request is made to spartan id "99" using "updateSpartanNonExistentSpartan.json"
    Then a 404 status code is given

  Scenario: Update spartan request with non existent course id
    Given the request is authorised
    When the put spartan request is made to spartan id "11" using "updateSpartanNonExistingCourse.json"
    Then a 404 status code is given
    And spartan with id "11" remains unchanged and matches "originalSpartan11.json"

  Scenario: Update spartan using empty json object
    Given the request is authorised
    When the put spartan request is made to spartan id "12" using "emptyJson.json"
    Then a 400 status code is given
    And a message is returned describing the missing fields
    And spartan with id "12" remains unchanged and matches "spartan12.json"

  Scenario: Update spartan using no body
    Given the request is authorised
    When the put spartan request is made to spartan id "13" using "noJson.json"
    Then a 400 status code is given
    And a message is returned describing the missing body
    And spartan with id "13" remains unchanged and matches "spartan13.json"