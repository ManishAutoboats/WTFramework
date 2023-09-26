Feature: Velo Avalanche My Account Page
    @veloPLReg
    Scenario Outline: 703992 VELO PL AC1 My Account
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then the user can see "account overview" on the my account page
        And the user can see "welcome back" on the my account page
        And the user can see "account details heading" on the my account page
        And the user can see "first and last name" on the my account page
        And the user can see "email address" on the my account page
        And the user can see "edit details cta" on the my account page
      #  And the user can see "change password cta" on the my account page
        And the user can see "order history heading" on the my account page
        And the user can see "order history table" on the my account page
       # And the user can see "view order history button" on the my account page
        And the user can see "address book heading" on the my account page
        And the user can see "default billing address heading" on the my account page
        And the user can see "default delivery address heading" on the my account page
        And the user can see "edit address button" on the my account page
        And the user can see "left nav my account" on the my account page
        And the user can see "left nav my details" on the my account page
        And the user can see "left nav order history" on the my account page
        And the user can see "left nav address book" on the my account page
        And the user can see "left nav legal consents" on the my account page
        And the user can see "left nav delete account" on the my account page
        And the user can see "left nav sign out" on the my account page
        Examples:
            | language  |
            | pl        |

@veloBeReg
Scenario Outline: 700862 AC1 My Account
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
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
    And the user can see "order history heading" on the my account page
    And the user can see "order history table" on the my account page
    And the user can see "view order history button" on the my account page
    And the user can see "address book heading" on the my account page
    And the user can see "default billing address heading" on the my account page
    And the user can see "default delivery address heading" on the my account page
    And the user can see "edit address button" on the my account page
    And the user can see "left nav my account" on the my account page
    And the user can see "left nav my details" on the my account page
    And the user can see "left nav order history" on the my account page
    And the user can see "left nav address book" on the my account page
    And the user can see "left nav payment methods" on the my account page
    And the user can see "left nav delete account" on the my account page
    And the user can see "left nav sign out" on the my account page
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |

    @veloZAReg
    Scenario Outline:  VELO ZA AC1 My Account
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
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
        Examples:
            | language  |
            | za        |


    @veloBeReg
    Scenario Outline: 926870 No error display when user login and open My Account page
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And assert text of 'myAccountPageErrorHeading' is not displayed for language "<language>"
        Examples:
            | language  |
            | en        |
            | nl        |
            | fr        |

    @veloPLReg @veloPLLive
    Scenario Outline: VELO PL 703992 AC2 My Account - my details
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then the user can see "account overview" on the my account page
        When I click on "left nav my details" tab
        Then I should see "personal details" on the my details page
    # not implemented yet
#    Then I should see "edit CTA for email" on the my details page
        Then I should see "edit CTA for password" on the my details page
        Then I should see "save changes CTA" on the my details page
    # not implemented yet
#    When I click on edit email
 #   Then the editable email fields show on the same page
#    When I click on edit password
#    Then the editable password fields show on the same page
        Examples:
            | language  |
            | pl        |

    @veloZAReg @veloZALive
    Scenario Outline: VELO ZA  AC2 My Account - my details
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then the user can see "account overview" on the my account page
        When I click on "left nav my details" tab
        Then I should see "personal details" on the my details page
        Then I should see "edit CTA for password" on the my details page
        Then I should see "save changes CTA" on the my details page
        Examples:
            | language  |
            | za        |

@veloBeReg @veloBELive
Scenario Outline: 700862 AC2 My Account - my details
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    Then the user can see "account overview" on the my account page
    When I click on "left nav my details" tab
    Then I should see "personal details" on the my details page
    Then I should see "edit CTA for password" on the my details page
    Then I should see "save changes CTA" on the my details page
    When I click on edit password
    Then the editable password fields show on the same page
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |

    @veloPLReg
    Scenario Outline: VELO PL 703992 AC3.1 AC3.2 My Account - order history
        Given user navigates to BAT home page for language "pl"
        And create a new account via api
        And select allow all from cookie popup and select over 18 age confirmation option
        And empty basket before adding related products
        And checkout any available product
        Then I am on the checkout page
        And I add the delivery address during payment
        And I see an expandable card payments tab in this section
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
            |Payment Type| basket heading       |
            | visa        | basketHeadingText-pl |

    @veloZAReg
    Scenario Outline: VELO ZA  AC3.1 AC3.2 My Account - order history-buy again and sid payment
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
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

    @veloPLReg
    Scenario: 741017 Velo PL AC2 > 10% Discount for Purchase more than 5 item
        Given user navigates to BAT home page for language "pl"
        And create a new account via api
        And select allow all from cookie popup and select over 18 age confirmation option
        And checkout more than "5" available product and get discount



@veloBeReg
Scenario Outline: 700862 AC3.1 AC3.2 My Account - order history
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And empty basket before adding related products
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
        | language  | basket heading       |
        | en        | basketHeadingText-en |
        | nl        | basketHeadingText-nl |
        | fr        | basketHeadingText-fr |

    @veloPLReg
    Scenario Outline: VELO PL 703992 AC4.1 My Account - address book
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
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
        Examples:
            | language  |
            | pl        |

    @veloZAReg
    Scenario Outline: VELO ZA AC4.1 My Account - address book
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
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
        Examples:
            | language  |
            | za       |


    @veloPLReg
    Scenario Outline: VELO ZA My Account - address book
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
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
        Examples:
            | language  |
            | za        |
@veloBeReg
Scenario Outline: 700862 AC4.1 My Account - address book
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
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
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |
    @veloPLReg
    Scenario: 761231 VELO PL AC4.2 My Account - address book edit
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
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

    @veloZAReg
    Scenario: VELO ZA AC4.2 My Account - address book edit
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
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


@veloBeReg
Scenario Outline: 700862 AC4.2 My Account - address book edit
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
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
    And I add other address again
    When I click on edit other address
    Then I am directed to the edit address page
    And there is a CTA to save my changes
    And I close the address modal
       Examples:
           | language  |
           | en        |
           | nl        |
           | fr        |
    @veloPLReg
    Scenario: VELO PL AC4.3 My Account - address book other address remove and add
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
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

    @veloZAReg
    Scenario: VELO ZAL AC4.3 My Account - address book other address remove and add
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
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


@veloBeReg
Scenario Outline: 700862 AC4.3 My Account - address book remove
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And empty basket before adding related products
    And the user navigates to My Account Page
    When I click on "left nav address book" tab
    And remove other addresses until only has one address
    And I add other address again
    When I click on remove other address
    Then I am asked if I am sure
    When If i click cancel
    Then the other addresses remain unaltered
    When I click on remove other address
    And I click yes I am sure
    Then the other addresses are reduced by one
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |
@veloBeReg
Scenario Outline: 700862 AC5.1 My Account - saved payment methods
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    And I have previously saved my payment methods
    And the user navigates to My Account Page
    When I click on "left nav saved payment methods" tab
    Then I am able to see all my saved methods on this page
    And there is an Edit and remove CTA for each payment method
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |

@veloBeReg
Scenario Outline: 700862 AC5.3 My Account - saved payment methods
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    And I have previously saved my payment methods
    And the user navigates to My Account Page
    When I click on "left nav saved payment methods" tab
    And I click on the edit payment method CTA
    Then I am redirected to the edit payment method page
    And I can add all the details and save my payment method
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |

    @veloPLReg @veloPLLive
    Scenario: VELO PL 703992 My Account - sign out
        Given user navigates to BAT home page for language "pl"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And I am logged out
        Then I am returned to the web shop home page

    @veloZAReg @veloZALive
    Scenario: VELO ZA  My Account - sign out
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        And I am logged out
        Then I am returned to the web shop home page

    @veloPLReg
    Scenario Outline: 714605 VELO PL My Account - NewsLetter
        Given user navigates to BAT home page for language "pl"
        And create a new account via api
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon after account created
        And the user navigates to My Account Page
        When I click on "left nav legal consents" tab
        Then assert that Newsletter checkbox is 'newsletterCheckboxChecked.key' selected
        Examples:
            | language  |
            | pl        |

@veloBeReg @veloBELive
Scenario Outline: 700862 AC7 My Account - sign out
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    When I click on "left nav sign out" tab
    And I am logged out
    Then the page heading is correct
    Examples:
        | language  |
        | en        |
        | nl        |
        | fr        |

   @veloZAReg
    Scenario: VELO ZA-Delete the user and create the same user again
        Given user navigates to BAT home page for language "za"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        And create a new account
       And the user navigates to My Account Page
       And user clicks on delete account
       And user clicks the person icon
       And user login with credentials
       And assert error message of invalid login is displayed for language "za"
       And create same account after deleting the user




