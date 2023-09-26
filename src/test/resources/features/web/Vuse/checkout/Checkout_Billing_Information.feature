    Feature: BAT checkout billing information -Vuse
    Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed

  @VuseUKLive @VuseFRLive @VuseITReg @VuseITLive
    Scenario: 9608 Ordering - Check Out - Billing Information-Vuse
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And assert billing and shipping are the same tick box present

  @VuseUKReg @VuseFRReg
    Scenario: Checkout tests - not agreeing to Ts and Cs error message
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And assert billing and shipping are the same tick box present
    And select place order
    And assert text of 'acceptTsAndCsErrorMessageText.key' is displayed

    @VuseDEReg @VuseDELive
    Scenario: Verify Guiding text in Billing and Shipping line items On Checkout Page
      And click add new address button
      And click on Manual Address Entry link
      Then assert guiding text in address fields

      @VuseFRReg @VuseITReg
      Scenario: 9607 15993 Ordering - Check Out - Change Address -Vuse FR
        And Credit Card - MasterCard option and press next
        And enter mastercard details
        And assert new address button is displayed
        And click add new address button
        And assert text of 'firstNameText.key' is displayed

     @VuseMXReg @VuseMXLive
      Scenario: Vuse MX Ordering - Check Out - Change Address CTA and Age Verification Labels
        And assert new address button is displayed
        And click add new address button
        Then click on Cancel button on Add new Address pop-up
        And assert text of 'firstNameText.key' is displayed
        And click on Debit/Credit Card Payment Option
        Then assert Checkout Age Verification warning label
        And assert Terms and Conditions link in Age Verification Warning
#        And click on PayPal Payment Option // REMOVING THIS AS PAYPAL IS BEEN DESCOPED FROM MX STORE
#        Then assert Checkout Age Verification warning label
#        And assert Terms and Conditions link in Age Verification Warning

      @VuseITReg
      Scenario: Check Out - Change Address CTA and Age Verification
        And click on Debit/Credit Card Payment Option
        Then assert Checkout Age Verification warning label

      @VuseDEReg
      Scenario: check new address dialog is displayed on checkout page
        When click change address button
        And assert add new address link is displayed
        And click add new address link
        Then new address dialog is displayed

