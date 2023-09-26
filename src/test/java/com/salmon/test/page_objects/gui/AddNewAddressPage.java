package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.cucumber.glo.myaccount.AddressModel;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.gloIt.GloItCheckoutPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.gui.AccountDashboardPage.MY_ACCOUNT_LINKS;
import static com.salmon.test.page_objects.gui.constants.Context.CURRENT_ADDRESS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddNewAddressPage extends PageObject {

	private AccountDashboardPage accountDashboardPage;
	private RegistrationPage registrationPage;
	private ScenarioContext scenarioContext;
	private LoqateAddressLookUpPage loqateAddressLookUpPage;

	public AddNewAddressPage(AccountDashboardPage accountDashboardPage, RegistrationPage registrationPage,LoqateAddressLookUpPage loqateAddressLookUpPage,ScenarioContext scenarioContext) {
		this.accountDashboardPage = accountDashboardPage;
		this.registrationPage = registrationPage;
		this.scenarioContext = scenarioContext;
		this.loqateAddressLookUpPage=loqateAddressLookUpPage;
	}

	public AddNewAddressPage() {
	}

	// ELEMENT MAPPING
	// Text
	public String pageTitle = "Add New Address";
	public final static By pageHeading = By.cssSelector("span.base"); // "Add New Address
	public final static By eleAddnewAddressSection = By.cssSelector("#form-validate");
	public final static By eleAddNewAddressForm = By.cssSelector("aside.modal-popup.modal_edit_address.address_edit_new.modal-slide._inner-scroll._show > div.modal-inner-wrap");
	public final static By eleAddNewAddressFormDe = By.id("edit_billing");
	public final static By eleAddNewAddressFormPl = By.cssSelector("#Edit_address_modal_new");
	private final static By ADDADDRESSPOPUP_SAVEADDRESS = By.cssSelector("footer.modal-footer>button.action.submit.primary");
	private final static By ADDADDRESSPOPUP_SAVEADDRESS_LYFTSE = By.cssSelector("form#form-validate button[type=\"submit\"]");
	private final static By ADDADDRESSPOPUP_SAVEADDRESS_IT = By.cssSelector("footer.modal-footer > button.action.submit.primary");
	private final static By ADDADDRESSPOPUP_ADDRESSFINDER = By.cssSelector("#address-search");
	private String POSTCODE_GLO_DE = random(4, NUMERIC) + " " + random(3, NUMERIC);
	// input fields

	public static By TOGGLE_FULL_ADDRESS = By.cssSelector("#js--address-search-toggle");
	public static By TOGGLE_FULL_ADDRESS_GLOIT = By.cssSelector("div.pcaitem");
	public final static By firstNameInput = By.cssSelector("#firstname");
	public final static By firstNameAddress = By.cssSelector("form.form-address-edit #firstname");
	public final static By firstNameSubscrAddress = By.cssSelector("#co-shipping-form input[name='firstname']");
	public final static By lastNameSubscrAddress = By.cssSelector("#co-shipping-form input[name='lastname']");
	public final static By streetSubscrAddress = By.cssSelector("#co-shipping-form input[name='street[0]']");
	public final static By citySubscrAddress = By.cssSelector("#co-shipping-form input[name='city']");
	public final static By regionSubscrAddress = By.cssSelector("#co-shipping-form select[name='region_id'] option:not([value=''])");
	public final static By regionSelectorSubscrAddress = By.cssSelector("#co-shipping-form select[name='region_id']");
	public final static By postcodeSubscrAddress = By.cssSelector("#co-shipping-form input[name='postcode']");
	public final static By phoneSubscrAddress = By.cssSelector("#co-shipping-form input[name='telephone']");
	public final static By surNameInput = By.cssSelector("#lastname");
	public final static By surNameAddress = By.cssSelector("form.form-address-edit #lastname");
	public final static By companyInput = By.cssSelector("form.form-address-edit #company");
	public final static By TELEPHONE_INPUT = By.cssSelector("form.form-address-edit #telephone");
	public final static By STREET_ADDRESS_LINE_1_INPUT = By.cssSelector("#street_1");
	public final static By streetAddressLine2Input = By.cssSelector("#street_2");
	public final static By streetAddressLine3Input = By.cssSelector("#street_3");
	public final static By CITY_INPUT = By.cssSelector("form.form-address-edit #city");
	public final static By StateInput = By.cssSelector("select#region_id");
	public final static By POST_CODE_INPUT = By.cssSelector("form.form-address-edit #zip");
	private final static By COLONIA_MX = By.cssSelector("input#colonia");
	public final static By countrySelectDropDown = By.cssSelector("select#country.required-entry");
	public final static By COUNTRY_SELECT_ON_ADDRESS=By.cssSelector("select#country");
	public final static By EDIT_BILLING_ADRESS_BUTTON=By.cssSelector("button#edit_billing");
	public final static By EDIT_SAVE_MESSAGE=By.cssSelector("div.page.messages > div> div > div > div");
	public final static By USE_AS_DEFAULT_SHIPPING_ADDRESS_LABEL=By.cssSelector("div[class='field choice set shipping']>label");
	public final static By USE_AS_DEFAULT_BILLING_ADDRESS_LABLE=By.cssSelector("div[class='field choice set billing']>label");
	public final static By USE_AS_DEFAULT_SHIPPING_ADDRESS_CHANGES=By.cssSelector("div.field.choice.set.shipping.edit-add-radio > div > label");
	public final static By useAsDefaultBillingAddressTickBox = By.cssSelector("div.field.choice.set.billing:nth-child(8) > label.label");
	public final static By userAsDefaultShippingAddressTickBox = By.cssSelector("div.field.choice.set.shipping:nth-child(9) > label.label");
	public final static By USER_AS_DEFAULT_SHIPPING_ADDRESS_TICK_BOX_UK = By.cssSelector("div.field.choice.set.shipping > label");
	public final static By USER_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_UK = By.cssSelector("div.field.choice.set.billing> label");
	public final static By useAsDefaultBillingAddressTickBoxMX = By.cssSelector("div.field.choice.set.billing:nth-child(12) > label.label");
	public final static By userAsDefaultShippingAddressTickBoxMX = By.cssSelector("div.field.choice.set.shipping:nth-child(13) > label.label");
	public final static By userAsDefaultShippingAddressTickBoxVuseCO = By.cssSelector("div.field.choice.set.shipping");
	public final static By useAsDefaultBillingAddressTickBoxVuseCO = By.cssSelector("div.field.choice.set.billing");
	public final static By userAsDefaultShippingAddressTickBoxDE = By.cssSelector("div.field.choice.set.shipping > label > span");
	public final static By useAsDefaultBillingAddressTickBoxCO = By.cssSelector("form#form-validate div.field.choice.set.billing > label");
	public final static By STATE_DROP_DOWN_VUSE_MX = By.cssSelector("div.region > div.control > select#region_id");
	public final static By STATE_DROP_DOWN_OPTIONS_VALUE_VUSE_MX = By.cssSelector("div.region > div.control > select#region_id > option");
	private final static By CO_STATE_DROP_DOWN = By.cssSelector("select#region_id");
	public final static By MX_STATE_INPUT = By.cssSelector("input#region.input-text");
	private final static By USER_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_VELO = By.cssSelector("#default_address_change");
	public final static By TXT_OUTDOOR_NUMBER = By.cssSelector("input#neo_ext");
	public final static By txtTelephoneEditAddress = By.cssSelector("#telephone");
	private static final By EDIT_ADDRESS_TELEPHONE = By.cssSelector("form.form-address-edit input[name='telephone']");
	private static final By EDIT_ADD_ADDRESS_MANUALLY_VYPEIT = By.cssSelector("a#js--address-search-toggle");
	private static final By PHONE_NUMBER_FIELD_CHECKOUT_ZA= By.cssSelector("div.checkout-billing-address input[name=telephone]");
	private static final By ADDRESS_QUICK_FINDER_PAYMENT_SECTION=By.cssSelector("input#billing-address-search-paygate");
	private static final By BUTTON_USE_THIS_ADDRESS_PAYMENT_SECTION=By.cssSelector("button.action.primary.action-update.address-list__use-button");
	private static final By DEFAULT_BILLING_ADDRESS_RADIO_BUTTON= By.xpath("//label[@for='default_address_change']");

	// Buttons
	public final static By saveAddressButton = By.cssSelector("footer > button[type=\"button\"].action.submit.primary");
	public final static By saveAddressButtonCO = By.cssSelector("form#form-validate button[type=\"submit\"]");
	public final static By goBackButton = By.cssSelector("a.action.back");
	public final static By GOBACK_BUTTON_DE = By.cssSelector("aside.modal-popup.modal_edit_address.address_edit_new.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button.action-close");
	private static final By CLOSE_ADDRESS_PANEL_FR = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.modal_edit_address.address_edit_new.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
	private static final By CANCEL_EDIT_ADDRESS = By.cssSelector("a.action.back > span");
	private static final By CANCEL_EDIT_ADDRESS_MX = By.cssSelector("button.action-close");
	private static final By CANCEL_EDIT_ADDRESS_DE = By.cssSelector("._inner-scroll._show > div.modal-inner-wrap > header > button");
	public final static By SAVE_ADDRESS_BUTTON_LYFT_DK = By.cssSelector("form#form-validate button[type=\"submit\"]");
	public static final By SAVE_ADDRESS_BUTTON_VELO = By.cssSelector("button.action.save.primary span");
	public static final By SAVE_ADDRESS_BUTTON_MODAL_VELOBE = By.cssSelector("#html-body > div.modals-wrapper > aside.modal-popup.modal_edit_address.modal-slide._inner-scroll._show > div.modal-inner-wrap > footer > button");
	public static final By SAVE_ADDRESS_BUTTON_VELOBE = By.cssSelector("button[data-action='save-address']");
	public static final By SAVE_ADDRESS_BUTTON_VELO2 = By.cssSelector("button.action.primary.action-save-address");
	public static final By SAVE_ADDRESS_BUTTON_DE = By.cssSelector("form#form-validate button[type=\"submit\"]");
	public static final By SAVE_ADDRESS_BUTTON_IT = By.cssSelector("form#form-validate button[type=\"submit\"]");
	public static final By SAVE_ADDRESS_BUTTON_NL = By.cssSelector("button.action.submit.primary > span:nth-child(1)");
	public static final By SAVE_ADDRESS_BUTTON_KZ = By.cssSelector("div.primary > button.action.submit.primary > span:nth-child(1)");
	private static final By EDIT_SHIPPING_ADDRESS_LINK = By.cssSelector("button#edit_shipping,a#edit_shipping");
	private static final By EDIT_SHIPPING_ADDRESS_LINK_KZ = By.cssSelector("div.box.box-address-shipping:nth-child(2) div.box-actions a.action.edit > span:nth-child(1)");
	private static final By FOOTER_SUBMIT_BUTTON = By.cssSelector("footer > button.action.submit.primary");
	public static  final By SAVE_ADDRESS_BUTTON_JP = By.cssSelector("button.btn-prim.btn-mypage");
	private static final By EDIT_BILLING_BUTTON = By.cssSelector("button#edit_billing");
	private static final By EDIT_ADDRESS_LINK_FR = By.cssSelector("#edit_billing");
	private static final By EDIT_ADDRESS_LINK_DE = By.cssSelector("#edit_address");
	private static final By EDIT_ADDRESS_LINK_MX=By.cssSelector("a.action.edit:nth-child(1) > i.material-icons");
	private static final By EDIT_ADDRESS_LINK_JP=By.cssSelector("a.action.update-address");
	private static final By EDIT_ADDRESS_LINK_VUSEDE = By.cssSelector("#edit_billing");
	private static final By VAT_ID_TXT_BOX = By.cssSelector("input#vat_id");
	private static final By VAT_ID_BY_NAME = By.name("vat_id");
	private static final By ENTER_ADDRESS_MANUALLY_PL = By.cssSelector("#js--address-search-toggle");
	public static final By EDIT_ADDRESS_LINK_VELO = By.cssSelector("div.shipping-address-item.selected-item > button");
	private static final By BILLING_EDIT_LINK_VELO = By.cssSelector("div.box.box-address-billing > div.box-actions span");
	private static final By MANUAL_ADDRESS_ENTRY_ADD_ADDRESS=By.xpath("//div[@id='address-book-search-container']//a[1]");
	private static final By ADDRESS_DROPDOWN_LINK_KZ = By.cssSelector("div.userLoggin div div div ul li:nth-child(4) a");
	private static final By USE_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_PL = By.cssSelector("#primary_billing");
	private static final By USE_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_KZ = By.cssSelector("div.field.choice.set.billing label");
	private static final By USE_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_VYPEIT = By.cssSelector("div.field.choice.set.billing > label");
	private static final By USE_AS_DEFAULT_SHIPPING_ADDRESS_TICK_BOX_VYPEIT = By.cssSelector("div.field.choice.set.shipping > label");
	private final static By USER_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_LYFTSE = By.cssSelector("#primary_billing");
	private final static By USER_AS_DEFAULT_SHIPPING_ADDRESS_TICK_BOX_LYFTSE = By.cssSelector("input#primary_shipping");
	private final static By USER_AS_DEFAULT_SHIPPING_AND_BILLING_ADDRESS_TICK_BOX_LYFTSE = By.cssSelector("#default_address_change_both");
	private static final By EDIT_SHIPPING_ADDRESS_GLODE = By.cssSelector("button#edit_shipping");
	//JP Address
	private static final By POST_CODE1_JP  = By.cssSelector("#postal-code-1");
	private static final By POST_CODE2_JP  = By.cssSelector("#postal-code-2");
	private static final By ADDRESS2_JP  = By.cssSelector("#Address2");
	private static final By ADDRESS3_JP  = By.cssSelector("#Address3");
	public String BuildingNumber=random(3,NUMERIC);

	private final static By BUTTON_ADDRESS_SAVE_FR = By.cssSelector(".action.submit.primary");
	public static final By SAVE_ADDRESS_BUTTON_VUSEFR = By.cssSelector("button.action.primary.action-save-address");
	public static final By CURRENT_BILLING_ADDRESS_DETAILS_VELOBE = By.cssSelector("div.box-address-billing");
	public static final By CURRENT_SHIPPING_ADDRESS_DETAILS_VELOBE = By.cssSelector("div.box-address-shipping");
	public static final By VAT_NUMBER_FIELD_ADD_ADDRESS=By.cssSelector("input#vat_id");
	public static final By VAT_NUMBER_FIELD_ADDRESS_ERROR=By.cssSelector("div#vat_id-error");
	private static final By CANCEL_EDIT_ADDRESS_UK = By.cssSelector("aside.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
	private final static By CHANGE_DELIVERY_ADDRESS = By.cssSelector("div.box-address-shipping a.edit");
	private final static By CHANGE_BILLING_ADDRESS = By.cssSelector("div.box-address-billing a.edit");
	public final static By ADDRESS_DETAIL_CONTENT=By.cssSelector("div.box.box-address-shipping > div>div>span");
	public final static By CLOSE_NEW_ADDRESS_POP_UP = By.cssSelector("aside.modal-popup.modal_edit_address.address_edit_new.modal-slide._inner-scroll._show:nth-child(4) header.modal-header > button.action-close");

	// Errors
	public static final By CELLULAR_ERROR = By.cssSelector("div#telephone-error.mage-error");
	public final static By Street1Error = By.cssSelector("div#street_1-error.mage-error");
	public final static By cityError = By.cssSelector("div#city-error.mage-error");
	public final static By postCodeError = By.cssSelector("div#zip-error.mage-error");
	public final static By eleTelephoneError = By.cssSelector("#telephone-error");
	public static final By STREET2_ERROR=By.cssSelector("div#street_2-error");
	public static final By STREET3_ERROR=By.cssSelector("div#street_3-error");
	private static final By ADDRESS_SEARCH_ERROR=By.cssSelector("#address-search-error");
	public static final By PL_STREET_ERROR = By.cssSelector(".page.messages > div:nth-child(2) > div > div:nth-child(2) >div");
	public static final By PL_CITY_ERROR = By.cssSelector(".page.messages > div:nth-child(2) > div > div:nth-child(3) > div");
	public static final By PL_POST_CODE_ERROR = By.cssSelector(".page.messages > div:nth-child(2) > div > div:nth-child(4) > div");
	private static final By EDIT_ADDRESS_LINK_MY_ACCOUNT = By.cssSelector("a.button.action.primary.edit");
	public static final By DEFAULT_BOTH_ADDRESS_CHANGE_INPUT = By.xpath("//label[@for='default_address_change_both']");

	public static final String firstNameData = random(6, ALPHABETS);
	public static final String lastNameData = random(6, ALPHABETS);

	private String phoneNumberData = "01"+random(10,NUMERIC);
	private String phoneNumberVypeIT = "01"+random(11,NUMERIC);
	private String phoneNumberCO = "6"+random(9,NUMERIC);
	private String phoneNumberKZ = "+71"+random(9,NUMERIC);
	public By province = By.cssSelector("#region_id");
	private final static String phoneNumberMX = "15"+random(9,NUMERIC);
	public final static By ENTER_ADDRESS_MANUALLY = By.cssSelector("a#js--address-search-toggle");
	private final static String PHONENUMBER_VYPEIT = "6" + random(9, NUMERIC);

	public static final By STREET_ADDRESS_VELOZA = By.cssSelector("input[name='street[2]']");
	public static final By BILLING_ADDRESS_DROPDOWN = By.cssSelector(".field.field-select-billing");
	public static final By NEW_ADDRESS_OPTION = By.xpath("//select[@name='billing_address_id']//option[text()='Neue Adresse']");
	public static final By STREET_AND_HOUSE_NUMBER = By.cssSelector("div[name='billingAddressshared.street.0'] .control input");
	public static final By CITY_VELOEUDE = By.cssSelector("div[name='billingAddressshared.city'] .control input");
	public static final By POSTCODE_VELOEUDE = By.cssSelector("div[name='billingAddressshared.postcode'] .control input");
	public static final By TO_UPDATE_BUTTON_VELOEUDE = By.cssSelector(".action.action-update");
	public static final By BILLING_ADDRESS_RADIO_BUTTON_VEDOEUDE = By.cssSelector("div.billing-address-same-as-shipping-block.field.choice > label");
	public static final By NEW_BILLING_ADDRESS = By.cssSelector(".billing-address-details");
	public static final By EDIT_BILLING_ADDRESS_BUTTON = By.cssSelector(".action.action-edit-address");
	private static final By SAVE_BILLING_ADDRESS_BUTTON=By.xpath("//div[@class='message info message__default-billing']//following::button[@class='action submit primary' and not(@title)][1]");
	private static final By SAVE_SHIPPING_ADDRESS_BUTTON=By.xpath("//div[@class='message info message__default-shipping']//following::button[@class='action submit primary' and not(@title)][1]");
	public static final By ADD_NEW_ADDRESS_CITY_CHECKOUT = By.cssSelector("div[name='shippingAddress.city']>div>input");
	public static final By ADD_NEW_ADDRESS_CITY_CHECKOUT_ERROR_MESSAGE = By.cssSelector("div[name='shippingAddress.city'] div.field-error span");


	// AVALANCHE
	private static final By AVALANCHE_EDIT_ADDRESS_SELECT_BILLING_ADDRESS_TICKBOX = By.cssSelector("input#primary_billing,input#default_address_change_both");
	private static final By AVALANCHE_EDIT_ADDRESS_SELECT_DELIVERY_ADDRESS_TICKBOX = By.cssSelector("input#primary_shipping,input#default_address_change_both");

	// inPage methods
	public boolean checkPageLoaded() {
		waitForExpectedElement(pageHeading, 30);
		return isPageLoadedWithPageTitleOf(UrlBuilder.getMessage("addressTitleText.key"));
	}

	public boolean isAddressFinderVisible(){
		return isElementDisplayedBySeconds(ADDADDRESSPOPUP_ADDRESSFINDER, 10);
	}

	public void inputElementsDisplayed() {
		assertTrue(waitForExpectedElement(firstNameInput).isDisplayed());
		assertTrue(waitForExpectedElement(surNameInput).isDisplayed());
		if (getWebDriver().getCurrentUrl().contains("/mx/es/"))
			LOG.info("Company field is not present");
		else
			assertTrue(waitForExpectedElement(companyInput).isDisplayed());
		assertTrue(waitForExpectedElement(TELEPHONE_INPUT).isDisplayed());
		assertTrue(waitForExpectedElement(STREET_ADDRESS_LINE_1_INPUT).isDisplayed());
		assertTrue(waitForExpectedElement(streetAddressLine2Input).isDisplayed());
		assertTrue(waitForExpectedElement(streetAddressLine3Input).isDisplayed());
		assertTrue(waitForExpectedElement(CITY_INPUT).isDisplayed());
		if (getWebDriver().getCurrentUrl().contains("/mx/es/"))
			assertTrue(waitForExpectedElement(MX_STATE_INPUT).isDisplayed());
		else
			assertTrue(waitForExpectedElement(StateInput).isDisplayed());
		assertTrue(waitForExpectedElement(POST_CODE_INPUT).isDisplayed());
		assertTrue(waitForExpectedElement(countrySelectDropDown).isDisplayed());
		assertTrue(waitForExpectedElement(useAsDefaultBillingAddressTickBox).isDisplayed());
		assertTrue(waitForExpectedElement(userAsDefaultShippingAddressTickBox).isDisplayed());
		assertTrue(waitForExpectedElement(saveAddressButton).isDisplayed());
		assertTrue(waitForExpectedElement(goBackButton).isDisplayed());
	}

	public void assertSaveAddressErrorMessageVuseCO() {
		assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(CELLULAR_ERROR).getText());
		assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(Street1Error).getText());
		assertEquals(UrlBuilder.getMessage("expectedRequiredSelectionError.key"), waitForExpectedElement(cityError).getText());
	}

	public void assertStreetAddressCityAndPostCodeErrorsDisplayed() {
		switch (UrlBuilder.getLocale()) {
			case "mx":
			case "vusemx":
				assertEquals(UrlBuilder.getMessage("uncheckTCsMessage.key"), waitForExpectedElement(Street1Error).getText());
				assertEquals(UrlBuilder.getMessage("errorTelephonerequired.key"), waitForExpectedElement(eleTelephoneError).getText());
				break;
			case "vuseco":
				try {
					assertSaveAddressErrorMessageVuseCO();
				} catch (Exception e) {
					pressSaveAddressButton();
					assertSaveAddressErrorMessageVuseCO();
				}
				break;
			case "lyftdk":
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(Street1Error).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(cityError).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(postCodeError).getText());
				break;
			case "vypeit":
			case "vuseit":
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(Street1Error).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(cityError).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(postCodeError).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(CELLULAR_ERROR).getText());
				break;
			case "uk":
			case "vuseuk":
			case "lyftse":
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessageForStreet.key"), waitForExpectedElement(PL_STREET_ERROR).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessageForCity.key"), waitForExpectedElement(PL_CITY_ERROR).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessageForPostcode.key"), waitForExpectedElement(PL_POST_CODE_ERROR).getText());
				break;
			case "pl":
				assertTrue(waitForExpectedElement(eleTelephoneError).getText().contains(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key")));
				break;
			case "vusefr":
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(ADDRESS_SEARCH_ERROR).getText());
				assertEquals(UrlBuilder.getMessage("errorTelephonerequired.key"), waitForExpectedElement(eleTelephoneError).getText());
				break;
			default:
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(Street1Error).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredSelectionError.key"), waitForExpectedElement(cityError).getText());
				assertEquals(UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key"), waitForExpectedElement(postCodeError).getText());
		}
	}

	public void pressPopUpFormSaveAddressButton() {
		pressSaveAddressButton();
	}

	public void pressSaveAddressButton() {
		By button;
		switch (UrlBuilder.getLocale()) {
			case "de":
				button = SAVE_ADDRESS_BUTTON_DE;
				break;
			case "kz":
			case "it":
			case "glode":
				button = SAVE_ADDRESS_BUTTON_KZ;
				break;
			case "nl":
				button = SAVE_ADDRESS_BUTTON_NL;
				break;
			case "lyftse":
				button = saveAddressButtonCO;
				break;
			case "lyftdk":
				button = SAVE_ADDRESS_BUTTON_LYFT_DK;
				break;
			case "vypeit":
			case "vuseit":
			case "pl":
				if (getWebDriver().findElements(DEFAULT_BOTH_ADDRESS_CHANGE_INPUT).size() > 0)
					clickByElementByQueryJSExecutor(DEFAULT_BOTH_ADDRESS_CHANGE_INPUT);
				button = SAVE_ADDRESS_BUTTON_IT;
				break;
			case "glojp":
				button = SAVE_ADDRESS_BUTTON_JP;
				break;
			case "velode":
			case "vuseuk":
				button = SAVE_ADDRESS_BUTTON_VELO2;
				break;
			case "velobe":
				button = SAVE_ADDRESS_BUTTON_VELOBE;
				break;
			case "mx":
			case "vusemx":
				if (getWebDriver().findElements(DEFAULT_BOTH_ADDRESS_CHANGE_INPUT).size() > 0)
					clickByElementByQueryJSExecutor(DEFAULT_BOTH_ADDRESS_CHANGE_INPUT);
				button = saveAddressButtonCO;
				break;
			case "vusefr":
				button = doesURLContain("/address/new") ? BUTTON_ADDRESS_SAVE_FR : saveAddressButton;
				break;
			default:
				button = saveAddressButton;
		}
		if ("glojp".equals(UrlBuilder.getLocale())) {
			try {
				waitForItemToBeClickableAndClick(button, 20);
				waitForExpectedElement(pageHeading, 30);
			} catch (Exception ex) {
				LOG.info("Failed to click on element using webdriver click, trying with js click.");
				clickByElement(button);
				waitForExpectedElement(pageHeading, 30);
			}
		} else {
			try {
				clickByElementByQueryJSExecutor(button);
				waitForExpectedElement(pageHeading, 20);
			} catch (Exception ex) {
				LOG.info("Failed to click on element using webdriver click, trying with js click.");
				clickByElementByQueryJSExecutor(FOOTER_SUBMIT_BUTTON);
				waitForExpectedElement(pageHeading, 20);
			}
		}
	}

	public void clickOnBackButton() {
		clickByElementByQueryJSExecutor(goBackButton);
	}

	public void pressGoBackButton() {
		waitForExpectedElement(pageHeading, 30);
		if (getWebDriver().getCurrentUrl().contains("de/de")) {
			clickByElementByQueryJSExecutor(GOBACK_BUTTON_DE);
		}
		else if (getWebDriver().getCurrentUrl().contains("fr/fr") || getWebDriver().getCurrentUrl().contains("mx/es")  || getWebDriver().getCurrentUrl().contains("it/it") || getWebDriver().getCurrentUrl().contains("co/es")   || getWebDriver().getCurrentUrl().contains("za/en") ){
			clickByElementByQueryJSExecutor(CLOSE_ADDRESS_PANEL_FR);
		} else if (getWebDriver().getCurrentUrl().contains("mx/es") || getWebDriver().getCurrentUrl().contains("pl/pl")) {
			try {
				waitForExpectedElement(goBackButton).click();
			} catch (Exception ex) {
				LOG.info("Failed to click, trying to click using javascript.");
				clickByElementByQueryJSExecutor(goBackButton);
			}
		} else {
			try {
				waitForExpectedElement(goBackButton).click();
			} catch (Exception ex) {
				LOG.info("Failed to click, trying to click using javascript.");
				clickByElementByQueryJSExecutor(By.cssSelector("footer > button.action.back"));
			}
		}
	}

	public void populateAllAddressInputFieldsIncludingFirstAndLastName() {
		if (!UrlBuilder.getLocale().equals("vypeit") && !UrlBuilder.getLocale().equals("pl") && !UrlBuilder.getLocale().equals("vuseit")) {
			waitClearAndEnterText(firstNameAddress, firstNameData);
			waitClearAndEnterText(surNameAddress, lastNameData);
		}

		if ("pl".equals(UrlBuilder.getLocale())) {
			waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "poland");
			waitClearAndEnterText(streetAddressLine2Input, "1polandfs");
			waitClearAndEnterText(CITY_INPUT, "poland");
			waitClearAndEnterText(TELEPHONE_INPUT, "223457789");
			waitClearAndEnterText(POST_CODE_INPUT, "22-444");
			return;
		}
		try {
			if (!UrlBuilder.getLocale().equals("vypeit") && !UrlBuilder.getLocale().equals("vuseit")) {
				waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberData);
				waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "12 the cloisters");
				waitClearAndEnterText(CITY_INPUT, "Watford");
			}
			switch (UrlBuilder.getLocale()) {
				case "vypeit":
				case "vuseit":
					if (isElementPresentByby(firstNameSubscrAddress)) {
						waitClearAndEnterText(firstNameSubscrAddress, firstNameData);
						waitClearAndEnterText(lastNameSubscrAddress, lastNameData);
						waitClearAndEnterText(streetSubscrAddress, "12 the cloisters");
						waitClearAndEnterText(citySubscrAddress, "Watford");
						waitClearAndEnterText(postcodeSubscrAddress, "WD3 1HF");
					} else {
						waitClearAndEnterText(firstNameAddress, firstNameData);
						waitClearAndEnterText(surNameAddress, lastNameData);
						waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberData);
						waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "12 the cloisters");
						waitClearAndEnterText(CITY_INPUT, "Watford");
						waitClearAndEnterText(POST_CODE_INPUT, "WD3 1HF");

					}
					if (isElementPresentByby(firstNameSubscrAddress)) {
						waitClearAndEnterText(phoneSubscrAddress, phoneNumberVypeIT);
						if (isElementPresentByby(regionSubscrAddress)) {
							List<WebElement> states = getWebDriver().findElements(regionSubscrAddress);
							if (states.size() > 0)
								selectOptionFromDropDownByValue(
										states.get(ThreadLocalRandom.current().nextInt(states.size()) % states.size())
												.getAttribute("value"),
										regionSelectorSubscrAddress
								);
						}
					}
					break;
				case "it":
					waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberVypeIT);
					selectValueFromDropDownByby(UrlBuilder.getMessage("stateRegion.key"), MX_STATE_INPUT);
					break;
				case "mx":
				case "vusemx":
					enterDataAndWait(TXT_OUTDOOR_NUMBER, UrlBuilder.getMessage("outdoorNumber.key"));
					enterDataAndWait(TELEPHONE_INPUT, phoneNumberData);
					selectValueFromDropDownByby(UrlBuilder.getMessage("stateRegion.key"), MX_STATE_INPUT);
					waitClearAndEnterText(POST_CODE_INPUT, "00810");
					break;
				case "lyftse":
					waitClearAndEnterText(POST_CODE_INPUT, "111 22");
					break;
				case "lyftdk":
					waitClearAndEnterText(POST_CODE_INPUT, "1682");
					break;
				default:
					waitClearAndEnterText(POST_CODE_INPUT, "WD3 1HF");
			}
		} catch (Exception ex) {
			LOG.info("Failed to populate data in Address fields due to error: " + ex.getMessage());
		}
	}

	public void populateAllAddressFieldsExceptForFirstAndLastName() {
		try {
			if (UrlBuilder.getLocale().equalsIgnoreCase("pl")) {
				waitForExpectedElement(ENTER_ADDRESS_MANUALLY, 5);
				if (getWebDriver().findElements(ENTER_ADDRESS_MANUALLY).size() > 0)
					clickByElementByQueryJSExecutor(ENTER_ADDRESS_MANUALLY);
			} else waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "12 the cloisters");
		} catch (Exception e) {

		}
		switch (UrlBuilder.getLocale()) {
			case "pl":
				waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "poland");
				waitClearAndEnterText(streetAddressLine2Input, "1 polandfs");
				waitClearAndEnterText(CITY_INPUT, "poland");
				waitClearAndEnterText(TELEPHONE_INPUT, "223457789");
				waitClearAndEnterText(POST_CODE_INPUT, "22-444");
				break;
		case "glode":
			waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberCO);
			if(getWebDriver().findElements(MANUAL_ADDRESS_ENTRY_ADD_ADDRESS).size()>0)
				clickUsingJS(MANUAL_ADDRESS_ENTRY_ADD_ADDRESS);
			waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, UrlBuilder.getMessage("streetAddressLine.key"));
			waitClearAndEnterText(CITY_INPUT, "Berlin");
			waitForExpectedElement(StateInput).click();
			waitForAjaxElementNotToBePresent(getWebDriver(),5);
			selectValueFromDropDownByWebElement(waitForExpectedElement(StateInput),"Berlin");
			waitClearAndEnterText(POST_CODE_INPUT, "10117");
			break;
			case "it":
				if(getWebDriver().findElements(TOGGLE_FULL_ADDRESS_GLOIT).size()>0)
					waitForExpectedElement(TOGGLE_FULL_ADDRESS_GLOIT).click();
				waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberCO);
				enterText(registrationPage.city,registrationPage.townOrCityData.toLowerCase());
				enterText(registrationPage.postCode,registrationPage.postCodeData);
				break;
			case "mx":
			case "vusemx":
			waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "12 the cloisters");
			waitClearAndEnterText(CITY_INPUT, "Jalisco");
			waitClearAndEnterText(TXT_OUTDOOR_NUMBER,UrlBuilder.getMessage("outdoorNumber.key"));
			waitClearAndEnterText(TELEPHONE_INPUT, "1234567890");
			waitForExpectedElement(StateInput).click();
			waitForAjaxElementNotToBePresent(getWebDriver(),5);
			selectValueFromDropDownByWebElement(waitForExpectedElement(StateInput),UrlBuilder.getMessage("stateRegion.key"));
			waitClearAndEnterText(POST_CODE_INPUT, "22301");
			waitClearAndEnterText(COLONIA_MX,"mexico");
			break;
		case "kz":
			waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberKZ);
			waitClearAndEnterText(CITY_INPUT, "Aksu");
			waitClearAndEnterText(POST_CODE_INPUT, "160800");
			break;
			case "vypeit":
			case "vuseit":
			waitClearAndEnterText(TELEPHONE_INPUT, PHONENUMBER_VYPEIT);
			clickByElementByQueryJSExecutor(ENTER_ADDRESS_MANUALLY);
			waitClearAndEnterText(CITY_INPUT, "Bagni Di Lucca Ponte");
			waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT,"Street 1");
			waitClearAndEnterText(POST_CODE_INPUT, "55021");
			break;
		case "lyftse":
			waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberData);
			waitClearAndEnterText(CITY_INPUT, "Watford");
			waitClearAndEnterText(POST_CODE_INPUT, "111 22");
			break;
		case "lyftdk":
			waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberData);
			waitClearAndEnterText(CITY_INPUT, "Watford");
			waitClearAndEnterText(POST_CODE_INPUT, "1682");
			break;
		case "fr":
		case "vusefr":
			clickUsingJS(TOGGLE_FULL_ADDRESS);
			try {
				waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "12 the cloisters");
			}catch(Exception e){
				clickUsingJS(TOGGLE_FULL_ADDRESS);
				waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "12 the cloisters");
			}
			waitClearAndEnterText(CITY_INPUT, "Watford");
			waitClearAndEnterText(POST_CODE_INPUT, "WD3 1HL");
			waitClearAndEnterText(TELEPHONE_INPUT, "67723456789");
			break;
		case "vuseco":
			waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberCO);
			waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "Främsteby Karlsborg 64");
			waitClearAndEnterText(streetAddressLine2Input, "Främsteby");
			waitClearAndEnterText(streetAddressLine3Input, "Karlsborg 64");
			selectValueFromDropDownByby(UrlBuilder.getMessage("stateRegion.key"), CO_STATE_DROP_DOWN);
			break;
		case "uk":
			case "vuseuk":
				//waitClearAndEnterText(TELEPHONE_INPUT, phoneNumberData);
				waitForExpectedElement(TOGGLE_FULL_ADDRESS).click();
				waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "12 the cloisters");
				waitClearAndEnterText(CITY_INPUT, "Watford");
				waitClearAndEnterText(POST_CODE_INPUT, "WD3 1HL");
				break;
			case "vusede":
				registrationPage.populateAddressFields();
				break;
			case "velode":
				waitForAjaxElementNotToBePresent(getWebDriver(), 20);
				waitForExpectedElement(BILLING_ADDRESS_RADIO_BUTTON_VEDOEUDE, 10);
				getWebDriver().findElement(BILLING_ADDRESS_RADIO_BUTTON_VEDOEUDE).click();
				waitForAjaxElementNotToBePresent(getWebDriver(), 5);
				waitForExpectedElement(BILLING_ADDRESS_DROPDOWN, 5);
				getWebDriver().findElement(BILLING_ADDRESS_DROPDOWN).click();
				getWebDriver().findElement(NEW_ADDRESS_OPTION).click();
				registrationPage.clickOnManualAddressEntryLink();
				SetAddressManuallyOnPaymentPage(UrlBuilder.getMessage("streetAndHouseNumber.key"), UrlBuilder.getMessage("city.key"), UrlBuilder.getMessage("postal.key"));
				break;
			case "glojp":
				waitClearAndEnterText(POST_CODE1_JP,UrlBuilder.getMessage("postalcode1.key"));
				waitClearAndEnterText(POST_CODE2_JP,UrlBuilder.getMessage("postalcode2.key"));
				waitClearAndEnterText(ADDRESS2_JP,UrlBuilder.getMessage("address2.key"));
				waitClearAndEnterText(ADDRESS3_JP,BuildingNumber);
				break;
				default:
				waitClearAndEnterText(CITY_INPUT, "Aksu");
				waitClearAndEnterText(POST_CODE_INPUT, "160800");
		}
	}

	public void tickDefaultBillingAddress() {
		switch (UrlBuilder.getLocale()) {
			case "mx":
			case "vusemx":
			case "uk":
			case "vuseuk":
			case "vusede":
			case "fr":
			case "vusefr":
				clickByElementByQueryJSExecutor(USER_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_UK);
				break;
			case "vuseco":
				clickByElementByQueryJSExecutor(useAsDefaultBillingAddressTickBoxVuseCO);
				break;
			case "pl":
				clickByElementByQueryJSExecutor(USE_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_PL);
				break;
			case "kz":
				clickByElementByQueryJSExecutor(USE_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_KZ);
				break;
			case "vypeit":
			case "vuseit":
				clickByElementByQueryJSExecutor(USE_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_VYPEIT);
				break;
			case "glode":
				clickByElementByQueryJSExecutor(userAsDefaultShippingAddressTickBoxDE);
				break;
			case "velode":
				clickByElementByQueryJSExecutor(USER_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_VELO);
				break;
			case "it":
				break;
			case "lyftse":
				clickByElementByQueryJSExecutor(USER_AS_DEFAULT_BILLING_ADDRESS_TICK_BOX_LYFTSE);
				break;
			default:
				clickByElementByQueryJSExecutor(useAsDefaultBillingAddressTickBox);
		}
	}

	public void tickDefaultShippingAddress() {
		switch (UrlBuilder.getLocale()) {
			case "uk":
			case "vuseuk":
			case "vusede":
			case "fr":
			case "vusefr":
			case "mx":
			case "vusemx":
			case "it":
			case "vuseza":
				clickByElementByQueryJSExecutor(USER_AS_DEFAULT_SHIPPING_ADDRESS_TICK_BOX_UK);
				break;
			case "de":
			case "pl":
			case "kz":
			case "glode":
				clickByElementByQueryJSExecutor(userAsDefaultShippingAddressTickBoxDE);
				break;
			case "vuseco":
				clickByElementByQueryJSExecutor(userAsDefaultShippingAddressTickBoxVuseCO);
				break;
			case "vypeit":
			case "vuseit":
				clickByElementByQueryJSExecutor(USE_AS_DEFAULT_SHIPPING_ADDRESS_TICK_BOX_VYPEIT);
				break;
			case "lyftse":
				try {
				clickByElementByQueryJSExecutor(USER_AS_DEFAULT_SHIPPING_ADDRESS_TICK_BOX_LYFTSE);
			} catch (TimeoutException e) {
				clickByElementByQueryJSExecutor(USER_AS_DEFAULT_SHIPPING_AND_BILLING_ADDRESS_TICK_BOX_LYFTSE);
			}
				break;
			default:
				clickByElementByQueryJSExecutor(userAsDefaultShippingAddressTickBox);
		}
	}

	public void clearDataFromAllEditAddressInputFields() {
		try {
			switch (UrlBuilder.getLocale()) {
				case "nl":
					waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "");
					waitClearAndEnterText(CITY_INPUT, "");
					waitClearAndEnterText(POST_CODE_INPUT, "");
					break;
				case "vypeit":
				case "vuseit":
				case "glode":
					clickByElementByQueryJSExecutor(EDIT_ADD_ADDRESS_MANUALLY_VYPEIT);
					waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "");
					waitClearAndEnterText(CITY_INPUT, "");
					waitClearAndEnterText(POST_CODE_INPUT, "");
					break;
				case "vusefr":
					waitClearAndEnterText(EDIT_ADDRESS_TELEPHONE, "");
					break;
				default:
					waitClearAndEnterText(txtTelephoneEditAddress, "");
					waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "");
					waitClearAndEnterText(CITY_INPUT, "");
					waitClearAndEnterText(POST_CODE_INPUT, "");
			}
		} catch (Exception ex) {
			LOG.info("Failed to clear data in Edit Address fields due to error: " + ex.getMessage());
		}
	}

	public void assertErrorValidationMessageForEditAddressInputFields(String strErrorValidationMessage) {
		try {
			assertEquals(UrlBuilder.getMessage(strErrorValidationMessage), waitForExpectedElement(eleTelephoneError).getText());
			assertEquals(UrlBuilder.getMessage(strErrorValidationMessage), waitForExpectedElement(Street1Error).getText());
			assertEquals(UrlBuilder.getMessage(strErrorValidationMessage), waitForExpectedElement(cityError).getText());
			assertEquals(UrlBuilder.getMessage(strErrorValidationMessage), waitForExpectedElement(postCodeError).getText());

		} catch (Exception ex) {
			LOG.info("Failed to clear data in Edit Address fields due to error: " + ex.getMessage());
		}
	}

	public void assertPrePopulatedFirstNameWithExistingAccount() {
		if (doesURLContain("checkout")) {
			assertTrue(waitForExpectedElement(accountDashboardPage.CHECKOUT_FIRST_NAME_READ_ONLY).getAttribute("value").equalsIgnoreCase(System.getProperty("UserFirstName.key")));
		} else if (UrlBuilder.getLocale().equals("uk")) {
			assertEquals(UrlBuilder.getMessage("firstNameEdit.key"), waitForExpectedElement(firstNameAddress).getAttribute("value"));
		} else {
			assertTrue(waitForExpectedElement(firstNameAddress).getAttribute("value").equalsIgnoreCase(System.getProperty("UserFirstName.key")));
		}
	}

	public void assertPrePopulatedLastNameWithExistingAccount() {
		if (doesURLContain("checkout")) {
			assertTrue(waitForExpectedElement(accountDashboardPage.CHECKOUT_LAST_NAME_READ_ONLY).getAttribute("value").equalsIgnoreCase(System.getProperty("UserLastName.key")));
		} else if (UrlBuilder.getLocale().equals("uk")) {
			assertEquals(UrlBuilder.getMessage("secondNameEdit.key"), waitForExpectedElement(surNameAddress).getAttribute("value"));
		} else {
			assertTrue(waitForExpectedElement(surNameAddress).getAttribute("value").equalsIgnoreCase(System.getProperty("UserLastName.key")));
		}
	}

	public void userClicksOnEditShippingAddressLink() {
		switch (UrlBuilder.getLocale()) {
			case "uk":
			case "ie":
			case "fr":
			case "dk":
			case "de":
			case "vusede":
			case "vuseuk":
			case "vusefr":
			case "mx":
			case "vusemx":
			case "vypeit":
			case "vuseit":
			case "vuseco":
				clickByElementByQueryJSExecutor(EDIT_SHIPPING_ADDRESS_LINK);
				break;
			case "pl":
				if(getWebDriver().findElements(EDIT_ADDRESS_LINK_MY_ACCOUNT).size()>0)
					clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_MY_ACCOUNT);
				waitAndClickByElementByJSExecutor(EDIT_SHIPPING_ADDRESS_LINK,10);
				break;
			case "veloza":
			case "velobe":
				String currentAddress = waitForExpectedElement(CURRENT_SHIPPING_ADDRESS_DETAILS_VELOBE).getText();
				scenarioContext.setContext(CURRENT_ADDRESS, currentAddress);
				clickByElementByQueryJSExecutor(EDIT_SHIPPING_ADDRESS_LINK);
				break;
			case "nl":
			case "lyftse":
			case "lyftdk":
			case "it":
			case "glode":
			case "kz":
				try {
					clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(("editShippingAddressLink.key"))));
				} catch (Exception e) {
					clickByElementByQueryJSExecutor(EDIT_SHIPPING_ADDRESS_GLODE);
				}
				break;
			case "velode":
				waitForExpectedElement(EDIT_SHIPPING_ADDRESS_LINK_KZ, 10);
				clickByElementByQueryJSExecutor(EDIT_SHIPPING_ADDRESS_LINK_KZ);
				break;
		}
	}

	public void userClicksOnTheAddressBookLink() {
		switch ( UrlBuilder.getLocale()) {
		case "kz":
			hoverOnElement(MY_ACCOUNT_LINKS);
			waitForExpectedElement(ADDRESS_DROPDOWN_LINK_KZ,10);
			clickByElementByQueryJSExecutor(ADDRESS_DROPDOWN_LINK_KZ);
			break;
		case "it":
		case "glode":
			waitForExpectedElement(By.linkText(UrlBuilder.getMessage("yourAddressText.key")),10);
			clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("yourAddressText.key")));
		}
	}

	public void userClicksOnEditBillingAddressLink() {
		switch (UrlBuilder.getLocale()) {
			case "uk":
			case "vusede":
			case "fr":
			case "vuseuk":
			case "vusefr":
			case "glode":
			case "mx":
			case "vusemx":
			case "vypeit":
			case "vuseco":
				waitForExpectedElement(EDIT_BILLING_BUTTON).click();
				break;
			case "vuseit":
				clickByElementByQueryJSExecutor(EDIT_BILLING_BUTTON);
				break;
			case "veloza":
			case "velopl":
			case "velobe":
				String currentAddress = waitForExpectedElement(CURRENT_BILLING_ADDRESS_DETAILS_VELOBE).getText();
				scenarioContext.setContext(CURRENT_ADDRESS, currentAddress);
				clickByElementByQueryJSExecutor(EDIT_BILLING_BUTTON);
				break;
			case "velode":
				waitForExpectedElement(BILLING_EDIT_LINK_VELO).click();
			break;
			case "kz":
			case "vuseza":
				clickByElementByQueryJSExecutor(EDIT_BILLING_BUTTON);
				break;
			case "pl":
				if(getWebDriver().findElements(EDIT_ADDRESS_LINK_MY_ACCOUNT).size()>0)
					clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_MY_ACCOUNT);
				clickByElementByQueryJSExecutor(EDIT_BILLING_BUTTON);
				break;
			default:
				waitForExpectedElement(By.cssSelector("div.block-content div.box.box-address-billing:nth-child(1) > div.box-actions > a > span:nth-child(1)"), 10);
				clickByElementByQueryJSExecutor(By.cssSelector("div.block-content div.box.box-address-billing:nth-child(1) > div.box-actions > a > span:nth-child(1)"));
		}
	}

	public void populateAllAddressFieldsForAnExistingUser() {
		switch (UrlBuilder.getLocale()) {
			case "glode":
			case "de":
				waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, "Ansgarstr. 4");
				waitClearAndEnterText(CITY_INPUT, "Wallenhorst");
				waitClearAndEnterText(POST_CODE_INPUT, POSTCODE_GLO_DE);
				String PostCode = waitForExpectedElement(POST_CODE_INPUT).getAttribute("value");
				String StreetAddress = waitForExpectedElement(STREET_ADDRESS_LINE_1_INPUT).getAttribute("value");
				String City = waitForExpectedElement(CITY_INPUT).getAttribute("value");
				System.setProperty("UserPostCode.key", PostCode);
				System.setProperty("UserStreetAddress.key", StreetAddress);
				System.setProperty("UserCity.key", City);
				break;
			default:
		}
	}

	public void clickOnCancelButtonFromEditAddressPopUp() {
		switch (UrlBuilder.getLocale()) {
			case "nl":
				clickByElementByQueryJSExecutor(CANCEL_EDIT_ADDRESS);
				break;
			case "mx":
			case "vusemx":
			case "vuseco":
			case "glode":
				try {
					clickByElementByQueryJSExecutor(CANCEL_EDIT_ADDRESS_MX);
				} catch (Exception e) {
					refreshBrowser();
				}
				break;
			case "vusede":
			case "fr":
			case "vusefr":
			case "vypeit":
			case "vuseit":
			case "pl":
				clickByElementByQueryJSExecutor(CANCEL_EDIT_ADDRESS_DE);
				break;
			case "vuseuk":
				clickByElementByQueryJSExecutor(CANCEL_EDIT_ADDRESS_UK);
				break;
			case "it":
			case "lyftse":
				clickByElementByQueryJSExecutor(goBackButton);
				break;
			default:
				clickByElementByQueryJSExecutor(GOBACK_BUTTON_DE);
		}
	}

	public void userClicksOnEditAddressLink() {
		switch (UrlBuilder.getLocale()) {
			case "vusede":
			case "pl":
				clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_VUSEDE);
				break;
			case "vuseuk":
				if(getWebDriver().findElements(EDIT_ADDRESS_LINK_MY_ACCOUNT).size()>0) {
					clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_MY_ACCOUNT);
					clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_FR);
				} else
					clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_FR);
				break;
			case "fr":
			case "vusefr":
			case "vuseza":
				clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_FR);
				break;
			case "vypeit":
			case "vuseit":
				clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_FR);
				waitForExpectedElement(STREET_ADDRESS_LINE_1_INPUT,10);
				break;
			case "mx":
			case "vusemx":
			case "vuseco":
			case "glode":
				if (getWebDriver().findElements(EDIT_ADDRESS_LINK_MY_ACCOUNT).size() > 0) {
					clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_MY_ACCOUNT);
					clickByElementByQueryJSExecutor(EDIT_BILLING_BUTTON);
				} else
					clickByElementByQueryJSExecutor(EDIT_BILLING_BUTTON);
				break;
			case "glojp":
				clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_JP);
				break;
			case "velode":
				waitForAjaxElementNotToBePresent(getWebDriver(),10);
				waitForExpectedElement(EDIT_ADDRESS_LINK_VELO,10);
				clickByElementByQueryJSExecutor(EDIT_ADDRESS_LINK_VELO);
				break;
			case "it":
				clickByElementByQueryJSExecutor(CHANGE_BILLING_ADDRESS);
				break;
			default:
				waitForExpectedElement(By.linkText(UrlBuilder.getMessage("editAddressText.key")), 10);
				clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("editAddressText.key")));
		}
	}

	public void populateTaxIDFieldWith(String input) {
		String actualInput = input.contains("length-") ? input.substring(input.indexOf("-") + 1) : input;
		String keyPattern = "length-";
		Matcher matcher = Pattern.compile(keyPattern + "\\d+").matcher(actualInput);
		if (matcher.find()) {
			int requiredLength = Integer.parseInt(actualInput.substring(keyPattern.length()));
			String taxId = RandomStringUtils.randomNumeric(requiredLength);
			try {
				waitForExpectedElement(VAT_ID_TXT_BOX).sendKeys(taxId);
			} catch (Exception ex) {
				waitForExpectedElement(VAT_ID_BY_NAME).sendKeys(taxId);
			}
		} else {
			try {
				waitForExpectedElement(VAT_ID_TXT_BOX).clear();
				waitForExpectedElement(VAT_ID_TXT_BOX).sendKeys(actualInput);
			} catch (Exception ex) {
				waitForExpectedElement(VAT_ID_BY_NAME).clear();
				waitForExpectedElement(VAT_ID_BY_NAME).sendKeys(actualInput);
			}
		}
	}

	public void addAddressManuallyOnDeliveryAddressPage(String streetAndHouseNumber, String city, String postal, String telephone) {
		registrationPage.addAddressManuallyOnAddressPage(streetAndHouseNumber, city, postal, telephone);
	}

	public void EnterAddressManuallyOnCheckoutPage(String streetAndHouseNumber, String city, String postal, String telephone) {
		registrationPage.EnterAddressManually(streetAndHouseNumber, city, postal, telephone);
	}

	public void validateNewlyAddedBillingAddress(String streetAndHouseNumber, String city, String postal) {

		if ("velode".equals(UrlBuilder.getLocale())) {
			assertTrue(waitForExpectedElement(NEW_BILLING_ADDRESS).getAttribute("innerText").contains(streetAndHouseNumber));
			assertTrue(waitForExpectedElement(NEW_BILLING_ADDRESS).getAttribute("innerText").contains(city));
			assertTrue(waitForExpectedElement(NEW_BILLING_ADDRESS).getAttribute("innerText").contains(postal));
		}
	}

	public void EditBillingAddress(String streetAndHouseNumber, String city, String postal) {
		if ("velode".equals(UrlBuilder.getLocale())) {
			waitForAjaxElementNotToBePresent(getWebDriver(), 10);
			waitForExpectedElement(EDIT_BILLING_ADDRESS_BUTTON, 10);
			clickByElementByQueryJSExecutor(EDIT_BILLING_ADDRESS_BUTTON);
			SetAddressManuallyOnPaymentPage(streetAndHouseNumber, city, postal);
		}
	}

	public void SetAddressManuallyOnPaymentPage(String streetAndHouseNumber, String city, String postal) {
		waitClearAndEnterText(STREET_AND_HOUSE_NUMBER, streetAndHouseNumber);
		waitClearAndEnterText(CITY_VELOEUDE, city);
		waitClearAndEnterText(POSTCODE_VELOEUDE, postal);
		clickByElementByQueryJSExecutor(TO_UPDATE_BUTTON_VELOEUDE);
		waitForAjaxElementNotToBePresent(getWebDriver(), 10);
	}

	public void saveAddress(String addressType) {
		switch (addressType.toLowerCase()) {
			case "billing":
				enterText(STREET_ADDRESS_VELOZA, "12 Albert Street");
				if (UrlBuilder.getLocale().equalsIgnoreCase("veloza")) {
					selectValueFromDropDownByby(UrlBuilder.getMessage("Province.key"), province);
				}
				clickUsingJS(AVALANCHE_EDIT_ADDRESS_SELECT_DELIVERY_ADDRESS_TICKBOX);
				break;
			case "delivery":
			case "shipping":
				clickUsingJS(AVALANCHE_EDIT_ADDRESS_SELECT_BILLING_ADDRESS_TICKBOX);
				break;
			default:
				clickUsingJS(AVALANCHE_EDIT_ADDRESS_SELECT_BILLING_ADDRESS_TICKBOX);
				clickUsingJS(AVALANCHE_EDIT_ADDRESS_SELECT_DELIVERY_ADDRESS_TICKBOX);
		}
			clickUsingJS(SAVE_ADDRESS_BUTTON_VELOBE);
	}
	public void clickOnSaveAddressButtonToAddAndEditAddress() {
		if (getWebDriver().findElements(SAVE_BILLING_ADDRESS_BUTTON).size()>0)
			clickByElementByQueryJSExecutor(SAVE_BILLING_ADDRESS_BUTTON);
		else if(getWebDriver().findElements(SAVE_SHIPPING_ADDRESS_BUTTON).size()>0)
			clickByElementByQueryJSExecutor(SAVE_SHIPPING_ADDRESS_BUTTON);
		else if(getWebDriver().findElements(FOOTER_SUBMIT_BUTTON).size()>0)
			clickByElementByQueryJSExecutor(FOOTER_SUBMIT_BUTTON);
	}

	public void userClicksChangeDeliveryAddressLink() {
		waitAndClickByElementByJSExecutor(CHANGE_DELIVERY_ADDRESS, 4);
	}

	public void userClicksChangeBillingAddressLink() {
		waitAndClickByElementByJSExecutor(CHANGE_BILLING_ADDRESS, 4);
	}

	public void setAddressInformation(AddressModel addressInformation) {
		if (addressInformation == null) {
			throw new IllegalArgumentException("Address information empty.");
		}
		String[] addressArr = addressInformation.getAddressLine().split(",");
		String[] addressTypeArr = addressInformation.getAddressType().split(",");
		String streetStr = "#street_";
		if (addressInformation.getCompany() != null) {
			waitClearAndEnterText(companyInput, addressInformation.getCompany());
		}
		for (int i = 0; i < addressArr.length; i++) {
			String streetFullStr = streetStr + (i + 1);
			waitClearAndEnterText(By.cssSelector(streetFullStr), addressArr[i]);
		}
		waitClearAndEnterText(CITY_INPUT, addressInformation.getTown());
		waitClearAndEnterText(POST_CODE_INPUT, addressInformation.getPostCode());
		Select countySelect = new Select(waitForExpectedElement(COUNTRY_SELECT_ON_ADDRESS));
		countySelect.selectByVisibleText(addressInformation.getCountry());
		for (String aType : addressTypeArr) {
			if ("billing".equalsIgnoreCase(aType)&& isElementPresent(USE_AS_DEFAULT_BILLING_ADDRESS_LABLE)) {
				waitForExpectedElement(USE_AS_DEFAULT_BILLING_ADDRESS_LABLE).click();
			}
			if ("shipping".equalsIgnoreCase(aType)&& isElementPresent(USE_AS_DEFAULT_SHIPPING_ADDRESS_LABEL)) {
				waitForExpectedElement(USE_AS_DEFAULT_SHIPPING_ADDRESS_LABEL).click();
			}
			if(isElementPresent(USE_AS_DEFAULT_SHIPPING_ADDRESS_CHANGES)){
                waitForExpectedElement(USE_AS_DEFAULT_SHIPPING_ADDRESS_CHANGES).click();
			}
		}
	}
	public boolean isEditSavedElementPresent(){
		return isElementPresent(EDIT_SAVE_MESSAGE);
	}
	public void userClickEditBillingAddress(){
		clickByElementByQueryJSExecutor(EDIT_BILLING_ADRESS_BUTTON);
	}

	public boolean isAddAddressPopupPresent() {
		switch (UrlBuilder.getLocale()) {
			case "vuseit":
				return isElementDisplayedBySeconds(ADDADDRESSPOPUP_SAVEADDRESS_IT, 10);
			case "lyftse":
				return isElementDisplayedBySeconds(ADDADDRESSPOPUP_SAVEADDRESS_LYFTSE, 10);
			default:
				return isElementDisplayedBySeconds(ADDADDRESSPOPUP_SAVEADDRESS, 10);
		}
	}

	public void populateAddressFieldsWithoutPhoneNumber() {
		switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
			case GLODE:
				clearFieldUsingControlKeys(TELEPHONE_INPUT);
				if (getWebDriver().findElements(MANUAL_ADDRESS_ENTRY_ADD_ADDRESS).size() > 0)
					clickUsingJS(MANUAL_ADDRESS_ENTRY_ADD_ADDRESS);
				waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT, UrlBuilder.getMessage("streetAddressLine.key"));
				waitClearAndEnterText(CITY_INPUT, "Berlin");
				waitClearAndEnterText(StateInput, "Berlin");
				waitClearAndEnterText(POST_CODE_INPUT, "10117");
				break;
			case VUSEDE:
				clearFieldUsingControlKeys(registrationPage.phoneNumber);
				enterText(registrationPage.ADDRESS_SEARCH_VUSEDE,  UrlBuilder.getMessage("yourAddressContent.key"));
				waitForExpectedElement(registrationPage.ADDRESS_SEARCH_RESULT).click();
				if(isElementDisplayedBySeconds(registrationPage.ADDRESS_SEARCH_RESULT,2)){
					waitForExpectedElement(registrationPage.ADDRESS_SEARCH_RESULT).click();
				}
				break;
			default:
		}
	}

	public boolean isAddressDuplicateOrDisappear(By addressLocator, String addressContent){
		List<WebElement> addressContentEle=getWebDriver().findElements(addressLocator);
		int i=0;
		for(WebElement content:addressContentEle){
			if(addressContent.equalsIgnoreCase(content.getText().trim())){
				i++;
			}
		}
		return i==0||i>1;
	}

	public boolean isAddressDuplicateOrDisappear(String addressContent) {
		return isAddressDuplicateOrDisappear(ADDRESS_DETAIL_CONTENT, addressContent);
	}

	public void populateAddressDetailsUsingLoqateIncludingPhoneNumberAndSave() {
		if (doesURLContain("checkout")) {
			waitClearAndEnterText(PHONE_NUMBER_FIELD_CHECKOUT_ZA, UrlBuilder.getMessage("validPhoneNumber.key"));
			waitClearAndEnterText(ADDRESS_QUICK_FINDER_PAYMENT_SECTION, "1 Handel Street, Pioneer Park, ABAQULUSI, 3100");
			loqateAddressLookUpPage.clickFirstOptionIfPresent("1 Handel Street");
			waitAndClickByElementByJSExecutor(BUTTON_USE_THIS_ADDRESS_PAYMENT_SECTION, 3);
		} else {
			waitAndClickByElementByJSExecutor(TOGGLE_FULL_ADDRESS,2);
			waitClearAndEnterText(TELEPHONE_INPUT,UrlBuilder.getMessage("validPhoneNumber.key"));
			waitClearAndEnterText(STREET_ADDRESS_LINE_1_INPUT,"1 Handel Street");
			waitClearAndEnterText(streetAddressLine2Input,"Pioneer Park");
			waitClearAndEnterText(CITY_INPUT,"ABAQULUSI");
			waitClearAndEnterText(POST_CODE_INPUT,"3100");
			selectValueFromDropDownByby("KwaZulu-Natal", CO_STATE_DROP_DOWN);
		}
	}

	public void userEntersCharactersLimitInCityField(String strLimit,String strErrorMessage) {
		switch (strLimit) {
			case "1":
				if (doesURLContain("checkout")) {
					waitClearAndEnterText(ADD_NEW_ADDRESS_CITY_CHECKOUT, random(Integer.valueOf(strLimit), ALPHABETS));
					clickByElementByQueryJSExecutor(PaymentPage.USE_THIS_ADDRESS_BUTTON);
					assertTrue(waitForExpectedElement(ADD_NEW_ADDRESS_CITY_CHECKOUT_ERROR_MESSAGE).getText().contains(UrlBuilder.getMessage(strErrorMessage)));
				} else {
					waitClearAndEnterText(CITY_INPUT, random(Integer.valueOf(strLimit), ALPHABETS));
					clickByElementByQueryJSExecutor(GloItCheckoutPage.USE_THIS_ADDRESS_BUTTON_PL);
					assertTrue(waitForExpectedElement(cityError).getText().contains(UrlBuilder.getMessage(strErrorMessage)));
				}
				break;
			default:
				if (doesURLContain("checkout")) {
					waitClearAndEnterText(ADD_NEW_ADDRESS_CITY_CHECKOUT, random(Integer.valueOf(strLimit), ALPHABETS));
					clickByElementByQueryJSExecutor(PaymentPage.USE_THIS_ADDRESS_BUTTON);
					assertTrue(getWebDriver().findElements(ADD_NEW_ADDRESS_CITY_CHECKOUT_ERROR_MESSAGE).size() == 0);
				} else {
					waitClearAndEnterText(CITY_INPUT, random(Integer.valueOf(strLimit), ALPHABETS));
					clickByElementByQueryJSExecutor(GloItCheckoutPage.USE_THIS_ADDRESS_BUTTON_PL);
					assertTrue(getWebDriver().findElements(cityError).size() == 0);
				}
		}
	}

	public void assertProvinceFieldDisplayedAsDropDown(){
		scrollElementIntoView(STATE_DROP_DOWN_VUSE_MX);
		assertTrue(isElementPresent(STATE_DROP_DOWN_VUSE_MX));
		assertTrue(getWebDriver().findElements(STATE_DROP_DOWN_OPTIONS_VALUE_VUSE_MX).stream().count() > 0);
	}

	public void userEntersPhoneNumberFieldOnAddressPopUp(String strValue) {
		switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
			case PL:
				waitClearAndEnterText(streetAddressLine2Input,"1polandfs");
				waitClearAndEnterText(txtTelephoneEditAddress, UrlBuilder.getMessage(strValue));
				break;
			default:
		}
	}

	public void selectDefaultBillingAddressRadiobutton() {
		waitAndClickByElementByJSExecutor(DEFAULT_BILLING_ADDRESS_RADIO_BUTTON,10);
		waitAndClickByElementByJSExecutor(FOOTER_SUBMIT_BUTTON,10);
	}
}