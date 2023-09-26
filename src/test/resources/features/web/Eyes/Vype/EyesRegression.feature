Feature: BAT Vype Eyes Regression Tests

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And turn off Eyes
    And select allow all from cookie popup and select over 18 age confirmation option
    And recover Eyes status

  @eyesVypeUkRegression
  Scenario: Pages directed with URL P1&2
    When user navigates to BAT "Blog" page via URL
    Then eyes check entire page "Blog Page"
    When user navigates to BAT "Bundles" page via URL
    Then eyes check entire page "Bundles Page"
    When user navigates to BAT "Contact Us" page via URL
    Then eyes check entire page "Contact Us Page"
    When user clicks newsletter signup button on Contact Us page
    Then eyes check region "Newsletter Signup Popup"
    When user navigates to BAT "Store Locator" page via URL
    And enter 'postCode.key' as lookup address
    Then eyes check Store Locator page
    When user navigates to BAT "404" page via URL
    Then eyes check entire page "404 Page"
    When user navigates to BAT "No Result Search" page via URL
    Then eyes check entire page "No Result Search"

  @eyesVypeUkRegression
  Scenario: Pages directed with URL P3
    When user navigates to BAT "FAQs" page via URL
    Then eyes check entire page "FAQs Page"
    When user navigates to BAT "Terms and Conditions" page via URL
    Then eyes check entire page "Terms and Conditions Page"
    When user navigates to BAT "About" page via URL
    Then eyes check entire page "About Page"
    When user navigates to BAT "Subscriptions Terms&Conditions" page via URL
    Then eyes check entire page "Subscriptions Terms&Conditions Page"
    When user navigates to BAT "Compare Devices" page via URL
    Then eyes check entire page "Compare Devices Page"
    When user navigates to BAT "Nicotine Salts" page via URL
    Then eyes check entire page "Nicotine Salts Page"

  @eyesVypeUkRegression
  Scenario: Pages directed with URL P4
    When user navigates to BAT "Delivery & Returns" page via URL
    Then eyes check entire page "Delivery & Returns Page"
    When user navigates to BAT "Cookie Notice" page via URL
    Then eyes check entire page "Cookie Notice Page"
    When user navigates to BAT "Privacy Notice" page via URL
    Then eyes check entire page "Privacy Notice Page"
    When user navigates to BAT "Terms of Sale" page via URL
    Then eyes check entire page "Terms of Sale Page"
    When user navigates to BAT "Warranty" page via URL
    Then eyes check entire page "Warranty Page"
    When user navigates to BAT "Accessibility" page via URL
    Then eyes check entire page "Accessibility Page"
    When user navigates to BAT "Satisfaction Guaranteed" page via URL
    Then eyes check entire page "Satisfaction Guaranteed Page"
    When user navigates to BAT "What is Vaping" page via URL
    Then eyes check entire page "What is Vaping Page"
    When user navigates to BAT "Disposal" page via URL
    Then eyes check entire page "Disposal Page"

  @eyesVypeUkRegression
  Scenario: Registration error message
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And expand address fields
    When user selects the create an account button without Email Verification script
    Then eyes check entire page "Registration Error - Empty Fields"
    When input invalid values for fields on registration page
    And user selects the create an account button without Email Verification script
    Then eyes check entire page "Registration Error - Invalid Fields"

  Scenario: error message - all pages
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    And set quantity to '0'
    Then click add to cart button
    And set quantity to '10001'
    Then click add to cart button
    And set quantity to '1'
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And submit visa details as below and eyes check error messages
      | cc_number         | cc_holderName | expire_month | expire_year | securityCode | tickTC | errorType                   |
      |                   |               |              |             |              | n      | all blank fields            |
      | 44443333222211118 | joe           | 05           | 01          | 123          | n      | wrong card number           |
      | 4444333322221111  | joe           | 05           | 01          | e            | n      | wrong securityCode          |
      | 4444333322221111  | joe           | 05           | 01          | 123          | n      | unselect term and condition |
