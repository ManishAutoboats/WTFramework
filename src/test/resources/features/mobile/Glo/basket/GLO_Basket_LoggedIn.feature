Feature: BAT Basket feature - Logged In - Mobile
Background: Navigate to BAT Home page 
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: Basket - Price includes taxes GLO IT
    Given Glo user clicks on PersonIcon and Navigate to the Login Page
    And user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user adds product to cart and navigates to checkout
    And user click on mini cart wrapper button from checkout on mobile
    Then assert text of 'taxRateText.key' is displayed
#   Removed below step due to #866544
#   And verify product cost include taxes