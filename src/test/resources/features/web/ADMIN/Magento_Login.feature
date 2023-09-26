@smokeVypeAdminFast
Feature: BAT Magento Admin feature - Logged In

  Scenario: User can successfully log in Magento Admin with existing user
    Given user navigates to BAT home page
    And user navigates to Magento Admin home page
    And user submits username and password
    Then user is logged in successfully