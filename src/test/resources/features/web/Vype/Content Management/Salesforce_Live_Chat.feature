Feature: BAT Salesforce Live Chat

  Background: 11057 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @regression @live @fr @frlive @ITLive
  Scenario: Live Chat visibility on Home Page
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    #And users clicks on the 'onlineChatLink.key' text link
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears

  @VuseDKReg
  Scenario: Ordering - Checkout - Live Chat CTA
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And search for a Device 'searchTerm.key' and checkout
    And Payment page details confirmed
    And user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears

  #@regression Pop-up not appearing on FE, even though enabled from BE, need more clarification so commenting out - Neha
  Scenario: Vype UK - Live Chat Pop-Up with back-end configured Delay time
    Given user logins Magento Admin and fetch the delay Time for Salesforce Live Chat pop-up
    And user navigates to BAT home page for FE validation
    And assert Live Chat pop-up appears after waiting for the specified delay and assert CTA
    And user clicks on 'signInLink.key' link from Person Menu
    And assert Live Chat pop-up appears after waiting for the specified delay and assert CTA
    Then verify user is able to close the Live Chat pop-up
    And user clicks on 'signInLink.key' link from Person Menu
    And assert pop-up no longer appears on any page during the session