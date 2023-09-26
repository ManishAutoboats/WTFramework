Feature: VELO FAQ page validations - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option

 @RegVeloZA_Mobile
  Scenario: Velo FAQ validation za
   When user click 'contactHeading' from mobile sandwich menu
   And assert text of 'FAQPageGuarantee.key' is displayed
   Then assert that page title is 'FAQPageTitle.key'
   And assert text of 'FAQ.key' is displayed
   And assert text of 'FAQQuestion1' is displayed
   And assert text of 'FAQQuestion2' is displayed
   And assert text of 'FAQQuestion3' is displayed
   And assert text of 'FAQQuestion4' is displayed
   And assert text of 'FAQQuestion5' is displayed
   And assert text of 'FAQQuestion6' is displayed

  @RegVeloZA_Mobile
  Scenario: About Velo Page
    When user click 'aboutVelo' from mobile sandwich menu
    Then url contains 'aboutVeloUrl.key'

  @RegVeloZA_Mobile
  Scenario: Validate blog post-Velo
    When user click 'blogheading' from mobile sandwich menu
    Then url contains 'blog.key'


  @RegVeloZA_Mobile
  Scenario: Check Youtube social Media link and its Navigation page
    When velo users click on YouTube link
    Then url contains 'youtube.key'


  @RegVeloZA_Mobile
  Scenario: Check Instagram social Media link and its Navigation page
    When velo users click on Instagram link
    Then url contains 'instagram.key'

  @RegVeloZA_Mobile
  Scenario: Check Twitter social Media link and its Navigation page
    When velo users click on Twitter link
    Then url contains 'twiter.key'

  @RegVeloZA_Mobile
  Scenario: Check Facebook social Media link and its Navigation page
    When velo users click on Facebook link
    Then url contains 'facebook.key'

  @RegVeloZA_Mobile
  Scenario: verify invalid url redirect to 404 error page
    And user navigates to URL 'error404Url.key'
    Then user get error 404 page displayed

  @RegVeloZA_Mobile
  Scenario: Verify Phone number and email address is displaying in the footer
    And user see the phone number in footer
    And user see the email in footer


  @RegVeloZA_Mobile
  Scenario: Velo NewsLetter Validation in every page
    When user click 'contactHeading' from mobile sandwich menu
    When user click on signup button
    And user click 'blogheading' from mobile sandwich menu
    When user click on signup button
    Then user click 'aboutVelo' from mobile sandwich menu
    When user click on signup button
    And user navigates to PLP page
    When user click on signup button
