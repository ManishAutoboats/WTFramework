Feature: BAT pop up banner test

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

   #user story 539544 pop up banner cases
  @gloKzRegression
  Scenario:Login user can see pop up banner
      When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
      Then assert pop up banner displayed
      And user closes the RDB pop-up banner if present
      And user click on 'device' menu navigation
      And click first result
