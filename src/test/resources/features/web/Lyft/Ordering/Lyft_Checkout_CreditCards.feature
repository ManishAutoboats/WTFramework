##This is the Checkout feature file
    ## Credit Cards - both debit and credit options
  @LyftRegression @checkout @LyftDKReg @LyftDKSmoke
  Feature: BAT checkout feature - CREDIT CARDS
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on basket icon
    And user clicks on checkout button
    Then Payment page details confirmed

  @LyftSmoke
  Scenario: Checkout- Credit Cards - Master Card Payment
    And selectable shipping options displayed
    And enter select package shop details
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And click save card details
    And tick agree to terms and conditions
    And select place order
    Then assert print receipt link is present
    Then assert text of 'ThankForPurchase.key' is displayed

  @LyftSmoke
  Scenario: Checkout -Credit Cards - VISA Card Payment
    And selectable shipping options displayed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And assert text of 'yourOrderNumberText.key' is displayed
    Then assert print receipt link is present
    Then assert text of 'ThankForPurchase.key' is displayed
