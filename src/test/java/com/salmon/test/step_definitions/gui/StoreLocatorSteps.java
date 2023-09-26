package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.StoreLocatorPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static com.salmon.test.page_objects.gui.constants.Locale.valueOf;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.testng.Assert.assertTrue;

public class StoreLocatorSteps {

    protected static final Logger LOG = LoggerFactory.getLogger(StoreLocatorSteps.class);
    private StoreLocatorPage storeLocatorPage;
    private String expectedAddress;

    public StoreLocatorSteps(StoreLocatorPage storeLocatorPage) {
        this.storeLocatorPage = storeLocatorPage;
    }

    @And("^(?:Glo|user) click on StoreLocator link$")
    public void gloClicksOnStoreLocatorLink() {
        storeLocatorPage.clickOnStoreLocatorLink();
    }

    @When("^(?:Glo) enters (.*) as lookup address$")
    public void gloEntersAddressKeyAsLookupAddress(String addressKey) throws Exception {
        storeLocatorPage.enterSearchAddress(addressKey);
    }

    @Then("^(?:Glo) assert displayed address is (.*)$")
    public void gloAssertDisplayedAddressIs(String expectedAddressKey) throws Exception {
        Thread.sleep(4000);
        String addressDisplayed = storeLocatorPage.getSelectedAddress();
        //assertTrue(addressDisplayed.contains(UrlBuilder.getMessage(expectedAddressKey)));
        storeLocatorPage.assertTrueExpectedTextContainsActualTextWithCustomError(UrlBuilder.getMessage(expectedAddressKey), addressDisplayed.trim());
    }

    @And("^(?:Glo) assert Shop Type dropdown is displayed$")
    public void gloAssertShopTypeDropdownIsDisplayed() {
        storeLocatorPage.isShopTypeDropDownDisplayed();
    }

    @And("^(?:Glo) assert Product Type dropdown is displayed$")
    public void gloAssertProductTypeDropdownIsDisplayed() {
        storeLocatorPage.isProductTypeDropDownDisplayed();
    }

    @And("^(?:Glo) assert option none is selected by default for both Shop and Product Types$")
    public void gloAssertOptionNoneIsSelectedByDefaultForBothShopAndProductTypes() {
        assertThat(storeLocatorPage.getSelectedValueFromShopTypeDropDown()).isEmpty();
        assertThat(storeLocatorPage.getSelectedValueFromProductTypeDropDown()).isEmpty();
    }

    @And("^(?:Glo) assert Shop Type options (.*) available in the dropdown$")
    public void gloAssertShopTypeOptionsAvailableInTheDropdown(String expectedDropDown) {
        assertThat(storeLocatorPage.getOptionsFromShopTypeDropDown())
                .isEqualTo(getExpectedOptionsList(expectedDropDown));
    }

    @And("^(?:Glo) assert Product Type options (.*) available in the dropdown$")
    public void gloAssertProductTypeOptionsAvailableInTheDropdown(String expectedDropDown) {
        assertThat(storeLocatorPage.getOptionsFromProductTypeDropDown())
                .isEqualTo(getExpectedOptionsList(expectedDropDown));
    }

    @When("^(?:Glo) clicks on Route button for the displayed address$")
    public void gloClicksOnRouteButtonForTheDisplayedAddress() {
        storeLocatorPage.clickOnRouteButton();
    }

    @Then("^(?:Glo) assert google maps are opened in a new tab with Destination pre-populated with (.*)$")
    public void gloAssertGoogleMapsAreOpenedInANewTabWithDestinationPrePopulatedWith(String expectedCountryKey) {
        String actualCountry = storeLocatorPage.getPrePopulatedDestinationCountryInGoogleMapsInNewTab();
        switch (UrlBuilder.getLocale()) {
            case "glode":
                LOG.info("Actual Country is: "+actualCountry);
                try {
                    assertTrue(((actualCountry).contains(UrlBuilder.getMessage(expectedCountryKey)) || ((actualCountry).contains(UrlBuilder.getMessage("storeLocatorLocalizedCountry.key")))));
                } catch (Exception e) {
                    storeLocatorPage.clickByElementByQueryJSExecutor(storeLocatorPage.GOOGLE_AGREE);
                    assertTrue(((actualCountry).contains(UrlBuilder.getMessage(expectedCountryKey)) || ((actualCountry).contains(UrlBuilder.getMessage("storeLocatorLocalizedCountry.key")))));
                }
                break;
            case "pl":
                LOG.info("Actual Country is: "+actualCountry);
                assertTrue(((actualCountry).contains(UrlBuilder.getMessage(expectedCountryKey)) || ((actualCountry).contains(UrlBuilder.getMessage("storeLocatorLocalizedCountry.key")))));
                break;
            default:
                assertThat(actualCountry).contains(UrlBuilder.getMessage(expectedCountryKey));
        }
    }

    @When("^(?:Glo) selects the first address from lookup$")
    public void gloSelectsTheFirstAddressInTheLookup() {
        storeLocatorPage.selectFirstAddressFromLookUp();
    }

    @And("^(?:Glo|user) should see the stores as per the selected (.*)$")
    public void gloShouldSeeTheStoresAsPerTheSelectedStoreLocatorKey(String key) {
        List<WebElement> webElements = storeLocatorPage.getListOfStoresDisplayed();
        String actual;
        switch (UrlBuilder.getLocale()) {
            case "pl":
                actual = webElements.stream().filter(element -> element.isDisplayed() == true).findFirst().get().getAttribute("title");
                break;
            case "vuseco":
                actual = webElements.stream().filter(element->element.isDisplayed()==true).findFirst().get().getText();
                break;
            default:
                actual = webElements.stream().findFirst().get().getText();
        }
        String expectedMatch = Arrays.stream(UrlBuilder.getMessage(key).split(",")).map(String::trim).collect(Collectors.toList()).get(1);

        switch (valueOf(getLocale().toUpperCase())) {
            case GLODE:
            case VUSECO:
                MatcherAssert.assertThat(actual, containsStringIgnoringCase(expectedMatch));
                break;
            case IT:
            case PL:
                IntStream.range(0, webElements.size())
                        .forEach(value -> assertThat(webElements.get(value).getText()).containsIgnoringCase(expectedMatch).as(
                                String.format("Address %d in the storeLocator search result is not matching:  " +
                                                "\n\nActual: %s\n should contain: \nExpected: %s\n\n", value + 1,
                                        webElements.get(value).getText(), expectedMatch.toUpperCase())));
                break;
            default:
                throw new IllegalStateException("Unexpected Locale : " + getLocale());
        }
    }

    @When("^(?:Glo|Vuse) select to filter by (.*) with (.*)$")
    public void gloSelectToFilterBy(String type, String value) throws Exception {
        storeLocatorPage.selectShopOrProductType(type, value);
    }

    @Then("^the results should be filtered accordingly and should contain (.*)$")
    public void theResultsShouldBeFilteredAccordinglyAndShouldContain(List<String> expected) {
        boolean found;
        List<WebElement> searchResults = storeLocatorPage.getStoreOrProductTypeFromStoreLocatorSearchResults();
        switch (valueOf(getLocale().toUpperCase())) {
        case VUSEDE:
            for(WebElement ele:searchResults){
                try{
                assertTrue(ele.getText().contains(expected.get(0)));
                break;}
                catch(Exception e){
                    continue;
                }
            }
            break;
            case IT:
                for (WebElement store: searchResults) {
                    found = false;
                    for (String storeType: expected) {
                        if (store.getText().toLowerCase().contains(storeType.toLowerCase())) {
                            found = true;
                            break;
                        }
                    }
                    assertThat(found).as("ERROR: could not find expected store title").isTrue();
                }
                break;
        default:
            searchResults.forEach(webElement -> {
                if (expected.size() > 1) {
                    MatcherAssert.assertThat(webElement.getText(),
                            anyOf(containsStringIgnoringCase(expected.get(0)), containsStringIgnoringCase(expected.get(1))));
                } else {
                    MatcherAssert.assertThat(webElement.getText(), containsStringIgnoringCase(expected.get(0)));
                }
            });
        }
    }

    private List<String> getExpectedOptionsList(String expectedListKey) {
        return Arrays.stream(UrlBuilder.getMessage(expectedListKey).split(","))
                .map(String::trim)
                .collect(toList());
    }

    @When("^Glo clicks on any of the listed stores in the search result page$")
    public void gloClicksOnAnyOfTheListedStoresInTheSearchResultPage() {
        final WebElement[] webElement = new WebElement[1];
        switch (UrlBuilder.getLocale()) {
            case "pl":
                new WebDriverWait(WebDriverHelper.getWebDriver(), 15)
                        .ignoring(StaleElementReferenceException.class)
                        .until((WebDriver d) -> {
                            webElement[0] = storeLocatorPage.getListOfStoresDisplayed().stream().filter(element -> element.isDisplayed() == true).findFirst().get();
                            return true;
                        });
                expectedAddress = webElement[0].getAttribute("title");
                storeLocatorPage.clickUsingJS(webElement[0]);
                break;
            default:
                webElement[0] = storeLocatorPage.getListOfStoresDisplayed().stream().findFirst().get();
                expectedAddress = webElement[0].getText();
                storeLocatorPage.clickElementByQueryJSExecutor(webElement[0]);
        }
    }

    @Then("^Glo should see its location on the google map$")
    public void gloShouldSeeItsLocationOnTheGoogleMap() {
        assertThat(storeLocatorPage.isGoogleMapDisplayed()).isTrue();
        assertThat(storeLocatorPage.isSelectedAddressPopUpDisplayedInGoogleMap()).isTrue();
        String actualAddress = storeLocatorPage.getAddressDisplayedOnGoogleMapPopup();
        assertThat(actualAddress).contains(expectedAddress);
    }

    @When("^Glo clicks on the 'Route' link on the store finder overlay on the map$")
    public void gloClicksOnTheRouteLinkOnTheStoreFinderOverlayOnTheMap() {
        storeLocatorPage.clickOnRouteLinkOnGoogleMapPopup();
    }

    @Then("^eyes check Store Locator page$")
    public void eyesCheckStoreLocatorPage() {
        storeLocatorPage.eyesCheckStoreLocatorPage();
    }

    @And("^assert product stocks for selected location on Store Finder$")
    public void assertProductStocksForSelectedLocation() {
        List<WebElement> lstStocks = storeLocatorPage.getProductStocksListOnStoreFinder();
        for(WebElement ele: lstStocks){
            assertTrue(ele.getText().equals("EPEN") || ele.getText().equals("EPOD") || ele.getText().equals("ELIQUIDE"));
        }
    }

    @And("^user clicks on a store and assert product stocks on store details pop-up$")
    public void userClicksOnStoreAndAssertProductStocksOnStoreDetailsPopUp() {
        storeLocatorPage.clickProductStock();
        assertTrue(storeLocatorPage.waitForExpectedElement(StoreLocatorPage.STORE_PRODUCT_STOCKS_ON_MAP,10).isDisplayed());
    }

    @And("^assert filter by Shop and filter by Product dropdowns are displayed$")
    public void assertFilterByShopAndProductDropdownsAreDisplayed() {
        assertTrue(storeLocatorPage.waitForExpectedElement(storeLocatorPage.SHOP_TYPE_FILTER,10).isDisplayed());
        assertTrue(storeLocatorPage.waitForExpectedElement(storeLocatorPage.PRODUCT_TYPE_FILTER).isDisplayed());
    }

    @And("^assert user is redirected to maps to find directions to the store$")
    public void assertUserIsRedirectedToMapsToFindStoreDirections() {
    switch (valueOf(getLocale().toUpperCase())) {
        case VUSEDE:
            assertTrue(storeLocatorPage.waitForExpectedElement(storeLocatorPage.STORE_LOCATOR_MAP_BOX_DIRECTIONS, 10).isDisplayed());
            assertTrue(storeLocatorPage.waitForExpectedElement(storeLocatorPage.MAP_BOX_DIRECTION_OPTIONS, 10).isDisplayed());
            break;
        }
    }

    @And("^assert CMS blocks on top and bottom of Store locator page$")
    public void assertTopAndBottomCMSBlocksOnStoreLocatorPage() {
        assertTrue(storeLocatorPage.getWebDriver().findElements(storeLocatorPage.STORE_LOCATOR_TOP_BOTTOM_BLOCKS).size()==2);
    }

    @And("^user clicks on Shop type filter icon$")
    public void userClickOnShopTypeFilterIcon() throws Throwable {
        storeLocatorPage.clickByElementByQueryJSExecutor(storeLocatorPage.SHOP_TYPE_FILTER);
    }

    @And("^user clicks on Product type filter icon$")
    public void userClickOnProductTypeFilterIcon() throws Throwable {
        storeLocatorPage.clickByElementByQueryJSExecutor(storeLocatorPage.PRODUCT_TYPE_FILTER);
    }

    @And("^(?:Vuse|Glo) select filter by (.*) type and assert results are filtered accordingly$")
    public void selectFilterByTypeAndAssertResultsAreFiltered(String strType,DataTable dtList) throws Throwable {
        storeLocatorPage.selectFilterByTypeAndAssertResultsAreFiltered(strType,dtList);
    }

    @And("^assert filter by Category dropdown is displayed$")
    public void assertFilterByCategoryDropdownsIsDisplayed() {
        assertTrue(storeLocatorPage.waitForExpectedElement(storeLocatorPage.GLO_CATEGORY_TYPE_FILTER,10).isDisplayed());
    }

    @And("^(?:Vuse|Glo) select filter by '(.*)' type and assert results$")
    public void selectFilterByTypeAndAssertResultsAreFiltered(String strType) throws Throwable {
        storeLocatorPage.selectFilterByTypeAndAssertResultsAreFiltered(strType);
    }

    @Then("^assert the location should be selected$")
    public void assertTheLocationShouldBeSelected() {
        String locatorName = UrlBuilder.getMessage("LocatorName.key");
        assertTrue(storeLocatorPage.getSearchInputText().toLowerCase().contains(locatorName));
    }

    @And("^user can click suggest route$")
    public void userCanClickSuggestRoute() {
        storeLocatorPage.clickSuggestRoute();
    }

    @And("^assert google maps are opened in a new tab$")
    public void assertGoogleMapsAreOpenedInANewTab() {
        assertTrue(storeLocatorPage.isGoogleMapWindownOpened());
    }

    @Then("^user select the store category or assortment from dropdown$")
    public void userSelectTheStoreCategoryOrAssortmentFromDropdown(DataTable rows) {
        List<List<String>> datas=rows.raw();
        for(List<String> data:datas){
            if ("category".equalsIgnoreCase(data.get(0))) {
                storeLocatorPage.selectCategoryType(data.get(1));
            } else if ("assortment".equalsIgnoreCase(data.get(0))) {
                storeLocatorPage.selectAssortmentType(data.get(1));
            }
            assertTrue(storeLocatorPage.getLocatorListSize() > 0);
        }
    }

    @When("^user select the store category and \"([^\"]*)\" from dropdown$")
    public void userSelectTheStoreCategoryAndFromDropdown(String name){
        storeLocatorPage.selectCategoryType(name);
    }

    @When("^user navigate menthol ban page$")
    public void userNavigateMentholBanPage() {
        storeLocatorPage.navigateToGivenPage("mentholBan.key");
    }

    @Then("^assert map icon is displayed$")
    public void assertMapIconIsDisplayed() {
        assertTrue(storeLocatorPage.isMapIconDisplayed());
    }

    @And("^find store near \"([^\"]*)\"$")
    public void findStoreNear(String aimStore)  {
        storeLocatorPage.findNearStore(aimStore);
    }
}
