#This is the create account feature
Feature: BAT Address management - create address
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then fetch First and Last Names of the logged-in user
    And users clicks on the 'yourAddressText.key' text link

  @LyftRegression @LyftDKReg
  Scenario: Add Address - Page Check
    Then assert text of 'addressHeading.key' is displayed
    Then assert text of 'defaultBillingAddressText.key' is displayed
    Then assert text of 'defaultShippingAddressText.key' is displayed
    And add new Address button displayed
    And click on my account new address button
    And confirm Add address form is presented to user

  Scenario: Add Address - validation Errors
    And click on my account new address button
    #And ensure Add Address page is loaded
    And user clicks on Save address without populating any default fields
    Then page displays expected errors

  @LyftRegression2 @LyftDKReg
  Scenario: Add Address - Go Back
    And click on my account new address button
    Then ensure Add Address page is loaded
    And click on back button
    And user is returned to the base Address book page

  #@LyftLive
  Scenario: Add Address - create new default billing address overwrite first last name and assert entry included on Your address page
    And add new Address button displayed
    And click on my account new address button
    #And ensure Add Address page is loaded
    And populate all address fields including new first and last name
    And tick default billing address
    Then click the save address button
    Then user is returned to the base Address book page
    Then assert new first and surname are present

  #@LyftSmoke  @LyftLive @LyftDKLive
  Scenario: Add Address - create new default shipping address and assert entry included on Your address page
    And add new Address button displayed
    And click on my account new address button
    #And ensure Add Address page is loaded
    And populate all address fields including new first and last name
    And tick default shipping address
    And click the save address button
    Then user is returned to the base Address book page
    Then assert new first and surname are present

  @LyftRegression
  Scenario: Add Address Successfully
    And click on my account new address button
    And ensure Add Address page is loaded
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page
