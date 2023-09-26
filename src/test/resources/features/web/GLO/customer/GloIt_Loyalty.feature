@loyaltyGloIT
Feature: Loyalty feature - Insiders Club - GLO IT

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloItRegression
  Scenario: Click on Insiders club as new user who doesn't have any existing devices registered(AC3, AC1, AC4)
    When create a new account via api
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user login with credentials registered via api
    Then user should be able to see Insiders Club menu option
    When User clicks on Insiders Club menu option of IT
    Then I will see a pop up asking me to register a device
    And closing the popup will take me to My Account Page

  #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Login as existing user who has device already registerd and sign up for loyalty program
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    Then user should be able to see Insiders Club menu option
    When User clicks on Insiders Club menu option of IT
    Then Link option is present with sections
      | howItWorkTerm.key |
      | benefitsTerm.key  |
      | howItWorkTerm.key |

 #// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Customer has the device registered and not yet opted for Loyalty
    And user signs in with customer properties 'loginLoyaltyNotOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    Then user should be able to see Insiders Club menu option
    When User clicks on Insiders Club menu option of IT
    And user switch into insider club frame
    And assert enter club button is display to click
    Then assert text of 'enterClub.key' is displayed
    And assert text of 'termsAndCondition.key' is displayed

#// remove  @gloItRegression tag due to loyalty user getting deleted
  Scenario: Glo PDP and PLP Non loyalty Customer
    When create a new account via api
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user login with credentials registered via api
    When users clicks on the 'gloShopLinkText.key' text link
    Then user see loyalty only configurable product
    And clicks on a "configurable" product
    Then assert text of 'loyaltyOnlyLabel.key' is displayed

  @gloItRegression
  Scenario: Glo Add to cart from PLP as Loyalty User
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    And empty basket
    And user click on 'device' menu navigation
    And user see the product name display
    When user hover the product and click on add to cart on plp page
    Then added product is visible in basket

  @gloItRegression
  Scenario: Glo Add to cart from PLP as Non Loyalty User
    When create a new account via api
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user login with credentials registered via api
    And empty basket
    And user click on 'device' menu navigation
    And user see the product name display
    Then add to cart is not visible for loyalty products

    #automate issue 982198(the user need copyback)
    @gloItRegression
    Scenario: Validate voting button for article or video
      When user signs in with customer properties 'loginLoyaltyOptedVote.key' 'loginLoyaltyOptedVotePassword.key'
      And Glo user navigate to 'articleVoteUrl.key' page
      And wait for the page to fully load
      Then verify the vote button works correctly



