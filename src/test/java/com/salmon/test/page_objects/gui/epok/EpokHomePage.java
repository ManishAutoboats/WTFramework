package com.salmon.test.page_objects.gui.epok;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class EpokHomePage extends PageObject {

	// Age Verification
	private By confirmOver18 = By.cssSelector("button#btn-entry-age-allow");

	// Header links
	private By epokLogo = By.cssSelector("a.logo");
	public By personIcon = By.cssSelector("div.account-icon");
	private static final By PERSON_ICON_VELO_PL = By.cssSelector("[href*='/account/login']");
	public By cartButton = By.cssSelector("div.cart-icon");
	private By hamBurgerMenu = By.cssSelector("div.menu-icon");
	private By acceptCookiesButton = By.cssSelector("btn-cookie-allow");
	private By privacyPolicyLink = By.linkText("Erfahren Sie mehr");
	public By productSubmenu = By.xpath("//div[contains(@class,'top-menu-desktop')]//a[text()='Produkte']");
	private static final By M_PRODUCT_LINK_SUB_MENU= By.cssSelector("div.top-menu.sticky:nth-child(2) ul:nth-child(1) li:nth-child(1) > a:nth-child(1)");
	public By productSubmenuIpad = By.cssSelector("div.top-menu.sticky li a");
	public By productSubmenuIphone = By.cssSelector("div.top-menu.sticky:nth-child(2) ul:nth-child(1) li:nth-child(1) > a:nth-child(1)");
	public static final By M_PRODUCT_SUB_MENU=By.cssSelector("div.top-menu.sticky:nth-child(2) ul:nth-child(1) li:nth-child(1) > a:nth-child(1)");

	// social media links
	private By facebooklink = By.cssSelector("div.footer-social div.facebook-icon");
	private By instagramlink = By.cssSelector("div.footer-social div.instagram-icon");
	private By SignInLink = By.linkText("ANMELDEN");
	private By allowCookiesButton = By.cssSelector("div#onetrust-button-group>button#onetrust-accept-btn-handler");
	public static final By HEADER_MENU_LINKS = By.xpath("//div[contains(@class,'top-menu-desktop')]//li//a");

	public void pressConfirmOver18Button() {
	try {	waitForExpectedElement(confirmOver18, 30).click();
		
	}catch(Exception e) {
		clickByElementByQueryJSExecutor(confirmOver18);	
	}
	}

	public void epokIconLogo() {
		assertTrue(waitForExpectedElement(epokLogo).isDisplayed());
	}

	public void miniCartIconIsDisplayed() {
		assertTrue(waitForExpectedElement(cartButton).isDisplayed());
	}

	public void hamburgerMenuIsNotDisplayed() {
		if(UrlBuilder.isDesktop()){
			try{
				assertTrue(waitForExpectedElement(hamBurgerMenu, 10).isDisplayed()); }
			catch(Exception ex){
				LOG.info("As expected, Hamburger Menu not displayed for Desktop site.");}
		}
		else
			assertTrue(waitForExpectedElement(hamBurgerMenu, 10).isDisplayed());
	}

	public boolean accountIconIsDisplayed() {
		return waitForExpectedElement(personIcon).isDisplayed();
	}

	public void acceptCookiesButton() {
		assertTrue(waitForExpectedElement(acceptCookiesButton).isDisplayed());
		assertTrue(waitForExpectedElement(acceptCookiesButton).isEnabled());
	}

	public void clickOnEpokLogoIcon() {
		waitForExpectedElement(epokLogo, 10);
		clickByElement(epokLogo);
	}

	public void clickOnPersonIcon() {
		switch (UrlBuilder.getLocale().toLowerCase()) {
			case "velopl":
				waitForExpectedElement(PERSON_ICON_VELO_PL, 3).click();
				break;
			default:
				waitForExpectedElement(personIcon, 20);
				clickByElement(personIcon);
				break;
		}
	}

	public void clickHamburgerMenu() throws Throwable {
		if(!UrlBuilder.isDesktop()){
			Thread.sleep(7000);
			waitForExpectedElement(hamBurgerMenu).click();}
	}

	public void clickOnFacebookIcon() {
		waitForExpectedElement(facebooklink, 10).click();
	}

	public void clickOnInstagramIcon() {
		waitForExpectedElement(facebooklink, 10);
		clickByElementByQueryJSExecutor(instagramlink);
	}
	public void closeWindow() {
		getWebDriver().close();
	}

	public void clickOnSignInButton() {
		waitForExpectedElement(SignInLink, 10);
		clickByElement(SignInLink);
	}

	public void cookieClickAllowAll() {
		waitForExpectedElement(allowCookiesButton, 15).click();
	}

	public void eyesCheckLoginPage() {
		if (Props.EYES_ON && EyesCheckpoints.LOGIN_PAGE.isSwitchOn()) {
			eyesCheckFullyPage(EyesCheckpoints.LOGIN_PAGE.getName());
		}
	}
}
