#This is the customer Account Dashboard feature
Feature: 13848 BAT Account management - My Account dash board tests

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
   	Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'



  @VuseDEReg2 @VuseFRReg @VuseITReg @VuseITLive @VuseMXReg @VuseMXLive
  Scenario: My Account Dashboard displays username which is also a link
    Then assert text of 'DashboardTitle.key' is displayed

  @VuseDEReg
  Scenario: Guest to enter valid email and password
    And user clicks on the links and navigates to the page
      | Title                |
      | MyAccountPage.key    |
      | personalDetails.key  |
      | YourOrders.key   |
      | MeinVuseReload.key   |
      | AddressBook.key      |
      | StorePayments.key    |
      | NewsLetter.key       |
    And user hovers over the person icon
    And users clicks on the 'SignOut.key' link


@VuseDEReg @VuseITReg @VuseITLive @VuseMXReg @VuseMXLive @VuseUKReg
  Scenario: My Account - Edit Account
    Then assert text of 'dashboardEditText.key' is displayed
    Then users clicks on the 'dashboardEditLinkText.key' text link
    Then assert text of 'editAccountInfoText.key' is displayed
    And assert first name input is present
    And assert last name input is present
    And assert DOB input is present
    And assert save button is present

@VuseDEReg @VuseUKReg @VuseFRReg @VuseITReg @VuseITLive @VuseMXLive @VuseMXReg
Scenario: My Account Dashboard page contents information expected
  Then assert text of 'dashboardEditText.key' is displayed
    Then assert text of 'myAccountDashboardText.key' is displayed
    Then assert text of 'firstNameText.key' is displayed
    Then assert text of 'lastNameText.key' is displayed
    Then assert text of 'emailText.key' is displayed

  @VuseDEReg @VuseFRReg
  Scenario: My Account Dashboard Page Contents
    Then assert text of 'myAccountDashboardText.key' is displayed
    Then assert text of 'firstNameText.key' is displayed
    Then assert text of 'lastNameText.key' is displayed
    Then assert text of 'emailText.key' is displayed

  @VuseDEReg
  Scenario: Order History
    And users clicks on the 'YourOrdersText.key' text link
    And assert text of 'recentlyOrderedText.key' is displayed


  @VuseUKReg @VuseZAReg
  Scenario: Profile Picture Not Displayed
    Then user profile picture is not displayed
    Then click on edit details
    Then user profile picture is not displayed
    And user click on search icon and submits the following search term 'searchTermTitle.key'
    And get all lists from page
    Then user profile picture is not displayed

  @VuseUKReg
  Scenario: My Account - Password change
    Then assert text of 'dashboardEditText.key' is displayed
    Then users clicks on the 'dashboardEditLinkText.key' text link
    Then assert text of 'editAccountInfoText.key' is displayed
    Then select update password checkbox
    Then user change the password
    And user hovers over the person icon
    And users clicks on the 'signOutLink.key' link
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPasswordNew.key'

  @VuseFRReg
  Scenario: FR My Account - Password change
    Then assert text of 'dashboardEditText.key' is displayed
    Given users clicks on the 'dashboardEditLinkText.key' text link
    Then assert text of 'editAccountInfoText.key' is displayed
    When select update password checkbox
    And user change the password
    And user hovers over the person icon
    And users clicks on the 'myAccountLink.key' text link
    And users clicks on the 'logoutText.key' text link
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPasswordNew.key'

  @VuseZAReg @VuseZALive
  Scenario: My Account Dashboard page contents information expected VuseZA
    Then assert text of 'dashboardEditText.key' is displayed
    Then assert text of 'myAccountDashboardText.key' is displayed
    Then assert text of 'firstNameText.key' is displayed
    Then assert text of 'lastNameText.key' is displayed
    Then assert text of 'emailText.key' is displayed

  @VuseZAReg
  Scenario: My Account Dashboard page check order history - VuseZA
    And assert My Orders Table headings are as expected
    And assert My Order table data is as expected

  @VuseITReg
  Scenario: User logout and back to home page
    When users clicks on the 'logoutText.key' text link
    Then url contains 'homeUrl.key'