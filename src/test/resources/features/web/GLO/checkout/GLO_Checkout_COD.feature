Feature: Glo checkout feature -COD
  Background: Navigate to BAT Home page
    When user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'


  @gloKzRegression @gloKzSmoke
  Scenario: Glo Checkout tests - Cash on Delivery
    And user closes the RDB pop-up banner if present
    And empty basket
    And Glo user clicks on buy button link and click on submenu
    And click on first result
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And Payment page details confirmed
    And user choose shipping method
    And Select COD CheckBOX
    Then select place order from Card
    And grab and output Order number
    And Order Confirmation page thankYouMessageHeading is displayed


  @gloPlRegression
  Scenario: Glo Poland Checkout tests - Cash on Delivery
    And empty basket
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    And user clicks on the View Basket cta
    And use add free gift module according to device
    And click on proceed to checkout button
    And Select COD CheckBOX
    Then select place order from Card
    And grab and output Order number
    And Order Confirmation page thankYouMessageHeading is displayed

  @gloKzLive
  Scenario: Glo Checkout- cash on delivery option is present
    And user closes the RDB pop-up banner if present
    And empty basket
    And Glo user clicks on buy button link and click on submenu
    And click on first result
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And Payment page details confirmed
    And user choose shipping method
    And assert COD option is present