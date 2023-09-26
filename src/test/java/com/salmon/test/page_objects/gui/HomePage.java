package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.Lyft.LyftLabPage;
import com.salmon.test.page_objects.gui.admin.ContentPage;
import com.salmon.test.page_objects.gui.admin.LoginPage;
import com.salmon.test.page_objects.gui.admin.ProductPage;
import com.salmon.test.page_objects.gui.admin.StorePage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.epok.EpokHomePage;
import com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage;
import com.salmon.test.page_objects.gui.newsLetter.NewslettersPage;
import cucumber.api.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.randomAlphabetic;
import static com.salmon.test.page_objects.gui.constants.Context.NEW_USER_EMAIL_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

public class HomePage extends PageObject {

    private OrderSuccessPage orderSuccessPage;
    private LogininPage loginPage;
    private StorePage storePage;
    private ContentPage contentPage;
    private PLP plp;
    private PDP pdp;
    private LoginPage adminLoginPage;
    private AddNewAddressPage addNewAddressPage;
    private ProductPage productPage;
    private NewslettersPage newsLettersPage;
    private static final By VERIFY_BUTTON_FR = By.cssSelector("#btn-entry-age-allow");
    private LyftLabPage lyftLabPage;
    private RegistrationPage registrationPage;
    private AccountDashboardPage accountDashboardPage;
    private ScenarioContext scenarioContext;
    private String freeTotalGift;
    private OrderHistoryPage orderHistoryPage;
    public EpokHomePage epokHomePage;
    public PaymentPage paymentPage;
    public final static By DATE_SELECT_BOX = By.cssSelector(".date");
    public final static By MONTH_SELECT_BOX = By.cssSelector(".month");
    public final static By YEAR_SELECT_BOX = By.cssSelector(".year");
    public final static By DATE_SELECT_BOX_VELOZA = By.cssSelector("#ageGate > div > div > div.bat-agegate--avalanche-content-greeting > form > div:nth-child(1) > div > select");
    public final static By MONTH_SELECT_BOX_VELOZA = By.cssSelector("#ageGate > div > div > div.bat-agegate--avalanche-content-greeting > form > div:nth-child(2) > div > select");
    public final static By YEAR_SELECT_BOX_VELOZA = By.cssSelector("#ageGate > div > div > div.bat-agegate--avalanche-content-greeting > form > div:nth-child(3) > div > select");
    public final static By VERIFY_BUTTON_ZA = By.cssSelector("#age-gate-over");

    public HomePage() {
    }

    public HomePage(OrderSuccessPage orderSuccessPage, PLP plp, PDP pdp, LoginPage adminLoginPage, LogininPage loginPage, AddNewAddressPage addNewAddressPage, StorePage storePage, ContentPage contentPage, NewslettersPage newsLettersPage, ProductPage productPage, LyftLabPage lyftLabPage, RegistrationPage registrationPage, ScenarioContext scenarioContext, AccountDashboardPage accountDashboardPage, com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage orderHistoryPage, EpokHomePage epokHomePage) {
        this.orderSuccessPage = orderSuccessPage;
        this.plp = plp;
        this.pdp = pdp;
        this.loginPage = loginPage;
        this.adminLoginPage = adminLoginPage;
        this.storePage = storePage;
        this.contentPage = contentPage;
        this.loginPage = loginPage;
        this.addNewAddressPage = addNewAddressPage;
        this.newsLettersPage = newsLettersPage;
        this.productPage = productPage;
        this.lyftLabPage = lyftLabPage;
        this.registrationPage = registrationPage;
        this.scenarioContext = scenarioContext;
        this.accountDashboardPage = accountDashboardPage;
        this.orderHistoryPage = orderHistoryPage;
        this.epokHomePage = epokHomePage;
    }

    public static int intDelayTime;
    public static boolean blnFlag = false;
    private static int FREE_DELIVERY_BANNERS = 0;
    public static boolean miniCartOpen = false;

    private static final By LIQUIDS_LINK_FR_IPAD = By.cssSelector("a.category[href*='/e-liquides']");

    // Device Trial
    private final static By DEVICE_TRIAL_CTA = By.cssSelector("a#devicetrial_cta");
    private final static By CONFIGURABLE_PRODUCT_CHECKBOX = By.cssSelector("#deviceTrialVariants li:nth-child(1) input[type='radio']");
    private final static By CONFIGURABLE_PRODUCT = By.cssSelector("ul#deviceTrialVariants");
    private final static By GLO_SHOP_LINK = By.cssSelector("#ui-id-2 > li.level0.nav-1.submenu-head.category-item.level-top.ui-menu-item");
    private final static By NEO_STICKS_LINK = By.cssSelector("#ui-id-2 > li.level0.nav-1.submenu-head.category-item.level-top.ui-menu-item > ol > li.level0.nav-1.category-item.first.level-top > a > span");
    private final static By PROCEED_TO_CART_BUTTON = By.cssSelector("div.block-minicart div.actions a.viewcart");
    private final static By NEO_STICKS_PRODUCT_LINK = By.cssSelector("li.level0.category-item.second > a:nth-child(2)");
    private static final By ACCEPT_OPTION_REMOVE_BASKET_PAGE = By.cssSelector("a.action.action-delete");
    private static final By NEO_STICKS_MENU = By.cssSelector("ul > li.level0.category-item.third > a:nth-child(2)");
    public static final By DEVICE_TRAIL_CTA_GLODE = By.cssSelector("div.experimentation-free-trial-banner > div > div > div > div > a > span");
    // Login
    public final static By loginContainer = By.cssSelector("div.login-container");
    private final static By SIGN_OFF_MENU = By.cssSelector("ul[class='dropdown account-dropdown']>li:nth-child(6)>a");

    public final static By OUR_BLOG_LINK_UK_CX = By.cssSelector("body > div.page-wrapper > header > div.more-menu-container > div > div.items.sixth > div > div > div > div:nth-child(1) > div > ul > li:nth-child(2) > a");
    public final By EMAIL_POPUP_LINK = By.cssSelector("input#email.input-text");
    public final static By proceedToCheckoutButton = By.cssSelector("#maincontent > div > div > div.cart-sidebar-wrap > div > ul > li > button:nth-child(1)");
    private final static By EDIT_LINK = By.cssSelector("div.block-content div.box.box-information div.box-actions a.action.edit:nth-child(1),div.account-info__container > div > a.action.primary");
    private final static By DASHBOARD_EDIT_LINK = By.cssSelector("div.account-info__container > div > a.action.primary");

    public By listedFooterItems = By.cssSelector("ul.footer-menu");
    // ELEMENT MAPPING
    private By homePageHeading = By.cssSelector("h1.page-title");
    public By logo = By.cssSelector("a.logo, .column.logo-container img");
    public By headerClass = By.cssSelector("div.header-vype.row");
    public final static By HEADER_CLASS_GLOIT = By.cssSelector("div.header-top.row");
    public final static By SANDWICH_MENU_ICON = By.cssSelector("div.column.burger-menu");
    private final static By LOGO_ICON_DE = By.cssSelector("div.column.logo-container.desktop-only > a > img");
    private final static By LOGO_ICON_FR = By.cssSelector(".column.logo-container img");
    private final static By BLOG_VIEW_ALL_LINK_GLO_PL = By.cssSelector(".column a[href='/pl/pl/blog']");
    public final static By M_SANDWICH_MENU_ICON = By.cssSelector(".action.nav-toggle span");
    public static final By M_SANDWICH_MENU_ICON_IE = By.cssSelector("body > div.page-wrapper > header > div > div.column.mobile-left-actions.mobile-only.row > div:nth-child(1) > span > i,a.action.showcart");
    private static final By M_BURGER_MENU_ZA = By.cssSelector("i.material-icons._at-menu-icon");
    public static final By MINI_CART_OPEN_STATUS = By.cssSelector(".action.showcart.active");
    public final static By VIEW_BASKET = By.cssSelector("#minicart-content-wrapper > div > div.actions.minicart-actions > div > a,a.action.viewcart.primary");
    public final static By VIEW_BASKET_PL = By.cssSelector("a.viewcart span");
    public final static By M_SANDWICH_MENU_ICON_UK = By.cssSelector("body > div.page-wrapper > header > div.header-vype.row > div.column.row.user-menu > div.action.nav-toggle > span");
    private static final By VUSECO_FLAVOURS = By.cssSelector("a.icon-cartridge");
    private static final By VUSECO_SUBFLAVOURS = By.cssSelector("li:nth-child(2) > div.sub-level > div:nth-child(1) > a");
    private final static By M_FLAVOURS_VUSEZA = By.cssSelector("a.icon-cartridge");
    private final static By M_SUBFLAVOUR_VUSEZA = By.cssSelector("ol.first-menu > li:nth-child(2) > div.sub-level > div:nth-child(1) > a");
    private static final By M_ALL_FLAVOURS_LINK_ZA = By.linkText("All");
    private static final By M_BURGER_MENU_VUSEDE = By.cssSelector("span.icon-menu");
    private static final By M_BURGER_MENU_VUSEFR = By.cssSelector("div.column.row.user-menu > div.action.nav-toggle > span.icon-menu");
    private static final By M_SANDWICH_MENU_ICON_GLO_IT = By.cssSelector("span.action.nav-toogle.mobile-only > img[alt='Toogle Nav']");
    private static final By M_ACCOUNTICON_VUSECO = By.cssSelector("a.icon-account");
    private static final By M_SIGNIN_VUSECO = By.cssSelector("div.burger-menu-item.userLoggout > a");
    private final static By COOKIES_CATEGORY_DESCRIPTION = By.cssSelector("p.ot-category-desc");
    private final static By COOKIES_DETAILS_LINK = By.cssSelector("button.ot-link-btn.category-host-list-btn.category-host-list-handler");
    private final static By COOKIES_DETAILS_SECTION_TITLE = By.cssSelector("h3#vendors-list-title");
    private final static By COOKIES_DETAILS_BACK_BUTTON = By.cssSelector("p.pc-back-button-text");
    public final static By M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT = By.cssSelector("li.my-account a");
    public final static By M_SANDWICH_MENU_ICON_OPTION_BASKET = By.cssSelector("div.burger-menu-item a[href*=cart],a.action.viewcart.primary");
    public final static By M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT_SIGNINREGISTER = By.cssSelector("div.burger-menu-item.userLoggout a");
    private final static By LOGO_DK = By.cssSelector("div.header-vype.row a.logo > img:nth-child(1)");
    private final static By PLP_LOGO_DK = By.cssSelector("a.logo img");
    private final static By LIST_FOOTER_ITEMS = By.cssSelector("div.footer_top-cmsblock");
    public final static By M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT_MYACCOUNT = By.cssSelector("div.burger-menu-item.userLoggin a");
    public final static By M_VIEW_BASKET_BUTTON = By.cssSelector("div.actions.minicart-actions a");
    public final static By T_BASKET_ICON_IE = By.cssSelector("body > div.page-wrapper > header > div > div.column.actions-secondary.mobile-right-actions.row > div.column.most-right-header.desktop-only > div > div > a > span.cart_icon");
    public final static By buttonAgeAllow = By.cssSelector("#btn-entry-age-allow");
    public final static By BUTTON_AGE_ALLOW_CX = By.cssSelector("#btn-entry-age-allow");
    public final static By buttonAgeAllowJP = By.cssSelector("button[data-cta-type='true']");
    public final static By BUTTON_AGE_ALLOW_VELOBE = By.cssSelector("#age-gate-over");
    public final static By BUTTON_AGE_ALLOW_VELOPL = By.cssSelector("button#btn-entry-age-allow > span");
    public final static By buttonAgeAllowUK = By.cssSelector(".actions #btn-entry-age-allow");
    private static final By BUTTON_AGE_ALLOW = By.cssSelector("#check-dob-age-gate-btn");
    private static final By BUTTON_AGE_ALLOW_GLOIT = By.cssSelector("div.actions > button#btn-entry-age-allow > span");
    public static final By ALL_FLAVOURS_UK_CX = By.cssSelector(".category-navigation li:nth-child(2)");
    public static final By DEVICES_LINK_UK_CX = By.cssSelector(".category-navigation li:nth-child(1)");
    public static final By CLEAR_CART_PL = By.cssSelector("button.action.clearcart");
    public final static By LOGO_ICON_VUSEUK = By.cssSelector("div.column.logo-container>a");
    public final static By DENY_AGE_GATE_CTA = By.cssSelector("button#btn-entry-age-deny");
    public final static By VUSE_CARE_LINK = By.xpath("//a[@class='navigation-widget__link'][contains(@href,'vuse-care')]");
    private static int productItemCount = 0;
    public final static By DELETE_MY_ACCOUNT = By.cssSelector("[href*='deletemyaccount/']");
    public final static By CONFIRM_CHECKBOX = By.cssSelector("div.delete-my-account > form > fieldset > div > label");
    public final static By CONFIRM_DELETE = By.cssSelector("div.delete-my-account > form > div > div > button");
    public final static By STORES_LIST = By.cssSelector("#storesearch > div.store-search-results");
    public static final By CLOSE_FREE_GIFT_WINDOW = By.cssSelector("div.ampromo-close");
    public static final By SIGN_OUT_MENU = By.cssSelector("#account-nav > ul > li:nth-child(11) > a");
    private static final By M_MENU_CLOSE_BTN_VUSEFR = By.cssSelector("div.column.row.user-menu > div.action.nav-toggle > span.icon-vype-close");
    public static final By GOOGLE_PAGE = By.cssSelector("#gb > div");
    // Header Links
    public final static By HOME_PAGE_TO_LOAD = By.cssSelector("a.pagebuilder-button-primary");
    private final static By SIGN_IN_LINK = By.cssSelector("div.dropdown.customer-menu ul:nth-child(1) li:nth-child(1) > a:nth-child(1)");
    private final static By SIGN_IN_LINK_PL = By.cssSelector("div.userLoggout > div > div > div > ul > li > a,div.userLoggout ul.dropdown.account-dropdown > li:nth-child(1) > a");
    private final static By HEADER_FAVORITE_ICON = By.cssSelector("div.column.actions-primary.desktop-only.row:nth-child(6) div.column:nth-child(2) a:nth-child(1) > i.material-icons");
    private final static By CUSTOMER_DROPDOWN_OPTION_ACCOUNT_VYPEDE = By.cssSelector("li.authorization-link a");
    private final static By CUSTOMER_DROPDOWN_OPTION_ACCOUNT_VUSE_DE = By.cssSelector("div.user_dropdown > div.userLoggout");
    public static final By DEVICES_CATEGORY = By.xpath("//div[@class='row-full-width-inner']//ul[@class='category-navigation']");
    public static final By SHOP_DEVICES = By.linkText("E-ZIGARETTEN");
    public static final By SHOP_DEVICES_VUSEIT = By.cssSelector("li.level0.category-item.icon-devices.first [href*='dispositivi-sigaretta-elettronica']");
    private static final By SHOP_FLAVORS_LINK = By.cssSelector("li.category-item.icon-cartridge.second > a[href*='liquid']");
    public final By homePageMainNavDropDown = By.cssSelector("div.items.first");
    private static final String BASKET_DELETE_BTN_IT = ".action.delete";
    private static final By BASKET_QTY_UK_CX = By.cssSelector("span.counter.qty");
    private static final By BASKET_QTY_VUSE_DE = By.cssSelector("span.counter.qty .counter-number");
    private static final By M_BASKET_QTY_VUSE_DE = By.cssSelector("div.quantity-dropdown > div.qty-btn > span");
    private static final By M_BASKET_QTY_VUSE_IT = By.cssSelector("div.quantity-dropdown > div.qty-btn > span");
    private static final By INCREASE_QTY_VUSE_DE = By.cssSelector("span.qty-modifier.plus.button-plus-1.icon-plus");
    private static final By BASKET_QUANTITY_COUNTER = By.cssSelector("div:nth-child(1) span.cart_icon:nth-child(1) > span:nth-child(1) > span:nth-child(1), .details-qty.qty [data-bind='text: qty']");
    private static final By OTCOOKIES_SETTINGS_BUTTON_DK = By.cssSelector("button#onetrust-pc-btn-handler");
    private static final By ALLOW_COOKIES_BUTTON_DK = By.cssSelector("button#accept-recommended-btn-handler");
    private static final By ALLOW_COOKIES_BUTTON_SE = By.cssSelector("button#btn-cookie-allow");
    private static final By COOKIES_ACTIVE_CATEGORY_DK = By.cssSelector("li.ot-cat-item.ot-always-active-group");
    private static final By FOOTER_REDIRECT_LINKS = By.xpath("//ul[@class='footer-menu']//a[contains(@href,'http')][not(contains(@target,'blank'))]");
    private static final By NEWSLETTER_SECTION_LINKS = By.xpath("//div[@class='newsletter-container']//a[contains(@href,'http')]");
    private static final By HEADER_REDIRECT_LINKS = By.xpath("//ul[@class='column categories row desktop-only']/li/a[contains(@href,'http')]");
    private static final By HEADER_USERBAR_LINKS = By.xpath("//div[@class='userbar-right']//li[contains(@class,'userbar-item')]/a[contains(@href,'http')]");
    private static final By DEVICES_SUB_LINKS = By.xpath("//div[@class='items first devices']//a[contains(@href,'/de/de/')][@data-element='link']");
    private static final By FLAVOURS_SUB_LINKS = By.xpath("//div[@class='items second flavours']//a[contains(@href,'/de/de/')][@data-element='link']");
    private static final By VIEW_BASKET_BUTTON = By.cssSelector("#minicart-content-wrapper > div > div.actions.minicart-actions > div > a");
    private static final By M_VIEW_BASKET_BUTTON_IT = By.cssSelector("div.column.minicart > div > a");
    private static final By M_VIEW_BASKET_BUTTON_GLOKZ = By.cssSelector("div.header-link > div.minicart-wrapper > a.action.showcart");
    private static final By M_VIEW_BASKET_BUTTON_FR = By.cssSelector("div.sliding-panel-section.sliding-minicart:nth-child(3) a.viewcart.action.secondary");
    private static final By M_FLAVOURS_VUSE_ZA = By.cssSelector("div.column.header-category >div > div > div > div > div > div > div:nth-child(2) > div > a");
    private final static By MIX_AND_MATCH = By.cssSelector("div.items.second > div:nth-child(3) > div > div > div:nth-child(5) > div > a > div > div");
    private final static By SHOP_DEVICES_ZA = By.cssSelector("li.level0.category-item.icon-vuseza-shop-devices.first > a:nth-child(2)");
    private final static By COMBOS = By.cssSelector("div.navigation-widget__info > ul > li:nth-child(4) > a");
    private final static By ABOUT_VUSE_ZA = By.cssSelector("div.custom-categories > div > div > ul > li.level0.category-item.icon-vuse.sixth");
    private final static By ABOUT_VELO_ZA = By.cssSelector("body > div.bat-wrapper > div > div > div:nth-child(2) > div > div > bat-header-avalanche > div > div > header > div.bat-header-menu > div > nav > div > ul > li:nth-child(3) > a > span");
    private final static By REFER_A_FRIEND_LINK_ZA = By.cssSelector("div.more-menu-container > div > div.items.fifth > div > div.pagebuilder-column-group > div:nth-child(3) > div > a > div > div");
    public final static By REFER_A_FRIEND_BUTTON_ZA = By.cssSelector("#maincontent > div.columns > div > div:nth-child(6) > div > div:nth-child(3) > div > a");
    private final static By ABOUT_VUSE_LINK_ZA = By.cssSelector("div.pagebuilder-column.firstcol > div > ul > li:nth-child(1) > a");
    private final static By FIND_OUT_MORE = By.cssSelector("#maincontent > div.columns > div > div:nth-child(9) > div > div:nth-child(1) > div > a");
    private final static By EXPLORE_PRODUCT = By.cssSelector("#maincontent > div.columns > div > div:nth-child(10) > div > div:nth-child(2) > div.two-button > div > a");
    private final static By TOTAL_TAX_LYFTSE = By.cssSelector("tr.totals-tax-summary>td.amount");
    private final static By TOTAL_TAX_DETAIL_LYFTSE = By.cssSelector("tr.totals-tax-details.shown>th");
    private final static By MINI_CART_PRICE = By.cssSelector("div.header-link > div > a > span.cart_details > span");
    public final static By M_EPEN_LIST = By.cssSelector("div.burger-menu-container > div > div > div > div > ol > li:nth-child(2) > div.sub-level > div:nth-child(2) > a");
    public final static By TAEKA_HEADER = By.cssSelector(".level0.category-item.first");

    //Store Locator
    private final static By STORE_LOCATOR_LINK = By.cssSelector("i.material-icons._at-fav-icon");
    private final static By SHOP_TYPE_DROPDOWN = By.xpath("//select[@id='store-type']");
    private final static By PRODUCT_TYPE_DROPDOWN = By.cssSelector("#activity-type");

    //Lyft Lab Objects
    private final static By NEWSLETTER_SUBSCRIBE_LINK = By.xpath("//a[contains(@href,'newsletter-register/subscribe')]");
    public final static By NEWSLETTER_SUBSCRIBE_PGE = By.cssSelector("#terms-and-conditions");
    private final static By LYFTLAB_BURGER_MENU = By.cssSelector("span.action.nav-toogle:nth-child(1) > img:nth-child(1)");
    private final static By LYFTLAB_BURGER_MENU_LOGIN_OPTION = By.cssSelector("li.burger-menu-item a[href*=login]");
    private final static By MINICART_COUNT = By.cssSelector("span.count");
    private final static By DELETE_CARTITEMS_LINK = By.cssSelector("a.action.delete");
    private final static By LOGOUT_LINK = By.linkText("Logout");
    private final static By LOGOUT_LINK_GLO_PL = By.cssSelector(".nav.items [href*='logout']");
    private final static By LOGOUT_LINK_VELO = By.cssSelector("header > div.bat-header-account.open > div.bat-header-account-menu > ul > li:nth-child(2) > a");
    private final static By LOGOUT_LINK_VELO_PL = By.cssSelector(".account-dropdown.dropdown > li:nth-of-type(5) > a");
    private final static By FORGET_PASSWORD_LINK = By.cssSelector(".action.remind");
    private final static By INSIDER_CLUB_LINK = By.cssSelector(".row [href*='it/it/insiders-club-info/']");
    private final static By TOBACCO_HEATER_LINK = By.cssSelector(".first [href*='/de/de/glo-tabak-heater/']");
    private final static By GLO_EQUIPMENT_LINK = By.cssSelector(".level0.category-item.third a[href*='/de/de/glo-zubehoer/']");
    private static By LINK_DOWNLOAD_PDF = By.cssSelector("p>#printpdf,a.pagebuilder-button-secondary");
    private final static By LYFT_LOGO_IMAGE = By.cssSelector("div.column-1.footer-desktop-logo.desktop-only a.logo > img:nth-child(1)");
    public final static By LYFTLAB_FAQEXPAND_Button = By.cssSelector("i.expand.material-icons");
    private final static By FREE_DELIVERY_BAR_SV = By.xpath("//div[@class='pagebuilder-column']//following::strong[text()='GRATIS_HEMLEVERANS']");
    private final static By FREE_DELIVERY_BAR_EN = By.xpath("//div[@class='pagebuilder-column']//following::strong[contains(text(),'FLUSH SALE') or contains(text(),'FREE DELIVERY TO YOUR HOME')]");
    private final static By SWITCH_LANGUAGE_TRIGGER = By.cssSelector("#switcher-language-trigger");
    private final static By SWITCH_LANGUAGE_LINK = By.cssSelector("div.actions.dropdown.options.switcher-options.active li.view-lyft_se_en_se.switcher-option > a");
    public final static By VYPE_UK_CAT_RESULT = By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product-item-group > div.product.name > a");
    private static final By ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_LYFTSE = By.cssSelector(".footer-support  .footer-cookie-manage-link.ot-sdk-show-settings");
    private static final By ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_UK = By.cssSelector(".pagebuilder-column .footer-cookie-manage-link.ot-sdk-show-settings");
    private static final By LANGUAGE_SWITCHER_TRIGGER_CSS = By.cssSelector(".header-content-search #switcher-language-trigger");
    private static final By SWITCH_LANGUAGE_CSS = By.xpath("//header//a[normalize-space()='EN']");
    private static final By OCR_POPUP = By.cssSelector("div.at-ocr-popup-align");
    private static final By CLOSE_OCR_POPUP = By.cssSelector("div.at-ocr-close-btn");
    private static final By ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_ZA = By.xpath("//*[@id=\"ot-sdk-btn\"]");

    //Back-Office
    private By lnkInstagram = By.cssSelector("div:nth-child(2) > div > div > a.icon-instagram");
    private By lnkFacebook = By.cssSelector("div.footer--social-media ul.social-media-icons li:nth-child(1) > a:nth-child(1)");
    private final static By YOUTUBE_FOOTER_LINK = By.xpath("//a[contains(@href,'youtube')]");
    private final static By INSTAGRAM_FOOTER_LINK = By.xpath("//a[contains(@href,'instagram')]");
    private final static By TWITTER_FOOTER_LINK = By.xpath("//a[contains(@href,'twitter')]");
    private final static By FACEBOOK_FOOTER_LINK = By.xpath("//a[contains(@href,'facebook')]");
    public final static By FACEBOOK_LINK_FR = By.cssSelector("div.desktop-footer-links a.icon-facebook");
    public final static By FACEBOOK_LINK_MX_FOOTER = By.cssSelector("div.desktop-footer-links a.icon-facebook");
    public final static By INSTAGRAM_LINK_MX_FOOTER = By.cssSelector("div.desktop-footer-links a.icon-instagram");
    private final static By INSTAGRAM_FOOTER_LINK_PL_FOOTER = By.cssSelector(".footer_top-cmsblock [data-content-type='row']:nth-child(3) .icon-instagram");
    private final static By FACEBOOK_FOOTER_LINK_PL_FOOTER = By.cssSelector(".footer_top-cmsblock [data-content-type='row']:nth-child(3) .icon-facebook");
    public final static By FOLLOW_US_SOCIAL_PL = By.cssSelector(".social-footer.extra-icons");
    public final static By COOKIE_SETTINGS_FOOTER_LINK_VUSE_MX = By.cssSelector("div.pagebuilder-column-group > div:nth-child(3) > div:nth-child(2) > ul.footer-menu > li:nth-child(6) > a.footer-cookie-manage-link");
    public By eleNewsletterErrMessage = By.cssSelector("div.message-error.error.message");

    private final static By PLP_ADDTOCART_SECONDARY = By.cssSelector("button.action.tocart.secondary");
    private final static By MX_CREDITCARD_OPTION = By.cssSelector("#tns_hosted");
    private final static By VUSE_EPOD_PLP_IMAGE = By.cssSelector("#product-list-plp li.item.product.product-item img");

    private final static By HEADER_CONTACT_ICON = By.cssSelector("div.column.contact.desktop-only:nth-child(2) a:nth-child(1) > i.material-icons");
    public final static By PERSON_ICON_GLO = By.cssSelector("span.icon-user,span.icon-account");
    public final static By PERSON_ICON_GLO_IPAD = By.xpath("//span[@class='icon-account']");
    public final static By M_PERSON_ICON_GLOIT = By.cssSelector("div.header-top.row > div.column.user > a > span.icon-user");
    private static final By MX_PERSON_ICON = By.cssSelector("div.header-vype.row div.column.actions-primary.desktop-only.row:nth-child(6) div.column:nth-child(1) > a:nth-child(1)");
    public final static By PERSON_ICON = By.cssSelector(UrlBuilder.getMessage("personIconReference.key"));  //("div:nth-child(1) > a > i"); //"div.column:nth-child(6) > div:nth-child(1) > a:nth-child(1) > i:nth-child(1)");
    public static final By PERSON_ICON_VELOBE = By.cssSelector("div.bat-header-account-link > a > i");
    public static final By PERSON_ICON_VELOPL = By.cssSelector(".header-link > .icon-account");
    public static final By ACCOUNT_VELOPL = By.cssSelector(".userLoggout > div > div > div > .account-dropdown.dropdown  a");
    public static final By LOGGEDIN_ACCOUNT_VELOPL = By.cssSelector(".userLoggin > div > div > div > .account-dropdown.dropdown > li:nth-of-type(1) > a");
    public final static By PERSON_ICON_LOGGEDIN = By.cssSelector("span.icon-account.userLoggin");
    public final static By VYPE_ACCOUNT_LINK_UK = By.cssSelector("a.header-link>span, span.icon-account");
    public final static By VUSE_ACCOUNT_LINK_DK = By.cssSelector(".customer-name a span");
    public final static By VYPE_ACCOUNT_LINK_IE = By.cssSelector("body > div.page-wrapper > header > div > div.column.actions-primary.desktop-only.row > div:nth-child(1) > a > i");
    private final static By MY_ACCOUNT_ON_ACCOUNT_DROPDOWN = By.cssSelector(".account-dropdown [href*='/account/index']");
    private final static By PEOPLE_ICON_VUSE_IT = By.cssSelector("div.column.desktop-only.customer_action > a");
    public final static By M_PERSONICON_LYFTDK = By.cssSelector("span.account-icon.mobile-only span");
    public final static By M_PERSONICON_VYPEDE = By.cssSelector("div[class*='mobile-only'] a[href*=customer]");
    public final static By T_PERSONICON_IE = By.cssSelector("body > div.page-wrapper > header > div > div.column.actions-primary.desktop-only.row > div:nth-child(1) > a > i");
    public final static By T_PERSONICON_FR = By.cssSelector("body > div.page-wrapper > header > div.userbar.userbar-top.clearfix > div.userbar-right > ul > li.userbar-item.customer > span > span");
    public final static By T_REGISTRATION_LINK_FR = By.cssSelector("body > div.page-wrapper > header > div.userbar.userbar-top.clearfix > div.userbar-right > ul > li.userbar-item.customer.active > div > ul > li > a");
    public final static By M_PERSONICON_VYPEIT = By.cssSelector("a i.material-icons");
    public final static By M_PERSONICON_VYPECO = By.cssSelector("div.mobile-only div.column.user a i");
    public final static By M_PERSONICON_VUSEFR = By.cssSelector(".userLoggout .account-dropdown li:first-child a");
    public final static By M_PERSONICON_VYPEMX = By.cssSelector("span.icon-account");
    public final static By T_PERSONICON_VYPEMX = By.cssSelector("div.header-vype.row div.column.mobile-left-actions.mobile-only.row:nth-child(1) div.column.user:nth-child(2) > a:nth-child(1)");
    public final static By PERSONICON_VELO_EU_DE = By.cssSelector(".account-icon");
    public final static By LOGIN_LIST_OPTION_DE = By.cssSelector("li.userbar-item.customer.active > div > ul > li > a");
    public final static By LYFT_PERSONICON = By.cssSelector(".column.user>a");
    public final static By M_LYFT_PERSONICON = By.cssSelector("span.account-icon.mobile-only a");
    public final static By BLOG_ICON_LYFT = By.cssSelector(".desktop-only.header-content-right.main-navigation > div:nth-of-type(1) > div > div > ul > li:nth-of-type(5) > a");
    public final static By BLOG_ICON_LYFTDK = By.cssSelector(".header-content-right.main-navigation.desktop-only li:nth-child(3)>a");
    public final static By NEWS_LETTER_CO = By.cssSelector(".block.newsletter-popup");
    public final static By CLOSE_BUTTON = By.cssSelector("body > div.page-wrapper > div.visitor-popup-wrapper > div > span");
    public final static By CLOSE_BUTTON_CO = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.modal.newsletter-popup.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
    public final static By M_PERSONICON = By.cssSelector("div.column.user > a > i");
    public By favIcon = By.cssSelector(".actions-primary.desktop-only.row > div:nth-child(2)");
    public By ZA_FAVICON = By.cssSelector("div.column.header-icons > div:nth-child(2) > a > i");
    public By backupRestore = By.cssSelector(".actions-primary.desktop-only.row > div:nth-child(3)");
    public By chatIcon = By.cssSelector("div.column.chat > i");
    public By searchIcon = By.cssSelector("div.column.vype_search > i");
    public By navMoreButton = By.cssSelector("a.more");
    public By headerShopLink = By.cssSelector("body > div.page-wrapper > header > nav > div.custom-categories > div > div > ul > li.level0.category-item.icon-devices.first > a:nth-child(2)");
    public By headerAboutVypeLink = By.cssSelector("li.level0.category-item.icon-vype.sixth > a:nth-child(2)");
    public By lnkOurBlog = By.xpath("//div[@class='more-menu-container']//a[contains(text(), 'Blog')]");
    public By lnkVypeSubscriptions = By.cssSelector("div.pagebuilder-column:nth-child(3) div:nth-child(1) p:nth-child(2) > a:nth-child(1)");
    public final static By BASKET_ICON = By.cssSelector("a.action.showcart");
    public static final By BASKET_LOADER = By.cssSelector("div.bat-loading-mask");
    private static final By BASKET_ITEMS = By.cssSelector("div.bat-cartdetail-item");
    public static final By BASKET_ICON_JP = By.cssSelector("a[href='/basket']");
    private static final By GLOJP_BASKET_COUNT = By.cssSelector("body > div.bat-wrapper.visible-true > div > div > div:nth-child(1) > div > div.header.aem-GridColumn.aem-GridColumn--default--12 > bat-header-glo > div.bat-header > div > header > div.bat-header--wrapper > div.bat-header--right.d-flex > div > div.bat-header-cart.bat-header-icon.right-icon > a > span.bat-header-cart-count.small.active");
    public final static By BASKET_QTY_VUSECO = By.cssSelector("a.action.showcart > span >  span.counter.qty > span:nth-child(1)");
    public final static By BASKET_ICON_CO = By.cssSelector("div.column.actions-secondary.mobile-right-actions.row > div.column.desktop-only > div > div > a > i");
    public static final By T_BASKET_ICON_FR = By.cssSelector("body > div.page-wrapper > header > div.header-vype.row > div.minicart-container > div > div > a > span.cart_icon");
    public final static By T_PORTRAIT_BASKET_ICON = By.cssSelector("body > div.page-wrapper > header > div.header-vype.row > div.column.actions-secondary.mobile-right-actions.row > div:nth-child(4) > div > div > a > i");
    private final static By BASKET_ICON_DK = By.cssSelector("a.action.showcart > span >  span.counter.qty");
    public final static By VUSE_DE_MY_ACCOUNT_LINK_FROM_DROPDOWN = By.linkText("MEIN KONTO");
    public final static By M_BASKET_ICON = By.cssSelector("div[class*=mobile-only] div.column.right i.material-icons");
    public final static By M_BASKET_ICON_NL = By.cssSelector("div.footer-mobile-floating-nav.mobile-only.row a.action.showcart i");
    public final static By BASKET_ICON_MX = By.xpath("//div[@class='column desktop-only']//div//a[@class='action showcart']");
    public final static By BASKET_ICON_IT = By.cssSelector("div.column.desktop-only span.counter.qty");
    public final static By BASKET_ICON_COUNT_MX = By.cssSelector("div:nth-child(4) > div > div > a > span.counter.qty > span.counter-number");
    private static final By DELETE_PRODUCT_ICON = By.cssSelector("div.remove>button");
    public final static By BASKET_ICON_ZA = By.cssSelector(" div.column.desktop-only:nth-child(7) div.minicart-wrapper a.action.showcart:nth-child(1) div.minicart-icon-right span.cart_details:nth-child(2) > span.price,span.cart_details > span");
    public By basketCloseSlidingPanelButton = By.cssSelector("body > div.sliding-panel-sections > a > i,#btn-minicart-close");
    public By basketCloseSlidingPanelButtonMobile = By.cssSelector("#btn-minicart-close");
    public By basketCloseSlidingPanelButton_PL = By.cssSelector("button#btn-minicart-close");
    public By basketIconCO = By.cssSelector("div.column.desktop-only:nth-child(3) div:nth-child(1) div.minicart-wrapper a.action.showcart:nth-child(1) > i.material-icons");
    public static final By REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX = By.cssSelector("button.action.primary.remove");
    public static final By REMOVE_ITEM_FROM_BASKETPAGE_ICON_SE = By.cssSelector("#mini-cart > li.item.product.product-item.odd.last > div > div > div.product.actions > div.secondary > a");
    public static final By CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_SE = By.cssSelector(".action-accept.action-primary");
    public static final By CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_UK_CX = By.cssSelector("a.action.action-delete");
    public static final By CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_FR_CX = By.cssSelector("footer.modal-footer button.action.primary,a.action.action-delete");
    private static final By BASKET_ICON_GLOIT = By.cssSelector("span.counter.qty > span.counter-number");
    private static final By NEWSLETTER_NAME_ERROR = By.cssSelector("#firstname-error");
    private static final By NEWSLETTER_EMAIL_TEXT = By.cssSelector("#newsletter");
    private static final By NEWSLETTER_EMAIL_ERROR = By.cssSelector("#newsletter-error");
    private static final By NEWSLETTER_REGISTER = By.cssSelector("#subscribe_news");
    private static final By CHECKOUT_BUTTON = By.cssSelector("div.bat-cartsummary-button>button");
    private static final By CHECKOUT_PAGE_HEADER = By.cssSelector("h1.page-title");
    private static final By RESET_PASSWORD_EMAIL = By.cssSelector("#email_address");
    private static final By RESET_PASSWORD_REGISTER = By.cssSelector("#form-validate > div > div > button");
    private static final By RESET_PASSWORD_EMAIL_ERROR = By.cssSelector("#email_address-error");
    private static final By REMOVE_PRDUCT_MINI_CART = By.cssSelector("li > div > div.product.actions > div > a > i");
    private static final By DELETE_BUTTON_POPUP = By.cssSelector("button.action-primary.action-accept");
    private static final By EMPTY_BASKET_MSG = By.cssSelector("#minicart-content-wrapper > div > strong");

    //More Menu - Country Selector/Blog
    public By navMoreMenuCountrySelector = By.cssSelector("div.column.more-menu-item.selected-country:nth-child(4) > a:nth-child(1)");
    public By navCountrySelectorMenuMexicoLink = By.cssSelector("div.items.countries:nth-child(5) div.row div.column.more-menu-item:nth-child(11) a:nth-child(1) > span:nth-child(2)");
    public By navMoreMenuCountrySelectorDiv = By.cssSelector("div.items.countries:nth-child(5)");
    public By eleBlogCategoriesMenu = By.cssSelector("div.category-panel-inner > div.widget.block.block-categories");
    public By navMoreMenuBlogLink = By.xpath("//a[contains(text(),'Blog')][@href='/uk/blog']");
    public By navMoreMenuBlogLinkIE = By.xpath("//a[contains(text(),'Blog')][@href='/ie/en/blog']");
    public By btnFindOutMore = By.cssSelector("a.button.post-read-more");
    public By eleShareThisPostWithSocialMediaBlogs = By.cssSelector("div.blog-socials");
    public By eleOtherArticlesWidget = By.xpath("//li[@class='item']//following::div[@class='item-info_details']//following-sibling::a[@class='item-image-wrap']");
    public final static By BASKET_ICON_LYFTSE = By.cssSelector("div.full-width>a:nth-child(1)");
    public final static By PERSON_ICON_PL = By.cssSelector("span.icon-account");
    public final static By EDIT_DETAILS = By.cssSelector("div.account-info__container > div > a.action.primary");
    public final static By PROFILE_PICTURE = By.cssSelector("span.icon-account.userprofile");
    public final static By PROMOS = By.cssSelector("li.level0.category-item.icon-megaphone.third");
    public final static By LEFT_WINDOW_MENUS = By.cssSelector("#account-nav > ul > li>a");
    //Refer A Friend
    public By btnLogin = By.xpath("//*[text()='Login']");
    public By btnInviteFriends = By.cssSelector("div.pagebuilder-column:nth-child(1) div.shard-buttons:nth-child(3) div:nth-child(2) > div.pagebuilder-button-secondary");
    public By inviteFriendsPopUp = By.cssSelector("div:nth-child(1) > form.invite-friendsForm");
    public By btnClosePopUp = By.xpath("//*[contains(@id,'modal-title')]//following::button[@class='action-close']");
    public By eleReferAfriendPageTitle = By.xpath("//*[text()='REFER A' and 'FRIEND']");
    public By eleInviteFriendsSection = By.xpath("//*[text()='Invite ' and 'Friends']");
    public By eleGetCouponsSection = By.xpath("//*[text()='Get' and 'Coupons']");
    public By eleRedeemVouchers = By.xpath("//*[text()='Reedem' and 'Vouchers']");
    public By BREING_FRIEND_WAY_LINK = By.cssSelector("a.icons.info-icon");
    public static final By M_BREING_FRIEND_WAY_LINK_GLOIT = By.cssSelector("div.mgm-box-social-icon > div.icon-container > a.icons.info-icon");

    public final static By SEARCH_INPUTBOX = By.cssSelector("#search");
    public final static By SEARCH_INPUTBOX_UK = By.xpath("//div[@class='column vype_search']//input[@id='search']");
    public final static By SEARCH_INPUTBOX_JP = By.cssSelector("button.bat-productsearch-button+input");
    public final static By DASHBOARD_MENU_EXPAND_FR = By.cssSelector("div.accordion-header > div");
    public final static By SEARCH_INPUTBOX_FR = By.cssSelector("div.vype_search input#search");

    // Header text
    private static final By HEADER_DELIVERY_MESSAGE = By.cssSelector("div.header-assurance div[class*='cyan-theme desktop-only header-reassurance-msg'] span");
    public By headerDeliveryMessage_VUSEFR = By.cssSelector("div.header-assurance > div.cyan-theme.desktop-only.header-reassurance-msg > div > div > div:nth-child(2) > div > span");
    // Basket
    public By baseketEmptyMsg = By.cssSelector("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > strong");
    private static By BASEKT_EMPTY_MSG = By.cssSelector(".subtitle.empty");
    private static By BASEKT_DISCOUNT_MSG = By.cssSelector("#ui-id-1 > div.minicart-discount-message");
    private static By BASEKT_EMPTY_MSG_KZ = By.cssSelector("div.cart-empty div[data-content-type='text']");
    private static By M_PRODUCT_ADDED_SUCCESS_MSG = By.cssSelector("div.messages div.message-success");
    public By basketQty = By.cssSelector("span.counter-number");
    public By basketQtyToUpdate = By.cssSelector(".qty.cart-qty");
    public By proceedToCheckOutButton = By.cssSelector("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > div:nth-child(6) > div > button#top-cart-btn-checkout");//By.cssSelector("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > div:nth-child(7) > div > a > button");//By.cssSelector("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > div.actions > div.primary > a > button#top-cart-btn-checkout-new");//By.cssSelector("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > div:nth-child(4) > div > a > button#top-cart-btn-checkout-new");  //("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > div:nth-child(7) > div > button#top-cart-btn-checkout");
    public static final By PROCEED_TO_CHECKOUT_BUTTON_NL = By.cssSelector("div.sliding-panel-sections button#top-cart-btn-checkout");
    private static final By PROCEED_TO_CHECKOUT_BUTTON_IE = By.cssSelector("div.sliding-panel-sections #top-cart-btn-checkout");
    private static final By CART_SUMMERY_PROCEED_TO_CHECKOUT_BUTTON_IE = By.cssSelector(".cart-summary button.action.primary.checkout");
    public static final By PROCEED_TO_BASKET_PAGE = By.cssSelector("a.action.viewcart.primary");
    public static final By proceedToCheckoutFromBasketPage = By.cssSelector("button.action.primary.checkout");
    public By clickToCheckOutButton = By.cssSelector("div.actions > div.primary > button#top-cart-btn-checkout");
    public By PROCEED_TO_CHECKOUT = By.cssSelector("button.action.primary.checkout:nth-child(1)");
    public By PROCEED_TO_CHECKOUT_PL = By.cssSelector("button.action.primary.checkout");
    public By PROCEED_TO_CHECKOUT_VUSEDE = By.cssSelector(".action.primary.viewcart");
    public By emptyBasketIcon = By.cssSelector("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > div.minicart-items-wrapper > ol#mini-cart > li:nth-child(1) > div > div > div.product-item-name-wrap > a > i"); //("div.sliding-panel-section.sliding-minicart > div > div > div#minicart-content-wrapper > div.block-content > div.minicart-items-wrapper > ol#mini-cart > li > div > div > div.product.actions > div.secondary > a");
    public By emptyBasketIconFR = By.cssSelector(".sliding-minicart.sliding-panel-section > .minicart-wrapper  div#minicart-content-wrapper  .minicart-items-wrapper > ol#mini-cart a[title='Supprimer l’élément'] > .material-icons");
    public static final By DISPLAYED_EMPTY_BASKET_ICON = By.cssSelector("div.sliding-panel-section.sliding-minicart:not([style*='none']) div.product-item-name-wrap i");
    public By emptyBasketConfirmPopUpOk = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.confirm._show > div.modal-inner-wrap > footer > button.action-primary.action-accept");
    public By basketEmpty = By.cssSelector(".mobile-right-actions .empty span.counter-number");
    private static final By MINI_CART_BLOCK = By.cssSelector(".block-minicart");
    private static final By MINI_CART_SLIDING = By.cssSelector(".sliding-panel-sections");
    private static final By MINI_CART_AMOUNT = By.cssSelector("div:nth-child(1) div.minicart-wrapper a.action.showcart:nth-child(1) span.cart_details:nth-child(3) > span.price");
    public By ZA_MINI_CART_AMOUNT = By.cssSelector("div.column.desktop-only:nth-child(7) div.minicart-wrapper a.action.showcart:nth-child(1) div.minicart-icon-right span.cart_details:nth-child(2) > span.price");
    private static final By VUSECO_MINICART_AMOUNT = By.cssSelector("div.column.row.user-menu > div:nth-child(5) > div > a > span.cart_details > span");
    private static final By CHECKOUT_BUTTON_VELOBE = By.cssSelector("button.checkout");
    public static final By FACEBOOK_ICON_PL = By.cssSelector("div.desktop-only.desktop-footer-links a.icon-facebook");
    public static final By INSTAGRAM_ICON_PL = By.cssSelector("div:nth-child(1) > div:nth-child(3) a.icon-instagram");
    public static final By FACEBOOK_ICON_LYFT = By.cssSelector("#UF39799");
    public static final By INSTAGRAM_ICON_LYFT = By.cssSelector("#MO0M51Q");
    public static final By FACEBOOK_ICON_ON_FOOTER_LYFT = By.cssSelector("div.desktop_after.desktop-only svg[data-icon=\"facebook\"]");
    public static final By INSTAGRAM_ICON_ON_FOOTER_LYFT = By.cssSelector("div.desktop_after.desktop-only svg[data-icon=\"instagram\"]");
    private static final By INSTAGRAM_STAYCONNECTED = By.cssSelector("div.desktop-only.desktop-footer-links a.icon-instagram");
    private static final By FACEBOOK_STAYCONNECTED = By.cssSelector("div.desktop-only.desktop-footer-links a.icon-facebook");
    private static final By TWITTER_STAYCONNECTED = By.cssSelector("div.desktop-only.desktop-footer-links a.icon-twitter");
    private static By BASEKT_EMPTY_MESSAGE = By.cssSelector("div.cart-empty > div:nth-child(2) div.pagebuilder-column > div:nth-child(3) > p");
    private static By BASEKT_EMPTY_MESSAGE_VUSEIT = By.cssSelector("div.cart-empty div > p");

    public static final By BUY_PRODUCT_404 = By.cssSelector("div.pagebuilder-column-group > div:nth-child(1) > div > div > a");

    // Cookie
    private By cookiePopUp = By.cssSelector("#notice-cookie-block");
    private final By OneTrustCookiePopUp = By.cssSelector("div.ot-sdk-container>div.ot-sdk-row");
    private final By OneTrustPolicyTextLink = By.cssSelector("#onetrust-policy-text>a");
    private final static By ONE_TRUST_POLICY_TEXT_LINK_MX = By.cssSelector("#onetrust-policy-text:nth-child(2) > a:nth-child(1)");
    private final By OneTrustPolicyRefuseAll = By.cssSelector("button#onetrust-reject-all-handler");
    private final By OneTrustPolicyRefuseAllOnPrivacyCenter = By.cssSelector("#cookie-preferences > div.save-preference-btn-container > button.ot-pc-refuse-all-handler.button-theme");
    private final By OneTrustManageCookiesButton = By.cssSelector("button#onetrust-pc-btn-handler");
    private final By OneTrustAcceptCookiesButton = By.cssSelector("button#onetrust-accept-btn-handler");
    private final By OneTrustPrivacyCenterLogo = By.cssSelector("div.pc-logo");
    private final By OneTrustPrivacyCenterTitle = By.cssSelector("h2#pc-title");
    private final By OneTrustPrivacyCenterTitle_KZ = By.cssSelector("h3#pc-title");
    private final By OneTrustPrivacyCenterCloseButton = By.cssSelector(".main.pc-close-button.ot-close-icon");
    private final By OneTrustPrivacyCenterText = By.cssSelector("div#pc-policy-text");
    private final By OneTrustPrivacyCenterAcceptAllButton = By.cssSelector("#accept-recommended-btn-handler");
    private final By OneTrustPrivacyCenterStrictlyNecessaryCookies = By.cssSelector("div.ot-always-active-group");
    private final By OneTrustPrivacyCenterStrictlyNecessaryCookiesToggle = By.cssSelector("div.ot-always-active");
    private final By OneTrustPrivacyCenterConfirmMyChoiceButton = By.cssSelector("#cookie-preferences > div.save-preference-btn-container > button");
    private final static By ONETRUST_FLOATING_ICON = By.cssSelector(".floating-cookie-icon,div#ot-sdk-btn");
    private final static By ONETRUST_COOKIES_SETTINGS_FOOTER_LINK = By.cssSelector(".footer-cookie-manage-link.ot-sdk-show-settings");
    private final By OneTrustPCToggle = By.cssSelector("div.ot-switch.toggle");
    private final By OneTrustPCToggleMX = By.cssSelector("div.ot-switch.ot-toggle");
    private final By OneTrustPCCookieCategoriesDetailsLink = By.cssSelector(".category-host-list-btn.category-host-list-handler");
    private static final By OT_COOKIES_LOGO_DK = By.cssSelector("div.ot-pc-footer-logo");
    private static final By OT_COOKIES_LOGO_VELOZA = By.cssSelector("div.ot-floating-button__front > button");
    private static final By OT_COOKIES_CLOSE_BUTTON = By.cssSelector("button#close-pc-btn-handler");
    private static final By OT_COOKIES_TITLE = By.cssSelector("h2#ot-pc-title");
    private static final By OT_COOKIES_PRIVACY_TEXT = By.cssSelector("p#ot-pc-desc");
    private static final By OT_COOKIES_ALLOW_ALL_BUTTON = By.cssSelector("button#onetrust-reject-all-handler");
    private static final By OT_COOKIES_CONFIRM_CHOICE_BUTTON = By.cssSelector("button.save-preference-btn-handler.onetrust-close-btn-handler");
    private static final By OT_BANNER_COOKIES_TAB = By.xpath("//li[@class='ot-cat-item']");
    private By cookieText = By.cssSelector("#notice-cookie-block > div > p > strong");
    private By cookieText2ndPart = By.cssSelector("#notice-cookie-block > div > p > span");
    public By allowCookiesButton = By.cssSelector("button#onetrust-accept-btn-handler");
    public static By M_ALLOWCOOKIES_BUTTON = By.cssSelector("#onetrust-button-group > button:nth-child(2)");
    private final By AllowCookiesButtonBox = By.cssSelector("#btn-cookie-allow");
    private By cookiesLearnMore = By.cssSelector("#notice-cookie-block > div > p > a");
    private final static By BURGER_MENU_ITEM_1 = By.cssSelector("ol.first-menu:nth-child(2) li:nth-child(2) div.burger-menu-item.level-1 > a:nth-child(1)");
    private final static By BURGER_MENU_PRODUCT_CATEGORY_LINK = By.cssSelector("ol.first-menu:nth-child(2) li:nth-child(2) div.sub-level div.sub-level div.burger-menu-item > a:nth-child(1)");
    private final static By INFORMATION_FOOTER_FR = By.cssSelector("div:nth-child(2) > div > div > div.collapsible-title");
    private final static By ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_FR = By.cssSelector("div:nth-child(2) > div > div > div.collapsible-content > ul > li:nth-child(6)");
    private final static By DELETE_CART_LINK_LYFT_SE = By.cssSelector("#mini-cart div.secondary a");
    private final static By CONFIRM_YES_LINK_LYFT_SE = By.cssSelector("#html-body footer button.action-primary.action-accept");
    public static final By VELO_PL_ONETRUST_BLOCK = By.cssSelector("div#onetrust-pc-sdk");


    // Footer elements
    // News Letter
    public By newsLetterText = By.cssSelector("#newsletter-validate-detail > div.field.newsletter > label > span");
    public By newsLetterInputBox = By.cssSelector("#newsletter");
    public By newsLetterSubscribeButton = By.cssSelector("button.action.subscribe.primary");
    public By REGISTER_WITH_VUSE_NOW_BUTTON = By.cssSelector("button.action.subscribe.primary a");
    public static final By btnNewsLetterSubscribeUK = By.cssSelector("button.action.subscribe.primary > span");
    public By NEWSLETTER_SUBSCRIBE_BUTTON_MX = By.cssSelector("div.newsletter-container div.action > button.action.subscribe.primary > span");
    private static final By NEWSLETTER_SUBSCRIBE_VYPEIT = By.cssSelector("div.newsletter-container div.action > button.action.subscribe.primary > span");
    public By newsLetterInvalidEmailError = By.cssSelector("div#newsletter-error");
    public By newsLetterCloseButton = By.xpath("//button[@class='action primary']");
    private static final By NEWSLETTER_ERROR_MSG = By.cssSelector("#newsletter-error");
    private static final By NEWSLETTER_ERROR_MSG_PL = By.cssSelector("div.field.newsletter.required > div.control > div");
    private static final By NEWSLETTER_SUCCESS_MSG = By.cssSelector("div.message-success.success.message");
    public static final By NEWSLETTER_SUCCESSS_BLOCK = By.cssSelector("div.message.success > span");
    public static final By NEWSLETTER_SUCCESSS_BLOCK_VUSEFR = By.cssSelector("div.message-block > div.message.success > span");
    private static final By NEWSLETTER_POPUP_CLOSE = By.cssSelector("[id=close_popup] > span");
    private static final By NEWSLETTER_FIRSTNAME = By.cssSelector("#firstname");
    private static final By NEWSLETTER_SURNAME = By.cssSelector("#lastname");
    private static final By PHONE_TXT_BOX_VELOPL = By.cssSelector("input#telephone");
    private static final By SHOW_CONSENT_VELOPL = By.cssSelector("form#newsletter-validate-detail div[role='tab'] > span:nth-of-type(1)");
    private static final By CHECKBOX_ADULT_USER_VELOPL = By.cssSelector(".field.social-checks > div:nth-of-type(1) > label > span");
    private static final By CHECKBOX_CONSENT_PERSONALDATA_VELOPL = By.cssSelector(".field.social-checks > div:nth-of-type(2) > label > span");
    private static final By CHECKBOX_MARKETING_VELOPL = By.cssSelector(".consent-check_all > .choice.choice-box__container.field > label > span");
    private static final By NEWSLETTER_EMAIL = By.cssSelector("input#newsletter");
    public static final By NEWSLETTER_EMAIL_TICKBOX = By.cssSelector("input#email_marketing");
    public static final By NEWSLETTER_SIGNUP_BUTTON = By.cssSelector("[id=subscribe_news] > span");
    public static final By DATE_PICKER = By.cssSelector("button.ui-datepicker-trigger");
    private static String RANDOMNAMEDATA = randomAlphabetic(6);
    private static String RANDOMNAMEDATA2 = randomAlphabetic(6);
    private static final String RANDOMAILADDRESS = RandomGenerator.randomEmailAddress(10);
    private static final By NEWSLETTERSUBMIT = By.cssSelector("#newsletter-validate-detail > fieldset > div.actions > button > span");
    private static final By NEWSLETTER_SIGNUP_BUTTON_ZA = By.cssSelector("div.action > button.action.secondary.subscribe,button#subscribe_news");
    private static final By NEWSLETTER_EMAIL_TICKBOX_ZA = By.cssSelector("div.newsletter-agree.required > div > div > label > p");
    public final static By NEWSLETTER_DOB_FIELD = By.cssSelector("input#newsletter_dob");
    public By footerContentBlock = By.cssSelector("footer.page-footer");
    public By footerLinkClusterBlock = By.cssSelector("div.column-1-3.col-full-menu");
    public By footerLinkClusterBlockUK = By.cssSelector("div.desktop-only.desktop-footer-links");
    public static final By FOOTER_LINKS_MX = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(3) > div:nth-child(2) > ul > li");
    public By footerSocialMediaIcons = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(3) > div.social-footer,div.footer--social-media");
    public By footerPaymentOptionsContentBlock = By.cssSelector("div.payment-options");
    public By footerPrivacyContentBlock = By.cssSelector("span.privacy");
    public By newsLetterContentBlock = By.cssSelector("newsletter-container");
    public By lnkTermsAndConditionsFooterLink = By.xpath("//a[@href='/terms-of-use']");
    private final static By BUTTON_NEWSLETTERS_FOOTER = By.cssSelector("div.pagebuilder-column.newsletter-container:nth-child(4) button.action.subscribe.primary:nth-child(1)");
    private final static By ICON_NEWSLETTERS_HEADER = By.xpath("//span[text()='Nyhedsbrev']");
    private final static By ICON_NEWSLETTERS_HEADER_IE = By.cssSelector(".material-icons._at-newsletter-icon");
    private final static By FOOTER_PAYMENT_BLOCK = By.cssSelector("div.footer_top-cmsblock div.desktop-only.desktop-footer-links div.pagebuilder-column-group");
    private final static By FOOTER_PAYMENT_BLOCK_MX = By.cssSelector("div.row-full-bleed.payment-icons > div.pagebuilder-column-group > div > figure");
    private final static By FOOTER_PAYMENT_BLOCK_VUSECO = By.cssSelector("div.row-full-bleed > div > div > div > figure.payment-logo");
    public static By homePage = By.xpath("//div[@class='hero-image-content']//h2");
    public static By heroCtas = By.xpath("//div[@class='hero-image-content']//parent::div//following-sibling::div//a[contains(@class,\"pagebuilder-button\")]//span");
    private final static By JOIN_POISTIVE_CHANGE = By.cssSelector("div:nth-child(10) > div > div:nth-child(3) > div > a");
    private final static By HERE_TO_HELP = By.cssSelector("div.footer-container > div > div > div:nth-child(5) > div > div:nth-child(1) > div > div > div.collapsible-title");
    private final static By HOMEPAGE_LINK = By.cssSelector("div.cart-empty > div:nth-child(1) > div > div > div > div:nth-child(4) > div > a");
    private final static By ERROR_HOMEPAGE_LINK = By.cssSelector("div > div:nth-child(6) > div > div > div > p:nth-child(4) > a");
    private final static By ERROR_HOMEPAGE_LINK_VUSEFR = By.cssSelector("div.disc a");

    //404 Page Not Found
    public By ele404PageNotFoundPageTitle = By.xpath("//*[text()='404 page' and text()='not found' and text()='whoops,' or contains(text(),'our bad')]");
    private final static By ERROR_404_PAGE = By.cssSelector("#maincontent > div.columns > div > div.waiting-for");
    private final static By ERROR_404_MESSAGE = By.cssSelector("div.waiting-for > div > div.pagebuilder-column.pagebuilder-column-left > div:nth-child(2) > div");
    private final static By LOGIN_ACCOUNT = By.cssSelector("div.pagebuilder-column.pagebuilder-column-left > div.two-button > div.button-color > a");
    private final static By CONTINUE_SHOPPING = By.cssSelector("div.pagebuilder-column.pagebuilder-column-left > div.two-button > div:nth-child(2) > a");
    private final static By ERROR_404_PAGE_FR = By.cssSelector("div.columns > div > div.page-title-wrapper");
    private final static By ERROR_404_MESSAGE_FR = By.cssSelector("div.disc");
    private final static By ERROR_HOMEPAGE_LINK_VUSEIT = By.cssSelector("ul.disc> li:nth-child(3) > a:nth-child(2)");
    private final static By ERROR_MYACCOUNTPAGE_LINK_VUSEIT = By.cssSelector("ul.disc> li:nth-child(3) > a:nth-child(4)");

    //Terms & Conditions - FAQs
    public By TCsLeftNavigationLinksHeader = By.cssSelector("div.page-title-wrapper");
    public By btnFAQExpand = By.cssSelector("span.expand.material-icons");
    public final static By HEALTH_WARNING_FR = By.cssSelector("div.pagebuilder-column:nth-child(2) div:nth-child(1) h3:nth-child(1) > strong:nth-child(1)");
    public final static By HEALTH_WARNING_KZ = By.cssSelector("div.health-warning-container>div>div>div>p");
    //conditions of sale
    private final static By CONDITIONS_OF_SALE = By.cssSelector("div > div.page-title-wrapper");

    // privacy link
    public By eleInformationFooterSection = By.cssSelector("div.row.footer-top div.footer_top-cmsblock div.pagebuilder-column-group:nth-child(4) div.pagebuilder-column div.pagebuilder-collapsible div.collapsible-title > i.expand.material-icons:nth-child(2)");
    public By privacyLink = By.linkText("Privacy Policy");
    // checkout Popup
    private By checkOutSignInPopUp = By.cssSelector("form.form.form-login");//By.cssSelector("body > div.modals-wrapper > aside > div.modal-inner-wrap > header");
    private final static By GUEST_CREATE_AN_ACCOUNT_LINK_CX_REDESIGN = By.cssSelector("a.action.create.primary");
    private By checkOutPopUpCreateAnAccountLink = By.cssSelector("a.action.action-register.primary");
    private By checkOutPopUpEmailAddressInput = By.cssSelector("input#customer-email.input-text,input#email");
    private By UPDATE_ID_VELOZA = By.cssSelector("#btn-age-verification-consent");
    private By checkOutPopUpPasswordAddressInput = By.cssSelector("input#pass.input-text,#login-form > fieldset div.field.password.required > div > input");
    private By checkOutPopUpSignInButton = By.cssSelector("button#send2"); //.action.login.primary");
    private By hamBurgerTable = By.cssSelector("body > div.burger-menu-container > div > div:nth-child(2) > div > div > ol");//("ol.burger-menu>li");
    private By closeHamburgerMenuIcon = By.cssSelector("body > div.burger-menu-container > div > div.close > i"); //("body > div.burger-menu-container > ol > li.close");
    private final static By CHECKOUT_CARTRIDGE_POPUP = By.cssSelector("aside.modal-popup.mx-checkout-cartridge-popup.modal-slide._inner-scroll._show div.entry-checkout-confirmation");
    private final static By CHECKOUT_CARTRIDGE_POPUP_CLOSED = By.cssSelector("aside.modal-popup.mx-checkout-cartridge-popup.modal-slide._inner-scroll._show div.entry-checkout-confirmation");
    private final static By CARTRIDGE_POPUP_SEE_CARTRIDGES_BUTTON = By.cssSelector("aside.modal-popup.mx-checkout-cartridge-popup.modal-slide._inner-scroll._show button.action.secondary > span");
    private final static By CARTRIDGE_POPUP_CONTINUE_WITH_PAYMENT_BUTTON = By.cssSelector("aside.modal-popup.mx-checkout-cartridge-popup.modal-slide._inner-scroll._show button.action.primary > span");
    private final static By CARTRIDGE_POPUP_CLOSE_BUTTON = By.cssSelector("aside.modal-popup.mx-checkout-cartridge-popup.modal-slide._inner-scroll._show button.action-close");
    public final static By PAGE_TITLE_WRAPPER = By.cssSelector("[data-ui-id='page-title-wrapper'");
    public final static By GRAND_TOTAL = By.cssSelector(".grand.totals.incl");
    public final static By PROCEED_TO_CHECKOUT_BUTTON = By.cssSelector("button.action.primary.checkout");
    public final static By AGE_VERIFICATION_CONSENT_BUTTON = By.cssSelector("#btn-age-verification-consent");
    private By MXCreditCardOption = By.cssSelector("#tns_hosted");
    public By MXsearchIcon = By.cssSelector(UrlBuilder.getMessage("searchIconRef.key"));
    public By VypeITsearchIcon = By.cssSelector("input#search");
    public final static By SUBSCRIPTION_ERROR_MESSAGE = By.cssSelector("#js--invalid-qty-basket > div");
    public final static By HEALTH_WARNING_BANNER = By.cssSelector("div.health-warning-container > div > div > div:nth-child(1)");
    //    private final static By HOMEPAGE_LINK = By.cssSelector("div:nth-child(6) > div p:nth-child(4) > a");
    private final static By HOMEPAGE_BANNER = By.cssSelector("div:nth-child(6) > div:nth-child(2) > div > div:nth-child(2) > div");

    //Mini Cart/Mini Basket
    public By QtyChangeField = By.cssSelector("div.control.qty");
    public By colorSwatchOption = By.xpath("//*[@class='product-item-name']//following::span[@class='swatch-option color'][3]");
    public By productStrengthOption = By.xpath("//*[@class='product-item-name']//following::span[@class='swatch-attribute vype_strength'][3]");
    public By btnAddToBasketMiniCart = By.xpath("//*[@class='product-item-name']//following::button[@class='action tocart primary'][3]");
    public By elePricePerPodText = By.xpath("//*[@class='product-item-name']//following::span[@class='minicart-price'][3]");
    public By btnRemoveMiniBasket = By.cssSelector("button.action.primary.remove.button-flavoured");
    public By popUpRemoveItemFromBasket = By.cssSelector("div.action-items-container > p.modal-text");
    public By btnRemoveItemPopUp = By.cssSelector("a.action.action-delete > span:nth-child(1)");
    public By btnContinueShoppingMiniBasket = By.cssSelector("a.pagebuilder-button-secondary > span:nth-child(1)");
    public By eleBasketEmptyMessage = By.cssSelector("div.pagebuilder-column:nth-child(1) div:nth-child(1) div:nth-child(1) > div:nth-child(1)");
    public By btnMoveToWishListPopUp = By.cssSelector("a.use-ajax.action.towishlist.action-towishlist > span:nth-child(1)");
    public By btnFavouriteIconHeader = By.cssSelector("i.material-icons._at-fav-icon");
    private static final By MINI_BASKET_SECTION_TABLE = By.cssSelector("#shopping-cart-table");
    private static final By CART_TOTALS = By.cssSelector(".cart-totals");
    public By btnIncreaseItemQty = By.cssSelector("span.qty-modifier.plus.button-plus-1.icon-plus");
    public By btnIncreaseProductQty = By.cssSelector("span.qty-modifier.plus.button-plus-1.icon-plus");
    public By btnDecreaseItemQty = By.xpath("//*[contains(@class,'qty-modifier button-minus')]");
    public By inputItemQuantity = By.cssSelector(".input-text.qty");
    public By eleApplyDiscountCode = By.cssSelector("#coupon_code");
    private final static By APPLY_DISCOUNT_CODE = By.cssSelector(".action.apply.primary");
    public By btnBack = By.cssSelector("#btn-minicart-close");
    public final static By PLP_ADDTOCART_BUTTON = By.cssSelector("button.action.tocart.primary");
    private final static By HEADER_KONTAKT_ICON = By.cssSelector("li.userbar-item.chat:nth-child(2) a:nth-child(1) > span.icon-text");
    private final static By HEADER_CONTACT_ICON_VELOZA = By.cssSelector("header > div.bat-header-support > div > a");
    private final static By CONTACT_LINK_VELOPL = By.cssSelector("div:nth-of-type(2) > div:nth-of-type(2) > p:nth-of-type(1) > a");
    public final static By MINICART_BACK_BUTTON = By.cssSelector("button#btn-minicart-close");
    private final static By FIRST_ADDTOCART_BTN_LYFTSE = By.cssSelector(".action.tocart.primary.mini-royal-purple");
    public static final By INPUT_ITEM_QUANTITY_SUBS = By.cssSelector("input#qty");
    public static final By INPUT_ITEM_QUANTITY_CART = By.cssSelector("div.control.qty input:nth-child(2)");
    public static final By CLICK_NEXT_SLIDE = By.cssSelector(".product-options-bottom .actions");
    public static final By SELECT_ITEM_QUANTITY_SUBSSE = By.cssSelector("select#qty");
    public static final By GLOBAL_SUBS_MINI_CART_BANNER = By.cssSelector(".product-item-details .subscription-label");
    public static final By GLOBAL_SUBS_MINI_CART_BANNERSE = By.cssSelector(".product .content span");
    public static final By LOADING_CIRCLE = By.cssSelector("img[alt='Chargement en cours...']");
    public static final By LOADING_CIRCLESE = By.cssSelector("img[alt='Laddar...']");
    public static final By LOADING_CIRCLEVUSE_DE = By.cssSelector("img[alt='Wird geladen …']");
    public static final By LOADING_CIRCLEVYPEIT = By.cssSelector("img[alt='Caricamento...']");
    public static final By LOADING_CIRCLEVUSEUK = By.cssSelector("img[alt='Loading...']");
    public static final By LOADING_CIRCLEVUSECO = By.cssSelector("img[alt='Cargando...']");
    public static final By GLOBAL_SUBS_ORDER_SUBS_FIRST_CTA = By.cssSelector(".action.basket-exclusivity-btn.btn-small.primary.subscription");
    public static final By GLOBAL_SUBS_ORDER_SUBS_FIRST_LOGGEDIN_CTA_VUSEFR = By.cssSelector(".action.basket-exclusivity-btn.btn-small.first-subscription.primary");
    public static final By GLOBAL_SUBS_ORDER_SUBS_FIRST_LOGGEDIN_CTA = By.cssSelector(".action.basket-exclusivity-btn.btn-small.first-subscription.primary > span");
    public static final By GLOBAL_SUBS_ORDER_NON_SUBS_CTA_VUSEFR = By.cssSelector(".action.basket-exclusivity-btn.btn-small.onetime.primary > span");
    public static final By GLOBAL_SUBS_ORDER_NON_SUBS_CTA = By.cssSelector(".action.basket-exclusivity-btn.btn-small.onetime.secondary > span");
    public static final By GLOBAL_SUBS_ORDER_NON_SUBS_DISCOUNT_TEXT = By.xpath("/html//div[@class='modals-wrapper']/aside[7]//div[@class='modal-content']/div");
    public static final By GLOBAL_SUBS_ORDER_NON_SUBS_CONFIRMATION_CTA = By.cssSelector(".action-accept.action-primary");
    public static final By GLOBAL_SUBS_POLICY_LINK = By.cssSelector("div:nth-of-type(2) > p:nth-of-type(1) > a:nth-of-type(2)");
    public static final By GLOBAL_SUBS_ERROR_MESSAGE_DISPLAY = By.cssSelector("div[role='alert'] > div");
    public static final By GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSECO = By.cssSelector("div#custom-message-subs > div");
    public static final By GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSEFR = By.cssSelector("div.error");
    public final static By VUSE_DE_About_VUSE_LINK = By.cssSelector("div.custom-categories > div > div > ul > li:nth-child(8) > a");
    private final static By VUSE_DE_THIRTY_DAYS_LINK = By.cssSelector("[href*='/de/de/30tagetesten']");
    private final static By VUSE_DE_TO_INVITE_FRIENDS_LINK = By.cssSelector("[href*='/de/de/freunde-einladen']");
    private final static By VUSE_DE_PERSONAL_DATA_LINK = By.cssSelector(".nav.item>a");
    private final static By VUSE_UK_PERSONAL_DATA_LINK = By.cssSelector("#account-nav > ul > li:nth-child(2) > a");
    private final static By VUSE_ZA_PERSONAL_DATA_LINK = By.cssSelector("#account-nav > ul > li:nth-child(4) > a");
    private static final By M_CLICK_NEXT_SLIDE = By.cssSelector("div.field.qty > div > span.qty-modifier.plus.button-plus-1.icon-plus");

    //Product Carousel Fields
    public static final By TOTAL_GIFT_OFFER = By.cssSelector("span.ampromo-counter");
    public static final By ADD_GIFT_OFFER = By.cssSelector("span[class*='qty-modifier am-qty-plus button-plus']");
    public static final By ADD_FREE_GIFT_BUTTON = By.cssSelector("button.action.tocart.primary.ampromo-button");
    public static final By ADD_FREE_GIFT_BUTTON_DISABLED = By.cssSelector("button[class='action tocart primary ampromo-button'][disabled='disabled']");
    private final static By PRODUCTS_GRID_CAROUSEL = By.cssSelector("div.products-grid.grid");
    private final static By PRODUCT_CAROUSEL_QTY = By.cssSelector("[name='qty'][tabindex='0']");
    private final static By PRODUCT_CAROUSEL_ADDTOCART = By.cssSelector("[class='action tocart secondary'][tabindex='0']");
    public final static By CAROUSEL_ARROW_NEXT = By.xpath("//ol[contains(@class,'slick-initialized slick-slider slick-dotted')]//button[@class='slick-next slick-arrow']");
    public final static By CAROUSEL_ARROW_PREV = By.xpath("//ol[contains(@class,'slick-initialized slick-slider slick-dotted')]//button[@class='slick-prev slick-arrow']");
    private final static By FOOTER_CUSTOMER_INFO = By.cssSelector("ul.footer-menu li:nth-child(2) > p:nth-child(1)");
    private final static By CONTACT_FORM_PAGE = By.cssSelector("#contact-form");
    private final static By PLP_STRENGTH_FILTER = By.xpath("//div[@class='filter-options-title'][contains(text(),'Styrke')]");
    private final static By PLP_SYSTEM_FILTER = By.xpath("//div[@class='filter-options-title'][contains(text(),'System')]");
    private final static By PLP_PRODUCT_LIST = By.cssSelector("#product-list-plp");
    private final static By PLP_PRODUCT_QTY = By.cssSelector("div.field.qty");
    private final static By ADD_NEW_ADDRESS = By.cssSelector("div.box.box-billing-address:nth-child(1) div.box-actions a.action.edit > span:nth-child(1)");
    private final static By BUTTON_ADDRESS_SAVE = By.cssSelector(".action.submit.primary");
    private final static By ADDRESSBOOK_LINK = By.cssSelector(".item [href*='customer/address/']");
    public static final By PROCEED_TO_CART_PAGE = By.cssSelector(".sliding-minicart.sliding-panel-section > .minicart-wrapper  div#minicart-content-wrapper .action.secondary.viewcart > span,a.action.viewcart.primary");
    public static final By PROCEED_TO_CART_PAGESE = By.cssSelector(".action.primary.subs-on.viewcart");
    public static final By CHECKOUT_CART_BUTTON = By.cssSelector("button[title='Commander'],.action.primary.checkout");
    public static final By CHECKOUT_CART_BUTTON_FR = By.cssSelector("li.item button[title='Commander'],.action.primary.checkout:nth-child(1)");
    public static final By CHECKOUT_CART_BUTTONSE = By.cssSelector("button[title='Till Kassan']");
    public static final By AVALANCHE_CHECKOUT_BUTTON = By.cssSelector("button.action.primary.checkout");
    public static final By LOADER = By.cssSelector(".loader");
    private final static By ADDRESS_BOOK_ITEMS = By.cssSelector("#account-links-collapsible li.nav.item:nth-child(3)");
    private final static By M_ADDRESS_BOOK_ITEMS_GLOPL = By.cssSelector("div.content.accordion-content.account-nav-content > ul.nav.items > li:nth-child(3) > a");
    private final static By ADD_NEW_ADDRESS_BUTTON = By.cssSelector("button.action.primary.add");
    private final static By ELIQUID_LINK_VUSE_FR = By.cssSelector(".category-navigation [href*='/fr/fr/e-liquides']");
    private final static By CLOSE_ADD_NEW_ADDRESS_BUTTON_GLO_PL = By.cssSelector("aside.modal-popup.modal_edit_address.address_edit_new.modal-slide._inner-scroll._show header > button");
    private final static By ABOUT_US_LINK = By.cssSelector(".header-content [href*='/se/sv/about-us']");
    private final static By ABOUT_US_LINK_GLO_KZ = By.cssSelector(".category-navigation [href*='about-glo/']");
    private final static By ABOUT_VUSE_LINK_VUSE_ZA = By.cssSelector(".level0.category-item.icon-vuse.sixth [href*='/za/en/about-us']");
    private final static By ABOUT_VUSE_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/vype-wird-vuse/']");
    private final static By ABOUT_VUSE_LINK_VUSE_CO = By.cssSelector(".category-item a[href*='co/es/informacion-de-la-empresa']");
    private final static By ABOUT_VUSE_FOOTER_LINK_VUSE_DE = By.cssSelector(".footer-container a[href*='/de/de/ueber-uns/']");
    private final static By SHOP_LYFT_LINK = By.cssSelector(".header-content [href*='/se/sv/nikotinpasar']");
    private final static By LYFT2VELO_LINK = By.cssSelector(".header-content [href*='/se/sv/velo']");
    private final static By COKKIE_BANNER = By.cssSelector("div.banner-actions-container>button");
    private final static By COUPON_CODE_SECTION = By.cssSelector("div#block-discount");
    private final static By SEARCH_TEXT_VUSE_DE = By.cssSelector(".header-vype.row #search");
    private final static By SEARCH_BUTTON_VUSE_DE = By.cssSelector(".row .action.search");
    private final static By NO_SEARCH_MESSAGE_VUSE_DE = By.cssSelector("#maincontent > div.columns > div > div.no-search-result > div");
    private final static By MINI_BASKET_VUSE_DE = By.cssSelector("div.header-link a");


    //SignUp Popup
    private final static By SIGN_UP_POPUP = By.xpath("//div[@class='visitor-popup-wrapper'][contains(@style,'block')]//div[@class='visitor-popup']");
    private final static By SIGN_UP_POPUP_HEADER_TEXT = By.cssSelector("div[class='heading'] p");
    private final static By SIGN_UP_POPUP_JOIN_NOW_BUTTON = By.cssSelector("div.registeration>a");
    private final static By ACCOUNT_SET_UP_PAGE = By.cssSelector("div[class='register-main-info-wrapper']");
    public static final By HEADER_ITEM_FIRST = By.cssSelector(".level0.category-item.column.first>a");
    public static final By HEADER_ITEM_FIRST_GLO = By.cssSelector(".level-top.ui-corner-all");
    public static final By MENU_ITEM_FIRST_GLO = By.cssSelector(".nav-1 .nav-1 span");
    public final static By EMAIL_INPUTBOX = By.cssSelector("input#email.input-text");
    public final static By PASSWORD_INPUTBOX = By.cssSelector("input#pass.input-text");
    public final static By SIGNIN_BUTTON = By.cssSelector("#send2");
    public By elePageTitle = By.cssSelector("span.base");
    public static final By LYFT_TAB = By.cssSelector("ul.lyft-header-top:nth-child(1) li.Lyft-link > a.top-link.active");
    public static final By TRACK_ORDER_BOX_ICON = By.cssSelector("div.column.desktop-only>a");
    public static final By FAQ_COLLAPSE_ICON = By.cssSelector("div.collapsible-title");
    public static final By FAQ_CONTENT = By.cssSelector("div.collapsible-content");
    public static final By BLOG_LYFT_LAB = By.cssSelector("div.column-6.footer-nav.desktop-only div:nth-child(1) div:nth-child(1) ul.footer-menu:nth-child(1) li:nth-child(3) > a:nth-child(1)");
    public static final By READ_MORE = By.cssSelector("a.post-read-more");
    public static final By BLOG_ITEM_TEXT = By.cssSelector("a.post-item-link");
    public static final By BLOG_ITEM_HEADER_TEXT = By.cssSelector("span.base");
    public static final By BLOG_ITEM_CONTENT = By.cssSelector("div.post-content");
    public static final By PHONE_NUMBER_FIELD_ERROR = By.cssSelector("#telephone-error");
    public static final By WHAT_IS_LYFTLAB_HEADER = By.cssSelector("div.widget.block.block-static-block div.edt-02-video-rhs:nth-child(1) div.pagebuilder-column-group div.pagebuilder-column:nth-child(2) div:nth-child(1) h2:nth-child(1) > span:nth-child(1)");
    public final static By WHAT_IS_LYFTLAB_CONTENT = By.cssSelector("div.widget.block.block-static-block div.edt-02-video-rhs:nth-child(1) div.pagebuilder-column-group div.pagebuilder-column:nth-child(2) div:nth-child(2) p:nth-child(1) > span:nth-child(1)");
    public final static By LYFT_LAB_COLLECTION_ITEM = By.cssSelector(".product-items.widget-product-grid");
    public final static By MY_PREFEENCES = By.cssSelector("div.block.tests.account-nav div.account-links div.content.accordion-content.account-nav-content ul.nav.items > li.nav.item.current:nth-child(8)");
    public final static By MOBILE_CONSENT_OPTION = By.cssSelector("#subscription");
    public final static By MOBILE_CONSENT_CONFIRMATION_MESSAGE = By.xpath("//div[@role='alert']");
    public final static By SAVE_BUTTON_FOR_MOBILE_CONSENT = By.xpath("//button[contains(@class,'action save')]");
    public static final By SLICK_LIST = By.cssSelector(".slick-list");
    private static final By CAROUSEL = By.cssSelector(".homepage-feature-carousel, .slick-list.draggable");
    private static final By CAROUSEL_FR = By.cssSelector(".homepage-feature-carousel");
    private static final By CAROUSEL_GLO = By.cssSelector(".slick-slider.slick-dotted");
    private static final By GLO_PRODUCT_SECTION = By.cssSelector(".pagebuilder-column-group:nth-child(2)");
    private static final By HYPER_SLIDE_ITEMS = By.cssSelector(".hyper-slide-items");
    private static final By HYPER_DISCOVER_ITEMS = By.cssSelector(".hyper-discover-items");
    private static final By CAROUSEL_DOTS = By.cssSelector(".vype-dk-homepage-carousel-hero .slick-dots, .slick-dots");
    private final static By COOKIE_BANNER = By.cssSelector("#onetrust-banner-sdk");
    private final static By LYFT_SE_COOKIE_BANNER = By.cssSelector(".ot-sdk-container");
    private final static By AGE_GATE = By.cssSelector(".entry-age-confirmation-wrapper");
    private final static By AGE_GATE_VELO_BE = By.cssSelector("#ageGate .bat-agegate--default-content");
    private final static By AGE_GATE_JP = By.cssSelector("div.bat-modal-content");
    private static final By REMOVE_BUTTON = By.cssSelector("button.action.primary.remove.button-flavoured");
    private static final By TRAIL_REMOVE_BUTTON = By.cssSelector("button.action.primary.remove.button-flavoured");
    private static final By TRAIL_REMOVE_POPUP_BUTTON = By.cssSelector(".modal-footer button.action.primary");
    private static final By VYPE_REMOVE_BUTTON_NL = By.cssSelector(".remove-from-basket-action.actions-toolbar>i");
    private static final By REMOVE_POPUP_BUTTON = By.cssSelector("a.action.action-delete");
    public final static By HEADER_TOP = By.cssSelector(".header-top");
    public final static By MESSAGE_ROW = By.cssSelector(".message-row");
    public final static By LIQUID_LINK = By.cssSelector("a.column");
    private static final By DYNA_IMAGE = By.cssSelector(".VideoContainer [data-content-type='image']");
    private static final By LYFT_SE_DYNA_IMAGE = By.cssSelector("[role='tab'],.slick-track,.widget.block.block-static-block,div.pagebuilder-column.VideoContainer,div.pagebuilder-overlay.pagebuilder-poster-overlay");
    public final static By VYPE_LIQUID_DESC_FR = By.cssSelector("div.product.product-short-description");
    public final static By SECOND_MENU = By.cssSelector("span.payment-section button");
    private static final By VUSE_NEWS_SECTION = By.xpath("//div[@class='vype-cms-news']");
    private static final By VUSE_DE_ACCESSIBILITY_LINK = By.cssSelector("[href*='barrierefreiheit']");
    public static final By MAXIMIZED_TRUST_BADGE = By.xpath("//div[contains(@id,'maximized-trustbadge')]");
    public static final By TRUST_LINKS = By.xpath("//div[contains(@id,'close-button')]//following::div[contains(@id,'contentBoxWithLink')]");
    public static final By CLOSE_BTN = By.xpath("//div[contains(@id,'close-button')]");
    public static final By TRUST_BADGE = By.xpath("(//div[contains(@id,'trustbadge')])[2]");
    public static final By MINI_BANNER = By.xpath("//div[@class='vype-homepage-mini-banner']");
    public static final By FOOTER_LINKS = By.xpath("//div[@class='footer_top-cmsblock']//div[@class='pagebuilder-column']//div//p//a");
    public static final By STORE_LOCALTOR_ICON = By.xpath("//a[@class='header-link header-link--stores icon-stores']");
    private static final By STORE_LOCALTOR_GLO_IT = By.cssSelector(".navigation [href*='/it/it/store-locator/']");
    private static final By STORE_LOCALTOR_GLO_DE = By.cssSelector(".header-link.header-link--stores.icon-stores");
    private static final By STORE_LOCALTOR_VELOZA = By.cssSelector("header > div.bat-header-stores > div > a");
    private static final By STORE_LOCALTOR_VELOPL = By.cssSelector(".header-link.header-link--stores.icon-stores");
    private static final By QUANTITY_PDP_UK = By.cssSelector("#qty");
    private static final By PROMOCODE_ERROR = By.cssSelector("div.message.message-error.error");
    private static final By APPLY_DISCOUNT_BUTTON = By.xpath("//button[contains(@class,'action apply')]");
    public static final By OT_COOKIE_ICON = By.cssSelector("div#ot-sdk-btn");
    public static final By COOKIE_SETTINGS_FOOTER_LINK = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(3) > div:nth-child(2) > p:nth-child(5) > a");
    private static final By OT_COOKIES_MANAGEMENT_POP_UP = By.cssSelector("div#ot-content");
    public static final By INDIVIDUAL_COOKIE_SETTING_LINK = By.cssSelector("a.ot-sdk-show-settings");
    public static final By VUSE_DK_UPDATE_COOKIE_SETTING_LINK = By.cssSelector(".footer-ot-toggle span");
    public static final By VUSE_DE_RIGHT_OF_WITHDRAWAL_LINK = By.cssSelector("[data-pb-style='ANHCPFB'] p:nth-child(3) a");
    public static final By VUSE_DE_SATISFACTION_GUARANTEED_LINK = By.cssSelector("[data-pb-style='ANHCPFB'] p:nth-child(2) a");
    public static final By VUSE_DK_REQUIRED_COOKIE_LINK = By.cssSelector("#ot-header-id-C0001");
    public static final By INDIVIDUAL_COOKIE_SETTING_POPUP = By.cssSelector("div #ot-content");
    private static final By M_PROCEED_TO_CHECKOUT_BUTTON = By.cssSelector("li.item > button.action.primary.checkout:nth-child(1),button.action.primary.checkout");
    private static final By VUSE_DE_MINICART_LINK = By.cssSelector(".secondary a");
    private static final By VUSE_DE_CHECKOUT_LINK = By.cssSelector(".action.primary.checkout");
    private static final By VUSE_DE_SHIPPING = By.cssSelector("#shipping");
    private static final By M_PROCEED_TO_CHECKOUT_BUTTON_MX = By.cssSelector("a.action.viewcart.primary");
    private static final By M_LIQUIDS_PRODUCT_LINK = By.cssSelector("div.burger-menu ol.first-menu div.sub-level div.burger-menu-item.level-1:nth-child(2) > a:nth-child(1)");
    public static final By LOADER_ICON = By.cssSelector("div.loading-mask");
    private static final By ICONBAG_LOADER_ICON = By.cssSelector("span.icon-bag div.loading-mask");
    private static final By MINI_CART_OPEN = By.cssSelector(".action.showcart.active");
    private static final By CART_ICON_VELO = By.cssSelector("div.cart-icon");
    // BLOG
    private static final By BLOG_CONTENTS_TABLE = By.cssSelector("#maincontent > div.columns > div > div.post-list-wrapper > ol > li");
    private static final By PROCEED_TO_VIEWBASKET = By.cssSelector("a.viewcart");
    private static final By EMPTY_CART_BUTTON = By.cssSelector("[name=clear_cart_action] > span");
    private static final By BLOG_GLO_EVENT_LINK = By.cssSelector("li.post-holder.post-holder-444 > div > div > a");
    private static final By BLOG_GLO_SECTION = By.cssSelector("a[href=\"/pl/pl/blog\"]");
    private static final By BLOG_FLAVOR_LINK = By.cssSelector("li.post-holder.post-holder-443 > div > div > a");
    private static final By BLOG_GLO_STORE_LINK = By.cssSelector("li.post-holder.post-holder-442 > div > div > a");
    public static final By GO_TO_MY_ACCOUNT = By.cssSelector("a.action.primary.account");
    public static final By MY_ACCOUNT_KZ = By.cssSelector("ul.dropdown.account-dropdown > li:nth-child(1) > a");
    private static final By CATEGORIES_DROPDOWN_VYPEIT = By.cssSelector("select#selectbox");
    private static final By ADVANT_CALENDAR_MENU_FR = By.cssSelector("li[class*='icon-blog'] a[href*='calendrier']");
    public static final By GIVE_FEEDBACK_BUTTON_ALERT = By.cssSelector("div.qprimary");
    //Vype is becoming Vuse popup
    public static final By CONTINUE_BUTTON_VUSE_ALERT = By.cssSelector("div.button-row a.pagebuilder-button-primary.close-migration-popup");
    private static final By M_SIGN_IN_LINK_FROM_PERSON_MENU = By.cssSelector("ul.dropdown.account-dropdown li.view-favorite.switcher-option > a:nth-child(1)");

    //Loyalty Page (DE Insiders Club)
    public static final By MY_REWARD_MENU_OPTION = By.cssSelector(".nav.item a[href*='de/insiders-club/benefit/']");

    //Loyalty Page (IT Insiders Club)
    public static final By IT_INSIDERSCLUB_MENU_OPTION = By.cssSelector(".nav.item a[href*='it/insiders-club/benefit/']");
    public static final By IT_DEVICE_REGISTER_POPUP = By.cssSelector("#modal > div.modal-body-content > form[name='device_register_page']");
    public static final By IT_POPUP_CLOSE_BUTTON = By.cssSelector("div.modal-inner-wrap > header > button");
    private static final By IT_INSIDERS_CLUB_HEADER_OPTION = By.cssSelector("#ui-id-1 > li:nth-child(4) > a");
    private static final By M_IT_INSIDERS_CLUB_HEADER_OPTION = By.cssSelector("div.burger-menu-container > ol > li:nth-child(5) > a");

    private static final By MYACCOUNT_SUBSCRIPTION = By.cssSelector("#account-nav > ul > li:nth-child(5) > a");

    public String getCartQty() {
        return webDriver.findElements(basketQty).get(0).getText();
    }

    //Attention Dialog
    public static final By OK_BUTTON_ATTENTION_ALERT = By.cssSelector("button.action-primary.action-accept");
    public static final By ATTENTION_ALERT = By.xpath("//h1[contains(text(), 'Attention')]");
    private static final By RECEIVE_MARKETING_EMAIL_CHECKBOX = By.cssSelector("#email_marketing");
    private static final By SELF_DECLARATION_CHECKBOX = By.cssSelector("#self_declaration_av");
    private static final By NEWSLETTER_VERIFY_BUTTON = By.cssSelector("#newsletter-validate-detail > fieldset > div.actions > button");
    private static final By AGREE_NEWSLETTEREMAIL_CHECKBOX = By.cssSelector("#newsletter-validate-detail > fieldset > div.newsletter-agree.required > label");
    private static final By NEWSLETTER_SUBSCRIBE_BUTTON = By.cssSelector("#newsletter-validate-detail > fieldset > div.actions > button > span");
    private static final By INPUT_NEWSLETTER = By.cssSelector("input#newsletter");
    private static final By NEWS_LETTER = By.cssSelector("#newsletter");
    private final static By NEWS_LETTER_POPUP_MX = By.cssSelector("aside.modal-popup.modal.newsletter-popup.modal-slide._inner-scroll._show");
    private final static By CHECKOUT_VUSELOGO_VUSECO = By.cssSelector("div.column.logo-container > a");
    private final static By NEWS_LETTER_POPUP_MX_IT = By.cssSelector("aside.modal-popup.modal.newsletter-popup.modal-slide._inner-scroll._show");
    private final static By NEWSLETTER_DOB_VUSE = By.cssSelector("input#newsletter_dob");
    private static final By CHECKOUT_VYPEIT_LOGO = By.cssSelector("div.column.logo-container > a");
    private static final By TESTIMONIALS_BLOG_VYPEIT = By.cssSelector("select#selectbox > option:nth-child(2)");
    private static final By SCIENCECATEGORY_BLOG_VYPEIT = By.cssSelector("select#selectbox > option:nth-child(3)");
    private static final By VYPESTYLE_BLOG_VYPEIT = By.cssSelector("select#selectbox > option:nth-child(4)");
    private static final By CHECK_DECLARATION_BAT = By.cssSelector("div.field.newsletter.newsletter-trms-error:nth-child(6) > label:nth-child(2),label.choice-box__label");
    private static final By CHECK_TELEPHONE_WHATSAPP = By.cssSelector("div.social-right > label:nth-child(2)");
    private static final By NEWSLETTER_BUTTON = By.cssSelector("button#subscribe_news");
    private static final By LEFT_NEWSLETTER_BUTTON = By.cssSelector("div.social-left.newsletter > label >span");
    private static final By BOTTOM_NEWSLETTER_BUTTON = By.cssSelector("div.actions:nth-child(9) > button.action.subscribe.primary,button.action.subscribe.pagebuilder-button-primary");
    private static final By SECURE_PURCHASE_ALERT = By.cssSelector("div[id*='close-button']");
    private static final By NUMBER_LINK = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(5) p:nth-child(1) > a");
    private static final By NUMBER_LINK_VELOZA = By.cssSelector("div.bat-footer-avalanche-submenu.submenu.need-help > ul > li:nth-child(1) > div > span");
    private static final By EMAIL_LINK = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(5) > div:nth-child(2) > p:nth-child(2) > a");
    private static final By EMAIL_LINK_VELOZA = By.cssSelector("div.bat-footer-avalanche-submenu.submenu.need-help > ul > li:nth-child(2) > div > a");
    private static final By PLP_ITEM = By.cssSelector("#product-list-plp > li:nth-child(2) > div > a > span > span > img");
    private static final By SUBS_LINK = By.cssSelector("li:nth-child(2) > div > div.product.details.product-item-details > div > div > div > div.enhanced-cta > p > a");
    private static final By SUBS_LINK_VUSE_UK = By.cssSelector(".category-navigation a[href*='/gb/en/subscriptions']");
    private static final By SUBS_LINK_LYFT_SE = By.cssSelector(".header-content [href*='prenumerationer']");
    private static final By SUBS_LINK_GLO_PL = By.cssSelector(".level0.category-item.seventh a[href*='pl']");
    private static final By SUBS_LINK_VUSE_FR = By.cssSelector(".level0.category-item.icon-subscriptions.forth a[href*='/fr/fr/abonnement']");
    private static final By SUBS_LINK_VUSE_CO = By.cssSelector(".category-item a[href*='/co/es/suscripcion']");
    private static final By CIGARETTES_LINK_FR = By.cssSelector(".level0.category-item.icon-devices.first a[href*='/fr/fr/cigarettes-electroniques']");
    private static final By LIQUIDS_LINK_VUSE_IT = By.cssSelector(".category-item a[href*='/it/it/liquidi-sigaretta-elettronica']");
    private static final By CIGARETTES_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/e-zigaretten/']");
    private static final By LIQUIDS_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/e-liquid-kaufen/']");
    private static final By VUSE_RELOAD_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/vuse-reload/']");
    private static final By EQUIPMENT_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/zubehoer/']");
    private static final By KNOW_YOUR_VAPE_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/know-your-vape/']");
    private static final By NICOTINE_FREE_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/dampfen-ohne-nikotin/']");
    private static final By SHOP_DEVICES_UK = By.cssSelector(".level0.category-item.icon-devices.first [href*='e-cigarette-devices']");
    private static final By SHOP_DEVICES_VUSE_ZA = By.cssSelector(".level0.category-item.icon-vuseza-shop-devices.first [href*='e-cigarette-devices']");
    private static final By PROMOS_LINK_VUSE_ZA = By.cssSelector(".level0.category-item.icon-megaphone.third a[href*='#']");
    private static final By LOGO_VUSE_ZA = By.cssSelector(".column.logo-container a");
    private static final By SHOP_DEVICES_FR = By.cssSelector(".level0.category-item.icon-devices.first [href*='cigarettes-electroniques']");
    private static final By HEATED_STICKS_LINK = By.cssSelector(".category-navigation [href*='kz/ru/nagrevaemye-stiki-dlja-glotm']");
    private static final By PERSONAL_PRESENTATION_LINK = By.cssSelector(".category-navigation [href*='personal-presentation']");
    private static final By ABOUT_DEVICES_LINK = By.cssSelector(".category-navigation [href*='kz/ru/about-devices']");
    private static final By KNOW_YOUR_VAPE_LINK = By.cssSelector(".category-navigation [href*='know-your-vape']");
    private static final By ALL_ABOUT_VAPING_LINK = By.cssSelector(".category-navigation [href*='/fr/fr/tout-savoir-sur-la-vape']");
    private static final By TRANSPARENCY_LINK = By.cssSelector("[href*='know-your-vape/transparency/']");
    private static final By TRANSPARENCY_LINK_VUSE_FR = By.cssSelector(".main [href*='/fr/fr/tout-savoir-sur-la-vape/transparence']");
    private static final By RESPONSIBILITY_LINK = By.cssSelector("[href*='know-your-vape/responsibility/']");
    private static final By RESPONSIBILITY_LINK_VUSE_FR = By.cssSelector(".main [href*='/fr/fr/tout-savoir-sur-la-vape/responsabilite']");
    private static final By VAPING_UNPACKED_LINK = By.cssSelector("[href*='know-your-vape/vaping-unpacked/']");
    private static final By VAPING_IN_DETAIL_LINK_VUSE_FR = By.cssSelector(".main [href*='/fr/fr/tout-savoir-sur-la-vape/vape-en-details']");
    private static final By SCIENCE_LINK = By.cssSelector("[href*='know-your-vape/science/']");
    private static final By SCIENCE_LINK_VUSE_FR = By.cssSelector(".main [href*='/fr/fr/tout-savoir-sur-la-vape/science']");
    private static final By TALK_VAPING_LINK = By.cssSelector("[href*='/talk-vaping/']");
    private static final By TALK_VAPING_LINK_VUSE_FR = By.cssSelector(".main [href*='/fr/fr/tout-savoir-sur-la-vape/parlons-vapotage']");
    private static final By COOKIE_NOTICE_LINK = By.cssSelector("[href*='cookie-notice']");
    private static final By COOKIE_POLICY_LINK_GLO_PL = By.cssSelector(".pagebuilder-column [href*='/pl/pl/polityka-prywatnosci']");
    private static final By COOKIE_NOTICE_LINK_GLO_PL = By.cssSelector("[href='/pl/pl/cookies']");
    private static final By COOKIE_SETTING_LINK = By.cssSelector(".footer-cookie-manage-link.ot-sdk-show-settings");
    private static final By COOKIE_SETTING_LINK_LYFT_SE = By.cssSelector("div.column-2.footer-support.desktop-only ul  li:nth-child(6)  span");
    private static final By CUSTMOIZE_LINK = By.cssSelector(".custom-categories [href*='/gb/en/customise']");
    private static final By ALL_ABOUT_VAPING_LINK_VUSE_FR = By.cssSelector(".category-navigation [href*='/fr/fr/tout-savoir-sur-la-vape']");
    private static final By A_PROPOS_LINK_VUSE_FR = By.cssSelector(".category-navigation [href*='/fr/fr/a-propos-de-vuse']");
    private static final By EPOD_PLP_LINK_VUSE_FR = By.cssSelector(".category-navigation [href*='/fr/fr/cigarettes-electroniques']");
    private static final By ADD_A_PATTERN_LINK_VUSE_ZA = By.cssSelector("div.pdp-personalisation-toggle div.add");
    private static final By ABOUT_VELO_LINK_VELO_BE = By.cssSelector("div.bat-header-menu ul > li:nth-child(2) > a");
    private static final By PRODUCTS_LINK_VELO_BE = By.cssSelector("div.bat-header-menu ul > li:nth-child(1) > a");
    private static final By PRODUCTS_LINK_VELO_PL = By.cssSelector(".row [href*='/pl/pl/produkty-velo']");
    private static final By PRODUCT_ITEMS_REGION = By.cssSelector("#productListingContainer");
    private static final By PRODUCT_ITEMS_REGION_VELO_PL = By.cssSelector("#product-list-plp");
    private static final By NEWS_INFORMATION_LINK_VELO_BE = By.cssSelector("div.bat-header-menu ul > li:nth-child(3) > a");
    private static final By LANGUAGE_SELECTOR_LINK_VELO_BE = By.cssSelector(".bat-header .language-selector-container");
    private static final By SIGN_IN_LINK_VELO_BE = By.cssSelector("span.bat-header-account-icon-label");
    private static final By MEET_VELO_LINK_VELO_PL = By.cssSelector(".custom-categories a[href*='/pl/pl/poznaj-velo']");
    private static final By BUY_ONLINE_LINK_VELO_PL = By.cssSelector(".custom-categories a[href*='/pl/pl/produkty-velo']");
    private static final By ESMOKING_WORLD_LINK_VELO_PL = By.cssSelector(".custom-categories a[href*='/pl/pl/esmoking-world']");
    private static final By PATTERN_IMAGE_VUSE_ZA = By.cssSelector("div.front-options > div.personalisation-options > div:nth-child(1) > label");
    private static final By SPECIFIC_PATTERN_IMAGE_VUSE_ZA = By.cssSelector("#front-pattern-content  div:nth-child(4) label span.img-wrap img");
    private static final By LOGO_IMAGE_VUSE_ZA = By.cssSelector("div.header-vype.row  div.column.logo-container a");
    private static final By I_ICON_VUSE_FR = By.cssSelector(".learn-more.subscribepro-learn-more-5020");
    private static final By CLOSE_SUBSCRIPTION_VUSE_FR = By.cssSelector(".modals-wrapper .action-close");
    private static final By FIRST_DEFER_PAYMENT_PRODUCT_LINK = By.cssSelector("#product-list-plp li:nth-child(1) div.product-item-group a");
    private static final By DEFERED_PAYMENT_LINK_VUSE_FR = By.cssSelector("#product-list-plp  li:nth-child(1)  div  div.product.details.product-item-details .product-item-selling div");
    private static final By COMMENCER_LINK_VUSE_FR = By.cssSelector(".redirect");
    private static final By NO_LINK_VUSE_FR = By.cssSelector("#device-trial-confirmation-pop-up-overlay  div.button-div  a.closePopup  span.bottom");
    private static final By TOP_LYFT_LINK = By.cssSelector("#html-body div.page-wrapper div.header-top-lyft ul li.Lyft-link a");
    public static final By FILTER_STRENGHT_VELOBE = By.xpath("//div[@class='bat-filter-collapse-items' and @data-items-for='strength']//label");


    private static final By AVALANCHE_REMOVE_FROM_BASKET_BUTTON = By.cssSelector("td.col.subtotal.desktop-only > div > button");
    public static final By AVALANCHE_PLP_PRODUCT = By.cssSelector("div.product-card.product-item-info");
    public static final By AVALANCHE_BASKET_PRODUCT = By.cssSelector("tr.item-info.product-card");
    public static final By AVALANCHE_PRODUCT = By.cssSelector("#shopping-cart-table > tbody");
    private static final By AVALANCHE_BASKET_ICON_PL = By.cssSelector(".action.showcart > .icon-bag");
    private static final By AVALANCHE_BASKET_ICON = By.cssSelector("div.bat-header-cart > button > i");
    private static final By AVALANCHE_VIEW_BASKET_BUTTON = By.cssSelector("a[href*='checkout/cart']");
    private static final By AVALANCHE_CONFIRM_REMOVE_PRODUCT = By.cssSelector("#modal-content-2 > div > div > div > a");
    private static final By AVALANCHE_CONFIRM_REMOVE_PRODUCT_VELOZA = By.cssSelector("#modal-content-3 > div > div > div > a.action.action-delete");
    private static final By AVALANCHE_PERSON_SUBMENU_LINK = By.cssSelector("div.bat-header-account-menu > ul > li");
    public static final By AVALANCHE_PRIVACY_HEADING = By.cssSelector("div.htmlContainer > h2");
    public static final By AVALANCHE_PRIVACY_HEADING_PL = By.cssSelector("p:nth-of-type(1) > span:nth-of-type(1) > strong > span");
    public static final By VELOZA_PRIVACY_HEADING = By.cssSelector("bat-headline-default > div > h2");
    public static final By M_AVALANCHE_MYACCOUNT_ACCORDION = By.cssSelector("#account-links-collapsible > div.accordion-header > div > span");

    private static final By CONTACT_LINK = By.cssSelector(".category-navigation [href*='contact']");
    private static final By CONTACT_LINK_VUSE_UK = By.cssSelector(".page-footer [href*='/gb/en/contact']");
    private static final By CONTACT_LINK_GLO_DE = By.cssSelector(".pagebuilder-column a[href*='/de/de/kontakt/']");
    private static final By GLO_ICON_GLO_DE = By.cssSelector(".header-vype.row .column.logo-container");
    private static final By CONTACT_LINK_VELO_PL = By.cssSelector("[href='/pl/pl/kontakt']");
    private static final By VELO_IMG_VELO_PL = By.cssSelector(".column.logo-container a");
    private static final By CONTACT_LINK_VUSE_FR = By.cssSelector(".pagebuilder-column [href*='contact']");
    private static final By CONTACT_LINK_GLO_KZ = By.cssSelector(".category-navigation [href*='kz/ru/sluzhba-podderzhki']");
    private static final By CONTACT_LINK_VUSE_ZA = By.cssSelector(".pagebuilder-column [href*='/za/en/contact']");
    private static final By CONTACT_LINK_VUSE_CO = By.cssSelector(".page-footer .pagebuilder-column a[href*='/co/es/contact']");
    private static final By MENU_GUIDED_SELL_LINK = By.cssSelector(".level0.category-item.icon-new [href*='guided-sell']");
    private static final By SEARCH_TEXT_VUSE_FR = By.cssSelector(".control #search");
    private static final By SEARCH_ICON_VUSE_FR = By.cssSelector(".actions .action.search");
    private static final By BLOG_LINK_GLO_IT = By.cssSelector(".row a[href*='/it/it/blog/']");
    private static final By BLOG_LINK_VUSE_DE = By.cssSelector(".category-item a[href*='/de/de/blog/']");
    private static final By BLOG_LINK_VUSE_ZA = By.cssSelector(".category-navigation a[href*='/za/en/blog']");
    private static final By BLOG_LINK_VUSE_CO = By.cssSelector(".category-navigation a[href*='/co/es/blog']");
    private static final By VAPERS_LINK_VUSE_CO = By.cssSelector(".category-item a[href*='/co/es/vapeadores']");
    private static final By PODS_LINK_VUSE_CO = By.cssSelector(".category-item a[href*='/co/es/liquidos-para-vapear']");
    private static final By SEARCH_ICON_LYFT_SE = By.cssSelector(".material-icons.header-search-btn");
    private static final By SEARCH_TEXT_LYFT_SE = By.cssSelector(".block-search .input-text");
    private static final By SEARCH_CLOSE_LYFT_SE = By.cssSelector(".sliding-panel-sections .sliding-panel-close");
    private static final By STORE_LOCATOR_LYFT_SE = By.cssSelector("div.header-content [href*='/se/sv/store-locator']");
    private static final By STORE_LOCATOR_VELO_BE = By.cssSelector(".bat-header-stores-link a[href*='/be/en/store-locator']");
    private static final By STORE_LOCATOR_VELO_PL = By.cssSelector("div.header-vype.header-velo.row > div.column.row.user-menu > a");
    private static final By TOBACCO_LINK = By.cssSelector(".category-navigation a[href*='/pl/pl/wklady-tytoniowe']");
    private static final By STORE_LINK = By.cssSelector(".category-navigation a[href*='pl/pl/sklep']");
    private static final By ACCESSORIES_LINK = By.cssSelector(".category-navigation a[href*='/pl/pl/akcesoria']");
    private static final By SUBSCRIBE_NEWSLETTER_BUTTON = By.cssSelector(".newsletter-action button");
    private static final By SUBSCRIBE_NEWSLETTER_BUTTON_GLO_IT = By.cssSelector(".row .action.subscribe.primary");
    private static final By NEWSLETTER_SUBMIT_GLO_IT = By.cssSelector("div.actions .action.subscribe.primary");
    private static final By NEWSLETTER_CLOSE_GLO_IT = By.cssSelector("header.modal-header .action-close");
    private static final By INSTAGRAM_ICON = By.cssSelector("div:nth-child(3) >div > a.icon-instagram");
    private static final By VIEW_BASKET_LINK_GLO_IT = By.cssSelector(".viewcart.link");
    private static final By BASKET_ICON_GLO_IT = By.cssSelector(".row .action.showcart");
    private static final By PROCEED_CHECKOUT_LINK_GLO_IT = By.cssSelector(".item .action.primary.checkout");
    public static final By DISCOVER_GLO_HEADER = By.cssSelector("nav.navigation>ul>li:nth-child(2)>a");
    public static final By SLICK_SLIDER_GLO_IT = By.cssSelector("#slick-slide00 > div > div > a > div > div");
    // My Rewards Menu Link
    public static final By MY_REWARDS_MENU_BUTTON = By.cssSelector("a[href*='de/loyalty']");

    //RDB bannner
    private static final By RDB_BANNER_CONTENT = By.cssSelector("#rdb-popup > div > div > div > p");
    public static final By RDB_POPUP_BANNER = By.cssSelector("div.close-rdb-popup>a");
    private final static By LOADER_IMG = By.cssSelector("div.loader img");

    private static final By DEVICE_TRIAL_LINK = By.cssSelector(".category-navigation li:nth-child(5) a:nth-child(2)");
    private static final By NEWSLETTER_SUB_FROM_FOOTER_LINK = By.cssSelector(".newsletter-action a");
    private static final By NEWSLETTER_SUB_FROM_FOOTER_BUTTON_VUSE_CO = By.cssSelector(".newsletter-action .action.subscribe.primary");
    private static final By LIQUIDI_LINK = By.cssSelector(".category-navigation>li:nth-child(2) a:nth-child(2)");
    private static final By SIGARETTE_ELETTRONICHE_LINK = By.cssSelector(".category-navigation>li:nth-child(1) a:nth-child(2)");
    private static final By LOGO_VUSE_DE = By.cssSelector("header > div.header-vype.row > div.column.logo-container");
    private static final By INSIDER_CLUB_LINK_VUSE_DE = By.cssSelector("li.level0.category-item.icon-vip a");
    //public By HOMEPAGE_BANNER = By.cssSelector(UrlBuilder.getMessage("deviceTitle.key"));

    public void franceProceedToCheckout() throws InterruptedException {
        if (!isElementDisplayedBySeconds(CHECKOUT_CART_BUTTON_FR, 3)) {
            waitForExpectedElement(PROCEED_TO_BASKET_PAGE, 5).click();
            eyesCheckBasketPage();
            waitForAjaxElementNotToBePresent(webDriver, 10);
        }
        try {
            waitForItemToBeClickableAndClick(CHECKOUT_CART_BUTTON_FR, 5);
        } catch (Exception e) {
            waitForExpectedElement(CHECKOUT_CART_BUTTON_FR, 10);
            clickUsingJS(CHECKOUT_CART_BUTTON_FR);
            waitForPage();
        }
    }

    public void clickOnProceedToCheckoutButton() throws InterruptedException {
        if (UrlBuilder.isDesktop() || (UrlBuilder.isIpad())) {
            switch (UrlBuilder.getLocale()) {
                case "lyftse":
                    eyesCheckMiniCart();
                    clickByElementByQueryJSExecutor(PROCEED_TO_CART_PAGESE);
                    goToBasketPageAndTakeEyesScreenShot();
                    goToBasketCouponCodeValidationsPageAndTakeEyesScreenshot();
                    waitForExpectedElement(CHECKOUT_CART_BUTTONSE, 10);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 15);
                    clickByElementByQueryJSExecutor(CHECKOUT_CART_BUTTONSE);
                    break;
                case "lyftdk":
                    eyesCheckMiniCart();
                    waitForItemToBeClickableAndClick(clickToCheckOutButton, 10);
                    break;
                case "fr":
                case "vusefr":
                case "vuseco":
                    eyesCheckMiniCart();
                    if (UrlBuilder.isIpad()) {
                        waitForExpectedElement(PROCEED_TO_CART_PAGE, 20).click();
                    }
                    try {
                        franceProceedToCheckout();
                    } catch (Exception e) {
                        waitForExpectedElement(BASKET_ICON, 30);
                        clickByElementByQueryJSExecutor(BASKET_ICON);
                        franceProceedToCheckout();
                    }
                    break;
                case "uk":
                case "vuseuk":
                case "mx":
                case "vusemx":
                    openBasketifNotOpenLogic();
                    eyesCheckMiniCart();
                    // below - clicks on view basket button
                    waitForExpectedElement(VIEW_BASKET, 30);
                    clickByElementByQueryJSExecutor(VIEW_BASKET);
                    waitForElementToAppearAndDisappear(LOADER, 10, 20);
                    waitForExpectedElement(PAGE_TITLE_WRAPPER);
                    eyesCheckBasketPage();
                    // below - proceed to checkout button press
                    waitForExpectedElement(GRAND_TOTAL, 30);
                    waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 30);
                    clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON);
                    waitForLoaderToDisapear();
                    break;
                case "nl":
                    eyesCheckMiniCart();
                    clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON_NL);
                    break;
                case "kz":
                case "pl":
                    clickOnBasketIcon();
                    eyesCheckMiniCart();
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_PL);
                    break;
                case "ie":
                    try {
                        eyesCheckMiniCart();
                        clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON_IE);
                    } catch (TimeoutException ex) {
                        clickByElementByQueryJSExecutor(CART_SUMMERY_PROCEED_TO_CHECKOUT_BUTTON_IE);
                    }
                    break;
                case "vypeit":
                case "vuseit":
                    clickOnBasketIcon();
                    eyesCheckMiniCart();
                    // below - clicks on view basket button
                    waitForExpectedElement(VIEW_BASKET, 10);
                    clickByElementByQueryJSExecutor(VIEW_BASKET);
                    waitForExpectedElement(PAGE_TITLE_WRAPPER);
                    eyesCheckBasketPage();
                    // below - proceed to checkout button press
                    waitForExpectedElement(GRAND_TOTAL, 10);
                    waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 10);
                    clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                    if (getWebDriver().findElements(AGE_VERIFICATION_CONSENT_BUTTON).size() > 0)
                        clickByElementByQueryJSExecutor(AGE_VERIFICATION_CONSENT_BUTTON);
                    waitForPage();
                    break;
                case "vusede":
                    eyesCheckMiniCart();
                    if (UrlBuilder.isIpad()) {
                        clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_VUSEDE);
                        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                        waitForExpectedElement(GRAND_TOTAL, 30);
                        clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT);
                    } else {
                        try {
                            waitForExpectedElement(PROCEED_TO_CHECKOUT_VUSEDE, 10);
                            clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_VUSEDE);
                            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                            waitForExpectedElement(GRAND_TOTAL, 8);
                            eyesCheckBasketPage();
                            clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT);
                        } catch (Exception e) {
                            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                            waitForExpectedElement(GRAND_TOTAL, 8);
                            clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT);
                        }

                    }
                    break;
                case "de":
                    eyesCheckMiniCart();
                    clickByElementByQueryJSExecutor(proceedToCheckOutButton);
                    break;
                case "vuseza":
                    if (!webDriver.getCurrentUrl().toLowerCase().contains("cart")) {
                        eyesCheckMiniCart();
                        waitForExpectedElement(VIEW_BASKET, 30);
                        clickByElementByQueryJSExecutor(VIEW_BASKET);
                    }
                    waitForElementToAppearAndDisappear(LOADER, 10, 20);
                    waitForExpectedElement(PAGE_TITLE_WRAPPER);
                    eyesCheckBasketPage();
                    waitForExpectedElement(GRAND_TOTAL, 30);
                    waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 30);
                    clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON);
                    waitForLoaderToDisapear();
                    break;
                default:
                    eyesCheckMiniCart();
                    WebElement webElement = waitForExpectedElement(proceedToCheckOutButton, 30);
                    if (webElement != null) {
                        webElement.click();
                    } else {
                        clickByElementByQueryJSExecutor(proceedToCheckOutButton);
                    }
            }
        } else {
            switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
                case VUSEZA:
                case IE:
                    if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIPhone() || UrlBuilder.isMobile()) {
                        waitForPage();
                        waitForExpectedElement(M_PROCEED_TO_CHECKOUT_BUTTON, 10);
                        clickByElementByQueryJSExecutor(M_PROCEED_TO_CHECKOUT_BUTTON);
                    }
                    break;
                case LYFTSE:
                    eyesCheckMiniCart();
                    waitForExpectedElement(M_PROCEED_TO_CHECKOUT_BUTTON, 10);
                    clickByElementByQueryJSExecutor(M_PROCEED_TO_CHECKOUT_BUTTON);
                    break;
                case VYPEIT:
                case VUSEUK:
                case LYFTDK:
                    if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIPhone()) {
                        waitForExpectedElement(M_PROCEED_TO_CHECKOUT_BUTTON, 10);
                        clickByElementByQueryJSExecutor(M_PROCEED_TO_CHECKOUT_BUTTON);
                    }
                    break;
                case FR:
                case VUSEFR:
                    if (UrlBuilder.isMobile()) {
                        franceProceedToCheckout();
                    }
                    break;
                case VUSEIT:
                    waitForExpectedElement(M_PROCEED_TO_CHECKOUT_BUTTON, 10);
                    clickByElementByQueryJSExecutor(M_PROCEED_TO_CHECKOUT_BUTTON);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                    if (getWebDriver().findElements(AGE_VERIFICATION_CONSENT_BUTTON).size() > 0)
                        clickByElementByQueryJSExecutor(AGE_VERIFICATION_CONSENT_BUTTON);
                    break;
                case KZ:
                case PL:
                    waitForExpectedElement(PROCEED_TO_CHECKOUT, 5);
                    clickUsingJS(PROCEED_TO_CHECKOUT);
                    break;
                case VUSEDE:
                    try {
                        waitForExpectedElement(M_PROCEED_TO_CHECKOUT_BUTTON, 10);
                        clickByElementByQueryJSExecutor(M_PROCEED_TO_CHECKOUT_BUTTON);
                    } catch (Exception ex) {
                        clickBasketIcon();
                        waitForExpectedElement(M_PROCEED_TO_CHECKOUT_BUTTON, 20);
                        if (UrlBuilder.isIPhone())
                            clickUsingJS(M_PROCEED_TO_CHECKOUT_BUTTON);
                        else
                            waitForItemToBeClickableAndClick(M_PROCEED_TO_CHECKOUT_BUTTON, 10);
                    }
                    break;
                case VUSEDK:
                    eyesCheckMiniCart();
                    clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON_NL);
                    break;
                case MX:
                    if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIPhone()) {
                        waitForExpectedElement(M_PROCEED_TO_CHECKOUT_BUTTON_MX, 10);
                        eyesCheckMiniCart();
                        clickByElementByQueryJSExecutor(M_PROCEED_TO_CHECKOUT_BUTTON_MX);
                        waitForExpectedElement(GRAND_TOTAL, 30);
                        waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 30);
                        clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON);
                    }
                    break;
                default:
            }
        }
    }

    public void waitForLoaderToDisapear() {
        waitForElementToDisappear(LOADER_IMG, 20);
    }

    public void clickOnProceedToCartCTA() {
        switch (UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
            case "mx":
            case "vusemx":
            case "vypeit":
            case "vuseit":
                try {
                    waitForExpectedElement(PROCEED_TO_CART_PAGE, 30).click();
                } catch (Exception e) {
                    LOG.info("Banner Blocking cart button-exiting banner");
                    waitForExpectedElement(By.cssSelector(".more-menu-container .material-icons")).click();
                    waitForExpectedElement(PROCEED_TO_CART_PAGE, 30).click();
                }
                break;
            case "lyftse":
                waitForExpectedElement(PROCEED_TO_CART_PAGESE).click();
                break;
        }
    }

    public void clickOnSecondMenu() {
        waitForExpectedElement(SECOND_MENU, 30);
        clickByElementByQueryJSExecutor(SECOND_MENU);
    }

    public void addFreeGiftAccordingToDevice() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 20);
        freeTotalGift = waitForExpectedElement(TOTAL_GIFT_OFFER, 20).getText().replaceAll("[^0-9]", "");
        int giftTotalOffer = Integer.parseInt(freeTotalGift);
        for (int i = 0; i < giftTotalOffer; i++) {
            waitForAjaxElementNotToBePresent(getWebDriver(), 3);
            waitForExpectedElement(ADD_FREE_GIFT_BUTTON, 8);
            clickByElementByQueryJSExecutor(ADD_GIFT_OFFER);
        }
        clickByElementByQueryJSExecutor(ADD_FREE_GIFT_BUTTON);
    }

    public void userCloseFreeGiftModule() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        clickByElementByQueryJSExecutor(CLOSE_FREE_GIFT_WINDOW);
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
    }

    public void proceedToCheckoutButton() {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                clickByElementByQueryJSExecutor(PROCEED_TO_CART_PAGESE);
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                clickByElementByQueryJSExecutor(CHECKOUT_CART_BUTTONSE);
                waitForAjaxElementNotToBePresent(getWebDriver(), 15);
                break;
            case "veloza":
                waitForElementToAppearAndDisappear(LOADER, 5, 5);
                clickUsingJS(AVALANCHE_CHECKOUT_BUTTON);
                break;
            case "lyftdk":
                waitForItemToBeClickableAndClick(clickToCheckOutButton, 10);
                break;
            case "it":
                clickUsingJS(clickToCheckOutButton);
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                if (!getWebDriver().getCurrentUrl().contains("checkout")) {
                    getWebDriver().navigate().to(getWebDriver().getCurrentUrl().replace("customer/account/index/", "checkout/"));
                }
                break;
            case "glojp":
                IsPageLoaded.waitAllRequest();
                waitForExpectedElement(CHECKOUT_BUTTON, 30);
                try {
                    closeOcrPopupOnBasket();
                    clickByElementByQueryJSExecutor(CHECKOUT_BUTTON);
                    closeOcrPopupOnBasket();
                    clickByElementByQueryJSExecutor(CHECKOUT_BUTTON);
                } catch (Exception e) {
                    closeOcrPopupOnBasket();
                    clickByElement(CHECKOUT_BUTTON);
                    closeOcrPopupOnBasket();
                    clickByElement(CHECKOUT_BUTTON);
                }

                if (!waitForExpectedElement(CHECKOUT_PAGE_HEADER).isDisplayed()) {
                    refreshBrowser();
                }
                waitForExpectedElement(CHECKOUT_PAGE_HEADER, 20).isDisplayed();
                break;
        }
    }

    public void clickOnLogo() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                waitForExpectedElement(LOGO_DK, 10);
                if (doesURLContain("/catalogsearch/"))
                    clickUsingJS(PLP_LOGO_DK);
                else
                    clickByElementByQueryJSExecutor(LOGO_DK);
                break;
            case "de":
                waitForExpectedElement(LOGO_ICON_DE, 10);
                if (doesURLContain("/catalogsearch/"))
                    clickUsingJS(LOGO_ICON_DE);
                else
                    clickByElementByQueryJSExecutor(LOGO_ICON_DE);
                break;
            case "fr":
            case "vusefr":
                try {
                    waitForExpectedElement(logo, 30);
                    waitForExpectedElement(logo).click();
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(LOGO_ICON_FR);
                }
            case "vuseco":
            case "vuseuk":
            case "vuseza":
                clickByElementByQueryJSExecutor(CHECKOUT_VUSELOGO_VUSECO);
                break;
            default:
                try {
                    waitForExpectedElement(logo, 10);
                    waitForExpectedElement(logo).click();
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(logo);
                }
        }
    }

    public void clickOnSunscription() {
        try {
            waitForExpectedElement(SUBS_LINK, 4).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(SUBS_LINK);
        }
        waitForLoaderIconDisappear();
    }

    public void clickOnLinkWithHref(String key) {
        if (UrlBuilder.isMobile() && UrlBuilder.getLocale().equals("it")) {
            clickMobileHamburgerMenu();
            clickByElementByQueryJSExecutor(By.cssSelector(".burger-menu-container a[href*='" + UrlBuilder.getMessage(key) + "']"));
        } else {
            switch (UrlBuilder.getLocale()) {
                case "kz":
                    clickByElementByQueryJSExecutor(By.cssSelector("a[href='" + UrlBuilder.getMessage(key) + "']"));
                    break;
                case "veloza":
                    clickByElementByQueryJSExecutor(HEADER_CONTACT_ICON_VELOZA);
                    break;
                case "velopl":
                    clickByElementByQueryJSExecutor(CONTACT_LINK_VELOPL);
                    break;
                default:
                    try {
                        waitForExpectedElement(By.cssSelector("a[href*='" + UrlBuilder.getMessage(key) + "']"), 10).click();
                    } catch (Exception ex) {
                        clickByElementByQueryJSExecutor(By.cssSelector("a[href*='" + UrlBuilder.getMessage(key) + "']"));
                    }
            }
        }
    }

    public void clickOnHrefLink(String key) {
        waitForExpectedElement(By.cssSelector("a[href='" + UrlBuilder.getMessage(key) + "']"), 10);
        getWebDriver().findElement(By.cssSelector("a[href='" + UrlBuilder.getMessage(key) + "']")).click();
    }

    public void clickAccountLink() {
        By accountSelector;
        if (UrlBuilder.getLocale().equalsIgnoreCase("ie")) {
            accountSelector = VYPE_ACCOUNT_LINK_IE;
        } else {
            accountSelector = VYPE_ACCOUNT_LINK_UK;
        }
        if (UrlBuilder.isDesktop()) {
            try {
                waitForExpectedElement(accountSelector, 10).click();
            } catch (Exception e) {
                clickByElementByQueryJSExecutor(accountSelector);
            }
        }
    }

    public void chooseMyAccountOnAccountDropdown() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case KZ:
                waitForExpectedElement(MY_ACCOUNT_KZ, 5).click();
                break;
            case VELOZA:
            case VELOBE:
                try {
                    waitForElementToAppearAndDisappear(LOADER, 2, 3);
                    webDriver.findElements(AVALANCHE_PERSON_SUBMENU_LINK).get(0).click();
                } catch (Exception e) {
                    clickUsingJS(webDriver.findElements(AVALANCHE_PERSON_SUBMENU_LINK).get(0));
                }
                if (!Props.getProp("siteMode").equalsIgnoreCase("desktop")) {
                    waitForExpectedElement(M_AVALANCHE_MYACCOUNT_ACCORDION).click();
                }
                break;
            case VELOPL:
                // Code is already in  clickPersonIcon()
/*                try {
                    waitForElementToAppearAndDisappear(LOADER,2,3);
                    webDriver.findElements(AVALANCHE_PERSON_SUBMENU_LINK).get(0).click();
                } catch (Exception e) {
                    clickUsingJS(webDriver.findElements(AVALANCHE_PERSON_SUBMENU_LINK).get(0));
                }*/
                break;
            default:
                waitForExpectedElement(MY_ACCOUNT_ON_ACCOUNT_DROPDOWN, 5).click();
        }
    }

    public void clickSignOut() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad() || UrlBuilder.isTablet()) {
            switch (UrlBuilder.getLocale()) {
                case "velobe":
                case "veloza":
                    if (isElementDisplayedBySeconds(LOGOUT_LINK_VELO, 2)) {
                        getWebDriver().findElement(LOGOUT_LINK_VELO).click();
                    }
                    break;
                case "velopl":
                    if (isElementDisplayedBySeconds(LOGOUT_LINK_VELO_PL, 2)) {
                        getWebDriver().findElement(LOGOUT_LINK_VELO_PL).click();
                        waitForAjaxElementNotToBePresent(webDriver,15);
                    }
                    break;
                default:
                    waitForExpectedElement(PERSON_ICON, 20).click();
            }
        }
    }

    public void clickPersonIconAfterCreateAccount()
    {
        hoverOnElement(PERSON_ICON_VELOPL);
        waitAndClickByElementByJSExecutor(LOGGEDIN_ACCOUNT_VELOPL, 10);
    }

    public void clickPersonIcon() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad() || UrlBuilder.isTablet()) {
            switch (UrlBuilder.getLocale()) {
                case "velopl":
                    waitForAjaxElementNotToBePresent(webDriver,5);
                    if(getWebDriver().getCurrentUrl().contains("account/index"))
                    {
                        hoverOnElement(PERSON_ICON_VELOPL);
                    }else if(getWebDriver().getCurrentUrl().contains("/success")){
                        hoverOnElement(PERSON_ICON_VELOPL);
                        waitAndClickByElementByJSExecutor(LOGGEDIN_ACCOUNT_VELOPL, 10);
                    }else{
                        hoverOnElement(PERSON_ICON_VELOPL);
                        waitAndClickByElementByJSExecutor(ACCOUNT_VELOPL, 10);
                    }
                    break;
                case "velobe":
                case "veloza":
                    waitForAjaxElementNotToBePresent(webDriver,3);
                    try{
                        waitAndClickByElementByJSExecutor(PERSON_ICON_VELOBE,10);
                    }catch (Exception e){
                        refreshBrowser();
                        waitAndClickByElementByJSExecutor(PERSON_ICON_VELOBE,10);
                    }
                    break;
                case "fr":
                case "vusefr":
                case "vuseco":
                case "vuseza":
                    try {
                        waitForExpectedElement(PERSON_ICON, 10);
                        hoverOnElement(PERSON_ICON);
                    } catch (Exception e) {
                        clickOnLogo();
                        waitForExpectedElement(PERSON_ICON, 10);
                        hoverOnElement(PERSON_ICON);
                    }
                    break;
                case "uk":
                case "ukLive":
                case "vuseuk":
                case "vuseuklive":
                case "mx":
                case "vusemx":
                case "glode":
                    waitForExpectedElement(PERSON_ICON, 10);
                    hoverOnElement(PERSON_ICON);
                    break;
                case "vusede":
                    waitForExpectedElement(PERSON_ICON, 10);
                    if(doesURLContain("vuse.com/de/de/"))
                        waitAndClickByElementByJSExecutor(PERSON_ICON,10);
                    else
                        hoverOnElement(PERSON_ICON);
                    break;
                case "lyftse":
                case "lyftdk":
                    clickPersonIcon_lyft();
                    break;
                case "pl":
                    waitForExpectedElement(PERSON_ICON, 10);
                    if (UrlBuilder.isDesktop())
                        hoverOnElement(PERSON_ICON);
                    else
                        waitForExpectedElement(PERSON_ICON, 20).click();
                    break;
                case "it":
                    try {
                        waitForItemToBeClickableAndClick(PERSON_ICON_GLO, 10);
                    } catch (Exception e) {
                        LOG.info("Exception catch " + e);
                        clickUsingJS(PERSON_ICON_GLO);
                    }
                    break;
                case "kz":
                    if(UrlBuilder.isIpad()){
                        try {
                            waitForItemToBeClickableAndClick(PERSON_ICON_GLO_IPAD, 10);
                        } catch (Exception e) {
                            clickByElementByQueryJSExecutor(PERSON_ICON_GLO_IPAD);
                        }
                    }
                    else {
                        waitForExpectedElement(PERSON_ICON_GLO).click();
                    }
                    return;
                case "vypeit":
                case "vuseit":
                    if (webDriver.getCurrentUrl().contains("/checkout")){
                        clickByElementByQueryJSExecutor(CHECKOUT_VYPEIT_LOGO);
                        try {
                            waitForExpectedElement(PERSON_ICON, 10);
                            hoverOnElement(PERSON_ICON);
                        }catch (Exception e) {
                            waitForExpectedElement(PERSON_ICON_LOGGEDIN, 10);
                            hoverOnElement(PERSON_ICON_LOGGEDIN);
                        }
                    }
                    else {
                        try {
                            waitForExpectedElement(PERSON_ICON, 10);
                            hoverOnElement(PERSON_ICON);
                        }catch (Exception e) {
                            waitForExpectedElement(PERSON_ICON_LOGGEDIN, 10);
                            hoverOnElement(PERSON_ICON_LOGGEDIN);
                        }
                    }
                    break;
                case "dk":
                    waitForExpectedElement(PERSON_ICON, 10);
                    clickByElementByQueryJSExecutor(PERSON_ICON);
                    break;
                case "vusedk":
                    clickByElement(PERSON_ICON);
                    clickLinkOnPersonMenu("signInLink.key");
                    break;
                default:
                    waitForExpectedElement(PERSON_ICON, 20).click();
            }
        } else {
            switch (UrlBuilder.getLocale()) {
                case "vusedk":
                    waitForExpectedElement(PERSON_ICON, 15).click();
                    break;
                case "fr":
                case "vusefr":
                    waitForPage();
                    if (UrlBuilder.isIPhone()) {
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                    } else {
                        waitForExpectedElement(PERSON_ICON, 15).click();
                        clickByElement(M_PERSONICON_VUSEFR);
                    }
                    break;
                case "dk":
                case "de":
                case "ie":
                    clickByElement(M_PERSONICON_VYPEDE);
                    break;
                case "uk":
                case "vuseuk":
                    waitForExpectedElement(M_SANDWICH_MENU_ICON, 10).click();
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT).click();
                    if (isElementDisplayedBySeconds(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT_SIGNINREGISTER, 1)) {
                        waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT_SIGNINREGISTER).click();
                    } else {
                        waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT_MYACCOUNT).click();
                    }
                    break;
                case "ukLive":
                case "vuseuklive":
                    waitForItemToBeClickableAndClick(PERSON_ICON, 10);
                    break;
                case "lyftse":
                    waitForExpectedElement(LYFTLAB_BURGER_MENU).click();
                    waitForExpectedElement(LYFTLAB_BURGER_MENU_LOGIN_OPTION).click();
                    break;
                case "lyftdk":
                    mobile_clickPersonIcon_lyft();
                    break;
                case "glode":
                    waitForExpectedElement(PERSON_ICON, 10);
                    hoverOnElement(PERSON_ICON);
                    break;
                case "it":
                    try{
                        waitForItemToBeClickableAndClick(M_PERSON_ICON_GLOIT,10);
                    }
                    catch (Exception e)
                    {
                        waitForItemToBeClickableAndClick(M_PERSON_ICON_GLOIT,20);
                    }
                    break;
                case "kz":
                    try {
                        waitForItemToBeClickableAndClick(PERSON_ICON_GLO, 10);
                    } catch (Exception e) {
                        waitForExpectedElement(PERSON_ICON_GLO, 10).click();
                    }
                    break;
                case "mx":
                case "vusemx":
                    if (UrlBuilder.isMobile()) {
                        waitForExpectedElement(M_PERSONICON_VYPEMX, 5).click();
                    } else {
                        waitForExpectedElement(T_PERSONICON_VYPEMX, 5).click();
                    }
                    break;
                case "veloza":
                case "vuseza":
                    clickByElement(M_PERSONICON_VYPECO);
                    break;
                case "nl":
                    waitForExpectedElement(M_PERSONICON_VYPEIT).click();
                    break;
                default:
                    waitForExpectedElement(PERSON_ICON, 20).click();
            }
        }
    }

    public void hoverPersonIcon() {
        hoverOnElement(PERSON_ICON);
    }

    public void userHoversOnPlpItem() {
        scrollElementIntoView(PLP_ITEM);
        hoverOnElement(PLP_ITEM);
    }

    public void clicksOnMyAccountEditLink() {
        By MyAccountEdit_lyft = By.cssSelector(UrlBuilder.getMessage("myAccountEditText.key"));
        if (doesURLContain("/se/sv") || (doesURLContain("lyft.non-prod.marketing.bat.net/dk/da"))) {
            clickByElementByQueryJSExecutor(MyAccountEdit_lyft);
        }
    }

    public void clickOnBlogIcon_lyft() {
        if (UrlBuilder.getLocale().equals("lyftdk")) {
            clickByElementByQueryJSExecutor(BLOG_ICON_LYFTDK);
        } else {
            clickByElementByQueryJSExecutor(BLOG_ICON_LYFT);
        }
    }

    public void closeNewsLetterPopUp() {
        waitForExpectedElement((NEWS_LETTER_CO));
        clickByElementByQueryJSExecutor(CLOSE_BUTTON_CO);
    }

    public void deUniqueToLoginSelector() {
        clickByElementByQueryJSExecutor(LOGIN_LIST_OPTION_DE);
    }

    public void cookiePopUpDisplayed() {
        assertTrue(waitForExpectedElement(cookiePopUp, 20).isDisplayed());
    }

    public void oneTrustCookiePopUpDisplayed() {
        switch (UrlBuilder.getLocale()){
            case "glode":
                tryClickIAmOver18();
                break;
        }
        assertTrue(waitForExpectedElement(OneTrustCookiePopUp, 10).isDisplayed());
    }

    public void oneTrustCookiesLinkAndButtonsIsDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                assertTrue(waitForExpectedElement(OT_COOKIES_ALLOW_ALL_BUTTON, 10).isDisplayed());
                assertTrue(waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK, 10).isDisplayed());
                assertTrue(waitForExpectedElement(OneTrustPolicyTextLink, 10).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(OneTrustAcceptCookiesButton, 10).isDisplayed());
                assertTrue(waitForExpectedElement(OneTrustManageCookiesButton, 10).isDisplayed());
                assertTrue(waitForExpectedElement(OneTrustPolicyTextLink, 10).isDisplayed());
                if (webDriver.getCurrentUrl().contains("fr/fr")) {
                    assertTrue(waitForExpectedElement(OneTrustPolicyRefuseAll, 10).isDisplayed());
                }
        }
    }

    public void oneTrustManageCookiesButtonClick() {
        switch (UrlBuilder.getLocale()) {
            case "veloza":
                assertTrue(waitForExpectedElement(OT_COOKIES_LOGO_VELOZA).isDisplayed());
                break;
            default:
                waitForExpectedElement(OneTrustManageCookiesButton, 10).click();
        }
    }

    public void oneTrustPCLogoDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                assertTrue(waitForExpectedElement(OT_COOKIES_LOGO_DK).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterLogo).isDisplayed());
        }
    }

    public void oneTrustPCCloseButtonDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                assertTrue(waitForExpectedElement(OT_COOKIES_CLOSE_BUTTON).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterCloseButton).isDisplayed());
        }
    }

    public void oneTrustPCTitleDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                assertTrue(waitForExpectedElement(OT_COOKIES_TITLE).isDisplayed());
                break;
            case "vusefr":
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterTitle).isDisplayed());
                break;
            case "kz":
            case "vusemx":
                try {
                    assertTrue(waitForExpectedElement(OneTrustPrivacyCenterTitle_KZ).isDisplayed());
                } catch (Exception e) {
                    assertTrue(waitForExpectedElement(OneTrustPrivacyCenterTitle).isDisplayed());
                }
                break;
            default:
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterTitle).isDisplayed());
        }
    }

    public void oneTrustPCTextDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                assertTrue(waitForExpectedElement(OT_COOKIES_PRIVACY_TEXT).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterText).isDisplayed());
        }
    }

    public void oneTrustPCAcceptAllButtonDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                assertTrue(waitForExpectedElement(OT_COOKIES_ALLOW_ALL_BUTTON).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterAcceptAllButton).isDisplayed());
        }
    }

    public void AcceptAllButtonClickedFromPrivacyCenterOT() {
        waitForExpectedElement(OneTrustPrivacyCenterAcceptAllButton).click();
    }

    public void clickOnFlotingIcon() {
        waitForExpectedElement(ONETRUST_FLOATING_ICON).click();
    }

    public void clickOnFooterLink() {
        waitForExpectedElement(ONETRUST_COOKIES_SETTINGS_FOOTER_LINK).click();
    }

    public void stationaryCookieIconDisplayed() {
        assertEquals(webDriver.findElements(ONETRUST_FLOATING_ICON).size(), 1);
        assertTrue(waitForExpectedElement(ONETRUST_FLOATING_ICON).isDisplayed());
    }

    public void stationaryCookieIconNotDisplayed() {
        assertEquals(webDriver.findElements(ONETRUST_FLOATING_ICON).size(), 0);
    }

    public void oneTrustCookiesSettingsFooterLinkDisplayed() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case LYFTSE:
                assertTrue(waitForExpectedElement(ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_LYFTSE).isDisplayed());
                break;
            case UK:
            case MX:
            case VUSEUK:
                assertTrue(waitForExpectedElement(ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_UK).isDisplayed());
                break;
            case VUSEZA:
                assertTrue(waitForExpectedElement(ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_ZA).isDisplayed());
                break;
            case VUSEFR:
                if(UrlBuilder.isDesktop()){
                    assertTrue(waitForExpectedElement(ONETRUST_COOKIES_SETTINGS_FOOTER_LINK).isDisplayed());}
                else {
                    waitForItemToBeClickableAndClick(INFORMATION_FOOTER_FR);
                    assertTrue(waitForExpectedElement(ONETRUST_COOKIES_SETTINGS_FOOTER_LINK_FR).isDisplayed());
                }
                break;
            default:
                assertTrue(waitForExpectedElement(ONETRUST_COOKIES_SETTINGS_FOOTER_LINK).isDisplayed());
        }
    }

    public void oneTrustPCStrictlyNecessaryCookiesDisplayed() {
        if (UrlBuilder.getLocale().equals("dk"))
            waitForExpectedElement(COOKIES_ACTIVE_CATEGORY_DK).click();
        assertTrue(waitForExpectedElement(OneTrustPrivacyCenterStrictlyNecessaryCookies).isDisplayed());
    }

    public void clickOnPrivacyNoticeLinkOnOneTrustBanner() {
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
                waitForExpectedElement(ONE_TRUST_POLICY_TEXT_LINK_MX).click();
                break;
            default:
                waitForExpectedElement(OneTrustPolicyTextLink).click();
        }
    }

    public void cookieClickAllowAllAndClickOver18() {
        switch (UrlBuilder.getLocale()) {
            case "kz":
                waitForExpectedElement(allowCookiesButton, 20).isDisplayed();
                eyesCheckCookieBanner();
                waitForExpectedElement(allowCookiesButton, 20).click();
                break;
            case "it":
                tryClickIAmOver18();
                assertTrue(isElementDisplayedBySeconds(allowCookiesButton, 10));
                eyesCheckCookieBanner();
                waitForExpectedElement(allowCookiesButton, 10).click();
                break;
            case "glode":
            case "pl":
                tryClickIAmOver18();
                waitForExpectedElement(allowCookiesButton, 10).isDisplayed();
                eyesCheckCookieBanner();
                waitForExpectedElement(allowCookiesButton, 10).click();
                break;
            case "de":
            case "mx":
            case "vusemx":
                waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
                eyesCheckCookieBanner();
                waitForExpectedElement(allowCookiesButton, 15).click();
                tryClickIAmOver18();
                break;
            case "vuseit":
            case "vypeit":
                waitForExpectedElement(By.cssSelector("button#onetrust-accept-btn-handler"), 15).isDisplayed();
                eyesCheckCookieBanner();
                waitForExpectedElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
                tryClickIAmOver18();
                break;
            case "lyftdk":
            case "dk":
            case "vusedk":
                try {
                    waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK, 10).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK).click();
                    waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK, 10).isDisplayed();
                    waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK).click();
                } catch (Exception ex) {
                    if (!doesURLContain("/se/en"))
                        waitForExpectedElement(OT_COOKIES_CONFIRM_CHOICE_BUTTON).click();
                }
                tryClickIAmOver18();
                break;
            case "lyftse":
                try {
                    waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK, 10).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK).click();
                    waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK, 10).isDisplayed();
                    waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK).click();
                    tryClickIAmOver18();
                } catch (Exception ex) {
                    tryClickIAmOver18();
                    waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK, 10).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK).click();
                    waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK, 10).isDisplayed();
                    waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK).click();
                }
                break;
            case "vusefr":
            case "vuseco":
            case "vuseza":
                assertTrue(isElementDisplayedBySeconds(allowCookiesButton, 10));
                eyesCheckCookieBanner();
                waitForExpectedElement(allowCookiesButton, 15).click();
                eyesCheckAgeGate();
                FrAndITAgePopulationScript();
                break;
            case "velobe":
            case "velopl":
                tryClickIAmOver18();
                try {
                    waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(allowCookiesButton, 15).click();
                } catch (Exception e) {
                    LOG.info("Could not find allows cookies");
                }
                break;
            case "veloza":
                tryClickIAmOver18();
                try {
                    waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(allowCookiesButton, 15).click();
                } catch (Exception e) {
                    LOG.info("Could not find allows cookies");
                }
                try {
                tryFranceAgeConfirmationLogic();
                } catch (Exception e) {
                    LOG.info("Could not find age gate");
                }
                try {
                    waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(allowCookiesButton, 15).click();
                } catch (Exception e) {
                    LOG.info("Could not find allows cookies");
                }
                break;
            case "vusede":
                waitForExpectedElement(allowCookiesButton, 15);
                if(getWebDriver().findElements(allowCookiesButton).size()==0) {
                    refreshBrowser();
                    waitForExpectedElement(allowCookiesButton, 15);
                }
                eyesCheckCookieBanner();
                try {
                    waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(allowCookiesButton, 15).click();
                } catch (Exception e) {
                    LOG.info("Could not find allows cookies");
                }
                goToAgeGateRestrictionDisclaimerGoogleRedirectionPageAndTakeEyesScreenshot();
                tryClickIAmOver18(); // Uk and others?
                tryFranceAgeConfirmationLogic();
                waitForElementToAppearAndDisappear(LOADER, 2, 4);
                break;

            default:
                try {
                    waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(allowCookiesButton, 15).click();
                    goToAgeGateRestrictionDisclaimerGoogleRedirectionPageAndTakeEyesScreenshot();
                    tryClickIAmOver18(); // Uk and others?
                    tryFranceAgeConfirmationLogic(); // Only FR
                } catch (Exception e) {
                    tryClickIAmOver18(); // Uk and others?
                    tryFranceAgeConfirmationLogic(); // Only FR
                    waitForExpectedElement(AllowCookiesButtonBox, 15).isDisplayed();
                    eyesCheckCookieBanner();
                    waitForExpectedElement(AllowCookiesButtonBox, 15).click();
                }
                waitForElementToAppearAndDisappear(LOADER, 1, 3);
        }
    }

    public void allowCookieButton() {
        if (UrlBuilder.getLocale().equals("velopl"));
        waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
        eyesCheckCookieBanner();
        waitForExpectedElement(allowCookiesButton, 15).click();
    }

    public void checkOrderOfCookieBanner() {
        List<WebElement> elementList = getWebDriver().findElements(COKKIE_BANNER);
        for (int i = 0; i < elementList.size(); i++) {
            String str = elementList.get(i).getText();
            if (i == 0)
                assertTrue(str.contains(UrlBuilder.getMessage("necessaryCokkies.key")));
            if (i == 1)
                assertTrue(str.contains(UrlBuilder.getMessage("allCokkies.key")));
        }
    }

    public void oneTrustPCConfirmMyChoiceButtonDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                assertTrue(waitForExpectedElement(OT_COOKIES_CONFIRM_CHOICE_BUTTON).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterConfirmMyChoiceButton).isDisplayed());
        }
    }

    public void refuseAllButtonOnPrivacyCenter() {
        assertTrue(waitForExpectedElement(OneTrustPolicyRefuseAllOnPrivacyCenter).isDisplayed());
    }

    public void oneTrustPCCookiesSwitchesAreOffByDefaultExceptStrictlyNecessary() {
        List<WebElement> toggles;
        int numberOfCookiesCategoriesPresent;
        switch (UrlBuilder.getLocale()) {
            case "dk":
                toggles = getWebDriver().findElements(By.cssSelector("label.ot-switch"));
                numberOfCookiesCategoriesPresent = toggles.size();
                oneTrustPCStrictlyNecessaryCookiesDisplayed();
                if (numberOfCookiesCategoriesPresent > 1) {
                    for (int i = 0; i < numberOfCookiesCategoriesPresent; i++) {
                        List<WebElement> lstTabs = getWebDriver().findElements(OT_BANNER_COOKIES_TAB);
                        lstTabs.get(i).click();
                        assertFalse(toggles.get(i).isSelected());
                    }
                }
                break;
            default:
                toggles = getWebDriver().findElements(OneTrustPCToggle);
                numberOfCookiesCategoriesPresent = toggles.size();
                assertTrue(waitForExpectedElement(OneTrustPrivacyCenterStrictlyNecessaryCookiesToggle).isDisplayed());
                if (numberOfCookiesCategoriesPresent > 1) {
                    for (WebElement elm : toggles) {
                        assertFalse(elm.isSelected());
                    }
                }
        }
    }

    public void oneTrustPCCookiesInformationLink() {
        List<WebElement> toggles = getWebDriver().findElements(OneTrustPCToggle);

        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
            case "fr":
            case "vusefr":
                toggles = getWebDriver().findElements(OneTrustPCToggleMX);
                break;
            default:
                toggles = getWebDriver().findElements(OneTrustPCToggle);
        }
        int numberOfCookiesCategoriesPresent = toggles.size();
        List<WebElement> cookiesInfoLinks = getWebDriver().findElements(OneTrustPCCookieCategoriesDetailsLink);
        int numberOfLinks = cookiesInfoLinks.size();
        assertEquals(numberOfCookiesCategoriesPresent, numberOfLinks, "numbers of cookies categories and cookies details links are not equal");
    }

    public void oneTrustPCCookiesSwitchesAreON() {
        List<WebElement> toggles = getWebDriver().findElements(By.cssSelector("div.ot-switch.ot-toggle>input"));
        int numberOfCookiesPresent = toggles.size();
        LOG.info("Type of cookies categories are " + numberOfCookiesPresent);
        assertTrue(waitForExpectedElement(OneTrustPrivacyCenterStrictlyNecessaryCookiesToggle).isDisplayed());
        if (numberOfCookiesPresent > 1) {
            for (WebElement elm : toggles) {
                assertTrue(elm.isSelected());
            }
        }
    }

    public void cookieLearnMoreLinkClick() {
        waitForExpectedElement(cookiesLearnMore).click();
    }

    public void cookieTextDisplayedAsExpected() {
        String expectedCopy = "We use cookies to make your experience better.";
        String expectedCopy2nd = "To comply with the new e-Privacy directive, we need to ask for your consent to set the cookies.";
        assertTrueWithCustomError(expectedCopy, waitForExpectedElement(cookieText).getText());
        assertTrueWithCustomError(expectedCopy2nd, waitForExpectedElement(cookieText2ndPart).getText());
    }

    public void checkoutPopUpPresented() {
        waitForPage();
        try {
            waitForExpectedElement(checkOutSignInPopUp).isDisplayed();
            LOG.info("\n ************* Waited for Checkout Pop up to be Presented!!");
            LOG.info("Looking for existence of Pop Up");
            assertTrue(waitForExpectedElement(checkOutSignInPopUp).isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (getWebDriver().getPageSource().contains("Guest checkout is disabled.")) {
            LOG.info("\n ************************* ERROR - GUEST CHECKOUT DISABLED - BUG - 26280");
        }
    }

    public void checkOutPopUpselectCreateNewAccount() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
            case "pl":
            case "mx":
            case "vusemx":
            case "vuseza":
                waitForExpectedElement(GUEST_CREATE_AN_ACCOUNT_LINK_CX_REDESIGN, 20);
                clickByElementByQueryJSExecutor(GUEST_CREATE_AN_ACCOUNT_LINK_CX_REDESIGN);
                break;
            case "vuseco":
                if (webDriver.getTitle().equals("Carrito")){
                    waitForPage();
                    clickByElementByQueryJSExecutor(PROCEED_TO_CHECKOUT_BUTTON);
                }
                waitForExpectedElement(GUEST_CREATE_AN_ACCOUNT_LINK_CX_REDESIGN, 20);
                clickByElementByQueryJSExecutor(GUEST_CREATE_AN_ACCOUNT_LINK_CX_REDESIGN);
                break;
            case "fr":
            case "vusefr":
                waitForExpectedElement(GUEST_CREATE_AN_ACCOUNT_LINK_CX_REDESIGN, 20).click();
                break;
            default:
                waitForExpectedElement(checkOutPopUpCreateAnAccountLink, 10).click();
        }
    }

    public void checkOutPopUpSignIn(String username, String password) {
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
            case "vuseco":
                try {
                    waitForExpectedElement(checkOutPopUpPasswordAddressInput, 10);
                    enterDataAndWait(checkOutPopUpEmailAddressInput, username);
                    enterDataAndWait(checkOutPopUpPasswordAddressInput, password);
                    clickUsingJS(checkOutPopUpSignInButton);
                    if (getWebDriver().findElements(CHECKOUT_CARTRIDGE_POPUP).size() == 0) {
                        waitForExpectedElement(MX_CREDITCARD_OPTION, 60);
                    }
                } catch (Exception e) {
                    LOG.info("Failed to login into application due to error: " + e.getMessage());
                }
                break;
            case "vypeit":
            case "vuseit":
                loginPage.waitForExpectedElement(loginPage.EMAIL_INPUTBOX, 20).sendKeys(username);
                loginPage.waitForExpectedElement(loginPage.PASSWORD_INPUTBOX,10).sendKeys(password);
                loginPage.clickByElementByQueryJSExecutor(loginPage.SIGNIN_BUTTON);
                break;
            case "veloza":
                clickUsingJS(UPDATE_ID_VELOZA);
                loginPage.waitForExpectedElement(loginPage.EMAIL_INPUTBOX, 20).sendKeys(username);
                loginPage.waitForExpectedElement(loginPage.PASSWORD_INPUTBOX,10).sendKeys(password);
                loginPage.clickByElementByQueryJSExecutor(loginPage.SIGNIN_BUTTON);
                break;
            //Neha - Not sure for which locales else condition is running so added the steps in default condition,slowing down execution for MX
            default:
                boolean loggedIn = getWebDriver().getPageSource().contains("Customer Login");
                LOG.info("\n ******* Customer Login text present : " + getWebDriver().getPageSource().contains("Customer Login"));
                try {
                    if (loggedIn == false && !waitForExpectedElement(checkOutPopUpPasswordAddressInput).isDisplayed()) {
                        LOG.info("\n User is already logged in, logging them out");
                        getWebDriver().findElement(By.linkText("Logout")).click();
                        waitForExpectedElement(PERSON_ICON, 30).click();
                        LOG.info("\n Now Attempting to Login again");
                    }
                } catch (Exception e) {
                    LOG.info("Still in the attemping to logout process");
                }

                try {
                    webDriver.findElement(By.linkText("Logout")).click();
                } catch (Exception e) {
                    LOG.info("No logout option present - continuing");
                }

                try {
                    if (getCurrentUrl().contains("gb/en") || getCurrentUrl().contains("fr/fr") || getCurrentUrl().contains("za/en")) {
                        waitForExpectedElement(EMAIL_POPUP_LINK,10).clear();
                        waitForExpectedElement(EMAIL_POPUP_LINK).sendKeys(username);
                    } else {
                        waitForExpectedElement(checkOutPopUpEmailAddressInput, 10).sendKeys(username);
                    }
                } catch (Exception e) {
                    waitForExpectedElement(EMAIL_POPUP_LINK, 10).sendKeys(password);
                }
                waitForExpectedElement(checkOutPopUpPasswordAddressInput).sendKeys(password);
                clickUsingJS(checkOutPopUpSignInButton);
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    public void checkOutPopUpSignInLyftLab() {
        String username = UrlBuilder.getMessage("loginValidEmail.key");
        String password = UrlBuilder.getMessage("loginValidPassword.key");
        waitForExpectedElement(checkOutPopUpPasswordAddressInput, 10);
        boolean loggedIn = getWebDriver().getPageSource().contains("Customer Login");

        try {
            if (loggedIn == false && !waitForExpectedElement(checkOutPopUpPasswordAddressInput).isDisplayed()) {
                getWebDriver().findElement(LOGOUT_LINK).click();
                waitForExpectedElement(PERSON_ICON, 10).click();
            }
        } catch (Exception e) {
            LOG.info("Still in the attemping to logout process");
        }

        try {
            webDriver.findElement(LOGOUT_LINK).click();
        } catch (Exception e) {
            LOG.info("No logout option present - continuing");
        }
        try {
            waitForExpectedElement(checkOutPopUpEmailAddressInput, 10).sendKeys(username);
        } catch (Exception e) {
            waitForExpectedElement(EMAIL_POPUP_LINK, 10).sendKeys(password);
        }
        waitForExpectedElement(checkOutPopUpPasswordAddressInput).sendKeys(password);
        clickUsingJS(checkOutPopUpSignInButton);
    }

    public void clickHamburgerMenu() {
        try {
            waitForExpectedElement(SANDWICH_MENU_ICON, 10).click();
        } catch (Exception e) {
            hoverOnElement(By.cssSelector("a.more"));
        }
    }

    public void clickMobileHamburgerMenu() {
        if(UrlBuilder.getLocale().equals("it"))
        {
            waitForAjaxElementNotToBePresent(getWebDriver(),10);
            clickUsingJS(M_SANDWICH_MENU_ICON_GLO_IT);
        }
        else {
            waitForExpectedElement(M_SANDWICH_MENU_ICON, 10).click();
        }
    }

    public void addNewAddress() {
        switch (UrlBuilder.getLocale()) {
            case "pl":
                if ((UrlBuilder.isMobile())
                        && !UrlBuilder.isDesktop())
                    waitForItemToBeClickableAndClick(accountDashboardPage.M_MYACCOUNT_ACCORDION_UK,10);
                if(UrlBuilder.isMobile())
                {
                    waitForExpectedElement(M_ADDRESS_BOOK_ITEMS_GLOPL,10).click();
                }
                else {
                    waitForExpectedElement(ADDRESS_BOOK_ITEMS).click();
                }
                addNewAddressPage.populateAllAddressInputFieldsIncludingFirstAndLastName();
                clickByElementByQueryJSExecutor(BUTTON_ADDRESS_SAVE);
                break;
            default:
                clickByElementByQueryJSExecutor(ADD_NEW_ADDRESS);
                addNewAddressPage.populateAllAddressInputFieldsIncludingFirstAndLastName();
                clickByElementByQueryJSExecutor(BUTTON_ADDRESS_SAVE);
                break;
        }
    }

    public void cycleThurHamBurgerLinks() {
        isElementPresent(hamBurgerTable);
        for (WebElement hamBurgerLink : visibilityOfAllElementsLocatedBy(hamBurgerTable)) {
            LOG.info("\nHamBurger Link: " + hamBurgerLink.getText());
        }
    }

    public void cycleThurHamBurgerLinksAndSelect(String linkToMatch) {
        isElementPresent(hamBurgerTable);
        LOG.info("LINK TO MATCH :: " + linkToMatch);
        for (WebElement hamBurgerLink : visibilityOfAllElementsLocatedBy(hamBurgerTable)) {
            LOG.info("\n LINK : " + hamBurgerLink.getText());
            if (hamBurgerLink.getText().equalsIgnoreCase(linkToMatch)) {
                LOG.info("\n ********************** Match found!!!!!");
                hamBurgerLink.click();
            }
        }
    }

    public Integer cycleThurHamBurgerLinksAndMatchArray(ArrayList<String> linksToMatch) {
        isElementPresent(hamBurgerTable);
        int counter = 0;
        for (WebElement hamBurgerLink : visibilityOfAllElementsLocatedBy(hamBurgerTable)) {
            LOG.info("\n LINK : " + hamBurgerLink.getText());
            for (String link : linksToMatch) {
                if (hamBurgerLink.getText().contains(link)) {
                    LOG.info("MATCH FOUND!! : " + link);
                    counter++;
                }
            }
        }
        LOG.info("Counter Total : " + counter + "\n");
        return counter;
    }

    public void closeHamburgerMenu() {
        clickByElementByQueryJSExecutor(closeHamburgerMenuIcon);
    }

    public void closeBasketSlidingPanel() {
        try {
            if (UrlBuilder.isSamsungMobile()) {
                waitForExpectedElement(basketCloseSlidingPanelButtonMobile, 10);
                clickByElementByQueryJSExecutor(basketCloseSlidingPanelButtonMobile);
            } else {
                waitForExpectedElement(basketCloseSlidingPanelButton, 10);
                waitForExpectedElement(basketCloseSlidingPanelButton, 10).click();
            }
        } catch (Exception e) {
            LOG.info("Couldn't see close basket icon");
        }
    }

    public void emptyBasket() {
        if (!isBasketEmpty()) {
            waitForLoaderIconDisappear();
            clickBasketIcon();
            switch (UrlBuilder.getLocale()) {
                case "it":
                case "kz":
                case "glode":
                    if (isElementPresentByby(By.cssSelector(BASKET_DELETE_BTN_IT))) {
                        int productItemCount = waitForExpectedElements(By.cssSelector(BASKET_DELETE_BTN_IT)).size();
                        while (productItemCount > 0) {
                            LOG.info("Product items in basket: " + String.valueOf(waitForExpectedElements(By.cssSelector(BASKET_DELETE_BTN_IT)).size()));
                            retryingFindClick(By.cssSelector(BASKET_DELETE_BTN_IT));
                            waitForExpectedElement(emptyBasketConfirmPopUpOk, 10);
                            clickByElementByQueryJSExecutor(emptyBasketConfirmPopUpOk);
                            closeAttentionDialogIfPresent();
                            productItemCount--;
                        }
                        LOG.info("Basket is empty");
                    }
                    closeBasketSlidingPanel();
                    break;
                case "vusede":
                case "uk":
                case "fr":
                case "vuseza":
                    clickBasketIcon();
                    try {
                        waitForExpectedElement(VIEW_BASKET).click();
                    } catch (Exception e) {
                        clickBasketIcon();
                        waitForExpectedElement(VIEW_BASKET).click();
                    }
                    while (getWebDriver().findElements(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX).size() != 0) {
                        clickByElementByQueryJSExecutor(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX);
                        clickByElementByQueryJSExecutor(CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_UK_CX);
                    }
                    break;
                case "vuseuk":
                    try {
                        waitForExpectedElement(VIEW_BASKET).click();
                    } catch (Exception e) {
                        clickBasketIcon();
                        waitForExpectedElement(VIEW_BASKET).click();
                    }
                    while (getWebDriver().findElements(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX).size() != 0) {
                        clickByElementByQueryJSExecutor(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX);
                        try{
                            clickByElementByQueryJSExecutor(CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_UK_CX);}
                        catch(Exception ex){
                            waitAndClickByElementByJSExecutor(CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_FR_CX,10);
                        }
                    }
                    break;
                case "vusefr":
                    try {
                        waitForExpectedElement(VIEW_BASKET).click();
                    } catch (Exception e) {
                        clickBasketIcon();
                        waitForExpectedElement(VIEW_BASKET).click();
                    }
                    while (getWebDriver().findElements(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX).size() != 0) {
                        LOG.info(getWebDriver().findElements(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX).size()+" items was found in the basket.");
                        clickByElementByQueryJSExecutor(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX);
                        clickByElementByQueryJSExecutor(CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_FR_CX);
                    }
                    LOG.info("Basket is empty.");
                    break;
                case "mx":
                case "vusemx":
                case "vypeit":
                case "vuseit":
                case "vuseco":
                    clickBasketIcon();
                    try {
                        waitForExpectedElement(VIEW_BASKET);
                        clickByElementByQueryJSExecutor(VIEW_BASKET);
                    } catch (Exception e) {
                        clickBasketIcon();
                        waitForExpectedElement(VIEW_BASKET,20);
                        clickByElementByQueryJSExecutor(VIEW_BASKET);
                    }
                    while (getWebDriver().findElements(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX).size() != 0) {
                        clickByElementByQueryJSExecutor(REMOVE_ITEM_FROM_BASKETPAGE_ICON_UK_CX);
                        clickByElementByQueryJSExecutor(CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_UK_CX);
                    }
                    break;
                case "lyftse":
                    try {
                        while (getWebDriver().findElements(DELETE_CARTITEMS_LINK).size() != 0) {
                            waitForExpectedElement(DELETE_CARTITEMS_LINK, 10).click();
                            waitForExpectedElement(CONFIRM_ITEM_REMOVAL_FROM_BASKETPAGE_SE, 10).click();
                            waitForElementToDisappear(emptyBasketIcon, 10);
                            waitForAjaxElementNotToBePresent(getWebDriver(),3);
                        }
                        closeBasketSlidingPanel();
                    } catch (Exception e) {
                        LOG.info("Basket is empty");
                        closeBasketSlidingPanel();
                    }
                    break;
                case "pl":
                    try {
                        clickByElementByQueryJSExecutor(PROCEED_TO_VIEWBASKET);
                        while (Integer.parseInt(waitForExpectedElement(basketQty).getText()) != 0) {
                            waitForExpectedElement(btnRemoveMiniBasket, 10);
                            clickByElementByQueryJSExecutor(btnRemoveMiniBasket);
                            clickByElementByQueryJSExecutor(ACCEPT_OPTION_REMOVE_BASKET_PAGE);
                            waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                            clickByElementByQueryJSExecutor(CLOSE_FREE_GIFT_WINDOW);
                            waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                        }
                        closeBasketSlidingPanel();
                    } catch (Exception e) {
                        LOG.info("Basket is empty");
                        closeBasketSlidingPanel();
                    }
                    break;
                case "ie":
                case "vusedk":
                    if (isElementPresentByby(DISPLAYED_EMPTY_BASKET_ICON)) {
                        int productItemCount = waitForExpectedElements(DISPLAYED_EMPTY_BASKET_ICON).size();
                        while (productItemCount > 0) {
                            LOG.info("Product items in basket: " + String.valueOf(waitForExpectedElements(DISPLAYED_EMPTY_BASKET_ICON).size()));
                            retryingFindClick(DISPLAYED_EMPTY_BASKET_ICON);
                            waitForExpectedElement(emptyBasketConfirmPopUpOk, 10).click();
                            closeAttentionDialogIfPresent();
                            productItemCount--;
                        }
                        LOG.info("Basket is empty");
                    }
                    closeBasketSlidingPanel();
                    break;
                case "glojp":
                    try{
                        productItemCount = waitForExpectedElements(BASKET_ITEMS).size();
                    }catch(TimeoutException te){
                        productItemCount = 0;
                    }
                    while (productItemCount > 0) {
                        waitForExpectedElements(BASKET_ITEMS).size();
                        retryingFindClick(DELETE_PRODUCT_ICON);
                        waitForElementToDisappear(BASKET_LOADER,20);
                        try {
                            productItemCount = waitForExpectedElements(BASKET_ITEMS).size();
                        }catch(TimeoutException te){
                            productItemCount--;
                        }
                    }
                    Assert.assertEquals(productItemCount, 0);
                    LOG.info("Basket is empty");
                    closeOcrPopupOnBasket();
                    break;
                default:
                    try {
                        while (waitForExpectedElement(emptyBasketIcon, 10).isDisplayed()) {
                            waitForExpectedElement(emptyBasketIcon, 10).click();
                            waitForExpectedElement(emptyBasketConfirmPopUpOk, 10).click();
                            waitForElementToDisappear(emptyBasketIcon, 10);
                        }
                        closeBasketSlidingPanel();
                    } catch (Exception e) {
                        LOG.info("Basket is empty");
                        closeBasketSlidingPanel();
                    }
                    break;
            }
        }
    }

    public void closeOcrPopupOnBasket(){
        try {
            if (waitForExpectedElement(OCR_POPUP).isDisplayed()) {
                try {
                    waitForAjaxElementNotToBePresent(getWebDriver(),10);
                    waitForExpectedElement(CLOSE_OCR_POPUP,10);
                    clickByElementByQueryJSExecutor(CLOSE_OCR_POPUP);
                } catch (Exception e) {
                    waitForAjaxElementNotToBePresent(getWebDriver(),10);
                    clickByElement(CLOSE_OCR_POPUP);
                }
            }
        } catch (Exception e) {
            LOG.info("OCR popup is not displayed");
        }
    }

    public void closeAttentionDialogIfPresent() {
        int attampt = 0;
        while (isElementPresent(ATTENTION_ALERT, 3) && attampt < 2) {
            LOG.info("Attention dialog is shown.");
            waitForExpectedElement(OK_BUTTON_ATTENTION_ALERT).click();
            attampt++;
            LOG.info("Attention dialog is shown and closed.");
        }
    }


    public void clickBasketIcon() {
        switch (UrlBuilder.getLocale()) {
            case "glojp":
                clickByElementByQueryJSExecutor(BASKET_ICON_JP);
                break;
            case "velopl":
               /* if(waitForExpectedElement(pdp.MINICART_CLOSE_BUTTON_VELOPL, 3).isDisplayed()){
                    pdp.clickByElementByQueryJSExecutor(pdp.MINICART_CLOSE_BUTTON_VELOPL);
                    waitForExpectedElement(AVALANCHE_BASKET_ICON).click();
                }*/
                waitForExpectedElement(AVALANCHE_BASKET_ICON_PL).click();
                break;
            case "veloza":
            case "velobe":
                try {
                    waitForExpectedElement(AVALANCHE_BASKET_ICON).click();
                } catch (Exception e ) {
                    clickUsingJS(AVALANCHE_BASKET_ICON);
                }
                break;
            default:
                clickByElementByQueryJSExecutor(BASKET_ICON);
        }
    }

    private Boolean isBasketEmpty() {
        switch (UrlBuilder.getLocale()) {
            case "glojp":
                try {
                    waitForExpectedElement(GLOJP_BASKET_COUNT).getText();
                    System.out.println("\nBasket needs emptying");
                    return false;
                } catch (Exception e) {
                    System.out.println("\n Basket is empty!");
                    return true;
                }
            case "mx":
            case "vusemx":
                waitForAjaxElementNotToBePresent(getWebDriver(), 30);
                return waitForExpectedElement(BASKET_ICON).getText().contains("$0.00") ||
                        waitForExpectedElement(BASKET_ICON).getText().contains("$0,00");
            case "uk":
            case "vuseuk":
                waitForExpectedElement(BASKET_ICON, 10);
                if (waitForExpectedElement(BASKET_ICON).getText().contains("0.00")) {
                    LOG.info("Basket is empty");
                }
                return waitForExpectedElement(BASKET_ICON).getText().contains("0.00");
            case "vusedk":
                return waitForExpectedElement(BASKET_ICON_DK).getText().contains("0.00") ||
                        waitForExpectedElement(BASKET_ICON_DK).getText().contains("0,00");
            case "vuseza":
                return waitForExpectedElement(BASKET_ICON_ZA).getText().contains("0.00") ||
                        waitForExpectedElement(BASKET_ICON_ZA).getText().contains("0,00");
            case "vusefr":
            case "vypeit":
            case "vuseit":
                waitForAjaxElementNotToBePresent(webDriver,10);
                return waitForExpectedElement(BASKET_ICON).getText().contains("0.00") ||
                        waitForExpectedElement(BASKET_ICON).getText().contains("0,00");
            case "vuseco":
                waitForAjaxElementNotToBePresent(webDriver,15);
                return waitForExpectedElement(VUSECO_MINICART_AMOUNT).getText().equals("$0") ||
                        waitForExpectedElement(VUSECO_MINICART_AMOUNT).getText().contains("0,00");
            case "it":
                try {
                    waitForExpectedElement(BASKET_ICON_GLOIT).getText();
                    LOG.info("\nBasket needs emptying");
                    return false;
                } catch (Exception e) {
                    LOG.info("\n Basket is empty!");
                    return true;
                }
            default:
                return waitForExpectedElement(BASKET_ICON).getText().contains("0.00") ||
                        waitForExpectedElement(BASKET_ICON).getText().contains("0,00");
        }
    }

    public void resizeBrowserWindow(int i, int i1) {
        Dimension d = new Dimension(i, i1);
        webDriver.manage().window().setSize(d);
    }

    public void enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit() {
        enterDataAndWait(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        enterDataAndWait(NEWSLETTER_SURNAME, RANDOMNAMEDATA);
        waitForExpectedElement(NEWSLETTER_EMAIL).sendKeys(RANDOMAILADDRESS);
        if (UrlBuilder.getLocale().equals("uk")) {
            clickByElementByQueryJSExecutor(NEWSLETTER_EMAIL_TICKBOX);
        } else {
            try {
                waitForExpectedElement(NEWSLETTER_EMAIL_TICKBOX).click();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        waitForExpectedElement(NEWSLETTERSUBMIT).click();
    }

    public void enterNamesAndEmailAddressesToNewsLetterSignUpAndSubmit(String strEmailAddress) {
        enterDataAndWait(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        enterDataAndWait(NEWSLETTER_SURNAME, RANDOMNAMEDATA);
        if (strEmailAddress.equals("randomEmailAddress"))
            waitForExpectedElement(NEWSLETTER_EMAIL).sendKeys(RANDOMAILADDRESS);
        if (UrlBuilder.getLocale().equals("uk")) {
            waitForExpectedElement(NEWSLETTER_EMAIL).click();
        } else {
            waitForExpectedElement(NEWSLETTER_EMAIL_TICKBOX).click();
        }
        waitForExpectedElement(NEWSLETTERSUBMIT).click();
    }

    public void enterNewsletterSubscriptionDetailsForVypeIT(String strEmailAddress) throws InterruptedException {
        enterDataAndWait(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        enterDataAndWait(NEWSLETTER_SURNAME, RANDOMNAMEDATA2);
        waitForExpectedElement(DATE_PICKER, 10).click();
        waitClearAndEnterText(NEWSLETTER_DOB_VUSE,"21/10/1996");
        if (strEmailAddress.isEmpty()) {
            String emailAddress = RandomGenerator.randomEmailAddress(10);
            scenarioContext.setContext(NEW_USER_EMAIL_ID, emailAddress);
            enterDataAndWait(By.cssSelector("#newsletter"), emailAddress);
        } else {
            try {
                enterDataAndWait(By.cssSelector("#newsletter"), UrlBuilder.getMessage(strEmailAddress));
            } catch (Exception e) {
                enterDataAndWait(By.cssSelector("#newsletter"), strEmailAddress);
            }
        }
        WebElement chkDeclarationToBAT = waitForExpectedElement(CHECK_DECLARATION_BAT);
        WebElement chkTelephoneWhatsapp = waitForExpectedElement(CHECK_TELEPHONE_WHATSAPP);
        WebElement chkEmail = waitForExpectedElement(NEWSLETTER_EMAIL_TICKBOX);
        clickElementByQueryJSExecutor(chkDeclarationToBAT);
        clickElementByQueryJSExecutor(chkTelephoneWhatsapp);
        clickElementByQueryJSExecutor(chkEmail);
        try {
            waitForExpectedElement(NEWSLETTER_BUTTON).click();
        }catch (Exception e){
            clickUsingJS(NEWSLETTER_BUTTON);
        }
        Thread.sleep(5000);
    }

    public void enterNewsletterSubscriptionDetailsForVeloPl(String strEmailAddress) throws InterruptedException{
        enterDataAndWait(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        enterDataAndWait(NEWSLETTER_SURNAME, RANDOMNAMEDATA2);
        if (strEmailAddress.isEmpty()) {
            String emailAddress = RandomGenerator.randomEmailAddress(10);
            scenarioContext.setContext(NEW_USER_EMAIL_ID, emailAddress);
            waitClearAndEnterText(INPUT_NEWSLETTER, emailAddress);
        } else {
            try {
                waitClearAndEnterText(INPUT_NEWSLETTER, UrlBuilder.getMessage(strEmailAddress));
            } catch (Exception e) {
                waitClearAndEnterText(INPUT_NEWSLETTER, strEmailAddress);
            }
        }
        enterText(PHONE_TXT_BOX_VELOPL, "7944816325");
        clickByElementByQueryJSExecutor(SHOW_CONSENT_VELOPL);
        clickByElementByQueryJSExecutor(CHECKBOX_ADULT_USER_VELOPL);
        clickByElementByQueryJSExecutor(CHECKBOX_CONSENT_PERSONALDATA_VELOPL);
        clickByElementByQueryJSExecutor(CHECKBOX_MARKETING_VELOPL);
        clickByElementByQueryJSExecutor(NEWSLETTER_BUTTON);
    }

    public void assertSubscribedUserMessageWhenSuccessMessageNotDisplayed(String strEmailAddress) throws Throwable {
        enterNewsletterSubscriptionDetails(strEmailAddress);
        assertErrorMessageForAlreadyUsedEmail(UrlBuilder.getMessage("emailAddressAlreadySubscribeTextMsg.key"));
    }

    public void enterNewsletterSubscriptionDetailsForVypeMX(String strEmailAddress) throws InterruptedException {
        waitClearAndEnterText(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        waitClearAndEnterText(NEWSLETTER_SURNAME, RANDOMNAMEDATA2);
        if (strEmailAddress.isEmpty()) {
            String emailAddress = RandomGenerator.randomEmailAddress(10);
            scenarioContext.setContext(NEW_USER_EMAIL_ID, emailAddress);
            waitClearAndEnterText(INPUT_NEWSLETTER, emailAddress);
        } else {
            try {
                waitClearAndEnterText(INPUT_NEWSLETTER, UrlBuilder.getMessage(strEmailAddress));
            } catch (Exception e) {
                waitClearAndEnterText(INPUT_NEWSLETTER, strEmailAddress);
            }
        }
        if(doesURLContain("mx/es")){
            enterDataAndWait(NEWSLETTER_DOB_FIELD, UrlBuilder.getMessage("ValidDOB.key"));
            assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage("dateOfBirthText.key").toLowerCase()));}
        clickByElementByQueryJSExecutor(RECEIVE_MARKETING_EMAIL_CHECKBOX);
        clickByElementByQueryJSExecutor(SELF_DECLARATION_CHECKBOX);
        waitForExpectedElement(NEWSLETTER_VERIFY_BUTTON).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),5);
    }

    public void enterNewsletterSubscriptionDetailsForVype(String strEmailAddress) throws InterruptedException {
        waitForExpectedElement(NEWSLETTER_FIRSTNAME, 10);
        enterDataAndWait(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        enterDataAndWait(NEWSLETTER_SURNAME, RANDOMNAMEDATA2);
        if (strEmailAddress.isEmpty()) {
            String emailAddress = RANDOMAILADDRESS;
            scenarioContext.setContext(NEW_USER_EMAIL_ID, emailAddress);
            enterDataAndWait(NEWSLETTER_EMAIL, emailAddress);
        } else {
            try {
                enterDataAndWait(NEWSLETTER_EMAIL, UrlBuilder.getMessage(strEmailAddress));
            } catch (Exception e) {
                enterDataAndWait(NEWSLETTER_EMAIL, strEmailAddress);
            }
        }
        switch (UrlBuilder.getLocale()) {
            case "vuseza":
                clickByElementByQueryJSExecutor(NEWSLETTER_EMAIL_TICKBOX_ZA);
                clickByElementByQueryJSExecutor(NEWSLETTER_SIGNUP_BUTTON_ZA);
                break;
            case "fr":
            case "vusefr":
            case "uk":
            case "vuseuk":
                enterDataAndWait(NEWSLETTER_DOB_FIELD, UrlBuilder.getMessage("ValidDOB.key"));
                clickByElementByQueryJSExecutor(NEWSLETTER_EMAIL_TICKBOX);
                clickByElementByQueryJSExecutor(NEWSLETTER_SIGNUP_BUTTON);
                break;
        }
    }

    public void enterNewsletterSubscriptionDetails(String strEmailAddress) throws Throwable {
        clickOnNewsletterButton();
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "vuseit":
                enterNewsletterSubscriptionDetailsForVypeIT(strEmailAddress);
                break;
            case "ie":
            case "mx":
            case "vusemx":
            case "vuseza":
                enterNewsletterSubscriptionDetailsForVypeMX(strEmailAddress);
                break;
            case "vusefr":
            case "uk":
            case "vuseuk":
                enterNewsletterSubscriptionDetailsForVype(strEmailAddress);
                break;
            case "velopl":
                enterNewsletterSubscriptionDetailsForVeloPl(strEmailAddress);
                break;
            default:
        }
    }

    public void enterNamesAndRandomEmailToNewsLetterSignUpAndSubmit(String strEmailAddress, String strSubscription) throws Throwable {
        String actualMessage = "";
        enterNewsletterSubscriptionDetails(strEmailAddress);
        switch (strSubscription) {
            case "alreadySubscribed":
                if (getWebDriver().findElements(eleNewsletterErrMessage).size() > 0) {
                    assertErrorMessageForAlreadyUsedEmail(UrlBuilder.getMessage("emailAddressAlreadySubscribeTextMsg.key"));
                }
                else if (UrlBuilder.getLocale().equals("velopl")){ waitForExpectedElement(NEWSLETTER_ERROR_MSG_PL,10);
                    String actualError = waitForExpectedElement(NEWSLETTER_ERROR_MSG_PL).getText();
                    assertTrueWithCustomError(UrlBuilder.getMessage("emailAddressAlreadySubscribeTextMsg.key"), actualError);
                }
                break;
            case "invalidEmail":
                if (getWebDriver().findElements(NEWSLETTER_ERROR_MSG).size() > 0) {
                    String actualError = waitForExpectedElement(NEWSLETTER_ERROR_MSG).getText();
                    assertTrueWithCustomError(UrlBuilder.getMessage("loginInvalidEmailErrorMsg.key"), actualError);
                }
                break;
            case "newUserEmail":
                switch (UrlBuilder.getLocale()) {
                    case "mx":
                    case "vusemx":
                        actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESSS_BLOCK).getText();
                        assertTrueWithCustomError(UrlBuilder.getMessage("emailVerificationText.key"), actualMessage);
                        break;
                    case "uk":
                    case "vuseuk":
                        actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESSS_BLOCK).getText();
                        assertTrueWithCustomError(UrlBuilder.getMessage("emailVerificationText.key"), actualMessage);
                        String EMAIL_ADDRESS = (String) scenarioContext.getContext(Context.NEW_USER_EMAIL_ID);
                        registrationPage.verifyEmailAndReturn(EMAIL_ADDRESS, "newsletter");
                        actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESS_MSG, 10).getText();
                        assertTrue(actualMessage.contains(UrlBuilder.getMessage("subscriptionConfirmedText.key")));
                        break;
                    case "vypeit":
                    case "vuseit":
                        actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESSS_BLOCK).getText();
                        assertTrueWithCustomError(UrlBuilder.getMessage("emailVerificationText.key"), actualMessage);
                    case "velopl":
                        actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESSS_BLOCK).getText();
                        assertTrueWithCustomError(UrlBuilder.getMessage("subscriptionConfirmedText.key"), actualMessage);
                        break;
                    case "vusefr":
                        if (getWebDriver().findElements(NEWSLETTER_SUCCESSS_BLOCK_VUSEFR).size() > 0) {
                            actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESSS_BLOCK_VUSEFR,30).getText();
                            assertTrue(isElementPresentByby(NEWSLETTER_POPUP_CLOSE));
                            waitForExpectedElement(NEWSLETTER_POPUP_CLOSE,10).click();
                            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                        }
                        if (getWebDriver().findElements(NEWSLETTER_SUCCESS_MSG).size() > 0)
                            actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESS_MSG).getText();
                        if (!actualMessage.isEmpty())
                            assertTrueWithCustomError(UrlBuilder.getMessage("subscribeSuccessText.key"), actualMessage);
                        else
                            assertSubscribedUserMessageWhenSuccessMessageNotDisplayed(scenarioContext.getContext(NEW_USER_EMAIL_ID).toString());
                        break;
                    default:
                        if (getWebDriver().findElements(NEWSLETTER_SUCCESSS_BLOCK).size() > 0) {
                            waitForPage();
                            actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESSS_BLOCK,30).getText();
                            waitForExpectedElement(NEWSLETTER_POPUP_CLOSE,10).click();
                            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                        }
                        if (getWebDriver().findElements(NEWSLETTER_SUCCESS_MSG).size() > 0)
                            actualMessage = waitForExpectedElement(NEWSLETTER_SUCCESS_MSG).getText();
                        if (!actualMessage.isEmpty())
                            assertTrueWithCustomError(UrlBuilder.getMessage("subscribeSuccessText.key"), actualMessage);
                        else
                            assertSubscribedUserMessageWhenSuccessMessageNotDisplayed(scenarioContext.getContext(NEW_USER_EMAIL_ID).toString());
                }
        }
    }

    public void enterNamesAndEmailToNewsLetterSignUpAndSubmit() {
        enterDataAndWait(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        enterDataAndWait(NEWSLETTER_SURNAME, RANDOMNAMEDATA);
        String emailAddress = RandomGenerator.randomEmailAddress(10);
        waitForExpectedElement(NEWS_LETTER).sendKeys(emailAddress);
        WebElement receiveMarketingEmailsTickBox = waitForExpectedElement(AGREE_NEWSLETTEREMAIL_CHECKBOX);
        receiveMarketingEmailsTickBox.click();
        waitForExpectedElement(NEWSLETTER_SUBSCRIBE_BUTTON).click();
    }

    public void enterNamesAndInvalidEmailToNewsLetterSignUpAndSubmit() {
        enterDataAndWait(NEWSLETTER_FIRSTNAME, RANDOMNAMEDATA);
        enterDataAndWait(NEWSLETTER_SURNAME, RANDOMNAMEDATA);
        String emailAddress = "invalidEmail";
        waitForExpectedElement(By.cssSelector("#newsletter")).sendKeys(emailAddress);
        waitForExpectedElement(By.cssSelector("#newsletter-validate-detail > fieldset > div.newsletter-agree.required > label")).click();
        waitForExpectedElement(By.cssSelector("#newsletter-validate-detail > fieldset > div.actions > button > span")).click();
    }

    public void inputFieldsPresentAsExpected() {
        assertTrue(waitForExpectedElement(By.cssSelector("#firstname"), 10).isDisplayed());
        assertTrue(waitForExpectedElement(By.cssSelector("#lastname")).isDisplayed());
        assertTrue(waitForExpectedElement(By.cssSelector("#newsletter")).isDisplayed());
    }

    public void emptyBasketAlpha() {
        String basketValue = "";
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
                basketValue = waitForExpectedElement(BASKET_ICON).getText();
                LOG.info("BASKET VALUE : " + waitForExpectedElement(BASKET_ICON).getText());
                if (waitForExpectedElement(BASKET_ICON).getText().contains("My Basket")) {
                    LOG.info("Basket is empty : value is : " + basketValue);
                } else {
                    LOG.info("Basket is NOT empty : value is : " + basketValue);
                }
                break;
            default:
                basketValue = waitForExpectedElement(BASKET_ICON).getText();
                LOG.info("BASKET VALUE : " + waitForExpectedElement(BASKET_ICON).getText());
                if (waitForExpectedElement(BASKET_ICON).getText().contains("My Basket")) {
                    LOG.info("Basket is empty : value is : " + basketValue);
                } else {
                    LOG.info("Basket is NOT empty : value is : " + basketValue);
                }
        }
    }

    public void clickOnInstagramIcon() {
        switch (UrlBuilder.getLocale()){
            case "lyftse":
                waitForExpectedElement(INSTAGRAM_ICON_LYFT,5).click();
            break;
            case "veloza":
                waitForExpectedElement(INSTAGRAM_FOOTER_LINK, 10);
                clickUsingJS(INSTAGRAM_FOOTER_LINK);
                waitForAjaxElementNotToBePresent(webDriver,3);
                break;
            case "velopl":
                waitForExpectedElement(INSTAGRAM_FOOTER_LINK_PL_FOOTER, 10);
                clickUsingJS(INSTAGRAM_FOOTER_LINK_PL_FOOTER);
                waitForAjaxElementNotToBePresent(webDriver,3);
                break;
            default:
                try {
                    waitForExpectedElement(lnkInstagram, 10);
                    getWebDriver().findElements(lnkInstagram).get(0).click();
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    LOG.info("Failed to click on Instragram Icon due to exception: " + ex.getMessage());
                }
        }
    }

    public void clickOnYouTubeIcon() {
        switch (UrlBuilder.getLocale()) {
            case "veloza":
                waitForExpectedElement(YOUTUBE_FOOTER_LINK, 10);
                clickUsingJS(YOUTUBE_FOOTER_LINK);
                waitForAjaxElementNotToBePresent(webDriver,3);
                break;
        default:
        try {
            waitForExpectedElement(YOUTUBE_FOOTER_LINK, 10);
            getWebDriver().findElements(YOUTUBE_FOOTER_LINK).get(0).click();
            Thread.sleep(1000);
        } catch (Exception ex) {
            LOG.info("Failed to click on Instragram Icon due to exception: " + ex.getMessage());
        }}
    }

    public void clickOnTwitterIcon() {
        switch (UrlBuilder.getLocale()) {
            case "veloza":
                waitForExpectedElement(TWITTER_FOOTER_LINK, 10);
                clickUsingJS(TWITTER_FOOTER_LINK);
                waitForAjaxElementNotToBePresent(webDriver,3);
                break;
            default:
        try {
            waitForExpectedElement(TWITTER_FOOTER_LINK, 10);
            getWebDriver().findElements(TWITTER_FOOTER_LINK).get(0).click();
            Thread.sleep(1000);
        } catch (Exception ex) {
            LOG.info("Failed to click on Instragram Icon due to exception: " + ex.getMessage());
        }}
    }

    public void clickOnFacebookIcon() {
        try {
            switch (UrlBuilder.getLocale()) {
                case "vusefr":
                    waitForExpectedElement(FACEBOOK_LINK_FR, 10).click();
                    break;
                case "lyftse":
                    waitForExpectedElement(FACEBOOK_ICON_LYFT,5).click();
                    break;
                case "veloza":
                    waitForExpectedElement(FACEBOOK_FOOTER_LINK, 10);
                    clickUsingJS(FACEBOOK_FOOTER_LINK);
                    waitForAjaxElementNotToBePresent(webDriver,3);
                    break;
                case "velopl":
                    waitForExpectedElement(FACEBOOK_FOOTER_LINK_PL_FOOTER, 10);
                    clickUsingJS(FACEBOOK_FOOTER_LINK_PL_FOOTER);
                    waitForAjaxElementNotToBePresent(webDriver,3);
                    break;
                default:
                    waitForExpectedElement(lnkFacebook, 10);
                    getWebDriver().findElements(lnkFacebook).get(0).click();
                    Thread.sleep(1000);
            }
        } catch (Exception ex) {
            LOG.info("Failed to click on Facebook Icon due to exception: " + ex.getMessage());
        }
    }

    public void assertErrorMessageForAlreadyUsedEmail(String strErrorMessage) {
        try {
            String actualMessage = waitForExpectedElement(eleNewsletterErrMessage).getText();
            Thread.sleep(2000);
            assertTrueWithCustomError(strErrorMessage, actualMessage);
        } catch (Exception ex) {
            LOG.info("Failed to verify error message due to exception: " + ex.getMessage());
        }
    }

    public void assertMessageForEmptybasketPage() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vusede":
            case "fr":
            case "vuseuk":
            case "vusefr":
            case "vuseza":
            case "mx":
            case "vusemx":
            case "vypeit":
            case "vuseco":
            case "vuseit":
                waitForExpectedElement(BASEKT_EMPTY_MSG, 50);
                assertTrueWithCustomError(UrlBuilder.getMessage("basketIsEmptyMessage.key"), waitForExpectedElement(BASEKT_EMPTY_MSG).getText());
                break;
            case "glode":
            case "it":
            case "pl":
                assertTrueWithCustomError(UrlBuilder.getMessage("basketIsEmptyMessage.key"), waitForExpectedElement(BASEKT_EMPTY_MSG).getText());
                break;
            case "kz":
                assertTrueWithCustomError(UrlBuilder.getMessage("basketIsEmptyMessage.key"), waitForExpectedElement(BASEKT_EMPTY_MSG_KZ).getText());
                break;
            case "lyftse":
                assertTrueWithCustomError(UrlBuilder.getMessage("basketIsEmptyMessage.key"), waitForExpectedElement(BASEKT_EMPTY_MSG).getText());
                assertTrueWithCustomError(UrlBuilder.getMessage("basketDiscountMessage.key"), waitForExpectedElement(BASEKT_DISCOUNT_MSG).getText());
                break;
            default:
                assertTrueWithCustomError(UrlBuilder.getMessage("basketIsEmptyMessage.key"), waitForExpectedElement(baseketEmptyMsg).getText());
        }
    }

    public boolean verifyMiniBasketSectionWithItemDetailsTableContent() {
        ArrayList<String> ItemsOrderedTableHeadings;
        int matchCount = 0;

        ItemsOrderedTableHeadings = new ArrayList<>(Arrays.asList("Items", "Price", "Subtotal"));

        for (WebElement title : getTableHeaders(MINI_BASKET_SECTION_TABLE)) {
            if (ItemsOrderedTableHeadings.contains(title.getText())) {
                matchCount++;
                if (title.getText().contains("Subtotal"))
                    break;
            }
        }
        return matchCount == 3;
    }

    public boolean clickOnExpandTagAndAssertEachFAQIsExpanded() {
        int matchCount = 0;
        List<WebElement> lstFAQs = getWebDriver().findElements(btnFAQExpand);

        for (int i = 0; i < lstFAQs.size(); i++) {
            WebElement ele = lstFAQs.get(i);
            JavascriptExecutor jse = (JavascriptExecutor) webDriver;
            jse.executeScript("arguments[0].scrollIntoView()", ele);
            jse.executeScript("arguments[0].click()", ele);
            assertTrue(waitForExpectedElement(By.cssSelector("div.pagebuilder-collapsible.active:nth-child" + "(" + (i + 1) + ")")).isDisplayed());
            matchCount++;
        }
        return matchCount == 7;
    }

    public void assertNewWindowOpensWithSocialMediaURLToShareThePost(String strURL) throws InterruptedException {
        ArrayList<String> windowTabs = new ArrayList<String>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(windowTabs.get(1));
        Thread.sleep(1000);
        assertTrue(doesURLContain(strURL));
        getWebDriver().close();
        getWebDriver().switchTo().window(windowTabs.get(0));
    }

    public void tryClickIAmOver18() {
        switch (UrlBuilder.getLocale()) {
            case "ukLive":
                waitForExpectedElement(BUTTON_AGE_ALLOW_CX, 10);
            case "vuseuklive":
                waitForExpectedElement(buttonAgeAllow, 10);
                eyesCheckAgeGate();
                clickByElementByQueryJSExecutor(BUTTON_AGE_ALLOW_CX);
                break;
            case "vuseco":
                try {
                    eyesCheckAgeGate();
                    tryFranceAgeConfirmationLogic();
                    LOG.info("Age content box handled seperately for FR.");
                } catch (Exception e) {
                    LOG.info("Age content box isn't present - continuing. ");
                }
                break;
            case "fr":
            case "vusefr":
                eyesCheckAgeGate();
                tryFranceAgeConfirmationLogic();
                LOG.info("Age content box handled seperately for FR.");
                break;
            case "de":
                eyesCheckAgeGate();
                LOG.info("Age content for Vype DE.");
                clickByElementByQueryJSExecutor(BUTTON_AGE_ALLOW_CX);
                break;
            case "uk":
            case "vuseuk":
                eyesCheckAgeGate();
                try{
                    clickByElementByQueryJSExecutor(buttonAgeAllowUK);
                }catch(Exception e) {
                    LOG.info(e.getMessage());
                }
                break;
            case "vuseit":
                eyesCheckAgeGate();
                try {
                    waitForItemToBeClickableAndClick(BUTTON_AGE_ALLOW_GLOIT, 5);
                } catch (Exception e) {
                    clickUsingJS(BUTTON_AGE_ALLOW_GLOIT);
                }
                break;
            case "mx":
            case "vusemx":
                eyesCheckAgeGate();
                FrAndITAgePopulationScript();
                break;
            case "vuseza":
                eyesCheckAgeGate();
                tryFranceAgeConfirmationLogic();
                break;
            case "epok":
                waitForExpectedElement(buttonAgeAllow, 5);
                eyesCheckAgeGate();
                waitForItemToBeClickableAndClick(getWebDriver(), 5, buttonAgeAllow);
                break;
            case "glojp":
                waitForExpectedElement(buttonAgeAllowJP, 5);
                eyesCheckAgeGate();
                waitForItemToBeClickableAndClick(buttonAgeAllowJP);
                break;
            case "it":
                waitForExpectedElement(BUTTON_AGE_ALLOW_GLOIT, 15);
                eyesCheckAgeGate();
                waitForExpectedElement(BUTTON_AGE_ALLOW_GLOIT).click();
                if (isElementDisplayedBySeconds(BUTTON_AGE_ALLOW_GLOIT, 2)) {
                    LOG.info("BUTTON_AGE_ALLOW_GLOIT element is still shown, try to click again.");
                    clickUsingJS(BUTTON_AGE_ALLOW_GLOIT);
                }
                break;
            case "velobe":
                waitForExpectedElement(BUTTON_AGE_ALLOW_VELOBE, 5);
                eyesCheckAgeGate();
                clickUsingJS(BUTTON_AGE_ALLOW_VELOBE);
                break;
            case "velopl":
                try{
                    waitForExpectedElement(BUTTON_AGE_ALLOW_VELOPL, 5);
                    eyesCheckAgeGate();
                    clickByElementByQueryJSExecutor(BUTTON_AGE_ALLOW_VELOPL);
                }catch (Exception e) {
                    waitForItemToBeClickableAndClick(BUTTON_AGE_ALLOW_VELOPL,2);
                    LOG.info("Age button is not clickable ");
                }
                break;
            default:
                try {
                    waitForExpectedElement(BUTTON_AGE_ALLOW_CX, 5);
                    eyesCheckAgeGate();
                    waitForItemToBeClickableAndClick(getWebDriver(), 5, BUTTON_AGE_ALLOW_CX);
                } catch (Exception e) {
                    LOG.info("Age content box isn't present - continuing. ");
                }
        }
    }

    public void tryFranceAgeConfirmationLogic() {
        if (UrlBuilder.getLocale().equalsIgnoreCase("fr")||UrlBuilder.getLocale().equalsIgnoreCase("veloza") || (UrlBuilder.getLocale().equalsIgnoreCase("vusefr") || (UrlBuilder.getLocale().equalsIgnoreCase("vuseco") ||(UrlBuilder.getLocale().equalsIgnoreCase("vuseza"))))) {
            try {
                FrAndITAgePopulationScript();
            } catch (Exception e) {
                LOG.info("No Age Gate present");
            }
        }
    }

    public void FrAndITAgePopulationScript() {
        LOG.info("Running FR AGE closure script");
        By dateSelectBox = By.cssSelector("select.date");
        By monthSelectBox = By.cssSelector(".month");
        By yearSelectBox = By.cssSelector(".year");
        if (UrlBuilder.getLocale().equalsIgnoreCase("veloza")){
            monthSelectBox = DATE_SELECT_BOX_VELOZA;
            yearSelectBox = MONTH_SELECT_BOX_VELOZA;
            waitForExpectedElement(DATE_SELECT_BOX_VELOZA, 10);
        } else {
            try {
                waitForExpectedElement(By.cssSelector(".date"), 10);
            }catch(WebDriverException e){
                LOG.info("WebDriverException: " +e.toString());
            }
        }
        By verifyButton = By.cssSelector("div.actions:nth-child(7)");
        selectValueFromDropDownByby("1970", yearSelectBox);
        selectValueFromDropDownByIndex(10, monthSelectBox);
        if(!UrlBuilder.getLocale().equalsIgnoreCase("veloza"))
            selectValueFromDropDownByby("19", dateSelectBox);
        if (UrlBuilder.getLocale().equalsIgnoreCase("vusefr") || UrlBuilder.getLocale().equals("mx") || UrlBuilder.getLocale().equals("vuseco") ||(UrlBuilder.getLocale().equalsIgnoreCase("vuseza"))||UrlBuilder.getLocale().equals("vusemx")) {
            waitForExpectedElement(VERIFY_BUTTON_FR);
            clickByElementByQueryJSExecutor(VERIFY_BUTTON_FR);
        } else if (doesURLContain("za/en")) {
            waitForExpectedElement(monthSelectBox).click();
            waitForExpectedElement(monthSelectBox).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
            clickByElementByQueryJSExecutor(VERIFY_BUTTON_ZA);
        } else {
            waitForExpectedElement(verifyButton).click();
        }
        waitForElementToAppearAndDisappear(LOADER, 3, 3);
    }

    public String getItemQuantityPDP(){
        return getWebDriver().findElement(INPUT_ITEM_QUANTITY_CART).getAttribute("value");
    }

    public void enterItemQuantityPDP(String quantity) {
        switch (UrlBuilder.getLocale()) {
            case "vuseza":
            case "vypeit":
            case "vuseit":
            case "pl":
                waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS).clear();
                waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS).sendKeys(quantity);
                if(quantity.equalsIgnoreCase("26")){
                    enterDataAndWait(pdp.M_SEARCH_INPUTBOX_UK,quantity); //to click off above text box to display error message
                }
                break;
            case "vuseco":
            case "vusede":
            case "fr":
            case "vusefr":
            case "vuseuk":
                waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS).clear();
                waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS).sendKeys(quantity);
                if(UrlBuilder.isMobile()){
                    clickUsingJS(M_CLICK_NEXT_SLIDE);
                }
                else{
                    waitForExpectedElement(CLICK_NEXT_SLIDE).click();}
                break;
            case "lyftse":
                selectValueFromDropDownByby(quantity, SELECT_ITEM_QUANTITY_SUBSSE);
                break;
        }
    }

    public void assertItemQuantityPDP(String qty) {
        switch (UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
                assertEquals(waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS).getAttribute("value"), qty, "qty should match");
                break;
            case "lyftse":
                assertEquals(waitForExpectedElement(SELECT_ITEM_QUANTITY_SUBSSE).getAttribute("value"), qty, "qty should match");
                break;
            case "uk":
            case "vuseuk":
                waitForExpectedElement(QUANTITY_PDP_UK).clear();
                waitForExpectedElement(QUANTITY_PDP_UK).sendKeys(qty);
            case "vuseza":
                waitClearAndEnterText(QUANTITY_PDP_UK,qty);
        }
    }

    public void emptyBasketLyftLab() {
        waitForPage();
        if (!isBasketEmpty()) {
            LOG.info("Basket isn't empty - emptying now");
            clickBasketIcon();
            try {
                while (waitForExpectedElement(MINICART_COUNT).isDisplayed()) {
                    clickFirstElementByQueryJSExecutor(DELETE_CARTITEMS_LINK);
                    waitForExpectedElement(emptyBasketConfirmPopUpOk, 10);
                    if (getWebDriver().findElements(emptyBasketConfirmPopUpOk).size() > 0) {
                        clickFirstElementByQueryJSExecutor(emptyBasketConfirmPopUpOk);
                    }
                    Thread.sleep(4000);
                }
                closeBasketSlidingPanel();
            } catch (Exception e) {
                LOG.info("Basket is empty");
                closeBasketSlidingPanel();
            }
        }
    }

    public void clickOnNewsletterSubscribeLinkLyftLab() {
        clickByElementByQueryJSExecutor(NEWSLETTER_SUBSCRIBE_LINK);
    }

    public void clickOnExpandTagAndAssertEachFAQIsExpandedOnLyftLabSite() throws InterruptedException {
        int matchCount = 0;
        List<WebElement> lstFAQs = getWebDriver().findElements(LYFTLAB_FAQEXPAND_Button);

        for (int i = 0; i < lstFAQs.size(); i++) {
            WebElement ele = lstFAQs.get(i);
            try {
                clickUsingJS(ele);
                assertTrue(waitForExpectedElement(By.cssSelector("div.pagebuilder-collapsible.active:nth-child" + "(" + (i + 2) + ")")).isDisplayed());
            } catch (Exception e) {
                break;
            }
            if (matchCount > 1) {
                assertTrue(waitForExpectedElement(By.cssSelector("div.pagebuilder-collapsible.active:nth-child" + "(" + (i) + ")")).isDisplayed());
            }
            matchCount++;
        }
    }

    public void clickOnContactLinkFromHeaderAndAssertURLcontains(String strURL) {
        switch (UrlBuilder.getLocale()) {
            case "dk":
                clickByElementByQueryJSExecutor(HEADER_KONTAKT_ICON);
                break;
            default:
                clickByElementByQueryJSExecutor(By.cssSelector("li.userbar-item.chat:nth-child(3) a:nth-child(1) > span.icon-text"));
                assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(strURL)));
        }
    }

    public void navigateToURLAndAssertTextContains(DataTable dtURLs) throws Throwable {
        String strCurrentURL = getCurrentUrl();
        List<List<String>> lstURLs = dtURLs.raw();
        for (List<String> lstURL : lstURLs) {
            String strToNavigateURL = strCurrentURL + UrlBuilder.getMessage(lstURL.get(0).toString());
            getWebDriver().navigate().to(strToNavigateURL);
            assertTrue(getCurrentUrl().contains(UrlBuilder.getMessage(lstURL.get(1).toString())));
            orderSuccessPage.verifyURLStatusCodeIsValid(getCurrentUrl());
        }
    }

    public void usersClicksOnTheLinkByText(String linkText) {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)), 10);
        switch (UrlBuilder.getLocale()) {
            case "glojp":
                clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
                break;
            case "glode":
                try{
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
                }catch(Exception e){
                    clickUsingJS(SIGN_OUT_MENU);
                }
                break;
            case "vusefr":
                if(linkText.equals("VuseAdventCalendar.key")) {
                    clickByElementByQueryJSExecutor(ADVANT_CALENDAR_MENU_FR);
                }
                else{
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
                }
                break;
            case "vuseuk":
                waitForLoaderIconDisappear();
                clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
                break;
            default:
                try {
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
                }catch(Exception e){
                    clickByElementByQueryJSExecutor(VIEW_BASKET);
                }
        }
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public void clickOnLinkAndAssertURLReturnsSuccess(DataTable links) throws Throwable {
        List<List<String>> lstLinks = links.raw();
        for (List<String> lstLink : lstLinks) {
            usersClicksOnTheLinkByText(lstLink.get(0).toString());
            assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(lstLink.get(1).toString())));
            orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
        }
    }

    public void verifyCookiesStationaryIconAndFooterLink() {
        switch (UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
            case "de":
                stationaryCookieIconDisplayed();
                oneTrustCookiesSettingsFooterLinkDisplayed();
                break;
            case "kz":
            case "glode":
            case "pl":
                stationaryCookieIconDisplayed();
                break;
            case "vypeit":
            case "vuseit":
                break;
            case "vuseza":
                oneTrustCookiesSettingsFooterLinkDisplayed();
                break;
            default:
                oneTrustCookiesSettingsFooterLinkDisplayed();
                stationaryCookieIconNotDisplayed();
        }
    }

    public String getAttributeOfTheLink() {
        String pdfHref;
        pdfHref = waitForExpectedElement(LINK_DOWNLOAD_PDF).getAttribute("href");
        return pdfHref;
    }

    public void verifyPDFLinkOnNoticePage() {
        String hrefValue = getAttributeOfTheLink();
        switch (UrlBuilder.getLocale()) {
            case "nl":
                AssertJUnit.assertTrue("\n ******* ERROR :: PDF Link isn't as expected!!",
                        hrefValue.contains("_POLICY_NL.pdf") || hrefValue.contains("_policy_nl_nl_20200910.pdf"));
                break;
        }
    }

    public void cookiePresenceAndExpiry() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date futureDate = getFutureDate();
        try {
            Date actualCookieExpiryDate = sdf.parse(sdf.format(getWebDriver().manage().getCookieNamed("OptanonAlertBoxClosed").getExpiry()));
            Date expectedCookieExpiryDate = sdf.parse(sdf.format(futureDate));
            assertEquals(actualCookieExpiryDate, expectedCookieExpiryDate);
        } catch (Exception e) {
            LOG.info("Cookies either not present or expiry date is not expected. " + e);
        }
    }

    public Date getFutureDate() {
        Calendar calendar = Calendar.getInstance();
        // Add 6 months to current date
        calendar.add(Calendar.MONTH, 6);
        return calendar.getTime();
    }

    public void clickOnContactIconOnTopRightOfThePage() {
        waitForExpectedElement(HEADER_CONTACT_ICON).click();
    }

    public void clickOnLinksFromPersonMenu(String strExpectedLink) {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "ukLive":
            case "fr":
            case "vusefr":
            case "vuseuklive":
            case "mx":
            case "vusemx":
            case "vuseza":
            case "vuseuk":
                try {
                    Thread.sleep(3000);
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 10);
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 10).click();
                } catch (Exception e) {
                    LOG.info("\n ** LOGGIN OUT **");
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("logoutText.key")), 20).click();
                    waitForItemToBeClickableAndClick(PERSON_ICON, 10);
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink))).click();
                }
                break;
            case "vuseco":
                try {
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 20).click();
                }catch(Exception e){
                hoverOnElement(PERSON_ICON);
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 20).click();
            }
                break;
            case "vypeit":
            case "vuseit":
                try {
                    waitForAjaxElementNotToBePresent(webDriver,5);
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 10);
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 10).click();
                } catch (Exception e) {
                    LOG.info("\n ** LOGGIN OUT **");
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("logoutText.key")),20).click();
                    waitForItemToBeClickableAndClick(PERSON_ICON, 10);
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink))).click();
                }
                break;
            case "vusede":
                if(!doesURLContain("vuse.com/de/de/")){
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 10);
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(strExpectedLink)));}
                break;
            case "dk":
            case "de":
                waitForExpectedElement(SIGN_IN_LINK, 10);
                clickFirstElementByQueryJSExecutor(SIGN_IN_LINK);
                break;
            case "glode":
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage("signInLink.key")));
                clickFirstElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("signInLink.key")));
                break;
            case "pl":
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 10).click();
                break;
            case "lyftse":
                clickPersonIcon_lyft();
            default:
                LOG.info("This step is only applicable for UK locale as of now.");
        }
    }

    public void userNavigatesToRegistrationPage() throws Throwable {
        clickPersonIcon();
        clickOnLinksFromPersonMenu(UrlBuilder.getMessage("signInLink.key"));
        loginPage.clickRegistrationButton();
    }

    public void urlContainsText(String urlContains) {
        assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(urlContains)));
    }

    public void assertShopTypeDropDownIsDisplayed() {
        assertTrue(waitForExpectedElement(SHOP_TYPE_DROPDOWN).isDisplayed());
    }

    public void assertProductTypeDropDownIsDisplayed() {
        assertTrue(waitForExpectedElement(PRODUCT_TYPE_DROPDOWN).isDisplayed());
    }

    public void assertShopTypeDropdownOptions(String strOptions) {
        verifyDropDownOptionsMatchesInputValues(SHOP_TYPE_DROPDOWN, strOptions);
    }

    public void assertProductTypeDropdownOptions(String strOptions) {
        verifyDropDownOptionsMatchesInputValues(PRODUCT_TYPE_DROPDOWN, strOptions);
    }

    public void clickOnStoreLocatorLink() {
        switch (UrlBuilder.getLocale()) {
            case "de":
                waitForExpectedElement(STORE_LOCATOR_LINK).click();
                break;
            default:
        }
    }

    public void verifyNoneSelectedByDefaultForDropdown(By by) {
        Select shopTypeDropDown = new Select(waitForExpectedElement(by, 10));
        clickByElementByQueryJSExecutor(by);
        assertEquals(shopTypeDropDown.getFirstSelectedOption().getAttribute("value"), "");
    }

    public void verifyNoneSelectedByDefaultForBothShopAndProductTypes() {
        verifyNoneSelectedByDefaultForDropdown(PRODUCT_TYPE_DROPDOWN);
        verifyNoneSelectedByDefaultForDropdown(SHOP_TYPE_DROPDOWN);
    }

    public void assertFavoriteIconIsNotPresent() {
        assertFalse(getWebDriver().findElements(HEADER_FAVORITE_ICON).size() > 0);
    }

    public void assertNewsletterButtonIsNotPresentOnFooter() {
        assertFalse(getWebDriver().findElements(BUTTON_NEWSLETTERS_FOOTER).size() > 0);
    }

    public void assertNewsletterIconIsNotPresentOnHeader() {
        assertFalse(getWebDriver().findElements(ICON_NEWSLETTERS_HEADER).size() > 0);
    }

    public void clickSigninLink() {
        if (UrlBuilder.isSafari() || UrlBuilder.isIpad()) {
            clickFirstElementByQueryJSExecutor(
                    By.linkText(UrlBuilder.getMessage("signInLink.key")));
        } else
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("signInLink.key")),10).click();
    }

    public void clickOnBasketIcon() throws InterruptedException {
        if (!UrlBuilder.getLocale().contains("glojp")){
            Thread.sleep(2000);
        }
        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
            switch (UrlBuilder.getLocale()) {
                case "uk":
                case "pl":
                case "fr":
                case "vuseuk":
                case "vusefr":
                case "glode":
                case "mx":
                case "vusemx":
                case "ie":
                case "vuseco":
                case "vuseza":
                case "kz":
                    waitForExpectedElement(MINI_CART_OPEN,5);
                    waitForLoaderIconDisappear();
                    miniCartOpen = getWebDriver().findElements(MINI_CART_OPEN).size() != 0;
                    if (!miniCartOpen) {
                        Thread.sleep(10000);
                        clickUsingJS(BASKET_ICON);
                    }
                    break;
                case "vusede":
                case "vypeit":
                case "vuseit":
                    waitForExpectedElement(MINI_CART_OPEN,5);
                    waitForLoaderIconDisappear();
                    miniCartOpen = getWebDriver().findElements(MINI_CART_OPEN).size() != 0;
                    if (!miniCartOpen) {
                        clickByElementByQueryJSExecutor(BASKET_ICON);
                    }
                    break;
                case "glojp":
                    try {
                        waitForExpectedElement(BASKET_ICON_JP, 30);
                        clickByElementByQueryJSExecutor(BASKET_ICON_JP);
                    }catch(Exception e){
                        waitForExpectedElement(BASKET_ICON_JP,30);
                        clickByElement(BASKET_ICON_JP);
                    }
                    IsPageLoaded.waitAllRequest();
                    waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,10);
                    break;
                case "velopl":
                    waitForExpectedElement(AVALANCHE_BASKET_ICON).click();
                    break;
                case "velobe":
                    try {
                        waitForExpectedElement(AVALANCHE_BASKET_ICON).click();
                    } catch (Exception e ) {
                        clickUsingJS(AVALANCHE_BASKET_ICON);
                    }
                    break;
                default:
                    waitForElementToAppearAndDisappear(LOADER, 3, 3);
                    if (getWebDriver().findElements(By.xpath("//li[contains(@class,'userbar-item customer active')]//span[@class='action toggle active']")).size() > 0) {
                        clickPersonIcon();
                    }
                    waitForExpectedElement(BASKET_ICON, 10);
                    clickByElementByQueryJSExecutor(BASKET_ICON);
            }
            waitForExpectedElement(basketCloseSlidingPanelButton, 2);
        } else {
            switch (UrlBuilder.getLocale()) {
                case "uk":
                case "vuseuk":
                case "vuseco":
                case "vuseza":
                    if (isElementDisplayedBySeconds(M_VIEW_BASKET_BUTTON, 5)) {
                        getWebDriver().findElement(M_VIEW_BASKET_BUTTON).click();
                    } else {
                        waitForExpectedElement(BASKET_ICON).click();
                        waitForExpectedElement(M_VIEW_BASKET_BUTTON).click();
                    }
                    waitForElementToAppearAndDisappear(LOADER, 3, 3);
                    break;
                case "ie":
                    if (UrlBuilder.isMobile()) {
                        if (webDriver.findElements(M_SANDWICH_MENU_ICON_OPTION_BASKET).size()==0) {
                            waitForExpectedElement(M_SANDWICH_MENU_ICON_IE, 10).click();
                        }
                        else {
                            waitForExpectedElement(M_SANDWICH_MENU_ICON_IE, 10).click();
                            waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_BASKET, 10).click();
                        }
                    } else {
                        waitForExpectedElement(T_BASKET_ICON_IE).click();
                    }
                    break;
                case "glode":
                case "de":
                case "dk":
                case "kz":
                    try{
                        waitForItemToBeClickableAndClick(M_VIEW_BASKET_BUTTON_GLOKZ, 10);
                    }
                    catch (Exception ex)
                    {
                        clickUsingJS(M_VIEW_BASKET_BUTTON_GLOKZ);
                    }
                    break;
                case "it":
                    waitForItemToBeClickableAndClick(M_VIEW_BASKET_BUTTON_IT, 10);
                case "mx":
                case "vusemx":
                case "vusede":
                    if (UrlBuilder.isMobile()) {
                        openBasketifNotOpenLogic();
                    } else {
                        waitForExpectedElement(T_PORTRAIT_BASKET_ICON, 10);
                        clickByElementByQueryJSExecutor(T_PORTRAIT_BASKET_ICON);
                    }
                    break;
                case "fr":
                case "vusedk":
                case "vusefr":
                    if (UrlBuilder.isMobile()) {
                        openBasketifNotOpenLogic();
                    } else {
                        waitForExpectedElement(T_BASKET_ICON_FR);
                        clickByElement(T_BASKET_ICON_FR);
                    }
                    break;
                case "pl":
                    waitForElementToAppearAndDisappear(LOADER, 10, 20);
                    miniCartOpen = getWebDriver().findElements(MINI_CART_OPEN).size() != 0;
                    if (!miniCartOpen) {
                        clickByElementByQueryJSExecutor(BASKET_ICON);
                    }
                    break;
                case "nl":
                    waitForExpectedElement(M_BASKET_ICON_NL, 5);
                    clickByElementByQueryJSExecutor(M_BASKET_ICON_NL);
                    break;
                case "lyftse":
                    waitForExpectedElement(PDP.addToCartButton,20).sendKeys(Keys.ARROW_DOWN);
                    waitForExpectedElement(BASKET_ICON_LYFTSE, 10);
                    scrollElementIntoView(BASKET_ICON_LYFTSE);
                    clickByElementByQueryJSExecutor(BASKET_ICON_LYFTSE);
                    break;
                case "vypeit":
                case "vuseit":
                    miniCartOpen = getWebDriver().findElements(MINI_CART_OPEN).size() != 0;
                    if (!miniCartOpen) {
                        clickByElementByQueryJSExecutor(BASKET_ICON);
                    }
                    break;
                case "velode":
                    clickByElement(CART_ICON_VELO);
                    break;
                default:
                    if (getWebDriver().findElements(By.xpath("//li[contains(@class,'userbar-item customer active')]//span[@class='action toggle active']")).size() > 0) {
                        clickPersonIcon();
                    }
                    waitForExpectedElement(M_BASKET_ICON, 10).click();
            }
        }
    }

    public void navigateToSignInPage() {
        if (UrlBuilder.isIpad() || UrlBuilder.isDesktop()) {
            switch (UrlBuilder.getLocale()) {
                case "uk":
                case "veloza":
                    waitForAjaxElementNotToBePresent(webDriver,3);
                    waitAndClickByElementByJSExecutor(PERSON_ICON_VELOBE,10);
                    break;
                case "vuseuk":
                    closeVuseAlertIfPresent();
                    try {
                        clickPersonIcon();
                        clickSigninLink();
                    } catch (Exception e) {
                        if (UrlBuilder.isIpad()) {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/referer");
                        } else {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                        }

                    }
                    closeVuseAlertIfPresent();
                    break;
                case "fr":
                case "mx":
                case "vusemx":
                    clickPersonIcon();
                    clickSigninLink();
                    closeVuseAlertIfPresent();
                    break;
                case "vuseza":
                    try {
                        clickPersonIcon();
                        clickSigninLink();
                        closeVuseAlertIfPresent();
                    } catch (Exception e) {
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                    }
                    break;
                case "pl":
                    try {
                        hoverOnElement(PERSON_ICON_PL);
                        waitForExpectedElement(SIGN_IN_LINK_PL, 10);
                        clickByElementByQueryJSExecutor(SIGN_IN_LINK_PL);
                    }catch (Exception e) {
                        if (UrlBuilder.isIpad()) {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                        } else {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                        }
                    }
                    break;
                case "vusede":
                    try {
                        clickPersonIcon();
                        waitForItemToBeClickableAndClick(CUSTOMER_DROPDOWN_OPTION_ACCOUNT_VUSE_DE, 10);
                    } catch (Exception e) {
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                    }
                    break;
                case "dk":
                case "vusedk":
                    waitForPage();
                    clickByElement(PERSON_ICON);
                    clickLinkOnPersonMenu("signInLink.key");
                    break;
                case "de":
                    waitForPage();
                    clickByElement(PERSON_ICON);
                    waitForItemToBeClickableAndClick(CUSTOMER_DROPDOWN_OPTION_ACCOUNT_VYPEDE, 10);
                    break;
                case "lyftse":
                    clickPersonIcon_lyft();
                    break;
                case "it":
                    clickPersonIcon();
                    break;
                case "vypeit":
                case "vuseit":
                case "vuseco":
                case "vusefr":
                case "glode":
                    try {
                        clickPersonIcon();
                        clickSigninLink();
                    } catch (Exception e) {
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                    }
                    break;
                case "kz" :
                    try{
                        waitForPage();
                        hoverOnElement(PERSON_ICON);
                        clickFirstElementByQueryJSExecutor(SIGN_OFF_MENU);
                        clickByElement(PERSON_ICON);
                    } catch (Exception e) {
                        if(!(getWebDriver().getCurrentUrl().contains("account/login"))) {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                        }
                    }
                    break;
                default:
                    clickByElement(PERSON_ICON);
            }
        } else {
            switch (UrlBuilder.getLocale()) {
                case "lyftse":
                    waitForExpectedElement(LYFTLAB_BURGER_MENU).click();
                    waitForExpectedElement(LYFTLAB_BURGER_MENU_LOGIN_OPTION).click();
                    break;
                case "lyftdk":
                    clickByElement(M_PERSONICON_LYFTDK);
                    break;
                case "uk":
                case "vuseuk":
                case "vuseco":
                    waitForElementToAppearAndDisappear(LOADER, 2, 3);
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_UK).click();
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT).click();
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT_SIGNINREGISTER).click();
                    closeVuseAlertIfPresent();
                    break;
                case "vypeit":
                case "vuseit":
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_UK).click();
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT).click();
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_OPTION_MYACCOUNT_SIGNINREGISTER).click();
                    closeVuseAlertIfPresent();
                    break;
                case "nl":
                    clickByElement(M_PERSONICON_VYPEIT);
                    break;
                case "fr":
                case "vusefr":
                    waitForPage();
                    if (UrlBuilder.isIPhone()) {
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                    } else {
                        waitForExpectedElement(PERSON_ICON, 15).click();
                        clickByElement(M_PERSONICON_VUSEFR);
                    }
                    break;
                case "vusedk":
                    if (UrlBuilder.isMobile()) {
                        clickByElement(M_PERSONICON_VUSEFR);
                    } else {
                        clickByElement(T_PERSONICON_FR);
                        clickByElement(T_REGISTRATION_LINK_FR);
                    }
                    break;
                case "ie":
                case "dk":
                case "de":
                    if (UrlBuilder.isMobile()) {
                        clickByElement(M_PERSONICON_VYPEDE);
                    } else {
                        clickByElement(T_PERSONICON_IE);
                    }
                    break;
                case "glode":
                    try {
                        clickPersonIcon();
                        clickSigninLink();
                    }catch (Exception e) {
                        if (UrlBuilder.isIPhone()) {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                        }
                    }
                    break;
                case "mx":
                case "vusemx":
                case "vuseza":
                    try {
                        waitForExpectedElement(M_PERSONICON_VYPEMX, 5).click();
                        clickSigninLink();
                    } catch (Exception e) {
                        if (UrlBuilder.isIPhone()) {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                        }
                    }
                    break;
                case "vusede":
                    clickPersonIcon();
                    if (UrlBuilder.isSamsungMobile())
                        waitForItemToBeClickableAndClick(CUSTOMER_DROPDOWN_OPTION_ACCOUNT_VUSE_DE, 10);
                    if (UrlBuilder.isIPhone()) {
                        try {
                            clickPersonIcon();
                            waitForItemToBeClickableAndClick(CUSTOMER_DROPDOWN_OPTION_ACCOUNT_VUSE_DE, 10);
                        } catch (Exception ex) {
                            getWebDriver().navigate().to(getCurrentUrl() + "customer/account/login/");
                        }
                    } else {
                        clickPersonIcon();
                        if (UrlBuilder.isSamsungMobile())
                            waitForItemToBeClickableAndClick(CUSTOMER_DROPDOWN_OPTION_ACCOUNT_VUSE_DE, 10);
                    }
                    break;
                case "pl":
                    waitForPage();
                    hoverOnElement(PERSON_ICON);
                    clickFirstElementByQueryJSExecutor(M_SIGN_IN_LINK_FROM_PERSON_MENU);
                    break;
                case "kz":
                    break;
                default:
                    clickByElement(PERSON_ICON);
            }
        }
    }

    public void clickPersonIcon_lyft() {
        clickByElementByQueryJSExecutor(LYFT_PERSONICON);
    }

    public void mobile_clickPersonIcon_lyft() {
        clickByElementByQueryJSExecutor(M_LYFT_PERSONICON);
    }

    public void clickLinkOnPersonMenu(String strExpectedLink) {
        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
            switch (UrlBuilder.getLocale()) {
                case "uk":
                case "ukLive":
                case "vuseuk":
                case "fr":
                case "vusefr":
                case "vuseuklive":
                case "mx":
                case "vusemx":
                case "vuseco":
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink)), 20).click();
                    break;
                case "dk":
                case "de":
                case "vuseza":
                case "vusedk":
                    clickByElementByQueryJSExecutor(SIGN_IN_LINK);
                    break;
                default:
            }
        } else {
            switch (UrlBuilder.getLocale()) {
                case "ukLive":
                case "vuseuklive":
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedLink))).click();
                    break;
                case "mx":
                case "vusemx":
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(strExpectedLink)));
                    break;
                default:
            }
        }
    }

    public boolean acceptCookieIfpresent() {
        if (isElementPresent(OneTrustAcceptCookiesButton)) {
            this.clickUsingJS(OneTrustAcceptCookiesButton);
            LOG.info("Accept cookie");
            return true;
        }
        return false;
    }

    public void newsLetterInvalidEmailErrorMsg() {
        waitForExpectedElement(NEWSLETTER_ERROR_MSG, 10);
        waitForExpectedElement(NEWSLETTER_ERROR_MSG).isDisplayed();
    }

    public void newsLetterValidationMessageDisplayed() {
        waitForExpectedElement(NEWSLETTER_SUCCESS_MSG, 10);
        waitForExpectedElement(NEWSLETTER_SUCCESS_MSG).isDisplayed();
    }

    public void clicksOnDashboardLink() {
        switch (UrlBuilder.getLocale()) {
            case "kz":
            case "pl":
                waitForExpectedElement(DASHBOARD_EDIT_LINK, 10);
                clickByElementByQueryJSExecutor(DASHBOARD_EDIT_LINK);
                break;
            default:
                try {
                    waitForExpectedElement(EDIT_LINK).click();
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(EDIT_LINK);
                }
                break;
        }
    }

    public void verifyUserNavigatesToPLPAndVerifyFields() {
        assertTrue(waitForExpectedElement(PLP_PRODUCT_LIST, 10).isDisplayed());
        assertTrue(waitForExpectedElement(PLP_ADDTOCART_BUTTON).isDisplayed());
        assertTrue(waitForExpectedElement(PLP_PRODUCT_QTY).isDisplayed());
    }

    public void verifyUserNavigatesToDevicesPLPAndVerifyFields() {
        verifyUserNavigatesToPLPAndVerifyFields();
    }

    public void verifyUserNavigatesToCapsPLPAndVerifyFields() {
        verifyUserNavigatesToPLPAndVerifyFields();
        assertTrue(waitForExpectedElement(pdp.btnProductStrength).isDisplayed());
        assertTrue(waitForExpectedElement(PLP_STRENGTH_FILTER).isDisplayed());
        assertEquals(getWebDriver().findElements(PLP_SYSTEM_FILTER).size(), 0);
    }

    public void userNavigatesToContactPage() {
        assertTrue(waitForExpectedElement(CONTACT_FORM_PAGE).isDisplayed());
    }

    public void verifyCustomerInformationSectionFooter() {
        assertTrue(getWebDriver().findElements(FOOTER_CUSTOMER_INFO).size() > 0);
    }

    public void verifyFieldsUnderProductCarouselBlock() {
        if (getWebDriver().findElements(PRODUCTS_GRID_CAROUSEL).size() > 0) {
            scrollElementIntoView(PRODUCTS_GRID_CAROUSEL);
            assertTrue(waitForExpectedElement(PRODUCT_CAROUSEL_QTY).isDisplayed());
            assertTrue(waitForExpectedElement(PRODUCT_CAROUSEL_ADDTOCART).isDisplayed());
            assertTrue(waitForExpectedElement(CAROUSEL_ARROW_NEXT).isDisplayed());
            assertTrue(waitForExpectedElement(CAROUSEL_ARROW_PREV).isDisplayed());
        }
    }

    public void verifyCarouselFunctionalityOnLeftRightArrowCTAs(By byCarousalNavigation, By byArrowNext, By byArrowPrev) {
        List<WebElement> lstProductDots;
        lstProductDots = getWebDriver().findElements(byCarousalNavigation);
        clickByElementByQueryJSExecutor(byArrowNext);
        assertTrue(lstProductDots.get(1).getAttribute("class").equalsIgnoreCase("slick-active"));
        clickByElementByQueryJSExecutor(byArrowPrev);
        assertTrue(lstProductDots.get(0).getAttribute("class").equalsIgnoreCase("slick-active"));
    }

    public void clickOnLinkAndAssertUrlAndPageTitle(String strLinkText, String strURL, String strPageTitle) throws Throwable {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strLinkText)),5).click();
        assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(strURL)));
        orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
        loginPage.assertTextOfRegisteredCustomersIsDisplayed(strPageTitle);
    }

    public void clickOnLinkAndAssertPageTitle(String strLinkText, String strPageTitle) throws Throwable {
        usersClicksOnTheLinkByText(strLinkText);
        orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
        loginPage.assertTextOfRegisteredCustomersIsDisplayed(strPageTitle);
    }

    public void clickOnLinkAndAssertUrlAndPageTitleInNewTab(String strLinkText, String strURL, String strPageTitle) throws Throwable {
        usersClicksOnTheLinkByText(strLinkText);
        switchBetweenWindowTabs(1);
        assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(strURL)));
        orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
        loginPage.assertTextOfRegisteredCustomersIsDisplayed(strPageTitle);
    }


    public void blogTilesPresent() {
        List<WebElement> list = presenceOfAllElementsLocatedBy(BLOG_CONTENTS_TABLE);
        for (WebElement items : list) {
            Assert.assertTrue(items.isDisplayed(), "Blog tiles are displayed");
        }
    }

    public void clickOnSelectCategoriesMenuAndAssertSubLinks() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "dk":
            case "ie":
            case "vusedk":
                break;
            case "vypeit":
            case "vuseit":
                waitAndClickByElementByJSExecutor(CATEGORIES_DROPDOWN_VYPEIT,5);
                waitAndClickByElementByJSExecutor(TESTIMONIALS_BLOG_VYPEIT,2);
                orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
                waitForPage();
                waitAndClickByElementByJSExecutor(CATEGORIES_DROPDOWN_VYPEIT,5);
                waitAndClickByElementByJSExecutor(SCIENCECATEGORY_BLOG_VYPEIT,5);
                orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
                waitForPage();
                waitAndClickByElementByJSExecutor(CATEGORIES_DROPDOWN_VYPEIT,5);
                waitAndClickByElementByJSExecutor(VYPESTYLE_BLOG_VYPEIT,5);
                orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
                break;
            case "nl":
                clickOnLinkAndAssertPageTitle("scienceCategoryLinkText.key", "scienceCategoryLinkText.key");
                clickOnLinkAndAssertPageTitle("tutorialsLinkText.key", "tutorialsLinkText.key");
                clickOnLinkAndAssertPageTitle("productInfoLinkText.key", "productInfoLinkText.key");
                clickOnLinkAndAssertPageTitle("newsAboutVapingLinkText.key", "newsAboutVapingLinkText.key");
                break;
            case "fr":
            case "vusefr":
                blogTilesPresent();
                break;
            case "de":
            case "vusede":
                clickOnCloseButtonFromSignUpPopUpAndVerifyPopupIsClosed();
                usersClicksOnTheLinkByText("selectCategoriesLinkText.key");
                clickOnLinkAndAssertPageTitle("category1LinkText.key", "category1LinkText.key");
                usersClicksOnTheLinkByText("selectCategoriesLinkText.key");
                clickOnLinkAndAssertPageTitle("category2LinkText.key", "category2LinkText.key");
                usersClicksOnTheLinkByText("selectCategoriesLinkText.key");
                clickOnLinkAndAssertPageTitle("category3LinkText.key", "category3LinkText.key");
                break;
            default:
                usersClicksOnTheLinkByText("selectCategoriesLinkText.key");
                clickOnLinkAndAssertPageTitle("scienceCategoryLinkText.key", "scienceCategoryLinkText.key");
                usersClicksOnTheLinkByText("selectCategoriesLinkText.key");
                clickOnLinkAndAssertPageTitle("qualityCategoryLinkText.key", "qualityCategoryLinkText.key");
        }
    }

    public void userClicksOnTheMoreHeaderLink() throws Throwable {
        hoverOnElement(navMoreButton);
        Thread.sleep(2000);
    }

    public void userHoversOnTheBlogLink() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
                hoverOnElement(navMoreMenuBlogLink);
                break;
            case "ie":
                hoverOnElement(navMoreMenuBlogLinkIE);
                break;
        }
        Thread.sleep(1000);
    }

    public void userClicksOnTheBlogLinkFromMoreMenu() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
                waitForExpectedElement(navMoreMenuBlogLink).click();
                break;
            case "ie":
                waitForExpectedElement(navMoreMenuBlogLinkIE).click();
                break;
        }
    }

    public void clickOnBlogLinkAndAssertUrlAndPageTitle(String link) throws Throwable {
        if(link.equals("Blog")) {
            hoverOnElement(By.linkText(UrlBuilder.getMessage("scopriVuse.key")));
            clickOnLinkAndAssertUrlAndPageTitle("VypeBlog.key", "VypeBlogUrlContain.key", "VypeBlogTitle.key");
        }
        else if(link.equals("Advent calendar")){
            clickOnLinkAndAssertUrlAndPageTitle("VuseAdventCalendar.key", "VuseAdventCalendarUrlContain.key", "VuseAdventCalendarTitle.key");
        }
    }

    public void assertFreeDeliveryBannerConfigurationFromBackend() throws Throwable {
        contentPage.performBackEndStepsToAssertFreeDeliveryBannerConfiguration();
        FREE_DELIVERY_BANNERS = contentPage.assertEnabledStatusForBannerAndReturnCountForLyftStore();
        if (FREE_DELIVERY_BANNERS == 0) {
            LOG.info("Free Delivery Banners not configured in Backend");
        }
    }

    public void assertFreeDeliveryBannerForSwedenSite() {
        UrlBuilder.startBATHomePage();
        switchBetweenWindowTabs(1);
        cookieClickAllowAllAndClickOver18();
    }

    public void assertFreeDeliveryBannerForENSite() {
        if (FREE_DELIVERY_BANNERS == 2 && (doesURLContain("/se/sv/"))) {
            clickFirstElementByQueryJSExecutor(SWITCH_LANGUAGE_TRIGGER);
            switch (UrlBuilder.getLocale()) {
                case "lyftdk":
                    clickUsingJS(lyftLabPage.SWITCH_LANGUAGE_LINK_LYFTDK);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    cookieClickAllowAllAndClickOver18();
                    break;
                default:
                    clickUsingJS(SWITCH_LANGUAGE_LINK);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    tryClickIAmOver18();
            }
        } else {
            clickFirstElementByQueryJSExecutor(SWITCH_LANGUAGE_TRIGGER);
            clickUsingJS(SWITCH_LANGUAGE_LINK);
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            tryClickIAmOver18();
        }
    }

    public void assertFreeDeliveryBannerOnAllLABPages() {
        if (FREE_DELIVERY_BANNERS == 2) {
            if (doesURLContain("/se/sv/")) {
                assertTrue(getWebDriver().findElements(FREE_DELIVERY_BAR_SV).size() > 0);
            }
            if (doesURLContain("/se/en/")) {
                assertTrue(getWebDriver().findElements(FREE_DELIVERY_BAR_EN).size() > 0);
            }
        }
    }

    public void assertPrivacyPolicyLinkAndNavigationOnCTA(String strExpectedLink) throws Throwable {
        clickOnLinkAndAssertUrlAndPageTitleInNewTab(strExpectedLink, "privacyPolicyURL.key", "privacyPolicyPageTitle.key");
    }

    public void fetchDelayTimeFromBackendForLiveChatPopUp() {
        intDelayTime = storePage.performBackEndStepsToFetchDelayTimeForSalesforceChat();
        if (!String.valueOf(intDelayTime).equals("null") || !String.valueOf(intDelayTime).isEmpty()) {
            System.setProperty("intDelayTime", String.valueOf(intDelayTime));
        }
    }

    public void assertCartridgePopUpOnCheckout() {
        if (getWebDriver().findElements(CHECKOUT_CARTRIDGE_POPUP).size() > 0)
            assertTrue(waitForExpectedElement(CHECKOUT_CARTRIDGE_POPUP).isDisplayed());
    }

    public void assertCartridgePopUpNotDisplayedOnCheckout() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        assertEquals(getWebDriver().findElements(CHECKOUT_CARTRIDGE_POPUP_CLOSED).size(), 0);
    }

    public void clickOnSeeCartridgesButtonFromPopUpAndNavigateToCartuchosPLP() throws Throwable {
        if (getWebDriver().findElements(CHECKOUT_CARTRIDGE_POPUP).size() > 0) {
            clickByElementByQueryJSExecutor(CARTRIDGE_POPUP_SEE_CARTRIDGES_BUTTON);
            waitForExpectedElement(PLP_ADDTOCART_SECONDARY, 10);
            assertUserIsNavigatedToCartuchosPLP();
        }
    }

    public void assertUserIsNavigatedToCartuchosPLP() {
        assertTrue(waitForExpectedElement(homePageHeading).getText().contains("Cartuchos"));
        assertTrue(waitForExpectedElement(PLP_ADDTOCART_SECONDARY, 10).isDisplayed());
    }


    public void clickOnContinueButtonAndAssertPopUpClosedOnCheckoutPage() {
        if (getWebDriver().findElements(CARTRIDGE_POPUP_CONTINUE_WITH_PAYMENT_BUTTON).size() > 0) {
            waitForExpectedElement(CARTRIDGE_POPUP_CONTINUE_WITH_PAYMENT_BUTTON, 10).click();
            waitForExpectedElement(MX_CREDITCARD_OPTION, 10);
            assertCartridgePopUpNotDisplayedOnCheckout();
        }
    }

    public void userClicksCloseButtonFromThePopUpAndAssertCTA() {
        if (getWebDriver().findElements(CARTRIDGE_POPUP_CLOSE_BUTTON).size() > 0) {
            waitForExpectedElement(CARTRIDGE_POPUP_CLOSE_BUTTON, 10).click();
            waitForExpectedElement(MX_CREDITCARD_OPTION, 10);
            assertCartridgePopUpNotDisplayedOnCheckout();
        }
    }

    public void userNavigatesToBATHomePage() {
        UrlBuilder.navigateToBATHomePage();
    }

    public void userLoginIntoMagentoAdminAndFetchChatPopUpDelayTime() {
        adminLoginPage.userLoginsMagentoAdminHomePageSuccessfully();
        fetchDelayTimeFromBackendForLiveChatPopUp();
    }

    public void loginIntoMagentoAdminHomePageAndAssertFreeDeliveryBanner() throws Throwable {
        adminLoginPage.userLoginsMagentoAdminHomePageSuccessfully();
        assertFreeDeliveryBannerConfigurationFromBackend();
    }

    public void confirmMiniBasketDisplayedAmountOf(String strBasketQty) throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
                waitForElementToAppearAndDisappear(ICONBAG_LOADER_ICON,3,5);
                waitForExpectedElement(basketQty, 15);
                if((UrlBuilder.isSamsungMobile()) || (UrlBuilder.isDesktop())){
                    closeBasketSlidingPanel();
                    assertTrueWithCustomError(strBasketQty, waitForExpectedElement(basketQty, 10).getText());}
                break;
            case "mx":
                waitForExpectedElement(basketQty, 30);
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                assertTrueWithCustomError(strBasketQty, waitForExpectedElement(basketQty, 10).getText());
                break;
            case "vuseco":
            case "vusemx":
                if (UrlBuilder.isDesktop()) {
                    waitForAjaxElementNotToBePresent(getWebDriver(), 30);
                    waitForExpectedElement(basketQty, 30);
                    assertTrueWithCustomError(strBasketQty, waitForExpectedElement(basketQty, 20).getText());
                }
                break;
            case "uk":
            case "vuseuk":
                waitForElementToAppearAndDisappear(LOADER_ICON,3,5);
                waitForTextToBe(getWebDriver(), 20, basketQty, strBasketQty);
                assertTrueWithCustomError(strBasketQty, waitForExpectedElement(basketQty, 10).getText());
                break;
            case "dk":
            case "vusedk":
                if (!UrlBuilder.isFirefox()) {
                    waitForTextToBe(getWebDriver(), 20, BASKET_QUANTITY_COUNTER, strBasketQty);
                    assertTrueWithCustomError(strBasketQty,
                            waitForExpectedElement(BASKET_QUANTITY_COUNTER, 10).getText());
                }
                break;
            case "de":
            case "vusede":
                if(UrlBuilder.isSamsungMobile())
                    closeBasketSlidingPanel();
                waitForExpectedElement(BASKET_QTY_VUSE_DE, 10);//added for FF
                if (!UrlBuilder.isFirefox()) {
                    if (UrlBuilder.isMobile()) {
                        waitForTextToBe(getWebDriver(), 20, M_BASKET_QTY_VUSE_DE, strBasketQty);
                    }
                    else {
                        waitForTextToBe(getWebDriver(), 20, BASKET_QTY_VUSE_DE, strBasketQty);
                    }
                }
                if (UrlBuilder.isMobile()) {
                    assertTrueWithCustomError(strBasketQty, waitForExpectedElement(M_BASKET_QTY_VUSE_DE, 10).getText());
                }
                else {
                    assertTrueWithCustomError(strBasketQty, waitForExpectedElement(BASKET_QTY_VUSE_DE, 10).getText());
                }
                break;
            case "it":
                waitForTextToBe(getWebDriver(), 10, basketQty, strBasketQty);
                assertTrueWithCustomError(strBasketQty, waitForExpectedElement(basketQty, 10).getText());
                break;
            case "vypeit":
            case "vuseit":
                if (UrlBuilder.isMobile()) {
                    if(!isElementDisplayedBySeconds(M_BASKET_QTY_VUSE_IT,30)) {
                        waitForExpectedElement(M_BASKET_QTY_VUSE_IT,30);
                    }
                    waitForTextToBe(getWebDriver(), 5, M_BASKET_QTY_VUSE_IT, strBasketQty);
                    assertTrueWithCustomError(strBasketQty, waitForExpectedElement(M_BASKET_QTY_VUSE_IT, 10).getText());
                }
                else {
                    waitForTextToBe(getWebDriver(), 30, basketQty, strBasketQty);
                    assertTrueWithCustomError(strBasketQty, waitForExpectedElement(basketQty, 10).getText());
                }
                break;
            case "vuseza":
                waitForTextToBe(getWebDriver(), 30, basketQty, strBasketQty);
                assertTrueWithCustomError(strBasketQty, waitForExpectedElement(basketQty, 10).getText());
                break;
            default:
                if (UrlBuilder.isDesktop()) {
                    waitForAjaxElementNotToBePresent(getWebDriver(),8);
                    waitForExpectedElement(basketQty, 25);
                    String basketValue = isElementPresentByby(basketEmpty) ? "0" : waitForExpectedElement(basketQty, 20).getText();
                    assertTrueWithCustomError(strBasketQty, basketValue);
                } else {
                    assertTrue(isElementDisplayedBySeconds(M_PRODUCT_ADDED_SUCCESS_MSG, 15));
                }
        }
    }

    public void userNavigatesToBATHomePageForFEValidation() {
        userNavigatesToBATHomePage();
    }

    public void clickOnNewsletterButton() {
        switch (UrlBuilder.getLocale()) {
            case "ukLive":
            case "uk":
            case "vusede":
                try {
                    waitForExpectedElement(btnNewsLetterSubscribeUK, 10);
                    clickByElementByQueryJSExecutor(btnNewsLetterSubscribeUK);
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(REGISTER_WITH_VUSE_NOW_BUTTON);
                }
                break;
            case "vuseuk":
            case "vuseuklive":
            case "velopl":
                waitForExpectedElement(btnNewsLetterSubscribeUK, 10);
                clickByElementByQueryJSExecutor(btnNewsLetterSubscribeUK);
                break;
            case "mx":
            case "vusemx":
            case "vuseza":
            case "vypeit":
            case "vuseit":
                waitForPage();
                openNewsLetterPopUpIfNotAlreadyOpen();
                break;
            case "lyftse":
                newsLettersPage.clickNewslettersLink();
                break;
            default:
                clickByElementByQueryJSExecutor(newsLetterSubscribeButton);
        }
    }

    private void openNewsLetterPopUpIfNotAlreadyOpen() {
        Boolean isNewsLetterPanelDisplayed = getWebDriver().findElements(NEWS_LETTER_POPUP_MX_IT).size() > 0;
        if (!isNewsLetterPanelDisplayed){
            clickByElementByQueryJSExecutor(newsLetterSubscribeButton);
        }
    }

    public void navigateToPLPVype() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
            switch (UrlBuilder.getLocale()) {
                case "uk":
                case "mx":
                    hoverOnElement(By.linkText(UrlBuilder.getMessage("vapeheader.key")));
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("vapeSubsheader.key")), 10).click();
                    waitForExpectedElement(VYPE_UK_CAT_RESULT, 20).click();
                    break;
                case "vuseuk":
                case "vusemx":
                    if (UrlBuilder.isIpad()) {
                        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("flavourHeaderLinkTextIphone.key")), 20);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("flavourHeaderLinkTextIphone.key")));
                    } else {
                        waitForExpectedElement(SHOP_FLAVORS_LINK, 20);
                        try{
                            waitForExpectedElement(SHOP_FLAVORS_LINK,10).click();
                        }catch(Exception e){
                            if(isElementPresent(SHOP_FLAVORS_LINK))
                                LOG.info("plp page already displayed");
                        }
                        waitForAjaxElementNotToBePresent(getWebDriver(),10);
                        waitForPage();
                        if("vuseuk".equalsIgnoreCase(UrlBuilder.getLocale())&&!doesURLContain("gb/en/e-liquids"))
                            getWebDriver().navigate().to( UrlBuilder.getUrl() + "/"+UrlBuilder.getMessage("eLiquidUrl.key"));
                    }
                    break;
                case "vusede":
                    if (UrlBuilder.isIpad()) {
                        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("flavourHeaderLinkTextIphone.key")), 20);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("flavourHeaderLinkTextIphone.key")));
                    }else {
                        try {
                            waitForAjaxElementNotToBePresent(getWebDriver(),5);
                            clickUsingJS(LIQUIDS_LINK_VUSE_DE);
                            waitForAjaxElementNotToBePresent(getWebDriver(),5);
                            if(!doesURLContain(UrlBuilder.getMessage("shopFlavoursURL.key"))){
                                LOG.info("Try Block - URL navigation to PLP page if header link CTA failed");
                                navigateToGivenPage("shopFlavoursURL.key");}
                        } catch (Exception ex) {
                            LOG.info("Catch Block - URL navigation to PLP page if header link CTA failed");
                            navigateToGivenPage("shopFlavoursURL.key");
                        }
                        waitForAjaxElementNotToBePresent(getWebDriver(),10);
                    }
                    break;
                case "vypeit":
                case "vuseit":
                    if (UrlBuilder.isIpad()) {
                        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("LiquidiLinkIpad.key")), 10);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("LiquidiLinkIpad.key")));
                    }
                    else if(UrlBuilder.isDesktop()){
                        try {
                            waitForAjaxElementNotToBePresent(getWebDriver(),5);
                            clickUsingJS(LIQUIDS_LINK_VUSE_IT);
                            waitForAjaxElementNotToBePresent(getWebDriver(),5);
                            if(!doesURLContain(UrlBuilder.getMessage("PlpUrl.key"))){
                                LOG.info("Try Block - URL navigation to PLP page if header link CTA failed");
                                UrlBuilder.navigateToRelativeUrl("PlpUrl.key");}
                        } catch (Exception ex) {
                            LOG.info("Catch Block - URL navigation to PLP page if header link CTA failed");
                            UrlBuilder.navigateToRelativeUrl("PlpUrl.key");
                        }
                        waitForAjaxElementNotToBePresent(getWebDriver(),10);
                    } else {
                        waitForExpectedElement(SHOP_FLAVORS_LINK, 10);
                        try {
                            waitAndClickByElementByJSExecutor(SHOP_FLAVORS_LINK, 5);
                        } catch (Exception e) {
                            UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("PlpUrl.key"));
                        }
                    }
                    break;
                case "vuseco":
                    waitForElementToAppearAndDisappear(LOADER,5,10);
                    if (UrlBuilder.isDesktop()) {
                        waitForPage();
                        //waitForURLToContain("customer/account");
                        waitAndClickByElementByJSExecutor(SHOP_FLAVORS_LINK,10);
                        waitForAjaxElementNotToBePresent(getWebDriver(),10);
                        if(!doesURLContain("es/capsulas"))
                            clickElementByQueryJSExecutor(webDriver.findElements(SHOP_FLAVORS_LINK).get(0));
                        waitForPage();
                    }else {
                        waitForPage();
                        waitForURLToContain("customer/account");
                        scrollToPageTop();
                        waitForItemToBeClickableAndClick(M_SANDWICH_MENU_ICON_UK);
                        waitForItemToBeClickableAndClick(VUSECO_FLAVOURS);
                        waitForItemToBeClickableAndClick(VUSECO_SUBFLAVOURS);
                    }
                    break;
                case "vuseza":
                    if (UrlBuilder.isDesktop()) {
                        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("LiquidiLink.key")), 10).click();
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("LiquidiLink.key")));
                    } else {
                        waitForItemToBeClickableAndClick(M_FLAVOURS_VUSE_ZA);
                    }
                    break;
                case "fr":
                case "vusefr":
                    if(UrlBuilder.isDesktop()){
                        waitForExpectedElement(SHOP_FLAVORS_LINK, 10);
                        waitForExpectedElement(SHOP_FLAVORS_LINK).click();
                        LOG.info("Clicked Shop menu using selenium click.");
                        waitForPage();
                        if(!getCurrentUrl().contains("/e-liquides")){
                            clickUsingJS(SHOP_FLAVORS_LINK);
                            LOG.info("Selenium click doesn't work, click using js again.");
                        }
                    }
                    else if (UrlBuilder.isSamsungMobile()) {
                        waitForExpectedElement(By.xpath("//span[@class='categoryLink']".replace("categoryLink", UrlBuilder.getMessage("eLiquidesHeaderLink.key")))).click();
                    } else if (UrlBuilder.isIpad()) {
                        waitForExpectedElement(LIQUIDS_LINK_FR_IPAD).click();
                    } else {
                        waitForExpectedElement(SHOP_FLAVORS_LINK, 10);
                        waitForExpectedElement(SHOP_FLAVORS_LINK).click();
                    }
                    break;
                case "de":
                    List<WebElement> liquidLink = getWebDriver().findElements(LIQUID_LINK);
                    if (liquidLink.size() > 1)
                        clickElementByQueryJSExecutor(liquidLink.get(1));
                    break;
                case "nl":
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("LiquidiUrlText.key")), 10).click();
                    break;
                case "ie":
                    scrollToElement(By.linkText(UrlBuilder.getMessage("eLiquidesUrlText.key")));
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("eLiquidesUrlText.key")), 20).click();
                    break;
                default:
                    waitForExpectedElement(HEADER_ITEM_FIRST).click();
            }
        } else {
            switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
                case VUSEZA:
                    waitForItemToBeClickableAndClick(M_FLAVOURS_VUSE_ZA);
                    break;
                case VUSEDK:
                    waitForItemToBeClickableAndClick(M_BURGER_MENU_ZA, 10);
                    waitForExpectedElement(By.linkText("Devices"), 10);
                    clickByElementByQueryJSExecutor(By.linkText("Devices"));
                    break;
                case VUSEDE:
                case VUSEFR:
                    if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIPhone() || UrlBuilder.isMobile()) {
                        refreshBrowser();
                        waitForItemToBeClickableAndClick(M_BURGER_MENU_VUSEFR, 10);
                        if(!isElementPresent(M_MENU_CLOSE_BTN_VUSEFR))
                        {
                            clickUsingJS(M_BURGER_MENU_VUSEFR);
                        }
                        try {
                            // if logged in - category sub menus are upper case, if not logged in they are lower, so this accounts for both
                            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("LiquidiUrlText.key").toUpperCase()), 10).click();
                        } catch (Exception e) {
                            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("LiquidiUrlText.key").toUpperCase()), 10).click();
                        }
                        waitForExpectedElement(M_LIQUIDS_PRODUCT_LINK,10);
                        clickFirstElementByQueryJSExecutor(M_LIQUIDS_PRODUCT_LINK);
                    }
                    break;
                case VUSEUK:
                    if (UrlBuilder.isIPhone() || UrlBuilder.isMobile()) {
                        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("flavourHeaderLinkTextIphone.key")), 20);
                        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("flavourHeaderLinkTextIphone.key")));
                    }
                    break;
                case VUSEIT:
                    waitForItemToBeClickableAndClick(M_BURGER_MENU_VUSEDE, 10);
                    if (!isElementPresent(By.linkText(UrlBuilder.getMessage("LiquidiLink.key"))))
                    {
                        refreshBrowser();
                        waitForItemToBeClickableAndClick(M_BURGER_MENU_VUSEDE, 10);
                    }
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("LiquidiLink.key")), 10);
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("LiquidiLink.key")));
                    waitForItemToBeClickableAndClick(BURGER_MENU_ITEM_1,10);
                    clickByElementByQueryJSExecutor(BURGER_MENU_PRODUCT_CATEGORY_LINK);
                    break;
                case VUSECO:
                    if (UrlBuilder.isMobile() || UrlBuilder.isIPhone() || UrlBuilder.isSamsungMobile()) {
                        waitForPage();
                        waitForURLToContain("customer/account");
                        scrollToPageTop();
                        waitForItemToBeClickableAndClick(M_SANDWICH_MENU_ICON_UK);
                        waitForItemToBeClickableAndClick(VUSECO_FLAVOURS);
                        waitForItemToBeClickableAndClick(VUSECO_SUBFLAVOURS);
                    }
                    break;
                default:
            }
        }
        plp.eyesCheckPlpPage();
        plp.eyesCheckPlpHoverRegion();
        plp.eyesCheckFlavorSubscriptionModalPage();
    }

    public void navigateToPLPGlo() {
        waitForExpectedElement(HEADER_ITEM_FIRST_GLO);
        webDriver.findElements(HEADER_ITEM_FIRST_GLO).get(0).click();
        switch (UrlBuilder.getLocale()) {
            case "glode":
            case "kz":
                waitForExpectedElement(MENU_ITEM_FIRST_GLO).click();
                break;
        }
    }

    public void openBasketifNotOpenLogic() throws InterruptedException {
        waitForAjaxElementNotToBePresent(webDriver,20);
        boolean miniCartOpen = getWebDriver().findElements(MINI_CART_OPEN_STATUS).size() != 0;
        if (!miniCartOpen) {
            clickOnBasketIcon();
        }
    }

    public void assertHeaderTextOfSignUpPopupIsDisplayed(String expectedText) {
        waitForExpectedElement(SIGN_UP_POPUP, 10);
        assertEquals(UrlBuilder.getMessage(expectedText), getTrimTextFor(SIGN_UP_POPUP_HEADER_TEXT).trim());
    }

    public void assertMessageTextOfSignUpPopupIsDisplayed(String expectedText) {
        waitForExpectedElement(SIGN_UP_POPUP, 10);
        assertEquals(expectedText, getTrimTextFor(SIGN_UP_POPUP_HEADER_TEXT).trim());
    }

    public void clickOnJoinNowButton() {
        try {
            waitForExpectedElement(SIGN_UP_POPUP_JOIN_NOW_BUTTON, 10);
            waitForExpectedElement(SIGN_UP_POPUP_JOIN_NOW_BUTTON).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(SIGN_UP_POPUP_JOIN_NOW_BUTTON);
        }
    }

    public void verifyAccountSetupPageIsOpened() {
        assertTrue(waitForExpectedElement(ACCOUNT_SET_UP_PAGE, 10).isDisplayed());
    }

    public void assertSignUpPopUpNotDisplayed() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 30);
        assertEquals(getWebDriver().findElements(SIGN_UP_POPUP).size(), 0);
    }

    public void clickOnCloseButtonFromSignUpPopUpAndVerifyPopupIsClosed() {
        if (UrlBuilder.getLocale().equalsIgnoreCase("de")) {
            waitForExpectedElement(SIGN_UP_POPUP, 10);
            clickByElementByQueryJSExecutor(CLOSE_BUTTON);
            assertSignUpPopUpNotDisplayed();
        }
    }

    public void signUpPopUpIsNotVisible() {
        assertFalse(webDriver.findElement(SIGN_UP_POPUP_HEADER_TEXT).isDisplayed());
    }

    public boolean assertURLsInternalRedirectWithStatusCode(By by) throws Throwable {
        int intCounter = 0;
        int intSize = 0;
        try {
            intSize = getWebDriver().findElements(by).size();
            List<WebElement> lstLinks = getWebDriver().findElements(by);
            for (WebElement eleLink : lstLinks) {
                if (!eleLink.getAttribute("href").contains("javascript:void(0)")) {
                    assertTrue(eleLink.getAttribute("href").endsWith("/"));
                    orderSuccessPage.verifyURLStatusCodeIsValid(eleLink.getAttribute("href"));
                    intCounter++;
                }
            }
        } catch (Exception e) {
            LOG.info("Failed to assert URL redirect and status code due to exception" + e.getMessage());
        }
        return intSize == intCounter;
    }

    public void assertInternalRedirectOfHeaderURLlinksWithStatusCode() throws Throwable {
        assertTrue(assertURLsInternalRedirectWithStatusCode(HEADER_REDIRECT_LINKS));
        assertTrue(assertURLsInternalRedirectWithStatusCode(HEADER_USERBAR_LINKS));
    }

    public void assertInternalRedirectOfFooterLinksWithStatusCode() throws Throwable {
        assertTrue(assertURLsInternalRedirectWithStatusCode(FOOTER_REDIRECT_LINKS));
        assertTrue(assertURLsInternalRedirectWithStatusCode(NEWSLETTER_SECTION_LINKS));
    }

    public void assertInternalRedirectOfDevicesSublinksWithStatusCode() throws Throwable {
        hoverOnElement(By.linkText("E-Zigaretten"));
        assertTrue(assertURLsInternalRedirectWithStatusCode(DEVICES_SUB_LINKS));
    }

    public void assertInternalRedirectOfFlavoursSublinksWithStatusCode() throws Throwable {
        hoverOnElement(By.linkText("E-Liquids"));
        assertTrue(assertURLsInternalRedirectWithStatusCode(FLAVOURS_SUB_LINKS));
    }

    public void usersClicksOnTheLogoutTextLink(String linkText) {
        if (UrlBuilder.getLocale().equalsIgnoreCase("fr") && linkText.equalsIgnoreCase("viewBasketText.key")) {
            clickOnProceedToCartCTA();
        } else {
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)), 10);
            clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
            switch (UrlBuilder.getLocale()) {
                case "lyftse":
                    waitForExpectedElement(FIRST_ADDTOCART_BTN_LYFTSE, 20);
                    urlContainsText("shopLyftLink");
                    break;
                default:
                    break;
            }
        }
    }

    public void ensureNewsletterElementIsPresentOnPage() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "ukLive":
            case "vuseuk":
            case "vuseuklive":
            case "mx":
            case "vusemx":
            case "vuseco":
                assertTrue(isElementPresent(btnNewsLetterSubscribeUK));
                break;
            case "dk":
            case "vusedk":
                break;
            default:
                assertTrue(isElementPresent(newsLetterInputBox));
        }
    }

    public void ensureNewsletterSignUpButtonPresentOnPage() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "ukLive":
            case "dk":
            case "vusedk":
            case "vuseuk":
            case "vuseuklive":
            case "mx":
            case "vusemx":
                break;
            default:
                assertTrue(isElementPresent(newsLetterSubscribeButton));
        }
    }

    public void selectFromMyAccountLinksNewsLetterLink() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
            case "lyftse":
            case "vypeit":
            case "vuseit":
            case "uk":
            case "vuseuk":
                newsLettersPage.clickNewslettersLink();
                break;
            default:
                waitForExpectedElement(By.cssSelector("#account-nav > ul > li:nth-child(4) > a")).click();
        }
    }

    public void selectAllowAllFromNewCookieConsentPopup() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "kz":
                waitForExpectedElement(allowCookiesButton, 10).isDisplayed();
                waitForExpectedElement(allowCookiesButton, 10).click();
                break;
            case "glode":
            case "de":
                waitForExpectedElement(By.cssSelector("button#onetrust-accept-btn-handler"), 15).isDisplayed();
                waitForExpectedElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
                break;
            case "lyftdk":
            case "dk":
                waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK, 10).isDisplayed();
                waitForExpectedElement(OTCOOKIES_SETTINGS_BUTTON_DK).click();
                waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK, 10).isDisplayed();
                waitForExpectedElement(ALLOW_COOKIES_BUTTON_DK).click();
                break;
            case "mx":
            case "vusemx":
                waitForExpectedElement(allowCookiesButton, 15).isDisplayed();
                eyesCheckCookieBanner();
                waitForExpectedElement(allowCookiesButton, 15).click();
                break;
            default:
                try {
                    waitForExpectedElement(allowCookiesButton, 10).isDisplayed();
                    waitForExpectedElement(allowCookiesButton, 10).click();
                } catch (Exception e) {
                    waitForExpectedElement(AllowCookiesButtonBox, 10).isDisplayed();
                    waitForExpectedElement(AllowCookiesButtonBox, 10).click();
                }
        }
    }

    public void eyesCheckMiniCart() {
        if (Props.EYES_ON && EyesCheckpoints.MINI_CART.isSwitchOn()) {
            final String checkpointName = EyesCheckpoints.MINI_CART.getName();
            switch (UrlBuilder.getLocale()) {
                case "vuseco":
                case "vuseza":
                case "vusede":
                case "mx":
                case "vusemx":
                case "uk":
                case "fr":
                case "glode":
                case "it":
                case "vypeit":
                case "vuseit":
                case "kz":
                case "pl":
                case "lyftdk":
                case "vusefr":
                    eyesCheckRegion(checkpointName, MINI_CART_BLOCK);
                    break;
                case "vuseuk":
                    eyesCheckRegion(checkpointName, MINI_CART_BLOCK, !UrlBuilder.isIpad());
                    break;
                case "lyftse":
                    if (Props.USE_EYES_GRID) {
                        eyesCheckVisiblePage(checkpointName);
                    } else {
                        eyesCheckRegion(checkpointName, MINI_CART_BLOCK);
                    }
                    break;
                default:
                    eyesCheckRegion(checkpointName, MINI_CART_SLIDING);
            }
        }
    }

    public void eyesCheckCookieBanner() {
        if (Props.EYES_ON && EyesCheckpoints.COOKIE_BANNER.isSwitchOn()) {
            final String checkpointName = EyesCheckpoints.COOKIE_BANNER.getName();
            switch (UrlBuilder.getLocale().toLowerCase()) {
                case "vusede":
                    eyesCheckRegion(checkpointName, UrlBuilder.isIpad() ? LYFT_SE_COOKIE_BANNER : COOKIE_BANNER);
                    break;
                case "vuseit":
                    if (Props.USE_EYES_GRID) {
                        String hook = "if (window.navigator && window.navigator.userAgent && " +
                                "(window.navigator.userAgent.match(/iPad/i) || window.navigator.userAgent.match(/iPhone/i))) " +
                                "document.getElementsByTagName('html')[0].style.webkitTransform='translate3d(0px, 0px, 0px)';";
                        eyes.check(checkpointName, Target.region(COOKIE_BANNER).beforeRenderScreenshotHook(hook));
                    } else {
                        eyesCheckRegion(checkpointName, UrlBuilder.isIpad() ? LYFT_SE_COOKIE_BANNER : COOKIE_BANNER);
                    }
                    break;
                case "lyftse":
                    if (Props.USE_EYES_GRID) {
                        eyesCheckVisiblePage(checkpointName);
                    } else {
                        eyesCheckRegion(checkpointName, LYFT_SE_COOKIE_BANNER);
                    }
                    break;
                case "vuseza":
                    eyesCheckVisiblePage(checkpointName);
                    break;
                default:
                    eyesCheckRegion(checkpointName, COOKIE_BANNER);
            }
        }
    }

    public void eyesCheckAgeGate() {
        if (Props.EYES_ON && EyesCheckpoints.AGE_GATE.isSwitchOn()) {
            final String checkpointName = EyesCheckpoints.AGE_GATE.getName();
            switch (UrlBuilder.getLocale().toLowerCase()) {
                case "glojp":
                    eyes.check(checkpointName, Target.window().fully().ignoreDisplacements());
                    break;
                case "pl":
                case "vuseuk":
                case "vuseit":
                    if (Props.USE_EYES_GRID) {
                        eyes.check(checkpointName, Target.region(AGE_GATE));
                    } else {
                        eyesCheckVisiblePage(checkpointName);
                    }
                    break;
                case "velobe":
                    eyesCheckRegion(checkpointName,AGE_GATE_VELO_BE);
                    break;
                default:
                    eyesCheckVisiblePage(checkpointName);
            }
        }
    }

    public boolean assertRecyclingSchemeConfigured() throws Throwable {
        storePage.performBackEndStepsToAssertRecyclingScheme();
        if (getWebDriver().findElements(storePage.SELECT_POD_RECYCLE_SETTING).size() > 0) {
            List<WebElement> strOptions = getWebDriver().findElements(storePage.SELECT_POD_RECYCLE_OPTIONS);
            if (strOptions.get(0).getText().equals("Yes") && !waitForExpectedElement(storePage.QTY_POD_RECYCLE_BAG).getAttribute("value").isEmpty()) {
                blnFlag = true;
                System.setProperty("MXRecyclingScheme.key", String.valueOf(blnFlag));
            } else {
                blnFlag = false;
                System.setProperty("MXRecyclingScheme.key", String.valueOf(blnFlag));
            }
        }
        productPage.performBackEndStepsToAssertRelatedUpSellProductsConfiguration("Drop the Pod", "Spanish");
        if (getWebDriver().findElements(storePage.POD_RECYCLE_SWITCH).size() > 0) {
            String strRecycleCheck = waitForExpectedElement(storePage.POD_RECYCLE_SWITCH).getAttribute("data-bind");
            if (strRecycleCheck.contains("checked") && (!strRecycleCheck.contains("disable"))) {
                blnFlag = true;
                System.setProperty("MXPodRecycleBagProduct.key", String.valueOf(blnFlag));
            } else {
                blnFlag = false;
                System.setProperty("MXPodRecycleBagProduct.key", String.valueOf(blnFlag));
            }
        } else {
            blnFlag = false;
            System.setProperty("MXPodRecycleBagProduct.key", String.valueOf(blnFlag));
        }
        return blnFlag;
    }

    public void loginIntoMagentoAdminAndAssertRecyclingSchemeIsSetUp() throws Throwable {
        adminLoginPage.userLoginsMagentoAdminHomePageSuccessfully();
        assertRecyclingSchemeConfigured();
    }

    public void paymentDivIsPresentAndDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
            case "vypeit":
            case "vuseit":
            case "vuseza":
                assertTrue(waitForExpectedElement(FOOTER_PAYMENT_BLOCK).isDisplayed());
                break;
            case "mx":
            case "vusemx":
                waitForExpectedElement(FOOTER_PAYMENT_BLOCK_MX);
                scrollElementIntoView(FOOTER_PAYMENT_BLOCK_MX);
                assertTrue(waitForExpectedElement(FOOTER_PAYMENT_BLOCK_MX).isDisplayed());
                break;
            case "vuseco":
                assertTrue(waitForExpectedElement(FOOTER_PAYMENT_BLOCK_VUSECO).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(footerPaymentOptionsContentBlock).isDisplayed());
        }
    }

    public void footerListItemsAreDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
            case "vuseza":
                assertTrue(waitForExpectedElement(LIST_FOOTER_ITEMS).isDisplayed());
                break;
            case "vuseit":
                assertTrue(isElementDisplayedBySeconds(LIST_FOOTER_ITEMS,10));
                break;
            case "mx":
            case "vusemx":
            case "vypeit":
            case "vuseco":
                assertTrue(waitForExpectedElement(LIST_FOOTER_ITEMS).isDisplayed());
                break;
            default:
                assertTrue(waitForExpectedElement(listedFooterItems).isDisplayed());
        }
    }

    public void eyesCheckBasketPage() {
        if (Props.EYES_ON && EyesCheckpoints.BASKET_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            eyes.check(EyesCheckpoints.BASKET_PAGE.getName(), Target.window().fully());
        }
    }

    public void eyesCheckHomePage() {
        if (Props.EYES_ON && EyesCheckpoints.HOME_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.HOME_PAGE.getName();
            switch (UrlBuilder.getLocale()) {
                case "vuseit":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        // bypass way to solve footer truncation problem
                        refreshBrowser();
                        scrollToShowEntirePage();
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(MESSAGE_ROW));
                    }
                    break;
                case "vuseza":
                case "vusefr":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(MESSAGE_ROW));
                    }
                    break;
                case "lyftse":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully().ignore(DYNA_IMAGE));
                    } else {
                        eyes.check(checkpointName, Target.window().fully().ignore(LYFT_SE_DYNA_IMAGE));
                    }
                    break;
                case "glojp":
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(CAROUSEL_GLO, 0, 10, 20, 0));
                    break;
                case "glode":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(CAROUSEL_GLO, 0, 10, 20, 0));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(MESSAGE_ROW)
                                .ignore(CAROUSEL_GLO, 0, 10, 20, 0));
                    }
                    break;
                case "it":
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(waitForPresenceOfElement(CAROUSEL_GLO), 200, 10, 20, 0));
                    break;
                case "vusedk":
                case "lyftdk":
                case "dk":
                    eyes.check(checkpointName, Target.window().fully().ignore(CAROUSEL, CAROUSEL_DOTS));
                    break;
                case "de":
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(CAROUSEL, 0, 0, 20, 20));
                    break;
                case "kz":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully().ignore(
                                CAROUSEL_GLO,
                                HEADER_TOP));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(CAROUSEL_GLO));
                    }
                    break;
                case "pl":
                    if ((Props.USE_EYES_GRID)) {
                        String bannerHook = "if(navigator.userAgent.indexOf('Firefox')!=-1){" +
                                "const _dh=document.scrollingElement.scrollHeight;" +
                                "const _f=document.querySelector('div.health-warning-container');" +
                                "_f.style=`position:absolute;top:${_dh-_f.offsetHeight}px;bottom:auto;`;" +
                                "const _b=document.querySelector('div#ot-sdk-btn');" +
                                "_b.style=`position:absolute;top:${_dh-_f.offsetHeight-22}px;bottom:auto;`;" +
                                "const _c=document.querySelector('div[id^=\"liveagent_button_\"]');" +
                                "_c.style=`position:absolute;top:${_dh-25}px;bottom:auto;`;" +
                                "}";
                        eyes.check(checkpointName, Target.window().fully().beforeRenderScreenshotHook(bannerHook));
                    } else {
                        if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                            eyes.check(checkpointName, Target.window().fully().ignore(
                                    HYPER_SLIDE_ITEMS,
                                    HYPER_DISCOVER_ITEMS)
                                    .ignoreDisplacements());
                        } else { // mobile
                            eyes.check(checkpointName, Target.window().fully().ignore(
                                    MESSAGE_ROW,
                                    HYPER_SLIDE_ITEMS,
                                    HYPER_DISCOVER_ITEMS)
                                    .ignoreDisplacements());
                        }
                    }
                    break;
                case "epok":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully().ignore(CAROUSEL_GLO));
                    } else { // mobile
                        // Temporary solution for long page capturing
                        eyes.check(checkpointName + "(1/6)", Target.window().fully().ignore(CAROUSEL_GLO));
                        eyes.check(Target.region(By.cssSelector("#maincontent > div.columns > div > div:nth-child(14)")).fully()
                                .withName(checkpointName + "(2/6) - WIE FUNKTIONIERTS?"));
                        eyes.check(Target.region(By.cssSelector("#maincontent > div.columns > div > div:nth-child(15)")).fully()
                                .withName(checkpointName + "(3/6) - WAS IST IN DEN VELO NICOTINE POUCHES DRIN?"));
                        eyes.check(Target.region(By.cssSelector("#maincontent > div.columns > div > div:nth-child(16)")).fully()
                                .withName(checkpointName + "(4/6) - WIE LANGE SOLL ICH DIE POUCH IM MUND BEHALTEN?"));
                        eyes.check(Target.region(By.cssSelector(".footer-container .info-line")).fully()
                                .withName(checkpointName + "(5/6) - Footer 1"));
                        eyes.check(Target.region(By.cssSelector(".footer-container .imprint-line")).fully()
                                .withName(checkpointName + "(6/6) - Footer 2"));
                    }
                    break;
                case "velobe":
                case "velopl":
                    eyesCheckFullyPageIgnoreDisplacements(checkpointName);
                    break;
                default:
                    if (Props.USE_EYES_GRID) {
                        waitForPage();
                        scrollToShowEntirePage();
                        eyes.check(checkpointName, Target.window().layoutBreakpoints(true));
                    } else {
                        eyesCheckFullyPage(checkpointName);
                    }
            }
        }
    }

    public void clickOnAboutPageLink(String hrefKey) {
        if (UrlBuilder.isMobile()) {
            clickOnMobileHamBurgerLinkWithHref(hrefKey);
        } else {
            clickOnLinkWithHref(hrefKey);
        }
    }

    private void clickOnMobileHamBurgerLinkWithHref(String key) {
        By cssSelector = By.cssSelector("li.burger-menu-item a[href*='" + UrlBuilder.getMessage(key) + "']");
        clickByElementByQueryJSExecutor(cssSelector);
    }

    public boolean assertDeviceTrialLandingPage() {
        return waitForExpectedElement(DEVICE_TRIAL_CTA).isDisplayed();
    }

    public void clickOnTheDeviceTrialCTA() {
        waitForExpectedElement(DEVICE_TRIAL_CTA);
        clickByElementByQueryJSExecutor(DEVICE_TRIAL_CTA);
        waitForAjaxElementNotToBePresent(webDriver,3);
    }

    public void selectConfigurableProduct() {
        waitForExpectedElement(CONFIGURABLE_PRODUCT_CHECKBOX);
        clickByElementByQueryJSExecutor(CONFIGURABLE_PRODUCT_CHECKBOX);
    }

    public boolean assertConfigurableProduct() {
        return waitForExpectedElement(CONFIGURABLE_PRODUCT_CHECKBOX, 10).isDisplayed();
    }

    public void hoverOverGloShopAndSelectNeoSticks() {
        switch (UrlBuilder.getLocale()){
            case "glode":
                waitForExpectedElement(NEO_STICKS_PRODUCT_LINK, 20);
                clickByElementByQueryJSExecutor(NEO_STICKS_PRODUCT_LINK);
                break;
            default:
                waitForExpectedElement(GLO_SHOP_LINK, 30);
                hoverOnElement(GLO_SHOP_LINK);
                clickByElementByQueryJSExecutor(NEO_STICKS_LINK);
        }
    }

    public void customerClicksOnBasketIcon() {
        waitForExpectedElement(BASKET_ICON);
        clickByElement(BASKET_ICON);
        clickByElement(PROCEED_TO_CART_BUTTON);
    }

    public boolean cartQuantityPickerIsPresent() {
        return waitForExpectedElement(inputItemQuantity).isDisplayed();
    }

    public boolean loginContainerIsPresent() {
        return waitForExpectedElement(loginContainer, 10).isDisplayed();
    }

    public void userNavigatesToURLAndAssertsTextContains() {
        String url = "home";
        String strCurrentURL = getWebDriver().getCurrentUrl();
        String strToNavigateURL = strCurrentURL + url;
        getWebDriver().navigate().to(strToNavigateURL);
        assertFalse(getWebDriver().getCurrentUrl().contains(url));
    }

    public void navigateToURLAndVerifyTheURLNotContains(DataTable dtURLs) {
        String strCurrentURL = getCurrentUrl();
        List<List<String>> lstURLs = dtURLs.raw();
        for (List<String> lstURL : lstURLs) {
            String strToNavigateURL = strCurrentURL + lstURL.get(0).toString();
            getWebDriver().navigate().to(strToNavigateURL);
            assertFalse(getWebDriver().getPageSource().contains("meta name=\"keywords\" content=\"Magento, Varien, E-commerce\""), "Expected META DATA Keyword:" + lstURL.get(0).toString() + " is PRESENT");
        }
    }

    public void assertLyftLogoAtCheckOutPageIsDisplayed() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        verifyLyftLogo();
    }

    public void verifyLyftLogo() {
        assertTrue(isElementPresent(LYFT_LOGO_IMAGE));
    }

    public void assertPageTitle(By webElement, String pageTitle) {
        waitForExpectedElement(webElement, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        assertTrue(getCurrentPageTitle().toUpperCase().contains(UrlBuilder.getMessage(pageTitle).toUpperCase()));
    }

    public void assertNavigatedToCorrectUrl(String url) {
        waitForExpectedElement(TRACK_ORDER_BOX_ICON, 10).click();
        getBrowserPageUrl(url);
    }

    public WebDriver getBrowserPageUrl(String pageUrl) {
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver = webDriver.switchTo().window(windowHandle);
            if (webDriver.getCurrentUrl().contains(pageUrl)) {
                return webDriver;
            }
        }
        return null;
    }

    public void assertCookieDetailsLinkDisplayedForEachCookieType() {
        List<WebElement> lstCookieDescription = getWebDriver().findElements(COOKIES_DETAILS_LINK);
                assertEquals(lstCookieDescription.size(), 4);
        }

    public void assertCookieSettingsLinkCTAForEachCookieType() {
        List<WebElement> lstCookieDescription = getWebDriver().findElements(COOKIES_DETAILS_LINK);
        switch (UrlBuilder.getLocale()) {
            case "veloza":
            case "lyftdk":
                for (WebElement eleLink : lstCookieDescription) {
                    eleLink.click();
                    assertTrue(waitForExpectedElement(COOKIES_DETAILS_SECTION_TITLE).isDisplayed());
                    waitForExpectedElement(COOKIES_DETAILS_BACK_BUTTON).click();
                }
                break;
            default:
        }
    }

    public void assertFaqOptionIsDisplayed() throws Throwable {
        clickOnFaqOption("false");
        clickOnFaqOption("true");
    }

    public void clickOnFaqOption(String value) throws Throwable {
        WebElement ele = WebDriverHelper.getWebDriver().findElement(FAQ_CONTENT);
        scrollElementIntoView(FAQ_COLLAPSE_ICON);
        List<WebElement> icons = WebDriverHelper.getWebDriver().findElements(FAQ_COLLAPSE_ICON);
        LOG.info("Total number of FAQs found : " + icons.size());
        for (WebElement icon : icons) {
            icon.click();
            Thread.sleep(1000);
            assertEquals(value, ele.getAttribute("aria-hidden"));
        }
    }

    public void assertNavigatedToCorrectBlogUrl(String pageTitle) {
        assertPageTitle(BLOG_LYFT_LAB, pageTitle);
    }

    public void assertProductIsGettingOpened() {
        String actual = WebDriverHelper.getWebDriver().findElements(BLOG_ITEM_TEXT).get(0).getText().trim();
        clickFirstElementByQueryJSExecutor(READ_MORE);
        String expected = getTrimTextFor(BLOG_ITEM_HEADER_TEXT);
        assertTrueWithCustomError(actual, expected);
        assertTrue(waitForExpectedElement(BLOG_ITEM_CONTENT, 10).isDisplayed());
    }

    public void phoneNumberFieldValidation() {
        assertTrueWithCustomError("+33", getValue(RegistrationPage.phoneNumber));
        waitForExpectedElement(RegistrationPage.phoneNumber, 10).sendKeys("@3456");
        clickByElementByQueryJSExecutor(RegistrationPage.CREATEACCOUNT_BUTTON);
        scrollElementIntoView(RegistrationPage.phoneNumber);
        assertTrue(isElementPresent(PHONE_NUMBER_FIELD_ERROR));
        waitClearAndEnterText(RegistrationPage.phoneNumber, "612345678");
        clickByElementByQueryJSExecutor(RegistrationPage.CREATEACCOUNT_BUTTON);
    }

    public void assertWhatIsLyftLabContentIsDisplayed() {
        assertTrue(waitForExpectedElement(WHAT_IS_LYFTLAB_HEADER, 10).isDisplayed());
        assertTrue(waitForExpectedElement(WHAT_IS_LYFTLAB_CONTENT, 10).isDisplayed());
        assertTrue(waitForExpectedElement(LYFT_LAB_COLLECTION_ITEM, 10).isDisplayed());
    }

    public void assertOptInOutOfMobileConsentIsDisplayed() {
        waitForExpectedElement(MY_PREFEENCES, 10);
        clickByElementByQueryJSExecutor(MY_PREFEENCES);
        assertTrue(waitForExpectedElement(MOBILE_CONSENT_OPTION, 10).isDisplayed());
        boolean beforeOpt = waitForExpectedElement(MOBILE_CONSENT_OPTION).getAttribute("checked")!=null;
        clickByElementByQueryJSExecutor(MOBILE_CONSENT_OPTION);
        clickByElementByQueryJSExecutor(SAVE_BUTTON_FOR_MOBILE_CONSENT);
        assertTrue(waitForExpectedElement(MOBILE_CONSENT_CONFIRMATION_MESSAGE, 10).isDisplayed());
        clickByElementByQueryJSExecutor(MY_PREFEENCES);
        boolean afterOpt = waitForExpectedElement(MOBILE_CONSENT_OPTION).getAttribute("checked")!=null;
        Assert.assertNotEquals(beforeOpt, afterOpt);
    }

    public void deleteProductFromRemoveButton() {
        switch (UrlBuilder.getLocale()) {
            case "nl":
                waitForExpectedElement(VYPE_REMOVE_BUTTON_NL, 10).click();
                waitForExpectedElement(REMOVE_POPUP_BUTTON, 10).click();
                break;
            case "pl":
                clickByElementByQueryJSExecutor(REMOVE_BUTTON);
                clickByElementByQueryJSExecutor(REMOVE_POPUP_BUTTON);
                break;
            default:
                waitForExpectedElement(REMOVE_BUTTON, 20).click();
                waitForExpectedElement(REMOVE_POPUP_BUTTON, 10).click();
        }
    }

    public void deleteTrialProductFromRemoveButton() {
        waitForExpectedElement(PROCEED_TO_VIEWBASKET, 10).click();
        getWebDriver().findElements(TRAIL_REMOVE_BUTTON).get(0).click();
        waitForExpectedElement(TRAIL_REMOVE_POPUP_BUTTON,15);
        clickUsingJS(TRAIL_REMOVE_POPUP_BUTTON);
    }

    public void amountOnMiniCart(String strBasketQty) {
        switch (UrlBuilder.getLocale()) {
            case "nl":
                assertEquals("", getCartQty());
                break;
            case "dk":
            case "vuseco":
                waitForTextToBe(getWebDriver(), 10, MINI_CART_AMOUNT, UrlBuilder.getMessage(strBasketQty));
                assertTrueWithCustomError(UrlBuilder.getMessage(strBasketQty), waitForExpectedElement(MINI_CART_AMOUNT, 10).getText());
                break;
            case "vuseza":
                assertTrueWithCustomError(UrlBuilder.getMessage(strBasketQty),
                        waitForExpectedElement(BASKET_ICON_ZA, 10).getText());
                break;
            default:
                waitForTextToBe(getWebDriver(), 10, MINI_CART_AMOUNT, strBasketQty);
                assertTrueWithCustomError(strBasketQty,
                        waitForExpectedElement(MINI_CART_AMOUNT, 10).getText());
        }
    }

    public void selectOrderSubsFirstCTA(Boolean loggedIn, String discount) throws InterruptedException {
        switch (UrlBuilder.getLocale()){
            case "vusefr":
                if (loggedIn) {
                    waitForExpectedElement(GLOBAL_SUBS_ORDER_SUBS_FIRST_LOGGEDIN_CTA_VUSEFR).click();
                } else {
                    waitForExpectedElement(GLOBAL_SUBS_ORDER_SUBS_FIRST_CTA).click();
                    assertTrue(waitForExpectedElement(GLOBAL_SUBS_ORDER_NON_SUBS_DISCOUNT_TEXT, 6).getText().contains(discount));
                    Thread.sleep(3000);
                    waitForExpectedElement(GLOBAL_SUBS_ORDER_NON_SUBS_CONFIRMATION_CTA).click();
                }
                break;
            default:
                if (loggedIn) {
                    waitForExpectedElement(GLOBAL_SUBS_ORDER_SUBS_FIRST_LOGGEDIN_CTA).click();
                } else {
                    waitForExpectedElement(GLOBAL_SUBS_ORDER_SUBS_FIRST_CTA).click();
                    assertTrue(waitForExpectedElement(GLOBAL_SUBS_ORDER_NON_SUBS_DISCOUNT_TEXT, 6).getText().contains(discount));
                    Thread.sleep(3000);
                    waitForExpectedElement(GLOBAL_SUBS_ORDER_NON_SUBS_CONFIRMATION_CTA).click();
                }
        }

    }

    public void selectOrderNonSubsCTA() {
        switch(UrlBuilder.getLocale()){
            case "vusefr":
                waitForExpectedElement(GLOBAL_SUBS_ORDER_NON_SUBS_CTA_VUSEFR).click();
                break;
            default:
                waitForExpectedElement(GLOBAL_SUBS_ORDER_NON_SUBS_CTA).click();
        }
        clickByElementByQueryJSExecutor(GLOBAL_SUBS_ORDER_NON_SUBS_CONFIRMATION_CTA);
    }

    public void selectGlobalSubsPolicyLink() {
        scrollElementIntoView(GLOBAL_SUBS_POLICY_LINK);
        waitForExpectedElement(GLOBAL_SUBS_POLICY_LINK).click();
    }

    public void checkGlobalSubsErrorMessageDisplay(String number, String errorMessage, By errorMessageDisplayMethod){
        if (errorMessage.contains(number)) {
            waitForAjaxElementNotToBePresent(getWebDriver(),5);
            assertEquals(errorMessage, waitForExpectedElement(errorMessageDisplayMethod,5).getText());
            WebDriverWait wait = new WebDriverWait(webDriver, 15);
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString().equals("complete"));
            assertFalse(cartCheckoutButtonIsEnabled(CHECKOUT_CART_BUTTON));
        } else {
            assertEquals(errorMessage.replaceAll("[^A-Za-z0-9]", ""), waitForExpectedElement(GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSEFR).getText().replaceAll("[^A-Za-z0-9]", ""));
        }
    }

    public void globalSubsErrorAssertCart(String errorMessage) {
        switch(UrlBuilder.getLocale()){
            case "vuseco":
                checkGlobalSubsErrorMessageDisplay("4",errorMessage,GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSECO);
                break;
            case "vusefr":
                checkGlobalSubsErrorMessageDisplay("5",errorMessage,GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSEFR);
                break;
            case "vuseit":
            case "vuseuk":
                checkGlobalSubsErrorMessageDisplay("4",errorMessage,GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSEFR);
                break;
        }
    }

    public void globalSubsErrorAssertCartMaxQty(String errorMessage){
        switch(UrlBuilder.getLocale()) {
            case "vuseit":
                checkGlobalSubsErrorMessageDisplay("4", errorMessage, GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSECO);
                break;
        }
    }

    public void clickCheckoutButtonOnCartPage() {
        switch (UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
            case "vuseco":
                assertTrue(cartCheckoutButtonIsEnabled(CHECKOUT_CART_BUTTON));
                clickByElementByQueryJSExecutor(CHECKOUT_CART_BUTTON);
                break;
            case "lyftse":
                assertTrue(cartCheckoutButtonIsEnabled(CHECKOUT_CART_BUTTONSE));
                clickByElementByQueryJSExecutor(CHECKOUT_CART_BUTTONSE);
                break;
        }
    }

    public boolean cartCheckoutButtonIsEnabled(By checkoutButton) {
        return isElementClickable(checkoutButton);
    }

    public void assertCartMessageHasDisappeared() {
        switch(UrlBuilder.getLocale()){
            case "vusefr":
                assertTrue(invisibilityOfElementLocated(GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSEFR));
                break;
            default:
                assertTrue(invisibilityOfElementLocated(GLOBAL_SUBS_ERROR_MESSAGE_HEADER_DISPLAY_VUSECO));
        }
    }

    public void switchToENSite() {
        waitForExpectedElement(LANGUAGE_SWITCHER_TRIGGER_CSS).click();
        waitForExpectedElement(SWITCH_LANGUAGE_CSS).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public void clickViewBasketButton() {
        if (UrlBuilder.isSamsungMobile() || UrlBuilder.isIPhone()) {
            switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
                case UK:
                case VUSEDE:
                case VUSECO:
                case VUSEUK:
                case FR:
                case VUSEFR:
                case VUSEIT:
                case VUSEZA:
                    orderSuccessPage.openBasketifNotOpenLogic();
                    eyesCheckMiniCart();
                    waitAndClickByElementByJSExecutor(PROCEED_TO_BASKET_PAGE,10);
                    break;
                default:
            }
        } else {
            orderSuccessPage.openBasketifNotOpenLogic();
            waitForExpectedElement(VIEW_BASKET_BUTTON, 5).click();
        }
    }

    public void clickOnNewsletterIconOnHeader() {
        waitForExpectedElement(ICON_NEWSLETTERS_HEADER_IE).click();
    }

    public void login(String username, String password) {
        waitForExpectedElement(EMAIL_INPUTBOX, 10).sendKeys(username);
        waitForExpectedElement(PASSWORD_INPUTBOX, 10).sendKeys(password);
        clickByElementByQueryJSExecutor(SIGNIN_BUTTON);
        waitForExpectedElement(elePageTitle, 5);
    }

    public void assertCookieDescriptionDisplayedForEachCookieType() {
        List<WebElement> lstCookieDescription = getWebDriver().findElements(COOKIES_CATEGORY_DESCRIPTION);
        switch (UrlBuilder.getLocale()){
            case "veloza":
        assertEquals(lstCookieDescription.size(), 3);
        break;
            default:
                assertEquals(lstCookieDescription.size(), 4);
    }}

    /**** Validating the categories under Shop Devices ***/
    public void vuseShopDevicesDE(DataTable table) {
        List<Map<String, String>> entries = table.asMaps(String.class, String.class);
        switch (UrlBuilder.getLocale()) {
            case "vuseit":
                for (int i = 0; i < presenceOfAllElementsLocatedBy(DEVICES_CATEGORY).size(); i++) {
                    hoverOnElement(SHOP_DEVICES_VUSEIT);
                    String expectedTitle = UrlBuilder.getMessage(entries.get(i).get("Title"));
                    List<WebElement> links = presenceOfAllElementsLocatedBy(DEVICES_CATEGORY);
                    waitForExpectedElement(By.partialLinkText(expectedTitle)).click();
                    clickBrowserBackButton();
                }
                break;
            default:
                for (int i = 0; i < presenceOfAllElementsLocatedBy(DEVICES_CATEGORY).size(); i++) {
                    hoverOnElement(SHOP_DEVICES);
                    String expectedTitle = UrlBuilder.getMessage(entries.get(i).get("Title"));
                    List<WebElement> links = presenceOfAllElementsLocatedBy(DEVICES_CATEGORY);
                    waitForExpectedElement(By.linkText(expectedTitle)).click();
                    clickBrowserBackButton();
                }
        }
    }

    /**** Validating  presence of news section ***/
    public void vuseNewsSection() {
        waitForExpectedElement(VUSE_NEWS_SECTION);
        LOG.info("News Section is displayed");
    }

    public void vuseHomeCta(DataTable table) {
        List<Map<String, String>> entries = table.asMaps(String.class, String.class);
        for (int i = 0; i < entries.size(); i++) {
            List<WebElement> ctaLinks = presenceOfAllElementsLocatedBy(heroCtas);
            String expectedCta = entries.get(i).get("Label");
            String expectedTitle = UrlBuilder.getMessage(entries.get(i).get("Title"));
            waitForExpectedElement(heroCtas, 4);
            String actualCta = ctaLinks.get(i).getText();
            assertEquals(expectedCta, actualCta);
            clickElementByQueryJSExecutor(ctaLinks.get(i));
            assertTrue(expectedTitle.contains("E-Zigaretten Shop: Vuse E-Zigaretten"));
            assertTrue(getCurrentPageTitle().contains("E-Zigaretten bequem online"));
            clickBrowserBackButton();
        }
    }

    public void footerSublinkNavigate() {
        List<WebElement> links = presenceOfAllElementsLocatedBy(FOOTER_LINKS);
        try {
            for (int i = 0; i < links.size() - 2; i++) {
                List<WebElement> footer = presenceOfAllElementsLocatedBy(FOOTER_LINKS);
                String expectedValue = footer.get(i).getText();

                clickElementByQueryJSExecutor(footer.get(i));
                String[] actualValue = getCurrentPageTitle().split("\\s");
                //System.out.println("Actual:"+actualValue[0].toLowerCase());
                //System.out.println("Expected:"+expectedValue.toLowerCase());
                if (expectedValue.equalsIgnoreCase("barrierefreiheit")) {
                    break;
                }
                if (expectedValue.equalsIgnoreCase("faq")) {
                    assertTrue(getCurrentPageTitle().contains("Häufig gestellte Fragen | Vuse"));
                    clickBrowserBackButton();
                } else {
                    assertTrue(expectedValue.toLowerCase().contains(actualValue[0].toLowerCase()));
                    clickBrowserBackButton();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trustBadgePresent() {
        assertTrue(isElementPresent(TRUST_BADGE));
        clickByElementByQueryJSExecutor(TRUST_BADGE);
    }

    public void isPhoneNumberPresent(){

        switch (UrlBuilder.getLocale()){
            case "vuseit":
                if(isElementPresent(NUMBER_LINK) || isElementClickable(NUMBER_LINK));
                assertEquals(waitForExpectedElement(NUMBER_LINK).getText(),"800 986 987");
                break;
            case "veloza":
                if(isElementPresent(NUMBER_LINK_VELOZA) || isElementClickable(NUMBER_LINK_VELOZA));
                assertEquals(waitForExpectedElement(NUMBER_LINK_VELOZA).getText(),"0800 202 503");
                break;

        }
    }

    public void isEmailPresent(){

            switch (UrlBuilder.getLocale()){
                case "vuseit":
                    if(isElementPresent(EMAIL_LINK)||isElementClickable(EMAIL_LINK))
                    assertEquals(waitForExpectedElement(EMAIL_LINK).getText(),"INFO.IT@VUSE.COM");
                    break;
                case "veloza":
                    if(isElementPresent(EMAIL_LINK_VELOZA)||isElementClickable(EMAIL_LINK_VELOZA))
                        assertEquals(waitForExpectedElement(EMAIL_LINK_VELOZA).getText(),"INFO.ZA@VELO.COM");
                    break;
            }
    }

    public void expandBadge() {
        assertTrue(isElementPresent(MAXIMIZED_TRUST_BADGE));
        List<WebElement> list = getWebDriver().findElements(TRUST_LINKS);
        for (WebElement item : list) {
            assertTrue(item.isDisplayed());
        }
        clickByElementByQueryJSExecutor(CLOSE_BTN);
    }


    public void vypeMiniBanner() {
        waitForExpectedElement(MINI_BANNER);
        LOG.info("Mini Banner Section is displayed");
    }

    public void trustLogo() {
        Assert.assertTrue(waitForExpectedElement(logo).isDisplayed());
    }

    public void vuseFooterlinks() {
        List<WebElement> links = presenceOfAllElementsLocatedBy(FOOTER_LINKS);
        for (WebElement footerLink : links) {
            elementToBeClickable(footerLink);
            assertTrue(footerLink.isDisplayed());
        }
    }


    public void clickStoreLocatorIcon() {
        switch(UrlBuilder.getLocale()) {
            case "veloza":
                clickByElementByQueryJSExecutor(STORE_LOCALTOR_VELOZA);
                break;
            case "velopl":
                clickByElementByQueryJSExecutor(STORE_LOCALTOR_VELOPL);
                break;
            default:
                clickByElementByQueryJSExecutor(STORE_LOCALTOR_ICON);
        }
    }

    public void setQtyAtPDPForVuseDE() {
        // Added this check because there is a Qty by default for Vuse DE, added if condition in case there is none
        waitForAjaxElementNotToBePresent(getWebDriver(),6);
        if(Integer.parseInt(waitForExpectedElement(INPUT_ITEM_QUANTITY_SUBS,10).getAttribute("value"))==0)
            waitForExpectedElement(INCREASE_QTY_VUSE_DE).click();
    }

    public void assertFreeDeliveryBannerOnLABPages() {
        if (doesURLContain("/se/sv/") && getWebDriver().findElements(FREE_DELIVERY_BAR_SV).size() > 0) {
            assertTrue(getWebDriver().findElements(FREE_DELIVERY_BAR_SV).size() > 0);
        }
        if (doesURLContain("/se/en/") && getWebDriver().findElements(FREE_DELIVERY_BAR_EN).size() > 0) {
            assertTrue(getWebDriver().findElements(FREE_DELIVERY_BAR_EN).size() > 0);
        }
    }

    public void defaultClickLinkByLinkText(String linkText) {
        waitForAjaxElementNotToBePresent(webDriver,30);
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)), 40);
        try {
            LOG.info("selecting link");
            waitForPage();
            clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
        } catch (Exception e) {
            LOG.info("Couldn't user JS to select");
            switch (UrlBuilder.getLocale()) {
                case "fr":
                case "vusefr":
                case "vuseco":
                    clickOnSecondMenu();
                    break;
                case "glode":
                    waitForExpectedElement(PROCEED_TO_BASKET_PAGE, 30);
                    clickByElementByQueryJSExecutor(PROCEED_TO_BASKET_PAGE);
                    break;
                case "vuseit":
                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
                    break;
                default:
                    waitForExpectedElement(By.cssSelector("#account-nav > ul > li:nth-child(5) > a"), 30);
                    clickByElementByQueryJSExecutor(By.cssSelector("#account-nav > ul > li:nth-child(5) > a"));
            }
        }
    }

    public List<WebElement> getMetaTagElementsWith(String attribute) {
        return getWebDriver().findElements(By.cssSelector("meta[name='" + attribute + "']"));
    }

    public void assertPromoErrorMessage() {
        try {
            Assert.assertTrue(waitForExpectedElement(PROMOCODE_ERROR).isDisplayed());
            if(UrlBuilder.getLocale().equalsIgnoreCase("vusemx")){
                Assert.assertTrue(waitForExpectedElement(PROMOCODE_ERROR).getText().equals(UrlBuilder.getMessage("invalidPromoCodeText.key")));
            }
        } catch (Exception e) {
            if (UrlBuilder.getLocale().equals("vusefr")) {
                waitForExpectedElement(APPLY_DISCOUNT_BUTTON).click();
                Assert.assertTrue(waitForExpectedElement(PROMOCODE_ERROR).isDisplayed());
            }
        }
    }

    public void assertCookieSettingsLinkCTAInFooter() {
        scrollToPageBottom();
        if(UrlBuilder.getLocale().equalsIgnoreCase("vusemx")){
            assertTrue(waitForExpectedElement(COOKIE_SETTINGS_FOOTER_LINK_VUSE_MX, 10).isDisplayed());
            clickByElementByQueryJSExecutor(COOKIE_SETTINGS_FOOTER_LINK_VUSE_MX);
        }
        else {
            assertTrue(waitForExpectedElement(COOKIE_SETTINGS_FOOTER_LINK, 10).isDisplayed());
            clickByElementByQueryJSExecutor(COOKIE_SETTINGS_FOOTER_LINK);
        }
        assertTrue(waitForExpectedElement(OT_COOKIES_MANAGEMENT_POP_UP, 10).isDisplayed());
    }

    public void viewBasketCheck(String linkText) {
        if (linkText.equalsIgnoreCase("viewBasketText.key")) {
            clickOnProceedToCartCTA();
        } else {
            defaultClickLinkByLinkText(linkText);
        }
    }

    public void clickIndividualCookieSettingLink() {
        scrollToPageBottom();
        clickIndexElementByQueryJSExecutor(INDIVIDUAL_COOKIE_SETTING_LINK, 1);
        waitForExpectedElement(INDIVIDUAL_COOKIE_SETTING_POPUP, 10).isDisplayed();
    }

    public void clickUpdateCookieLink() {
        clickByElementByQueryJSExecutor(VUSE_DK_UPDATE_COOKIE_SETTING_LINK);
        waitForExpectedElement(VUSE_DK_REQUIRED_COOKIE_LINK, 10).isDisplayed();
    }

    public void closeVuseAlertIfPresent() {
        if (isElementDisplayedBySeconds(CONTINUE_BUTTON_VUSE_ALERT, 2)) {
            clickByElementByQueryJSExecutor(CONTINUE_BUTTON_VUSE_ALERT);
        }
        waitForAjaxElementNotToBePresent(getWebDriver(),2);
    }

    public void closeFeedbackIfPresent() {
        if (isElementDisplayedBySeconds(GIVE_FEEDBACK_BUTTON_ALERT, 2)) {
            clickByElementByQueryJSExecutor(GIVE_FEEDBACK_BUTTON_ALERT);
        }
    }

    public void clickMyRewardsMenuButton() {
        waitForExpectedElement(MY_REWARD_MENU_OPTION, 10);
        clickByElementByQueryJSExecutor(MY_REWARD_MENU_OPTION);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        getWebDriver().navigate().refresh();
    }
    public void clickInsidersClubMenuButton() {
        try{
            waitForExpectedElement(IT_INSIDERSCLUB_MENU_OPTION,10).click();
        }catch (Exception e){
            waitAndClickByElementByJSExecutor(IT_INSIDERSCLUB_MENU_OPTION,5);
        }
    }
    public void waitForLoaderIconDisappear() {
        try {
            waitForElementToDisappear(LOADER_ICON, 10);
        } catch (Exception e) {
            waitForElementToDisappear(LOADER_ICON, 20);
        }
    }

    public void clickDenyAgeGateCta() {
        waitForExpectedElement(DENY_AGE_GATE_CTA);
        clickByElementByQueryJSExecutor(DENY_AGE_GATE_CTA);
    }

    public void userWaitHomePageToLoad(){
        waitForExpectedElement(HOME_PAGE_TO_LOAD,10);
    }

    public void navigateToAccountDashboard() {
        String store = UrlBuilder.getLocale();
        if (store.equals("pl") || store.equals("vusede")) {
            hoverOnElement((M_PERSONICON_VYPEMX));
            clickByElement(By.linkText(UrlBuilder.getMessage("myAccountLink.key")));
        }
        else if (store.equals("velode") || store.equals("vusefr")) {
            waitForExpectedElement(PERSONICON_VELO_EU_DE, 10);
            clickFirstElementByQueryJSExecutor(PERSONICON_VELO_EU_DE);
        }
    }

    public void clickOnProudctMenu() {
        if (UrlBuilder.isIPhone()){
            waitForItemToBeClickableAndClick(epokHomePage.productSubmenuIphone, 10);}
        else if(UrlBuilder.isSamsungMobile())
            clickByElementByQueryJSExecutor(epokHomePage.M_PRODUCT_SUB_MENU);
        else if (UrlBuilder.isIpad()) {
            try {
                waitForItemToBeClickableAndClick(epokHomePage.productSubmenuIpad, 10);
            } catch (Exception ex) {
                if (!doesURLContain("produkte")) {
                    waitForItemToBeClickableAndClick(epokHomePage.productSubmenuIpad);
                }
            }
        }
        else {
            try {
                waitForItemToBeClickableAndClick(epokHomePage.productSubmenu, 10);
            } catch (Exception ex) {
                if (!doesURLContain("produkte")) {
                    waitForItemToBeClickableAndClick(epokHomePage.productSubmenu);
                }
            }
        }
    }

    public void closeSecurePurchaseAlertIfPresent() {
        if (isElementDisplayedBySeconds(SECURE_PURCHASE_ALERT,2)) {
            waitForExpectedElement(SECURE_PURCHASE_ALERT).click();
        }
    }

    public void addProductForVuseDe(){
        waitForAjaxElementNotToBePresent(getWebDriver(),15);
        waitForExpectedElement(PLP.PRODUCTS_VUSEDE,8);
        List<WebElement> products = webDriver.findElements(PLP.PRODUCTS_VUSEDE);
        WebElement product = products.get(0);
        List<WebElement> strengths = product.findElements(PLP.STRENGTHS_VUSEDE);
        if(UrlBuilder.isMobile()) scrollDownByCoordinator(500);
        waitForExpectedElement(PLP.BUTTON_VUSEDE,10);
        if(getWebDriver().findElements(PLP.STRENGTHS_VUSEDE_DISABLED).size()>0)
            clickUsingJS(strengths.get(1));
        else clickUsingJS(strengths.get(0));
        hoverOnElement(product);
        product.findElement(PLP.BUTTON_VUSEDE).click();
    }

    public void navigateToPLPForHealthWarningPopup(String linkOption){
        if(linkOption.equals("E-Liquids")){
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("LiquidiUrlText.key")), 10);
            clickUsingJS(By.linkText(UrlBuilder.getMessage("LiquidiUrlText.key")));
        }
        else if(linkOption.equals("E-Zigaratten")){
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("Ecigarettes.key")), 10);
            clickUsingJS(By.linkText(UrlBuilder.getMessage("Ecigarettes.key")));
        }
        addProductForVuseDe();
    }

    public void switchToSiteUsingLanguageSelector(String strLanguage) {
        waitForExpectedElement(LANGUAGE_SWITCHER_TRIGGER_CSS,5).click();
        waitForExpectedElement(By.xpath("//header//a[normalize-space()='"+strLanguage+"']")).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public void verifycheckoutbuttonisdisabled() {
        assertFalse(waitForExpectedElement(PROCEED_TO_CHECKOUT_BUTTON, 5).isEnabled());
    }

    public void userclickoneditdetails() {
        waitForExpectedElement(EDIT_DETAILS, 5).click();
    }

    public void assertprofilepicturenotdisplayed() {
        assertFalse(isElementDisplayedBySeconds(PROFILE_PICTURE, 5));
    }

    public void userdeletecustomeraccount() {
        waitForExpectedElement(DELETE_MY_ACCOUNT, 5).click();
        waitForExpectedElement(CONFIRM_CHECKBOX, 5).click();
        waitForExpectedElement(CONFIRM_DELETE, 4).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public void assertlistofstores() {
        assertTrue(waitForExpectedElement(STORES_LIST, 4).isDisplayed());

    }

    public void assertconditionsofsaledisplayed() {
        assertTrue(waitForExpectedElement(CONDITIONS_OF_SALE, 5).isDisplayed());
    }

    public void clickRightOfWithWithdrawalLink() {
        waitForExpectedElement(VUSE_DE_RIGHT_OF_WITHDRAWAL_LINK, 10).isDisplayed();
        clickByElementByQueryJSExecutor(VUSE_DE_RIGHT_OF_WITHDRAWAL_LINK);
    }

    public void clickSatisfactionGuranteedLink() {
        waitForExpectedElement(VUSE_DE_SATISFACTION_GUARANTEED_LINK, 10).isDisplayed();
        clickByElementByQueryJSExecutor(VUSE_DE_SATISFACTION_GUARANTEED_LINK);
    }

    public void assertSubscriptionErrorMessageNotDisplay() {
        assertTrue(invisibilityOfElementLocated(SUBSCRIPTION_ERROR_MESSAGE,10));
    }

    public void clickMixAndMatch() {
        hoverOnElement(SHOP_FLAVORS_LINK);
        assertTrue(isElementDisplayedBySeconds(MIX_AND_MATCH,5));
        waitForItemToBeClickableAndClick(MIX_AND_MATCH,10);
    }

    public void clickAccessibilityLink() {
        assertTrue(isElementDisplayedBySeconds(VUSE_DE_ACCESSIBILITY_LINK, 10));
        clickByElementByQueryJSExecutor(VUSE_DE_ACCESSIBILITY_LINK);
        waitForPage();
    }

    public void clickAboutVuse() {
        assertTrue(isElementDisplayedBySeconds(VUSE_DE_About_VUSE_LINK,10));
        clickByElementByQueryJSExecutor(VUSE_DE_About_VUSE_LINK);
    }

    public void clickPersonalDataLink() {
        switch (UrlBuilder.getLocale()) {
            case "pl":
            case "vuseuk":
                clickByElementByQueryJSExecutor(VUSE_UK_PERSONAL_DATA_LINK);
                break;
            case "vuseza":
            case "glopl":
                clickByElementByQueryJSExecutor(VUSE_ZA_PERSONAL_DATA_LINK);
                break;
            case "vusefr":
                clickIndexElementByQueryJSExecutor(DASHBOARD_EDIT_LINK, 1);
                break;
            default:
                this.clickIndexElementByQueryJSExecutor(VUSE_DE_PERSONAL_DATA_LINK, 1);
        }
    }

    public void userJoinPositiveChange() {
        assertTrue(isElementDisplayedBySeconds(JOIN_POISTIVE_CHANGE, 5));
        clickByElementByQueryJSExecutor(JOIN_POISTIVE_CHANGE);
    }

    public boolean verifyInsidersClubOption() {
        return isElementDisplayedBySeconds(IT_INSIDERSCLUB_MENU_OPTION, 15);
    }

    public boolean verifyInsidersClubHeaderOption() {
        if (UrlBuilder.isMobile())
        {
            clickMobileHamburgerMenu();
            return isElementDisplayedBySeconds(M_IT_INSIDERS_CLUB_HEADER_OPTION, 10);
        }
        else {
            return isElementDisplayedBySeconds(IT_INSIDERS_CLUB_HEADER_OPTION, 10);
        }
    }

    public boolean checkRegisterDevicePopUpIsPresented() {
        return isElementDisplayedBySeconds(IT_DEVICE_REGISTER_POPUP,10);
    }

    public void closePopUP() {
        waitForExpectedElement(IT_POPUP_CLOSE_BUTTON).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        getWebDriver().navigate().refresh();
    }
    public void avalancheEmptyBasket() {
        waitForAjaxElementNotToBePresent(webDriver,3);
        List<WebElement> products;
        waitForExpectedElement(AVALANCHE_BASKET_ICON,5);
        try {
            clickUsingJS(AVALANCHE_BASKET_ICON);
        }catch (Exception e){
            clickUsingJS(AVALANCHE_BASKET_ICON_PL);
        }
        clickUsingJS(AVALANCHE_VIEW_BASKET_BUTTON);
        waitForElementToAppearAndDisappear(LOADER,3,3);
        products = webDriver.findElements(AVALANCHE_PRODUCT);
        while (products.size() > 0) {
            products.get(0).findElement(AVALANCHE_REMOVE_FROM_BASKET_BUTTON).click();
            if(UrlBuilder.getLocale().equals("veloza")){
                waitForElementToAppearAndDisappear(LOADER,3,3);
            clickUsingJS(AVALANCHE_CONFIRM_REMOVE_PRODUCT_VELOZA);
            } else{
                clickUsingJS(AVALANCHE_CONFIRM_REMOVE_PRODUCT);
            }
            waitForElementToAppearAndDisappear(LOADER, 2,3);
            products = webDriver.findElements(AVALANCHE_PRODUCT);
        }
    }

    public void userSelectCombos() {
        hoverOnElement(SHOP_DEVICES_ZA);
        clickByElementByQueryJSExecutor(COMBOS);
    }

    public void assertAgeModuleDropDownMenu() {
        assertTrue(isElementDisplayedBySeconds(By.cssSelector(".date"),5));
        assertTrue(isElementDisplayedBySeconds(By.cssSelector(".month"),5));
        assertTrue(isElementDisplayedBySeconds(By.cssSelector(".year"),5));
    }

    public void assertUnder18AndAbove18ButtonDisplayed() {
        assertTrue(isElementDisplayedBySeconds(By.cssSelector("#btn-entry-age-deny"), 5));
        assertTrue(isElementDisplayedBySeconds(VERIFY_BUTTON_FR, 5));
    }

    public boolean isThirtyDaysLinkPresent() {
        return isElementDisplayedBySeconds(VUSE_DE_THIRTY_DAYS_LINK,10);
    }

    public boolean isToInviteFriendLinkPresent() {
        return isElementDisplayedBySeconds(VUSE_DE_TO_INVITE_FRIENDS_LINK,10);
    }

    public boolean isHeaderDeliveryMessageDisplay() {
        return isElementDisplayedBySeconds(HEADER_DELIVERY_MESSAGE,10);
    }

    public String getHeaderDeliveryMessageText() {
        return waitForExpectedElement(HEADER_DELIVERY_MESSAGE).getText();
    }

    public void selectReferAFriendFromHeader() {
        hoverOnElement(ABOUT_VUSE_ZA);
        isElementDisplayedBySeconds(REFER_A_FRIEND_LINK_ZA,10);
        waitForExpectedElement(REFER_A_FRIEND_LINK_ZA).click();
    }

    public void assertError404PageDisplayed() {
        switch (UrlBuilder.getLocale()) {
            case "vuseza":
                assertTrue(isElementDisplayedBySeconds(ERROR_404_PAGE,5));
                assertTrue(isElementDisplayedBySeconds(ERROR_404_MESSAGE,5));
                break;
            case "vuseit":
                assertTrue(getWebDriver().getPageSource().contains(UrlBuilder.getMessage("404Error.key")));
                break;
            case "vusefr":
                assertTrue(isElementDisplayedBySeconds(ERROR_404_PAGE_FR,5));
                assertTrue(isElementDisplayedBySeconds(ERROR_404_MESSAGE_FR,5));
                break;
            default:
        }
    }

    public void verifyCTAButtonsNavigateToCorrectPage() {
        clickByElementByQueryJSExecutor(LOGIN_ACCOUNT);
        urlContainsText("login.key");
        webDriver.navigate().back();
        waitForPage();
        clickByElementByQueryJSExecutor(CONTINUE_SHOPPING);
        assertFalse(isElementDisplayedBySeconds(ERROR_404_PAGE,5));
        assertFalse(isElementDisplayedBySeconds(ERROR_404_MESSAGE,5));
    }

    public void selectAboutVuseFromHeader() {
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "veloza":
        hoverOnElement(ABOUT_VELO_ZA);
        isElementDisplayedBySeconds(ABOUT_VELO_ZA,10);
        waitForExpectedElement(ABOUT_VELO_ZA).click();
        break;
            default:
                hoverOnElement(ABOUT_VUSE_ZA);
                isElementDisplayedBySeconds(ABOUT_VUSE_LINK_ZA,10);
                waitForExpectedElement(ABOUT_VUSE_LINK_ZA).click();
    }}

    public void verifyCTALinksNavigateToCorrectPage() {
        clickByElementByQueryJSExecutor(FIND_OUT_MORE);
        urlContainsText("vapeQualityUrl.key");
        webDriver.navigate().back();
        waitForPage();
        clickByElementByQueryJSExecutor(EXPLORE_PRODUCT);
        urlContainsText("exploreProduct.key");
    }

    public String getTotalTaxOnLyftSE() {
        waitAndClickByElementByJSExecutor(TOTAL_TAX_LYFTSE, 4);
        return waitForExpectedElement(TOTAL_TAX_DETAIL_LYFTSE, 4).getText();
    }

    public String getProductPrice() {
        waitForLoaderIconDisappear();
        waitForExpectedElement(MINI_CART_PRICE,5);
        return waitForExpectedElement(MINI_CART_PRICE).getText();
    }

    public void gotoMainSubscriptionsPageAndTakeEyesScreenshot() {
        final String pageName = "Main Subscriptions Page";
        switch (UrlBuilder.getLocale()) {
            case "pl":
                clickIndexElementByQueryJSExecutor(SUBS_LINK_GLO_PL, 0);
                eyesCheckFullyPage(pageName);
                break;
            case "vusefr":
                clickIndexElementByQueryJSExecutor(SUBS_LINK_VUSE_FR, 0);
                eyesCheckFullyPage(pageName);
                break;
            case "lyftse":
                waitAndClickByElementByJSExecutor(SUBS_LINK_LYFT_SE, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseco":
                waitForExpectedElement(SUBS_LINK_VUSE_CO).click();
                eyesCheckFullyPage(pageName);
                break;
            case "vuseuk":
                clickIndexElementByQueryJSExecutor(SUBS_LINK_VUSE_UK, 0);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToStoreLocatorPageAndTakeEyesScreenShot() {
        final String pageName = "Store Locator Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                waitAndClickByElementByJSExecutor(STORE_LOCATOR_LYFT_SE, 3);
                waitForExpectedElement(StoreLocatorPage.STORE_SEARCH_RESULTS);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseco":
            case "vuseza":
            case "vusefr":
            case "vuseit":
            case "kz":
            case "pl":
                if (!Props.USE_EYES_GRID) {
                    clickStoreLocatorIcon();
                    eyesCheckFullyPage(pageName);
                }
                break;
            case "vuseuk":
                clickStoreLocatorIcon();
                eyesCheckFullyPage(pageName);
                break;
            case "vusede":
                if (!Props.USE_EYES_GRID) {
                    UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("storeLocatorURL.key"));
                    eyesCheckFullyPage(pageName);
                }
                break;
            case "velobe":
                waitAndClickByElementByJSExecutor(STORE_LOCATOR_VELO_BE, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "it":
                waitAndClickByElementByJSExecutor(STORE_LOCALTOR_GLO_IT, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "glode":
                waitAndClickByElementByJSExecutor(LOGO_VUSE_ZA,3);
                waitAndClickByElementByJSExecutor(STORE_LOCALTOR_GLO_DE, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "velopl":
                waitAndClickByElementByJSExecutor(STORE_LOCATOR_VELO_PL, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToShopDevicePlpPageAndTakeEyesScreenShot() {
        final String pageName = "Shop Device Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                waitAndClickByElementByJSExecutor(SHOP_DEVICES_FR, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseza":
                waitAndClickByElementByJSExecutor(SHOP_DEVICES_VUSE_ZA, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseuk":
                waitAndClickByElementByJSExecutor(SHOP_DEVICES_UK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToTopMenuFlyoutPageAndTakeEyesScreenShot() {
        final String pageName = "Top Menu Flyout Page(Only For Desktop)";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                hoverOnElement(CIGARETTES_LINK_FR);
                eyes.check(pageName, Target.window().fully());
                break;
            case "vuseuk":
                clickIndexElementByQueryJSExecutor(SUBS_LINK_VUSE_UK, 0);
                hoverOnElement(SHOP_DEVICES_UK);
                if (Props.USE_EYES_GRID) {
                    eyes.check(pageName, Target.window().fully().beforeRenderScreenshotHook(
                            "if (document.querySelector('#html-body').scrollWidth < " + WebDriverHelper.DESKTOP_VIEW_MIN_WIDTH + ")"
                                    + "{ document.querySelector('div.more-menu-container').style.display='none' }"));
                } else {
                    eyes.check(pageName, Target.window().fully());
                }
                break;
        }
    }

    public void clickOnSticksMenu(){
        waitAndClickByElementByJSExecutor(NEO_STICKS_MENU,10);
    }

    public Boolean assertPopUpBannerContentCorrect() {
        Boolean flag=false;
        List<String> expectedContent=new ArrayList<>(3);
        expectedContent.add(UrlBuilder.getMessage("bannerMessage.key"));
        expectedContent.add(UrlBuilder.getMessage("bannerContentFirst.key"));
        expectedContent.add(UrlBuilder.getMessage("bannerContentSecond.key"));
        List<WebElement> contentElements=getWebDriver().findElements(RDB_BANNER_CONTENT);
        if(contentElements!=null){
            for(int i=0;i<expectedContent.size();i++){
                if(contentElements.get(i).getText().contains(expectedContent.get(i)))
                    flag=true;
                else
                    flag=false;
            }
        }
        return flag;
    }

    public void goToNewsletterOverlayPageAndTakeEyesScreenshot() {
        final String pageName = "Newsletter Overlay Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
            case "vuseza":
            case "vuseuk":
                waitAndClickByElementByJSExecutor(SUBSCRIBE_NEWSLETTER_BUTTON, 3);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "it":
                waitAndClickByElementByJSExecutor(SUBSCRIBE_NEWSLETTER_BUTTON_GLO_IT, 3);
                scrollToShowEntirePage();
                waitAndClickByElementByJSExecutor(NEWSLETTER_SUBMIT_GLO_IT, 3);
                eyes.check(pageName, Target.window().fully());
                clickIndexElementByQueryJSExecutor(NEWSLETTER_CLOSE_GLO_IT, 0);
                break;
        }
    }

    public void goToPlpStorePageAndTakeEyesScreenshot() {
        final String pageName = "Plp Store Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "pl":
                waitAndClickByElementByJSExecutor(STORE_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToPlpAccessoriesPageAndTakeEyesScreenShot() {
        final String pageName = "Plp Accessories Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "pl":
                waitAndClickByElementByJSExecutor(ACCESSORIES_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void clickOnInstagramIconFooter() {
        waitAndClickByElementByJSExecutor(INSTAGRAM_ICON, 10);
    }

    public void goToPlpTobaccoPageAndTakeEyesScreenshot() {
        final String pageName = "Plp Tobacco Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "pl":
                waitAndClickByElementByJSExecutor(TOBACCO_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToContactUsPageAndTakeEyesScreenshot() {
        if (!Props.EYES_ON) {
            return;
        }
        final String pageName = "Contact Us Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                clickIndexElementByQueryJSExecutor(CONTACT_LINK_VUSE_ZA, 1);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseit":
            case "vusefr":
                clickIndexElementByQueryJSExecutor(CONTACT_LINK_VUSE_FR, 1);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseco":
                waitForExpectedElement(CONTACT_LINK_VUSE_CO).click();
                eyesCheckFullyPage(pageName);
                break;
            case "pl":
                waitAndClickByElementByJSExecutor(CONTACT_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "glode":
                waitAndClickByElementByJSExecutor(GLO_ICON_GLO_DE, 3);
                waitAndClickByElementByJSExecutor(CONTACT_LINK_GLO_DE, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "kz":
                waitAndClickByElementByJSExecutor(CONTACT_LINK_GLO_KZ, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseuk":
                clickIndexElementByQueryJSExecutor(CONTACT_LINK_VUSE_UK, 1);
                eyesCheckFullyPage(pageName);
                break;
            case "velobe":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("contactUsURL.key"));
                eyesCheckFullyPage(pageName);
                break;
            case "velopl":
                waitAndClickByElementByJSExecutor(VELO_IMG_VELO_PL, 3);
                waitAndClickByElementByJSExecutor(CONTACT_LINK_VELO_PL, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToPasswordResetPageAndTakeEyesScreenshot() {
        final String pageName = "Password Reset Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
            case "lyftse":
            case "vuseco":
            case "vusede":
            case "vusefr":
            case "pl":
            case "it":
            case "glode":
            case "vuseit":
            case "vuseuk":
                waitAndClickByElementByJSExecutor(LOGOUT_LINK_GLO_PL, 3);
                scrollToShowEntirePage();
                navigateToSignInPage();
                waitAndClickByElementByJSExecutor(FORGET_PASSWORD_LINK, 5);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                navigateToSignInPage();
                loginPage.login(scenarioContext.getContext(Context.EMAIL_ID).toString(), scenarioContext.getContext(Context.PASSWORD).toString());
                break;
            case "kz":
                waitAndClickByElementByJSExecutor(LOGOUT_LINK_GLO_PL, 3);
                scrollToShowEntirePage();
                navigateToSignInPage();
                waitAndClickByElementByJSExecutor(FORGET_PASSWORD_LINK, 5);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                UrlBuilder.navigateToBATHomePage();
                loginPage.login(scenarioContext.getContext(Context.EMAIL_ID).toString(), scenarioContext.getContext(Context.PASSWORD).toString());
                waitForExpectedElement(RDB_POPUP_BANNER,4).click();
                break;
        }
    }

    public void verifyUserIsRedirectedTo(String urlContains) {
        waitForLoaderToDisapear();
        if ((UrlBuilder.getLocale().equals("vuseza") && urlContains.equalsIgnoreCase(UrlBuilder.getMessage("newNewsleterPage.key"))))
        {
            LOG.info("Successfully redirected to URL.");
        }
    }

    public void goToMenuGuidedSellPageAndTakeEyesScreenshot() {
        final String pageName = "Menu Guided Sell Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                waitAndClickByElementByJSExecutor(MENU_GUIDED_SELL_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToSearchFlyoutPageAndTakeEyesScreenShot() {
        final String pageName = "Search Flyout Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                waitAndClickByElementByJSExecutor(SEARCH_ICON_LYFT_SE, 3);
                enterDataAndWait(SEARCH_TEXT_LYFT_SE, UrlBuilder.getMessage("searchFlyoutTerm.key"));
                waitForElementToAppearAndDisappear(LOADER, 5, 10);
                eyes.check(pageName, Target.window().fully());
                waitAndClickByElementByJSExecutor(SEARCH_CLOSE_LYFT_SE, 3);
                break;
            case "vusede":
            case "vuseza":
            case "vusefr":
            case "glode":
            case "vuseco":
            case "vuseit":
            case "vuseuk":
            case "kz":
            case "pl":
                waitAndClickByElementByJSExecutor(SEARCH_TEXT_VUSE_FR, 3);
                enterDataAndWait(SEARCH_TEXT_VUSE_FR, UrlBuilder.getMessage("searchFlyoutTerm.key"));
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                waitAndClickByElementByJSExecutor(SEARCH_ICON_VUSE_FR, 3);
                waitForPage();
                break;
        }
    }

    public void goToMyAccountAddNewAddressPageAndTakeEyesScreenShot() {
        final String pageName = "My Account Add New Address Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                waitAndClickByElementByJSExecutor(ADDRESSBOOK_LINK, 3);
                waitForPage();
                waitAndClickByElementByJSExecutor(ADD_NEW_ADDRESS_BUTTON, 3);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "vusefr":
            case "vuseuk":
            case "pl":
                switchBetweenWindowTabs(0);
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("addressBookUrl.key"));
                waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON,4).click();
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(CLOSE_ADD_NEW_ADDRESS_BUTTON_GLO_PL,3);
                break;
            case "velopl":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("VeloAddressBookUrl.key"));
                waitForExpectedElement(ADD_NEW_ADDRESS_BUTTON,4).click();
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(CLOSE_ADD_NEW_ADDRESS_BUTTON_GLO_PL,3);
                break;
        }
    }

    public void goToCigarettesPlpPageAndTakeEyesScreenshot() {
        final String pageName = "Cigarettes Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitForExpectedElement(CIGARETTES_LINK_VUSE_DE).click();
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToCigarettesHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Cigarettes Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                hoverOnElement(CIGARETTES_LINK_VUSE_DE);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToLiquidsHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Liquids Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                hoverOnElement(LIQUIDS_LINK_VUSE_DE);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToVuseReloadPageAndTakeEyesScreenshot() {
        final String pageName = "Vuse Reload Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitForExpectedElement(VUSE_RELOAD_LINK_VUSE_DE).click();
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToEquipmentPageAndTakeEyesScreenshot() {
        final String pageName = "Equipment Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitForExpectedElement(EQUIPMENT_LINK_VUSE_DE).click();
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToKnowYourVapePageAndTakeEyesScreenshot() {
        final String pageName = "Know Your Vape Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitForExpectedElement(KNOW_YOUR_VAPE_LINK_VUSE_DE).click();
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToNicotineFreePageAndTakeEyesScreenshot() {
        final String pageName = "Nicotine Free Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitForExpectedElement(NICOTINE_FREE_LINK_VUSE_DE).click();
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToAboutUsPageAndTakeEyesScreenShot() {
        final String pageName = "About Us Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                waitAndClickByElementByJSExecutor(ABOUT_US_LINK, 3);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "kz":
                waitAndClickByElementByJSExecutor(ABOUT_US_LINK_GLO_KZ, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToShopLyftPlpPageAndTakeEyesScreenShot() {
        final String pageName = "Shop Lyft Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                waitAndClickByElementByJSExecutor(SHOP_LYFT_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLyft2VeloMainMenuAndTakeEyesScreenShot() {
        final String pageName = "Lyft2Velo Main Menu Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                waitAndClickByElementByJSExecutor(LYFT2VELO_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public WebElement getMenuElement(Integer number) {
        String subMenu = "#account-nav > ul > li:nth-child(" + number + ")>a";
        return getWebDriver().findElement(By.cssSelector(subMenu));
    }

    public Integer getSubMenuSize() {
        return getWebDriver().findElements(LEFT_WINDOW_MENUS).size();
    }

    public void goToBasketPageAndTakeEyesScreenShot() {
        if (!Props.EYES_ON) {
            return;
        }
        final String pageName = "Basket Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
            case "lyftse":
                eyesCheckFullyPage(pageName);
                break;
            case "it":
                clickUsingJS(BASKET_ICON_GLO_IT);
                clickUsingJS(VIEW_BASKET_LINK_GLO_IT);
                eyesCheckFullyPage(pageName);
                waitForExpectedElement(PROCEED_CHECKOUT_LINK_GLO_IT).click();
                waitForPage();
                scrollToShowEntirePage();
                break;
        }
    }

    public void clickOnLinkAndAssertExpectedUrl(DataTable dataTable){
        List<List<String>> lstURLs = dataTable.raw();
        for (List<String> lstURL : lstURLs) {
            if (UrlBuilder.getLocale().equals("it")){
               hoverOnElement(DISCOVER_GLO_HEADER);
            }

            else if (!UrlBuilder.getLocale().equals("velopl")) {
                hoverOnElement(By.linkText(UrlBuilder.getMessage("scopriVuse.key")));
                waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage(lstURL.get(0)))).click();
                assertTrue(getCurrentUrl().contains(UrlBuilder.getMessage(lstURL.get(1))));
            if (UrlBuilder.getLocale().equals("it") && UrlBuilder.getEnv().equals("uat1")) {
                assertTrue(getCurrentUrl().contains("uat1"));
            }}
            else {
                jsScrollElementInCenter(waitForExpectedElement(By.linkText(UrlBuilder.getMessage(lstURL.get(0)).toUpperCase())));
                clickUsingJS(By.linkText(UrlBuilder.getMessage(lstURL.get(0)).toUpperCase()));
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                assertTrue(getCurrentUrl().contains(UrlBuilder.getMessage(lstURL.get(1))));
                assertTrue("**** ERROR - following was expected in URL : " +(UrlBuilder.getMessage(lstURL.get(1)) + " but full URL was : \n" + getWebDriver().getCurrentUrl()), getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(lstURL.get(1))));
            }
        }
    }

    public void goToAboutVusePageAndTakeEyesScreenshot() {
        final String pageName = "About Vuse Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                waitForExpectedElement(ABOUT_VUSE_LINK_VUSE_ZA).click();
                eyes.check(pageName, Target.window().fully());
                break;
            case "vusede":
                waitForExpectedElement(ABOUT_VUSE_LINK_VUSE_DE).click();
                eyes.check(pageName, Target.window().fully());
                break;
            case "vuseco":
                waitForExpectedElement(ABOUT_VUSE_LINK_VUSE_CO).click();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToAboutVuseHoverPageAndTakeEyesScreenShot() {
        final String pageName = "About Vuse Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                hoverOnElement(ABOUT_VUSE_LINK_VUSE_ZA);
                eyes.check(pageName, Target.window().fully());
                break;
            case "vusede":
                hoverOnElement(ABOUT_VUSE_LINK_VUSE_DE);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToAboutVuseFooterPageAndTakeEyesScreenshot() {
        final String pageName = "About Vuse (Footer) Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("AboutVuseFooterURL.key"));
                eyes.check(pageName, Target.window().fully());
                break;
            case "vusede":
                waitForExpectedElement(ABOUT_VUSE_FOOTER_LINK_VUSE_DE).click();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToShopDeviceHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Shop Device Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                hoverOnElement(SHOP_DEVICES_VUSE_ZA);
                eyesCheckFullyPage(pageName);
                break;
        }

    }

    public void goToPromosHoverPageAndTakeEyesScreenShot() {
        final String pageName = "Promos Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                waitAndClickByElementByJSExecutor(LOGO_VUSE_ZA,3);
                waitForPage();
                hoverOnElement(PROMOS_LINK_VUSE_ZA);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToAccountHoverDropdownLoggedInUserPageAndTakeEyesScreenShot() {
        final String pageName = "Account Hover Dropdown (Logged In User) Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
            case "vuseza":
            case "vuseco":
                waitAndClickByElementByJSExecutor(LOGO_VUSE_ZA,3);
                this.clickAccountLink();
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(LOGO_VUSE_ZA,3);
                break;
            case "vuseit":
                UrlBuilder.navigateToBATHomePage();
                waitForExpectedElement(PEOPLE_ICON_VUSE_IT,3).click();
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(LOGO_VUSE_ZA,3);
                break;
        }
    }

    public void usersClicksOnTheLinkByTextFromHereToHelpMenu(String linkText) {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText)), 10);
        if (UrlBuilder.isMobile()) {
            scrollToPageBottom();
            waitForExpectedElement(HERE_TO_HELP);
            waitForItemToBeClickableAndClick(HERE_TO_HELP);
            clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkText)));
            waitForPage();
        }
    }

    public void goToEpodStarterPackPdpPageAndTakeEyesScreenshot() {
        final String pageName = "Epod Starter Pack Pdp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseco":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("EPodStarterPackPdpUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
            case "vuseza":
                enterDataAndWait(SEARCH_TEXT_VUSE_FR, UrlBuilder.getMessage("searchEpodStarterPackTerm.key"));
                waitAndClickByElementByJSExecutor(SEARCH_ICON_VUSE_FR, 3);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToBlogPageAndTakeEyesScreenShot() {
        final String pageName = "Blog Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "it":
                waitAndClickByElementByJSExecutor(BLOG_LINK_GLO_IT, 3);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseza":
                waitForExpectedElement(BLOG_LINK_VUSE_ZA).click();
                eyesCheckFullyPage(pageName);
                break;
            case "vuseco":
                waitForExpectedElement(BLOG_LINK_VUSE_CO).click();
                eyesCheckFullyPage(pageName);
                break;
            case "vusede":
                waitForExpectedElement(BLOG_LINK_VUSE_DE).click();
                eyesCheckFullyPage(pageName);
                break;
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("blogUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("VuseBlogUrl.key"));
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "pl":
                getWebDriver().navigate().to(UrlBuilder.url);
                waitAndClickByElementByJSExecutor(BLOG_VIEW_ALL_LINK_GLO_PL, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToVapersPageAndTakeEyesScreenshot() {
        final String pageName = "Vapers Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseco":
                waitForExpectedElement(VAPERS_LINK_VUSE_CO).click();
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToVapersHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Vapers Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseco":
                hoverOnElement(VAPERS_LINK_VUSE_CO);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToPodsHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Pods Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseco":
                hoverOnElement(PODS_LINK_VUSE_CO);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goTo404PageAndTakeEyesScreenshot() {
        final String pageName = "404 Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vusede":
            case "pl":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("404PageUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
            case "vuseza":
            case "vuseco":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("404PageUrl.key"));
                eyes.check(pageName, Target.window().fully());
                UrlBuilder.navigateToBATHomePage();
                break;
        }
    }

    public void goToGuestCheckoutLoginPageAndTakeEyesScreenshot() {
        final String pageName = "Guest Checkout Login Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vusede":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("GuestCheckoutLoginPageUrl.key"));
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToGuestCheckoutRegisterPageAndTakeEyesScreenshot() {
        final String pageName = "Guest Checkout Register Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vusede":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("GuestCheckoutRegisterPageUrl.key"));
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToNewsletterSubscriptionPopupAndTakeEyesScreenshot() {
        final String pageName = "Newsletter Subscription Popup";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vuseco":
                scrollToPageBottom();
                waitAndClickByElementByJSExecutor(NEWSLETTER_SUB_FROM_FOOTER_BUTTON_VUSE_CO, 3);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
            case "vusede":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("NewsletterSubscriptionPopupUrl.key"));
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToSubscribeToInsiderClubPageAndTakeEyesScreenShot() {
        final String pageName = "Subscribe To Insider Club Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "it":
                clickPersonIcon();
                waitAndClickByElementByJSExecutor(LOGOUT_LINK_GLO_PL, 3);
                scrollToShowEntirePage();
                waitAndClickByElementByJSExecutor(INSIDER_CLUB_LINK, 5);
                eyesCheckFullyPage(pageName);
                navigateToSignInPage();
                loginPage.login(scenarioContext.getContext(Context.EMAIL_ID).toString(), scenarioContext.getContext(Context.PASSWORD).toString());
                break;
        }
    }

    public void goToTobaccoHeaterPlpPageAndTakeEyesScreenshot() {
        final String pageName = "Tobacco Heater Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
                waitAndClickByElementByJSExecutor(TOBACCO_HEATER_LINK, 5);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToGloAccessoriesPlpPageAndTakeEyesScreenshot() {
        final String pageName = "Glo Accessories Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
                waitAndClickByElementByJSExecutor(GLO_EQUIPMENT_LINK, 5);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToSearchResultPageAndTakeEyesScreenshot() {
        final String pageName = "Search Result Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
            case "kz":
                waitAndClickByElementByJSExecutor(GLO_ICON_GLO_DE, 3);
                enterDataAndWait(SEARCH_TEXT_VUSE_FR, UrlBuilder.getMessage("searchFlyoutTerm.key"));
                waitForPage();
                waitAndClickByElementByJSExecutor(SEARCH_ICON_VUSE_FR, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToDeviceTrialPageAndTakeEyesScreenshot() {
        final String pageName = "Device Trial Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
                waitAndClickByElementByJSExecutor(DEVICE_TRIAL_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToNewsletterSubscriptionFromFooterPageAndTakeEyesScreenshot() {
        final String pageName = "Newsletter Subscription From Footer Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "glode":
                scrollToPageBottom();
                waitAndClickByElementByJSExecutor(NEWSLETTER_SUB_FROM_FOOTER_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLiquidHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Liquidi Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("liquidPlpUrl.key"));
                hoverOnElement(LIQUIDI_LINK);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToSigaretteElectronicHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Sigarette Electronic Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                hoverOnElement(SIGARETTE_ELETTRONICHE_LINK);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToSigaretteElectronicPlpPageAndTakeEyesScreenshot() {
        final String pageName = "Sigarette Electronic Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                waitAndClickByElementByJSExecutor(SIGARETTE_ELETTRONICHE_LINK,3);
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToAccountHoverDropdownGuestUserPageAndTakeEyesScreenShot() {
        final String pageName = "Account Hover Dropdown (Guest User) Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vuseco":
            case "vuseza":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getUrl());
                waitAndClickByElementByJSExecutor(LOGO_VUSE_ZA,3);
                this.clickAccountLink();
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(LOGO_VUSE_ZA,3);
                break;
        }
    }


    public void goToVuseEpodPdpPagePageAndTakeEyesScreenshot() {
        final String pageName = "Vuse Epod Pdp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("VuseEpodPdpUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToAbbonamentoPageAndTakeEyesScreenshot() {
        final String pageName = "Abbonamento Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("AbbonamentoPageUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToPersonalPresentationPageAndTakeEyesScreenshot() {
        final String pageName = "Personal Presentation Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "kz":
                waitAndClickByElementByJSExecutor(PERSONAL_PRESENTATION_LINK,3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToPlp2HeatedGloSticksPageAndTakeEyesScreenshot() {
        final String pageName = "Plp2 Heated Glo Sticks Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "kz":
                waitAndClickByElementByJSExecutor(HEATED_STICKS_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToAboutDevicesPageAndTakeEyesScreenshot() {
        final String pageName = "About Devices Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "kz":
                waitAndClickByElementByJSExecutor(ABOUT_DEVICES_LINK, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToBasketOnCheckoutPageAndTakeEyesScreenshot() {
        if (Props.EYES_ON && EyesCheckpoints.BASKET_PAGE.isSwitchOn()) {
            final String pageName = EyesCheckpoints.BASKET_PAGE.getName();
            switch (UrlBuilder.getLocale()) {
                case "kz":
                    eyes.check(pageName, Target.window().fully());
                    break;
            }
        }
    }

    public void userClickOnTheLink(String link){
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        jsScrollElementInCenter(waitForExpectedElement(By.linkText(UrlBuilder.getMessage(link))));
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(link))).click();
    }

    public void clickKnowYourVapeLink() {
        waitAndClickByElementByJSExecutor(KNOW_YOUR_VAPE_LINK, 3);
    }

    public void goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(String pageName, By subPageLink) {
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseuk":
                clickKnowYourVapeLink();
                waitForExpectedElement(subPageLink, 3);
                clickIndexElementByQueryJSExecutor(subPageLink, 0);
                eyesCheckFullyPage(pageName);
                break;
            case "vusefr":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("allAboutVapingUrl.key"));
                waitForPage();
                waitForExpectedElement(subPageLink, 3);
                clickIndexElementByQueryJSExecutor(subPageLink, 0);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToTransparencyAboutVapingPageAndTakeEyesScreenshot() {
        final String pageName = "Transparency About Vaping Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseuk":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, TRANSPARENCY_LINK);
                break;
            case "vusefr":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, TRANSPARENCY_LINK_VUSE_FR);
                break;
        }
    }

    public void goToResponsibilityBeyondProductPageAndTakeEyesScreenshot() {
        final String pageName = "Responsibility Beyond Product Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseuk":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, RESPONSIBILITY_LINK);
                break;
            case "vusefr":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, RESPONSIBILITY_LINK_VUSE_FR);
                break;
        }
    }

    public void goToUnpackingVapingForYouPageAndTakeEyesScreenshot() {
        final String pageName = "Unpacking Vaping For You Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseuk":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, VAPING_UNPACKED_LINK);
                break;
            case "vusefr":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, VAPING_IN_DETAIL_LINK_VUSE_FR);
                break;
        }
    }

    public void goToScienceFocusPageAndTakeEyesScreenshot() {
        final String pageName = "Science Focus Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseuk":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, SCIENCE_LINK);
                break;
            case "vusefr":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, SCIENCE_LINK_VUSE_FR);
                break;
        }
    }

    public void goToTalkingVapingWithYouPageAndTakeEyesScreenshot() {
        final String pageName = "Talking Vaping With You Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseuk":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, TALK_VAPING_LINK);
                break;
            case "vusefr":
                goToSubPageInKnowYourVapeMenuAndTakeEyesScreenshot(pageName, TALK_VAPING_LINK_VUSE_FR);
                break;
        }
    }

    public void clickTaekaHeaderLink(){
        waitForExpectedElement(TAEKA_HEADER).click();
    }

    public void goToCookieSettingOverlayPageAndTakeEyesScreenshot() {
        final String pageName = "Cookie Setting Overlay Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "pl":
            case "vusefr":
            case "vuseuk":
                clickIndexElementByQueryJSExecutor(COOKIE_SETTING_LINK, 1);
                eyesCheckFullyPage(pageName);
                clickIndexElementByQueryJSExecutor(OT_COOKIES_CLOSE_BUTTON, 1);
                break;
            case "lyftse":
                waitAndClickByElementByJSExecutor(COOKIE_SETTING_LINK_LYFT_SE,3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToCookieNoticePageAndTakeEyesScreenshot() {
        final String pageName = "Cookie Notice Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("cookieNoticeUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
            case "pl":
                waitAndClickByElementByJSExecutor(COOKIE_NOTICE_LINK_GLO_PL,3);
                eyesCheckFullyPage(pageName);
                break;
            case "vuseuk":
                clickIndexElementByQueryJSExecutor(COOKIE_NOTICE_LINK, 1);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToCustomizePageAndTakeEyesScreenshot() {
        final String pageName = "Customize Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseuk":
                waitAndClickByElementByJSExecutor(CUSTMOIZE_LINK,3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToAgeGateRestrictionDisclaimerGoogleRedirectionPageAndTakeEyesScreenshot() {
        if (Props.EYES_ON && EyesCheckpoints.AGE_GATE_RESTRICTION.isSwitchOn()) {
            final String pageName = "Age Gate Restriction Disclaimer Google Redirection Page";
            switch (UrlBuilder.getLocale().toLowerCase()) {
                case "vuseuk":
                    waitAndClickByElementByJSExecutor(DENY_AGE_GATE_CTA, 3);
                    waitForURLToContain(UrlBuilder.getMessage("googleUrl.key"));
                    eyesCheckFullyPage(pageName);
                    getWebDriver().navigate().back();
                    break;
            }
        }
    }

    public boolean isCorrectEnv() {
        return webDriver.getCurrentUrl().contains(UrlBuilder.getEnv());
    }

    public void clickDeviceTrailCTA(){
        scrollToElement(DEVICE_TRAIL_CTA_GLODE);
        waitAndClickByElementByJSExecutor(DEVICE_TRAIL_CTA_GLODE,5);
    }

    public void goToAllAboutVapingPageAndTakeEyesScreenshot() {
        final String pageName = "All About Vaping Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                waitAndClickByElementByJSExecutor(ALL_ABOUT_VAPING_LINK_VUSE_FR,3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToFlyoutMenuLiquidPageAndTakeEyesScreenshot() {
        final String pageName = "Flyout Menu Liquid Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                hoverOnElement(ELIQUID_LINK_VUSE_FR);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToFlyoutMenuAProposPageAndTakeEyesScreenshot() {
        final String pageName = "Flyout Menu A Propos Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                hoverOnElement(A_PROPOS_LINK_VUSE_FR);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void goToFlyoutMenuAllAboutVapingPageAndTakeEyesScreenshot() {
        final String pageName = "Flyout Menu All About Vaping Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                hoverOnElement(ALL_ABOUT_VAPING_LINK_VUSE_FR);
                waitForPage();
                eyes.check(pageName, Target.window().fully());
                break;
        }
    }

    public void clickSlickSlider() {
        waitForExpectedElement(SLICK_SLIDER_GLO_IT).click();
    }

    public void goToEpodPlpPageAndTakeEyesScreenshot() {
        final String pageName = "Epod Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                waitAndClickByElementByJSExecutor(EPOD_PLP_LINK_VUSE_FR,3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToFlavorPlpSubscriptionViaIIconPageAndTakeEyesScreenshot() {
        final String pageName = "Flavor Plp Subscription Via I Icon Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("plpUrl.key"));
                waitAndClickByElementByJSExecutor(I_ICON_VUSE_FR,3);
                eyesCheckFullyPage(pageName);
                clickIndexElementByQueryJSExecutor(CLOSE_SUBSCRIPTION_VUSE_FR,4);
                break;
        }
    }

    public void clickOnInstagramIconatstayConnected() {
        waitForItemToBeClickableAndClick(INSTAGRAM_STAYCONNECTED,5);
    }
    public void clickOnFacebookIconatstayConnected() {
        waitForItemToBeClickableAndClick(FACEBOOK_STAYCONNECTED,5);
    }
    public void clickOnTwitterIconatstayConnected() {
        waitForItemToBeClickableAndClick(TWITTER_STAYCONNECTED, 5);
    }
    public void goToCookiePrivacyPolicyPageAndTakeEyesScreenshot() {
        final String pageName = "Cookie Privacy Policy Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("privacyPolicyUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
            case "pl":
                waitAndClickByElementByJSExecutor(COOKIE_POLICY_LINK_GLO_PL,3);
                eyesCheckFullyPage(pageName);
                clickUsingJS(GLO_ICON_GLO_DE);
                break;
        }
    }

    public void goToCookiePolicyPageAndTakeEyesScreenshot() {
        final String pageName = "Cookie Policy Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("cookiePolicyUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToEpodPlpPaymentDeferPageAndTakeEyesScreenshot() {
        final String pageName1 = "Epod Plp Payment Defer Page I";
        final String pageName2 = "Epod Plp Payment Defer Page II";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusefr":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("cigaretteUrl.key"));
                scrollToShowEntirePage();
                hoverOnElement(FIRST_DEFER_PAYMENT_PRODUCT_LINK);
                waitForExpectedElement(DEFERED_PAYMENT_LINK_VUSE_FR,3).click();
                eyes.check(pageName1, Target.window().fully());
                waitForExpectedElement(COMMENCER_LINK_VUSE_FR,3).click();
                waitForExpectedElement(NO_LINK_VUSE_FR);
                eyes.check(pageName2, Target.window().fully());
                waitForExpectedElement(NO_LINK_VUSE_FR,3).click();
                break;
        }
    }
    public void goToLabProductsPageAndTakeEyesScreenshot() {
        final String pageName = "Lab Products Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labProductsUrl.key"));
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(TOP_LYFT_LINK,3);
                break;
        }
    }

    public void goToLyftLabHomePageAndTakeEyesScreenshot() {
        final String pageName = "Lyft Lab Home Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("lyftLabHomeUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLyftPdpPageAndTakeEyesScreenshot() {
        final String pageName = "Lyft Pdp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("lyftPdpUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLabInsightsPageAndTakeEyesScreenshot() {
        final String pageName = "Lab Insights Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labInsightsUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLabCollections01PageAndTakeEyesScreenshot() {
        final String pageName = "Lab Collections 01 Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labCollections01Url.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void assertErrorMessageAfterSubmittingNewsletterWithoutDetails() {
        String actualErrorMessage = null;
        String expectedErrorMessage;
        By ERROR =NEWSLETTER_NAME_ERROR ;
        clickOnNewsletterButton();
        waitForItemToBeClickableAndClick(NEWSLETTER_REGISTER,10);
        expectedErrorMessage = UrlBuilder.getMessage("expectedRequiredFieldErrorMessage.key");
        actualErrorMessage = waitForExpectedElement(ERROR, 5).getText();
        assertThat(expectedErrorMessage.equals(actualErrorMessage))
                .as("ERROR checkRegistrationError: expected error message was " + expectedErrorMessage + " but I got " + actualErrorMessage + " for field ").isTrue();

    }

    public void assertErrorMessageForIncorrectFormat() {
        String actualErrorMessage = null;
        String expectedErrorMessage;
        By ERROR = NEWSLETTER_EMAIL_ERROR;
        clickOnNewsletterButton();
        enterText(NEWSLETTER_EMAIL_TEXT, "test@test");
        waitForItemToBeClickableAndClick(NEWSLETTER_REGISTER, 10);
        expectedErrorMessage = UrlBuilder.getMessage("loginInvalidEmailErrorMsg.key");
        actualErrorMessage = waitForExpectedElement(ERROR, 5).getText();
        assertThat(expectedErrorMessage.equals(actualErrorMessage))
                .as("ERROR checkRegistrationError: expected error message was " + expectedErrorMessage + " but I got " + actualErrorMessage + " for field ").isTrue();

    }

    public void goToLabCollections02PageAndTakeEyesScreenshot() {
        final String pageName = "Lab Collections 02 Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labCollections02Url.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLabCollections03PageAndTakeEyesScreenshot() {
        final String pageName = "Lab Collections 03 Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labCollections03Url.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLabCollections04PageAndTakeEyesScreenshot() {
        final String pageName = "Lab Collections 04 Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labCollections04Url.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLabCollections05PageAndTakeEyesScreenshot() {
        final String pageName = "Lab Collections 05 Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labCollections05Url.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToGuestUserLyftLabActiveCollectionAddToCartPageAndTakeEyesScreenshot() throws Throwable {
        final String pageName = "Guest User Lyft Lab Active Collection Add To Cart Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                LOG.info("Start to go to lab collection07 page");
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labCollections07Url.key"));
                plp.userSelectStrengthForSixPackLabProduct("Regular.key");
                clickOnBasketIcon();
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(DELETE_CART_LINK_LYFT_SE,3);
                waitAndClickByElementByJSExecutor(CONFIRM_YES_LINK_LYFT_SE,3);
                break;
        }
    }

    public void goToLabCollaboratorPageAndTakeEyesScreenshot() {
        final String pageName = "Lab Collaborator Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("labCollaboratorUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void clickOnFacebookIconOnFooter() {
        try {
            switch (UrlBuilder.getLocale()) {
                case "lyftse":
                    waitForExpectedElement(FACEBOOK_ICON_ON_FOOTER_LYFT, 5).click();
                    break;
                default:
            }
        } catch (Exception ex) {
            LOG.info("Failed to click on Facebook Icon on footer due to exception: " + ex.getMessage());
        }
    }

    public void goToVeloPdpPageAndTakeEyesScreenshot() {
        final String pageName = "Velo Pdp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("veloPdpUrl.key"));
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToBasketCouponCodeValidationsPageAndTakeEyesScreenshot() {
        if (!Props.EYES_ON) {
            return;
        }
        final String pageName = "Basket Coupon Code Validtions Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "lyftse":
                enterDataAndWait(eleApplyDiscountCode,UrlBuilder.getMessage("invalidCoupon.key"));
                waitAndClickByElementByJSExecutor(APPLY_DISCOUNT_CODE,3);
                scrollToShowEntirePage();
                eyesCheckVisiblePage(pageName);
                break;
        }
    }

    public void clickOnSubscription(){
        waitForExpectedElement(MYACCOUNT_SUBSCRIPTION);
        waitForExpectedElement(MYACCOUNT_SUBSCRIPTION).click();
    }

    public void goToTypoInProductSearchPageAndTakeEyesScreenshot() {
        final String pageName = "Typo In Product Search Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vusede":
            case "vuseza":
            case "vuseco":
                enterDataAndWait(SEARCH_TEXT_VUSE_DE,UrlBuilder.getMessage("typoSearchTerm.key"));
                waitAndClickByElementByJSExecutor(SEARCH_BUTTON_VUSE_DE,3);
                waitForExpectedElement(NO_SEARCH_MESSAGE_VUSE_DE,6);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToInvalidProductSearchPageAndTakeEyesScreenshot() {
        final String pageName = "Invalid Product Search Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vusede":
            case "vuseza":
            case "vuseco":
                enterDataAndWait(SEARCH_TEXT_VUSE_DE,UrlBuilder.getMessage("invalidSearchTerm.key"));
                waitAndClickByElementByJSExecutor(SEARCH_BUTTON_VUSE_DE,3);
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(GLO_ICON_GLO_DE,3);
                break;
        }
    }

    public void goToEmptyMiniBasketPageAndTakeEyesScreenshot() {
        final String pageName = "Empty Mini Basket Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
            case "vuseit":
            case "vusede":
                waitAndClickByElementByJSExecutor(MINI_BASKET_VUSE_DE,3);
                eyesCheckFullyPage(pageName);
                waitForExpectedElement(MINICART_BACK_BUTTON).click();
                break;
            case "vuseco":
                waitAndClickByElementByJSExecutor(MINI_BASKET_VUSE_DE,3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToEmptyCheckoutCartPageAndTakeEyesScreenshot() {
        final String pageName = "Empty Checkout Cart Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
            case "vusede":
            case "vuseza":
                LOG.info("Current URL before navigation:" + getCurrentUrl());
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("emptyCheckoutCartURL.key"));
                LOG.info("Current URL after navigation:" + getCurrentUrl());
                eyesCheckFullyPage(pageName);
                waitForExpectedElement(LOGO_VUSE_DE).click();
                break;
            case "velobe":
            case "velopl":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("emptyCheckoutCartURL.key"));
                eyesCheckFullyPage(pageName);
                UrlBuilder.navigateToBATHomePage();
                break;
        }
    }

    public void goToInsidersClubPageAndTakeEyesScreenshot() {
        final String pageName = "Insiders Club Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vusede":
                waitAndClickByElementByJSExecutor(INSIDER_CLUB_LINK_VUSE_DE,3);
                eyesCheckFullyPage(pageName);
                waitForExpectedElement(LOGO_VUSE_DE).click();
                break;
        }
    }



    public void goToVuseEpodPlpDeviceHoverPageAndTakeEyesScreenshot() {
        final String pageName = "Vuse Epod Plp Device Hover Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("epodPlpUrl.key"));
                hoverOnElement(VUSE_EPOD_PLP_IMAGE);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void assertErrorMessageForIncorrectEmailFormatResetPassword() {
        String actualErrorMessage = null;
        String expectedErrorMessage;
        By ERROR = RESET_PASSWORD_EMAIL_ERROR;
        enterText(RESET_PASSWORD_EMAIL, "test@test");
        waitForItemToBeClickableAndClick(RESET_PASSWORD_REGISTER, 10);
        expectedErrorMessage = UrlBuilder.getMessage("loginInvalidEmailErrorMsg.key");
        actualErrorMessage = waitForExpectedElement(ERROR, 5).getText();
        assertThat(expectedErrorMessage.equals(actualErrorMessage))
                .as("ERROR : expected error message was " + expectedErrorMessage + " but I got " + actualErrorMessage + " for field ").isTrue();
    }

    public void assertDifferentRowForProducts() {
        List<WebElement> productCount = getWebDriver().findElements(By.cssSelector("#mini-cart > li"));
        int count = productCount.size();
        assertEquals(count, 2);
    }

    public void assertFilterStrengthIsAscendingOrder(){
        List<WebElement> strengthElement = getWebDriver().findElements(FILTER_STRENGHT_VELOBE);
        List<Double> list = new ArrayList<>();
        for(int i=0;i<strengthElement.size();i++){
            list.add(Double.parseDouble(strengthElement.get(i).getAttribute("for").replaceAll("[^0-9]", "")));
        }
        assertTrue(isSorted(list));

    }

    boolean isSorted(List<Double> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) > list.get(i + 1))
                return false;
        }
        return true;
    }

    public void basketWithEmptyMessageAndHomepageButtonIsPresentAndRedirected() {
        waitForExpectedElement(BASEKT_EMPTY_MESSAGE, 20);
        assertTrueWithCustomError(UrlBuilder.getMessage("basketIsEmptyMessage.key"), waitForExpectedElement(BASEKT_EMPTY_MESSAGE).getText());
    }

    public void linkToHomepageIsDisplayed() {
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                assertTrue(isElementPresentByby(ERROR_HOMEPAGE_LINK_VUSEIT));
                assertTrue(waitForExpectedElement(ERROR_HOMEPAGE_LINK_VUSEIT).getText().contains(UrlBuilder.getMessage("404PageHomepageText.key")));
                assertTrue(waitForExpectedElement(ERROR_HOMEPAGE_LINK_VUSEIT).getAttribute("href").endsWith(UrlBuilder.getMessage("404PageHomepagURL.key")));
                break;
            case "vusefr":
                assertTrue(isElementPresentByby(ERROR_HOMEPAGE_LINK_VUSEFR));
                clickUsingJS(ERROR_HOMEPAGE_LINK_VUSEFR);
                assertTrue(isElementPresentByby(HOMEPAGE_BANNER));
                break;
            default:
                assertTrue(isElementPresentByby(ERROR_HOMEPAGE_LINK));
                clickUsingJS(ERROR_HOMEPAGE_LINK);
                assertTrue(isElementPresentByby(HOMEPAGE_BANNER));
        }
    }

    public void linkToMyAccountageIsDisplayed() {
        assertTrue(isElementPresentByby(ERROR_MYACCOUNTPAGE_LINK_VUSEIT));
        assertTrue(waitForExpectedElement(ERROR_MYACCOUNTPAGE_LINK_VUSEIT).getText().contains(UrlBuilder.getMessage("404PageMyAccountpageText.key")));
        assertTrue(waitForExpectedElement(ERROR_MYACCOUNTPAGE_LINK_VUSEIT).getAttribute("href").endsWith(UrlBuilder.getMessage("404PageMyAccountpagURL.key")));
    }

    public void homepageLinkIsPresentAndRedirected() {
        String title = UrlBuilder.getMessage("homepageTitle.key");
        assertTrue(isElementPresentByby(HOMEPAGE_LINK));
        clickUsingJS(HOMEPAGE_LINK);
        assertEquals(webDriver.getTitle(), title);
    }

    public void clickOnTheLogoAndAssertNavigationHomepage() {
        String pageTitle = UrlBuilder.getMessage("homepageTitle.key");
        try {
            waitForExpectedElement(logo, 30);
            waitForExpectedElement(logo).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(LOGO_ICON_FR);
        }
        assertTrue(isPageLoadedWithPageTitleOf(pageTitle));
    }

    public void goToPdpDevicePageAndTakeEyesScreenshot() {
        final String pageName = "Pdp Device Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseit":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("pdpDeviceUrl.key"));
                eyesCheckFullyPage(pageName);
                waitForExpectedElement(LOGO_VUSE_DE).click();
                break;
        }
    }

    public void clickOnFacebookIconOnTheFooter() {
        clickUsingJS(FACEBOOK_LINK_MX_FOOTER);
    }

    public void clickOnInstagramIconOnTheFooter() {
        clickUsingJS(INSTAGRAM_LINK_MX_FOOTER);
    }

    public void deleteProductAndVerifyCartIsEmpty() {
        String actualErrorMessage = null;
        String expectedErrorMessage;
        clickUsingJS(REMOVE_PRDUCT_MINI_CART);
        waitForExpectedElement(DELETE_BUTTON_POPUP);
        clickUsingJS(DELETE_BUTTON_POPUP);
        expectedErrorMessage = UrlBuilder.getMessage("basketIsEmptyMessage.key");
        actualErrorMessage = waitForExpectedElement(EMPTY_BASKET_MSG, 5).getText();
        assertThat(expectedErrorMessage.equals(actualErrorMessage))
                .as("ERROR : expected error message was " + expectedErrorMessage + " but I got " + actualErrorMessage + " for field ").isTrue();
    }

    public void goToEngraveAProductPageAndTakeEyesScreenshot() {
        final String pageName = "Engrave A Product Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "vuseza":
                UrlBuilder.navigateToRelativeUrl(UrlBuilder.getMessage("pdpEngraveProductUrl.key"));
                waitAndClickByElementByJSExecutor(ADD_A_PATTERN_LINK_VUSE_ZA, 3);
                clickUsingJS(PATTERN_IMAGE_VUSE_ZA);
                waitAndClickByElementByJSExecutor(SPECIFIC_PATTERN_IMAGE_VUSE_ZA, 3);
                eyesCheckFullyPage(pageName);
                waitAndClickByElementByJSExecutor(LOGO_IMAGE_VUSE_ZA, 3);
                break;
        }
    }

    public void goToAboutVeloPageAndTakeEyesScreenshot() {
        final String pageName = "About Velo Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velobe":
                waitAndClickByElementByJSExecutor(ABOUT_VELO_LINK_VELO_BE, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToProductsPlpPageAndTakeEyesScreenshot() {
        final String pageName = "Products Plp Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velobe":
                waitAndClickByElementByJSExecutor(PRODUCTS_LINK_VELO_BE, 3);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully()
                        .ignore(waitForExpectedElement(PRODUCT_ITEMS_REGION))
                        .ignoreDisplacements());
                break;
            case "velopl":
                waitAndClickByElementByJSExecutor(PRODUCTS_LINK_VELO_PL, 3);
                scrollToShowEntirePage();
                eyes.check(pageName, Target.window().fully()
                        .ignore(waitForExpectedElement(PRODUCT_ITEMS_REGION_VELO_PL))
                        .ignoreDisplacements());
                break;
        }
    }

    public void goToNewsAndInformationPageAndTakeEyesScreenshot() {
        final String pageName = "News And Information Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velobe":
                waitAndClickByElementByJSExecutor(NEWS_INFORMATION_LINK_VELO_BE, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToLanguageSelectorPageAndTakeEyesScreenshot() {
        final String pageName = "Language Selector Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velobe":
                waitAndClickByElementByJSExecutor(LANGUAGE_SELECTOR_LINK_VELO_BE, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToRegistrationPageAndTakeEyesScreenshot() {
        final String pageName = "Registration Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velopl":
            case "velobe":
                waitAndClickByElementByJSExecutor(SIGN_IN_LINK_VELO_BE, 3);
                waitAndClickByElementByJSExecutor(GUEST_CREATE_AN_ACCOUNT_LINK_CX_REDESIGN, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToMeetVeloPageAndTakeEyesScreenshot() {
        final String pageName = "Meet Velo Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velopl":
                waitAndClickByElementByJSExecutor(MEET_VELO_LINK_VELO_PL, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToBuyOnlinePageAndTakeEyesScreenshot() {
        final String pageName = "Buy Online Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velopl":
                waitAndClickByElementByJSExecutor(BUY_ONLINE_LINK_VELO_PL, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToEsmokingWorldPageAndTakeEyesScreenshot() {
        final String pageName = "Esmoking World Page";
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "velopl":
                waitAndClickByElementByJSExecutor(ESMOKING_WORLD_LINK_VELO_PL, 3);
                eyesCheckFullyPage(pageName);
                break;
        }
    }

    public void goToFollowUsBlock(){
        scrollToElement(FOLLOW_US_SOCIAL_PL);
        waitForExpectedElement(FOLLOW_US_SOCIAL_PL,5).isDisplayed();
    }
}