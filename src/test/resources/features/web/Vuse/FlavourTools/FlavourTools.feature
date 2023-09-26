Feature: BAT Flavour Quiz Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And user navigates to Flavour Tool landing page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user closes the alert if present

  @VuseUKReg3
  Scenario: Device Question Page title display correctly
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And the 'Device_H5.key' 'Device_H2.key' 'Device_Smalltitle.key' display correctly on question page
    Then the process bar is updated on 'device'
    And the back button displays
    And user select options from device question page
      | deviceOption |
      | epen1|

  @VuseUKReg3
  Scenario: user can  know about devices
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    When user select options from device question page
      | deviceOption |
      | no_device2|
    Then the 'BuyDevice_H5.key' 'BuyDevice_Smalltitle.key' display correctly on buy devices page

  @VuseUKReg3
  Scenario: Nicotine Question Page works correctly
    And user start flavour tools by clicking lets go
    And user select options from device question page
      | deviceOption |
      | epod0|
    And the process bar is updated on 'nicotine'
    And user select options from nicotine question page
      | nicotineOption |
      | nic0|
    And the back button displays

  @VuseUKReg3
  Scenario: Taste Question Page works correctly
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And user select options from device question page
      | deviceOption |
      | epod0|
    And user select options from nicotine question page
      | nicotineOption |
      | nic0|
    And user select options from taste question page
      | tasteOption |
      | taste0|
    Then the process bar is updated on 'taste'
    And the back button displays
    And the next button displays
    And user click Next Btn

  @VuseUKReg3
  Scenario: Sensation Question Page works correctly
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And user select options from device question page
      | deviceOption |
      | epod0|
    And user select options from nicotine question page
      | nicotineOption |
      | nic0|
    And user select options from taste question page
      | tasteOption |
      | taste0|
    And user click Next Btn
    And user select options from sensation question page
      | sensationOption |
      | sen0|
    Then the process bar is updated on 'sensation'
    And the back button displays
    And the next button displays
    And user click Next Btn

  @VuseUKReg3
  Scenario: Flavour Question Page works correctly
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And user select options from device question page
      | deviceOption |
      | epod0|
    And user select options from nicotine question page
      | nicotineOption |
      | nic0|
    And user select options from taste question page
      | tasteOption |
      | taste0|
    And user click Next Btn
    And user select options from sensation question page
      | sensationOption |
      | sen0|
    And user click Next Btn
    And user select options from flavour question page
      | flavourOption |
      | fla0|
    Then the process bar is updated on 'flavour'
    And the back button displays
    And the next button displays

  @VuseUKReg3
  Scenario: Image Question Page works correctly
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And user select options from device question page
      | deviceOption |
      | epod0|
    And user select options from nicotine question page
      | nicotineOption |
      | nic0|
    And user select options from taste question page
      | tasteOption |
      | taste0|
    And user click Next Btn
    And user select options from sensation question page
      | sensationOption |
      | sen0|
    And user click Next Btn
    And user select options from flavour question page
      | flavourOption |
      | fla0|
    And user click Next Btn
    And user select options from image question page
      | imageOption |
      | IMG3|
      | IMG6|
    Then the process bar is updated on 'image'
    And the back button displays
    And the next button displays
    Then the count down video displays


  Scenario: user can walk through the tour by CTA CREATE YOUR PROFILE
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    Then the 'Device_H5.key' 'Device_H2.key' 'Device_Smalltitle.key' display correctly on question page

  @VuseUKReg
  Scenario: user can walk through the tour by CTA JUMP BACK IN
    And user clicks CTA JUMP BACK IN
    And user closes the alert if present
    And user enters username 'loginValidEmail.key' and password 'loginValidPassword.key' in the flavourtool login popup
    Then user is directed to flavour profile submenu

  @VuseUKReg
  Scenario: save profile
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And user select options from device question page
      | deviceOption |
      | epen1|
    And user select options from nicotine question page
      | nicotineOption |
      | nic0|
    And user select options from taste question page
      | tasteOption |
      | taste0|
    And user click Next Btn
    And user select options from sensation question page
      | sensationOption |
      | sen2|
    And user click Next Btn
    And user select options from flavour question page
      | flavourOption |
      | fla3|
    And user click Next Btn
    And user select options from image question page
      | imageOption |
      | IMG2|
    And user click Next Btn
    Then the count down video displays
    And user save profile
    And user enters username 'loginValidEmail.key' and password 'loginValidPassword.key' in the flavourtool login popup
    And user submits the flavourtool login popup form
    And user goes to my account page
    Then verify profiled is saved

  @VuseUKReg3
  Scenario: update your profile
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And user select options from device question page
      | deviceOption |
      | epod0|
    And user select options from nicotine question page
      | nicotineOption |
      | nic1|
    And user select options from taste question page
      | tasteOption |
      | taste2|
    And user click Next Btn
    And user select options from sensation question page
      | sensationOption |
      | sen1|
    And user click Next Btn
    And user select options from flavour question page
      | flavourOption |
      | fla2|
    And user click Next Btn
    And user select options from image question page
      | imageOption |
      | IMG4|
      | IMG8|
    And user click Next Btn
    And the count down video displays
    And user update your profile
    Then the 'Device_H5.key' 'Device_H2.key' 'Device_Smalltitle.key' display correctly on question page

  @VuseUKReg
  Scenario: user can  know about devices and buy it
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'logoutText.key' text link
    And user navigates to Flavour Tool landing page
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    When user select options from device question page
      | deviceOption |
      | no_device2|
    When the user selects shop epod
    And user selects a colour and add to basket
    And user enters username 'loginValidEmail.key' and password 'loginValidPassword.key' in the flavourtool login popup
    And user submits the flavourtool login popup form
    And user selects add to basket
    And user click Back btn on popup
    And display the top menu
    Then confirm mini-basket displayed amount of '1'
    Then empty basket

  @VuseUKReg
  Scenario: user can continue the journey after knowing about devices
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    When user select options from device question page
      | deviceOption |
      | no_device2|
    When the user selects shop epod
    And user selects a colour and add to basket
    And user enters username 'loginValidEmail.key' and password 'loginValidPassword.key' in the flavourtool login popup
    And user submits the flavourtool login popup form
    And user selects add to basket
    And user clicks continue with flavour profile
    Then the process bar is updated on 'nicotine'
    And display the top menu
    And empty basket

  @VuseUKReg
  Scenario: user can buy product on recommendation page
    And user start flavour tools by clicking lets go
    And user closes the alert if present
    And user select options from device question page
      | deviceOption |
      | epen1|
    And user select options from nicotine question page
      | nicotineOption |
      | nic0|
    And user select options from taste question page
      | tasteOption |
      | taste1|
    And user click Next Btn
    And user select options from sensation question page
      | sensationOption |
      | sen2|
    And user click Next Btn
    And user select options from flavour question page
      | flavourOption |
      | fla3|
    And user click Next Btn
    And assert text of 'lastQuestionSecondaryText.key' is displayed on page
    And user select options from image question page
      | imageOption |
      | IMG2|
    And user click Next Btn
    Then the count down video displays
    And user click ADD SELECTED FLAVOURS btn
    And user selects weight for each product on recommendation pop up
    And user clicks ADD SELECTED FLAVOURS TO BASKET on reommendation pop up
    And user enters username 'loginValidEmail.key' and password 'loginValidPassword.key' in the flavourtool login popup
    And user submits the flavourtool login popup form
    And user clicks ADD SELECTED FLAVOURS TO BASKET on reommendation pop up
    And user clicks back button on recommendation pop up
    And display the top menu
    Then confirm mini-basket displayed amount of '3'
    Then empty basket

  @VuseUKReg3
  Scenario: Find a Match CTA on PLP and Navigation to Flavours Quiz
    And user navigate to "flavourQuizPageURL.key" page
    And assert URL contains text 'flavourQuizPageURL.key'
    And user clicks on Lets go button to initiate Vuse flavour selector
    And assert sizzle video is not displayed on Flavours landing page
    And user clicks on Start Now button to start the quiz