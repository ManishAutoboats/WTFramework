Feature: Verify Order History page

  Background:  Navigate to Order History Page
    Given user navigates to BAT home page

  @gloJPReg
  Scenario: Verify order history page , pagination and Historical orders button
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user clicks on person icon
    And user clicks on My Orders
    And assert header 'YourOrders.key' is displayed
    And assert My Orders Table is displayed
    And verify My Orders Table headings are as expected
    And verify My Order table data is as expected
    And verify My Order date is displayed
    And assert order date on order history
    And On next page click user is navigated to next page
    And assert title 'YourOrders.key' is displayed
    And On previous page click user is navigated to previous page
    And list is updated with further orders

  @gloJPReg @gloJPLive
  Scenario: User is logged in and has no orders
    Then user signs in to the site credentials details 'loginValidEmail3.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user clicks on person icon
    And user clicks on My Orders
    And assert Generic message is displayed

  @gloJPReg @gloJPLive
  Scenario: Verify historic orders list and historic order details page
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user clicks on person icon
    And user clicks on My Orders
    And user can see the icon
    And user can see the Historical orders heading
    And user can see the explaining message
    And user clicks View historical orders button
    And assert header 'historicOrders.key' is displayed

  @gloJPReg
  Scenario: Verify Footer & Header on Order History Page
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user clicks on person icon
    And user clicks on My Orders
    And footer is present and displayed
    And assert content links with success status code
    Then assert Link contains text and assert Url returns successful response
      | terms&ConditionsLink.key     | terms&ConditionsURL.key      |
      | commercialTransactionLaw.key | commercialTransactionURL.key |
      | privacyPolicy.key            | privacyPolicyUrl.key         |
      | gloFAQ.key                   | gloFAQURL.key                |
      |gloOfficialWebsite.key        |gloOfficialWebsiteURL.key     |
      | gloStore.key                 | gloStoreURL.key              |
      | veloFAQ.key                  | veloFAQURL.key               |
      |veloOfficialWebsite.key       |veloOfficialWebsiteURL.key    |
      | veloStore.key                | veloStoreURL.key             |
    And assert glo velo contact details are displayed
    And assert copyright is present and assert text
    And assert social media links with success status code
      | instagram |
      | line      |
      | twitter   |
    And header is displayed
    And glo velo logo is displayed
    And person icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert basket icon link
    And assert internal redirect of header link URLs with success status code
    And assert homepage icon link
    And assert links in person icon
      | yourInformation    | yourInformationURL.key    |
      | deviceRegistration | deviceRegistrationURL.key |
      | signOut            | logoutURL.key             |

  @gloJPReg
  Scenario: Verify Header & Footer on Historic Orders Page
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user clicks on person icon
    And user clicks on My Orders
    And user clicks View historical orders button
    And header is displayed
    And glo velo logo is displayed
    And person icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert basket icon link
    And assert homepage icon link
    And assert internal redirect of header link URLs with success status code
    And assert links in person icon
      | yourInformation    | yourInformationURL.key    |
      | myOrders           | myOrdersURL.key           |
      | deviceRegistration | deviceRegistrationURL.key |
      | signOut            | logoutURL.key             |
    And footer is present and displayed
    And assert content links with success status code
    And assert glo velo contact details are displayed
    And assert copyright is present and assert text
    And assert social media links with success status code
      | instagram |
      | line      |
      | twitter   |