Feature: BAT Glo Eyes Regression Tests

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And turn off Eyes
    And select allow all from cookie popup and select over 18 age confirmation option
    And recover Eyes status

  @eyesGloItRegression
  Scenario: GloIT - Pages directed with URL P1&2
    When user navigates to BAT "Blog" page via URL
    Then eyes check entire page "Blog Page"
    When user navigates to BAT "Contact Us" page via URL
    Then eyes check entire page "Contact Us Page"
    When user clicks newsletter signup button on Contact Us page
    Then eyes check region "Newsletter Signup Popup"
    When user navigates to BAT "Store Locator" page via URL
    And enter 'postcode.key' as lookup address
    Then eyes check Store Locator page
    When user navigates to BAT "404" page via URL
    Then eyes check entire page "404 Page"
    When user navigates to BAT "No Result Search" page via URL
    Then eyes check entire page "No Result Search"

  @eyesGloItRegression
  Scenario: GloIT - Pages directed with URL P3&4
    When user navigates to BAT "FAQs" page via URL
    Then eyes check entire page "FAQs Page"
    When expand all FAQs
    Then eyes check entire page "FAQs expanded"
    When user navigates to BAT "Terms and Conditions" page via URL
    Then eyes check entire page "Terms and Conditions Page"
    When user navigates to BAT "Cookie Policy" page via URL
    Then eyes check entire page "Cookie Policy Page"
    When user navigates to BAT "Privacy Policy" page via URL
    Then eyes check entire page "Privacy Policy Page"
    When user navigates to BAT "Terms of Sale" page via URL
    Then eyes check entire page "Terms of Sale Page"
    When user clicks on oneTrust footer link
    Then eyes check scroll region "Cookie Settings Popup"

  @eyesGloItRegression
  Scenario: GloIT - Contact Us error message
    When user navigates to BAT "Contact Us" page via URL
    And user clicks send button on Contact Us page
    Then eyes check entire page "Contact Us Error - Empty Fields"
    When user input email as "loginInvalidEmail.key" on Contact Us page
    And user clicks send button on Contact Us page
    Then eyes check entire page "Contact Us Error - Invalid Email"

  @eyesGloDeRegression
  Scenario: GloDE - Pages directed with URL P1&2
    When user navigates to BAT "Blog" page via URL
    Then eyes check entire page "Blog Page"
    When user navigates to BAT "Contact Us" page via URL
    Then eyes check entire page "Contact Us Page"
    When user navigates to BAT "Store Locator" page via URL
    And enter 'postcode.key' as lookup address
    Then eyes check Store Locator page
    When user navigates to BAT "404" page via URL
    Then eyes check entire page "404 Page"
    When user navigates to BAT "No Result Search" page via URL
    Then eyes check entire page "No Result Search"
    When user navigates to BAT "Try Glo for Free" page via URL
    Then eyes check entire page "Try Glo for Free"

  @eyesGloDeRegression
  Scenario: GloDE - Pages directed with URL P3&4
    When user navigates to BAT "FAQs" page via URL
    Then eyes check entire page "FAQs Page"
    When expand all FAQs
    Then eyes check entire page "FAQs expanded"
    When user navigates to BAT "About" page via URL
    Then eyes check entire page "About Page"
    When user navigates to BAT "Terms and Conditions" page via URL
    Then eyes check entire page "Terms and Conditions Page"
    When user navigates to BAT "Privacy Policy" page via URL
    Then eyes check entire page "Privacy Policy Page"
    When user navigates to BAT "Withdrawal" page via URL
    Then eyes check entire page "Withdrawal Page"
    When user navigates to BAT "Imprint" page via URL
    Then eyes check entire page "Imprint Page"
    When user navigates to BAT "Terms of Use" page via URL
    Then eyes check entire page "Terms of Use Page"
    When user navigates to BAT "Shipping and Payment" page via URL
    Then eyes check entire page "Shipping and Payment Page"
    When user clicks on oneTrust footer link
    Then eyes check scroll region "Cookie Settings Popup"
