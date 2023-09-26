Feature: BAT Vuse Eyes Regression Tests

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And turn off Eyes
    And select allow all from cookie popup and select over 18 age confirmation option
    And recover Eyes status

  @eyesVuseDeRegression @eyesVuseDkRegression
  Scenario: Registration error message
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And user selects the create an account button without Email Verification script
    Then eyes check entire page "Registration Error - Empty Fields"
    When input invalid values for fields on registration page
    And user selects the create an account button without Email Verification script
    Then eyes check entire page "Registration Error - Invalid Fields"

  @eyesVuseDeRegression @eyesVuseDkRegression @eyesVuseCORegression
  Scenario: Pages directed with URL P1&2
    When user navigates to BAT "Blog" page via URL
    Then eyes check entire page "Blog Page"
    When user navigates to BAT "404" page via URL
    Then eyes check entire page "404 Page"
    When user navigates to BAT "No Result Search" page via URL
    Then eyes check entire page "No Result Search"
    When user navigates to BAT "Devices PLP" page via URL
    Then eyes check entire page "Devices PLP"

  @eyesVuseDeRegression
  Scenario: Pages directed with URL P3
    When user navigates to BAT "FAQs" page via URL
    Then eyes check entire page "FAQs Page"
    When user navigates to BAT "Terms and Conditions" page via URL
    Then eyes check entire page "Terms and Conditions Page"
    When user navigates to BAT "Terms of Use" page via URL
    Then eyes check entire page "Terms of Use Page"

  @eyesVuseDkRegression
  Scenario: Pages directed with URL P3 for Vuse/Dk
    When user navigates to BAT "FAQs" page via URL
    Then eyes check entire page "FAQs Page"
    When user navigates to BAT "Terms of Trade" page via URL
    Then eyes check entire page "Terms of Trade Page"
    When user navigates to BAT "Business information" page via URL
    Then eyes check entire page "Business information Page"



  @eyesVuseDeRegression
  Scenario: Pages directed with URL P4
    When user navigates to BAT "Delivery & Returns" page via URL
    Then eyes check entire page "Delivery & Returns Page"
    When user navigates to BAT "Cookie Notice" page via URL
    Then eyes check entire page "Cookie Notice Page"
    When user clicks Right of withdrawal link
    Then eyes check entire page "Right of withdrawal"
    When user clicks Satisfaction Guaranteed link
    Then eyes check entire page "Satisfaction Guaranteed"
    When user clicks Accessibility link
    Then eyes check entire page "Accessibility Page"

  @eyesVuseDkRegression
  Scenario: Pages directed with URL P4 on VuseDk
    When user navigates to BAT "Disposal" page via URL
    Then eyes check entire page "Disposal Page"
    When user navigates to BAT "Cookie Policy" page via URL
    Then eyes check entire page "Cookie Policy Page"
    When user navigates to BAT "Privacy Policy" page via URL
    Then eyes check entire page "Privacy Policy Page"
    When user navigates to BAT "Delivery & Returns" page via URL
    Then eyes check entire page "Delivery & Returns Page"

  @eyesVuseDeRegression @eyesVuseDkRegression
  Scenario Outline: Error messaging - Checkout
    When create a new account
    And user navigates to PLP page and adds a product to basket
    And eyes check error message after trigger error message on checkout for "<Payment Type>"
    Examples:
      | Payment Type |
      | mastercard   |

  @eyesVuseDeRegression
  Scenario: individual cookie setting page
    When user clicks individual cookie setting link
    Then eyes check entire page "Individual Cookie Setting Popup"

  @eyesVuseDkRegression
  Scenario: update cookie setting page
    When user clicks update cookie setting link
    Then eyes check entire page "Update Cookie Setting Popup"

  @eyesVuseDeRegression @eyesVuseDkRegression
  Scenario: Error messaging - PDP
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    And set quantity to '0'
    Then click add to cart button
    Then eyes check PDP error message
    And set quantity to '10001'
    Then click add to cart button
    Then eyes check PDP error message

  @eyesVuseCORegression
  Scenario: Pages directed with URL P3 for CO
    When user navigates to BAT "FAQs" page via URL
    Then eyes check entire page "FAQs Page"
