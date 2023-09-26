Feature: BAT navigation feature - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
  Scenario: Mobile - 21398 Guest, opens hamburger menu select main hamburger links-Vuse
     And user navigate to link and verify URL and title
      | LinkToClick                 | UrlToContain                   | Title                        |
      | abonnement.key              | abonnementUrlText.key          | abonnementTitle.key          |
      | VypeBlog.key                | VypeBlogUrlContain.key         | BlogTitle.key                |
      | toutsavoirsurlavape.key     | toutsavoirsurlavapeUrlText.key | toutsavoirsurlavapeTitle.key |
      | apropos.key                 | aproposUrlText.key             | aproposTitle.key             |
