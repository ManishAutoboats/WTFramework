Feature: BAT Basket Persistence feature - Logged In
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket

  @sprint4 @9598 @frSmoke @basket @regression @dklive @fr @dk @de @nl @ITReg @IEReg @MXReg
  Scenario: 9533 9598 - Basket - Persistence - Guest to Logged in item remains in basket
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'logoutText.key' text link
    And click on the logo
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And click on the logo
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then confirm mini-basket displayed amount of '1'
    And empty basket

  @sprint4 @9598 @basket @regression @dklive @fr @dk @de @nl @ITReg @IEReg @VuseZAReg @MXReg
  Scenario: 9598 - Basket - Persistence - Logged in to Logged out item do not remain in basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And click on the logo
   	And user clicks the person icon
   	And user clicks on 'myAccountLink.key' link from Person Menu
    When users clicks on the 'logoutText.key' text link
    And click on the logo
    And user clicks the person icon
    And click on basket icon
    And basket is empty message is presented

  # Test is included in bug feature
  #Scenario: BUG - 34760 Basket - Update Qty
  #  And user click on search icon and submits the following search term 'liquid'
  #  And get all lists from page
  #  And Click specific result of 'Vpure - Vype eLiquid Bottle'
  #  And testingBreakpoint
  #  And select product colour
  #  Then click add to cart button js
  #  And click on basket icon
  #  And users clicks on the 'View Basket' text link
  #  And update mini-basket qty to '4'
  #  Then confirm mini-basket displayed amount of '4'
  #  And testingBreakpoint

  @LyftRegression
  Scenario:  Basket - Cross-Sell Products Section
    Given user logins Magento Admin and assert Related,Up-Sell and Cross-Sell Products Configuration
    And user navigates to BAT home page for FE validation
    And empty basket before adding related products
    And user click on search icon and submits the following search term 'searchRelatedProdTerm.key'
    Then user clicks on the first link if navigated to PLP page and add to cart
    And users clicks on the 'viewBasketText.key' text link
    And assert Cross Sell Products section and carousal on the Cart page
    And assert Add To Cart button is displayed for Cross Sell Products and assert CTA

  @regression
  Scenario: Basket-one time purchases and subscription items together are not allowed
    And user click on search icon and submits the following search term 'searchTermEpod.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And user clicks on One Time Purchase
    And click add to cart button
    And user clicks on Subscribe and Save
    And click add to cart button
    And assert message about basked is displayed
    And remove one time purchase product
    And user increases the product quantity for Subscriptions if error appears
    And Payment page details confirmed