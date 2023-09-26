Feature: PLP page - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: PLP page intact with required information
    When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And Glo user clicks on buy button link and click on submenu
    Then url contains 'DeviceUrlText.key'

  @RegGloIT_Mobile
  Scenario: Glo poland PLP page intact with required information
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user click on 'device' menu navigation
    And user see the product name display
    And user see the price display
    And user see the Buy Now CTA display
    Then url contains 'DeviceUrlText.key'

  Scenario: merge devices / accessories sections
    When user click on 'device' menu navigation
    Then assert text of 'devicesText.key' is displayed
    And assert text of 'accessoriesText.key' is displayed
    And url contains 'DeviceUrlText.key'