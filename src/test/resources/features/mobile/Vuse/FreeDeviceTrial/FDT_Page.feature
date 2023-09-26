Feature: Free Device Trial - Checkout Mobile

  Background: Navigate to Free Device Trial Landing page
    Given user navigates to Free Device Trial landing page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Free Device Trial - Landing page validation
    And user to see Brief info on Trial product
    And User to see the steps for placing a device trail order
    And user to see info about Trial delivery, Payment and related details
