#@MXReg - Request to disable the MGM functionality for MX in Bug - 441028
Feature: BAT MGM - InVype A Friend feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: MGM Link to public program Landing Page
    Then user logins Magento Admin and assert MGM Verification Status of the customer
    And user navigates to BAT home page for FE validation
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "mastercard"
    And assert 'OrderNumberText.key' 'EmailConfirmationText.key' 'RegisterText.key' text is displayed
    When Logged in user navigate to InVype a friend link
    Then user navigate to MGM private landing page
    And assert text for 'WhatsappButtonText.key' 'EmailButtonText.key' is displayed
    And assert Whatsapp button and email option is displayed
    And assert Whatsapp page is Opened

  Scenario: MGM Public Landing Page and Navigation to Private Landing Page
    And assert InVype a friend link is displayed on header and footer
    And assert 'MGMLandingPage.key' is opened if user clicks on the InVypeLnk
    And assert Generate My Code Option is displayed
    And verify guest user navigate to 'LogInPage.key' if action perform on Generate My Code button
    When user signs in with credentials 'loginValidEmail.key' 'loginValidPassword.key'
    Then user navigate to MGM private landing page

  Scenario: MGM Sign Up Page - Validated phone number
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user fetch Referee name and customized link
    And assert Refer code option is displayed for guest user on Sign Up page
    And assert Invalid code error message is displayed
    And assert text of error message should be 'ReferErrorMessageText.key'
    And assert Referee name is displayed when user navigate to copied link

  Scenario: MGM Phone Number Validation Page in Private Section - non validated phone number
    And create a new account
    Then users clicks on the 'yourAddressText.key' text link
    Then user fetch the telephone number and enter duplicate value
    And verify the request code button and validate code button is available
    And verify messages when user enter the telephone number
    And I delete customer account