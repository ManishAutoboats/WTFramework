Feature: BAT Basket Persistence feature - Logged In
    Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket

   @VuseUKReg @VuseFRReg @VuseITReg @VuseMXReg
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

  @VuseUKReg @VuseFRReg @VuseITReg @VuseMXReg
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

#  @VuseUKReg removed tag as it has been invalid according to #533595
    Scenario: Basket-one time purchases and subscription items together are not allowed
    And user click on search icon and submits the following search term 'oneTimePurchaseProduct.key'
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

   @VuseZAReg
   Scenario: Basket - Shipping Bar message - Free shipping price Countdown vuseza
     Then assert shipping bar message displayed on top of the page
     And fetch free shipping threshold price from the message displayed
     And user click on search icon and submits the following search term 'searchTerm.key'
     And get all lists from page
     And click on first result
     And select product strength or colour
     When click add to cart button js
     Then click back button on view basket
     And fetch the product price
     Then assert shipping bar msg update
     And increases the quantity of selected product item by '4'
     Then assert shipping bar msg update

  @VuseITReg
  Scenario: 979696 - Check tax rate and subtotal section
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And assert text of 'subTotalText.key' is displayed
    And assert text of 'summaryTitleText.key' is displayed
    And assert text of 'summaryTotalText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    
    
    
    
    
    
    
    
    
    