package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.gloIt.GloItCheckoutPage;
import com.salmon.test.page_objects.gui.PaymentPage;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import static com.salmon.test.framework.PageObject.LOADER;
import static com.salmon.test.page_objects.gui.constants.Context.ORDER_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class OrderSuccessPageSteps {
  private static final Logger LOG = LoggerFactory.getLogger(OrderSuccessPageSteps.class);
  public static String orderNumber;
  private OrderSuccessPage orderSuccessPage;
  private AccountDashboardPage accountDashboardPage;
  private PaymentPage paymentPage;
  private AddNewAddressPage addNewAddressPage;
  private HomePage homePage;
  private ScenarioContext scenarioContext;
  private BATHelper batHelper;

  public OrderSuccessPageSteps(TestContext testContext, OrderSuccessPage orderSuccessPage, BATHelper batHelper, AccountDashboardPage accountDashboardPage, PaymentPage paymentPage, AddNewAddressPage addNewAddressPage, HomePage homePage) {
    this.orderSuccessPage = orderSuccessPage;
    this.batHelper=batHelper;
    this.accountDashboardPage=accountDashboardPage;
    this.paymentPage=paymentPage;
    this.addNewAddressPage=addNewAddressPage;
    this.homePage=homePage;
    this.scenarioContext = testContext.getScenarioContext();
  }


  @And("^assert print receipt link is present$")
  public void assertPrintReceiptLinkIsPresent() {
    switch (UrlBuilder.getLocale()){
      case "vusede":
        orderSuccessPage.waitForAjaxElementNotToBePresent(orderSuccessPage.getWebDriver(),10);
        break;
      case "vuseco":
          orderSuccessPage.waitForAjaxElementNotToBePresent(orderSuccessPage.getWebDriver(),20);
        break;
      case "uk":
      case "vuseuk":
      case "fr":
      case "vusefr":
        orderSuccessPage.waitForAjaxElementNotToBePresent(orderSuccessPage.getWebDriver(),15);
        break;
      case "mx":
      case "vusemx":
      case "vypeit":
      case "vuseit":
        LOG.info("we don't have a generate invoice link at the success page");
        break;
      default:
        SoftAssert softAssert= new SoftAssert();
        orderSuccessPage.waitForElementToAppearAndDisappear(LOADER,2,12);
        softAssert.assertTrue(orderSuccessPage.isPrintReciptPresent());
    }
  }

  @And("^grab and output Order number$")
  public void grabAndOutputOrderNumber() throws Throwable {
    orderSuccessPage.waitForElementToAppearAndDisappear(GloItCheckoutPage.loader,12,12);
    orderNumber = orderSuccessPage.getGeneratedOrderNumber();
    scenarioContext.setContext(ORDER_NUMBER, orderNumber);
  }

  @And("^user clicks on the 'continue shopping' button$")
  public void userClicksOnTheContinueShoppingButton() {
    orderSuccessPage.clickContinueShoppingButton();
  }

  @And("^assert order number is displayed Previous orders on page$")
  public void assertOrderNumberIsDisplayedPreviousOrdersOnPage() {
    assertTrue("Expected order number of : " +orderNumber+ " cannot be found",orderSuccessPage.getWebDriver().getPageSource().contains(orderNumber));
  }

  @And("^grab contents of matched row and assert order status$")
  public void grabContentsOfMatchedRowAndAssertOrderStatus() throws Throwable {
    orderSuccessPage.grabContentsOfMatchedRowAndAssertOrderStatus(orderNumber);
  }

  @And("^users clicks on order number to be taken to order view order page$")
  public void usersClicksOnOrderNumberToBeTakenToOrderViewOrderPage() throws Throwable {
    orderSuccessPage.clickOnOrderNumberToBeTakenToOrderViewOrderPage();
  }

  @And("^assert expected order number is present$")
  public void assertExpectedOrderNumberIsPresent() {
    assertTrue(orderSuccessPage.getWebDriver().getPageSource().contains(orderNumber));
  }

  @And("^type to screen thanks you message$")
  public void typeToScreenThanksYouMessage() {
    LOG.info("\n ************ THANKS FOR PLAYING");
  }
  
  @And("^assert Order Number is displayed$")
  public void assertOrderNumberIsDisplayedOnViewOrderPage() {
    String strOrderNumberText = orderSuccessPage.waitForExpectedElement(orderSuccessPage.eleOrderNumberViewOrder).getText();
    assertTrue(strOrderNumberText.contains("") && strOrderNumberText.matches(".*\\d+.*"));
  }
  
  @And("^assert Order Status is displayed$")
  public void assertOrderStatusIsDisplayedOnViewOrderPage() {
    assertTrue(orderSuccessPage.waitForExpectedElement(orderSuccessPage.eleOrderStatusViewOrder).getText().contains("Awaiting Shipment"));
  }
  
  @And("^assert Order Date is displayed$")
  public void assertOrderDateIsDisplayedOnViewOrderPage() {
    assertTrue(orderSuccessPage.waitForExpectedElement(orderSuccessPage.eleOrderDateViewOrder).isDisplayed());
  }
  
  @And("^assert Items Ordered table data is as expected$")
  public void assertItemsOrderedTableDataIsAsExpected() {
		  assertTrue(orderSuccessPage.verifyItemsOrderedTableContent());
  }
  
  @And("^assert Address and Payment Methods sections are displayed$")
  public void assertAddressAndPaymentMethodSectionsAreDisplayed() {
		  assertTrue(orderSuccessPage.verifyBillingDeliveryAddressAndPaymentMethods());
  }
  
  @And("^assert a new tab is opened with url contains '(.*)'$")
  public void assertNewTabOpenedWithURLContains(String strExpectedURL) {
	  orderSuccessPage.switchBetweenWindowTabs(1);
	  assertTrue(orderSuccessPage.getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(strExpectedURL)));
  }
  
  @And("^assert Order Confirmation PDF is displayed with order details$")
  public void assertOrderConfirmationPDFIsDisplayedInNewTab() {
      orderSuccessPage.assertOrderConfirmationPDFIsDisplayedInNewTab();
  }
  
  @And("^assert Order Number link redirects to url '(.*)'$")
  public void assertOrderNumberLinkRedirectsToURL(String strURL) throws Throwable{
	  	assertTrue(orderSuccessPage.waitForExpectedElement(orderSuccessPage.lnkOrderNumber).getAttribute("href").contains(UrlBuilder.getMessage(strURL)));
	  	orderSuccessPage.verifyURLStatusCodeIsValid(UrlBuilder.getMessage(strURL));
  }
  
  @And("^assert ACCOUNT PAGE link redirects to url '(.*)'$")
  public void assertAccountPageLinkRedirectsToURL(String strURL) throws Throwable{
	  	orderSuccessPage.assertAccountPageLinkRedirectsToURL(strURL);
  }
  
  @And("^assert Your Account link redirects to url '(.*)'$")
  public void assertYourAccountLinkRedirectsToURL(String strURL) throws Throwable {
	  	orderSuccessPage.assertYourAccountLinkRedirectsToURL(strURL);
  }

  @And("^Order Confirmation page thankYouMessageHeading is displayed$")
  public void assertOrderConfirmationMessage(){
    assertTrue(orderSuccessPage.orderConfirmationMessage());
}

  @And("^assert CMS block from first purchase on Order Confirmation page$")
  public void assertCMSBlockFromFirstPurchaseOnOrderConfirmationPage() throws Throwable {
        orderSuccessPage.verifyCMSBlockFromFirstPurchase();
  }

  @And("^assert on Order Confirmation page '(.*)' is displayed$")
  public void GloIt_assert_ThankYou_Is_Displayed(String expectedMessage) {
    orderSuccessPage.waitForElementToAppearAndDisappear(LOADER,5,20);
    if (UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
      orderSuccessPage.eyesCheckOrderSuccessPage();
    }
    assertThat(orderSuccessPage.returnPageHeading()).contains(UrlBuilder.getMessage(expectedMessage));
  }

  @And("^Customer makes a home delivery purchase with \"([^\"]*)\"$")
  public void customerMakesAHomeDeliveryPurchaseWith(String paymentType) throws Throwable {
      switch (UrlBuilder.getSite()) {
          case "glo":
              batHelper.purchaseAProductOnGlo(paymentType);
              break;
          case "lyft":
          case "vype":
            batHelper.purchaseAProduct("searchItem.key",paymentType);
            break;
          case "vuse":
              batHelper.purchaseAProduct("searchProductItem.key",paymentType);
              batHelper.waitForPage(40);
              break;
          case "velo":
              batHelper.purchaseAProductOnVelo(paymentType);
              break;
            }
      orderSuccessPage.eyesCheckOrderSuccessPage();
  }


  @And("^user add address details after registration - GloPL$")
    public void addAddressDetailsOnMyAccountPage() {
    homePage.addNewAddress();
    }

  @And("^user clicks on Next button to move head and place the order$")
  public void clickOnNextButtonAndPlaceOrder(){
    if(UrlBuilder.getSite().contains("glo"))
      paymentPage.paymentClickNextButton();
    else if(UrlBuilder.getLocale().equalsIgnoreCase("mx"))
      paymentPage.clickOnNextButtonAndPlaceOrder();
    }

  @And("^grab Order Number and assert Order Status on View Order Details page$")
  public void grabOrderNumberAndAssertOrderStatusOnViewOrderDetailsPage() throws Throwable {
    String orderNumber = orderSuccessPage.grabOrderNumberAndAssertOrderStatusOnOrderDetailsPage();
    scenarioContext.setContext(ORDER_NUMBER, orderNumber);
  }

  @And("^assert Loyality points against the order number generated$")
  public void assertLoyalityPointsAgainstTheGeneratedOrderNumber() throws Throwable {
    orderSuccessPage.assertLoyalityPointsAgainstGeneratedOrderNumber();
  }

  @And("^user clicks on the recent orders link$")
  public void userClicksOnTheRecentOrdersLink() {
    orderSuccessPage.clickRecentOrders();
  }

  @And("^customer place an order with personalized product with \"([^\"]*)\"$")
  public void customerPlaceAnOrderWithPersonalizedProductWith(String paymentType) {

  }

  @And("^Customer makes a home delivery purchase with \"([^\"]*)\" from PDP page$")
  public void customerMakesAHomeDeliveryPurchaseWithFromPDPPage(String paymentType) throws Throwable {
    batHelper.purchaseAProduct(paymentType);
    }

  @And("^assert message about basked is displayed$")
  public void assertMessageAboutBasket() {
    orderSuccessPage.assertMessageAboutBasket();
  }

  @And("^remove one time purchase product$")
  public void removeOneTimePurchaseProduct() {
    orderSuccessPage.removeOneTimePurchaseProduct();
  }

  @And("^assert links on Order Confirmation page redirects to correct URLs$")
  public void assertOrderConfirmationPageLinksRedirectsToCorrectURLs() throws Throwable {
      orderSuccessPage.assertOrderConfirmationPageLinksRedirectsToCorrectURLs();
    }

  @And("^assert Back To My Orders link redirects to correct URL$")
  public void assertBackToMyOrdersLinkRedirectsToCorrectURL() throws Throwable {
      orderSuccessPage.assertBackToMyOrdersLinkRedirectsToCorrectURL();
    }

  @And("^assert that globalSubs Success message '(.*)' appears for order$")
  public void assertThatGlobalSubsSuccessMessageSuccessfulSubscriptionKeyAppearsForOrder(String expectedText) {
    orderSuccessPage.assertGlobalSubsManageSubsLink(UrlBuilder.getMessage(expectedText).toLowerCase());
  }

  @And("^check Subscription Order has free shipping on Order Details Page$")
  public void checkSubscriptionOrderHasFreeShippingOnOrderDetailsPage() {
    switch (UrlBuilder.getLocale()) {
      case "lyftse":
        orderSuccessPage.assertShippingPriceForOrder("0,00 kr");
        break;
      default:
        orderSuccessPage.assertShippingPriceForOrder("0,00 €");
    }
  }

  @And("^assert CBD Strength variant on Order Details Page$")
  public void assertCBDStrengthVariantOnOrderDetailsPage() {
    orderSuccessPage.assertCBDStrengthVariantOnOrderDetailsPage();
  }

  @And("^assert that the '(.*)' thank you message is displayed in the page header$")
  public void assertThatTheThankForPurchaseKeyThankYouMessageIsDisplayed(String key) {
    String expectedThanks = UrlBuilder.getMessage(key).toLowerCase();
    batHelper.assertThatHeaderContains(expectedThanks);
  }

  @And("^Enter \"([^\"]*)\" card details$")
  public void enterPaymentDetails(String paymentType) throws InterruptedException {
    batHelper.SelectAndEnterCardPaymentDetails(paymentType);
  }

  @And("^user navigates to checkout page$")
  public void userNavigatesToCheckoutPage() throws InterruptedException {
    batHelper.checkoutAproduct("searchProductItem.key");
  }

  @And("^assert Free gift items message is not displayed$")
  public void assertFreeGiftItemsMessageIsNotDisplayed() {
    assertFalse(orderSuccessPage.assertFreeGiftMessage());;
  }

  @And("^assert it navigate to success page$")
  public void assertItNavigateToSuccessPage() {
    batHelper.waitForPage(40);
    assertTrue(orderSuccessPage.getWebDriver().getCurrentUrl().contains("success"));
  }
}