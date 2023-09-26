Feature: BAT Social Login | Using Facebook feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present

  @VuseUKReg2
  Scenario: Sign In using Facebook - Linking and Unlinking of M2 and FB accounts after password reset
    When user clicks on Continue with Facebook button
    Then assert new window opens with 'facebookLoginURL.key' login page
    When user enters facebook email id 'facebookLoginEmail2.key' and password 'facebookLoginPassword.key' and click login
    And assert text of 'createAccountHeading.key' is displayed
    And assert text of 'nearlyDonetxt.key' is displayed
    Then assert First name,Last name and email address fields are pre-populated
    And assert Email address field is non editable
    And assert Password field is removed from account registration form
    And populate DOB field with 'UnderAgeDob.key'
    And user selects the create an account button without Email Verification script
    And assert DOB error is presented
    Then register an account with same email ID on Create Account page when customer doesn't exist on M2
    And assert text of 'accountLinkedSuccessMessage.key' is displayed
    And assert text of 'successRegistraionMsg.key' is displayed
    And assert text of 'emailVerificationMessage.key' is not displayed
    Then assert text of 'DashboardTitle.key' is displayed
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then assert 'DISCONNECT' link is displayed verifying both facebook and M2 accounts have been linked
    Then users clicks on the 'disconnectLinkText.key' link
    And assert add password error 'addPwdToDisconnectErrorMsg.key' is displayed for M2 account
    Then users clicks on the 'addPasswordLinkText.key' link
    Then wait for the page to fully load
    And select update password checkbox
    And assert Current Password field is not displayed for M2 user without a password
    And Enter new Password 'loginValidPassword.key' in new password box
    And Enter confirm new Password 'loginValidPassword.key' in confirm new password box
    And Click on save button
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then users clicks on the 'disconnectLinkText.key' link
    And assert Sign in link with text 'signInWithFacebookLink.key' is displayed and enabled
    And assert text of 'disconnectLinkText.key' is not displayed
    Then user clicks on Sign in with Facebook link
    Then wait for facebook pop-up window to close
    Then assert 'DISCONNECT' link is displayed verifying both facebook and M2 accounts have been linked
    Then user goes to my account page
    Then user clicks on Change Password button
    And select update password checkbox
    And assert Current Password field is displayed after password is added from My Social Accounts
    And Enter current Password 'currentPassword.key' in new password box
    And Enter new Password 'loginValidPassword.key' in new password box
    And Enter confirm new Password 'loginValidPassword.key' in confirm new password box
    And Click on save button
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then assert 'DISCONNECT' link is displayed verifying both facebook and M2 accounts have been linked
    Then attempt to logout
    Then user wait for home page to load
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    Then user signs in to the site custom details 'facebookLoginEmail2.key' 'loginValidPassword.key'
    Then assert text of 'DashboardTitle.key' is displayed
    Then proceed to delete customer account

  #@VuseUKReg2 @VuseUKLive2
  Scenario: Sign In using Facebook - Linking FB Account to M2 account with same email ID, assert Error message when password not set
    When user clicks on Continue with Facebook button
    Then assert new window opens with 'facebookLoginURL.key' login page
    When user enters facebook email id 'facebookLoginEmail2.key' and password 'facebookLoginPassword.key' and click login
    Then register an account with same email ID on Create Account page when customer doesn't exist on M2
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then assert 'DISCONNECT' link is displayed verifying both facebook and M2 accounts have been linked
    Then users clicks on the 'disconnectLinkText.key' link
    And assert add password error 'addPwdToDisconnectErrorMsg.key' is displayed for M2 account
    Then proceed to delete customer account

  #@VuseUKReg2 @VuseUKLive
  Scenario: Unlinking Accounts - Unlink Facebook account from M2 account from My Social Accounts page
    When user clicks on Continue with Facebook button
    Then assert new window opens with 'facebookLoginURL.key' login page
    When user enters facebook email id 'facebookLoginEmail1.key' and password 'facebookLoginPassword.key' and click login
    Then register an account with same email ID on Create Account page when customer doesn't exist on M2
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then users clicks on the 'disconnectLinkText.key' link
    Then users clicks on the 'addPasswordLinkText.key' link
    Then wait for the page to fully load
    And select update password checkbox
    And assert Current Password field is not displayed for M2 user without a password
    And Enter new Password 'loginValidPassword.key' in new password box
    And Enter confirm new Password 'loginValidPassword.key' in confirm new password box
    And Click on save button
    Then user clicks on Change Password button
    And select update password checkbox
    And assert Current Password field is displayed after password is added from My Social Accounts
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then users clicks on the 'disconnectLinkText.key' link
    And assert Sign in link with text 'signInWithFacebookLink.key' is displayed and enabled
    And assert text of 'disconnectLinkText.key' is not displayed
    Then user clicks on Sign in with Facebook link
    Then wait for facebook pop-up window to close
    Then assert 'DISCONNECT' link is displayed verifying both facebook and M2 accounts have been linked
    Then attempt to logout
    Then user wait for home page to load
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    Then user signs in to the site custom details 'facebookLoginEmail1.key' 'loginValidPassword.key'
    Then assert text of 'DashboardTitle.key' is displayed
    Then proceed to delete customer account

  @VuseUKReg2 @VuseUKLive
  Scenario: Sign In using Google - Link on Sign In page
    When user clicks on Continue with Google button
    Then assert new window opens with 'googleLoginURL.key' login page

  @VuseUKReg2 @VuseUKLive
  Scenario: Sign In using Google - Link on Create Account page
    And user clicks the registration button
    When user clicks on Continue with Google button
    Then assert new window opens with 'googleLoginURL.key' login page

  @VuseUKLive
  Scenario: Sign In using Facebook - Link on Sign In page
    When user clicks on Continue with Facebook button
    Then assert new window opens with 'facebookLoginURL.key' login page

  @VuseUKLive
  Scenario: Sign In using Facebook - Link on Create Account page
    And user clicks the registration button
    When user clicks on Continue with Facebook button
    Then assert new window opens with 'facebookLoginURL.key' login page

  #@VuseUKReg2 @VuseUKLive
  Scenario: Assert both M2 and FB accounts remains linked on Social Accounts page after Password reset
    When user clicks on Continue with Facebook button
    Then assert new window opens with 'facebookLoginURL.key' login page
    When user enters facebook email id 'facebookLoginEmail3.key' and password 'facebookLoginPassword.key' and click login
    Then register an account with same email ID on Create Account page when customer doesn't exist on M2
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then users clicks on the 'disconnectLinkText.key' link
    Then users clicks on the 'addPasswordLinkText.key' link
    Then wait for the page to fully load
    And select update password checkbox
    And Enter new Password 'loginValidPassword.key' in new password box
    And Enter confirm new Password 'loginValidPassword.key' in confirm new password box
    And Click on save button
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then assert 'DISCONNECT' link is displayed verifying both facebook and M2 accounts have been linked
    Then user goes to my account page
    Then user clicks on Change Password button
    And select update password checkbox
    And Enter current Password 'currentPassword.key' in new password box
    And Enter new Password 'loginValidPassword.key' in new password box
    And Enter confirm new Password 'loginValidPassword.key' in confirm new password box
    And Click on save button
    Then attempt to logout
    Then user wait for home page to load
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    Then user signs in to the site custom details 'facebookLoginEmail3.key' 'loginValidPassword.key'
    And users clicks on the 'mySocialAccountsLinkText.key' text link
    Then assert 'DISCONNECT' link is displayed verifying both facebook and M2 accounts have been linked
    Then proceed to delete customer account

  @VuseUKReg2
  Scenario: 902974 Forgotten password page has social account login option
    When user clicks the forgotten password link
    Then user is taken to forgot your password page
    When user clicks on Continue with Facebook button
    Then assert new window opens with 'facebookLoginURL.key' login page

  @VuseUKReg2 @VuseUKLive
  Scenario: My Accounts - Link Social Accounts CTA and navigation to Social Accounts landing page
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And assert link social account cta is displayed
    And users clicks on the 'linkSocialAccountCTA.key' text link
    And assert URL contains text 'socialAccountsPageURL.key'
    And assert Sign in link with text 'signInWithFacebookLink.key' is displayed and enabled
    And assert Sign in link with text 'signInWithGoogleLink.key' is displayed and enabled
    And assert text of 'linkingAccountsHeaderText.key' is displayed
    And assert text of 'disconnectLinkText.key' is not displayed

  @VuseUKReg2
  Scenario: 902974 registration page has social account login option
    And user clicks the registration button
    And assert text of 'noMorePasswordHeadingtext.key' is displayed
    And assert text of 'noMorePasswordContenttext.key' is displayed

  @VuseUKReg2
  Scenario: 902974 registration at guest checkout page has social account login option
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And select product by index "productIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on proceed to checkout button
    And confirm popup mask present
    And select create new account from checkout PopUp
    And assert text of 'noMorePasswordHeadingtext.key' is displayed
    And assert text of 'noMorePasswordContenttext.key' is displayed