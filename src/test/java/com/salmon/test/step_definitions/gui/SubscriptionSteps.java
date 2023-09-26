package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.LogininPage;
import com.salmon.test.page_objects.gui.SubscriptionsPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class SubscriptionSteps extends PageObject {

    private SubscriptionsPage subscriptionsPage;
    private HomePage homepage;
    private LogininPage loginPage;
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionSteps.class);

    public SubscriptionSteps(SubscriptionsPage subscriptionsPage, HomePage homepage, LogininPage loginPage) {
        this.subscriptionsPage = subscriptionsPage;
        this.homepage = homepage;
        this.loginPage = loginPage;
    }

  @And("^wait for the page to fully load$")
  public void assertPageisLoaded() {
    subscriptionsPage.isPageLoaded();
  }

  @And("^ensure the expected page assets are loaded$")
  public void ensureTheExpectedPageAssetsAreLoaded() throws Throwable {
    assertTrue("\n ****** ERROR ********* \n\nKey expected page elements are not loaded.",subscriptionsPage.arePageElementsPresentAsExpected());
  }

  @And("^ensure the step 1 device button is displayed$")
  public void ensureTheStep1DeviceButtonIsDisplayed() {
    assertTrue("Subs step 1 button is displayed as expected",subscriptionsPage.step1ButtonDisplayed());
  }

  @And("^ensure the step 2 device button is displayed$")
  public void ensureTheStep2DeviceButtonIsDisplayed() {
    assertTrue("Subs step 2 button is displayed as expected",subscriptionsPage.step2ButtonDisplayed());
  }

  @And("^ensure the step 3 device button is displayed$")
  public void ensureTheStep3DeviceButtonIsDisplayed() {
    assertTrue("Subs step 3 button is displayed as expected",subscriptionsPage.step3ButtonDisplayed());
  }

  @And("^ensure the start building my subscriptions is displayed$")
  public void ensureStartBuildingMySubsButtonIsDisplayed() {
    assertTrue("Subs start building my subs button is displayed as expected",subscriptionsPage.startMySubsButtonDisplayed());
  }

  @And("^is subs main content block displayed$")
  public void isSubsMainContentBlockDisplayed() {
    assertTrue("Main content block isn't displayed",subscriptionsPage.isMainSubsDivPresent());
  }

  @And("^assert h1 title is correct$")
  public void assertHTitleIsCorrect() {
    assertTrue("H1 title not as expected",subscriptionsPage.geth1Title().equals("Subscriptions"));
  }

  @And("^users clicks start building my subscription button on the page footer$")
  public void usersClicksStartBuildingMySubscriptionButtonOnThePageFooter() {
    subscriptionsPage.pressStartBuildingMySubscriptionButton();
  }

  @And("^select first subscription product$")
  public void selectFirstSubscriptionProduct() throws Throwable {
    Thread.sleep(4000);
    subscriptionsPage.selectFirstSubColour();
    subscriptionsPage.selectFirstSubProduct();
    subscriptionsPage.selectContinue();
  }

  @And("^user continues to step 2$")
  public void userContinuesToSummaryPage() throws Throwable {
    Thread.sleep(4000);
    subscriptionsPage.pressProceedToStep2Button();
  }

  @And("^choose contract and flavours$")
  public void chooseContractAndFlavours() throws Throwable {
    Thread.sleep(2000);
    int counter = 0;
    while (counter < 6){
      counter ++;
      subscriptionsPage.addFlavToOrder();
      Thread.sleep(500);
      LOG.info("COUNTER : " + counter);
    }
  }

  @And("^user continues to step 3$")
  public void userContinuesToStep() {
    subscriptionsPage.pressProceedToStep3Button();
  }

  @And("^assert Savings table is displayed$")
  public void assertSavingsTableIsDisplayed() throws Throwable {
    Thread.sleep(7000);
    By summaryTable = By.cssSelector(".summary-item table");
    WebElement summaryTableElement = subscriptionsPage.getWebDriver().findElement(summaryTable);

    for(int i=0; i<=2;i++){
      try{
        LOG.info("Table is displayed : " + subscriptionsPage.getWebDriver().findElement(summaryTable).isDisplayed());

        for(WebElement rowData : subscriptionsPage.getWebDriver().findElement(summaryTable).findElements(By.tagName("th"))){//subscriptionsPage.waitForExpectedElement(By.cssSelector(".summary-item table")).findElements(By.tagName("th"))){
          LOG.info("\n SUBS HEADING DATA : " + rowData.getText());
        }
      }
      catch(Exception e){
        LOG.info("\n STALE ELEMENT EXCEPTION");
      }
    }
    //Setting up arrayList outside of Loop
    SubscriptionsPage.SubsModel.savingsTableData = new ArrayList<>();
    Boolean arrayFullyPopulated = false;
    int counter = 0;
    for(int i=0; i<=2;i++){
      try{
        for(WebElement rowData : subscriptionsPage.getWebDriver().findElement(summaryTable).findElements(By.tagName("td"))){//subscriptionsPage.waitForExpectedElement(By.cssSelector(".summary-item table")).findElements(By.tagName("th"))){
            SubscriptionsPage.SubsModel.savingsTableData.add(rowData.getText());
          if (SubscriptionsPage.SubsModel.savingsTableData.size() == 15){
            LOG.info("Subs table is expected full size - breaking off someThang");
            break;
          }
        }
      }
      catch(Exception e){
        SubscriptionsPage.SubsModel.savingsTableData.clear();
        LOG.info("\n STALE ELEMENT EXCEPTION");
      }
    }
    for (String ArrayEle : SubscriptionsPage.SubsModel.savingsTableData){
      LOG.info("Array Entry : " + ArrayEle);
    }
    LOG.info("\n ********** Array SIZE : " + SubscriptionsPage.SubsModel.savingsTableData.size());
  }

  @And("^scrape page for all tables$")
  public void scrapePageForAllTables() {
    subscriptionsPage.getAllTables();
  }

  @And("^click add to basket$")
  public void clickAddToBasket() {
    subscriptionsPage.clickAddToBasket();
  }

  @And("^click on checkout button$")
  public void clickOnCheckoutButton() throws Throwable {
    Thread.sleep(4000);
    subscriptionsPage.clickCheckoutButton();
  }

  @And("^click subscriptions continue button$")
  public void clickSubscriptionsContinueButton() {
    try {
      subscriptionsPage.waitForExpectedElement(By.cssSelector("button.action.pagebuilder-button-primary.desktop-contniue"),10).click();
    } catch (Exception e) {
      subscriptionsPage.waitForExpectedElement(By.cssSelector("button.action.pagebuilder-button-secondary.desktop-contniue"),10).click();
    }
  }

  @And("^user selects flavours for subscription$")
  public void userSelectsFlavoursForSubscription() {
    subscriptionsPage.waitForPage();
    selectFirstSubscriptionFlavourAndQty();
    subscriptionsPage.addFlavToOrder();
    subscriptionsPage.clickContinueButton();
 }

  private void selectFirstSubscriptionFlavourAndQty() {
    subscriptionsPage.clickByElementByQueryJSExecutor(By.cssSelector("div.swatch-option.text"));
    subscriptionsPage.clickByElementByQueryJSExecutor(By.cssSelector(".input-text.qty"));
    setSubscriptionAmountTo("6");
  }

  private void setSubscriptionAmountTo(String amountToEnter) {
    subscriptionsPage.waitForExpectedElement(By.cssSelector("#qty")).clear();
    subscriptionsPage.waitForExpectedElement(By.cssSelector("#qty")).sendKeys(amountToEnter);
  }

  @And("^click agree to terms and conditions$")
  public void clickAgreeToTermsAndConditions() {
    subscriptionsPage.clickUsingJS(By.cssSelector("#summary_checkbox"));
  }

  @When("^user clicks on Get Flavours CTA$")
  public void userClicksOnGetFlavoursCTA() {
    subscriptionsPage.clickGloSubGetFlavours();
  }

  @When("^user clicks on the Subscribe and save Banner for a product$")
  public void userClicksOnTheSubscribeAndSaveBanner() {
    subscriptionsPage.clickGloSubPLPCTABanner();
  }

  @And("^user selects the subscriptions as in table and add to basket then assert added message$")
  public void userSelectsSubscriptionsAddToBasketThenAssertAddedMessage(List<Map<String, String>> mapList) {
      mapList.forEach(map -> {
          clickSubsLinkSelectProductFlavourAndQty(map);
          clickSubContinueButton();
          clickAgreeToTermsAndConditions();
          clickAddToBasket();
          assertSubscriptionAddedToBasketMsg();
      });
  }

    @When("^user selects the subscriptions as$")
    public void userSelectsTheSubscriptionsAs(List<Map<String, String>> mapList) {
        mapList.forEach(this::clickSubsLinkSelectProductFlavourAndQty);
    }

    private void clickSubsLinkSelectProductFlavourAndQty(Map<String, String> map) {
        homepage.usersClicksOnTheLinkByText("subscriptionsHeaderLinkText.key");
        clickSubscriptionsContinueButton();
        selectSubscriptionProductWithName(map.get("SubscriptionProduct"));
        clickSubContinueButton();
        userSelectCommitmentWithAmount(map.get("NoOfMonths"), map.get("NoOfPacks"));
        selectFlavourAndQuantity(map.get("Flavour"), map.get("Quantity"));
    }

    @And("^selects subscription product with name \"([^\"]*)\"$")
    public void selectSubscriptionProductWithName(String proName) {
        String productName = getInput(proName);
        subscriptionsPage.selectProductColourAndClickSelectButtonFor(productName);
    }

    @And("^user selects \"([^\"]*)\" commitment with \"([^\"]*)\" amount$")
    public void userSelectCommitmentWithAmount(String noOfMonths, String noOfPacks) {
        String commitment = getInput(noOfMonths), commitmentAmount = getInput(noOfPacks);
        subscriptionsPage.selectCommitmentAndCommitmentAmount(commitment, commitmentAmount);
    }

    @And("^user selects flavour \"([^\"]*)\" and quantity as \"([^\"]*)\"$")
    public void userSelectsFlavourAndQuantityAs(String flavour, String quantity) {
        subscriptionsPage.selectFlavourAndQuantity(getInput(flavour), getInput(quantity));
    }

    @And("^clicks continue button$")
    public void clickSubContinueButton() {
        subscriptionsPage.selectContinue();
    }

    @And("^click on subscription pause button$")
    public void clickOnSubscriptionPauseButton() {
        subscriptionsPage.clickPauseLink();
    }

    @Then("^assert user gets \"([^\"]*)\" percent discount$")
    public void assertUserGetsPercentDiscount(String expectedDiscountPercentage) {
        WebElement summaryTableElement = subscriptionsPage.getSubsSummaryTable();
        List<WebElement> tr = summaryTableElement.findElements(By.tagName("tr"));

        List<String> subscriptionPricePerMonthList = tr.stream()
                .filter(webElement -> webElement.getText().contains("MONTH"))
                .map(webElement -> webElement.findElement(By.cssSelector("td:nth-child(3)")))
                .map(WebElement::getText)
                .collect(Collectors.toList());
        assertThat(subscriptionPricePerMonthList.stream().distinct().count())
                .as("Expected the price for every month is same but they are not same :" + subscriptionPricePerMonthList.toString())
                .isEqualTo(1);

        WebElement totalCostRow = tr.stream()
                .filter(webElement -> webElement.getText().contains("TOTAL COST")).findFirst().get();

        double totalCostIfSoldSeparately = Double.parseDouble(totalCostRow.findElement(By.cssSelector("td:nth-child(2)"))
                .getText().replaceAll("[^0-9]", ""));
        double totalCostWithSubscription = Double.parseDouble(totalCostRow.findElement(By.cssSelector("td:nth-child(3)"))
                .getText().replaceAll("[^0-9]", ""));
        double actualPercentage = ((totalCostIfSoldSeparately - totalCostWithSubscription) / totalCostIfSoldSeparately) * 100;

//        ToDo: uncomment the following line once the bug 392984 fixed
//    assertThat(Math.floor(actualPercentage))
//            .as("Discount is not as expected, actual discount is :" + Math.floor(actualPercentage) + " and expected is :" + Double.parseDouble(expectedDiscountPercentage))
//            .isEqualTo(Double.parseDouble(expectedDiscountPercentage));
    }

    private void assertSubscriptionAddedToBasketMsg() {
        String actualText = subscriptionsPage.getSuccessMsgText();
        assertThat(actualText).contains(getInput("SubscriptionBundleAddedToCartMsg.key"));
    }

    private void selectFlavourAndQuantity(String flavour, String qty) {
        List<String> flavours = Stream.of(flavour.split(",")).collect(Collectors.toList());
        List<String> quantity = Stream.of(qty.split(",")).collect(Collectors.toList());
        for (int i = 0; i < flavours.size(); i++) {
            for(int j=0; j < quantity.size(); j++) {
                if(i == j)
                    userSelectsFlavourAndQuantityAs(getInput(flavours.get(i)), getInput(quantity.get(i)));
            }
        }
    }

    private String getInput(String value) {
        return value.contains(".key") ? UrlBuilder.getMessage(value) : value;
    }

    @Then("^assert error pop up is displayed and message \"([^\"]*)\"$")
    public void assertErrorPopUpIsDisplayedAndMessage(String expected) {
        assertThat(subscriptionsPage.isSubsErrorPopUpDisplayed()).isTrue();
        String messageDisplayedOnSubsErrorPopup = subscriptionsPage.getMessageDisplayedOnSubsErrorPopup();
        assertThat(messageDisplayedOnSubsErrorPopup).contains(getInput(expected));
    }

    @And("^click ok button on subs error pop up$")
    public void clickOkButtonOnSubsErrorPopUp() {
        subscriptionsPage.clickButtonOnSubsErrorPopUp();
    }

    @And("^assert correct page is landing when usr click on select flavors$")
    public void assertCorrectPageIsDisplayedOnClickingSelectFlavors(DataTable table) {
        List<List<String>> flavors = table.raw();
        switch (UrlBuilder.getLocale()) {
            case "vuseit" :
                for (int i = 0; i <= flavors.size() - 1; i++) {
                    scrollElementIntoView(subscriptionsPage.SELECT_FLAVORS_BUTTON_VUSEIT);
                    String linkHeader = getWebDriver().findElements(subscriptionsPage.SELECT_FLAVORS_BUTTON_VUSEIT).get(i).getText();
                    jsScrollElementInCenter(waitForExpectedElement(subscriptionsPage.SELECT_FLAVORS_BUTTON_VUSEIT));
                    waitForExpectedElements(subscriptionsPage.SELECT_FLAVORS_BUTTON_VUSEIT).get(i).click();
                    waitForPage();
                    LOG.info("the url name is " + linkHeader);
                    if (linkHeader.contains("EPEN"))
                        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("liquidi-sigaretta-elettronica"));
                    else
                        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("vuse-epod-2-liquidi-sigaretta-elettronica"));
                    waitForExpectedElement(By.cssSelector("body > div.page-wrapper > header > nav > div.custom-categories > div > div > ul > li.level0.category-item.icon-subscriptions.third > a:nth-child(2)")).click();

                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                }
                break;
            default:
                for (int i = 0; i <= flavors.size() - 1; i++) {
                    scrollElementIntoView(subscriptionsPage.SELECT_FLAVORS_BUTTON);
                    String linkHeader = getWebDriver().findElements(subscriptionsPage.SELECT_FLAVORS_HEADER).get(i).getText();
                    if (linkHeader.contains("Capsules")) {
                        linkHeader = linkHeader.replace("Capsules", "").toLowerCase().trim();
                    }
                    jsScrollElementInCenter(waitForExpectedElement(subscriptionsPage.SELECT_FLAVORS_BUTTON));
                    elementToBeClickable(waitForExpectedElements(subscriptionsPage.SELECT_FLAVORS_BUTTON).get(i)).click();
                    waitForPage();
                    LOG.info("the url name is " + linkHeader);
                    if (linkHeader.startsWith("Flacon"))
                        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("etank"));
                    else Assert.assertTrue(getWebDriver().getCurrentUrl().contains(linkHeader));
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("GlobalSubsPageLink.key").toUpperCase())).click();
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                }
        }
    }

    @Then("^assert description for vuse subscription$")
    public void assertSubscriptionDescription() {
        switch (UrlBuilder.getLocale()) {
            case "vuseit" :
                assertTrue(isElementDisplayedBySeconds(SubscriptionsPage.SUBSCRIPTION_DESCRIPTION_VUSEIT,5));
                break;
            default:
                assertTrue(isElementDisplayedBySeconds(SubscriptionsPage.SUBSCRIPTION_DESCRIPTION,5));
        }
    }
}