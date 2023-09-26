@LyftRegression2 @LyftDKReg
Feature: BAT Blog

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: Blog page present and correct
    And user clicks the blog icon -lyft
    Then url contains 'VypeBlogUrlContain.key'
    Then social media links are present on blog page
    And blogs are listed in descending order

  Scenario: Blog Image and link navigation
    And user clicks the blog icon -lyft
    Then url contains 'VypeBlogUrlContain.key'
    Then click on first blog image and verify selected blog page open
    And user clicks the blog icon -lyft
    Then click on first blog link and verify selected blog page open
    And user clicks on Twitter Icon
    Then Twitter login page is present