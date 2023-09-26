#This is the PDP feature file
# @LyftRegression @LyftSmoke
Feature: BAT PDP feature

Background: Navigate to BAT Home page
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option

@LyftDkReg @LyftDKSmoke @LyftDKLive
Scenario: Guest - PDP elements present as expected
	And user click on search icon and submits the following search term 'searchTermdi.key'
	And get all lists from page
	And click first result
	Then assert PDP product title
	Then assert PDP product price
	#Then assert PDP product description

Scenario:  PDP Vype Strength Swatch
	And user click on search icon and submits the following search term 'searchTermdi.key'
	And assert product colour is displayed
	And assert product strength is displayed

#	@LyftRegression2 @LyftLive #Disabled as functionality of switching languages has been removed from live
	Scenario: PDP - Guest - assert navigation with change in language on switching language using language selector
		And user click on search icon and submits the following search term 'searchTermdi.key'
		And click first result
		And switch to 'EN' site using language selector
		And select allow all from cookie popup and select over 18 age confirmation option
		And assert navigation to home page with 'urlInEnglishOnLangSwitchFrom.key' and response status code as 200
		And assert navigation to home page with 'urlInEnglishOnLangSwitchTo.key' and response status code as 200
		Then assert text is displayed in English after switching language
		And switch to 'SV' site using language selector
		Then assert text is displayed in Swedish after switching language

#	@LyftRegression2 @LyftLive #Disabled as functionality of switching languages has been removed from live
	Scenario: PDP - Logged-In - assert navigation and user remains logged-in on switching language using language selector
		And user clicks the person icon -lyft
		And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
		And user click on search icon and submits the following search term 'searchTermdi.key'
		And click first result
		And switch to 'EN' site using language selector
		And select allow all from cookie popup and select over 18 age confirmation option
		And assert navigation to home page with 'urlInEnglishOnLangSwitchFrom.key' and response status code as 200
		And assert navigation to home page with 'urlInEnglishOnLangSwitchTo.key' and response status code as 200
		Then assert text is displayed in English after switching language
		And assert user remains logged-in after language switch
		And switch to 'SV' site using language selector
		Then assert text is displayed in Swedish after switching language
		And assert user remains logged-in after language switch

	@LyftRegression2
	Scenario: PDP product detail verification
		And user click on search icon and submits the following search term 'searchTermdi.key'
		And click on first product image and PDP page is open
		Then assert PDP product title
		Then assert PDP product price
		Then verify PDP product details
		Then assert Health Warning is displayed


	@LyftRegression2
	Scenario: PLP product image redirect to PDP
		And user click on search icon and submits the following search term 'searchTermdi.key'
		And click first result image
		Then assert PDP product title