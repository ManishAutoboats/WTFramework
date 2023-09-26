Feature: Velo Avalanche PLP

  @veloBeReg2
  Scenario Outline: 965162 Velo BE - Add product in PLP and check mini cart
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page and adds a product to basket
    And assert mini cart basket displays the total price of added product
    Examples:
      | language |
      | en       |
      | fr       |
      | nl       |

  @veloZAReg
  Scenario:Velo ZA - FAQ section displayed assertion for PLP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And assert text of 'FAQ.key' is displayed
    And assert text of 'FAQQuestion1' is displayed
    And assert text of 'FAQQuestion2' is displayed
    And assert text of 'FAQQuestion3' is displayed
    And assert text of 'FAQQuestion4' is displayed
    And assert text of 'FAQQuestion5' is displayed
    And assert text of 'FAQQuestion6' is displayed

  @veloZAReg
  Scenario:Velo ZA - Assertion for Filtering in PLP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And user clicks on 'filter' option
    And user clicks on 'Medium' option
    And user clicks done in filter pop-up
    And confirm number of results returned is greater than '0'
    And user clicks on 'filter' option
    And user clicks on 'Mild' option
    And user clicks done in filter pop-up
    And confirm number of results returned is greater than '0'

  @veloPLReg
  Scenario: Assertion for Filtering in PLP
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    And users clicks on the 'Store.key' text link
    And user clicks on 'filter' option
    And user clicks on 'Medium' option
    And user clicks done in filter pop-up
    And confirm number of results returned is greater than '0'
    And user clicks on 'filter' option
    And user clicks on 'Medium' option
    And user clicks on 'Mild' option
    And user clicks done in filter pop-up
    And confirm number of results returned is greater than '0'

  @veloPLReg
  Scenario:Assertion for Sorting in PLP
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    When users clicks on the 'Store.key' text link
    And user clicks on 'sorting' option
    Then user clicks on 'Name A-Z' option
    And confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    And user clicks on 'Name Z-A' option
    Then confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    And user clicks on 'Price low' option
    Then confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    And user clicks on 'Price high' option
    Then confirm number of results returned is greater than '0'

  @@veloPLReg
  Scenario: Verify all elements displayed for product on PLP
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    When users clicks on the 'Store.key' text link
    Then validate product elements is present on PLP

  @@veloPLReg
  Scenario Outline: Add Single strength product to cart from PLP and checkout
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    When users clicks on the 'Store.key' text link
    And user add single strength product to checkout
    Then I am on the checkout page
    And Customer makes payu payment with "<Payment Type>"
    And grab and output Order number
    And assert text of 'ThankForPurchase-pl' is displayed
    And assert text of 'yourOrderNumberText-pl' is displayed
    Examples:
      |Payment Type   |
      | visa          |

  @veloZAReg
  Scenario:Velo ZA - Assertion for Sorting in PLP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user navigates to PLP page
    Then user clicks on 'sorting' option
    Then user clicks on 'Name A-Z' option
    And confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    Then user clicks on 'sorting' option
    And user clicks on 'Name Z-A' option
    Then confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    And user clicks on 'Price low' option
    Then confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    And user clicks on 'Price high' option
    Then confirm number of results returned is greater than '0'

  @veloZAReg
  Scenario:Velo ZA - verify Cookies Description and Cookie Settings link CTA
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    When user click on manage cookies button
    And assert description is displayed for each cookie type
    And assert Cookie Settings link CTA for each cookie type