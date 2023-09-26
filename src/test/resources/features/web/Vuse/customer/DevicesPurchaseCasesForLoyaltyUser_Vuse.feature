
  ######################################################################################################################
  # These scripts will not run on local chrome
  # But can be executed on Firefox - Application has Loyalty dashboard iframe loading issue (Takes a bit longer to load)
  # User should execute the scripts on browserstack Chrome
  ######################################################################################################################

Feature: Loyalty feature when user purchases epen and epod

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

#  @VuseDEReg
  Scenario: Validate Batch - Purchase Epen when user already have an Epod (Create New user with Loyalty)
    And create a new account with Loyalty
    And user click on search icon and submits the following search term 'EpodDevice.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user click on search icon and submits the following search term 'EpenDeviceCategory.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And click on 'Badges'
    Then assert 'enabledTileLabel.key' label

#  @VuseDEReg
  Scenario: Validate Batch - Purchase Epod when user already have an Epen (Create New user with Loyalty)
    And create a new account with Loyalty
    And user click on search icon and submits the following search term 'EpenDeviceCategory.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user click on search icon and submits the following search term 'EpodDevice.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And click on 'Badges'
    Then assert 'enabledTileLabel.key' label

#  @VuseDEReg
  Scenario: Validate Batch - Purchase Epod and Epen in the same order (Create New user with Loyalty)
    And create a new account with Loyalty
    And user click on search icon and submits the following search term 'EpenDeviceCategory.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And user click on search icon and submits the following search term 'EpodDevice.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And click on 'Badges'
    Then assert 'enabledTileLabel.key' label

#  @VuseDEReg
  Scenario: Validate Batch - Purchase Epod and Epen in the same order (Create New user with no Loyalty)
    And create a new account
    And user click on search icon and submits the following search term 'EpenDeviceCategory.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And user click on search icon and submits the following search term 'EpodDevice.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And User Sign Up for Loyalty program
    And click on 'Badges'
    Then assert 'disabledTileLabel.key' label

  @VuseDEReg2
  Scenario: Vuse DE - Loyalty - 2nd and 5th Flavour Badges
    And create a new account with Loyalty
    And user click on search icon and submits the following search term
      | loyalityProduct1.key |
      | loyalityProduct2.key |
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And user place the order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And click on 'Badges'
    And verify '2' badges is enabled
    And user click on search icon and submits the following search term
      | loyalityProduct3.key |
      | loyalityProduct4.key |
      | loyalityProduct5.key |
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And user place the order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And click on 'Badges'
    And verify '5' badges is enabled

  @VuseDEReg2
  Scenario: Vuse DE - Global Loyalty - Free Accessories for Customers in Explorer Tier
    And create a new account with Loyalty
    And user click on search icon and submits the following search term
      | loyalityePodProduct.key |
      | loyalityePenProduct.key |
    And click on basket icon
    And user clicks on the View Basket cta
    And assert message for a free skin and free capsule is displayed
    And user click on search icon and submits the following search term
      | loyalityFreeSkinProduct.key |
    And search and view basket for the product 'loyaltyFreeCapsuleProduct.key'
    And assert discount is applied against both free capsule and free skin items for 'Explorer' user
    And click on proceed to checkout from cart page
    And Payment page details confirmed
    And Credit Card - VISA order option and press next
    And enter visa card details
    And user place the order
    Then assert text of 'ThankForPurchase.key' is displayed

  @VuseDEReg2
  Scenario: Vuse DE - Global Loyalty - Free Accessories for Customers in Challenger Tier
    And create a new account with Loyalty
    And user click on search icon and submits the following search term
      | loyalityePodProduct.key |
    And user adds '5' item into the cart
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And Credit Card - VISA order option and press next
    And enter visa card details
    And user place the order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And assert user has been moved to 'Challenger' tier
    And user click on search icon and submits the following search term
      | loyalityePodProduct.key |
      | loyalityePenProduct.key |
    And click on basket icon
    And user clicks on the View Basket cta
    And assert message for a free skin and free capsule is displayed
    And user click on search icon and submits the following search term
      | loyalityFreeSkinProduct.key |
    And search and view basket for the product 'loyaltyFreeCapsuleProduct.key'
    And assert discount is applied against both free capsule and free skin items for 'Challenger' user

  @VuseDEReg2
  Scenario: Vuse DE - Global Loyalty - Free Accessories for Customers in Innovator Tier
    And create a new account with Loyalty
    And user click on search icon and submits the following search term
      | loyalityePodProduct.key |
    And user adds '10' item into the cart
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And Credit Card - VISA order option and press next
    And enter visa card details
    And user place the order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user navigate to my account page
    And User clicks on Insiders Club menu option
    And assert user has been moved to 'Innovator' tier
    And user click on search icon and submits the following search term
      | loyalityePodProduct.key |
      | loyalityePenProduct.key |
    And click on basket icon
    And user clicks on the View Basket cta
    And assert message for a free skin and free capsule is displayed
    And user click on search icon and submits the following search term
      | loyalityFreeSkinProduct.key |
    And search and view basket for the product 'loyaltyFreeCapsuleProduct.key'
    And assert discount is applied against both free capsule and free skin items for 'Innovator' user

  @VuseDEReg2
  Scenario: Create New Loyality Account,delete the account and create new user with deleted details
    And create a new account with Loyalty
    Then assert text of 'successRegistraionMsg.key' is displayed
    Then fetch First and Last Names of the logged-in user
    And fetch Email Address of the logged-in user
    And fetch DOB and Address Details of the logged-in user
    When user navigates to my account page from header
    And I delete customer account
    And user wait for home page to load
    When create a new loyality account with deleted user details
    Then verify New User's Email and navigate back to Account Page
    And assert text of 'successRegistraionMsg.key' is displayed

  @VuseDEReg2
  Scenario: Loyalty pop-up with 1st voucher code on registration from Insiders Club page
    When create a new account via api
    And user clicks on PersonIcon and Navigate to My Account Page
    Then assert first voucher loyalty pop up is not displayed for non loyalty user
    And User clicks on Insiders Club menu option
    Then non loyalty user registers for Loyalty on selecting the checkbox and clicking Register Now
    And assert first voucher loyalty pop up is displayed for loyalty user
    Then user clicks on discount code button from Loyalty pop-up and redirected to Loyalty dashboard
    And wait for the page to fully load
    And assert user has been moved to 'Explorer' tier
    And assert URL contains text 'insidersClubPageURL.key'
    And proceed to delete customer account
