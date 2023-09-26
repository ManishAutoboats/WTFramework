#This feature - Verify how LAB and Non LAB products will be displayed in Mini-cart and Checkout
  #for review / purchase LAB products.

@LyftDKReg @LyftRegression2
Feature: Lyft Lab Mini Basket Display

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft
    And create a new account

  Scenario: LAB and Non-LAB Bundle Pack Products Display on Mini Cart
    And user navigates to PLP page and adds a product to basket
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And user clicks on See The Flavors link from Block 1
    And user selects strength 'Regular.key' for a six pack LAB product and add to cart
    And click on basket icon
    And assert same products details for LAB and Non-LAB products in mini cart
    And assert Strength attribute as 'productStrength.key' appears for only LAB products in mini cart

  Scenario: Bundle Pack Products Display on Checkout
    And user navigates to PLP page and adds a product to basket
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And user clicks on See The Flavors link from Block 1
    And user selects strength 'Regular.key' for a three pack LAB product,selects quantity '1' and add to cart
    And click on basket icon
    And user clicks on checkout button
    Then Payment page details confirmed
    Then user selects Items in Cart section under Order Overview section
    And assert same products details for LAB and Non-LAB products on Checkout
    And assert Strength attribute as 'productStrength.key' appears for only LAB products on Checkout

  Scenario: Separate Lines Mini Cart Display for different products with Same Attributes
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And user clicks on See The Flavors link from Block 1
    And user selects strength 'Regular.key' for a three pack LAB product,selects quantity '1' and add to cart
    And user selects strength 'Regular.key' for a single pack LAB product,selects Quantity '1' and add to cart
    And click on basket icon
    And assert different products with same flavour and strength show on separate lines in mini cart
