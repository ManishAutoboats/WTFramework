Feature: BAT newsletter Guest Feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: Newsletter Guest - SMS Marketing options are displayed
    Given ensure glo newsletter element is present on page
    And ensure glo newsletter sign up button present on page
    And click on glo newsletter button
    Then verify the email and sms consent option is present

  Scenario: Glo IT Newsletter Guest - enter valid e-mail address and assert success message
    And ensure glo newsletter element is present on page
    And ensure glo newsletter sign up button present on page
    And click on glo newsletter button
    And Newsletter-glo user enters DOB
    And enter glo newsletter details and random email and submit
    And assert newsletter success validation message of 'Grazie per la tua iscrizione.'