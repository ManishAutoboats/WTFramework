  Feature: FAQ - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


    Scenario: Mobile - PDP Q&A feature
      And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
      And empty basket
      And user click on search icon and submits the following search term 'searchTerm.key'
      And click first result
      Then assert ask a question button is displayed
      Then assert question popup is displayed on clicking ask a question cta
      And fill the form and click on submit
      And verify subscription confirmation mail for ask a question
