package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.cucumber.CardAndAddressDetails;
import com.salmon.test.page_objects.gui.constants.Locale;
import org.openqa.selenium.*;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class GlobalSubscriptionsPage extends PageObject{
    private PaymentPage paymentPage;
    private PDP pdp;
    private BasketPage basketPage;
    private HomePage homePage;
    private AccountDashboardPage accountDashboardPage;
    private LoqateAddressLookUpPage loqateAddressLookUpPage;
    private static String currentTier;
    public GlobalSubscriptionsPage(PaymentPage paymentPage, PDP pdp, HomePage homePage, BasketPage basketPage,AccountDashboardPage accountDashboardPage,LoqateAddressLookUpPage loqateAddressLookUpPage) {
        this.paymentPage=paymentPage;
        this.pdp=pdp;
        this.basketPage=basketPage;
        this.homePage=homePage;
        this.accountDashboardPage=accountDashboardPage;
        this.loqateAddressLookUpPage=loqateAddressLookUpPage;
    }


    //GlobalSubs Native version Account
    private static final By ORDER_NUMBER = By.cssSelector(".subscription-tier-order-no");
    private static final By ORDER_NUMBERSE = By.cssSelector(".subscription-tier-order-no");
    private static final By ORDER_NUMBERVUSEUK = By.xpath("//*[@id=\"maincontent\"]/div[1]/div[1]/div[9]/div[1]/span[2]/span[3]");
    private static final By ORDER_NUMBERVUSEFR = By.cssSelector("#maincontent > div.columns > div.column.main > div.subs-block.Active.show > div.grouped-subscription-tier > span.subscription-tier-info > span.subscription-tier-order-no");
    private static final By ORDER_NUMBERVUSEDE = By.xpath("/html//main[@id='maincontent']/div[@class='columns']/div[@class='column main']/div[9]/div[@class='grouped-subscription-tier']/span[@class='subscription-tier-info']/span[@class='subscription-tier-order-no']");
    private static final By SUBS_DATE_PICKERLYFTSE =By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[4]/div[3]/div[2]/div[1]/div[1]/div/span[1]/span[4]/span");
    private static final By SUBS_CANCELLED_TAB =By.cssSelector(".filterCancelled.toggle-subs");
    private static final By ACCEPT_CTA_GLOBAL_SUBS =  By.cssSelector("button.action.primary.subscription-modal");
    private static final By ACCEPT_CTA_GLOBAL_SUBS_LYFTSE =  By.cssSelector(".action-accept.action-primary");
    private static final By ACCEPT_CTA_GLOBAL_SUBS_VUSEDE = By.cssSelector(".action.primary.subscription-modal");
    private static final By EDIT_CTA_GLOBAL_SUBS_VUSEUK = By.cssSelector(".action.edit-subs-trigger.primary > span");
    private static final By ADD_NEW_FLAVOUR_CTA_GLOBAL_SUBS_VUSEUK = By.cssSelector(".add-new-product-button > .action.add-new-products-trigger.primary");
    private static final By PAUSE_CTA_GLOBAL_SUBS_VUSEUK = By.cssSelector("[data-action='pause']");
    private static final By SKIP_CTA_GLOBAL_SUBS_VUSEUK = By.cssSelector("[data-action='skip']");
    private static final By STOP_CTA_GLOBAL_SUBS_CONFIRM = By.cssSelector(".action.primary.stop");
    private static final By STOP_CTA_GLOBAL_SUBS_CONFIRM_VUSEFR = By.cssSelector("button.action.primary.stop");
    private static final By STOP_CTA_GLOBAL_SUBS_CONFIRM_SE = By.cssSelector("button.action-primary.action-accept");
    private static final By STOP_CTA_GLOBAL_SUBS_CONFIRM_LYFTSE = By.cssSelector(".modal-content ~ .modal-footer button.action.primary.stop");
    private static final By STOP_CTA_GLOBAL_SUBS_CONFIRM_REASON_DROPDOWN = By.cssSelector("div#trigger_drop > .text");
    private static final By SUBS_CANCELLATION_SURVEY_FIRST_PAGE_UNSUBSCRIBE_CTA = By.cssSelector("button#go-to-step-two-cancel-wizard");
    private static final By SUBS_CANCELLATION_SURVEY_SECOND_PAGE_UNSUBSCRIBE_CTA = By.cssSelector("button#cancel-subscription-btn");
    private static final By SUBS_CANCELLATION_SURVEY_CONFIRM_CANCELLATION_FEE = By.cssSelector(".action-accept.action-primary");
    private static final By SUBS_CANCELLATION_SURVEY_END_PAGE_RETURN_CTA = By.cssSelector(".action.back_to_subs_page.button.primary");
    private static final By CONFIRM_RESTART_CTA_GLOBAL_SUBS = By.cssSelector(".action-accept.action-primary");
    private static final By CONFIRM_RESTART_CTA_GLOBAL_SUBS_VYPEIT = By.cssSelector(".action-accept.action-primary > span");
    private static final By SUCCESS_MESSAGE_GLOBAL_SUBS = By.cssSelector("div.grouping-message.message.success");
    private static final By SUCCESS_MESSAGE_GLOBAL_SUBS_VUSEUK = By.cssSelector("[data-ui-id='message-success']");
    private static final By EDIT_ADDRESS_GLOBAL_SUBS_NATIVE = By.cssSelector(".grouped-subscriptions-wrapper:nth-child(7) td:nth-of-type(1) span");
    private static final By EDIT_ADDRESS_GLOBAL_SUBS_NATIVE_SE = By.cssSelector("div:nth-of-type(3) > .subscription-tier-body > .payment-shipping-info > .shiiping-address > p:nth-of-type(2) > a[title='Ändra leveransadress'] > span");
    private static final By ADDRESS_GLO_SUBS_NATIVE = By.cssSelector(".my-subscriptions-page .grouped-subscriptions-wrapper:nth-of-type(3) address");
    private static final By ADDRESS_GLO_SUBS_NATIVE_FR = By.cssSelector("div.subs-block.Active span > span > address[id*='address-container']");
    private static final By ADDRESS_GLO_SUBS_NATIVE_TELEPHONE = By.cssSelector("input#telephone");
    private static final By ADDRESS_GLO_SUBS_NATIVE_TELEPHONE_VUSEFR = By.cssSelector("input.phoneFR");
    private static final By ADDRESS_GLO_SUBS_NATIVE_ADDRESS = By.cssSelector(".field.required.street > .control > input[name='street[]']");
    private static final By ADDRESS_GLO_SUBS_NATIVE_ADDRESS_VUSEDE = By.cssSelector("input[name='street[1]']");
    private static final By ADDRESS_GLO_SUBS_NATIVE_ADDRESS_NO_VUSEDE = By.cssSelector(".field.required.street > .control > input[name='street[]']");
    private static final By ADDRESS_GLO_SUBS_NATIVE_CITY = By.cssSelector("input#city");
    private static final By ADDRESS_GLO_SUBS_NATIVE_CITY_VUSECO = By.cssSelector("input#city_input_field");
    private static final By ADDRESS_GLO_SUBS_REGION_DROPDOWN_VUSECO = By.cssSelector("select#region_id");
    private static final By ADDRESS_GLO_SUBS_CITY_DROPDOWN_VUSECO = By.cssSelector("select#city");
    private static final By ADDRESS_GLO_SUBS_NATIVE_ZIP = By.cssSelector("input#zip");
    private static final By ADDRESS_GLO_SUBS_NATIVE_CONFIRMATION = By.cssSelector(".address-change-btn-modal.continue-btn");
    private static final By GLO_SUBS_CHANGE_PAYMENT = By.cssSelector("div:nth-of-type(6) > .data.subscription-section.table button[title='Modifier le method de paiement']");
    private static final By GLO_SUBS_TOP_CARD = By.cssSelector("div:nth-of-type(1) > .card-number-type.field > input[name='gateway']");
    private static final By GLO_SUBS_APPLY_TO_ALL_CARDS = By.cssSelector("select#is-applied");
    private static final By GLO_SUBS_CONFIRM_CARD = By.cssSelector(".continue-btn.payment-change-btn-modal");
    private static final By GLO_SUBS_CARD_USED = By.cssSelector("div:nth-of-type(6) > .data.subscription-section.table .cc-masked-update");
    private static final By GLO_SUBS_CANCEL = By.xpath("//*[@id=\"maincontent\"]/div[1]/div[1]/div[8]/div[2]/div[3]/span[2]/a");
    private static final By GLO_CANCEL_SURVEY = By.cssSelector("#js-cancellation-survey");
    private static final By GLO_CANCEL_SURVEY_DROPDOWN = By.cssSelector("#trigger_drop");
    private static final By GLO_CANCEL_SURVEY_TEXTAREA = By.cssSelector("#js-cancellation-survey > div:nth-child(1) > textarea");
    private static final By REMOVE_SUBSCRIPTION_LYFTSE = By.cssSelector("p.delete-item");
    private static final By GLO_SUBS_CURRENT_TIER = By.xpath("//*[@id=\"maincontent\"]/div[1]/div[1]/div[9]/div[1]/span[1]/span/span[2]");
    private static final By GLO_SUBS_CURRENT_TIER_LYFTSE = By.xpath("/html//main[@id='maincontent']/div[@class='columns']//div[@class='my-subscriptions-page']/div[3]/div[1]//span[@class='text']");
    private static final By GLO_SUBS_CURRENT_TIER_VUSEFR = By.cssSelector("#maincontent > div.columns > div.column.main > div.subs-block.Active.show > div.grouped-subscription-tier > span.subscription-tier-label.bronze > span > span.text");
    private static final By VUSE_SUBS_CANCEL_BUTTON=By.cssSelector("button.action.cancel-wizard-button");
    private static final By MY_SUBS_LEARN_MORE_BUTTON = By.cssSelector(".blank-subs p:nth-child(3) span");
    private static final By SUBS_CANCEL_REASON_CHANGE_ADDRESS =By.cssSelector("input#cancel_reason_2");
    private static final By SUBS_UPDATE_ADDRESS_BUTTON= By.cssSelector("button.subscription-update-action.action.secondary.change_address");

    private static final By GLO_SUBS_CURRENT_CARD_VUSEDE = By.cssSelector(".cc-ending.cc-masked-update");
    private static final By GLO_SUBS_CHANGE_CARD_VUSEDE = By.cssSelector("span.payment-section > button");
    private static final By GLO_SUBS_CARDS_SELECTOR = By.cssSelector(".card-number-type.field > input[name='gateway']");
    private static final By GLO_SUBS_CARDS_SELECTOR_NUMBER = By.cssSelector("div > label > span:nth-child(4)");
    private static final By GLO_SUBS_CARD_CHANGE = By.cssSelector("#maincontent > div.columns > div.column.main > div:nth-child(7) > table.data.table.subscription-section > tbody > tr > td:nth-child(2) > p:nth-child(2) > button > span");
    private static final By GLO_SUBS_CURRENT_CARD = By.cssSelector("#maincontent > div.columns > div.column.main > div:nth-child(7) > table.data.table.subscription-section > tbody > tr > td:nth-child(2) > div > span.cc-masked-update");
    private static final By GLO_SUBS_CARDS_SELECTORSE = By.cssSelector(".card-number-type.field > .label ");
    private static final By GLO_SUBS_CARD_CHANGESE = By.cssSelector("div.my-subscriptions-page > div:nth-child(3) > div.subscription-tier-body > div.payment-shipping-info > div.payment-info > p:nth-child(3) > a > span");
    private static final By GLO_SUBS_CURRENT_CARDSE = By.cssSelector("#maincontent > div.columns > div > div.my-subscriptions-page > div:nth-child(3) > div.subscription-tier-body > div.payment-shipping-info > div.payment-info > div > p > span.cc-masked-update");
    private static final By ORDER_NUMBERVYPEIT = By.cssSelector(".subscription-tier-order-no");
    private static final By ACCEPT_CTA_GLOBAL_SUBS_VYPEIT = By.cssSelector("button.action.primary.subscription-modal");
    private static final By STOP_CTA_GLOBAL_SUBS_CONFIRMFEE_VYPEIT = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.cancel-survey-fee._show > div.modal-inner-wrap > footer > button.action-primary.action-accept > span");
    private static final By ADDRESS_GLO_SUBS_NATIVE_TELEPHONEVYPEIT = By.cssSelector(".telephone #telephone");
    private static final By GLO_SUBS_CONFIRM_CARDVYPEIT = By.cssSelector(".action.continue-btn.payment-change-btn-modal.primary");
    private static final By GLO_SUBS_CARDS_SELECTORVYPEIT = By.cssSelector(".card-number-type.field > .label ");
    private static final By GLO_SUBS_CARDS_SELECTORVUSECO= By.cssSelector(".card-number-type.field > .label > span:nth-of-type(3)");
    private static final By GLO_SUBS_UPDATE_CTA = By.cssSelector("form[method='post'] > .action.adjust-subscription-btn.primary");
    private static final By GLO_SUBS_UPDATE_CTA_LYFTSE = By.cssSelector("div:nth-of-type(3) > .subscription-tier-body > .update-flavour > form[method='post'] > .adjust-subscription-btn.show");
    private static final By GLO_SUBS_CURRENT_QTY_LYFTSE = By.cssSelector("div:nth-of-type(3) > .subscription-tier-body > .order-details > .data.subscription-section.table .item-qty > p:nth-of-type(2)");
    private static final By GLO_SUBS_EPOD_CATEGORY_VUSECO = By.cssSelector("li:nth-of-type(2) > .category-button");
    private static final By GLO_SUBS_INCREASE_CTA = By.cssSelector("input[value='+']");
    private static final By GLO_SUBS_INCREASE_CTA_VUSEUK = By.cssSelector(".icon-plus.plus.qty-modifier");
    private static final By GLO_SUBS_INCREASE_BOTTOM_CTA_VUSEUK = By.cssSelector(".details-subscription-item.new .icon-plus.plus.qty-modifier");
    private static final By GLO_SUBS_INCREASE_CTA_BOTTOM = By.cssSelector(".padding-section.product-summary > div:nth-of-type(2) input[value='+']");
    private static final By GLO_SUBS_DECREASE_CTA = By.cssSelector("input[value='-']");
    private static final By GLO_SUBS_DECREASE_CTA_VUSEUK = By.cssSelector(".icon-minus.minus.qty-modifier");
    private static final By GLO_SUBS_CONFIRM_UPDATE_CTA = By.cssSelector(".update-subscription");
    private static final By GLO_SUBS_APPLY_CHANGES_CTA = By.cssSelector(".action.apply-modify.primary");
    private static final By GLO_SUBS_CONFIRM_UPDATE_FEE_CTA = By.cssSelector(".action.primary.update-subscription");
    private static final By GLO_SUBS_CONFIRM_UPDATE_FEE_CTA_VUSEUK = By.cssSelector("aside:nth-of-type(4) .action.primary > span");
    private static final By GLO_SUBS_CONFIRM_UPDATE_FEE_CTA_LYFTSE = By.cssSelector(".update-subscription > span");
    private static final By GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA = By.cssSelector(".close-notification");
    private static final By GLO_SUBS_SELECT_PRODUCT_STRENGTH = By.cssSelector(".swatch-opt-4260 > .swatch-attribute.vuse_strength > div[role='listbox'] > div:nth-of-type(1)");
    private static final By GLO_SUBS_SELECT_PRODUCT_STRENGTH_UK = By.cssSelector(".swatch-opt-4001 > .swatch-attribute.vuse_strength > div[role='listbox'] > div:nth-of-type(3)");
    private static final By GLO_SUBS_SELECT_PRODUCT_STRENGTH_VUSEIT = By.cssSelector(".swatch-opt-4438 > .swatch-attribute.vuse_strength > div[role='listbox'] > div[role='option']");
    private static final By GLO_SUBS_SELECT_PRODUCT_ADD = By.cssSelector(".item.product.product-item.product-item-4260 > .product-item-info  .action.add-to-subscription.primary");
    private static final By GLO_SUBS_SELECT_PRODUCT_ADD_UK = By.cssSelector(".item.product.product-item.product-item-4001 button > span");
    private static final By GLO_SUBS_SELECT_PRODUCT_ADD_VUSEIT  = By.cssSelector("li:nth-of-type(2) > .product-card.product-item-info .action.add-to-subscription");
    private static final By GLO_SUBS_EPODS_VUSEFR = By.cssSelector("li:nth-of-type(2) > .category-button");
    private static final By GLO_SUBS_SELECT_PRODUCT_ADD_LYFTSE = By.cssSelector("li:nth-child(3) > button");
    private static final By GLO_SUBS_DELETE_PRODUCT = By.cssSelector("div:nth-of-type(1) > .delete-item");
    private static final By GLO_SUBS_DELETE_PRODUCT_VUSEUK = By.cssSelector(".subs_edit_content > div:nth-of-type(2)  .remove-item");
    private static final By GLO_SUBS_CONFIRM_DELETE_PRODUCT_VUSEUK = By.cssSelector("aside:nth-of-type(4) .action.primary > span");
    private static final By GLO_SUBS_CLOSE_CTA = By.cssSelector("a.cancel-subscription");
    private static final By CANCEL_SUBS_ARROW_ICON = By.cssSelector(".arrow");
    private static final By CURRENT_ORDER_TIER_VUSEITCO = By.xpath("/html//main[@id='maincontent']/div[@class='columns']/div[@class='column main']//span[@class='gradient']/span[@class='text']");
    private static final By CURRENT_ORDER_QTY_VUSEITCO = By.xpath("/html//main[@id='maincontent']/div[@class='columns']/div[@class='column main']//span[@class='product-info']");
    private static final By CURRENT_CARDNO_VUSECO = By.cssSelector(".cc-masked-update");
    private static final By CLOSE_MODAL_BUTTON = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.confirm-subscription-update.modal-slide._inner-scroll._show > div.modal-inner-wrap > footer > button.close-notification > span");

    //VuseUKSub
    public By oneTimePurchase = By.cssSelector("div.block-content > div > button.action.basket-exclusivity-btn.secondary.onetime.btn-small");
    public By subscribeAll = By.cssSelector("div.block-content > div > button.action.basket-exclusivity-btn.subscription.secondary.btn-small");
    public By confirmchange = By.cssSelector("button.action-primary.action-accept");
    public By allonetimepurchase = By.cssSelector("button.action.basket-exclusivity-btn.secondary.onetime.btn-small");
    public By subscriptionfirst = By.cssSelector("button.action.secondary.basket-exclusivity-btn.first-subscription.btn-small");
    public By onetimepurchase = By.cssSelector("div.fieldset > div.field.choice.active");
    public By subscriptiontotal = By.cssSelector("tr.subscription.subscription-total");
    public By selectsubscription = By.cssSelector("div.field.subscription.choice");

    //SubscribePro payment locators
    private final static By LOADING_CIRCLE = By.cssSelector("img[alt='Laddar...']");
    private final static By LOADING_CIRCLE_VUSE_DE = By.cssSelector("img[alt='Wird geladen …']");
    private final static By LOADING_CIRCLE_VUSE_IT = By.cssSelector("img[alt='Caricamento...']");
    private final static By LOADING_CIRCLE_VUSE_FR = By.cssSelector("img[alt='Chargement en cours...']");
    private final static By LOADING_CIRCLE_VUSE_CO = By.cssSelector("img[alt='Cargando...']");
    private final static By SUB_MESSAGE_VUSE_DE = By.cssSelector(".invalid-qty__container");
    public final static By DECREASE_QUANTITY_BASKET_PAGE_VUSEIT = By.cssSelector("div[id*='cart-item-qty-btn-'] span");
    private final static By QUANTITY_SELECTOR_BASKET_PAGE_VUSEIT = By.cssSelector("div[id*='cart-item-qty-btn-']");
    public final static By QUANTITY_BASKET_PAGE_INPUT_VUSEIT = By.cssSelector("input.qty.cart-qty");
    private final static By I_ICON = By.cssSelector(".learn-more.subscribepro-learn-more-2392");
    public final static By GLOBAL_SUB_PAYMENT_OPTION =By.cssSelector(".payment-method.payment-method-subscribe_pro > .choice.field.payment-method-title > .label");
    public final static By GLOBAL_SUBS_CARD_NO_INPUT =By.cssSelector("input#card_number");
    public final static By GLOBAL_SUBS_EXPIRATION_MONTH =By.cssSelector("input#subscribe_pro_expiration");
    public final static By GLOBAL_SUBS_EXPIRATION_YEAR =By.cssSelector("input#subscribe_pro_expiration_yr");
    public final static By GLOBAL_SUBS_CV_VFIELD =By.cssSelector("input#cvv");
    public final static By GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOX =By.cssSelector("input#agreement_subscribe_pro_5");
    public final static By GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXSE =By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[16]']");
    public final static By GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVUSEDE =By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[19]']");
    public final static By GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVYPEIT =By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[21]']");
    public final static By TERMS_AND_CONDITIONS_CHECKBOXVUSEUK = By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[1]']");
    public final static By GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVUSEUK = By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[15]']");
    public final static By GLOBAL_SUBS_3DS_PASSWORD = By.cssSelector("input#otp");
    public final static By GLOBAL_SUBS_3DS_CONFIRM = By.cssSelector("button#button");
    public final static By GLOBAL_SUBS_3DS_FRAME = By.xpath("//iframe[contains(@id, 'challenge-iframe')]");
    public final static By TERMS_AND_CONDITIONS_CHECKBOXSE =By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[9]']");
    public final static By TERMS_AND_CONDITIONS_CHECKBOXVYPEIT =By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[20]']");
    public final static By SUBS_CHECKOUT_CTA_FR =By.cssSelector(".payment-method.payment-method-subscribe_pro button[title='COMMANDER']");
    public final static By SUBS_CHECKOUT_CTA_SE =By.cssSelector(".payment-method.payment-method-subscribe_pro button[title='Beställ']");
    public final static By SUBS_CHECKOUT_CTA_VUSEDE =By.cssSelector(".payment-method.payment-method-subscribe_pro button[title='Kostenpflichtig bestellen']");
    public final static By SUBS_CHECKOUT_CTA_VYPEIT =By.cssSelector(".payment-method.payment-method-subscribe_pro button[title='Invia Il Tuo Ordine']");
    public final static By SUBS_CHECKOUT_CTA_VUSEUK = By.cssSelector(".payment-method.payment-method-subscribe_pro button[title='Place Order']");
    public final static By GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVUSECO =By.cssSelector(".payment-method.payment-method-subscribe_pro .checkout-agreements  input[name='agreement[10]']");
    public final static By SUBS_CHECKOUT_CTA_VUSECO =By.cssSelector(".payment-method.payment-method-subscribe_pro button[title='Realizar pedido']");
    private final static By GLOBAL_SUBS_PAYMENT_LOWER_STRENGTH = By.cssSelector("input#lowerStrength");

    //cart locators
    public final static By GLOBAL_SUBS_OPTION_PDP = By.cssSelector(".fieldset.pdp-subscriptionpro-options > div:nth-of-type(2) > .label");
    public final static By GLOBAL_SUBS_OPTION_PDPSE = By.cssSelector("label[for='option_subscription']");
    public final static By GLOBAL_SUBS_OPTION_PDPVUSE = By.cssSelector(".choice.field.subscription > .option-wrapper > .label > .label-text > span");
    public final static By GLOBAL_SUBS_INTERVAL_DROPDOWN = By.cssSelector("select[name='subscription_option[interval]']");
    public final static By GLOBAL_SUBS_ERROR_MESSAGE = By.cssSelector(".error.message.message-error > div");
    private final static By GLOBAL_SUBS_PDP_ERROR_MESSAGE = By.xpath("//div[@id='product-options-wrapper']//div[@class='messages']/div[@role='alert']/div");
    private final static By GLOBAL_SUBS_PDP_ERROR_MESSAGE_VUSEIT = By.cssSelector("div[role='alert'] > div");
    private final static By GLOBAL_SUBS_PDP_HEADER_ERROR_MESSAGE = By.cssSelector(".error.message.message-error > div");
    public static final By GLOBAL_SUBS_CART_ITEM_BANNER = By.cssSelector(".col.product-item-details  .subscriptionpro-label");
    public static final By GLOBAL_SUBS_CART_ITEM_BANNERSE = By.cssSelector(".attribute-value");
    public static final By GLOBAL_SUBS_CART_INTEVAL_DROPDOWN = By.cssSelector(".subscribepro-intervals");
    public static final By GLOBAL_SUBS_IMMEDIATE_PURCHASE_OPTION = By.cssSelector("[data-bind='attr\\: \\{for\\:quoteItemId\\+\\'_\\'\\+oneTimePurchaseOption\\}']");
    public static final By GLOBAL_SUBS_SUBSCRIBE_AND_SAVE_OPTION = By.cssSelector(".choice.field.subscription > .option-wrapper > .label > .subscribe-save");
    public static final By GLOBAL_SUBS_ADD_ACCESSORY = By.cssSelector(".action.choose-device.primary");
    public static final By GLOBAL_SUBS_ADD_ACCESSORY_COLOUR = By.cssSelector("[index='2']");
    public static final By GLOBAL_SUBS_ADD_ACCESSORY_ADD_ITEM = By.cssSelector("#free-device-caraousel [data-slick-index='0'] .free-device-modal span");
    public static final By CART_PAGE_TOTALS = By.cssSelector("#cart-totals");
    public static final By CART_BTN_INCREASE_ITEM_QTY = By.xpath("/html//table[@id='shopping-cart-table']/tbody[@class='cart item']//td[@class='col product-item-details']//div[@class='control qty']/input[3]");
    public static final By CART_BTN_DECREASE_ITEM_QTY = By.xpath("/html//table[@id='shopping-cart-table']/tbody[@class='cart item']//td[@class='col product-item-details']//div[@class='control qty']/input[1]");
    public static final By CART_ITEM_DELETE= By.cssSelector(".action.button-flavoured.primary.remove");
    public static final By CART_ITEM_DELETE_CONFIRM= By.cssSelector(".modal-content a[title='Remove'] > span");
    public static final By QUANTITY_DROPDOWN = By.cssSelector("div.control.qty");
    public static final By QUANTITY_DROPDOWN_VALUE = By.cssSelector("div.qty-btn[id*='cart-item-qty-btn']");
    public static final By CURRENTLY_DISPLAYED_DROPDOWN_VALUE = By.cssSelector("div.qty-btn[id*='cart-item-qty-btn']");
    public static final By ALL_DROPDOWN_VALUES = By.cssSelector("ul > li");
    private static final By GLO_SUBS_ADD_CARD = By.cssSelector(".action.add-card-btn.payment-change-btn-modal.secondary > span");
    private final static By GLOBAL_SUBS_OPTION_PDP_FR = By.cssSelector("div.field.choice.subscription > div > label");
    private final static By GLOBAL_SUBS_PROMOCODE_ERROR = By.cssSelector(".error.message.message-error > div");
    public static final By SUBSCRIPTION_ACCOUNT_PAGE = By.cssSelector("div.my-subscriptions-page");

    //Billing Address Card details
    private static final By GLO_SUBS_CARD_FIRST_NAME_INPUT = By.cssSelector("input#first_name");
    private static final By GLO_SUBS_CARD_LAST_NAME_INPUT = By.cssSelector("input#last_name");
    private static final By GLO_SUBS_CARD_TELEPHONE_INPUT = By.cssSelector(".telephone [data-validate]");
    private static final By GLO_SUBS_CARD_ADDRESS1_INPUT = By.cssSelector("input[name='billing_address[street1]']");
    private static final By GLO_SUBS_CARD_CITY_INPUT = By.cssSelector("input#city");
    private static final By GLO_SUBS_CARD_POSTCODE_INPUT = By.cssSelector("input#postcode");
    private static final By GLO_SUBS_CARDNUMBER_INPUT = By.cssSelector("input#card_number");
    private static final By GLO_SUBS_CARD_MONTH_INPUT = By.cssSelector("input#subscribe_pro_expiration");
    private static final By GLO_SUBS_CARD_YEAR_INPUT = By.cssSelector("input#subscribe_pro_expiration_yr");
    private static final By GLO_SUBS_CARD_CVV_INPUT = By.cssSelector("input#cvv");
    private static final By GLO_SUBS_CARD_CONFIRM_CTA = By.cssSelector("button[title='Save Card']");
    private static final By GLO_SUBS_CARD_CONFIRM_CTA_VUSECO = By.cssSelector("button[title='Guardar tarjeta'] > span");
    private static final By GLO_SUBS_CARD_CONFIRM_CTA_VUSEFR = By.cssSelector("button[title='Enregistrer la Carte']");
    private static final By GLO_SUBS_CARD_CONFIRM_CTA_VUSEDE = By.cssSelector("button[title='Kreditkarte speichern'] > span");
    private static final By GLO_SUBS_CARD_CONFIRM_CTA_LYFTSE = By.cssSelector("button[title='Spara kort']");
    private static final By GLO_SUBS_MY_SUBSCRIPTIONS_CTA = By.cssSelector("div#account-nav > ul > li:nth-of-type(4) > a");
    private static final By GLO_SUBS_MY_SUBSCRIPTIONS_CTA_VUSECO = By.cssSelector("div#account-nav > ul > li:nth-of-type(5) > a");
    private static final By GLO_SUBS_MY_SUBSCRIPTIONS_CTA_VUSEFR = By.cssSelector("div#account-nav > ul > li:nth-of-type(5) > a");


    public void acceptConfirmationAndLoadGloSubs(){
        switch(UrlBuilder.getLocale()){
            case "vypeit":
            case "vuseit":
                try {
                    waitForExpectedElement(ACCEPT_CTA_GLOBAL_SUBS_VUSEDE,3).click();
                }catch(Exception e){
                    waitForExpectedElement(ACCEPT_CTA_GLOBAL_SUBS_VYPEIT).click();
                }
                break;
            case("vusede"):
                try {
                    waitForExpectedElement(ACCEPT_CTA_GLOBAL_SUBS_VUSEDE,3).click();
                }catch(Exception e){
                    waitForExpectedElement(ACCEPT_CTA_GLOBAL_SUBS).click();
                }
                break;
            case("lyftse"):
                try {
                    waitForExpectedElement(ACCEPT_CTA_GLOBAL_SUBS_LYFTSE,3).click();
                }catch(Exception e){
                    waitForExpectedElement(ACCEPT_CTA_GLOBAL_SUBS).click();
                }
            default:
                if(isElementPresent(ACCEPT_CTA_GLOBAL_SUBS)) {
                    waitForExpectedElement(ACCEPT_CTA_GLOBAL_SUBS).click();
                }
        }
        invisibilityOfElementLocated(HomePage.LOADING_CIRCLE,20);
    }

    public void acceptLoadAssertGlobalSubsAccountMessage (String message){
        acceptConfirmationAndLoadGloSubs();
        switch(UrlBuilder.getLocale()){
            case "vusefr":
            case "lyftse":
            case "vusede":
            case "vuseuk":
                break;
            default:
                waitForElementToAppearAndDisappear(LOADER,5,15);
                assertEquals(waitForExpectedElement(SUCCESS_MESSAGE_GLOBAL_SUBS, 10).getText(),message);
        }
    }

    public int nextMonthCalculator(List<String> dates, String orderNumber){
        By datePicker = null;
        datePicker = By.cssSelector(".estimated-date-info.subscription-order-"+orderNumber);
        for(String s:dates){
            String date = waitForExpectedElement(datePicker,5).getText();
            if(date.contains(s)){
                return dates.indexOf(s)+1;
            }
        }
        return 0;
    }

    public int getIndexOfDate(List<String> dates,By datePicker){
        for(String s:dates){
            String date = waitForExpectedElement(datePicker,5).getText();
            if(date.contains(s)){
                return dates.indexOf(s)+1;
            }
        }
        return 0;
    }

    public int getDateFromDatePicker(String orderNumber){
        By datePicker = null;
        switch (UrlBuilder.getLocale()){
            case "fr":
            case "vusefr":
                List<String> dateFR = Arrays.asList(new String[]{"janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"});
                datePicker = By.cssSelector(".estimated-date-info.subscription-order-"+orderNumber);
                return getIndexOfDate(dateFR,datePicker);
            case "lyftse":
                datePicker = SUBS_DATE_PICKERLYFTSE;
                break;
            case "vusede":
                List<String> dateDE = Arrays.asList(new String[]{"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"});
                datePicker = By.cssSelector(".estimated-date-info.subscription-order-"+orderNumber);
                return getIndexOfDate(dateDE,datePicker);
            case "vuseco":
                List<String> dateCO = Arrays.asList(new String[]{"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre", "diciembre"});
                datePicker = By.cssSelector(".estimated-date-info.subscription-order-"+orderNumber);
                return getIndexOfDate(dateCO,datePicker);
            case "vuseuk":
                List<String> monthUK = Arrays.asList(new String[]{"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"});
                datePicker = By.cssSelector(".estimated-date-info.subscription-order-"+orderNumber);
                int indexUK =getIndexOfDate(monthUK,datePicker);
                if(indexUK==0){
                    monthUK = Arrays.asList(new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"});
                    datePicker = By.cssSelector(".estimated-date-info.subscription-order-"+orderNumber);
                    indexUK =getIndexOfDate(monthUK,datePicker);
                }
                return indexUK;
        }
        String str[] = waitForExpectedElement(datePicker).getText().split("-");
        return Integer.parseInt(str[1]);
    }

    public String getOrderNumber(int position, By orderNumbers){
        List<WebElement> orders = webDriver.findElements(orderNumbers);
        List<String> validOrders = new ArrayList<>();
        for(WebElement o : orders){
            if(!o.getText().equals("")){
                 validOrders.add(o.getText());
                break;
            }
        }
        return validOrders.get(position).replaceAll("[^\\d.]","");
    }


    public void myAccountSubscriptionFunctionalityNativeVer(String cta) throws InterruptedException{
        String orderNumber ="";
        By restartCTA = null,pauseCTA = null,skipCTA = null,stopCTA = null,statusSub = null;
        switch(UrlBuilder.getLocale()){
            case "fr":
            case "vusefr":
                orderNumber = getOrderNumber(0,ORDER_NUMBERVUSEFR);
                restartCTA = By.cssSelector(".order-restart-" + orderNumber + " > .action.primary.subscription-update-event");
                pauseCTA = By.cssSelector("[class='action-container-"+ orderNumber +" subscribe-desktop-only'] [data-action='pause']");
                skipCTA = By.cssSelector("[class='action subscription-update-event " + orderNumber+"']");
                stopCTA = By.cssSelector(".action-container-"+ orderNumber +".cancel > form[method='post']  span");
                statusSub = By.cssSelector("[class='status "  + orderNumber+"']");
                break;
            case "vuseuk":
                if (UrlBuilder.getLocale().equals("vusefr")) {
                    orderNumber = webDriver.findElements(ORDER_NUMBERVUSEFR).get(0).getText();
                } else {
                    orderNumber = waitForExpectedElement(ORDER_NUMBERVUSEUK, 5).getText();
                }
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                restartCTA = By.cssSelector(".order-restart-" + orderNumber + " > .action.primary.subscription-update-event");
                pauseCTA = PAUSE_CTA_GLOBAL_SUBS_VUSEUK;
                skipCTA = SKIP_CTA_GLOBAL_SUBS_VUSEUK;
                stopCTA = By.cssSelector(".action-container-"+ orderNumber +".cancel > form[method='post']  span");
                statusSub = By.cssSelector("[class='status "  + orderNumber+"']");
                break;
            case "vusede":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVUSEDE,5).getText();
                orderNumber = orderNumber.substring(11);
                orderNumber = orderNumber.toLowerCase();
                restartCTA = By.cssSelector(".order-restart-" + orderNumber + " > .action.primary.subscription-update-event");
                pauseCTA = By.cssSelector("[class='action-container-"+ orderNumber +" subscribe-desktop-only'] [data-action='pause']");
                skipCTA = By.cssSelector("[class='action subscription-update-event " + orderNumber+"']");
//                stopCTA = By.cssSelector(".action-container-"+ orderNumber +".cancel > form[method='post']"); Cancellation survey disabled
                stopCTA = By.cssSelector(".action-container-"+orderNumber+".cancel > form[method='post']  span");
                statusSub = By.cssSelector("[class='status "  + orderNumber+"']");
                break;
            case "lyftse":
                orderNumber = waitForExpectedElement(ORDER_NUMBERSE,10).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                restartCTA = By.cssSelector(".order-restart.order-restart-"+orderNumber+".show > .action-primary.subscription-update-event");
                pauseCTA = By.cssSelector(".action-bar.action-container-"+orderNumber+".flex > .desktop-buttons > .action-primary.desktop-only.subscription-update-event");
                skipCTA = By.cssSelector(".action-bar.action-container-"+orderNumber+".flex > .desktop-buttons > .action.desktop-only.subscription-update-event");
                stopCTA = By.cssSelector(".bottom-action.bottom-action-container-"+orderNumber+".flex > .subscription-update-event");
                statusSub = By.cssSelector(".order-status-"+orderNumber+".show.status > strong");
                break;
            case "vuseco":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVYPEIT,5).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                restartCTA = By.cssSelector(".order-restart-" + orderNumber + " > .action.cancel-flag-yes.primary.subscription-update-event");
                pauseCTA = By.cssSelector(".action-container-" + orderNumber + ".subscribe-desktop-only > button:nth-child(3) a");
                skipCTA = By.cssSelector("[class='action subscription-update-event " + orderNumber + "']");
                stopCTA = By.cssSelector(".action-container-" + orderNumber + ".cancel > .action.subscription-update-event");
                statusSub = By.cssSelector("[class='status " + orderNumber + "']");
                break;
            case "vypeit":
            case "vuseit":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVYPEIT,5).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                restartCTA = By.xpath("/html//main[@id='maincontent']/div[@class='columns']//span[@class='order-restart-"+orderNumber+"']/button[@type='button']");
                pauseCTA = By.cssSelector("[class='action-container-"+orderNumber+" subscribe-desktop-only'] [type='button']:nth-child(3) span");
                skipCTA = By.cssSelector("[class='action subscription-update-event " + orderNumber + "']");
                stopCTA = By.cssSelector(".action-container-"+orderNumber+".cancel > form[method='post']  span");
                statusSub = By.cssSelector("[class='status " + orderNumber + "']");
                break;
        }
        switch(cta){
            case "pause":
                pauseCTAFlowMethod(pauseCTA,statusSub,restartCTA);
                break;
            case "skip":
                skipCTAFlowMethod(orderNumber,skipCTA);
                break;
            case "stop":
                stopCTAFlowMethod(stopCTA,restartCTA,statusSub);
                break;
            default:
                LOG.info("Given CTA is not valid for this function");
        }
    }

    public void pauseCTAFlowMethod(By pauseCTA,By statusSub,By restartCTA) throws InterruptedException{
        scrollToPageTop();
        if(UrlBuilder.getLocale().equalsIgnoreCase("vuseuk")||UrlBuilder.getLocale().equalsIgnoreCase("vusefr")){
            clickUsingJS(EDIT_CTA_GLOBAL_SUBS_VUSEUK);
        }
        waitForExpectedElement(pauseCTA,10);
        jsScrollElementInCenter(waitForExpectedElement(pauseCTA,10));
        clickUsingJS(pauseCTA);
        acceptLoadAssertGlobalSubsAccountMessage(UrlBuilder.getMessage("SubsAccountStatusPause.key"));
        if(UrlBuilder.getLocale().equalsIgnoreCase("vuseit")){
            refreshBrowser();
        }
        waitForExpectedElement(statusSub, 30);
        assertEquals(waitForExpectedElement(statusSub).getText(), UrlBuilder.getMessage("SubsAccountStatusDisplayedPause.key"));
        jsScrollElementInCenter(waitForExpectedElement(restartCTA));
        waitForExpectedElement(restartCTA, 30);
        clickUsingJS(restartCTA);
        switch(UrlBuilder.getLocale()){
            case "vypeit":
            case "vuseit":
            case "vuseco":
                waitForExpectedElement(CONFIRM_RESTART_CTA_GLOBAL_SUBS_VYPEIT).click();
                invisibilityOfElementLocated(HomePage.LOADING_CIRCLE);
                waitForElementToAppearAndDisappear(LOADER,10,10);
                assertEquals(waitForExpectedElement(SUCCESS_MESSAGE_GLOBAL_SUBS).getText(),UrlBuilder.getMessage("SubsAccountStatusRestart.key"));
                break;
            case "fr":
            case "vusefr":
                waitForExpectedElement(CONFIRM_RESTART_CTA_GLOBAL_SUBS,10).click();//same css
                invisibilityOfElementLocated(HomePage.LOADING_CIRCLE,20);
                waitForElementToAppearAndDisappear(LOADER,2,5);
                break;
            case "vusede":
            case "vuseuk":
                waitForExpectedElement(CONFIRM_RESTART_CTA_GLOBAL_SUBS,10).click();//same css
                invisibilityOfElementLocated(HomePage.LOADING_CIRCLE,20);
                waitForElementToAppearAndDisappear(LOADER,2,5);
                assertEquals(waitForExpectedElement(SUCCESS_MESSAGE_GLOBAL_SUBS,20).getText(),UrlBuilder.getMessage("SubsAccountStatusRestart.key"));
                break;
            default:
                acceptConfirmationAndLoadGloSubs();
        }
    }


    public void skipCTAFlowMethod(String orderNumber,By skipCTA) throws InterruptedException{
        int initialMonth = getDateFromDatePicker(orderNumber);
        if(UrlBuilder.getLocale().equalsIgnoreCase("vuseuk")||UrlBuilder.getLocale().equalsIgnoreCase("vusefr")){
            clickUsingJS(EDIT_CTA_GLOBAL_SUBS_VUSEUK);
        }
        waitForExpectedElement(skipCTA).click();
        acceptConfirmationAndLoadGloSubs();
        Thread.sleep(3000);
        switch (UrlBuilder.getLocale()){
            case "vusede":
                invisibilityOfElementLocated(HomePage.LOADING_CIRCLEVUSE_DE,20);
                break;
            case "vuseuk":
                invisibilityOfElementLocated(HomePage.LOADING_CIRCLEVUSEUK,20);
                break;
        }
        int skippedMonth = getDateFromDatePicker(orderNumber);
        if(initialMonth==12){
            assertEquals(skippedMonth, 1);
        }else {
            assertEquals(initialMonth + 1, skippedMonth);
        }
        refreshBrowser();
    }

    public void stopCTAFlowMethod(By stopCTA, By restartCTA, By statusSub){
            switch(UrlBuilder.getLocale()){
                case "lyftse":
                    clickByElementByQueryJSExecutor(stopCTA);
                    assertTrue(waitForExpectedElement(STOP_CTA_GLOBAL_SUBS_CONFIRM_REASON_DROPDOWN).isDisplayed());
                    waitForExpectedElement(STOP_CTA_GLOBAL_SUBS_CONFIRM_LYFTSE).click();
                    break;
                case "vuseuk":
                case "vusede":
                case "vusefr":
                    waitForExpectedElement(stopCTA).click();
                    waitAndClickByElementByJSExecutor(SUBS_CANCELLATION_SURVEY_FIRST_PAGE_UNSUBSCRIBE_CTA,5);
                    waitAndClickByElementByJSExecutor(SUBS_CANCELLATION_SURVEY_SECOND_PAGE_UNSUBSCRIBE_CTA,5);
                    urlToContainInSeconds("/survey/#complete",20);
                    waitAndClickByElementByJSExecutor(SUBS_CANCELLATION_SURVEY_END_PAGE_RETURN_CTA,5);
                    break;
                case "vypeit":
                case "vuseit":
                    waitForExpectedElement(stopCTA).click();
                    waitAndClickByElementByJSExecutor(SUBS_CANCELLATION_SURVEY_FIRST_PAGE_UNSUBSCRIBE_CTA,5);
                    waitAndClickByElementByJSExecutor(SUBS_CANCELLATION_SURVEY_SECOND_PAGE_UNSUBSCRIBE_CTA,5);
                    waitAndClickByElementByJSExecutor(SUBS_CANCELLATION_SURVEY_CONFIRM_CANCELLATION_FEE,5);
                    urlToContainInSeconds("/survey/#complete",20);
                    break;
                default:
                    clickByElementByQueryJSExecutor(stopCTA);
                    assertTrue(waitForExpectedElement(STOP_CTA_GLOBAL_SUBS_CONFIRM_REASON_DROPDOWN).isDisplayed());
                    waitForExpectedElement(STOP_CTA_GLOBAL_SUBS_CONFIRM).click();
                    break;
            }
            if(!UrlBuilder.getLocale().equalsIgnoreCase("vuseit")) {
                refreshBrowser();
                if (!UrlBuilder.getLocale().equalsIgnoreCase("lyftse")) {
                    scrollToPageTop();
                    waitForExpectedElement(SUBS_CANCELLED_TAB).click();
                }
                String currentStatus = "";
                try{
                    currentStatus=waitForExpectedElement(statusSub).getText();
                }catch (TimeoutException te){
                    refreshBrowser();
                    currentStatus=waitForExpectedElement(statusSub,10).getText();
                }
                assertEquals(currentStatus, UrlBuilder.getMessage("SubsAccountStatusDisplayedCancelled.key"));
                scrollToPageTop();
                waitForExpectedElement(restartCTA, 5);
                clickByElementByQueryJSExecutor(restartCTA);
                switch (UrlBuilder.getLocale()) {
                    case "fr":
                    case "vusefr":
                    case "vusede":
                    case "vuseuk":
                    case "vuseco":
                        waitForExpectedElement(STOP_CTA_GLOBAL_SUBS_CONFIRM_SE).click();//same css
                        invisibilityOfElementLocated(HomePage.LOADING_CIRCLE,20);
                        break;
                    default:
                        acceptConfirmationAndLoadGloSubs();
                }
            }
        }

    public void addressAddAndAssertSubs(String address, By loadingCircle, By addressLabel, By telephone, By city, By zip, By addressField) throws InterruptedException{
        invisibilityOfElementLocated(loadingCircle,30);
        switch(UrlBuilder.getLocale()){
            case "fr":
            case "vusefr":
                waitClearAndEnterText(ADDRESS_GLO_SUBS_NATIVE_TELEPHONE_VUSEFR,"+336789021345");
                break;
            case "vusede":
                waitClearAndEnterText(ADDRESS_GLO_SUBS_NATIVE_TELEPHONE,"7744123456");
                break;
            case "vypeit":
            case "vuseit":
                waitClearAndEnterText(telephone,"+391234567890");
                break;
            case "vuseco":
                waitForExpectedElement(telephone);
                waitClearAndEnterText(telephone,"+573930420182");
                break;
            default:
                waitClearAndEnterText(ADDRESS_GLO_SUBS_NATIVE_TELEPHONE,"+336789021345");
                break;
        }
        if(UrlBuilder.getLocale().equals("vuseco")) {
            selectValueFromDropDownByIndex(2,ADDRESS_GLO_SUBS_REGION_DROPDOWN_VUSECO);
        }else{
            if(getWebDriver().findElements(accountDashboardPage.ADD_ADDRESS_MANUALLY).size()>0)
                waitAndClickByElementByJSExecutor(accountDashboardPage.ADD_ADDRESS_MANUALLY,10);
            waitClearAndEnterText(zip, "75007");
            waitClearAndEnterText(city,"Paris");
        }
        waitClearAndEnterText(addressField, "Champ de Mars, 6 Avenue Anatol");
        waitForExpectedElement(ADDRESS_GLO_SUBS_NATIVE_CONFIRMATION).click();
        invisibilityOfElementLocated(loadingCircle, 40);
        assertTrue(waitForExpectedElement(addressLabel).getText().contains("6"));
    }

    public void updateAddressGlobalSubsNativeVer() throws InterruptedException{
        String address;
        String orderNumber;
        By currentAddress;
        By changeCurrentAddress;
        switch (UrlBuilder.getLocale()){
            case "fr":
            case "vusefr":
                orderNumber = webDriver.findElements(ORDER_NUMBERVUSEFR).get(0).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                currentAddress = By.cssSelector("#address-container-"+orderNumber);
                changeCurrentAddress = By.cssSelector(".subscription-column-action-"+orderNumber+" > span.address-section > button");
                address = waitForExpectedElement(currentAddress).getText();
                waitForExpectedElement(changeCurrentAddress).click();
                addressAddAndAssertSubs(address, HomePage.LOADING_CIRCLE,ADDRESS_GLO_SUBS_NATIVE_FR, ADDRESS_GLO_SUBS_NATIVE_TELEPHONE,ADDRESS_GLO_SUBS_NATIVE_CITY,ADDRESS_GLO_SUBS_NATIVE_ZIP,ADDRESS_GLO_SUBS_NATIVE_ADDRESS);
                break;
            case "lyftse":
                address = waitForExpectedElement(ADDRESS_GLO_SUBS_NATIVE).getText();
                scrollElementIntoView(EDIT_ADDRESS_GLOBAL_SUBS_NATIVE_SE);
                waitForExpectedElement(EDIT_ADDRESS_GLOBAL_SUBS_NATIVE_SE).click();
                addressAddAndAssertSubs(address, HomePage.LOADING_CIRCLESE,ADDRESS_GLO_SUBS_NATIVE, ADDRESS_GLO_SUBS_NATIVE_TELEPHONE,ADDRESS_GLO_SUBS_NATIVE_CITY,ADDRESS_GLO_SUBS_NATIVE_ZIP,ADDRESS_GLO_SUBS_NATIVE_ADDRESS);
                break;
            case "vusede":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVUSEDE,5).getText();
                orderNumber = orderNumber.substring(11);
                orderNumber = orderNumber.toLowerCase();
                currentAddress = By.cssSelector("#address-container-"+orderNumber);
                changeCurrentAddress = By.cssSelector(".subscription-column-action-"+orderNumber+" > .address-section > button[title='Lieferadresse ändern'] > span");
                By addressLabel = By.cssSelector("[class='subscription-column-action-"+orderNumber+"'] address");
                address = waitForExpectedElement(currentAddress).getText();
                waitForExpectedElement(changeCurrentAddress).click();
                addressAddAndAssertSubs(address, HomePage.LOADING_CIRCLEVUSE_DE,addressLabel, ADDRESS_GLO_SUBS_NATIVE_TELEPHONE,ADDRESS_GLO_SUBS_NATIVE_CITY,ADDRESS_GLO_SUBS_NATIVE_ZIP,ADDRESS_GLO_SUBS_NATIVE_ADDRESS_NO_VUSEDE);
                break;
            case "vuseco":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVYPEIT,5).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                currentAddress = By.cssSelector("[class='subscription-column-action-"+orderNumber+"'] address");
                changeCurrentAddress = By.cssSelector("button[title='CAMBIAR DIRECCIÓN DE ENVIO'] > span");
                address = waitForExpectedElement(currentAddress).getText();
                waitForExpectedElement(changeCurrentAddress).click();
                addressAddAndAssertSubs(address, HomePage.LOADING_CIRCLEVUSECO,currentAddress, ADDRESS_GLO_SUBS_NATIVE_TELEPHONEVYPEIT,ADDRESS_GLO_SUBS_NATIVE_CITY_VUSECO,ADDRESS_GLO_SUBS_NATIVE_ZIP,ADDRESS_GLO_SUBS_NATIVE_ADDRESS);
                break;
            case "vypeit":
            case "vuseit":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVYPEIT,5).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                currentAddress = By.cssSelector("[class='subscription-column-action-"+orderNumber+"'] address");
                changeCurrentAddress = By.cssSelector(".subscription-column-action-"+orderNumber+" > .address-section > button[title='Cambia il tuo indirizzo di spedizione'] > span");
                address = waitForExpectedElement(currentAddress).getText();
                waitForExpectedElement(changeCurrentAddress).click();
                addressAddAndAssertSubs(address, HomePage.LOADING_CIRCLEVYPEIT,currentAddress, ADDRESS_GLO_SUBS_NATIVE_TELEPHONEVYPEIT,ADDRESS_GLO_SUBS_NATIVE_CITY,ADDRESS_GLO_SUBS_NATIVE_ZIP,ADDRESS_GLO_SUBS_NATIVE_ADDRESS);
                break;
        }
    }

    public void updatePayment(By currentcard,By changeCard,By cardSelectors,By cardSelectorNo,By cardApplyAll,
                              By confirmSelection, By usedCard, By loadingCircle){
        String newCard="1111";
        if(waitForExpectedElement(currentcard,5).getText().contains("4444")){
            newCard="4444";
        }
        clickByElementByQueryJSExecutor(changeCard);
        List<WebElement> checkBoxList=webDriver.findElements(cardSelectors);
        for (WebElement cl : checkBoxList) {
            System.out.println("cards"+cl.getText());
            if (cl.getText().contains(newCard)) {
                cl.click();
            }
        }
        selectValueFromDropDownByIndex(1,cardApplyAll);
        waitForExpectedElement(confirmSelection,5).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        assertTrue(waitForExpectedElement(usedCard,5).getText().contains(newCard));
        invisibilityOfElementLocated(loadingCircle,30);
    }

    public void updatePaymentMethod() throws InterruptedException{
        String orderNumber;
        By changeCard;
        By currentCard;
        switch(UrlBuilder.getLocale()){
            case "fr":
            case "vusefr":
                orderNumber = waitForExpectedElement(ORDER_NUMBER,5).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                changeCard = By.cssSelector("span.subscription-column-action-"+orderNumber+" > span.payment-section > button");
                currentCard = By.cssSelector("span.payment-section > span.payment-info-"+orderNumber+".method");
                updatePayment(currentCard,changeCard,GLO_SUBS_CARDS_SELECTORSE,GLO_SUBS_CARDS_SELECTORSE,
                        GLO_SUBS_APPLY_TO_ALL_CARDS,GLO_SUBS_CONFIRM_CARD,currentCard,HomePage.LOADING_CIRCLE);
                break;
            case "lyftse":
                updatePayment(GLO_SUBS_CURRENT_CARDSE,GLO_SUBS_CARD_CHANGESE,GLO_SUBS_CARDS_SELECTORSE,GLO_SUBS_CARDS_SELECTORSE,
                        GLO_SUBS_APPLY_TO_ALL_CARDS,GLO_SUBS_CONFIRM_CARD,GLO_SUBS_CURRENT_CARDSE,HomePage.LOADING_CIRCLESE);
                break;
            case "vusede":
                updatePayment(GLO_SUBS_CURRENT_CARD_VUSEDE,GLO_SUBS_CHANGE_CARD_VUSEDE,GLO_SUBS_CARDS_SELECTORSE,GLO_SUBS_CARDS_SELECTORSE,
                        GLO_SUBS_APPLY_TO_ALL_CARDS,GLO_SUBS_CONFIRM_CARD,GLO_SUBS_CURRENT_CARD_VUSEDE,HomePage.LOADING_CIRCLEVUSE_DE);
                break;
            case "vuseco":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVYPEIT,5).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                changeCard = By.cssSelector(".subscription-column-action-"+orderNumber+" > .payment-section > button[title='CAMBIAR MÉTODO DE PAGO'] > span");
                currentCard = CURRENT_CARDNO_VUSECO;
                addNewCardSubscription(changeCard);
                updatePayment(currentCard,changeCard,GLO_SUBS_CARDS_SELECTORVUSECO,GLO_SUBS_CARDS_SELECTORVUSECO,
                        GLO_SUBS_APPLY_TO_ALL_CARDS,GLO_SUBS_CONFIRM_CARDVYPEIT,currentCard,HomePage.LOADING_CIRCLEVUSECO);
                break;
            case "vypeit":
            case "vuseit":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVYPEIT,5).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                changeCard = By.cssSelector(".subscription-column-action-"+orderNumber+" > .payment-section > button[title='Cambia metodo di pagamento']");
                currentCard = By.cssSelector(".method.payment-info-"+orderNumber);
                addNewCardSubscription(changeCard);
                updatePayment(currentCard,changeCard,GLO_SUBS_CARDS_SELECTORVYPEIT,GLO_SUBS_CARDS_SELECTORVYPEIT,
                        GLO_SUBS_APPLY_TO_ALL_CARDS,GLO_SUBS_CONFIRM_CARDVYPEIT,currentCard,HomePage.LOADING_CIRCLEVYPEIT);
                break;
        }
    }

    public void addCardFromSubscriptionPage() throws InterruptedException{
        String orderNumber;
        By changeCard;
        List<WebElement> checkBoxList;
        int newCardCount;
        int initialCardCount=0;
        switch(UrlBuilder.getLocale()){
            case "fr":
            case "vusefr":
                orderNumber = getOrderNumber(0,ORDER_NUMBER);
                changeCard = By.cssSelector("span.subscription-column-action-"+orderNumber+" > span.payment-section > button");
                clickByElementByQueryJSExecutor(changeCard);
                checkBoxList=webDriver.findElements(GLO_SUBS_CARDS_SELECTORSE);
                initialCardCount=checkBoxList.size();
                addNewCardSubscription(changeCard);
                clickByElementByQueryJSExecutor(changeCard);
                break;
            case "lyftse":
                clickByElementByQueryJSExecutor(GLO_SUBS_CARD_CHANGESE);
                checkBoxList=webDriver.findElements(GLO_SUBS_CARDS_SELECTORSE);
                initialCardCount=checkBoxList.size();
                addNewCardSubscription(GLO_SUBS_CARD_CHANGESE);
                clickByElementByQueryJSExecutor(GLO_SUBS_CARD_CHANGESE);
                break;
            case "vusede":
                clickByElementByQueryJSExecutor(GLO_SUBS_CHANGE_CARD_VUSEDE);
                checkBoxList=webDriver.findElements(GLO_SUBS_CARDS_SELECTORSE);
                initialCardCount=checkBoxList.size();
                addNewCardSubscription(GLO_SUBS_CHANGE_CARD_VUSEDE);
                clickByElementByQueryJSExecutor(GLO_SUBS_CHANGE_CARD_VUSEDE);
                break;
        }
        checkBoxList=webDriver.findElements(GLO_SUBS_CARDS_SELECTORSE);
        newCardCount=checkBoxList.size();
        assertEquals((initialCardCount + 1),newCardCount);
        refreshBrowser();
    }

    public void addNewCardSubscription(By changeCard) throws InterruptedException{
        CardAndAddressDetails cardAndAddressDetails = new CardAndAddressDetails("12 the cloisters","Watford","WD3 1HF",
                "4111111111111111","10","2029","123");
        clickByElementByQueryJSExecutor(changeCard);
        waitForExpectedElement(GLO_SUBS_ADD_CARD).click();
        cardAndAddressDetails.enterAddressDetails(GLO_SUBS_CARD_FIRST_NAME_INPUT,GLO_SUBS_CARD_LAST_NAME_INPUT,GLO_SUBS_CARD_TELEPHONE_INPUT,
                GLO_SUBS_CARD_ADDRESS1_INPUT,GLO_SUBS_CARD_CITY_INPUT,GLO_SUBS_CARD_POSTCODE_INPUT);
        cardAndAddressDetails.enterSubscriptionCardDetails(GLO_SUBS_CARDNUMBER_INPUT,GLO_SUBS_CARD_MONTH_INPUT,
                GLO_SUBS_CARD_YEAR_INPUT,GLO_SUBS_CARD_CVV_INPUT);
        switch(UrlBuilder.getLocale()){
            case "vuseco":
                clickByElementByQueryJSExecutor(GLO_SUBS_CARD_CONFIRM_CTA_VUSECO);
                urlToContainInSeconds("batsubscribepro/cards/savedcard",3);
                homePage.defaultClickLinkByLinkText("MySubscriptions.key");
                urlToContainInSeconds("customer/subscriptions",5);
                break;
            case "vusede":
                clickByElementByQueryJSExecutor(GLO_SUBS_CARD_CONFIRM_CTA_VUSEDE);
                urlToContainInSeconds("batsubscribepro/cards/savedcard",3);
                homePage.defaultClickLinkByLinkText("MySubscriptions.key");
                urlToContainInSeconds("customer/subscriptions",5);
                break;
            case "lyftse":
                clickByElementByQueryJSExecutor(GLO_SUBS_CARD_CONFIRM_CTA_LYFTSE);
                urlToContainInSeconds("batsubscribepro/cards/savedcard",3);
                homePage.defaultClickLinkByLinkText("MySubscriptions.key");
                break;
            case "vusefr":
                clickByElementByQueryJSExecutor(GLO_SUBS_CARD_CONFIRM_CTA_VUSEFR);
                urlToContainInSeconds("batsubscribepro/cards/savedcard",3);
                homePage.defaultClickLinkByLinkText("MySubscriptions.key");
                break;
            default:
                clickByElementByQueryJSExecutor(GLO_SUBS_CARD_CONFIRM_CTA);
                urlToContainInSeconds("batsubscribepro/cards/savedcard",3);
                homePage.defaultClickLinkByLinkText("MySubscriptions.key");
        }
    }

    public void modifyExistingSubscriptionProduct(String incdec,int qty) throws InterruptedException{
        int cq=setQtyOfSubscription();
        clickUsingJS(EDIT_CTA_GLOBAL_SUBS_VUSEUK);
        if(incdec.equals("increase")){
            for(int i=qty;i>0;i--){
                waitForExpectedElement(GLO_SUBS_INCREASE_CTA_VUSEUK).click();
            }
        }else{
            for(int i=qty;i>0;i--){
                waitForExpectedElement(GLO_SUBS_DECREASE_CTA_VUSEUK).click();
            }
        }
        jsScrollElementInCenter(waitForExpectedElement(GLO_SUBS_APPLY_CHANGES_CTA));
        waitForExpectedElement(GLO_SUBS_APPLY_CHANGES_CTA,15).click();
        waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_FEE_CTA_VUSEUK).click();
        waitForExpectedElement(SUCCESS_MESSAGE_GLOBAL_SUBS_VUSEUK,60);
        int uq = setQtyOfSubscription();
        if(incdec.equalsIgnoreCase("increase")){
            assertEquals(uq, cq + qty);
        }else{
            assertEquals(uq, cq - qty);
        }
    }

    public void modifySubscriptionQty(String modifier, int qty) throws InterruptedException{
        int cq=setQtyOfSubscription();
        switch(UrlBuilder.getLocale()){
            case "vusefr":
                String orderNumber = getOrderNumber(0,ORDER_NUMBERVUSEFR);
                clickUsingJS(By.cssSelector("#subscription-form-"+orderNumber+" > button"));
                break;
            case "lyftse":
                waitForExpectedElement(GLO_SUBS_UPDATE_CTA_LYFTSE).click();
                break;
            default:
                waitForExpectedElement(GLO_SUBS_UPDATE_CTA,50);
                clickUsingJS(GLO_SUBS_UPDATE_CTA);
        }
        if(modifier.equals("increase")){
            for(int i=qty;i>0;i--){
                try {
                    waitForExpectedElement(GLO_SUBS_INCREASE_CTA).click();
                }catch(Exception e){
                    waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
                    waitForExpectedElement(GLO_SUBS_INCREASE_CTA).click();
                }
            }
        }else{
            for(int i=qty;i>0;i--){
                try {
                    waitForExpectedElement(GLO_SUBS_DECREASE_CTA).click();
                }catch(Exception e){
                    waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
                    jsScrollElementInCenter(waitForExpectedElement(GLO_SUBS_DECREASE_CTA));
                    waitForExpectedElement(GLO_SUBS_DECREASE_CTA).click();
                }
            }
        }try {
            waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
        }catch(Exception e){
            LOG.info("No Notification popup occurred");
        }
        jsScrollElementInCenter(waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_CTA));
        waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_CTA,15).click();
        if(UrlBuilder.getLocale().equalsIgnoreCase("lyftse")){
            waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_FEE_CTA_LYFTSE).click();
        }else{
            waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_FEE_CTA).click();
        }
        urlToContainInSeconds("customer/subscriptions",80);
        int uq = setQtyOfSubscription();
        if(modifier.equalsIgnoreCase("increase")){
            assertEquals(uq, cq + qty);
        }else{
            assertEquals(uq, cq - qty);
        }
    }

    public int setQtyOfSubscription() throws InterruptedException{
        String orderNumber="";
        By qtySelector = null;
        Thread.sleep(3000);
        switch(UrlBuilder.getLocale()) {
            case "fr":
                orderNumber =getOrderNumber(0,ORDER_NUMBERVUSEFR);
                qtySelector = By.cssSelector("[class='data table details "+orderNumber+"'] .product-info");
                break;
            case "vuseuk":
                if (webDriver.findElements(CLOSE_MODAL_BUTTON).size() > 0) {
                    waitForExpectedElement(CLOSE_MODAL_BUTTON).click();
                }
                qtySelector = By.cssSelector(".product-info");
                break;
            case "vusefr":
                if (webDriver.findElements(CLOSE_MODAL_BUTTON).size() > 0) {
                    waitForExpectedElement(CLOSE_MODAL_BUTTON).click();
                }
                orderNumber = webDriver.findElements(ORDER_NUMBERVUSEFR).get(0).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                qtySelector = By.cssSelector("[class='data table details "+orderNumber+"'] .product-info");
                break;
            case "lyftse":
                qtySelector = GLO_SUBS_CURRENT_QTY_LYFTSE;
                break;
            case "vusede":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVUSEDE, 5).getText();
                orderNumber = orderNumber.substring(11);
                orderNumber = orderNumber.toLowerCase();
                qtySelector = By.cssSelector("[class='data table details "+orderNumber+"'] .product-info");
                break;
            case "vuseco":
            case "vypeit":
            case "vuseit":
                qtySelector = CURRENT_ORDER_QTY_VUSEITCO;
                break;
        }
        String quantity="";
        try {
            switch(UrlBuilder.getLocale()){
                case "vusefr":
                    quantity = getNumberFromString(waitForExpectedElement(qtySelector, 10).getText().split("\n")[2]);
                    break;
                case "vuseuk":
                    quantity = waitForExpectedElement(qtySelector, 10).getText().replace("\n","");
                    break;
                default:
                    quantity = getNumberFromString(waitForExpectedElement(qtySelector, 10).getText());
            }
        }catch(NullPointerException ne){
            urlToContainInSeconds("customer/subscriptions",25);
            quantity = getNumberFromString(waitForExpectedElement(qtySelector, 10).getText());
        }
        switch(UrlBuilder.getLocale()){
            case "vusede":
                return Integer.parseInt(quantity.substring(quantity.lastIndexOf("Anzahl:")+2));
            case "vuseuk":
                return Integer.parseInt(quantity.substring(quantity.lastIndexOf("mg")+2));
            default:
                return Integer.parseInt(quantity);
        }
    }

    public void addRemoveNewSubscriptionProducts(String addremove){
        scrollToPageTop();
        waitForExpectedElement(EDIT_CTA_GLOBAL_SUBS_VUSEUK,30);
        clickUsingJS(EDIT_CTA_GLOBAL_SUBS_VUSEUK);
        if(addremove.equals("add")){
            clickUsingJS(ADD_NEW_FLAVOUR_CTA_GLOBAL_SUBS_VUSEUK);
            int qty=4;
            switch(UrlBuilder.getLocale()){
                case "vuseuk":
                    waitForExpectedElement(GLO_SUBS_SELECT_PRODUCT_STRENGTH_UK,20);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_STRENGTH_UK);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_ADD_UK);
                    break;
                case "vusefr":
                    waitForExpectedElement(GLO_SUBS_SELECT_PRODUCT_STRENGTH,20);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_STRENGTH);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_ADD);
                    break;
            }
            scrollToPageTop();
            for(int i=0;i<qty;i++){
                waitForExpectedElement(GLO_SUBS_INCREASE_BOTTOM_CTA_VUSEUK).click();
            }
        }else {
            scrollToPageTop();
            waitForExpectedElement(GLO_SUBS_DELETE_PRODUCT_VUSEUK,10).click();
            waitForExpectedElement(GLO_SUBS_CONFIRM_DELETE_PRODUCT_VUSEUK,10).click();
        }
        jsScrollElementInCenter(waitForExpectedElement(GLO_SUBS_APPLY_CHANGES_CTA));
        waitForExpectedElement(GLO_SUBS_APPLY_CHANGES_CTA,15).click();
        waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_FEE_CTA_VUSEUK).click();
        waitForExpectedElement(SUCCESS_MESSAGE_GLOBAL_SUBS_VUSEUK,120);
    }

    public void modifySubscriptionProducts(String modifier){
        switch(UrlBuilder.getLocale()){
            case "vusefr":
                String orderNumber = getOrderNumber(0,ORDER_NUMBERVUSEFR);
                clickUsingJS(By.cssSelector("#subscription-form-"+orderNumber+" > button"));
                break;
            case "lyftse":
                waitForExpectedElement(GLO_SUBS_UPDATE_CTA_LYFTSE).click();
                break;
            default:
                waitForExpectedElement(GLO_SUBS_UPDATE_CTA,30);
                clickUsingJS(GLO_SUBS_UPDATE_CTA);
        }
        if(modifier.equals("add")){
            int qty=4;
            switch(UrlBuilder.getLocale()){//Change product category
                case "vusefr":
                    clickUsingJS(GLO_SUBS_EPODS_VUSEFR);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_STRENGTH);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_ADD);
                    break;
                case "vuseuk":
                    waitForExpectedElement(GLO_SUBS_SELECT_PRODUCT_STRENGTH_UK,20);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_STRENGTH_UK);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_ADD_UK);
                    break;
                case "vuseit":
                    waitForExpectedElement(GLO_SUBS_SELECT_PRODUCT_STRENGTH_VUSEIT,10);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_STRENGTH_VUSEIT);
                    clickUsingJS(GLO_SUBS_SELECT_PRODUCT_ADD_VUSEIT);
                    break;
                case "vuseco":
                    waitForExpectedElement(GLO_SUBS_EPOD_CATEGORY_VUSECO,10).click();
                    qty=2;
                try {
                    addProductHasIntensity(1);
                } catch (Exception e) {
                    LOG.info(e.getMessage());
                }
                    break;
                case "lyftse":
                    clickByElementByQueryJSExecutor(GLO_SUBS_SELECT_PRODUCT_ADD_LYFTSE);
                break;
                default:
                    try {
                        addProductHasIntensity(1);
                    } catch (Exception e) {
                        LOG.info(e.getMessage());
                    }
            }
            scrollToPageTop();
            for(int i=0;i<qty;i++){
                try {
                    waitForExpectedElement(GLO_SUBS_INCREASE_CTA_BOTTOM).click();
                }catch(Exception e){
                    waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA,15).click();
                    jsScrollElementInCenter(waitForExpectedElement(GLO_SUBS_INCREASE_CTA_BOTTOM));
                    waitForExpectedElement(GLO_SUBS_INCREASE_CTA_BOTTOM).click();
                }
            }
        }else{
            waitForExpectedElement(GLO_SUBS_DELETE_PRODUCT).click();
            waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
        }try {
            waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
        }catch(Exception e){
            LOG.info("No Notification popup occurred");
        }
        waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_CTA).click();
        if(UrlBuilder.getLocale().equalsIgnoreCase("lyftse")){
            waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_FEE_CTA_LYFTSE).click();
        }else{
            waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_FEE_CTA).click();
        }
        urlToContainInSeconds("customer/subscriptions",45);
    }

    public void checkSubscriptionTier(String tier) throws InterruptedException{
        Thread.sleep(3000);
        String orderNumber ="";
        By currentTier = null;
        String capTier = tier.substring(0, 1).toUpperCase() + tier.substring(1);
        String translatedTier = UrlBuilder.getMessage(capTier+"Tier.key");
        switch(UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
                orderNumber = getOrderNumber(0,ORDER_NUMBERVUSEFR);
                currentTier = By.cssSelector("[class='subscription-tier-label "+orderNumber+" "+translatedTier+"'] .text");
                break;
            case "vuseuk":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVUSEFR, 60).getText();
                orderNumber = orderNumber.replaceAll("[^\\d.]","");
                currentTier = By.cssSelector("[class='subscription-tier-label "+orderNumber+" "+translatedTier+"'] .text");
                break;
            case "lyftse":
                currentTier = GLO_SUBS_CURRENT_TIER_LYFTSE;
                break;
            case "vusede":
                orderNumber = waitForExpectedElement(ORDER_NUMBERVUSEDE, 5).getText();
                orderNumber = orderNumber.substring(11);
                orderNumber = orderNumber.toLowerCase();
                currentTier = By.cssSelector("[class='subscription-tier-label "+orderNumber+" "+translatedTier+"'] .text");
                break;
            case "vuseco":
            case "vypeit":
            case "vuseit":
                currentTier = CURRENT_ORDER_TIER_VUSEITCO ;
                break;
        }
        try {
            assertTrue(waitForExpectedElement(currentTier,60).isDisplayed());
        }catch(NullPointerException ne){
            urlToContainInSeconds("customer/subscriptions",60);
            assertEquals(webDriver.findElements(currentTier).size(),1);
        }
    }

    public void getUpdatedSubscriptionTier(String modifier) throws InterruptedException{
        if(modifier.equals("promoted")){
            if(currentTier.equalsIgnoreCase(UrlBuilder.getMessage("BronzeTier.key"))){ checkSubscriptionTier("silver");}
            else{checkSubscriptionTier("gold");}
        }else{
            if(currentTier.equalsIgnoreCase(UrlBuilder.getMessage("SilverTier.key"))){ checkSubscriptionTier("silver");}
            else{checkSubscriptionTier("bronze");}
        }
    }

    public void assertSubscriptionStatus(String currentStatus){
        waitForExpectedElement(SUBS_CANCELLED_TAB).click();
        String orderNumber = waitForExpectedElement(ORDER_NUMBERVYPEIT,5).getText();
        orderNumber = orderNumber.replaceAll("[^\\d.]","");
        By statusSub = By.cssSelector("[class='status " + orderNumber + "']");
        assertTrue(waitForExpectedElement(statusSub).getText().equalsIgnoreCase(currentStatus));
    }
    //Payment methods
    public void selectGlobalSubPaymentOptions() {
        if(getWebDriver().findElements(GLOBAL_SUBS_PAYMENT_LOWER_STRENGTH).size()>0)
            waitAndClickByElementByJSExecutor(GLOBAL_SUBS_PAYMENT_LOWER_STRENGTH,10);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        scrollElementIntoView(GLOBAL_SUB_PAYMENT_OPTION);
        try {
            waitForExpectedElement(GLOBAL_SUB_PAYMENT_OPTION, 10).click();
        }catch (Exception e) {
            clickByElementByQueryJSExecutor(GLOBAL_SUB_PAYMENT_OPTION);
        }
    }

    public void attemptToCheckoutWith3DSActiveSubs(By checkoutCTA, By loadingCircle) {
        clickByElementByQueryJSExecutor(checkoutCTA);
        invisibilityOfElementLocated(loadingCircle,60);
        waitForAjaxElementNotToBePresent(getWebDriver(),15);
        try {
            frameToBeAvailableAndSwitchToIt(GLOBAL_SUBS_3DS_FRAME);
            waitForExpectedElement(GLOBAL_SUBS_3DS_PASSWORD).sendKeys("123456");
            waitForExpectedElement(GLOBAL_SUBS_3DS_CONFIRM).click();
        } catch (WebDriverException we) {
            LOG.info("3DS subscription check did not show for this payment, proceeding to checkout");
        }
    }

    public void enterValidMasterCardDetailsAndSubmitSubscribePro(String cardNO) throws Exception {
        if (UrlBuilder.getLocale().equals("lyftse")) {
            paymentPage.enterSelectPackageShopDetails();
        }
        getWebDriver().switchTo().frame(0);
        waitForExpectedElement(GLOBAL_SUBS_CARD_NO_INPUT).sendKeys(cardNO);
        getWebDriver().switchTo().defaultContent();
        waitForExpectedElement(GLOBAL_SUBS_EXPIRATION_MONTH).sendKeys("10");
        waitForExpectedElement(GLOBAL_SUBS_EXPIRATION_YEAR).sendKeys("2029");
        getWebDriver().switchTo().frame(1);
        waitForExpectedElement(GLOBAL_SUBS_CV_VFIELD).sendKeys("123");
        getWebDriver().switchTo().defaultContent();
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_CHECKBOXSE);
                clickByElementByQueryJSExecutor(GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXSE);
                attemptToCheckoutWith3DSActiveSubs(SUBS_CHECKOUT_CTA_SE, LOADING_CIRCLE);
                urlToContainInSeconds("onepage/sucesss",80);
                break;
            case "vypeit":
            case "vuseit":
                clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_CHECKBOXVYPEIT);
                clickByElementByQueryJSExecutor(GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVYPEIT);
                attemptToCheckoutWith3DSActiveSubs(SUBS_CHECKOUT_CTA_VYPEIT, LOADING_CIRCLE_VUSE_IT);
                urlToContainInSeconds("onepage/sucesss",80);
                break;
            case "vuseco":
                clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_CHECKBOXSE);
                clickByElementByQueryJSExecutor(GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVUSECO);
                waitForAjaxElementNotToBePresent(webDriver,20);
                clickByElementByQueryJSExecutor(SUBS_CHECKOUT_CTA_VUSECO);
                waitForAjaxElementNotToBePresent(getWebDriver(),20);
                urlToContainInSeconds("onepage/sucesss",80);
                break;
            case "vusede":
                // clickByElementByQueryJSExecutor(GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVUSEDE); existing issue
                attemptToCheckoutWith3DSActiveSubs(SUBS_CHECKOUT_CTA_VUSEDE, LOADING_CIRCLE_VUSE_DE);
                urlToContainInSeconds("onepage/sucesss",80);
                break;
            case "fr":
            case "vusefr":
                clickByElementByQueryJSExecutor(GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOX);
                clickByElementByQueryJSExecutor(SUBS_CHECKOUT_CTA_FR);
                urlToContainInSeconds("onepage/sucesss",80);
                break;
            case "vuseuk":
                clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_CHECKBOXVUSEUK);
                clickByElementByQueryJSExecutor(GLOBAL_SUBS_TERMS_AND_CONDITIONS_CHECKBOXVUSEUK);
                clickByElementByQueryJSExecutor(SUBS_CHECKOUT_CTA_VUSEUK);
                urlToContainInSeconds("onepage/sucesss",80);
                break;
        }
    }

    //cart
    public void clickGlobalSubsOption() {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                waitForAjaxElementNotToBePresent(webDriver,10);
                clickByElementByQueryJSExecutor(GLOBAL_SUBS_OPTION_PDPSE);
                break;
            case "vusefr":
                try{
                waitForExpectedElement(GLOBAL_SUBS_OPTION_PDPVUSE,15);
                waitForItemToBeClickableAndClick(GLOBAL_SUBS_OPTION_PDPVUSE,5);}
                catch (Exception e){
                    waitForItemToBeClickableAndClick(GLOBAL_SUBS_OPTION_PDP_FR,5);}
                break;
            default:
                waitForExpectedElement(GLOBAL_SUBS_OPTION_PDPVUSE,15);
                clickByElementByQueryJSExecutor(GLOBAL_SUBS_OPTION_PDPVUSE);
        }
    }

    public void selectIntervalFromDropDownGlobalSubs(int index) {
        selectValueFromDropDownByIndex(index, GLOBAL_SUBS_INTERVAL_DROPDOWN);
    }

    public void globalSubsErrorAssert(String errorMessage) {
        Assert.assertEquals(errorMessage, waitForExpectedElement(GLOBAL_SUBS_PDP_ERROR_MESSAGE).getText().replaceAll("[^A-Za-z0-9]", ""));
    }

    public void globalSubsErrorAssertNoStringFilter(String errorMessage) {
        switch(UrlBuilder.getLocale()){
            case "vuseco":
                Assert.assertEquals(errorMessage, waitForExpectedElement(GLOBAL_SUBS_ERROR_MESSAGE).getText());
                break;
            case "vuseit" :
                Assert.assertTrue(waitForExpectedElement(GLOBAL_SUBS_PDP_ERROR_MESSAGE_VUSEIT).getText().contains(errorMessage));
                break;
            default:
                Assert.assertEquals(errorMessage, waitForExpectedElement(GLOBAL_SUBS_PDP_ERROR_MESSAGE).getText());
                break;
        }
    }

    public void verifyBasketSectionWithItemDetailsTableContent(ArrayList<String> titles) throws InterruptedException {
        ArrayList<String> tableHeadersWeb = new ArrayList();
        jsScrollElementInCenter(waitForExpectedElement(CART_PAGE_TOTALS));
        Thread.sleep(5000);
        waitForExpectedElement(CART_PAGE_TOTALS);
        for (WebElement title : getTableHeaders(CART_PAGE_TOTALS)) {
            String t = title.getText().replaceAll("\\n", "");
            tableHeadersWeb.add(t);
        }
        tableHeadersWeb.removeAll(Arrays.asList("", null));
        Collections.sort(titles);
        Collections.sort(tableHeadersWeb);
        Assert.assertEquals(titles, tableHeadersWeb);
    }

    public void assertIntervalMatchesGlobalSubs(String intervalChosen) {
        Assert.assertEquals(intervalChosen, waitForExpectedElement(GLOBAL_SUBS_CART_INTEVAL_DROPDOWN).getText());
    }

    public void increaseQuantityOfItemInCart(int qty) throws Throwable {
        jsScrollElementInCenter(waitForExpectedElement(GLOBAL_SUBS_IMMEDIATE_PURCHASE_OPTION));
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSECO:
            case VUSEIT:
            case VUSEDE:
            basketPage.enterItemQuantityCart(Integer.toString(qty));
            break;
            case VUSEUK:
            case VUSEFR:
                List<WebElement> quantityDropdowns = webDriver.findElements(QUANTITY_DROPDOWN);
                String currentQuantity = quantityDropdowns.get(0).getText();
                String newQuantity = String.valueOf(Integer.parseInt(currentQuantity) + qty);
                waitForExpectedElement(CURRENTLY_DISPLAYED_DROPDOWN_VALUE).click();
                List <WebElement> quantities = quantityDropdowns.get(0).findElements(ALL_DROPDOWN_VALUES);
                for (WebElement quantity: quantities) {
                    if (quantity.getText().trim().equals(newQuantity)) {
                        quantity.click();
                        break;
                    }
                }
                break;
        default:
            for (int i = 0; i < qty; i++) {
                Thread.sleep(6000);
                waitForExpectedElement(CART_BTN_INCREASE_ITEM_QTY, 5).click();
                switch (UrlBuilder.getLocale()) {
                    case "vuseco":
                        invisibilityOfElementLocated(LOADING_CIRCLE_VUSE_CO);
                        break;
                    case "vusefr":
                        invisibilityOfElementLocated(LOADING_CIRCLE_VUSE_FR);
                        break;
                    case "vusede":
                        invisibilityOfElementLocated(LOADING_CIRCLE_VUSE_DE);
                        break;
                    case "lyftse":
                        invisibilityOfElementLocated(LOADING_CIRCLE);
                        break;
                }
            }
        }
    }

    public void decreaseQuantityOfItemInCart(int qty) throws Throwable {
        jsScrollElementInCenter(waitForExpectedElement(GLOBAL_SUBS_IMMEDIATE_PURCHASE_OPTION));
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
        case VUSECO:
            List<WebElement> product = getWebDriver().findElements(basketPage.QUANTITIES_IN_BASKET_PAGE);
            for(int j=0;j<product.size();j++) {
                int quantity = Integer.parseInt(product.get(j).getText());
                if (quantity > 1) {
                    basketPage.enterItemQuantityCart(Integer.toString(quantity-1));
                    break;
                }
            }
            break;
            case VUSEIT:
                basketPage.enterItemQuantityCart(Integer.toString(qty));
                break;
            case VUSEFR:
                List<WebElement> quantityDropdowns = webDriver.findElements(QUANTITY_DROPDOWN);
                String currentQuantity = quantityDropdowns.get(0).getText();
                String newQuantity = String.valueOf(Integer.parseInt(currentQuantity) - qty);
                waitForExpectedElement(CURRENTLY_DISPLAYED_DROPDOWN_VALUE).click();
                List <WebElement> quantities = quantityDropdowns.get(0).findElements(ALL_DROPDOWN_VALUES);
                for (WebElement quantity: quantities) {
                    if (quantity.getText().trim().equals(newQuantity)) {
                        quantity.click();
                        break;
                    }
                }
                break;
        default:
            for (int i = 0; i < qty; i++) {
                Thread.sleep(3000);
                waitForExpectedElement(CART_BTN_DECREASE_ITEM_QTY).click();
                switch (UrlBuilder.getLocale()) {
                    case "vuseco":
                        invisibilityOfElementLocated(LOADING_CIRCLE_VUSE_CO);
                        break;
                    case "vusefr":
                        invisibilityOfElementLocated(LOADING_CIRCLE_VUSE_FR);
                        break;
                    case "vusede":
                        invisibilityOfElementLocated(LOADING_CIRCLE_VUSE_DE);
                        break;
                    case "lyftse":
                        invisibilityOfElementLocated(LOADING_CIRCLE);
                        break;
                }
            }
        }
    }

    public void selectImmediatePurchaseOptionCart() {
        waitForExpectedElement(GLOBAL_SUBS_IMMEDIATE_PURCHASE_OPTION).click();
        invisibilityOfElementLocated(LOADING_CIRCLE);
    }

    public void selectSubscribeAndSaveOptionCart() {
        waitForExpectedElement(GLOBAL_SUBS_SUBSCRIBE_AND_SAVE_OPTION,30);
        waitForExpectedElement(GLOBAL_SUBS_SUBSCRIBE_AND_SAVE_OPTION,30).click();
    }

    public void addSubsAccessoryViaCart() {
        waitForExpectedElement(GLOBAL_SUBS_ADD_ACCESSORY);
        waitForExpectedElement(GLOBAL_SUBS_ADD_ACCESSORY).click();
//        waitForExpectedElement(GLOBAL_SUBS_ADD_ACCESSORY_COLOUR).click();
        waitForExpectedElement(GLOBAL_SUBS_ADD_ACCESSORY_ADD_ITEM).click();
    }

    public void checkErrorMessage(String errorMessage){
        clickUsingJS(pdp.PDP_ADDTOCART_BUTTON);
        waitForAjaxElementNotToBePresent(getWebDriver(),15);
        if(errorMessage.contains("1Subscription")){
            Assert.assertEquals(waitForExpectedElement(GLOBAL_SUBS_PDP_HEADER_ERROR_MESSAGE, 10).getText(), UrlBuilder.getMessage(errorMessage));
        }else {
            Assert.assertEquals(waitForExpectedElement(GLOBAL_SUBS_PDP_ERROR_MESSAGE, 10).getText(), UrlBuilder.getMessage(errorMessage));
        }
    }

    public void clickCancelSubscription() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEUK:
            case VUSEFR:
                waitAndClickByElementByJSExecutor(VUSE_SUBS_CANCEL_BUTTON, 10);
                break;
            default:
                waitForExpectedElement(GLO_SUBS_CANCEL).click();
        }
    }

    public void verifyCancellationSurveyDisplay() {
        Assert.assertTrue(waitForExpectedElement(GLO_CANCEL_SURVEY, 4).isDisplayed());
    }

    public void checkDropown() {
        Assert.assertTrue(waitForExpectedElement(GLO_CANCEL_SURVEY_DROPDOWN, 4).isDisplayed());
    }

    public void checkTextAreaNotDisplayed() {
        Assert.assertFalse(waitForExpectedElement(GLO_CANCEL_SURVEY_TEXTAREA, 4).isDisplayed());
    }

    public void clickOneTimePurchase() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad() || UrlBuilder.isTablet()) {
            switch (UrlBuilder.getLocale()) {
                case "vuseuk":
                    waitForExpectedElement(allonetimepurchase, 5).click();
                    waitForExpectedElement(confirmchange, 10).click();
                    break;
            }
        }
    }

    public void clickSubscribeAll() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad() || UrlBuilder.isTablet()) {
            switch (UrlBuilder.getLocale()) {
                case "vuseuk":
                    waitForExpectedElement(subscribeAll, 5).click();
                    waitForExpectedElement(confirmchange, 10).click();
                    break;
            }
        }
    }

    public void viewSubsOption() {
        Assert.assertTrue(waitForExpectedElement(allonetimepurchase, 5).isDisplayed());
        Assert.assertTrue(waitForExpectedElement(subscriptionfirst, 10).isDisplayed());

    }

    public void clickAllOneTimePurchase() {
        waitForExpectedElement(allonetimepurchase, 5).click();
        waitForExpectedElement(confirmchange, 10).click();
    }

    public void viewOneTimePurchaseSelected() {
        Assert.assertTrue(waitForExpectedElement(onetimepurchase, 5).isDisplayed());
    }

    public void clickSubscriptioFfirst() {
        waitForExpectedElement(subscriptionfirst, 5).click();
    }
    public void assertSubscriptionProductAdded() {
        Assert.assertTrue(waitForExpectedElement(subscriptiontotal, 5).isDisplayed());
    }

    public void assertSubMessageDisplayed() {
        Assert.assertTrue(waitForExpectedElement(oneTimePurchase, 5).isDisplayed());
        Assert.assertTrue(waitForExpectedElement(subscribeAll, 5).isDisplayed());
    }

    public void assertSubMessageNotDisplayed() {
        assertFalse(isElementDisplayedBySeconds(oneTimePurchase,5));
        assertFalse(isElementDisplayedBySeconds(subscribeAll,5));
    }

    public void toggleToSubscription() {
        waitForExpectedElement(selectsubscription, 5).click();
    }

    public boolean isSubMessagePresent() {
        return isElementPresent(SUB_MESSAGE_VUSE_DE, 10);
    }

    public boolean isIIconPresent() {
        return isElementPresent(I_ICON, 10);
    }

    /**
     * add a product which has intensity option in update subscription page.
     * @param intensityNumber how many intensity, the valid number is 1,2,3
     */
    public void addProductHasIntensity(Integer intensityNumber ) throws Exception {
        if(intensityNumber<1 ||intensityNumber>3){
            throw new IllegalArgumentException("please inout the valid number, the number should be 1,2 or 3.");
        }
        Boolean flag=false;
        String rootDiv="(//*[@class='item-bottom-wrapper'])";
        String middelLeft="[";
        String button="]/div/button";
        String strength="]/div[1]/div[5]/div/div/div";

        List<WebElement> rootDivsEle=webDriver.findElements(By.xpath(rootDiv));
        for(int i=0 ;i<rootDivsEle.size();i++){
            String fullStrength=rootDiv+middelLeft+(i+1)+strength;
            List<WebElement> strengthElements=webDriver.findElements(By.xpath(fullStrength));
            if(strengthElements.size()>=intensityNumber){
                int n=new Random().nextInt(strengthElements.size());
                strengthElements.get(n).click();
                String fullButton=rootDiv+middelLeft+(i+1)+button;
                WebElement buttonEle=webDriver.findElement(By.xpath(fullButton));
                buttonEle.click();
                flag=true;
                break;
            }
        }
        if(!flag){
            throw new Exception("There is no product which has "+intensityNumber+" intensity option,please check the product.");
        }
    }

    public void setCurrentTier(){
        String orderNumber = "";
        By tierCss = null;
        switch(UrlBuilder.getLocale()){
            case "lyftse":
                currentTier = waitForExpectedElement(GLO_SUBS_CURRENT_TIER_LYFTSE).getText();
                break;
            case "vusefr":
                orderNumber = getOrderNumber(0,ORDER_NUMBERVUSEFR);
                tierCss = By.cssSelector("[class='subscription-tier-label "+orderNumber+" bronze'] .text");
                currentTier = waitForExpectedElement(tierCss).getText();
                break;
            case "vusede":
                orderNumber = getOrderNumber(0,ORDER_NUMBERVUSEDE);
                tierCss = By.cssSelector("[class='subscription-tier-label "+orderNumber+" bronze'] .text");
                try{
                    currentTier = waitForExpectedElement(tierCss).getText();
                }catch(TimeoutException te){
                    tierCss = By.cssSelector("[class='subscription-tier-label "+orderNumber+" silber'] .text");
                    currentTier = waitForExpectedElement(tierCss).getText();
                }
                break;
        }
        assertNotEquals(UrlBuilder.getMessage("GoldTier.key"), currentTier, "If this assertion fails, please go to the subscription manually and update the subscription to a lower tier");
    }

    public void clickUpdateSubscriptionButton() { waitForExpectedElement(GLO_SUBS_UPDATE_CTA_LYFTSE).click();}

    public void clickBinToRemoveFirstSubscription() { waitForExpectedElements(REMOVE_SUBSCRIPTION_LYFTSE).get(0).click();}

    public void clickCancelSubscriptionButtonOnModifySubscriptionModule() { waitForExpectedElement(GLO_SUBS_CLOSE_CTA).click();}

    public boolean updateSubscriptionButtonOnModifySubscriptionModuleIsDisabled() {
        return !waitForExpectedElement(GLO_SUBS_CONFIRM_UPDATE_CTA).isEnabled();
    }

    public void modifyTheSubscriptionQty(String modifier, int qty) throws InterruptedException{
        int cq=setQtyOfSubscription();
        if(UrlBuilder.getLocale().equalsIgnoreCase("lyftse")){
            waitForExpectedElement(GLO_SUBS_UPDATE_CTA_LYFTSE).click();
        }else{
            waitForExpectedElement(GLO_SUBS_UPDATE_CTA).click();
        }
        if(modifier.equals("increase")){
            for(int i=qty;i>0;i--){
                try {
                    waitForExpectedElement(GLO_SUBS_INCREASE_CTA).click();
                }catch(Exception e){
                    waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
                    waitForExpectedElement(GLO_SUBS_INCREASE_CTA).click();
                }
            }
        }else{
            for(int i=qty;i>0;i--){
                try {
                    waitForExpectedElement(GLO_SUBS_DECREASE_CTA).click();
                }catch(Exception e){
                    waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
                    jsScrollElementInCenter(waitForExpectedElement(GLO_SUBS_DECREASE_CTA));
                    waitForExpectedElement(GLO_SUBS_DECREASE_CTA).click();
                }
            }
        }try {
            waitForExpectedElement(GLO_SUBS_UPDATE_CLOSE_NOTIFICATION_CTA).click();
        }catch(Exception e){
            LOG.info("No Notification popup occurred");
        }
    }

    public void clickCancleSubscriptionArrowIcon() {
        waitForExpectedElement(CANCEL_SUBS_ARROW_ICON, 20);
        clickUsingJS(CANCEL_SUBS_ARROW_ICON);
    }

    public void clickStopSubscriptionButton() {
        clickIndexElementByQueryJSExecutor(STOP_CTA_GLOBAL_SUBS_CONFIRM, 10);
    }

    public boolean isRestartButtonPresent() {
        return isElementDisplayedBySeconds(CONFIRM_RESTART_CTA_GLOBAL_SUBS, 5);
    }

    public void removeSpecificItemFromCart(int itemPosition){
        List<WebElement> items = getWebDriver().findElements(CART_ITEM_DELETE);
        List<WebElement> itemsConfirm = getWebDriver().findElements(CART_ITEM_DELETE_CONFIRM);
        items.get(itemPosition).click();
        itemsConfirm.get(itemPosition).click();
    }

    public void clickOnLearnMoreButtonUnderMyAccountSubscriptionsAndAssertCTA() {
        waitAndClickByElementByJSExecutor(MY_SUBS_LEARN_MORE_BUTTON,10);
        waitForURLToContain("subscriptions",10);
        assertTrue(doesURLContain("subscriptions"));
    }

    public void updateAddressUsingLoqateForGlobalSubs() throws InterruptedException {
        assertTrue(waitForExpectedElement(paymentPage.NEW_ADDRESS_MODAL_TITLE,10).getText().toLowerCase().contains(UrlBuilder.getMessage("editAddress.key").toLowerCase()));
        assertTrue(waitForExpectedElement(accountDashboardPage.ADD_ADDRESS_MANUALLY).isDisplayed());
        if(UrlBuilder.getLocale().equalsIgnoreCase("vuseuk"))
            waitClearAndEnterText(ADDRESS_GLO_SUBS_NATIVE_TELEPHONEVYPEIT,"7343534534");
        else
            waitClearAndEnterText(ADDRESS_GLO_SUBS_NATIVE_TELEPHONE_VUSEFR,"+336789021345");
        loqateAddressLookUpPage.startEnteringAddressFR(UrlBuilder.getMessage("subsAddressFinder.key"),"");
        loqateAddressLookUpPage.clickFirstOptionIfPresent(UrlBuilder.getMessage("subsSelectAddress.key"));
        waitForExpectedElement(ADDRESS_GLO_SUBS_NATIVE_CONFIRMATION).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        assertTrue(waitForExpectedElement(ADDRESS_GLO_SUBS_NATIVE_FR,10).getText().contains(UrlBuilder.getMessage("subsAddressSelected.key")));
    }

    public void selectChangeAddressOrPaymentMethodCheckboxAndThenClickUpdateAddressButton() throws InterruptedException {
        waitAndClickByElementByJSExecutor(SUBS_CANCEL_REASON_CHANGE_ADDRESS,10);
        waitAndClickByElementByJSExecutor(SUBS_CANCELLATION_SURVEY_FIRST_PAGE_UNSUBSCRIBE_CTA,5);
        waitAndClickByElementByJSExecutor(SUBS_UPDATE_ADDRESS_BUTTON,10);
    }

    public void checkErrorMessageForPromoCode(String errorMessage){
        String actualErrorMessage = waitForExpectedElement(GLOBAL_SUBS_PROMOCODE_ERROR,10).getText();
        assertEquals(actualErrorMessage,(UrlBuilder.getMessage(errorMessage)));
    }
}
