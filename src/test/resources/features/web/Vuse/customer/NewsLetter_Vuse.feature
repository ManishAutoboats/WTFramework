Feature: Vuse Germany My Account page validations

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And user selects over 18 age confirmation option
    And select allow all from cookie popup
    When create a new account

  @VuseDEReg
  Scenario: Marketing Preferences -  Style Newsletter Page
    And user clicks the person icon
    And users clicks on the 'NewsLetter.key' text link
    And assert text of 'NewsLetterTitle.key' is displayed
    And assert save button is present
    And newsLetter div form displayed