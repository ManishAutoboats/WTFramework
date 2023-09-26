Feature: BAT checkout feature - CREDIT CARDS - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account via api and log in with the account
    And search and checkout product "searchProductItem.key"
    And Payment page details confirmed

  @RegVuseFR_Mobile
  Scenario: Mobile - Checkout tests - Signed In - Credit Cards - VISA Card - Debit Card Option - Payment-Vuse
    And selectable shipping options displayed
    And click on shipping method
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    #And assert text of 'yourOrderNumberText.key' is displayed
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
