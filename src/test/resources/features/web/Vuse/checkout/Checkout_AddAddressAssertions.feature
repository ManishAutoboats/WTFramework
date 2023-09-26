    Feature: BAT Checkout - Add/Change Address Assertions
    Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account via api and log in with the account
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed

    @VuseZAReg
    Scenario: Checkout - Assert Phone Number field on Add New Address pop-up and Address section under Card Holder Details
      Then user click on change address link
      Then assert phone number field is displayed as a mandatory field on Add Address pop-up
      And click use this address
      Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
      And populate address fields on Checkout page
      Then user clicks on Change Address link below card holder details
      And user selects New Address radiobutton and assert address form is populated
      Then assert phone number field is displayed as a mandatory field on Add Address form
      Then user populates address details using loqate including phone number and save
      And user makes a purchase with "Sid Secure EFT" on Payment page
      Then assert that the 'ThankForPurchase.key' thank you message is displayed in the page header