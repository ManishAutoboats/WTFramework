# This is the editing customer account details story
#@LyftRegression @LyftDKReg
Feature: BAT Account management - Edit my account details
  Background: Navigate to BAT Home Page
 	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
	And user clicks the person icon -lyft 
	And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'


  Scenario: My Account Dashboard page contents information expected
	And user clicks the myAccount edit link - lyft
    And update firstName field with a random string
    Then assert successfully updated details