Feature: BAT Engraving feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @COReg  
  Scenario: Presence of personalisation badge
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    Then assert product is personalizable

  @COReg  
  Scenario: Presence of personalisation tab features on PDP
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    Then assert personalizable tab is visible
    Then assert personalisation-options is available
    Then assert link of personalize back-only is available
    Then assert link of cancel button is available on personlize serivce

  @COReg  
  Scenario: Mini cart trolly displays all the the engraving details
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "icon" on front
    And select first visible engraving patternOrIcon
    And user clicks on add and continue button "icon"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    And click add to cart button
    Then verify mini cart basket displays the engraving details

  @COReg  
  Scenario: View cart page display the engraving details
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "icon" on front
    And select first visible engraving patternOrIcon
    And user clicks on add and continue button "icon"
    And user add text on the "back" of the device with "vertical" orientation
    And user select the number '1' font
    And user clicks on add and continue button "back"
    And click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then assert view cart page displays the engraving details

  @COReg  
  Scenario: Remove engraving service on summary page and assert default product price
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "icon" on front
    And select first visible engraving patternOrIcon
    And user clicks on add and continue button "icon"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    And user remove the selected engraving service for both front and back
    Then assert displaying a alert message for removal of engraved service

    
  Scenario: pdp-personalisation-panel-price visible
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    Then assert pdp-personalisation-panel is displayed
    
  Scenario:Price breakdown for prodcut and engraving are displayed and correct
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    Then assert pdp-personalisation-panel icon tooltip is displayed
    And user clicks on pdp-personalisation-panel icon tooltip
    Then assert total price is correct and price-breakdown popup has engraving charges

  @COReg  
  Scenario: Verify cancel button is present on all the pages
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    Then user clicks on the mentioned pagelink and verify cancel-link is displayed
      |frontTab.key|
      |backTab.Key|
      |summaryTab.Key|

  @COReg  
  Scenario: Verify clicking on the cancel button redirects the user out of the engraving feature
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    Then user clicks on the mentioned pagelink and verify clicking on the cancel button redirects the user out of the engraving
      |backTab.Key|
      |summaryTab.Key|
      |frontTab.key|

  @COReg  
  Scenario: Assert color swatch options are available on all the pages
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    Then user clicks on the mentioned pagelink and verify color swatch tab is displayed
      |frontTab.key|
      |backTab.Key|
      |summaryTab.Key|

  @COReg  
  Scenario: Verify don't customize front and back functionality and assert message on Summary page
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And user skips front personalisation
    And user skips back personalisation
    Then assert displaying a alert message for removal of engraved service

  @COReg  
  Scenario: Verify error validation message for using more letters than defined limit
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "text" on front
    And user add text on the "front" of the device with "vertical" orientation
    And user clicks on horizental tab of engraving
    Then assert error validation message for using more letters than defined limit

  @COReg  
  Scenario: verify validation message for restricted words
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "text" on front
    Then user add text on the device with "restrictedWords" and assert error message

  @COReg  
  Scenario: Verify back button is present on back side and summary page of engraving
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    Then user clicks on the mentioned pagelink and verify back-button is displayed
      |backTab.Key|
      |summaryTab.Key|

  @COReg  
  Scenario Outline: Verify back button is present on all page of front side of engraving
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving '<OptionToChoose>' on front and verify back button on '<chosenOption>'
    Examples:
      |OptionToChoose|chosenOption|
      |   pattern   | pattern  |
      |   icon    | icon   |
      |   text    | text   |

  @COReg
  Scenario: Remove engraving service on summary page and assert add to cart button is disbaled
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "icon" on front
    And select first visible engraving patternOrIcon
    And user clicks on add and continue button "icon"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    And user remove the selected engraving service for both front and back
    Then assert add to cart button is disabled

  @COReg
  Scenario: Verify AddToCart CTA is disabled by default for Engravable product
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    Then assert add to cart button is disabled

  @COReg
  Scenario: Verify ViewProduct CTA is displayed on PLP page for Engravable product
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct2'
    Then assert personalized product has ViewPorduct CTA

  @COReg  
  Scenario: Pattern(front) and text(back) selection, add to basket and proceed to checkout
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "pattern" on front
    And select first visible engraving patternOrIcon
    Then assert pattern is selected on the image
    And user clicks on add and continue button "pattern"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    And Customer makes a home delivery purchase with "mastercard" from PDP page
    Then assert text of 'ThankForPurchase.key' is displayed

  @COReg
  Scenario: Text(front) and text(back) selection, add to basket and proceed to checkout page
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "text" on front
    And user add text on the "front" of the device with "vertical" orientation
    And user clicks on add and continue button "text"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    And Customer makes a home delivery purchase with "mastercard" from PDP page
    Then assert text of 'ThankForPurchase.key' is displayed

  @COReg
  Scenario: Icon(front) and text(back) selection, add to basket and proceed to checkout page
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "icon" on front
    And select first visible engraving patternOrIcon
    And user clicks on add and continue button "icon"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    And Customer makes a home delivery purchase with "mastercard" from PDP page
    Then assert text of 'ThankForPurchase.key' is displayed

  @COReg
  Scenario: Edit choice(Front), add to basket and proceed to checkout page
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "icon" on front
    And select first visible engraving patternOrIcon
    And user clicks on add and continue button "icon"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    And user clicks on edit front end choice
    And user choose to edit chosen patternOrIcon
    And user clicks on add and continue button "icon"
    And user clicks on add and continue button "back"
    And Customer makes a home delivery purchase with "mastercard" from PDP page
    Then assert text of 'ThankForPurchase.key' is displayed
    And users clicks on order number to be taken to order view order page

  @COReg
  Scenario: Verify Engraving details remain intact after a guest user register on checkout page
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'
    And user click to opt personalizable service
    And select engraving "pattern" on front
    And select first visible engraving patternOrIcon
    And user clicks on add and continue button "pattern"
    And user add text on the "back" of the device with "vertical" orientation
    And user select the number '1' font
    And user clicks on add and continue button "back"
    And click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then assert view cart page displays the engraving details
    And user clicks on checkout button
    And click on proceed to checkout for guest user
    And select create new account from checkout PopUp
    And create a new account for guest
    Then assert checkout page displays the engraving details
    When user navigates to my account page from header
    And user selects deleteMyAccount link on my account Page

     #@gloItRegression This functionality is going in disable mode and will uncomment once it is enabled
  Scenario: Glo IT Engraving
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And Glo user clicks on buy button link and click on submenu
    And user click on first engraving product
    And user click to opt personalizable service
    And select engraving "pattern" on front
    When user selects a Motif pattern and assert selection
    And user clicks on add and continue button "pattern"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And Payment page details confirmed