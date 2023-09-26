package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.gloIt.GloItLoginPage;
import com.salmon.test.step_definitions.gui.MyAccountPageSteps;
import org.openqa.selenium.*;
import java.util.List;
import static org.testng.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class LogininPage extends PageObject {

    private ScenarioContext scenarioContext;
    private PDP pdp;
    public LogininPage(ScenarioContext scenarioContext, PDP pdp) {
        this.scenarioContext = scenarioContext;
        this.pdp = pdp;
    }

    // ELEMENT MAPPING
    private By pageHeading = By.cssSelector("span.base");
    private By logo = By.cssSelector(".logo");

  // Text
  public By pageSubHeading = By.cssSelector("#block-customer-login-heading");
  public final static By PASSWORD_REQUIRED_ERROR_MESSAGE = By.cssSelector("#pass-error");
  public final static By INVALID_EMAIL_ERROR_MESSAGE = By.cssSelector("#email-error");
  public By errorMessage = By.cssSelector("div.message-error.error.message");
  public By elePageTitle = By.cssSelector("span.base");
  public By popupLoginEmail = By.cssSelector("#email");
  public By popupLoginPassword = By.cssSelector("input[name='password']");
  public By popupSubmitLoginFormBtn = By.cssSelector("#send2");
  public By restPasswordText = By.cssSelector("div.forgot-password-container.check.customer-form-group > div > div > span");
  public final static By RESET_PASSWORD_TEXT = By.cssSelector("div.page.messages > div:nth-child(2) > div");

  // intractable elements
  public final static By EMAIL_INPUTBOX = By.cssSelector("input#email.input-text");
  public final static By GLOJP_EMAIL_INPUTBOX = By.cssSelector("#email");
  public final static By PASSWORD_INPUTBOX = By.cssSelector("input#pass.input-text");
  public final static By GLOJP_PASSWORD_INPUTBOX = By.id("password");
  public final static By REMEMBERME_CHECKOUTBOX = By.name("persistent_remember_me");
  public final static By SIGNIN_BUTTON = By.cssSelector("#send2");
  public final static By GLOJP_SIGNIN_BUTTON = By.cssSelector("#btn-login-submit");
  public final static By FORGOTPASSWORD_BUTTON = By.cssSelector("a.action.remind");
  public final static By CREATE_ACCOUNT_BUTTON = By.cssSelector("a.action.create.primary");
  public final static By CREATE_ACCOUNT_BUTTON_JP = By.cssSelector("div#register>a");
  public final static By CREATE_ACCOUNT_BUTTON_KZ = By.cssSelector("main#maincontent div.block-content > div > div > a");
  public final static By CREATE_ACCOUNT_BUTTON_CO = By.cssSelector("main#maincontent div.block-content > div > div > a");
  public final static By CREATE_ACCOUNT_BUTTON_UK = By.cssSelector("div.userLoggout li:nth-child(1) a");
  private final static By SUBMIT_BUTTON = By.cssSelector("button.action.submit.primary,button.submit");
  private final static By CONFIRM_LINK = By.cssSelector("[href*='account/confirmation']");
  private static final By CUSTOMER_NAME_NEXT_TO_PERSON_ICON = By.cssSelector(".logged-in.customer-name");
  private static final By FIRST_NAME_LABEL = By.cssSelector("div#shipping-new-address-form div.field._required._disabled:nth-child(1)");
  private final By createAccountSuccessMessage = By.cssSelector("div.message-success");
  public static final By BASKET_DETAILS_HEADER = By.cssSelector("span.cart_details.pos");
  public static final By MESSAGES_BLOCK = By.cssSelector("div.messages");
  public static final By MESSAGES_ERROR = By.cssSelector(".message-error");
  private static final By M_CREATE_ACCOUNT_LINK = By.cssSelector("li.view-favorite.switcher-option:nth-child(2) > a:nth-child(1)");
  public final static By ACCOUNT_BUTTON = By.cssSelector("span.icon-account");
  private static final By ACCOUNT_INFO_EMAIL = By.cssSelector("p.account-info__email");
  private static final By CONFIRM_DELETE = By.cssSelector("label.label.radio-inline");
  private static final By ACTION_SUBMIT_DELETE = By.cssSelector("button.action.submit.primary");
  private static final By REGISTER_USER_SUCESSFULL = By.cssSelector("div.page.messages > div:nth-child(2) > div > div");
  private static final By CREATE_ACCOUNT_VUSEZA = By.cssSelector("div.login-container.customer-form-group > div.block.block-new-customer > div.block-content > div > div");
  private static final By CONTACT_US_SUCESSFULL = By.cssSelector("div.page.messages > div:nth-child(2) > div > div > div");
  private static final By CONTACT_US_SUCESSFULL_VELOPL = By.cssSelector("div[class='form-success-block'] p[class='contact-bot-content-text']");
    private static final By CONTACT_US_SUCESSFULL_VELOZA = By.cssSelector("#step2 > div.bat-form-text > div > h4");
  private static final By GO_TO_MY_ACCOUNT_LINK_THANK_YOU_PAGE = By.cssSelector(".action.primary.account");
  private static final By ERROR_MESSAGE_ELEMENT= By.xpath("//div[@class='message message-error error']/parent::div");
  private static final By SUBSCRIPTION_PRESENT = By.cssSelector("div.grouped-subscription-tier");
  private static final By SUBSCRIPTION_TOTAL_AMOUNT = By.xpath(" //*[@id=\"maincontent\"]/div[1]/div[1]/div[9]/span[2]/span[2]/span");
  private static final By CONTINUE_BUTTON = By.xpath("//*[@id=\"rdb-popup\"]//span");
  private static final By FACEBOOK_LOGIN_EMAIL_FIELD = By.cssSelector("input#email");
  private static final By FACEBOOK_LOGIN_PWD_FIELD=By.cssSelector("input#pass");
  private static final By FACEBOOK_LOGIN_BUTTON=By.cssSelector("button#loginbutton");
  private static final By CONTINUE_AS_BUTTON=By.xpath("//span[contains(text(),'Continue as')]");
  public static final By SIGN_IN_WITH_FACEBOOK_BUTTON=By.cssSelector("a.btn.btn-block.btn-social.btn-facebook");
  public static final By SIGN_IN_WITH_GOOGLE_BUTTON=By.cssSelector("a.btn.btn-block.btn-social.btn-google");
  public static final By ALLOW_ALL_COOKIES_BUTTONB=By.cssSelector("body > div> div > div > div > div > div > div>button:nth-child(2)");
  public static final By DATA_PROTECTION=By.cssSelector("div.field.social-checks.glo_customer_consents.desktop-margin > div > label > span > b:nth-child(2) > a");
  private static final By REGISTER_PAGE=By.cssSelector(".fieldset.create.info");

    // avalanche
  private static final By LOGIN_PAGE_TITLE = By.cssSelector("head > title");
  private static final By LOGIN_PAGE_EMAIL_ADDRESS_LABEL = By.cssSelector("label[for='email']");
  private static final By LOGIN_PAGE_PASSWORD_LABEL = By.cssSelector("label[for='pass']");
  private static final By LOGIN_PAGE_FORGOT_PASSWORD_LINK = By.cssSelector("a.action.remind > span.icon-arrow-right");
  private static final By LOGIN_PAGE_SIGN_IN_BUTTON = By.cssSelector("button.action.login.primary");
  private static final By LOGIN_PAGE_DONT_HAVE_AN_ACCOUNT_HEADING = By.cssSelector("div.login-container > div.block-new-customer > div.block-title");
  public static final By LOGIN_PAGE_CREATE_ACCOUNT_BUTTON = By.cssSelector("a.create");
  private static final By LOGIN_PAGE_ERROR_MESSAGE = By.cssSelector("div.page.messages");
  private static final By PASSWORD_RESET_HEADING_ZA = By.cssSelector("#html-body > div.page-wrapper > div.page.messages > div:nth-child(2) > div > div > div");
    private static final By PASSWORD_RESET_HEADING = By.cssSelector("div.info-heading");
  private static final By PASSWORD_RESET_EMAIL_TO = By.cssSelector("div.field.note.password-check");
  private static final By LOGIN_FIRST_AND_LAST_NAME = By.cssSelector("p.account-info__name");
  private static final By LOGIN_PAGE_HEADING = By.cssSelector("#block-customer-login-heading");
  public static final By NOTIFICATION_MESSAGE = By.cssSelector("div[role='alert']");
  public static final By NOTIFICATION_MESSAGE_CLOSE_BUTTON = By.cssSelector("div[role='alert'] span.close-btn");

    private HomePage homePage = new HomePage();
    public By LOG_OUT_LINK = By.linkText("Logout");


    public LogininPage(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    public void successfulAccountCreationMessageDisplayed() {
        waitForExpectedElement(createAccountSuccessMessage, 10).isDisplayed();
    }

    public String getFirstNameFiledDisplayed() {
        return waitForExpectedElement(FIRST_NAME_LABEL).getText();
    }
    public String getPageHeading(){
    return waitForExpectedElement(pageHeading).getText();
    }

    public void login(String username, String password) {
        waitForAjaxElementNotToBePresent(webDriver, 5);
        enterDataUsingJS(waitForExpectedElement(EMAIL_INPUTBOX, 15), username);
        enterDataUsingJS(waitForExpectedElement(PASSWORD_INPUTBOX, 15), password);
        clickByElementByQueryJSExecutor(SIGNIN_BUTTON);
        waitForExpectedElement(elePageTitle, 5);
    }
    public void loginWithConfigProperties(String username, String password){
        login(UrlBuilder.getMessage(username),UrlBuilder.getMessage(password));
    }

  public void clickRegistrationButton() {
    try {
      switch (UrlBuilder.getLocale().toLowerCase()) {
        case "kz":
          waitForExpectedElement(CREATE_ACCOUNT_BUTTON_KZ);
          clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON_KZ);
          break;
        case "co":
          waitForExpectedElement(CREATE_ACCOUNT_BUTTON_CO,10);
          clickUsingJS(CREATE_ACCOUNT_BUTTON_CO);
          break;
        case "glode":
        case "velode":
          waitForExpectedElement(CREATE_ACCOUNT_BUTTON);
          clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
          waitForAjaxElementNotToBePresent(getWebDriver(),5);
          break;
        case "fr":
        case "vusefr":
          waitForExpectedElement(CREATE_ACCOUNT_BUTTON,10);
          clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
          if(UrlBuilder.isMobile())
          {
              waitForExpectedElement(REGISTER_PAGE,5);
          }
          break;
        case "vuseuk":
          if(UrlBuilder.isDesktop()){
              try {
            waitForExpectedElement(CREATE_ACCOUNT_BUTTON_UK).click();
            clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);}
          catch (Exception e) {
              waitForExpectedElement(CREATE_ACCOUNT_BUTTON,10);
              clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
              }
          }
          else{
            waitForExpectedElement(CREATE_ACCOUNT_BUTTON,10);
            clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
          }
          break;
        case "vuseco":
          if (UrlBuilder.isMobile() || UrlBuilder.isIPhone()){
            waitForItemToBeClickableAndClick(CREATE_ACCOUNT_BUTTON);
            LOG.info("clicked create checkout button");
          }else {
            waitForExpectedElement(CREATE_ACCOUNT_BUTTON,10);
            clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
          }
          break;
        default:
          waitForExpectedElement(CREATE_ACCOUNT_BUTTON,10);
          clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
      }
    } catch (NoSuchElementException | TimeoutException ex) {
      switch (UrlBuilder.getLocale().toLowerCase()) {
        case "vuseuk":
          homePage.closeVuseAlertIfPresent();
          if(UrlBuilder.isDesktop()){
            hoverOnElement(ACCOUNT_BUTTON);
            clickUsingJS(CREATE_ACCOUNT_BUTTON_UK);
            clickUsingJS(CREATE_ACCOUNT_BUTTON);
          }
          else{
            waitForExpectedElement(CREATE_ACCOUNT_BUTTON,10);
            clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);}
          break;
        default:
          waitForExpectedElement(LOG_OUT_LINK).click();
          homePage.navigateToSignInPage();
          waitForExpectedElement(CREATE_ACCOUNT_BUTTON);
          clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
      }
    } catch (Exception e) {
      homePage.navigateToSignInPage();
      waitForExpectedElement(CREATE_ACCOUNT_BUTTON);
      clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
    }
  }

    public void userEntersValidSigninDetails(String email, String password) throws Throwable {
        Thread.sleep(2000);

        Boolean LoggedInState = getWebDriver().getPageSource().contains("My Account Dashboard");

        if (LoggedInState){
          LOG.info("\n ****** USER APPEARS TO ALREADY BE LOGGED IN ");
        } else {
          LOG.info("\n ****** Attempting to login");
            waitForExpectedElement(EMAIL_INPUTBOX, 10).sendKeys(UrlBuilder.getMessage(email));
            waitForExpectedElement(PASSWORD_INPUTBOX,10).sendKeys(UrlBuilder.getMessage(password));
            clickUsingJS(SIGNIN_BUTTON);
            if (isElementDisplayedBySeconds(CONTINUE_BUTTON, 5)) {
                waitAndClickByElementByJSExecutor(CONTINUE_BUTTON,5);
            }
        }

        if (getWebDriver().getPageSource().contains("Incorrect CAPTCHA")){
          LOG.info("\n ********** LOGIN ERROR *****************");
          LOG.info("\n ********** CAPTCHA STOPPING LOGIN");
          LOG.info("\n ********** ERROR *****************");
        }
        scenarioContext.setContext(Context.EMAIL_ID, UrlBuilder.getMessage(email));
    }

    public void storeUserData() {
         waitForAjaxElementNotToBePresent(webDriver,3);
         String firstAndLastName = waitForExpectedElement(LOGIN_FIRST_AND_LAST_NAME).getText();
         scenarioContext.setContext(Context.FIRST_AND_LAST_NAME, firstAndLastName);
    }


    public void userSignIntoSiteApp(String email, String password) {
        if (doesURLContain("kz/ru")) {
            waitForExpectedElement(EMAIL_INPUTBOX, 10).sendKeys(UrlBuilder.getMessage(email));
            waitForExpectedElement(PASSWORD_INPUTBOX, 10).sendKeys(UrlBuilder.getMessage(password));
            clickUsingJS(SIGNIN_BUTTON);
        }
        if (UrlBuilder.getLocale().equals("glojp")){
          System.out.println("GLO JP LOGIN");
          GloItLoginPage.username = UrlBuilder.getMessage(email);
          waitForExpectedElement(GLOJP_EMAIL_INPUTBOX).sendKeys(UrlBuilder.getMessage(email));
          waitForExpectedElement(GLOJP_PASSWORD_INPUTBOX).sendKeys(UrlBuilder.getMessage(password));
          clickUsingJS(GLOJP_SIGNIN_BUTTON);
      }
    }

    public void assertTextOfRegisteredCustomersIsDisplayed(String expectedText) {
        switch (UrlBuilder.getLocale()) {
            case "glode":
                try {
                    assertTrue(waitForExpectedElement(REGISTER_USER_SUCESSFULL, 10).getText().equalsIgnoreCase(UrlBuilder.getMessage(expectedText)));
                } catch (Exception e) {
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
                }
                break;
            case "vusefr":
                try {
                    assertTrue(waitForExpectedElement(REGISTER_USER_SUCESSFULL, 10).getText().contains(UrlBuilder.getMessage(expectedText)));
                } catch (Exception e) {
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
                }
                break;
            case "kz":
                if(expectedText.equals("successRegistraionMsg.key")) {
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage("successRegistraionMsg.key").toLowerCase()) ||
                            getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage("welcome.key").toLowerCase()) ||
                            getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage("ageSuccessRegistraionMsg.key").toLowerCase()));
                }else
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
                     break;
            case "veloza":
                try {
                    assertTrue(waitForExpectedElement(CONTACT_US_SUCESSFULL_VELOZA, 10).getText().equalsIgnoreCase(UrlBuilder.getMessage(expectedText)));
                } catch (Exception e) {
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
                }
                break;
            case "velopl":
                try {
                    assertTrue(waitForExpectedElement(CONTACT_US_SUCESSFULL_VELOPL, 10).getText().equalsIgnoreCase(UrlBuilder.getMessage(expectedText)));
                } catch (Exception e) {
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
                }
                break;
            default:
            LOG.info("Message being asserted : " + UrlBuilder.getMessage(expectedText));
            try {
                waitForAjaxElementNotToBePresent(getWebDriver(), 30);
                if (UrlBuilder.getLocale().equals("vuseza")||UrlBuilder.getLocale().equals("vuseit"))
                assertTrue("".equalsIgnoreCase(waitForExpectedElement(ERROR_MESSAGE_ELEMENT, 20).getAttribute("style"))) ;
                assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
            } catch (Exception e) {
                if (!UrlBuilder.getLocale().equalsIgnoreCase("ie"))
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
                if (!UrlBuilder.getLocale().equalsIgnoreCase("glode") || UrlBuilder.getLocale().equalsIgnoreCase("mx")) {
                    waitForPage();
                    assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
                }
            }
        }
    }

    public void assertTextOfNewsletterSubscriptionMessageIsDisplayed(String expectedText) {
        waitForAjaxElementNotToBePresent(getWebDriver(),20);
        assertTextOfRegisteredCustomersIsDisplayed(expectedText);
    }

  public void fillInPopupLoginForm(String username, String password) {
    waitClearAndEnterText(popupLoginEmail, username);
    waitClearAndEnterText(popupLoginPassword, password);
  }

  public void submitPopupLoginForm() {
    waitForExpectedElement(popupSubmitLoginFormBtn, 10).click();
  }

  public void clickOnForgotPasswordLink() {
      try {
          waitForExpectedElement(FORGOTPASSWORD_BUTTON,20).click();
      } catch (ElementClickInterceptedException e) {
          clickByElementByQueryJSExecutor(FORGOTPASSWORD_BUTTON);
      }
  }

  public void clickSubmitButton() throws Exception {
      waitForExpectedElement(SUBMIT_BUTTON, 10);
      clickByElementByQueryJSExecutor(SUBMIT_BUTTON);
      Thread.sleep(2000);
  }

  public WebElement getCustomerNameElementNextToPersonIcon() {
      return waitForExpectedElement(CUSTOMER_NAME_NEXT_TO_PERSON_ICON, 5);
  }

    public void eyesCheckLoginPage() {
        if (Props.EYES_ON && EyesCheckpoints.LOGIN_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.LOGIN_PAGE.getName();
            switch (UrlBuilder.getLocale()) {
                case "vuseit":
                case "vuseza":
                case "vusefr":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(HomePage.MESSAGE_ROW));
                    }
                    break;
                case "vusedk":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(HomePage.SLICK_LIST));
                    }
                    break;
                case "pl":
                case "glode":
                case "vusede":
                  if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                      eyes.check(checkpointName, Target.window().fully());
                } else { // mobile
                      eyes.check(EyesCheckpoints.LOGIN_PAGE.getName(),
                    Target.window().fully().ignore(HomePage.MESSAGE_ROW));
                  }
                  break;
                default:
                    if (Props.USE_EYES_GRID) {
                        scrollToShowEntirePage();
                        String bcsHook = "document.documentElement.querySelector('.embeddedServiceHelpButton').style.position = 'unset';\n" +
                                "var chatButton = document.documentElement.querySelector('.embeddedServiceHelpButton .helpButton');\n" +
                                "chatButton.style.position = 'absolute';\n" +
                                "chatButton.style.top = document.documentElement.scrollHeight + 'px';\n" +
                                "chatButton.style.transform = 'translateY(-100%)';\n" +
                                "document.querySelector('.QSISlider.SI_4OxdgNCtc59GVRI_SliderContainer').style.transform = 'translateX(15px)';";
                        eyes.check(checkpointName,Target.window().fully().beforeRenderScreenshotHook(bcsHook).layoutBreakpoints(true));
                    } else
                        eyes.check(checkpointName, Target.window().fully());
            }
        }
    }

    public void eyesCheckRegistrationPage() {
        if (Props.EYES_ON && EyesCheckpoints.REGISTRATION_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.REGISTRATION_PAGE.getName();
            switch (UrlBuilder.getLocale()) {
                case "vuseit":
                case "vuseza":
                case "vusefr":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        scrollToShowEntirePage(); // invoke the second time to solve footer truncate issue
                        eyes.check(checkpointName, Target.window().fully().ignore(HomePage.MESSAGE_ROW));
                    }
                    break;
                case "vusedk":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully().ignore(HomePage.SLICK_LIST));
                    }
                    break;
                case "pl":
                case "glode":
                case "vusede":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(EyesCheckpoints.REGISTRATION_PAGE.getName(),
                                Target.window().fully().ignore(HomePage.MESSAGE_ROW));
                    }
                    break;
                default:
                    if (Props.USE_EYES_GRID) scrollToShowEntirePage();
                    eyes.check(checkpointName, Target.window().fully());
            }
        }
    }

    public void verifyNewAccount(){
        waitForExpectedElement(ACCOUNT_INFO_EMAIL);
        isElementDisplayedBySeconds(ACCOUNT_INFO_EMAIL,10);
        }

    public void DeleteNewAccount(){
        waitForExpectedElement(CONFIRM_DELETE).click();
        waitForExpectedElement(ACTION_SUBMIT_DELETE).click();
    }

    public void clickCreateAccountButton() {
        waitForExpectedElement(CREATE_ACCOUNT_BUTTON, 10);
        clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON);
    }

    public void assertUserHasSubscription(){
        assertTrue(waitForExpectedElement(SUBSCRIPTION_PRESENT, 10).isDisplayed());
    }

    public void assertSubsTotalPrice(){
        assertTrue(waitForExpectedElement(SUBSCRIPTION_TOTAL_AMOUNT, 10).isDisplayed());
    }

    public void assertResetPasswordText(){
        assertTrue(waitForExpectedElement(restPasswordText, 10).isDisplayed());
    }

    public void assertTextOfPasswordReset(String expectedText){
        try {
            assertTrue(waitForExpectedElement(RESET_PASSWORD_TEXT, 10).getText().equalsIgnoreCase(UrlBuilder.getMessage(expectedText)));
        } catch (Exception e) {
            assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
        }

    }

    public boolean isConfirmLinkPresent() {
        return isElementPresent(CONFIRM_LINK, 10);
    }

    public void assertNewWindowOpensWithSocialLoginPage(String strURL) {
        waitForAjaxElementNotToBePresent(getWebDriver(),4);
        switchBetweenWindowTabs(1);
        doesURLContain(UrlBuilder.getMessage(strURL));
    }

    public void enterFacebookEmailAndPasswordAndClickLogin(String strUserName,String strPassword) {
        ClickAllowAllCookiesButton();
        enterDataAndWait(FACEBOOK_LOGIN_EMAIL_FIELD,UrlBuilder.getMessage(strUserName));
        enterDataAndWait(FACEBOOK_LOGIN_PWD_FIELD,UrlBuilder.getMessage(strPassword));
        waitAndClickByElementByJSExecutor(FACEBOOK_LOGIN_BUTTON,3);
        waitForAjaxElementNotToBePresent(getWebDriver(),5);
        if(getWebDriver().getWindowHandles().size()>1){
            if(getWebDriver().findElements(CONTINUE_AS_BUTTON).size()>0)
                clickByElementByQueryJSExecutor(CONTINUE_AS_BUTTON);
        }
        switchBetweenWindowTabs(0);
        waitForExpectedElement(MyAccountPageSteps.VUSE_FIRST_NAME,5);
    }

    public void validateLoginPageHeading(String language) {
        String expectedHeading = UrlBuilder.getMessage("loginHeading-" + language);
        if (UrlBuilder.getLocale().equals("velobe") || UrlBuilder.getLocale().equals("velopl") ) {
            String actualHeading = waitForExpectedElement(LOGIN_PAGE_HEADING).getText();
            assertThat(expectedHeading.equalsIgnoreCase(actualHeading))
                    .as("ERROR validateLoginPageHeading: expected page heading was " + expectedHeading + " but I got " + actualHeading).isTrue();
        } else {
            checkPageTitleContains(expectedHeading);
        }
    }
    public void verifyLoginPageHeading(String language) {
        String expectedHeading = UrlBuilder.getMessage("loginPageHeading-" + language);
        checkPageTitleContains(expectedHeading);
    }
//Remove once eyes test is completed.
    public void validateLoginPageHeading() {
        String expectedHeading = UrlBuilder.getMessage("loginHeading");
        String actualheading = waitForExpectedElement(LOGIN_PAGE_TITLE).getText();
        assertThat(expectedHeading.equalsIgnoreCase(actualheading))
                .as("ERROR validateLoginPageHeading: expected page heading was " + expectedHeading + " but I got " + actualheading).isTrue();
    }
    //Remove once eyes test is completed.
    public void validateLoginFieldLabels(String language) {
        String expectedEmailAddressLabel = UrlBuilder.getMessage("loginEmailLabel-" + language);
        String actualEmailAddressLabel = waitForExpectedElement(LOGIN_PAGE_EMAIL_ADDRESS_LABEL).getText();
        assertThat(expectedEmailAddressLabel.equalsIgnoreCase(actualEmailAddressLabel))
                .as("ERROR validateLoginFieldLabels: expected email address label was " + expectedEmailAddressLabel + " but i got " + actualEmailAddressLabel).isTrue();
        String expectedPasswordLabel = UrlBuilder.getMessage("loginPasswordLabel-" + language);
        String actualPasswordLabel = waitForExpectedElement(LOGIN_PAGE_PASSWORD_LABEL).getText();
        assertThat(expectedPasswordLabel.equalsIgnoreCase(actualPasswordLabel))
                .as("ERROR validateLoginFieldLabels: expected password label was " + expectedPasswordLabel + " but i got " + actualPasswordLabel).isTrue();
        String expectedPasswordResetLinkText = UrlBuilder.getMessage("loginForgottenPasswordLinkText-" + language);
        String actualPasswordResetLinkText = waitForExpectedElement(LOGIN_PAGE_FORGOT_PASSWORD_LINK).getText();
        assertThat(expectedPasswordResetLinkText.equalsIgnoreCase(actualPasswordResetLinkText))
                .as("ERROR validateLoginFieldLabels: expected password reset link text was " + expectedPasswordResetLinkText + " but i got " + actualPasswordResetLinkText).isTrue();
        String expectedSignInButtonText = UrlBuilder.getMessage("loginButtonLabel-" + language);
        String actualSignInButtonText = waitForExpectedElement(LOGIN_PAGE_SIGN_IN_BUTTON).getText();
        assertThat(expectedSignInButtonText.equalsIgnoreCase(actualSignInButtonText))
                .as("ERROR validateLoginFieldLabels: expected sign in button text was " + expectedSignInButtonText + " but i got " + actualSignInButtonText).isTrue();
        String expectedDontHaveAnAccountHeading = UrlBuilder.getMessage("loginDontHaveAccountHeading-" + language);
        String actualDontHaveAnAccountHeading = waitForExpectedElement(LOGIN_PAGE_DONT_HAVE_AN_ACCOUNT_HEADING).getText();
        assertThat(expectedDontHaveAnAccountHeading.equalsIgnoreCase(actualDontHaveAnAccountHeading))
                .as("ERROR validateLoginFieldLabels: expected don't have an account heading was " + expectedDontHaveAnAccountHeading + " but i got " + actualDontHaveAnAccountHeading).isTrue();
        String expectedCreateAccountButtonText = UrlBuilder.getMessage("loginCreateAccountButtonLabel-" + language);
        String actualCreateAccountButtonText = waitForExpectedElement(LOGIN_PAGE_CREATE_ACCOUNT_BUTTON).getText();
        assertThat(expectedCreateAccountButtonText.equalsIgnoreCase(actualCreateAccountButtonText))
                .as("ERROR validateLoginFieldLabels: expected create account button text was " + expectedCreateAccountButtonText + " but i got " + actualCreateAccountButtonText).isTrue();
    }
    //Remove once eyes test is completed.
    public void validateLoginFieldLabels() {
        String expectedEmailAddressLabel = UrlBuilder.getMessage("loginEmailLabel");
        String actualEmailAddressLabel = waitForExpectedElement(LOGIN_PAGE_EMAIL_ADDRESS_LABEL).getText();
        assertThat(expectedEmailAddressLabel.equalsIgnoreCase(actualEmailAddressLabel))
                .as("ERROR validateLoginFieldLabels: expected email address label was " + expectedEmailAddressLabel + " but i got " + actualEmailAddressLabel).isTrue();
        String expectedPasswordLabel = UrlBuilder.getMessage("loginPasswordLabel");
        String actualPasswordLabel = waitForExpectedElement(LOGIN_PAGE_PASSWORD_LABEL).getText();
        assertThat(expectedPasswordLabel.equalsIgnoreCase(actualPasswordLabel))
                .as("ERROR validateLoginFieldLabels: expected password label was " + expectedPasswordLabel + " but i got " + actualPasswordLabel).isTrue();
        String expectedPasswordResetLinkText = UrlBuilder.getMessage("loginForgottenPasswordLinkText");
        String actualPasswordResetLinkText = waitForExpectedElement(LOGIN_PAGE_FORGOT_PASSWORD_LINK).getText();
        assertThat(expectedPasswordResetLinkText.equalsIgnoreCase(actualPasswordResetLinkText))
                .as("ERROR validateLoginFieldLabels: expected password reset link text was " + expectedPasswordResetLinkText + " but i got " + actualPasswordResetLinkText).isTrue();
        String expectedSignInButtonText = UrlBuilder.getMessage("loginButtonLabel");
        String actualSignInButtonText = waitForExpectedElement(LOGIN_PAGE_SIGN_IN_BUTTON).getText();
        assertThat(expectedSignInButtonText.equalsIgnoreCase(actualSignInButtonText))
                .as("ERROR validateLoginFieldLabels: expected sign in button text was " + expectedSignInButtonText + " but i got " + actualSignInButtonText).isTrue();
        String expectedDontHaveAnAccountHeading = UrlBuilder.getMessage("loginDontHaveAccountHeading");
        String actualDontHaveAnAccountHeading = waitForExpectedElement(LOGIN_PAGE_DONT_HAVE_AN_ACCOUNT_HEADING).getText();
        assertThat(expectedDontHaveAnAccountHeading.equalsIgnoreCase(actualDontHaveAnAccountHeading))
                .as("ERROR validateLoginFieldLabels: expected don't have an account heading was " + expectedDontHaveAnAccountHeading + " but i got " + actualDontHaveAnAccountHeading).isTrue();
        String expectedCreateAccountButtonText = UrlBuilder.getMessage("loginCreateAccountButtonLabel");
        String actualCreateAccountButtonText = waitForExpectedElement(LOGIN_PAGE_CREATE_ACCOUNT_BUTTON).getText();
        assertThat(expectedCreateAccountButtonText.equalsIgnoreCase(actualCreateAccountButtonText))
                .as("ERROR validateLoginFieldLabels: expected create account button text was " + expectedCreateAccountButtonText + " but i got " + actualCreateAccountButtonText).isTrue();
    }
    //Remove once eyes test is completed.
    public void emailAndPasswordFieldsDisplayed() {
        waitForExpectedElement(EMAIL_INPUTBOX).isDisplayed();
        waitForExpectedElement(PASSWORD_INPUTBOX).isDisplayed();
    }
    //Remove once eyes test is completed.
    public void LoginButtonDisplayed() {
        assertThat(waitForExpectedElement(LOGIN_PAGE_SIGN_IN_BUTTON).isDisplayed())
                .as("ERROR LoginButtonDisplayed: sign in button is not displayed").isTrue();
    }
    //Remove once eyes test is completed.
    public void forgotPasswordLinkDisplayed() {
        assertThat(waitForExpectedElement(LOGIN_PAGE_FORGOT_PASSWORD_LINK).isDisplayed())
                .as("ERROR forgotPasswordLinkDisplayed: forgot password link not displayed").isTrue();
    }
    //Remove once eyes test is completed.
    public void createAccountButtonDisplayed() {
        assertThat(waitForExpectedElement(LOGIN_PAGE_SIGN_IN_BUTTON).isDisplayed())
                .as("ERROR createAccountButtonDisplayed: create account button not displayed").isTrue();
    }

    public void loginWithMissingFields(List<List<String>> fields) {
        String email = "";
        String password = "";
        String language = scenarioContext.getContext(Context.LANGUAGE).toString();
        for (List<String> field : fields) {
            switch (field.get(0)) {
                case "email":
                    email = "";
                    password = UrlBuilder.getMessage(("loginValidPassword.key"));
                    login(email, password);
                    assertThat(waitForExpectedElement(INVALID_EMAIL_ERROR_MESSAGE).getText().equals(UrlBuilder.getMessage("requiredFieldError-" + language)))
                            .as("ERROR loginWithMissingFields: error message incorrect for email omission ").isTrue();
                    break;
                case "password":
                    email = UrlBuilder.getMessage("loginValidUser");
                    password = "";
                    login(email, password);
                    assertThat(waitForExpectedElement(PASSWORD_REQUIRED_ERROR_MESSAGE).getText().equals(UrlBuilder.getMessage("requiredFieldError-" + language)))
                            .as("ERROR loginWithMissingFields: error message incorrect for password omission ").isTrue();
                    break;
                case "both":
                    email = "";
                    password = "";
                    login(email, password);
                    assertThat(waitForExpectedElement(INVALID_EMAIL_ERROR_MESSAGE).getText().equals(UrlBuilder.getMessage("requiredFieldError-" + language)))
                            .as("ERROR loginWithMissingFields: error message incorrect for email omission ").isTrue();
                    assertThat(waitForExpectedElement(PASSWORD_REQUIRED_ERROR_MESSAGE).getText().equals(UrlBuilder.getMessage("requiredFieldError-" + language)))
                            .as("ERROR loginWithMissingFields: error message incorrect for password omission ").isTrue();
                    break;
                default:
                    assertThat(true).as("ERROR loginWithMissingFields: invalid field supplied " + field.get(0));
            }
        }
    }

    public void validateLoginErrorMessage() {
        String expectedLoginErrorMessage = UrlBuilder.getMessage("loginError-" + scenarioContext.getContext(Context.LANGUAGE).toString());
        String actualLoginErrorMessage = waitForExpectedElement(LOGIN_PAGE_ERROR_MESSAGE).getText();
        assertThat(expectedLoginErrorMessage)
                .as("ERROR validateLoginErrorMessage: expected error message was "+expectedLoginErrorMessage+" but i fot "+actualLoginErrorMessage).isEqualToIgnoringCase(actualLoginErrorMessage);
    }

    public void validateSuccessfulregistration() {
        String actualMessage = waitForExpectedElement(MESSAGES_BLOCK).getText();
        String expectedMessage = UrlBuilder.getMessage("registrationSuccessMessage" + "-" + scenarioContext.getContext(Context.LANGUAGE).toString());
        assertThat(actualMessage.contains(expectedMessage))
                .as("ERROR validateSuccessfulregistration: expected message was "+expectedMessage+" but I got "+actualMessage).isTrue();
        assertThat(waitForExpectedElement(LOGIN_PAGE_SIGN_IN_BUTTON).isEnabled()).as("ERROR validateSuccessfulregistration: sign in button is not enabled").isTrue();
    }

    public void validateEmailRequiredMessage() {
        String expectedEmailRequiredErrorMessage = UrlBuilder.getMessage("emailRequiredErrorMessage-" + scenarioContext.getContext(Context.LANGUAGE));
        String actualEmailRequiredErrorMessage = waitForExpectedElement(INVALID_EMAIL_ERROR_MESSAGE).getText();
        assertThat(expectedEmailRequiredErrorMessage.equalsIgnoreCase(actualEmailRequiredErrorMessage))
                .as("ERROR validateEmailRequiredMessage: expected error message was " + expectedEmailRequiredErrorMessage + " but I got " + actualEmailRequiredErrorMessage).isTrue();
    }

    public void validatePasswordRequiredMessage() {
        String expectedPasswordRequiredErrorMessage = UrlBuilder.getMessage("passwordRequiredErrorMessage-" + scenarioContext.getContext(Context.LANGUAGE));
        String actualPasswordRequiredErrorMessage = waitForExpectedElement(PASSWORD_REQUIRED_ERROR_MESSAGE).getText();
        assertThat(expectedPasswordRequiredErrorMessage.equalsIgnoreCase(actualPasswordRequiredErrorMessage))
                .as("ERROR validateEmailRequiredMessage: expected error message was " + expectedPasswordRequiredErrorMessage + " but I got " + actualPasswordRequiredErrorMessage).isTrue();
    }

    public void validatePasswordResetConfirmation() {
        if (UrlBuilder.getLocale().equals("veloza") ) {
            String expectedHeading = UrlBuilder.getMessage("resetPasswordHeading-" + scenarioContext.getContext(Context.LANGUAGE)).replace("%EAMIL%", String.valueOf(scenarioContext.getContext(Context.EMAIL_ID)));
            String actualLoginErrorMessage = waitForExpectedElement(PASSWORD_RESET_HEADING_ZA).getText();
            assertThat(expectedHeading).as("ERROR validatePasswordResetInstructions: expected heading was "+expectedHeading+" but i got "+actualLoginErrorMessage)
                    .isEqualTo(actualLoginErrorMessage);
        } else {
            String expectedHeading = UrlBuilder.getMessage("resetPasswordHeading-" + scenarioContext.getContext(Context.LANGUAGE));
        String actualHeading = waitForExpectedElement(PASSWORD_RESET_HEADING).getText();
        assertThat(expectedHeading)
                .as("ERROR validatePasswordResetInstructions: expected heading was "+expectedHeading+" but i got "+actualHeading)
                .isEqualTo(actualHeading);
    }}

    public void ClickAllowAllCookiesButton(){
        if(isElementDisplayedBySeconds(ALLOW_ALL_COOKIES_BUTTONB,20)){
            clickByElementByQueryJSExecutor(ALLOW_ALL_COOKIES_BUTTONB);
        }
    }
}
