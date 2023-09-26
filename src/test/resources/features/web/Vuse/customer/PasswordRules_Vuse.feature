Feature: BAT Account Management - Password rules
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
	And user clicks on 'signInLink.key' link from Person Menu

  @VuseDEReg @VuseFRReg @VuseFRLive @VuseITReg @VuseMXReg @VuseMXLive @VuseUKReg @VuseZAReg
  Scenario: Password rules
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in
    And users clicks on the 'dashboardEditLinkText.key' text link
    And select update password checkbox
    And update password with '122' and assert error msg
    And update password with '1333' and assert error msg
    And update password with '144444' and assert error msg
    And update password with '15555555' and assert error msg
    And update password with '^&*(^&*(^&*(^*(' and assert error msg
    And update password with 'asferwq' and assert error msg

  @VuseITReg
  Scenario: Edit Password with incorrect current password
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in
    And user clicks on Change Password button
    And select Change Password checkbox
    When update password with incorrect current password
    And assert text of 'incorrectPasswordMsg.key' is displayed

    # Automate bug 928041
    @VuseUKReg

  @VuseFRReg @VuseITReg
  Scenario: Edit Password with incorrect confirm password
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in
    And user clicks on Change Password button
    And select Change Password checkbox
    When update password with incorrect confirm password
    And assert text of 'incorrectConfirmPasswordError.key' is displayed

    #automate issue 988274
    @VuseMXReg
    Scenario: Change new user's passed and get the correct email
      Given create a new account
      And user clicks the person icon
      And user clicks on 'myAccountLink.key' link from Person Menu
      And users clicks on the 'dashboardEditLinkText.key' text link
      When select update password checkbox
      And Enter current Password 'currentPassword.key' in new password box
      And user change the password
      Then assert text of 'resetPassword.key' is displayed
      Then validate get "reset password" email for user
