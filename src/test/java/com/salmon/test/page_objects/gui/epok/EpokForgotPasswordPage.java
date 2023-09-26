package com.salmon.test.page_objects.gui.epok;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class EpokForgotPasswordPage extends PageObject {
	
	public By pageHeading = By.cssSelector("div.field.note");
	
	 public String getPageHeading(){
	      return waitForExpectedElement(pageHeading).getText();
	    }

}
