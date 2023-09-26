#This is the BAT Navigation feature
#@smoke @smokeLite @epokRegression
  @EpokHome 
Feature: EPOK HomePage feature
  # TO BE CODED

  Background: Navigate to EPOK Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

     
    @epokUat  
    Scenario: Epok Home Page-Ensure all expected element are present
    And Epok Icon is displayed
    And Person Icon is displayed
    And Cart Icon is Displayed
    And Hamburger Menu is not displayed
    And assert navigation menu with 'HeaderLinks.key' links on the home page
   # And accept Cookies Button is displayed and enabled
    
   @NavigationEpok @epokUat 
     Scenario:  Click On Person Icon and Cart ICon then retrun to HomePage
    When user click on PersonIcon and Navigate to the Login Page
    Then  assert login pageTitle is 'loginPageTitle.key'
    And click on Epok ICon and Navigate to Home Page
    Then assert pageTitle is 'homePageTitle.key'
    When user click on cart icon and Navigate to cart Page
    And assert cart pageTitle is 'basketPageTitle.key'
    And click on Epok ICon and Navigate to Home Page
    Then assert pageTitle is 'homePageTitle.key'
    
   
     
     @epokUat
   Scenario: Check the SignIn link 
   When users Clicks on Text Links 'homepageSignInBtn.key'
   Then url contains 'homepageSignInUrlContains.key'
 
     
       @epokUat 
     Scenario: Check  Facebook social Media link and its Navigation page
     When users click on Facebook link
     Then assert cart pageTitle is 'homepageFacebookTitle.key'
     And Close the child window
     And users Navigate to parent window
     
        @epokUat
     Scenario: Check  Instagram social Media link and its Navigation page
     When users click on Instagram link
     Then assert cart pageTitle is 'homepageInstgramTitle.key'
      And Close the child window
       And users Navigate to parent window
    
     
 
     
   
     
    
   
    
    
     
    
    
   
    
    
    













