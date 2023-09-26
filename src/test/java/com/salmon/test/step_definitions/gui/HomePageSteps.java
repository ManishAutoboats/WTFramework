package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.Lyft.LyftLabPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage;
import com.salmon.test.page_objects.gui.newsLetter.NewslettersPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.page_objects.gui.gloIt.GloItHomePage.MY_ACCOUNT_LINK_PL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HomePageSteps {

    private static final By GLO_ERROR_MESSAGE = By.cssSelector("#maincontent > div.page.messages > div:nth-child(2) > div > div,div.page.messages > div:nth-child(2) > div > div > div");
    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    private static final By CLOSE_POPUP = By.cssSelector("#close_popup");
    private static final By NEWSLETTER_LINK = By.cssSelector("#account-nav > ul > li:nth-child(4) > a");
    private static final By ENTRY_AGE_GATE_POP_UP_OVERLAY = By.cssSelector("#entry-age-gate-pop-up-overlay");
    private static final By AGE_VERIFY_INPUT_MX = By.cssSelector("div.date-select");
    private static final By AGE_VERIFY_INPUT_VYPEIT = By.cssSelector("div.date-select");
    private static final By NEWSLETTER_MARKETING_OPTION = By.cssSelector("#account-nav > ul > li:nth-child(7) > a");
    private static final By NEWSLETTER_MARKETING_OPTION_VUSEIT = By.cssSelector("#account-nav > ul > li:nth-child(8) > a");
    private static final By HOW_IT_WORKS = By.cssSelector("div.howworks > div");
    private static final By INSTAGRAM_ICON = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(3) > div > a.icon-instagram");
    private static final By YOUTUBE_ICON = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(3) > div > a.icon-youtube");
    private static final By FACEBOOK_ICON = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(3) > div > a.icon-facebook");
    private static final By TWITTER_ICON = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(3) > div > a.icon-twitter");
    private static final By PAYMENT_ICON = By.cssSelector("div.paymentbar img.pagebuilder-mobile-hidden");
    private static final By STICKY_BAR = By.cssSelector("#html-body > div.page-wrapper > footer:nth-child(4) > div");
    private static final By FLAVOUR_SECTION = By.cssSelector("div.some-flavour");
    private static final By INSIDERS_CLUB_FOOTER =By.cssSelector("div.page-wrapper > footer > div.footer.content  ul > li:nth-child(6) > a");
    private static final By FREE_GIFT_MESSAGE= By.cssSelector("tbody:nth-of-type(2) > .flavour-.item-info.product-card > .col.product-item-details > .cart.item.message.notice");
    private static final By GLOIT_FREE_GIFT_MESSAGE= By.cssSelector("div[role='alert'] > div > div");

    private HomePage homePage;
    private WhatWeDoPage whatWeDoPage;
    private NewslettersPage pgeNewsletters;
    private OrderSuccessPage orderSuccessPage;
    private PDP pdp;
    private BATHelper batHelper;
    private PLP plp;
    private LyftLabPage lyftLabPage;
    private SearchResult searchResult;
    private OrderHistoryPage orderHistoryPage;
    private boolean userBuyingNonSubItems = false;
    private LogininPage loginPage;
    private PaymentPage paymentPage;
    private ScenarioContext scenarioContext;
    private AccountDashboardPage accountPage;
    private GlobalSubscriptionsPage globalSubscriptionsPage;
    private SoftAssertions softAssertions;

    public HomePageSteps(HomePage homePage, WhatWeDoPage whatWeDoPage, NewslettersPage pgeNewsletters, PDP pdp,
                         BATHelper batHelper, OrderSuccessPage orderSuccessPage, PLP plp, LyftLabPage lyftLabPage,
                         SearchResult searchResult, LogininPage loginPage, PaymentPage paymentPage,
                         AccountDashboardPage accountPage, GlobalSubscriptionsPage globalSubscriptionsPage,ScenarioContext scenarioContext,
                         SoftAssertions softAssertions) {
        this.homePage = homePage;
        this.whatWeDoPage = whatWeDoPage;
        this.pgeNewsletters = pgeNewsletters;
        this.pdp = pdp;
        this.batHelper = batHelper;
        this.orderSuccessPage = orderSuccessPage;
        this.plp = plp;
        this.lyftLabPage = lyftLabPage;
        this.searchResult = searchResult;
        this.orderHistoryPage=orderHistoryPage;
        this.loginPage = loginPage;
        this.paymentPage = paymentPage;
        this.accountPage = accountPage;
        this.scenarioContext = scenarioContext;
        this.globalSubscriptionsPage=globalSubscriptionsPage;
        this.softAssertions=softAssertions;
    }

    // Use the below to set environment (UAT / LIVE etc)
    // Generally we are just using UAT and LIVE
    // Epok site feature has separate URL setting, so that's independent
    @Given("^user navigates to privacy policy page$")
    public void userNavigatesToPrivacyPage() {
        // LIVE / PRODUCTION
        //homePage.getWebDriver().get("https://www.govype.com/gb/en/privacy-policy");
        // UAT
        //homePage.getWebDriver().get("https://www-eu-ci3-global-vype.non-prod.marketing.bat.net/gb/en/");
        //homePage.getWebDriver().get("https://www-eu-uat-global-vype.non-prod.marketing.bat.net/uk/privacy-policy/");
        // FRENCH UAT
        //homePage.getWebDriver().get("https://www-eu-uat-global-vype.non-prod.marketing.bat.net/fr/fr/privacy-policy");
        // DENMARK UAT
        //homePage.getWebDriver().get("https://www-eu-uat-global-vype.non-prod.marketing.bat.net/dk/da/privacy-policy");
        // Global CI
        //homePage.getWebDriver().get("https://www-eu-ci-global-vype.non-prod.marketing.bat.net/gb/en/privacy-policy-cookie-restriction-mode/");
        //"https://www-eu-ci-global-vype.non-prod.marketing.bat.net/uk/privacy-policy-cookie-restriction-mode/");
        //CI4
        homePage.getWebDriver().get("https://www-eu-ci4-global-vype.non-prod.marketing.bat.net/gb/en/privacy-policy-cookie-restriction-mode/");
    }

    @When("^i click what we do on home page$")
    public void i_click_what_we_do_on_home_page() {
        //homePage.clickWhatWeDo();
    }

    @Then("^i can see feature projects$")
    public void i_can_see_feature_projects() throws Throwable {
        assertTrue(whatWeDoPage.featuredProjectsIsDisplayed());
    }

    @Given("^Bat expected homepage title is '(.*)'$")
    public void iNavigateToTheBATPage(String expectedPageHeader) {
        LOG.info("break point");
        homePage.assertTrueWithCustomError(expectedPageHeader, homePage.getWebDriver().getTitle());
    }

    @And("^I scrape BAT homepage for all links$")
    public void iScrapeBATHomepageForAllLinks() {
        homePage.getAllLinks();
    }

    @And("^I scrape BAT homepage for all buttons$")
    public void iScrapeBATHomepageForAllButtons() {
        homePage.getAllButtons();
    }

    @And("^I scrape BAT homepage for all images$")
    public void iScrapeBATHomepageForAllImages() {
        homePage.getAllImages();
    }

    @And("^I scrape BAT homepage for all tables$")
    public void iScrapeBATHomepageForAllTables() {
        homePage.getAllTables();
    }

    @And("^I scrape BAT homepage for all lists$")
    public void iScrapeBATHomepageForAllLists() {
        homePage.getAllLists();
    }

    @Then("^expected header link of '(.*)' is present$")
    public void thenExpectedHeaderLinksArePresent(String expectedLink) {
        assertTrue(homePage.getWebDriver().findElement(By.linkText(expectedLink)).isDisplayed());
    }

    @Then("^ensure text of '(.*)' present$")
    public void ensureTextOfSignUpForOurNewsletterPresent(String textToBePresent) {
        String expectedText = textToBePresent;
        homePage.assertTrueWithCustomError(expectedText, homePage.getTextFor(homePage.newsLetterText));
    }

    @And("^ensure newsletter element is present on page$")
    public void ensureNewsletterElementIsPresentOnPage()  {
        homePage.ensureNewsletterElementIsPresentOnPage();
    }

    @And("^ensure newsletter sign up button present on page$")
    public void ensureNewsletterSignUpButtonPresentOnPage() {
        homePage.ensureNewsletterSignUpButtonPresentOnPage();
    }

    @And("^enter incorrectly formatted email and verify error message presented$")
    public void enterIncorrectlyFormattedEmailAndVerifyErrorMessagePreesnted() throws Throwable {
        String incorrectlyFormattedEmail = "customeremail";
        String expectedErrorMessage = "Please enter a valid email address (Ex: johndoe@domain.com).";
        homePage.waitClearAndEnterText(homePage.newsLetterInputBox, incorrectlyFormattedEmail);
        Thread.sleep(3000);
        homePage.clickElementByQueryJSExecutor(homePage.waitForExpectedElement(homePage.newsLetterSubscribeButton));
        LOG.info(homePage.getTextFor(homePage.newsLetterInvalidEmailError));
        homePage.assertTrueWithCustomError(expectedErrorMessage, homePage.getTextFor(homePage.newsLetterInvalidEmailError));
    }

    @Given("^user selects over 18 age confirmation option$")
    public void userSelectsOverAgeConfirmationOption() {
        homePage.tryClickIAmOver18(); // Uk and others?
    }

    @Given("^user selects not over 18 age confirmation option, confirm text and google redirection$")
    public void userSelectsNotOverAgeConfirmationOption() throws Throwable {
        String expectedText = "You must be over 18 to use this website, you will now be redirected to Google";
        switch (UrlBuilder.getLocale()) {
            case "ukLive":
            case "uk":
            case "vuseuk":
            case "vuseuklive":
            case "velode":
                homePage.waitForExpectedElement(By.cssSelector("#btn-entry-age-deny"), 20);
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("#btn-entry-age-deny"));
                break;
            case "de":
                LOG.info("Age denial for Vype DE.");
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("#btn-entry-age-deny"));
                break;
            case "mx":
            case "vusemx":
            case "vuseco":
                LOG.info("not applicable for CO / MX");
                break;
            case "glode":
                homePage.clickDenyAgeGateCta();
                break;
            case "velopl":
                homePage.waitForExpectedElement(By.cssSelector("#btn-entry-age-deny"), 10).click();
                String actualMessage = homePage.waitForExpectedElement(By.cssSelector("div#entry-age-underage-message-wrapper  p"),5).getText();
                Assert.assertEquals(UrlBuilder.getMessage("ageDenyMsg.key"),actualMessage);
                break;
            default:
                homePage.waitForExpectedElement(By.cssSelector("#btn-entry-age-deny"), 10).click();
                break;
        }
        Thread.sleep(6000);
        if(UrlBuilder.getLocale().equals("vuseza")) {
            homePage.waitForAjaxElementNotToBePresent(getWebDriver(),5);
            //assertTrue(homePage.getWebDriver().getCurrentUrl().contains("www.google.co.za")); comment as Remote browser not getting handle getCurrentUrl for different region on ADO
            assertTrue(homePage.isElementDisplayedBySeconds(homePage.GOOGLE_PAGE,10));
        }
        else if (!UrlBuilder.getLocale().equals("mx") && (!UrlBuilder.getLocale().equals("vuseco"))&&(!UrlBuilder.getLocale().equals("vusemx"))&&(!UrlBuilder.getLocale().equals("vuseza"))) {
            homePage.waitForAjaxElementNotToBePresent(getWebDriver(),6);
            if(UrlBuilder.getLocale().equals("vuseit"))
            {
                homePage.waitForPage(10);
            }
            assertTrue(homePage.getWebDriver().getCurrentUrl().contains("www.google.com"));
        }
    }

    @Given("^mobile user selects not over 18 age confirmation option, confirm text and google redirection$")
    public void mobileUserSelectsNotOverAgeConfirmationOption() throws Throwable {
        homePage.waitForExpectedElement(By.cssSelector("#btn-entry-age-deny")).click();
        Thread.sleep(5000);
        assertTrue(homePage.getWebDriver().getCurrentUrl().contains("www.google.com"));
    }

    @And("^age confirmation box is no longer displayed$")
    public void ageConfirmationBoxIsNoLongerDisplayed() {
        if(UrlBuilder.isIPhone() && UrlBuilder.getLocale().equals("it"))
        {
            assertFalse(homePage.getWebDriver().findElement(homePage.buttonAgeAllow).isDisplayed());
        }
        else {
            assertFalse(homePage.getWebDriver().findElement(homePage.buttonAgeAllow).isSelected());
        }
    }

    @And("^assert entry age module is presented to user$")
    public void assertEntryAgeModuleIsPresentedToUser() throws Throwable {
        Thread.sleep(4000);
        String expectedText = UrlBuilder.getMessage("entryAgeVerificationMsg.key");
        WebElement entryAgeElementText = homePage.waitForExpectedElement(By.cssSelector(".entry-age-confirmation>p"), 10);
        homePage.assertTrueWithCustomError(expectedText, entryAgeElementText.getText());
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
            case "vuseco":
                assertTrue(homePage.getWebDriver().findElement(AGE_VERIFY_INPUT_MX).isDisplayed());
                break;
            case "fr":
                break;
            case "vusefr":
                assertTrue(homePage.getWebDriver().findElement(By.cssSelector("#btn-entry-age-allow")).isDisplayed());
                break;
            default:
                assertTrue(homePage.getWebDriver().findElement(By.cssSelector("#btn-entry-age-allow")).isDisplayed());
                assertTrue(homePage.getWebDriver().findElement(By.cssSelector("#btn-entry-age-deny")).isDisplayed());
        }
    }

    @And("^tearDownBAT$")
    public void teardownBAT() {
        homePage.getWebDriver().manage().deleteAllCookies();
        homePage.getWebDriver().navigate().refresh();
    }

    public void enterTextIntoNewsLetterAndSubmit(String emailToEnter) throws InterruptedException {
        homePage.waitClearAndEnterText(homePage.newsLetterInputBox, emailToEnter);
        Thread.sleep(2000);
    }

    @And("^enter e-mail address already used and assert '(.*)' message is presented$")
    public void enterEMailAddressAlreadyUsedAndAssertThisEmailAddressIsAlreadySubscribedMesasgeIsPresented(String expectedMessage) throws Throwable {
        if (homePage.getWebDriver().getCurrentUrl().contains("/mx/es")) {
            homePage.waitForExpectedElement(pgeNewsletters.txtFirstName).clear();
            homePage.waitForExpectedElement(pgeNewsletters.txtLastName).clear();
            homePage.waitForExpectedElement(pgeNewsletters.txtFirstName).sendKeys("Testing");
            homePage.waitForExpectedElement(pgeNewsletters.txtLastName).sendKeys("tesingSurname");
            homePage.waitForExpectedElement(pgeNewsletters.chkAuthorizeEmail).click();
            enterTextIntoNewsLetterAndSubmit(UrlBuilder.getMessage("loginValidEmail.key"));
            homePage.waitForExpectedElement(pgeNewsletters.btnSubscribeNewsletterPopUpMX).click();
            homePage.waitForExpectedElement(pgeNewsletters.eleNewsletterErrMessage, 10);
            String actualMessage = homePage.waitForExpectedElement(pgeNewsletters.eleNewsletterErrMessage).getText();
            Thread.sleep(2000);
            homePage.assertTrueWithCustomError(UrlBuilder.getMessage("emailAddressAlreadySubscribeGuest.key"), actualMessage);
        } else if (UrlBuilder.getLocale().equals("uk")) {
            String existingEmail = "msmith@salmon.com";
            enterTextIntoNewsLetterAndSubmit(existingEmail);
            Thread.sleep(3000);
            homePage.clickByElementByQueryJSExecutor(By.cssSelector("#email_marketing"));
            homePage.waitForExpectedElement(By.cssSelector("#newsletter-validate-detail > fieldset > div.actions > button > span")).click();

            Thread.sleep(3000);
            String actualMessage = homePage.waitForExpectedElement(By.cssSelector("#newsletter_error")).getText();   //"body > div.page-wrapper > header > div.page.messages > div:nth-child(2) > div > div > div")).getText();
            homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedMessage), actualMessage);
        } else {
            homePage.waitForExpectedElement(By.cssSelector("#newsletter-validate-detail > fieldset > div.newsletter-agree.required > label")).click();
            String existingEmail = "msmith@salmon.com";
            enterTextIntoNewsLetterAndSubmit(existingEmail);
            homePage.waitForExpectedElement(By.cssSelector("#newsletter-validate-detail > fieldset > div.actions > button > span")).click();
            Thread.sleep(3000);
            String actualMessage = homePage.waitForExpectedElement(By.cssSelector("div[data-bind='html: message.text']")).getText();   //"body > div.page-wrapper > header > div.page.messages > div:nth-child(2) > div > div > div")).getText();
            homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedMessage), actualMessage);
        }
    }

    @And("^enter e-mail '(.*)' address and assert '(.*)' message is presented$")
    public void enterEMailAddressAndAssertThanksForSubscribingMessageIsPresented(String emailAddress, String expectedMessage) throws Throwable {
        enterTextIntoNewsLetterAndSubmit(emailAddress);
        homePage.assertTrueWithCustomError(expectedMessage, homePage.getTextFor(homePage.newsLetterInvalidEmailError));
    }

    @And("^enter unique e-mail address used and assert '(.*)' message is presented$")
    public void enterUniqueEMailAddressUsedAndAssertThanksForSubscribingMessageIsPresented(String expectedMessage) throws Throwable {
        Thread.sleep(4000);
        String actualMessage;
        if (homePage.getWebDriver().getCurrentUrl().contains("/mx/es")) {
            actualMessage = homePage.waitForExpectedElement(By.cssSelector("div.message-success.success.message div")).getText();
            homePage.assertTrueWithCustomError(UrlBuilder.getMessage("ThankyouforSubscriptionGuest.key"), actualMessage);
        } else if (UrlBuilder.getLocale().equals("vypeuat") || UrlBuilder.getLocale().equals("uk")) {
            actualMessage = homePage.waitForExpectedElement(By.cssSelector("div.message.success")).getText();
            homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedMessage), actualMessage);
            homePage.waitForExpectedElement(homePage.newsLetterCloseButton).click();
        } else {
            actualMessage = homePage.waitForExpectedElement(By.cssSelector("div.page.messages > div:nth-child(2) > div > div > div")).getText(); //"body > div.page-wrapper > header > div.page.messages > div:nth-child(2) > div > div > div")).getText();
            LOG.info(UrlBuilder.getMessage(expectedMessage));
            LOG.info(actualMessage);
            homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedMessage), actualMessage);
        }
    }

    @And("^user clicks the person icon$")
    public void userClicksThePersonIcon() {
        homePage.clickPersonIcon();
    }

    @And("^user hovers over the person icon$")
    public void userHoversThePersonIcon() {
        homePage.hoverPersonIcon();
    }

    @And("^user hovers over the plp item$")
    public void userHoversOnPlpItem() {
        homePage.userHoversOnPlpItem();
    }

    @And("^user clicks the myAccount edit link - lyft$")
    public void userClicksonMyAccountEditLink() {
        homePage.clicksOnMyAccountEditLink();
    }

    @And("^user clicks the person icon -lyft$")
    public void userClicksThePersonIcon_lyft() {
        homePage.clickPersonIcon_lyft();
    }

    @And("^user clicks the blog icon -lyft$")
    public void userClicksTheBlogIcon_lyft() {
        homePage.clickOnBlogIcon_lyft();
    }

    @And("^user clicks the mobile person icon$")
    public void userClicksTheMobilePersonIcon() throws Throwable {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(homePage.getWebDriver(), 7);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.M_PERSONICON));
        try {
            homePage.waitForExpectedElement(homePage.M_PERSONICON).click();
        } catch (Exception e) {
            LOG.info("/n ****** Couldn't click - waiting 2 seconds then re-attempting");
            Thread.sleep(2000);
            homePage.waitForExpectedElement(homePage.M_PERSONICON).click();
        }
    }

    @Then("^ensure basket icon is displayed$")
    public void ensureBasketIconIsDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
            case "pl":
                assertTrue(homePage.waitForExpectedElement(homePage.BASKET_ICON).isDisplayed());
                break;
            case "vuseco":
                try {
                    assertTrue(homePage.waitForExpectedElement(homePage.BASKET_ICON_CO).isDisplayed());
                }catch (Exception e){
                    assertTrue(homePage.waitForExpectedElement(homePage.BASKET_ICON).isDisplayed());
                }
                break;
            case "glojp":
                assertTrue(homePage.waitForExpectedElement(orderHistoryPage.BASKET_ICON).isDisplayed());
                break;
            default:
                assertTrue("\n **** ERROR :: Basket Icon is missing", homePage.isElementDisplayedBySeconds(homePage.BASKET_ICON,10));
        }
    }

    @And("^click on basket icon$")
    public void clickOnBasketIcon() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
            case "fr":
            case "vusefr":
            case "vypeit":
            case "vuseit":
            case "vuseco":
            case "vusede":
                homePage.openBasketifNotOpenLogic();
                break;
            case "velobe":
            case "velopl":
                homePage.clickOnBasketIcon();
                break;
            case "glode":
                homePage.openBasketifNotOpenLogic();
                break;
            default:
                homePage.clickOnBasketIcon();
        }
    }

    @And("^users clicks on the store locator icon$")
    public void storeLocatorIcon(){
        homePage.clickStoreLocatorIcon();
    }

    @And("^basket is empty message is presented$")
    public void basketIsEmptyMessageIsPresented() {
        homePage.assertMessageForEmptybasketPage();
    }

    @And("^logo is displayed$")
    public void logoIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.logo).isDisplayed());
    }

    @Then("^assert logo is not displayed$")
    public void assertLogoIsNotDisplayed() {
        assertFalse(homePage.waitForExpectedElement(ENTRY_AGE_GATE_POP_UP_OVERLAY)
                .findElements(homePage.logo).size() > 0);
    }

    @And("^assert pageTitle is displaying '(.*)'$")
    public void assertPageTitleIsHomePage(String expectedTitle) throws Throwable {
        Thread.sleep(5000);
        LOG.info("Testing breakpoint");
        LOG.info("Page title : " + homePage.getWebDriver().getTitle());
        homePage.assertTrueWithCustomError(expectedTitle, homePage.getCurrentPageTitle());
    }

    @And("^header class is displayed$")
    public void headerClassIsDisplayed() {
        switch(UrlBuilder.getLocale()) {
            case "it":
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.HEADER_CLASS_GLOIT, 3));
                break;
            default:
                assertTrue(homePage.waitForExpectedElement(homePage.headerClass).isDisplayed());
        }
    }

    @And("^sandwich menu icon is displayed$")
    public void sandwichMenuIconIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.SANDWICH_MENU_ICON).isDisplayed());
    }

    @And("^person icon is displayed$")
    public void personIconIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.PERSON_ICON).isDisplayed());
    }

    @And("^favorite icon is displayed$")
    public void favoriateIconIsDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
            case "uk":
            case "vuseuk":
            case "vusefr":
            case "vusedk":
            case "vuseza":
            case "vusede":
            case "vuseit":
                break;
            default:
                assertTrue(homePage.waitForExpectedElement(homePage.favIcon).isDisplayed());
        }
    }

    @And("^backupRestore icon is displayed$")
    public void backuprestoreIconIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.backupRestore).isDisplayed());
    }

    @And("^chat icon is displayed$")
    public void chatIconIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.chatIcon).isDisplayed());
    }

    @And("^search icon is displayed$")
    public void searchIconIsDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
            case "vusede":
            case "vuseza":
                assertTrue(homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX_UK).isDisplayed());
                break;
            case "ukLive":
            case "vuseuklive":
            case "pl":
                assertTrue(homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX_UK).isDisplayed());
                break;
            case "vypeit":
            case "dk":
            case "vusedk":
            case "vuseit":
            case "glode":
            case "kz":
                homePage.waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key"))).isDisplayed();
                break;
            case "mx":
            case "vusemx":
                homePage.waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key"))).isDisplayed();
                break;
            case "glojp":
                assertTrue(homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX_JP).isDisplayed());
                break;
            case "it":
                break;
            case "vusefr":
                assertTrue(homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX_FR).isDisplayed());
                break;
            default:
                assertTrue(homePage.waitForExpectedElement(homePage.searchIcon).isDisplayed());
        }
    }

    @And("^user hovers over Shop Devices and verifies the categories in it$")
    public void vypeShopDevices(DataTable table){
        homePage.vuseShopDevicesDE(table);
    }

    @And("^should see Delivery message banner near the header$")
    public void shouldSeeDeliveryMessageBannerNearTheHeader() {
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
            case "vuseit":
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.headerDeliveryMessage_VUSEFR, 5));
                assertTrue(homePage.waitForExpectedElement(homePage.headerDeliveryMessage_VUSEFR).getText().contains(UrlBuilder.getMessage("deliveryMessageText.key")));
                LOG.info("\n ****** Delivery message " + homePage.waitForExpectedElement(homePage.headerDeliveryMessage_VUSEFR).getText());
                break;
            case "uk":
            case "ukLive":
            case "vuseuk":
            case "vuseuklive":
                assertTrue(homePage.isHeaderDeliveryMessageDisplay());
                assertTrue(homePage.getHeaderDeliveryMessageText().contains(UrlBuilder.getMessage("deliveryMessageText.key")));
                LOG.info("\n ****** Delivery message " + homePage.getHeaderDeliveryMessageText());
                break;
            default:
                LOG.info("Not applicable for other locales.");
        }
    }

    @And("^footer div is present and displayed$")
    public void footerDivIsPresentAndDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.footerContentBlock).isDisplayed());
    }

    @And("^user see the phone number in footer$")
    public void footerPhoneNumberIsPresent(){
        homePage.isPhoneNumberPresent();
    }

    @And("^user see the email in footer$")
    public void footerEmailIsPresent(){
        homePage.isEmailPresent();
    }

    @And("^user asserts trust badge is present$")
    public void trustBadgePresent(){
        homePage.trustBadgePresent();
    }

    @And("^user expands the badge$")
    public void expandBadge(){
        homePage.expandBadge();
    }

    @And("^footer sublinks displayed$")
    public void footerSublinksDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "ukLive":
            case "vuseuklive":
            case "vypeit":
            case "vuseit":
                assertTrue(homePage.waitForExpectedElement(homePage.footerLinkClusterBlockUK).isDisplayed());
                break;
            case "uk":
            case "vuseuk":
            case "vuseza":
                assertTrue(homePage.waitForExpectedElement(homePage.footerLinkClusterBlockUK, 15).isDisplayed());
                break;
            case "mx":
            case "vusemx":
            case "vuseco":
                assertTrue(homePage.waitForExpectedElement(homePage.FOOTER_LINKS_MX, 15).isDisplayed());
                break;
            default:
                assertTrue(homePage.waitForExpectedElement(homePage.footerLinkClusterBlock).isDisplayed());
        }
    }

    @And("^validates news section is present$")
    public void vuseNewsSection(){
        homePage.vuseNewsSection();
    }

    @And("^social media icons displayed$")
    public void socialMediaIconsDisplayed() {
        if (UrlBuilder.getLocale().equals("vypeuat") || UrlBuilder.getLocale().equals("vuseuk")  || UrlBuilder.getLocale().equals("vypeit")||UrlBuilder.getLocale().equals("vuseit")) {
            assertTrue(homePage.waitForExpectedElement(By.xpath("(//div[@class='footer_top-cmsblock']//div[@class='social-footer'])[2]")).isDisplayed());
        } else if (UrlBuilder.getLocale().equalsIgnoreCase("co")) {
            assertTrue(homePage.waitForExpectedElement(FooterPageSteps.FOOTER_SOCIAL_MEDIA_ICONS).isDisplayed());}
        else{
            assertTrue(homePage.waitForExpectedElement(homePage.footerSocialMediaIcons).isDisplayed());
        }
    }

    @And("^payment content block is preset and displayed$")
    public void paymentDivIsPresetAndDisplayed() {
        homePage.paymentDivIsPresentAndDisplayed();
    }

    @And("^privacy policy displayed$")
    public void privacyPolicyDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "ukLive":
            case "vuseuklive":
            case "mx":
            case "vusemx":
                assertTrue(homePage.checkPageTitle(UrlBuilder.getMessage("privacyPolicyPageTitleLive.key")));
                break;
            case "uk":
            case "vuseuk":
            case "dk":
            case "vusedk":
                assertTrue(homePage.checkPageTitle(UrlBuilder.getMessage("privacyPolicyPageTitle.key")));
                break;
            case "vuseit":
                break;
            default:
                assertTrue(homePage.waitForExpectedElement(homePage.footerPrivacyContentBlock).isDisplayed());
        }
    }

    @And("^validates minibanner section is present$")
    public void vypeMiniBanner(){
        homePage.vypeMiniBanner();
    }

    @And("^user asserts trust shop logo is displayed$")
    public void trustLogo(){
        homePage.trustLogo();
    }

    @And("^footer sublinks are displayed$")
    public void vuseFooterLinks(){
        homePage.vuseFooterlinks();
    }

    @And("^newsletter content block displayed$")
    public void newsletterContentBlockDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.newsLetterContentBlock).isDisplayed());
    }

    private void performSearch(String searchTerm) {
        pdp.performSearch(searchTerm);
    }

    private void enterSearch(String searchTerm) throws InterruptedException {
        homePage.clickOnLogo();
        Thread.sleep(5000);
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
                homePage.waitForExpectedElement(homePage.MXsearchIcon).click();
                homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX, 30);
                homePage.enterDataAndWait(homePage.SEARCH_INPUTBOX, searchTerm);
                break;
            case "vypeit":
            case "vuseit":
                homePage.waitForExpectedElement(homePage.VypeITsearchIcon).click();
                homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX, 30);
                homePage.enterDataAndWait(homePage.SEARCH_INPUTBOX, searchTerm);
                break;
            case "uk":
            case "vuseuk":
                homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX_UK, 30);
                homePage.enterDataAndWait(homePage.SEARCH_INPUTBOX_UK, searchTerm);
                break;
            case "ukLive":
            case "vuseuklive":
            case "vusefr":
            case "vuseza":
                homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX_UK, 30);
                homePage.enterDataAndWait(homePage.SEARCH_INPUTBOX_UK, searchTerm);
                break;
            case "nl":
                homePage.clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
                homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX, 30);
                homePage.enterDataAndWait(homePage.SEARCH_INPUTBOX, searchTerm);
                break;
            case "dk":
            case "vusedk":
                homePage.clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
                homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX, 30);
                homePage.enterDataAndWait(homePage.SEARCH_INPUTBOX, searchTerm);
                break;
            default:
                homePage.waitForExpectedElement(homePage.searchIcon).click();
                homePage.waitForExpectedElement(homePage.SEARCH_INPUTBOX, 30);
                homePage.enterDataAndWait(homePage.SEARCH_INPUTBOX, searchTerm);
        }
    }

    @And("^user click on search icon and submits the following search term '(.*)'$")
    public void userClickOnSearchIconAndSubmitsTheFollowingSearchTermProduct(String searchTerm) throws Throwable {
        performSearch(searchTerm);
    }

    @And("^user click on search icon and submits the following search term$")
    public void userClickOnSearchIconAndSubmitsTheFollowingSearchTerm(DataTable dtURLs) throws Throwable {
        List<List<String>> lstURLs = dtURLs.raw();
        for (int i = 0; i < lstURLs.size(); i++) {
            performSearch(lstURLs.get(i).get(0).toString());
            if(homePage.doesURLContain("catalogsearch/result/"))
                searchResult.selectSearchResult();
            pdp.waitForAjaxElementNotToBePresent(pdp.getWebDriver(),5);
            pdp.selectProductStrengthOrColour();
            pdp.clickAddToCartButton();
            pdp.waitForAjaxElementNotToBePresent(pdp.getWebDriver(),10);
            homePage.openBasketifNotOpenLogic();
            pdp.waitForExpectedElement(pdp.MINICART_CLOSE_BUTTON,15);
            if(pdp.getWebDriver().findElements(pdp.MINICART_CLOSE_BUTTON).size()>0)
                pdp.clickByElementByQueryJSExecutor(pdp.MINICART_CLOSE_BUTTON);
        }
    }

    @And("^click on Edit button$")
    public void clickOnEditButton() {
        switch (UrlBuilder.getLocale()) {
            case "vusede":
            case "fr":
            case "vusefr":
                pdp.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("dashboardEditLinkText.key"))).click();
                break;
            case "velode":
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("td.col.actions > a.action.edit > span"));
                break;
            default:
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("div.editBtn>a.action.edit"));
        }
    }

    @And("^user performs search$")
    public void userPerformsSearch() throws Throwable {
        String productForPurchase = "searchTermVuse.key";
        performSearch(productForPurchase);
    }

    @And("^user click on search icon and enters the following search term '(.*)'$")
    public void userClickOnSearchIconAndEntersTheFollowingSearchTerm(String searchTerm) throws Throwable {
        enterSearch(UrlBuilder.getMessage(searchTerm));
    }

    @And("^search for engraving product, open personalisation window$")
    public void searchForEngravingItemEditPeronalitation() throws Throwable {
        performSearch("searchTermEngraving.key");
        selectEngravingProduct();
        pdp.waitForPage(30);
        pdp.waitForExpectedElement(pdp.ENGRAVING_BUTTON,15);
        pdp.clickByElementByQueryJSExecutor(pdp.ENGRAVING_BUTTON);
        swtichToEngravingIFrame();
    }

    public void swtichToEngravingIFrame() throws InterruptedException {
        Thread.sleep(5000);
        pdp.getWebDriver().switchTo().frame("engraving_iframe");
    }

    public void selectEngravingProduct() {
        switch (UrlBuilder.getLocale()) {
            case "vuseco":
                pdp.waitForPage(20);
                try {
                    pdp.getWebDriver().findElement(pdp.ENGRAVING_ELEMENT_VUSECO);
                } catch (NoSuchElementException e) {
                    pdp.waitForItemToBeClickableAndClick(pdp.MX_ENGRAVING_PRODUCT, 10);
                }
                break;
            case "mx":
            case "vusemx":
                pdp.waitForExpectedElement(pdp.MX_ENGRAVING_PRODUCT,10).click();
                break;
        }
    }

    @And("^select privacy policy link$")
    public void selectPrivacyPolicyLink() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
                homePage.clickByElementByQueryJSExecutor(homePage.eleInformationFooterSection);
                homePage.clickByElementByQueryJSExecutor(homePage.privacyLink);
                break;
            case "ukLive":
            case "vuseuklive":
                homePage.clickByElementByQueryJSExecutor(homePage.eleInformationFooterSection);
                homePage.clickByElementByQueryJSExecutor(homePage.privacyLink);
                break;
            default:
                homePage.clickByElementByQueryJSExecutor(homePage.privacyLink);
        }
    }

    @When("^on homePage$")
    public void onHomePage() {
        assertTrue(homePage.getWebDriver().getTitle().equals("Home page"));
    }

    @Then("^confirm mini-basket displayed amount of '(.*)'$")
    public void confirmMiniBasketDisplayedAmountOf(String basketQty) throws Throwable {
        homePage.confirmMiniBasketDisplayedAmountOf(basketQty);
    }

    @Then("^update mini-basket qty to '(.*)'$")
    public void updateMiniBasketDisplayedAmountOf(String basketQty) throws AWTException {
        homePage.waitForExpectedElement(homePage.basketQtyToUpdate).clear();
        homePage.waitForExpectedElement(homePage.basketQtyToUpdate).sendKeys(basketQty);
        if (UrlBuilder.getLocale().equals("fr")) {
            Actions actions = new Actions(homePage.getWebDriver());
            Robot robot = new Robot();
            robot.mouseMove(50,50);
            actions.click().build().perform();
            homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 10);
        } else {
            homePage.waitForExpectedElement(homePage.basketQtyToUpdate).submit();
        }
    }

    @And("^user ensures basket is empty$")
    public void userEnsuresBasketIsEmpty() {
    }

    @And("^click on proceed to checkout button$")
    public void clickOnProceedToCheckoutButton() throws Throwable {
        homePage.eyesCheckBasketPage();
        homePage.clickOnProceedToCheckoutButton();
        paymentPage.goToAddNewAddressPageAndTakeEyesScreenShot();
        paymentPage.eyesCheckCheckoutPage();
        paymentPage.waitForLoaderToDisapear();
    }

    @And("^user clicks on checkout button$")
    public void clicksOnCheckoutButton() throws Throwable {
        homePage.proceedToCheckoutButton();
        Thread.sleep(10000);
    }

    @And("^use add free gift module according to device$")
    public void addFreeGiftAccordingToDevice() {
        homePage.addFreeGiftAccordingToDevice();
        paymentPage.waitForLoaderToDisapear();
    }

    @And("^user close the free gift module$")
    public void userCloseFreeGiftModule() {
        homePage.userCloseFreeGiftModule();
    }

    @And("^click view and edit card$")
    public void clickViewAndEditCard() {
        homePage.clickElementByQueryJSExecutor(homePage.waitForExpectedElement(By.cssSelector("#minicart-content-wrapper > div.block-content > div:nth-child(6) > div > a")));
    }

    @And("^cookies popup is displayed to user$")
    public void cookiesPopupIsDisplayedToUser() {
        homePage.cookiePopUpDisplayed();
    }

    @And("^oneTrustCookies popup is displayed to user$")
    public void oneTrustCookiesPopupIsDisplayedToUser() {
        switch (UrlBuilder.getLocale()){
            case "velopl":
                homePage.tryClickIAmOver18();
                break;
        }
        homePage.oneTrustCookiePopUpDisplayed();
    }

    @And("^oneTrustCookies link and buttons is displayed to user$")
    public void oneTrustCookiesLinkAndButtonsIsDisplayedToUser() {
        homePage.oneTrustCookiesLinkAndButtonsIsDisplayed();
    }

    @And("^user click on manage cookies button$")
    public void userClickOnOneTrustManageCookiesButtonClick() {
        switch (UrlBuilder.getLocale()){
            case "glode":
            case "velopl":
                homePage.tryClickIAmOver18();
                break;
        }
        homePage.oneTrustManageCookiesButtonClick();
    }

    @And("^oneTrustCookies privacy center logo is displayed$")
    public void oneTrustPrivacyCenterLogoIsDisplayedToUser() {
        homePage.oneTrustPCLogoDisplayed();
    }

    @And("^ensure all oneTrust privacy center elements are present$")
    public void allOneTrustPrivacyCenterElementsArePresent() {
        homePage.oneTrustPCLogoDisplayed();
        homePage.oneTrustPCCloseButtonDisplayed();
        homePage.oneTrustPCTitleDisplayed();
        homePage.oneTrustPCTextDisplayed();
        homePage.oneTrustPCAcceptAllButtonDisplayed();
        homePage.oneTrustPCStrictlyNecessaryCookiesDisplayed();
        homePage.oneTrustPCConfirmMyChoiceButtonDisplayed();
        if (homePage.getWebDriver().getCurrentUrl().contains("fr/fr")) {
            homePage.refuseAllButtonOnPrivacyCenter();
        }
    }

    @And("^ensure all cookies are set to off by default except strictly necessary$")
    public void allCookiesSetToOFFByDefaultExceptStrictlyNecessary() {
        homePage.oneTrustPCCookiesSwitchesAreOffByDefaultExceptStrictlyNecessary();
    }

    @And("^ensure all cookies information link are present under cookies category$")
    public void allCookiesInformationPresenceUnderCookiesCategories() {
        homePage.oneTrustPCCookiesInformationLink();
    }

    @And("^ensure all cookies are set to on$")
    public void allCookiesSetToON() {
        homePage.oneTrustPCCookiesSwitchesAreON();
    }

    @And("^user clicks on oneTrustCookies acceptAll button from privacy center$")
    public void userClicksOnoneTrustCookiesAcceptAllButton() {
        homePage.AcceptAllButtonClickedFromPrivacyCenterOT();
        homePage.tryClickIAmOver18(); // Uk and others?
    }

    @And("^user clicks on oneTrust floating icon$")
    public void userClicksOnoneTrustFlotingIcon() {
        homePage.clickOnFlotingIcon();
    }

    @And("^user clicks on oneTrust footer link$")
    public void userClicksOnoneTrustFooterLink() {
        if (UrlBuilder.getLocale().contains("de/de") || UrlBuilder.getLocale().contains("kz/ru")) {
            homePage.clickOnFlotingIcon();
        } else {
            homePage.clickOnFooterLink();
        }
    }

    @And("^user clicks on privacy cookie link on oneTrustcookies banner$")
    public void userClicksOnPrivacyCookieLinkOnOneTrustCookiesBanner() {
        homePage.clickOnPrivacyNoticeLinkOnOneTrustBanner();
    }

    @And("^ensure user lands on privacy notice page$")
    public void ensureUserLandsOnPrivacyNoticePage() {
        assertTrue(homePage.getWebDriver().getCurrentUrl().contains("privacy-notice") || homePage.getWebDriver().getCurrentUrl().contains("politica-cookies") || homePage.getWebDriver().getCurrentUrl().contains("cookie-notice"));
    }

    @And("^select allow all from cookie popup$")
    public void selectAllowAllFromCookiePopup() {
        homePage.cookieClickAllowAllAndClickOver18();
        homePage.eyesCheckHomePage();
    }

    @And("^verify cookie presence and expiry$")
    public void verifyCookiePresenceAndExpiry() {
        homePage.cookiePresenceAndExpiry();
    }

    @And("^select allow all from new cookie consent popup$")
    public void selectAllowAllFromNewCookieConsentPopup() throws Throwable {
        homePage.selectAllowAllFromNewCookieConsentPopup();
    }

    @And("^user selects learn more link from cookie popup$")
    public void userSelectsLearnMoreLinkFromCookiePopup() {
        homePage.cookieLearnMoreLinkClick();
    }

    @And("^assert cookie text is present as expected$")
    public void assertCookieTextIsPresentAsExpected() {
        homePage.cookieTextDisplayedAsExpected();
    }

    @And("^confirm popup mask present$")
    public void confirmPopupMaskPresent() {
        homePage.checkoutPopUpPresented();
    }

    @Then("^select create new account from checkout PopUp$")
    public void selectCreateNewAccountFromCheckoutPopUp() {
        homePage.checkOutPopUpselectCreateNewAccount();
        LOG.info("testing breakpoint");
    }

    @And("^login with valid details$")
    public void loginWithValidDetails() throws Throwable {
        homePage.waitForElementToAppearAndDisappear(PageObject.LOADER, 3,3);
        homePage.checkOutPopUpSignIn(UrlBuilder.getMessage("loginValidEmail.key"), UrlBuilder.getMessage("loginValidPassword.key"));
        homePage.waitForElementToAppearAndDisappear(PageObject.LOADER, 5,5);
    }

    @And("^click hamburger menu$")
    public void clickHamburgerMenu() throws Throwable {
        Thread.sleep(1000);
        homePage.clickHamburgerMenu();
        Thread.sleep(2000);
    }

    @And("^click mobile hamburger menu$")
    public void M_clickHamburgerMenu() throws Throwable {
        homePage.clickMobileHamburgerMenu();
        Thread.sleep(1000);
    }

    @And("^cycle through menu options$")
    public void cycleThroughMenuOptions() {
        homePage.cycleThurHamBurgerLinks();
    }

    @And("^cycle through menu options and ensure the following is present '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)'$")
    public void cycleThroughMenuOptionsAndEnsureTheFollowingIsPresentSubscriptionsTheUsualFavoriatesMyBasket(String firstLink, String secondLink, String thirdLink, String forthLink, String fifthLink, String sixthLink, String seventhLink, String eigthLink) throws Throwable {
        ArrayList<String> linksToMatch = new ArrayList<>(Arrays.asList(firstLink, secondLink, thirdLink, forthLink, fifthLink, sixthLink, seventhLink, eigthLink));
        assertTrue("** Failure as links not as expected ", homePage.cycleThurHamBurgerLinksAndMatchArray(linksToMatch) >= 7);
    }

    @Then("^close the hamburger menu$")
    public void closeTheHamburgerMenu() {
        homePage.closeHamburgerMenu();
    }

    @Then("^close the mobile hamburger menu$")
    public void closeTheMobileHamburgerMenu() {
        homePage.clickMobileHamburgerMenu();
    }

    @When("^users clicks on the '(.*)' text link$")
    public void usersClicksOnTheLogoutTextLink(String linkText) {
        String language =null;
        switch (UrlBuilder.getLocale()) {
            case "velobe":
                language = scenarioContext.getContext(Context.LANGUAGE).toString();
                homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText + "-" + language).toUpperCase()));
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
                break;
            case "veloza":
                language = scenarioContext.getContext(Context.LANGUAGE).toString();
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
                homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText + "-" + language).toUpperCase())).click();
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
                break;
            case "velopl":
                homePage.jsScrollElementInCenter(homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase())));
                homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()), 10);
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                break;
            case "vusede":
                if(!linkText.equals("YourOrdersText.key"))
                    homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()),10);
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
                break;
            case "fr":
            case "vusefr":
            case "vuseuk":
                if (UrlBuilder.isMobile()) {
                    try {
                        M_clickHamburgerMenu();
                        homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()), 10);
                        homePage.waitForItemToBeClickableAndClick(By.partialLinkText("EPEN"), 5);
                        homePage.waitForLoaderToDisapear();
                        homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    } catch (Throwable e) {
                        homePage.waitAndClickByElementByJSExecutor(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()), 10);
                    }
                }
                else {
                    switch (SessionInfo.scenarioName) {
                        case "17313 Cycle through footer links ensure each one is working as expected":
                        case "Conditions of sale VuseUK":
                            if (linkText.equals("DeliveryReturn.key") || linkText.equals("warranty.key") || linkText.equals("conditionsofsale.key") || linkText.equals("Terms&Conditions.key") || linkText.equals("Accessibility.key")) {
                                By footerLink = By.cssSelector("div.desktop-only.desktop-footer-links a[href*='" + UrlBuilder.getMessage(linkText.substring(0, linkText.length() - 4) + "Url.key") + "']");
                                homePage.clickByElementByQueryJSExecutor(footerLink);
                            } else {
                                homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText)), 10);
                            }
                            break;
                        case "21398 Guest, opens hamburger menu select main hamburger links":
                        case "BUG - 26280 Checkout tests - Guest to Sign in - Populate Cart proceed to checkout, then sign in from basket page - Paypal path -Empty basket":
                            homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText)), 10);
                            break;
                        default:
                            homePage.jsScrollElementInCenter(homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase())));
                            homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()), 5).click();
                            homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    }
                }
                break;
            case "vuseco":
            case "vuseza":
                if (linkText.equals("MySubscriptions.key")){
                    homePage.scrollToPageTop();
                    homePage.waitForAjaxElementNotToBePresent(getWebDriver(),40);
                    try {
                        homePage.clickOnSubscription();
                    }catch(Exception e) {
                    }
                    try{
                        homePage.urlToContainInSeconds("customer/subscriptions",40);
                    }catch(Exception e) {
                        homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 40);
                        homePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()));
                    }
                }else {
                    homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 40);
                    homePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()));
                }
                break;
            case "kz":
            case "glode":
                if(homePage.getCurrentUrl().contains("/customer/account/index")){
                    homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase())).click();
                } else {
                    homePage.clickPersonIcon();
                    homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("InviteFriendTop.key").toUpperCase())).click();
                    homePage.clickUsingJS(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()));
                }
                break;
            case "mx":
            case "vusemx":
            case "vypeit":
            case "vuseit":
                if (linkText.equalsIgnoreCase("viewBasketText.key")) {
                    homePage.clickOnProceedToCartCTA();
                }else if(linkText.equalsIgnoreCase("newsLetterText.key")){
                    homePage.clickOnNewsletterButton();
                }else if(linkText.equalsIgnoreCase("logoutText.key")){
                    try{
                        homePage.usersClicksOnTheLogoutTextLink(linkText);
                    }catch(Exception e) {
                        LOG.info("\n ** LOGGIN OUT **");
                    }
                }
                else{
                    homePage.defaultClickLinkByLinkText(linkText);
                }
                break;
            case "lyftse":
                try{
                    homePage.viewBasketCheck(linkText);
                }catch(Exception e){
                    LOG.info("Banner Blocking cart button-exiting banner");
                    homePage.waitForExpectedElement(By.cssSelector(".more-menu-container .material-icons")).click();
                    homePage.viewBasketCheck(linkText);
                }
                break;
            case "velode":
                String myAccountMenuOption=UrlBuilder.getMessage(linkText);
                getWebDriver().findElement(By.xpath("//div[@class='select-items']/div[contains(text(),'"+myAccountMenuOption+"')]")).click();
                break;
            case "it":
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(),5);
                try {
                    homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)),10).sendKeys(Keys.ARROW_DOWN);
                    homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)),10).click();
                }
                catch (Exception ex)
                {
                    if(homePage.isElementPresent(By.linkText(UrlBuilder.getMessage(linkText)))) {
                        homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)),10).sendKeys(Keys.ARROW_DOWN);
                        homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)), 10).click();
                    }
                }

                break;
            default:
                homePage.defaultClickLinkByLinkText(linkText);
                break;
        }
    }

    @And("^users clicks on the '(.*)' link$")
    public void usersClicksOnTheLinkByText(String linkText) {
        homePage.usersClicksOnTheLinkByText(linkText);
    }

    @And("^users clicks on the Recent Orders link$")
    public void usersClicksOnRecent(String linkText) {
        try {
            Thread.sleep(3000);
            homePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
            Thread.sleep(3000);
        } catch (Exception e) {
            LOG.info("Couldn't click due to : " + e.getMessage());
        }
    }

    @And("^user clicks on the link that contains '(.*)' href$")
    public void usersClicksOnLinkWithHref(String path) {
        homePage.clickOnLinkWithHref(path);
    }

    @Then("^empty basket$")
    public void emptyBasket() {
        homePage.emptyBasket();
    }

    @Then("^empty basket Alpha$")
    public void emptyBasketApha() {
        homePage.emptyBasketAlpha();
    }

    @Then("^url contains '(.*)'$")
    public void urlContainsSubscriptions(String urlContains) {
        if ((UrlBuilder.getLocale().equals("vypeit") && urlContains.equalsIgnoreCase(UrlBuilder.getMessage("DeleteRegistrationUrl.key")))
                || (UrlBuilder.getLocale().equals("uk") && urlContains.equalsIgnoreCase("e-cigarette-devices"))
                || (UrlBuilder.getLocale().equals("uk") && urlContains.equalsIgnoreCase("e-liquids"))
                || (UrlBuilder.getLocale().equals("uk") && urlContains.equalsIgnoreCase("vype-bundles"))
                || (UrlBuilder.getLocale().equals("uk") && urlContains.equalsIgnoreCase("e-cigarette-accessories"))
                || (UrlBuilder.getLocale().equals("de") && urlContains.equalsIgnoreCase((urlContains)))
        ) {
            LOG.info("This Step is not applicable for Vype IT locale with respective to asserting delete success URL.");
        } else
            userWaitHomePageToLoad();
        assertTrue("**** ERROR - following was expected in URL : " + UrlBuilder.getMessage(urlContains) + " but full URL was : \n" + homePage.getWebDriver().getCurrentUrl(), homePage.getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(urlContains)));
    }

    @Given("^user navigates to the following URL '(.*)'$")
    public void userNavigatesToTheFollowingURLHttpsWwwCiGlobalEuwVypeMagentoBatdigitalComPrivacyPolicyCookieRestrictionMode(String URL) throws Throwable {
        homePage.getWebDriver().get(URL);
    }

    @Given("^user navigates to the Blog page$")
    public void userNavigatesToTheBlogPage() {
        String currentUrl = homePage.getWebDriver().getCurrentUrl();
        homePage.getWebDriver().get(currentUrl + "blog");
    }

    @And("^resize browser screen$")
    public void resizeBrowserScreen() {
        LOG.info("\n RE SIZING ****************************** ");
        homePage.resizeBrowserWindow(480, 800);
    }

    @And("^resize browser screen - full screen$")
    public void resizeBrowserToFullScreen() {
        homePage.getWebDriver().manage().window().maximize();
    }

    @And("^user selects proceed to checkout from cart page$")
    public void userSelectsProceedToCheckoutFromCartPage() throws Throwable {
        Thread.sleep(4000);
        switch (UrlBuilder.getLocale()) {
            case "it":
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("li.item button.action.primary.checkout:nth-child(1) > span:nth-child(1)"));
                break;
            case "glode":
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("li.item button.action.primary.checkout:nth-child(1) > span:nth-child(1)"));
                break;
            case "lyftse":
                homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(),5);
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("ul.checkout.methods.items.checkout-methods-items:nth-child(5) li.item > button.action.primary.checkout:nth-child(1)"));
                break;
            case "lyftdk":
                homePage.clickByElementByQueryJSExecutor(By.cssSelector("main#maincontent li > button[type=\"button\"]:nth-child(1)"));
                break;
            case "vuseit":
            case "vuseza":
                homePage.waitForExpectedElement(homePage.PROCEED_TO_CHECKOUT_BUTTON, 10);
                homePage.clickByElementByQueryJSExecutor(homePage.PROCEED_TO_CHECKOUT_BUTTON);
                homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(),5);
                if(getWebDriver().findElements(homePage.AGE_VERIFICATION_CONSENT_BUTTON).size()>0)
                    homePage.clickByElementByQueryJSExecutor(homePage.AGE_VERIFICATION_CONSENT_BUTTON);
                break;
            case "vuseuk":
                homePage.closeFeedbackIfPresent();
                homePage.waitForExpectedElement(homePage.PROCEED_TO_CHECKOUT_BUTTON, 10);
                homePage.clickByElementByQueryJSExecutor(homePage.PROCEED_TO_CHECKOUT_BUTTON);
                break;
            case "vusefr":
                try{
                    homePage.waitForItemToBeClickableAndClick(homePage.PROCEED_TO_CHECKOUT_BUTTON,10);
                }catch(Exception e){
                    LOG.info("Exception: "+e);
                    homePage.waitAndClickByElementByJSExecutor(homePage.PROCEED_TO_CHECKOUT_BUTTON,10);
                }
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
                break;
            default:
                try {
                    homePage.waitForExpectedElement(homePage.proceedToCheckoutButton, 10).click();
                } catch (Exception ex) {
                    Thread.sleep(4000);
                    homePage.clickByElementByQueryJSExecutor(homePage.proceedToCheckoutButton);
                    Thread.sleep(4000);
                }
        }
    }

    @And("^assert text of '(.*)' is displayed on page$")
    public void assertTextOfVypeEPenStarterKitHasBeenAddedToYourWishListClickHereToContinueShoppingIsDisplayedOnPage(String textToFind) throws Throwable {
        Thread.sleep(3000);
        if(textToFind.endsWith("key"))
            assertTrue(homePage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(textToFind)));
        else
            assertTrue(homePage.getWebDriver().getPageSource().contains(textToFind));
    }

    @And("^assert sign up for our newsletter is present$")
    public void assertSignUpForOurNewsletterIsPresent() {
        homePage.waitForExpectedElement(homePage.newsLetterSubscribeButton).isDisplayed();
    }

    @And("^click on newsletter button$")
    public void clickOnNewsletterButton() {
        homePage.clickOnNewsletterButton();
    }

    @And("^click on newsletter icon on header$")
    public void clickOnNewsletterIconOnHeader() {
        homePage.clickOnNewsletterIconOnHeader();
    }

    @And("^enter newsletter details and submit$")
    public void enterNewsletterDetailsAndSubmit() {
        homePage.enterNamesAndEmailToNewsLetterSignUpAndSubmit();
    }

    @And("^enter newsletter details with email address '(.*)' and submit$")
    public void enterNewsletterDetailsWithEmailAndSubmit(String strEmailAddress) {
        homePage.enterNamesAndEmailAddressesToNewsLetterSignUpAndSubmit(strEmailAddress);
    }

    @And("^enter newsletter details and random email and submit$")
    public void enterNewsletterDetailsAndRandomEmailAndSubmit() {
        homePage.enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit();
    }

    @And("^enter newsletter details with random email and assert '(.*)' subscription$")
    public void enterNewsletterDetailsAndRandomEmailAndSubmit(String strMessage) throws Throwable {
        homePage.enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit("", strMessage);
    }

    @And("enter newsletter details with existing email address '(.*)' and assert '(.*)' subscription$")
    public void enterNewsletterDetailsWithExistingEmailAndSubmit(String strExistingEmail, String strMessage) throws Throwable {
        homePage.enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit(strExistingEmail, strMessage);
    }

    @And("^assert '(.*)' message is presented for already used email$")
    public void assertErrorMessageForAlreadyUsedNewsletterSubscriptionEmail(String strErrorMessage) {
        homePage.assertErrorMessageForAlreadyUsedEmail(strErrorMessage);
    }

    @And("^enter invalid newsletter details and submit$")
    public void enterInvalidNewsletterDetailsAndSubmit() {
        homePage.enterNamesAndInvalidEmailToNewsLetterSignUpAndSubmit();
    }

    @And("^enter newsletter details with invalid email address '(.*)' and assert '(.*)' subscription$")
    public void enterInvalidNewsletterDetailsAndSubmit(String strInvalidEmail, String strMessage) throws Throwable {
        homePage.enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit(strInvalidEmail, strMessage);
    }

    @And("^assert newsletter error validation message of '(.*)'$")
    public void andAssertNewsletterErrorValidationMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainCom(String expectedError) {
        String actualError = homePage.waitForExpectedElement(By.cssSelector("#newsletter-error")).getText();
        if (homePage.doesURLContain("/mx/es") || homePage.doesURLContain("vype.non-prod.marketing.bat.net/it/it/") || homePage.doesURLContain("govype.com/it/it/")) {
            expectedError = UrlBuilder.getMessage("loginInvalidEmailErrorMsg.key");
            homePage.getWebDriver().getPageSource().contains(expectedError);
        } else {
            homePage.assertTrueWithCustomError(expectedError, actualError);
        }
    }

    @And("^attempt to logout$")
    public void attemptToLogout() {
        switch (UrlBuilder.getLocale()) {
            case "de":
                userClicksThePersonIcon();
                homePage.clickByElementByQueryJSExecutor(By.linkText("Ausloggen"));
                homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 10);
                break;
            case "pl":
            case "vusefr":
                homePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("logoutText.key")));
                homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 10);
                break;
            case "glode":
            case "vusede":
                userClicksThePersonIcon();
                homePage.clickByElementByQueryJSExecutor(By.linkText("AUSLOGGEN"));
                homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 10);
                break;
            case "vypeit":
            case "vuseit":
            case "vuseza":
            case "vuseuk":
                userClicksThePersonIcon();
                homePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("logoutText.key")));
                break;
            case "velode":
            case "vuseco":
                userClicksThePersonIcon();
                homePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("logoutTextLink.key")));
                homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 10);
                break;
            default:
                try {
                    homePage.waitForExpectedElement(By.linkText("Logout")).click();
                    Thread.sleep(2000);
                    userClicksThePersonIcon();
                } catch (Exception e) {
                    LOG.info("\n Not logged in - continuing ...");
                }
        }
    }

    @And("^feature block div present$")
    public void featureBlockDivPresent() throws Throwable {
        Thread.sleep(3000);
        LOG.info("\n ******************* " + homePage.waitForExpectedElement(By.cssSelector("div.pagebuilder-column.pagebuilder-desktop-visible")).isDisplayed());
    }

    @And("^bestsellers feature block present$")
    public void bestsellersFeatureBlockPresent() {
        LOG.info("\n ******************* " + homePage.waitForExpectedElement(By.cssSelector("div.products-grid.grid")).isDisplayed());
    }

    @And("^click on the logo$")
    public void clickOnTheLogo() {
        homePage.clickOnLogo();
    }

    @When("^click on subscription link$")
    public void clickOnSunscription(){
        homePage.clickOnSunscription();
    }

    @And("^click homePage Add to Cart Mix and Match Carousel without selecting required attribute$")
    public void clickHomePageAddToCartMixAndMatchCarouselWithoutSelectingRequiredAttribute() throws Throwable {
        Thread.sleep(3000);
        LOG.info("\n clicking add to cart button on homepage");
        homePage.clickByElementByQueryJSExecutor(By.cssSelector("#slick-slide10 > div > li > div > div > div.product-item-inner > div > div > form > div > button > span"));
    }

    @And("^assert page title is as expected displaying '(.*)'$")
    public void assertPageTitleIsAsExpectedDisplaying(String expectedPageTitle) {
        assertTrue("Page title isn't as expected", homePage.getWebDriver().getTitle().equals(expectedPageTitle));
    }

    @And("^cycle through menu options and ensure the following is present '(.*)' and select it$")
    public void cycleThroughMenuOptionsAndMatchAndSelect(String linkToMatchAndClick) throws Throwable {
        homePage.cycleThurHamBurgerLinksAndSelect(linkToMatchAndClick);
        Thread.sleep(3000);
    }

    @And("^click on hamburger Store Locator link$")
    public void clickOnHamburgerStoreLocatorLink() {
        try {
            homePage.waitForExpectedElement(By.cssSelector("body > div.burger-menu-container > div > div:nth-child(2) > div > div > ol > li:nth-child(6) > a")).click();
        } catch (Exception e) {
            homePage.waitForExpectedElement(By.linkText("Store Locator")).click();
        }
    }

    @Given("^user navigates to the production homepage$")
    public void userNavigatesToTheProductionHomepage() {
        homePage.getWebDriver().get("https://www.govype.com/gb/en/");
    }

    @Given("^user navigates EPOK homepage$")
    public void userNavigatesEPOKHomepage() {
        homePage.getWebDriver().get("https://www.epok.de");
    }

    @Then("^assert that page title is '(.*)'$")
    public void assertPageHeadingIsNotFound(String expectePageHeading) {
        String pageTitle = homePage.getWebDriver().getTitle();
        homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectePageHeading), pageTitle);
    }

    @And("^user clicks on the cta and navigates to that page$")
    public void vypeHomePageCta(DataTable table) {
        homePage.vuseHomeCta(table);
    }

    @And("^first and last name and email input boxes present as guest user$")
    public void firstAndLastNameInputBoxesPresentAsGuestUser() {
        homePage.inputFieldsPresentAsExpected();
    }

    @And("^enter first and last name$")
    public void enterFirstAndLastName() {
        homePage.waitForExpectedElement(By.cssSelector("#firstname")).sendKeys("Testing");
        homePage.waitForExpectedElement(By.cssSelector("#lastname")).sendKeys("tesingSurname");
    }

    @And("^user hovers over on the More header link$")
    public void userClicksOnTheMoreHeaderLink() throws Throwable {
        homePage.userClicksOnTheMoreHeaderLink();
    }

    @And("^click the Bundles link$")
    public void clickTheBundlesLink() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
                homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("bundlesHeaderLinkText.key"))).click();
                break;
            default:
                homePage.waitForExpectedElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > a")).click();
        }

    }

    @Then("^Assert GTM event contains '(.*)'$")
    public void checkExpectedGTMEventFired(String tagEvent) {
        JavascriptExecutor js = (JavascriptExecutor) homePage.getWebDriver();
        ArrayList<Map<String, List<String>>> myList = new ArrayList<>();
        //Execute GTM script to fetch values
        myList = (ArrayList<Map<String, List<String>>>) js.executeScript("return window.dataLayer");
        Boolean containsExpectedEvent = false;
        for (Map<String, List<String>> entry : myList) {
            LOG.info("\nENTRY VALUE : " + entry.values());
            LOG.info("\n ENTRY KeySet : " + entry.keySet());
            if (entry.keySet().contains("ecommerce")) {
                LOG.info("\nENTRY VALUE : " + entry.values());
                // below looping with in loop as it's actually returning an array
                for (Object item : entry.values()) {
                    if (item.toString().contains(tagEvent)) {
                        containsExpectedEvent = true;
                    }

                }
            }
        }
    }

    @And("^RestAssured return all information$")
    public void restassuredReturnAllInformation() {
        homePage.restAssuredReturnAllInfo(homePage.getWebDriver().getCurrentUrl());
    }

    @And("^browserMob to grab network traffic payload data$")
    public void browsermobToGrabNetworkTrafficPayloadData() {
        // implementing browserMob Proxy below:
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        LOG.info("No failures on the above code - needs to be implemented at chrome driver setup level");
    }

    @And("^footer list items are displayed$")
    public void footerListItemsAreDisplayed() {
        homePage.footerListItemsAreDisplayed();
    }

    @When("^(?:Vype|velo) users click on Facebook link$")
    public void users_click_on_Facebook_link() {
        homePage.clickOnFacebookIcon();
    }

    @And("^assert title '(.*)' is displayed$")
    public void assertTitleIsDisplayed(String strExpectedTitle) {
        assertTrue(homePage.getCurrentPageTitle().equalsIgnoreCase(UrlBuilder.getMessage(strExpectedTitle)));
    }

    @And("^assert Color Swatch option is displayed on Mini Cart$")
    public void assertColorSwatchOptionIsDisplayedOnMiniCart() {
        assertTrue(homePage.getWebDriver().findElements(homePage.colorSwatchOption).size() > 0 || homePage.getWebDriver().findElements(homePage.productStrengthOption).size() > 0);
    }

    @And("^assert Add To Basket button is not displayed on Mini Cart$")
    public void assertAddToBasketButtonNotDisplayedOnMiniCart() throws Throwable {
        homePage.getWebDriver().navigate().back();
        clickOnBasketIcon();
        assertFalse(homePage.getWebDriver().findElements(homePage.btnAddToBasketMiniCart).size() > 0);
    }

    @And("^assert Price Per prod text is not displayed on Mini Cart$")
    public void assertPricePerProdTextNotDisplayedOnMiniCart() {
        assertFalse(homePage.getWebDriver().findElements(homePage.elePricePerPodText).get(0).getText().contains("Price per pod"));
    }

    @And("^assert Basket Section with Item details$")
    public void assertMiniBasketSectionWithItemDetails() {
        homePage.verifyMiniBasketSectionWithItemDetailsTableContent();
    }

    @And("^user increases the quantity of the selected item$")
    public void userIncreasesItemQuantity() {
        homePage.waitForExpectedElement(homePage.btnIncreaseItemQty, 5).click();
        homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 5);
        assertTrue(homePage.waitForExpectedElement(homePage.inputItemQuantity).getAttribute("Value").equals("2"));
    }

    @And("^user decreases the quantity of the selected item$")
    public void userDecreasesItemQuantity() {
        homePage.waitForExpectedElement(homePage.btnDecreaseItemQty, 5).click();
        homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 5);
        assertTrue(homePage.waitForExpectedElement(homePage.inputItemQuantity).getAttribute("Value").equals("1"));
    }

    @And("^assert Discount Code section under Order Summary$")
    public void assertDiscountCodeSectionUnderOrderSummary() {
        assertTrue(homePage.waitForExpectedElement(homePage.eleApplyDiscountCode).isDisplayed());
    }

    @And("^validation success message displayed$")
    public void newsLetterValidationMessageDisplayed() {
        if(!UrlBuilder.getLocale().equals("kz"))
            homePage.newsLetterValidationMessageDisplayed();
    }

    @And("^users clicks on the dashboardEditLink text link$")
    public void clicksOnDashboardLink() {
        homePage.clicksOnDashboardLink();
    }

    @And("^assert text of underAgeErrorMsg is displayed$")
    public void errorValidationMessageDisplayed() {
        homePage.waitForExpectedElement(By.cssSelector("#dob-error"), 10);
        homePage.waitForExpectedElement(By.cssSelector("#dob-error")).isDisplayed();
    }

    @And("^NewsLetter invalid email message displayed$")
    public void newsLetterInvalidEmailErrorMsg() {
        homePage.newsLetterInvalidEmailErrorMsg();
    }

    @When("^(?:Vype|velo) users click on Instagram link$")
    public void users_click_on_Instagram_link() {
        homePage.clickOnInstagramIcon();
    }

    @When("^(?:Vype|velo) users click on YouTube link$")
    public void users_click_on_YouTube_link() {
        homePage.clickOnYouTubeIcon();
    }

    @When("^(?:Vype|velo) users click on Twitter link$")
    public void users_click_on_Twitter_link() {
        homePage.clickOnTwitterIcon();
    }

    @And("^assert Quantity Change option is not displayed on Mini Cart$")
    public void assertQuantityChangeOptionNotDisplayedOnMiniCart() {
        assertFalse(homePage.getWebDriver().findElements(homePage.QtyChangeField).size() > 0);
    }

    @And("^user clicks on Remove button on Mini Basket page$")
    public void userClicksOnRemoveButtonOnMiniBasketPage()  {
        homePage.waitForExpectedElement(homePage.btnRemoveMiniBasket, 5).click();
    }

    @And("^assert Are You Sure you want to remove pop-up is displayed$")
    public void assertAreYouSureToRemoveItemPopUp()  {
        assertTrue(homePage.waitForExpectedElement(homePage.popUpRemoveItemFromBasket).isDisplayed());
    }

    @And("^I delete customer account$")
    public void iDeleteCustomerAccount() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "vuseit":
                break;
            case "uk":
            case "vuseuk":
            case "ie":
                batHelper.deleteCustomer();
                homePage.waitForPage();
                break;
            default:
                batHelper.deleteCustomer();
        }
    }

    @Then("^assert URL '(.*)' returns successful response$")
    public void assertOrderConfirmationPDFIsDisplayed(String strURL) throws Throwable {
        if(strURL.contains("key"))
            orderSuccessPage.verifyURLStatusCodeIsValid(UrlBuilder.getMessage(strURL));
        else
            orderSuccessPage.verifyURLStatusCodeIsValid(strURL);
    }

    @And("^user clicks Remove Item on the pop-up$")
    public void userClicksOnRemoveItemButtonOnPopUp() {
        homePage.clickByElementByQueryJSExecutor(homePage.btnRemoveItemPopUp);
    }

    @And("^users clicks on the Continue Shopping button on Mini Basket Page$")
    public void userClicksOnContinueShoppingButtonOnMiniBasketPage() {
        homePage.clickByElementByQueryJSExecutor(homePage.btnContinueShoppingMiniBasket);
    }

    @And("^assert basket empty message '(.*)' is displayed$")
    public void assertBasketEmptyMessageOnMiniCart(String strExpectedText) {
        String strEmptyBasketText = homePage.getWebDriver().findElements(homePage.eleBasketEmptyMessage).get(0).getText();
        assertTrue(strEmptyBasketText.equalsIgnoreCase(UrlBuilder.getMessage(strExpectedText)));
    }

    @And("^user clicks Move to Wish List on the pop-up$")
    public void userClicksOnMoveToWishListButtonOnPopUp() {
        homePage.clickByElementByQueryJSExecutor(homePage.btnMoveToWishListPopUp);
    }

    @And("^user clicks on Favourite Icon from Header$")
    public void userClicksOnFavouriteIconFromHeader() {
        homePage.clickByElementByQueryJSExecutor(homePage.btnFavouriteIconHeader);
    }

    @And("^users navigates to '(.*)' URL$")
    public void userNavigatesToReferAFriendURL(String strURL) {
        homePage.getWebDriver().navigate().to(UrlBuilder.getMessage(strURL));
    }

    @And("^assert Login button is displayed$")
    public void assertLoginButtonIsDisplayedOnReferFriendPage() {
        assertTrue(homePage.waitForExpectedElement(homePage.btnLogin).isDisplayed());
    }

    @And("^assert Invite Friends button is displayed$")
    public void assertInviteFriendsButtonIsDisplayedOnReferFriendPage() {
        assertTrue(homePage.waitForExpectedElement(homePage.btnInviteFriends).isDisplayed());
    }

    @And("^user clicks on Login button$")
    public void userClicksOnLoginButtonReferFriendPage() {
        homePage.getWebDriver().findElements(homePage.btnLogin).get(0).click();
    }

    @And("^user clicks on Invite Friends button$")
    public void userClicksOnInviteFriendsButtonOnReferFriendPage() {
        homePage.waitForExpectedElement(homePage.btnInviteFriends).click();
    }

    @And("^assert Invite Friends pop up is displayed$")
    public void assertInviteFriendsPopUpWindow() {
        assertTrue(homePage.waitForExpectedElement(homePage.inviteFriendsPopUp).isDisplayed());
    }

    @And("^close the pop-up window and return to base page$")
    public void closeInviteFriendsPopUpWindow() {
        homePage.clickByElementByQueryJSExecutor(homePage.btnClosePopUp);
    }

    @Given("^select allow all from cookie popup and select over 18 age confirmation option$")
    public void userSelectsOverAgeConfirmationOptionAndSelectsOver18AgeConfirmation() {
        homePage.waitForPage();
        if (!UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
            homePage.cookieClickAllowAllAndClickOver18();
        }
        if (!UrlBuilder.getLocale().equalsIgnoreCase("kz")
                && !UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
            homePage.eyesCheckHomePage();
        }
    }


    @And("^verify the order of cookiebanner$")
    public void verifyTheOrderOfCookiebanner() {
        homePage.checkOrderOfCookieBanner();
    }

    @And("^assert text of '(.*)' section is displayed$")
    public void assertReferAFriendPageTitle(String strPageTitle) {
        assertTrue(homePage.waitForExpectedElement(homePage.eleReferAfriendPageTitle).isDisplayed());
    }

    @And("^assert Step 1 Invite Friends section is displayed$")
    public void assertInviteFriendsSection1() {
        assertTrue(homePage.waitForExpectedElement(homePage.eleInviteFriendsSection).isDisplayed());
    }

    @And("^assert Step 2 Get Coupons section is displayed$")
    public void assertGetCouponsSection2() {
        assertTrue(homePage.waitForExpectedElement(homePage.eleGetCouponsSection).isDisplayed());
    }

    @And("^assert Step 3 Redeem Vouchers section is displayed$")
    public void assertRedeemVouchersSection3() {
        assertTrue(homePage.waitForExpectedElement(homePage.eleRedeemVouchers).isDisplayed());
    }

    @And("^users access incorrect application URL$")
    public void usersAccessApplicationIncorrectURL() {
        String strApplicationIncorrectURL = homePage.getCurrentUrl() + RandomGenerator.randomAlphabetic(5);
        ;
        homePage.getWebDriver().navigate().to(strApplicationIncorrectURL);
    }

    @And("^assert 404 Page Not Found is displayed$")
    public void assert404PageNotFoundIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.ele404PageNotFoundPageTitle).isDisplayed());
    }

    @And("^user hovers over on the Country Selector link$")
    public void userHoversOnTheCountrySelectorLink() throws Throwable {
        homePage.hoverOnElement(homePage.navMoreMenuCountrySelector);
        Thread.sleep(1000);
    }

    @And("^assert different Countries div is displayed$")
    public void assertDifferentCountriesDivIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(homePage.navMoreMenuCountrySelectorDiv).isDisplayed());
    }

    @And("^users clicks on the Mexico link from Country selector div$")
    public void userClicksOnTheMexicoLinkFromCountrySelector() {
        homePage.waitForExpectedElement(homePage.navCountrySelectorMenuMexicoLink).click();
    }

    @And("user clicks on Terms and Conditions link from the footer$")
    public void userClicksOnTermsAndConditionsLinkFromFooter() {
        homePage.clickByElementByQueryJSExecutor(homePage.lnkTermsAndConditionsFooterLink);
    }

    @And("assert header of '(.*)' on Terms And Conditions page$")
    public void assertHeaderOfEachLinkOnTermsConditionsPage(String strHeader) {
        assertTrue(homePage.waitForExpectedElement(homePage.TCsLeftNavigationLinksHeader, 20).getText().equalsIgnoreCase(UrlBuilder.getMessage(strHeader)));
    }

    @And("user clicks on Expand tag and assert each FAQ question is expanded$")
    public void clickOnExpandTagAndAssertEachFAQIsExpanded() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                homePage.clickOnExpandTagAndAssertEachFAQIsExpandedOnLyftLabSite();
                break;
            case "lyftdk":
                homePage.clickOnExpandTagAndAssertEachFAQIsExpandedOnLyftLabSite();
                break;
            default:
                assertTrue(homePage.clickOnExpandTagAndAssertEachFAQIsExpanded());
        }
    }

    @And("assert Categories Menu is displayed$")
    public void assertBlogCategoriesMenu() {
        assertTrue(homePage.waitForExpectedElement(homePage.eleBlogCategoriesMenu).isDisplayed());
    }

    @And("user Clicks on Find Out More button for a blog post$")
    public void userClicksFindOutMoreButtonForBlogPost() {
        homePage.clickByElementByQueryJSExecutor(homePage.btnFindOutMore);
    }

    @And("assert Find Out More button is not displayed for the post$")
    public void assertFindOutMoreButtonNotDisplayedForBlogPost() {
        assertTrue(homePage.getWebDriver().findElements(homePage.btnFindOutMore).size() == 0);
    }

    @And("assert Share This Post section with social media blogs at the bottom of the page$")
    public void assertShareThisPostSectionAtBottomOfThePage()  {
        homePage.scrollElementIntoView(homePage.eleShareThisPostWithSocialMediaBlogs);
        assertTrue(homePage.waitForExpectedElement(homePage.eleShareThisPostWithSocialMediaBlogs).isDisplayed());
    }

    @And("user clicks on '(.*)' link to share the post$")
    public void userClicksOnSocialMediaLinksToShareThePost(String strSocialMediaLink) throws Throwable {
        homePage.waitForExpectedElement(By.xpath("//a[@class='addthis_button_" + UrlBuilder.getMessage(strSocialMediaLink) + "']")).click();
        Thread.sleep(2000);
    }

    @And("assert a new window opens with '(.*)' URL$")
    public void assertNewWindowOpensWithSocialMediaURL(String strURL) throws Throwable {
        homePage.assertNewWindowOpensWithSocialMediaURLToShareThePost(UrlBuilder.getMessage(strURL));
    }

    @And("assert Other Articles Widget is displayed$")
    public void assertOtherArticlesWidgetIsDisplayed() throws InterruptedException {
        homePage.scrollElementIntoView(homePage.eleOtherArticlesWidget);
        Thread.sleep(1000);
        assertTrue(homePage.getWebDriver().findElements(homePage.eleOtherArticlesWidget).size() >= 3);
    }

    @And("assert Mini Cart is non-editable$")
    public void assertMiniCartIsNonEditable() {
        assertFalse(homePage.waitForExpectedElement(homePage.emptyBasketIcon).isDisplayed());
    }

    @And("^user clicks on '(.*)' link from Person Menu$")
    public void userClicksOnLinksFromPersonMenu(String strExpectedLink) {
        homePage.clickOnLinksFromPersonMenu(strExpectedLink);
    }

    @And("^user hovers over on the SHOP header link$")
    public void userHoversOnTheShopHeaderLink() throws Throwable {
        homePage.hoverOnElement(homePage.headerShopLink);
        Thread.sleep(2000);
    }

    @And("^user closes the alert if present$")
    public void userClosesTheAlertIfPresent() throws Throwable {
        homePage.closeVuseAlertIfPresent();
    }

    @And("^user hovers over on the Vype Subscriptions link$")
    public void userHoversOnTheVypeSubscriptionsLink() throws Throwable {
        Thread.sleep(1000);
        homePage.waitForExpectedElement(By.cssSelector("body > div.page-wrapper > header > nav > div.custom-categories > div > div > ul > li.level0.category-item.icon-subscriptions.third > a:nth-child(2)")).click();
    }

    @And("^users clicks on the Vype Subscriptions link from SHOP Menu$")
    public void userClicksOnTheVypeSubscriptionsLinkFromShopMenu() {
        homePage.waitForExpectedElement(homePage.lnkVypeSubscriptions).click();
    }

    @And("^user hovers over on the See all Vype devices link$")
    public void userHoversOnTheSeeAllVypeDevicesLink() throws Throwable {
        if (UrlBuilder.getLocale().equals("uk")||(UrlBuilder.getLocale().equals("vuseuk"))) {
            homePage.hoverOnElement(homePage.DEVICES_LINK_UK_CX);
        } else {
            homePage.hoverOnElement(By.xpath("//*[contains(text(),'See all Vype devices')]"));
            Thread.sleep(1000);
        }
    }

    @And("^users clicks on the See all Vype devices link from SHOP Menu$")
    public void userClicksOnTheSeeAllVypeDevicesLinkFromShopMenu() {
        if (UrlBuilder.getLocale().equals("vusefr")||(UrlBuilder.getLocale().equals("vuseuk"))) {
            homePage.waitForExpectedElement(homePage.DEVICES_LINK_UK_CX).click();
        } else {
            homePage.waitForExpectedElement(By.xpath("//*[contains(text(),'See all Vype devices')]")).click();
        }
    }

    @When("^Go back to myAccount links NewsLetter link$")
    public void navigateToMyaccountPage(){
        getWebDriver().navigate().to(UrlBuilder.getUrl()+"/customer/account/index/");
        if(UrlBuilder.getLocale().equals("vuseit"))
        {
            try {
                homePage.waitForExpectedElement(NEWSLETTER_MARKETING_OPTION_VUSEIT).click();
            } catch (Exception e) {
                homePage.clickByElementByQueryJSExecutor(NEWSLETTER_MARKETING_OPTION_VUSEIT);
            }
        }
        else {
            homePage.waitForExpectedElement(NEWSLETTER_MARKETING_OPTION).click();
        }
    }

    @And("^select from myAccount links NewsLetter link$")
    public void selectFromMyAccountLinksNewsLetterLink() {
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
            case "lyftse":
            case "it":
            case "kz":
            case "pl":
            case "glode":
            case "vuseza":
            case "vuseco":
                pgeNewsletters.clickNewslettersLink();
                break;
            case "uk":
            case "vuseuk":
            case "vusefr":
                LOG.info("UK Site - selecting Newsletter");
                try {
                    homePage.clickByElementByQueryJSExecutor(CLOSE_POPUP);
                } catch (Exception e) {
                    LOG.info("No UK NewsLetter popup present - continuing");
                }
                if(!homePage.getCurrentUrl().contains("account")){
                    homePage.clickPersonIcon();
                    homePage.clickLinkOnPersonMenu("myAccountLink.key");}
                pgeNewsletters.clickNewslettersLink();
                break;
            //added try/catch for VYPE_IT to make it work at home page as well as My Account page
            case "vypeit":
            case "vuseit":
                try {
                    pgeNewsletters.clickNewLetterButton();
                }catch (Exception e) {
                    homePage.clickOnNewsletterButton();
                }
                break;
            default:
                homePage.waitForExpectedElement(NEWSLETTER_LINK).click();
                break;
        }
    }

    @And("^assert meta data present$")
    public void assertMetaDataPresent() {
        if (homePage.getWebDriver().getCurrentUrl().contains("/mx/es/")) {
            assertTrue("Expected META DATA NOT PRESENT, MISSING META KEYWORD DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"keywords"));
            assertTrue("Expected META DATA NOT PRESENT, MISSING META TITLE DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"title"));
            assertTrue("Expected META DATA NOT PRESENT, MISSING META ROBOT DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"robot"));
            assertTrue("Expected META DATA NOT PRESENT, MISSING META VIEWPORT DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"viewport"));
        } else {
            assertTrue("Expected META DATA NOT PRESENT, MISSING META KEYWORD DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"keywords"));
            assertTrue("Expected META DATA NOT PRESENT, MISSING META TITLE DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"title"));
            assertTrue("Expected META DATA NOT PRESENT, MISSING META ROBOT DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"robot"));
            assertTrue("Expected META DATA NOT PRESENT, MISSING META VIEWPORT DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"viewport"));
            assertTrue("Expected META DATA NOT PRESENT, MISSING META DESCRIPTION DATA", homePage.getWebDriver().getPageSource().contains("meta name=\"description"));
        }
    }

    @And("^assert css block is as expected with display attribute of '(.*)'$")
    public void assertCssBlockIsAsExpected(String expectedCssDisplayAttribute) throws Throwable {
        Thread.sleep(3000);
        WebElement searchBarCssStatus = homePage.getWebDriver().findElement(By.cssSelector("#search_autocomplete"));
        String displayStatus = searchBarCssStatus.getCssValue("display");
        LOG.info("\n ******** Search attrib : " + searchBarCssStatus.getCssValue("display"));
        assertEquals("Display attribute isn't as expected", expectedCssDisplayAttribute, displayStatus);
    }

    @And("^user hovers over on the Blog link$")
    public void userHoversOnTheBlogLink() throws Throwable {
        homePage.userHoversOnTheBlogLink();
    }

    @And("^users clicks on the Blog link from More Menu$")
    public void userClicksOnTheBlogLinkFromMoreMenu() {
        homePage.userClicksOnTheBlogLinkFromMoreMenu();
    }

    @And("^assert css menu reference is displayed to user$")
    public void assertCssMenuReferenceIsDisplayedToUser() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
                assertTrue(homePage.waitForExpectedElement(homePage.homePageMainNavDropDown).isDisplayed());
                break;
            default:
                boolean dropDownMenuPresent = homePage.waitForExpectedElement(By.cssSelector("div.items.first.shop")).isDisplayed();
                assertTrue(dropDownMenuPresent);
        }
    }

    @And("^close the more menu$")
    public void closeTheMoreMenu() {
        homePage.clickByElement(By.cssSelector("body > div.more-menu-container > div > div.close > i"));
    }

    @And("^user hovers over on the See all Vype flavours link$")
    public void userHoversOnTheSeeAllVypeFlavoursLink() throws Throwable {
        if (UrlBuilder.getLocale().equals("uk")||(UrlBuilder.getLocale().equals("vuseuk"))) {
            homePage.hoverOnElement(homePage.ALL_FLAVOURS_UK_CX);
        } else {
            homePage.hoverOnElement(By.xpath("//*[contains(text(),'See all Vype flavours')]"));
            Thread.sleep(1000);
        }
    }

    @And("^users clicks on the See all Vype flavours link from SHOP Menu$")
    public void userClicksOnTheSeeAllVypeFlavoursLinkFromShopMenu() {
        if (UrlBuilder.getLocale().equals("vusefr")||(UrlBuilder.getLocale().equals("vuseuk"))) {
            homePage.waitForExpectedElement(homePage.ALL_FLAVOURS_UK_CX).click();
        } else {
            homePage.waitForExpectedElement(By.xpath("//*[contains(text(),'See all Vype flavours')]")).click();
        }
        homePage.waitForPage();
    }

    @And("^user hovers over on the About Vype header link$")
    public void userHoversOnTheAboutVypeHeaderLink() throws Throwable {
        homePage.hoverOnElement(homePage.headerAboutVypeLink);
        //homePage.clickNavMoreButton();
        Thread.sleep(2000);
    }

    @And("^user hovers over on the Our Blog link$")
    public void userHoversOnTheOurBlogLink() throws Throwable {
        if (UrlBuilder.getLocale().equals("uk")) {
            homePage.hoverOnElement(homePage.OUR_BLOG_LINK_UK_CX);
        } else {
            homePage.hoverOnElement(homePage.lnkOurBlog);
            Thread.sleep(1000);
        }
    }

    @And("^users clicks on the Our Blog link from About Vype Menu$")
    public void userClicksOnTheOurBlogFromAboutVypeMenu() {
        if (UrlBuilder.getLocale().equals("uk")) {
            homePage.waitForExpectedElement(homePage.OUR_BLOG_LINK_UK_CX).click();
        } else {
            homePage.waitForExpectedElement(homePage.lnkOurBlog).click();
        }
    }

    @And("click on Basket icon and proceed to Payment Page$")
    public void clickOnBasketIconAndProceedToPaymentPage() throws Throwable {
        batHelper.checkoutAproductAddedInBasket();
    }

    @And("^login with valid details on Lyft Lab site$")
    public void loginWithValidDetailsOnLyftLabSite() throws Throwable {
        Thread.sleep(2000);
        homePage.checkOutPopUpSignInLyftLab();
    }

    @Then("^empty basket for Lyft Lab Site$")
    public void emptyBasketLyftLabSite() {
        homePage.emptyBasketLyftLab();
    }

    @And("user clicks Newsletter Subscribe link at bottom of the page$")
    public void userClicksNewsletterSubscribeLinkAtBottomOfThePage() {
        homePage.clickOnNewsletterSubscribeLinkLyftLab();
    }

    @And("user navigates to Newsletter Subscription landing page$")
    public void userNavigatesToNewsletterSubscribeLandingPage() {
        assertTrue(homePage.waitForExpectedElement(homePage.NEWSLETTER_SUBSCRIBE_PGE).isDisplayed());
    }

    @And("^launch given URL and assert URL contains text and returns success$")
    public void userNavigatesToURLAndAssertTextContains(DataTable dtURLs) throws Throwable {
        homePage.navigateToURLAndAssertTextContains(dtURLs);
    }

    @And("^user clicks on Link and assert Url returns successful response$")
    public void userClicksOnLinkAndAssertURLreturnsSuccessfulResponse(DataTable dtLinks) throws Throwable {
        homePage.clickOnLinkAndAssertURLReturnsSuccess(dtLinks);
    }

    @Then("^assert stationary cookies icon and footer links are available$")
    public void assertStationaryCookiesIconAndFooterLinks() {
        homePage.verifyCookiesStationaryIconAndFooterLink();
    }

    @And("^assert PDF link is correct on policy page$")
    public void assertPDFLinkIsCorrectOnPolicyPage() {
        homePage.verifyPDFLinkOnNoticePage();
    }

    @And("user navigates to Registration Page$")
    public void userNavigatesToRegistrationPage() throws Throwable {
        homePage.userNavigatesToRegistrationPage();
    }

    @And("^url contains '(.*)' text$")
    public void urlContainsText(String urlContains) {
        homePage.urlContainsText(urlContains);
    }

    @When("^user navigates to my account page from header$")
    public void userNavigatesToMyAccountPageFromHeader() throws InterruptedException {
        homePage.clickPersonIcon();
        //In case dropdown option selecting required
        switch (UrlBuilder.getLocale()) {
            case "glode":
                if(UrlBuilder.isIPhone()||UrlBuilder.isIpad()){
                    homePage.clickByElementByQueryJSExecutor(homePage.LOGO_ICON_VUSEUK);
                    homePage.waitForPage();
                    homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
                    Thread.sleep(5000);
                    getWebDriver().navigate().to( getWebDriver().getCurrentUrl() + "customer/account/index");
                } else { // desktop
                    homePage.waitForExpectedElement(By.partialLinkText(UrlBuilder.getMessage("MyAccount.key"))).click();
                }
                break;
            case "de":
                homePage.deUniqueToLoginSelector();
                break;
            case "pl":
                try {
                    homePage.waitForExpectedElement(By.partialLinkText(UrlBuilder.getMessage("myAccount.key"))).click();
                }catch (Exception e){
                    if(UrlBuilder.isIPhone()||UrlBuilder.isIpad()){
                        homePage.clickByElementByQueryJSExecutor(homePage.LOGO_ICON_VUSEUK);
                        homePage.waitForPage();
                        homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
                        Thread.sleep(5000);
                        getWebDriver().navigate().to( getWebDriver().getCurrentUrl() + "customer/account/index");
                    }
                }
                break;
            case "vuseco":
                homePage.waitForExpectedElement(By.partialLinkText(UrlBuilder.getMessage("MyAccount.key"))).click();
                break;
            case "kz":
                if (!getWebDriver().getCurrentUrl().contains("customer/account/index"))
                    try {
                        homePage.waitForExpectedElement(MY_ACCOUNT_LINK_PL).click();
                    }catch (Exception e){
                        homePage.waitForExpectedElement(homePage.GO_TO_MY_ACCOUNT).click();
                        LOG.info("last title :"+ getWebDriver().getTitle());
                    }
                break;
            default:
                //in progress
        }
    }

    @Then("^user clicks on Store Locator link$")
    public void userClicksOnStoreLocatorLink() {
        homePage.clickOnStoreLocatorLink();
    }

    @And("^assert Shop Type dropdown is displayed$")
    public void assertShopTypeDropDownIsDisplayed() {
        homePage.assertShopTypeDropDownIsDisplayed();
    }

    @And("^assert Product Type dropdown is displayed$")
    public void assertProductTypeDropDownIsDisplayed() {
        homePage.assertProductTypeDropDownIsDisplayed();
    }

    @And("^assert Shop Type options '(.*)' available in the dropdown$")
    public void assertShopTypeOptionsAvailableInDropDown(String strDropDownOptions) {
        homePage.assertShopTypeDropdownOptions(strDropDownOptions);
    }

    @And("^assert Product Type options '(.*)' available in the dropdown$")
    public void assertSProductTypeOptionsAvailableInDropDown(String strDropDownOptions) {
        homePage.assertProductTypeDropdownOptions(strDropDownOptions);
    }

    @And("^assert option none is selected by default for both Shop and Product Types$")
    public void assertNoneSelectedByDefaultForBothShopAndProductTypesDropDowns() {
        homePage.verifyNoneSelectedByDefaultForBothShopAndProductTypes();
    }

    @And("^assert favorite icon is not present on header$")
    public void assertFavoriteIconIsNotPresent() {
        homePage.assertFavoriteIconIsNotPresent();
    }

    @And("^assert Newsletter button is not present on footer$")
    public void assertNewsletterButtonIsNotPresentOnFooter() {
        homePage.assertNewsletterButtonIsNotPresentOnFooter();
    }

    @And("^assert Newsletter Icon is not present on header$")
    public void assertNewsletterIconIsNotPresentOnHeader() {
        homePage.assertNewsletterIconIsNotPresentOnHeader();
    }

    @Then("^user navigates to PLP page for Devices link$")
    public void userNavigatesToPLPForDevicesLink() {
        homePage.verifyUserNavigatesToDevicesPLPAndVerifyFields();
    }

    @Then("^user navigates to PLP page for Caps link$")
    public void userNavigatesToPLPForCapsLink() {
        homePage.verifyUserNavigatesToCapsPLPAndVerifyFields();
    }

    @Then("^users clicks on Contact link from the header menu and assert url contains '(.*)'$")
    public void userClicksOnContactLinkFromHeaderMenu(String strUrl) {
        homePage.clickOnContactLinkFromHeaderAndAssertURLcontains(strUrl);
    }

    @Then("^user navigates to Contact page$")
    public void userNavigatesToContactPage() {
        homePage.userNavigatesToContactPage();
    }

    @Then("^assert Customer Information section in the footer menu$")
    public void assertCustomerInformationSectionInFooterMenu() {
        homePage.verifyCustomerInformationSectionFooter();
    }

    @And("^assert all the fields under Product Carousel block$")
    public void assertAllFieldsUnderProductCarouselBlock() {
        homePage.verifyFieldsUnderProductCarouselBlock();
    }

    @And("^assert carousel works on both left and right arrow CTAs$")
    public void assertCarouselWorksOnBothLeftAndRightArrowCTAs() {
        //homePage.verifyCarouselFunctionalityOnLeftRightArrowCTA();
    }

    @And("^user clicks on Select Categories menu and assert Sub-Links and their CTAs$")
    public void userClicksOnSelectCategoriesMenuAndAssertSubLinksCTAs() throws Throwable {
        homePage.clickOnSelectCategoriesMenuAndAssertSubLinks();
    }

    @And("^users clicks on '(.*)' link,assert url and page title$")
    public void userClicksOnBlogLinkAndAssertUrlAndPageTitle(String link) throws Throwable {
        homePage.clickOnBlogLinkAndAssertUrlAndPageTitle(link);
    }

    @Then("^Subscription item has banner in minicart$")
    public void subscriptionItemHasBannerInMinicart() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "vuseza":
            case "lyftse":
                clickOnBasketIcon();
                assertTrue(homePage.waitForExpectedElement(homePage.GLOBAL_SUBS_MINI_CART_BANNERSE).isDisplayed());
                break;
            default:
                try{
                    assertTrue(homePage.waitForExpectedElement(homePage.GLOBAL_SUBS_MINI_CART_BANNER).isDisplayed());
                }catch (TimeoutException te){
                    LOG.info("Minicart display is not open");
                    clickOnBasketIcon();
                    assertTrue(homePage.waitForExpectedElement(homePage.GLOBAL_SUBS_MINI_CART_BANNER).isDisplayed());
                }
        }
    }

    @And("^assert Free Delivery Banner for Sweden Site$")
    public void assertFreeDeliveryBannerForSwedenSite() {
        homePage.assertFreeDeliveryBannerForSwedenSite();
    }

    @And("^assert Free Delivery Banner for EN Site$")
    public void assertFreeDeliveryBannerForENSite()  {
        homePage.assertFreeDeliveryBannerForENSite();
    }

    @And("^assert Free Delivery Banner is displayed on all LAB pages$")
    public void assertFreeDeliveryBannerIsDisplayedOnAllLABPages()  {
        homePage.assertFreeDeliveryBannerOnAllLABPages();
    }

    @And("^assert '(.*)' text link and navigation on CTA$")
    public void assertPrivacyPolicyLinkAndNavigationOnCTA(String strExpectedLink) throws Throwable {
        homePage.assertPrivacyPolicyLinkAndNavigationOnCTA(strExpectedLink);
    }

    @And("fetch the Delay Time configured in Magento Admin for Salesforce Live Chat pop-up$")
    public void fetchDelayTimeConfiguredInBackendForSalesforceLiveChatPopUp() {
        homePage.fetchDelayTimeFromBackendForLiveChatPopUp();
    }

    @Then("search for a Device '(.*)' and checkout$")
    public void searchForADeviceAndCheckout(String strSearchTerm) throws Throwable {
        batHelper.checkoutAproduct(strSearchTerm);
    }

    @Then("select a GLO product and checkout$")
    public void selectGloProductAndCheckout() throws Throwable {
        batHelper.checkoutAproductOnGlo();
    }

    @Then("assert Cartridge pop-up is displayed on Checkout Page$")
    public void assertCartridgePopUpIsDisplayedOnCheckoutPage() {
        homePage.assertCartridgePopUpOnCheckout();
    }

    @Then("assert Cartridge pop-up is not displayed on Checkout Page$")
    public void assertCartridgePopUpIsNotDisplayedOnCheckoutPage() {
        homePage.assertCartridgePopUpNotDisplayedOnCheckout();
    }

    @Then("user clicks on See Cartridges button from the pop-up and navigated to Cartuchos PLP$")
    public void userClicksOnSeeCartridgesButtonFromPopUpAndNavigatedToCartuchosPLP() throws Throwable {
        homePage.clickOnSeeCartridgesButtonFromPopUpAndNavigateToCartuchosPLP();
    }

    @Then("user clicks on Continue with Payment button and assert user remains on Checkout page with pop-up closed$")
    public void userClicksOnContinueWithPaymentButtonAndAssertPopUpclosedOnCheckoutPage() {
        homePage.clickOnContinueButtonAndAssertPopUpClosedOnCheckoutPage();
    }

    @Then("user clicks on Close button from the pop-up and assert CTA$")
    public void userClicksCloseButtonFromThePopUpAndAssertCTA() {
        homePage.userClicksCloseButtonFromThePopUpAndAssertCTA();
    }

    @Given("user logins Magento Admin and fetch the delay Time for Salesforce Live Chat pop-up$")
    public void userLoginsIntoMagentoAdminAndFetchDelayTimeForSalesforceLiveChatPopUp() {
        homePage.userLoginIntoMagentoAdminAndFetchChatPopUpDelayTime();
    }

    @Given("^user logins Magento Admin home page and assert Free Delivery Banner Configuration$")
    public void userLoginsMagentoAdminHomePageAndAssertFreeDeliveryBannerConfiguration() throws Throwable {
        homePage.loginIntoMagentoAdminHomePageAndAssertFreeDeliveryBanner();
    }

    @And("^user navigates to BAT home page for FE validation$")
    public void userNavigatesToBATHomePageForFEValidation() {
        homePage.userNavigatesToBATHomePageForFEValidation();
    }

    @And("^assert text of SignUp popup Header '(.*)' is displayed$")
    public void assertTextOfSignUpPopupIsDisplayed(String expectedText)  {
        homePage.assertHeaderTextOfSignUpPopupIsDisplayed(expectedText);
    }

    @And("^assert text of SignUp popup Message '(.*)' is displayed$")
    public void assertTextOfSignUpPopupMessageIsDisplayed(String expectedText) {
        homePage.assertMessageTextOfSignUpPopupIsDisplayed(expectedText);
    }

    @Then("^user clicks on Join Now button$")
    public void userClicksOnJoinNowButton() {
        homePage.clickOnJoinNowButton();
    }

    @And("^verify user is directed to SetUp An Account page$")
    public void verifyUserIsDirectedToSetUpAnAccountPage() {
        homePage.verifyAccountSetupPageIsOpened();
    }

    @And("^user clicks on Close button from the pop-up and assert pop-up is closed$")
    public void userClicksOnCloseButtonFromThePopupAndAssertPopupIsClosed() throws Throwable {
        homePage.clickOnCloseButtonFromSignUpPopUpAndVerifyPopupIsClosed();
    }

    @And("^assert URL contains text '(.*)'$")
    public void assertURLContainsText(String expectedText) {
        homePage.urlContainsText(expectedText);
    }

    @Then("^asset sign Up pop-up is not displayed$")
    public void assertSignUpPopupIsNotDisplayed() {
        homePage.signUpPopUpIsNotVisible();
    }

    @And("^assert internal redirect of header links URLs with success status code$")
    public void assertInternalRedirectOfHeaderLinksWithSuccessStatusCode() throws Throwable {
        homePage.assertInternalRedirectOfHeaderURLlinksWithStatusCode();
    }

    @And("^assert internal redirect of footer links URLs with success status code$")
    public void assertInternalRedirectOfFooterLinksWithSuccessStatusCode() throws Throwable {
        homePage.assertInternalRedirectOfFooterLinksWithStatusCode();
    }

    @And("^assert internal redirect of Devices Sub-links URLs with success status code$")
    public void assertInternalRedirectOfDevicesSubLinksWithSuccessStatusCode() throws Throwable {
        homePage.assertInternalRedirectOfDevicesSublinksWithStatusCode();
    }

    @And("^assert internal redirect of Flavours Sub-links URLs with success status code$")
    public void assertInternalRedirectOfFlavourSubLinksWithSuccessStatusCode() throws Throwable {
        homePage.assertInternalRedirectOfFlavoursSublinksWithStatusCode();
    }

    @And("^user navigates to PLP page and adds a product to basket$")
    public void userNavigatesToPLPPageAndAddsAProductToBasket() throws Throwable {
        userNavigatesToPLPPage();
        plp.addProductToBasket();
        if (!UrlBuilder.getLocale().equals("mx") && !UrlBuilder.getLocale().equals("velopl") && !UrlBuilder.getLocale().equals("veloza")&& !UrlBuilder.getLocale().equals("velobe") && !UrlBuilder.isFirefox())
            homePage.confirmMiniBasketDisplayedAmountOf("1");
    }

    @And("^user navigates to PLP page and adds a Non Trial product to basket$")
    public void userNavigatesToPLPPageAddNonTrial() throws Throwable {
        homePage.navigateToPLPVype();
        homePage.waitForPage();
        plp.addProductToBasket();
    }

    @And("^launches given URL and assert URL contains text and returns success$")
    public void userNavigatesToURLAndAssertsTextContains() {
        homePage.userNavigatesToURLAndAssertsTextContains();
    }

    @And("^users clicks on the url text link and verify url dost not contains meta tag keywords$")
    public void userNavigateToURLAndAssertsTextDoesNotContains(DataTable dtURLs) {
        homePage.navigateToURLAndVerifyTheURLNotContains(dtURLs);
    }

    @And("^verify Lyft logo at checkout page is displayed$")
    public void verifyLyftLogoAtChechOutPageIsDisplayed() {
        homePage.assertLyftLogoAtCheckOutPageIsDisplayed();
    }

    @And("^assert url of the page is '(.*)' when users click on Box icon$")
    public void assertURLOfThePage(String url) {
        homePage.assertNavigatedToCorrectUrl(url);
    }

    @And("^assert FAQ content is displayed if user click on Faq icon$")
    public void assertFAQOption() throws Throwable {
        homePage.assertFaqOptionIsDisplayed();
    }

    @And("^verify page title is '(.*)' when user click on Blog Link$")
    public void verifyPageIsOpened(String url) {
        homePage.assertNavigatedToCorrectBlogUrl(url);
    }

    @And("^verify product is getting opened$")
    public void verifyProductIsGettingOpened() {
        homePage.assertProductIsGettingOpened();
    }

    @And("^assert what is Lyft Lab content is displayed$")
    public void whatIsLyftLabContentIsDisplayed() {
        homePage.assertWhatIsLyftLabContentIsDisplayed();
    }

    @Then("^(?:Glo|velo|lyft|vype|Vuse|user) should be redirected to (.*) page$")
    public void userShouldBeRedirectedTo(String key) {
        if (UrlBuilder.isMobile() && UrlBuilder.getLocale().equals("it"))
        {
            homePage.waitForAjaxElementNotToBePresent(getWebDriver(),20);
        }
        assertThat(homePage.getCurrentPageTitle()).contains(UrlBuilder.getMessage(key));
    }

    @When("^click on about page link href (.*)$")
    public void clickOnAboutPageLinkWithHrefAboutPageUrlKey(String hrefKey) {
        homePage.clickOnAboutPageLink(hrefKey);
    }

    @And("^check Email checkbox isn't selected$")
    public void checkEmailCheckboxIsnTSelected() {
        pgeNewsletters.isEmailUnchecked();
    }

    @And("^ensure text of '(.*)' is displayed$")
    public void ensureTextOfSubscriptionEmailKeyIsDisplayed(String text) {
        pgeNewsletters.isEmailTextDisplayed(UrlBuilder.getMessage(text));
    }

    @And("^check SMS checkbox isn't selected$")
    public void checkSMSCheckboxIsnTSelected() {
        pgeNewsletters.isSMSUnchecked();
    }

    @And("^check text of '(.*)' is displayed$")
    public void checkTextOfNewsletterSubscriptionSMSKeyIsDisplayed(String text) {
        pgeNewsletters.isSMSTextDisplayed(text);
    }

    @And("^delete product from remove button$")
    public void deleteProductFromRemoveButton() {
        homePage.deleteProductFromRemoveButton();
    }

    @And("^verify mini cart amount '(.*)'$")
    public void amountOnMiniCart(String strBasketQty) {
        homePage.amountOnMiniCart(strBasketQty);
    }

    @Then("^Customer navigates to Device Trial landing page$")
    public boolean customerNavigatesToDeviceTrialLandingPage() {
        return homePage.assertDeviceTrialLandingPage();
    }

    @Then("^Customer clicks Start Device Trial link on landing page$")
    public void customerClicksOnDeviceTrialCTA() {
        homePage.clickOnTheDeviceTrialCTA();
    }

    @And("^configurable product is present$")
    public boolean assertConfigurableProduct() {
        return homePage.assertConfigurableProduct();
    }

    @Then("^customer selects configurable product$")
    public void customerSelectsConfigurableProduct() {
        homePage.selectConfigurableProduct();
    }

    @And("Device Trial error message is displayed$")
    public void deviceTrialErrorMessageIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(GLO_ERROR_MESSAGE).isDisplayed());
    }

    @And("^user hovers over Glo shop and selects Neo Sticks$")
    public void hoverOverGloShopAndSelectNeoStick() {
        homePage.hoverOverGloShopAndSelectNeoSticks();
    }

    @And("^Device Trial Link from Header Menu is displayed$")
    public void deviceTrialLinkFromMenuIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(By.linkText("DEVICE TRIAL"), 20).isDisplayed());
    }

    @When("^customer click on cart icon and Navigate to cart Page")
    public void customerNavigatesToCartPage() {
        homePage.customerClicksOnBasketIcon();
    }

    @And("Quantity Picker should not be displayed$")
    public void cartQuantityPickerIsNotDisplayed() {
        homePage.waitForExpectedElement(homePage.inputItemQuantity, 10);
        Boolean qtyPickerDisplayed = homePage.getWebDriver().findElements(homePage.inputItemQuantity).size() == 1;
        LOG.info("\n ***** Qty checker Boolean :  " + qtyPickerDisplayed);
        assertFalse(qtyPickerDisplayed);
    }

    @And("Quantity Picker is displayed$")
    public void cartQuantityPickerIsDisplayed() {
        assertTrue(homePage.cartQuantityPickerIsPresent());
    }

    @And("login is displayed$")
    public boolean loginContainerIsDisplayed() {
        return homePage.loginContainerIsPresent();
    }

    @And("^switch to EN site$")
    public void switchToENSite() {
        homePage.switchToENSite();
    }

    @And("^Enter a item quantity of amount '(.*)'$")
    public void enterAItemQuantityOfAmountQuantity(String itemQuantity) {
        homePage.enterItemQuantityPDP(itemQuantity);
    }

    @And("^user proceeds to the cart page$")
    public void userProceedsToTheCartPage() {
        homePage.clickOnProceedToCartCTA();
    }

    @And("^(|logged-in )user selects to order subscription items first with '(.*)' discount$")
    public void userSelectsToOrderSubscriptionItemsFirst(String loggedIn, String discount) throws Exception {
        if (loggedIn.isEmpty()) {
            homePage.selectOrderSubsFirstCTA(false, discount);
        } else {
            homePage.selectOrderSubsFirstCTA(true, discount);
        }
    }

    @And("^user clicks on the policy link in the footer$")
    public void userClicksOnThePolicyLinkInTheFooter() {
        homePage.selectGlobalSubsPolicyLink();
    }

    @Then("^quantity should be set to '(\\d+)'$")
    public void quantityShouldBeSetTo(String qty) {
        homePage.assertItemQuantityPDP(qty);
    }

    @Then("^'(.*)' Message is (|not )displayed in Cart page$")
    public void messageIsDisplayedInCartPage(String errorMessageKey, String not) {
        if (errorMessageKey.contains("Min")) {
            if (!not.isEmpty()) {
                homePage.assertCartMessageHasDisappeared();
            }
            homePage.globalSubsErrorAssertCart(UrlBuilder.getMessage(errorMessageKey));
        }else if(errorMessageKey.contains("Max")){
            globalSubscriptionsPage.globalSubsErrorAssertNoStringFilter(UrlBuilder.getMessage(errorMessageKey));
        }
        else {
            String errorMessage = UrlBuilder.getMessage(errorMessageKey).replaceAll("[^A-Za-z0-9]", "");
            homePage.globalSubsErrorAssertCart(errorMessage);
        }
    }

    @Then("^user is able to proceed to checkout page$")
    public void userIsAbleToProceedToCheckoutPage() {
        assertTrue(homePage.cartCheckoutButtonIsEnabled(homePage.CHECKOUT_CART_BUTTON));
    }

    @And("^click on checkout button on cart page$")
    public void clickOnCheckoutButtonOnCartPage() {
        homePage.clickCheckoutButtonOnCartPage();
    }

    @And("^assert description is displayed for each cookie type$")
    public void assertCookieDescriptionDisplayedForEachCookieType() {
        homePage.assertCookieDescriptionDisplayedForEachCookieType();
    }

    @And("^assert Cookie Settings link is displayed for each cookie type$")
    public void assertCookieDetailsLinkDisplayedForEachCookieType() {
        homePage.assertCookieDetailsLinkDisplayedForEachCookieType();
    }

    @And("^assert Cookie Settings link CTA for each cookie type$")
    public void assertCookieSettingsLinkCTAForEachCookieType() {
        homePage.assertCookieSettingsLinkCTAForEachCookieType();
    }

    @And("^close the mini basket menu$")
    public void closeTheMiniBasketMenu() {
        homePage.closeBasketSlidingPanel();
    }

    @And("^User should click on the link assert Free Delivery Banner is displayed$")
    public void userClickOnLink(DataTable dataTable) {
        int i = 0;
        for (List<String> elementItem : dataTable.raw()) {
            homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(elementItem.get(i)))).click();
            homePage.assertFreeDeliveryBannerOnLABPages();
        }
    }

    @And("^assert Free Delivery Banner is displayed on LAB pages$")
    public void assertFreeDeliveryBannerIsDisplayedOnLABPages() {
        homePage.assertFreeDeliveryBannerOnLABPages();
    }

    @And("^search and add 2 products into the cart '(.*)'$")
    public void selectProductFromTextAndNavigatesPDPPage(String searchTerm) throws Throwable {
        pdp.performSearch(searchTerm);
        pdp.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("searchVypeStartKit.key"))).click();
        pdp.selectProductStrengthOrColour();
        pdp.clickAddToCartButton();
        pdp.performSearch(searchTerm);
        searchResult.selectSearchResult();
        pdp.selectProductStrengthOrColour();
        pdp.clickAddToCartButton();
        homePage.openBasketifNotOpenLogic();
        homePage.waitForExpectedElement(homePage.VIEW_BASKET).click();
        homePage.waitForExpectedElement(homePage.PAGE_TITLE_WRAPPER);
    }

    @Then("^assert \"([^\"]*)\" meta data tag content$")
    public void assertMetaDataTagContent(String attribute) {
        List<WebElement> elements = homePage.getMetaTagElementsWith(attribute);
        switch (UrlBuilder.getLocale()) {
            case "vusede":
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(),4);
                String strContent = elements.get(0).getAttribute("content");
                if (UrlBuilder.getUrl().contains("non-prod")) {
                    assertThat(strContent.equals("NOINDEX,NOFOLLOW")).isTrue();
                } else {
                    assertThat(strContent.equals("INDEX,FOLLOW")).isTrue();
                }
                break;
            default:
                elements.stream().map(webElement -> webElement.getAttribute("content")).forEach(s -> {
                    if (UrlBuilder.getUrl().contains("non-prod")) {
                        assertThat(s.equals("NOINDEX,NOFOLLOW")).isTrue();
                    } else {
                        assertThat(s.equals("INDEX,FOLLOW")).isTrue();
                    }
                });
        }
    }

    @And("^assert stationary cookie icon is not displayed on home page$")
    public void assertStationaryCookieIconNotDisplayedOnHomePage() {
        assertTrue(homePage.invisibilityOfElementLocated(homePage.OT_COOKIE_ICON));
    }

    @And("^assert Cookie settings link and CTA in Footer$")
    public void assertCookieSettingsLinkCTAInFooter() {
        homePage.assertCookieSettingsLinkCTAInFooter();
    }

    @When("^user clicks individual cookie setting link$")
    public void userClicksIndividualCookieSettingLink() {
        homePage.clickIndividualCookieSettingLink();
    }

    @When("^user clicks update cookie setting link$")
    public void userClicksUpdateCookieSettingLink() {
        homePage.clickUpdateCookieLink();
    }

    @And("^user hovers over on the Vuse Care link$")
    public void userHoversOnTheVuseCareLink() throws Throwable {
        homePage.hoverOnElement(homePage.VUSE_CARE_LINK);
    }

    @And("^users clicks on the Vuse Care link from About Vype Menu$")
    public void userClicksOnTheVuseCareFromAboutVypeMenu() {
        homePage.waitForExpectedElement(homePage.VUSE_CARE_LINK).click();
    }

    @And("^User clicks on Insiders Club menu option$")
    public void userClicksOnMyRewards() {
        homePage.clickMyRewardsMenuButton();
    }

    @When("^User clicks on Insiders Club menu option of IT$")
    public void userClicksOnInsidersClubMenuOptionOfIT() {
        homePage.clickInsidersClubMenuButton();
    }

    @And("^Select '(.*)' from header Menu$")
    public void selectInsidersClubHeaderMenuOptKeyFromHeaderMenu(String headerText) {
        homePage.clickOnHrefLink(headerText);
    }

    @And("^user wait for home page to load$")
    public void userWaitHomePageToLoad() {
        homePage.userWaitHomePageToLoad();
    }

    @And("^cart icon should display the quantity '(.*)'$")
    public void cartIconShouldDisplayTheQuantity(String qty) {
        homePage.waitForAjaxElementNotToBePresent(batHelper.getWebDriver(), 10);
        Assertions.assertThat(homePage.getCartQty()).isEqualTo(qty);
    }

    @And("^user navigate to my account page$")
    public void navigateToAccountDashboard() {
        homePage.navigateToAccountDashboard();
    }

    @And("^user navigate to link and verify URL and title$")
    public void userNavigateToLinkAndVerifyURLAndTitle(List<Map<String, String>> mapList) throws Throwable {
        for (Map map : mapList) {
            String headers = UrlBuilder.getMessage((String) map.get("LinkToClick"));
            if(UrlBuilder.isDesktop()){
                try {
                    homePage.getWebDriver().findElement(By.linkText(headers)).click();
                } catch (Exception e) {
                    homePage.waitAndClickByElementByJSExecutor(By.linkText(headers), 10);
                }
            }
            else {
                M_clickHamburgerMenu();
                homePage.getWebDriver().findElement(By.linkText(headers)).click();
                homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            }
            String urls = UrlBuilder.getMessage((String) map.get("UrlToContain"));
            homePage.getWebDriver().getCurrentUrl().contains(urls);
            String titles = UrlBuilder.getMessage((String) map.get("Title"));
            homePage.getWebDriver().getTitle().equals(titles);
        }
    }

    @And("^user clicks on product menu$")
    public void user_clicks_on_product_menu() {
        homePage.clickOnProudctMenu();
    }

    @When("^user click on cart icon and Navigate to cart Page$")
    public void user_click_on_cart_icon_navigate_to_cart_Page() throws InterruptedException {
        Thread.sleep(5000);
        homePage.clickOnBasketIcon();
        Thread.sleep(5000);
    }

    @And("^user closes the Secure Your Purchase alert if present$")
    public void userClicksSecureYourPurchaseAlertIfPresent() {
        homePage.closeSecurePurchaseAlertIfPresent();
    }

    @And("^user navigates to '(.*)' PLP page and adds a product to basket$")
    public void userNavigatesToPLPPageForHealthWarningPopup(String linkToSelect){
        homePage.navigateToPLPForHealthWarningPopup(linkToSelect);
    }

    @And("^assert '(.*)' is displayed$")
    public void assertOptionIsDisplayed(String option){
        if(option.equals("back button"))
            assertTrue(plp.waitForExpectedElement(plp.HEALTH_WARNING_POPUP_BACK_BUTTON,5).isDisplayed());
        else if(option.equals("continue to cart"))
            assertTrue(plp.waitForExpectedElement(plp.HEALTH_WARNING_POPUP_INTHECART_BUTTON,5).isDisplayed());
    }

    @And("^assert google optimize code snippet '(.*)' is present in pagesource$")
    public void assertGoogleOptimizeCodeSnippetPresentInPageSource(String textToFind){
        assertTrue(homePage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(textToFind)));
    }

    @And("^switch to '(.*)' site using language selector$")
    public void switchToSiteUsingLanguageSelector(String strLanguage) {
        homePage.switchToSiteUsingLanguageSelector(strLanguage);
    }

    @And("^user goes to my account page$")
    public void userGoesToMyAccountPage() {
        homePage.clickPersonIcon();
        homePage.clickLinkOnPersonMenu("myAccountLink.key");
    }

    @And("^verify checkout button is disabled$")
    public void userVerifyCheckoutButtonIsDisabled() {
        homePage.verifycheckoutbuttonisdisabled();
    }

    @Then("^click on edit details$")
    public void userClickOnEditDetails() {
        homePage.userclickoneditdetails();
    }

    @Then("^user profile picture is not displayed$")
    public void userProfilePictureIsNotDisplayed() {
        homePage.assertprofilepicturenotdisplayed();
    }


    @Then("^click on delete my account and proceed$")
    public void deleteUserAccountAndProceed() {
        homePage.userdeletecustomeraccount();
    }

    @Then("^conditions of Sale page is disaplyed$")
    public void verifyConditionsOfSalePageDisplayed() {
        homePage.assertconditionsofsaledisplayed();
    }

    @When("^user clicks Right of withdrawal link$")
    public void userClicksRightOfWithdrawalLink() {
        homePage.clickRightOfWithWithdrawalLink();
    }


    @When("^user clicks Satisfaction Guaranteed link$")
    public void userClicksSatisfactionGuaranteedLink() {
        homePage.clickSatisfactionGuranteedLink();
    }

    @When("^user click on mix and match from shop flavour$")
    public void userClickMixAndMatch() {
        homePage.clickMixAndMatch();
    }

    @And("^user increases the quantity of product$")
    public void userIncreasesProductQuantity() {
        homePage.waitForExpectedElement(homePage.btnIncreaseProductQty, 5).click();
        homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 5);
    }

    @And("^click back button on view basket$")
    public void userClickBackButton() {
        homePage.waitForExpectedElement(homePage.btnBack, 10).click();
        homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 5);
    }


    @Then("^subscription error message is not display$")
    public void verifySubscriptionErrorMessageNotDisplay() {
        homePage.assertSubscriptionErrorMessageNotDisplay();
    }

    @When("^user clicks Accessibility link$")
    public void userClicksAccessibilityLink()  {
        homePage.clickAccessibilityLink();
    }

    @And("^user navigates to the About Vuse link$")
    public void userNavigatesToTheAboutVuseLink() {
        homePage.clickAboutVuse();
    }

    @And("^user clicks on personal data link$")
    public void userClicksOnPersonalDataLink(){
        homePage.clickPersonalDataLink();
    }

    @Then("^assert all elements are displayed$")
    public void assertAllElementsAreDisplayed()  {
        accountPage.isPersonalDataPresent();
    }

    @And("^user navigates to PLP page$")
    public void userNavigatesToPLPPage() {
        switch (UrlBuilder.getSite()) {
            case "glo":
                homePage.navigateToPLPGlo();
                break;
            case "vype":
            case "vuse":
                homePage.scrollToPageTop();
                homePage.navigateToPLPVype();
                if(!UrlBuilder.isSamsungMobile())
                    homePage.waitForPage();
                break;
            case "lyft":
                homePage.usersClicksOnTheLogoutTextLink("ProductCategoryText.key");
            case "velo":
                paymentPage.navigateToPlp();
                break;
            case "velobe":
                paymentPage.navigateToPlp();
                break;
        }
    }


    @And("^social media icons are displayed$")
    public void verifySocialMediaIcons()
    {
        switch (UrlBuilder.getLocale())
        {
            case "pl":
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.INSTAGRAM_ICON_PL,5));
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.FACEBOOK_ICON_PL,5));
                break;
            case "lyftse":
                homePage.scrollToElement(homePage.FACEBOOK_ICON_LYFT);
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.FACEBOOK_ICON_LYFT,10));
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.INSTAGRAM_ICON_LYFT,10));
                break;
            default:
                assertTrue(homePage.isElementDisplayedBySeconds(INSTAGRAM_ICON,5));
                assertTrue(homePage.isElementDisplayedBySeconds(YOUTUBE_ICON,4));
                assertTrue(homePage.isElementDisplayedBySeconds(FACEBOOK_ICON,4));
                assertTrue(homePage.isElementDisplayedBySeconds(TWITTER_ICON,4));
        }
    }

    @And("^payment icon are displayed$")
    public void verifyPaymentIconDisplayed() {
        homePage.scrollToPageBottom();
        assertTrue(homePage.isElementDisplayedBySeconds(PAYMENT_ICON,5));
    }

    @Then("^assert Health Warning text of '(.*)' present$")
    public void ensureHealthWarningTextIsPresent(String textToBePresent) {
        By healthBy=null;
        switch (UrlBuilder.getLocale()){
            case "kz":
                healthBy=homePage.HEALTH_WARNING_KZ;
                assertTrue(getWebDriver().findElement(healthBy).getAttribute("innerHTML").contains(UrlBuilder.getMessage(textToBePresent)));
                break;
            default:
                healthBy=homePage.HEALTH_WARNING_FR;
                assertTrue(homePage.getTextFor(healthBy).contains(UrlBuilder.getMessage(textToBePresent).trim()));
        }
    }

    @And("^user hovers over on promos$")
    public void userHoversOnPromos() throws Throwable {
        homePage.hoverOnElement(homePage.PROMOS);
    }

    @When("^users clicks on promos '(.*)' text link$")
    public void usersClicksOnPromosTextLink(String linkText) {
        homePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
    }

    @Then("^user see How it works description$")
    public void verifyHowItWorksDescription() {
        Assert.assertTrue(homePage.waitForExpectedElement(HOW_IT_WORKS, 5).isDisplayed());
    }

    @Then("^user can join poistive change$")
    public void userJoinPositiveChange() {
        homePage.userJoinPositiveChange();
    }

    @When("^I click on the \"([^\"]*)\" link$")
    public void iClickOnTheLink(String url) throws Throwable {
        paymentPage.clickOnLink(url);
    }

    @Then("^I confirm I am on the \"([^\"]*)\" page$")
    public void iConfirmIAmOnThePage(String url) throws Throwable {
        paymentPage.IamOnPage(url);
    }
    @Then("^user should be able to see Insiders Club menu option$")
    public void userShouldBeAbleToSeeInsidersClubMenuOption() {
        assertTrue(homePage.verifyInsidersClubOption());
    }

    @Then("^user should be able to see Insiders Club menu option in header$")
    public void userShouldBeAbleToSeeInsidersClubMenuInHeader() {
        assertTrue(homePage.verifyInsidersClubHeaderOption());
    }


    @Then("^I will see a pop up asking me to register a device$")
    public void iWillSeeAPopUpAskingMeToRegisterADevice() {
        assertTrue(homePage.checkRegisterDevicePopUpIsPresented());
    }

    @And("^closing the popup will take me to My Account Page$")
    public void closingThePopupWillTakeMeToMyAccountPage() {
        homePage.closePopUP();
    }

    @Then("^user select combos from shop devices$")
    public void userSelectCombosFromShopDevices() {
        homePage.userSelectCombos();
    }

    @Then("^verify age module have date, month, year dropdown menu$")
    public void verifyAgeModuleDropDownMenu() {
        homePage.assertAgeModuleDropDownMenu();
    }

    @And("^assert under 18 and over 18 button displayed$")
    public void verifyUnder18AndAbove18Button() {
        homePage.assertUnder18AndAbove18ButtonDisplayed();
    }

    @And("^assert sticky bar is present$")
    public void verifyStickyBarAtFooter() throws Throwable {
        assertTrue(homePage.isElementDisplayedBySeconds(STICKY_BAR,5));
    }

    @And("^user hovers over on the About Vuse link$")
    public void userHoversOverOnTheAboutVuseLink() {
        homePage.hoverOnElement(homePage.VUSE_DE_About_VUSE_LINK);
    }

    @Then("^sub menu links are present$")
    public void subMenuLinksArePresent() {
        assertTrue(homePage.isThirtyDaysLinkPresent());
        assertTrue(homePage.isToInviteFriendLinkPresent());
    }

    @Then("^select Refer a friend from About Vuse header$")
    public void userSelectReferAFriendFromHeader() {
        homePage.selectReferAFriendFromHeader();
    }

    @And("^refer a friend link button is present$")
    public void verifyReferAFriendButtonPresent() {
        assertTrue(homePage.isElementDisplayedBySeconds(homePage.REFER_A_FRIEND_BUTTON_ZA,5));
    }

    @Then("^user get error 404 page displayed$")
    public void verifyError404PageDisplayed() {
        homePage.assertError404PageDisplayed();
    }

    @Given("^user navigates to URL '(.*)'$")
    public void navigateToURL(String link)  {
        String URL = UrlBuilder.getMessage(link);
        homePage.getWebDriver().get(URL);
    }

    @Then("^page should also display a section with flavours$")
    public void verifyFlavourSectionDisplayed()  {
        assertTrue(homePage.isElementDisplayedBySeconds(FLAVOUR_SECTION,5));
    }

    @Then("^clicking on the CTA buttons, users should navigate to the appropriate pages$")
    public void verifyCTAButtonNavigateToCorrectPage()  {
        homePage.verifyCTAButtonsNavigateToCorrectPage();
    }

    @Then("^select About (?:vuse|velo) from About (?:Vuse|Velo) header$")
    public void userSelectAboutVuseFromHeader() {
        homePage.selectAboutVuseFromHeader();
    }

    @Then("^CTA links should be properly handled and navigate to the correct pages$")
    public void verifyCTALinkNavigateToCorrectPage()  {
        homePage.verifyCTALinksNavigateToCorrectPage();
    }

    @Then("^Health warning banner and text '(.*)' is display$")
    public void assetHealthWarningBannerIsPresent(String textToBePresent) {
        assertTrue(homePage.isElementDisplayedBySeconds(homePage.HEALTH_WARNING_BANNER, 5));
        assertTrue(homePage.getTextFor(homePage.HEALTH_WARNING_BANNER).contains(UrlBuilder.getMessage(textToBePresent)));
    }

    @Then("^conditions of Sale page is displayed$")
    public void verifyConditionsOfSalePageIsDisplayed() {
        homePage.assertconditionsofsaledisplayed();
    }

    @And("^user click on '(.*)' menu navigation$")
    public void userClickOnMenuNavigation(String menu) {
        switch (menu) {
            case "neo sticks":
            homePage.clickOnSticksMenu();
            break;
            case "device":
            plp.clickOnDeviceMenu();
            break;
            default:
                plp.waitForExpectedElement(By.linkText(menu)).click();
        }
    }

    @Then("^verify product count is same as product present when user click on menu navigation$")
    public void verifyProductCountDisplayedIsSameAsProductProductWhenClickingOnMenu(List<String> menuList) {
        menuList.forEach(s->userClickOnMenuNavigation(s));
        plp.verifyProductCountDisplayedIsSameAsProductProduct();
    }

    @Then("^assert pop up banner displayed$")
    public void assertPopUpBannerDisplayed() {
        assertTrue(homePage.assertPopUpBannerContentCorrect());
        assertTrue(homePage.isElementPresentByby(homePage.RDB_POPUP_BANNER));
    }

    @Then("^user should be able to see Insiders Club T&C option in footer$")
    public void assertFooterLoyaltyLinkDisplayed() {
        assertTrue(accountPage.isElementDisplayedBySeconds(INSIDERS_CLUB_FOOTER,5));
    }

    @And("^users click on Instagram icon and redirect$")
    public void userClickOnInstagramIconAndRedirect() {
        homePage.clickOnInstagramIconFooter();
        homePage.switchBetweenWindowTabs(1);
    }

    @Then("^verify user is directed to '(.*)'$")
    public void verifyUserIsRedirectedTo(String urlContains) {
        homePage.verifyUserIsRedirectedTo(urlContains);
    }

    @And("^select a bring friend way if it is presence$")
    public void selectABringFriendWayIfItIsPresence() {
        if(UrlBuilder.isMobile()&&UrlBuilder.getLocale().equals("it"))
        {
            try {
                userWaitHomePageToLoad();
                homePage.scrollElementIntoView(homePage.M_BREING_FRIEND_WAY_LINK_GLOIT);
                homePage.clickUsingJS(homePage.M_BREING_FRIEND_WAY_LINK_GLOIT);
            }
            catch (Exception ex)
            {
                homePage.refreshBrowser();
                userWaitHomePageToLoad();
                homePage.clickUsingJS(homePage.M_BREING_FRIEND_WAY_LINK_GLOIT);
            }
        }
        else  {
            if (!homePage.isElementPresentByby(homePage.BREING_FRIEND_WAY_LINK))
            {
                userWaitHomePageToLoad();
            }
            homePage.clickUsingJS(homePage.BREING_FRIEND_WAY_LINK);
        }
    }

    @When("^user navigate to every page and assert health warning is displayed$")
    public void userNavigateToBelowPageAndAssertHealthWarningIsDisplayed() {
        homePage.clickPersonIcon();
        homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("InviteFriendTop.key").toUpperCase())).click();
        ensureHealthWarningTextIsPresent("healthWarningText.key");
        Integer subMenuSize = homePage.getSubMenuSize();
        for (int i = 2; i < subMenuSize + 2; i++) {
            WebElement subMenu = homePage.getMenuElement(i);
            homePage.clickUsingJS(subMenu);
            homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            ensureHealthWarningTextIsPresent("healthWarningText.key");
        }
    }

    @When("^users clicks on the '(.*)' text link mobile$")
    public void usersClicksOnTheLogoutTextLinkMobile(String linkText) {
        homePage.waitForItemToBeClickableAndClick(homePage.DASHBOARD_MENU_EXPAND_FR, 10);
        homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText)), 10);
        homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    @And("^user click on the Link and assert url$")
    public void userClickOnTheLinkAndAssertUrl(DataTable dataTable) {
        homePage.clickOnLinkAndAssertExpectedUrl(dataTable);
    }

    @And("^users clicks on the '(.*)' from here to help menu on footer$")
    public void usersClicksOnTheLinkByTextFromHereToHelpMenu(String linkText) {
        homePage.usersClicksOnTheLinkByTextFromHereToHelpMenu(linkText);
    }

    @When("^users clicks on the '(.*)' header text link$")
    public void usersClicksOnHeaderTextLink(String linkText) throws Throwable {
        M_clickHamburgerMenu();
        homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(linkText).toUpperCase()), 10);
        homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    @And("^existing user error msg is displayed$")
    public void existingUserErrorMsgIsDisplayed() {
        Assert.assertEquals(homePage.waitForExpectedElement(GLO_ERROR_MESSAGE).getText(),UrlBuilder.getMessage("existingUserErr.key"));
    }

    @And("^assert msg on free gift item$")
    public void assertMsgOnFreeGiftItem() {
        if (UrlBuilder.getLocale().equals("it")) {
            Assert.assertEquals(homePage.waitForExpectedElement(GLOIT_FREE_GIFT_MESSAGE).getText(), UrlBuilder.getMessage("freeGiftItemMsg.key"));
        } else {
            Assert.assertEquals(homePage.waitForExpectedElement(FREE_GIFT_MESSAGE).getText(), UrlBuilder.getMessage("freeGiftItemMsg.key"));
        }
    }

    @And("^user hovers on header menu and navigates to submenu$")
    public void userHoversOnHeadermenuMenuAndNavigatesToSubmenu(
            DataTable dataTable) {
        List<List<String>> row=dataTable.raw();
        for(List r:row){
            plp.userHoverHeaderMenu(r.get(0).toString());
            plp.waitForPresenceOfElement(By.linkText(UrlBuilder.getMessage(r.get(1).toString())));
            plp.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(r.get(1).toString())));
            plp.waitForPage();
            assertTrue(plp.getCurrentPageTitle().contains(UrlBuilder.getMessage(r.get(2).toString())));
        }
    }

    @Then("^assert message of '(.*)' is displayed on the page$")
    public void assertMessageOfIsDisplayedOnThePage(String expectedText) {
        assertTrue(accountPage.returnSuccessMessage().contains(UrlBuilder.getMessage(expectedText)));
    }

    @And("^the url on the '(.*)' page is relative path$")
    public void assertTheUrlIsRelativePath(String pageName, DataTable table) {
        List<List<String>> lstTextURLs = table.raw();
        By css=null;
        switch (pageName) {
            case "Privacy Policy":
                css=By.cssSelector("div.column.main div[data-content-type='row'] a");
                for (List<String> entry:lstTextURLs) {
                    if(homePage.waitForExpectedElement(css).getText().contains(UrlBuilder.getMessage(entry.get(0)))){
                        softAssertions.assertThat(homePage.waitForExpectedElement(css).getAttribute("href").contains(UrlBuilder.getMessage(entry.get(1))))
                        .as("Expected url: "+UrlBuilder.getMessage(entry.get(1))+" Actual url: "+homePage.waitForExpectedElement(css).getAttribute("href"))
                                .isTrue();
                    };
                }
                break;
            default:

        }
    }

    @And("^user click on the '(.*)' link$")
    public void userClickOnTheLink(String link) {
        homePage.userClickOnTheLink(link);
    }

    @And("^click taeka header for glo de$")
    public void clickTaekaHeaderForGloDe() {
        homePage.clickTaekaHeaderLink();
    }

    @And("^assert visit correct environment$")
    public void assertVisitCorrectEnvironment() {
        assertTrue(homePage.isCorrectEnv());
    }

    @When("^user navigate to device trail page$")
    public void userNavigateToDeviceTrailPage() {
        homePage.clickDeviceTrailCTA();
    }

    @And("^select over (\\d+) age confirmation option$")
    public void selectOverAgeConfirmationOption(int age) {
        if(age>=18)
            homePage.tryClickIAmOver18();
    }

    @And("^assert cookie banner should appear$")
    public void assertCookieBannerShouldAppear() {
        assertTrue(homePage.waitForExpectedElement(
                homePage.allowCookiesButton).isDisplayed());
    }

    @Then("^Lyft users click on Facebook link$")
    public void lyftUsersClickOnFacebookLink() {
        homePage.clickOnFacebookIcon();
    }

    @And("^assert lyft page title is '(.*)'$")
    public void assertPageTitleIsLyftHomePage(String expectedTitle) throws Throwable
    {
        homePage.waitForPage();
        LOG.info("Page title : " + homePage.getWebDriver().getTitle());
        homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedTitle),
                homePage.getCurrentPageTitle());
    }

    @And("^navigate back to lyft homepage$")
    public void navigateBackToLyftHomepage() {
        homePage.getWebDriver().navigate().back();
        assertTrue(homePage.getWebDriver().getCurrentUrl().contains(UrlBuilder.getUrl()));
    }

    @Then("^Lyft users click on Instagram link$")
    public void lyftUsersClickOnInstagramLink() {
        homePage.clickOnInstagramIcon();
    }

    @And("^assert lyft page title contains '(.*)'$")
    public void assertLyftPageTitleContainsHomepage(String expectedTitle) throws Throwable {
        homePage.waitForPage();
        LOG.info("Page title : " + homePage.getWebDriver().getTitle());
        homePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedTitle),
                homePage.getCurrentPageTitle());
    }

    @And("^user click toilet paper on glo it home page$")
    public void userClickToiletPaperOnGloItHomePage() {
        homePage.clickSlickSlider();
    }

    @And("^assert link social account cta is displayed$")
    public void assertLinkSocialAccountCTAIsDisplayed() {
        assertTrue(homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("linkSocialAccountCTA.key")), 10).isDisplayed());}
    @When("^users click on Instagram link at stayConnected$")
    public void users_click_on_Instagram_link_at_stayConnected() {
        homePage.clickOnInstagramIconatstayConnected();
    }

    @When("^users click on Facebook link at stayConnected$")
    public void users_click_on_Facebook_link_at_stayConnected() {
        homePage.clickOnFacebookIconatstayConnected();
    }

    @When("^users click on Twitter link at stayConnected$")
    public void users_click_on_Twitter_link_at_stayConnected() {
        homePage.clickOnTwitterIconatstayConnected();
    }
    @And("^social media icons are displayed on footer$")
    public void verifySocialMediaIconsOnFooter()
    {
        switch (UrlBuilder.getLocale())
        {
            case "lyftse":
                homePage.scrollToElement(homePage.FACEBOOK_ICON_ON_FOOTER_LYFT);
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.FACEBOOK_ICON_ON_FOOTER_LYFT,10));
                assertTrue(homePage.isElementDisplayedBySeconds(homePage.INSTAGRAM_ICON_ON_FOOTER_LYFT,10));
                break;
            default:
        }
    }

    @Then("^Lyft users click on Facebook link on footer$")
    public void lyftUsersClickOnFacebookLinkOnFooter() {
        homePage.clickOnFacebookIconOnFooter();
    }

    @Then("^assert error message for submitting newsletter without filling any details$")
    public void assertErrorMessageAfterSubmittingNewsletterWithoutDetails() {
        homePage.assertErrorMessageAfterSubmittingNewsletterWithoutDetails();
    }

    @Then("^assert error message for incorrect email format$")
    public void assertErrorMessageForIncorretEmailFormat() {
        homePage.assertErrorMessageForIncorrectFormat();
    }

    @And("^the account has been deleted$")
    public void theAccountHasBeenDeleted() {
        assertTrue(homePage.waitForURLToContain(UrlBuilder.getMessage("DeleteRegistrationUrl.key"),20));
    }

    @Then("^assert error message for incorrect email format reset password$")
    public void assertErrorMessageForIncorrectEmailFormatResetPassword() {
        homePage.assertErrorMessageForIncorrectEmailFormatResetPassword();
    }

    @Then("^verify two different row for product in basket$")
    public void assertDifferentRowForProducts() {
        homePage.assertDifferentRowForProducts();
    }

    @And("^assert filter option strength is in ascending order$")
    public void assertFilterStrengthIsAscendingOrder()  {
        homePage.assertFilterStrengthIsAscendingOrder();
    }

    @And("^basket with empty message and homepage button is present and redirected$")
    public void basketWithEmptyMessageAndHomepageButtonIsPresentAndRedirected() {
        homePage.basketWithEmptyMessageAndHomepageButtonIsPresentAndRedirected();}

    @Then("^link to Homepage is displayed$")
    public void linkToHomepageIsDisplayed() {
        homePage.linkToHomepageIsDisplayed();
    }

    @Then("^link to MyAccountpage is displayed$")
    public void linkToMyAccountpageIsDisplayed() {
        homePage.linkToMyAccountageIsDisplayed();
    }

    @And("^homepage link is present and redirected$")
    public void homepageLinkIsPresentAndRedirected() {
        homePage.homepageLinkIsPresentAndRedirected();}

    @And("^click on logo and assert navigate to Homepage$")
    public void clickOnTheLogoAndAssertNavigationHomepage() {
        homePage.clickOnTheLogoAndAssertNavigationHomepage();
    }

    @And("^user click on search icon and submits search term '(.*)' and select product display on left$")
    public void userClickOnSearchIconAndSubmitsSearchTermSelectProductOnLeft(String searchTerm) throws Throwable {
        pdp.userClickOnSearchIconAndSubmitsSearchTermSelectProductOnLeft(searchTerm);
    }

    @And("^user click on search icon and submits search term '(.*)' and select text display on right$")
    public void userClickOnSearchIconAndSubmitsSearchTermSelectTextOnRight(String searchTerm) throws Throwable {
        plp.userClickOnSearchIconAndSubmitsSearchTermSelectTextOnRight(searchTerm);
    }

    @Then("^assert cookie banner should not appear$")
    public void assertCookieBannerShouldNotAppear() {
        assertFalse(homePage.isElementPresentByby(homePage.allowCookiesButton));
    }

    @Then("^assert link \"([^\"]*)\" is not present$")
    public void assertLinkIsNotPresent(String linkText) {
        assertFalse(homePage.isElementPresent(By.linkText(UrlBuilder.getMessage(linkText))));
    }

    @When("^user click on Facebook link on the footer$")
    public void userClickOnFacebookLinkOnTheFooter() {
        homePage.clickOnFacebookIconOnTheFooter();
        switch (UrlBuilder.getLocale()) {
            case "vuseit":
                homePage.switchBetweenWindowTabs(1);
                break;
            default:

        }
    }

    @When("^user click on Instagram link on the footer$")
    public void userClickOnInstagramLinkOnTheFooter() {
        homePage.clickOnInstagramIconOnTheFooter();
        switch (UrlBuilder.getLocale()) {
            case "vuseit":
                homePage.switchBetweenWindowTabs(1);
                break;
            default:

        }
    }

    @Then("^delete product and verify cart is empty$")
    public void deleteProductAndVerifyCartIsEmpty() {
        homePage.deleteProductAndVerifyCartIsEmpty();}

    @And("^Assert Url is as expected$")
    public void assertUrlIsAsExpected() {
        assertEquals(UrlBuilder.getUrl()+"/cookie-notice",getWebDriver().getCurrentUrl());
    }

    @And("^user clicks on allow cookies button$")
    public void userClicksOnAllowCookiesButton() {
        homePage.allowCookieButton();
    }

    @And("^User clicks on cookie icon on homepage$")
    public void userClicksOnCookieIconOnHomepage() {
        homePage.clickOnFlotingIcon();
        homePage.waitForExpectedElement(homePage.VELO_PL_ONETRUST_BLOCK,5).isDisplayed();
    }

    @And("^user click on home page link to return to home page$")
    public void userClickOnHomePageLink() {
        homePage.waitForExpectedElement(homePage.HOME_PAGE_TO_LOAD,10).click();
    }

    @When("^velo user scroll to follow us block and able to see social icons$")
    public void veloUserScrollToFollowUsBlockAndAbleToSeeSocialIcons() {
        homePage.goToFollowUsBlock();
    }

    @And("^user clicks the person icon after account created$")
    public void userClicksThePersonIconAfterAccountCreated() {
        homePage.clickPersonIconAfterCreateAccount();
    }
}
