Feature: BAT checkout feature - CREDIT CARDS
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed

@VuseUKReg @VuseFRReg @VuseITReg
Scenario: Checkout tests - Signed In - Credit Cards - VISA Card - Debit Card Option - Payment-Vuse
    And selectable shipping options displayed
    And click on shipping method
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button to move head and place the order
    And assert text of 'yourOrderNumberText.key' is displayed
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed

  @VuseMXReg
  Scenario: Checkout tests - Signed In - Credit Cards - With Electronic Invoice - Payment
    And customer selects electronic invoice check and enter details
    And enter mastercard details
    And select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed

  @COReg
  Scenario: Checkout tests - Signed In - Credit Cards - Payment
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And tick agree to terms and conditions
    And select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed



