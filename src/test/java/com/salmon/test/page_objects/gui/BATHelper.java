package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.gui.admin.CustomerPage;
import com.salmon.test.page_objects.gui.admin.LoginPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.epok.*;
import com.salmon.test.page_objects.gui.gloIt.GloItCheckoutPage;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.salmon.test.page_objects.gui.PDP.SWATCH_COLOR_BUTTON;
import static com.salmon.test.page_objects.gui.PaymentPage.M_CONTINUE_CHECK_OUT_BTN_VUSEUK;
import static com.salmon.test.page_objects.gui.constants.Context.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class BATHelper extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(BATHelper.class);

    private HomePage homePage;
    private PDP pdp;
    private SearchResult searchResult;
    private LogininPage logininPage;
    private RegistrationPage registrationPage;
    private PaymentPage paymentPage;
    private AccountDashboardPage accountDashboardPage;
    private float productPrice;
    //glo page objects
    private GloItHomePage gloItHomePage;
    private GloItProductListPage gloItProductListPage;
    private GloItCheckoutPage gloItCheckoutPage;
    private OrderSuccessPage orderSuccessPage;
    private EpokLoginPage epokLoginPage;
    private EpokHomePage epokHomePage;
    private EpokRegistrationPage epokRegistrationPage;
    private EpokProductListPage epokProductListPage;
    private BasketPage basketPage;
    private EpokCheckoutPage epokCheckoutPage;
    private CustomerPage customerPage;
    private LoginPage loginPage;
    private PLP plp;
    private ScenarioContext scenarioContext;
    private AvalancheAccountDashboardPage avalancheAccountDashboardPage;
    private static final By PAGE_HEADING = By.cssSelector("h1");
    private static final By VUSECO_AGREETS = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content div.checkout-agreements-block > div > div > div > label > span");
    private static final By VUSECO_PLACEORDER = By.cssSelector("div.payment-method._active >div.payment-method-content > div.actions-toolbar > div > button");
    private static final By CREATE_PROFILE = By.cssSelector("div.waiting-for > div > div:nth-child(1) > div.two-button > div > a");
    private static final By STANDARD_DELIVERY = By.cssSelector("#label_method_standard_delivery__1_to_3_working_days__matrixrates");
    private static final By DELIVERY_COST = By.cssSelector("#checkout-shipping-method-load > table > tbody > tr > td.col.col-price > span");
    private static final By FREE_DELIVERY = By.cssSelector("#label_method_free_shipping___1_to_3_working_days__matrixrates");
    private static final By DELIVERY_COST_INCLUDED = By.cssSelector("td.col.col-price > span");

    public BATHelper(TestContext testContext, HomePage homePage, PDP pdp, SearchResult searchResult, LogininPage logininPage, PLP plp,
                     RegistrationPage registrationPage, PaymentPage paymentPage, AccountDashboardPage accountDashboardPage,
                     GloItHomePage gloItHomePage, GloItProductListPage gloItProductListPage, GloItCheckoutPage gloItCheckoutPage, OrderSuccessPage orderSuccessPage, EpokLoginPage epokLoginPage, EpokHomePage epokHomePage,
                     EpokRegistrationPage epokRegistrationPage, EpokProductListPage epokProductListPage, BasketPage basketPage, EpokCheckoutPage epokCheckoutPage, CustomerPage customerPage, LoginPage loginPage,
                     AvalancheAccountDashboardPage avalancheAccountDashboardPage){
        this.homePage = homePage;
        this.pdp = pdp;
        this.searchResult = searchResult;
        this.logininPage = logininPage;
        this.registrationPage = registrationPage;
        this.paymentPage = paymentPage;
        this.accountDashboardPage = accountDashboardPage;
        this.gloItHomePage=gloItHomePage;
        this.gloItProductListPage=gloItProductListPage;
        this.gloItCheckoutPage=gloItCheckoutPage;
        this.orderSuccessPage=orderSuccessPage;
        this.epokLoginPage=epokLoginPage;
        this.epokHomePage=epokHomePage;
        this.epokRegistrationPage=epokRegistrationPage;
        this.epokProductListPage=epokProductListPage;
        this.basketPage = basketPage;
        this.epokCheckoutPage=epokCheckoutPage;
        this.customerPage=customerPage;
        this.loginPage=loginPage;
        this.plp = plp;
        scenarioContext = testContext.getScenarioContext();
        this.avalancheAccountDashboardPage = avalancheAccountDashboardPage;
    }

    public void searchAddToCart(String searchTerm) throws InterruptedException {
      pdp.performSearch(searchTerm);
      searchResult.eyesCheckSearchResultPage();
      searchResult.selectSearchResult();
      pdp.eyesCheckPdpPage();
      pdp.waitForAjaxElementNotToBePresent(getWebDriver(),5);
      pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
      if(UrlBuilder.getLocale().equalsIgnoreCase("vusefr") || UrlBuilder.getLocale().equalsIgnoreCase("de") ||
              UrlBuilder.getLocale().equalsIgnoreCase("uk")|| UrlBuilder.getLocale().equalsIgnoreCase("vuseuk") || UrlBuilder.getLocale().equalsIgnoreCase("vuseco")) {
        pdp.selectProductColorStrengthFromList(SWATCH_COLOR_BUTTON);
      } else if (getWebDriver().findElements(pdp.btnSwatchColor).size() > 0) {
        pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
      }
      if (UrlBuilder.getLocale().equals("vusede")&& (!UrlBuilder.isSamsungMobile())&& (!UrlBuilder.isIPhone())){
        clickUsingJS(pdp.VUSE_ADD_ONE_QTY);
      }
      pdp.clickAddToCartButton();
      if(UrlBuilder.isFirefox()&&UrlBuilder.getLocale().equalsIgnoreCase("fr"))
        Thread.sleep(3000);
    }

    public void searchAddAProductToBasket(String searchTerm) throws InterruptedException {
        if (UrlBuilder.isDesktop()) {
            if (!pdp.doesURLContain("/se/sv/lab")) {
                searchAddToCart(searchTerm);
            }
        } else {//for mobile
            searchAddToCart(searchTerm);
        }
    }

    public void searchAndViewProductForTheBasket(String searchTerm) throws InterruptedException {
        if(pdp.doesURLContain("/mx/es/")) {
            pdp.performSearch(searchTerm);
            searchResult.selectSearchResult();
            pdp.clickAddToCartButton();
        }
        if(!pdp.doesURLContain("/se/sv/lab")) {
            pdp.performSearch(searchTerm);
            searchResult.selectSearchResult();
            pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
            if(UrlBuilder.getLocale().equalsIgnoreCase("fr"))
                pdp.selectProductColorStrengthFromList(SWATCH_COLOR_BUTTON);
            else
                pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
            pdp.clickAddToCartButton();
        }
        homePage.clickOnBasketIcon();
    }

    public void checkoutAproduct(String searchTerm) throws InterruptedException {
        searchAddAProductToBasket(searchTerm);
        if(UrlBuilder.isDesktop()||UrlBuilder.isIpad()){
            homePage.clickOnBasketIcon();
            homePage.clickOnProceedToCheckoutButton();
            paymentPage.waitForElementToAppearAndDisappear(LOADER, 15, 30);
        }
        else if(UrlBuilder.isSamsungMobile()||UrlBuilder.isIPhone()||UrlBuilder.isMobile()) {
            switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
                case VYPEIT:
                case VUSEIT:
                case LYFTSE:
                case LYFTDK:
                case VUSEDK:
                case VUSECO:
                case VUSEDE:
                    if(UrlBuilder.isIPhone()||UrlBuilder.isSamsungMobile()){
                        homePage.clickOnBasketIcon();
                        homePage.clickViewBasketButton();
                    }
                    homePage.clickOnProceedToCheckoutButton();
                    break;
                case MX:
                case VUSEMX:
                case FR:
                case VUSEFR:
                    homePage.clickOnProceedToCheckoutButton();
                    break;
                case VUSEZA:
                case IE:
                    homePage.clickOnBasketIcon();
                    homePage.clickViewBasketButton();
                    homePage.clickOnProceedToCheckoutButton();
                    break;
                case VUSEUK:
                    homePage.clickViewBasketButton();
                    homePage.clickOnProceedToCheckoutButton();
                    break;
                default:
                    basketPage.clickOnCheckoutButton();
            }
        }
    }

    public void checkoutAproductOnGlo() throws Throwable {
        gloItHomePage.clickOnBuyButtonAndItsSubMenu();
        plp.eyesCheckPlpPage();
        plp.eyesCheckPlpHoverRegion();
        switch(UrlBuilder.getLocale()) {
            case "it":
                gloItProductListPage.selectFirstBuyableProduct();
                break;
            case "pl":
                searchResult.selectFirstBuyableProduct_Pl();
                gloItProductListPage.waitForExpectedElement(gloItProductListPage.PRODUCT_TITLE,10);
                break;
            default:
                gloItProductListPage.selectFirstResult();
        }
        pdp.eyesCheckPdpPage();
        pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
        switch(UrlBuilder.getLocale()) {
            case "glode"://added for FF stability
                LOG.info("we currently dont have colour or strength for a product");
                break;
            case "pl"://TODO: test
                if(pdp.isElementPresent(pdp.BTN_SWATCH_COLOR_GLOIT)) {
                    if (pdp.waitForExpectedElement(pdp.BTN_SWATCH_COLOR_GLOIT).getAttribute("aria-checked").equalsIgnoreCase("false")) {
                        pdp.clickByElementByQueryJSExecutor(pdp.BTN_SWATCH_COLOR_GLOIT);
                    }
                }
                break;
            case "kz":
                pdp.clickByElementByQueryJSExecutor(pdp.btnSwatchColor);
                break;
            default:
                pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor); // not working on FF
                break;
        }
        if(customerPage.getWebDriver().findElements(pdp.CUSTOMIZE_ADD_TO_CART).size() > 0){
            pdp.clickByElementByQueryJSExecutor(pdp.CUSTOMIZE_ADD_TO_CART);
            pdp.clickUsingJS(pdp.addToCartButton);
        } else{
//            pdp.jsScrollElementInCenter(waitForExpectedElement(pdp.addToCartButton,10));
            pdp.waitForExpectedElement(pdp.addToCartButton,10);
            pdp.clickUsingJS(pdp.addToCartButton);
            switch(UrlBuilder.getLocale()) {
                case "pl"://added  below for FF stability
                    waitForAjaxElementNotToBePresent(getWebDriver(),10);
                    basketPage.viewBasketCta();
                    homePage.addFreeGiftAccordingToDevice();
                    pdp.waitForExpectedElement(PDP.GLOIT_CART_QTY, 15);
                    break;
                case "it"://added below for FF stability
                    pdp.waitForExpectedElement(PDP.GLOIT_CART_QTY, 15);
                    break;
                default:
                    break;
            }
        }
        if (!UrlBuilder.getLocale().equalsIgnoreCase("glode")
                && !UrlBuilder.getLocale().equalsIgnoreCase("it")) {
            homePage.clickOnBasketIcon();
        }
        homePage.eyesCheckMiniCart();
        gloItHomePage.OnMiniCartclickOnProceedToCheckoutButton();
        homePage.waitForElementToAppearAndDisappear(PageObject.LOADER, 2, 10);
    }

    public void createSameAccount() throws Throwable {
            homePage.navigateToSignInPage();
        logininPage.eyesCheckLoginPage();
        logininPage.clickRegistrationButton();
        logininPage.eyesCheckRegistrationPage();
        registrationPage.enterSameRegistrationDetailsAndCreateAccountWithoutConfirmEmail(scenarioContext.getContext(NEW_USER_EMAIL_ID).toString());
    }
    public void createNewAccount() throws Throwable {
        if(!(UrlBuilder.getLocale().equalsIgnoreCase("kz") || UrlBuilder.getLocale().equalsIgnoreCase("veloza")))
           homePage.navigateToSignInPage();
        logininPage.eyesCheckLoginPage();
       logininPage.clickRegistrationButton();
        logininPage.eyesCheckRegistrationPage();
        registrationPage.enterRegistrationDetailsAndCreateAccount(registrationPage.emailAddressData);
        switch (UrlBuilder.getLocale()) {
            case "kz":
                loginPage.userLoginsMagentoAdminHomePageSuccessfully();
                customerPage.userApprovesNewlyCreatedUser(registrationPage.emailAddressData);
                customerPage.getWebDriver().navigate().to(UrlBuilder.url);
                logininPage.login(registrationPage.emailAddressData,scenarioContext.getContext(Context.PASSWORD).toString());
                break;
            case "ie":
                if(!SessionInfo.scenarioName.contains("Registration of new user")) {
                    loginPage.userLoginsMagentoAdminHomePageSuccessfully();
                    customerPage.userTurnOnIDScan();
                    customerPage.getWebDriver().navigate().to(UrlBuilder.url);
                    homePage.navigateToSignInPage();
                }
                break;
        }
        if (UrlBuilder.getLocale().equalsIgnoreCase("kz")) {
            gloItHomePage.userClosesRDBPopUpBannerIfPresentForKZ();
            homePage.eyesCheckHomePage();
            if(!(UrlBuilder.isIPhone() || UrlBuilder.isIpad())) {
                homePage.clickPersonIcon();
                homePage.chooseMyAccountOnAccountDropdown();
            }
        }
        accountDashboardPage.eyesCheckAccountDashboardPage();
    }

    public void createNewAccountWithoutConfirmEmail() throws Throwable {
        logininPage.clickRegistrationButton();
        registrationPage.enterRegistrationDetailsAndCreateAccountWithoutConfirmEmail(registrationPage.emailAddressData);
    }

    public void approveUser(String email, String password) throws Throwable{
        if ("kz".equals(UrlBuilder.getLocale())) {
            loginPage.userLoginsMagentoAdminHomePageSuccessfully();
            customerPage.userApprovesNewlyCreatedUser(email);
            customerPage.getWebDriver().navigate().to(UrlBuilder.url);
        }
    }


    public void createANewAccountThroughNewsletterWithoutLoyaltySignup() throws Throwable {
        homePage.navigateToSignInPage();
        logininPage.eyesCheckLoginPage();
        logininPage.clickRegistrationButton();
        logininPage.eyesCheckRegistrationPage();
        homePage.clickOnNewsletterButton();
        registrationPage.enterRegistrationDetailsAndCreateAccount(registrationPage.emailAddressData);
    }

    public void createNewAccountFromLoginPage(){
        logininPage.clickRegistrationButton();
        registrationPage.enterRegistrationDetailsAndCreateAccount(registrationPage.emailAddressData);
    }

    public void createNewAccountGuest() {
        registrationPage.enterRegistrationDetailsAndCreateAccount(registrationPage.emailAddressData);
    }

    public void navigateToUserCreationForm(){
        homePage.navigateToSignInPage();
        logininPage.clickRegistrationButton();
    }

    public void createNewAccountBAT() throws Throwable {
        epokHomePage.clickOnPersonIcon();
        logininPage.eyesCheckLoginPage();
        epokLoginPage.clickRegisterButton();
        logininPage.eyesCheckRegistrationPage();
        epokRegistrationPage.enterRegistrationDetails();
        accountDashboardPage.eyesCheckAccountDashboardPage();
    }

    public void createNewAccount(String emailAddress) {
        homePage.navigateToSignInPage();
        logininPage.eyesCheckLoginPage();
        logininPage.clickRegistrationButton();
        logininPage.eyesCheckRegistrationPage();
        registrationPage.enterRegistrationDetailsAndCreateAccount(emailAddress);
        accountDashboardPage.eyesCheckAccountDashboardPage();
    }

    public void  paysBy(String paymentType) throws InterruptedException {
        paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(),30);
        if(UrlBuilder.getLocale().equalsIgnoreCase("mx")&&(UrlBuilder.isIPhone()||UrlBuilder.isIpad())){
            paymentType="paypal";
        }

        if(UrlBuilder.getLocale().contains("kz")){
            paymentPage.eyesCheckCheckoutPage();
            gloItCheckoutPage.cashOnDeliveryCheckBox();
            gloItCheckoutPage.selectBuyNowButtonFromCard();
            return;
        }
        else if(UrlBuilder.getLocale().contains("pl")){
            paymentPage.eyesCheckCheckoutPage();
            paymentPage.choosePaymentMethod();
            gloItCheckoutPage.selectAndPurchaseThroughPayU_PAYMENT();
            return;
        }else if(UrlBuilder.getLocale().equalsIgnoreCase("lyftse")){
            paymentPage.enterDpsParcelshopNotificationIfPresent();
        }
        paymentPage.waitForElementToAppearAndDisappear(LOADER,2,10);
        if (!UrlBuilder.getLocale().equals("vuseco")) {
            paymentPage.eyesCheckCheckoutPage();
        }
        switch (paymentType) {
            case "mastercard":
                if(UrlBuilder.getLocale().equals("vuseuk") && UrlBuilder.isMobile())
                {
                    if(isElementPresent(M_CONTINUE_CHECK_OUT_BTN_VUSEUK))
                    {
                        scrollElementIntoView(M_CONTINUE_CHECK_OUT_BTN_VUSEUK);
                        clickByElementByQueryJSExecutor(M_CONTINUE_CHECK_OUT_BTN_VUSEUK);
                    }
                    paymentPage.selectCardPaymentsRadioButton();
                    paymentPage.selectMasterCardOption();
                    paymentPage.enterValidMasterCardDetailsAndSubmit();
                    break;
                }
                else if (UrlBuilder.getLocale().equals("vuseco")){
                    paymentPage.MasterCardOrderOptionSelected();
                    paymentPage.enterValidMasterCardDetailsAndSubmit();
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    waitAndClickByElementByJSExecutor(VUSECO_AGREETS, 10);
                    waitForAjaxElementNotToBePresent(getWebDriver(),5);
                    waitForExpectedElement(VUSECO_PLACEORDER).click();
                    waitForAjaxElementNotToBePresent(getWebDriver(),20);
                    break;
                } else {
                    if (!doesURLContain("mx/es/")){
                        try {
                            paymentPage.selectCardPaymentsRadioButton();
                            paymentPage.selectMasterCardOption(); }
                        catch (Exception e) {
                            paymentPage.selectSubscribeProPayments();
                        }
                    }
                    paymentPage.goToTermsAndConditionPageAndTakeEyesScreenshot();
                    paymentPage.enterValidVISACardDetailsAndSubmit();
                }
                break;
            case "debitcard":
                //To be implemented
                break;
            case "sofort":
                paymentPage.selectAlternativePaymentOptions();
                paymentPage.selectSoFortRadioButton();
                break;
            case "paypal":
                paymentPage.selectAlternativePaymentOptions();
                paymentPage.selectPaypalRadioButton();
                break;
            case "open pay pse":
                paymentPage.selectDebitCardOptionCO();
                paymentPage.clickPlaceOrder();
                paymentPage.enterOpenPayDetails();
                paymentPage.placeOpenPayOrder();
                break;
            case "Pay gate card":
                    if(UrlBuilder.getLocale().matches("vuseza")){
                        paymentPage.clickOnPaygatePaymentRadioButton();
                        paymentPage.clickPlaceOrder();
                        paymentPage.paymentCardZA();
                    }
                break;
            case "Sid Secure EFT":
                if (UrlBuilder.getLocale().equals("vuseza")){
                    paymentPage.clickOnPaygatePaymentRadioButton();
                    paymentPage.clickPlaceOrder();
                    paymentPage.sidPayment();
                }else {
                    paymentPage.clickOnPaygatePaymentRadioButton();
                    paymentPage.sidPayment();
                }
                break;
            case "google pay":
                paymentPage.selectWalletPaymentsOption();
                break;
            default:
                throw new IllegalArgumentException("unsupportted payment type:"+paymentType);
        }
        if (Props.EYES_ON && UrlBuilder.getLocale().equalsIgnoreCase("uk")) {
            paymentPage.saveCard();
        }
        if (paymentType.equals("google pay"))
            paymentPage.clickTermsAndConditionsBoxForGooglePay();
        else if(!UrlBuilder.getLocale().equalsIgnoreCase("vuseco"))
            paymentPage.clickTermsAndConditionsBox();
        paymentPage.waitForElementToAppearAndDisappear(LOADER,3,10);
        if(UrlBuilder.getSite().contains("glo")){
            gloItCheckoutPage.selectBuyNowButtonFromCard();
        }else{
            if(!UrlBuilder.getLocale().contains("co") && (!UrlBuilder.getLocale().equals("vuseza"))) {
                if (paymentType.equals("sofort") ){
                    paymentPage.waitForPage();
                    paymentPage.clickPlaceOrder();
                    paymentPage.selectSofortContinueButton();
                } else if (paymentType.equals("paypal")) {
                    paymentPage.waitForPage();
                    paymentPage.clickPlaceOrder();
                    paymentPage.selectPayPalContinueButton();
                } else {
                    paymentPage.clickPlaceOrder();
                }
            }
        }
        switch (UrlBuilder.getLocale()) {
            case "lyftdk":
            case "dk":
            case "nl":
            case "fr":
            case "vusefr":
               orderSuccessPage.getGeneratedOrderNumber();
            break;
        }
        paymentPage.waitForLoaderToDisapear();
        paymentPage.waitForElementToAppearAndDisappear(LOADER,5,10);
    }

    public void purchaseAProduct(String searchTerm, String paymentType) throws Exception {
        checkoutAproduct(searchTerm);
        switch (UrlBuilder.getLocale()) {
            case "lyftdk":
            case "dk":
            case "vusedk":
                paymentPage.enterSelectPackageShopDetails();
                break;
            case "vusefr":
            case "vuseuk":
                paymentPage.goToAddNewAddressPageAndTakeEyesScreenShot();
                break;
            case "lyftse":
                paymentPage.goToAddNewAddressPageAndTakeEyesScreenShot();
                paymentPage.goToCheckoutCouponCodeValidationsPageAndTakeEyesScreenshot();
                paymentPage.enterSelectPackageShopDetails();
                break;
        }
        paysBy(paymentType);
    }
    public void purchaseAProductAndTriggerError(String searchTerm, String paymentType) throws Exception {
        checkoutAproduct(searchTerm);
        switch (UrlBuilder.getLocale()) {
            case "lyftdk":
            case "dk":
            case "lyftse":
            case "vusedk":
                paymentPage.enterSelectPackageShopDetails();
                break;
        }
        triggerErrorForPayType(paymentType);
    }

    public void purchaseAProduct(String paymentType) throws Exception {
        pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
        pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
        pdp.clickAddToCartButton();
        homePage.clickOnBasketIcon();
        homePage.clickOnProceedToCheckoutButton();
        paysBy(paymentType);
    }

    public void purchaseAProductOnGlo(String paymentType) throws Throwable {
        checkoutAproductOnGlo();
        paysBy(paymentType);
    }

    public void loginAndProceedToCheckoutPage(String email, String password) throws Throwable {
        switch (UrlBuilder.getLocale()){
            case "kz":
                logininPage.userEntersValidSigninDetails(email,password);
                checkoutAproductOnGlo();
                break;
            case "pl":
                gloItHomePage.clicksOnPersonIcon();
                logininPage.userEntersValidSigninDetails(email,password);
                homePage.emptyBasket();
                checkoutAproductOnGlo();
            break;
            case "glode":
                gloItHomePage.clicksOnPersonIcon();
                logininPage.userEntersValidSigninDetails(email,password);
                checkoutAproductOnGlo();
                break;
            default:
                gloItHomePage.clicksOnPersonIcon();
                logininPage.userEntersValidSigninDetails(email,password);
                gloItHomePage.clicksOnPersonIcon();
                homePage.emptyBasket();
                checkoutAproductOnGlo();
        }
    }
    public void purchaseAProductOnVelo(String paymentType) throws Throwable {
        epokHomePage.clickHamburgerMenu();
        homePage.clickOnProudctMenu();
        plp.eyesCheckPlpPage();
        plp.clickOnAddToCartButton();
        epokProductListPage.productAddToCartSuccessMsg();
        homePage.waitForElementToAppearAndDisappear(LOADER,3,5);
        homePage.clickOnBasketIcon();
        homePage.waitForElementToAppearAndDisappear(LOADER,3,5);
        homePage.eyesCheckBasketPage();
        basketPage.clickOnCheckoutButton();
        paymentPage.eyesCheckCheckoutPage();
        SelectAndEnterCardPaymentDetails(paymentType);
    }

    public void SelectAndEnterCardPaymentDetails(String paymentType) throws InterruptedException {
        switch (paymentType) {
            case "mastercard":
                paymentPage.selectCardPaymentsRadioButton();
                gloItCheckoutPage.selectCreditCardCheckbox();
                paymentPage.MasterCardOrderOptionSelected();
                gloItCheckoutPage.enterValidMasterOrVisaCardDetailsAndSubmit();
                gloItCheckoutPage.selectBuyNowButtonFromCard();
                break;
            case "paypal":
                paymentPage.selectAlternativePaymentOptions();
                paymentPage.selectPaypalRadioButton();
                gloItCheckoutPage.selectBuyNowButtonFromCard();
                paymentPage.selectPayPalContinueButton();
                break;
            case "visa":
                paymentPage.selectCardPaymentsRadioButton();
                gloItCheckoutPage.selectCreditCardCheckbox();
                paymentPage.VISACardOrderOptionSelected();
                gloItCheckoutPage.enterValidMasterOrVisaCardDetailsAndSubmit();
                gloItCheckoutPage.selectBuyNowButtonFromCard();
                break;
            case "savedcard":
                paymentPage.selectCardPaymentsRadioButton();
                gloItCheckoutPage.selectCreditCardCheckbox();
                paymentPage.MasterCardOrderOptionSelected();
                gloItCheckoutPage.enterValidMasterOrVisaCardDetailsAndSubmit();
                paymentPage.saveCard();
                gloItCheckoutPage.selectBuyNowButtonFromCard();
                paymentPage.assertCheckoutSuccess();
                homePage.clickOnProudctMenu();
                plp.eyesCheckPlpPage();
                plp.clickOnAddToCartButton();
                epokProductListPage.productAddToCartSuccessMsg();
                homePage.waitForElementToAppearAndDisappear(LOADER,3,5);
                homePage.clickOnBasketIcon();
                homePage.waitForElementToAppearAndDisappear(LOADER,3,5);
                homePage.eyesCheckBasketPage();
                basketPage.clickOnCheckoutButton();
                gloItCheckoutPage.selectUsersSavedCard();
        }
    }

    public void deleteCustomer() throws InterruptedException {
        switch (UrlBuilder.getLocale()) {
            case "vypeit":
            case "vuseit":
                break;
            case "vusede":
                if(UrlBuilder.isIPhone()||UrlBuilder.isIpad()) {
                    try {
                        homePage.waitForExpectedElement(homePage.LOGO_ICON_VUSEUK).click();
                        waitForPage();
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/index");
                        accountDashboardPage.deleteMyAccount();
                    } catch (Exception ex) {
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/index");
                    }
                }
                else {
                    homePage.clickPersonIcon();
                    homePage.waitForExpectedElement(homePage.VUSE_DE_MY_ACCOUNT_LINK_FROM_DROPDOWN).click();
                    accountDashboardPage.deleteMyAccount();
                }
                break;
            case "vusedk":
            case "vuseza":
                homePage.clickPersonIcon();
                accountDashboardPage.deleteMyAccount();
                break;
            case "vuseuk":
            case"mx":
                try{
                    homePage.clickPersonIcon();
                    homePage.clickLinkOnPersonMenu("myAccountLink.key");
                    accountDashboardPage.deleteMyAccount();
                }catch(Exception e){
                    clickUsingJS(HomePage.LOGO_ICON_VUSEUK);
                    waitForAjaxElementNotToBePresent(getWebDriver(),5);
                    waitForPage();
                    getWebDriver().navigate().to(getCurrentUrl() + "customer/account/index");
                    accountDashboardPage.deleteMyAccount();}
                break;
            case "vuseco":
                if(UrlBuilder.isIPhone()||UrlBuilder.isIpad()||UrlBuilder.isMobile()) {
                    try {
                        waitForPage();
                        homePage.waitForExpectedElement(homePage.LOGO_ICON_VUSEUK).click();
                        waitForPage();
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/index");
                        accountDashboardPage.deleteMyAccount();
                    } catch (Exception ex) {
                        getWebDriver().navigate().to(getCurrentUrl() + "customer/account/index");
                    }
                } else {
                    try{
                    homePage.clickPersonIcon();
                    homePage.clickLinkOnPersonMenu("myAccountLink.key");
                    accountDashboardPage.deleteMyAccount();
                }catch(Exception e) {
                    homePage.waitForExpectedElement(HomePage.LOGO_ICON_VUSEUK, 5).click();
                    waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                    waitForPage();
                    getWebDriver().navigate().to(getCurrentUrl() + "customer/account/index");
                    accountDashboardPage.deleteMyAccount();
                }
                }
                    break;
            default:
                homePage.clickPersonIcon();
                homePage.clickLinkOnPersonMenu("myAccountLink.key");
                accountDashboardPage.deleteMyAccount();
        }
    }

    public void searchAndViewBasketForTheProduct(String searchTerm) throws Throwable {
        searchAndViewProductForTheBasket(searchTerm);
        homePage.usersClicksOnTheLinkByText("viewBasketText.key");
    }

    public void selectGLOProductAndViewBasketAsGuest()throws Throwable{
        gloItHomePage.clickOnBuyButtonAndItsSubMenu();
        searchResult.selectFirstBuyableProduct_Pl();
        pdp.clickByElementByQueryJSExecutor(pdp.addToCartButton);
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        homePage.clickOnBasketIcon();
        homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("viewBasketText.key")));
        homePage.usersClicksOnTheLinkByText("viewBasketText.key");
        waitForAjaxElementNotToBePresent(getWebDriver(), 5);
    }

    public void selectGLOProductAndViewBasket() throws Throwable {
        switch (UrlBuilder.getLocale()){
            case "kz":
                gloItHomePage.clickOnBuyButtonAndItsSubMenu();
                gloItProductListPage.selectFirstResult();
                pdp.clickByElementByQueryJSExecutor(pdp.btnSwatchColor);
                pdp.clickByElementByQueryJSExecutor(pdp.addToCartButton);
                homePage.clickOnBasketIcon();
                homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("viewBasketText.key")));
                homePage.usersClicksOnTheLinkByText("viewBasketText.key");
                break;
            case "pl":
                gloItHomePage.clickOnBuyButtonAndItsSubMenu();
                searchResult.selectFirstBuyableProduct_Pl();
                pdp.clickByElementByQueryJSExecutor(pdp.addToCartButton);
                waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                try {
                    homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("viewBasketText.key")));
                    homePage.usersClicksOnTheLinkByText("viewBasketText.key");
                }catch(TimeoutException te){
                    homePage.clickBasketIcon();
                    homePage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("viewBasketText.key")));
                    homePage.usersClicksOnTheLinkByText("viewBasketText.key");
                }
                waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                if(isElementDisplayedBySeconds(HomePage.CLOSE_FREE_GIFT_WINDOW,10)) {
                    clickByElementByQueryJSExecutor(HomePage.CLOSE_FREE_GIFT_WINDOW);
                }
                break;
            case "glojp" :
                gloItProductListPage.clickOnGloDropdown();
                gloItProductListPage.selectFirstResult();
                break;
            case "it" :
                if(UrlBuilder.isMobile())
                {
                    gloItHomePage.clickOnBuyButtonAndItsSubMenu();
                    gloItProductListPage.selectFirstBuyableProduct();
                    pdp.clickByElementByQueryJSExecutor(SWATCH_COLOR_BUTTON);
                    pdp.clickByElementByQueryJSExecutor(pdp.M_ADD_TO_CART_BTN_GLOIT);
                }
                else {
                    gloItHomePage.clickOnBuyButtonAndItsSubMenu();
                    gloItProductListPage.selectFirstResult();
                    pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                    pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                    Thread.sleep(4000);
                    pdp.clickByElementByQueryJSExecutor(pdp.addToCartButton);
                    homePage.clickOnBasketIcon();
                    homePage.usersClicksOnTheLinkByText("viewBasketText.key");
                }
                break;
            default:
                gloItHomePage.clickOnBuyButtonAndItsSubMenu();
                gloItProductListPage.selectFirstResult();
                pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
                pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
                Thread.sleep(4000);
                pdp.clickByElementByQueryJSExecutor(pdp.addToCartButton);
                homePage.clickOnBasketIcon();
                homePage.usersClicksOnTheLinkByText("viewBasketText.key");
                break;
        }
    }

    public void checkoutAproductAddedInBasket() throws InterruptedException {
        switch (UrlBuilder.getLocale()) {
            case "ie":
            case "vusede":
                homePage.clickOnBasketIcon();
                break;
            case "lyftse":
                homePage.openBasketifNotOpenLogic();
                break;
        }
        homePage.clickOnProceedToCheckoutButton();
        paymentPage.paymentPageDetailsConfirmed();
    }

    public void addProductIntoBasket(String searchTerm) throws Throwable {
        searchAndViewProductForTheBasket(searchTerm);
        homePage.usersClicksOnTheLinkByText("viewBasketText.key");
    }

    public void approveNewUserInAdminAndReturn(String emailAddress) throws Throwable {
        loginPage.userLoginsMagentoAdminHomePageSuccessfully();
        customerPage.userApprovesNewlyCreatedUser(emailAddress);
        customerPage.getWebDriver().navigate().to(UrlBuilder.url);
    }

    public void loginWithRegisteredRandomEmailAndPassword() {
        logininPage.login(registrationPage.emailAddressData, registrationPage.passwordData);
    }

    public void addTofinoProductToBasket()throws Throwable{
        pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
        pdp.clickAddToCartButton();
        homePage.clickOnBasketIcon();
        homePage.waitForExpectedElement(homePage.VIEW_BASKET).click();
        homePage.waitForExpectedElement(homePage.PAGE_TITLE_WRAPPER);
    }

    public void additionalWaitAfterPlacingOrderVuseCO(){
        if (UrlBuilder.isDesktop() && UrlBuilder.getLocale().equals("vuseco")) {
            waitForPage();
            waitForExpectedElement(homePage.GO_TO_MY_ACCOUNT,60);
        }
    }

    public void assertThatHeaderContains(String expectedText) {
        additionalWaitAfterPlacingOrderVuseCO();
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        waitForExpectedElement(PAGE_HEADING);
        String actualText = returnHeaderText();
        assertThat(actualText.toLowerCase().contains(expectedText.toLowerCase()))
                .as("ERROR expected header text was "+expectedText+" but got "+actualText).isTrue();
    }

    public void triggerErrorForPayType(String paymentType) {
        paymentPage.waitForElementToAppearAndDisappear(LOADER,3,20);
        paymentPage.eyesCheckCheckoutPage();
        if ("mastercard".equals(paymentType)) {
            paymentPage.selectCardPaymentsRadioButton();
            paymentPage.selectMasterCardOption();
            paymentPage.clickPlaceOrder();
            paymentPage.eyesCheckCheckoutPage();
            paymentPage.enterInvalidVISACardDetailsAndSubmit();
        }
        paymentPage.clickPlaceOrder();
        paymentPage.eyesCheckCheckoutPage();
    }

    public void paysTrialOrder(String paymentType) throws InterruptedException {
        paymentPage.waitForElementToAppearAndDisappear(LOADER, 3, 20);
        switch (paymentType) {
            case "mastercard":
                paymentPage.selectMasterCardOption();
                paymentPage.enterValidVISACardDetailsAndSubmitForTrial();
                paymentPage.clickTermsAndConditionsBox();
                paymentPage.waitForElementToAppearAndDisappear(LOADER,3,10);
                paymentPage.clickPlaceOrder();
                paymentPage.waitForElementToAppearAndDisappear(LOADER,5,15);
                orderSuccessPage.getGeneratedOrderNumber();
                break;
            case "debitcard":
                //To be implemented
                break;
            default:
                throw new IllegalArgumentException("unsupportted payment type:" + paymentType);
        }
    }
        public void placeTrialOrder() throws InterruptedException{
            paymentPage.clickTermsAndConditionsBox();
            paymentPage.waitForElementToAppearAndDisappear(LOADER,3,10);
            paymentPage.clickPlaceOrder();
            paymentPage.waitForElementToAppearAndDisappear(LOADER,5,15);
            orderSuccessPage.getGeneratedOrderNumber();
        }

    public void createNewAccountFromLoginPageWithLoyalty(){
        logininPage.clickRegistrationButton();
        registrationPage.enterRegistrationDetailsAndCreateLoyaltyAccount(registrationPage.emailAddressData);
    }

    public void createANewAccountThroughNewsletterWithLoyaltySignup() throws Throwable {
        homePage.navigateToSignInPage();
        logininPage.eyesCheckLoginPage();
        logininPage.clickRegistrationButton();
        logininPage.eyesCheckRegistrationPage();
        homePage.clickOnNewsletterButton();
        registrationPage.enterRegistrationDetailsAndCreateLoyaltyAccount(registrationPage.emailAddressData);
    }

    public void createANewLoyaltyAccount() throws Throwable {
        if (doesURLContain("checkout/register")) {
            registrationPage.enterRegistrationDetailsAndCreateLoyaltyAccount(registrationPage.emailAddressData);
            waitForAjaxElementNotToBePresent(getWebDriver(),5);
            waitAndClickByElementByJSExecutor(registrationPage.LOYALTY_POP_UP_CLOSE_BUTTON,5);
        } else{
            homePage.navigateToSignInPage();
            logininPage.eyesCheckLoginPage();
            logininPage.clickRegistrationButton();
            logininPage.eyesCheckRegistrationPage();
            registrationPage.enterRegistrationDetailsAndCreateLoyaltyAccount(registrationPage.emailAddressData);
            try {
                waitForItemToBeClickableAndClick(getWebDriver(), 10, registrationPage.LOYALTY_POP_UP_CLOSE_BUTTON);
            } catch (Exception ex) {
                registrationPage.waitAndClickByElementByJSExecutor(registrationPage.LOYALTY_POP_UP_CLOSE_BUTTON, 5);
            }
        }
    }

    public void issRegistration() {
        logininPage.clickRegistrationButton();
        String emailAddress = RandomGenerator.randomEmailAddress(6);
        scenarioContext.setContext(EMAIL_ID, emailAddress);
        registrationPage.enterRegistrationDetailsAndCreateAccount(emailAddress);
        accountDashboardPage.eyesCheckAccountDashboardPage();
    }

    public void createNewAccountForInvite() {
        clickUsingJS(CREATE_PROFILE);
        registrationPage.enterRegistrationDetailsAndCreateAccount(registrationPage.emailAddressData);
    }

    public Float fetchProductPriceFromMiniCart() {
        String productPriceMiniCart = homePage.getProductPrice();
        productPrice = Float.parseFloat(productPriceMiniCart.replaceAll("R", ""));
        scenarioContext.setContext(PRODUCT_PRICE, productPrice);
        return productPrice;
    }

    public void assertDeliveryMethodDetails() {
        float threshold = (float) 399.00;
        if (threshold > productPrice) {
            waitForExpectedElement(STANDARD_DELIVERY);
            assertTrue(paymentPage.isElementDisplayedBySeconds(STANDARD_DELIVERY, 4));
            assertTrue(paymentPage.isElementDisplayedBySeconds(DELIVERY_COST, 5));
        } else {
            waitForExpectedElement(FREE_DELIVERY);
            assertTrue(paymentPage.isElementDisplayedBySeconds(FREE_DELIVERY, 5));
            assertTrue(paymentPage.isElementDisplayedBySeconds(DELIVERY_COST_INCLUDED, 5));
        }
    }

    public void iHavePreviouslySavedMyPaymentMethods() throws InterruptedException {
        avalancheAccountDashboardPage.iClickOnTab("left nav saved payment methods");
        // delete existing cards otherwise you get errors if you modify the card too many times
        if (avalancheAccountDashboardPage.savedPaymentMethods()) {
            avalancheAccountDashboardPage.deleteSavedPaymentmethods();
        }
            paymentPage.checkoutAnAvailableProduct();
            paymentPage.MasterCardOrderOptionSelected();
            paymentPage.enterValidMasterCardDetailsAndSubmit();
            waitForExpectedElement(paymentPage.SAVE_CARD_WORLDPAY_CHK_BOX).click();
            registrationPage.agreeToCheckoutTermsAndConditionsVeloBe();
            paymentPage.clickPlaceOrder();
            waitForElementToAppearAndDisappear(LOADER, 2 , 20);
    }
}
