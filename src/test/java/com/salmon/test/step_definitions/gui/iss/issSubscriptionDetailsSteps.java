package com.salmon.test.step_definitions.gui.iss;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.BATHelper;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.admin.CustomerPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.iss.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class issSubscriptionDetailsSteps {
    private IssSubscriptionDetailsPage issSubscriptionDetailsPage;
    private IssBasketOverlayPage issBasketOverlayPage;
    private IssChooseYourFlavoursPage issChooseYourFlavoursPage;
    private ScenarioContext scenarioContext;
    private BATHelper batHelper;
    private CustomerPage customerPage;
    private IssHomePage issHomePage;
    private IssSendToCustomerOverlayPage issSendToCustomerOverlayPage;
    private HomePage homePage;
    private String scannedUrl;

    public issSubscriptionDetailsSteps(IssSubscriptionDetailsPage issSubscriptionDetailsPage,
                                       IssBasketOverlayPage issBasketOverlayPage,
                                       IssChooseYourFlavoursPage issChooseYourFlavoursPage,
                                       ScenarioContext scenarioContext,
                                       BATHelper batHelper,
                                       CustomerPage customerPage,
                                       IssHomePage issHomePage,
                                       IssSendToCustomerOverlayPage issSendToCustomerOverlayPage,
                                       HomePage homePage
                                       )
    {
        this.issSubscriptionDetailsPage = issSubscriptionDetailsPage;
        this.issBasketOverlayPage = issBasketOverlayPage;
        this.issChooseYourFlavoursPage = issChooseYourFlavoursPage;
        this.scenarioContext = scenarioContext;
        this.batHelper = batHelper;
        this.customerPage = customerPage;
        this.issHomePage = issHomePage;
        this.issSendToCustomerOverlayPage = issSendToCustomerOverlayPage;
        this.homePage = homePage;
    }

    @Then("^he moves to the next screen where he sees the subscription details page$")
    public void heMovesToTheNextScreenWhereHeSeesTheSubscriptionDetailsPage() {
      assertThat(issSubscriptionDetailsPage.isSubscriptionDetailsHeadingdisplayed())
              .as("ERROR: did not navigate to subscription details page").isTrue();
    }

    @And("^sees items with prices, subtotal, tier discount and subscription price$")
    public void seesItemsWithPricesSubtotalTierDiscountAndSubscriptionPrice() {
        assertThat(issSubscriptionDetailsPage.isPriceDisplayed())
                .as("ERROR: item price was not displayed for one or more items on subscription details page").isTrue();
        assertThat(issSubscriptionDetailsPage.isSubtotalDisplayed())
                .as("ERROR: Subtitle not displayed on subscription details page").isTrue();
        assertThat(issSubscriptionDetailsPage.isTierDiscountDisplayed())
                .as("ERROR: Tier discount not displayed on subscription details page").isTrue();
        assertThat(issSubscriptionDetailsPage.isSubscriptionPriceDisplayed())
                .as("ERROR: Price not displayed on subscription details page").isTrue();
    }

    @And("^CTA Amend Basket$")
    public void ctaAmendBasket() {
        assertThat(issSubscriptionDetailsPage.ctaAmendSubscriptionIsDisplayed())
                .as("ERROR: Amend subscription button not displayed on subscription details page").isTrue();
    }

    @And("^CTA  Send to Customer$")
    public void ctaSendToCustomer() {
        assertThat(issSubscriptionDetailsPage.ctaSendToCustomerIsDisplayed())
                .as("ERROR: Send to customner button not displayed on subscription details page").isTrue();
    }

    @And("^a separate section for setting up monthly payments$")
    public void aSeparateSectionForSettingUpMonthlyPayments() {
        assertThat(issSubscriptionDetailsPage.setupMonthlyPaymentsSectionIsDisplayed())
                .as("ERROR: Set up monthly payments section not displayed").isTrue();
    }

    @When("^he adds sufficient quantity to get \"([^\"]*)\" discount$")
    public void heAddsSufficientQuantityToGetDiscount(String requiredTier) throws InterruptedException {
        switch (requiredTier) {
            case "none":
                break;
            case "bronze":
                issBasketOverlayPage.increaseItemQtyTo(Integer.parseInt(UrlBuilder.getMessage("bronzeThreshold")));
                break;
            case "silver":
                issBasketOverlayPage.increaseItemQtyTo(Integer.parseInt(UrlBuilder.getMessage("silverThreshold")));
                break;
            case "gold":
                issBasketOverlayPage.increaseItemQtyTo(Integer.parseInt(UrlBuilder.getMessage("goldThreshold")));
                break;
            default:
                assertThat(true).as("ERROR invalid discount tier "+requiredTier+" supplied").isFalse();
        }
    }

    @When("^the colleague clicks on the Amend Subscription CTA$")
    public void theColleagueClicksOnTheAmendSubscriptionCTA() {
        issSubscriptionDetailsPage.clickAmendSubscriptionButton();
    }

    @Then("^he should be sent to the choose your flavours page$")
    public void heShouldBeSentToTheChooseYourFlavoursPage() {
        assertThat(issChooseYourFlavoursPage.amIOnTheChooseFlavourspage())
                .as("ERROR: did not return to choose flavours page").isTrue();
    }

    @Then("^the newly added item is in the basket$")
    public void theNewlyAddedItemIsInTheBasket() {
        assertThat(issBasketOverlayPage.areAllAddedItemsDisplayedInBasket())
                .as("ERROR: not all added items are in the basket - expected "+ scenarioContext.getContext(Context.BASKET_CONTENTS_MAP)).isTrue();
    }

    @And("^sees VAT$")
    public void seesVAT() {
        assertThat(issSubscriptionDetailsPage.isVatDisplayed())
                .as("ERROR: VAT not displayed on subscription details page").isTrue();
    }

    @And("^clicks on the send via email button$")
    public void clicksOnTheSendViaEmailButton() {
        issSubscriptionDetailsPage.clickSendViaEmail();
    }

    @And("^clicks the send email button$")
    public void clicksTheSendEmailButton() {
        issSubscriptionDetailsPage.clickSendEmail();
    }

    @And("^the user click on \"([^\"]*)\" link$")
    public void theUserClickOnLink(String linkText) throws Throwable {
        issSubscriptionDetailsPage.clickOnEmailLinktext(linkText, UrlBuilder.getMessage("issCustomer"));
    }

    @Then("^the user is on the login page for the main site$")
    public void theUserIsOnTheLoginPageForTheMainSite() {
        assertThat(issSubscriptionDetailsPage.userisOnLoginPage())
                .as("ERROR: user is not on login page").isTrue();
    }

    @And("^the user pays$")
    public void theUserPays() throws InterruptedException {
        batHelper.paysBy("mastercard");
    }

    @And("^the new user click on \"([^\"]*)\" link$")
    public void theNewUserClickOnLink(String linkText) throws Throwable {
        issSubscriptionDetailsPage.clickOnEmailLinktext(linkText, scenarioContext.getContext(Context.NEW_USER_EMAIL_ID).toString());
//        issSubscriptionDetailsPage.switchToTab();
    }

    @Then("^he clicks on the CTA finish subscription$")
    public void heClicksOnTheCTAFinishSubscription() {
        issSubscriptionDetailsPage.clickOnFinishSubscription();
    }

    @And("^a modal is displayed explaining the basket will be emptied$")
    public void aModalIsDisplayedExplainingTheBasketWillBeEmptied() {
        assertThat(issSubscriptionDetailsPage.isDoYouWantToLeaveModalDisplayed())
                .as("ERROR: are you sure you want to leave modal not displayed").isTrue();
    }

    @And("^the user clicks on YES$")
    public void theUserClicksOnYES() {
        issSubscriptionDetailsPage.clickOnAreYouSureYes();
    }

    @And ("^customer scans the QR code$")
    public void andCustomerScansTheQrCode() {
        scannedUrl = issSubscriptionDetailsPage.getSetUpMonthlyPaymentsQrCodeUrl();
    }

    @And ("^navigates to the scanned url$")
    public void navigatesToTheScannedUrl() {
        issSubscriptionDetailsPage.navigateToScannedUrl(scannedUrl);
    }

    @Then("^the staff member sees a success message$")
    public void theStaffMemberSeesASuccessMessage() {
        assertThat(issSubscriptionDetailsPage.paymentSuccessMessageIsDisplayed())
                .as("ERROR: success message not displayed for payment").isTrue();
    }

    @And("^the user pays using saved card$")
    public void theUserPaysUsingSavedCard() {
        issSubscriptionDetailsPage.payBySubscribePro();
    }

    @And("^create a new account following in store subscription$")
    public void createANewAccountFollowingInStoreSubscription() {
        batHelper.issRegistration();
    }

    @Then("^the customer shows the AV QR code to the colleague who scans it$")
    public void theCustomerShowsTheAVQRCodeToTheColleagueWhoScansIt() throws InterruptedException {
        issSubscriptionDetailsPage.scanAgeVerificationQrCode();
    }

    @And("^the AV page informs the user why age verification is required$")
    public void theAVPageInformsTheUserWhyAgeVerificationIsRequired() {
        String expectedAvText = UrlBuilder.getMessage("adultVerificationText");
        assertThat(issSubscriptionDetailsPage.avTextIsdisplayed(expectedAvText))
                .as("ERROR: adult verification pGE DID NOT CONTAIN "+expectedAvText).isTrue();
    }

    @And("^payment wording is correct$")
    public void paymentWordingIsCorrect() {
        assertThat(issSubscriptionDetailsPage.paymentWordingCorrect())
                .as("ERROR: payment wording incorrect").isTrue();
    }
}
