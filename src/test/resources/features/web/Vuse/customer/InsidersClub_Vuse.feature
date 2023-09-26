#This is the customer Loyalty Dashboard feature

  ######################################################################################################################
  # These scripts will not run on local chrome
  # But can be executed on Firefox - Application has Loyalty dashboard iframe loading issue (Takes a bit longer to load)
  # User should execute the scripts on browserstack Chrome
  ######################################################################################################################

Feature: Loyalty feature - Insiders Club

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

#  @VuseDEReg
  Scenario: Registration of new Loyalty user WITH selecting Loyalty checkbox while creating new user
    And create a new account with Loyalty
    And User clicks on Insiders Club menu option
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Registration of new user WITHOUT selecting Loyalty checkbox while creating new user
    And create a new account
    And User clicks on Insiders Club menu option
    And User Sign Up for Loyalty program
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Registration of new Loyalty user WITH selecting Loyalty checkbox while creating new user (Through Newsletter)
    And create a new account through Newsletter With Loyalty Signup
    And User clicks on Insiders Club menu option
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Registration of new Loyalty user WITHOUT selecting Loyalty checkbox while creating new user (Through Newsletter)
    And create a new account through Newsletter Without Loyalty Signup
    And User clicks on Insiders Club menu option
    And User Sign Up for Loyalty program
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Verify Loyalty dashboard through Home page when user is Logged in (not opted for Loyalty)
    And create a new account
    And Select 'InsidersClubHeaderMenuOpt.key' from header Menu
    And Click on 'Register Now' on Insiders Club Page
    And User Sign Up for Loyalty program
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Verify Loyalty dashboard through Home page when user is logged in (Already opted for Loyalty)
    And create a new account with Loyalty
    And Select 'InsidersClubHeaderMenuOpt.key' from header Menu
    And Click on 'Register Now' on Insiders Club Page
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg @VuseDELive
  Scenario: Verify Loyalty dashboard through Home page when user is not logged in (Already opted for Loyalty)
    And create a new account with Loyalty
    And attempt to logout
    And Select 'InsidersClubHeaderMenuOpt.key' from header Menu
    And Click on 'Register Now' on Insiders Club Page
    And Login again with newly created user in same session
    And User clicks on Insiders Club menu option
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Verify Loyalty dashboard through Home page when user is not logged in (not opted for Loyalty)
    And create a new account
    And attempt to logout
    And Select 'InsidersClubHeaderMenuOpt.key' from header Menu
    And Click on 'Register Now' on Insiders Club Page
    And Login again with newly created user in same session
    And User clicks on Insiders Club menu option
    And User Sign Up for Loyalty program
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Verify create a user through Loyalty home page (Skipping loyalty checkbox)
    And Select 'InsidersClubHeaderMenuOpt.key' from header Menu
    And Click on 'Register Now' on Insiders Club Page
    And create new account from login page
    And User clicks on Insiders Club menu option
    And User Sign Up for Loyalty program
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

#  @VuseDEReg
  Scenario: Verify create a user through Loyalty home page (Selecting loyalty checkbox)
    And Select 'InsidersClubHeaderMenuOpt.key' from header Menu
    And Click on 'Register Now' on Insiders Club Page
    And create new account from login page without selecting loyalty checkbox
    And User clicks on Insiders Club menu option
    Then assert default status for Loyalty is 'ExplorerTerm.key'
    Then assert 'ClubVorteileTerm.key' section is present
    Then assert 'VipVorteileTerm.key' section is available

  @VuseDEReg
  Scenario: Verify account creation with Loyalty Sign up through Checkout Registration Page
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And user selects proceed to checkout from cart page
    And user clicks the registration button
    And assert Loyalty signup checkbox is displayed with Loyalty TCs link
    And create a new account with Loyalty
    And Payment page details confirmed

  @VuseDEReg
  Scenario: Verify account creation without Loyalty Sign up through Checkout Registration Page
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And user selects proceed to checkout from cart page
    And user clicks the registration button
    And assert Loyalty signup checkbox is displayed with Loyalty TCs link
    When user attempts to create a new account without LoyaltySignUp
    And assert first voucher loyalty pop up is not displayed for non loyalty user
    And Payment page details confirmed

