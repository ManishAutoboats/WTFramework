package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.testng.Assert.*;

public class OrderDetailsPage extends PageObject {
    private final SoftAssert softAssert;
    private final GloItCheckoutPage gloItCheckoutPage;

    public OrderDetailsPage(GloItCheckoutPage gloItCheckoutPage, SoftAssert softAssert) {
        this.gloItCheckoutPage = gloItCheckoutPage;
        this.softAssert=softAssert;
    }
    private static final By ORDER_HISTORY_LINK = By.cssSelector("li.item.order-history>a");
    private static final By GRAND_TOTAL = By.cssSelector("div.grand_total_incl.row>span.amount");
    private static final By SUB_TOTAL = By.cssSelector("div.subtotal.row>span.amount");
    private static final By COURIER_FEE = By.cssSelector("div.shipping.row>span.amount");
    private static final By DISCOUNT = By.cssSelector("div.discount.row>span.amount");
    public static final By ORDER_NUMBER = By.cssSelector("div.order-incre-cls.order-id>span.value");
    public static final By ORDER_DATE = By.cssSelector("div.order-incre-cls.order-date>span.value");
    public static final By ORDER_STATUS = By.cssSelector("div.order-incre-cls.order-status>span.value");
    public static final By ITEMS_COUNT = By.cssSelector("span.items-count");
    private static final By PRODUCT_NAME = By.cssSelector("div.product-item-details>strong.product.name.product-item-name");
    private static final By PRODUCT_QTY = By.cssSelector("li.item.qty>span.content");
    private static final By PRODUCT_PRICE = By.cssSelector("span.cart-price");
    private static final By PRODUCT_IMG = By.cssSelector("table#my-orders-table>tbody>tr>td>div.image-box>img");
    private static final By ORDER_DETAIL_PRODUCT_LIST = By.cssSelector("tbody>tr");
    private static final By EMAIL_PRODUCT_NAME = By.cssSelector("div.email-product-detail>p");
    private static final By EMAIL_PRODUCT_QTY = By.cssSelector("div.qty-price-details>div.item-qty");
    private static final By EMAIL_PRODUCT_PRICE = By.cssSelector("div.qty-price-details>div.item-price");
    private static final By EMAIL_PRODUCT_IMG = By.cssSelector("div.product-image>img");
    private static final By EMAIL_ORDER_DETAILS = By.cssSelector("table.order-details");
    private static final By EMAIL_ORDER_SUMMARY = By.cssSelector("div#my-orders-table");
    private static final By EMAIL_ITEMS_ORDERED = By.cssSelector("div.items-title");
    private static final By EMAIL_ITEMS_LIST = By.cssSelector("table.email-items>tbody>tr");
    private static final By EMAIL_GLO_CONTACTS_DNU = By.cssSelector("div.glo-contacts");
    private static final By EMAIL_GLO_CONTACTS = By.cssSelector("td.glo-contacts");
    private static final By EMAIL_VELO_CONTACTS_DNU = By.cssSelector("div.velo-contacts");
    private static final By EMAIL_VELO_CONTACTS = By.cssSelector("td.weburl-info");
    private static final By EMAIL_THANKYOU_NOTE = By.cssSelector("tr>td>p:last-child");
    private static final By EMAIL_ORDER_NUMBER = By.cssSelector("div:first-child>span.no-link");
    private static final By EMAIL_ORDER_DATE = By.cssSelector("div:nth-child(2)>span.no-link");
    private static final By EMAIL_USERNAME = By.cssSelector("tr>td>p:first-child");
    private static final By EMAIL_BANNER = By.cssSelector("tr>td>p:first-child");
    private static final By EMAIL_HEADER = By.cssSelector("td.header-wrap");
    private static final By EMAIL_GLO_LOGO = By.cssSelector("a.glo-logo");
    private static final By EMAIL_VELO_LOGO = By.cssSelector("a.velo-logo");
    private static final By EMAIL_FOOTER = By.cssSelector("table.footer-bottom>tbody>tr");
    private static final By EMAIL_FOOTER_COMPANY = By.cssSelector("td.footer>span:first-child");
    private static final By EMAIL_FOOTER_RIGHTS = By.cssSelector("td.footer>span:last-child");
    private static final By EMAIL_CANCEL_ORDER_BLOCK = By.cssSelector("tr.order-cancellation");
    private static final By EMAIL_ORDER_CANCEL = By.cssSelector("p.thanks");
    private static final By EMAIL_ORDER_CANCEL_NOTE = By.cssSelector("tr.order-cancellation>td>p:nth-child(3)");
    private static final By EMAIL_SHIPPING_METHOD = By.cssSelector("div.section.method-info>span.section-content");
    private static final By EMAIL_ADDRESS_SECTION = By.cssSelector("div.address-details");
    private static final By EMAIL_COD_FEE = By.cssSelector("div.cash_on_delivery_fee.row>span:last-child");
    private static final By EMAIL_DISCOUNT = By.cssSelector("div.discount.row>span:last-child");
    private static final By EMAIL_GRAND_TOTAL = By.cssSelector("div.grand_total_incl.row>span:last-child");
    private static final By EMAIL_ORDER_MAIN_BODY_COLOR = By.cssSelector("dl:nth-child(2)>dd");
    private static final By EMAIL_ORDER_SIDE_BODY_COLOR = By.cssSelector("dl:nth-child(2)>dd");

    private static final By ORDER_INFORMATION_BLOCK = By.cssSelector("div.block.block-order-details-view");
    private static final By ORDER_SUMMARY_BLOCK = By.cssSelector("div.order-details-summary");
    public static final By M_ORDER_SUMMARY_CLOSE_BTN_IT = By.cssSelector("aside.modal-custom.opc-sidebar.opc-summary-wrapper.custom-slide._show > div.modal-inner-wrap > header > button");
    private static final By PRODUCTS_TABLE = By.cssSelector("table.table-order-items");
    private static final By PAYMENT_METHOD = By.cssSelector("div.box-content>dl.payment-method");
    private static final By DELIVERY_METHOD = By.cssSelector("div.sidebar.sidebar-additional>div>div.block-content>div.box.box-order-shipping-method>div.box-content");
    private static final By DELIVERY_DATE_TIME = By.cssSelector("div.sidebar.sidebar-additional>div>div.block-content>div.box.box-order-shipping-method>div.box-content>div.box-content");
    private static final By ORDER_LIST = By.cssSelector("tbody>tr");
    private static final By CANCEL_BUTTON = By.cssSelector("button.cancel-order");
    private static final By CANCEL_NOTE = By.cssSelector("div.cancel-note");
    private static final By CONFIRM_CANCEL_ORDER = By.cssSelector("a.action.cancel_order");
    private static final By CANCEL_ORDER = By.cssSelector("span.cancel");
    private static final By ORDER_DETAILS_ADDRESS = By.cssSelector("div.box.box-order-shipping-address>div>address");
    private static final By EMAIL_SHIPPING_ADDRESS = By.cssSelector("div.address-details>span:nth-child(2)");
    public static String[] delivery_date_time, orderDetailShipping, total_discount, sub_total, courier_total;
    public static String shippingAddr, orderDetailPayment, orderDetails_date, order_delivery_time, email_shipping_addr;
    public static final By ORDER_DETAILS_TEXT = By.cssSelector(".page-title>span.base");
    private static final By CANCEL_ORDER_SUCCESS_MESSAGE = By.cssSelector("div.message-success.success.message");
    private static final By DELIVERY_FEE_TEXT = By.cssSelector("div.cash_on_delivery_fee.row");
    private static final By COD_TEXT = By.cssSelector("dl.payment-method>dt.title");
    private static final By COD_FEE = By.cssSelector("div.cash_on_delivery_fee.row>span.amount");
    private static float delivery_amt, ORDER_GRAND_TOTAL;
    private static final By EMAIL_RECEIVED = By.cssSelector("tr.ng-scope>td.ng-binding:nth-child(3)");
    private static final By EMAIL_LIST = By.cssSelector("tr.ng-scope");
    public static final By M_MINI_CART_BUTTON_IT = By.cssSelector("div#checkout > div.opc-estimated-wrapper > div.minicart-wrapper > button.action.showcart");
    private static List<WebElement> email_list;
    private final List<Object> productsInEmail = new ArrayList<>();

    public void clickOrderHistoryBreadcrumb() {
        clickByElementByQueryJSExecutor(ORDER_HISTORY_LINK);
        waitForPage();
    }

    public List<Object> productDetail = new ArrayList<>();
    public Map<String, String> productDetailImage = new HashMap<>();

    public void getProductsOnDetailsPage() {
        productDetail.clear();
        List<WebElement> details = presenceOfAllElementsLocatedBy(ORDER_DETAIL_PRODUCT_LIST);
        for (WebElement data : details) {
            productDetail.add(Arrays.asList(data.findElement(PRODUCT_NAME).getText(), data.findElement(PRODUCT_PRICE).getText(), data.findElement(PRODUCT_QTY).getText()));
            productDetailImage.put(data.findElement(PRODUCT_NAME).getText(), gloItCheckoutPage.imgName);
        }
    }

    public void assertProductsOnOrderDetails() {
        getProductsOnDetailsPage();
        softAssert.assertEquals(gloItCheckoutPage.checkoutProducts, productDetail);
        gloItCheckoutPage.isProductImageDisplayed(PRODUCT_IMG);
        softAssert.assertAll();
    }

    public void assertOrderDetailsWithOrderHistory() {
        String[] grand_Total = waitForExpectedElement(GRAND_TOTAL).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
        ORDER_GRAND_TOTAL = Float.parseFloat(grand_Total[0].replaceAll(",", ""));
        String OdrNum = waitForExpectedElement(ORDER_NUMBER).getText();
        String status = waitForExpectedElement(ORDER_STATUS).getText();
        orderDetails_date = waitForExpectedElement(ORDER_DATE).getText();
        softAssert.assertEquals(ORDER_GRAND_TOTAL, Float.parseFloat(OrderHistoryPage.HISTORY_GRAND_TOTAL[0].replaceAll(",", "")));
        softAssert.assertEquals(OdrNum, OrderHistoryPage.orderNumber);
        softAssert.assertEquals(status, OrderHistoryPage.orderStatus);
        softAssert.assertEquals(orderDetails_date, OrderHistoryPage.orderDatehist);
        softAssert.assertAll();
        noOfProductsInOrderDetails();
    }

    public void assertOrderSummarySection() {
        softAssert.assertEquals(ORDER_GRAND_TOTAL, gloItCheckoutPage.actualGrandTotal);
        sub_total = waitForExpectedElement(SUB_TOTAL).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
        softAssert.assertEquals(Float.parseFloat(sub_total[0].replaceAll(",", "")), gloItCheckoutPage.sub_total);
        courier_total = waitForExpectedElement(COURIER_FEE).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
        softAssert.assertEquals(Float.parseFloat(courier_total[0]), Float.parseFloat(gloItCheckoutPage.courier_delivery[0]));
        assertDiscount();
        softAssert.assertAll();
    }

    public String noOfProductsInOrderDetails() {
        List<WebElement> productList = presenceOfAllElementsLocatedBy(ORDER_LIST);
        return String.valueOf(productList.size());
    }

    public void assertNoOfProductsInOrderDetails() {
        assertEquals(gloItCheckoutPage.expectedProductCount, noOfProductsInOrderDetails());
    }

    public void assertPaymentMethod() {
        orderDetailPayment = waitForExpectedElement(PAYMENT_METHOD).getText();
        assertEquals(gloItCheckoutPage.selectedPaymentText, orderDetailPayment);

    }

    public void assertDiscount() {
        if (gloItCheckoutPage.cartDiscountPrice > 0) {
            total_discount = waitForExpectedElement(DISCOUNT).getText().substring(1).split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
            assertEquals(gloItCheckoutPage.cartDiscountPrice, Float.parseFloat(total_discount[0].replaceAll(",","")));
        } else {
            LOG.info("No discount");
        }
    }

    public void assertShippingMethod() throws ParseException {
        gloItCheckoutPage.convertTimeTo24hrFormat();
        orderDetailShipping = waitForExpectedElement(DELIVERY_METHOD,20).getText().split("\\r?\\n");
        assertTrue(gloItCheckoutPage.selectedDeliverytext[0].contains(orderDetailShipping[0]));
        if (gloItCheckoutPage.deliveryDate != null) {
            delivery_date_time = waitForExpectedElement(DELIVERY_DATE_TIME).getText().split(" ");
            String delDate = delivery_date_time[0].replace("\u6708", "/").replace("\u5e74", "/");
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            format.setLenient(false);
            Date delivery_date = format.parse(delDate);
            assertEquals(delivery_date.toString(), gloItCheckoutPage.deliveryDate);
            assertEquals(delivery_date_time[1], gloItCheckoutPage.checkout_delivery_time);
        } else {
            order_delivery_time = waitForExpectedElement(DELIVERY_DATE_TIME).getText().split(": ")[1];
            assertEquals(order_delivery_time, gloItCheckoutPage.checkout_delivery_time);
        }
    }

    public void assertShippingAddressOnOrderDetails() {
        String shippAddr = waitForExpectedElement(ORDER_DETAILS_ADDRESS).getText();
        shippingAddr = shippAddr.replaceAll(" ","").replaceAll("\n", " ");
        assertTrueWithCustomError(gloItCheckoutPage.selectedAddress,shippingAddr);
    }

    public boolean isOrderInformationDisplayed() {
        return waitForExpectedElement(ORDER_INFORMATION_BLOCK,30).isDisplayed();
    }

    public boolean orderSummaryBlockDisplayed() {
        return waitForExpectedElement(ORDER_SUMMARY_BLOCK).isDisplayed();
    }

    public boolean isProductsTableDisplayed() {
        return waitForExpectedElement(PRODUCTS_TABLE).isDisplayed();
    }

    public void getCODFeeOnOrderDetails() {
        delivery_amt = 0;
        if (waitForExpectedElement(COD_TEXT).getText().contains(UrlBuilder.getMessage("cash_payment_text.key"))) {
            String[] delivery_fee = waitForExpectedElement(COD_FEE).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
            delivery_amt = Float.parseFloat(delivery_fee[0]);
        }
    }

    public boolean isCODfeedisplayed() {
        return waitForExpectedElement(DELIVERY_FEE_TEXT).isDisplayed();
    }

    public void assertCODeliveryFee() {
        if (waitForExpectedElement(COD_TEXT).getText().contains(UrlBuilder.getMessage("cash_payment_text.key"))) {
            getCODFeeOnOrderDetails();
            assertEquals(gloItCheckoutPage.delivery_fee_amt, delivery_amt);
        } else {
            LOG.info("Delivery fee is 0");
        }
    }

    public boolean cancelButtonIsDisplayed() {
        return waitForExpectedElement(CANCEL_BUTTON).isDisplayed();
    }

    public boolean cancelNoteIsDisplayed() {
        return waitForExpectedElement(CANCEL_NOTE).isDisplayed();
    }

    public void clickOnCancelOrder() {
        scrollToPageTop();
        try {
            waitForExpectedElement(CANCEL_BUTTON,30).click();
        } catch (Exception e) {
            refreshBrowser();
            waitForExpectedElement(CANCEL_BUTTON,30);
            clickByElementByQueryJSExecutor(CANCEL_BUTTON);
        }
    }

    public void confirmCancelOrder() {
        try {
            clickByElementByQueryJSExecutor(CONFIRM_CANCEL_ORDER);
        } catch (Exception e) {
            LOG.warn("**** Couldn't click confirm cancel order :: Trying again");
            waitForExpectedElement(CONFIRM_CANCEL_ORDER, 30);
            clickByElementByQueryJSExecutor(CONFIRM_CANCEL_ORDER);
        }
    }

    public void doNotCancelOrder() {
        try {
            waitForExpectedElement(CANCEL_ORDER,30);
            clickByElementByQueryJSExecutor(CANCEL_ORDER);
        } catch (Exception e) {
            refreshBrowser();
            clickOnCancelOrder();
            waitForExpectedElement(CANCEL_ORDER,30);
            clickByElementByQueryJSExecutor(CANCEL_ORDER);
        }
    }

    public boolean cancelOrderSuccessMessageIsDisplayed() {
        try {
            return waitForExpectedElement(CANCEL_ORDER_SUCCESS_MESSAGE, 25).isDisplayed();
        }catch(Exception e){
            refreshBrowser();
            return waitForExpectedElement(CANCEL_ORDER_SUCCESS_MESSAGE, 40).isDisplayed();
        }
    }

    public void verifyOrderIsCanceled() {
        waitForExpectedElement(ORDER_STATUS,20);
        if (!getWebDriver().findElement(COD_TEXT).getText().contains(UrlBuilder.getMessage("cash_payment_text.key"))) {
            softAssert.assertEquals(waitForExpectedElement(ORDER_STATUS).getText(), UrlBuilder.getMessage("cardCancelStatus.key"));
        } else {
            softAssert.assertEquals(waitForExpectedElement(ORDER_STATUS).getText(), UrlBuilder.getMessage("cancelStatus.key"));
        }
        softAssert.assertFalse(isCancelOrderButtonPresent());
        softAssert.assertAll();
    }

    public boolean isCancelOrderButtonPresent() {
        try {
            getWebDriver().findElement(CANCEL_BUTTON);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean receivedCancellationEmail() {
        waitForExpectedElement(EMAIL_RECEIVED);
        email_list = presenceOfAllElementsLocatedBy(EMAIL_LIST);
        for (WebElement list : email_list) {
            if (list.findElement(EMAIL_RECEIVED).getText().equals(UrlBuilder.getMessage("cancellationSubject.key"))) {
                return true;
            }
        }
        return false;
    }

    public boolean isHeaderDisplayed() {
        return waitForExpectedElement(EMAIL_HEADER).isDisplayed();
    }

    public boolean isGloLogoDisplayed() {
        return waitForExpectedElement(EMAIL_GLO_LOGO).isDisplayed();
    }

    public boolean isGloContactsDisplayed() {
        return waitForExpectedElement(EMAIL_GLO_CONTACTS).isDisplayed();
    }

    public boolean isVeloLogoDisplayed() {
        return waitForExpectedElement(EMAIL_VELO_LOGO).isDisplayed();
    }

    public boolean isVeloContactsDisplayed() {
        return waitForExpectedElement(EMAIL_VELO_CONTACTS).isDisplayed();
    }

    public void footerIsDisplayed() {
        waitForExpectedElement(EMAIL_FOOTER, 5);
        softAssert.assertTrue(waitForExpectedElement(EMAIL_FOOTER).isDisplayed());
        softAssert.assertTrue(waitForExpectedElement(EMAIL_FOOTER_COMPANY).isDisplayed());
        softAssert.assertTrue(waitForExpectedElement(EMAIL_FOOTER_RIGHTS).isDisplayed());
        softAssert.assertAll();
    }

    public void navigateToMailinator() throws InterruptedException {
        String emailAddressData = UrlBuilder.getMessage("loginValidEmail.key");
        LOG.info("email substring " + emailAddressData);
        //Redirect to Mailinator
//        getWebDriver().get("https://www.mailinator.com/v3/index.jsp?zone=public&query="+emailAddressData.substring(0, emailAddressData.indexOf("@"))+"#/#inboxpane");
        getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to="+emailAddressData.substring(0, emailAddressData.indexOf("@")));
        Thread.sleep(30000L);
        LOG.info(getCurrentUrl());
        waitClearAndEnterText(By.cssSelector("input#inbox_field"),emailAddressData.substring(0, emailAddressData.indexOf("@")));
        webDriver.findElement(By.cssSelector("button.primary-btn")).click();
        waitForExpectedElement(EMAIL_RECEIVED, 30);
    }

    public void isCancellationEmailReceived() {
        softAssert.assertTrue(receivedCancellationEmail());
        softAssert.assertAll();
    }

    public void verifyOrderCancellationEmail() {
                waitForExpectedElement((EMAIL_HEADER), 10);
                softAssert.assertTrue(isHeaderDisplayed());
                softAssert.assertTrue(isGloLogoDisplayed());
                softAssert.assertTrue(isVeloLogoDisplayed());
                softAssert.assertTrue(waitForExpectedElement(EMAIL_CANCEL_ORDER_BLOCK).isDisplayed());
                softAssert.assertTrue(waitForExpectedElement(EMAIL_USERNAME).getText().contains(UrlBuilder.getMessage("orderEmailUsername.key")));
                softAssert.assertTrue(waitForExpectedElement(EMAIL_ORDER_CANCEL).getText().contains("#" + OrderHistoryPage.orderNumber));
                softAssert.assertTrue(waitForExpectedElement(EMAIL_ORDER_CANCEL_NOTE).isDisplayed());
                softAssert.assertTrue(waitForExpectedElement(EMAIL_ORDER_CANCEL).isDisplayed());
                softAssert.assertEquals(UrlBuilder.getMessage("CancelNote.key"), waitForExpectedElement(EMAIL_ORDER_CANCEL_NOTE).getText());
                softAssert.assertTrue(isGloContactsDisplayed());
                softAssert.assertTrue(isVeloContactsDisplayed());
                footerIsDisplayed();
                softAssert.assertAll();
    }

    public boolean isOrderConfirmationEmailReceived() {
        waitForExpectedElement(EMAIL_LIST, 30);
        email_list = presenceOfAllElementsLocatedBy(EMAIL_LIST);
        for (WebElement list : email_list) {
            if (list.findElement(EMAIL_RECEIVED).getText().equals(UrlBuilder.getMessage("orderConfirmationSubject.key"))) {
                return true;
            }
        }
        return false;
    }

    public void verifyOrderConfirmationEmail() {
                waitForExpectedElement(EMAIL_HEADER, 20);
                softAssert.assertTrue(isHeaderDisplayed());
                softAssert.assertTrue(isGloLogoDisplayed());
                softAssert.assertTrue(isVeloLogoDisplayed());
                softAssert.assertTrue(waitForExpectedElement(EMAIL_BANNER).isDisplayed());
                softAssert.assertTrue(waitForExpectedElement(EMAIL_USERNAME).getText().contains(UrlBuilder.getMessage("orderEmailUsername.key")));
                softAssert.assertTrue(waitForExpectedElement(EMAIL_THANKYOU_NOTE).isDisplayed());
                softAssert.assertEquals(waitForExpectedElement(EMAIL_ORDER_NUMBER).getText(), OrderHistoryPage.orderNumber);
                assertOrderDate();
                softAssert.assertTrue(isGloContactsDisplayed());
                softAssert.assertTrue(isVeloContactsDisplayed());
                softAssert.assertTrue(waitForExpectedElement(EMAIL_ADDRESS_SECTION).isDisplayed());
                footerIsDisplayed();
                verifyOrderDetailsSectionInEmail();
                verifyProductsSectionInEmail();
                assertCodFeeInEmail();
                assertShippingMethodInEmail();
                assertDiscountInEmail();
                verifyOrderDetailsSummarySectionInEmail();
                assertShippingAddressInEmail();
                softAssert.assertAll();
    }

    public void verifyOrderDetailsSectionInEmail() {
        assertTrue(waitForExpectedElement(EMAIL_ORDER_DETAILS).isDisplayed());
    }

    public void verifyOrderDetailsSummarySectionInEmail() {
        softAssert.assertTrue(waitForExpectedElement(EMAIL_ORDER_SUMMARY).isDisplayed());
        String[] grand_total = waitForExpectedElement(EMAIL_GRAND_TOTAL).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
        softAssert.assertEquals(Float.parseFloat(grand_total[0].replaceAll(",", "")),gloItCheckoutPage.actualGrandTotal);
        softAssert.assertAll();
    }

    public void verifyProductsSectionInEmail() {
        productsInEmail.clear();
        assertTrue(waitForExpectedElement(EMAIL_ITEMS_ORDERED).isDisplayed());
        List<WebElement> details = waitForExpectedElements(EMAIL_ITEMS_LIST);
        for (WebElement data : details) {
            String[] qty = data.findElement(EMAIL_PRODUCT_QTY).getText().split(": ");
            productsInEmail.add(Arrays.asList(data.findElement(EMAIL_PRODUCT_NAME).getText(), data.findElement(EMAIL_PRODUCT_PRICE).getText(), qty[1]));
        }
        softAssert.assertEquals(productsInEmail, gloItCheckoutPage.checkoutProducts);
        gloItCheckoutPage.isProductImageDisplayed(EMAIL_PRODUCT_IMG);
        softAssert.assertAll();
    }

    public void assertShippingMethodInEmail() {
        assertTrue(gloItCheckoutPage.selectedDeliverytext[0].contains(waitForExpectedElement(EMAIL_SHIPPING_METHOD).getText()));
    }

    public String email_main_body_colour, email_side_body_colour;

    public void hyperPlusColoursInEmail() {
        email_main_body_colour = waitForExpectedElement(EMAIL_ORDER_MAIN_BODY_COLOR).getText();
        email_side_body_colour = waitForExpectedElement(EMAIL_ORDER_SIDE_BODY_COLOR).getText();
    }

    public void assertHyperPlusColoursInEmail() {
        hyperPlusColoursInEmail();
        assertEquals(HyperPlusPage.main_colour, email_main_body_colour);
        assertEquals(HyperPlusPage.side_colour, email_side_body_colour);
    }

    public void assertCodFeeInEmail() {
        if (gloItCheckoutPage.selectedPaymentText.equals(UrlBuilder.getMessage("cash_payment_text.key"))) {
            String[] cod_fee = waitForExpectedElement(EMAIL_COD_FEE,15).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
            assertEquals(gloItCheckoutPage.delivery_fee_amt, Float.parseFloat(cod_fee[0]));
        } else {
            assertFalse(isElementPresent(EMAIL_COD_FEE));
            LOG.info("Delivery fee is 0");
        }
    }

    public void assertShippingAddressInEmail() {
        String shippAddr = waitForExpectedElement(EMAIL_SHIPPING_ADDRESS).getText();
        email_shipping_addr = shippAddr.replaceAll(" ","").replaceAll("\n", " ");
        assertEquals(email_shipping_addr, gloItCheckoutPage.selectedAddress);
    }

    public void assertDiscountInEmail() {
        if (gloItCheckoutPage.cartDiscountPrice > 0) {
            String[] discount = waitForExpectedElement(EMAIL_DISCOUNT).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
            assertEquals(Float.parseFloat(discount[0].replaceAll(",","")), gloItCheckoutPage.cartDiscountPrice);
        }
    }

    public void assertOrderDate() {
        gloItCheckoutPage.getCurrentDate();
        String[] orderDate = waitForExpectedElement(EMAIL_ORDER_DATE).getText().split(" ");
        assertEquals(gloItCheckoutPage.currentDate, orderDate[0]);
    }

    public void clickOnOrderNumberOnOrderSuccessPage(){
        waitForExpectedElement(By.linkText(OrderHistoryPage.orderNumber), 15);
        clickByElementByQueryJSExecutor(By.linkText(OrderHistoryPage.orderNumber));
        LOG.info("Order number should of been clicked");
    }
}