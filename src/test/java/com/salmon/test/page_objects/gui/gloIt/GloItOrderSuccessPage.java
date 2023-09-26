package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;

public class GloItOrderSuccessPage extends PageObject {
	
	  public By successPageHeading = By.cssSelector(".page-title-wrapper");
	  public By generatedOrderNumber = By.cssSelector("div.order-number-table:nth-child(3) div.content div:nth-child(1) > strong:nth-child(1)");
	  public By orderTable=By.cssSelector("#my-orders-table");
	  public By orderStatusCol=By.cssSelector("td.col.status");

	private static final By ORDER_NUMBER_TEXT = By.cssSelector("div.checkout-success>p>a.order-number");
	private static final By CANCEL_NOTE = By.cssSelector("p.checkout-message");
	public static final By MANAGE_ORDERS_LINK = By.cssSelector("a.action.primary.account");
	private static final By HOMEPAGE_LINK = By.cssSelector("a.action.secondary.continue");
	private static final By THANKYOU_TEXT = By.cssSelector("div.checkout-success>h2");

	public String returnPageHeading() {
	    return waitForExpectedElement(successPageHeading).getText();
	  }

	  public String getGeneratedOrderNumber() {
	    return waitForExpectedElement(generatedOrderNumber).getText();
	  }

	public boolean orderNumberIsDisplayed() {
		return waitForExpectedElement(ORDER_NUMBER_TEXT).isDisplayed();
	}

	public boolean cancellationNoteIsDisplayed() {
		return waitForExpectedElement(CANCEL_NOTE).isDisplayed();
	}

	public boolean manageOrdersButtonIsDisplayed() {
		return waitForExpectedElement(MANAGE_ORDERS_LINK).isDisplayed();
	}

	public boolean homepageLinkIsDisplayed() {
		return waitForExpectedElement(HOMEPAGE_LINK).isDisplayed();
	}

	public boolean thankYouNoteIsDisplayed() {
		return waitForExpectedElement(THANKYOU_TEXT).isDisplayed();
	}

	public void assertManageOrdersLinkRedirectsToCorrectURL() {
		String strURL = waitForExpectedElement(MANAGE_ORDERS_LINK).getAttribute("href");
		assertTrue(strURL.contains(UrlBuilder.getMessage("orderHistoryUrl.key")));
	}

	public void assertHomepageLinkRedirectsToCorrectURL() {
		String strURL = waitForExpectedElement(HOMEPAGE_LINK).getAttribute("href");
		assertTrue(strURL.contains(UrlBuilder.getMessage("homePageUrl.key")));
	}

	public void assertOrderNumberRedirectsToCorrectURL() {
		String strURL = waitForExpectedElement(ORDER_NUMBER_TEXT).getAttribute("href");
		assertTrue(strURL.contains(UrlBuilder.getMessage("orderDetailUrl.key")));
	}

}


