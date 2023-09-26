package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.CodiceFiscaleGenerator;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.admin.LoginPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel;
import com.salmon.test.page_objects.gui.newsLetter.NewsLetterPage;
import com.salmon.test.step_definitions.gui.MyAccountPageSteps;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.randomDateOfBirth;
import static com.salmon.test.page_objects.gui.constants.Context.*;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.*;

public class RegistrationPage extends PageObject {

    public LogininPage logininPage;
    public LoginPage loginPage;
    public AccountDashboardPage accountDashboardPage;
    private ScenarioContext scenarioContext;
    public NewsLetterPage newsLetterPage;
    public MailinatorPage mailinatorPage;
    HomePage homePage = new HomePage();
    AddNewAddressPage addNewAddressPage = new AddNewAddressPage();
    PDP pdp;

    public RegistrationPage(TestContext context,LogininPage logininPage, LoginPage loginPage, AccountDashboardPage accountDashboardPage,NewsLetterPage newsLetterPage,MailinatorPage mailinatorPage) {
        scenarioContext = context.getScenarioContext();
        this.logininPage=logininPage;
        this.loginPage=loginPage;
        this.accountDashboardPage=accountDashboardPage;
        this.newsLetterPage=newsLetterPage;
        this.mailinatorPage=mailinatorPage;
        this.pdp=pdp;
    }

    public RegistrationPage() {
    }

    private static final String EMPTY_STRING = "";
    private static final String SAID_NUMBER_LABEL = "label[for=\"document_value\"]";
    private static final String FIRST_NAME_LABEL = "label[for=\"firstname\"]";
    private static final String LAST_NAME_LABEL = "label[for=\"lastname\"]";
    private static final String DOB_LABEL = "label[for=\"dob\"]";
    private static final String GENDER_LABEL = "label[for=\"gender\"]";
    private static final String STREET_LABEL = "label[for=\"street_1\"]";
    private static final String CITY_LABEL = "label[for=\"city\"]";
    private static final String POSTCODE_LABEL = "label[for=\"zip\"]";
    private static final String EMAIL_LABEL = "label[for=\"email_address\"]";
    private static final String PASSWORD_LABEL = "label[for=\"password\"]";
    private static final String TELEPHONE_LABEL = "label[for=\"telephone\"]";
    private static final String COUNTRY_LABEL = "label[for=\"country\"]";
    private static final String CONFIRM_PASSWORD_LABEL = "label[for=\"password-confirmation\"]";
    private static final String CONSENT_AGE_LABEL = "label[for=\"consent_bat_agreement\"]";
    private static final String CONSENT_AGREEMENT_LABEL = "label[for=\"agreement-24\"]";
    private static final String CONSENT_EMAIL_LABEL = "label[for=\"consent_email_marketing\"]";
    private static final String CONSENT_MOBILE_LABEL = "label[for=\"consent_mobile\"]";
    private static final String CONSENT_FACEBOOK_LABEL = "label[for=\"consent_third_party\"]";
    private static final String CONSENT_ALLTHIRDPARTY_LABEL = "label[for=\"consent_select_all\"]";
    private static final String CONSENT_HYPERCARE_LABEL = "label[for=\"consent_trial_feedback\"]";
    private static final By NEWS_LETTER_CONSENT_BOX_kz = By.cssSelector("#consent_email_marketing");
    private static final By SUCCESS_LINE_VUSECO = By.cssSelector("#maincontent > div.columns > div.column.main > p");
    public static final By ERROR_MESSAGE_ADDRESS_FIELD = By.cssSelector("div.field-error,div#street_1-error");


    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    private String intPhoneNumber = "31"+random(10,NUMERIC);
    public String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private String companyName = random(20, ALPHABETS);
    private String phoneNumberIT = "11"+random(8,NUMERIC);
    private String PHONE_NUMBER_IT_MOBILE = "11"+random(8,NUMERIC);
    public String postCodeData = "UB10 9DW";
    public String townOrCityData = random(6, ALPHABETS);
    private String PHONENUMBER_GLOKZ = "+79"+random(9,NUMERIC);
    private String CUSTOMER_ID = random(12,NUMERIC);
    public static final By EMAIL_SENDER = By.cssSelector("#email_pane > div > div.message-header.d-flex.justify-content-between.m-t-20.m-b-25.m-x-32 > div.sender-info.d-flex.flex-column.p-xy-20.w-100.primary-border-r > div:nth-child(2) >");
    public static final By EMAIL_RECEIVER = By.cssSelector("#email_pane > div > div.message-header.d-flex.justify-content-between.m-t-20.m-b-25.m-x-32 > div.sender-info.d-flex.flex-column.p-xy-20.w-100.primary-border-r > div:nth-child(1) > div.from.fz-20.ff-futura-book.p-l-20.ng-binding >");
    public static final By EMAIL_HEADER = By.cssSelector("div.sender-info.d-flex.flex-column.p-xy-20.w-100.primary-border-r > div");
    public static final By EMAIL_HEADER_MESSAGE=By.cssSelector(".fz-20.ff-futura-demi.gray-color.ng-binding,div.wrapper-title.d-flex.align-items-center>div[class*='ff-futura-demi gray-color ng-binding']");
    public static final By MESSAGE_TITLE = By.cssSelector(".wrapper-inner table.main h1");
    // ELEMENT MAPPING
    // Tick boxes
    public By signUpNewsLetterTickBoxVuseUk = By.cssSelector("#form-validate > fieldset.fieldset.create.account > div.field.choice.newsletter.choice-box__container > div > div:nth-child(1) > label");
    public static final By SIGNUP_MARKETING_EMAIL_VUSEUK = By.cssSelector("div.marketing-method > input#email_marketing");
    public By signUpNewsLetterTickBoxUk = By.cssSelector("#is_subscribed");
    public By signUpNewsLetterTickBox = By.cssSelector("#subscription");
    private static final By NEWSLETTER_TICKBOX_DE =By.cssSelector("#global_loyalty_optin");
    public By readTsAndCs = By.cssSelector("#register-input-custom");
    public static final By PRIVACY_POLICY_TICKBOX_VELOEUDE = By.cssSelector("div[class='field choice accept_conditions required'] span[class='checkmark']");
    public static final By PRIVACY_POLICY_TICKBOX_VELOBE = By.cssSelector("#form-validate > fieldset.fieldset.create.account > div:nth-child(6) > input");
    public static final By PRIVACY_POLICY_TICKBOX = By.cssSelector("#register-input-policy");
    private static final By TERMS_CONDITION_CHECKBOX=By.cssSelector("#terms-and-conditions");
    private static final By TERMS_CONDITION_CHECKBOX_VELOBE=By.cssSelector("#form-validate > fieldset.fieldset.create.account > div:nth-child(7) > input");
    public By readTsAndCs_it = By.cssSelector("div.input_box >label[for='register-input-privacy']");
    public By readTsAndCs_kz = By.cssSelector("input[name='terms-and-conditions']");

    public static final By TERMS_AND_CONDITIONS_VELOBE = By.cssSelector("#agreement-26");
    public static final By TERMS_AND_CONDITIONS_VELOPL = By.cssSelector(".choice-box__label.label");
    public static final By NEWSLETTER_SUBSCRIBE_CHECKBOX = By.cssSelector("#is_subscribed");
    public static final By REMEMBERME_CHECKBOX = By.cssSelector("#remember-me-box > label");
    public static final By GENDER_DROPDOWN = By.cssSelector("#gender");
    public static final By CONSENT_AGREEMENT_CHECKBOX = By.cssSelector("#consent_bat_agreement");
    public static final By CONSENT_EMAIL_CHECKBOX = By.cssSelector("#consent_email_marketing");
    public static final By CONSENT_MOBILE_CHECKBOX = By.cssSelector("#consent_mobile");
    public static final By CONSENT_SOCIALNETWORK_CHECKBOX = By.cssSelector("#consent_third_party");
    public static final By CONSENT_ALL_CHECKBOX = By.cssSelector("#consent_select_all");
    public static final By CONSENT_TRIAL_FEEDBACK_CHECKBOX = By.cssSelector("#consent_trial_feedback");
    public static final By MOBILE_SOCIAL_NEWSLETTER_CHK_BOX_IT= By.cssSelector("#mobile_consent");

    // Text
    public By pageTitle = By.cssSelector("span.base");
    private By pageHeading = By.cssSelector(".block-customer-register-heading"); // main heading
    private static final By PAGE_HEADING_DE =By.cssSelector("div.register-header-wrapper>h1");

    // intractable elements
    public static final By EXPAND_ADDRESS_LINK_VELO= By.cssSelector("div.address-manual-button>span");
    public By expandManualAddressFields = By.cssSelector("div.address-manual-button.active,div.address-manual-button");
    public By collapseManualAddressFields = By.cssSelector("div.address-search-button");
    public static final By EXPAND_MANUAL_ADDRESS_UK = By.cssSelector("div.address-manual-button");
    public static final By EXPAND_MANUAL_ADDRESS_VELOPL = By.xpath("//*[@class='alink']");
    public static final By EXPAND_ADDRESS_LINK_CHECKOUT = By.xpath("//a[contains(@id,'address-search-toggle')]");
    public final static By FIRST_NAME_INPUT_REGISTRATION_MXCX = By.cssSelector("#form-validate > fieldset:nth-child(3) #firstname");
    public final static By LAST_NAME_INPUT_REGISTRATION_MXCX = By.cssSelector("#form-validate > fieldset:nth-child(3) #lastname");
    public By firstNameInput = By.cssSelector(" fieldset.fieldset.create.info input#firstname");
    public By surNameInput = By.cssSelector("fieldset.fieldset.create.info input#lastname");
    public By NAME_PRIFIX = By.cssSelector("div.field.field-name-prefix > div > div:nth-child(2)");
    public By firstNameInput_mx = By.cssSelector(" fieldset:nth-child(3) > div.field.field-name-firstname.required > div > input");
    public By surNameInput_mx = By.cssSelector("fieldset:nth-child(3) > div.field.field-name-lastname.required > div > input");
    public By firstNameInput_it = By.cssSelector("form.form.create.account.form-create-account input#firstname");
    public By DOBInput = By.cssSelector("#dob"); // format = 08/05/2019
    public By DOBInputVuseDE = By.cssSelector("input#dob");
    public By gender = By.cssSelector("#gender");
    public By company = By.cssSelector("#company"); // not mandatory
    public final static By phoneNumber_paymentpage = By.xpath("//*[@name='telephone']");
    public By streetAddressLine1_paymentpage = By.xpath("//*[@name='street[0]']");
    public By streetAddressLine2_paymentpage = By.xpath("//*[@name='street[1]']");
    public By streetAddressLine3_paymentpage = By.xpath("//*[@name='street[2]']");
    public By country_paymentpage = By.xpath("//*[@name='country_id']");
    public By city_paymentpage = By.xpath("//*[@name='city']");
    public By postCode_paymentpage = By.xpath("//*[@name='postcode']");
    public final static By phoneNumber = By.cssSelector("input.input-text#telephone");
    public By streetAddressLine1 = By.cssSelector("#street_1");
    public By streetAddressLine2 = By.cssSelector("#street_2");
    public By streetAddressLine3 = By.cssSelector("#street_3");
    public By city = By.cssSelector("#city");
    public By postCode = By.cssSelector("input#zip");
    public final static By ADDRESS_CHOOSE = By.cssSelector("#Edit_address_modal_2226754 > form > fieldset.fieldset.default-choices");
    public By country = By.cssSelector("select#country");
    public By citySelector = By.cssSelector("select#city");
    public By homeTown = By.cssSelector("input#birth_city");
    public By postalCode = By.cssSelector("input#codice_fiscale");
    public By province = By.cssSelector("#region_id");
    public By provinceOptions = By.cssSelector("#region_id option:not([value=''])");
    public By txtOutDoorNumber = By.cssSelector("input#address\\:neo_ext");
    public By drpMXState = By.cssSelector("#region_id");
    public By txtFiscalCode = By.cssSelector("#codice_fiscale");
    public By VeloDE_GENDER = By.cssSelector("#female ~ span");
    public static final By DATE_PICKER = By.cssSelector("button[class='ui-datepicker-trigger v-middle']");
    public By MGM_PROMO_CODE = By.cssSelector("#mgm_promo_code");
    public By txtBirthCity = By.cssSelector("[name=birth_city]");
    public By drpITState = By.cssSelector("#region_id");
    public By bankId = By.cssSelector("#bank_id");
    public By citizenCardNumber = By.cssSelector("input#citizen_card_number");
    public By ContinueVuseDE = By.cssSelector("button[id=wtc-btn-0]");
    private static final By REG_ADD_ADDRESS_MANUALLY = By.cssSelector("div.address-manual-button > span");
    public By eleHomePageActionBar = By.cssSelector("div.header-vype-action-bar div.cyan-theme:nth-child(1) > div.pagebuilder-column-group");
    public By btnEditAccountDetails = By.cssSelector("div.editBtn>a.action.edit");
    public By btnYourAccount = By.xpath("//*[@class='pagebuilder-button-primary']//preceding::a[@href='customer/account'][2]");
    public By btnContinueShopping = By.xpath("//*[@class='pagebuilder-button-primary']//preceding::div[@class='continue-shopping'][2]/a");
    public final static By EMAIL_INPUTBOX = By.cssSelector("input#email.input-text");
    public final static By PASSWORD_INPUTBOX = By.cssSelector("input#pass.input-text");
    public final static By SIGNIN_BUTTON = By.cssSelector("#send2");
    private static final By SA_ID_NUMBER=By.cssSelector("#za_document_value");
    private final static By ACCOUNT_SIDEBAR = By.cssSelector("#account-nav");
    public final static By MANUALLY_ENTER_ADDRESS_ZA=By.cssSelector("#js--address-search-toggle");


    // sign in information
    public By email = By.cssSelector("#email_address");
    public By password = By.cssSelector("#password");
    public By passwordConfirmation = By.cssSelector("#password-confirmation");
    public String passwordData = "Pa55w@rd12345";
    public final By EmailConfirmationMessage = By.cssSelector("td.ng-binding");
    private final static By EMAIL_RECEIVED = By.cssSelector("td.ng-binding");
    private final static By M_EMAIL_RECEIVED_VUSEUK = By.cssSelector("div > div.block-subject.w-100 > a");
    private final static By M_IFRAME_VUSEUK = By.cssSelector("#html_msg_body");
    private final static By M_CONFIRM_BTN_VUSEUK = By.cssSelector("table.inner-wrapper > tbody > tr > td > a[rel='nofollow']");
    private final static By M_CONFIRM_BTN_GLODE = By.cssSelector("table.main > tbody > tr > td.main-content > a[rel='nofollow']");
    private final static By M_RESET_PWD_EMAIL_BTN_GLOIT = By.cssSelector("table.full-width-table > tbody > tr.email-content > td > a[rel='nofollow']");
    private String randomPhoneNumber = "01"+random(10,NUMERIC);
    private static final String RANDOM_DE_PHONE_NUMBER = "54"+random(10,NUMERIC);
    private static final String RANDOM_FR_PHONE_NUMBER = "61"+random(10,NUMERIC);
    private String randomCoPhoneNUmber = "11"+random(9,NUMERIC);
    private String randomMXPhoneNUmber = "5510101010";
    private static final String RANDOM_CO_PHONE_NUMBER_MOBILE = "1"+random(9,NUMERIC);
    private static final String RANDOM_MX_PHONE_NUMBER_MOBILE = 6 + random(9,NUMERIC);
    private String IntMGMPromoCode = "MGM-"+random(5,NUMERIC);
    private static final By PRIVACY_CHECK = By.cssSelector("input#register-input-privacy");
    private static final By PRIVACY_CHECK_PL = By.cssSelector("#consent_bat_agreement");
    private static final By AGREEMENT_CHECK = By.cssSelector("input#consent-bat-agreement-fake");
    private static final By AGREEMENT_CHECK_PL = By.cssSelector("#agreement-10");
    private static final By AgreeTsCsUkCx = By.cssSelector("#agreement-1");
    public static final By AGREE_AWARE_MY_CARD_UK= By.cssSelector("input#agreement-25");
    private static final By AGREE_TSCS_VUSE_DE = By.cssSelector("#agreement-18");
    private static final By AGREE_LOYALTY_VUSE_DE = By.cssSelector("#global_loyalty_optin");
    private static final By AGREE_TSCS_VYPE_FR_CO = By.cssSelector("#agreement-5,#agreement-9");
    private static final By AGREE_TSCS_VYPE_FR_MX = By.cssSelector("#agreement-5,#agreement-6");
    private static final By AGREE_TSCS_VYPE_VUSEZA = By.cssSelector("#agreement-22");
    private static final By AGREEMENT_CHECK_GLODE = By.cssSelector("input#register-input-custom");
    public static final By SHOW_BUTTON = By.cssSelector("a#pills-links-tab");
    public static final By CLICK_LINKS = By.cssSelector("#pills-links-content a");
    public By messageContainer = By.cssSelector("div[class*='message'] .message");
    public static final By REGISTRAION_ERROR_MESSAGE = By.cssSelector("div.message-error.error.error.message");
    public static final By REGISTRATION_DOB_ERROR_MESSAGE = By.cssSelector("div#dob-error.mage-error");
    public static final By REGISTRATION_CODICE_FISCALE_ERROR_MESSAGE = By.cssSelector("div#fiscale-error");
    public static final By REGISTRATION_EXIST_EMAIL_ERROR_MESSAGE = By.cssSelector("div.message-error.error.message");
    public static final By SUCCESS_MESSAGE = By.cssSelector(".message-success > div");
    public static final By EMAIL_NEWSLETTER_CHKBOX = By.cssSelector(".field.marketing-methods .marketing-method:nth-child(1) input");
    private static final By EMAIL_NEWSLETTER_CHKBOX_FR=By.cssSelector("#consent_email_marketing");
    private static final By AGREE_TSCS_VYPE_IT = By.cssSelector("#agreement-19,#agreement-21,#agreement-20");
    public static final By DUPLICATE_EMAIL_ERROR_MESSAGE = By.cssSelector("div.page.messages > div:nth-child(2) > div > div > div");

    // buttons
    public final static By CREATEACCOUNT_BUTTON = By.cssSelector("button.action.submit.primary,button.submit");
    public final static By CREATEACCOUNT_BUTTON_CO = By.cssSelector("button[title='CREAR CUENTA']");
    public final static By M_CREATEACCOUNT_BUTTON = By.cssSelector("button.action.submit.primary");
    private final static By STREET2=By.cssSelector("input#street_3");
    private static final By SELECT_REGIONID = By.cssSelector("select#region_id");
    private static final By SELECT_CITY = By.cssSelector("select#city");
    public final static By M_CREATEACCOUNT_BUTTON_FR = By.cssSelector("#form-validate > div > div > button");
    public final static By POST_CODE_RESULTS_DROPDOWN_ITEM=By.cssSelector("div.pca.pcalist>div>b");
    //Vuse
    private final static By IS_SUBSCRIBED_CHECKBOX=By.cssSelector("input#is_subscribed");
    public final static By THIRD_PARTY_CONSENT_CHK_BOX = By.cssSelector("input#consent_third_party");
    public final static By SUBSCRIPTION_MESSAGE=By.cssSelector("div.page.messages > div:nth-child(2) > div > div > div");
    public static final By ADDRESS_SEARCH_VUSEDE = By.cssSelector("input[name='address-search']");
    public static final By ADDRESS_SEARCH_VUSEIT = By.cssSelector("input#pac-input");
    public static final By STREET_ADDRESS_VELOZA = By.cssSelector("input[name='street[2]']");
    public static final By ADDRESS_SEARCH_RESULT = By.cssSelector("div.pcaitem");
    public static final By STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON = By.cssSelector("#default_address_change");
    public static final By MANUAL_ADDRESS_ENTRY_VELOZA=By.cssSelector("#js--address-search-toggle");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE = By.cssSelector("#form-validate > fieldset.fieldset.default-choices > div.field.choice.set.shipping.edit-add-radio > div > div > label");
    public static final By AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE_BUTTON = By.cssSelector("#form-validate > div.actions-toolbar > div.primary > button");
    public static final By MANUAL_ADDRESS_ENTRY_VUSEDE = By.cssSelector("#form-validate > fieldset.fieldset.address > div.address-manual-button.active > span");
    public static final By MANUAL_ADDRESS_ENTRY_LINK = By.cssSelector("#form-validate > fieldset.fieldset.address > div.address-manual-button.active > span,div.address-manual-button");
    public static final By INPUT_FIELD = By.cssSelector("input[name='address-search'],input#pac-input");
    public static final By DELETE_MY_ACCOUNT_LYFT = By.cssSelector("#account-nav > ul > li:nth-child(10) > a");
    public static final By DELETE_MY_ACCOUNT_DESCRIPTIONLYFT = By.cssSelector("div.delete-my-account");

    private final static By SUBURBMXCX = By.cssSelector("#address\\:colonia");
    private final static By TermsAndConditionsLinkUnderLoyaltyConsent_VUSEDE = By.cssSelector(".form.create.account.form-create-account a[href*='vuse-insidersclub']");
    private final static By ENTER_ADDRESS_MANUALLY_LINK_UK = By.cssSelector("div.address-manual-button");
    public final static By MOBILE_NUMBER_CHECKBOX = By.cssSelector("#mobile_text_required");
    private final static By MOBILE_NUMBER_EDIT_ADDRESS= By.cssSelector("div.field.telephone.required:nth-child(7) > div.control > input#telephone");
    private final static By PHONE_NUMBER_TXT_BOX = By.cssSelector("input#telephone");
    private static final String AV_QR_URL = "/av/qr/";
    //Veldo EUDE
    private final static By LAND_OR_COUNTRY_VELOEUDE=By.cssSelector("#country option[selected='selected']");
    public static final By SHIPPING_STREET_HOUSE_NUMBER_VELODE = By.cssSelector("div[name= 'shippingAddress.street.0'] .control input");
    public static final By SHIPPING_CITY_VELODE = By.cssSelector("div[name='shippingAddress.city'] .control input");
    public static final By SHIPPING_POSTAL_VELODE = By.cssSelector("div[name='shippingAddress.postcode'] .control input");
    public static final By SHIPPING_TELEPHONE = By.cssSelector("#shipping-new-address-form > div:nth-child(10) >div>input");
    public static final By MANUAL_DELIVERY_ADDRESS_ENTRY_LINK = By.xpath("//*[text()='Manuelle Adresseingabe']");
    private static final By USER_LOGGED_IN_MESSAGE = By.cssSelector("p.welcome-message");
    public static final By CUSTOMER_ID_COLLECTION_FIELD = By.cssSelector("input#kz_iin");
    public static final By CUSTOMER_ID_FIELD_ERROR = By.cssSelector("div#kz_iin-error");
    public static final By CARD_INFO_CHECKBOX = By.cssSelector(".choice:nth-child(6) > .label");
    private static final By FROM_EMAIL_ADDRESS = By.xpath("//td/b[contains(text(),'info.it@vuse.com')]");
    private static final By TELEPHONE_ERROR_MESSAGE= By.cssSelector("div#telephone-error");
    // Velo BE
    public static final By REGISTRATION_FIRST_NAME_ERROR = By.cssSelector("#firstname-error");
    public static final By REGISTRATION_LAST_NAME_ERROR = By.cssSelector("#lastname-error");
    public static final By REGISTRATION_EMAIL_ERROR = By.cssSelector("#email_address-error");
    public static final By REGISTRATION_DOB_ERROR = By.cssSelector("#dob-error");
    public static final By REGISTRATION_GENDER_ERROR = By.cssSelector("#gender-error");
    public static final By REGISTRATION_CODICE_FISCALE_ERROR = By.cssSelector("#codice_fiscale-error");
    public static final By REGISTRATION_BIRTH_CITY_ERROR = By.cssSelector("#birth_city-error");
    public static final By REGISTRATION_ADDRESS_SEARCH_ERROR = By.cssSelector("#address-search-error");
    public static final By REGISTRATION_TELEPHONE_ERROR = By.cssSelector("#telephone-error");
    public static final By REGISTRATION_PASSWORD_ERROR = By.cssSelector("#password-error");
    private static final By REGISTRATION_PASSWORD_CONFIRM_ERROR = By.cssSelector("#password-confirmation-error");
    private static final By AGE_POLICY_CONFIRM_ERROR = By.cssSelector("#consent_bat_agreement-error");
    private static final By TERM_AND_CONDITION_CONFIRM_ERROR = By.cssSelector("#agreement-24-error");
    private static final By TERM_AND_CONDITION_CONFIRM_ERROR_VUSEIT = By.cssSelector("#agreement-20-error");
    public static final By REGISTRATION_SA_ID_ERROR = By.cssSelector("#za_document_value-invalid-error");
    private static final By CHAT_BUTTON = By.cssSelector("#Embed > div > div > button");
    private static final By START_CHAT_BUTTON = By.cssSelector("#Embed > div > div > div > div > div > div > form > footer > div > button");
    private static final By CHAT_ERROR = By.cssSelector("div[data-garden-id='forms.input_message']");
    private static final By CHAT_FRAME = By.cssSelector("iframe#launcher");
    private static final By YOTI_PAGE_HEADING = By.cssSelector("#intro > div > h1");
    private static final By AVALANCHE_CHECKOUT_AGREEMENT_TICKBOX = By.cssSelector("div.checkout-agreements-block-wrapper > div.checkout-agreements-block > div[data-role='checkout-agreements'] >  div.checkout-agreements > div.checkout-agreement > input.terms-and-conditions.required-entry");
    private static final By AVALANCHE_REGISTRATION_AGREEMENT_TICKBOX = By.cssSelector("input[id^='agreement-']");
    private static final By CHECKBOX = By.cssSelector("input[type='checkbox']");
    private static final By AVALANCHE_AGREEMENT_HEADER = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal-slide._show > div.modal-inner-wrap > header > h1");
    private static final By AVALANCHE_AGREEMENT_BODY = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal-slide._show > div.modal-inner-wrap > div.modal-content");
    private static final By AVALANCHE_AGREEMENT = By.cssSelector("#form-validate > fieldset.fieldset.create.account > div.terms-and-conditions");
    private static final By AVALANCHE_CLOSE_AGREEMENT_BUTTON = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal-slide._show > div.modal-inner-wrap > header > button");
    public static final  By AVALANCHE_AGREEMENT_PL = By.cssSelector("#form-validate > fieldset.fieldset.create.account > div.field.social-checks.velo_customer_consents.desktop-margin > div.field.choice.choice-box__container");
    public static final By AVALANCHE_SEARCH_RESULT = By.cssSelector("#html-body > div.pca > div:nth-child(1) > div.pca.pcalist > div.pcaitem.pcalastitem");
    private static final By DELETE_ACCOUNT_BUTTON = By.cssSelector("div.delete-my-account > form > div > div > button");
    private static final By DELETE_ACCOUNT_ERROR_MESSAGE = By.cssSelector("#confirm-error");
    private static final By DELETE_ACCOUNT_CHECKBOX = By.cssSelector("#confirm");
    private static final By DELETE_ACCOUNT_SUCCESS_MESSAGE = By.cssSelector("#maincontent > div.columns > div > p");

    //VeloPl
    public static final By PL_HOMEPAGE_IMAGES =By.cssSelector("img.pagebuilder-mobile-hidden");
    public enum ChatError
    {
        NAME, EMAIL, DEPARTMENT, MESSAGE
    }
    public static final By LOYALTY_POP_UP_CLOSE_BUTTON= By.cssSelector("aside.modal-popup.loyalty-popup.modal-slide._inner-scroll._show:nth-child(2) header.modal-header > button.action-close");
    private static final By HOME_PAGE_HEADER=By.cssSelector("div.cyan-theme.desktop-only.header-reassurance-msg:nth-child(1) div.row-full-width-inner > div.pagebuilder-column-group");
    public static final By ADD_ADDRESS_ACCORDIAN= By.cssSelector("legend.legend.accordion");
    public static final By ACCORDIAN_CONTINUE_BUTTON=By.xpath("//div[@class='field gender']//following::button[@class='action primary']");
    public static final By NEWSLETTER_FIRSTNAME = By.cssSelector("#firstname");
    public static final By NEWSLETTER_SURNAME = By.cssSelector("#lastname");
    private static final By NEWSLETTER_EMAIL = By.cssSelector("#newsletter");
    private static final By TERMS_AND_CONDITIONS_CHK_BOX = By.cssSelector("#terms-and-conditions");
    private static final By EMAIL_CONSENT_CHK_BOX = By.cssSelector("#user-consent");
    private static final By BANK_ID = By.cssSelector("#bank_id");
    private static final By DOB = By.cssSelector("input#newsletter_dob");
    private static final By SUBSCRIBE_BUTTON = By.cssSelector("div.actions > button.action.subscribe.primary");
    private SoftAssertions softAssertions;

    private void softAssertions(boolean contains) {
           }

    public String getPageHeading() {
        switch (UrlBuilder.getLocale()) {
            case "de":
                return waitForExpectedElement(PAGE_HEADING_DE).getText();
            default:
                return waitForExpectedElement(pageHeading).getText();
        }
    }

    public void checkTsCs(){
        switch (UrlBuilder.getLocale()) {
            case "it":
                //case "vypeit": Commenting out to avoid regression failures as step is not applicable, will update after confirming from Tracy
                clickByElementByQueryJSExecutor(readTsAndCs_it);
                break;
            case "fr":
            case "vusefr":
                clickByElementByQueryJSExecutor(AGREE_TSCS_VYPE_FR_CO);
                break;
            case "mx":
            case "vusemx":
                clickByElementByQueryJSExecutor(AGREE_TSCS_VYPE_FR_MX);
                break;
            case "vuseco":
                clickByElementByQueryJSExecutor(AGREE_TSCS_VYPE_FR_CO);
                break;
            case "kz":
                if (!getWebDriver().findElement(readTsAndCs_kz).isSelected()) {
                    clickUsingJS(readTsAndCs_kz);
                }
                break;
            case "pl":
                clickByElementByQueryJSExecutor(PRIVACY_CHECK_PL);
                clickByElementByQueryJSExecutor(AGREEMENT_CHECK_PL);
                clickByElementByQueryJSExecutor(newsLetterPage.CONSENT_ALL);
                break;
            case "vusede":
                clickByElementByQueryJSExecutor(AGREE_TSCS_VUSE_DE);
                break;
            case "uk":
            case "vuseuk":
                clickByElementByQueryJSExecutor(AgreeTsCsUkCx);
                break;
            case "vuseza":
                clickByElementByQueryJSExecutor(AGREE_TSCS_VYPE_VUSEZA);
                break;
            case "veloza":
                clickByElementByQueryJSExecutor(AGREE_TSCS_VYPE_VUSEZA);
                clickByElementByQueryJSExecutor(NEWSLETTER_SUBSCRIBE_CHECKBOX);
                break;
            case "vypeit":
            case "vuseit":
                clickByElementByQueryJSExecutor(AGREE_TSCS_VYPE_IT);
                clickByElementByQueryJSExecutor(CONSENT_EMAIL_CHECKBOX);
                clickByElementByQueryJSExecutor(MOBILE_SOCIAL_NEWSLETTER_CHK_BOX_IT);
                break;
            case "velode":
                clickByElementByQueryJSExecutor(PRIVACY_POLICY_TICKBOX_VELOEUDE);
                break;
            case "velopl":
                break;
            case "velobe":
                clickUsingJS(PRIVACY_POLICY_TICKBOX_VELOBE);
                clickUsingJS(TERMS_CONDITION_CHECKBOX_VELOBE);
                break;
            default:
                clickByElementByQueryJSExecutor(readTsAndCs);
        }
    }

    public void checkLoyalty() {
        if(UrlBuilder.getLocale().equals("vusede")) {
            clickByElementByQueryJSExecutor(AGREE_LOYALTY_VUSE_DE);
        }
        else {
            LOG.info("Locale does not have Loyalty checkbox");
        }
    }

    public void signUpForNewsLetterTickBoxSelection() {
        if(getWebDriver().getCurrentUrl().contains("mx/es/") || doesURLContain("vype.non-prod.marketing.bat.net/it/it/")||doesURLContain("vuse.non-prod.marketing.bat.net/it/it/")){
            try
            {
                clickUsingJS(signUpNewsLetterTickBox);
                assertTrue("newsLetter signup checkbox isn't selected as expected",waitForExpectedElement(signUpNewsLetterTickBox).isSelected());
            }
            catch(Exception ex)
            {
                LOG.info("Failed to click on 'Sign Up for Newsletter' check-box due to error: "+ex.getMessage());
            }
        }
        else
        {
            try
            {
                LOG.info("Trying to pull from config File!! : " + UrlBuilder.getMessage("newsLetterSignUp"));
                clickUsingJS(By.cssSelector(UrlBuilder.getMessage("newsLetterSignUp")));
            } catch (Exception e) {
                switch (UrlBuilder.getLocale()) {
                    case "vusede":
                        clickUsingJS(NEWSLETTER_TICKBOX_DE);
                        break;
                    default:
                        clickUsingJS(signUpNewsLetterTickBox);
                        assertTrue("newsLetter signup checkbox isn't selected as expected",waitForExpectedElement(signUpNewsLetterTickBox).isSelected());
                        LOG.info("\n Is selected : " + waitForExpectedElement(signUpNewsLetterTickBox).isSelected());
                }
            }
        }
    }

    public void addBankId()
    {
        getWebDriver().findElement(bankId).sendKeys("197506032916");
    }

    public void populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData() {
      switch(UrlBuilder.getLocale()){
          case "mx":
          case "vusemx":
          case "vuseco":
          enterDataAndWait(FIRST_NAME_INPUT_REGISTRATION_MXCX,firstNameData);
          enterDataAndWait(LAST_NAME_INPUT_REGISTRATION_MXCX,lastNameData);
          break;
        default:
          enterDataAndWait(firstNameInput,firstNameData);
          enterDataAndWait(surNameInput,lastNameData);
          scenarioContext.setContext(FIRST_NAME,firstNameData);
          scenarioContext.setContext(LAST_NAME,lastNameData);

      }
    }

    public void populatePersonalInformationFirstAndLastNameWithPreviousDeletedUser() {
                enterDataAndWait(firstNameInput, scenarioContext.getContext(FIRST_NAME).toString());
                enterDataAndWait(surNameInput,scenarioContext.getContext(LAST_NAME).toString());
                scenarioContext.setContext(FIRST_NAME,firstNameData);
                scenarioContext.setContext(LAST_NAME,lastNameData);

    }

    public void populateAddressFieldsForLyftUser() {
        enterText(phoneNumber, intPhoneNumber);
        enterText(streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
        enterText(city, UrlBuilder.getMessage("cityName.key"));
        enterText(postCode, UrlBuilder.getMessage("postalCode.key"));
    }

    public void populateSigninFields(String emailAddress) {
        this.emailAddressData = emailAddress;
        String password = UrlBuilder.getMessage("loginValidPassword.key");
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        scenarioContext.setContext(PASSWORD, password);

        enterDataUsingJS(waitForExpectedElement(email), emailAddress);
        enterDataUsingJS(waitForExpectedElement(this.password), password);
        enterDataUsingJS(waitForExpectedElement(passwordConfirmation), password);
        checkTsCs();
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case LYFTSE:
                checkPrivacyPolicy();
                break;
            case VUSEFR:
                checkEmailNewsletter();
                break;
            case PL:
                try {
                    if (System.getProperty("marketingChecks.key").equalsIgnoreCase("not required")) {
                        clickByElementByQueryJSExecutor(newsLetterPage.EMAIL_CONSENT_CHK_BOX);
                        clickByElementByQueryJSExecutor(newsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX);
                        clickByElementByQueryJSExecutor(THIRD_PARTY_CONSENT_CHK_BOX);
                    }
                }
                catch (Exception e){
                    LOG.info("Marketing Checks not required,proceed with normal flow");
                }

        }
    }

    public void populateSigninFieldsForLoyalty(String emailAddress) {
        this.emailAddressData = emailAddress;
        String password = UrlBuilder.getMessage("loginValidPassword.key");
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        scenarioContext.setContext(PASSWORD, password);

        enterText(email, emailAddress);
        enterText(this.password, password);
        enterText(passwordConfirmation, password);
        checkTsCs();
        checkLoyalty();
    }

    public void checkPrivacyPolicy() {
        clickByElementByQueryJSExecutor(PRIVACY_POLICY_TICKBOX);
    }

    public void checkEmailNewsletter(){
       if(UrlBuilder.getLocale().equals("vusefr"))
           clickByElementByQueryJSExecutor(EMAIL_NEWSLETTER_CHKBOX_FR);
       else
           clickByElementByQueryJSExecutor(EMAIL_NEWSLETTER_CHKBOX);
    }

    public void clickCreateAccountButtonVuseco(){
        if (UrlBuilder.isIpad()||UrlBuilder.isIPhone()) {
            waitForExpectedElement(CREATEACCOUNT_BUTTON, 20);
            waitForExpectedElement(M_CREATEACCOUNT_BUTTON).click();}
        else if(UrlBuilder.isDesktop()){
            try {
                clickByElementByQueryJSExecutor(CREATEACCOUNT_BUTTON_CO);
                waitForAjaxElementNotToBePresent(getWebDriver(),4);
                assertTrue(UrlBuilder.getLocale(), waitForExpectedElement(SUCCESS_LINE_VUSECO, 10).getText().contains("Hola de nuevo,"));
            } catch (Exception ex) {
                LOG.info("getting into catch block");
                clickCreateAccountFromElementsList();
                waitForAjaxElementNotToBePresent(getWebDriver(),10);
                getWebDriver().navigate().refresh();
            }
        }
        else {
            clickCreateAccountFromElementsList();
        }
        if(!getCurrentUrl().contains("checkout"))
            assertTrue("/n **** ERROR COMPLETING REGISTRATION :: COLUMBIA SUCCESS MESSAGE NOT PRESENT FOR : " + UrlBuilder.getLocale(), waitForExpectedElement(SUCCESS_LINE_VUSECO, 30).getText().contains("Hola de nuevo,"));

    }

    public void clickCreateAccountFromElementsList(){
        waitForExpectedElement(CREATEACCOUNT_BUTTON,10);
        switch(UrlBuilder.getLocale())
        {
            case "vuseza":
            case "veloza":
                clickUsingJS(webDriver.findElements(CREATEACCOUNT_BUTTON).get(0));
                break;
            case "vusefr":
                waitForExpectedElement(CREATEACCOUNT_BUTTON,20);
                jsScrollElementInCenter(waitForExpectedElement(CREATEACCOUNT_BUTTON));
                waitForExpectedElement(CREATEACCOUNT_BUTTON).click();
                break;
            case "vuseco":
                if(!doesURLContain("customer/account/index"))
                    clickElementByQueryJSExecutor(webDriver.findElements(CREATEACCOUNT_BUTTON).get(0));
                break;
            default:
                clickElementByQueryJSExecutor(webDriver.findElements(CREATEACCOUNT_BUTTON).get(0));
        }
        waitForExpectedElement(SUCCESS_MESSAGE,10);
        LOG.info("Able to login. Success message is available "+isElementPresent(SUCCESS_MESSAGE,10));
    }

    public void userSelectsTheCreateAnAccountButton() {
        switch (UrlBuilder.getLocale()){
            case "vuseco":
                clickCreateAccountButtonVuseco();
                break;
            case "kz":
                if (UrlBuilder.isIPhone() || UrlBuilder.isDesktop() || UrlBuilder.isSamsungMobile()||UrlBuilder.isIpad()) {
                    waitForExpectedElement(CREATEACCOUNT_BUTTON, 10);
                    clickElementByQueryJSExecutor(webDriver.findElements(CREATEACCOUNT_BUTTON).get(0));
                    waitForExpectedElement(SUCCESS_MESSAGE, 30);
                } else {
                    waitForExpectedElement(CREATEACCOUNT_BUTTON, 10);
                    waitForExpectedElement(CREATEACCOUNT_BUTTON).click();
                }
                break;
            case "veloza":
            case "velopl":
            case "velobe":
                waitForExpectedElement(CREATEACCOUNT_BUTTON, 10);
                clickElementByQueryJSExecutor(webDriver.findElements(CREATEACCOUNT_BUTTON).get(0));
                break;
            default:
                clickCreateAccountFromElementsList();
        }
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }
    public void populateAddressFieldsPayment() {
        if ("velopl".equals(UrlBuilder.getLocale())) {
            waitForAjaxElementNotToBePresent(webDriver, 10);
            enterText(phoneNumber_paymentpage, "519340985");
            waitForAjaxElementNotToBePresent(webDriver, 10);
            enterText(streetAddressLine1_paymentpage, "Listopada 137");
            enterText(streetAddressLine2_paymentpage, "11");
            enterText(streetAddressLine3_paymentpage, "11");
            enterText(country_paymentpage, "Poland");
            enterText(city_paymentpage, "Sosnowiec");
            waitClearAndEnterText(postCode_paymentpage, "12-345");
        }
        if ("veloza".equals(UrlBuilder.getLocale())) {
            waitForAjaxElementNotToBePresent(webDriver, 10);
            if (!isElementDisplayedBySeconds(STREET_ADDRESS_VELOZA, 5))
                clickUsingJS(MANUALLY_ENTER_ADDRESS_ZA);
            if (isElementDisplayedBySeconds(STREET_ADDRESS_VELOZA, 5)){
                enterText(STREET_ADDRESS_VELOZA, "12 Albert Street");
                selectValueFromDropDownByby(UrlBuilder.getMessage("Province.key"), province);
                clickUsingJS(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE);
                clickUsingJS(AVALANCHE_ADDRESS_BOOK_ADD_NEW_ADDRESS_MANUALLY_SAVE_BUTTON);
        }}


    }


    public void populateAddressFields() {
        String currentAddress;
        String newAddress;
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
                enterDataAndWait(streetAddressLine1,"12 The Cloisters");
                enterDataAndWait(txtOutDoorNumber,UrlBuilder.getMessage("outdoorNumber.key"));
                enterDataAndWait(city,townOrCityData);
                selectValueFromDropDownByby(UrlBuilder.getMessage("stateRegion.key"),drpMXState);
                enterDataAndWait(postCode,postCodeData);
                enterDataAndWait(SUBURBMXCX,"MexicoSuburb");
                if(UrlBuilder.isDesktop()){
                    enterText(phoneNumber,randomMXPhoneNUmber);
                }
                else{
                    enterText(phoneNumber,RANDOM_MX_PHONE_NUMBER_MOBILE);
                }
                break;
            case "vypeit":
            case "vuseit":
                generateFiscalAndPopulate();
                if(UrlBuilder.isDesktop()){
                    enterDataAndWait(phoneNumber,phoneNumberIT);
                }
                else{
                    enterText(phoneNumber,PHONE_NUMBER_IT_MOBILE);
                }
                clickByElementByQueryJSExecutor(REG_ADD_ADDRESS_MANUALLY);
                enterDataAndWait(streetAddressLine1,UrlBuilder.getMessage("streetAddress.key"));
                enterDataAndWait(city,UrlBuilder.getMessage("AddressCity.key"));
                selectValueFromDropDownByby(UrlBuilder.getMessage("stateRegion.key"),drpITState);
                enterDataAndWait(postCode,"35040");
                if(getWebDriver().findElements(MGM_PROMO_CODE).size()>0)
                    enterDataAndWait(MGM_PROMO_CODE,IntMGMPromoCode);
                enterDataAndWait(txtBirthCity,UrlBuilder.getMessage("birthCity.key"));
                break;
            case "nl":
                enterText(streetAddressLine1,"12 The Cloisters");
                enterText(city,townOrCityData);
                enterText(postCode,postCodeData);
                break;

            case "fr":
              if(isElementDisplayedBySeconds(expandManualAddressFields,2)){
                scrollToElement(expandManualAddressFields);
                clickUsingJS(expandManualAddressFields);
              }
                enterText(phoneNumber,RANDOM_FR_PHONE_NUMBER);
                enterText(streetAddressLine1,"12 The Cloisters");
                enterText(city,townOrCityData);
                enterText(postCode,postCodeData);
                break;
            case "vusefr":
                if (UrlBuilder.isDesktop() && UrlBuilder.isSafari()) { // Mac Safari
                    enterDataUsingJS(waitForExpectedElement(phoneNumber), "+33772345986");
                } else {
                    enterText(phoneNumber, "772345986");
                }
                enterText(ADDRESS_SEARCH_VUSEDE, "BIDOU, 64460 AAST, FRANCE");
                waitForExpectedElement(ADDRESS_SEARCH_RESULT).click();
                break;
            case "vuseco":
                enterText(streetAddressLine1,"12 The Cloisters");
                selectValueFromDropDownByIndex(5,SELECT_REGIONID);
                selectValueFromDropDownByIndex(0, SELECT_CITY);
                if(UrlBuilder.isDesktop()){
                    enterText(phoneNumber,randomCoPhoneNUmber);
                }
                else{
                    enterText(phoneNumber,RANDOM_CO_PHONE_NUMBER_MOBILE);
                }
                enterText(citizenCardNumber,"12345");
                break;
            case "it":
                String fiscalCode = RegistrationPageModel.builder().build()
                        .getValidFiscalCode(UrlBuilder.getMessage("validDOB.key"));
                enterText(txtFiscalCode, fiscalCode.toLowerCase());
                verifyCodiceFiscalInUpperCase(fiscalCode);
                enterText(city,townOrCityData.toLowerCase());
                enterText(phoneNumber,"1234567890");
                enterText(postCode,postCodeData);
                enterText(streetAddressLine1,"12 The Cloisters");
                break;
            case "glode":
                enterText(city,townOrCityData);
                enterText(postCode,postCodeData);
                enterText(streetAddressLine1,"12 The Cloisters");
                break;
            case "kz":
                enterText(streetAddressLine1,"12 The Cloisters");
                enterText(city,townOrCityData);
                enterText(postCode,"100600");
                if (UrlBuilder.isDesktop() && UrlBuilder.isSafari()) { // Mac Safari
                    enterText(phoneNumber, "+7" + PHONE_NUMBER_IT_MOBILE);
                } else {
                    enterText(phoneNumber, PHONE_NUMBER_IT_MOBILE);
                }
                break;
            case "lyftdk":
                enterText(company,companyName);
                enterText(phoneNumber,"12345678");
                enterText(streetAddressLine1,"12 The Cloisters");
                enterText(city,townOrCityData);
                enterText(postCode,postCodeData);
                break;
            case "pl":
                break;
            case "uk":
                if(isElementDisplayedBySeconds(expandManualAddressFields,2)){
                    scrollToElement(expandManualAddressFields);
                    clickUsingJS(expandManualAddressFields);
                }
                enterText(phoneNumber,"7729353241");
                enterText(company,companyName);
                enterText(streetAddressLine1,"12 The Cloisters");
                enterText(city,townOrCityData);
                enterText(postCode,postCodeData);
                clickByElementByQueryJSExecutor(signUpNewsLetterTickBoxUk);
                break;
            case "vuseuk":
                enterText(phoneNumber,"7729353241");
                enterText(ADDRESS_SEARCH_VUSEDE, "Oberschwemke 1");
                waitForExpectedElement(ADDRESS_SEARCH_RESULT).click();
                break;
            case"veloza":
                enterText(phoneNumber,"123456789");
                waitClearAndEnterText(phoneNumber,"9876543210");
                enterText(ADDRESS_SEARCH_VUSEDE, "12 Albert Street");
                waitForExpectedElement(ADDRESS_SEARCH_RESULT, 5).click();
                if(isElementDisplayedBySeconds(STREET_ADDRESS_VELOZA,2))
                    enterText(STREET_ADDRESS_VELOZA, "12 Albert Street");
                if(isElementDisplayedBySeconds(STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON,2)){
                clickUsingJS(STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON);}
                break;
            case "vuseza":
                enterText(phoneNumber,"123456789");
                clickByElementByQueryJSExecutor(expandManualAddressFields);
                enterText(company,companyName);
                enterText(streetAddressLine1,"12 The Cloisters");
                enterText(city,townOrCityData);
                selectValueFromDropDownByIndex(3,By.cssSelector("select#region_id"));
                enterText(STREET2,"street2");
                enterText(postCode,postCodeData);
                break;
            case "vusede":
                enterText(phoneNumber,"7729353241");
                if(doesURLContain("/checkout/register/")){
                    enterText(streetAddressLine1, "Oberschwemke 1");
                    enterText(city, "Sundern");
                    enterText(postCode, "59846");}
                else{
                    enterText(ADDRESS_SEARCH_VUSEDE, "Oberschwemke 1");
                    waitForExpectedElement(ADDRESS_SEARCH_RESULT, 5).click();
                    if(isElementDisplayedBySeconds(ADDRESS_SEARCH_RESULT,2)){
                        waitForExpectedElement(ADDRESS_SEARCH_RESULT).click();
                    }
                }
                break;
            case "velopl":
                waitForAjaxElementNotToBePresent(webDriver,10);
                waitClearAndEnterText(phoneNumber, "+48519340985");
                waitForAjaxElementNotToBePresent(webDriver,10);
                scrollToElement(EXPAND_MANUAL_ADDRESS_VELOPL);
                clickUsingJS(EXPAND_MANUAL_ADDRESS_VELOPL);
                enterText(streetAddressLine1, "Listopada 137");
                enterText(streetAddressLine2, "11");
                enterText(streetAddressLine3, "11");
                enterText(city, "Sosnowiec");
                enterText(postCode, "26-609");
                break;
            case "velobe":
                switch (scenarioContext.getContext(LANGUAGE).toString()) {
                    case "en":
                    case "fr":
                    case "nl":
                        waitForAjaxElementNotToBePresent(webDriver,5);
                        waitClearAndEnterText(streetAddressLine1, "Burchtstraat 7");
                           waitClearAndEnterText(streetAddressLine2, "11");
                           waitClearAndEnterText(streetAddressLine3, "11");
                           waitClearAndEnterText(city, "Aalst");
                           waitClearAndEnterText(postCode, "9300");
                           waitClearAndEnterText(phoneNumber, "+3290123456");
                          // waitForExpectedElement(ADDRESS_CHOOSE).click();
                           break;
                }

                break;
            case "velode":
                waitClearAndEnterText(phoneNumber,"9876543210");
                enterText(ADDRESS_SEARCH_VUSEDE, "Oberschwemke 1");
                waitForExpectedElement(ADDRESS_SEARCH_RESULT, 5).click();
                if (isElementDisplayedBySeconds(STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON, 5))
                    getWebDriver().findElement(STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON).click();
                break;
            default:
                enterText(company,companyName);
                enterText(phoneNumber,randomPhoneNumber);
                enterText(streetAddressLine1,"12 The Cloisters");
                enterText(city,townOrCityData);
                enterText(postCode,postCodeData);
        }
    }

    public void populateAddressSignInFieldsAndClickCreateAccount(String email) {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                populateAddressFieldsForLyftUser();
                addBankId();
                populateSigninFields(email);
                userSelectsTheCreateAnAccountButton();
                break;
            case "glode":
                enterGloDeSpecifiedUserDetails();
                populateSigninFields(email);
                userSelectsTheCreateAnAccountButton();
                break;
            default:
                populateAddressFields();
                populateSigninFields(email);
                userSelectsTheCreateAnAccountButton();
                waitForPage();
        }
    }

    public void populateAddressSignInFieldsAndClickCreateLoyaltyAccount(String email) {
        try {
            populateAddressFields();
            populateSigninFieldsForLoyalty(email);
            userSelectsTheCreateAnAccountButton();
            waitForPage();
        } catch (Exception e) {
            LOG.info("Failed to click on Create Loyalty Account button due to exception: " + e.getMessage());
        }
    }

    public void createNewAccount() throws Throwable{
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("createAccountLink.key")));
        enterRegistrationDetailsAndCreateAccount(emailAddressData);
    }

    public void createEmailAndPassword(){
        String emailAddress = emailAddressData;
        LOG.info("Email  : " + emailAddress);
        LOG.info("Password : " + passwordData);
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        scenarioContext.setContext(PASSWORD, passwordData);
        enterText(email, emailAddress);
        enterText(password, passwordData);
        enterText(passwordConfirmation, passwordData);
    }

    public void verifyEmailAndReturnToMyAccountPage() {
        LOG.info("email substring " + emailAddressData.substring(0, 6));
        //Redirect to Mailinator
        getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData.substring(0, 6) + "#/#inboxpane");
        waitForExpectedElement(EmailConfirmationMessage, 5);
        //open the received mail
        //add for FF
        if (UrlBuilder.getLocale().equals("velobe")) {
            waitForExpectedElement(EMAIL_RECEIVED, 20);
            String confirm = UrlBuilder.getMessage("confirm-" + scenarioContext.getContext(LANGUAGE).toString());
            List<WebElement> emails = waitForExpectedElements(By.cssSelector("tr.even.pointer.ng-scope"));
            for (WebElement email : emails) {
                if (email.getText().toLowerCase().contains(confirm.toLowerCase())) {
                    email.findElement(By.cssSelector("td > a")).click();
                    break;
                }
            }
        }
        else if(UrlBuilder.isMobile())
        {
            clickUsingJS(M_EMAIL_RECEIVED_VUSEUK);
            getWebDriver().switchTo().defaultContent();
            getWebDriver().switchTo().frame(getWebDriver().findElement(M_IFRAME_VUSEUK));
            if (UrlBuilder.getLocale().equals("vuseuk") || UrlBuilder.getLocale().equals("vusede") || UrlBuilder.getLocale().equals("pl"))
            {
                getWebDriver().navigate().to(getWebDriver().findElement(M_CONFIRM_BTN_VUSEUK).getAttribute("href"));
            }
            else if(UrlBuilder.getLocale().equals("glode"))
            {
                getWebDriver().navigate().to(getWebDriver().findElement(M_CONFIRM_BTN_GLODE).getAttribute("href"));
            }
            LOG.info("URLs is " + getWebDriver().getCurrentUrl());
        }
        else {
            waitForExpectedElement(EmailConfirmationMessage, 5);
            //open the received mail
            waitForExpectedElement(EMAIL_RECEIVED, 20);//add for FF
            waitForExpectedElement(EMAIL_RECEIVED,20).click();
        }

        if(!UrlBuilder.isMobile()) {
            switch (UrlBuilder.getLocale()) {
                case "vuseuk":
                case "pl":
                case "glode":
                case "vusede":
                    waitForPage();
                    waitForExpectedElement(SHOW_BUTTON, 20).click();
                    waitForExpectedElement(CLICK_LINKS,10);
                    List<WebElement> actualItems = getWebDriver().findElements(CLICK_LINKS);
                    List<String> URLs = actualItems.stream().map(WebElement::getText).collect(toList());
                    for (String url : URLs) {
                        if (url.contains("confirm")) {
                            LOG.info("The confirming url is: "+url.toString());
                            getWebDriver().get(url);
                        }
                    }
                    waitForPage();
            }
        }

    }

    public void verifyEmailTemplate() {
        LOG.info("email substring " + emailAddressData.substring(0, 6));
        //Redirect to Mailinator
        webDriver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData.substring(0, 6) + "#/#inboxpane");
        waitForExpectedElement(EmailConfirmationMessage, 5);
        //open the received mail
        waitForExpectedElement(EMAIL_RECEIVED, 20);//add for FF
        getWebDriver().findElement(EMAIL_RECEIVED).click();
        waitForPage();
        switch (UrlBuilder.getLocale()) {
            case "vusede":
            case "vuseuk":
                waitForExpectedElement(EMAIL_HEADER,10);
                if (Props.EYES_ON) {
                    eyes.check("Eyes check email template", Target.window().fully());
                }
                assertTrue(webDriver.findElement(EMAIL_HEADER_MESSAGE).getText().contains(UrlBuilder.getMessage("welcome_subject.key")));
                assertTrue(webDriver.findElements(EMAIL_HEADER).get(0).getText().contains(emailAddressData.substring(0, 6)));
                assertTrue(webDriver.findElements(EMAIL_HEADER).get(1).getText().contains(UrlBuilder.getMessage("from.key")));
                assertTrue(webDriver.findElements(EMAIL_HEADER).get(2).getText().contains(UrlBuilder.getMessage("sendIP.key")));
                assertTrue(webDriver.findElements(EMAIL_HEADER).get(3).getText().contains(UrlBuilder.getMessage("received.key")));
                webDriver.switchTo().frame("html_msg_body");
                assertTrue(webDriver.findElement(MESSAGE_TITLE).getText().contains(UrlBuilder.getMessage("messageTitle.key")));
                webDriver.switchTo().defaultContent();
                break;
            case "vusepl":
                waitForExpectedElement(EMAIL_HEADER,10);
                if (Props.EYES_ON) {
                    eyes.check("Eyes check email template", Target.window().fully());
                }
                softAssertions(webDriver.findElements(EMAIL_HEADER).get(0).getText().contains(UrlBuilder.getMessage("welcome_subject.key")));
                softAssertions(webDriver.findElements(EMAIL_HEADER).get(1).getText().contains(emailAddressData.substring(0, 6)));
                softAssertions(webDriver.findElements(EMAIL_HEADER).get(2).getText().contains(UrlBuilder.getMessage("from.key")));
                softAssertions(webDriver.findElements(EMAIL_HEADER).get(3).getText().contains(UrlBuilder.getMessage("received.key")));
                softAssertions(webDriver.findElements(EMAIL_HEADER).get(4).getText().contains(UrlBuilder.getMessage("sendIP.key")));
                softAssertions.assertAll();
                break;
            default:
                LOG.info("Ignore if there is no confirm email");
        }
    }


    public void validateEmail(String emailType) throws InterruptedException {
        boolean emailReceived = false;
        int numberOfChecks=24;
        String language = scenarioContext.getContext(LANGUAGE).toString();
        String expectedEmailSender = UrlBuilder.getMessage("expectedEmailFromAddress-" + scenarioContext.getContext(LANGUAGE));
        String emailAddressData = scenarioContext.getContext(EMAIL_ID).toString();
        String expectedEmailRecipient = emailAddressData.substring(0,emailAddressData.indexOf("@"));
        List<WebElement> receivedEmails;
        String expectedSubjectString = null;
        LOG.info("email substring " + emailAddressData.substring(0, 6));
        //Redirect to Mailinator
        webDriver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + expectedEmailRecipient + "#/#inboxpane");
        switch (emailType) {
            case "verify":
                expectedSubjectString = UrlBuilder.getMessage("evailVerificationSubject-" + language);
                break;
            case "av not completed":
                expectedSubjectString = UrlBuilder.getMessage("emailAVNotCompletedSubject-" + language);
                break;
            case "reset password":
                expectedSubjectString = UrlBuilder.getMessage("emailPasswordResetSubject-" + language);
                break;
            case "order confirmation":
                expectedSubjectString = UrlBuilder.getMessage("emailOrderConfirmationSubject-" + language);
        }
        receivedEmails = mailinatorPage.getReceivedEmailElements(emailAddressData);
        while(!emailReceived && numberOfChecks > 0) {
            try {
                for (WebElement email: receivedEmails) {
                    if (email.getText().contains(expectedSubjectString)) {
                        email.click();
                        break;
                    }
                }
                emailReceived = true;
            } catch(Exception e) {
                LOG.info("Couldn't find email with subject " + expectedSubjectString + " trying " + numberOfChecks + " more times at 5s interval");
                numberOfChecks--;
                Thread.sleep(2000);
                receivedEmails = mailinatorPage.getReceivedEmailElements(emailAddressData);
            }
        }
    }


    public void enterRegistrationDetailsAndCreateAccount(String email) {
    populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData();
    switch (UrlBuilder.getLocale()){
        case "ie":
      case "fr":
      case "dk":
      case "lyftse":
      case "vusefr":
          String dob = doesURLContain("/se/en") ?
                  UrlBuilder.getMessage("ValidDOBEn.key") : UrlBuilder.getMessage("ValidDOB.key");
          enterDataAndWait(DOBInput, dob);
        waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
        selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"),gender);
        break;
      case "velode":
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            waitForExpectedElement(DATE_PICKER).click();
            waitForExpectedElement(VeloDE_GENDER).click();
            break;
      case "pl":
          try {
              if (System.getProperty("marketingChecks.key").equalsIgnoreCase("required")) {
                  LOG.info("Marketing Checks will select in later step");
              }
          }
          catch (Exception e){
              LOG.info("Marketing Checks not required,proceed with normal flow");
          }
          try {
              if (System.getProperty("markAllConsent.key").equalsIgnoreCase("required")) {
                  LOG.info("Marketing Checks already select later ");
              }
          }
          catch (Exception e){
              LOG.info("Marketing Checks not required,proceed with normal flow");
          }
          break;
      case "glode":
          try{
          if(System.getProperty("marketingChecks.key").equalsIgnoreCase("required")){
              clickByElementByQueryJSExecutor(newsLetterPage.EMAIL_CONSENT_CHK_BOX);
              clickByElementByQueryJSExecutor(newsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX);
              clickByElementByQueryJSExecutor(THIRD_PARTY_CONSENT_CHK_BOX);}
          }
          catch (Exception e){
              LOG.info("Marketing Checks not required,proceed with normal flow");
          }
          try{
              if(System.getProperty("markAllConsent.key").equalsIgnoreCase("required")){
                  clickByElementByQueryJSExecutor(newsLetterPage.CONSENT_ALL);}}
          catch (Exception ex){
              LOG.info("Mark All consent Checks not required,proceed with normal flow");
          }
        break;
        case "vuseuk":
            try{
                clickByElementByQueryJSExecutor(CARD_INFO_CHECKBOX);
                if(System.getProperty("markAllConsent.key").equalsIgnoreCase("required")){
                    clickByElementByQueryJSExecutor(newsLetterPage.NEWSLETTER_SMS_VUSEUK);
                    clickByElementByQueryJSExecutor(newsLetterPage.NEWSLETTER_EMAIL_VUSEUK);
                    }}
            catch (Exception e){
                LOG.info("Marketing Checks not required,proceed with normal flow");
            }
            try{
                if(System.getProperty("noConsent.key").equalsIgnoreCase("notRequired")){
                    clickByElementByQueryJSExecutor(newsLetterPage.NEWSLETTER_EMAIL_VUSEUK);
                }}
            catch (Exception e){
                LOG.info("Marketing Checks not required,proceed with normal flow");
            }
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
            selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
            break;
        case "vuseco":
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            if (!UrlBuilder.isMobile()) {
                waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
            }
            break;
        case "kz":
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            enterDataAndWait(CUSTOMER_ID_COLLECTION_FIELD,CUSTOMER_ID);
            clickByElementByQueryJSExecutor(newsLetterPage.EMAIL_CONSENT_CHK_BOX);
            break;
        case "veloza":
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
            selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
            waitForExpectedElement(SA_ID_NUMBER).sendKeys(UrlBuilder.getMessage("SAIDNumber.key"));
            break;
        case "vuseza":
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
            selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
            waitForExpectedElement(SA_ID_NUMBER).sendKeys(UrlBuilder.getMessage("SAIDNumber.key"));
            break;
        default:
            goToRegistrationDobMessagePageAndTakeEyesScreenshot();
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
            selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
    }
    scenarioContext.setContext(NEW_USER_EMAIL_ID, email);
    populateAddressSignInFieldsAndClickCreateAccount(email);
    LOG.info("Created account: "+NEW_USER_EMAIL_ID);
    if((UrlBuilder.getLocale().contains("de")||UrlBuilder.getLocale().contains("pl") || UrlBuilder.getLocale().contains("uk")
            || UrlBuilder.getLocale().contains("vuseuk") || UrlBuilder.getLocale().contains("glode")) && !isOnAvQrPage()) {
      verifyEmailAndReturnToMyAccountPage();
    }
  }

    public void enterSameRegistrationDetailsAndCreateAccountWithoutConfirmEmail(String email) {
        populatePersonalInformationFirstAndLastNameWithPreviousDeletedUser();
        enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
        waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
        selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
        waitForExpectedElement(SA_ID_NUMBER).sendKeys(UrlBuilder.getMessage("SAIDNumber.key"));


        scenarioContext.setContext(NEW_USER_EMAIL_ID, email);
        populateAddressSignInFieldsAndClickCreateAccount(email);
        LOG.info("Created Same account: "+NEW_USER_EMAIL_ID);
    }

    public void enterRegistrationDetailsAndCreateAccountWithoutConfirmEmail(String email) {
        populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData();
        switch (UrlBuilder.getLocale()){
            case "pl":
                try {
                    if (System.getProperty("marketingChecks.key").equalsIgnoreCase("required")) {
                        LOG.info("Marketing Checks will select in later step");
                    }
                }
                catch (Exception e){
                    LOG.info("Marketing Checks not required,proceed with normal flow");
                }
                try {
                    if (System.getProperty("markAllConsent.key").equalsIgnoreCase("required")) {
                        LOG.info("Marketing Checks already select later ");
                    }
                }
                catch (Exception e){
                    LOG.info("Marketing Checks not required,proceed with normal flow");
                }
                break;
            case "glode":
                try{
                    if(System.getProperty("marketingChecks.key").equalsIgnoreCase("required")){
                        clickByElementByQueryJSExecutor(newsLetterPage.EMAIL_CONSENT_CHK_BOX);
                        clickByElementByQueryJSExecutor(newsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX);
                        clickByElementByQueryJSExecutor(THIRD_PARTY_CONSENT_CHK_BOX);}
                }
                catch (Exception e){
                    LOG.info("Marketing Checks not required,proceed with normal flow");
                }
                try{
                    if(System.getProperty("markAllConsent.key").equalsIgnoreCase("required")){
                        clickByElementByQueryJSExecutor(newsLetterPage.CONSENT_ALL);}}
                catch (Exception ex){
                    LOG.info("Mark All consent Checks not required,proceed with normal flow");
                }
                break;
            case "vuseuk":
                try{
                    clickByElementByQueryJSExecutor(CARD_INFO_CHECKBOX);
                    if(System.getProperty("markAllConsent.key").equalsIgnoreCase("required")){
                        clickByElementByQueryJSExecutor(newsLetterPage.NEWSLETTER_SMS_VUSEUK);
                        clickByElementByQueryJSExecutor(newsLetterPage.NEWSLETTER_EMAIL_VUSEUK);
                    }}
                catch (Exception e){
                    LOG.info("Marketing Checks not required,proceed with normal flow");
                }
                try{
                    if(System.getProperty("noConsent.key").equalsIgnoreCase("notRequired")){
                        clickByElementByQueryJSExecutor(newsLetterPage.NEWSLETTER_EMAIL_VUSEUK);
                    }}
                catch (Exception e){
                    LOG.info("Marketing Checks not required,proceed with normal flow");
                }
                enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
                waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
                selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
                break;
            default:
                enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
                waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
                selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
        }
        scenarioContext.setContext(NEW_USER_EMAIL_ID, email);
        populateAddressSignInFieldsAndClickCreateAccount(email);
        LOG.info("Created account: "+NEW_USER_EMAIL_ID);
    }

  private boolean isOnAvQrPage() {
        return webDriver.getCurrentUrl().contains(AV_QR_URL);
  }
  public void enterRegistrationDetailsAndCreateLoyaltyAccount(String email){
      populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData();
      enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
      waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
      selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
      scenarioContext.setContext(NEW_USER_EMAIL_ID, email);
      populateAddressSignInFieldsAndClickCreateLoyaltyAccount(email);
      waitForAjaxElementNotToBePresent(getWebDriver(),10);
      if(UrlBuilder.getLocale().contains("de")||UrlBuilder.getLocale().contains("pl") || UrlBuilder.getLocale().contains("uk")){
          verifyEmailAndReturnToMyAccountPage();
      }
  }

    public void createANewLoyalityAccountWithSpecifiedUserDetails(String strEmailAddress,String strFirstName,String strLastName,String DOB,String PostCode) throws Throwable {
        homePage.navigateToSignInPage();
        logininPage.clickRegistrationButton();
        if ("vusede".equals(UrlBuilder.getLocale())) {
            enterSpecifiedUserDetails(strFirstName, strLastName, DOB, UrlBuilder.getMessage("Gender.key"));
            enterSpecifiedAddressDetails(System.getProperty("UserStreetAddress.key"), System.getProperty("UserCity.key"), PostCode);
            enterSpecifiedSignInDetails(strEmailAddress, UrlBuilder.getMessage("loginValidPassword.key"));
            checkTsCs();
            checkLoyalty();
            userSelectsTheCreateAnAccountButton();
            waitForPage();
        }
    }

    public void tickAgreeToTermsAndConditionsOnGlo() {
        if(UrlBuilder.getSite().equals("glo")) {
            switch (UrlBuilder.getLocale()) {
                case "kz":
                    clickByElementByQueryJSExecutor(TERMS_CONDITION_CHECKBOX);
                    break;
                case "pl":
                    clickByElementByQueryJSExecutor(PRIVACY_CHECK);
                    clickByElementByQueryJSExecutor(AGREEMENT_CHECK);
                    break;
                case "glode":
                    clickByElementByQueryJSExecutor(AGREEMENT_CHECK_GLODE);
                    break;
                default:
                    clickByElementByQueryJSExecutor(PRIVACY_CHECK);
            }
        }
    }

    public void agreeToCheckoutTermsAndConditionsVeloBe() {
        List<WebElement> tickboxes = webDriver.findElements(AVALANCHE_CHECKOUT_AGREEMENT_TICKBOX);
        for (WebElement tickbox: tickboxes) {
            clickUsingJS(tickbox);
        }
    }

    public void agreeToRegistrationTermsAndConditionsVeloBe() {
        List<WebElement> tickboxes = webDriver.findElements(AVALANCHE_REGISTRATION_AGREEMENT_TICKBOX);
        for (WebElement tickbox: tickboxes) {
            clickUsingJS(tickbox);
        }
    }

    public void validateAgreement(String agreement) {
        LOG.info("validateAgreement: validating " + agreement);
        String actualAgreementBody;
        String actualAgreementTitle;
        String expectedAgreementTitle = null;
        WebElement agreementElement = null;
        By linkTextSelector = null;
        String language = scenarioContext.getContext(LANGUAGE).toString();
        switch (agreement.toLowerCase()) {
            case "privacy policy":
                agreementElement =  webDriver.findElements(AVALANCHE_AGREEMENT).get(0);
                linkTextSelector = By.linkText(UrlBuilder.getMessage("privacyPolicyLinkText-" + language));
                expectedAgreementTitle = UrlBuilder.getMessage("privacyPolicyTitle-" + language);
                break;
            case "terms and conditions":
                if (UrlBuilder.getLocale().equals("velobe")) {
                    agreementElement = webDriver.findElements(AVALANCHE_AGREEMENT).get(1);
                } else {
                    agreementElement = webDriver.findElement(TERMS_AND_CONDITIONS_VELOPL);
                }
                linkTextSelector = By.linkText(UrlBuilder.getMessage("termsAndConditionsLinktext-" + language));
                expectedAgreementTitle = UrlBuilder.getMessage("termsAndConditionsTitle-" + language);
                break;
            case "age":
                agreementElement = webDriver.findElement(CONSENT_AGREEMENT_CHECKBOX);
                break;
            case "email":
                agreementElement = webDriver.findElement(CONSENT_EMAIL_CHECKBOX);
                break;
            case "mobile":
                agreementElement = webDriver.findElement(CONSENT_MOBILE_CHECKBOX);
                break;
            case "social media":
                agreementElement = webDriver.findElement(CONSENT_SOCIALNETWORK_CHECKBOX);
                break;
            case "newsletter":
                agreementElement = webDriver.findElement(NEWSLETTER_SUBSCRIBE_CHECKBOX);
                break;
            case "remember me":
                agreementElement = webDriver.findElement(REMEMBERME_CHECKBOX);
                break;
            case "gender":
                agreementElement = webDriver.findElement(GENDER_DROPDOWN);
                break;
            default:
                assertThat(true).as("ERROR validateAgreement: invalid agreement supplied as parameter " + agreement).isFalse();
        }
        if ((agreement.equals("privacy policy") || agreement.equals("terms and conditions")) && UrlBuilder.getLocale().equals("velobe")) {
        assertThat(agreementElement.findElement(CHECKBOX).isDisplayed())
                .as("ERROR validateAgreement: no tickbox is displayed for " + agreement).isTrue();
            assertThat(agreementElement.findElement(linkTextSelector).isDisplayed())
                    .as("ERROR validatePrivacyPolicy: no link to privacy policy is displayed").isTrue();
            clickUsingJS(linkTextSelector);
            actualAgreementTitle = waitForExpectedElement(AVALANCHE_AGREEMENT_HEADER).getText().trim();
            assertThat(expectedAgreementTitle.equalsIgnoreCase(actualAgreementTitle))
                    .as("ERROR validatePrivacyPolicy: expected " + agreement + " title was " + expectedAgreementTitle + " but I got " + actualAgreementTitle).isTrue();
            actualAgreementBody = waitForExpectedElement(AVALANCHE_AGREEMENT_BODY).getText().trim();
            assertThat(actualAgreementBody.length() > 0)
                    .as("ERROR: " + agreement + " body contains no text").isTrue();
            waitForExpectedElement(AVALANCHE_CLOSE_AGREEMENT_BUTTON).click();
            waitForElementToAppearAndDisappear(LOADER, 2, 2);
        }
    }

    public void generateFiscalAndPopulate() {
        LocalDate dob = randomDateOfBirth();
        Boolean isMale = new Random().nextBoolean();
        String sex = (isMale) ? "1" : "2";
        selectOptionFromDropDownByValue(sex, gender);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        String formattedDate = dob.format(formatters);
        webDriver.findElement(DOBInput).clear();
        enterText(DOBInput, formattedDate);
        CodiceFiscaleGenerator fcg = new CodiceFiscaleGenerator(dob, isMale);
        String strFiscalCode = fcg.getFiscalCode();
        enterText(txtFiscalCode, strFiscalCode.toLowerCase());
        verifyCodiceFiscalInUpperCase(strFiscalCode);
    }

    public void enterRegistrationDetailsAndCreateAccountWithout(String fieldUnderTest) {
        refreshBrowser();
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();

        switch (fieldUnderTest) {
            case "firstName":
                registrationPageModel.getPersonalInfo().setFirstName(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "lastName":
                registrationPageModel.getPersonalInfo().setLastName(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "dob":
                registrationPageModel.getPersonalInfo().setDob(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "gender":
                registrationPageModel.getPersonalInfo().setGender(EMPTY_STRING);
                createUserWithMailinatorCheck(registrationPageModel);
                break;
            case "streetAndAddressLine1":
                registrationPageModel.getAddress().setStreetAndHouseNumber(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "city":
                registrationPageModel.getAddress().setCity(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "postcode":
                registrationPageModel.getAddress().setPostcode(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "phoneNumber":
                registrationPageModel.getAddress().setPhoneNumber(EMPTY_STRING);
                createUserWithMailinatorCheck(registrationPageModel);
                break;
            case "email":
                registrationPageModel.getSignInInfo().setEmail(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "password":
                registrationPageModel.getSignInInfo().setPassword(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "confirmPassword":
                registrationPageModel.getSignInInfo().setConfirmPassword(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "TermsAndConditions":
                createUserWithOutTsAndCs(registrationPageModel);
                break;
            case "LoyaltySignUp":
                enterRegistrationDetailsAndCreateAccount(emailAddressData);
                break;
            case "ValidUserID":
                registrationPageModel.getAddress().setUserID(EMPTY_STRING);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;

            case "Age policy":
                clickUsingJS(CONSENT_AGREEMENT_CHECKBOX);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "sa id number":
                clickUsingJS(SA_ID_NUMBER);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            case "terms and conditions":
                clickUsingJS(TERMS_AND_CONDITIONS_VELOPL);
                createUserWithOutMailinatorCheck(registrationPageModel);
                break;
            default:
                throw new IllegalArgumentException("Invalid Field " + fieldUnderTest);
        }
    }

    public void createUserWithOutMailinatorCheck(RegistrationPageModel registrationData) {
        populateCreateUserFields(registrationData);
        checkTsCs();
        if (UrlBuilder.getLocale().equals("kz")){
            RegistrationPageModel.PersonalInfo personalInfo = registrationData.getPersonalInfo();
            waitClearAndEnterText(PHONE_NUMBER_TXT_BOX,PHONENUMBER_GLOKZ);
            enterDataAndWait(CUSTOMER_ID_COLLECTION_FIELD, random(Integer.valueOf(12), NUMERIC));
        }
        userSelectsTheCreateAnAccountButton();
        waitForPage();
    }

    public void createUserWithMailinatorCheck(RegistrationPageModel registrationData) {
        populateCreateUserFields(registrationData);
        checkTsCs();
        userSelectsTheCreateAnAccountButton();
        waitForPage();
        if (UrlBuilder.getLocale().contains("de") || UrlBuilder.getLocale().contains("pl")) {
            verifyEmailAndReturn(registrationData.getSignInInfo().getEmail(), "confirm");
        }
    }


    public void createUserPopulateInvalidInput(RegistrationPageModel registrationData) {
        populatePersonalInformation(registrationData);
        populateSigninFields(registrationData);
    }

    public void createUserWithOutTsAndCs(RegistrationPageModel registrationData) {
        populateCreateUserFields(registrationData);
        userSelectsTheCreateAnAccountButton();
        waitForPage();
    }

    private void populateCreateUserFields(RegistrationPageModel registrationData) {
        if (!UrlBuilder.getLocale().equals("velopl")){
        populatePersonalInformation(registrationData);
        if (UrlBuilder.getLocale().equals("kz")){
            populateAddressFields();
        }
        else if(!UrlBuilder.getLocale().equals("velobe")) {
            populateAddressFields(registrationData);
        }
        populateSigninFields(registrationData);
    }}

    public void populatePersonalInformation(RegistrationPageModel registrationData) {
        RegistrationPageModel.PersonalInfo personalInfo = registrationData.getPersonalInfo();
        if(!UrlBuilder.getLocale().equals("it")) {
            waitForExpectedElement(firstNameInput, 10);
            enterDataAndWait(firstNameInput, personalInfo.getFirstName());
            enterDataAndWait(surNameInput, personalInfo.getLastName());
            enterDataAndWait(DOBInput, personalInfo.getDob());
        }
        switch (UrlBuilder.getLocale()) {
            case "glode":
                gloDeSpecifiedGenderAndAddressDetails(registrationData);
                break;
            case "velode":
                clickByElementByQueryJSExecutor(VeloDE_GENDER);
                break;
            case "kz":
                enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
                break;
            case "velopl":
                break;
            case "velobe":
                break;
            case "it":
                waitForExpectedElement(firstNameInput_it, 10);
                enterDataAndWait(firstNameInput_it, personalInfo.getFirstName());
                enterDataAndWait(surNameInput, personalInfo.getLastName());
                enterDataAndWait(DOBInput, personalInfo.getDob());
                selectValueFromDropDownByby(personalInfo.getGender(), gender);
                if (isElementPresentByby(txtFiscalCode)) {
                    enterText(txtFiscalCode, personalInfo.getFiscalCode());
                }
                break;
            default:
                selectValueFromDropDownByby(personalInfo.getGender(), gender);
                if (isElementPresentByby(txtFiscalCode)) {
                    enterText(txtFiscalCode, personalInfo.getFiscalCode());
                }
        }
    }

    public void populateSigninFields(RegistrationPageModel registrationData) {
        RegistrationPageModel.SignInInfo signInInfo = registrationData.getSignInInfo();
        waitClearAndEnterText(email, signInInfo.getEmail());
        waitClearAndEnterText(password, signInInfo.getPassword());
        waitClearAndEnterText(passwordConfirmation, signInInfo.getConfirmPassword());
    }

    public void populateAddressFields(RegistrationPageModel registrationData) {
        RegistrationPageModel.Address address = registrationData.getAddress();
        if (UrlBuilder.getLocale().equals("velode") || UrlBuilder.getLocale().equals("vusede")) {
            if (isElementDisplayedBySeconds(MANUAL_DELIVERY_ADDRESS_ENTRY_LINK, 5))
                clickOnManualAddressEntryLink();
        }
        if (!UrlBuilder.getLocale().equals("veloza")){
            waitClearAndEnterText(streetAddressLine1, address.getStreetAndHouseNumber());
            waitClearAndEnterText(city, address.getCity());
            if (UrlBuilder.getLocale().equals("it") && !registrationData.getAddress().getPostcode().isEmpty())
                waitClearAndEnterText(postCode, "UB10 9DW");
            else if(UrlBuilder.getLocale().equals("vusede")){
                waitClearAndEnterText(postCode, registrationData.getAddress().getPostcode());
                waitClearAndEnterText(streetAddressLine2,"1");}
            waitClearAndEnterText(phoneNumber, address.getPhoneNumber());

        }}

    public void verifyEmailAndReturn(String emailAddressData, String expectedStringInUrl) {
        if (UrlBuilder.isDesktop()) {
            LOG.info("email substring " + emailAddressData.substring(0, emailAddressData.indexOf("@")));

            //Redirect to Mailinator
            getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData
                    .substring(0, emailAddressData.indexOf("@")) + "#/#inboxpane");
            presenceOfAllElementsLocatedBy(EmailConfirmationMessage);
            getWebDriver().findElement(EMAIL_RECEIVED).click();
            clickUsingJS(SHOW_BUTTON);
            isElementDisplayedBySeconds(FROM_EMAIL_ADDRESS, 10);
            List<WebElement> actualItems = getWebDriver().findElements(CLICK_LINKS);
            List<String> URLs = actualItems.stream().map(WebElement::getText).collect(toList());
            for (String url : URLs) {
                if (url.contains(expectedStringInUrl)) {
                    getWebDriver().get(url);
                }
            }
        }else {
            switch (UrlBuilder.getLocale()){
                case "it":
                    LOG.info("email substring " + emailAddressData.substring(0, emailAddressData.indexOf("@")));
                    //Redirect to Mailinator
                    getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData
                            .substring(0, emailAddressData.indexOf("@")) + "#/#inboxpane");
                    waitForExpectedElement(M_EMAIL_RECEIVED_VUSEUK, 30);
                    clickUsingJS(M_EMAIL_RECEIVED_VUSEUK);
                    if (UrlBuilder.getLocale().equals("it"))
                    {
                        getWebDriver().switchTo().defaultContent();
                        getWebDriver().switchTo().frame(getWebDriver().findElement(M_IFRAME_VUSEUK));
                        getWebDriver().navigate().to(getWebDriver().findElement(M_RESET_PWD_EMAIL_BTN_GLOIT).getAttribute("href"));
                    }
                    LOG.info("URLs is " + getWebDriver().getCurrentUrl());
                    break;
            }
        }
    }

    public String getCssStyleContent(String field) {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", FIRST_NAME_LABEL);
        map.put("lastName", LAST_NAME_LABEL);
        map.put("dob", DOB_LABEL);
        map.put("gender", GENDER_LABEL);
        map.put("streetAndAddressLine1", STREET_LABEL);
        map.put("city", CITY_LABEL);
        map.put("postcode", POSTCODE_LABEL);
        map.put("email", EMAIL_LABEL);
        map.put("password", PASSWORD_LABEL);
        map.put("confirmPassword", CONFIRM_PASSWORD_LABEL);
        map.put("confirmTelephone", TELEPHONE_LABEL);
        map.put("country", COUNTRY_LABEL);

        String content = null;

        for (String key : map.keySet()) {
            if (key.equalsIgnoreCase(field)) {
                content = getAfterSudoCssContent(map.get(key));
            }
        }
        return content;
    }

    private String getAfterSudoCssContent(String cssselector) {
        return getPropertyValueFromCssPseudoByJsExecutor(cssselector, "::after", "content");
    }

    public void enterRegistrationDetailsWithCodiceFiscal(String input) {
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();
        String keyPattern = "length-";
        Matcher matcher = Pattern.compile(keyPattern + "\\d+").matcher(input);

        if (matcher.find()) {
            String fiscalCode = getRequiredLengthFiscalCode(input, keyPattern, registrationPageModel);
            registrationPageModel.getPersonalInfo().setFiscalCode(fiscalCode);
            createUserWithOutMailinatorCheck(registrationPageModel);
        } else {
            switch (input) {
                case "NoDOBField":
                    registrationPageModel.getPersonalInfo().setDob(EMPTY_STRING);
                    populatePersonalInformation(registrationPageModel);
                    break;
                case "NoGenderField":
                    registrationPageModel.getPersonalInfo().setGender(EMPTY_STRING);
                    populatePersonalInformation(registrationPageModel);
                    break;
                case "RandomNumberNoDOBIncluded":
                    registrationPageModel.getPersonalInfo().setFiscalCode(randomAlphanumeric(0, 16));
                    populatePersonalInformation(registrationPageModel);
                    break;
                case "RandomNumberWithDOBIncluded":
                    createUserWithOutMailinatorCheck(registrationPageModel);
                    break;
                default:
                    throw new IllegalStateException("Unexpected input: " + input);
            }
        }
    }

    private String getRequiredLengthFiscalCode(String input, String keyPattern, RegistrationPageModel pageModel) {
        int requiredLength = Integer.parseInt(input.substring(keyPattern.length()));
        String fiscalCode = pageModel.getPersonalInfo().getFiscalCode();
        if (!(requiredLength < fiscalCode.length())){
            fiscalCode = fiscalCode.concat(randomAlphanumeric(requiredLength - fiscalCode.length()));
        }
        return fiscalCode;
    }

    public void enterSpecifiedUserDetails(String strFirstName,String strLastName,String DOB,String strGender) {
        enterDataAndWait(firstNameInput, strFirstName);
        enterDataAndWait(surNameInput, strLastName);
        switch (UrlBuilder.getLocale()) {
            case "glode":
                enterDataAndWait(DOBInput, DOB);
                gloDeSpecifiedGenderAndAddressDetails();
                break;
            default:
                if (UrlBuilder.getLocale().equals("vusede")) {
                    enterDataAndWait(DOBInputVuseDE, UrlBuilder.getMessage("DateOfBirth.key"));
                } else {
                    enterDataAndWait(DOBInput, DOB);
                }
                selectValueFromDropDownByby(strGender, gender);
        }
    }

    public void enterGloDeSpecifiedUserDetails() {
        clickByElementByQueryJSExecutor(NAME_PRIFIX);
        enterText(DOBInputVuseDE, UrlBuilder.getMessage("DateOfBirth.key"));
        clickManualAddressEntryButton();
        enterText(streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
        enterText(city, UrlBuilder.getMessage("city.key"));
        selectValueFromDropDownByIndex(5,By.cssSelector("select#region_id"));
        enterText(postCode, UrlBuilder.getMessage("postalCode.key"));
        enterText(phoneNumber,RANDOM_DE_PHONE_NUMBER);
        }

    public void gloDeSpecifiedGenderAndAddressDetails() {
        clickByElementByQueryJSExecutor(NAME_PRIFIX);
        clickManualAddressEntryButton();
        enterText(streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
        enterText(city, UrlBuilder.getMessage("city.key"));
        selectValueFromDropDownByIndex(5,By.cssSelector("select#region_id"));
        enterText(postCode, UrlBuilder.getMessage("postalCode.key"));
        enterText(phoneNumber,RANDOM_DE_PHONE_NUMBER);
    }

    public void gloDeSpecifiedGenderAndAddressDetails(RegistrationPageModel registrationData) {
        clickByElementByQueryJSExecutor(NAME_PRIFIX);
        clickManualAddressEntryButton();
        enterText(streetAddressLine1, registrationData.getAddress().getStreetAndHouseNumber());
        enterText(city, registrationData.getAddress().getCity());
        selectValueFromDropDownByIndex(5,By.cssSelector("select#region_id"));
        enterText(postCode, registrationData.getAddress().getPostcode());
        enterText(phoneNumber,RANDOM_DE_PHONE_NUMBER);
    }

        public void enterSpecifiedAddressDetails(String StreetAddress,String strCity,String PostCode) {
        if (UrlBuilder.getLocale().equals("vusede")) {
            enterText(ADDRESS_SEARCH_VUSEDE, "Oberschwemke 1");
            waitForExpectedElement(ADDRESS_SEARCH_RESULT).click();
        } else {
            enterText(streetAddressLine1, StreetAddress);
            enterText(city, strCity);
            enterText(postCode, PostCode);
        }
    }

    public void enterSpecifiedSignInDetails(String strEmailAddress,String strPassword) {
        enterText(email,strEmailAddress);
        enterText(password,strPassword);
        enterText(passwordConfirmation,strPassword);
    }

    public void createANewAccountWithSpecifiedUserDetails(String strEmailAddress,String strFirstName,String strLastName,String DOB,String PostCode) throws Throwable {
        homePage.navigateToSignInPage();
        logininPage.clickRegistrationButton();
        switch (UrlBuilder.getLocale()) {
            case "de":
            case "glode":
                DOB = DOB.replace("/", ".");
            enterSpecifiedUserDetails(strFirstName, strLastName, DOB, UrlBuilder.getMessage("Gender.key"));
            enterSpecifiedAddressDetails(System.getProperty("UserStreetAddress.key"), System.getProperty("UserCity.key"), PostCode);
            enterSpecifiedSignInDetails(strEmailAddress, UrlBuilder.getMessage("loginValidPassword.key"));
            checkTsCs();
            userSelectsTheCreateAnAccountButton();
            waitForPage();
            break;
            case "vusede":
            case "velode":
                enterSpecifiedUserDetails(strFirstName, strLastName, DOB, UrlBuilder.getMessage("Gender.key"));
                enterSpecifiedAddressDetails(System.getProperty("UserStreetAddress.key"), System.getProperty("UserCity.key"), PostCode);
                enterSpecifiedSignInDetails(strEmailAddress, UrlBuilder.getMessage("loginValidPassword.key"));
                checkTsCs();
                userSelectsTheCreateAnAccountButton();
                waitForPage();
                break;
            case "it":
                enterRegistrationDetailsAndCreateAccount(System.getProperty("UserEmailAddress.key"));
                break;
            case "vusefr":
                enterSpecifiedUserDetails(strFirstName, strLastName, DOB, UrlBuilder.getMessage("Gender.key"));
                clickByElementByQueryJSExecutor(REG_ADD_ADDRESS_MANUALLY);
                enterDataAndWait(streetAddressLine1,UrlBuilder.getMessage("streetwrongAddress.key"));
                enterSpecifiedSignInDetails(strEmailAddress, UrlBuilder.getMessage("loginValidPassword.key"));
                checkTsCs();
                userSelectsTheCreateAnAccountButton();
                waitForPage();
                break;
            case "vuseuk":
                enterSpecifiedUserDetails(strFirstName, strLastName, DOB, UrlBuilder.getMessage("Gender.key"));
                populateAddressFieldsWithSpecificPostCode(PostCode);
                populateSigninFields(emailAddressData);
                userSelectsTheCreateAnAccountButton();
                verifyEmailAndReturnToMyAccountPage();
                waitForPage();
                break;
            }
    }

    public void createANewAccountWithNonDuplicateLastNameAndEmailAddress() throws Throwable {
        createANewAccountWithSpecifiedUserDetails(emailAddressData, System.getProperty("UserFirstName.key"), lastNameData, System.getProperty("UserDOB.key"), System.getProperty("UserPostCode.key"));
    }

    public void enterRegistrationDetailsWithAge(String input) {
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();
        String dateOfBirth = getDateOfBirthFromAge(input);
        registrationPageModel.getPersonalInfo().setDob(dateOfBirth);
        createUserWithOutMailinatorCheck(registrationPageModel);
    }

    public String getDateOfBirthFromAge(String input) {
        int numberOfYears = Integer.parseInt(input.substring(0, input.indexOf("-")));
        LocalDate dob = LocalDate.now().minusYears(numberOfYears);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        return dob.format(formatters);
    }

    public void signUpForConsentMobileTickBoxSelection(){
        clickUsingJS(MyAccountPageSteps.MOBILE_SOCIAL_CONSENT_CHK_BOX);
    }

    public void clickManualAddressEntryButton() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case GLODE:
                waitForExpectedElement(REG_ADD_ADDRESS_MANUALLY);
                clickUsingJS(REG_ADD_ADDRESS_MANUALLY);
                break;
            case VUSEDE:
                waitForExpectedElement(MANUAL_DELIVERY_ADDRESS_ENTRY_LINK);
                clickUsingJS(MANUAL_DELIVERY_ADDRESS_ENTRY_LINK);
                break;
            default:
                waitForExpectedElement(MANUAL_ADDRESS_ENTRY_LINK);
                clickUsingJS(MANUAL_ADDRESS_ENTRY_LINK);
        }
    }

    public void assertAllMarketingConsentCheckox(){
        assertFalse(waitForExpectedElement(newsLetterPage.CONSENT_ALL).isSelected());
        clickByElementByQueryJSExecutor(newsLetterPage.CONSENT_ALL);
        assertTrue(waitForExpectedElement(newsLetterPage.EMAIL_CONSENT_CHK_BOX).isSelected());
        assertTrue(waitForExpectedElement(newsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX).isSelected());
        assertTrue(waitForExpectedElement(THIRD_PARTY_CONSENT_CHK_BOX).isSelected());
    }

    public void assertAllMarketingConsentCheckoxIsDeselected(){
        clickByElementByQueryJSExecutor(newsLetterPage.EMAIL_CONSENT_CHK_BOX);
        assertFalse(waitForExpectedElement(newsLetterPage.CONSENT_ALL).isSelected());
    }

    public void assertSubscriptionCheckboxSelectionAndVerifyNLSubscriptionEmailIfNotChecked() {
        WebElement marketingTickBox = waitForExpectedElement(accountDashboardPage.MARKETING_TICK_BOX, 10);
        Boolean newsLetterTickBoxSelected = marketingTickBox.isSelected();
        if (newsLetterTickBoxSelected)
            LOG.info("Newsletter Subscription is completed via Single Opt-In as email confirmation is not required");
        else {
            String EMAIL_ADDRESS= (String)scenarioContext.getContext(Context.NEW_USER_EMAIL_ID);
            List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(EMAIL_ADDRESS);
            mailinatorPage.clickOnEmailWithSubject(receivedEmailElements,UrlBuilder.getMessage("NLsubscriptionEmailSubject.Text"));
            waitForAjaxElementNotToBePresent(getWebDriver(), 5);
            waitForExpectedElement(SHOW_BUTTON).click();
            List<WebElement> actualItems = getWebDriver().findElements(CLICK_LINKS);
            List<String> URLs = actualItems.stream().map(WebElement::getText).collect(toList());
            for (String url : URLs) {
                if (url.contains(UrlBuilder.getMessage("newsletterConfirmationURL.key"))) {
                    getWebDriver().get(url);
                }
            }
        }
    }

    public void clickOnTermsAndConditionLinkUnderLoyaltyConsentSection(){
        clickByElementByQueryJSExecutor(TermsAndConditionsLinkUnderLoyaltyConsent_VUSEDE);
    }

    public String getErrorMessageDisplayedFor(String fieldUnderTest) {
        By by = By.cssSelector("div[for=\"" + fieldUnderTest + "\"]");
        scrollElementIntoView(by);
        return getTextFor(by);
    }

    public String getLandTextOnRegistrationPage(){
        waitForExpectedElement(EXPAND_ADDRESS_LINK_VELO, 10);
        clickByElementByQueryJSExecutor(EXPAND_ADDRESS_LINK_VELO);
        return (waitForExpectedElement(LAND_OR_COUNTRY_VELOEUDE, 5).getText());
    }

    public void verifyCodiceFiscalInUpperCase(String strEnteredText) {
        String strCodiceFiscal = waitForExpectedElement(txtFiscalCode).getAttribute("value");
        assertEquals(strCodiceFiscal,strEnteredText.toUpperCase());
    }

    public void enterRegistrationDetailsWithMentionedAge(String input) {
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();
        String dateOfBirth = getDateOfBirthFromAge(input);
        registrationPageModel.getPersonalInfo().setDob(dateOfBirth);
        createUserWithOutValidatingMailinator(registrationPageModel);
    }

    public void clickOnManualAddressEntryLink(){
        waitForExpectedElement(MANUAL_DELIVERY_ADDRESS_ENTRY_LINK, 5);
        clickByElementByQueryJSExecutor(MANUAL_DELIVERY_ADDRESS_ENTRY_LINK);
    }

    public void addAddressManuallyOnAddressPage(String streetAndHouseNumber, String city_1, String postal, String telephone) {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        clickOnManualAddressEntryLink();
        try {
            waitClearAndEnterText(streetAddressLine1, streetAndHouseNumber);
            waitClearAndEnterText(city, city_1);
            waitClearAndEnterText(postCode, postal);
        }catch(Exception e){
            EnterAddressManually(streetAndHouseNumber, city_1, postal, telephone);
        }
        if (isElementDisplayedBySeconds(STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON, 8))
            getWebDriver().findElement(STANDARD_DELIVERY_ADDRESS_RADIO_BUTTON).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public void EnterAddressManually(String streetAndHouseNumber, String city, String postal, String telephone) {
        waitClearAndEnterText(SHIPPING_STREET_HOUSE_NUMBER_VELODE, streetAndHouseNumber);
        waitClearAndEnterText(SHIPPING_CITY_VELODE, city);
        waitClearAndEnterText(SHIPPING_POSTAL_VELODE, postal);
        waitClearAndEnterText(SHIPPING_TELEPHONE, telephone);
    }

    public void userUpdatesValidNumberAndSaveAddress() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEUK:
                enterDataAndWait(MOBILE_NUMBER_EDIT_ADDRESS, "7768123456");
                break;
            case VUSEFR:
                enterDataAndWait(MOBILE_NUMBER_EDIT_ADDRESS, "+337744123456");
                break;
        }
        clickByElementByQueryJSExecutor(addNewAddressPage.DEFAULT_BOTH_ADDRESS_CHANGE_INPUT);
        addNewAddressPage.pressPopUpFormSaveAddressButton();
    }

    public void createNewAccountWithDefaultMobileNumber() {
        populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData();
        enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
        waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
        selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"), gender);
        populateSigninFields(emailAddressData);
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEUK:
                if(isElementDisplayedBySeconds(ENTER_ADDRESS_MANUALLY_LINK_UK,2)){
                    scrollToElement(ENTER_ADDRESS_MANUALLY_LINK_UK);
                    clickUsingJS(ENTER_ADDRESS_MANUALLY_LINK_UK);
                }
                enterText(company, companyName);
                enterText(streetAddressLine1, "12 The Cloisters");
                enterText(city, townOrCityData);
                enterText(postCode, postCodeData);
                break;
            case VUSEFR:
                enterText(ADDRESS_SEARCH_VUSEDE, "EN NEXIRUE, 57000 METZ, FRANCE");
                waitForExpectedElement(ADDRESS_SEARCH_RESULT).click();
                waitForAjaxElementNotToBePresent(getWebDriver(),2);
                break;
        }
        userSelectsTheCreateAnAccountButton();
        waitForExpectedElement(USER_LOGGED_IN_MESSAGE,5);
        if(getWebDriver().findElements((USER_LOGGED_IN_MESSAGE)).size()==0)
            verifyEmailAndReturnToMyAccountPage();
    }

    public void userEntersCharactersLimitInIDField(String strLimit) {
        enterDataAndWait(CUSTOMER_ID_COLLECTION_FIELD, random(Integer.valueOf(strLimit), NUMERIC));
        clickElementByQueryJSExecutor(getWebDriver().findElements(CREATEACCOUNT_BUTTON).get(0));
    }

    public void userEntersNonNumericCharactersInIDField(String strLimit) {
        enterDataAndWait(CUSTOMER_ID_COLLECTION_FIELD, random(Integer.valueOf(strLimit), ALPHABETS));
        clickElementByQueryJSExecutor(getWebDriver().findElements(CREATEACCOUNT_BUTTON).get(0));
    }

    public void createUserWithOutValidatingMailinator(RegistrationPageModel registrationData) {
        populateCreateUserFields(registrationData);
        checkTsCs();
        if (UrlBuilder.getLocale().equals("kz")){
            RegistrationPageModel.PersonalInfo personalInfo = registrationData.getPersonalInfo();
            waitClearAndEnterText(PHONE_NUMBER_TXT_BOX,PHONENUMBER_GLOKZ);
            enterDataAndWait(DOBInput, personalInfo.getDob());
            enterDataAndWait(CUSTOMER_ID_COLLECTION_FIELD, random(Integer.valueOf(12), NUMERIC));
        }
        userSelectsTheCreateAnAccountButton();
        waitForPage();
    }

    public void populateAddressFieldsWithSpecificPostCode(String strExpectedPostCode) {
        enterText(phoneNumber,"7729353241");
        enterText(ADDRESS_SEARCH_VUSEDE, strExpectedPostCode);
        waitForExpectedElement(ADDRESS_SEARCH_RESULT).click();
    }

    public void loginwithdeleteaccountdetail() {
        String password = UrlBuilder.getMessage("loginValidPassword.key");
        waitForExpectedElement(EMAIL_INPUTBOX, 20).sendKeys(emailAddressData);
        waitForExpectedElement(PASSWORD_INPUTBOX,10).sendKeys(password);
        clickByElementByQueryJSExecutor(SIGNIN_BUTTON);
        scrollToPageTop();
    }

    public void addSAIDNumber() {
        switch (UrlBuilder.getLocale()) {
            case "vuseza":
                waitForExpectedElement(SA_ID_NUMBER).sendKeys(UrlBuilder.getMessage("SAIDNumber.key"));
                break;
            default:
        }
    }

    public boolean fieldMarkedAsMandatory(String fieldName) {
        String FIELD_SELECTOR = null;
        switch (fieldName) {
            case "first name":
                FIELD_SELECTOR = FIRST_NAME_LABEL;
                break;
            case "last name":
                FIELD_SELECTOR = LAST_NAME_LABEL;
                break;
            case "email":
                FIELD_SELECTOR = EMAIL_LABEL;
                break;
            case "dob":
                FIELD_SELECTOR = DOB_LABEL;
                break;
            case "phone":
                FIELD_SELECTOR = TELEPHONE_LABEL;
                break;
            case "password":
                FIELD_SELECTOR = PASSWORD_LABEL;
                break;
            case "confirm password":
                FIELD_SELECTOR = CONFIRM_PASSWORD_LABEL;
                break;
            case "sa id number":
                FIELD_SELECTOR = SAID_NUMBER_LABEL;
                break;
            default:
                assertThat(true).as("ERROR fieldMarkedAsMandatory: invalid field name supplied -> " + fieldName).isFalse();
        }
        LOG.info("checking field " + fieldName);
// asterisk is a pseudo element
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        String pseudoElement = js.executeScript("return window.getComputedStyle(document.querySelector('" + FIELD_SELECTOR + "'),'::after').getPropertyValue('content')")
                .toString();
        return pseudoElement.equals(UrlBuilder.getMessage("mandatorySymbol"));
    }

    public boolean checkboxConsentAsMandatory(String fieldName) {
        String FIELD_SELECTOR = null;
        switch (fieldName) {
            case "Age policy":
                waitForExpectedElement(TERMS_AND_CONDITIONS_VELOPL).isDisplayed();
                FIELD_SELECTOR = CONSENT_AGE_LABEL;
                break;
            case "terms and conditions":
                waitForExpectedElement(CONSENT_AGREEMENT_CHECKBOX).isDisplayed();
                FIELD_SELECTOR = CONSENT_AGREEMENT_LABEL;
                break;

            default:
                assertThat(true).as("ERROR checkboxConsentAsMandatory: invalid field name supplied -> " + fieldName).isFalse();
        }
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        String pseudoElement = js.executeScript("return window.getComputedStyle(document.querySelector('" + FIELD_SELECTOR + "'),'::after').getPropertyValue('content')")
                .toString();
        return pseudoElement.equals(UrlBuilder.getMessage("mandatorySymbol"));
    }

    public boolean checkboxConsentAsNOnMandatory(String fieldName) {

        String FIELD_SELECTOR = null;
        switch (fieldName) {
            case "email-marketing":
                waitForExpectedElement(CONSENT_EMAIL_CHECKBOX).isDisplayed();
                FIELD_SELECTOR = CONSENT_EMAIL_LABEL;
                break;
            case "mobile":
                waitForExpectedElement(CONSENT_TRIAL_FEEDBACK_CHECKBOX).isDisplayed();
                FIELD_SELECTOR = CONSENT_FACEBOOK_LABEL;
                break;
            case "facebook":
                waitForExpectedElement(CONSENT_MOBILE_CHECKBOX).isDisplayed();
                FIELD_SELECTOR = CONSENT_MOBILE_LABEL;
                break;
            case "all third party":
                waitForExpectedElement(CONSENT_ALL_CHECKBOX).isDisplayed();
                FIELD_SELECTOR = CONSENT_ALLTHIRDPARTY_LABEL;
                break;
            case "hypercare":
                waitForExpectedElement(CONSENT_TRIAL_FEEDBACK_CHECKBOX).isDisplayed();
                FIELD_SELECTOR = CONSENT_HYPERCARE_LABEL;
                break;
            default:
                assertThat(true).as("ERROR checkboxConsentAsMandatory: invalid field name supplied -> " + fieldName).isFalse();
        }
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        String pseudoElement = js.executeScript("return window.getComputedStyle(document.querySelector('" + FIELD_SELECTOR + "'),'::after').getPropertyValue('content')")
                .toString();
        return !pseudoElement.equals(UrlBuilder.getMessage("mandatorySymbol"));
    }


    public void validateCreateAccountButton() {
        waitForExpectedElement(CREATEACCOUNT_BUTTON).isDisplayed();
        waitForExpectedElement(CREATEACCOUNT_BUTTON).isEnabled();
        String actualButtonText = waitForExpectedElement(CREATEACCOUNT_BUTTON).getText();
        String language = scenarioContext.getContext(LANGUAGE).toString();
        String expectedButtonText = UrlBuilder.getMessage("createAccountButtonText-" + language);
        assertThat(actualButtonText.equalsIgnoreCase(expectedButtonText))
                .as("ERROR validateCreateAccountButton: for language " + language + " expected " + expectedButtonText + " but got " + actualButtonText).isTrue();
    }

    public void checkRegistrationError(String fieldName) {
        String actualErrorMessage = null;
        String expectedErrorMessage;
        By ERROR = null;
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VELOBE:
            case VUSEIT:
            case VELOPL:
                expectedErrorMessage = UrlBuilder.getMessage("requiredFieldError-" + scenarioContext.getContext(LANGUAGE));
                break;
            case VUSEZA:
            case VUSEFR:
                expectedErrorMessage = UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key");
                break;
            default:
                expectedErrorMessage = UrlBuilder.getMessage("requiredFieldError");
        }
        switch (fieldName) {
            case "firstName":
                ERROR = REGISTRATION_FIRST_NAME_ERROR;
                break;
            case "lastName":
                ERROR = REGISTRATION_LAST_NAME_ERROR;
                break;
            case "email":
                ERROR = REGISTRATION_EMAIL_ERROR;
                break;
            case "dob":
                ERROR = REGISTRATION_DOB_ERROR;
                break;
            case "genda":
                ERROR = REGISTRATION_GENDER_ERROR;
                break;
            case "codiceFiscale":
                ERROR = REGISTRATION_CODICE_FISCALE_ERROR;
                break;
            case "birthCity":
                ERROR = REGISTRATION_BIRTH_CITY_ERROR;
                break;
            case "addressSearch":
                ERROR = REGISTRATION_ADDRESS_SEARCH_ERROR;
                break;
            case "telephone":
                ERROR = REGISTRATION_TELEPHONE_ERROR;
                break;
            case "password":
                ERROR = REGISTRATION_PASSWORD_ERROR;
                break;
            case "confirmPassword":
                ERROR = REGISTRATION_PASSWORD_CONFIRM_ERROR;
                break;
            case "Age policy":
                ERROR = AGE_POLICY_CONFIRM_ERROR;
                break;
            case "terms and conditions":
                ERROR = TERM_AND_CONDITION_CONFIRM_ERROR;
                break;
            case "sa id number":
                ERROR = REGISTRATION_SA_ID_ERROR;
                break;
            default:
                assertThat(true).as("ERROR checkRegistrationError: invalid field name " + fieldName).isFalse();
        }
        if (ERROR==REGISTRATION_SA_ID_ERROR){
            actualErrorMessage = waitForExpectedElement(ERROR, 5).getText();
            expectedErrorMessage = UrlBuilder.getMessage("requiredFieldErrorSAID");
            assertThat(expectedErrorMessage.equals(actualErrorMessage))
                    .as("ERROR checkRegistrationError: expected error message was " + expectedErrorMessage + " but I got " + actualErrorMessage + " for field " + fieldName).isTrue();

        }else{
        actualErrorMessage = waitForExpectedElement(ERROR, 5).getText();
        assertThat(expectedErrorMessage.equals(actualErrorMessage))
                .as("ERROR checkRegistrationError: expected error message was " + expectedErrorMessage + " but I got " + actualErrorMessage + " for field " + fieldName).isTrue();
    }}

    public void checkGTM() {
        refreshBrowser();
        waitForElementToAppearAndDisappear(LOADER, 5, 5);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        ArrayList<Map<String, List<String>>> gtmList;
        //Execute GTM script to fetch values
        try {
            gtmList = (ArrayList) js.executeScript("return window.dataLayer");
        } catch (StaleElementReferenceException s) {
            waitForElementToAppearAndDisappear(LOADER,5,5);
            gtmList = (ArrayList) js.executeScript("return window.dataLayer");
        }
        assertThat(gtmList.size() > 0).as("ERROR checkGTM: no Google Tag manager events were detected").isTrue();
        LOG.info(gtmList.size() + " google tag manager events found");
    }

    public void ensureNoNewsletter(String page) throws InterruptedException {
        LOG.info("checking for any visible references to newsletter on page " + page);
        assertThat(!webDriver.findElement(By.cssSelector("body")).getText().toLowerCase().contains("newsletter"))
                .as("ERROR ensureNoNewsletter: page " + page + " contains a reference to newsletter").isTrue();

    }

    public void validateLiveChat(String page) throws InterruptedException {
        LOG.info("validateLiveChat: testing " + page);
        if (page.equals("checkout")) {
            homePage.clickOnProceedToCheckoutButton();
        } else if (!page.equals("my account")) {
            homePage.clickPersonIcon();
            homePage.chooseMyAccountOnAccountDropdown();
            logininPage.storeUserData();
        }
        WebElement frame = waitForExpectedElement(CHAT_FRAME, 10);
        getWebDriver().switchTo().frame(frame);
        waitForExpectedElement(CHAT_BUTTON).click();
        webDriver.switchTo().defaultContent();
        waitForExpectedElement(By.cssSelector("#webWidget"), 5);
        webDriver.switchTo().frame("webWidget");
        waitForExpectedElement(START_CHAT_BUTTON).click();
        List<WebElement> errorMessages = waitForExpectedElements(CHAT_ERROR);
        String expectedChatNameError = UrlBuilder.getMessage("chatNameError-" + scenarioContext.getContext(LANGUAGE).toString());
        String actualChatNameError = errorMessages.get(ChatError.NAME.ordinal()).getText();
        assertThat(expectedChatNameError.equalsIgnoreCase(actualChatNameError))

                .as("ERROR validateLiveChat: expected chat name error was "+expectedChatNameError+" but I got "+actualChatNameError).isTrue();
        String expectedChatEmailError = UrlBuilder.getMessage("chatEmailError-" + scenarioContext.getContext(LANGUAGE).toString());
        String actualChatEmailError = errorMessages.get(ChatError.EMAIL.ordinal()).getText();
        assertThat(expectedChatEmailError.equalsIgnoreCase(actualChatEmailError))
                .as("ERROR validateLiveChat: expected chat email error was "+expectedChatEmailError+" but I got "+actualChatEmailError).isTrue();

        String expectedChatDepartmentError = UrlBuilder.getMessage("chatDepartmentError-" + scenarioContext.getContext(LANGUAGE).toString());
        String actualChatDepartmentError = errorMessages.get(ChatError.DEPARTMENT.ordinal()).getText();
        assertThat(expectedChatDepartmentError.equalsIgnoreCase(actualChatDepartmentError))
                .as("ERROR validateLiveChat: expected chat department error was " + expectedChatDepartmentError + " but I got " + actualChatDepartmentError).isTrue();
        String expectedChatMessageError = UrlBuilder.getMessage("chatMessageError-" + scenarioContext.getContext(LANGUAGE).toString());
        String actualChatMessageError = errorMessages.get(ChatError.MESSAGE.ordinal()).getText();
        assertThat(expectedChatMessageError.equalsIgnoreCase(actualChatMessageError))
                .as("ERROR validateLiveChat: expected chat message error was " + expectedChatMessageError + " but I got " + actualChatMessageError).isTrue();
        webDriver.switchTo().defaultContent();
    }

    public void assertThatOnYotiAgeEstimationPage() {
        String expectedPageheading = UrlBuilder.getMessage("yotiPageHeading-" + scenarioContext.getContext(LANGUAGE));
        String actualPageHeading = waitForExpectedElement(YOTI_PAGE_HEADING).getText();
        assertThat(expectedPageheading.equalsIgnoreCase(actualPageHeading))
                .as("ERROR assertThatOnYotiAgeEstimationPage: expected to be on yoti page with heading " + expectedPageheading + " but i got " + actualPageHeading).isTrue();
    }

    public void confirmCannotLoginBeforeYotiAV() {
        logininPage.login(scenarioContext.getContext(EMAIL_ID).toString(), scenarioContext.getContext(PASSWORD).toString());
        logininPage.validateLoginErrorMessage();
    }

    public void registerAccountWithSameEmailIDasFacebookAccountIfNotExistingInM2() {
        if (doesURLContain("customer/account/create/")) {
            enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
            waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
            populateAddressFields();
            checkTsCs();
            userSelectsTheCreateAnAccountButton();
            waitForPage();
            waitForExpectedElement(USER_LOGGED_IN_MESSAGE, 5);
        } else {
            //delete account if exist
            try {
                accountDashboardPage.deleteMyAccount();
                homePage.clickPersonIcon();
                homePage.clickOnLinksFromPersonMenu("signInLink.key");
                waitForExpectedElement(logininPage.SIGN_IN_WITH_FACEBOOK_BUTTON).click();
                logininPage.assertNewWindowOpensWithSocialLoginPage("facebookLoginURL.key");
                logininPage.enterFacebookEmailAndPasswordAndClickLogin("facebookLoginEmail1.key", "facebookLoginPassword.key");
                enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
                waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
                populateAddressFields();
                checkTsCs();
                userSelectsTheCreateAnAccountButton();
                waitForPage();
                waitForExpectedElement(USER_LOGGED_IN_MESSAGE, 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void verifySubscriptionMailDisplayed(){
        getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData.substring(0, 6) + "#/#inboxpane");
        waitForExpectedElement(EmailConfirmationMessage, 5);
        waitForExpectedElement(EMAIL_RECEIVED, 20);//add for FF
        getWebDriver().findElement(EMAIL_RECEIVED).click();
        waitForPage();
        waitForExpectedElement(SHOW_BUTTON,20).click();
        List<WebElement> actualItems = getWebDriver().findElements(CLICK_LINKS);
        List<String> URLs = actualItems.stream().map(WebElement::getText).collect(toList());
        for(String url:URLs) {
            if (url.contains("subscriber")) {
                getWebDriver().get(url);
            }
        }
    }

    public void assertHomePageIsDisplayed() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEFR:
                String crtUrl=getWebDriver().getCurrentUrl();
                if(crtUrl.endsWith("/")){
                    crtUrl=crtUrl.substring(0, crtUrl.length() - 1);
                }
                assertTrue(crtUrl.equals(UrlBuilder.getUrl()));
                break;
            case PL:
            case GLODE:
            case KZ:
            case LYFTSE:
                assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getUrl()));
                break;
            case VELOPL:
                assertTrue(isElementDisplayedBySeconds(PL_HOMEPAGE_IMAGES,10));
                break;
            default:
                assertTrue(waitForExpectedElement(eleHomePageActionBar, 10).isDisplayed());
        }
    }
    public List<WebElement> getDataProtectionElements(){
        By DATA_PROTECTION_LINK=By.linkText(UrlBuilder.getMessage("dataprotection.key"));
        return waitForExpectedElements(DATA_PROTECTION_LINK);
    }

    public void assertUserNotAllowedToEnterMoreThanDigitsInPhoneNumberField(String strCharlimit) {
        waitForExpectedElement(PHONE_NUMBER_TXT_BOX,10);
        enterDataAndWait(PHONE_NUMBER_TXT_BOX,random(Integer.valueOf(strCharlimit),NUMERIC));
        waitForAjaxElementNotToBePresent(getWebDriver(),4);
        String[] phoneNumberVal=waitForExpectedElement(PHONE_NUMBER_TXT_BOX).getAttribute("value").split("49");
        waitForAjaxElementNotToBePresent(getWebDriver(),4);
        assertTrue(phoneNumberVal[1].length()==Integer.valueOf(strCharlimit)-1);
    }

    public void assertErrorMessageWhenPhoneNumberStartingWithZeroLessThanLimitIsEntered(String strErrorText) {
        enterDataAndWait(PHONE_NUMBER_TXT_BOX,"0"+random(9,NUMERIC));
        if(doesURLContain("customer/address/"))
            addNewAddressPage.pressSaveAddressButton();
        else
            userSelectsTheCreateAnAccountButton();
        assertTrue(waitForExpectedElement(TELEPHONE_ERROR_MESSAGE,5).getText().equalsIgnoreCase(UrlBuilder.getMessage(strErrorText)));
    }

    public boolean hasDuplicateItemsInTheAddressDropdownList() {
        Select addressDropDown = new Select(waitForExpectedElement(SELECT_REGIONID,20));
        List<WebElement> webElements = addressDropDown.getOptions();
        Set<String> addressSet = new HashSet<String>();
        for (WebElement webElement : webElements) {
            if (addressSet.contains(webElement.getText())) {
                return true;
            } else {
                addressSet.add(webElement.getText());
            }
        }
        return false;
    }

    public void userEntersCharactersLimitInPhoneNumberField(String strLimit) {
        enterDataAndWait(PHONE_NUMBER_TXT_BOX, random(Integer.valueOf(strLimit) - 1, NUMERIC));
        clickElementByQueryJSExecutor(getWebDriver().findElements(CREATEACCOUNT_BUTTON).get(0));
    }

    public String getTelephoneErrorMessage() {
        return waitForExpectedElement(TELEPHONE_ERROR_MESSAGE).getText();
    }

    public void userEntersNonNumericCharactersInPhoneNumberField(String strLimit) {
        enterDataAndWait(PHONE_NUMBER_TXT_BOX, random(Integer.valueOf(strLimit), ALPHABETS));
        clickElementByQueryJSExecutor(getWebDriver().findElements(CREATEACCOUNT_BUTTON).get(0));
    }

    public void userEntersNumberInPhoneNumberField(long number) {
        enterDataAndWait(PHONE_NUMBER_TXT_BOX, Long.toString(number));
        clickElementByQueryJSExecutor(getWebDriver().findElements(CREATEACCOUNT_BUTTON).get(0));
    }

    public void createANewAccountWithRestrictedStreetAddress(String strInputText){
        homePage.navigateToSignInPage();
        logininPage.clickRegistrationButton();
        enterSpecifiedUserDetails(firstNameData,lastNameData,UrlBuilder.getMessage("ValidDOB.key"),UrlBuilder.getMessage("Gender.key"));enterDataAndWait(DOBInput, UrlBuilder.getMessage("ValidDOB.key"));
        waitAndClickByElementByJSExecutor(expandManualAddressFields,10);
        if(strInputText.contains("Street"))
            enterSpecifiedAddressDetails(UrlBuilder.getMessage(strInputText),townOrCityData,UrlBuilder.getMessage("postalCode.key"));
        else
            enterSpecifiedAddressDetails(UrlBuilder.getMessage("streetAddress.key"),townOrCityData,UrlBuilder.getMessage(strInputText));
        populateSigninFields(emailAddressData);
        enterText(phoneNumber,"7729353241");
        userSelectsTheCreateAnAccountButton();
        verifyEmailAndReturnToMyAccountPage();
        waitForPage();
    }

    public void enterNewsLetterDetail(String dob) {
        enterText(NEWSLETTER_FIRSTNAME, "testfirstname");
        enterText(NEWSLETTER_SURNAME, "testlastname");
        String emailAddress = RandomGenerator.randomEmailAddress(6);
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        waitClearAndEnterText(NEWSLETTER_EMAIL, emailAddress);
        waitClearAndEnterText(BANK_ID, "197506032916");
        if(dob.equals("below 18 years"))
            enterDataAndWait(DOB, UrlBuilder.getMessage("UnderAgeDOB"));
        else enterDataAndWait(DOB, UrlBuilder.getMessage("ValidDOB.key"));
        waitForExpectedElement(DOB, 10).sendKeys(Keys.TAB);
        clickUsingJS(TERMS_AND_CONDITIONS_CHK_BOX);
        clickUsingJS(EMAIL_CONSENT_CHK_BOX);
        waitForExpectedElement(SUBSCRIBE_BUTTON,5).click();
    }

    public void assertErrorMessageForInvalidEmailMobileNumberConfirmPassword() {
        String expectedEmailErrorMessage = UrlBuilder.getMessage("loginInvalidEmailErrorMsg.key");
        assertTrueWithCustomError(expectedEmailErrorMessage,waitForExpectedElement(REGISTRATION_EMAIL_ERROR,10).getText());
        String expectedTelephoneErrorMessage = UrlBuilder.getMessage("errorTelephonerequired.key");
        assertTrueWithCustomError(expectedTelephoneErrorMessage,waitForExpectedElement(TELEPHONE_ERROR_MESSAGE,10).getText());
        String expectedConfirmErrorMessage = UrlBuilder.getMessage("errorConfirmPasswordrequired.key");
        assertTrueWithCustomError(expectedConfirmErrorMessage,waitForExpectedElement(REGISTRATION_PASSWORD_CONFIRM_ERROR,10).getText());
    }

    public void enterRegistrationDetailsAndCreateAccountWithoutDuplicateEmail()  {
        populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData();
        String dob = doesURLContain("/se/en") ?
                UrlBuilder.getMessage("ValidDOBEn.key") : UrlBuilder.getMessage("ValidDOB.key");
        enterDataAndWait(DOBInput, dob);
        waitForExpectedElement(DOBInput, 10).sendKeys(Keys.TAB);
        selectValueFromDropDownByby(UrlBuilder.getMessage("Gender.key"),gender);
        populateAddressFields();
        String email_field = UrlBuilder.getMessage("email.key");
        String password = UrlBuilder.getMessage("loginValidPassword.key");
        scenarioContext.setContext(PASSWORD, password);
        enterDataUsingJS(waitForExpectedElement(email),email_field);
        enterDataUsingJS(waitForExpectedElement(this.password), password);
        enterDataUsingJS(waitForExpectedElement(passwordConfirmation), password);
        checkTsCs();
        checkEmailNewsletter();
        userSelectsTheCreateAnAccountButton();
        waitForPage();
    }
    public void createANewAccountWithDuplicateEmailAddress()  {
        homePage.navigateToSignInPage();
        logininPage.clickRegistrationButton();
        enterRegistrationDetailsAndCreateAccountWithoutDuplicateEmail();
    }

    public void enterPostCode(String postCodeData) {
        enterDataAndWait(postCode,postCodeData);
    }
    public boolean isPostCodeResultsDropdownDisplayed() {
        return waitForExpectedElement(POST_CODE_RESULTS_DROPDOWN_ITEM).isDisplayed();
    }

    public void goToRegistrationDobMessagePageAndTakeEyesScreenshot() {
        if (Props.EYES_ON && EyesCheckpoints.REGISTRATION_DOB_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.REGISTRATION_DOB_PAGE.getName();
            switch (UrlBuilder.getLocale()) {
                case "vuseit":
                    enterDataAndWait(DOBInput, UrlBuilder.getMessage("invalidDOB.key"));
                    userSelectsTheCreateAnAccountButton();
                    scrollToShowEntirePage();
                    eyes.check(checkpointName, Target.window().fully());
                    break;
            }
        }
    }
    public boolean verifyEmailMessage(String emailType) {
        LOG.info("email substring " + emailAddressData.substring(0, 6));
        //Redirect to Mailinator
        getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData.substring(0, 6) + "#/#inboxpane");
        waitForExpectedElement(EmailConfirmationMessage, 5);
        String expectedEmailSubject="";
        boolean flag=false;
        switch (emailType){
            case "reset password":
                expectedEmailSubject=UrlBuilder.getMessage("emailPasswordResetSubject.key");
                break;
            case "order confirmation":
                break;
            default:
                LOG.info("validate create new user email subject.");
        }
        List<WebElement> receivedEmails = mailinatorPage.getReceivedEmailElements(emailAddressData);
        for(WebElement email:receivedEmails){
            if(expectedEmailSubject.contentEquals(email.getText())){
                flag=true;
                break;
            }
        }
        return flag;
    }

    public String getDOBErrorMessage() {
        return waitForExpectedElement(REGISTRATION_DOB_ERROR_MESSAGE, 5).getText();
    }

    public String getCodiceFiscaleErrorMessage() {
        return waitForExpectedElement(REGISTRATION_CODICE_FISCALE_ERROR_MESSAGE, 5).getText();
    }

    public String getExistEmailErrorMessage() {
        return waitForExpectedElement(REGISTRATION_EXIST_EMAIL_ERROR_MESSAGE, 5).getText();
    }

    public String getPasswordErrorMessage() {
        return waitForExpectedElement(REGISTRATION_PASSWORD_ERROR, 5).getText();
    }

    public String getTsAndCsErrorMessage() {
        return waitForExpectedElement(TERM_AND_CONDITION_CONFIRM_ERROR_VUSEIT, 5).getText();
    }

    public void withoutClickingCheckboxCickOnDeleteAndVerifyErrorMessage()  {
       clickByElementByQueryJSExecutor(DELETE_ACCOUNT_BUTTON);
       assertTrue(isElementPresentByby(DELETE_ACCOUNT_ERROR_MESSAGE));
    }

    public void clickCheckboxDeleteAccountVerifyMessage()  {
        clickUsingJS(DELETE_ACCOUNT_CHECKBOX);
        clickUsingJS(DELETE_ACCOUNT_BUTTON);
        assertTrue(isElementPresentByby(DELETE_ACCOUNT_SUCCESS_MESSAGE));
    }

    public void loginWithDeletedUserDetail()  {
        String username = System.getProperty("UserEmailAddress.key");
        String password = UrlBuilder.getMessage("loginValidPassword.key");
        scenarioContext.setContext(Context.EMAIL_ID, username);
        waitForExpectedElement(EMAIL_INPUTBOX, 20).sendKeys(username);
        waitForExpectedElement(PASSWORD_INPUTBOX,10).sendKeys(password);
        clickByElementByQueryJSExecutor(SIGNIN_BUTTON);
        waitForExpectedElement(ACCOUNT_SIDEBAR,5);
    }
}
