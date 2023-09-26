# This is the editing customer account details story
# This covers BUG - 31562
#@gloDeRegression
Feature: BAT Account management - Edit my account details
  Background: Navigate to BAT Home Page
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

#  Scenario: BUG: 31562 / Story 32666 - My Account Dashboard page contents information expected
#    And users clicks on the 'dashboardEditLinkText.key' text link
#    And update firstName field with a random string
#    And assert successfully updated details

  Scenario: BUG: 34534 - My Account cannot edit DOB test
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And users clicks on the 'dashboardEditLinkText.key' text link
    Then assert DOB field isn't editable
    And update firstName field with a random string
    And assert successfully updated details

  #468198 - this is removed from scope for PL as per requirements the name fields are uneditable
  Scenario: BUG: 31562 / Story 32666 - My Account Dashboard page contents information expected
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And users clicks on the dashboardEditLink text link
    And update firstName field with a random string
    And  validation success message displayed

  #@gloKzRegression
  Scenario: BUG: 34534 - My Account cannot edit DOB test
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And users clicks on the dashboardEditLink text link
    And assert DOB field isn't editable
    And update firstName field with a random string
    And  validation success message displayed

  @gloKzRegression
  Scenario: My Account cannot edit User ID field
    When create a new account
    And user closes the RDB pop-up banner if present
    And user clicks on PersonIcon and Navigate to My Account Page
    And users clicks on the dashboardEditLink text link
    And assert ID field is not editable

  @gloPlRegression2
  Scenario: Email address update and verify
    When create a new account
    And users clicks on the 'myData.key' link
    And user select change email address and update the new address
    Then user try to login with new email
