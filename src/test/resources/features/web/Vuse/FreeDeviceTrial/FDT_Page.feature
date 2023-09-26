#Free Device Trial - FDT - prerequisite - feature to be enabled on admin
#below Scenario is functional & supported only for Vype UK atm
#Link implemented for navigation to landing page, removed hardcoded URL
Feature: Free Device Trial - Checkout
  Background: Navigate to Free Device Trial Landing page
#    Given user navigates to BAT home page
#    And select allow all from cookie popup and select over 18 age confirmation option
#    When user hovers over on the SHOP header link
#    Then users clicks on the 'deviceTrialLink.key' text link
#    Then user closes the alert if present
    Given user navigates to Free Device Trial landing page
    And select allow all from cookie popup and select over 18 age confirmation option

  @FDT
  Scenario: Free Device Trial - Landing page validation
    And user to see Brief info on Trial product
    And User to see the steps for placing a device trail order
    And user to see info about Trial delivery, Payment and related details

  @FDT
  Scenario: Checkout a Trial product  - choose a Device step's validation
    Then user to see "choose your device" step "is" highlighted
    Then user to see "select flavour packs" step "is not" highlighted
    Then user to see "checkout" step "is not" highlighted
    And verify user is able to choose trial device
    And user is able to see the description of the chosen Device
    And verify user can choose colour options for the chosen trial device
    And verify the actual price of the chosen device is shown in the device trail disclaimer info
    And user can click on 'choose your flavours' button to move to next step
    Then user to see "choose your device" step "is not" highlighted
    Then user to see "select flavour packs" step "is" highlighted
    Then user to see "checkout" step "is not" highlighted

  @FDT
  Scenario: Checkout a Trial product  - select flavours pack step's validation
    And user selects a product on choose a Device Step
    And user can click on 'choose your flavours' button to move to next step
    Then user sees maximum flavours allowed message
    Then user to see "choose your device" step "is not" highlighted
    Then user to see "select flavour packs" step "is" highlighted
    Then user to see "checkout" step "is not" highlighted
    #Then user to see strength info popup when clicked on 'Learn about strengths'
    Then user to see the "select flavour packs" page heading
    And the 'Chosen Flavours' section displays correctly
    And user sees the correct more to go messages
    And user adds 1 Cartridge Trial
    And the 'Chosen Flavours' section displays correctly
    And user sees the correct more to go messages
    Then user adds minimum Cartridge Trial allowed
    And user sees the correct more to go messages
    And the 'Chosen Flavours' section displays correctly
    And user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'
    And user can still add trial Cartridge till reaching the maximum number of flavours reached
    And user to get error when more than allowed maximum number of flavours reached
    And user dismisses the error
    And user clicks the checkout button
    And confirm that the user is notified they must be registered
    And confirm the user is on the login page

  @FDT
  Scenario: Free Device Trial - Content - Choose Device Page
    And user to see Brief info on Trial product
    And assert Banner text in Choose Your device page header
    And assert 'CHOOSE YOUR DEVICE' section title is displayed
    And assert Read more is displayed
    Then user clicks on 'Device Trial T&C' link from Information section and assert navigation to 'deviceTrialTCsURL.key'
    Then user closes the alert if present
    And assert Device Trial T&C link on the sidebar menu
    And user navigates back to Free Device Trial landing page
    Then user clicks on 'Conditions of Sale' link from Information section and assert navigation to 'conditionsOfSaleURL.key'
    And assert Device Trial T&C link 'DEVICE TRIAL TERMS & CONDITIONS' in Footer section
    Then user clicks on 'DEVICE TRIAL TERMS & CONDITIONS' link from Footer section and assert navigation to 'deviceTrialTCsURL.key'
    And assert Device Trial T&C link on the sidebar menu




