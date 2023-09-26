Feature:  Checkout Purchase velo
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on PersonIcon and Navigate to the Login Page
    And create a new account

  #@veloDEReg  after  creating new account  100% discount is appearing on cart
  Scenario: Mastercard Payment - Velo
    And Customer makes a home delivery purchase with "savedcard"
    And Epok grab and output Order number
    Then Epok assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed

