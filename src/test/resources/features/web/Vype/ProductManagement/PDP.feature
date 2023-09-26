Feature: BAT PDP feature

Background: Navigate to BAT Home page 
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option

@MXReg2 @NonCaptchaLive @MXSmoke @26566 @live @ITReg2 @ITLive @IEReg @IElive @VuseUKReg2 @VuseFRReg2
Scenario: Guest - PDP elements present as expected 
	And user click on search icon and submits the following search term 'searchTermVype.key'
	And get all lists from page
	And click first result
	Then assert PDP product title 
	Then assert PDP product price 
	Then assert PDP product description 

@regression @26566 @live @ITReg2 @ITLive @IEReg @IElive
Scenario: 26566 / 20318 PDP Vype Strength Swatch 
	And user click on search icon and submits the following search term 'searchTermVype.key' 
	And assert product colour is displayed 
	And assert product strength is displayed

@frlive @fr
 Scenario: 65296 PDP Avis Upgrade - Stars/Ratings
	 Then user click on Avis Ratings icon and assert Avis Reviews pop up is displayed

@LyftRegression2
 Scenario:  PDP - Related Products and Up-Sell Products Section
	Given user logins Magento Admin and assert Related,Up-Sell and Cross-Sell Products Configuration
	And user navigates to BAT home page for FE validation
	And empty basket before adding related products
	And user click on search icon and submits the following search term 'searchRelatedProdTerm.key'
	Then user clicks on the first link if navigated to PLP page
	And assert Related Products section and carousal at bottom of the page
	And assert UpSell Products section and carousal at bottom of the page
	Then user selects a product from Related Products section and assert Add To Cart button
	And user click on search icon and submits the following search term 'searchRelatedProdTerm.key'
	Then user clicks on the first link if navigated to PLP page
	Then user selects a product from UpSell Products section and assert Add To Cart button

	@delive @de @VuseDEReg2
	Scenario: PDP URL - Assert Internal Link Redirects
		And user click on search icon and submits the following search term 'searchTermVype.key'
		And click first result
		And assert internal redirect of PDP links URLs with success status code

	@fr @VuseUKReg2
	Scenario: PDP - customer should be able to add more than 10 quantity
		And user click on search icon and submits the following search term 'searchTermVype.key'
		And click first result
		When modify quantity of the product to '15'
		And click add to cart button
		Then confirm mini-basket displayed amount of '15'

	#@MXReg commented because of Defect: 486331
	Scenario: Device specifications of the product
		And user click on search icon and submits the following search term 'epodstarterset.key'
		And assert specifications section is displayed
		And assert text of 'WhatsInTheBox.key' is displayed