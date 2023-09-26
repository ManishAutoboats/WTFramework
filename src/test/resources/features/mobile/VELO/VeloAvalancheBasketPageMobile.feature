Feature: Velo Avalanche Basket Page - Mobile

  Background: Velo Avalanche Basket Page
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVeloZA_Mobile
  Scenario Outline: AC1 basket page ZA - page components
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket before adding related products
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    And div cart summary pane is displayed
    And confirm that the heading "<basket heading>" is displayed
    And div cart summary pane is displayed
    And each item in the basket has a name quantity image price and remove button
    Examples:
      | basket heading       |
      | basketHeadingText-za |

  @RegVeloZA_Mobile
  Scenario Outline: AC2 AC3 basket page za- remove items - Registered user
    And user clicks the person icon
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket before adding related products
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    And i click on 'X' button next to any product to remove the product
    Then I see a pop-up on the screen with text "<pop up text>" asking me to confirm removal of product from my basket
    And there is a Cancel and Remove CTA
    And If i click cancel i go back to my basket
    And empty basket before adding related products
    Then I see message "<empty basket message>" informing that my basket is empty
    And there is a 'Start Shopping' CTA
    And this CTA redirects me to web shop homepage
    Examples:
      | pop up text            | empty basket message  |
      | deleteItemPopupText-za | basketEmptyMessage-za |

  @RegVeloZA_Mobile
  Scenario Outline: AC2 AC3 basket page ZA- remove items - Guest
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    And i click on 'X' button next to any product to remove the product
    Then I see a pop-up on the screen with text "<pop up text>" asking me to confirm removal of product from my basket
    And there is a Cancel and Remove CTA
    And If i click cancel i go back to my basket
    And empty basket before adding related products
    Then I see message "<empty basket message>" informing that my basket is empty
    And there is a 'Start Shopping' CTA
    And this CTA redirects me to web shop homepage
    Examples:
      | pop up text            | empty basket message  |
      | deleteItemPopupText-za | basketEmptyMessage-za |