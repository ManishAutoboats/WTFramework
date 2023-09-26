Feature: payment related

  Scenario: 9605 Ordering - Check Out - Review Order-Vuse
    And url contains 'checkoutUrl.key'
    And assert text of 'addressHeading.key' is displayed
    And assert text of 'ShippingMethodHeading.key' is displayed
    And assert text of 'paymentMethodText.key' is displayed
    And assert text of 'orderSummaryText.key' is displayed
    And assert that one block summary div is displayed
    And assert items are displayed within the summary plain
    And selectable shipping options displayed
