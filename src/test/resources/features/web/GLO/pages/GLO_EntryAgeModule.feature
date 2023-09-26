 @gloDeRegression @gloPlRegression2 @gloItRegression2 @gloPlLive
Feature: 14782 BAT Age module feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page

  Scenario: user selects not over 18 age confirmation
    And user selects not over 18 age confirmation option, confirm text and google redirection
    
  Scenario: user selects over 18 age confirmation
    And select allow all from cookie popup
    And age confirmation box is no longer displayed