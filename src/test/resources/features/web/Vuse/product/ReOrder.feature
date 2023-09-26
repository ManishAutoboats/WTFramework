Feature: 20395,22130,9736 - BAT reorder feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
      
@VuseUKReg @VuseMXReg
  Scenario Outline: Reorder CTA on View Order/Transaction Details Page
    Then user is successfully logged in
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    And assert My Orders Table is displayed
    And assert My Orders Table headings are as expected
    And assert My Order table data is as expected
    And users clicks on the View Order link
    And users clicks on the ReOrder link
    And url contains 'viewOrderURLcontains.key'
    Examples:
      | LinkToClick                   | UrlToContain          |
      | recentlyOrderedText.key       | recentOrderUrlContains.key        |

@VuseFRReg @VuseMXReg
  Scenario: Assert Print Order and Back to My Orders Functionality
    And users clicks on the 'recentlyOrderedText.key' text link
    And users clicks on the View Order link
    And assert Back To My Orders link redirects to correct URL
    And users clicks on the Print Order link
    And assert Order Confirmation PDF is displayed with order details

