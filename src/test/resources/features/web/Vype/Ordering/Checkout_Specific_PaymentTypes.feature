Feature: BAT checkout feature - DEBIT CARDS
  Background: Navigate to BAT Home page
    #And resize browser screen - full screen
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    #And get all lists from page

    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed

  @COReg @COSmoke
  Scenario: Checkout tests - Signed In -Open Pay PSE Option - Payment
    And selectable shipping options displayed
    And click on shipping method
    And enter select package shop details
    And enter open pay PSE details
    And place Open Pay Order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And grab and output Order number
    And user clicks the person icon
    And users clicks on the 'recentlyOrderedText.key' text link
    And assert order number is displayed Previous orders on page
    And grab contents of matched row and assert order status


    #@VuseZAReg we have similar scenario in Checkout_creditcard_orderStatus_vuse.feature
    Scenario: Checkout test-Sid Secure EFT
      And Customer makes a home delivery purchase with "Sid Secure EFT"
      And assert text of 'ThankForPurchase.key' is displayed
      And grab and output Order number
