@globalsubs
Feature: Global Subs Cart Tests

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option

  @VuseFRGlobalSubs @LyftGlobalSubs #@VuseDeReg content disabled
  Scenario: Subscription is available in Global header on multiple places
    And users clicks on the 'GlobalSubsPageLink.key' text link
    And url contains 'GlobalSubsURL.key'

  @VuseFRGlobalSubs
  Scenario: Subscription policy is available in Global footer
    And footer div is present and displayed
    And user clicks on the policy link in the footer
    And url contains 'GlobalSubsPolicyURL.key'
    Then assert that page title is 'GlobalSubsPagePolicyTitle.key'

  @VuseFRGlobalSubs
  Scenario: Subscription landing contains correct content
    And users clicks on the 'GlobalSubsPageLink.key' text link
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

  @VuseFRGlobalSubs @frlive  @VuseITGlobalSubs
  #todo- promotion going on, changed to "subscribe and get upto 15% off" instead of "subscribe and save" @LyftRegression
  Scenario: Subscription Products are correctly displayed on PLPs
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And clicks on product link on the basis of index '9'
    Then assert Subscribe and Save Option on PDP page

  @VuseDEGlobalSubs
  Scenario: Subscription Products are correctly displayed on PLPs vuse de
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And clicks on product link on the basis of index '1'
    Then assert Subscribe and Save Option on PDP page

  @LyftGlobalSubs ###Promotional flavours take up the first two slots and are not subscribable
  Scenario: Subscription Products are correctly displayed on PLPs lyftse
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And click third result
    Then assert Subscribe and Save Option on PDP page

  @LyftGlobalSubs @VuseITGlobalSubs ###Promotional flavours take up the first two slots and are not subscribable
  Scenario: Subscription product are added successfully to the cart from PDP lyftse
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    #And click third result
    And clicks on product link on the basis of index '7'
    When Subscribe and save Option is selected
    And select product strength
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    Then Subscription item has banner in minicart
    And users clicks on the 'viewBasketText.key' text link
    Then Subscription item has banner in Cart page

  @VuseFRGlobalSubs @frlive @VuseCOGlobalSubs @VuseITGlobalSubs
  Scenario: Subscription product are added successfully to the cart from PDP
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And clicks on product link on the basis of index '9'
    When Subscribe and save Option is selected
    And select product strength
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    Then Subscription item has banner in minicart
    And users clicks on the 'viewBasketText.key' text link
    Then Subscription item has banner in Cart page

  @VuseDEGlobalSubs
  Scenario: Subscription product are added successfully to the cart from PDP vusede
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And clicks on product link on the basis of index '1'
    When Subscribe and save Option is selected
    And select product strength
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    Then Subscription item has banner in minicart
    And users clicks on the 'viewBasketText.key' text link
    Then Subscription item has banner in Cart page


  @VuseCOGlobalSubs
  Scenario: Subscription product are added successfully to the cart from PDP vuse co
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And clicks on product link on the basis of index '11'
    When Subscribe and save Option is selected
    And select product strength
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    Then Subscription item has banner in minicart
    And users clicks on the 'viewBasketText.key' text link
    Then Subscription item has banner in Cart page

  @VuseFRGlobalSubs @frlive @VuseCOGlobalSubs #@LyftRegression
  Scenario: Single Subscription products have correct tier of discounts at cart
    Then cart has the correct discount when appropriate quantity is added to cart
      | 1        | no discount |
      | 4        | bronze      |
      | 5        | silver      |
      | 5        | gold        |

  @VuseDEGlobalSubs
  Scenario: Single Subscription products have correct tier of discounts at cart vuse De
    Then cart has the correct discount when appropriate quantity is added to cart
      | 1        | no discount |
      | 5        | bronze      |
      | 4        | silver      |
      | 5        | gold        |


  @VuseITGlobalSubs
  Scenario: Single Subscription products have correct tier of discounts at cart vype IT
    Then cart has the correct discount when appropriate quantity is added to cart
      | 1        | no discount |
      | 4        | silver      |
      | 3        | gold        |
      | 4        | platinum    |

  @VuseFRGlobalSubs @VuseCOGlobalSubs
  Scenario: Cart limits display error message for Max quantity
#    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '31'
    And user navigates to 'SubscriptionItemTerm.key' and changes qty to '31'
    Then 'GloSubsMaximumQuantityError.key' Message is displayed in PDP
    Then quantity should be set to '30'
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
#    And user increases the quantity of the selected item by '1'
#    Then 'GloSubsMaximumQuantityError.key' Message is displayed in Cart page

  @VuseITGlobalSubs
  Scenario: Cart limits display error message for Max quantity vype IT
      And users clicks on the 'eLiquidFlavoursURlText.key' text link
      And clicks on product link on the basis of index '7'
      When Subscribe and save Option is selected
      And Enter a item quantity of amount '26'
      Then 'GloSubsMaximumQuantityError.key' Message is displayed in PDP
      Then quantity should be set to '25'

  @VuseFRGlobalSubs @VuseITGlobalSubs @VuseCOGlobalSubs
  Scenario: Cart limits display error message for Min quantity
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '1'
    And users clicks on the 'viewBasketText.key' text link
    Then 'GloSubsMinimumQuantityError.key' Message is displayed in Cart page
    And user increases the quantity of the selected item by '6'
    Then user is able to proceed to checkout page

 @VuseITGlobalSubs
  Scenario: Vuse IT Cart limits display error message for Min quantity
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '1'
    And users clicks on the 'viewBasketText.key' text link
    Then 'GloSubsMinimumQuantityError.key' Message is displayed in Cart page
    And user increases the quantity of the selected item by '5'
    Then user is able to proceed to checkout page

  @VuseFRGlobalSubs @VuseITGlobalSubs
  Scenario: Multiple Subscription products obey cart limits
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '4'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '1'
    And users clicks on the 'viewBasketText.key' text link
    And user decreases the quantity of the selected item by '1'
    Then 'GloSubsMinimumQuantityError.key' Message is displayed in Cart page

  @VuseCOGlobalSubs
  Scenario: Multiple Subscription products obey cart limits Vuse CO
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '3'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '1'
    And users clicks on the 'viewBasketText.key' text link
    And user decreases the quantity of the selected item by '1'
    Then 'GloSubsMinimumQuantityError.key' Message is displayed in Cart page

  @VuseFRGlobalSubs
  Scenario: Multiple Subscription products have correct tier of discounts at cart
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And click on checkout button on cart page
    Then check subtotals contains tier discount 'silver'

  @VuseCOGlobalSubs
  Scenario: Multiple Subscription products have correct tier of discounts at cart vuseco
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user login with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And click on checkout button on cart page
    Then check subtotals contains tier discount 'silver'

  @VuseFRGlobalSubs
  Scenario: Mixed cart buy subscription item first (Items cannot be converted)
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
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

  @VuseCOGlobalSubs
  Scenario: Mixed cart buy subscription item first (Items cannot be converted) vuse co
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user login with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
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

#  @VuseFRGlobalSubs
  Scenario Outline: Mixed cart buy subscription item first (All items can be converted)
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '<quantity>'
    And User adds 'SubscriptionItemTerm.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to order subscription items first with '<discountPercent>' discount
    Then confirm mini-basket displayed amount of '<expectedQty>'
    Then cart has appropriate '<discount>' applied to cart
    Examples:
      | quantity | discount | discountPercent | expectedQty |
      | 4        | bronze   | 20%             | 5           |
      | 9        | silver   | 30%             | 10          |
      | 14       | gold     | 40%             | 15          |
      | 30       | offer    | s’appliquent    | 31          |

  @VuseFRGlobalSubs @VuseCOGlobalSubs
  Scenario: Mixed cart convert items into non subscription items
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And User adds 'searchTerm.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '6'


  @VuseFRGlobalSubs
  Scenario: Mixed cart convert items into non subscription items (all items can become subscription items)
    And create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And User adds 'SubscriptionItemTerm.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '6'
    And click on checkout button on cart page
    Then check subtotals does not contain Subscription discount

  @VuseFRGlobalSubs
  Scenario: Mixed cart convert items into non subscription items (all items can become subscription items) Vuseco
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user login with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And User adds 'SubscriptionItemTerm2.key' to the basket
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '6'
    And click on checkout button on cart page
    Then check subtotals does not contain Subscription discount

  @VuseFRGlobalSubs
  Scenario: Free device becomes paid for when subscription items are changed to one time purchase
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And User adds 'SubscriptionItemTerm.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And User adds 'SubscriptionItemTermAccessory.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '7'
    And click on checkout button on cart page
    Then check subtotals does not contain Subscription discount

  @VuseFRGlobalSubs
  Scenario: Free device becomes paid for when subscription items are changed to one time purchase (non convertable subscription item)
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And User adds 'searchTermePen3.key' to the basket
    And User adds 'SubscriptionItemTermAccessory.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And user selects to buy items as non subscription items
    Then confirm mini-basket displayed amount of '7'
    And click on checkout button on cart page
    Then check subtotals does not contain Subscription discount

  @VuseFRGlobalSubs
  Scenario: Free device remains free when checkout with subscription first
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And User adds 'searchTermePen3.key' to the basket
    And User adds 'SubscriptionItemTermAccessory.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And logged-in user selects to order subscription items first with '20%' discount
    And Payment page details confirmed
    Then check subtotals contains tier discount 'bronze'
    Then check subtotals contains tier discount 'accessory'

  Scenario: Free device remains free when checkout with subscription first all items convertable
    And create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And User adds 'SubscriptionItemTerm.key' to the basket
    And User adds 'SubscriptionItemTermAccessory.key' to the basket
    And users clicks on the 'viewBasketText.key' text link
    And logged-in user selects to order subscription items first with '20%' discount
    And Payment page details confirmed
    Then check subtotals contains tier discount 'bronze'
    Then check subtotals contains tier discount 'accessory'

  @VuseFRGlobalSubsSmoke
  Scenario: Single Subscription product Full checkout
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order


  @VuseITGlobalSubsSmoke
  Scenario: Single Subscription product Full checkout vype it
    And user logins Magento Admin home page successfully
    When user navigate to Store Configuration page
    Then 3DS subs is turned off for given locale
    Then user navigates to BAT home page for FE validation
    And create a new account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseCOGlobalSubsSmoke
  Scenario: Single Subscription product Full checkout vuse co
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user login with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

 @LyftGlobalSubsSmoke
  Scenario: Single Subscription product Full checkout Lyftse
   And user logins Magento Admin home page successfully
   When user navigate to Store Configuration page
   Then 3DS subs is turned off for given locale
   Then user navigates to BAT home page for FE validation
   And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
   And empty basket
   And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
   And click on basket icon
   And click on proceed to checkout button
   And Payment page details confirmed
   When Credit or Debit Cards (SubscribePro) option is chosen
   And Checkout is selected with appropriate card details entered '5555555555554444'
   Then assert print receipt link is present
   And assert text of 'ThankForPurchase.key' is displayed
   And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseDEGlobalSubsSmoke
  Scenario: Single Subscription product Full checkout vuse de
    And user logins Magento Admin home page successfully
    When user navigate to Store Configuration page
    Then 3DS subs is turned off for given locale
    Then user navigates to BAT home page for FE validation
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '6'
    And users clicks on the 'viewBasketText.key' text link
    And click on proceed to checkout from cart page
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseFRGlobalSubs
  Scenario: Multi Subscription product Full checkout grouping
    And create a new account via api
    And user logins with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '4111111111111111'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseCOGlobalSubs
  Scenario: Multi Subscription product Full checkout  vuse co
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user login with credentials registered via api
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '4111111111111111'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @LyftGlobalSubs
  Scenario: Multi Subscription product Full checkout grouping 3ds
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseDEGlobalSubs
  Scenario: Multi Subscription product Full checkout grouping 3ds vusede
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseITGlobalSubs
  Scenario: Multi Subscription product Full checkout grouping 3ds vype it
    And create a new account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And user adds subscription item 'SubscriptionItemTerm2.key' to the basket by quantity '5'
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseFRGlobalSubs
  Scenario: checkout is possible with free accessory
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And user adds accessory to cart through add accessory CTA
    And click on checkout button on cart page
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    Then check subtotals contains tier discount 'bronze'
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order

  @VuseFRGlobalSubs
  Scenario: Bronze tier order has free standard shipping
    And create a new account via api and log in with the account
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And click on proceed to checkout button
    And Payment page details confirmed
    And assert that shipping cost is zero
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert that globalSubs Success message 'SuccessfulSubscription.key' appears for order
    And grab Order Number and assert Order Status on View Order Details page
    And check Subscription Order has free shipping on Order Details Page

#  @VuseFRGlobalSubs bugged, fix inbound
  Scenario: User is able to switch products from Onetime Purchase to Subscription and Viceversa at cart
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    When user clicks on Immediate Purchase option for product
    Then Subscription Banner is no longer displayed
    When user click on subscribe and save option for product
    Then Subscription item has banner in Cart page
    Then cart has appropriate 'bronze' applied to cart

  @VuseUKGlobalSubs
  Scenario: Cart limits display error message for Min quantity VuseUK
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '4'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then 'GloSubsMinimumQuantityError.key' Message is displayed in Cart page
    Then verify checkout button is disabled
    And user increases the quantity of the selected item by '1'
    Then subscription error message is not display

#  @VuseUKGlobalSubs #disabled due to a change functionality this test is not valid at the moment in time
  Scenario: Cart limits display error message for Max quantity VuseUK
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '30'
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And user increases the quantity of the selected item by '1'
    Then 'subscriptionMaxQtyError.key' Message is displayed in Cart page

  @VuseUKGlobalSubsSmoke
  Scenario: Single Subscription product checkout VuseUK
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then subscription error message is not display
    And click on proceed to checkout button
    And Payment page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present

  @VuseUKGlobalSubs
  Scenario: Single product checkout VuseUK
    When create a new account
    And click on the logo
    And user navigates to PLP page and adds a product to basket
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then subscription error message is not display
    And click on proceed to checkout button

  @VuseUKGlobalSubs
  Scenario: Multiple Subscription With One Time purchase VuseUK
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'SubscriptionItemTerm2.key'
    And user closes the alert if present
    And select product strength or colour
    Then click add to cart button
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user click on one time purchase
    And click on proceed to checkout button
    And Payment page details confirmed

  @VuseUKGlobalSubs
  Scenario: Multiple Subscription With Subscribe All VuseUK
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'SubscriptionItemTerm2.key'
    And user closes the alert if present
    And select product strength or colour
    Then click add to cart button
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user click on subscribe all
    And click on proceed to checkout button
    And Payment page details confirmed

  @VuseUKGlobalSubs
  Scenario: Cart has mix of one-off purchase and subscription items select make all one time purchase
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then two options display
    Then select make all one time purchase
    Then subscription product change to one time purchase
    And click on proceed to checkout button
    And Payment page details confirmed

  @VuseUKGlobalSubs
  Scenario: Cart has mix of one-off purchase and subscription items select subscription first
    And user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then two options display
    Then select checkout subscription first
    And Payment page details confirmed
    And verify only subscription product is added for payment

  @VuseUKGlobalSubs
  Scenario: Toggling/delete to subscription VuseUK
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'SubscriptionItemTerm2.key'
    And user closes the alert if present
    And select product strength or colour
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then verify subscription message is displayed
    And toggle to one time product to subscription
    Then subscription error message is not display
    And user click on search icon and submits the following search term 'SubscriptionItemTerm2.key'
    And user closes the alert if present
    And select product strength or colour
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    Then verify subscription message is displayed
    Then the most recent product is removed from the cart
    Then verify subscription message is not displayed

  @VuseDEGlobalSubs
  Scenario Outline: check min or max sub qty message is displayed
    When user signs in with customer properties 'loginValidEmailGloSubs.key' 'loginValidPassword.key'
    And empty basket
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '<qty>'
    And users clicks on the 'viewBasketText.key' text link
    Then min or max subscription message is displayed
    Examples:
      | qty |
      | 2   |
      | 31  |

  @VuseITGlobalSubs
  Scenario: Customer hovers and views subs CTA on PLP product tile - toggle bars enabled
    And user click on search icon and submits the following search term 'ePenLink.key'
    And get all lists from page
    And user hovers over the plp item
    When click on subscription link
    And url contains 'GlobalSubsURL.key'
    Then Subscription text of packages are displayed on page

  @VuseITGlobalSubs
  Scenario: Customer hovers and views subs CTA on PLP product tile - toggle bars disabled
    And user click on search icon and submits the following search term 'ePenLink.key'
    And get all lists from page
    And user select subscription option plp
    And user hovers over the plp item
    When click on subscription link
    And url contains 'GlobalSubsURL.key'
    Then Subscription text of packages are displayed on page

  @VuseCOGlobalSubs
  Scenario: Customer views information icon on PLP subscription toggle bar
    When users clicks on the 'eLiquidFlavoursURlText.key' text link
    Then information icon beside the price on the subscription toggle bar is displayed

  @LyftGlobalSubs
  Scenario: Valid promotion cannot be added to a subscription order lyftse
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '6'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And user apply the discount coupon 'validCouponCode.key'
    And error message 'specificInvalidPromoCodeError.key' is displayed for given promocode