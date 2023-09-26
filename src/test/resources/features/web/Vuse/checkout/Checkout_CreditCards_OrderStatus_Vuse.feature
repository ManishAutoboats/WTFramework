  Feature: BAT Order status feature
    Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseFRReg @VuseITReg
  Scenario Outline: Order Status tests - Debit Card Payment - Pending Payment section
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "<Payment Type>"
    Then assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page

    Examples:
      | Payment Type |
      | mastercard   |

  @VuseDEReg
  Scenario: Order Status tests - Debit Card Payment - email confirmation
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "mastercard"
    Then assert text of 'ThankForPurchase.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And assert status of the order is "processing" via api
    And user should receive a order confirmation email with order number

    @VuseZAReg
    Scenario: Order Status tests -Sid Secure EFT - email confirmation
      And create a new account via api and log in with the account
      And Customer makes a home delivery purchase with "Sid Secure EFT"
      Then assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
      And grab and output Order number
      Then user should receive a order confirmation email with order number

    @VuseZAReg
    Scenario: Checkout tests - Signed In - Credit Cards - VISA Card
      When create a new account via api and log in with the account
      And user click on search icon and submits the following search term 'searchTerm.key'
      And click first result
      And select product strength or colour
      Then click add to cart button
      And click on basket icon
      And click on proceed to checkout button
      And Payment page details confirmed
      And click on paygate payment radio button
      And select place order
      And proceed with DPO Card payment
      And assert text of 'ThankForPurchase.key' is displayed
      And assert text of 'yourOrderNumberText.key' is displayed
