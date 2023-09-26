package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OrderSuccessPage extends PageObject {

    //@Getter
    public String orderNumber;
    // ELEMENT MAPPING
    public By pageHeading = By.cssSelector("span.base");
    public By generatedOrderNumber = By.cssSelector("a.order-number,div.checkout-success p span");
    public By printReceiptLink = By.cssSelector("a.print");
    //CSS Selector changed after UAT's deployment - UK - Added By Neha (03/25/20)
    public By continueShoppingButtonUK = By.cssSelector("a.action.secondary.continue");
    public By continueShoppingButton = By.cssSelector("a.action.primary.continue");
    public By eleOrderNumberViewOrder = By.cssSelector("div.order-incre-cls");
    public By eleOrderStatusViewOrder = By.cssSelector("div.order-status");
    public By eleOrderDateViewOrder = By.cssSelector("div.order-date:nth-child(3) > date:nth-child(2)");
    public By ItemsOrderedTable = By.cssSelector("#my-orders-table");
    private final static By GERMAN_LOGIN_LIST_OPTIONS = By.cssSelector("li.userbar-item.customer.active > div > ul > li > a");
    private final static By PERSON_ICON = By.cssSelector(UrlBuilder.getMessage("personIconReference.key"));
    private By RECENT_ORDERS_LINK = By.cssSelector("div#account-nav li:nth-child(3) > a");

    //Order Confirmation Page Links
    public By lnkOrderNumber = By.cssSelector("a.order-number");
    public By lnkAccountPage = By.cssSelector("p.checkout-message:nth-child(2) > a:nth-child(1)");
    public By lnkYourAccount = By.cssSelector("a.action.primary.account");
    public By lnkContinueShopping = By.cssSelector("a.action.secondary.continue");
    public By myOrdersTable = By.cssSelector("#my-orders-table");
    private final static By ORDER_COFIRM_MSG = By.cssSelector("h1.page-title");
    private final static By ORDER_COFIRM_MSG_VELO = By.cssSelector("div.checkout-success > h2");
    private final static By LOYALITY_POINTS_SECTION = By.cssSelector("table.data.table.table-reward-history");
    private final static By ORDER_NUMBER_LOYALITY_POINTS = By.xpath("//td[@class='col reason']");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".checkout-success p:first-child");
    private static final By CHECKOUT_MESSAGE = By.cssSelector(".checkout-message");
    private static final By GO_ON_WITH_SHOPPING_LINK = By.cssSelector("a.action.primary.continue");
    private final static By SHIPPING_COST_ORDER = By.cssSelector(".shipping > .amount > .price");
    private final static By VUSE_DE_SHIPPING_TO = By.cssSelector("#shipping");
    private final static By VUSE_DE_DYNA_DATA = By.cssSelector(".page-wrapper .header-assurance");
    private final static By ORDER_COFIRM_STATUS = By.cssSelector("div.order-status");
    private static final By ORDER_NUMBER_LINE = By.cssSelector(".order-number-table .content");
    private static final By ORDER_DETAILS_STRENGTH_VARIANT=By.cssSelector("dl.item-options > dd");
    private static final By ORDER_STATUS_ORDER_HISTORY = By.cssSelector("table.data.table.table-order-items.history tbody tr:nth-child(1) > td.col.status");
    public final static By ORDER_SUCCESS_PAGE_MESSAGE=By.cssSelector("#html-body > div.page-wrapper > div.page.messages>div");

    //Lyft LAB
    private final static By BUNDLE_PRODUCT_DETAILS = By.xpath("//tbody[2]//following::tr[contains(@id,'order-item-row')]");
    private final static By BUNDLE_SKUs_DETAILS = By.xpath("//tr[contains(@id,'order-item-row')]//following::td[@class='col value']");
    private final static By SINGLE_PRODUCT_DETAILS = By.xpath("//tr[contains(@id,'order-item-row')]");
    private final static By PRODUCT_NAME = By.cssSelector("td.col.name");
    private final static By PRODUCT_STRENGTH = By.cssSelector("dl.item-options");
    private final static By PRODUCT_QTY = By.cssSelector("td.col.qty");
    private final static By CMS_BLOCK_ORDER_CONFIRMATION = By.cssSelector("div.column.main div.checkout-success:nth-child(3) div:nth-child(3) div:nth-child(1) > p:nth-child(1)");
    private final static By INVALID_BASKET_MESSAGE = By.cssSelector("div.invalid-basket-message-content");
    private final static By MAKE_ALL_SUBSCRIPTION_BUTTON = By.cssSelector("button.action.accept-subscriptions.primary");
    private final static By MAKE_ALL_ONETIME_BUTTON = By.cssSelector("button.action.accept-one-time-purchases.secondary");
    private final static By CHANGE_TO_SUBSCRIPTION_BUTTON = By.cssSelector("button.action.primary.action-accept");
    private final static By CANCEL_SUBSCRIPTON_BUTTON = By.cssSelector("button.action.secondary.action-dismiss");
    private final static By GLOBAL_SUBS_MANAGE_SUBS = By.cssSelector(".checkout-message.spro-message > a");
    private final static By ORDER_STATUS = By.cssSelector("span.order-status,div.order-status");

  public String returnPageHeading() {
    return waitForExpectedElement(pageHeading).getText();
  }

  public String getGeneratedOrderNumber() throws InterruptedException {
    if (UrlBuilder.isFirefox()) { Thread.sleep(3000);}
      orderNumber = waitForExpectedElement(generatedOrderNumber, 45).getText();
      return orderNumber;
  }

  public boolean isPrintReciptPresent(){
      switch (UrlBuilder.getLocale()) {
          case "dk":
          case "mx":
          case "vusemx":
              waitForExpectedElement(printReceiptLink, 50).isDisplayed();
              break;
      }
      try {
          return waitForExpectedElement(printReceiptLink, 30).isDisplayed();
      }
      catch(NullPointerException e){
          return waitForExpectedElement(printReceiptLink, 30).isDisplayed();
      }
  }

  public void clickContinueShoppingButton() {
      if (doesURLContain("/uk/") || doesURLContain("/gb/en")) {
          clickByElementByQueryJSExecutor(continueShoppingButtonUK);
      } else {
          clickByElementByQueryJSExecutor(continueShoppingButton);
      }
  }

    public void verifyURLStatusCodeIsValid(String strURL) throws Throwable {
        URL obj = new URL(strURL);
        //Opening a connection
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        //Sending the request
        conn.setRequestMethod("GET");

        int responsePDFPage = conn.getResponseCode();
        assertEquals(responsePDFPage, 200);
    }

  public boolean getURLStatusCodeIsValid(String strURL) throws Throwable {
    URL obj = new URL(strURL);
    //Opening a connection
    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

    //Sending the request
    conn.setRequestMethod("GET");

    int responsePDFPage = conn.getResponseCode();
    LOG.info("Response code and URL are  --> "+responsePDFPage + " " + obj);
    if (responsePDFPage == 200 || responsePDFPage== 201)
      return true;
    else
      return false;
  }

    public boolean verifyItemsOrderedTableContent() {
        ArrayList<String> ItemsOrderedTableHeadings;
        int matchCount = 0;

        ItemsOrderedTableHeadings = new ArrayList<>(Arrays.asList("Product Name", "SKU", "Price", "Quantity", "Subtotal"));

        for (WebElement title : getTableHeaders(ItemsOrderedTable)) {
            if (ItemsOrderedTableHeadings.contains(title.getText())) {
                matchCount++;
                if (title.getText().contains("Subtotal"))
                    break;
            }
        }
        return matchCount == 5;
    }

    public boolean verifyBillingDeliveryAddressAndPaymentMethods() {
        ArrayList<String> AddressPaymentSections;
        int matchCount = 0;
        List<WebElement> eleSections = getWebDriver().findElements(By.cssSelector("strong.box-title > span:nth-child(1)"));
        AddressPaymentSections = new ArrayList<>(Arrays.asList("Delivery Address", "Billing Address", "Payment Method"));

        for (WebElement ele : eleSections) {
            if (AddressPaymentSections.contains(ele.getText())) {
                matchCount++;
            }
        }
        return matchCount == 3;
    }

    public int verifyOrderConfirmationPDFIsDisplayed() {
        int responsePDFPage = 0;
        try {
            String strURL = getWebDriver().getCurrentUrl() + ".pdf";
            getWebDriver().navigate().to(strURL);
            URL obj = new URL(strURL);
            //Opening a connection
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            //Sending the request
            conn.setRequestMethod("GET");
            responsePDFPage = conn.getResponseCode();
        } catch (Exception Ex) {
          LOG.info("Failed to verify Order Confirmation PDF due to exception: " + Ex.getMessage());
        }
        return responsePDFPage;
    }

    public void verifyLabBundlePackDetails() {
        List<WebElement> lstOrderDetails = waitForExpectedElement(myOrdersTable).findElements(BUNDLE_PRODUCT_DETAILS);
        if ("lyftse".equals(UrlBuilder.getLocale())) {
        } else {
            assertTrue(lstOrderDetails.get(0).findElement(PRODUCT_NAME).getText().contains("Collection 3 PACK") || lstOrderDetails.get(0).findElement(PRODUCT_NAME).getText().contains("Collection 3-PACK") || lstOrderDetails.get(0).findElement(PRODUCT_NAME).getText().contains("3-PACK") || lstOrderDetails.get(0).findElement(PRODUCT_NAME).getText().contains("3 PACK"));
            assertTrue(lstOrderDetails.get(0).findElement(PRODUCT_STRENGTH).getText().contains("Regular"));
            assertTrue(lstOrderDetails.get(0).findElement(PRODUCT_QTY).getText().contains("1"));
        }
    }

    public void verifyLabSKUsDetails() {
        int intCounter = 1;
        List<WebElement> lstBundlePackSKUs = waitForExpectedElement(myOrdersTable).findElements(BUNDLE_SKUs_DETAILS);
        for (WebElement eleSKU : lstBundlePackSKUs) {
            assertTrue(eleSKU.getText().contains("1 x EDT.01_0" + intCounter) || eleSKU.getText().contains("1 x EDT.02_0" + intCounter));
            intCounter++;
        }
    }

    public void verifyLabSingleProductDetails() {
        WebElement eleOrderDetails = waitForExpectedElement(myOrdersTable).findElement(SINGLE_PRODUCT_DETAILS);
        if ("lyftse".equals(UrlBuilder.getLocale())) {
            assertTrue(eleOrderDetails.findElement(PRODUCT_NAME).getText().contains(UrlBuilder.getMessage("SubscriptionItemTerm.key")));
            assertTrue(eleOrderDetails.findElement(PRODUCT_STRENGTH).getText().contains("Regular"));
            assertTrue(eleOrderDetails.findElement(PRODUCT_QTY).getText().contains("5"));
        } else {
            assertTrue(eleOrderDetails.findElement(PRODUCT_NAME).getText().contains("EDT.01_01") || eleOrderDetails.findElement(PRODUCT_NAME).getText().contains("EDT.02_01"));
            assertTrue(eleOrderDetails.findElement(PRODUCT_STRENGTH).getText().contains("Regular"));
            assertTrue(eleOrderDetails.findElement(PRODUCT_QTY).getText().contains("3"));
        }
    }

    public void assertLABProductOrderDetails() {
        if (doesURLContain("se/sv/") || doesURLContain("dk/da/")) {
            verifyLabSingleProductDetails();
            verifyLabBundlePackDetails();
            verifyLabSKUsDetails();
        }
    }

    public boolean orderConfirmationMessage() {
        if (UrlBuilder.getLocale().contains("velode")||UrlBuilder.getLocale().contains("epok")) {
            waitForExpectedElement(ORDER_COFIRM_MSG_VELO, 30).isDisplayed();
        } else {
            waitForAjaxElementNotToBePresent(getWebDriver(),10);
            waitForExpectedElement(ORDER_COFIRM_MSG, 30).isDisplayed();
        }
        return true;
    }


    public void verifyCMSBlockFromFirstPurchase() throws Throwable {
        if (getWebDriver().findElements(CMS_BLOCK_ORDER_CONFIRMATION).size() > 0) {
            assertTrue(waitForExpectedElement(CMS_BLOCK_ORDER_CONFIRMATION).isDisplayed());
        }
    }

    public void grabAndOutputOrderNumber() throws Throwable {
        LOG.info("\n ********** ORDER NUMBER :  " + getGeneratedOrderNumber());
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        orderNumber = getGeneratedOrderNumber();
    }

    public void clickOnOrderNumberToBeTakenToOrderViewOrderPage() throws Throwable {
        orderNumber = getGeneratedOrderNumber();
        waitForExpectedElement(By.linkText(orderNumber), 10);
        clickByElementByQueryJSExecutor(By.linkText(orderNumber));
        LOG.info("Order number should of been clicked");
    }

    public void grabContentsOfMatchedRowAndAssertOrderStatus(String orderNumber) throws Throwable {
        String orderStatus = "";
        if (!getWebDriver().getCurrentUrl().contains("de/de") && (!getWebDriver().getCurrentUrl().contains("/uk/")) && (!getWebDriver().getCurrentUrl().contains("ie/en")) &&
           (!getWebDriver().getCurrentUrl().contains("mx/es/") && (!getWebDriver().getCurrentUrl().contains("fr/fr/") && (!getWebDriver().getCurrentUrl().contains("gb/en/") &&
           (!getWebDriver().getCurrentUrl().contains("se/sv") && (!UrlBuilder.getLocale().equals("dk")) && (!UrlBuilder.getLocale().equals("vypeit") && (!UrlBuilder.getLocale().equals("nl")) &&
           (!UrlBuilder.getLocale().equals("lyftdk") && (!UrlBuilder.getLocale().equals("vusedk") && (!UrlBuilder.getLocale().equals("vuseco")&&(!UrlBuilder.getLocale().equals("vuseit"))))))))))) {
            for (WebElement rowData : getTableRows(myOrdersTable)) {
                if (rowData.getText().contains(orderNumber)) {
                    LOG.info("\n Match FOUND for order number : " + orderNumber);
                    LOG.info(rowData.getText());
                    orderStatus = rowData.findElement(By.cssSelector("td.col.status")).getText();

                }
            }
            AssertJUnit.assertEquals("*** ERROR - orderStatus NOT as expected - should be 'Awaiting Shipment, but is : " + orderStatus, orderStatus, UrlBuilder.getMessage("orderStatus.key"));
        }

        if (doesURLContain("fr/fr/") || doesURLContain("gb/en/") || UrlBuilder.getLocale().equals("dk")) {
            AssertJUnit.assertFalse(waitForExpectedElement(By.cssSelector("div.order-status")).getText().isEmpty());
        }
        if (doesURLContain("dk/da/") && UrlBuilder.getLocale().equals("dk")) {
            assertTrue(waitForExpectedElement(ORDER_COFIRM_STATUS).getText().contains(UrlBuilder.getMessage("orderStatus.key")));
        }
        if (doesURLContain("se/sv/") && UrlBuilder.getLocale().equals("lyftse")) {
            assertTrue(waitForExpectedElement(By.cssSelector("span.order-status")).getText().contains(UrlBuilder.getMessage("orderStatus.key")));
            assertLABProductOrderDetails();
        }
        if (doesURLContain("dk/da/") && UrlBuilder.getLocale().equals("lyftdk")) {
            assertTrue(waitForExpectedElement(By.cssSelector("span.order-status")).getText().contains(UrlBuilder.getMessage("orderStatus.key")));
            assertLABProductOrderDetails();
        }
        if (UrlBuilder.getLocale().equals("co")) {
            if(doesURLContain("/order/history"))
                assertTrue(waitForExpectedElement(ORDER_STATUS_ORDER_HISTORY).getText().contains(UrlBuilder.getMessage("orderStatus.key")));
            else
                assertTrue(waitForExpectedElement(By.cssSelector("span.order-status")).getText().contains(UrlBuilder.getMessage("orderStatus.key")));
        }
        if (doesURLContain("mx/es/") || UrlBuilder.getLocale().equals("nl")) {
            assertTrue(waitForExpectedElement(ORDER_STATUS).getText().contains(UrlBuilder.getMessage("orderStatus.key")));
        }
        if (UrlBuilder.getLocale().equals("vypeit")||UrlBuilder.getLocale().equals("vuseit")){
            assertTrue(waitForExpectedElement(ORDER_COFIRM_STATUS).getText().contains(UrlBuilder.getMessage("orderStatus.key")));
        }
    }

    public String grabOrderNumberAndAssertOrderStatusOnOrderDetailsPage() throws Throwable {
        grabAndOutputOrderNumber();
        clickOnOrderNumberToBeTakenToOrderViewOrderPage();
        grabContentsOfMatchedRowAndAssertOrderStatus(orderNumber);
        return orderNumber;
    }

    public void clickPersonIcon() {
        waitForExpectedElement(PERSON_ICON, 20);
        clickByElement(PERSON_ICON);
        if (doesURLContain("de/de")) {
            clickByElementByQueryJSExecutor(GERMAN_LOGIN_LIST_OPTIONS);
        }
    }

    public void assertLoyalityPointsAgainstGeneratedOrderNumber() throws Throwable {
        orderNumber = getGeneratedOrderNumber();
        clickPersonIcon();
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("loyalityPointsLinkText.key")), 10);
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("loyalityPointsLinkText.key")));
        waitForExpectedElement(LOYALITY_POINTS_SECTION, 10);
        for (WebElement rowData : getTableCols(LOYALITY_POINTS_SECTION)) {
            if (rowData.findElement(ORDER_NUMBER_LOYALITY_POINTS).getText().contains(orderNumber)) {
                assertTrue(rowData.findElement(By.xpath("//*[contains(text(),'" + orderNumber + "')]//following::td[@class='col points'][1]")).getText().contains("+149"));
            }
            break;
        }
    }

    public void clickRecentOrders() {
        clickByElementByQueryJSExecutor(RECENT_ORDERS_LINK);
    }

    public void eyesCheckOrderSuccessPage() {
        if (Props.EYES_ON && EyesCheckpoints.ORDER_CONFIRMATION_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.ORDER_CONFIRMATION_PAGE.getName();
            switch (UrlBuilder.getLocale()) {
                case "epok":
                    eyes.check(checkpointName, Target.window().fully().layout(ORDER_NUMBER_LINE));
                    break;
                case "kz":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully()
                                .layout(SUCCESS_MESSAGE,
                                        HomePage.HEADER_TOP));
                    } else {
                        eyes.check(checkpointName, Target.window().fully().layout(SUCCESS_MESSAGE));
                    }
                    break;
                case "vusede":
                  if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                    eyes.check(checkpointName, Target.window().fully()
                            .layout(SUCCESS_MESSAGE,
                                    CHECKOUT_MESSAGE));
                  } else {
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(VUSE_DE_SHIPPING_TO,
                                    VUSE_DE_DYNA_DATA)
                            .layout(SUCCESS_MESSAGE,
                                    CHECKOUT_MESSAGE));
                  }
                    break;
                case "vusedk":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully().layout(SUCCESS_MESSAGE));
                    } else {
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(HomePage.SLICK_LIST)
                                .layout(SUCCESS_MESSAGE));
                    }
                    break;
                case "mx":
                case "vusemx":
                case "vusefr":
                case "vuseco":
                    WebElement successMessage = waitForExpectedElement(SUCCESS_MESSAGE,8);
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully()
                                .layout(successMessage));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(HomePage.MESSAGE_ROW)
                                .layout(successMessage));
                    }
                    break;
                case "vuseit":
                case "vuseza":
                case "pl":
                case "glode":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully().layout(SUCCESS_MESSAGE));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(HomePage.MESSAGE_ROW)
                                .layout(SUCCESS_MESSAGE));
                    }
                    break;
                case "glojp":
                    eyes.check(checkpointName, Target.window().fully().layout(lnkOrderNumber));
                    break;
                default:
                    eyes.check(checkpointName, Target.window().fully().layout(SUCCESS_MESSAGE));
            }
        }
    }

    public void assertMessageAboutBasket() {
        openBasketifNotOpenLogic();
        waitForExpectedElement(HomePage.VIEW_BASKET).click();
        waitForCheckoutPageElement();
        assertTrue(waitForExpectedElement(INVALID_BASKET_MESSAGE, 10).isDisplayed());
        assertTrue(waitForExpectedElement(MAKE_ALL_SUBSCRIPTION_BUTTON, 10).isDisplayed());
        assertTrue(waitForExpectedElement(MAKE_ALL_ONETIME_BUTTON, 10).isDisplayed());
    }

    public void removeOneTimePurchaseProduct() {
        waitForExpectedElement(MAKE_ALL_SUBSCRIPTION_BUTTON, 10).click();
        assertTrue(waitForExpectedElement(CHANGE_TO_SUBSCRIPTION_BUTTON, 10).isDisplayed());
        assertTrue(waitForExpectedElement(CANCEL_SUBSCRIPTON_BUTTON, 10).isDisplayed());
        waitForExpectedElement(CHANGE_TO_SUBSCRIPTION_BUTTON, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        waitForExpectedElement(HomePage.proceedToCheckoutFromBasketPage, 10);
    }

    public void waitForCheckoutPageElement() {
        waitForExpectedElement(HomePage.PAGE_TITLE_WRAPPER);
        waitForExpectedElement(HomePage.GRAND_TOTAL, 10);
    }

    public void openBasketifNotOpenLogic(){
        waitForPage();
        boolean miniCartOpen = getWebDriver().findElements(HomePage.MINI_CART_OPEN_STATUS).size() != 0;
        if (!miniCartOpen) {
            clickOnBasketIcon();
        }
    }

    public void clickOnBasketIcon(){
        waitForExpectedElement(HomePage.MINI_CART_OPEN_STATUS,10);
        boolean miniCartOpen = getWebDriver().findElements(HomePage.MINI_CART_OPEN_STATUS).size() != 0;
        if (!miniCartOpen) {
            clickByElementByQueryJSExecutor(HomePage.BASKET_ICON);
        }
    }

  public void assertGlobalSubsManageSubsLink(String expectedMessage){
      waitForAjaxElementNotToBePresent(webDriver,10);
      assertEquals(waitForExpectedElement(GLOBAL_SUBS_MANAGE_SUBS).getText().toLowerCase(),expectedMessage);
  }

  public void assertShippingPriceForOrder(String price){
      assertEquals(waitForExpectedElement(SHIPPING_COST_ORDER).getText(),price);
  }

    public void assertAccountPageLinkRedirectsToURL(String strExpectedText) throws Throwable{
        String strURL = waitForExpectedElement(lnkAccountPage).getAttribute("href");
        assertTrue(strURL.contains(UrlBuilder.getMessage(strExpectedText)));
        verifyURLStatusCodeIsValid(strURL);
    }

    public void assertYourAccountLinkRedirectsToURL(String strExpectedText) throws Throwable {
        String strURL = waitForExpectedElement(lnkYourAccount).getAttribute("href");
        assertTrue(strURL.contains(UrlBuilder.getMessage(strExpectedText)));
        verifyURLStatusCodeIsValid(strURL);
    }

    public void assertContinueShoppingLinkRedirectsToURL() throws Throwable {
      String strURL = "";
      if(UrlBuilder.getLocale().equalsIgnoreCase("nl") || UrlBuilder.getLocale().equalsIgnoreCase("de") || UrlBuilder.getLocale().equalsIgnoreCase("mx"))
          strURL= waitForExpectedElement(GO_ON_WITH_SHOPPING_LINK).getAttribute("href");
      else
          strURL= waitForExpectedElement(lnkContinueShopping).getAttribute("href");
        assertTrue(strURL.contains(UrlBuilder.getUrl()));
        verifyURLStatusCodeIsValid(strURL);
    }

    public void assertOrderConfirmationPageLinksRedirectsToCorrectURLs() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "dk":
            case "de":
            case "ie":
                assertAccountPageLinkRedirectsToURL("customerAccountURL.key");
                assertYourAccountLinkRedirectsToURL("customerAccountURL.key");
                assertContinueShoppingLinkRedirectsToURL();
                break;
            case "fr":
            case "uk":
            case "vuseuk":
            case "vusefr":
                assertYourAccountLinkRedirectsToURL("customerAccountURL.key");
                assertContinueShoppingLinkRedirectsToURL();
                break;
            case "nl":
            case "mx":
            case "vusemx":
                assertContinueShoppingLinkRedirectsToURL();
                break;
            default:
        }
    }

    public void assertBackToMyOrdersLinkRedirectsToCorrectURL() throws Throwable {
      switch (UrlBuilder.getLocale()) {
          case "fr":
          case "vusefr":
          case "mx":
          case "vusemx":
          case "vuseco":
          break;
        default:
        String strURL = waitForExpectedElement(By.cssSelector("a.action.back")).getAttribute("href");
        assertTrue(strURL.contains("/sales/order/history/"));
        verifyURLStatusCodeIsValid(strURL);
    }
  }

    public void assertOrderConfirmationPDFIsDisplayedInNewTab() {
      switch (UrlBuilder.getLocale()) {
          case "fr":
          case "vusefr":
          case "mx":
          case "vusemx":
          case "vuseco":
          break;
        default:
        switchBetweenWindowTabs(1);
        assertTrue(getWebDriver().getCurrentUrl().contains("sales/order/print/order_id/"));
        assertEquals(verifyOrderConfirmationPDFIsDisplayed(), 200);
    }
  }

    public void assertCBDStrengthVariantOnOrderDetailsPage() {
        String StrengthVariant_OrderDetails = waitForExpectedElement(ORDER_DETAILS_STRENGTH_VARIANT).getText();
        assertTrue(StrengthVariant_OrderDetails.contains(System.getProperty("selectedCBDStrength")));
    }
  public boolean assertFreeGiftMessage(){
      try{
          return waitForExpectedElement(ORDER_SUCCESS_PAGE_MESSAGE).isDisplayed();
      }catch(Exception e){
          return false;
      }
  }

}
