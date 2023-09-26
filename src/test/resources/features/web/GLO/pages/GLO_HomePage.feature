#This is the BAT Navigation feature

Feature: Glo HomePage

	Background: Navigate to Glo Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option

	@gloItLive @gloDeLive @gloDeSmoke @gloKzRegression @gloKzLive @gloItRegression @gloDeRegression @gloPlRegression @gloPlLive
	Scenario: Glo Home Page-Ensure all expected element are present
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And user closes the RDB pop-up banner if present
		And logo is displayed
		And GloIt PersonIcon is displayed
		And GloIt miniCart is displayed

	@gloItLive @gloDeLive @gloItRegression2 @gloDeRegression2 @gloPlRegression @gloPlLive
	Scenario: Confrim hompage and login page title
		When Glo user clicks on PersonIcon and Navigate to the Login Page
		Then assert glo login pageTitle is 'loginPageTitle.key'
		And  click on the logo
		Then assert glo pageTitle is 'homePageTitle.key'

	@gloPlRegression
	Scenario: Glo FAQ validation
		And users clicks on the FAQ link
		And assert content and expands the accordion

#	@gloDeRegression ToDo: Uncomment the tag once the BUG 338619 is fixed
	Scenario: Check Instagram social Media link and its Navigation page
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		When Glo users click on Instagram link
		Then assert cart pageTitle is 'homepageInstgramTitle.key'
		And Close the child window
		And users Navigate to parent window

	@gloDeRegression2
	Scenario: Duplicate Homepage URL
		And launches given URL and assert URL contains text and returns success

	@gloDeRegression2
	Scenario: Remove redundant meta tag keywords
		And users clicks on the url text link and verify url dost not contains meta tag keywords
			| blog                                                                                                                       |
			| contact                                                                                                                    |
			| store-locator/                                                                                                             |
			| firststeps                                                                                                                 |
			| imprint                                                                                                                    |
			| versand-und-zahlung                                                                                                        |
			| nutzungsbedingungen                                                                                                        |
			| about-glo-brand                                                                                                            |
			| policies                                                                                                                   |
			| index                                                                                                                      |
			| blog/post/wie-funktioniert-ein-tabakerhitzer-von-glo                                                                       |
			| widerrufsbelehrung                                                                                                         |
			| datenschutz                                                                                                                |
			| customer/account/forgotpassword/                                                                                           |
			| customer/account/create/                                                                                                   |
			| customer/account/login/referer/aHR0cHM6Ly93d3cuZGlzY292ZXJnbG8uY29tL2RlL2RlL2N1c3RvbWVyL2FjY291bnQvY3JlYXRlLw%2C%2C/       |
			| customer/account/login/referer/aHR0cHM6Ly93d3cuZGlzY292ZXJnbG8uY29tL2RlL2RlL2N1c3RvbWVyL2FjY291bnQvZm9yZ290cGFzc3dvcmQv/   |
			| customer/account/login/referer/aHR0cHM6Ly93d3cuZGlzY292ZXJnbG8uY29tL2RlL2RlL25ld3NsZXR0ZXIvbWFuYWdlL2luZGV4Lw%2C%2C/       |
			| customer/account/login/referer/aHR0cHM6Ly93d3cuZGlzY292ZXJnbG8uY29tL2RlL2RlL2N1c3RvbWVyL2FjY291bnQvaW5kZXgv/               |
			| checkout/cart/                                                                                                             |
			| blog/post/dein-glo-reinigen-in-5-schritten/                                                                                |
			| blog/post/rauchen-verboten-gilt-das-auch-fur-tabakerhitzer/                                                                |
			| blog/post/tabakerhitzer-von-glo-als-alternative-zur-zigarette/|


	#@gloPlRegression #Remove the test due to Bug 907849
	Scenario: Newsletter Subscription - Registered Users
		When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And click on newsletter button
		Then assert Newsletter pop up is displayed
		And enter the required details and signup for newsletter
		Then newsletter subscription is successful

	#@gloPlRegression #Remove the test due to Bug 907849
	Scenario: Newsletter Subscription - Guest Users
		And click on newsletter button
		Then assert Newsletter pop up is displayed
		And enter the required details and signup for newsletter guest user
		Then newsletter subscription is successful

	@gloPlRegression
	Scenario: Check Health Warning display
		Then Health warning banner and text 'healthWarningText.key' is display

	@gloItRegression2 @gloItLive
	Scenario: Guest User- New menu behaviour for discover glo header
		And user click on the Link and assert url
			| deviceAndAroma.key  | dscoverGloUrl.key     |
			| heatedTobacco.key   | alternativeSmoke.key  |

	@gloItRegression @gloItLive
	Scenario: Logged in user- New menu behaviour for discover glo header
		And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And user click on the Link and assert url
			| deviceAndAroma.key  | dscoverGloUrl.key     |
			| heatedTobacco.key   | alternativeSmoke.key  |

	@gloPlRegression @gloPlLive
	Scenario: assert Continue Shopping and Contact Us CTAs and navigation on 404 Not Found page
		And navigate to invalid or non-existing URL 'nonExistingURL.key'
		When user clicks on Continue Shopping button on 404 page
		And assert home page is displayed
		And navigate to invalid or non-existing URL 'nonExistingURL.key'
		When user clicks on Contact Us button on 404 page
		And assert URL contains text 'contactUsUrl.key'

	@gloDeRegression2
	Scenario: Redirection to home page from Straight to GLO CTA on 404 Not Found page
		And navigate to invalid or non-existing URL 'nonExistingURL.key'
		When user clicks on Straight to GLO button on 404 page
		Then assert home page is displayed

	@gloKzRegression
	Scenario: Redirection to home page from Straight to GLO CTA on 404 Not Found page for KZ
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And navigate to invalid or non-existing URL 'nonExistingURL.key'
		When user clicks on Straight to GLO button on 404 page
		Then assert home page is displayed

	@gloItRegression2
	Scenario: assert the CTA 'FIND OUT HOW TO COLLECT INSIDERS COIN' works
		Then Assert the CTA 'FIND OUT HOW TO COLLECT INSIDERS COIN' is clickable

    # Automate bug 880841
	@gloItRegression2
	Scenario: assert the subscribe CTA redirect correct URL
		When user clicks on INSIDERS CLUB link
		And click the CTA 'ISCRIVITI'
		Then assert URL contains text 'site.url'

	# Automate bug 894129
	@gloItRegression2
	Scenario: assert the url is relative path
		When user click on the 'PrivacyPolicy.key' link
		And the url on the 'Privacy Policy' page is relative path
			| contactText.key | contactUsUrl.key |

	@gloPlRegression @gloItRegression2
	Scenario: Blog page present and content is correct order
		When user navigates to the Blog
		Then url contains 'BlogUrlContain.key'
		And assert text of 'BlogTitle.key' is displayed
		Then blogs are listed in descending order
		Then user can view blogs and navigate to it

	#automate issue 917658
	@gloDeRegression2
	Scenario: User can redirect device tail page correctly
		When wait for the page to fully load
		And user navigate to device trail page
		Then url contains 'TryGloForFree.key' text
		And assert visit correct environment