package com.salmon.test.page_objects.gui.admin;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Pattern;

import static com.salmon.test.page_objects.gui.admin.StorePage.WEB_SITE_DROP_DOWN;

public class CustomerPage extends PageObject {
  public RegistrationPage registrationPage;
  public StorePage storePage;
  public LoginPage loginPage;
  private ScenarioContext scenarioContext;

  public CustomerPage(RegistrationPage registrationPage,StorePage storePage,LoginPage loginPage, ScenarioContext scenarioContext) {
    this.registrationPage=registrationPage;
    this.storePage=storePage;
    this.loginPage=loginPage;
    this.scenarioContext = scenarioContext;
  }

  public By customersLink = By.cssSelector("#menu-magento-customer-customer>a");
  public By allCustomersLink = By.cssSelector("li[data-ui-id='menu-magento-customer-customer-manage']>a");
  public By addNewCustomerBtn = By.cssSelector("#add");
  public By customerRecordText = By.cssSelector(".col-xs-3>div");
  public By customerTableRows = By.cssSelector(".data-row");
  public By customerTableCols = By.cssSelector("[data-repeat-index=\"0\"][class=\"data-row\"]>td");
  public By checkbox = By.cssSelector(".data-grid-checkbox-cell-inner");
  public By customerIds = By.cssSelector("[data-repeat-index='0']>td:nth-child(2)>div");
  public By emailValue = By.cssSelector("[data-repeat-index='0']>td:nth-child(4)>div");
  public By actionBtn = By.cssSelector("div.col-xs-2 button[class=\"action-select\"]:nth-child(1)");
  public By editText = By.xpath("//*[@id=\"container\"]/div/div[2]/div[2]/div[1]/div/div/ul/li[5]/span");
  public By saveCustomerBtn = By.cssSelector("#save");
  public By telephoneInput = By.cssSelector("[name=\"customer[telephone]\"]");
  public By cityzenCardNumberInput = By.cssSelector("input[name=\"customer[citizen_card_number]\"]");
  public By AccountInfoLink = By.cssSelector("#tab_customer");
  public By successMsg = By.cssSelector("[data-ui-id=\"messages-message-success\"]");
  public By pageTitleText = By.cssSelector(".page-title");
  public By searchTermInput = By.cssSelector("#fulltext");
  public By searchBtn= By.cssSelector("button.action-submit");
  public By editLink= By.linkText("Edit");
  private static final By ACCOUNT_INFORMATION_LINK=By.xpath("//a[@id='tab_customer']//span[contains(text(),'Account Information')]");
  private static final By IDSCAN_ON = By.cssSelector("input[name*='idscan']");
  private static final By APPROVED_USER_BUTTON=By.xpath("//button[@id='not_approve']");
  private static final By SUCCESS_MESSAGE = By.cssSelector("div.message.message-success.success");
  private static final By MGM_VERIFICATION_STATUS_FIELD=By.cssSelector("[name='customer[mgm_verification_status]']");
  private static final By IS_AGE_VERIFIED_CHECK_BOX = By.cssSelector("input[name='customer[is_age_verified]']");
  private static final By IS_AGE_VERIFIED_CHECK_BOX_UNCHECKED = By.cssSelector("input[name='customer[is_age_verified]'][value='0'");
  private static final By FILTER_BUTTON = By.cssSelector("#container > div > div.admin__data-grid-header > div:nth-child(1) > div.data-grid-filters-actions-wrap > div > button");
  private static final By APPLY_FILTERS_BUTTON = By.cssSelector("#container > div > div.admin__data-grid-header > div:nth-child(1) > div.admin__data-grid-filters-wrap._show > div > div > button.action-secondary > span");
  private static final By ADMIN_LOADING_MASK = By.cssSelector("div.admin__data-grid-loading-mask");

  public String getPageTitle(){
    return waitForExpectedElement(pageTitleText,30).getText();
  }

  public void clickSaveBtn(){
    waitForExpectedElement(saveCustomerBtn,30).click();
  }
  public void clickAccountInfoLink(){
    waitForExpectedElement(AccountInfoLink,30).click();
  }

  public void clickRecordNumbers(){
    waitForExpectedElement(customerRecordText,30).click();
  }

  public void setTelephone(String newTelephone){
    waitForExpectedElement(telephoneInput,30).sendKeys(newTelephone);
  }
  public void setCityzenCardNumber(String newNumber){
    waitForExpectedElement(cityzenCardNumberInput,30).clear();
    waitForExpectedElement(cityzenCardNumberInput,30).sendKeys(newNumber);
  }
  public boolean isSuccesMsgPresent(){
    return waitForExpectedElement(successMsg,30).isDisplayed();
  }


  public void setEmail(String newEmail) {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("document.getElementById('R0SIFRQ').setAttribute('value', '"+newEmail+"')");
  }

  public String getEmail(){
    return waitForExpectedElement(emailValue,30).getText();
  }


  public void clickCustomersLink() {
    waitForAjaxElementNotToBePresent(getWebDriver(),10);
    try {
      waitForExpectedElement(customersLink, 30).click();
    } catch (Exception e) {
      clickUsingJS(customersLink);
    }
  }

  public void clickAllCustomersLink(){
    waitForAjaxElementNotToBePresent(getWebDriver(),5);
    waitForExpectedElement(allCustomersLink,30).click();
  }

  public void waitPageLoad() throws Exception {
    Thread.sleep(12000);
  }

  public boolean isAddNewCustomerBtnPresent(){
    return waitForExpectedElement(addNewCustomerBtn,30).isDisplayed();
  }

  public boolean isCustomerRecordTextPresent(){
    return waitForExpectedElement(customerRecordText,30).isDisplayed();
  }
  public String getCustomerRecordNumbers() {
    return waitForExpectedElement(customerRecordText, 30).getText().replace("records found", "").trim();
  }

  public int getCustomerTableCount() {
    List<WebElement> listOfRows = getWebDriver().findElements(customerTableRows);
    return listOfRows.size();
     }
  public int getCustomerTableCol() throws Exception {
    waitPageLoad();
    List<WebElement> listOfRows = this.getWebDriver().findElements(customerTableCols);
    return listOfRows.size();
  }
  public void selectItemInCustomerTable(int index)throws Exception {
    waitPageLoad();
    List<WebElement> listOfRows = getWebDriver().findElements(checkbox);
    if(listOfRows.size()> index && index>=0  && listOfRows.size()>0)
      //waitPageLoad();
    listOfRows.get(index).click();
  }

  public void clickEditLink() throws Exception {
    ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
    jse.executeScript("document.querySelector('table[data-role=\"grid\"] th:nth-child(20)').scrollIntoView();");
    Thread.sleep(4000);
    ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, 0)");
    Thread.sleep(4000);
    waitForExpectedElement(editLink,10);
    clickByElementByQueryJSExecutor(editLink);
    waitPageLoad();
  }

  public void verifyAllCustomerIds() {
    for(int index=0;index<getCustomerTableCount();index++) {
      customerIds =By.cssSelector("[data-repeat-index='"+index+"']>td:nth-child(2)>div");
      List<WebElement> listOfRows = this.getWebDriver().findElements(customerIds);
      for (WebElement element : listOfRows){
        Assert.assertNotNull(element.getText());
        Assert.assertTrue(isInteger(element.getText()));
      }

    }
  }

  public void clickActionBtn()throws Exception{
    waitPageLoad();
    waitForExpectedElement(actionBtn,30).click();
  }

  public void selectEditOption()throws Exception{
    waitPageLoad();
    waitForExpectedElement(editText,60).click();
  }
  public static boolean isInteger(String str) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    return pattern.matcher(str).matches();    }

  public void enterSearchTerm(String searchTerm) throws Exception {
    scrollToPageTop();
    waitForElementToAppearAndDisappear(LOADER,1,1);
    List<WebElement> listOfRows = getWebDriver().findElements(searchTermInput);
      listOfRows.get(0).clear();
      listOfRows.get(0).sendKeys(searchTerm);
      waitPageLoad();
  }

  public void clickSearchBtn() {
    List<WebElement> listOfRows = getWebDriver().findElements(searchBtn);
    if(listOfRows.size()>0)
      listOfRows.get(1).click();
  }
  public void clickOnApproveUserButton(){
    waitForAjaxElementNotToBePresent(getWebDriver(),5);
    waitForExpectedElement(APPROVED_USER_BUTTON,5);
    clickByElementByQueryJSExecutor(APPROVED_USER_BUTTON);
    waitForAjaxElementNotToBePresent(getWebDriver(),5);
    waitForExpectedElement(SUCCESS_MESSAGE,5);
  }

  public void userApprovesNewlyCreatedUser(String emailAddress) throws Throwable {
    clickCustomersLink();
    clickAllCustomersLink();
    storePage.removeAnyDefaultSearchFilters();
    waitForElementToDisappear(ADMIN_LOADING_MASK,10);
    enterSearchTerm(emailAddress);
    clickSearchBtn();
    clickEditLink();
    clickOnApproveUserButton();
  }

  public void clickOnAccountDashboardLink(){
    waitForExpectedElement(ACCOUNT_INFORMATION_LINK,40).click();
  }

   public void IDScanIsOn() {
    clickByElementByQueryJSExecutor(IDSCAN_ON);
   }

   public void setSuccessMsg(){
     waitForExpectedElement(SUCCESS_MESSAGE,30).isDisplayed();
   }

   public void userTurnOnIDScan() throws Throwable {
        clickCustomersLink();
        clickAllCustomersLink();
        storePage.removeAnyDefaultSearchFilters();
       enterSearchTerm(registrationPage.emailAddressData);
        clickSearchBtn();
        selectItemInCustomerTable(0);
        clickEditLink();
        clickOnAccountDashboardLink();
        IDScanIsOn();
        storePage.clickOnSaveButton();
        setSuccessMsg();
    }

  public void clickEditInCustomerTableByText(String strLocale) throws Throwable {
    waitPageLoad();
    try{
    clickFirstElementByQueryJSExecutor(By.xpath("//div[@class='data-grid-cell-content'][text()='"+strLocale+"']//following::a[text()='Edit']"));}
    catch (Exception ex){
      storePage.applyStoreViewFilter("Mexico");
      clickFirstElementByQueryJSExecutor(By.xpath("//div[@class='data-grid-cell-content'][text()='"+strLocale+"']//following::a[text()='Edit']"));}
    }

  public void setMGMVerificationStatus() {
      waitForExpectedElement(MGM_VERIFICATION_STATUS_FIELD,10);
      if(!waitForExpectedElement(MGM_VERIFICATION_STATUS_FIELD).getAttribute("value").contains("1")){
        enterDataAndWait(MGM_VERIFICATION_STATUS_FIELD,"1");
        storePage.clickOnSaveButton();
        setSuccessMsg();
      }
  }

  public void loginMagentoAdminAndAssertCustomerMGMVerificationStatus() throws Throwable {
    loginPage.userLoginsMagentoAdminHomePageSuccessfully();
    clickCustomersLink();
    clickAllCustomersLink();
    storePage.removeAnyDefaultSearchFilters();
    enterSearchTerm(UrlBuilder.getMessage("loginValidEmail.key"));
    clickSearchBtn();
    clickEditInCustomerTableByText("Vype Mexico");
    clickOnAccountDashboardLink();
    setMGMVerificationStatus();
  }

  private boolean isAgeVerifiedChecked() {
    try {
      waitForExpectedElement(IS_AGE_VERIFIED_CHECK_BOX_UNCHECKED);
      return false;
    }
      catch (Exception e) {
        return true;
      }
  }
  private void setIsAgeVerifiedCheckBox(boolean requireChecked) throws Exception {
    if (requireChecked && !isAgeVerifiedChecked()
            || !requireChecked && isAgeVerifiedChecked()) {
      waitForExpectedElement(IS_AGE_VERIFIED_CHECK_BOX).click();
    }
  }

    public void loginMagentoAdminAndSetAgeVerified(boolean ageVerified, String user) throws Exception {
      loginPage.userLoginsMagentoAdminHomePageSuccessfully();
      clickCustomersLink();
      clickAllCustomersLink();
      storePage.removeAnyDefaultSearchFilters();
      waitForElementToAppearAndDisappear(LOADER,10,10);
      waitForExpectedElement(FILTER_BUTTON).click();
      clickByElementByQueryJSExecutor(WEB_SITE_DROP_DOWN);
      waitForExpectedElement(WEB_SITE_DROP_DOWN).sendKeys(UrlBuilder.getMessage("adminWebsite"));
      clickByElementByQueryJSExecutor(WEB_SITE_DROP_DOWN);
      clickByElementByQueryJSExecutor(APPLY_FILTERS_BUTTON);
      waitForElementToAppearAndDisappear(LOADER,10,10);
      enterSearchTerm(user);
      clickSearchBtn();
      selectItemInCustomerTable(0);
      clickEditLink();
      if (UrlBuilder.getLocale().equals("velobe")) {
        clickOnApproveUserButton();
      } else {
        clickOnAccountDashboardLink();
        setIsAgeVerifiedCheckBox(ageVerified);
      }
      storePage.clickOnSaveButton();
      waitForElementToAppearAndDisappear(LOADER,5,5);
    }
}
