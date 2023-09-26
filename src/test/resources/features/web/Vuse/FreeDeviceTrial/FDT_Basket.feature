#Free Device Trial - FDT - prerequisite - feature to be enabled on admin
#below Scenario is functional & supported only for Vype UK atm
#Link implemented for navigation to landing page, removed hardcoded URL

Feature: Free Device Trial - Basket
  Background: Navigate to Free Device Trial Landing page
#    Given user navigates to BAT home page
#    And select allow all from cookie popup and select over 18 age confirmation option
#    When user hovers over on the SHOP header link
#    Then users clicks on the 'deviceTrialLink.key' text link
#    Then user closes the alert if present
    Given user navigates to Free Device Trial landing page
    And select allow all from cookie popup and select over 18 age confirmation option

  @FDT
  Scenario: Trial Product - Remove from Basket Validation
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    And user closes the alert if present
    And confirm that the user is notified they must be registered
    And user closes the alert if present
    And user selects the create an account button shown on the login page
    And create a new account for Trial
    And upon confirmation of account creation email user to be redirected to Trial's checkout page
    And Device Trial discount info is displayed on checkout page
    And user navigates back to Free Device Trial landing page
    And empty Trial product from basket
    And click on basket icon
    And empty basket
    And basket is empty message is presented

  @FDT
  Scenario: Trial Product - Retain in Basket on new session validation
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
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And div cart summary pane is displayed
    And user clicks the person icon
    And users clicks on the 'signOutLink.key' text link
    And click on the logo
    And user clicks the person icon
    And click on basket icon
    And basket is empty message is presented
    And click on the logo
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user re login
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And div cart summary pane is displayed
