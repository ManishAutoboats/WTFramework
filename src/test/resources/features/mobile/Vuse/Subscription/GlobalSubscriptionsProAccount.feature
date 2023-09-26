Feature: Global Subs Account tests - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @RegVuseFR_Mobile
  Scenario: Mobile - User is able to change payment option (Native)
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link mobile
    Then User can add new card to be used as a subscription
    And User is able to update payment option for a subscription (Native)


  Scenario: Update Address in My Subscriptions (Native)
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    Then users clicks on the 'MySubscriptions.key' text link mobile
    And assert text of 'MySubscriptionsTitle.key' is displayed
    Then User is able to update address for a subscription (Native)
