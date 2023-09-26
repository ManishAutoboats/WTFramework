#This is the Basket feature file
  ##Testing
    ## Presence of Basket icon
    ## Click on basket
      ## verify populated
      ## verify empty
Feature: BAT Basket feature - Guest 
Background: Navigate to BAT Home page 
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @gloPlLive @gloPlRegression2 @gloItRegression2 @gloDeRegression2
  Scenario: Basket tests - Basket Icon presence
    Then ensure basket icon is displayed
    And click on basket icon
    And basket is empty message is presented

  @gloPlRegression2
  Scenario: Glo Basket - Able to Apply Discount as a Guest User and assert voucher is present after login
    Then select a GLO product and view basket as a guest user
    And apply discount '10off' to the discount module and assert success message
    And click on proceed to checkout for guest user
    And select create new account from checkout PopUp
    And create a new account for guest
    And assert the applied discount code 'validDiscountCode.key' is still present in basket
    When user navigates to my account page from header
    And user selects deleteMyAccount link on my account Page

  @gloPlRegression2
  Scenario: 837569 Glo Basket - Qty is not editable on cart page
    When select a GLO product and view basket as a guest user
    Then Qty is not editable
    
  @gloKzRegression
  Scenario: 917653 Basket - Empty basket - Assert empty message
    When create a new account via api and log in with the account
    And Glo user clicks on Shop link and click on 'ShopCategory.key' category link
    And click on first result
    When click add to cart button js
    And user clicks on the View Basket cta
    And empty basket from cart page
    Then basket is empty message is presented

  @gloItRegression2
  Scenario: Add free gift on shopping cart
    When Glo user clicks on buy button link and click on submenu
    And clicks and add hyperplus product to cart
    Then user adds free device from a popup
    And assert msg on free gift item