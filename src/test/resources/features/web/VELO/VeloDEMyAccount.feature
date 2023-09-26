Feature: Velo My Account Page

  Background: Navigate to Velo Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on PersonIcon and Navigate to the Login Page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the my account menu dropdown

  @veloDEReg
  Scenario: My Account - Change Billing address
    Then users clicks on the 'DeliveryAddress.key' text link
    And url contains 'addressURL.key' text
    And user clicks on Edit Billing Address Link
    And user add 'streetAndHouseNumber.key' 'city.key' 'postal.key' 'telephone.key' address manually
    And click save button
    And validation success message displayed

  @veloDEReg @veloDELive
  Scenario: My Account - Add New Address
    Then users clicks on the 'DeliveryAddress.key' text link
    And url contains 'addressURL.key' text
    And click on my account new address button
    And user add 'streetAndHouseNumber.key' 'city.key' 'postal.key' 'telephone.key' address manually
    And click save button
    And validation success message displayed

  @veloDEReg
  Scenario: My Account - Edit Address
    Then users clicks on the 'DeliveryAddress.key' text link
    And users clicks on Edit Shipping Address link
    And user add 'streetAndHouseNumber.key' 'city.key' 'postal.key' 'telephone.key' address manually
    And click save button
    And validation success message displayed

  @veloDEReg
  Scenario: My Account - Change Delivery address
    Then users clicks on the 'DeliveryAddress.key' text link
    And users clicks on Edit Shipping Address link
    And user add 'streetAndHouseNumber.key' 'city.key' 'postal.key' 'telephone.key' address manually
    And click save button after changing address under Address page
    And validation success message displayed