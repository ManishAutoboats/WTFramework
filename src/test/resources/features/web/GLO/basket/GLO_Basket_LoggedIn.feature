#This is the Basket feature file
  ##Testing
    ## Presence of Basket icon
    ## Click on basket
      ## verify populated
      ## verify empty
Feature: BAT Basket feature - Logged In
Background: Navigate to BAT Home page 
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloDeRegression
  Scenario:  GLO - Basket - Cross-Sell Products Section
    Given user logins Magento Admin and assert Related,Up-Sell and Cross-Sell Products Configuration
    And user navigates to BAT home page for FE validation
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty glo mini basket
    And Glo user clicks on buy button link and select first link from SubMenu
    And click on first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And assert Cross Sell Products section and carousal on the Cart page
    And assert Add To Cart button is displayed for Cross Sell Products and assert CTA

  @gloPlRegression2
  Scenario:  Register a user with Marketing Communication Consents and assert free consumable packs in Cart
    When create a new account with marketing communication checks selected
    And user navigate to my account page
    And user add address details after registration - GloPL
    #And select a GLO product and view basket
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And user select product add to basket and view basket
    Then assert message 'freePacksWithConsentMessage.key' is displayed in header
    Then add one free consumable pack and assert user is not allowed to checkout

  #@gloPlRegression bug: 865501 scenario is invalid because as per AC logged-in user should get 3 free gift with 1 device
  Scenario: Register a user without Marketing Communication Consents and assert No free consumable packs in Cart
    When create a new account without selecting marketing communication checks
    And user navigate to my account page
    And user add address details after registration - GloPL
    #And select a GLO product and view basket
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And user select product add to basket and view basket
    Then assert Free gift item is not displayed


  @gloPlRegression2
  Scenario: Add and Remove product from basket
    When create a new account with marketing communication checks selected
    And user navigate to my account page
    And user add address details after registration - GloPL
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    And user clicks on the View Basket cta
    And assert summary section and total is matching in basket page without code
    And delete product from remove button
    And user asserts basket is empty


  @gloKzRegression
#  USER STORY 111008
  Scenario: Basket - Shipping Bar message - Free shipping price Countdown
    When create a new account via api
    And user login with credentials registered via api
    And user closes the RDB pop-up banner if present
    Then assert shipping bar message displayed on top of the page
    And fetch free shipping threshold price from the message displayed
    And Glo user clicks on Shop link and click on 'ShopCategory.key' category link
    And click on first result
    When click add to cart button js
    And user clicks on the View Basket cta
    Then assert shipping bar msg changes based on cart total price by increasing product quantity

  @gloPlRegression @gloPlLive
  Scenario:  Loader is present at cart page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And select product strength or colour
    When click add to cart button js
    And user clicks on the View Basket cta
    Then assert message 'freePacksWithConsentMessage.key' is displayed in header
    Then add one free consumable pack and assert user is not allowed to checkout
    
    #Excluding scenario from GloPL - refer bug 539756
  #@gloDeRegression #@gloPlRegression
  Scenario: Remove SKU from basket with "-" sign
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    And user clicks on the View Basket cta
    And verify the remove sku functionality on 'basketpage'
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    And verify the remove sku functionality on 'minicart'

  @gloPlRegression
  Scenario: Basket - Price includes taxes, 979936 - Cart summary check
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And empty basket
    And select a GLO product and view basket
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And assert text of 'subTotalText.key' is displayed
    And assert text of 'summaryTitleText.key' is displayed
    And assert text of 'summaryTotalText.key' is displayed
    Then verify product cost include taxes

  @gloItRegression
  Scenario: Basket - Price includes taxes GLO IT
    Given Glo user clicks on PersonIcon and Navigate to the Login Page
    And user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And empty basket
    And Glo user clicks on buy button link and click on submenu
    And click on first result
    And select product strength or colour
    When click add to cart button js
    And click on basket icon
    And click on proceed to checkout button on mini cart
    Then assert text of 'taxRateText.key' is displayed
    And verify product cost include taxes

  @gloPlRegression
  Scenario: Free pack visibility improvement
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And select product strength or colour
    When click add to cart button js
    And user clicks on the View Basket cta
    Then free pack pop up is displayed

  @gloDeRegression
  Scenario: Start Your Shopping redirect to homepage
    When search and add the product into the basket 'ProductName.key'
    And empty basket
    And user click on Start your shopping button
    Then assert user is navigate to homepage

  Scenario: Assert Free gift item message.
    When create a new account
    Then user click on search icon and enter product 'ProductName.key' in the search bar
    And user select product add to basket and view basket
    And use add free gift module according to device
    And assert msg on free gift item

  @gloDeRegression
  Scenario: Remove SKU from basket on Mini Cart
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty

  @gloDeRegression
  Scenario: Remove SKU from basket on Basket page
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    And user clicks on the View Basket cta
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty

  @gloDeRegression
  Scenario: Quantity Selector switch to free text on max value selection and switch back to dropdown on Mini Cart
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    Then user selects maximum quantity available in Quantity dropdown on mini cart
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '22' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '22'
    Then user enters quantity '6' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '6'

  @gloDeRegression
  Scenario: Quantity Selector switch to free text on max value selection and switch back to dropdown on Basket page
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    And user clicks on the View Basket cta
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '22' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '22'
    Then user enters quantity '8' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '8'

  @gloDeRegression
  Scenario:  Verify product quantity displayed correctly in title
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And click first result
    And click add to basket
    And user clicks on the View Basket cta
    And assert quantity number is '1' in  title
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    Then user enters quantity '8' in the mini-cart text-field and update
    And assert quantity number is '8' in  title

  @gloKzRegression
  Scenario: Glo KZ-Remove SKU from basket on Mini page
    When create a new account via api and log in with the account
    And user closes the RDB pop-up banner if present
    And Glo user clicks on Shop link and click on 'ShopCategory.key' category link
    And click on first result
    When click add to cart button js
    And click on basket icon
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty

  @gloKzRegression
  Scenario: Glo KZ-Remove SKU from basket on Basket page
    When create a new account via api and log in with the account
    And user closes the RDB pop-up banner if present
    And select a GLO product and view basket
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty

  @gloKzRegression
  Scenario: Glo KZ- Quantity Selector switch to free text on max value selection and switch back to dropdown on Mini Cart
    When create a new account via api and log in with the account
    And user closes the RDB pop-up banner if present
    And Glo user clicks on Shop link and click on 'ShopCategory.key' category link
    And click on first result
    When click add to cart button js
    And click on basket icon
    Then user selects maximum quantity available in Quantity dropdown on mini cart
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '15' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '15'
    Then user enters quantity '14' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '14'

  @gloKzRegression
  Scenario: Glo KZ- Quantity Selector switch to free text on max value selection and switch back to dropdown on Basket page
    When create a new account via api and log in with the account
    And user closes the RDB pop-up banner if present
    And select a GLO product and view basket
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    And assert Qty dropdown control switches to free text entry and update CTA is populated
    Then user enters quantity '15' in the mini-cart text-field and update
    And assert Qty field continues to display as a free text field when qty is more than max value
    Then confirm mini-basket displayed amount of '15'
    Then user enters quantity '14' in the mini-cart text-field and update
    And assert Qty free text field switches back to a dropdown when qty is less than max value
    Then confirm mini-basket displayed amount of '14'

  @gloKzRegression
  Scenario:  Glo KZ- Verify product quantity displayed correctly in title
    When create a new account via api and log in with the account
    And user closes the RDB pop-up banner if present
    And select a GLO product and view basket
    And assert quantity number is '1' in  title
    Then user selects maximum quantity available in Quantity dropdown on mini basket
    Then user enters quantity '8' in the mini-cart text-field and update
    And assert quantity number is '8' in  title