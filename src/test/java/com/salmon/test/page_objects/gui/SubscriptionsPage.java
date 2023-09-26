package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.PLP;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.page_objects.gui.constants.Locale.valueOf;


public class SubscriptionsPage extends PageObject {


  private static final By SUBSCRIPTION_PRODUCT_DETAILS = By.cssSelector(".product.details.product-item-details");
  private static final By SUBSCRIPTION_PRODUCT_NAME = By.cssSelector(".product.name");
  private static final By SUBSCRIPTION_PRODUCT_COLOUR = By.cssSelector("div.swatch-option.color");
  private static final By SUBSCRIPTION_PRODUCT_SELECT_BTN = By.cssSelector("button.select.action.tocart.secondary");
  private static final By SUBSCRIPTION_COMMITMENT_FREQUENCY = By.cssSelector(".picker-frequency.select-container .contractSelectionButton");
  private static final By SUBSCRIPTION_COMMITMENT_PACKS_AMOUNT = By.cssSelector(".picker-packs.select-container .contractSelectionButton");
  private static final By SUBSCRIPTION_PRODUCT_FLAVOUR = By.cssSelector(".consumable.product-item-info");
  private static final By FLAVOUR_NAME = SUBSCRIPTION_PRODUCT_NAME;
  private static final By FLAVOUR_COLOUR = By.cssSelector(".swatch-option.text");
  private static final By QUANTITY = By.cssSelector(".input-text.qty");
  private static final By QUANTITY_ADD_BUTTON = By.cssSelector(".add.action.tocart.secondary");
  private static final By SUBSCRIPTION_PAUSE_BUTTON = By.cssSelector(".action.secondary.pause");
  private static final By SUCCESS_MSG_CSS = By.cssSelector(".message-success.success.message");
  private static final By SUBS_ERROR_POPUP = By.cssSelector(".modal-popup.confirm._show");
  private static final By MSG_ON_SUBS_ERROR_POPUP = By.cssSelector(".modal-content");
  private static final By OK_BUTTON_ON_SUBS_ERROR_POPUP = By.cssSelector(".action-primary.action-accept");
  private static final By VYPE_IE_CHECKOUT_BUTTON = By.cssSelector("a[href='/ie/en/checkout/cart/']");
  private static final By ADD_TO_CART = By.cssSelector("#product-addtocart-button,button.action.tocart.primary,action.primary.tocart");
  private static final By VYPE_UK_CHECKOUT_BUTTON = By.cssSelector("div.pagebuilder-column.subscriptions-steps-image-text:nth-child(1) div:nth-child(4) div:nth-child(1) > a.pagebuilder-button-primary");
  private static final By DEFAULT_CHECKOUT_BUTTON = By.cssSelector("div:nth-child(4) div:nth-child(1) > a.pagebuilder-button-primary");
  public static final By SELECT_FLAVORS_BUTTON = By.cssSelector(".widget.block.block-static-block a.pagebuilder-button-primary");
  public static final By SELECT_FLAVORS_BUTTON_VUSEIT = By.cssSelector("div.subs-border-radius-down a.pagebuilder-button-secondary");
  public static final By SELECT_FLAVORS_HEADER = By.cssSelector(".block.block-static-block.widget > div:nth-of-type(1) > div h3");
  public static final By SUBSCRIPTION_DESCRIPTION = By.cssSelector(".pagebuilder-column.hero-banner-column > div:nth-child(1)");
  public static final By SUBSCRIPTION_DESCRIPTION_VUSEIT = By.cssSelector("div.pagebuilder-column > div.subscription-heading");
  public void selectFirstSubColour() {
    clickByElementByQueryJSExecutor(By.cssSelector("div.swatch-option.color"));
  }

  public void selectContinue() {
    waitForExpectedElement(By.cssSelector("#maincontent > div > div > div.subscriptions-builder > div.steps-actions > div:nth-child(2) > button"),10);
    clickByElementByQueryJSExecutor(By.cssSelector("#maincontent > div > div > div.subscriptions-builder > div.steps-actions > div:nth-child(2) > button"));
  }

  // TODO build Subs Obj Model to support automation implementation
  public static class SubsModel {
    public String deviceName;
    public String deviceColour;
    public ArrayList<String> deviceFlavours;
    public static ArrayList<String> savingsTableData;
    public String normalCost;
    public String discountedCode;
    public String savings;
  }

  // ELEMENT MAPPING
  private By pageHeading = By.cssSelector("h1");

  //GlobalSubs Mapping
  private final static By GLOBAL_SUBS_GET_FLAVOURS = By.cssSelector("#maincontent > div > div > div.feature-block.subscribe-top-row.background-image-5f0daaf140ba8 > div > div > div:nth-child(1) > div:nth-child(3) > div > a");
  private final static By GLOBAL_SUBS_PLP_CTA_BANNER = By.cssSelector("[class='item product product-item flavour-crisp_mint vype_system_type-n-a vype_strength-0mg-ml vype_strength-6mg-ml vype_strength-12mg-ml vype_strength-18mg-ml'] .subscriptionpro-label");

  // Universal subs elements
  private By subsStepsWrapper = By.cssSelector("div.steps-wrapper");
  // Navigation of subs
  private By subsStepsNavigation = By.cssSelector("div.steps-navigator");
  // subs footer bar
  private By subsFooterBar = By.cssSelector("div.steps-actions");
  // main page div
  private By subsMainContentBlock = By.cssSelector("div.picker-devices");

  // Buttons
  // Step 1
  private By BUTTON_step1DeviceButton = By.cssSelector("#sticky-wrapper > div > div > div > button:nth-child(1)");
  private By BUTTON_step2Flavours = By.cssSelector("#sticky-wrapper > div > div > div > button:nth-child(2)");
  private By BUTTON_step3Summary = By.cssSelector("#sticky-wrapper > div > div > div > button:nth-child(3)");
  // Footer Button
  private By BUTTON_startBuildingMySubscription = By.cssSelector("button.btn-small.action.primary");
  private By BUTTON_proceedToStep2 = By.cssSelector("button.btn-small.action.primary");
  private By BUTTON_proceedToStep3 = By.cssSelector("button.btn-small.action.primary");

  private By BUTTON_addFirstFlavour = By.cssSelector("button.add.action.tocart.secondary");

  // PRODUCTS
  private By BUTTON_selectProduct = By.cssSelector("button.select.action.tocart.secondary");
  private By BUTTON_continue = By.cssSelector("#maincontent > div > div > div.subscriptions-builder > div.steps-actions > div:nth-child(3) > button");

  // Summary Page
  private By savingsCSSBlock = By.cssSelector("div.discount-breakdown-wrapper");
  private By BUTTON_addToBasket = By.cssSelector("#maincontent > div > div > div.subscriptions-builder > div.steps-actions > div:nth-child(4) > div > button");
  public static final By ADD_TO_BASKET_PL = By.cssSelector("#product-addtocart-button");

  // Table
  private By summaryTable = By.cssSelector(".summary-item table");

  public void clickAddToBasket() {
    switch (UrlBuilder.getLocale()){
      case "pl":
        waitForExpectedElement(ADD_TO_BASKET_PL,20);
        clickByElementByQueryJSExecutor(ADD_TO_BASKET_PL);
      break;
      case "glode":
        waitForExpectedElement(ADD_TO_CART,20);
        clickByElementByQueryJSExecutor(ADD_TO_CART);
        break;
      default:
        clickByElementByQueryJSExecutor(BUTTON_addToBasket);
    }
  }

  public void selectFirstSubProduct(){
    clickByElementByQueryJSExecutor(BUTTON_selectProduct);
  }

  public void clickContinueButton(){
    clickByElementByQueryJSExecutor(BUTTON_continue);
  }

  public void addFlavToOrder() {
    clickByElementByQueryJSExecutor(BUTTON_addFirstFlavour);
  }

  public void pressProceedToStep2Button() {
    clickByElementByQueryJSExecutor(BUTTON_proceedToStep2);
  }

  public void pressProceedToStep3Button() {
    clickByElementByQueryJSExecutor(BUTTON_proceedToStep3);
  }

  public void isPageLoaded(){
    waitForPage();
  }

  public String geth1Title(){
    return waitForExpectedElement(pageHeading).getText();
  }

  public boolean isMainSubsDivPresent(){
    return waitForExpectedElement(subsMainContentBlock).isDisplayed();
  }

  // first button should always be clickable / displayed
  public boolean step1ButtonDisplayed(){
     return waitForExpectedElement(BUTTON_step1DeviceButton).isDisplayed();
  }

  public boolean step2ButtonDisplayed(){
    return waitForExpectedElement(BUTTON_step2Flavours).isDisplayed();
  }

  public boolean step3ButtonDisplayed(){
    return waitForExpectedElement(BUTTON_step3Summary).isDisplayed();
  }

  public boolean startMySubsButtonDisplayed(){
    return waitForExpectedElement(BUTTON_startBuildingMySubscription).isDisplayed();
  }

  // Checks for presents of subWrapper div, Navigation and footer.
  public boolean arePageElementsPresentAsExpected() throws InterruptedException {
    Thread.sleep(2000);
    Boolean pageElementsPresent = false;
    if (waitForExpectedElement(subsStepsWrapper).isDisplayed() && waitForExpectedElement(subsStepsNavigation).isDisplayed()
        && waitForExpectedElement(subsFooterBar).isDisplayed()){
      pageElementsPresent = true;
    }
    return pageElementsPresent;
  }

  public void pressStartBuildingMySubscriptionButton(){
    clickByElementByQueryJSExecutor(BUTTON_startBuildingMySubscription);
  }

  //GlobalSubs Functions
  public void clickGloSubGetFlavours(){clickByElementByQueryJSExecutor(GLOBAL_SUBS_GET_FLAVOURS);}
  public void clickGloSubPLPCTABanner(){clickByElementByQueryJSExecutor(GLOBAL_SUBS_PLP_CTA_BANNER);}

  public void clickPauseLink() {
    waitForExpectedElement(SUBSCRIPTION_PAUSE_BUTTON);
    clickByElementByQueryJSExecutor(SUBSCRIPTION_PAUSE_BUTTON);
  }

  public String getSuccessMsgText() {
    return waitForExpectedElement(SUCCESS_MSG_CSS).getText();
  }

  public void selectProductColourAndClickSelectButtonFor(String productName) {
    getWebDriver().findElements(SUBSCRIPTION_PRODUCT_DETAILS).stream()
            .filter(webElement -> webElement.findElement(SUBSCRIPTION_PRODUCT_NAME).getText().equals(productName))
            .map(webElement -> webElement.findElement(SUBSCRIPTION_PRODUCT_COLOUR))
            .findFirst().ifPresent(this::clickElementByQueryJSExecutor);

    getWebDriver().findElements(SUBSCRIPTION_PRODUCT_DETAILS).stream()
            .filter(ele -> ele.findElement(SUBSCRIPTION_PRODUCT_NAME).getText().equals(productName))
            .map(ele -> ele.findElement(SUBSCRIPTION_PRODUCT_SELECT_BTN))
            .findFirst().ifPresent(this::clickElementByQueryJSExecutor);
  }

  public void selectCommitmentAndCommitmentAmount(String commitment, String commitmentAmount) {
    getWebDriver().findElements(SUBSCRIPTION_COMMITMENT_FREQUENCY).stream()
            .filter(webElement -> webElement.getText().equals(commitment))
            .findFirst().ifPresent(WebElement::click);

    getWebDriver().findElements(SUBSCRIPTION_COMMITMENT_PACKS_AMOUNT).stream()
            .filter(webElement -> webElement.getText().equals(commitmentAmount))
            .findFirst().ifPresent(WebElement::click);
  }

  public void selectFlavourAndQuantity(String flavour, String qty) {
    getWebDriver().findElements(SUBSCRIPTION_PRODUCT_FLAVOUR).stream()
            .filter(webElement -> webElement.findElement(FLAVOUR_NAME).getText().contains(flavour))
            .map(flavourEle -> flavourEle.findElement(FLAVOUR_COLOUR))
            .findAny().ifPresent(WebElement::click);

    getWebDriver().findElements(SUBSCRIPTION_PRODUCT_FLAVOUR).stream()
            .filter(webElement -> webElement.findElement(FLAVOUR_NAME).getText().contains(flavour))
            .findFirst()
            .ifPresent(flavourEle -> {
              WebElement quantity = flavourEle.findElement(QUANTITY);
              quantity.click(); quantity.clear(); quantity.sendKeys(qty);
              flavourEle.findElement(QUANTITY_ADD_BUTTON).click();
            });
  }

  public WebElement getSubsSummaryTable() {
    return waitForExpectedElement(summaryTable, 10);
  }

  public boolean isSubsErrorPopUpDisplayed() {
    return waitForExpectedElement(SUBS_ERROR_POPUP).isDisplayed();
  }

  public String getMessageDisplayedOnSubsErrorPopup() {
    return waitForExpectedElement(SUBS_ERROR_POPUP).findElement(MSG_ON_SUBS_ERROR_POPUP).getText();
  }

  public void clickButtonOnSubsErrorPopUp() {
    waitForExpectedElement(SUBS_ERROR_POPUP).findElement(OK_BUTTON_ON_SUBS_ERROR_POPUP).click();
  }

  public void clickCheckoutButton () {
    switch (valueOf(getLocale().toUpperCase())) {
      case IE:
        waitForExpectedElement(VYPE_IE_CHECKOUT_BUTTON);
        WebElement element = getWebDriver().findElement(VYPE_IE_CHECKOUT_BUTTON);
        JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
        executor.executeScript("arguments[0].click();", element);
        break;
      case VUSEUK:
        waitForExpectedElement(VYPE_UK_CHECKOUT_BUTTON).click();
        break;
      default:
        waitForExpectedElement(DEFAULT_CHECKOUT_BUTTON).click();
    }
  }

  public List<String> getExpectedSubTitlesSubscriptionCondition() {
    List<String> subTitleList = new ArrayList<>();
    String[] storeSubTitle = new String[10];
    for(int i=0;i<10;i++){
      storeSubTitle[i]="subscriptionConditionSubTitle"+i+".Key";
    }
   Collections.addAll(subTitleList,storeSubTitle);
    return subTitleList;
  }
}
