package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.PDP;
import com.salmon.test.page_objects.gui.PLP;
import com.salmon.test.page_objects.gui.PaymentPage;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.AssertJUnit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.*;

public class PLPSteps extends PageObject {

  private PLP plp;
  private ScenarioContext scenarioContext;
  private PDP pdp;
  private PaymentPage paymentPage;
  private HomePageSteps homePageSteps;
  private static final Logger LOG = LoggerFactory.getLogger(PLPSteps.class);

  public PLPSteps(PLP plp, ScenarioContext scenarioContext, PDP pdp, PaymentPage paymentPage, HomePageSteps homePageSteps) {
    this.scenarioContext  =scenarioContext;
    this.plp = plp;
    this.pdp = pdp;
    this.paymentPage = paymentPage;
    this.homePageSteps = homePageSteps;
  }

  @And("^assert Categories '(.*)' section is displayed$")
  public void assertCategoriesSectionIsDisplayed(String strExpectedText) {
    assertTrue(plp.waitForExpectedElement(plp.eleCategoriesTitle).getText().contains(UrlBuilder.getMessage(strExpectedText)));
  }

  @And("^assert Filters '(.*)' section is displayed$")
  public void assertFiltersSectionIsDisplayed(String strExpectedText) {
    if (UrlBuilder.getLocale().equals("uk")) {
      assertTrue(plp.waitForExpectedElement(plp.eleFiltersTitleUk).getText().contains(UrlBuilder.getMessage(strExpectedText)));
    } else {
      assertTrue(plp.waitForExpectedElement(plp.eleFiltersTitle).getText().contains(UrlBuilder.getMessage(strExpectedText)));
    }
  }

  @And("^assert expanded '(.*)' Devices category and assert links are present$")
  public void assertExpandedDevicesCategoryAndAssertLinks(String strFilterType) {
    if (UrlBuilder.getLocale().equals("uk")) {
      assertTrue(plp.waitForExpectedElement(By.xpath("//*[@class='filter__heading'][contains(text(),'" + UrlBuilder.getMessage(strFilterType) + "')]")).isDisplayed());
    } else {
      assertTrue(plp.waitForExpectedElement(By.xpath("//*[@class='filter-options-title active'][contains(text(),'" + UrlBuilder.getMessage(strFilterType) + "')]")).isDisplayed());
    }
  }

  @And("^user expands the '(.*)' category and assert links are present$")
  public void userExpandCategoryAndAssertLinks(String strFilterType) {
    try {
      if (UrlBuilder.getLocale().equals("uk")) {
        assertTrue(plp.waitForExpectedElement(By.xpath("//*[@class='filter__heading'][contains(text(),'" + UrlBuilder.getMessage(strFilterType) + "')]"), 10).isDisplayed());
      } else {
        plp.clickByElementByQueryJSExecutor(By.xpath("//*[@class='filter-options-title'][contains(text(),'" + UrlBuilder.getMessage(strFilterType) + "')]"));
        assertTrue(plp.waitForExpectedElement(By.xpath("//*[@class='filter-options-title active'][contains(text(),'" + UrlBuilder.getMessage(strFilterType) + "')]"), 10).isDisplayed());
      }
    } catch (Exception Ex) {
      LOG.info("Failed to expand selected Category and verify item links due to exception: " + Ex.getMessage());
    }
  }

  @And("^user expands the '(.*)' filter and assert links are present$")
  public void userExpandFilterAndAssertLinks(String strFilterType) {
    plp.clickByElementByQueryJSExecutor(By.xpath("//*[@class='filter-options-title'][contains(text(),'" + UrlBuilder.getMessage(strFilterType) + "')]"));
    assertTrue(plp.waitForExpectedElement(By.xpath("//*[@class='filter-options-item active']//*[contains(text(),'" + UrlBuilder.getMessage(strFilterType) + "')]"), 5).isDisplayed());
  }

  @And("^user expands the Flavours Category and assert Items are present$")
  public void userExpandsFlavoursCategoryAndAssertItemsArePresent() {
    try {
      plp.waitForExpectedElement(plp.eleFlavoursItemsLinks, 5);
      plp.clickByElementByQueryJSExecutor(plp.eleFlavoursCategory);
      assertTrue(plp.waitForExpectedElement(plp.eleFlavoursItemsLinks, 5).isDisplayed());
    } catch (Exception Ex) {
      LOG.info("Failed to expand selected Category and verify item links due to exception: " + Ex.getMessage());
    }
  }

  @Then("^select '(.*)' Devices from the Categories menu$")
  public void openLinksFromCategoriesMenu(String strCategoryItem) {
    plp.clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(strCategoryItem)));
    plp.waitForExpectedElement(plp.searchResultsHeading, 30);
  }

  @Then("^search results '(.*)' is returned and assert items are displayed$")
  public void searchResultsDisplayedAndAssertItems(String strSelectedItemLink) {
    plp.clickItemVerifyResultsAndNavigateToPDP(UrlBuilder.getMessage(strSelectedItemLink));
  }

  @And("^search results with color swatch '(.*)' is returned$")
  public void searchResultsDisplayedWithColorSwatch(String strColor) throws Throwable {
    plp.clickItemVerifyResultsWithColorSwatch(UrlBuilder.getMessage(strColor));
  }

  @And("assert text of the button '(.*)' before login$")
  public void assertTextOfTheButtonBeforeLogin(String strExpectedText) {
    plp.assertTextOfTheAddToCartButtonBeforeLogin(strExpectedText);
  }

  @And("user clicks on Login or Register to Buy from PLP$")
  public void userClicksOnLoginOrRegisterToBuyButtonFromPLP() {
    plp.clickOnLoginOrRegisterToBuyButtonFromPLP();
  }

  @And("assert text of the button '(.*)' after login$")
  public void assertTextOfTheButtonAfterLogin(String strExpectedText) {
    plp.assertTextOfTheAddToCartButtonAfterLogin(strExpectedText);
  }

  @And("user selects strength '(.*)' for a six pack LAB product and add to cart$")
  public void userSelectStrengthForSixPackLabProduct(String strStrength) {
    plp.userSelectStrengthForSixPackLabProduct(strStrength);
  }

  @And("user selects strength '(.*)' for a six pack LAB product,selects Quantity '(.*)' and add to cart$")
  public void userSelectStrengthAndQuantityForSixPackLabProduct(String strStrength, String strQuantity) throws Throwable {
    plp.userSelectStrengthAndQuantityForSixPackLabProduct(strStrength, strQuantity);
  }

  @And("user selects strength '(.*)' for a single pack LAB product,selects Quantity '(.*)' and add to cart$")
  public void userSelectStrengthForSinglePackLabProduct(String strStrength, String strQuantity) throws Throwable {
    plp.userSelectStrengthForSinglePackLabProduct(strStrength, strQuantity);
  }

  @And("user selects strength '(.*)' for a three pack LAB product,selects quantity '(.*)' and add to cart$")
  public void userSelectStrengthForThreePackLabProduct(String strStrength, String strQuantity) throws Throwable {
    plp.userSelectStrengthForThreePackLabProduct(strStrength, strQuantity);
  }

  @And("assert same products details for LAB and Non-LAB products in mini cart$")
  public void assertSameProductDetailsForLABandNonLabProductsInMiniCart() {
    plp.assertSameProductDetailsForLABandNonLabProductsInMiniCart();
  }

  @And("assert Strength attribute as '(.*)' appears for only LAB products in mini cart$")
  public void assertStrengthAttributeForOnlyLABProductsInMiniCart(String strExpectedStrength) {
    plp.assertStrengthAttributeForOnlyLABProductsInMiniCart(strExpectedStrength);
  }

  @And("assert different products with same flavour and strength show on separate lines in mini cart$")
  public void assertDifferentLABProductsWithSameFlavourSameStrengthInMiniCart() {
    plp.assertDifferentLABProductsWithSameFlavourSameStrengthInMiniCart();
  }

  @And("assert same bundle LAB products with specified quantity '(.*)' show on separate lines in mini cart$")
  public void assertSameBundleLABProductsWithSpecifiedQuantityInMiniCart(String strQuantity) {
    plp.assertSameBundleLABProductsWithSpecifiedQuantityInMiniCart(strQuantity);
  }

  @And("user enters a quantity '(.*)' in the mini-cart text-field and clicks update$")
  public void userEntersQuantityInMiniCartTextFieldAndClicksUpdate(String strQuantity) throws Throwable {
    plp.userEntersQuantityInTextFieldAndClicksUpdate(PLP.MINICART_PRODUCTS_QTY, strQuantity, PLP.MINICART_QTY_UPDATE);
  }

  @And("user enters a quantity '(.*)' in the mini-basket text-field and clicks update$")
  public void userEntersQuantityInMiniBasketTextFieldAndClicksUpdate(String strQuantity) throws Throwable {
    plp.userEntersQuantityInTextFieldAndClicksUpdate(PLP.MINIBASKET_PRODUCTS_QTY, strQuantity, PLP.MINIBASKET_QTY_UPDATE);
  }

  @And("^assert internal redirect of PLP links URLs with success status code$")
  public void assertInternalRedirectOfPLPLinksWithSuccessStatusCode() throws Throwable {
    plp.assertInternalRedirectOfPLPlinksWithStatusCode();
  }

  @And("user selects strength '(.*)' for LAB product,selects Quantity '(.*)' and add to cart$")
  public void userSelectStrengthAndQuantityForLabProduct(String strStrength, String strQuantity) throws Throwable {
    plp.userSelectStrengthAndQuantityForLabProduct(strStrength, strQuantity);
  }

  @And("^users navigates to TOFINO products landing page URL '(.*)'$")
  public void userNavigatesToTofinoProductsLandingPage(String strExpectedURL) {
    plp.navigateToTofinoProductsLandingPage(strExpectedURL);
  }

  @And("^assert tofino products section at bottom of the page$")
  public void assertTofinoProductsSectionAtBottomOfLandingPage() {
    plp.assertTofinoProductsSectionAtBottomOfLandingPage();
  }

  @And("^assert page title contains '(.*)'$")
  public void assertPageTitleContains(String actualPageTitle) {
    plp.assertPageTitleContains(actualPageTitle);
  }

  @And("^select product colour and assert device color switch on PLP$")
  public void selectProductColorAndAssertDeviceColorSwitchOnPLP() {
    plp.selectProductColorAndAssertDeviceColorSwitchOnPLP();
  }

  @And("^user click buy it button$")
  public void userClickBuyItButton() {
    plp.waitForExpectedElement(PLP.VUSE_DE_FIRST_PRODUCT, 10);
    plp.hoverOnElement(PLP.VUSE_DE_FIRST_PRODUCT);
    plp.waitForExpectedElement(PLP.VUSE_DE_PLP_ADD_TO_CART, 15).click();
  }

  @Then("^eyes check PLP error message$")
  public void eyesCheckPLPErrorMessage() {
    plp.eyesCheckPlpPage();
  }

  @And("^assert tofino strength on '(.*)'$")
  public void assertTofinoStrength(String page) throws Throwable {
    plp.assertTofinoStrengthOnPage(page);
  }

  @Then("user clicks on Filters button to open filters flyout$")
  public void userClicksOnFiltersButtonToOpenFiltersFlyout() {
    plp.waitForExpectedElement(plp.FILTERS_BUTTON, 10);
    plp.clickFirstElementByQueryJSExecutor(plp.FILTERS_BUTTON);
  }

  @Then("^assert Devices Category section on flyout$")
  public void assertDevicesCategorySectionOnFiltersFlyout() {
    assertTrue(plp.waitForExpectedElement(plp.FILTERS_SECTION_FLYOUT).isDisplayed());
    assertTrue(plp.waitForExpectedElement(plp.CONSUMABLE_TYPE_FILTERS_HEADING).getText().contains(UrlBuilder.getMessage("consumableTypeFiltersText.key")));
  }

  @Then("^assert list of consumable types for '(.*)' and CTA for one type$")
  public void assertListOfConsumableTypesForDevicesFilterAndCTA(String strType) throws Throwable {
    plp.assertListOfConsumableTypesInFiltersAndCTA(strType);
  }

  @Then("^assert quantity selector is displayed on each SKU with default value 1$")
  public void assertQtySelectorDisplayedOnEachSKUWithOneAsDefaultValue() {
    List<WebElement> lstQtyFields = null;
    List<WebElement> addToBasketButtons = null;
    switch (UrlBuilder.getLocale()) {
      case "vusefr":
        lstQtyFields = plp.getWebDriver().findElements(plp.QTY_SELECTOR_PLP_VUSEFR);
        addToBasketButtons = plp.getWebDriver().findElements(plp.ADD_TO_BASKET_PLP_VUSEFR);
        break;
      case "vuseuk":
        lstQtyFields = plp.getWebDriver().findElements(plp.QTY_SELECTOR_PLP_UK);
        addToBasketButtons = plp.getWebDriver().findElements(plp.ADD_TO_BASKET_PLP_UK);
        break;
      default:
        lstQtyFields = plp.getWebDriver().findElements(plp.QTY_SELECTOR_PLP);
        addToBasketButtons = plp.getWebDriver().findElements(plp.ADD_TO_BASKET_PLP);
    }
    assertTrue(lstQtyFields.size() > 0);
    assertTrue(addToBasketButtons.size() == lstQtyFields.size());
    plp.assertQtySelectorDefaultValueForEachSKU();
  }

  @Then("^assert if user is able to input any value '(.*)' in Qty text field$")
  public void assertUserIsAbleToInputAnyValueInQtyTextField(String strQty) throws Throwable {
    plp.assertUserIsAbleToInputAnyValueInQtyTextField(strQty);
  }

  @Then("^user clicks on plus button on qty selector and assert qty increase to '(.*)'$")
  public void userClicksOnPlusButtonOnQtySelectorToIncreaseTheQty(String strIncreaseQty) throws Throwable {
    plp.clickOnPlusButtonOnQtySelectorToIncreaseTheQty(strIncreaseQty);
  }

  @Then("^user clicks on minus button on qty selector and assert qty decrease to '(.*)'$")
  public void userClicksOnMinusButtonOnQtySelectorToDecreaseTheQty(String strDecreaseQty) throws Throwable {
    plp.hoverOnElement(plp.STRENGTH_SELECTED);
    plp.clickUsingJS(plp.QTY_MINUS_BUTTON);
    plp.assertTrueWithCustomError(strDecreaseQty, plp.getWebDriver().findElements(plp.QTY_SELECTOR_PLP_VUSEUK).get(0).getAttribute("value"));
  }

  @Then("^select first product color or strength on PLP$")
  public void selectFirstProductColorStrengthOnPLP() throws Throwable {
    plp.selectFirstProductColorStrengthOnPLP();
  }

  @Then("^select second product color or strength on PLP$")
  public void selectSecondProductColorStrengthOnPLP() throws Throwable {
    plp.selectSecondProductColorStrengthOnPLP();
  }

  @And("^update the quantity in plp to '(.*)' and add to cart$")
  public void updateTheQuantityInPlpTo(int qty) {
    plp.updateQuantityAndClickAddToBasket(qty);
  }


  @And("^user see the product name display$")
  public void productNameDisplayed() {
    plp.productNameDisplayed();
  }

  @And("^user see the price display$")
  public void productPriceDisplayed() {
    plp.productPriceDisplayed();
  }

  @And("^user see the Buy Now CTA display$")
  public void productByNowDisplayed() {
    plp.productByNowDisplayed();
  }

  @And("^user hovers on '(.*)' and clicks on '(.*)'$")
  public void vypePlpPageNavigation(String category, String subCategory) {
    plp.vypePlpPageNavigation(category, subCategory);
  }

  @And("^user lands on plp page$")
  public void vypeCategoryBannerPage() {
    plp.vypeCategoryBannerPage();
  }

  @And("^user Clicks on add to cart button$")
  public void user_Clicks_on_add_to_cart_button() {
    plp.clickOnAddToCartButton();
  }

  @Then("^assert personalized product has ViewPorduct CTA$")
  public void assertPersonalizedProductHasViewProdcutCTA() {
    plp.ViewProductCTAIsDisplayedOnPLPForPersonalizedProduct();
  }

  @And("^user bypass the CLP healthwarning popup$")
  public void userBypassTheHealthwarningPopup() {
    plp.bypassHealthWarningPopup();
  }

  @And("^assert below filter type options are available$")
  public void assertFilterTypeOptionsAreAvailable(DataTable table) throws InterruptedException {
    List<List<String>> flavors = table.raw();
    List<WebElement> filterTypes = getWebDriver().findElements(plp.FILTER_TYPE_HEADING);
    assertTrue(filterTypes.size() >= flavors.size());
    List<String> list = new ArrayList<>();
    for (WebElement webElement : filterTypes) {
      waitForAjaxElementNotToBePresent(getWebDriver(), 5);
      String name = webElement.getText();
      list.add(name);
      LOG.info("the new list is name " + list);
    }
    for (int i = 0; i <= flavors.size() - 1; i++) {
      assertTrue("the content which is not present is -" + UrlBuilder.getMessage(flavors.get(i).get(0)).trim(),
              list.contains(UrlBuilder.getMessage(flavors.get(i).get(0)).trim()));
    }
  }

  @And("^verify the sorting options '(.*)' for '(.*)'$")
  public void verifySortingOptions(String sortingOptions, String product) {
    plp.verifySortingOptons(sortingOptions, product);
  }

  @And("^assert subscription wording is not present on '(.*)'$")
  public void assertSubscriptionWordingNotDisplayed(String page) {
    if (page.equals("pdp"))
      assertTrue(getWebDriver().findElements(plp.SUBSCRIPTION_WORDING_PDP).size() == 0);
    else assertTrue(getWebDriver().findElements(plp.SUBSCRIPTION_WORDING_BASKETPAGE).size() == 0);
  }

  @And("^user adds a product to basket$")
  public void userAddsAProductToBasket() throws InterruptedException {
    plp.addProductToBasket();
  }

  @And("^user assert the count after applying the filter for '(.*)'$")
  public void assertCountAfterApplyingFilter(String option) throws InterruptedException {
    switch (option) {
      case "eCigarettes":
        assertTrue(plp.verifyCountOfSelectedCheckbox(plp.VUSE_COLOR_CHECKBOX, plp.VUSE_COLOR_CHECKBOX_COUNT, plp.RESULT_COUNT));
        break;
      case "eLiquides":
        assertTrue(plp.verifyCountOfSelectedCheckbox(plp.VUSE_TYPE_OF_PRODUCT_CHECKBOX, plp.VUSE_TYPE_OF_PRODUCT_COUNT, plp.RESULT_COUNT));
        break;

    }
  }

  @And("^assert '(.*)' option is highlighted$")
  public void assertOptionIsHighlighted(String option) throws InterruptedException {
    switch (option) {
      case "All":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("allOption.key")));
        break;
      case "ePen":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("ePenlink.key"))||plp.optionHiglighted(UrlBuilder.getMessage("ePenlinkInFilter.key")));
        break;
      case "ePod":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("ePodlink.key"))||plp.optionHiglighted(UrlBuilder.getMessage("ePodlinkInFilter.key")));
        break;
      case "eTank":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("eTanklink.key"))||plp.optionHiglighted(UrlBuilder.getMessage("eTanklinkInFilter.key")));
        break;
      case "eLiquid ePod":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("capsulesEliquidEPodlink.key")));
        break;
      case "eLiquid ePen":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("capsulesEliquidEPenlink.key")));
        break;
      case "Flacons":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("flaconsEliquidLink.key")));
        break;
      case "ePod Pod":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("ePodEliquidlink.key")));
        break;
      case "ePen Pod":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("ePenEpodLink.key")));
        break;
      case "eLiquid Bottle":
        assertTrue(plp.optionHiglighted(UrlBuilder.getMessage("eLiquidBottles.key")));
        break;
    }
  }

  @And("^verify that the navigated url should contains '(.*)'$")
  public void verifyTheNavigatedUrl(String urlContains) throws InterruptedException {
    assertTrue(plp.verifyRedirectedUrl(UrlBuilder.getMessage(urlContains)));
  }

  @And("^user navigate to '(.*)' and '(.*)' and verify the '(.*)' is highlighted and navigated to expected '(.*)'$")
  public void userNavigateAndVerifyTheHighlightedOption(String category,String subCategory,String option,String url,DataTable dtList) throws InterruptedException {
    List<List<String>> lstLinks = dtList.raw();
    for (int i = 1; i < lstLinks.size(); i++) {
      plp.vypePlpPageNavigation(lstLinks.get(i).get(0), lstLinks.get(i).get(1));
      waitForExpectedElement(plp.FILTER_OPTION,5);
      clickFirstElementByQueryJSExecutor(plp.FILTER_OPTION);
      assertOptionIsHighlighted(lstLinks.get(i).get(2));
      verifyTheNavigatedUrl(lstLinks.get(i).get(3));
    }
  }

 @And("^user navigate to '(.*)' to '(.*)' and verify the '(.*)'$")
  public void userNavigateAndVerifyTheFilterOptions(String category,String subCategory,String sortingOption,DataTable dtList) throws InterruptedException {
    List<List<String>> lstLinks = dtList.raw();
    for (int i = 1; i < lstLinks.size(); i++) {
      plp.vypePlpPageNavigation(lstLinks.get(i).get(0), lstLinks.get(i).get(1));
      LOG.info("Useris navigated to -  "+UrlBuilder.getMessage(lstLinks.get(i).get(1))+" under the option "+UrlBuilder.getMessage(lstLinks.get(i).get(0)));
      plp.verifySortingOptons(lstLinks.get(i).get(2), lstLinks.get(i).get(1));
    }
  }

  @And("^verify the content of the Filter type$")
  public void verifyContentOfFilterTypes(DataTable dtList) throws InterruptedException {
    List<List<String>> lstLinks = dtList.raw();
    for (int i = 1; i < lstLinks.size(); i++) {
      plp.vypePlpPageNavigation(lstLinks.get(i).get(0), lstLinks.get(i).get(1));
      waitForExpectedElement(plp.FILTER_OPTION, 5);
      clickFirstElementByQueryJSExecutor(plp.FILTER_OPTION);
    }
  }

    @And("^verify the filter content for '(.*)' with filter by '(.*)'$")
    public void verifyTheFilterContent(String option, String filterType ) throws InterruptedException {
    plp.verifyTheContent(option, filterType);
    }

  @And("^the following are displayed correctly$")
  public void theFollowingProductDescriptionsAreDisplayed(DataTable productDataTable) {
    List<Map<String, String>> allProducts = productDataTable.asMaps(String.class, String.class);
    plp.validatePlp(allProducts);
    }

    @Then("^the following images are named correctly$")
    public void theFollowingImagesAreNamedCorrectly(DataTable productDataTable) {
      List<List<String>> allProducts = productDataTable.asLists(String.class);
      plp.validateImages(allProducts, "plp");
    }

    @And("^assert strength label '(.*)' is displayed for CBD products on PLP$")
    public void assertStrengthLabelDisplayedForCBDProductsOnPLP(String strStrengthLabel){
      List<WebElement> lstCBDProducts = plp.getWebDriver().findElements(plp.CBD_PRODUCT_STRENGTH_LABEL);
      for(WebElement ele : lstCBDProducts){
          assertTrue(ele.getText().contains(strStrengthLabel));
      }
    }

  @And("^assert strength label '(.*)' is displayed for CBD products on PDP$")
  public void assertStrengthLabelDisplayedForCBDProductsOnPDP(String strStrengthLabel){
      assertTrue(plp.waitForExpectedElement(plp.PRODUCT_STRENGTH_LABEL,10).getText().contains(strStrengthLabel));
  }

  @And("^assert strength label NICOTINE LEVEL is not displayed for CBD products$")
  public void assertStrengthLabelNotDisplayedForCBDProducts(){
      assertTrue(plp.getWebDriver().findElements(plp.NICOTINE_LEVEL_STRENGTH_LABEL).size()==0);
  }

  @And("^assert review rating is present for all the products on PLP$")
  public void assertReviewRatingIsPresentForAllTheProductsOnPLP()  {
    assertTrue(plp.getProductTotalNumber()>=plp.getFirstReviewRateNumber());
  }

  @Then("^user see default One time Purchase option selected on PLP$")
  public void assertOneTimePurchaseOptionSelectedOnPLP() {
    assertTrue(plp.verifyOneTimePurchaseOptionSelected());
  }

  @And("^user verify Subscription option is unselected on PLP$")
  public void assertSubscriptionOptionIsUnselectedOnPLP() {
    assertTrue(plp.verifySubscriptionOptionUnselected());
  }

  @Then("^on clicking on \"i\" icon the modal window opens up on PLP$")
  public void userClickInfoButton() {
    assertTrue(plp.verifyUserClickSubscriptionInfo());
  }

  @And("^verify \"([^\"]*)\" on One time Purchase and on Subscription options$")
  public void verifyOnOneTimePurchaseAndOnSubscriptionOptions(String expectText){
    assertTrue(plp.getOneTimePurchaseText().equals(UrlBuilder.getMessage(expectText)));
    assertTrue(plp.getSubscriptionText().equals(UrlBuilder.getMessage(expectText)));
  }

  @And("^verify content of the category$")
  public void assertCategoryContentDisplayed() {
    assertTrue(isElementDisplayedBySeconds(plp.CATEGORYPAGE,5));
  }

  @And("^verify all the CTA present on PLP page and select product on accessories$")
  public void verifyAllCTAPresentOnAccessories() {
    plp.verifyAllCTAPresentOnPagePLPOnAccessories();
  }

  @And("^verify all the CTA present on PLP page and select charger accessories$")
  public void verifyAllCTAPresentOnChargerAccessories() {
    plp.verifyAllCTAPresentOnPagePLPOnChargerAccessories();
  }

  @And("^user navigates to PLP page and required product for ZA$")
  public void userAddRequiredProduct() throws InterruptedException {
    plp.addRequiredProducts();
  }

  @Then("^add 5 products from promotion flavour category$")
  public void useradd5ProductFromPromotionFlavourCategory() {
    plp.addPromotionFlavourProducts();
  }

  @And("^user hovers on '(.*)' menu$")
  public void userHoverHeaderMenu(String category) {
    plp.userHoverHeaderMenu(category);
  }

  @And("^'(.*)' flyout is displayed and navigated$")
  public void verifyHeaderMenuFlyoutDisplayed(String subcategory) {
    assertTrue(isElementDisplayedBySeconds(By.linkText(UrlBuilder.getMessage(subcategory)),5));
    waitForPresenceOfElement(By.linkText(UrlBuilder.getMessage(subcategory)));
    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(subcategory)));
  }

  @And("^user hovers on one-time purchase enabled product$")
  public void userHoversOnOneTimePurchaseEnabledProduct() {
      plp.hoverOnKnowMoreAboutProduct();
  }

  @Then("^know more about message is displayed$")
  public void knowMoreAboutMessageIsDisplayed() {
      assertTrue(plp.isKnowMoreAboutMessagePresent());
  }

  @And("^user clicks know more about subscription link$")
  public void userClicksKnowMoreAboutSubscriptionLink() {
      plp.clickKnowMoreAboutSubscriptionLink();
  }

  @Then("^user goes to know more about subscription page$")
  public void userGoesToKnowMoreAboutSubscriptionPage() {
      assertTrue(plp.isKnowMoreAboutSubscriptionUrlPresent());
  }

  @And("^user hover the product and click on add to cart on plp page$")
  public void userHoverProductAndAddCartPLPPage() {
      plp.userHoverProductAndAddToCartPLPPage();
  }

  @And("^the user navigates to the PLP$")
  public void theUserNavigatesToThePLPForLanguage() throws Throwable {
    paymentPage.navigateToPlp();
  }

  @When("^I click on the product then the PDP components are correct$")
  public void iClickOnTheProductThenThePDPComponentsAreCorrect(DataTable productDataTable) {
    List<Map<String, String>> allProducts = productDataTable.asMaps(String.class, String.class);
    plp.validatePdp(allProducts);
  }

  @And("^verify first available nicotine strength is selected$")
  public void verifyFirstAvailableNicotineLevelIsSelected() {
    plp.verifyFirstAvailableNicotineLevelIsSelected();
  }

  @When("^click the neo product by name \"([^\"]*)\"$")
  public void clickTheNeoProductByName(String productName)  {
      plp.selectProductByName(productName);
  }

  @Then("^verify product count is same as product present$")
  public void verifyProductCountDisplayedIsSameAsProductProduct() {
    plp.verifyProductCountDisplayedIsSameAsProductProduct();
  }

  @Then("^verify PLP page is scrollable$")
  public void verifyPLPPageIsScrollable() {
      plp.plpPageIsScrollable();
  }

  @And("^select low to high sort filter$")
  public void userClickOnLowToHighSortFilter() {
      plp.clickLowToHighSortFilter();
  }

  @Then("^verify product is arrange from low to high price$")
  public void verifyProductAreSortedLowToHigh() {
      plp.verifyProductAreSortedLowToHigh();
  }

  @Then("^assert Add to cart button and quantity selector CTAs works as expected from PLP$")
  public void assertAddToCartAndQuantitySelectorCTAsWorksAsExpectedFromPLP() {
    WebElement eleQty = getWebDriver().findElements(plp.MINIBASKET_PRODUCTS_QTY).get(0);
    enterDataUsingJS(eleQty, "4");
    plp.clickFirstElementByQueryJSExecutor(plp.BUTTON_VUSEUK);
  }

  @Then("^users click on Find Your Match link from Flavours PLP$")
  public void userClicksOnFindYourMatchLinkFromFlavoursPLP() {
    if(doesURLContain("vuse.com/gb/en"))
        plp.waitAndClickByElementByJSExecutor(plp.FIND_YOUR_MATCH_LINK_LIVE,10);
    else
        plp.waitAndClickByElementByJSExecutor(plp.FIND_YOUR_MATCH_LINK,10);
  }

  @Then("^assert the products count is correct$")
  public void assertTheProductsCountIsCorrect() {
      assertTrue(plp.getProductTotalCount()<=Integer.parseInt(
            UrlBuilder.getMessage("totalProductCount.key")));
  }

  @And("^select first subscription option on PLP$")
  public void selectFirstSubscriptionOptionOnPLP() {
    plp.selectFirstsubscriptionOptionOnPLP();
  }

  @Then("^assert quantity exceeded warning message '(.*)' is displayed on PLP page$")
  public void assertQuantityExceededErrorMessageIsDisplayedOnPLPPage(String linkText) {
    assertTrue(plp.getQTYErrorMessageOnPLPPage().contains(UrlBuilder.getMessage(linkText)));
  }

  @Then("^assert equipment's categories are displayed correctly$")
  public void assertEquipmentSCategoriesAreDisplayedCorrectly() {
     List<String> expectedCategories= Arrays.asList(UrlBuilder.getMessage("eqipmentCategories.key").split(","));
     List<String> actualCategories=plp.getEquipmentCategories();
     assertTrue(expectedCategories.size()==actualCategories.size());
     assertTrue(expectedCategories.equals(actualCategories));
  }

  @And("^verify all the CTA present on PLP page and select product$")
  public void verifyAllCTAPresentOnPagePLP() {
    plp.verifyAllCTAPresentOnPagePLP();
  }

  @And("^user hover on '(.*)' and click on '(.*)' and verify url contains '(.*)'$")
  public void userHoverOnLinkAndClickOnItAndURLContains(String LinkToHover,String LinkToClick,String UrlToContain,DataTable dtList) {
    List<List<String>> lstLinks = dtList.raw();
    for (int i = 1; i < lstLinks.size(); i++) {
      plp.vypePlpPageNavigation(lstLinks.get(i).get(0), lstLinks.get(i).get(2));
      LOG.info("User is navigated to -  "+UrlBuilder.getMessage(lstLinks.get(i).get(1))+" under the option "+UrlBuilder.getMessage(lstLinks.get(i).get(0)));
      homePageSteps.urlContainsSubscriptions(lstLinks.get(i).get(1));
    }
  }

  @And("^click on '(.*)' and verify url contains '(.*)' and verify content of the category$")
  public void userClickOnLinkAndVerifyURLContains(String LinkToClick,String UrlToContain,DataTable dtList) {
    List<List<String>> lstLinks = dtList.raw();
    for (int i = 1; i < lstLinks.size(); i++) {
      homePageSteps.usersClicksOnTheLogoutTextLink(lstLinks.get(i).get(0));
      homePageSteps.urlContainsSubscriptions(lstLinks.get(i).get(1));
      assertCategoryContentDisplayed();
    }
  }

  @And("^user hover on '(.*)' menu '(.*)' flyout is displayed and navigated and verify url contains '(.*)'$")
  public void userHoverOnMenuAndClickOnFlyoutAndURLContains(String headermenu,String headerflyout,String UrlToContain,DataTable dtList) {
    List<List<String>> lstLinks = dtList.raw();
    for (int i = 1; i < lstLinks.size(); i++) {
      plp.vypePlpPageNavigation(lstLinks.get(i).get(0), lstLinks.get(i).get(1));
      homePageSteps.urlContainsSubscriptions(lstLinks.get(i).get(2));
    }
  }

  @And("assert same products details for LAB products in mini cart$")
  public void assertSameProductDetailsForLABProductsInMiniCart() {
      plp.assertSameProductDetailsForLABProducts();
  }

  @Then("^assert supplementary CMS blocks displayed$")
  public void assertSupplementaryCMSBlocksDisplayed() {
      plp.assertSupplementaryText();
      plp.assertSupplementaryBlockExist();
  }

  @Then("^assert epod is not displayed in epen devices$")
  public void assertEpodIsNotDisplayedInEpenDevices() {
    List<WebElement> products = plp.getProductsOnPLP();
    for (WebElement product : products) {
      assertThat(product.getText().contains("ePod"))
      .as("ERROR validate product name not include ePod: product is " + product.getText()).isFalse();
    }
  }

  @Then("^all quick filters display$")
  public void allQuickFiltersDisplayed() {
    AssertJUnit.assertEquals("*** ERROR: not all results displayed. ", plp.getAllQuickFilter(), UrlBuilder.getMessage("eLiquidQuickFilterItem.key"));
  }

  @Then("^all results contain the quick filter when clicking it one by one$")
  public void allResultsContainTheQuickFilter() {
    assertThat(plp.eachResultContainsQuickFilter())
            .as("ERROR: not all results contained quick filter.").isTrue();
  }

  @And("^user click on filter option$")
  public void user_clicks_on_filter() {
    plp.clickOnFilter();
  }

  @And("^click on first buyable product in PLP in Velo site$")
  public void clickOnFirstBuyableProd() {
    plp.clickOnFirstBuyableProd();
  }


  @And("^select more than one options of same attribute$")
  public void selectMoreThanOneOptionOfSameAttribute() {
    plp.selectMoreThanOneOptionOfSameAttribute();
  }

  @And("^verify option selected in quick filter is selected in classic filter$")
  public void verifyOptionSelectedInQuickFilterIsSelectedInClassicFilter() {
    plp.verifyOptionSelectedInQuickFilterIsSelectedInClassicFilter();
  }

  @And("^unchecked first option strength$")
  public void uncheckedFirstOptionStrength() {
    plp.uncheckedFirstOptionStrength();}

  @And("^verify PLP page is open$")
  public void verifyPLPPageIsOpen() {
    assertTrue(pdp.isElementDisplayedBySeconds(plp.FILTER_OPTION, 15));

  }

  @And("^select a subscription product on PLP and add to cart$")
  public void selectASubscriptionProductOnPLPAndAddToCart() {
    plp.selectASubscriptionOnPLPAndAddToCart();
  }

  @Then("^add product to basket success message is displayed on PLP$")
  public void addProductToBasketSuccessMessageIsDisplayedOnPLP() {
    String actualMessage = plp.getAddToBasketSuccessMessage();
    String expectedMessage = UrlBuilder.getMessage("addProductToBasketMessage.key");
    assertThat(actualMessage.contains(expectedMessage))
            .as("ERROR validate addProductToBasket: expected message was " + expectedMessage + ", but I got " + actualMessage).isTrue();

  }

  @And("^select few filter option$")
  public void selectFiltersOptions() {
    plp.selectFiltersOptions();
  }

  @And("^click on empty and verify filter as clear$")
  public void clickOnEmptyLinkVerifyFilterAsClear() {
    plp.clickOnEmptyLinkVerifyFilterAsClear();
  }

  @Then("^verify product name price strength color purchase option$")
  public void verifyProductNamePriceStrengthColorPurchaseOption() {
    plp.verifyProductNamePriceStrengthColorPurchaseOption();
  }

  @Then("^validate product elements is present on PLP$")
  public void validateProductElementsIsPresentOnPLP() {
    plp.getPlpElements();
  }

  @And("^clicks PLP product link index '(.*)'$")
  public void clicksOnProductLinkIndex(String strIndex) {
    plp.clickOnProductLinkOnBasisOfIndex(strIndex);
  }
}