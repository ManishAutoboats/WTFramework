Feature: GLO Footer Items and Content

  Background:Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloItRegression2 @gloItLive @gloPlRegression @gloDeRegression
#  61714 & 74641
  Scenario: verify footer Links and its content
    Then Glo should see footerGeneralLinkText.key and footerGeneralLinkContent.key under GeneralSection of Footer
    And Glo should see footerLegalNoticeLinkText.key and footerLegalNoticeLinkContentUrl.key under LegalSection of Footer
    And Glo should get the success response for the links
    And assert stationary cookies icon and footer links are available

  @gloKzRegression
  Scenario: verify footer Links and its url content
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And Glo should see footerGeneralLinkText.key and footerGeneralLinkContent.key under GeneralSection of Footer
    And Glo should see footerLegalNoticeLinkText.key and footerLegalNoticeLinkContentUrl.key under LegalSection of Footer
    And Glo should get the success response for the links
    And assert stationary cookies icon and footer links are available

  @gloKzRegression
  Scenario: verify footer Links and its url content - Guest User
    Then Glo should not see the footer General Items
    And Glo should get the success response for the links
    And assert stationary cookies icon and footer links are available

#  ToDo: Uncomment the bellow tags once the BUG 338619 is fixed
  @gloItRegression @gloKzRegression @gloDeRegression
  Scenario: verify footer Social Media Links and its url content
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    Then Glo should see footerSocialMediaLinkUrl.key under FollowUs Section of Footer
    And Glo should get the success response for the links

  @gloItRegression2
  Scenario: Conditions of sale
    When users clicks on the 'conditionsofsale.key' text link
    Then url contains 'terms&ConditionsOfSaleURL.key'

  #user story 113338 health warning
  @gloKzRegression
  Scenario: Verify health warning is display on footer
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    Then user navigate to every page and assert health warning is displayed

  @gloPlRegression @gloPlLive
  Scenario: GLO Social media icons are displayed and assert CTAs for each
    Then social media icons are displayed
    Then Glo users click on Facebook link
    And assert glo pageTitle is 'footerFacebookPageTitle.key'
    And navigate back to Home page
    Then Glo users click on Instagram link
    And assert page title contains 'footerInstagramPageTitle.key'
