package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.salmon.test.framework.helpers.UrlBuilder.ENVIRONMENT;
import static com.salmon.test.framework.helpers.UrlBuilder.SITE;
import static com.salmon.test.framework.helpers.UrlBuilder.getEndPoints;
import static com.salmon.test.page_objects.gui.constants.Context.EMAIL_ID;
import static org.apache.xerces.impl.dtd.XMLDTDLoader.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class FreeDeviceTrialPageSteps {
  private ScenarioContext scenarioContext;
  private FreeDeviceTrialPage freeDeviceTrialPage;
  private RegistrationPage registrationPage;
  private PaymentPage paymentPage;
  private BATHelper batHelper;
  private HomePage homePage;
  private PDP pdp;
  private AccountDashboardPage accountDashboardPage;



  public FreeDeviceTrialPageSteps(TestContext testContext, FreeDeviceTrialPage freeDeviceTrialPage, RegistrationPage registrationPage, PaymentPage paymentPage, BATHelper batHelper, HomePage homePage, PDP pdp,AccountDashboardPage accountDashboardPage) {
    scenarioContext = testContext.getScenarioContext();
    this.freeDeviceTrialPage = freeDeviceTrialPage;
    this.registrationPage = registrationPage;
    this.paymentPage = paymentPage;
    this.batHelper = batHelper;
    this.homePage = homePage;
    this.pdp =pdp;
    this.accountDashboardPage = accountDashboardPage;
  }
  private int cartridgesAlreadyAdded = 0;

  @Given("^user navigates to Free Device Trial Landing page$")

  @And("^user to see Brief info on Trial product$")
  public void userToSeeBriefInfoOnTrialProduct() {
    freeDeviceTrialPage.deviceInfoSection();
  }

  @And("^User to see the steps for placing a device trail order$")
  public void userToSeeTheStepsForPlacingADeviceTrailOrder() {
    freeDeviceTrialPage.trialStatusBarSection();
  }

  @And("^user to see the status of the steps$")
  public void userToSeeTheStatusOfTheSteps() {
  }

  @And("^user to see common questions section with below titles$")
  public void userToSeeCommonQuestionsSectionWithBelowTitles() {
  }

  @And("^verify user is able to choose trial device$")
  public void verifyUserIsAbleToChooseTrialDevice() {
    freeDeviceTrialPage.selectDevice();
  }

  @And("^user is able to see the description of the chosen Device$")
  public void userIsAbleToSeeTheDescriptionOfTheChosenDevice() {
    freeDeviceTrialPage.checkDescription();
  }

  @And("^verify user is not allowed to 'choose your flavours' step without choosing device options$")
  public void verifyUserIsNotAllowedToChooseYourFlavoursStepWithoutChoosingDeviceOptions() {
    freeDeviceTrialPage.chooseFlavourBeforeDeviceColour();
  }

  @And("^verify user can choose colour options for the chosen trial device$")
  public void verifyUserCanChooseColourOptionsForTheChosenTrialDevice() {
    freeDeviceTrialPage.chooseColour();
  }

  @And("^verify the product image is loaded as expected$")
  public void verifyTheProductImageIsLoadedAsExpected() {
    freeDeviceTrialPage.verifyProductImageHasLoaded();
  }

  @And("^verify the actual price of the chosen device is shown in the device trail disclaimer info$")
  public void verifyTheActualPriceOfTheChosenDeviceIsShownInTheDeviceTrailDisclaimerInfo() {
    freeDeviceTrialPage.validateDisclaimerForPriceInclusion();
  }

  @And("^user can click on 'choose your flavours' button to move to next step$")
  public void userCanClickOnChooseYourFlavoursButtonToMoveToNextStep() {
    freeDeviceTrialPage.clickChooseFlavoursButton();
  }

  @And("^user selects a product on choose a Device Step$")
  public void userSelectsAProductOnChooseADeviceStep() {
    freeDeviceTrialPage.selectDevice();
    freeDeviceTrialPage.chooseColour();
  }

  @Then("^user to see strength info popup when clicked on 'Learn about strengths'$")
  public void userToSeeStrengthInfoPopupWhenClickedOnLearnAboutStrengths() {
    freeDeviceTrialPage.validateLearnAboutStrengths();
    freeDeviceTrialPage.closeAboutStrengthsModal();
  }

  @And("^user adds one Cartridge Trial$")
  public void userAddsOneCartridgeTrial() throws InterruptedException {
    freeDeviceTrialPage.addCartridges(1);
  }

  @And("^the 'Chosen Flavours' section displays correctly$")
  public void userToSeeChosenFlavoursSectionToBeUpdated() {
    freeDeviceTrialPage.validateChosenFlavours(cartridgesAlreadyAdded);
  }

  @Then("^user adds minimum Cartridge Trial allowed$")
  public void userAddsMinimumCartridgeTrialAllowed() throws InterruptedException {
    freeDeviceTrialPage.addMinimumFlavours(cartridgesAlreadyAdded);
    cartridgesAlreadyAdded = Integer.parseInt(UrlBuilder.getMessage("minimumFlavours.key"));
  }

  @And("^user to see 'Choose x flavours' indicator to be updated as 'CHECKOUT'$")
  public void userToSeeChooseXFlavoursIndicatorToBeUpdatedAsCHECKOUT() {
    freeDeviceTrialPage.validateCheckoutButton();
  }

  @And("^user can still add trial Cartridge till reaching the maximum number of flavours reached$")
  public void userCanStillAddTrialCartridgeTillReachingTheMaximumNumberOfFlavoursReached() throws InterruptedException {
    freeDeviceTrialPage.addMaximumFlavours(cartridgesAlreadyAdded);
    cartridgesAlreadyAdded = Integer.parseInt(UrlBuilder.getMessage("maximumFlavours.key"));
  }

  @And("^user to get error when more than allowed maximum number of flavours reached$")
  public void userToGetErrorWhenMoreThanAllowedMaximumNumberOfFlavoursReached() throws InterruptedException {
    freeDeviceTrialPage.addCartridges(1);
    freeDeviceTrialPage.validateMoreThanMaxError();
  }

  @And("^Choose at least the Minimum flavours on Select Your Flavours step$")
  public void chooseAtleastTheMinimumFlavoursOnSelectYourFlavoursStep() throws InterruptedException {
    freeDeviceTrialPage.clickChooseFlavoursButton();
    freeDeviceTrialPage.addMinimumFlavours(cartridgesAlreadyAdded);
    cartridgesAlreadyAdded = Integer.parseInt(UrlBuilder.getMessage("minimumFlavours.key"));
  }

  @Then("^click create new account from the hyperlink on the message$")
  public void clickCreateNewAccountFromTheHyperlinkOnTheMessage() {
  }

  @And("^upon confirmation of account creation email user to be redirected to Trial's checkout page$")
  public void uponConfirmationOfAccountCreationEmailUserToBeRedirectedToTrialSCheckoutPage() {
    paymentPage.paymentPageDetailsConfirmed();
  }

  @Then("^user places a trial order$")
  public void userPlacesATrialOrder() {
  }

  @Then("^assert if user is able to see the trial order placed under My orders section$")
  public void assertIfUserIsAbleToSeeTheTrialOrderPlacedUnderMyOrdersSection() {
  }

  @Then("^user to click on that recent trial order$")
  public void userToClickOnThatRecentTrialOrder() {
  }

  @And("^assert if recent trial order has expected trial start date and end date$")
  public void assertIfRecentTrialOrderHasExpectedTrialStartDateAndEndDate() {
    freeDeviceTrialPage.validateTrialDates();
  }

  @And("^assert if recent trial order has expected trial Duration$")
  public void assertIfRecentTrialOrderHasExpectedTrialDuration() {
    freeDeviceTrialPage.validateTrialDuration();
  }

  @And("^assert if recent trial order has expected trial price details$")
  public void assertIfRecentTrialOrderHasExpectedTrialItemDetails() {
    freeDeviceTrialPage.validateTotals();
  }

  @And("^assert if recent trial order has expected order information$")
  public void assertIfRecentTrialOrderHasExpectedOrderInformation() {
    freeDeviceTrialPage.validateOrderSummary();
  }

  @Given("^I navigate to BAT Homepage$")
  public void iNavigateToBATHomepage() {
  }

  @And("^user adds (\\d+) Cartridge Trial$")
  public void userAddsCartridgeTrial(int qty) throws InterruptedException {
    freeDeviceTrialPage.addCartridges(qty);
    cartridgesAlreadyAdded = cartridgesAlreadyAdded + qty;
  }

  @Then("^user to see \"([^\"]*)\" step \"([^\"]*)\" highlighted$")
  public void userToSeeStepHighlighted(String stepName, String highlighted) throws Throwable {
    if (stepName.toLowerCase().contains("flavour packs")) {
      String expectedminimumFlavours = UrlBuilder.getMessage("minimumFlavours.key");
      stepName = UrlBuilder.getMessage("trialStepTwoHeading.key").replace("%MINFLAVOUR%", expectedminimumFlavours);
    }
    else if (stepName.toLowerCase().contains("choose your device")){
      stepName = UrlBuilder.getMessage("trialStepOneHeading.key");
    }
    else {
      stepName = UrlBuilder.getMessage("trialStepThreeHeading.key");
    }
    boolean expectHighlighted = false;
    if (highlighted.toLowerCase().equals("is")) {
      expectHighlighted = true;
    }
    assertThat(freeDeviceTrialPage.stepIsHighlighted(stepName, expectHighlighted))
            .as("ERROR expected "+stepName+" step to be highlighted but it wasn't").isTrue();
  }

  @Then("^user to see the \"([^\"]*)\" page heading$")
  public void userToSeeThePageHeading(String stepName) {
    freeDeviceTrialPage.validateStepPageHeading(stepName);
  }

  @And("^user sees the correct more to go messages$")
  public void userSeesTheCorrectMoreToGoMessages() {
    freeDeviceTrialPage.validateMoreToGo(cartridgesAlreadyAdded);
  }

  @And("^user dismisses the error$")
  public void userDismissesTheError() {
    freeDeviceTrialPage.dismissError();
  }

  @Then("^user sees maximum flavours allowed message$")
  public void userSeesMaximumFlavoursAllowedMessage() {
    freeDeviceTrialPage.checkMaxFlavoursMessage();
  }

  @And("^user clicks the checkout button$")
  public void userClicksTheCheckoutButton() {
    freeDeviceTrialPage.clickCheckoutButton();
    }

  @And("^confirm that the user is notified they must be registered$")
  public void confirmThatTheUserIsNotifiedTheyMustBeRegistered() {
    freeDeviceTrialPage.assertThatRegisteredMessageIsDisplayed();
  }
  @And("^confirm the user is on the login page$")
  public void confirmTheUserIsOnTheLoginPage() {
    freeDeviceTrialPage.confirmUserOnLoginPage();
  }

  @And("^user selects the create an account button shown on the login page$")
  public void userSelectsTheCreateAnAccountButtonShownOnTheLoginPage() {
    freeDeviceTrialPage.confirmUserOnLoginPage();
    freeDeviceTrialPage.clickCreateNewAccount();
  }

  @And("^create a new account for Trial$")
  public void createANewAccountForTrial() {
    String emailAddress = registrationPage.emailAddressData;
    scenarioContext.setContext(EMAIL_ID,emailAddress );
    registrationPage.enterRegistrationDetailsAndCreateAccount(emailAddress);
  }

  @And("^Device Trial discount info is displayed on checkout page$")
  public void deviceTrialDiscountInfoIsDisplayedOnCheckoutPage() throws InterruptedException {
    paymentPage.assertDeviceTrialDiscountPresent();
  }

  @Then("^User places a trial order with \"([^\"]*)\"$")
  public void customerPlacesATrialOrderWith(String paymentType) throws Throwable {
    batHelper.paysTrialOrder(paymentType);
  }

  @And("^confirm that the user is notified about the limited Trial$")
  public void confirmThatTheUserIsNotifiedAboutTheLimitedTrial() {
      freeDeviceTrialPage.assertThatLimitedTrialMessageIsDisplayed();
  }

  @And("^confirm that the user is notified about the First time Trial only$")
  public void confirmThatTheUserIsNotifiedAboutTheFirstTimeTrialOnly() {
    freeDeviceTrialPage.assertThatFirstTimeOnlyTrialMessageIsDisplayed();

  }

  @Then("^user adds minimum Cartridge Trial allowed for the second time$")
  public void userAddsMinimumCartridgeTrialAllowedForTheSecondTime() throws InterruptedException {
    cartridgesAlreadyAdded = 0;
    freeDeviceTrialPage.addMinimumFlavours(cartridgesAlreadyAdded);
    cartridgesAlreadyAdded = Integer.parseInt(UrlBuilder.getMessage("minimumFlavours.key"));
  }

  @And("^user clicks on latest order details button$")
  public void userClicksOnLatestOrderDetailsButton() {
    freeDeviceTrialPage.viewLatestOrder();
    }

  @And("^all sections of the order details are correct$")
  public void allSectionsOfTheOrderDetailsAreCorrect() throws ParseException {
    Date placedDate = new Date();
    cartridgesAlreadyAdded=2;
    freeDeviceTrialPage.validateOrderDetails(cartridgesAlreadyAdded);
  }

  @And("^customer pays by \"([^\"]*)\"$")
  public void customerPaysBy(String cardType) throws InterruptedException {
    batHelper.paysBy(cardType);
  }

  @And("^User agrees the Trial payment agreement$")
  public void userAgreesTheTrialPaymentAgreement() {
    paymentPage.waitForAjaxElementNotToBePresent(paymentPage.getWebDriver(), 20);
    paymentPage.selectDeviceConsentTrialCheckBox();
  }

  @And("^user to see payment card \"([^\"]*)\" is saved for Future use and orders$")
  public void userToSeeInformationThatPaymentCardIsSavedForFutureUse(String paymentType) throws InterruptedException {
    batHelper.paysTrialOrder(paymentType);
    paymentPage.assertSavedcardcheckboxselectedTrial();
  }

  @And("Device Trial Consent Checkbox is displayed$")
  public void assertDeviceTrialConsentCheckbox() {
     assertTrue(paymentPage.isDeviceTrialConsentTextBoxPresent());
  }

  @And("^Device Trail payment method details present$")
  public void deviceTrailPaymentMethodDetailsPresent() {
    freeDeviceTrialPage.verifyTrialPaymentMethodDetails();
  }

  @And("^confirm that the user is notified about the Two overlapping Trial not possible$")
  public void confirmThatTheUserIsNotifiedAboutTheTwoOverlappingTrialNotPossible() {
    freeDeviceTrialPage.assertThatTwoTrialNotPossibleMessageIsDisplayed();
  }

  @Then("^confirm that the user is notified about the Mixed basket of Trial & Non Trial checkout not possible$")
  public void confirmThatTheUserIsNotifiedAboutTheMixedBasketOfTrialNonTrialCheckoutNotPossible() {
    freeDeviceTrialPage.assertThatMixedBasketErrorMessageIsDisplayed();
  }

  @And("^confirm that the user is notified about the One Trial per transaction$")
  public void confirmThatTheUserIsNotifiedAboutTheOneTrialPerTransaction() {
    freeDeviceTrialPage.assertThatLimitedTrialMessageIsDisplayed();
  }

  @And("^User to see No debit card payment possible$")
  public void userToSeeNoDebitCardPaymentPossible() {
    paymentPage.isDeviceTrialDebitCardNoInfoPresent();
  }

  @And("^user to see Credit card payment only possible$")
  public void userToSeeCreditCardPaymentOnlyPossible() {
    paymentPage.isDeviceTrialCreditCardOnlyInfoPresent();
  }

  @And("^user to see info about Trial delivery, Payment and related details$")
  public void userToSeeInfoAboutTrialDeliveryPaymentAndRelatedDetails() {
    freeDeviceTrialPage.trialDisclaimerDetails();
  }

  @And("^empty Trial product from basket$")
  public void emptyTrialProductFromBasket() {
    homePage.clickBasketIcon();
    homePage.deleteTrialProductFromRemoveButton();
  }

  @And("^user navigates to PDP and adds a non Trial product to basket$")
  public void userNavigatesToPDPAndAddsANonTrialProductToBasket() {
    pdp.performSearch("charging case");
  }

  @And("^user adds a non Trial product into basket$")
  public void userAddsANonTrialProductIntoBasket() throws InterruptedException {
    batHelper.searchAddAProductToBasket("searchProductItem.key");
  }

  @And("^user re login$")
  public void userReLogin() {
    freeDeviceTrialPage.freeDeviceTrialLogin();
  }

  @And("^User to see card for use after the trial section is present$")
  public void userToSeeCardForUseAfterTheTrialSectionIsPresent() {
    paymentPage.isCardForFutureUseInfoPresent();
  }

  @Then("^assert saved card is not present after trial order$")
  public void assertSavedCardIsNotPresentAfterTrialOrder() {
    accountDashboardPage.isSavedCardPresent();
  }

  @Then("^assert Banner text in Choose Your device page header$")
  public void assertBannerTextInChooseYourDevicePageHeader() {
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.CHOOSE_YOUR_DEVICE_BANNER).getText().contains("Try Vuse before you buy."));
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.CHOOSE_YOUR_DEVICE_BANNER).getText().contains("When your 14-days trial is over, you decide: keep your Vuse or return it at no charge."));
  }

  @Then("^assert '(.*)' section title is displayed$")
  public void assertTitleChooseYourDeviceIsDisplayed(String strExpectedText) {
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.CHOOSE_YOUR_DEVICE_SECTION_TITLE).getText().contains(strExpectedText));
  }

  @Then("^user clicks on '(.*)' link from (?:Information|Footer) section and assert navigation to '(.*)'$")
  public void userClicksOnLinkFromInformationSectionAndAssertNavigationTo(String strExpectedLink,String strExpectedURL) {
    freeDeviceTrialPage.clickOnLinkFromInformationSectionAndAssertNavigation(strExpectedLink,strExpectedURL);
  }

  @Then("^assert Device Trial T&C link on the sidebar menu$")
  public void assertDeviceTrialTClinkOnTheSidebarMenu() {
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.DEVICE_TRIAL_TCs_SIDEBAR).getText().contains("DEVICE TRIAL TERMS AND CONDITIONS"));
  }

  @Then("^assert Device Trial T&C link '(.*)' in Footer section$")
  public void assertDeviceTrialTClinkInFooterSection(String strExpectedLink) {
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.DEVICE_TRIAL_TCs_LINK_FOOTER).getText().contains(strExpectedLink));
  }

  @Then("^user clicks on Device Trial link from ePod sub-menu$")
  public void userClicksOnLinkFromEPodSubMenu() {
    freeDeviceTrialPage.clickUsingJS(freeDeviceTrialPage.getWebDriver().findElements(freeDeviceTrialPage.DEVICE_TRIAL_LINK_SHOP_MENU).get(0));
  }

  @Then("^user clicks on Device Trial link from ePen sub-menu$")
  public void userClicksOnLinkFromEPenSubMenu() {
    freeDeviceTrialPage.clickUsingJS(freeDeviceTrialPage.getWebDriver().findElements(freeDeviceTrialPage.DEVICE_TRIAL_LINK_SHOP_MENU).get(1));
  }

  @And("^assert Read more is displayed$")
  public void assertReadMoreIsDisplay() {
    assertTrue(freeDeviceTrialPage.isElementDisplayedBySeconds(freeDeviceTrialPage.READ_MORE_LINK, 4));
    freeDeviceTrialPage.clickUsingJS(freeDeviceTrialPage.READ_MORE_LINK);
    assertTrue(freeDeviceTrialPage.isElementDisplayedBySeconds(freeDeviceTrialPage.READ_MORE_DESC, 5));
  }

  @Then("^user selects '(.*)' option from FDT pop-up for a user with first order$")
  public void userSelectsOptionFromFDTPopUpForUserWithFirstOrder(String strOption) {
    if(strOption.equalsIgnoreCase("No"))
      try {
        freeDeviceTrialPage.waitAndClickByElementByJSExecutor(freeDeviceTrialPage.DEVICE_TRIAL_FIRST_ORDER_NO_OPTION, 10);
      } catch (Exception e) {
        freeDeviceTrialPage.waitAndClickByElementByJSExecutor(freeDeviceTrialPage.DEVICE_TRIAL_FIRST_ORDER_NO_OPTION, 10);
      }
    else if(strOption.equalsIgnoreCase("Yes"))
      freeDeviceTrialPage.waitAndClickByElementByJSExecutor(freeDeviceTrialPage.DEVICE_TRIAL_FIRST_ORDER_YES_OPTION,10);
  }

  @Then("^assert device trial confirmation pop-up is displayed with two CTAs for guest user$")
  public void assertDeviceTrialConfirmationPopUpIsDisplayedWithTwoCTAsForGuestUser() {
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.DEVICE_TRIAL_CONFIRMATION_POPUP,10).isDisplayed());
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.DEVICE_TRIAL_FIRST_ORDER_NO_OPTION).isDisplayed());
    assertTrue(freeDeviceTrialPage.waitForExpectedElement(freeDeviceTrialPage.DEVICE_TRIAL_FIRST_ORDER_YES_OPTION).isDisplayed());
  }
}
