Feature: BAT homepage feature - page scraping for elements
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

@LyftSmoke @LyftLive @LyftDKLive @LyftRegression2 @LyftDKReg
  Scenario: 11057 Browse to main home page and assert expected elements
    #Then assert pageTitle is displaying 'LYFT – Det tobaksfria alternativet till snus' // hardcoded so commented
    Then logo is displayed
    Then footer div is present and displayed

  @LyftDKReg
  Scenario: Switch to EN site and Browse to main home page and assert expected elements - EN site
    And switch to EN site
    And select allow all from cookie popup and select over 18 age confirmation option
    Then assert URL contains text 'lyftDkEnUrl.key'
    Then logo is displayed
    Then footer div is present and displayed

  @LyftRegression2
  Scenario: Lyft SE - Free delivery top banner
    And assert Free Delivery Banner is displayed on LAB pages
    And users clicks on the 'ShopLYFT.key' text link
    And click first result
    And assert Free Delivery Banner is displayed on LAB pages
    Then User should click on the link assert Free Delivery Banner is displayed
      | Prenumerationer.key    |
      | Blog.key               |
      | Storelocator.key       |
      | WhatAboutLyft.key      |
      | lyft2velo.key          |

  Scenario: Home Page - Guest - assert navigation and change in language on switching language using language selector
    Then assert that page title is 'homePageTitleInSwedish.key'
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    Then assert that page title is 'homePageTitleInEN.key'
    And assert navigation to home page with 'urlInEnglishOnLangSwitch.key' and response status code as 200
    Then assert text is displayed in English after switching language
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language
    Then assert that page title is 'homePageTitleInSwedish.key'

  Scenario: Home Page - Logged-In - assert navigation and change in language on switching language using language selector
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And click on the logo
    Then assert that page title is 'homePageTitleInSwedish.key'
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    Then assert that page title is 'homePageTitleInEN.key'
    And assert navigation to home page with 'urlInEnglishOnLangSwitch.key' and response status code as 200
    Then assert text is displayed in English after switching language
    And assert user remains logged-in after language switch
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language
    And assert user remains logged-in after language switch

  @LyftLive @LyftRegression2
  Scenario: Lyft SE - Assert Google Optimize code present in pagesource of the page
    And assert google optimize code snippet 'googleOptimizeCode.key' is present in pagesource
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And assert google optimize code snippet 'googleOptimizeCode.key' is present in pagesource

  @LyftRegression2
  Scenario: Lyft Social media icons are displayed and assert CTAs for each
    Then social media icons are displayed
    Then Lyft users click on Facebook link
    And assert lyft page title is 'homePageFacebookPageTitle.key'
    And navigate back to lyft homepage
    Then Lyft users click on Instagram link
    And assert lyft page title contains 'homepageInstagramPageTitle.key'

  # Automate bug 953932
  @LyftRegression2
  Scenario: Lyft Social media icons are displayed on footer and assert Facebook
    Then social media icons are displayed on footer
    Then Lyft users click on Facebook link on footer
    And assert a new tab is opened with url contains 'homePageFacebooURL.key'

  #Remove case due to #1034897
  Scenario: 404 page with redirection to add product in cart
    And navigate to invalid or non-existing URL 'nonExistingURL.key'
    Then user click on buy option from 404 page
    And click first result
    And select product strength or colour
    And click add to cart button
    Then confirm mini-basket displayed amount of '1'