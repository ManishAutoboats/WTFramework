Feature: BAT checkout billing information Vuse - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account via api and log in with the account
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed


  Scenario: Mobile - 9608 Ordering - Check Out - Billing Information-Vuse
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And assert billing and shipping are the same tick box present
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed


  @RegVuseFR_Mobile
    Scenario: Mobile - 9607 15993 Ordering - Check Out - Change Address -Vuse FR
      And Credit Card - MasterCard option and press next
      And enter mastercard details
      And assert new address button is displayed
      And click add new address button
      And assert text of 'firstNameText.key' is displayed