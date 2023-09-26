Feature: Glo checkout feature - Register_checkout_deleteAccount
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And take eyes screenshot
			| SearchFlyoutPage                     |
			| NewsletterSubscriptionFromFooterPage |
			| DeviceTrialPage                      |
			| CookieNoticePage                     |
			| CookiePrivacyPolicyPage              |
			| BlogPage                             |
		When create a new account

	@UniversalSmokeGlo
	Scenario: Glo - Checkout tests - Register_checkout_deleteAccount
		And Customer makes a home delivery purchase with "mastercard"
		Then assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		When user navigates to my account page from header
		And eyes check sub pages on My Account page
		Then take eyes screenshot
			| SubscribeNewsLetterPage                |
			| MyAccountYourTobaccoHeaterPage         |
			| MyAccountOrderPage                     |
			| InsiderClubPage                        |
			| PasswordResetPage                      |
			| NewsLetterOverlayPage                  |
			| StoreLocatorPage                       |
			| ContactUsPage                          |
			| BlogPage                               |
			| SubscribeToInsiderClubPage             |
			| TobaccoHeaterPlpPage                   |
			| GloAccessoriesPlpPage                  |
			| SearchFlyoutPage                       |
			| SearchResultPage                       |
			| AccountHoverDropdown(LoggedInUser)Page |
		And user navigates to my account page from header
		And user selects deleteMyAccount link on my account Page
		#Then account is deleted successfully on my account Page

	@UniversalSmokeGloPL
	Scenario: Glo - Checkout tests - Register_checkout_deleteAccount - PL
		And user clicks on PersonIcon and Navigate to My Account Page
		And user add address details after registration - GloPL
		And user click on search icon and enter product 'ProductName.key' in the search bar
		And user select product add to basket and view basket
		And use add free gift module according to device
		And click on proceed to checkout button
		And Customer makes payu payment with "visa"
		Then assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		When user navigates to my account page from header
		And eyes check sub pages on My Account page
		Then take eyes screenshot
			| MyAccountOverviewWithOrderPage |
            | MyAccountAddNewAddressPage     |
			| MyDetailsPage                  |
			| PasswordResetPage              |
			| SubscribeNewsLetterPage        |
			| MainSubscriptionsPage          |
			| StoreLocatorPage               |
			| PlpStorePage                   |
			| PlpAccessoriesPage             |
			| ContactUsPage                  |
			| PlpTobaccoPage                 |
			| 404Page                        |
		And user navigates to my account page from header
		And user selects deleteMyAccount link on my account Page

	@UniversalSmokeGloKZ
	Scenario: GloKZ Checkout tests - Cash on Delivery
		And user closes the RDB pop-up banner if present
		And take eyes screenshot
			| PersonalPresentationPage |
			| Plp2HeatedGloSticksPage  |
			| AboutDevicesPage         |
			| AboutUsPage              |
			| StoreLocatorPage         |
			| ContactUsPage            |
			| SearchFlyoutPage         |
			| SearchResultPage         |
		And Customer makes a home delivery purchase with "COD"
		And Order Confirmation page thankYouMessageHeading is displayed
		And grab and output Order number
		When user navigates to my account page from header
		And eyes check sub pages on My Account page
		Then take eyes screenshot
			| MyDetailsPage           |
			| MyAccountOrderPage      |
			| SubscribeNewsLetterPage |
			| PasswordResetPage       |
		And user navigates to my account page from header
		And user selects deleteMyAccount link on my account Page
		#Then account is deleted successfully on my account Page