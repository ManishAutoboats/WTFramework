package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.models.CheckoutPageDeliveryAddressModel;
import com.salmon.test.page_objects.gui.models.VisaDetail;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.*;
import java.util.stream.Stream;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.gui.ShippingPage.PAGE_HEADER_ANNOUNCEMENT_CSS_KZ;
import static com.salmon.test.page_objects.gui.constants.Context.ORDER_NUMBER;
import static com.salmon.test.page_objects.gui.constants.Context.CURRENT_ADDRESS;
import static com.salmon.test.page_objects.gui.constants.Context.LANGUAGE;
import static com.salmon.test.page_objects.gui.constants.Locale.*;
import static java.util.stream.Collectors.toCollection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PaymentPage extends PageObject {

  private AccountDashboardPage accountDashboardPage;
  private HomePage homePage;
  private AddNewAddressPage addNewAddressPage;
  private OrderSuccessPage orderSuccessPage;
  private ScenarioContext scenarioContext;
  private BasketPage basketPage;
  private AvalancheAccountDashboardPage avalancheAccountDashboardPage;
  private PLP plp;
  private PDP pdp;

  public PaymentPage(AccountDashboardPage accountDashboardPage,
                     AddNewAddressPage addNewAddressPage,
                     HomePage homePage,
                     ScenarioContext scenarioContext,
                     BasketPage basketPage,
                     AvalancheAccountDashboardPage avalancheAccountDashboardPage,
                     PLP plp, OrderSuccessPage orderSuccessPage,PDP pdp) {
        this.accountDashboardPage = accountDashboardPage;
        this.addNewAddressPage=addNewAddressPage;
        this.homePage = homePage;
        this.scenarioContext = scenarioContext;
        this.orderSuccessPage = orderSuccessPage;
        this.basketPage = basketPage;
        this.avalancheAccountDashboardPage = avalancheAccountDashboardPage;
        this.plp = plp;
        this.pdp = pdp;
  }

    private static final Logger LOG = LoggerFactory
            .getLogger(PaymentPage.class);

    public static Float TOTAL_CHARGES_MINIBASKET_CHECKOUT;
    public static Float TOTAL_CHARGES_CART;
    public static Float SUB_TOTAL_CHARGES_MINIBASKET_CHECKOUT;
    public static Float FINAL_CHARGES_CART;
    public static Float DISCOUNT_CHARGES_CART;
    public static Float ECO_TAX_CHARGES_MINIBASKET_CHECKOUT;
    public static Float SHIPPING_CHARGES_CHECKOUT;
    public static Float DELIVERY_CHARGES_MINIBASKET_CHECKOUT;
    public static Float SUB_TOTAL_CHARGES_AFTER_DISCOUNT;
    public static Float TOTAL_CHARGES_AFTER_DISCOUNT;
    public static Float PRODUCT_PRICE;
    public static int PRODUCT_QUANTITY;
    public static float TOTAL_SUMMARY_CART_AMOUNT;



  // Device Trial
  public By deviceTrialCheckbox = By.cssSelector("#devicetrial_worldpay_cc,div.devicetrial_consent > label ");
  public By deviceTrialPaymentBlock = By.cssSelector("div.devicetrial_checkout");
  private By checkoutSuccess = By.cssSelector(".checkout-success");

  public By TRAIL_DISCOUNT_INFOS = By.cssSelector(".device-trial-discount.totals span");
  public By TRAIL_DISCOUNT_INFO_PAYMENT_DUE= By.cssSelector(".dt-grand-total.grand.totals.incl span");
  public By TRAIL_DISCOUNT_FUTURE_PAYMENT_NOTE = By.cssSelector(".dt-grand-total-note.incl");
  public By TRAIL_CONSENT= By.cssSelector("label[for='devicetrial_consent']");
  public By TRAIL_CREDITCARD_ONLY= By.cssSelector(".payment-title-wrapper.device-trial-payment-title-wrapper>h2");
  public By TRAIL_DEBITCARD_NO= By.cssSelector(".payment-title-wrapper.device-trial-payment-title-wrapper>p");
  public By STATE_DROP_DOWN=By.cssSelector("#shipping-new-address-form > div:nth-child(8) > div select");

  // @Getter
    // ELEMENT MAPPING
    public static final By TELEPHONE_BILLING_ADDRESS_INPUT = By.cssSelector("fieldset.fieldset.contact-details input[name=telephone]");
    public static final By ADD_NEW_BILLING_ADDRESS_BUTTON = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.payment-method-billing-address > div > div > a");
    public static final By ADD_NEW_BILLING_ADDRESS_RADIO_BUTTON = By.cssSelector("div.payment-method._active > div.payment-method-content > div.payment-method-billing-address > div > fieldset > div.field.field-select-billing > div > div > div:nth-child(2)");
    public static final By ENTER_ADDRESS_MANUALLY = By.cssSelector("span.address-search__toggle");
    private static final By TERMS_AND_CONDITIONS_LINK_VUSE_FR = By.cssSelector("[for='agreement_worldpay_cc_5'] span a");
    private static final By TERMS_AND_CONDITIONS_LINK_LYFT_SE = By.cssSelector("div.payment-method._active div.payment-method-content div.checkout-agreements-block div div div label a");
    public static final By USE_THIS_ADDRESS = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.payment-method-billing-address > div > fieldset > div.actions-toolbar > div > button.action.primary.action-update.address-list__use-button");
    public static final By BILLING_ADDRESS_BLOCK = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.payment-method-billing-address > div > div > div");
    public static final By BILLING_ADDRESS_TELEPHONE_BLOCK = By.cssSelector("p.selected-billing-address-phone");
    public final static String UPDATED_BILLING_ADDRESS_TELEPHONE = "0777923761";
    public final static String UPDATED_BILLING_ADDRESS_STREET = "12 The Cloisters";
    public final static String UPDATED_BILLING_ADDRESS_CITY = "Watford";
    public final static String UPDATED_BILLING_ADDRESS_POST_CODE = "WD3 1HL";
    public final static By BILLING_ADDRESS_STREET_INPUTS = By.cssSelector("input[name='street[0]']");
    public final static By BILLING_ADDRESS_POSTCODE_INPUTS = By.cssSelector("input[name='postcode']");
    public final static By BILLING_ADDRESS_CITY_INPUTS = By.cssSelector("input[name='city");
    private static final By LOADING_MASK = By.cssSelector("img[alt='Loading ...']");
    private static final By CREDIT_CARD_RADIO_BTN_GLODE = By.cssSelector("input#cardTypeCredit");
    private static final By VISA_CARD_OPTION_VYPEIT = By.cssSelector("#worldpay_cc_cc_type_div > div > label:nth-child(4) > div > img");
    public static final By ADD_NEW_ADDRESS_BUTTON = By.cssSelector("button.action.action-show-popup > span:nth-child(1)");
    public static final By ADD_NEW_ADDRESS_SECONDARY = By.cssSelector("button.alink.address-list__new-button");
    public static final By PROMOMESSAGE_FR = By.cssSelector("div[data-ui-id='checkout-cart-validationmessages-message-success']");

    // below ensure selected / ticked
    public By billingSameAsShippingAddressCheckBox = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.payment-method-billing-address > div > div.billing-address-same-as-shipping-block.field.choice > label");
    public By orderSummaryHeading = By.cssSelector("div.opc-block-summary,.summary.title,div.cart-summary > strong.summary.title");
    private static final By MINI_CART_BUTTON_IT = By.cssSelector("#checkout > div.opc-estimated-wrapper > div.minicart-wrapper > button.action.showcart");
    private static final By POPUP_ClOSE_BUTTON_IT = By.cssSelector("#checkout > aside > div.modal-inner-wrap > header > button");
    public By paymentNextButton = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button > span");
    private By checkMoneyOrder = By.cssSelector("#checkout-payment-method-load > div > div > div:nth-child(2) > div.payment-method-title.field.choice > label");
    private By creditCardOption = By.cssSelector("input#worldpay_cc.radio");
    private By masterCardOption = By.cssSelector("label[for='direct_cc_ECMC-SSL']");
    private By visaCardOption = By.cssSelector("#worldpay_cc_cc_type_div > div > label:nth-child(4)");
    public By amexCardOption = By.cssSelector("#worldpay_cc_cc_type_div > div > label:nth-child(6)");
    private By creditCardHolderNameField = By.cssSelector("#worldpay_cc_cc_name");
    private By creditCardNumberField = By.cssSelector("#worldpay_cc_cc_number");
    private By cardExpirationDateMonthDropDown = By.cssSelector("#worldpay_cc_expiration.select.select-month");
    private By cardExpirationDateYearDropDown = By.cssSelector("#worldpay_cc_expiration_yr.select.select-year");
    private By creditCardVerificationNumberField = By.cssSelector("#worldpay_cc_cc_cid");
    private By placeOrderButton = By.cssSelector("#checkout-payment-method-load button.action.primary.checkout");
    private By applyDiscountText = By.cssSelector("#discount-code,#coupon_code");
    private By discountCodeInputField = By.cssSelector("#discount-code,#coupon_code");
    private By promoMessage = By.cssSelector("#co-payment-form > fieldset > div.payment-option.opc-payment-additional.discount-code > div.payment-option-content > div > div");
    private By validatePaymentButton = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button > span");
    public By enterZipCode = By.cssSelector("#gls-parcelshop-postcode");
    public By ParcelShopOptions=By.xpath("//select[@name='gls-parcelshop-pickup-id']//option[2]");
    public By packagePickPersonName = By.cssSelector("#gls-parcelshop-pickupname");
    public By phoneNumberAdvising = By.cssSelector("#gls-parcelshop-notification");
    public final By EnterZipCode_lyft = By.cssSelector("#dps-parcelshop-postcode");
    public final By PhoneNumberAdvising_lyft = By.cssSelector("#dps-parcelshop-notification");
    public By btnSaveCard = By.cssSelector("div.field.savecc:nth-child(2) div.control > label.label:nth-child(5)");
    public By btnSaveCardVypeIT = By.cssSelector("div.field.savecc.cc-Visibility-Enabled:nth-child(6) div.control > label.label");
    public By rdoUseSavedCards = By.cssSelector("#worldpay_cc_cc_type_div > div > div:nth-child(3) > label,#worldpay_cc_cc_type_div > div > label:nth-child(6)");
    public By rdoSavedCard = By.cssSelector("#payment_form_worldpay_cc > div.field.savecc > div > div > label > img, label.label div.payment-logo-wrapper > img");
    public By txtSavedCardCVV = By.cssSelector(".input-text.cvv.saved-cvv-number");
    private static final By TERMS_AND_CONDITIONS_LYFTSEPAYPAL = By.cssSelector("input#agreement_worldpay_apm_9");
    private static final By TERMS_AND_CONDITIONS_LYFTSE = By.id("agreement_worldpay_cc_9");
    private static final By SAVED_CARD = By.cssSelector("#worldpay_cc_cc_type_div > div > div:nth-child(3) > label");
    public final By PAYPAL_CONTINUE_BUTTON =  By.xpath("//input[@id='PMMakePayment']");
    private final static By M_PLACE_ORDER_BUTTON_CO=By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button");
    private final static By M_PLACE_ORDER_BUTTON_IE=By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.checkout-agreements-block-wrapper > div.actions-toolbar > div > button");
    public By TermsAndCondtionKeyLyftDK=By.cssSelector("div.checkout-agreement.required label");
    private static final By AGE_CONFIRMATION_TICKBOX_LYFT_DK = By.cssSelector("#agreement_worldpay_cc_12");
    public List<WebElement> productItems,bundleProductStrength;
    private By payPalPlaceOrderButton  = By.cssSelector("div#checkout-payment-method-load div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button[type=\"submit\"]");
    private static final By PAYPAL_PLACE_ORDER_CTA_FR  = By.cssSelector("button#checkout-place-order > span > span");
    public By selectPaypalPaymentOption  = By.cssSelector("div#worldpay_apm_cc_type_div  label");
    private static final By TERMS_CONDITIONS_CHECKBOX = By.cssSelector("div.field.choice.required:nth-child(1) > input.terms.checkbox.required");
    private static final By AGE_VERIFICATION_CHECKBOX = By.cssSelector("div.field.choice.required:nth-child(2) > input.age-verification.checkbox.required");
    private final static By SHIPPING_BILLING_ADDRESS_SAME_CHECKBOX =By.cssSelector("input#billing-address-same-as-shipping-worldpay_cc");
    private final static By REMOVE_PROMOTION_BUTTON = By.cssSelector("#discount-form > div > div > div > button");
    private static final By DELIVERY_ADDRESS_REGION = By.cssSelector(".selected-address__container");
    private static final By CHECKOUT_SUMMARY_REGION = By.cssSelector(".opc-sidebar .opc-block-summary");
    private static final By BILLING_ADDRESS_REGION = By.cssSelector(".billing-address-details");
    private static final By SHIPPING_ADDRESS_REGION = By.cssSelector(".shipping-address-item");
    private static final By CHANGE_ADDRESS_GLO_PL = By.cssSelector(".selected-address__container .selected-address__change-button.alink");
    private static final By SHIPPING_INFO_REGION = By.cssSelector(".ship-to .shipping-information-content");
    private static final By VUSE_DE_SHIPPING_TO = By.cssSelector("#shipping");
    private static final By MINICART_CLOSE_CTA = By.cssSelector(".sliding-panel-close > .material-icons");
    private static final By MINICART_CLOSE_CTA_VYPEFR = By.cssSelector("button#btn-minicart-close");
    private static final By NOTIFY_PHONE_NUMBER = By.cssSelector("#dps-parcelshop-notification");
    private static final By SAVE_CARD_CHK_BOX_CO = By.cssSelector("#save_cc");
    public static final By SAVE_CARD_WORLDPAY_CHK_BOX = By.cssSelector("input#worldpay_cc_save_card");
    private static final By SAVE_CARD_WORLDPAY_CHK_BOX_STATUS = By.cssSelector("#worldpay_cc_save_card");
    private static final By SAVED_CARD_DRP_DWN_CO = By.cssSelector("#openpay_cc");
    private static final By CVV_INPUT_BOX_CO = By.cssSelector("input[id='openpay_cards_cc_cid']");
    private static final By TERMS_AND_CONDITIONS_MX_IPHONE = By.xpath("//input[contains(@id,'agreement_paypal_express')]");
    public static final By BUTTON_CONTINUE_SHOPPING = By.cssSelector("a.action.primary.continue");
    public static final By BUTTON_PLACE_ORDER_DE = By.cssSelector("div.payment-method._active div.primary > button.action.primary.checkout");
    public final static By SELECT_PAYMENT_OUTCOME_DROPDOWN = By.cssSelector("select[name='status']");
    public final static By SHIPPING_METHODS = By.xpath("//div[@id='checkout-shipping-method-load']//tbody//tr[@class='row']/td/following::td[contains(@id,'label_method')]");
    public final static By SHIPPING_METHOD_LABEL = By.xpath("//div[@id='checkout-shipping-method-load']//tbody//tr[@class='row']/td/following::td[contains(@id,'label_method')]");
    public final static By RETURN_BASKET_PAGE = By.cssSelector("div.opc-block-summary > div.actions-toolbar > div > a");
    private final static By APPLY_DISCOUNT_TEXT_ZA = By.cssSelector("#discount-code");
    private final static By TERMS_CONDITIONS_LINK = By.cssSelector(".checkout-agreement.required a");
    private final static By CLOSE_TERMS_CONDITIONS_BUTTON = By.cssSelector("._show .action-close");
    private final static By CLOSE_TERMS_CONDITIONS_BUTTON_LYFT_SE = By.cssSelector("aside.modal-popup.agreements-modal.modal-slide._inner-scroll._show > div.modal-inner-wrap > footer > button");
    public final static By M_CONTINUE_CHECK_OUT_BTN_VUSEUK = By.cssSelector("li.item > button.action.primary.checkout");
    private final static By SHIPPING_STREET_ADDRESS_2=By.cssSelector("div[name='shippingAddress.street.2'] input[name='street[2]']");
    public final static By LINK_CHANGE_ADDRESS_PAYMENT_SECTION=By.cssSelector("div.checkout-billing-address a.selected-address__change-button.alink");
    public final static By RADIO_NEW_ADDRESS_PAYMENT_SECTION=By.cssSelector("div.checkout-billing-address div.control div:nth-child(2) > div.shipping-address-item:nth-child(2)");
    public final static By ADDRESS_FORM_PAYMENT_SECTION=By.cssSelector("div.billing-address-form");
    public final static By PHONE_NUMBER_FIELD_PAYMENT_SECTION=By.cssSelector("div.checkout-billing-address input[name=telephone]");
    public static final By PAYMENT_WARNING_ZA=By.cssSelector("#testAlert > div > div > div.modal-header > button.close");
    //JP
    public static By TermsAndCondtionCheckBoxJP=By.cssSelector("div.payment-method._active>div.payment-method-content>div.checkout-agreements-block>div>div>div.checkout-agreement.required>label");
    // Glo
    public final static By RESALE_CODE_FIELD_TITLE = By.cssSelector("span#block-retailer-code-heading > span");
    public final static By RESALE_CODE_FIELD = By.cssSelector("input#retailer-code");
    public final static By PAYMENT_METHOD_TITLE_CHOICE = By.cssSelector("#checkout-payment-method-load > div > div > div:nth-child(3) > div.payment-method-title.field.choice > label");
    private static final By COUNTRY = By.cssSelector("div[name='shippingAddress.country_id'] select[name='country_id']");
    private static final By STREET = By.cssSelector("div[name='shippingAddress.street.0'] input[name='street[0]']");
    private static final By CITY = By.cssSelector("div[name='shippingAddress.city'] input[name='city']");
    public static final By POSTCODE = By.cssSelector("div[name='shippingAddress.postcode'] input[name='postcode']");
    private static final By CHECKOUT_DELIVERY_NOTICE_POSTCODE_LIST = By.cssSelector("#checkout-delivery-notice");
    private static final By NEW_DELIVERY_ADDRESS_DELIVER_HERE_OR_SAVE_BUTTON =By.xpath("//button[@class='action primary action-save-address']/span");
    public static final By PHONE_NUMBER = By.cssSelector("div[name='shippingAddress.telephone'] input[name='telephone']");
    private static final By DELIVER_HERE_BUTTON = By.cssSelector("button.action.action-select-shipping-item");
    private static final By FREE_SHIPPING_RECOMMENDATION_MSG = By.cssSelector(".Recommended-msg,span.icon-vype-delivery.header-assurance-icon");
    private static final By NEWSLETTER_SUBSCRIPTION_CHKBOX = By.cssSelector("#newsletter-subscription,div:nth-child(3) > form > div > div > div.control > label");
    private static final By NEWSLETTER_SUBSCRIPTION_CHKBOX_VELO = By.cssSelector("#form-validate > fieldset > label > span.checkmark");
    private static final By NEWSLETTER_PROMOTION_TEXT_CSS = By.cssSelector("div.payment-option.opc-payment-additional.newsletter.last > form > div > div > div.note > span");
    private static final By NEWSLETTER_LABEL_CSS = By.cssSelector("label[for=\"newsletter-subscription\"]");
    public By streetAddressLineOnPyment = By.cssSelector("input[name='street[0]']");
    public By houseNumber = By.cssSelector("input[name='street[1]']");
    public By cityInputOnPayment = By.cssSelector("input[name='city']");
    public By postCodeInput = By.cssSelector("input[name='postcode']");
    public By phoneNumberInput= By.cssSelector("input[name='telephone'][class='input-text']");
    private final static By ADD_NEW_ADDRESS = By.cssSelector("div.box.box-billing-address:nth-child(1) div.box-actions a.action.edit > span:nth-child(1)");
    private final static By BUTTON_ADDRESS_SAVE = By.cssSelector(".action.submit.primary");
    private final static By ADD_NEW_ADDRESS_VELO = By.cssSelector("div.new-address-popup > button>span");


  //Lyft/Lab
    private final static By CARTITEMS_CHECKOUT_LINK=By.cssSelector("div.title>strong");
    private final static By CHECKOUT_PRODUCTS_LIST = By.xpath("//*[@class='block items-in-cart active']//ol[@class='minicart-items']");
    private final static By CHECKOUT_PRODUCTS_NAMES = By.xpath("//*[@class='block items-in-cart active']//ol[@class='minicart-items']//li//strong[@class='product-item-name']");
    private final static By CHECKOUT_PRODUCTS_PRICES = By.cssSelector("span.cart-price");
    private final static By CHECKOUT_PRODUCTS_QTYS = By.xpath("//*[@class='block items-in-cart active']//ol[@class='minicart-items']//li//div[@class='details-qty']");
    private final static By CHECKOUT_SEEDETAILS_LINK= By.xpath("//li[@class='product-item']//span[@class='toggle']");
    private final static By CHECKOUT_PRODUCTS_DETAILS= By.cssSelector("dl.item-options>dt");
    private final static By PARCEL_SHOP_SHIPPING_METHOD= By.xpath("//td[@id='label_method_parcelshop_dps']");
    private static final By PARCEL_SHOP_PICKUP_ID = By.id("gls-parcelshop-pickup-id");
    private final static By PARCEL_SHOP_SHIPPING_RADIOBUTTON=By.xpath("//td[@id='label_method_parcelshop_dps']//preceding::input[@value='dps_parcelshop']");
    private static final By PARCEL_SHOP_SHIPPING_RADIOBUTTON_LYFTSE = By.cssSelector("#checkout-shipping-method-load > table > tbody > tr:nth-child(1) > td:nth-child(1)");
    private final static By PARCEL_SHOP_NOTIFICATION =By.cssSelector("input[name='dps-parcelshop-notification']");
    private final static By GLS_SHIPPING_METHOD=By.xpath("//input[@value='gls_parcelshop_ebac']");
    public final static By NEW_ADDRESS_MODAL_TITLE = By.cssSelector("h1.modal-title");
    private final static By ADDRESS_TOGGLE = By.cssSelector(".address-search__toggle");
    private final static By FREE_DELIVERY_RADIO_BUTTON = By.cssSelector(".radio.active");
    private final static By ADDRESS_FINDER = By.cssSelector("#new-address-search");
    private final static By UK_CHANGE_ADDRESS=By.cssSelector("#checkout-step-shipping > div.selected-address__container > a");
    private final static By UK_ADD_NEW_ADDRESS=By.cssSelector("button.alink.address-list__new-button");
    private final static By UK_ADDRESS_MANUALLY = By.cssSelector("#co-shipping-form > span");
    private static final By COUPON_CODE_TEXT_FIELD = By.cssSelector("#coupon_code,input#discount-code");
    private static final By COUPON_CODE_TEXT_FIELDDE = By.cssSelector("input#coupon_code");
    private static final By COUPON_CODE_TEXT_FIELDFR = By.cssSelector("input#discount-code");
    private static final By APPLY_DISCOUNT_BUTTON = By.xpath("//button[contains(@class,'action apply')]");
    private static final By ECO_PARTICIPATION_TAX = By.cssSelector("tr.totals.ecoparticipation");
    private static final By ECO_PARTICIPATION_TAX_CHECKOUT_PAGE = By.cssSelector("#opc-sidebar > div.opc-block-summary > div.table-totals-wrapper > table > tbody > tr.totals.ecoparticipation");
    private static final By ECO_TAX_CHARGES = By.cssSelector("span.price");
    private static final By PRODUCT_TOTAL_CHARGES = By.xpath("//table[contains(@class,'data table') and contains(@class,'totals')]");
    private static final By PRODUCT_TOTAL_CHARGES_SUBS = By.xpath("//div[@id='opc-sidebar']/div[@class='opc-block-summary']/div[@class='table-totals-wrapper']");
    private static final By PRODUCT_TOTAL_CHARGES_SUBS_VUSECO = By.xpath("//div[@id='opc-sidebar']/div[@class='opc-block-summary']//table[@class='data table table-totals']");
    private static final By SUB_TOTAL_CHARGES_MINIBASKET = By.cssSelector("td.amount > span.price");
    private static final By SUB_TOTAL_PRICE = By.xpath("//tr[@class='totals sub']//span[@class='price']");
    private static final By FINAL_CHARGES_SHOPPING_CART = By.cssSelector("div.sliding-panel-section.sliding-minicart:nth-child(3) div.minicart_grandtotal:nth-child(5) > div.amount.price-container");
    private static final By TOTAL_CHARGES_SHOPPING_CART = By.cssSelector("div.sliding-panel-section.sliding-minicart:nth-child(3) div.minicart-wrapper div.block.block-minicart:nth-child(2) div.block-content div.subtotal:nth-child(5) div.amount.price-container:nth-child(3) span.price-wrapper > span.price");
    private static final By DISCOUNT_CHARGES_SHOPPING_CART = By.cssSelector("div.sliding-panel-section.sliding-minicart:nth-child(3) div.minicart-wrapper div.block.block-minicart:nth-child(2) div.block-content div.subtotal:nth-child(5) div.discounted:nth-child(4) span.price-wrapper.discount_value_formatted > span.price");
    private static final By TOTAL_CHARGES_MINIBASKET = By.cssSelector("tr.grand.totals.incl>td>strong>span");
    private static final By TOTAL_CHARGES_MINIBASKET_DE = By.cssSelector("tr.grand.totals.incl");
    private static final By DELIVERY_CHARGES_MINIBASKET = By.cssSelector("tr.totals.shipping.incl > td.amount > span.price");
    private static final By ECO_TAX_CHARGES_MINIBASKET = By.cssSelector("tr.totals.ecoparticipation > td.amount > span.price");
    private static final By SHIPPING_CHARGES_MINIBASKET = By.cssSelector("tr.totals.shipping.incl > td.amount > span.price");
    private static final By CHARGES_AFTER_DISCOUNT = By.xpath("//span[@class='discount coupon']//following::span[@class='price'][1]");
    private static final By CHECKOUT_PAYMENT_FIRST_NAME_READ_ONLY = By.xpath("//div[@name='billingAddressworldpay_cc.firstname']//input[@disabled]");
    private static final By CHECKOUT_PAYMENT_LAST_NAME_READ_ONLY = By.xpath("//div[@name='billingAddressworldpay_cc.lastname']//input[@disabled]");
    private static final By INCLUDE_POD_BAG_BUTTON=By.cssSelector("button.action.action-add-to-cart > span");
    private static final By PRODUCT_DETAILS_WITH_POD_BAG = By.xpath("//div[@class='product-item-details']//following::div[@class='product-item-name-block']//strong[text()='Pod Recycle Bag']");
    private final static By GLS_SHIPPING_METHOD_LABEL = By.cssSelector("#label_method_parcelshop_ebac_gls");
    private static final By PRODUCT_ALL_CHARGES_CART = By.cssSelector("div.sliding-panel-section.sliding-minicart:nth-child(3) div.block-content > div.subtotal:nth-child(5)");
    private static final By PRODUCT_ALL_CHARGES_CART_GLO = By.cssSelector("div.subtotal");
    private static final By TOTAL_CHARGES_CART_GLO=By.cssSelector("div.subtotal > div.amount.price-container");
    private static final By TOTAL_CHARGES_CART_GLODE = By.cssSelector("#cart-totals > div > table > tbody > tr.totals.sub > td > span");
    private static final By SUBTOTAL_CHARGES_VUSEIT = By.cssSelector("tr.totals.sub > td > span");
    private static final By DISCOUNT_COUPON_ERROR=By.cssSelector("div.message-error.error.message");
    private static final By DISCOUNT_CHARGES_CART_GLO=By.cssSelector("div.discounted > span > span.price");
    private static final By DISCOUNT_CHARGES_CART_GLODE = By.cssSelector("#cart-totals > div > table > tbody > tr.total-rules.amasty-rules > td > span");
    private static final By DISCOUNT_CHARGES_CART_VYPEFR = By.cssSelector("span.rule-amount");
    private static final By DISCOUNT_CHARGES_CART_LYFTSE = By.cssSelector("span.price-wrapper.discount_value_formatted > span.price");
    private static final By FINAL_CHARGES_CART_GLO=By.cssSelector("div.minicart_grandtotal > div.amount.price-container");
    private static final By FINAL_CHARGES_CART_GLODE = By.cssSelector("#cart-totals > div > table > tbody > tr.grand.totals.incl > td > strong > span");
    private static final By FINAL_CHARGES_CART_VYPEFR= By.cssSelector(".grand.totals.incl > td span");
    private static final By FINAL_CHARGES_CART_LYFTSE= By.cssSelector(".grand.totals.incl > td span");
    private static final By GLS_PARCEL_SHOP_PICKUP_ADDRESS_CSS = By.cssSelector("#gls-parcelshop-pickup-id");
    private static final By DPS_PARCEL_SHOP_PICKUP_ADDRESS_CSS = By.cssSelector("#dps-parcelshop-pickup-id");
    private static final By PARCELSHOP_LOCATOR_POPUP_SHOW_MAP_BUTTON_LYFTSE = By.cssSelector("#dps-parcelshop-block-wrapper  button.showmap");
    private static final By DPS_LOCATOR_POPUP = By.cssSelector("#dps-popup-map");
    private static final By DPS_MAP_INSIDE_POPUP = By.cssSelector("#dps-map");
    private static final By DPS_MAP_POPUP_CONTINUE_BUTTON = By.xpath("//div[@id='dps-popup-map']//following::footer//button/span");
    private static final By CHECKBOX_MASTERCARD_TYPE = By.cssSelector("#direct_cc_ECMC-SSL");
    private static final By CHECKBOX_VISACARD_TYPE = By.cssSelector("input#direct_cc_VISA-SSL");
    private static final By SHOW_PRODUCT_SUMMARY=By.cssSelector("div.opc-block-summary > div.block.items-in-cart>div");
    private static final By FIRST_SHIPPING_METHOD = By.cssSelector("table.table-checkout-shipping-method>tbody>tr:nth-child(1)");
  //MX Objects for Checkout Flow
    public static final By ENGRAVING_CONTINUE_PODS = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.mx-checkout-cartridge-popup.modal-slide._inner-scroll._show > div.modal-inner-wrap > footer > button.action.primary");
    public By MXCreditCardOption = By.cssSelector("div:nth-of-type(4) > .choice.field.payment-method-title > .label");
    public By GloPaymentMethod = By.cssSelector("#checkout-payment-method-load > div > div");
    public By electronicInvoiceCheckbox = By.cssSelector(".choice.field.receive-electronic-invoice > label > span");
    public By chkTermsAndConditions = By.xpath("//*[@id='tns_hosted']//following::input[contains(@id,'agreement')]");
    private static final By ENTER_RFC = By.cssSelector("div.payment-method._active > div.payment-method-content >div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(1) > div > input");
    private static final By enterBussinessName = By.cssSelector("div.payment-method._active > div.payment-method-content >div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(2) > div > input");//13-01-2020
    private static final By enterRoadName = By.cssSelector("div.payment-method._active > div.payment-method-content >div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(3) > div > input");//13-01-2020
    private static final By enterOutdoorNumber = By.cssSelector("div.payment-method._active > div.payment-method-content >div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(4) > div > input");//13-01-2020
    private static final By enterSuburb = By.cssSelector(".payment-group > div:nth-of-type(4) .form  div[name='before-place-order.edicomForm.district']  input[name='edicomForm[district]']");//13-01-2020
    private static final By enterCity = By.cssSelector("div.payment-method._active > div.payment-method-content >div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(7) > div > input");//13-01-2020
    public By enterState = By.cssSelector(".payment-group > div:nth-of-type(4) .form  div[name='before-place-order.edicomForm.state']  input[name='edicomForm[state]']");//13-01-2020
    private static final By enterPostalCode = By.cssSelector("div.payment-method._active > div.payment-method-content >div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(9) > div > input");//13-01-2020
    private static final By COLONIA_MX = By.cssSelector("div.payment-method._active > div.payment-method-content > div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(6) > div > input");
    private static final By ESTODO_MX = By.cssSelector("div.payment-method._active > div.payment-method-content > div.checkout-agreements-block > div.checkout-edicom > form > fieldset > div:nth-child(8) > div > input");
    public By btnPay = By.cssSelector("[title=Pagar]");
    public By iframeCheckoutCard = By.cssSelector("iframe[title='Hosted Checkout']");
    private By MXcreditCardHolderName = By.cssSelector("#cardHolderName");
    private By MXcreditCardNumber = By.cssSelector("#cardNumber");
    private By MXCardExpirationMonthDropDown = By.cssSelector("#expiryMonth");
    private By MXCardExpirationYearDropDown = By.cssSelector("#expiryYear");
    private By MXcreditCardVerificationNumber = By.cssSelector("#csc");
    public By eleErrorTCsNotSelected = By.cssSelector("div.mage-error");
    public By rdoPaypalExpressCheckout=By.cssSelector("#paypal_express");
    public By chkTermsConditionsPaypal=By.xpath("//*[@id='paypal_express']//following::input[contains(@id,'agreement_paypal_express')]");
    public By elePayPalPageHeader = By.cssSelector("span.merchantName");
    public By btnAcceptCookies=By.cssSelector("#acceptAllButton");
    public By btnContinue=By.cssSelector("button.btn.full.confirmButton.continueButton");//16-01-2020
    public By btnConfirmContinue=By.cssSelector("#confirmButtonTop");
    public By chkTermsConditionsReviewOrder=By.cssSelector("input.terms-and-conditions.required-entry");
    public By btnContinueOnPaypal=By.cssSelector("div.payment-method._active:nth-child(3) div.payment-method-content div.actions-toolbar div.primary button.action.primary.checkout");
    public By txtPaypalEmail=By.cssSelector("#email");
    public By txtPaypalPassword=By.cssSelector("#password");
    public By btnPaypalLogIn=By.cssSelector("#btnLogin");
    public By btnValidatePayment=By.cssSelector("button#review-button");
    public By btnNext=By.xpath("//*[text()='Next'][@class='submitButton btn btn-success pull-right contrast-success-btn']");
    public By btnPayNow=By.xpath("//*[text()='Pay now'][@class='submitButton btn btn-success pull-right contrast-success-btn']");
    public By btnSubmit=By.cssSelector("[value=Submit]");
    public By eleSuccessOrderTitle=By.cssSelector("div.checkout-success");
    public By btnRemoveCartItem=By.cssSelector("i.remove.material-icons");
    public By btnDeleteAction=By.cssSelector("a.action.action-delete");
    public By eleCartEmptyMessage=By.cssSelector("div.cart-empty");
    public By ukVypeCXChkUseThisCard=By.cssSelector("#worldpay_cc_save_card");
    public By chkUseThisCard=By.cssSelector("div.field.savecc.cc-Visibility-Enabled:nth-child(6) div.control > label.label");
    public By rdoSavedCardLyft = By.cssSelector("div.field.savecc:nth-child(2) div.control > label.label:nth-child(2)");
    public By lstSavedCards = By.xpath("//*[@class='field sacedcard']//following::input[@name='payment[token_to_use]']");
    public final static By BTN_SUBMIT_IPHONE=By.cssSelector("div.checkout-card>button.submit");
    public final static By BACK_BUTTON_ON_CHECKOUTPAGE = By.cssSelector(".back-button.icon-arrow-left");
    public final static By CHECKOUT_SUCCESS_ORDER = By.cssSelector("div.checkout-success");

    public final static By SUBS_CHECKOUT_CTA_ORDERSUMMARYFR =By.cssSelector("form#checkout_pending_cart_form  span");
    public final static By SHIPPING_COST = By.cssSelector("div#checkout-shipping-method-load > .table-checkout-shipping-method");
    public final static By VYPE_PAYMENT_METHOD_CREDIT_CARD_IT = By.cssSelector("label[for=\"worldpay_cc\"]");
    private final static By MX_CONTINUE_PAYMENT_BUTTON=By.xpath("//*[text()='Continue'][@class='submitButton btn btn-success float-right contrast-success-btn']");
    private final static By MX_M_CONTINUE_PAYMENT_BUTTON=By.xpath("//*[text()='Continue'][@class='submitButton btn btn-success contrast-success-btn']");
    private final static By MX_IPHONE_CONTINUE_PAYMENT_BUTTON=By.xpath("//*[text()='Continuar en PayPal']");
    public final static By PAYMENTPAGE_SHIPPINGOPTIONS = By.cssSelector("#checkout-shipping-method-load");
    private static final By SHIPPING_METHODS_TABLE = By.cssSelector(".table-checkout-shipping-method > tbody");
    private static final By SHIPPING_METHOD_RADIO_BTN = By.cssSelector(".radio.active");
    public final static By EXPRESS_DELIVERY_PAYMENT_METHOD = By.cssSelector("td#label_method_bestway_tablerate");
    public final static By EXPRESS_DELIVERY_PAYMENT_METHOD_CHECKBOX = By.xpath("//td[input[@value='tablerate_bestway']]");
    // In store subs
    public static final By SUBSCRIBE_PRO_PAYMENTS_BUTTON = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method.payment-method-subscribe_pro > div.payment-method-title.field.choice > label > span");

    //Vype CO
    public By COCreditCardOption = By.cssSelector("input#openpay_cards.radio");
    private By COcreditCardHolderNameField = By.cssSelector("#openpay_cards_cc_name");
    private By COcreditCardNumberField = By.cssSelector("#openpay_cards_cc_number");
    private By COcardExpirationDateMonthDropDown = By.cssSelector("select#openpay_cards_expiration.select.select-month");
    private By COcardExpirationDateYearDropDown = By.cssSelector("select#openpay_cards_expiration_yr.select.select-year");
    private By COcreditCardVerificationNumberField = By.cssSelector("#openpay_cards_cc_cid");
    private By COdebitCardOption = By.cssSelector("#checkout-payment-method-load > div > div > div:nth-child(3) > div.payment-method-title.field.choice > label");
    private static final By MOBILE_CO_DEBIT_CARD_OPTION = By.cssSelector("#checkout-payment-method-load > div > div > div:nth-child(3) > div.payment-method-title.field.choice > label");
    private By subcriptionDebitCreditCardOption = By.cssSelector(".payment-method-subscribe_pro,div.payment-method label[for='worldpay_cc']");
    private By bankName = By.cssSelector("select#bank_type");
    private By personType = By.cssSelector("select#user_type");
    private By docType = By.cssSelector("select#doc_type");
    private By identificationID = By.cssSelector("input#identification_number");
    private By paymentBtn = By.cssSelector("button#showModal");
    private By continueBtn = By.cssSelector("button#confirmInitChargeBtn");
    private By continueBtn2 = By.cssSelector("#lblTo");
    private static final By NEWADDRESS_CANCEL_BUTTON = By.cssSelector("button.action.secondary.action-hide-popup:nth-child(2) > span:nth-child(1)");
    private static final By NEWADDRESS_CANCEL_BUTTON_MX = By.cssSelector("aside.modal-popup.new-address.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
    private static final By AGE_VERIFICATION_WARNING = By.cssSelector("div.payment-method._active div.checkout-agreement-text > strong:nth-child(3)");
    private static final By AGE_VERIFICATION_WARNING_MX = By.cssSelector("div.health-warning");
    private static final By CARD_PAYMENTS_RADIO_BUTTON = By.cssSelector("input#worldpay_cc.radio");
    private static final By PAYMENT_METHOD_TITLE_FR = By.cssSelector(".payment-method-title.field.choice");
    private static final By CARD_PAYMENTS_TEXT = By.cssSelector("#checkout-payment-method-load > div > div > div:nth-child(2) > div.payment-method-title.field.choice > label > span");
    private static final By AGREEMENT_WORLDPAY = By.cssSelector("#agreement_worldpay_cc_1");
    private static final By AGREEMENT_WORLDPAY_APM = By.cssSelector("input#agreement_worldpay_apm_1");
    public final static By TELEPHONE_NUMBER_FIELD_CHECKOUT = By.xpath("//div[@name='shippingAddress.telephone'][contains(@class,'required')]");
    private final static By CHECKBOX_DPD_SHIPPING_OPTION_GLO = By.cssSelector("input#input_uid_0.radio");
    private final static By CHECKBOX_DPD_SHIPPING_OPTION_GLO_GUEST = By.cssSelector("input#input_uid_18.radio");
    private static final By OPENPAY_OPTION_VUSECO = By.cssSelector("#checkout-payment-method-load div.payment-group input#openpay_banks");
    private static final By OPENPAY_TC_VUSECO = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content div.checkout-agreements-block > div > div > div > label > span");
    private static final By OPENPAY_PLACEORDER_VUSECO = By.cssSelector("div.payment-method._active >div.payment-method-content > div.actions-toolbar > div > button");
    private static final By VUSECO_CARDPAYMENT = By.cssSelector("[for='openpay_cards']");
    private static final By M_VUSECO_CARDPAYMENT = By.cssSelector("#checkout-payment-method-load > div > div > div:nth-child(3) > div:nth-child(1)");
    private static final By AGE_VERIFICATION_POP_UP=By.cssSelector("div.age-verification-pop-up-content>h3");
    private static final By UPDATE_ID_NUMBER_BUTTON=By.cssSelector("a#btn-age-verification-consent");
    public static final By DROP_THE_POD_LINK=By.cssSelector("#checkoutSteps > div.pod-recyle-block > p > a");
    //Parcel Shop Locator Pop-Up
    private final static By PARCELSHOP_LOCATOR_POPUP_SHOW_MAP_BUTTON = By.cssSelector("div.gls-additional-data-wrapper.gls-parcelshop:nth-child(4) div > button.showmap");
    private final static By GLS_LOCATOR_POPUP = By.cssSelector("#gls-popup-map");
    private final static By GLS_MAP_INSIDE_POPUP = By.cssSelector("#gls-map");
    private final static By GLS_MAP_POPUP_CONTINUE_BUTTON = By.xpath("//div[@id='gls-popup-map']//following::footer//button/span");
    private final static By GLS_MAP_POPUP_CLOSE_BUTTON = By.xpath("//aside[@role='dialog']//h1//following::button[@class='action-close'][2]");
    private final static By GLS_MAP_POPUP_CLOSED = By.cssSelector("aside.modal-popup.modal-slide._inner-scroll._show:nth-child(6) div.modal-inner-wrap:nth-child(2) header.modal-header > button.action-close");
    private final static By loader = By.cssSelector("div.loader img");

    // Add to card and apply discount
    private final static By SEARCH_ICON = By.cssSelector("div[class='column vype_search'] i");
    private final static By SEARCH_FIELD = By.cssSelector("#search");
    private final static By ADDTOCART_BUTTON = By.cssSelector("#product-addtocart-button");
    private final static By CANCEL_COUPON_CODE = By.cssSelector("div.fieldset.coupon.applied div.actions-toolbar:nth-child(3) > button.action.cancel.secondary");
    private final static By CANCEL_COUPON_CODE_VYPEFR = By.cssSelector("button.action-cancel.secondary");
    private final static By CANCEL_COUPON_CODE_FOR_IT = By.cssSelector("div.fieldset.coupon.applied div.actions-toolbar:nth-child(3) div.primary > button.action.cancel.primary,button.action.action-cancel.secondary");
    private final static By APPLY_DISCOUNTCODE_FIELD_FOR_VYPEIT = By.cssSelector("div.fieldset.coupon div.actions-toolbar:nth-child(3) div.primary > button.action.apply.primary,button.action.apply.action-apply");
    private final static By CANCEL_COUPON_CODE_FOR_LYFT = By.cssSelector("button.action.action-cancel.secondary");
    private final static By CANCEL_COUPON_CODE_FOR_DE = By.cssSelector("button.action.cancel.secondary");
    private final static By CANCEL_COUPON_CODE_FOR_GLO_DE = By.cssSelector("button.action.cancel.primary");
    private final static By APPLY_DISCOUNT_DROPDOWN_GLO = By.cssSelector("#block-discount-heading");
    private final static By APPLY_DISCOUNT_BUTTON_GLO_DE = By.cssSelector("button.action.apply.action-apply");
    private final static By CANCEL_COUPON_CODE_FOR_GLO_IT = By.xpath("//div[@class='actions-toolbar']//button[contains(@value,'ANNULLA')]");
    private final static By APPLY_DISCOUNT_BUTTON_GLO_IT = By.cssSelector("#discount-form > div.actions-toolbar > div > button");
    private final static By APPLY_DISCOUNT_BUTTON_GLO_KZ = By.cssSelector("button.action.action-apply");
    private final static By CANCEL_COUPON_CODE_FOR_GLO_KZ = By.cssSelector("button.action.cancel.primary,button.action.action-cancel.secondary");
    private final static By MINI_CART_BUTTON = By.cssSelector("a.action.showcart");
    private final static By MINI_CART_BUTTON_FOR_DE = By.xpath("//div[contains(@class,'minicart-container')]//a[contains(@class,'showcart')]");
    private final static By totalChargesMiniCart = By.cssSelector("div.minicart_grandtotal>div>span");
    private final static By TOTAL_CHARGES_MINICART_DE = By.cssSelector("div.sliding-panel-section.sliding-minicart:nth-child(3) div.minicart-wrapper div.block.block-minicart:nth-child(2) div.block-content div.subtotal:nth-child(5) div.minicart_grandtotal:nth-child(5) div.amount.price-container span.price-wrapper.price-excluding-tax > span.price");
    private final static By LOADING_CIRCLE = By.cssSelector("img[alt='Laddar...']");
    private final static By UK_CANCEL_COUPON_CODE=By.cssSelector("button.action.action-cancel.secondary");
    private final static By APPLY_DISCOUNT_BUTTON_UK=By.cssSelector("button.action.apply.action-apply");
    public final static By CANCEL_DISCOUNT_BUTTON_GLODE = By.cssSelector("button.action.action-cancel.secondary");
    private final static By ORDER_SUMMARY_ON_CART = By.cssSelector(".summary.title");
    private final static By PRODUCT_PRICE_BASKET = By.cssSelector("td.col.price > span.price-including-tax > span > span");
    private final static By PRODUCT_QUANTITY_BASKET = By.cssSelector("td.col.qty > div > div input");
    private final static By TOTAL_SUMMARY_CARY_AMOUNT_BASKET = By.cssSelector("tr.grand.totals.incl > td > strong > span");


  //SubscribePro locators
    public By SubsCreditCardOption = By.cssSelector("input#subscribe_pro.radio");
    public By cardNumberIframe = By.cssSelector("iframe[name*='spreedly-number-frame']");
    private By cardCVVIframe = By.cssSelector("iframe[name*='spreedly-cvv-frame']");
    private By subscrCardNumber = By.cssSelector("input#card_number");
    private By subscrCardMonth = By.cssSelector("input#subscribe_pro_expiration");
    private By subscrCardYear = By.cssSelector("input#subscribe_pro_expiration_yr");
    private By subscrCvv = By.cssSelector("input#cvv");
    public By subscrSavedCardsLabel = By.cssSelector(".payment-group div.payment-method:nth-child(2) label[for*=\"subscribe_pro_vault\"]");

    //Vuse
    private static final By ALTERNATIVE_PAYMENT_OPTIONS = By.cssSelector("#worldpay_apm");
    private static final By BUTTON_SOFORT_CONTINUE_OPTION = By.cssSelector("#jsEnabled > a");
    private static final By RADIO_SOFORT_PAYMENT_OPTION = By.cssSelector("#apm_SOFORT-SSL");
    private static final By RADIO_PAYPAL_OPTIONS = By.cssSelector("input#paypal_express");
    private static final By RADIO_PAYPAL_OPTIONS_SE = By.cssSelector("div#worldpay_apm_cc_type_div  label");
    private static final By RADIO_PAYPAL_VUSEDE = By.cssSelector("span[for='apm_PAYPAL-EXPRESS']");
    private static final By BUTTON_PAYPAL_CONTINUE = By.cssSelector("#PMMakePayment");
    private static final By RADIO_BUTTON_PAYGATE=By.cssSelector("input#paygate.radio");
    private static final By LABEL_PAYGATE=By.cssSelector("div.payment-method-title.field.choice label");
    private static final By SID_SECURE_BUTTON=By.cssSelector("button#pmSidBtn");
    private static final By SID_FIRST_NAME=By.cssSelector("input#sidCUSTOMER_NAME_INPUT");
    private static final By SID_FIRST_BANK=By.cssSelector("div.bank-card");
    private static final By SID_IFRAME = By.cssSelector("iframe#iFrameSID");
    private static final By NEXT_BUTTON=By.cssSelector("button#nextBtn");
    private static final By CONFIRM_SID_BUTTON=By.cssSelector("div.au-target.sid-button");
    private static final By SID_TEST_COMPLETED_BUTTON =By.cssSelector("div.bank-list-wrapper div:nth-child(1) div");
    private static final By CARD_BUTTON=By.cssSelector("button#pmCreditcardBtn");
    private static final By CARD_HOLDER_NAME=By.cssSelector("input#ccName");
    private static final By CARD_NUMBER=By.cssSelector("input#ccNumber");
    private static final By MONTH_DD=By.cssSelector("select#ccOpMonth");
    private static final By YEAR_DD=By.cssSelector("select#ccOpYear");
    private static final By CVV_NUMBER=By.cssSelector("input#ccCvv");
    private final static By CHECKOUT_CARTRIDGE_POPUP = By.cssSelector("aside.modal-popup.mx-checkout-cartridge-popup.modal-slide._inner-scroll._show div.entry-checkout-confirmation");
    public static final By CHECKOUT_CARTRIDGE_POPUP_PROCEED_BUTTON = By.cssSelector("footer.modal-footer > button.action.primary:nth-child(2)");
    private final static By REMOVE_TOFINO_PRODUCT = By.cssSelector("div.invalid-postcode-message-content>p>a");
    private final static By SUBMIT_BUTTON=By.cssSelector("input[name='UsernamePasswordEntry']");
    private final static By LOADER_IMAGE=By.cssSelector("img[src='images/loader.gif']");
    private final static By IFRAME_SUBMIT_PAYMENT=By.cssSelector("iframe.redirectIframe");
    private final static By SHIPPING_METHODS_FREE_PRICE = By.cssSelector("table > tbody > tr:nth-child(1) > td.col.col-price > span");
    private final static By SHIPPING_METHODS_FREE_RADIO = By.cssSelector("table > tbody > tr:nth-child(1) > td:nth-child(1)");
    private final static By SHIPPING_METHODS_STANDARD_PRICE = By.cssSelector("table > tbody > tr:nth-child(2) > td.col.col-price > span");
    private final static By SHIPPING_METHODS_STANDARD_RADIO = By.cssSelector("table > tbody > tr:nth-child(2) > td:nth-child(1)");
    private final static By SHIPPING_METHODS_DELIVERY_PRICE = By.cssSelector("table > tbody > tr:nth-child(3) > td.col.col-price > span");
    private final static By SHIPPING_METHODS_DELIVERY_RADIO = By.cssSelector("table > tbody > tr:nth-child(3) > td:nth-child(1)");
    private final static By VUSE_DE_DISCOUNT_TITLE = By.cssSelector(".rule-name");
    private final static By VUSE_DE_DISCOUNT_AMOUNT = By.cssSelector(".rule-amount");
    private final static By DELIVERY_METHOD_TITLE = By.cssSelector("#opc-shipping_method > div > div.step-title");
    private final static By CHANGE_ADDRESS_LINK_ZA=By.cssSelector("#checkout-step-shipping > div.selected-address__container > a");
    private final static By ADD_NEW_ADDRESS_ZA=By.cssSelector("button.alink.address-list__new-button");
  private final static By ADD_NEW_ADDRESS_SAVE_BUTTON_VELOZA=By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.new-address.modal-slide._inner-scroll._show > div.modal-inner-wrap > footer > button.action.primary.action-save-address");
    private final static By ADDRESS_MANUAL_ENTRY_ACTIVE=By.cssSelector("div.fieldset.address-fields.address-manual.active");
    public final static By ADDRESS_RESTRICTION_ERROR_MESSAGE= By.cssSelector("div.restiction-message");
    public final static By CHECKOUT_DELIVERY_METHODS = By.cssSelector("table.table-checkout-shipping-method tbody:nth-child(2) tr.row td.col.col-method:nth-child(1) > label:nth-child(2)");
    public final static By PAYMENT_ADDRESS_BOOK_ADD_NEW_ADDRESS_TELEPHONE= By.cssSelector("#shipping-new-address-form > div:nth-child(3) > div >input");
  public final static By PAYMENT_ADDRESS_BOOK_STREET_ADDRESS_VELOZA= By.cssSelector("#shipping-new-address-form > fieldset > div > div:nth-child(1) > div >input");
  public final static By PAYMENT_ADDRESS_BOOK_SUBURB_VELOZA= By.cssSelector("#shipping-new-address-form > fieldset > div > div.field.additional._required > div >input");
  public By province = By.cssSelector("#shipping-new-address-form > div:nth-child(8) > div >select");
  public final static By POSTCODE_PAYMENTPAGE = By.cssSelector("#shipping-new-address-form > div:nth-child(11) > div >input");
  public final static By CITY_PAYMENTPAGE = By.cssSelector("#shipping-new-address-form > div:nth-child(10) > div >input");


    //Add New Address
    private final static By CHANGE_ADDRESS_LINK=By.cssSelector("a.selected-address__change-button.alink");
    private final static By ADD_NEW_ADDRESS_BUTTON_UK=By.cssSelector("button.alink.address-list__new-button > span");
    private final static By ENTER_ADDRESS_MANUALLY_LINK=By.cssSelector("span.address-search__toggle");
    public final static By USE_THIS_ADDRESS_BUTTON =By.cssSelector("button.action.primary.action-save-address > span");
    public final static By PHONE_NUMBER_FIELD=By.cssSelector("#shipping-new-address-form > div> div.control._with-tooltip>input");
    public final static By STREET_ADDRESS_FIELD_CHECKOUT = By.cssSelector("[name='shippingAddress.street.0'] > div.control > input");
    public final static By CITY_FIELD_CHECKOUT=By.cssSelector("[name='shippingAddress.city'] > div.control > input");
    public final static By POSTAL_CODE_FIELD_CHECKOUT=By.cssSelector("[name='shippingAddress.postcode'] > div.control > input");
    public static final By NEWSLETTER_SUBSCRIPTION_COMPONENT=By.cssSelector("button.action.subscribe.primary > span");
    private static final By VISA_CARD_OPTION_GLODE = By.cssSelector("input#direct_cc_VISA-SSL");
    public static final By COMPANY_ADDRESS_FIELD = By.cssSelector("form.form.form-shipping-address  div.field:nth-child(3) > div.control > input[name='company']");
    public static final By STREET_ADDRESS1_FIELD = By.cssSelector("div.fieldset.address:nth-child(2) div.control div.field> div.control > input[name='street[0]']");
    public static final By STREET_ADDRESS2_FIELD = By.cssSelector("div.fieldset.address:nth-child(2) div.control div.field> div.control > input[name='street[1]']");
    private static final By WALLET_OPTION = By.cssSelector("input#worldpay_wallets");
    private static final By GOOGLE_PAY_CHECKBOX = By.cssSelector("input[value='PAYWITHGOOGLE-SSL']");
    private static final By GOOGLE_PAY_TERMS_AND_CONDITIONS = By.cssSelector("#agreement_worldpay_wallets_1");
    private static final By OPENPAY_HEADER = By.cssSelector("h3");
    private static final By CHECKOUTPAGE_PRODUCT_DETAILS_EXPENSION = By.cssSelector(".title[data-role='title']");
    private static final By CHECKOUTPAGE_ENGRAVING_DETAILS_CONFIRMATION = By.cssSelector("div[class='psn-options']");
    private static final By MANUAL_ADDRESS_ENTRY_LINK_VUSEDE = By.xpath("//*[text()='Manuelle Adresseingabe']");
    public By CHECKOUT_PATMENT_RADIO_PAYPAL=By.cssSelector("#apm_PAYPAL-EXPRESS");
    public By CHECKOUT_PATMENT_RADIO_SOFORT=By.cssSelector("#apm_SOFORT-SSL");
    public By CHECKOUT_PATMENT_BUTTON_ALT=By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button");
    public By CHECKOUT_PATMENT_BUTTON=By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.checkout-agreements-block-wrapper > div.actions-toolbar > div > button > span");
    private static final By AGE_VERIFICATION_WARNING_IT = By.cssSelector("div.payment-method._active > div.payment-method-content > div.payment-method-billing-address > div > p");
    private static final By DELIVERY_ADDRESS_HEADING = By.cssSelector(".selected-address__heading");
    private static final By CURRENT_DELIVERY_ADDRESS = By.cssSelector("p.selected-address__address");
    private static final By CURRENT_DELIVERY_ADDRESS_GLOIT = By.cssSelector("div.shipping-address-item.selected-item");
    private static final By DELIVERY_ADDRESS_NAME = By.cssSelector(".selected-address__name");
    private static final By DELIVERY_ADDRESS_PHONE = By.cssSelector(".selected-address__phone");
    private static final By DELIVERY_METHOD_HEADING = By.cssSelector("div.checkout-shipping-method > div.step-title");
    private static final By DELIVERY_METHOD_OPTIONS = By.cssSelector("#checkout-shipping-method-load > table > tbody > tr");
    private static final By ORDER_SUMMARY_TITLE = By.cssSelector("#opc-sidebar > div.opc-block-summary > span");
    private static final By PREVIOUSLY_USED_ADDRESSES = By.cssSelector("#address-list-collapsible > div > div > div > div.os-padding > div > div > div");
    private static final By AVALANCHE_ADD_NEW_ADDRESS_BUTTON = By.cssSelector("#address-list-collapsible > div > button.alink.address-list__new-button");
    private static final By AVALANCHE_CHANGE_ADDRESS_BUTTON = By.cssSelector("#checkout-step-shipping > div.selected-address__container > a");
    private static final By ADD_NEW_ADDRESS_CLOSE = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.new-address.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
    private static final By ADD_NEW_ADDRESS_MODAL = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.new-address.modal-slide._inner-scroll._show > div.modal-inner-wrap");
    private static final By CARD_PAYMENTS_EXPANDABLE_LINK = By.cssSelector(".payment-method.payu-payment > .choice.field.payment-method-title > .label");
    private static final By CARD_PAYMENTS_EXPANDABLE_LINK_VELO_BE = By.cssSelector(".payment-method > .choice.field.payment-method-title > .label");
    private static final By PAYMENT_SUMMARY = By.cssSelector("div.payment-summary");
    private static final By HEADING_TEXT = By.cssSelector("span.heading-text");
    public static final By AVALANCHE_AGREEMENT_TICKBOX = By.cssSelector("[id*='agreement_worldpay_cc']");
    public static final By AVALANCHE_ORDER_CONFIRMATION_EMAIL_TEXT = By.cssSelector("#maincontent > div.columns > div > div.checkout-success > p.checkout-message");
    public static final By AVALANCHE_ORDER_CONFIRMATION_GOT_TO_MY_ACCOUNT_LINK = By.cssSelector("#maincontent > div.columns > div > div.checkout-success > div > div > a[href$='account/']");
    public static final By AVALANCHE_ORDER_CONFIRMATION_RETURN_TO_HOME_LINK = By.cssSelector("#maincontent > div.columns > div > div.checkout-success > div > div > a.action.secondary.continue");
    public static final By AVALANCHE_PLACE_ORDER_BUTTON = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.checkout-agreements-block-wrapper > div.actions-toolbar > div > button > span");
  public static final By AVALANCHE_PLACE_ORDER_BUTTON_VELOZA = By.cssSelector("#paygateButton");
  public static final By AVALANCHE_DELIVERY_METHOD =By.cssSelector("#checkout-shipping-method-load > table > tbody > tr");
    public static final By AVALANCHE_ORDER_CONFIRMATION_THANKYOU = By.cssSelector("#maincontent > div.page-title-wrapper > h1 > span");
    public static final By AVALANCHE_ORDER_CONFIRMATION_MESSAGES = By.cssSelector("#maincontent > div.columns > div > div.checkout-success > p");
    public static final By AVALANCHE_PRODUCTS_PL = By.cssSelector("li:nth-of-type(2) > .velo-cta-style.velo-navigation-group-list-item-link");
    public static final By AVALANCHE_PRODUCTS_BE = By.cssSelector("div.bat-header-menu > div > nav > div > ul > li:nth-child(1) > a");
    public static final By AVALANCHE_VIEW_BASKET_BUTTON = By.cssSelector("a[href*='checkout/cart/']");
    public static final By PL_SHOW_CART= By.cssSelector(".action.showcart");
    public static final By PL_VIEW_BASKET_BUTTON = By.cssSelector(".action.primary.viewcart");
    public static final By AVALANCHE_CHECKOUT_BUTTON = By.cssSelector("button.action.primary.checkout");
  public static final By AVALANCHE_VELOZA_UPDATEID_BUTTON = By.cssSelector("#btn-age-verification-consent > span");
    public static final By AVALANCHE_VISA_MC_SELECTION_BUTTON = By.cssSelector("img[src$='pbl_c.png']");
    public static final By AVALANCHE_DELIVERY_OPTION_TITLE = By.cssSelector("td.col-method > strong.col-method");
    public static final By AVALANCHE_DELIVERY_OPTION_DESCRIPTION = By.cssSelector("span.col-carrier");
    public static final By FIRST_ORDER_DISCOUNT_LABEL_VELOPL = By.cssSelector("#cart-totals > div > table > tbody > tr.total-rules.amasty-rules > th > span");
    public static final By FIRST_ORDER_TOTAL_VALUE_VELOPL = By.cssSelector("#cart-totals > div > table > tbody > tr.grand.totals.incl > td > strong > span");
    public static final By FIRST_ORDER_PRICE_VELOPL = By.cssSelector("#cart-totals > div > table > tbody > tr.totals.sub > td > span");
    public static final By FIRST_ORDER_DISCOUNT_PRICE_VELOPL = By.cssSelector("#cart-totals > div > table > tbody > tr.total-rules.amasty-rules > td > span");
    public static final By INCREASE_BASKET_QTY_VELOPL = By.cssSelector("#shopping-cart-table > tbody > tr > td.col.product-item-details > div.cart-actions > div.field.qty > div > input.qty-modifier.plus");
    public static final By DISCOUNT_LABEL_VELOPL = By.cssSelector("#cart-totals > div > table > tbody > tr:nth-child(3) > th > span");
    public static final By VAT_TAX_LABEL_VELOPL = By.cssSelector("#cart-totals > div > table > tbody > tr.totals-tax-details > th");
// avalanche Mobile
    public static final By M_AVALANCHE_HAMBURGER_MENU = By.cssSelector("div.bat-header-menu-button > button > i");
    public static final By M_AVALANCHE_HANBURGER_MENU_OPTIONS = By.cssSelector("div.bat-header-menu.open > div > nav > div > ul > li > a");
    public static final By M_AVALANCHE_DELIVERY_OPTION_DESCRIPTION = By.cssSelector("#label_carrier_flatrate_flatrate");

    private static final By NEW_ADDRESS_CLOSE_BUTTON = By.cssSelector(".new-address .action-close");
    private static final By CARD_OPTION_VUSEFR = By.cssSelector("div>div>label[for='worldpay_cc']");
    private static final By CARD_TYPE = By.cssSelector("input#cardTypeCredit");
    private static final By CLOSE_POPUP_LYFT_SE = By.cssSelector(".modal-footer .action.secondary.action-hide-popup");
    private static final By AGE_VERIFICATION_BLOCK_IT= By.cssSelector("div.reach_age_verification_doc_description_block");
    private static final By VERIFY_AGE_BUTTON=By.cssSelector("a.button.action.primary.reach_age_verification-btn");
    private static final By PHOTO_SIZE_INFO=By.cssSelector("div.reach_age_verification-disclaimer");
    private final static By LAST_MILE_DELIVERY=By.cssSelector("button.alink.address-list__new-button");
    public final static By UPS_DELIVERY_COMPANY_CHECKBOX=By.cssSelector("button.alink.address:nth-child(1)");
    public final static By DPD_PICKUP_POINT_DELIVERY_COMPANY_CHECKBOX=By.cssSelector("button.alink.address:nth-child(2)");
    public final static By DPD_DOOR_TO_DOOR_DELIVERY_COMPANY_CHECKBOX=By.cssSelector("button.alink.address:nth-child(3)");
    public final static By ARIA_BUSY_FALSE = By.cssSelector("body[aria-busy='false']");

    public void enterSelectPackageShopDetails() throws Exception {
        String whichLocale = UrlBuilder.getLocale();
        switch (whichLocale) {
          case "dk":
          case "vusedk":
            waitForExpectedElement(GLS_SHIPPING_METHOD, 20);
                if (getWebDriver().findElements(GLS_SHIPPING_METHOD).size() > 0 && waitForExpectedElement(GLS_SHIPPING_METHOD).isSelected()) {
              waitForExpectedElement(enterZipCode, 30);
              waitClearAndEnterText(enterZipCode, "2100");
              waitForExpectedElement(ParcelShopOptions, 20);
              waitClearAndEnterText(packagePickPersonName, "Manish");
              waitForExpectedElement(phoneNumberAdvising, 10);
              waitClearAndEnterText(phoneNumberAdvising, "9078803097");
            }
            break;
          case "lyftse":
            invisibilityOfElementLocated(LOADING_MASK);
            waitForAjaxElementNotToBePresent(getWebDriver(),15);
            assertThat(waitForExpectedElement(PARCEL_SHOP_SHIPPING_METHOD, 10).isDisplayed())
                .as("parcel shop shipping method is not present")
                .isTrue();
            if (!waitForExpectedElement(PARCEL_SHOP_SHIPPING_RADIOBUTTON, 10).isSelected()) {
              invisibilityOfElementLocated(LOADING_CIRCLE);
              clickByElementByQueryJSExecutor(PARCEL_SHOP_SHIPPING_RADIOBUTTON_LYFTSE);
            }
            waitForExpectedElement(EnterZipCode_lyft, 10);
            waitClearAndEnterText(EnterZipCode_lyft, "11164");
            waitClearAndEnterText(PhoneNumberAdvising_lyft, "9078803097");
            break;
          case "lyftdk":
            Thread.sleep(5000);
            waitForAjaxElementNotToBePresent(getWebDriver(),10);
            waitForExpectedElement(enterZipCode, 30);
            waitClearAndEnterText(enterZipCode, "2100");
            try {
              Select shop = new Select(webDriver.findElement(PARCEL_SHOP_PICKUP_ID));
              shop.selectByIndex(0);
                } catch (org.openqa.selenium.StaleElementReferenceException ex)
                {
              Select shop = new Select(webDriver.findElement(PARCEL_SHOP_PICKUP_ID));
              shop.selectByIndex(0);
            }
            waitClearAndEnterText(packagePickPersonName, "Manish");
            waitClearAndEnterText(phoneNumberAdvising, "9078803097");
            break;
          default:
            LOG.info("**** ERROR - NO MATCH FOUND");
        }
    }

    public void pressValidatePaymentButton() throws InterruptedException {
        Thread.sleep(3000);
        clickUsingJS(validatePaymentButton);
    }

    public boolean isBillingSameAsAddressTicked() {
        return waitForExpectedElement(billingSameAsShippingAddressCheckBox, 20).isEnabled();
    }

    public Boolean isOrderSummaryPresent() {
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
                if(getWebDriver().findElements(CHECKOUT_CARTRIDGE_POPUP).size()>0)
                    clickByElementByQueryJSExecutor(CHECKOUT_CARTRIDGE_POPUP_PROCEED_BUTTON);
                waitForExpectedElement(MXCreditCardOption, 60);
                break;
            case "it":
               if(UrlBuilder.isMobile())
               {
                 waitForExpectedElement(MINI_CART_BUTTON_IT).click();
                 if (isElementPresent(orderSummaryHeading)){
                   LOG.info("Able to open Order Summary pop up.");
                   clickByElementByQueryJSExecutor(POPUP_ClOSE_BUTTON_IT);
                 }
                 //Skip Order Summary check by #866544
                 return true;
               }
               break;
            case "kz":
                if (doesURLContain("glo")) {
                    waitForElementToAppearAndDisappear(LOADER, 2, 30);
                    waitForExpectedElement(GloPaymentMethod, 120).isEnabled();
                }
                break;
            case "vuseco":
              try {
                if (webDriver.getTitle().equals("Carrito")){
                  clickByElementByQueryJSExecutor(homePage.PROCEED_TO_CHECKOUT_BUTTON);
                }
              }catch (Exception e){
              }
              break;
        }
        return waitForExpectedElement(orderSummaryHeading, 60).isDisplayed();
    }

	public void pressPlaceOrderButton() {
		if(UrlBuilder.isDesktop()){
			try {
				waitForItemToBeClickableAndClick(getWebDriver(), 30, placeOrderButton);
				LOG.info("Place order button has been clicked.");
			} catch (Exception e) {
                LOG.info("Trying to select pay pal place order button");
              if(UrlBuilder.getLocale().equals("vusefr")){
                waitForItemToBeClickableAndClick(getWebDriver(), 20, PAYPAL_PLACE_ORDER_CTA_FR);
              }else if (UrlBuilder.getLocale().equals("vuseza")||UrlBuilder.getLocale().equals("glode")) {
                  waitForExpectedElement(payPalPlaceOrderButton, 10);
                  clickByElementByQueryJSExecutor(payPalPlaceOrderButton);
              }else if(UrlBuilder.getLocale().equalsIgnoreCase("veloza")){
                try {
                  if(waitForExpectedElement(payPalPlaceOrderButton,10).getAttribute("disabled").equals("true")&&doesURLContain("checkout"))
                    refreshBrowser();
                }catch(NullPointerException ex){
                  LOG.info("No need refresh.");
                }
                clickByElementByQueryJSExecutor(payPalPlaceOrderButton);
              }else {
                waitForExpectedElement(payPalPlaceOrderButton,10);
                clickByElementByQueryJSExecutor(payPalPlaceOrderButton);
                waitForExpectedElement(payPalPlaceOrderButton).click();
                waitForAjaxElementNotToBePresent(getWebDriver(), 5);
              }
			}
		}else{
          switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case IE:
                waitForExpectedElement(M_PLACE_ORDER_BUTTON_IE, 5);
                clickUsingJS(M_PLACE_ORDER_BUTTON_IE);
                break;
            case VUSEZA:
                break;
            default:
                waitForExpectedElement(placeOrderButton, 5);
                clickUsingJS(placeOrderButton);
          }
		}
	}

    public void paymentClickNextButton() {
        clickByElementByQueryJSExecutor(paymentNextButton);
    }

    public void checkMoneyOrderOptionSelected() throws InterruptedException {
        LOG.info("Trying to select checkout money order tick box");
        Thread.sleep(2000);
        clickByElementByQueryJSExecutor(checkMoneyOrder);
    }

	public void selectCreditCardOptions() {
		switch (UrlBuilder.getLocale()) {
			case "mx":
          case "vusemx":
			case "pl":
				break;
            case "vypeit":
            case "vuseit":
            case "glode":
				waitForExpectedElement(subcriptionDebitCreditCardOption,20);
                try {
                    scrollToElement(VYPE_PAYMENT_METHOD_CREDIT_CARD_IT);
                    clickUsingJS(VYPE_PAYMENT_METHOD_CREDIT_CARD_IT);
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(SubsCreditCardOption);
                }
                break;
			default:
				try {
					waitForExpectedElement(creditCardOption, 10).click();
				} catch (Exception e) {
					clickByElementByQueryJSExecutor(creditCardOption);
				}
		}
	}

	public void assertPriceOfShipping(){
      if ("lyftse".equals(getLocale())) {
        assertTrue(waitForExpectedElement(SHIPPING_COST).getText().contains(UrlBuilder.getMessage("homeDelivery.key")));
      } else {
        assertTrue(waitForExpectedElement(SHIPPING_COST).getText().contains("LIVRAISON STANDARD Inclus"));
      }
	}

	public void SelectCardPaymentThenCreditCardOption() {
		if (webDriver.getCurrentUrl().contains("nl/nl")) {
			try {
				webDriver.findElement(By.cssSelector("#worldpay_cc-form > fieldset.card-type-radios.fieldset > div > label"))
						.click();
			} catch (Exception e) {
				clickByElementByQueryJSExecutor(By.cssSelector("div.payment-method:nth-child(2) div.payment-method-title.field.choice label.label > span:nth-child(1)"));
			}
		} else if (UrlBuilder.getLocale().equalsIgnoreCase("co")) {
			selectCreditCardOptions();
		} else if (Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).equals(VYPEIT)||Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).equals(VUSEIT)) {
          selectCreditCardOption();
        } else {
            selectCreditCardOptions();
            selectCreditCardOption();
          }
	}

  public void MasterCardOrderOptionSelected() {
    switch (UrlBuilder.getLocale()) {
      case "mx":
      case "vusemx":
        waitForExpectedElement(MXCreditCardOption, 60);
        selectCreditCardOptions();
        clickTermsAndConditions();
        clickByElementByQueryJSExecutor(btnPay);
        break;
      case "lyftdk":
      case "lyftse":
        selectCreditCardOption();
        waitForExpectedElement(CHECKBOX_MASTERCARD_TYPE, 10);
        clickByElementByQueryJSExecutor(CHECKBOX_MASTERCARD_TYPE);
        break;
      case "velode":
        waitForExpectedElement(CHECKBOX_MASTERCARD_TYPE, 10);
        clickByElementByQueryJSExecutor(CHECKBOX_MASTERCARD_TYPE);
        break;
      case "vuseco":
        if (webDriver.getTitle().equals("Carrito")){
          waitForPage();
          clickByElementByQueryJSExecutor(homePage.PROCEED_TO_CHECKOUT_BUTTON);
        }
        waitForPage();
        eyesCheckCheckoutPage();
        waitForExpectedElement(VUSECO_CARDPAYMENT,30);
        LOG.info("page title is :"+webDriver.getTitle());
          clickByElementByQueryJSExecutor(VUSECO_CARDPAYMENT);
        break;
      default:
        selectCreditCardOption();
        selectMasterCardOption();
    }
  }

    public void selectCardPaymentsRadioButton(){
		switch (UrlBuilder.getLocale()) {
          case "mx":
          case "vusemx":
				waitForExpectedElement(MXCreditCardOption, 60);
				clickByElementByQueryJSExecutor(MXCreditCardOption);
				break;
            case "vuseco":
				waitForExpectedElement(COCreditCardOption, 20);
				clickByElementByQueryJSExecutor(COCreditCardOption);
				break;
			case "fr":
          case "vusefr":
          case "vuseuk":
			  waitForAjaxElementNotToBePresent(getWebDriver(),10);
              if(!isElementPresent(PAYMENT_METHOD_TITLE_FR))
              {
                refreshBrowser();
              }
              waitForExpectedElement(PAYMENT_METHOD_TITLE_FR).click();
              waitForExpectedElement(CARD_PAYMENTS_RADIO_BUTTON, 30);
              clickByElementByQueryJSExecutor(CARD_PAYMENTS_RADIO_BUTTON);
				break;
        case "gloit":
            LOG.info("PAGE TITLE : " +webDriver.getTitle());
            waitForExpectedElement(CARD_PAYMENTS_RADIO_BUTTON, 60);
            assertTrue("** ERROR ** Checkout Page hasn't fully loaded correctly",waitForExpectedElement(CARD_PAYMENTS_RADIO_BUTTON).isDisplayed());
            clickByElementByQueryJSExecutor(CARD_PAYMENTS_RADIO_BUTTON);
            break;
          case "velode":
            waitForAjaxElementNotToBePresent(getWebDriver(),5);
            waitForExpectedElement(CARD_PAYMENTS_RADIO_BUTTON, 20);
            clickByElementByQueryJSExecutor(CARD_PAYMENTS_RADIO_BUTTON);
           break;
			default:
			  try {
			    LOG.info("PAGE TITLE"+webDriver.getTitle());
                waitForExpectedElement(CARD_PAYMENTS_RADIO_BUTTON, 30);
                scrollElementIntoView(CARD_PAYMENTS_RADIO_BUTTON);
                clickByElementByQueryJSExecutor(CARD_PAYMENTS_RADIO_BUTTON);
              } catch (TimeoutException e) {
			    waitForExpectedElement(CARD_PAYMENTS_TEXT);
                clickByElementByQueryJSExecutor(CARD_PAYMENTS_TEXT);
              }
		}
    }

    public void selectSubscribeProPayments() {
      try {
        waitForExpectedElement(SUBSCRIBE_PRO_PAYMENTS_BUTTON,25);
        jsScrollElementInCenter(waitForExpectedElement(SUBSCRIBE_PRO_PAYMENTS_BUTTON));
        clickUsingJS(SUBSCRIBE_PRO_PAYMENTS_BUTTON);
      } catch (TimeoutException e) {
        waitForLoaderToDisapear();
        waitForExpectedElement(SUBSCRIBE_PRO_PAYMENTS_BUTTON,20);
        jsScrollElementInCenter(waitForExpectedElement(SUBSCRIBE_PRO_PAYMENTS_BUTTON));
        clickUsingJS(SUBSCRIBE_PRO_PAYMENTS_BUTTON);
      }
    }

	public void selectMasterCardOption() {
		switch (UrlBuilder.getLocale()) {
          case "mx":
          case "vusemx":
				break;
			default:
			    waitForAjaxElementNotToBePresent(getWebDriver(),10);
				waitForItemToBeClickableAndClick(masterCardOption,20);
		}
	}

	public void selectDebitCardOption() {
		if (webDriver.getCurrentUrl().contains("nl/nl") || (webDriver.getCurrentUrl().contains("de/de") || (webDriver.getCurrentUrl().contains("ie/en")))) {
			try {
				webDriver.findElement(By.cssSelector("#worldpay_cc-form > fieldset.card-type-radios.fieldset > div > label"))
						.click();
			} catch (Exception e) {
				clickByElementByQueryJSExecutor(By.cssSelector("#worldpay_cc-form > fieldset.card-type-radios.fieldset > div > label"));
			}
		} else {
			// click the debit card option
			waitForExpectedElement(By.cssSelector("#worldpay_cc-form > fieldset.card-type-radios.fieldset > div:nth-child(2) > label"), 10);
			clickByElementByQueryJSExecutor(By.cssSelector("#worldpay_cc-form > fieldset.card-type-radios.fieldset > div:nth-child(2) > label"));
		}
	}

	public void selectCreditCardOption() {
            waitForLoaderToDisapear();
			waitForExpectedElement(By.cssSelector("input#worldpay_cc.radio"), 30);
			clickByElementByQueryJSExecutor(By.cssSelector("input#worldpay_cc.radio"));
			clickByElementByQueryJSExecutor(By.cssSelector("input#cardTypeCredit"));
			waitForAjaxElementNotToBePresent(getWebDriver(), 20);
		}

	public void enterValidMasterCardDetailsAndSubmit() {
		enterCardHolderName(random(8, ALPHABETS));
		enterCustomCreditCardNumber("5555555555554444");
		enterCardExpiryMonthAndyear();
		enterCreditCardVerificationNumber();
	}

  public void VISACardOrderOptionSelected() throws InterruptedException {
    switch (UrlBuilder.getLocale()) {
      case "mx":
      case "vusemx":
        if(getWebDriver().findElements(CHECKOUT_CARTRIDGE_POPUP).size()>0) {
          waitForExpectedElement(CHECKOUT_CARTRIDGE_POPUP_PROCEED_BUTTON, 10).click();
        }
        waitForExpectedElement(MXCreditCardOption, 60);
        selectCreditCardOptions();
        clickTermsAndConditions();
        clickUsingJS(waitForExpectedElement(btnPay, 30));
        Thread.sleep(15000);
        break;
      case "lyftdk":
      case "lyftse":
      case "vusedk":
      case "uk":
      case "ie":
      case "fr":
      case "vusefr":
        waitForLoaderToDisapear();
        if(UrlBuilder.isMobile())
        {
          scrollToElement(CARD_OPTION_VUSEFR);
        }
        waitForExpectedElement(CARD_OPTION_VUSEFR, 30).click();
        clickByElementByQueryJSExecutor(CARD_TYPE);
        waitForAjaxElementNotToBePresent(getWebDriver(), 20);
        waitForExpectedElement(CHECKBOX_VISACARD_TYPE, 10);
        clickByElementByQueryJSExecutor(CHECKBOX_VISACARD_TYPE);
        break;
      case "velode":
        selectCreditCardOption();
        waitForExpectedElement(CHECKBOX_VISACARD_TYPE, 10);
        clickByElementByQueryJSExecutor(CHECKBOX_VISACARD_TYPE);
        break;
      case "glode":
        selectCreditCardOption();
        waitForExpectedElement(VISA_CARD_OPTION_GLODE, 10);
        clickByElementByQueryJSExecutor(VISA_CARD_OPTION_GLODE);
        break;
      case "vypeit":
        case "vuseit":
        selectCreditCardOption();
        waitForExpectedElement(VISA_CARD_OPTION_VYPEIT, 10);
        clickByElementByQueryJSExecutor(VISA_CARD_OPTION_VYPEIT);
        break;
      default:
        selectCreditCardOption();
        waitForExpectedElement(visaCardOption, 10);
        clickByElementByQueryJSExecutor(visaCardOption);
    }
  }

  public void enterInvalidVISACardDetailsAndSubmit(VisaDetail visa)  {
        selectDebitCardOption();
        enterCustomCreditCardNumber(visa.getCc_number());
        enterCardHolderName(visa.getCc_holderName());
        if (!visa.getExpire_month().trim().isEmpty()) {
          selectValueFromDropDownByIndex(Integer.valueOf(visa.getExpire_month()),
              cardExpirationDateMonthDropDown);
        }
        if (!visa.getExpire_year().trim().isEmpty()) {
          selectValueFromDropDownByIndex(Integer.valueOf(visa.getExpire_year()),
              cardExpirationDateYearDropDown);
        }
        waitForExpectedElement(creditCardVerificationNumberField).sendKeys(visa.getSecurityCode());
        if (visa.getTickTC().equals("y")) {
          clickTermsAndConditionsBox();
        }

  }

  public void enterInvalidVISACardDetailsAndSubmit() {
    switch (UrlBuilder.getLocale()) {
      case "vusedk":
      case "vusede":
        selectDebitCardOption();
        enterCardHolderName(UrlBuilder.getMessage("invalidCardHoldName.key"));
        enterCustomCreditCardNumber(UrlBuilder.getMessage("invalidCardNumber.key"));
        enterInvalidCreditCardVerificationNumber();
        break;
    }
  }

	public void enterValidVISACardDetailsAndSubmit() {
		switch (UrlBuilder.getLocale()) {
          case "mx":
          case "vusemx":
				clickTermsAndConditions();
				clickUsingJS(waitForExpectedElement(btnPay, 30));
				enterValidVISACardDetailsForMX(UrlBuilder.getMessage("cardHolderName.key"), UrlBuilder.getMessage("masterCreditCardNumber.key"), UrlBuilder.getMessage("MasterCardSecurityCode.key"));
				break;
			case "pl":
				enterCardHolderName(random(8, ALPHABETS));
				enterCustomCreditCardNumber("4111111111111111");
				enterCardExpiryMonthAndyear();
				enterCreditCardVerificationNumber();
				break;
			default:
				selectDebitCardOption();
				enterCardHolderName(random(8, ALPHABETS));
				enterCustomCreditCardNumber("4444333322221111");
				enterCardExpiryMonthAndyear();
				enterCreditCardVerificationNumber();
		}
	}

  public void enterValidVISACardDetailsAndSubmitForTrial() {
        enterCardHolderName(random(8, ALPHABETS));
        enterCustomCreditCardNumber("4444333322221111");
        enterCardExpiryMonthAndyear();
        enterCreditCardVerificationNumber();
  }

	public void enterValidAMEXCardDetailsAndSubmit() {
		enterCardHolderName(random(8, ALPHABETS));
		enterCustomCreditCardNumber("34343434343434");
		enterCreditCardExpiryDate();
		enterCreditCardVerificationNumber();
		paymentClickNextButton();
	}

	public void enterValidSubscribeProCardDetails() {
		enterCustomCreditCardNumber("4111 1111 1111 1111");
		enterCardExpiryMonthAndyear();
		enterCreditCardVerificationNumber();
	}

	public void enterCreditCardVerificationNumber() {
		if (doesURLContain("co/es")) {
			waitForExpectedElement(COcreditCardVerificationNumberField).sendKeys("123");
		} else if (doesURLContain("it/it/") && isElementPresent(cardNumberIframe)) {
			String getFrameId = waitForExpectedElement(cardCVVIframe).getAttribute("id");

			getWebDriver().switchTo().frame(getFrameId);
			waitForExpectedElement(subscrCvv).sendKeys("054");
			getWebDriver().switchTo().parentFrame();
		} else {
		    waitClearAndEnterText(creditCardVerificationNumberField,"123");
//			waitForExpectedElement(creditCardVerificationNumberField).sendKeys("123");
		}
	}

  public void enterInvalidCreditCardVerificationNumber() {
    if ("it".equals(getLocale())) {
      if (isElementPresent(cardNumberIframe)) {
        String getFrameId = waitForExpectedElement(cardCVVIframe).getAttribute("id");
        getWebDriver().switchTo().frame(getFrameId);
        waitForExpectedElement(subscrCvv).sendKeys("0548909");
        getWebDriver().switchTo().parentFrame();
      }
    } else {
      waitForExpectedElement(creditCardVerificationNumberField).sendKeys("1235678");
    }
  }

	private void enterCreditCardVerificationNumber(String CVCNumber) throws InterruptedException {
		waitForExpectedElement(creditCardVerificationNumberField).sendKeys(CVCNumber);
		Thread.sleep(2000);
	}

	private void enterCreditCardExpiryDate() {
		try {
			if (doesURLContain("/mx/es/")) {
				waitForExpectedElement(MXCardExpirationMonthDropDown);
				selectValueFromDropDownByby("05", MXCardExpirationMonthDropDown);
				Thread.sleep(2000);
				selectValueFromDropDownByby("21", MXCardExpirationYearDropDown);
				Thread.sleep(2000);
			} else {
				waitForExpectedElement(cardExpirationDateMonthDropDown);
				selectValueFromDropDownByby(UrlBuilder.getMessage("ExpiryDateMonth.key"), cardExpirationDateMonthDropDown);
				selectValueFromDropDownByby("2022", cardExpirationDateYearDropDown);
			}
		} catch (Exception ex) {
          LOG.info("Failed to select card's expiry date due to error: " + ex.getMessage());
		}
	}

	private void enterCardExpiryMonthAndyear() {
		if(doesURLContain("co/es")){
            waitForElementToAppearAndDisappear(LOADER, 2, 10);
            if (UrlBuilder.isDesktop() && UrlBuilder.isSafari()) {
                enterDataUsingJS(waitForExpectedElement(COcardExpirationDateMonthDropDown), "6");
                enterDataUsingJS(waitForExpectedElement(COcardExpirationDateYearDropDown), "2026");
            } else {
                selectValueFromDropDownByIndex(6, COcardExpirationDateMonthDropDown);
                selectValueFromDropDownByIndex(6, COcardExpirationDateYearDropDown);
            }
		} else if (doesURLContain("it/it/") && isElementPresent(cardNumberIframe)) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR) + 2;
			int month = c.get(Calendar.MONTH) +1;
			waitForExpectedElement(subscrCardMonth).sendKeys(Integer.toString(month));
			waitForExpectedElement(subscrCardYear).sendKeys(Integer.toString(year));
		} else {
			waitForExpectedElement(cardExpirationDateMonthDropDown,15);
			selectValueFromDropDownByIndex(6, cardExpirationDateMonthDropDown);
			selectValueFromDropDownByIndex(3, cardExpirationDateYearDropDown);
		}
	}

	public void selectSubscribedProSavedCard() {
		clickByElementByQueryJSExecutor(subscrSavedCardsLabel);
	}

  public void clickOnSavedCardOption() {
    waitForExpectedElement(SAVED_CARD).click();
  }

	private void enterCustomCreditCardNumber(String creditCardNumber) {
		if (doesURLContain("co/es")) {
			waitForExpectedElement(COcreditCardNumberField).sendKeys(creditCardNumber);
		} else if (doesURLContain("it/it/") && isElementPresent(cardNumberIframe)) {
			String getFrameId = waitForExpectedElement(cardNumberIframe, 60).getAttribute("id");

			getWebDriver().switchTo().frame(getFrameId);
			waitForExpectedElement(subscrCardNumber).sendKeys(creditCardNumber);
			getWebDriver().switchTo().parentFrame();
		} else {
			waitForExpectedElement(creditCardNumberField,10).sendKeys(creditCardNumber);
		}
	}

	private void enterCardHolderName(String creditCardHolderName) {
		if (UrlBuilder.getLocale().equals("vuseco")) {
			waitForExpectedElement(COcreditCardHolderNameField,20).sendKeys(creditCardHolderName);
		} else {
			waitForExpectedElement(creditCardHolderNameField).sendKeys(creditCardHolderName);
		}
	}

	public void AMEXCardOrderOptionSelected() {
		selectCreditCardOptions();
		waitForExpectedElement(amexCardOption, 10).click();
	}

	public void subscribeProOrderOptionSelected() {
		selectCreditCardOptions();
		waitForExpectedElement(cardNumberIframe, 10);
	}

	public void enterValidAmexCardDetailsAndSubmit() throws InterruptedException {
		selectCreditCardOption();
		enterCardHolderName(random(8, ALPHABETS));
		enterCustomCreditCardNumber("34343434343434");
		enterCreditCardExpiryDate();
		enterCreditCardVerificationNumber("1234");
	}

	public void promotionMessage(String expectedPromotionMsg) throws InterruptedException {
      if ("vusefr".equals(getLocale())) {
        LOG.info(waitForExpectedElement(PROMOMESSAGE_FR, 10).getText());
        assertTrueWithCustomError(UrlBuilder.getMessage(expectedPromotionMsg), waitForExpectedElement(PROMOMESSAGE_FR).getText());
      } else {
        LOG.info(waitForExpectedElement(promoMessage, 10).getText());
        if(UrlBuilder.getLocale().equalsIgnoreCase("vuseuk"))
            assertTrue(waitForExpectedElement(promoMessage).getText().contains(expectedPromotionMsg));
        else
            assertTrueWithCustomError(expectedPromotionMsg, waitForExpectedElement(promoMessage).getText());
      }
	}

	public void enterCommonInvoiceDetails(){
      waitForExpectedElement(electronicInvoiceCheckbox).click();
      enterDataAndWait(ENTER_RFC, "HEGJ820506M10");
      enterDataAndWait(enterBussinessName, "Software");
      enterDataAndWait(enterRoadName, "Test Road");
      enterDataAndWait(enterOutdoorNumber, "32145");
      enterDataAndWait(enterSuburb, "Suburb");
      enterDataAndWait(enterCity, "Tehuacan");
      enterDataAndWait(enterState, "Puebla");
      enterDataAndWait(enterPostalCode, "75741");
    }

    public void enterAddditionalMxInvoiceDetails(){
      enterDataAndWait(COLONIA_MX, "mexico");
      enterDataAndWait(ESTODO_MX, "estodo");
    }

	public void enterElectronicInvoiceDetails() {
		if(getWebDriver().findElements(electronicInvoiceCheckbox).size()>0) {
		  switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
              enterCommonInvoiceDetails();
              enterAddditionalMxInvoiceDetails();
              break;
            default:
              enterCommonInvoiceDetails();
          }
		}
	}

	public void clickTermsAndConditions() {
		if(getWebDriver().getCurrentUrl().contains("/mx/es/")) {
			try	{
				clickUsingJS(waitForExpectedElement(chkTermsAndConditions,10));
				Thread.sleep(2000);
			}catch(Exception e)  {
                LOG.info("Failed to click on 'Terms and Conditions' check-box.");
			}
		}
	}

	public void enterValidVISACardDetailsForMX(String strCardName,String strCardNumber,String strSecurityCode) {
		waitForExpectedElement(iframeCheckoutCard,80);
		getWebDriver().switchTo().frame(getWebDriver().findElement(iframeCheckoutCard));
		enterDataAndWait(MXcreditCardHolderName,strCardName);
		enterDataAndWait(MXcreditCardNumber,strCardNumber);
		enterCreditCardExpiryDate();
		enterDataAndWait(MXcreditCardVerificationNumber,strSecurityCode);
		if(UrlBuilder.isIPhone()) {
          try {
            waitForExpectedElement(BTN_SUBMIT_IPHONE, 10).submit();
          } catch (Exception e) {
            clickByElement(BTN_SUBMIT_IPHONE);
          }
        }
	}

	public void loginPayPal(String strUserName,String strPassword) {
		try
		{
			enterDataAndWait(txtPaypalEmail,strUserName);
			enterDataAndWait(txtPaypalPassword,strPassword);

			waitForExpectedElement(btnPaypalLogIn).click();
			waitForExpectedElement(btnContinue,80);
		}
		catch(Exception e)
		{
            LOG.info("Failed to login into Paypal.");
		}
	}

	public void verifyOrderSummaryDetailsAfterDiscount() {
		assertTrue(waitForExpectedElement(By.cssSelector("tr.totals.discount:nth-child(2) th.mark > span.title")).getText().contains("Discount (TOMREP)"));
		assertTrue(waitForExpectedElement(By.cssSelector("span.rule-name")).getText().contains("100% Refund"));
		assertTrue(waitForExpectedElement(By.cssSelector("tr.grand.totals.incl:nth-child(5)")).getText().contains("Order Total Incl. Tax") && waitForExpectedElement(By.cssSelector("tr.grand.totals.incl:nth-child(5)")).getText().contains("£0.00"));
	}

  public void verifyFirstOrderDiscount() {
    waitForAjaxElementNotToBePresent(webDriver,3);
    String firstOrderDiscountlbl = waitForExpectedElement(FIRST_ORDER_DISCOUNT_LABEL_VELOPL).getText();
    LOG.info("First Oder Discount Displayed as : " + firstOrderDiscountlbl );
  assertThat(firstOrderDiscountlbl.equalsIgnoreCase(UrlBuilder.getMessage("firstOrderDiscountlbl.key")));

  }
  public void verifyDiscountForFiveItems(){
    waitForAjaxElementNotToBePresent(webDriver,3);
    assertThat(waitForExpectedElement(DISCOUNT_LABEL_VELOPL).getText().equalsIgnoreCase(UrlBuilder.getMessage("discountMoreItems.key")));
    assertThat(waitForExpectedElement(VAT_TAX_LABEL_VELOPL).getText().equalsIgnoreCase(UrlBuilder.getMessage("vatTaxLabel.key")));
  }



  public void increaseBasketQty(int qty) {
    for(int i=2; i<=qty; i++) {
      clickByElementByQueryJSExecutor(INCREASE_BASKET_QTY_VELOPL);
      waitForAjaxElementNotToBePresent(webDriver,3);
    }
  }
	public void assertSameProductDetailsForLABandNonLabProductsOnCheckout() {
		waitForExpectedElement(CHECKOUT_PRODUCTS_LIST,10);
		productItems = waitForExpectedElement(CHECKOUT_PRODUCTS_LIST).findElements(By.tagName("li"));
        AssertJUnit.assertEquals(2, getWebDriver().findElements(CHECKOUT_PRODUCTS_NAMES).size());
        AssertJUnit.assertEquals(2, getWebDriver().findElements(CHECKOUT_PRODUCTS_PRICES).size());
        AssertJUnit.assertEquals(2, getWebDriver().findElements(CHECKOUT_PRODUCTS_QTYS).size());
	}

	public void assertStrengthAttributeForOnlyLABProductsOnCheckout(String strExpectedStrength) {
		assertTrue(waitForExpectedElement(CHECKOUT_SEEDETAILS_LINK,10).isDisplayed());
		int attempts = 0;
		boolean result = false;
		while(attempts < 2) {
			try {
				waitForItemToBeClickableAndClick(CHECKOUT_SEEDETAILS_LINK,10);
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
				attempts++;
			}
		}
		waitForExpectedElement(CHECKOUT_PRODUCTS_DETAILS,10);
		bundleProductStrength = waitForExpectedElement(CHECKOUT_PRODUCTS_DETAILS).findElements(By.tagName("dt"));
		for(WebElement eleStrength: bundleProductStrength){
			assertTrue(eleStrength.getText().contains(UrlBuilder.getMessage(strExpectedStrength)));
		}
	}

	public void assertSKUsQuantityAttributeForOnlyBundleLABProductsOnCheckout() {
		int intCounter=1;
		waitForExpectedElement(CHECKOUT_PRODUCTS_DETAILS,10);
		productItems = waitForExpectedElement(CHECKOUT_PRODUCTS_DETAILS).findElements(By.tagName("dd"));
        assertTrue(productItems.get(0).getText().contains("1 x EDT.01_0" + intCounter));
        assertTrue(productItems.get(0).getText().contains("1 x EDT.01_0" + intCounter++));
        assertTrue(productItems.get(0).getText().contains("1 x EDT.01_0" + intCounter++));
	}

	public void userSelectsItemsInCartSectionUnderOrderOverview() {
		waitForExpectedElement(creditCardOption,40);
		if(waitForExpectedElement(CARTITEMS_CHECKOUT_LINK).isDisplayed())
            clickByElementByQueryJSExecutor(CARTITEMS_CHECKOUT_LINK);
        if(!isElementPresent(CHECKOUT_PRODUCTS_LIST,5)){
          clickByElementByQueryJSExecutor(CARTITEMS_CHECKOUT_LINK);
        }
	}

	public void selectDebitCardOptionCO(){
		try {
		    waitForAjaxElementNotToBePresent(getWebDriver(),10);
			waitForExpectedElement(COdebitCardOption).click();
		} catch (Exception e) {
			if (UrlBuilder.isDesktop()) {
				waitForExpectedElement(COdebitCardOption, 10);
				clickByElementByQueryJSExecutor(COdebitCardOption);
			} else {
				waitForExpectedElement(MOBILE_CO_DEBIT_CARD_OPTION, 10);
				clickByElementByQueryJSExecutor(MOBILE_CO_DEBIT_CARD_OPTION);
			}
		}
	}

	public void enterOpenPayDetails(){
      if ("vuseco".equals(getLocale())) {
        clickByElementByQueryJSExecutor(OPENPAY_OPTION_VUSECO);
        waitForAjaxElementNotToBePresent(getWebDriver(), 8);
        clickByElementByQueryJSExecutor(OPENPAY_TC_VUSECO);
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        clickByElementByQueryJSExecutor(OPENPAY_PLACEORDER_VUSECO);
      } else {
        if (UrlBuilder.isSamsungMobile()) {
          waitForExpectedElement(identificationID, 60);
        }
        waitForExpectedElement(identificationID, 20);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        selectValueFromDropDownByIndex(2, bankName);
        selectValueFromDropDownByIndex(2, personType);
        selectValueFromDropDownByIndex(3, docType);
        waitForExpectedElement(identificationID).sendKeys("12345");
      }
	}

	public void placeOpenPayOrder(){
      if (UrlBuilder.getLocale().equals("vuseco")){
        waitForPage();
        waitForExpectedElement(bankName,60);
        selectValueFromDropDownByIndex(1,bankName);
        selectValueFromDropDownByIndex(1,personType);
        selectValueFromDropDownByIndex(1,docType);
        waitForExpectedElement(identificationID).sendKeys("123");
      }
		try {
			waitForExpectedElement(paymentBtn,30).click();
		}catch(Exception e){
			clickByElementByQueryJSExecutor(paymentBtn);
		}
		waitForExpectedElement(continueBtn,30);
		clickByElementByQueryJSExecutor(continueBtn);
		waitForElementToAppearAndDisappear(OPENPAY_HEADER,12,12);
		clickByElementByQueryJSExecutor(continueBtn2);
	}

  public void clickOnNextButtonAndPlaceOrder() {
    if(UrlBuilder.isIPhone()||UrlBuilder.isIpad()){
      clickUsingJS(MX_IPHONE_CONTINUE_PAYMENT_BUTTON);
      waitForAjaxElementNotToBePresent(getWebDriver(),20);
      waitForPage();
      loginPayPal(UrlBuilder.getMessage("PaypalValidEmail.key"), UrlBuilder.getMessage("PaypalValidPassword.key"));
      waitForExpectedElement(btnContinue,5).click();
    }
    if (doesURLContain("/mx/es/")&& !UrlBuilder.isIPhone()&&!UrlBuilder.isIpad()) {
        waitForExpectedElement(btnSubmit,5);
      if (getWebDriver().findElements(MX_CONTINUE_PAYMENT_BUTTON).size() > 0) {
        try {
          waitForExpectedElement(MX_CONTINUE_PAYMENT_BUTTON).click();
        } catch (Exception e) {
          LOG.info("Next button not appearing on the pop-up.");
        }
      }
      if (UrlBuilder.isSamsungMobile()) {
        if (getWebDriver().findElements(MX_M_CONTINUE_PAYMENT_BUTTON).size() > 0)
          try{
            waitForExpectedElement(MX_M_CONTINUE_PAYMENT_BUTTON).click();
          }
          catch(Exception ex){
            waitForExpectedElement(MX_CONTINUE_PAYMENT_BUTTON).click();
          }
      }
      if (getWebDriver().findElements(btnPayNow).size() > 0) {
        try {
          waitForItemToBeClickableAndClick(btnPayNow, 10);
        } catch (Exception e) {
          LOG.info("Next button not appearing on the pop-up.");
        }
      }
      waitForAjaxElementNotToBePresent(getWebDriver(),30);
      getWebDriver().switchTo().frame(getWebDriver().findElement(By.cssSelector("iframe#redirectTo3ds1Frame")));
      if (getWebDriver().findElements(btnSubmit).size() > 0) {
        try {
          clickByElementByQueryJSExecutor(btnSubmit);
        } catch (Exception e) {
            LOG.info("Submit button not appearing on the page.");}
      }

      if (UrlBuilder.isFirefox()) {
        try {
          Thread.sleep(12000);
          // Could I request to sleep a little while here for vype/mx? :(
          // There are 3 changing UIs during this wait time,
          // take long time but difficult to get the properties manually
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        getWebDriver().switchTo().defaultContent();// resolve "can not access dead object" issue on FF
        waitForExpectedElement(eleSuccessOrderTitle, 60);
        assertTrue(doesURLContain("success"));//added checkpoint for FF
      }
    }
  }

	public void clickPlaceOrder() {
		if(UrlBuilder.isDesktop()) {
            switch (UrlBuilder.getLocale()) {
              case "mx":
              case "vusemx":
                    clickOnNextButtonAndPlaceOrder();
                    break;
                case "uk":
              case "vuseuk":
              case "vypeit":
                case "vuseit":
              case "velode":
              case "lyftse":
              case "pl":
                    try {
                        pressPlaceOrderButton();
                        urlToContainInSeconds("onepage/sucesss",30);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "vuseza":
                     // waitForPage();
                    break;
                case "vusede":
                case "vusefr":
                  waitForPage();
                  try {
                    waitForExpectedElement(CHECKOUT_PATMENT_BUTTON,20).click();
                  } catch (Exception e) {
                    LOG.info("Error is: "+e.toString());
                    waitForExpectedElement(CHECKOUT_PATMENT_BUTTON_ALT).click();
                  }
                  waitForAjaxElementNotToBePresent(getWebDriver(),20);
                    break;
                case "vuseco":
                    clickByElementByQueryJSExecutor(OPENPAY_PLACEORDER_VUSECO);
                  break;
              case "veloza":
                waitForLoaderToDisapear();
                waitForAjaxElementNotToBePresent(webDriver,5);
                clickByElementByQueryJSExecutor(AVALANCHE_PLACE_ORDER_BUTTON_VELOZA);
                break;
              case "velobe":
                waitForLoaderToDisapear();
                clickByElementByQueryJSExecutor(AVALANCHE_PLACE_ORDER_BUTTON);
                break;
                default:
                    waitForPage();
                    try {
                      waitForLoaderToDisapear();
                    } catch (Exception e) {
                      waitForLoaderToDisapear();
                    }
                    try {
                        pressPlaceOrderButton();
                    } catch (Exception e) {
                      waitAndClickByElementByJSExecutor(By.cssSelector(
                              "#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button > span")
                              ,5);
                    }
            }
        } else {
            switch (UrlBuilder.getLocale()) {
              case "mx":
              case "vusemx":
                    clickOnNextButtonAndPlaceOrder();
                    break;
                case "uk":
              case "vuseuk":
                    try {
                        pressPlaceOrderButton();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
              case "vuseza":
                    waitForPage();
                break;
                default:
                    waitForPage();
                    try {
                        pressPlaceOrderButton();
                        waitForAjaxElementNotToBePresent(getWebDriver(), 3);
                    } catch (Exception e) {
                        waitForExpectedElement(By.cssSelector(
                                "#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button > span"))
                                .click();
                    }
            }
        }

    }

	public void clickTermsAndConditionsBox() {
		switch (UrlBuilder.getLocale()) {
		  case "fr":
          case "vusefr":
          case "vypeit":
          case "vuseit":
				try {
					clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("TermsAndCondtionRef.key")));
				} catch (Exception e) {
					waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("TermsAndCondtionRefPaypalFR.key"))).click();
				}
				break;
			case "nl":
				try {
					clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("TermsAndCondtionRef.key")));
				} catch (Exception e) {
					presenceOfAllElementsLocatedBy(By.xpath(UrlBuilder.getMessage("TermsAndCondtionRefPaypal.key"))).get(0).click();
				}
				break;
				case "uk":
          case "vuseuk":
				try {
					clickByElementByQueryJSExecutor(AGREEMENT_WORLDPAY);
				} catch (Exception e) {
					clickByElementByQueryJSExecutor(AGREEMENT_WORLDPAY_APM);
				}
				break;
			case "lyftse":
				try {
				    waitForExpectedElement(TERMS_AND_CONDITIONS_LYFTSE).isDisplayed();
                    clickUsingJS(TERMS_AND_CONDITIONS_LYFTSE);
				} catch (Exception e) {
                    clickUsingJS(TERMS_AND_CONDITIONS_LYFTSEPAYPAL);
				}
			break;
          case "mx":
          case "vusemx":
              if(UrlBuilder.isIPhone()||UrlBuilder.isIpad()) {
                  try {
                      waitForExpectedElement(TERMS_AND_CONDITIONS_MX_IPHONE).isDisplayed();
                      clickUsingJS(TERMS_AND_CONDITIONS_MX_IPHONE);
                  } catch (Exception e) {
                      clickUsingJS(TERMS_AND_CONDITIONS_LYFTSEPAYPAL);
                  }
              }
            break;
			case "lyftdk":
				clickByElementByQueryJSExecutor(TermsAndCondtionKeyLyftDK);
				clickByElementByQueryJSExecutor(AGE_CONFIRMATION_TICKBOX_LYFT_DK);
				break;
			case "dk":
          case "vusedk":
				waitForExpectedElement(TERMS_CONDITIONS_CHECKBOX,10);
				clickUsingJS(TERMS_CONDITIONS_CHECKBOX);
				waitForExpectedElement(AGE_VERIFICATION_CHECKBOX,10);
				clickUsingJS(AGE_VERIFICATION_CHECKBOX);
				break;
          case "glojp":
            clickByElementByQueryJSExecutor(TermsAndCondtionCheckBoxJP);
            break;
            case "vuseco":
                waitForExpectedElement(OPENPAY_TC_VUSECO,10);
                clickByElementByQueryJSExecutor(OPENPAY_TC_VUSECO);
              break;
			default:
		}
	}

	public void clickTermsAndConditionsBoxForGooglePay(){
      clickByElementByQueryJSExecutor(GOOGLE_PAY_TERMS_AND_CONDITIONS);
    }

	public void clickOnCancelButtonOnAddNewAddressPopUp() {
		waitForExpectedElement(NEWADDRESS_CANCEL_BUTTON_MX, 10);
		clickByElementByQueryJSExecutor(NEWADDRESS_CANCEL_BUTTON_MX);
		waitForExpectedElement(MXCreditCardOption, 10);
	}

	public void assertCheckoutAgeVerificationWarning() {
      switch (UrlBuilder.getLocale()) {
        case "mx":
        case "vusemx":
          waitForExpectedElement(AGE_VERIFICATION_WARNING_MX);
          LOG.info("age verification :" + getTextFor(AGE_VERIFICATION_WARNING_MX));
          assertTrue(waitForExpectedElement(AGE_VERIFICATION_WARNING_MX).getText().contains(UrlBuilder.getMessage("ageVerificationWarning.key")));
          break;
        case "vuseza":
          waitForExpectedElement(AGE_VERIFICATION_POP_UP);
          assertTrue(waitForExpectedElement(AGE_VERIFICATION_POP_UP).getText().contains(UrlBuilder.getMessage("AgeVerficationConsentHeading.key")));
          waitForExpectedElement(UPDATE_ID_NUMBER_BUTTON).click();
          break;
        case "vuseit":
          assertTrue(waitForExpectedElement(AGE_VERIFICATION_WARNING_IT).getText().contains(UrlBuilder.getMessage("ageVerificationWarning.key")));
          break;
        default:
          waitForExpectedElement(AGE_VERIFICATION_WARNING);
          assertTrue(waitForExpectedElement(AGE_VERIFICATION_WARNING).getText().contains(UrlBuilder.getMessage("ageVerificationWarning.key")));
      }
    }

	public void assertTermsAndConditionsLinkInAgeVerificationWarning() {
      switch (UrlBuilder.getLocale()) {
        case "mx":
        case "vusemx":
          break;
        default:
          waitForExpectedElement(By.linkText(UrlBuilder.getMessage("termsAndConditionsLink.key")), 10);
          clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("termsAndConditionsLink.key")));
      }
    }

	public void assertTelephoneNumberFieldIsMandatory() {
		switch (UrlBuilder.getLocale()) {
			case "fr":
          case "vusefr":
				scrollElementIntoView(TELEPHONE_NUMBER_FIELD_CHECKOUT);
				assertTrue(waitForExpectedElement(TELEPHONE_NUMBER_FIELD_CHECKOUT).isDisplayed());
				break;
			default:
		}
	}

	public void clickOnShowOnMapButtonUnderGLSShippingMethod() {
      if (valueOf(getLocale().toUpperCase()) == LYFTSE) {
        waitForExpectedElement(PARCELSHOP_LOCATOR_POPUP_SHOW_MAP_BUTTON_LYFTSE).click();
      } else {
        clickUsingJS(PARCELSHOP_LOCATOR_POPUP_SHOW_MAP_BUTTON);
      }
	}

	public void assertGLSPopUpMapIsDisplayed() {
      if (valueOf(getLocale().toUpperCase()) == LYFTSE) {
        waitForExpectedElement(DPS_LOCATOR_POPUP, 10);
        assertTrue(getWebDriver().findElements(DPS_LOCATOR_POPUP).size() > 0);
        assertTrue(getWebDriver().findElements(DPS_MAP_INSIDE_POPUP).size() > 0
                && waitForExpectedElement(DPS_MAP_INSIDE_POPUP)
                .getAttribute("style")
                .contains("position: relative"));
      } else {
        waitForExpectedElement(GLS_LOCATOR_POPUP, 10);
        assertTrue(getWebDriver().findElements(GLS_LOCATOR_POPUP).size() > 0);
        assertTrue(getWebDriver().findElements(GLS_MAP_INSIDE_POPUP).size() > 0 && waitForExpectedElement(GLS_MAP_INSIDE_POPUP).getAttribute("style").contains("position: relative"));
      }
	}

	public void clickOnContinueButtonOnGLSMapPopUp() {
      switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
        case LYFTSE:
          waitForExpectedElement(DPS_MAP_POPUP_CONTINUE_BUTTON,10).click();
          break;
        case VUSEDK:
          clickUsingJS(GLS_MAP_POPUP_CONTINUE_BUTTON);
          break;
        default:
          waitForExpectedElement(GLS_MAP_POPUP_CONTINUE_BUTTON,10).click();
      }
	}

	public void clickOnCloseButtonOnGLSMapPopUp() {
		waitForExpectedElement(GLS_MAP_POPUP_CLOSE_BUTTON,10).click();
	}

	public void assertGLSPopUpMapHasClosed() {
      AssertJUnit.assertEquals(0, getWebDriver().findElements(GLS_MAP_POPUP_CLOSED).size());
	}

	public void paymentPageDetailsConfirmed() {
		waitForAjaxElementNotToBePresent(getWebDriver(), 30);
		AssertJUnit.assertTrue(isOrderSummaryPresent());
		waitForAjaxElementNotToBePresent(getWebDriver(), 20);
		if(UrlBuilder.getLocale().equals("it")||UrlBuilder.getLocale().equals("lyftse")){
          assertTrue(basketPage.getWebDriver().findElements(CURRENT_DELIVERY_ADDRESS_GLOIT).size() > 0);
        }
		else{
          String currentAddress = waitForExpectedElement(CURRENT_DELIVERY_ADDRESS).getText();
          scenarioContext.setContext(Context.CURRENT_ADDRESS, currentAddress);
        }
	}

	public void choosePaymentMethod () {
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
      if ("pl".equals(getLocale())) {
        waitForExpectedElement(PAYMENT_METHOD_TITLE_CHOICE, 10);
      } else {
        waitForExpectedElement(CHECKBOX_DPD_SHIPPING_OPTION_GLO, 10).isEnabled();
        if (!waitForExpectedElement(CHECKBOX_DPD_SHIPPING_OPTION_GLO).isSelected()) {
          clickByElementByQueryJSExecutor(CHECKBOX_DPD_SHIPPING_OPTION_GLO);
        }
      }
	}

	public void enterDpsParcelshopNotificationIfPresent(){
		if(isElementDisplayedBySeconds(PARCEL_SHOP_NOTIFICATION,7)){
			waitForExpectedElement(PARCEL_SHOP_NOTIFICATION).sendKeys("12345678");
		}
	}

	public boolean newAddressModalHasTitle() {
      if (UrlBuilder.getLocale().equals("velobe")) {
        String expectedNewAddressModalHeading = UrlBuilder.getMessage("newAddressModalHeading-" + scenarioContext.getContext(Context.LANGUAGE).toString());
        String actualNewAddressModalHeading = waitForExpectedElement(NEW_ADDRESS_MODAL_TITLE, 10).getText();
        assertThat(expectedNewAddressModalHeading.toLowerCase().equals(actualNewAddressModalHeading.toLowerCase()))
                .as("ERROR newAddressModalHasTitle: expected new address modal title was " + expectedNewAddressModalHeading + " but I got " + actualNewAddressModalHeading).isTrue();
        return true;
      } else {
        return !waitForExpectedElement(NEW_ADDRESS_MODAL_TITLE, 10).getText().isEmpty();
      }
    }

	public void waitForLoaderToDisapear(){
		waitForElementToDisappear(loader,40);
	}

	public void clickChangeAddress(){
      clickByElementByQueryJSExecutor(UK_CHANGE_ADDRESS);
    }

	public void clickNewAddressButton() {
		switch (UrlBuilder.getLocale()) {
			case "uk":
            case "vuseuk":
            case "fr":
            case "vusede":
            case "kz":
              clickByElementByQueryJSExecutor(UK_CHANGE_ADDRESS);
              clickByElementByQueryJSExecutor(UK_ADD_NEW_ADDRESS);
              waitForExpectedElement(CITY_FIELD_CHECKOUT,10);
              break;
          case "velobe":
          case "velopl":
            waitForExpectedElement(AVALANCHE_CHANGE_ADDRESS_BUTTON).click();;
            waitForExpectedElement(AVALANCHE_ADD_NEW_ADDRESS_BUTTON, 5).click();
            break;
            case "glode":
				clickByElementByQueryJSExecutor(UK_CHANGE_ADDRESS);
				clickByElementByQueryJSExecutor(UK_ADD_NEW_ADDRESS);
				clickByElementByQueryJSExecutor(UK_ADDRESS_MANUALLY);
			break;
          case "mx":
          case "vusemx":
          case "pl":
          case "vuseco":
                waitForExpectedElement(UK_CHANGE_ADDRESS,5);
                clickByElementByQueryJSExecutor(UK_CHANGE_ADDRESS);
                clickByElementByQueryJSExecutor(UK_ADD_NEW_ADDRESS);
                waitForAjaxElementNotToBePresent(getWebDriver(),3);
              break;
            case "vusefr":
          case "vuseit":
            clickByElementByQueryJSExecutor(UK_CHANGE_ADDRESS);
                clickByElementByQueryJSExecutor(UK_ADD_NEW_ADDRESS);
              break;
          case "velode":
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            waitForExpectedElement(ADD_NEW_ADDRESS_VELO, 10);
            clickByElementByQueryJSExecutor(ADD_NEW_ADDRESS_VELO);
            waitForLoaderToDisapear();
            break;
			default:
				try {
					waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON, 10).click();
				} catch (Exception e) {
					clickByElementByQueryJSExecutor(ADD_NEW_ADDRESS_BUTTON);
				}
		}
	}

	public void enterResaleCode() {
      clickUsingJS(RESALE_CODE_FIELD_TITLE);
			waitForExpectedElement(RESALE_CODE_FIELD).sendKeys(RandomGenerator.randomAlphabetic(5));
	}


    public void assertDropThePodSectionAndDropThePodLinkCTA (String strSection, String strLink) {
        if (!String.valueOf(System.getProperty("MXRecyclingScheme.key")).equals("null") && (!String.valueOf(System.getProperty("MXPodRecycleBagProduct.key")).equals("null"))) {
            if (System.getProperty("MXRecyclingScheme.key").equals("true") && System.getProperty("MXPodRecycleBagProduct.key").equals("true")) {
                assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(strSection).toLowerCase()));
                clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(strLink)));
                assertUserNavigatedToDropThePodLandingPage(); }
        }else {
          AssertJUnit.assertEquals(0, getWebDriver().findElements(INCLUDE_POD_BAG_BUTTON).size());
        }
    }

    public void clickOnIncludeButtonToAddRecyclingBagSKUToTheOrder () throws Throwable {
    waitForExpectedElement(INCLUDE_POD_BAG_BUTTON, 10);
    clickByElementByQueryJSExecutor(INCLUDE_POD_BAG_BUTTON);
    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    assertRecyclingBagAddedToOrderProductItems();
  }

    public void assertRecyclingBagAddedToOrderProductItems() {
        waitForExpectedElement(PRODUCT_DETAILS_WITH_POD_BAG, 10);
        waitForAjaxElementNotToBePresent(getWebDriver(), 20);
        assertTrue(waitForExpectedElement(PRODUCT_DETAILS_WITH_POD_BAG).isDisplayed());
    }

    public void assertUserNavigatedToDropThePodLandingPage() {
        ArrayList<String> windowTabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(windowTabs.get(1));
        assertTrue(getCurrentUrl().contains(UrlBuilder.getMessage("dropThePodURL.key")));
        webDriver.switchTo().window(windowTabs.get(0));
    }

    public void userDeSelectsMyBillingShippingAddressIsSameCheckbox() {
        waitForExpectedElement(SHIPPING_BILLING_ADDRESS_SAME_CHECKBOX, 10);
        if (waitForExpectedElement(SHIPPING_BILLING_ADDRESS_SAME_CHECKBOX).isSelected())
            clickByElementByQueryJSExecutor(SHIPPING_BILLING_ADDRESS_SAME_CHECKBOX);
    }

    public void assertViewNoticeSectionRegardingReadOnlyFieldsNotDisplayed() {
      AssertJUnit.assertEquals(0, getWebDriver().findElements(accountDashboardPage.VIEW_NOTICE_CMS_BLOCK).size());
    }

    public void assertFirstNameFieldIsNotEditableUnderCheckoutBillingAddress() {
        assertTrue(waitForExpectedElement(CHECKOUT_PAYMENT_FIRST_NAME_READ_ONLY, 10).isDisplayed());
    }

    public void assertLastNameFieldIsNotEditableUnderCheckoutBillingAddress() {
        assertTrue(waitForExpectedElement(CHECKOUT_PAYMENT_LAST_NAME_READ_ONLY, 10).isDisplayed());
    }

    public void assertPrePopulatedFirstNameWithExistingAccountUnderBillingAddress() {
        assertTrue(waitForExpectedElement(CHECKOUT_PAYMENT_FIRST_NAME_READ_ONLY).getAttribute("value").equalsIgnoreCase(System.getProperty("UserFirstName.key")));
    }

    public void assertPrePopulatedLastNameWithExistingAccountUnderBillingAddress() {
        assertTrue(waitForExpectedElement(CHECKOUT_PAYMENT_LAST_NAME_READ_ONLY).getAttribute("value").equalsIgnoreCase(System.getProperty("UserLastName.key")));
    }

    public void assertAgeVerificationCheckBoxUnderCardDetails() {
        assertTrue(getWebDriver().findElements(AGE_VERIFICATION_CHECKBOX).size()>0);
    }

    public void assertTermsConditionCheckBoxUnderCardDetails() {
        assertTrue(getWebDriver().findElements(TERMS_CONDITIONS_CHECKBOX).size()>0);
    }

    public void assertEcoTaxIsAppliedForTheProduct(){
        WebElement eleEcoTaxField = waitForExpectedElement(ECO_PARTICIPATION_TAX, 10);
        if (getWebDriver().getCurrentUrl().contains("fr/fr")){
          if (eleEcoTaxField == null){
            // Put below in place as on the checkout page the table reference eleEcoTaxField has x2 matches and doesn't work the same as the cart page discount
            eleEcoTaxField = waitForExpectedElement(ECO_PARTICIPATION_TAX_CHECKOUT_PAGE);
          }
          assertTrue(eleEcoTaxField.findElement(By.tagName("th")).getText().contains("ECO PARTICIPATION INCLUSE"));
          double ecoTaxCharges = Double.parseDouble(eleEcoTaxField.findElement(By.tagName("td")).findElement(ECO_TAX_CHARGES).getText().split(" ")[0].replace(",", "."));
          assertTrue(ecoTaxCharges > 0.00);
        } else {
          assertTrue(eleEcoTaxField.findElement(By.tagName("th")).getText().contains("Eco participation incluse"));
          double ecoTaxCharges = Double.parseDouble(eleEcoTaxField.findElement(By.tagName("td")).findElement(ECO_TAX_CHARGES).getText().split(" ")[0].replace(",", "."));
          assertTrue(ecoTaxCharges > 0.00);
        }

    }

    public void fetchTotalChargesForTheProduct() {
        if(!isElementPresent(PRODUCT_TOTAL_CHARGES)) {
          waitForPageLoad();
          waitForExpectedElement(PRODUCT_TOTAL_CHARGES, 30);
        }
        waitForExpectedElement(PRODUCT_TOTAL_CHARGES, 20);
        for (WebElement rowData : getTableRows(PRODUCT_TOTAL_CHARGES)) {
            if (rowData.getText().contains("Sous-total")) {
                SUB_TOTAL_CHARGES_MINIBASKET_CHECKOUT = Float.valueOf(rowData.findElements(SUB_TOTAL_CHARGES_MINIBASKET).get(0).getText().split(" ")[0].replace(",", "."));
            }
            if (rowData.getText().contains("Ordersumma Inkl. Moms")) {
                TOTAL_CHARGES_MINIBASKET_CHECKOUT = Float.valueOf(rowData.findElements(TOTAL_CHARGES_MINIBASKET).get(0).getText().split(" ")[0].replace(",", "."));
            }
            if (rowData.getText().contains("Total")) {
                TOTAL_CHARGES_MINIBASKET_CHECKOUT = Float.valueOf(rowData.findElements(TOTAL_CHARGES_MINIBASKET).get(0).getText().split(" ")[0].replace(",", "."));
            }
            if (rowData.getText().contains("Livraison express")) {
                DELIVERY_CHARGES_MINIBASKET_CHECKOUT = Float.valueOf(rowData.findElements(DELIVERY_CHARGES_MINIBASKET).get(0).getText().split(" ")[0].replace(",", "."));
            }
            if (rowData.getText().contains("Eco participation incluse")) {
                ECO_TAX_CHARGES_MINIBASKET_CHECKOUT = Float.valueOf(rowData.findElements(ECO_TAX_CHARGES_MINIBASKET).get(0).getText().split(" ")[0].replace(",", "."));
            }
        }
    }

  public void assertNoSubscriptionProDiscountInTotal(){
    waitForExpectedElement(PRODUCT_TOTAL_CHARGES, 20);
    for (WebElement rowData : getTableRows(PRODUCT_TOTAL_CHARGES)) {
      assertNotEquals(UrlBuilder.getMessage("BronzeDiscountTitle.key"), rowData.getText());
    }
  }

  public void assertSubscriptionProDiscountInTotal(String discountExpected){
      By subTotalCharges;
    if ("vuseco".equals(getLocale())) {
      subTotalCharges = PRODUCT_TOTAL_CHARGES_SUBS_VUSECO;
    } else {
      subTotalCharges = PRODUCT_TOTAL_CHARGES_SUBS;
    }
    waitForExpectedElement(subTotalCharges, 20);
    ArrayList<String> totalTitlesArrayList = new ArrayList<>();
    for (WebElement rowData : getTableRows(subTotalCharges)) {
      totalTitlesArrayList.add(rowData.getText());
    }
    boolean discountTitle;
    switch(discountExpected){
      case "bronze":
        discountTitle =
                totalTitlesArrayList.stream()
                        .anyMatch(a -> a.startsWith(UrlBuilder.getMessage("BronzeDiscountTitle.key")));
        assertTrue(discountTitle);
        break;
      case "silver":
        discountTitle =
                totalTitlesArrayList.stream()
                        .anyMatch(a -> a.startsWith(UrlBuilder.getMessage("SilverDiscountTitle.key")));
        assertTrue(discountTitle);
        break;
      case "gold":
        discountTitle =
                totalTitlesArrayList.stream()
                        .anyMatch(a -> a.startsWith(UrlBuilder.getMessage("GoldDiscountTitle.key")));
        assertTrue(discountTitle);
        break;
      case "platinum":
        discountTitle =
                totalTitlesArrayList.stream()
                        .anyMatch(a -> a.startsWith(UrlBuilder.getMessage("PlatinumDiscountTitle.key")));
        assertTrue(discountTitle);
        break;
      case "accessory":
        discountTitle =
                totalTitlesArrayList.stream()
                        .anyMatch(a -> a.startsWith(UrlBuilder.getMessage("AccessoryDiscountTitle.key")));
        assertTrue(discountTitle);
        break;
    }
  }

  public void clickCheckoutCTAOrderSummary(){
      try {
        homePage.clickByElementByQueryJSExecutor(SUBS_CHECKOUT_CTA_ORDERSUMMARYFR);
      } catch (Exception e) {
        scrollElementIntoView(SUBS_CHECKOUT_CTA_ORDERSUMMARYFR);
        waitForExpectedElement(SUBS_CHECKOUT_CTA_ORDERSUMMARYFR, 10).click();
      }
  }

    public void assertTotalChargesForTheProductAfterDiscount() {
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "vuseit":
                assertTrue(webDriver.findElement(TOTAL_CHARGES_MINIBASKET).getText().replace(",", ".").contains(Float.toString(FINAL_CHARGES_CART)));
                break;
            case "kz":
            case "it":
                waitForExpectedElement(TOTAL_CHARGES_MINIBASKET, 10);
                waitForExpectedElement(MINI_CART_BUTTON, 10).click();
                assertTrue(webDriver.findElement(totalChargesMiniCart).getText().contains(UrlBuilder.getMessage(Float.toString(FINAL_CHARGES_CART))));
                break;
            case "de":
                waitForExpectedElement(TOTAL_CHARGES_MINIBASKET_DE, 10);
                waitForExpectedElement(MINI_CART_BUTTON_FOR_DE, 10).click();
                waitForExpectedElement(TOTAL_CHARGES_MINICART_DE, 10);
                assertTrue(webDriver.findElement(TOTAL_CHARGES_MINICART_DE).getText().split(" ")[0].replace(",", ".").contains(Float.toString(FINAL_CHARGES_CART)));
                break;
            case "fr":
          case "vusefr":
                waitForExpectedElement(TOTAL_CHARGES_MINIBASKET, 10);
                String text = webDriver.findElement(TOTAL_CHARGES_MINIBASKET).getText().split(" ")[0].replace(",", ".");
                float actualTotalCharges = Float.parseFloat(text);
                if(!getCurrentUrl().contains("cart")){
                actualTotalCharges = Float.parseFloat(text) - DELIVERY_CHARGES_MINIBASKET_CHECKOUT;
                }
                Float expectedTotalCharges = Float.valueOf(Float.toString(FINAL_CHARGES_CART).split(" ")[0].replace(",", "."));
                assertThat(actualTotalCharges).isEqualTo(expectedTotalCharges);
                break;
            default:
                WebElement elements = waitForExpectedElement(TOTAL_CHARGES_MINIBASKET, 10);
                assertTrue(webDriver.findElement(TOTAL_CHARGES_MINIBASKET).getText().contains(Float.toString(FINAL_CHARGES_CART)));
        }
    }

    public void assertTotalChargesForTheProductAfterDiscountInShoppingCart() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "kz":
            case "it":
            case "de":
            case "ie":
            case "lyftse":
            case "glode":
            case "fr":
          case "vusefr":
            case "vuseit":
            case "pl":
                assertFinalPriceAfterDiscount();
                break;
            default:
                WebElement elements = waitForExpectedElement(TOTAL_CHARGES_MINIBASKET, 10);
                assertTrue(webDriver.findElement(TOTAL_CHARGES_MINIBASKET).getText().contains(Float.toString(FINAL_CHARGES_CART)));
        }
    }

    public void assertFinalPriceAfterDiscount() throws InterruptedException {
        waitForExpectedElement(DISCOUNT_COUPON_ERROR,10);
        if(getWebDriver().findElements(DISCOUNT_COUPON_ERROR).size()==0){
            switch (UrlBuilder.getLocale()) {
                case "ie":
                  homePage.clickOnBasketIcon();
                    if (waitForExpectedElement(PRODUCT_ALL_CHARGES_CART, 10).isDisplayed()) {
                        TOTAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(TOTAL_CHARGES_SHOPPING_CART).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                        DISCOUNT_CHARGES_CART = Float.valueOf(waitForExpectedElement(DISCOUNT_CHARGES_SHOPPING_CART).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                        FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_SHOPPING_CART).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                        assertTrue(FINAL_CHARGES_CART==TOTAL_CHARGES_CART+DISCOUNT_CHARGES_CART);
                    }
                    break;
                case "lyftse":
                  homePage.clickOnBasketIcon();
                  if(!isElementPresent(PRODUCT_ALL_CHARGES_CART_GLO))
                  {
                    waitForPageLoad();
                    waitForExpectedElement(PRODUCT_ALL_CHARGES_CART_GLO, 20);
                  }
                  if (waitForExpectedElement(PRODUCT_ALL_CHARGES_CART_GLO, 10).isDisplayed()) {
                    TOTAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(TOTAL_CHARGES_CART_GLO).getText().replaceAll("[\\D]", ""));
                    DISCOUNT_CHARGES_CART = Float.valueOf(waitForExpectedElement(DISCOUNT_CHARGES_CART_LYFTSE).getText().replaceAll("[\\D]", ""));
                    FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_LYFTSE).getText().replaceAll("[\\D]", ""));
                    assertTrue(FINAL_CHARGES_CART==TOTAL_CHARGES_CART-DISCOUNT_CHARGES_CART);
                  }
                  break;
                case "vypeit":
                case "vuseit":
                case "kz":
                case "pl":
                  homePage.clickOnBasketIcon();
                  if (waitForExpectedElement(PRODUCT_ALL_CHARGES_CART_GLO, 10).isDisplayed()) {
                    TOTAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(TOTAL_CHARGES_CART_GLO).getText().replaceAll("[\\D]", ""));
                    DISCOUNT_CHARGES_CART = Float.valueOf(waitForExpectedElement(DISCOUNT_CHARGES_CART_VYPEFR).getText().replaceAll("[\\D]", ""));
                    FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_VYPEFR).getText().replaceAll("[\\D]", ""));
                    assertTrue(FINAL_CHARGES_CART==TOTAL_CHARGES_CART-DISCOUNT_CHARGES_CART);
                  }
                  break;
                case "de":
                  homePage.clickOnBasketIcon();
                    if (waitForExpectedElement(PRODUCT_ALL_CHARGES_CART, 10).isDisplayed()) {
                        TOTAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(TOTAL_CHARGES_SHOPPING_CART).getText().split(" ")[0].replace(",", "."));
                        DISCOUNT_CHARGES_CART = Float.valueOf(waitForExpectedElement(DISCOUNT_CHARGES_SHOPPING_CART).getText().split(" ")[0].replace(",", "."));
                        FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_SHOPPING_CART).getText().split(" ")[0].replace(",", "."));
                      assertTrue(FINAL_CHARGES_CART==TOTAL_CHARGES_CART+DISCOUNT_CHARGES_CART);
                    }
                    break;
              case "fr":
              case "vusefr":
                homePage.clickOnBasketIcon();
                if (waitForExpectedElement(PRODUCT_ALL_CHARGES_CART_GLO, 10).isDisplayed()) {
                  TOTAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(TOTAL_CHARGES_CART_GLO).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                  DISCOUNT_CHARGES_CART = Float.valueOf(waitForExpectedElement(DISCOUNT_CHARGES_CART_VYPEFR).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                  FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_VYPEFR).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                  assertTrue(FINAL_CHARGES_CART==TOTAL_CHARGES_CART+DISCOUNT_CHARGES_CART);
                }
                break;
                case "glode":
                        TOTAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(TOTAL_CHARGES_CART_GLODE).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                        DISCOUNT_CHARGES_CART = Float.valueOf(waitForExpectedElement(DISCOUNT_CHARGES_CART_GLODE).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                        FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_GLODE).getText().split(" ")[0].replace(",", ".").replace("€", ""));
                        assertThat(FINAL_CHARGES_CART==TOTAL_CHARGES_CART+DISCOUNT_CHARGES_CART)
                                .as("ERROR assertFinalPriceAfterDiscount: final charge " + FINAL_CHARGES_CART + " does not equal total charge " + TOTAL_CHARGES_CART + " +  discount of " + DISCOUNT_CHARGES_CART).isTrue();
                    break;
                default:
            }
        }
        else
            LOG.info("Coupon Code not valid/applicable.");
    }

    public void assertTotalChargesForTheProductAfter50PercentDiscount() {
        waitForExpectedElement(PRODUCT_TOTAL_CHARGES, 30);
        if(getWebDriver().findElements(DISCOUNT_COUPON_ERROR).size()==0) {
            SUB_TOTAL_CHARGES_AFTER_DISCOUNT = SUB_TOTAL_CHARGES_MINIBASKET_CHECKOUT * (50.0f / 100.0f);
            if (String.valueOf(DELIVERY_CHARGES_MINIBASKET_CHECKOUT).equals("null"))
                TOTAL_CHARGES_AFTER_DISCOUNT = SUB_TOTAL_CHARGES_AFTER_DISCOUNT;
            else
                TOTAL_CHARGES_AFTER_DISCOUNT = SUB_TOTAL_CHARGES_AFTER_DISCOUNT + DELIVERY_CHARGES_MINIBASKET_CHECKOUT;
            for (WebElement rowData : getTableRows(PRODUCT_TOTAL_CHARGES)) {
                if (rowData.getText().contains("Livraison express")) {
                    SHIPPING_CHARGES_CHECKOUT = Float.valueOf(rowData.findElements(SHIPPING_CHARGES_MINIBASKET).get(0).getText().split(" ")[0].replace(",", "."));
                    TOTAL_CHARGES_AFTER_DISCOUNT = TOTAL_CHARGES_AFTER_DISCOUNT + SHIPPING_CHARGES_CHECKOUT;
                }
                if (rowData.getText().contains("Total")) {
                    assertEquals(TOTAL_CHARGES_AFTER_DISCOUNT, Float.valueOf(rowData.findElement(TOTAL_CHARGES_MINIBASKET).getText().split(" ")[0].replace(",", ".")));
                }
                if (rowData.getText().contains("Remise")) {
                    assertEquals(-SUB_TOTAL_CHARGES_AFTER_DISCOUNT, Float.valueOf(rowData.findElement(CHARGES_AFTER_DISCOUNT).getText().split(" ")[0].replace(",", ".")));
                }
            }
        }
    }

    public void assertDeliveryChargesForTheProductBeforeDiscount() {
        if (!String.valueOf(DELIVERY_CHARGES_MINIBASKET_CHECKOUT).equals("null"))
            assertEquals(DELIVERY_CHARGES_MINIBASKET_CHECKOUT, Float.valueOf(waitForExpectedElement(DELIVERY_CHARGES_MINIBASKET).getText().split(" ")[0].replace(",", ".")));
    }

    public void assertDeliveryChargesForTheProductAfterDiscount() {
        if(getWebDriver().findElements(DISCOUNT_COUPON_ERROR).size()==0) {
            if (!String.valueOf(DELIVERY_CHARGES_MINIBASKET_CHECKOUT).equals("null"))
                assertEquals(DELIVERY_CHARGES_MINIBASKET_CHECKOUT, Float.valueOf(waitForExpectedElement(DELIVERY_CHARGES_MINIBASKET).getText().split(" ")[0].replace(",", ".")));
        }
    }

    public void assertShippingMethodsOnCheckout() {
      assertTrue(waitForExpectedElement(GLS_SHIPPING_METHOD_LABEL).getText().contains("GLS Shipping") );
    }

    public void addPoductIntoCart(String item) {
        clickByElementByQueryJSExecutor(SEARCH_ICON);
        waitForExpectedElement(SEARCH_FIELD).sendKeys(UrlBuilder.getMessage(item));
        waitForExpectedElement(SEARCH_FIELD).sendKeys(Keys.ENTER);
        waitForExpectedElement(ADDTOCART_BUTTON, 10);
        clickByElementByQueryJSExecutor(ADDTOCART_BUTTON);
    }

    public void verifyIfCouponAppliedAndThenAddCoupon(){
        waitForExpectedElement(COUPON_CODE_TEXT_FIELDFR);
        if (getWebDriver().findElements(UK_CANCEL_COUPON_CODE).size() > 0){
            clickByElementByQueryJSExecutor(UK_CANCEL_COUPON_CODE);
         waitForExpectedElement(COUPON_CODE_TEXT_FIELDFR).sendKeys(UrlBuilder.getMessage("couponCodeVypeit.key"));
        } else {
          waitForExpectedElement(COUPON_CODE_TEXT_FIELDFR).sendKeys(UrlBuilder.getMessage("couponCodeVypeit.key"));
        }
  }

    public void applyDiscountCode(String discountCode) {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
               verifyIfCouponAppliedAndThenAddCoupon();
                break;
          case "mx":
          case "vusemx":
                waitForExpectedElement(COUPON_CODE_TEXT_FIELD,10).sendKeys(discountCode);
                break;
            case "de":
                waitForExpectedElement(COUPON_CODE_TEXT_FIELDDE,10).sendKeys(discountCode);
                break;
          case "lyftse":
            if(!isElementPresent(discountCodeInputField)){
              waitForExpectedElement(discountCodeInputField,20);
            }
            waitForExpectedElement(discountCodeInputField).clear();
            waitForExpectedElement(discountCodeInputField).sendKeys(discountCode);
            break;
            case "it":
            case "ie":

            case "kz":
            case "uk":
            case "vuseuk":
            case "vuseza":
            case "vuseit":
              waitForExpectedElement(discountCodeInputField).clear();
              waitForExpectedElement(discountCodeInputField).sendKeys(discountCode);
                break;
            case "glode":
              if(getWebDriver().findElements(CANCEL_DISCOUNT_BUTTON_GLODE).size()>0) {
                clickByElementByQueryJSExecutor(CANCEL_DISCOUNT_BUTTON_GLODE);
                waitForItemToBeClickableAndClick(COUPON_CODE_TEXT_FIELD,20);
                waitForExpectedElement(COUPON_CODE_TEXT_FIELD,10).sendKeys(discountCode);
              }else {
                waitForItemToBeClickableAndClick(COUPON_CODE_TEXT_FIELD, 20);
                waitForExpectedElement(COUPON_CODE_TEXT_FIELD, 10).sendKeys(discountCode);
              }
                break;
            case "fr":
         //   case "pl": Remove duplicate coupon entry
            case "vusefr":
                waitForItemToBeClickableAndClick(COUPON_CODE_TEXT_FIELDFR,20);
                waitForExpectedElement(COUPON_CODE_TEXT_FIELDFR,10).sendKeys(discountCode);
                break;
            default:
                waitForExpectedElement(COUPON_CODE_TEXT_FIELD, 10).sendKeys(discountCode);
        }
        clickApplyDiscountButton();
    }

    public boolean applyDiscountCodeModulePresent() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        cancelCouponCodeButton();
        switch (UrlBuilder.getLocale()) {
            case "fr":
          case "vusefr":
               waitForElementToAppearAndDisappear(LOADER,5,20);
               waitForExpectedElement(COUPON_CODE_TEXT_FIELDFR);
               return waitForExpectedElement(COUPON_CODE_TEXT_FIELDFR,10).isDisplayed();
            case "de":
                break;
            case "vypeit":
            case "vuseit":
                break;
          case "mx":
          case "vusemx":
                return waitForExpectedElement(COUPON_CODE_TEXT_FIELD, 10).isDisplayed();
            case "lyftse":
                return waitForExpectedElement(discountCodeInputField, 10).isDisplayed();
            case "glode":
                break;
            case "it":
                break;
            case "kz":
                break;
            case "vuseza":
                return isElementDisplayedBySeconds(APPLY_DISCOUNT_TEXT_ZA,10);
            default:
                return waitForExpectedElement(applyDiscountText, 10).isDisplayed();
        }
        return waitForExpectedElement(applyDiscountText, 10).isDisplayed();
    }

    public void cancelCouponCodeButton() {
        switch (UrlBuilder.getLocale()) {
            case "fr":
          case "vusefr":
              if (getWebDriver().findElements(CANCEL_COUPON_CODE).size() > 0) {
                clickByElementByQueryJSExecutor(CANCEL_COUPON_CODE_VYPEFR);
              }
              break;
            case "ie":
          case "mx":
          case "vusemx":
          case "glode":
            if (getWebDriver().findElements(CANCEL_COUPON_CODE).size() > 0)
                    waitForExpectedElement(CANCEL_COUPON_CODE).click();
                break;
            case "vypeit":
            case "vuseit":
                if (getWebDriver().findElements(CANCEL_COUPON_CODE_FOR_IT).size() > 0)
                    clickByElementByQueryJSExecutor(CANCEL_COUPON_CODE_FOR_IT);
                break;
            case "de":
                if (getWebDriver().findElements(CANCEL_COUPON_CODE_FOR_DE).size() > 0)
                    waitForExpectedElement(CANCEL_COUPON_CODE_FOR_DE).click();
                break;
            case "lyftse":
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                if (getWebDriver().findElements(CANCEL_COUPON_CODE_FOR_LYFT).size() > 0)
                    waitForExpectedElement(CANCEL_COUPON_CODE_FOR_LYFT).click();
                break;
          case "it":
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                waitForExpectedElement(APPLY_DISCOUNT_DROPDOWN_GLO).click();
                if (getWebDriver().findElements(CANCEL_COUPON_CODE_FOR_GLO_IT).size() > 0)
                    waitForExpectedElement(CANCEL_COUPON_CODE_FOR_GLO_IT).click();
                break;
            case "kz":
                if (getWebDriver().findElements(CANCEL_COUPON_CODE_FOR_GLO_KZ).size() > 0) {
                  waitForExpectedElement(CANCEL_COUPON_CODE_FOR_GLO_KZ, 10);
                  clickByElementByQueryJSExecutor(CANCEL_COUPON_CODE_FOR_GLO_KZ);
                } else {
                  waitForExpectedElement(APPLY_DISCOUNTCODE_FIELD_FOR_VYPEIT, 10);
                }
                break;
          case "uk":
          case "vuseuk":
            if (getWebDriver().findElements(UK_CANCEL_COUPON_CODE).size() > 0)
              clickByElementByQueryJSExecutor(UK_CANCEL_COUPON_CODE);
            default:
        }
    }

    public void clickApplyDiscountButton() {
        switch (UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
                waitForItemToBeClickableAndClick(APPLY_DISCOUNT_BUTTON,10);
                break;
          case "ie":
          case "mx":
          case "vusemx":
          case "de":
          case "lyftse":
          case "vuseza":
                waitForExpectedElement(APPLY_DISCOUNT_BUTTON).click();
                break;
            case "vypeit":
                waitForExpectedElement(APPLY_DISCOUNTCODE_FIELD_FOR_VYPEIT).click();
                break;
            case "glode":
              waitForExpectedElement(APPLY_DISCOUNT_BUTTON_GLO_DE).click();
                break;
            case "it":
                waitForExpectedElement(APPLY_DISCOUNT_BUTTON_GLO_IT).click();
                break;
            case "kz":
                waitForExpectedElement(APPLY_DISCOUNT_BUTTON_GLO_KZ,10);
                waitForExpectedElement(APPLY_DISCOUNT_BUTTON_GLO_KZ).click();
                break;
            case "uk":
            case "vuseuk":
            case "pl":
            case "vuseit":
                waitForExpectedElement(APPLY_DISCOUNT_BUTTON_UK).click();
                break;
            default:
        }
        waitForAjaxElementNotToBePresent(getWebDriver(), 20);
    }

    public void removePromotion() {
      if ("vusefr".equals(getLocale())) {
        waitForExpectedElement(CANCEL_COUPON_CODE_VYPEFR).click();
      } else {
        waitForExpectedElement(REMOVE_PROMOTION_BUTTON).click();
      }
    }

    public void checkResaleCodeFieldTitle() {
        waitForExpectedElement(RESALE_CODE_FIELD_TITLE, 5);
        String resaleCodeFieldTitle = webDriver.findElement(RESALE_CODE_FIELD_TITLE).getText();
        assertThat(resaleCodeFieldTitle.equals(UrlBuilder.getMessage("resaleCodeFieldTitle")))
                .as("ERROR: could not find expected resale code title "+resaleCodeFieldTitle).isTrue();
    }
      public void clickOnPaygatePaymentRadioButton(){
        waitForExpectedElement(LABEL_PAYGATE,15);
     try{
         waitForLoaderToDisapear();
         jsScrollElementInCenter(waitForExpectedElement(LABEL_PAYGATE));
         waitForExpectedElement(LABEL_PAYGATE).click();
     }catch(Exception e){
              waitForLoaderToDisapear();
              waitForExpectedElement(LABEL_PAYGATE).click();
          }
      waitForAjaxElementNotToBePresent(getWebDriver(),5);
      }
      public void sidPayment() throws InterruptedException {
          waitForExpectedElement(placeOrderButton,5);
          if (UrlBuilder.getLocale().equals("vuseza")) {
            clickByElementByQueryJSExecutor(placeOrderButton);
          }else {
            if (! UrlBuilder.getLocale().equals("veloza")) {
              waitForExpectedElement(placeOrderButton, 5).click();
            }
          }
          /*if(urlToContainInSeconds("process.trans", 10))
          {assertTrue(doesURLContain("process.trans"));}*/
        waitForURLToContain("process.trans",10);
        try{
           waitForExpectedElement(SID_SECURE_BUTTON,20);
           clickUsingJS(SID_SECURE_BUTTON);
           waitForPageLoad();
       }catch (Exception e){
           clickByElementByQueryJSExecutor(SID_SECURE_BUTTON);
       }
       if(UrlBuilder.getLocale().equalsIgnoreCase("veloza")&&waitForExpectedElement(PAYMENT_WARNING_ZA).isDisplayed())
          waitForExpectedElement(PAYMENT_WARNING_ZA).click();
       if(isElementDisplayedBySeconds(SID_FIRST_NAME, 10)) {
         waitForExpectedElement(SID_FIRST_NAME, 20).sendKeys("Test Auto");
         waitForExpectedElement(NEXT_BUTTON, 20);
         clickByElementByQueryJSExecutor(NEXT_BUTTON);
         waitForLoaderToDisapear();
         waitForAjaxElementNotToBePresent(getWebDriver(), 45);
         if (UrlBuilder.getLocale().equals("vuseza") || UrlBuilder.getLocale().equals("veloza")) {
           waitForExpectedElement(SID_TEST_COMPLETED_BUTTON, 10);
           clickByElementByQueryJSExecutor(SID_TEST_COMPLETED_BUTTON);
         } else {
           clickByElementByQueryJSExecutor(CONFIRM_SID_BUTTON);
         }
       }
       else {
         if (webDriver.findElements(SID_IFRAME).size() != 0) {
           frameToBeAvailableAndSwitchToIt(SID_IFRAME);
         }
         waitForExpectedElement(SID_FIRST_BANK).click();
       }
       webDriver.switchTo().defaultContent();
       waitForAjaxElementNotToBePresent(getWebDriver(),5);
       waitForLoaderToDisapear();
       if(!UrlBuilder.getLocale().equalsIgnoreCase("vuseza")){
           waitForExpectedElement(CHECKOUT_SUCCESS_ORDER,180);
           if(!isElementDisplayedBySeconds(CHECKOUT_SUCCESS_ORDER,5))
           {
             waitForExpectedElement(CHECKOUT_SUCCESS_ORDER,60);
           }
           String orderNumber = orderSuccessPage.getGeneratedOrderNumber();
          scenarioContext.setContext(ORDER_NUMBER, orderNumber);
        }

     }

     public void paymentCardZA() {
         pressPlaceOrderButton();
         waitForAjaxElementNotToBePresent(getWebDriver(),5);
        if(UrlBuilder.getLocale().equalsIgnoreCase("veloza")&&waitForExpectedElement(PAYMENT_WARNING_ZA).isDisplayed())
            waitForExpectedElement(PAYMENT_WARNING_ZA).click();
        waitForExpectedElement(CARD_BUTTON,10).click();
        waitForExpectedElement(CARD_HOLDER_NAME,10).sendKeys("Test Automation");
        waitForExpectedElement(CARD_NUMBER,10).sendKeys("4000000000000002");
        waitForExpectedElement(CVV_NUMBER,10).sendKeys("123");
        selectValueFromDropDownByIndex(2,MONTH_DD);
        selectValueFromDropDownByIndex(3,YEAR_DD);
        waitForExpectedElement(NEXT_BUTTON,15).click();
       WebDriverWait w = new WebDriverWait(webDriver, 5);
       try{
         if(w.until(ExpectedConditions.alertIsPresent())==null){
           getWebDriver().switchTo().alert().accept();
         }
       }catch(Exception e){
         e.printStackTrace();
       }
        waitForElementToAppearAndDisappear(LOADER_IMAGE, 5,30);
        if (webDriver.findElements(IFRAME_SUBMIT_PAYMENT).size() != 0) {
          frameToBeAvailableAndSwitchToIt(IFRAME_SUBMIT_PAYMENT);
        }
        waitForAjaxElementNotToBePresent(getWebDriver(),3);
        if(!isElementPresent(SUBMIT_BUTTON))
        {
          waitForElementToAppearAndDisappear(LOADER_IMAGE, 5,30);
        }
        try{
          clickUsingJS(SUBMIT_BUTTON);
        }catch(Exception e){
            LOG.info("After click submit it try to load success page. ");
            waitForAjaxElementNotToBePresent(webDriver,40);
        }
     }

	public void eyesCheckCheckoutPage() {
      if (!(Props.EYES_ON && EyesCheckpoints.CHECKOUT_PAGE.isSwitchOn())) {
        return;
      }
      waitForElementToAppearAndDisappear(LOADER, 15, 30);
      scrollToShowEntirePage();
      final String checkpointName = EyesCheckpoints.CHECKOUT_PAGE.getName();
      if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
        switch (UrlBuilder.getLocale()) {
          case "epok":
            eyes.check(checkpointName, Target.window().fully()
                    .layout(SHIPPING_ADDRESS_REGION,
                            BILLING_ADDRESS_REGION)
                    .layout(SHIPPING_INFO_REGION, 10, 10, 30, 30));
            break;
          case "lyftse":
            eyes.check(checkpointName, Target.window().fully()
                    .layout(SHIPPING_ADDRESS_REGION,
                            NOTIFY_PHONE_NUMBER)
                    .layout(SHIPPING_INFO_REGION, 10, 10, 30, 30));
            break;
          case "lyftdk":
          case "dk":
          case "nl":
          case "ie":
          case "de":
          case "vypeit":
          case "vusedk":
          case "it":
            eyes.check(checkpointName, Target.window().fully()
                    .layout(SHIPPING_ADDRESS_REGION)
                    .layout(SHIPPING_INFO_REGION, 10, 10, 30, 30));
            break;
          case "glojp":
            eyes.check(checkpointName, Target.window().fully());
            break;
          default:
            eyes.check(checkpointName, Target.window().fully()
                    .layout(DELIVERY_ADDRESS_REGION, 10, 0, 0, 0));
        }
      } else { // mobile
        switch (UrlBuilder.getLocale()) {
          case "ie":
          case "vusedk":
            eyes.check(checkpointName, Target.window().fully()
                    .ignore(HomePage.SLICK_LIST)
                    .layout(SHIPPING_ADDRESS_REGION)
                    .layout(SHIPPING_INFO_REGION, 10, 10, 30, 30));
            break;
          case "kz":
          case "glode":
          case "vusede":
          case "pl":
          case "vuseuk":
          case "vusefr":
          case "vuseit":
          case "vuseza":
          case "vuseco":
            scrollToShowEntirePage(); // invoke the second time to solve footer truncate issue
            eyes.check(checkpointName, Target.window().fully()
                    .layout(DELIVERY_ADDRESS_REGION, 10, 0, 0, 0));
            break;
          default:
            eyes.check(checkpointName, Target.window().fully()
                    .layout(SHIPPING_ADDRESS_REGION)
                    .ignoreDisplacements());
        }
      }
	}

  public void addNewBillingAddress() {
      waitForExpectedElement(ADD_NEW_BILLING_ADDRESS_BUTTON).isDisplayed();
      jsScrollElementInCenter(waitForExpectedElement(ADD_NEW_BILLING_ADDRESS_BUTTON));
      waitForExpectedElement(ADD_NEW_BILLING_ADDRESS_BUTTON,10).click();
      jsScrollElementInCenter(waitForExpectedElement(ADD_NEW_BILLING_ADDRESS_RADIO_BUTTON));
      waitForExpectedElement(ADD_NEW_BILLING_ADDRESS_RADIO_BUTTON).click();
      waitForExpectedElement(ENTER_ADDRESS_MANUALLY).click();

      waitForExpectedElement(TELEPHONE_BILLING_ADDRESS_INPUT).sendKeys(UPDATED_BILLING_ADDRESS_TELEPHONE);
      List<WebElement> streetLines = getWebDriver().findElements(BILLING_ADDRESS_STREET_INPUTS);
      streetLines.get(1).sendKeys(UPDATED_BILLING_ADDRESS_STREET);

      List<WebElement> cityInput = getWebDriver().findElements(BILLING_ADDRESS_CITY_INPUTS);
      cityInput.get(1).sendKeys(UPDATED_BILLING_ADDRESS_CITY);

      List<WebElement> postcodeInput = getWebDriver().findElements(BILLING_ADDRESS_POSTCODE_INPUTS);
      postcodeInput.get(1).sendKeys(UPDATED_BILLING_ADDRESS_POST_CODE);

      waitForExpectedElement(USE_THIS_ADDRESS).click();

      AssertNewBillingAddressCorrectlyUsedAndDisplayed();
  }

  private void AssertNewBillingAddressCorrectlyUsedAndDisplayed() {
    assertTrue(waitForExpectedElement(BILLING_ADDRESS_TELEPHONE_BLOCK).getText().contains(UPDATED_BILLING_ADDRESS_TELEPHONE));
    List<String> billingAddressBlock= Arrays.asList(waitForExpectedElement(BILLING_ADDRESS_BLOCK).getText().split(","));
    assertTrue(billingAddressBlock.get(1).trim().equals(UPDATED_BILLING_ADDRESS_STREET));
    assertTrue(billingAddressBlock.get(2).trim().equals(UPDATED_BILLING_ADDRESS_CITY));
    assertTrue(billingAddressBlock.get(3).trim().equals(UPDATED_BILLING_ADDRESS_POST_CODE));
  }

  public void completeNewDeliveryAddressFormAndSave(CheckoutPageDeliveryAddressModel deliveryAddressModel) {
        clickByElementByQueryJSExecutor(NEW_DELIVERY_ADDRESS_DELIVER_HERE_OR_SAVE_BUTTON);
        populateAddressFields(deliveryAddressModel);
        clickByElementByQueryJSExecutor(NEW_DELIVERY_ADDRESS_DELIVER_HERE_OR_SAVE_BUTTON);
    }

     private void populateAddressFields(CheckoutPageDeliveryAddressModel deliveryAddressModel) {
        enterText(STREET, deliveryAddressModel.getAddress().getStreetAndHouseNumber());
        enterText(CITY, deliveryAddressModel.getAddress().getCity());
        enterText(POSTCODE, deliveryAddressModel.getAddress().getPostcode());
        enterText(PHONE_NUMBER, deliveryAddressModel.getAddress().getPhoneNumber() );

        if (!new Select(waitForExpectedElement(COUNTRY)).getFirstSelectedOption().isSelected()){
            selectValueFromDropDownByby(deliveryAddressModel.getAddress().getCountry() , COUNTRY);
        }
    }

    public boolean isCheckoutDeliveryNoticeDisplayed() {
        return waitForExpectedElement(CHECKOUT_DELIVERY_NOTICE_POSTCODE_LIST).isDisplayed();
    }

    public void clickOnDeliverHereButton() {
        clickByElementByQueryJSExecutor(DELIVER_HERE_BUTTON);
    }

  public boolean deviceTrialTextBoxPresent() {
    return waitForExpectedElement(deviceTrialCheckbox, 5).isDisplayed();
  }

  public void selectDeviceTrialTextBox(){
    clickByElementByQueryJSExecutor(deviceTrialCheckbox);
  }

  public boolean isDeviceTrialConsentTextBoxPresent() {
      waitForPresenceOfElement(TRAIL_CONSENT);
      return waitForExpectedElement(TRAIL_CONSENT, 20).isDisplayed();
  }

  public void selectDeviceConsentTrialCheckBox(){
      waitForExpectedElement(TRAIL_CONSENT,15);
      try {
        waitForExpectedElement(TRAIL_CONSENT).click();
      }catch(ElementClickInterceptedException e){
        LOG.info("Got ElementClickInterceptedException: "+e);
        jsScrollElementInCenter(waitForExpectedElement(TRAIL_CONSENT));
        waitForExpectedElement(TRAIL_CONSENT).click();
      }
  }

  public boolean isDeviceTrialCreditCardOnlyInfoPresent() {
    return waitForExpectedElement(TRAIL_CREDITCARD_ONLY, 5).isDisplayed();
  }

  public boolean isCardForFutureUseInfoPresent() {
    return waitForExpectedElement(TRAIL_CREDITCARD_ONLY, 5).isDisplayed();
  }

  public boolean isDeviceTrialDebitCardNoInfoPresent() {
    return   waitForExpectedElement(TRAIL_DEBITCARD_NO, 5).isDisplayed();
  }

  public boolean deviceTrialPaymentBlockPresent() {
      if (UrlBuilder.getLocale().equals("glode")) {
        waitForElementToAppearAndDisappear(LOADER,15,15);
        clickByElementByQueryJSExecutor(CREDIT_CARD_RADIO_BTN_GLODE);
      }
      return waitForExpectedElement(deviceTrialPaymentBlock, 20).isDisplayed();
  }

  public boolean assertCheckoutSuccess() {
    return waitForExpectedElement(checkoutSuccess, 5).isDisplayed();
  }

    public boolean isFreeShippingRecommendationMessageDisplayed() {
      if ("kz".equals(getLocale())) {
        return waitForExpectedElement(PAGE_HEADER_ANNOUNCEMENT_CSS_KZ).isDisplayed();
      }
      return waitForExpectedElement(FREE_SHIPPING_RECOMMENDATION_MSG).isDisplayed();
    }

    public void clickMiniCartClose(){
      switch (UrlBuilder.getLocale()) {
        case "fr":
        case "vusefr":
        case "vypeit":
          case "vuseit":
          waitForExpectedElement(MINICART_CLOSE_CTA_VYPEFR).click();
          break;
        default:
          waitForExpectedElement(MINICART_CLOSE_CTA).click();
      }

    }

  public void clickCheckoutNewsLetterSubscriptionCheckbox() {
    if ("velode".equals(getLocale())) {
      clickByElementByQueryJSExecutor(NEWSLETTER_SUBSCRIPTION_CHKBOX_VELO);
    } else {
      clickByElementByQueryJSExecutor(NEWSLETTER_SUBSCRIPTION_CHKBOX);
    }
  }

    public void assertCheckoutNewsLetterCheckBoxAndText(String key) {
        assertThat(waitForExpectedElement(NEWSLETTER_SUBSCRIPTION_CHKBOX).isDisplayed()).isTrue();
        List<String> list = Stream.of(UrlBuilder.getMessage(key).split("\\."))
                .collect(toCollection(LinkedList::new));

        assertThat(waitForExpectedElement(NEWSLETTER_PROMOTION_TEXT_CSS).getText()).contains(list.get(0));
        assertThat(waitForExpectedElement(NEWSLETTER_LABEL_CSS).getText()).contains(list.get(1));
    }

    public void paysWithElectronicInvoiceCheck() {
        selectCreditCardOptions();
        clickTermsAndConditions();
        enterElectronicInvoiceDetails();
        clickByElementByQueryJSExecutor(btnPay);
    }

    public String fetchTheSelectedParcelShopAddress() {
      WebElement element;
      switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
        case LYFTDK:
          element = waitForExpectedElement(GLS_PARCEL_SHOP_PICKUP_ADDRESS_CSS);
          break;
        case LYFTSE:
          element = waitForExpectedElement(DPS_PARCEL_SHOP_PICKUP_ADDRESS_CSS);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + Locale.valueOf(UrlBuilder.getLocale().toUpperCase()));
      }
      return new Select(element).getFirstSelectedOption().getText();
    }

  public void populateAllAddressFieldsOnCheckout() {
    switch (UrlBuilder.getLocale()) {
      case "uk":
      case "vuseuk":
        waitClearAndEnterText(STREET_ADDRESS_FIELD_CHECKOUT, "12 the cloisters");
        waitClearAndEnterText(CITY_FIELD_CHECKOUT, "Watford");
        waitClearAndEnterText(POSTAL_CODE_FIELD_CHECKOUT, "WA3 1HL");
        break;
      case "vuseza":
        waitClearAndEnterText(STREET, "22 Bloukrans Road");
        waitClearAndEnterText(SHIPPING_STREET_ADDRESS_2,"Ladysmith");
        waitClearAndEnterText(PHONE_NUMBER_FIELD,UrlBuilder.getMessage("validPhoneNumber.key"));
        selectValueFromDropDownByIndex(2,STATE_DROP_DOWN);
        waitClearAndEnterText(CITY,"Alfred Duma");
        waitClearAndEnterText(POSTCODE,"3370");
        clickByElementByQueryJSExecutor(USE_THIS_ADDRESS_BUTTON);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        break;
      case "veloza":
        enterText(PAYMENT_ADDRESS_BOOK_ADD_NEW_ADDRESS_TELEPHONE,"123456789");
        enterText(PAYMENT_ADDRESS_BOOK_STREET_ADDRESS_VELOZA, "12 Albert Street");
        enterText(PAYMENT_ADDRESS_BOOK_SUBURB_VELOZA, "12 Albert Street");
        selectValueFromDropDownByby(UrlBuilder.getMessage("Province.key"), province);
        enterText(POSTCODE_PAYMENTPAGE, "10-229");
        enterText(CITY_PAYMENTPAGE, "Sosnowiec");
        waitForItemToBeClickableAndClick(ADD_NEW_ADDRESS_SAVE_BUTTON_VELOZA);
        break;
      default:
        LOG.info("This Step is also running for Vype UK as of now.");
    }
  }

  public void userPlaceOrderAfterRemovingTofinoProduct() {
    clickByElementByQueryJSExecutor(REMOVE_TOFINO_PRODUCT);
  }

  public void userAddsNewAddressWithAllowedPostCode() {
    clickFirstElementByQueryJSExecutor(CHANGE_ADDRESS_LINK);
    waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON_UK,10);
    clickByElementByQueryJSExecutor(ADD_NEW_ADDRESS_BUTTON_UK);
    if(getWebDriver().findElements(ADDRESS_MANUAL_ENTRY_ACTIVE).size()==0)
        waitForExpectedElement(ENTER_ADDRESS_MANUALLY_LINK,10).click();
    populateAllAddressFieldsOnCheckout();
    clickByElementByQueryJSExecutor(USE_THIS_ADDRESS_BUTTON);
    waitForAjaxElementNotToBePresent(getWebDriver(),5);
  }

  public void saveCard() {
    switch (valueOf(getLocale().toUpperCase())) {
      case VUSEUK:
        LOG.info("On Vype UK Site trying to tick use this card tickbox");
        clickByElementByQueryJSExecutor(ukVypeCXChkUseThisCard);
        break;
      case MX:
        break;
      case EPOK:
      case VELODE:
        clickByElementByQueryJSExecutor(SAVE_CARD_WORLDPAY_CHK_BOX);
        break;
      default:
        try {
          clickByElementByQueryJSExecutor(chkUseThisCard);
        } catch (Exception e) {
          clickUsingJS(SAVE_CARD_WORLDPAY_CHK_BOX);
        }
    }
  }

  public void selectContinueWithPods() {
    waitForExpectedElement(ENGRAVING_CONTINUE_PODS,10).click();
  }

  public void selectAlternativePaymentOptions() {
        if(UrlBuilder.isDesktop()) {
            waitForPage();
            waitForExpectedElement(ALTERNATIVE_PAYMENT_OPTIONS, 30);
            clickByElementByQueryJSExecutor(ALTERNATIVE_PAYMENT_OPTIONS);
            waitForLoaderToDisapear();
        }
  }

  public void selectSoFortRadioButton() {
    waitForExpectedElement(RADIO_SOFORT_PAYMENT_OPTION,30);
    clickByElementByQueryJSExecutor(RADIO_SOFORT_PAYMENT_OPTION);
  }

  public void selectSofortContinueButton() {
    waitForPage();
    clickByElementByQueryJSExecutor(BUTTON_SOFORT_CONTINUE_OPTION);
  }

  public void selectPaypalRadioButton() {
      if (UrlBuilder.getLocale().equals("vusede")) {
        waitForExpectedElement(RADIO_PAYPAL_VUSEDE, 30);
        clickByElementByQueryJSExecutor(RADIO_PAYPAL_VUSEDE);
      } else {
        try {
          waitForExpectedElement(RADIO_PAYPAL_OPTIONS, 30);
          clickByElementByQueryJSExecutor(RADIO_PAYPAL_OPTIONS);
        }catch (TimeoutException te){
          waitForExpectedElement(RADIO_PAYPAL_OPTIONS_SE, 30);
          clickByElementByQueryJSExecutor(RADIO_PAYPAL_OPTIONS_SE);
        }
      }
  }

  public void selectPayPalContinueButton() {
      if(UrlBuilder.isDesktop()){
        waitForPage();
        waitForExpectedElement(BUTTON_PAYPAL_CONTINUE,20);
        clickByElementByQueryJSExecutor(BUTTON_PAYPAL_CONTINUE);
      }
      else {
        waitForExpectedElement(btnConfirmContinue, 20);
        if (getWebDriver().findElements(btnConfirmContinue).size() > 0)
          clickByElementByQueryJSExecutor(btnConfirmContinue);
        waitForExpectedElement(btnValidatePayment, 30);
        scrollElementIntoView(btnValidatePayment);
        clickByElementByQueryJSExecutor(btnValidatePayment);
        waitForAjaxElementNotToBePresent(getWebDriver(), 25);
      }
  }

  public void assertNewsletterSignUpNotDisplayedForTofinoProducts() {
    assertTrue(invisibilityOfElementLocated(NEWSLETTER_SUBSCRIPTION_COMPONENT));}

  public void selectSavedCardAndEnterCVV() {
    selectValueFromDropDownByIndex(1, SAVED_CARD_DRP_DWN_CO);
    waitForExpectedElement(SAVED_CARD_DRP_DWN_CO);
    waitForExpectedElement(CVV_INPUT_BOX_CO).sendKeys("123");
  }

  public float getSubtotalPrice() {
    return Float.parseFloat(getWebDriver().findElements(SUB_TOTAL_PRICE).get(0).getText().replaceAll("[\\D]", ""));
  }

  public float getShippingPrice() {
      return Float.parseFloat(getWebDriver().findElements(DELIVERY_CHARGES_MINIBASKET).get(0).getText().replaceAll("[\\D]", ""));
  }

  public String getTotalPriceText() {
      waitForExpectedElement(TOTAL_CHARGES_MINIBASKET, 20);
      return getWebDriver().findElements(TOTAL_CHARGES_MINIBASKET).get(0).getText();
  }

  public float getTotalPrice() {
      return Float.parseFloat(getWebDriver().findElements(TOTAL_CHARGES_MINIBASKET).get(0).getText().replaceAll("[\\D]", ""));
  }

  public Map<String, List<WebElement>> getOrderItemsInOrderOverviewCheckoutPage() {
    Map<String, List<WebElement>> map = new HashMap<>();
    waitForExpectedElement(CHECKOUT_PRODUCTS_LIST,10);
    productItems = waitForExpectedElement(CHECKOUT_PRODUCTS_LIST).findElements(By.tagName("li"));
    map.put("OrderOverViewItemsList", productItems);
    map.put("OrderOverViewItemsNamesList", getWebDriver().findElements(CHECKOUT_PRODUCTS_NAMES));
    map.put("OrderOverViewItemsPriceList", getWebDriver().findElements(CHECKOUT_PRODUCTS_PRICES));
    map.put("OrderOverViewItemsQuantityList", getWebDriver().findElements(CHECKOUT_PRODUCTS_QTYS));
    return map;
  }

  public void assertDeviceTrialDiscountPresent() throws InterruptedException {
    waitForExpectedElement(TRAIL_DISCOUNT_INFOS,20);
    if (getWebDriver().findElements(TRAIL_DISCOUNT_INFOS).size() == 2) {
      String trialPeriod = UrlBuilder.getMessage("trialDuration.key") + " DAY TRIAL";
      assertTrue(getWebDriver().findElements(TRAIL_DISCOUNT_INFOS).get(0).getText().contains(trialPeriod));
      String whichLocale = UrlBuilder.getLocale();
      switch (whichLocale) {
        case "vuseuk":
          assertTrue(getWebDriver().findElements(TRAIL_DISCOUNT_INFOS).get(1).getText().contains("-£"));
          break;
        case "vypeit":
        case "vusefr":
          assertTrue(getWebDriver().findElements(TRAIL_DISCOUNT_INFOS).get(1).getText().contains("-") && getWebDriver().findElements(TRAIL_DISCOUNT_INFOS).get(1).getText().contains("€") );

      }
      if (getWebDriver().findElements(TRAIL_DISCOUNT_INFO_PAYMENT_DUE).size() == 1) {
        assertTrue(getWebDriver().findElements(TRAIL_DISCOUNT_INFO_PAYMENT_DUE).get(0).getText().contains(UrlBuilder.getMessage("trialDetailCurrency.key")));
      }
      if (getWebDriver().findElements(TRAIL_DISCOUNT_FUTURE_PAYMENT_NOTE).size() == 1) {
        assertTrue(getWebDriver().findElements(TRAIL_DISCOUNT_FUTURE_PAYMENT_NOTE).get(0).getText().contains(UrlBuilder.getMessage("trialDetailAmount.key")));
      }
    }
  }

  public void addNewAddressOnPaymentPage() {
    switch (UrlBuilder.getLocale()) {
      case "pl":
        waitClearAndEnterText(streetAddressLineOnPyment, "Främsteby Karlsborg");
        waitClearAndEnterText(houseNumber, "64");
        waitClearAndEnterText(cityInputOnPayment, "FURULUND");
        waitClearAndEnterText(phoneNumberInput, "223457789");
        waitClearAndEnterText(postCodeInput, "24-559");
        break;
      case "velode":
        addNewAddressPage.addAddressManuallyOnDeliveryAddressPage(UrlBuilder.getMessage("streetAndHouseNumber.key"), UrlBuilder.getMessage("city.key"), UrlBuilder.getMessage("postal.key"), UrlBuilder.getMessage("telephone.key"));
        break;
      default:
        clickByElementByQueryJSExecutor(ADD_NEW_ADDRESS);
        addNewAddressPage.populateAllAddressInputFieldsIncludingFirstAndLastName();
        clickByElementByQueryJSExecutor(BUTTON_ADDRESS_SAVE);
        break;
    }
  }

  public void assertSavedcardcheckboxselectedTrial() {
    Boolean saveCardTickBoxSelected =  waitForExpectedElement(SAVE_CARD_WORLDPAY_CHK_BOX_STATUS).isSelected();
    LOG.info("Status of Savecard checkbox on Trial checkout page : " + saveCardTickBoxSelected);
    assertTrue("ERROR *** Save card Tick box SHOULD be selected",saveCardTickBoxSelected);
  }

  public void clickBackButtonCheckoutPage(){
      waitForExpectedElement(BACK_BUTTON_ON_CHECKOUTPAGE).click();
  }

  public void selectWalletPaymentsOption(){
      waitForExpectedElement(WALLET_OPTION,10);
      clickByElementByQueryJSExecutor(WALLET_OPTION);
      waitForExpectedElement(GOOGLE_PAY_CHECKBOX,4);
      clickByElementByQueryJSExecutor(GOOGLE_PAY_CHECKBOX);
  }

  public void engravingDetailsConfirmationOnCheckoutPage() {
    try{
      clickByElementByQueryJSExecutor(CHECKOUTPAGE_PRODUCT_DETAILS_EXPENSION);}
    catch(Exception ex){
        getWebDriver().navigate().refresh();
        waitAndClickByElementByJSExecutor(CHECKOUTPAGE_PRODUCT_DETAILS_EXPENSION,10);
    }
    Assert.assertTrue(
        waitForExpectedElement(CHECKOUTPAGE_ENGRAVING_DETAILS_CONFIRMATION,10).isDisplayed());
  }
  public void clickOnManualAddressEntryLink(){
    switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
      case VUSEIT:
      case VUSEFR:
        waitForExpectedElement(UK_ADDRESS_MANUALLY, 5);
        clickByElementByQueryJSExecutor(UK_ADDRESS_MANUALLY);
        break;
      case PL:
        waitForExpectedElement(addNewAddressPage.TOGGLE_FULL_ADDRESS, 5);
        if(getWebDriver().findElements(addNewAddressPage.TOGGLE_FULL_ADDRESS).size()>0)
            clickByElementByQueryJSExecutor(addNewAddressPage.TOGGLE_FULL_ADDRESS);
        break;
      default:
        waitForExpectedElement(MANUAL_ADDRESS_ENTRY_LINK_VUSEDE, 5);
        clickByElementByQueryJSExecutor(MANUAL_ADDRESS_ENTRY_LINK_VUSEDE);
    }
  }

  public void selectDeliveryMethodBy(String deliveryMethodName) {
    if (valueOf(getLocale().toUpperCase()) == VUSEUK) {
      By shippingMethodRadioButton = By.xpath("//td[contains(text(), '" + deliveryMethodName.toUpperCase() + "')]/ancestor::tr//input");
      clickElementByQueryJSExecutor(waitForExpectedElement(shippingMethodRadioButton));
      waitForElementToAppearAndDisappear(LOADER, 2, 5);
      if (waitForExpectedElement(shippingMethodRadioButton).getAttribute("class") != "radio active") {
        clickElementByQueryJSExecutor(waitForExpectedElement(shippingMethodRadioButton));
        waitForElementToAppearAndDisappear(LOADER, 2, 5);
      }
    } else {
      List<WebElement> tableRows = getTableRows(SHIPPING_METHODS_TABLE);
      for (WebElement row : tableRows) {
        for (WebElement col : row.findElements(By.tagName("td"))) {
          if (col.getText().contains(deliveryMethodName.toUpperCase())) {
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            clickElementByQueryJSExecutor(row.findElement(SHIPPING_METHOD_RADIO_BTN));
          }
        }
      }
    }
  }

  public void verifyDeliveryChargesInTheOrderSummery() {
      switch (valueOf(getLocale().toUpperCase())) {
        case VUSEFR:
          paymentPageDetailsConfirmed();
          //if the order price is more than 30 then the customer should get free shipping,
          // otherwise shipping charges 3.50 should be added to the price
          assertThat(getShippingPrice()).isEqualTo(getSubtotalPrice() >= 4000.0f ? 0.00f : 399.0f);
          break;
        case VUSEUK:
          paymentPageDetailsConfirmed();
          //if the order price is more than 50 then the customer should get free shipping,
          // otherwise shipping charges 4.99 should be added to the price
          assertThat(getShippingPrice()).isEqualTo(getSubtotalPrice() >= 5000.0f ? 0.00f : 499.0f);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + valueOf(getLocale().toUpperCase()));
      }
  }

  public void deliveryMethodsDetails() {
    assertTrue(waitForExpectedElement(DELIVERY_METHOD_TITLE).isDisplayed());
    for (int i = 0; i < 2.; i++) {
      assertTrue(waitForExpectedElement(SHIPPING_METHODS).isDisplayed());
    }
    //price and radio button displayed for all available methods
    assertTrue(waitForExpectedElement(SHIPPING_METHODS_FREE_PRICE).isDisplayed());
    assertTrue(waitForExpectedElement(SHIPPING_METHODS_FREE_RADIO).isDisplayed());
    //assertTrue(waitForExpectedElement(SHIPPING_METHODS_STANDARD_PRICE).isDisplayed());
    //assertTrue(waitForExpectedElement(SHIPPING_METHODS_STANDARD_RADIO).isDisplayed());
    //Delivery methods update for Vuse UK
    //assertTrue(waitForExpectedElement(SHIPPING_METHODS_DELIVERY_PRICE).isDisplayed());
    //assertTrue(waitForExpectedElement(SHIPPING_METHODS_DELIVERY_RADIO).isDisplayed());
  }

  public void deliverymethodsdetails2() {
    waitForExpectedElement(SHIPPING_METHODS_STANDARD_RADIO, 5).click();
    assertTrue(waitForExpectedElement(SHIPPING_METHODS_DELIVERY_RADIO).isSelected());
  }

  public boolean isDiscountTitlePresent() {
    return isElementDisplayedBySeconds(VUSE_DE_DISCOUNT_TITLE, 15);
  }

  public boolean isDiscountAmountPresent() {
    return isElementDisplayedBySeconds(VUSE_DE_DISCOUNT_AMOUNT, 15);
  }

  public void clickChangeAddressLink() {
    clickFirstElementByQueryJSExecutor(CHANGE_ADDRESS_LINK);
  }

  public void clickAddNewAddressLink() {
    clickByElementByQueryJSExecutor(ADD_NEW_ADDRESS_BUTTON_UK);
  }

  public boolean isNewAddressButtonPresent() {
    return isElementPresent(ADD_NEW_ADDRESS_BUTTON_UK, 10);
  }

  public boolean isModalTitlePresent() {
    return isElementPresent(NEW_ADDRESS_MODAL_TITLE, 10);
  }

  public boolean isAddressFinderPresent() {
    return isElementPresent(ADDRESS_FINDER, 10);
  }

  public boolean isAddressTogglePresent() {
    return isElementPresent(ADDRESS_TOGGLE, 10);
  }

  public boolean isFreeDeliveryRadioButtonPresent() {
    return isElementPresent(FREE_DELIVERY_RADIO_BUTTON, 10);
  }

  public void clickOnAShipOptionOtherThanFree() { clickByElementByQueryJSExecutor(FIRST_SHIPPING_METHOD); }
  public void userClicksOnChangeAddressLinkCheckoutPage
          () {
    switch (UrlBuilder.getLocale()) {
      case "veloza":
        waitAndClickByElementByJSExecutor(CHANGE_ADDRESS_LINK_ZA,10);
        waitForItemToBeClickableAndClick(ADD_NEW_ADDRESS_ZA);
        waitForPage();
        break;
      default:
    }
  }
  public void userClicksOnChangeAddressLink() {
      switch (UrlBuilder.getLocale()) {
          case "vusefr":
              waitAndClickByElementByJSExecutor(CHANGE_ADDRESS_LINK_ZA, 3);
              waitAndClickByElementByJSExecutor(ADD_NEW_ADDRESS_ZA, 3);
              break;
          case "vuseuk":
          case "vuseza":
              waitAndClickByElementByJSExecutor(CHANGE_ADDRESS_LINK_ZA,10);
              waitForItemToBeClickableAndClick(ADD_NEW_ADDRESS_ZA);
              waitForPage();
              break;
        case "veloza":
          waitAndClickByElementByJSExecutor(CHANGE_ADDRESS_LINK_ZA,20);
          waitForItemToBeClickableAndClick(ADD_NEW_ADDRESS_ZA);
          waitForPage();
          enterText(PAYMENT_ADDRESS_BOOK_ADD_NEW_ADDRESS_TELEPHONE,"123456789");
          enterText(PAYMENT_ADDRESS_BOOK_STREET_ADDRESS_VELOZA, "12 Albert Street");
          enterText(PAYMENT_ADDRESS_BOOK_SUBURB_VELOZA, "12 Albert Street");
          selectValueFromDropDownByby(UrlBuilder.getMessage("Province.key"), province);
          enterText(POSTCODE_PAYMENTPAGE, "10-229");
          enterText(CITY_PAYMENTPAGE, "Sosnowiec");
          waitForItemToBeClickableAndClick(ADD_NEW_ADDRESS_SAVE_BUTTON_VELOZA);
              break;
          default:
      }
  }

  public void clickUseThisAddress() {
    waitForElementToAppearAndDisappear(LOADER,2,5);
    clickUsingJS(USE_THIS_ADDRESS_BUTTON);
  }

  public void assertAddressIsSet() {
    String oldAddress;
    waitForElementToAppearAndDisappear(LOADER,2,5);
    String newAddress = waitForExpectedElement(CURRENT_DELIVERY_ADDRESS).getText();
    oldAddress = scenarioContext.getContext(Context.CURRENT_ADDRESS).toString();

    assertThat(newAddress)
            .as("ERROR assertAddressIsSet: address was not set correctly old address was "+oldAddress+" nw address was "+newAddress).isNotEqualTo(oldAddress);
  }

  public void onTheCheckoutPage() {
    waitForElementToAppearAndDisappear(LOADER,5,10);
   waitForAjaxElementNotToBePresent(webDriver,25);
    String oldAddress = waitForExpectedElement(CURRENT_DELIVERY_ADDRESS).getText();
    scenarioContext.setContext(CURRENT_ADDRESS, oldAddress);
    assertThat(doesURLContain("/checkout/")).isTrue()
            .as("ERROR onTheCheckoutPage: user expected to be on checkout page but wasn't").isTrue();
  }

  public void canSeeDeliveryAndPaymentOptions() {
    waitForElementToAppearAndDisappear(LOADER,2,5);
      String expectedDeliveryAddressHeading = UrlBuilder.getMessage("deliveryAddressHeading" + "-" + scenarioContext.getContext(Context.LANGUAGE));
      String actualDeliveryAddressHeading = waitForExpectedElement(DELIVERY_ADDRESS_HEADING, 10).getText();
      assertThat(expectedDeliveryAddressHeading.equals(actualDeliveryAddressHeading))
              .as("ERROR canSeeDeliveryAndPaymentOptions: expected delivery address heading was "+expectedDeliveryAddressHeading+" but I got "+actualDeliveryAddressHeading).isTrue();
      String actualDeliveryAddressName = waitForExpectedElement(DELIVERY_ADDRESS_NAME).getText();
      String firstAndLastName = scenarioContext.getContext(Context.FIRST_AND_LAST_NAME).toString();
      String firstName = firstAndLastName.split(" ")[0];
      String lastName = firstAndLastName.split(" ")[1];
      assertThat(actualDeliveryAddressName.toLowerCase().contains(firstName.toLowerCase()))
              .as("ERROR canSeeDeliveryAndPaymentOptions: couldn't find first name "+firstName+" in delivery name "+actualDeliveryAddressName).isTrue();
      assertThat(actualDeliveryAddressName.toLowerCase().contains(lastName.toLowerCase()))
            .as("ERROR canSeeDeliveryAndPaymentOptions: couldn't find last name "+lastName+" in delivery name "+actualDeliveryAddressName).isTrue();
      String actualDeliveryMethodHeading = waitForExpectedElement(DELIVERY_METHOD_HEADING).getText();
      String expectedDeliveryMethodHeading = UrlBuilder.getMessage("deliveryMethodHeading" + "-" + scenarioContext.getContext(Context.LANGUAGE).toString());
      assertThat(expectedDeliveryMethodHeading.equalsIgnoreCase(actualDeliveryMethodHeading))
              .as("ERROR canSeeDeliveryAndPaymentOptions: expected delivery method heading was "+expectedDeliveryMethodHeading+" but I got "+actualDeliveryMethodHeading).isTrue();
      List<WebElement> deliveryMethodOptions = waitForExpectedElements(DELIVERY_METHOD_OPTIONS);
      assertThat(deliveryMethodOptions.size() > 0)
              .as("ERROR canSeeDeliveryAndPaymentOptions: expected at least one deleivery method but couldn't find any").isTrue();
  }

  public void canSeeOrderSummary() {
    if (Props.getProp("siteMode").equalsIgnoreCase("desktop")) {
      String expectedOrderSummaryTitle = UrlBuilder.getMessage("ordersummaryTitle" + "-" + scenarioContext.getContext(Context.LANGUAGE)).toString();
      String actualOrderSummaryTitle = waitForExpectedElement(ORDER_SUMMARY_TITLE).getText();
      assertThat(expectedOrderSummaryTitle.equalsIgnoreCase(actualOrderSummaryTitle))
              .as("ERROR canSeeOrderSummary: expected order summary title was " + expectedOrderSummaryTitle + " but I got " + actualOrderSummaryTitle).isTrue();
    }
  }

  public void checkForChangeAddressLink() {
      String actualChangeAddressLinkText = waitForExpectedElement(CHANGE_ADDRESS_LINK).getText();
      String expectedChangeAddressLinkText = UrlBuilder.getMessage("changeAddressLinkText" + "-" + scenarioContext.getContext(Context.LANGUAGE).toString());
      assertThat(expectedChangeAddressLinkText.equalsIgnoreCase(actualChangeAddressLinkText))
              .as("ERROR checkForChangeAddressLink: expected change address link text was "+expectedChangeAddressLinkText+" but I got "+actualChangeAddressLinkText).isTrue();
  }

  public void clickOnChangeAddress() {
      try {
        waitForElementToDisappear(LOADER, 10);
        waitForExpectedElement(CHANGE_ADDRESS_LINK).click();
      } catch (Exception e) {
        clickUsingJS(CHANGE_ADDRESS_LINK);
      }
  }

  public void validateAddressesDisplayedOnCheckoutPage() {
      boolean found = false;
    List<WebElement> previouslyUsedAddresses = waitForExpectedElements(PREVIOUSLY_USED_ADDRESSES);
    assertThat(previouslyUsedAddresses.size() > 0)
            .as("ERROR validateAddressesDisplayedOnCheckoutPage: didn't find any previously used addersses").isTrue();
    String currentAddress = waitForExpectedElement(CURRENT_DELIVERY_ADDRESS).getText();
    for (WebElement previouslyUsedAddress: previouslyUsedAddresses) {
      if (previouslyUsedAddress.getText().toLowerCase().contains(currentAddress.toLowerCase())) {
        found = true;
        break;
      }
    }
    assertThat(found).as("ERROR validateAddressesDisplayedOnCheckoutPage: couldn't find address "+currentAddress+" in prevously used addresses").isTrue();
  }

  public void validateAddNewAddressCta() {
      String expectedAddNewAddressLinkText = UrlBuilder.getMessage("newAddressModalHeading-" + scenarioContext.getContext(Context.LANGUAGE).toString());
      String actualAddNewAddressLinkText = waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON).getText();
      assertThat(expectedAddNewAddressLinkText.equalsIgnoreCase(actualAddNewAddressLinkText))
              .as("ERROR validateAddNewAddressCta: expected add new address link text was "+expectedAddNewAddressLinkText+" but I got "+actualAddNewAddressLinkText).isTrue();
      waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON).click();
  }

  public void validateUseThisAddresCta() {
      String expectedUseThisAddressCtaText = UrlBuilder.getMessage("useThisAddressButtonText-" + scenarioContext.getContext(Context.LANGUAGE).toString());
      String actualUseThisAddressCtaText = waitForExpectedElement(USE_THIS_ADDRESS_BUTTON).getText();
      assertThat(expectedUseThisAddressCtaText.equalsIgnoreCase(actualUseThisAddressCtaText))
            .as("ERROR validateAddNewAddressCta: expected add new address link text was "+expectedUseThisAddressCtaText+" but I got "+actualUseThisAddressCtaText).isTrue();
  }

  public void validateAddNewAddressCancel() {
      assertThat(waitForExpectedElement(ADD_NEW_ADDRESS_CLOSE).isDisplayed())
              .as("ERROR validateAddNewAddressCancel: close CTA is not displayed").isTrue();
    assertThat(waitForExpectedElement(ADD_NEW_ADDRESS_CLOSE).isEnabled())
            .as("ERROR validateAddNewAddressCancel: close CTA is not enabled").isTrue();
    waitForExpectedElement(ADD_NEW_ADDRESS_CLOSE).click();
    List<WebElement> addNewAddressModal = webDriver.findElements(ADD_NEW_ADDRESS_MODAL);
    assertThat(addNewAddressModal.size() == 0)
            .as("ERROR validateAddNewAddressCancel: add new address modal did not close").isTrue();
  }

  public void validateShippingMethod() {
    String actualDeliveryOptionDescription;
    String language = scenarioContext.getContext(LANGUAGE).toString();
    List<WebElement> deliveryOptions = webDriver.findElements(DELIVERY_METHOD_OPTIONS);
    assertThat(deliveryOptions.size() == 1)
            .as("ERROR validateShippingMethod: expected 1 delivery option but i found "+deliveryOptions.size()).isTrue();
    String expectedDeliveryOptionDescription = UrlBuilder.getMessage("deliveryOptionDescription-" + language);
    if (Props.getProp("siteMode").equalsIgnoreCase("desktop")) {
      actualDeliveryOptionDescription = deliveryOptions.get(0).findElement(AVALANCHE_DELIVERY_OPTION_DESCRIPTION).getText();
    } else {
      actualDeliveryOptionDescription = deliveryOptions.get(0).findElement(M_AVALANCHE_DELIVERY_OPTION_DESCRIPTION).getText();
    }
    assertThat(expectedDeliveryOptionDescription.equalsIgnoreCase(actualDeliveryOptionDescription))
            .as("ERROR validateShippingMethod: expected delivery option description was "+expectedDeliveryOptionDescription+" but I got "+actualDeliveryOptionDescription).isTrue();
  }

  public void deliveryMethodHasRadioButton() {
      assertThat(waitForExpectedElement(SHIPPING_METHOD_RADIO_BTN).isDisplayed())
              .as("ERROR deliveryMethodHasRadioButton: shipping method did not have a radio button displayed").isTrue();
  }

  public void expandableCardPaymentsdisplayed() {
      try {
        waitForExpectedElement(UrlBuilder.getLocale().equalsIgnoreCase("velobe")?CARD_PAYMENTS_EXPANDABLE_LINK_VELO_BE:CARD_PAYMENTS_EXPANDABLE_LINK).click();
      } catch (Exception e) {
        clickByElementByQueryJSExecutor(UrlBuilder.getLocale().equalsIgnoreCase("velobe")?CARD_PAYMENTS_EXPANDABLE_LINK_VELO_BE:CARD_PAYMENTS_EXPANDABLE_LINK);
      }
      if (UrlBuilder.getLocale().equals("velobe")) {
        assertThat(waitForExpectedElement(CHECKBOX_MASTERCARD_TYPE).isDisplayed())
                .as("ERROR expandableCardPaymentsdisplayed: mastercard tick box was not displayed as expected").isTrue();
        assertThat(waitForExpectedElement(CHECKBOX_VISACARD_TYPE).isDisplayed())
                .as("ERROR expandableCardPaymentsdisplayed: visa tick box was not displayed as expected").isTrue();
      } else {
        assertThat(waitForExpectedElement(AVALANCHE_VISA_MC_SELECTION_BUTTON).isDisplayed())
                .as("ERROR expandableCardPaymentsdisplayed: couldn't find button for visa/mastercard").isTrue();
      }
  }

  public void canSeeTotalAmountPayable() {
    waitForExpectedElement(PAYMENT_SUMMARY);
    List<WebElement> paymentSummary = webDriver.findElements(PAYMENT_SUMMARY);
    assertThat(paymentSummary.size() == 1)
            .as("ERROR canSeeTotalAmountPayable: payment summary block was not displayed").isTrue();
    String actualAmountToPayText = paymentSummary.get(0).findElement(HEADING_TEXT).getText();
    String expectedAmountToPayText = UrlBuilder.getMessage("amountToPayText-" + scenarioContext.getContext(Context.LANGUAGE).toString());
    assertThat(expectedAmountToPayText.equalsIgnoreCase(actualAmountToPayText))
            .as("ERROR canSeeTotalAmountPayable: expected amount to pay text was "+expectedAmountToPayText+" but I got "+actualAmountToPayText).isTrue();
  }

  public void thereIsCheckboxToSaveCardDetails() {
      assertThat(waitForExpectedElement(SAVE_CARD_WORLDPAY_CHK_BOX).isDisplayed())
              .as("ERROR thereIsCheckboxToSaveCardDetails: no saved card tickbox displayed").isTrue();
  }

  public void thereIsConsentCheckbox() {
      List<WebElement> tickboxes = waitForExpectedElements(AVALANCHE_AGREEMENT_TICKBOX);
      clickUsingJS(tickboxes.get(0));
  }

  public void thereIsLinkToTermsAndConditionsPolicy() {
      List<WebElement> tickboxes = waitForExpectedElements(AVALANCHE_AGREEMENT_TICKBOX);
      clickElementByQueryJSExecutor(tickboxes.get(1));
  }

  public void iShouldSeeThatConfirmationIsSentToMyEmailAddress() {
    String emailAddress = UrlBuilder.getMessage("loginValidEmail.key");
    String expectedEmailConfirmationText = UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("emailConfirmationText")).replace("%EMAIL%", emailAddress);
    // getText with embedded <strong> tag returns /n in string so below is a workaround
    String actualEmailConfirmationText = waitForExpectedElement(AVALANCHE_ORDER_CONFIRMATION_EMAIL_TEXT, 20).getAttribute("innerHTML").replaceAll("\\<.*?\\>", "").replace("&nbsp;"," ");
    assertThat(expectedEmailConfirmationText.equalsIgnoreCase(actualEmailConfirmationText))
            .as("ERROR iShouldSeeThatConfirmationIsSentToMyEmailAddress: expected confirmation message was " + expectedEmailConfirmationText + " but I got " + actualEmailConfirmationText).isTrue();
  }

  public void thereIsACTAToGoToMyAccount() {
      String actualGoToMyAccountButtonText = waitForExpectedElement(AVALANCHE_ORDER_CONFIRMATION_GOT_TO_MY_ACCOUNT_LINK).getText();
      String expectedGoToMyAccountButtonText = UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("goToMyAccountButtonText"));
      assertThat(expectedGoToMyAccountButtonText.equalsIgnoreCase(actualGoToMyAccountButtonText))
              .as("ERROR thereIsACTAToGoToMyAccount: expected go to my account button text was " + expectedGoToMyAccountButtonText + " but I got " + actualGoToMyAccountButtonText).isTrue();
  }

  public void thereIsACTAToReturnToHomepage() {
      String actualReturnToHomeButtonText = waitForExpectedElement(AVALANCHE_ORDER_CONFIRMATION_RETURN_TO_HOME_LINK).getText();
      String expectedReturnToHomeButtonText = UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("returnToHomeButtonText"));
      assertThat(expectedReturnToHomeButtonText.equalsIgnoreCase(actualReturnToHomeButtonText))
            .as("ERROR thereIsACTAToReturnToHomepage: expected return to home button text was " + expectedReturnToHomeButtonText + " but I got " + actualReturnToHomeButtonText).isTrue();
  }

  public void iCanSeeTheDeliveryOptionsAvailable() {
      String deliveryIncludedText = UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("deliveryIncludedText"));
      String deliveryMethodDescription;
      String deliveryMethodPrice;
    List<WebElement> deliveryMethods = waitForExpectedElements(AVALANCHE_DELIVERY_METHOD);
    for (WebElement deliveryMethod: deliveryMethods) {
      deliveryMethodDescription = deliveryMethod.findElement(By.cssSelector("strong.col-method")).getText();
      assertThat(deliveryMethod.findElement(By.cssSelector("input[type='radio']")).isDisplayed())
              .as("ERROR iCanSeeTheDeliveryOptionsAvailable: could not find a radio button for " + deliveryMethodDescription).isTrue();
      deliveryMethodPrice = deliveryMethod.findElement(By.cssSelector("span.price")).getText();
      assertThat(deliveryMethodPrice.equalsIgnoreCase(deliveryIncludedText)
              || basketPage.validPrice(deliveryMethodPrice))
              .as("ERROR iCanSeeTheDeliveryOptionsAvailable: expected delivery price to be either a valid price or " + deliveryIncludedText).isTrue();
    }
  }

  public void assertThankYou(String expectedThankYouText) {
      waitForElementToAppearAndDisappear(LOADER,5,30);
      String actualThankYouText = waitForExpectedElement(AVALANCHE_ORDER_CONFIRMATION_THANKYOU).getText();
      assertThat(expectedThankYouText.equalsIgnoreCase(actualThankYouText))
              .as("ERROR assertThankYou: expected thank you message was " + expectedThankYouText + " but I got " + actualThankYouText).isTrue();
  }

  public void assertOrderNumberMessage(String expectedOrderNumberMessage) {
    String actualOrderNumberText = waitForExpectedElements(AVALANCHE_ORDER_CONFIRMATION_MESSAGES).get(0).getText();
    assertThat(actualOrderNumberText.toLowerCase().contains(expectedOrderNumberMessage.toLowerCase()))
            .as("ERROR assertThankYou: expected thank you message was " + expectedOrderNumberMessage + " but I got " + actualOrderNumberText).isTrue();
  }

  public void navigateToPlp() {
    if (UrlBuilder.isDesktop() ) {
      switch (UrlBuilder.getLocale()) {
        case "velopl":
          waitAndClickByElementByJSExecutor(AVALANCHE_PRODUCTS_PL,3);
          plp.eyesCheckPlpPage();
          plp.eyesCheckPlpHoverRegion();
          break;
        case "veloza":
        case "velobe":
          waitAndClickByElementByJSExecutor(AVALANCHE_PRODUCTS_BE,3);
          break;
      }
    } else {
      waitForAjaxElementNotToBePresent(webDriver,5);
      clickUsingJS(M_AVALANCHE_HAMBURGER_MENU);

      switch (UrlBuilder.getLocale()) {
        case "velopl":
          waitForExpectedElements(M_AVALANCHE_HANBURGER_MENU_OPTIONS).get(1).click();
          break;
        case "veloza":
        case "velobe":
          waitForExpectedElements(M_AVALANCHE_HANBURGER_MENU_OPTIONS).get(0).click();
          break;
      }
    }
  }

  public void checkoutAnAvailableProduct() {
      navigateToPlp();
    if (!(UrlBuilder.getLocale().equalsIgnoreCase("veloza") || UrlBuilder.getLocale().equalsIgnoreCase("velopl"))) {
        homePage.cookieClickAllowAllAndClickOver18();
    }
    if (UrlBuilder.getLocale().equalsIgnoreCase("velopl")){
      plp.selectFirstAvailableProductPl();
    }else {
      plp.selectFirstAvailableProduct();
    }
      homePage.clickBasketIcon();
    if(UrlBuilder.getLocale().equalsIgnoreCase("velobe") && isElementDisplayedBySeconds(plp.VELO_BE_PLP_MINI_CART_CLOSE_BUTTON,5)){
      clickUsingJS(plp.VELO_BE_PLP_MINI_CART_CLOSE_BUTTON);
    }
      clickOnViewBasketButton();
      clickOnCheckoutButton();
      waitForElementToAppearAndDisappear(LOADER, 2, 5);
  }

  public void checkoutSimpleProduct(){
    plp.addSimpleProduct();
    homePage.clickBasketIcon();
    clickOnViewBasketButton();
    clickOnCheckoutButton();
    waitForElementToAppearAndDisappear(LOADER, 2, 5);
  }

  public void checkoutFromPdp(){
    pdp.verifyPdpElements();
    homePage.clickBasketIcon();
    clickOnViewBasketButton();
    clickOnCheckoutButton();
    waitForElementToAppearAndDisappear(LOADER, 2, 5);
  }

  public void checkoutFirstOrderDiscount() {
    verifyFirstOrderDiscount();
  }

  public void checkoutDiscountForFiveItems(int qty) {
    navigateToPlp();
    if (UrlBuilder.getLocale().equals("velopl")){
      plp.selectFirstAvailableProductPl();
    }else {
      plp.selectFirstAvailableProduct();
    }
    homePage.clickBasketIcon();
    clickOnViewBasketButton();
    increaseBasketQty(qty);
    verifyDiscountForFiveItems();
  }

  public void checkoutDiscountForOneItems(int qty) {
    navigateToPlp();
    plp.selectFirstAvailableProduct();
    homePage.clickBasketIcon();
    clickOnViewBasketButton();
    increaseBasketQty(qty);

  }


  public void applyCouponCode(String couponCode) {
    basketPage.addVusePromoCode(couponCode);
  }


  public void clickOnViewBasketButton() {
    if (UrlBuilder.getLocale().contains("velopl")) {
      clickUsingJS(PL_VIEW_BASKET_BUTTON);
      waitForElementToAppearAndDisappear(LOADER, 2, 2);
    } else {
      clickUsingJS(AVALANCHE_VIEW_BASKET_BUTTON);
      waitForElementToAppearAndDisappear(LOADER, 2, 2);
    }
  }

  public void clickOnCheckoutButton() {
      waitForElementToAppearAndDisappear(LOADER,5,5);
    clickUsingJS(AVALANCHE_CHECKOUT_BUTTON);
   // if(waitForExpectedElement(AVALANCHE_VELOZA_UPDATEID_BUTTON).isDisplayed())
     // clickByElementByQueryJSExecutor(AVALANCHE_VELOZA_UPDATEID_BUTTON);
  }
  public void clickOnLink(String url) {
    switch (url) {
      case "products":
        navigateToPlp();
        break;
      case "privacy":
        waitForAjaxElementNotToBePresent(webDriver,3);
        clickByLinkTextIgnoreCase(UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("privacyPolicyLinkText")));
        break;
      case "contact":
        clickByLinkTextIgnoreCase(UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("contactUsLinkText")));
        break;
      default:
        assertThat(true).as("ERROR clickOnLink: invalid url supplied - "  + url).isFalse();
    }
  }

    public void clickByLinkTextIgnoreCase(String linkText) {
            waitForAjaxElementNotToBePresent(webDriver, 5);
            List<WebElement> allURLs = webDriver.findElements(By.tagName("a"));
            for (WebElement url : allURLs) {
                if (url.getText().equalsIgnoreCase(linkText)) {
                    clickUsingJS(url);
                    break;
                }
            }
    }

  public void IamOnPage(String url) {
      waitForAjaxElementNotToBePresent(webDriver, 5);
    switch (url) {
      case "products":
        List<WebElement> products = waitForExpectedElements(homePage.AVALANCHE_PLP_PRODUCT);
        assertThat(products.size() > 0)
                .as("ERROR IamOnPage: expected to be on the PLP but couldn't find any products").isTrue();
        break;
      case "privacy":
        String expectedPrivacyHeadingText = UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("privacyHeading"));
        String actualPrivacyHeadingText;
        if (UrlBuilder.getLocale().equals("veloza")) {
          actualPrivacyHeadingText = waitForExpectedElement(homePage.VELOZA_PRIVACY_HEADING).getText();
        }
        else if (UrlBuilder.getLocale().equals("velopl")){
            actualPrivacyHeadingText = waitForExpectedElement(homePage.AVALANCHE_PRIVACY_HEADING_PL).getText();
          }
      else{
       actualPrivacyHeadingText = waitForExpectedElement(homePage.AVALANCHE_PRIVACY_HEADING).getText();
      }
        assertThat(expectedPrivacyHeadingText.equalsIgnoreCase(actualPrivacyHeadingText))
                .as("ERROR IamOnPage: expected privacy page heading was not found. Instead I got "+expectedPrivacyHeadingText).isTrue();
     break;
      case "contact":
        String expectedContactheading = UrlBuilder.getMessage(avalancheAccountDashboardPage.translate("contactHeading"));
        String actualContactheading = webDriver.getTitle();
        assertThat(expectedContactheading.equalsIgnoreCase(actualContactheading))
                .as("ERROR IamOnPage: expected to be on contact us page with heading " + expectedContactheading + " but I got " + actualContactheading).isTrue();
        break;
      default:
        assertThat(true).as("ERROR clickOnLink: invalid url supplied - "  + url).isFalse();
    }
  }


  public void goToAddNewAddressPageAndTakeEyesScreenShot() {
    if (!Props.EYES_ON) {
      return;
    }
    final String pageName = "Add New Address Page";
    switch (UrlBuilder.getLocale().toLowerCase()) {
      case "pl":
      case "kz":
        waitForElementToAppearAndDisappear(LOADER, 15, 30);
        waitForExpectedElement(CHANGE_ADDRESS_GLO_PL).click();
        clickAddNewAddressLink();
        waitForLoaderToDisapear();
        eyesCheckVisiblePage(pageName);
        clickUsingJS(NEW_ADDRESS_CLOSE_BUTTON);
        break;
      case "lyftse":
        clickUsingJS(ADD_NEW_ADDRESS_BUTTON);
        waitForElementToAppearAndDisappear(LOADER, 5, 10);
        eyesCheckVisiblePage(pageName);
        clickIndexElementByQueryJSExecutor(CLOSE_POPUP_LYFT_SE, 2);
        break;
      case "vusefr":
      case "vuseuk":
        waitForLoaderToDisapear();
        clickChangeAddressLink();
        clickAddNewAddressLink();
        waitForLoaderToDisapear();
        waitForExpectedElement(ENTER_ADDRESS_MANUALLY).click();
        eyesCheckVisiblePage(pageName);
        clickUsingJS(NEW_ADDRESS_CLOSE_BUTTON);
        break;
      case "it":
        waitForLoaderToDisapear();
        clickUsingJS(ADD_NEW_ADDRESS_BUTTON);
        waitForElementToAppearAndDisappear(LOADER, 5, 10);
        eyesCheckVisiblePage(pageName);
        clickIndexElementByQueryJSExecutor(CLOSE_POPUP_LYFT_SE, 2);
        break;
    }
  }

  public void userClickOnAgeVeriyButton() {
    waitForExpectedElement(AGE_VERIFICATION_BLOCK_IT, 5);
    waitForExpectedElement(VERIFY_AGE_BUTTON).click();
  }

  public boolean isPhotoSizeInfoPresent() {
  return waitForExpectedElement(PHOTO_SIZE_INFO).isDisplayed();
  }

  public void selectLastMileDelivery() {
    clickByElementByQueryJSExecutor(LAST_MILE_DELIVERY);
  }

  public void populateRestrictedAddressFields(String strInputText) {
      if (getWebDriver().findElements(ADDRESS_MANUAL_ENTRY_ACTIVE).size() == 0)
        waitAndClickByElementByJSExecutor(ENTER_ADDRESS_MANUALLY, 10);
      if (strInputText.contains("Street")) {
        waitClearAndEnterText(STREET, UrlBuilder.getMessage(strInputText));
        waitClearAndEnterText(POSTCODE, UrlBuilder.getMessage("postalCode.key"));
      } else {
        waitClearAndEnterText(STREET, UrlBuilder.getMessage("streetAddress.key"));
        waitClearAndEnterText(POSTCODE, UrlBuilder.getMessage(strInputText));
      }
      waitClearAndEnterText(PHONE_NUMBER_FIELD,UrlBuilder.getMessage("validPhoneNumber.key"));
      clickByElementByQueryJSExecutor(USE_THIS_ADDRESS_BUTTON);
      waitForAjaxElementNotToBePresent(getWebDriver(), 10);
  }

  public void goToTermsAndConditionPageAndTakeEyesScreenshot() {
    if (!Props.EYES_ON) {
      return;
    }
    final String pageName = "Terms And Conditions Overlay Page";
    switch (UrlBuilder.getLocale().toLowerCase()) {
      case "vuseuk":
        clickIndexElementByQueryJSExecutor(TERMS_CONDITIONS_LINK, 0);
        scrollToShowEntirePage();
        eyes.check(pageName, Target.window().fully());
        waitAndClickByElementByJSExecutor(CLOSE_TERMS_CONDITIONS_BUTTON, 3);
        break;
      case "vusefr":
        waitAndClickByElementByJSExecutor(TERMS_AND_CONDITIONS_LINK_VUSE_FR, 3);
        scrollToShowEntirePage();
        eyes.check(pageName, Target.window().fully());
        waitAndClickByElementByJSExecutor(CLOSE_TERMS_CONDITIONS_BUTTON, 3);
        break;
      case "lyftse":
        waitAndClickByElementByJSExecutor(TERMS_AND_CONDITIONS_LINK_LYFT_SE, 3);
        scrollToShowEntirePage();
        eyes.check(pageName, Target.window().fully());
        waitAndClickByElementByJSExecutor(CLOSE_TERMS_CONDITIONS_BUTTON_LYFT_SE, 3);
        break;
    }
  }

  public void waitForPageLoad(){
      try {
        waitForExpectedElement(ARIA_BUSY_FALSE, 20);
      } catch (Exception e) {
        waitForLoaderToDisapear();
      }
    }

  public void goToCheckoutCouponCodeValidationsPageAndTakeEyesScreenshot() {
    if (!Props.EYES_ON) {
      return;
    }
    final String pageName = "Checkout Coupon Code Validations Page";
    switch (UrlBuilder.getLocale().toLowerCase()) {
      case "lyftse":
        enterDataAndWait(applyDiscountText, UrlBuilder.getMessage("invalidCoupon.key"));
        waitAndClickByElementByJSExecutor(APPLY_DISCOUNT_BUTTON_UK,3);
        eyesCheckVisiblePage(pageName);
        waitClearAndEnterText(applyDiscountText,"");
        break;
    }
  }

  public void assertTotalChargesForTheProductAfterRemovedDiscountInShoppingCart() {
    waitForExpectedElement(DISCOUNT_COUPON_ERROR,10);
    if(getWebDriver().findElements(DISCOUNT_COUPON_ERROR).size()==0){
        TOTAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(TOTAL_CHARGES_CART_GLODE).getText().replaceAll("[\\D]", ""));
        FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_VYPEFR).getText().replaceAll("[\\D]", ""));
        assertTrue(FINAL_CHARGES_CART.floatValue()==TOTAL_CHARGES_CART.floatValue());
    }
  }

  public void assertTotalChargesForTheProductAfterDiscountInCheckoutPage() {
    Float subTotal = Float.valueOf(waitForExpectedElement(SUBTOTAL_CHARGES_VUSEIT).getText().replaceAll("[\\D]", ""));
    DISCOUNT_CHARGES_CART = Float.valueOf(waitForExpectedElement(DISCOUNT_CHARGES_CART_VYPEFR).getText().replaceAll("[\\D]", ""));
    SHIPPING_CHARGES_CHECKOUT = Float.valueOf(waitForExpectedElement(SHIPPING_CHARGES_MINIBASKET).getText().replaceAll("[\\D]", ""));
    FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_VYPEFR).getText().replaceAll("[\\D]", ""));
    assertTrue(FINAL_CHARGES_CART == subTotal - DISCOUNT_CHARGES_CART + SHIPPING_CHARGES_CHECKOUT);
  }

  public void assertTotalChargesForTheProductAfterRemovedDiscountInCheckoutPage() {
    Float subTotal = Float.valueOf(waitForExpectedElement(SUBTOTAL_CHARGES_VUSEIT).getText().replaceAll("[\\D]", ""));
    SHIPPING_CHARGES_CHECKOUT = Float.valueOf(waitForExpectedElement(SHIPPING_CHARGES_MINIBASKET).getText().replaceAll("[\\D]", ""));
    FINAL_CHARGES_CART = Float.valueOf(waitForExpectedElement(FINAL_CHARGES_CART_VYPEFR).getText().replaceAll("[\\D]", ""));
    assertTrue(FINAL_CHARGES_CART == subTotal + SHIPPING_CHARGES_CHECKOUT);
  }

  public void verifyPriceOfProductAccordinglyInSummary() {
    PRODUCT_PRICE = Float.valueOf(waitForExpectedElement(PRODUCT_PRICE_BASKET).getText().split(" ")[0].replace(",",""));
    PRODUCT_QUANTITY = Integer.valueOf(waitForExpectedElement(PRODUCT_QUANTITY_BASKET).getAttribute("value"));
    TOTAL_SUMMARY_CART_AMOUNT = Float.valueOf(waitForExpectedElement(TOTAL_SUMMARY_CARY_AMOUNT_BASKET).getText().split(" ")[0].replace(",",""));
    float VALUE_OF_PRODUCT = PRODUCT_PRICE * PRODUCT_QUANTITY;
    assertEquals(TOTAL_SUMMARY_CART_AMOUNT,VALUE_OF_PRODUCT);

  }

}