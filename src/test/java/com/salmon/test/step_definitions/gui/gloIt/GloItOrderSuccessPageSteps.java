package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertTrue;

public class GloItOrderSuccessPageSteps extends PageObject {
	public String orderNumber;
	private static final Logger LOG = LoggerFactory.getLogger(GloItOrderSuccessPageSteps.class);

	com.salmon.test.page_objects.gui.gloIt.GloItOrderSuccessPage GloItOrderSuccessPage;

	public GloItOrderSuccessPageSteps(com.salmon.test.page_objects.gui.gloIt.GloItOrderSuccessPage GloItOrderSuccessPage) {
		this.GloItOrderSuccessPage = GloItOrderSuccessPage;
	}

	@And("^GloIt grab and output Order number$")
	public void grabAndOutputOrderNumber() {
		orderNumber = GloItOrderSuccessPage.getGeneratedOrderNumber();
	}

	@And("^Glo assert on Order Confirmation page '(.*)' is displayed$")
	public void Glo_assert_ThankYou_Is_Displayed(String expectedMessage) {
		assertTrue(GloItOrderSuccessPage.returnPageHeading().contains(UrlBuilder.getMessage(expectedMessage)));

	}
	@And("^GloIt assert order number is displayed Previous orders on page$")
	  public void assertOrderNumberIsDisplayedPreviousOrdersOnPage() {
	    assertTrue("Expected order number of : " +orderNumber+ " cannot be found",GloItOrderSuccessPage.getWebDriver().getPageSource().contains(orderNumber));
	  }
	
	@And("^GloIt grab contents of matched row and assert order status$")
	  public void grabContentsOfMatchedRowAndAssertOrderStatus() {
		    String orderStatus = "";
		    for(WebElement rowData : GloItOrderSuccessPage.getTableRows(GloItOrderSuccessPage.orderTable)){
		      if (rowData.getText().contains(orderNumber)){
		        LOG.info("\n Match FOUND for order number : " + orderNumber);
		        LOG.info(rowData.getText());
		        orderStatus = rowData.findElement(GloItOrderSuccessPage.orderStatusCol).getText();
		      }
		    }
		    assertTrue("*** ERROR - orderStatus NOT as expected - should be 'Awaiting Shipment, but is : " + orderStatus,orderStatus.equals("Verarbeitung"));
		  }

	@And("^verify Order number is displayed$")
	public void verifyOrderNumberIsDisplayed() {
		Assert.assertTrue(GloItOrderSuccessPage.orderNumberIsDisplayed());
	}

	@And("^verify Cancellation note is displayed$")
	public void verifyCancellationNoteIsDisplayed() {
		Assert.assertTrue(GloItOrderSuccessPage.cancellationNoteIsDisplayed());
	}

	@And("^verify Manage Orders button is displayed$")
	public void verifyManageOrdersButtonIsDisplayed() {
		Assert.assertTrue(GloItOrderSuccessPage.manageOrdersButtonIsDisplayed());
	}

	@And("^verify link to Homepage is displayed$")
	public void verifyLinkToHomepageIsDisplayed() {
		Assert.assertTrue(GloItOrderSuccessPage.homepageLinkIsDisplayed());
	}

	@And("^verify thank you note is displayed$")
	public void verifyThankYouNoteIsDisplayed() {
		Assert.assertTrue(GloItOrderSuccessPage.thankYouNoteIsDisplayed());
	}

	@And("^assert Order Number link redirects to order details$")
	public void assertOrderNumberLinkRedirectsToOrderDetails() {
		GloItOrderSuccessPage.assertOrderNumberRedirectsToCorrectURL();
	}

	@And("^assert manage orders link redirects to order history$")
	public void assertManageOrdersLinkRedirectsToOrderHistory() {
		GloItOrderSuccessPage.assertManageOrdersLinkRedirectsToCorrectURL();
	}

	@And("^assert Homepage link redirects to homepage$")
	public void assertHomepageLinkRedirectsToHomepage() {
		GloItOrderSuccessPage.assertHomepageLinkRedirectsToCorrectURL();
	}


}
