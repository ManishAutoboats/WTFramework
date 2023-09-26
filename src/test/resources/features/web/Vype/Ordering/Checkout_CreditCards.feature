##This is the Checkout feature file
    ## Credit Cards - both debit and credit options
Feature: BAT checkout feature - CREDIT CARDS
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed

  @20390 @regression @checkout  @22647 @dksmoke  @fr @dk @nl  @checkout @VuseDKReg
  Scenario: Checkout tests - not agreeing to Ts and Cs error message
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And select place order
   And assert text of 'acceptTsAndCsErrorMessageText.key' is displayed

  #@MXReg THIS GETS CALLED BY OTHER LOCALES AT TOP OF CLASS, SO INJECTION FAILURES TO ALL TAGS ABOVE
  #Scenario: Checkout tests - not agreeing to Ts and Cs error message for MX
 # And assert text of 'uncheckTCsMessage.key' is displayed when Pay button is clicked without selecting Terms and Conditions
    
  @22647 @20390 @creditCard @nlSmoke @dksmoke @smokeFast @desmoke  @frSmoke @ITSmoke @ITReg @COSmoke @de @IEReg @regression @checkout @22647 @dksmoke  @fr @dk @nl  @checkout @VuseDKReg #@COReg Save cards is out of scope
  Scenario: Checkout tests - Signed In - Credit Cards - Master Card - Debit Card Option - Payment
    And enter select package shop details
    And selectable shipping options displayed
    And click on shipping method
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And click save card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button and place the order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed

  @smokeFast @desmoke @nlSmoke @frSmoke @ITSmoke @ITReg @de @IEReg @regression @checkout  @22647 @dksmoke  @fr @dk @nl  @checkout @VuseDKReg
  Scenario: Checkout tests - Signed In - Credit Cards - VISA Card - Debit Card Option - Payment
    And selectable shipping options displayed
    And click on shipping method
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button to move head and place the order
    And assert text of 'yourOrderNumberText.key' is displayed
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed

  @MXReg
  Scenario: Checkout tests - Signed In - Credit Cards - With Electronic Invoice - Payment
    And customer selects electronic invoice check and enter details
    And enter mastercard details
    And select place order
    And user clicks on Next button and place the order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed

  #@sprint1 @18283 @creditCard - REMOVING - AMEX IS NOT AVAILABLE FOR UK
  #Scenario: Checkout tests - Signed In - Credit Cards - AMEX Card - Credit Card option - Payment
  #  And Credit Card - AMEX order option and press next
  #  And enter amex card details
  #  And tick agree to terms and conditions
  #  And select place order
  #  And assert text of 'ThankForPurchase.key' is displayed
  # And assert print receipt link is present
  #  And grab and output Order number
  #  And user clicks on the 'continue shopping' button
  #  And user clicks the person icon
  #  And users clicks on the 'logoutText.key' text link

  #@ITLive NA/for VYPE-IT
  Scenario: Populate selected Address from Look up and amend manually - checkoutOverlay
    And click add new address button
    When Glo start entering the address in checkoutOverlay with streetKeyword.key
    Then Glo should be presented with Loqate address look up auto-completion
    When Glo has selected an address from the auto-completion
    Then Glo address fields should be populated with the selected address
    And Glo can amend the fields manually if desired

