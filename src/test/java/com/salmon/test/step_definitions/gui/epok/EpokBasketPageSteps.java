package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.BasketPage;
import com.salmon.test.page_objects.gui.epok.EpokBasketPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class EpokBasketPageSteps {

	BasketPage basketPage;
	EpokBasketPage epokBasketPage;

	public EpokBasketPageSteps(BasketPage basketPage, EpokBasketPage epokBasketPage) {

		this.basketPage = basketPage;
		this.epokBasketPage=epokBasketPage;
	}

	@Then("^assert cart pageTitle is '(.*)'$")
	public void assert_login_pageTitle(String expectedBasketPageTitle) {
		basketPage.getCurrentPageTitle().toUpperCase().contains(UrlBuilder.getMessage(expectedBasketPageTitle).toUpperCase());

	}

	@And("^Epok assert text of '(.*)' is displayed on basket page$")
	public void assertTextOfCustomerLoginIsDisplayedOnMyRegistrationPage(String expectedText) {

		basketPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedText));
	}

	@And("^Epok empty basket$")
	public void Epok_empty_basket() {
		try {
			epokBasketPage.EpokemptyBasket();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@And("^Go to checkout page$")
	public void go_To_checkout_Page() throws InterruptedException {
		Thread.sleep(3000);
		basketPage.clickOnCheckoutButton();
	}

}