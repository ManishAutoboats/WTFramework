# Repeatable Order
Feature: BAT Order status feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario Outline: Repeatable order
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    And Check alternative payment order option
    And Check paypal option
    And tick agree to terms and conditions
    And select place order
    And paypal page - click continue
    #And Credit Card - VISA order option and press next
    #And enter visa card details
    #And tick agree to terms and conditions
    #And select place order
    #And assert text of 'Thank you for your purchase!' is displayed
    #And assert text of 'Your order number is:' is displayed
    #And assert text of 'We'll email you an order confirmation with details and tracking info.' is displayed
    #And assert print receipt link is present
    And grab and output Order number
    #And user clicks on the 'continue shopping' button
    #And user clicks the person icon
    #And users clicks on the 'Recent Orders' text link
    #And assert order number is displayed Previous orders on page
    #And grab contents of matched row and assert order status
    Examples:
      | UserName           |
      #| 8x4oia@demo.com    |
      #| frzyom@demo.com    |
      #| rgp29z@demo.com    |
      #| liy3qc@demo.com    |
      #| t367rl@demo.com    |
      #| dfolvt@demo.com    |
      #| muo2fm@demo.com    |
      #| qnfuxb@demo.com    |
      #| exuzwv@demo.com    |
      #| nde6cz@demo.com    |
      #| 4glumn@demo.com    |
      #| agmmxb@demo.com    |
      #| xvhaht@demo.com    |
      #| lvfjz5@demo.com    |
      | emjhzf@demo.com    |

