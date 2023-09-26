package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.gloIt.GloItProductDetailsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;

public class GloPDPPageSteps {

	GloItProductDetailsPage gloProductDetailsPage;

	public GloPDPPageSteps(GloItProductDetailsPage gloProductDetailsPage) {
		this.gloProductDetailsPage = gloProductDetailsPage;
	}

	@Given("^users navigates to GLO Accessories PLP '(.*)'$")
	public void userNavigatesToGLOAccessoriesPage(String strURL) {
		gloProductDetailsPage.userNavigatesToGLOAccessoriesPage(strURL);
	}

	@Given("^assert Weitere Informationen block is not displayed$")
	public void assertWeitereInformationBlockNotDisplayed() {
		gloProductDetailsPage.assertWeitereInformationBlockNotDisplayed();
	}

	@And("^Glo user clicks on any '(.*)' link by text$")
	public void GloUserClicksOnAnyLinkByText(String linkText) {
		gloProductDetailsPage.GloUserClicksOnAnyLinkByText(linkText);
	}

	@And("^assert quantity exceeded error message '(.*)' is displayed$")
	public void assertQuantityExceededErrorMessageIsDisplayed(String linkText) {
		assertTrue(gloProductDetailsPage.waitForExpectedElement(gloProductDetailsPage.QTY_EXCEEDED_ERROR_MESSAGE,10).getText().contains(UrlBuilder.getMessage(linkText)));
	}
}