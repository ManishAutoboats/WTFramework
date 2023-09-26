package com.salmon.test.page_objects.gui.gloIt;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.UrlBuilder.getMessage;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.gui.constants.Context.COUPON_PRODUCT_URL_ISVALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

public class GloItCheckoutPage extends PageObject {

	private GloItHomePage gloItHomePage;
	private GloItProductListPage gloItProductListPage;
	private HomePage homePage;
	private LogininPage logininPage;
	private ScenarioContext scenarioContext;
	private PaymentPage paymentPage;
	private AddNewAddressPage addNewAddressPage;
	private SoftAssert softAssert;

	public GloItCheckoutPage(GloItHomePage gloItHomePage, HomePage homePage, LogininPage logininPage, ScenarioContext scenarioContext, AddNewAddressPage addNewAddressPage, SoftAssert softAssert, GloItProductListPage gloItProductListPage, PaymentPage paymentPage){
		this.gloItHomePage= gloItHomePage;
		this.homePage=homePage;
		this.logininPage=logininPage;
		this.scenarioContext = scenarioContext;
		this.paymentPage = paymentPage;
		this.addNewAddressPage = addNewAddressPage;
   	this.softAssert= softAssert;
   	this.gloItProductListPage=gloItProductListPage;
	}
	public String emailAddressData = RandomGenerator.randomEmailAddress(6);
	public final static By LOADER = By.cssSelector("div.LOADER");
	private By cardPaymentMethodCheckbox = By.cssSelector("input#worldpay_cc");
	private By creditCardCheckbox = By.cssSelector("#cardTypeCredit");
	private By creditCardHolderNameField = By.cssSelector("input#worldpay_cc_cc_name");
	private By creditCardNumberField = By.cssSelector("input#worldpay_cc_cc_number");
	private By creditCardCVVField = By.cssSelector("input#worldpay_cc_cc_cid");
	private By creditCardExpirationDateMonthDropDown = By.cssSelector("#worldpay_cc_expiration");
	private By creditCardExpirationDateYearDropDown = By.cssSelector("select#worldpay_cc_expiration_yr.select.select-year");
	private By debitCardCheckbox = By.cssSelector("input#cardTypeDebit");
	private By savedCardTypeCheckbox = By.cssSelector("input#direct_cc_savedcard");
	private By usersSavedCardCheckbox = By.cssSelector("input[id*=saved_card]");
	private By otherPaymentMethodCheckbox = By.cssSelector("#worldpay_apm");
	private By termsconditonscheckbox = By.cssSelector("input#agreement_worldpay_apm_2");
	private By termsConditonCardCheckbox = By.cssSelector("input#agreement_worldpay_cc_2");
	private final static By PLACE_ORDER_BUTTON_CARD = By.cssSelector("div.payment-method.payu-payment._active:nth-child(4) div.primary button.action.primary.checkout > span");
	private final static By PLACE_ORDER_BUTTON_CARD_VELOPL = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method.payu-payment._active > div.payment-method-content > div.actions-toolbar > div > button");
	private final static By PLACE_ORDER_BUTTON_CARD_GLOIT = By.cssSelector(".payment-method._active #checkout-place-order span span");
	private final static By PLACE_ORDER_BUTTON_CARD_GLOIT_KZ = By.cssSelector("button.action.primary.checkout");
	private By orderSummaryHeading = By.cssSelector("div.opc-block-summary");
	private final static By PLACE_ORDER_BUTTON_CARDPL_IPAD = By.cssSelector("button.action.primary.checkout");
	private By paypalCheckbox = By.cssSelector("#apm_PAYPAL-EXPRESS");
	public By couponCodeLink = By.cssSelector("#block-discount-heading");
	public By couponCodeInputField = By.cssSelector("#discount-code");
	public final static By appyDiscountButton = By.cssSelector("button.action.action-apply");
	public By couponCodeCancelButton = By.cssSelector(".action.action-cancel.secondary");
	private By validPromoCodeSucessMsg = By.cssSelector("div.message.message-success.success");
	private By inValidPromoCodeSucessMsg = By.cssSelector("div.payment-option-content > div > div > div");
	private By switchAddressCheckbox = By.cssSelector("button.action.action-select-shipping-item> span:nth-child(1)");
	private By cancelDiscount = By.xpath("//form[@id='discount-form']//button[@class='action action-cancel']");
	private final static By PROMOTION_ERROR_MSG = By.cssSelector("div.message.message-error.error");
	public final static By CHECKBOX_COD = By.cssSelector("input#cashondelivery");
	private final static By CHECKBOX_COD_TERMSANDCONDITION_GLO_PL = By.cssSelector("#agreement_cashondelivery_10");
	private final static By CHECKBOX_COD_TERMSANDCONDITION_VELO_PL = By.cssSelector("label[for='agreement_cashondelivery_26']");
	private final static By SUBMIT_COD_ORDER_VELO_PL = By.cssSelector("button[class='action primary checkout'] span");
	private final static By PAYU_PAYMENT = By.cssSelector("input#payu_gateway");
	private final static By PAYU_PAYMENT_TERMS_AND_CONDITION = By.xpath("//*[contains(@id,'agreement_payu_gateway_')]");
	private final static By PAYU_PAYMENT_CARD_NUMBER = By.cssSelector("#card-number");
	private final static By PAYU_PAYMENT_CARD_EXPIRYDATE = By.cssSelector("#card-date");
	private final static By PAYU_PAYMENT_CARD_CVV = By.cssSelector("#card-cvv");
	private final static By BUTTON_PAYU_PAYMENT_SUBMIT = By.name("submit");
	private final static By PAYU_VISACARD_PAYMENT = By.cssSelector("img[src*='pbl_c']");
	private final static By M_PAYU_VISACARD_PAYMENT_IPAD_GLOPL = By.cssSelector("div.payment__method > div.method__list > div:nth-child(9) > div > span.method__single__image > img[src*='pbl_c']");
	private final static By BUTTON_CONTINUE_SHOPPING = By.cssSelector(".action-button.buttonlike.secondary");
	private static final By CREDIT_CARD_RADIO_BTN_GLODE = By.cssSelector("input#cardTypeCredit");
	private final static By SELECT_BLIK_PAYMENT_OPTION=By.cssSelector("img[src*='blik']");
	private final static By SELECT_MBANK_PAYMENT_OPTION=By.cssSelector("img[src*='pbl_m']");
	private static final By SELECT_BANK_PEKAO_PAYMENT_OPTION = By.cssSelector("img[src*='pbl_o']");
	private static final By SELECT_BANK_IPKO_PAYMENT_OPTION = By.cssSelector("img[src*='pbl_p']");
	private final static By CLICK_GO_TO_BANK_BTN = By.cssSelector("#app  input[type=submit]");
	private static final By CLICK_GO_TO_BANK_BTN_BLIK = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method.payu-payment._active > div.payment-method-content > div.actions-toolbar > div > button");
	private final static By CLICK_SUBMIT_BANK_SIMULATOR_BTN=By.cssSelector("input#formSubmit");
	private final static By CLICK_SUBMIT_BANK_SIMULATOR_BTN_VELOPL=By.cssSelector("#app > div > div > main > div > article > div > div > div > input[type=submit]");
	private final static By CLICK_LOGOUT_BANK_SIMULATOR_BTN=By.cssSelector("#btnLogout");
	private final static By CHECKOUT_SUCCESS_SECTION=By.cssSelector(".checkout-success");
	private final static By TRANSACTION_CONFIRMATION_BLINK= By.cssSelector("#btnLogout");
	private final static By TRANSACTION_CONFIRMATION_BLINK_VELOPL = By.cssSelector("#formSubmit");
	private final static By POSITIVE_AUTH_MBANK=By.cssSelector("#formSubmit");
	private final static By CONTINUE_BTN_MABANK=By.cssSelector("#formSubmit");
	public static final By USE_THIS_ADDRESS_BUTTON_PL = By.cssSelector("footer.modal-footer > button.action.submit.primary > span");
	public static final By STREET_ADDRESS_FIELD3_CHECKOUT=By.xpath("//div[@id='shipping-new-address-form']//input[@name='street[2]']");
	public static final By STREET_ADDRESS_MAX_LIMIT_ERROR=By.cssSelector("div.field-error > span");
	public static final By VAT_NUMBER_FIELD=By.xpath("//*[@id='shipping-new-address-form']//input[@name='vat_id']");
	public static final By VAT_NUMBER_FIELD_ERROR=By.xpath("//*[@id='shipping-new-address-form']//input[@name='vat_id']//following::div[@class='field-error']");

	//Guest Checkout Form
	private final static By EMAIL_FIELD=By.cssSelector("input#customer-email");
	private final static By FIRSTNAME_FIELD=By.xpath("//input[@name='firstname']");
	private final static By LASTNAME_FIELD=By.xpath("//input[@name='lastname']");
	public final static By STREET_FIELD=By.name("street[0]");
	public final static By HOUSE_FIELD=By.name("street[1]");
	private final static By CITY_FIELD=By.xpath("//input[@name='city']");
	private final static By REGION_FIELD=By.xpath("//select[@name='region_id']");
	private final static By POSTAL_CODE=By.xpath("//input[@name='postcode']");
	private final static By PHONE_NUMBER=By.xpath("//input[@name='telephone']");
	private final static By SAVE_BUTTON=By.xpath("//button[@class='action primary action-save-address']/span");
	public final static By loader=By.cssSelector("div.LOADER img");
	public final static By HEADER_PROMO_MESSAGE = By.cssSelector("div.header-promo");
	public final static By HEADER_PROMO_MESSAGE_PL = By.cssSelector("p.free_items_subtitle");
	public final static By HEADER_PROMO_MESSAGE_FOR_NO_FREE_CONSUMABLE = By.cssSelector(".message-success.success.message");
	private final static By MINICART_BACK_BUTTON = By.cssSelector("button#btn-minicart-close");
	public final static By PROCEED_CHECKOUT_FROM_VIEW_BASKET = By.cssSelector("li.item > button.action.primary.checkout");
	private static final By ADD_FREE_GIFT_BUTTON = By.cssSelector("button.action.tocart.primary.freeitem-addtocart");
	private static final By PROCEED_TO_CHECKOUT_GREYED_OUT = By.cssSelector("ul.checkout.methods.items.checkout-methods-items > li> button.disabled");
	public final static By LOADER_GLOPL = By.cssSelector("div.loader");
	public final static By VOUCHER_CODE_APPLIED = By.cssSelector("span.rule-name");
	public final static By GLO_BASKET_QTY = By.cssSelector("span.counter-number");
	public final static By GLO_BASKET_ICON = By.cssSelector("a.action.showcart");
	public By successPageHeading = By.cssSelector(".page-title-wrapper");
	public static final By VOUCHER_APPLIED_SUCCESS_MSG = By.cssSelector("div.message-success.success.message");
	private static final By CANCEL_APPLIED_COUPON = By.cssSelector("button.action.action-cancel.secondary > span");

	private static final By TERMS_BLOCK = By.cssSelector("div.payment-method._active > div.payment-method-content > div.checkout-agreements-block > div:nth-child(2) > div > div");
	private static final By TERMS_TITLE = By.cssSelector("div.terms-block-content>div.title");
	private static final By GLO_CONTACTS_BLOCK = By.cssSelector("div[data-bind='html: window.checRkoutConfig.gloOnlineBlock']");
	private static final By VELO_CONTACTS_BLOCK = By.cssSelector("div[data-bind='html: window.checkoutConfig.veloOnlineBlock']");
	private static final By GLO_TITLE = By.cssSelector("div#opc-sidebar>div:nth-child(4).checkout-content-block.contacts-block>div>div>div>p.footer-contact>strong");
	private static final By VELO_TITLE = By.cssSelector("div#opc-sidebar>div:nth-child(5).checkout-content-block.contacts-block>div>div>div>p.footer-contact>strong");
	private static final By CARD_PLACE_ORDER_BUTTON = By.cssSelector("div.payment-method._active>div:nth-child(2)>div.payment-method-content>div.actions-toolbar>div>button");
	private static final By PLACE_ORDER_BUTTON = By.cssSelector("div.payment-method._active>div.payment-method-content>div.actions-toolbar>div>button");
	public static final By COD_PAYMENT = By.cssSelector("#cashondelivery");
	public static final By CARD_PAYMENT = By.cssSelector("div.payment-method-title.field.choice>input#veritegmo_cc");
	public static final By CARD_NUMBER = By.cssSelector("input#veritegmo_cc_cc_number");
	public static final By MONTH_DD = By.cssSelector("select.select.select-month");
	public static final By YEAR_DD = By.cssSelector("select.select.select-year");
	public static final By CVV_NUMBER = By.cssSelector("input#veritegmo_cc_cc_cid");
	private static final By TERMS_AND_CONDITIONS_GLO_PL = By.cssSelector("div.payment__payu-agreement > div > div > div > a");
	private static final By TERMS_AND_CONDITIONS = By.cssSelector("div.payment-method-content>div.payment-method-content>div.checkout-agreements-block>div>div>div.checkout-agreement.required>label");
	public static final By TERMS_AND_CONDITIONS_TEXT = By.cssSelector("div.payment-method-content>div.payment-method-content>div.checkout-agreements-block>div>div>div.checkout-agreement.required>label>span");
	private static final By ORDER_SUMMARY_BLOCK = By.cssSelector("div.block.items-in-cart");
	private static final By TOTAL_ITEMS = By.cssSelector("span.heading-count");
	private static final By TOTAL_CHECKOUT_VALUE = By.cssSelector("div.block.items-in-cart>div.title>span.items-in-cart-heading>div>table>tbody>tr.grand.totals.incl>td>strong>span.price");
	private static final By EXPAND_PRODUCT_LIST_ICON = By.cssSelector("div.block.items-in-cart>div.title[aria-expanded=false]");
	private static final By COLLAPSE_PRODUCT_LIST_ICON = By.cssSelector("div.block.items-in-cart>div.title[aria-expanded=true]");
	private static final By PRODUCT_LIST = By.cssSelector("div.content.minicart-items>div>div:nth-child(4)>div>div>ol.minicart-items");
	private static final By ORDER_SUMMARY_PRODUCT_LIST = By.cssSelector("#opc-sidebar>div.opc-block-summary>div.block.items-in-cart.active>div.content.minicart-items>div>div.os-padding>div>div>ol>li");
	public static final By PRODUCT_NAME = By.cssSelector("span.product-item-name");
	private static final By PRODUCT_QTY = By.cssSelector("div.details-qty>span.value");
	private static final By PRODUCT_PRICE = By.cssSelector("span.cart-price");
	private static final By MAIN_BODY_COLOR = By.cssSelector("div.product.options > div > dl > dd:nth-child(2)");
	private static final By ORDER_MAIN_BODY_COLOR = By.cssSelector("dl.item-options>dd:nth-child(1)");
	private static final By SIDE_BODY_COLOR = By.cssSelector("dl.item-options>dd:last-child");
	private static final By ORDER_SIDE_BODY_COLOR = By.cssSelector("dl.item-options>dd:last-child");
	private static final By PRODUCT_IMG = By.cssSelector("div.product>span>span.product-image-wrapper>img");
	private static final By COURIER_DELIVERY_TEXT = By.cssSelector("tr.row>td#label_method_courier_delivery__select_a_time_and_day_for_delivery__matrixrates");
	public static final By COURIER_DELIVERY_RADIO_BUTTON = By.cssSelector("table.table-checkout-shipping-method>tbody>tr.row>td>input.radio");
	public static final By TIME = By.cssSelector("#slotSelect");
	public static final By cardError = By.cssSelector("#veritegmo_cc_cc_number-error");
	public static final By insufficientCardError = By.cssSelector("div.message.message-error.error");
	public static final By cvvError = By.cssSelector("#veritegmo_cc_cc_cid-error");
	public static final By expiryDateError = By.cssSelector("#veritegmo_cc_expiration-error");
	public static final By expiryYearError = By.cssSelector("#veritegmo_cc_expiration_yr-error");
	private static final By APPLY_DISCOUNT_TEXT = By.cssSelector("#discount-code,#coupon_code");
	private static final By DISCOUNT_CODE = By.cssSelector("input#discount-code");
	public static final By BILLING_ADDRESS_RESTRICTION_ERROR=By.cssSelector("[data-ui-id='checkout-cart-validationmessages-message-error']");

	private static final By CANCEL_COUPON_BUTTON = By.cssSelector("button.action.action-cancel.secondary");
	public static final By ERROR_MESSAGE = By.cssSelector("div.message.message-error.error>div");
	private static final By DELIVERY_PRICE = By.cssSelector("div.opc-block-summary>div.table-totals-wrapper>table>tbody>tr.totals.shipping.incl>td>span.price");
	private static final By DELIVERY_PRICE_TEXT = By.cssSelector("div.opc-block-summary>div.table-totals-wrapper>table>tbody>tr.totals.shipping.incl");
	private static final By GRAND_TOTAL = By.cssSelector("div.opc-block-summary>div.table-totals-wrapper>table>tbody>tr.grand.totals.incl>td>strong>span.price");
	private static final By CART_DISCOUNT_TEXT = By.cssSelector("div.opc-block-summary>div.table-totals-wrapper>table>tbody>tr.total-rules");
	private static final By CART_DISCOUNT_PRICE = By.cssSelector("div.opc-block-summary>div.table-totals-wrapper>table>tbody>tr.total-rules>td");
	private static final By COD_DELIVERY_FEE = By.cssSelector("#opc-sidebar>div:nth-child(1)>div:nth-child(4)>table>tbody>tr.totals.cash-on-delivery-fee>td>span");
	private static final By COD_DELIVERY_TEXT = By.cssSelector("#opc-sidebar>div:nth-child(1)>div:nth-child(4)>table>tbody>tr.totals.cash-on-delivery-fee");
	public static float actualGrandTotal, orderSummaryGrandTotal, expectedGrandTotal, discountPrice, sub_total, subtotal_discount, sticks_amt, acc_amt, bundle_amt, devices_amt;
	public float delivery_fee_amt,cartDiscountPrice;
	private static final By CHECKOUT_PAGE_HEADER = By.cssSelector("h1.page-title");
	public static String[] shippingAddress1,shippingAddress2,delivery_time, cod_delivery_fee, selectedDeliverytext, courier_delivery, acc_price, sticks_price, bundle_price, devices_price;
	private static final By STANDARD_DELIVERY_BUTTON = By.cssSelector("#label_method_standard_delivery__2_to_5_working_days__matrixrates");
	public static String currentDate, imgName, text, checkout_delivery_time, selectedAddress, selectedPaymentText, expectedProductCount, deliveryDate, checkout_grandtotal;
	private static final By ORDER_SUMMARY_PRICES = By.cssSelector("#opc-sidebar>div:nth-child(1)>div:nth-child(4)>table");
	private static final By DEDUCTIONS = By.cssSelector("#opc-sidebar>div:nth-child(1)>div:nth-child(4)>table>tbody>tr.deductions-block>th>span.heading");
	private static final By PROMOTION_NOTE = By.cssSelector("#opc-sidebar>div:nth-child(1)>div:nth-child(4)>table>tbody>tr.deductions-block>th>span.block-hint");
	private static final By TAX_INCLUDED_NOTE = By.cssSelector("#opc-sidebar>div:nth-child(1)>div:nth-child(4)>table>tbody>tr.grand.totals.incl>td>p.price");
	private static final By SHIPPING_ADDRESS_HEADER = By.cssSelector("#shipping>div.step-title");
	private static final By DISCOUNT_HEADER = By.cssSelector("h3.checkout-payment-method__heading");
	private static final By DISCOUNT_NOTE = By.cssSelector(".discount-note");
	private static final By SHIPPING_ADDRESS = By.cssSelector("#shipping");
	private static final By ADDRESS_LIST = By.cssSelector("div.field.addresses.address-list__container");
	private static final By FLUID_ADDRESS = By.cssSelector("div.os-content>div.shipping-address-item:nth-child(2)");
	private static final By FLUID_RADIO_BUTTON = By.cssSelector("div.os-content>div.shipping-address-item:first-child");
	private static final By FLUID_ADDRESS_CONTACT = By.cssSelector("div.os-content>div.shipping-address-item:first-child>div");
	private static final By OCR_ADDRESS_CONTACT = By.cssSelector("div.os-content>div.shipping-address-item:last-child>div");
	private static final By OCR_ADDRESS = By.cssSelector("div.os-content>div.shipping-address-item:last-child");
	private static final By OCR_RADIO_BUTTON = By.cssSelector("div.os-content>div.shipping-address-item:last-child[class*='disabled']");
	private static final By OCR_ADDRESS_ERROR = By.cssSelector("span.ocr-not-message");
	private static final By OCR_STATIC_MESSAGE = By.cssSelector("div.ocr.checkout-static-message>span");
	private static final By SAVE_SHIPPING_ADDRESS_FORM1 = By.cssSelector("button#btnsub");
	private static final By SAVE_SHIPPING_ADDRESS_FORM2 = By.cssSelector("button#btnSave[type='submit']");
	private static final By DISCLAIMER_CHECKBOX = By.cssSelector("input#chkdisclaimer");
	private static final By SHIPPING_ADDRESS_2 = By.cssSelector("div.no_address_found.ocr_address_missing");
	private static final By NO_ADDRESS_PRESENT = By.cssSelector("div.no_address_found");
	private static final By ZIPCODE1 = By.cssSelector("input#inZipcode1");
	private static final By ZIPCODE2 = By.cssSelector("input#inZipcode2");
	private static final By PHONE_NUMBER_JP = By.cssSelector("input#Phonenumber");
	private static final By UPLOAD_FRONT_PAGE = By.cssSelector("input#image-front-file-input");
	private static final By UPLOAD_BACK_PAGE = By.cssSelector("input#image-back-file-input");
	private static final By PROOF_LIST = By.cssSelector("select#ddlPhotoTypeId");
	private static final By SHIPPING_ADDRESS_NAME = By.cssSelector("#shipping>div.step-content>div.selected-address__container>h4");
	private static final By SHIPPING_ADDRESS_PHONE = By.cssSelector("#shipping>div.step-content>div.selected-address__container>p.selected-address__phone");
	private static final By SHIPPING_ADDRESS_ADDRESS = By.cssSelector("#shipping>div.step-content>div.selected-address__container>p.selected-address__address");
	public static final By PAYMENT_METHODS = By.cssSelector("div.payment-group");
	private static final By CVV_HINT1 = By.cssSelector("div.cvv-hint>span:first-child");
	private static final By CVV_HINT2 = By.cssSelector("div.cvv-hint>span:last-child");
	private static final By PAYMENT_RADIO_BUTTON = By.cssSelector("div.payment-method._active>div.payment-method-title>label>span:nth-child(1)");
	private static final By COD_FEE = By.cssSelector(".cashondelivery_price");
	public static final By COURIER_DELIVERY_FEE = By.cssSelector("td.col.col-price");
	private static final By FLUID_UPDATE_ADDRESS_LINK = By.cssSelector("a.action.update-address-fluid");
	private static final By FLUID_UPDATE_ADDRESS_LINK_JP = By.cssSelector("a.action.update-address-fluid");
	private static final By UPDATE_ADDRESS2_LINK = By.cssSelector("a.action.update-address-ocr");
	private static final By ADDRESS1_STATUS = By.cssSelector("div.shipping-address-item:first-child>div.update-action-status>div.addr-status>span");
	private static final By ADDRESS2_STATUS = By.cssSelector("div.shipping-address-item:last-child>div.update-action-status>div.addr-status>span");
	private static final By CHECKOUT_PRODUCT_LIST = By.cssSelector("div.content.minicart-items>div>div.os-padding>div>div>ol>li>div.product");
	private static final By PRODUCT_LIST_QTY = By.cssSelector("span.heading-count>span:nth-child(1)");
	private static final By DELIVERY_DATE_TIME_ERROR = By.cssSelector("div.payment-method._active>div.payment-method-content * div.primary>div.custom-error");
	private static final By CHECKOUT_BUTTON = By.cssSelector("div.bat-cartsummary-button>button");
	private String main_body_colour, side_body_colour, checkout_main_body_colour, checkout_side_body_colour;
	public static final By PAYMENT_CANCEL = By.cssSelector("div>table>tbody>tr:last-child>td:last-child>a");
	public static final List<Object> checkoutProducts = new ArrayList<>();
	private static final By ORDER_SUMMARY_LINE_ITEMS = (By.cssSelector("div.opc-block-summary>div.table-totals-wrapper>table.data.table.table-totals>tbody"));
	private static final By ORDER_SUMMARY_LINE_ITEMS_PRICE = (By.cssSelector("div.opc-block-summary>div.table-totals-wrapper>table.data.table.table-totals>tbody>tr>td"));
	private static String frontPagePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"main"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"uploadFiles"+System.getProperty("file.separator")+"frontPage.png";
	private static String backPagePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"main"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"uploadFiles"+System.getProperty("file.separator")+"backPage.png";
	public static final By COURIER_DELIVERY_METHOD = By.cssSelector("[value='matrixrates_courier_delivery__select_a_time_and_day_for_delivery_']");
	public static final By MAIL_BIN_DELIVERY_METHOD=By.cssSelector("[value='mailbinshipping_mailbinshipping']");
	public static final By GMO_PAYMENT_METHOD=By.cssSelector("div.payment-method._active div.payment-method-title.field.choice > label.label");
	public final static By FREE_GIFT_OPTION_GLOPL = By.cssSelector("div.ampromo-popup-container");
	public final static By FREE_GIFT_MESSAGE = By.cssSelector("div.ampromo-overlay.-show > div > h2 > div > p");
	public final static By FREE_PACK = By.cssSelector("div.free_items_wrapper");
	public final static By FREE_PACK_POPUP = By.cssSelector("#html-body > div.page-wrapper > div.ampromo-overlay.-show > div");
	public final static By FAST_FINDER_ADDRESS_FIELD=By.cssSelector("input#address-search");

	public void selectCardPaymentMethod() {
		waitForExpectedElement(cardPaymentMethodCheckbox, 20);
		clickByElementByQueryJSExecutor(cardPaymentMethodCheckbox);
	}

	private void enterCardHolderName(String creditCardHolderName) {
		waitForExpectedElement(creditCardHolderNameField).sendKeys(creditCardHolderName);
	}

	private void enterCustomCreditCardNumber(String creditCardNumber) {
		waitForExpectedElement(creditCardNumberField).sendKeys(creditCardNumber);
	}

	private void enterCreditCardExpiryDate() {
		waitForExpectedElement(creditCardExpirationDateMonthDropDown);
		selectValueFromDropDownByby("05 - May", creditCardExpirationDateMonthDropDown);
		selectValueFromDropDownByby("2025", creditCardExpirationDateYearDropDown);
	}

	public void enterCreditCardVerificationNumber() throws InterruptedException {
		waitForExpectedElement(creditCardCVVField).sendKeys("123");
		Thread.sleep(2000);
	}

	public void enterValidMasterOrVisaCardDetailsAndSubmit() throws InterruptedException {
		enterCardHolderName(random(8, ALPHABETS));
		enterCustomCreditCardNumber("5555555555554444");
		enterCreditCardExpiryDate();
		enterCreditCardVerificationNumber();
	}

	public void selectSavedCardTypeCheckbox() {
		waitForExpectedElement(savedCardTypeCheckbox, 20);
		clickByElementByQueryJSExecutor(savedCardTypeCheckbox);
	}

	public void selectUsersSavedCard() {
		waitForExpectedElement(usersSavedCardCheckbox, 20);
		clickByElementByQueryJSExecutor(usersSavedCardCheckbox);
	}

	public void selectCreditCardCheckbox() {
		switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
			case GLODE:
			case VELODE:
				waitForElementToAppearAndDisappear(LOADER,15,10);
				clickByElementByQueryJSExecutor(CREDIT_CARD_RADIO_BTN_GLODE);
				break;
			default:
				waitForExpectedElement(creditCardCheckbox, 20);
				clickByElementByQueryJSExecutor(creditCardCheckbox);
		}
	}

	public void selectDebitCardCheckbox() {
		waitForExpectedElement(debitCardCheckbox, 20);
		clickByElementByQueryJSExecutor(debitCardCheckbox);
	}

	public void selectOtherPaymentMethodCheckbox() {
		waitForExpectedElement(otherPaymentMethodCheckbox, 20);
		clickByElementByQueryJSExecutor(otherPaymentMethodCheckbox);
	}

	public void selectPayPalCheckbox() {
		waitForExpectedElement(paypalCheckbox, 10);
		clickByElementByQueryJSExecutor(paypalCheckbox);
	}

	public void selectTermsAndConditionCheckbox() {
		waitForExpectedElement(termsconditonscheckbox, 20);
		clickByElementByQueryJSExecutor(termsconditonscheckbox);
	}

	public void selectTermsAndCondtionsCard() {
		waitForExpectedElement(termsConditonCardCheckbox, 20);
		clickByElementByQueryJSExecutor(termsConditonCardCheckbox);
	}

	public void selectBuyNowButtonFromCard() {
		switch (UrlBuilder.getLocale()) {
			case "kz":
				clickByElementByQueryJSExecutor(PLACE_ORDER_BUTTON_CARD_GLOIT_KZ);
				break;
			case "velobe":
				paymentPage.thereIsConsentCheckbox();
				paymentPage.thereIsLinkToTermsAndConditionsPolicy();
				clickByElementByQueryJSExecutor(PLACE_ORDER_BUTTON_CARD_GLOIT_KZ);
				break;
			default:
				if (UrlBuilder.getLocale().contains("it")) {
					waitForExpectedElement(PLACE_ORDER_BUTTON_CARD_GLOIT, 10).click();
				} else if (UrlBuilder.getLocale().contains("de") || UrlBuilder.getLocale().contains("pl")||UrlBuilder.getLocale().contains("velode")||UrlBuilder.getLocale().contains("epok")) {
					paymentPage.clickPlaceOrder();
				}
				else {
					clickByElementByQueryJSExecutor(PLACE_ORDER_BUTTON_CARD);
			}

		}
	}

	public void selectPayU_PAYMENT() {
		waitForAjaxElementNotToBePresent(getWebDriver(), 10);
		waitForExpectedElement(PAYU_PAYMENT, 25).isEnabled();
		if(!(UrlBuilder.getLocale().equals("pl")||UrlBuilder.getLocale().equals("velopl")))
			clickByElement(PAYU_PAYMENT);
		if(UrlBuilder.isIpad()||UrlBuilder.isIPhone()) {
			waitForAjaxElementNotToBePresent(getWebDriver(), 20);
			scrollElementIntoView(M_PAYU_VISACARD_PAYMENT_IPAD_GLOPL);
			waitForItemToBeClickableAndClick(M_PAYU_VISACARD_PAYMENT_IPAD_GLOPL, 20);
		}
		else clickByElementByQueryJSExecutor(PAYU_VISACARD_PAYMENT);
		clickByElementByQueryJSExecutor(PAYU_PAYMENT_TERMS_AND_CONDITION);
		goToTermsAndConditionsPageAndTakeScreenshot();
		if(UrlBuilder.isIpad()){
			waitForExpectedElement(PLACE_ORDER_BUTTON_CARD,15);
			switch (UrlBuilder.getLocale()) {
				case "velopl":
					clickByElementByQueryJSExecutor(PLACE_ORDER_BUTTON_CARD_GLOIT_KZ);
					break;
				default:
					clickByElementByQueryJSExecutor(PLACE_ORDER_BUTTON_CARD);
			}
		}
		else {
			switch (UrlBuilder.getLocale()) {
				case "pl":
				case "velopl":
					waitForExpectedElement(PLACE_ORDER_BUTTON_CARD, 15);
					clickByElementByQueryJSExecutor(PLACE_ORDER_BUTTON_CARD);
			}
		}
		paymentPage.waitForLoaderToDisapear();
	}

	public void payThroughPayU_PAYMENT() {
		waitForExpectedElement(BUTTON_PAYU_PAYMENT_SUBMIT, 10);
		waitForAjaxElementNotToBePresent(getWebDriver(),20);
		paymentPage.waitForLoaderToDisapear();
		waitForExpectedElement(PAYU_PAYMENT_CARD_NUMBER,5).click();
		waitForExpectedElement(PAYU_PAYMENT_CARD_NUMBER).clear();
		waitForExpectedElement(PAYU_PAYMENT_CARD_NUMBER).sendKeys("4444333322221111");
		waitForExpectedElement(PAYU_PAYMENT_CARD_EXPIRYDATE).click();
		waitForExpectedElement(PAYU_PAYMENT_CARD_EXPIRYDATE).clear();
		waitForExpectedElement(PAYU_PAYMENT_CARD_EXPIRYDATE).sendKeys("06/2322");
		waitForExpectedElement(PAYU_PAYMENT_CARD_CVV, 5).click();
		waitForExpectedElement(PAYU_PAYMENT_CARD_CVV, 5).clear();
		waitForExpectedElement(PAYU_PAYMENT_CARD_CVV, 5).sendKeys("123");
		clickByElement(BUTTON_PAYU_PAYMENT_SUBMIT);
		clickByElement(BUTTON_CONTINUE_SHOPPING);
		waitForExpectedElement(CHECKOUT_SUCCESS_SECTION,60);
	}

	public void userClickOnPayuMbankPymentOption() {
		selectPayU_mbankPAYMENT();
		userClickOnSubmitBankSimulator();
	}

	public void userClickOnPayuBlikPymentOption() {
		selectPayU_blikPAYMENT();
		userClickOnSubmitBlikBankSimulator();
		userClickOnLogoutBankSimulator();
	}

	public void userClickOnLogoutBankSimulator(){
		waitForExpectedElement(CLICK_LOGOUT_BANK_SIMULATOR_BTN,20);
		clickByElementByQueryJSExecutor(CLICK_LOGOUT_BANK_SIMULATOR_BTN);
		waitForExpectedElement(CHECKOUT_SUCCESS_SECTION,20);
	}

	public void userClickOnSubmitBankSimulator(){
		waitForExpectedElement(CLICK_GO_TO_BANK_BTN,20);
		clickByElementByQueryJSExecutor(CLICK_GO_TO_BANK_BTN);
		waitForExpectedElement(CLICK_SUBMIT_BANK_SIMULATOR_BTN,20);
		clickByElementByQueryJSExecutor(CLICK_SUBMIT_BANK_SIMULATOR_BTN);
		waitForExpectedElement(POSITIVE_AUTH_MBANK,10);
		clickByElementByQueryJSExecutor(POSITIVE_AUTH_MBANK);
		waitForExpectedElement(CHECKOUT_SUCCESS_SECTION,20);
	}

	public void userClickOnSubmitBlikBankSimulator(){
		waitForExpectedElement(CLICK_GO_TO_BANK_BTN_BLIK,20);
		try {
			clickByElementByQueryJSExecutor(CLICK_GO_TO_BANK_BTN_BLIK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		waitForExpectedElement(CLICK_SUBMIT_BANK_SIMULATOR_BTN_VELOPL,20);
		clickByElementByQueryJSExecutor(CLICK_SUBMIT_BANK_SIMULATOR_BTN_VELOPL);
		if (UrlBuilder.getLocale().equals("velopl") || UrlBuilder.getLocale().equals("pl")) {
			waitForExpectedElement(TRANSACTION_CONFIRMATION_BLINK_VELOPL, 20);
			clickByElementByQueryJSExecutor(TRANSACTION_CONFIRMATION_BLINK_VELOPL);
			waitForExpectedElement(CLICK_LOGOUT_BANK_SIMULATOR_BTN, 20);
			clickByElementByQueryJSExecutor(CLICK_LOGOUT_BANK_SIMULATOR_BTN);
		} else {
			waitForExpectedElement(TRANSACTION_CONFIRMATION_BLINK, 20);
			clickByElementByQueryJSExecutor(TRANSACTION_CONFIRMATION_BLINK);
		}
	}

	public void selectPayU_mbankPAYMENT() {
		waitForExpectedElement(PAYU_PAYMENT, 30).isEnabled();
		if(!(UrlBuilder.getLocale().equals("pl")||UrlBuilder.getLocale().equals("velopl")))
			clickByElement(PAYU_PAYMENT);
		clickByElementByQueryJSExecutor(SELECT_MBANK_PAYMENT_OPTION);
		clickByElementByQueryJSExecutor(PAYU_PAYMENT_TERMS_AND_CONDITION);
		if (UrlBuilder.getLocale().equals("velopl")) {
			clickByElement(PLACE_ORDER_BUTTON_CARD_VELOPL);
		} else {
			clickByElement(PLACE_ORDER_BUTTON_CARD);
		}
	}

	public void selectPekao_PAYMENT() {
		waitForExpectedElement(PAYU_PAYMENT, 30).isEnabled();
		if(!(UrlBuilder.getLocale().equals("pl")||UrlBuilder.getLocale().equals("velopl")))
			clickByElement(PAYU_PAYMENT);
		clickByElementByQueryJSExecutor(SELECT_BANK_PEKAO_PAYMENT_OPTION);
		clickByElementByQueryJSExecutor(PAYU_PAYMENT_TERMS_AND_CONDITION);
		clickByElement(PLACE_ORDER_BUTTON_CARD_VELOPL);
	}

	public void selectIpko_PAYMENT() {
		waitForExpectedElement(PAYU_PAYMENT, 30).isEnabled();
		if(!(UrlBuilder.getLocale().equals("pl")||UrlBuilder.getLocale().equals("velopl")))
			clickByElement(PAYU_PAYMENT);
		clickByElementByQueryJSExecutor(SELECT_BANK_IPKO_PAYMENT_OPTION);
		clickByElementByQueryJSExecutor(PAYU_PAYMENT_TERMS_AND_CONDITION);
		clickByElement(PLACE_ORDER_BUTTON_CARD_VELOPL);
	}

	public void selectPayU_blikPAYMENT() {
		waitForExpectedElement(PAYU_PAYMENT, 30).isEnabled();
		if(!(UrlBuilder.getLocale().equals("pl")||UrlBuilder.getLocale().equals("velopl")))
			clickByElement(PAYU_PAYMENT);
		clickByElementByQueryJSExecutor(SELECT_BLIK_PAYMENT_OPTION);
		clickByElementByQueryJSExecutor(PAYU_PAYMENT_TERMS_AND_CONDITION);
		clickByElement(PLACE_ORDER_BUTTON_CARD);
	}

	public void selectAndPurchaseThroughPayU_PAYMENT() {
		selectPayU_PAYMENT();
		payThroughPayU_PAYMENT();
	}

	public void clickOnCouponCodeLink()  {
		waitForAjaxElementNotToBePresent(getWebDriver(),10);
		waitForExpectedElement(couponCodeLink, 30);
		clickByElementByQueryJSExecutor(couponCodeLink);
	}

	public String invalidPromoCodeError() {
		if(UrlBuilder.getLocale().equalsIgnoreCase("lyftse")) {
			try {
				return waitForExpectedElement(BasketPage.COUPONCODE_MESSAGE_LYFTSE).getText();
			} catch (Exception e) {
				return null;
			}
		}else
			return waitForExpectedElement(inValidPromoCodeSucessMsg,2).getAttribute("innerHTML");
	}

	public String validPromoCodeSucessMsg() {
		return waitForExpectedElement(validPromoCodeSucessMsg, 10).getText();
	}

	public void ClickOnRemoveDiscountButton() {
		if (isElementPresent(cancelDiscount)) {
			clickByElementByQueryJSExecutor(cancelDiscount);
			waitForExpectedElement(appyDiscountButton, 10);
		} else {
			try {
				waitForExpectedElement(appyDiscountButton, 10);
			} catch (Exception e) {
				LOG.info("Ërror message " + e);
			}
		}
	}

	public void ClickOnApplyDiscountButton() {
		switch (UrlBuilder.getLocale()){
			case "pl":
				waitForExpectedElement(appyDiscountButton, 10);
				clickUsingJS(appyDiscountButton);
		    break;
			case "glojp":
				waitForExpectedElement(appyDiscountButton).click();
			break;
		    default:
		waitForExpectedElement(appyDiscountButton, 10);
		clickUsingJS(appyDiscountButton);
		break;
	}
}
	public void selectSwitchAddressCheckbox() {
		waitForExpectedElement(switchAddressCheckbox, 20);
		clickByElementByQueryJSExecutor(switchAddressCheckbox);
	}

	public void promocode_error_Message() {
		waitForExpectedElement(PROMOTION_ERROR_MSG,10).isDisplayed();
	}

	public void cashOnDeliveryCheckBox() {
		waitForAjaxElementNotToBePresent(getWebDriver(), 10);
		if (!waitForExpectedElement(CHECKBOX_COD, 20).isSelected()) {
			try {
				waitForExpectedElement(CHECKBOX_COD,20).click();
			} catch (Exception e) {
				clickByElementByQueryJSExecutor(CHECKBOX_COD);
			}
		}
		switch (UrlBuilder.getLocale()){
			case "kz":
				break;
			case "pl":
				waitForExpectedElement(CHECKBOX_COD_TERMSANDCONDITION_GLO_PL,20);
				clickByElementByQueryJSExecutor(CHECKBOX_COD_TERMSANDCONDITION_GLO_PL);
				break;
			case "velopl":
				waitForExpectedElement(CHECKBOX_COD_TERMSANDCONDITION_VELO_PL,20);
				clickByElementByQueryJSExecutor(CHECKBOX_COD_TERMSANDCONDITION_VELO_PL);
				clickByElementByQueryJSExecutor(SUBMIT_COD_ORDER_VELO_PL);
				break;
		}
	}
	public void fillGuestCheckoutDetailForm(){
		switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
			case PL:
				populateAddressFieldsAndClickSaveButton();
				break;
			default:
				waitForExpectedElement(EMAIL_FIELD,10).sendKeys(emailAddressData);
				waitForExpectedElement(FIRSTNAME_FIELD).sendKeys(UrlBuilder.getMessage("FirstName.key"));
				waitForExpectedElement(LASTNAME_FIELD).sendKeys(UrlBuilder.getMessage("LastName.key"));
				populateAddressFieldsAndClickSaveButton();
		}
	}

	private void populateAddressFieldsAndClickSaveButton() {
		populateAddressFields();
		clickSaveShippingAddressButton();
	}

	public void populateAddressFields() {
		waitForExpectedElement(STREET_FIELD).sendKeys(UrlBuilder.getMessage("Street.key"));
		waitForExpectedElement(HOUSE_FIELD).sendKeys(UrlBuilder.getMessage("HouseNo.key"));
		waitForExpectedElement(POSTAL_CODE).sendKeys(UrlBuilder.getMessage("PostalCode.key"));
		waitClearAndEnterText(PHONE_NUMBER, UrlBuilder.getMessage("PhoneNumber.key"));
		waitForExpectedElement(CITY_FIELD).sendKeys(UrlBuilder.getMessage("City.key"));
		selectValueFromDropDownByIndex(3, REGION_FIELD);
	}

	public void clickSaveShippingAddressButton() {
		clickByElementByQueryJSExecutor(SAVE_BUTTON);
	}

	public void userClicksNewsletterSubscriptionOptionOnCheckout() {
		clickByElementByQueryJSExecutor(gloItHomePage.NEWSLETTER_SUBSCRIPTION_CHECKBOX_CHECKOUT);
	}

	public void addTwoFreeGiftsToCartAndAssertUserIsAllowedToCheckout() throws InterruptedException {
		gloItHomePage.OnMiniCartclickOnProceedToCheckoutButton();
		waitForAjaxElementNotToBePresent(getWebDriver(),10);
		waitForExpectedElement(orderSummaryHeading, 10).isDisplayed();
	}

	public void addFreeConsumablePackToCartAndAssertUserIsAllowedToCheckout() {
		switch (UrlBuilder.getLocale()){
			case "pl":
			waitForExpectedElement(gloItHomePage.BUTTON_GIFT_ITEMS_ADDTOCART_FIRST,30);
			clickFirstElementByQueryJSExecutor(gloItHomePage.BUTTON_GIFT_ITEMS_ADDTOCART_FIRST);
			waitForElementToDisappear(gloItHomePage.COUNTER_QTY_LOADER, 30);
			clickByElementByQueryJSExecutor(PROCEED_CHECKOUT_FROM_VIEW_BASKET);
			waitForAjaxElementNotToBePresent(getWebDriver(), 20);
			waitForExpectedElement(orderSummaryHeading, 30).isDisplayed();
			break;
			default:
				waitForExpectedElement(MINICART_BACK_BUTTON).click();
				clickFirstElementByQueryJSExecutor(gloItHomePage.BUTTON_GIFT_ITEMS_ADDTOCART_FIRST);
				waitForElementToDisappear(gloItHomePage.COUNTER_QTY_LOADER, 10);
				waitForItemToBeClickableAndClick(PROCEED_CHECKOUT_FROM_VIEW_BASKET, 10);
				waitForAjaxElementNotToBePresent(getWebDriver(), 10);
				waitForExpectedElement(orderSummaryHeading, 10).isDisplayed();
		}
	}

	public void addFreeConsumablePackToCartAndAssertUserIsNotAllowedToCheckout() {
		waitForExpectedElement(FREE_GIFT_OPTION_GLOPL,10);
		waitForExpectedElement(ADD_FREE_GIFT_BUTTON, 8);
		clickIndexElementByQueryJSExecutor(HomePage.ADD_GIFT_OFFER,1);
		assertTrue(getWebDriver().findElements(HomePage.ADD_FREE_GIFT_BUTTON_DISABLED).size()>0);
	}

	public void clickOnProceedToCheckoutButtonForGuestUser() {
		try {
			waitForAjaxElementNotToBePresent(getWebDriver(),10);
			waitForExpectedElement(gloItHomePage.PROCEED_TO_CHECKOUT_BUTTON_PL, 20).click();
		} catch (Exception ex) {
			LOG.info(
					"Failed to click on Proceed to Checkout button from basket window due to exception: "
							+ ex.getMessage());
			clickByElementByQueryJSExecutor(gloItHomePage.BASKET_ICON);
			waitForExpectedElement(gloItHomePage.PROCEED_TO_CHECKOUT_BUTTON_PL, 5);
			clickByElementByQueryJSExecutor(gloItHomePage.PROCEED_TO_CHECKOUT_BUTTON_PL);
		}
	}

	public String returnPageHeading() {
		return waitForExpectedElement(successPageHeading).getText();
	}

	public void GloIt_assert_ThankYou_Is_Displayed(String expectedMessage) {
		waitForElementToAppearAndDisappear(LOADER,5,20);
		assertThat(returnPageHeading()).contains(UrlBuilder.getMessage(expectedMessage));
	}

	public void userNavigatesToMiniCartWithCouponAppliedAndCheckout(String strCoupon) throws Throwable {
		Boolean blnCouponValid = (Boolean) scenarioContext.getContext(COUPON_PRODUCT_URL_ISVALID);
		if(blnCouponValid) {
			clickByElementByQueryJSExecutor(GLO_BASKET_ICON);
			clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("viewBasketText.key")));
			waitForAjaxElementNotToBePresent(getWebDriver(),5);
			assertTrue(waitForExpectedElement(CANCEL_APPLIED_COUPON,10).isDisplayed());
			clickByElementByQueryJSExecutor(PROCEED_CHECKOUT_FROM_VIEW_BASKET);
			waitForAjaxElementNotToBePresent(getWebDriver(),10);
			homePage.addFreeGiftAccordingToDevice();
			homePage.clickOnProceedToCheckoutButton();
		}
	}

/*	public void navigateToCheckout() {
		waitForExpectedElement(CHECKOUT_BUTTON, 30);
		try {
			clickByElementByQueryJSExecutor(CHECKOUT_BUTTON);
		} catch (Exception e) {
			clickByElement(CHECKOUT_BUTTON);
		}
		gloItProductListPage.waitForLoaderToDisappear();
		if (!waitForExpectedElement(CHECKOUT_PAGE_HEADER).isDisplayed()) {
			refreshBrowser();
			gloItProductListPage.waitForLoaderToDisappear();
		}
		waitForExpectedElement(CHECKOUT_PAGE_HEADER, 20).isDisplayed();
	}*/

	public boolean isTermsAndConditionsBlockPresent() {
		return waitForExpectedElement(TERMS_BLOCK).isDisplayed();
	}

	public void verifyTermsAndConditionsTitle() {
		assertEquals(webDriver.findElement(TERMS_TITLE).getText(), getMessage("termsTitle.key"));
	}

	public boolean isGloContactsBlockPresent() {
		return waitForExpectedElement(GLO_CONTACTS_BLOCK).isDisplayed();
	}

	public boolean isVeloContactsBlockPresent() {
		return waitForExpectedElement(VELO_CONTACTS_BLOCK).isDisplayed();
	}

	public void verifyGloContactsTitle() {
		assertEquals(waitForExpectedElement(GLO_TITLE).getText(), getMessage("gloTitle.key"));
	}

	public void verifyVeloContactsTitle() {
		assertEquals(waitForExpectedElement(VELO_TITLE).getText(), getMessage("veloTitle.key"));
	}

	public boolean placeOrderButtonIsEnabled() {
		return waitForExpectedElement(PLACE_ORDER_BUTTON,40).isEnabled();
	}

	public boolean placeCardOrderButtonIsEnabled() {
		return waitForExpectedElement(CARD_PLACE_ORDER_BUTTON, 60).isEnabled();
	}

	public void clickCODOnPlaceOrder() {
		try {
			scrollToElement(PLACE_ORDER_BUTTON);
			waitForExpectedElement(PLACE_ORDER_BUTTON,20).click();
			IsPageLoaded.waitAllRequest();
		} catch (Exception e) {
			clickByElementByQueryJSExecutor(PLACE_ORDER_BUTTON);
		}
	}

	public void clickCODPaymentOption() {
		clickByJavaScriptExecutor(waitForExpectedElement(COD_PAYMENT,10));
		selectedPaymentText = waitForExpectedElement(PAYMENT_RADIO_BUTTON).getText();
		gloItProductListPage.waitForLoaderToDisappear();
	}

	public void clickCardPaymentOption() {
		clickByJavaScriptExecutor(waitForExpectedElement(CARD_PAYMENT,30));
		selectedPaymentText = waitForExpectedElement(PAYMENT_RADIO_BUTTON).getText();
	}

	public void clickOnCardPlaceOrder() {
		try {
			waitForExpectedElement(CARD_PLACE_ORDER_BUTTON, 20).click();
		} catch (Exception e) {
			waitForExpectedElement(CARD_PLACE_ORDER_BUTTON, 20);
			clickByElementByQueryJSExecutor(CARD_PLACE_ORDER_BUTTON);
		}
	}

	public void enterCreditCardDetails() {
		waitForExpectedElement(CARD_NUMBER, 10).sendKeys(getMessage("validCardNumber.key"));
		selectValueFromDropDownByIndex(2, MONTH_DD);
		selectValueFromDropDownByIndex(3, YEAR_DD);
		waitForExpectedElement(CVV_NUMBER, 10).sendKeys(getMessage("validSecurityCode.key"));
	}

	public void enterInvalidCreditCardDetails() {
		waitForExpectedElement(CARD_NUMBER, 10).sendKeys(getMessage("InvalidVisaCreditCardNumber.key"));
		selectValueFromDropDownByIndex(1, MONTH_DD);
		selectValueFromDropDownByIndex(1, YEAR_DD);
		waitForExpectedElement(CVV_NUMBER, 10).sendKeys(getMessage("InvalidVisaSecurityCode.key"));
	}

	public void clickTermsAndConditions() {
		waitForExpectedElement(TERMS_AND_CONDITIONS);
		clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS);
	}

	public boolean isOrderSummaryDisplayed() {
		try {
			waitForExpectedElement(GloItCheckoutPage.ORDER_SUMMARY_BLOCK,20);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void totalCartValueAndItemsCount() {
		waitForExpectedElement(TOTAL_CHECKOUT_VALUE, 30);
		softAssert.assertTrue(waitForExpectedElement(TOTAL_ITEMS).isDisplayed());
		softAssert.assertTrue(waitForExpectedElement(TOTAL_CHECKOUT_VALUE).isDisplayed());
		softAssert.assertAll();
	}

	public void verifyProductListIsDisplayed() {
		try {
			waitForItemToBeClickableAndClick(EXPAND_PRODUCT_LIST_ICON,30);
		} catch (Exception e) {
			try {
				clickByElementByQueryJSExecutor(EXPAND_PRODUCT_LIST_ICON);
			} catch (Exception exception) {
				retryingFindClick(EXPAND_PRODUCT_LIST_ICON);
			}
		}
		waitForExpectedElement(PRODUCT_LIST,30);
		Assert.assertTrue(waitForExpectedElement(PRODUCT_LIST).isDisplayed());
	}

	public void collapseProductList() {
		waitForItemToBeClickableAndClick(getWebDriver(), 30, COLLAPSE_PRODUCT_LIST_ICON);
		waitForExpectedElement(EXPAND_PRODUCT_LIST_ICON).isDisplayed();
	}

	public void isNamePriceQuantityDisplayed() {
		List<WebElement> productList = waitForExpectedElements(ORDER_SUMMARY_PRODUCT_LIST);
		for (WebElement product : productList) {
			softAssert.assertTrue(product.findElement(PRODUCT_NAME).isDisplayed());
			softAssert.assertTrue(product.findElement(PRODUCT_QTY).isDisplayed());
			softAssert.assertTrue(product.findElement(PRODUCT_PRICE).isDisplayed());
			softAssert.assertTrue(product.findElement(PRODUCT_IMG).isDisplayed());
		}
		softAssert.assertAll();
	}

	public void assertProductsAddedWithCheckout() {
		assertEquals(checkoutProducts.size(), gloItProductListPage.productsAdded.size());
		assertEquals(gloItProductListPage.productsAdded, checkoutProducts);
		waitForPage();

		try {
			isProductImageDisplayed(PRODUCT_IMG);
		} catch (Exception e) {
			try {
				LOG.info("First attemp failed - sleeping for 6 seconds");
				Thread.sleep(6000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			isProductImageDisplayed(PRODUCT_IMG);
		}

	}

	public void isProductImageDisplayed(By elements) {
		waitForPage();
		boolean isPresent;
		int index = 0;
		List<WebElement> productsList = presenceOfAllElementsLocatedBy(elements);
		if (productsList.size() == 0) {
			fail("Product images are not displayed");
		} else {
			for (WebElement productImg : productsList) {
				jsScrollElementInCenter(productsList.get(index));
				waitForExpectedElement(elements, 50);
				isPresent = productImg.isDisplayed();
				if (String.valueOf(isPresent).equals("false")) {
					refreshBrowser();
					productsList = presenceOfAllElementsLocatedBy(elements);
					jsScrollElementInCenter(productsList.get(index));
					waitForExpectedElement(elements, 30);
				}
				Assert.assertTrue(productsList.get(index).isDisplayed(), "Product images are not displayed");
			}
			index++;
		}
	}

	public void getCheckoutProductDetails() {
		int count = 0;
		while (count < 3) {
			try{
				checkoutProducts.clear();
				List<WebElement> details = waitForExpectedElements(ORDER_SUMMARY_PRODUCT_LIST);
				for (WebElement data : details) {
					checkoutProducts.add(Arrays.asList(data.findElement(PRODUCT_NAME).getText(),
							data.findElement(PRODUCT_PRICE).getText(), data.findElement(PRODUCT_QTY).getText()));
				}
				break;
			}
			catch (StaleElementReferenceException ste){
				count++;
			}
			catch (Exception e){
				LOG.error(ExceptionUtils.getStackTrace(e));
				break;
			}
		}
	}
	public void hyperPlusMainBodyColourOnCheckout() {
		checkout_main_body_colour = waitForExpectedElement(MAIN_BODY_COLOR).getText();
	}

	public void hyperPlusSideBodyColourOnCheckout() {
		checkout_side_body_colour = waitForExpectedElement(SIDE_BODY_COLOR).getText();
	}

	public void hyperPlusColoursOnOrderDetails() {
		main_body_colour = waitForExpectedElement(ORDER_MAIN_BODY_COLOR).getText();
		side_body_colour = waitForExpectedElement(ORDER_SIDE_BODY_COLOR).getText();
	}

	public void assertHyperPlusColoursOnCheckout() {
		hyperPlusMainBodyColourOnCheckout();
		hyperPlusSideBodyColourOnCheckout();
		assertTrueWithCustomError(HyperPlusPage.main_colour, checkout_main_body_colour);
		assertTrueWithCustomError(HyperPlusPage.side_colour, checkout_side_body_colour);
	}

	public boolean isProductNameDisplayed() {
		return waitForExpectedElement(PRODUCT_NAME).isDisplayed();
	}

	public boolean isProductQtyDisplayed() {
		return waitForExpectedElement(PRODUCT_QTY).isDisplayed();
	}

	public boolean isProductPriceDisplayed() {
		return waitForExpectedElement(PRODUCT_PRICE).isDisplayed();
	}

	public void assertUpdatedPriceOnCheckout() {
		String[] price = waitForExpectedElement(PRODUCT_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
		Assert.assertEquals(OrderHistoryPage.updatedPrice, Float.parseFloat(price[0]));
	}

	public void assertUpdatedQuantityOnCheckout() {
		assertEquals(OrderHistoryPage.updatedQty, waitForExpectedElement(PRODUCT_QTY).getAttribute("value"));
	}

	public void getProductsCountOnCheckout() {
		List<WebElement> productList = waitForExpectedElements(CHECKOUT_PRODUCT_LIST);
		expectedProductCount = String.valueOf(productList.size());
	}

	public void assertProductCountOnCheckout() {
		assertEquals(expectedProductCount, String.valueOf(gloItProductListPage.productsAdded.size()), waitForExpectedElement(PRODUCT_LIST_QTY).getText());
	}

	public void assertOrderSummaryTotalWithCheckoutGrandTotal() {
		getActualGrandTotal();
		assertEquals(orderSummaryGrandTotal, actualGrandTotal);
	}

	public void getActualGrandTotal() {
		waitForExpectedElement(TOTAL_CHECKOUT_VALUE);
		checkout_grandtotal = waitForExpectedElement(TOTAL_CHECKOUT_VALUE).getText();
		String[] checkoutTotal = checkout_grandtotal.split(getMessage("JapaneseCurrencySymbol.key"));
		actualGrandTotal = Float.parseFloat(checkoutTotal[0].replaceAll("[\\D]", ""));
	}

	public void selectCourierDeliveryOption() throws StaleElementReferenceException {
		try{
		clickByJavaScriptExecutor(waitForExpectedElement(COURIER_DELIVERY_TEXT));
		gloItProductListPage.waitForLoaderToDisappear();
		Assert.assertTrue(waitForExpectedElement(COURIER_DELIVERY_RADIO_BUTTON,20).isSelected());
		selectedDeliverytext = waitForExpectedElement(COURIER_DELIVERY_TEXT).getText().split("\\(");
		IsPageLoaded.waitAllRequest();
	}
		catch (StaleElementReferenceException ex)
		{
			refreshBrowser();
			IsPageLoaded.waitAllRequest();
			clickByJavaScriptExecutor(waitForExpectedElement(COURIER_DELIVERY_TEXT));
			IsPageLoaded.waitAllRequest();
		}
	}

	public void isCourierDeliveryOptionSelected() {
		Assert.assertTrue(waitForExpectedElement(COURIER_DELIVERY_RADIO_BUTTON,20).isSelected());
		selectedDeliverytext = waitForExpectedElement(COURIER_DELIVERY_TEXT).getText().split("\\(");
	}

	public void selectDeliveryDateTime() throws ParseException {
		selectDeliveryTime();
	}

	public boolean isSelectTimeDisplayed() {
		return waitForExpectedElement(TIME).isDisplayed();
	}

	public void selectDeliveryTime() {
		IsPageLoaded.waitAllRequest();
		waitForElementToDisappear(gloItProductListPage.loader,10);
		Select deliveryTime = new Select(waitForExpectedElement(TIME));
		deliveryTime.selectByIndex(1);
		delivery_time = deliveryTime.getFirstSelectedOption().getText().split("-");
	}

	public void convertTimeTo24hrFormat() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
		DateFormat formate = new SimpleDateFormat("HH:mm");
		Date time1;
		Date time2;
		checkout_delivery_time = "";
		time1 = dateFormat.parse(delivery_time[0]);
		time2 = dateFormat.parse(delivery_time[1]);
		checkout_delivery_time = formate.format(time1) + " - " + formate.format(time2);
	}

	public void selectStandardDeliveryOption() {
		gloItProductListPage.waitForLoaderToDisappear();
		waitForExpectedElement(STANDARD_DELIVERY_BUTTON);
		if (!waitForExpectedElement(STANDARD_DELIVERY_BUTTON).isSelected()) {
			clickByElementByQueryJSExecutor(STANDARD_DELIVERY_BUTTON);
		}
		selectedDeliverytext = waitForExpectedElement(STANDARD_DELIVERY_BUTTON).getText().split(" ");
	}

	public boolean isSelectDeliveryDateTimeErrorDisplayed() {
		jsScrollElementInCenter(waitForExpectedElement(DELIVERY_DATE_TIME_ERROR));
		return waitForExpectedElement(DELIVERY_DATE_TIME_ERROR).isDisplayed();
	}

	public boolean applyDiscountCodeModulePresent() {
		return waitForExpectedElement(APPLY_DISCOUNT_TEXT, 5).isDisplayed();
	}

	public void enterValidDiscountCode() {
		if (isElementPresent(CANCEL_COUPON_BUTTON)) {
			removeCouponCode();
		}
		waitClearAndEnterText(DISCOUNT_CODE, getMessage("validCouponCode.key"));
	}

	public void enterInValidDiscountCode() {
		waitForExpectedElement(DISCOUNT_CODE,20);
		scrollElementIntoView(DISCOUNT_CODE);
		waitClearAndEnterText(DISCOUNT_CODE, getMessage("inValidCouponCode.key"));
	}

	public boolean successMessageIsDisplayed() {
		return isElementPresent(gloItProductListPage.SUCCESS_MESSAGE,30);
	}

	public boolean removeCouponCodeOption() {
		waitForAjaxElementNotToBePresent(getWebDriver(), 10);
		return isElementPresent(CANCEL_COUPON_BUTTON,20);
	}

	public boolean couponCodeErrorMessageIsDisplayed() {
		return isElementPresent(ERROR_MESSAGE,20);
	}

	public void removeCouponCode() {
		waitForItemToBeClickableAndClick(CANCEL_COUPON_BUTTON);
		waitForAjaxElementNotToBePresent(getWebDriver(), 10);
	}

	public void getSubTotalForDiscount() {
		acc_amt = 0;
		devices_amt = 0;
		sticks_amt = 0;
		bundle_amt = 0;
		waitForExpectedElement(ORDER_SUMMARY_LINE_ITEMS);
		for (WebElement rowData : getTableRows(ORDER_SUMMARY_LINE_ITEMS)) {
			if (rowData.getText().contains(UrlBuilder.getMessage("ACCESSORIES_TEXT.key"))) {
				acc_price = rowData.findElement(ORDER_SUMMARY_LINE_ITEMS_PRICE).getText().split(UrlBuilder.getMessage("JapaneseCurrencySymbol.key"));
				acc_amt = Float.parseFloat(acc_price[0].replaceAll(",", ""));
			} else if (rowData.getText().contains(UrlBuilder.getMessage("STICKS_TEXT.key"))) {
				sticks_price = rowData.findElement(ORDER_SUMMARY_LINE_ITEMS_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
				sticks_amt = Float.parseFloat(sticks_price[0].replaceAll(",", ""));
			} else if (rowData.getText().contains(UrlBuilder.getMessage("DEVICE_TEXT.key"))) {
				devices_price = rowData.findElement(ORDER_SUMMARY_LINE_ITEMS_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
				devices_amt = Float.parseFloat(devices_price[0].replaceAll(",", ""));
			} else if (rowData.getText().contains(UrlBuilder.getMessage("BUNDLE_TEXT.key"))) {
				bundle_price = rowData.findElement(ORDER_SUMMARY_LINE_ITEMS_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
				bundle_amt = Float.parseFloat(bundle_price[0].replaceAll(",", ""));
			}
		}
		subtotal_discount = acc_amt + bundle_amt + devices_amt;
		sub_total = subtotal_discount + sticks_amt;
	}

	public void assertOrderSummaryTotal() {
		getSubTotalForDiscount();
		getCourierDeliveryFeeOnCheckout();
		cartDiscountPrice = 0;
		discountPrice = 0;
		float expectedDiscountPrice = 0;
		String[] grandTotal = getTextFor(GRAND_TOTAL).split(getMessage("JapaneseCurrencySymbol.key"));
		orderSummaryGrandTotal = Float.parseFloat(grandTotal[0].replaceAll(",", ""));
		if (isElementDisplayedBySeconds(CART_DISCOUNT_TEXT, 1)) {
			List<String> list= getWebDriver().findElements(By.xpath("//tr[@class=\"total-rules\"]//td[@class=\"amount\"]/span")).
					stream().filter(s->s.isDisplayed()).map(s1->(s1.getText().substring(1).split(getMessage("JapaneseCurrencySymbol.key"))[0].replaceAll(",","")))
					.collect(Collectors.toList());
			for (int i=0;i<list.size();i++){
				cartDiscountPrice += Float.parseFloat(list.get(i));
			}
		}else {
			expectedDiscountPrice = 0;
		}
		float cod_fee_amt;
		if (isElementDisplayedBySeconds(COD_DELIVERY_TEXT, 1)) {
			String[] cod_fee = getTextFor(COD_DELIVERY_FEE).split(getMessage("JapaneseCurrencySymbol.key"));
			cod_fee_amt = Float.parseFloat(cod_fee[0].replaceAll(",", ""));
		} else {
			cod_fee_amt = 0;
		}
		if (Float.parseFloat(courier_delivery[0]) > 0) {
			expectedGrandTotal = sub_total + Float.parseFloat(courier_delivery[0].replaceAll(",", "")) + cod_fee_amt - cartDiscountPrice - expectedDiscountPrice - discountPrice;
		} else {
			expectedGrandTotal = sub_total + cod_fee_amt - expectedDiscountPrice - discountPrice - cartDiscountPrice;
		}
		softAssert.assertEquals(orderSummaryGrandTotal, expectedGrandTotal);
		softAssert.assertAll();
	}

	public void CODFeeIsDisplayed() {
		gloItProductListPage.waitForLoaderToDisappear();
		if (waitForExpectedElement(COD_PAYMENT).isSelected()) {
			String[] delivery_fee = waitForExpectedElement(COD_DELIVERY_FEE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
			float delivery_fee_amt = Float.parseFloat(delivery_fee[0].replaceAll(",", ""));
			Assert.assertTrue(waitForExpectedElement(COD_DELIVERY_FEE).isDisplayed() && delivery_fee_amt > 0);
		} else {
			LOG.info("As CARD payment is selected, COD fee is not displayed");
		}
	}

	public void getCODFeeOnCheckout() {
		if (waitForExpectedElement(COD_PAYMENT).isSelected()) {
			cod_delivery_fee = waitForExpectedElement(COD_DELIVERY_FEE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
			delivery_fee_amt = Float.parseFloat(cod_delivery_fee[0].replaceAll(",", ""));
		}
	}

	public void getCourierDeliveryFeeOnCheckout() {
		waitForExpectedElement(DELIVERY_PRICE,10);
		courier_delivery = getTextFor(DELIVERY_PRICE).split(getMessage("JapaneseCurrencySymbol.key"));
	}

	public boolean orderSummaryPricesBreakdownIsDisplayed() {
		try {
			getWebDriver().findElement(GloItCheckoutPage.ORDER_SUMMARY_PRICES);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean discountHeadingIsDisplayed() {
		return waitForExpectedElement(DEDUCTIONS).isDisplayed();
	}

	public void verifyDiscountHeading() {
		assertEquals(waitForExpectedElement(DEDUCTIONS).getText(), getMessage("deductionHeading.key"));
	}

	public void verifyPromotionNoteDisplayed() {
		Assert.assertTrue(waitForExpectedElement(PROMOTION_NOTE).isDisplayed());
		assertEquals(waitForExpectedElement(PROMOTION_NOTE).getText(), getMessage("promotionNote.key"));
	}

	public void verifyTaxIncludedNote() {
		gloItProductListPage.waitForLoaderToDisappear();
		Assert.assertTrue(waitForExpectedElement(TAX_INCLUDED_NOTE).isDisplayed());
		assertEquals(waitForExpectedElement(TAX_INCLUDED_NOTE).getText(), getMessage("taxIncludedNote.key"));
	}

	public boolean deliveryFeeIsDisplayed() {
		return waitForExpectedElement(DELIVERY_PRICE_TEXT).isDisplayed();
	}

	public boolean totalAmountInOrderSummaryIsDisplayed() {
		return waitForExpectedElement(GRAND_TOTAL).isDisplayed();
	}

	public void assertShippingAddressHeader(String strExpectedText) {
		waitForAjaxElementNotToBePresent(getWebDriver(), 20);
		String strEmptyBasketText = waitForExpectedElement(SHIPPING_ADDRESS_HEADER,20).getText();
		assertTrueWithCustomError(strEmptyBasketText,(getMessage(strExpectedText)));
	}

	public void assertDiscountHeadingAndNote(String strExpectedText, String strExpectedText1) {
		String strDiscountHeading = waitForExpectedElement(DISCOUNT_HEADER).getText();
		String strDiscountNote = waitForExpectedElement(DISCOUNT_NOTE).getText();
		Assert.assertTrue(strDiscountHeading.equalsIgnoreCase(getMessage(strExpectedText)));
		Assert.assertTrue(strDiscountNote.equalsIgnoreCase(getMessage(strExpectedText1)));
	}

	public void assertShippingAddressIsDisplayed() {
		assertTrue(waitForExpectedElement(SHIPPING_ADDRESS,20).isDisplayed());
	}

	public boolean assertPaymentMethodsIsDisplayed() {
		try {
			getWebDriver().findElement(PAYMENT_METHODS);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void assertCvvHint() {
		Assert.assertTrue(getTextFor(CVV_HINT1).equalsIgnoreCase(getMessage("cvvHint1.key")));
		Assert.assertTrue(getTextFor(CVV_HINT2).equalsIgnoreCase(getMessage("cvvHint2.key")));
	}

	public boolean assertFeeIsDisplayedForCODpayment() {
		return waitForExpectedElement(COD_FEE).isDisplayed();
	}

	public void assertCODFeeAmount() {
		assertEquals(getMessage("COD_fee_amount.key"), getTextFor(COD_FEE), getTextFor(COD_DELIVERY_FEE));
	}

	public boolean assertUpdateAddressLinkPresent() {
		By updateAddressSelector;
		if (UrlBuilder.getLocale().equals("glojp")) {
			updateAddressSelector = FLUID_UPDATE_ADDRESS_LINK_JP;
		} else {
			updateAddressSelector = FLUID_UPDATE_ADDRESS_LINK;
		}
		return waitForExpectedElement(updateAddressSelector).isDisplayed();
	}

	public void clickOnUpdateAddressLink() {
		clickByElementByQueryJSExecutor(FLUID_UPDATE_ADDRESS_LINK);
	}

	public boolean assertUpdateAddress2LinkPresent() {
		return waitForExpectedElement(UPDATE_ADDRESS2_LINK).isDisplayed();
	}

	public void clickOnUpdateAddress2Link() {
		try {
			waitForItemToBeClickableAndClick(UPDATE_ADDRESS2_LINK,20);
		} catch (Exception e) {
			clickByElementByQueryJSExecutor(UPDATE_ADDRESS2_LINK);
		}
	}

	public boolean assertCourierDeliveryFeeIsDisplayed() {
		return waitForExpectedElement(COURIER_DELIVERY_FEE).isDisplayed();
	}

	public void assertCourierDeliveryAmount() {
		assertEquals(waitForExpectedElement(COURIER_DELIVERY_FEE).getText(), waitForExpectedElement(DELIVERY_PRICE).getText(), getMessage("courierDeliveryAmount.key"));
	}

	public void assertProductPricesWithCategoryTotal() {
		getSubTotalForDiscount();
		softAssert.assertEquals(gloItProductListPage.expectedProductsTotal, sub_total);
		softAssert.assertEquals(acc_amt, gloItProductListPage.plp_acc_amt);
		softAssert.assertEquals(bundle_amt, gloItProductListPage.plp_bundle_amt);
		softAssert.assertEquals(devices_amt, gloItProductListPage.plp_devices_amt);
		softAssert.assertEquals(sticks_amt, gloItProductListPage.plp_sticks_amt + gloItProductListPage.plp_velo_amt);
		softAssert.assertAll();
	}

	public void cancelPayment() {
		waitForExpectedElement(PAYMENT_CANCEL, 40);
		clickByElementByQueryJSExecutor(PAYMENT_CANCEL);
	}

	public static void getCurrentDate() {
		DateFormat japaneseDf = DateFormat.getDateInstance(DateFormat.FULL, java.util.Locale.JAPAN);
		Calendar cal = Calendar.getInstance();
		ZonedDateTime asiaTokyozone = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(UrlBuilder.getMessage("JapanZoneId.key")));
		LocalDate d = asiaTokyozone.toLocalDate();
		Date date = Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		cal.setTime(date);
		currentDate = japaneseDf.format(cal.getTime());
	}

	public void assertHyperPlusColoursOnOrderDetails() {
		hyperPlusColoursOnOrderDetails();
		assertEquals(HyperPlusPage.main_colour, main_body_colour);
		assertEquals(HyperPlusPage.side_colour, side_body_colour);
	}

	public void verifyUpdatedAddress() {
		try {
			waitForExpectedElement(SHIPPING_ADDRESS_HEADER,20).isDisplayed();
		} catch (Exception e) {
			getWebDriver().navigate().refresh();
			gloItProductListPage.waitForLoaderToDisappear();
		}
		waitForExpectedElement(SHIPPING_ADDRESS_HEADER, 20);
		assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(SHIPPING_ADDRESS).getText(), (getMessage("postalcode1.key")));
		assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(SHIPPING_ADDRESS).getText(), (getMessage("postalcode2.key")));
		assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(SHIPPING_ADDRESS).getText(), (getMessage("address2.key")));
		assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(SHIPPING_ADDRESS).getText(), addNewAddressPage.BuildingNumber);
	}

	public void ocrAddressIsNotAvailable(){
		assertTrue(waitForExpectedElement(SHIPPING_ADDRESS_2,40).isDisplayed());
	}
	public void ocrAddressVerificationStatusIsPending(){
		assertTrueWithCustomError((UrlBuilder.getMessage("VerificationPendingStatus.key")),(waitForExpectedElement(ADDRESS2_STATUS, 60).getText()));
	}

	public void ocrAddressVerificationStatusIsVerified() throws InterruptedException {
		Thread.sleep(15000L);
		waitForPage(40);
		gloItProductListPage.waitForLoaderToDisappear();
		waitForExpectedElement(ADDRESS2_STATUS,30).isDisplayed();
		assertTrueWithCustomError((UrlBuilder.getMessage("VerifiedStatus.key")),(waitForExpectedElement(ADDRESS2_STATUS).getText()));
	}

	public void userSelectsFluidDeliveryAddress(){
	/*	try {
			clickByElementByQueryJSExecutor(FLUID_ADDRESS);
		} catch (Exception e) {
			waitForExpectedElement(FLUID_ADDRESS,20);
			clickByElementByQueryJSExecutor(FLUID_ADDRESS);
		}*/
		getFluidAddress();
		IsPageLoaded.waitAllRequest();
		waitForAjaxElementNotToBePresent(getWebDriver(),6);
	}

	public void getFluidAddress(){
			shippingAddress1=waitForExpectedElement(FLUID_ADDRESS).getText().split(webDriver.findElement(FLUID_UPDATE_ADDRESS_LINK).getText().trim());
			selectedAddress=shippingAddress1[0].trim().replaceAll(" ","").replaceAll("\n"," ");
	}

	public void getOCRAddress(){
		if(waitForExpectedElement(ADDRESS_LIST,30).getText().contains(UrlBuilder.getMessage("VerifiedStatus.key"))){
			shippingAddress2=waitForExpectedElement(OCR_ADDRESS).getText().split(webDriver.findElement(OCR_ADDRESS_CONTACT).getText());
			selectedAddress=shippingAddress2[0].trim().replaceAll(" ","").replaceAll("\n"," ");
		}
	}

	public void userSelectsOcrDeliveryAddress(){
		try {
			waitForExpectedElement(OCR_ADDRESS,50).click();
		} catch (Exception e) {
			refreshBrowser();
			gloItProductListPage.waitForLoaderToDisappear();
			waitForExpectedElement(OCR_ADDRESS);
			clickByElementByQueryJSExecutor(OCR_ADDRESS);
		}
		getOCRAddress();
	}

	public boolean ocrAddressErrorIsDisplayed(){
		return waitForExpectedElement(OCR_ADDRESS_ERROR).isDisplayed();
	}
	public boolean ocrStaticMessageIsDisplayed(){
		return waitForExpectedElement(OCR_STATIC_MESSAGE).isDisplayed();
	}

	public boolean fluidAddressIsEnabled(){
		return waitForExpectedElement(FLUID_ADDRESS,20).isEnabled();
	}

	public boolean ocrAddressIsEnabled(){
		return waitForExpectedElement(OCR_RADIO_BUTTON,20).isEnabled();
	}

	public void uploadDocuments(){
		clickByElementByQueryJSExecutor(PROOF_LIST);
		selectValueFromDropDownByby(UrlBuilder.getMessage("DrivingLicense.key"),PROOF_LIST);
		WebElement uploadFrontPage=webDriver.findElement(UPLOAD_FRONT_PAGE);
		((RemoteWebElement) uploadFrontPage).setFileDetector(new LocalFileDetector());
		uploadFrontPage.sendKeys(frontPagePath);
		WebElement uploadBackPage=webDriver.findElement(UPLOAD_BACK_PAGE);
		((RemoteWebElement) uploadBackPage).setFileDetector(new LocalFileDetector());
		uploadBackPage.sendKeys(backPagePath);
		waitForExpectedElement(SAVE_SHIPPING_ADDRESS_FORM1).click();
	}

	public void enterValidAddressDetails() throws InterruptedException {
		waitForExpectedElement(ZIPCODE1,20).sendKeys(UrlBuilder.getMessage("validZipCode1.key"));
		waitForExpectedElement(ZIPCODE2,20).sendKeys(UrlBuilder.getMessage("validZipCode2.key"));
		waitForExpectedElement(PHONE_NUMBER_JP,20).sendKeys(random(10,NUMERIC));
		clickByElementByQueryJSExecutor(DISCLAIMER_CHECKBOX);
		clickByElement(SAVE_SHIPPING_ADDRESS_FORM2);
		Thread.sleep(5000L);
	}
	public void enterInvalidAddressDetails(){
		waitForExpectedElement(ZIPCODE1).sendKeys(random(3,NUMERIC));
		waitForExpectedElement(ZIPCODE2).sendKeys(random(4,NUMERIC));
		waitForExpectedElement(PHONE_NUMBER_JP,20).sendKeys(random(10,NUMERIC));
		clickByElementByQueryJSExecutor(DISCLAIMER_CHECKBOX);
		clickByElement(SAVE_SHIPPING_ADDRESS_FORM2);
	}
	public void updateShippingAddress2WithValidDetails() throws InterruptedException {
		uploadDocuments();
		enterValidAddressDetails();
		waitForPage(50);
		Thread.sleep(10000L);
	}
	public void updateShippingAddress2WithInvalidDetails(){
		uploadDocuments();
		enterInvalidAddressDetails();
		gloItProductListPage.waitForLoaderToDisappear();
	}
	public void noAddressSaved(){
		assertTrue(waitForExpectedElement(NO_ADDRESS_PRESENT).isDisplayed());
	}

	public void analyzeLog() {
		LogEntries logEntries = webDriver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}

	public void userCreatesNewAddressOnCheckoutPageForGloPL() {
		waitClearAndEnterText(paymentPage.STREET_ADDRESS_FIELD_CHECKOUT,random(34,ALPHABETS));
		waitClearAndEnterText(paymentPage.STREET_ADDRESS2_FIELD, "36 House");
		waitClearAndEnterText(STREET_ADDRESS_FIELD3_CHECKOUT, "14 App");
		waitClearAndEnterText(paymentPage.CITY_FIELD_CHECKOUT, "poland");
		waitClearAndEnterText(paymentPage.POSTAL_CODE_FIELD_CHECKOUT, "22-444");
		waitClearAndEnterText(paymentPage.PHONE_NUMBER, "223457789");
		clickByElementByQueryJSExecutor(paymentPage.USE_THIS_ADDRESS_BUTTON);
		waitForAjaxElementNotToBePresent(getWebDriver(),10);
	}

	public void userCreatesNewAddressOnCheckoutPageWithVATNumberForGloPL() {
		waitClearAndEnterText(paymentPage.STREET_ADDRESS_FIELD_CHECKOUT,random(50,ALPHABETS));
		waitClearAndEnterText(paymentPage.STREET_ADDRESS2_FIELD, "36 Claredon Road");
		waitClearAndEnterText(STREET_ADDRESS_FIELD3_CHECKOUT, random(10,ALPHABETS));
		waitClearAndEnterText(paymentPage.CITY_FIELD_CHECKOUT, "poland");
		waitClearAndEnterText(paymentPage.POSTAL_CODE_FIELD_CHECKOUT, "22-444");
		waitClearAndEnterText(paymentPage.PHONE_NUMBER, "223457789");
		enterDataAndWait(VAT_NUMBER_FIELD, random(10, NUMERIC));
		clickByElementByQueryJSExecutor(paymentPage.USE_THIS_ADDRESS_BUTTON);
		waitForAjaxElementNotToBePresent(getWebDriver(),10);
	}

	public void userClearsTaxNumberDataAsFieldIsOptional() {
		if(doesURLContain("checkout"))
			clearFieldUsingControlKeys(VAT_NUMBER_FIELD);
		else
			clearFieldUsingControlKeys(addNewAddressPage.VAT_NUMBER_FIELD_ADD_ADDRESS);
	}

	public void userEntersCharactersLimitInTaxNumberFieldOnCheckoutPage(String strCharacters) {
		String strVAT="";
		if(doesURLContain("checkout")){
			enterDataAndWait(VAT_NUMBER_FIELD, random(Integer.valueOf(strCharacters), NUMERIC));
			strVAT=waitForExpectedElement(VAT_NUMBER_FIELD).getAttribute("value");
		}else{
			enterDataAndWait(addNewAddressPage.VAT_NUMBER_FIELD_ADD_ADDRESS, random(Integer.valueOf(strCharacters), NUMERIC));
			strVAT=waitForExpectedElement(addNewAddressPage.VAT_NUMBER_FIELD_ADD_ADDRESS).getAttribute("value");
		}
		String[] vatData = strVAT.split("-");
		assertTrue(vatData[0].chars().allMatch(Character::isDigit) && vatData[0].length()==3);
		assertTrue(vatData[1].chars().allMatch(Character::isDigit) && vatData[1].length()==3);
		assertTrue(vatData[2].chars().allMatch(Character::isDigit)&& vatData[2].length()==2);
		assertTrue(vatData[3].chars().allMatch(Character::isDigit)&& vatData[3].length()==2);
	}

	public void userEntersLettersInTaxNumberFieldOnCheckoutPageAndAssertError(String strLimit,String strErrorMessage){
		if(doesURLContain("checkout")){
			enterDataAndWait(VAT_NUMBER_FIELD,random(Integer.valueOf(strLimit),ALPHABETS));
			clickByElementByQueryJSExecutor(paymentPage.USE_THIS_ADDRESS_BUTTON);
			waitForAjaxElementNotToBePresent(getWebDriver(),5);
			assertTrue(waitForExpectedElement(VAT_NUMBER_FIELD_ERROR).getText().contains(UrlBuilder.getMessage(strErrorMessage)));}
		else{
			enterDataAndWait(addNewAddressPage.VAT_NUMBER_FIELD_ADD_ADDRESS,random(Integer.valueOf(strLimit),ALPHABETS));
			addNewAddressPage.clickOnSaveAddressButtonToAddAndEditAddress();
			waitForAjaxElementNotToBePresent(getWebDriver(),5);
			assertTrue(waitForExpectedElement(addNewAddressPage.VAT_NUMBER_FIELD_ADDRESS_ERROR).getText().contains(UrlBuilder.getMessage(strErrorMessage)));
		}
	}

	public void userEntersMoreThanLimitCharactersInInTaxNumberFieldOnCheckoutPageAndAssertFormat(String strInput,String strLimit){
		if(doesURLContain("checkout")){
			enterDataAndWait(VAT_NUMBER_FIELD,random(Integer.valueOf(strInput),NUMERIC));
			clickByElementByQueryJSExecutor(paymentPage.USE_THIS_ADDRESS_BUTTON);
			waitForAjaxElementNotToBePresent(getWebDriver(),5);
			assertTrue(waitForExpectedElement(VAT_NUMBER_FIELD).getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").length()==Integer.parseInt(strLimit));}
		else{
			enterDataAndWait(addNewAddressPage.VAT_NUMBER_FIELD_ADD_ADDRESS,random(Integer.valueOf(strInput),NUMERIC));
			assertTrue(waitForExpectedElement(addNewAddressPage.VAT_NUMBER_FIELD_ADD_ADDRESS).getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").length()==Integer.parseInt(strLimit));
		}
	}

	public void assertFreeGiftItemIsNotDisplayed(){
		waitForAjaxElementNotToBePresent(getWebDriver(), 8);
		if(getWebDriver().findElements(HomePage.TOTAL_GIFT_OFFER).size()==1){
			String s = getWebDriver().findElement(HomePage.TOTAL_GIFT_OFFER).getText();
			assertTrue(s.equals(""));
		}
		else assertFalse(waitForExpectedElement(HomePage.TOTAL_GIFT_OFFER).isDisplayed());
	}

	public boolean isWorldPayPage(){
		return doesURLContain("worldpay.com");
	}

	public void goToTermsAndConditionsPageAndTakeScreenshot() {
		if (!Props.EYES_ON) {
			return;
		}
		final String pageName = "Terms And Conditions Page";
		switch (UrlBuilder.getLocale().toLowerCase()) {
			case "pl":
				waitForExpectedElement(TERMS_AND_CONDITIONS_GLO_PL, 3).click();
				scrollToShowEntirePage();
				eyes.check(pageName, Target.window().fully());
				break;


		}
	}
}
