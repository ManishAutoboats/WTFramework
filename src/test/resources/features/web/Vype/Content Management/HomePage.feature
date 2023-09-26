Feature: BAT homepage feature - page scraping for elements

  Background: 11057 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: 11057 Browse to main home page and assert expected elements
    And assert that page title is 'homePageTitle.key'
    And logo is displayed
    And header Class is displayed
    And person icon is displayed
    And search icon is displayed
    And should see Delivery message banner near the header
    And footer div is present and displayed
    And footer sublinks displayed
    And social media icons displayed

  @smokeLite @regression @NonCaptchaLive @MXReg2 @VuseZAReg2 #@ITReg
Scenario: Check Instagram social Media link and its Navigation page
	When Vype users click on Instagram link 
	Then assert cart pageTitle is 'homepageInstgramTitle.key'

  @smokeLite @regression @NonCaptchaLive @MXReg2 @VuseZAReg2 #@ITReg
Scenario: Check Facebook social Media link and its Navigation page 
	When Vype users click on Facebook link
	Then assert cart pageTitle is 'homepageFacebookTitle.key'

  #@dk @dklive - Not applicable as of now for Vype DK - Contact Link removed
  Scenario: Privacy Policy Text and Link CTA on Contact Us page
    Then users clicks on Contact link from the header menu and assert url contains 'contactPageUrl.key'
    Then assert text of 'privacyPolicyText.key' is displayed
    And assert 'privatePolicy.key' text link and navigation on CTA

  @de @delive
  Scenario: Newsletter pop-up - for new customers
    And assert text of SignUp popup Header 'SignUpPopupTextNewToVype.key' is displayed
    Then user clicks on Join Now button
    And verify user is directed to SetUp An Account page
    And assert URL contains text 'URLForAccountRegistration.key'

  @de @delive
  Scenario: Newsletter pop-up - Cross Button for Sign Up pop-up
    And user clicks on Close button from the pop-up and assert pop-up is closed

  @de @delive
  Scenario: Newsletter pop-up - Registered User must not see the Sign Up pop-up
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then asset sign Up pop-up is not displayed

  @MXReg2
  Scenario: Change URL linked for BOX ICON
    And assert url of the page is 'TrackMyOrderUrl.key' when users click on Box icon

