# new feature
# Tags: optional
Feature: Glo checkout feature -Checkout

  Background: Navigate to BAT Home page
    When user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user click on 'device' menu navigation
    And click first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And click on proceed to checkout for guest user

  @gloPlRegression2
  Scenario: Glo Checkout tests - Guest Checkout
    And select create new account from checkout PopUp
    And create a new account for guest
    And click on basket icon
    And user clicks on the View Basket cta
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And user add address on payment page
    And user choose shipping method
    And Select COD CheckBOX
    Then select place order from Card
    And Order Confirmation page thankYouMessageHeading is displayed

  @gloPlRegression2
  Scenario: Guest Checkout - register - Add Address at checkout - complete checkout
    And select create new account from checkout PopUp
    And create a new account for guest
    And click on basket icon
    And user clicks on the View Basket cta
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And user add address on payment page
    And user select PayU as payment option
   # And select place order from Card
    Then Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed

#  @gloPlRegression  TODO: uncomment once the Bug 446813 fixed
  Scenario: Guest Checkout - register - Add Address - NIP(Tax ID) field validations
    And confirm popup mask present
    And select create new account from checkout PopUp
    And assert page title is 'registrationPageTitle.key'
    When create a new account
    And click on basket icon
    And click on proceed to checkout for guest user
    And add two free gifts to cart and assert user is allowed to checkout
    When user attempts to add shipping address as with following NIP Then assert error message
      | pattern          | message             |
      | length-9         | nipErrorMessage.key |
      | length-11        | nipErrorMessage.key |
      | 123-45-67-89-123 | nipErrorMessage.key |
      | 12345#####       | nipErrorMessage.key |
    When user add the new shipping address with NIP-length-10
    And user choose shipping method
    And Select COD CheckBOX
    And select place order from Card
    Then Order Confirmation page thankYouMessageHeading is displayed