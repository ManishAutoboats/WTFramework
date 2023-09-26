package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class VuseCarePage extends PageObject {

    private HomePage homePage;
    private PLP plp;
    private static final By REGISTER_FOR_VUSE_CARE_BUTTON = By.cssSelector("input.action.save.register.primary");
    public static final By VUSE_CARE_NOT_REGISTERED_MESSAGE = By.cssSelector(".message-success.success.message");
    public static final By VUSE_CARE_SUCCESS_MESSAGE = By.cssSelector("div[data-ui-id='message-success']");
    public static final By REGISTERED_FORVUSE_CARE = By.cssSelector("p.description-text");
    public static final By OPT_OUT_VUSE_CHECKBOX = By.cssSelector("input.warranty_consent.checkbox");
    public static final By DISABLED_CONFIRM_BUTTON = By.cssSelector("input.action.save.primary.confirm.disabled");
    public static final By ENABLED_CONFIRM_BUTTON = By.cssSelector("input.action.save.primary.confirm");
    public static final By YES_CANCEL_VUSE_CARE_BUTTON = By.cssSelector("button.action-primary.action-accept");
    public static final By NO_CANCEL_VUSE_CARE_BUTTON = By.cssSelector("button.action-secondary.action-dismiss");
    private static final By SUBSCRIBE_VUSE = By.xpath("//a[contains(text(),'Subscribe')]");
    private static final By SUBSCRIBE_PODS = By.cssSelector("#maincontent > div.columns > div > div:nth-child(10) > div > div.pagebuilder-column-group > div:nth-child(1) > div > div > div:nth-child(8) > div > div > div > a > span");
    private static final By SUBSCRIBE_TO_VUSE_CHECKBOX = By.cssSelector("input#option_subscription");
    private static final By LEARN_MORE_VUSE = By.cssSelector("span[class*='subscribepro-learn-more']");
    public static final By SUBSCRIBE_ITEM_QUANTITY = By.cssSelector("span.cartridges_quantity");
    public static final By SUBSCRIBE_ITEM_QUANTITY_MODAL_WINDOW = By.cssSelector("span.cartridges_quantity");
    public static final By BRONZE_SUBSCRIPTON_ACTIVE = By.cssSelector("tr.cartridgestype.cartridgestype_Bronze.active,#subs-plp-modal > div:nth-child(1) tr.cartridgestype.cartridgestype_Bronze.active");
    public static final By SILVER_SUBSCRIPTON_ACTIVE = By.cssSelector("tr.cartridgestype.cartridgestype_Silver.active");
    public static final By GOLD_SUBSCRIPTON_ACTIVE = By.cssSelector("tr.cartridgestype.cartridgestype_Gold.active");
    public static final By LEARN_MORE_HEADING = By.cssSelector("h2.modal-top-heading");
    public static final By BASKET_QUANTITY_ON_PAGE = By.cssSelector("span.counter-number");
    public static final By BASKET_QUANTITY = By.cssSelector("span.counter-number");
    private static final By ADD_ITEM_QUANTITY = By.cssSelector("span.qty-modifier.plus.button-plus-1.icon-plus");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button#product-addtocart-button");
    private static final By CLOSE_MINICART = By.cssSelector("button#btn-minicart-close");
    public static final By LINK_REGISTER_NOW=By.cssSelector("a.pagebuilder-button-primary[href='customer/account/']");
    public static final By LINK_CREATE_PROFILE=By.cssSelector("a.pagebuilder-button-secondary[href='customer/account/']");
    public static final By FAQS_SECTION_ACCORDIAN=By.cssSelector("div.collapsible-title");
    public static final By FAQS_ACCORDIAN_EXPAND=By.cssSelector("div.collapsible-title > span");
    public static final By FAQS_SECTION_EXPANDED=By.cssSelector("div.pagebuilder-collapsible.active");
    private static final By VUSECARE_MYACCOUNT=By.cssSelector("div.content.accordion-content.account-nav-content ul.nav.items li.nav.item:nth-child(7) > a:nth-child(1)");
    public static final By TERMS_AND_CONDITIONS_CHECKBOX=By.cssSelector("input#consent_terms_condition");
    public static final By SUBSCRIBE_NEWSLETTER_CHECKBOX=By.cssSelector("input#consent_subscribe");
    public static final By TERMS_AND_CONDITIONS_LINK=By.cssSelector("a.warranty-terms-condition");
    public static final By REGISTER_FOR_VUSE_CARE_BTN=By.cssSelector("input.action.save.register.primary");
    public static final By TERMS_CONDITIONS_CONSENT_ERROR=By.cssSelector("div#consent_terms_condition-error");
    public static final By SUBSCRIPTION_OPTION=By.cssSelector("div.option_choice_wrapper_choice.field.choice.subscription");
    public static final By CLOSE_ICON_MODAL_WNDOW=By.xpath("//div[div[div[@class='subscription-modal']]]/header/button");
    public static final By MEDIUM_STRENGTH=By.cssSelector("\n" +
            "div.bat-filter > div > div > div:nth-child(3) > ul > li:nth-child(1) > label > input[type=checkbox]");
    public static final By MEDIUM_STRENGTH_PL=By.cssSelector ("div:nth-of-type(2) > div[role='tabpanel'] > .filter__list > li:nth-of-type(3) > .filter__label");
    public static final By MILD_STRENGTH=By.cssSelector("\n" +
            "div.bat-filter > div > div > div:nth-child(3) > ul > li:nth-child(2) > label > input[type=checkbox]");
    public static final By MILD_STRENGTH_PL=By.cssSelector ("div:nth-of-type(2) > div[role='tabpanel'] > .filter__list > li:nth-of-type(4) > .filter__label");
    public static final By SORT_A_Z=By.cssSelector("div.bat-sort-button > div > ul > li:nth-child(1) > button");
    public static final By SORT_Z_A=By.cssSelector("div.bat-sort-button > div > ul > li:nth-child(2) > button");
    public static final By PL_SORT=By.xpath("/html//main[@id='maincontent']/div[@class='columns']/div/div[@class='toolbar toolbar-products']/div[@class='toolbar-btns__container']/div[1]/select[@class='sort-btn__select']");
    public static final By SORT_PRICE_LOW=By.cssSelector("div.bat-sort-button > div > ul > li:nth-child(3) > button");
    public static final By SORT_PRICE_HIGH=By.cssSelector("div.bat-sort-button > div > ul > li:nth-child(4) > button");
    public static final By PRODUCT_DESCRIPTION=By.cssSelector("div.value");
    public static final By EXTEND_DESCRIPTION_LINK=By.cssSelector("span.toggle-more");
    public static final By EXTENDED_DESCRIPTION=By.cssSelector("div.product.attribute.overview > div > p:nth-child(2)");
    public static final By EXTENDED_DESCRIPTION_VUSEIT=By.cssSelector("div.product.attribute.overview > div > p:nth-child(4)");
    public static final By REDUIRE_DESCRIPTION_LINK=By.cssSelector("div.product-info-main > div.product.attribute.overview > div > span");


    public VuseCarePage(HomePage homePage, PLP plp){
        this.plp = plp;
        this.homePage = homePage;
    }

    public void userClickOnTheOption(String option) {
        switch (option) {
            case "Vuse Care":
                waitForExpectedElement(VUSECARE_MYACCOUNT, 5).click();
                break;
            case "Terms and condition":
                waitForExpectedElement(TERMS_AND_CONDITIONS_LINK, 5).click();
                break;
            case "Yes":
                waitForExpectedElement(YES_CANCEL_VUSE_CARE_BUTTON, 5).click();
                break;
            case "Medium":
                if ("velopl".equals(UrlBuilder.getLocale())) {
                    clickByElementByQueryJSExecutor(MEDIUM_STRENGTH_PL);
                } else {
                    waitForExpectedElement(MEDIUM_STRENGTH, 5).click();
                }
                break;
            case "Mild":
                if ("velopl".equals(UrlBuilder.getLocale())) {
                    clickByElementByQueryJSExecutor(MILD_STRENGTH_PL);
                } else {
                    waitForExpectedElement(MILD_STRENGTH, 5).click();
                }
                break;
            case "Name A-Z":
                if ("velopl".equals(UrlBuilder.getLocale())) {
                    Select se = new Select(getWebDriver().findElement(PL_SORT));
                    se.selectByValue("name-asc");
                } else {
                    waitForExpectedElement(SORT_A_Z, 5).click();
                }
                break;
            case "Name Z-Z":
                if ("velopl".equals(UrlBuilder.getLocale())) {
                    Select se = new Select(getWebDriver().findElement(PL_SORT));
                    se.selectByValue("name-desc");
                } else {
                    waitForExpectedElement(SORT_Z_A, 5).click();
                }
                break;
            case "Price low":
                if ("velopl".equals(UrlBuilder.getLocale())) {
                    Select se = new Select(getWebDriver().findElement(PL_SORT));
                    se.selectByValue("price-asc");
                } else {
                    waitForExpectedElement(SORT_PRICE_LOW, 5).click();
                }
                break;
            case "Price high":
                if ("velopl".equals(UrlBuilder.getLocale())) {
                    Select se = new Select(getWebDriver().findElement(PL_SORT));
                    se.selectByValue("price-desc");
                } else {
                    waitForExpectedElement(SORT_PRICE_HIGH, 5).click();
                }
                break;
            case "Subscribe":
                waitForExpectedElement(SUBSCRIBE_VUSE, 5).click();
                break;
            case "Pods subscribe":
                homePage.closeVuseAlertIfPresent();
                waitForExpectedElement(SUBSCRIBE_PODS, 8);
                clickIndexElementByQueryJSExecutor(SUBSCRIBE_PODS,1);
                break;
            case "filter":
                switch (UrlBuilder.getLocale()) {
                    case "veloza":
                    case "velobe":
                        waitForExpectedElement(PLP.FILTER_OPTION_VELOZA,5);
                        clickFirstElementByQueryJSExecutor(PLP.FILTER_OPTION_VELOZA);
                        break;
                    default:
                waitForExpectedElement(PLP.FILTER_OPTION,5);
                clickFirstElementByQueryJSExecutor(PLP.FILTER_OPTION);
                }
                break;
            case "sorting":
                switch (UrlBuilder.getLocale()) {
                    case "veloza":
                        waitForExpectedElement(PLP.SORTING_OPTION_VELOZA,5);
                        clickFirstElementByQueryJSExecutor(PLP.SORTING_OPTION_VELOZA);
                        break;
                    case "velopl":
                        waitForExpectedElement(PLP.SORTING_OPTION_VELOPL,5);
                        clickFirstElementByQueryJSExecutor(PLP.SORTING_OPTION_VELOPL);
                        waitForAjaxElementNotToBePresent(getWebDriver(),5);
                        break;
                    default:
                waitForExpectedElement(PLP.SORTING_OPTION,5);
                clickFirstElementByQueryJSExecutor(PLP.SORTING_OPTION);}
                break;
        }
    }

    public void userTryToRegisterForVuseCare() {
        waitForExpectedElement(TERMS_AND_CONDITIONS_CHECKBOX, 5);
        clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_CHECKBOX);
        if (getWebDriver().findElements(SUBSCRIBE_NEWSLETTER_CHECKBOX).size() > 0)
            clickByElementByQueryJSExecutor(SUBSCRIBE_NEWSLETTER_CHECKBOX);
        waitForExpectedElement(REGISTER_FOR_VUSE_CARE_BUTTON, 5).click();
    }

    public void userOptOutAndClickOnConfirmOption() {
        clickByElementByQueryJSExecutor(OPT_OUT_VUSE_CHECKBOX);
        waitForExpectedElement(ENABLED_CONFIRM_BUTTON, 5);
        clickByElementByQueryJSExecutor(ENABLED_CONFIRM_BUTTON);
    }

    public void openLearMorePopup() {
        waitForExpectedElement(SUBSCRIBE_TO_VUSE_CHECKBOX, 5);
        if(getWebDriver().findElements(SUBSCRIBE_TO_VUSE_CHECKBOX).size()>0)
            clickByElementByQueryJSExecutor(SUBSCRIBE_TO_VUSE_CHECKBOX);
        waitForExpectedElement(LEARN_MORE_VUSE, 5);
        clickByElementByQueryJSExecutor(LEARN_MORE_VUSE);
    }

    public int quantityOfItem(By element) {
            int quantity = Integer.parseInt(waitForExpectedElement(element, 5).getText());
            return quantity;
        }

    public void addItemandAddToBasket(int quantity){
        int itemQuantity = 0;
        do {
            waitForExpectedElement(ADD_ITEM_QUANTITY, 5);
            clickByElementByQueryJSExecutor(ADD_ITEM_QUANTITY);
            itemQuantity++;
        }
        while (itemQuantity < quantity);
        waitForExpectedElement(ADD_TO_CART_BUTTON, 5);
        clickByElementByQueryJSExecutor(ADD_TO_CART_BUTTON);

    }

    public void addItembasedOnInput(String quantity) {
        switch (quantity) {
            case "5":
                addItemandAddToBasket(5);
                break;
            case "10":
                addItemandAddToBasket(10);
                break;
            case "15":
                addItemandAddToBasket(15);
                break;
        }
    }

    public void navigateToLearnMore(){
        waitForExpectedElement(CLOSE_MINICART,10);
        openLearMorePopup();
    }

    public void addProductIntoCart() {
        plp.waitForExpectedElement(plp.PRODUCTS_VUSEDE,8);
        jsScrollElementInCenter(waitForExpectedElement(plp.PRODUCT_STRENGTH_SWATCH));
        List<WebElement> products = plp.getWebDriver().findElements(plp.PRODUCTS_VUSEDE);
        WebElement product = products.get(2);
        hoverOnElement(plp.PRODUCT_STRENGTH_SWATCH);
        clickFirstElementByQueryJSExecutor(plp.BUTTON_VUSEUK);
    }

    public void clickSuscriptionRadioButtonIfPresent() {
        if(isElementDisplayedBySeconds(SUBSCRIPTION_OPTION,5)){
            waitForExpectedElement(SUBSCRIPTION_OPTION).click();
        }
    }
}
