#This is the customer Account Dashboard feature
Feature: 13848 BAT Account management - My Account dash board tests

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
   	Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'

@de @regression @accountDashboard @MXReg @NLlive  @nl @fr @nlSmoke @nl @ITSmoke @ITReg @ITLive @IEReg @COReg @VuseZAReg @VuseDKReg
Scenario: My Account Dashboard displays username which is also a link
    Then assert text of 'DashboardTitle.key' is displayed

@accountDashboard @MXReg @NLlive  @nl @fr @nlSmoke @nl @ITSmoke @ITReg @ITLive @IEReg @COReg @VuseZAReg
  Scenario: My Account - Edit Account
    Then assert text of 'dashboardEditText.key' is displayed
    Then users clicks on the 'dashboardEditLinkText.key' text link
    Then assert text of 'editAccountInfoText.key' is displayed
    And assert first name input is present
    And assert last name input is present
    And assert DOB input is present
    And assert save button is present
    And assert cancel button is present

@smokeLite @dklive @dk @dksmoke @accountDashboard @MXReg @NLlive  @nl @fr @nlSmoke @nl @ITSmoke @ITReg @ITLive @IEReg @COReg @VuseDKReg @VuseDKLive
  Scenario: My Account Dashboard page contents information expected
  Then assert text of 'dashboardEditText.key' is displayed
    Then assert text of 'myAccountDashboardText.key' is displayed
    Then assert text of 'firstNameText.key' is displayed
    Then assert text of 'lastNameText.key' is displayed
    Then assert text of 'emailText.key' is displayed
    Then assert text of 'dateOfBirthText.key' is displayed

  @regression
  Scenario: My Account Dashboard Page Contents
    Then assert text of 'myAccountDashboardText.key' is displayed
    Then assert text of 'firstNameText.key' is displayed
    Then assert text of 'lastNameText.key' is displayed
    Then assert text of 'emailText.key' is displayed
    Then assert text of 'dateOfBirthText.key' is displayed
