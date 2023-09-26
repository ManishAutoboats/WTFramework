# This is the editing customer account details story
# This covers BUG - 31562
#@regressiondw
Feature: BAT Account management - Edit my account details
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'

@accountDashboard @32666 @10004 @nlSmoke @fr @NLlive @nl @ITSmoke @ITReg @frlive @IEReg @COSmoke
  Scenario: BUG: 31562 / Story 32666 - My Account Dashboard page contents information expected
    And users clicks on the 'dashboardEditLinkText.key' text link
    And update firstName field with a random string
    And assert successfully updated details

@accountDashboard @32666 @10004 @nlSmoke @fr @NLlive @nl @ITSmoke @ITReg @frlive @IEReg
  Scenario: BUG: 34534 - My Account cannot edit DOB test
   And users clicks on the 'dashboardEditLinkText.key' text link
    And assert DOB field isn't editable
    And update firstName field with a random string
    And assert successfully updated details

#@de @fr @frlive @dklive @dk @dksmoke
Scenario: My Account Dashboard page contents information expected
    And click on Edit button
    And update firstName field with a random string
    And assert successfully updated details

#@de @fr @frlive @dksmoke @dklive @dk
  Scenario: My Account cannot edit DOB test
   And click on Edit button
    And assert DOB field isn't editable
    And update firstName field with a random string
    And assert successfully updated details