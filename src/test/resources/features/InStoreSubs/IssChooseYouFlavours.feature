@iss
Feature: In Store Subs - choose Your Flavours
  Background: 432776 AC1 434642 AC1 452445 AC1 Navigate to home page
    Given user is at the In Store Subs home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
@isslive
Scenario: 434233 AC1 AC2 Start again journey
  Given the user is signed in
  And accepts the challenge screen
  And click on Start Subscription button
  And user is on the Choose your Flavours screen
  When he clicks on the link Start Again
  Then he gets a warning overlay
  When user clicks "no"
  Then  user is on the Choose your Flavours screen
  When he clicks on the link Start Again
  When user clicks "yes"
  And is on the Challenge screen

Scenario: 434371 AC1 Add to basket CTA
  Given user navigates to the Choose Your Flavours screen
  And he sees a plus CTA on at least one flavour
  When he clicks on the plus CTA
  Then a pop up opens with strength details
@isslive
Scenario: 449974 AC1 Tablet sticky header
  Given user navigates to the Choose Your Flavours screen
  When staff scrolls down
  Then header should be sticky
  And colleague should be able to see header details like "tier"
  And colleague should be able to see header details like "price per month"
  And colleague should be able to see header details like "basket"
@isslive
Scenario: 434224 AC1 Choose flavours search suggestions
  Given user navigates to the Choose Your Flavours screen
  And enters "searchTermIss1" into the search box
  Then a list of flavour suggestions is displayed
  And he is able to select flavour number "1"
  And the correct product is displayed
  @isssmoke @isslive
  Scenario: 434224 AC2 Choose flavours search
  Given user navigates to the Choose Your Flavours screen
  And performs a search for "searchTermIss1"
  Then a list of flavour suggestions is not displayed
  Then the search results page is displayed

  Scenario: 452432 OOS SKU strength should be strikethrough
    Given user revisits BAT home page
    Given sku "oosSkuSearchTerm" is "out of stock" stock
    Given user resumes ISS home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    Then the user cannot add that sku to his basket
    Given sku "oosSkuSearchTerm" is "in stock" stock

  Scenario: 447064 AC1 Selecting strength & adding quantity from pop-up
    Given user navigates to the Choose Your Flavours screen
    When he clicks on the plus CTA
    Then a pop up opens with strength details
    Then pack quantity is displayed with CTAs to update
    And an add CTA is displayed
    And add CTA "is not" clickable
    When I add a quantity
    Then add CTA "is" clickable

  Scenario Outline: 434641 AC1 AC3 AC4 AC5 Subscription Tiers
    Given user navigates to the Choose Your Flavours screen
    Then he should see "N/A" in front of the Tier when no items are added
    And he should see a clickable i icon in front of the tier
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "<tier>" discount
    And closes the basket overlay
    Then the choose your flavours header indicates "<tier>" tier
    @isssmoke @iss @isslive
    Examples:
    | tier   |
    | bronze |
    @iss
    Examples:
    | tier   |
    | silver |
    | gold   |

  Scenario: 434641 AC2 Tier information
    Given user navigates to the Choose Your Flavours screen
    When he clicks on the clickable i icon
    Then the subscription tiers overlay is displayed
    And the description of each tier is displayed
    When x is clicked he should navigate back to the choose your flavours page
  @isssmoke @isslive
  Scenario: 434040 AC1 Filters
    Given user navigates to the Choose Your Flavours screen
    When he clicks on the link Filters
    Then a flyout opens
    And the flyout has a Devices section
    And the device counts are correct
    And the flyout has a Strengths section for different strengths
    And the strength count is correct

  Scenario Outline: 434040 AC2 Filter selection
    Given user navigates to the Choose Your Flavours screen
    When he clicks on the link Filters
    And selects "<facet name>" facet value "<facet value>"
    And clicks on Done
    Then all results contain the "<facet name>" attribute "<facet value>"
    @isslive @iss
    Examples:
    | facet name  | facet value |
    | device      | ePod        |
    Examples: @iss
    | facet name  | facet value |
    | device      | ePen        |
    | strength    | 0mg         |
    | strength    | 3mg         |
    | strength    | 6mg         |
    | strength    | 12mg        |
    | strength    | 18mg        |

  Scenario Outline: 434040 AC3 Clearing filters
    Given user navigates to the Choose Your Flavours screen
    And records the number of products displayed
    When he clicks on the link Filters
    And selects "<facet>" facet value "<facet value>"
    And clicks on Done
    Then the number of products displayed have reduced
    When he clicks on the link Filters
    And clicks on "<facet>" clear filter
    And clicks on Done
    Then the number of products displayed is as previously recorded
    Examples:
    | facet    | facet value |
    | device   | epod        |
    | device   | epen        |
    | strength | 0mg         |
    | strength | 3mg         |
    | strength | 6mg         |
    | strength | 12mg        |
    | strength | 18mg        |

 Scenario: 447077 AC1 Subscription total and discount display
   Given user navigates to the Choose Your Flavours screen
   And adds "0" items to the basket
   Then he sees the price per month message correctly updated
   And adds "1" items to the basket
   Then he sees the price per month message correctly updated
   And adds "3" items to the basket
   Then he sees the price per month message correctly updated
   And adds "1" items to the basket
   Then he sees the price per month message correctly updated
   And adds "1" items to the basket
   Then he sees the price per month message correctly updated
   And adds "3" items to the basket
   Then he sees the price per month message correctly updated
   And adds "1" items to the basket
   Then he sees the price per month message correctly updated
   And adds "4" items to the basket
   Then he sees the price per month message correctly updated
   And adds "1" items to the basket
   Then he sees the price per month message correctly updated
@isslive
Scenario: 485148 447069 List of flavours for subscription to be shown via API
  Given user navigates to the Choose Your Flavours screen
  And the user notes the product name price and strengths for each
  When user visits the BAT home page
  And select allow all from cookie popup and select over 18 age confirmation option
  When user navigates to the flavours page
  And user closes the alert if present
  And the user notes the product name price and strengths for each on the main site
  Then the same subscription flavours appear on both with the same prices and strengths
