package com.salmon.test.page_objects.gui.epok;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

public class EpokProductListPage extends PageObject {

    public By addToCartSuccessMsg=By.cssSelector("div.message-success.success.message>div");


	public void productAddToCartSuccessMsg(){
		if(!UrlBuilder.isFirefox()) //TODO:FF doesn't have such msg,need confirm
	 waitForExpectedElement(addToCartSuccessMsg, 20).isDisplayed();
	}

}
