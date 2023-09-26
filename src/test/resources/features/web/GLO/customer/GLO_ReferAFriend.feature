
Feature: BAT - GLO - Invite/Refer A Friend feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'referFriendLink.key' link

  @gloDeRegression
  #Add step(assert CTA via whatsapp should displayed and via email should not displayed)
  # to verify the VIA WHATSAPP AND VIA EMAIL CTA should displayed correctly,the issue is 926156
  Scenario: Refer a Friend Landing Page
    And assert CTA via whatsapp should displayed and via email should not displayed
    And assert user is navigated to 'referFriendPageTitle.key' Invite Your Friends page
    And assert steps followed to invite a friend
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    And assert CTA for Copy link and Email Link on the page
    Then assert error message when logged-in user tries to access the referrer link

  @gloDeRegression
  Scenario: Invite a Friend to Redeem Voucher Code and assert applied discount in Basket
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    Then attempt to logout
    Then create a new account via api
    Then new user accesses the referrer link and successful message is displayed
    And Glo user clicks on Shop link and click on 'glo™ TABAK HEATER' category link
    And click on first result
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    Then assert discount applied in basket using the redeemed voucher code
    And click on proceed to checkout button on mini cart
    And Payment page details confirmed
    And user choose shipping method
    And user select Card Payment option
    And user select credit card as payment option
    And user select the Visa card as type
    And enter visa card details
    Then select place order from Card
    And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed

  @gloDeRegression
  Scenario: Invite a Friend discount coupon is not applied on Neo Sticks products
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    Then attempt to logout
    Then create a new account via api
    Then new user accesses the referrer link and successful message is displayed
    And Glo user clicks on Shop link and click on 'neo™ STICKS' category link
    And click on first result
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    Then assert discount applied in basket is not displayed
    And click on basket icon
    Then assert discount applied in cart is not displayed

  @gloDeRegression
  Scenario: Refer a Friend Landing Page Steps Text
    And assert CTA via whatsapp should displayed and via email should not displayed
    And assert user is navigated to 'referFriendPageTitle.key' Invite Your Friends page
    Then assert text of 'referaFriendPageStepOneText.key' is displayed
    Then assert text of 'referaFriendPageStepTwoText.key' is displayed
    Then assert text of 'referaFriendPageStepThreeText.key' is displayed

  #automate issue 990994
  @gloDeRegression
  Scenario: Verify VIA WHATSAPP web can be access
    When click on via  whatsapp button
    And wait for the page to fully load
    Then assert URL contains text 'whatsappUrl.key'
    And assert CTA via whatsapp web page displayed correctly
