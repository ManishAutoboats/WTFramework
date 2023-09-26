Feature: Velo Avalanche PDP - Mobile


  @RegVeloZA_Mobile
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


  @RegVeloZA_Mobile
  Scenario:Velo ZA - Add product and check mini cart from You might also like section assertion in PDP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And click on first buyable product in PLP in Velo site
    And click on first buyable product in PDP in Velo site
    And click add to cart button
    And assert mini cart basket displays the total price of added product
