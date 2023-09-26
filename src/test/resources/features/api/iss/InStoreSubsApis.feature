@iss
  Feature: In Store Subs APIs
Background: Get store list
  Given user is at the In Store Subs home page
  Given I can retrieve a list of stores

Scenario: Create QR code happy path
  And I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And I generate a QR code for type "" and site "https://www.vuse.com/gb/en/"

Scenario Outline: Staff login error scenarios
  When I attempt to login with the error condition "<error condition>"
  Then I am returned the following "<http response>"
  And error message "<message>" is returned
  And where "<error condition>" is for missing parameters the "<field name>" is reported
    Examples:
    | error condition   | http response | message                           | field name |
    | no outlet id      | 400           | is required. Enter and try again. | outlet_id  |
    | no username       | 400           | is required. Enter and try again. | username   |
    | no password       | 400           | is required. Enter and try again. | password   |
    | invalid outlet id | 400           | Store is invalid                  | outlet_id  |
    | invalid user      | 403           | Username or Password invalid      | username   |
    | invalid password  | 403           | Username or Password invalid      | password   |

Scenario Outline: QR generation error scenarios
  And I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And I attempt to generate a QR code with "<error condition>"
  Then I am returned the following "<http response>"
  And error message "<message>" is returned
  And where "<error condition>" is for missing parameters the "<field name>" is reported
    Examples:
    | error condition | http response | message                           | field name |
    | no type         | 400           | is required. Enter and try again. | type       |
    | no data         | 400           | is required. Enter and try again. | data       |
    | invalid data    | 400           | Malformed URL                     | data       |

Scenario: QR generation error - invalid token
  And I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And I attempt to generate a QR code with "invalid token"
  Then I am returned the following "401"
  And error message "The consumer isn't authorized to access %resources." is returned

Scenario: 434679 Set-up monthly payments via Email address valid data
  When I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And add a product to the cart
  And I call the handover email service
  Then the handover email service response is correct
  And the user receives an email containing "setUpMonthlySubscriptions"

Scenario Outline: 434679 Set-up monthly payments via Email address invalid data
  When I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And add a product to the cart
  And I call the handover email service with "<error condition>" error condition
  Then I get the correct value in the "<message>" response parameter
    Examples:
    | error condition       | message                                             |
    | invalid email address | 1005: Invalid E-mail -                              |
    | token not found       | The consumer isn't authorized to access %resources. |
    | token invalid         | The consumer isn't authorized to access %resources. |

Scenario: 434645 Send to Customer Journey
  When I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And add a product to the cart
  When I call the send to customer service
  Then the user receives an email containing "pickUpWhereYouLeftOff"

Scenario: 434026 434647 Retrieve a previous quote - happy path
  When I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And add a product to the cart
  When I call the send to customer service
  Then the user receives an email containing "pickUpWhereYouLeftOff"
  When I call the retrieve a quote service
  Then an empty array is returned in the response

Scenario: 434647 Retrieve a previous quote - invalid hash
  When I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And add a "12mg" strength "VUSE-EPEN-NS-DC-UK" product to the cart
  When I call the send to customer service
  Then the user receives an email containing "pickUpWhereYouLeftOff"
  When I call the retrieve a quote service with an invalid hash
  Then an invalid hash is indicated in the error response

Scenario: 434647 Retrieve a previous quote - one product goes out of stock
  When I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  Given sku "oosSkuSearchTerm2" is "in stock" stock
  And add a "12mg" strength "VUSE-EPEN-BA-UK" product to the cart
  Given sku "oosSkuSearchTerm2" is "out of stock" stock
  When I call the send to customer service
  Then the user receives an email containing "pickUpWhereYouLeftOff"
  When I call the retrieve a quote service
  Given sku "oosSkuSearchTerm2" is "in stock" stock
  Then the out of stock product is returned in the response

Scenario: Retrieve a previous quote - two products go out of stock
  When I login as a staff user "inStoreSubUser" with password "inStoreSubPassword"
  And add a "12mg" strength "VUSE-EPEN-BA-UK" product to the cart
  And add a "12mg" strength "VUSE-EPOD-CART-CHM-UK" product to the cart
  Given sku "oosSkuSearchTerm" is "out of stock" stock
  Given sku "oosSkuSearchTerm2" is "out of stock" stock
  When I call the send to customer service
  Then the user receives an email containing "pickUpWhereYouLeftOff"
  When I call the retrieve a quote service
  Given sku "oosSkuSearchTerm" is "in stock" stock
  Given sku "oosSkuSearchTerm2" is "in stock" stock
  Then the out of stock product is returned in the response