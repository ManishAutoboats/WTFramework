# NB Vuse DE you can't empty / remove products from the mini basket side panel
Feature: BAT Basket feature - Guest
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDEReg2 @VuseDELive @VuseUKReg2 @VuseITAnonReg2 @VuseITLive @VuseMXReg @VuseMXLive
  Scenario: Basket tests - Basket Icon presence
    Then ensure basket icon is displayed
    And click on basket icon
    And basket is empty message is presented

  @VuseDEReg2 @VuseDELive @VuseUKReg2 @VuseITAnonReg2 @VuseITLive @VuseMXReg @VuseMXLive
  Scenario: Basket tests - (from PDP)Populate Cart and assert header qty displayed on mini basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'

  Scenario: Basket tests - (from PLP)Populate Cart and assert header qty displayed on mini basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'

  @VuseDEReg2 @VuseDELive
  Scenario: VUSEDE 9554 Basket Page (cart page) confirm product present
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    And user closes the alert if present

  @VuseITAnonReg2
  Scenario: 9554 Basket Page (cart page) confirm product present
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'

   @VuseITAnonReg2 @VuseMXReg @VuseFRReg2
  Scenario: Basket Page (cart page) confirm product present and empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    And empty basket
    And click on basket icon
    And assert text of 'basketCurrentlyEmptyMsg.key' is displayed

  @VuseITAnonReg2 @VuseITLive
  Scenario: Basket - empty basket Guest populates basket then removes items from basket and asserts basket is empty
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    Then empty basket
     And click on basket icon
    And basket is empty message is presented


  @VuseUKReg2 @VuseDEReg2 @VuseFRReg2 @ITReg @VuseDELive @VuseUKLive2 @VuseFRLive @ITLive @VuseITLive @VuseZAReg2 @COReg @VuseITAnonReg3
  Scenario: Remove SKU from basket on Mini Cart
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty

  @VuseUKReg2 @VuseDEReg2 @VuseFRReg2 @ITReg @VuseDELive @VuseUKLive2 @VuseFRLive @ITLive @VuseITLive @VuseZAReg2 @COReg
  Scenario: Remove SKU from basket on Basket page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty

  #@VuseFRReg2 - This is going live with RT77 but currently in Disabled mode, will uncomment later today
  Scenario: Free Gifts added to Basket right at the top
    And user click on search icon and submits the following search term 'searchProductWithFreeGift.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then assert free gift is added at top right on the basket page

  @VuseDEReg2 @VuseDELive
  Scenario: E Liquids-CLP Healthwarning before purchase due to Legal reasons
    And user navigates to 'E-Liquids' PLP page and adds a product to basket
    And assert 'back button' is displayed
    And assert 'continue to cart' is displayed
    And user bypass the CLP healthwarning popup
    Then confirm mini-basket displayed amount of '1'

  #@VuseDEReg2(functionality is going live in disabled state and will enable after 80.1 live release)
  Scenario: E Cigarette-CLP Healthwarning before purchase due to Legal reasons
    And user navigates to 'E-Zigaratten' PLP page and adds a product to basket
    And assert 'back button' is displayed
    And assert 'continue to cart' is displayed
    And user bypass the CLP healthwarning popup
    Then confirm mini-basket displayed amount of '1'

  @VuseFRReg2
  Scenario: Bug 832195 - After first product add to cart PLP page hang
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    Then select first product color or strength on PLP
    And from PLP click add to cart
    And click back button on view basket
    Then verify PLP page is scrollable

  @VuseZAReg2
  Scenario: Adding 2 similar personalized product will have different tiles in basket
    And user click on search icon and submits the following search term 'reviewapprovedProduct.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    Then confirm mini-basket displayed amount of '1'
    Then confirm basket displayed 1 tiles
    And click add to cart button
    Then confirm mini-basket displayed amount of '2'
    Then confirm basket displayed 2 tiles

  @VuseUKReg2 @VuseFRReg2 @VuseZAReg2 @ITReg @COReg
  Scenario: Quantity Selector switch to free text on max value selection and switch back to dropdown on Mini Cart
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And select product by index "productIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    Then user selects maximum quantity available in Quantity dropdown on mini cart
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '16' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '16'
    Then user enters quantity '6' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '6'

  @VuseDEReg2
  Scenario: Vuse DE - Quantity Selector switch to free text on max value selection and switch back to dropdown on Mini Cart
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then user selects maximum quantity available in Quantity dropdown on mini cart
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '22' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '22'
    Then user enters quantity '6' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '6'

  @VuseUKReg2 @VuseFRReg2 @VuseZAReg2 @ITReg @COReg
  Scenario: Quantity Selector switch to free text on max value selection and switch back to dropdown on Basket page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And select product by index "productIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '16' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '16'
    Then user enters quantity '8' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '8'

  @VuseUKReg2
  Scenario: 990549 - Maximum quantity message check on Basket page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And select product by index "productIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '10001' in the mini-cart text-field and update
    And assert quantity exceeded error message 'QtyExceededError.key' is displayed in basket page

 @VuseDEReg2
  Scenario: Vuse DE - Quantity Selector switch to free text on max value selection and switch back to dropdown on Basket page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '22' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '22'
    Then user enters quantity '8' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '8'

  @VuseZAReg2
  Scenario: Basket - Persistence - Guest to Logged in item remains in basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And click on the logo
    And create a new account via api and log in with the account
    Then confirm mini-basket displayed amount of '1'

  # automate back log bug 866079
  @VuseDEReg2
  Scenario:  Verify product quantity displayed correctly in title
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And assert quantity number is '1' in  title
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    Then user enters quantity '8' in the mini-cart text-field and update
    And assert quantity number is '8' in  title


  @VuseFRReg2
  Scenario: Adding 2 similar personalized product will have different tiles in basket VuseFR
    And user click on search icon and submits the following search term 'searchDeviceEPOD.key'
    And get all lists from page
    And select product strength or colour
    And click add to cart button
    Then confirm mini-basket displayed amount of '1'
    Then confirm basket displayed 1 tiles
    And user click on search icon and submits the following search term 'searchDeviceEPOD.key'
    And get all lists from page
    And select product strength or colour
    And click add to cart button
    Then confirm mini-basket displayed amount of '2'
    Then confirm basket displayed 2 tiles

  # automate back log bug 954007
  @VuseDEReg2
  Scenario: Valid message displayed when user add product with Vuse Reload AB
    When user navigates to PLP page
    And select a subscription product on PLP and add to cart
    And click on basket icon
    Then add product to basket success message is displayed on PLP

  @VuseUKReg2 @VuseFRReg2 @VuseUKLive2 @VuseFRLive
  Scenario: Vuse- Cross-sell component in mini-basket
    And user click on search icon and submits the following search term 'confiduredProduct.key'
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And assert Cross-sell products section at minicart is displayed
    And user select cross-sell product and verify PDP of the selected product is opened
    And click on basket icon
    And user click on add to cart cta of cross sell product and verify product is added into the cart

  @VuseFRReg2
  Scenario: Basket - empty basket with message and homepage link
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    Then empty basket
    And basket with empty message and homepage button is present and redirected

  @VuseMXReg
  Scenario: 988606 - VUSE MX change Qty into max value in mini-basket pop-up window
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And select product by index "productIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    Then user enters quantity '100001' in the mini-cart text-field and update
    And assert user click accept button to close exceeds the maximum qty notification window

  @VuseITAnonReg3
  Scenario: Vuse IT - Remove SKU from basket on Basket page
    Given user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty
    And user wait for home page to load
    And user click on home page link to return to home page
    Then assert URL contains text 'homeUrl.key'
