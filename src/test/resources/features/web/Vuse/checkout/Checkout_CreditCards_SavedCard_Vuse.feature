Feature: BAT checkout feature - SAVED CARD
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu

  @VuseUKReg @VuseFRReg2 @VuseITReg
  Scenario: Checkout tests - Delete Saved Cards and place an order with a New Card
    Then create a new account via api and log in with the account
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'savedCardText.key' link
    And user deletes all the saved cards from My Saved Cards list
    And Search for a product 'uniqueSearchProduct.key' , and add to cart from PDP page
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And click Save This Card checkbox
    And tick agree to terms and conditions and select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And click on a re-order link
    And user selects proceed to checkout from cart page
    And Payment page details confirmed
    And enter select package shop details
    And select Card Payment option then credit Card option
    And click use saved card
    And click Use Saved Cards - Vype IT
    And tick agree to terms and conditions and select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'savedCardText.key' link
    And user deletes all the saved cards from My Saved Cards list
    And assert text of 'noSavedCardsText.key' is displayed

  @VuseITReg
  Scenario: Customer unable to checkout subs without checking subs checkbox
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And Search for a product 'uniqueSearchProduct.key' , and add to cart from PDP page
    When click on Basket icon and proceed to Payment Page
    Then Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    When click Save This Card checkbox
    And select place order without tick TandC
    Then assert text of 'termsAndCondtionError.key' is displayed

