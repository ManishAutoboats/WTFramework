@iss @isssmoke
Feature: 357462 In Store Subscriptions - full flow

Background: set up user
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

Scenario: 588140 434690 466134 AC1 466136 AC1 AC2 466124 AC1 434683  AC1  Monthly payments set up complete via email - existing age verified user
#    And ensure that in admin age verified is set to "true" for user "loginValidEmail.key"
    And user resumes ISS home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And clicks on the send via email button
    And the colleague selects the consent radio button
    And enters the email address of the customer
    And clicks the send email button
    And a success message is returned
    And the user receives an email containing "setUpMonthlySubscriptions"
    And the user click on "setUpPaymentsLinkText" link
    Then the user is on the login page for the main site
    And login with valid details
    And payment wording is correct
    And the user pays using saved card
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And that future payments will be done automatically is displayed

Scenario: 504958 AC1 434697 466134 AC1 466136 AC1 AC2 466124 AC1 434683  AC1  Monthly payments set up complete via email - new user
    And user resumes ISS home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And clicks on the send via email button
    And the colleague selects the consent radio button
    And enters the email address of the customer
    And clicks the send email button
    And a success message is returned
    And the user click on "setUpPaymentsLinkText" link
    Then the user is on the login page for the main site
    And create a new account following in store subscription
    And the AV page informs the user why age verification is required
    Then the customer shows the AV QR code to the colleague who scans it
    And payment wording is correct
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And that future payments will be done automatically is displayed
    Then the staff member sees a success message

Scenario: 434690 466128 460859 434675 494407 Monthly payments set up complete via QR Code - existing age verified user
    And ensure that in admin age verified is set to "true" for user "loginValidEmail.key"
    And user resumes ISS home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And customer scans the QR code
    And navigates to the scanned url
    And login with valid details
    And payment wording is correct
    And the user pays using saved card
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And that future payments will be done automatically is displayed
    Then the staff member sees a success message

    Scenario: 434697 466128 460859 434675 447412 494407 Monthly payments set up complete via QR Code - new user
    And user resumes ISS home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And customer scans the QR code
    And navigates to the scanned url
    And create a new account following in store subscription
    And the AV page informs the user why age verification is required
    Then the customer shows the AV QR code to the colleague who scans it
    And payment wording is correct
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And that future payments will be done automatically is displayed
    Then the staff member sees a success message

Scenario: 434690 466128 460859 434675 494407 Monthly payments set up complete via QR Code - existing NOT age verified user
    And ensure that in admin age verified is set to "false" for user "loginValidEmail.key"
    And user resumes ISS home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And customer scans the QR code
    And navigates to the scanned url
    And login with valid details
    And the AV page informs the user why age verification is required
    Then the customer shows the AV QR code to the colleague who scans it
    And payment wording is correct
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And that future payments will be done automatically is displayed
    Then the staff member sees a success message
    And ensure that in admin age verified is set to "true" for user "loginValidEmail.key"

Scenario: 434697 494407 Monthly payments set up complete via email - existing NOT age verified user
    And ensure that in admin age verified is set to "false" for user "loginValidEmail.key"
    And user resumes ISS home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    When he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And clicks on the send via email button
    And the colleague selects the consent radio button
    And enters the email address of the customer
    And clicks the send email button
    And a success message is returned
    And the user click on "setUpPaymentsLinkText" link
    Then the user is on the login page for the main site
    And login with valid details
    And the AV page informs the user why age verification is required
    Then the customer shows the AV QR code to the colleague who scans it
    And payment wording is correct
    And Checkout is selected with appropriate card details entered '5555555555554444'
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And that future payments will be done automatically is displayed
    Then the staff member sees a success message
    And ensure that in admin age verified is set to "true" for user "loginValidEmail.key"

