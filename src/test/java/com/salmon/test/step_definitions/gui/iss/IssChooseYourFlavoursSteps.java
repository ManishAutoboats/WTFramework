package com.salmon.test.step_definitions.gui.iss;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.admin.LoginPage;
import com.salmon.test.page_objects.gui.admin.StorePage;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.iss.IssChooseYourFlavoursPage;
import com.salmon.test.page_objects.gui.iss.IssHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

import static com.salmon.test.page_objects.gui.constants.Context.ISS_PLP_SUBSCRIPTION_FLAVOUR_DATA;
import static com.salmon.test.page_objects.gui.constants.Context.MAIN_SITE_SUBSCRIPTION_FLAVOUR_DATA;
import static org.assertj.core.api.Assertions.assertThat;

public class IssChooseYourFlavoursSteps {
    private ScenarioContext scenarioContext;
    private String searchString;
    private String productSelected;
    private int numProductsVisible;
    private int numberOfItemsInBasket = 0;
    private IssHomePage issHomePage;
    private IssChooseYourFlavoursPage issChooseYourFlavoursPage;
    private StorePage storePage;
    private LoginPage loginPage;
    private HomePage homePage;


    public IssChooseYourFlavoursSteps(IssHomePage issHomePage,
                                      IssChooseYourFlavoursPage issChooseYourFlavoursPage,
                                      StorePage storePage,
                                      LoginPage loginPage,
                                      TestContext context,
                                      HomePage homePage) {
        this.issHomePage = issHomePage;
        this.issChooseYourFlavoursPage = issChooseYourFlavoursPage;
        this.storePage = storePage;
        this.loginPage = loginPage;
        scenarioContext = context.getScenarioContext();
        this.homePage = homePage;
    }

    @Then("^user is on the Choose your Flavours screen$")
    public void userIsOnTheChooseYorFalvoursScreen() {
        assertThat(issHomePage.pageHeadingIs("chooseYourFlavoursText")).as("ERROR: not on the choose your flavours page").isTrue();
    }

    @And("^should see an additional link start Again$")
    public void shouldSeeAnAdditionalLinkStartAgain() {
        assertThat(issChooseYourFlavoursPage.isStartAgainLinkDisplayed()).as("ERROR: Start Again link is not disdplayed").isTrue();
    }

    @When("^he clicks on the link Start Again$")
    public void heClicksOnTheLinkStartAgain() {
        issChooseYourFlavoursPage.clickOnStartAgain();
    }

    @Then("^he gets a warning overlay$")
    public void heGetsAWarningOverlay() {
        assertThat(issChooseYourFlavoursPage.overlayHeadingIs("areYouSureYouWantToLeaveText")).as("ERROR: are you sure overlay not displayed").isTrue();
    }

    @When("^user clicks \"([^\"]*)\"$")
    public void userClicks(String yesOrNo) throws Throwable {
        issChooseYourFlavoursPage.clickOnOverlay(yesOrNo);
    }

    @And("^he sees a plus CTA on at least one flavour$")
    public void heSeesAPlusCTAOnAtLeastOneFlavour() {
        assertThat(issChooseYourFlavoursPage.productHasAddToCartCTA()).as("ERROR: noproducts with a add to cart CTA").isTrue();
    }

    @When("^he clicks on the plus CTA$")
    public void heClicksOnThePlusCTA() {
        issChooseYourFlavoursPage.clickOnPlusCTA();
    }

    @Then("^a pop up opens with strength details$")
    public void aPopUpOpensWithStrengthDetails() {
        issChooseYourFlavoursPage.strengthPopUpIsDisplayed();
    }

    @When("^staff scrolls down$")
    public void staffScrollsDown() {
        issChooseYourFlavoursPage.scrollToPageBottom();
    }

    @Then("^header should be sticky$")
    public void headerShouldBeSticky() {
        assertThat(issChooseYourFlavoursPage.stickyHeaderIsDisplayed()).as("ERROR: stick header is not visible").isTrue();
    }

    @And("^colleague should be able to see header details like \"([^\"]*)\"$")
    public void colleagueShouldBeAbleToSeeHeaderDetailsLike(String detail) {
        assertThat(issChooseYourFlavoursPage.isHeaderDetailVisible(detail)).as("ERROR: header detail "+detail+" is not displayed in sticky header").isTrue();
    }

    @And("^performs a search for \"([^\"]*)\"$")
    public void performsASearchFor(String searchTerm) throws Throwable {
        issChooseYourFlavoursPage.enterSearchTerm(searchTerm);
    }

    @And("^performs a search for \"([^\"]*)\" and selects the item from the suggestion list$")
    public void performsASearchForAndSelectFromSuggestionList(String searchTerm) throws Throwable {
        issChooseYourFlavoursPage.enterSearchTermIntoSearchField(searchTerm);
        issChooseYourFlavoursPage.selectSearchSuggestion(searchTerm);
    }

    @And("^enters \"([^\"]*)\" into the search box$")
    public void entersIntoTheSearchBox(String searchTerm) throws Throwable {
        searchString=  searchTerm;
        issChooseYourFlavoursPage.enterSearchTermIntoSearchField(searchString);
    }

    @Then("^a list of flavour suggestions is displayed$")
    public void aListOfFlavourSuggestionsIsDisplayed() {
        assertThat(issChooseYourFlavoursPage.listOfSuggestionsDisplayed())
                .as("ERROR: Suggestions for search term not displayed").isTrue();
    }

    @And("^he is able to select flavour number \"([^\"]*)\"$")
    public void heIsAbleToSelectFlavourNumber(int index) {
        productSelected = issChooseYourFlavoursPage.selectSearchSuggestion(index);
    }

    @Then("^the search results page is displayed$")
    public void theSearchResultsPageIsDisplayed() {
        assertThat(issChooseYourFlavoursPage.numberOfProductsVisible() > 0 )
                .as("ERROR: no products returned in search results").isTrue();
    }

    @And("^the correct product is displayed$")
    public void theCorrectProductIsDisplayed() {
        assertThat(issChooseYourFlavoursPage.correctProductIsDisplayed(productSelected))
                .as("ERROR incorrect result displayed").isTrue();
    }

    @Given("^a sku is out of stock$")
    public void aSkuIsOutOfStock() throws Exception {
        storePage.clickMenuLinkFromAdminDashboard(storePage.DASHBOARD_CATALOG_LINK);
        storePage.clickLinkFromMenuWindow(storePage.PRODUCTS_LINK);
        storePage.removeAnyDefaultSearchFilters();
        storePage.performSearchSelectStoreViewAndClickEditLink(UrlBuilder.getMessage("oosSku"),UrlBuilder.getMessage("storeView"));
        storePage.makeCurrentSkuOutOfStock();
    }

    @Then("^the user cannot add that sku to his basket$")
    public void theUserCannotAddThatSkuToHisBasket() {
        String oosSku = UrlBuilder.getMessage("oosSkuSearchTerm");
        issChooseYourFlavoursPage.selectProductForSkuDescription(oosSku);
        assertThat(issChooseYourFlavoursPage.strengthSelectable(oosSku))
                .as("ERROR: "+oosSku+" is selectable but shouldn't be").isFalse();
    }

    @Then("^a list of flavour suggestions is not displayed$")
    public void aListOfFlavourSelectionsIsNotDisplayed() {
        assertThat(issChooseYourFlavoursPage.listOfSuggestionsDisplayed())
                .as("ERROR: Suggestions for search term was displayed").isFalse();
    }

    @And("^an add CTA is displayed$")
    public void anAddCTAIsDisplayed() {
        assertThat(issChooseYourFlavoursPage.strengthAddButtonDisplayed())
                .as("ERROR: Strength add button is not displayed").isTrue();
    }

    @Then("^pack quantity is displayed with CTAs to update$")
    public void packQuantityIsDisplayedWithCTAsToUpdate() {
        assertThat(issChooseYourFlavoursPage.packQuantityIsDisplayed(UrlBuilder.getMessage("packQuantityTitle")))
                .as("ERROR: pack quantity title is incorrect").isTrue();
        assertThat(issChooseYourFlavoursPage.updateQuantityCtasDisplayed())
                .as("ERROR: not all update quantity icons displayed").isTrue();

    }

    @And("^add CTA \"([^\"]*)\" clickable$")
    public void addCTAClickable(String isOrNot) throws Throwable {
        if (isOrNot.toLowerCase().contains("no")) {
            assertThat(issChooseYourFlavoursPage.strengthAddButtonClickable())
                    .as("ERROR strength add button should not be clickable but was").isFalse();
        } else {
            assertThat(issChooseYourFlavoursPage.strengthAddButtonClickable())
                    .as("ERROR strength add button should be clickable but was not").isTrue();
        }
    }

    @When("^I add a quantity$")
    public void iAddAQuantity() throws InterruptedException {
        issChooseYourFlavoursPage.addQuantity(2);
    }

    @Then("^he should see \"([^\"]*)\" in front of the Tier when no items are added$")
    public void heShouldSeeInFrontOfTheTierWhenNoItemsAreAdded(String expectedTier) throws Throwable {
        String actualTier = issChooseYourFlavoursPage.getTierDescription();
        assertThat(actualTier.equalsIgnoreCase(expectedTier))
                .as("ERROR: expected tier was "+expectedTier+" but got "+actualTier).isTrue();
    }

    @And("^he should see a clickable i icon in front of the tier$")
    public void heShouldSeeAClickableIIconInFrontOfTheTier() {
        assertThat(issChooseYourFlavoursPage.clickableIDisplayed())
                .as("ERROR: clickable i not displayed").isTrue();
    }

    @Given("^sku \"([^\"]*)\" is \"([^\"]*)\" stock$")
    public void skuIsStock(String skuSearchTerm, String inOrOutOfStock) throws Throwable {
        loginPage.userLoginsMagentoAdminHomePageSuccessfully();
        storePage.clickMenuLinkFromAdminDashboard(storePage.DASHBOARD_CATALOG_LINK);
        storePage.clickLinkFromMenuWindow(storePage.PRODUCTS_LINK);
        storePage.removeAnyDefaultSearchFilters();
        storePage.performSearchSelectStoreViewAndClickEditLink(UrlBuilder.getMessage(skuSearchTerm),UrlBuilder.getMessage("storeView"));
        if (inOrOutOfStock.equalsIgnoreCase("in stock")) {
            storePage.makeCurrentSkuInStock();
        } else if (inOrOutOfStock.equalsIgnoreCase("out of stock")) {
            storePage.makeCurrentSkuOutOfStock();
        } else {
            assertThat(true).as("ERROR: invalid stock state "+inOrOutOfStock+" supplied").isFalse();
        }
    }


    @Then("^the choose your flavours header indicates \"([^\"]*)\" tier$")
    public void theChooseYourFlavoursHeaderIndicatesTier(String tier)  {
        assertThat(issChooseYourFlavoursPage.isTierDisplayed(tier)).as("ERROR incorrect tier is displayed").isTrue();
    }

    @When("^he clicks on the clickable i icon$")
    public void heClicksOnTheClickableIIcon() {
        issChooseYourFlavoursPage.clickIIcon();
    }

    @And("^the description of each tier is displayed$")
    public void theDescriptionOfEachTierIsDisplayed() {
        assertThat(issChooseYourFlavoursPage.tierDescriptionsAreDisplayed())
                .as("ERROR: tier descriptions are incorrect").isTrue();
    }

    @When("^x is clicked he should navigate back to the choose your flavours page$")
    public void xIsClickedHeShouldNavigateBackToTheChooseYourFlavoursPage() {
        issChooseYourFlavoursPage.clickCloseTierOverlay();
        assertThat(issChooseYourFlavoursPage.amIOnTheChooseFlavourspage())
                .as("ERROR: did not return to choose flavours page").isTrue();
    }

    @Then("^the subscription tiers overlay is displayed$")
    public void theSubscriptionTiersOverlayIsDisplayed() {
        issChooseYourFlavoursPage.isTierOverlayDisplayed();
    }

    @When("^he clicks on the link Filters$")
    public void heClicksOnTheLinkFilters() {
        issChooseYourFlavoursPage.clickOnFilter();;
    }

    @Then("^a flyout opens$")
    public void aFlyoutOpens() {
        assertThat(issChooseYourFlavoursPage.isFlyoutDisplayed())
                .as("ERROR: filterflyout not displayed").isTrue();
    }

    @And("^the flyout has a Devices section$")
    public void theFlyoutHasADevicesSectionForEPodAndEPen() {
        assertThat(issChooseYourFlavoursPage.filterHasSectionFor("device"))
                .as("ERROR: didnt find a devices section in filter flyout").isTrue();
        assertThat(issChooseYourFlavoursPage.allFacetsDisplayed("filterDevices"))
                .as("ERROR: not all device facets were displayed").isTrue();
    }

    @And("^the device counts are correct$")
    public void theDeviceCountIsCorrect() {
        assertThat(issChooseYourFlavoursPage.validateFacetCounts("device"))
        .as("ERROR: facet counts are incorrect for devices").isTrue();
    }

    @And("^the flyout has a Strengths section for different strengths$")
    public void theFlyoutHasAStrengthsSectionForDifferentStrengths() {
        assertThat(issChooseYourFlavoursPage.filterHasSectionFor("strength"))
                .as("ERROR: didnt find a strengths section in filter flyout").isTrue();
        assertThat(issChooseYourFlavoursPage.allFacetsDisplayed("filterStrengths"))
                .as("ERROR: not all device facets were displayed").isTrue();
    }

    @And("^the strength count is correct$")
    public void theStrengthCountIsCorrect() {
        assertThat(issChooseYourFlavoursPage.validateFacetCounts("strength"))
                .as("ERROR: facet counts are incorrect for strengths").isTrue();
    }

    @And("^selects \"([^\"]*)\" facet value \"([^\"]*)\"$")
    public void selectsFacetValue(String facetName, String facetValue) throws Throwable {
        issChooseYourFlavoursPage.selectFacetValue(facetName, facetValue);
    }

    @And("^clicks on Done$")
    public void clicksOnDone() {
        issChooseYourFlavoursPage.clickDone();
    }

    @Then("^all results contain the \"([^\"]*)\" attribute \"([^\"]*)\"$")
    public void allResultsContainTheAttribute(String facetName, String facetValue) throws Throwable {
        assertThat(issChooseYourFlavoursPage.eachResultContainsAttribute(facetName, facetValue))
                .as("ERROR: not all results contained facet "+facetName+" with facet value "+facetValue).isTrue();
    }

    @And("^the sku strength is striked out$")
    public void theSkuStrengthIsStrikedOut() {
        assertThat(true)
                .as("ERROR: BUG 560551 causing test to fail ").isFalse();
    }

    @And("^records the number of products displayed$")
    public void recordsTheNumberOfProductsDisplayed() {
        numProductsVisible = issChooseYourFlavoursPage.numberOfProductsVisible();
    }

    @Then("^the number of products displayed have reduced$")
    public void theNumberOfProductsDisplayedHaveReduced() {
        assertThat(numProductsVisible > issChooseYourFlavoursPage.numberOfProductsVisible() )
                .as("ERROR: applied filter did not reduce number of products displayed").isTrue();
    }

    @Then("^the number of products displayed is as previously recorded$")
    public void theNumberOfProductsDisplayedIsAsPreviouslyRecorded() {
        assertThat(numProductsVisible == issChooseYourFlavoursPage.numberOfProductsVisible() )
                .as("ERROR: number of products following filter clear isn't correct").isTrue();
    }

    @And("^clicks on \"([^\"]*)\" clear filter$")
    public void clicksOnClearFilter(String facetName) throws Throwable {
        issChooseYourFlavoursPage.clearDeviceFilter(facetName);
    }

    @And("^adds \"([^\"]*)\" items to the basket$")
    public void addsItemsToTheBasket(int items) throws Throwable {
        numberOfItemsInBasket = numberOfItemsInBasket + items;
        if (items > 0 ) {
            issChooseYourFlavoursPage.clickOnPlusCTA();
            issChooseYourFlavoursPage.addQuantity(items);
            issChooseYourFlavoursPage.clickOnStrengthAddButton();
        }
    }

    @Then("^he sees the price per month message correctly updated$")
    public void heSeesThePricePerMonthMessageCorrectlyUpdated() {
        assertThat(issChooseYourFlavoursPage.validPricePerMonthDisplayed(numberOfItemsInBasket))
                .as("ERROR: price per month message incorrect").isTrue();
    }

    @Then("^the basket is emptied$")
    public void theBasketIsEmptied() {
        assertThat(issChooseYourFlavoursPage.validPricePerMonthDisplayed(0))
                .as("ERROR: basket is not empty").isTrue();
    }

    @And("^the user notes the product name price and strengths for each$")
    public void theUserNotesTheProductNamePriceAndStrengthsForEach() {
        Map<String, IssChooseYourFlavoursPage.FlavourPriceAndStrength> issFlavours = issChooseYourFlavoursPage.getIssFlavourData();
        scenarioContext.setContext(ISS_PLP_SUBSCRIPTION_FLAVOUR_DATA,issFlavours);
    }

    @When("^user visits the BAT home page$")
    public void userVisitsTheBATHomePage() {
        UrlBuilder.revisitBATHomePage();
    }

    @When("^user navigates to the flavours page$")
    public void userNavigatesToTheFlavoursPage() {
        issChooseYourFlavoursPage.navigateToSiteFlavoursPage();
    }

    @And("^the user notes the product name price and strengths for each on the main site$")
    public void theUserNotesTheProductNamePriceAndStrengthsForEachOnTheMainSite() {
        Map<String, IssChooseYourFlavoursPage.FlavourPriceAndStrength> issFlavours = issChooseYourFlavoursPage.getSiteFlavourData();
        scenarioContext.setContext(MAIN_SITE_SUBSCRIPTION_FLAVOUR_DATA,issFlavours);
    }

    @Then("^the same subscription flavours appear on both with the same prices and strengths$")
    public void theSameSubscriptionFlavoursAppearOnBothWithTheSamePricesAndStrengths() {
        assertThat(issChooseYourFlavoursPage.sameFlavours((Map<String, IssChooseYourFlavoursPage.FlavourPriceAndStrength>) scenarioContext.getContext(ISS_PLP_SUBSCRIPTION_FLAVOUR_DATA),
                (Map<String, IssChooseYourFlavoursPage.FlavourPriceAndStrength>) scenarioContext.getContext(MAIN_SITE_SUBSCRIPTION_FLAVOUR_DATA)));
    }

}
