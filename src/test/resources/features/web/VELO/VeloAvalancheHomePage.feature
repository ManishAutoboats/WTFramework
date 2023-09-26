Feature: Velo Avalanche Home Page
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option

  @veloPLReg
  Scenario: Blog page present and content is correct order
    Given user navigates to the Blog
    Then url contains 'BlogUrlContain.key'
    And assert text of 'BlogTitle.key' is displayed
    Then blogs are listed in descending order

  @veloPLReg
  Scenario: Assert title for About Velo page
    Given users clicks on the 'AboutVelo.key' text link
    Then url contains 'AboutVeloUrl.key'
    And assert text of 'AboutVeloTitle.key' is displayed

  @veloPLReg
  Scenario: Assert title for Esmoking World page
    Given users clicks on the 'ESmokingWorld.key' text link
    Then url contains 'ESmokingWorldURL.key'
    And assert text of 'ESmokingWorldTitle.key' is displayed

  @veloPLReg
  Scenario: Assert title for Velo X Freeze page
    Given users clicks on the 'VeloBlack.key' text link
    Then url contains 'VeloBlackUrl.key'
    And assert text of 'VeloBlackTitle.key' is displayed

  @veloPLReg
  Scenario: Velo-PL Guest user live chat
    And assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page

  @veloPLReg
  Scenario: Check Facebook social Media link and its Navigation page
    When velo users click on Facebook link
    Then url contains 'facebook.key'

  @veloPLReg
  Scenario: Check Instagram social Media link and its Navigation page
    When velo users click on Instagram link
    Then url contains 'instagram.key'

  @veloPLReg
  Scenario: Check Follow us bar is visible on homepage
    When velo user scroll to follow us block and able to see social icons

  @veloPLReg
  Scenario: Cycle through footer links ensure each one is working as expected
    And assert home page is displayed
    And user click on the Link and assert url
      | contact.key            | contactUsUrl.key           |
      | PrivacyPolicy.key      | PrivacyPolicyUrl.key       |
      | ServiceRegulations.key | ServiceRegulationsUrl.key  |
      | ReturnAndComplaints.key|ReturnAndComplaintsUrl.key  |
      | Regulations.key        |RegulationsUrl.key          |
      | ForMedia.key           |ForMediaUrl.key             |
      | CookiesPolicy.key      |CookiesPolicyUrl.key        |
      | HowDoesVeloWork.key    |HowDoesVeloWorkUrl.key      |
      | Faq.key                |FaqUrl.key                  |
      | FindOutMore.key        |FindOutMoreUrl.key          |

  @veloPLReg
  Scenario: Newsletter Guest - enter valid e-mail address and assert success message
    And enter newsletter details with random email and assert 'newUserEmail' subscription
    Then assert no Success email on newsletter subscription for user

  @veloPLReg
  Scenario: Newsletter Guest - enter invalid e-mail address and assert Error
    And enter newsletter details with invalid email address 'loginInvalidEmail.key' and assert 'invalidEmail' subscription

  @veloPLReg
  Scenario: Newsletter Guest - enter existing e-mail address and expected message
    And enter newsletter details with existing email address 'loginSubscribedEmail.key' and assert 'alreadySubscribed' subscription

  @veloPLReg
  Scenario: Store Locator - enter valid postcode
    And users clicks on the store locator icon
    And enter 'postCode.key' as lookup address
    And assert displayed address is 'myAddressAccount.key'

  @veloPLReg
  Scenario: Store Locator - enter valid county
    And users clicks on the store locator icon
    And enter 'stateRegion.key' as lookup address
    And assert displayed address is 'stateRegionWithCountry.key'