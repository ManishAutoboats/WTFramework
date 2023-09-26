#This is the BAT Navigation feature
@smoke @21398
Feature: BAT navigation feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    #And click hamburger menu

  #@sprint3 @Navigation @regression @live
  #Scenario: 21398 Guest, opens hamburger menu and closes
  #  And cycle through menu options and ensure the following is present 'eCigarette Devices' 'eLiquid Flavours' 'Bundles' 'Accessories' 'Vype x HoH' 'Store Locator' 'Vype Blog' 'My account'
  #  Then close the hamburger menu

  #@sprint3 @Navigation ** IN DEVELOPMENT
  #Scenario: Guest, opens hamburger selects Store Locator
  #  And cycle through menu options and ensure the following is present 'Store Locator' and select it
  #  And testingBreakpoint
  #  Then close the hamburger menu

  @live @nav @regression
  Scenario Outline: 21398 Guest, opens hamburger menu select main hamburger links
    And user hovers over on the About Vype header link
    And users clicks on the 'bundlesHeaderLinkText.key' text link
    And url contains 'bundlesUrlText.key'
    And user closes the alert if present
  	And user hovers over on the SHOP header link
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Examples:
      | LinkToClick                 | UrlToContain              |
      | DeviceCategoryText.key      | AllDevicesUrlText.key     |
      | flavourHeaderLinkText.key   | eLiquidesUrlText.key      |
      | AccessoriesLinkText.key     | AllAccessoriesUrlText.key |

  @smokeLite @34246
  Scenario Outline: 21398 Guest UAT, opens hamburger menu select main hamburger links
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Examples:
      | LinkToClick         | UrlToContain            |
      | Devices             | e-cigarette-devices     |
      | Flavours            | e-liquids               |
      | Bundles             | vype-bundles            |
      | Accessories         | e-cigarette-accessories |

  @smokeLite @34246
  Scenario: Guest UAT, opens the More menu
    And user hovers over on the SHOP header link
    And assert css menu reference is displayed to user
    And click the Bundles link

  Scenario: Guest UAT, open and close the More menu
    And user hovers over on the More header link
    And assert css menu reference is displayed to user
    And close the more menu

  @sprint6 @20631
  Scenario: 21398 Guest - opens House of Holland page from account menu
    And users clicks on the 'Vype x HoH' text link
    And url contains 'house-of-holland'
    And assert text of 'House of Holland.' is displayed
    And assert text of 'These Limited Edition Vype x House of Holland items are only available for purchase through our Vype Care team' is displayed








