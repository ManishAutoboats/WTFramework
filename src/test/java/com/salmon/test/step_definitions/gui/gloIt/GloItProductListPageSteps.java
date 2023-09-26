package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

public class GloItProductListPageSteps {

	private HomePage homePage;
	private com.salmon.test.page_objects.gui.gloIt.GloItProductListPage GloItProductListPage;

	public GloItProductListPageSteps(com.salmon.test.page_objects.gui.gloIt.GloItProductListPage GloItProductListPage, HomePage homePage) {
		this.GloItProductListPage =GloItProductListPage;
		this.homePage = homePage;
	}

	@And("^user clicks on add to cart button from PLP page$")
    public void GloIt_user_Clicks_on_add_to_cart_button() {
		GloItProductListPage.ClickonAddToCartButton();
		
	}

	@And("^click on first result for Loyalty$")
	public void clickFirstResultAsLoyalty() {
		GloItProductListPage.selectFirstBuyableProductForLoyalty();
	}

	  @And("^click on first result$")
	  public void clickFirstResult() {
		switch (UrlBuilder.getLocale()) {
			case "pl":
			case "it":
				GloItProductListPage.selectFirstBuyableProduct();
			break;
			default:
				GloItProductListPage.selectFirstResult();
			}
	  }

	@And("^click on the updated product$")
	public void clickUpdatedProduct() {
			GloItProductListPage.selectProductByName();
	}

	@Then("^there is a \"([^\"]*)\" Hyper Plus product displayed$")
	public void thereIsAHyperPlusProductDisplayed(String productType) {
		assertThat(GloItProductListPage.hyperPlusProductIsDisplayed(productType))
				.as("ERROR: couldn't find a "+productType+ " hyper+ product").isTrue();
	}

	@And("^all Hyper Plus products have a configurable icon$")
	public void allHyperPlusProductsHaveAConfigurableIcon() {
		assertThat(GloItProductListPage.hyperPlusProductsHaveIcon())
				.as("ERROR: not all hyper plus products have a configurable icon").isTrue();
	}

	@And("^clicks on a \"([^\"]*)\" product$")
	public void clicksOnAProduct(String productType) {
		GloItProductListPage.clickOnHyperPlusPlpProductComponent(productType, "productImage");
	}

	@And("^select colour for configurable product$")
	public void selectLoyaltyOnlyItemsColor() {
		GloItProductListPage.selectColorInLoyaltyOnlyConfigProduct();
	}

	@And("^user see loyalty only configurable product$")
	public void verifyLoyaltyOnlyItem() {
		assertThat(GloItProductListPage.assertLoyaltyOnlyConfigProduct()).as("ERROR: Cannot find configurable loyalty only products").isTrue();
	}

	@Then("^the configuration page is displayed$")
	public void theConfigurationPageIsDisplayed() {
		GloItProductListPage.isConfiguratorPageDisplayed();
	}

	@And("^clicks on a \"([^\"]*)\" product item link$")
	public void clicksOnAProductItemLink(String productType) {
		GloItProductListPage.clickOnHyperPlusPlpProductComponent(productType, "productItemLink");
	}

	@And("^clicks on a \"([^\"]*)\" product button$")
	public void clicksOnAProductButton(String productType) {
		GloItProductListPage.clickOnHyperPlusPlpProductComponent(productType, "productButton");
	}

	@And("^click on first simple product result$")
	public void clickOnFirstNonHyperPlusResult() {
		GloItProductListPage.selectFirstNonHyperPlusResult();
	}

	@Then("^the simple product is added to cart$")
	public void theSimpleProductIsAddedToCart() throws Throwable {
		homePage.confirmMiniBasketDisplayedAmountOf("1");
	}

	@And("^user click on first engraving product$")
	public void clickFirstEngravingProduct() {
		GloItProductListPage.clickFirstElementByQueryJSExecutor(GloItProductListPage.firstBuyableProduct);
	}

	@And("^clicks and add hyperplus product to cart$")
	public void clicksAndAddHyperplusProductToCart() {
		GloItProductListPage.addHyperPlusPlpProductToCart();
	}
}
	
