package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderViewPage extends PageObject {

    private static final By MY_ORDERS_TABLE = By.cssSelector("#my-orders-table");
    private static final By SKU_OR_ITEM_NUMBER_CSS = By.cssSelector("tbody > tr > td.col.sku");
    private static final By ITEM_PRICE_CSS = By.cssSelector("tbody > tr > td.col.subtotal");
    private static final By ITEM_QUANTITY_CSS = By.cssSelector("tbody > tr > td.col.qty > .items-qty > .item > .content");
    private static final By TOTAL_PRICE_CSS = By.cssSelector("tr.grand_total_incl > td.amount");
    private static final By DISCOUNT_PRICE = By.cssSelector("tr.discount > td.amount");
    private static final By ORDER_VIEW_DELIVERY_ADDRESS_CSS = By.cssSelector(".box-order-shipping-address .box-content address");
    private static final By ENGRAVING_DETAILS = By.cssSelector(".personalise-wrapping");


    public void assertFreeTrialProductsInOrderView(int numberOfFreeProducts, List<String> expectedSkus) {
        List<WebElement> tbodyList = waitForExpectedElement(MY_ORDERS_TABLE).findElements(By.tagName("tbody"));
        assertThat(tbodyList.size() - 1).isEqualTo(numberOfFreeProducts);

        List<WebElement> actualSkuList = getWebDriver().findElements(SKU_OR_ITEM_NUMBER_CSS);
        actualSkuList.remove(0);

        // assert free product added to the order are same as expected sku numbers
        List<String> freeProductsSkus = actualSkuList.stream().map(WebElement::getText).collect(Collectors.toList());
        assertThat(freeProductsSkus).hasSameElementsAs(expectedSkus);

        // assert quantity of the free product should be 1 for each sku
        getWebDriver().findElements(ITEM_QUANTITY_CSS).stream().map(WebElement::getText)
                .forEach(s -> assertThat(s.equals("1")).isTrue());

        // assert price added of the free product should be 0.00 for each sku
        List<WebElement> itemPriceList = getWebDriver().findElements(ITEM_PRICE_CSS);
        WebElement purchasedItem = itemPriceList.remove(0);
        itemPriceList.stream().map(WebElement::getText)
                .forEach(s -> {
                    String freeItemPrice = s.split(" ")[0].replace(",", ".");
                    assertThat(Double.parseDouble(freeItemPrice)).isEqualTo(0.00);
                });

        // assert total price is equal to the price of the purchased Item only
        assertThat(waitForExpectedElement(TOTAL_PRICE_CSS).getText()).isEqualTo(purchasedItem.getText());
    }

    public String getDeliveryAddressInOrderView() {
        return waitForExpectedElement(ORDER_VIEW_DELIVERY_ADDRESS_CSS).getText();
    }

    public String getTotalPrice() {
        return waitForExpectedElement(TOTAL_PRICE_CSS).getText();
    }

    public String getSubTotalOrItemPrice() {
        return waitForExpectedElement(ITEM_PRICE_CSS).getText();
    }

    public String getDiscountPrice() {
        return isElementPresent(DISCOUNT_PRICE)&&waitForExpectedElements(DISCOUNT_PRICE).size() > 0
                ? waitForExpectedElement(DISCOUNT_PRICE).getText().split("-")[1]
                : "0.00";
    }

    public void assertEngravingDetailsOnOrderViewPage(){
        assertThat(waitForExpectedElement(ENGRAVING_DETAILS).isDisplayed()).as("Engraving details are not present").isTrue();
    }
}
