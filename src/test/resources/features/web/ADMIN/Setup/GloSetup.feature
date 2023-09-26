@smokeGloDEAdmin @smokeGloITAdmin  @gloDeAdminSmoke  @gloItAdminSmoke
Feature: Glo-Setup prerequirement for magento admin cases
  Background: Navigate to BAT Home Page


  Scenario: Registration of new user for GLO brand
    And tearDownBAT
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    And GloIt user clicks on PersonIcon and Navigate to the Login Page
    And user clicks the registration button
    And add personal data for glo user with date of birth 'validDOB.key' and gender 'gender.key'
    And add postal code field with 'codiceTax.key'
    And add home town field with 'homeTown.key'
    And add telephone number field with 'validPhoneNumber.key'
    And populate glo address fields 'addressKeyword.key'
    And add province field with 'province.key'
#    And populate signin fields for Glo
    And populate signin fields with specific email for Glo
    And tick agree to terms and conditions of glo on registration page
    Then user selects the create an account button
    And assert text of 'registrationSuccessMsg.key' is displayed

  Scenario:Admin-Glo-Customer search result is displayed
    When user logins Magento Admin home page successfully
    And user navigates to all customers page
    And user searches customer by search keyword
    Then search result is displayed correctly
