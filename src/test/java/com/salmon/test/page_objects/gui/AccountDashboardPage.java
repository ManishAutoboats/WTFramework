package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.gloIt.GloItCheckoutPage;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import java.util.*;

import static com.salmon.test.page_objects.gui.AddNewAddressPage.TELEPHONE_INPUT;
import static com.salmon.test.page_objects.gui.gloIt.GloItHomePage.PERSON_ICON_PL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.*;

public class AccountDashboardPage extends PageObject {
    private ScenarioContext scenarioContext;

    public AccountDashboardPage(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    //a hashmap to capture User's successful deletion message in key-value pair
    public static HashMap<String, String> strSuccessUserDeletion = new HashMap<String, String>();

    // ELEMENT MAPPING
    // Text
    public By pageHeading = By.cssSelector("span.base"); // main heading
    public By registeringConfirmationMsg = By.cssSelector("div.message-success.success.message");

    // main links
    private static final By HOW_DOES_IT_WORKS_LINK = By.cssSelector("a.icons.info-icon");
    private static final By PRINT_ORDER_LINK=By.cssSelector("a.action.print > span");

    // DELETE MY ACCOUNT
    public By lnkDeleteMXAccount = By.linkText("ELIMINAR MI CUENTA");
    public static final By M_DELETE_MY_ACCOUNT_CO = By.cssSelector("div#account-nav li:nth-child(6) > a");
    public final static By deleteMyAccountLinkByHref = By.cssSelector("a[href*='deletemyaccount");
    public final static By DELETE_MY_ACCOUNT_SUB_PAGE_HEADING = By.cssSelector("#maincontent > div > div.column.main > div.page-title-wrapper > h1 > span");
    public final By DeleteMyAccountText = By.cssSelector("div.delete-warning > p:nth-child(1)");
    public final By DeleteMyAccountIUnderstandTheRisksWarningText = By.cssSelector("div.field.choice.confirm.required label.label.radio-inline > span:nth-child(1)");
    public final static By DELETE_MYACCOUNT_TICKBOX = By.cssSelector("div.field.choice.confirm.required > label.label.radio-inline");
    public final static By DELETE_MYACCOUNT_TICKBOXCO_SELECTED = By.cssSelector("fieldset > input.checkbox");
    public final static By DELETE_MYACCOUNT_TICKBOXCO_SELECT = By.cssSelector("label.field.choice.confirm.required.radiocontainer");
    public final static By M_MYACCOUNT_ACCORDION_UK=By.cssSelector("div.accordion-header span");
    public final static By M_DELETE_MY_ACCOUNT_TICKBOX_CO = By.cssSelector("label.field.choice.confirm.required.radiocontainer");
    public final static By DELETE_MYACCOUNT_TICKBOX_VELO = By.cssSelector("#maincontent > div.columns > div > form > fieldset > label > span");
    public final static By DELETE_MYACCOUNT_BUTTON = By.cssSelector("button.action.submit.primary");
    public final static By DELETE_MYACCOUNT_DROPDOWN = By.cssSelector("div.userLoggin > div > div > div > ul > li:nth-child(5) > a");
    public By lnkDeleteMyAccount = By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key"));
    public By deleteMyAccountButtonGLO = By.cssSelector("form.form.delete-account div.actions-toolbar div.primary button.action.submit.primary > span:nth-child(1)");
    public final static By T_MY_ACCOUNT_FR = By.cssSelector("body > div.page-wrapper > header > div.userbar.userbar-top.clearfix > div.userbar-right > ul > li.userbar-item.customer > span > i.material-icons-outlined");
    public static final By T_LOGIN_REGISTER_FR = By.cssSelector("body > div.page-wrapper > header > div.userbar.userbar-top.clearfix > div.userbar-right > ul > li.userbar-item.customer.active > div > ul > li.customer-name > a > span");
    public static final By T_PERSON_ICON_IE = By.cssSelector("body > div.page-wrapper > header > div > div.column.actions-primary.desktop-only.row > div:nth-child(1) > a > i");
    public static final By T_HAMBURGER_UK = By.cssSelector("div.action.nav-toggle > span");
    public static final By T_HAMBURGER_MY_ACCOUNT1_UK = By.cssSelector("li.my-account > div.burger-menu-item.top-category > a");
    public static final By T_HAMBURGER_MY_ACCOUNT2_UK = By.cssSelector("li.my-account > div.sub-level > div:nth-child(2) > a");
    public static final By T_MY_ACCOUNT3_UK = By.cssSelector("div#account-links-collapsible span");
    public static final By T_DELETE_ACCOUNT_UK = By.cssSelector("div#account-nav li > a[href*='/gb/en/deletemyaccount']");
    public static final By M_DELETE_ACCOUNT_UK = By.xpath("//a[contains(@href,'deletemyaccount')]");
    private static final By M_DELETE_ACCOUNT_LINK_ZA = By.cssSelector("ul.nav.items li.nav.item:nth-child(6) > a:nth-child(1)");
    private static final By DELETE_ACCOUNT_LINK_VUSE_UK = By.cssSelector("[href*='deletemyaccount']");
    public final static By MYACCOUNT_EXPAND_IPAD = By.cssSelector("div.accordion-header>div");
    public final static By INPUT_SEARCH_LOCATION = By.cssSelector("#pac-input");
    public final static By STORE_LOCATOR_SEARCH_LOCATION = By.cssSelector(".mapboxgl-ctrl-geocoder--input");
    public final static By COUPON_APPLIED_MESSAGE = By.cssSelector("div.message.message-notice.notice");
    public final static By STORE_LOCATOR_ADDRESS_LOOKUP = By.cssSelector("input.mapboxgl-ctrl-geocoder--input");
    private final static By KZ_MYACCOUNT_MOREOPTION = By.cssSelector("#account-links-collapsible > div.accordion-header > div");
    private final static By KZ_DELETEONMORE = By.cssSelector("#account-nav > ul > li:nth-child(6) > a");
    private static final By REMOVE_ADDRESS_BUTTON=By.cssSelector("button.action.secondary.delete > span");
    private static final By REMOVE_ADDRESS_CANCEL_BUTTON=By.cssSelector("footer > button.action-secondary.action-dismiss");
    private static final By REMOVE_ADDRESS_OK_BUTTON=By.cssSelector("footer > button.action-primary.action-accept");
    private static final By STREET_LINE_CONTENT=By.cssSelector("div.block-content > div > div.address-container > span:nth-child(2)");
    private static final By HOME_PAGE_BANNER=By.cssSelector("div.iso-banner-inner");

    // MARKETING PREFERENCES
    public By updatedSuccessfullyMessage = By.cssSelector("div.message-success.success.message");
    public By marketingPreferences = By.linkText("Marketing Preferences");
    public By marketingPreferencesHeading = By.cssSelector("#maincontent > div > div.column.main > div.page-title-wrapper > h1 > span");
    public By newsLetterTickBox = By.cssSelector("input#subscription.checkbox");
    public By consentToEmailMarketingTickBox = By.cssSelector("input#consent_email_marketing");
    public By consentToMobileMarketingTickBox = By.cssSelector("input#consent_mobile");
    public By consentToWhiteMailMarketingTickBox = By.cssSelector("input#consent_whitemail");
    public By consentToBatContactForTrialFeedbackTickBox = By.cssSelector("input#consent_trial_feedback");
    public By consentToBatContactingForMarketingResearchPurposesTickBox = By.cssSelector("input#consent_market_research");
    public By consentToSharingDataWithThirdPartiesTickBox = By.cssSelector("input#consent_third_parties");
    public By consentToStoringDataForMarketingPurposesTickBox = By.cssSelector("input#consent_store_my_data");
    public By savePreferencesButton = By.cssSelector("div.primary");
    public By saveCardDetails = By.cssSelector("button.action.save.secondary"); //worldpay_cc_save_card
    public By myOrdersTable = By.cssSelector("#my-orders-table");
    public By mySubscriptionTable = By.cssSelector("#my-subscriptions-table");
    public By saveButton = By.cssSelector("button.action.save.primary");
    public By lnkPDFLink = By.cssSelector("#device-registration-form > fieldset > div.field.serial-code.required > div > a");
    public By ReOrderLink= By.cssSelector(".action.order");

    public final static By LINK_VIEW_ORDER_MX= By.cssSelector("#my-orders-table > tbody > tr > td.col.actions > div > a");
    public By lnkReOrderMX= By.cssSelector("div.actions > a.action.order:nth-child(1)");
    private final By orderStatus=By.cssSelector("div.page-title-wrapper span.order-status");
    public static final By lnkViewOrderJP =By.cssSelector("tr:nth-child(1)>td>div.actions-wrapper>a.view.action-orders");

    //User Profile Image and Banner
    public By lnkEditProfileImage = By.cssSelector("div.box-actions a.action.edit > span:nth-child(1)");
    public By btnUploadImage = By.cssSelector("i.material-icons.profile-upload");
    public By eleProfileImage = By.cssSelector("div.profile-avatar:nth-child(1) > img.profile-pic");
    public By eleProfileImageBanner = By.cssSelector("a.my-account-dash div.user-img > img.profile-pic");
    private static final By FULL_USER_NAME_REGION =  By.cssSelector("a.my-account-dash");

    public By eleFirstNameValue = By.cssSelector("div.field.field-name-firstname:nth-child(1) > span.value");
    public By eleLastNameValue = By.cssSelector("div.field.field-name-lastname:nth-child(2) > span.value");
    public By eleUserNameAccDashboard = By.cssSelector("div.greeting");
    private static final By EMAIL_ADDRESS = By.cssSelector(".field-name-email .value");
    private static final By BIRTH_DATE = By.cssSelector(".field-name-dob .value");
    private static final By GREETING = By.cssSelector(".box-profile div.greeting");
    public static final By MY_ACCOUNT_LINKS = By.cssSelector("span.icon-account");
    public static final By MY_ACCOUNT_DROPDOWN_LINKS = By.cssSelector("div.userLoggin div div div ul li:nth-child(1) a");

    //Need a Hand Section
    public By eleNeedHandSectionHeader = By.cssSelector("div:nth-child(1) div:nth-child(1) h1:nth-child(1) > span:nth-child(1)");
    public By eleNeedHandSectionText = By.xpath("//*[text()='NEED A HAND?']//following::span[1]");

    //Store Locator
    public By btnRoute = By.xpath("//*[@class='result-list']//following::button[1]");
    public By txtDestinationSearchBox = By.cssSelector("div.searchbox div.gstl_51.sbib_a div.sbib_b > input.tactile-searchbox-input");

    //Stored Payment Methods
    public By elePaymentMethodsSection = By.cssSelector("div.data.table-credit-cards");
    public By eleCardNumberField = By.cssSelector("div.col.card-number:nth-child(1)");
    public By eleExpirationDateField = By.cssSelector("div.col.card-expire:nth-child(2)");
    public By btnRemovePaymentMethod = By.cssSelector("button.action.delete");
    public By btnCancelDeletion = By.cssSelector("button.action.secondary.cancel");
    public By btnDeleteMethod = By.cssSelector("button.action.primary:nth-child(2) > span:nth-child(1)");

    //Favorites
    public By productWishlistSectionMyAccount = By.cssSelector("div.products-grid.wishlist");
    public By btnAddToBasketMyAccount = By.cssSelector("button.action.tocart.secondary.desktop_cart:nth-child(1) > span:nth-child(1)");
    public By btnAddToBasketFavoritesPage = By.cssSelector("button.action.tocart.secondary > span:nth-child(1)");
    public By eleProductItemsMyFavorites = By.cssSelector("strong.product-item-name");
    public By btnEditSavedCard = By.cssSelector("a.action.edit:nth-child(1) > span:nth-child(1)");
    public final static By BTN_REMOVE_SAVED_CARD_FR = By.cssSelector("#my-savecards-table > tbody > tr > td:nth-child(7) > a");
    public By btnRemoveSavedCard = By.cssSelector("div.col.card-saved div.actions a.action.delete:nth-child(2) > span:nth-child(1)");
    public By vypeCXbtnRemoveSasvedCard = By.cssSelector("#maincontent > div > div.column.main > div.table-wrapper.saved-cards > div > div > div > div.actions > a");
    public By txtCardNameSavedCard = By.cssSelector("[title='Card Holder Name']");
    public final static By BTN_UPDATE_SAVED_CARD = By.cssSelector("button.action.save.secondary:nth-child(2) > span:nth-child(1)");
    public By btnCancelSavedCardUpdates = By.cssSelector("button.action.cancel.primary:nth-child(1) > span:nth-child(1)");
    public By logout = By.linkText("Logout");
    public final static By REMOVE_SAVED_CARDS_VYPEIT=By.cssSelector("a.action.delete");

    // Order
    public static final By ORDER_DEVICE_TRIAL_TABLE = By.cssSelector("#devicetrial_order_details");
    public static final By ORDER_HISTORY_BUTTON_GLO = By.cssSelector("#account-nav > ul > li:nth-child(8) > a,div.userLoggin > div > div > div > ul > li:nth-child(4) > a");
    public static final By LNK_ORDER_VIEW = By.cssSelector("div.orders-history table td.col.actions a.action.view,div.actions-wrapper > a");
    // links
    public By Edit = By.linkText("Edit");

    // Edit my Account Information Page
    public By firstNameField = By.cssSelector("#firstname");
    private static final By CUSTOMER_FIRST_NAME_READ_ONLY = By.xpath("//input[@id='firstname'][@readonly]");
    private static final By CUSTOMER_LAST_NAME_READ_ONLY = By.xpath("//input[@id='lastname'][@readonly]");
    private static final By CHANGE_EMAIL_READ_ONLY = By.xpath("//input[@id='change-email'][@disabled]");
    public static final By CHECKOUT_FIRST_NAME_READ_ONLY = By.cssSelector("div[class^='fieldset address'] div.field._required._disabled:nth-child(1) > div.control > input");
    public static final By CHECKOUT_LAST_NAME_READ_ONLY = By.cssSelector("div[class^='fieldset address'] div.field._required._disabled:nth-child(2) > div.control > input");
    public static final By VIEW_NOTICE_CMS_BLOCK=By.cssSelector("div.message.info:nth-child(1)");
    private static final By ADD_NEW_ADDRESS_BUTTON=By.cssSelector("button.action.primary.add > span:nth-child(1)");
    private static final By LYFT_ACCOUNT_FIRST_NAME=By.cssSelector("div.user-details-container.firstname:nth-child(1) > p.user-details");
    private static final By LYFT_ACCOUNT_LAST_NAME=By.cssSelector("div.user-details-container.surname:nth-child(2) > p.user-details");
    private static final By GLO_ACCOUNT_USER_NAME=By.cssSelector("p.account-info__name,div.box.box-information:nth-child(1) > div.box-content > p,p.account-info__name");
    private static final By UK_ACCOUNT_USER_NAME=By.cssSelector("div.account-info__container > p.account-info__name");
    private static final By ACCOUNT_USER_FIRST_NAME=By.cssSelector("div.field.field-name-firstname:nth-child(1) > span.value");
    private static final By ACCOUNT_USER_LAST_NAME=By.cssSelector("div.field.field-name-lastname:nth-child(2) > span.value");
    private static final By EDIT_ACCOUNT_BUTTON=By.cssSelector("div.editBtn>a.action.edit");
    private static final By EDIT_ACCOUNT_BUTTON_MXCX = By.cssSelector(".account-info__container  a.action.primary");
    private static final By MYACCOUNT_PAYMENT_LINK=By.xpath("//ul[@class='nav items']//following::a[text()='Betaling']");
    private static final By SAVED_CARDS_GRID=By.cssSelector("div.table-wrapper.saved-cards");
    private static final By WELCOME_MESSAGE = By.cssSelector(".welcome-message");
    private static final By ACCOUNT_EMAIL = By.cssSelector(".account-info__email");
    private static final By BILLING_ADDRESS_REGION = By.cssSelector(".box-billing-address .box-content");
    private static final By SHIPPING_ADDRESS_REGION = By.cssSelector(".box-shipping-address");
    private static final By CONTACT_INFO = By.cssSelector(".box-information .box-content");
    private static final By PAGE_TITLE = By.cssSelector(".page-title .base");
    private static final By ADDRESS_BOOK_BILLING_ADDRESS = By.cssSelector(".box-address-billing .box-content");
    private static final By ADDRESS_BOOK_SHIPPING_ADDRESS = By.cssSelector(".box-address-shipping");
    private static final By ADDRESS_BOOK_LINK = By.cssSelector(".nav a[href*='/address']");
    private static final By NEWSLETTER_LINK = By.cssSelector(".nav a[href*='/newsletter/manage']");
    private static final By SUBSCRIBE_NEWSLETTER_BUTTON_LYFT_SE = By.cssSelector(".columns .pagebuilder-button-primary.button");
    private static final By LOGO_LYFT_SE = By.cssSelector(".column.logo-container a");
    private static final By MY_SUBSCRIPTION_LINK = By.cssSelector(".nav a[href*='/subscriptions/']");
    private static final By MY_DETAILS_LINK = By.cssSelector(".nav a[href*='/edit/']");
    private static final By INSIDER_CLUB_LINK = By.cssSelector("[href*='/it/it/insiders-club/benefit/']");
    private static final By CLOSE_INSIDER_CLUB_BUTTON = By.cssSelector(".modals-wrapper .modal-header .action-close");
    private static final By MARKETING_PREFERENCE_LINK = By.cssSelector(".nav a[href*='newsletter/manage/']");
    private static final By ORDER_HISTORY_LINK = By.cssSelector(".nav a[href*='/sales/order/history']");
    private static final By MY_ACCOUNT_LINK_GLO_PL = By.cssSelector(".items a[href*='customer/account/']");
    private static final By MY_ACCOUNT_INSIDERS_CLUB_LINK_VUSE_DE = By.cssSelector("#account-nav ul li:nth-child(2) a");
    private static final By MY_ACCOUNT_DELETE_ACCOUNT_LINK_VUSE_DE = By.cssSelector("#account-nav ul li:nth-child(10) a");
    private static final By MY_ACCOUNT_DELETE_ACCOUNT_LINK_VUSE_IT = By.cssSelector("#account-nav ul li:nth-child(9) a");
    private static final By MY_ACCOUNT_DELETE_ACCOUNT_LINK_VUSE_ZA = By.cssSelector("#account-nav > ul > li:nth-child(7) > a");
    private static final By MY_ACCOUNT_DIRECTORY_LINK_VUSE_DE = By.cssSelector("#account-nav ul li:nth-child(6) a");
    private static final By MY_ACCOUNT_EDIT_BILLING_ADDRESS_LINK_VUSE_DE = By.cssSelector("#edit_billing");
    private static final By MY_ACCOUNT_CLOSE_EDIT_ADDRESS_BUTTON_VUSE_DE = By.cssSelector(".modal-slide._inner-scroll._show div.modal-inner-wrap header button");
    private static final By MY_ACCOUNT_LINK_GLO_DE = By.cssSelector("li.nav.item:nth-child(2)>a");
    private static final By MY_ACCOUNT_LINK_VUSE_KZ = By.cssSelector("#account-nav > ul > li:nth-child(1) > a");
    private static final By MY_ACCOUNT_LINK_VELO_PL = By.cssSelector("#account-nav > ul > li:nth-child(2) > a");
    private static final By MY_ACCOUNT_TOBACCO_HEATER_LINK = By.cssSelector(".nav.item a[href*='glo_device/customer/devices']");
    private static final By GLO_PL_ORDER_HISTORY_LINK = By.cssSelector("a.button[href*='/sales/order/history']");
    private static final By VUSE_DK_ORDER_HISTORY_LINK = By.cssSelector(".nav.items li:nth-child(2)");
    private static final By HISTORIC_ORDERS_LINK = By.cssSelector(".nav a[href*='/legacy_sales/order/history']");
    private static final By SAVED_CARD_LINK = By.cssSelector(".nav a[href*='/worldpay/savedcard']");
    private static final By DEVICE_REGISTRATION_LINK = By.cssSelector(".nav a[href*='/register_device/customer/index/'], .nav a[href*='/glo_device/customer/index/']");
    private static final By REFER_A_FRIEND_LINK = By.cssSelector(".nav a[href*='/invitation'], .nav a[href*='/raf/referafriend'],.nav a[href*='invite/customer/index/']");
    private static final By REFER_A_FRIEND_LINK_GLO_IT = By.cssSelector("[href*='it/it/customer/mgm/']");
    private static final By FAVORITES_LINK = By.cssSelector(".nav a[href*='/wishlist']");
    private static final By GLO_DEVICE_LINK = By.cssSelector(".nav a[href*='/glo_device/customer/devices']");
    private static final By HISTORY_ORDER_NUMBER = By.cssSelector(".table-body .col.id, tbody .col.id");
    private static final By HISTORY_ORDER_DATE = By.cssSelector(".table-body .col.date, tbody .col.date");
    private static final By HISTORY_ORDER_SHIP_ADDRESS = By.cssSelector(".table-body .col.shipping, tbody .col.shipping");
    private static final By VIEW_ORDER_LINK = By.cssSelector(".view.action-orders, a[href*='/view/order_id']");
    private static final By ORDER_DETAILS_NUMBER = By.cssSelector(".order-incre-cls, .page-title .base");
    private static final By ORDER_DETAILS_DATE = By.cssSelector(".order-date");
    private static final By ORDER_SHIPPING_ADDRESS = By.cssSelector(".box-order-shipping-address .box-content");
    private static final By ORDER_BILLING_ADDRESS = By.cssSelector(".box-order-billing-address .box-content");
    public static final By ACCOUNT_LINKS = By.xpath("//div[@class='userLoggin']//ul[@class='dropdown account-dropdown']//li//a");
    public By lastName = By.cssSelector("#lastname");
    public By savedInformationMessage = By.cssSelector("div.message-success.success.message > div:nth-child(1)");
    private static final By USER_EMAIL_GLODE = By.cssSelector("p.account-info__email > a");
    private static final By USER_EMAIL_LYFTSE = By.cssSelector(".email > p.user-details");
    public static final By UPDATE_PASSWORD_CHECKBOX = By.cssSelector("input#change-password.checkbox");
    public static final By UPDATE_EMAIL_CHECKBOX = By.cssSelector("input#change-email.checkbox");
    private static final By MANUAL_ADDRESS_ENTRY_GLODE = By.cssSelector("div.address-manual-button > span");
    private static final By TEXT_FIELD_REFERRER_COUPON_ZA = By.cssSelector("div.block-list > ul > li > span.coupon-code");
    private static final By WHATSAPP_BUTTON = By.cssSelector("a.whatsapp-icon");
    private static final By CHANGE_EMAIL = By.cssSelector("div.field.choice.choice-box__container.choice-email > label");
    private static final By EMAIL_FIELD = By.cssSelector("#email");
    private static final By SAVE_CHANGES = By.cssSelector("#form-validate > div > div > button");
    private static final By PASSWORD_INPUTBOX = By.cssSelector("#pass");
    private static final By SIGNIN_BUTTON = By.cssSelector("#send2");
    public static final By CURRENT_PASSWORD = By.cssSelector("#current-password");
    public static final By NEW_PASSWORD_VUSEIT = By.cssSelector("div.field.new.password.required > div.control > input#password");
    public static final By CONFIRMED_PASSWORD_VUSEIT = By.cssSelector("div.field.confirm.password.required > div.control > input#password-confirmation");
    private static final By MY_ACCOUNT_PL = By.cssSelector("#account-nav > ul > li.nav.item.current");
    private static final By OPTION_EMAIL = By.cssSelector("#subscription");
    private static final By OPTION_OFFER = By.cssSelector("#consent_mobile");
    private static final By NEWSLETTER_OPTION_RECORD = By.cssSelector("#form-validate > fieldset > div.actions-toolbar > div > button");
    private static final By RECIEVE_NEWSLETTER_BUTTON = By.cssSelector("div.newsletter > div.newsletter-action > button");
    private static final By NEWSLETTER_POPUP = By.cssSelector("#modal-content-1 > div > div");
    private static final By EMAIL_NEWSLETTER = By.cssSelector("#newsletter");
    private static final By EMAIL_MARKETING = By.cssSelector("#email_marketing");
    private static final By MOBILE_MARKETING = By.cssSelector("#mob_marketing");
    private static final By SUBSCRIBE_NEWSLETTER = By.cssSelector("#subscribe_news");
    private static final By CLOSE_NEWSLETTER_POPUP = By.cssSelector("#close_popup");
    private static final By USER_EMAIL_ADDRESS = By.cssSelector("div.column.main > div.account-info__container > p.account-info__email");
    private static final By VIEW_ORDER_LINK_LYFT = By.cssSelector("#my-orders-table > tbody > tr:nth-child(1) > td.col.actions > div > a.view.action-orders > p");
    private static final By ORDER_DETAILS_PAGE = By.cssSelector("#my-orders-table > tfoot > tr.grand_total_incl");

    // Address
    public static final By ADD_ADDRESS_BUTTON = By.cssSelector("button.action.primary.add");
    private static final By CLOSE_POPUP = By.cssSelector(".modal-inner-wrap .action-close");
    public final static By ADD_ADDRESS_BUTTON_VUSEFR =By.cssSelector("footer.modal-footer button.action.submit.primary");
    public final static By ADD_ADDRESS_BUTTON_VUSEFR_CHECKOUT =By.cssSelector("footer.modal-footer .action.primary.action-save-address");
    public static final By ADD_ADDRESS_BUTTON_CHECKOUT_PAGE = By.cssSelector("div[name='shippingAddress.street.0']>div>input");
    public static final By SHIPPING_ADDRESS = By.cssSelector("div.box.box-address-shipping");

    String updatedFirstName;
    String updatedSecondName;
    private By removeFavButton = By.cssSelector(".btn-remove.action.delete");
    private By addedFavSuccess = By.cssSelector("div.message-success.success.message > div:nth-child(1)");
    private static final By DOB_FIELD =By.cssSelector("input#dob");
    private static final By LOGGED_IN_USER_DOB = By.cssSelector("div.field.field-name-dob > span");
    private static final By BACK_BUTTON = By.cssSelector("button.action.back > span");
    public static final By postCode = By.cssSelector("input#zip");
    public static final By STREET_ADDRESS_FIELD = By.cssSelector("#street_1");
    public static final By STREET_ADDRESS_FIELD_VUSEFR = By.cssSelector("div[name='shippingAddress.street.0'] input[name='street[0]']");
    public static final By CITY_FIELD = By.cssSelector("#city");
    public static final By CONTENT_BLOCK = By.cssSelector("#maincontent > div.columns > div.column.main > div");
    public static final By VIEW_ALL_TEXT_LINK = By.linkText("Visa alla");
    private static final By CHANGE_BILLING_ADDRESS = By.cssSelector("button#edit_billing");
    public static final By ADD_ADDRESS_MANUALLY = By.cssSelector("a#js--address-search-toggle");
    private static final By ADD_ADDRESS_MANUALLY_VUSEFR = By.cssSelector("span.address-search__toggle");
    private static final By GO_TO_MY_ACCOUNT = By.cssSelector("a.action.primary.account");
    public static final By MARKETING_TICK_BOX = By.cssSelector("input#subscription.checkbox");
    private static final By SAVE_BUTTON = By.cssSelector("button.action.save.primary");
    private static final By SAVE_BUTTON_MX = By.cssSelector("button#subscribe_news");
    private static final By SOCIAL_LEFT_NEWSLETTER = By.cssSelector("div.social-left.newsletter");
    private static final By MARKETING_TICK_NEWSLETTER = By.cssSelector("div.social-left.newsletter");
    private static final By ADD_NEWADDRESS_FORM = By.cssSelector("div#Edit_address_modal_new");
    private static final By NO_SAVED_CARD_PRESENT = By.cssSelector(".message.info.empty");

    public By NEWSLETTERSUBSCRIPTIONCHECKEDCHECKBOX = By.cssSelector("#subscription");
    public By NEWSLETTERSUBSCRIPTIONCHECKEDCHECKBOX_EMAIL = By.cssSelector("#consent_email_marketing");
    public By NEWSLETTERSUBSCRIPTIONCHECKEDCHECKBOX_MOBILE = By.cssSelector("#consent_mobile");
    public static final By NEWSLETTER_SUCCESS_MSG_Text = By.cssSelector("div.message-success.success.message div");
    private static final By LAST_NAME = By.cssSelector("#lastname");
    private static final By CHANGE_PASSWORD_CHECKBOX = By.cssSelector("#change-password");
    private static final By GENDER = By.cssSelector("#gender");
    private static final By SOCIAL_ACCOUNT_DISCONNECT_LINK= By.cssSelector("a.btn.btn-block.btn-social.btn-facebook");
    private static final By ACCOUNTS_LINKED_DETAILS=By.cssSelector("div.facebook-details-container");
    public static final By ADD_PASSWORD_DISCONNECT_ERR_MSG=By.cssSelector("div.social-error-msg");
    private static final By CHANGE_EMAIL_CHECKBOX = By.cssSelector("#change-email");
    private static final By LOYALTY_CREDIT_ORDER_SUMMARY = By.cssSelector("#my-orders-table > tfoot > tr:nth-child(5) > td.mark");
    private static final By LOYALTY_AMOUNT_ORDER_SUMMARY = By.cssSelector(" #my-orders-table > tfoot > tr:nth-child(5) > td.amount");
    public static final By CHANGE_PASSWORD_BUTTON = By.cssSelector("a.action.secondary");
    public static final By CHANGE_PASSWORD_BUTTON_VUSEUK = By.cssSelector("#maincontent > div.columns > div.column.main > div.account-info__container > div > a.action.secondary > span");
    private static final By SAID_NUMBER_TEXTBOX=By.cssSelector("#za_document_value");
    public static final By CHANGE_PASSWORD_BUTTON_LYFT = By.cssSelector("div.block-content > div.box.box-information > div.box-actions > a");



    public void getFavsucessMessage() {
        waitForExpectedElement(addedFavSuccess, 10);
        if (doesURLContain("vype.non-prod.marketing.bat.net/it/it/") || doesURLContain("govype.com/it/it/")) {
            assertTrue(waitForExpectedElement(addedFavSuccess).getText().contains(UrlBuilder.getMessage("AddedToWishlist.key")));
        } else {
            assertTrue(waitForExpectedElement(addedFavSuccess).getText().contains("has been added to your Wish List. Click here to continue shopping."));
        }
    }

    public void updateFirstandSecondNameSave() {
        waitForExpectedElement(firstNameField).clear();
        waitForExpectedElement(lastName).clear();
        updatedFirstName = RandomGenerator.randomAlphabetic(10);
        updatedSecondName = RandomGenerator.randomAlphabetic(10);
        waitForExpectedElement(firstNameField).sendKeys(updatedFirstName);
        waitForExpectedElement(lastName).sendKeys(updatedSecondName);
        clickSave();
    }

    public void verifyInsiderClubCreditInOrderSummary(){
        assertTrue(isElementDisplayedBySeconds(LOYALTY_CREDIT_ORDER_SUMMARY,5));
        assertTrue(isElementDisplayedBySeconds(LOYALTY_AMOUNT_ORDER_SUMMARY,5));

    }

    public void updateDetailsSavedMessage() {
        String expectedMessage;
        if (getWebDriver().getCurrentUrl().contains("/mx/es/") || getWebDriver().getCurrentUrl().contains("/fr/fr/") || UrlBuilder.getLocale().equals("vypeit") || UrlBuilder.getLocale().equals("dk")|| UrlBuilder.getLocale().equals("vuseit")) {
            expectedMessage = UrlBuilder.getMessage("editDetailsSucessMsg.key");
            assertEquals(expectedMessage, waitForExpectedElement(savedInformationMessage, 10).getText());
            assertUpdatedNames();
        } else if (getWebDriver().getCurrentUrl().contains("glo.")) {
            expectedMessage = UrlBuilder.getMessage("editDetailsSucessMsg.key");
            assertEquals(expectedMessage, waitForExpectedElement(savedInformationMessage, 10).getText());
        } else if (getWebDriver().getCurrentUrl().contains("lyft.non-prod.marketing.bat.net/dk/da")) {
            assertTrue(getWebDriver().findElement(By.cssSelector("div.messages")).isDisplayed());

        } else if (getWebDriver().getCurrentUrl().contains("vype.non-prod.marketing.bat.net/de/de")) {
            assertEquals(updatedFirstName, waitForExpectedElement(By.cssSelector("div.field.field-name-firstname > span")).getText());

        } else {
            assertTrue(getWebDriver().findElement(By.cssSelector("div.message")).isDisplayed());
        }
    }

    public void assertUpdatedNames() {
        String grabbedFirstName = webDriver.findElement(By.cssSelector("#maincontent > div > div.column.main > div.block.block-dashboard-info > div > div.box.box-information > div > div.field.field-name-firstname > span")).getText();
        String grabbedSecondName = webDriver.findElement(By.cssSelector("#maincontent > div > div.column.main > div.block.block-dashboard-info > div > div.box.box-information > div > div.field.field-name-lastname > span")).getText();
        assertEquals(grabbedFirstName, updatedFirstName);
        assertEquals(grabbedSecondName, updatedSecondName);
    }

    public void clickSave() {
        try {
            waitForExpectedElement(saveButton, 10).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(saveButton);
        }
    }

    public void clickChangeEmailCheckbox() {
        try {
            waitForExpectedElement(CHANGE_EMAIL_CHECKBOX).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(CHANGE_EMAIL_CHECKBOX);
        }
    }

    public void deleteMyAccount() throws InterruptedException {
        if(UrlBuilder.isDesktop()||UrlBuilder.isIpad()) {
            switch (UrlBuilder.getLocale()) {
                    case "mx":
                case "vusemx":
                        if(UrlBuilder.isIpad()){
                            waitForExpectedElement(MYACCOUNT_EXPAND_IPAD,10);
                            clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                            waitForExpectedElement(lnkDeleteMXAccount, 5);
                            clickByElementByQueryJSExecutor(lnkDeleteMXAccount);
                            waitForExpectedElement(DELETE_MY_ACCOUNT_SUB_PAGE_HEADING, 30);
                            waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).click();
                            clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                        }
                        else {
                            waitForExpectedElement(lnkDeleteMXAccount, 10);
                            clickByElementByQueryJSExecutor(lnkDeleteMXAccount);
                            waitForExpectedElement(DELETE_MY_ACCOUNT_SUB_PAGE_HEADING, 30);
                            assertTrueWithCustomError("ELIMINAR MI CUENTA", waitForExpectedElement(DELETE_MY_ACCOUNT_SUB_PAGE_HEADING).getText());
                            assertTrueWithCustomError(UrlBuilder.getMessage("DelectMyAccountWarning.key"), waitForExpectedElement(
                                    DeleteMyAccountText).getText());
                            assertTrueWithCustomError(UrlBuilder.getMessage("WishToDeleteMsg.key"), waitForExpectedElement(
                                    DeleteMyAccountIUnderstandTheRisksWarningText).getText());
                            assertFalse(waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).isSelected());
                            waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).click();
                            clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                        }
                        break;
                case "vypeit":
                case "vuseit":
                    break;
                case "uk":
                case "vuseuk":
                    if(UrlBuilder.isIpad()){
                        waitForExpectedElement(MYACCOUNT_EXPAND_IPAD,10);
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON,10);
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).click();
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON).click();
                    }
                    else{
                        waitForExpectedElement(DELETE_ACCOUNT_LINK_VUSE_UK, 4).click();
                        String deleteMyAccountCopy = UrlBuilder.getMessage("deleteMyAccountWarningText.key");
                        String deleteMyAccountWarningCopy = UrlBuilder.getMessage("deleteMyAccountWarningCopy.key");
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON, 10);//added checkpoint for FF
                        assertTrue(checkPageUrlContains("deletemyaccount"));
                        assertTrueWithCustomError("DELETE MY ACCOUNT", waitForExpectedElement(DELETE_MY_ACCOUNT_SUB_PAGE_HEADING).getText());
                        assertTrueWithCustomError(deleteMyAccountCopy, waitForExpectedElement(DeleteMyAccountText).getText());
                        assertTrueWithCustomError(deleteMyAccountWarningCopy, waitForExpectedElement(
                                DeleteMyAccountIUnderstandTheRisksWarningText).getText());
                        assertFalse(waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).isSelected());
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).click();
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON).click();
                    }
                    break;
                case "it":
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                    Thread.sleep(1000);
                    waitForItemToBeClickableAndClick(deleteMyAccountButtonGLO);
                    break;
                case "kz":
                    if (UrlBuilder.isDesktop()) {
                            clickByElementByQueryJSExecutor(lnkDeleteMyAccount);
                            waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10);
                            clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_TICKBOX);
                            waitForExpectedElement(deleteMyAccountButtonGLO, 5);
                            clickByElementByQueryJSExecutor(deleteMyAccountButtonGLO);
                    }else {
                        clickByElement(KZ_MYACCOUNT_MOREOPTION);
                        clickByElement(KZ_DELETEONMORE);
                    }
                    break;
                case "pl":
                    if(UrlBuilder.isIpad()){
                        waitForExpectedElement(M_MYACCOUNT_ACCORDION_UK, 10);
                        clickByElementByQueryJSExecutor(M_MYACCOUNT_ACCORDION_UK);}
                    clickByElementByQueryJSExecutor(deleteMyAccountLinkByHref);
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10);
                    clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_TICKBOX);
                    Thread.sleep(1000);
                    if(UrlBuilder.isIpad())
                        clickByElementByQueryJSExecutor(deleteMyAccountButtonGLO);
                    else{
                        waitForExpectedElement(deleteMyAccountButtonGLO,5);
                        clickByElementByQueryJSExecutor(deleteMyAccountButtonGLO);
                        assertTrue(isElementDisplayedBySeconds(HOME_PAGE_BANNER,10));
                    }
                    break;
                case "de":
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                    clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                    break;
                case "vusede":
                    if(UrlBuilder.isIpad()){
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                    }
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key").toUpperCase()));
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                    clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                    break;
                case "velode":
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX_VELO, 10).click();
                    clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                    break;
                case "fr":
                case "veloza":
                case "vusefr":
                case "velobe":
                case "velopl":
                    if (UrlBuilder.isIpad()) {
                        clickByElementByQueryJSExecutor(HomePage.LOGO_ICON_VUSEUK);
                        waitForPage();
                        waitForAjaxElementNotToBePresent(getWebDriver(),10);
                        Thread.sleep(5000);
                        getWebDriver().navigate().to( getWebDriver().getCurrentUrl() + "customer/account/index");
                        waitForExpectedElement(MYACCOUNT_EXPAND_IPAD,10);
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                    }

                    waitForExpectedElement(deleteMyAccountLinkByHref,10);
                    clickUsingJS(deleteMyAccountLinkByHref);
                    waitForElementToAppearAndDisappear(LOADER, 3, 5);
                    clickUsingJS(DELETE_MYACCOUNT_TICKBOX);
                    waitForElementToAppearAndDisappear(LOADER, 3, 5);
                    clickUsingJS(deleteMyAccountButtonGLO);
                    waitForElementToAppearAndDisappear(LOADER, 2, 5);
                    break;
                case "glode":
                    if(UrlBuilder.isIpad()){
                        waitForExpectedElement(MYACCOUNT_EXPAND_IPAD,10);
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON,10);
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).click();
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON).click();
                    }
                    else{
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON, 10).click();
                    }
                    break;
                default:
                    if (UrlBuilder.isIpad()&&doesURLContain("/ie/en"))
                        waitForExpectedElement(T_PERSON_ICON_IE,5).click();
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                    waitForExpectedElement(DELETE_MYACCOUNT_BUTTON, 10).click();
            }

        }else{
            switch (UrlBuilder.getLocale()) {
                case "mx":
                case "vusemx":
                    if(UrlBuilder.isIPhone()){
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                        waitForExpectedElement(lnkDeleteMXAccount, 5);
                        clickByElementByQueryJSExecutor(lnkDeleteMXAccount);
                        waitForExpectedElement(DELETE_MY_ACCOUNT_SUB_PAGE_HEADING, 30);
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).click();
                        clickUsingJS(DELETE_MYACCOUNT_BUTTON);
                    }
                    else {
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                        waitForExpectedElement(lnkDeleteMXAccount, 5);
                        clickByElementByQueryJSExecutor(lnkDeleteMXAccount);
                        waitForExpectedElement(DELETE_MY_ACCOUNT_SUB_PAGE_HEADING, 30);
                        assertFalse(waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).isSelected());
                        clickUsingJS(DELETE_MYACCOUNT_TICKBOX);
                        clickUsingJS(DELETE_MYACCOUNT_BUTTON);
                    }
                    break;
                case "vypeit":
                case "vuseit":
                    break;
                case "uk":
                case "vuseuk":
                    if(UrlBuilder.isIPhone()) {
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                        waitForExpectedElement(T_DELETE_ACCOUNT_UK,10);
                        clickUsingJS(T_DELETE_ACCOUNT_UK);
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 5).click();
                        waitForItemToBeClickableAndClick(deleteMyAccountButtonGLO);
                    }
                    else{
                    waitForExpectedElement(T_HAMBURGER_UK).click();
                    waitForExpectedElement(T_HAMBURGER_MY_ACCOUNT1_UK).click();
                    waitForExpectedElement(T_HAMBURGER_MY_ACCOUNT2_UK).click();
                    waitForExpectedElement(T_MY_ACCOUNT3_UK, 5);
                    clickByElementByQueryJSExecutor(T_MY_ACCOUNT3_UK);
                    if(UrlBuilder.isSamsungMobile())
                        waitForExpectedElement(M_DELETE_ACCOUNT_UK).click();
                    else
                        waitForExpectedElement(T_DELETE_ACCOUNT_UK).click();
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 5).click();
                    waitForItemToBeClickableAndClick(deleteMyAccountButtonGLO);}
                    break;
                case "it":
                    waitForElementToAppearAndDisappear(LOADER,6,6);
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                    Thread.sleep(1000);
                    waitForItemToBeClickableAndClick(deleteMyAccountButtonGLO);
                    break;
                case "kz":
                      if (UrlBuilder.isSamsungMobile()) {
                        waitForExpectedElement(PERSON_ICON_PL, 10);
                        hoverOnElement(PERSON_ICON_PL);
                        waitForExpectedElement(DELETE_MYACCOUNT_DROPDOWN, 10).click();
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON, 10);
                        clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                    } else {
                        clickByElement(KZ_MYACCOUNT_MOREOPTION);
                        clickByElement(KZ_DELETEONMORE);
                    }
                    break;
                case "dk":
                case "de":
                case "ie":
                case "nl":
                case "vusedk":
                    waitForElementToAppearAndDisappear(LOADER,6,6);
                    waitForElementToAppearAndDisappear(LOADER, 6, 6);
                    if (Props.getProp("siteMode").equalsIgnoreCase("tablet")) {
                        waitForExpectedElement(T_PERSON_ICON_IE).click();
                        Thread.sleep(5000);
                    }
                    clickUsingJS(deleteMyAccountLinkByHref);
                    waitForElementToAppearAndDisappear(LOADER, 3, 5);
                    clickUsingJS(DELETE_MYACCOUNT_TICKBOX);
                    waitForElementToAppearAndDisappear(LOADER, 3, 5);
                    clickUsingJS(deleteMyAccountButtonGLO);
                    waitForElementToAppearAndDisappear(LOADER, 2, 5);
                    break;
                case "vuseza":
                    waitForExpectedElement(M_DELETE_ACCOUNT_LINK_ZA,10);
                    scrollToElement(M_DELETE_ACCOUNT_LINK_ZA);
                    clickByElementByQueryJSExecutor(M_DELETE_ACCOUNT_LINK_ZA);
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                    clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                    break;
                case "vusede":
                    if(UrlBuilder.isSamsungMobile()){
                        waitForExpectedElement(M_MYACCOUNT_ACCORDION_UK,10);
                        clickByElementByQueryJSExecutor(M_MYACCOUNT_ACCORDION_UK);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key").toUpperCase()));
                        clickUsingJS(DELETE_MYACCOUNT_TICKBOX);
                        clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);}
                    else{
                        waitForExpectedElement(M_MYACCOUNT_ACCORDION_UK, 10);
                        scrollToElement(M_MYACCOUNT_ACCORDION_UK);
                        clickByElementByQueryJSExecutor(M_MYACCOUNT_ACCORDION_UK);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key").toUpperCase()));
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                        clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                    }
                    break;
                case "fr":
                case "vusefr":
                case "vuseco":
                    if(UrlBuilder.isSamsungMobile()){
                        waitForExpectedElement(GO_TO_MY_ACCOUNT,10);
                        clickByElementByQueryJSExecutor(GO_TO_MY_ACCOUNT);
                        waitForExpectedElement(M_MYACCOUNT_ACCORDION_UK,10);
                        clickByElementByQueryJSExecutor(M_MYACCOUNT_ACCORDION_UK);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key").toUpperCase()));
                        clickUsingJS(DELETE_MYACCOUNT_TICKBOX);
                        clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);}
                    else{
                        clickByElementByQueryJSExecutor(HomePage.LOGO_ICON_VUSEUK);
                        waitForPage();
                        waitForAjaxElementNotToBePresent(getWebDriver(),10);
                        Thread.sleep(5000);
                        getWebDriver().navigate().to( getWebDriver().getCurrentUrl() + "customer/account/index");
                        waitForExpectedElement(MYACCOUNT_EXPAND_IPAD,10);
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                        waitForExpectedElement(deleteMyAccountLinkByHref,10);
                        clickUsingJS(deleteMyAccountLinkByHref);
                        waitForElementToAppearAndDisappear(LOADER, 3, 5);
                        clickUsingJS(DELETE_MYACCOUNT_TICKBOX);
                        waitForElementToAppearAndDisappear(LOADER, 3, 5);
                        clickUsingJS(deleteMyAccountButtonGLO);
                        waitForElementToAppearAndDisappear(LOADER, 2, 5);
                    }
                    break;
                case "pl":
                        waitForExpectedElement(M_MYACCOUNT_ACCORDION_UK, 10);
                        scrollToElement(M_MYACCOUNT_ACCORDION_UK);
                        clickByElementByQueryJSExecutor(M_MYACCOUNT_ACCORDION_UK);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key").toUpperCase()));
                        clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_TICKBOX);
                        clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_BUTTON);
                    break;
                case "glode":
                    if(UrlBuilder.isIpad()||UrlBuilder.isIPhone()||UrlBuilder.isSamsungMobile()){
                        waitForExpectedElement(MYACCOUNT_EXPAND_IPAD,10);
                        clickByElementByQueryJSExecutor(MYACCOUNT_EXPAND_IPAD);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON,10);
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX).click();
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON).click();
                    }
                    else{
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                        waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX,10);
                        clickByElementByQueryJSExecutor(DELETE_MYACCOUNT_TICKBOX);
                        waitForExpectedElement(DELETE_MYACCOUNT_BUTTON, 10).click();
                    }
                    break;
                case "velopl":
                    if (UrlBuilder.isIPhone() || UrlBuilder.isSamsungMobile() || UrlBuilder.isTablet()) {
                        waitForExpectedElement(deleteMyAccountLinkByHref, 10);
                        clickUsingJS(deleteMyAccountLinkByHref);
                        waitForElementToAppearAndDisappear(LOADER, 3, 5);
                        clickUsingJS(DELETE_MYACCOUNT_TICKBOX);
                        waitForElementToAppearAndDisappear(LOADER, 3, 5);
                        clickUsingJS(deleteMyAccountButtonGLO);
                        waitForElementToAppearAndDisappear(LOADER, 2, 5);
                        webDriver.navigate().refresh();
                    }
                    break;

                default:
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("deleteMyAccountLink.key")));
                    waitForExpectedElement(DELETE_MYACCOUNT_TICKBOX, 10).click();
                    waitForExpectedElement(DELETE_MYACCOUNT_BUTTON, 10).click();
            }
        }
        if (!UrlBuilder.getLocale().equals("ie") && !doesURLContain("lyft") && !doesURLContain("glo") && !UrlBuilder.getLocale().equals("mx")) {
            waitForElementToAppearAndDisappear(GloItCheckoutPage.LOADER, 3, 5);
        }
    }

    public Boolean assertHeadingsAreCorrect() {
        int matchCount = 0;
        ArrayList<String> MyOrderTableHeadings;

        if (getWebDriver().getCurrentUrl().contains("/mx/es/")) {
            MyOrderTableHeadings = new ArrayList<>(Arrays.asList("N\u00famero de Orden", "Fecha", "Enviada a", "Total de Orden", "Estatus"));
        } else if (getWebDriver().getCurrentUrl().contains("vype.non-prod.marketing.bat.net/it/it/")) {
            MyOrderTableHeadings = new ArrayList<>(Arrays.asList("Ordine n.", "Data", "Spedisci A", "Totale ordine", "Stato", "Azione"));
        } else if (UrlBuilder.getLocale().equals("pl")){
            MyOrderTableHeadings = new ArrayList<>(Arrays.asList("Numer zam\u00f3wienia","Data zam\u00f3wienia","Zamawiaj\u0105cy","Podsumowanie","Status"));
        }
        else {
            MyOrderTableHeadings = new ArrayList<>(Arrays.asList("Order number", "Date ordered", "Order total"));
        }

        for (WebElement title : getTableHeaders(myOrdersTable)) {
            if (MyOrderTableHeadings.contains(title.getText())) {
                matchCount++;
            }
        }
        if (getWebDriver().getCurrentUrl().contains("/mx/es/") || UrlBuilder.getLocale().equals("pl"))
            return matchCount == 5;
        else
            return matchCount == 3;
    }

    public Boolean isMyOrderTableDisplayed() {
        return waitForExpectedElement(myOrdersTable,20).isDisplayed();
    }

    public void selectMarketingPreferences() {
        switch (UrlBuilder.getLocale()) {
            case "vuseit":
                clickByElementByQueryJSExecutor(MARKETING_PREFERENCE_LINK);
                break;
            default:
                waitForExpectedElement(marketingPreferences).click();
        }
    }

    public void assertMarektingPreferenesAreAllPresentAndNotSelected() {
        assertEquals("Marketing Preferences", waitForExpectedElement(marketingPreferencesHeading).getText());
        assertTrue(waitForExpectedElement(newsLetterTickBox).isSelected());
        assertFalse(waitForExpectedElement(consentToEmailMarketingTickBox).isSelected());
        assertFalse(waitForExpectedElement(consentToMobileMarketingTickBox).isSelected());
        assertFalse(waitForExpectedElement(consentToWhiteMailMarketingTickBox).isSelected());
        assertFalse(waitForExpectedElement(consentToBatContactForTrialFeedbackTickBox).isSelected());
        assertFalse(waitForExpectedElement(consentToBatContactingForMarketingResearchPurposesTickBox).isSelected());
        assertFalse(waitForExpectedElement(consentToSharingDataWithThirdPartiesTickBox).isSelected());
        assertFalse(waitForExpectedElement(consentToStoringDataForMarketingPurposesTickBox).isSelected());
    }

    public void selectAllRemainingCheckBoxes() {
        clickUsingJS(consentToEmailMarketingTickBox);
        clickUsingJS(consentToMobileMarketingTickBox);
        clickUsingJS(consentToWhiteMailMarketingTickBox);
        clickUsingJS(consentToBatContactForTrialFeedbackTickBox);
        clickUsingJS(consentToBatContactingForMarketingResearchPurposesTickBox);
        clickUsingJS(consentToSharingDataWithThirdPartiesTickBox);
        clickUsingJS(consentToStoringDataForMarketingPurposesTickBox);
    }

    public void confirmRemainingTickBoxesSelected() {
        assertTrue(waitForExpectedElement(consentToEmailMarketingTickBox).isSelected());
        assertTrue(waitForExpectedElement(consentToMobileMarketingTickBox).isSelected());
        assertTrue(waitForExpectedElement(consentToWhiteMailMarketingTickBox).isSelected());
        assertTrue(waitForExpectedElement(consentToBatContactForTrialFeedbackTickBox).isSelected());
        assertTrue(waitForExpectedElement(consentToBatContactingForMarketingResearchPurposesTickBox).isSelected());
        assertTrue(waitForExpectedElement(consentToSharingDataWithThirdPartiesTickBox).isSelected());
        assertTrue(waitForExpectedElement(consentToStoringDataForMarketingPurposesTickBox).isSelected());
    }

    public boolean loopThurOrderTableContentsAndReturnifOrderAndReorderLinksPresent() {
        boolean viewOrderLinkPresent = false;
        boolean expectedOrderandReorderLinksPresent = false;

        for (WebElement rowData : getTableRows(myOrdersTable)) {
            if (rowData.getText().contains("VIEW ORDER") || rowData.getText().contains("Visualizza ordine") || rowData.getText().contains(UrlBuilder.getMessage("MyOrderTable.key"))) {
                viewOrderLinkPresent = true;
            }
        }
        if (viewOrderLinkPresent == true) {
            expectedOrderandReorderLinksPresent = true;
        }
        return expectedOrderandReorderLinksPresent;
    }

    public void clickOnReOrderLink() {
        waitForExpectedElement(By.cssSelector("div.actions a.action.order:nth-child(1)"), 10);
        clickByElementByQueryJSExecutor(By.cssSelector("div.actions a.action.order:nth-child(1)"));
    }

    public void clickOnReOrderLinkLyft() {
        clickByElementByQueryJSExecutor(ReOrderLink);
    }

    public void clickRemoveFavoriateButton() {
        waitForExpectedElement(removeFavButton,10);
        if(getWebDriver().findElements(removeFavButton).size()>0)
            while (getWebDriver().findElements(removeFavButton).size()>0) {
                clickByElementByQueryJSExecutor(removeFavButton);
                waitForAjaxElementNotToBePresent(getWebDriver(),10);
            }
    }

    public void assertLengthOfUserFirstNameAndLastNameOnAccountDashboard() {
        String strFirstNameUserDetails = waitForExpectedElement(eleFirstNameValue, 10).getText();
        String strLastNameUserDetails = waitForExpectedElement(eleLastNameValue, 10).getText();

        String strUserNameAccDashboard = waitForExpectedElement(eleUserNameAccDashboard).getText();
        String[] strUserNameSplit = strUserNameAccDashboard.split(" ");

        strFirstNameUserDetails = strFirstNameUserDetails.substring(0, 5);
        strLastNameUserDetails = strLastNameUserDetails.substring(0, 7);

        assertTrue(strFirstNameUserDetails.equalsIgnoreCase(strUserNameSplit[0]));
        assertTrue(strLastNameUserDetails.equalsIgnoreCase(strUserNameSplit[1].replace(".", "")));
    }

    public void assertOrderOfProductsAddedIsReverseOfSequence() {

        ArrayList<String> ProductsOrderOnMyFavorites;
        int matchCount = 0;

        ProductsOrderOnMyFavorites = new ArrayList<>(Arrays.asList(UrlBuilder.getMessage("favoritesProduct3.key"), UrlBuilder.getMessage("favoritesProduct2.key"), UrlBuilder.getMessage("favoritesProduct1.key")));
        List<WebElement> productName = getWebDriver().findElements(eleProductItemsMyFavorites);

        for (int i = 0; i <= productName.size(); i++) {
            if (ProductsOrderOnMyFavorites.contains(productName.get(i).getText())) {
                matchCount++;
                if (matchCount == 3)
                    break;
            }
        }
    }

    public void removeMultipleProductsFromMyFavoritesPage() {
        do {
            clickRemoveFavoriateButton();
        }
        while (getWebDriver().findElements(removeFavButton).size() > 0);
    }

    public void userEditCardNameFieldOfTheSavedCard() {
        waitForExpectedElement(txtCardNameSavedCard, 10).clear();
        enterDataAndWait(txtCardNameSavedCard, RandomGenerator.randomAlphabetic(10));
    }

    public void assertGoogleMapsInNewTabWithPrePopulatedDestination() {
        String expectedCountry;
        try { expectedCountry = UrlBuilder.getMessage("expectedDestination"); }
        catch (Exception e) { expectedCountry = "UK"; }
        ArrayList<String> windowTabs = new ArrayList<String>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(windowTabs.get(1));
        doesURLContain("https://www.google.com/maps/dir/");
        assertTrue(waitForExpectedElement(txtDestinationSearchBox, 10).getAttribute("aria-label").contains(expectedCountry));
        getWebDriver().close();
        getWebDriver().switchTo().window(windowTabs.get(0));
    }

    public void usersClicksOnTheTextLink(String linkText) {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)), 10);
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
    }

    public void clickOnHowDoesItWorksIcon() {
        clickByElementByQueryJSExecutor(HOW_DOES_IT_WORKS_LINK);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public String getOrderStatus(){
        return waitForExpectedElement(orderStatus).getText();
    }

    public void assertFavoritesLinkIsNotPresentOnMyAccount(String strExpectedLink) {
        assertFalse(getWebDriver().findElements(By.linkText(UrlBuilder.getMessage(strExpectedLink))).size() > 0);
    }

    public void assertFieldIsnTEditable() {
        waitForExpectedElement(DOB_FIELD);
        assert (!(getWebDriver().findElement(DOB_FIELD).isEnabled()));
    }

  public void addNewAddressButtonDisplayed() {
    switch (UrlBuilder.getLocale()) {
      case "kz":
      case "vuseza":
        case "pl":
            waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON, 10).isDisplayed();
        break;
        case "glode":
            try{
                if(!waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON, 10).isDisplayed()){
                    waitAndClickByElementByJSExecutor(By.linkText(
                            UrlBuilder.getMessage("addressMenuLink.key")),10);
                }
            }catch(Exception e){
                waitAndClickByElementByJSExecutor(By.linkText(
                        UrlBuilder.getMessage("addressMenuLink.key")),10);
            }
            break;
      default:
        waitForExpectedElement(ADD_ADDRESS_BUTTON, 10).isDisplayed();
    }
  }

    public void clickOnMyAccountNewAddressButton() {
        switch (UrlBuilder.getLocale()) {
            case "kz":
                clickByElementByQueryJSExecutor(ADD_NEW_ADDRESS_BUTTON);
                break;
            case "vuseco":
                clickByElementByQueryJSExecutor(ADD_ADDRESS_BUTTON);
                waitForExpectedElement(TELEPHONE_INPUT,15);
                break;
            default:
                clickByElementByQueryJSExecutor(ADD_ADDRESS_BUTTON);
        }
    }

    public void assertFirstNameFieldIsNotEditable() {
        if (UrlBuilder.getLocale().equals("vuseco")) {
            waitForExpectedElement(ADD_NEWADDRESS_FORM, 30);
        }
        if(doesURLContain("checkout")) {
            waitForExpectedElement(CHECKOUT_FIRST_NAME_READ_ONLY, 10);
            assertTrue(isElementDisplayedBySeconds(CHECKOUT_FIRST_NAME_READ_ONLY, 10));
        }
        else
            assertTrue(waitForExpectedElement(CUSTOMER_FIRST_NAME_READ_ONLY,10).isDisplayed());
    }

    public void assertLastNameFieldIsNotEditable() {
        if(doesURLContain("checkout"))
            assertTrue(waitForExpectedElement(CHECKOUT_LAST_NAME_READ_ONLY,20).isDisplayed());
        else
            assertTrue(waitForExpectedElement(CUSTOMER_LAST_NAME_READ_ONLY,20).isDisplayed());
    }

    public void fetchFirstAndLastNamesOfLoggedInUser() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vusede":
            case "fr":
            case "vusefr":
            case "mx":
            case "vusemx":
            case "vypeit":
            case "vuseit":
            case "vuseuk":
            case "vuseco":
            case "vuseza":
                String[] ArrFullNameUk = waitForExpectedElement(UK_ACCOUNT_USER_NAME).getText().split(" ");
                String strFirstNameUk = ArrFullNameUk[0];
                String[] strLastNameUk = ArrFullNameUk[1].split("\n");
                System.setProperty("UserFirstName.key", strFirstNameUk);
                System.setProperty("UserLastName.key",strLastNameUk[0]);
                break;
            case "lyftse":
            case "lyftdk":
                System.setProperty("UserFirstName.key", waitForExpectedElement(LYFT_ACCOUNT_FIRST_NAME).getText());
                System.setProperty("UserLastName.key", waitForExpectedElement(LYFT_ACCOUNT_LAST_NAME).getText());
                break;
            case "kz":
                hoverOnElement(MY_ACCOUNT_LINKS);
                waitForExpectedElement(MY_ACCOUNT_DROPDOWN_LINKS,10);
                clickByElementByQueryJSExecutor(MY_ACCOUNT_DROPDOWN_LINKS);
                String[] ArrFullNameKZ = waitForExpectedElement(GLO_ACCOUNT_USER_NAME).getText().split(" ");
                String strFirstNameKZ = ArrFullNameKZ[0];
                String[] strLastNameKZ = ArrFullNameKZ[1].split("\n");
                System.setProperty("UserFirstName.key", strFirstNameKZ);
                System.setProperty("UserLastName.key",strLastNameKZ[0]);
                break;
            case "it":
            case "pl":
            case "glode":
                String[] ArrFullName = waitForExpectedElement(GLO_ACCOUNT_USER_NAME).getText().split(" ");
                String strFirstName = ArrFullName[0];
                String[] strLastName = ArrFullName[1].split("\n");
                System.setProperty("UserFirstName.key", strFirstName);
                System.setProperty("UserLastName.key",strLastName[0]);
                break;
            default:
                System.setProperty("UserFirstName.key", waitForExpectedElement(ACCOUNT_USER_FIRST_NAME,10).getText());
                System.setProperty("UserLastName.key", waitForExpectedElement(ACCOUNT_USER_LAST_NAME,10).getText());
        }
    }

    public void assertChangeEmailFieldIsNotEditable() {
        assertTrue(waitForExpectedElement(CHANGE_EMAIL_READ_ONLY).isDisplayed());
    }

    public void assertNonEditableCustomerFieldsContentInfo() {
        switch (UrlBuilder.getLocale()) {
            case "vusede":
            case "fr":
            case "vusefr":
                assertFirstNameFieldIsNotEditable();
                assertLastNameFieldIsNotEditable();
                break;
            default:
                if(getWebDriver().findElements(VIEW_NOTICE_CMS_BLOCK).size()>0) {
                    scrollElementIntoView(VIEW_NOTICE_CMS_BLOCK);
                    assertTrue(waitForExpectedElement(VIEW_NOTICE_CMS_BLOCK, 10).isDisplayed());
                }
        }

    }

    public void clickOnEditButtonFromAccountDashboard() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
            case "de":
            case "vusedk":
                clickByElementByQueryJSExecutor(EDIT_ACCOUNT_BUTTON);
                break;
            case "mx":
            case "vusemx":
                clickByElementByQueryJSExecutor(EDIT_ACCOUNT_BUTTON_MXCX);
                break;
            default:
                usersClicksOnTheTextLink("dashboardEditLinkText.key");
        }
    }

    public void clickOnPaymentLinkFromMyAccountDashboard() {
        clickByElementByQueryJSExecutor(MYACCOUNT_PAYMENT_LINK);
        waitForExpectedElement(SAVED_CARDS_GRID,10);
    }

    public void eyesCheckAccountDashboardPage() {
        if (Props.EYES_ON && EyesCheckpoints.ACCOUNT_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.ACCOUNT_PAGE.getName();
            switch (UrlBuilder.getLocale()) {
                case "lyftse":
                case "lyftdk":
                    eyes.check(checkpointName, Target.window().fully()
                            .layout(PAGE_TITLE, 0, 0, 0, 10)
                            .layout(CONTACT_INFO)
                            .layout(SHIPPING_ADDRESS_REGION, 5, 5, 5, 5));
                    break;
                case "it":
                    eyes.check(checkpointName, Target.window().fully()
                            .layout(CONTACT_INFO, 5, 5, 5, 5)
                            .layout(BILLING_ADDRESS_REGION, 5, 5, 5, 5)
                            .layout(SHIPPING_ADDRESS_REGION, 5, 5, 5, 5));
                    break;
                case "vusedk":
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(UrlBuilder.isDesktop()? FULL_USER_NAME_REGION : HomePage.SLICK_LIST)
                            .layout(eleFirstNameValue, 5, 5, 10, 10)
                            .layout(eleLastNameValue, 5, 5, 10, 10)
                            .layout(EMAIL_ADDRESS, 5, 5, 10, 10)
                            .layout(BIRTH_DATE));
                    break;
                case "dk":
                case "ie":
                case "de":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(FULL_USER_NAME_REGION)
                                .layout(eleFirstNameValue, 5, 5, 10, 10)
                                .layout(eleLastNameValue, 5, 5, 10, 10)
                                .layout(EMAIL_ADDRESS, 5, 5, 10, 10)
                                .layout(BIRTH_DATE));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully()
                                .layout(eleFirstNameValue, 5, 5, 10, 10)
                                .layout(eleLastNameValue, 5, 5, 10, 10)
                                .layout(EMAIL_ADDRESS, 5, 5, 10, 10)
                                .layout(BIRTH_DATE));
                    }
                    break;
                case "nl":
                case "vypeit":
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(FULL_USER_NAME_REGION)
                            .layout(eleFirstNameValue, 5, 5, 10, 10)
                            .layout(eleLastNameValue, 5, 5, 10, 10)
                            .layout(EMAIL_ADDRESS, 5, 5, 10, 10)
                            .layout(BIRTH_DATE)
                            .layout(GREETING, 5, 5, 10, 30));
                    break;
                case "vuseza":
                case "vuseit":
                case "vusefr":
                case "glode":
                case "vusede":
                case "kz":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully()
                                .layout(WELCOME_MESSAGE,
                                        BILLING_ADDRESS_REGION)
                                .layout(UK_ACCOUNT_USER_NAME, 5, 5, 10, 10)
                                .layout(ACCOUNT_EMAIL, 5, 5, 10, 10)
                                .layout(SHIPPING_ADDRESS_REGION, 5, 5, 25, 25));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(HomePage.MESSAGE_ROW)
                                .layout(WELCOME_MESSAGE,
                                        BILLING_ADDRESS_REGION)
                                .layout(UK_ACCOUNT_USER_NAME, 5, 5, 10, 10)
                                .layout(ACCOUNT_EMAIL, 5, 5, 10, 10)
                                .layout(SHIPPING_ADDRESS_REGION, 5, 5, 25, 25));
                    }
                    break;
                default:
                    eyes.check(checkpointName, Target.window().fully()
                            .layout(WELCOME_MESSAGE,
                                    BILLING_ADDRESS_REGION)
                            .layout(UK_ACCOUNT_USER_NAME, 5, 5, 10, 10)
                            .layout(ACCOUNT_EMAIL, 5, 5, 10, 10)
                            .layout(SHIPPING_ADDRESS_REGION, 5, 5, 25, 25));
            }
        }
    }

    public void eyesCheckAccountSubPage(EyesCheckpoints subPage) {
        if (Props.EYES_ON && subPage.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = subPage.getName();
            switch (subPage) {
                case ACCOUNT_ADDRESS_BOOK_PAGE:
                    if ("vusedk".equals(UrlBuilder.getLocale())) {
                        sleepFor(3000);
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(eleUserNameAccDashboard)
                                .layout(ADDRESS_BOOK_BILLING_ADDRESS)
                                .layout(ADDRESS_BOOK_SHIPPING_ADDRESS, 5, 5, 25, 25));
                    } else {
                        eyes.check(checkpointName, Target.window().fully()
                                .layout(ADDRESS_BOOK_BILLING_ADDRESS)
                                .layout(ADDRESS_BOOK_SHIPPING_ADDRESS, 5, 5, 25, 25));
                    }
                    break;
                case ACCOUNT_ADD_A_NEW_ADDRESS_PAGE:
                    switch (UrlBuilder.getLocale().toLowerCase()) {
                        case "it":
                        case "vuseco":
                        case "vusede":
                        case "vuseza":
                            waitForExpectedElement(ADD_ADDRESS_BUTTON, 3).click();
                            scrollToShowEntirePage();
                            eyes.check(checkpointName, Target.window().fully());
                            break;
                        case "glode":
                            waitForExpectedElement(ADD_ADDRESS_BUTTON, 3).click();
                            scrollToShowEntirePage();
                            eyes.check(checkpointName, Target.window().fully());
                            clickIndexElementByQueryJSExecutor(CLOSE_POPUP,3);
                            break;
                    }
                    break;
                case ACCOUNT_ORDER_HISTORY_PAGE:
                    if ("vusedk".equals(UrlBuilder.getLocale())) {
                        sleepFor(3000);
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(eleUserNameAccDashboard)
                                .layout(HISTORY_ORDER_NUMBER,
                                        HISTORY_ORDER_DATE,
                                        HISTORY_ORDER_SHIP_ADDRESS)
                                .ignoreDisplacements());
                    } else {
                        eyes.check(checkpointName, Target.window().fully()
                                .layout(HISTORY_ORDER_NUMBER,
                                        HISTORY_ORDER_DATE,
                                        HISTORY_ORDER_SHIP_ADDRESS)
                                .ignoreDisplacements());
                    }
                    break;
                case ACCOUNT_ORDER_DETAILS_PAGE:
                    if ("vusedk".equals(UrlBuilder.getLocale())) {
                        sleepFor(3000);
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(eleUserNameAccDashboard)
                                .layout(ORDER_DETAILS_NUMBER, 0, 0, 30, 0)
                                .layout(ORDER_DETAILS_DATE,
                                        ORDER_SHIPPING_ADDRESS,
                                        ORDER_BILLING_ADDRESS));
                    } else {
                        eyes.check(checkpointName, Target.window().fully()
                                .layout(ORDER_DETAILS_NUMBER, 0, 0, 20, 0)
                                .layout(ORDER_DETAILS_DATE,
                                        ORDER_SHIPPING_ADDRESS,
                                        ORDER_BILLING_ADDRESS));
                    }
                    break;
                default:
                    eyes.check(checkpointName, Target.window().fully());
            }
        }
    }

    public boolean deviceTrialOrderTablePresent() {
        return waitForExpectedElement(ORDER_DEVICE_TRIAL_TABLE, 5).isDisplayed();
    }

    public void orderHistory() {
        if (UrlBuilder.getLocale().equals("glode")) {
            clickByElementByQueryJSExecutor(ORDER_HISTORY_BUTTON_GLO);
            waitForExpectedElement(DELETE_MY_ACCOUNT_SUB_PAGE_HEADING, 30);
        }
    }

    public void ensureTheNewsletterIconIsnTSelected() {
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "vuseuk":
            case "vuseit":
            case "vuseco":
                break;
            case "mx":
            case "vusemx":
                WebElement marketingTickBoxMx = waitForExpectedElement(MARKETING_TICK_NEWSLETTER);
                if (!marketingTickBoxMx.isSelected()) {
                    clickUsingJS(SOCIAL_LEFT_NEWSLETTER);
                }
                clickByElementByQueryJSExecutor(SAVE_BUTTON_MX);
                waitForExpectedElement(pageHeading, 10);
                break;
            default:
                WebElement marketingTickBox = waitForExpectedElement(MARKETING_TICK_BOX);
                if (!marketingTickBox.isSelected()) {
                    clickUsingJS(MARKETING_TICK_BOX);
                }
                clickByElementByQueryJSExecutor(SAVE_BUTTON);
                waitForExpectedElement(pageHeading, 10);
        }
    }

    public void clickOnPrintOrderLink() {
      switch (UrlBuilder.getLocale()) {
          case "fr":
          case "vusefr":
          case "mx":
          case "vusemx":
          case "vuseco":
          break;
        default:
          clickFirstElementByQueryJSExecutor(PRINT_ORDER_LINK);
      }
    }

    public void fetchDOBandAddressDetailsOfLoggedInUser() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "glode":
                clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("dashboardEditLinkText.key")));
                waitForExpectedElement(DOB_FIELD,10);
                String strGloUserDOB = waitForExpectedElement(DOB_FIELD).getAttribute("value");
                System.setProperty("UserDOB.key",strGloUserDOB);
                clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("yourAddressText.key")));
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage("changeBillingAddressLink.key")),10);
                try {
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("changeBillingAddressLink.key")));
                }catch (Exception e){
                    clickByElementByQueryJSExecutor(CHANGE_BILLING_ADDRESS);
                }
                fetchAddressDetailsOfLoggedInUser();
                refreshBrowser();
                break;
            case "de":
                waitForExpectedElement(LOGGED_IN_USER_DOB,10);
                String strVypeUserDOB = waitForExpectedElement(LOGGED_IN_USER_DOB).getText();
                System.setProperty("UserDOB.key",strVypeUserDOB);
                clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("yourAddressText.key")));
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage("editBillingAddressLink.key")),5);
                clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("editBillingAddressLink.key")));
                fetchAddressDetailsOfLoggedInUser();
                clickByElementByQueryJSExecutor(BACK_BUTTON);
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage("editBillingAddressLink.key")),5);
                break;
            default:
        }
    }

    public void fetchAddressDetailsOfLoggedInUser() {
            if (UrlBuilder.getLocale().equals("glode")) {
                if(getWebDriver().findElements(MANUAL_ADDRESS_ENTRY_GLODE).size()>0){
                    waitForExpectedElement(MANUAL_ADDRESS_ENTRY_GLODE);
                    clickByElementByQueryJSExecutor(MANUAL_ADDRESS_ENTRY_GLODE);}
                else{
                    waitForExpectedElement(ADD_ADDRESS_MANUALLY);
                    clickByElementByQueryJSExecutor(ADD_ADDRESS_MANUALLY);}
            }
            waitForExpectedElement(postCode,10);
        String PostCode = waitForExpectedElement(postCode).getAttribute("value");
        String StreetAddress = waitForExpectedElement(STREET_ADDRESS_FIELD).getAttribute("value");
        String City = waitForExpectedElement(CITY_FIELD).getAttribute("value");
        System.setProperty("UserPostCode.key",PostCode);
        System.setProperty("UserStreetAddress.key",StreetAddress);
        System.setProperty("UserCity.key",City);
    }

    public void fetchEmailAddressOfLoggedInUser() {
        String strEmail="";
        switch (UrlBuilder.getLocale()) {
            case "glode":
            case "vusede":
                strEmail = waitForExpectedElement(USER_EMAIL_GLODE).getText();
                System.setProperty("UserEmailAddress.key", strEmail);
                break;
            case "de":
                strEmail = waitForExpectedElement(By.cssSelector("div.field.field-name-email > span.value")).getText();
                System.setProperty("UserEmailAddress.key", strEmail);
                break;
            case "it":
                strEmail = waitForExpectedElement(By.cssSelector("div.box.box-information p")).getText();
                String[]s=strEmail.split("\n");
                System.setProperty("UserEmailAddress.key", s[1]);
                break;
            case "lyftse":
                strEmail = waitForExpectedElement(USER_EMAIL_LYFTSE).getText();
                System.setProperty("UserEmailAddress.key", strEmail);
                break;
            default:
        }
    }

    public boolean isSuccessMessageReturned(){
        boolean result;
        try {
            getWebDriver().findElement(registeringConfirmationMsg);
            result = true;
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    public void gotoAddressBookSubPage() {
        clickByElementByQueryJSExecutor(ADDRESS_BOOK_LINK);
        waitForPage();
    }

    public void gotoOrderHistorySubPage() {
        switch (UrlBuilder.getLocale()) {
            case "vusedk":
                clickByElementByQueryJSExecutor(VUSE_DK_ORDER_HISTORY_LINK);
                break;
            case "pl":
                clickByElementByQueryJSExecutor(GLO_PL_ORDER_HISTORY_LINK);
                break;
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("orderHistoryUrl.key"));
                break;
            default:
                clickByElementByQueryJSExecutor(ORDER_HISTORY_LINK);
        }
        waitForPage();
    }

    public void gotoHistoryOrdersSubPage() {
        clickByElementByQueryJSExecutor(HISTORIC_ORDERS_LINK);
        waitForPage();
    }

    public void gotoOrderDetailsSubPage() {
        if ("vusedk".equals(UrlBuilder.getLocale())) {
            clickIndexElementByQueryJSExecutor(VIEW_ORDER_LINK, 1);
        } else {
            clickByElementByQueryJSExecutor(VIEW_ORDER_LINK);
            waitForPage();
        }
    }

    public void gotoSavedCardSubPage() {
        clickByElementByQueryJSExecutor(SAVED_CARD_LINK);
        waitForPage();
    }

    public void gotoNewsletterSubPage() {
        clickByElementByQueryJSExecutor(NEWSLETTER_LINK);
        waitForPage();
    }

    public void gotoDeviceRegistrationSubPage() {
        clickByElementByQueryJSExecutor(DEVICE_REGISTRATION_LINK);
        waitForPage();
    }

    public void gotoReferAFriendSubPage() {
        if ("it".equals(UrlBuilder.getLocale().toLowerCase())) {
            clickByElementByQueryJSExecutor(REFER_A_FRIEND_LINK_GLO_IT);
        } else {
            clickByElementByQueryJSExecutor(REFER_A_FRIEND_LINK);
        }
        waitForPage();
    }

    public void gotoFavoritesSubPage() {
        clickByElementByQueryJSExecutor(FAVORITES_LINK);
        waitForPage();
    }

    public void gotoGloDeviceSubPage() {
        clickByElementByQueryJSExecutor(GLO_DEVICE_LINK);
        waitForPage();
    }

    public void gotoMyAccountSubPage(EyesCheckpoints subPage) {
        switch (subPage) {
            case ACCOUNT_ADDRESS_BOOK_PAGE:
                gotoAddressBookSubPage();
                break;
            case ACCOUNT_HISTORIC_ORDERS_PAGE:
                gotoHistoryOrdersSubPage();
                break;
            case ACCOUNT_SAVED_CARD_PAGE:
                gotoSavedCardSubPage();
                break;
            case ACCOUNT_ORDER_HISTORY_PAGE:
                gotoOrderHistorySubPage();
                break;
            case ACCOUNT_ORDER_DETAILS_PAGE:
                gotoOrderDetailsSubPage();
                break;
            case ACCOUNT_DEVICE_REGISTRATION_PAGE:
                gotoDeviceRegistrationSubPage();
                break;
            case ACCOUNT_NEWSLETTER_PAGE:
                gotoNewsletterSubPage();
                break;
            case ACCOUNT_REFER_A_FRIEND_PAGE:
                gotoReferAFriendSubPage();
                break;
            case ACCOUNT_FAVORITES_PAGE:
                gotoFavoritesSubPage();
                break;
            case ACCOUNT_GLO_DEVICE_PAGE:
                gotoGloDeviceSubPage();
                break;
        }
    }

    public void myAccountLinks(DataTable table){
        List<Map<String, String>> entries = table.asMaps(String.class, String.class);
        for(int i=0;i<entries.size()-1;i++){
            hoverOnElement(MY_ACCOUNT_LINKS);
            List<WebElement> links = presenceOfAllElementsLocatedBy(ACCOUNT_LINKS);
            clickElementByQueryJSExecutor(links.get(i));
            waitForPage();
            String expectedTitle =  UrlBuilder.getMessage(entries.get(i).get("Title"));
            assertTrueWithCustomError(expectedTitle,getCurrentPageTitle());
        }
    }

    public void clickViewAllSavedCards() {
        List<WebElement> contentBlocks = webDriver.findElements(CONTENT_BLOCK);
        for (WebElement contentBlock: contentBlocks) {
            if (contentBlock.getText().toLowerCase().contains(UrlBuilder.getMessage("savedCardText.key").toLowerCase())) {
                contentBlock.findElement(VIEW_ALL_TEXT_LINK).click();
                break;
            }
        }
    }

    public boolean isSavedCardsPresent() {
        Boolean isPresent = webDriver.findElements(CONTENT_BLOCK).size() > 0;
        return isPresent;
    }

    public void isSavedCardPresent() {
        assertTrue(getWebDriver().findElement(NO_SAVED_CARD_PRESENT).isDisplayed());
    }

    public void addNewAddressFromMyAccountWithMoreThan30CharactersInAddressField() {
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
                waitForExpectedElement(ADD_ADDRESS_MANUALLY_VUSEFR, 5);
                //On checkout-add address page
                if (webDriver.findElements(ADD_ADDRESS_MANUALLY_VUSEFR).size() > 0) {
                    clickByElementByQueryJSExecutor(ADD_ADDRESS_MANUALLY_VUSEFR);
                    waitForExpectedElement(STREET_ADDRESS_FIELD_VUSEFR).sendKeys(UrlBuilder.getMessage("streetwrongAddress.key"));
                    clickByElementByQueryJSExecutor(ADD_ADDRESS_BUTTON_VUSEFR_CHECKOUT);
                }
                //On my account page
                else if (webDriver.findElements(ADD_ADDRESS_MANUALLY).size() > 0) {
                    clickByElementByQueryJSExecutor(ADD_ADDRESS_MANUALLY);
                    waitForExpectedElement(STREET_ADDRESS_FIELD).sendKeys(UrlBuilder.getMessage("streetwrongAddress.key"));
                    clickByElementByQueryJSExecutor(ADD_ADDRESS_BUTTON_VUSEFR);
                } else {
                    waitForExpectedElement(ADD_ADDRESS_BUTTON_CHECKOUT_PAGE, 8).sendKeys(UrlBuilder.getMessage("streetwrongAddress.key"));
                    clickByElementByQueryJSExecutor(AddNewAddressPage.SAVE_ADDRESS_BUTTON_VUSEFR);
                }
                break;
            case "vuseuk":
                //On my account page
                if (webDriver.findElements(ADD_ADDRESS_MANUALLY).size() > 0) {
                    clickByElementByQueryJSExecutor(ADD_ADDRESS_MANUALLY);
                    waitForExpectedElement(STREET_ADDRESS_FIELD).sendKeys(UrlBuilder.getMessage("streetwrongAddress.key"));
                    clickByElementByQueryJSExecutor(ADD_ADDRESS_BUTTON_VUSEFR);
                }
                break;
        }
    }
    public void isPersonalDataPresent() {
        SoftAssert softAssert = new SoftAssert();
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
                softAssert.assertTrue(getWebDriver().findElement(firstNameField).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(LAST_NAME).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(DOB_FIELD).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(GENDER).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(saveButton).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(CHANGE_PASSWORD_CHECKBOX).isDisplayed());
                softAssert.assertAll();
                break;
            case "vuseza":
            case "vusede":
                softAssert.assertTrue(getWebDriver().findElement(firstNameField).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(LAST_NAME).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(DOB_FIELD).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(GENDER).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(saveButton).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(CHANGE_PASSWORD_CHECKBOX).isDisplayed());
                softAssert.assertTrue(getWebDriver().findElement(firstNameField).getAttribute("value").equals(scenarioContext.getContext(Context.FIRST_NAME)));
                softAssert.assertTrue(getWebDriver().findElement(LAST_NAME).getAttribute("value").equals(scenarioContext.getContext(Context.LAST_NAME)));
                softAssert.assertTrue(getWebDriver().findElement(DOB_FIELD).getAttribute("value").equalsIgnoreCase(UrlBuilder.getMessage("ValidDOB.key")));
                softAssert.assertTrue(getWebDriver().findElement(GENDER).getAttribute("value").equals("2"));
                break;
            case "glopl":
                softAssert.assertTrue(isElementDisplayedBySeconds(firstNameField,2));
                softAssert.assertTrue(isElementDisplayedBySeconds(LAST_NAME,2));
                softAssert.assertTrue(isElementDisplayedBySeconds(saveButton,2));
                softAssert.assertTrue(isElementDisplayedBySeconds(CHANGE_PASSWORD_CHECKBOX,2));
                softAssert.assertTrue(isElementDisplayedBySeconds(CHANGE_EMAIL_CHECKBOX,2));
                softAssert.assertAll();
                break;
        }
    }

    public void getReferralCouponCode() {
       String strReferrercode = waitForExpectedElement(TEXT_FIELD_REFERRER_COUPON_ZA).getText();
        System.setProperty("referrerCouponCode.key", strReferrercode);
    }

    public void verifyReferralSendViaWhatsapp() {
        assertTrue(isElementDisplayedBySeconds(WHATSAPP_BUTTON,5));
        waitForItemToBeClickableAndClick(WHATSAPP_BUTTON,5);
        switchBetweenWindowTabs(1);
        String whatsappURL = getCurrentUrl();
        assertThat(whatsappURL)
                .contains(UrlBuilder.getMessage("whatsappUrl.key"));
    }

    public void assertDisconnectLinkIsDisplayedVerifyingBothFBandM2AccountsLinking(String strLinkText) {
        assertTrue(waitForExpectedElement(SOCIAL_ACCOUNT_DISCONNECT_LINK,10).getText().toLowerCase().contains(strLinkText.toLowerCase()));
        assertTrue(waitForExpectedElement(ACCOUNTS_LINKED_DETAILS).getText().contains("Your Facebook account is successfully linked to Vuse UK."));
    }

    public void goToMyAccountSubscriptionPageAndTakeEyesScreenshot() {
        final String pageName = "My Account Subscription Page";
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
            case "vuseco":
            case "vusede":
            case "vuseuk":
            case "vuseit":
                clickByElementByQueryJSExecutor(MY_SUBSCRIPTION_LINK);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToMyDetailsPageAndTakeEyesScreenshot() {
        final String pageName = "My Details Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseco":
            case "vusede":
            case "vuseza":
            case "lyftse":
            case "vusefr":
            case "pl":
            case "vuseuk":
            case "kz":
                clickByElementByQueryJSExecutor(MY_DETAILS_LINK);
                scrollToShowEntirePage();
                if (Props.USE_EYES_GRID) scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully().ignore(firstNameField, lastName));
                break;
            case "vuseit":
            case "velopl":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("MyDetailPageUrl.key"));
                eyes.check(pageName, Target.window().fully().ignore(firstNameField, lastName));
                break;
        }
    }

    public void userChangeEmailIDLoggedUser() {
        String newEMailID = "newEMailID.key";
        String password = "loginValidPassword.key";
        waitForItemToBeClickableAndClick(CHANGE_EMAIL,5);
        waitForExpectedElement(EMAIL_FIELD).clear();
        waitForExpectedElement(EMAIL_FIELD,4).sendKeys(UrlBuilder.getMessage(newEMailID));
        waitForExpectedElement(CURRENT_PASSWORD,4).sendKeys(UrlBuilder.getMessage(password));
        waitForItemToBeClickableAndClick(SAVE_CHANGES);
    }

    public void userLoginWithNewEmail() {
        String email_ID = "newEMailID.key";
        String password = "loginValidPassword.key";
        waitForExpectedElement(EMAIL_FIELD, 10).sendKeys(UrlBuilder.getMessage(email_ID));
        waitForExpectedElement(PASSWORD_INPUTBOX,10).sendKeys(UrlBuilder.getMessage(password));
        clickUsingJS(SIGNIN_BUTTON);
        assertTrue(isElementDisplayedBySeconds(MY_ACCOUNT_PL,10));
    }

    public void removeAddress() {
        List<WebElement> streetLineContentList=getWebDriver().findElements(STREET_LINE_CONTENT);
        List<WebElement> removeButtoneList=getWebDriver().findElements(REMOVE_ADDRESS_BUTTON);
        for(int i=0;i<streetLineContentList.size();i++){
            if(UrlBuilder.getMessage("streetAddressLine.key")
                    .equalsIgnoreCase(streetLineContentList.get(i).getText())){
                clickUsingJS(removeButtoneList.get(i));
                waitForExpectedElement(REMOVE_ADDRESS_OK_BUTTON).click();
                break;
           }
        }
    }

    public void goToSubscribeNewsLetterPageAndTakeEyesScreenshot() {
        final String pageName = "Subscribe Newsletter Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                clickIndexElementByQueryJSExecutor(LOGO_LYFT_SE, 0);
                waitForPage();
                clickIndexElementByQueryJSExecutor(SUBSCRIBE_NEWSLETTER_BUTTON_LYFT_SE, 0);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "vuseco":
            case "vusede":
            case "vusefr":
            case "pl":
            case "glode":
            case "kz":
                waitForExpectedElement(NEWSLETTER_LINK, 3);
                clickByElementByQueryJSExecutor(NEWSLETTER_LINK);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public boolean waitForFacebookPopUpWindowToClose() {
        boolean result = false;
        int attempts = 0;
        while (attempts < 4) {
            try {
                result = wait.until(ExpectedConditions.numberOfWindowsToBe(1));
                break;
            } catch (StaleElementReferenceException e) {
                LOG.info("wait again for any additional windows to close.");
            }
            attempts++;
        }
        return result;
    }

    public void goToMyAccountOrderPageAndTakeEyesScreenshot() {
        final String pageName = "My Account Order Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
            case "vusefr":
                clickByElementByQueryJSExecutor(ORDER_HISTORY_LINK);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "glode":
                clickByElementByQueryJSExecutor(MY_ACCOUNT_LINK_GLO_DE);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "kz":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("accountOrderUrl.key"));
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void clickDeleteMyAccountButton() {
        waitAndClickByElementByJSExecutor(DELETE_MYACCOUNT_BUTTON, 10);
    }

    public boolean assertSAIDNumberOnTheEditDeatilsPage() {
    return waitForExpectedElement(SAID_NUMBER_TEXTBOX).getAttribute("value").isEmpty();

    }
    public void userEntersValidSAIDNumberInEditDetailsAndSave() {
        waitForExpectedElement(SAID_NUMBER_TEXTBOX).sendKeys("3612045800082");
        clickByElementByQueryJSExecutor(saveButton);
    }

    public void verifyShippingAddress() {
        assertTrue(waitForExpectedElement(SHIPPING_ADDRESS).getText().contains(UrlBuilder.getMessage("streetAddressLine.key")));
        assertTrue(waitForExpectedElement(SHIPPING_ADDRESS).getText().contains(UrlBuilder.getMessage("stateRegion.key")));
    }

    public void goToPasswordChangePageAndTakeEyesScreenshot() {
        final String pageName = "Password Change Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseco":
            case "vusede":
            case "vuseit":
                clickByElementByQueryJSExecutor(CHANGE_PASSWORD_CHECKBOX);
                waitForPage();
                eyes.check(pageName, Target.window().fully().ignore(firstNameField, lastName));
                scrollToPageTop();
                break;
            case "vuseza":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("myAccountDetailsURL.key"));
                clickByElementByQueryJSExecutor(CHANGE_PASSWORD_CHECKBOX);
                waitForPage();
                eyes.check(pageName, Target.window().fully().ignore(firstNameField, lastName));
                scrollToPageTop();
                break;
        }
    }

    public void selectChangePasswordCheckbox()
    {
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vusefr":
            case "vuseuk":
                clickByElementByQueryJSExecutor(CHANGE_PASSWORD_CHECKBOX);
                break;
        }
    }

    public void updatePasswordWithIncorrectCurrentPassword()
    {
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "it":
            case "vuseit":
                waitForExpectedElement(CURRENT_PASSWORD).sendKeys("incorrect");
                waitForExpectedElement(NEW_PASSWORD_VUSEIT).sendKeys(UrlBuilder.getMessage("loginValidPassword.key"));
                waitForExpectedElement(CONFIRMED_PASSWORD_VUSEIT).sendKeys(UrlBuilder.getMessage("loginValidPassword.key"));
                waitForItemToBeClickableAndClick(SAVE_BUTTON);
                break;
        }
    }

    public void goToMarketingPreferencePageAndTakeEyesScreenshot() {
        final String pageName = "Marketing Preference Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                waitForExpectedElement(MARKETING_PREFERENCE_LINK).click();
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("marketingPreferenceUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }

    }

    public void goToInsiderClubPageAndTakeEyesScreenshot() {
        final String pageName = "Insider Club Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "it":
                clickByElementByQueryJSExecutor(INSIDER_CLUB_LINK);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                clickIndexElementByQueryJSExecutor(CLOSE_INSIDER_CLUB_BUTTON,0);
                waitForPage();
                break;
        }
    }

    public void goToMyAccountYourTobaccoHeaterPageAndTakeEyesScreenshot() {
        final String pageName = "My Account Your Tobacco Heater Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
                clickIndexElementByQueryJSExecutor(MY_ACCOUNT_TOBACCO_HEATER_LINK,0);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToAddressBookPageAndTakeEyesScreenshot() {
        final String pageName = "Address Book Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("AddressBookUrl.key"));
                eyes.check(pageName, Target.window().fully().ignore(firstNameField, lastName));
                break;
            case "velopl":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("VeloAddressBookUrl.key"));
                eyes.check(pageName, Target.window().fully().ignore(firstNameField, lastName));
                break;
        }
    }

    public void goToRegisterDevicePageAndTakeEyesScreenshot() {
        final String pageName = "Register Device Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("RegisterDeviceUrl.key"));
                eyes.check(pageName, Target.window().fully().ignore(firstNameField, lastName));
                break;
        }
    }

    public String returnSuccessMessage() {
        return waitForExpectedElement(registeringConfirmationMsg, 5).getText();
    }

    public void goToMyAccountOverviewWithOrderPageAndTakeEyesScreenshot() {
        final String pageName = "My Account Overview With Order Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "pl":
                clickIndexElementByQueryJSExecutor(MY_ACCOUNT_LINK_GLO_PL, 0);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToMyAccountInsidersClubPageAndTakeEyesScreenshot() {
        final String pageName = "My Account Insiders Club Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitForExpectedElement(MY_ACCOUNT_INSIDERS_CLUB_LINK_VUSE_DE,3).click();
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToMyAccountDeleteAccountPageAndTakeEyesScreenshot() {
        final String pageName = "My Account Delete Account Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                waitForExpectedElement(MY_ACCOUNT_DELETE_ACCOUNT_LINK_VUSE_IT,3).click();
                eyesCheckFullyPage(pageName);
                break;
            case "vusede":
                waitForExpectedElement(MY_ACCOUNT_DELETE_ACCOUNT_LINK_VUSE_DE,3).click();
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "vuseza":
                waitForExpectedElement( MY_ACCOUNT_DELETE_ACCOUNT_LINK_VUSE_ZA,3).click();
                eyesCheckFullyPage(pageName);
                break;
            case "velopl":
                waitForExpectedElement( MY_ACCOUNT_DIRECTORY_LINK_VUSE_DE,3).click();
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToMyAccountEditAddressPagePopupAndTakeEyesScreenshot() {
        final String pageName = "My Account Edit Address Popup Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitForExpectedElement(MY_ACCOUNT_DIRECTORY_LINK_VUSE_DE,3).click();
                waitForExpectedElement(MY_ACCOUNT_EDIT_BILLING_ADDRESS_LINK_VUSE_DE,3).click();
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully());
                waitAndClickByElementByJSExecutor(MY_ACCOUNT_CLOSE_EDIT_ADDRESS_BUTTON_VUSE_DE,3);
                break;
        }
    }

    public void updatePasswordWithIncorrectConfirmPassword() {
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "fr":
            case "vusefr":
            case "lyftse":
            case "vuseuk":
                waitForExpectedElement(CURRENT_PASSWORD).sendKeys("loginValidPassword.key");
                waitForExpectedElement(NEW_PASSWORD_VUSEIT).sendKeys(UrlBuilder.getMessage("loginValidNewPassword.key"));
                waitForExpectedElement(CONFIRMED_PASSWORD_VUSEIT).sendKeys(UrlBuilder.getMessage("IncorrectloginValidNewPassword.key"));
                waitForItemToBeClickableAndClick(SAVE_BUTTON);
                break;
        }
    }

    public void clickNewsletterOptionAndSave() {
        if(isSelected(OPTION_EMAIL))
        {
            LOG.info("Already subscribe.");
            assertTrue(waitForExpectedElement(OPTION_EMAIL).isSelected());
        }
        else {
           clickUsingJS(OPTION_EMAIL);
           assertTrue(waitForExpectedElement(OPTION_EMAIL).isSelected());

    }
        if(isSelected(OPTION_OFFER))
        {
            LOG.info("Already subscribe.");
            assertTrue(waitForExpectedElement(OPTION_OFFER).isSelected());
        }
        else {
            clickUsingJS(OPTION_OFFER);
            assertTrue(waitForExpectedElement(OPTION_OFFER).isSelected());
        }
        clickUsingJS(NEWSLETTER_OPTION_RECORD);
    }

    public void unsubscribeTheNewsletter() {
            clickUsingJS(OPTION_EMAIL);
            clickUsingJS(OPTION_OFFER);
            clickUsingJS(NEWSLETTER_OPTION_RECORD);
    }

    public void reSubscribeNewsletterWithSameEmailAddress() {
        String email_address = waitForExpectedElement(USER_EMAIL_ADDRESS).getText();
        clickUsingJS(RECIEVE_NEWSLETTER_BUTTON);
        waitForExpectedElement(NEWSLETTER_POPUP);
        enterDataAndWait(EMAIL_NEWSLETTER,email_address);
        clickUsingJS(EMAIL_MARKETING);
        clickUsingJS(MOBILE_MARKETING);
        clickUsingJS(SUBSCRIBE_NEWSLETTER);
        waitForExpectedElement(CLOSE_NEWSLETTER_POPUP);
        clickUsingJS(CLOSE_NEWSLETTER_POPUP);
    }

    public void assertNewsletterOptionSelected() {
        assertTrue(waitForExpectedElement(OPTION_EMAIL).isSelected());
        assertTrue(waitForExpectedElement(OPTION_OFFER).isSelected());
    }

    public void goToMyAccountOverviewPageAndTakeEyesScreenshot() {
        final String pageName = "My Account Overview Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                waitAndClickByElementByJSExecutor(MY_ACCOUNT_LINK_VUSE_KZ,3);
                eyes.check(pageName, Target.window().fully().ignore(UK_ACCOUNT_USER_NAME,HISTORY_ORDER_NUMBER));
                break;
            case "velopl":
                waitAndClickByElementByJSExecutor(MY_ACCOUNT_LINK_VELO_PL,3);
                eyes.check(pageName, Target.window().fully().ignore(UK_ACCOUNT_USER_NAME,HISTORY_ORDER_NUMBER));
                break;
        }
    }

    public void viewOrderDetails() {
      clickUsingJS(VIEW_ORDER_LINK_LYFT);
      assertTrue(isElementDisplayedBySeconds(ORDER_DETAILS_PAGE, 10));
    }

    public void updatePasswordWithCorrectConfirmPassword() {
                waitForExpectedElement(CURRENT_PASSWORD).sendKeys(UrlBuilder.getMessage("loginValidPassword.key"));
                waitForExpectedElement(NEW_PASSWORD_VUSEIT).sendKeys(UrlBuilder.getMessage("loginValidNewPassword.key"));
                waitForExpectedElement(CONFIRMED_PASSWORD_VUSEIT).sendKeys(UrlBuilder.getMessage("loginValidNewPassword.key"));
                waitForItemToBeClickableAndClick(SAVE_BUTTON);

    }
}
