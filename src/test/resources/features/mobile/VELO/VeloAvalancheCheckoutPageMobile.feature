Feature: Velo Avalanche Checkout Page - Mobile

  Background: Velo Avalanche Checkout Page
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option

    @RegVeloZA_Mobile
    Scenario Outline: VELO ZA-Smoke Flow - Registration - Visa Payment - Delete Account
        And user clicks the person icon
        And create a new account
        And checkout any available product
        And I add the delivery address during payment
        And user views the cart
        And user clicks on checkout button
        And I can see my order summary on the right side of this page
        And Customer makes payu payment with "<Payment Type>"
        #And grab and output Order number
        #And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
        #And assert text of 'yourOrderNumberText.key' is displayed
        And the user navigates to My Account Page
        And user clicks on delete account
        And user clicks the person icon
        And user login with credentials
        And assert error message of invalid login is displayed for language "za"
        And I validate the "order confirmation" email
        Examples:
            | Payment Type  |
            | Pay gate card |

   @UniversalSmokeVeloZA_mobile
    Scenario Outline: VELO ZA-Smoke-mobile Flow - Registration - SID Payment - Delete Account
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And checkout any available product
        Then I am on the checkout page
        And I add the delivery address during payment
        And Customer makes payu payment with "<Payment Type>"
        And grab and output Order number
        And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
        And assert text of 'yourOrderNumberText.key' is displayed
        And the user navigates to My Account Page
        And user clicks on delete account
        And user clicks the person icon
        And user login with credentials
        And assert error message of invalid login is displayed for language "za"
        And I validate the "order confirmation" email
        Examples:
            |Payment Type|
            | Sid Secure EFT        |



    @RegVeloZA_Mobile
    Scenario: Checkout - Assert Phone Number field on Add New Address pop-up and Address section under Card Holder Details
        And create a new account via api and log in with the account
        And checkout any available product
        Then I am on the checkout page
        Then user click on change address link in checkoutpage
        Then assert phone number field is displayed as a mandatory field on Add Address pop-up
        And click use this address
        Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
        And populate address fields on Checkout page
        Then user clicks on Change Address link below card holder details
        And user selects New Address radiobutton and assert address form is populated
        Then assert phone number field is displayed as a mandatory field on Add Address form
        Then user populates address details using loqate including phone number and save
        And Customer makes payu payment with "Pay gate card"
        And grab and output Order number
        And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header


 @RegVeloZA_Mobile
 Scenario Outline: VELO ZA-Change Address in checkout
     Then user clicks the person icon
     And create a new account
     Then checkout any available product
     When I add the delivery address during payment
     Then user views the cart
     Then user clicks on checkout button
     Then user click on change address link
     Then Customer makes payu payment with "<Payment Type>"
     Then grab and output Order number
     And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
     And assert text of 'yourOrderNumberText.key' is displayed
     Then the user navigates to My Account Page
     Then user clicks on delete account
     Then user clicks the person icon
     Then user login with credentials
     And assert error message of invalid login is displayed for language "za"
     Then I validate the "order confirmation" email
     Examples:
         | Payment Type  |
         | Pay gate card |