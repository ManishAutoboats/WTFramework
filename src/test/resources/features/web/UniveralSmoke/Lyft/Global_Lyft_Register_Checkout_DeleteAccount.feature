Feature: Universal smoke flow - lyft
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option

	@UniversalSmokeLyft
	Scenario: Lyft - Order Status tests - Saved Cards - Complete Flow
		And take eyes screenshot
			| StoreLocatorPage                              |
			| GuestUserLyftLabActiveCollectionAddToCartPage |
			| CookieSettingOverlayPage                      |
			| VeloPdpPage                                   |
			| LabCollaboratorPage                           |
			| LabCollection01Page                           |
			| LabCollection02Page                           |
			| LabCollection03Page                           |
			| LabCollection04Page                           |
			| LabCollection05Page                           |
			| LabProductsPage                               |
			| LyftLabHomePage                               |
			| LyftPdpPage                                   |
			| LabInsightsPage                               |
			| BlogPage                                      |
			| CookiePrivacyPolicyPage                       |
			| CookiePolicyPage                              |
		When create a new account
		And user navigates to PLP page and adds a product to basket
		And Customer makes a home delivery purchase with "mastercard"
		Then assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		When user navigates to my account page from header
		And eyes check sub pages on My Account page
		Then take eyes screenshot
			| MyDetailsPage              |
			| MyAccountSubscriptionPage  |
			| MyAccountAddNewAddressPage |
			| PasswordResetPage          |
			| SubscribeNewsLetterPage    |
			| AboutUsPage                |
			| ShopLyftPlpPage            |
			| Lyft2VeloMainMenuPage      |
			| MainSubscriptionsPage      |
			| SearchFlyoutPage           |
		And user navigates to my account page from header
		And user selects deleteMyAccount link on my account Page
		#Then account is deleted successfully on my account Page

