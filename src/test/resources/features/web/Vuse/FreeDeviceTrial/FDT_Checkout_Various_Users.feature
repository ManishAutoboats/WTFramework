#Free Device Trial - FDT - prerequisite - feature to be enabled on admin
#below Scenario is functional & supported only for Vype UK atm
#Link implemented for navigation to landing page, removed hardcoded URL
Feature: Free Device Trial - Checkout
  Background: Navigate to Free Device Trial Landing page
#    Given user navigates to BAT home page
#    And select allow all from cookie popup and select over 18 age confirmation option
#    When user hovers over on the SHOP header link
#    Then users clicks on the 'deviceTrialLink.key' text link
#    Then user closes the alert if present
    Given user navigates to Free Device Trial landing page
    And select allow all from cookie popup and select over 18 age confirmation option

  @FDT
  Scenario: Trial Checkout - User registering on the flow and placing Trial order
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
    And Device Trial Consent Checkbox is displayed
    And Device Trail payment method details present
    And User agrees the Trial payment agreement
    Then User places a trial order with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And all sections of the order details are correct

  @FDT
  Scenario:Trial Checkout - Registered New User doing a Trial Checkout as first Order
    And create a new account
    And user navigates back to Free Device Trial landing page
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed for the second time
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    And Device Trial discount info is displayed on checkout page
    And User agrees the Trial payment agreement
    Then User places a trial order with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And all sections of the order details are correct

  @FDT
  Scenario:Trial Checkout - Existing Trial user trying to place a second Trial when First Trial is still active
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
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
    And all sections of the order details are correct
    And user navigates back to Free Device Trial landing page
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed for the second time
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    And confirm that the user is notified about the First time Trial only
    #Below steps to happen when 'first time purchasing validation' is disabled
    #And confirm that the user is notified about the Two overlapping Trial not possible

  @FDT
  Scenario:Trial Checkout - Existing user trying to place a Trial order after placing an payed order.
    And create a new account
    And Customer makes a home delivery purchase with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And user navigates back to Free Device Trial landing page
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    #Below steps to happen when 'first time purchasing validation' is disabled
    #And Device Trial discount info is displayed on checkout page
    #And User agrees the Trial payment agreement
    #Then User places a trial order with "mastercard"
    #And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    #And assert text of 'yourOrderNumberText.key' is displayed
    #Below steps to happen when 'first time purchasing validation' is enabled
    And confirm that the user is notified about the First time Trial only

  @FDT
  Scenario: Trial Checkout - Trial & Non Trial items on basket together to be prevented
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
    And user navigates back to Free Device Trial landing page
    And user adds a non Trial product into basket
    Then confirm that the user is notified about the Mixed basket of Trial & Non Trial checkout not possible

  @FDT
  Scenario:Trial Checkout - Two Trial items on basket to be prevented
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
    And user navigates back to Free Device Trial landing page
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed for the second time
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    And confirm that the user is notified about the One Trial per transaction
