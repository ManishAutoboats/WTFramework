Feature: Verify Age verification popup

  Background: User navigates to BAT homepage
    Given user navigates to BAT home page

  @gloJPReg
  Scenario Outline: Verify when user is not logged in and lands on M2 pages
    And user lands on '<page>'
    And assert entry age popup is displayed
    And assert accept and deny CTA is displayed
    And user selects not over 18 age confirmation option
    And assert entry age error message displayed
    And user selects over 18 age confirmation option on homepage
    And user is redirected to homepage
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And verify user lands on M2 '<pages>' after login
    Examples:
      | page                  | pages               |
      | checkout              | basketURL.key       |
      #| order history         | orderHistoryUrl.key |
      | order detail          | orderHistoryUrl.key |
      | historic orders       | orderHistoryUrl.key |
      #| historic order detail | orderHistoryUrl.key |


