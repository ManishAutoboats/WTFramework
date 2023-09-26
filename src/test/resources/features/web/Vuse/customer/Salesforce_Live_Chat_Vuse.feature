Feature: BAT Salesforce Live Chat

  Background: 11057 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDEReg @VuseUKLive
  Scenario: Ordering - Checkout - Live Chat CTA
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And search for a Device 'searchTerm.key' and checkout
    And Payment page details confirmed
    And assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears

    @VuseFRReg3 @VuseFRLive @VuseUKReg2 @VuseUKLive2 @COReg @CoLive
  Scenario: Live Chat visibility on Home Page
    Then assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears

  @VuseDEReg @VuseZAReg
  Scenario: Ordering Live Chat CTA
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears

  @VuseUKReg2 #(steps commented as we will have only Agent offline in UAT)
  Scenario: Vuse UK- Guest user live chat
    And assert CTA for Live chat icon at the bottom right of the page
    #When user clicks on the Live chat icon at the bottom right of the page
    #Then assert Chat pop-up appears with hamburger option displayed
    #And user click on chat hamburger
    #And click on speak to agent option if available
    #And assert agent is asking the user first name

  #@VuseUKReg2 (Commented out as credential need to be configured and in pending state)
  Scenario: Vuse UK- Logged in user live chat
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then fetch First and Last Names of the logged-in user
    And assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert Chat pop-up appears with hamburger option displayed
    And user click on chat hamburger
    And click on speak to agent option if available
    And assert username is displayed with expected text