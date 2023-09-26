Feature: Velo Avalanche Checkout Page

    @veloPLReg
    Scenario: 704073 AC1 AC2 Checkout page  PL- user has delivery address
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And checkout any available product
        Then I am on the checkout page
        And I can see my delivery and payment options on the left side of this page
        And I can see my order summary on the right side of this page
        And I can see there is a change address CTA


    @veloPLReg @veloPLLive
    Scenario Outline: 704073 AC2.2 Checkout page - Delivery Address  PL- user has delivery address
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And checkout any available product
        Then I am on the checkout page
        When I click on the change address CTA
        Then the section expands and I can see my default address with a radio button
        And assert new address button is displayed
        And click add new address button
        Then assert that the modal has a title
        And there is a CTA Use this address below to save my address preferences
        And there is a CANCEL button to collapse this section
        Examples:
            | language  |
            | pl        |
  #  @veloPLReg @veloPLLive // this delivery method is no avaliable on store velo-pl
    Scenario: 704073 VELO PL> AC3 Checkout page - Delivery Method
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And checkout any available product
        Then I am on the checkout page
        And I can see my delivery and payment options on the left side of this page
        And the delivery section shows only one delivery option Standard Delivery
        And the delivery method has a radio button which i can select


    @veloPLReg
    Scenario Outline: 704073 VELO PL> AC4.1 AC4.2 AC5 Checkout page - Payment Method and place order
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And checkout any available product
        Then I am on the checkout page
        And I can see my delivery and payment options on the left side of this page
        And I can see the total amount payable in this section
        And I see an expandable card payments tab in this section
        And Customer makes payu payment with "<Payment Type>"
        And grab and output Order number
        And assert text of '<thank you text>' is displayed
        And assert text of '<your order number text>' is displayed
        And user should receive a order confirmation email with order number
        Examples:
            | language  |Payment Type| thank you text        | your order number text |
            | pl        | visa       | ThankForPurchase-pl   | yourOrderNumberText-pl |

    @UniversalSmokeVeloPL_mobile
    Scenario Outline: 704073 VELO PL> AC4.1 AC4.2 AC5 Checkout page - Payment Method and place order- Mobile
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And checkout any available product
        Then I am on the checkout page
        And I see an expandable card payments tab in this section
        And Customer makes payu payment with "<Payment Type>"
        And grab and output Order number
        And assert text of '<thank you text>' is displayed
        And assert text of '<your order number text>' is displayed
        Examples:
            | language  |Payment Type| thank you text        | your order number text |
            | pl        | visa       | ThankForPurchase-pl   | yourOrderNumberText-pl |


    @UniversalSmokeVeloPL
    Scenario Outline: VELO PL-Smok e Flow - Registration - Visa Payment - Delete Account
        Given user navigates to BAT home page for language "pl"
        And create a new account via api
        And select allow all from cookie popup and select over 18 age confirmation option
        And take eyes screenshot
          | StoreLocatorPage        |
          | MeetVeloPage            |
          | ContactUsPage           |
          | CookiePrivacyPolicyPage |
          | CookieNoticePage        |
          | ProductsPlpPage         |
          | BuyOnlinePage           |
          | EsmokingWorldPage       |
        And checkout any available product
        Then I am on the checkout page
        And I add the delivery address during payment
        And I see an expandable card payments tab in this section
        And Customer makes payu payment with "<Payment Type>"
        And grab and output Order number
        And assert text of '<thank you text>' is displayed
        And assert text of '<your order number text>' is displayed
        And the user navigates to My Account Page
        Then take eyes screenshot
            | AddressBookPage                        |
            | MyAccountAddNewAddressPage             |
            | MyAccountOverviewPage                  |
            | MyAccountDeleteAccountPage             |
            | MyDetailsPage                          |
        And user clicks on delete account
        And user clicks the person icon
        And user login with credentials
        And assert error message of invalid login is displayed for language "pl"
        Examples:
            | language  |Payment Type| thank you text        | your order number text |
            | pl        | visa       | ThankForPurchase-pl   | yourOrderNumberText-pl |

    @UniversalSmokeVeloZA @veloZAReg
    Scenario Outline: VELO ZA-Smoke Flow - Registration - Visa Payment - Delete Account
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
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
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
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

@veloPLReg
    Scenario Outline: 690006 VELO PL> Payment> PayU
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And checkout any available product
    Then I am on the checkout page
    And Customer makes payu payment with "<Payment Type>"
    And grab and output Order number
    And assert text of 'ThankForPurchase-pl' is displayed
    And assert text of 'yourOrderNumberText-pl' is displayed
    Examples:
        |Payment Type   |
        | visa          |
        | payu          |
        | blik          |
        | mbank         |
        | pekao         |
        | ipko          |

    @veloBeReg
Scenario Outline: 700885 AC1 AC2 Checkout page - user has delivery address
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    And checkout any available product
    Then I am on the checkout page
    And I can see my delivery and payment options on the left side of this page
    And I can see my order summary on the right side of this page
    And I can see there is a change address CTA
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |
@veloBeReg @veloBELive
Scenario Outline: 700885 AC2.2 Checkout page - Delivery Address - user has delivery address
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And checkout any available product
    Then I am on the checkout page
    When I click on the change address CTA
    Then the section expands and I can see my default address with a radio button
    And assert new address button is displayed
    And click add new address button
    Then assert that the modal has a title
    And there is a CTA Use this address below to save my address preferences
    And there is a CANCEL button to collapse this section
        Examples:
            | language  |
            | en        |
            | nl        |
            | fr        |
@veloBeReg @veloBELive
Scenario Outline: 700932 AC4 721138 700885 VELO BE> AC3 Checkout page - Delivery Method
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    And checkout any available product
    Then I am on the checkout page
    And I can see my delivery and payment options on the left side of this page
    And the delivery section shows only one delivery option Standard Delivery
    And the delivery method has a radio button which i can select
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |

@veloBeReg @UniversalSmokeVeloBE_mobile @UniversalSmokeVeloBE
Scenario Outline: 700891 700885 VELO BE> AC4.1 AC4.2 AC5 Checkout page - Payment Method and place order
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    And checkout any available product
    Then I am on the checkout page
    And I can see my delivery and payment options on the left side of this page
    And I can see the total amount payable in this section
    And I see an expandable card payments tab in this section
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And there is a checkbox to save my card details
    And there is consent checkbox
    And a link to terms and conditions policy
    And select place order
    And I should see that confirmation is sent to my email address
    And assert text of '<thank you text>' is displayed
    And grab and output Order number
    And assert text of '<your order number text>' is displayed
    And I validate the "order confirmation" email
    Examples:
        | language  | thank you text        | your order number text |
        | en        | ThankForPurchase-en   | yourOrderNumberText-en |
        | nl        | ThankForPurchase-nl   | yourOrderNumberText-nl |
        | fr        | ThankForPurchase-fr   | yourOrderNumberText-fr |
@veloBeReg
Scenario Outline: 700888 Thank you page AC1 700885 AC6 AC7 Checkout Page - logged out user journey
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And checkout any available product
    And login with valid details
    Then I am on the checkout page
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And a link to terms and conditions policy
    And there is consent checkbox
    And select place order
    And assert thank you text of "<thank you text>" is displayed
    And assert your ordernumber text of "<your order number text>" is displayed
    And I should see that confirmation is sent to my email address
    And there is a CTA to Go to My Account
    And there is a CTA to return to homepage
    Examples:
        | language  | thank you text        | your order number text |
        | en        | ThankForPurchase-en   | yourOrderNumberText-en |
        | nl        | ThankForPurchase-nl   | yourOrderNumberText-nl |
        | fr        | ThankForPurchase-fr   | yourOrderNumberText-fr |
@veloBeReg
Scenario Outline: 700932 AC1 Delivery methods - viewing options
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And checkout any available product
    And I can see the delivery options available including radio button estimated time and price
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |


    @veloPLReg
    Scenario: 834856 VELO PL- Place the order using cash on delivery
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And checkout any available product
        Then I am on the checkout page
        And Customer makes payu payment with "COD"
        Then Order Confirmation page thankYouMessageHeading is displayed

 #   @veloPLReg // delivery method no longer avaliable on velo-pl store
    Scenario: 845402 VELO PL- last-mile delivery company at checkout page
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And checkout any available product
        Then I am on the checkout page
        And user select last mile delivery option
        And assert radio buttons for delivery companies is displayed

    @veloZAReg
    Scenario: Checkout - Assert Phone Number field on Add New Address pop-up and Address section under Card Holder Details
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
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


 @veloZAReg
 Scenario Outline: VELO ZA-Change Address in checkout
     Given user navigates to BAT home page for language "za"
     And select allow all from cookie popup and select over 18 age confirmation option
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