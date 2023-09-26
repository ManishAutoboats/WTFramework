
Feature: EPOK MyAccount feature
 Background: Navigate to BAT home page
  Given user navigates to BAT home page
   And select allow all from cookie popup and select over 18 age confirmation option
  And user click on PersonIcon and Navigate to the Login Page
  When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'

     
     
      @EpokmyAccount  @liveEpok
     Scenario: Edit Email Functionality with blank password field 
     And Click on edit button to change password & Email address 
      And Click on change email checkbox
     And Enter new Email id in email box
     And Click on save button
     Then Epok assert text of 'editEmailwithNopasswordErrorMessage.key' is displayed
     
     
     @EpokmyAccount  @liveEpok @epokLive @epokRegression
     Scenario: Edit Password Functionality
     And Click on edit button to change password & Email address
     And Click on change Password checkbox
     And Enter current Password 'currentPassword.key' in new password box
     And Enter new Password 'newPassword.key' in new password box
     And Enter confirm new Password 'confirmPassword.key' in confirm new password box
     And Click on save button
     #Then Epok assert text of 'passwordChangeSuccessMsg.key' is displayed
     
      @EpokmyAccount
      Scenario Outline: Verify the order page.
     And users clicks on the my account menu dropdown
     And user select <myAccountDropdown> in my account menu
     Then Epok assert text of my account <Headings> is displayed
     Examples:
     |myAccountDropdown|Headings|
     |MyorderText.key|recentOrdersHeading.key|
     |inviteFriend.key|inviteFriendHeading.key|
     |shippingAddress.key|shippingAddressHeading.key|
     
     
     
    
     
     