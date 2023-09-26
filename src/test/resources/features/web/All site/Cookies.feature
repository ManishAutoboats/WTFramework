Feature: BAT Cookie feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page


  @gloItLive @gloItSmoke @gloDeSmoke @gloDeLive @gloItRegression2 @gloDeRegression2 @gloItLive @gloKzRegression  @NonCaptchaLive  @fr @dklive @dk @NLlive @nl @frlive @IEReg @ITLive @ITSmoke @MXReg2 @VuseFRReg3 @veloDEReg @VuseMXReg @VuseZAReg @LyftRegression
  Scenario: Presence of cookie policy banner, link and buttons
    Then oneTrustCookies popup is displayed to user
    And oneTrustCookies link and buttons is displayed to user

 #@gloItLive @gloItSmoke @gloDeSmoke @gloItRegression2 @gloDeRegression2 @gloItLive @gloKzRegression @NonCaptchaLive  @fr @dklive @dk @NLlive @nl @frlive @IEReg @MXReg @VuseFRReg3 @@veloDEReg @VuseMXReg @VuseZAReg @LyftRegression  #@gloDeLive commented because of bug 516666
  Scenario: Presence of cookie banner until choice is made
    When user navigates to the Blog page
    Then oneTrustCookies popup is displayed to user


  @gloItLive @gloItSmoke @gloDeSmoke @gloDeLive @gloItRegression2 @gloDeRegression2 @gloItLive @gloKzRegression @NonCaptchaLive  @fr @dklive @dk @NLlive @nl @frlive @IEReg @ITLive @ITSmoke @MXReg2 @VuseFRReg3 @veloDEReg @VuseMXReg
  Scenario: Presence of Privacy preference center and its all elements
    When user click on manage cookies button
    Then ensure all oneTrust privacy center elements are present


  @gloItLive @gloItSmoke @gloDeSmoke @gloDeLive @gloItRegression2 @gloDeRegression2 @gloItLive @gloKzRegression  @NonCaptchaLive  @fr @dklive @dk @NLlive @nl @frlive @IEReg @ITLive @ITSmoke @MXReg2 @VuseFRReg3 @veloDEReg @VuseMXReg
  Scenario: All cookie types are turned OFF by default except strictly necessary
    When user click on manage cookies button
    Then ensure all cookies are set to off by default except strictly necessary

    #ToDo
#  @gloItLive @gloItSmoke @gloDeSmoke @gloDeLive @gloItRegression @gloDeRegression @gloItLive @gloKzRegression @MXReg @NonCaptchaLive  @fr @dklive @dk @NLlive @nl @frlive @IEReg @ITLive @ITSmoke
#  Scenario: 'Accept All' button enables all type of cookies in Privacy center for user
#    When user click on manage cookies button
#    And user clicks on oneTrustCookies acceptAll button from privacy center
#    And user clicks on oneTrust footer link
#    Then ensure all cookies are set to on
#
 # @veloDEReg @VuseZAReg @VuseFRReg3 # @frlive @frSmoke @gloDeLive
  Scenario: 'Accept All' button enables all type of cookies in Privacy center for user - floating icon
    When user click on manage cookies button
    And user clicks on oneTrustCookies acceptAll button from privacy center
    And user clicks on oneTrust floating icon
    Then ensure all cookies are set to on

#  Scenario: Presence of cookies information link under cookie category
#    When user click on manage cookies button
#    Then ensure all cookies information link are present under cookies category
  
#  Scenario: Redirection to privacy notice page
#    When user clicks on privacy cookie link on oneTrustcookies banner
#    Then ensure user lands on privacy notice page

 # @gloItLive @gloItSmoke @gloDeSmoke @gloDeLive @gloItRegression2 @gloDeRegression2 @gloKzRegression @MXSmoke @NonCaptchaLive  @fr @dklive @dk @NLlive @nl @frlive @IEReg @ITLive @ITSmoke @ITReg2 @VuseFRReg3  @VuseZAReg #@MXReg
  Scenario: Stationary Cookie icon and footer link available
    And select allow all from cookie popup
    Then assert stationary cookies icon and footer links are available

  @NLlive @nl @nlSmoke
  Scenario: Presence of cookies and privacy PDF link on respective pages
    And select allow all from cookie popup
    When users clicks on the 'cookiePolicy.key' text link
    Then assert PDF link is correct on policy page
    And users clicks on the 'privacyPolicy.key' text link
    Then assert PDF link is correct on policy page

 # @frlive @frSmoke @ITLive @ITSmoke @ITReg @fr
  Scenario: verify cookies presence and its expiry date set to expected value
    And select allow all from cookie popup
    And verify cookie presence and expiry

  @dk @LyftDKReg
  Scenario: verify Cookies Description and Cookie Settings link CTA
    When user click on manage cookies button
    And assert description is displayed for each cookie type
    And assert Cookie Settings link is displayed for each cookie type
    And assert Cookie Settings link CTA for each cookie type

  @regression @live @VuseUKLive2
  Scenario: verify Stationary Cookie icon and Cookie Settings link CTA in Footer
    And assert stationary cookie icon is not displayed on home page
    And assert Cookie settings link and CTA in Footer

  @VuseMXReg
  Scenario: 980602 - verify Cookie Settings link CTA in Footer
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert Cookie settings link and CTA in Footer