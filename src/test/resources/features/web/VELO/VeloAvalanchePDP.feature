Feature: Velo Avalanche PDP

  @veloPLReg @veloPLLive
  Scenario Outline: 725599 Velo PL > Configuring AEM base URLs in Magento
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    And user clicks the person icon
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    Examples:
      | url checked |
      | products    |
      | privacy     |
      | contact     |

  @veloPLReg
 Scenario Outline: Verify all elements displayed on PDP and checkout
   Given user navigates to BAT home page for language "pl"
   And select allow all from cookie popup and select over 18 age confirmation option
   And user clicks the person icon
   When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
   When users clicks on the 'Store.key' text link
   And clicks PLP product link index '1'
   And user verify elements on PDP to checkout
   Then I am on the checkout page
   And Customer makes payu payment with "<Payment Type>"
   And grab and output Order number
   And assert text of 'ThankForPurchase-pl' is displayed
   And assert text of 'yourOrderNumberText-pl' is displayed
   Examples:
     |Payment Type   |
     | visa          |

  @veloZAReg @veloZALive
  Scenario Outline:Configuring AEM base URLs in Magento- VELO ZA
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    And user clicks the person icon
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    When I click on the "<url checked>" link
    Then I confirm I am on the "<url checked>" page
    Examples:
      | url checked |
      | products    |
      | privacy     |
      | contact     |

  @veloBeReg2
  Scenario Outline: 965162 Velo BE - Add product in PDP and check mini cart
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And click on first buyable product in PLP in Velo site
    And click add to cart button
    And assert mini cart basket displays the total price of added product
    Examples:
      | language |
      | en       |
      | fr       |
      | nl       |

  @veloZAReg
  Scenario:Velo ZA - Add product and check mini cart from You might also like section assertion in PDP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And click on first buyable product in PLP in Velo site
    And click on first buyable product in PDP in Velo site
    And click add to cart button
    And assert mini cart basket displays the total price of added product

  @veloBeReg2
  Scenario Outline: Velo BE - Verify strength filter is ascending order
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And user clicks on 'filter' option
    And assert filter option strength is in ascending order
    Examples:
      | language |
      | en       |
      | fr       |
      | nl       |


