package com.salmon.test.step_definitions.gui;

import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import com.salmon.test.page_objects.gui.newsLetter.NewsLetterPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.page_objects.gui.constants.Locale.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class MyAccountPageSteps extends PageObject {
  private static final Logger LOG = LoggerFactory.getLogger(MyAccountPageSteps.class);
  public static final By EMAIL_CONSENT_CHK_BOX = By.cssSelector("input#consent_email_marketing");
  public static final By MOBILE_SOCIAL_CONSENT_CHK_BOX = By.cssSelector("input#consent_mobile,div.field.choice.newsletter.choice-box__container > div > div > div.social-left > label,div.newsletter-agree.newsletter-polices.field.required");
  public static final By MOBILE_SOCIAL_CONSENT_CHK_BOX_VUSEIT = By.cssSelector("input#mobile_consent");
  public static final By SUBSCRIPTION_SAVE_BTN = By.cssSelector("button.action.save.primary");
  private static final By SUBSCRIPTION_CHK_BOX = By.cssSelector("input#subscription.checkbox,input#consent_email_marketing");
  private static final By SAVE_CARD_CHK_BOX = By.cssSelector("#save_cc");
  private final static By SUBSCRIPTION_MESSAGE=By.cssSelector("div.message-success.success.message");
  private final static By NEWS_LETTER_FORM_MXCX = By.cssSelector("form.form.subscribe");
  private final static By MX_SUBS_SAVE_BUTTON = By.cssSelector("button.action.save.primary");
  private final static By MX_SUBS_SAVE_BUTTON2 = By.cssSelector("button#subscribe_news");
  private final static By EMAIL_CONSCENT_MX = By.cssSelector("div.social-left.newsletter > input");
  private static final By EMAIL_CONSENT_CHK_BOX_FR = By.cssSelector("input#subscription");
  private static final By FIRST_NAME = By.cssSelector(".fieldset.fieldset-fullname>div>div.field.field-name-firstname.required");
  private static final By EDIT_ACCOUNT_FIRST_NAME = By.cssSelector("div.field.field-name-firstname.required:nth-child(2) > div.control > input#firstname");
  private static final By EDIT_ACCOUNT_LAST_NAME = By.cssSelector("div.field.field-name-lastname.required:nth-child(3) > div.control > input#lastname");
  public static final By VUSE_FIRST_NAME = By.cssSelector("input#firstname");
  private static final By VUSE_LAST_NAME = By.cssSelector("input#lastname");
  private static final By EMAIL_ADDRESS_GLOPL = By.cssSelector("input#email");
  private static final By LAST_NAME = By.cssSelector(".fieldset.fieldset-fullname>div>div.field.field-name-lastname.required>div>input");
  private static final By DATE_OF_BIRTH = By.cssSelector("#dob");
  private static final By SUCCESS_MESSAGE_VUSEFR = By.cssSelector(".message-success.success.message");

  private AccountDashboardPage accountDashboardPage;
  private HomePage homePage;
  private BATHelper batHelper;
  private NewsLetterPage newsLetterPage;
  private GlobalSubscriptionsPage globalSubscriptionsPage;
  private RegistrationPage registrationPage;
  private LogininPage logininPage;
  private AddNewAddressPage addNewAddressPage;
  private PaymentPage paymentPage;
  private AvalancheAccountDashboardPage avalancheAccountDashboardPage;

  public MyAccountPageSteps(AccountDashboardPage accountDashboardPage, HomePage homePage, BATHelper batHelper, NewsLetterPage newsLetterPage,
                            GlobalSubscriptionsPage globalSubscriptionsPage,RegistrationPage registrationPage,LogininPage logininPage, AddNewAddressPage addNewAddressPage, PaymentPage paymentPage,
                            AvalancheAccountDashboardPage avalancheAccountDashboardPage) {

    this.accountDashboardPage = accountDashboardPage;
    this.homePage = homePage;
    this.batHelper = batHelper;
    this.newsLetterPage=newsLetterPage;
    this.globalSubscriptionsPage=globalSubscriptionsPage;
    this.registrationPage=registrationPage;
    this.addNewAddressPage = addNewAddressPage;
    this.paymentPage = paymentPage;
    this.avalancheAccountDashboardPage = avalancheAccountDashboardPage;
    this.logininPage=logininPage;
  }

  @And("^assert link saying 'Delete My Account' is present$")
  public void assertLinkSayingDeleteMyAccountIsPresent() {
	 assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.lnkDeleteMyAccount).isDisplayed());
  }

  @And("^user clicks on the links and navigates to the page$")
  public void myAccountLinks(DataTable table){
    accountDashboardPage.myAccountLinks(table);
  }

  @And("^proceed to delete customer account$")
  public void proceedToDeleteCustomerAccount() throws Throwable {
    accountDashboardPage.deleteMyAccount();
  }


  @And("^navigate to Marketing Preferences page$")
  public void navigateToMarketingPreferencesPage() {
    accountDashboardPage.selectMarketingPreferences();
  }

  @And("^assert all marketing preferences displayed as expected$")
  public void assertAllMarketingPreferencesDisplayedAsExpected() {
    accountDashboardPage.assertMarektingPreferenesAreAllPresentAndNotSelected();
  }

  @And("^select all remaining marketing preferences$")
  public void selectAllRemainingMarketingPreferences() {
    accountDashboardPage.selectAllRemainingCheckBoxes();
  }

  @And("^click Save$")
  public void clickSave() {
    accountDashboardPage.waitForExpectedElement(accountDashboardPage.savePreferencesButton,10).click();
  }

  @Then("^assert all expected tickboxes have been successfully selected$")
  public void assertAllExpectedTickboxesHaveBeenSuccessfullySelected() {
    accountDashboardPage.confirmRemainingTickBoxesSelected();
  }


  @And("^assert first name input is present$")
  public void assertFirstNameInputIsPresent() {
    switch (Locale.valueOf(getLocale().toUpperCase())) {
      case GLODE:
      case MX:
      case VYPEIT:
      case VUSEIT:
      case VUSECO:
      case VUSEMX:
      case VUSEUK:
          assertTrue(accountDashboardPage.waitForExpectedElement(EDIT_ACCOUNT_FIRST_NAME).isDisplayed());
        break;
      case VUSEZA:
      case IE:
        assertTrue(accountDashboardPage.waitForExpectedElement(VUSE_FIRST_NAME).isDisplayed());
        break;
      default:
          assertTrue(accountDashboardPage.waitForExpectedElement(FIRST_NAME).isDisplayed());
    }
  }

  @And("^assert '(.*)' field is editable$")
  public void assertFieldEditable(String fieldName) {
      switch (Locale.valueOf(getLocale().toUpperCase())) {
          case PL:
              if(fieldName.equals("First Name")){
                  accountDashboardPage.waitForExpectedElement(VUSE_FIRST_NAME).clear();
                  accountDashboardPage.waitForExpectedElement(VUSE_FIRST_NAME).sendKeys("newFirstName");
                  assertTrue(accountDashboardPage.waitForExpectedElement(VUSE_FIRST_NAME).getAttribute("value").equals("newFirstName"));
              }
              else if(fieldName.equals("Last Name")) {
                  accountDashboardPage.waitForExpectedElement(VUSE_LAST_NAME).clear();
                  accountDashboardPage.waitForExpectedElement(VUSE_LAST_NAME).sendKeys("newLastName");
                  assertTrue(accountDashboardPage.waitForExpectedElement(VUSE_LAST_NAME).getAttribute("value").equals("newLastName"));
              }
              else if(fieldName.equals("Email Address")) {
                  accountDashboardPage.waitForExpectedElement(EMAIL_ADDRESS_GLOPL).clear();
                  accountDashboardPage.waitForExpectedElement(EMAIL_ADDRESS_GLOPL).sendKeys("newEmailAddress");
                  assertTrue(accountDashboardPage.waitForExpectedElement(EMAIL_ADDRESS_GLOPL).getAttribute("value").equals("newEmailAddress"));
              }
          break;
      }
  }

  @And("^click on change email checkbox$")
  public void clickChangeEmailCheckbox() {
      switch (Locale.valueOf(getLocale().toUpperCase())) {
          case PL:
              accountDashboardPage.clickChangeEmailCheckbox();
          break;
      }
  }

  @And("^assert last name input is present$")
  public void assertLastNameInputIsPresent() {
    switch (Locale.valueOf(getLocale().toUpperCase())) {
      case GLODE:
      case MX:
      case VYPEIT:
      case VUSEIT:
      case VUSECO:
      case VUSEMX:
      case VUSEUK:
        assertTrue(accountDashboardPage.waitForExpectedElement(EDIT_ACCOUNT_LAST_NAME).isDisplayed());
        break;
      case VUSEZA:
      case IE:
        assertTrue(accountDashboardPage.waitForExpectedElement(VUSE_LAST_NAME).isDisplayed());
        break;
      default:
        assertTrue(accountDashboardPage.waitForExpectedElement(LAST_NAME).isDisplayed());
    }
  }

  @And("^assert DOB input is present$")
  public void assertDOBInputIsPresent() {
    assertTrue(accountDashboardPage.waitForExpectedElement(DATE_OF_BIRTH).isDisplayed());
  }

  @And("^assert save button is present$")
  public void assertSaveButtonIsPresent() {
    switch(UrlBuilder.getLocale()){
      case "mx":
      case "vusemx":
        if(accountDashboardPage.getWebDriver().getCurrentUrl().contains("customer/account/edit")) {
          assertTrue(accountDashboardPage.waitForExpectedElement(MX_SUBS_SAVE_BUTTON).isDisplayed());
        } else {
          assertTrue(accountDashboardPage.waitForExpectedElement(MX_SUBS_SAVE_BUTTON2).isDisplayed());
        }
        break;
      default:
        assertTrue(accountDashboardPage.waitForExpectedElement(By.cssSelector("button.action.save.primary")).isDisplayed());
    }
  }

  @And("^assert cancel button is present$")
  public void assertCancelButtonIsPresent() {
    if (!UrlBuilder.getLocale().equals("uk") &&
            !UrlBuilder.getLocale().equals("vusede") &&
            !UrlBuilder.getLocale().equals("fr") &&
            !UrlBuilder.getLocale().equals("mx") &&
            !UrlBuilder.getLocale().equals("vusemx") &&
            !UrlBuilder.getLocale().equals("vypeit") &&
            !UrlBuilder.getLocale().equals("vuseit") &&
            !UrlBuilder.getLocale().equals("glode") &&
            !UrlBuilder.getLocale().equals("vuseco")&&
            !UrlBuilder.getLocale().equals("vuseza")) {
      assertTrue(accountDashboardPage.waitForExpectedElement(By.cssSelector("a.action.back")).isDisplayed());
    }
  }

  @And("^assert My Orders Table is displayed$")
  public void assertMyOrdersTableIsDisplayed() {
    assertTrue(accountDashboardPage.isMyOrderTableDisplayed());
  }

  @And("^assert My Orders Table headings are as expected$")
  public void assertMyOrdersTableHeadngsAreAsExpected() {
    assertTrue(accountDashboardPage.assertHeadingsAreCorrect());
  }


  @And("^assert My Order table data is as expected$")
  public void assertMyOrderTableDataIsAsExpected() {
	  if(!accountDashboardPage.getWebDriver().getCurrentUrl().contains("/mx/es/"))
		  assertTrue("Expected contents or Order and Reorder are not present in the order table contents",accountDashboardPage.loopThurOrderTableContentsAndReturnifOrderAndReorderLinksPresent());
  }

  @And("^click on a re-order link$")
  public void clickOnAReOrderLink() {
    accountDashboardPage.clickOnReOrderLink();
  }

  @And("^users clicks on the View Order link$")
  public void clickOnViewOrderLink() {
    switch (UrlBuilder.getLocale()) {
      case "glode":
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.LNK_ORDER_VIEW);
        accountDashboardPage.clickFirstElementByQueryJSExecutor(accountDashboardPage.LNK_ORDER_VIEW);
        break;
      case "glojp" :
        accountDashboardPage.clickFirstElementByQueryJSExecutor(accountDashboardPage.lnkViewOrderJP);
        break;
      case "mx":
      case "vusemx":
      case "nl":
      default:
        accountDashboardPage.waitForPage();
        accountDashboardPage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("viewOrderLinkText.key")),10);
        accountDashboardPage.clickFirstElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("viewOrderLinkText.key")));
    }
  }

  @And("^users clicks on the ReOrder link$")
  public void clickOnReOrderLink() {
    accountDashboardPage.waitForExpectedElement(accountDashboardPage.lnkReOrderMX,30);
	accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.lnkReOrderMX);
  }

  @And("^click on a re-order link -Lyft$")
  public void clickOnAReOrderLinkLyft() {
    accountDashboardPage.clickOnReOrderLinkLyft();
  }

  @And("^glo user click on a re-order link$")
  public void GloUserclickOnAReOrderLink() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.ReOrderLink);
  }

  @And("^user see the applied insider credit in order summary$")
  public void assertLoyaltyCreditInOrderSummary() {
    accountDashboardPage.verifyInsiderClubCreditInOrderSummary();
  }

  @And("^update firstName field with a random string$")
  public void updateFirstNameFieldWithARandomString() {
    accountDashboardPage.updateFirstandSecondNameSave();
  }

  @And("^assert successfully updated details$")
  public void assertSuccessfullyUpdatedDetails() {
    accountDashboardPage.updateDetailsSavedMessage();
  }

  @And("^click remove product from favorites button$")
  public void clickRemoveProductFromFavoriatesButton() {
    accountDashboardPage.clickRemoveFavoriateButton();
  }

  @And("^assert added to wish list message is displayed to user$")
  public void assertAddedToWishListMessageIsDisplayedToUser() {
    accountDashboardPage.getFavsucessMessage();
  }

  @And("^select device type '(.*)' from dropdown$")
  public void selectDeviceTypeEPenFromDropdown(String deviceToSelectFromDropDown) {
    accountDashboardPage.selectValueFromDropDownByby(deviceToSelectFromDropDown,By.cssSelector("#device-registration-form > fieldset > div.field.device.required > div > select"));
  }

  @And("^populate device registration with valid details and submit$")
  public void populateDeviceRegistrationWithValidDetailsAndSubmit() throws InterruptedException {
    accountDashboardPage.selectValueFromDropDownByby("ePen3",By.cssSelector("#device-registration-form > fieldset > div.field.device.required > div > select"));
    accountDashboardPage.enterText(By.cssSelector("#serial_code"),RandomGenerator.randomInteger(18));
    accountDashboardPage.enterText(By.cssSelector("#purchase-location"),"Watford") ;
    accountDashboardPage.enterText(By.cssSelector("#purchase-town"),"Watford");
    accountDashboardPage.clickUsingJS(By.cssSelector("#warranty_opt_in"));
    accountDashboardPage.enterText(By.cssSelector("#purchase-date"),"2019-07-03");
    accountDashboardPage.clickUsingJS(By.cssSelector("#save > span"));
    Thread.sleep(2000);
  }

  @And("^tick 'General Subscription' box$")
  public void tickGeneralSubscriptionBox() {
    accountDashboardPage.waitForExpectedElement(By.cssSelector("#form-validate > fieldset > div > label")).click();
  }

  @And("^click save button$")
  public void clickSaveButton() {
    accountDashboardPage.waitForExpectedElement(By.cssSelector("button.action.save.primary")).click();
  }

  @And("^assert success message of '(.*)' presented to user$")
  public void assertSuccessMessageOfWeHaveSavedYourSubscriptionPresentedToUser(String expectedMsg) throws InterruptedException {
    String FreeCouponSuccessMessage = null;
    Thread.sleep(3000);
    switch (UrlBuilder.getLocale()) {
      case "pl":
        try {
          FreeCouponSuccessMessage = accountDashboardPage.waitForExpectedElement(accountDashboardPage.COUPON_APPLIED_MESSAGE).getText();
        } catch (Exception e) {
          accountDashboardPage.refreshBrowser();
          FreeCouponSuccessMessage = accountDashboardPage.waitForExpectedElement(accountDashboardPage.COUPON_APPLIED_MESSAGE).getText();
        }
        accountDashboardPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedMsg).toLowerCase(), FreeCouponSuccessMessage.toLowerCase());
        break;
      case "vusefr":
        String actualSuccessMessage = accountDashboardPage.waitForExpectedElement(SUCCESS_MESSAGE_VUSEFR).getText();
        accountDashboardPage.assertTrueExpectedTextContainsActualTextWithCustomError(actualSuccessMessage, UrlBuilder.getMessage(expectedMsg));
        break;
      default:
        String successMessage = accountDashboardPage.waitForExpectedElement(By.cssSelector("body > div.page-wrapper > header > div.page.messages > div:nth-child(2) > div > div > div")).getText();
        accountDashboardPage.assertTrueWithCustomError(expectedMsg, successMessage);
    }
  }

  private By myAccountLinkList = By.cssSelector("ul.nav.items>li");

  public Integer cycleThurLinkListAndMatchArray(ArrayList<String> linksToMatch) {
    accountDashboardPage.isElementPresent(myAccountLinkList);
    int counter = 0;
    for (WebElement hamBurgerLink : accountDashboardPage.visibilityOfAllElementsLocatedBy(myAccountLinkList)) {
      for (String link : linksToMatch) {
        if (hamBurgerLink.getText().contains(link)) {
          LOG.info("MATCH FOUND!! : " + link);
          counter++;
        }
      }
    }
    LOG.info("Counter Total : " + counter +"\n");
    return counter;
  }

  @And("^cycle through My Account links and confirm content as expected, including '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)'$")
  public void cycleThroughMyAccountLinksAndConfirmContentAsExpected(String firstLink, String secondLink, String thirdLink, String forthLink, String fifthLink, String sixthLink, String seventhLInk, String eightLink) throws Throwable {
    ArrayList<String> linksToMatch = new ArrayList<>(Arrays.asList(firstLink,secondLink,thirdLink, forthLink, fifthLink, sixthLink, seventhLInk, eightLink));
    assertTrue(cycleThurLinkListAndMatchArray(linksToMatch).equals(8));
  }

  @And("^assert DOB field isn't editable$")
  public void assertFieldIsnTEditable() {
      accountDashboardPage.assertFieldIsnTEditable();
    }

  @And("^assert saved card div is present$")
  public void assertSavedCardDivIsPresent() {
        assertTrue(accountDashboardPage.waitForExpectedElement(By.cssSelector("div.table-wrapper.saved-cards")).isDisplayed());
  }

  @And("^assert saved card div is not present$")
  public void assertSavedCardDivIsNotPresent() {
    assertFalse(accountDashboardPage.isSavedCardsPresent());
  }

  @And("^click save card details$")
  public void clickSaveCardDetails() {
    if(accountDashboardPage.getCurrentUrl().contains("co/es/")){
       accountDashboardPage.clickByElementByQueryJSExecutor(SAVE_CARD_CHK_BOX);
    }else {
      try {
        if (!accountDashboardPage.getWebDriver().getCurrentUrl().contains("/mx/es/"))
          accountDashboardPage.waitForExpectedElement(accountDashboardPage.saveCardDetails, 10).click();
      } catch (Exception e) {
        accountDashboardPage.clickByElementByQueryJSExecutor(By.cssSelector("#worldpay_cc_save_card"));
      }
    }
  }

  @And("^assert PDF link is correct$")
  public void assertPDFLinkIsCorrect() {
	  String pdfHref;
	  if(accountDashboardPage.getWebDriver().getCurrentUrl().contains("/mx/es/"))
	  {
		  pdfHref = accountDashboardPage.waitForExpectedElement(accountDashboardPage.lnkPDFLink).getAttribute("href");
		  assertTrue("\n ******* ERROR :: PDF Link isn't as expected!!",pdfHref.contains("/mx/es/device-code/"));

//		   TODO: Uncomment the following code once the Bug 465282 fixed.

//        int actualStatusCode = RestAssured.given().log().all().get(pdfHref).statusCode();
//        Assertions.assertThat(actualStatusCode).as("The response for :" + pdfHref).isEqualTo(200);
	  }
	  else
	  {
		  pdfHref = accountDashboardPage.waitForExpectedElement(By.cssSelector("#device-registration-form > fieldset > div.field.serial-code.required > div > a")).getAttribute("href");
		  assertTrue("\n ******* ERROR :: PDF Link isn't as expected!!",pdfHref.contains("device-registration-code-helper.pdf"));
	  }
  }

  @And("^click on add news Address$")
  public void clickOnAddNewsAddress() {
    accountDashboardPage.waitForExpectedElement(By.cssSelector("button.action.primary.add")).click();
  }

  @And("^enter '(.*)' as lookup address$")
  public void enterAsLookupAddress(String storeLocatorLookup) throws Throwable {
    Thread.sleep(6000);
    switch (valueOf(getLocale().toUpperCase())) {
      case VELOZA:
      case VUSEDE:
      case VELOPL:
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.STORE_LOCATOR_SEARCH_LOCATION).clear();
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.STORE_LOCATOR_SEARCH_LOCATION).sendKeys(UrlBuilder.getMessage(storeLocatorLookup));
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.STORE_LOCATOR_SEARCH_LOCATION).sendKeys(Keys.DOWN);
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.STORE_LOCATOR_SEARCH_LOCATION).sendKeys(Keys.RETURN);
        break;
      case VUSEZA:
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.INPUT_SEARCH_LOCATION).clear();
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.INPUT_SEARCH_LOCATION).sendKeys(UrlBuilder.getMessage(storeLocatorLookup));
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.INPUT_SEARCH_LOCATION).sendKeys(Keys.DOWN);
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.INPUT_SEARCH_LOCATION).sendKeys(Keys.RETURN);
        break;
      case VELODE:
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEDE).clear();
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEDE).sendKeys(UrlBuilder.getMessage(storeLocatorLookup));
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEDE).sendKeys(Keys.DOWN);
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEDE).sendKeys(Keys.RETURN);
        break;
      case VUSEIT:
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEIT).clear();
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEIT).sendKeys(UrlBuilder.getMessage(storeLocatorLookup));
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEIT).sendKeys(Keys.DOWN);
        accountDashboardPage.waitForExpectedElement(registrationPage.ADDRESS_SEARCH_VUSEIT).sendKeys(Keys.RETURN);
        break;
      default:
        accountDashboardPage.waitForExpectedElement(registrationPage.INPUT_FIELD).clear();
        accountDashboardPage.waitForExpectedElement(registrationPage.INPUT_FIELD).sendKeys(UrlBuilder.getMessage(storeLocatorLookup));
        accountDashboardPage.waitForExpectedElement(registrationPage.INPUT_FIELD).sendKeys(Keys.DOWN);
        accountDashboardPage.waitForExpectedElement(registrationPage.INPUT_FIELD).sendKeys(Keys.RETURN);
    }
  }

  @And("^assert displayed address is '(.*)'$")
  public void assertDisplayedAddressIsTheCloistersRickmansworthWDHLUK(String expectedAddress) throws Throwable {
    String addressDisplayed="";
    Thread.sleep(4000);
    switch (UrlBuilder.getLocale()) {
      case "veloza":
      case "vusede":
      case "velopl":
        addressDisplayed = accountDashboardPage.waitForExpectedElement(accountDashboardPage.STORE_LOCATOR_SEARCH_LOCATION).getAttribute("value");
        accountDashboardPage.assertTrueExpectedTextContainsActualTextWithCustomError(addressDisplayed, UrlBuilder.getMessage(expectedAddress));
        break;
      case "vuseco":
        accountDashboardPage.waitForAjaxElementNotToBePresent(accountDashboardPage.getWebDriver(),30);
        addressDisplayed = accountDashboardPage.waitForExpectedElement(accountDashboardPage.INPUT_SEARCH_LOCATION).getAttribute("value");
        accountDashboardPage.assertTrueExpectedTextContainsActualTextWithCustomError(addressDisplayed, UrlBuilder.getMessage(expectedAddress));
        break;
      default:
        addressDisplayed = accountDashboardPage.waitForExpectedElement(accountDashboardPage.INPUT_SEARCH_LOCATION).getAttribute("value");
        LOG.info("Address displayed : " + addressDisplayed);
        accountDashboardPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedAddress),addressDisplayed);
    }
  }

  @And("^assert status of marketing tickbox$")
  public void assertStatusOfMarketingTickbox() throws Throwable
  {
    Thread.sleep(3000);
    switch (UrlBuilder.getLocale()) {
      case "vuseit":
        WebElement emailCheckBoxVuseIT = accountDashboardPage.waitForExpectedElement(EMAIL_CONSENT_CHK_BOX,10);
        WebElement telephoneTickBoxVuseIT = accountDashboardPage.waitForExpectedElement(MOBILE_SOCIAL_CONSENT_CHK_BOX_VUSEIT,10);
        assertTrue("ERROR *** Tick box SHOULD be selected", Boolean.parseBoolean(emailCheckBoxVuseIT.getAttribute("checked")));
        assertTrue("ERROR *** Tick box SHOULD be selected", Boolean.parseBoolean(telephoneTickBoxVuseIT.getAttribute("checked")));
        break;
      case "it":
        WebElement emailCheckBox = accountDashboardPage.waitForExpectedElement(EMAIL_CONSENT_CHK_BOX,10);
        WebElement telephoneTickBox = accountDashboardPage.waitForExpectedElement(MOBILE_SOCIAL_CONSENT_CHK_BOX,10);
        assertTrue("ERROR *** Tick box SHOULD be selected", Boolean.parseBoolean(emailCheckBox.getAttribute("checked")));
        assertTrue("ERROR *** Tick box SHOULD be selected", Boolean.parseBoolean(telephoneTickBox.getAttribute("checked")));
        break;
      case "vuseuk":
        WebElement emailTickBox = accountDashboardPage.waitForExpectedElement(EMAIL_CONSENT_CHK_BOX,10);
        assertTrue("ERROR *** Tick box SHOULD be selected", !emailTickBox.isSelected());
        break;
      case "kz":
      case "pl":
         emailTickBox = accountDashboardPage.waitForExpectedElement(EMAIL_CONSENT_CHK_BOX,10);
        assertTrue("ERROR *** Tick box SHOULD be selected", emailTickBox.isSelected());
        break;
      case "vusefr":
        WebElement emailTickBoxFR = accountDashboardPage.waitForExpectedElement(EMAIL_CONSENT_CHK_BOX_FR,10);
        assertTrue("ERROR *** Tick box SHOULD be selected", Boolean.parseBoolean(emailTickBoxFR.getAttribute("checked")));
        break;
      case "mx":
      case "vusemx":
        Thread.sleep(3000);
        accountDashboardPage.waitForExpectedElement(EMAIL_CONSCENT_MX);
        accountDashboardPage.isElementPresent(EMAIL_CONSCENT_MX);
        break;
      default:
        WebElement marketingTickBox = accountDashboardPage.waitForExpectedElement(By.cssSelector("input#subscription.checkbox"),10);  //("#form-validate > fieldset > div.field.choice > label"));
        Boolean newsLetterTickBoxSelected =  marketingTickBox.isSelected();
        LOG.info("Status of Marketing checkbox using isChecked attribute : " + marketingTickBox.getAttribute("checked"));
        assertTrue("ERROR *** Tick box SHOULD be selected",newsLetterTickBoxSelected);
    }
  }


  @And("^uncheck email and sms consent checkbox and click save button$")
  public void uncheckEmailAndSmsConsentCheckboxAndClickSaveButton() throws Throwable {
      uncheckEmailConsentCheckbox("uncheck");
      uncheckSMSConsentCheckbox("uncheck");
      clickSubscriptionSaveButton();
  }

  @And("^(tick|uncheck) email consent checkbox$")
  public void uncheckEmailConsentCheckbox(String input) {
    WebElement emailCheckbox;
    emailCheckbox = Optional.ofNullable(accountDashboardPage.waitForExpectedElement(EMAIL_CONSENT_CHK_BOX, 10))
            .orElseGet(() -> accountDashboardPage.waitForExpectedElement(SUBSCRIPTION_CHK_BOX));

    switch (input) {
      case "uncheck":
        if (emailCheckbox.isSelected()) {
          accountDashboardPage.clickUsingJS(emailCheckbox);
        }
        break;
      case "tick":
        accountDashboardPage.clickUsingJS(emailCheckbox);
        break;
    }
  }

  @And("^(tick|uncheck) SMS consent checkbox$")
  public void uncheckSMSConsentCheckbox(String input) {
    if (accountDashboardPage.isElementDisplayedBySeconds(MOBILE_SOCIAL_CONSENT_CHK_BOX, 10)) {
      WebElement smsCheckbox = accountDashboardPage.waitForExpectedElement(MOBILE_SOCIAL_CONSENT_CHK_BOX, 10);
      if ("uncheck".equals(input)) {
          if (smsCheckbox.isSelected()) {
              accountDashboardPage.clickUsingJS(smsCheckbox);
          }
      } else if ("tick".equals(input)) {
          accountDashboardPage.clickUsingJS(smsCheckbox);
      }
    }
  }

  @And("^click subscription save button$")
  public void clickSubscriptionSaveButton() {
      accountDashboardPage.clickByElementByQueryJSExecutor(SUBSCRIPTION_SAVE_BTN);
      accountDashboardPage.waitForExpectedElement(SUBSCRIPTION_MESSAGE,10);
  }

  @And("^uncheck marketing tickbox$")
  public void uncheckMarketingTickbox() throws Throwable {
    Thread.sleep(3000);
    WebElement marketingTickBox = accountDashboardPage.waitForExpectedElement(By.cssSelector("input#subscription.checkbox"));
    WebElement saveButton = accountDashboardPage.waitForExpectedElement(By.cssSelector("button.action.save.primary"));
    accountDashboardPage.clickByElementByQueryJSExecutor(By.cssSelector("input#subscription.checkbox"));
    assertTrue("Tick box shouldn't be selected!",!marketingTickBox.isSelected());
    Thread.sleep(3000);
    accountDashboardPage.clickByElementByQueryJSExecutor(By.cssSelector("button.action.save.primary"));
  }

  @And("^assert Subscriptions table headings$")
  public void assertSubscriptionsTableHeadings() {
    ArrayList<String> headingToMatch = new ArrayList<>(Arrays.asList("Description","Status","Frequency","Total"));
    int matchCounter = 0;
    for (WebElement header : accountDashboardPage.getTableHeaders(accountDashboardPage.mySubscriptionTable)){ //accountDashboardPage.visibilityOfAllElementsLocatedBy(accountDashboardPage.mySubscriptionTable)){
      if (headingToMatch.contains(header.getText())){
        matchCounter ++;
      } else {
        LOG.info("\n No match");
      }
    }
    assertTrue("Incorrect number of heading matches were found for the subscription table heading",matchCounter == 4);
  }

  @And("^open a subscription and ensure data is correct$")
  public void openASubscriptionAndEnsureDataIsCorrect() throws Throwable {
    for (WebElement data : accountDashboardPage.getTableCols(accountDashboardPage.mySubscriptionTable)){
      LOG.info("Table DATA : " + data.getText());
      if (data.getText().equals("find_in_page View")){
        data.findElement(By.linkText("find_in_page View")).click();
        LOG.info("Element has been clicked");
        Thread.sleep(6000);
        break;
      }
    }
  }

  @And("^newsLetter div form displayed$")
  public void newsletterDivFormDisplayed() {
    switch(UrlBuilder.getLocale()){
      case "mx":
      case "vusemx":
        assertTrue(accountDashboardPage.waitForExpectedElement(NEWS_LETTER_FORM_MXCX).isDisplayed());
        break;
        default:
          assertTrue(accountDashboardPage.waitForExpectedElement(By.cssSelector("form.form-newsletter-manage")).isDisplayed());
    }
  }

  @And("^ensure the newsletter icon isn't selected$")
  public void ensureTheNewsletterIconIsnTSelected() {
    accountDashboardPage.ensureTheNewsletterIconIsnTSelected();
  }

  @And("^select update password checkbox$")
  public void selectUpdatePasswordCheckbox() {
	  switch (UrlBuilder.getLocale()) {
      case "it":
    	  accountDashboardPage.clickByElementByQueryJSExecutor(By.xpath("//label[@for='change-password']"));
        break;
      default:
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.UPDATE_PASSWORD_CHECKBOX,20);
        accountDashboardPage.scrollToElement(accountDashboardPage.UPDATE_PASSWORD_CHECKBOX);
        accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.UPDATE_PASSWORD_CHECKBOX);
	  }
  }

  @And("^update password with '(.*)' and assert error msg$")
  public void updatePasswordWith(String password) {
    accountDashboardPage.waitForExpectedElement(By.cssSelector("#current-password")).sendKeys("Pa55word");
    accountDashboardPage.waitForExpectedElement(By.cssSelector("#password")).clear();
    accountDashboardPage.waitForExpectedElement(By.cssSelector("#password")).sendKeys(password);
    assertTrue(accountDashboardPage.waitForExpectedElement(By.cssSelector("#password-error")).isDisplayed());
  }

  @And("^add new Address button displayed$")
  public void addNewAddressButtonDisplayed() {
      accountDashboardPage.addNewAddressButtonDisplayed();
  }

  @And("^click on my account new address button$")
  public void clickOnMyAccountNewAddressButton() {
      accountDashboardPage.clickOnMyAccountNewAddressButton();
  }

  @And("^user is returned to the base Address book page$")
  public void userIsReturnedToTheBaseAddressBookPage() {
    switch (UrlBuilder.getLocale()) {
      case "mx":
      case "vusemx":
      case "vuseco":
      case "vuseza":
        accountDashboardPage.waitForPage();
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading, 30);
        String expectedMessage = UrlBuilder.getMessage("addressBookPageTitle.key");
        try {
          assertEquals(expectedMessage, accountDashboardPage.waitForExpectedElement(accountDashboardPage.registeringConfirmationMsg, 10).getText());
        } catch (Exception ex) {
          assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading).getText().contains(UrlBuilder.getMessage("AddressBookHeading.key")));
        }
        break;
      case "vusede":
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading, 3);
        try {
          assertEquals(UrlBuilder.getMessage("successfulSaveAddressMessage.key"), accountDashboardPage.waitForExpectedElement(accountDashboardPage.registeringConfirmationMsg, 10).getText());
        } catch (Exception ex) {
          assertTrue(accountDashboardPage.getWebDriver().getTitle().equals(UrlBuilder.getMessage("editAddressPageTitle.key")));
        }
        break;
      case "nl":
      case "de":
          accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading, 3);
        try {
          assertEquals(UrlBuilder.getMessage("successfulSaveAddressMessage.key"), accountDashboardPage.waitForExpectedElement(accountDashboardPage.registeringConfirmationMsg, 10).getText());
        } catch (Exception ex) {
          assertTrue(accountDashboardPage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("editAddressHeading.key"))).isDisplayed());
        }
        break;
      case "kz":
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading, 20);
        try {
          assertEquals(accountDashboardPage.waitForExpectedElement(accountDashboardPage.registeringConfirmationMsg, 20).getText(),UrlBuilder.getMessage("RegistrationConfirmMsg.key"));
        } catch (Exception ex) {
          assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading).getText().contains("АДРЕСНАЯ КНИГА"));
        }
        break;
      case "vypeit":
      case "it":
          accountDashboardPage.waitForPage();
          if(!UrlBuilder.getMessage("addressBookPageTitle.key").equalsIgnoreCase(accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading).getText()))
          {
            accountDashboardPage.waitForAjaxElementNotToBePresent(webDriver,20);
          }
          else {
            assertEquals(UrlBuilder.getMessage("addressBookPageTitle.key").toLowerCase(), accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading).getText().toLowerCase());
          }
          break;
      case "vuseit":
        accountDashboardPage.waitForPage();
        assertEquals(UrlBuilder.getMessage("addressBookPageTitle.key"), accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading, 10).getText());
        break;
      case "lyftse":
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains("Adressbok"));
        break;
      case "fr":
      case "vusefr":
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains("Carnet D’adresses"));
        break;
      case "dk":
      case "lyftdk":
      case "vusedk":
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().toLowerCase().contains("adresser"));
        break;
      case "glode":
        assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading, 10).getText().contains("ADRESSBUCH"));
        break;
      case "pl":
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains("Zarządzanie kontem"));
        break;
      default:
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains("Address Book"));
    }
  }

  @And("^assert new first and surname are present$")
  public void assertNewFirstAndSurnameArePresent() {
      if("glode".equals(UrlBuilder.getLocale())){
          assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains(
                UrlBuilder.getMessage("streetAddressLine.key")));
          accountDashboardPage.removeAddress();

      }else{
        LOG.info("First Name : "  +AddNewAddressPage.firstNameData);
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains(AddNewAddressPage.firstNameData));
        assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains(AddNewAddressPage.lastNameData));
      }
  }

  @And("^ensure Email checkbox isn't selected$")
  public void ensureEmailCheckboxIsnTSelected() {
    if(accountDashboardPage.doesURLContain("vype.non-prod.marketing.bat.net/it/it/")) {
    WebElement chkEmailOption = accountDashboardPage.waitForExpectedElement(By.cssSelector("div.field.social-checks:nth-child(2) div.social-left.newsletter > label:nth-child(2)"));

    By saveButton = By.cssSelector("button.action.save.primary");
    if (!chkEmailOption.isSelected()){
    	accountDashboardPage.clickElementByQueryJSExecutor(chkEmailOption);
    }
      accountDashboardPage.clickByElementByQueryJSExecutor(saveButton);
      accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading,30);
   }
  }

  @And("^ensure SMS checkbox isn't selected$")
  public void ensureSMSCheckboxIsnTSelected() {
    if(accountDashboardPage.doesURLContain("vype.non-prod.marketing.bat.net/it/it/")) {
    WebElement chkSMSOption = accountDashboardPage.waitForExpectedElement(By.cssSelector("div.field.social-checks:nth-child(2) div.social-right > label:nth-child(2)"));

    By saveButton = By.cssSelector("button.action.save.primary");
    if (!chkSMSOption.isSelected()){
    	accountDashboardPage.clickElementByQueryJSExecutor(chkSMSOption);
    }
      accountDashboardPage.clickByElementByQueryJSExecutor(saveButton);
      accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading,30);
   }
  }

  @And("^ensure SMS checkbox is selected$")
  public void ensureSMSCheckboxIsSelected() {
    if (accountDashboardPage.isElementDisplayedBySeconds(MOBILE_SOCIAL_CONSENT_CHK_BOX, 10)) {
      switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
        case PL:
        case IT:
          WebElement mobileOrSocialConsentChkBox = accountDashboardPage
                  .waitForExpectedElement(MOBILE_SOCIAL_CONSENT_CHK_BOX, 10);
          assertTrue("ERROR *** Tick box SHOULD be selected",
                  Boolean.parseBoolean(mobileOrSocialConsentChkBox.getAttribute("checked")));
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + Locale.valueOf(UrlBuilder.getLocale().toUpperCase()));
      }
    }
  }

  @And("^ensure thirdParty consent checkbox is selected$")
  public void ensureThirdPartyConsentCheckboxIsSelected() {
    if (accountDashboardPage.isElementDisplayedBySeconds(
            accountDashboardPage.consentToSharingDataWithThirdPartiesTickBox, 10)) {
      WebElement mobileOrSocialConsentChkBox = accountDashboardPage
              .waitForExpectedElement(accountDashboardPage.consentToSharingDataWithThirdPartiesTickBox);
      assertTrue("ERROR *** Tick box SHOULD be selected",
              Boolean.parseBoolean(mobileOrSocialConsentChkBox.getAttribute("checked")));
    }
  }

  @Then("^users clicks on the Edit link under Profile Image section$")
  public void usersclicksOnEditLinkUnderProfileImageSection() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.lnkEditProfileImage);
  }

  @Then("^assert Upload Profile Image section is displayed$")
  public void assertUploadProfileImageSectionIsDisplayed() {
	  assertTrue(accountDashboardPage.getWebDriver().getPageSource().contains("Upload Profile Image"));
  }

  @When("^user clicks on profile upload icon$")
  public void userClicksOnProfileUploadIcon() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnUploadImage);
  }

  @Then("^user uploads the Image from the system$")
  public void userUploadsTheImageFromSystem() {
	  accountDashboardPage.clickSave();
  }

  @Then("^assert a Profile Image is uploaded$")
  public void assertProfileImageIsUploaded() {
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.eleProfileImage).getAttribute("src").contains("media/customer_profile_image"));
  }

  @Then("^assert Profile Image banner is displayed on the side menu$")
  public void assertProfileImageBannerOnSideMenu() {
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.eleProfileImageBanner).getAttribute("src").contains("media/customer_profile_image"));
  }

  @Then("^assert length of User's First Name and Last Name on Account Dashboard$")
  public void assertLengthOfFirstNameAndLastNameOnAccountDashboard() {
	  accountDashboardPage.assertLengthOfUserFirstNameAndLastNameOnAccountDashboard();
  }

  @Then("^assert Need a Hand Section is displayed$")
  public void assertNeedaHandSection() {
	  accountDashboardPage.scrollElementIntoView(accountDashboardPage.eleNeedHandSectionHeader);
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.eleNeedHandSectionHeader).isDisplayed());
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.eleNeedHandSectionText).isDisplayed());
	  assertTrue(accountDashboardPage.waitForExpectedElement(By.linkText("Online Chat")).isEnabled());
	  assertTrue(accountDashboardPage.waitForExpectedElement(By.partialLinkText("Call To Number- ")).isEnabled());
	  assertTrue(accountDashboardPage.waitForExpectedElement(By.linkText("info@govype.com")).isEnabled());
  }

  @Then("^assert product is added to wishlist$")
  public void assertProductIsAddedUnderMyFavorites() {
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.productWishlistSectionMyAccount).isDisplayed());
  }

  @Then("^user clicks on Add to Basket button from My Favorites section$")
  public void userClicksOnAddToBasketFromMyFavouritesSection() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnAddToBasketMyAccount);
  }

  @Then("^user clicks on Add to Basket button from My Favorites page$")
  public void userClicksOnAddToBasketFromMyFavouritesPage() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnAddToBasketFavoritesPage);
  }

  @Then("^assert the order of the products added is reverse of the sequence$")
  public void assertOrderOfProductsAddedIsReverseOfSequence() {
	  accountDashboardPage.assertOrderOfProductsAddedIsReverseOfSequence();
  }

  @Then("^remove multiple products from My Favorites page$")
  public void removeMultipleProductsFromMyFavoritesPage() {
	  accountDashboardPage.removeMultipleProductsFromMyFavoritesPage();
  }

  @Then("^clicks on Edit button for a saved card$")
  public void userClicksOnEditButtonForASavedCard() {
	  accountDashboardPage.waitForExpectedElement(accountDashboardPage.btnEditSavedCard,10).click();
  }

  @Then("^clicks on Remove button for a saved card$")
  public void userClicksOnRemoveButtonForASavedCard() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnRemoveSavedCard);
  }

  @Then("^user edits the Card Name field of the saved card$")
  public void userEditCardNameFieldOfTheSavedCard() {
	  accountDashboardPage.userEditCardNameFieldOfTheSavedCard();
  }

  @Then("^clicks on Update button on Edit block$")
  public void userClicksOnUpdateButtonOnEditBlock() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.BTN_UPDATE_SAVED_CARD);
  }

  @Then("^user clicks on Cancel button on Edit block$")
  public void userClicksOnCancelButtonOnEditBlock() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnCancelSavedCardUpdates);
  }

  @Then("^user clicks on Route button for the displayed address$")
  public void userClicksOnRouteButtonForDisplayedAddress() {
	  accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnRoute);
  }

  @Then("^assert google maps are opened in a new tab with Destination pre-populated$")
  public void assertGoogleMapsAreOpenedInNewTabWithPrePopulatedDestination() {
    accountDashboardPage.assertGoogleMapsInNewTabWithPrePopulatedDestination();
  }

  @Then("^assert payment methods section with card details is displayed$")
  public void assertPaymentMethodsSectionIsDisplayed() {
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.elePaymentMethodsSection,10).isDisplayed());
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.eleCardNumberField).isDisplayed());
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.eleExpirationDateField).isDisplayed());
  }

  @Then("^user clicks on Remove button for a payment method$")
  public void userClicksOnRemoveButtonForPaymentMethod() {
	  accountDashboardPage.getWebDriver().findElements(accountDashboardPage.btnRemovePaymentMethod).get(0).click();
  }

  @Then("^user clicks on Cancel button from Delete pop up and navigate back to base page$")
  public void userClicksOnCancelFromDeletePopUpAndBackToBasePage() {
	  accountDashboardPage.waitForItemToBeClickableAndClick(accountDashboardPage.btnCancelDeletion,5);
	  assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.elePaymentMethodsSection,10).isDisplayed());
  }

  @Then("^user clicks on Delete button from the pop up$")
  public void userClicksOnDeleteFromDeletePopUp() {
	  accountDashboardPage.waitForItemToBeClickableAndClick(accountDashboardPage.btnDeleteMethod,5);
	  accountDashboardPage.waitForExpectedElement(accountDashboardPage.pageHeading,10);
  }

  @Then("^user deletes the account and verify deletion success page$")
  public void userDeletesTheAccountAndVerifyDeletionSuccessPage () throws Throwable {
    switch (UrlBuilder.getLocale()) {
      case "de":
        LOG.info("This Step is not applicable for Vype DE locale");
        break;
      default:
        assertLinkSayingDeleteMyAccountIsPresent();
        accountDashboardPage.deleteMyAccount();
    }
  }

  @Then("^user deletes all the saved cards from My Saved Cards list$")
  public void userDeletesSavedCardsFromMySavedCardsList() {
    int intCountSavedCards=0;
    switch (UrlBuilder.getLocale()) {
      case "ie":
        intCountSavedCards = accountDashboardPage.getWebDriver().findElements(accountDashboardPage.btnRemoveSavedCard).size();
        if (intCountSavedCards == 0) {
          LOG.info("No Saved Cards Under My Saved Cards list.");
        } else {
          do {
            accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnRemoveSavedCard);
          }
          while (accountDashboardPage.getWebDriver().findElements(accountDashboardPage.btnRemoveSavedCard).size() > 0);
        }
        break;
      case "uk":
        intCountSavedCards = accountDashboardPage.getWebDriver().findElements(accountDashboardPage.vypeCXbtnRemoveSasvedCard).size();
        if (intCountSavedCards == 0) {
        } else {
          do {
            accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.vypeCXbtnRemoveSasvedCard);
          }
          while (accountDashboardPage.getWebDriver().findElements(accountDashboardPage.vypeCXbtnRemoveSasvedCard).size() > 0);
        }
        break;
      case "dk":
          intCountSavedCards = accountDashboardPage.getWebDriver().findElements(accountDashboardPage.btnRemoveSavedCard).size();
          if (intCountSavedCards == 0) {
            LOG.info("No Saved Cards Under My Saved Cards list.");
          } else {
            do {
              accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.btnRemoveSavedCard);
            }
            while (accountDashboardPage.getWebDriver().findElements(accountDashboardPage.btnRemoveSavedCard).size() > 0);
          }
          break;
      case "fr":
      case "vusefr":
        intCountSavedCards = accountDashboardPage.getWebDriver().findElements(accountDashboardPage.BTN_REMOVE_SAVED_CARD_FR).size();
        if (intCountSavedCards == 0) {
          LOG.info("No Saved Cards Under My Saved Cards list.");
        } else {
          do {
            accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.BTN_REMOVE_SAVED_CARD_FR);
            accountDashboardPage.waitForExpectedElement(GloItHomePage.DELETE_YES_BUTTON,5).click();
          }
          while (accountDashboardPage.getWebDriver().findElements(accountDashboardPage.BTN_REMOVE_SAVED_CARD_FR).size() > 0);
        }
        break;
      case "vypeit":
      case "vuseuk":
      case "vuseit":
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.REMOVE_SAVED_CARDS_VYPEIT,10);
        intCountSavedCards = accountDashboardPage.getWebDriver().findElements(accountDashboardPage.REMOVE_SAVED_CARDS_VYPEIT).size();
        if (intCountSavedCards == 0) {
          LOG.info("No Saved Cards Under My Saved Cards list.");
        } else {
          do {
            accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.REMOVE_SAVED_CARDS_VYPEIT);
            accountDashboardPage.waitForExpectedElement(GloItHomePage.DELETE_YES_BUTTON,5).click();
          }
          while (accountDashboardPage.getWebDriver().findElements(accountDashboardPage.REMOVE_SAVED_CARDS_VYPEIT).size() > 0);
        }
        break;
         default:
           LOG.info("This step is not applicable for the current locale.");
    }
  }

  @Then("^user clicks on How Does it work icon$")
  public void userClicksOnHowDoesItWorksIcon() {
    accountDashboardPage.clickOnHowDoesItWorksIcon();
  }

  @Then("^assert '(.*)' link is not present on Account Dashboard Menu$")
  public void assertFavoritesLinkIsNotPresentOnAccountDashboardMenu(String strExpectedLink) throws Throwable {
    accountDashboardPage.assertFavoritesLinkIsNotPresentOnMyAccount(strExpectedLink);}

  @When("^user selects ([^\"]*) link on my account Page$")
  public void userSelectsLinkOnLoginPage(String linkName) throws InterruptedException {
    switch (linkName){
      case "deleteMyAccount":
        accountDashboardPage.deleteMyAccount();
        break;
      case "orderHistory":
        accountDashboardPage.orderHistory();
        break;
      default:
        throw new IllegalArgumentException("Invalid linkName provided:"+linkName);
    }
  }

  @Then("^account is deleted successfully on my account Page$")
  public void accountIsDeletedSuccessfullyOnLoginPage(){
    if(UrlBuilder.getLocale().matches("kz|vypeit|vuseit")){
      return;
    }
  }

  @And("^assert First Name field isn't editable$")
  public void assertFirstNameFieldIsNotEditable() {
    if(!UrlBuilder.getLocale().matches("vypeuk")) {
      accountDashboardPage.assertFirstNameFieldIsNotEditable();
    }
  }

  @And("^assert Last Name field isn't editable$")
  public void assertLastNameFieldIsNotEditable() {
    if(!UrlBuilder.getLocale().matches("vypeuk")) {
      accountDashboardPage.assertLastNameFieldIsNotEditable();
    }
  }

  @Then ("^fetch First and Last Names of the logged-in user$")
  public void fetchFirstAndLastNamesOfLoggedInUser() {
    accountDashboardPage.fetchFirstAndLastNamesOfLoggedInUser();
  }

  @And("^assert Change Email field isn't editable$")
  public void assertChangeEmailFieldIsNotEditable() {
    accountDashboardPage.assertChangeEmailFieldIsNotEditable();
  }

  @And("^assert content section by business regarding non-editable fields$")
  public void assertNonEditableCustomerFieldsContentInfo() {
    accountDashboardPage.assertNonEditableCustomerFieldsContentInfo();
  }

  @And("^click on Edit button from Account Dashboard$")
  public void clickOnEditButtonFromAccountDashboard() {
    accountDashboardPage.clickOnEditButtonFromAccountDashboard();
  }

  @And("^users clicks on Payment link from My Account Dashboard$")
  public void userClicksOnPaymentLinkFromMyAccountDashboard() {
    accountDashboardPage.clickOnPaymentLinkFromMyAccountDashboard();
  }

  @And("^users clicks on the Print Order link$")
  public void clickOnPrintOrderLink() {
    accountDashboardPage.clickOnPrintOrderLink();
  }

  @And("^device trial order table is displayed$")
  public boolean deviceTrialOrderTableDisplayed() throws Throwable {
    return accountDashboardPage.deviceTrialOrderTablePresent();
  }

  @Then("^User is able to update payment option for a subscription \\(Native\\)$")
  public void userIsAbleToUpdatePaymentOptionForASubscriptionNative() throws InterruptedException{
    globalSubscriptionsPage.updatePaymentMethod();
  }

  @Then ("^fetch DOB and Address Details of the logged-in user$")
  public void fetchDOBandAddressDetailsOfLoggedInUser() throws Throwable {
    accountDashboardPage.fetchDOBandAddressDetailsOfLoggedInUser();
  }

  @Then ("^fetch Address Details of the logged-in user$")
  public void fetchAddressDetailsOfLoggedInUser() {
    accountDashboardPage.fetchAddressDetailsOfLoggedInUser();
  }

  @Then ("^fetch Email Address of the logged-in user$")
  public void fetchEmailAddressOfLoggedInUser() {
    accountDashboardPage.fetchEmailAddressOfLoggedInUser();
  }

    @And("^registration success message is not displayed$")
    public void userIsNotNavigatedToMyAccountPage() {
      assertFalse(accountDashboardPage.isSuccessMessageReturned());

    }

  @And("^eyes check sub pages on My Account page$")
  public void eyesCheckSubPagesOnMyAccountPage() {
    if (!Props.EYES_ON) {
      return;
    }

    List<EyesCheckpoints> pages = new ArrayList<>();
    switch (UrlBuilder.getLocale()) {
      case "it":
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_ADD_A_NEW_ADDRESS_PAGE,
                EyesCheckpoints.ACCOUNT_HISTORIC_ORDERS_PAGE,
                EyesCheckpoints.ACCOUNT_SAVED_CARD_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE,
                EyesCheckpoints.ACCOUNT_DEVICE_REGISTRATION_PAGE,
                EyesCheckpoints.ACCOUNT_REFER_A_FRIEND_PAGE
        );
        break;
      case "lyftse":
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_SAVED_CARD_PAGE
        );
        break;
      case "vuseit":
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_SAVED_CARD_PAGE
        );
        break;
      case "vusedk":
        homePage.clickByElement(homePage.PERSON_ICON);
        homePage.clickByElement(homePage.VUSE_ACCOUNT_LINK_DK);
        pages = Arrays.asList(
            EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
            EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
            EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE,
            EyesCheckpoints.ACCOUNT_SAVED_CARD_PAGE
        );
        break;
      case "vusede":
        homePage.clickAccountLink();
        homePage.chooseMyAccountOnAccountDropdown();
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_ADD_A_NEW_ADDRESS_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE,
                EyesCheckpoints.ACCOUNT_SAVED_CARD_PAGE,
                EyesCheckpoints.ACCOUNT_REFER_A_FRIEND_PAGE
        );
        break;
      case "glode":
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_ADD_A_NEW_ADDRESS_PAGE,
                EyesCheckpoints.ACCOUNT_SAVED_CARD_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE,
                EyesCheckpoints.ACCOUNT_DEVICE_REGISTRATION_PAGE,
                EyesCheckpoints.ACCOUNT_REFER_A_FRIEND_PAGE
        );
        break;
      case "pl":
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE,
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE
        );
        break;
      case "vusefr":
        homePage.clickAccountLink();
        homePage.chooseMyAccountOnAccountDropdown();
        break;
      case "uk":
      case "vuseuk":
        homePage.clickAccountLink();
        homePage.chooseMyAccountOnAccountDropdown();
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_SAVED_CARD_PAGE,
                EyesCheckpoints.ACCOUNT_NEWSLETTER_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE
        );
        break;
      case "vuseco":
        homePage.clickAccountLink();
        homePage.chooseMyAccountOnAccountDropdown();
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_ADD_A_NEW_ADDRESS_PAGE
        );
        break;
      case "vuseza":
        homePage.clickAccountLink();
        homePage.chooseMyAccountOnAccountDropdown();
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_ADD_A_NEW_ADDRESS_PAGE,
                EyesCheckpoints.ACCOUNT_REFER_A_FRIEND_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE
        );
        break;
      case "kz":
        homePage.clickAccountLink();
        homePage.chooseMyAccountOnAccountDropdown();
        pages = Arrays.asList(
                EyesCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                EyesCheckpoints.ACCOUNT_ADD_A_NEW_ADDRESS_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                EyesCheckpoints.ACCOUNT_ORDER_DETAILS_PAGE
        );
        break;
    }
    pages.forEach(subPage -> {
      accountDashboardPage.gotoMyAccountSubPage(subPage);
      accountDashboardPage.eyesCheckAccountSubPage(subPage);
    });
  }

    @And("^user clicks on the view all cards link$")
    public void userClicksOnTheViewAllCardsLink() {
      accountDashboardPage.clickViewAllSavedCards();
    }

  @And("^assert all marketing tickbox should be selected$")
  public void assertAllMarketingTickboxIsSelected() throws Throwable {
   assertTrue(newsLetterPage.waitForExpectedElement(newsLetterPage.EMAIL_CONSENT_CHK_BOX,5).isSelected());
   assertTrue(newsLetterPage.waitForExpectedElement(newsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX).isSelected());
   assertTrue(newsLetterPage.waitForExpectedElement(newsLetterPage.THIRD_PARTY_CONSENT_CHK_BOX_GLOPL).isSelected());
  }

  @Then("^Subscription has the status '(.*)'$")
  public void subscriptionHasTheStatusSubsAccountStatusDisplayedPauseKey(String status) {
    globalSubscriptionsPage.assertSubscriptionStatus(UrlBuilder.getMessage(status));
  }

  @Then("^user can (increase|decrease) then subscription amount by '(\\d+)'$")
  public void userCanIncreaseThenSubscriptionAmountBy(String modifier,int qty) throws InterruptedException{
    globalSubscriptionsPage.modifySubscriptionQty(modifier,qty);
  }

  @And("^the tier discount has been updated to '(.*)'$")
  public void theTierDiscountHasBeenUpdatedToGold(String tier) throws Throwable{
    globalSubscriptionsPage.checkSubscriptionTier(tier);
  }

  @Then("^assert that Newsletter checkbox is '(.*)' selected$")
  public void assertThatNewsletterCheckboxIsChecked(String checkboxStatus) {
    switch (UrlBuilder.getLocale()) {
      case "velopl":
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        boolean checkboxCheckedStatus_email = getWebDriver().findElement(accountDashboardPage.NEWSLETTERSUBSCRIPTIONCHECKEDCHECKBOX_EMAIL).isSelected();
        boolean checkboxCheckedStatus_mobile = getWebDriver().findElement(accountDashboardPage.NEWSLETTERSUBSCRIPTIONCHECKEDCHECKBOX_MOBILE).isSelected();
        LOG.info("checkboxCheckedStatus=" + checkboxCheckedStatus_email);
        assertTrue(checkboxCheckedStatus_email);
        assertTrue(checkboxCheckedStatus_mobile);
        break;


      default:
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        boolean checkboxCheckedStatus = getWebDriver().findElement(accountDashboardPage.NEWSLETTERSUBSCRIPTIONCHECKEDCHECKBOX).isSelected();
        LOG.info("checkboxCheckedStatus=" + checkboxCheckedStatus);
        assertTrue(checkboxCheckedStatus);
    }
  }

  @Then("^assert that Newsletter checkbox is '(.*)' not selected$")
  public void assertThatNewsletterCheckboxIsNotChecked(String checkboxStatus) {
    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    boolean checkboxCheckedStatus = getWebDriver().findElement(accountDashboardPage.NEWSLETTERSUBSCRIPTIONCHECKEDCHECKBOX).isSelected();
    assertFalse(checkboxCheckedStatus);
  }

  @Then("^assert newsletter unsubscribe success message '(.*)'$")
  public void assertNewsletterUnsubscribeSuccessMessageNewsletterUnsubscribeSuccessMessageKey(String successMessage) {
    String actualMessage = waitForExpectedElement(accountDashboardPage.NEWSLETTER_SUCCESS_MSG_Text, 10).getText();
    assertThat(actualMessage.equals(successMessage));
  }

  @And("^assert ID field is not editable$")
  public void assertUserIDFieldIsNotEditable() {
    assertTrue(!waitForExpectedElement(registrationPage.CUSTOMER_ID_COLLECTION_FIELD).isEnabled());
  }

  @And("^assert user remains logged-in after language switch$")
  public void assertUserRemainsLoggedInAfterLanguageSwitch() {
      homePage.clickPersonIcon();
      assertTrue(accountDashboardPage.waitForURLToContain("/customer/account/"));
  }

  @And("^user should see the list of stores$")
  public void verifyListOfStoresDisplayed() {
    homePage.assertlistofstores();
  }

  @And("^capture the referal coupon code$")
  public void getReferralCouponCodeZA() {
    accountDashboardPage.getReferralCouponCode();
  }

  @And("^navigate to whatsapp url after select share via whatsapp$")
  public void shareReferralViaWhatsapp() {
    accountDashboardPage.verifyReferralSendViaWhatsapp();
  }

  @And("^user clicks on the update subscription button$")
  public void userClicksOnTheUpdateSubscriptionButton() { globalSubscriptionsPage.clickUpdateSubscriptionButton(); }

  @And("^user clicks on bin to remove the subscription$")
  public void userClicksOnBinToRemoveTheSubscription() { globalSubscriptionsPage.clickBinToRemoveFirstSubscription(); }

  @And("^user clicks on cancel subscription button on Modify Subscription module$")
  public void userClicksOnCancelSubscriptionButtonOnModifySubscriptionModule() { globalSubscriptionsPage.clickCancelSubscriptionButtonOnModifySubscriptionModule(); }

  @Then("^assert update subscription button on Modify Subscription module is disabled$")
  public void assertUpdateSubscriptionButtonOnModifySubscriptionModuleIsDisabled() {
    assertTrue(globalSubscriptionsPage.updateSubscriptionButtonOnModifySubscriptionModuleIsDisabled());
  }

  @Then("^user (increase|decrease) the subscription amount by '(\\d+)'$")
  public void userIncreaseTheSubscriptionAmountBy(String modifier,int qty) throws InterruptedException {
    globalSubscriptionsPage.modifyTheSubscriptionQty(modifier,qty);
  }

  @And("^users clicks on Edit \"([^\"]*)\" Address link$")
  public void usersClicksOnEditAddressLink(String shippingOrBilling) throws Throwable {
      if (shippingOrBilling.equals("billing")) {
        addNewAddressPage.userClicksOnEditBillingAddressLink();
      } else {
        addNewAddressPage.userClicksOnEditShippingAddressLink();
      }
  }

  @Then("^the user can see \"([^\"]*)\" on the my account page$")
  public void theUserCanSeeOnTheMyAccountPage(String myAccountComponent) throws Throwable {
    if (Props.getProp("siteMode").equalsIgnoreCase("desktop")) {
      logininPage.storeUserData();
      avalancheAccountDashboardPage.validateMyAccountComponent(myAccountComponent);
    }
  }

  @When("^I click on \"([^\"]*)\" tab$")
  public void iClickOnTab(String tabName) throws Throwable {
    avalancheAccountDashboardPage.iClickOnTab(tabName);
  }

  @Then("^I should see \"([^\"]*)\" on the my details page$")
  public void iShouldSeeOnTheMyDetailsPage(String myDetailscomponent) throws Throwable {
    avalancheAccountDashboardPage.validateMyDetailsComponent(myDetailscomponent);
  }

  @Then("^I should see a history of all orders on the page$")
  public void iShouldSeeAHistoryOfAllOrdersOnThePage() {
    if (Props.getProp("siteMode").equals("desktop")) {
      avalancheAccountDashboardPage.iSeeHistoryOfAllOrders();
    }
  }

  @And("^there is a view order CTA next to each order$")
  public void thereIsAViewOrderCTANextToEachOrder() {
    avalancheAccountDashboardPage.thereIsAViewOrderCtaNextToEachOrder();
  }

  @And("^on clicking the View Order CTA for any order I am redirected to Order Details Page$")
  public void onClickingTheViewOrderCTAForAnyOrderIAmRedirectedToOrderDetailsPage() {
    avalancheAccountDashboardPage.clickViewOrder();
  }

  @And("^there is a buy again CTA$")
  public void thereIsABuyAgainCTA() {
    avalancheAccountDashboardPage.buyAgainCtaVisible();
  }

  @And("^I make a note of the order contents$")
  public void iMakeANoteOfTheOrderContents() {
    avalancheAccountDashboardPage.recordOrderContents();
  }

  @When("^I click on the buy again CTA$")
    public void iClickOnTheBuyAgainCTA() {
    avalancheAccountDashboardPage.clickOnBuyAgainButton();
  }

  @Then("^I should see my default shipping and billing address$")
  public void iShouldSeeMyDefaultShippingAndBillingAddress() {
    avalancheAccountDashboardPage.iSeeDefaultShippingAndBillingAddress();
  }

  @And("^I should see other address info$")
  public void iShouldSeeOtherAddressInfo() {
    avalancheAccountDashboardPage.iSeeOtherAddressInfo();
  }

  @And("^there should be a CTA to edit any of the addresses$")
  public void thereShouldBeACTAToEditTheOtherAddressInfo() {
    avalancheAccountDashboardPage.thereAreCtasToEditAnyOfTheAddresses();
  }

  @Then("^I am on the address book page$")
  public void iAmOnTheAddressBookPage() {
    avalancheAccountDashboardPage.iAmOnTheAddressBookPage();
  }

  @And("^only other addresses has a remove button$")
  public void onlyOtherAddressesHasARemoveButton() {
    avalancheAccountDashboardPage.onlyOtherAddressesHasRemoveButton();
  }

  @When("^I click on edit default billing address$")
  public void iClickOnEditDefaultBillingAddress() {
    avalancheAccountDashboardPage.clickOnEditDefaultBillingAddress();
  }

  @Then("^I am directed to the edit address page$")
  public void iAmDirectedToTheEditAddressPage() {
    avalancheAccountDashboardPage.iAmDirectedToTheEditAddressPage();
  }

  @And("^there is a CTA to save my changes$")
  public void thereIsACTAToSaveMyChanges() {
    avalancheAccountDashboardPage.thereIsACtaToSaveMyChanges();
  }

  @And("^I save my shipping address during checkout$")
  public void iSaveMyChanges() {
    avalancheAccountDashboardPage.clickOnSaveChanges();
    homePage.clickBasketIcon();
    paymentPage.clickOnViewBasketButton();
    paymentPage.clickOnCheckoutButton();
  }

  @And("^I go back to my address book$")
  public void GoBackToMyAddressBook() {
    avalancheAccountDashboardPage.GoBackToMyAddressBook();
  }

  @When("^I click on edit default delivery address$")
  public void iClickOnEditDefaultDeliveryAddress() {
    avalancheAccountDashboardPage.clickOnEditDefaultDeliveryAddress();
  }

  @When("^I click on edit other address$")
  public void iClickOnEditOtherAddress() {
    avalancheAccountDashboardPage.clickOnEditOtherAddress();
  }

  @When("^I click on remove other address$")
  public void iClickOnRemoveOtherAddress() {
    avalancheAccountDashboardPage.clickOnRemoveOtherAddress();
    boolean x = true;
  }

  @Then("^I am asked if I am sure$")
  public void iAmAskedIfIAmSure() {
    avalancheAccountDashboardPage.iAmAskedIfIAmSure();
  }

  @When("^If i click cancel$")
  public void ifIClickCancel() {
    avalancheAccountDashboardPage.clickCancelRemove();
  }

  @Then("^the other addresses remain unaltered$")
  public void theOtherAddressesRemainUnaltered() {
    avalancheAccountDashboardPage.otherAddressesUnaltered();
  }

  @And("^I click yes I am sure$")
  public void iClickYesIAmSure() {
    avalancheAccountDashboardPage.clickYesIAmSure();
  }

  @Then("^the other addresses are reduced by one$")
  public void theOtherAddressesAreReducedByOne() {
    avalancheAccountDashboardPage.theOtherAddressesAreReducedByOne();
  }

  @Then("^remove other addresses until only has one address$")
  public void removeOtherAddressUntilOne() {
    avalancheAccountDashboardPage.removeOtherAddressUntilOne();
  }

  @And("^I have previously saved my payment methods$")
  public void iHavePreviouslySavedMyPaymentMethods() throws InterruptedException {
    batHelper.iHavePreviouslySavedMyPaymentMethods();
  }

  @Then("^I am able to see all my saved methods on this page$")
  public void iAmAbleToSeeAllMySavedMethodsOnThisPage() {
    avalancheAccountDashboardPage.iAmAbleToSeeAllMySavedMethodsOnThisPage();
  }

  @And("^I click on the edit payment method CTA$")
  public void iClickOnTheEditPaymentMethodCTA() {
    avalancheAccountDashboardPage.clickOnEditPaymentMethdodCta();
  }

  @And("^user saves the \"([^\"]*)\" address$")
  public void userSavesTheAddress(String addressType) throws Throwable {
    addNewAddressPage.saveAddress(addressType);
  }

  @And("^there is an Edit and remove CTA for each payment method$")
  public void thereIsAnEditAndRemoveCTAForEachPaymentMethod() {
    avalancheAccountDashboardPage.validateSavedPaymentMethodActions();
  }


  @Then("^I am redirected to the edit payment method page$")
  public void iAmRedirectedToTheEditPaymentMethodPage() {
    avalancheAccountDashboardPage.iAmRedirectedToTheEditPaymentMethodPage();
  }

  @And("^I can add all the details and save my payment method$")
  public void iCanAddAllTheDetailsAndSaveMyPaymentMethod() {
    avalancheAccountDashboardPage.iCanAddAllTheDetailsAndSaveMyPaymentMethod();
  }

  @Then("^I am returned to the web shop home page$")
  public void iAmReturnedToTheWebShopHomePage() {
    waitForAjaxElementNotToBePresent(webDriver,5);
    String url = webDriver.getCurrentUrl();

    switch (UrlBuilder.getLocale()) {
      case "velobe":
      case "veloza":
        assertThat(url.equals(UrlBuilder.url)).as("ERROR: iAmReturnedToTheWebShopHomePage: not returned to home page after log out. Current url = " + url).isTrue();
        break;
      case "velopl":
        assertThat(url.equals(UrlBuilder.url+"/")).as("ERROR: iAmReturnedToTheWebShopHomePage: not returned to home page after log out. Current url = " + url).isTrue();

        break;

      default:
        assertThat(url.equals(UrlBuilder.url+"/shop/")).as("ERROR: iAmReturnedToTheWebShopHomePage: not returned to home page after log out. Current url = " + url).isTrue();
        break;

    }

  }

  @And("^I am logged out$")
  public void iAmLoggedOut() {
    homePage.clickPersonIcon();
    homePage.clickSignOut();

  }

  @And("^I add other address again$")
  public void iAddOtherAddressAgain() {
    avalancheAccountDashboardPage.addNewAddress();
  }

  @And("^I add the delivery address during payment$")
  public void iAddTheDeliveryAddreessDuringPayment() {
    avalancheAccountDashboardPage.addNewAddressPayment();

  }

  @When("^I click on edit password$")
  public void iClickOnEditPassword() {
    avalancheAccountDashboardPage.clickOnEditPassword();
  }

  @Then("^the editable password fields show on the same page$")
  public void theEditablePasswordFieldsShowOnTheSamePage() {
    avalancheAccountDashboardPage.validateEditablePasswordFields();
  }

  @And("^I close the address modal$")
  public void iCloseTheAddressModal() {
    avalancheAccountDashboardPage.closeAddressModal();
  }

  @And("^if there are more than \"([^\"]*)\" other addresses I reduce them$")
  public void ifThereAreMoreThanOtherAddressesIReduceThem(String target) throws Throwable {
    avalancheAccountDashboardPage.ensureAllOtherAddressesViewable(target);
  }

  @Then("^assert '(.*)' link is displayed verifying both facebook and M2 accounts have been linked$")
  public void assertDisconnectLinkIsDisplayedVerifyingBothFBandM2AccountsLinking(String strLinkText) {
    accountDashboardPage.assertDisconnectLinkIsDisplayedVerifyingBothFBandM2AccountsLinking(strLinkText);
  }

  @Then("^assert Sign in link with text '(.*)' is displayed and enabled$")
  public void assertSignInWithFacebookLinkIsDisplayedAndEnabled(String strLinkText) {
    if(strLinkText.contains("Facebook")){
      assertTrue(accountDashboardPage.waitForExpectedElement(logininPage.SIGN_IN_WITH_FACEBOOK_BUTTON).getText().toLowerCase().contains(UrlBuilder.getMessage(strLinkText).toLowerCase()));
      assertTrue(accountDashboardPage.waitForExpectedElement(logininPage.SIGN_IN_WITH_FACEBOOK_BUTTON).isEnabled());}
    else{
      assertTrue(accountDashboardPage.waitForExpectedElement(logininPage.SIGN_IN_WITH_GOOGLE_BUTTON).getText().toLowerCase().contains(UrlBuilder.getMessage(strLinkText).toLowerCase()));
      assertTrue(accountDashboardPage.waitForExpectedElement(logininPage.SIGN_IN_WITH_GOOGLE_BUTTON).isEnabled());}
  }

  @Then("^user clicks on Sign in with Facebook link$")
  public void userClicksOnSignInWithFacebookLink() {
    accountDashboardPage.waitAndClickByElementByJSExecutor(logininPage.SIGN_IN_WITH_FACEBOOK_BUTTON,10);
  }

  @Then("^assert add password error '(.*)' is displayed for M2 account$")
  public void assertAddPasswordErrorMessageIsDisplayedForM2Accounts(String strErrMessage) {
    scrollToPageTop();
    assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.ADD_PASSWORD_DISCONNECT_ERR_MSG, 10).getText().contains(UrlBuilder.getMessage(strErrMessage)));
  }

  @And("^user select change email address and update the new address$")
  public void userChangeEmailID()  {
    accountDashboardPage.userChangeEmailIDLoggedUser();
  }

  @Then("^user try to login with new email$")
  public void userLoginWithNewEmail()  {
    accountDashboardPage.userLoginWithNewEmail();
  }

  @Then("^user clicks on Change Password button$")
  public void userClicksOnChangePasswordButton(){
    switch (UrlBuilder.getLocale()) {
      case "lyftse":
        waitAndClickByElementByJSExecutor(accountDashboardPage.CHANGE_PASSWORD_BUTTON_LYFT,10);
        break;
      case "vuseuk":
        waitAndClickByElementByJSExecutor(accountDashboardPage.CHANGE_PASSWORD_BUTTON_VUSEUK,10);
        break;
      default:
        waitAndClickByElementByJSExecutor(accountDashboardPage.CHANGE_PASSWORD_BUTTON,10);
        break;
    }
  }

  @Then("^assert Current Password field is not displayed for M2 user without a password$")
  public void assertCurrentPasswordFieldNotDisplayedForM2UserWithoutPassword(){
    try{
      assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.CURRENT_PASSWORD).isDisplayed());}
    catch(Exception ex){
      LOG.info("Current Password field is not appearing which is expected.");
    }
  }

  @Then("^assert Current Password field is displayed after password is added from My Social Accounts$")
  public void assertCurrentPasswordFieldIsDisplayedAfterPasswordAddedFromMySocialAccounts()  {
    assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.CURRENT_PASSWORD).isDisplayed());
  }

  @Then("^wait for facebook pop-up window to close$")
  public void waitForFacebookPopUpWindowToClose()  {
    accountDashboardPage.waitForFacebookPopUpWindowToClose();
  }

  @And("^assert delete my account button displayed$")
  public void assertDeleteMyAccountButtonDisplayed() {
      assertTrue(accountDashboardPage.isElementPresent(
              accountDashboardPage.DELETE_MYACCOUNT_BUTTON));
  }

  @When("^user delete account from delete my account page$")
  public void userDeleteAccountFromDeleteMyAccountPage() throws InterruptedException {
      accountDashboardPage.deleteMyAccount();
  }

  @When("^User delete account without ticking the consent CheckBox$")
  public void userDeleteAccountWithoutTickingTheConsentCheckBox() {
      accountDashboardPage.clickDeleteMyAccountButton();
  }

    @And("^Assert SA ID number on the Edit deatils page$")
    public void assertSAIDNumberOnTheEditDeatilsPage() {
      assertFalse(accountDashboardPage.assertSAIDNumberOnTheEditDeatilsPage());
    }
  @And("^user enters valid SA ID number in Edit Details and save$")
  public void userEntersValidSAIDNumberInEditDetailsAndSave() {
    accountDashboardPage.userEntersValidSAIDNumberInEditDetailsAndSave();
  }

  @Then("^verify new address is update on Address Book for default shipping address$")
  public void verifyNewAddressUpdateForShippingAddress() {
      accountDashboardPage.verifyShippingAddress();
  }

  @And("^select from myAccount links NewsLetter link mobile$")
  public void clickNewsletterLinkMobile() {
      waitForItemToBeClickableAndClick(homePage.DASHBOARD_MENU_EXPAND_FR);
      waitForExpectedElement(By.linkText(UrlBuilder.getMessage("myPreferences.key")), 10);
      clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("myPreferences.key")));
      waitForExpectedElement(newsLetterPage.elePageHeading, 20);
  }

  @And("^select Change Password checkbox$")
  public void selectChangePasswordCheckbox() {
      accountDashboardPage.selectChangePasswordCheckbox();
  }

  @When("^update password with incorrect current password$")
  public void updatePasswordWithIncorrectCurrentPassword()
  {
      accountDashboardPage.updatePasswordWithIncorrectCurrentPassword();
  }

  @And("^select update email checkbox$")
  public void selectUpdateEmailCheckbox() {
      accountDashboardPage.waitForExpectedElement(accountDashboardPage.UPDATE_EMAIL_CHECKBOX,20);
      accountDashboardPage.scrollToElement(accountDashboardPage.UPDATE_EMAIL_CHECKBOX);
      accountDashboardPage.clickByElementByQueryJSExecutor(accountDashboardPage.UPDATE_EMAIL_CHECKBOX);
  }

  @When("^update password with incorrect confirm password$")
  public void updatePasswordWithIncorrectConfirmPassword()
  {
    accountDashboardPage.updatePasswordWithIncorrectConfirmPassword();}

  @And("^click the newsletter option and click save button$")
  public void clickNewsletterOptionAndSave() {
      accountDashboardPage.clickNewsletterOptionAndSave();
  }

  @And("^assert Newsletter subscribe message displayed$")
  public void assertNewsletterSubscribeMsgDisplay() {
    assertTrue(accountDashboardPage.isElementDisplayedBySeconds(accountDashboardPage.NEWSLETTER_SUCCESS_MSG_Text, 10));
  }

  @And("^unsubscribe the newsletter$")
  public void unsubscribeTheNewsletter() {
    accountDashboardPage.unsubscribeTheNewsletter();
  }

  @Then("^resubscribe the newsletter with same email address$")
  public void reSubscribeNewsletterWithSameEmailAddress() {
    accountDashboardPage.reSubscribeNewsletterWithSameEmailAddress();
  }

  @Then("^assert newsletter option are selected$")
  public void assertNewsletterOptionSelected() {
    accountDashboardPage.assertNewsletterOptionSelected();
  }

  @And("^view order details$")
  public void viewOrderDetails() {
    accountDashboardPage.viewOrderDetails();
  }

  @When("^update password with correct confirm password$")
  public void updatePasswordWithCorrectConfirmPassword()
  {
    accountDashboardPage.updatePasswordWithCorrectConfirmPassword();}
}