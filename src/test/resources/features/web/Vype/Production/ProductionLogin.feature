#This is the login feature file
  ##Testing
    ## Invalid sign-in & error generated
      ## invalid email
      ## missing password
      ## incorrect password
    ## Valid sign-in
    ## Nav to Registration page
    ## Nav to Forgotten password page
Feature: PRODUCTION - BAT Login feature
  Background: Navigate to BAT Home page
    Given user navigates to the production homepage
    And select allow all from cookie popup and select over 18 age confirmation option

    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu

  Scenario: Correct Sign-in & logout
    When user enters sign in details, with username of 'ukLiveLogin.key' and password 'ukLiveLoginPassword.key'
    Then user is successfully logged in
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'logoutText.key' text link
    And url contains 'logoutSuccessURL.key'