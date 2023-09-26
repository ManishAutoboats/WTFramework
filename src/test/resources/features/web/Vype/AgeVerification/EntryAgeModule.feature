Feature: 14782 BAT Age module feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from new cookie consent popup
    And assert entry age module is presented to user

  @sprint1 @182238 @regression @live @MXReg2 @NonCaptchaLive @nlSmoke @dklive  @dk @NLlive @MXSmoke @de @desmoke @nl @ITReg @ITLive @delive @IEReg2 @IElive @COReg @VuseITAnonReg2 #VuseZA remove as SA google not handle in browserstack
  Scenario: user selects not over 18 age confirmation
    And user selects not over 18 age confirmation option, confirm text and google redirection

  @smokeLite @sprint1 @182238 @regression @live @MXReg2 @NonCaptchaLive @nlSmoke @dklive  @dk @NLlive @MXSmoke @de @desmoke @nl @ITReg @ITLive @delive @IEReg2 @IElive @COReg @VuseFRReg3 @VuseITAnonReg2
  Scenario: user selects over 18 age confirmation
    And user selects over 18 age confirmation option
    And age confirmation box is no longer displayed

  @nl @VuseUKReg2 @VuseZAReg2  @VuseFRReg2 @VuseITAnonReg2
  Scenario: Pre site landing page - user should not be able to view any content of site until confirming their age
    Then assert logo is not displayed
    When user selects over 18 age confirmation option
    Then age confirmation box is no longer displayed
    And logo is displayed

  @VuseZAReg2
  Scenario: Verify Age verification popup parameters
    Then verify age module have date, month, year dropdown menu
    And assert under 18 and over 18 button displayed