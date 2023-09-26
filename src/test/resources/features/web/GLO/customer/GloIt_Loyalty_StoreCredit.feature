@loyaltyGloIT
Feature: Loyalty feature - Store credit - GLO IT

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: View available spend on wallet
    When user should be able to see Insiders Club menu option
    And empty basket
    And User clicks on Insiders Club menu option of IT
    And user switch into insider club frame
    Then assert text of 'howItWorkTerm.key' is displayed
    And Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    When click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    Then user can see insider club credit available

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Spend Loyalty orders
    When user should be able to see Insiders Club menu option
    And empty basket
    And Glo user navigate to 'shopUrl.key' page
    When click on first result for Loyalty
    And select product strength or colour
    And click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    Then user can see insider club credit available
    And user enter '3.00' credit to use from insider club
    Then assert text of 'creditSummary.key' is displayed
    And assert applied credit amount is deducted from item price

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Pay for the whole order
    When user should be able to see Insiders Club menu option
    And empty basket
    And Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    And user can see insider club credit available
    When user enter '4.99' credit to use from insider club
    Then assert text of 'creditSummary.key' is displayed
    And assert user has paid for whole order

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Spend more points than a consumer has
    When user should be able to see Insiders Club menu option
    And empty basket
    And Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    And delete insider club credit amount from summary
    And user can see insider club credit available
    When user enter '16.00' credit to use from insider club
    Then an error message will be shown

 #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Discount codes applied
    And empty basket
    And Glo user clicks on Buy Link and add the product to mini cart
    And apply discount code module present
    Then apply discount '10off' to the discount module and assert success message
    And delete insider club credit amount from summary
    And user can see insider club credit available
    When user enter '3.00' credit to use from insider club
    Then assert text of 'creditSummary.key' is displayed
    And assert applied credit amount is deducted from item price
    And assert text of 'promotion10OffSummary.key' is displayed

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Add and remove points consumer has
    When user should be able to see Insiders Club menu option
    And empty basket
    And Glo user navigate to 'shopUrl.key' page
    And click on first result for Loyalty
    And select product strength or colour
    Then click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    When delete insider club credit amount from summary
    Then assert total order value '4,99 €' is displayed
    When user enter '3.00' credit to use from insider club
    Then assert total order value '1,99 €' is displayed
    And delete insider club credit amount from summary
    When user enter '4.99' credit to use from insider club
    Then assert total order value '0,00 €' is displayed

  @gloItRegression
  Scenario: Verify Insiders Club Link in Header
    And user navigates GloIt Homepage
    When user should be able to see Insiders Club menu option in header
    And users clicks on the 'insiderClubTopNavigation.key' text link
    Then user sent to Loyalty Insiders Club landing page
    And assert text of 'signUpLoyalty.key' is displayed
    And assert text of 'buyLoyalty.key' is displayed
    And assert text of 'logInLoyalty.key' is displayed

  @gloItRegression
  Scenario: Verify Insiders Club Terms and Condition Link in Footer
    When user should be able to see Insiders Club T&C option in footer
    And users clicks on the 'insiderClubFooterNavigation.key' text link
    Then user sent to Loyalty Insiders Club T&C page
    And assert text of 'promoterSubTitle.key' is displayed
    And assert text of 'durationSubTitle.key' is displayed
    And assert text of 'scopeSubTitle.key' is displayed
