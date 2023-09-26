#@gloDeRegression --Not Applicable for Glo De
Feature: Add products to wishlist feature

  Background: Navigate to Glo Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: Add to wishlist from PLP and verify in My Account page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'favouriteText.key' text link
    And click remove product from favorites button
    And Glo user clicks on buy button link and click on submenu
    When click add to favorites
    Then assert text of 'productAddWishlistSuccessMsg.key' is displayed
    When user clicks the person icon
    And users clicks on the 'favouriteText.key' text link
    Then assert URL contains text 'favouriteURL.key'
    And assert product is added to wishlist

  Scenario: Add to wishlist from PDP
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'favouriteText.key' text link
    And click remove product from favorites button
    And Glo user clicks on buy button link and select first link from SubMenu
    And click on first result
    When click add to favorites
    Then assert URL contains text 'favouriteURL.key'
    And assert text of 'productAddWishlistSuccessMsg.key' is displayed

  Scenario: Wishlist button not present for Guest user in both PLP and PDP
    And Glo user clicks on buy button link and select first link from SubMenu
    Then assert favorites button is not present
    And click on first result
    Then assert favorites button is not present