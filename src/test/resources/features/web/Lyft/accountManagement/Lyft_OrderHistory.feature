#ToDo
#This is the customer Order History feature
  ##Testing
    ## Newly registered user
    ## Navigate to My Account
    ## Delete account
    ## confirm account deletion message
    ## attempt to login in previous account details
@LyftRegression
Feature: BAT Account management - order history feature

  Background: Navigate to BAT Home Page
 	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft
	Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'


  Scenario: My order history
    Then user is successfully logged in
    Then users clicks on the 'recentlyOrderedText.key' text link
    And assert text of 'orderText.key' is displayed
    And assert text of 'dateText.key' is displayed
    #And assert text of 'deliverToText.key' is displayed
    And assert text of 'orderTotalText.key' is displayed
    #And assert text of 'statusText.key' is displayed
    #And assert text of 'actionText.key' is displayed
    And view order details
