#Free Device Trial - FDT - prerequisite - feature to be enabled on admin
#Link from menu for landing page implemented now
Feature: Free Device Trial - Navigation
  Background: Navigate to Free Device Trial Landing page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #@FDT #@VuseUKLive  The test should be enabled again after #789136 get fixed.
  Scenario: Free Device Trial - Links to Device Trial in Navigation
    When user hovers over on the About Vype header link
    Then users clicks on the 'deviceTrialLink.key' text link
    And user closes the alert if present
    And assert URL contains text 'deviceTrialURL.key'
    And user to see Brief info on Trial product
    When user hovers over on the SHOP header link
    Then users clicks on the 'deviceTrialLink.key' text link
    And assert URL contains text 'deviceTrialURL.key'
    And user to see Brief info on Trial product
    Then click on the logo
    When user hovers over on the SHOP header link
    Then user clicks on Device Trial link from ePod sub-menu
    And assert URL contains text 'deviceTrialURL.key'
    And user to see Brief info on Trial product
    Then click on the logo
    When user hovers over on the SHOP header link
    Then user clicks on Device Trial link from ePen sub-menu
    And assert URL contains text 'deviceTrialURL.key'
    And user to see Brief info on Trial product




