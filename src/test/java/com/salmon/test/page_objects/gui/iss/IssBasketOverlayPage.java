package com.salmon.test.page_objects.gui.iss;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.iss.InStoreSubscriptionServices;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class IssBasketOverlayPage extends PageObject {
    // basket overlay
    // class names generated by react :(
    public static final By BASKET_OVERLAY = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._1AN6d1WwxLkWvKG1pyaoCx");
    public static final By BASKET_SUBTOTAL = By.cssSelector("#app > main div._25GhrprYs2ACUNfPSx3WQ3");
    public static final By BASKET_TIER_DISCOUNT = By.cssSelector("#app > main div._2YDQNa_Si5YIVMlNMFhMKd");
    public static final By BASKET_TOTAL = By.cssSelector("#app > main div._33SV5TAMkPXqLYN2aNbtcu");
    public static final By ACTIVE_SET_UP_SUBSCRIPTION_BUTTON = By.cssSelector("#app > main div.WY0asLCOxmS3jcGK1hBe8 > button[class*=btn]:not([class*='disabled'])");
    public static final By INACTIVE_SET_UP_SUBSCRIPTION_BUTTON = By.cssSelector("#app > main div.WY0asLCOxmS3jcGK1hBe8 > button.btn.disabled");
    public static final By REMOVE_ITEM_ICON = By.cssSelector("#app > main  div._3TYTl7eiBv16o8zcbl_AOs > div span.icon.icon__close");
    public static final By BASKET_ITEM = By.cssSelector("#app > main div._3TYTl7eiBv16o8zcbl_AOs > div > div");
    public static final By BASKET_ITEM_COUNT = By.cssSelector("#app > main div.SGnJSsg_8JES0R020Rh2R > div > span");
    public static final By BASKET_ITEM_INCREASE_QTY = By.cssSelector("span.plus-icon");
    public static final By BASKET_ITEM_QTY = By.cssSelector("#app > main  div.SGnJSsg_8JES0R020Rh2R > div > span");
    public static final By BASKET_ITEM_DECREASE_QTY = By.cssSelector("span.minus-icon");
    public static final By BASKET_SEND_TO_CUSTOMER_BUTTON = By.cssSelector("#app > main > div div.zIqWtxgsWC92nrqQogYvy > button:nth-child(2)");
    public static final By CLOSE_BASKET = By.cssSelector("#app > main > div._3v-sbJHHUzqYijADRtUbub._1AN6d1WwxLkWvKG1pyaoCx > div._2sVga5wq5yA76aYAwWIFgE > span");
    public static final By BASKET_ITEM_NAME = By.cssSelector("#app > main  div.GBPQkyQvQXqLfU0aOI52m > h4");
    public static final By BASKET_ITEM_QUANTITY = By.cssSelector("#app > main  div.SGnJSsg_8JES0R020Rh2R > div > span");
    public static final By BASKET_ITEM_STRENGTH = By.cssSelector("#app > main div._2FFIUJHplcPdSldmYNGf8O > span");
    public static final By BASKET_MIN_QUANTITY_MESSAGE = By.cssSelector("span._27eT8k-jSaE_iWYKn5OP2r");

    private ScenarioContext scenarioContext;
    private InStoreSubscriptionServices inStoreSubscriptionServices;

    public IssBasketOverlayPage(ScenarioContext scenarioContext, InStoreSubscriptionServices inStoreSubscriptionServices) {
        this.scenarioContext = scenarioContext;
        this.inStoreSubscriptionServices = inStoreSubscriptionServices;
    }

    public boolean overlayIsDisplayed() {
        return waitForExpectedElement(BASKET_OVERLAY).isDisplayed();
    }

    public boolean basketOverlayContains(String detail) {
        By detailSelector = null;
        switch (detail) {
            case "subtotal":
                detailSelector = BASKET_SUBTOTAL;
                break;
            case "tier discount":
                detailSelector = BASKET_TIER_DISCOUNT;
                break;
            case "total":
                detailSelector = BASKET_TOTAL;
                break;
            case "set up subscription button":
                detailSelector = ACTIVE_SET_UP_SUBSCRIPTION_BUTTON;
                break;
            default:
                assertThat(true).as("ERROR: invalid basket detail "+detail+" supplied").isFalse();
        }
        return waitForExpectedElement(detailSelector).isDisplayed();
    }
    public int basketCount() throws InterruptedException {
        waitForElementToAppearAndDisappear(LOADER, 3, 3);
        return webDriver.findElements(BASKET_ITEM).size();
    }
    public void clickRemoveIcon() {
        List<WebElement> items = webDriver.findElements(BASKET_ITEM);
        items.get(0).findElement(REMOVE_ITEM_ICON).click();
        waitForElementToAppearAndDisappear(LOADER,5,5);
    }

    public void increaseItemQty(int by) {
        waitForExpectedElement(BASKET_ITEM);
        WebElement itemToBeIncreased = webDriver.findElements(BASKET_ITEM).get(0);
        for (int click=0; click<by; click++) {
            itemToBeIncreased.findElement(BASKET_ITEM_INCREASE_QTY).click();
            waitForElementToAppearAndDisappear(LOADER,3,3);
        }
    }

    public void increaseItemQtyTo(int targetQty) {
        Map<String,String> basketContents = (Map<String, String>) scenarioContext.getContext(Context.BASKET_CONTENTS_MAP);
        int currentQty;
        try {
            currentQty = Integer.parseInt(waitForExpectedElement(BASKET_ITEM_QUANTITY).getText());
        } catch (Exception e) {
            currentQty = 0;
        }
        waitForExpectedElement(BASKET_ITEM);
        WebElement itemToBeIncreased = webDriver.findElements(BASKET_ITEM).get(0);
        while (currentQty < targetQty) {
            itemToBeIncreased.findElement(BASKET_ITEM_INCREASE_QTY).click();
            waitForElementToAppearAndDisappear(LOADER,3,3);
            currentQty = Integer.parseInt(waitForExpectedElement(BASKET_ITEM_QUANTITY).getText());
        }
//        basketContents = basketContents.replace("=1", "="+String.valueOf(targetQty));
        scenarioContext.setContext(Context.BASKET_CONTENTS_MAP, basketContents);
    }

    public void decreaseItemQty(int by) throws InterruptedException {
        waitForExpectedElement(BASKET_ITEM);
        WebElement itemToBeReduced = webDriver.findElements(BASKET_ITEM).get(0);
        for (int click=0; click<by; click++) {
            itemToBeReduced.findElement(BASKET_ITEM_DECREASE_QTY).click();
            waitForElementToAppearAndDisappear(LOADER,3,3);
        }
    }

    public int getItemQty() {
        waitForElementToAppearAndDisappear(LOADER, 3, 3);
        waitForExpectedElement(BASKET_ITEM);
        WebElement itemToBeCounted = webDriver.findElements(BASKET_ITEM).get(0);
        return Integer.parseInt(itemToBeCounted.findElement(BASKET_ITEM_COUNT).getText());
    }

    public void clickSetUpSubscription() {
        waitForExpectedElement(ACTIVE_SET_UP_SUBSCRIPTION_BUTTON, 30);
        waitForExpectedElement(ACTIVE_SET_UP_SUBSCRIPTION_BUTTON).click();
        waitForElementToAppearAndDisappear(LOADER,5,5);
    }

    public String getHash() {
        String[] eventCalls;
        String scriptToExecute = "var network = performance.getEntries() || {}; return network;";
        try {
            eventCalls =((JavascriptExecutor)webDriver).executeScript(scriptToExecute).toString().split("identifier/");
        } catch (Exception e) {
            waitForElementToAppearAndDisappear(LOADER,3,3);
            eventCalls = ((JavascriptExecutor)webDriver).executeScript(scriptToExecute).toString().split("identifier/");
        }
        return eventCalls[eventCalls.length -1].split(",")[0];
    }

    public void clickSendToCustomer() {
        waitForExpectedElement(BASKET_SEND_TO_CUSTOMER_BUTTON).click();
    }

    public int getBasketQty() {
        int count = 0;
        waitForElementToAppearAndDisappear(LOADER, 3, 3);
        List<WebElement> quantities = webDriver.findElements(BASKET_ITEM_COUNT);
        for (WebElement quantity: quantities) {
            count = count + Integer.parseInt(quantity.getText());
        }
        return count;
    }

    public void closeBasketOverlay() {
        waitForExpectedElement(CLOSE_BASKET).click();
    }

    public HashMap<String, String> getBasketContents() {
        String name;
        String quantity;
        String strength;
        HashMap<String, String> result = new HashMap<>();
        List<WebElement> basketItems = waitForExpectedElements(BASKET_ITEM);
        for (WebElement basketItem: basketItems) {
            name = basketItem.findElement(BASKET_ITEM_NAME).getText();
            quantity = basketItem.findElement(BASKET_ITEM_QUANTITY).getText();
            strength = basketItem.findElement(BASKET_ITEM_STRENGTH).getText();
            result.put(name, quantity+","+strength);
        }
        LOG.info("Displayed basket contents = " + result);
        return result;
    }

    public boolean areAllAddedItemsDisplayedInBasket() {
        HashMap<String, String> itemsAdded = (HashMap<String, String>) scenarioContext.getContext(Context.BASKET_CONTENTS_MAP);
        HashMap<String, String> itemsDisplayed = getBasketContents();
        return itemsAdded.equals(itemsDisplayed);
    }

    public boolean minQtyMessageDisplayed(String minQty) {
        String message;
        try {
            message = waitForExpectedElement(BASKET_MIN_QUANTITY_MESSAGE).getText();
            return message.contains(UrlBuilder.getMessage("minQtyMessage").replace("%QTY%",minQty));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateSetUpSubscriptionButtonIs(String expectedState) {
        By expectedButton;
        if (expectedState.equalsIgnoreCase("enabled")) {
            expectedButton = ACTIVE_SET_UP_SUBSCRIPTION_BUTTON;
        } else {
            expectedButton = INACTIVE_SET_UP_SUBSCRIPTION_BUTTON;
        }
        try {
            waitForExpectedElement(expectedButton);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void addUntilQuantityReached(int targetTotal) {
        int currentTotal = getBasketQty();
        while (currentTotal < targetTotal) {
            waitForExpectedElement(BASKET_ITEM_INCREASE_QTY);
            clickUsingJS(BASKET_ITEM_INCREASE_QTY);
            currentTotal = getBasketQty();
        }
    }
}