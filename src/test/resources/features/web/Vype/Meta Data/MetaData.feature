#This is the BAT Navigation feature
Feature: Meta Data feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: META Data page presence test
    And assert meta data present
    Then click on the logo
    And assert meta data present
    #And users clicks on the 'SHOP NOW' text link
    #And assert meta data present
    And users clicks on the 'DeviceCategoryText.key' text link
    And assert meta data present
    And users clicks on the 'flavourHeaderLinkText.key' text link
    And assert meta data present
    And users clicks on the 'bundlesHeaderLinkText.key' text link
    And assert meta data present

  Scenario Outline: Cycle through footer links ensure META data present expected
    And users clicks on the '<LinkToClick>' text link
    And assert meta data present
    Examples:
      | LinkToClick                 |
      | companyDetails.key         |
      | productDetails.key       |
      #| privacyPolicy.key             |
      | subscribeText.key                   |
      | Terms&Conditions.key        |
      #| Store Locator              |
      | generalCondition.key          |
      #| FAQ.key  |
      | Satisfaction.key     |
      | DeliveryReturn.key         |
      | guarantee.key                   |
      | Accessibility.key               |







