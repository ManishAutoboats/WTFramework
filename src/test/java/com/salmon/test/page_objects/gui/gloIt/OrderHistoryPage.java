package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderHistoryPage extends PageObject {
    private final GloItProductListPage gloItProductListPage;
    private final GloItLoginPage gloItLoginPage;
    public OrderHistoryPage(GloItProductListPage gloItProductListPage, GloItLoginPage gloItLoginPage){
        this.gloItProductListPage=gloItProductListPage;
        this.gloItLoginPage=gloItLoginPage;
    }
    private static final By BUTTON_AGE_ALLOW = By.cssSelector("#btn-entry-age-allow");
    private static final By MY_ORDERS_TABLE = By.cssSelector("#my-orders-table");
    private static final By ORDER_HISTORY_MSG = By.cssSelector("div.message.info.empty");
    private static final By ORDER_DATE = By.cssSelector("tr:first-child>td.col.date");
    private static final By ORDER_NUMBER_LIST = By.cssSelector("tbody.table-body>tr>td.col.id.desktop-only");
    private static final By NEXT_PAGE_BUTTON = By.cssSelector("div.order-products-toolbar.toolbar.bottom>div>div.pages>ul>li.item.pages-item-next");
    private static final By PAGINATION_LIST = By.cssSelector("div.order-products-toolbar.toolbar.bottom>div.pager>div.pages>ul.items.pages-items>li>a");
    private static final By PREVIOUS_PAGE_BUTTON = By.cssSelector("div.order-products-toolbar.toolbar.bottom>div>div.pages>ul>li:nth-child(1).item.pages-item-previous");
    private static final By SUCCESS_PAGE_ORDER_NUMBER = By.cssSelector("a.order-number");
    public static String viewOrderLink;
    public static String orderNumber, orderStatus, orderPrice, orderName, qty, price, orderDatehist, updatedQty;
    private static final By ORDER_STATUS = By.cssSelector("td.col.status");
    private static final By ORDER_PRICE = By.cssSelector("td.col.total");
    private static final By ORDER_NAME = By.cssSelector("td.col.name");
    private static final By ORDER_LINK = By.cssSelector("div.actions-wrapper>a");
    private static final List<String> FIRST_PAGE_ORDERS = new ArrayList<>();
    private static final List<String> NEXT_PAGE_ORDERS = new ArrayList<>();
    private static final List<String> PREVIOUS_PAGE_ORDERS = new ArrayList<>();
    public static final By loader = By.cssSelector("div.loader");
    private static final By HISTORY_ICON = By.cssSelector("div.historic-orders-info>div.history-icon");
    private static final By HISTORY_BUTTON = By.cssSelector("div.historic-orders-info>div.history-action>a");
    private static final By HISTORIC_TITLE = By.cssSelector("div.historic-orders-info>div.history-content>h6.title");
    private static final By HISTORIC_MESSAGE = By.cssSelector("div.historic-orders-info>div.history-content>span");
    private static final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("div.cart-header-message>a");
    public static String[] HISTORY_GRAND_TOTAL;
    public static final By QUANTITY_PLUS = By.cssSelector("div.quantity>div.bat-quantity>button.bat-quantity-button.bat-quantity-button--plus.button-plus");
    private static final By QUANTITY_MINUS = By.cssSelector("div.quantity>div.bat-quantity>button.bat-quantity-button.bat-quantity-button--minus.button-minus");
    private static final By CART_PRODUCT_QTY = By.cssSelector("div.bat-cartdetail-item>div.bat-cartdetail-details>div>div.quantity-wrapper>div.quantity>div.bat-quantity>input");
    private static final By CART_PRODUCT_PRICE = By.cssSelector("div.bat-cartdetail-item>div.bat-cartdetail-details>div>div.quantity-wrapper>div.quantity>div.prices>span");
    public static final By M_MINI_CART_PROD_QTY_GLOIT = By.cssSelector("div.minicart-wrapper > a.action.showcart > span.counter.qty > span.counter-number");
    public static float updatedPrice;
    private static final By DELETE_PRODUCT_ICON = By.cssSelector("div.remove>button");
    private static final By BASKET_ITEMS = By.cssSelector("div.bat-cartdetail-item");
    public static final By BASKET_ICON_DNU = By.cssSelector("a.bat-header-icon--cart.js-cart-icon");
    public static final By BASKET_ICON = By.cssSelector("a[href='/basket']");
    public static final By BASKET_LOADER = By.cssSelector("div.bat-loading-mask");
    public static final By BASKET_ICON_COUNT = By.cssSelector("a.bat-header-icon--cart.js-cart-icon>span:nth-child(2)>span");
    private static final By HISTORIC_ORDERS_LINK = By.cssSelector("li.item.historic-orders>a");
    private static final By LNK_ORDER_VIEW_JP = By.cssSelector("td.col.actions>div>a.view.action-orders");
    public static final By PERSON_ICON = By.cssSelector("a.bat-header-icon--account.js-account-icon");
    private static final By MY_ORDERS = By.cssSelector("div.bat-cta.bat-cta-list--vertical>a:nth-child(4)");
    private static final By OCR_POPUP = By.cssSelector("div.at-ocr-popup-align");
    private static final By CLOSE_OCR_POPUP = By.cssSelector("div.at-ocr-close-btn");

    public void tryClickIAmOver18JP() {
        waitForExpectedElement(BUTTON_AGE_ALLOW);
        clickByElementByQueryJSExecutor(BUTTON_AGE_ALLOW);
    }

    public void assertOrderDateOnOrderHistory() {
        GloItCheckoutPage.getCurrentDate();
        String orderdate = waitForExpectedElement(ORDER_DATE).getText();
        Assert.assertEquals(orderdate, GloItCheckoutPage.currentDate);
    }

    public void navigateToNextPage() {
        List<WebElement> page1Orders = presenceOfAllElementsLocatedBy(ORDER_NUMBER_LIST);
        waitForExpectedElement(ORDER_NUMBER_LIST);
        for (WebElement ele : page1Orders) {
            FIRST_PAGE_ORDERS.add(ele.getText());
        }
        List<WebElement> pagination = presenceOfAllElementsLocatedBy(PAGINATION_LIST);
        if (pagination.size() > 0) {
            waitForExpectedElement(NEXT_PAGE_BUTTON).click();
            List<WebElement> pageOrderList = presenceOfAllElementsLocatedBy(ORDER_NUMBER_LIST);
            for (WebElement ele : pageOrderList) {
                NEXT_PAGE_ORDERS.add(ele.getText());
            }
            Assert.assertNotEquals(FIRST_PAGE_ORDERS, NEXT_PAGE_ORDERS);
        } else {
            LOG.info("Pagination does not exists");
        }
    }

    public void navigateToPreviousPage() {
        List<WebElement> pagination = presenceOfAllElementsLocatedBy(PAGINATION_LIST);
        if (pagination.size() > 0) {
            waitForExpectedElement(PREVIOUS_PAGE_BUTTON).click();
        } else {
            LOG.info("Pagination does not exists");
        }
    }

    public void previousOrderlistUpdated() {
        List<WebElement> pageOrderList = waitForExpectedElements(ORDER_NUMBER_LIST);
        for (WebElement ele : pageOrderList) {
            PREVIOUS_PAGE_ORDERS.add(ele.getText());
        }
        Assert.assertNotEquals(PREVIOUS_PAGE_BUTTON, NEXT_PAGE_BUTTON);
    }

    public boolean loopThurOrderTableContentsAndReturnifOrderLinksPresent() {
        boolean viewOrderLinkPresent = false;
        for (WebElement rowData : getTableRows(MY_ORDERS_TABLE)) {
            if (rowData.getText().contains(UrlBuilder.getMessage("OrderDetails.key"))) {
                viewOrderLinkPresent = true;
            }
        }
        return viewOrderLinkPresent;
    }

    public Boolean assertMyOrderTableHeadingsAreCorrect() {
        int matchCount = 0;
        ArrayList<String> MyOrderTableHeadings;
        MyOrderTableHeadings = new ArrayList<>(Arrays.asList(UrlBuilder.getMessage("orderNumber.key"), UrlBuilder.getMessage("orderDate.key"), UrlBuilder.getMessage("customerName.key"), UrlBuilder.getMessage("orderPrice.key"), UrlBuilder.getMessage("orderStatus.key")));
        for (WebElement title : getTableHeaders(MY_ORDERS_TABLE)) {
            if (MyOrderTableHeadings.contains(title.getText())) {
                matchCount++;
            }
        }
        return matchCount == 5;
    }

    public Boolean assertHistoricOrdersTableHeadingsAreCorrect() {
        int matchCount = 0;
        ArrayList<String> MyOrderTableHeadings;
        MyOrderTableHeadings = new ArrayList<>(Arrays.asList(UrlBuilder.getMessage("orderDate.key"), UrlBuilder.getMessage("orderNumber.key"), UrlBuilder.getMessage("historicOrderPrice.key")));
        for (WebElement title : getTableHeaders(MY_ORDERS_TABLE)) {
            if (MyOrderTableHeadings.contains(title.getText())) {
                matchCount++;
            }
        }
        return matchCount == 3;
    }

    public String noHistoryOrders() {
        return waitForExpectedElement(ORDER_HISTORY_MSG).getText();
    }

    public boolean isOrderDateDisplayed() {
        return waitForExpectedElement(ORDER_DATE).isDisplayed();
    }

    public void closeOcrPopupOnBasket(){
        try {
            if (waitForExpectedElement(OCR_POPUP).isDisplayed()) {
                try {
                    waitForExpectedElement(CLOSE_OCR_POPUP);
                    clickByElementByQueryJSExecutor(CLOSE_OCR_POPUP);
                } catch (Exception e) {
                    clickByElement(CLOSE_OCR_POPUP);
                }
            }
        } catch (Exception e) {
            LOG.info("OCR popup is not displayed");
        }
    }

    public void emptyBasket() {
        int productItemCount;
        waitForElementToDisappear(BASKET_LOADER,5);
        try{
        productItemCount = waitForExpectedElements(BASKET_ITEMS).size();
        }catch(TimeoutException te){
            productItemCount = 0;
        }
        if(productItemCount > 0){
            while (productItemCount > 0) {
                retryingFindClick(DELETE_PRODUCT_ICON);
                waitForElementToDisappear(BASKET_LOADER,20);
                try {
                    productItemCount = waitForExpectedElements(BASKET_ITEMS).size();
                }catch(TimeoutException te){
                    productItemCount--;
                }
            }
            Assert.assertEquals(productItemCount, 0);
            LOG.info("Basket is empty");
            closeOcrPopupOnBasket();
        }
    }

    public void navigateToCart() {
        try {
            waitForExpectedElement(BASKET_ICON, 30);
            clickByElementByQueryJSExecutor(BASKET_ICON);
        }catch(Exception e){
                refreshBrowser();
                waitForExpectedElement(BASKET_ICON,30);
                clickByElement(BASKET_ICON);
        }
    }

    public String getGeneratedOrderNumber() {
        IsPageLoaded.waitAllRequest();
        orderNumber = waitForExpectedElement(SUCCESS_PAGE_ORDER_NUMBER, 60).getText();
        return orderNumber;
    }

    public void verifyOrderInOrderHistoryPage() {
        GloItCheckoutPage.getCurrentDate();
        waitForExpectedElement(MY_ORDERS_TABLE,20);
        for (WebElement rowData : getTableRows(MY_ORDERS_TABLE)) {
            if (rowData.getText().contains(orderNumber)) {
                orderStatus = rowData.findElement(ORDER_STATUS).getText();
                orderPrice = rowData.findElement(ORDER_PRICE).getText();
                orderName = rowData.findElement(ORDER_NAME).getText();
                orderDatehist = rowData.findElement(ORDER_DATE).getText();
                HISTORY_GRAND_TOTAL = orderPrice.split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
                viewOrderLink = rowData.findElement(ORDER_LINK).getAttribute("href");
                Assert.assertEquals(orderDatehist, GloItCheckoutPage.currentDate);
                Assert.assertEquals(Float.parseFloat(HISTORY_GRAND_TOTAL[0].replaceAll(",", "")), GloItCheckoutPage.actualGrandTotal);
            }
        }
    }

    public void clickViewOrderLink() {
        waitForExpectedElement(ORDER_LINK);
        for (WebElement rowData : getTableRows(MY_ORDERS_TABLE)) {
            if (rowData.getText().contains(orderNumber)) {
                rowData.findElement(ORDER_LINK).click();
                break;
            }
        }
    }

    public boolean historyIconIsPresent() {
        try {
            return waitForExpectedElement(HISTORY_ICON, 30).isDisplayed();
        } catch (Exception e) {
            refreshBrowser();
            return waitForExpectedElement(HISTORY_ICON, 20).isDisplayed();
        }
    }

    public boolean historyButtonIsPresent() {
        return waitForExpectedElement(HISTORY_BUTTON, 20).isDisplayed();
    }

    public void clickOnHistoricOrders() {
        waitForItemToBeClickableAndClick(HISTORY_BUTTON, 10);
    }

    public void verifyHistoricOrdersTitle() {
        Assert.assertEquals(UrlBuilder.getMessage("HistoricBlockTitle.key"), waitForExpectedElement(HISTORIC_TITLE).getText());
    }

    public boolean isHistoricOrdersMessageIsDisplayed() {
        return waitForExpectedElement(HISTORIC_MESSAGE, 10).isDisplayed();
    }

    public void verifyHistoricOrdersMessage() {
        Assert.assertEquals(UrlBuilder.getMessage("HistoricOrdersMessage.key"), waitForExpectedElement(HISTORIC_MESSAGE).getText());
    }

    public void updateBasketByIncreasingQty() {
        waitForExpectedElement(QUANTITY_PLUS).click();
        gloItProductListPage.waitForLoaderToDisappear();
        getUpdatedProductPrice();
        getUpdatedProductQuantity();
    }

    public void updateBasketByDecreasingQty() {
        waitForExpectedElement(QUANTITY_MINUS);
        if (waitForExpectedElement(QUANTITY_MINUS).isEnabled()) {
            waitForExpectedElement(QUANTITY_MINUS).click();
            gloItProductListPage.waitForLoaderToDisappear();
            getUpdatedProductPrice();
            getUpdatedProductQuantity();
        } else {
            LOG.info("Basket qty is already minimum");
        }
    }

    public void getUpdatedProductPrice() {
        String prod_cart_qty = waitForExpectedElement(CART_PRODUCT_QTY).getAttribute("value");
        String[] prod_cart_price = waitForExpectedElement(CART_PRODUCT_PRICE).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
        int sum = Integer.parseInt(prod_cart_price[0]) * Integer.parseInt(prod_cart_qty);
        updatedPrice = Float.parseFloat(String.valueOf(sum));
    }

    public void getUpdatedProductQuantity() {
        updatedQty = waitForExpectedElement(CART_PRODUCT_QTY).getAttribute("value");
    }

    public void clickHistoricOrdersBreadcrumb() {
        clickByElementByQueryJSExecutor(HISTORIC_ORDERS_LINK);
    }

    public void usersClicksOnTheViewOrderLinkForHistoricOrder() {
        waitForExpectedElement(LNK_ORDER_VIEW_JP);
        clickFirstElementByQueryJSExecutor(LNK_ORDER_VIEW_JP);
    }

    public void clickOnPersonIcon() {
        try {
            clickByElementByQueryJSExecutor(PERSON_ICON);
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(PERSON_ICON);
        }
    }

    public void clickOnMyOrders() {
        try {
            //waitForExpectedElement(MY_ORDERS,20);
            clickByElementByQueryJSExecutor(MY_ORDERS);
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(MY_ORDERS);
        }
    }
}