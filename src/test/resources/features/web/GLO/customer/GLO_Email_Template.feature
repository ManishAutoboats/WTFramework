Feature: Email template verification after registering new user

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @gloPlRegression2
  Scenario: Verification for email template
    And create a new account
    And assert text of 'successRegistraionMsg.key' is displayed
    And assert email template is correct