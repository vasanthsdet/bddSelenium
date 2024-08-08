Feature: Gmail Login

  Scenario: Invalid Login
    Given the user logs in with username "veda.dama2019" and password "text"
    Then Validate wrong password error text