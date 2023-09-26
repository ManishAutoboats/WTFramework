package com.salmon.test.page_objects.gui.epok;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class EpokOrderSuccessPage extends PageObject {
	
	  public By pageHeading = By.cssSelector(".checkout-success>h2");
	  public By generatedOrderNumber = By.cssSelector("a.order-number>strong");
	  public By orderTable=By.cssSelector("#my-orders-table");
	  public By orderStatusCol=By.cssSelector("td.col.status");

	  public String returnPageHeading() {
	    return waitForExpectedElement(pageHeading).getText();
	  }

	  public String getGeneratedOrderNumber() {
	    return waitForExpectedElement(generatedOrderNumber).getText();
	  }

	}


