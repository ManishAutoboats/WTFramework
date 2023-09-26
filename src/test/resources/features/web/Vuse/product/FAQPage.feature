  Feature: FAQ
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseUKReg2 @VuseITAnonReg2
  Scenario: 26380 - FAQ Page | Test env
    And users clicks on the 'FAQText' text link
    And assert text of 'FAQPageGuarantee.key' is displayed
    Then assert that page title is 'FAQPageTitle.key'
    And assert text of 'FAQ.key' is displayed
    And assert text of 'FAQQuestion1' is displayed
    And assert text of 'FAQQuestion2' is displayed
    And assert text of 'FAQQuestion3' is displayed
    And assert text of 'FAQQuestion4' is displayed
    And assert text of 'FAQQuestion5' is displayed
    And assert text of 'FAQQuestion6' is displayed

    @VuseUKLive2
    Scenario: 26380 - FAQ Page | Production-Vuse
      And user navigates to the following URL 'https://www.vuse.com/gb/en/frequently-asked-questions'
      Then assert that page title is 'FAQPageTitleLive.key'

    @VuseFRReg
    Scenario: PDP Q&A feature
      And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
      And empty basket
      And user click on search icon and submits the following search term 'searchTerm.key'
      And click first result
      Then assert ask a question button is displayed
      Then assert question popup is displayed on clicking ask a question cta
      And fill the form and click on submit
      And verify subscription confirmation mail for ask a question

    @VuseDEReg2
    Scenario Outline: Verify different product's FAQs on PDP
      When user click on search icon and submits the following search term '<searchTerm>'
      Then FAQ is present on PDP
      Examples:
        | searchTerm   |
        | EpenFAQ.key  |
        | EpodFAQ.key  |
        | EtankFAQ.key |

    @VuseFRReg
    Scenario: Ask a question feature
      And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
      And user click on search icon and submits the following search term 'searchTerm.key'
      And click first result
      Then assert ask a question button is displayed
      Then assert question popup is displayed on clicking ask a question cta
      When terms and Policy checkbox is not ticked then Send button is not clickable
      Then assert error message for Ask a question without filling any details
      And enter invalid email address and assert error message
