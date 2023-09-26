Feature: In Store Subs - Basket Overlay
  Background: 432776 AC1 434642 AC1 452445 AC1 Navigate to home page
    Given user is at the In Store Subs home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
@iss
  Scenario: 514183 AC1 434373 AC1 Subscription Basket overlay - Set up Subscription
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    Then an overlay opens with item details
    And he can see "subtotal" details
    And he can see "tier discount" details
    And he can see "total" details
    And he can see "set up subscription button" details
@isssmoke @iss
  Scenario: 514183 AC2 434373 AC2 Subscription Basket overlay - Set up Subscription - remove item
    And he adds item 7 to the basket
    And he adds item 8 to the basket
    When he clicks on the basket CTA
    Then an overlay opens with item details
    When he clicks the remove icon in front of the item
    Then the item gets removed from the basket
@isslive
  Scenario Outline:514183 AC3 AC4 434373 AC3 AC4 increase and reduce quantity
    And he adds item 7 to the basket
    And he clicks on the basket CTA
    When he clicks the increase quantity icon "<increase by>" times
    And he clicks the decrease quantity item "<decrease by>" times
    Then the final total quantity for this item is "<item total>"
    @isssmoke @iss
    Examples:
      | increase by | decrease by | item total |
      | 3           | 3           | 1          |
      @iss
    Examples:
      | increase by | decrease by | item total |
      | 4           | 2           | 3          |

  Scenario: 578911 Subscription minimum quantity enforcement
    And adds "1" items to the basket
    When he clicks on the basket CTA
    Then a message "is" displayed instructing a minimum quantity of "minQty" is required for a subscription
    And the set up subscription button is "disabled"
    When he adds further items to a total of "minQty"
    Then a message "is not" displayed instructing a minimum quantity of "minQty" is required for a subscription
    And the set up subscription button is "enabled"
    And he is able to proceed to the subscription details page
