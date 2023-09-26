#This feature - Verify features on Lyft Lab Landing page
  #for review / purchase LAB products.
Feature: Navigation on Features and Links CTAs

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page

  #@LyftSmoke @LyftDKReg - Can be covered with EYES
  Scenario: Lyft Lab Footer Theme
    And verify Lyft Lab logo is displayed
    And assert background color 'bgColorFooterLab.key' of the footer section
    And verify Lyft logo on navigating links
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidNewEmail.key' 'loginValidNewPassword.key'
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on basket icon
    And user clicks on checkout button
    And verify Lyft logo at checkout page is displayed

  #@LyftRegression @LyftLive Can be covered with EYES
  Scenario: Lyft/Lyft LAB Logo and Footer Theme
    And assert Lyft LAB Logo in the Footer section
    And assert background color 'bgColorFooterLab.key' of the footer section
    And assert text color 'linksColorFooterLab.key' for Lyft Footer Links
    Then users clicks on the 'LYFTLinkBurgerMenu.key' text link
    And assert Lyft Logo in the Footer section
    And assert background color 'bgColorFooterLyft.key' of the footer section
    And assert text color 'linksColorFooterLyft.key' for Lyft Footer Links