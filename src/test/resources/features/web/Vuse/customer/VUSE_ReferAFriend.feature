
Feature: BAT - Vuse - Invite/Refer A Friend feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDEReg
  Scenario: Refer a Friend Landing Page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'referFriendLink.key' link
    And assert user is navigated to 'referFriendPageTitle.key' Invite Your Friends page
    And assert steps followed to invite a friend
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    And assert CTA for Copy link and Email Link on the page
    Then assert error message when logged-in user tries to access the referrer link

  @VuseDEReg
  Scenario: Invite a Friend to Redeem Voucher Code and assert applied discount in Basket
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'referFriendLink.key' link
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    Then attempt to logout
    When create a new account
    Then new user accesses the referrer link and successful message is displayed
    Then assert "robots" meta data tag content
    And user navigates to PLP page and adds a product to basket
    And click on basket icon
    And user increase the quantity of the selected item by '3'
    And user clicks on the View Basket cta
    Then assert discount applied in basket using the redeemed voucher code
    And Customer makes a home delivery purchase with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header

  @VuseDEReg
  Scenario:  RAF via referral link - create account at checkout - verify voucher
    And create a new account via api
    And user clicks on PersonIcon and Navigate to My Account Page
    Then users clicks on the 'referFriendLink.key' link
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    Then attempt to logout
    Then new user accesses the referrer link and successful message is displayed
    When user clicks on shop now button
    Then user should be landed on 'shopNowPage.key'
    And user adds a product to basket
    And click on basket icon
    And user increase the quantity of the selected item by '3'
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And user clicks the registration button
    And complete registration details and create account
    Then assert discount applied in basket using the redeemed voucher code
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    Then assert that the referrer received the voucherDiscountText.key voucher code in mailinator

  @VuseZAReg
  Scenario: Invite a Friend to Redeem Voucher Code and assert applied discount in Basket VuseZA
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'referFriendLink.key' link
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    Then attempt to logout
    Then new user accesses the referrer link and successful message is displayed
    When create new account for invite
    Then assert "robots" meta data tag content
    Then capture the referal coupon code
    And search and checkout product "reviewapprovedProduct.key"
    Then return to basket page
    And search and checkout product "searchTermVype.key"
    Then return to basket page
    Then apply discount code and verify coupon applied

  @VuseZAReg
  Scenario: Send Referral link via whatsapp
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    When users clicks on the 'referFriendLink.key' link
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    Then navigate to whatsapp url after select share via whatsapp

  @VuseZAReg
  Scenario: 962798 When the invitee uses the invited link then the invitation page is displayed
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'referFriendLink.key' link
    And user copys referrer link
    And attempt to logout
    And new user accesses the referrer link url
    Then your invitation page is displayed
