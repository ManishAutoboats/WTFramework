
Feature: Lyft Feedback form

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #Remove the tag due to #1027175
  Scenario: customer feed back form and assert success message
    And users clicks on the 'feedBackFormLinkText.key' link
    And url contains 'feedBackFormUrl.key'
    When user completes the feed back form and submits
    Then user should see a text of feedBackSuccessMsg.key

  #Remove the tag due to #1027175
  Scenario: customer is not able to submit the Feedback form without selecting the Product and checkbox for Privacy Policy
    And users clicks on the 'feedBackFormLinkText.key' link
    And url contains 'feedBackFormUrl.key'
    When user completes the feed back form and submits without selecting the product
    Then user should see a text of feedbackMissingProductError.key
    When user completes the feed back form and submits without selecting the Privacy Policy checkbox
    Then user should see a text of feedbackUncheckPrivacyPolicyError.key

  Scenario: customer feed back form and assert success message - En Site
    And switch to EN site
    And select allow all from cookie popup and select over 18 age confirmation option
    And users clicks on the 'feedBackFormLinkTextEn.key' link
    And url contains 'feedBackFormUrl.key'
    When user completes the feed back form and submits
    Then user should see a text of feedBackSuccessMsgEn.key