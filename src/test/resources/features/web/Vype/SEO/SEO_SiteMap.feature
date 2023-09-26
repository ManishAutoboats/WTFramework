#This is the Search feature file
  ##Testing
    ## Presence of search bar
    ## Search returning no results
      ## valid search
      ## invalid search
        ## php data-injection
        ## symbols
    ## Search returning results
    ## Nav to Registration page
    ## Nav to Forgotten password page
@9511 @SiteMap
Feature: BAT Site Map feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page

  @smokeLite
  Scenario: Navigate to UAT Vype sitemap and assert page content is correct
    When user navigates to the following URL 'https://www-eu-uat-global-vype.non-prod.marketing.bat.net/media/sitemap_uk_vype.xml'
    Then assert text of 'XmlFileText.key' is displayed
    And assert text of 'XmlFileVersionText.key' is displayed

  @smokeLite
  Scenario: Navigate to UAT EPOK sitemap and assert page content is correct
    When user navigates to the following URL 'https://www-eu-uat-global-epok.non-prod.marketing.bat.net/media/sitemap_de_epok.xml'
    Then assert text of 'XmlFileText.key' is displayed
    And assert text of 'XmlFileVersionText.key' is displayed

  @live
  Scenario: Navigate to Vype Production sitemap and assert page content is correct
    When user navigates to the following URL 'https://www.govype.com/gb/en/media/sitemap/vype_gb_en.xml'
    Then user closes the alert if present
    Then assert text of 'XmlFileText.key' is displayed
    And assert text of 'XmlFileVersionText.key' is displayed

  Scenario: Navigate to EPOK Production sitemap and assert page content is correct
    When user navigates to the following URL 'https://epok.de/media/sitemap_de_epok.xml'
    Then assert text of 'XmlFileText.key' is displayed
    And assert text of 'XmlFileVersionText.key' is displayed

    




