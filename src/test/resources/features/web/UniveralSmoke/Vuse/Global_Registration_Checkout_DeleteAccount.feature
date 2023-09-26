Feature: BAT Vuse Universal Smoke Test
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @UniversalSmokeVuseZA
  Scenario: VUSE - Smoke Flow - Registration - Visa Payment - Delete Account
    And take eyes screenshot
      | EngraveAProductPage                 |
      | AboutVusePage                       |
      | 404Page                             |
      | BlogPage                            |
      | EmptyCheckoutCartPage               |
      | EmptyMiniBasketPage                 |
      | TypoInProductSearchPage             |
      | InvalidProductSearchPage            |
      | AccountHoverDropdown(GuestUser)Page |
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "Sid Secure EFT"
    #And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    #And assert text of 'yourOrderNumberText.key' is displayed
    And assert it navigate to success page
    And eyes check sub pages on My Account page
    Then take eyes screenshot
      | MyDetailsPage                          |
      | MyAccountEditAddressPopupPage          |
      | MyAccountOverviewPage                  |
      | MyAccountDeleteAccountPage             |
      | PasswordChangePage                     |
      | PasswordResetPage                      |
#     | MarketingPreferencePage | blocked by bug849152
      | StoreLocatorPage                       |
      | PromosHoverPage                        |
      | ShopDeviceHoverPage                    |
      | ShopDevicePlpPage                      |
      | ContactUsPage                          |
      | AboutVuseHoverPage                     |
      | SearchFlyoutPage                       |
      | AccountHoverDropdown(LoggedInUser)Page |
      | EpodStarterPackPdpPage                 |
      | NewsLetterOverlayPage                  |
 # And I delete customer account // Becuase of DPO issue and bug ID is 324016

  @UniversalSmokeVuse
  Scenario: Smoke Flow - Registration - Visa Payment - Delete Account
    And take eyes screenshot
      | AboutVuseHoverPage                  |
      | InsidersClubPage                    |
      | PdpDevicePage                       |
      | EmptyCheckoutCartPage               |
      | TypoInProductSearchPage             |
      | InvalidProductSearchPage            |
      | EmptyMiniBasketPage                 |
      | FlavorPlpSubscriptionViaIIconPage   |
      | EpodPlpPaymentDeferPage             |
      | EpodPlpPage                         |
      | VuseEpodPlpDeviceHoverPage          |
      | FlyoutMenuLiquidPage                |
      | FlyoutMenuAllAboutVapingPage        |
      | FlyoutMenuAProposPage               |
      | AllAboutVapingPage                  |
      | CustomizePage                       |
      | CookieSettingOverlayPage            |
      | CookieNoticePage                    |
      | TransparencyAboutVapingPage         |
      | ResponsibilityBeyondProductPage     |
      | UnpackingVapingForYouPage           |
      | ScienceFocusPage                    |
      | TalkingVapingWithYouPage            |
      | LiquidHoverPage                     |
      | SigaretteElectronicHoverPage        |
      | SigaretteElectronicPlpPage          |
      | NewsletterSubscriptionPopup         |
      | GuestCheckoutLoginPage              |
      | GuestCheckoutRegisterPage           |
      | VuseEpodPdpPage                     |
      | AbbonamentoPage                     |
      | AccountHoverDropdown(GuestUser)Page |
      | EpodStarterPackPdpPage              |
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And eyes check sub pages on My Account page
    Then take eyes screenshot
      | AddressBookPage                        |
      | MyAccountInsidersClubPage              |
      | MyAccountEditAddressPopupPage          |
      | MyAccountDeleteAccountPage             |
      | MyAccountAddNewAddressPage             |
      | MarketingPreferencePage                |
      | RegisterYourDevicePage                 |
      | MyDetailsPage                          |
      | PasswordChangePage                     |
      | PasswordResetPage                      |
      | MyAccountSubscriptionPage              |
      | MyAccountOrderPage                     |
      | SubscribeNewsLetterPage                |
      | NewsLetterOverlayPage                  |
      | MainSubscriptionsPage                  |
      | ContactUsPage                          |
      | SearchFlyoutPage                       |
      | MenuGuidedSellPage                     |
      | StoreLocatorPage                       |
      | ShopDevicePlpPage                      |
      | TopMenuFlyoutPage                      |
      | CigarettesPlpPage                      |
      | CigarettesHoverPage                    |
      | LiquidsHoverPage                       |
      | VuseReloadPage                         |
      | EquipmentPage                          |
      | KnowYourVapePage                       |
      | NicotineFreePage                       |
      | AboutVusePage                          |
      | AboutVuseFooterPage                    |
      | BlogPage                               |
      | 404Page                                |
      | VapersPage                             |
      | VapersHoverPage                        |
      | PodsHoverPage                          |
      | AccountHoverDropdown(LoggedInUser)Page |
    And I delete customer account
    