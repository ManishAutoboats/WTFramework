@sprint1 @182238 @mobile @live #@IEReg
Feature: BAT Age mobile module feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And assert entry age module is presented to user

  Scenario: mobile user selects not over 18 age confirmation
    And select allow all from new cookie consent popup
    And mobile user selects not over 18 age confirmation option, confirm text and google redirection
    And resize browser screen - full screen

  Scenario: mobile user selects over 18 age confirmation
    And select allow all from cookie popup
	 #And user selects over 18 age confirmation option

    And age confirmation box is no longer displayed
   #And Bat expected homepage title is 'Privacy and Cookie Policy'
   # And resize browser screen - full screen

