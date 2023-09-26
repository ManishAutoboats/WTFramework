package com.salmon.test.page_objects.gui.admin;

    import com.salmon.test.framework.PageObject;
    import java.util.List;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebElement;

public class OrderPage extends PageObject {
  public By salesLink = By.cssSelector("#menu-magento-sales-sales >a");
  public By orderLink = By.cssSelector("[data-ui-id=\"menu-magento-sales-sales-order\"]>a");
  public By orderRecordText = By.cssSelector(".col-xs-3>div");
  public By orderNoText = By.cssSelector(".page-title");
  public By viewLink = By.xpath("//*[@id=\"container\"]/div/div[4]/table/tbody/tr[1]/td[10]/a");
  public By orderItemcheckbox = By.cssSelector(".data-grid-checkbox-cell-inner");
  public By orderStatus = By.cssSelector("#order_status");
  public By pageTitleText = By.cssSelector(".page-title");
  public By searchTermInput = By.cssSelector("input[placeholder=\"Search by keyword\"]");
  public By searchBtn= By.cssSelector("button.action-submit");
  public By clearAllBtn= By.cssSelector("button[class='action-tertiary action-clear']");
  /*Order table row value*/
  public By orderId= By.cssSelector(".data-row>td:nth-child(2)>div");
  public By purchasePoint= By.cssSelector(".data-row>td:nth-child(3)>div");
  public By purchaseDate= By.cssSelector(".data-row>td:nth-child(4)>div");
  public By billToName= By.cssSelector(".data-row>td:nth-child(5)>div");
  public By shipToName= By.cssSelector(".data-row>td:nth-child(6)>div");
  public By grandTotalBase= By.cssSelector(".data-row>td:nth-child(7)>div");
  public By grandTotalPurchase= By.cssSelector(".data-row>td:nth-child(8)>div");
  public By status= By.cssSelector(".data-row>td:nth-child(9)>div");
  public By removeSearchFilter= By.cssSelector(".admin__current-filters-list>li>button.action-remove");
 //address info
  public By billingAddress= By.cssSelector("div[class='admin__page-section-item order-billing-address']>address");
  public By shippingAddress= By.cssSelector("div[class='admin__page-section-item order-shipping-address']>address");
  //account info
  public By accountName= By.cssSelector("table[class='admin__table-secondary order-account-information-table'] tr td a");
  public By accountEmail= By.cssSelector("table[class='admin__table-secondary order-account-information-table'] tr td a");

  public String getAccountEmail(){
    List<WebElement> listOfRows = getWebDriver().findElements(accountEmail);
    if(listOfRows.size()>1)
      return listOfRows.get(1).getText();
  return null;
  }
  public String getAccountName(){
    List<WebElement> listOfRows = getWebDriver().findElements(accountName);
    if(listOfRows.size()>0)
     return listOfRows.get(0).getText();
   return null;
  }
  public String getShippingAddress(){
    return waitForExpectedElement(shippingAddress,30).getText();
  }
  public String getBillingAddress(){
    return waitForExpectedElement(billingAddress,30).getText();
  }

  public String getOrderId(){
    return waitForExpectedElement(orderId,30).getText();
  }
  public String getPurchasePoint(){
    return waitForExpectedElement(purchasePoint,30).getText();
  }
  public String getPurchaseDate(){
    return waitForExpectedElement(purchaseDate,30).getText();
  }

  public String getBillToName(){
    return waitForExpectedElement(billToName,30).getText();
  }
  public String getShipToName(){
    return waitForExpectedElement(shipToName,30).getText();
  }
  public String getGrandTotalBase(){
    return waitForExpectedElement(grandTotalBase,30).getText();
  }
  public String getGrandTotalPurchase(){
    return waitForExpectedElement(grandTotalPurchase,30).getText();
  }
  public String getStatus(){
    return waitForExpectedElement(status,30).getText();
  }

  public boolean isPageTitlePresent(){
    return waitForExpectedElement(pageTitleText,30).isDisplayed();
  }
  public String getPageTitle(){
    return waitForExpectedElement(pageTitleText,30).getText();
  }
  public void clickSalesLink()throws Exception{
    waitPageLoad();
    waitForExpectedElement(salesLink,30).click();
  }

  public void clickViewLink(){
    waitForExpectedElement(viewLink,30).click();
  }

  public void clickOrderLink(){
    waitForExpectedElement(orderLink,30).click();
  }

  public boolean isOrderRecordTextPresent(){
    return waitForExpectedElement(orderRecordText,30).isDisplayed();
  }

  public boolean isOrderStatusPresent(){
    return waitForExpectedElement(orderStatus,30).isDisplayed();
  }
  public String getOrderRecordNumbers() {
    return waitForExpectedElement(orderRecordText, 30).getText().replace("records found", "").trim();
  }

  public boolean isOrderNoValid() {
    return waitForExpectedElement(orderNoText, 30).getText().contains("#");
  }

  public void selectItemInOrderTable(int index){
    List<WebElement> listOfRows = getWebDriver().findElements(orderItemcheckbox);
    if(listOfRows.size()> index && index>=0  && listOfRows.size()>0)
    listOfRows.get(index).click();
  }

  public void waitPageLoad() throws Exception {
    Thread.sleep(12000);
  }

  public void enterSearchTerm(String searchTerm) throws Exception {
    waitPageLoad();
    List<WebElement> listOfRows = getWebDriver().findElements(searchTermInput);
    if(listOfRows.size()>0) {
      listOfRows.get(0).clear();
      listOfRows.get(0).sendKeys(searchTerm);
    }
  }

  public void clickSearchBtn() {
    List<WebElement> listOfRows = getWebDriver().findElements(searchBtn);
    if(listOfRows.size()>0)
      listOfRows.get(1).click();
  }
  public void clickRemoveSearchFilter(){
    List<WebElement> listOfRows = getWebDriver().findElements(removeSearchFilter);
    if(listOfRows.size()>0)
      listOfRows.get(0).click();
  }

  public void clickClearAll() throws Exception {
    List<WebElement> listOfRows = getWebDriver().findElements(clearAllBtn);
    if(listOfRows.size()>0) {
        try {
            listOfRows.get(0).click();
        } catch (Exception e) {
            clickElementByQueryJSExecutor(listOfRows.get(0));
        }
    }
    waitPageLoad();
  }
  public boolean isClearAllPresent() {
    List<WebElement> listOfRows = getWebDriver().findElements(clearAllBtn);
    if(listOfRows.size()>0)
     return listOfRows.get(0).isDisplayed();
    return false;
  }

  public void clearAllSearch() throws Exception {
    Thread.sleep(5000);
     if(isClearAllPresent())clickClearAll();
    Thread.sleep(5000);
  }
}
