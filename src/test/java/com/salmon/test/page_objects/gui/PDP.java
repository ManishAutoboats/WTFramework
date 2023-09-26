
package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.enums.PermittedBrowserMode;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.models.website.EngravingAttribute;
import com.salmon.test.page_objects.gui.admin.LoginPage;
import com.salmon.test.page_objects.gui.admin.ProductPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.asserts.SoftAssert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.upperCase;
import static org.testng.Assert.*;

@Getter
@Setter
public class PDP extends PageObject {

	PLP PlpPage = new PLP();
	HomePage homePage = new HomePage();
	SearchResult searchResult = new SearchResult();
	ProductPage productPage = new ProductPage();
	SoftAssert softAssert = new SoftAssert();
	private LoginPage adminLoginPage;
	private ScenarioContext scenarioContext;
	private static double regularServicePrice;
	private static double specialServicePrice;
	private static double customServicePrice;
	private static double productDefaultPrice;
	public static List<EngravingAttribute> engravingAttributeList = new ArrayList<>();

	public static String reviewField;

	public PDP(LoginPage adminLoginPage,ScenarioContext scenarioContext) {
		this.adminLoginPage = adminLoginPage;
		this.scenarioContext = scenarioContext;
	}

	public static boolean blnRelatedProducts = false;
	public static boolean blnUpSellProducts = false;
	public static boolean blnCrossSellProducts = false;

	// @Getter
	public By pdpProductTitle = By.cssSelector("#maincontent > div > div > div.product-details > div.product-info-main > div.product-title > div > h1 > span");
	public By MXpdpProductTitle = By.cssSelector("div.product-info-main div.page-title-wrapper.product:nth-child(1) h1.page-title > span.base");
	public static final By PDP_PRODUCT_PRICE = By.cssSelector("span.price"); // span.price");
	public By PDP_PRODUCT_TITLE_VYPE = By.cssSelector("div.page-title-wrapper.product > h1 > span > span.heading");
	private static final By PDP_PRODUCT_TITLE_FR = By.cssSelector("div.page-title-wrapper.product span.heading");
	private static final By PDP_PRODUCT_SUBTITLE_FR = By.cssSelector("div.page-title-wrapper.product span.sub-heading");
	public By prodcutPrice = By.cssSelector("span.price"); // span.price");
	public By MXproductPrice = By.cssSelector("div.product-info-price"); // span.price");
	public By productDescriptoin = By.cssSelector("div.product.attribute.overview");
	public By colourTitleText = By.cssSelector(".swatch-attribute-label");
	public By productColourSwitch = By.cssSelector("div.swatch-attribute-options.clearfix");
	public By favoritesButton = By.cssSelector("a.action.towishlist:nth-child(3)");
	public By favoritesButtonCO = By.cssSelector("a.action.towishlist");
	public By favoritesButtonIT = By.cssSelector("div.product-addto-links > a.action.towishlist:nth-child(1)");
	public By qty = By.cssSelector("#qty");
	public By btnProductColor = By.cssSelector("div.swatch-option.color");
	public By basketQty = By.cssSelector("span.counter-number");
	public By btnSwatchColor = By.cssSelector(".swatch-option.color:not([option-empty])");
	public static final By BTN_SWATCH_COLOR_FR = By.xpath("//div[@class='field qty']/ancestor::div[@class='product-item-info product-card']//div[@class='swatch-option color']");
	public static final By BTN_SWATCH_COLOR_GLOIT = By.cssSelector(".swatch-option.color");
	public static final By BTN_SWATCH_STRENGTH_VUSECO = By.cssSelector(".swatch-attribute.vuse_strength");
	public static final By COLOR_TEXT_GLOIT = By.cssSelector("span.swatch-attribute-selected-option");
	private static final By PRODUCT_QUANTITY = By.cssSelector("#qty");
	private static final By PRODUCT_PRICE_UK = By.cssSelector("div.price-box.price-final_price");
	private static final By FAQ_VUSE_DE = By.cssSelector("a[href*='haeufig-gestellte-fragen']");
	public final static By PRODUCT_SPECIFICATION = By.cssSelector("div.page-main-pagebuilder-attributes");
	public final static By PRODUCT_FEEDBACK = By.cssSelector("#reviews");
	public final static By OPINION_SEND = By.cssSelector("div.page.messages");
	public final static By MORE_EXPLORE_PRODUCTS=By.cssSelector(".item.product.product-item.flavour-classic");

	// Engraving
	public static final By ENGRAVING_PREVIEW_TEXT = By.cssSelector(".position-relative #bg_text");
	public static final By CO_ENGRAVING_PREVIEW_TEXT = By.cssSelector("#maincontent > div.columns > div > div.product-details > div.product.media > div.personalisation-preview.front.front-text.text > span");
	public static final By ENGRAVING_SUMMARY = By.cssSelector("div#resumefront_engraving");
	public static final By ENGRAVING_CLOSE_IFRAME = By.cssSelector("#close_app > i");
	public static final By CO_TO_ENGRAVING_SUMMARY = By.cssSelector("#ui-id-4");
	public static final By CO_ENGRAVING_SUMMARY = By.cssSelector("#tab-summary > div.summary > table > thead > tr > th:nth-child(2)");
	public static final By CO_ENGRAVING_CLOSE_IFRAME = By.cssSelector("a.pdp-personalisation-cancel");
	public static final By SWATCH_COLOR_BUTTON = By.xpath("//div[@class='swatch-option color']");
	public static final By SWATCH_COLOR_OR_STRENGTH_BUTTON = By.xpath("//div[@class='swatch-option color' or @class='swatch-option text']");
	public static final By ENGRAVING_FRONT_OPTIONS = By.cssSelector("#engraving_options #front");
	public static final By ENGRAVING_BACK_OPTIONS = By.cssSelector("#engraving_options #back");
	public static final By REMOVE_ENGRAVING_FRONT = By.cssSelector("#resumen_engraving #remove_front");
	public static final By ENGRAVING_SELECT_PATTEN_CTA = By.cssSelector("#pattern #opt-pattern");
	public static final By ENGRAVING_SELECT_PALMA_PATTERN = By.cssSelector("#pattern-carousel > div > div.carousel-item.active > div > div:nth-child(1) > div.thumb.pattern");
	public static final By CO_ENGRAVING_FRONT_OPTIONS = By.cssSelector("#ui-id-2");
	public static final By CO_ENGRAVING_BACK_OPTIONS = By.cssSelector("#ui-id-3");
	public static final By ENGRAVING_HEADER = By.cssSelector("#welcome > h1");
	public static final By ENGRAVING_BUTTON = By.cssSelector("#customizeButton");
	public static final By ENGRAVING_CONFIRM_PATTEN = By.cssSelector("#patterns_actions #next_pattern");
	public static final By ENGRAVING_ADD_TO_CART = By.cssSelector("#action-btns #cart");
	public static final By ENGRAVING_ERROR = By.cssSelector("#badWordFront > div");
	public static final By ENGRAVING_PATTERN = By.cssSelector("#pattern-carousel .carousel-item.active");
	public static final By ENGRAVING_MINI_ICONS_CTA = By.cssSelector("#icons #opt-pattern");
	public static final By CO_ENGRAVING_HEADER = By.cssSelector("#tab-front > div.front-options > div.tab-content-title");
	public static final By ENGRAVING_EDIT_FRONT = By.cssSelector("#resumen_engraving #edit_front");
	public static final By ENGRAVING_TEXT_SELECTION = By.cssSelector("#text_options.third.m-0.px-2.py-3.text-center.over.collapse.show");
	public static final By ENGRAVING_MINI_ICONS = By.cssSelector("#icon-carousel .carousel-inner");
	public static final By ENGRAVING_PODS_OPTIONS = By.cssSelector("#pods > h2");
	public static final By ENGRAVING_SKIN_OPTIONS = By.cssSelector("#skins> h2");
	public static final By ENGRAVING_PODS_ERROR = By.cssSelector("#pods_error");
	public static final By ENGRAVING_SKINS_ERROR = By.cssSelector("#skin_error");
	public static final By ENGRAVING_SELECT_BACK_HORIZONTAL = By.cssSelector("#backtext_options #back-h-btn");
	public static final By ENGRAVING_SELECT_FRONT_HORIZONTAL = By.cssSelector("#text_options #front-h-btn");
	public static final By ENGRAVING_SELECT_BACK_VERTICAL = By.cssSelector("#backtext_options #back-v-btn");
	public static final By ENGRAVING_SELECT_FRONT_VERTICAL = By.cssSelector("#text_options #front-v-btn");
	public static final By ENGRAVING_SELECT_FRONT_TEXT_OPTION = By.cssSelector("#front_options #front_text");
	public static final By ENGRAVING_SELECT_BACK_TEXT_OPTION = By.cssSelector("#back_options #back_text");
	public static final By ENGRAVING_FRONT_TEXT_INPUT = By.cssSelector("input#frontinput");
	public static final By ENGRAVING_BACK_TEXT_INTPUT = By.cssSelector("input#backinput");
	public static final By ENGRAVING_TEXT_NEXT = By.cssSelector("#text_options #next_text");
	public static final By CO_ENGRAVING_SELECT_FRONT_TEXT_OPTION = By.cssSelector("#tab-front > div.front-options > div.personalisation-options > div:nth-child(3) > label > span.img-wrap > img");
	public static final By ENGRAVING_TEXT_NEXT_BACK = By.cssSelector("#backtext_options #next_backtext");
	public static final By CO_ENGRAVING_FRONT_TEXT_INPUT = By.cssSelector("#personalisation_front_text");
	public static final By ENGRAVING_BACK_TEXT_INPUT = By.cssSelector("input#backinput");
	public static final By CO_ENGRAVING_BACK_TEXT_INPUT = By.cssSelector("#personalisation_back_text");
	public static final By ENGRAVING_CANCEL = By.cssSelector("#cancel");
	public static final By ENGRAVING_CUSTOMER_ENGRAVING_CTA = By.cssSelector("i.fa.fa-angle-down");
	public static final By MX_ENGRAVING_MANGO_QTY_SELECTION = By.cssSelector("#handle_simple_mango_1 > div:nth-child(2) > div > div:nth-child(3) > div");
	public static final By ENGRAVING_SELECT_MANGO = By.cssSelector("#simple_mango > div:nth-child(2) > div.flavor_qty.pt-3 > div");
	public static final By ENGRAVING_MANGO_QTY_SELECTION = By.cssSelector("#handle_simple_mango_1 > div:nth-child(2) > div > div:nth-child(3) > div");
	private static final By ENGRAVING_SELECT_MANGO_VYPE_CO = By.cssSelector("#just_mango > div:nth-child(2) > div.flavor_qty.pt-3 > div");
	private static final By ENGRAVING_MANGO_QTY_SELECTION_VYPE_CO = By.cssSelector("#handle_just_mango_1 > div:nth-child(2) > div > div:nth-child(3) > div");
	public static final By ENGRAVING_NEXT_PODS = By.cssSelector("#next_pods");
	public static final By MX_ENGRAVING_PRODUCT = By.cssSelector("#maincontent > div.columns > div > div.search.results > div.products.wrapper.grid.products-grid > ol > li.item.product.product-item.cdp_product_type-epod.vype_color-negro > div > a > span > span > img");
	public static final By CO_ENGRAVING_PRODUCT = By.cssSelector("div.show-product-button button.action.tocart.primary > span:nth-child(1)");
	private static final By ENGRAVING_POD_OPTIONS_EXPANDED = By.xpath("//div[@id='pods_options'][contains(@class,'collapse show')]");
	public static final By GLOIT_CART_QTY = By.cssSelector(".counter.qty>span.counter-number");
	public static final By VUSE_ADD_ONE_QTY = By.cssSelector("span.qty-modifier.plus.button-plus-1.icon-plus");
	public static final By CUSTOMIZE_PRODUCT_ENGRAVING= By.cssSelector("div.action.primary.customize-product");
	public static final By ENGRAVING_PRODUCT_LINK = By.cssSelector("div.product-main-info div.product.name > a.product-item-link:nth-child(1)");
	public static final By ADD_TO_PERSONALIZE_BUTTON = By.cssSelector("div.pdp-personalisation-toggle > div.add");
	public static final By PRODUCT_PERSONOLIZATION_OPTIONS=By.cssSelector("div.tabs-content div.front-options > div.personalisation-options");
	public static final By NEW_PERSONOLIZATION_OPTIONS=By.cssSelector("div.tabs-content div.front-options > div.personalisation-options > div label:nth-child(1) > span.option-title");
	public static final By MOTIFS_MINI_ICON_CUSTOMIZE_OPTION=By.cssSelector("div.tabs-content div.front-options > div.personalisation-options > div");
	public static final By MOTIFS_PERSONOLIZATION_OPTIONS=By.xpath("//label[contains(@for,'front_pattern')]");
	public static final By MINI_ICONS_PERSONOLIZATION_OPTIONS=By.xpath("//label[contains(@for,'front_icon')]");
	public static final By PERSONLIZATION_RETURN_LINK=By.cssSelector("a.front-option-back.alink");
	public static final By MOTIF_PATTERN_SELECTED=By.xpath("//label[contains(@for,'front_pattern')]//preceding::div[@class='option col-3 personalisation-custom-radio active']");
	public static final By MINI_ICON_SELECTED=By.xpath("//label[contains(@for,'front_icon')]//preceding::div[@class='option col-3 personalisation-custom-radio active']");
	public static final By PERSONLIZATION_TEXT_DIRECTION_VERTICAL_LABEL=By.cssSelector("label[for='front_text_direction_vertical']");
	public static final By PERSONLIZATION_TEXT_DIRECTION_HORIZONTAL_LABEL=By.cssSelector("label[for='front_text_direction_horizontal']");
	public static final By PERSONLIZATION_TEXT_INPUT=By.cssSelector("input#personalisation_front_text");
	public static final By PERSONLIZATION_TEXT_ERROR_LABEL=By.cssSelector("div#personalisation_front_text-error");
	private static final By PERSONLIZATION_FONT_RADIO=By.cssSelector("div#tab-back div.option.personalisation-custom-radio.font span.option-title");
	public static final By QA_SECTION=By.cssSelector("div.page-main-pagebuilder-attributes > div > div > div > div:nth-child(4) > div");

	private static final By AVALANCHE_PDP_BUYABLE_PRODUCT=By.cssSelector("body > div.bat-wrapper > div > div > div.responsivegrid.rootTemplateGrid.aem-GridColumn.aem-GridColumn--default--12 > div > div:nth-child(3) > bat-section-default > div > div > div:nth-child(2) > div > div.responsivegrid.aem-GridColumn--phone--hide.aem-GridColumn--phone--12.aem-GridColumn.aem-GridColumn--default--12.aem-GridColumn--offset--phone--0 > div > div");
	public By btnProductStrength = By.xpath("//div[@class='swatch-option text']");
	private static final By PRODUCT_STRENGTH_MX = By.cssSelector("li:nth-child(1) div.product-item-group > div.product-item-masthead > div.product-main-info > div > div > div > div > div:nth-child(2)");
	public static By addToCartButton = By.cssSelector("#product-addtocart-button,button.action.tocart.primary,action.primary.tocart");
	public static final By ADD_TO_CART_BUTTON_VUSEUK = By.cssSelector("#product-addtocart-button");
	public static final By M_ADD_TO_CART_BTN_GLOIT = By.cssSelector("div.actions > button#product-addtocart-button");
	private static final By ADD_TO_SHOPPING_CART_BUTTON = By.cssSelector("button.bat-cta-style.button-dark.center.active");
	private static final By ADD_TO_CART_BUTTON_MOBILE = By.cssSelector("button.action.tocart.secondary");
	public static final By VUSE_DE_ADD = By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product.details.product-item-details > div > div > div > form > div > button");
	public By ADD_TO_CART_BUTTON_VYPE_IE = By.cssSelector("div.sliding-panel-sections button#top-cart-btn-checkout");
	public By addToWishList = By.cssSelector("a.action.towishlist");
	public By addToCompare = By.cssSelector("a.action.tocompare");
	public By reviewTitle = By.cssSelector("h2.product-section-title");
	public By submitReviewButton = By.cssSelector("button.action.submit.primary");
	public By M_EXPAND_PRODUCT_SEARCH = By.cssSelector("header > div.bat-header-menu > div > nav > div > ul > li:nth-child(2) > a");
	public By M_EXPAND_SEARCH_INPUTBOX = By.cssSelector("span.icon-search.header-link");
	public static final By ENGRAVING_ELEMENT_VUSECO = By.cssSelector("body > div.page-wrapper > div.page.messages > div:nth-child(2) > div > div > div");
	public By M_SEARCH_INPUTBOX_UK = By.cssSelector("input#search");
	public By M_EXPAND_SEARCH_INPUTBOX_DK = By.cssSelector("div[class*='mobile-only'] li[data-slide-target*='search'] i.material-icons,span.icon-search.header-link");
	public final static By T_SEARCH_FR = By.cssSelector("body > div.page-wrapper > header > div.userbar.userbar-top.clearfix > div.userbar-right > ul > li.userbar-item.vype_search > a > i");
	public final static By SEARCH_INPUTBOX = By.cssSelector("input#search");
	public final static By SEARCH_INPUTBOX_IE = By.cssSelector("input#search");
	public final static By SEARCH_INPUTBOX_UK = By.xpath("//div[@class='column vype_search']//input[@id='search']");
	public final static By ADDTOCART_BUTTON_LYFT = By.cssSelector("button.action.tocart.primary");
	public final static By M_ADDTOCART_BUTTON = By.cssSelector("#product-addtocart-button");
	public By basketQtyLyft = By.cssSelector("span.counter.qty");
	public By vuseVeloStrength = By.cssSelector("#product-options-wrapper > div.fieldset > div > div");
	public By vuseVeloStrengthValue = By.cssSelector("#attribute500 > option:nth-child(2)");
	public final static By PDP_ADDTOCART_BUTTON = By.cssSelector("button#product-addtocart-button");
	public final static By PDP_ADDTOCART_BUTTON_DISABLED = By.cssSelector(".action.primary.disabled");
	private final static By PRODUCT_PRICE_LYFT = By.cssSelector("div.price-box.price-final_price");
	public final static By PRODUCT_PRICE_VYPE = By.cssSelector("div.price-box.price-final_price");
	private final static By PDP_PRODUCT_TITLE_LYFT = By.cssSelector("h1.page-title");
	public final static By PDP_SELECTED_STRENGTH_COLOR = By.xpath("//div[@class='swatch-option text selected' or 'swatch-option color selected'][@tabindex=0][@aria-checked='true']");
	private final static By PDP_SELECTED_COLOR = By.cssSelector("div.swatch-option.color.selected");
	private final static By UPSELL_CAROUSEL_ARROW_NEXT = By.xpath("//div[@class='block upsell']//following::button[@class='slick-next slick-arrow']");
	private final static By UPSELL_CAROUSEL_ARROW_PREV = By.xpath("//div[@class='block upsell']//following::button[@class='slick-prev slick-arrow']");
	public final static By COUPON_NAME = By.cssSelector("span.rule-name");
	public final static By COUPON_AMOUNT = By.cssSelector("span.rule-amount");
	public final static By COUPON_NAME_CHECKOUT = By.cssSelector("tr.discount>th");
	public final static By COUPON_AMOUNT_CHECKOUT = By.cssSelector("tr.discount>td.amount");
	private static final By PLUS_QUANTITY_ICON = By.cssSelector("div.control>input.qty-modifier.plus");
	private final static By CAROUSEL_NAVIGATION_RELATED_PRODUCTS = By.cssSelector("div.block.related ol.products.list.items.product-items.slick-initialized.slick-slider.slick-dotted > ul.slick-dots > li");
	private final static By CAROUSEL_NAVIGATION_UPSELL_PRODUCTS = By.cssSelector("div.block.upsell ol.products.list.items.product-items.slick-initialized.slick-slider.slick-dotted > ul.slick-dots > li");
	private final static By CAROUSEL_NAVIGATION_CROSSELL_PRODUCTS = By.cssSelector("ol.products.list.items.product-items.slick-initialized.slick-slider.slick-dotted ul.slick-dots > li");
	public final static By CROSSELL_PRODUCTS_ADDTOCART = By.cssSelector("button.action.tocart.secondary");
	private final static By CROSSELL_PRODUCTS_SECTION = By.cssSelector("div.block.crosssell");
	private final static By CROSSELL_PRODUCTS_ITEMS = By.xpath("//div[@class='block crosssell']//a[@class='product-item-link']");
	private final static By RELATED_PRODUCTS_SECTION = By.cssSelector("div.block.related");
	private final static By UPSELL_PRODUCTS_SECTION = By.cssSelector("div.block.upsell");
	private final static By RELATED_PRODUCTS_FIRST_ITEM_LINK = By.xpath("//div[@class='block related']//a[@class='product-item-link']");
	private final static By UPSELL_PRODUCTS_FIRST_ITEM_LINK = By.xpath("//div[@class='block upsell']//a[@class='product-item-link']");
	public final static By CUSTOMIZE_ADD_TO_CART = By.cssSelector("#bundle-slide");
	public final static By VYPEIE_STRENGTH = By.cssSelector(".swatch-option.text");
	public final static By VYPEUK_CHOOSE_STRENGTH = By.cssSelector("#product-addtocart-button>span");
	private final static By ADD_TO_WISHLIST_BTN = By.cssSelector("a.action.towishlist");
	private static final By PDP_PRODUCT_NAME_CSS = By.cssSelector("div.product-info-main > div.product-title > div > h1 > span, ol.products.list.items.product-items li:nth-child(1) div.product.name a");
	public static final By PDP_PRODUCT_NAME_CSS_FR = By.cssSelector("span.heading, #product-list-plp > li:nth-child(1) > div > div.product-item-group > h3 > a");
	private static final By PDP_PRODUCT_NAME_CSS_LYFTSE = By.cssSelector(".base");
	private static final By PDP_PRODUCT_NAME_CSS_MX = By.cssSelector("ol.products.list.items.product-items li:nth-child(1) div.product.name a, h1 > span");
	private static final By PDP_PRODUCT_NAME_CSS_IT = By.cssSelector("ol.products.list.items.product-items li:nth-child(2) div.product.name a");
	private static final By PDP_PRODUCT_NAME_CSS_VUSEDE = By.cssSelector("div.page-title-wrapper.product > h1 > span > span");
	private static final By PDP_PRODUCT_NAME_CSS_GLODE = By.cssSelector("span.base");
	private static final By PDP_PRODUCT_NAME_CSS_VUSEIT = By.cssSelector(".product-item-link");
	private static final By GROUPED_PRODUCT = By.cssSelector("#product_addtocart_form > div.table-wrapper.grouped");
	private static final By INCREMENT_BUTTON = By.cssSelector("#product_addtocart_form div.box-tocart > div > div.box-tocart__swatch-qty > div.field.qty > div > span.qty-modifier.plus.button-plus-1.icon-plus");
	private static final By DECREMENT_BUTTON = By.cssSelector("[class='qty-modifier minus button-minus-1 icon-minus']");
	private static final By VIEW_BASKET = By.cssSelector("#minicart-content-wrapper > div");
	private static final By PRODUCT_QUANTIITY = By.cssSelector("#qty");
	private static final By ADD_ENGRAVE = By.cssSelector("#product_addtocart_form > div.pdp-personalisation-toggle");
	private static final By CANCEL_ENGRAVE = By.cssSelector("#tab-front > div.front-options > div.action-toolbar > a");
	private static final By ENGRAVING_DETAILS = By.cssSelector("#pdp-personalisation-tabs");
	private static final By DECREMENT_BUTTON_EPOD = By.cssSelector("div.product-options-bottom span[class*='qty-modifier minus']");
	private static final By PDP_PRODUCT_NAME_ZA = By.cssSelector("span.heading, #product-list-plp > li:nth-child(1) > div > div.product-item-group > div.product.name > a");
	private static final By VIEW_BASKET_ZA = By.cssSelector("div.actions.minicart-actions > div > a");
	private static final By YOUR_BASKET = By.cssSelector("div.minicart-title");
	private static final By ADD_TO_BASKET_SUCCESS_MSG = By.cssSelector("div.product-info-main div.message-success");
	public static final By SEARCH_SUGGESTION = By.cssSelector("#search_autocomplete > div > dl > dt");
	private static final By FIRST_SUGGESTION = By.cssSelector("#qs-option-3 > div.product-image-box");
	public static final By NEXT_DAY_DELIVERY_BLOCK = By.cssSelector("p.next-day-delivery-details-info");
	public static final By NEXT_DAY_DELIVERY_COUNTDOWN=By.cssSelector("span.delivery-countdown");
	private static final By ERROR_MESSAGE_OUT_OF_STOCK = By.cssSelector("div.message-error.error.message");
	public static final By ADD_REVIEW_SUCCESS_MSG = By.cssSelector("div.message-success.success.message");

	//PDP Star/Review Ratings
	private final static By AVIS_RATINGS_LINK = By.cssSelector("a.netreviews-stars-link");
	private final static By PDP_RATINGS_SECTION = By.cssSelector("div.netreviews_rating_content");
	private final static By AVIS_RATINGS_ICON = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(5) > div:nth-child(3) > div > a");
	private final static By AVIS_RATINGS_HEADER = By.cssSelector("div.row.top");
	private final static By AVIS_RATINGS_POPUP = By.cssSelector("div.avis-reviews-frame");
	private final static By AVIS_POPUP_CLOSE = By.xpath("//aside[contains(@class,'_inner-scroll _show')]//button[contains(@class,'action-close')]");
	public final static By LINK_SIXPACK_PRODUCT = By.xpath("//a[contains(@title,'6 PACK')]");
	public final static By LINK_THREEPACK_PRODUCT = By.xpath("//a[contains(@title,'3 PACK')]");
	private final static By PDP_SELECTED_PRODUCT = By.cssSelector("div.product.attribute.description");
	private final static By PDP_IMAGE_THUMBNAILS = By.xpath("//div[contains(@class,'nav--thumbs')]");
	private final static By PRODUCT_IMAGE_THUMBNAIL = By.xpath("//div[contains(@class,'nav__frame--thumb')][1]");
	private final static By PRODUCT_IMAGE_WITH_SELECTED_THUMBNAIL = By.cssSelector("div.fotorama__stage__shaft>div:nth-child(1)>img");
	private final static By MOBILE_PRODUCTIMAGE_CAROUSAL = By.cssSelector("div.fotorama__nav__frame.fotorama__nav__frame--dot");
	private final static By MOBILE_ACTIVE_IMAGE_CAROUSAL = By.cssSelector("div.fotorama__nav__frame.fotorama__nav__frame--dot.fotorama__active");
	private final static By PRODUCT_PERSONALIZABLE = By.cssSelector("div[class='pdp-personalisation-toggle']");
	private final static By PRODUCT_PERSONALIZABLE_TAB = By.xpath("//ul[@class='tabs-navigation ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all']");
	private final static By PRODUCT_PERSONALIZABLE_OPTIONS = By.cssSelector("div[class='personalisation-options']");
	private final static By LINK_PERSONALIZABLE_BACK_ONLY = By.cssSelector("div[class='tabs-content'] div:nth-child(1) div:nth-child(5) a:nth-child(1)");
	private final static By LINK_CANCEL_PERSONALIZABLE_SERVICE = By.cssSelector("p[class='pdp-personalisation-cancel']");
	private final static By LINK_CANCEL_PERSONALIZABLE_SERVICE_UXRESET = By.cssSelector("div[class='front-options'] a[class='pdp-personalisation-cancel']");
	private final static By PERSONALIZE_SERVICE_PRICE = By.cssSelector("div[class='pdp-personalisation-toggle']");
	private final static By SKIP_FRONT_PERSONALIZE = By.cssSelector("#tab-front > div.actions-toolbar:nth-child(5) > a");
	private final static By FRONT_PERSONALIZE_HORIZENTAL_TEXT_TAB = By.cssSelector("label[for='front_text_direction_horizontal']");
	private final static By ENGRAVING_ERROR_VALIDATION_MESSAGE = By.cssSelector("#personalisation_front_text-error");
	private final static By SKIP_BACK_PERSONALIZE = By.cssSelector("#tab-back > div.actions-toolbar:nth-child(6) > a");
	private final static By PATTERN_BACK_BUTTON_PERSONALIZE = By.cssSelector("div[id='front-pattern-content'] a[class='front-option-back alink']");
	private final static By ICON_BACK_BUTTON_PERSONALIZE = By.cssSelector("div[id='front-icon-content'] a[class='front-option-back alink']");
	private final static By TEXT_BACK_BUTTON_PERSONALIZE = By.cssSelector("div[id='front-text-content'] a[class='front-option-back alink']");
	private final static By PERSONALIZE_COLOR_SWATCH_OPTIONS = By.cssSelector("div[class='swatch-opt']");
	private final static By PERSONALIZE_BACK_TAB_BACK_BUTTON = By.cssSelector("div[id='tab-back'] a[class='tab-toggle alink']");
	private final static By PERSONALIZE_SUMMARY_TAB_BACK_BUTTON = By.cssSelector("div[id='tab-summary'] a[class='tab-toggle alink']");
	private final static By PDP_DELIVERY_MESSAGE = By.cssSelector("div.delivery-details-container");
	private final static By PERSONALIZE_SERVICE_PATTERNS = By.cssSelector("div[data-target='front-pattern-content']");
	private final static By PERSONALIZE_SERVICE_ICONS = By.cssSelector("div[data-target='front-icon-content']");
	private final static By PERSONALIZE_SERVICE_TEXTS = By.cssSelector("div[data-target='front-text-content']");
	private final static By NUMBEROF_PERSONALIZE_PATTERN_OR_ICON_IMAGES = By.cssSelector("div[class='front-option-content active'] div[class='personalisation-options personalisation-options-slider']>div");
	private final static By NUMBER_OF_PERSONALIZE_PATTERN_OR_ICON_IMAGES = By.cssSelector("div[class='front-option-content active'] div[class='option col-3 personalisation-custom-radio']");
	private final static By ASSERTION_PERSONALIZE_PATTERN_IMAGE_ON_DEVICE = By.cssSelector("div[class='personalisation-preview front pattern']>img");
	private final static By BUTTON_ADD_AND_CONTINUE_PATTERN_FRONT = By.cssSelector("button#front-pattern-save");
	private final static By BUTTON_ADD_AND_CONTINUE_ICONS_FRONT = By.cssSelector("button#front-icon-save");
	private final static By BUTTON_ADD_AND_CONTINUE_TEXT_FRONT = By.cssSelector("button#front-text-save");
	private final static By CANCEL_BUTTON_BACKSIDE_ENGRAVING = By.cssSelector("div[id='tab-back'] a[class='pdp-personalisation-cancel']");
	private final static By CANCEL_BUTTON_FRONTSIDE_ENGRAVING = By.cssSelector("div[class='front-options'] a[class='pdp-personalisation-cancel']");
	private final static By CANCEL_BUTTON_SUMMARY_ENGRAVING = By.cssSelector("div[id='tab-summary'] a[class='pdp-personalisation-cancel']");
	private final static By BUTTON_ADD_AND_CONTINUE_BACK = By.cssSelector("button#back-text-save");
	private final static By PERSONALIZE_SERVICE_TEXT_AT_BACK = By.cssSelector("input#personalisation_back_text");
	private final static By PERSONALIZE_SERVICE_TEXT_AT_FRONT = By.cssSelector("input#personalisation_front_text");
	private final static By PERSONALIZE_ORIENTATTION_ACTIVE_RADIO_BACK = By.cssSelector("#tab-back div.control.personalisation-options.select-first div.option.personalisation-custom-radio.active");
	private final static By PERSONALIZE_ORIENTATTION_ACTIVE_RADIO_FRONT = By.cssSelector("div#front-text-content div.control.personalisation-options.select-first div.option.personalisation-custom-radio.active");
	private final static By PERSONALIZE_ORIENTATTION_ACTIVE_RADIO = By.cssSelector("#tab-back div.control.personalisation-options.select-first div.option.personalisation-custom-radio.active");
	private final static By PRODUCT_PRICE_WITHOUT_PERSONALIZE_SERVICE = By.cssSelector("div[class='product-info-price'] meta[itemprop='price']");
	private final static By PERSONALIZE_SERVICE_SPECIAL_PRICE = By.cssSelector("div.price>span.special-price");
	private final static By PERSONALIZE_SERVICE_CUSTOME_PRICE = By.cssSelector("div[class='price']");
	private final static By BUTTON_PERSONALIZE_ADD = By.xpath("//div[@class='add']");
	private final static By COLOR_SWATCH_DIV = By.cssSelector("div.swatch-opt");
	private final static By MODIFY_FRONT_SUMMARY = By.cssSelector("#summary-front > td.actions > a.edit.tab-toggle");
	private final static By DEFAULT_PRICE_NOTICE = By.cssSelector("p[class='psn-summary-default-price-notice message notice']");
	private final static By REMOVE_FRONT_END_CHOICE = By.cssSelector("#summary-front > td.actions > a.remove.clear-selection");
	private final static By REMOVE_BACK_END_CHOICE = By.cssSelector("#summary-back > td.actions > a.remove.clear-selection");
	private final static By FRONT_ENGRAVING_DETAILS = By.cssSelector("tr[id='summary-front'] dl>dd");
	private final static By PRODUCT_FAQ = By.cssSelector(".common-questions-content");
	//private final static By MINICART_FRONT_ENGRAVING_DETAILS = By.xpath("//div[@class='sliding-panel-section sliding-minicart']//dd[@class='values']");
	private final static By MINICART_FRONT_ENGRAVING_DETAILS = By.cssSelector("//*[@class='product options psn-options active']//dd[@class='values']/text()");
	private final static By MINICART_ENGRAVING_DETAILS = By.cssSelector("div[class='product options psn-options active']");
	private final static By PDP_ENGRAVING_PERSONALISATION_PANEL = By.cssSelector("#pdp-personalisation-panel-price");
	private final static By PDP_ENGRAVING_PERSONALISATION_ICON_TOOLTIP = By.cssSelector("span[class='icon-tooltip']");
	private final static By BACK_ENGRAVING_DETAILS = By.cssSelector(".sliding-minicart.sliding-panel-section > .minicart-wrapper  div#minicart-content-wrapper ol#mini-cart div[role='tablist'] > div[role='tabpanel'] > dl:nth-of-type(2) > .values");
	private final static By PLP_ENGRAVING_PRODUCT_SUMMARY_PRICE = By.cssSelector("div.psn-summary-price.psn-summary-price--personalised");
	private final static By PRODUCT_TOTAL_PRICE_INCLUDING_ENGRAVING = By.cssSelector("div[class='pdp-total-price'] span span[class='price']");
	private final static By PRODUCT_ONLY_PRICE_EXCLUDING_ENGRAVING_IN_PRICEBREAKDOWN_POPUP = By.xpath("//*[@id='pdp-price-breakdown']/table/tbody/tr[1]/td[2]/span");
	private final static By ENGRAVING_ONLY_PRICE_IN_PRICEBREAKDOWN_POPUP = By.xpath("//*[@id='pdp-price-breakdown']/table/tbody/tr[2]/td[2]/span");
	private final static By TOTAL_PRICE_IN_PRICEBREAKDOWN_POPUP = By.xpath("//*[@id=\"pdp-price-breakdown\"]/table/tfoot/tr/td[2]/span");
	public final static By RELATED_PRODUCTS = By.cssSelector(".block.related, .products-related");
	public final static By PDP_VIDEO = By.cssSelector(".pagebuilder-video-container");
	public final static By REVIEW_NICKNAME = By.cssSelector("#nickname_field");
	public final static By REVIEW_LIST = By.cssSelector(".ts-reviews");
	private final static By SLIDER_REGION = By.cssSelector(".slick-slider.slick-dotted");
	public final static By MINICART_CLOSE_BUTTON = By.cssSelector("button#btn-minicart-close");
	public final static By M_MINICART_POPUP_VUSEZA = By.cssSelector("div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.mage-dropdown-dialog");
	public final static By MINICART_CLOSE_BUTTON_VELOPL = By.cssSelector("bat-minicart-avalanche > div > button");
	public final static By LOGO_VUSEDE = By.cssSelector("div.column.logo-container a");
	public final static By ASK_QUESTION_BUTTON = By.cssSelector("button#netreviewsqa-show-question");
	public final static By ASK_QUESTION_BUTTO_POPUP = By.cssSelector("form#form-nr-question");
	private final static By ASK_QUESTION_FIRST_NAME = By.cssSelector("input#nr-qr-firstName");
	private final static By ASK_QUESTION_FIRST_NAME_ERROR = By.cssSelector("#nr-qr-firstName-error");
	private final static By ASK_QUESTION_LAST_NAME = By.cssSelector("input#nr-qr-lastName");
	private final static By ASK_QUESTION_EMAIL = By.cssSelector("input#nr-qr-email");
	private final static By ASK_QUESTION_EMAIL_ERROR = By.cssSelector("#nr-qr-email-error");
	private final static By ASK_QUESTION_QUESTION_TITLE = By.cssSelector("input#nr-qr-questionTitle");
	private final static By ASK_QUESTION_QUESTION_TITLE_ERROR = By.cssSelector("#nr-qr-questionTitle-error");
	private final static By ASK_QUESTION_QUESTION_CONTENT = By.cssSelector("#nr-qr-questionContent");
	private final static By ASK_QUESTION_QUESTION_CONTENT_ERROR = By.cssSelector("#nr-qr-questionContent-error");
	public final static By ASK_QUESTION_QUESTION_TICKBOX = By.cssSelector("input#netreviewsqa-checkboxCgu1");
	public final static By ASK_QUESTION_QUESTION_SUBMIT = By.cssSelector("button#netreviewsqa-submit-question");
	private final static By NOTICE_ME_WHEN_IN_STOCK=By.cssSelector("button.action.primary[id^='product-notify-button']");
	// Tofino Products
	public static final By FIND_OUT_MORE_LINK = By.xpath("//a[contains(@href,'cbd')][contains(text(),'find out more')]");
	private static final By CBD_STRENGTH_LABEL_PDP = By.cssSelector("span.swatch-attribute-label");
	private static final By NEWSLETTER_SUBSCRIPTION_PDP = By.cssSelector("button.action.subscribe.primary > span");
	private static final By TOFINO_FOOTER_DISCLAIMER_PDP = By.cssSelector("p.tofino-disclaimer");
	public static final By SELECTED_CBD_STRENGTH_PDP = By.cssSelector("div.swatch-option.text.selected");
	public static final By SELECTE_VELOPL_STRENGTH_PDP = By.cssSelector("div.bat-producthero-strengths-buttons > button");
	private static final By STRENGTH_PDP = By.cssSelector("div.swatch-opt div.swatch-option.text");

	private final static By FIND_OUT_MORE_OPTION = By.xpath("//a[contains(@href,'cbd')][contains(text(),'find out more')]");
	private final static By INVALID_POSTAL_CODE = By.cssSelector("div.invalid-postcode-message-content>h4");
	private final static By COLOURSWATCHCHECK = By.className("swatch-option color selected");

	private static final By SPECIFICATION_SECTION = By.cssSelector("div.product-specs__fields");
	private static final By SPECIFICATION_TITLE = By.cssSelector("h3.product-specs__title");
	public static final By STAR_RATING = By.cssSelector("div.rating-result");
	public static final By STAR_RATING_REVIEW = By.cssSelector("div.reviews-actions>a");
	public static final By ADD_REVIEW_BUTTON = By.cssSelector("button.product-reviews__create.action.secondary");
	public static final By M_ADD_REVIEW_BUTTON = By.cssSelector("button.product-reviews__create-mobile.action.secondary");
	public static final By GUST_USER_REVIEW_MESSAGE = By.cssSelector("div.notloggin.hide-mobile");
	public static final By GUST_USER_REVIEW_SIGNIN_LINK = By.cssSelector("div.notloggin.hide-mobile a");
	private static final By FIRST_NAME_TITLE = By.cssSelector("input#nickname_field");
	private static final By TITLE_FIELD = By.cssSelector("input#summary_field");
	private static final By REVIEW_DESCRIPTION = By.cssSelector("#review_field");
	private static final By REVIEW_SUBMIT_BUTTON = By.cssSelector("button.action.submit.primary");
	private static final By REVIEW_STAR_RATING = By.cssSelector("div.control.review-control-vote>input");
	public static final By REVIEW_TITLE_LIST = By.cssSelector("h3.review__title");
	public static final By REVIEW_STAR_RATING_ERROR_TXT = By.cssSelector("div#ratings\\[4\\]-error.mage-error");
	public static final By REVIEW_NAME_ERROR_TXT = By.cssSelector("div#nickname_field-error");
	public static final By REVIEW_SUMMARY_ERROR_TXT = By.cssSelector("div#summary_field-error");
	public static final By REVIEW_COMMENTS_ERROR_TXT = By.cssSelector("div#review_field-error");
	public static final By DELIVERY_TIMELINE_MESSAGE = By.cssSelector("p.delivery-details-title");
	public static final By MORE_INFORMATION_LINK= By.cssSelector("p.delivery-details-info > a");
	public static final By STAR_RATING_REVIEW_VUSEUK = By.cssSelector("div.bv_rating_summary button");
	public static final By STAR_FIVE_RATING_REVIEW = By.cssSelector("div.bv_stars_component_container>svg:nth-child(5)");
	public static final By WRITE_FIRST_REVIEW_BUTTON = By.cssSelector("button#first-to-write");
	public static final By WRITE_REVIEW_BUTTON = By.cssSelector("button[class*='bv-write-review']");
	public static final By WRITE_REVIEW_MODAL_WINDOW = By.cssSelector("div#bv-mbox-lightbox-list");
	private static final By REVIEW_FIVE_STAR_MODAL_WINDOW = By.cssSelector("span#bv-radio-rating-5");
	private static final By REVIEW_STARS_MODAL_WINDOW = By.cssSelector("span[id*='bv-radio-rating-']");
	private static final By OVERALL_RATING_COMMENT = By.cssSelector("span.bv-rating-helper.bv-rating-helper-1");
	private static final By REVIEW_TITLE = By.cssSelector("input#bv-text-field-title");
	private static final By REVIEW_TEXT_AREA = By.cssSelector("textarea[name='reviewtext']");
	private static final By EMAIL_INPUT = By.cssSelector("input#bv-email-field-hostedauthentication_authenticationemail");
	private static final By NICK_NAME_INPUT = By.cssSelector("input#bv-text-field-usernickname");
	private static final By LOCATION_INPUT = By.cssSelector("input#bv-text-field-userlocation");
	private static final By QUALITY_STAR_RATING = By.cssSelector("span[class*='bv-radio-input bv-rating_Quality-input']");
	private static final By VALUE_STAR_RATING = By.cssSelector("span[class*='bv-radio-input bv-rating_Value-input ']");
	private static final By TERMS_CONDITIONS_CHECKBOX = By.cssSelector("input#bv-checkbox-reviews-termsAndConditions");
	private static final By TERMS_CONDITIONS_LINK = By.cssSelector("a.bv-text-link.bv-focusable");
	private static final By TERMS_CONDITIONS_POPUP = By.cssSelector("div#bv-mbox-terms-and-conditions-list");
	private static final By TERMS_CONDITIONS_ACCEPT_BUTTON = By.cssSelector("button.bv-button.bv-accept-tc-button.bv-focusable");
	public static final By REVIEW_SUBMITTED_WINDOW = By.cssSelector("span.bv-submission-icon");
	public static final By REVIEW_SUBMITTED_TEXT = By.cssSelector("h2#bv-mbox-label-text");
	private static final By POST_REVIEW_BUTTON = By.cssSelector("button.bv-form-actions-submit.bv-submit.bv-focusable.bv-submission-button-submit");
	public static final By REVIEW_SUBMITTED_CLOSE_OPTION = By.cssSelector("div#bv-mbox-lightbox-list>button");
	private static final By REVIEWED_DISCRIPTION_ON_PDP = By.xpath("(//div[@class='bv-content-summary-body-text']/p)[1]");
	private static final By PRODUCT_COLOR_SELECTED= By.xpath("//div[@class='swatch-option color selected'][@aria-checked='true']");
	private static final By PRODUCT_STRENGTH_SELECTED=By.xpath("//div[@class='swatch-option text selected'][@aria-checked='true']");
	private static final By OOS_PRODUCT_SWATCH = By.xpath("//div[@class='swatch-option text pointer-event-reset' or @class='swatch-option color pointer-event-reset']");
	private static final By NOTIFY_ME_BUTTON=By.xpath("//div[contains(@id,'back-in-stock')]//button[@class='action primary']");
	private static final By NOTIFY_ME_POP_UP_WINDOW=By.cssSelector("strong.block-customer-login-heading");
	private static final By NOTIFY_ME_WINDOW_CLOSE=By.cssSelector("aside.modal-popup.popup-authentication-custom.modal-slide._inner-scroll._show header.modal-header > button.action-close");
	private static final By ONE_IIME_PURCHASE=By.cssSelector("div.subscription-container > div.fieldset.pdp-subscriptionpro-options > div.field.choice.active");
	private static final By SUBSCRIPTION_FROM=By.cssSelector("div.fieldset.pdp-subscriptionpro-options > div.field.choice.subscription");
	private static final By ONE_TIME_PRICE=By.cssSelector("div.fieldset.pdp-subscriptionpro-options > div:nth-child(1) > span > label > span.option_price");
	private static final By SUBSCRIPTION_PRICE=By.cssSelector("div.field.choice.subscription.active > span > label > span.option_price");
	private static final By SUBSCRIPTION_PRICE_FR=By.cssSelector("div.field.choice.subscription > div > label > span.option_price");
	private static final By SUBSCRIPTION_INFO=By.cssSelector("div.field.choice.subscription.active > span > label > span.option_price > span.subscribepro-learn-more");
	private static final By SUBSCRIPTION_INFO_IT=By.cssSelector("label > span.option_price > span.subscribepro-learn-more");
	private static final By SUBSCRIPTION_SAVE	=By.cssSelector("div.price-div > span > span > span");
	private static final By SUBSCRIPTION_FROM_SELECT=By.cssSelector("div.fieldset.pdp-subscriptionpro-options > div.field.choice.subscription.active");
	private static final By ONE_TIME_PRICE_IT=By.cssSelector("div.fieldset.pdp-subscriptionpro-options > div:nth-child(1) > div > label > span.option_price");
	private static final By PRICE_IT=By.xpath("//*[@data-price-type='finalPrice']");
	private static final By SUBSCRIPTION_PACKS_SILVER=By.cssSelector("tbody > tr:nth-child(2) > td > strong");
	private static final By SUBSCRIPTION_PACKS_GOLD=By.cssSelector("tbody > tr:nth-child(3) > td > strong");
	private static final By SUBSCRIPTION_PACKS_PLATINUM=By.cssSelector("tbody > tr:nth-child(4) > td > strong");
	private static final By SUBSCRIPTION_PAGE_1=By.cssSelector("div.subscription-tiles-container > div > div > div:nth-child(2) > div > div > div:nth-child(1)");
	private static final By SUBSCRIPTION_PAGE_2=By.cssSelector("div.subscription-tiles-container > div > div > div:nth-child(2) > div > div > div:nth-child(2)");
	private static final By SUBSCRIPTION_PLP=By.cssSelector("#option_choice_wrapper_4776 > div.field.choice.subscription > div > label");
	public static final By STAR_RATING_REVIEW_ZA = By.cssSelector("#product-review-container");
	private static final By VIEW_MORE_SUBSCRIPTION=By.cssSelector("a.view-more-subs");
	private static final By SUBSCRIPTION_INFO_POPUP=By.cssSelector("aside.modal-popup.modal-slide._show");
	private static final By CANCEL_SUBSCRIPTION=By.cssSelector("div.bottom-action > a.subscription-update-event");
	public static final By STAR_RATING_PL = By.cssSelector("div.rating-summary > div");
	public static final By STAR_RATING_OPINION = By.cssSelector("#review-form > div > div > button");
	public static final By ADD_RATING_QUALITY = By.cssSelector("#Jakość_4_label");
	public static final By ADD_RATING_PRICE = By.cssSelector("#Cena_4_label");
	public static final By ADD_RATING_FUNCTIONALITY = By.cssSelector("#Funkcjonalność_4_label");
	private static final By SUBSCRIPTION_PACKS_BRONZE=By.cssSelector("tr:nth-child(2) > td > div");
	private static final By SUBSCRIPTION_PACKS_MONEY=By.cssSelector("tr:nth-child(3) > td > div");
	private static final By SUBSCRIPTION_PACKS_GOLD_FR=By.cssSelector("tr:nth-child(4) > td > div");
	// avalanche
	public static final By PDP_SUB_HEADING = By.cssSelector("body > div.bat-wrapper > div > div > div.responsivegrid.rootTemplateGrid.aem-GridColumn.aem-GridColumn--default--12 > div > div > bat-producthero-avalanche > div > div.bat-producthero-configurator > div.bat-producthero-desktop > div.bat-headline.bat-producthero-configurator-subheading");
	public static final By CONFIGURABLE = By.cssSelector("div > select");
	public static final By AVALANCHE_PDP_PRODUCT_DESCRIPTION = By.cssSelector("#prodHero > div.bat-producthero-configurator > div.bat-producthero-desktop > div.bat-producthero-configurator-description.productDescription");
	private static final By AVALANCHE_VIEW_BASKET_BUTTON = By.cssSelector("a[href$='checkout/cart']");
	public static final By AVALANCHE_PDP_PRICE = By.cssSelector("body > div.bat-wrapper > div > div > div > div > div > bat-producthero-avalanche > div > div.bat-producthero-configurator > div.bat-producthero-configurator-bottom > div > span");
    public static final By STAR_RATING_BADGE=By.cssSelector("#ratingStarsContainer-98e3dadd90eb493088abdc5597a70810");
    public static final By PRODUCT_REVIEW_LIST=By.cssSelector("div.ts-reviews>ul>li");
    public static final By FDT_DEFERRED_PAYMENT_LINK= By.cssSelector("div.item-deferred-payment > span:nth-child(1)");
	public static final By ADD_TO_CART_SE=By.cssSelector("#product-addtocart-button");
	public static final By QUANTITY_SE=By.cssSelector("#qty");
	public static final By SUBSCRIPTION_OPTION_SE=By.cssSelector("div.tab-heads > div.input-wrapper.subs > label");
	public static final By ARTIST_BLOCK_CONTAINER_VUSEUK=By.cssSelector("div.artist-block-container");
	public static final By ARTIST_BLOCK_IMAGE_VUSEUK=By.cssSelector("img[data-pb-style='AYCHQBM']");
	public static final By ARTIST_BLOCK_ARTIST_NAME_VUSEUK=By.cssSelector("div[data-pb-style='HQDMSYL']");
	public static final By ARTIST_BLOCK_ARTIST_DESC_VUSEUK=By.cssSelector("div[data-pb-style='RVLCU29']");
	public static final By ARTIST_BLOCK_ARTIST_COLLECTION_TITLE_VUSEUK=By.cssSelector("div[data-pb-style='NVPUECU']");
	public static final By ARTIST_BLOCK_ARTIST_COLLECTION_DETAIL_VUSEUK=By.cssSelector("div[data-pb-style='HQDMSYL']");
	public static final By HEALTH_WARNING_PDP_LYFT=By.cssSelector("div.page-main-pagebuilder-attributes > div > div > div > div:nth-child(2) > div:nth-child(1) > div > div:nth-child(2) > div");

	//PL_Headful
	public static final By PDP_BRANDNAME_PL= By.cssSelector(".brand-name");
	public static final By PDP_PRODUCT_HEADING_PL= By.cssSelector(".heading");
	public static final By PDP_PRODUCT_SUBHEADING_PL= By.cssSelector(".sub-heading");
	public static final By PDP_PRODUCT_DESC_PL= By.cssSelector("div.product.attribute.overview");
	public static final By PDP_PRODUCT_STRENGTH_MILD_PL= By.cssSelector("div[role='listbox'] > div:nth-of-type(1)");
	public static final By PDP_PRODUCT_STRENGTH_MEDIUM_PL=By.cssSelector("div[role='listbox'] > div:nth-of-type(2)");
	public static final By PDP_QUANITY_BLOCK_PL=By.cssSelector(".field.qty");
	public static final By PDP_PRODUCT_PRICE_PL=By.cssSelector("#product-price-4893 > span");
	public static final By PDP_ADDTOCART_PL=By.cssSelector("button#product-addtocart-button");
	public static final By PDP_DELIVERYDETAILS_BLOCK_PL=By.cssSelector(".delivery-details-container");

	private String foreheadType;

	public void updateQuantity(String quantity) {
		waitForExpectedElement(qty).clear();
		waitForExpectedElement(qty).sendKeys(quantity);
	}

	public void selectStrength(By byProductAttribute) {
		waitForExpectedElement(byProductAttribute);
		List<WebElement> strength = getWebDriver().findElements(byProductAttribute);
		if (strength.size() > 0) {
			clickUsingJS(strength.get(0));
		}
	}

	public void selectNthStrength(By byProductAttribute, int index) {
		waitForExpectedElement(byProductAttribute);
		List<WebElement> strength = getWebDriver().findElements(byProductAttribute);
		if (strength.size() > index) {
			clickUsingJS(strength.get(index));
		}
	}

	public void selectProductColorStrengthFromList(By byProductAttribute) {
		// establish which (if any) attribute is displayged and then select and action
		try {
			if (UrlBuilder.getLocale().equalsIgnoreCase("mx") || UrlBuilder.getLocale().equalsIgnoreCase("co"))
				waitForExpectedElement(byProductAttribute, 10);
			else {
				if (!isElementPresent(addToCartButton))
				{
					refreshBrowser();
				}
				scrollToElement(addToCartButton);
				waitForExpectedElement(addToCartButton, 10);
			}
			if (getWebDriver().findElements(byProductAttribute).size() == 0) {
				if (UrlBuilder.getLocale().equalsIgnoreCase("pl") && UrlBuilder.isMobile()) {
					LOG.info("PL selecting for mobile:" + byProductAttribute.toString());
					Thread.sleep(2000);
					clickFirstElementByQueryJSExecutor(byProductAttribute);
				}
				LOG.info("Product's color or strength not available");
			} else if (UrlBuilder.getLocale().equalsIgnoreCase("vusefr") || UrlBuilder.getLocale().equalsIgnoreCase("vuseco" )) {
				if ((byProductAttribute.equals(btnProductStrength)) || byProductAttribute.equals(btnSwatchColor) || byProductAttribute.equals(SWATCH_COLOR_BUTTON) || getWebDriver().findElements(COLOURSWATCHCHECK).size()==0 || byProductAttribute.equals(SWATCH_COLOR_OR_STRENGTH_BUTTON) || byProductAttribute.equals(vuseVeloStrength)) {
					int defaultIndex=0;
					clickIndexElementByQueryJSExecutor(byProductAttribute,defaultIndex);
					if (UrlBuilder.getLocale().equalsIgnoreCase("vusefr")&&btnProductStrength.equals(byProductAttribute)){
						defaultIndex=1;
						clickIndexElementByQueryJSExecutor(byProductAttribute,defaultIndex);
					}
				}
				if (UrlBuilder.isFirefox()) {
					sleep(3000); //The page often flashes which causes unstable issue here, have to sleep
					scrollUpByCoordinator(150);
				}
			}
			else if(UrlBuilder.getLocale().equalsIgnoreCase("vuseuk" )){
				if ((byProductAttribute.equals(btnProductStrength)) || byProductAttribute.equals(btnSwatchColor) || byProductAttribute.equals(SWATCH_COLOR_BUTTON) || getWebDriver().findElements(COLOURSWATCHCHECK).size()==0 || byProductAttribute.equals(SWATCH_COLOR_OR_STRENGTH_BUTTON)) {
					int defaultIndex=0;
					if(!waitForExpectedElement(btnProductStrength)
							.getAttribute("class").contains("selected")||!waitForExpectedElement(btnSwatchColor)
							.getAttribute("class").contains("selected"))
						clickIndexElementByQueryJSExecutor(byProductAttribute,defaultIndex);
				}
			}
			else if (UrlBuilder.getLocale().equalsIgnoreCase("vuseza")){
				if ((byProductAttribute.equals(btnProductStrength)) || byProductAttribute.equals(btnSwatchColor) || byProductAttribute.equals(SWATCH_COLOR_BUTTON) || getWebDriver().findElements(COLOURSWATCHCHECK).size()==0 || byProductAttribute.equals(SWATCH_COLOR_OR_STRENGTH_BUTTON)) {
					int defaultIndex=1;
					clickIndexElementByQueryJSExecutor(byProductAttribute,defaultIndex);}
			}
			else {
				//If the color has already been selected
				scrollToPageTop();
				waitForExpectedElement(PDP_SELECTED_COLOR, 10);
				if (!(getWebDriver().findElements(PDP_SELECTED_COLOR).size() > 0)) {
					waitForExpectedElement(byProductAttribute, 10);
					scrollToPageTop();
					if(getWebDriver().findElements(PDP_SELECTED_STRENGTH_COLOR).size() == 0) {
						if(UrlBuilder.isIPhone())
							clickByElementByQueryJSExecutor(byProductAttribute);
						else
							clickFirstElementByQueryJSExecutor(byProductAttribute);
					}
					if (!(getWebDriver().findElements(PDP_SELECTED_STRENGTH_COLOR).size() > 0)) {
						assertTrue((waitForExpectedElement(PDP_SELECTED_STRENGTH_COLOR, 10).isDisplayed()), "Assertion fails and either product strength or colour is not selected");
					}
				}
			}
		} catch (Exception ex) {
			LOG.info("Failed to click select specified Product's attribute from the list due to exception: " + ex.getMessage());
		}
	}

	public void performSearch(String searchTerm) {
		if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
			switch (UrlBuilder.getLocale()) {
				case"velopl":
					waitForAjaxElementNotToBePresent(getWebDriver(),5);
					waitForExpectedElement(M_EXPAND_PRODUCT_SEARCH).click();
					webDriver.navigate().refresh();
					break;
				case "de":
				case "vusede":
				case "vypeit":
				case "dk":
					LOG.info("We are on the DK or DE SITE!!!!");
					if (UrlBuilder.isIpad()) {
						if (!UrlBuilder.getLocale().equalsIgnoreCase("vusede"))
							clickByElementByQueryJSExecutor(MINICART_CLOSE_BUTTON);
						waitForExpectedElement(M_EXPAND_SEARCH_INPUTBOX).click();
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).clear();
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).submit();
					} else {
						try {
							waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")), 10).click();
						} catch (Exception e) {
							LOG.info("Trying JS selector");
							clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRefITXpath.key")));
						}
						waitForExpectedElement(SEARCH_INPUTBOX, 20);
						enterDataAndWait(SEARCH_INPUTBOX, UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(SEARCH_INPUTBOX, 20).submit();
					}
					break;
				case "uk":
				case "vuseuk":
				case "vuseco":
					if (UrlBuilder.isIpad()) {
						if (getWebDriver().findElements(homePage.MINI_CART_OPEN_STATUS).size() > 0)
							homePage.waitForExpectedElement(homePage.MINICART_BACK_BUTTON).click();
						waitForExpectedElement(M_EXPAND_SEARCH_INPUTBOX,5);
						clickUsingJS(M_EXPAND_SEARCH_INPUTBOX);
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).clear();
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).submit();
					} else {
						enterDataAndWait(SEARCH_INPUTBOX_UK,UrlBuilder.getMessage(searchTerm));
						try{
							waitForExpectedElement(SEARCH_INPUTBOX_UK).submit();
							waitForPage();
						}catch(org.openqa.selenium.TimeoutException timeExp){
							LOG.info(timeExp.getMessage());
						}
						if("vuseuk".equalsIgnoreCase(UrlBuilder.getLocale())&&!doesURLContain("result/?")){
							enterDataAndWait(SEARCH_INPUTBOX_UK,UrlBuilder.getMessage(searchTerm));
							waitForExpectedElement(SEARCH_INPUTBOX_UK).sendKeys(Keys.ENTER);
						}
					}
					break;
				case "vusefr":
					if (UrlBuilder.isIpad()) {
						if (getWebDriver().findElements(homePage.MINI_CART_OPEN_STATUS).size() > 0)
							homePage.waitForExpectedElement(homePage.MINICART_BACK_BUTTON).click();
						waitForExpectedElement(M_EXPAND_SEARCH_INPUTBOX,5);
						clickUsingJS(M_EXPAND_SEARCH_INPUTBOX);
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).clear();
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).submit();
					} else {
						enterDataAndWait(SEARCH_INPUTBOX_UK,UrlBuilder.getMessage(searchTerm));
						try {
							waitForExpectedElement(SEARCH_INPUTBOX_UK, 20).submit();
							waitForPage();
							if(!getCurrentUrl().contains("result/?")){
								if(searchResult.isSearchSuggestionListPresent()) {
									LOG.info("Selected item from search suggestion list.");
									searchResult.selectSearchSuggestion(searchTerm);
								}
								else{
									LOG.info("Try to submit via sendKeys:Keys.ENTER.");
									waitForExpectedElement(SEARCH_INPUTBOX_UK).sendKeys(Keys.ENTER);
								}
							}
						} catch (Exception e) {
							LOG.info("Submit search unsuccessfully!");
							waitForExpectedElement(SEARCH_INPUTBOX_UK, 20).submit();
						}
						waitForPage();
					}
					break;
				case "ie":
				case "vuseit":
					if(UrlBuilder.isIpad()){
						clickByElementByQueryJSExecutor(M_EXPAND_SEARCH_INPUTBOX);
						waitForExpectedElement(SEARCH_INPUTBOX_IE, 10).clear();
						waitForExpectedElement(SEARCH_INPUTBOX_IE).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(SEARCH_INPUTBOX_IE).submit();
					}
					else {
						clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
						waitForExpectedElement(SEARCH_INPUTBOX_IE, 10).clear();
						waitForExpectedElement(SEARCH_INPUTBOX_IE).sendKeys(UrlBuilder.getMessage(searchTerm));
						try {
							waitForExpectedElement(SEARCH_INPUTBOX_IE, 20).submit();
							waitForPage();
							if(!getCurrentUrl().contains("result/?")){
								if(searchResult.isSearchSuggestionListPresent()) {
									LOG.info("Selected item from search suggestion list.");
									searchResult.selectSearchSuggestion(searchTerm);
								}
								else{
									LOG.info("Try to submit via sendKeys:Keys.ENTER.");
									waitForExpectedElement(SEARCH_INPUTBOX_IE).sendKeys(Keys.ENTER);
								}
							}
						} catch (Exception e) {
							LOG.info("Submit search unsuccessfully!");
							waitForExpectedElement(SEARCH_INPUTBOX_IE, 20).submit();
						}
						waitForPage();
					}
					break;
				case "mx":
				case "vusemx":
						if(UrlBuilder.isIpad()){
							waitForExpectedElement(M_EXPAND_SEARCH_INPUTBOX,5);
							clickUsingJS(M_EXPAND_SEARCH_INPUTBOX);
							clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
							waitForExpectedElement(SEARCH_INPUTBOX).clear();
							waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
							waitForExpectedElement(SEARCH_INPUTBOX).submit();
						}
						else{
							clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
							waitForExpectedElement(SEARCH_INPUTBOX, 20).clear();
							waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
							waitForExpectedElement(SEARCH_INPUTBOX).submit();
						}
					break;
				case "vuseza":
					if (UrlBuilder.isDesktop()){
						clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
						waitForExpectedElement(SEARCH_INPUTBOX, 20).clear();
						waitForExpectedElement(SEARCH_INPUTBOX,20).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(SEARCH_INPUTBOX).submit();
					} else if (webDriver.findElements(MINICART_CLOSE_BUTTON).size()>0) {
						waitForItemToBeClickableAndClick(MINICART_CLOSE_BUTTON,20);
						scrollToPageTop();
						waitForExpectedElement(M_EXPAND_SEARCH_INPUTBOX_DK).click();
						waitForExpectedElement(SEARCH_INPUTBOX).clear();
						waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(SEARCH_INPUTBOX).submit();
				}
					break;
				default:
					clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
					waitForExpectedElement(SEARCH_INPUTBOX, 20).clear();
					waitForExpectedElement(SEARCH_INPUTBOX,20).sendKeys(UrlBuilder.getMessage(searchTerm));
					waitForExpectedElement(SEARCH_INPUTBOX,20).submit();
			}
		} else {
			switch (UrlBuilder.getLocale()) {
				case "uk":
				case "vusede":
				case "vuseuk":
				case "fr":
				case "vusefr":
					if (UrlBuilder.getLocale().equalsIgnoreCase("vusede") ||
							UrlBuilder.getLocale().equalsIgnoreCase("vusefr")) {
						LOG.info("\n ****** At Vuse De - not closing side panel ");
						if (getWebDriver().findElements(homePage.MINI_CART_OPEN_STATUS).size() > 0)
							homePage.waitForExpectedElement(homePage.MINICART_BACK_BUTTON).click();
					}
					clickByElementByQueryJSExecutor(M_EXPAND_SEARCH_INPUTBOX);
					waitForExpectedElement(M_SEARCH_INPUTBOX_UK).clear();
					if(UrlBuilder.isMobile()&& UrlBuilder.getLocale().equalsIgnoreCase("vuseuk")){
						waitForExpectedElement(M_SEARCH_INPUTBOX_UK).sendKeys(UrlBuilder.getMessage("searchItemIphone.key"));
					}
					else waitForExpectedElement(M_SEARCH_INPUTBOX_UK).sendKeys(UrlBuilder.getMessage(searchTerm));
					waitForExpectedElement(M_SEARCH_INPUTBOX_UK).submit();
					break;
				case "de":
				case "dk":
				case "vusedk":
				case "vuseza":
					if (UrlBuilder.isIpad()) {
						clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
						waitForExpectedElement(SEARCH_INPUTBOX, 20).clear();
						waitForExpectedElement(SEARCH_INPUTBOX, 10).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(SEARCH_INPUTBOX, 10).sendKeys(Keys.ENTER);
					} else if (Props.getProp("siteMode").equalsIgnoreCase("tablet")) {
						waitForExpectedElement(T_SEARCH_FR).click();
						waitForExpectedElement(SEARCH_INPUTBOX).clear();
						waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
						waitForExpectedElement(SEARCH_INPUTBOX).submit();
					} else {
						if (webDriver.findElements(MINICART_CLOSE_BUTTON).size()>0) {
							if(isElementDisplayedBySeconds(M_MINICART_POPUP_VUSEZA,0)) {
								waitForItemToBeClickableAndClick(MINICART_CLOSE_BUTTON, 20);
							}
							scrollToPageTop();
							waitForExpectedElement(M_EXPAND_SEARCH_INPUTBOX_DK).click();
							waitForExpectedElement(SEARCH_INPUTBOX).clear();
							waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
							waitForExpectedElement(SEARCH_INPUTBOX).submit();
						}
					}
					break;
				case "mx":
				case "vusemx":
					waitForExpectedElement(M_EXPAND_SEARCH_INPUTBOX,5);
					clickUsingJS(M_EXPAND_SEARCH_INPUTBOX);
					clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
					waitForExpectedElement(SEARCH_INPUTBOX).clear();
					waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
					waitForExpectedElement(SEARCH_INPUTBOX).submit();
					break;
				case "vypeit":
				case "vuseco":
				case "vuseit":
					clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("m_searchIconRef.key")));
					waitForExpectedElement(SEARCH_INPUTBOX).clear();
					waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
					waitForExpectedElement(SEARCH_INPUTBOX).submit();
					break;
				default:
					waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")),10);
					try{
						clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
					}
					catch (Exception ex)
					{
						clickUsingJS(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")));
					}
					waitForExpectedElement(SEARCH_INPUTBOX,10).clear();
					waitForExpectedElement(SEARCH_INPUTBOX).sendKeys(UrlBuilder.getMessage(searchTerm));
					waitForExpectedElement(SEARCH_INPUTBOX).submit();
					break;
			}
			waitForElementToAppearAndDisappear(LOADER, 3, 5);
		}
	}

	public void clickOnAvisRatingsLink() {
		waitForItemToBeClickableAndClick(AVIS_RATINGS_LINK, 10);
	}

	public void userIsDirectedToReviewRatingsSection() {
		waitForExpectedElement(PDP_RATINGS_SECTION, 10).getAttribute("class").equals(getWebDriver().switchTo().activeElement().getAttribute("class"));
	}

	public void clickOnAvisRatingsIconAndVerifyAvisReviewsPopUp() {
		waitForExpectedElement(AVIS_RATINGS_ICON, 20);
		jsScrollElementInCenter(waitForExpectedElement(AVIS_RATINGS_ICON));
		clickByElementByQueryJSExecutor(AVIS_RATINGS_ICON);
		waitForExpectedElement(AVIS_RATINGS_HEADER, 4);
		assertTrue(waitForExpectedElement(AVIS_RATINGS_POPUP, 10).isDisplayed());
		clickFirstElementByQueryJSExecutor(AVIS_POPUP_CLOSE);
	}

	public void clickOnProductLinkWithStarRating(String strRating) {
		waitForExpectedElement(By.xpath("//*[contains(@class,'avis-rating-" + strRating + "')]//preceding::a[1]"), 30);
		clickByElementByQueryJSExecutor(By.xpath("//*[contains(@class,'avis-rating-" + strRating + "')]//preceding::a[1]"));
	}

	public void clickAddToCartButton() {
		if (UrlBuilder.isDesktop() || (UrlBuilder.isIpad())) {
			switch (UrlBuilder.getLocale()) {
				case "lyftse":
					try {
						waitForExpectedElement(ADDTOCART_BUTTON_LYFT, 10).click();
						waitForExpectedElement(basketQtyLyft, 10);
					} catch (Exception e) {
						clickByElementByQueryJSExecutor(ADDTOCART_BUTTON_LYFT);
					}
					break;
				case "dk":
				case "de":
				case "vusedk":
				case "vuseco":
				case "pl":
					waitForPage();
					try {
						waitForExpectedElement(PDP_ADDTOCART_BUTTON, 10).click();
						waitForExpectedElement(basketQty, 20);
					} catch (Exception e) {
						clickByElementByQueryJSExecutor(PDP_ADDTOCART_BUTTON);
					}
					break;
				case "ie":
				case "nl":
					try {
						waitForExpectedElement(addToCartButton, 10).click();
						waitForExpectedElement(basketQty, 10);
					} catch (Exception e) {
						clickByElementByQueryJSExecutor(ADD_TO_CART_BUTTON_VYPE_IE);
					}
					break;
				case "uk":
				case "vuseuk":
				case "vusefr":
					try {
						if (isElementPresent(STRENGTH_PDP) && getWebDriver().findElements(SELECTED_CBD_STRENGTH_PDP).size() == 0) {
							waitForExpectedElement(STRENGTH_PDP).click();
						}
						scrollToPageTop();
						waitForExpectedElement(ADD_TO_CART_BUTTON_VUSEUK, 10).click();
						waitForExpectedElement(basketQty, 20);
					} catch (Exception e) {
						if (WebDriverHelper.BROWSER.equalsIgnoreCase(PermittedBrowserMode.FIREFOX.toString())) {
							waitForExpectedElement(ADD_TO_CART_BUTTON_VUSEUK, 20);
						}
						if(getWebDriver().findElements(M_ADD_TO_CART_BTN_GLOIT).size()>0)
							waitAndClickByElementByJSExecutor(M_ADD_TO_CART_BTN_GLOIT,5);
						else
							clickByElementByQueryJSExecutor(ADD_TO_CART_BUTTON_VUSEUK);
					}
					break;
				case "vuseit":
					waitForExpectedElement(addToCartButton,10);
					clickFirstElementByQueryJSExecutor(addToCartButton);
					break;
				case "velobe":
				case "velopl":
				case "veloza":
					clickByElementByQueryJSExecutor(SELECTE_VELOPL_STRENGTH_PDP);
					clickUsingJS(ADD_TO_SHOPPING_CART_BUTTON);
					break;
				case "vusede":
					clickAddToCartCTA();
					waitForAjaxElementNotToBePresent(webDriver,10);
					if(getWebDriver().findElements(ERROR_MESSAGE_OUT_OF_STOCK).size()>0){
						try {
							clickIndexElementByQueryJSExecutor(SWATCH_COLOR_BUTTON, 0);
							homePage.setQtyAtPDPForVuseDE();
							clickAddToCartCTA();
						}catch(TimeoutException te){
							LOG.info("Failed to add to basket");
						}
					}
					break;
				default:
					try {
						if (UrlBuilder.isIpad()) {
							waitForExpectedElement(addToCartButton, 15);
							clickUsingJS(addToCartButton);
						} else {
							waitForExpectedElement(addToCartButton, 10).click();
							waitForExpectedElement(basketQty, 20);
						}
					} catch (Exception e) {
						if (WebDriverHelper.BROWSER.equalsIgnoreCase(PermittedBrowserMode.FIREFOX.toString())) {
							waitForExpectedElement(addToCartButton, 20);
						}
						clickByElementByQueryJSExecutor(addToCartButton);
					}
			}
		} else {
			switch (UrlBuilder.getLocale()) {
				case "de":
				case "dk":
				case "uk":
				case "fr":
				case "vusefr":
				case "vuseza":
					if(UrlBuilder.isIPhone() || UrlBuilder.isSamsungMobile()){
						waitForExpectedElement(PDP_ADDTOCART_BUTTON, 10);
						scrollToElement(PDP_ADDTOCART_BUTTON);
						waitAndClickByElementByJSExecutor(PDP_ADDTOCART_BUTTON,10);
						waitForExpectedElement(basketQtyLyft, 10);
					} else {
						clickUsingJS(M_ADDTOCART_BUTTON);
						waitForExpectedElement(basketQtyLyft, 10);
					}
					break;
				case "lyftse":
				case "ie":
					if(UrlBuilder.isIPhone()){
						waitForExpectedElement(HomePage.PLP_ADDTOCART_BUTTON, 10);
						clickUsingJS(HomePage.PLP_ADDTOCART_BUTTON);
						waitForExpectedElement(basketQtyLyft, 10);
					}
					else if(UrlBuilder.isSamsungMobile()){
						waitForExpectedElement(PDP_ADDTOCART_BUTTON, 10);
						scrollToElement(PDP_ADDTOCART_BUTTON);
						clickUsingJS(PDP_ADDTOCART_BUTTON);
						waitForExpectedElement(basketQtyLyft, 10);
					} else {
						clickUsingJS(M_ADDTOCART_BUTTON);
						waitForExpectedElement(basketQtyLyft, 10);
					}
					break;
				case "vusede":
				case "mx":
				case "vusemx":
					waitForExpectedElement(PDP_ADDTOCART_BUTTON, 10);
					scrollToElement(PDP_ADDTOCART_BUTTON);
					clickUsingJS(PDP_ADDTOCART_BUTTON);
					waitForExpectedElement(basketQtyLyft, 10);
					break;
				case "lyftdk":
				case "vusedk":
					if (UrlBuilder.isSamsungMobile()) {
						waitForExpectedElement(PDP_ADDTOCART_BUTTON, 10);
						clickByElementByQueryJSExecutor(PDP_ADDTOCART_BUTTON);
					} else if (UrlBuilder.isIPhone()) {
						waitForExpectedElement(PDP_ADDTOCART_BUTTON, 10);
						scrollToElement(addToCartButton);
						clickUsingJS(addToCartButton);
					} else {
						waitForExpectedElement(ADD_TO_CART_BUTTON_MOBILE, 10);
						clickByElementByQueryJSExecutor(ADD_TO_CART_BUTTON_MOBILE);
					}
					waitForExpectedElement(basketQty, 10);
					break;
				case "vuseuk":
				case "vypeit":
				case "vuseit":
				case "vuseco":
					try {
						waitForExpectedElement(ADDTOCART_BUTTON_LYFT, 10);
						clickByElementByQueryJSExecutor(ADDTOCART_BUTTON_LYFT);
					}catch(Exception e){
						clickByElementByQueryJSExecutor(PDP_ADDTOCART_BUTTON);
					}
					break;
				default:
					try {
						waitForExpectedElement(ADD_TO_CART_BUTTON_MOBILE, 10).click();
						waitForExpectedElement(basketQty, 10);
					} catch (Exception e) {
						clickByElementByQueryJSExecutor(ADD_TO_CART_BUTTON_MOBILE);
					}
			}
		}
	}

	public void getPDPProductPrice() {
		switch (UrlBuilder.getLocale()) {
			case "mx":
			case "vusemx":
			case "pl":
				AssertJUnit.assertTrue(waitForExpectedElement(MXproductPrice).isDisplayed());
				break;
			case "vypeit":
			case "vuseit":
				AssertJUnit.assertTrue(waitForExpectedElement(MXproductPrice).isDisplayed());
				break;
			case "lyftdk":
			case "lyftse":
				AssertJUnit.assertTrue(waitForExpectedElement(PRODUCT_PRICE_LYFT).isDisplayed());
				break;
			case "uk":
			case "vuseuk":
				AssertJUnit.assertTrue(waitForExpectedElement(PRODUCT_PRICE_VYPE).isDisplayed());
				break;
			default:
				LOG.info("Switch statement isn't configured for this locale - add to 'Assert PDP product prices please'");
				AssertJUnit.assertTrue(waitForExpectedElement(prodcutPrice).isDisplayed());
		}
	}

	public void getPDPTitle() {
		switch (UrlBuilder.getLocale()) {
			case "mx":
			case "vusemx":
				AssertJUnit.assertTrue(waitForExpectedElement(MXpdpProductTitle).isDisplayed());
				break;
			case "vypeit":
			case "vuseit":
				AssertJUnit.assertTrue(waitForExpectedElement(MXpdpProductTitle).isDisplayed());
				break;
			case "lyftdk":
			case "lyftse":
				AssertJUnit.assertTrue(waitForExpectedElement(PDP_PRODUCT_TITLE_LYFT).isDisplayed());
				break;
			case "uk":
			case "vuseuk":
				AssertJUnit.assertTrue(waitForExpectedElement(PDP_PRODUCT_TITLE_VYPE).isDisplayed());
				break;
			case "vusefr":
				AssertJUnit.assertTrue(isElementDisplayedBySeconds(PDP_PRODUCT_TITLE_FR, 10));
				AssertJUnit.assertTrue(isElementDisplayedBySeconds(PDP_PRODUCT_SUBTITLE_FR, 10));
				break;
			default:
				LOG.info("Switch statement isn't configured for this locale, using default - add to 'assert PDP product title'");
				AssertJUnit.assertTrue(waitForExpectedElement(pdpProductTitle).isDisplayed());
		}
	}

	public void clickSixPackLyftLabProduct() {
		clickFirstElementByQueryJSExecutor(LINK_SIXPACK_PRODUCT);
	}

	public void userIsRedirectedToPDPForSelectedProduct() {
		assertTrue(waitForExpectedElement(PDP_SELECTED_PRODUCT, 10).isDisplayed());
	}

	public void assertImageThumbnailOnTopLeftCornerOfThePDP() {
		assertTrue(waitForExpectedElement(PDP_IMAGE_THUMBNAILS, 10).isDisplayed());
	}

	public void selectImageThumbnailAndAssertProductMainImage() {
		waitForExpectedElement(PRODUCT_IMAGE_THUMBNAIL, 10);
		clickByElementByQueryJSExecutor(PRODUCT_IMAGE_THUMBNAIL);
		assertTrue(waitForExpectedElement(PRODUCT_IMAGE_WITH_SELECTED_THUMBNAIL, 10).isDisplayed());
	}

	public void assertImagesAppearsAsCarouselWithTrianglesBelowMainProduct() {
		assertTrue(waitForExpectedElement(MOBILE_PRODUCTIMAGE_CAROUSAL, 10).isDisplayed());
	}

	public void assertNoImageThumbnailsShowOnMobile() {
		assertTrue(getWebDriver().findElements(PDP_IMAGE_THUMBNAILS).size() == 0);
	}

	public void assertUserAbleToScrollThroughImagesThroughCarousel() {
		List<WebElement> lstElements = getWebDriver().findElements(MOBILE_PRODUCTIMAGE_CAROUSAL);
		for (WebElement ele : lstElements) {
			clickElementByQueryJSExecutor(ele);
			assertTrue(waitForExpectedElement(MOBILE_ACTIVE_IMAGE_CAROUSAL, 10).isDisplayed());
		}
	}

	public void verifySingleLABPackQtyDropDownOptionsOnPDP(String strCount) {
		verifyCountOfDropDownOptions(PlpPage.QTY_DROPDOWN_PDP, UrlBuilder.getMessage(strCount));
	}

	public void verifyThreeLABPackQtyDropDownOptionsOnPDP(String strCount) {
		verifyCountOfDropDownOptions(PlpPage.QTY_DROPDOWN_PDP, UrlBuilder.getMessage(strCount));
	}

	public void verifySixLABPackQtyDropDownOptionsOnPDP(String strCount) {
		verifyCountOfDropDownOptions(PlpPage.QTY_DROPDOWN_PDP, UrlBuilder.getMessage(strCount));
	}

	public void verifyLABProductsQtyDropDownOptionsOnPLP(String strCount) {
		verifyCountOfDropDownOptions(PlpPage.QTY_SINGLEPRODUCT_DROPDOWN, UrlBuilder.getMessage(strCount));
		verifyCountOfDropDownOptions(PlpPage.QTY_THREEPACK_DROPDOWN, UrlBuilder.getMessage(strCount));
		verifyCountOfDropDownOptions(PlpPage.QTY_SIXPACK_DROPDOWN, UrlBuilder.getMessage(strCount));
	}

	public void userSelectsFromQuantityDropDownForLABProduct(String strQuantity) {
		selectValueFromDropDownByWebElement(waitForExpectedElement(PlpPage.QTY_SINGLEPRODUCT_DROPDOWN), UrlBuilder.getMessage(strQuantity));
	}

	public void userSelectsFromStrengthDropDownForLABProduct(String strStrength) {
		selectValueFromDropDownByWebElement(waitForExpectedElement(PlpPage.STRENGTH_SINGLEPRODUCT_PDP), UrlBuilder.getMessage(strStrength));
	}

	public void clickFirstLyftLabProductFromPack(By by) {
		clickFirstElementByQueryJSExecutor(by);
	}

	public void clickThreePackLyftLabProduct() {
		clickFirstLyftLabProductFromPack(LINK_THREEPACK_PRODUCT);
	}


	public void assertRelatedAndUpSellProductSectionsConfiguration() {
		waitForExpectedElement(productPage.RELATED_UPSELL_CROSSSELL_SECTION, 30);
		clickByElementByQueryJSExecutor(productPage.RELATED_UPSELL_CROSSSELL_SECTION);
		if (getWebDriver().findElements(productPage.RELATED_PRODUCT_ROWS).size() == 1)
			blnRelatedProducts = true;
		if (getWebDriver().findElements(productPage.UPSELL_PRODUCT_ROWS).size() == 1)
			blnUpSellProducts = true;
		if (getWebDriver().findElements(productPage.CROSSSELL_PRODUCT_ROWS).size() == 1)
			blnCrossSellProducts = true;
		else {
			LOG.info("Related Products, Up-Sells, and Cross-Sells sections not configured in Backend");
		}
	}

	public void assertRelatedProductsSectionAndCarousalOnPDP() {
		if (blnRelatedProducts)
			assertTrue(waitForExpectedElement(RELATED_PRODUCTS_SECTION, 10).isDisplayed());
		assertRelatedProductsCarouselAndCTA();
	}

	public void assertUpSellProductsSectionAndCarousalOnPDP() {
		if (blnUpSellProducts)
			assertTrue(waitForExpectedElement(UPSELL_PRODUCTS_SECTION, 10).isDisplayed());
		assertUpSellProductsCarouselAndCTA();
	}

	public void selectProductFromRelatedBlockAndAssertAddToCartButton() {
		if (blnRelatedProducts) {
			switch (UrlBuilder.getLocale()){
				case "glode":
					assertTrue(waitForExpectedElement(addToCartButton, 10).isDisplayed());
					break;
				default:
					clickFirstLyftLabProductFromPack(RELATED_PRODUCTS_FIRST_ITEM_LINK);
					assertTrue(waitForExpectedElement(addToCartButton, 10).isDisplayed());
			}
		}
	}


	public void selectProductFromUpSellBlockAndAssertAddToCartButton() {
		if (blnUpSellProducts) {
			clickFirstLyftLabProductFromPack(UPSELL_PRODUCTS_FIRST_ITEM_LINK);
			assertTrue(waitForExpectedElement(addToCartButton, 10).isDisplayed());
		}
	}

	public void assertRelatedProductsCarouselAndCTA() {
		if (getWebDriver().findElements(RELATED_PRODUCTS_FIRST_ITEM_LINK).size() > 4) {
			if (waitForExpectedElement(homePage.CAROUSEL_ARROW_NEXT).isDisplayed() || waitForExpectedElement(homePage.CAROUSEL_ARROW_PREV).isDisplayed())
				;
			homePage.verifyCarouselFunctionalityOnLeftRightArrowCTAs(CAROUSEL_NAVIGATION_RELATED_PRODUCTS, homePage.CAROUSEL_ARROW_NEXT, homePage.CAROUSEL_ARROW_PREV);
		} else
			LOG.info("Products Section has less than 4 items so carousel doesn't appear.");
	}

	public void assertUpSellProductsCarouselAndCTA() {
		if (getWebDriver().findElements(UPSELL_PRODUCTS_FIRST_ITEM_LINK).size() > 4) {
			assertTrue(waitForExpectedElement(homePage.CAROUSEL_ARROW_NEXT).isDisplayed() || waitForExpectedElement(homePage.CAROUSEL_ARROW_PREV).isDisplayed());
			homePage.verifyCarouselFunctionalityOnLeftRightArrowCTAs(CAROUSEL_NAVIGATION_UPSELL_PRODUCTS, UPSELL_CAROUSEL_ARROW_NEXT, UPSELL_CAROUSEL_ARROW_PREV);
		}
	}

	public void assertCrossSellProductsSectionOnMiniCart() {
		assertTrue(waitForExpectedElement(CROSSELL_PRODUCTS_SECTION, 10).isDisplayed());
	}

	public void assertCrossSellProductsCarouselAndCTA() {
		waitForAjaxElementNotToBePresent(getWebDriver(), 10);
		if (getWebDriver().findElements(CROSSELL_PRODUCTS_ITEMS).size() > 4) {
			assertTrue(waitForExpectedElement(homePage.CAROUSEL_ARROW_NEXT, 10).isDisplayed() || waitForExpectedElement(homePage.CAROUSEL_ARROW_PREV, 10).isDisplayed());
			homePage.verifyCarouselFunctionalityOnLeftRightArrowCTAs(CAROUSEL_NAVIGATION_CROSSELL_PRODUCTS, homePage.CAROUSEL_ARROW_NEXT, homePage.CAROUSEL_ARROW_PREV);
		}
	}

	public void assertAddToCartButtonForCrossSellProductsAndAssertCTA() throws Throwable {
		if (blnCrossSellProducts) {
			int intAddToCartBtnCount = 0;
			int intCrossSellItems = getWebDriver().findElements(CROSSELL_PRODUCTS_ITEMS).size();
			switch (UrlBuilder.getLocale()) {
				case "lyftse":
				case "glode":
					intAddToCartBtnCount = getWebDriver().findElements(ADDTOCART_BUTTON_LYFT).size();
					break;
				case "uk":
				case "vuseuk":
					intAddToCartBtnCount = getWebDriver().findElements(CROSSELL_PRODUCTS_ADDTOCART).size();
					break;
				default:
			}
			assertTrue(intCrossSellItems >= intAddToCartBtnCount);
			clickOnAddToCartButtonForCrossSellProduct();
			homePage.confirmMiniBasketDisplayedAmountOf("2");
		}
	}


	public void assertCrossSellProductsSectionAndCarousalOnCartPage() {
		if (blnCrossSellProducts) {
			assertCrossSellProductsSectionOnMiniCart();
			assertCrossSellProductsCarouselAndCTA();
		}
	}

	public void clickOnFirstLinkIfNavigatedToPLP() throws Throwable {
		if (getCurrentPageTitle().contains("Search results")) {
			searchResult.selectSearchResult();
		}
	}

	public void clickOnFirstLinkIfNavigatedToPLPAndAddToCart() throws Throwable {
		clickOnFirstLinkIfNavigatedToPLP();
		clickAddToCartButton();
		homePage.clickOnBasketIcon();
	}

	public void selectProductStrengthOrColour() {
		switch (UrlBuilder.getLocale()) {
			case "fr":
			case "vuseza":
				selectProductColorStrengthFromList(SWATCH_COLOR_OR_STRENGTH_BUTTON);
				break;
			case "uk":
			case "vuseuk":
			case "ie":
			case "vypeit":
			case "vuseit":
			case "vuseco":
			case "vusefr":
				selectProductColorStrengthFromList(btnProductStrength);
				selectProductColorStrengthFromList(SWATCH_COLOR_BUTTON);
				break;
            case "mx":
			case "vusemx":
				selectProductColorStrengthFromList(btnProductStrength);
            	selectProductColorStrengthFromList(btnSwatchColor);
			    break;
			case "vusede":
				selectProductColorStrengthFromList(btnProductStrength);
				selectProductColorStrengthFromList(SWATCH_COLOR_BUTTON);
				// This is in place as Vuse DE does not have a Qty by default
				homePage.setQtyAtPDPForVuseDE();
				break;
			case "kz":
				clickByElementByQueryJSExecutor(btnSwatchColor);
				break;
			default:
				selectProductColorStrengthFromList(btnSwatchColor);
		}
	}

	public void clickOnAddToCartButtonForCrossSellProduct() {
		switch (UrlBuilder.getLocale()) {
			case "lyftse":
			case "glode":
				waitForAjaxElementNotToBePresent(getWebDriver(), 10);
				waitForExpectedElement(ADDTOCART_BUTTON_LYFT, 10);
				clickFirstElementByQueryJSExecutor(ADDTOCART_BUTTON_LYFT);
				break;
			case "uk":
			case "vuseuk":
				selectProductStrengthOrColour();
				clickFirstElementByQueryJSExecutor(CROSSELL_PRODUCTS_ADDTOCART);
				break;
			default:
		}
	}

	public void emptyBasketBeforeAddingRelatedProducts() {
		switch (UrlBuilder.getLocale()) {
			case "lyftse":
				homePage.emptyBasketLyftLab();
				break;
			case "uk":
			case "vuseuk":
			case "velobe":
			case "velopl":
			case "veloza":
				homePage.avalancheEmptyBasket();
				break;
			default:
		}
	}

	public void assertRelatedUpSellProductsFromBackend(String strProduct, String strStoreView) throws Throwable {
		productPage.performBackEndStepsToAssertRelatedUpSellProductsConfiguration(strProduct, strStoreView);
		assertRelatedAndUpSellProductSectionsConfiguration();
	}

	public void loginMagentoAdminAndAssertRelatedUpSellProductsConfiguration() throws Throwable {
		adminLoginPage.userLoginsMagentoAdminHomePageSuccessfully();
		if (UrlBuilder.getLocale().equalsIgnoreCase("uk"))
			assertRelatedUpSellProductsFromBackend(UrlBuilder.getMessage("relatedUpSellProducts.key"), "English");
		if (UrlBuilder.getLocale().equalsIgnoreCase("lyftse"))
			assertRelatedUpSellProductsFromBackend(UrlBuilder.getMessage("relatedUpSellProducts.key"), "Swedish");
		if (UrlBuilder.getLocale().equalsIgnoreCase("glode"))
			assertRelatedUpSellProductsFromBackend(UrlBuilder.getMessage("relatedUpSellProducts.key"), "German");
	}

	public void assertPersonalizeableServiceOnPDP() {
		assertTrue((waitForExpectedElement(PRODUCT_PERSONALIZABLE, 10).isDisplayed()), "Personlize serivce isn't available on the selected product");
	}

	public void assertPersonalizeTabOption() {
		assertTrue((waitForExpectedElement(PRODUCT_PERSONALIZABLE_TAB, 10).isDisplayed()), "Personlize tab isn't available on the selected product");
	}

	public void assertPersonalizeServiceOptions() {
		assertTrue((waitForExpectedElement(PRODUCT_PERSONALIZABLE_OPTIONS, 10).isDisplayed()), "Personlize serivce isn't available on the selected product");
	}

	public void assertLinkOfBackOnlyPersonalization() {
		assertTrue((waitForExpectedElement(LINK_PERSONALIZABLE_BACK_ONLY, 10).isDisplayed()), "Personlize serivce isn't available on the selected product");
	}

	public void assertCancelationLinkOnPersonalization() {
		switch (UrlBuilder.getLocale()) {
			case "fr":
			case "vusefr":
				assertTrue((waitForExpectedElement(LINK_CANCEL_PERSONALIZABLE_SERVICE_UXRESET, 10).isDisplayed()), "Personlize serivce isn't available on the selected product");
				break;
			default:
				assertTrue((waitForExpectedElement(LINK_CANCEL_PERSONALIZABLE_SERVICE, 10).isDisplayed()), "Personlize serivce isn't available on the selected product");
		}
	}

	public void assertCancelButtonLinkOnPersonalization() {
		assertTrue((waitForExpectedElement(LINK_CANCEL_PERSONALIZABLE_SERVICE_UXRESET, 10).isDisplayed()), "Personlize serivce isn't available on the selected product");
	}

	public void clickToOptPersonalizeService() {
		waitForAjaxElementNotToBePresent(getWebDriver(),35);
		wait.until(ExpectedConditions.elementToBeClickable(COLOR_SWATCH_DIV));
		scrollToElement(BUTTON_PERSONALIZE_ADD);
		try{waitForItemToBeClickableAndClick(BUTTON_PERSONALIZE_ADD,15);}
		catch(Exception ex){
			waitAndClickByElementByJSExecutor(BUTTON_PERSONALIZE_ADD,4);
		}
	}

	public void clickToSkipFrontPersonalizeService() {
		waitForExpectedElement(SKIP_FRONT_PERSONALIZE).click();
	}

	public void clickOnHorizentalTabOfEngraving() {
		waitForExpectedElement(FRONT_PERSONALIZE_HORIZENTAL_TEXT_TAB).click();
	}

	public void userAssertErrorValidationMessageIsDisplayed() {
		assertTrue(waitForExpectedElement(ENGRAVING_ERROR_VALIDATION_MESSAGE).getCssValue("display").equalsIgnoreCase("block"));
	}

	public void clickToSkipBackPersonalizeService() {
		waitForExpectedElement(SKIP_BACK_PERSONALIZE).click();
	}

	public void verifyBackButton(String optionToVerify) {
		switch (optionToVerify.toLowerCase()) {
			case "pattern":
				assertTrue(waitForExpectedElement(PATTERN_BACK_BUTTON_PERSONALIZE).isDisplayed());
				break;
			case "icon":
				assertTrue(waitForExpectedElement(ICON_BACK_BUTTON_PERSONALIZE).isDisplayed());
				break;
			case "text":
				assertTrue(waitForExpectedElement(TEXT_BACK_BUTTON_PERSONALIZE).isDisplayed());
				break;
		}
	}

	public void assertCancelButtonIsDisplayed(String page) {
		switch (page) {
			case "frontTab.key":
				assertTrue(waitForExpectedElement(CANCEL_BUTTON_FRONTSIDE_ENGRAVING).isDisplayed());
				break;
			case "backTab.Key":
				assertTrue(waitForExpectedElement(CANCEL_BUTTON_BACKSIDE_ENGRAVING).isDisplayed());
				break;
			case "summaryTab.Key":
				assertTrue(waitForExpectedElement(CANCEL_BUTTON_SUMMARY_ENGRAVING).isDisplayed());
				break;
		}
	}

	public void assertBackButtonIsDisplayedOnBackTab() {
		assertTrue(waitForExpectedElement(PERSONALIZE_BACK_TAB_BACK_BUTTON).isDisplayed());
	}

	public void assertBackButtonIsDisplayedOnSummaryTab() {
		assertTrue(waitForExpectedElement(PERSONALIZE_SUMMARY_TAB_BACK_BUTTON).isDisplayed());
	}

	public void assertColorSwatchIsDisplayed() {
		assertTrue(waitForExpectedElement(PERSONALIZE_COLOR_SWATCH_OPTIONS).isDisplayed());
	}

	public void assertPDPPageDeliveryMessageIsDisplayed() {
		assertTrue(waitForExpectedElement(BUTTON_PERSONALIZE_ADD).isDisplayed());
		//assertTrue(waitForExpectedElement(PDP_DELIVERY_MESSAGE).isDisplayed());
	}

	public void userClicksOnCancelButton(String page) {
		switch (page) {
			case "frontTab.key":
				clickByElementByQueryJSExecutor(CANCEL_BUTTON_FRONTSIDE_ENGRAVING);
				break;
			case "backTab.Key":
				clickByElementByQueryJSExecutor(CANCEL_BUTTON_BACKSIDE_ENGRAVING);
				break;
			case "summaryTab.Key":
				clickByElementByQueryJSExecutor(CANCEL_BUTTON_SUMMARY_ENGRAVING);
				break;
		}
	}

	public void clickToPattern() {
		foreheadType=waitForExpectedElement(PERSONALIZE_SERVICE_PATTERNS).getText();
		foreheadType=upperCase(foreheadType.substring(0, 1)) + lowerCase(foreheadType.substring(1));
		waitForExpectedElement(PERSONALIZE_SERVICE_PATTERNS).click();
	}

	public void clickToFirstPatternOrIconImage() {
		try {
			List<WebElement> numberOfImages = getWebDriver().findElements((NUMBEROF_PERSONALIZE_PATTERN_OR_ICON_IMAGES));
			if (numberOfImages.size() > 0) {
				numberOfImages.get(0).click();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.info("Pattern or Icon Images are not present " + e);
		}
	}

	public void clickOnFirstPatternOrIconImage() {
		try {
			List<WebElement> numberOfImages = getWebDriver().findElements((NUMBER_OF_PERSONALIZE_PATTERN_OR_ICON_IMAGES));
			if (numberOfImages.size() > 0) {
				numberOfImages.get(0).click();
				EngravingAttribute engravingAttribute = EngravingAttribute.builder().name(foreheadType)
						.value(numberOfImages.get(0).getText())
						.build();
				engravingAttributeList.add(engravingAttribute);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.info("Pattern or Icon Images are not present " + e);
		}
	}

	public void editPatternOrIconImage() {
		try {
			List<WebElement> numberOfImages = getWebDriver().findElements((NUMBEROF_PERSONALIZE_PATTERN_OR_ICON_IMAGES));
			if (numberOfImages.size() == 1) {
				numberOfImages.get(0).click(); //since no other choice
			} else if (numberOfImages.size() > 1) {
				Random rand = new Random();
				int randomNumber = rand.nextInt(numberOfImages.size()) + 1;
				clickUsingJS(numberOfImages.get(randomNumber));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.info("Pattern or Icon Images are not present " + e);
		}
	}

	public boolean getAssertionPersonalizePatternImageOnDevice() {
		return waitForExpectedElement(ASSERTION_PERSONALIZE_PATTERN_IMAGE_ON_DEVICE).isDisplayed();
	}

	public void clickToIcons() {
		foreheadType=UrlBuilder.getMessage("engravingMiniIcon.key");
		waitForExpectedElement(PERSONALIZE_SERVICE_ICONS).click();
	}

	public void clickToText() {
		foreheadType=waitForExpectedElement(PERSONALIZE_SERVICE_TEXTS).getText();
		waitForExpectedElement(PERSONALIZE_SERVICE_TEXTS).click();
	}

	public void clickOnAddAndContinueButton(String selectedOption) {
		if (!selectedOption.equalsIgnoreCase("back")) {
			switch (selectedOption.toLowerCase()) {
				case "pattern":
					//scrollElementIntoView(BUTTON_ADD_AND_CONTINUE_PATTERN_FRONT);
					clickByElementByQueryJSExecutor(BUTTON_ADD_AND_CONTINUE_PATTERN_FRONT);
					break;
				case "icon":
					//	scrollElementIntoView(BUTTON_ADD_AND_CONTINUE_ICONS_FRONT);
					clickByElementByQueryJSExecutor(BUTTON_ADD_AND_CONTINUE_ICONS_FRONT);
					break;
				case "text":
					//	scrollElementIntoView(BUTTON_ADD_AND_CONTINUE_TEXT_FRONT);
					clickByElementByQueryJSExecutor(BUTTON_ADD_AND_CONTINUE_TEXT_FRONT);
					break;
			}
		} else if (selectedOption.equalsIgnoreCase("back")) {
			scrollElementIntoView(BUTTON_ADD_AND_CONTINUE_BACK);
			clickUsingJS(BUTTON_ADD_AND_CONTINUE_BACK);
		} else {
			LOG.info("Chosen side of the device is not correct");
		}
	}

	public void addTextOnTheDevice(String whichSide, String alignment) {
		if (whichSide.equalsIgnoreCase("back") && alignment.equalsIgnoreCase("vertical")) {
			waitForExpectedElement(PERSONALIZE_SERVICE_TEXT_AT_BACK).sendKeys("BACK");
			engravingAttributeList.add(EngravingAttribute.builder().name(UrlBuilder.getMessage("engravingText.key"))
					.value("BACK")
					.build());
			engravingAttributeList.add(EngravingAttribute.builder().name(UrlBuilder.getMessage("engravingTextDerection.key"))
					.value(waitForExpectedElement(PERSONALIZE_ORIENTATTION_ACTIVE_RADIO_BACK).getText())
					.build());
		} else if (whichSide.equalsIgnoreCase("front") && alignment.equalsIgnoreCase("vertical")) {
			waitForExpectedElement(PERSONALIZE_SERVICE_TEXT_AT_FRONT).sendKeys("FRONT");
			engravingAttributeList.add(EngravingAttribute.builder().name(UrlBuilder.getMessage("engravingText.key"))
					.value("FRONT")
					.build());
			engravingAttributeList.add(EngravingAttribute.builder().name(UrlBuilder.getMessage("engravingTextDerection.key"))
					.value(waitForExpectedElement(PERSONALIZE_ORIENTATTION_ACTIVE_RADIO_FRONT).getText())
					.build());
		}

	}

	public void addTextOnTheDevice(String restrictedWord) {
		waitForExpectedElement(PERSONALIZE_SERVICE_TEXT_AT_FRONT).sendKeys(restrictedWord);
	}

	public void clearTextOnTheInputFieldInEngraving() {
		waitForExpectedElement(PERSONALIZE_SERVICE_TEXT_AT_FRONT).clear();
	}

	public double getDefaultProductPrice() {
		String defaultProductPrice = getWebDriver().findElement(PRODUCT_PRICE_WITHOUT_PERSONALIZE_SERVICE).getAttribute("content");
		productDefaultPrice = Double.parseDouble(defaultProductPrice);
		return productDefaultPrice;
	}


	public double getEngravingPrice() {
		double enravedServicePrice = 0;
		if (getWebDriver().findElement(PERSONALIZE_SERVICE_SPECIAL_PRICE).isDisplayed()) {
			String[] specialEngravingServicePrice = getWebDriver()
					.findElement(PERSONALIZE_SERVICE_SPECIAL_PRICE)
					.getText().split("€");
			specialServicePrice = Double
					.parseDouble(specialEngravingServicePrice[0].substring(2, 7).replace(",", "."));
			enravedServicePrice = specialServicePrice;
		} else if (getWebDriver().findElement(PERSONALIZE_SERVICE_CUSTOME_PRICE).isDisplayed()) {
			String[] customEngravingPrice = getWebDriver().findElement(PERSONALIZE_SERVICE_CUSTOME_PRICE)
					.getText().split("€");
			customServicePrice = Double
					.parseDouble(customEngravingPrice[0].substring(2, 7).replace(",", "."));
			enravedServicePrice = customServicePrice;
		}

		return enravedServicePrice;
	}

	public double totalPriceWithChosenEngravingService() {
		double dfp = getDefaultProductPrice();
		double gep = getEngravingPrice();
		double totalOfProductAndEngravingService = dfp + gep;
		return totalOfProductAndEngravingService;
	}

	public Double getDisplayedTotalPriceWithEngraving() {
		String totalOfProductAndEngravingService = getWebDriver().findElement(PRODUCT_TOTAL_PRICE_INCLUDING_ENGRAVING).getText();
		Pattern pattern = Pattern.compile("([0-9.,]+)");
		Matcher matcher = pattern.matcher(totalOfProductAndEngravingService);
		LOG.info(matcher.group(1));
		return Double.parseDouble(matcher.group(1));
	}

	public Double productOnlyPriceInPriceBreakDownPopUp() {
		String productOnlyPriceInPriceBreakDownPopUp = getWebDriver().findElement(PRODUCT_ONLY_PRICE_EXCLUDING_ENGRAVING_IN_PRICEBREAKDOWN_POPUP).getText();
		Pattern pattern = Pattern.compile("([0-9.,]+)");
		Matcher matcher = pattern.matcher(productOnlyPriceInPriceBreakDownPopUp);
		LOG.info(matcher.group(1));
		return Double.parseDouble(matcher.group(1));
	}

	public Double engravingOnlyPriceInPriceBreakDownPopUp() {
		String engravingOnlyPriceInPriceBreakDownPopUp = getWebDriver().findElement(ENGRAVING_ONLY_PRICE_IN_PRICEBREAKDOWN_POPUP).getText();
		Pattern pattern = Pattern.compile("([0-9.,]+)");
		Matcher matcher = pattern.matcher(engravingOnlyPriceInPriceBreakDownPopUp);
		LOG.info(matcher.group(1));
		return Double.parseDouble(matcher.group(1));
	}

	public double summaryPrice() {
		String[] summaryPrice = getWebDriver().findElement(PLP_ENGRAVING_PRODUCT_SUMMARY_PRICE).getText().split("€");
		Double sep = Double.parseDouble(summaryPrice[0]);
		return sep;
	}

	public void userClicksOnEditFrontEndChoice() {
		waitForExpectedElement(MODIFY_FRONT_SUMMARY).isEnabled();
		clickByElement(MODIFY_FRONT_SUMMARY);
	}

	public void defaultPriceNoticeNotDisplayed() {
		assertFalse(waitForExpectedElement(DEFAULT_PRICE_NOTICE).isEnabled());
	}

	public void defaultPriceNoticeDisplayed() {
		assertTrue(waitForExpectedElement(DEFAULT_PRICE_NOTICE).isDisplayed());
	}

	public void VerifyAddToCartCTAIsDisabled() {
		assertTrue(waitForExpectedElement(PDP_ADDTOCART_BUTTON_DISABLED).isDisplayed());
	}
	public void removeBothFrontAndBackEndChoice() {
		clickByElementByQueryJSExecutor(REMOVE_FRONT_END_CHOICE);
		clickByElementByQueryJSExecutor(REMOVE_BACK_END_CHOICE);
	}

	public void engravingDetailsConfirmationOnminiBasket() {
		//Front side details
		waitForExpectedElement(FRONT_ENGRAVING_DETAILS).isDisplayed();
		String chosenFrontEndOptionText = webDriver.findElement(FRONT_ENGRAVING_DETAILS).getText();
		List<WebElement> list = webDriver.findElements(MINICART_FRONT_ENGRAVING_DETAILS);
		assertTrue(chosenFrontEndOptionText.trim().equals(list.get(0).getText().trim()));
		//Back side details
		String chosenBackEndOptionText = webDriver.findElement(BACK_ENGRAVING_DETAILS).getText();
		assertTrue(chosenBackEndOptionText.trim().equals(list.get(1).getText().trim()));

	}

	public void engravingDetailsPresenceConfirmationOnminiBasket() {
		assertTrue(waitForExpectedElement(MINICART_ENGRAVING_DETAILS).isDisplayed());
	}

	public void assertPDPPersonalisationPanelIsDisplayed() {
		assertTrue(waitForExpectedElement(PDP_ENGRAVING_PERSONALISATION_PANEL).isDisplayed());
	}

	public void assertPDPPersonalisationPanelIconTollTipIsDisplayed() {
		switch (UrlBuilder.getLocale()) {
			case "vuseco":
			case "mx":
				break;
			default:
				scrollElementIntoView(homePage.PERSON_ICON);
				assertTrue(waitForExpectedElement(PDP_ENGRAVING_PERSONALISATION_ICON_TOOLTIP).isDisplayed());
		}
	}

	public void userClicksOnPDPPersonalisationPanelIcon() {
		scrollToPageTop();
		waitForExpectedElement(PDP_ENGRAVING_PERSONALISATION_ICON_TOOLTIP).click();
	}

	public void assertInternalRedirectOfPDPlinksWithStatusCode() {
		assertTrue(getCurrentUrl().endsWith("/"));
		assertTrue(restAssuredReturnStatusCode() == 200);
	}

	public void eyesCheckPdpPage() {
		if (Props.EYES_ON && EyesCheckpoints.PDP.isSwitchOn()) {
			scrollToShowEntirePage();
			final String checkpointName = EyesCheckpoints.PDP.getName();
			switch (UrlBuilder.getLocale()) {
				case "vusedk":
					if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
						eyes.check(checkpointName, Target.window().fully());
					} else { // mobile
						eyes.check(checkpointName, Target.window().fully().ignore(HomePage.SLICK_LIST));
					}
					break;
				case "vuseza":
				case "lyftse":
					eyes.check(checkpointName, Target.window().fully());
					break;
				case "lyftdk":
					eyes.check(checkpointName, Target.window().fully().layout(REVIEW_NICKNAME));
					break;
				case "vusede":
					if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
						eyes.check(checkpointName, Target.window().fully().ignore(RELATED_PRODUCTS));
					} else {
						eyes.check(checkpointName, Target.window().fully()
								.ignore(REVIEW_LIST)
								.ignoreDisplacements());
					}
					break;
				case "glode":
					if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
						eyes.check(checkpointName, Target.window().fully()
								.ignore(RELATED_PRODUCTS)
								.ignore(REVIEW_LIST, 0, 0, 50, 0)
								.ignoreDisplacements());
					} else {
						eyes.check(checkpointName, Target.window().fully()
								.ignore(HomePage.MESSAGE_ROW)
								.ignore(RELATED_PRODUCTS)
								.ignore(REVIEW_LIST, 0, 0, 50, 0)
								.ignoreDisplacements());
					}
					break;
				case "pl":
				case "kz":
					if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
						eyes.check(checkpointName, Target.window().fully().ignore(
								RELATED_PRODUCTS,
								HomePage.HEADER_TOP));
					} else {
						eyes.check(checkpointName, Target.window().fully().ignore(RELATED_PRODUCTS));
					}
					break;
				case "uk":
					if (isElementPresent(By.cssSelector(".vype-epod-feature-block"))) {
						// bring the images to the front layer
						execJsScript("document.querySelectorAll('.vype-epod-feature-block')[0].style.zIndex=''");
						execJsScript("document.querySelectorAll('.vype-epod-feature-block')[0].style.backgroundImage=''");
						execJsScript("document.querySelectorAll('.vype-epod-feature-block')[1].style.zIndex=''");
						execJsScript("document.querySelectorAll('.vype-epod-feature-block')[1].style.backgroundImage=''");
						sleepFor(2000);
					}
					eyes.check(checkpointName, Target.window().fully().ignore(RELATED_PRODUCTS, PDP_VIDEO));
					break;
				case "vuseit":
				case "vusefr":
					if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
						eyes.check(checkpointName, Target.window().fully().ignore(RELATED_PRODUCTS));
					} else { // mobile
						eyes.check(checkpointName, Target.window().fully()
								.ignore(RELATED_PRODUCTS,
										HomePage.MESSAGE_ROW));
					}
					break;
				default:
					eyes.check(checkpointName, Target.window().fully().ignore(RELATED_PRODUCTS));
			}
		}
	}


	public void userClicksOnFindOutMoreLinkForTofinoProduct() {
		clickFirstElementByQueryJSExecutor(FIND_OUT_MORE_OPTION);
	}

	public void assertInvalidPostCodeMessageIsDisplayed() {
		assertTrue(waitForExpectedElement(INVALID_POSTAL_CODE, 10).isDisplayed());
	}

	public void userNavigatesToTofinoPDP() {
		String strStrengthLabel = waitForExpectedElement(CBD_STRENGTH_LABEL_PDP).getText();
		assertEquals(strStrengthLabel, "CHOOSE CBD STRENGTH (MG)");
	}

	public void fetchSelectedCBDStrengthVariant() {
		String strSelectedStrength = waitForExpectedElement(SELECTED_CBD_STRENGTH_PDP).getText();
		System.setProperty("selectedCBDStrength", strSelectedStrength);
	}

	public void assertNewsletterComponentAndFooterDisclaimerForTofinoProducts() {
		assertTrue(invisibilityOfElementLocated(NEWSLETTER_SUBSCRIPTION_PDP));
		assertTrue(waitForExpectedElement(TOFINO_FOOTER_DISCLAIMER_PDP, 10).isDisplayed());
		assertTrue(waitForExpectedElement(TOFINO_FOOTER_DISCLAIMER_PDP).getText().contains("Vuse CBD products may be hazardous to health"));
	}

	public void selectPodOption() {
		switch (UrlBuilder.getLocale()) {
			case "mx":
			case "vusemx":
				waitForExpectedElement(ENGRAVING_PODS_OPTIONS, 10).click();
				waitForExpectedElement(ENGRAVING_SELECT_MANGO, 10).click();
				waitForExpectedElement(ENGRAVING_MANGO_QTY_SELECTION, 10).click();
				waitForItemToBeClickableAndClick(ENGRAVING_NEXT_PODS);
				break;
		}
	}


	public void couponCodeValidationOnCartPage(String couponAmount) {
		//String actualCoupon = waitForExpectedElement(COUPON_NAME, 10).getText();
		//Assert.assertEquals(actualCoupon, couponName);
		String actualAmount = waitForExpectedElement(COUPON_AMOUNT, 10).getText();
		Assert.assertEquals(actualAmount, couponAmount);
	}

	public void couponCodeValidationOnOrderHistoryPage(String couponAmount) {
		//String actualCoupon = waitForExpectedElement(COUPON_NAME_CHECKOUT, 10).getText();
		//Assert.assertEquals(actualCoupon, couponName);
		String actualAmount = waitForExpectedElement(COUPON_AMOUNT_CHECKOUT, 10).getText();
		Assert.assertEquals(actualAmount, couponAmount);
	}

	public void clickWishlistButton() {
		clickByElementByQueryJSExecutor(ADD_TO_WISHLIST_BTN);
	}

	public boolean isWishlistButtonDisplayed() {
		return waitForExpectedElement(ADD_TO_WISHLIST_BTN).isDisplayed();
	}

    public void landingToPDPDirectly() {
		UrlBuilder.navigateToAnyPDP();
    }

	public String getProductNameToBeAddedToCart() {
		switch (UrlBuilder.getLocale()) {
			case "fr":
			case "vusefr":
				return waitForExpectedElement(PDP_PRODUCT_NAME_CSS_FR).getText();
			case "vuseza":
				return waitForExpectedElement(PDP_PRODUCT_NAME_ZA).getText();
			case "mx":
			case "vusemx":
			case "vypeit":
			case "kz":
			case "vuseco":
			case "vuseuk":
			case "vuseit":
				try{
					return presenceOfAllElementsLocatedBy(PDP_PRODUCT_NAME_CSS_MX).get(0).getText();}
				catch(Exception e){
					return presenceOfAllElementsLocatedBy(PDP_PRODUCT_NAME_CSS_IT).get(0).getText();}
			case "lyftse":
				return waitForExpectedElement(PDP_PRODUCT_NAME_CSS_LYFTSE).getText();
			case "vusede":
				return waitForExpectedElement(PDP_PRODUCT_NAME_CSS_VUSEDE).getText();
			case "glode":
			case "it":
      case "pl":
				return waitForExpectedElement(PDP_PRODUCT_NAME_CSS_GLODE).getText();
			default:
				return waitForExpectedElement(PDP_PRODUCT_NAME_CSS).getText();

		}
	}

	public void cLickOnAskQuestionCTA() {
		waitForExpectedElement(ASK_QUESTION_BUTTON,5).click();
	}

	public void fillAskQuestionForm(){
		waitForExpectedElement(ASK_QUESTION_FIRST_NAME).sendKeys(UrlBuilder.getMessage("firstName.key"));
		waitForExpectedElement(ASK_QUESTION_LAST_NAME).sendKeys(UrlBuilder.getMessage("lastName.key"));
		waitForExpectedElement(ASK_QUESTION_EMAIL).sendKeys(UrlBuilder.getMessage("email.key"));
		waitForExpectedElement(ASK_QUESTION_QUESTION_TITLE).sendKeys(UrlBuilder.getMessage("title.key"));
		waitForExpectedElement(ASK_QUESTION_QUESTION_CONTENT).sendKeys(UrlBuilder.getMessage("content.key"));
		clickUsingJS(ASK_QUESTION_QUESTION_TICKBOX);
		clickUsingJS(ASK_QUESTION_QUESTION_SUBMIT);
		waitForAjaxElementNotToBePresent(getWebDriver(),8);
	}

	public void assertSpecificationPresentUnderPdpPage(){
		scrollElementIntoView(SPECIFICATION_SECTION);
		softAssert.assertTrue(isElementPresent(SPECIFICATION_SECTION));
		softAssert.assertTrue(getTextFor(SPECIFICATION_TITLE).equals(UrlBuilder.getMessage("specificationsTitle.key")));
		softAssert.assertAll();
	}

	public void clickJustAddToCartButton() {
		waitForExpectedElement(M_ADDTOCART_BUTTON, 20);
		waitForExpectedElement(M_ADDTOCART_BUTTON, 10).click();
	}

	public void fillAndSubmitRatingForm(){
		switch(UrlBuilder.getLocale()) {
			case "vuseit":
			case "vuseza":
				waitForExpectedElement(FIRST_NAME_TITLE, 8).sendKeys(random(6, ALPHABETS));
				reviewField = random(6, ALPHABETS);
				waitForExpectedElement(TITLE_FIELD).sendKeys(reviewField);
				waitForExpectedElement(REVIEW_DESCRIPTION).sendKeys(random(10, ALPHABETS));
				clickIndexElementByQueryJSExecutor(REVIEW_STAR_RATING, 3);
				clickByElementByQueryJSExecutor(REVIEW_SUBMIT_BUTTON);
				break;
			case "vuseuk":
				waitForAjaxElementNotToBePresent(getWebDriver(), 8);
				assertTrue(getReviewComment(1).equalsIgnoreCase("poor"));
				assertTrue(getReviewComment(2).equalsIgnoreCase("fair"));
				assertTrue(getReviewComment(3).equalsIgnoreCase("average"));
				assertTrue(getReviewComment(4).equalsIgnoreCase("good"));
				assertTrue(getReviewComment(5).equalsIgnoreCase("excellent"));
				waitForExpectedElement(REVIEW_TITLE).sendKeys(random(8, ALPHABETS));
				System.setProperty("ReviewDescription.key",random(55, ALPHABETS));
				waitForExpectedElement(REVIEW_TEXT_AREA).sendKeys(System.getProperty("ReviewDescription.key"));
				waitClearAndEnterText(EMAIL_INPUT,UrlBuilder.getMessage("loginValidEmail.key"));
				if(getWebDriver().findElements(NICK_NAME_INPUT).size()>0)
					waitForExpectedElement(NICK_NAME_INPUT).sendKeys(random(8, ALPHABETS));
				waitForExpectedElement(LOCATION_INPUT).sendKeys(random(8, ALPHABETS));
				clickFirstElementByQueryJSExecutor(QUALITY_STAR_RATING);
				clickFirstElementByQueryJSExecutor(VALUE_STAR_RATING);
				assertTrue(waitForExpectedElement(TERMS_CONDITIONS_LINK).isDisplayed());
				clickByElementByQueryJSExecutor(TERMS_CONDITIONS_CHECKBOX);
				clickByElementByQueryJSExecutor(POST_REVIEW_BUTTON);
				waitForAjaxElementNotToBePresent(getWebDriver(), 8);
				break;
			default:
		}
	}

	public String getReviewComment(int rating){
		clickIndexElementByQueryJSExecutor(REVIEW_STARS_MODAL_WINDOW,rating-1);
		return waitForExpectedElement(OVERALL_RATING_COMMENT,5).getText();
	}

	public String getReviewedDescriptionText(){
		return waitForExpectedElement(REVIEWED_DISCRIPTION_ON_PDP,5).getText().trim();
	}

	public String getWeekDay(){
		String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		Calendar calendar = Calendar.getInstance();
		Calendar LATime = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		LATime.setTimeInMillis(calendar.getTimeInMillis());
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return days[dayOfWeek -1];
	}

	public void selectOOSDeviceSKUAndAssertNotifyMeCTA() {
		boolean blnFlag = (boolean) scenarioContext.getContext(Context.PRODUCT_OOS);
		if(blnFlag) {
			waitForExpectedElement(NOTIFY_ME_BUTTON,6);
			if (getWebDriver().findElements(NOTIFY_ME_BUTTON).size() == 0)
				waitForExpectedElement(OOS_PRODUCT_SWATCH).click();
			assertTrue(waitForExpectedElement(NOTIFY_ME_BUTTON).getText().toLowerCase().contains(UrlBuilder.getMessage("notifyMeWhenOOSText.key")));
			clickByElementByQueryJSExecutor(NOTIFY_ME_BUTTON);
			assertTrue(waitForExpectedElement(NOTIFY_ME_POP_UP_WINDOW, 4).isDisplayed());
			clickByElementByQueryJSExecutor(NOTIFY_ME_WINDOW_CLOSE);
		}

	}

	public void verifyOneTimePurchaseOptionSelected() {
		assertTrue(waitForExpectedElement(ONE_IIME_PURCHASE, 4).isDisplayed());
	}

	public void verifySubscriptionOptionUnselected() {
		assertTrue(invisibilityOfElementLocated((SUBSCRIPTION_FROM_SELECT)));
	}

	public void clickSubscriptionOption() {
		switch(UrlBuilder.getLocale()){
			case "vuseit":
			case "vusefr":
				clickByElementByQueryJSExecutor(SUBSCRIPTION_FROM);
				break;
			default:
				scrollToPageTop();
				waitForExpectedElement(SUBSCRIPTION_FROM, 10).click();
		}
	}

	public void clickSubscriptionOptionPlp() {
		clickByElementByQueryJSExecutor(SUBSCRIPTION_PLP);
	}

	public void verifyOneTimePurchasePriceDisplay() {
		switch(UrlBuilder.getLocale()){
			case "vusefr":
			case "vuseit":
				assertTrue(isElementDisplayedBySeconds(ONE_TIME_PRICE_IT, 10));
				break;
			default:
				assertTrue(waitForExpectedElement(ONE_TIME_PRICE, 4).isDisplayed());
		}
	}

	public void verifySubscriptionPriceDisplay() {
		switch(UrlBuilder.getLocale()){
			case "vuseit":
				break;
			case "vusefr":
				assertTrue(isElementDisplayedBySeconds(SUBSCRIPTION_PRICE_FR, 4));
				break;
			default:
				assertTrue(isElementDisplayedBySeconds(SUBSCRIPTION_PRICE, 4));
		}

	}

	public void verifyUserClickSubscriptionInfo() {
		switch(UrlBuilder.getLocale()){
			case "vusefr":
				String Bronze = UrlBuilder.getMessage("bronze.key");
				String Money = UrlBuilder.getMessage("money.key");
				String Gold = UrlBuilder.getMessage("gold.key");
				assertTrue(isElementDisplayedBySeconds(SUBSCRIPTION_INFO_IT, 4));
				clickUsingJS(SUBSCRIPTION_INFO_IT);
				waitForExpectedElement(SUBSCRIPTION_PACKS_BRONZE, 5);
				assertEquals(getTextFor(SUBSCRIPTION_PACKS_BRONZE) ,Bronze);
				assertEquals(getTextFor(SUBSCRIPTION_PACKS_MONEY) ,Money);
				assertEquals(getTextFor(SUBSCRIPTION_PACKS_GOLD_FR) ,Gold);
				break;
			case "vuseit":
				assertTrue(isElementDisplayedBySeconds(SUBSCRIPTION_INFO_IT, 4));
				clickUsingJS(SUBSCRIPTION_INFO_IT);
				waitForExpectedElement(SUBSCRIPTION_PACKS_SILVER, 5);
				assertEquals(getTextFor(SUBSCRIPTION_PACKS_SILVER) ,"5-7 confezioni");
				assertEquals(getTextFor(SUBSCRIPTION_PACKS_GOLD) ,"8-11 confezioni");
				assertEquals(getTextFor(SUBSCRIPTION_PACKS_PLATINUM) ,"15-25 confezioni");
				break;
			default:
				assertTrue(waitForExpectedElement(SUBSCRIPTION_INFO, 4).isDisplayed());
		}
	}

	public void verifySubscriptionPage(){
		assertTrue(isElementDisplayedBySeconds(SUBSCRIPTION_PAGE_1,4));
		assertTrue(isElementDisplayedBySeconds(SUBSCRIPTION_PAGE_2,4));
	}

	public void verifySubscriptionSaveDisplayed() {
		assertTrue(waitForExpectedElement(SUBSCRIPTION_SAVE, 4).isDisplayed());
	}

	public void assertProductQuantityAndPrice() {
		assertTrue(isElementDisplayedBySeconds(PRODUCT_QUANTITY,5));
		assertTrue(isElementDisplayedBySeconds(PRODUCT_PRICE_UK,5));
	}

	public void assertProductPriceRemainSame () {
		switch(UrlBuilder.getLocale()){
			case "vuseuk":
				String[] productPrice = getWebDriver()
						.findElement(PRODUCT_PRICE_UK)
						.getText().split("€");

				homePage.waitForExpectedElement(homePage.btnIncreaseItemQty, 5).click();
				homePage.waitForAjaxElementNotToBePresent(homePage.getWebDriver(), 5);
				assertEquals(homePage.waitForExpectedElement(homePage.inputItemQuantity).getAttribute("value"), "2");

				assertTrue(isElementDisplayedBySeconds(PRODUCT_PRICE_UK,5));
				String[] productPriceAfterIncreaseQuantity = getWebDriver()
						.findElement(PRODUCT_PRICE_UK)
						.getText().split("€");

				assertEquals(productPrice,productPriceAfterIncreaseQuantity);
				break;
			case "vuseit":
				String[] expectedPrice = homePage.waitForExpectedElement(ONE_TIME_PRICE_IT).getText().split("€");
				String[] actualPrice = homePage.waitForExpectedElement(PRICE_IT).getText().split("€");
				assertEquals(actualPrice[0],expectedPrice[0]);
				break;
		}
	}

	public void assertMultipleProductPresentInCombo() {
		assertTrue(isElementDisplayedBySeconds(GROUPED_PRODUCT,5));
	}

    public boolean isFAQPresent() {
        return isElementDisplayedBySeconds(FAQ_VUSE_DE, 5);
    }


	public void verifyAllCTAonPDPPAge() {
		assertTrue(isElementDisplayedBySeconds(INCREMENT_BUTTON,2));
		clickUsingJS(INCREMENT_BUTTON);
		assertTrue(isElementDisplayedBySeconds(DECREMENT_BUTTON,2));
		try {
			clickUsingJS(DECREMENT_BUTTON);
		} catch (Exception e) {
			LOG.info("Trying alternate button option");
			clickByElementByQueryJSExecutor(DECREMENT_BUTTON_EPOD);
		}
		assertTrue(isElementDisplayedBySeconds(ADD_REVIEW_BUTTON,2));
		assertTrue(isElementDisplayedBySeconds(PDP_ADDTOCART_BUTTON,2));
		clickUsingJS(PDP_ADDTOCART_BUTTON);
		waitForExpectedElement(YOUR_BASKET,10);
		assertTrue(isElementDisplayedBySeconds(VIEW_BASKET,4));
	}

	public boolean verifyFAQsForProduct() {
		return isElementDisplayedBySeconds(PRODUCT_FAQ, 5);
	}

	public void verifyCTANavigateToCorrectPage() {
		waitForPage();
		clickUsingJS(INCREMENT_BUTTON);
		String actualQuantity = waitForExpectedElement(PRODUCT_QUANTIITY, 10).getAttribute("value");
		Assert.assertEquals(actualQuantity, "2");
		try {
			clickUsingJS(DECREMENT_BUTTON);
		} catch (Exception e) {
			LOG.info("Trying alternate button option");
			clickByElementByQueryJSExecutor(DECREMENT_BUTTON_EPOD);
		}
		String actualQuantityNext = waitForExpectedElement(PRODUCT_QUANTIITY, 10).getAttribute("value");
		Assert.assertEquals(actualQuantityNext, "1");
		clickAddToCartButton();
		assertTrue(isElementDisplayedBySeconds(VIEW_BASKET,5));
	}

	public void verifyCTAonDeviceEpodPage() {
		assertTrue(isElementDisplayedBySeconds(INCREMENT_BUTTON,4));
		clickUsingJS(INCREMENT_BUTTON);
		assertTrue(isElementDisplayedBySeconds(DECREMENT_BUTTON_EPOD,4));
		clickUsingJS(DECREMENT_BUTTON_EPOD);
		assertTrue(isElementDisplayedBySeconds(ADD_ENGRAVE,2));
		clickUsingJS(ADD_ENGRAVE);
		assertTrue(isElementDisplayedBySeconds(ENGRAVING_DETAILS,2));
		clickUsingJS(CANCEL_ENGRAVE);
		assertTrue(isElementDisplayedBySeconds(ADD_REVIEW_BUTTON,2));
		assertTrue(isElementDisplayedBySeconds(PDP_ADDTOCART_BUTTON,10));
		clickByElementByQueryJSExecutor(PDP_ADDTOCART_BUTTON);
		waitForAjaxElementNotToBePresent(webDriver,5);
		if(isElementPresent(VIEW_BASKET_ZA)){
			if(UrlBuilder.getLocale().equalsIgnoreCase("vuseza")) {
				try {
					homePage.openBasketifNotOpenLogic();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			assertTrue(isElementDisplayedBySeconds(VIEW_BASKET_ZA,10));
		}
		else{
		homePage.clickBasketIcon();
		assertTrue(isElementDisplayedBySeconds(VIEW_BASKET_ZA,10));}
	}

	public void clickMoreSubscriptionLink() { waitAndClickByElementByJSExecutor(VIEW_MORE_SUBSCRIPTION, 2); }

	public boolean isSubscriptionPopUpDisplayedOnPDP() {
		return isElementDisplayedBySeconds(SUBSCRIPTION_INFO_POPUP, 2);
	}

		public boolean isConfigurable() {
		try {
				waitForExpectedElement(CONFIGURABLE);
				return true;
			} catch (Exception e) {
				return false;
			}
		}



	public void avalancheClearPopups() {
		try {
			clickUsingJS(homePage.allowCookiesButton);
		} catch (Exception e) {};
		try {
			clickUsingJS(By.cssSelector("#age-gate-over"));
		} catch (Exception e) {};
		try {
			clickUsingJS(By.cssSelector("#btn-entry-age-allow > span"));
		} catch (Exception e) {};
		try {
			clickUsingJS(By.cssSelector("#hs-eu-confirmation-button"));
		} catch (Exception e) {};
	}

	private void avalancheEmptyBasket() {
		List<WebElement> basketItems = webDriver.findElements(BasketPage.BASKET_ITEM);
		for (WebElement basketItem : basketItems) {
			try {
				webDriver.findElement(BasketPage.BASKET_ITEM_REMOVE_BUTTON).click();
			} catch (Exception e) {
				clickUsingJS(basketItem.findElement(BasketPage.BASKET_ITEM_REMOVE_BUTTON));
			}
			try {
				waitForExpectedElement(BasketPage.BASKET_ITEM_REMOVE_CONFIRM).click();
			} catch (Exception e) {
				clickUsingJS(BasketPage.BASKET_ITEM_REMOVE_CONFIRM);
			}
		}
	}

	public void clickOnFirstBuyableProdPDP(){
		waitForAjaxElementNotToBePresent(webDriver,3);
		scrollToPageBottom();
		waitForExpectedElements(AVALANCHE_PDP_BUYABLE_PRODUCT).get(1).findElement(By.cssSelector("a")).click();
	}


	public void clickCancelSubscriptionLink() {
		waitAndClickByElementByJSExecutor(CANCEL_SUBSCRIPTION, 2);
	}

	public Integer getProductReviewListSize(){
		return  getWebDriver().findElements(PRODUCT_REVIEW_LIST).size();
	}

	public void addPersonalisationTextIntoTheField(String number) {
		waitForExpectedElement(PERSONLIZATION_TEXT_INPUT).click();
		waitForExpectedElement(PERSONLIZATION_TEXT_INPUT).clear();
		waitForExpectedElement(PERSONLIZATION_TEXT_INPUT).sendKeys(RandomGenerator.randomAlphabetic(Integer.valueOf(number)));
	}

	public boolean isPersonalisationTextErrorDisplayed() {
		return isElementDisplayedBySeconds(PERSONLIZATION_TEXT_ERROR_LABEL, 5);
	}

	public String getAddToBasketSuccessMessage() {
		return waitForExpectedElement(ADD_TO_BASKET_SUCCESS_MSG, 10).getText();
	}
	public void selectFont(String strIndex){
		WebElement font=waitForExpectedElements(PERSONLIZATION_FONT_RADIO).get(Integer.parseInt(strIndex));
		font.click();
		engravingAttributeList.add(EngravingAttribute.builder().name(UrlBuilder.getMessage("engravingTypeFace.key"))
				.value(font.getText().toLowerCase())
				.build());
	}

	public void assertArtistCMSBlock(String block)
	{
		switch(block)
		{
			case "artist-block-container":
				assertTrue(isElementDisplayedBySeconds(ARTIST_BLOCK_CONTAINER_VUSEUK,5), "ARTIST_BLOCK_CONTAINER_VUSEUK missing on page.");
				break;
			case "artist-block-image":
				scrollToElement(ARTIST_BLOCK_IMAGE_VUSEUK);
				assertTrue(isElementDisplayedBySeconds(ARTIST_BLOCK_IMAGE_VUSEUK,5), "ARTIST_BLOCK_IMAGE_VUSEUK missing on page.");
				break;
			case "artist-block-artist-name":
				scrollToElement(ARTIST_BLOCK_ARTIST_NAME_VUSEUK);
				assertTrue(!waitForExpectedElement(ARTIST_BLOCK_ARTIST_NAME_VUSEUK,5).getText().isEmpty(), "ARTIST_BLOCK_ARTIST_NAME_VUSEUK missing on page.");
				break;
			case "artist-block-artist-desc":
				scrollToElement(ARTIST_BLOCK_ARTIST_DESC_VUSEUK);
				assertTrue(!waitForExpectedElement(ARTIST_BLOCK_ARTIST_DESC_VUSEUK,5).getText().isEmpty(), "ARTIST_BLOCK_ARTIST_DESC_VUSEUK missing on page.");
				break;
			case "artist-block-artist-collection-title":
				scrollToElement(ARTIST_BLOCK_ARTIST_COLLECTION_TITLE_VUSEUK);
				assertTrue(!waitForExpectedElement(ARTIST_BLOCK_ARTIST_COLLECTION_TITLE_VUSEUK,5).getText().isEmpty(), "ARTIST_BLOCK_ARTIST_COLLECTION_TITLE_VUSEUK missing on page.");
				break;
			case "artist-block-artist-collection-detail":
				scrollToElement(ARTIST_BLOCK_ARTIST_COLLECTION_DETAIL_VUSEUK);
				assertTrue(!waitForExpectedElement(ARTIST_BLOCK_ARTIST_COLLECTION_DETAIL_VUSEUK,5).getText().isEmpty(), "ARTIST_BLOCK_ARTIST_COLLECTION_DETAIL_VUSEUK missing on page.");
				break;
		}

	}

	public void clickStrengthByValue(String strengthValue) {
		List<WebElement> strengthElements=new ArrayList<>();
		switch (UrlBuilder.getLocale()){
			case "vuseuk":
				strengthElements=getWebDriver().findElements(STRENGTH_PDP);
				break;
			default:
		}
		for(WebElement s:strengthElements){
			String strengthNumber=getNumberFromString(s.getText());
			if(strengthValue.trim().equals(strengthNumber)){
				s.click();
			}
		}
	}

	public boolean isNoticeMeWheninStockDisplayed() {
		return waitForExpectedElement(NOTICE_ME_WHEN_IN_STOCK).isDisplayed();
	}


	public void assertErrorMessageForAskAQuestionSubmitWithoutAnyDetails(){
		String errorMessage = UrlBuilder.getMessage("expectedRequiredSelectionError.key");
		waitForExpectedElement(ASK_QUESTION_QUESTION_TICKBOX,5);
		clickUsingJS(ASK_QUESTION_QUESTION_TICKBOX);
		clickUsingJS(ASK_QUESTION_QUESTION_SUBMIT);
		waitForExpectedElement(ASK_QUESTION_FIRST_NAME_ERROR,10);
		assertTrue(waitForExpectedElement(ASK_QUESTION_FIRST_NAME_ERROR).getText().contains(errorMessage));
		assertTrue(waitForExpectedElement(ASK_QUESTION_EMAIL_ERROR).getText().contains(errorMessage));
		assertTrue(waitForExpectedElement(ASK_QUESTION_QUESTION_TITLE_ERROR).getText().contains(errorMessage));
		assertTrue(waitForExpectedElement(ASK_QUESTION_QUESTION_CONTENT_ERROR).getText().contains(errorMessage));
	}

	public void enterInvalidEmailAssertErrorMessage(){
		String errorMessage = UrlBuilder.getMessage("invalidEmailErrorMsg.key");
		waitForExpectedElement(ASK_QUESTION_QUESTION_TICKBOX,5);
		enterDataAndWait(ASK_QUESTION_EMAIL,"test@test");
		clickUsingJS(ASK_QUESTION_QUESTION_SUBMIT);
		assertTrue(waitForExpectedElement(ASK_QUESTION_EMAIL_ERROR).getText().contains(errorMessage));
	}


	public void userClickOnSearchIconAndSubmitsSearchTermSelectProductOnLeft(String searchTerm) {
		enterDataAndWait(SEARCH_INPUTBOX_UK,UrlBuilder.getMessage(searchTerm));
		waitForExpectedElement(SEARCH_SUGGESTION,10);
		clickUsingJS(FIRST_SUGGESTION);
	}

    public boolean moreExploreProductsDisplayed() {
		scrollElementIntoView(MORE_EXPLORE_PRODUCTS);
		return getWebDriver().findElements(MORE_EXPLORE_PRODUCTS).size()>0;
    }

	public void clickAddToCartCTA() {
		try {
			if (UrlBuilder.isIpad()) {
				waitForExpectedElement(addToCartButton, 15);
				clickUsingJS(addToCartButton);
			} else {
				waitForExpectedElement(addToCartButton, 10).click();
				waitForExpectedElement(basketQty, 20);
			}
		} catch (Exception e) {
			if (WebDriverHelper.BROWSER.equalsIgnoreCase(PermittedBrowserMode.FIREFOX.toString())) {
				waitForExpectedElement(addToCartButton, 20);
			}
			clickByElementByQueryJSExecutor(addToCartButton);
		}
	}

	public void submitRatingForm(){
		clickByElementByQueryJSExecutor(REVIEW_SUBMIT_BUTTON);
	}

	public void verifyPdpElements(){
		assertTrue(waitForExpectedElement(PDP_BRANDNAME_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_PRODUCT_HEADING_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_PRODUCT_SUBHEADING_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_PRODUCT_DESC_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_PRODUCT_STRENGTH_MILD_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_PRODUCT_STRENGTH_MEDIUM_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_QUANITY_BLOCK_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_PRODUCT_PRICE_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_ADDTOCART_PL).isDisplayed());
		assertTrue(waitForExpectedElement(PDP_DELIVERYDETAILS_BLOCK_PL).isDisplayed());
		try {
			clickUsingJS(PDP_PRODUCT_STRENGTH_MEDIUM_PL);
		}catch (Exception e){
			clickUsingJS(PDP_ADDTOCART_PL);
		}
		clickUsingJS(PDP_ADDTOCART_PL);
		waitForAjaxElementNotToBePresent(webDriver,3);
	}
}
