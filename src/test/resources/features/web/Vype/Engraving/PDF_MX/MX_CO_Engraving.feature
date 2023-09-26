@mxEngraving #@coEngraving #@COReg Not is scope
Feature: Mexico Engraving
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: 269034 - Process start - Open Engraving iFrame - Asserted presented - Close iFrame - Confirm closed
    And search for engraving product, open personalisation window
    When assert engraving iFrame displayed to user
    Then close engraving iFrame

  Scenario: 269034 - Process start - Open Engraving iFrame - Asserted presented - user cancels engraving process
    And search for engraving product, open personalisation window
    When user selects cancel button to stop engraving process
    Then assert engraving iFrame not present

  Scenario Outline: 269055 Adding Text for Engraving, horizontal and vertical, BVA and EP
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of '<Front or Back>'
    And select text option
    And select positional text alignment '<Text alignment>'
    And update engraving text to '<Text Added>'
    And engraving input field contains '<Text displayed>'
    And image overlay preview text should be '<Text displayed>'
    Examples:
      |Front or Back | Text alignment| Text Added        | Text displayed        |
      |     front    | vertical      |Rodriguez1         | Rodriguez1            |
      |     front    | horizontal    |Rodriguez Burnley  | Rod                   |
      |     front    | horizontal    |12345678901234     | 123                   |
      |     back     | vertical      |12345678901234     | 1234567890            |
      |     back     | horizontal    |Enrique            | Enr                   |
  Scenario: 269055 Summary tests - Adding Text for Engraving, confirm selection and assert summary contents expected text, edit and delete product, summary no longer displayed
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of 'front'
    And select text option
    And select positional text alignment 'horizontal'
    And update engraving text to 't34'
    And engraving input field contains 't34'
    And image overlay preview text should be 't34'
    And select submit selection
    And summary should be displayed to user
    And summary contains the expected text of 't34'
    And summary contains the expected text of 'Tipografía - Abebedera'
    And summary contains the expected text of 'Alineación de texto - Horizontal'
    And summary contains the expected text of 'Mensaje -'
    And edit selection from summary section
    And edit text selection displayed to user
    And delete selection from summary section
    And summary should not be displayed to user

  Scenario: Pattern and text selection, add to basket and proceed to checkout page
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of 'front'
    And select Pattern CTA
    And select Pattern Palma
    And add confirm pattern selection
    And update engraving text to 'mda'
    And select submit selection
    And add engraving options to basket
    And assert engraving iFrame not present
    And click on basket icon
    And click on proceed to checkout button
    And login with valid details
    And Payment page details confirmed

  Scenario: Invalid Text entered error message presented
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of 'front'
    And select text option
    And select positional text alignment 'front'
    And update engraving text to '!"£'
    And select submit selection
    And invalid text error message displayed to user

  Scenario: selecting skins and pods options with no engraving option selected
    And search for engraving product, open personalisation window
    And select pods option without engraving options and assert error message

  Scenario: 269055 Engraving added and summary updated to reflect user input correctly
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of 'front'
    And select text option
    And select positional text alignment 'front'
    And update engraving text to 'tba'
    And select submit selection
    And summary should be displayed to user
    And summary contains the expected text of 'tba'

  Scenario: 269055 Profanity filter
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of 'front'
    And select text option
    And select positional text alignment 'front'
    And update engraving text to 'gay'
    And select submit selection
    And profanity error message displayed to user

  Scenario: 269053 Engraving types - Pattern and Mini icon
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of 'front'
    And select Pattern CTA
    And assert Pattern displayed
    And select mini icons CTA
    And assert mini icons displayed

  Scenario: Engraving flow and payment
    And create a new account
    And search for engraving product, open personalisation window
    And select customer engraving CTA
    And select text engraving location of 'front'
    And select Pattern CTA
    And select Pattern Palma
    And add confirm pattern selection
    And update engraving text to 'mda'
    And select submit selection
    And select pods option amd qty and add to order
    And add engraving options to basket
    And assert engraving iFrame not present
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    And Credit Card - VISA order option and press next
    And enter visa card details
    And click save card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button to move head and place the order
    And assert text of 'ThankForPurchase.key' is displayed