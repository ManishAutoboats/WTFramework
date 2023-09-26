package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.SearchResult;
import com.salmon.test.page_objects.gui.constants.Context;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SearchPageSteps {
    private static final By HOME_SEARCH_ICON = By.cssSelector("button.action.search");
    private SearchResult searchResult;
    private static final Logger LOG = LoggerFactory.getLogger(SearchPageSteps.class);
    int filterCount;
    int productCount;

    public SearchPageSteps(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    @Then("^search results title of '(.*)' is returned$")
    public void searchResultsTitleIsReturned(String selectedSearch) {
        if (!UrlBuilder.getLocale().equals("vusede")) {
            LOG.info("Search Result Title : " + searchResult.getSearchResultTitle());
            assertTrue(searchResult.getSearchResultTitle().toLowerCase().contains(UrlBuilder.getMessage(selectedSearch).toLowerCase()));
        }
    }

    @And("^search results are returned saying '(.*)'$")
    public void searchResultsAreReturnedTest(String expectedResults) {
        if (!UrlBuilder.getLocale().equals("vusede")) {
            LOG.info("Results : " + searchResult.getSearchResultTitle());
            if (UrlBuilder.getLocale().equals("ie")) {
                assertTrue(searchResult.getSearchHeading().contains(UrlBuilder.getMessage(expectedResults)));
            } else {
                assertTrue(searchResult.getSearchResultTitle().contains(UrlBuilder.getMessage(expectedResults)));
            }
        }
    }

    @Then("^check search result list$")
    public void checkSearchResultList() {
        searchResult.loopThurProducts();
    }

    @And("^get all lists from page$")
    public void getAllListsFromPage() {
        searchResult.getAllLists();
    }

    @And("user filters by product epods$")
    public void filterByproduct(){
        searchResult.waitAndClickByElementByJSExecutor(searchResult.FILTER_BY_PRODUCT,5);
        filterCount=searchResult.getFitlerCount();
    }

    @And("get fitler count$")
    public void getFilterCount(){
        filterCount=searchResult.getFitlerCount();
    }

    @And("user filters by product strenth$")
    public void filterByproductStrenth(){
        searchResult.waitAndClickByElementByJSExecutor(searchResult.FILTER_BY_PRODUCT,5);
    }

    @And ("user clicks done in filter pop-up$")
    public void filterPopupDone() {
        switch (UrlBuilder.getLocale()) {
            case "veloza":
                searchResult.waitAndClickByElementByJSExecutor(searchResult.FILTER_DONE_VELOZA, 5);
                break;
            case "vusefr":
                searchResult.waitAndClickByElementByJSExecutor(searchResult.FILTER_APPLY_BUTTON, 5);
                break;
            default:
                searchResult.waitAndClickByElementByJSExecutor(searchResult.FILTER_APPLY_BUTTON, 5);
        }}

    @And ("user clicks cancel in filter pop-up$")
    public void filterPopupCancel() {
        searchResult.clickFilterPopupCancel();
    }

    @And("^search notification says '(.*)'$")
    public void searchNotificationSaysYourSearchReturnedNoResults(String expectedNotificaiton) {
        switch (UrlBuilder.getLocale()) {
            case "uk":
            case "vuseuk":
                searchResult.assertTrueWithCustomError(UrlBuilder.getMessage(expectedNotificaiton), searchResult.waitForExpectedElement(searchResult.searchRestulsCXRedesign).getText());
                break;
            case "fr":
            case "vusefr":
            case "vusede":
                assertTrue(searchResult.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(("NoResultsReturnedText.key"))));
                break;
            case "mx":
            case "vusemx":
            case "vypeit":
            case "vuseco":
            case "vuseit":
            case "vuseza":
                assertTrue(searchResult.getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage("NoResultsReturnedText.key").toLowerCase()));
                break;
            default:
                searchResult.assertTrueWithCustomError(UrlBuilder.getMessage(expectedNotificaiton), searchResult.getSearchResultText());
        }
    }

    @And("^confirm number of results returned is greater than '(\\d+)'$")
    public void confirmNumberOfResultsReturnedIsGreaterThan(int greatThanNum) {
        assertTrue("Search results number isn't as expected", searchResult.returnNumOfSearchResults() > greatThanNum);
    }

    @And("^confirm number of results returned is same as it in filter pop-up$")
    public void confirmNumberOfResultsReturnedIsSameAsFilterPopup() {
        assertEquals(filterCount,searchResult.getProductTotalNumber());
    }

    @And("^click first result$")
    public void clickFirstResult() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                searchResult.selectFirstBuyableProduct_Lyft(0);
                break;
            case "pl":
                searchResult.selectFirstBuyableProduct_Pl();
                break;
            default:
                searchResult.selectSearchResult();
        }
    }

    @And("^click first buyable product$")
    public void clickFirstBuyableProduct() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                searchResult.selectFirstBuyableProduct_Lyft(0);
                break;
            case "pl":
                searchResult.selectFirstBuyableProduct_Pl();
                break;
            default:
                searchResult.selectFirstBuyableProduct();
        }
    }

    @And("^click first Lyft Lab product$")
    public void clickFirstLyftLabProduct() throws Throwable {
        searchResult.clickFirstLyftLabProduct();
    }

    @And("Click specific result of '(.*)'")
    public void selectSpecificResult(String productName) {
        searchResult.selectSpecificSearchResult(productName);
    }

    @And("^from PLP click add to cart$")
    public void fromPLPClickAddToCart() {
        searchResult.clickSecondAddToCartButtonPLP();
    }

    @And("^click add to cart on a product has multiple swatches$")
    public void clickAddToCartOnProductHasMultipleColours() {
        if (!UrlBuilder.getLocale().equals("uk")) {
            searchResult.clickAddToCartOnProductHasMultipleSwatches();
        }
    }

    @And("^out of stock indicator is displayed to the user$")
    public void outOfStockIndicatorIsDisplayedToTheUser() {
        searchResult.assertTrueWithCustomError("Out of stock", searchResult.getOutOfStockMesssage());
    }

    @And("^assert product strength is displayed$")
    public void assertProductStrengthIsDisplayed() {
        if (UrlBuilder.getLocale().equals("vuseuk")) {
            WebElement strengthSwatches = searchResult.waitForExpectedElement(By.cssSelector("div.swatch-attribute.vuse_strength"), 10);
            assertTrue(strengthSwatches.isDisplayed());
        } else {
            WebElement strengthSwatches = searchResult.waitForExpectedElement(By.cssSelector("div.swatch-attribute.vype_strength"), 10);
            assertTrue(strengthSwatches.isDisplayed());
        }
    }

    @And("^assert filters are in place as expected$")
    public void assertFiltersAreInPlaceAsExpected() {
        if (UrlBuilder.getLocale().equals("uk") || (UrlBuilder.getLocale().equals("vuseuk") || (UrlBuilder.getLocale().equals("vypeit") || (UrlBuilder.getLocale().equals("vuseit"))))) {
            searchResult.waitForExpectedElement(searchResult.SEARCH_PAGE_FILTER);
        } else {
            if (!searchResult.doesURLContain("/mx/es") && (!searchResult.doesURLContain("vype.non-prod.marketing.bat.net/it/it/") && (!searchResult.doesURLContain("govype.com/it/it/")))) {
                searchResult.waitForExpectedElement(By.cssSelector("div.block-content.filter-content")).isDisplayed();
            }
        }
    }

    @And("^assert product colour is displayed$")
    public void assertProductColourIsDisplayed() {
        if (!searchResult.doesURLContain("/uk/") && (!searchResult.doesURLContain("/gb/en/"))) {
            WebElement strengthSwatches = searchResult.waitForExpectedElement(By.cssSelector("div.swatch-attribute.vype_color"), 10);
            assertTrue(strengthSwatches.isDisplayed());
        }
    }

    @And("^open eLiquid Flavours from the Categories menu$")
    public void openELiquidFlavoursFromTheCategoriesMenu() throws Throwable {
        if (searchResult.doesURLContain("vuse.non-prod.marketing.bat.net/it/it/") || searchResult.doesURLContain("vuse.com/it/it/")) {
            By categoryMenuItem = By.linkText(UrlBuilder.getMessage("flavoursLinkText.key"));
            searchResult.clickByElementByQueryJSExecutor(categoryMenuItem);
            searchResult.waitForExpectedElement(By.cssSelector("ol.products.list.items.product-items")).isDisplayed();
        } else {
            By categoryMenuItem = By.cssSelector("li.level0.nav-2.category-item.column.parent:nth-child(2) a.column > span:nth-child(1)");
            assertTrue("Expected Category result isn't present ", searchResult.waitForExpectedElement(categoryMenuItem).isDisplayed());
            searchResult.waitForExpectedElement(categoryMenuItem).click();
            Thread.sleep(2000);
        }
    }

    @And("^user clicks on '(.*)' from Menu$")
    public void clickCategoryLink(String strExpectedLink) {
        try {
            searchResult.waitForExpectedElement(By.linkText(Props.getProp(strExpectedLink)), 30);
            searchResult.waitForExpectedElement(By.linkText(Props.getProp(strExpectedLink))).click();
            searchResult.waitForExpectedElement(searchResult.searchResultsHeading, 30);
        } catch (Exception ex) {
            LOG.info("Failed to click on Category links from Menu due to error: " + ex.getMessage());
        }
    }

    @And("^user redirects to category page -lyft$")
    public void redirectsToCategoryPage() {
        searchResult.waitForExpectedElement(By.cssSelector(".header-content-right.main-navigation.desktop-only>div>div>div>ul>li:nth-child(1)>a")).click();
    }

    @And("^user click on search icon and enter product '(.*)' in the search bar$")
    public void userClicksOnSearchIconAndEnterProductInSearchBar(String strSearchTerm) {
        switch (UrlBuilder.getLocale()) {
            case "glode":
                if (UrlBuilder.isMobile()) {
                    searchResult.waitForExpectedElement(searchResult.SEARCH_ICON_MOBILE, 5);
                    searchResult.clickByElementByQueryJSExecutor(searchResult.SEARCH_ICON_MOBILE);
                }
                searchResult.waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key"))).click();
                searchResult.waitForExpectedElement(searchResult.searchInputBox, 20);
                searchResult.enterDataAndWait(searchResult.searchInputBox, UrlBuilder.getMessage(strSearchTerm));
                searchResult.clickByElementByQueryJSExecutor(HOME_SEARCH_ICON);
                searchResult.eyesCheckSearchResultPage();
                break;
            case "pl":
                if (UrlBuilder.isMobile()) {
                    searchResult.waitForExpectedElement(searchResult.SEARCH_ICON_MOBILE, 5);
                    searchResult.clickByElementByQueryJSExecutor(searchResult.SEARCH_ICON_MOBILE);
                }
                try{
                    searchResult.waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key"))).click();}
                catch(Exception ex){
                    searchResult.waitAndClickByElementByJSExecutor(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key")),5);
                }
                searchResult.waitForExpectedElement(searchResult.searchInputBox, 20);
                searchResult.enterDataAndWait(searchResult.searchInputBox, UrlBuilder.getMessage(strSearchTerm));
                searchResult.clickByElementByQueryJSExecutor(HOME_SEARCH_ICON);
                searchResult.eyesCheckSearchResultPage();
                break;
            default:
                try {
                    searchResult.waitForExpectedElement(By.cssSelector(UrlBuilder.getMessage("searchIconRef.key"))).click();
                    searchResult.waitForExpectedElement(searchResult.searchInputBox, 20);
                    searchResult.enterDataAndWait(searchResult.searchInputBox, UrlBuilder.getMessage(strSearchTerm));
                } catch (Exception ex) {
                    LOG.info("Failed enter search term due to error: " + ex.getMessage());
                }
        }
    }

    @And("^user clicks on a product link from the Search Results and navigate to PDP$")
    public void userClicksOnProductLinkFromSearchResultsNavigateToPDP() {
        try {
            searchResult.waitForExpectedElement(searchResult.lnkProductFromAutoSearchList, 20).click();
            assertTrue(searchResult.waitForExpectedElement(searchResult.productDescriptionPDP).isDisplayed());
        } catch (Exception ex) {
            LOG.info("Failed enter search term due to error: " + ex.getMessage());
        }
    }

    @And("^from PLP click add to cart for a product with variants without selecting strength or colour$")
    public void fromPLPClickAddToCartForAProductWithVariantsWithoutSelectingStrenthOrColour() {
        searchResult.clickMultipleVariantAddToCartButtonPLP();
    }

    @And("^click on the first result with a review$")
    public void clickOnTheFirstResultWithAReview() {
        searchResult.clickOnFirstResultwithAReview();
    }

    @And("^click third result$")
    public void clickThirdResult() {
        searchResult.selectFirstBuyableProduct_Lyft(2);
    }

    @And("^click first subscription product$")
    public void clickFirstSubscription() {
        searchResult.selectFirstSubscriptionProduct();
    }

    @And("^clicks on product link on the basis of index '(.*)'$")
    public void clicksOnProductLinkOnBasisOfIndex(String strIndex) {
        searchResult.clickOnProductLinkOnBasisOfIndex(strIndex);
    }

    @And("^clicks on product link with first SKU OOS and navigate to PDP$")
    public void clicksOnProductLinkWithFirstSKUOOSAndNavigateToPDP() {
       searchResult.clicksOnProductLinkWithFirstSKUOOSAndNavigateToPDP();
    }

    @And("^search results returned '(.*)'$")
    public void searchResultsReturned(String expectedResults) {
        if (UrlBuilder.getLocale().equals("vuseuk")) {
            LOG.info("Results : " + searchResult.getSearchResult());
            assertTrue(searchResult.getSearchResult().contains(UrlBuilder.getMessage(expectedResults)));
        }
    }

    @And("^closest match product list is displayed$")
    public void getListsFromPage() {
        searchResult.getAllLists();
    }

    @And("^click on first product image and PDP page is open$")
    public void click_on_first_product_image_verify_PDP_page() {
        searchResult.clickFirstProductImageNavigatePDPPage();
    }

    @And("^select product by index \"([^\"]*)\" on plp page$")
    public void selectProductByIndexOnPlpPage(String productIndex)  {
        Integer productIndex_plp=Integer.parseInt(UrlBuilder.getMessage(productIndex).trim());
        try {
            searchResult.selectProductByIndex(productIndex_plp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^get number of results returned$")
    public void getProductTotalNumber() {
        productCount=searchResult.getProductTotalNumber();
    }

    @And("^confirm number of results returned is same as it was before$")
    public void confirmNumberOfResultsReturnedIsSameAsBefore() {
        assertEquals(productCount,searchResult.getProductTotalNumber());
    }

    @And("^click first result image$")
    public void clickFirstResultImage() {
        searchResult.selectFirstBuyableProduct_Lyft_Image(0);}
}