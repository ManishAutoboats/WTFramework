Feature: Glo Checkout - newsletter subscription - get 4 free products

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #@gloDeRegression blocked by bug 479857
  Scenario: Glo Checkout - newsletter subscription - get 4 free products
    And create a new account
    And Glo user clicks on Shop link and click on 'glo™ TABAK HEATER' category link
    And click on first result
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And Payment page details confirmed
    And user choose shipping method
    And user select Card Payment option
    And user select credit card as payment option
    And user select the Visa card as type
    And enter visa card details
    Then user should see a checkbox with option to subscribe to newsletter with text checkoutNewsLetterSubText.key
    When user select the newsletter subscription checkbox
    And select place order from Card
    And grab and output Order number
    Then Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
    When users clicks on order number to be taken to order view order page
    Then assert free 4 trail packs of the product are added to the order with sku's freeProductSkuList.key
    And assert status of the order is "processing" via api