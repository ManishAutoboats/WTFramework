
Feature: EPOK BAT RegestarionPage feature and will not Get sucessful message by automation


Background: Navigate to EPOK Home Page
  Given user navigates to BAT home page
  And select allow all from cookie popup and select over 18 age confirmation option
  And user click on PersonIcon and Navigate to the Login Page
  And Epok user clicks the registration button


 @liveEpok @epokLive
 Scenario: Registration page - ensure all expected elements are present
   And Epok assert text of 'regipageHeading.key' is displayed on my registration page
   And Epok assert text of 'regpageFooterText.key' is displayed on my registration page
   And Epok assert text of 'regipagePassStrengthText.key' is displayed on my registration page
   And Epok assert text of 'regipageNopassordText.key' is displayed on my registration page
  


   Scenario: Epok Registration of new user page
     And Epok populate Personal Information - first and last name with randomly generated data
     And Epok populate Email Address and password	
     And Epok user Click on Confirmage button	
     And Epok testingBreakpoint
    And Epok populate address fields
    And Epok populate DOB field with 'regpageValidDOB.key'
    And Epok User click on checkData button
    And Epok populate phone number field with 'regpagePhoneNumber.key'
    And Epok user click terms and comdintion ando complete registeration button
    
   @liveEpok @epokLive
  Scenario: Epok Registration of underage user
   And Epok populate Personal Information - first and last name with randomly generated data
     And Epok populate Email Address and password	
     And Epok user Click on Confirmage button	
     And Epok testingBreakpoint
    And Epok populate address fields
    And Epok populate DOB field with 'regpageInvalidDOB.key'
    And Epok User click on checkData button
    And Epok assert text of 'regUnderAgeErrorMsg.key' is displayed

  
  Scenario Outline: Epok Registration - phone number validation tests
    And Epok populate Personal Information - first and last name with randomly generated data
    And Epok populate Email Address and password	
    And Epok user Click on Confirmage button	
    And Epok testingBreakpoint
    And Epok populate address fields
    And Epok populate DOB field with 'regpageValidDOB.key'
    And Epok User click on checkData button
    And Epok populate phone number field with '<PhoneNumber>'
    And Epok and assert error validation message of '<Expected Error>'
    Examples:
      | PhoneNumber                          | Expected Error                           |
      | AlphabaticPhoneNumber.key            |AlphabaticPhoneNumError.key               |
      | phoneNumberWithspecialCharAndNum.key | phoneNumberWithspecialCharAndNumError.key|
      |phoneNumberWithAlphanumeric.key       |phoneNumberWithAlphanumericError.key      |
      |phoneNumberWithSpecialChar.key        |phoneNumberWithSpecialCharError.key       |
      