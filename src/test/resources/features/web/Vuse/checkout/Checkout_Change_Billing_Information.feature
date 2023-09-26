@VuseUKReg2
Feature: BAT checkout billing information
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    And create a new account

  Scenario: Bug 345479 - new Billing Address added at checkout
    And search and checkout product "searchTerm.key"
    And Credit Card - MasterCard option and press next
    And assert billing and shipping are the same tick box present
    And add new billing address at checkout

  #Scenario: Bug 371822 - subs payment options should not be displayed when doing one time purchase
  #  And search and checkout product "searchSubsEnabledProduct.key"
  #  And Credit Card - MasterCard option and press next