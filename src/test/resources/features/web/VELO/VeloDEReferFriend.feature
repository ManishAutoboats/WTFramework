
Feature: BAT - Velo- Invite/Refer A Friend feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on PersonIcon and Navigate to the Login Page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the my account menu dropdown
    Then users clicks on the 'referFriendLink.key' text link

  @veloDEReg @veloDELive
  Scenario: 551702 verify Refer a Friend Landing Page element
    And assert user is navigated to 'referFriendPageTitle.key' Invite Your Friends page
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    And assert CTA for Copy link and Email Link on the page

  @veloDEReg
  Scenario: Invite a Friend to Redeem Voucher Code and assert applied discount in Basket
    And assert referrer link appended with voucher code 'referrerLink.key' in text field
    And user click on PersonIcon and Navigate to the Login Page
    And users clicks on the my account menu dropdown
    Then users clicks on the 'logOut.key' text link
    Then new user accesses the referrer link and successful message is displayed
    When user clicks on product menu
    And get all lists from page
    Then user Clicks on add to cart button
    And click on basket icon
    And assert text of 'Rabatt' is displayed on page
    And Go to checkout page
    When create a new account
    And validation success message displayed
    And click on basket icon
    And Go to checkout page
    And Enter "mastercard" card details
    And select place order from Card
    And Order Confirmation page thankYouMessageHeading is displayed


