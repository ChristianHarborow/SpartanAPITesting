Feature: As a user, I need to be able to log in
  Scenario: The user logs in using valid credentials
    Given the credentials used are valid
    When the log in request is made
    Then a 200 status code is given
    And an auth token is returned

  Scenario Outline: The user logs in using invalid credentials
    Given the credentials used are "<username>" and "<password>"
    When the log in request is made
    Then a 401 status code is given

    Examples:
      | username | password |
      | john     | admin    |
      | ACTUAL   | pass123  |
      | sarah    | ACTUAL   |