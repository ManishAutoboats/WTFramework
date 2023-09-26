Feature: Global Subs Cart Tests - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @RegVuseFR_Mobile
  Scenario: Mobile - Single Subscription product Full checkout
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @RegVuseFR_Mobile
  Scenario: Mobile - Cart limits display error message for Min quantity
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '1'
    And user clicks on the View Basket cta
    Then 'GloSubsMinimumQuantityError.key' Message is displayed in Cart page
    And user increases the quantity of the selected item by '4'
    Then user is able to proceed to checkout page

  
  Scenario: Subscription product are added successfully to the cart from PDP
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And clicks on product link on the basis of index '2'
    When Subscribe and save Option is selected
    And select product strength
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    Then Subscription item has banner in minicart
    And users clicks on the 'viewBasketText.key' text link
    Then Subscription item has banner in Cart page

 
  Scenario: Subscription landing contains correct content
    And users clicks on the 'GlobalSubsPageLink.key' header text link
    Then assert that page title is 'GlobalSubsPageTitle.key'
    And Asserted text for globalSubs is displayed
      | HowObtainFreeAccessory.key  |
      | HowCanICancel.key           |
      | CanIChangeSub.key           |
      | WhenFirstDeliv.key          |
      | CanIPauseSubs.key           |
      | WhatdelivOptions.key        |
      | CanIBuyOnlineWithoutSub.key |
      | AcceptablePaymentMethod.key |
      | HowToGetReplacementTag.key  |



  Scenario: Single Subscription products have correct tier of discounts at cart
    Then cart has the correct discount when appropriate quantity is added to cart
      | 1        | no discount |
      | 4        | bronze      |
      | 5        | silver      |
      | 5        | gold        |


  Scenario: Multi Subscription product Full checkout grouping
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click back button on view basket
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '4111111111111111'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order


  Scenario: Multiple Subscription products obey cart limits
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '4'
    And click back button on view basket
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '1'
    And users clicks on the 'viewBasketText.key' text link
    And user decreases the quantity of the selected item by '1'
    Then 'GloSubsMinimumQuantityError.key' Message is displayed in Cart page


  Scenario: Mixed cart convert items into non subscription items
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click back button on view basket
    And User adds 'searchTerm.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '6'


  Scenario: Multiple Subscription products have correct tier of discounts at cart
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click back button on view basket
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And click on checkout button on cart page
    Then check subtotals contains tier discount 'silver'


  Scenario: Mixed cart convert items into non subscription items (all items can become subscription items)
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click back button on view basket
    And User adds 'SubscriptionItemTerm.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '6'
    And click on checkout button on cart page
    Then check subtotals does not contain Subscription discount


  Scenario: Mixed cart buy subscription item first (Items cannot be converted)
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click back button on view basket
    And User adds 'NonSubscriptionItemTerm.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And logged-in user selects to order subscription items first with '20%' discount
    And Payment page details confirmed
    And Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order
    When user proceeds to checkout non-subscription product from checkout page
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed

 
  Scenario: Free device becomes paid for when subscription items are changed to one time purchase (non convertable subscription item)
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click back button on view basket
    And User adds 'searchTermePen3.key' to the basket
    And click back button on view basket
    And User adds 'SubscriptionItemTermAccessory.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '7'
    And click on checkout button on cart page
    Then check subtotals does not contain Subscription discount


  @RegVuseFR_Mobile
  Scenario: Mobile - Cart limits display error message for Max quantity
    And user navigates to 'SubscriptionItemTerm.key' and changes qty to '31'
    Then 'GloSubsMaximumQuantityError.key' Message is displayed in PDP
    Then quantity should be set to '30'
    Then click add to cart button
    And user clicks on the View Basket cta

  @RegVuseFR_Mobile
  Scenario: Mobile - Subscription Products are correctly displayed on PLPs
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And clicks on product link on the basis of index '7'
    Then assert Subscribe and Save Option on PDP page