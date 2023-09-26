Feature: Velo Avalanche Basket Page

  @veloPLReg
  Scenario Outline: 700882 AC1 basket page PL - page components
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket before adding related products
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    And div cart summary pane is displayed
    And confirm that the heading "<basket heading>" is displayed
    And assert text of 'basketTaxText' is displayed
    And div cart summary pane is displayed
    And each item in the basket has a name quantity image price and remove button
    Examples:
      | language | basket heading       |
      | pl       | basketHeadingText-pl |

  @veloPLReg @veloPLLive
  Scenario Outline: 700882 AC2 AC3 basket page PL- remove items - Registered user
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
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
      | language | pop up text            | empty basket message  |
      | pl       | deleteItemPopupText-pl | basketEmptyMessage-pl |

  @veloPLReg @veloPLLive
  Scenario Outline: 704072 AC2 AC3 basket page PL- remove items - Guest
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page and adds a product to basket
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
      | language | pop up text            | empty basket message  |
      | pl       | deleteItemPopupText-pl | basketEmptyMessage-pl |

  @veloBeReg
  Scenario Outline: 700882 AC1 basket page - page components, 962467 Add product in PLP as a logged in user, 964762 - Navigate to mini cart after adding product to cart from PLP
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user navigates to PLP page and adds a product to basket
    And empty basket before adding related products
    And user navigates to PLP page and adds a product to basket
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    And div cart summary pane is displayed
    And confirm that the heading "<basket heading>" is displayed
    And each item in the basket has a name quantity image price and remove button
    And user click on mini cart wrapper button in basket page
    And assert mini cart pop-up displays in basket page
    Examples:
      | language | basket heading       |
      | en       | basketHeadingText-en |
      | fr       | basketHeadingText-fr |
      | nl       | basketHeadingText-nl |

  @veloBeReg @veloBELive
  Scenario Outline: 700882 AC2 AC3 basket page - remove items - Registered user
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user navigates to PLP page and adds a product to basket
    And empty basket before adding related products
    And user navigates to PLP page and adds a product to basket
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
      | language | pop up text            | empty basket message  |
      | en       | deleteItemPopupText-en | basketEmptyMessage-en |
      | fr       | deleteItemPopupText-fr | basketEmptyMessage-fr |
      | nl       | deleteItemPopupText-nl | basketEmptyMessage-nl |

  @veloBeReg2 @veloBELive
  Scenario Outline: 700882 AC2 AC3 basket page - remove items - Guest, 962458 add product as guest
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page and adds a product to basket
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
      | language | pop up text            | empty basket message  |
      | en       | deleteItemPopupText-en | basketEmptyMessage-en |
      | fr       | deleteItemPopupText-fr | basketEmptyMessage-fr |
      | nl       | deleteItemPopupText-nl | basketEmptyMessage-nl |

  @veloBeReg
  Scenario Outline: 700882 AC4 Basket page - order summary
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user navigates to PLP page and adds a product to basket
    And empty basket before adding related products
    And user navigates to PLP page and adds a product to basket
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    And the user can see the total price including VAT labelled as "<total price label>"
    And the user can see the proceed to checkout CTA with button text "<checkout button text>"
    Examples:
      | language | total price label    | checkout button text           |
      | en       | basketTotalString-en | proceedToCheckoutButtonText-en |
      | fr       | basketTotalString-fr | proceedToCheckoutButtonText-fr |
      | nl       | basketTotalString-nl | proceedToCheckoutButtonText-nl |


  @veloZAReg
  Scenario Outline: AC1 basket page ZA - page components
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket before adding related products
    And user navigates to PLP page and adds a product to basket
    And user views the cart
    And div cart summary pane is displayed
    And confirm that the heading "<basket heading>" is displayed
    And div cart summary pane is displayed
    And each item in the basket has a name quantity image price and remove button
    Examples:
      | language | basket heading       |
      | za       | basketHeadingText-za |

  @veloZAReg
  Scenario Outline: AC2 AC3 basket page za- remove items - Registered user
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
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
      | language | pop up text            | empty basket message  |
      | za       | deleteItemPopupText-za | basketEmptyMessage-za |

  @veloZAReg
  Scenario Outline: AC2 AC3 basket page ZA- remove items - Guest
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
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
      | language | pop up text            | empty basket message  |
      | za       | deleteItemPopupText-za | basketEmptyMessage-za |