Feature: Velo Registration Page

Background: Navigate to Velo Home Page
  Given user navigates to BAT home page
  And select allow all from cookie popup and select over 18 age confirmation option
  And user click on PersonIcon and Navigate to the Login Page
  When create a new account

  @veloDEReg
  Scenario: Verifying successful newsletter subscription
    And user navigate to my account page
    And click on edit button of newsletter
    And select check box of newsletter subscription
    And Click on save button
    And verify email and return to my account page
    And validation success message displayed
    And click on edit button of newsletter
    Then assert that Newsletter checkbox is 'newsletterCheckboxChecked.key' selected

  @veloDEReg
  Scenario: Verifying successful newsletter unsubscribe
    And user navigate to my account page
    And click on edit button of newsletter
    And select check box of newsletter subscription
    And Click on save button
    And verify email and return to my account page
    And validation success message displayed
    And click on edit button of newsletter
    And select check box of newsletter subscription
    And Click on save button
    Then assert newsletter unsubscribe success message 'NewsletterUnsubscribeSuccessMessage.key'

  @veloDEReg
  Scenario: Autosubscribe On first successful Order > Unsubscribe > No autosubscription on Order placement again
    When user clicks on product menu
    And get all lists from page
    And user Clicks on add to cart button
    And click on basket icon
    And Go to checkout page
    And Enter "mastercard" card details
    And select place order from Card
    And Order Confirmation page thankYouMessageHeading is displayed
    And user navigate to my account page
    And click on edit button of newsletter
    Then assert that Newsletter checkbox is 'newsletterCheckboxChecked.key' selected
    And select check box of newsletter subscription
    And Click on save button
    And Customer makes a home delivery purchase with "mastercard"
    And Order Confirmation page thankYouMessageHeading is displayed
    And user navigate to my account page
    And click on edit button of newsletter
    Then assert that Newsletter checkbox is 'newsletterCheckboxChecked.key' not selected

    # Excluding from Regression because of known issue 549694
  Scenario:  No Autosubscription triggered for a Newly Registered User, Upon unsubscription before placing first Order
    And user navigate to my account page
    And click on edit button of newsletter
    And select check box of newsletter subscription
    And Click on save button
    And validation success message displayed
    And click on edit button of newsletter
    And select check box of newsletter subscription
    And Click on save button
    Then assert newsletter unsubscribe success message 'NewsletterUnsubscribeSuccessMessage.key'
    And user clicks on product menu
    And get all lists from page
    And user Clicks on add to cart button
    And click on basket icon
    And Go to checkout page
    And select place order from Card
    And Order Confirmation page thankYouMessageHeading is displayed
    And user navigate to my account page
    And click on edit button of newsletter
    Then assert that Newsletter checkbox is 'newsletterCheckboxChecked.key' not selected

  @veloDEReg
  Scenario: No Newsletter autosubscription on First Failed Order Placement
    And user clicks on product menu
    And get all lists from page
    And user Clicks on add to cart button
    And click on basket icon
    And Go to checkout page
    And user navigate to my account page
    And click on edit button of newsletter
    Then assert that Newsletter checkbox is 'newsletterCheckboxChecked.key' not selected
