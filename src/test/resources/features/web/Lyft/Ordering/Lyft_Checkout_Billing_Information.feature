##This is the Checkout Billing information
Feature: BAT checkout billing information

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then fetch First and Last Names of the logged-in user
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on basket icon
    And user clicks on checkout button
    Then Payment page details confirmed

  @LyftSmoke  @LyftLive @LyftDKLive @LyftRegression @checkout @LyftDKReg
  Scenario: 9608 Ordering - Check Out - Billing Information
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    Then assert billing and shipping are the same tick box present

  @LyftLive @LyftDKLive @LyftRegression @checkout @LyftDKReg
  Scenario: 9607 15993 Ordering - Check Out - Change Address
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    Then assert new address button is displayed
    And click add new address button
    Then assert that the modal has a title

  #@LyftDKReg @LyftRegression
  Scenario: select Parcel Shop - and verify in Order view
    And enter select package shop details
    And fetch the selected parcel shop address
    Then user clicks on Shop on Map button under GLS Shipping Method
    And assert GLS Pop-up is displayed with GLS map inside
    Then user clicks on Continue button On GLS pop-up
    And assert GLS pop-up has closed
    And click on shipping method
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And users clicks on order number to be taken to order view order page
    Then assert delivery address is the selected parcel shop address in order view page

  #@LyftRegression2
  Scenario: Checkout - Logged-In - assert navigation and user remains logged-in on switching language using language selector
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert navigation to cart page with 'checkoutPageURLTextInEN.key' and response status code as 200
    Then assert that page title is 'checkoutPageTitleInEN.key'
    Then assert text is displayed in English after switching language
    And assert user remains logged-in after language switch
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language
    And assert user remains logged-in after language switch

  @LyftRegression
  Scenario: Checkout tests - not agreeing to Ts and Cs error message
    When Credit Card - MasterCard option and press next
    And enter mastercard details
    And select place order
    Then assert text of 'acceptTsAndCsErrorMessageText.key' is displayed

  @LyftRegression2
  Scenario: Checkout page - Invalid promotion code
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode 'invalidPromoCodeError.key' error message displayed