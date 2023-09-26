package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toCollection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

@Getter
@Setter
public class PLP extends PageObject {
    PDP pdp;
    BATHelper batHelper;
    HomePage homePage = new HomePage();
    OrderSuccessPage orderSuccessPage = new OrderSuccessPage();
    private SoftAssertions softAssertions;

    // @Getter
    public By eleCategoriesTitle = By.cssSelector("div.block.filter:nth-child(1) div.block-title.filter-title > strong:nth-child(1)");
    public By eleFiltersTitle = By.cssSelector("div.block.filter:nth-child(2) div.block-title.filter-title > strong:nth-child(1)");
    public By eleFiltersTitleUk = By.xpath("//div[@class='toolbar-btns__filter filter-btn__container']//button[@class='filter-btn__button']");
    public By searchResultsHeading = By.cssSelector("span.base");
    public By productDescription = By.cssSelector("div.product.attribute.overview");
    public By eleFlavoursCategory = By.cssSelector("li.item.e-liquids:nth-child(2) > a.filter-options-title");
    public By eleFlavoursItemsLinks = By.cssSelector("li.item.e-liquids:nth-child(2) > div.item-links");
    public By addToCartButton = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link']//following::button[@id='product-addtocart-button']");
    private final static By M_PLP_ADDTOCART_FORM_VUSEUK = By.cssSelector("#product-list-plp > li:nth-child(1) > div div.product.details.product-item-details > div div.actions-primary > form");
    private final static By M_PLP_FIRST_ITEM_COLOR_VUSEUK = By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product-item-group > div.product-item-masthead > div.product-main-info > div > div > div > div > div > div > div.slick-slide.slick-current.slick-active > div > div");
    private final static By PLP_ADDTOCART_BUTTON_VUSEUK = By.xpath("//div[@class='swatch-option text selected']/ancestor::div[@class='product-item-info product-card']//button[@class='action tocart primary']");
    private final static By M_PLP_ADDTOCART_BUTTON_VUSEUK = By.cssSelector("div.fieldset > button.action.tocart.primary");
    public By basketQty = By.cssSelector("span.counter-number");
    public List<WebElement> productItems;
    public static final By ADD_TO_CART_BUTTON_UK = By.cssSelector("button#product-addtocart-button");
    public By ADD_TO_CART_BUTTON = By.cssSelector(".item:nth-child(1).product-item-inner.action");
    public By ADD_TO_CART_BUTTON_DE = By.cssSelector(".desktop-only .pagebuilder-button-primary > span");
    public static final By PRODUCT_ITEMS_REGION = By.cssSelector(".product-items, bat-productlistings");
    public static final By TOOLBAR_COUNT = By.cssSelector(".toolbar-count");
    public static final By SUPPLEMENTARY_FILED=By.cssSelector("#html-body > div.page-wrapper > div.page-main-pagebuilder-attributes > div > div > div > div > div > div > div > div > div > h2");
    public static final By SUPPLEMENTARY_CHILDREN=By.cssSelector("div > div > div > div > div > div > div> figure");

    //Lyft LAB
    public final static By LOGIN_REGISTER_TOBUY_BUTTON = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::button[@class='action tocart login show-cms-block']");
    private final static By LAB_PRODUCTS_LIST = By.cssSelector("ol.product-items.widget-product-grid");
    private final static By PRODUCTS_CHECKOUT_LIST = By.xpath("//ol[@class='minicart-items']");
    public final static By ADDTOCART_KITCHEN_BUTTON = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link']//following::button[@id='product-addtocart-button']");
    private static By STRENGTH_SIXPACK_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][contains(@title,'6 PACK') or contains(@title,'6-PACK')]//following::select[@class='super-attribute-select'][2]");
    private static By ADD_TO_CART_SIXPACK_BUTTON = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][contains(@title,'6 PACK') or contains(@title,'6-PACK')]//following::button[@id='product-addtocart-button']");
    private final static By STRENGTH_SINGLEPRODUCT_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][not(contains(@title,'PACK')) or not(contains(@title,'PACK'))]//following::select[@class='super-attribute-select']");
    public final static By STRENGTH_SINGLEPRODUCT_PDP = By.cssSelector("div:nth-child(3) > div:nth-child(2) > div:nth-child(1) select");
    private final static By STRENGTH_SINGLEPRODUCT_PDP_LYFTSE = By.cssSelector("form > div.product-main-info-attributes.product-options-select > div > select");
    public final static By QTY_SINGLEPRODUCT_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link']//following::select[@id='qty']");
    public final static By QTY_DROPDOWN_PDP = By.cssSelector("#qty");
    private static By STRENGTH_THREEPACK_DROPDOWN_DISABLED = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][contains(@title,'3 PACK') or contains(@title,'3-PACK')]//following::select[@class='super-attribute-select']/input");
    private static By STRENGTH_THREEPACK_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][contains(@title,'3 PACK') or contains(@title,'3-PACK')]//following::select[@class='super-attribute-select']");
    private final static By STRENGTH_COLLECTION_THREEPACK_DROPDOWN = By.xpath("//*[@class='product-item-link'][contains(@title,'Collection 3 PACK')]//following::select[@class='super-attribute-select']");
    public static By QTY_THREEPACK_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][contains(@title,'3 PACK') or contains(@title,'3-PACK')]//following::select[@id='qty']");
    public final static By QTY_COLLECTION_THREEPACK_DROPDOWN = By.xpath("//*[@class='product-item-link'][contains(@title,'Collection 3 PACK')]//following::select[@id='qty']");
    public static By QTY_SIXPACK_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][contains(@title,'6 PACK') or contains(@title,'6-PACK')]//following::select[@id='qty'][2]");
    private static By ADDTOCART_THREEPACK_BUTTON = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link'][contains(@title,'3 PACK') or contains(@title,'3-PACK')]//following::button[@id='product-addtocart-button']");
    private final static By ADDTOCART_COLLECTION_THREEPACK_BUTTON = By.xpath("//a[contains(@title,'Collection 3 PACK')]//following::button[@id='product-addtocart-button']");
    private final static By MINICART_PRODUCTS_LIST = By.cssSelector("ol#mini-cart");
    private final static By MINICART_PRODUCTS_NAMES = By.cssSelector("li.item.product.product-item div.product div.product-item-details > strong.product-item-name");
    private final static By MINICART_PRODUCTS_PRICES = By.cssSelector("span.minicart-price");
    private final static By MINICART_PRODUCTS_QTYS = By.cssSelector("div.details-qty.qty");
    private final static By MINICART_SEEDETAILS_LINK = By.cssSelector("div.product.options:nth-child(2) span.toggle > span:nth-child(1)");
    private final static By MINICART_LABPRODUCT_STRENGTH = By.cssSelector("div.product.options.active");
    private final static By MINICART_LABPRODUCT_STRENGTH_LYFTDK = By.cssSelector("dl.product.options.list dd.values > span:nth-child(1)");
    public final static By MINICART_PRODUCTS_QTY = By.cssSelector("input.item-qty.cart-item-qty");
    public final static By MINICART_QTY_UPDATE = By.xpath("//button[contains(@id,'update-cart-item')]");
    public final static By MINIBASKET_PRODUCTS_QTY = By.cssSelector("input.input-text.qty");
    public final static By MINIBASKET_QTY_UPDATE = By.cssSelector("[name='update_cart_action']");
    public final static By PLP_REDIRECT_LINKS = By.xpath("//a[@class='product-item-link'][contains(@href,'http')]");
    public final static By ADD_TO_CART_IT_AND_NL = By.cssSelector("form > div > button[type=\"submit\"] > span");
    private final static By ADD_TO_CART_ZA = By.cssSelector("form > div > button[type=\"submit\"] > span");
    private final static By M_ADD_TO_CART_ZA = By.cssSelector("div.slick-slide.slick-current.slick-active div.actions-primary form div.fieldset > button.action.tocart.secondary");
    private final static By M_PRODUCT_STRENGTH_FR = By.cssSelector("div.swatch-option.text");
    private final static By M_PRODUCT_STRENGTH_ZA = By.cssSelector("div.slick-slide.slick-current.slick-active div.swatch-attribute.vuse_strength_percentage > div.swatch-attribute-options.clearfix > div.swatch-option.text");
    private static final By M_PRODUCT_STRENGTH_IT = By.cssSelector("div.swatch-attribute.vuse_strength > div.swatch-attribute-options.clearfix > div.swatch-option.text");
    private final static By FIRST_ADDTOCART_BTN_LYFTSE = By.cssSelector("div.product.actions.product-item-actions > div > form > button");
    private final static By VYPE_FIRST_ADDTOCART_BTN_DK = By.cssSelector("#product-list-plp > li:not([style*='none']) .action.tocart.primary");
    private final static By FIRST_PRODUCT_IMAGE_LYFTSE = By.cssSelector(".product-image-photo");
    private static final By VUSE_DE_PRODUCT_TOTAL_NUMBER = By.cssSelector(".column.main p.toolbar-count span");
    private static final By VUSE_DE_PRODUCT_REVIEW_FIRST_STAR_NUMBER = By.cssSelector("i.icon-star-full:nth-child(1)");
    public static final By VUSE_DE_FIRST_PRODUCT = By.cssSelector("#product-list-plp > li:not([style*='none'])");
    public static final By VUSE_DE_PRODUCT_STRENGTH = By.cssSelector("#product-list-plp > li:nth-child(1) .swatch-option.text");
    public static final By VUSE_DE_PLP_ADD_TO_CART = By.cssSelector("#product-list-plp > li:nth-child(1) .action.tocart.primary");
    public static final By VUSE_DE_CLOSE_MINI_CART_PANEL = By.cssSelector("#btn-minicart-close");
    public final static By VELO_BE_PLP_MINI_CART_CLOSE_BUTTON = By.cssSelector("bat-minicart-avalanche.bat > div.bat-minicart > button.bat-minicart-close");
    public static final By M_CLOSE_MINI_CART_PANEL_VUSEUK = By.cssSelector("#btn-minicart-close");
    public static final By M_CLOSE_MINI_CART_DIALOG_VUSEUK = By.cssSelector("div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.mage-dropdown-dialog");
    public final static By PRODUCT_COLOR_SWATCH = By.cssSelector("ol#product-list-plp li:nth-child(1) div.product-item-masthead div.swatch-option.color");
    public final static By PRODUCT_COLOR_SWATCH_VUSEIT = By.cssSelector("ol#product-list-plp li:nth-child(2) div.product-item-masthead div.swatch-option.color");
    public final static By PRODUCT_COLOR_SWATCH_VUSEFR = By.cssSelector("ol#product-list-plp li:nth-child(2) div.swatch-option.color");
    private final static By M_PRODUCT_COLOR_SWATCH_VUSEUK = By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product-item-group > div.product-item-masthead > div.product-main-info div > div.slick-slide.slick-current.slick-active > div > div");
    private final static By PRODUCT_COLOR_SWATCH_SELECTED = By.cssSelector("ol#product-list-plp li:nth-child(1) div.product-item-masthead div.swatch-option.color.selected");
    private final static By SELECTED_PRODUCT_COLOR = By.xpath("//div[@class='swatch-option color selected'][@data-option-type='1' or @option-type='1']");
    private final static By SELECTED_PRODUCT_IMAGE = By.cssSelector("img.product-image-photo");
    private final static By SELECTED_PRODUCT_LABEL = By.cssSelector("span.selected-colour__value");
    public final static By STRENGTH_SELECTED = By.cssSelector("div.swatch-option.text.selected");
    private final static By STRENGTH_SELECTED_VUSEUK = By.xpath("//div[@class='field qty'][@style='display: block;']/ancestor::div[@class='product-item-info product-card']//div[@class='swatch-option text selected']");
    private final static By TOFINO_STRENGTH_VALUE_MINICART = By.cssSelector("span.value");
    private final static By TOFINO_STRENGTH_VALUE_PAYMENTPAGE = By.cssSelector("dl.item-options>dd.values");
    private final static By TOFINO_STRENGTH_VALUE_ORDER_SUMMARY = By.cssSelector("dl.item-options>dd");
    public final static By FILTERS_BUTTON = By.cssSelector("button.filter-btn__button");
    private final static By plusIcon = By.xpath("//span[@class='items-in-cart-heading']");
    public final static By FILTERS_SECTION_FLYOUT = By.cssSelector("div#layered-filter-block");
    public final static By CONSUMABLE_TYPE_FILTERS_HEADING = By.cssSelector("div.filter-options-content ol.items li.item.active > h3.filter__heading");
    private final static By CONSUMABLE_TYPE_FILTERS_LIST = By.cssSelector("div.item-links > a");
    public final static By PRODUCT_STRENGTH_SWATCH = By.cssSelector("ol#product-list-plp li:nth-child(1) div.product-item-masthead div.swatch-option.text");
    public final static By SUB_PRODUCT_STRENGTH_SWATCH = By.cssSelector("a[href*=\"epod-caps/blushed-mango\"] + div div.swatch-option");
    public final static By SUB_PRODUCT_SUB_OPTION = By.cssSelector("a[href*=\"epod-caps/blushed-mango\"] + div div.field.choice.subscription");
    private static final By ADD_TO_BASKET_SUCCESS_MSG = By.cssSelector("div.message-success");
    public final static By PRODUCT_STRENGTH_SWATCH_VUSEUK = By.xpath("//div[@class='field qty'][@style='display: block;']/ancestor::div[@class='product-item-info product-card']//div[@class='swatch-option text']");
    public final static By PRODUCT_SELECTED_STRENGTH = By.cssSelector("li.item.product.product-item.product-list__item--visible div.swatch-option.text.selected");
    public final static By SUBSCRIPTION_OPTION_PLP_VUSEFR = By.cssSelector("ol#product-list-plp li:nth-child(1) div.subscription>div>input");
    public final static By QTY_SELECTOR_PLP = By.cssSelector("ol#product-list-plp li:nth-child(1) input[name='qty'][title='Quantity']");
    public final static By QTY_SELECTOR_PLP_UK=By.cssSelector("ol#product-list-plp li input[name='qty'][title='Quantity']");
    public final static By QTY_SELECTOR_PLP_VUSEFR = By.cssSelector("ol#product-list-plp li:nth-child(1) input[name='qty']");
    public final static By QTY_SELECTOR_PLP_VUSEUK = By.xpath("//div[@class='field qty'][@style='display: block;']/ancestor::div[@class='product-item-info product-card']//input[@name='qty']");
    private final static By QTY_SELECTOR_PLUS_BUTTON = By.xpath("//input[@name='qty'][contains(@style,'block')]//following::input[1]");
    private final static By QTY_SELECTOR_PLUS_BUTTON_VUSEFR = By.cssSelector("ol#product-list-plp li:nth-child(1) input.plus");
    public final static By QTY_SELECTOR_MINUS_BUTTON = By.xpath("//input[contains(@class,'button-minus')][@data-field='qty']");
    public final static By ADD_TO_BASKET_PLP = By.cssSelector("ol#product-list-plp li:nth-child(1) button[title='Add to basket']");
    public final static By ADD_TO_BASKET_PLP_UK = By.cssSelector("ol#product-list-plp li button[title='Add to basket']");
    public final static By ADD_TO_BASKET_PLP_VUSEFR = By.cssSelector("ol#product-list-plp li:nth-child(2) button");
    private final static By ADD_TO_BASKET_BUTTON_PLP = By.xpath("//button[@class='action tocart primary'][not (@disabled)]");
    private final static By ADD_TO_BASKET_BUTTON_PLP_CO = By.xpath("//a[not(contains(@href,'bundle'))][@class='product-item-link']//following::button[@class='action tocart primary'][not (@disabled)]");
    private final static By ADD_TO_BASKET_BUTTON_PLP_VUSEUK = By.xpath("//div[@class='field qty'][@style='display: block;']/ancestor::div[@class='product-item-info product-card']//button[@title='Add to basket']");
    private static final By CBD_STRENGTH_LABEL_PLP = By.xpath("//*[text()='Available CBD Strengths']");
    public static final By PRODUCTS_VUSEDE = By.cssSelector("div.product-item-info");
    public static final By STRENGTHS_VUSEDE = By.cssSelector("div[class='swatch-option text']");
    public static final By STRENGTHS_VUSEDE_DISABLED = By.cssSelector("div[class='swatch-option text'][option-empty='true']");
    public static final By BUTTON_VUSEDE = By.cssSelector("button");
    public static final By BUTTON_VUSEUK = By.cssSelector("button.action.tocart.primary");
    public static final By BUTTON_PLP_SUBSCRIBE_OPTION = By.cssSelector("[data-bind='scope\\: \\'subscription-container-4005\\''] [data-bind='attr\\: \\{for\\:productId\\+\\'_\\'\\+subscriptionOption\\}']");
    public static final By ADD_TO_CART_VUSEIT_IPAD = By.cssSelector("button.action.tocart.primary");
    private static final By CLICK_ON_DEVICE_MENU = By.cssSelector("ul > li.level0.category-item.forth > a:nth-child(2)");
    private static final By CLICK_ON_DEVICE_MENU_GLOIT = By.cssSelector("li.level0> a[href*='shop']");
    private static final By M_CLICK_ON_DEVICE_MENU_GLOIT = By.cssSelector("li.burger-menu-item > a[href*='shop']");
    private static final By DEVICE_MENU_GLOKZ=By.cssSelector("div.slick-track> div:nth-child(2) > div > a");
    private static final By PLP_PRODUCT_NAME = By.cssSelector("div.product.name>a");
    public static final By PLP_PRODUCT_NAME_GLOIT = By.cssSelector("a.product-item-link");
    public static final By PLP_PRODUCT_NAME_VELO= By.cssSelector("div.bat-productlistings-card-name");
    private static final By PLP_PRODUCT_NAME_GLODE = By.cssSelector("#product-list-plp .product-item:nth-child(2)");
    private static final By PLP_FLAVOUR_SUBSCRIPTION_MODAL = By.cssSelector(".learn-more.subscribepro-learn-more-5020 span");
    private static final By CLOSE_SUBSCRIPTION_VUSE_FR = By.cssSelector(".modals-wrapper .action-close");
    private static final By PLP_PRODUCT_PRICE = By.cssSelector("div.price > div > span");
    private static final By PLP_PRODUCT_PRICE_GLOIT = By.cssSelector("span[data-price-type='finalPrice']");
    private static final By PLP_PRODUCT_PRICE_VELO = By.cssSelector("div.bat-productlistings-card-prices>span");
    private static final By PLP_PRODUCT_BY_NOW = By.cssSelector("div.initial-cta > span");
    private static final By PLP_PRODUCT_CTA_GLOIT = By.cssSelector("div > div > div.product-item-inner > div > div.actions-primary > form > button");
    private static final By FIRST_BUYABLE_PRODUCT_FR = By.xpath("//div[@class='field qty']/ancestor::div[@class='product-item-info product-card']");
    public static final By FIRST_COLOUR_SWTACH_FR = By.xpath("//div[@class='field qty']/ancestor::div[@class='product-item-info product-card']//div[@class='swatch-option color']");
    private static final By FIRST_STRENTH_SWTACH_FR = By.xpath("//div[@class='field qty']/ancestor::div[@class='product-item-info product-card']//div[@class='swatch-option text']");
    private static final By VYPE_PLP_HERO = By.cssSelector("div.category-banner__foreground");
    public static final By PRODUCT_ACTUAL_QTY = By.cssSelector("span.icon-bag > span > span.counter-number");
    public final static By MINICART_QTY_UPDATE_KZ = By.cssSelector("input.qty-modifier.plus");
    private static final By CTA_PERSONALIZED_PRODUCT = By.cssSelector("button[class='action tocart-personalised primary-personalised'] span");
    private static final By PERSONALIZED_PRODUCT = By.xpath("//img[contains(@alt,'ePod 2.0 - Personalizado')]");
    private static final By KNOW_MORE_ABOUT_PRODUCT = By.cssSelector("[alt*='Pods para ePod - Cucumber Fizz']");
    private static final By SUBSCRIPTION_LINK = By.cssSelector("a.alink");
    public static final By COLORS_VUSEDE = By.cssSelector("div[class='swatch-option color']");
    private static final By PLP_FILTER_ONE = By.cssSelector("div.block.filters__container > div div  > ol > li:nth-child(1)");
    private static final By PLP_FILTER_TWO = By.cssSelector("div.block.filters__container > div div  > ol > li:nth-child(3)");
    private static final By EMPTY_FILTER = By.cssSelector("div.block.filters__container > div > div > p > button");

    //GuidedSell
    private static final By GET_DEVICE = By.cssSelector("div.device-heading>span");
    private static final By FIRST_FAVOURATE_TAB = By.cssSelector("#guided-sell-product-tabs > span:nth-child(1)");
    private static final By SECOND_FAVOURATE_TAB = By.cssSelector("#guided-sell-product-tabs > span:nth-child(2)");
    private static final By SECOND_FAVOURATE_TAB_ACIVE = By.cssSelector("#guided-sell-product-tabs > span.flav-tab.flav-active");
    private static final By VIEW_ALL_DEVICES = By.cssSelector("div.section.section-more > div > div.action > a:nth-child(1)");
    private static final By VIEW_ALL_FLAVOURS = By.cssSelector("div.section.section-more > div > div.action > a:nth-child(2)");
    private static final By START_AGAIN_BTN = By.cssSelector("div.section.section-restart > div > div.action > a");
    private static final By VIEW_ALL_RESULT = By.cssSelector("#maincontent > div.columns > div > div.toolbar.toolbar-products > p");
    private static final By INPUT_ITEM_QUANTITY_PLP = By.cssSelector("input[name='qty']");
    private static final By PLUS_ICON_MINICART = By.cssSelector("input[class^='qty-modifier plus button-plus']");
    private static final By PRODUCT_ADD_TO_CART_BUTTON = By.cssSelector(".product-item-actions .action.tocart.primary");
    //CLP HealthWarning for PLP
    public static final By HEALTH_WARNING_POPUP = By.cssSelector("aside[class*='modal-popup add-to-cart-confirm']");
    public static final By HEALTH_WARNING_POPUP_BACK_BUTTON = By.cssSelector("button.action-secondary.action-dismiss");
    public static final By HEALTH_WARNING_POPUP_INTHECART_BUTTON = By.cssSelector("button.action-primary.action-accept");
    public static final By FILTER_OPTION = By.cssSelector("button.filter-btn__button");
    public static final By FILTER_OPTION_VELOZA = By.cssSelector("div.bat-filter-button > button");
    public static final By FILTER_OPTION_VELOPL = By.cssSelector(".column.main > .toolbar.toolbar-products > .toolbar-btns__container > .filter-btn__container.toolbar-btns__filter > .filter-btn__button");
    public static final By SORTING_OPTION = By.cssSelector("select.sort-btn__select");
    public static final By SORTING_OPTION_VELOZA = By.cssSelector("div.bat-sort-button > button");
    public static final By SORTING_OPTION_VELOPL = By.xpath("/html//main[@id='maincontent']/div[@class='columns']/div/div[@class='toolbar toolbar-products']/div[@class='toolbar-btns__container']/div[1]/select[@class='sort-btn__select']");
    public static final By FILTER_FINISHED_BUTTON = By.cssSelector("button.overlay-sidebar__btn-done.action.primary");
    public static final By FILTER_TYPE_HEADING = By.cssSelector("h3.filter__heading");
    public static final By FILTER_BY_PRODUCT_ITEM = By.cssSelector("input.filter__checkbox[name='vuse-type[]']");
    public static final By FILTER_BY_PRODUCT_ITEM_COUNT = By.xpath("//li[input[@class='filter__checkbox' and @name='vuse-type[]']]/label/span");
    public static final By SUBSCRIPTION_WORDING_PDP = By.cssSelector("div.subscription-title");
    public static final By SUBSCRIPTION_WORDING_BASKETPAGE = By.cssSelector("p.subscription-prompt");
    public static final By VUSE_COLOR_CHECKBOX = By.cssSelector("input.filter__checkbox[name='vuse-color[]']");
    public static final By VUSE_COLOR_CHECKBOX_COUNT = By.xpath("//li[input[@class='filter__checkbox' and @name='vuse-color[]']]//span");
    public static final By FILTER_APPLY_BUTTON = By.cssSelector("button.overlay-sidebar__btn-done.action.primary");
    public static final By RESULT_COUNT = By.cssSelector("p.toolbar-count>span");
    public static final By VUSE_TYPE_OF_PRODUCT_CHECKBOX = By.cssSelector("input.filter__checkbox[name='vuse-product-type[]']");
    public static final By VUSE_TYPE_OF_PRODUCT_COUNT = By.xpath("//li[input[@class='filter__checkbox' and @name='vuse-product-type[]']]//span");
    public static final By SUBCATEGORY_LINK = By.cssSelector("a.item-link");
    public static final By SUBCATEGORY_LINK_ACTIVE = By.cssSelector("a.item-link.current-page");
    public static final By CIGARETTE_PRODUCT_TYPE_FILTER_OPTIONS = By.xpath("//li[input[@class='filter__checkbox' and @name='vuse-type[]']]/label");
    public static final By CIGARETTE_COLOR_TYPE_FILTER_OPTIONS = By.xpath("//li[input[@class='filter__checkbox' and @name='vuse-color[]']]/label");
    public static final By CIGARETTE_LIMITED_EDITION_FILTER_OPTIONS = By.xpath("//li[input[@class='filter__checkbox' and @name='vuse-limited-editions[]']]/label");
    public static final By CIGARETTE_DEVICES_FILTER_OPTIONS = By.cssSelector("a.item-link");
    public static final By STARTERKIT_PRODUCT_TYPE_FILTER_OPTIONS = By.xpath("//li[input[@class='filter__checkbox' and @name='vuse-product-type[]']]/label");
    public static final By NICOTINE_LEVEL_STRENGTH_LABEL = By.xpath("//span[@class='swatch-attribute-label'][contains(text(),'Nicotine Level')]");
    public static final By PRODUCT_SHORT_DESCRIPTION = By.cssSelector("p.product-short-description-text");
    public static final By PRODUCT_STRENGTH_LABEL = By.xpath("//span[@class='swatch-attribute-label']");
    public static final By CBD_PRODUCT_STRENGTH_LABEL = By.xpath("//*[@class='product-item-link'][contains(text(),'CBD')]//following::span[@class='swatch-attribute-label'][1]");
    public static final By PRODUCT_ADD_TO_CART = By.cssSelector("button");
    private static final By VUSE_UK_PRODUCT_REVIEW_FIRST_STAR_NUMBER = By.cssSelector("div[data-bv-show='inline_rating']");
    private static final By FILTER_OPTION_FR = By.cssSelector("div.columns > div > div.toolbar.toolbar-products > div.toolbar-btns__container > div.toolbar-btns__filter.filter-btn__container > button");
    private static final By FIRST_TEXT = By.cssSelector("#qs-option-0");

    //vuse for PLP
    private static final By ONE_IIME_PURCHASE=By.cssSelector("#product-list-plp>li:nth-child(1) div.one_time");
    private static final By ONE_IIME_PURCHASE_TEXT=By.cssSelector("#product-list-plp>li:nth-child(1) span.option_price>span.label");
    private static final By SUBSCRIPTION_FROM_SELECT=By.cssSelector("#product-list-plp>li:nth-child(1) div.field.choice.subscription.active");
    private static final By SUBSCRIPTION_TEXT=By.cssSelector("#product-list-plp>li:nth-child(1) div.field.choice.subscription>div>label>span.option_price>span.label");
    private static final By SUBSCRIPTION_INFO=By.cssSelector("#product-list-plp>li:nth-child(1) span.learn-more");
    private static final By SUBSCRIPTION_POPUP=By.cssSelector("div.modal-inner-wrap > div.modal-content > div.subscription-modal");
    public static final By CATEGORYPAGE=By.cssSelector("#html-body > div.page-wrapper > div.category-view");
    private static final By SORT=By.cssSelector("div.columns > div > div.toolbar.toolbar-products > div.toolbar-btns__container > div.toolbar-btns__sort.sort-btn__container");
    private static final By FILTER_VIEW=By.cssSelector("div.page-wrapper > aside > div.overlay-sidebar__drawer");
    private static final By CHOOSE_STRENGTH=By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product.details.product-item-details > div > div > div > form > div > button > span");
    private static final By ADD_TO_CART_PLP=By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product.details.product-item-details > div > div > div > form > div > button");
    private static final By FILTER_DONE=By.cssSelector("aside > div.overlay-sidebar__drawer > button");
    private static final By FIRST_PRODUCT=By.cssSelector("#product-list-plp > li:nth-child(1) > div");
    private static final By FIRST_ACCESSORIES=By.cssSelector("#product-list-plp > li.item.product.product-item.twispvape > div > a");
    private static final By CHOOSE_SIZE=By.cssSelector("#product-list-plp > li.item.product.product-item.twispvape > div > div.product-item-group > div.product-item-masthead > div.product-main-info");
    private static final By FIRST_CHARGER=By.cssSelector("#product-list-plp > li:nth-child(1) > div > a > span > span > img");
    private static final By SEARCH_BAR=By.cssSelector("#search");
    private static final By SEARCH_BUTTON=By.cssSelector("#search_mini_form > div.field.search > div > div > button");
    private static final By SHOP_FLAVOURS=By.xpath("//*[@id=\"html-body\"]/div[3]/header/nav/div[1]/div/div/ul/li[2]/a[2] ");
    private static final By PROMOTION_FLAVOURS=By.cssSelector("div.pagebuilder-column.flyout-image-content.long-text.second > div > a > div > div");
    private static final By ADD_TO_BASKET=By.cssSelector("#product-addtocart-button");
    private static final By VIEW_BASKET=By.cssSelector("div.actions.minicart-actions > div > a");
    private static final By FIRST_PRODUCT_ZA=By.cssSelector("#product-list-plp > li:nth-child(1) > div > a");
    private static final By FIRST_PRODUCT_IMAGE=By.cssSelector("#product-list-plp > li:nth-child(1) > div > a > span > span > img");
    private static final By ADD_TO_CART_PRODUCT_PLP=By.cssSelector("div.product-item-info.product-card button.action.tocart.primary");
    private static final By FIRST_PRODUCT_ADDTO_CART=By.cssSelector("button[type='submit']");
    private static final By FIRST_COLOR_IMAGE=By.cssSelector("div[class='swatch-option color']");
    private static final By IN_STOCK_PRODUCTS = By.xpath("//a[@class='product-item-link']//following::div[contains(@class,'swatch-option text')][1][not(@class='swatch-option text pointer-event-reset')]");
    private static final By TOTAL = By.cssSelector("p.toolbar-count span:nth-child(1)");
    private static final By OUT_STOCK_PRODUCTS = By.xpath("//div[div[@class='swatch-option text pointer-event-reset' and @index='0']]");
    private static final By TOTAL_COUNT = By.cssSelector("div.columns > div > div.toolbar.toolbar-products > p > span");
    private static final By TOTAL_PRODUCTS = By.cssSelector("#product-list-plp > li");
    public static final By QTY_MINUS_BUTTON=By.xpath("//div[@class='field qty'][@style='display: block;']//input[contains(@class,'button-minus')]");
    private static final By SORT_PL = By.xpath("/html/body/div[3]/main/div[2]/div/div[3]/div[3]/div[1]/select");
    private static final By PRODUCT_PRICE_LIST = By.cssSelector("price-box price-final_price");
    public static final By FIND_YOUR_MATCH_LINK = By.cssSelector("div:nth-child(3) > div > div.pagebuilder-column-group > div > div > div > a");
    public static final By FIND_YOUR_MATCH_LINK_LIVE = By.cssSelector("a.pagebuilder-button-secondary");
    public static final By QTY_EXCEEDED_ERROR_MESSAGE_VUSEFR = By.cssSelector("div.message-warning.warning.message");
    private static final By EQUIPMENT_CATEGORIES=By.cssSelector("#narrow-by-list > div > ol > li > div > a");
    private static final By FIRST_PRODUCT_LINK=By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product-item-group > div.product.name > a");
    public static final By QUICK_FILTER_LIST=By.cssSelector("div.column.main>div.toolbar.toolbar-products ul.toolbar-easy-filter-list label");
    public static final By FILTERED_PRODUCT_LIST=By.cssSelector("ol.products.list.items.product-items li.item.product.product-item.product-list__item--visible");
    private static final By ZERO_MG_STRENGTH=By.cssSelector("div.columns > div >div.toolbar.toolbar-products > div.toolbar-btns__container > div.toolbar-easy-filter-container > ul > li:nth-child(1) > label");
    private static final By SIX_MG_STRENGTH=By.cssSelector("div.columns > div > div.toolbar.toolbar-products > div.toolbar-btns__container > div.toolbar-easy-filter-container > ul > li:nth-child(3) > label");
    private static final By ZERO_MG_STRENGTH_CLASSIC=By.cssSelector("div.filters__group.active.filters__group--filtering > div > ol > li:nth-child(1) > label");
    private static final By SIX_MG_STRENGTH_CLASSIC=By.cssSelector("div.filters__group.active.filters__group--filtering > div > ol > li:nth-child(3) > label");
    private static final By FINISH_SELECTION=By.cssSelector("div.overlay-sidebar__drawer > button");
    private static final By PRODUCT_NAME=By.cssSelector("li:nth-child(1) > div > div.product-item-group > h3 > a");
    private static final By PRODUCT_PRICE=By.cssSelector("li:nth-child(1) div div.product-item-group > div div.price-box.price-final_price > span");
    private static final By ADD_TO_CART=By.cssSelector("li:nth-child(1) > div > div.product-item-group > h3 > a");

    // avalanche
    public static final By AVALANCHE_PLP_PRODUCT = By.cssSelector("div.bat-productlistings-card");
    public static final By AVALANCHE_PLP_BUYABLE_PRODUCT = By.cssSelector("div[data-instock='IN_STOCK'] > div.bat-productlistings-card-details");
    public static final By AVALANCHE_PRODUCT_STRENGTH = By.cssSelector("button[class*='strength-']");
    public static final By AVALANCHE_ADD_TO_CART_BUTTON = By.cssSelector("button.bat-cta-style.button-dark.center.active > span");
    public static final By AVALANCHE_CONFIRMATION_MESSAGE = By.cssSelector("body > div.bat-wrapper > div > div > div:nth-child(3) > div > div > bat-messagebar-default > div > div > div.bat-messagebar--default-message.active > p:nth-child(3) > a");
    public static final By AVALANCHE_CONFIRMATION_MESSAGE_BE = By.cssSelector("body > div.bat-wrapper > div > div > div:nth-child(3) > div > div > bat-messagebar-default > div > div > div.bat-messagebar--default-message.active > p:nth-child(3) > span > a");

    // headful
    public static final By HEADFUL_PLP_PRODUCT = By.cssSelector(".product-card.product-item-info");
    public static final By HEADFUL_PRODUCT_STRENGTH = By.cssSelector(".swatch-opt-4893 > .swatch-attribute.velo_strength > div[role='listbox'] > div:nth-of-type(1)");
    public static final By HEADFUL_ADD_TO_CART_BUTTON = By.cssSelector("li:nth-of-type(1) > .product-card.product-item-info form[method='post']  button[title='Dodaj do koszyka'] > span");
    public static final By HEADFUL_CONFIRMATION_MESSAGE = By.cssSelector(".message.message-success.success  a");
    public static final By PLP_PRODUCT_NAME_PL = By.cssSelector("li:nth-of-type(1) > .product-card.product-item-info > .product-item-group > .name.product");
    public static final By PLP_PRODUCT_STRENGTH_MILD_PL = By.cssSelector(".swatch-opt-4893 > .swatch-attribute.velo_strength > div[role='listbox'] > div:nth-of-type(1)");
    public static final By PLP_PRODUCT_STRENGTH_MEDIUM_PL = By.cssSelector(".swatch-opt-4893 > .swatch-attribute.velo_strength > div[role='listbox'] > div:nth-of-type(2)");
    public static final By PLP_PRODUCT_SINGLE_STRENGTH_PL = By.cssSelector("div.product-strength.velo_strength");
    public static final By PLP_SIMPLE_PRODUCT_PL = By.cssSelector ("div.product-strength.velo_strength");
    public static final By PLP_SIMPLE_PRODUCT_ADD_TO_CART_PL = By.cssSelector("li:nth-of-type(3) > .product-card.product-item-info form[method='post']  button[title='Dodaj do koszyka'] > span");
    public By lnkProductItem = By.cssSelector("div.product-item-group a.product-item-link");

    List<WebElement> allQuickfilters;

    public static final By AVALANCHE_PLP_PRODUCT_NAME = By.cssSelector("div.bat-productlistings-card-name > a");
    public PLP() {
    }
    private SearchResult searchResult;
    private ScenarioContext scenarioContext;
    public PLP(PDP pdp, SearchResult searchResult, ScenarioContext scenarioContext, SoftAssertions softAssertions)
    {
        this.pdp = pdp;
        this.searchResult = searchResult;
        this.scenarioContext = scenarioContext;
        this.softAssertions = softAssertions;
    }

    public void scrollNthProductToCenter(int index) {
        List<WebElement> resultItems = getWebDriver().findElements(ADD_TO_CART_BUTTON_DE);
        if (index < resultItems.size())
            jsScrollElementInCenter(resultItems.get(index));
    }

    public void clickItemVerifyResultsAndNavigateToPDP(String strSelectedItemLink) {
        List<WebElement> resultItems = getWebDriver().findElements(By.cssSelector("a.product-item-link"));
        for (WebElement item : resultItems) {
            String productInLoopName = item.getText();
            if (productInLoopName.contains((strSelectedItemLink))) {
                clickByElementByQueryJSExecutor(By.cssSelector("a.product-item-link"));
                assertTrue(waitForExpectedElement(productDescription, 30).isDisplayed());
                break;
            }
        }
    }

    public void clickItemVerifyResultsWithColorSwatch(String strColor) throws Throwable {
        List<WebElement> resultItems = getWebDriver().findElements(By.cssSelector("[class='swatch-option color'][aria-label='" + strColor + "']"));
        for (WebElement item : resultItems) {
            if (item.isDisplayed() && item.isEnabled()) {
                item.click();
                sleep(2000);
                assertTrue(waitForExpectedElement(By.cssSelector("[class='swatch-option color selected'][aria-label='" + strColor + "']"), 5).isDisplayed());
                break;
            }
        }
    }

    public void assertTextOfTheAddToCartButtonBeforeLogin(String strExpectedText) {
        waitForExpectedElement(LOGIN_REGISTER_TOBUY_BUTTON, 10);
        assertTrue(getWebDriver().findElements(LOGIN_REGISTER_TOBUY_BUTTON).get(0).getText().equalsIgnoreCase(UrlBuilder.getMessage(strExpectedText)));
    }

    public void assertTextOfTheAddToCartButtonAfterLogin(String strExpectedText) {
        waitForExpectedElement(ADDTOCART_KITCHEN_BUTTON, 10);
        assertTrue(getWebDriver().findElements(ADDTOCART_KITCHEN_BUTTON).get(0).getText().equalsIgnoreCase(UrlBuilder.getMessage(strExpectedText)));
    }

    public void userSelectStrengthAndQuantityForSixPackLabProduct(String strStrength, String strQuantity) throws Throwable {
        waitForExpectedElement(LAB_PRODUCTS_LIST, 10);
        try {
            scrollElementIntoView(STRENGTH_SIXPACK_DROPDOWN);
        }catch(Exception e){
            STRENGTH_SIXPACK_DROPDOWN=By.cssSelector("#attribute300");
            QTY_SIXPACK_DROPDOWN=By.cssSelector("#qty");
            ADD_TO_CART_SIXPACK_BUTTON=By.cssSelector("#product-addtocart-button");
            scrollElementIntoView(STRENGTH_SIXPACK_DROPDOWN);
        }
        if(getWebDriver().findElements(STRENGTH_THREEPACK_DROPDOWN_DISABLED).size()>0){
            if (getWebDriver().findElements(QTY_SIXPACK_DROPDOWN).size() > 0)
                selectValueFromDropDownByby(strQuantity, QTY_SIXPACK_DROPDOWN);
            clickFirstElementByQueryJSExecutor(ADD_TO_CART_SIXPACK_BUTTON);
            waitForExpectedElement(basketQty, 10);
        }
        else {
            if (UrlBuilder.getMessage(strStrength).equals("Regular"))
                selectValueFromDropDownByby(strStrength, STRENGTH_SIXPACK_DROPDOWN);
            if (UrlBuilder.getMessage(strStrength).equals("Strong"))
                selectValueFromDropDownByby(strStrength, STRENGTH_SIXPACK_DROPDOWN);
            waitForExpectedElement(STRENGTH_SIXPACK_DROPDOWN).click();
            if (getWebDriver().findElements(QTY_SIXPACK_DROPDOWN).size() > 0)
                selectValueFromDropDownByby(strQuantity, QTY_SIXPACK_DROPDOWN);
            clickFirstElementByQueryJSExecutor(ADD_TO_CART_SIXPACK_BUTTON);
            waitForExpectedElement(basketQty, 10);
        }
    }

    public void userSelectStrengthAndQuantityForLabProduct(String strStrength, String strQuantity) throws Throwable {
        if (getWebDriver().findElements(STRENGTH_SIXPACK_DROPDOWN).size() > 0) {
            userSelectStrengthAndQuantityForSixPackLabProduct(strStrength, strQuantity);
        }
        if (getWebDriver().findElements(STRENGTH_SINGLEPRODUCT_DROPDOWN).size() > 0) {
            userSelectStrengthForSinglePackLabProduct(strStrength, strQuantity);
        }
        if (getWebDriver().findElements(STRENGTH_THREEPACK_DROPDOWN).size() > 0) {
            userSelectStrengthForThreePackLabProduct(strStrength, strQuantity);
        }
    }

    public void userSelectStrengthForSixPackLabProduct(String strStrength) {
        waitForExpectedElement(LAB_PRODUCTS_LIST, 10);
        clickFirstElementByQueryJSExecutor(ADD_TO_CART_SIXPACK_BUTTON);
        scrollElementIntoView(STRENGTH_SIXPACK_DROPDOWN);
        if (UrlBuilder.getMessage(strStrength).equals("Regular"))
            waitForExpectedElement(STRENGTH_SIXPACK_DROPDOWN,5).sendKeys("Re");
        if (UrlBuilder.getMessage(strStrength).equals("Strong"))
            waitForExpectedElement(STRENGTH_SIXPACK_DROPDOWN).sendKeys("St");
        if(getWebDriver().findElements(STRENGTH_THREEPACK_DROPDOWN_DISABLED).size()==0)
            waitForExpectedElement(STRENGTH_SIXPACK_DROPDOWN).click();
        clickFirstElementByQueryJSExecutor(ADD_TO_CART_SIXPACK_BUTTON);
        waitForExpectedElement(basketQty, 10);
    }

    public void userSelectStrengthForSinglePackLabProduct(String strStrength, String strQuantity) throws Throwable {
        waitForExpectedElement(LAB_PRODUCTS_LIST, 10);
        if(getWebDriver().findElements(STRENGTH_THREEPACK_DROPDOWN_DISABLED).size()>0){
            selectValueFromDropDownByby(strQuantity, QTY_SINGLEPRODUCT_DROPDOWN);
            clickFirstElementByQueryJSExecutor(addToCartButton);
            waitForAjaxElementNotToBePresent(getWebDriver(),8);
        }
        else {
            scrollElementIntoView(STRENGTH_SINGLEPRODUCT_DROPDOWN);
            waitForExpectedElement(STRENGTH_SINGLEPRODUCT_DROPDOWN).click();
            if (UrlBuilder.getMessage(strStrength).equals("Regular")) {
                try {
                    selectValueFromDropDownByby(strStrength, STRENGTH_SINGLEPRODUCT_PDP);
                } catch (NullPointerException e) {
                    LOG.info("sometime can't find the element in Lyft SE");
                    selectValueFromDropDownByby(strStrength, STRENGTH_SINGLEPRODUCT_PDP_LYFTSE);
                }
            }
            if (UrlBuilder.getMessage(strStrength).equals("Strong"))
                selectValueFromDropDownByby(strStrength, STRENGTH_SINGLEPRODUCT_PDP);
            try {
                waitForExpectedElement(STRENGTH_SINGLEPRODUCT_DROPDOWN).click();
            } catch (ElementClickInterceptedException e) {
                clickUsingJS(STRENGTH_SINGLEPRODUCT_DROPDOWN);
            }
            selectValueFromDropDownByby(strQuantity, QTY_SINGLEPRODUCT_DROPDOWN);
            clickFirstElementByQueryJSExecutor(addToCartButton);
            waitForAjaxElementNotToBePresent(getWebDriver(),8);
        }
    }

    public void userSelectStrengthForThreePackLabProduct(String strStrength, String strQuantity) throws Throwable {
        waitForExpectedElement(LAB_PRODUCTS_LIST, 10);
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                try {
                    scrollElementIntoView(STRENGTH_THREEPACK_DROPDOWN);
                } catch (Exception e) {
                    STRENGTH_THREEPACK_DROPDOWN = By.cssSelector("#attribute300");
                    QTY_THREEPACK_DROPDOWN = By.cssSelector("#qty");
                    ADDTOCART_THREEPACK_BUTTON = By.cssSelector("#product-addtocart-button");
                    scrollElementIntoView(STRENGTH_SIXPACK_DROPDOWN);
                }
                if(getWebDriver().findElements(STRENGTH_THREEPACK_DROPDOWN_DISABLED).size()>0){
                    waitForExpectedElement(QTY_THREEPACK_DROPDOWN).click();
                    selectValueFromDropDownByby(strQuantity, QTY_THREEPACK_DROPDOWN);
                    clickFirstElementByQueryJSExecutor(ADDTOCART_THREEPACK_BUTTON);
                    waitForAjaxElementNotToBePresent(getWebDriver(),8);
                }
                else {
                    waitForExpectedElement(STRENGTH_THREEPACK_DROPDOWN).click();
                    if (UrlBuilder.getMessage(strStrength).equals("Regular"))
                        selectValueFromDropDownByby(strStrength, STRENGTH_THREEPACK_DROPDOWN);
                    if (UrlBuilder.getMessage(strStrength).equals("Strong"))
                        selectValueFromDropDownByby(strStrength, STRENGTH_THREEPACK_DROPDOWN);
                    waitForExpectedElement(QTY_THREEPACK_DROPDOWN).click();
                    selectValueFromDropDownByby(strQuantity, QTY_THREEPACK_DROPDOWN);
                    clickFirstElementByQueryJSExecutor(ADDTOCART_THREEPACK_BUTTON);
                    waitForAjaxElementNotToBePresent(getWebDriver(),8);
                }
                break;
            case "lyftdk":
                scrollElementIntoView(STRENGTH_COLLECTION_THREEPACK_DROPDOWN);
                waitForExpectedElement(STRENGTH_COLLECTION_THREEPACK_DROPDOWN).click();
                if (UrlBuilder.getMessage(strStrength).equals("Regular"))
                    waitForExpectedElement(STRENGTH_COLLECTION_THREEPACK_DROPDOWN).sendKeys("Re");
                if (UrlBuilder.getMessage(strStrength).equals("Strong"))
                    waitForExpectedElement(STRENGTH_COLLECTION_THREEPACK_DROPDOWN).sendKeys("St");
                waitForExpectedElement(STRENGTH_COLLECTION_THREEPACK_DROPDOWN).click();
                selectValueFromDropDownByby(strQuantity, QTY_COLLECTION_THREEPACK_DROPDOWN);
                clickFirstElementByQueryJSExecutor(ADDTOCART_COLLECTION_THREEPACK_BUTTON);
                break;
            default:
        }
        Thread.sleep(1000);
    }

    public void assertSameProductDetailsForLABandNonLabProductsInMiniCart() {
        waitForExpectedElement(MINICART_PRODUCTS_LIST, 10);
        waitForExpectedElement(MINICART_PRODUCTS_PRICES, 10);
        productItems = waitForExpectedElement(MINICART_PRODUCTS_LIST).findElements(By.tagName("li"));
        assertTrue(getWebDriver().findElements(MINICART_PRODUCTS_NAMES).size() == 2);
        assertTrue(getWebDriver().findElements(MINICART_PRODUCTS_PRICES).size() == 2);
        assertTrue(getWebDriver().findElements(MINICART_PRODUCTS_QTYS).size() == 2);
    }

    public void assertStrengthAttributeForOnlyLABProductsInMiniCart(String strExpectedStrength) {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                assertTrue(waitForExpectedElement(MINICART_SEEDETAILS_LINK).isDisplayed());
                clickFirstElementByQueryJSExecutor(MINICART_SEEDETAILS_LINK);
                break;
            case "lyftdk":
                if (getWebDriver().findElements(MINICART_SEEDETAILS_LINK).size() > 0) {
                    clickFirstElementByQueryJSExecutor(MINICART_SEEDETAILS_LINK);
                    assertTrue(waitForExpectedElement(MINICART_LABPRODUCT_STRENGTH, 10).getText().contains(UrlBuilder.getMessage(strExpectedStrength)));
                } else
                    assertTrue(waitForExpectedElement(MINICART_LABPRODUCT_STRENGTH_LYFTDK, 10).getText().contains(UrlBuilder.getMessage(strExpectedStrength)));
                break;
            default:
        }
    }

    public void assertDifferentLABProductsWithSameFlavourSameStrengthInMiniCart() {
        waitForExpectedElement(PRODUCTS_CHECKOUT_LIST, 10);
        productItems = waitForExpectedElement(PRODUCTS_CHECKOUT_LIST).findElements(By.tagName("li"));
        assertTrue(productItems.size() == 2);
    }

    public void assertSameBundleLABProductsWithSpecifiedQuantityInMiniCart(String strQuantity) {
        WebElement ItemsQtyMiniCart = waitForExpectedElement(MINICART_PRODUCTS_QTY, 10);
        waitForExpectedElement(PRODUCTS_CHECKOUT_LIST, 10);
        productItems = waitForExpectedElement(PRODUCTS_CHECKOUT_LIST).findElements(By.tagName("li"));
        assertTrue(productItems.size() == 1);
        assertTrue(ItemsQtyMiniCart.getAttribute("data-item-qty").contains(strQuantity));
    }

    public void clickOnLoginOrRegisterToBuyButtonFromPLP() {
        clickFirstElementByQueryJSExecutor(LOGIN_REGISTER_TOBUY_BUTTON);
    }

    public void userEntersQuantityInTextFieldAndClicksUpdate(By txtField, String strQuantity, By btnUpdate) throws Throwable {
        if(UrlBuilder.getLocale().equals("kz")){
            int i= Integer.parseInt(strQuantity);
            if(i<=15){
            clickByElementByQueryJSExecutor(BasketPage.QTY_DROPDOWN_SELECT_BASKET_PAGE);
            clickByElementByQueryJSExecutor(BasketPage.QTY_DROPDOWN_MORE_VALUE_BASKET_PGE);}

            waitForExpectedElement(BasketPage.INPUT_QTY_MINI_BASKET,10).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            waitForExpectedElement(BasketPage.INPUT_QTY_MINI_BASKET).sendKeys(strQuantity);
            waitForExpectedElement(BasketPage.INPUT_QTY_MINI_BASKET).sendKeys(Keys.ENTER);
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        }
        else {
            waitForExpectedElement(txtField).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            waitClearAndEnterText(txtField, strQuantity);
            clickFirstElementByQueryJSExecutor(btnUpdate);
        }
            sleep(1000);
    }

    public void assertInternalRedirectOfPLPlinksWithStatusCode() throws Throwable {
        homePage.assertURLsInternalRedirectWithStatusCode(PLP_REDIRECT_LINKS);
    }

    public void addProductToBasket() throws InterruptedException {
        switch (UrlBuilder.getLocale()) {
            case "veloza":
            case "velobe":
                selectFirstAvailableProduct();
                break;
            case "velopl":
                selectFirstAvailableProductPl();
                break;
            case "uk":
                if (pdp.getWebDriver().findElements(pdp.SELECTED_CBD_STRENGTH_PDP).size() == 0)
                    pdp.selectStrength(pdp.VYPEIE_STRENGTH);
                if (waitForExpectedElement(pdp.VYPEUK_CHOOSE_STRENGTH, 10).getText().contains("Choose strength"))
                    pdp.selectNthStrength(pdp.VYPEIE_STRENGTH, 1);
                break;
            case "ie":
                pdp.selectStrength(pdp.VYPEIE_STRENGTH);
                break;
            case "co":
            case "nl":
            case "vuseza":
            case "vusemx":
                pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                break;
            case "vusefr":
                if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIPhone()) {
                    pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                } else {
                    pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                    pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                }
                break;
            case "fr":
                if (isElementPresent(FIRST_COLOUR_SWTACH_FR, 2)) {
                    waitForExpectedElement(FIRST_COLOUR_SWTACH_FR).click();
                }
                if (isElementPresent(FIRST_STRENTH_SWTACH_FR, 2)) {
                    waitForExpectedElement(FIRST_STRENTH_SWTACH_FR).click();
                }
                break;
            case "vuseuk":
                if (UrlBuilder.isIpad() || UrlBuilder.isSamsungMobile()) {
                    pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                    pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                }
                else if(UrlBuilder.isIPhone())
                {
                    pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                }
                break;
            case "vypeit":
            case "vuseit":
                if (UrlBuilder.isIpad()) {
                    waitForAjaxElementNotToBePresent(getWebDriver(), 15);
                    waitForExpectedElement(PRODUCTS_VUSEDE, 8);
                    List<WebElement> products = webDriver.findElements(PRODUCTS_VUSEDE);
                    WebElement product = products.get(0);
                    List<WebElement> strengths = webDriver.findElements(STRENGTHS_VUSEDE);
                    waitForExpectedElement(STRENGTHS_VUSEDE, 10).sendKeys(Keys.PAGE_DOWN);
                    strengths.get(0).click();
//                    hoverOnElement(product);
//                    product.findElement(BUTTON_VUSEDE).click();
                }
                if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIPhone()) {
                    pdp.selectProductColorStrengthFromList(M_PRODUCT_STRENGTH_IT);
                } else {
                    pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                    pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                }
                break;

        }
        switch (UrlBuilder.getLocale()) {
            case "de":
                if (UrlBuilder.isFirefox()) {
                    checkPageUrlContains(UrlBuilder.getMessage("plp_url.key"));
                }
                scrollNthProductToCenter(0);
                waitForExpectedElement(ADD_TO_CART_BUTTON_DE, 10);
                clickByJavaScriptExecutor(webDriver.findElement(ADD_TO_CART_BUTTON_DE));
                waitForElementToDisappear(ADD_TO_CART_BUTTON_DE, 10);
                scrollDownByCoordinator(300);
                pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                clickByJavaScriptExecutor(webDriver.findElements(pdp.addToCartButton).get(0));
                if (UrlBuilder.isFirefox()) {
                    scrollUpByCoordinator(100);
                    waitForPage();
                }
                break;
            case "vusede":
                waitForAjaxElementNotToBePresent(getWebDriver(), 15);
                waitForExpectedElement(PRODUCTS_VUSEDE, 8);
                boolean found = false;
                List<WebElement> products = webDriver.findElements(PRODUCTS_VUSEDE);
                for (WebElement product: products) {
                    List<WebElement> strengths = product.findElements(STRENGTHS_VUSEDE);
                    List<WebElement> colors = product.findElements(COLORS_VUSEDE);
                    for (WebElement strength : strengths) {
                        if (UrlBuilder.isMobile()) {
                            scrollDownByCoordinator(500);
                        }
                        if (strength.getAttribute("option-empty") == null) {
                            clickUsingJS(strength);
                            found = true;
                            break;
                        }
                    }
                    for (WebElement color : colors) {
                        if (UrlBuilder.isMobile()) {
                            scrollDownByCoordinator(500);
                        }
                        if (color.getAttribute("data-option-type") != null) {
                            clickUsingJS(color);
                            found = true;
                            break;
                        }
                    }
                    hoverOnElement(product);
                    product.findElement(BUTTON_VUSEDE).click();
                    bypassHealthWarningPopup();
                    if (found) {
                        break;
                    }
                }
                break;
            case "fr":
            case "vusemx":
                waitForExpectedElement(VYPE_FIRST_ADDTOCART_BTN_DK, 20);
                //scrollUpByCoordinator(50); // try to fix ado failure
                scrollToPageTop();
                if(!isElementPresent(VUSE_DE_FIRST_PRODUCT))
                {
                    scrollToPageTop();
                }
                scrollToElement(VUSE_DE_FIRST_PRODUCT);
                hoverOnElement(VUSE_DE_FIRST_PRODUCT);
                try {
                    waitForExpectedElement(VYPE_FIRST_ADDTOCART_BTN_DK, 10).click();
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(VYPE_FIRST_ADDTOCART_BTN_DK);
                }
                break;
            case "vusefr":
                scrollToElement(VUSE_DE_FIRST_PRODUCT);
                hoverOnElement(VUSE_DE_FIRST_PRODUCT);
                try {
                    waitForExpectedElement(VYPE_FIRST_ADDTOCART_BTN_DK, 10).click();
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(VYPE_FIRST_ADDTOCART_BTN_DK);
                }
                break;
            case "ie":
            case "uk":
            case "mx":
            case "vypeit":
            case "vuseit":
                if (UrlBuilder.isFirefox()) waitForExpectedElement(pdp.addToCartButton, 10);
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
//               if(UrlBuilder.getLocale().contains("vuseuk")){ //currently all items on PLP page have a default strength chosen, reenable if this changes
//                   selectFirstProductColorStrengthOnPLP();
//               }
                waitForExpectedElement(pdp.addToCartButton, 10);
                clickByJavaScriptExecutor(webDriver.findElements(pdp.addToCartButton).get(0));
                if (UrlBuilder.isFirefox()) sleep(3000);
                scrollToPageTop();//added for FF stability
                break;
            case "vuseuk":
                if (UrlBuilder.isFirefox()) waitForExpectedElement(pdp.addToCartButton, 10);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                if(UrlBuilder.getLocale().contains("vuseuk")){
                    selectFirstProductColorStrengthOnPLP();
                }
                if(isElementPresent(STRENGTH_SELECTED,5))
                {
                    List<WebElement> productStrength = waitForExpectedElements(STRENGTH_SELECTED);
                    hoverOnElement(productStrength.get(0));
                }
                if(UrlBuilder.isMobile()) {
                    if (waitForExpectedElement(M_PLP_FIRST_ITEM_COLOR_VUSEUK).getAttribute("aria-checked").equals("false"))
                    {
                        waitForExpectedElement(M_PLP_FIRST_ITEM_COLOR_VUSEUK).click();
                    }
                    scrollElementIntoView(M_PLP_ADDTOCART_FORM_VUSEUK);
                    clickByElementByQueryJSExecutor(M_PLP_ADDTOCART_BUTTON_VUSEUK);
                    if (!waitForExpectedElement(M_CLOSE_MINI_CART_DIALOG_VUSEUK).getAttribute("style").equals("display: none")) {
                        clickByElementByQueryJSExecutor(M_CLOSE_MINI_CART_PANEL_VUSEUK);
                    }
                }
                else {
                    waitForExpectedElement(PLP_ADDTOCART_BUTTON_VUSEUK,3);
                    clickByJavaScriptExecutor(webDriver.findElements(PLP_ADDTOCART_BUTTON_VUSEUK).get(0));
                }
                if (UrlBuilder.isFirefox()) sleep(3000);
                scrollToPageTop();//added for FF stability
                break;
            case "vuseco":
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                selectFirstProductColorStrengthOnPLP();
                clickUsingJS(ADD_TO_BASKET_BUTTON_PLP_CO);
                break;
            case "nl":
                waitForExpectedElement(ADD_TO_CART_IT_AND_NL).click();
                break;
            case "vuseza":
                clickUsingJS(ADD_TO_BASKET_BUTTON_PLP);
                break;
            case "vusedk":
                if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIpad() || UrlBuilder.isIPhone()) {
                    clickFirstElementByQueryJSExecutor(homePage.PLP_ADDTOCART_BUTTON);
                } else if (UrlBuilder.isDesktop()) {
                    clickFirstElementByQueryJSExecutor(VYPE_FIRST_ADDTOCART_BTN_DK);
                }
                break;
            case "lyftse":
                waitForExpectedElement(FIRST_PRODUCT_IMAGE_LYFTSE, 10);
                if (webDriver.findElements(FIRST_PRODUCT_IMAGE_LYFTSE).size() > 0) {
                    waitForExpectedElement(FIRST_PRODUCT_IMAGE_LYFTSE, 10);
                }
                scrollToElement(FIRST_ADDTOCART_BTN_LYFTSE);// for BS stability
                clickUsingJS(FIRST_ADDTOCART_BTN_LYFTSE);
                break;
            case "dk":
                if (UrlBuilder.isDesktop()) {
                    clickFirstElementByQueryJSExecutor(VYPE_FIRST_ADDTOCART_BTN_DK);
                    if (UrlBuilder.isFirefox()) sleep(6000);
                } else {
                    waitForExpectedElement(ADD_TO_CART_BUTTON);
                    clickUsingJS(ADD_TO_CART_BUTTON);
                }
                break;
            case "veloza":
            case "velopl":
            case "velobe":
                break;
            default:
                if (UrlBuilder.isDesktop()) {
                    waitForExpectedElement(pdp.addToCartButton, 5);
                    clickByElementByQueryJSExecutor(pdp.addToCartButton);
                } else {
                    try {
                        waitForExpectedElement(ADD_TO_CART_BUTTON, 10);
                        clickUsingJS(ADD_TO_CART_BUTTON);
                    } catch (Exception ex) {
                        waitForExpectedElement(homePage.PLP_ADDTOCART_BUTTON, 10);
                        clickUsingJS(homePage.PLP_ADDTOCART_BUTTON);
                    }
                }
        }
    }

    public void eyesCheckPlpPage() {
        if (Props.EYES_ON & EyesCheckpoints.PLP.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.PLP.getName();
            WebElement productItems;
            WebElement toolbarCount;
            switch (UrlBuilder.getLocale()) {
                case "kz":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(PRODUCT_ITEMS_REGION,
                                        HomePage.HEADER_TOP));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(PRODUCT_ITEMS_REGION));
                    }
                    break;
                case "velopl":
                case "de":
                    eyes.check(checkpointName, Target.window().fully());
                    break;
                case "fr":
                case "uk":
                case "vuseuk":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(PRODUCT_ITEMS_REGION));
                    }
                    break;
                case "pl":
                case "glode":
                case "vusede":
                case "vuseco":
                case "vuseza":
                case "vuseit":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        scrollToShowEntirePage(); // invoke the second time to solve footer truncate issue
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(PRODUCT_ITEMS_REGION,
                                        HomePage.MESSAGE_ROW));
                    }
                    break;
                case "vusefr":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("plpUrl.key"));
                        scrollToShowEntirePage();
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        scrollToShowEntirePage(); // invoke the second time to solve footer truncate issue
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(PRODUCT_ITEMS_REGION,
                                        HomePage.MESSAGE_ROW));
                    }
                    break;
                case "epok":
                case "glojp":
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(PRODUCT_ITEMS_REGION));
                    break;
                default:
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(waitForExpectedElement(PRODUCT_ITEMS_REGION))
                            .ignoreDisplacements());
            }
        }
    }

    public void navigateToTofinoProductsLandingPage(String strExpectedURL) {
        getWebDriver().navigate().to(getWebDriver().getCurrentUrl() + UrlBuilder.getMessage(strExpectedURL));
    }

    public void assertTofinoProductsSectionAtBottomOfLandingPage() {
        scrollToPageBottom();
        assertTrue(getWebDriver().findElements(pdp.FIND_OUT_MORE_LINK).size() > 0);
        assertTrue(getWebDriver().findElements(CBD_STRENGTH_LABEL_PLP).size() > 0);
    }

    public void assertPageTitleContains(String actualPageTitle) {
        String expectedPageHeading = UrlBuilder.getMessage(actualPageTitle);
        assertTrue(getCurrentPageTitle().contains(expectedPageHeading));
    }

    public void selectProductColorAndAssertDeviceColorSwitchOnPLP() {
        waitForExpectedElement(PRODUCT_COLOR_SWATCH_SELECTED, 10);
        if (!(getWebDriver().findElements(PRODUCT_COLOR_SWATCH_SELECTED).size() > 0)) {
            try {
                clickFirstElementByQueryJSExecutor(PRODUCT_COLOR_SWATCH);
            } catch (Exception e){
                if (UrlBuilder.getLocale().equalsIgnoreCase("vuseit")) {
                    clickFirstElementByQueryJSExecutor(PRODUCT_COLOR_SWATCH_VUSEIT);
                }
            }
        }
        String strSelectedLabel = waitForExpectedElement(SELECTED_PRODUCT_COLOR).getAttribute("data-option-label");
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        String strImageColor = getWebDriver().findElements(SELECTED_PRODUCT_IMAGE).get(0).getAttribute("src");
        assertEquals(strSelectedLabel, waitForExpectedElement(SELECTED_PRODUCT_LABEL).getText());
    }

    public void assertTofinoStrengthOnPage(String page) {
        if (UrlBuilder.getMessage(page).equalsIgnoreCase("order summary page"))
            assertTofinoStrength(TOFINO_STRENGTH_VALUE_ORDER_SUMMARY);
        else if (UrlBuilder.getMessage(page).equalsIgnoreCase("payment page")) {
            waitForExpectedElement(plusIcon, 5).click();
            assertTofinoStrength(TOFINO_STRENGTH_VALUE_PAYMENTPAGE);
        } else
            assertTofinoStrength(TOFINO_STRENGTH_VALUE_MINICART);
    }

    public void assertTofinoStrength(By strength) {
        String s = waitForExpectedElement(strength, 10).getText();
        assertTrue(s.contains("mg") && Pattern.compile("mg$").matcher(s).find());
    }

    public void assertListOfConsumableTypesInFiltersAndCTA(String strType) throws Throwable {
        homePage.closeVuseAlertIfPresent();
        List<WebElement> lstLinks = getWebDriver().findElements(CONSUMABLE_TYPE_FILTERS_LIST);
        for (int i = 1; i < lstLinks.size(); i++) {
            if (strType.equalsIgnoreCase("Devices"))
                assertTrue(lstLinks.get(i).getText().contains("epen") || lstLinks.get(i).getText().contains("epod") || lstLinks.get(i).getText().contains("eTank") || lstLinks.get(i).getText().contains("Starter"));
            else if (strType.equalsIgnoreCase("Flavours"))
                assertTrue(lstLinks.get(i).getText().contains("ePen") || lstLinks.get(i).getText().contains("ePod") || lstLinks.get(i).getText().contains("eLiquid"));
            orderSuccessPage.verifyURLStatusCodeIsValid(lstLinks.get(i).getAttribute("href"));
        }
        String strCategoryURL = lstLinks.get(1).getAttribute("href");
        lstLinks.get(1).click();
        waitForElementToAppearAndDisappear(LOADER, 2, 12);
        assertTrueWithCustomError(strCategoryURL, getWebDriver().getCurrentUrl());
    }

    public void assertQtySelectorDefaultValueForEachSKU() {
        List<WebElement> lstQtyFields = null;
        if ("vusefr".equals(UrlBuilder.getLocale())) {
            lstQtyFields = getWebDriver().findElements(QTY_SELECTOR_PLP_VUSEFR);
        }else if ("vuseuk".equals(UrlBuilder.getLocale())) {
            lstQtyFields = getWebDriver().findElements(QTY_SELECTOR_PLP_UK);
        }else {
            lstQtyFields = getWebDriver().findElements(QTY_SELECTOR_PLP);
        }
        for (int i = 1; i < lstQtyFields.size(); i++) {
            assertTrueWithCustomError("1", lstQtyFields.get(i).getAttribute("value"));
        }
    }

    public void assertUserIsAbleToInputAnyValueInQtyTextField(String strQty) throws Throwable {
        Integer elementIndex=Integer.parseInt(UrlBuilder.getMessage("productIndex.key"));
        String itemListSelector="#product-list-plp > li:nth-child("+elementIndex+")";
        if (getWebDriver().findElements(PRODUCT_COLOR_SWATCH).size() > 0) {
            hoverOnElement(PRODUCT_COLOR_SWATCH);
        } else if (getWebDriver().findElements(PRODUCT_STRENGTH_SWATCH_VUSEUK).size() > 0) {
            By elementStrengthBy=By.cssSelector(itemListSelector+"> div > div> div > div > div > div > div > div>div");
            if(!waitForExpectedElement(elementStrengthBy).getAttribute("class").contains("selected"))
                waitForExpectedElement(elementStrengthBy).click();
            hoverOnElement(elementStrengthBy);
            if (webDriver.findElements(STRENGTH_SELECTED_VUSEUK).size() == 0) {
                clickByElementByQueryJSExecutor(PRODUCT_STRENGTH_SWATCH_VUSEUK);
            }
        }
        By qtyInputBy=By.cssSelector(itemListSelector+"> div > div> div > div > div > form > div > div > div>input.qty");
        By addToBasketButton=By.cssSelector(itemListSelector+"> div > div > div > div > div > form > div > button");
        WebElement eleQty = getWebDriver().findElement(qtyInputBy);
        enterDataUsingJS(eleQty, strQty);
        clickUsingJS(getWebDriver().findElement(addToBasketButton));
    }

    public void clickOnPlusButtonOnQtySelectorToIncreaseTheQty(String strIncreaseQty) throws Throwable {
        Integer elementIndex;
        By elementStrengthSelector=null;
        By elementPlusButton=null;
        By addToBasketButton=null;
        By qtyInputBy=null;
        if(waitForExpectedElement(M_CLOSE_MINI_CART_PANEL_VUSEUK).isDisplayed())
            waitForExpectedElement(M_CLOSE_MINI_CART_PANEL_VUSEUK).click();
        switch (UrlBuilder.getLocale()){
            case "vuseuk":
                elementIndex=Integer.parseInt(UrlBuilder.getMessage("productIndex.key"));
                String itemListSelector="#product-list-plp > li:nth-child("+elementIndex+")";
                elementStrengthSelector=By.cssSelector(itemListSelector+"> div > div> div > div > div > div > div > div>div");
                qtyInputBy=By.cssSelector(itemListSelector+"> div > div> div > div > div > form > div > div > div>input.qty");
                elementPlusButton=By.cssSelector(itemListSelector+"> div > div> div > div > div > form > div > div > div > input.plus");
                addToBasketButton=By.cssSelector(itemListSelector+"> div > div > div > div > div > form > div > button");
                break;
            default:
                elementStrengthSelector=PRODUCT_STRENGTH_SWATCH_VUSEUK;
                elementPlusButton=QTY_SELECTOR_PLUS_BUTTON;
                qtyInputBy=By.cssSelector("#product-list-plp > li:nth-child(1) > div > div> div > div > div > form > div > div > div>input");
                addToBasketButton=By.cssSelector("#product-list-plp > li:nth-child(1) > div > div > div > div > div > form > div > button");

        }
        hoverOnElement(elementStrengthSelector);
        clickUsingJS(elementPlusButton);
        assertTrueWithCustomError(strIncreaseQty, getWebDriver().findElement(qtyInputBy).getAttribute("value"));
        clickUsingJS(getWebDriver().findElement(addToBasketButton));
        waitForPage(5);
    }

    public void selectFirstProductColorStrengthOnPLP() {
        homePage.closeVuseAlertIfPresent();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        if (UrlBuilder.isMobile())
        {
            switch (UrlBuilder.getLocale()) {
                case "vuseuk":
                    if (getWebDriver().findElements(M_PRODUCT_COLOR_SWATCH_VUSEUK).size() > 0) {
                        jsScrollElementInCenter(waitForExpectedElement(M_PRODUCT_COLOR_SWATCH_VUSEUK));
                        clickUsingJS(M_PRODUCT_COLOR_SWATCH_VUSEUK);
                    } else if (getWebDriver().findElements(PRODUCT_STRENGTH_SWATCH).size() > 0) {
                        jsScrollElementInCenter(waitForExpectedElement(PRODUCT_STRENGTH_SWATCH));
                        clickByElementByQueryJSExecutor(PRODUCT_STRENGTH_SWATCH);
                        clickUsingJS(PRODUCT_STRENGTH_SWATCH);
                    }
                    break;
                default:
                    if (getWebDriver().findElements(PRODUCT_COLOR_SWATCH).size() > 0) {
                        jsScrollElementInCenter(waitForExpectedElement(PRODUCT_COLOR_SWATCH));
                        clickUsingJS(PRODUCT_COLOR_SWATCH);
                    } else if (getWebDriver().findElements(PRODUCT_STRENGTH_SWATCH).size() > 0) {
                        jsScrollElementInCenter(waitForExpectedElement(PRODUCT_STRENGTH_SWATCH));
                        clickByElementByQueryJSExecutor(PRODUCT_STRENGTH_SWATCH);
//                        clickUsingJS(PRODUCT_STRENGTH_SWATCH);
                    }
            }
        }
        else {
            if (getWebDriver().findElements(PRODUCT_COLOR_SWATCH).size() > 1) {
                jsScrollElementInCenter(waitForExpectedElement(PRODUCT_COLOR_SWATCH));
                clickUsingJS(PRODUCT_COLOR_SWATCH);
            } else if (getWebDriver().findElements(PRODUCT_STRENGTH_SWATCH).size() > 1) {
                jsScrollElementInCenter(waitForExpectedElement(PRODUCT_STRENGTH_SWATCH));
                if(!("vuseuk".equalsIgnoreCase(UrlBuilder.getLocale())
                        && waitForExpectedElement(PRODUCT_STRENGTH_SWATCH).getAttribute("class").contains("selected")))
                    clickByElementByQueryJSExecutor(PRODUCT_STRENGTH_SWATCH);
                //clickUsingJS(PRODUCT_STRENGTH_SWATCH);
            }else if ("vusefr".equalsIgnoreCase(UrlBuilder.getLocale())) {
                if (getWebDriver().findElements(PRODUCT_STRENGTH_SWATCH).size() == 1) {
                    clickByElementByQueryJSExecutor(PRODUCT_STRENGTH_SWATCH);
                } else if (getWebDriver().findElements(PRODUCT_COLOR_SWATCH).size() == 1) {
                    clickByElementByQueryJSExecutor(PRODUCT_COLOR_SWATCH);
                }
            }
            else
            {
                try{
                    LOG.info("The first product is invalid, choose product by index.");
                    By itemStrength=By.cssSelector("ol#product-list-plp li:nth-child("
                            +UrlBuilder.getMessage("productIndex.key")+") div.product-item-masthead div.swatch-option.text");
                    WebElement itemElement=waitForExpectedElement(itemStrength);
                    jsScrollElementInCenter(itemElement);
                    hoverOnElement(itemElement);
                }catch(Exception e){
                    LOG.info("There is no color or strength can be selected");
                }
            }
        }
    }

    public void updateQuantityAndClickAddToBasket(int qty) {
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
                IntStream.range(1, qty).mapToObj(i -> QTY_SELECTOR_PLUS_BUTTON_VUSEFR).forEach(this::clickUsingJS);
                break;
            default:
                IntStream.range(1, qty).mapToObj(i -> QTY_SELECTOR_PLUS_BUTTON).forEach(this::clickUsingJS);
        }
        clickUsingJS(getWebDriver().findElements(ADD_TO_BASKET_BUTTON_PLP).get(0));
    }

    public void clickOnDeviceMenu() {
        switch (UrlBuilder.getLocale()) {
            case "pl":
                waitForExpectedElement(CLICK_ON_DEVICE_MENU, 20);
                clickByElementByQueryJSExecutor(CLICK_ON_DEVICE_MENU);
                break;
            case "it":
                if(UrlBuilder.isMobile())
                {
                    homePage.clickMobileHamburgerMenu();
                    waitForExpectedElement(M_CLICK_ON_DEVICE_MENU_GLOIT, 20);
                    clickUsingJS(M_CLICK_ON_DEVICE_MENU_GLOIT);
                }
                else {
                    clickUsingJS(CLICK_ON_DEVICE_MENU_GLOIT);
                }
                break;
            case "glode":
            case "kz":
                clickUsingJS(DEVICE_MENU_GLOKZ);
                break;
            default:
        }
    }

    public void productNameDisplayed() {
        if (UrlBuilder.getLocale().equals("it")) {
            scrollElementIntoView(PLP_PRODUCT_NAME_GLOIT);
            assertTrue(waitForExpectedElement(PLP_PRODUCT_NAME_GLOIT).isDisplayed());
        } else {
            scrollElementIntoView(PLP_PRODUCT_NAME);
            assertTrue(waitForExpectedElement(PLP_PRODUCT_NAME).isDisplayed());
        }
    }

    public void productPriceDisplayed() {
        if (UrlBuilder.getLocale().equals("it")) {
            scrollElementIntoView(PLP_PRODUCT_PRICE_GLOIT);
            assertTrue(waitForExpectedElement(PLP_PRODUCT_PRICE_GLOIT).isDisplayed());
        } else {
            scrollElementIntoView(PLP_PRODUCT_PRICE);
            assertTrue(waitForExpectedElement(PLP_PRODUCT_PRICE).isDisplayed());
        }
    }

    public void productByNowDisplayed() {
        if (UrlBuilder.getLocale().equals("it")) {
            scrollElementIntoView(PLP_PRODUCT_CTA_GLOIT);
            assertTrue(waitForExpectedElement(PLP_PRODUCT_CTA_GLOIT).isDisplayed());
        } else {
            scrollElementIntoView(PLP_PRODUCT_BY_NOW);
            assertTrue(waitForExpectedElement(PLP_PRODUCT_BY_NOW).isDisplayed());
        }
    }

    public void ViewProductCTAIsDisplayedOnPLPForPersonalizedProduct() {
        waitForPage(30);
        hoverOnElement(PERSONALIZED_PRODUCT);
        assertEquals(waitForExpectedElement(CTA_PERSONALIZED_PRODUCT).getText(), (UrlBuilder.getMessage("ViewCartButtonText.key")));
    }

    public void vypePlpPageNavigation(String category, String subCategory) {
        if(UrlBuilder.isDesktop()){
            hoverOnElement(By.linkText(UrlBuilder.getMessage(category)));
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage(subCategory)));
            hoverOnElement(By.linkText(UrlBuilder.getMessage(subCategory)));
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage(subCategory))).click();
        }
        else {
            String subCategoryUpperCase = (UrlBuilder.getMessage(subCategory)).toUpperCase();
            homePage.clickMobileHamburgerMenu();
            waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage(category).toUpperCase()), 10);
            waitForItemToBeClickableAndClick(By.partialLinkText(subCategoryUpperCase),5);
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        }
    }

    public void vypeCategoryBannerPage() {
        waitForExpectedElement(VYPE_PLP_HERO);
        LOG.info("Hero Image is present in");
    }

    public boolean verifyDeviceOnResultPage(final String text) throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
            case "vuseuk":
            case "vusede":
            case "vuseco":
            case "vusemx":
            case "vuseza":
                return textToBePresentInElementLocated(GET_DEVICE, UrlBuilder.getMessage(text));
            default:
                return false;
        }
    }

    public void clickSwitchFavourateTab() {
        try {
            waitForExpectedElement(SECOND_FAVOURATE_TAB, 5).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(SECOND_FAVOURATE_TAB);
        }
    }

    public void clickViewAllDevicesBtn() {
        waitForExpectedElement(VIEW_ALL_DEVICES).click();
    }

    public boolean verifyViewAllDevicesDisplay() {
        return isElementPresentByby(VIEW_ALL_RESULT);
    }

    public void clickViewAllFlavoursBtn() {
        waitForExpectedElement(VIEW_ALL_FLAVOURS).click();
    }

    public void clickStartAgainBtn() {
        waitForExpectedElement(START_AGAIN_BTN).click();
    }

    public boolean verifyFlavourGroup(String flavour) {
        return waitForExpectedElement(FIRST_FAVOURATE_TAB).getText().contains(flavour)
                || waitForExpectedElement(SECOND_FAVOURATE_TAB).getText().contains(flavour);
    }

    public boolean verifyNotExistFlavourGroup() {
        return invisibilityOfElementLocated(FIRST_FAVOURATE_TAB, 3);
    }

    public void clickOnAddToCartButton() {
        waitForExpectedElement(PRODUCT_ADD_TO_CART_BUTTON, 20);
        if (UrlBuilder.isFirefox()) scrollToElement(PRODUCT_ADD_TO_CART_BUTTON);
        clickByElementByQueryJSExecutor(PRODUCT_ADD_TO_CART_BUTTON);
        waitForAjaxElementNotToBePresent(getWebDriver(), 8);
    }

    public boolean verifyActiveFlavourGroup() {
        return isElementPresent(SECOND_FAVOURATE_TAB_ACIVE);
    }

    public void bypassHealthWarningPopup() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        if (getWebDriver().findElements(HEALTH_WARNING_POPUP).size() > 0)
            waitForExpectedElement(HEALTH_WARNING_POPUP_INTHECART_BUTTON, 4).click();
        else LOG.info("Health warning popup is not available for the given product");
    }

    private LinkedList<String> getCommaSeparatedStringConfigAsListForKey(String linkTextKey) {
        return Stream.of(UrlBuilder.getMessage(linkTextKey).split(","))
                .collect(toCollection(LinkedList::new));
    }

    public void verifySortingOptons(String sortingOptions, String product) {
        List<String> options;
        List<String> expectedUrlList;
        switch (UrlBuilder.getMessage(product)) {
            case "ECIGARETTES":
            case "SHOP DEVICES":
            case "ePen":
            case "ePEN":
            case "ePOD":
            case "eTANK":
            case "ePod":
            case "ePOD PODS":
            case "ePEN PODS":
            case "eLIQUID BOTTLES":
            case "eTank":
            case "ACCESSOIRES":
                expectedUrlList = getCommaSeparatedStringConfigAsListForKey(sortingOptions);
                LOG.info("The expected list is " + expectedUrlList);
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                options = getAllSelectOptions(SORTING_OPTION);
                LOG.info("The actual list is " + options);
                Assert.assertEquals(expectedUrlList.size(), options.size());
                softAssertions.assertThat(options)
                        .hasSameElementsAs(expectedUrlList);
                softAssertions.assertAll();
                options.clear();
                expectedUrlList.clear();
                break;
            case "ELIQUIDES":
            case "SHOP FLAVOURS":
            case "Capsules ePod":
            case "Capsules ePen":
            case "Flacons e-liquides":
                expectedUrlList = getCommaSeparatedStringConfigAsListForKey(sortingOptions);
                LOG.info("The expected list is " + expectedUrlList);
                if (UrlBuilder.getMessage(product).equals("ELIQUIDES") || UrlBuilder.getMessage(product).equals("SHOP FLAVOURS"))
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(product).toUpperCase()));
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                options = getAllSelectOptions(SORTING_OPTION);
                LOG.info("The actual list is " + options);
                Assert.assertEquals(expectedUrlList.size(), options.size());
                assertTrue(options.containsAll(expectedUrlList));
                options.clear();
                expectedUrlList.clear();
                break;
        }
    }

    public boolean verifyCountOfSelectedCheckbox(By checkbox, By checkboxCount, By resultCount) {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        int actual = Integer.parseInt(getWebDriver().findElements(checkboxCount).get(0).getText().trim().replaceAll("\\(|\\)", ""));
        clickFirstElementByQueryJSExecutor(checkbox);
        clickByElementByQueryJSExecutor(FILTER_APPLY_BUTTON);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        int expected = Integer.parseInt(waitForExpectedElement(resultCount).getText().trim());
        return actual == expected;
    }

    public boolean optionHiglighted(String option) {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        Assert.assertEquals(getWebDriver().findElements(SUBCATEGORY_LINK_ACTIVE).size(), 1);
        String activeOption = getWebDriver().findElements(SUBCATEGORY_LINK_ACTIVE).get(0).getText().trim();
        return activeOption.equalsIgnoreCase(option);
    }

    public boolean verifyRedirectedUrl(String urlContains) {
        clickByElementByQueryJSExecutor(SUBCATEGORY_LINK_ACTIVE);
        return getWebDriver().getCurrentUrl().contains(urlContains);
    }

    public void verifyTheContent(String option, String filterType) {
        List<String> filterOptionsFromSite;
        List<String> expectedFilterList;
        waitForExpectedElement(FILTER_OPTION, 5);
        clickFirstElementByQueryJSExecutor(FILTER_OPTION);
        switch (option) {
            case "eCiggarette":
                switch (filterType) {
                    case "Colour":
                        expectedFilterList = getCommaSeparatedStringConfigAsListForKey("cigaratteColorFilterOption.key");
                        filterOptionsFromSite = getStringlist(CIGARETTE_COLOR_TYPE_FILTER_OPTIONS);
                        assertTrue(filterOptionsFromSite.containsAll(expectedFilterList));
                        break;
                    case "Product Type":
                        expectedFilterList = getCommaSeparatedStringConfigAsListForKey(UrlBuilder.getMessage("cigaretteProductTypeFilterOption.key"));
                        filterOptionsFromSite = getStringlist(CIGARETTE_PRODUCT_TYPE_FILTER_OPTIONS);
                        assertTrue(filterOptionsFromSite.containsAll(expectedFilterList));
                        break;
                    case "Limited Editions":
                        expectedFilterList = getCommaSeparatedStringConfigAsListForKey(UrlBuilder.getMessage("cigaretteLimitedEditionOption.key"));
                        filterOptionsFromSite = getStringlist(CIGARETTE_LIMITED_EDITION_FILTER_OPTIONS);
                        assertTrue(filterOptionsFromSite.containsAll(expectedFilterList));
                        break;
                    case "Devices":
                        expectedFilterList = getCommaSeparatedStringConfigAsListForKey(UrlBuilder.getMessage("cigaretteDevicesOption.key"));
                        filterOptionsFromSite = getStringlist(CIGARETTE_DEVICES_FILTER_OPTIONS);
                        assertTrue(filterOptionsFromSite.containsAll(expectedFilterList));
                        break;
                }
                break;
        }
    }

    public List<String> getStringlist(By by) {
        List<WebElement> filterTypes = getWebDriver().findElements(by);
        List<String> list = new ArrayList<>();
        for (WebElement webElement : filterTypes) {
            waitForAjaxElementNotToBePresent(getWebDriver(), 3);
            String name = webElement.getText().replaceAll("[0-9|(|)]", "").trim();
            list.add(name);
            LOG.info("the new list is name " + list);
        }
        return list;
    }

    public void selectStrengthAndAddQty(String quantity) {
        waitForExpectedElement(PRODUCTS_VUSEDE,8);
        List<WebElement> products = webDriver.findElements(PRODUCTS_VUSEDE);
        WebElement product = products.get(0);
        List<WebElement> strengths = product.findElements(STRENGTHS_VUSEDE);
                    if(UrlBuilder.isMobile()) scrollDownByCoordinator(500);
        waitForExpectedElement(BUTTON_VUSEDE,10);
        waitForItemToBeClickableAndClick(BUTTON_PLP_SUBSCRIBE_OPTION);
        hoverOnElement(product);
        try{
        product.findElement(INPUT_ITEM_QUANTITY_PLP).clear();}
        catch (ElementNotInteractableException notInteractableException){
            hoverOnElement(products.get(1));
            hoverOnElement(product);
            clickUsingJS(strengths.get(0));
            product.findElement(INPUT_ITEM_QUANTITY_PLP).clear();}
        waitForAjaxElementNotToBePresent(getWebDriver(), 8);
        hoverOnElement(product);
        product.findElement(INPUT_ITEM_QUANTITY_PLP).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        product.findElement(INPUT_ITEM_QUANTITY_PLP).sendKeys(quantity);
        waitForAjaxElementNotToBePresent(getWebDriver(), 8);
        product.findElement(PRODUCT_ADD_TO_CART).click();
        pdp.waitForItemToBeClickableAndClick(pdp.MINICART_CLOSE_BUTTON,10);
    }

    public void checkDescription(String expectedDescription) {
        List<String> actualDescriptions = new ArrayList<>();
        List<WebElement> actualDescriptionElements = waitForExpectedElements(PLP_PRODUCT_NAME_GLOIT);
        actualDescriptionElements.stream().map(WebElement::getText).forEach(actualDescriptions::add);
        assertThat(actualDescriptions)
                .as("ERROR checkDescriptions: expected and actual description mismatch").contains(expectedDescription);
    }

    public void validatePlp(List<Map<String,String>> expectedPlpData) {
        String actualDescription;
        String actualPrice;
        List<Map<String,String>> actualPlpData = new ArrayList<>();
        List<WebElement> products = waitForExpectedElements(AVALANCHE_PLP_PRODUCT);
        for (WebElement product: products) {
            actualDescription = product.findElement(PLP_PRODUCT_NAME_VELO).getText();
            if (!actualDescription.toLowerCase().contains("test")) {
                actualPrice = product.findElement(PLP_PRODUCT_PRICE_VELO).getText();
                jsScrollElementInCenter(product);
                hoverOnElement(product);
                Map<String, String> productMap = new HashMap<>();
                productMap.put("productName", actualDescription);
                productMap.put("productPrice", actualPrice);
                actualPlpData.add(productMap);
            }
        }
        assertThat(actualPlpData).as("ERROR validatePlp: expected and actual product data does not match").containsExactlyInAnyOrderElementsOf(expectedPlpData);
    }

    private String buildExpectedImage(String productName, String component) {
        String width = "";
        String height = "";
        String imageName;
        String imagePrefix = UrlBuilder.getMessage("imagePrefix");
        if ("plp".equals(component)) {
            width = UrlBuilder.getMessage("plpImageWidth");
            height = UrlBuilder.getMessage("plpImageHeight");
        } else {
            assertThat(true).as("ERROR buildExpectedImages: invalid component").isFalse();
        }
            imageName = imagePrefix + productName.split("\\(")[0].replace("VELO","").replace(" ","-").toLowerCase()+"-"+width+"-"+height;
        return imageName;
    }
    public void validateImages(List<List<String>> expectedImageData, String page) {
        String expectedImageName;
        String actualImagePath = null;
        String actualImageName = null;
        String productName = null;
        String plpProductName = null;
        List<WebElement> plpProducts = waitForExpectedElements(AVALANCHE_PLP_PRODUCT);
        for (List<String> expectedImageItem: expectedImageData) {
            productName = expectedImageItem.get(0);
                expectedImageName = buildExpectedImage(productName, page);
                for (WebElement plpProduct: plpProducts) {
                    plpProductName = plpProduct.findElement(AVALANCHE_PLP_PRODUCT_NAME).getText();
                    if (plpProductName.equalsIgnoreCase(productName)) {
                        actualImageName = new File(plpProduct.findElement(By.cssSelector("img")).getAttribute("src")).getName();
                        assertThat(expectedImageName.equals(actualImageName))
                                .as("ERROR validateImages: expected image name was " + expectedImageName + " but I got " + actualImageName).isTrue();
                        break;
                    }
                }
        }
    }

    public void validatePdp(List<Map<String, String>> expectedPdpData) {
        String actualDescription;
        String actualPrice;
        String actualProductName;
        String actualSubHeading;
        String actualProductType;
        List<WebElement> actualProducts;
        List<Map<String,String>> actualPdpData = new ArrayList<>();
        for (Map<String,String> expectedData: expectedPdpData) {
            actualProducts = waitForExpectedElements(AVALANCHE_PLP_PRODUCT);
            for (WebElement actualProduct: actualProducts) {
                if (actualProduct.findElement(AVALANCHE_PLP_PRODUCT_NAME).getText().trim().equalsIgnoreCase(expectedData.get("Product Name"))) {
                    clickUsingJS(actualProduct.findElement(AVALANCHE_PLP_PRODUCT_NAME));
                    actualProductName = waitForExpectedElement(pdp.PDP_PRODUCT_NAME_CSS_FR).getText().trim();
                    actualSubHeading  = waitForExpectedElement(pdp.PDP_SUB_HEADING).getText().trim();
                    if (pdp.isConfigurable()) {
                        actualProductType = "Configurable";
                    } else {
                        actualProductType = "Simple";
                    }
                    actualPrice = waitForExpectedElement(pdp.AVALANCHE_PDP_PRICE).getText().trim();
                    actualDescription = waitForExpectedElement(pdp.AVALANCHE_PDP_PRODUCT_DESCRIPTION).getText().trim().replace("\n"," ");
                    Map<String, String> productMap = new HashMap<>();
                    productMap.put("Product Name",actualProductName);
                    productMap.put("PDP Sub Heading", actualSubHeading);
                    productMap.put("Product Type", actualProductType);
                    productMap.put("Price", actualPrice);
                    productMap.put("Description", actualDescription);
                    actualPdpData.add(productMap);
                    webDriver.navigate().back();
                    break;
                }
            }
        }
        assertThat(actualPdpData).as("ERROR validatePdp: expected and actual product data does not match").containsExactlyInAnyOrderElementsOf(expectedPdpData);
    }
    public void selectFirstAvailableProductPl(){
        boolean added = false;
        String confirmationMessage = "";
        List<WebElement> strengths;
        waitForAjaxElementNotToBePresent(webDriver,5);
        List<WebElement> products = waitForExpectedElements(HEADFUL_PLP_PRODUCT);
        for (WebElement product: products) {
            strengths = product.findElements(HEADFUL_PRODUCT_STRENGTH);
            for (WebElement strength: strengths) {
                try {
                    jsScrollElementInCenter(strength);
                    strength.click();
                    hoverOnElement(product);
                    clickUsingJS(product.findElement(HEADFUL_ADD_TO_CART_BUTTON));
                    waitForAjaxElementNotToBePresent(webDriver,3);
                    confirmationMessage = waitForExpectedElement(HEADFUL_CONFIRMATION_MESSAGE).getText();
                    if (confirmationMessage.toLowerCase().contains(UrlBuilder.getMessage("addToCartSuccessMessage-" + scenarioContext.getContext(Context.LANGUAGE)).toLowerCase())) {
                        added = true;
                        break;
                    }

                } catch (Exception e) { }
            }
            if (added) {
                break;
            }
        }
    }

    public void selectFirstAvailableProduct() {
        boolean added = false;
        String confirmationMessage = "";
        String strenthActiveStatus="";
        List<WebElement> strengths;
        waitForAjaxElementNotToBePresent(webDriver,5);
        List<WebElement> products = waitForExpectedElements(AVALANCHE_PLP_PRODUCT);
        for (WebElement product: products) {
            strengths = product.findElements(AVALANCHE_PRODUCT_STRENGTH);
            for (WebElement strength: strengths) {
                try {
                    jsScrollElementInCenter(strength);
                    strength.click();
                    strenthActiveStatus = strength.getAttribute("class");
                    assertTrue(strenthActiveStatus.contains("active"));
                    hoverOnElement(product);
                    clickUsingJS(product.findElement(AVALANCHE_ADD_TO_CART_BUTTON));
                    waitForAjaxElementNotToBePresent(webDriver,3);
                    switch (UrlBuilder.getLocale()) {
                        case "veloza":
                        case "velobe":
                            confirmationMessage = waitForExpectedElement(AVALANCHE_CONFIRMATION_MESSAGE_BE).getText();
                            break;
                        default:
                            confirmationMessage = waitForExpectedElement(AVALANCHE_CONFIRMATION_MESSAGE).getText();
                            break;
                    }
                    if (confirmationMessage.toLowerCase().contains(UrlBuilder.getMessage("addToCartSuccessMessage-" + scenarioContext.getContext(Context.LANGUAGE)).toLowerCase())) {
                        added = true;
                        break;
                    }

                } catch (Exception e) { }
            }
            if (added) {
                break;
            }
        }
    }

    public void clickOnFirstProduct() {
        waitForExpectedElements(AVALANCHE_PLP_PRODUCT).get(0).findElement(By.cssSelector("a")).click();
    }

    public int getProductTotalNumber() {
        return Integer.parseInt(webDriver.findElements(VUSE_DE_PRODUCT_TOTAL_NUMBER).get(0).getText());
    }

    public int getFirstReviewRateNumber() {
        if(UrlBuilder.getLocale().equalsIgnoreCase("vuseuk"))
            return webDriver.findElements(VUSE_UK_PRODUCT_REVIEW_FIRST_STAR_NUMBER).size();
        else return webDriver.findElements(VUSE_DE_PRODUCT_REVIEW_FIRST_STAR_NUMBER).size();
    }

    public boolean verifyOneTimePurchaseOptionSelected() {
        return isElementDisplayedBySeconds(ONE_IIME_PURCHASE,10);
    }

    public boolean verifySubscriptionOptionUnselected() {
        return invisibilityOfElementLocated((SUBSCRIPTION_FROM_SELECT));
    }

    public boolean verifyUserClickSubscriptionInfo() {
        waitForExpectedElement(SUBSCRIPTION_INFO, 4).click();
        return isElementDisplayedBySeconds(SUBSCRIPTION_POPUP, 4);
    }

    public String getOneTimePurchaseText() {
        return waitForExpectedElement(ONE_IIME_PURCHASE_TEXT,10).getText();
    }

    public String getSubscriptionText() {
        return waitForExpectedElement(SUBSCRIPTION_TEXT,10).getText();
    }

    public void verifyAllCTAPresentOnPagePLP() {
        assertTrue(isElementDisplayedBySeconds(SORT,4));
        waitForExpectedElement(FILTER_OPTION).click();
        assertTrue(isElementDisplayedBySeconds(FILTER_VIEW,4));
        waitForExpectedElement(FILTER_DONE).click();
        scrollToElement(FIRST_PRODUCT);
        hoverOnElement(FIRST_PRODUCT);
        assertTrue(isElementDisplayedBySeconds(CHOOSE_STRENGTH,20));
        assertTrue(isElementDisplayedBySeconds(ADD_TO_CART_PLP,4));
        try {
            waitForExpectedElement(FIRST_PRODUCT_LINK).click();
        }
        catch(Exception e) {
            clickByElementByQueryJSExecutor(FIRST_PRODUCT);
        }
    }

    public void verifyAllCTAPresentOnPagePLPOnAccessories() {
        assertTrue(isElementDisplayedBySeconds(SORT,8));
        waitForExpectedElement(FILTER_OPTION).click();
        assertTrue(isElementDisplayedBySeconds(FILTER_VIEW,4));
        waitForExpectedElement(FILTER_DONE).click();
        hoverOnElement(FIRST_ACCESSORIES);
        assertTrue(isElementDisplayedBySeconds(CHOOSE_SIZE,20));
        clickByElementByQueryJSExecutor(FIRST_ACCESSORIES);
    }

    public void verifyAllCTAPresentOnPagePLPOnChargerAccessories() {
        assertTrue(isElementDisplayedBySeconds(SORT,4));
        waitForItemToBeClickableAndClick(FILTER_OPTION,5);
        assertTrue(isElementDisplayedBySeconds(FILTER_VIEW,4));
        waitForItemToBeClickableAndClick(FILTER_DONE,5);
        scrollToElement(FIRST_CHARGER);
        hoverOnElement(FIRST_CHARGER);
        waitForItemToBeClickableAndClick(FIRST_CHARGER,5);
    }

    public void addRequiredProducts() throws InterruptedException {
        String StarterPack ="reviewapprovedProduct.key";
        batHelper.searchAddAProductToBasket(StarterPack);
        String Flavours ="searchTermVype.key";
        batHelper.searchAddAProductToBasket(Flavours);
    }

    public void verifyFirstAvailableNicotineLevelIsSelected() {
        waitForExpectedElement(IN_STOCK_PRODUCTS, 10);
        int totalProducts = Integer.parseInt(waitForExpectedElement(TOTAL, 5).getText());
        List<WebElement> inStockProducts = getWebDriver().findElements(IN_STOCK_PRODUCTS);
        List<WebElement> outStockProducts = getWebDriver().findElements(OUT_STOCK_PRODUCTS);
        Assert.assertEquals(totalProducts, inStockProducts.size() + outStockProducts.size());
        for (WebElement eleSKU : inStockProducts) {
            Assert.assertEquals(eleSKU.getAttribute("class"), "swatch-option text selected");
        }
        assertFirstNicotineStrengthIsSelectedForOutOfStockProduct(outStockProducts);
    }

    public void assertFirstNicotineStrengthIsSelectedForOutOfStockProduct(List<WebElement> list) {
        if (list.size() > 0) {
            ArrayList<String> arr = new ArrayList<>();
            waitForExpectedElement(OUT_STOCK_PRODUCTS, 10);
            for (int i = 1; i <= list.size(); i++) {
                By strength = By.xpath("(//div[div[@class='swatch-option text pointer-event-reset' and @index='0']])[" + i + "]/div");
                List<WebElement> insideStrength = getWebDriver().findElements(strength);
                for (WebElement eleSKU1 : insideStrength) {
                    String s = eleSKU1.getAttribute("class");
                    arr.add(eleSKU1.getAttribute("class"));
                }
                int strengthIndex = arr.indexOf("swatch-option text selected");
                Assert.assertEquals(arr.get(strengthIndex - 1), "swatch-option text pointer-event-reset");
                arr.clear();
            }
        } else LOG.info("No any product which has out of stock nicotine strength");
    }

    public void addPromotionFlavourProducts() {
        hoverOnElement(SHOP_FLAVOURS);
        waitAndClickByElementByJSExecutor(PROMOTION_FLAVOURS,5);
        waitAndClickByElementByJSExecutor(FIRST_PRODUCT_ZA,5);
        waitForPage();
        pdp.updateQuantity("5");
        scrollToElement(ADD_TO_BASKET);
        clickUsingJS(ADD_TO_BASKET);
        waitForItemToBeClickableAndClick(VIEW_BASKET);
    }

    public void userHoverHeaderMenu(String category) {
        hoverOnElement(By.linkText(UrlBuilder.getMessage(category)));
    }

    public void hoverOnKnowMoreAboutProduct() {
        scrollToElement(KNOW_MORE_ABOUT_PRODUCT);
        hoverOnElement(KNOW_MORE_ABOUT_PRODUCT);
    }

    public boolean isKnowMoreAboutMessagePresent() {
        return webDriver.getPageSource().contains(UrlBuilder.getMessage("knowMoreAboutMessage.key"));
    }

    public void clickKnowMoreAboutSubscriptionLink() {
        clickIndexElementByQueryJSExecutor(SUBSCRIPTION_LINK, 1);
    }

    public boolean isKnowMoreAboutSubscriptionUrlPresent() {
        return webDriver.getCurrentUrl().contains("suscripcion");
    }

    public void userHoverProductAndAddToCartPLPPage() {
        if ("it".equals(UrlBuilder.getLocale())) {
            clickUsingJS(FIRST_COLOR_IMAGE);
            scrollToElement(FIRST_PRODUCT_ADDTO_CART);
            hoverOnElement(waitForExpectedElement(FIRST_PRODUCT_ADDTO_CART));
            clickUsingJS(FIRST_PRODUCT_ADDTO_CART);
        } else {
            jsScrollElementInCenter(waitForExpectedElement(FIRST_PRODUCT_IMAGE));
            hoverOnElement(waitForExpectedElement(FIRST_PRODUCT_IMAGE));
            try {
                waitForExpectedElement((ADD_TO_CART_PRODUCT_PLP), 10).click();
            } catch (Exception e) {
                clickUsingJS(ADD_TO_CART_PRODUCT_PLP);
            }
        }

    }

    public void selectProductByName(String productName){
        if(productName==null||productName.isEmpty())
            throw new IllegalArgumentException("product name is empty.");
        String urlPro=parserEndUrlByProductName(productName);
        String cssSelector="div.product-item-group > div.product.name > a[href$='"+urlPro+"']";
        By proElement=By.cssSelector(cssSelector);
        waitAndClickByElementByJSExecutor(proElement,10);
    }
    public String parserEndUrlByProductName(String productName){
        String [] proArr=productName.split(" ");
        String splitor="-";
        StringBuffer bf=new StringBuffer();
        for(String s:proArr){
            if("neo™".equals(s)){
                bf.append("neo");
                bf.append(splitor);
                continue;
            }
            bf.append(s.toLowerCase());
            bf.append(splitor);
        }
       return bf.toString().substring(0,bf.length()-1);
    }

    public void verifyProductCountDisplayedIsSameAsProductProduct() {
        int totalcounts = Integer.parseInt(waitForExpectedElement(TOTAL_COUNT, 5).getText());
        List<WebElement> totalProducts = getWebDriver().findElements(TOTAL_PRODUCTS);
        assertEquals("*** ERROR - Product count is not same as it is shown on PLP. ", totalcounts, totalProducts.size());
    }

    public void plpPageIsScrollable() {
      scrollToPageBottom();
      selectFirstProductColorStrengthOnPLP();
    }

    public void clickLowToHighSortFilter() {
        Select se = new Select(getWebDriver().findElement(SORT_PL));
        se.selectByValue("price-asc");
    }

    public void verifyProductAreSortedLowToHigh() {
        List<WebElement> price = getWebDriver().findElements(PRODUCT_PRICE_LIST);

        List<String> prices = new ArrayList<String>();
        for (WebElement e : price)
        {
            prices.add(e.getText());
        }

        List<String> sortedPrices = new ArrayList<String>(prices);

        Collections.sort(sortedPrices);

        assertEquals(prices, sortedPrices);
    }

    public void selectSecondProductColorStrengthOnPLP() {
        homePage.closeVuseAlertIfPresent();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        if (getWebDriver().findElements(PRODUCT_COLOR_SWATCH_VUSEFR).size() > 0) {
            jsScrollElementInCenter(waitForExpectedElement(PRODUCT_COLOR_SWATCH_VUSEFR));
            clickElementByQueryJSExecutor(getWebDriver().findElements(PRODUCT_COLOR_SWATCH_VUSEFR).get(0));
        } else if (getWebDriver().findElements(PRODUCT_STRENGTH_SWATCH).size() > 0) {
            jsScrollElementInCenter(waitForExpectedElement(PRODUCT_STRENGTH_SWATCH));
            clickByElementByQueryJSExecutor(PRODUCT_STRENGTH_SWATCH);
            //clickUsingJS(PRODUCT_STRENGTH_SWATCH);
        }
    }

    public void eyesCheckPlpHoverRegion() {
        if (Props.EYES_ON & EyesCheckpoints.PLPHover.isSwitchOn()) {
            final String checkpointName = EyesCheckpoints.PLPHover.getName();
            switch(UrlBuilder.getLocale()){
                case "vuseit":
                case "vusede":
                case "glode":
                case "kz":
                case "vuseza":
                case "pl":
                case "velopl":
                    scrollToShowEntirePage();
                    scrollToPageTop();
                    waitForExpectedElement(PLP_PRODUCT_NAME_GLODE,5);
                    hoverOnElement(PLP_PRODUCT_NAME_GLODE);
                    eyes.check(checkpointName, Target.window().fully());
                    break;
                case "vuseuk":
                case "vusefr":
                    UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("plpUrl.key"));
                    scrollToShowEntirePage();
                    scrollToPageTop();
                    waitForExpectedElement(PLP_PRODUCT_NAME_GLODE,5);
                    hoverOnElement(PLP_PRODUCT_NAME_GLODE);
                    eyes.check(checkpointName, Target.window().fully());
                    break;
                case "vuseco":
                    scrollToShowEntirePage();
                    scrollToPageTop();
                    hoverOnElement(PLP_PRODUCT_NAME_GLODE);
                    waitForPage();
                    eyes.check(checkpointName, Target.window().fully());
                    break;
            }
        }
    }

    public void eyesCheckFlavorSubscriptionModalPage() {
        if (Props.EYES_ON & EyesCheckpoints.FlAVOR_SUBSCRIPTION_MODAL.isSwitchOn()) {
            final String checkpointName = EyesCheckpoints.FlAVOR_SUBSCRIPTION_MODAL.getName();
            switch(UrlBuilder.getLocale()){
                case "vusefr":
                    waitForExpectedElement(PLP_FLAVOUR_SUBSCRIPTION_MODAL,5);
                    waitAndClickByElementByJSExecutor(PLP_FLAVOUR_SUBSCRIPTION_MODAL,3);
                    scrollToShowEntirePage();
                    eyes.check(checkpointName, Target.window().fully());
                    clickIndexElementByQueryJSExecutor(CLOSE_SUBSCRIPTION_VUSE_FR,4);
                    break;

            }
        }
    }

    public Integer getProductTotalCount(){
        return Integer.parseInt(waitForExpectedElement(TOTAL_COUNT).getText().trim());
    }

    public void selectFirstsubscriptionOptionOnPLP() {
        homePage.closeVuseAlertIfPresent();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        waitAndClickByElementByJSExecutor(SUBSCRIPTION_OPTION_PLP_VUSEFR, 5);
    }

    public String getQTYErrorMessageOnPLPPage() {
        return waitForExpectedElement(QTY_EXCEEDED_ERROR_MESSAGE_VUSEFR, 10).getText();
    }

    public List<String> getEquipmentCategories() {
        List<WebElement> categoriesElements=getWebDriver().findElements(EQUIPMENT_CATEGORIES);
        List<String> categoriesText=new ArrayList<>();
        for(WebElement e:categoriesElements){
            categoriesText.add(e.getAttribute("innerHTML").trim());
        }
        waitForAjaxElementNotToBePresent(getWebDriver(),15);
        return  categoriesText;
    }

    public void assertSameProductDetailsForLABProducts() {
        waitForExpectedElement(MINICART_PRODUCTS_LIST, 10);
        waitForExpectedElement(MINICART_PRODUCTS_PRICES, 10);
        productItems = waitForExpectedElement(MINICART_PRODUCTS_LIST).findElements(By.tagName("li"));
        Assert.assertEquals(getWebDriver().findElements(MINICART_PRODUCTS_NAMES).size(), 1);
        Assert.assertEquals(getWebDriver().findElements(MINICART_PRODUCTS_PRICES).size(), 1);
        Assert.assertEquals(getWebDriver().findElements(MINICART_PRODUCTS_QTYS).size(), 1);
    }

    public void assertSupplementaryText(){
        scrollToElement(SUPPLEMENTARY_FILED);
        Assert.assertTrue(waitForExpectedElement(SUPPLEMENTARY_FILED).getText()
                .equalsIgnoreCase(UrlBuilder.getMessage("supplementaryText.key")));
    }

    public void assertSupplementaryBlockExist(){
        Assert.assertTrue(getWebDriver().findElements(SUPPLEMENTARY_CHILDREN).size()>1);
    }

    public List<WebElement> getProductsOnPLP() {
        return (getWebDriver().findElements(PLP_PRODUCT_NAME));
    }

    public String getAllQuickFilter(){
        allQuickfilters = presenceOfAllElementsLocatedBy(QUICK_FILTER_LIST);
        String quickFilterTextList = allQuickfilters.stream().map(WebElement::getText).map(e->e.trim()).map(Object::toString).collect(Collectors.joining(","));
        return quickFilterTextList;
    }

    public boolean eachResultContainsQuickFilter() {
        waitForElementToAppearAndDisappear(LOADER, 3, 3);
        List<WebElement> filteredList;
        for (WebElement quickfilter : allQuickfilters) {
            if(!quickfilter.getAttribute("class").equals("active")) {
                quickfilter.click();
                waitForPage();
            }
            filteredList = waitForExpectedElements(FILTERED_PRODUCT_LIST);
            for(int i=1;i<=filteredList.size();i++){
                for (WebElement targetStrength : filteredList) {
                    if (!targetStrength.getAttribute("data-vuse-strength").toLowerCase().contains(quickfilter.getText().toLowerCase())) {
                        LOG.info("The number "+i+"product'quick filter doesn't contain "+quickfilter.getText().toLowerCase());
                        return false;
                    }
                }
            }
            quickfilter.click();
            waitForPage();
        }
        return true;
    }

    public void clickOnFilter(){
        waitForItemToBeClickableAndClick(FILTER_OPTION,5);
    }

    public void clickOnFirstBuyableProd(){
        waitForExpectedElements(AVALANCHE_PLP_BUYABLE_PRODUCT).get(1).findElement(By.cssSelector("a")).click();
    }


    public void selectMoreThanOneOptionOfSameAttribute(){
        waitForItemToBeClickableAndClick(ZERO_MG_STRENGTH,10);
        waitForItemToBeClickableAndClick(SIX_MG_STRENGTH,10);
        isSelected(ZERO_MG_STRENGTH);
        isSelected(SIX_MG_STRENGTH);
    }

    public void verifyOptionSelectedInQuickFilterIsSelectedInClassicFilter(){
        String zero_Strength = waitForExpectedElement(ZERO_MG_STRENGTH, 10).getText();
        String six_Strength = waitForExpectedElement(SIX_MG_STRENGTH, 10).getText();
        isSelected(ZERO_MG_STRENGTH_CLASSIC);
        isSelected(SIX_MG_STRENGTH_CLASSIC);
        String zero_Strength_classic = waitForExpectedElement(ZERO_MG_STRENGTH_CLASSIC, 10).getText();
        String six_Strength_classic = waitForExpectedElement(SIX_MG_STRENGTH_CLASSIC, 10).getText();
        assertTrue(zero_Strength_classic.contains(zero_Strength));
        assertTrue(six_Strength_classic.contains(six_Strength));
    }

    public void uncheckedFirstOptionStrength(){
        waitForItemToBeClickableAndClick(ZERO_MG_STRENGTH_CLASSIC);
        clickUsingJS(FINISH_SELECTION);
    }

    public void userClickOnSearchIconAndSubmitsSearchTermSelectTextOnRight(String searchTerm) {
        enterDataAndWait(pdp.SEARCH_INPUTBOX_UK, UrlBuilder.getMessage(searchTerm));
        waitForExpectedElement(pdp.SEARCH_SUGGESTION, 10);
        clickUsingJS(FIRST_TEXT);

    }

    public void selectASubscriptionOnPLPAndAddToCart() {
        homePage.closeVuseAlertIfPresent();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        scrollToPageTop();
        jsScrollElementInCenter(waitForExpectedElement(SUB_PRODUCT_STRENGTH_SWATCH));
        clickUsingJS(SUB_PRODUCT_STRENGTH_SWATCH);
        jsScrollElementInCenter(waitForExpectedElement(SUB_PRODUCT_SUB_OPTION));
        waitForExpectedElement(SUB_PRODUCT_SUB_OPTION).click();
        waitForExpectedElement(ADD_TO_BASKET_BUTTON_PLP, 10).click();

        if (isElementPresent(homePage.OK_BUTTON_ATTENTION_ALERT)) {
            jsScrollElementInCenter(waitForExpectedElement(homePage.OK_BUTTON_ATTENTION_ALERT));
            clickUsingJS(homePage.OK_BUTTON_ATTENTION_ALERT);
        }
    }

    public String getAddToBasketSuccessMessage() {
        return waitForExpectedElement(ADD_TO_BASKET_SUCCESS_MSG, 10).getText();

    }

    public void selectFiltersOptions(){
        clickUsingJS(PLP_FILTER_ONE);
        clickUsingJS(PLP_FILTER_TWO);
        isSelected(PLP_FILTER_ONE);
        isSelected(PLP_FILTER_TWO);
    }

    public void clickOnEmptyLinkVerifyFilterAsClear() {
        clickUsingJS(EMPTY_FILTER);
        isSelected(PLP_FILTER_ONE);
        isSelected(PLP_FILTER_TWO);
        assertFalse(waitForExpectedElement(PLP_FILTER_ONE).isSelected());
        assertFalse(waitForExpectedElement(PLP_FILTER_TWO).isSelected());
    }

    public void verifyProductNamePriceStrengthColorPurchaseOption() {
        assertTrue(isElementDisplayedBySeconds(PRODUCT_NAME, 5));
        assertTrue(isElementDisplayedBySeconds(PRODUCT_PRICE, 5));
        selectFirstProductColorStrengthOnPLP();
        hoverOnElement(FIRST_PRODUCT);
        assertTrue(isElementDisplayedBySeconds(ADD_TO_CART, 5));

    }

    public void getPlpElements(){
        List<WebElement> products = waitForExpectedElements(AVALANCHE_PLP_PRODUCT);
        assertThat(products.size() > 0)
                .as("ERROR IamOnPage: expected to be on the PLP but couldn't find any products").isTrue();
        assertTrue(waitForExpectedElement(PLP_PRODUCT_NAME_PL).isDisplayed());
        assertTrue(waitForExpectedElement(PLP_PRODUCT_STRENGTH_MILD_PL).isDisplayed());
        clickUsingJS(PLP_PRODUCT_STRENGTH_MILD_PL);
        assertTrue(waitForExpectedElement(PLP_PRODUCT_STRENGTH_MEDIUM_PL).isDisplayed());
        clickUsingJS(PLP_PRODUCT_STRENGTH_MEDIUM_PL);
        List<WebElement> simpleproducts = waitForExpectedElements(PLP_PRODUCT_SINGLE_STRENGTH_PL);
        assertThat(simpleproducts.size() > 0)
                .as("ERROR IamOnPage: expected to be on the PLP but couldn't find any products").isTrue();
        
    }

    public void addSimpleProduct(){
        waitForExpectedElement(PLP_SIMPLE_PRODUCT_PL,5);
        hoverOnElement(PLP_SIMPLE_PRODUCT_PL);
        clickUsingJS(PLP_SIMPLE_PRODUCT_ADD_TO_CART_PL);
        waitForAjaxElementNotToBePresent(webDriver,3);
    }

    public void clickOnProductLinkOnBasisOfIndex(String strIndex) {
        lnkProductItem = By.cssSelector("ol.products.list.items.product-items li:nth-child(" + strIndex + ") div.product-item-group a.product-item-link");
        scrollToElement(lnkProductItem);
        clickByElementByQueryJSExecutor(lnkProductItem);
        waitForPage();
    }
}