Feature: Lyft Contact us page

  Background:Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @LyftRegression2
  Scenario: customer feed back form and assert success message
    Given users clicks on the 'contactUsText.key' link
    And url contains 'contactUsUrl.key'
    And user closes the alert if present
    Then user should see an email of 'contactusEmail.key'