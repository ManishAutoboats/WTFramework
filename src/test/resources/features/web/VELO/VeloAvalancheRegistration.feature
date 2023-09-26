Feature: Velo Avalanche Registration Page

  @veloBeReg2
  Scenario Outline: 700723 AC1 VELO BE > Customer Registration
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    Given the form has the following fields marked as mandatory
      | first name       |
      | last name        |
      | email            |
      | dob              |
      | password         |
      | confirm password |
    And the following mandatory consents with a link to notice copy
      | privacy policy       |
      | terms and conditions |
    And the page has a create your account CTA
    Examples:
      | language |
      | en       |
      | nl       |
      | fr       |

  #noinspection GherkinBrokenTableInspection
  @veloZAReg @veloZALive
  Scenario Outline: AC1 VELO ZA > Customer Registration
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    Given the form has the following fields marked as mandatory
      | sa id number     |
      | first name       |
      | last name        |
      | email            |
      | dob              |
      | password         |
      | confirm password |
    And the following mandatory consents with a link to notice copy
      | newsletter  |
      | remember me |
      | gender      |
    And the page has a create your account CTA
    Examples:
      | language |
      | za       |


  @veloBEReg2 @veloBELive
  Scenario Outline: 700723 AC3 VELO BE > Customer Registration
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    And I have filled in all mandatory fields other than
      | firstName       |
      | lastName        |
      | email           |
      | dob             |
      | password        |
      | confirmPassword |
    Examples:
      | language |
      | en       |
      | nl       |
      | fr       |

  @veloZAReg @veloZALive
  Scenario Outline:  AC3 VELO ZA > Customer Registration
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    And I have filled in all mandatory fields other than
      | sa id number     |
      | firstName       |
      | lastName       |
      | email            |
      | dob              |
      | password         |
      | confirmPassword |
    Examples:
      | language |
      | za       |

  @veloPLReg @veloPLLive
  Scenario Outline: 703987 VELO PL | Customer Registration | AC 1 | Verify the registration form design and content
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    Given the form has the following fields marked as mandatory
      | first name       |
      | last name        |
      | email            |
      | password         |
      | confirm password |
    And the following mandatory consents with a link to notice copy
      | age                  |
      | terms and conditions |
    And the form has the following non mandatory marketing consents
      | email        |
      | mobile       |
      | social media |
    And the page has a create your account CTA
    Examples:
      | language |
      | pl       |


  Scenario Outline: 703987: VELO PL | Customer Registration | AC 2 | Verify the the double opt-in and AV process
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    And I have filled in all mandatory fields
    And I click on create your account CTA
    And I verify my account through email
#    And assert that you are now on the yoti age estimation page
#    And you cannot log in without completing yoti AV
    Examples:
      | language |
      | pl       |

  @veloPLReg @veloPLLive
  Scenario Outline: 703987: VELO PL | Customer Registration | AC 3 | Mandatory field check
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    And I have filled in all mandatory fields other than
      | firstName       |
      | lastName        |
      | email           |
      | password        |
      | confirmPassword |
    Examples:
      | language |
      | pl       |

  @veloBeReg2 @veloBELive
  Scenario Outline: 700859 AC1 VELO BE> Customer Login
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the page heading is correct
    And all the field labels are correct for language "<language>"
    Then I am able enter my email and password
    And there is a sign in CTA for me to login
    And there is a forgot password link
    And there is a create account button
    Examples:
      | language |
      | en       |
      | nl       |
      | fr       |
  @veloZAReg @veloZALive
  Scenario Outline:  AC1 VELO ZA> Customer Login
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And verify all the field labels are correct
    Then I am able enter my email and password
    And there is a sign in CTA for me to login
    And there is a forgot password link
    And there is a create account button
    Examples:
      | language |
      | za       |

  @veloZAReg
  Scenario Outline:  AC2 VELO ZA> Customer Login
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    Given I omit the following and click on sign in I get the correct error message
      | email    |
      | password |
      | both     |
    Examples:
      | language |
      | za       |

  @veloBeReg2
  Scenario Outline: 700859 AC2 VELO BE> Customer Login
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    Given I omit the following and click on sign in I get the correct error message
      | email    |
      | password |
      | both     |
    Examples:
      | language |
      | en       |
      | nl       |
      | fr       |

  @veloPLReg @veloPLLive
  Scenario Outline: 703991: VELO PL | Customer Login| AC 1 | Account Login-page Validations
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the Login page heading is correct
    And verify all the field labels are correct
    Then I am able enter my email and password
    And there is a sign in CTA for me to login
    And there is a forgot password link
    And there is a create account button
    Examples:
      | language |
      | pl       |

  @veloZAReg @veloZALive
  Scenario Outline:  VELO ZA | Customer Login| AC 1 | Account Login-page Validations
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the Login page heading is correct
    And verify all the field labels are correct
    Then I am able enter my email and password
    And there is a sign in CTA for me to login
    And there is a forgot password link
    And there is a create account button
    Examples:
      | language |
      | za       |


  @veloPLReg @veloPLLive
  Scenario Outline: 703991: VELO PL | Customer Login| AC 2 | Validate blank sign in
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When I am on the account log-in page
    And I have not filled in my personal details
    And I click on Sign In
    Then I should get an error message that my email and password are needed to login
    Examples:
      | language |
      | pl       |

  @veloZAReg @veloZALive
  Scenario Outline: VELO ZA | Customer Login| AC 2 | Validate blank sign in
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When I am on the account log-in page
    And I have not filled in my personal details
    And I click on Sign In
    Then I should get an error message that my email and password are needed to login
    Examples:
      | language |
      | za       |

  @veloPLReg
  Scenario: 703991: VELO PL | Customer Login| AC 3 | Verify Forgot Password
    Given user navigates to BAT home page for language "pl"
    And create a new account via api
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon after account created
    And the user navigates to My Account Page
    When I click on "left nav sign out" tab
        #And user clicks the person icon
    And the user navigates back to login page
    And user click on forgot password link
    Then user should be redirected to forgotYourPasswordTitle.key page
    And user email input box displayed and enabled
    And user enters email address registered
    And user click on submit button
    Then user should see the correct password reset message

 @veloZAReg
  Scenario:  VELO ZA | Customer Login| AC 3 | Verify Forgot Password
    Given user navigates to BAT home page for language "za"
    And create a new account via api
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user click on forgot password link
    Then user should be redirected to forgotYourPasswordTitle.key page
    And user email input box displayed and enabled
    And user enters email address registered
    And user click on submit button
    Then user should see the correct password reset message

 # @veloZAReg
  Scenario:  API sample
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account via api

  @veloPLReg @veloPLLive
  Scenario: 703991: VELO PL | Customer Login| AC 4 | Validate Create Account button
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user is on the account log-in page
    And user clicks on create your account CTA Link
    And I have filled in all mandatory fields
    And I click on create your account CTA

  @veloZAReg
  Scenario:  VELO ZA | Customer Login| AC 4 | Validate Create Account button
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And create a new account


#@veloBeReg - unable to create new users due to yoti
#Scenario Outline: 700857 AC1 AC2 VELO BE> Customer deletion
#    And create a new account via api
#    And the user navigates to the login page for language "<language>"
#    And  user login with credentials
#    And user clicks on delete account
#    And the user navigates to the login page for language "<language>"
#    And user login with credentials
#    And assert text of "loginFailureMessage" is displayed for language "<language>"
#    Examples:
#        | language  |
##        | en        |
##        | nl        |
#        | fr        |

    # cannot create new users with yoti, cannot use existing user as you get too many resets error
#@veloBeReg
#Scenario Outline: 700891 700874 VELO BE> Forgot password
#    Given user navigates to BAT home page for language "<language>"
#    And select allow all from cookie popup and select over 18 age confirmation option
#    And user clicks the person icon
#    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
#    And the user navigates to My Account Page
#    When I click on "left nav sign out" tab
#    And the user navigates to the login page for language "<language>"
#    When user clicks the forgotten password link
#    Then user is taken to forgot your password page for language "<language>"
#    Then user enters his email address
#    And I validate the "reset password" email
#    And user should receive a email to registered email with reset password link
#    When user resets the password
#    Then the user should be redirected to the login page for language "<language>"
#    And a success message is received for language "<language>"
#    Examples:
#        | language  |
##        | en        |
##        | nl        |
#        | fr        |

  @veloBeReg
  Scenario Outline: 692364 VELO BE> GTM tags and google analytics
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When I check google tag manager
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    When I check google tag manager
    And user navigates to PLP page and adds a product to basket
    And click on basket icon
    When I check google tag manager
    And user views the cart
    When I check google tag manager
    And Go to checkout page
    When I check google tag manager
    And Enter "mastercard" card details
    And assert thank you text of "<thank you text>" is displayed
    When I check google tag manager
    Examples:
      | language | thank you text      |
      | en       | ThankForPurchase-en |
      | nl       | ThankForPurchase-nl |
      | fr       | ThankForPurchase-fr |

  @veloPLReg
  Scenario Outline: 703977 VELO PL> GTM tags and google analytics
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When I check google tag manager
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    When I check google tag manager
    And user navigates to PLP page and adds a product to basket
    When I check google tag manager
    And user views the cart
    When I check google tag manager
    And Go to checkout page
    When I check google tag manager
    And I see an expandable card payments tab in this section
    And Customer makes payu payment with "<Payment Type>"
    And assert thank you text of "<thank you text>" is displayed
    When I check google tag manager
    Examples:
      | language | Payment Type | thank you text      | your order number text |
      | pl       | visa         | ThankForPurchase-pl | yourOrderNumberText-pl |


  @veloBeReg2
  Scenario Outline: 715198 Disable newsletter
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And navigates to the following page and confirms that newsletter is disabled
#   do not change the order since my account is dependent on login and basket is dependent on pdp
      | registration |
      | login        |
      | my account   |
      | plp          |
      | pdp          |
      | basket       |
      | checkout     |
    Examples:
      | language |
      | en       |
      | nl       |
      | fr       |


   # @veloPLReg
  Scenario Outline: VELO PL Disable newsletter
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And navigates to the following page and confirms that newsletter is disabled
#         do not change the order since my account is dependent on login and basket is dependent on pdp
      | registration |
      | login        |
      | my account   |
      | plp          |
      | pdp          |
      | basket       |
      | checkout     |
    Examples:
      | language |
      | pl       |



#    @veloBeReg
#Scenario Outline: DEFECT 765983 701645 Live chat zendesk
#    And the user navigates to the login page for language "<language>"
#    And navigates to the following page and confirms that live chat is functioning
##         do not change the order since my account is dependent on login and basket is dependent on pdp
#        | registration   |
#        | login          |
#        | my account     |
#        | search         |
#        | plp            |
#        | pdp            |
#        | basket         |
#        | checkout       |
#    And empty basket before adding related products
#    Examples:
#        | language  |
#        | en        |
#        | nl        |
#        | fr        |
#@veloBeReg
#Scenario Outline: DEFECT 765983 701645 Live chat HubBot
#    Given user navigates to BAT home page for language "<language>"
#    And select allow all from cookie popup and select over 18 age confirmation option
#    And user clicks the person icon
#    And navigates to the following page and confirms that live chat is functioning
##         do not change the order since my account is dependent on login and basket is dependent on pdp
#        | registration   |
#        | login          |
#        | my account     |
#        | plp            |
#        | pdp            |
#        | basket         |
#        | checkout       |
#    And empty basket before adding related products
#    Examples:
#        | language  |
#        | en        |
##        | nl        |
##        | fr        |

 #unable to test due to face id scan
#@veloBeReg
  Scenario Outline: 700891 700723 AC2 700615 Account confirmation (double opt-in) before Age Verification with Yoti
    Given user navigates to BAT home page for language "<language>"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And the user navigates to the registration page
    And I have filled in all mandatory fields
    And I click on create your account CTA
    And I validate the "verify" email
    And I verify my account through email
    And assert that you are now on the yoti age estimation page
    And you cannot log in without completing yoti AV
    And I validate the "av not completed" email
    Examples:
      | language |
      | en       |
      | nl       |
      | fr       |