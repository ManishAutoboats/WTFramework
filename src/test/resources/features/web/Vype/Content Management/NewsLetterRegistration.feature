#This is the newsletter feature file
  ##Testing
    ## Invalid email entry into the newsletter sub
    ## Entering existing email into newsletter sub
    ## Entering valid (random) email into newsletter sub
Feature: BAT newsletter Registration Feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @newsletter @regression
 Scenario: NewsLetter - Register and sign
   And create a new account
   And user clicks the person icon
   And user clicks on 'myAccountLink.key' link from Person Menu
   And select from myAccount links NewsLetter link
   And assert status of marketing tickbox

  @MXReg2
  Scenario: NewsLetter - Register Newsletter Subscription and sign
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate DOB field with 'ValidDOB.key'
    And populate signin fields
    And tick signup for newsLetter tick box
    And user selects the create an account button without Email Verification script
    And users clicks on the 'newsLetterText.key' text link
    And assert status of marketing tickbox

  @ITReg2
  Scenario: Create account with consent mobile and newsletter
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields
    And tick signup for newsLetter tick box
    And tick signup for consentMobile tick box
    And user selects the create an account button without Email Verification script
    And users clicks on the 'newsLetterText.key' text link
#   And assert status of marketing tickbox