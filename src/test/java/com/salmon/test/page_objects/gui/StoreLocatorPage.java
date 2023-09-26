package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.page_objects.gui.constants.Locale.valueOf;
import static org.testng.Assert.assertTrue;

public class StoreLocatorPage extends PageObject {
    private static final By STORE_LOCATOR_LINK = By.cssSelector("#ui-id-1 > li:nth-child(5) > a > span");
    private static final By STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE = By.cssSelector(".item.adress-text span:nth-child(5)");
    private static final By STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE_GLOPL = By.cssSelector("div.item-row.item-row--address");
    private static final By STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE_GLODE = By.cssSelector("div.item.adress-text:nth-child(1) > span:nth-child(3)");
    private static final By STORE_SEARCH_RESULT_ADDRESS_HEADER = By.cssSelector("div.item.name > h4");
    private static final By STORE_SEARCH_RESULT_ADDRESS_HEADER_DE = By.cssSelector("div.item.name");
    private static final By STORE_SEARCH_RESULT_ADDRESS_HEADER_IT = By.cssSelector("div.item.name span");
    private static final By STORE_LOCATOR_ADDRESS_INPUT = By.cssSelector("input#pac-input.input-field.controls");
    private static final By STORE_LOCATOR_ADDRESS_INPUT_GLOPL = By.cssSelector("input.mapboxgl-ctrl-geocoder--input");
    private final static By ROUTE_BTN = By.xpath("//*[@class='result-list']//following::button[1]");
    private final static By SHOP_TYPE_DROPDOWN = By.xpath("//select[@id='store_filter']");
    private final static By PRODUCT_TYPE_DROPDOWN = By.cssSelector("#activity-type");
    private final static By SHOP_TYPE_DROPDOWN_GLO_IT= By.xpath("//select[@id='store-type']");
    private final static By SHOP_TYPE_DROPDOWN_DE = By.xpath("//select[@id='store_filter']");
    private final static By PRODUCT_TYPE_DROPDOWN_DE = By.xpath("//select[@id='product_filter']");
    private static final By DESTINATION_SEARCH_BOX = By.cssSelector("div.searchbox div.gstl_51.sbib_a div.sbib_b > input.tactile-searchbox-input");
    private static final By GOOGLE_MAP_SELECTOR = By.cssSelector("div#map");
    private static final By GOOGLE_COOKIE_CONSENT_FRAME = By.cssSelector(".widget-consent-frame");
    private static final By GOOGLE_COOKIE_CONSENT_AGREE_BUTTON = By.cssSelector("#introAgreeButton .RveJvd.snByac");
    private static final By POPUP_ON_GOOGLEMAP = By.cssSelector("div.gm-style-iw-d");
    private static final By POPUP_ON_GOOGLEMAP_PL = By.cssSelector("div.gm-style-iw.gm-style-iw-c");
    private static final By ADDRESS_ON_GOOGLEMAP_POPUP = By.cssSelector("div.gm-style-iw-d > div > div > div.adress-text.uppercase");
    private static final By ADDRESS_ON_GOOGLEMAP_POPUP_PL = By.cssSelector("div.gm-style-iw.gm-style-iw-c span");
    private static final By ROUTE_BUTTON_ON_GOOGLEMAP_POPUP = By.cssSelector(".gm-style-iw-d button.route");
    private static final By ROUTE_BUTTON_ON_GOOGLEMAP_POPUP_PL = By.cssSelector("button.route");
    public final static By SANDWICH_MENU_ICON = By.cssSelector("span.action.nav-toogle.mobile-only");
    public final static By M_SANDWICH_MENU_ICON_IT = By.cssSelector("span.action.nav-toogle.mobile-only > img");
    public static final By STORE_SEARCH_RESULTS = By.cssSelector(".store-search-results, .result-list");
    private static final By ACCEPT_GEO_LOCATION = By.xpath("//*[@id='content']/div/button");
    private static final By ALERT_OK = By.cssSelector("footer.modal-footer button");
    private static final By STORE_PRODUCT_STOCKS = By.cssSelector("div.store-stocks > div.store-stock");
    public static final By STORE_PRODUCT_STOCKS_ON_MAP = By.xpath("//div[@class='info-left-content']//following::div[@class='store-stock']");

    public static final By STORE_LOCATOR_TOP_BOTTOM_BLOCKS=By.cssSelector("div[data-content-type='block'] div.widget.block.block-static-block");
    public static final By STORE_LOCATOR_MAP_BOX_DIRECTIONS=By.xpath("//div[@id='map']//following::div[@class='mapbox-directions-origin']");
    public static final By MAP_BOX_DIRECTION_OPTIONS=By.xpath("//div[@id='map']//following::div[@class='mapbox-directions-profile mapbox-directions-component-keyline mapbox-directions-clearfix']");
    public static final By SHOP_TYPE_FILTER=By.xpath("//*[@href='#filter-storetype']");
    public static final By PRODUCT_TYPE_FILTER=By.xpath("//*[@href='#filter-products']");
    public static final By GLO_CATEGORY_TYPE_FILTER= By.cssSelector("#store_filter");
    public static final By GOOGLE_AGREE = By.linkText("I Agree");
    private static final By STORE_SEARCHRESULTADDRESS_VUSECO = By.cssSelector("#resultList > li.active > div > div.item-row.item-row--address > div.item.adress-text");
    private final static By PRODUCT_TYPE_DROPDOWN_GLODE = By.cssSelector("select#product_filter");
    private final static By MANTHOL_BAN_MAP_ICON=By.cssSelector("div > form > img");
    private final static By STORE_INPUT_TEXT=By.cssSelector("input#pac-input");
    private final static By NEAR_BUTTON=By.cssSelector("div > form > div> input[type=submit]");
    //Glo kz elements
    public static final By STORE_LOCATOR_SEARCH_INPUT=By.cssSelector("input#pac-input");
    public static final By STORE_CATEGORY_DROPDOWN=By.cssSelector("div[class='product data items filter__store-type']>div>select[class='input-field controls']");
    public static final By STORE_LOCATOR_LIST=By.cssSelector("#resultList > li");
    public static final By STORE_ASSORTMENT_DROPDOWN=By.cssSelector("select#activity-type");
    public static final By STORE_ROUTE_BUTTON=By.cssSelector("div.item.direction > button");

    public static Map<String,By> STORE_CATEGOY_DROPDOWN_BY_MAP;

    public void clickOnStoreLocatorLink() {
        if (UrlBuilder.isMobile()) {
            switch (UrlBuilder.getLocale()) {
                case "it": {
                    waitForExpectedElement(M_SANDWICH_MENU_ICON_IT, 20).click();
                    LOG.info("Click on sandwich menu icon");
                    break;
                }
                default:
                {
                    waitForExpectedElement(SANDWICH_MENU_ICON, 10).click();
                    break;
                }
            }
        }
        try {
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("StoreLocator.key")), 10);
            clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("StoreLocator.key")));
        } catch (Exception e) {
            waitForExpectedElement(STORE_LOCATOR_LINK,20).click();
        }
        waitForPage();
    }

    public void enterSearchAddress(String addressKey) throws Exception {
        Thread.sleep(6000);
        allowPopup();
        switch (UrlBuilder.getLocale()) {
            case "pl":
            case "glode":
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).clear();
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).sendKeys(UrlBuilder.getMessage(addressKey));
                break;
            case "kz":
                waitForExpectedElement(STORE_LOCATOR_SEARCH_INPUT).clear();
                waitClearAndEnterText(STORE_LOCATOR_SEARCH_INPUT,UrlBuilder.getMessage(addressKey));
                break;
            default:
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT).clear();
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT).sendKeys(UrlBuilder.getMessage(addressKey));
        }

        selectFirstAddressFromLookUp();
    }

    public void selectFirstAddressFromLookUp() {

        switch (UrlBuilder.getLocale()) {
            case "pl":
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).sendKeys(Keys.SPACE);
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).sendKeys(Keys.DOWN);
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).sendKeys(Keys.RETURN);
                break;
            case "glode":
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).sendKeys(Keys.SPACE);
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).sendKeys(Keys.ENTER);
                break;
            default:
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT).sendKeys(Keys.SPACE);
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT).sendKeys(Keys.DOWN);
                waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT).sendKeys(Keys.RETURN);
        }
    }

    public String getSelectedAddress() {
        String addressDisplayed;
        if(UrlBuilder.getLocale().contains("pl")||UrlBuilder.getLocale().contains("glode")){
            addressDisplayed= waitForExpectedElement(STORE_LOCATOR_ADDRESS_INPUT_GLOPL).getAttribute("value");
        }
        else {
            addressDisplayed = waitForExpectedElement(By.cssSelector("#pac-input")).getAttribute("value");
        }
        LOG.info("Address displayed : " + addressDisplayed);
        return addressDisplayed;
    }

    public List<WebElement> getListOfStoresDisplayed() {
        switch (UrlBuilder.getLocale()){
            case "pl":
                waitForExpectedElement(STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE_GLOPL, 10);
                return presenceOfAllElementsLocatedBy(STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE_GLOPL);
            case "vuseco":
                waitForExpectedElement(STORE_SEARCHRESULTADDRESS_VUSECO, 10);
                return presenceOfAllElementsLocatedBy(STORE_SEARCHRESULTADDRESS_VUSECO);
            case "glode":
                waitForExpectedElement(STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE_GLODE, 10);
                return presenceOfAllElementsLocatedBy(STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE_GLODE);
            default:
                waitForExpectedElement(STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE, 10);
                return getWebDriver().findElements(STORE_SEARCH_RESULT_ADDRESS_BOTTOM_LINE);
        }
    }

    public void isShopTypeDropDownDisplayed() {
        waitForExpectedElement(SHOP_TYPE_DROPDOWN).isDisplayed();
    }

    public void isProductTypeDropDownDisplayed() {
        waitForExpectedElement(SHOP_TYPE_DROPDOWN).isDisplayed();
    }

    public String getSelectedValueFromShopTypeDropDown() {
        return getSelectedValueFrom(SHOP_TYPE_DROPDOWN);
    }

    public String getSelectedValueFromProductTypeDropDown() {
        return getSelectedValueFrom(PRODUCT_TYPE_DROPDOWN);
    }

    public List<String> getOptionsFromShopTypeDropDown() {
        return getAllSelectOptions(SHOP_TYPE_DROPDOWN);
    }

    public List<String> getOptionsFromProductTypeDropDown() {
        return getAllSelectOptions(PRODUCT_TYPE_DROPDOWN_GLODE);
    }

    public void clickOnRouteButton() {
        clickByElementByQueryJSExecutor(ROUTE_BTN);
    }

    public String getPrePopulatedDestinationCountryInGoogleMapsInNewTab() {
        ArrayList<String> windowTabs = new ArrayList<>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(windowTabs.get(1));
        doesURLContain("https://www.google.com/maps/dir/");
        //the following line of code is only needed for Local Runs but not needed for Browser Stack.
//        clickOnGoogleCookieConsentButton();
        String attribute = waitForExpectedElement(DESTINATION_SEARCH_BOX, 30).getAttribute("aria-label");
        getWebDriver().close();
        getWebDriver().switchTo().window(windowTabs.get(0));
        return attribute;
    }

    private void clickOnGoogleCookieConsentButton() {
        getWebDriver().switchTo().frame(waitForExpectedElement(GOOGLE_COOKIE_CONSENT_FRAME, 10));
        getWebDriver().findElement(GOOGLE_COOKIE_CONSENT_AGREE_BUTTON).click();
        getWebDriver().switchTo().defaultContent();
    }

    private String getSelectedValueFrom(By dropdown) {
        return new Select(waitForExpectedElement(dropdown)).getFirstSelectedOption().getAttribute("value");
    }

    public void selectShopOrProductType(String type, String value) throws Exception {
        switch (valueOf(getLocale().toUpperCase())) {
            case VUSEDE:
            case VUSEUK:
                By locator = "shop".equalsIgnoreCase(type) ? SHOP_TYPE_DROPDOWN_DE : PRODUCT_TYPE_DROPDOWN_DE;
                Thread.sleep(2000); //this is required due to slowness in uploading locations on maps,tried other wait options but no success
                selectValueFromDropDownByby(value.trim(), locator);
                waitForAjaxElementNotToBePresent(getWebDriver(),5);
                waitForExpectedElement(STORE_SEARCH_RESULT_ADDRESS_HEADER_DE,5);
            break;
            case GLODE:
                if(type.equals("product")){
                    WebElement element= getWebDriver().findElement(PRODUCT_TYPE_DROPDOWN_GLODE);
                    selectValueFromDropDownByWebElement(element,value);
                    waitForAjaxElementNotToBePresent(getWebDriver(),10);
                }
                else{
                    WebElement element= getWebDriver().findElement(SHOP_TYPE_DROPDOWN_DE);
                    selectValueFromDropDownByWebElement(element,value);
                    waitForAjaxElementNotToBePresent(getWebDriver(),10);
                }
                break;
            case IT:
                locator = "shopType".equalsIgnoreCase(type) ? SHOP_TYPE_DROPDOWN_GLO_IT : PRODUCT_TYPE_DROPDOWN;
                selectOptionFromDropDownByValue(value, locator);
                waitForExpectedElement(locator,10);
                break;
            default:
                locator = "shopType".equalsIgnoreCase(type) ? SHOP_TYPE_DROPDOWN : PRODUCT_TYPE_DROPDOWN;
                selectOptionFromDropDownByValue(value, locator);
        }
    }

    public List<WebElement> getStoreOrProductTypeFromStoreLocatorSearchResults() {
        switch (valueOf(getLocale().toUpperCase())) {
            case VUSEDE:
            case VUSEUK:
            case PL:
                waitForAjaxElementNotToBePresent( getWebDriver(), 5);
                return getWebDriver().findElements(STORE_SEARCH_RESULT_ADDRESS_HEADER_DE);
            case IT:
                waitForAjaxElementNotToBePresent( getWebDriver(), 10);
                waitForExpectedElement(STORE_SEARCH_RESULT_ADDRESS_HEADER_IT,20);
                return getWebDriver().findElements(STORE_SEARCH_RESULT_ADDRESS_HEADER_IT);
            default:
                waitForExpectedElement(STORE_SEARCH_RESULT_ADDRESS_HEADER, 10);
                return getWebDriver().findElements(STORE_SEARCH_RESULT_ADDRESS_HEADER);
        }
    }

    public boolean isGoogleMapDisplayed() {
        return waitForExpectedElement(GOOGLE_MAP_SELECTOR, 10).isDisplayed();
    }

    public boolean isSelectedAddressPopUpDisplayedInGoogleMap() {
        switch (UrlBuilder.getLocale()) {
            case "pl":
                return waitForExpectedElement(POPUP_ON_GOOGLEMAP_PL, 10).isDisplayed();
            default:
                return waitForExpectedElement(POPUP_ON_GOOGLEMAP, 10).isDisplayed();
        }
    }

    public String getAddressDisplayedOnGoogleMapPopup() {
        switch (UrlBuilder.getLocale()) {
            case "pl":
                return waitForExpectedElement(ADDRESS_ON_GOOGLEMAP_POPUP_PL, 10).getText();
            default:
                return waitForExpectedElement(ADDRESS_ON_GOOGLEMAP_POPUP, 10).getText();
        }
    }

    public void clickOnRouteLinkOnGoogleMapPopup() {
        switch (UrlBuilder.getLocale()) {
            case "pl":
                waitForExpectedElement(ROUTE_BUTTON_ON_GOOGLEMAP_POPUP_PL).click();
                break;
            default:
                waitForExpectedElement(ROUTE_BUTTON_ON_GOOGLEMAP_POPUP).click();
        }
    }

    public void eyesCheckStoreLocatorPage() {
        if (Props.EYES_ON) {
            waitForExpectedElement(STORE_SEARCH_RESULTS);
            scrollToShowEntirePage();
            eyes.check("Store Locator Page", Target.window().fully());
        }
    }

    public void allowGeoLocationPopup(){
        if (isElementPresent(ACCEPT_GEO_LOCATION)) {
            waitForExpectedElement(ACCEPT_GEO_LOCATION).click();
        }
    }

    public void allowPopup() {
        if (isElementClickable(ALERT_OK)) {
            waitForExpectedElement(ALERT_OK).click();
        }
    }

    public List<WebElement> getProductStocksListOnStoreFinder(){
        return getWebDriver().findElements(STORE_PRODUCT_STOCKS);
    }

    public void clickProductStock() {
        clickFirstElementByQueryJSExecutor(STORE_PRODUCT_STOCKS);
    }

    public void selectCategoryType(String value){
        //store by variable to map for different local
        STORE_CATEGOY_DROPDOWN_BY_MAP=new HashMap();
        STORE_CATEGOY_DROPDOWN_BY_MAP.put("PL",GLO_CATEGORY_TYPE_FILTER);
        STORE_CATEGOY_DROPDOWN_BY_MAP.put("KZ",STORE_CATEGORY_DROPDOWN);
        //get By variable according to local
        By storeCategory=STORE_CATEGOY_DROPDOWN_BY_MAP.get(UrlBuilder.getLocale().toUpperCase().trim());
        selectValueFromDropDownByby(value.trim(),storeCategory);
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
    }

    public void selectFilterByTypeAndAssertResultsAreFiltered(String strType, DataTable dtTypes) throws Throwable {
        switch (valueOf(getLocale().toUpperCase())) {
        case VUSEDE:
        case VUSEUK:
        case GLODE:
            case IT:
                List<List<String>> shopProductTypes = dtTypes.raw();
            for (int i = 1; i <= shopProductTypes.size() - 1; i++) {
                selectShopOrProductType(strType, shopProductTypes.get(i).get(0));
                List<WebElement> searchResults = getStoreOrProductTypeFromStoreLocatorSearchResults();
                for (WebElement ele : searchResults) {
                    try {
                        assertTrue(ele.getText().contains(shopProductTypes.get(i).get(1)));
                        break;
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            break;
        case PL:
            List<List<String>> categoryTypes = dtTypes.raw();
            for (int i = 1; i < categoryTypes.size(); i++) {
                selectCategoryType(categoryTypes.get(i).get(0));
                List<WebElement> searchResults = getStoreOrProductTypeFromStoreLocatorSearchResults();
                for (WebElement ele : searchResults) {
                    try {
                        assertTrue(ele.getText().contains(categoryTypes.get(i).get(1)));
                        break;
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            break;
            default:
        }
    }

    public void selectFilterByTypeAndAssertResultsAreFiltered(String strType) throws Throwable {
        switch (valueOf(getLocale().toUpperCase())) {
            case VUSEDE:
            case VUSEUK:
                String[] Types = UrlBuilder.getMessage(strType).split(",");
                for (int i = 0; i < Types.length; i++) {
                    if(strType.contains("product"))
                        selectShopOrProductType("product", Types[i]);
                    else
                        selectShopOrProductType("shop", Types[i]);
                    List<WebElement> searchResults = getStoreOrProductTypeFromStoreLocatorSearchResults();
                        assertTrue(searchResults.size()>=0);
                        break;
                }
            default:
        }
    }

    /**
     * @return return search input default text
     */
    public String getSearchInputText() {
        return waitForExpectedElement(STORE_LOCATOR_SEARCH_INPUT).getAttribute("value");
    }

    /**
     * @return the store locator size
     */
    public int getLocatorListSize() {
        return getWebDriver().findElements(STORE_LOCATOR_LIST).size();
    }

    public void selectAssortmentType(String assortmentName) {
        selectValueFromDropDownByby(assortmentName.trim(), STORE_ASSORTMENT_DROPDOWN);
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    /**
     * Random click the suggest route in the result list on store locator page
     */
    public void clickSuggestRoute() {
        List<WebElement> routeElement = getWebDriver().findElements(STORE_ROUTE_BUTTON);
        if (routeElement.size() > 0) {
            int randomNumber = new Random().nextInt(routeElement.size());
            clickUsingJS(routeElement.get(randomNumber));
        }
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public Boolean isGoogleMapWindownOpened() {
        Boolean flag = false;
        ArrayList<String> windowTabs = new ArrayList<String>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(windowTabs.get(1));
        if (!doesURLContain("https://www.google.com/maps/dir/")) {
            By aggreeButton = By.cssSelector("div.AIC7ge > form > div > div > button > span");
            if (isElementPresent(aggreeButton))
                clickUsingJS(aggreeButton);
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        }
        flag = doesURLContain("https://www.google.com/maps/dir/");
        getWebDriver().close();
        getWebDriver().switchTo().window(windowTabs.get(0));
        return flag;
    }

    public boolean isMapIconDisplayed() {
        scrollToElement(MANTHOL_BAN_MAP_ICON);
        return waitForExpectedElement(MANTHOL_BAN_MAP_ICON).isDisplayed();
    }

    public void findNearStore(String aimStore) {
        waitClearAndEnterText(STORE_INPUT_TEXT,UrlBuilder.getMessage(aimStore));
        try{
            waitForExpectedElement(NEAR_BUTTON).click();
        }catch(Exception e){
            LOG.info(e.getMessage());
            waitForExpectedElement(STORE_INPUT_TEXT).sendKeys(Keys.ENTER);
        }
    }
}