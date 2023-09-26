package com.salmon.test.step_definitions.gui.admin;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.BATHelper;
import com.salmon.test.page_objects.gui.admin.OrderPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class OrderPageSteps {

  private OrderPage orderPage;
  BATHelper batHelper;

  public OrderPageSteps(OrderPage orderPage, BATHelper batHelper) {
    this.orderPage = orderPage;
    this.batHelper = batHelper;
  }

  @Given("^user navigates to order page$")
  public void userNavigatesToOrderPage() throws Throwable{
    orderPage.clickSalesLink();
    orderPage.clickOrderLink();

  }

  @And("^order list is displayed correctly$")
  public void orderListIsDisplayedCorrectly()  {
    Assert.assertTrue(orderPage.isPageTitlePresent());
    Assert.assertEquals(orderPage.getPageTitle(),"Orders");
    Assert.assertTrue(orderPage.isOrderRecordTextPresent());
    Assert.assertNotEquals(orderPage.getOrderRecordNumbers(),0);
  }

  @And("^user selects the first order entry$")
  public void userSelectsTheFirstOrderEntry() throws Throwable {
    orderPage.clearAllSearch();
    orderPage.selectItemInOrderTable(0);
  }

  @And("^users clicks view link$")
  public void usersClicksViewLink() {
    orderPage.clickViewLink();
  }

  @Then("^order info page is displayed correctly$")
  public void orderInfoPageIsDisplayedCorrectly()  {
    Assert.assertTrue(orderPage.isOrderNoValid());
    Assert.assertTrue(orderPage.isOrderStatusPresent());
  }

  @And("^user searches order by search keyword$")
  public void userSearchesOrderBySearchKeyword() throws Throwable {
    orderPage.enterSearchTerm(UrlBuilder.getMessage("loginValidEmail.key"));
    orderPage.clickSearchBtn();
  }

  @Then("^search result is shown correctly$")
  public void searchResultIsShownCorrectly() {
    Assert.assertTrue(orderPage.isOrderRecordTextPresent());
    Assert.assertNotEquals(orderPage.getOrderRecordNumbers(),0);
    Assert.assertNotNull(orderPage.getOrderId());
    Assert.assertNotNull(orderPage.getPurchasePoint());
    Assert.assertNotNull(orderPage.getPurchaseDate());
    Assert.assertNotNull(orderPage.getShipToName());
    Assert.assertNotNull(orderPage.getBillToName());
    Assert.assertNotNull(orderPage.getGrandTotalBase());
    Assert.assertNotNull(orderPage.getGrandTotalPurchase());
    Assert.assertNotNull(orderPage.getStatus());
    orderPage.clickRemoveSearchFilter();//clean env
  }

  @Then("^order address info page is displayed correctly$")
  public void orderAddressInfoPageIsDisplayedCorrectly() {
    Assert.assertNotNull(orderPage.getBillingAddress());
    Assert.assertNotNull(orderPage.getShippingAddress());

  }

  @Then("^order account info is displayed correctly$")
  public void orderAccountInfoIsDisplayedCorrectly() {
    Assert.assertNotNull(orderPage.getAccountEmail());
    Assert.assertNotNull(orderPage.getAccountName());
  }


  @And("^eyes check error message after trigger error message on checkout for \"([^\"]*)\"$")
  public void eyesCheckErrorMessageAfterTriggerErrorMessageOnCheckoutFor(
      String paymentType) throws Throwable {
    switch (UrlBuilder.getSite()) {
      case "vuse":
        batHelper.purchaseAProductAndTriggerError("searchItem.key", paymentType);
        break;
    }
  }
}
