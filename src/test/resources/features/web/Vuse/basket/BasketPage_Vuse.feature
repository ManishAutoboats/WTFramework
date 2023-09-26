 Feature: 22658 Basket - VAT & shipping details
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link

  @VuseFRReg3 @VuseITReg @VuseITLive
  Scenario: Basket - VAT & shipping details, 979696 - Check tax rate and subtotal section
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And assert text of 'subTotalText.key' is displayed
    And assert text of 'summaryTitleText.key' is displayed
    And assert text of 'summaryTotalText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    And user selects proceed to checkout from cart page
    And login with valid details
    And Shipping page details confirmed

  @VuseFRReg2 @VuseITAnonReg2 @VuseITLive
  Scenario: Basket - Continue Shopping
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And user click on search icon and submits the following search term 'searchTerm.key'
    And search results title of 'searchResults.key' is returned
    And confirm mini-basket displayed amount of '1'

  @VuseUKReg2 @VuseZAReg2 @VuseFRReg2
  Scenario: Basket - Price includes taxes
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    Then verify product cost include taxes

  @VuseDEReg2
  Scenario: check tax rate is displayed, 979936 - Cart summary check
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And assert text of 'subTotalText.key' is displayed
    And assert text of 'summaryTitleText.key' is displayed
    And assert text of 'summaryTotalText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    
  @VuseMXReg @VuseMXLive
  Scenario: 966061 - Check flavours price in cart
    And search and add the product into the basket 'searchDeviceTerm.key'
    And each item in the basket has a name quantity image price and remove button

   @VuseFRReg3 @VuseITAnonReg3
   Scenario: check price increase in cart if we add more product
     Then verify the product and price added into the cart
     And search and add the product into the basket 'searchTerm.key'
     Then verify the product and price according to increase in product count
