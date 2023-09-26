                                                                                                                                                      #This is the Basket feature file
  ##Testing
    ## Presence of Basket icon
    ## Click on basket
      ## verify populated
      ## verify empty

Feature: 22658 Basket - VAT & shipping details

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
  #  And attempt to logout
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link

  @smokeLite  @basket @fr @dk @de @nl @ITLive @IEReg @COReg @VuseDKReg @ITReg2
  Scenario: 22658 13840 Basket - VAT & shipping details
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    And user selects proceed to checkout from cart page
    And login with valid details
    And Shipping page details confirmed

  @smokeLite  @basket @fr @dk @de @nl @ITReg2 @ITLive @IEReg @COReg @VuseZAReg2 @VuseDKReg @VuseDKLive @VuseZALive
  Scenario: 9553 Basket - Continue Shopping
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And user click on search icon and submits the following search term 'searchTerm.key'
    And search results title of 'searchResults.key' is returned
    And confirm mini-basket displayed amount of '1'

    @VuseZAReg2
    Scenario: Delete Product from the basket by using the remove button
      And div cart summary pane is displayed
      And delete product from remove button
      And verify mini cart amount 'Amount.key'

  @VuseZAReg2
  Scenario: DEFECT 874163 22658 13840 Basket - VAT & shipping details vuseZA
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    And user selects proceed to checkout from cart page
    And login with valid details
    And Shipping page details confirmed
