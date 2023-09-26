@epokCheckout @epokRegression @epokSmoke @checkout
Feature: Epok checkout feature - CreditCard

 Background: Navigate to BAT Home page
   Given user navigates to BAT home page
   And select allow all from cookie popup and select over 18 age confirmation option
   And user click on PersonIcon and Navigate to the Login Page
   When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
   And Epok empty basket
   When user click on humberger menu
   When user clicks on product menu
   Then user Clicks on add to cart button
   Then validate Success Message of add to cart product
   And user click on cart icon and Navigate to cart Page
   And Go to checkout page
   And Epok Payment page details confirmed
  
  
 @CreditCard1  
  Scenario: Epok Checkout tests - credit card
   And Epok user select Card Payment option 
   And Epok user select credit card as payment option
   And user select the Master card as type
   And user fills Master or Visa card information
   And Epok select agree to terms and conditions for card payment
   Then Epok select place order from Card
   And Epok grab and output Order number
   And Epok assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
