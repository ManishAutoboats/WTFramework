#This is the Cartridge Pop-Up Checkout feature file
@VuseMXReg
Feature: Cartridge Pop Up on navigating to Checkout Page

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket

  Scenario: Vuse MX - Cartridge pop-up on Checkout with Devices
    Then search for a Device 'searchDeviceTerm.key' and checkout
    And assert Cartridge pop-up is displayed on Checkout Page
    Then user clicks on See Cartridges button from the pop-up and navigated to Cartuchos PLP

  Scenario: Vuse MX - Cartridge pop-up - Continue with Payment CTA
    Then search for a Device 'searchDeviceTerm.key' and checkout
    And assert Cartridge pop-up is displayed on Checkout Page
    Then user clicks on Continue with Payment button and assert user remains on Checkout page with pop-up closed
    And click on the logo
    And click on basket icon
    And click on proceed to checkout button
    And assert Cartridge pop-up is not displayed on Checkout Page

  Scenario: Vuse MX - Cartridge pop-up - Close CTA
    Then search for a Device 'searchDeviceTerm.key' and checkout
    And assert Cartridge pop-up is displayed on Checkout Page
    Then user clicks on Close button from the pop-up and assert CTA
    And click on the logo
    And click on basket icon
    And click on proceed to checkout button
    And assert Cartridge pop-up is not displayed on Checkout Page

    #Automate issue 980895(this issue need update config,if no drop pod section please check the config on admin first)
  Scenario: Vuse MX - verify drop pod section works fine on checkout page
    When search and checkout product "searchTerm.key"
    And Payment page details confirmed
    Then assert text of 'dropThePodSectionText.key' is displayed
    And click drop the pod link
    And wait for the page to fully load
    And switch to window "1"
    Then assert URL contains text 'dropThePodUrl.key'
    And assert text of 'dropThePodPageText.key' is displayed
