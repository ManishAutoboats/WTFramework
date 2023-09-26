package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.epok.EpokOrderSuccessPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class EpokOrderSuccessPageSteps {
	public String orderNumber;

	EpokOrderSuccessPage epokOrderSuccessPage;

	public EpokOrderSuccessPageSteps(EpokOrderSuccessPage epokOrderSuccessPage) {
		this.epokOrderSuccessPage = epokOrderSuccessPage;
	}

	@And("^Epok grab and output Order number$")
	public void grabAndOutputOrderNumber() {

		orderNumber = epokOrderSuccessPage.getGeneratedOrderNumber();
	}

	@And("^Epok assert on Order Confirmation page '(.*)' is displayed$")
	public void epok_assert_ThankYou_Is_Displayed(String expectedMessage) {
		epokOrderSuccessPage.returnPageHeading();
		assertEquals(epokOrderSuccessPage.returnPageHeading(), UrlBuilder.getMessage(expectedMessage));

	}
	@And("^Epok assert order number is displayed Previous orders on page$")
	  public void assertOrderNumberIsDisplayedPreviousOrdersOnPage() {
	    assertTrue("Expected order number of : " +orderNumber+ " cannot be found",epokOrderSuccessPage.getWebDriver().getPageSource().contains(orderNumber));
	  }
	
	@And("^Epok grab contents of matched row and assert order status$")
	  public void grabContentsOfMatchedRowAndAssertOrderStatus() {
		    String orderStatus = "";
		    for(WebElement rowData : epokOrderSuccessPage.getTableRows(epokOrderSuccessPage.orderTable)){
		      if (rowData.getText().contains(orderNumber)){
		        orderStatus = rowData.findElement(epokOrderSuccessPage.orderStatusCol).getText();
		      }
		    }
		    assertTrue("*** ERROR - orderStatus NOT as expected - should be 'Awaiting Shipment, but is : " + orderStatus,orderStatus.equals("Verarbeitung"));
		  }
	
	

}
