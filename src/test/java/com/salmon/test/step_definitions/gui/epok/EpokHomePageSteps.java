package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.epok.EpokHomePage;
import com.salmon.test.page_objects.gui.epok.EpokLoginPage;
import com.salmon.test.services.posts.CommentsApi;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EpokHomePageSteps {

	EpokHomePage epokHomePage;
	EpokLoginPage epokLoginPage;
	private static final Logger LOG = LoggerFactory.getLogger(EpokHomePageSteps.class);

	public EpokHomePageSteps(EpokHomePage epokHomePage, EpokLoginPage epokLoginPage) {
		this.epokHomePage = epokHomePage;
		this.epokLoginPage = epokLoginPage;
	}

	@Given("^user navigates EPOK Homepage$")
	public void userNavigatesEPOKHomepage() {
	    UrlBuilder.startBATHomePage();
	}

	@And("^Epok tearDownBAT$")
	public void teardownBAT() {
		epokHomePage.getWebDriver().manage().deleteAllCookies();
		epokHomePage.getWebDriver().navigate().refresh();
	}

	@And("^Epok resize browser screen - full screen$")
	public void resizeBrowserToFullScreen() {
		epokHomePage.getWebDriver().manage().window().maximize();
	}

	@And("^Epok select allow all from cookie popup$")
	public void selectAllowAllFromCookiePopup() {
		epokHomePage.cookieClickAllowAll();

	}

	@And("^EPOK select over 18$")
	public void epokSelectOver() {
		epokHomePage.pressConfirmOver18Button();
	}

	@And("^Epok Icon is displayed$")
	public void epok_Icon_is_displayed() {
		epokHomePage.epokIconLogo();
	}

	@And("^Person Icon is displayed$")
	public void person_Icon_is_displayed() {
		epokHomePage.accountIconIsDisplayed();
	}

	@And("^Cart Icon is Displayed$")
	public void cart_Icon_is_Displayed() {
		epokHomePage.miniCartIconIsDisplayed();
	}

	@And("^Hamburger Menu is not displayed$")
	public void hamburger_Menu_is_Not_displayed() {
		epokHomePage.hamburgerMenuIsNotDisplayed();
	}

	@And("^accept Cookies Button is displayed and enabled$")
	public void accept_Cookies_Button_is_displayed_and_enabled() {
		epokHomePage.acceptCookiesButton();
	}

	@And("^assert pageTitle is '(.*)'$")
	public void assertPageTitleIsEPOKHomePage(String expectedTitle) throws Throwable {
		Thread.sleep(5000);
		LOG.info("Testing breakpoint");
		LOG.info("Page title : " + epokHomePage.getWebDriver().getTitle());
		epokHomePage.assertTrueWithCustomError(expectedTitle, epokHomePage.getCurrentPageTitle());
	}

	@When("^user click on PersonIcon and Navigate to the Login Page$")
	public void user_click_on_PersonIcon() {
		epokHomePage.clickOnPersonIcon();
		epokHomePage.eyesCheckLoginPage();
	}

	@And("^click on Epok ICon and Navigate to Home Page$")
	public void click_On_Epok_ICon_navigate_to_Home_Page() {
		epokHomePage.clickOnEpokLogoIcon();
	}


	@When("^user click on humberger menu$")
	public void user_click_on_humberger_menu() throws Throwable {
		epokHomePage.clickHamburgerMenu();
	}

	@And("^users Clicks on Text Links '(.*)'$")
	public void usersClicksOnTextLinks(String linkText) throws Throwable {
		try {

			epokHomePage.getWebDriver().findElement(By.linkText(Props.getProp(linkText))).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			LOG.info("Couldn't click due to : " + e.getMessage());
			epokHomePage.clickByElementByQueryJSExecutor(By.linkText(Props.getProp(linkText)));
			Thread.sleep(3000);
		}
	}

	@When("^users click on Facebook link$")
	public void users_click_on_Facebook_link() {
		epokHomePage.clickOnFacebookIcon();
		epokHomePage.switchBetweenWindowTabs(1);

	}

	@When("^users click on Instagram link$")
	public void users_click_on_Instagram_link() {
		epokHomePage.clickOnInstagramIcon();
		epokHomePage.switchBetweenWindowTabs(1);
	}

	@And("^Close the child window$")
	public void close_the_child_window() {
		epokHomePage.closeWindow();
	}

	@When("^users Navigate to parent window$")
	public void users_navigate_to_parent_window() {
		epokHomePage.switchBetweenWindowTabs(0);
	}

	@When("^user click on SignIn button$")
	public void user_click_on_SignIn_button() {
		epokHomePage.clickOnSignInButton();
	}

	@And("^Epok users clicks on the '(.*)' text link$")
	  public void usersClicksOnTheLogoutTextLink(String linkText) {
		  try 	    {
		    	Thread.sleep(3000);
		    	epokHomePage.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
		    	Thread.sleep(3000);
		    } catch (Exception e)   {
			  LOG.info("Couldn't click due to : " + e.getMessage());
		    } 
}
	@And("^Epok url contains '(.*)'$")
	  public void urlContainsSubscriptions(String urlContains) {
	    assertTrue("**** ERROR - following was expected in URL : " + urlContains + " but full URL was : \n" + epokHomePage.getWebDriver().getCurrentUrl(), epokHomePage.getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(urlContains)));
	}

	@And("^assert navigation menu with '(.*)' links on the home page$")
	public void assertNavigationMenuWithLinksOnHomePage(String strHeaderLinks) {
		String[] lstLinks = UrlBuilder.getMessage(strHeaderLinks).split(",");
		List<WebElement> options = epokHomePage.getWebDriver().findElements(epokHomePage.HEADER_MENU_LINKS);
		for(int i=0;i<options.size();i++){
			try {
				assertTrue(options.get(i).getText().equals(lstLinks[i]));
			} catch (Exception ex) {
				LOG.info("Failed to verify header link text.");
			}
		}
	}
}
