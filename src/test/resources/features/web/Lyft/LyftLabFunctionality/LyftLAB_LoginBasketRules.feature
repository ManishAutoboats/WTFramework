#This feature - Verify Login Rules to add products on Lyft Lab Landing page
  #for review / purchase LAB products. - Confirmation on the missing text on loginOrRegisterToBuy button
Feature: Login Rules to add LAB Products to Basket

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page

#  @LyftLive @LyftRegression - descoped following discussion with prerit/jai prakash 1/3/2022
  Scenario: PLP - Login or Register to Buy and Add to Cart CTA, Login with existing user and add to basket
    When user clicks on See The Flavors link from Block 1
    And assert text of buy button on basis of login rule applied for guest user
    Then user logs in from PLP when login rule is enabled
    Then empty basket for Lyft Lab Site
    Then user clicks on Add To Cart button to Buy from PLP when login rule is disabled

#  @LyftLive @LyftRegression - descoped following discussion with prerit/jai prakash 1/3/2022
  Scenario: PLP - Login or Register to Buy and Add to Cart CTA, for a guest user and add to basket
    When user clicks on See The Flavors link from Block 1
    And assert text of buy button on basis of login rule applied for guest user
    Then user clicks on Login or Register to Buy from PLP when login rule is enabled
    Then user clicks on Login or Register to Buy from previous collection when login rule is enabled
    Then user clicks on Add To Cart button to Buy from PLP when login rule is disabled

  Scenario: PLP - Login or Register to Buy CTA, Register with a new user and add to basket
    When user clicks on See The Flavors link from Block 1
    Then verify user is directed to Lab Products Collection on the same page
    And assert text of the button 'loginOrRegisterToBuyText.key' before login
    And user clicks on Login or Register to Buy from PLP
    And create a new account from PLP
    And user navigates to Lyft LAB landing page
    And user clicks on See The Flavors link from Block 1
    Then assert text of the button 'AddToCartText.key' after login
    And user clicks the person icon -lyft
    And user deletes the account and verify deletion success page

  Scenario: PDP - Login or Register to Buy CTA, Login with existing user and add to basket
    When user clicks on See The Flavors link from Block 1
    Then verify user is directed to Lab Products Collection on the same page
    And click first Lyft Lab product
    Then assert text of the button 'loginOrRegisterToBuyText.key' before login
    And user clicks on Login or Register to Buy from PLP
    And login with valid details on Lyft Lab site
    And user is redirected to PDP page for the selected product
    Then assert text of the button 'AddToCartText.key' after login





