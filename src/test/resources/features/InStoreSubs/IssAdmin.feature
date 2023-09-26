@iss
Feature: In Stores Subs Admin

  Background: log in as ISS admin
    Given user navigates to BAT home page
    And user logins Magento Admin home page successfully

  Scenario: 443355 515740 AC1 Check staff id label
    And user navigates to Manage Staff page
    And user navigates to add new staff page
    Then the staff id label is present against the staff id

  Scenario: 431040 515741 AC1 Unique Outlet ID and Save and continue edit
    And user navigates to Physical Stores page
    And user ensures a proposed outlet id is unique
    And user attempts to add an already existing outlet  id
    Then an error is reported
    And I correct the outlet id to a unique value
    And I get a success message
    Then I delete this store

  Scenario: 553907 Add admin config for FE translations
    And user navigates to the Manage Translation page
    And can see a section titled "handover"
    And can see a section titled "staff signin"
    And can see a section titled "store selector"
    And can see a section titled "navigation"
    And can see a section titled "start again popup"
    And can see a section titled "sign in"
    And can see a section titled "retrieve quote"
    And can see a section titled "product section"
    And can see a section titled "product popup section"
    And can see a section titled "summary section"
    And can see a section titled "basket summary"
    And can see a section titled "send to customer"
    And can see a section titled "checkout"
    And can see a section titled "web"
