Feature: Lyft Subscription

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @LyftGlobalSubs
  Scenario: Increase max subscription limit from 20 to 30 on PDP drop-down
    When user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '30'
    And click add to cart button

  @LyftGlobalSubs
  Scenario: View subscription details in pop-up from PDP
    When user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And user clicks on more subscription link
    Then assert subscription pop-up is displayed on PDP page

  @LyftGlobalSubs
  Scenario: User can get Cancellation Survey when cancel subscription
    Given create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'MySubscriptions.key' text link
    Then verify subscription page is displayed
    And user clicks on cancel subscription link
    Then display the cancellation survey

  @LyftGlobalSubs
  Scenario: Customer has free standard shipping
    Given user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    Then assert that shipping cost is zero
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order
    And grab Order Number and assert Order Status on View Order Details page
    Then check Subscription Order has free shipping on Order Details Page

  @LyftGlobalSubs
  Scenario: Customer chooses a shipping option other than free standard shipping at checkout
    Given user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    And click on a shipping option other than free standard shipping
    Then assert that shipping cost is zero

  @LyftGlobalSubs
  Scenario: Customer attempts to remove a SKU/SKUs using bin icon against product in 'Modify subscription' module, hits 5 lower limit
    Given create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '6'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'MySubscriptions.key' text link
    And user clicks on the update subscription button
    And user clicks on bin to remove the subscription
    Then assert 'below 5 validation' message is presented on Modify Subscription module
    And assert update subscription button on Modify Subscription module is disabled

  @LyftGlobalSubs
  Scenario: Customer attempts to remove a SKU when they are already at the min limit for subscriptions
    Given create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'MySubscriptions.key' text link
    And user decrease the subscription amount by '1'
    Then assert 'below 5 validation' message is presented on Modify Subscription module
    And assert update subscription button on Modify Subscription module is disabled

  @LyftGlobalSubs
  Scenario: Customer attempts to add a SKU/SKUs in the 'Modify your subscription' module, when their subscription is already at the 30 max limit
    Given create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '30'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'MySubscriptions.key' text link
    And user increase the subscription amount by '1'
    Then assert 'above 30 validation' message is presented on Modify Subscription module
    And assert update subscription button on Modify Subscription module is disabled