package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.jetty.client.AuthenticationProtocolHandler.LOG;
import static org.testng.AssertJUnit.*;

public class PDPSteps {

  private PDP pdp;
  private HomePage homepage;
  BATHelper batHelper;
  private SearchResult searchResult;
  private PaymentPage paymentPage;
  private GloItHomePage glo;
  private ScenarioContext scenarioContext;
  private RegistrationPage registrationPage;

  public PDPSteps(TestContext testContext, GloItHomePage glo, PDP pdp, HomePage homepage, BATHelper batHelper, SearchResult searchResult, PaymentPage paymentPage, RegistrationPage registrationPage) {
    scenarioContext = testContext.getScenarioContext();
    this.pdp = pdp;
    this.glo=glo;
    this.homepage=homepage;
    this.batHelper=batHelper;
    this.searchResult=searchResult;
    this.paymentPage=paymentPage;
    this.registrationPage=registrationPage;
  }

  @Then("^assert PDP product title$")
  public void getPDPTitle() {
    pdp.getPDPTitle();
  }


  @Then("^assert PDP product price$")
  public void getPDPProductPrice() {
   pdp.getPDPProductPrice();
  }

  @Then("^assert PDP product description$")
  public void getPDPProductDescription() {
    assertTrue(pdp.waitForExpectedElement(pdp.productDescriptoin).isDisplayed());
  }

  @Then("^ensure PDP elements are displayed as expected$")
  public void ensurePDPElementsAreDisplayedAsExpected() throws Throwable {
    switch (UrlBuilder.getLocale()) {
      case "dk":
        assertTrue(pdp.waitForExpectedElement(pdp.productDescriptoin).isDisplayed());
        assertTrue(pdp.waitForExpectedElement(pdp.PDP_ADDTOCART_BUTTON).isDisplayed());
        assertTrue(pdp.waitForExpectedElement(pdp.productColourSwitch).isDisplayed());
      case "vusedk":
        assertTrue(pdp.waitForExpectedElement(pdp.productDescriptoin).isDisplayed());
        assertTrue(pdp.waitForExpectedElement(pdp.PDP_ADDTOCART_BUTTON).isDisplayed());
        break;
      case "de":
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.productDescriptoin).isDisplayed());
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.productColourSwitch).isDisplayed());
        break;
      case "vuseza":
      case "vuseco":
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.addToCartButton).isDisplayed());
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.productColourSwitch).isDisplayed());
        break;
      case "vuseit":
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.addToCartButton).isDisplayed());
        break;
      default:
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.productDescriptoin).isDisplayed());
        if(UrlBuilder.getLocale().equals("fr")){pdp.addToCartButton=By.cssSelector("#product-addtocart-button");}
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.addToCartButton).isDisplayed());
        assertTrue("Expected element isn't displayed as expected, check page layout", pdp.waitForExpectedElement(pdp.productColourSwitch).isDisplayed());
    }
  }

  @Then("^click add to cart button$")
  public void clickAddToCartButton() {
	  pdp.clickAddToCartButton();
  }

  @Then("^click just add to cart button$")
  public void clickJustAddToCartButton() {
    pdp.clickJustAddToCartButton();
  }

  @Then("^click add to cart button js$")
  public void clickAddToCartButtonjs() throws Throwable {
    Thread.sleep(4000);
    try {
      switch (UrlBuilder.getLocale()) {
        case "pl":
          glo.CloseGoOnPopup();
          break;
      }
      pdp.waitAndClickByElementByJSExecutor(pdp.addToCartButton,10);
    } catch (Exception exception) {
      pdp.clickUsingJS(pdp.addToCartButton);
    }
  }

  @And("^select product colour$")
  public void selectProductColour() {
	  pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
  }

  @And("^select product strength$")
  public void selectProductStrength(){
	  pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);

  }

  @And("^select product strength or colour$")
  public void selectProductStrengthOrColour() {
      String productNameToBeAddedToCart = pdp.getProductNameToBeAddedToCart();
      scenarioContext.setContext(Context.PRODUCT_NAME, productNameToBeAddedToCart);
      pdp.selectProductStrengthOrColour();
  }

  @Then("^assert favorites button is (?:present|not present)$")
  public void assertFavoritesButtonIsNotPresent() {
    assertThat(pdp.isWishlistButtonDisplayed()).isTrue();
  }

  @Then("^click add to favorites$")
  public void clickAddToFavorites() {
	  switch (UrlBuilder.getLocale()) {
        case "glode" :
          pdp.clickWishlistButton();
          break;
      case "vypeit":
        pdp.clickByElementByQueryJSExecutor(pdp.favoritesButtonIT);
        break;
        case "vuseit":
          pdp.clickByElementByQueryJSExecutor(pdp.favoritesButtonIT);
          break;
      default:
        pdp.clickByElementByQueryJSExecutor(pdp.favoritesButton);
    }
  }

  @And("^click subscription icon$")
  public void clickSubscriptionIcon() throws Throwable {
    Thread.sleep(8000);
    pdp.clickByElementByQueryJSExecutor(By.cssSelector("#product-options-wrapper > div.fieldset > div > div.field > label"));
    pdp.waitForExpectedElement(By.cssSelector("#select_7 > option:nth-child(2)")).click();
    LOG.info("\nSubscription selector displayed : " + pdp.getWebDriver().findElement(By.cssSelector("select#select_7.product-custom-option.admin__control-select")).isEnabled());
  }

  @And("^qty input field present$")
  public void qtyInputFieldPresent() {
    if ((UrlBuilder.getLocale().equals("fr")||UrlBuilder.getLocale().equals("vusefr") || UrlBuilder.getLocale().equals("mx")||UrlBuilder.getLocale().equals("vuseco"))) {
      pdp.scrollToPageTop();
      pdp.hoverOnElement(pdp.BTN_SWATCH_COLOR_FR);
      assertTrue(pdp.waitForExpectedElement(By.name("qty")).isDisplayed());
    } else {
      if (!UrlBuilder.getLocale().equals("uk"))
        assertTrue(pdp.waitForExpectedElement(By.name("qty")).isDisplayed());
    }
  }

  @And("^update qty input field and confirm update$")
  public void updateQtyInputFieldAndConfirmUpdate() {
    if (UrlBuilder.getLocale().equals("fr")){
      pdp.hoverOnElement(pdp.BTN_SWATCH_COLOR_FR);
      searchResult.EnterQuantityOnPLPPage("4");
      assertTrue(searchResult.waitForExpectedElement(SearchResult.INPUT_VYPE_FR_QTY).getAttribute("value").equals("4"));
    }
    else if (UrlBuilder.getLocale().equals("vuseco")){
      searchResult.scrollToPageTop();
      pdp.hoverOnElement(pdp.BTN_SWATCH_STRENGTH_VUSECO);
      searchResult.EnterQuantityOnPLPPage("4");
      assertTrue(searchResult.waitForExpectedElement(SearchResult.INPUT_VYPE_FR_QTY).getAttribute("value").equals("4"));
    }
    else if (!UrlBuilder.getLocale().equals("uk")){
      pdp.waitForExpectedElement(By.name("qty")).clear();
      pdp.waitForExpectedElement(By.name("qty")).sendKeys("4");
      assertTrue(pdp.waitForExpectedElement(By.name("qty")).getAttribute("value").equals("4"));
    }
  }

  @And("^modify quantity of the product to '(.*)'$")
  public void updateProductQuantity(String quantity) {
    pdp.updateQuantity(quantity);
  }

  @And("^related carousel div block is present$")
  public void relatedCarouselDivBlockIsPresent() {
   pdp.waitForExpectedElement(By.cssSelector("div.block.related")).isDisplayed();
  }

  @And("^all selectable colour variants displayed$")
  public void allSelectableColourVariantsDisplayed() {
    assertTrue(pdp.waitForExpectedElement(By.cssSelector("div.swatch-attribute-options.clearfix")).isDisplayed());
  }

  @And("^Search for a product '(.*)' , and add to cart from PDP page$")
  public void searchForProductAddToCartFromPDPAndNavigateToPaymentPage(String strProductName) throws Throwable {
    pdp.performSearch(strProductName);
    Thread.sleep(2000);
    pdp.selectProductColorStrengthFromList(pdp.btnProductStrength);
    pdp.selectProductColorStrengthFromList(pdp.btnSwatchColor);
    clickAddToCartButton();
  }

  @Then ("^user click on Avis Ratings icon and assert Avis Reviews pop up is displayed$")
  public void userClicksOnAvisRatingsIconAndVerifyAvisReviewsPopUp() {
    pdp.clickOnAvisRatingsIconAndVerifyAvisReviewsPopUp();
  }

  @When("^user clicks on Avis Ratings link$")
  public void userClicksOnAvisRatingsLink() {
    pdp.clickOnAvisRatingsLink();
  }

  @When("^user clicks on product link with '(.*)' Star Rating from PLP$")
  public void userClicksOnProductLinkWithStarRatingFromPLP(String strRating) {
    pdp.clickOnProductLinkWithStarRating(strRating);
  }

  @Then ("^verify user is directed to Review Ratings section at bottom of the page$")
  public void verifyUserDirectedToReviewRatingsSectionBottomOfThePage() {
    pdp.userIsDirectedToReviewRatingsSection();
  }

  public void userIsRedirectedToPDPpageForSelectedProduct() {
      pdp.userIsRedirectedToPDPForSelectedProduct();
  }

  @And("^click first Six Pack Lyft Lab product$")
  public void clickSixPackLyftLabProduct() {
    pdp.clickSixPackLyftLabProduct();
  }

  @And("^assert image gallery/thumbnails on top-left corner of the PDP$")
  public void assertImageThumbnailsOnTopLeftCornerOfThePDP() {
      pdp.assertImageThumbnailOnTopLeftCornerOfThePDP();
  }

  @And("^user selects a image thumbnail and assert selected image show as the product's main image$")
  public void userSelectsImageThumbnailsAndAssertProductMainImage() {
    pdp.selectImageThumbnailAndAssertProductMainImage();
  }

  @And("^enter personalisation path$")
  public void enterPersonalisationPath() {
    pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_BUTTON);
  }

  @And("^assert engraving iFrame displayed to user$")
  public void assertEngravingIFrameDisplayedToUser() {
    switch (UrlBuilder.getLocale()) {
      case "vuseco":
        assertEquals(pdp.waitForExpectedElement(pdp.CO_ENGRAVING_HEADER).getText(), UrlBuilder.getMessage("engravingHeaderText.key"));
        break;
      default:
        assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_HEADER,50).isDisplayed());
        assertEquals(pdp.waitForExpectedElement(pdp.ENGRAVING_HEADER).getText(), UrlBuilder.getMessage("engravingHeaderText.key"));
    }
  }

  @And("^select text engraving location of '(.*)'$")
  public void selectTextEngravingPositionOfFrontOrBack(String engravingLocation) {
    switch (UrlBuilder.getLocale()) {
      case "vuseco":
        if (engravingLocation.equals("front")) {
          pdp.waitForExpectedElement(pdp.CO_ENGRAVING_FRONT_OPTIONS, 10);
          pdp.clickByElementByQueryJSExecutor(pdp.CO_ENGRAVING_FRONT_OPTIONS);
        }
        if (engravingLocation.equals("back")) {
          pdp.waitForItemToBeClickableAndClick(pdp.CO_ENGRAVING_BACK_OPTIONS);
        }
        break;
      default:
        if (engravingLocation.equals("front")) {
          pdp.waitForExpectedElement(pdp.ENGRAVING_FRONT_OPTIONS, 10);
          pdp.clickByElementByQueryJSExecutor(pdp.ENGRAVING_FRONT_OPTIONS);
        }
        if (engravingLocation.equals("back")) {
          pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_BACK_OPTIONS);
        }
    }
  }

  @And("^select to engrave front of epen$")
  public void selectToEngraveFrontOfEpen() {
    pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_FRONT_OPTIONS);
  }

  @And("^summary contains the expected text of '(.*)'$")
  public void summaryContainsTheExpectedTextOfTba(String expectedSummaryText) {
    switch (UrlBuilder.getLocale()) {
      case "vuseco":
        assertTrue(pdp.waitForExpectedElement(pdp.CO_ENGRAVING_SUMMARY).getText().contains(expectedSummaryText));
      break;
      default:
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_SUMMARY).getText().contains(expectedSummaryText));
    }
  }

  @And("^summary should not be displayed to user$")
  public void summaryShouldNotBeDisplayedToUser() {
    Boolean isSummaryPresent = pdp.getWebDriver().findElements(pdp.ENGRAVING_SUMMARY).size() == 0;
    assertTrue(!isSummaryPresent);
  }

  @And("^delete selection from summary section$")
  public void deleteSelectionFromSummarySection() {
    pdp.waitForExpectedElement(pdp.REMOVE_ENGRAVING_FRONT).isDisplayed();
    pdp.waitForExpectedElement(pdp.REMOVE_ENGRAVING_FRONT,10);
    pdp.clickByElementByQueryJSExecutor(pdp.REMOVE_ENGRAVING_FRONT);
  }

  @And("^select Pattern CTA$")
  public void selectPatternCTA() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_SELECT_PATTEN_CTA).click();
  }

  @And("^select Pattern Palma$")
  public void selectPatternPalma() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_SELECT_PALMA_PATTERN).isDisplayed();
    pdp.waitForExpectedElement(pdp.ENGRAVING_SELECT_PALMA_PATTERN).click();
  }

  @And("^add confirm pattern selection$")
  public void addConfirmPatternSelection() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_CONFIRM_PATTEN).isDisplayed();
    pdp.waitForExpectedElement(pdp.ENGRAVING_CONFIRM_PATTEN).click();
  }

  @And("^add engraving options to basket$")
  public void addEngravingOptionsToBasket() throws Throwable {
    Thread.sleep(2000);
    pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_ADD_TO_CART,40);
  }

  @And("^invalid text error message displayed to user$")
  public void invalidTextErrorMessageDisplayedToUser() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_ERROR).isDisplayed();
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_ERROR).getText().equals(UrlBuilder.getMessage("engravingTextError.key")));
  }

  @And("^profanity error message displayed to user$")
  public void profanityErrorMessageDisplayedToUser() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_ERROR).isDisplayed();
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_ERROR).getText().equals(UrlBuilder.getMessage("engravingProfanityError.key")));
  }

  @And("^assert Pattern displayed$")
  public void assertPatternDisplayed() {
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_PATTERN).isDisplayed());
  }

  @And("^select mini icons CTA$")
  public void selectMiniIconsCTA() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_MINI_ICONS_CTA).click();
  }

  @And("^edit selection from summary section$")
  public void editSelectionFromSummarySection() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_EDIT_FRONT).isDisplayed();
    pdp.waitForExpectedElement(pdp.ENGRAVING_EDIT_FRONT).click();
  }

  @And("^edit text selection displayed to user$")
  public void editSelectionDisplayedToUser() {
    Boolean textSelectionAreaDisplayedpdp = pdp.waitForExpectedElement(pdp.ENGRAVING_TEXT_SELECTION,10).isDisplayed();
    assertTrue(textSelectionAreaDisplayedpdp);
  }

  @And("^assert mini icons displayed$")
  public void assertMiniIconsDisplayed() {
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_MINI_ICONS).isDisplayed());
  }

  @And("^select pods option without engraving options and assert error message$")
  public void selectPodOptionNoEngraving() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_PODS_OPTIONS,10);
    pdp.clickByElementByQueryJSExecutor(pdp.ENGRAVING_PODS_OPTIONS);
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_PODS_ERROR).getText().equals(UrlBuilder.getMessage("engravingPodsError.key")));
  }

  @And("^select skins option without engraving options and assert error message$")
  public void selectSkinOptionNoEngraving() {
    pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_SKIN_OPTIONS);
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_SKINS_ERROR).getText().equals(UrlBuilder.getMessage("engravingSkinsError.key")));
  }

  @And("^select pods option amd qty and add to order$")
  public void selectPodOption() {
    pdp.selectPodOption();
  }

  @And("^summary should be displayed to user$")
  public void summaryShouldBeDisplayedToUser() {
    switch (UrlBuilder.getLocale()){
      case "vuseco":
        try {
          assertTrue(pdp.waitForExpectedElement(pdp.CO_ENGRAVING_SUMMARY,15).isDisplayed());
        } catch (Exception e) {
          pdp.waitForExpectedElement(pdp.CO_ENGRAVING_SUMMARY,10);
        }
        break;
      default:
    assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_SUMMARY,15).isDisplayed());
    }
  }

  @And("^select to engrave back of epen$")
  public void selectToEngraveBackOfEpen() {
    pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_BACK_OPTIONS);
  }

  @And("^select positional text alignment '(.*)'$")
  public void selectPositionalTextAlignmentHorizontalOrVerticial(String textAlignment) {
    if (UrlBuilder.getLocale().equals("co")){
      if (textAlignment.equals("horizontal")){
        try {
          pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_SELECT_BACK_HORIZONTAL);
        } catch (Exception e) {
          pdp.waitForExpectedElement(pdp.ENGRAVING_SELECT_FRONT_HORIZONTAL,10);
          pdp.clickByElementByQueryJSExecutor(pdp.ENGRAVING_SELECT_FRONT_HORIZONTAL);
        }
      }
    }
    if (UrlBuilder.getLocale().equals("mx")) {
      if (textAlignment.equals("vertical")) {
        try {
          pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_SELECT_BACK_VERTICAL);
        } catch (Exception e) {
          pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_SELECT_FRONT_VERTICAL);
        }
      }
    }
  }

  @And("^select text option$")
  public void selectTextOption() {
    switch (UrlBuilder.getLocale()){
      case "vuseco":
        pdp.waitForExpectedElement(pdp.CO_ENGRAVING_SELECT_FRONT_TEXT_OPTION, 10);
        pdp.clickByElementByQueryJSExecutor(pdp.CO_ENGRAVING_SELECT_FRONT_TEXT_OPTION);
      break;
      default:
    try {
      pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_SELECT_FRONT_TEXT_OPTION);
    } catch (Exception e) {
      pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_SELECT_BACK_TEXT_OPTION);
      e.printStackTrace();
    }
  }
  }

  @And("^engraving input field contains '(.*)'$")
  public void engravingInputFieldContainsTextDisplayed(String inputFieldContains) {
    try {
      assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_FRONT_TEXT_INPUT).getAttribute("value").contains(inputFieldContains));
    } catch (Exception e) {
      assertTrue(pdp.waitForExpectedElement(pdp.ENGRAVING_BACK_TEXT_INPUT).getAttribute("value").contains(inputFieldContains));

    }
  }

  @And("^update engraving text to '(.*)'$")
  public void updateEngravingTextToMda(String engravingText) {
    switch (UrlBuilder.getLocale()){
      case "vuseco":
        try {
          pdp.waitForExpectedElement(pdp.CO_ENGRAVING_FRONT_TEXT_INPUT, 10).sendKeys(engravingText);
        }
        catch (Exception e) {
            pdp.waitForExpectedElement(pdp.CO_ENGRAVING_BACK_TEXT_INPUT).sendKeys(engravingText);
          }

        break;
      default:
    try {
      pdp.waitForExpectedElement(pdp.ENGRAVING_FRONT_TEXT_INPUT).sendKeys(engravingText);
    } catch (Exception e) {
      pdp.waitForExpectedElement(pdp.ENGRAVING_BACK_TEXT_INPUT).sendKeys(engravingText);
    }
  }
  }

  @And("^image overlay preview text should be '(.*)'$")
  public void imageOverlayPreviewText(String textShouldBe) {
    switch (UrlBuilder.getLocale()) {
      case "vuseco":
        String previewText = pdp.waitForExpectedElement(pdp.CO_ENGRAVING_PREVIEW_TEXT).getText();
        assertTrue(previewText.equalsIgnoreCase(textShouldBe));
      break;
      default:
    String previewText2 = pdp.waitForExpectedElement(pdp.ENGRAVING_PREVIEW_TEXT).getText();
    assertTrue(previewText2.equalsIgnoreCase(textShouldBe));
  }
  }

  @And("^select submit selection$")
  public void selectSubmitSelection() {
    switch (UrlBuilder.getLocale()) {
      case "vuseco":
        pdp.clickByElementByQueryJSExecutor(pdp.CO_TO_ENGRAVING_SUMMARY);
        break;
      default:
    try {
      pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_TEXT_NEXT);
    } catch (Exception e) {
      pdp.waitForItemToBeClickableAndClick(pdp.ENGRAVING_TEXT_NEXT_BACK);
      }
    }
  }
  @And("^teardown test$")
  public void teardownTest() {
    pdp.getWebDriver().close();
    pdp.getWebDriver().quit();
  }

  @And("^close engraving iFrame$")
  public void closeEngravingIFrame() {
    switch (UrlBuilder.getLocale()) {
    case "vuseco":
    pdp.clickByElementByQueryJSExecutor(pdp.CO_ENGRAVING_CLOSE_IFRAME);
    break;
    default:
    pdp.waitForExpectedElement(pdp.ENGRAVING_CLOSE_IFRAME,15);
    pdp.clickByElementByQueryJSExecutor(pdp.ENGRAVING_CLOSE_IFRAME);
    pdp.getWebDriver().switchTo().defaultContent();
    assertTrue(!returnIFrameName().equals(UrlBuilder.getMessage("engravingiFrameName.key")));
    }
  }

  public String returnIFrameName(){
    JavascriptExecutor jsExecutor = (JavascriptExecutor)pdp.getWebDriver();
    String currentFrame = jsExecutor.executeScript("return self.name").toString();
    LOG.info("\n ******* Current iFrame : " + currentFrame);
    return currentFrame;
  }

  @And("^assert engraving iFrame not present$")
  public void assertEngravingiFrameNotDisplayed() {
    pdp.getWebDriver().switchTo().defaultContent();
    assertTrue(!returnIFrameName().equals(UrlBuilder.getMessage("engravingiFrameName.key")));
  }

  @And("^user selects cancel button to stop engraving process$")
  public void cancelEngravingProcess() {
    switch (UrlBuilder.getLocale()) {
      case "vuseco":
        pdp.clickByElementByQueryJSExecutor(pdp.CO_ENGRAVING_CLOSE_IFRAME);
        break;
      default:
      pdp.waitForExpectedElement(pdp.ENGRAVING_CANCEL,30);
      pdp.clickByElementByQueryJSExecutor(pdp.ENGRAVING_CANCEL);
    }
  }
  @And("^select customer engraving CTA$")
  public void selectCustomerEngravingCTA() {
    pdp.waitForExpectedElement(pdp.ENGRAVING_CUSTOMER_ENGRAVING_CTA,50);
    pdp.clickByElementByQueryJSExecutor(pdp.ENGRAVING_CUSTOMER_ENGRAVING_CTA);
  }

  @And("^assert product appears as carousel with triangles below product main image$")
  public void assertProductImagesAppearsAsCarouselWithTrianglesBelowMainProduct() {
      pdp.assertImagesAppearsAsCarouselWithTrianglesBelowMainProduct();
  }

  @And("^assert user is able to scroll through images through carousel$")
  public void assertUserAbleToScrollThroughImagesThroughCarousel() {
      pdp.assertUserAbleToScrollThroughImagesThroughCarousel();
  }

    @And("^assert no image thumbnails show on Mobile$")
    public void assertNoImageThumbnailsShowOnMobile() {
      pdp.assertNoImageThumbnailsShowOnMobile();
    }

    @And("^assert single LAB pack Quantity dropdown show options '(.*)' same as any Lyft Product on PDP$")
    public void assertSingleLABPackQtyDropDownOptionsSameAsLyftProductOnPDP(String strCount) {
      pdp.verifySingleLABPackQtyDropDownOptionsOnPDP(strCount);
    }

    @And("^assert three LAB pack Quantity dropdown show options '(.*)' same as any Lyft Product on PDP$")
    public void assertThreeLABPackQtyDropDownOptionsSameAsLyftProductOnPDP(String strCount) {
      pdp.verifyThreeLABPackQtyDropDownOptionsOnPDP(strCount);
    }

    @And("^assert six LAB pack Quantity dropdown show options '(.*)' same as any Lyft Product on PDP$")
    public void assertSixLABPackQtyDropDownOptionsSameAsLyftProductOnPDP(String strCount) {
      pdp.verifySixLABPackQtyDropDownOptionsOnPDP(strCount);
    }

    @And("^assert LAB products Quantity dropdown show options '(.*)' same as any Lyft Product on PLP$")
    public void assertLABProductsQtyDropDownOptionsSameAsLyftProductOnPLP(String strCount) {
      pdp.verifyLABProductsQtyDropDownOptionsOnPLP(strCount);
    }

    @And("^user selects '(.*)' for the LAB product from Quantity dropdown$")
    public void userSelectsFromQuantityDropDownForLABProduct(String strQuantity) {
      pdp.userSelectsFromQuantityDropDownForLABProduct(strQuantity);
    }

    @And("^user selects '(.*)' for the LAB product from Strength dropdown$")
    public void userSelectsFromStrengthDropDownForLABProduct(String strStrength) {
      pdp.userSelectsFromStrengthDropDownForLABProduct(strStrength);
    }

    @And("^click a three pack Lyft Lab product$")
    public void clickThreePackLyftLabProduct() {
      pdp.clickThreePackLyftLabProduct();
    }

  @And("^assert Related Products section and carousal at bottom of the page$")
  public void assertRelatedProductsSectionAndCarousalAtBottomOfThePage() {
    pdp.assertRelatedProductsSectionAndCarousalOnPDP();
  }

  @And("^assert UpSell Products section and carousal at bottom of the page$")
  public void assertUpSellProductsAndCarousalSectionAtBottomOfThePage() {
    pdp.assertUpSellProductsSectionAndCarousalOnPDP();
  }

  @Then ("^user selects a product from Related Products section and assert Add To Cart button$")
  public void userSelectsProductFromRelatedProductsAndAssertAddToCartButton() {
    pdp.selectProductFromRelatedBlockAndAssertAddToCartButton();
  }

  @Then ("^user selects a product from UpSell Products section and assert Add To Cart button$")
  public void userSelectsProductFromUpSellProductsAndAssertAddToCartButton() {
    pdp.selectProductFromUpSellBlockAndAssertAddToCartButton();
  }

  @And("^assert Cross Sell Products section and carousal on the Cart page$")
  public void assertCrossSellProductsSectionAndCarousalOnTheCartPage() {
    pdp.assertCrossSellProductsSectionAndCarousalOnCartPage();
  }

  @And("^assert Add To Cart button is displayed for Cross Sell Products and assert CTA$")
  public void assertAddToCartButtonForCrossSellProductsAndAssertCTA() throws Throwable {
    pdp.assertAddToCartButtonForCrossSellProductsAndAssertCTA();
  }

  @And("^click add to cart button for a cross sell product$")
  public void clickOnAddToCartButtonForCrossSellProduct() {
    pdp.clickOnAddToCartButtonForCrossSellProduct();
  }

  @And("^assert Cross Sell Products Carousel if more than 4 products appears$")
  public void assertCrossSellProductsCarouselIfMoreProductsAppears() {
    pdp.assertCrossSellProductsCarouselAndCTA();
  }

  @And("^user clicks on the first link if navigated to PLP page$")
  public void userClicksOnFirstLinkIfNavigatedToPLPPage() throws Throwable {
    pdp.clickOnFirstLinkIfNavigatedToPLP();
  }

  @And("^user clicks on the first link if navigated to PLP page and add to cart$")
  public void userClicksOnFirstLinkIfNavigatedToPLPPageAndAddToCart() throws Throwable {
    pdp.clickOnFirstLinkIfNavigatedToPLPAndAddToCart();
  }

  @And("^empty basket before adding related products$")
  public void emptyBasketBeforeAddingRelatedProducts() {
    pdp.emptyBasketBeforeAddingRelatedProducts();
  }

  @And("^user logins Magento Admin and assert Related,Up-Sell and Cross-Sell Products Configuration$")
  public void userLoginsMagentoAdminAndAssertRelatedUpSellProductsConfiguration() throws Throwable {
    pdp.loginMagentoAdminAndAssertRelatedUpSellProductsConfiguration();
  }

  @Then("^assert product is personalizable$")
  public void assertProductIsPersonalizable() {
    pdp.assertPersonalizeableServiceOnPDP();
  }

  @Then("^assert personalizable tab is visible$")
  public void assertPersonalizableTabIsVisible() {
    pdp.assertPersonalizeTabOption();
  }

  @Then("^assert personalisation-options is available$")
  public void assertPersonalisationOptionsIsAvailable() {
    pdp.assertPersonalizeServiceOptions();
  }

  @Then("^assert link of personalize back-only is available$")
  public void assertLinkOfPersonalizeBackOnlyIsAvailable() {
    pdp.assertLinkOfBackOnlyPersonalization();
  }

  @Then("^assert link of cancel personlize serivce is available$")
  public void assertLinkOfCancelPersonlizeSerivceIsAvailable() {
    pdp.assertCancelationLinkOnPersonalization();
  }
  @Then("^assert link of cancel button is available on personlize serivce$")
  public void assertLinkOfCancelButtonIsAvailableOnPersonlizeSerivce() {
    pdp.assertCancelButtonLinkOnPersonalization();
  }


  @And("^user click to opt personalizable service$")
  public void userClickToOptPersonalizableService() {
    pdp.clickToOptPersonalizeService();
  }

  @And("^user skips front personalisation$")
  public void userSkipsFrontPersonalizableService() {
    pdp.clickToSkipFrontPersonalizeService();
  }

  @And("^user clicks on horizental tab of engraving$")
  public void userClicksOnHorizentalTabOfEngraving() {
    pdp.clickOnHorizentalTabOfEngraving();
  }

  @And("^assert error validation message for using more letters than defined limit|restricted words$")
  public void assertErrorValidationMessageForUsingTextMoreThanDefinedLengthLimit() {
    pdp.userAssertErrorValidationMessageIsDisplayed();
  }

  @Then("^user add text on the device with \"([^\"]*)\" and assert error message$")
  public void userAddRestrictedWordsAndassertErrorValidationMessage(String  lisOfBadWords) {
    String[] types = UrlBuilder.getMessage(lisOfBadWords).split(",");
    for (String str : types) {
      pdp.addTextOnTheDevice(str);
      pdp.userAssertErrorValidationMessageIsDisplayed();
      pdp.clearTextOnTheInputFieldInEngraving();
    }
  }

  @And("^user skips back personalisation$")
  public void userSkipsBackPersonalizableService() {
    pdp.clickToSkipBackPersonalizeService();
  }

  @And("^user clicks on the mentioned pagelink and verify cancel-link is displayed$")
  public void userClickOnLinkAndVerifyCancelButtonLink(DataTable dataTable) {
    int i = 0;
    for (List<String> elementItem : dataTable.raw()) {
      pdp.clickByElementByQueryJSExecutor(By.xpath(UrlBuilder.getMessage(elementItem.get(i))));
      pdp.assertCancelButtonIsDisplayed(elementItem.get(i));
    }
  }

  @And("^user clicks on the mentioned pagelink and verify back-button is displayed$")
  public void userClickOnLinkAndVerifyBackButtonLink(DataTable dataTable) {
    int i = 0;
    for (List<String> elementItem : dataTable.raw()) {
      pdp.clickByElementByQueryJSExecutor(By.xpath(UrlBuilder.getMessage(elementItem.get(i))));
      if(elementItem.get(i).equalsIgnoreCase("backTab.Key"))
        pdp.assertBackButtonIsDisplayedOnBackTab();
      else if(elementItem.get(i).equalsIgnoreCase("summaryTab.Key"))
        pdp.assertBackButtonIsDisplayedOnSummaryTab();
    }
  }

  @And("^user clicks on the mentioned pagelink and verify color swatch tab is displayed$")
  public void userClickOnLinkAndVerifyColorSwatchTabIsDisplayed(DataTable dataTable) {
    int i = 0;
    for (List<String> elementItem : dataTable.raw()) {
      pdp.clickByElementByQueryJSExecutor(By.xpath(UrlBuilder.getMessage(elementItem.get(i))));
      pdp.assertColorSwatchIsDisplayed();
    }
  }

  @And("^user clicks on the mentioned pagelink and verify clicking on the cancel button redirects the user out of the engraving$")
  public void userClickOnCancelButtonAndVerifyEngravingFeatureNotSelected(DataTable dataTable) {
    int i = 0;
    for (List<String> elementItem : dataTable.raw()) {
      pdp.clickByElementByQueryJSExecutor(By.xpath(UrlBuilder.getMessage(elementItem.get(i))));
      pdp.userClicksOnCancelButton(elementItem.get(i));
      pdp.assertPDPPageDeliveryMessageIsDisplayed();
      pdp.clickToOptPersonalizeService();
    }
  }

  @And("^select engraving \"([^\"]*)\" on front$")
  public void selectEngravingOfFront(String arg0)  {
    switch(arg0.toLowerCase()){
      case "pattern":
        pdp.clickToPattern();
        break;
      case "icon":
        pdp.clickToIcons();
        break;
      case "text":
        pdp.clickToText();
        break;
    }
  }

  @And("^select engraving '(.*)' on front and verify back button on '(.*)'$")
  public void selectEngravingOfFrontSideAndVerifyBackButton(String optionToChoose, String chosenOption)  {
    switch(optionToChoose.toLowerCase()){
      case "pattern":
        pdp.clickToPattern();
        break;
      case "icon":
        pdp.clickToIcons();
        break;
      case "text":
        pdp.clickToText();
        break;
    }
    pdp.verifyBackButton(chosenOption);
  }

  @Then("^assert pattern is selected on the image$")
  public void assertPatternIsSelectedOnTheImage() {
    pdp.getAssertionPersonalizePatternImageOnDevice();
  }

  @And("^select first visible patternOrIcon$")
  public void selectFirstVisiblePattern() {
    pdp.clickToFirstPatternOrIconImage();
  }


  @And("^select first visible engraving patternOrIcon$")
  public void selectFirstVisibleEngravingPatternOrIcon() {
    pdp.clickOnFirstPatternOrIconImage();
  }

  @And("^user choose to edit chosen patternOrIcon$")
  public void editChosenPatternOrIcon() {
    pdp.editPatternOrIconImage();
  }

  @And("^user clicks on add and continue button \"([^\"]*)\"$")
  public void userClicksOnAddAndContinueButton(String chosenService)  {
    pdp.clickOnAddAndContinueButton(chosenService);
  }

  @And("^user add text on the \"([^\"]*)\" of the device with \"([^\"]*)\" orientation$")
  public void userAddTextOnTheOfTheDeviceWithOrientation(String sideOfTheDevice, String alignment){
    pdp.addTextOnTheDevice(sideOfTheDevice, alignment);
  }

  @Then("^assert total price of the product on summary page has engraving charges$")
  public void assertThePriceShownToTheUserIsIncludingEngravingServiceCharge() {

    DecimalFormat df2 = new DecimalFormat("#.##");
    Double expctedTotalPrice =Double.parseDouble(df2.format(pdp.totalPriceWithChosenEngravingService()));
    pdp.clickToOptPersonalizeService();
    selectEngravingOfFront("text");
    pdp.clickOnAddAndContinueButton("text");
    pdp.addTextOnTheDevice("back", "vertical");
    pdp.clickOnAddAndContinueButton("back");
    Double displayedSummaryPrice= pdp.summaryPrice();
    assertEquals(expctedTotalPrice, displayedSummaryPrice);
  }

  @Then("^assert total price is correct and price-breakdown popup has engraving charges$")
  public void assertTotalDisplayedPriceAndPriceBreakDownTotalIsSame() {
    Double expctedTotalPrice =pdp.getDisplayedTotalPriceWithEngraving();
    Double displayedProductOnlyPriceInPriceBreakDownPopUp= pdp.productOnlyPriceInPriceBreakDownPopUp();
    Double displayedEngravingOnlyPriceInPriceBreakDownPopUp= pdp.engravingOnlyPriceInPriceBreakDownPopUp();
    Double displayedTotalPriceInPriceBreakDownPopUp= displayedProductOnlyPriceInPriceBreakDownPopUp+displayedEngravingOnlyPriceInPriceBreakDownPopUp;
        assertEquals(expctedTotalPrice, displayedTotalPriceInPriceBreakDownPopUp);
  }

  @And("^user clicks on edit front end choice$")
  public void userClicksOnEditFrontEndChoice() {
    pdp.userClicksOnEditFrontEndChoice();
  }

  @Then("^assert on summary page that user has selected personalization service$")
  public void assertOnSummaryPageThatUserHasSelectedPersonalizationService() {
    pdp.defaultPriceNoticeNotDisplayed();
  }

  @And("^user remove the selected engraving service for both front and back$")
  public void userRemoveTheSelectedEngravingServiceForBothFronaAndBack() {
    pdp.removeBothFrontAndBackEndChoice();
  }

  @Then("^assert displaying a alert message for removal of engraved service$")
  public void assertDisplayingAAlertMessageForRemovalOfEngravedService() {
    pdp.defaultPriceNoticeDisplayed();
  }
  @Then("^assert add to cart button is disabled$")
  public void assertAddToCartButtonIsDisabledUntilProductIsEngraved() {
    pdp.VerifyAddToCartCTAIsDisabled();
  }

  @Then("^assert mini cart basket displays the engraving details$")
  public void assertMiniCartBasketDisplaysTheEngravingDetails() {
    pdp.engravingDetailsConfirmationOnminiBasket();
  }

  @Then("^verify mini cart basket displays the engraving details$")
  public void verifyMiniCartBasketDisplaysTheEngravingDetails() {
    pdp.engravingDetailsPresenceConfirmationOnminiBasket();
  }

  @And("^assert internal redirect of PDP links URLs with success status code$")
  public void assertInternalRedirectOfPDPLinksWithSuccessStatusCode() {
      pdp.assertInternalRedirectOfPDPlinksWithStatusCode();
  }

  @And("^assert pdp-personalisation-panel is displayed$")
  public void assertPDPPersonalisationPanelIsDisplayed() {
    pdp.assertPDPPersonalisationPanelIsDisplayed();
  }

  @And("^assert pdp-personalisation-panel icon tooltip is displayed$")
  public void assertPDPPersonalisationPanelIconToolTipIsDisplayed() {
    pdp.assertPDPPersonalisationPanelIconTollTipIsDisplayed();
  }

  @And("user clicks on pdp-personalisation-panel icon tooltip$")
  public void userClicksPDPPersonalisationPanelIconTooltip() {
    pdp.userClicksOnPDPPersonalisationPanelIcon();
  }

  @And("^user clicks on Find Out More link for a Tofino product$")
  public void userClicksOnFindOutMoreLinkForTofinoProduct() {
    pdp.userClicksOnFindOutMoreLinkForTofinoProduct();
  }

  @And("^add Tofino product to the basket$")
  public void addTofinoProductToBasket() throws Throwable{
    batHelper.addTofinoProductToBasket();
  }

  @And("^assert invalid post code message is displayed$")
  public void assertInvalidPostCodeMessageIsDisplayed() {
    pdp.assertInvalidPostCodeMessageIsDisplayed();
  }

  @And("^assert user navigates to Tofino PDP$")
  public void userNavigatesToTofinoPDP() {
    pdp.userNavigatesToTofinoPDP();
  }

  @And("^assert Newsletter Component and footer disclaimer for Tofino products$")
  public void assertNewsletterComponentAndFooterDisclaimerForTofinoProducts() {
    pdp.assertNewsletterComponentAndFooterDisclaimerForTofinoProducts();
  }

  @Then("^assert Newsletter Sign Up is displayed on Basket page$")
  public void assertNewsletterSignUpIsDisplayedOnBasketPage() {
    assertTrue(pdp.waitForExpectedElement(paymentPage.NEWSLETTER_SUBSCRIPTION_COMPONENT).isDisplayed());
  }

  @And("^fetch selected CBD strength variant$")
  public void fetchSelectedCBDStrengthVariant() {
    pdp.fetchSelectedCBDStrengthVariant();
  }

  @And("^set quantity to '(\\d+)'$")
  public void setQuantityTo(String qty) {
    pdp.updateQuantity(qty);
  }

  @And("^close side panel basket$")
  public void closeSidePanelBasket(){
    pdp.waitForItemToBeClickableAndClick(pdp.MINICART_CLOSE_BUTTON,10);
  }
  @And("coupon code validation on cartpage '(.*)'")
  public void couponCodeValidationOnCartPage(String couponAmount){
    //   pdp.setPlusQuantityIcon();
    pdp.waitForPage();
    pdp.couponCodeValidationOnCartPage(couponAmount);
  }

  @And("coupon code validation on OrderHistorypage '(.*)'")
  public void couponCodeValidationOnOrderHistoryPage(String couponAmount){
    pdp.couponCodeValidationOnOrderHistoryPage(couponAmount);
  }

  @Then("^assert link \"([^\"]*)\" is present$")
  public void assertLinkIsPresent(String linkText) {
    pdp.waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkText))).isDisplayed();
  }

  @Then("^eyes check PDP error message$")
  public void eyesCheckPDPErrorMessage() {
    pdp.eyesCheckPdpPage();
  }

  @Then("^assert ask a question button is displayed$")
  public void assertAskQuestionButtonIsDisplayed() {
    assertTrue(pdp.waitForExpectedElement(pdp.ASK_QUESTION_BUTTON).isDisplayed());
  }

  @Then("^assert question popup is displayed on clicking ask a question cta$")
  public void assertAskQuestionPopupIsDisplayed() {
    pdp.cLickOnAskQuestionCTA();
    assertTrue(pdp.waitForExpectedElement(pdp.ASK_QUESTION_BUTTO_POPUP,5).isDisplayed());
  }

  @Then("^fill the form and click on submit$")
  public void fillAskQuestionForm() {
    pdp.fillAskQuestionForm();
  }

  @And("^verify subscription confirmation mail for ask a question$")
  public void assertConfirmationRequestEmailForAskQuestion() {
    registrationPage.verifyEmailAndReturn(UrlBuilder.getMessage("email.key"), "token");
  }

  @And("^assert specifications section is displayed$")
  public void specificationPresent(){
    pdp.assertSpecificationPresentUnderPdpPage();
  }

  @Then("^user clicks on Add button to personalize the engraving product$")
  public void userClicksOnAddButtonToPersonalizeProduct() {
    pdp.clickUsingJS(pdp.ADD_TO_PERSONALIZE_BUTTON);
    assertTrue(pdp.waitForExpectedElement(pdp.PRODUCT_PERSONOLIZATION_OPTIONS).isDisplayed());
  }

  @Then("^assert personalization options Motifs and Mini Icons are displayed$")
  public void assertPersonalizationOptionsMotifsAndMiniIconsAreDisplayed() {
    List<WebElement> lstPatterns = pdp.getWebDriver().findElements(pdp.NEW_PERSONOLIZATION_OPTIONS);
    assertTrue(lstPatterns.get(0).getText().equalsIgnoreCase("Motifs"));
    assertTrue(lstPatterns.get(1).getText().equalsIgnoreCase("Mini icônes"));
  }

  @And("^assert library of Motifs are visible to the user$")
  public void assertLibraryOfMotifsPatternVisibleToTheUser() {
    pdp.waitForItemToBeClickableAndClick(pdp.MOTIFS_MINI_ICON_CUSTOMIZE_OPTION,5);
    assertTrue(pdp.getWebDriver().findElements(pdp.MOTIFS_PERSONOLIZATION_OPTIONS).size()>=0);
  }

  @And("^assert library of Mini Icons are visible to the user$")
  public void assertLibraryOfMiniIconsPatternVisibleToTheUser() {
    pdp.clickFirstElementByQueryJSExecutor(pdp.PERSONLIZATION_RETURN_LINK);
    pdp.clickUsingJS(pdp.getWebDriver().findElements(pdp.MOTIFS_MINI_ICON_CUSTOMIZE_OPTION).get(1));
    assertTrue(pdp.getWebDriver().findElements(pdp.MINI_ICONS_PERSONOLIZATION_OPTIONS).size()==14);
  }

  @And("^user selects a Motif pattern and assert selection$")
  public void userSelectsMotifPatternAndAssertSelection() {
    pdp.clickFirstElementByQueryJSExecutor(pdp.MOTIFS_PERSONOLIZATION_OPTIONS);
    assertTrue(pdp.waitForExpectedElement(pdp.MOTIF_PATTERN_SELECTED).isDisplayed());
  }

  @And("^user selects a Mini Icon and assert selection$")
  public void userSelectsMiniIconAndAssertSelection() {
    pdp.clickFirstElementByQueryJSExecutor(pdp.MINI_ICONS_PERSONOLIZATION_OPTIONS);
    assertTrue(pdp.waitForExpectedElement(pdp.MINI_ICON_SELECTED).isDisplayed());
  }


  @And("^assert review rating is present$")
  public void assertReviewRatingIsPresent() {
    switch(UrlBuilder.getLocale()){
      case "it":
      case "vuseit":
        assertTrue(pdp.waitForExpectedElement(pdp.STAR_RATING).isDisplayed());
        assertTrue(pdp.waitForExpectedElement(pdp.STAR_RATING_REVIEW).isDisplayed());
        break;
      case "vuseuk":
        assertTrue(pdp.waitForExpectedElement(pdp.STAR_RATING_REVIEW_VUSEUK, 5).isDisplayed());
        assertTrue(pdp.waitForExpectedElement(pdp.STAR_FIVE_RATING_REVIEW, 5).isDisplayed());
        break;
      case "vuseza":
        assertTrue(pdp.waitForExpectedElement(pdp.STAR_RATING).isDisplayed());
        assertTrue(pdp.waitForExpectedElement(pdp.STAR_RATING_REVIEW_ZA).isDisplayed());
        break;
      case "pl":
        assertTrue(pdp.isElementDisplayedBySeconds(pdp.STAR_RATING_PL,2));
        pdp.scrollToElement(pdp.STAR_RATING_OPINION);
        assertTrue(pdp.isElementDisplayedBySeconds(pdp.STAR_RATING_OPINION,2));
        break;
      default:
    }
  }

  @When("^user click on add rating CTA$")
  public void userClickOnAddRatingCTA() {
    switch(UrlBuilder.getLocale()) {
      case "it":
        pdp.waitForItemToBeClickableAndClick(pdp.STAR_RATING_REVIEW, 10);
        if(UrlBuilder.isMobile()) {
          pdp.waitForItemToBeClickableAndClick(pdp.M_ADD_REVIEW_BUTTON, 10);
        }
        else {
          pdp.waitForItemToBeClickableAndClick(pdp.ADD_REVIEW_BUTTON, 10);
        }
        break;
      case "vuseit":
        pdp.waitForItemToBeClickableAndClick(pdp.STAR_RATING_REVIEW, 10);
        pdp.waitForItemToBeClickableAndClick(pdp.ADD_REVIEW_BUTTON, 10);
        break;
      case "vuseuk":
        if (System.getProperty("PDPReview.key").equals("Yes")) {
          pdp.scrollElementIntoView(pdp.WRITE_REVIEW_BUTTON);
          pdp.clickByElementByQueryJSExecutor(pdp.WRITE_REVIEW_BUTTON);
        } else {
          pdp.scrollElementIntoView(pdp.WRITE_FIRST_REVIEW_BUTTON);
          pdp.clickByElementByQueryJSExecutor(pdp.WRITE_FIRST_REVIEW_BUTTON);
        }
        pdp.waitForAjaxElementNotToBePresent(pdp.getWebDriver(), 10);
        break;
      case "vuseza":
        pdp.scrollElementIntoView(pdp.ADD_REVIEW_BUTTON);
        pdp.clickUsingJS(pdp.ADD_REVIEW_BUTTON);
        break;
      case "pl":
        pdp.clickUsingJS(pdp.ADD_RATING_QUALITY);
        pdp.clickUsingJS(pdp.ADD_RATING_PRICE);
        pdp.clickUsingJS(pdp.ADD_RATING_FUNCTIONALITY);
        pdp.clickUsingJS(pdp.STAR_RATING_OPINION);
        break;
      default:
    }
  }

  @Then("^verify an error message is displayed$")
  public void assertErrorMassageForGuestUser() {
    assertTrue(pdp.waitForExpectedElement(pdp.GUST_USER_REVIEW_MESSAGE,8).isDisplayed());
    assertTrue(pdp.waitForExpectedElement(pdp.GUST_USER_REVIEW_SIGNIN_LINK).isDisplayed());
  }

  @And("^guest user click on sign in link from error message$")
  public void userClickOnSignInLink() {
    pdp.clickByElementByQueryJSExecutor(pdp.GUST_USER_REVIEW_SIGNIN_LINK);
  }

  @And("^user fill the rating form and submit$")
  public void userFillAndSubmitRatingForm() {
    pdp.fillAndSubmitRatingForm();
  }

  @And("^assert Delivery message on PDP on basis of current timeline$")
  public void assertDeliveryMessagesOnPDPonTheBasisOfCurrentTimeline() throws ParseException {
    String EndTimeLine = "15:00";

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Calendar cal = Calendar.getInstance(); // creates calendar
    cal.add(Calendar.HOUR_OF_DAY, 1);
    cal.add(Calendar.MINUTE,1);
    String currentTime = dateFormat.format(cal.getTime());

    if (!pdp.getWeekDay().equalsIgnoreCase("Saturday") && !pdp.getWeekDay().equalsIgnoreCase("Sunday")) {
      if((LocalTime.parse(currentTime).compareTo(LocalTime.parse(EndTimeLine)) == -1)){
        String NEXT_DAY_DELIVERY_TIMELINES=pdp.waitForExpectedElement(pdp.NEXT_DAY_DELIVERY_BLOCK,10).getText();
        String NDD_COUNTOWN_DETAILS=pdp.waitForExpectedElement(pdp.NEXT_DAY_DELIVERY_COUNTDOWN,10).getText();
        String[] NDD_COUNTDOWN_TIMER=pdp.waitForExpectedElement(pdp.NEXT_DAY_DELIVERY_COUNTDOWN,10).getText().split(" ");
        assertEquals(NEXT_DAY_DELIVERY_TIMELINES,"Order within "+NDD_COUNTOWN_DETAILS+" for next day delivery.");
        assertTrue(NDD_COUNTDOWN_TIMER[0].matches("\\d+") && NDD_COUNTDOWN_TIMER[2].matches("\\d+"));
        assertTrue(NDD_COUNTDOWN_TIMER[1].equalsIgnoreCase("hrs") && NDD_COUNTDOWN_TIMER[3].equalsIgnoreCase("mins"));}
      else {
        // changed from 2-4 days to 3-5 days as per live @ 07/06/2022
        assertTrue(pdp.waitForExpectedElement(pdp.DELIVERY_TIMELINE_MESSAGE, 10).getText().contains("DELIVERED IN 3-5 WORKING DAYS"));
        userClicksOnMoreInformationLinkOnPDP("pdpMoreInformationLinkText.key");
      }
    }
    else{
      assertTrue(pdp.waitForExpectedElement(pdp.DELIVERY_TIMELINE_MESSAGE,10).getText().contains("DELIVERED IN 3-5 WORKING DAYS"));
      userClicksOnMoreInformationLinkOnPDP("pdpMoreInformationLinkText.key");}
  }

  @And("^user clicks on More Information link with text '(.*)'$")
  public void userClicksOnMoreInformationLinkOnPDP(String strLinkText) {
    pdp.waitForExpectedElement(pdp.MORE_INFORMATION_LINK,5);
    assertTrue(pdp.waitForExpectedElement(pdp.MORE_INFORMATION_LINK).getText().equalsIgnoreCase(UrlBuilder.getMessage(strLinkText)));
    pdp.clickByElementByQueryJSExecutor(pdp.MORE_INFORMATION_LINK);
    pdp.switchBetweenWindowTabs(1);
    assertTrue(pdp.getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage("DeliveryReturnUrl.key")));
    pdp.assertTrueWithCustomError(UrlBuilder.getMessage("deliveryReturnsPageTitle.key"),pdp.getCurrentPageTitle());
  }

  @And("^assert first (?:device|flavor) SKU is pre-selected by default when in stock$")
  public void assertFirstDeviceFlavorSKUIsPreSelectedByDefaultWhenInStock() {
    assertTrue(pdp.waitForExpectedElement(pdp.PDP_SELECTED_STRENGTH_COLOR, 10).isDisplayed());
  }

  @And("^user selects OOS (?:device|flavor) SKU and assert Notify Me CTA$")
  public void userSelectsOOSDeviceSKUAndAssertNotifyMeCTA() {
    pdp.selectOOSDeviceSKUAndAssertNotifyMeCTA();
  }

  @Then("^assert review button is present for (.*) product$")
  public void assertReviewButton(String product) {
    if(System.getProperty("PDPReview.key").equals("Yes"))
      assertTrue(pdp.waitForExpectedElement(pdp.WRITE_REVIEW_BUTTON,8).isDisplayed());
    else assertTrue(pdp.waitForExpectedElement(pdp.WRITE_FIRST_REVIEW_BUTTON,8).isDisplayed());
  }

  @And("^assert rating modal window is opened$")
  public void assertRatingModalWindowIsDisplayed() {
    assertTrue(pdp.waitForExpectedElement(pdp.WRITE_REVIEW_MODAL_WINDOW,8).isDisplayed());
  }

  @And("^assert form is submitted successfully$")
  public void assertFormIsSubmittedSuccessfully() {
    assertTrue(pdp.waitForExpectedElement(pdp.REVIEW_SUBMITTED_WINDOW,10).isDisplayed());
    assertTrue(pdp.waitForExpectedElement(pdp.REVIEW_SUBMITTED_TEXT,10).isDisplayed());
  }

  @When("^user close the review submit confirmation popup$")
  public void closeReviewSubmitPopup() {
    pdp.clickByElementByQueryJSExecutor(pdp.REVIEW_SUBMITTED_CLOSE_OPTION);
  }

  @Then("^assert the review description is same as submitted by user$")
  public void assertReviewisSameAsSubmitted() {
    assertTrue(pdp.getReviewedDescriptionText().equalsIgnoreCase(System.getProperty("ReviewDescription.key")));
  }

  @Then("^user see default One time Purchase option selected$")
  public void assertOneTimePurchaseOptionSelected() {
    pdp.verifyOneTimePurchaseOptionSelected();

  }
  @Then("^user verify Subscription option is unselected$")
  public void assertSubscriptionOptionIsUnselected() {
    pdp.verifySubscriptionOptionUnselected();
  }

  @Then("^user select subscription option$")
  public void userselectSubscriptionOption() {
    pdp.clickSubscriptionOption();
  }

  @And("^user select subscription option plp$")
  public void userselectSubscriptionOptionPlp() {
    pdp.clickSubscriptionOptionPlp();
  }

  @Then("^customers see the at the end of the \"One time purchase\" label the amount spent on that SKU$")
  public void assertonetimepurchasepricedisplay() {
    pdp.verifyOneTimePurchasePriceDisplay();
  }

  @And("^customers see the at the end of the \"Subscription\" label the total monthly amount for that SKU$")
  public void assertsubscriptionpricedisplay() {
    pdp.verifySubscriptionPriceDisplay();
  }

  @And("^on clicking on \"i\" icon the modal window opens up$")
  public void userClickInfoButton() {
    pdp.verifyUserClickSubscriptionInfo();
  }

  @Then("^user see \"You save £xx\" below the whole component$")
  public void assertYouSaveIsDisplayed() {
    pdp.verifySubscriptionSaveDisplayed();
  }


    @And("^user views the cart$")
    public void userViewsTheCart() {
    homepage.clickBasketIcon();
    paymentPage.clickOnViewBasketButton();
    }

  @Then("^verify product quantity and price is displayed$")
  public void verifyProductQuantityAndPriceDisplayed() {
    pdp.assertProductQuantityAndPrice();
  }

  @Then("^user increases the quantity of the selected item and verify price remain same$")
  public void verifyProductPriceRemainSameAfterQuantityIncrease() {
    pdp.assertProductPriceRemainSame();
  }

  @Then("^verify multiple product are present in combos$")
  public void verifyMultipleProductPresentInCombo() {
    pdp.assertMultipleProductPresentInCombo();
  }

  @Then("^FAQ is present on PDP$")
  public void faqIsPresentOnPDP() {
    assertTrue(pdp.isFAQPresent());
  }

  @Then("^verify all CTA present on PDP page$")
  public void verifyCTAonPDPPage() {
    pdp.verifyAllCTAonPDPPAge();
  }

  @Then("^verify FAQ's is present for product$")
  public void verifyFAQsPresentForProduct() {
    assertTrue(pdp.verifyFAQsForProduct());
  }

  @And("^verify all CTAs should navigate to the correct pages$")
  public void verifyAllCTANavigateToCorrectPage() {
    pdp.verifyCTANavigateToCorrectPage();
  }

  @And("^user verify all the CTA present on Device epod page$")
  public void verifyCTAonDeviceEpodPage() {
    pdp.verifyCTAonDeviceEpodPage();
  }

  @And("^user clicks on more subscription link$")
  public void userClicksOnMoreSubscriptionLinkOnPDP() { pdp.clickMoreSubscriptionLink(); }

  @Then("assert subscription pop-up is displayed on PDP page$")
  public void assertSubscriptionPopUpIsDisplayedOnPDPPage() { assertTrue(pdp.isSubscriptionPopUpDisplayedOnPDP()); }

  @And("^user clicks on cancel subscription link$")
  public void userClicksOnCancelSubscriptionLinkOnPDP() { pdp.clickCancelSubscriptionLink(); }

  @Then("^assert PDP specification and feedback$")
  public void getPDPProductSpecificationAndFeedback() {
      assertTrue(pdp.isElementDisplayedBySeconds(pdp.PRODUCT_FEEDBACK,2));
  }

  @Then("^opinion succesfully send$")
  public void userOpinionSuccessfullySend() {
    assertTrue(pdp.isElementDisplayedBySeconds(pdp.OPINION_SEND,5));
  }

  @Then("^assert the product is displayed success$")
  public void assertTheProductIsDisplayedSuccess() {
    assertTrue(pdp.isElementDisplayedBySeconds(pdp.M_ADDTOCART_BUTTON,2));
  }

  @Then("^opinion successfully send$")
  public void opinionSuccessfullySend() {
    assertTrue(pdp.isElementDisplayedBySeconds(pdp.OPINION_SEND,5));
  }

  @Then("^assert rating star is present$")
  public void assertRatingStarIsPresent() {
      assertTrue(pdp.isElementDisplayedBySeconds(pdp.STAR_RATING_BADGE,2));
  }

  @And("^assert product review list is present$")
  public void assertProductReviewListIsPresent() {
      assertTrue(pdp.getProductReviewListSize()>0);
  }

  @And("^user clicks on Deferred Payment link for FDT navigation$")
  public void userClicksOnDeferredPaymentLinkForFDTNavigation() {
      pdp.scrollToElement(pdp.FDT_DEFERRED_PAYMENT_LINK);
      pdp. clickUsingJS(pdp.FDT_DEFERRED_PAYMENT_LINK);
  }

  @Then("^verify PDP product details$")
  public void verifyPDPProductDetails() {
      assertTrue(pdp.isElementDisplayedBySeconds(pdp.ADD_TO_CART_SE,5));
      assertTrue(pdp.isElementDisplayedBySeconds(pdp.QUANTITY_SE,5));
      assertTrue(pdp.isElementDisplayedBySeconds(pdp.SUBSCRIPTION_OPTION_SE,5));
  }

  @Then("^assert personalization options FANTASIES and Text are displayed$")
  public void assertPersonalizationOptionsFANTASIESAndTextAreDisplayed() {
    List<WebElement> lstPatterns = pdp.getWebDriver().findElements(pdp.NEW_PERSONOLIZATION_OPTIONS);
    assertTrue(lstPatterns.get(0).getText().equalsIgnoreCase("FANTASIE"));
    assertTrue(lstPatterns.get(1).getText().equalsIgnoreCase("Testo"));
  }

  @And("^assert library of FANTASIES are visible to the user$")
  public void assertLibraryOfFANTASIESPatternVisibleToTheUser() {
    pdp.waitForItemToBeClickableAndClick(pdp.MOTIFS_MINI_ICON_CUSTOMIZE_OPTION,5);
    assertTrue(pdp.getWebDriver().findElements(pdp.MOTIFS_PERSONOLIZATION_OPTIONS).size()>=0);
  }

  @And("^user selects a FANTASIES pattern and assert selection$")
  public void userSelectsFANTASIESPatternAndAssertSelection() {
    pdp.clickFirstElementByQueryJSExecutor(pdp.MOTIFS_PERSONOLIZATION_OPTIONS);
    assertTrue(pdp.waitForExpectedElement(pdp.MOTIF_PATTERN_SELECTED).isDisplayed());
  }

  @And("^user selects text pattern$")
  public void userSelectsTextPattern() {
    pdp.clickFirstElementByQueryJSExecutor(pdp.PERSONLIZATION_RETURN_LINK);
    pdp.clickUsingJS(pdp.getWebDriver().findElements(pdp.MOTIFS_MINI_ICON_CUSTOMIZE_OPTION).get(1));
  }

  @Then("^assert the direction Of The Text options Vertical and Horizontal are displayed$")
  public void assertTheDirectionOfTheTextOptionsVerticalAndHorizontalAreDisplayed() {
    assertTrue(pdp.waitForExpectedElement(pdp.PERSONLIZATION_TEXT_DIRECTION_VERTICAL_LABEL).isDisplayed());
    assertTrue(pdp.waitForExpectedElement(pdp.PERSONLIZATION_TEXT_DIRECTION_HORIZONTAL_LABEL).isDisplayed());
  }

  @And("^user add \"([^\"]*)\" characters into the field for Vertical direction$")
  public void userAddCharactersIntoTheFieldForVerticalDirection(String number) {
    pdp.addPersonalisationTextIntoTheField(number);
  }

  @And("^user click the field for Horizontal direction$")
  public void userClickTheFieldForHorizontalDirection() {
    pdp.waitForExpectedElement(pdp.PERSONLIZATION_TEXT_DIRECTION_HORIZONTAL_LABEL).click();
  }

  @And("^assert error message is displayed for characters number$")
  public void assertErrorMessageIsDisplayedForCharactersNumber() {
    assertTrue(pdp.isPersonalisationTextErrorDisplayed());
  }

  @And("^user add \"([^\"]*)\" characters into the field for Horizontal direction$")
  public void userAddCharactersIntoTheFieldForHorizontalDirection(String number) {
    pdp.addPersonalisationTextIntoTheField(number);
  }

  @And("^assert error message is not displayed for characters number$")
  public void assertErrorMessageIsNotDisplayedForCharactersNumber() {
    pdp.waitForElementToDisappear(pdp.PERSONLIZATION_TEXT_ERROR_LABEL, 5);
    assertFalse(pdp.isPersonalisationTextErrorDisplayed());
  }

  @Then("^add product to basket success message is displayed$")
  public void addProductToBasketSuccessMessageIsDisplayed() {
    String actualMessage = pdp.getAddToBasketSuccessMessage();
    String expectedMessage = UrlBuilder.getMessage("addProductToBasketMessage.key");
    assertThat(actualMessage.contains(expectedMessage))
            .as("ERROR validate addProductToBasket: expected message was " + expectedMessage + ", but I got " + actualMessage).isTrue();
  }

  @And("^user select the number '(.*)' font$")
  public void userSelectFontRadio(String strIndex){
    pdp.selectFont(strIndex);
  }

  @And("^assert '(.*)' section is display on PDP$")
  public void assertPDPSection(String block){
    pdp.assertArtistCMSBlock(block);
  }

  @And("^click on first buyable product in PDP in Velo site$")
  public void clickOnFirstBuyableProd() {
    pdp.clickOnFirstBuyableProdPDP();
  }

  @Then("^click strength \"([^\"]*)\" on pdp page$")
  public void clickStrengthOnPdpPage(String strengthValue)  {
    pdp.clickStrengthByValue(strengthValue);
  }

  @And("^assert CTA notice me when in stock is display correctly$")
  public void assertCTANoticeMeWhenInStockIsDisplayCorrectly() {
    assertTrue(pdp.isNoticeMeWheninStockDisplayed());
  }

  @Then("^verify Q&A section is present on PDP$")
  public void verifyQASectionISPRESENTPDP() {
      pdp.waitForExpectedElement(pdp.QA_SECTION,10);
      pdp.scrollToElement(pdp.QA_SECTION);
      assertTrue(pdp.isElementDisplayedBySeconds(pdp.QA_SECTION,5));
  }


  @When("^terms and Policy checkbox is not ticked then Send button is not clickable$")
  public void termAndPolicyCheckboxNotCheckedSendButtonNotClickable() {
    assertFalse(pdp.waitForExpectedElement(pdp.ASK_QUESTION_QUESTION_TICKBOX,5).isSelected());
    assertFalse(pdp.waitForExpectedElement(pdp.ASK_QUESTION_QUESTION_SUBMIT,5).isEnabled());
  }

  @Then("^assert error message for Ask a question without filling any details$")
  public void assertErrorMessageForAskAQuestionSubmitWithoutAnyDetails() {
    pdp.assertErrorMessageForAskAQuestionSubmitWithoutAnyDetails();
  }

  @Then("^enter invalid email address and assert error message$")
  public void enterInvalidEmailAssertErrorMessage() {
    pdp.enterInvalidEmailAssertErrorMessage();
  }

  @And("^verify PDP page is open$")
  public void verifyPDPPageIsOpen() {
    assertTrue(pdp.isElementDisplayedBySeconds(searchResult.PDP_DESCRIPTION, 15));
  }

  @And("^assert more explore products displayed$")
  public void assertMoreExploreProductsDisplayed() {
      assertTrue(pdp.moreExploreProductsDisplayed());
  }

  @And("^user clicks on submit button without filling the rating form$")
  public void userSubmitRatingFormWithoutFillingTheFields() {
    pdp.submitRatingForm();
  }

  @Then("^assert the error messages on the rating form$")
  public void assertTheErrorMessagesOnTheRatingForm() {
    assertTrue(pdp.waitForExpectedElement(pdp.REVIEW_STAR_RATING_ERROR_TXT, 5).getText().contains(UrlBuilder.getMessage("ratingErrorMessage.key")));
    assertTrue(pdp.waitForExpectedElement(pdp.REVIEW_NAME_ERROR_TXT, 5).getText().contains(UrlBuilder.getMessage("nameErrorMessage.key")));
    assertTrue(pdp.waitForExpectedElement(pdp.REVIEW_SUMMARY_ERROR_TXT, 5).getText().contains(UrlBuilder.getMessage("summaryErrorMessage.key")));
    assertTrue(pdp.waitForExpectedElement(pdp.REVIEW_COMMENTS_ERROR_TXT, 5).getText().contains(UrlBuilder.getMessage("commentsErrorMessage.key")));
  }


  @Then("^assert Health Warning is displayed$")
  public void assertHealthWarningIsDisplayed() {
    assertTrue(pdp.isElementDisplayedBySeconds(pdp.HEALTH_WARNING_PDP_LYFT, 10));
  }

  @Then("^add review success message is displayed$")
  public void addReviewSuccessMessageIsDisplayed() {
    String actualMessage = pdp.waitForExpectedElement(pdp.ADD_REVIEW_SUCCESS_MSG, 10).getText();;
    String expectedMessage = UrlBuilder.getMessage("addReviewSuccessMessage.key");
    assertThat(actualMessage.contains(expectedMessage))
            .as("ERROR validate addReview: expected message was " + expectedMessage + ", but I got " + actualMessage).isTrue();
  }

  @And("^the newly added item is not in the review list$")
  public void theNewlyAddedItemIsNotInTheReviewList() {
    List<WebElement> reviewList = pdp.waitForExpectedElements(pdp.REVIEW_TITLE_LIST);
    boolean isExist = false;
    for (WebElement webElement : reviewList) {
      if (webElement.getText().contains(pdp.reviewField)) {
        isExist = true;
        break;
      }
    }
    assertFalse("the newly added item should not in the review list$", isExist);
  }

}