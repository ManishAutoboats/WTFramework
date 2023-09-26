
  @LyftRegression
  Feature: BAT checkout feature - CREDIT CARDS
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account via api and log in with the account
#    And user clicks the person icon -lyft
#    And user login with credentials registered via api
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on basket icon
    And user clicks on checkout button
    Then Payment page details confirmed

  Scenario: 889150 Checkout -Credit Cards - VISA Card Payment - Order Confirmation Email
    And selectable shipping options displayed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    Then assert text of 'yourOrderNumberText.key' is displayed
    And grab and output Order number
    And user should receive a order confirmation email with order number
    And the links on the email will take user back to website
