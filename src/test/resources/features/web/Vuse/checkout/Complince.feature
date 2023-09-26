Feature: BAT Vuse Compliance Age Verification
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  #@VuseZAReg  Open bug 847813
  Scenario: checkout with new account created without SAID Number
    When create a new account via api and log in with the account
    And user navigates to PLP page and adds a product to basket
    And click on proceed to checkout button
    And assert Checkout Age Verification warning label
    And assert URL contains text 'MyAccountURL.key'
    And users clicks on the 'dashboardEditLinkText.key' text link
    And user enters valid SA ID number in Edit Details and save
    And Customer makes a home delivery purchase with "Sid Secure EFT"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed


  @VuseZAReg2
  Scenario: Existing account with SAID Number-SA ID is not blank
    When create a new account
    And assert URL contains text 'MyAccountURL.key'
    And users clicks on the 'dashboardEditLinkText.key' text link
    And Assert SA ID number on the Edit deatils page

@VuseITLive
  Scenario: checkout with new account created without Age verification
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user navigates to PLP page and adds a product to basket
    And click on proceed to checkout button
    And user Clicks On Age Verify Button
    And assert URL contains text 'AgeVerificationURL.key'
    Then Assert photo size disclaimer info displayed
