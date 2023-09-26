#@VuseUKReg - Commenting out since the enhancements have been moved to RT77, will uncomment once GB regression starts
Feature: Vuse Care Landing Page and Links
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: Vuse Care Links and CTA as a guest user
    And user hovers over on the About Vype header link
    Then users clicks on the Vuse Care link from About Vype Menu
    And user closes the alert if present
    And url contains 'VuseCareUrlContain.key'
    And assert text of 'VuseCarePageTitle.key' is displayed
    Then users clicks on the 'vuseCareLinkText.key' link
    And url contains 'VuseCareUrlContain.key'
    And assert text of 'VuseCarePageTitle.key' is displayed

  Scenario: Vuse Care Link and CTA as a logged-in user
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks the person icon
    Then user clicks on 'vuseCareLinkText.key' link from Person Menu
    Then user should be redirected to vuseCareTitle.key page

  Scenario: Vuse Care Landing Page and Fields Assertions
    Then users clicks on the 'vuseCareLinkText.key' link
    And user closes the alert if present
    Then assert Register Now link is displayed
    And assert Create Profile link is displayed
    And assert FAQs section in accordion style

  Scenario: Vuse Care Registration as a Guest User
    Then users clicks on the 'vuseCareLinkText.key' link
    And user closes the alert if present
    Then user clicks on Register Now link from Vuse Care Registration block
    And assert text of 'loginpageTopTextHeading.key' is displayed
    And user clicks create an account button
    And populate personal informations section fields
    And populate address fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    Then delete the account via api

  Scenario: Vuse Care Create Profile as a Guest User
    Then users clicks on the 'vuseCareLinkText.key' link
    And user closes the alert if present
    Then user clicks on Create Profile link Vuse Care Profile block
    And assert text of 'loginpageTopTextHeading.key' is displayed
    And user clicks create an account button
    And populate personal informations section fields
    And populate address fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    Then delete the account via api

  Scenario: Navigation to My Account from Vuse Care page for a logged-in user
    Then users clicks on the 'vuseCareLinkText.key' link
    And user closes the alert if present
    Then user clicks on Register Now link from Vuse Care Registration block
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And url contains 'customerAccountURL.key'

  Scenario: Link to Vuse Care
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And url contains 'customerAccountURL.key'
    And user click on 'Vuse Care' option
    Then Vuse should be redirected to vuseCareTitle.key page
    And assert terms and condition and subscribe newsletter checkox is displayed
    And user click on 'Terms and condition' option
    Then Vuse should be redirected to termsAndConditionPage.key page

  Scenario: Extended warranty - Registered user confirmation page and opt-out feature with marketing consent
    And user clicks the person icon
    And create a new account with mark all marketing consent checks selected
    And user click on 'Vuse Care' option
    Then Vuse should be redirected to vuseCareTitle.key page
    And verify subscribe checkbox is not displayed
    And user try to register for vuse care
    Then assert user is registered successfully
    And assert opt out for vuse care option and confirm CTA is displayed
    Then user opt out for vuse care and click on confirm CTA
    And assert confirmation option with yes and no is displayed
    And user click on 'Yes' option
    And assert user is not registered for vuse care anymore

  Scenario: Extended warranty - Registered user confirmation page and opt-out feature with no marketing consent
    And user clicks the person icon
    And create a new account with no marketing consent
    And user clicks the person icon
    And user click on 'Vuse Care' option
    Then Vuse should be redirected to vuseCareTitle.key page
    And user try to register for vuse care
    Then assert user is registered successfully
    And assert opt out for vuse care option and confirm CTA is displayed
    Then user opt out for vuse care and click on confirm CTA
    And assert confirmation option with yes and no is displayed
    And user click on 'Yes' option
    And assert user is not registered for vuse care anymore

  Scenario: My Account - Vuse Care page assertions
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user click on 'Vuse Care' option
    Then user should be redirected to vuseCareTitle.key page
    And assert text of 'vuseCareDescription.key' is displayed
    And assert terms and condition and subscribe newsletter checkox is displayed
    Then user clicks on Register for Vuse Care libk without accepting TCs
    And assert error message 'TCsErrorMessageText.key' is displayed
    And user click on 'Terms and condition' option
    Then user should be redirected to termsAndConditionPage.key page

