Feature: Loyalty feature - Store credit - GLO IT - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'

  Scenario: Verify Insiders Club Link in Header
    And user navigates GloIt Homepage
    When user should be able to see Insiders Club menu option in header
    And users clicks on the 'insiderClubTopNavigation.key' text link
    Then user sent to Loyalty Insiders Club landing page
    And assert text of 'signUpLoyalty.key' is displayed
    And assert text of 'buyLoyalty.key' is displayed
    And assert text of 'logInLoyalty.key' is displayed

  Scenario: Verify Insiders Club Terms and Condition Link in Footer
    When user should be able to see Insiders Club T&C option in footer
    And users clicks on the 'insiderClubFooterNavigation.key' text link
    Then user sent to Loyalty Insiders Club T&C page
    And assert text of 'promoterSubTitle.key' is displayed
    And assert text of 'durationSubTitle.key' is displayed
    And assert text of 'scopeSubTitle.key' is displayed
