package com.salmon.test.page_objects.gui;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchResult extends PageObject {

    private ScenarioContext scenarioContext;
    public SearchResult(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }
    public SearchResult() {
    }

        // ELEMENT MAPPING
    public By searchResultsHeading = By.cssSelector("span.base,a.product-item-link");
    public By searchResults = By.cssSelector("div.message.notice,input#search.input-text");
    public By searchRestulsCXRedesign = By.cssSelector("#maincontent > div > div > div.no-search-result > p");
    public By firstDisplayedAddToCartButton = By.cssSelector("button.action.tocart.primary");
    public By secondDisplayedAddToCartButton = By.cssSelector("button.action.tocart.secondary > span");
    private static final By ADD_TO_CART_BUTTON_PLP = By.cssSelector("button.action.tocart.primary:nth-child(5) > span");
    private static final  By searchResults_DE = By.cssSelector("div.message.notice,#maincontent > div.columns > div > div.no-search-result > div");
    private static final By SUBSCRIPTION_PRODUCT = By.cssSelector("div.subscription");
    public By btnProductColor = By.cssSelector("div.swatch-option.color");
    public By lstProducts = By.cssSelector("ol.products.list");
    public By lnkProductItem = By.cssSelector("a.product-item-link");
    private static final By FIRSTLINKPRODUCTITEM = By.cssSelector("#productListingContainer > div > div:nth-child(1) > div.bat-productlistings-card-details > div.bat-productlistings-card-details-image > a > img");
    public final static By MX_BTN_ADD_TO_CART_FROM_PLP = By.cssSelector("li:nth-child(1) div.product.details.product-item-details button span");
    public final static By ZA_BTN_ADD_TO_CART_FROM_PLP = By.cssSelector("button[title=\"Add to basket\"]");
    public By eleBasketQty = By.cssSelector("span.counter-number");
    public final static By ADDTOCART_BUTTON = By.cssSelector("button#product-addtocart-button");
    public By searchResultsText = By.cssSelector("div.no-search-result > div");
    public By PRODUCT_ITEMS=By.cssSelector("#product-list-plp > li");

    //Search
    public By searchInputBox = By.cssSelector("#search");
    public By productDescriptionPDP = By.cssSelector("div.product.attribute.overview");
    public By lnkProductFromAutoSearchList = By.cssSelector("div.product-primary > div.product-name");
    private final static By PRODUCT = By.cssSelector("li.item.product");
    public final static By SEARCH_ICON_MOBILE = By.cssSelector("span.icon-search.header-link");
    private final static By SWATCH = By.cssSelector("div.swatch-option");
    private final static By ADD_TO_CART_BUTTON = By.cssSelector("button.action.tocart");
    private static final By SEARCH_RESULT = By.cssSelector("#product-list-plp > li.item.product.product-item");
    private static final By PRODUCT_TITLE_LINK = By.cssSelector("#product-list-plp > li div.product.name > a");
    private static final By REVIEW_PRODUCT_TITLE_LINK = By.xpath("//div[div[div[div[div[@class='product-reviews-summary']]]]]/a");
    //Lyft LAB
    private static final By LAB_PLP_PREV_COLLECTION = By.xpath("(//ol[@class='product-items widget-product-grid'])[2]");
    private static final By LAB_PLP_NEW_COLLECTION = By.cssSelector("div.lab-landing-product-carousel:nth-child(1) div.block-content div.products-grid.grid > ol.product-items.widget-product-grid");
    private static final By FIRST_PRODUCT_LINK = By.cssSelector("a.product-item-link");
    private static final By FIRST_PRODUCT_LINK_PL = By.cssSelector("#product-list-plp > li:nth-child(1) > div  div.name-price-container> div.product.name > a");
    private static final By M_FIRST_PRODUCT_LINK_GLOIT = By.cssSelector("ol:nth-child(2) > li:nth-child(1) > div > div > div:nth-child(1) > strong > a.product-item-link");
    private static final By FOURTH_PRODUCT_LINK = By.cssSelector("li.item.product.product-item:nth-child(4) div.product.name > a.product-item-link");
    private static final By VUSE_DE_FIRST_PRODUCT_LINK = By.cssSelector(".product.name a");
    private static final By FIRST_BUYABLE_PRODUCT_LINK_Lyft=By.cssSelector("div > div.product.details.product-item-details > strong > a");
    private static final By SHOP_LAB = By.cssSelector("div.lab-header-left li>a[href*='lab-products']");
    private static final By FIRST_BUYABLE_PRODUCT_LINK_Lyft_Image=By.cssSelector("div.products.wrapper.grid.products-grid > ol > li > div > a > span > span > img");
    private static final By PRODUCT_ITEM_LINK = By.cssSelector("a.product-item-link");
    WebElement searchListResults;
    List<WebElement> resultItems;
    private By outOfStockMsg = By.cssSelector("#maincontent > div.columns > div.column.main > div.search.results > div.products.wrapper.grid.products-grid > ol > li:nth-child(4) > div > div > div.product-item-inner > div > div > div > span");
    public static final By FIRST_RESULT = By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product-item-group");
    public static final By INPUT_VYPE_FR_QTY = By.cssSelector("input[name='qty'][style='display: block;']");
    public static final By SECOND_RESULT = By.cssSelector("#product-list-plp > li:nth-child(2) > div > div.product-item-group");
    public static final By SEARCH_PAGE_FILTER = By.cssSelector("#maincontent > div.columns > div > div.toolbar.toolbar-products > div.toolbar-btns__container > div.toolbar-btns__filter.filter-btn__container > button");
    public final static By VUSE_MX_CAT_RESULT = By.cssSelector("#product-list-plp > li:nth-child(1) > div > div.product-item-group > div.product.name > a");
    public static final By CLICK_ON_DEVICE_MENU = By.cssSelector("div.custom-categories > div > div > ul > li.level0.category-item.forth > a:nth-child(2)");
    public static final By VUSE_ZA_IMAGE_PRICE = By.cssSelector("#product-price-3717");
    private final static By TOTAL_PRODUCTS = By.cssSelector("a.product-item-link");
    private final static By TOTAL_PRODUCTS_REVIEW_COUNT = By.xpath("//div[@data-bv-show='inline_rating']//div[@class='bv_averageRating_component_container']/div");
    private static final By OOS_PRODUCT_COLOR_SWATCH= By.xpath("//div[@class='swatch-option color pointer-event-reset'][1]");
    private static final By OOS_PRODUCT_DEVICE_LINK=By.xpath("//div[@class='swatch-option color pointer-event-reset'][1]//preceding::a[@class='product-item-link'][1]");
    private static final By OOS_PRODUCT_STRENGTH_SWATCH=By.xpath("//div[@class='swatch-option text pointer-event-reset'][@index='0']");
    private static final By OOS_PRODUCT_FLAVOR_LINK=By.xpath("//div[@class='swatch-option text pointer-event-reset'][@index='0']//preceding::a[@class='product-item-link'][1]");
    public static final By FILTER_BY_PRODUCT = By.cssSelector("div:nth-of-type(1) > div[role='tabpanel'] > .filter__list > li:nth-of-type(1) > .filter__label");
    public static final By FILTER_BY_PRODUCT_COUNT = By.cssSelector("div:nth-of-type(1) > div[role='tabpanel'] > .filter__list > li:nth-of-type(1) .filter__count");
    public static final By FILTER_BY_STRENGTH = By.cssSelector("body > div.bat-wrapper > div > div > div.responsivegrid.rootTemplateGrid.aem-GridColumn.aem-GridColumn--default--12 > div > div:nth-child(3) > bat-section-default > div > div > div > bat-sortfilter-avalanche > div > div.bat-filter > div > div > div:nth-child(2)");
    public static final By FILTER_DONE_VELOZA = By.cssSelector("div.group-title > div > button");
    public static final By FILTER_APPLY_BUTTON = By.cssSelector("button.overlay-sidebar__btn-done.action.primary");
    public static final By FILTER_CANCEL_BUTTON = By.cssSelector("button.filter__btn-clear");
    public static final By SEARCH_SUGGESTION_ITEM = By.cssSelector("span.qs-option-name");
    private final static By FIRST_PRODUCT_IMAGE = By.cssSelector("#product-list-plp > li:nth-child(1) > div > a");
    public final static By PDP_DESCRIPTION = By.cssSelector("div.product-info-main > div.product.attribute.overview > div");
    private static final By AVALANCHE_PDP_FILTERED_PRODUCT=By.cssSelector("#productListingContainer > div > div");
    private static final By PRODUCT_TOTAL_NUMBER = By.cssSelector(".column.main p.toolbar-count span");
    private final static By FIRST_PRODUCT_IMAGE_LYFT = By.cssSelector("div.products.wrapper.grid.products-grid > ol > li:nth-child(1) > div > a > span");
    public final static By PDP_DESCRIPTION_LYFT = By.cssSelector("div.product-info-main.product-card > div.product.attribute.description");

    public Integer returnNumOfSearchResults(){
        switch ( UrlBuilder.getLocale()) {
            case "veloza":
                List<WebElement> elemList = waitForExpectedElements(AVALANCHE_PDP_FILTERED_PRODUCT);
                return elemList.size();
            default:
        searchListResults = waitForExpectedElement(By.cssSelector("ol.products.list")); //.product-item"));
        resultItems = searchListResults.findElements(By.tagName("li"));
        return resultItems.size();}

    }

    public void hoverOverFirstResult(){
        hoverOnElement(FIRST_RESULT);
    }

    public void  clickSecondAddToCartButtonPLP()
    {
        switch ( UrlBuilder.getLocale()) {
            case "mx":
            case "vypeit":
                waitForExpectedElement(MX_BTN_ADD_TO_CART_FROM_PLP,30);
                clickFirstElementByQueryJSExecutor(MX_BTN_ADD_TO_CART_FROM_PLP);
                waitForExpectedElement(eleBasketQty,30);
                break;
            case "vuseza":
                hoverOnElement(VUSE_ZA_IMAGE_PRICE);
                waitForExpectedElement(ZA_BTN_ADD_TO_CART_FROM_PLP,30);
                clickFirstElementByQueryJSExecutor(ZA_BTN_ADD_TO_CART_FROM_PLP);
                waitForExpectedElement(eleBasketQty,30);
                break;
            case "vusemx":
                hoverOnElement(VUSE_MX_CAT_RESULT);
                waitForExpectedElement(MX_BTN_ADD_TO_CART_FROM_PLP, 20).click();
                break;
            case "de":
                waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("PLPaddToCartButton.key")),30);
                clickByElementByQueryJSExecutor(By.cssSelector(UrlBuilder.getMessage("PLPaddToCartButton.key")));
                break;
            case "nl":
                selectAddToCartProductFromList();
                break;
            case "dk":
            case "fr":
            case "uk":
            case "vusedk":
            case "vusefr":
            case "vuseit":
                waitForExpectedElement(ADD_TO_CART_BUTTON_PLP,10);
                clickFirstElementByQueryJSExecutor(ADD_TO_CART_BUTTON_PLP);
                break;
            case "vuseuk":
                hoverOverFirstResult();
                waitForExpectedElement(ADD_TO_CART_BUTTON_PLP,10);
                clickFirstElementByQueryJSExecutor(ADD_TO_CART_BUTTON_PLP);
                break;
            default:
                clickByElementByQueryJSExecutor(firstDisplayedAddToCartButton);
        }
    }

    public void clickMultipleVariantAddToCartButtonPLP()
    {
        List<WebElement> products = webDriver.findElements(PRODUCT);
        for (WebElement webElement: products) {
            if (webElement.findElements(SWATCH).size()>1) {
                webElement.findElement(ADD_TO_CART_BUTTON).click();
                break;
            }
        }
    }

    public void selectSearchResult() throws InterruptedException {
        if(UrlBuilder.isDesktop()||UrlBuilder.isIpad()) {
            waitForExpectedElement(lnkProductItem, 30);
            Thread.sleep(3000);
            int defaultIndex = 0;
            switch (UrlBuilder.getLocale()) {
                case "vuseuk":
                    if(SessionInfo.scenarioName.equals("Assert checkbox under Mobile Number field displayed for customers not having a UK/FR number on Registration page")
                    ||SessionInfo.scenarioName.equals("9601 PROMOTION - Valid Promotion applied")){
                        defaultIndex = UrlBuilder.getMessage("checkMobileNumberProductIndex.key").isEmpty()
                                ?Integer.parseInt(UrlBuilder.getMessage("checkMobileNumberProductIndex.key")):5;
                    }
                    else if(SessionInfo.scenarioName.equals("13842 Navigate to PDP page via search and product selection - ensure expected PDP elements are present as expected")
                            ||SessionInfo.scenarioName.equals("Verify PDP Delivery messages on the basis of UK Timelines")
                            ||SessionInfo.scenarioName.equals("Search and Browse - variants displayed")
                            //subscription item
                            ||SessionInfo.scenarioName.equals("VuseUK product details on product page")
                            ||SessionInfo.scenarioName.equals("Quantity Selector switch to free text on max value selection and switch back to dropdown on Mini Cart")
                            ||SessionInfo.scenarioName.equals("Verify first Flavor and Device SKU pre-selected on page load when in stock")
                            ||SessionInfo.scenarioName.equals("Quantity Selector switch to free text on max value selection and switch back to dropdown on Basket page")){
//                            ||SessionInfo.scenarioId.contains("GlobalSubsciptions")){
                        defaultIndex = UrlBuilder.getMessage("productIndex.key").isEmpty()
                                ?Integer.parseInt(UrlBuilder.getMessage("productIndex.key")):3;
                    }
                    else {
                        defaultIndex = 3;
                    }
                    lnkProductItem = By.cssSelector("ol.products.list.items.product-items li:nth-child(" + defaultIndex + ") a.product-item-link");
                    waitForExpectedElement(lnkProductItem,20);
                    clickUsingJS(lnkProductItem);
                    break;
                case "velobe":
                    defaultIndex = 2;
                    resultItems = webDriver.findElements(PRODUCT_TITLE_LINK);
                    try {
                        resultItems.get(defaultIndex).click();
                    } catch (Exception e) {
                        clickUsingJS(resultItems.get(defaultIndex));
                    }
                    break;
                case "velopl":
                    waitForAjaxElementNotToBePresent(getWebDriver(),5);
                    waitForExpectedElement(FIRSTLINKPRODUCTITEM, 10).click();

                    break;
                default:
                    searchListResults = waitForExpectedElement(lstProducts, 10);
                    try {
                        resultItems = searchListResults.findElements(By.tagName("li"));
                    } catch (StaleElementReferenceException e) {
                        resultItems = searchListResults.findElements(By.tagName("li"));
                    }

                    WebElement product = resultItems.get(defaultIndex);
                    try {
                        clickByElementByQueryJSExecutor(lnkProductItem);
                    } catch (Exception e) {
                        WebElement e1 = product.findElement(lnkProductItem);
                        clickElementByQueryJSExecutor(e1);
                    }
                    break;
            }
        }
        else{
            int defaultIndex=1;
            switch (UrlBuilder.getLocale()){
                case "uk":
                case "vuseuk":
                    defaultIndex=3;
                    break;
                case "vuseco":
                    defaultIndex=1;
                default:
                    break;
            }
            switch (UrlBuilder.getLocale()) {
                case "lyftse":
                case "vuseza":
                case "ie":
                case "mx":
                case "vusemx":
                case "fr":
                case "vypeit":
                case "vusefr":
                case "vusede":
                case "vuseco":
                case "vuseit":
                    waitForExpectedElement(VUSE_DE_FIRST_PRODUCT_LINK, 10);
                    clickIndexElementByQueryJSExecutor(VUSE_DE_FIRST_PRODUCT_LINK, 0);
                    break;
                case "vuseuk":
                    if(UrlBuilder.isMobile()){
                        waitForExpectedElement(FIRST_PRODUCT_LINK, 10);
                        clickIndexElementByQueryJSExecutor(FIRST_PRODUCT_LINK, 0);}
                    else
                        clickUsingJS(By.cssSelector("#maincontent > div.columns > div > div.search.results > div.products.wrapper.grid.products-grid > ol > li:nth-child("+defaultIndex+")"));
                    break;
                case "it":
                    if(UrlBuilder.isMobile()){
                        waitForExpectedElement(M_FIRST_PRODUCT_LINK_GLOIT, 10);
                        scrollElementIntoView(M_FIRST_PRODUCT_LINK_GLOIT);
                        clickIndexElementByQueryJSExecutor(M_FIRST_PRODUCT_LINK_GLOIT, 0);}
                    else
                        clickUsingJS(By.cssSelector("#maincontent > div.columns > div > div.search.results > div.products.wrapper.grid.products-grid > ol > li:nth-child("+defaultIndex+")"));
                    break;
                default:
                    clickUsingJS(By.cssSelector("#maincontent > div.columns > div > div.search.results > div.products.wrapper.grid.products-grid > ol > li:nth-child("+defaultIndex+")"));
            }
        }
    }

    public void selectFirstBuyableProduct() {
        String AvailableProductNameList = "//button[@class='action tocart primary'][ not(@disabled)]/ancestor::div[@class='product-item-info product-card']//h3[@class='product name']/a";
        List<WebElement> products =visibilityOfAllElementsLocatedBy(By.xpath(AvailableProductNameList));
        String selectedProductName = products.get(0).getText().trim();
        LOG.info("Selected product name:" + selectedProductName);
        By image=By.xpath("//button[@class='action tocart primary'][ not(@disabled)]/ancestor::div[@class='product-item-info product-card']//img[@class='product-image-photo']");
        WebElement productFirst= presenceOfAllElementsLocatedBy(image).get(0);
        jsScrollElementInCenter(productFirst);
        productFirst.click();
    }

    public void selectFirstBuyableProduct_Pl() {
        waitForExpectedElement(FIRST_PRODUCT_LINK_PL,10);
        clickFirstElementByQueryJSExecutor(FIRST_PRODUCT_LINK_PL);
        if(!waitForExpectedElement(ADDTOCART_BUTTON).isEnabled()){
            waitForExpectedElement(CLICK_ON_DEVICE_MENU,20);
            clickByElementByQueryJSExecutor(CLICK_ON_DEVICE_MENU);
            waitForExpectedElement(FOURTH_PRODUCT_LINK,10);
            clickFirstElementByQueryJSExecutor(FOURTH_PRODUCT_LINK);
        }
    }
    public void selectFirstBuyableProduct_Lyft(int productPosition) {
        try {
            webDriver.findElements(FIRST_BUYABLE_PRODUCT_LINK_Lyft).get(productPosition).click();
        }
        catch(Exception ex) {
            LOG.info("No any buyable product present");
        }
    }

    public void selectSpecificSearchResult(String productName){
        searchListResults = waitForExpectedElement(By.cssSelector("ol.products.list"));
        resultItems = searchListResults.findElements(By.tagName("li"));
        for (WebElement product : resultItems){
            LOG.info("\n PRODUCT NAME : " + product.findElement(By.cssSelector("a.product-item-link")).getText());
            String productInLoopName = product.findElement(By.cssSelector("a.product-item-link")).getText();
            if (productInLoopName.equals(productName)){
                LOG.info("PRODUCT MATCH!!");
                product.findElement(By.cssSelector("span.product-image-container")).click();
                LOG.info("\n ***** product should of been clicked");
                break;
            }
        }
    }

    // product loop
    public void loopThurProducts() {
        // Result list - need to cycle thur
        searchListResults = waitForExpectedElement(By.cssSelector("ol.products.list"));
        resultItems = searchListResults.findElements(By.tagName("li"));

        LOG.info("\n Results returned : " + resultItems.size() );
        for (WebElement prod: resultItems){
            LOG.info("ProdName " + prod.findElement(By.cssSelector("a.product-item-link")).getText());
        }
    }

    // page actions
    public String getSearchResultTitle(){
        switch ( UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
            case "dk":
            case "ukLive":
            case "fr":
            case "vusefr":
            case "vusedk":
            case "vuseuklive":
            case "mx":
            case "vusemx":
            case "vypeit":
            case "ie":
            case "vuseit":
            case "vuseco":
            case "vuseza":
                return getCurrentPageTitle();
            default:
                return waitForExpectedElement(searchResultsHeading).getText();}
    }

    public String getSearchHeading(){
        return waitForExpectedElement(searchResultsHeading).getText();
    }

    public String getSearchResultText(){
        if (UrlBuilder.getLocale().equals("vusede") || UrlBuilder.getLocale().equals("vuseza"))
            return waitForExpectedElement(searchResults_DE).getText();
        else
            return waitForExpectedElement(searchResults).getText();
    }

    public String getOutOfStockMesssage() {
        return waitForExpectedElement(outOfStockMsg).getText();
    }

    public void selectAddToCartProductFromList()
    {
        int intAddToCartCount;
        waitForExpectedElement(secondDisplayedAddToCartButton,30);
        try
        {
            //Count of 'Add to Cart' button to select from the list
            intAddToCartCount = getWebDriver().findElements(secondDisplayedAddToCartButton).size();

            if (intAddToCartCount==0)
                LOG.info("'Add To Cart' button not available.");
            else if(intAddToCartCount ==1)
                clickElementByQueryJSExecutor(waitForExpectedElement(secondDisplayedAddToCartButton));

            else if(intAddToCartCount>1)
            {  				clickElementByQueryJSExecutor(getWebDriver().findElements(secondDisplayedAddToCartButton).get(0));
                Thread.sleep(1500);
                assertTrue(getWebDriver().findElements(eleBasketQty).size() >0);
            }
        }
        catch(Exception ex)
        {
            LOG.info("Failed to click on 'Add To Cart' button for a product from the list due to exception: ."+ex.getMessage());
        }
    }

    public void clickFirstLyftLabProduct() throws InterruptedException {
        if(getWebDriver().findElements(LAB_PLP_NEW_COLLECTION).size()>0){
            searchListResults = waitForExpectedElement(LAB_PLP_NEW_COLLECTION,30); }
        else {
            searchListResults = waitForExpectedElement(LAB_PLP_PREV_COLLECTION,30); }
        resultItems = searchListResults.findElements(By.tagName("li"));
        clickFirstLink();
    }

    public void clickFirstLink() throws InterruptedException {
        Thread.sleep(3000);
        for (WebElement product : resultItems) {
            try {
                clickByElementByQueryJSExecutor(FIRST_PRODUCT_LINK);
            } catch (Exception e) {
                WebElement e1 = product.findElement(FIRST_PRODUCT_LINK);
                clickElementByQueryJSExecutor(e1);
            }
            break;
        }
        waitForExpectedElement(ADDTOCART_BUTTON, 20);
    }

    public void clickAddToCartOnProductHasMultipleSwatches() {
        String str = "//*[@class='products list items product-items']/li";
        By productDetails = By.xpath(str);
        List<WebElement> productLists = getWebDriver().findElements(productDetails);
        LOG.info("Total number of product lists : " + productLists.size());
        for (int i = 1; i <= productLists.size(); i++) {
            List<WebElement> swatchLists = getWebDriver().findElements(By.xpath(str + "[" + i + "]//div[starts-with(@class, 'swatch-option')]"));
            if (swatchLists.size() > 1) {
                String productName=getWebDriver().findElement(By.xpath(str + "[" + i + "]//a[@class='product-item-link']")).getText();
                LOG.info("The product "+productName+" has "+ swatchLists.size() + " swatches.");
                clickByElementByQueryJSExecutor(By.xpath(str + "[" + i + "]//button"));
                break;
            }
        }
    }

    public void eyesCheckSearchResultPage() {
        if (Props.EYES_ON && EyesCheckpoints.SEARCH_RESULT_PAGE.isSwitchOn()) {
            scrollToShowEntirePage();
            final String checkpointName = EyesCheckpoints.SEARCH_RESULT_PAGE.getName();
            WebElement productItems;
            WebElement toolbarCount;
            switch (UrlBuilder.getLocale()) {
                case "vusedk":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(PLP.PRODUCT_ITEMS_REGION));
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(HomePage.SLICK_LIST)
                                .ignore(PLP.PRODUCT_ITEMS_REGION));
                    }
                    break;
                case "vusede":
                case "vusefr":
                case "vuseit":
                case "vuseza":
                case "vuseco":
                    if (UrlBuilder.isDesktop() || UrlBuilder.isIpad()) {
                        eyes.check(checkpointName, Target.window().fully());
                    } else { // mobile
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(PLP.PRODUCT_ITEMS_REGION,
                                        HomePage.MESSAGE_ROW));
                    }
                    break;
                case "pl":
                case "mx":
                case "vusemx":
                case "fr":
                case "uk":
                    productItems = waitForExpectedElement(PLP.PRODUCT_ITEMS_REGION);
                    if (Props.USE_EYES_GRID) scrollToShowEntirePage();
                    if (UrlBuilder.isDesktop()) {
                        toolbarCount = waitForExpectedElement(PLP.TOOLBAR_COUNT);
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(productItems,
                                        toolbarCount));
                    } else {
                        eyes.check(checkpointName, Target.window().fully());
                    }
                    break;
                case "vuseuk":
                    if (Props.USE_EYES_GRID) scrollToShowEntirePage();
                    if (UrlBuilder.isDesktop()) {
                        toolbarCount = waitForExpectedElement(PLP.TOOLBAR_COUNT);
                        eyes.check(checkpointName, Target.window().fully()
                                .ignore(toolbarCount));
                    } else {
                        eyes.check(checkpointName, Target.window().fully());
                    }
                    break;
                default:
                    eyes.check(checkpointName, Target.window().fully()
                            .ignore(PLP.PRODUCT_ITEMS_REGION));
            }
        }
    }

    public void EnterQuantityOnPLPPage(String qty){
        hoverOnElement(SearchResult.INPUT_VYPE_FR_QTY);
        waitForExpectedElement(SearchResult.INPUT_VYPE_FR_QTY).clear();
        waitForExpectedElement(SearchResult.INPUT_VYPE_FR_QTY).sendKeys(qty);
    }

    public void clickOnFirstResultwithAReview() {
        waitForExpectedElement(REVIEW_PRODUCT_TITLE_LINK,10);
        if(getWebDriver().findElements(REVIEW_PRODUCT_TITLE_LINK).size()>0)
            clickFirstElementByQueryJSExecutor(REVIEW_PRODUCT_TITLE_LINK);
        else  assertThat(true).as("ERROR clickOnFirstResultwithAReview: couldn't find a search result with a review").isFalse();

    }

    public void selectFirstSubscriptionProduct() {
        List<WebElement> products = waitForExpectedElements(PRODUCT);
        for (WebElement product: products) {
            if (product.findElements(SUBSCRIPTION_PRODUCT).size() > 0) {
                product.findElement(lnkProductItem).click();
                break;
            }
        }
    }

    public void productIsReviewedOrNot() {
        String reviewCount = getWebDriver().findElements(TOTAL_PRODUCTS_REVIEW_COUNT).get(0).getText().trim();
        double count = Double.parseDouble(reviewCount);
        if (count > 0.0) {
            System.setProperty("PDPReview.key", "Yes");
            getWebDriver().findElements(TOTAL_PRODUCTS).get(0).click();
        } else if (count == 0.0) {
            System.setProperty("PDPReview.key", "No");
            getWebDriver().findElements(TOTAL_PRODUCTS).get(0).click();
        } else LOG.info("Review option is disabled from backend");
    }

    public void assertReviewRatingIsPresentForEveryProduct(){
        assertEquals(getWebDriver().findElements(TOTAL_PRODUCTS_REVIEW_COUNT).size(), getWebDriver().findElements(TOTAL_PRODUCTS).size());
    }

    public void clickOnProductLinkOnBasisOfIndex(String strIndex) {
        lnkProductItem = By.cssSelector("ol.products.list.items.product-items li:nth-child(" + strIndex + ") a.product-item-link");
        if(UrlBuilder.isMobile()){
            scrollToElement(lnkProductItem);
        }
        clickUsingJS(lnkProductItem);
    }

    public void clicksOnProductLinkWithFirstSKUOOSAndNavigateToPDP() {
        waitForAjaxElementNotToBePresent(getWebDriver(),5);
        scenarioContext.setContext(Context.PRODUCT_OOS, false);
        if (getWebDriver().findElements(OOS_PRODUCT_COLOR_SWATCH).size() > 0){
            scenarioContext.setContext(Context.PRODUCT_OOS, true);
            clickByElementByQueryJSExecutor(OOS_PRODUCT_DEVICE_LINK);
        }
        else if (getWebDriver().findElements(OOS_PRODUCT_STRENGTH_SWATCH).size() > 0){
            scenarioContext.setContext(Context.PRODUCT_OOS, true);
            clickByElementByQueryJSExecutor(OOS_PRODUCT_FLAVOR_LINK);
        }
    }

    public String getSearchResult(){
        return waitForExpectedElement(searchResultsText).getText();}

    public void clickFirstProductImageNavigatePDPPage() {
        switch ( UrlBuilder.getLocale()) {
            case "vusefr":
                waitForItemToBeClickableAndClick(FIRST_PRODUCT_IMAGE, 5);
                assertTrue(isElementDisplayedBySeconds(PDP_DESCRIPTION, 5));
                break;
            case "lyftse":
                waitForItemToBeClickableAndClick(FIRST_PRODUCT_IMAGE_LYFT, 5);
                assertTrue(isElementDisplayedBySeconds(PDP_DESCRIPTION_LYFT, 5));
                break;
        }

    }
    public void selectProductByIndex(Integer productIndex_plp) throws Exception {
        String itemSelector="";
        List<WebElement> productItemsEle=productItemsEle=getWebDriver().findElements(PRODUCT_ITEMS);
        switch ( UrlBuilder.getLocale()) {
            case "vuseuk":
            case "vuseza":
                itemSelector="#product-list-plp > li:nth-child("+productIndex_plp+")>div>div>div>a";
                break;
            case "vusefr":
                itemSelector="#product-list-plp > li:nth-child("+productIndex_plp+")>div > div> h3 > a";
                break;
            default:
                itemSelector="#product-list-plp > li:nth-child(1)>div>div>div>a";


        }
        if(productItemsEle.size()>0 &&productItemsEle.size()>=productIndex_plp)
            clickUsingJS(By.cssSelector(itemSelector));
        else
            throw new Exception("There is no product on plp page or index isn't correct.");
    }

    public boolean isSearchSuggestionListPresent(){
        return isElementDisplayedBySeconds(SEARCH_SUGGESTION_ITEM,5);

    }
    public void selectSearchSuggestion(String searchTermKey) {
        String searchTerm = UrlBuilder.getMessage(searchTermKey);
        try {
            waitForExpectedElements(SEARCH_SUGGESTION_ITEM).stream()
                    .forEach(webElement -> {
                        if(webElement.getText().toLowerCase().equals(searchTerm.toLowerCase())) {
                            webElement.click();
                            return;
                        }
                    });
        }catch(StaleElementReferenceException e){
        }
    }

    public int getFitlerCount(){
        String productCountStr=waitForExpectedElement(FILTER_BY_PRODUCT_COUNT).getText();
        return Integer.parseInt(Pattern.compile("[^0-9]").matcher(productCountStr).replaceAll("").trim());
    }

    public int getProductTotalNumber() {
        return Integer.parseInt(waitForExpectedElement(PRODUCT_TOTAL_NUMBER).getText());
    }

    public void clickFilterPopupCancel(){
        waitAndClickByElementByJSExecutor(FILTER_CANCEL_BUTTON, 5);
    }

    public void selectFirstBuyableProduct_Lyft_Image(int productPosition) {
        try {
            webDriver.findElements(FIRST_BUYABLE_PRODUCT_LINK_Lyft_Image).get(productPosition).click();
        }
        catch(Exception ex) {
            LOG.info("No any buyable product present");
        }
    }
}
