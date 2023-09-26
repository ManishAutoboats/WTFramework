@iss
Feature: In Store Subs Payments

    Background: Create a new user
        Given user navigates to BAT home page
        And select allow all from cookie popup and select over 18 age confirmation option
        And create a new account
#        And ensure that in admin age verified is set to "true"
        And user resumes ISS home page
        When he clicks on STORE drop down with locations
        And he can select the store from the drop down where he is sitting
        Given user navigates to the Choose Your Flavours screen
        And he adds item 7 to the basket
        When he clicks on the basket CTA
        When he adds sufficient quantity to get "bronze" discount
        When he clicks on the CTA SET UP SUBSCRIPTION
        And clicks on the send via email button
        Then the colleague selects the consent radio button
        And enters the email address of the new customer
        And clicks the send email button
        And the new user click on "setUpPaymentsLinkText" link
        Then the customer shows the AV QR code to the colleague who scans it
        And Checkout is selected with appropriate card details entered '5555555555554444'

        Scenario: 434698 AC1 AC2 Order completion summary page - go to my account page
        And assert text of 'ThankForPurchase.key' is displayed
        And assert text of 'yourOrderNumberText.key' is displayed
        And that future payments will be done automatically is displayed
        And the order completion page informs that the first payment has been completed
        And that an order confirmation email has been sent
        And the go to my account CTA is displayed
        And the return to home CTA is displayed
        When the customer clicks on go to my account CTA
        Then assert text of 'myAccountDashboardText.key' is displayed

    Scenario: 434698 AC1 AC2 Order completion summary page - go to home page
        When the customer clicks on the Return to Home CTA
        Then he navigates to the home page