package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.page_objects.gui.epok.EpokProductListPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class EpokProductListPageSteps {

	private EpokProductListPage epokProductListPage;

	public EpokProductListPageSteps(EpokProductListPage epokProductListPage) {
		this.epokProductListPage =epokProductListPage;
	}

	@Then("^validate Success Message of add to cart product$")
	public void validate_Success_Message_add_to_cart_product() {
		epokProductListPage.productAddToCartSuccessMsg();
	}

}
	
