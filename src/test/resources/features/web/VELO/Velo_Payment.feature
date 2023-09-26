Feature:  Checkout Purchase velo

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on PersonIcon and Navigate to the Login Page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'

 @veloDEReg
  Scenario: Mastercard Payment - Velo
    And Customer makes a home delivery purchase with "mastercard"
    And grab and output Order number
    And Order Confirmation page thankYouMessageHeading is displayed

  Scenario: Paypal Payment - Velo
    And Customer makes a home delivery purchase with "paypal"
    And grab and output Order number
    And Order Confirmation page thankYouMessageHeading is displayed

 @veloDEReg
  Scenario: Visa Payment - Velo
    And Customer makes a home delivery purchase with "visa"
    And grab and output Order number
    And Order Confirmation page thankYouMessageHeading is displayed
