Feature: BAT EYES Mobile and Desktop registration to Card Purchase velo
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option



  Scenario: Smoke Flow - Login - Visa Payment - Velo
    And user click on PersonIcon and Navigate to the Login Page
    When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And Customer makes a home delivery purchase with "mastercard"
    And Epok grab and output Order number
    Then Epok assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
