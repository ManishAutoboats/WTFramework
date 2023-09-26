
Feature: BAT newsletter Registration Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseUKReg3
  Scenario: UK Subscription - modal window for 0 item
    And user clicks on 'Subscribe' option
    And user clicks on 'Pods subscribe' option
    And click first result
    And user user navigate to learn more from pdp
    And verify the content of the learn more popup

  @VuseUKReg3
  Scenario: UK Subscription - modal window for 5 item
    And user clicks on 'Subscribe' option
    And user clicks on 'Pods subscribe' option
    And click first result
    When Subscribe and save Option is selected
    And select product strength
    And Enter a item quantity of amount '5'
    Then click add to cart button
    And verify the content of the learn more popup for '5' item

  @VuseUKReg3
  Scenario: UK Subscription - modal window for 10 item
    And user clicks on 'Subscribe' option
    And user clicks on 'Pods subscribe' option
    And click first result
    When Subscribe and save Option is selected
    And select product strength
    And Enter a item quantity of amount '10'
    Then click add to cart button
    And verify the content of the learn more popup for '10' item

  @VuseUKReg3
  Scenario: UK Subscription - modal window for 15 item
    And user clicks on 'Subscribe' option
    And user clicks on 'Pods subscribe' option
    And click first result
    When Subscribe and save Option is selected
    And select product strength
    And Enter a item quantity of amount '15'
    Then click add to cart button
    And verify the content of the learn more popup for '15' item

  @VuseUKReg3
  Scenario: UK Subscription - modal window for 5 to 15 items on PDP
    Then modal window should have the correct subscribe package when appropriate quantity is added for PDP
      | 5        |
      | 10       |
      | 15       |

  @VuseUKGlobalSubs
  Scenario: UK Subscription - modal window for 5 to 15 items on PLP
    Then modal window should have the correct subscribe package when appropriate quantity is added for PLP
      | 5        |
      | 10       |
      | 15       |

  @VuseUKGlobalSubs
  Scenario: UK Subscription - modal window for 5 to 15 items on cart page
    Then modal window should have the correct subscribe package when appropriate quantity is added for cart page
      | 5        |
      | 10       |
      | 15       |

  @VuseFRReg3
  Scenario: Content Update on Subscriptions Landing Page
    And users clicks on the 'GlobalSubsPageLink.key' text link
    And url contains 'GlobalSubsURL.key'
    Then assert text of 'monthlySubscriptionText.key' is displayed on page
    Then assert description for vuse subscription
    Then assert FAQs section in accordion style
    Then assert correct page is landing when usr click on select flavors
      | capsuleEpodUrl.key       |
      | capsuleEpenUrl.key       |
      | falconEliquidUrl.key     |

  @VuseITAnonReg3
  Scenario: Customer views min subscription qty message
    And user click on search icon and submits the following search term 'SubscriptionItemTerm2.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then user select subscription option
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '2'
    Then assert message of 'AddedProductInCartMsg.key' is displayed on the page
    And users clicks on the 'viewBasketText.key' text link
    Then 'subscriptionMinQtyError.key' Message is displayed in Cart page
    And verify checkout button is disabled

  @VuseITAnonReg3
  Scenario: Customer views max sub qty message
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '30'
    And assert text of 'subscriptionMaxQty.key' is displayed on page

  @VuseFRReg2
  Scenario: Customer views subs/one-time toggle on PLP
    Given user hovers on 'eLiquids.key' and clicks on 'capsulesEPenlink.key'
    And user see default One time Purchase option selected on PLP
    And user verify Subscription option is unselected on PLP
    And verify "parPack.key" on One time Purchase and on Subscription options
    Then on clicking on "i" icon the modal window opens up on PLP

  @VuseFRReg2
  Scenario: product details on product page VuseFR
    Given user click on search icon and submits the following search term 'ePenlink.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    And user see default One time Purchase option selected
    Then user verify Subscription option is unselected
    And user select subscription option
    And customers see the at the end of the "One time purchase" label the amount spent on that SKU
    And customers see the at the end of the "Subscription" label the total monthly amount for that SKU
    And on clicking on "i" icon the modal window opens up
    And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    Then user see "You save £xx" below the whole component

  # Automate bug 849770
  @VuseFRGlobalSubs
  Scenario: Get warning message when add subscription quantity more than 30 on PLP page
    When user click on search icon and submits the following search term 'ePenlink.key'
    And select first product color or strength on PLP
    And select first subscription option on PLP
    Then assert quantity selector is displayed on each SKU with default value 1
    And update the quantity in plp to '31' and add to cart
    Then assert quantity exceeded warning message 'qtyMaxValidationErrMessage.key' is displayed on PLP page
    And confirm mini-basket displayed amount of '30'

  @VuseITAnonReg3
  Scenario: Claim a free device when you subscribe to 5 or more packs
    When user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
    And users clicks on the 'viewBasketText.key' text link
    And user clicks on choose your device on basket page
    Then user adds free device from a popup

  @VuseUKReg3
  Scenario: UK Subscription - modal window for 5 item basket page
    And user clicks on 'Subscribe' option
    And user clicks on 'Pods subscribe' option
    And click first result
    When Subscribe and save Option is selected
    And select product strength
    And Enter a item quantity of amount '5'
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And verify the content of the learn more popup for '5' item

  @VuseITAnonReg3
  Scenario: Content Update on Subscriptions Landing Page - Vuse IT
    And user hovers over on the Vype Subscriptions link
    And url contains 'GlobalSubsURL.key'
    Then assert text of 'subscriptionPlanText.key' is displayed on page
    Then assert text of 'freeDeliveryText.key' is displayed on page
    Then assert description for vuse subscription
    Then assert FAQs section in accordion style
    Then assert correct page is landing when usr click on select flavors
      | capsuleEpodUrl.key       |
      | capsuleEpenUrl.key       |

  @VuseMXReg
  Scenario: 988600 - Vuse MX NEWSLETTER SUBSCRIPTION Page display saved result
    And create a new account
    And assert text of 'successRegistraionMsg.key' is displayed
    And users clicks on the 'NewsletterSubscriptionNav.key' text link
    And user select the newsletter subscription checkbox and submit
    And assert text of 'ThankyouforyoursubscriptionTextMsg.key' is displayed
    And users clicks on the 'NewsletterSubscriptionNav.key' text link
    And assert that Newsletter checkbox is 'newsletterCheckboxChecked.key' selected

  @VuseDEGlobalSubs
  Scenario: 989533 - Vuse DE - Check warning message not display when add sub product in basket page
    When user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '1'
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And assert text of 'subMinimumQtyRequiredMessage.key' is displayed
    And user increases the quantity of product into '7' using dropdown menu in basket page
    And assert text of 'subMinimumQtyRequiredMessage.key' is not displayed