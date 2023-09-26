package com.salmon.test.page_objects.gui.epok;


import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

public class EpokBasketPage extends PageObject {


	public By emptyBasketIcon = By.cssSelector("a.action.action-delete");
	public By cartButton = By.cssSelector("div.cart-icon");
	private By epokLogo = By.cssSelector("a.logo");



	public void EpokemptyBasket() throws InterruptedException {
		Thread.sleep(7000);
		LOG.info("\n **** Starting empty basket routine");
		waitForExpectedElement(cartButton).click();
		try {
			while (waitForExpectedElement(emptyBasketIcon, 10).isDisplayed()) {

				LOG.info("Basket has content and can be emptied");
				LOG.info("In Basket cleaning while loop");
				waitForExpectedElement(emptyBasketIcon, 10).click();
				LOG.info("Closing basket menu screen");
				Thread.sleep(3000);

			}
			clickOnEpokLogoIcon();

		} catch (Exception e) {
			LOG.info("Basket is empty");
			clickOnEpokLogoIcon();
		}
	}

	public void clickOnEpokLogoIcon() {
		waitForExpectedElement(epokLogo, 10);
		clickByElement(epokLogo);
	}

}
