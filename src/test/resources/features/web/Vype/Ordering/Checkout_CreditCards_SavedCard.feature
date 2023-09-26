##This is the Checkout feature file
    ## Credit Cards - both debit and credit options
Feature: BAT checkout feature - SAVED CARD
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu

  @desmoke @de #@COReg saved cards is out of scope
  Scenario: Checkout tests - Signed In - Credit Cards - Saved Card
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed
    And enter select package shop details
    And select Card Payment option then credit Card option
    And click use saved card
    And click Use Saved Cards - Vype IT
    And tick agree to terms and conditions
    And select place order
    #And assert print receipt link is present
    #And assert text of 'ThankForPurchase.key' is displayed
    #And assert text of 'yourOrderNumberText.key' is displayed
    And grab and output Order number
    #And user clicks on the 'continue shopping' button

  @nl @checkout @nlSmoke
  Scenario: Checkout tests - Saved Card Complete flow
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate DOB field with 'ValidDOB.key'
    And populate gender field with 'Gender.key'
    And populate address fields with valid details
    And populate signin fields
    Then user selects the create an account button
    And assert text of 'successRegistraionMsg.key' is displayed
#    And Search for a product 'searchTerm.key' , and add to cart from PDP page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And Credit Card - VISA order option and press next
    And enter visa card details
    And click Save This Card checkbox
    And tick agree to terms and conditions and select place order
#    And user clicks on Next button and place the order
    And assert text of 'ThankForPurchase.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And click on a re-order link
    And user selects proceed to checkout from cart page
    And Payment page details confirmed
    And enter select package shop details
    And select Card Payment option then credit Card option
    And click use saved card
    And click Use Saved Cards - Vype IT
    And tick agree to terms and conditions and select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And user deletes the account and verify deletion success page

  @dksmoke @dk @regression @smokeLite @smokeFast @IEReg @ITSmoke @ITReg @fr @frSmoke
  Scenario: Checkout tests - Delete Saved Cards and place an order with a New Card
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'savedCardText.key' link
    And user deletes all the saved cards from My Saved Cards list
    And Search for a product 'searchProduct.key' , and add to cart from PDP page
    And click on Basket icon and proceed to Payment Page
    And Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And click Save This Card checkbox
    And tick agree to terms and conditions and select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And click on a re-order link
    And user selects proceed to checkout from cart page
    And Payment page details confirmed
    And enter select package shop details
    And select Card Payment option then credit Card option
    And click use saved card
    And click Use Saved Cards - Vype IT
    And tick agree to terms and conditions and select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'savedCardText.key' link
    And user deletes all the saved cards from My Saved Cards list
    And assert text of 'noSavedCardsText.key' is displayed

  #@COReg because of  Defect# 531905 (Saved cards is out of scope)
  Scenario: Checkout tests - Saved Card Complete flow - reg via api
    When create a new account via api
    And user login with credentials registered via api
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed
    And user complete "MasterCard" payment details and click on place the order
    And assert text of 'ThankForPurchase.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And click on a re-order link
    And user selects proceed to checkout from cart page
    And Payment page details confirmed
    And enter select package shop details
    And select Card Payment option then credit Card option
    And click use saved card
    And tick agree to terms and conditions and select place order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And delete the account via api