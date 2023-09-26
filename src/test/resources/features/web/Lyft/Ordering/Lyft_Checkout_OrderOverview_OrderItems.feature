Feature: BAT checkout - Order items in order overview

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @LyftRegression2
  Scenario: checkout - verify order items displayed at order overview
    And create a new account via api
    And user clicks the person icon -lyft
    And user login with credentials registered via api
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on basket icon
    And user clicks on checkout button
    Then Payment page details confirmed
    Then user selects Items in Cart section under Order Overview section
    And assert that product item displayed in checkout Order Overview is same as product added to cart
