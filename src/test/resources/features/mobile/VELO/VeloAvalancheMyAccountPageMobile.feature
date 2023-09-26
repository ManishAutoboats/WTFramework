Feature: Velo Avalanche My Account Page - Mobile

    Background:Velo Avalanche My Account Page
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option


    @RegVeloZA_Mobile
    Scenario:  VELO ZA AC1 My Account
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then the user can see "account overview" on the my account page
        And the user can see "welcome back" on the my account page
        And the user can see "account details heading" on the my account page
        And the user can see "first and last name" on the my account page
        And the user can see "email address" on the my account page
        And the user can see "edit details cta" on the my account page
        And the user can see "change password cta" on the my account page
        And the user can see "left nav my account" on the my account page
        And the user can see "left nav my details" on the my account page
        And the user can see "left nav order history" on the my account page
        And the user can see "left nav address book" on the my account page
        And the user can see "left nav market preferances" on the my account page
        And the user can see "left nav delete account" on the my account page
        And the user can see "left nav sign out" on the my account page


    @RegVeloZA_Mobile
    Scenario: VELO ZA  AC2 My Account - my details
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then the user can see "account overview" on the my account page
        When I click on "left nav my details" tab
        Then I should see "personal details" on the my details page
        Then I should see "edit CTA for password" on the my details page
        Then I should see "save changes CTA" on the my details page


    @RegVeloZA_Mobile
    Scenario Outline: VELO ZA  AC3.1 AC3.2 My Account - order history-buy again and sid payment
        And create a new account via api and log in with the account
        And checkout any available product
        Then I am on the checkout page
        And I add the delivery address during payment
        And Customer makes payu payment with "<Payment Type>"
        And the user navigates to My Account Page
        Then the user can see "account overview" on the my account page
        When I click on "left nav order history" tab
        Then I should see a history of all orders on the page
        And there is a view order CTA next to each order
        And on clicking the View Order CTA for any order I am redirected to Order Details Page
        And I make a note of the order contents
        And there is a buy again CTA
        When I click on the buy again CTA
        And confirm that the heading "<basket heading>" is displayed
        And all products are automatically added to the basket
        Examples:
            | Payment Type   | basket heading       |
            | Sid Secure EFT | basketHeadingText-za |



    @RegVeloZA_Mobile
    Scenario: VELO ZA AC4.1 My Account - address book
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And empty basket before adding related products
        And the user navigates to My Account Page
        Then the user can see "account overview" on the my account page
        When I click on "left nav address book" tab
        Then I am on the address book page
        Then I should see my default shipping and billing address
        And I should see other address info
        And there should be a CTA to edit any of the addresses
        And only other addresses has a remove button



    @RegVeloZA_Mobile
    Scenario: VELO ZA AC4.2 My Account - address book edit
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And empty basket before adding related products
        And the user navigates to My Account Page
        When I click on "left nav address book" tab
        When I click on edit default billing address
        Then I am directed to the edit address page
        And there is a CTA to save my changes
        And I close the address modal
        When I click on edit default delivery address
        Then I am directed to the edit address page
        And there is a CTA to save my changes
        And I close the address modal
        When I click on edit other address
        Then I am directed to the edit address page
        And there is a CTA to save my changes
        And I close the address modal



    @RegVeloZA_Mobile
    Scenario: VELO ZAL AC4.3 My Account - address book other address remove and add
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        When I click on "left nav address book" tab
        When I click on remove other address
        Then I am asked if I am sure
        When If i click cancel
        Then the other addresses remain unaltered
        When I click on remove other address
        And I click yes I am sure
        Then the other addresses are reduced by one
        And I add other address again



    @RegVeloZA_Mobile
    Scenario: VELO ZA  My Account - sign out
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And I am logged out
        Then I am returned to the web shop home page


   @RegVeloZA_Mobile
    Scenario: VELO ZA-Delete the user and create the same user again
       And user clicks the person icon
       And create a new account
       And the user navigates to My Account Page
       And user clicks on delete account
       And user clicks the person icon
       And user login with credentials
       And assert error message of invalid login is displayed for language "za"
       And create same account after deleting the user
