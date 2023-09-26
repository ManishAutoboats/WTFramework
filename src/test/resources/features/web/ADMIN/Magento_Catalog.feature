@smokeVypeAdminFast
Feature: BAT Magento Admin feature - Catalog page

  Background:
    Given user navigates to BAT home page
    And user logins Magento Admin home page successfully


  Scenario:Generic- product list is displayed
    Given user navigates to product page
    And product list is displayed correctly

  Scenario:Generic- product info is viewable for the selection
    Given user navigates to product page
    And user selects the first entry in product table
    And user clicks edit link in product table
    And product info is displayed correctly


