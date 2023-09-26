Feature: BAT Account management - saved payment methods feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloDeRegression2
  Scenario: My account - saved payment methods page
    Given create a new account via api
    And Glo user clicks on Buy Link and add the product to mini cart
    And Payment page details confirmed
    And user select Card Payment option
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And click Save This Card checkbox
    Then select place order from Card
    And grab and output Order number
    And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
    And user clicks on PersonIcon and Navigate to My Account Page
    And users clicks on the 'savedCardText.key' text link
    Then assert saved card div is present