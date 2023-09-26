package com.salmon.test.step_definitions.gui.Lyft;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.LogininPage;
import com.salmon.test.page_objects.gui.Lyft.LyftLabPage;
import com.salmon.test.page_objects.gui.PLP;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class lyftLABPageSteps {

    public LyftLabPage lyftLabPage;
    public LogininPage logininPage;
    public PLP plp;
    public HomePage homePage;

    public lyftLABPageSteps(LyftLabPage lyftLabPage, LogininPage logininPage,PLP plp,HomePage homePage) {
        this.lyftLabPage = lyftLabPage;
        this.logininPage=logininPage;
        this.plp=plp;
        this.homePage=homePage;
    }

    @And("user clicks on LYFT/LAB link from the header menu on Lyft site$")
    public void userClicksOnLyftLabLinkFromHeaderMenuLyftSite() {
        lyftLabPage.clickLyftLabLinkFromHeaderMenu();
    }

    @Then("user navigates to Lyft LAB landing page$")
    public void userNavigatesToLyftLabLandingPage() {
        lyftLabPage.navigateToLyftLabLandingPage();
    }

    @And("user clicks on LYFT/LAB link from the Burger menu on Lyft site$")
    public void userClicksOnLyftLabLinkFromBurgerMenuLyftSiteMobile() {
        lyftLabPage.clickOnLyftLabLinkFromMobileBurgerMenu();
    }

    @And("user clicks on LYFT link from the Burger menu on Lyft site$")
    public void userClicksOnLyftLinkFromBurgerMenuLyftSiteMobile() {
        lyftLabPage.clickOnLyftLinkFromMobileBurgerMenu();
    }

    @And("user navigates to Lyft LAB landing page on Mobile$")
    public void userNavigatesToLyftLabLandingPageOnMobile() {
        lyftLabPage.waitForExpectedElement(lyftLabPage.HEADER_LYFTLAB_LOGO).isDisplayed();
    }

    @Then("user clicks on See The Flavors link from Block 1$")
    public void userClicksOnSeeTheFlavorsLink() {
        lyftLabPage.clickOnSeeTheFlavorsLink();
    }

    @Then("verify user is directed to Lab Products Collection on the same page$")
    public void verifyUserDirectedToLabProductCollectionOnTheSamePage() {
        lyftLabPage.waitForExpectedElement(lyftLabPage.LAB_PRODUCTS_GRID).getAttribute("class").equals(lyftLabPage.getWebDriver().switchTo().activeElement().getAttribute("class"));
    }

    @And("user navigates to Collaboration landing page$")
    public void userNavigatesToCollaborationLandingPage() {
        try{
            assertTrue(lyftLabPage.waitForExpectedElement(lyftLabPage.SHOW_COLLABORATION_PGE).isDisplayed());}
        catch(Exception ex){
            assertTrue(lyftLabPage.waitForExpectedElement(lyftLabPage.SHOW_COLLABORATION_NEWCOLLECTION_PGE).isDisplayed());
        }
    }

    @And("navigate to LAB Collection PLP on the Lyft Lab home page$")
    public void navigateToLabCollectionPLPOnLyftLabHomePage() {
        lyftLabPage.navigateToLabPLPOnLyftLabHomePage();
    }

    @Then("user clicks on See The Flavors link for Collection 3$")
    public void userClicksOnSeeTheFlavorsLinkForCollection3() {
        lyftLabPage.clickOnSeeTheFlavorsLinkForCollection3();
    }

    @Then("^assert links under LAB Category header$")
    public void assertLinksUnderLABCategoryHeader() {
        lyftLabPage.assertLinksUnderLABCategoryHeader();
    }

    @And("^user hovers over '(.*)' category and assert sub-category$")
    public void userHoversOverCollectionCategoryAndAssertSubCategory(String strExpectedText) {
        lyftLabPage.hoverOverCollectionCategoryAndAssertSubCategory(strExpectedText);
    }

    @And("^user clicks '(.*)' category and assert sub-category on Mobile$")
    public void userClicksCollectionCategoryAndAssertSubCategoryOnMobile(String strExpectedText) {
        lyftLabPage.clickCollectionCategoryAndAssertSubCategory(strExpectedText);
    }

    @Then ("^user navigates to Insights landing page$")
    public void userNavigatesToInsightsLandingPage() {
        lyftLabPage.navigateToInsightsLandingPage();
    }

    @Then ("user clicks on the Burger menu on Lyft LAB site$")
    public void userClicksOnBurgerMenuOnLyftLabSite() {
        lyftLabPage.clickOnBurgerMenuOnLyftLabSite();
    }

    @And ("assert background color '(.*)' of the footer section$")
    public void assertBackgroundColorOfTheLyftFooterSection(String strBgColor) {
        lyftLabPage.assertBackgroundColorOfTheLyftFooterSection(strBgColor);
    }

    @And ("assert text color '(.*)' for Lyft Footer Links$")
    public void assertTextColorForLyftFooterLinks(String strTextColor) {
        lyftLabPage.assertTextColorForLyftFooterLinks(strTextColor);
    }

    @And ("assert Lyft LAB Logo in the Footer section$")
    public void assertLyftLABLogoInFooterSection() {
        lyftLabPage.assertLyftLABLogoInFooterSection();
    }

    @And ("assert Lyft Logo in the Footer section$")
    public void assertLyftLogoInFooterSection() {
        lyftLabPage.assertLyftLogoInFooterSection();
    }

    @And ("user clicks on Lyft LAB logo in the header$")
    public void userClicksOnTheLyftLabLogoInHeader() {
        lyftLabPage.clickOnTheLyftLabLogoInHeader();
    }

    @And ("user clicks on Lyft logo in the header$")
    public void userClicksOnTheLyftLogoInHeader() {
        lyftLabPage.clickOnTheLyftLogoInHeader();
    }

    @Then("^user clicks on the currently active collection link from Collections header$")
    public void userClicksOnCurrentlyActiveCollectionLinkFromCollectionsHeader() {
        lyftLabPage.clickOnCurrentlyActiveCollectionLinkFromCollections();
    }

    @And("assert single pack products on LAB PLP page$")
    public void assertSinglePackProductsOnLabPLP() {
        lyftLabPage.assertSinglePackProductsOnLabPLP();
    }

    @And("assert bundle pack products on LAB PLP page$")
    public void assertBundlePackProductsOnLabPLP() {
        lyftLabPage.assertBundlePackProductsOnLabPLP();
    }

    @And("user clicks active collection link from Collections Category$")
    public void userClicksActiveCollectionLinkFromCollectionsCategory() {
        lyftLabPage.clickActiveCollectionLinkFromCollectionsCategory();
    }

    @And("^verify Lyft Lab logo is displayed$")
    public void verifyLyftLabLogoIsDisplayed() throws Throwable {
        lyftLabPage.assertLyftLabLogoIsDisplayed();
    }

    @And("^verify Lyft logo is displayed$")
    public void verifyLyftLogoIsDisplayed() {
        lyftLabPage.assertLyftLogoIsDisplayed();
    }

    @And("^verify Lyft logo on navigating links$")
    public void verifyLyftLogoOnNavigatingLinksIsDisplayed() {
        lyftLabPage.clickOnLinkAndVeriFyLogo();
    }

    @And("^user select New collection$")
    public void userSelectNewCollection() {
        lyftLabPage.userSelectNewCollection();
    }

    @And("^user select Previous collection$")
    public void userSelectPreviousCollection() {
        lyftLabPage.userSelectPreviousCollection();
    }

    @And("verify count of Inspired By with respect to displayed products$")
    public void assertCountOfInspiredBy() {
        assertTrue(lyftLabPage.countOfWebelement(LyftLabPage.LYFT_LAB_PRODUCT_LINK)==lyftLabPage.countOfWebelement(LyftLabPage.LYFT_LAB_INSPIRED_BY));
    }

    @And("click first Lyft Lab 3 pack product$")
    public void clickFirstLyftLabThreePackProduct() {
        lyftLabPage.clickFirstLyftLabThreePackProduct();
    }

    @And("click first Lyft Lab 6 pack product$")
    public void clickFirstLyftLabSixPackProduct() {
        lyftLabPage.clickFirstLyftLabSixPackProduct();
    }

    @Then("^assert Insights Greeting block for logged-in user at the top$")
    public void assertInsightsGreetingBlockForLoggedUserAtTop(){
        while(!lyftLabPage.isElementDisplayedBySeconds(lyftLabPage.USER_INSIGHTS_GREETING_BLOCK,30))
        {
            lyftLabPage.waitForExpectedElement(lyftLabPage.USER_INSIGHTS_GREETING_BLOCK,30);
        }
        assertTrue(lyftLabPage.waitForExpectedElement(lyftLabPage.USER_INSIGHTS_GREETING_BLOCK,30).isDisplayed());
    }

    @Then("^assert CTA for See My Rewards link$")
    public void assertCTAForSeeMyRewardsLink(){
        lyftLabPage.clickSeeMyRewardsLink();
    }

    @And("^assert My Opinion block$")
    public void assertMyOpinionBlock() {
        String MyOpinionBlockHeading = lyftLabPage.waitForExpectedElement(lyftLabPage.MY_OPINION_BLOCK_HEADER).getText();
        assertTrue(lyftLabPage.waitForExpectedElement(lyftLabPage.INSIGHTS_MY_OPINION_BLOCK).isDisplayed());
        assertTrue(MyOpinionBlockHeading.contains("MIN") && MyOpinionBlockHeading.contains("ÅSIKT"));
        assertTrue(lyftLabPage.waitForExpectedElement(By.linkText(UrlBuilder.getMessage("chooseFirstProductBeforeSurvey.key"))).isDisplayed());
    }

    @And("assert CTA for Choose your First Taste link$")
    public void assertCTAForChooseYourFirstTasteLink() {
        lyftLabPage.clickByElementByQueryJSExecutor(lyftLabPage.CHOOSE_FIRST_TASTE_LINK);
        assertTrue(lyftLabPage.waitForURLToContain("se/sv/lab-edt-"));
    }

    @And("assert My Rewards section for active collection with inactive badges$")
    public void assertMyRewardsSectionForActiveCollectionWithInactiveBadges() {
        while (!lyftLabPage.isElementDisplayedBySeconds(lyftLabPage.CURRENT_COLLECTION_MY_REWARDS_EXPANDED,30))
        {
            lyftLabPage.waitForExpectedElement(lyftLabPage.CURRENT_COLLECTION_MY_REWARDS_EXPANDED,30);
        }
        assertTrue(lyftLabPage.waitForExpectedElement(lyftLabPage.CURRENT_COLLECTION_MY_REWARDS_EXPANDED).isDisplayed());
        assertTrue(lyftLabPage.waitForExpectedElement(lyftLabPage.CURRENT_COLLECTION_INACTIVE_BADGES).isDisplayed());
    }

    @And("assert previous collection badge titles appears as per collection names$")
    public void assertPreviousCollectionBadgeTitlesAsPerCollectionName() {
        lyftLabPage.assertPreviousCollectionBadgeTitlesAsPerCollectionName();
    }

    @When("user clicks on Previous collection tiles and assert badges for each$")
    public void userClicksOnPreviousCollectionTilesAndAssertBadgesForEach() {
        lyftLabPage.clickOnPreviousCollectionTilesAndAssertBadgesForEach();
    }

    @Then("^assert Insights Greeting block is not displayed for guest user$")
    public void assertInsightsGreetingBlockNotDisplayedForGuestUser(){
        assertTrue(lyftLabPage.getWebDriver().findElements(lyftLabPage.USER_INSIGHTS_GREETING_BLOCK).size()==0);
    }

    @And("^assert login to lab section is displayed for guest user$")
    public void assertLoginToLabSectionIsDisplayedForGuestUser(){
        assertTrue(lyftLabPage.waitForExpectedElement(lyftLabPage.LOGIN_TO_LAB_LINK,10).isDisplayed());
        lyftLabPage.clickByElementByQueryJSExecutor(lyftLabPage.LOGIN_TO_LAB_LINK);
        assertTrue(lyftLabPage.waitForExpectedElement(logininPage.popupLoginEmail,4).isDisplayed());
    }

    @And("assert text of buy button on basis of login rule applied for guest user$")
    public void assertTextOfBuyButtonOnBasisOfLoginRuleAppliedForGuestUser() {
        lyftLabPage.assertTextOfBuyButtonOnBasisOfLoginRuleAppliedForGuestUser();
    }

    @And("user logs in from PLP when login rule is enabled$")
    public void userLogsInFromPLPWhenLoginRuleIsEnabled() {
        lyftLabPage.userLogsInFromPLPWhenLoginRuleIsEnabled();
    }

    @And("user clicks on Login or Register to Buy from PLP when login rule is enabled$")
    public void userClicksOnLoginOrRegisterToBuyButtonFromPLPWhenLoginRuleIsEnabled() {
        lyftLabPage.clickOnLoginOrRegisterToBuyButtonFromPLPWhenLoginRuleIsEnabled();
    }

    @And("user clicks on Login or Register to Buy from previous collection when login rule is enabled$")
    public void userClicksOnLoginOrRegisterToBuyButtonFromPreviousCollectionWhenLoginRuleIsEnabled() {
        lyftLabPage.clickOnLoginOrRegisterToBuyButtonFromPreviousCollectionWhenLoginRuleIsEnabled();
    }

    @And("user clicks on Add To Cart button to Buy from PLP when login rule is disabled$")
    public void userClicksOnAddToCartButtonFromPLPWhenLoginRuleIsDisabled() throws Throwable {
        lyftLabPage.clickOnAddToCartButtonFromPLPWhenLoginRuleIsDisabled();
        if (lyftLabPage.getWebDriver().findElements(lyftLabPage.ADD_TO_CART_BUTTON_LAB).size() > 0) {
            homePage.confirmMiniBasketDisplayedAmountOf("1");
        }
    }

    @And("^assert text of '(.*)' is displayed on lyft cart page$")
    public void assertTextOfRegisteredCustomersIsDisplayed(String expectedText) {
        assertTrue(homePage.getTotalTaxOnLyftSE().contains(UrlBuilder.getMessage(expectedText)));
    }

    @And("^assert all Surveys for Brand, Innovation and Research are displayed$")
    public void assertAllSurveysForBrandInnovationAndResearchAreDisplayed() {
        lyftLabPage.assertAllSurveysForBrandInnovationAndResearchAreDisplayed();
    }

    @And("^assert Survey rules appear in no specific sequence for active collection$")
    public void assertSurveyRulesAppearInNoSpecificSequenceForActiveCollection() {
        lyftLabPage.assertSurveyRulesAppearInNoSpecificSequenceForActiveCollection();
    }

    @And("^assert strength for each product is pre-selected for Collection7$")
    public void assertStrengthForEachProductIsPreSelectedForCollection7() {
        lyftLabPage.assertStrengthForEachProductIsPreSelectedForCollection7();
    }

    @And("^assert strength for each product is pre-selected for Collection7 on PDP$")
    public void assertStrengthForEachProductIsPreSelectedForCollection7onPDP() {
        lyftLabPage.assertStrengthForEachProductIsPreSelectedForCollection7onPDP();
    }
}
