#This is the PDP feature file
##Testing
## PDP
## Attributes
Feature: PDP Page

	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option

	@gloItRegression @gloDeRegression @gloKzRegression @gloItLive @gloItSmoke @gloDeSmoke @gloDeLive @gloKzSmoke @gloKzLive @gloPlLive
	Scenario: Guest - PDP elements present as expected
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And user closes the RDB pop-up banner if present
		And Glo user clicks on buy button link and click on submenu
		And click first result
		Then assert PDP product title for Glo product
		#Then assert PDP product price

	@gloPlRegression
	Scenario: Guest - Glo Poland PDP elements present as expected
		And user click on 'device' menu navigation
		And click first result
		Then assert PDP product title for Glo product
		Then assert PDP product price
		Then assert PDP product description
		Then assert PDP specification and feedback

	@gloDeRegression
	Scenario:  GLO - PDP - Related Products and Up-Sell Products Section
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		When performs a search for "relatedProduct.key" and selects the item from the suggestion list
		And assert Related Products section and carousal at bottom of the page
		And assert UpSell Products section and carousal at bottom of the page
		Then user selects a product from Related Products section and assert Add To Cart button
		And Glo user clicks on buy button link and select first link from SubMenu
		And click on the updated product
		Then user selects a product from UpSell Products section and assert Add To Cart button

	#@gloItRegression This is out of scope due to new functionality US#571051
	Scenario Outline: 256443 Glo Hyper+ PDP
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on buy button link and click on submenu
		And clicks on a "<product-type>" product
		And the main image loads
		And the product displays a price
		And the PDP contains delivery details
		And the PDP contains shipping details
		And the PLP contains specification details
#		And the PDP displays whats in the box
#		And the PDP displays you might also like << this does not exist on live
		And the main body bubble is displayed and enabled
		When the main button is clicked
		Then the main body colour swatches are displayed
		And the side panel bubble is displayed and enabled
		When the side button is clicked
		Then the side panel swatches are displayed
		When the get inspired swatch heading is clicked
		Then the get inspired swatches are displayed
		Examples:
			| product-type |
			| simple	   |
			| configurable |

	@gloDeRegression @gloDeLive
	Scenario:  PDP - 'Further Information' Block Removed
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on buy button link and click on submenu
		And Glo user clicks on buy button link and select first link from SubMenu
		And Glo user clicks on any 'neo™ Summer Yellow Switch' link by text
		And assert Weitere Informationen block is not displayed
		And Glo user clicks on Shop link and click on 'glo™ TABAK HEATER' category link
		And click first result
		And assert Weitere Informationen block is not displayed
		And Glo user clicks on buy button link and select first link from SubMenu
		And Glo user clicks on any 'neo™ Tobacco Bright' link by text
		And assert Weitere Informationen block is not displayed

	#@gloDeRegression blocked by bug 492433
  	Scenario: PDP - download user guide as pdf
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on buy button link and click on submenu
		And Glo user clicks on Shop link and click on 'glo™ TABAK HEATER' category link
		And click first result
		Then assert link "downloadUserManualLinkText.key" is present
		And users clicks on the 'downloadUserManualLinkText.key' link
		Then assert a new tab is opened with url contains 'userManualPDFText.key'

	#@gloPlRegression blocked by bug 859735
	Scenario: logged-in user Ratings on PDP
		And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And user click on 'device' menu navigation
		And click first result
		And assert review rating is present
		When user click on add rating CTA
		Then opinion succesfully send

	@gloKzRegression
	#user story 115299 case
	Scenario:Show available tastes to logged in users on neo stick PDP
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And user closes the RDB pop-up banner if present
		And user click on neo sticks menu navigation
		When click the neo product by name "neo™ DEMI VIOLET TROPIC MIX"
		Then assert the product is displayed success

	@gloItRegression
	Scenario: logged-in user Ratings on PDP GLO IT
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And user click on 'device' menu navigation
		And click on the first result with a review
		When user click on add rating CTA
		And user fill the rating form and submit
		Then opinion successfully send

	@gloDeRegression
	Scenario: logged-in user product review on PDP
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on buy button link and select first link from SubMenu
		And click on the updated product
		Then assert rating star is present
		And assert text of 'productReviewText.key' is displayed
		And assert product review list is present

	@gloPlRegression @gloPlLive
	Scenario: Guest - Assert Quantity Exceeded Validation Error Message on PDP
		And performs a search for "gloHyperProductWithMaxOneQty.key"
		And Enter a item quantity of amount '51'
		And click add to cart button
		And assert quantity exceeded error message 'qtyValidationErrMessage.key' is displayed

	@gloPlRegression @gloPlLive
	Scenario: Logged-In - Assert Quantity Exceeded Validation Error Message on PDP
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And performs a search for "gloHyperProductWithMaxOneQty.key"
		And Enter a item quantity of amount '51'
		And click add to cart button
		And assert quantity exceeded error message 'qtyValidationErrMessage.key' is displayed

	@gloItRegression2
	Scenario: Guest - Personalisation text
		When Glo user clicks on buy button link and click on submenu
		And clicks on a "simple" product
		And user clicks on Add button to personalize the engraving product
		Then assert personalization options FANTASIES and Text are displayed
		And assert library of FANTASIES are visible to the user
		When user selects a FANTASIES pattern and assert selection
		And user selects text pattern
	    Then assert the direction Of The Text options Vertical and Horizontal are displayed
		And user add "10" characters into the field for Vertical direction
	    And user click the field for Horizontal direction
	    Then assert error message is displayed for characters number
		And user add "5" characters into the field for Horizontal direction
	    Then assert error message is not displayed for characters number

	@gloPlRegression
	Scenario: Cross-sell products at cart page
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And empty basket
		And user click on 'device' menu navigation
		And click first result
		Then assert PDP product title for Glo product
		And click add to basket
		And user clicks on the View Basket cta
		And user close the free gift module
		And assert Cross-sell products section at cart page
		And assert text of 'relatedProductText.key' is displayed
		And user add 'cross sell related' into the cart and verify item is added
		And user click on related product image and verify product pdp is opened

	@gloPlRegression
	Scenario: Related products at cart page
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And empty basket
		And user click on 'device' menu navigation
		And click first result
		Then assert PDP product title for Glo product
		And assert Related Products section at PDP
		And user add 'related product' into the cart and verify item is added

	@gloDeRegression
	Scenario: 873070 Verify supplementary CMS blocks display correct
		When performs a search for "supplementaryProduct.key"
		Then assert supplementary CMS blocks displayed

    # Automate bug 933190
	@gloKzRegression
	Scenario:the text of quantity is displayed on PDP
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And user closes the RDB pop-up banner if present
		And user click on neo sticks menu navigation
		And user click on related product image and verify product pdp is opened
		Then assert text of quantity is displayed on PDP page
