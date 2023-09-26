Feature: BAT Guided Sell Feature Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user start guided sell by clicking navigation menu
    And user closes the alert if present
    And user clicks LET US GET STARTED link


  Scenario: user could use learn more CTA go to devicetrial page
    And user click learn more CTA
    Then assert guided sell popup is displayed
    And click popup redirect CTA on popup page
    Then assert URL contains text 'deviceTrial.key'


  Scenario: The device with most weighting will be recommended
    And user select options from Step1 question page
      | deviceOption   |
      | deviceOption1  |
      | deviceOption2  |
      | deviceOption3  |
      | deviceOption4  |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption1|
      | flavourOption4|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | 0mg           |
      | big           |
    And user click Find button on Step3 question page
    Then user get recommended device 'ePod.key'


  Scenario: FDT Links Deferred payment and Learn More is displayed for a new user
    And create a new account via api
    And user logins with credentials registered via api
    And user start guided sell by clicking navigation menu
    And user closes the alert if present
    And user clicks LET US GET STARTED link
    And user select options from Step1 question page
      | deviceOption2  |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption2|
      | flavourOption3|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | mild           |
      | big            |
    And user click Find button on Step3 question page
    Then assert FDT links Deferred payment and Learn More are not displayed
    Then delete the account via api


  Scenario: user view all devices
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption2|
      | deviceOption4|
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption2|
      | flavourOption4|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | big            |
    And user click Find button on Step3 question page
    And from guided sell PLP view all devices
    Then all devices or flavours are returned


  Scenario: Step1 question page displays correctly
    Then options on step1 question page display correctly


  Scenario: Step3 question page displays correctly
    And user select options from Step1 question page
      | deviceOption  |
      | deviceOption1 |
      | deviceOption2 |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption1|
      | flavourOption2|
      | flavourOption4|
    And user click Next button on Step2 question page
    Then options on step3 question page display correctly


  Scenario: user could buy product from guided sell result page
    And user select options from Step1 question page
      | deviceOption   |
      | deviceOption1  |
      | deviceOption2  |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption5|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | 0mg            |
      | mild           |
    And user click Find button on Step3 question page
    And from guided sell add second product to cart
    Then confirm mini-basket displayed amount of '1'