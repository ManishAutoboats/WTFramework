package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.AccountDashboardPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItLoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.salmon.test.framework.helpers.UrlBuilder.LOCALE;
import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static org.testng.AssertJUnit.assertTrue;

public class GloItHomePageSteps {

	GloItHomePage gloItHomePage;
	GloItLoginPage gloItLoginPage;
	HomePage homePage;
	private AccountDashboardPage accountDashboardPage;
	private static final Logger LOG = LoggerFactory.getLogger(GloItHomePageSteps.class);

	public GloItHomePageSteps(GloItHomePage gloItHomePage, GloItLoginPage gloItLoginPage, AccountDashboardPage accountDashboardPage, HomePage homePage) {
		this.gloItHomePage = gloItHomePage;
		this.gloItLoginPage = gloItLoginPage;
		this.accountDashboardPage = accountDashboardPage;
		this.homePage = homePage;
	}

	@Given("^user navigates GloIt Homepage$")
	public void userNavigatesGloItHomePage() {
		gloItHomePage.getWebDriver().get(UrlBuilder.getMessage("site.url"));
	}

	@And("^GloIt tearDownBAT$")
	public void teardownBAT() {
		gloItHomePage.getWebDriver().manage().deleteAllCookies();
		gloItHomePage.getWebDriver().navigate().refresh();
	}

	@And("^GloIt resize browser screen - full screen$")
	public void resizeBrowserToFullScreen() {
		LOG.info("\n RE SIZING ****************************** Full SCREEN");
		gloItHomePage.getWebDriver().manage().window().maximize();
	}

	@And("^GloIt header Class is displayed$")
	public void GloItheaderClassIsDisplayed() {
		gloItHomePage.headerClassIsDisplayed();
	}

	@And("^Glo user clicks on buy button link and click on submenu$")
	public void user_clicks_on_buy_button_link_and_click_on_submenu() {
		gloItHomePage.clickOnBuyButtonAndItsSubMenu();
	}

	@And("^Glo user clicks on buy category link$")
	public void user_clicks_on_buy_category_link() {
		gloItHomePage.clickOnBuyCategoryLink();
	}
	
	@And("^user select sub menu from header '(.*)'$")
	public void user_select_sub_menu_from_header(String expectedValue1)  {
		gloItHomePage.selectSubmenuFromHeader(UrlBuilder.getMessage(expectedValue1));
	}

	@And("^GloIt select over 18$")
	public void gloItSelectOver() {
		gloItHomePage.pressConfirmOver18Button();
	}

	@And("^GloIt accept Cookies Button is displayed and enabled$")
	public void accept_Cookies_Button_is_displayed_and_enabled() {
		gloItHomePage.acceptCookiesButton();
	}

	@And("^assert glo pageTitle is '(.*)'$")
	public void assertPageTitleIsGloItHomePage(String expectedTitle) throws Throwable {
		gloItHomePage.waitForPage();
		LOG.info("Page title : " + gloItHomePage.getWebDriver().getTitle());
		gloItHomePage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedTitle),
				gloItHomePage.getCurrentPageTitle());

	}

	@When("^Glo user clicks on PersonIcon and Navigate to the Login Page$")
	public void glo_user_clicks_on_PersonIcon() {
		switch (Locale.valueOf(getLocale().toUpperCase())) {
			case IT:
				gloItHomePage.clicksOnPersonIcon();
				break;
			default:
				if (!(gloItHomePage.getWebDriver().getCurrentUrl().contains("customer/account/index"))) {
					gloItHomePage.clicksOnPersonIcon();
				}
		}
	}
	@And("^Glo user clicks on PersonIcon and Navigate to the Login Page after logout$")
	public void gloUserClicksOnPersonIconAfterLogout() throws InterruptedException {
		gloItHomePage.clicksOnPersonIconAfterLogout();
	}

	@When("GloIt PersonIcon is displayed$")
	public void gloIt_PersonIcon_is_displayed() {
		switch (UrlBuilder.getLocale()) {
			case "kz":
			case "pl":
				assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.PERSON_ICON_PL).isDisplayed());
				break;
			case "it":
				assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.PERSON_ICON_GLOIT).isDisplayed());
				break;
			default:
				assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.PersonIcon).isDisplayed());
		}

	}

	@When("^GloIt miniCart is displayed$")
	public void gloIt_miniCartIcon_is_displayed() {
		assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.GloItCartButton).isDisplayed());
	}

	@And("^click on proceed to checkout button on mini cart$")
	public void clickOnProceedToCheckoutButton() throws Throwable {
		gloItHomePage.OnMiniCartclickOnProceedToCheckoutButton();
	}

	@When("^Glo users click on Facebook link$")
	public void users_click_on_Facebook_link() {
		gloItHomePage.clickOnFacebookIcon();
		if(!UrlBuilder.getLocale().equalsIgnoreCase("pl"))
			gloItHomePage.switchBetweenWindowTabs(1);
	}

	@When("^Glo users click on Instagram link$")
	public void users_click_on_Instagram_link() {
		gloItHomePage.clickOnInstagramIcon();
		if(!UrlBuilder.getLocale().equalsIgnoreCase("pl"))
			gloItHomePage.switchBetweenWindowTabs(1);
	}

	@When("^Glo users click on youtube link$")
	public void users_click_on_youtube_link() {
		gloItHomePage.clickOnYoutubeIcon();
		gloItHomePage.switchBetweenWindowTabs(1);
	}

	@And("^ensure glo newsletter element is present on page$")
	public void ensureNewsletterElementIsPresentOnPage() {
		assertTrue(gloItHomePage.isElementPresent(gloItHomePage.gloNewsLetter));
	}

	@And("^ensure glo newsletter sign up button present on page$")
	public void ensureNewsletterSignUpButtonPresentOnPage() {
		gloItHomePage.jsScrollElementInCenter(gloItHomePage.waitForPresenceOfElement(gloItHomePage.gloNewsLetterSubscribeButton));
		if(gloItHomePage.isElementPresent(gloItHomePage.gloNewsLetterSubscribeButton)){
			assertTrue(gloItHomePage.isElementPresent(gloItHomePage.gloNewsLetterSubscribeButton));
		}
		else{
			LOG.info(gloItHomePage.getWebDriver().getPageSource());
			assertTrue(gloItHomePage.isElementPresent(gloItHomePage.gloNewsLetterSubscribeButton));
		}

	}

	@And("^click on glo newsletter button$")
	public void clickOnNewsletterButton() {
		gloItHomePage.clickOnNewsLetterButton();
	}

	@And("^enter glo newsletter details and random email and submit$")
	public void enterNewsletterDetailsAndRandomEmailAndSubmit() {
		gloItHomePage.enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit();
	}

	@And("^enter glo invalid newsletter details for glo and submit$")
	public void enterInvalidNewsletterDetailsAndSubmit() throws Throwable {
		gloItHomePage.enterNamesAndRandomInvalidEmailToNewsLetterSignUpAndSubmit();
	}

	@And("^Newsletter-glo user enters DOB$")
	public void populateDOBFieldWith() {
		gloItHomePage.waitForExpectedElement(By.cssSelector(".dob_date")).sendKeys("10");
		gloItHomePage.selectValueFromDropDownByIndex(3,By.cssSelector(".dob_month"));
		gloItHomePage.waitForExpectedElement(By.cssSelector(".dob_year")).sendKeys("1990");
	}

	@And("^assert newsletter success validation message of '(.*)'$")
	public void andAssertNewsletterErrorValidationMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainCom(
			String expectedError) {
		boolean blnFlag=false;
		try {
			blnFlag=gloItHomePage.isElementPresent(By.cssSelector("#maincontent > div.page.messages > div:nth-child(2) > div > div > div"));
			if(blnFlag) {
			String actualError = gloItHomePage
					.waitForExpectedElement(
							By.cssSelector("#maincontent > div.page.messages > div:nth-child(2) > div > div > div"))
					.getText();
			gloItHomePage.assertTrueWithCustomError(expectedError, actualError);}
		}
		catch(Exception ex) {
			LOG.info("Failed to assert newsletter success validation message due to exception: "+ex.getMessage());
		}
	}
	
	  @And("^glo user enter '(.*)' as lookup address$")
	  public void gloUserenterAsLookupAddress(String storeLocatorLookup) throws Throwable {
	    Thread.sleep(6000);
	    gloItHomePage.waitForExpectedElement(By.cssSelector("#pac-input")).sendKeys(storeLocatorLookup);
	    gloItHomePage.waitForExpectedElement(By.cssSelector("#pac-input")).sendKeys(Keys.DOWN);
	    gloItHomePage.waitForExpectedElement(By.cssSelector("#pac-input")).sendKeys(Keys.RETURN);
	  }
	  
	  @Then("^empty glo mini basket$")
	  public void emptyBasket() {
		  gloItHomePage.emptyMiniBasket();
	  }
	  
	  @Then("^Glo user clicks on Buy Link and add the product to mini cart$")
	  public void GloUserClicksOnBuyLinkAndAddProductToMiniCart() throws Throwable {
			gloItHomePage.clickOnBuyLinkAndAddProductToMiniCart();
	}
	  
  @Then("^Glo user deletes all the saved cards from My Saved Cards list$")
  public void GloUserDeletesSavedCardsFromMySavedCardsList() {
	  gloItHomePage.DeleteSavedCard();
	}

	@And("^Glo user clicks on buy button link and select first link from SubMenu$")
	public void userClicksOnBuyButtonAndThenSecondLinkFromSubMenu() {
		gloItHomePage.clickOnBuyButtonAndSelectFirstLinkFromSubMenu();
	}


	@And("^click on proceed to cart page from on mini cart$")
	public void clickOnProceedToCartPageFromMiniCart() {
		gloItHomePage.OnMiniCartclickOnProceedToCartPage();
	}

	@And("^Glo user clicks on Shop link and click on '(.*)' category link$")
	public void userClicksOnShopLinkAndClickOnSubCategoryLink(String strSubCategoryLink) {
		gloItHomePage.clickOnShopLinkAndClickOnSubCategoryLink(strSubCategoryLink);
	}

	@And("^Glo user navigate to '(.*)' page$")
	public void userNavigateToPage(String page) {
		gloItHomePage.navigateToGivenPage(page);
	}

	@And("^assert user have not subscribed to Newsletter by default$")
	public void assertUserNotSubscribedToNewsletterByDefault() {
		assertTrue(!gloItHomePage.waitForExpectedElement(gloItHomePage.NEWSLETTER_SUBSCRIPTION_CHECKBOX_ACCOUNT,10).isSelected());
	}

	@And("^assert user is auto subscribed to Newsletter after successful Device Trial Order$")
	public void assertUserAutoSubscribedToNewsletterAfterDeviceTrialOrder() {
		gloItHomePage.assertUserAutoSubscribedToNewsletterAfterDeviceTrialOrder();
	}

	@And("^assert user subscribed to Newsletter after successful Order$")
	public void assertUserSubscribedToNewsletterAfterSuccessfulOrder() {
		switch(UrlBuilder.getLocale()){
			case "glode":
				gloItHomePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsletterLink.key")));
				Assert.assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.NEWSLETTER_SUBSCRIPTION_SECTION, 10).getText().contains("NEWSLETTER ABONNEMENT"));
				break;
			default:
				assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.NEWSLETTER_SUBSCRIPTION_SECTION,10).getText().contains("Du abonnierst unser \"Allgemeines Abonnement\"."));
				gloItHomePage.userClicksNewsletterSubscriptionLinkFromMyAccount();
				assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.NEWSLETTER_SUBSCRIPTION_CHECKBOX_ACCOUNT,10).isSelected());
		}
    }

	@And("^user subscribes to the newsletter from My Account page$")
	public void userSubscribesToNewsletterFromMyAccountPage() {
		gloItHomePage.userSubscribesToNewsletterFromMyAccountPage();
	}

	@And("^assert error message for login button$")
	public void assertErrorMessageForLoginButton() {
		gloItHomePage.waitForExpectedElement(gloItHomePage.LOGIN_BUTTON_GUEST_POPUP, 10).click();
		assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.ERROR_USERNAME_GUEST_POPUP,5).isDisplayed());
		assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.ERROR_PASSWORD_GUEST_POPUP,5).isDisplayed());
	}

	@When("^Glo clicks on forgot password link on popup$")
	public void clicksOnForgotPasswordLinkOnPopup() {
		gloItHomePage.clicksOnForgotPasswordLinkOnPopup();
	}

	@And("^click on view cart for guest user$")
	public void clicksOnViewCartForGuest() {
		gloItHomePage.OnMiniCartClickOnViewBasket();
		gloItHomePage.CloseGoOnPopup();
	}

	@And("^Click on change Password$")
	public void clicksOnChangePassword() {
		gloItHomePage.clicksOnChangePassword();
	}

	@And("^click on proceed to checkout from cart page$")
	public void clicksOnCheckoutFromCartPage() {
		gloItHomePage.clicksOnCheckoutFromCartPage();
	}

	@And("^user clicks on PersonIcon and Navigate to My Account Page$")
	public void userClicksOnPersonIconAndNavigateToMyAccountPage() throws InterruptedException {
		switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
			case KZ:
				gloItHomePage.hoverOnElement(gloItHomePage.PERSON_ICON_PL);
				gloItHomePage.waitForExpectedElement(GloItHomePage.MY_ACCOUNT_LINK_PL, 10);
				gloItHomePage.clickByElementByQueryJSExecutor(GloItHomePage.MY_ACCOUNT_LINK_PL);
				break;
			case VUSEIT:
				gloItHomePage.hoverOnElement(gloItHomePage.PERSON_ICON_PL);
				gloItHomePage.waitForExpectedElement(GloItHomePage.MY_ACCOUNT_LINK_IT, 10);
				gloItHomePage.clickByElementByQueryJSExecutor(GloItHomePage.MY_ACCOUNT_LINK_IT);
				break;
			default:
			if (UrlBuilder.isIPhone() || UrlBuilder.isIpad()) {
				gloItHomePage.clickByElementByQueryJSExecutor(HomePage.LOGO_ICON_VUSEUK);
				gloItHomePage.waitForPage();
				gloItHomePage.waitForAjaxElementNotToBePresent(gloItHomePage.getWebDriver(), 10);
				Thread.sleep(5000);
				gloItHomePage.getWebDriver().navigate().to(gloItHomePage.getWebDriver().getCurrentUrl() + "customer/account/index");
			} else {
				gloItHomePage.waitForPage(5);
				gloItHomePage.waitForExpectedElement(gloItHomePage.PERSON_ICON_PL,5);
				gloItHomePage.hoverOnElement(gloItHomePage.PERSON_ICON_PL);
				gloItHomePage.waitForExpectedElement(GloItHomePage.MY_ACCOUNT_LINK_PL, 10);
				gloItHomePage.clickByElementByQueryJSExecutor(GloItHomePage.MY_ACCOUNT_LINK_PL);
			}
		}
	}

	@And("^user closes the RDB pop-up banner if present$")
	public void userClosesRDBPopUpBannerIfPresent() throws Throwable {
		gloItHomePage.userClosesRDBPopUpBannerIfPresentForKZ();
	}

	@And("^assert SEO friendly block on Device Trial Landing page below Start Trial CTA$")
	public void assertSEOFriendlyBlockOnDeviceTrialLandingPageBelowStartCTA(){
		assertTrue(gloItHomePage.waitForExpectedElement(gloItHomePage.DEVICE_TRIAL_SEO_FRIENDLY_BLOCK,5).isDisplayed());
	}

	@And("^enter glo newsletter details and random email and submit on registration page$")
	public void enterNewsletterDetailsAndRandomEmailAndSubmitOnRegistrationPage() {
		gloItHomePage.enterNamesAndRandomEmailToNewsLetterPopupOnRegistrationPage();
	}

	@And("^navigate to invalid or non-existing URL '(.*)'$")
	public void navigateToInvalidOrNonExistingURL(String strURL) {
		gloItHomePage.getWebDriver().navigate().to(gloItHomePage.getCurrentUrl() + UrlBuilder.getMessage(strURL));
	}

	@And("^user clicks on Continue Shopping button on 404 page$")
	public void userClicksOnContinueShoppingButtonOn404Page() {
		gloItHomePage.waitAndClickByElementByJSExecutor(gloItHomePage.CONTINUE_SHOPPING_BUTTON,10);
	}

	@And("^user clicks on Contact Us button on 404 page$")
	public void userClicksOnContactUsButtonOn404Page() {
		gloItHomePage.waitAndClickByElementByJSExecutor(gloItHomePage.CONTACT_US_BUTTON,10);
	}

	@And("^navigate back to Home page$")
	public void navigateBackToHomePage() {
		gloItHomePage.getWebDriver().navigate().back();
		assertTrue(gloItHomePage.getWebDriver().getCurrentUrl().contains(UrlBuilder.getUrl()));
	}

	@Then("^Assert the CTA 'FIND OUT HOW TO COLLECT INSIDERS COIN' is clickable$")
	public void assertCTAFindOutHowToCollectInsidersCoinIsClickable() {
		assertTrue(gloItHomePage.assertFindOutHowToCollectInsidersCoinCTAIsClickable());
	}

	@When("^user clicks on INSIDERS CLUB link$")
	public void userClicksOnInsidersClubLink() {
		gloItHomePage.clickInsidersClub();
	}

	@And("^click the CTA 'ISCRIVITI'$")
	public void clickTheCTASubscribe() {
		gloItHomePage.clickCTASubscribe();
	}

	@And("^user click on neo sticks menu navigation$")
	public void userClickOnNeoSticksMenuNavigation() {
		homePage.clickOnSticksMenu();
	}

	@And("^user clicks on Straight to GLO button on 404 page$")
	public void userClicksOnStraightToGLOButtonOn404Page() {
		gloItHomePage.clickOnStraigntToGLOButton();
	}

	@And("^user selects country on country selector page$")
	public void userSelectsCountryOnCountrySeletorPage() {
		gloItHomePage.selectsCountry();
	}
}