@smokeVypeAdminFast
Feature: Vype - place order and check order in admin

  Background: Navigate to BAT Home Page

  Scenario: Admin prereq c:- Checkout tests - Signed In - Credit Cards - Master Card - Debit Card Option - Payment for Vype
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    When user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    #And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    And enter select package shop details
    And selectable shipping options displayed
    And click on shipping method
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And click save card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button to move head and place the order
    And assert print receipt link is present
    Then assert text of 'ThankForPurchase.key' is displayed
    And grab and output Order number

  Scenario:Admin prereq d:- Admin-Vype-Order search result is displayed
    Given user logins Magento Admin home page successfully
    When user navigates to order page
    And user searches order by search keyword
    Then search result is shown correctly