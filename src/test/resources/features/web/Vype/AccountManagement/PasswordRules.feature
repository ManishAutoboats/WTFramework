Feature: BAT Account Management - Password rules
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
	And user clicks on 'signInLink.key' link from Person Menu

  @regression @18939 @pass @MXReg @NLlive @nl @ITReg @MXReg @fr @frlive @COReg
  Scenario: 18939 Password rules
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in
    And users clicks on the 'dashboardEditLinkText.key' text link
    And select update password checkbox
    And update password with '1' and assert error msg
    And update password with '122' and assert error msg
    And update password with '1333' and assert error msg
    And update password with '144444' and assert error msg
    And update password with '15555555' and assert error msg
    And update password with '^&*(^&*(^&*(^*(' and assert error msg
    And update password with 'asferwq' and assert error msg

  @fr @frlive @dksmoke @dk @VuseDKReg
  Scenario: Vype FR, Vype DK - Password rules
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in
    And click on Edit button
    And select update password checkbox
    And update password with '1' and assert error msg
    And update password with '122' and assert error msg
    And update password with '1333' and assert error msg
    And update password with '144444' and assert error msg
    And update password with '15555555' and assert error msg
    And update password with '^&*(^&*(^&*(^*(' and assert error msg
    And update password with 'asferwq' and assert error msg




