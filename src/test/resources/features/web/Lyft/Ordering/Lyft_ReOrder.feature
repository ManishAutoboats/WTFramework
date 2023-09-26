##This is the Reorder feature file
  ##Testing
    ## Account Order History
    ## Reorder functionality
 #@LyftRegression #TODO
Feature: 20395,22130,9736 - BAT reorder feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'

  Scenario Outline:Quick Order - Reorder
    Then user is successfully logged in
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Then assert My Orders Table is displayed
    And users clicks on the 'viewOrderLinkText.key' text link
    And click on a re-order link -Lyft
#    And url contains 'viewOrderURLcontains.key'
    Examples:
      | LinkToClick           | UrlToContain          |
      | recentOrderedText.key       | recentOrderUrlContains.key        |