#Free Device Trial - FDT - prerequisite - feature to be enabled on admin
#below Scenario is functional & supported only for Vype UK atm
#No link from menu for landing page hence we just use hardcoded url to land for now on the Given statement
Feature: Free Device Trial - Checkout
  Background: Navigate to Free Device Trial Landing page
    Given user navigates to Free Device Trial landing page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then user selects 'No' option from FDT pop-up for a user with first order

  @FDT_FR
  Scenario: Trial Product - Remove from Basket Validation
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user adds minimum Cartridge Trial allowed
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user clicks the checkout button
    And confirm that the user is notified they must be registered
    And user closes the alert if present
    And user selects the create an account button shown on the login page
    And create a new account for Trial
    And user selects proceed to checkout from cart page
    #And Device Trial discount info is displayed on checkout page
    And user navigates back to Free Device Trial landing page
    Then user selects 'No' option from FDT pop-up for a user with first order
    And empty Trial product from basket
    And click on basket icon
    And empty basket
    And basket is empty message is presented

  @FDT_FR
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
    And user selects proceed to checkout from cart page
    #And Device Trial discount info is displayed on checkout page
    And user navigates back to Free Device Trial landing page
    Then user selects 'No' option from FDT pop-up for a user with first order
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And div cart summary pane is displayed
    And user clicks the person icon
    And users clicks on the 'myAccountLink.key' text link
    And users clicks on the 'logoutText.key' text link
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
