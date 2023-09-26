
Feature: BAT Device Trial Add to Cart tests
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup

  @PDFDeviceTrail @gloDeRegression2
  Scenario: Device Trial only for first purchase
    And create a new account
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And user select credit card as payment option
    And user select the Visa card as type
    And enter visa card details
    And Device Trial Checkbox is displayed
    And user clicks Device Trial Checkbox
    Then select place order from Card
    And assert that checkout was successful
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And Device Trial error message is displayed

  @PDFDeviceTrail @gloDeRegression2
  Scenario: One Device Trial Product per transaction
    And create a new account
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And Device Trial error message is displayed

  @PDFDeviceTrail @gloDeRegression2
  Scenario: Non Device Trial product in Basket
    And create a new account
    And user hovers over Glo shop and selects Neo Sticks
    And click on first result
    Then click add to cart button
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And Device Trial error message is displayed

  @PDFDeviceTrail @gloDeRegression2
  Scenario: Device Trial Order Payment Block and checkbox is displayed
    And create a new account
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And PaymentInfoBlock is displayed
    And Device Trial Checkbox is displayed

  @PDFDeviceTrail @gloDeRegression2
  Scenario: Device Trial Order Payment Block and Checkbox is not displayed
    And create a new account
    And user hovers over Glo shop and selects Neo Sticks
    And click on first result
    Then click add to cart button
    And click on proceed to cart page from on mini cart
    And PaymentInfoBlock is not displayed
    And Device Trial Checkbox is not displayed

  @PDFDeviceTrail @gloDeRegression2
  Scenario: Customer must be logged in and select configurable product
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And login is displayed
    And create a new account
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
#    And configurable product is present
#    And customer selects configurable product
    Then Customer clicks Start Device Trial link on landing page

  @PDFDeviceTrail @gloDeRegression2
  Scenario: Account Order History Device Trial Details
    And create a new account
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And user select credit card as payment option
    And user select the Visa card as type
    And enter visa card details
    And Device Trial Checkbox is displayed
    And user clicks Device Trial Checkbox
    Then select place order from Card
    And assert that checkout was successful
    And user closes the Secure Your Purchase alert if present
    And user clicks the person icon
    And user selects orderHistory link on my account Page
    And users clicks on the View Order link
    And device trial order table is displayed

  @gloDeRegression2
  Scenario: Place Order with Free Device Trial and assert auto Newsletter subscription
    And create a new account
    And select from myAccount links NewsLetter link
    And assert user have not subscribed to Newsletter by default
    Then user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And user select credit card as payment option
    And user select the Visa card as type
    And assert Newsletter Subscription option not displayed for Free Trial Checkout
    And enter visa card details
    And user clicks Device Trial Checkbox
    Then select place order from Card
    And assert that checkout was successful
    Then user clicks the person icon
    And assert user is auto subscribed to Newsletter after successful Device Trial Order

  #@gloDeRegression2 - commented out due to defect #571476
  Scenario: Place order (Without Free Device Trial) with Newsletter Subscription Validated
    And create a new account
    And select from myAccount links NewsLetter link
    And assert user have not subscribed to Newsletter by default
    Then select a GLO product and checkout
    And user select Card Payment option
    And user select credit card as payment option
    And assert Newsletter Subscription option not selected by default
    And user select Card Payment option
    And user select credit card as payment option
    And user select the Visa card as type
    And user clicks Newsletter Subscription option on Checkout
    And enter visa card details
    Then select place order from Card
    And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
    Then user clicks the person icon
    And assert user subscribed to Newsletter after successful Order

  @gloDeRegression2
  Scenario: Device Trial - Additional block for SEO friendly content for Guest and Logged-in Users
    Then user navigates to Device Trial landing page
    Then assert SEO friendly block on Device Trial Landing page below Start Trial CTA
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    Then user navigates to Device Trial landing page
    Then assert SEO friendly block on Device Trial Landing page below Start Trial CTA

  Scenario: Device Trial - Existing user error message
    Then Glo user clicks on PersonIcon and Navigate to the Login Page
    Then user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user navigates to Device Trial landing page
    Then Customer navigates to Device Trial landing page
    Then Customer clicks Start Device Trial link on landing page
    And existing user error msg is displayed