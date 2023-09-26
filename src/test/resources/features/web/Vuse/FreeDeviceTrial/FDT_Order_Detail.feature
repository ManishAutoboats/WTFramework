#This is place holder to cover Scenarios related to order history and per order details.
Feature: Free Device Trial - Checkout
  Background: Navigate to Free Device Trial Landing page
#    Given user navigates to BAT home page
#    And select allow all from cookie popup and select over 18 age confirmation option
#    When user hovers over on the SHOP header link
#    Then users clicks on the 'deviceTrialLink.key' text link
#      Then user closes the alert if present
      Given user navigates to Free Device Trial landing page
      And select allow all from cookie popup and select over 18 age confirmation option

@FDT
Scenario: Placing Trial Order & Validating trial details in order details page
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    And confirm that the user is notified they must be registered
    And user closes the alert if present
    And user selects the create an account button shown on the login page
    And create a new account for Trial
    And upon confirmation of account creation email user to be redirected to Trial's checkout page
    And Device Trial discount info is displayed on checkout page
    And User agrees the Trial payment agreement
    Then User places a trial order with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And assert if recent trial order has expected trial start date and end date
    And assert if recent trial order has expected trial Duration
    And assert if recent trial order has expected trial price details
    And assert if recent trial order has expected order information

@FDT
Scenario: Trial Order checkout to save payment card by default and not to be seen on My accounts
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    And confirm that the user is notified they must be registered
    And user closes the alert if present
    And user selects the create an account button shown on the login page
    And create a new account for Trial
    And upon confirmation of account creation email user to be redirected to Trial's checkout page
    And Device Trial discount info is displayed on checkout page
    And User to see card for use after the trial section is present
    And User agrees the Trial payment agreement
    Then User places a trial order with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And user hovers over the person icon
    And users clicks on the 'StorePayments.key' link
    And assert text of 'storedPaymentMethodsTitle.key' is displayed
    Then assert saved card is not present after trial order