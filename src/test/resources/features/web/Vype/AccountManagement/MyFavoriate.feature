# This is the editing customer account details story
# This covers BUG - 31562
#@regression @accountDashboard #@smokeLite @favorites @MXReg @NonCaptchaLive
  # This story has been deprecated
Feature: BAT Account management - My Favorites
  Background: 9597 - Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  #@ITLive - Commented for Vype IT due to known open defect 266662
   Scenario: Guest - PDP elements present as expected and add to Favorites - promoted to login
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    Then click add to favorites
    And assert text of 'loginOrRegAddToItemWishlistErrorMsg.key' is displayed
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
   # And assert added to wish list message is displayed to user
    And assert text of 'favouriteText.key' is displayed
    And assert text of 'likeBestproductText.key' is displayed
    And click remove product from favorites button
    And assert text of 'NotAddedProductInFavMsg.key' is displayed
    And users clicks on the 'logoutText.key' text link

  #@ITLive - Commented for Vype IT due to known open defect 266662
  Scenario: My Favorites - no favourites message present
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And users clicks on the 'favouriteText.key' text link
    And url contains 'favouriteURL.key'
    And click remove product from favorites button
    And assert text of 'NotAddedProductInFavMsg.key' is displayed

  @dk @dklive @VuseDKReg @VuseDKLive
  Scenario: Verify Favourites Functionality Removed
    And assert favorite icon is not present on header
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And assert 'favouriteText.key' link is not present on Account Dashboard Menu