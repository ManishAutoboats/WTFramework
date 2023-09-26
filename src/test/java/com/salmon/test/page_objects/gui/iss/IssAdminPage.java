package com.salmon.test.page_objects.gui.iss;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class IssAdminPage extends PageObject {

    private static final By STAFF_ID_LABEL = By.cssSelector("div#container div:nth-child(3) > div.admin__field-label > label > span");
    private static final By FIRST_OUTLET_ID_VALUE = By.cssSelector("#container > div > div.admin__data-grid-wrap > table > tbody > tr:nth-child(2) > td:nth-child(3) > div");
    private static final By ADD_NEW_STORE_LINK = By.cssSelector("button[title='Add new Store']");
    private static final By OUTLET_ID_FIELD = By.cssSelector("input[name='outlet_id']");
    private static final By AREA_FIELD = By.cssSelector("input[name='area']");
    private static final By SITE_FIELD = By.cssSelector("input[name='site']");
    private static final By STORE_VIEWS = By.name("storeviews");
    private static final By SAVE_STORE_BUTTON = By.cssSelector("button[title='Save Store']");
    private static final By ERROR_MESSAGE_BLOCK = By.cssSelector("div.message-error");
    private static final By SUCCESS_MESSAGE_BLOCK = By.cssSelector("div.message-success");
    private static final By FILTERS_BUTTON = By.cssSelector("#container > div > div.admin__data-grid-header > div:nth-child(1) > div.data-grid-filters-actions-wrap > div > button");
    private static final By APPLY_FILTERS_BUTTON = By.cssSelector("#container > div > div.admin__data-grid-header > div:nth-child(1) > div.admin__data-grid-filters-wrap._show > div > div > button.action-secondary");
    private static final By SELECT = By.cssSelector("button.action-select");
    private static final By ITEM_DELETE = By.cssSelector("#container > div > div.admin__data-grid-wrap > table > tbody > tr.data-row > td.data-grid-actions-cell > div > ul > li:nth-child(2) > a");
    private static final By CONFIRM_DELETE = By.cssSelector("button.action-primary");
    private static final By CLEAR_FILTER = By.cssSelector("button.action-clear");
    private static final By FILTER_RESULT_ROW = By.cssSelector("tr.data-row");

    public boolean staffIdLabelDisplayed() {
        return UrlBuilder.getMessage("staffIdLabel").equalsIgnoreCase(waitForExpectedElement(STAFF_ID_LABEL).getText());
    }

    public String getExistingOutletId() {
        String result;
        try {
            result = waitForExpectedElement(FIRST_OUTLET_ID_VALUE).getText();
        } catch (Exception e) {
            waitForExpectedElement(CLEAR_FILTER).click();
            result = waitForExpectedElement(FIRST_OUTLET_ID_VALUE).getText();
        }
        return result;
    }

    public void addNewStore(String outletId) {
        waitForExpectedElement(ADD_NEW_STORE_LINK).click();
        waitForExpectedElement(OUTLET_ID_FIELD);
        webDriver.findElement(OUTLET_ID_FIELD).sendKeys(outletId);
        webDriver.findElement(AREA_FIELD).sendKeys("Area");
        webDriver.findElement(SITE_FIELD).sendKeys("Site");
        Select storeView = new Select(webDriver.findElement(STORE_VIEWS));
        storeView.selectByIndex(0);
        waitForExpectedElement(SAVE_STORE_BUTTON).click();
    }

    public boolean outletIdAlreadyExistsErrorDisplayed() {
        String expectedErrorMessage = UrlBuilder.getMessage("outletIdAlreadyExistsError");
        String actualErrorMessage = waitForExpectedElement(ERROR_MESSAGE_BLOCK).getText();
        return expectedErrorMessage.equalsIgnoreCase(actualErrorMessage);
    }

    private void searchForOutletId(String outletId) {
        waitForElementToAppearAndDisappear(LOADER,5,5);
        waitForExpectedElement(FILTERS_BUTTON).click();
        webDriver.findElement(OUTLET_ID_FIELD).clear();
        webDriver.findElement(OUTLET_ID_FIELD).sendKeys(outletId);
        waitForExpectedElement(APPLY_FILTERS_BUTTON).click();
    }

    public void correctOutletIdToUniqueValue(String uniqueOutletId) {
        webDriver.findElement(OUTLET_ID_FIELD).clear();
        webDriver.findElement(OUTLET_ID_FIELD).sendKeys(uniqueOutletId);
        waitForElementToAppearAndDisappear(LOADER,3,3);
        waitForExpectedElement(SAVE_STORE_BUTTON, 5).click();
        waitForElementToAppearAndDisappear(LOADER,3,3);
    }

    public void saveStore() {
        waitForExpectedElement(SAVE_STORE_BUTTON).click();
    }

    public boolean storeSavedSuccessMessageDisplayed() {
        String expectedsuccessMessage = UrlBuilder.getMessage("savedTheStoreSuccess");
        String actualSuccessMessage = waitForExpectedElement(SUCCESS_MESSAGE_BLOCK).getText();
        return expectedsuccessMessage.equalsIgnoreCase(actualSuccessMessage);
    }

    public void deleteOutletId(String outletId) {
        waitForElementToAppearAndDisappear(LOADER,3,3);
        searchForOutletId(outletId);
        clickByElementByQueryJSExecutor(SELECT);
        clickByElementByQueryJSExecutor(ITEM_DELETE);
        waitForExpectedElement(CONFIRM_DELETE).click();
    }

    private boolean outletIdExists(String outletId) {
        try {
            waitForExpectedElement(FILTER_RESULT_ROW);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void ensureOutletIdIsUnique(String outletId) {
        searchForOutletId(outletId);
        waitForElementToAppearAndDisappear(LOADER,3,3);
        if (outletIdExists(outletId)) {
            deleteOutletId(outletId);
        }
    }
}

