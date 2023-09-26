Feature: Velo My Account Page

  Background: Navigate to Velo Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on PersonIcon and Navigate to the Login Page
    And create a new account
    And users clicks on the my account menu dropdown

  @veloDEReg
  Scenario: Address look up -Loqate |My Account - Add new Address
    Then users clicks on the 'DeliveryAddress.key' text link
    And url contains 'addressURL.key' text
    And click on my account new address button
    And populate address fields
    And click save button
    And validation success message displayed

  @veloDEReg
  Scenario: Address look up -Loqate |My Account - Edit Address
    Then users clicks on the 'DeliveryAddress.key' text link
    And user clicks on Edit Billing Address Link
    And populate address fields
    And click save button
    And validation success message displayed

  @veloDEReg
  Scenario: Checkout - Delivery Address Section - Add New and Edit address
    When user clicks on product menu
    And get all lists from page
    Then user Clicks on add to cart button
    And click on basket icon
    And Go to checkout page
    And click add new address button
    And user add address on payment page
    And click the save address button
    And users clicks on Edit Address link
    And Enter details manually on Delivery address Edit window
    And click the save address button

  @veloDEReg
  Scenario: My Account Delete Account option and Verify
    And users clicks on the 'deleteMyAccountLink.key' text link
    Then url contains 'deleteMyAccountURL.key' text
    And proceed to delete customer account
    And user clicks the person icon
    And Login again with newly created user in same session
    And assert text of 'incorrectSignInMsg.key' is displayed
    And create a new account
    And validation success message displayed

  @veloDEReg
  Scenario: Checkout - Add Delivery Address Section Manually-Add New and Edit address
    When user clicks on product menu
    And get all lists from page
    Then user Clicks on add to cart button
    And click on basket icon
    And Go to checkout page
    And click add new address button
    And user add 'streetAndHouseNumber.key' 'city.key' 'postal.key' 'telephone.key' address manually
    And click the save address button
    And users clicks on Edit Address link
    And Enter details manually on Delivery address Edit window
    And click the save address button