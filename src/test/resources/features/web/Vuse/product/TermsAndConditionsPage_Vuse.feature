Feature: 21182 BAT terms and conditions Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option

   @VuseITAnonReg3
  Scenario: 21182 Ensure terms and conditions page is as expected
    And users clicks on the 'Terms&Conditions.key' text link
    And user closes the alert if present
    Then assert text of 'ConditionsAndTermsText.key' is displayed
    Then assert text of 'AboutUsText.key' is displayed
    Then assert text of 'HowToCreateASubscriptionText.key' is displayed
    Then assert text of 'SubscriptionProductsText.key' is displayed
    Then assert text of 'ExclusiveBenefitsText.key' is displayed
    Then assert text of 'BillingText.key' is displayed
    Then assert text of 'DeliveryText.key' is displayed
    Then assert text of 'ReturnOfProductsText.key' is displayed

  @VuseDEReg2 @VuseDELive
  Scenario: Ensure Terms and Conditions page is present for loyalty users
    And Select 'InsidersClubHeaderMenuOpt.key' from header Menu
    Then assert 'TermsAndConditionLabelText.key' label is displayed at the bottom of loyalty page
    And click 'Learn More' to navigate to 'Terms and Condition' page
    Then assert Terms and Conditions page heading 'TermsAndConditionPageHeader.key'
    Then assert Terms and Conditions sub heading 'TermsAndConditionPageHeader.key'

  @VuseDEReg2 @VuseDELive
  Scenario: Ensure Terms and Conditions link from footer
    And click on 'Terms and Conditions' link from footer
    Then assert Terms and Conditions page heading 'TermsAndConditionPageHeader.key'
    Then assert Terms and Conditions sub heading 'TermsAndConditionPageHeader.key'

  @VuseDEReg2
  Scenario: Verify Terms and Conditions link from account creation page
    And navigate to user creation form
    And click on 'Terms and Conditions' link in loyalty consent section
    Then assert Terms and Conditions page heading 'TermsAndConditionPageHeader.key'
    Then assert Terms and Conditions sub heading 'TermsAndConditionPageHeader.key'

  @VuseITAnonReg3
  Scenario: 21182 Ensure terms and conditions page content is as expected
    And users clicks on the 'Terms&Conditions.key' text link
    Then assert subtitles of terms and conditions page
