package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.MailinatorPage;
import com.salmon.test.page_objects.gui.gloIt.GloItLoginPage;
import com.salmon.test.page_objects.gui.gloIt.GloItOrderSuccessPage;
import com.salmon.test.page_objects.gui.gloIt.OrderDetailsPage;
import com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.util.List;

public class OrderDetailsSteps extends PageObject {

    private final OrderDetailsPage orderDetailsPage;
    private final OrderHistoryPage OdrHistPage;
    private final MailinatorPage mailinatorPage;
    private final SoftAssert softAssert;
    private final GloItLoginPage gloItLoginPage;

    public OrderDetailsSteps(OrderDetailsPage orderDetailsPage, OrderHistoryPage OdrHistPage, SoftAssert softAssert, MailinatorPage mailinatorPage, GloItLoginPage gloItLoginPage) {
        this.orderDetailsPage = orderDetailsPage;
        this.OdrHistPage = OdrHistPage;
        this.softAssert =softAssert;
        this.mailinatorPage=mailinatorPage;
        this.gloItLoginPage=gloItLoginPage;
    }


    @And("^get generated Order Number$")
    public void getGeneratedOrderNumber() throws Throwable {
        OdrHistPage.getGeneratedOrderNumber();
    }

    @And("^user can navigate back to the order list via breadcrumb$")
    public void userCanNavigateBackToTheOrderListViaBreadcrumb() {
        orderDetailsPage.clickOrderHistoryBreadcrumb();
    }

    @And("^assert order details is displayed$")
    public void assertOrderDetailsIsDisplayed() {
        softAssert.assertTrue(orderDetailsPage.isOrderInformationDisplayed());
        softAssert.assertTrue(orderDetailsPage.orderSummaryBlockDisplayed());
        softAssert.assertTrue(orderDetailsPage.isProductsTableDisplayed());
        softAssert.assertNotNull(OrderDetailsPage.ORDER_NUMBER);
        softAssert.assertNotNull(OrderDetailsPage.ORDER_DATE);
        softAssert.assertNotNull(OrderDetailsPage.ORDER_STATUS);
        softAssert.assertAll();
    }

    @And("^users clicks on the View Order link for generated Order Number$")
    public void usersClicksOnTheViewOrderLinkForGeneratedOrderNumber() {
        OdrHistPage.clickViewOrderLink();
    }

    @And("^assert header '(.*)' is displayed$")
    public void assertHeaderOrderDetailsKeyIsDisplayed(String strExpectedText) {
        //orderDetailsPage.waitForExpectedElement(OrderDetailsPage.ORDER_DETAILS_TEXT,20);
        IsPageLoaded.waitAllRequest();
        String strEmptyBasketText = waitForExpectedElement(OrderDetailsPage.ORDER_DETAILS_TEXT).getText();
        assertTrueWithCustomError(strEmptyBasketText, UrlBuilder.getMessage(strExpectedText));
    }

    @And("^verify cancel button is displayed with note$")
    public void verifyCancelButtonIsDisplayedWithNote() {
        softAssert.assertTrue(orderDetailsPage.cancelButtonIsDisplayed());
        softAssert.assertTrue(orderDetailsPage.cancelNoteIsDisplayed());
        softAssert.assertAll();
    }

    @And("^click on Cancel order button and confirm$")
    public void clickOnCancelOrderButtonAndConfirm() {
        orderDetailsPage.clickOnCancelOrder();
        orderDetailsPage.confirmCancelOrder();
    }

    @And("^verify order is canceled$")
    public void verifyOrderIsCanceled() {
        orderDetailsPage.verifyOrderIsCanceled();
    }

    @And("^click on Cancel order button and close$")
    public void clickOnCancelOrderButtonAndClose() {
        orderDetailsPage.clickOnCancelOrder();
        orderDetailsPage.doNotCancelOrder();
    }

    @And("^assert COD fee is displayed$")
    public void assertCODFeeIsDisplayed() {
        Assert.assertTrue(orderDetailsPage.isCODfeedisplayed());
        orderDetailsPage.assertCODeliveryFee();
    }

    @And("^assert order details with history page$")
    public void assertOrderDetailsWithHistoryPage() {
        orderDetailsPage.assertOrderDetailsWithOrderHistory();

    }

    @And("^assert payment and shipping method$")
    public void assertPaymentAndShippingMethod() throws ParseException {
        orderDetailsPage.assertPaymentMethod();
        orderDetailsPage.assertShippingMethod();
        orderDetailsPage.assertShippingAddressOnOrderDetails();
    }

    @And("^assert products section$")
    public void assertProductsSection() {
        orderDetailsPage.assertProductsOnOrderDetails();
        orderDetailsPage.assertNoOfProductsInOrderDetails();
    }

    @And("^verify order confirmation email is received$")
    public void verifyOrderConfirmationEmailIsReceived() throws InterruptedException {
        Thread.sleep(40000L);
        List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(gloItLoginPage.username);
        mailinatorPage.clickOnEmailWithSubject(receivedEmailElements,UrlBuilder.getMessage("orderConfirmationSubject.key"));
        mailinatorPage.switchToMessageBodyIframe();
    }

    @And("^verify order cancelled email is received$")
    public void verifyOrderCancelledEmailIsReceived() throws InterruptedException {
        Thread.sleep(10000L);
        List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(gloItLoginPage.username);
        mailinatorPage.clickOnEmailWithSubject(receivedEmailElements,UrlBuilder.getMessage("cancellationSubject.key"));
        mailinatorPage.switchToMessageBodyIframe();
        orderDetailsPage.verifyOrderCancellationEmail();
    }

    @And("^assert order summary section$")
    public void assertOrderSummarySection() {
        orderDetailsPage.assertOrderSummarySection();
    }

    @And("^verify order confirmation email received$")
    public void verifyOrderConfirmationEmailReceived() {
        orderDetailsPage.verifyOrderConfirmationEmail();
    }

    @And("^verify order cancellation email$")
    public void verifyOrderCancellationEmail() {
        orderDetailsPage.verifyOrderCancellationEmail();
    }

    @And("^user clicks on manage order link$")
    public void userClicksOnManageOrderLink() {
        waitForExpectedElement(GloItOrderSuccessPage.MANAGE_ORDERS_LINK,5).click();
    }

    @And("^click on order number to view order$")
    public void clickOnOrderNumberToViewOrder() {
        orderDetailsPage.clickOnOrderNumberOnOrderSuccessPage();
    }

    @And("^user click on mini cart wrapper button from checkout on mobile$")
    public void userClickOnMinicartWrapperButton() {
        int numberOfChecks = 2;
        while(!isElementPresent(orderDetailsPage.M_ORDER_SUMMARY_CLOSE_BTN_IT, 5) && numberOfChecks > 0) {
                refreshBrowser();
                orderDetailsPage.waitForAjaxElementNotToBePresent(getWebDriver(),20);
                clickUsingJS(orderDetailsPage.M_MINI_CART_BUTTON_IT);
                numberOfChecks--;
        }
    }
}
