# new feature
# Tags: optional

Feature: BAT Magento Admin feature - Order page

  Background:
    Given user navigates to BAT home page
    And user logins Magento Admin home page successfully

  Scenario: Disable NemID from Back-end
    Given user navigate to Store Configuration page
    And  navigate to NEMID Configuration page
    And user disables the NemID for selected Stores

