@globalsubs
Feature: Global Subs Account tests

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    #And select allow all from cookie popup
    #And user selects over 18 age confirmation option
    And select allow all from cookie popup and select over 18 age confirmation option


  @LyftGlobalSubs
  Scenario: User can update the quantity of their subscription and add/remove FR/SE/DE
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    When user checks the current tier of latest subscription
    Then user can increase then subscription amount by '5'
    And tier discount has been promoted
    Then user can decrease then subscription amount by '5'
    And tier discount has been demoted
    Then user can add another product to the subscription
    And tier discount has been promoted
    Then user can remove another product from the subscription
    And tier discount has been demoted

  @VuseDEGlobalSubs
  Scenario: User can update the quantity of their subscription and add/remove DE
    And create a new account via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '6'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    When user checks the current tier of latest subscription
    Then user can increase then subscription amount by '5'
    And tier discount has been promoted
    Then user can decrease then subscription amount by '5'
    And tier discount has been demoted
    Then user can add another product to the subscription
    And tier discount has been promoted
    Then user can remove another product from the subscription
    And tier discount has been demoted

  @VuseFRGlobalSubs @LyftGlobalSubs
  Scenario: Update Address in My Subscriptions (Native)
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    And assert text of 'MySubscriptionsTitle.key' is displayed
    Then User is able to update address for a subscription (Native)

  @VuseDEGlobalSubs
  Scenario: Update Address in My Subscriptions (Native) DE
    And create a new account via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '6'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    And assert text of 'MySubscriptionsTitle.key' is displayed
    Then User is able to update address for a subscription (Native)

  @VuseFRGlobalSubs @LyftGlobalSubs
  Scenario: User is able to skip/pause/resume/stop subscriptions (Native)
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then 'pause' function is working as intended (Native)
    Then 'skip' function is working as intended (Native)
    Then 'stop' function is working as intended (Native)

  @VuseDEGlobalSubs
  Scenario: User is able to skip/pause/resume/stop subscriptions (Native) DE
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then 'pause' function is working as intended (Native)
    Then 'skip' function is working as intended (Native)
    Then 'stop' function is working as intended (Native)

  @VuseUKGlobalSubs
  Scenario: User is able to skip/pause/resume/stop subscriptions UK
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then 'pause' function is working as intended (Native)
    Then 'skip' function is working as intended (Native)
    Then 'stop' function is working as intended (Native)


  @LyftGlobalSubs @VuseFRGlobalSubs
  Scenario: User is able to change payment option (Native)
    And create a new account via api and log in with the account
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then User can add new card to be used as a subscription
    And User is able to update payment option for a subscription (Native)

  @VuseDEGlobalSubs
  Scenario: User is able to change payment option vusede
    And create a new account via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '6'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    Then User can add new card to be used as a subscription
    And User is able to update payment option for a subscription (Native)

  @VuseITGlobalSubs @VuseCOGlobalSubs
  Scenario: User is able to skip/pause/update address and change payment subscriptions vype IT
    And create a new account via api and log in with the account
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then User is able to update address for a subscription (Native)
    Then User is able to update payment option for a subscription (Native)
    Then 'pause' function is working as intended (Native)
#    Then 'skip' function is working as intended (Native)


  @VuseITGlobalSubs
  Scenario: User has to pay cancellation fee for subscription
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then 'stop' function is working as intended (Native)
#    And Payment page details confirmed
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button to move head and place the order
    And assert text of 'yourOrderNumberText.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    Then Subscription has the status 'SubsAccountStatusDisplayedCancelled.key'

  @VuseCOGlobalSubs
  Scenario: User can update the quantity of their subscription and add/remove Vuse CO
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then user can increase then subscription amount by '3'
    And the tier discount has been updated to 'silver'
    Then user can decrease then subscription amount by '3'
    And the tier discount has been updated to 'bronze'
    Then user can add another product to the subscription
    And the tier discount has been updated to 'silver'
    Then user can remove another product from the subscription
    And the tier discount has been updated to 'bronze'

  @VuseITGlobalSubs
  Scenario: User can update/add/remove items to their subscription
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then user can increase then subscription amount by '3'
    And the tier discount has been updated to 'gold'
    Then user can decrease then subscription amount by '3'
    And the tier discount has been updated to 'silver'
    Then user can add another product to the subscription
    And the tier discount has been updated to 'gold'
    Then user can remove another product from the subscription
    And the tier discount has been updated to 'silver'


  @VuseITGlobalSubs @VuseCOGlobalSubs
  Scenario: User can only have one subscription per account
    And create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '1'
    Then error message '1SubscriptionOnly.key' is displayed to user after add to cart

  @VuseUKGlobalSubs
  Scenario: User can update the quantity of their subscription and add/remove Vuse UK
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    And assert text of 'MySubscriptionsDescription.key' is displayed
    Then user clicks on Learn More button under My Account Subscriptions tab and assert CTA
    And assert text of 'MySubscriptionsTitle.key' is displayed
    And user clicks the person icon
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    Then user can increase existing product in subscription by '5'
    And the tier discount has been updated to 'silver'
    Then user can decrease existing product in subscription by '5'
    And the tier discount has been updated to 'bronze'
    Then user can add a new product to the subscription
    And the tier discount has been updated to 'silver'
    Then user can remove a new product from the subscription
    And the tier discount has been updated to 'bronze'

  @VuseFRGlobalSubs
  Scenario: User can update the quantity of their subscription and add/remove Vuse FR
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    Then users clicks on the 'MySubscriptions.key' text link
    Then user can increase existing product in subscription by '5'
    And the tier discount has been updated to 'silver'
    Then user can decrease existing product in subscription by '5'
    And the tier discount has been updated to 'bronze'
    Then user can add a new product to the subscription
    And the tier discount has been updated to 'silver'
    Then user can remove a new product from the subscription
    And the tier discount has been updated to 'bronze'

  @VuseCOGlobalSubs
  Scenario: Check free shipping method is displayed for the subscription order over $60,000
    When create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    Then Payment page details confirmed
    Then free delivery radio button is displayed
    And assert text of 'freeShippingDescription.key' is displayed

  @VuseCOGlobalSubs
  Scenario: Error message is displayed if registered user adds products to cart for active subscription
    When create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '1'
    Then error message '1SubscriptionOnly.key' is displayed to user after add to cart

  @VuseCOGlobalSubs
  Scenario: Restart button isn't displayed when view cancelled subscription
    When create a new account via api
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
    And user cancels the active subscription
    Then restart button isn't displayed

  @VuseFRGlobalSubs
  Scenario: Assert loqate functionality for update address for Subscriptions vuse fr
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then customer clicks the cancel button against a subscription
    Then select change address or payment method checkbox and then click on Update Address button
    Then User is able to update address for a subscription using loqate
    And wait for the page to fully load
    And assert text of 'addressSavedSuccessMessage.key' is displayed
    And delete the account via api

  @VuseUKGlobalSubs
  Scenario: Assert loqate functionality for update address for Subscriptions
    And create a new account via api
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
    Then users clicks on the 'MySubscriptions.key' text link
    Then customer clicks the cancel button against a subscription
    Then select change address or payment method checkbox and then click on Update Address button
    Then User is able to update address for a subscription using loqate
    And wait for the page to fully load
    And assert text of 'addressSavedSuccessMessage.key' is displayed
    And delete the account via api