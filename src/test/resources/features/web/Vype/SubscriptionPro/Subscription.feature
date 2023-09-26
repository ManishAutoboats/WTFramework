#@PDFSubsPro this functionality is being changed to use a different implimentation of subspro for vype IT cx reset which means the majority of these
  #tests will either be covered by another set of tests or invalid
Feature: BAT subscriptionPro feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: Subscription block present & sub product label displayed on PLP and sub opened and content present
    When user clicks on the link that contains 'LiquidiUrlText.key' href
    Then assert subscription block is displayed
    Then assert subscription label on product is displayed
    And user clicks on the subscription block
    Then assert subscription block has content

  Scenario: Subscription block present in product detail page
    When user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    Then assert subscription container is displayed
    And assert One Time Purchase is checked by default

  Scenario: One Time Purchase will add the product to cart
    When user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And user clicks on One Time Purchase
    And click add to cart button
    And confirm mini-basket displayed amount of '1'
    Then click on basket icon
    And assert One Time Purchase label is displayed in cart
    And assert current product can be found '1' times in cart

  Scenario: Subscribe and Save will add the product to cart
    When user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '1'
    Then click on basket icon
    And assert Subscription label is displayed in cart
    And assert current product can be found '1' times in cart

  Scenario: Add both Subscription and One Time Purchase to cart
    When user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And user clicks on One Time Purchase
    And click add to cart button
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '2'
    Then click on basket icon
    And assert One Time Purchase label is displayed in cart
    And assert Subscription label is displayed in cart
    And assert current product can be found '2' times in cart

  Scenario: Guest cannot checkout with a Subscription and One Time Purchase
    Given user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And user clicks on One Time Purchase
    And click add to cart button
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '2'
    And click on basket icon
    When click on proceed to checkout button
    Then confirm popup mask present

  Scenario: User has Silver discount when subscribing with 5-7 products
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '7'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '7'
    And click on basket icon
    When click on proceed to checkout button
    Then assert the discount is applied for 'silver' subscription

  Scenario: User has Gold discount when subscribing with 8-11 products
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '8'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '8'
    And click on basket icon
    When click on proceed to checkout button
    Then assert the discount is applied for 'gold' subscription

  Scenario: User has Platinum discount when subscribing with 12-25 products
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '12'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '12'
    And click on basket icon
    When click on proceed to checkout button
    Then assert the discount is applied for 'platinum' subscription

  # Bug:501323
  Scenario: User cannot checkout with more than 25 products with subscription
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '26'
    And user clicks on Subscribe and Save
    And assert maximum quantity for subscription error is displayed

  Scenario: User receive error in checkout when minimum quantity condition is not met for Subscription
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '4'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '4'
    And click on basket icon
    When click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    Then select place order
    And assert minimum quantity for subscription error is displayed

  Scenario: User can checkout with One Time Purchase without meeting minimum quantity condition
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And user clicks on One Time Purchase
    And click add to cart button
    And confirm mini-basket displayed amount of '1'
    And click on basket icon
    When click on proceed to checkout button
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    Then select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '0' products in subscription exist under My Subscriptions

    # Spinning issue at checkout
#  Scenario: User can checkout with Subscription product and a free device when meeting minimum quantity condition
#    Given user clicks the person icon
#    And user clicks on 'signInLink.key' link from Person Menu
#    And user clicks the registration button
#    And populate personal informations section fields
#    And populate address informations section fields
#    And populate signin fields
#    And user selects the create an account button
#    And assert text of 'successRegistraionMsg.key' is displayed
#    And user clicks on the link that contains 'LiquidiUrlText.key' href
#    And user navigates to a product page with subscription
#    And modify quantity of the product to '5'
#    And user clicks on Subscribe and Save
#    And click add to cart button
##    And confirm mini-basket displayed amount of '5'
#    And user clicks on the link that contains 'DeviceUrlText.key' href
#    And user navigates to the product page with 'freeDevicePath.key' href
#    And select product strength or colour
#    And click add to cart button
#    And confirm mini-basket displayed amount of '6'
#    And click on basket icon
#    When click on proceed to checkout button
#    And Credit/Debit Card - SubscribePro order option and press next
#    And enter mastercard details
#    And assert the free device is applied for subscription
#    Then select place order
#    And assert text of 'ThankForPurchase.key' is displayed
#    And user clicks the person icon
#    And users clicks on the 'mySubscriptionLinkText.key' text link
 #   And assert '1' products in subscription exist under My Subscriptions

  Scenario: User can checkout with Subscription product when meeting minimum quantity condition
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    When click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    Then select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions

  Scenario: User can checkout with mixed products
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And user clicks on One Time Purchase
    And click add to cart button
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '6'
    And click on basket icon
    When click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    Then select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions

  Scenario: Subscription products are merged in cart and user can checkout
    When user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And user clicks the person icon
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    Then user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions

  Scenario: User cannot have more than one subscription
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    When user clicks on Subscribe and Save
    And click add to cart button
    Then assert user is not allowed to have any other subscriptions

  #Needs to be looked into these scenarios again once UX reset is done on VYPE IT, as discussed with Alex, currently, scenarios are not valid
#  Scenario: User cannot modify the qty of items under and over the limits from the subscription
#    Given user clicks the person icon
#    And user clicks on 'signInLink.key' link from Person Menu
#    And user clicks the registration button
#    And populate personal informations section fields
#    And populate address informations section fields
#    And populate signin fields
#    And user selects the create an account button
#    And assert text of 'successRegistraionMsg.key' is displayed
#    And user clicks on the link that contains 'LiquidiUrlText.key' href
#    And user navigates to a product page with subscription
#    And modify quantity of the product to '5'
#    And user clicks on Subscribe and Save
#    And click add to cart button
#    And confirm mini-basket displayed amount of '5'
#    And click on basket icon
#    And click on proceed to checkout button
#    And Credit/Debit Card - SubscribePro order option and press next
#    And enter mastercard details
#    And select place order
#    And assert text of 'ThankForPurchase.key' is displayed
#    And user clicks the person icon
#    And users clicks on the 'mySubscriptionLinkText.key' text link
#    And assert '1' products in subscription exist under My Subscriptions
#    When user updates the subscription quantity with '1'
#    And assert minimum quantity for subscription error is displayed
#    Then user updates the subscription quantity with '26'
#    And assert maximum quantity for subscription error is displayed

    #Disabled, As is not relevant at current point in time 380157
#  Scenario: Discount changes when the user modifies qty for items in subscription over the margins
#    Given user clicks the person icon
#    And user clicks on 'signInLink.key' link from Person Menu
#    And user clicks the registration button
#    And populate personal informations section fields
#    And populate address informations section fields
#    And populate signin fields
#    And user selects the create an account button
#    And assert text of 'successRegistraionMsg.key' is displayed
#    And user clicks on the link that contains 'LiquidiUrlText.key' href
#    And user navigates to a product page with subscription
#    And modify quantity of the product to '5'
#    And user clicks on Subscribe and Save
#    And click add to cart button
#    And confirm mini-basket displayed amount of '5'
#    And click on basket icon
#    And click on proceed to checkout button
#    And Credit/Debit Card - SubscribePro order option and press next
#    And enter mastercard details
#    And select place order
#    And assert text of 'ThankForPurchase.key' is displayed
#    And user clicks the person icon
#    And users clicks on the 'mySubscriptionLinkText.key' text link
#    And assert '1' products in subscription exist under My Subscriptions
#    And assert subscription discount is not displayed
#    When user updates the subscription quantity with '11'
#    And assert subscription discount is displayed
#    And assert subscription discount text is different
#    Then user updates the subscription quantity with '25'
#    And assert subscription discount is displayed
#    And assert subscription discount text is different

  Scenario: User cannot have products over the margin when adding new items to subscriptions
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    When user clicks on add a new subscription button
    And user adds a new product to subscription in quantity '21'
    Then assert '1' products in subscription exist under My Subscriptions

  #Needs to be looked into these scenarios again once UX reset is done on VYPE IT, as discussed with Alex, currently, scenarios are not valid
#  Scenario: User pays only 1 fee when modifies qty for items in subscription from 2 different levels
#    Given user clicks the person icon
#    And user clicks on 'signInLink.key' link from Person Menu
#    And user clicks the registration button
#    And populate personal informations section fields
#    And populate address informations section fields
#    And populate signin fields
#    And user selects the create an account button
#    And assert text of 'successRegistraionMsg.key' is displayed
#    And user clicks on the link that contains 'LiquidiUrlText.key' href
#    And user navigates to a product page with subscription
#    And modify quantity of the product to '25'
#    And user clicks on Subscribe and Save
#    And click add to cart button
#    And confirm mini-basket displayed amount of '25'
#    And click on basket icon
#    And click on proceed to checkout button
#    And Credit/Debit Card - SubscribePro order option and press next
#    And enter mastercard details
#    And select place order
#    And assert text of 'ThankForPurchase.key' is displayed
#    And user clicks the person icon
#    And users clicks on the 'mySubscriptionLinkText.key' text link
#    And assert '1' products in subscription exist under My Subscriptions
#    When user updates the subscription quantity with '11'
#    And user accepts paying the fee
#    And assert '1' number of products can be found in checkout
#    And user clicks the person icon
#    And users clicks on the 'mySubscriptionLinkText.key' text link
#    And user updates the subscription quantity with '5'
#    And user accepts paying the fee
#    And assert '1' number of products can be found in checkout
#    Then Credit Card - MasterCard option and press next
#    And enter mastercard details
#    And select place order
#    And assert text of 'ThankForPurchase.key' is displayed

  #Needs to be looked into these scenarios again once UX reset is done on VYPE IT, as discussed with Alex, currently, scenarios are not valid
#  Scenario: User doesn't need to pay a fee when modifies qty for items in subscription from Gold to Silver
#    Given user clicks the person icon
#    And user clicks on 'signInLink.key' link from Person Menu
#    And user clicks the registration button
#    And populate personal informations section fields
#    And populate address informations section fields
#    And populate signin fields
#    And user selects the create an account button
#    And assert text of 'successRegistraionMsg.key' is displayed
#    And user clicks on the link that contains 'LiquidiUrlText.key' href
#    And user navigates to a product page with subscription
#    And modify quantity of the product to '6'
#    And user clicks on Subscribe and Save
#    And click add to cart button
#    And confirm mini-basket displayed amount of '6'
#    And click on basket icon
#    And click on proceed to checkout button
#    And Credit/Debit Card - SubscribePro order option and press next
#    And enter mastercard details
#    And select place order
#    And assert text of 'ThankForPurchase.key' is displayed
#    And user clicks the person icon
#    And users clicks on the 'mySubscriptionLinkText.key' text link
#    And assert '1' products in subscription exist under My Subscriptions
#    When user updates the subscription quantity with '5'
#    And assert subscription discount is displayed

  Scenario: User cannot skip the delivery for more than 5 times
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions
    When user clicks on Skip Delivery '5' times
    Then assert skip delivery button not displayed

  Scenario: User can skip the delivery 1 times
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions
    When user clicks on Skip Delivery '1' times
    Then assert skip delivery button displayed
    And assert subscription was delayed for '1' months

  Scenario: User has to pay a fee when cancelling the subscription
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions
    When user cancels the latest subscription
    And user accepts paying the fee
    And assert '1' number of products can be found in checkout
    Then Credit Card - MasterCard option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed

  Scenario: User has to pay a fee when removing items from the subscription and doesn't have enough subscribed items left
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions
    And user clicks on add a new subscription button
    And user adds a new product to subscription in quantity '1'
    When user removes the first item from subscription
    And user accepts paying the fee
    And assert '1' number of products can be found in checkout
    Then Credit Card - MasterCard option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed

  Scenario: User skips the subscription for 1 month and adds another product to subscription
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And assert '1' products in subscription exist under My Subscriptions
    And user clicks on Skip Delivery '1' times
    When user clicks on add a new subscription button
    And user adds a new product to subscription in quantity '6'
    Then assert '2' products in subscription exist under My Subscriptions

  Scenario: Subscription delivery address is updated when user changes his address
    Given user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields
    And user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
    And user clicks on the link that contains 'LiquidiUrlText.key' href
    And user navigates to a product page with subscription
    And modify quantity of the product to '5'
    And user clicks on Subscribe and Save
    And click add to cart button
    And confirm mini-basket displayed amount of '5'
    And click on basket icon
    And click on proceed to checkout button
    And Credit/Debit Card - SubscribePro order option and press next
    And enter mastercard details
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And users clicks on the 'mySubscriptionLinkText.key' text link
    And user clicks on modify delivery address button
    And populate all address fields including new first and last name
    When user saves the new modified address
    Then assert delivery address changed