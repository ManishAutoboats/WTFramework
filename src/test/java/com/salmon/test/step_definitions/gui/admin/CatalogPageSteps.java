package com.salmon.test.step_definitions.gui.admin;


import com.salmon.test.page_objects.gui.admin.ProductPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class CatalogPageSteps {

  private ProductPage productPage;
  private static String productUrlPath;

  public CatalogPageSteps(ProductPage productPage) {
    this.productPage = productPage;
  }

  public static String getProductUrlPath() {
    return productUrlPath;
  }

  @Given("^user navigates to product page$")
  public void userNavigatesToProductPage() throws Throwable {
    productPage.clickCatalogLink();
    productPage.clickProductLink();
  }

  @And("^product list is displayed correctly$")
  public void productListIsDisplayedCorrectly() throws Throwable {
    Assert.assertTrue(productPage.isPageTitlePresent());
    Assert.assertEquals(productPage.getPageTitle(),"Products");
    Assert.assertTrue(productPage.isProductRecordTextPresent());
    Assert.assertNotEquals(productPage.getOrderRecordNumbers(),0);
  }

  @And("^user selects the first entry in product table$")
  public void userSelectsTheFirstEntryInProductTable() throws Throwable {
    productPage.selectItemInCustomerTable(0);
  }

  @And("^user clicks edit link in product table$")
  public void userClicksEditLinkInProductTable() throws Throwable {
    productPage.clickEditLink();
  }

  @And("^product info is displayed correctly$")
  public void productInfoIsDisplayedCorrectly() {
    Assert.assertTrue(productPage.isProductDetailPagePresent());
  }

  @And("^user stores the product url in the clipboard$")
  public void storeProductUrlKey() {
    productUrlPath = productPage.getProductUrl();
  }

  @And("^user enables subscription Pro for the product$")
  public void enableProductSubscriptionPro() throws Throwable {
    productPage.enableSubscribeProBlock();
  }

  @When("^user saves the product changes$")
  public void saveProductChanges() {
    productPage.clickSaveButton();
  }

  @And("^assert success message is displayed$")
  public void assertConfirmationMessageIsDisplayed(){
    productPage.productSavedMessageDisplayed();
  }
}
