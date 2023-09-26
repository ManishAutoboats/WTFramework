Feature: 16513 BAT header Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @regression @smokeLite @live @IEReg @VuseZAReg2 @VuseDKReg @VuseDKLive @VuseZALive
  Scenario: Browse to main home page and assert header elements displayed as expected
    And logo is displayed
    And header class is displayed
    And person icon is displayed
    And favorite icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert text 'myBasketText.key' on basket icon

  @theme @header @regression @smokeLite @live @IEReg @dk @dklive @VuseZAReg2 @VuseDKReg
  Scenario: Check default delivery message is correct
    And should see Delivery message banner near the header

  @dk @dklive @VuseDKReg @VuseDKLive
  Scenario: Verify Header Links and CTAs
    Then users clicks on the 'devicesLinkHeader.key' text link
    Then user navigates to PLP page for Devices link
    And url contains 'devicesUrl.key'
    Then users clicks on the 'capsLinkHeader.key' text link
    Then user navigates to PLP page for Caps link
    And url contains 'eLiquidsUrl.key'

  @delive @de
  Scenario: Header URLs - Assert Internal Links Redirects
    And assert internal redirect of header links URLs with success status code
    And assert internal redirect of Devices Sub-links URLs with success status code
    And assert internal redirect of Flavours Sub-links URLs with success status code