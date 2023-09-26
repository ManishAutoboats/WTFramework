Feature: GLO newsletter Registration Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloItRegression2
  Scenario: NewsLetter - Register Newsletter Subscription on registration page
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And ensure newsletter sign up button present on page
    And click on glo newsletter button
    And Newsletter-glo user enters DOB
    And enter glo newsletter details and random email and submit on registration page
    Then assert newsletter success validation message of 'Grazie per la tua iscrizione.'