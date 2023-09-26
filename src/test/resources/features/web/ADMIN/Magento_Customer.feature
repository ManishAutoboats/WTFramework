@smokeVypeAdminFast
Feature: BAT Magento Admin feature - Customer page

  Background:
    Given user navigates to BAT home page
    Given user logins Magento Admin home page successfully

  @smokeVypeAdminFast
  Scenario:Generic- Customer list is displayed
    Given user navigates to all customers page
    And customer list is displayed correctly

  @smokeVypeAdminFast
  Scenario:Generic- Customer info is displayed
    Given user navigates to all customers page
    And next page arrow button is enabled
    And customer table count and columns are correct
    And customer table info is correct


  Scenario:Generic- Customer info is updated and saved
    Given user navigates to all customers page
    And user selects the first entry in customer table
    And user clicks edit link
    And user switches to account info page
    And user updates telephone and clicks save
    Then save is successful