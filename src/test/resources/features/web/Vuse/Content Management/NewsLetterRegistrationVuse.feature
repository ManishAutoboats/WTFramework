Feature: BAT newsletter Registration Feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseUKReg @VuseFRReg3
 Scenario: NewsLetter - Register and sign
   And create a new account
   And user clicks the person icon
   And user clicks on 'myAccountLink.key' link from Person Menu
   And select from myAccount links NewsLetter link
   And assert status of marketing tickbox

  @VuseDEReg2
  Scenario: NewsLetter - Register, place an order and assert Newsletter Subscription
    And create a new account
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'NewsLetter.key' link from Person Menu
    And assert status of Subscription checkbox and verify NL subscription email if checkbox is not selected
    And user clicks the person icon
    And user clicks on 'NewsLetter.key' link from Person Menu
    And assert status of marketing tickbox

  @VuseDEReg2
  Scenario: NewsLetter - Register, place an unsuccessful order proceeded by a successful one and assert Newsletter Subscription
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And select Sofort payment option and click place order
    Then select Cancelled in Payment outcome dropdown and press continue
    And assert URL contains text 'cartURLcontains.key'
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'NewsLetter.key' link from Person Menu
    And assert status of Subscription checkbox and verify NL subscription email if checkbox is not selected
    And user clicks the person icon
    And user clicks on 'NewsLetter.key' link from Person Menu
    And assert status of marketing tickbox

   @VuseITAnonReg2
  Scenario: Create account with consent mobile and newsletter
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields
    And tick signup for newsLetter tick box
    And tick signup for consentMobile tick box
    And user selects the create an account button without Email Verification script
    And users clicks on the 'newsLetterText.key' text link

   @VuseMXReg
  Scenario: NewsLetter - Register Newsletter Subscription and sign
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate DOB field with 'ValidDOB.key'
    And populate signin fields
    And tick signup for newsLetter tick box
    And user selects the create an account button without Email Verification script
    And users clicks on the 'newsLetterText.key' text link
    And assert status of marketing tickbox

  @VuseFRReg2
  Scenario: NewsLetter - Register Newsletter Subscription on registration page
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate DOB field with 'ValidDOB.key'
    And populate signin fields
    And tick signup for newsLetter tick box
    And user selects the create an account button without Email Verification script
    And ensure newsletter sign up button present on page
    And enter newsletter details with random email and assert 'newUserEmail' subscription
    Then assert no Success email on newsletter subscription for user

  @VuseDEReg2
  Scenario: Check newsletter tick box on registration page
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate DOB field with 'ValidDOB.key'
    And populate signin fields
    And tick signup for newsLetter tick box
    And user selects the create an account button without Email Verification script
    Then assert confirm link is displayed

  @VuseDEReg
  Scenario: Email verification for subscribe and unsubscribe of Newsletter
    And create a new account
    And users clicks on the 'NewsLetter.key' text link
    And user select the newsletter subscription checkbox and submit
    Then verify subscription mail is displayed

  @VuseFRReg3
  Scenario: NewsLetter - subscribe Unsubscribe and resubscribe
    When create a new account via api and log in with the account
    And select from myAccount links NewsLetter link
    And unsubscribe the newsletter
    Then assert Newsletter subscribe message displayed
    Then resubscribe the newsletter with same email address
    And select from myAccount links NewsLetter link
    Then assert newsletter option are selected