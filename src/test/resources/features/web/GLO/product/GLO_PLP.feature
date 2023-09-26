#This is the PLP feature file
  ##Testing
    ## PLP Filtering story
Feature: PLP page

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @gloKzSmoke @gloKzLive @gloPlLive @gloDeSmoke @gloDeLive @gloDeRegression @gloKzRegression @gloItLive @gloItRegression @gloItSmoke
  Scenario: PLP page intact with required informantion
    When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And Glo user clicks on buy button link and click on submenu
    Then url contains 'DeviceUrlText.key'

  @gloPlRegression @gloItRegression @gloItSmoke
  Scenario: Glo poland PLP page intact with required informantion
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user click on 'device' menu navigation
    And user see the product name display
    And user see the Buy Now CTA display
    Then url contains 'DeviceUrlText.key'

  #@gloItRegression @gloItSmoke This is out of scope due to new functionality US#571051
    Scenario: 256385 GLO Hyper+ PLP simple product-
      When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
      And empty basket
      When users clicks on the 'gloShopLinkText.key' text link
      Then there is a "simple" Hyper Plus product displayed
      And all Hyper Plus products have a configurable icon
      And clicks on a "simple" product
      Then the configuration page is displayed
      When users clicks on the 'gloShopLinkText.key' text link
      And clicks on a "simple" product item link
      Then the configuration page is displayed
      When users clicks on the 'gloShopLinkText.key' text link
      And clicks on a "simple" product button
      Then the simple product is added to cart

  #@gloItRegression @gloItSmoke This is out of scope due to new functionality US#571051
  Scenario: 256385 GLO Hyper+ PLP configurable product-
    When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    When users clicks on the 'gloShopLinkText.key' text link
    Then there is a "configurable" Hyper Plus product displayed
    And all Hyper Plus products have a configurable icon
    And clicks on a "configurable" product
    Then the configuration page is displayed
    When users clicks on the 'gloShopLinkText.key' text link
    And clicks on a "configurable" product item link
    Then the configuration page is displayed
    When users clicks on the 'gloShopLinkText.key' text link
    And clicks on a "configurable" product button
    And the configuration page is displayed

  @gloPlRegression @gloDeRegression
  Scenario: Glo Add to cart from PLP
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user click on 'device' menu navigation
    And user see the product name display
    And user hover the product and click on add to cart on plp page
    Then product added in basket

  @gloItRegression
  Scenario: Glo IT Add to cart from PLP
    Given Glo user clicks on PersonIcon and Navigate to the Login Page
    And user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user click on 'device' menu navigation
    And user see the product name display
    And from PLP click add to cart
    Then product added in basket

  @gloDeRegression
  Scenario: Product count is same as product present on plp
    And user click on 'neo sticks' menu navigation
    Then verify product count is same as product present

  @gloPlRegression
  Scenario: 945992 Product count is same as product present on multiple plp
    Then verify product count is same as product present when user click on menu navigation
    |device   |
    |SKLEP    |
    |AKCESORIA|

  @gloItRegression2
  Scenario: merge devices / accessories sections
    When user click on 'device' menu navigation
    Then assert text of 'devicesText.key' is displayed
    And assert text of 'accessoriesText.key' is displayed
    And url contains 'DeviceUrlText.key'

  @gloDeRegression
  Scenario: Product sorting on based of price
    And user click on 'neo sticks' menu navigation
    And select low to high sort filter
    Then verify product is arrange from low to high price

  #automated bug 831287(The products shown count is incorrect in PLP)
  @gloDeRegression
  Scenario: Shows correct product count in plp page
    When user hovers over Glo shop and selects Neo Sticks
    Then assert the products count is correct
