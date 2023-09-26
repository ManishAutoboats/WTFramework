package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.AccountDashboardPage;
import com.salmon.test.page_objects.gui.EyesPage;
import com.salmon.test.page_objects.gui.HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EyesSteps {
    private EyesPage eyesPage;
    private AccountDashboardPage accountDashboardPage;
    private HomePage homePage;

    public EyesSteps(EyesPage eyesPage, AccountDashboardPage accountDashboardPage, HomePage homePage) {
        this.eyesPage = eyesPage;
        this.accountDashboardPage = accountDashboardPage;
        this.homePage = homePage;
    }

    @When("^user clicks newsletter signup button on Contact Us page$")
    public void userClicksNewsletterSignupButtonOnContactUsPage() {
        eyesPage.clickNewsletterSignupButton();
    }

    @And("^eyes check entire page \"([^\"]*)\"$")
    public void eyesCheckEntirePage(String tag) {
        eyesPage.eyesCheckFullyPage(tag);
    }

    @Then("^eyes check region \"([^\"]*)\"$")
    public void eyesCheckRegion(String tag) {
        Map<String, By> map = new HashMap<>();
        map.put("Newsletter Signup Popup", EyesPage.NEWSLETTER_SIGNUP_BLOCK);
        eyesPage.eyesCheckRegion(tag, map.get(tag));
    }

    @Then("^eyes check scroll region \"([^\"]*)\"$")
    public void eyesCheckScrollRegion(String tag) {
        Map<String, By> map = new HashMap<>();
        map.put("Cookie Settings Popup", EyesPage.COOKIE_SETTINGS_POPUP);
        eyesPage.eyesCheckScrollRegion(tag, map.get(tag));
    }

    @When("^expand all FAQs$")
    public void expandAllFAQs() {
        eyesPage.expandAllFAQs();
    }

    @And("^user clicks send button on Contact Us page$")
    public void userClicksSendButtonOnContactUsPage() {
        eyesPage.clickSendButtonOnContactUsPage();
    }

    @When("^user input email as \"([^\"]*)\" on Contact Us page$")
    public void userInputEmailAsOnContactUsPage(String email) {
        eyesPage.inputEmailOnContactUsPage(UrlBuilder.getMessage(email));
    }

    @Then("^take eyes screenshot$")
    public void takeEyesScreenshot(List<String> pages) throws Throwable {
        if (!Props.EYES_ON) {
            return;
        }

        for (String page : pages) {
            switch (page) {
                case "EpodPlpPage":
                    homePage.goToEpodPlpPageAndTakeEyesScreenshot();
                    break;
                case "VuseEpodPlpDeviceHoverPage":
                    homePage.goToVuseEpodPlpDeviceHoverPageAndTakeEyesScreenshot();
                    break;
                case "PdpDevicePage":
                    homePage.goToPdpDevicePageAndTakeEyesScreenshot();
                    break;
                case "CookieSettingOverlayPage":
                    homePage.goToCookieSettingOverlayPageAndTakeEyesScreenshot();
                    break;
                case "CookieNoticePage":
                    homePage.goToCookieNoticePageAndTakeEyesScreenshot();
                    break;
                case "VapersPage":
                    homePage.goToVapersPageAndTakeEyesScreenshot();
                    break;
                case "VapersHoverPage":
                    homePage.goToVapersHoverPageAndTakeEyesScreenshot();
                    break;
                case "PodsHoverPage":
                    homePage.goToPodsHoverPageAndTakeEyesScreenshot();
                    break;
                case "CigarettesPlpPage":
                    homePage.goToCigarettesPlpPageAndTakeEyesScreenshot();
                    break;
                case "CigarettesHoverPage":
                    homePage.goToCigarettesHoverPageAndTakeEyesScreenshot();
                    break;
                case "LiquidsHoverPage":
                    homePage.goToLiquidsHoverPageAndTakeEyesScreenshot();
                    break;
                case "VuseReloadPage":
                    homePage.goToVuseReloadPageAndTakeEyesScreenshot();
                    break;
                case "EquipmentPage":
                    homePage.goToEquipmentPageAndTakeEyesScreenshot();
                    break;
                case "KnowYourVapePage":
                    homePage.goToKnowYourVapePageAndTakeEyesScreenshot();
                    break;
                case "NicotineFreePage":
                    homePage.goToNicotineFreePageAndTakeEyesScreenshot();
                    break;
                case "MyDetailsPage":
                    accountDashboardPage.goToMyDetailsPageAndTakeEyesScreenshot();
                    break;
                case "PasswordChangePage":
                    accountDashboardPage.goToPasswordChangePageAndTakeEyesScreenshot();
                    break;
                case "MarketingPreferencePage":
                    accountDashboardPage.goToMarketingPreferencePageAndTakeEyesScreenshot();
                    break;
                case "SubscribeNewsLetterPage":
                    accountDashboardPage.goToSubscribeNewsLetterPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountSubscriptionPage":
                    accountDashboardPage.goToMyAccountSubscriptionPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountOrderPage":
                    accountDashboardPage.goToMyAccountOrderPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountOverviewWithOrderPage":
                    accountDashboardPage.goToMyAccountOverviewWithOrderPageAndTakeEyesScreenshot();
                    break;
                case "PlpStorePage":
                    homePage.goToPlpStorePageAndTakeEyesScreenshot();
                    break;
                case "PasswordResetPage":
                    homePage.goToPasswordResetPageAndTakeEyesScreenshot();
                    break;
                case "NewsLetterOverlayPage":
                    homePage.goToNewsletterOverlayPageAndTakeEyesScreenshot();
                    break;
                case "MainSubscriptionsPage":
                    homePage.gotoMainSubscriptionsPageAndTakeEyesScreenshot();
                    break;
                case "AboutUsPage":
                    homePage.goToAboutUsPageAndTakeEyesScreenShot();
                    break;
                case "ShopLyftPlpPage":
                    homePage.goToShopLyftPlpPageAndTakeEyesScreenShot();
                    break;
                case "Lyft2VeloMainMenuPage":
                    homePage.goToLyft2VeloMainMenuAndTakeEyesScreenShot();
                    break;
                case "StoreLocatorPage":
                    homePage.goToStoreLocatorPageAndTakeEyesScreenShot();
                    break;
                case "PromosHoverPage":
                    homePage.goToPromosHoverPageAndTakeEyesScreenShot();
                    break;
                case "ShopDeviceHoverPage":
                    homePage.goToShopDeviceHoverPageAndTakeEyesScreenshot();
                    break;
                case "ShopDevicePlpPage":
                    homePage.goToShopDevicePlpPageAndTakeEyesScreenShot();
                    break;
                case "PlpAccessoriesPage":
                    homePage.goToPlpAccessoriesPageAndTakeEyesScreenShot();
                    break;
                case "TopMenuFlyoutPage":
                    homePage.goToTopMenuFlyoutPageAndTakeEyesScreenShot();
                    break;
                case "PlpTobaccoPage":
                    homePage.goToPlpTobaccoPageAndTakeEyesScreenshot();
                    break;
                case "ContactUsPage":
                    homePage.goToContactUsPageAndTakeEyesScreenshot();
                    break;
                case "MenuGuidedSellPage":
                    homePage.goToMenuGuidedSellPageAndTakeEyesScreenshot();
                    break;
                case "SearchFlyoutPage":
                    homePage.goToSearchFlyoutPageAndTakeEyesScreenShot();
                    break;
                case "SearchResultPage":
                    homePage.goToSearchResultPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountAddNewAddressPage":
                    homePage.goToMyAccountAddNewAddressPageAndTakeEyesScreenShot();
                    break;
                case "AboutVusePage":
                    homePage.goToAboutVusePageAndTakeEyesScreenshot();
                    break;
                case "AboutVuseHoverPage":
                    homePage.goToAboutVuseHoverPageAndTakeEyesScreenShot();
                    break;
                case "AboutVuseFooterPage":
                    homePage.goToAboutVuseFooterPageAndTakeEyesScreenshot();
                    break;
                case "AccountHoverDropdown(LoggedInUser)Page":
                    homePage.goToAccountHoverDropdownLoggedInUserPageAndTakeEyesScreenShot();
                    break;
                case "AccountHoverDropdown(GuestUser)Page":
                    homePage.goToAccountHoverDropdownGuestUserPageAndTakeEyesScreenShot();
                    break;
                case "EpodStarterPackPdpPage":
                    homePage.goToEpodStarterPackPdpPageAndTakeEyesScreenshot();
                    break;
                case "InsiderClubPage":
                    accountDashboardPage.goToInsiderClubPageAndTakeEyesScreenshot();
                    break;
                case "BlogPage":
                    homePage.goToBlogPageAndTakeEyesScreenShot();
                    break;
                case "404Page":
                    homePage.goTo404PageAndTakeEyesScreenshot();
                    break;
                case "SubscribeToInsiderClubPage":
                    homePage.goToSubscribeToInsiderClubPageAndTakeEyesScreenShot();
                    break;
                case "GuestCheckoutLoginPage":
                    homePage.goToGuestCheckoutLoginPageAndTakeEyesScreenshot();
                    break;
                case "GuestCheckoutRegisterPage":
                    homePage.goToGuestCheckoutRegisterPageAndTakeEyesScreenshot();
                    break;
                case "NewsletterSubscriptionPopup":
                    homePage.goToNewsletterSubscriptionPopupAndTakeEyesScreenshot();
                    break;
                case "MyAccountYourTobaccoHeaterPage":
                    accountDashboardPage.goToMyAccountYourTobaccoHeaterPageAndTakeEyesScreenshot();
                    break;
                case "TobaccoHeaterPlpPage":
                    homePage.goToTobaccoHeaterPlpPageAndTakeEyesScreenshot();
                    break;
                case "GloAccessoriesPlpPage":
                    homePage.goToGloAccessoriesPlpPageAndTakeEyesScreenshot();
                    break;
                case "DeviceTrialPage":
                    homePage.goToDeviceTrialPageAndTakeEyesScreenshot();
                    break;
                case "NewsletterSubscriptionFromFooterPage":
                    homePage.goToNewsletterSubscriptionFromFooterPageAndTakeEyesScreenshot();
                    break;
                case "LiquidHoverPage":
                    homePage.goToLiquidHoverPageAndTakeEyesScreenshot();
                    break;
                case "SigaretteElectronicHoverPage":
                    homePage.goToSigaretteElectronicHoverPageAndTakeEyesScreenshot();
                    break;
                case "SigaretteElectronicPlpPage":
                    homePage.goToSigaretteElectronicPlpPageAndTakeEyesScreenshot();
                    break;
                case "VuseEpodPdpPage":
                    homePage.goToVuseEpodPdpPagePageAndTakeEyesScreenshot();
                    break;
                case "AbbonamentoPage":
                    homePage.goToAbbonamentoPageAndTakeEyesScreenshot();
                    break;
                case "AddressBookPage":
                    accountDashboardPage.goToAddressBookPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountOverviewPage":
                    accountDashboardPage.goToMyAccountOverviewPageAndTakeEyesScreenshot();
                    break;
                case "RegisterYourDevicePage":
                    accountDashboardPage.goToRegisterDevicePageAndTakeEyesScreenshot();
                    break;
                case "PersonalPresentationPage":
                    homePage.goToPersonalPresentationPageAndTakeEyesScreenshot();
                    break;
                case "Plp2HeatedGloSticksPage":
                    homePage.goToPlp2HeatedGloSticksPageAndTakeEyesScreenshot();
                    break;
                case "AboutDevicesPage":
                    homePage.goToAboutDevicesPageAndTakeEyesScreenshot();
                    break;
                case "TransparencyAboutVapingPage":
                    homePage.goToTransparencyAboutVapingPageAndTakeEyesScreenshot();
                    break;
                case "ResponsibilityBeyondProductPage":
                    homePage.goToResponsibilityBeyondProductPageAndTakeEyesScreenshot();
                    break;
                case "UnpackingVapingForYouPage":
                    homePage.goToUnpackingVapingForYouPageAndTakeEyesScreenshot();
                    break;
                case "ScienceFocusPage":
                    homePage.goToScienceFocusPageAndTakeEyesScreenshot();
                    break;
                case "TalkingVapingWithYouPage":
                    homePage.goToTalkingVapingWithYouPageAndTakeEyesScreenshot();
                    break;
                case "CustomizePage":
                    homePage.goToCustomizePageAndTakeEyesScreenshot();
                    break;
                case "AllAboutVapingPage":
                    homePage.goToAllAboutVapingPageAndTakeEyesScreenshot();
                    break;
                case "FlyoutMenuAllAboutVapingPage":
                    homePage.goToFlyoutMenuAllAboutVapingPageAndTakeEyesScreenshot();
                    break;
                case "FlyoutMenuLiquidPage":
                    homePage.goToFlyoutMenuLiquidPageAndTakeEyesScreenshot();
                    break;
                case "FlyoutMenuAProposPage":
                    homePage.goToFlyoutMenuAProposPageAndTakeEyesScreenshot();
                    break;
                case "FlavorPlpSubscriptionViaIIconPage":
                    homePage.goToFlavorPlpSubscriptionViaIIconPageAndTakeEyesScreenshot();
                    break;
                case "CookiePrivacyPolicyPage":
                    homePage.goToCookiePrivacyPolicyPageAndTakeEyesScreenshot();
                    break;
                case "CookiePolicyPage":
                    homePage.goToCookiePolicyPageAndTakeEyesScreenshot();
                    break;
                case "EpodPlpPaymentDeferPage":
                    homePage.goToEpodPlpPaymentDeferPageAndTakeEyesScreenshot();
                    break;
                case "LabProductsPage":
                    homePage.goToLabProductsPageAndTakeEyesScreenshot();
                    break;
                case "LyftLabHomePage":
                    homePage.goToLyftLabHomePageAndTakeEyesScreenshot();
                    break;
                case "LyftPdpPage":
                    homePage.goToLyftPdpPageAndTakeEyesScreenshot();
                    break;
                case "LabInsightsPage":
                    homePage.goToLabInsightsPageAndTakeEyesScreenshot();
                    break;
                case "LabCollection01Page":
                    homePage.goToLabCollections01PageAndTakeEyesScreenshot();
                    break;
                case "LabCollection02Page":
                    homePage.goToLabCollections02PageAndTakeEyesScreenshot();
                    break;
                case "LabCollection03Page":
                    homePage.goToLabCollections03PageAndTakeEyesScreenshot();
                    break;
                case "LabCollection04Page":
                    homePage.goToLabCollections04PageAndTakeEyesScreenshot();
                    break;
                case "LabCollection05Page":
                    homePage.goToLabCollections05PageAndTakeEyesScreenshot();
                    break;
                case "GuestUserLyftLabActiveCollectionAddToCartPage":
                    homePage.goToGuestUserLyftLabActiveCollectionAddToCartPageAndTakeEyesScreenshot();
                    break;
                case "LabCollaboratorPage":
                    homePage.goToLabCollaboratorPageAndTakeEyesScreenshot();
                    break;
                case "VeloPdpPage":
                    homePage.goToVeloPdpPageAndTakeEyesScreenshot();
                    break;
                case "TypoInProductSearchPage":
                    homePage.goToTypoInProductSearchPageAndTakeEyesScreenshot();
                    break;
                case "InvalidProductSearchPage":
                    homePage.goToInvalidProductSearchPageAndTakeEyesScreenshot();
                    break;
                case "EmptyMiniBasketPage":
                    homePage.goToEmptyMiniBasketPageAndTakeEyesScreenshot();
                    break;
                case "EmptyCheckoutCartPage":
                    homePage.goToEmptyCheckoutCartPageAndTakeEyesScreenshot();
                    break;
                case "InsidersClubPage":
                    homePage.goToInsidersClubPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountInsidersClubPage":
                    accountDashboardPage.goToMyAccountInsidersClubPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountDeleteAccountPage":
                    accountDashboardPage.goToMyAccountDeleteAccountPageAndTakeEyesScreenshot();
                    break;
                case "MyAccountEditAddressPopupPage":
                    accountDashboardPage.goToMyAccountEditAddressPagePopupAndTakeEyesScreenshot();
                    break;
                case "EngraveAProductPage":
                    homePage.goToEngraveAProductPageAndTakeEyesScreenshot();
                    break;
                case "AboutVeloPage":
                    homePage.goToAboutVeloPageAndTakeEyesScreenshot();
                    break;
                case "ProductsPlpPage":
                    homePage.goToProductsPlpPageAndTakeEyesScreenshot();
                    break;
                case "NewsAndInformationPage":
                    homePage.goToNewsAndInformationPageAndTakeEyesScreenshot();
                    break;
                case "LanguageSelectorPage":
                    homePage.goToLanguageSelectorPageAndTakeEyesScreenshot();
                    break;
                case "RegistrationPage":
                    homePage.goToRegistrationPageAndTakeEyesScreenshot();
                    break;
                case "MeetVeloPage":
                    homePage.goToMeetVeloPageAndTakeEyesScreenshot();
                    break;
                case "BuyOnlinePage":
                    homePage.goToBuyOnlinePageAndTakeEyesScreenshot();
                    break;
                case "EsmokingWorldPage":
                    homePage.goToEsmokingWorldPageAndTakeEyesScreenshot();
                    break;
                default:
                    throw new IllegalArgumentException("Wrong page name in feature file: " + page);
            }
        }
    }
}