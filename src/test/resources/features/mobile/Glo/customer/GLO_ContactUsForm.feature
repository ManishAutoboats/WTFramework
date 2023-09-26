Feature: Glo Contact Us tests - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: submit contact us form and assert success message
    When user clicks on the link that contains 'contacturl.key.glo.it.mobile' href
    And completes the contact us form and submits
    Then user should see a text of contactUsSuccessMsg.key