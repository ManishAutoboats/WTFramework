Feature: 14782 BAT Age module feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from new cookie consent popup
    And assert entry age module is presented to user


  @VuseDEReg2 @VuseDELive @VuseUKReg2 @VuseUKLive2 @VuseITLive @VuseMXReg @VuseMXLive
  Scenario: user selects not over 18 age confirmation
    And user selects not over 18 age confirmation option, confirm text and google redirection

  @VuseDEReg2 @VuseDELive @VuseUKReg2 @VuseUKLive2 @VuseMXReg @VuseMXLive @VuseITAnonReg2
  Scenario: user selects over 18 age confirmation
    And user selects over 18 age confirmation option
    And age confirmation box is no longer displayed

  @VuseUKReg2
  Scenario: Age verification Logo added to Age gate popup and Footer
   Then assert Age verification logo is displayed on Age Gate pop-up
   And user selects over 18 age confirmation option
   Then assert Age verification logo is displayed on home page Footer

  @VuseITAnonReg3
  Scenario: user selects not over 18 age confirmation and back to homepage again
    Given user selects not over 18 age confirmation option, confirm text and google redirection
    And user navigates to URL 'homeUrl.key'
    And user selects over 18 age confirmation option
    Then age confirmation box is no longer displayed
