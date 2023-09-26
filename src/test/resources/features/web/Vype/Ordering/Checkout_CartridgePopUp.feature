#This is the Cartridge Pop-Up Checkout feature file
@MXReg
Feature: Cartridge Pop Up on navigating to Checkout Page

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket

  Scenario: Vype MX - Cartridge pop-up on Checkout with Devices
    Then search for a Device 'searchDeviceTerm.key' and checkout
    And assert Cartridge pop-up is displayed on Checkout Page
    Then user clicks on See Cartridges button from the pop-up and navigated to Cartuchos PLP

  Scenario: Vype MX - Cartridge pop-up - Continue with Payment CTA
    Then search for a Device 'searchDeviceTerm.key' and checkout
    And assert Cartridge pop-up is displayed on Checkout Page
    Then user clicks on Continue with Payment button and assert user remains on Checkout page with pop-up closed
    And click on the logo
    And click on basket icon
    And click on proceed to checkout button
    And assert Cartridge pop-up is not displayed on Checkout Page

  Scenario: Vype MX - Cartridge pop-up - Close CTA
    Then search for a Device 'searchDeviceTerm.key' and checkout
    And assert Cartridge pop-up is displayed on Checkout Page
    Then user clicks on Close button from the pop-up and assert CTA
    And click on the logo
    And click on basket icon
    And click on proceed to checkout button
    And assert Cartridge pop-up is not displayed on Checkout Page