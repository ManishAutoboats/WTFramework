package com.salmon.test.page_objects.gui.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.salmon.test.framework.PageObject;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ProductPage extends PageObject {

    StorePage storePage = new StorePage();

    public By catalogLink = By.cssSelector("#menu-magento-catalog-catalog>a");
    public By productLink = By.cssSelector("li[data-ui-id='menu-magento-catalog-catalog-products']>a");

    public By pageTitleText = By.cssSelector(".page-title");
    public By productRecordText = By.cssSelector(".col-xs-3>div");
    public By checkbox = By.cssSelector(".data-grid-checkbox-cell-inner");
    //product detail
    public String seoLinkData = "input[name=\"mageplazaSeoData\"]";
    public By editLink= By.xpath("//*[@id=\"container\"]/div/div[4]/table/tbody/tr[17]/td[18]/a");
    public final static By RELATED_UPSELL_CROSSSELL_SECTION=By.xpath("//span[text()='Related Products, Up-Sells, Cross-Sells and DeviceTrial']");
    public final static By RELATED_PRODUCT_ROWS=By.xpath("//div[@class='fieldset-wrapper-title']//following::span[text()='Related Products']//following::tr[1]");
    public final static By UPSELL_PRODUCT_ROWS=By.xpath("//div[@class='fieldset-wrapper-title']//following::span[text()='Up-Sell Products']//following::tr[1]");
    public final static By CROSSSELL_PRODUCT_ROWS=By.xpath("//div[@class='fieldset-wrapper-title']//following::span[text()='Cross-Sell Products']//following::tr[1]");
    public By subscribeProWrapper = By.cssSelector("div[data-index='subscribe-pro'] strong");
    public By subscribeSwitchInput = By.cssSelector("input[name='product[subscription_enabled]']");
    public By subscribeSwitchWrapper = By.cssSelector("div[data-index='subscription_enabled'] div[class='admin__actions-switch']");
    public By subscribeSwitchDisabled = By.cssSelector("input[name='product[subscription_enabled]']:disabled");
    public By useDefaultSubsCheckbox = By.cssSelector("input[name='use_default[subscription_enabled]']");
    public By saveButton = By.cssSelector("#save-button");
    public By successSaveBlock = By.cssSelector("div[data-ui-id='messages-message-success']");

    public boolean isPageTitlePresent(){
        return waitForExpectedElement(pageTitleText,30).isDisplayed();
    }
    public String getPageTitle(){
        return waitForExpectedElement(pageTitleText,30).getText();
    }

    public String getProductUrl(){
        String json = getElementValueJSExecutor(seoLinkData);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Pattern pattern = Pattern.compile(".*s\\/([^']*)\\/key.*");
        Matcher matcher = pattern.matcher(jsonObject.get("link").getAsString());

        if (matcher.find())
        {
            LOG.info(matcher.group(1));
        }
        return matcher.group(1);
    }

    public boolean isProductRecordTextPresent(){
        return waitForExpectedElement(productRecordText,30).isDisplayed();
    }
    public String getOrderRecordNumbers() {
        return waitForExpectedElement(productRecordText, 30).getText().replace("records found", "").trim();
    }
    public void selectItemInCustomerTable(int index)throws Exception {
        waitPageLoad();
        waitPageLoad();
        List<WebElement> listOfRows = getWebDriver().findElements(checkbox);
        if(listOfRows.size()> index && index>=0  && listOfRows.size()>0)
            waitPageLoad();
        listOfRows.get(index).click();
    }

    public void clickCatalogLink() throws Exception{
        waitPageLoad();
        waitForExpectedElement(catalogLink,30).click();
    }

    public void clickSaveButton() {
        waitForExpectedElement(saveButton,30).click();
    }

    public void productSavedMessageDisplayed() {
        waitForExpectedElement(successSaveBlock, 30).isDisplayed();
    }

    //@TODO Keep in mind the default selection and reset them after all scenarios
    public void enableSubscribeProBlock() throws Exception{
        waitPageLoad();
        waitForExpectedElement(subscribeProWrapper,30).click();

        Boolean isSubscribed = waitForExpectedElement(subscribeSwitchInput,30)
                .getAttribute("value")
                .equals("1");
        Boolean isSetDefaultValue = isElementPresentByby(subscribeSwitchDisabled);

        if (!isSubscribed) {
            if (isSetDefaultValue)
                waitForExpectedElement(useDefaultSubsCheckbox,30).click();

            waitForExpectedElement(subscribeSwitchWrapper,30).click();
        }
    }

    public void clickProductLink(){
        waitForExpectedElement(productLink,30).click();
    }

    public boolean isProductDetailPagePresent(){
       return getWebDriver().getCurrentUrl().contains("product/edit/id");
     }

    public void waitPageLoad() throws Exception {
        Thread.sleep(8000);
    }

    public void clickEditLink() throws Exception {
        ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
        jse.executeScript("document.querySelector('table[data-role=\"grid\"] th:nth-child(18)').scrollIntoView();");

        ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, 0)");
        Thread.sleep(4000);
        waitForExpectedElement(editLink,30).click();
        waitPageLoad();
    }

    public void performBackEndStepsToAssertRelatedUpSellProductsConfiguration(String strProduct,String strStoreView) throws Throwable {
        storePage.clickMenuLinkFromAdminDashboard(storePage.DASHBOARD_CATALOG_LINK);
        storePage.clickLinkFromMenuWindow(storePage.PRODUCTS_LINK);
        storePage.removeAnyDefaultSearchFilters();
        storePage.performSearchSelectStoreViewAndClickEditLink(strProduct,strStoreView);
    }
}
