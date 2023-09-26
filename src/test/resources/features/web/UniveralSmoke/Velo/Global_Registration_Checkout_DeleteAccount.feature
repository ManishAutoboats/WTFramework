Feature: BAT EYES Mobile and Desktop registration to Card Purchase velo
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @UniversalSmokeVelo
  Scenario: Smoke Flow - Login - Visa Payment - Velo
    And take eyes screenshot
      | LanguageSelectorPage   |
      | RegistrationPage       |
      | EmptyCheckoutCartPage  |
      | ContactUsPage          |
      | AboutVeloPage          |
      | ProductsPlpPage        |
      | NewsAndInformationPage |
      | StoreLocatorPage       |
    And user click on PersonIcon and Navigate to the Login Page
    When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And Customer makes a home delivery purchase with "mastercard"
    Then grab and output Order number
    Then Order Confirmation page thankYouMessageHeading is displayed

