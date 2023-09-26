#This feature - Verify features on My LAB Landing page
  #for review / purchase LAB products.
@LyftRegression
Feature: MyLAB/Insights Landing Page and Links CTAs

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario:My Opinion Block and My Rewards and Badges for Previous and New Collections with CTAs(Before Survey)
    And create a new account
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And users clicks on the 'MyLABLinkHeader.key' text link
    Then assert Insights Greeting block for logged-in user at the top
    And assert My Opinion block
    And assert CTA for Choose your First Taste link
    When users clicks on the 'MyLABLinkHeader.key' text link
    And assert My Rewards section for active collection with inactive badges
    And assert previous collection badge titles appears as per collection names
    When user clicks on Previous collection tiles and assert badges for each
    Then I delete customer account

  @LyftLive
  Scenario: Verify My LAB(Insights) Landing Page for guest users when login rule is disabled
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And users clicks on the 'MyLABLinkHeader.key' text link
    Then assert Insights Greeting block is not displayed for guest user
    And assert login to lab section is displayed for guest user

  Scenario: Survey Rules for any Flavour,Brand or Research in no specific sequence after placing an order with Active Collection
    And create a new account via api and log in with the account
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And user select New collection
    And user selects strength 'productStrength.key' for LAB product,selects Quantity '1' and add to cart
    And click on Basket icon and proceed to Payment Page
    And Check alternative payment order option
    And Check paypal option
    And tick agree to terms and conditions
    And select place order
    And click on Continue on Paypal button on Checkout page
    And user sign-in into Paypal account with username 'PaypalValidEmail.key' and password 'PaypalValidPassword.key'
    And paypal page - click continue
    Then assert text of 'ThankForPurchase.key' is displayed
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And users clicks on the 'MyLABLinkHeader.key' text link
    And assert all Surveys for Brand, Innovation and Research are displayed
    And assert Survey rules appear in no specific sequence for active collection