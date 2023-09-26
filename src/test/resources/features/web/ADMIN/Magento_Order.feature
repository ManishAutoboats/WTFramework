@smokeVypeAdminFast
Feature: BAT Magento Admin feature - Order page

  Background:
    Given user navigates to BAT home page
    And user logins Magento Admin home page successfully


  Scenario:Generic- Order list is displayed
    Given user navigates to order page
    And order list is displayed correctly

  Scenario:Generic- Order info is viewable for the selection
    Given user navigates to order page
    And user selects the first order entry
    And users clicks view link
    Then order info page is displayed correctly

#    DUPLICATE
#  Scenario:Generic- Admin Order search result is displayed
#    Given user navigates to order page
#    And user searches order by search keyword
#    Then search result is shown correctly

  Scenario:Generic- Order address info is viewable for the selection
    Given user navigates to order page
    And user selects the first order entry
    And users clicks view link
    Then order address info page is displayed correctly

  Scenario:Generic- Order account info is viewable for the selection
    Given user navigates to order page
    And user selects the first order entry
    And users clicks view link
    Then order account info is displayed correctly


