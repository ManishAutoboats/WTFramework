Feature: Glo About page

  Background: Navigate to Glo Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present

  @gloKzRegression
#  USER STORY 217646
  Scenario: Minimum age 21 years - TyrIt page DOB field validation
    And user click on Try link
    When user enters Try form details and submits the form with age as in table then assert expected is displayed
      | age      | expected                        |
      | 20-years | tryItPageMinimumAgeErrorMsg.key |
      | 21-years | tryItRequestSuccessText.key     |