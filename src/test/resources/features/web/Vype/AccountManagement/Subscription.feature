#This is the subscription feature
  ##Testing
    ## Navigate to My Account
    ## subscription link present
    ## navigate to subscription page
    ## perform available tests
#@regression
@smokeLite @subscriptions
Feature: BAT subscription feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu

  #@live
  Scenario: Subscription link present and subscript content present
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user is successfully logged in
    And user hovers over on the Vype Subscriptions link
    And wait for the page to fully load
    And assert text of 'HowItWorksText.key' is displayed
    #And ensure the expected page assets are loaded
    And assert text of 'SelectDeviceText.key' is displayed
    And assert text of 'SubsInstructions.key' is displayed
    And assert text of 'SubsDeliveryIncluded.key' is displayed
    And assert text of 'SubsPlanOptionsText.key' is displayed

  #Scenario: Defect 71406 - Subscriptions page title present test
  #  And assert that page title is 'Subscriptions'

  Scenario: 54434 Subscriptions purchase flow - mastercard
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user is successfully logged in
    And user hovers over on the Vype Subscriptions link
    And wait for the page to fully load
    And click subscriptions continue button
    And select first subscription product
    And assert text of 'SubsYourPlanText.key' is displayed
    And user selects flavours for subscription
    And assert Savings table is displayed
    And click agree to terms and conditions
    And click add to basket
    And click on checkout button
    #And user selects proceed to checkout from cart page
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And tick agree to terms and conditions
    And tick save card box
    And select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page

  Scenario: 54434 Subscriptions purchase flow - visa
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user is successfully logged in
    And user hovers over on the Vype Subscriptions link
    And wait for the page to fully load
    And click subscriptions continue button
    And select first subscription product
    And user selects flavours for subscription
    And assert Savings table is displayed
    And click agree to terms and conditions
    And click add to basket
    And click on checkout button
    #And user selects proceed to checkout from cart page
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And tick save card box
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And assert print receipt link is present
    And grab Order Number and assert Order Status on View Order Details page

  Scenario: 54434 Subscriptions purchase flow - saved card payment
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user is successfully logged in
    And user hovers over on the Vype Subscriptions link
    And wait for the page to fully load
    And assert text of 'SelectDeviceText.key' is displayed
    And assert text of 'SubsInstructions.key' is displayed
    And assert text of 'SubsDeliveryIncluded.key' is displayed
    And assert text of 'SubsPlanOptionsText.key' is displayed
    And click subscriptions continue button
    And select first subscription product
    And assert text of 'SubsYourPlanText.key' is displayed
    And user selects flavours for subscription
    And assert Savings table is displayed
    And click agree to terms and conditions
    And click add to basket
    And click on checkout button
    #And user selects proceed to checkout from cart page
    And select Card Payment option then credit Card option
    And click use saved card
    And tick agree to terms and conditions
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And assert print receipt link is present
    And grab Order Number and assert Order Status on View Order Details page

   #@live - Vype UK has stopped using subs functionality as they will switch to subs pro in coming weeks, Disabled until then
  Scenario: Subscriptions Checkout Flow
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user is successfully logged in
    And user hovers over on the Vype Subscriptions link
    And wait for the page to fully load
    And click subscriptions continue button
    And select first subscription product
    And assert text of 'SubsYourPlanText.key' is displayed
    And user selects flavours for subscription
    And assert Savings table is displayed
    And click agree to terms and conditions
    And click add to basket
    And click on checkout button
    #And user selects proceed to checkout from cart page
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And tick agree to terms and conditions
    And tick save card box

  @IEReg2
  Scenario: verify no subscriptions - add single subscription to basket, checkout - view and pause subscription in My Account
    And create a new account via api
    And user login with credentials registered via api
    And user clicks the person icon
    When user clicks on the link that contains 'CustomerSubscriptionsLink.key' href
    Then assert text of 'NoSubscriptionsText.key' is displayed
    When user selects the subscriptions as in table and add to basket then assert added message
      | SubscriptionProduct                     | NoOfMonths | NoOfPacks | Flavour                     | Quantity |
      | Vype ePen 3 Starter Kit Black vPro 12mg | 3 months   | 12 packs  | Master Blend, Classic Peach | 6, 6     |
    And click on checkout button
    And click on proceed to checkout button
    And user complete "VISA" payment details and click on place the order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    When user clicks on the link that contains 'CustomerSubscriptionsLink.key' href
    And click on subscription pause button
    Then assert text of 'SubscriptionPauseSuccessText.key' is displayed
    And delete the account via api

  @IEReg2
  Scenario: Guest add single subscription to basket, login and checkout
    And create a new account via api
    When user selects the subscriptions as in table and add to basket then assert added message
      | SubscriptionProduct        | NoOfMonths | NoOfPacks | Flavour                  | Quantity |
      | Vype ePod Device Kit Black | 3 months   | 12 packs  | Chilled Mint, Just Mango | 6, 6     |
    And click on checkout button
    And click on proceed to checkout button
    And user login on checkout pop up with credentials registered via api
    And user complete "VISA" payment details and click on place the order
    Then assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    When user clicks on the link that contains 'CustomerSubscriptionsLink.key' href
    And click on subscription pause button
    Then assert text of 'SubscriptionPauseSuccessText.key' is displayed
    And delete the account via api

  @IEReg2
  Scenario: verify subscriptions discount
    And create a new account via api
    And user login with credentials registered via api
    When user selects the subscriptions as
      | SubscriptionProduct                     | NoOfMonths | NoOfPacks | Flavour         | Quantity |
      | Vype ePen 3 Starter Kit Black vPro 12mg | 3 months   | 6 packs   | Infused Vanilla | 6        |
    And clicks continue button
    Then assert user gets "25" percent discount
    And click agree to terms and conditions
    And click add to basket
    When user selects the subscriptions as
      | SubscriptionProduct                     | NoOfMonths | NoOfPacks | Flavour         | Quantity |
      | Vype ePen 3 Starter Kit Black vPro 12mg | 6 months   | 6 packs   | Blended Tobacco | 6        |
    And clicks continue button
    Then assert user gets "33" percent discount
    And delete the account via api

  @IEReg2
  Scenario: User adds 2 or more subscriptions to basket
    And create a new account via api
    And user login with credentials registered via api
    When user selects the subscriptions as in table and add to basket then assert added message
      | SubscriptionProduct                     | NoOfMonths | NoOfPacks | Flavour             | Quantity |
      | Vype ePod Device Kit Black              | 3 months   | 12 packs  | Golden Tobacco      | 12       |
      | Vype ePen 3 Starter Kit Black vPro 12mg | 6 months   | 6 packs   | Original Strawberry | 6        |
    And delete the account via api

  @IEReg2
  Scenario: verify error pop and msg when user chosen flavour count is less and more than selected NoOfPacks
    And create a new account via api
    And user login with credentials registered via api
    When user selects the subscriptions as
      | SubscriptionProduct                     | NoOfMonths | NoOfPacks | Flavour         | Quantity |
      | Vype ePen 3 Starter Kit Black vPro 12mg | 3 months   | 6 packs   | Infused Vanilla | 1        |
    And clicks continue button
    Then assert error pop up is displayed and message "subsFlavourCountIsLessMsg.key"
    And click ok button on subs error pop up
    When user selects the subscriptions as
      | SubscriptionProduct                     | NoOfMonths | NoOfPacks | Flavour         | Quantity |
      | Vype ePen 3 Starter Kit Black vPro 12mg | 3 months   | 6 packs   | Infused Vanilla | 8        |
    Then assert error pop up is displayed and message "subsFlavourCountExceededMsg.key"
    And delete the account via api