@smokeVypeAdminFast
#@ie @uk @co @it_a @fr_admin @nl_admin @mx
Feature:Admin prereq a for magento admin cases - Vype - register

  Scenario: Admin prereq a:Registration of specific user for Vype
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate DOB field with 'ValidDOB.key'
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields with specific email
    Then user selects the create an account button

  Scenario:Admin prereq b:Admin-Vype-Customer search result is displayed
    When user logins Magento Admin home page successfully
    And user navigates to all customers page
    And user searches customer by search keyword
    Then search result is displayed correctly
#
#  Scenario:Admin prereq c: Registration of random user for Vype
#    Given user navigates to BAT home page
#    And select allow all from cookie popup
#    And user selects over 18 age confirmation option
#    And user clicks the person icon
#    And user clicks on 'signInLink.key' link from Person Menu
#    And user clicks the registration button
#    And populate Personal Information - first and last name with randomly generated data
#    And populate DOB field with 'ValidDOB.key'
#    And populate gender field with 'Gender.key'
#    And populate address fields
#    And populate signin fields
#    Then user selects the create an account button

