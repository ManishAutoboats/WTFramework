Feature: BAT Guided Sell Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user start guided sell by clicking navigation menu
    And user closes the alert if present
    And user clicks LET US GET STARTED link

  @VuseDEReg2 @VuseFRReg3 @COReg @VuseMXReg @VuseZAReg2
  Scenario: Step1 question page displays correctly
    Then options on step1 question page display correctly

  @VuseDEReg2 @VuseFRReg3 @COReg @VuseMXReg
  Scenario: Step2 question page displays correctly
    And user select option 'DeviceOpt.key' from Step1 question page
    And user click Next button on Step1 question page
    Then options on step2 question page display correctly

  @VuseDEReg2 @VuseFRReg3 @COReg @VuseMXReg @VuseZAReg2
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

  @VuseDEReg2 @VuseFRReg3 @COReg @VuseZAReg2 #@VuseMXReg
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

  @VuseDEReg2 @VuseFRReg3 @COReg
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
    Then empty basket

  @VuseDEReg2 @VuseFRReg3 @COReg
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

  @VuseDEReg2 @VuseFRReg3 @COReg
  Scenario: user view all Flavours
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption1|
      | deviceOption2|
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption5|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | mild           |
      | big            |
    And user click Find button on Step3 question page
    And from guided sell PLP view all flavours
    Then all devices or flavours are returned

  @VuseDEReg2 @VuseFRReg3 @COReg @VuseMXReg
  Scenario: user goes back to Guided Sell homepage by clicking Start Again
    And user select options from Step1 question page
      | deviceOption     |
      | deviceOption1    |
      | deviceOption4    |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption1|
      | flavourOption2|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | big            |
      | 0mg           |
    And user click Find button on Step3 question page
    And from guided sell PLP click Start Again
    And confirm guided sell start

  @VuseDEReg2 @VuseFRReg3 @COReg @VuseZAReg2 #@VuseMXReg
  Scenario: back button works well on Step2 and Step3 question page
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption4|
    And user click Next button on Step1 question page
    And user click Back button on Step2 question page
    Then confirm 'deviceOption4' on Step1 is selected
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption2|
    And user click Next button on Step2 question page
    And user click Back button on Step3 question page
    And confirm 'flavourOption2' on Step2 is selected
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | big            |
    And user click Find button on Step3 question page
    Then all devices or flavours are returned

  @VuseDEReg2 @VuseFRReg3 @COReg @VuseMXReg
  Scenario: no data is retained from product selector
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption1|
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption1|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | big            |
    And user click Find button on Step3 question page
    And from guided sell PLP click Start Again
    And confirm guided sell start
    And user click Let us Get Start
    Then confirm 'deviceOption1' on Step1 is not selected

  @VuseFRReg3 @COReg @VuseMXReg @VuseZAReg2
  Scenario: there is no flavour group returned when user select I don't have a preference
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption3 |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption4|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | 0mg            |
      | mild           |
    And user click Find button on Step3 question page
    Then all flavour groups are not returned

  @VuseFRReg3 @COReg
  Scenario: user could buy product after switch flavour tab
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption1  |
      | deviceOption2  |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption1|
      | flavourOption4|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | mild           |
      | big            |
    And user click Find button on Step3 question page
    And from guided sell PLP switch to another flavour tab
    And confirm the selected flavour tab is active

  #@VuseMXReg
  Scenario: user could buy product after switch flavour tab MX
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption1  |
      | deviceOption2  |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption1|
      | flavourOption3|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | mild           |
      | big            |
    And user click Find button on Step3 question page
    And from guided sell PLP switch to another flavour tab
    And confirm the selected flavour tab is active

  @VuseZAReg2
  Scenario: user could buy product
    And user select options from Step1 question page
      | deviceOption |
      | deviceOption1  |
    And user click Next button on Step1 question page
    And user select options from Step2 question page
      | flavourOption |
      | flavourOption1|
      | flavourOption3|
    And user click Next button on Step2 question page
    And user select options from Step3 question page
      | strengthOption |
      | notSure           |
    And user click Find button on Step3 question page
    And from guided sell PLP switch to another flavour tab
    And confirm the selected flavour tab is active

  @VuseFRReg3 @VuseFRLive
  Scenario: user could use deferred payment CTA go to devicetrial page
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
    And user click deferred payment CTA
    Then assert URL contains text 'deviceTrial.key'
    And assert device trial confirmation pop-up is displayed with two CTAs for guest user
    Then user selects 'Yes' option from FDT pop-up for a user with first order
    Then assert home page is displayed

  @VuseFRReg3 @VuseFRLive
  Scenario: user could use learn more CTA go to devicetrial page
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
    And user click learn more CTA
    Then assert guided sell popup is displayed
    And click popup redirect CTA on popup page
    Then assert URL contains text 'deviceTrial.key'

  @VuseFRReg3 @VuseFRLive
  Scenario: FDT Links Deferred payment and Learn More not displayed for a logged-in User
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
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

  @VuseFRReg3
  Scenario: FDT Links Deferred payment and Learn More is displayed for a new user
    And create a new account
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
    Then assert FDT links Deferred payment and Learn More are displayed

  @VuseFRReg3 @VuseFRLive
  Scenario: hover on any device on GS Results page and assert Deferred Payment CTA(FDT) navigation
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
    Then user hovers on 'searchDeviceEPOD.key' menu
    Then user clicks on Deferred Payment link for FDT navigation
    Then assert guided sell popup is displayed
    And click popup redirect CTA on popup page
    Then assert URL contains text 'deviceTrial.key'

  @VuseDEReg2
  Scenario: Health Warning popup for guided sell result page
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
    And user navigates to 'Guided sell' PLP page and adds a product to basket
    And assert 'back button' is displayed
    And assert 'continue to cart' is displayed
    And user bypass the CLP healthwarning popup
    Then confirm mini-basket displayed amount of '1'