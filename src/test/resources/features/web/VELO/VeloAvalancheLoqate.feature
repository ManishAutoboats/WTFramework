Feature: Velo Avalanche Loqate

# descoped for PL MVP
Scenario Outline: 689090 VELO PL> Loqate - Edit billing address
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    Then users clicks on the 'addressBook' text link
    And users clicks on Edit "billing" Address link
    And populate address fields
    And user saves the "billing" address
    And validation success message displayed
    Examples:
        | language  |
        | pl        |

@veloBeReg
Scenario Outline: 689090 VELO BE> Loqate - Edit billing address
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    Then users clicks on the 'addressBook' text link
    And users clicks on Edit "billing" Address link
    And populate address fields
    And user saves the "billing" address
    And validation success message displayed
    Examples:
        | language  |
       | en        |
       | fr        |
       | nl        |

    @veloZAReg
    Scenario Outline: VELO ZA> Loqate - Edit billing address
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then users clicks on the 'addressBook' text link
        And users clicks on Edit "billing" Address link
        And populate address fields
        And user saves the "billing" address
        And validation success message displayed
        Examples:
            | language  |
            | za        |


@veloBeReg
Scenario Outline: 689090 VELO BE> Loqate - Edit shipping address
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And the user navigates to My Account Page
    Then users clicks on the 'addressBook' text link
    And users clicks on Edit "shipping" Address link
    And populate address fields
    And user saves the "shipping" address
    And validation success message displayed
    Examples:
        | language  |
        | en        |
        | fr        |
        | nl        |

    @veloZAReg
    Scenario Outline: VELO ZA> Loqate - Edit shipping address
        Given user navigates to BAT home page for language "<language>"
        And select allow all from cookie popup and select over 18 age confirmation option
        And user clicks the person icon
        When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
        And the user navigates to My Account Page
        Then users clicks on the 'addressBook' text link
        And users clicks on Edit "shipping" Address link
        And populate address fields
        And user saves the "shipping" address
        And validation success message displayed
        Examples:
            | language  |
            | za        |


    Scenario Outline: VELO ZA> Loqate - add address during checkout
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And checkout any available product
    And login with valid details
    Then I am on the checkout page
    And click add new address button
    When user start entering the address in checkoutOverlay with streetKeyword.key
    When user has selected an address from the auto-completion
    And click use this address
    And assert the address has been set
    Examples:
        | language  |
        | za        |

