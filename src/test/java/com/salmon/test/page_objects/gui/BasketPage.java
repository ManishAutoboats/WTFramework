package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.website.EngravingAttribute;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import cucumber.api.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.page_objects.gui.constants.Locale.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class BasketPage extends PageObject {

    private SoftAssert softAssert;
    private HomePage homePage;
    private ScenarioContext scenarioContext;

    public BasketPage(SoftAssert softAssert, HomePage homePage, ScenarioContext scenarioContext) {
        this.softAssert = softAssert;
        this.homePage = homePage;
        this.scenarioContext = scenarioContext;
    }

    private static final By MINUS_ICON = By.xpath("//div[@class='control qty']//input[contains(@class,'qty-modifier minus')]");
    private static final By PLUS_ICON = By.xpath("//div[@class='control qty']//input[contains(@class,'qty-modifier plus')]");
    private static final By ITEM_PRICE = By.xpath("//td[@class='col subtotal desktop-only']//span[@class='cart-price']//span");
    private static final By SUB_TOTAL_PRICE = By.xpath("//tr[@class='totals sub']//span[@class='price']");
    private static final By DISCOUNT_PRICE = By.xpath("//tr[@class='total-rules']//span[@class='rule-amount']");
    private static final By BRONZE_DISCOUNT = By.xpath("//tr[@class='total-rules']//span[@class='rule-amount']");
    private static final By TOTAL_PRICE = By.xpath("//tr[@class='grand totals incl']//span[@class='price']");
    private static final By BASKET_EMPTY_MESSAGE = By.cssSelector(".cart-empty > div:nth-of-type(1)  .pagebuilder-column-group > .pagebuilder-column > div:nth-of-type(2) > p");
    private static final By COUPON_CODE_FIELD = By.xpath("//div[@class='payment-option-inner']//input[@class='input-text']");
    private static final By CODE_SUBMIT_BTN = By.xpath("//button[@class='action apply action-apply']");
    private static final By ERROR_MESSAGE = By.xpath("//div[@class='invalid-basket-message-content']//p");
    private static final By SUBSCRIPTION_CTA = By.xpath("//div[@class='invalid-basket-message']//div[@class='actions']//button//span");
    private static final By AGREE_CTA = By.xpath("(//footer[@class='modal-footer']//button)[2]");
    private static final By SUBSCRIBE_OPTION = By.xpath("//span[@class='subscribe-save']//parent::label//parent::div//input");
    private static final By ONE_TIME_OPTION = By.xpath("//span[text()='One Time Purchase']//parent::label//parent::div//input");
    private static final By SUBSCRIPTION_TEXT = By.xpath("//tr[@class='total-rules']//span[@class='rule-name']");
    private static final By QUANTITY_PLUS = By.xpath("//div[@class='control quantity-selector']//span[@class='qty-modifier plus button-plus-1 icon-plus']");
    private static final By VIEW_BASKET = By.cssSelector("a.action.viewcart.primary");
    private static final By Click_BASKET_ICON = By.cssSelector("a.action.showcart");
    private static final By PROCEED_TO_CHECKOUT = By.cssSelector(".action.primary.checkout");
    private final static By VIEWCART_ENGRAVING_DETAILS_CONFIRMATION = By.cssSelector("div[class='psn-options active']");
    private final static By INPUT_PROMOCODE = By.cssSelector("input#discount-code");
    private final static By PROMOCODE_SUBMIT = By.cssSelector("button.action.apply.action-apply");
    private final static By PROMO_RULE_APPLIED = By.cssSelector("span.rule-name");
    private final static By REMOVE_PROMOTION = By.cssSelector("button.action.action-cancel.secondary,button.action.apply.action-apply");
    private final static By VIEW_BASKET_LINK_GLO= By.cssSelector("a.viewcart.link,a.action.viewcart.primary");
    private static final By SUBS_SELECTED = By.xpath("//span[text()='One Time Purchase']//parent::label//parent::div//input");
    private static final By CANCEL_COUPON = By.xpath("//button[@class='action action-cancel secondary']//span");
    public final static By QTY_SELECTOR_MINI_BASKET=By.cssSelector("div.control.qty");
    public final static By QTY_SELECTOR_MINI_CART = By.cssSelector("div.details-qty.qty");
    public final static By QTY_SELECTOR_PLUS_BUTTON_BASKET = By.cssSelector("div.field.qty div.control.qty > input:nth-child(3)");
    public final static By QTY_SELECTOR_PLUS_BUTTON_MINI_CART =By.cssSelector("div.product-item-pricing div.details-qty.qty > input:nth-child(4)");
    public final static By QTY_SELECTOR_MINUS_BUTTON_BASKET=By.cssSelector("div.control.qty > input:nth-child(1)");
    public final static By QTY_SELECTOR_MINUS_BUTTON_MINI_CART = By.cssSelector("div.field.qty input:nth-child(1)");
    public final static By QTY_SELECTOR_MINUS_BUTTON_MINI_CART_VUSEUK = By.cssSelector("input[class*='qty-modifier minus button-minus']");
    public final static By QTY_SELECTOR_MINUS_BUTTON_CART_VUSEUK = By.cssSelector("table#shopping-cart-table input[class*='qty-modifier minus button-minus']");
    public final static By QTY_SELECTOR_MINUS_BUTTON_MINI_CART_VUSEDE=By.xpath("//input[contains(@class,'qty-modifier minus button-minus')]");
    public final static By INPUT_QTY_MINI_BASKET = By.cssSelector("input.qty.cart-qty");
    public final static By INPUT_QTY_MINI_CART = By.cssSelector("input.item-qty.cart-item-qty.qty");
    public final static By BAKSET_EMPTY_STATUS = By.cssSelector("span.counter.qty.empty");
    public final static By PRODUCT_REMOVAL_CONFIRM_POPUP = By.cssSelector("a.action.action-delete,button.action-primary.action-accept");
    public final static By PRODUCT_REMOVAL_CONFIRM_POPUP_CART=By.xpath("//*[contains(@class,'modal-inner-wrap')]//header/following::p[@class='modal-text']");
    private final static By INPUT_QTY_GLOPL = By.cssSelector("div.control.qty input");
    private final static By PRODUCT_REMOVAL_CANCEL_BUTTON_CART = By.cssSelector("div.action-items-container > div > button.action.secondary");
    private final static By PRODUCT_REMOVAL_CANCEL_BUTTON_BASKET=By.cssSelector("button.action-secondary.action-dismiss");
    private final static By PRODUCT_REMOVAL_CANCEL_BUTTON_CART_FR=By.cssSelector(".action-items-container .actions .action.secondary");
    private final static By PRODUCT_REMOVE_BUTTON_CART = By.cssSelector("div.action-items-container > div > a.action.action-delete > span");
    private final static By PRODUCT_REMOVE_BUTTON_CART_FR=By.cssSelector(".action.action-delete");
    private final static By PRODUCT_REMOVE_BUTTON_BASKET = By.cssSelector("button.action-primary.action-accept > span");
    private final static By PRODUCT_REMOVE_BUTTON_BASKET_KZ = By.cssSelector("button.action.primary.remove.button-flavoured");
    public final static By FREE_GIFT_LABEL= By.cssSelector("div.cart.item.message.notice");
    public final static By PRODUCTS_IN_BASKET=By.xpath("//td[@class='col product-item-details']//strong[@class='product-item-name']");
    private static final By FINAL_CHARGES_CART_GLO=By.cssSelector("div.minicart_grandtotal > div.amount.price-container,span.cart_details > span.price");
    private static final By INPUT_ITEM_QUANTITY_CART = By.cssSelector("div.control.qty input:nth-child(2)");
    private static final By PLUS_ICON_CART = By.cssSelector("div.control.qty input:nth-child(3)");
    private static final By MINUS_SIGN_MINICART = By.cssSelector("input[class^='qty-modifier minus button-minus']");
    private static final By ACCEPT_OPTION_REMOVE_MINICART = By.cssSelector("button.action-primary.action-accept");
    private static final By CANCEL_OPTION_REMOVE_MINICART = By.cssSelector("button.action-secondary.action-dismiss");
    private static final By EMPTY_BASKET_MESSAGE = By.cssSelector(".subtitle.empty");
    private static final By MINUS_SIGN_BASKET_PAGE = By.cssSelector("div.control.qty input[class^='qty-modifier minus button-minus']");
    private static final By ACCEPT_OPTION_REMOVE_BASKET_PAGE = By.cssSelector("a.action.action-delete");
    private static final By CANCEL_OPTION_REMOVE_BASKET_PAGE = By.cssSelector("div.actions>button.action.secondary:not([id='btn-entry-age-allow'])");
    private final static By MINICART_BACK_BUTTON = By.cssSelector("button#btn-minicart-close");
    private final static By BASKET_CLOSE_SLIDING_PANEL_BUTTON_PL = By.cssSelector("button#btn-minicart-close");
    private By ToCheckoutBtn = By.cssSelector("button.action.primary.checkout");
    private final static By M_CHECKOUT_BUTTON = By.cssSelector("div.cart-summary button.action.primary.checkout");
    private final static By M_VIEWBASKET_BUTTON_FR=By.xpath("(.//span[contains(@data-bind,'View Basket')])[2]");
    private final static By M_CHECKOUT_BUTTON_FR = By.cssSelector("button[data-role='proceed-to-checkout']");
    private final static By M_CHECKOUT_BUTTON_IE = By.xpath("(.//button[@data-role='proceed-to-checkout'])[2]");
    private final static By M_CHECKOUT_BUTTON_DK = By.xpath("(.//button[@id='top-cart-btn-checkout'])[2]");
    private final static By M_CHECKOUT_BUTTON_MX = By.xpath("(.//button[@id='top-cart-btn-checkout'])[3]");
    public final static By M_CHECKOUT_BUTTON_CO = By.xpath("(.//button[@id='top-cart-btn-checkout'])[4]");
    private final static By T_CHECKOUT_BUTTON_IE = By.cssSelector("#top-cart-btn-checkout");
    private final static By TOGGLE_BUTTON_VYPEIE = By.cssSelector("span.action.nav-toggle>i");
    private final static By MY_BASKET_VYPEIE_IPHONE = By.cssSelector("div.burger-menu div:nth-child(1) ol:nth-child(1) li:nth-child(6) div.burger-menu-item > a:nth-child(1)");
    public final static By LYFT_EN_LANGUAGE= By.cssSelector("[lang='en']");
    public final static By LYFT_SV_LANGUAGE=By.cssSelector("[lang='sv']");
    public final static By PRODUCT_REMOVAL_CONFIRM_POPUP_CART_VUSEUK = By.cssSelector("footer.modal-footer");
    public final static By PRODUCT_PRICE_UK = By.cssSelector("tr.totals.sub > td");
    public final static By DISCOUNT_PRICE_UK = By.cssSelector("tr.total-rules > td.amount > span");
    public final static By VAT_UK = By.cssSelector("tr.totals-tax-details > td");
    public final static By TOTAL_PRICE_UK = By.cssSelector("tr.grand.totals.incl > td");
    private final static By SUBSCRIPTION_DISCOUNT = By.cssSelector("tr.total-rules.amasty-rules");
    private final static By COMBO_DISCOUNT = By.cssSelector("tr.total-rules.amasty-rules");
    public final static By DISCOUNT_FIELD = By.cssSelector("#discount-code");
    public final static By APPLY_DISCOUNT = By.cssSelector("#discount-form > div > div > div > button");
    public final static By DISCOUNT_FIELD_LYFTSE = By.cssSelector("#coupon_code,#discount-code");
    public final static By APPLY_DISCOUNT_LYFTSE = By.cssSelector("button.action.apply.primary,button.action.apply.action-apply");
    public static final By COUPONCODE_MESSAGE_LYFTSE=By.cssSelector("div[data-ui-id='checkout-cart-validationmessages-message-error']");
    private static final By FINAL_CHARGES_CART_ZA=By.cssSelector("span.cart_details > span");
    private final static By INCREASE_BUTTON = By.cssSelector("div.product-options-bottom > div.box-tocart > div > div.box-tocart__swatch-qty > div.field.qty > div > span.qty-modifier.plus.button-plus-1.icon-plus");
    private final static By LOADING_CIRCLE = By.cssSelector("img[alt='Laddar...']");
    private final static By INCREASE_BUTTON_BASKET = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[3]/div[2]/div[1]/div/input[3]");
    public final static By SUCCESS_MESSAGE= By.cssSelector("div.page.messages");
    public final static By QUANTITY_EXCEEDED_MESSAGE= By.cssSelector("div.cart-messages > div.page > div:nth-child(2) > div.messages > div.message-error > div");
    public final static By PRODUCT_BASKET= By.cssSelector("#shopping-cart-table > tbody > tr");
    private final static By START_YOUR_SHOPPING = By.cssSelector("div.cart-empty > div:nth-child(5) > div > div > div > div:nth-child(4) > div > a");
    public final static By GLO_DE_HOMEPAGE = By.cssSelector("div.glo_de--hero-banner > div.pagebuilder-column-group");
    public final static By PRODUCT_IN_BASKET= By.cssSelector("#mini-cart > li > div > div");
    public final static By ADD_TOCART_LOYALTY= By.cssSelector("div[data-loyalty-product='1'] form");
    private final static By QUANTITY_BUTTON = By.cssSelector(".qty-btn");
    public final static By BASKET_CHOOSE_YOUR_DEVICE_CTA = By.cssSelector(".action.choose-device.primary");
    private final static By FREE_DEVICE_POPUP = By.cssSelector("aside:nth-of-type(1)  .modal-content");
    private final static By FREE_GLOIT_ACCESSORY = By.cssSelector(".slick-track > div:nth-of-type(2)");
    private final static By SELECT_FREE_DEVICE_COLOUR = By.cssSelector("#free-device-caraousel [data-slick-index='0'] div.swatch-option.color");
    private final static By ADD_TO_CART_FREE_DEVICE = By.cssSelector("#free-device-caraousel [data-slick-index='0'] .free-device-modal");
    private final static By ADD_TO_CART_FREE_ACCESSORY = By.cssSelector("div:nth-of-type(2) > form[method='post']  button[title='Aggiungi al carrello']");
    public final static By INPUT_QTY_BASKET = By.cssSelector(".control.qty > label > input");

    public final static By QTY_DROPDOWN_SELECT_BASKET_PAGE =By.cssSelector("div.control.qty div.quantity-dropdown-wrapper > div.quantity-dropdown div.qty-btn");
    private final static By QTY_DROPDOWN_SELECT_REMOVE_BASKET_PAGE=By.xpath("//div[@class='qty-btn active']//following::li[@class='remove-option']");
    public final static By QTY_DROPDOWN_SELECT_MINI_CART = By.cssSelector("div.qty-btn");
    public final static By QTY_DROPDOWN_SELECT_BASKET_PAGE_VUSEDE = By.cssSelector("div.cart-actions > div.field.qty > div.control.qty > div.quantity-selector-box > div.quantity-dropdown-wrapper > div.quantity-dropdown > div.qty-btn");
    private final static By QTY_DROPDOWN_SELECT_REMOVE_MINICART_VUSEIT=By.cssSelector("li.remove-option");
    private final static By QTY_DROPDOWN_SELECT_REMOVE_MINICART=By.cssSelector("li.remove-option.even");
    private final static By QTY_DROPDOWN_SELECT_MORE_VALUE_OPTION_MINICART=By.cssSelector("li.more-value-option");
    private final static By QTY_DROPDOWN_MORE_VALUE_MINI_CART=By.xpath("//div[@class='qty-btn active']//following::li[contains(@class,'more-value-option')]");
    public final static By QTY_DROPDOWN_MORE_VALUE_BASKET_PGE=By.xpath("//li[@class='more-value-option'][contains(@id,'cart-item-more-option')]");
    public final static By QTY_UPDATE_LINK_MINI_CART=By.cssSelector("div.update-lbl > span");
    public final static By QTY_UPDATE_LINK_MINI_CART_VUSEMX=By.cssSelector("div.product-item-pricing.qty-updatecheck > div.details-qty.qty > input.qty-modifier.plus");
    public final static By QTY_UPDATE_LINK_BASKET_PAGE=By.cssSelector("div.cart-update-qty-wrapper.update-qty-wrapper-show div.update-lbl > span:nth-child(1)");
    private final static By BASKET_TILES=By.cssSelector("#mini-cart > li");
    public final static By QTY_DROPDOWN_ACTIVE=By.xpath("//div[@class='qty-btn active']");
    public final static By QTY_TITLE_TEXT=By.cssSelector("h1.page-title > span");
    public final static By RELATED_PRODUCT_ADD_TO_CART_PL=By.cssSelector("div[class*='slick-slide'] button");
    public final static By RELATED_PRODUCT_PRODUCT_IMAGE_PL=By.cssSelector("a.product.photo.product-item-photo");
    public final static By BASKET_ITEM_COUNT=By.cssSelector("span.counter-label");
    public final static By RELATED_PRODUCT_NAME=By.cssSelector("div.product.name");
    private final static By RELATED_PRODUCT_NAME_PDP_HEADER=By.cssSelector("span.heading");
    public final static By RELATED_PRODUCT_ADD_TO_CART=By.cssSelector("div.product-main-info button");
    public final static By RELATED_PRODUCT_PRODUCT_IMAGE=By.cssSelector("a.product.photo.product-item-photo");
    public final static By PLP_MINI_CART_IMAGE=By.cssSelector("button > i.bat-icon.icon-bag");
    public final static By PLP_MINI_CART_PRICE=By.cssSelector("button > span.bat-header-cart-label");
    public static final By AVALANCHE_CONFIRMATION_MESSAGE_BE = By.cssSelector("body > div.bat-wrapper > div > div > div:nth-child(3) > div > div > bat-messagebar-default > div > div > div.bat-messagebar--default-message.active > p:nth-child(3) > span > a");
    private final static By PRODUCT_ITEM=By.cssSelector("div.columns > div > div.cart-container");
    private final static By COUNT_VALUE=By.xpath("/html/body/div[5]/main/div[1]/div/div[4]/form/div[1]/table/tbody/tr/td[3]/div[4]/div[1]/div/div/div[1]/div/div/span");
    private final static By COUNT_VALUE_VUSEIT=By.cssSelector("div.field.qty div.qty-btn > span");

    private final static By TOTAL_CART_VALUE=By.cssSelector("#cart-totals > div > table > tbody > tr.grand.totals.incl > td");


    // Avalanche
    public final static By BASKET_ITEM = By.cssSelector("#shopping-cart-table > tbody > tr");
    public final static By AVALANCHE_BASKET_ITEM_NAME_VELOBE = By.cssSelector(".product-item-name");
    public final static By AVALANCHE_BASKET_ITEM_NAME_VELOPL = By.cssSelector("#shopping-cart-table > tbody > tr > td.col.product-item-details > strong > a");
    public static final By BASKET_ITEM_IMAGE = By.cssSelector("img");
    public static final By BASKET_ITEM_NAME_VELOPL = By.cssSelector("#shopping-cart-table > tbody > tr > td.col.item > a");
    public static final By BASKET_ITEM_PRICE = By.cssSelector("td.col.product-item-details > div.cart-actions > div.price-div > span > span > span");
    public static final By BASKET_ITEM_QUANTITY_SELECTOR = By.cssSelector("td.col.product-item-details > div.cart-actions > div.field.qty");
    public static final By BASKET_ITEM_REMOVE_BUTTON = By.cssSelector("td.col.subtotal.desktop-only > div > button");
    public static final By BASKET_ITEM_QUANTITY = By.cssSelector("input.qty.cart-qty");
    public static final By BASKET_ITEM_REMOVE_CONFIRM = By.cssSelector("a.action-delete");
    public static final By BASKET_ITEM_REMOVE_POPUP = By.cssSelector(".modal-content .modal-text");
    public static final By BASKET_ITEM_REMOVE_POPUP_VELOZA = By.cssSelector("#modal-content-3 > div > div > p");
    public static final By BASKET_ITEM_REMOVE_CANCEL = By.cssSelector("#modal-content-2 > div > div > div > button");
    public static final By BASKET_ITEM_REMOVE_CANCEL_VELOZA = By.cssSelector("#modal-content-3 > div > div > div > button");
    public static final By BASKET_IS_EMPTY_MESSAGE = By.cssSelector("#maincontent > div.columns > div.column.main > div.cart-empty > div > p");
    public static final By BASKET_IS_EMPTY_MESSAGE_VELOPL = By.cssSelector("#maincontent > div.columns > div.column.main > div.cart-empty > div > div > div");
    public static final By BASKET_START_SHOPPING_CTA = By.cssSelector("a.continue-shopping");
    public static final By BASKET_PRICE_LABEL = By.cssSelector("#cart-totals > div > table > tbody > tr.grand.totals.incl > th > strong");
    public static final By BASKET_TAX_AMOUNT = By.cssSelector("#cart-totals > div > table > tbody > tr.totals-tax-details > td > span");
    public static final By BASKET_TAX_LABEL = By.cssSelector("#cart-totals > div > table > tbody > tr.totals-tax-details > th");
    public static final By BASKET_CHECKOUT_CTA_TEXT = By.cssSelector("#maincontent > div.columns > div.column.main > div.cart-sidebar-wrap > div > ul > li > button > span");
    public static final By AVALANCHE_PROCEED_TO_CHECKOUT_BUTTON = By.cssSelector("body > div.bat-wrapper > div > div > div > div > div > bat-header-avalanche > div > div > div.bat-header-content > div.minicart > bat-minicart-avalanche > div > div.bat-minicart-wrapper > div.bat-minicart-buttons > div > a");
    public static final By AVALANCHE_TOTAL_PRICE = By.cssSelector("#cart-totals > div > table > tbody > tr.grand.totals.incl > th > strong");
    public final static By QUANTITIES_IN_BASKET_PAGE = By.cssSelector("div[id*='cart-item-qty-btn-'] span");
    public final static By MINI_BASKET_BUTTON_IN_BASKET_PAGE = By.cssSelector("header > div.bat-header-cart > button.bat-cta-style");
    public final static By MINI_CART_CLOSE_BUTTON = By.cssSelector("bat-minicart-avalanche.bat > div.bat-minicart > button.bat-minicart-close");
    public final static By CHECK_OUT_BUTTON = By.cssSelector("div.bat-minicart-buttons > div.bat-minicart-button-edit > a.bat-cta-style");
    public final static By PRODUCT_IMAGE_IN_MINI_POP_UP = By.cssSelector("div.bat-minicart-cart-item-container > div.bat-minicart-cart-item > div.bat-minicart-cart-item-img > img");
    public final static By PRODUCT_NAME_IN_MINI_POP_UP = By.cssSelector("div.bat-minicart-cart-item-product-container > div.bat-minicart-cart-item-product-container-title > span");
    public final static By EXCEED_MAX_QTY_WINDOW_TITLE_VUSEMX = By.cssSelector("div.modal-inner-wrap > header.modal-header > h1.modal-title");
    public final static By EXCEED_MAX_QTY_WINDOW_MESSAGE_VUSEMX = By.cssSelector("aside.modal-popup.confirm._show > div.modal-inner-wrap > div.modal-content > div");
    public final static By EXCEED_MAX_QTY_WINDOW_ACCEPT_BTN_VUSEMX = By.cssSelector("div.modal-inner-wrap > footer.modal-footer > button.action-primary.action-accept > span");
    private final static By ENGRAVING_ATTRIBUTE_NAME = By.cssSelector("div[class='psn-options active'] dt:not([style='display: none'])");
    private final static By ENGRAVING_ATTRIBUTE_VALUE = By.cssSelector("div[class='psn-options active'] dd:not([style='display: none'])");
    public final static By CROSS_SELL_PRODUCT_MINICART = By.cssSelector("ul[class*='minicart-cross_sell product-items']");
    public final static By CROSS_SELL_PRODUCT_MINICART_PRODUCT_LINK = By.cssSelector("ul[class*='minicart-cross_sell product-items'] div.product-item-details a");
    public final static By CROSS_SELL_PRODUCT_MINICART_ADD_TO_CART = By.cssSelector("ul[class*='minicart-cross_sell product-items'] button.action.tocart.primary");
    private static final By PDP_PRODUCT_SUBTITLE_UK = By.cssSelector("div.page-title-wrapper.product span.sub-heading");

    public void summaryTotalUpdated() throws NoSuchElementException {
        try{
        waitForExpectedElement(PLUS_ICON,10).click();
        String[] expectedPrice = waitForExpectedElement(ITEM_PRICE,15).getText().split("£");
        String[] actualPrice = waitForExpectedElement(SUB_TOTAL_PRICE,15).getText().split("£");
        softAssert.assertEquals(expectedPrice[1],actualPrice[1]);
        waitForExpectedElement(MINUS_ICON,30).click();
        String[] newPrice = waitForExpectedElement(ITEM_PRICE,10).getText().split("£");
        String[] newActualPrice =  waitForExpectedElement(SUB_TOTAL_PRICE,10).getText().split("£");
        softAssert.assertEquals(newPrice[1],newActualPrice[1]);
        softAssert.assertAll();}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void basketEmptyMessage(){
        Assert.assertEquals(getTextFor(BASKET_EMPTY_MESSAGE), UrlBuilder.getMessage("BasketMessage.key"));
    }

    public void subTotalAndProductsCount(){
        List<WebElement> prices = presenceOfAllElementsLocatedBy(ITEM_PRICE);
        float count = 0;
        for(WebElement item : prices){
            String[] price = item.getText().split("£");
            count = count + Float.parseFloat(price[1]);
        }
        String[] actualPrice = getTextFor(SUB_TOTAL_PRICE).split("£");
        softAssert.assertEquals(count,Float.parseFloat(actualPrice[1]));
        softAssert.assertAll();
    }

    public void summaryTotalCount(){
        try {
            Float expectedTotal;
            waitForExpectedElement(SUB_TOTAL_PRICE,10);
            scrollElementIntoView(SUB_TOTAL_PRICE);
            String[] subtotal = waitForExpectedElement(SUB_TOTAL_PRICE,20).getText().split("£");
            String[] grandTotal = waitForExpectedElement(TOTAL_PRICE,15).getText().split("£");
                scrollElementIntoView(DISCOUNT_PRICE);
                String[] discount = waitForExpectedElement(DISCOUNT_PRICE,30).getText().split("£");
                expectedTotal = Float.parseFloat(subtotal[1]) - Float.parseFloat(discount[1]);
            Assert.assertEquals(expectedTotal, Float.parseFloat(grandTotal[1]));
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void summaryTotalCountWithoutCode(){

        try {
            Float expectedTotal;
            Thread.sleep(20000);
            waitForExpectedElement(SUB_TOTAL_PRICE, 20);
            scrollElementIntoView(SUB_TOTAL_PRICE);
            String[] subtotal = waitForExpectedElement(SUB_TOTAL_PRICE, 20).getText().split("£");
            String[] grandTotal = waitForExpectedElement(TOTAL_PRICE, 15).getText().split("£");

            expectedTotal = Float.parseFloat(subtotal[1]);
            Assert.assertEquals(expectedTotal, Float.parseFloat(grandTotal[1]));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void couponCode(String code){
        waitForExpectedElement(COUPON_CODE_FIELD).sendKeys(UrlBuilder.getMessage(code));
            waitForExpectedElement(CODE_SUBMIT_BTN,10).click();
    }

    public void subscriptionIsSelected(){
        Assert.assertTrue(webDriver.findElement(SUBS_SELECTED).isSelected());
    }

    public void subscriptionModalIsPresent(String text) {
        String expectedText = getTextFor(ERROR_MESSAGE);
        Assert.assertEquals(UrlBuilder.getMessage(text),expectedText);
    }

    public void subscriptionCtaIsDisplayed(DataTable table){
        List<Map<String,String>> entries = table.asMaps(String.class,String.class);
        for(int i = 0; i<presenceOfAllElementsLocatedBy(SUBSCRIPTION_CTA).size(); i++){
            List<WebElement> cta = presenceOfAllElementsLocatedBy(SUBSCRIPTION_CTA);
            String expectedCta = entries.get(i).get("Label");
            String actualCta = cta.get(i).getText();
            Assert.assertEquals(UrlBuilder.getMessage(expectedCta).toLowerCase(),actualCta.toLowerCase());
        }
    }

    public void selectCta(String text){
        String subscriptionOption = UrlBuilder.getMessage(text);
        if(subscriptionOption.contains("subscriptions")) {
            clickByElementByQueryJSExecutor(By.xpath("//span[text()='" + UrlBuilder.getMessage(text) + "']"));
            clickByElementByQueryJSExecutor(AGREE_CTA);
            List<WebElement> items = presenceOfAllElementsLocatedBy(SUBSCRIBE_OPTION);
            for (WebElement option : items) {
                Assert.assertTrue(option.isSelected(), "The option is selected");
            }
        }
        else if(subscriptionOption.contains("one time")) {
            clickByElementByQueryJSExecutor(By.xpath("//span[text()='" + UrlBuilder.getMessage(text) + "']"));
            clickByElementByQueryJSExecutor(AGREE_CTA);
            List<WebElement> items = presenceOfAllElementsLocatedBy(ONE_TIME_OPTION);
            for (WebElement option : items) {
                Assert.assertTrue(option.isSelected(), "The option is selected");
            }
        }
        else{
            LOG.info("Invalid Subscription Option");
        }
    }

    public void selectForFirstProduct(){
        List<WebElement> items = presenceOfAllElementsLocatedBy(ONE_TIME_OPTION);
        clickElementByQueryJSExecutor(items.get(1));
        refreshBrowser();
    }

    public void selectSubscriptionOption(String text){
        clickByElementByQueryJSExecutor(By.xpath("//span[text()='"+UrlBuilder.getMessage(text)+"']"));
        if(UrlBuilder.getMessage(text).contains("Subscribe")) {
            Assert.assertTrue(isSelected(SUBSCRIBE_OPTION));
        }
        else{
            Assert.assertTrue(isSelected(ONE_TIME_OPTION));
        }
    }

    public void subscriptionBronze(String text) {
        try {
            float count = 0;
            refreshBrowser();
            clickByElementByQueryJSExecutor(By.xpath("//span[text()='Subscribe to Vype']"));
            Thread.sleep(10);
            waitForExpectedElement(SUB_TOTAL_PRICE, 30);

            String[] grandTotal = getTextFor(TOTAL_PRICE).split("£");
            String[] expectedPrice = getTextFor(SUB_TOTAL_PRICE).split("£");
            waitForExpectedElement(BRONZE_DISCOUNT, 30);
            String[] actualPrice = getTextFor(BRONZE_DISCOUNT).split("£");
            LOG.info(grandTotal[1]);
            LOG.info(expectedPrice[1]);
            LOG.info(actualPrice[1]);

            count = Float.parseFloat(expectedPrice[1]) - Float.parseFloat(actualPrice[1]);

            if (getTextFor(SUBSCRIPTION_TEXT).toLowerCase().contains(UrlBuilder.getMessage(text))) {
                Assert.assertEquals(count, Float.parseFloat(grandTotal[1]));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            }
        }

        public void bronzeQuantity(String text){
            clickByElementByQueryJSExecutor(By.xpath("//button[@class='action close']"));
            if(UrlBuilder.getMessage(text).contains("bronze")) {
                for (int i = 0; i <= 4; i++) {
                    waitForExpectedElement(QUANTITY_PLUS).click();
                }
            }
            else if(UrlBuilder.getMessage(text).contains("silver")){
                for (int i = 0; i <= 7; i++) {
                    waitForExpectedElement(QUANTITY_PLUS).click();
                }
            }
        }

        public void viewBasketCta() throws InterruptedException {
            switch (valueOf(getLocale().toUpperCase())) {
                case IT:
                case PL:
                case GLODE:
                    waitForExpectedElement(VIEW_BASKET_LINK_GLO, 10);
                    clickByElementByQueryJSExecutor(VIEW_BASKET_LINK_GLO);
                    break;
                default:
                    try {
                        waitForExpectedElement(VIEW_BASKET, 20).click();
                    } catch (Exception exception) {
                        homePage.openBasketifNotOpenLogic();
                        clickByElementByQueryJSExecutor(VIEW_BASKET);
                    }
            }
        }

        public void viewShoppingCartAndProceedCheckout(){
            clickByElementByQueryJSExecutor(VIEW_BASKET);
            waitForExpectedElement(PROCEED_TO_CHECKOUT,10);
            clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT);
        }

    public void cancelCoupon(){
        clickByElementByQueryJSExecutor(CANCEL_COUPON);
        }

    public void engravingDetailsConfirmationOnViewcartPage(){
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        assertTrue(waitForExpectedElement(VIEWCART_ENGRAVING_DETAILS_CONFIRMATION).isDisplayed());
    }

    public List<EngravingAttribute> getEngravingAttributesOnViewcartPage(){
        List<EngravingAttribute> engravingAttributeList = new ArrayList<>();
        waitForExpectedElement(ENGRAVING_ATTRIBUTE_NAME,10);
        List<WebElement> actualEngravingAttributeName = presenceOfAllElementsLocatedBy(ENGRAVING_ATTRIBUTE_NAME);
        for(int i=0;i<actualEngravingAttributeName.size();i++){
            engravingAttributeList.add(EngravingAttribute.builder().name(actualEngravingAttributeName.get(i).getText()).value(waitForExpectedElements(ENGRAVING_ATTRIBUTE_VALUE).get(i).getText()).build());
        }
        return engravingAttributeList;
    }

    public void addVusePromoCode(String promoCode) {
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        waitForExpectedElement(INPUT_PROMOCODE).isDisplayed();
        waitForExpectedElement(INPUT_PROMOCODE).sendKeys(promoCode);
        waitForExpectedElement(PROMOCODE_SUBMIT).click();
        switch (valueOf(getLocale().toUpperCase())) {
            case VELOPL:
                assertThat(waitForExpectedElement(PROMO_RULE_APPLIED).getText().equalsIgnoreCase(UrlBuilder.getMessage("validCouponCode.key")));
                break;
            case VUSEIT:
                try {
                    assert (waitForExpectedElement(PROMO_RULE_APPLIED, 10).isDisplayed());
                } catch (StaleElementReferenceException e) {
                    assert (waitForExpectedElement(PROMO_RULE_APPLIED, 10).isDisplayed());
                }
                break;
            default:
                assert (waitForExpectedElement(PROMO_RULE_APPLIED, 10).isDisplayed());

        }
    }

    public void removeVusePromo(){
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        waitForExpectedElement(REMOVE_PROMOTION,10);
        clickByElementByQueryJSExecutor(REMOVE_PROMOTION);
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
       if(UrlBuilder.getLocale().equalsIgnoreCase("vusede")
               ||UrlBuilder.getLocale().equalsIgnoreCase("lyftse")){
            assertTrue(getWebDriver().findElements(PROMO_RULE_APPLIED).size()<=1);
        }
        else assertTrue(getWebDriver().findElements(PROMO_RULE_APPLIED).size()<=0);
    }

    public void assertUserIsAbleToInputAnyValueInMiniBasketQtyField(String strQty) throws Throwable {
        if (doesURLContain("/checkout/cart/") && !UrlBuilder.getLocale().equals("vusefr")) {
            waitForExpectedElement(INPUT_QTY_MINI_BASKET, 10);
            clearFieldUsingControlKeys(INPUT_QTY_MINI_BASKET);
            enterDataAndWait(INPUT_QTY_MINI_BASKET, strQty);
            clickByElementByQueryJSExecutor(QTY_SELECTOR_PLUS_BUTTON_BASKET);
            waitForAjaxElementNotToBePresent(getWebDriver(), 8);
            assertTrueWithCustomError("5", getWebDriver().findElements(INPUT_QTY_MINI_BASKET).get(0).getAttribute("value"));
        } else {
            if (UrlBuilder.getLocale().equals("vusefr")) {
                enterItemQuantityMiniCart(strQty);
            } else {
                waitForExpectedElement(INPUT_QTY_MINI_CART, 10);
                if(isElementDisplayedBySeconds(QTY_DROPDOWN_SELECT_MINI_CART,10)) {
                    waitForExpectedElement(QTY_DROPDOWN_SELECT_MINI_CART).click();
                    waitForExpectedElement(QTY_DROPDOWN_SELECT_MORE_VALUE_OPTION_MINICART).click();
                }
                clearFieldUsingControlKeys(INPUT_QTY_MINI_CART);
                enterText(INPUT_QTY_MINI_CART, strQty);
                assertTrueWithCustomError(strQty, getWebDriver().findElements(INPUT_QTY_MINI_CART).get(0).getAttribute("value"));
            }
        }
    }

    public void clickToActionOnQtySelectorToUpdateMiniBasketQty(By ByBasketAction,String strExpectedQty,By ByMiniCartAction) throws Throwable {
        if(doesURLContain("/checkout/cart/")){
            clickByElementByQueryJSExecutor(ByBasketAction);
            waitForAjaxElementNotToBePresent(getWebDriver(),5);
            assertTrueWithCustomError(strExpectedQty, getWebDriver().findElements(INPUT_QTY_MINI_BASKET).get(0).getAttribute("value"));
        }
        else {
            waitForExpectedElement(ByMiniCartAction).click();
            assertTrueWithCustomError(strExpectedQty, getWebDriver().findElements(INPUT_QTY_MINI_CART).get(0).getAttribute("value"));
        }
    }

    public void clickOnMinusButtonOnMiniCartQtySelector() throws Throwable {
        if(doesURLContain("/checkout/cart")){
            switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
                case VUSEUK:
                case VUSEFR:
                case VUSEIT:
                case VUSEDE:
                case VUSEZA:
                case VUSECO:
                case GLODE:
                case KZ:
                    waitAndClickByElementByJSExecutor(QTY_DROPDOWN_SELECT_BASKET_PAGE,10);
                    clickByElementByQueryJSExecutor(QTY_DROPDOWN_SELECT_REMOVE_BASKET_PAGE);
                    break;
                default:
                    waitForExpectedElement(QTY_SELECTOR_MINUS_BUTTON_BASKET, 10);
                    clickByElementByQueryJSExecutor(QTY_SELECTOR_MINUS_BUTTON_BASKET); }
        }else {
            if (UrlBuilder.getLocale().equals("vuseuk") || UrlBuilder.getLocale().equals("vusefr") || UrlBuilder.getLocale().equals("vuseit") || UrlBuilder.getLocale().equals("vusede") || UrlBuilder.getLocale().equals("vuseza") || UrlBuilder.getLocale().equals("vuseco")||UrlBuilder.getLocale().equals("glode")||UrlBuilder.getLocale().equals("kz")){
                    waitForExpectedElement(QTY_DROPDOWN_SELECT_MINI_CART,10);
                clickByElementByQueryJSExecutor(QTY_DROPDOWN_SELECT_MINI_CART);
                    clickByElementByQueryJSExecutor(QTY_DROPDOWN_SELECT_REMOVE_MINICART);
            }
            else {
                waitForExpectedElement(QTY_SELECTOR_MINUS_BUTTON_MINI_CART, 10);
                clickByElementByQueryJSExecutor(QTY_SELECTOR_MINUS_BUTTON_MINI_CART);
            }
        }
    }

    public void clickOnCancelButtonFromConfirmRemovalPopUp(){
        if(doesURLContain("/checkout/cart"))
            if (valueOf(getLocale().toUpperCase()) == Locale.VUSEFR) {
                clickFirstElementByQueryJSExecutor(PRODUCT_REMOVAL_CANCEL_BUTTON_CART_FR);
            } else {
                waitForItemToBeClickableAndClick(PRODUCT_REMOVAL_CANCEL_BUTTON_CART, 10);
            }
        else
            waitForItemToBeClickableAndClick(PRODUCT_REMOVAL_CANCEL_BUTTON_BASKET, 10);
    }

    public void clickOnRemoveButtonFromConfirmRemovalPopUp(){
        if(doesURLContain("/checkout/cart"))
            if (valueOf(getLocale().toUpperCase()) == Locale.VUSEFR) {
                clickFirstElementByQueryJSExecutor(PRODUCT_REMOVE_BUTTON_CART_FR);
            } else {
                waitForItemToBeClickableAndClick(PRODUCT_REMOVE_BUTTON_CART, 10);
            }
        else
            waitForItemToBeClickableAndClick(PRODUCT_REMOVE_BUTTON_BASKET, 10);
        waitForExpectedElement(BAKSET_EMPTY_STATUS, 5);
    }


    public Float getBasketTotal() {
        return Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_GLO).getText().replaceAll("[\\D]", ""));
    }

    public void enterItemQuantityCart(String quantity){
        waitForAjaxElementNotToBePresent(getWebDriver(), 8);
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEUK:
            case VUSEIT:
            case VUSEZA:
            case VUSECO:
                waitAndClickByElementByJSExecutor(QTY_DROPDOWN_SELECT_BASKET_PAGE,10);
                if(quantity.equalsIgnoreCase("15")){
                    clickByElementByQueryJSExecutor(QTY_DROPDOWN_MORE_VALUE_BASKET_PGE);
                    waitForExpectedElement(INPUT_QTY_MINI_BASKET).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    waitForExpectedElement(INPUT_QTY_MINI_BASKET).sendKeys(quantity);
                    waitForExpectedElement(INPUT_QTY_MINI_BASKET).sendKeys(Keys.ENTER);}
                else{
                    clickByElementByQueryJSExecutor(By.xpath("//div[@class='qty-btn active']//following::li[@class='value-option'][@data-value='"+quantity+"']"));}
                waitForAjaxElementNotToBePresent(getWebDriver(),10);
                waitForElementToDisappear(QTY_DROPDOWN_ACTIVE,10);
                break;
            default:
                waitForExpectedElement(INPUT_ITEM_QUANTITY_CART).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                waitForExpectedElement(INPUT_ITEM_QUANTITY_CART, 10).sendKeys(quantity);
                new WebDriverWait(getWebDriver(), 20)
                        .ignoring(StaleElementReferenceException.class)
                        .until((WebDriver d) -> {
                            d.findElement(INPUT_ITEM_QUANTITY_CART).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.ENTER));
                            return true;
                        });
        }
        waitForAjaxElementNotToBePresent(getWebDriver(),8);
    }

    public void verifyRmoveSkuFunctionality(String cart) throws InterruptedException {
        if(cart.equals("minicart")){
            if(UrlBuilder.getLocale().equals("pl")){
                waitForExpectedElement(GloItHomePage.CLICK_ON_DEVICE_MENU,20);
                clickByElementByQueryJSExecutor(GloItHomePage.CLICK_ON_DEVICE_MENU);
                homePage.openBasketifNotOpenLogic();
            }
            waitForExpectedElement(MINUS_SIGN_MINICART,8);
            if(getWebDriver().findElements(MINUS_SIGN_MINICART).size() > 0){
                clickByElementByQueryJSExecutor(MINUS_SIGN_MINICART);
                assertTrue(waitForExpectedElement(ACCEPT_OPTION_REMOVE_MINICART,5).isDisplayed());
                assertTrue(waitForExpectedElement(CANCEL_OPTION_REMOVE_MINICART,5).isDisplayed());
                clickByElementByQueryJSExecutor(ACCEPT_OPTION_REMOVE_MINICART);
                waitForAjaxElementNotToBePresent(getWebDriver(),5);
                assertTrue(waitForExpectedElement(EMPTY_BASKET_MESSAGE,5).isDisplayed());
                clickByElementByQueryJSExecutor(MINICART_BACK_BUTTON);}
            else clickByElementByQueryJSExecutor(BASKET_CLOSE_SLIDING_PANEL_BUTTON_PL);
        }
        else{
            if(!UrlBuilder.getLocale().equals("pl"))
                homePage.clickViewBasketButton();
            waitForExpectedElement(MINUS_SIGN_BASKET_PAGE,8);
            clickByElementByQueryJSExecutor(MINUS_SIGN_BASKET_PAGE);
            assertTrue(waitForExpectedElement(ACCEPT_OPTION_REMOVE_BASKET_PAGE,5).isDisplayed());
            assertTrue(waitForExpectedElement(CANCEL_OPTION_REMOVE_BASKET_PAGE,5).isDisplayed());
            clickByElementByQueryJSExecutor(ACCEPT_OPTION_REMOVE_BASKET_PAGE);
            waitForAjaxElementNotToBePresent(getWebDriver(),5);
            homePage.openBasketifNotOpenLogic();
            assertTrue(waitForExpectedElement(EMPTY_BASKET_MESSAGE,5).isDisplayed());
            clickByElementByQueryJSExecutor(BASKET_CLOSE_SLIDING_PANEL_BUTTON_PL);
        }
    }

    public void clickOnCheckoutButton() {
        if(UrlBuilder.isDesktop()|| UrlBuilder.isIpad()){
            waitForExpectedElement(ToCheckoutBtn, 10);
            clickByElement(ToCheckoutBtn);
            if(UrlBuilder.isFirefox()&&UrlBuilder.getSite().equalsIgnoreCase("velo"))
                waitForElementToDisappear(ToCheckoutBtn,20);
        }else{
            switch (UrlBuilder.getLocale()){
                case "vypeit":
                case "vuseit":
                    waitForExpectedElement(M_CHECKOUT_BUTTON_FR,5);
                    clickUsingJS(M_CHECKOUT_BUTTON_FR);
                    break;
                case "fr":
                case "vusefr":
                    waitForExpectedElement(M_VIEWBASKET_BUTTON_FR).click();
                    waitForExpectedElement(M_CHECKOUT_BUTTON_FR);
                    clickUsingJS(M_CHECKOUT_BUTTON_FR);
                    break;
                case "dk":
                case "de":
                    waitForExpectedElement(M_CHECKOUT_BUTTON_DK).click();
                    break;
                case "ie":
                    if (UrlBuilder.isMobile()) {
                        if(UrlBuilder.isIPhone()){
                            scrollToPageTop();
                            waitForExpectedElement(TOGGLE_BUTTON_VYPEIE, 5);
                            clickUsingJS(TOGGLE_BUTTON_VYPEIE);
                            waitForExpectedElement(MY_BASKET_VYPEIE_IPHONE, 5);
                            clickUsingJS(MY_BASKET_VYPEIE_IPHONE);
                        }
                        waitForAjaxElementNotToBePresent(getWebDriver(),10);
                        waitForExpectedElement(M_CHECKOUT_BUTTON_IE, 5);
                        clickUsingJS(M_CHECKOUT_BUTTON_IE);
                    } else {
                        waitForExpectedElement(T_CHECKOUT_BUTTON_IE, 5);
                        clickUsingJS(T_CHECKOUT_BUTTON_IE);
                    }
                    break;
                case "nl":
                case "mx":
                case "vusemx":
                    if(UrlBuilder.isIPhone())
                        clickUsingJS(M_CHECKOUT_BUTTON_MX);
                    else waitForExpectedElement(M_CHECKOUT_BUTTON_MX, 5).click();
                    break;
                case "co":
                    if(UrlBuilder.isIPhone())
                        clickUsingJS(M_CHECKOUT_BUTTON_CO);
                    else waitForExpectedElement(M_CHECKOUT_BUTTON_CO).click();
                    break;
                case "epok":
                case "velodude":
                    waitForExpectedElement(M_CHECKOUT_BUTTON,10);
                    clickByElementByQueryJSExecutor(M_CHECKOUT_BUTTON);
                    waitForAjaxElementNotToBePresent(getWebDriver(),20);
                    break;
                default:
                    waitForExpectedElement(M_CHECKOUT_BUTTON).click();
            }
        }
    }

    public float getValueOfPriceString(String price) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float floatValue;
        switch (UrlBuilder.getLocale()) {
            case "it":
            case "lyftse":
            case "vusefr":
                String itPrice = price.replace(',','.');
                floatValue = Float.parseFloat(itPrice.split(UrlBuilder.getMessage("currency.key"))[0]);
                break;
            case "pl":
                String plPrice = price.replace(',','.');
                floatValue= Float.parseFloat(plPrice.split(UrlBuilder.getMessage("plCurrency.key"))[0].trim());
                break;
            default:
                floatValue = Float.parseFloat(price.split(UrlBuilder.getMessage("currency.key"))[1]);
        }
        if ( price.contains("-") ) {
            return Float.parseFloat(decimalFormat.format(floatValue * -1.00));
        } else {
            return Float.parseFloat(decimalFormat.format(floatValue));
        }
    }

    public void verifyProductCostIncludeTaxes(){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float discount=0;
        if(isElementPresent(DISCOUNT_PRICE_UK)) {
            discount = getValueOfPriceString(getWebDriver().findElement(DISCOUNT_PRICE_UK).getText());
        }
        float subTotal = getValueOfPriceString(getWebDriver().findElement(PRODUCT_PRICE_UK).getText());
        float totalPrice = getValueOfPriceString(getWebDriver().findElement(TOTAL_PRICE_UK).getText());
        assertThat(Float.parseFloat(decimalFormat.format(subTotal - discount)) == totalPrice )
                .as("ERROR verifyProductCostIncludeTaxes: sub total = " + subTotal + " discount = " + discount + " total price = " + totalPrice).isTrue();
    }

    public void verifySubscriptionDiscount(){
        assertTrue(isElementDisplayedBySeconds(SUBSCRIPTION_DISCOUNT, 5));
    }

    public void validateBasketItemComponents() {
        By productNameSelector;
        if (UrlBuilder.getLocale().equals("velopl")) {
            productNameSelector = AVALANCHE_BASKET_ITEM_NAME_VELOPL;
        } else {
            productNameSelector = AVALANCHE_BASKET_ITEM_NAME_VELOBE;
        }
        String itemName;
        scrollToPageBottom();
        List<WebElement> basketItems = webDriver.findElements(BASKET_ITEM);
        for (WebElement basketItem: basketItems) {
            itemName = basketItem.findElement(productNameSelector).getText();
            assertThat(basketItem.findElement(BASKET_ITEM_IMAGE).isDisplayed())
                    .as("ERROR validateBasketItemComponents: could not find a displayed image for "+itemName).isTrue();
            assertThat(itemName.length()>0)
                    .as("ERROR validateBasketItemComponents: item name is missing").isTrue();
            NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.GERMANY);
            String price = basketItem.findElement(BASKET_ITEM_PRICE).getText();
            switch (UrlBuilder.getLocale()) {
                case "veloza":
                    assertThat(price).contains("R");
                    break;
                case "velobe":
                    assertThat(price).contains("€");
                    break;
                default:
                    assertThat(validPrice(price))
                            .as("ERROR validateBasketItemComponents: invalid price " + price + " for item " + itemName).isTrue();
                    assertThat(basketItem.findElement(BASKET_ITEM_QUANTITY_SELECTOR).isDisplayed())
                            .as("ERROR validateBasketItemComponents: could not find quantity selector for item " + itemName).isTrue();
                    assertThat(basketItem.findElement(BASKET_ITEM_REMOVE_BUTTON).isDisplayed())
                            .as("ERROR validateBasketItemComponents: could not find remove button for item " + itemName).isTrue();
            }}
    }

    public int itemsInBasket() {
        int quantity = 0;
        List<WebElement> basketItems = webDriver.findElements(BASKET_ITEM);
        for (WebElement basketItem: basketItems) {
            quantity = quantity + Integer.parseInt(basketItem.findElement(BASKET_ITEM_QUANTITY).getAttribute("value"));
        }
        return quantity;
    }

    public boolean validPrice(String price) {
        String regExp;
        switch (UrlBuilder.getLocale()){
            case "vusemx":
                regExp = "\\" + UrlBuilder.getMessage("currency.key") + "[1-9][0-9]+\\.[0-9]{2}";
                break;
            case "velopl":
                regExp ="(?:0|[1-9][0-9]{0,2}(?:\\.[0-9]{3})*),[0-9]{2}\\s" + UrlBuilder.getMessage("currency.key");
                break;
            default:
                regExp ="(?:0|[1-9][0-9]{0,2}(?:\\.[0-9]{3})*),[a-zA-Z],[0-9]{2}\\s" + UrlBuilder.getMessage("currency.key");
        }
        final Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    public void validateBasketHeading(String expectedBasketHeading) {
        String actualBasketHeading = returnHeaderText();
        if (!actualBasketHeading.replaceAll("[^0-9]","").equals("1")) {
            actualBasketHeading = actualBasketHeading.replace("items","item");
        }
        assertThat(expectedBasketHeading.equalsIgnoreCase(actualBasketHeading))
                .as("ERROR validateBasketHeading: expected basket heading was " + expectedBasketHeading + " but I got " + actualBasketHeading).isTrue();
    }

    public void clickOnRemoveProduct() {
        List<WebElement> basketItems = webDriver.findElements(BASKET_ITEM);
        try {
        webDriver.findElement(BasketPage.BASKET_ITEM_REMOVE_BUTTON).click();
            } catch (Exception e) {
                clickUsingJS(basketItems.get(0).findElement(BasketPage.BASKET_ITEM_REMOVE_BUTTON));
            }
    }

    public void IseePopupAskingConfirmation(String expectedPopupText) {
        if (UrlBuilder.getLocale().equals("veloza")){
            assertThat(waitForExpectedElement(BASKET_ITEM_REMOVE_POPUP_VELOZA).isDisplayed())
                    .as("ERROR IseePopupAskingConfirmation: no remove item pop up displayed").isTrue();
            String actualPopupText = waitForExpectedElement(BASKET_ITEM_REMOVE_POPUP_VELOZA).getText();
            assertThat(actualPopupText.toLowerCase().contains(expectedPopupText.toLowerCase()))
                    .as("ERROR IseePopupAskingConfirmation: expected popup text to contain "+expectedPopupText+" but popup text was "+actualPopupText).isTrue();

        }else{
        assertThat(waitForExpectedElement(BASKET_ITEM_REMOVE_POPUP).isDisplayed())
                .as("ERROR IseePopupAskingConfirmation: no remove item pop up displayed").isTrue();
        String actualPopupText = waitForExpectedElement(BASKET_ITEM_REMOVE_POPUP).getText();
        assertThat(actualPopupText.toLowerCase().contains(expectedPopupText.toLowerCase()))
                .as("ERROR IseePopupAskingConfirmation: expected popup text to contain "+expectedPopupText+" but popup text was "+actualPopupText).isTrue();
    }}

    public void thereIsCancelAndRemove() {
        assertThat(waitForExpectedElement(CANCEL_OPTION_REMOVE_BASKET_PAGE).isDisplayed())
                .as("ERROR thereIsCancelAndRemove: couldn't find the cancel button on delete item popup").isTrue();
        assertThat(waitForExpectedElement(ACCEPT_OPTION_REMOVE_BASKET_PAGE).isDisplayed())
                .as("ERROR thereIsCancelAndRemove: couldn't find the remove button on delete item popup").isTrue();

    }

    public void clickCancelAndCheckOnBasketPage() {
        if (UrlBuilder.getLocale().equals("veloza")){
            waitForExpectedElement(BASKET_ITEM_REMOVE_CANCEL_VELOZA).click();
        }else{
            waitForExpectedElement(BASKET_ITEM_REMOVE_CANCEL).click();
        }
    }

    public void validateEmptyBasketMessage(String expectedBasketEmptyMessage) {
        String actualBasketEmpyMessage;
        if (scenarioContext.getContext(Context.LANGUAGE) .equals("pl")) {
            actualBasketEmpyMessage  = waitForExpectedElement(BASKET_IS_EMPTY_MESSAGE_VELOPL).getText();
        } else {
            actualBasketEmpyMessage  = waitForExpectedElement(BASKET_IS_EMPTY_MESSAGE).getText();
        }
        assertThat(expectedBasketEmptyMessage.equalsIgnoreCase(actualBasketEmpyMessage))
                .as("ERROR validateEmptyBasketMessage: expected empty basket message was "+expectedBasketEmptyMessage+" but I got "+actualBasketEmpyMessage).isTrue();
    }

    public void validateThereIsAStartShoppingCta() {
        assertThat(waitForExpectedElement(BASKET_START_SHOPPING_CTA).isDisplayed())
                .as("ERROR validateThereIsAStartShoppingCta: expected a start shopping CTA to be visible but it wasn't").isTrue();
    }

    public void clickStartShoppingAndValidateRedirect() {
        waitForExpectedElement(BASKET_START_SHOPPING_CTA).click();
        String currentUrl = webDriver.getCurrentUrl();
        String redirectPage=currentUrl.concat((String) scenarioContext.getContext(Context.LANGUAGE));

        if (scenarioContext.getContext(Context.LANGUAGE) .equals("be")) {
            assertThat(redirectPage)
                    .as("ERROR clickStartShoppingAndValidateRedirect: error was not redirected to the home page. Current url is " + "nicotine-pouches");
        }
        if (scenarioContext.getContext(Context.LANGUAGE) .equals("fr")) {
            assertThat(redirectPage)
                    .as("ERROR clickStartShoppingAndValidateRedirect: error was not redirected to the home page. Current url is " + "sachet-nicotine");
        }
        if (scenarioContext.getContext(Context.LANGUAGE) .equals("nl")) {
            assertThat(redirectPage)
                    .as("ERROR clickStartShoppingAndValidateRedirect: error was not redirected to the home page. Current url is " + "nicotinezakjes");
        }
        }

    public void canSeeTotalPrice(String expectedPriceLabel) {
        if (UrlBuilder.getLocale().equals("veloza")) {
            String stringPrice = waitForExpectedElement(AVALANCHE_TOTAL_PRICE).getText();
            assertThat(stringPrice)
                    .as("ERROR canSeeTotalPrice: invalid price displayed " + stringPrice.equalsIgnoreCase(expectedPriceLabel));
        }else{
        String actualPriceLabel = waitForExpectedElement(BASKET_PRICE_LABEL).getText();
        assertThat(actualPriceLabel.equalsIgnoreCase(expectedPriceLabel))
                .as ("ERROR canSeeTotalPrice: expected basket price label was "+expectedPriceLabel+" but I got "+actualPriceLabel ).isTrue();
    }}

    public void canSeeTaxCalculationAmount(String expectedTaxLabel) {
        String stringTaxAmount = waitForExpectedElement(BASKET_TAX_AMOUNT).getText();
        assertThat(validPrice(stringTaxAmount))
                .as("ERROR canSeeTaxCalculationAmount: invalid tax amount displayed "+stringTaxAmount).isTrue();
        String actualTaxLabel = waitForExpectedElement(BASKET_TAX_LABEL).getText();
        assertThat(actualTaxLabel.equalsIgnoreCase(expectedTaxLabel))
                .as ("ERROR canSeeTotalPrice: expected basket price label was "+expectedTaxLabel+" but I got "+actualTaxLabel ).isTrue();
    }

    public void canSeeProceedToCheckoutCta(String expectedCheckoutCtaText) {
        String actualCheckoutCtaText = waitForExpectedElement(BASKET_CHECKOUT_CTA_TEXT).getText();
        assertThat(actualCheckoutCtaText.equalsIgnoreCase(expectedCheckoutCtaText))
                .as ("ERROR canSeeProceedToCheckoutCta: expected checkout cta text was "+expectedCheckoutCtaText+" but I got "+actualCheckoutCtaText ).isTrue();
    }

    public void checkAllOrderAgainProductsAddedToBasket() {
        String basketItemName = null;
        String basketItemQuantity = null;
        switch (UrlBuilder.getLocale()){
            case "velobe":
        Map<String, String> basketProducts = new HashMap<>();
        Map<String, String> originalOrderProducts = (Map<String, String>) scenarioContext.getContext(Context.BASKET_CONTENTS_MAP);
        List<WebElement> basketItems = waitForExpectedElements(BASKET_ITEM);
        for (WebElement basketItem: basketItems) {
            basketItemName = basketItem.findElement(AVALANCHE_BASKET_ITEM_NAME_VELOBE).getText();
            basketItemQuantity = basketItem.findElement(BASKET_ITEM_QUANTITY).getAttribute("value");
            basketProducts.put(basketItemName, basketItemQuantity);
        }
        assertThat(basketProducts.size() == (originalOrderProducts.size()))
                .as("ERROR checkAllOrderAgainProductsAddedToBasket: expected number of items was " + originalOrderProducts.size() + " but I got " + basketProducts.size()).isTrue();
        break;
        case "velopl":
        waitForAjaxElementNotToBePresent(webDriver,10);
            String productName= webDriver.findElement(BASKET_ITEM_NAME_VELOPL).getAttribute("title");
            Map<String, String> basketProduct = new HashMap<>();
            List<WebElement> basketItemspl = waitForExpectedElements(BASKET_ITEM);
            for (WebElement basketItem: basketItemspl) {
                basketItemQuantity = basketItem.findElement(BASKET_ITEM_QUANTITY).getAttribute("value");
                basketProduct.put(productName, basketItemQuantity);
                Map<String, String> originalOrderProduct = (Map<String, String>) scenarioContext.getContext(Context.BASKET_CONTENTS_MAP);
                assertThat(basketProduct).as("ERROR checkAllOrderAgainProductsAddedToBasket: not all previous order detaiuls are in the basket").isEqualTo(originalOrderProduct);
            }
            break;
        }}

    public int originalOrderQuantity() {
        Map<String, String> originalOrderProducts;
            originalOrderProducts = (Map<String, String>) scenarioContext.getContext(Context.BASKET_CONTENTS_MAP);
        int quantity = 0;
        if (originalOrderProducts == null) {
            return 0;
        } else {
            for (String value : originalOrderProducts.values()) {
                quantity = quantity + Integer.parseInt(value);
            }
            return quantity;
        }
    }

    public void verifySubscriptionDiscountNotDisplay(){
        assertTrue(invisibilityOfElementLocated(SUBSCRIPTION_DISCOUNT));
    }

    public void assetComboDiscountApplied(){
        assertTrue(isElementDisplayedBySeconds(COMBO_DISCOUNT, 5));
    }

    public void applyReferalCouponCode(){
        waitForAjaxElementNotToBePresent(getWebDriver(),5);
        String code= System.getProperty("referrerCouponCode.key");
        waitClearAndEnterText(DISCOUNT_FIELD,code);
        waitForItemToBeClickableAndClick(APPLY_DISCOUNT,5);
        assertTrue(isElementDisplayedBySeconds(COMBO_DISCOUNT,10));
    }

    public void increaseQuantityOfItemInCart(int qty){
        waitForPage();
        for (int i = 0; i < qty; i++) {
            waitForExpectedElement(INCREASE_BUTTON);
            clickUsingJS(INCREASE_BUTTON);
            if ("vuseco".equals(getLocale())) {
                invisibilityOfElementLocated(LOADING_CIRCLE);
            }
        }
    }

    public int verifyProductPrice() {
        return Math.round(Float.parseFloat(waitForExpectedElement(FINAL_CHARGES_CART_ZA).getText().replaceAll("R", ""))); }

    public void increaseProductQuantityOnBasketPage(){
        if (valueOf(getLocale().toUpperCase()) == Locale.VUSEZA) {
            enterItemQuantityCart("6");
        } else {
            waitForItemToBeClickableAndClick(INCREASE_BUTTON_BASKET, 5);
        }
    }

    public void userGetFlavourDiscount() {
        int beforeDiscount = verifyProductPrice();
        increaseProductQuantityOnBasketPage();
        invisibilityOfElementLocated(LOADING_CIRCLE);
        int afterDiscount = verifyProductPrice();
        assertEquals(beforeDiscount, afterDiscount);
        assertTrue(isElementDisplayedBySeconds(COMBO_DISCOUNT, 5));
    }

    public boolean isRemovalConfirmPopupPresent() {
        return isElementDisplayedBySeconds(PRODUCT_REMOVAL_CONFIRM_POPUP,5);
    }

    public void clickStartYourShoppingButton(){
        waitForItemToBeClickableAndClick(START_YOUR_SHOPPING,5);
    }

    public void confirmBasketDisplayedAmountOfTiles(int BaskettilesQty) throws Throwable {
        clickUsingJS(Click_BASKET_ICON);
        List<WebElement> baskettiles = presenceOfAllElementsLocatedBy(BASKET_TILES);
        int baskettilesValue = baskettiles.size();
        assertEquals(baskettilesValue,BaskettilesQty);
    }

    public void selectMaxQuantityFromQtyDropdownOnMiniCartAndBasket() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        if(doesURLContain("/checkout/cart/")){
            clickByElementByQueryJSExecutor(QTY_DROPDOWN_SELECT_BASKET_PAGE);
            clickByElementByQueryJSExecutor(QTY_DROPDOWN_MORE_VALUE_BASKET_PGE);
        }else {
            clickByElementByQueryJSExecutor(QTY_DROPDOWN_SELECT_MINI_CART);
            clickByElementByQueryJSExecutor(QTY_DROPDOWN_MORE_VALUE_MINI_CART);
        }
    }

    public void assertQtyFreeTextFieldSwitchesBackToDropdownWhenQtyIsLessThanMaxValue() {
        if(doesURLContain("/checkout/cart/"))
            assertTrue(isElementDisplayedBySeconds(QTY_DROPDOWN_SELECT_BASKET_PAGE,10));
        else
            assertTrue(isElementDisplayedBySeconds(QTY_DROPDOWN_SELECT_MINI_CART, 10));
    }

    public void assertQtyFieldContinuesToDisplayAsTextFieldWhenQtyIsMoreThanMaxValue() {
        waitForAjaxElementNotToBePresent(getWebDriver(),5);
        if (doesURLContain("/checkout/cart/"))
            assertTrue(isElementDisplayedBySeconds(INPUT_QTY_MINI_BASKET, 5));
        else
            assertTrue(isElementDisplayedBySeconds(INPUT_QTY_MINI_CART, 5));
    }

    public void enterQuantityInMiniCartTextFieldAndUpdate(String strQuantity) throws InterruptedException {
        if (doesURLContain("/checkout/cart/")) {
            waitForExpectedElement(INPUT_QTY_MINI_BASKET,10).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            waitForExpectedElement(INPUT_QTY_MINI_BASKET).sendKeys(strQuantity);
            waitForExpectedElement(INPUT_QTY_MINI_BASKET).sendKeys(Keys.ENTER);
        } else {
            homePage.openBasketifNotOpenLogic();
            waitForExpectedElement(INPUT_QTY_MINI_CART,10).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            waitForExpectedElement(INPUT_QTY_MINI_CART).sendKeys(strQuantity);
            if(UrlBuilder.getLocale().equalsIgnoreCase("vusemx"))
            {
                waitForItemToBeClickableAndClick(QTY_UPDATE_LINK_MINI_CART_VUSEMX, 10);
            }
            else {
                waitForItemToBeClickableAndClick(QTY_UPDATE_LINK_MINI_CART, 10);
            }
        }
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public void enterItemQuantityMiniCart(String quantity){
        waitForAjaxElementNotToBePresent(getWebDriver(), 8);
        waitAndClickByElementByJSExecutor(QTY_DROPDOWN_SELECT_MINI_CART,10);
        clickByElementByQueryJSExecutor(By.xpath("//li[contains(@class,'value-option')][@data-value='"+quantity+"']"));
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public void selectItemQuantityUsingDropdownInBasketPage(String quantity){
        waitForAjaxElementNotToBePresent(getWebDriver(), 8);
        waitAndClickByElementByJSExecutor(QTY_DROPDOWN_SELECT_BASKET_PAGE_VUSEDE,10);
        clickByElementByQueryJSExecutor(By.cssSelector("div.cart-actions > div.field.qty > div.control.qty > div.quantity-selector-box > div.quantity-dropdown-wrapper > div.quantity-dropdown > ul.options-list > li[data-value='"+quantity+"']"));
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public Integer getQuantityNumInTitle() {
        return Integer.parseInt(getNumberFromString(waitForExpectedElement(QTY_TITLE_TEXT).getText()));
    }

    public void addRelatedProductIntoCart(String product){
        if(product.equals("cross sell related")) {
            int itemCountPrevious = Integer.parseInt(waitForExpectedElement(BASKET_ITEM_COUNT, 5).getText());
            clickFirstElementByQueryJSExecutor(RELATED_PRODUCT_ADD_TO_CART_PL);
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            homePage.userCloseFreeGiftModule();
            int itemCountCurrent = Integer.parseInt(waitForExpectedElement(BASKET_ITEM_COUNT, 5).getText());
            assertTrue(itemCountCurrent == itemCountPrevious + 1);
        }
        else{
            int itemCountPrevious = Integer.parseInt(waitForExpectedElement(BASKET_ITEM_COUNT, 20).getText());
            clickFirstElementByQueryJSExecutor(RELATED_PRODUCT_ADD_TO_CART);
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            int itemCountCurrent = Integer.parseInt(waitForExpectedElement(BASKET_ITEM_COUNT, 5).getText());
            assertTrue(itemCountCurrent == itemCountPrevious + 1);

        }
    }

    public void userClickRelatedProductImageAndVerifyPDP(){
        String relatedProductName= getWebDriver().findElements(RELATED_PRODUCT_NAME).get(0).getText();
        clickFirstElementByQueryJSExecutor(RELATED_PRODUCT_PRODUCT_IMAGE_PL);
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        String pdpHeader= waitForExpectedElement(RELATED_PRODUCT_NAME_PDP_HEADER,5).getText();
        assertTrue(relatedProductName.equalsIgnoreCase(pdpHeader));
    }

    public boolean isQTYEditable(){
        return hasAttribute(waitForExpectedElement(INPUT_QTY_GLOPL),"readonly");
    }

    public void clickChooseYourDevice(){
        waitForExpectedElement(BASKET_CHOOSE_YOUR_DEVICE_CTA).click();
    }
    public void chooseFreeDeviceFromPopUp(){
        switch (UrlBuilder.getLocale()){
            case "vuseit":
                waitForExpectedElement(FREE_DEVICE_POPUP,5);
                clickFirstElementByQueryJSExecutor(SELECT_FREE_DEVICE_COLOUR);
                clickFirstElementByQueryJSExecutor(ADD_TO_CART_FREE_DEVICE);
                break;
            case "it":
                waitForExpectedElement(FREE_GLOIT_ACCESSORY,5);
                clickFirstElementByQueryJSExecutor(ADD_TO_CART_FREE_ACCESSORY);
                break;
        }
    }

    public void emptyBasketFromCartPage() {
        switch (UrlBuilder.getLocale()) {
            case "kz":
                if (isElementPresentByby(PRODUCT_REMOVE_BUTTON_BASKET_KZ)) {
                    int productItemCount = waitForExpectedElements(PRODUCT_REMOVE_BUTTON_BASKET_KZ).size();
                    while (productItemCount > 0) {
                        LOG.info("Product items in basket: " + String.valueOf(waitForExpectedElements(PRODUCT_REMOVE_BUTTON_BASKET_KZ).size()));
                        retryingFindClick(PRODUCT_REMOVE_BUTTON_BASKET_KZ);
                        waitForExpectedElement(PRODUCT_REMOVAL_CONFIRM_POPUP, 10);
                        clickByElementByQueryJSExecutor(PRODUCT_REMOVAL_CONFIRM_POPUP);
                        productItemCount--;
                    }
                    LOG.info("Basket is empty");
                }
                break;
            default:
        }
    }

    public void checkMiniBasketPLP(){
        scrollToPageTop();
        waitForExpectedElement(AVALANCHE_CONFIRMATION_MESSAGE_BE,10);
        assertTrue(waitForExpectedElement(PLP_MINI_CART_IMAGE,5).isDisplayed(),"Missing mini cart image.");
        switch (scenarioContext.getContext(Context.LANGUAGE).toString())
        {
            case "fr":
            case "en":
                assertFalse(waitForExpectedElement(PLP_MINI_CART_PRICE,10).getText().equals("Shop"),"Mini cart price not updated.");
                assertFalse(waitForExpectedElement(PLP_MINI_CART_PRICE,10).getText().equals("0 €"),"Mini cart price not updated.");
                break;
            case "nl":
                assertFalse(waitForExpectedElement(PLP_MINI_CART_PRICE,10).getText().equals("0,00 €"),"Mini cart price not updated.");
                break;
        }
    }

    public void selectCrossSellProductAndVerifyPDPOpened(){
        String crossSellProductName= getWebDriver().findElements(CROSS_SELL_PRODUCT_MINICART_PRODUCT_LINK).get(0).getText().toLowerCase();
        clickFirstElementByQueryJSExecutor(CROSS_SELL_PRODUCT_MINICART_PRODUCT_LINK);
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        String pdpHeader= waitForExpectedElement(PDP_PRODUCT_SUBTITLE_UK,5).getText().toLowerCase();
        assertTrue(crossSellProductName.contains(pdpHeader));
    }
    public void clickAddToCartCTAAndVerifyProductIsAddedIntoCart(){
        int itemCountPrevious = Integer.parseInt(waitForExpectedElement(homePage.basketQty, 5).getText());
        clickFirstElementByQueryJSExecutor(CROSS_SELL_PRODUCT_MINICART_ADD_TO_CART);
        waitForElementToAppearAndDisappear(homePage.LOADER_ICON,4,5);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        int itemCountCurrent = Integer.parseInt(waitForExpectedElement(homePage.basketQty, 5).getText());
        assertTrue(itemCountCurrent == itemCountPrevious + 1);
    }

    public void verifyProductAndPriceAddedInCart(){
        assertTrue(isElementDisplayedBySeconds(PRODUCT_ITEM, 5));
        assertTrue(isElementDisplayedBySeconds(TOTAL_CART_VALUE, 5));
        String price_One = waitForExpectedElement(TOTAL_CART_VALUE).getText().replaceAll(" €", "");
        double price_one_value = Double.parseDouble(price_One.replace(',','.'));

    }

    public void verifyProductPriceAccordingToIncreaseInProductCount(){
        double price_one_value;
        int count2;
        switch (UrlBuilder.getLocale()) {
            case "vuseit":
                price_one_value = 7.90;
                count2 = Integer.parseInt(waitForExpectedElement(COUNT_VALUE_VUSEIT).getText());
                break;
            default:
                price_one_value = 7.99;
                count2 = Integer.parseInt(waitForExpectedElement(COUNT_VALUE).getText());
        }
        String price_Two = waitForExpectedElement(TOTAL_CART_VALUE).getText().replaceAll(" €", "");
        double price_two_value = Math.round(Double.parseDouble(price_Two.replace(',','.')));
        double sum = Math.round(count2 * price_one_value);
        assertEquals(sum,price_two_value);}

    public void clickMiniBasketInCart(){
        if (isElementPresent(MINI_BASKET_BUTTON_IN_BASKET_PAGE, 5)) {
            waitForAjaxElementNotToBePresent(getWebDriver(),20);
            clickUsingJS(MINI_BASKET_BUTTON_IN_BASKET_PAGE);
        }
    }

    public void assertMiniCartDisplay(){
        assertTrue(isElementPresent(MINI_CART_CLOSE_BUTTON), "Missing mini cart close button in mini cart pop-up.");
        assertTrue(isElementPresent(CHECK_OUT_BUTTON), "Missing checkout button in mini cart pop-up.");
        assertTrue(isElementPresent(PRODUCT_IMAGE_IN_MINI_POP_UP), "Missing product image in mini cart pop-up.");
        assertTrue(isElementPresent(PRODUCT_NAME_IN_MINI_POP_UP), "Missing product name in mini cart pop-up.");
    }

    public void userClickAcceptBtnCloseExceedsMaxQtyWindow(){
        assertTrue(waitForExpectedElement(EXCEED_MAX_QTY_WINDOW_TITLE_VUSEMX).getText().equals(UrlBuilder.getMessage("exceedsMaxQtyTitle.key")));
        assertTrue(waitForExpectedElement(EXCEED_MAX_QTY_WINDOW_MESSAGE_VUSEMX).getText().equals(UrlBuilder.getMessage("exceedsMaxQtyMessage.key")));
        assertTrue(waitForExpectedElement(EXCEED_MAX_QTY_WINDOW_ACCEPT_BTN_VUSEMX).getText().equals(UrlBuilder.getMessage("exceedsMaxQtyAcceptBtn.key")));
        clickUsingJS(EXCEED_MAX_QTY_WINDOW_ACCEPT_BTN_VUSEMX);
        waitForElementToAppearAndDisappear(LOADER, 2 , 20);
        assertFalse(getWebDriver().getPageSource().contains(UrlBuilder.getMessage("exceedsMaxQtyMessage.key")));
    }

    public void increaseProductQuantityAndVerifyPricingUpdate(){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float discount=0;
        if(isElementPresent(DISCOUNT_PRICE_UK)) {
            discount = getValueOfPriceString(getWebDriver().findElement(DISCOUNT_PRICE_UK).getText());
        }
        float subTotal = getValueOfPriceString(getWebDriver().findElement(PRODUCT_PRICE_UK).getText());
        float totalPrice = getValueOfPriceString(getWebDriver().findElement(TOTAL_PRICE_UK).getText());
        assertThat(Float.parseFloat(decimalFormat.format(subTotal - discount)) == totalPrice )
                .as("ERROR verifyProductCostIncludeTaxes: sub total = " + subTotal + " discount = " + discount + " total price = " + totalPrice).isTrue();
        waitForExpectedElement(INPUT_QTY_BASKET,10).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        waitForExpectedElement(INPUT_QTY_BASKET).sendKeys("2");
        waitForExpectedElement(INPUT_QTY_BASKET).sendKeys(Keys.ENTER);
        if(isElementPresent(DISCOUNT_PRICE_UK)) {
            discount = getValueOfPriceString(getWebDriver().findElement(DISCOUNT_PRICE_UK).getText());
        }
        float subTotalUpdate = getValueOfPriceString(getWebDriver().findElement(PRODUCT_PRICE_UK).getText());
        float totalPriceUpdate = getValueOfPriceString(getWebDriver().findElement(TOTAL_PRICE_UK).getText());
        assertThat(Float.parseFloat(decimalFormat.format(subTotalUpdate - discount)) == totalPriceUpdate )
                .as("ERROR verifyProductCostIncludeTaxes: sub total = " + subTotalUpdate + " discount = " + discount + " total price = " + totalPriceUpdate).isTrue();
        float expectedTotal = totalPrice*2;
        float actualTotal = totalPriceUpdate;
        assertEquals(actualTotal,expectedTotal);
    }
}

