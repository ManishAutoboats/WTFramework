Feature: Velo Avalanche Loqate - Mobile

    Background: Velo Avalanche Loqate
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option

    @RegVeloZA_Mobile
    Scenario: VELO ZA> Loqate - Edit billing address
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then users clicks on the 'addressBook' text link
        And users clicks on Edit "billing" Address link
        And populate address fields
        And user saves the "billing" address
        And validation success message displayed


    @RegVeloZA_Mobile
    Scenario: VELO ZA> Loqate - Edit shipping address
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then users clicks on the 'addressBook' text link
        And users clicks on Edit "shipping" Address link
        And populate address fields
        And user saves the "shipping" address
        And validation success message displayed


    Scenario: VELO ZA> Loqate - add address during checkout
      And checkout any available product
      And login with valid details
      Then I am on the checkout page
      And click add new address button
      When user start entering the address in checkoutOverlay with streetKeyword.key
      When user has selected an address from the auto-completion
      And click use this address
      And assert the address has been set

