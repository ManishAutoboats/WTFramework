package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AvalancheAccountDashboardPage extends PageObject
{
    private ScenarioContext scenarioContext;
    private RegistrationPage registrationPage;
    public AvalancheAccountDashboardPage(ScenarioContext scenarioContext, RegistrationPage registrationPage) {
        this.scenarioContext = scenarioContext;
        this.registrationPage = registrationPage;
    }

    public static final By AVALANCHE_ACCOUNT_OVERVIEW_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span");
    public static final By AVALANCHE_WELCOME_BACK = By.cssSelector("#maincontent > div.columns > div.column.main > p");
    public static final By AVALANCHE_ACCOUNT_DETAILS_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.account-info__container > h2");
    public static final By AVALANCHE_FIRST_NAME_LAST_NAME = By.cssSelector("#maincontent > div.columns > div.column.main > div.account-info__container > p.account-info__name");
    public static final By AVALANCHE_EMAIL_ADDRESS = By.cssSelector("#maincontent > div.columns > div.column.main > div.account-info__container > p.account-info__email > a");
    public static final By AVALANCHE_EDIT_DETAILS_CTA = By.cssSelector("a[href*='account/edit'].action.primary");
    public static final By AVALANCHE_CHANGE_PASSWORD_CTA = By.cssSelector("a.action.secondary");
    public static final By AVALANCHE_CHANGE_PASSWORD_CHECKBOX = By.cssSelector("#change-password");
    public static final By AVALANCHE_ORDER_HISTORY_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-orders > div.block-title.order > h2");
    public  static final By AVALANCHE_ORDER_HISTORY_TABLE = By.cssSelector("#my-orders-table");
    public static final By AVALANCHE_ORDER_TABLE_ORDER_NUMBER_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.id");
    public static final By AVALANCHE_ORDER_TABLE_DATE_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.date");
    public static final By AVALANCHE_ORDER_TABLE_SHIPPING_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.shipping");
    public static final By AVALANCHE_ORDER_TABLE_TOTAL_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.total");
    public static final By AVALANCHE_ORDER_TABLE_STATUS_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.status");
    public static final By AVALANCHE_VIEW_ORDER_HISTORY_BUTTON = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-orders > div.block-content > a > span");
    public static final By AVALANCHE_ADDRESS_BOOK_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-addresses > div.block-title > h2");
    public static final By AVALANCHE_DEFAULT_BILLING_ADDRESS_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-addresses > div.block-content > div.box-billing-address > strong");
    public static final By AVALANCHE_DEFAULT_DELIVERY_ADDRESS_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-addresses > div.block-content > div.box-shipping-address > strong");
    public static final By AVALANCHE_EDIT_ADDRESS_BUTTON = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-addresses > div.block-content > div.actions > a > span");
    public static final By AVALANCHE_LEFT_NAV_MY_ACCOUNT = By.cssSelector("#account-nav > ul > li.nav.item.current > strong");
    public static final By AVALANCHE_LEFT_NAV_MY_DETAILS = By.cssSelector("div#account-nav > ul > li:nth-of-type(2) > a");
    public static final By AVALANCHE_LEFT_NAV_ORDER_HISTORY = By.cssSelector("#account-nav > ul > li > a[href*='history']");
    public static final By AVALANCHE_LEFT_NAV_ADDRESS_BOOK = By.cssSelector("#account-nav > ul > li > a[href*='address']");
    public static final By AVALANCHE_LEFT_NAV_SAVED_PAYMENT_METHOD = By.cssSelector("#account-nav > ul > li > a[href*='savedcard']");
    public static final By AVALANCHE_LEFT_NAV_LEGAL_CONSENT = By.cssSelector("#account-nav > ul > li > a[href*='newsletter']");
    public static final By AVALANCHE_LEFT_NAV_DELETE_ACCOUNT = By.cssSelector("#account-nav > ul > li > a[href*='delete']");
    public static final By AVALANCHE_LEFT_NAV_SIGN_OUT = By.cssSelector("#account-nav > ul > li > a[href*='logout']");
    public static final By AVALANCHE_LEFT_NAV_MARKET_PREFERANCE = By.cssSelector("#account-nav > ul > li:nth-child(5) > a");
    public static final By AVALANCHE_MY_DETAILS_FIRST_NAME_LABEL = By.cssSelector("#form-validate > fieldset.fieldset.info > div.field.field-name-firstname.required > label > span");
    public static final By AVALANCHE_MY_DETAILS_SAID_LABEL = By.cssSelector("#form-validate > fieldset.fieldset.info > div.field.required.za-av-field > label > span");
    public static final By AVALANCHE_MY_DETAILS_EDIT_EMAIL_BUTTON = By.cssSelector("#form-validate > fieldset.fieldset.info > div.field.choice.choice-box__container.choice-email > label");
    public static final By AVALANCHE_MY_DETAILS_EDIT_PASSWORD_BUTTON = By.cssSelector("#form-validate > fieldset.fieldset.info > div.field.choice.choice-box__container.choice-password > label");
    public static final By AVALANCHE_MY_DETAILS_SAVE_CHANGES_BUTTON = By.cssSelector("#form-validate > div > div > button > span");
    public static final By AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_HEADING = By.cssSelector("#form-validate > fieldset.fieldset.password > legend > span");
    public static final By AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_CURRENT_PASSWORD_FIELD = By.cssSelector("#current-password");
    public static final By AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_NEW_PASSWORD_FIELD = By.cssSelector("#password");
    public static final By AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_CONFIRM_PASSWORD_FIELD = By.cssSelector("#password-confirmation");
    public static final By AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_WEAK = By.cssSelector("div.password-weak");
    public static final By AVALANCHE_MY_DETAILS_FIRST_NAME_INPUT = By.cssSelector("input#firstname");
    public static final By AVALANCHE_MY_DETAILS_LAST_NAME_INPUT = By.cssSelector("input#lastname");
    public static final By AVALANCHE_ORDER_HISTORY_PAGE_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span");
    public static final By AVALANCHE_ORDER_HISTORY_ORDER_NUMBER_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.id");
    public static final By AVALANCHE_ORDER_HISTORY_DATE_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.date");
    public static final By AVALANCHE_ORDER_HISTORY_SHIPPING_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.shipping");
    public static final By AVALANCHE_ORDER_HISTORY_TOTAL_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.total");
    public static final By AVALANCHE_ORDER_HISTORY_STATUS_HEADING = By.cssSelector("#my-orders-table > thead > tr > th.col.status");
    public static final By AVALANCHE_ORDER_HISTORY_ORDER_ENTRY = By.cssSelector("#my-orders-table > tbody > tr");
    public static final By AVALANCHE_ORDER_HISTORY_VIEW_ORDER_BUTTON = By.cssSelector(" td.col.actions > div > a > span");
    public static final By AVALANCHE_ORDER_DETAILS_BUY_AGAIN_BUTTON = By.cssSelector("#maincontent > div.columns > div.column.main > div.content-inner > div.actions-toolbar.order-actions-toolbar > div > a > span");
    public static final By AVALANCHE_ORDER_DETAILS_ORDER_ITEM = By.cssSelector("tbody > tr[id*='order-item-row-']");
    public static final By AVALANCHE_ORDER_DETAILS_ORDER_ITEM_NAME = By.cssSelector("td.col.name > strong");
    public static final By AVALANCHE_ORDER_DETAILS_ORDER_ITEM_QUANTITY = By.cssSelector("td.col.qty > ul > li > span.content");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-default > div.block-content > div.box.box-address-billing > div.box-title > span");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-default > div.block-content > div.box.box-address-shipping > strong > span");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-default > div.block-content > div.box.box-address-billing > div.box-content > div.address-container");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS_BLOCK = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-default > div.block-content > div.box.box-address-billing > div.box-content");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-default > div.block-content > div.box.box-address-shipping > div > div.address-container");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS_BLOCK = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-default > div.block-content > div.box.box-address-shipping > div");
    public static final By AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-list.block-additional-addresses > div.additional-addresses-head > h2");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS_EDIT_BUTTON = By.cssSelector("#edit_billing");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS_EDIT_BUTTON = By.cssSelector("#edit_shipping");
    public static final By AVALANCHE_ADDRESS_BOOK_DEFAULT_ADDRESS_EDIT_BUTTON = By.cssSelector("#default_address_change_both");
    public static final By AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_EDIT_BUTTON = By.cssSelector("#edit_address > span");
    public static final By AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_NO_ADDRESSES_TEXT = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-list.block-additional-addresses > div.block-content > p");
    public static final By AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-list.block-additional-addresses > div.block-content > div");
    public static final By AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS_REMOVE_BUTTON = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-list.block-additional-addresses > div.block-content > div >div>button.action.secondary.delete>span");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_BUTTON = By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-addresses-default > div.actions-toolbar.new-address-box > div.primary > button > span");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY = By.cssSelector("#js--address-search-toggle");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE = By.cssSelector("footer > .action.primary.submit");
    public final static By PHONE_NUMBER_PAYMENTPAGE = By.cssSelector(".telephone #telephone");
    public final static By STREETADDRESSLINE1_PAYMENTPAGE = By.cssSelector("#street_1");
    public final static By STREETADDRESSLINE2_PAYMENTPAGE = By.cssSelector("#street_2");
    public final static By STREETADDRESSLINE3_PAYMENTPAGE = By.cssSelector("#street_3");
    public final static By CITY_PAYMENT = By.cssSelector("#city");
    public final static By POSTCODE_PAYMENTPAGE = By.cssSelector("#zip");
    public static final By AVALANCHE_ADDRESS_BOOK_SAVE_NEW_ADDRESS_BUTTON = By.cssSelector("#form-validate > div.actions-toolbar > div.primary > button");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_HEADER = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal_edit_address.address_edit_new.modal-slide._inner-scroll._show > div.modal-inner-wrap > header");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_TELEPHONE = By.cssSelector("#telephone");
    public static final By ADDRESS_SEARCH_VUSEDE = By.cssSelector("input[name='address-search']");
    public static final By STREET_ADDRESS_VELOZA = By.cssSelector("input[name='street[2]']");
    public static final By MANUAL_ADDRESS_ENTRY_VELOZA=By.cssSelector("#js--address-search-toggle");
    public By province = By.cssSelector("#region_id");
    public static final By ADDRESS_SEARCH_RESULT = By.cssSelector("div.pcaitem");
    public static final By STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON = By.cssSelector("div.field.choice.set.billing > label");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_ADDRESS = By.cssSelector("#address-search");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_ADDRESS_SUGGESTION = By.cssSelector("#html-body > div.pca > div > div.pca.pcalist > div");
    public static final By AVALANCHE_ADDRESS_BOOK_PAGE_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span");
    public static final By AVALANCHE_ADDRESS_BOOK_EDIT_ADDRESS_MODAL = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal_edit_address._inner-scroll._show > div.modal-inner-wrap");
    public static final By AVALANCHE_ADDRESS_BOOK_EDIT_ADDRESS_MODAL_BACK_TO_ADDRESSES = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal_edit_address > div.modal-inner-wrap > header > button");
    public static final By AVALANCHE_ADDRESS_BOOK_EDIT_ADDRESS_MODAL_BACK_TO_ADDRESSES_CLOSE = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal_edit_address.address_edit_new.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
    public static final By AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.confirm._show > div.modal-inner-wrap");
    public static final By AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL_TEXT = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.confirm._show > div.modal-inner-wrap > div > div");
    public static final By AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL_CANCEL_BUTTON = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.confirm._show > div.modal-inner-wrap > footer > button.action-secondary.action-dismiss > span");
    public static final By AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL_CONFIRM_BUTTON = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.confirm._show > div.modal-inner-wrap > footer > button.action-primary.action-accept > span");
    public static final By AVALANCHE_SAVED_PAYMENT_METHODS_NO_SAVED_CARDS_TEXT = By.cssSelector("#maincontent > div.columns > div.column.main > div.message.info.empty > span");
    public static final By AVALANCHE_SAVED_PAYMENT_METHODS_HEADING = By.cssSelector("#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span");
    public static final By AVALANCHE_SIGNED_OUT_HEADING = By.cssSelector("#maincontent > div.page-title-wrapper > h1 > span");
    public static final By AVALANCHE_SIGNED_OUT_MESSAGE = By.cssSelector("#maincontent > div.columns > div > p");
    public static final By AVALANCHE_SAVED_PAYMENT_METHOD = By.cssSelector("#maincontent > div.columns > div.column.main > div > div > div");
    public static final By AVALANCHE_SAVED_CARDS_PAGE_TITLE = By.cssSelector("h1.page-title");
    public static final By AVALANCHE_SAVED_CARD_CARD_HOLDER_NAME = By.cssSelector("#card-holder-name");
    public static final By AVALANCHE_SAVED_CARD_EXPIRY_MONTH_SELECTOR = By.cssSelector("#expiry_month");
    public static final By AVALANCHE_SAVED_CARD_EXPIRY_YEAR_SELECTOR = By.cssSelector("select[name*='card_expiry_year']");
    public static final By AVALANCHE_SAVED_CARD_EDIT_SAVE_BUTTON = By.cssSelector("button.action.save.primary");
    public static final By AVALANCHE_SAVED_CARD_DELETE = By.cssSelector("a[data-delete-url*='/delete/']");
    public static final By AVALANCHE_SAVED_CARD_DELETE_CONFIRM = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.confirm._show > div.modal-inner-wrap > footer > button.action-primary.action-accept > span");
    public static final By AVALANCHE_ADDRESS_BOOK_CANCEL_EDIT_BUTTON = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
    public static final By AVALANCHE_SAVED_CARD_EXPIRY_DETAILS = By.cssSelector("span.card-expire");

    public void validateMyAccountComponent(String component) {
        String actualText = null;
        String expectedText = null;
        String firstAndLastName = scenarioContext.getContext(Context.FIRST_AND_LAST_NAME).toString();
        String firstName = firstAndLastName.split(" ")[0];
        String lastName = firstAndLastName.split(" ")[1];
        // remove when polish age gate is removed
//        try {
//            List<WebElement> polishAgeGate = waitForExpectedElements(By.cssSelector("#age-gate-over"));
//            if (polishAgeGate.size() > 0) {
//                polishAgeGate.get(0).click();
//            }
//        } catch (Exception e) {};
        //
        switch (component) {
            case "account overview":
                expectedText = UrlBuilder.getMessage(translate("accountOverviewText"));
                actualText = waitForExpectedElement(AVALANCHE_ACCOUNT_OVERVIEW_HEADING).getText();
                break;
            case "welcome back":
                expectedText = UrlBuilder.getMessage(translate("welcomeBackText")).replace("%FIRSTNAME%", firstName);
                actualText = waitForExpectedElement(AVALANCHE_WELCOME_BACK).getText();
                break;
            case "account details heading":
                expectedText = UrlBuilder.getMessage(translate("accountDetailsHeadingText"));
                actualText = waitForExpectedElement(AVALANCHE_ACCOUNT_DETAILS_HEADING).getText();
                break;
            case "first and last name":
                expectedText = firstName + " " + lastName;
                actualText = waitForExpectedElement(AVALANCHE_FIRST_NAME_LAST_NAME).getText();
                break;
            case "email address":
                expectedText = scenarioContext.getContext(Context.EMAIL_ID).toString();
                actualText = waitForExpectedElement(AVALANCHE_EMAIL_ADDRESS).getText();
                break;
            case "edit details cta":
                assertThat(waitForExpectedElement(AVALANCHE_EDIT_DETAILS_CTA).isEnabled())
                        .as("ERROR  validateMyAccountComponent: edit details cta is not enabled").isTrue();
                expectedText = UrlBuilder.getMessage(translate("editDetailsCtaText"));
                actualText = waitForExpectedElement(AVALANCHE_EDIT_DETAILS_CTA).getText();
                break;
            case "change password cta":
                assertThat(waitForExpectedElement(AVALANCHE_CHANGE_PASSWORD_CTA).isEnabled())
                        .as("ERROR  validateMyAccountComponent: change password cta is not enabled").isTrue();
                expectedText = UrlBuilder.getMessage(translate("changePasswordCtaText"));
                actualText = waitForExpectedElement(AVALANCHE_CHANGE_PASSWORD_CTA).getText();
                break;
            case "order history heading":
                expectedText = UrlBuilder.getMessage(translate("orderHistoryTableHeadingText"));
                actualText = waitForExpectedElement(AVALANCHE_ORDER_HISTORY_HEADING).getText();
                break;
            case "order history table":
                validateOrderHistoryTable();
                break;
            case "view order history button":
                assertThat(waitForExpectedElement(AVALANCHE_VIEW_ORDER_HISTORY_BUTTON).isEnabled())
                        .as("ERROR  validateMyAccountComponent: view order history cta is not enabled").isTrue();
                expectedText = UrlBuilder.getMessage(translate("viewOrderHistoryButtonText"));
                actualText = waitForExpectedElement(AVALANCHE_VIEW_ORDER_HISTORY_BUTTON).getText();
                break;
            case "address book heading":
                expectedText = UrlBuilder.getMessage(translate("addressBookHeading"));
                actualText = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_HEADING).getText();
                break;
            case "default billing address heading":
                expectedText = UrlBuilder.getMessage(translate("defaultBillingAddressheading"));
                actualText = waitForExpectedElement(AVALANCHE_DEFAULT_BILLING_ADDRESS_HEADING).getText();
                break;
            case "default delivery address heading":
                expectedText = UrlBuilder.getMessage(translate("defaultDeliveryAddressheading"));
                actualText = waitForExpectedElement(AVALANCHE_DEFAULT_DELIVERY_ADDRESS_HEADING).getText();
                break;
            case "edit address button":
                assertThat(waitForExpectedElement(AVALANCHE_EDIT_ADDRESS_BUTTON).isEnabled())
                        .as("ERROR  validateMyAccountComponent: edit address cta is not enabled").isTrue();
                expectedText = UrlBuilder.getMessage(translate("editAddressesButtontext"));
                actualText = waitForExpectedElement(AVALANCHE_EDIT_ADDRESS_BUTTON).getText();
                break;
            case "left nav my account":
                expectedText = UrlBuilder.getMessage(translate("leftNavMyAccountText"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_MY_ACCOUNT).getText();
                break;
            case "left nav my details":
                    expectedText = UrlBuilder.getMessage(translate("leftNavMyDetailsText"));
                    actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_MY_DETAILS).getText();
                break;
            case "left nav order history":
                expectedText = UrlBuilder.getMessage(translate("leftNavOrderHistoryText"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_ORDER_HISTORY).getText();
                break;
            case "left nav address book":
                expectedText = UrlBuilder.getMessage(translate("leftNavAddressBookText"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_ADDRESS_BOOK).getText();
                break;
            case "left nav payment methods":
                expectedText = UrlBuilder.getMessage(translate("leftNavPaymentMethodsText"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_SAVED_PAYMENT_METHOD).getText();
                break;
            case "left nav delete account":
                expectedText = UrlBuilder.getMessage(translate("leftNavDeleteAccountText"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_DELETE_ACCOUNT).getText();
                break;

            case "left nav legal consents":
                expectedText = UrlBuilder.getMessage(translate("leftNavLegalConsentsText"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_LEGAL_CONSENT).getText();
                break;
            case "left nav sign out":
                expectedText = UrlBuilder.getMessage(translate("leftNavSignOutText"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_SIGN_OUT).getText();
                break;
            case "left nav market preferances":
                expectedText = UrlBuilder.getMessage(translate("leftNavMarketPreferance"));
                actualText = waitForExpectedElement(AVALANCHE_LEFT_NAV_MARKET_PREFERANCE).getText();
                break;
            default:
                assertThat(true).as("ERROR validateMyAccountComponent: invlaid component " + component + " supplied").isFalse();
        }
        assertThat(expectedText == actualText || expectedText.equalsIgnoreCase(actualText))
                .as("ERROR validateMyAccountComponent: error for component " + component + " expected " + expectedText + " but got "+actualText).isTrue();
    }

    private void validateOrderHistoryTable() {
        String expectedText = null;
        String actualText = null;
        assertThat(waitForExpectedElement(AVALANCHE_ORDER_HISTORY_TABLE).isDisplayed())
                .as("ERROR validateOrderHistoryTable: order history table is not displayed").isTrue();
        expectedText = UrlBuilder.getMessage(translate("orderTableOrderNumberHeading"));
        actualText = waitForExpectedElement(AVALANCHE_ORDER_TABLE_ORDER_NUMBER_HEADING).getText();
        assertThat(actualText)
                .as("ERROR validateOrderHistoryTable: expected order table order number heading was " + expectedText + " but i got " + actualText)
                .isEqualToIgnoringCase(expectedText);
        expectedText = UrlBuilder.getMessage(translate("orderTableDateHeading"));
        actualText = waitForExpectedElement(AVALANCHE_ORDER_TABLE_DATE_HEADING).getText();
        assertThat(actualText)
                .as("ERROR validateOrderHistoryTable: expected order table date heading was " + expectedText + " but i got " + actualText)
                .isEqualToIgnoringCase(expectedText);
        expectedText = UrlBuilder.getMessage(translate("orderTableShippingHeading"));
        actualText = waitForExpectedElement(AVALANCHE_ORDER_TABLE_SHIPPING_HEADING).getText();
        assertThat(actualText)
                .as("ERROR validateOrderHistoryTable: expected order table shipping heading was " + expectedText + " but i got " + actualText)
                .isEqualToIgnoringCase(expectedText);
        expectedText = UrlBuilder.getMessage(translate("orderTableTotalHeading"));
        actualText = waitForExpectedElement(AVALANCHE_ORDER_TABLE_TOTAL_HEADING).getText();
        assertThat(actualText)
                .as("ERROR validateOrderHistoryTable: expected order table total heading was " + expectedText + " but i got " + actualText)
                .isEqualToIgnoringCase(expectedText);
        expectedText = UrlBuilder.getMessage(translate("orderTableStatusHeading"));
        actualText = waitForExpectedElement(AVALANCHE_ORDER_TABLE_STATUS_HEADING).getText();
        assertThat(actualText)
                .as("ERROR validateOrderHistoryTable: expected order table status heading was " + expectedText + " but i got " + actualText)
                .isEqualToIgnoringCase(expectedText);
    }

    public void iClickOnTab(String tabName) {
        By selector = null;
        switch (tabName) {
            case "left nav my details":
                    selector = AVALANCHE_LEFT_NAV_MY_DETAILS;
                break;
            case "left nav order history":
                selector = AVALANCHE_LEFT_NAV_ORDER_HISTORY;
                break;
            case "left nav address book":
                selector = AVALANCHE_LEFT_NAV_ADDRESS_BOOK;
                break;
            case "left nav saved payment methods":
                selector = AVALANCHE_LEFT_NAV_SAVED_PAYMENT_METHOD;
                break;
            case "left nav sign out":
                selector = AVALANCHE_LEFT_NAV_SIGN_OUT;
                break;
            case "left nav legal consents":
                selector = AVALANCHE_LEFT_NAV_LEGAL_CONSENT;
                break;
            default:
                assertThat(true).as("ERROR iClickOnTab: invalid link name " + tabName + " supplied").isFalse();
        }
        clickByElementByQueryJSExecutor(selector);
    }

    public void validateMyDetailsComponent(String component) {
        String actualText = null;
        String expectedText = null;
        switch(component) {
            case "personal details":
                expectedText = UrlBuilder.getMessage(translate("myDetailsFirstNameLabel"));
                actualText = waitForExpectedElement(AVALANCHE_MY_DETAILS_FIRST_NAME_LABEL).getText();
                break;
            case "sa id number":
                expectedText = UrlBuilder.getMessage(translate("myDetailsSAIDText"));
                actualText = waitForExpectedElement(AVALANCHE_MY_DETAILS_SAID_LABEL).getText();
                break;
            case "edit CTA for email":
                expectedText = UrlBuilder.getMessage(translate("myDetailsEditEmailCtaText") );
                actualText = waitForExpectedElement(AVALANCHE_MY_DETAILS_EDIT_EMAIL_BUTTON).getText();
                break;
            case "edit CTA for password":
                expectedText = UrlBuilder.getMessage(translate("myDetailsEditPasswordCtaText") );
                actualText = waitForExpectedElement(AVALANCHE_MY_DETAILS_EDIT_PASSWORD_BUTTON).getText();
                break;
            case "save changes CTA":
                expectedText = UrlBuilder.getMessage(translate("myDetailsSaveChangesCtaText"));
                actualText = waitForExpectedElement(AVALANCHE_MY_DETAILS_SAVE_CHANGES_BUTTON).getText();
                break;
            default:
                assertThat(true).as("ERROR validateMyDetailsComponent: invlaid component " + component + " supplied").isFalse();
        }
        assertThat(expectedText == actualText || expectedText.equalsIgnoreCase(actualText))
                .as("ERROR validateMyDetailsComponent: error for component " + component + " expected " + expectedText + " but got "+actualText).isTrue();
        validateMyDetailsInputValues();
    }

    public void validateMyDetailsInputValues() {
        String firstAndLastName = scenarioContext.getContext(Context.FIRST_AND_LAST_NAME).toString();
        String expectedFirstName = firstAndLastName.split(" ")[0];
        String expectedLastName = firstAndLastName.split(" ")[1];
        String actualFirstName = waitForExpectedElement(AVALANCHE_MY_DETAILS_FIRST_NAME_INPUT).getAttribute("value");
        String actualLastName = waitForExpectedElement(AVALANCHE_MY_DETAILS_LAST_NAME_INPUT).getAttribute("value");
        assertThat(expectedFirstName.equalsIgnoreCase(actualFirstName))
                .as("ERROR validateMyDetailsInputValues: expected first name was " + expectedFirstName + " but I got " + actualFirstName ).isTrue();
        assertThat(expectedLastName.equalsIgnoreCase(actualLastName))
                .as("ERROR validateMyDetailsInputValues: expected last name was " + expectedLastName + " but I got " + actualLastName ).isTrue();
    }

    public void iSeeHistoryOfAllOrders() {
        String expectedOrderHistoryPageHeading = UrlBuilder.getMessage(translate("orderHistoryHeading"));
        String actualOrderHistoryPageHeading = waitForExpectedElement(AVALANCHE_ORDER_HISTORY_PAGE_HEADING).getText();
        assertThat(expectedOrderHistoryPageHeading.equalsIgnoreCase(actualOrderHistoryPageHeading))
                .as("ERROR iSeeHistoryOfAllOrders: expected page heading was " + expectedOrderHistoryPageHeading + " but I got " + actualOrderHistoryPageHeading).isTrue();
        validateOrderTableHeadings();
    }

    private void validateOrderTableHeadings() {
        String expectedText = null;
        String actualtext = null;
        actualtext = waitForExpectedElement(AVALANCHE_ORDER_HISTORY_ORDER_NUMBER_HEADING).getText();
        expectedText = UrlBuilder.getMessage(translate("orderTableOrderNumberHeading"));
        assertThat(expectedText.equalsIgnoreCase(actualtext))
                .as("ERROR validateOrderTableHeadings: expected order number heading was " + expectedText + " but I got " + actualtext).isTrue();
        actualtext = waitForExpectedElement(AVALANCHE_ORDER_HISTORY_DATE_HEADING).getText();
        expectedText = UrlBuilder.getMessage(translate("orderTableDateHeading"));
        assertThat(expectedText.equalsIgnoreCase(actualtext))
                .as("ERROR validateOrderTableHeadings: expected order date heading was " + expectedText + " but I got " + actualtext).isTrue();
        actualtext = waitForExpectedElement(AVALANCHE_ORDER_HISTORY_SHIPPING_HEADING).getText();
        expectedText = UrlBuilder.getMessage(translate("orderTableShippingHeading"));
        assertThat(expectedText.equalsIgnoreCase(actualtext))
                .as("ERROR validateOrderTableHeadings: expected order shipping heading was " + expectedText + " but I got " + actualtext).isTrue();
        actualtext = waitForExpectedElement(AVALANCHE_ORDER_HISTORY_TOTAL_HEADING).getText();
        expectedText = UrlBuilder.getMessage(translate("orderTableTotalHeading"));
        assertThat(expectedText.equalsIgnoreCase(actualtext))
                .as("ERROR validateOrderTableHeadings: expected order total heading was " + expectedText + " but I got " + actualtext).isTrue();
        actualtext = waitForExpectedElement(AVALANCHE_ORDER_HISTORY_STATUS_HEADING).getText();
        expectedText = UrlBuilder.getMessage(translate("orderTableStatusHeading"));
        assertThat(expectedText.equalsIgnoreCase(actualtext))
                .as("ERROR validateOrderTableHeadings: expected order status heading was " + expectedText + " but I got " + actualtext).isTrue();
    }

    public void thereIsAViewOrderCtaNextToEachOrder() {
        String actualViewOrderLinkText = null;
        List<WebElement> orders = waitForExpectedElements(AVALANCHE_ORDER_HISTORY_ORDER_ENTRY);
        String expectedViewOrderLinkText = UrlBuilder.getMessage(translate("viewOrderLinkText"));
        for (WebElement order: orders) {
            actualViewOrderLinkText = order.findElement(By.cssSelector("a")).getText();
            assertThat(actualViewOrderLinkText.equalsIgnoreCase(expectedViewOrderLinkText))
                    .as("ERROR thereIsAViewOrderCtaNextToEachOrder: expected view order link text was " + expectedViewOrderLinkText + " but I got " + actualViewOrderLinkText).isTrue();
        }
    }

    public void clickViewOrder() {
        List<WebElement> orders = waitForExpectedElements(AVALANCHE_ORDER_HISTORY_ORDER_ENTRY);
        Random rand = new Random();
        int randomOrderIndex = rand.nextInt(orders.size());
        clickUsingJS(orders.get(randomOrderIndex).findElement(AVALANCHE_ORDER_HISTORY_VIEW_ORDER_BUTTON));
        String actualOrderDetailsHeading = waitForExpectedElement(AVALANCHE_ACCOUNT_OVERVIEW_HEADING).getText();
        String expectedOrderDetailsHeading = UrlBuilder.getMessage(translate("orderDetailsHeading"));
                assertThat(actualOrderDetailsHeading.equalsIgnoreCase(expectedOrderDetailsHeading))
                        .as("ERROR clickViewOrder: expected page heading following click on view order was "+expectedOrderDetailsHeading+" but I got "+actualOrderDetailsHeading).isTrue();
    }

    public void buyAgainCtaVisible() {
        String expectedBuyAgainButtonText = UrlBuilder.getMessage(translate("buyAgainButtonText"));
        String actualBuyAgainButtonText = waitForExpectedElement(AVALANCHE_ORDER_DETAILS_BUY_AGAIN_BUTTON).getText();
        assertThat(actualBuyAgainButtonText.equalsIgnoreCase(expectedBuyAgainButtonText))
                .as("ERROR buyAgainCtaVisible: expected buy again button text was " + expectedBuyAgainButtonText + " but I got " + actualBuyAgainButtonText).isTrue();
    }

    public void recordOrderContents() {
        String productName = null;
        String productQuantity = null;
        Map<String,String> orderContents = new HashMap<>();
        List<WebElement> orderItems = waitForExpectedElements(AVALANCHE_ORDER_DETAILS_ORDER_ITEM);
        for (WebElement orderItem: orderItems) {
            productName = orderItem.findElement(AVALANCHE_ORDER_DETAILS_ORDER_ITEM_NAME).getText().trim();
            productQuantity = orderItem.findElement(AVALANCHE_ORDER_DETAILS_ORDER_ITEM_QUANTITY).getText();
            orderContents.put(productName, productQuantity);
        }
        scenarioContext.setContext(Context.BASKET_CONTENTS_MAP, orderContents);
    }

    public void clickOnBuyAgainButton() {
        waitForExpectedElement(AVALANCHE_ORDER_DETAILS_BUY_AGAIN_BUTTON).click();
    }

    public void iAmOnTheAddressBookPage() {
        String expectedAddressBookPageHeading =  UrlBuilder.getMessage(translate("leftNavAddressBookText"));
        String actualAddressBookPageHeading = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_PAGE_HEADING).getText();
        assertThat(expectedAddressBookPageHeading.equalsIgnoreCase(actualAddressBookPageHeading))
                .as("ERROR iAmOnTheAddressBookPage: expected address book page heading was " + expectedAddressBookPageHeading + " but I got " + actualAddressBookPageHeading).isTrue();
    }

    public String translate(String key) {
        String language = scenarioContext.getContext(Context.LANGUAGE).toString();
        return key + "-" + language;
    }

    public void iSeeDefaultShippingAndBillingAddress() {
        String expectedDefaultBillingAddressHeading = UrlBuilder.getMessage(translate("defaultBillingAddressheading"));
        String actualDefaultBillingAddressHeading = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS_HEADING).getText();
        assertThat(expectedDefaultBillingAddressHeading.equalsIgnoreCase(actualDefaultBillingAddressHeading))
                .as("ERROR iSeeDefaultShippingAndBillingAddress: expected default billing address heading was " + expectedDefaultBillingAddressHeading + " but I got " + actualDefaultBillingAddressHeading).isTrue();
        String expectedDefaultShippingAddressHeading = UrlBuilder.getMessage(translate("defaultDeliveryAddressheading"));
        String actualDefaultShippingAddressHeading = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS_HEADING).getText();
        assertThat(expectedDefaultShippingAddressHeading.equalsIgnoreCase(actualDefaultShippingAddressHeading))
                .as("ERROR iSeeDefaultShippingAndBillingAddress: expected default shipping address heading was " + expectedDefaultShippingAddressHeading + " but I got " + actualDefaultShippingAddressHeading).isTrue();
        String defaultBillingAddress = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS).getText();
        assertThat(defaultBillingAddress.length() > 0).as("ERROR iSeeDefaultShippingAndBillingAddress: default billing address is not populated").isTrue();
        String defaultShippingAddress = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS).getText();
        assertThat(defaultShippingAddress.length() > 0).as("ERROR iSeeDefaultShippingAndBillingAddress: default shipping address is not populated").isTrue();
    }

    public void iSeeOtherAddressInfo() {
        String expectedOtherAddressesHeading = UrlBuilder.getMessage(translate("otherAddressHeading"));
        String actualOtherAddressesHeading = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_HEADING).getText();
        assertThat(expectedOtherAddressesHeading.equalsIgnoreCase(actualOtherAddressesHeading))
                .as("ERROR iSeeOtherAddressInfo: expected other addresses heading was " +  expectedOtherAddressesHeading + " but I got " + actualOtherAddressesHeading).isTrue();
    }

    public void thereAreCtasToEditAnyOfTheAddresses() {
        String actualEditOtherAddressesButtonText;
        int buttonCount;
        WebElement section;
        String expectedEditAddressButtonText = UrlBuilder.getMessage(translate("editAddressButtonText"));
        String actualEditDefaultBillingAddressButtonText = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS_EDIT_BUTTON).getText();
        assertThat(expectedEditAddressButtonText.equalsIgnoreCase(actualEditDefaultBillingAddressButtonText))
                .as("ERROR thereAreCtasToEditAnyOfTheAddresses: expected edit default billing address button text was " + expectedEditAddressButtonText + " but I got " + actualEditDefaultBillingAddressButtonText).isTrue();
        section = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS_BLOCK);
        buttonCount = section.findElements(By.cssSelector("button")).size();
        assertThat(buttonCount == 1)
                .as("ERROR thereAreCtasToEditAnyOfTheAddresses: expected only a single default billing address edit button but found " + buttonCount + " buttons").isTrue();
        String actualEditDefaultShippingAddressButtonText = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS_EDIT_BUTTON).getText();
        assertThat(expectedEditAddressButtonText.equalsIgnoreCase(actualEditDefaultShippingAddressButtonText))
                .as("ERROR thereAreCtasToEditAnyOfTheAddresses: expected edit default shipping address button text was " + expectedEditAddressButtonText + " but I got " + actualEditDefaultBillingAddressButtonText).isTrue();
        section = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS_BLOCK);
        buttonCount = section.findElements(By.cssSelector("button")).size();
        assertThat(buttonCount == 1)
                .as("ERROR thereAreCtasToEditAnyOfTheAddresses: expected only a single default shipping edit button but found " + buttonCount + " buttons").isTrue();
        List<WebElement> otherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        for (WebElement otherAddress: otherAddresses) {
            actualEditOtherAddressesButtonText = otherAddress.findElement(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_EDIT_BUTTON).getText();
            assertThat(expectedEditAddressButtonText.equalsIgnoreCase(actualEditOtherAddressesButtonText))
                    .as("ERROR thereAreCtasToEditAnyOfTheAddresses: expected default billing address edit button text was " + expectedEditAddressButtonText + " but I got " + actualEditDefaultBillingAddressButtonText).isTrue();
        }
        if (otherAddresses.size() == 0) {
            String expectedNoOtherAddressEntriesText = UrlBuilder.getMessage(translate("noOtherAddressEntriesText"));
            String actualNoOtherAddressEntriesText = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_NO_ADDRESSES_TEXT).getText();
            assertThat(expectedNoOtherAddressEntriesText.equalsIgnoreCase(actualNoOtherAddressEntriesText))
                    .as("ERROR thereAreCtasToEditAnyOfTheAddresses: no other addresses expected text was " + expectedNoOtherAddressEntriesText + " but I got " + actualNoOtherAddressEntriesText).isTrue();
        }
    }

    public void onlyOtherAddressesHasRemoveButton() {
        List<WebElement> otherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        if (otherAddresses.size() == 0) {
            addNewAddress();
            otherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        }
            for (WebElement otherAddress: otherAddresses) {
                waitForAjaxElementNotToBePresent(webDriver,5);
                String cancelBtn=webDriver.findElement(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS_REMOVE_BUTTON).getText();
                assertThat(cancelBtn.equalsIgnoreCase(UrlBuilder.getMessage(translate("otherAddressRemoveButtonText"))));
            }
    }

    public void addNewAddress() {
        waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_BUTTON, 5);
        clickUsingJS(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_BUTTON);
        switch (UrlBuilder.getLocale()) {
            case "veloza":
                enterText(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_TELEPHONE,"123456789");
                waitClearAndEnterText(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_TELEPHONE,"9876543210");
                enterText(ADDRESS_SEARCH_VUSEDE, "12 Albert Street");
                waitForExpectedElement(ADDRESS_SEARCH_RESULT, 5).click();
                if(!isElementDisplayedBySeconds(STREET_ADDRESS_VELOZA,2))
                    clickUsingJS(MANUAL_ADDRESS_ENTRY_VELOZA);
                enterText(STREET_ADDRESS_VELOZA, "12 Albert Street");
                clickUsingJS(STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON);
                clickUsingJS(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE);
                break;
            case "velobe":
        waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_TELEPHONE).sendKeys(UrlBuilder.getMessage("telephone"));
               // enterText(PHONE_NUMBER_PAYMENTPAGE, "519340985");
                waitForAjaxElementNotToBePresent(webDriver, 10);
                enterText(STREETADDRESSLINE1_PAYMENTPAGE, "Burchtstraat 7");
                enterText(STREETADDRESSLINE2_PAYMENTPAGE, "11");
                enterText(STREETADDRESSLINE3_PAYMENTPAGE, "11");
                enterText(CITY_PAYMENT, "Aalst");
                enterText(POSTCODE_PAYMENTPAGE, "9300");
              //  waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE, 5);
                clickUsingJS(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE);
    break;
            case "velopl":
                waitForAjaxElementNotToBePresent(webDriver, 10);
                waitClearAndEnterText(PHONE_NUMBER_PAYMENTPAGE, "+48519340985");
                waitForAjaxElementNotToBePresent(webDriver, 10);
                enterText(STREETADDRESSLINE1_PAYMENTPAGE, "Listopada 137");
                enterText(STREETADDRESSLINE2_PAYMENTPAGE, "11");
                enterText(STREETADDRESSLINE3_PAYMENTPAGE, "123");
                enterText(CITY_PAYMENT, "Sosnowiec");
                enterText(POSTCODE_PAYMENTPAGE, "26-609");
                waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE, 5);
                clickUsingJS(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE);
                break;
        }}

    public void addNewAddressPayment() {
        registrationPage.populateAddressFieldsPayment();
    }

    public void clickOnEditDefaultBillingAddress() {
        waitForAjaxElementNotToBePresent(webDriver,5);
        clickUsingJS(AVALANCHE_ADDRESS_BOOK_DEFAULT_BILLING_ADDRESS_EDIT_BUTTON);
    }

    public void clickOnEditDefaultDeliveryAddress() {
        clickUsingJS(AVALANCHE_ADDRESS_BOOK_DEFAULT_SHIPPING_ADDRESS_EDIT_BUTTON);
    }

    public void iAmDirectedToTheEditAddressPage() {
        assertThat(waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_EDIT_ADDRESS_MODAL).isDisplayed())
                .as("ERROR iAmDirectedToTheEditAddressPage: edit address modal not displayed").isTrue();
    }

    public void GoBackToMyAddressBook() {
        clickUsingJS(AVALANCHE_ADDRESS_BOOK_EDIT_ADDRESS_MODAL_BACK_TO_ADDRESSES_CLOSE);
    }


    public void thereIsACtaToSaveMyChanges() {
        WebElement modal = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_EDIT_ADDRESS_MODAL);
        String expectedSaveAddressButtonText = UrlBuilder.getMessage(translate("saveAddressButtonText"));
        WebElement button = modal.findElement(By.cssSelector("footer > button"));
        String actualSaveAddressButtonText = button.getText();
        assertThat(expectedSaveAddressButtonText.equalsIgnoreCase(actualSaveAddressButtonText))
                .as("ERROR thereIsACtaToSaveMyChanges: expected save button text was " + expectedSaveAddressButtonText + " but I got " + actualSaveAddressButtonText).isTrue();
        assertThat(button.isEnabled()).as("ERROR thereIsACtaToSaveMyChanges: expected save button to be enabled but it wasnt").isTrue();
    }

    public void clickOnSaveChanges() {
        waitForAjaxElementNotToBePresent(webDriver,3);
        enterText(STREET_ADDRESS_VELOZA, "12 Albert Street");
        selectValueFromDropDownByby(UrlBuilder.getMessage("Province.key"), province);
        clickUsingJS(AVALANCHE_ADDRESS_BOOK_DEFAULT_ADDRESS_EDIT_BUTTON);
        clickUsingJS(AVALANCHE_ADDRESS_BOOK_SAVE_NEW_ADDRESS_BUTTON);
    }


    public void clickOnEditOtherAddress() {
        waitForElementToAppearAndDisappear(LOADER,2,5);
        List<WebElement> otherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        if (otherAddresses.size() == 0) {
            addNewAddress();
            otherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        }
        Random rand = new Random();
        waitForAjaxElementNotToBePresent(webDriver,3);
        int randomAddressIndex = rand.nextInt(otherAddresses.size());
        clickUsingJS(otherAddresses.get(randomAddressIndex).findElement(By.cssSelector("#edit_address")));
    }

    public void clickOnRemoveOtherAddress() {
        List<WebElement> otherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        if (otherAddresses.size() == 0) {
            addNewAddress();
            otherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        }
        scenarioContext.setContext(Context.NUMBER_OF_OTHER_ADDRESSES, otherAddresses.size());
        clickUsingJS(otherAddresses.get(0).findElement(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS_REMOVE_BUTTON));
    }

    public void iAmAskedIfIAmSure() {
        waitForElementToAppearAndDisappear(LOADER,2,5);
        WebElement modal = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL, 20);
        assertThat(modal.isDisplayed()).as("ERROR iAmAskedIfIAmSure: are you sure modal was not displayed").isTrue();
        String expectedAreYouSureText = UrlBuilder.getMessage(translate("removeAreYouSureText"));
        String actualAreYouSureText = waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL_TEXT).getText();
        assertThat(expectedAreYouSureText.equalsIgnoreCase(actualAreYouSureText))
                .as("ERROR iAmAskedIfIAmSure: expected are you sure text was " + expectedAreYouSureText + " but I got " + actualAreYouSureText).isTrue();

    }

    public void clickCancelRemove() {
        waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL_CANCEL_BUTTON).click();
    }

    public void otherAddressesUnaltered() {
        int previousOtherAddresses = (int) scenarioContext.getContext(Context.NUMBER_OF_OTHER_ADDRESSES);
        int currentOtherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS).size();
        assertThat(previousOtherAddresses == currentOtherAddresses)
                .as("ERROR otherAddressesUnaltered: expected number of other addresses was " + previousOtherAddresses + " but there are " + currentOtherAddresses).isTrue();
    }

    public void clickYesIAmSure() {
        try {
            waitForExpectedElement(AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL_CONFIRM_BUTTON).click();
        } catch (Exception e) {
            clickUsingJS(AVALANCHE_ADDRESS_BOOK_REMOVE_ADDRESS_MODAL_CONFIRM_BUTTON);
        }
    }

    public void theOtherAddressesAreReducedByOne() {
        int previousOtherAddresses = (int) scenarioContext.getContext(Context.NUMBER_OF_OTHER_ADDRESSES);
        int currentOtherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS).size();
        assertThat(previousOtherAddresses - currentOtherAddresses == 1)
                .as("ERROR otherAddressesUnaltered: expected number of other addresses was " + (previousOtherAddresses - 1) + " but there are " + currentOtherAddresses).isTrue();
    }

    public void removeOtherAddressUntilOne()
    {
        int currentOtherAddresses = webDriver.findElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS).size();
        if (currentOtherAddresses == 0){
            addNewAddress();
        }
        while(currentOtherAddresses > 1){
            clickOnRemoveOtherAddress();
            iAmAskedIfIAmSure();
            clickYesIAmSure();
            currentOtherAddresses--;
        }
    }

    public boolean savedPaymentMethods() {
        List<WebElement> savedCardsEmptyMessage = webDriver.findElements(AVALANCHE_SAVED_PAYMENT_METHODS_NO_SAVED_CARDS_TEXT);
        return savedCardsEmptyMessage.size() == 0;
    }

    public void deleteSavedPaymentmethods() {
        List<WebElement> savedPaymentMethods = webDriver.findElements(AVALANCHE_SAVED_PAYMENT_METHOD);
        while (savedPaymentMethods.size() > 0) {
            try {
                savedPaymentMethods.get(0).findElement(AVALANCHE_SAVED_CARD_DELETE).click();
            } catch (Exception e) {
                clickUsingJS(savedPaymentMethods.get(0).findElement(AVALANCHE_SAVED_CARD_DELETE));
            }
            waitForExpectedElement(AVALANCHE_SAVED_CARD_DELETE_CONFIRM, 5).click();
            savedPaymentMethods = webDriver.findElements(AVALANCHE_SAVED_PAYMENT_METHOD);
        }
    }

    public void iAmAbleToSeeAllMySavedMethodsOnThisPage() {
        String expectedSavedPaymentMethodsHeading = UrlBuilder.getMessage(translate("savedPaymentMethodsHeading"));
        String actualSavedPaymentMethodsHeading = waitForExpectedElement(AVALANCHE_SAVED_PAYMENT_METHODS_HEADING).getText();
        assertThat(expectedSavedPaymentMethodsHeading.equalsIgnoreCase(actualSavedPaymentMethodsHeading))
                .as("ERROR iAmAbleToSeeAllMySavedMethodsOnThisPage: expected page heading for saved cards was " + expectedSavedPaymentMethodsHeading + " but I got " + actualSavedPaymentMethodsHeading).isTrue();
    }

    public void clickOnEditPaymentMethdodCta() {
        String expectedUpdatePaymentMethodLinkText = UrlBuilder.getMessage("updatePaymentMethodText-" + scenarioContext.getContext(Context.LANGUAGE));
       clickUsingJS(webDriver.findElements(AVALANCHE_SAVED_PAYMENT_METHOD).get(0).findElement(By.linkText(expectedUpdatePaymentMethodLinkText)));
    }



    public void validateSavedPaymentMethodActions() {
        String expectedUpdatePaymentMethodLinkText = UrlBuilder.getMessage("updatePaymentMethodText-" + scenarioContext.getContext(Context.LANGUAGE));
        String expectedDeletePaymentMethodLinkText = UrlBuilder.getMessage("deletePaymentMethodText-" + scenarioContext.getContext(Context.LANGUAGE));
        List<WebElement> savedPaymentMethods = webDriver.findElements(AVALANCHE_SAVED_PAYMENT_METHOD);
        for (WebElement savedPaymentMethod: savedPaymentMethods) {
            assertThat(savedPaymentMethod.findElement(By.linkText(expectedUpdatePaymentMethodLinkText)).isDisplayed())
                    .as("ERROR validateSavedPaymentMethodActions: could't find expected update link " + expectedDeletePaymentMethodLinkText).isTrue();
            assertThat(savedPaymentMethod.findElement(By.linkText(expectedDeletePaymentMethodLinkText)).isDisplayed())
                    .as("ERROR validateSavedPaymentMethodActions: could't find expected delete link " + expectedDeletePaymentMethodLinkText).isTrue();
        }
    }

    public void iAmRedirectedToTheEditPaymentMethodPage() {
        String actualPageTitle = waitForExpectedElement(AVALANCHE_SAVED_CARDS_PAGE_TITLE).getText();
        String expectedPageTitle = UrlBuilder.getMessage("updatePaymentMethodPageTitle-" + scenarioContext.getContext(Context.LANGUAGE));
        assertThat(expectedPageTitle.equalsIgnoreCase(actualPageTitle))
                .as("ERROR iAmRedirectedToTheEditPaymentMethodPage: expected page title was " + expectedPageTitle + " but I got "+ actualPageTitle).isTrue();
    }

    public void iCanAddAllTheDetailsAndSaveMyPaymentMethod() {
        String nameSelector;
        String monthSelector;
        String yearSelector;
        String finalExpiryMonth;
        String finalExpiryYear;
        String expiryDetails;
        String newCardExpiryMonth = null;
        String newCardExpiryYear = null;
        int currentYear;
        List<WebElement> months;
        List<WebElement> years;
        Select selectYear;
        List<WebElement> savedPaymentMethods;
        String newCardName = RandomStringUtils.randomAlphabetic(8);
        waitForExpectedElement(AVALANCHE_SAVED_CARD_CARD_HOLDER_NAME).clear();
        waitForExpectedElement(AVALANCHE_SAVED_CARD_CARD_HOLDER_NAME).sendKeys(newCardName);
        Select selectMonth = new Select(webDriver.findElement(AVALANCHE_SAVED_CARD_EXPIRY_MONTH_SELECTOR));
        months = selectMonth.getOptions();
        for (WebElement month : months) {
            if (!month.isSelected() && !month.getAttribute("value").equals("")) {
                newCardExpiryMonth = stripLeadingZeros(month.getAttribute("value"));
                month.click();
                break;
            }
        }
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        selectYear = new Select(webDriver.findElement(AVALANCHE_SAVED_CARD_EXPIRY_YEAR_SELECTOR));
        years = selectYear.getOptions();
        for (WebElement year : years) {
            if (!year.isSelected() && !year.getAttribute("value").equals("") && Integer.parseInt(year.getAttribute("value")) > currentYear) {
                newCardExpiryYear = year.getAttribute("value");
                year.click();
                break;
            }
        }
        clickByElementByQueryJSExecutor(AVALANCHE_SAVED_CARD_EDIT_SAVE_BUTTON);
        savedPaymentMethods = webDriver.findElements(AVALANCHE_SAVED_PAYMENT_METHOD);
        expiryDetails = savedPaymentMethods.get(0).findElement(AVALANCHE_SAVED_CARD_EXPIRY_DETAILS).getText();
        finalExpiryMonth = expiryDetails.split(":")[1].split("/")[0].trim();
        finalExpiryYear = expiryDetails.split(":")[1].split("/")[1].trim();
        assertThat(finalExpiryMonth.equals(newCardExpiryMonth))
                .as("ERROR iCanAddAllTheDetailsAndSaveMyPaymentMethod: expected saved expiry month to be " + newCardExpiryMonth + " but I got " + finalExpiryMonth).isTrue();
        assertThat(finalExpiryYear.equals(newCardExpiryYear))
                .as("ERROR iCanAddAllTheDetailsAndSaveMyPaymentMethod: expected saved expiry year to be " + newCardExpiryYear + " but I got " + finalExpiryYear).isTrue();
    }

    public String stripLeadingZeros(final String data)
    {
        final String strippedData;
        strippedData = StringUtils.stripStart(data, "0");
        return StringUtils.defaultString(strippedData, "0");
    }

    public String escapeMetaCharacters(String inputString){
        final String[] metaCharacters = {"'","é"};
        for (String metaCharacter : metaCharacters) {
            if (inputString.contains(metaCharacter)) {
                inputString = inputString.replace(metaCharacter, "\\" + metaCharacter);
            }
        }
        return inputString;
    }

    public void clickOnEditPassword() {
        clickUsingJS(AVALANCHE_CHANGE_PASSWORD_CHECKBOX);
    }

    public void validateEditablePasswordFields() {
        String expectedChangePasswordHeading = UrlBuilder.getMessage("changePasswordHeading-" + scenarioContext.getContext(Context.LANGUAGE));
        String actualChangePasswordHeading = waitForExpectedElement(AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_HEADING).getText();
        assertThat(expectedChangePasswordHeading.equalsIgnoreCase(actualChangePasswordHeading))
                .as("ERROR validateEditablePasswordFields: expected change password heading was " + expectedChangePasswordHeading + " but I got " + actualChangePasswordHeading).isTrue();
        assertThat(waitForExpectedElement(AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_CURRENT_PASSWORD_FIELD).isDisplayed())
                .as("ERROR validateEditablePasswordFields: could not find current password field").isTrue();
        waitForExpectedElement(AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_NEW_PASSWORD_FIELD).sendKeys("abc");
        assertThat(waitForExpectedElement(AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_WEAK).isDisplayed())
                .as("ERROR validateEditablePasswordFields: added a short password but did not get a weak message").isTrue();
        assertThat(waitForExpectedElement(AVALANCHE_MY_DETAILS_CHANGE_PASSWORD_CONFIRM_PASSWORD_FIELD).isDisplayed())
                .as("ERROR validateEditablePasswordFields: confirm password was not displayed").isTrue();

    }

    public void closeAddressModal() {
        clickByElementByQueryJSExecutor(AVALANCHE_ADDRESS_BOOK_CANCEL_EDIT_BUTTON);
    }

    public void ensureAllOtherAddressesViewable(String target) {
        List<WebElement> otherAddressesDisplayed = waitForExpectedElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        while (otherAddressesDisplayed.size() > Integer.parseInt(target)) {
            try {
                otherAddressesDisplayed.get(0).findElement(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS_REMOVE_BUTTON).click();
            } catch (Exception e) {
                clickUsingJS(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS_REMOVE_BUTTON);
            }
            clickYesIAmSure();
            otherAddressesDisplayed = waitForExpectedElements(AVALANCHE_ADDRESS_BOOK_OTHER_ADDRESSES_ADDRESS);
        }
        waitForElementToAppearAndDisappear(LOADER, 1 , 5);
    }
}
