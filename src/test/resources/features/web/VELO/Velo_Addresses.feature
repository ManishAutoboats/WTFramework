Feature: Velo Registration Page

Background: Navigate to Velo Home Page
  Given user navigates to BAT home page
  And select allow all from cookie popup and select over 18 age confirmation option
  And user click on PersonIcon and Navigate to the Login Page

  @veloDEReg
  Scenario: Checkout - Payment Method - Add new Address
    When create a new account
    And user clicks on product menu
    And get all lists from page
    And user Clicks on add to cart button
    And click on basket icon
    And Go to checkout page
    And Add a new billing address
    Then assert new billing address has been added 'streetAndHouseNumber.key' 'city.key' 'postal.key'
    And Edit newly added billing address 'updatedStreetAndHouseNumber.key' 'updatedCity.key' 'updatedPostal.key'
    Then assert new billing address has been added 'updatedStreetAndHouseNumber.key' 'updatedCity.key' 'updatedPostal.key'

  @veloDEReg
  Scenario: Account Registration - Add address from any Country
    Then assert default county or land is 'country.key'
