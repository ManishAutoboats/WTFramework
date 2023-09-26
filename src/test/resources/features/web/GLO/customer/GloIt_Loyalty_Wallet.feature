@loyaltyGloIT
Feature: Loyalty feature - Wallet Order Management - GLO IT

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Order Management Purchase and Enable Dashboard
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    When user should be able to see Insiders Club menu option
    Then User clicks on Insiders Club menu option of IT
    And user switch into insider club frame
    And I can see credit balance '15' is displayed in the dashboard

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Order Management Purchase and spend points partially
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    And empty basket
    And Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    And user enter '4.00' credit to use from insider club
    And Glo user navigate to 'insiderClubUrl.key' page
    And user switch into insider club frame
    And I can see credit balance '15' is displayed in the dashboard

 #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Order Management earn and spend points (AC1,AC2)
    And user signs in with customer properties 'loginLoyaltyEmail.key' 'loginLoyaltyOptedPassword.key'
    And empty basket
    When users clicks on the 'gloShopLinkText.key' text link
    And clicks on a "configurable" product
    And select colour for configurable product
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    And user choose shipping method
    And user select Card Payment option
    And user select credit card as payment option
    And user select the Visa card as type
    And enter visa card details
    Then select place order from Card
    When Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    When user enter '4.00' credit to use from insider club
    And user select Card Payment option
    And user select credit card as payment option
    And user select the Visa card as type
    And enter visa card details
    Then select place order from Card
    And grab and output Order number
    And users clicks on order number to be taken to order view order page
    Then user see the applied insider credit in order summary
    When Glo user navigate to 'insiderClubUrl.key' page
    And user switch into insider club frame
    And Loyalty user should receive a order confirmation email with order number

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Purchasing both loyalty only and simple products
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    And empty basket
    When Glo user navigate to 'shopUrl.key' page
    And clicks on a "configurable" product
    And select colour for configurable product
    Then added product is visible in basket
    When users clicks on the 'gloShopLinkText.key' text link
    And click on first result for Loyalty
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    When user enter '3.00' credit to use from insider club
    Then assert text of 'creditSummary.key' is displayed

 #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Order Management spend points partially and check Cart
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    And empty basket
    And Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    And user enter '3.00' credit to use from insider club
    When Glo user navigate to 'cartPage.key' page
    Then assert text of 'creditSummary.key' is displayed
    And assert applied credit amount '-3,00 €' is deducted from item price in Cart
    When delete insider club credit amount from Cart summary
    Then assert success message is displayed for removing credit in Cart

 #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Order Management spend points and pay for whole order
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    And empty basket
    And Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    When user enter '4.99' credit to use from insider club
    And Glo user navigate to 'cartPage.key' page
    Then assert text of 'creditSummary.key' is displayed
    And assert user has paid for whole order in Cart