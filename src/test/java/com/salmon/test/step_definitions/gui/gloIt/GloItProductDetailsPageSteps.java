package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.gloIt.GloItProductDetailsPage;
import com.salmon.test.page_objects.gui.gloIt.HyperPlusPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

public class GloItProductDetailsPageSteps {

	private GloItProductDetailsPage gloItProductDetailsPage;
	private HyperPlusPage hyperPlusPage;

	public GloItProductDetailsPageSteps(GloItProductDetailsPage gloItProductDetailsPage,HyperPlusPage hyperPlusPage) {
		this.gloItProductDetailsPage =gloItProductDetailsPage;
		this.hyperPlusPage=hyperPlusPage;
	}

	@And("^user navigates to PDPPage and add product$")
    public void user_navigates_to_PDPPage() throws MalformedURLException {
		gloItProductDetailsPage.userNavigatesToPdpPage();
	}
	
	@And("^user navigates to checkoutPage$")
    public void user_navigates_to_checkoutPage() throws MalformedURLException {
		gloItProductDetailsPage.userNavigatesToCheckoutPage();
	}


	  @Then("^assert PDP product title for Glo product$")
	  public void getPDPTitle() {
	 gloItProductDetailsPage.getPDPTitle();
	  }

    @And("^the PDP contains delivery details$")
    public void thePDPContainsDeliveryDetails() {
		assertThat(gloItProductDetailsPage.getHyperPlusDeliveryDetails().length()>0)
				.as("ERROR: no delivery details on Hyper+ PDP").isTrue();
    }

	@And("^the PDP contains shipping details$")
	public void thePDPContainsShippingDetails() {
		assertThat(gloItProductDetailsPage.getHyperPlusShippingDetails().length()>0)
				.as("ERROR: no shipping details on Hyper+ PDP").isTrue();
	}

	@And("^the PLP contains specification details$")
	public void thePLPContainsSpecificationDetails() {
		assertThat(gloItProductDetailsPage.getHyperPlusSpecsDetails().length()>0)
				.as("ERROR: no specification details on Hyper+ PDP").isTrue();
	}

	@And("^the PDP displays whats in the box$")
	public void thePDPDisplaysWhatsInTheBox() {
		assertThat(gloItProductDetailsPage.validWhatsInTheBox()).isTrue()
				.as("ERROR: not all item descriptions are present").isTrue();
	}

	@And("^the PDP displays you might also like$")
	public void thePDPDisplaysYouMightAlsoLike() {
		assertThat(gloItProductDetailsPage.validYouMightAlsoLike())
				.as("ERROR: You Might Also Like not displaying correctly").isTrue();
	}

	@And("^the main image loads$")
	public void theMainImageLoads() {
		assertThat(gloItProductDetailsPage.waitForMainImageToLoad()).as("ERROR: The main image didn't load").isTrue();
	}

	@And("^the product displays a price$")
	public void theProductDisplaysAPrice() {
		assertThat(gloItProductDetailsPage.priceIsDisplayed()).as("ERROR: no price is diplayed").isTrue();
	}

	@And("^the main body bubble is displayed and enabled$")
	public void theMainBodyBubbleIsDisplayed() {
		assertThat(gloItProductDetailsPage.mainBubbleIsDisplayed()).as("ERROR: main bubble is not displayed").isTrue();
	}

	@When("^the main button is clicked$")
	public void theMainButtonIsClicked() {
		gloItProductDetailsPage.clickOnMainButton();
	}

	@Then("^the main body colour swatches are displayed$")
	public void theMainBodyColourSwatchesAreDisplayed() {
		assertThat(gloItProductDetailsPage.validMainBodySwatches()).as("ERROR: main body swatches not displayed correctly").isTrue();
	}

	@And("^the side panel bubble is displayed and enabled$")
	public void theSidePanelBubbleIsDisplayedAndEnabled() {
		assertThat(gloItProductDetailsPage.sideBubbleIsDisplayed()).as("ERROR: side bubble is not displayed").isTrue();
	}

	@When("^the side button is clicked$")
	public void theSideButtonIsClicked() {
		gloItProductDetailsPage.clickOnSideButton();
	}

	@Then("^the side panel swatches are displayed$")
	public void theSidePanelSwatchesAreDisplayed() {
		gloItProductDetailsPage.clickOnSideButton();
		assertThat(gloItProductDetailsPage.validSideSwatches()).as("ERROR: side panel swatches not displayed correctly").isTrue();
	}

	@And("^the get inspired swatches are displayed$")
	public void theGetInspiredSwatchesAreDisplayed() {
		assertThat(gloItProductDetailsPage.validGetInspiredSwatches()).as("ERROR: get inspired swatches not displayed correctly").isTrue();
	}

	@When("^the get inspired swatch heading is clicked$")
	public void theGetInspiredSwatchHeadingIsClicked() {
		gloItProductDetailsPage.clickOnGetInspired();
	}

	@And("^assert text of quantity is displayed on PDP page$")
	public void assertTextOfQuantityIsDisplayedOnPDPPage() {
		String actualText =gloItProductDetailsPage.getQuantityTextOnPDPPage();
		String expectedText = UrlBuilder.getMessage("quantityText.key");
		assertThat(actualText.equals(expectedText))
				.as("ERROR validate TextOfQuantity: expected message was "+expectedText+" but I got "+actualText).isTrue();
	}
}
	
