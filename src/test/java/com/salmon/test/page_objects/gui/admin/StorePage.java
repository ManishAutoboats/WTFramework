package com.salmon.test.page_objects.gui.admin;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StorePage extends PageObject {

    public static int intDelayTime;
    private final static By STORE_LINK = By.cssSelector("#menu-magento-backend-stores>a");
    private final static By CONFIG_LINK = By.cssSelector("li.item-system-config.level-2>a");
    private final static By BAT_LINK = By.xpath("//strong[text()='BAT']");
    private final static By NEMID_LINK = By.xpath("//span[text()='Nem ID']");
    private final static By STORE_SWITCH_LINK = By.xpath("//button[@id='store-change-button']");
    private static final By DISABLE_NEMID_DROPDOWN = By.xpath("//select[@id='nemid_iframe_integration_enable']/option[@selected='selected']");
    private static final By ACCEPT_OK_BUTTON = By.cssSelector("button.action-primary.action-accept");
    private static final By DROPDOWN_SELECTION = By.id("nemid_iframe_integration_enable");
    private static final By SAVE_BUTTON = By.cssSelector("button#save");
    private static final By SAVE_PRODUCT_BUTTON = By.cssSelector("button#save-button");
    private static final By SUCCESS_MESSAGE = By.cssSelector("div.message.message-success.success");
    private static final By STORE_SCOPE_LIST=By.xpath("//ul[@class='dropdown-menu']/li/a");
    private final static By SEARCH_FILTERS_CLEAR_ALL_LINK = By.cssSelector("div.admin__data-grid-header button.action-tertiary.action-clear");
    private final static By SEARCH_TEXT_FIELD = By.cssSelector("#fulltext");
    private final static By SUBMIT_SEARCH_BUTTON = By.cssSelector("div.data-grid-search-control-wrap > button.action-submit");
    public final static By SEARCH_RESULTS_ROWS = By.cssSelector("tr.data-row");
    private final static By DASHBOARD_STORES_BAT_SECTION = By.xpath("//div[@class='admin__page-nav-title title _collapsible']//following::*[text()='BAT' or text()='bat']");
    private final static By SALESFORCE_CHAT_LINK = By.linkText("Salesforce Chat");
    private final static By CHECKOUT_POPUP_ALERT_LINK = By.linkText("Checkout Popup Alert");
    public final static By BLOCKS_LINK=By.linkText("Blocks");
    public final static By PRODUCTS_LINK=By.linkText("Products");
    private final static By SELECT_SALESFORCE_CHAT_ENABLED=By.cssSelector("select#bat_salesforce_chat_general_enabled");
    public final static By DASHBOARD_CATALOG_LINK=By.cssSelector("li.item-catalog.parent.level-0 > a:nth-child(1)");
    private final static By DASHBOARD_STORES_LINK = By.cssSelector("li.item-stores.parent.level-0> a:nth-child(1)");
    private final static By DASHBOARD_STORES_CONFIGURATION_LINK = By.xpath("//li[contains(@class,'item-system-store')]//following::span[text()='Configuration'][1]");
    private final static By SALESFORCE_CHAT_DELAY_TIME_FIELD = By.cssSelector("input#bat_salesforce_chat_general_popup_delay");
    private final static By FILTERS_BUTTON=By.cssSelector("div.data-grid-filters-action-wrap > button.action-default");
    private final static By SELECT_STORE_VIEW=By.cssSelector("[name='store_id']");
    private final static By APPLY_FILTERS_BUTTON=By.cssSelector("div.admin__footer-main-actions > button.action-secondary");
    private final static By RELATED_PRODUCT_ROWS=By.xpath("//div[@class='fieldset-wrapper-title']//following::span[text()='Related Products']//following::tr[1]");
    private final static By POD_RECYCLE_SCHEME_LINK = By.linkText("Pod Recycle Scheme");
    public final static By SELECT_POD_RECYCLE_SETTING=By.cssSelector("select#podrecyclescheme_general_enabled");
    public final static By SELECT_POD_RECYCLE_OPTIONS=By.cssSelector("select#podrecyclescheme_general_enabled > option");
    public final static By QTY_POD_RECYCLE_BAG=By.cssSelector("input#podrecyclescheme_general_prs_product_qty");
    public final static By POD_RECYCLE_SWITCH=By.cssSelector("input.admin__actions-switch-checkbox");
    public static final By WEB_SITE_DROP_DOWN = By.xpath("//*[@class='admin__form-field-label']//span[text()='Web Site']//following::select[1]");
    private static final By STOCK_OPTIONS = By.cssSelector("select.admin__control-select[name='sources[assigned_sources][0][status]']");
    private static final By YOTI_AGE_VERIFICATION_SELFIE = By.cssSelector("#age_verification_yoti_age_scan-head");
    private static final By YOTI_TOGGLE = By.cssSelector("#age_verification_yoti_age_scan_enabled");
    private static final By YOTI_SELECTOR = By.id("age_verification_yoti_age_scan_enabled");
    // in store subs
    private static final By MANAGE_STAFF_LINK = By.cssSelector("a[href*='iss/staff/index/']");
    private static final By ADD_STAFF_LINK = By.cssSelector("button[title='Add Staff']");
    private static final By PHYSICAL_STORES_LINK = By.cssSelector("li.item-instore-subscriptions-stores.level-2 > a");
    private static final By MANAGE_TRANSLATION_LINK = By.cssSelector("li.item-translation.level-2 > a > span");
    private static final By TRANSLATION_STAFF_SIGNIN = By.cssSelector("#bat_instore_subscriptions_translation_staff_signin_strings-head");
    private static final By TRANSLATION_STORE_SELECTOR = By.cssSelector("#bat_instore_subscriptions_translation_store_selector_strings-head");
    private static final By TRANSLATION_NAV_STRINGS = By.cssSelector("#bat_instore_subscriptions_translation_nav_strings-head");
    private static final By TRANSLATION_START_AGAIN = By.cssSelector("#bat_instore_subscriptions_translation_start_again_popup-head");
    private static final By TRANSLATION_SIGNIN = By.cssSelector("#bat_instore_subscriptions_translation_signin_strings-head");
    private static final By TRANSLATION_RETRIEVE_QUOTE = By.cssSelector("#bat_instore_subscriptions_translation_retreive_quote_strings-head");
    private static final By TRANSLATION_PRODUCT_SECTION = By.cssSelector("#bat_instore_subscriptions_translation_product_section-head");
    private static final By TRANSLATION_PRODUCT_POPUP_SECTION = By.cssSelector("#bat_instore_subscriptions_translation_product_popup_section-head");
    private static final By TRANSLATION_SUMMARY_SECTION = By.cssSelector("#bat_instore_subscriptions_translation_summary_section-head");
    private static final By TRANSLATION_BASKET_SUMMARY_SECTIOM = By.cssSelector("#bat_instore_subscriptions_translation_basket_summary-head");
    private static final By TRANSLATION_HANDOVER = By.cssSelector("#bat_instore_subscriptions_translation_handover-head");
    private static final By TRANSLATION_SEND_TO_CUSTOMER = By.cssSelector("#bat_instore_subscriptions_translation_send_to_customer-head");
    private static final By TRANSLATION_CHECKOUT = By.cssSelector("#bat_instore_subscriptions_translation_checkout-head");
    private static final By TRANSLATION_STORE_VERIFY_CUSTOMER_ID = By.cssSelector("#bat_instore_subscriptions_translation_verify_customer_id-head");
    private static final By TRANSLATION_WEB = By.cssSelector("#bat_instore_subscriptions_translation_web-head");
    private static final By TRANSLATION_HEADER = By.cssSelector("#bat_instore_subscriptions_translation-head");
    private static final By SALES_LINK = By.cssSelector("div:nth-of-type(14) > div[role='tab'] > strong");
    private static final By PAYMENT_METHODS_LINK = By.cssSelector("div:nth-of-type(14) > ul[role='tabpanel'] > li:nth-of-type(1) > .admin__page-nav-link.item-nav > span");
    private static final By SUBSCRIBE_PRO_TAB = By.cssSelector("#payment_us_subscribe_pro-head");
    private static final By SUBSCRIBE_PRO_ENABLE_3DS_DROPDOWN = By.xpath("//*[@id=\"payment_us_subscribe_pro_three_ds_active\"]");
    private static final By SUBSCRIBE_PRO_ENABLE_3DS_CHECKBOX = By.xpath("//*[@id=\"payment_us_subscribe_pro_three_ds_active_inherit\"]");
    private static final By SUBSCRIBE_PRO_EXPAND = By.cssSelector("div.section-config a#payment_us_subscribe_pro-head");
    private static final By THREE_DS_OPTIONS = By.cssSelector("select#payment_us_subscribe_pro_three_ds_active");
    private static final By PAYMENT_METHODS_LINK_LYFTSE = By.linkText("Payment Methods");
    private static final By THREE_DS_USE_DEFAULT_TICKBOX = By.cssSelector("input[name*='three_ds_active']");

    public void navigateToStorePage() {
        waitForExpectedElement(STORE_LINK,10);
        clickByElementByQueryJSExecutor(STORE_LINK);
    }

    public void navigateToConigPage() {
        clickByElementByQueryJSExecutor(CONFIG_LINK);
    }

    public void navigateToBATPage() {
        clickByElementByQueryJSExecutor(BAT_LINK);
    }

    public void clickOnNemIDLink() {
        clickByElementByQueryJSExecutor(NEMID_LINK);
    }

    public void selectFromStoreDropDown(String store) {
        waitForExpectedElement(STORE_SWITCH_LINK, 10).click();
        List<WebElement> list = getWebDriver().findElements(STORE_SCOPE_LIST);
        for (WebElement ele : list) {
            if (ele.getText().trim().contains(store)) {
                ele.click();
                break;
            }
        }
    }

    public void acceptButton() {
        waitForExpectedElement(ACCEPT_OK_BUTTON).click();
    }

    public void disableNemidDropdown() {
        if (waitForExpectedElement(DISABLE_NEMID_DROPDOWN).getText().contains("No")) {
            LOG.info("No need to change");
        } else {
            waitForExpectedElement(DISABLE_NEMID_DROPDOWN).getText().contains("Yes");
            selectValueFromDropDownByby("No", DROPDOWN_SELECTION);
            clickOnSaveButton();
        }
    }

    public void clickOnSaveButton() {
        waitForExpectedElement(SAVE_BUTTON).click();
    }

    public void successMessage() {
        if (waitForExpectedElement(DISABLE_NEMID_DROPDOWN).getText().contains("No")) {
            LOG.info("No need to change");
        } else {
            Assert.assertTrue(waitForExpectedElement(SUCCESS_MESSAGE).isDisplayed());
        }
    }

    public void userNavigateToStoreConfigPage() {
        navigateToStorePage();
        navigateToConigPage();
    }

    public void userNavigateToNEMIDPage() {
        navigateToBATPage();
        clickOnNemIDLink();
    }

    public void disableNemID(String store) {
        selectFromStoreDropDown(store);
        acceptButton();
        disableNemidDropdown();
        successMessage();
    }

    public void clickMenuLinkFromAdminDashboard(By byMenuLink) {
        waitForPage(50);
        clickByElementByQueryJSExecutor(byMenuLink);
    }

    public void clickLinkFromMenuWindow(By byLinkText) {
        try {
            waitForExpectedElement(byLinkText, 10).click();
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        } catch (Exception ex) {
            clickByElementByQueryJSExecutor(byLinkText);
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        }
    }

    public void removeAnyDefaultSearchFilters() {
        waitForPage();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        if(isElementPresent(SEARCH_FILTERS_CLEAR_ALL_LINK)){
            clickUsingJS(SEARCH_FILTERS_CLEAR_ALL_LINK);
        }
    }

    public void performSearch(String strSearchField) {
        waitForExpectedElement(SEARCH_TEXT_FIELD,20);
        enterDataAndWait(SEARCH_TEXT_FIELD, strSearchField);
        waitForExpectedElement(SUBMIT_SEARCH_BUTTON,20);
        clickUsingJS(SUBMIT_SEARCH_BUTTON);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public void applyStoreViewFilter(String strStoreView) {
        clickFirstElementByQueryJSExecutor(FILTERS_BUTTON);
        if(strStoreView.equalsIgnoreCase("Swedish")) {
            waitForExpectedElement(SELECT_STORE_VIEW).click();
            waitForExpectedElement(SELECT_STORE_VIEW).sendKeys(strStoreView);
            waitForExpectedElement(SELECT_STORE_VIEW).click();
        }else if(strStoreView.equalsIgnoreCase("English")) {
            selectValueFromDropDownByby(strStoreView, SELECT_STORE_VIEW);}
        else if(strStoreView.equalsIgnoreCase("Mexico")){
            clickByElementByQueryJSExecutor(WEB_SITE_DROP_DOWN);
            waitForExpectedElement(WEB_SITE_DROP_DOWN).sendKeys("Vype Mexico");
            clickByElementByQueryJSExecutor(WEB_SITE_DROP_DOWN);
        }
        clickByElementByQueryJSExecutor(APPLY_FILTERS_BUTTON);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public void clickOnEditLinkForSelectedProduct() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        if (getWebDriver().findElements(SEARCH_RESULTS_ROWS).size() > 0) {
            List<WebElement> rowData = getWebDriver().findElements(SEARCH_RESULTS_ROWS);
            for (WebElement row : rowData) {
                if (row.getText().contains("Vype UK") || row.getText().contains("Lyft Sweden") || row.getText().contains("GLO Germany") || row.getText().contains("Pod")) {
                    if(row.getText().contains("Enabled")) {
                        clickByElementByQueryJSExecutor(By.linkText("Edit"));
                        waitForExpectedElement(RELATED_PRODUCT_ROWS, 10);
                        break;
                    }
                }
            }
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        }
    }

    public void performSearchSelectStoreViewAndClickEditLink(String strProduct,String strStoreView) {
        performSearch(strProduct);
        applyStoreViewFilter(strStoreView);
        clickOnEditLinkForSelectedProduct();
    }

    public void selectStoreFromDefaultConfigListAndAcceptPopUp(String strStore) {
        selectFromStoreDropDown(strStore);
        acceptButton();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public void clickOnLinkFromBATSection(By byLinkText) {
        clickByElementByQueryJSExecutor(DASHBOARD_STORES_BAT_SECTION);
        clickByElementByQueryJSExecutor(byLinkText);
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public int performBackEndStepsToFetchDelayTimeForSalesforceChat() {
        clickMenuLinkFromAdminDashboard(DASHBOARD_STORES_LINK);
        clickLinkFromMenuWindow(DASHBOARD_STORES_CONFIGURATION_LINK);
        clickOnLinkFromBATSection(SALESFORCE_CHAT_LINK);
        selectStoreFromDefaultConfigListAndAcceptPopUp("Vype UK");
        if(getAllSelectOptions(SELECT_SALESFORCE_CHAT_ENABLED).get(0).equalsIgnoreCase("Yes")) {
            if(waitForExpectedElement(SALESFORCE_CHAT_DELAY_TIME_FIELD).getAttribute("value").isEmpty())
                intDelayTime =15;
            if(!waitForExpectedElement(SALESFORCE_CHAT_DELAY_TIME_FIELD).getAttribute("value").isEmpty())
                intDelayTime = Integer.parseInt(waitForExpectedElement(SALESFORCE_CHAT_DELAY_TIME_FIELD).getAttribute("value"));
        return intDelayTime;
        }
        else
            LOG.info("Salesforce Live Chat not configured from Backend.");
        return intDelayTime;
    }

    public void performBackEndStepsToAssertRecyclingScheme() {
        clickMenuLinkFromAdminDashboard(DASHBOARD_STORES_LINK);
        clickLinkFromMenuWindow(DASHBOARD_STORES_CONFIGURATION_LINK);
        clickOnLinkFromBATSection(POD_RECYCLE_SCHEME_LINK);
        selectStoreFromDefaultConfigListAndAcceptPopUp("Vype Mexico");
    }

    public void makeCurrentSkuOutOfStock() {
        waitForExpectedElement(STOCK_OPTIONS);
        Select stockOptions = new Select(webDriver.findElement(STOCK_OPTIONS));
        stockOptions.selectByVisibleText("Out of Stock");
        waitForExpectedElement(SAVE_PRODUCT_BUTTON).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public void makeCurrentSkuInStock() {
        waitForExpectedElement(STOCK_OPTIONS);
        Select stockOptions = new Select(webDriver.findElement(STOCK_OPTIONS));
        stockOptions.selectByVisibleText("In Stock");
        waitForExpectedElement(SAVE_PRODUCT_BUTTON).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public void navigateToManageStaffPage() {
        navigateToStorePage();
        clickByElementByQueryJSExecutor(MANAGE_STAFF_LINK);
    }

    public void navigateToAddNewStaffpage() {
        waitForExpectedElement(ADD_STAFF_LINK).click();
    }

    public void navigateToPhysicalStoresPage() {
        clickMenuLinkFromAdminDashboard(DASHBOARD_STORES_LINK);
        clickByElementByQueryJSExecutor(PHYSICAL_STORES_LINK);
    }

    public void navigateToManageTranslationPage(String language) {
        clickMenuLinkFromAdminDashboard(DASHBOARD_STORES_LINK);
        clickByElementByQueryJSExecutor(MANAGE_TRANSLATION_LINK);
        selectStoreLanguageScope(language);
        clickByElementByQueryJSExecutor(TRANSLATION_HEADER);
        waitForElementToAppearAndDisappear(LOADER, 5,5);
    }

    private void selectStoreLanguageScope(String language) {
        boolean storeFound = false;
        waitForExpectedElement(By.cssSelector("#store-change-button")).click();
        List<WebElement> stores = waitForExpectedElements(By.cssSelector("#anchor-content > div.page-main-actions > div.store-switcher.store-view > div.actions.dropdown.closable.active > ul > li"));
        String store = Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).toString();
        for (WebElement storeLanguage: stores) {
            if (storeLanguage.getText().replace(" ","").equalsIgnoreCase(store)) {
                storeFound = true;
            }
            if (storeFound) {
                if (storeLanguage.getText().toLowerCase().equals(language.toLowerCase())) {
                    storeLanguage.click();
                    waitForExpectedElement(ACCEPT_OK_BUTTON).click();
                    break;
                }
            }
        }
    }

    public boolean translationSectionVisible(String translationsection) {
        boolean result;
        By selector = null;
        switch (translationsection.toLowerCase()) {
            case "staff signin":
                selector = TRANSLATION_STAFF_SIGNIN;
                break;
            case "store selector":
                selector = TRANSLATION_STORE_SELECTOR;
            break;
            case "navigation":
                selector = TRANSLATION_NAV_STRINGS;
            break;
            case "start again popup":
                selector = TRANSLATION_START_AGAIN;
            break;
            case "sign in":
                selector = TRANSLATION_SIGNIN;
            break;
            case "retrieve quote":
               selector = TRANSLATION_RETRIEVE_QUOTE;
            break;
            case "product section":
                selector = TRANSLATION_PRODUCT_SECTION;
            break;
            case "product popup section":
                selector = TRANSLATION_PRODUCT_POPUP_SECTION;
            break;
            case "summary section":
                selector = TRANSLATION_SUMMARY_SECTION;
            break;
            case "basket summary":
                selector = TRANSLATION_BASKET_SUMMARY_SECTIOM;
            break;
            case "handover":
                selector = TRANSLATION_HANDOVER;
            break;
            case "send to customer":
                selector = TRANSLATION_SEND_TO_CUSTOMER;
            break;
            case "checkout":
                selector = TRANSLATION_CHECKOUT;
            break;
            case "verify customer id":
                selector = TRANSLATION_STORE_VERIFY_CUSTOMER_ID;
            break;
            case "web":
                selector = TRANSLATION_WEB;
                break;
            default:
                LOG.info("ERROR translationSectionVisible: invalid section supplied: "+translationsection);
                result = false;
        }
        try {
            result = waitForExpectedElement(selector).isDisplayed();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(TRANSLATION_HEADER);
            result = waitForExpectedElement(selector).isDisplayed();
        }
        return result;
    }

    public void switchOff3dsIfON(){
        if (waitForExpectedElement(SUBSCRIBE_PRO_EXPAND).getAttribute("class").equals("")) {
            waitForExpectedElement(SUBSCRIBE_PRO_EXPAND).click();
        }
        if (waitForExpectedElement(THREE_DS_USE_DEFAULT_TICKBOX).isSelected()) {
            waitForExpectedElement(THREE_DS_USE_DEFAULT_TICKBOX).click();
        }
        selectOptionFromDropDownByValue("0", THREE_DS_OPTIONS);
        clickOnSaveButton();
        assertTrue(new Select(waitForExpectedElement(SUBSCRIBE_PRO_ENABLE_3DS_DROPDOWN)).getFirstSelectedOption().getText().equalsIgnoreCase("No"));
    }

    public void ensure3DSisTurnedOff(String store){
        selectFromStoreDropDown(store);
        acceptButton();
        clickByElementByQueryJSExecutor(SALES_LINK);
        clickByElementByQueryJSExecutor(PAYMENT_METHODS_LINK_LYFTSE);
        try{
            switchOff3dsIfON();
        }catch (TimeoutException te){
            clickByElementByQueryJSExecutor(SUBSCRIBE_PRO_TAB);
            switchOff3dsIfON();
        }
    }


}


