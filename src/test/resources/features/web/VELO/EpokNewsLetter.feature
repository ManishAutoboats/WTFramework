#This is the newsletter feature file
  ##Testing
    ## Invalid email entry into the newsletter sub
    ## Entering existing email into newsletter sub
    ## Entering valid (random) email into newsletter sub
  @epoknewsletter @liveEpok @epoksubs @epokLive 
Feature: BAT newsletter Guest Feature

Background: Navigate to EPOK Home Page
  Given user navigates to BAT home page
  And select allow all from cookie popup and select over 18 age confirmation option
  And user click on PersonIcon and Navigate to the Login Page
  When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'


 Scenario: Newsletter subscription
   And click on edit button of newsletter
   And select check box of newsletter subscription
   And Click on save button
   Then Epok assert text of my account newsLetterSubscriptionMsg.key is displayed
     
   
  