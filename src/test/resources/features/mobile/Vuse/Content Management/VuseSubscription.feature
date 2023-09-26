Feature: BAT newsletter Registration Feature - Moile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Mobile - VuseUK product details on product page VuseFR
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


  Scenario: Mobile - Content Update on Subscriptions Landing Page
    And users clicks on the 'GlobalSubsPageLink.key' header text link
    And url contains 'GlobalSubsURL.key'
    And assert text of 'monthlySubscriptionText.key' is displayed on page
    And assert correct page is landing when usr click on select flavors
      | capsuleEpodUrl.key       |
      | capsuleEpenUrl.key       |
      | falconEliquidUrl.key     |
