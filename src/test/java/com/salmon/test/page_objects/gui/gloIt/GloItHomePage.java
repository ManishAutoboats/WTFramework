package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.gui.AccountDashboardPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.OrderSuccessPage;
import com.salmon.test.page_objects.gui.PaymentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.UrlBuilder.LOCALE;
import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.framework.helpers.UrlBuilder.getUrl;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.gui.HomePage.PROCEED_TO_BASKET_PAGE;
import static com.salmon.test.page_objects.gui.constants.Locale.valueOf;
import static org.testng.Assert.assertTrue;

/**
 * @author msmith
 */
public class GloItHomePage extends PageObject {

	private AccountDashboardPage accountDashboardPage;
	private OrderSuccessPage orderSuccessPage;
	private HomePage homePage;
	private PaymentPage paymentPage;

	public GloItHomePage(AccountDashboardPage accountDashboardPage,OrderSuccessPage orderSuccessPage, HomePage homePage,PaymentPage paymentPage) {
		this.accountDashboardPage = accountDashboardPage;
		this.orderSuccessPage=orderSuccessPage;
		this.homePage=homePage;
		this.paymentPage=paymentPage;
	}

	// Age Verification
	private By confirmOver18 = By.cssSelector("button#btn-entry-age-allow");
	// Header links
	public By GloItHeaderClass = By.cssSelector("div.header-content.row");
	public final static By PROCEED_TO_CHECKOUT_BUTTON = By.cssSelector("#top-cart-btn-checkout");
	public final static By PROCEED_TO_CHECKOUT_BUTTON_PL = By.cssSelector("button.action.primary.checkout");
	public final static By PROCEED_TO_CHECKOUT_BUTTON_GLODE = By.cssSelector("button.action.primary.checkout");
	private static final By PROCEED_TO_VIEWBASKET = By.cssSelector("a.viewcart");
	public By gloItLogo = By.cssSelector("div.column.logo-container a");
	public static final By PERSON_ICON_GLOIT = By.cssSelector("div.column.user a");
	public By PersonIcon = By.cssSelector("span.icon-account,.icon-user");
	public final static By PERSON_ICON_PL = By.cssSelector("span.icon-account");
	public By GloItCartButton = By.cssSelector(".showcart");
	private By acceptCookiesButton = By.cssSelector("btn-cookie-allow");
	private final static By BUY_BUTTON = By.cssSelector("li.level0.category-item.third > a:nth-child(2),.nav-1.submenu-head.category-item.level-top.ui-menu-item:nth-child(1)>a,span.initial-cta__text.alink");
	private final static By BUY_BUTTON_IPAD = By.cssSelector(".nav-1.submenu-head.category-item.level-top.ui-menu-item:nth-child(1)>a");
	private final static By GLODE_SHOP_LINK = By.cssSelector("a.level-top.ui-corner-all,li.level0.category-item.second,li.level0.category-item.second > a:nth-child(2)");
	private static final By BUY_BUTTON_POLAND = By.cssSelector(".nav-1.category-item.level-top.ui-menu-item:nth-child(1) a.level-top.ui-corner-all > span:nth-child(1)");
	public static final By CLICK_ON_DEVICE_MENU = By.cssSelector("div.custom-categories > div > div > ul > li.level0.category-item.forth > a:nth-child(2)");
	private static final By PRODUCT_ITEM_LINK = By.cssSelector(".product-item-link");
	private static final By SHOP_MENU_SUB_ITEM = By.cssSelector(".nav-1.submenu-head.category-item.level-top.ui-menu-item:nth-child(1)>ol>li:nth-child(1)>a,li.level0.category-item.second");
	private static final By MOBILE_BUY_BUTTON_POLAND = By.cssSelector("body > div.burger-menu-container > ol > li.level0.nav-1.category-item.first.last.burger-menu-item > a > span");
	public By headerShopLink = By.cssSelector("li.level0.nav-1.submenu-head.category-item.level-top.ui-menu-item:nth-child(1)");
	public By headerShopLinkDE = By.cssSelector(".header-sub-menu>li:nth-child(2)");
	private final static By EMPTY_BASKET_CONFIRM_POPUP = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.confirm._show > div.modal-inner-wrap > footer > button.action-primary.action-accept,a.action.action-delete");
	private final static By SHOP_MENU_STICKS_SUB_LINK = By.xpath("//ol[@class='header-sub-menu']//a/span[contains(text(),'STICKS')]");
	private final static By GLO_DE_BAKSET_EMPTY_STATUS = By.cssSelector("span.counter.qty.empty");
	private final static By BAKSET_EMPTY_STATUS = By.cssSelector(".subtitle.empty");
	private static final By SHOP_MENU_SUB_ITEM_IPAD = By.cssSelector(".nav-1.submenu-head.category-item.level-top.ui-menu-item:nth-child(1)>ol>li>a");
	private final static By CLEAR_BUTTON = By.cssSelector("td.col.actions>a.action.delete");
	public final static By DELETE_YES_BUTTON = By.cssSelector("button.action-primary.action-accept");
	private final static By M_SHOP_LINK= By.cssSelector("div.slick-track div.slick-slide.slick-active:nth-child(2) div:nth-child(1) > a.category");
	private final static By SHOP_MAIN_MENU_STICKS_LINK = By.cssSelector("li.level0.category-item.second > a:nth-child(2)");
	public static final By MINI_CART_OPEN_STATUS = By.cssSelector("a.action.showcart.active");
	public static final By MY_ACCOUNT_LINK_PL = By.cssSelector("div.userLoggin > div > div > div > ul > li:nth-child(1) > a");
	public static final By INSIDERS_CLUB_GLO_HEADER = By.cssSelector("nav.navigation>ul>li:nth-child(4)>a");
	public static final By FIND_OUT_HOW_TO_COLLECT_INSIDERS_COIN_CTA = By.cssSelector("#maincontent > div.columns > div > div > div > div.orangeMe.minS > div > a > span");
	public static final By SUBSCRIBE_GLOIT_CTA = By.cssSelector("div.orangeMe>div>a.pagebuilder-button-primary");


	// social media links
	private By facebooklink = By.cssSelector(".fab.fa-facebook-f");
	private By instagramlink = By.cssSelector(".fab.fa-instagram");
	private By youtubelink = By.cssSelector(".fab.fa-youtube");
	public By gloNewsLetter = By.cssSelector("input#newsletter");
	public By gloNewsLetterSubscribeButton = By.cssSelector("footer.page-footer button.action.subscribe.primary");
	public final static By BASKET_ICON = By.cssSelector("a.action.showcart");
	public By txtFirstName = By.cssSelector("#firstname");
	public By txtLastName = By.cssSelector("#lastname");
	public By txtEmailAddress = By.cssSelector("input[id='newsletter'][name='email']");
	public By txtTelephone = By.cssSelector("#telephone");
	private static final By FIRST_NAME_TEXTBOX = By.cssSelector("form.form.subscribe #firstname");
	private static final By LAST_NAME_TEXTBOX = By.cssSelector("form.form.subscribe #lastname");
	private static final By TELEPHONE_TEXTBOX = By.cssSelector("div.telephonefield>input");
	public By chkNewsletterDeclaration = By.cssSelector("#newsletter-validate-detail > div.field.newsletter.newsletter-trms-error > label");
	public By btnNewsletterSubscribe = By.cssSelector("div.content form.form.subscribe div.actions:nth-child(5) > button.action.subscribe.primary");
	public By btnProductStrength = By.cssSelector("div.swatch-option.text");
	public By addToCartButton = By.cssSelector("#product-addtocart-button");
	public By btnSwatchColor = By.cssSelector("div.swatch-option.color");
	public final By BUTTON_GIFT_ITEMS_ADDTOCART_FIRST = By.cssSelector("div.products.wrapper.grid.products-grid li:nth-child(1) div.actions-primary button");
	public final By COUNTER_QTY_LOADER = By.cssSelector("counter qty _block-content-loading");
	public By customizeButton = By.cssSelector(" #bundle-slide");
	private final By firstBuyableProduct = By.xpath("//button[@class='action tocart primary']/ancestor::div[@class='product details product-item-details']//a[@class='product-item-link']");
	private final By prductItemLink = By.cssSelector(".product-item-link");
	public final static By MY_ACCOUNT_LINK_IT = By.cssSelector("div.userLoggout > div > div > div > ul > li:nth-child(1) > a");

	//mobile elements
	public final static By BURGER_MENU = By.cssSelector("span[data-action='toogle-nav']");
	public final static By BURGER_MENU_IT = By.cssSelector("span[data-action='toogle-nav'] > img");
	public final static By BURGER_MENU_GLODE = By.cssSelector(".icon-menu");
	private final static By SHOP_MENU_ITEM_BUTTON = By.cssSelector("li.burger-menu-item a[href*='shop']");
	private By GLODE_SHOP_NEO_STICK_BURGER_MENU_ITEM = By.cssSelector("li.burger-menu-item a[href*='sticks']");
	private By GLOTM_SHOP_NEO_STICK_BURGER_MENU_ITEM = By.cssSelector("li.burger-menu-item a[href*='glotm'],div.column.header-category div div div div div div div:nth-child(2) div a");
	private By GLODE_SHOP_NEO_STICK_BURGER_MENU_ITEM_MOBILE= By.cssSelector(".icon-glo-subscription");
	private By GLOPL_SHOP_NEO_STICK_BURGER_MENU_ITEM = By.cssSelector("li.burger-menu-item a[href*='tytoniu']");
	public final static By PROCESD_TO_CART_PAGE = By.cssSelector("#minicart-content-wrapper > div.block-content > div:nth-child(7) > div > a > span");

	public final static By NEWSLETTER_SUBSCRIPTION_SECTION = By.cssSelector("div.box.box-newsletter,div.page-title-wrapper > h1 > span");
	public final static By NEWSLETTER_SUBSCRIPTION_CHECKBOX_ACCOUNT = By.cssSelector("input#subscription");
	private final static By NEWSLETTER_SUBSCRIPTION_SAVE_BUTTON = By.cssSelector("button.action.save.primary");
	public final static By NEWSLETTER_SUBSCRIPTION_CHECKBOX_CHECKOUT = By.cssSelector("input#newsletter-subscription");
	public final static By LOGIN_BUTTON_GUEST_POPUP = By.cssSelector("button.action.action-login.secondary");
	public final static By ERROR_USERNAME_GUEST_POPUP = By.cssSelector("div#customer-email-error");
	public final static By ERROR_PASSWORD_GUEST_POPUP = By.cssSelector("div#pass-error");
	private final static By FORGOT_PASSWORD_GUEST_POPUP = By.cssSelector("div.secondary>a.action");
	private final static By CHECKOUT_ON_CARTPAGE = By.cssSelector("ul.checkout.methods.items.checkout-methods-items>li>button:nth-child(1)");
	private final static By GO_ON_POPUP = By.cssSelector("aside.modal-popup.custom-modal.modal-slide._show:nth-child(1) div.modal-inner-wrap:nth-child(2) footer.modal-footer > button:nth-child(1)");
	private final static By CART_ERROR_MESSAGE = By.cssSelector("div.message-error.error.message");
	private final static By SIGN_IN_LINK = By.cssSelector("div.userLoggout > div > div > div > ul > li > a,div.userLoggout ul.dropdown.account-dropdown > li:nth-child(1) > a");
	private static final By PROCEED_TO_CHECKOUT_KZ= By.cssSelector("button#top-cart-btn-checkouta.action.viewcart.primary");
	public static final By ADD_TO_BASKET_PL = By.cssSelector("#product-addtocart-button");
	private static final By VIEW_BASKET = By.cssSelector(".action.viewcart.primary");
	private static final By INSTAGRAMLINK_GLODE = By.cssSelector("div:nth-child(2) > div:nth-child(2) > p > a:nth-child(2)");
	private static final By APPLY_COUPAN_BUTTON = By.cssSelector("button.action.apply.action-apply");
	private static final By GLO_REMOVE_PRODUCT = By.cssSelector("button.action.primary.remove.button-flavoured");
	private static final By RDB_POPUP_BANNER= By.xpath("//div[@class='close-rdb-popup']//a[1]");
	public static final By DEVICE_TRIAL_SEO_FRIENDLY_BLOCK= By.xpath("//*[@id='devicetrial_cta']//following::div[1]");
	private static final By EDIT_PASSWORD = By.cssSelector("div.account-info__container > div > a.action.secondary > span");
	private static final By EDIT_PASSWORD_GLOIT = By.cssSelector("a.action.change-password");
	private static final By CART = By.cssSelector("header > div.header-container > div > div.header-top.row > div.column.minicart > div > a");
	private static final By SHOP_OPTION = By.cssSelector("#minicart-content-wrapper > div.block-content > div.actions-wrapper > a");
	private static final By LOADER = By.cssSelector("div.loading-mask");
	public static final By CONTINUE_SHOPPING_BUTTON=By.cssSelector("a.pagebuilder-button-primary");
	public static final By CONTACT_US_BUTTON=By.cssSelector("a.pagebuilder-button-secondary");
	public static final By STRAIGHT_TO_GLO_BUTTON=By.cssSelector("a.pagebuilder-button-primary");

	public void pressConfirmOver18Button() {
		waitForExpectedElement(confirmOver18, 10);
		clickByElement(confirmOver18);
	}

	public void clickOnNewsLetterButton() {
		try {
			clickByElementByQueryJSExecutor(gloNewsLetterSubscribeButton);
		}catch(Exception e){
			LOG.info("Exception catch " + e);
			clickUsingJS(gloNewsLetterSubscribeButton);
		}
	}

	public void headerClassIsDisplayed() {
		assertTrue(waitForExpectedElement(GloItHeaderClass, 10).isDisplayed());
	}

	public void acceptCookiesButton() {
		assertTrue(waitForExpectedElement(acceptCookiesButton).isDisplayed());
		assertTrue(waitForExpectedElement(acceptCookiesButton).isEnabled());
	}

	public void clicksOnPersonIcon() {
		switch (UrlBuilder.getLocale()) {
			case "pl":
				hoverOnElement(PERSON_ICON_PL);
				waitForExpectedElement(SIGN_IN_LINK, 10);
				try {
					clickByElementByQueryJSExecutor(SIGN_IN_LINK);
				}
				catch(Exception e){
					LOG.info("Exception catch " + e);
					hoverOnElement(PERSON_ICON_PL);
					waitForExpectedElement(SIGN_IN_LINK, 10);
					clickByElementByQueryJSExecutor(SIGN_IN_LINK);
			}
				break;
			case "it":
				try {
					hoverOnElement(PersonIcon);
					waitForExpectedElement(PersonIcon, 30).click();
				} catch (Exception e) {
					LOG.info("Exception catch " + e);
					clickUsingJS(PersonIcon);
				}
				break;
			case"kz":
				break;
			case "glode":
				hoverOnElement(PersonIcon);
				waitForExpectedElement(SIGN_IN_LINK, 10);
				clickUsingJS(SIGN_IN_LINK);
				break;
			default:
				waitForExpectedElement(PersonIcon, 30);
				try {
					webDriver.findElement(PersonIcon).click();
				} catch (Exception e) {
					LOG.info("Exception catch " + e);
					clickUsingJS(PersonIcon);
					break;
				}
		}
	}
	public void clicksOnPersonIconAfterLogout() throws InterruptedException {
		waitForPage();
		switch (UrlBuilder.getLocale()) {
			case "pl":
				hoverOnElement(PERSON_ICON_PL);
				break;
			default:
				hoverOnElement(PersonIcon);
		}
		waitForExpectedElement(SIGN_IN_LINK, 10);
		clickUsingJS(SIGN_IN_LINK);
	}

	public void clickOnBasketIcon() {
		clickByElement(GloItCartButton);
	}

	public void clickOnFacebookIcon() {
		switch (valueOf(getLocale().toUpperCase())) {
			case PL:
				waitAndClickByElementByJSExecutor(homePage.FACEBOOK_ICON_PL, 10);
				break;
			default:
				waitForExpectedElement(facebooklink, 10).click();
		}
	}

	public void clickOnInstagramIcon() {
		switch (UrlBuilder.getLocale()) {
			case "glode":
				waitForExpectedElement(INSTAGRAMLINK_GLODE, 10);
				clickByElementByQueryJSExecutor(INSTAGRAMLINK_GLODE);
				break;
			case "pl":
				waitAndClickByElementByJSExecutor(homePage.INSTAGRAM_ICON_PL,10);
				break;
			default:
			waitForExpectedElement(instagramlink, 10);
			clickByElementByQueryJSExecutor(instagramlink);
		}
	}

	public void clickOnYoutubeIcon() {
		waitForExpectedElement(youtubelink, 10);
		clickByElementByQueryJSExecutor(youtubelink);
	}

	public void OnMiniCartclickOnProceedToCheckoutButton() throws InterruptedException {
		switch (UrlBuilder.getLocale()) {
			case "kz":
			case "pl":
				try {
					waitForExpectedElement(PROCEED_TO_BASKET_PAGE, 10).click();
					homePage.goToBasketOnCheckoutPageAndTakeEyesScreenshot();
					if(UrlBuilder.isIPhone()) {
						try {
							waitForAjaxElementNotToBePresent(getWebDriver(), 15);
							clickUsingJS(PROCEED_TO_CHECKOUT_BUTTON_PL);
						}
						catch (Exception ex)
						{
							refreshBrowser();
							clickUsingJS(PROCEED_TO_CHECKOUT_BUTTON_PL);
						}
					}
					else {
						waitForAjaxElementNotToBePresent(getWebDriver(), 10);
						clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON_PL);
						paymentPage.goToAddNewAddressPageAndTakeEyesScreenShot();
					}
				} catch (Exception ex) {
					LOG.info("Failed to click on Proceed to Checkout button from basket window due to exception: "
							+ ex.getMessage());
					try {
						waitForExpectedElement(BASKET_ICON, 20).click();
					} catch (Exception e) {
						clickByElementByQueryJSExecutor(BASKET_ICON);
					}
					waitForExpectedElement(PROCEED_TO_BASKET_PAGE, 20).click();
					if(UrlBuilder.getLocale().equals("kz")){
						waitForAjaxElementNotToBePresent(getWebDriver(), 10);
						clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON_PL);
					}
				}
				break;
			case "glode":
				try {
					waitForElementToAppearAndDisappear(LOADER, 10,10);
					waitForItemToBeClickableAndClick(PROCEED_TO_CHECKOUT_BUTTON_GLODE,20);
				}catch (Exception e) {
					clickByElementByQueryJSExecutor(VIEW_BASKET);
					homePage.goToBasketPageAndTakeEyesScreenShot();
					waitForExpectedElement(APPLY_COUPAN_BUTTON, 20);
					clickFirstElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON_GLODE);
				}
				break;
			default:
				try {
					waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 20).click();
				} catch (Exception ex) {
					LOG.info("Failed to click on Proceed to Checkout button from basket window due to exception: "
							+ ex.getMessage());
					refreshBrowser();
					waitAndClickByElementByJSExecutor(BASKET_ICON,20);
					waitAndClickByElementByJSExecutor(PROCEED_TO_CHECKOUT_BUTTON,20);
					waitForAjaxElementNotToBePresent(getWebDriver(), 10);
				}
				switch (UrlBuilder.getLocale().toLowerCase()) {
					case "it":
						homePage.goToBasketPageAndTakeEyesScreenShot();
						paymentPage.goToAddNewAddressPageAndTakeEyesScreenShot();
						break;
				}
				break;
		}
	}

	public void OnMiniCartClickOnViewBasket() {
		try {
			waitForExpectedElement(PROCEED_TO_VIEWBASKET, 10).click();
		} catch (Exception ex) {
			LOG.info("Failed to click on Proceed to Checkout button from basket window due to exception: " + ex.getMessage());
			waitForExpectedElement(BASKET_ICON, 10).click();
			waitForExpectedElement(PROCEED_TO_VIEWBASKET, 10).click();
		}
	}

	public void clickOnBuyButtonAndItsSubMenu() {
		if (UrlBuilder.isIpad() || UrlBuilder.isDesktop()) {
			switch (UrlBuilder.getLocale()) {
				case "pl":
					if (UrlBuilder.isDesktop()) {
						waitForExpectedElement(CLICK_ON_DEVICE_MENU,20);
						clickByElementByQueryJSExecutor(CLICK_ON_DEVICE_MENU);
					} else if (UrlBuilder.isIpad()) {
						waitForExpectedElement(M_SHOP_LINK,5);
						clickByElementByQueryJSExecutor(M_SHOP_LINK);
					} else {
						waitForExpectedElement(MOBILE_BUY_BUTTON_POLAND, 10).click();
					}
					break;
				case "glode":
					if (UrlBuilder.isIpad()) {
						clickUsingJS(gloItLogo);
						waitForPage();
						waitForAjaxElementNotToBePresent(getWebDriver(),10);
						getWebDriver().navigate().to(getCurrentUrl() + "neo-sticks");
					} else {
						hoverOnElement(GLODE_SHOP_LINK);//For FF stability
						waitForPage();
						waitForExpectedElement(SHOP_MAIN_MENU_STICKS_LINK, 30);
						clickByElementByQueryJSExecutor(SHOP_MAIN_MENU_STICKS_LINK);
					}
					break;
				case "kz":
					if (UrlBuilder.isIpad()) {
						waitForAjaxElementNotToBePresent(getWebDriver(),5);
						waitForExpectedElement(GLOTM_SHOP_NEO_STICK_BURGER_MENU_ITEM, 8);
						clickFirstElementByQueryJSExecutor(GLOTM_SHOP_NEO_STICK_BURGER_MENU_ITEM);
					} else {
						waitForExpectedElement(GLODE_SHOP_LINK).click();
					}
					break;
				case "it":
					clickUsingJS(BUY_BUTTON_IPAD);
					break;
				default:
					if (UrlBuilder.isIpad()) {
						clickUsingJS(BUY_BUTTON_IPAD);
					} else
						waitForExpectedElement(BUY_BUTTON, 10).click();
					break;
			}
		} else {
			if(UrlBuilder.isIPhone()||(UrlBuilder.getLocale().equalsIgnoreCase("kz")||(UrlBuilder.isSamsungMobile())))
				LOG.info("No need to click on Burger Menu");
			else
				clickUsingJS(BURGER_MENU);
			switch (UrlBuilder.getLocale()) {
				case "glode":
					if(UrlBuilder.isSamsungMobile()||UrlBuilder.isIPhone()) {
						clickUsingJS(BURGER_MENU_GLODE);
						clickUsingJS(GLODE_SHOP_NEO_STICK_BURGER_MENU_ITEM_MOBILE);
					}
					else
						waitForExpectedElement(GLODE_SHOP_NEO_STICK_BURGER_MENU_ITEM).click();
					break;
				case "it":
					if(UrlBuilder.isMobile()) {
						clickUsingJS(BURGER_MENU_IT);
					}
					clickUsingJS(SHOP_MENU_ITEM_BUTTON);
					break;
				case "kz":
					if(UrlBuilder.isIPhone()){
						waitForAjaxElementNotToBePresent(getWebDriver(),5);
						clickFirstElementByQueryJSExecutor(GLOTM_SHOP_NEO_STICK_BURGER_MENU_ITEM);}
					else if(UrlBuilder.isSamsungMobile())
						clickByElementByQueryJSExecutor(GLOTM_SHOP_NEO_STICK_BURGER_MENU_ITEM);
					else
						clickByElementByQueryJSExecutor(GLOTM_SHOP_NEO_STICK_BURGER_MENU_ITEM);
					break;
				case "pl":
						clickByElementByQueryJSExecutor(M_SHOP_LINK);
					break;
				default:
					clickUsingJS(SHOP_MENU_ITEM_BUTTON);
					break;
			}
		}
	}

	public void clickOnBuyButtonAndSelectFirstLinkFromSubMenu() {
		switch (UrlBuilder.getLocale()) {
			case "glode":
				waitForPage();
				waitForExpectedElement(SHOP_MAIN_MENU_STICKS_LINK, 10);
				clickByElementByQueryJSExecutor(SHOP_MAIN_MENU_STICKS_LINK);
				break;
			default:
				try {
					clickUsingJS(BUY_BUTTON);
					waitForExpectedElement(SHOP_MENU_STICKS_SUB_LINK, 10);
					clickByElementByQueryJSExecutor(SHOP_MENU_STICKS_SUB_LINK);
					waitForExpectedElement((PRODUCT_ITEM_LINK), 10);
				} catch (Exception e) {
					LOG.info("Try to select submenu again.");
					clickUsingJS(BUY_BUTTON);
					waitForExpectedElement(SHOP_MENU_STICKS_SUB_LINK, 10);
					clickUsingJS(SHOP_MENU_STICKS_SUB_LINK);
					waitForExpectedElement((PRODUCT_ITEM_LINK), 10);
				}
		}
	}

	public void clickOnBuyCategoryLink() {
		WebElement ele = null;
		try {
			switch (UrlBuilder.getLocale()) {
				case "it":
					waitForExpectedElement(headerShopLink, 10);
					ele = getWebDriver().findElement(headerShopLink);
					ele.click();
					break;
				case "glode":
					waitForExpectedElement(headerShopLinkDE, 10);
					ele = getWebDriver().findElement(headerShopLinkDE);
					clickByElementByQueryJSExecutor(headerShopLinkDE);
					break;
			}
		} catch (Exception ex) {
			LOG.info("Failed to click on Shop link from the header menu due to exception: " + ex.getMessage());
		}
	}

	public void selectSubmenuFromHeader(String expectedValue1) {
		List<WebElement> ele = getWebDriver().findElements(By.cssSelector(".header-sub-menu>li>a"));
		for (WebElement ele1 : ele) {
			if (ele1.getText().contains(expectedValue1)) {
				ele1.click();
				break;
			}
		}
	}

	public void enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit() {
		enterDataAndWait(txtFirstName, "Testing");
		enterDataAndWait(txtLastName, "tesingSurname");
		String emailAddress = RandomGenerator.randomEmailAddress(10);
		enterDataAndWait(txtEmailAddress, emailAddress);
		waitForExpectedElement(txtTelephone).sendKeys(random(10, NUMERIC));
		enterDataAndWait(txtTelephone, random(10, NUMERIC));
		WebElement declaration = waitForExpectedElement(chkNewsletterDeclaration);
		declaration.click();
		waitForExpectedElement(btnNewsletterSubscribe).click();
	}

	public void enterNamesAndRandomInvalidEmailToNewsLetterSignUpAndSubmit() throws InterruptedException {
		waitForExpectedElement(By.cssSelector("#firstname")).sendKeys("Testing");
		waitForExpectedElement(By.cssSelector("#lastname")).sendKeys("tesingSurname");
		String emailAddress = RandomGenerator.randomInvalidEmailAddress(10);
		waitForExpectedElement(By.cssSelector("input[id='newsletter'][name='email']")).sendKeys(emailAddress);
		Thread.sleep(1000);
		WebElement declaration = waitForExpectedElement(
				By.cssSelector("#newsletter-validate-detail > div.field.newsletter.newsletter-trms-error > label"));
		declaration.click();
		// click on subscribe button
		waitForExpectedElement(By.cssSelector("div.content form.form.subscribe div.actions:nth-child(5) > button.action.subscribe.primary")).click();
	}

	public void emptyMiniBasket() {
		waitForPage();
		clickGloBasketIcon();
		if (!isBasketEmpty()) {
			LOG.info("Basket isn't empty - emptying now");
			switch (UrlBuilder.getLocale()){
				case "glode":
					clickByElementByQueryJSExecutor(VIEW_BASKET);
					try {
						while (!isBasketEmpty()) {
							List<WebElement> elem = new ArrayList<>();
							waitForExpectedElement(APPLY_COUPAN_BUTTON,20);
							elem = webDriver.findElements(GLO_REMOVE_PRODUCT);
							for (WebElement el : elem) {
								webDriver.findElement(GLO_REMOVE_PRODUCT).click();
								waitForExpectedElement(EMPTY_BASKET_CONFIRM_POPUP, 20);
								if (getWebDriver().findElements(EMPTY_BASKET_CONFIRM_POPUP).size() > 0) {
									clickFirstElementByQueryJSExecutor(EMPTY_BASKET_CONFIRM_POPUP);
								}
							}
						}
					} catch (Exception e) {
						LOG.info("Basket is empty");
					}
					break;
				default:
					try {
						while (!isBasketEmpty()) {
							List<WebElement> elem = new ArrayList<>();
					elem = webDriver.findElements(By.cssSelector(".action.delete"));
					for (WebElement el : elem) {
						webDriver.findElement(By.cssSelector(".action.delete")).click();
								waitForExpectedElement(EMPTY_BASKET_CONFIRM_POPUP, 10);
								if (getWebDriver().findElements(EMPTY_BASKET_CONFIRM_POPUP).size() > 0) {
									clickFirstElementByQueryJSExecutor(EMPTY_BASKET_CONFIRM_POPUP);
								}
							}
						}
					} catch (Exception e) {
						LOG.info("Basket is empty");
					}
			}
		}
	}

	private void clickGloBasketIcon() {
		clickByElementByQueryJSExecutor(BASKET_ICON);
	}

	private Boolean isBasketEmpty() {
		switch (UrlBuilder.getLocale()) {
			case "glode":
				return getWebDriver().findElements(GLO_DE_BAKSET_EMPTY_STATUS).size() > 0;
			default:
				return waitForExpectedElement(BAKSET_EMPTY_STATUS).isDisplayed();
		}
	}

	public void selectProductColorStrengthFromList(By byProductAttribute) {
		// establish which (if any) attribute is displayed and then select and action
		try {
			waitForPage();
			waitForExpectedElement(addToCartButton, 10);
			if (getWebDriver().findElements(byProductAttribute).size() == 0) {
				LOG.info("Product's color or strength not available");
			} else {
				clickUsingJS(webDriver.findElements(byProductAttribute).get(0));
				assertTrue((getWebDriver().findElements(byProductAttribute).get(0).getAttribute("aria-checked").equalsIgnoreCase("true")),
						"Assertion fails and either product strength or colour is not selected");
			}
		} catch (Exception ex) {
			LOG.info("Failed to click select specified Product's attribute from the list due to exception: " + ex.getMessage());
		}
	}

	public void selectFirstResult() {
		clickFirstElementByQueryJSExecutor(PRODUCT_ITEM_LINK);
	}

	public void selectFirstBuyableProduct() {
		//work around as most of product are not available on pdp, so pick up the fourth one.
		List<WebElement> webElementList = visibilityOfAllElementsLocatedBy(firstBuyableProduct);
		clickUsingJS(webElementList.get(webElementList.size()-3));
	}

	public void selectProductStrengthOrColour() {
		selectProductColorStrengthFromList(btnProductStrength);
		selectProductColorStrengthFromList(btnSwatchColor);
	}

	public void clickAddToCartButtonjs() throws Throwable {
		Thread.sleep(4000);
		try {
			clickByElementByQueryJSExecutor(addToCartButton);
		} catch (Exception e) {
			waitForExpectedElement(customizeButton).click();
			clickByElementByQueryJSExecutor(addToCartButton);
		}
	}

	public void clickOnBuyLinkAndAddProductToMiniCart() throws Throwable {
		clickOnBuyButtonAndItsSubMenu();
		waitForAjaxElementNotToBePresent(getWebDriver(),10);
		if (UrlBuilder.getLocale().equals("it")) {
			selectFirstBuyableProduct();
		} else {
			selectFirstResult();
		}
		Thread.sleep(2000);
		if (UrlBuilder.getLocale().equals("it") || UrlBuilder.getLocale().equals("glode") || UrlBuilder.getLocale().equals("pl")) {
			waitForAjaxElementNotToBePresent(getWebDriver(),10);
			selectProductStrengthOrColour();
		}
		clickAddToCartButtonjs();
		clickByElementByQueryJSExecutor(BASKET_ICON);
		switch (UrlBuilder.getLocale()) {
			case "kz":
			case "pl":
			case "glode":
				OnMiniCartClickOnViewBasket();
				break;
		}
		OnMiniCartclickOnProceedToCheckoutButton();
	}

	public void OnMiniCartclickOnProceedToCartPage() {
		OnMiniCartClickOnViewBasket();
		switch (UrlBuilder.getLocale()){
			case "glode": //This step is NA as previous step is performing the required task for Glo de Device trail
				break;
			default:
				try {
					LOG.info("Trying to go to cart page");
					waitForExpectedElement(BASKET_ICON, 10).click();
					waitForExpectedElement(PROCESD_TO_CART_PAGE, 10).click();
				} catch (Exception ex) {
					LOG.info("Failed to click on Proceed to Checkout button from basket window due to exception: " + ex.getMessage());
					waitForExpectedElement(BASKET_ICON, 10).click();
					waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 10).click();
				}
		}
	}

	public void clickOnShopLinkAndClickOnSubCategoryLink(String strLink) {
		if (UrlBuilder.getLocale().equals("kz")) {
			waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strLink)),10);
			clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(strLink)));
		}
		else if (UrlBuilder.getLocale().equals("glode")) {
			clickByElementByQueryJSExecutor(By.linkText(strLink));
		}
		else {
			waitForExpectedElement(BUY_BUTTON, 10).click();
			clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(strLink)));
		}
		waitForExpectedElement(PRODUCT_ITEM_LINK,10);
	}

	public void assertNewsletterSubscriptionAfterSuccessfulOrder() {
		switch (UrlBuilder.getLocale()){
			case "glode":
				clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsletterLink.key")));
				assertTrue(waitForExpectedElement(NEWSLETTER_SUBSCRIPTION_SECTION, 10).getText().contains("NEWSLETTER ABONNEMENT"));
				assertTrue(waitForExpectedElement(NEWSLETTER_SUBSCRIPTION_CHECKBOX_ACCOUNT, 10).isSelected());
				break;
			default:
				assertTrue(waitForExpectedElement(NEWSLETTER_SUBSCRIPTION_SECTION, 10).getText().contains("Du abonnierst unser \"Allgemeines Abonnement\"."));
				clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsletterSubscriptionLink.key")));
				assertTrue(waitForExpectedElement(NEWSLETTER_SUBSCRIPTION_CHECKBOX_ACCOUNT, 10).isSelected());
		}
	}

	public void userClicksNewsletterSubscriptionLinkFromMyAccount() {
		clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsletterSubscriptionLink.key")));
	}

	public void assertUserAutoSubscribedToNewsletterAfterDeviceTrialOrder() {
		assertNewsletterSubscriptionAfterSuccessfulOrder();
	}

	public void userSubscribesToNewsletterFromMyAccountPage() {
		clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("newsletterSubscriptionLink.key")));
		clickByElementByQueryJSExecutor(NEWSLETTER_SUBSCRIPTION_CHECKBOX_ACCOUNT);
		clickByElementByQueryJSExecutor(NEWSLETTER_SUBSCRIPTION_SAVE_BUTTON);
		waitForAjaxElementNotToBePresent(getWebDriver(), 5);
	}

	public void clicksOnForgotPasswordLinkOnPopup() {
		waitForExpectedElement(FORGOT_PASSWORD_GUEST_POPUP, 10).click();
	}

	public void clicksOnCheckoutFromCartPage() {
		try {
			waitForExpectedElement(CHECKOUT_ON_CARTPAGE, 10).click();
		} catch (Exception e) {
			clickFirstElementByQueryJSExecutor(CHECKOUT_ON_CARTPAGE);
		}
	}

	public void CloseGoOnPopup() {
		switch (UrlBuilder.getLocale()) {
			case "pl":
				if (getWebDriver().findElements(GO_ON_POPUP).size() > 0)
					clickByElementByQueryJSExecutor(GO_ON_POPUP);
				break;
			default:
		}
	}

	public void DeleteSavedCard() {
		if (getWebDriver().findElements(CLEAR_BUTTON).size() == 0) {
			LOG.info("No Saved Cards Under My Saved Cards list.");
		} else {
			do {
				waitForExpectedElement(CLEAR_BUTTON, 10);
				clickByElementByQueryJSExecutor(CLEAR_BUTTON);
				waitForExpectedElement(DELETE_YES_BUTTON, 10).click();
			}
			while (getWebDriver().findElements(CLEAR_BUTTON).size() > 0);
		}
	}

	public void waitForMiniBasketToLoadAndProceedToCheckout() {
		waitForElementToDisappear(COUNTER_QTY_LOADER, 10);
		switch (UrlBuilder.getLocale()){
			case"pl":
				if (getWebDriver().getCurrentUrl().contains("pl/pl/checkout/cart/")) {
					if(UrlBuilder.isDesktop()){
						waitForExpectedElement(BASKET_ICON, 10).click();
						waitForExpectedElement(PROCEED_TO_BASKET_PAGE, 10).click();
						waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 10).click();
					}else
						waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON_PL,10);
						clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON_PL);
				}
			break;
			default:
				waitForExpectedElement(BASKET_ICON, 10).click();
				waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 10).click();
		}
	}

	public void addFreeGiftPacksToMiniBasket(int numOfPacksRequired) {
		int attempts=0;
		while (attempts < numOfPacksRequired) {
			retryingFindClick(BUTTON_GIFT_ITEMS_ADDTOCART_FIRST);
			attempts++;
		}
	}

	public void addFreePacksToMiniBasketAndProceedToCheckout() throws InterruptedException {
		waitForAjaxElementNotToBePresent(getWebDriver(),15);
		if (doesURLContain("checkout/cart/") && waitForExpectedElement(CART_ERROR_MESSAGE).isDisplayed()) {
			String strCartErrMessage = waitForExpectedElement(CART_ERROR_MESSAGE).getText();
			int numOfPacksRequired = Integer.parseInt(strCartErrMessage.replaceAll("[^0-9]", ""));
			addFreeGiftPacksToMiniBasket(numOfPacksRequired);
			waitForMiniBasketToLoadAndProceedToCheckout();
		}
		else{
			waitForMiniBasketToLoadAndProceedToCheckout();
		}
	}

	public void userClosesRDBPopUpBannerIfPresentForKZ(){
		try{
			if (isElementDisplayedBySeconds(RDB_POPUP_BANNER, 5)) {
				waitForExpectedElement(RDB_POPUP_BANNER).click();
			}
		}catch(TimeoutException e ){
			e.printStackTrace();
		}

	}

	public void clicksOnChangePassword(){
		switch (UrlBuilder.getLocale()) {
			case "it":
				waitAndClickByElementByJSExecutor(EDIT_PASSWORD_GLOIT, 5);
				break;
			default:
				waitAndClickByElementByJSExecutor(EDIT_PASSWORD,5);
		}


	}

	public void enterNamesAndRandomEmailToNewsLetterPopupOnRegistrationPage() {
		enterDataAndWait(FIRST_NAME_TEXTBOX, "Testing");
		enterDataAndWait(LAST_NAME_TEXTBOX, "tesingSurname");
		String emailAddress = RandomGenerator.randomEmailAddress(10);
		enterDataAndWait(txtEmailAddress, emailAddress);
		waitForExpectedElement(TELEPHONE_TEXTBOX).sendKeys(random(10, NUMERIC));
		enterDataAndWait(TELEPHONE_TEXTBOX, random(10, NUMERIC));
		WebElement declaration = waitForExpectedElement(chkNewsletterDeclaration);
		declaration.click();
		waitForExpectedElement(btnNewsletterSubscribe).click();
	}

	public boolean assertFindOutHowToCollectInsidersCoinCTAIsClickable(){
		waitForExpectedElement(INSIDERS_CLUB_GLO_HEADER).click();
		waitForAjaxElementNotToBePresent(getWebDriver(),20);
		return(isElementClickable(FIND_OUT_HOW_TO_COLLECT_INSIDERS_COIN_CTA));
	}

	public void clickInsidersClub() {
		jsScrollElementInCenter(waitForExpectedElement(INSIDERS_CLUB_GLO_HEADER));
		clickUsingJS(INSIDERS_CLUB_GLO_HEADER);
	}

	public void clickCTASubscribe() {
		waitForAjaxElementNotToBePresent(getWebDriver(),20);
		if (!UrlBuilder.getLocale().equals("it")) {
			waitForExpectedElement(FIND_OUT_HOW_TO_COLLECT_INSIDERS_COIN_CTA).click();
		}
		getWebDriver().findElements(SUBSCRIBE_GLOIT_CTA).get(0).click();
	}

	public void clickOnStraigntToGLOButton(){
		waitAndClickByElementByJSExecutor(STRAIGHT_TO_GLO_BUTTON,10);
	}

	public void selectsCountry(){
		By COUNTRY_OPTION=By.cssSelector("div.pagebuilder-column.mobile-divider a[href*='"+UrlBuilder.getEndPoints(LOCALE)+"']");
		waitAndClickByElementByJSExecutor(COUNTRY_OPTION,10);
	}
}
