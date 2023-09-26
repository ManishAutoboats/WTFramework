package com.salmon.test.page_objects.gui.Lyft;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.PDP;
import com.salmon.test.page_objects.gui.PLP;
import com.salmon.test.page_objects.gui.constants.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import java.util.List;

import static org.testng.Assert.*;

public class LyftLabPage extends PageObject {

    private PLP plp;
    public LyftLabPage(PLP plp) {
        this.plp = plp;
    }

    public final static By SEE_ALL_FLAVOURS_LINK_LYFTSE = By.cssSelector("a.pagebuilder-button-secondary");
    public final static By SEE_ALL_FLAVOURS_LINK = By.cssSelector("a.pagebuilder-button-primary");
    private final static By SEE_ALL_FLAVOURS_NEW_COLLECTION = By.xpath("//h1//following::a[contains(@href,'collection')][1]/span");
    private final static By SEE_ALL_FLAVOURS_COLLECTION_3 = By.xpath("//a[@href='#lyft-lab-products']");
    private final static By SEE_ALL_FLAVOURS_EDT1 = By.cssSelector("div:nth-child(4) div:nth-child(1) > a.pagebuilder-button-primary");
    public final static By SHOW_COLLABORATION_PGE = By.cssSelector("div.pagebuilder-column.lyft-lab-rectangle-target-element.collection:nth-child(2)");
    public final static By SHOW_COLLABORATION_NEWCOLLECTION_PGE = By.cssSelector("div.widget.block.block-static-block div.edt-02-video-rhs div.pagebuilder-column-group > div:nth-child(2)");
    private final static By LYFTLAB_DESKTOP_LINK = By.cssSelector("li.lyft-lab-link > a.top-link");
    private final static By LYFTLAB_BURGER_MENU = By.cssSelector("span.action.nav-toogle:nth-child(1) > img:nth-child(1)");
    private final static By LYFTLAB_MOBILE_LINK = By.cssSelector("li.burger-menu-item.lyft-lab-mobile > a:nth-child(1)");
    private final static By HEADER_LYFT_LOGO = By.cssSelector("div.row div.column.logo-container:nth-child(2) > a.logo");
    public final static By HEADER_LYFTLAB_LOGO = By.cssSelector("div.row div.column.logo-container:nth-child(2) > a.lab-logo");
    private final static By LYFT_FOOTER_SECTION = By.cssSelector("div.footer.content");
    private final static By FOOTER_LYFT_LOGO = By.cssSelector("div.column-1.footer-desktop-logo.desktop-only > a.logo");
    private final static By LYFT_LOGO_IMAGE = By.cssSelector("div.column-1.footer-desktop-logo.desktop-only a.logo > img:nth-child(1)");
    private final static By FOOTER_LYFTLAB_LOGO = By.cssSelector("div.column-1.footer-desktop-logo.desktop-only a.f-lab-logo");
    private final static By LYFTLAB_LOGO_IMAGE = By.cssSelector("div.column-1.footer-desktop-logo.desktop-only a.f-lab-logo > img:nth-child(1)");
    private final static By LYFT_FOOTER_LINKS = By.xpath("//div[@class='row footer main-content'][2]//li//a");
    private final static By FOOTER_LYFT_CONTENT = By.cssSelector("footer.page-footer");
    private final static By COLLECTIONS_CATEGORY_LINK = By.cssSelector("li.lab-links>a");
    private final static By CATEGORY_NEW_BADGE = By.cssSelector("li.lab-links > span.badge");
    private final static By MOBILE_COLLECTIONS_CATEGORY = By.cssSelector("div.sub-level > div.burger-menu-item");
    private final static By MOBILE_CATEGORY_NEW_BADGE = By.cssSelector("div.sub-level > div.burger-menu-item > span.badge");
    private final static By INSIGHTS_LANDING_PAGE = By.xpath("//div[contains(@class,'insight-page-top-section')]");
    private final static By LYFTLAB_FAQEXPAND_Button = By.cssSelector("i.expand.material-icons");
    public final static By LAB_PRODUCTS_GRID = By.cssSelector("div.products-grid.grid > ol.product-items.widget-product-grid");
    public final static By SWITCH_LANGUAGE_LINK_LYFTDK = By.cssSelector("div.actions.dropdown.options.switcher-options.active ul.dropdown.switcher-dropdown li.view-lyft_dk_en_dk.switcher-option > a:nth-child(1)");
    private final static By CURRENT_ACTIVE_COLLECTION_LINK = By.xpath("//span[@class='badge']//preceding::li[@class='lab-links']/a");
    private static final By LYFT_LAB_LOGO = By.xpath("(//footer[@class='page-footer']//img[contains(@data-src,'lab_logo')])[2]");
    private static final By LYFT_LOGO = By.xpath("(//footer[@class='page-footer']//img[contains(@data-src,'lyft_dk')])[2]");
    private static final By LYFT_TAB = By.xpath("//li[@class='Lyft-link']/a");
    private final static By THE_COLLECTION_LINK = By.cssSelector("li.lab-category>a");
    private static final By SEARCH_ICON = By.xpath("//i[contains(text(),'search')]");
    private static final By BLOG_LINK = By.xpath("//div[@class='header-content']//a[contains(@href,'blog')]");
    private static final By ABOUT_US_LINK = By.xpath("//div[@class='header-content']//a[contains(@href,'about-us')]");
    private static final By SHOP_LINK = By.xpath("//div[@class='header-content']//a[contains(@href,'lyft-tin')]");
    private static final By POPUP_CLOSE_BUTTON = By.cssSelector("aside.modal-popup.popup-authentication.modal-slide._inner-scroll._show:nth-child(2) header.modal-header > button.action-close");

    public static final By USER_INSIGHTS_GREETING_BLOCK = By.cssSelector("div.insight-page-top-section.loggedIn-user-greeting.loggedin-user-insight.show-cms-block");
    private static final By SEE_MY_REWARDS_LINK = By.cssSelector("[href='#my-achievements'] > span");
    private static final By BADGES_ACTIVE_COLLECTION = By.cssSelector("div.collapsibleContent.active-first-content");
    public static final By INSIGHTS_MY_OPINION_BLOCK= By.cssSelector("div.state-v1.insight-buysomething.show-cms-block");
    public static final By CHOOSE_FIRST_TASTE_LINK= By.cssSelector("div.insight-buyproduct-btn.show-cms-block>a");
    public static final By CURRENT_COLLECTION_MY_REWARDS_EXPANDED= By.cssSelector("div.collapsibleContent.active-first-content");
    public static final By CURRENT_COLLECTION_INACTIVE_BADGES= By.cssSelector("div.collapsibleContent.active-first-content:nth-child(2) div.badgeContainer div.badgeCardContainer > div.badgeCards.inactive:nth-child(1)");
    private static final By PREVIOUS_COLLECTIONS_TILES = By.cssSelector("[class='collapsibleTab'][aria-expanded='false'] > div > span");
    private static final By PREVIOUS_COLLECTIONS_INACTIVE_BADGES = By.xpath("//div[@class='collapsibleContent']//following::div[@class='badgeCards inactive'][1]");
    public static final By LYFT_LAB_PRODUCT_LINK = By.cssSelector("a.product-item-link");
    public static final By LYFT_LAB_INSPIRED_BY = By.cssSelector("div.inspired-by-text");
    private static final By BUNDLE_THREE_PACK_PRODUCT = By.xpath("//div[@class='lab-landing-product-carousel']//following::a[(contains(@title,'3 PACK') or contains(@title,'3-PACK'))][@class='product-item-link']");
    private static final By BUNDLE_SIX_PACK_PRODUCT=By.xpath("//div[@class='lab-landing-product-carousel']//following::a[(contains(@title,'6 PACK') or contains(@title,'6-PACK') )][@class='product-item-link']");
    public static final By MY_OPINION_BLOCK_HEADER = By.cssSelector("div.state-v1.insight-buysomething.show-cms-block:nth-child(2)>p");
    public static final By LOGIN_TO_LAB_LINK=By.xpath("//div[@class='lyft-lab-rectangle-target-element']//a[contains(@href,'/se/sv/customer/account/login/')]");
    private static final By EMAIL_POPUP_LINK = By.cssSelector("input#email.input-text");
    private static final By CHECKOUT_POPUP_EMAIL = By.cssSelector("input#customer-email.input-text,input#email");
    private static final By CHECKOUT_POPUP_PASSWORD = By.cssSelector("input#pass.input-text,#login-form > fieldset div.field.password.required > div > input");
    private static final By CHECKOUT_POPUP_SIGN_IN = By.cssSelector("button#send2"); //.action.login.primary");
    private final static By PLP_STRENGTH_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link']//following::select[@class='super-attribute-select']");
    private final static By PLP_QUANTITY_DROPDOWN = By.xpath("//div[@class='lyft-lab lyft-lab-product'][@data-content-type='row']//following::a[@class='product-item-link']//following::select[@class='input-text qty']");
    private final static By LOGIN_REGISTER_TOBUY_BUTTON_PREVIOUS_COLLECTION=By.xpath("//a[contains(@title,'3 PACK') or contains(@title,'3-PACK')]//following::button[@class='action tocart login show-cms-block']");
    public final static By ADD_TO_CART_BUTTON_LAB=By.xpath("//button[@class='action primary tocart addtocart show-cms-block'][@id='product-addtocart-button']");
    public final static By PRODUCT_ADD_TO_CART_BUTTON_LYFTSE = By.cssSelector("div[data-pb-style='PAACC5G'] button#product-addtocart-button");
    private final static By MYLAB_SURVEY_BLOCK = By.cssSelector("div.pagebuilder-column.survey-iframe.product-survey-container.show-cms-block");
    private final static By MYLAB_NUMBER_OF_SURVEYS = By.cssSelector("div.pagebuilder-column.survey-iframe.product-survey-container.show-cms-block > div");
    private final static By MYLAB_INNOVATION_RESEARCH_SURVEY=By.xpath("//div[contains(@class,'product-survey-container')]//following::strong[@class='product-item-name']");
    private final static By BUTTON_MYLAB_SURVEYS_OVERVIEW=By.cssSelector("button.review.pagebuilder-button-primary");
    private final static By MYLAB_SURVEY_CONTENT=By.cssSelector("div.survey-frame-top");
    private final static By BUTTON_CLOSE_MYLAB_SURVEY=By.cssSelector("button.close.hide.pagebuilder-button-primary");

    public void clickLyftLabLinkFromHeaderMenu() {
        try{
            waitForExpectedElement(LYFTLAB_DESKTOP_LINK, 10).click();}
        catch (Exception ex){
            clickByElementByQueryJSExecutor(LYFTLAB_DESKTOP_LINK);
        }
    }

    public void clickOnSeeTheFlavorsLink() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case LYFTSE:
                waitForExpectedElement(SEE_ALL_FLAVOURS_LINK_LYFTSE, 10);
                if (getWebDriver().findElements(SEE_ALL_FLAVOURS_LINK_LYFTSE).size() > 0) {
                    clickFirstElementByQueryJSExecutor(SEE_ALL_FLAVOURS_LINK_LYFTSE);
                }
                break;
            default:
                waitForExpectedElement(SEE_ALL_FLAVOURS_LINK, 10);
                if (getWebDriver().findElements(SEE_ALL_FLAVOURS_LINK).size() > 0)
                    clickFirstElementByQueryJSExecutor(SEE_ALL_FLAVOURS_LINK);
                else if (getWebDriver().findElements(SEE_ALL_FLAVOURS_NEW_COLLECTION).size() > 0)
                    clickFirstElementByQueryJSExecutor(SEE_ALL_FLAVOURS_NEW_COLLECTION);
                else if (getWebDriver().findElements(SEE_ALL_FLAVOURS_EDT1).size() > 0)
                    clickFirstElementByQueryJSExecutor(SEE_ALL_FLAVOURS_EDT1);
        }
    }

    public void clickOnSeeTheFlavorsLinkForCollection3() {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                waitForExpectedElement(SEE_ALL_FLAVOURS_COLLECTION_3, 10);
                try {
                    clickFirstElementByQueryJSExecutor(SEE_ALL_FLAVOURS_COLLECTION_3);
                } catch (Exception ex) {
                    clickFirstElementByQueryJSExecutor(SEE_ALL_FLAVOURS_NEW_COLLECTION);
                }
                break;
            default:
        }
    }

    public void clickOnLyftLabLinkFromMobileBurgerMenu() {
        waitForExpectedElement(LYFTLAB_BURGER_MENU).click();
        clickByElementByQueryJSExecutor(LYFTLAB_MOBILE_LINK);
    }

    public void clickOnLyftLinkFromMobileBurgerMenu() {
        waitForExpectedElement(LYFTLAB_BURGER_MENU).click();
        clickByElementByQueryJSExecutor(LYFTLAB_MOBILE_LINK);
    }

    public void clickOnCurrentlyActiveCollectionLinkFromCollections() {
        clickByElementByQueryJSExecutor(CURRENT_ACTIVE_COLLECTION_LINK);
    }

    public void navigateToLyftLabLandingPage() {
        waitForExpectedElement(HEADER_LYFTLAB_LOGO).isDisplayed();
    }

    public void navigateToLabPLPOnLyftLabHomePage() {
        clickLyftLabLinkFromHeaderMenu();
        navigateToLyftLabLandingPage();
        clickOnSeeTheFlavorsLink();
    }

    public void assertLinksUnderLABCategoryHeader() {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                assertTrue(waitForExpectedElement(By.linkText(UrlBuilder.getMessage("theCollectionsLinkHeader.key"))).isDisplayed());
                //assertTrue(waitForExpectedElement(By.linkText(UrlBuilder.getMessage("collaboratorLinkHeader.key"))).isDisplayed()); - Link Removed as of now - Bug 443516
                assertTrue(waitForExpectedElement(By.linkText(UrlBuilder.getMessage("MyLABLinkHeader.key"))).isDisplayed());
                break;
            case "lyftdk":
                assertTrue(waitForExpectedElement(By.linkText(UrlBuilder.getMessage("theCollectionsLinkHeader.key"))).isDisplayed());
                break;
            default:
        }
    }

    public void hoverOverCollectionCategoryAndAssertSubCategory(String strExpectedText) {
        hoverOnElement(By.linkText(UrlBuilder.getMessage(strExpectedText)));
        assertTrue(waitForExpectedElement(COLLECTIONS_CATEGORY_LINK).isDisplayed());
        assertTrue(waitForExpectedElement(CATEGORY_NEW_BADGE).isDisplayed());
    }

    public void clickCollectionCategoryAndAssertSubCategory(String strExpectedText) {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(strExpectedText)), 10).click();
        assertTrue(waitForExpectedElement(MOBILE_COLLECTIONS_CATEGORY).isDisplayed());
        assertTrue(waitForExpectedElement(MOBILE_CATEGORY_NEW_BADGE).isDisplayed());
    }

    public void navigateToInsightsLandingPage() {
        waitForExpectedElement(INSIGHTS_LANDING_PAGE, 3);
        assertTrue(getWebDriver().findElements(INSIGHTS_LANDING_PAGE).size() > 0);
    }

    public void clickOnBurgerMenuOnLyftLabSite() {
        waitForExpectedElement(LYFTLAB_BURGER_MENU,10);
        clickByElementByQueryJSExecutor(LYFTLAB_BURGER_MENU);
    }

    public void assertTextColorForLyftFooterLinks(String strTextColor) {
        List<WebElement> lstElements = getWebDriver().findElements(LYFT_FOOTER_LINKS);
        for (WebElement ele : lstElements) {
            String text = ele.getCssValue("color").toString();
            String hexColorValue = Color.fromString(text).asHex();
            AssertJUnit.assertTrue(hexColorValue.equals(UrlBuilder.getMessage(strTextColor)));
            if (ele.getText().equals(UrlBuilder.getMessage("lastFooterLink.key"))) {
                break;
            }
        }
    }

    public void assertBackgroundColorOfTheLyftFooterSection(String strBgColor) {
        scrollElementIntoView(LYFT_FOOTER_SECTION);
        WebElement WebElement1 = waitForExpectedElement(FOOTER_LYFT_CONTENT);
        String text = WebElement1.getCssValue("background-color").toString();
        String hexColorValue = Color.fromString(text).asHex();
        assertTrue(hexColorValue.equals(UrlBuilder.getMessage(strBgColor)));
    }

    public void assertLyftLABLogoInFooterSection() {
        scrollElementIntoView(LYFT_FOOTER_SECTION);
        assertTrue(waitForExpectedElement(FOOTER_LYFTLAB_LOGO).isDisplayed());
        assertTrue(waitForExpectedElement(LYFTLAB_LOGO_IMAGE).getAttribute("data-src").contains("lab_logo.png"));
    }

    public void assertLyftLogoInFooterSection() {
        scrollElementIntoView(LYFT_FOOTER_SECTION);
        assertTrue(waitForExpectedElement(FOOTER_LYFT_LOGO).isDisplayed());
        assertTrue(waitForExpectedElement(LYFT_LOGO_IMAGE).getAttribute("data-src").contains("logo-white.svg"));
    }

    public void clickOnTheLyftLabLogoInHeader() {
        waitForExpectedElement(HEADER_LYFTLAB_LOGO).click();
    }

    public void clickOnTheLyftLogoInHeader() {
        waitForExpectedElement(HEADER_LYFT_LOGO, 10).click();
    }

    public void assertSinglePackProductsOnLabPLP() {
        List<WebElement> lstSinglePackProducts = getWebDriver().findElements(By.xpath("//div[@class='lab-landing-product-carousel']//following::a[(contains(@title,'SINGLE PACK'))][@class='product-item-link']"));
        assertTrue(lstSinglePackProducts.size() > 1);
    }

    public void assertBundlePackProductsOnLabPLP() {
        List<WebElement> lst3PackProducts = getWebDriver().findElements(BUNDLE_THREE_PACK_PRODUCT);
        List<WebElement> lst6PackProducts = getWebDriver().findElements(BUNDLE_SIX_PACK_PRODUCT);
        assertTrue(lst3PackProducts.size() >= 1 && lst6PackProducts.size() >= 1);
    }

    public void clickActiveCollectionLinkFromCollectionsCategory() {
        waitForExpectedElement(By.xpath("//li[@class='lab-links']//a")).click();
        waitForPage(10);
    }

    public void assertLyftLabLogoIsDisplayed() throws Throwable {
        Thread.sleep(5000);
        assertTrue(isElementPresent(LYFT_LAB_LOGO));
    }

    public void assertLyftLogoIsDisplayed() {
        waitForExpectedElement(LYFT_TAB, 10).click();
        assertTrue(isElementPresent(SEARCH_ICON));
        assertTrue(isElementPresent(LYFT_LOGO));
    }

    public void clickOnLinkAndVeriFyLogo() {
        waitForExpectedElement(BLOG_LINK, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        assertTrue(isElementPresent(LYFT_LOGO));
        assertLyftLogoIsDisplayed();
        waitForExpectedElement(ABOUT_US_LINK, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        assertTrue(isElementPresent(LYFT_LOGO));
        assertLyftLogoIsDisplayed();
        waitForExpectedElement(SHOP_LINK, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        assertTrue(isElementPresent(LYFT_LOGO));
    }

    public void userSelectNewCollection(){
        waitForExpectedElement(THE_COLLECTION_LINK, 10).click();
        if(getWebDriver().findElements(COLLECTIONS_CATEGORY_LINK).size()>1){
            clickFirstElementByQueryJSExecutor(COLLECTIONS_CATEGORY_LINK);
        }
        else{
            clickByElementByQueryJSExecutor(COLLECTIONS_CATEGORY_LINK);
        }
    }

    public void userSelectPreviousCollection(){
        scrollElementIntoView(THE_COLLECTION_LINK);
        waitForExpectedElement(THE_COLLECTION_LINK, 10).click();
        if(getWebDriver().findElements(COLLECTIONS_CATEGORY_LINK).size()>1){
            clickIndexElementByQueryJSExecutor(COLLECTIONS_CATEGORY_LINK,1);
        }
        else{
            LOG.info("There is no any previous collection configured. Only New collection is available");

        }
    }

    public int countOfWebelement(By by){
        List<WebElement> elements = getWebDriver().findElements(by);
        return elements.size();
    }

    public void clickFirstLyftLabThreePackProduct(){
        clickFirstElementByQueryJSExecutor(PDP.LINK_THREEPACK_PRODUCT);
    }

    public void clickFirstLyftLabSixPackProduct(){
        waitForExpectedElement(PDP.LINK_SIXPACK_PRODUCT,10);
        clickFirstElementByQueryJSExecutor(PDP.LINK_SIXPACK_PRODUCT);
    }

    public void clickSeeMyRewardsLink(){
        waitForExpectedElement(SEE_MY_REWARDS_LINK,20);
        clickByElementByQueryJSExecutor(SEE_MY_REWARDS_LINK);
        waitForExpectedElement(BADGES_ACTIVE_COLLECTION).getAttribute("class").equals(getWebDriver().switchTo().activeElement().getAttribute("class"));
    }

    public void assertPreviousCollectionBadgeTitlesAsPerCollectionName() {
        int intPreviousCollectionSize = getWebDriver().findElements(COLLECTIONS_CATEGORY_LINK).size()-1;
        List <WebElement> LIST_PREVIOUS_COLLECTIONS = getWebDriver().findElements(PREVIOUS_COLLECTIONS_TILES);
        assertTrue(intPreviousCollectionSize==LIST_PREVIOUS_COLLECTIONS.size());
        for (WebElement ele: LIST_PREVIOUS_COLLECTIONS){
            assertTrue(ele.getText().contains("EDT.0"+intPreviousCollectionSize)||ele.getText().contains("COLLECTION "+intPreviousCollectionSize));
            intPreviousCollectionSize--;
        }
    }

    public void clickOnPreviousCollectionTilesAndAssertBadgesForEach() {
        for (WebElement ele: getWebDriver().findElements(PREVIOUS_COLLECTIONS_TILES)){
            clickUsingJS(ele);
            assertTrue(waitForExpectedElement(PREVIOUS_COLLECTIONS_INACTIVE_BADGES,5).isDisplayed());
        }
    }

    public void checkOutPopUpSignInLyftLab() {
        String username = UrlBuilder.getMessage("loginValidEmail.key");
        String password = UrlBuilder.getMessage("loginValidPassword.key");
        waitForExpectedElement(CHECKOUT_POPUP_EMAIL, 10);
        try {
            waitForExpectedElement(CHECKOUT_POPUP_EMAIL, 10).sendKeys(username);
        } catch (Exception e) {
            waitForExpectedElement(EMAIL_POPUP_LINK, 10).sendKeys(password);
        }
        waitForExpectedElement(CHECKOUT_POPUP_PASSWORD).sendKeys(password);
        clickUsingJS(CHECKOUT_POPUP_SIGN_IN);
    }

    public void assertTextOfBuyButtonOnBasisOfLoginRuleAppliedForGuestUser() {
        waitForExpectedElement(plp.LOGIN_REGISTER_TOBUY_BUTTON, 5);
        if(getWebDriver().findElements(plp.LOGIN_REGISTER_TOBUY_BUTTON).size()>0)
            assertTrue(getWebDriver().findElements(plp.LOGIN_REGISTER_TOBUY_BUTTON).get(0).getText().equalsIgnoreCase(UrlBuilder.getMessage("loginOrRegisterToBuyText.key")));
        else  if(getWebDriver().findElements(plp.ADD_TO_CART_BUTTON_UK).size()>0)
            if (UrlBuilder.getLocale().equals("lyftse")) {
                assertTrue(getWebDriver().findElements(PRODUCT_ADD_TO_CART_BUTTON_LYFTSE).get(0).getText().equalsIgnoreCase(UrlBuilder.getMessage("AddToCartText.key")));
            } else {
                assertTrue(getWebDriver().findElements(plp.ADD_TO_CART_BUTTON_UK).get(0).getText().equalsIgnoreCase(UrlBuilder.getMessage("AddToCartText.key")));
            }
    }

    public void userLogsInFromPLPWhenLoginRuleIsEnabled() {
        if(getWebDriver().findElements(plp.LOGIN_REGISTER_TOBUY_BUTTON).size()>0) {
            clickFirstElementByQueryJSExecutor(plp.LOGIN_REGISTER_TOBUY_BUTTON);
            checkOutPopUpSignInLyftLab();
            waitForExpectedElement(plp.ADDTOCART_KITCHEN_BUTTON, 10);
            assertTrue(getWebDriver().findElements(plp.ADDTOCART_KITCHEN_BUTTON)
                    .get(0).getAttribute("innerHTML").contains(UrlBuilder.getMessage("AddToCartText.key")));
        }
    }

    public void clickOnLoginOrRegisterToBuyButtonFromPLPWhenLoginRuleIsEnabled() {
        if(getWebDriver().findElements(plp.LOGIN_REGISTER_TOBUY_BUTTON).size()>0) {
            clickFirstElementByQueryJSExecutor(plp.LOGIN_REGISTER_TOBUY_BUTTON);
            assertTrue(waitForExpectedElement(CHECKOUT_POPUP_EMAIL, 10).isDisplayed());
            clickByElementByQueryJSExecutor(POPUP_CLOSE_BUTTON);
        }
    }

    public void clickOnLoginOrRegisterToBuyButtonFromPreviousCollectionWhenLoginRuleIsEnabled() {
        if(getWebDriver().findElements(LOGIN_REGISTER_TOBUY_BUTTON_PREVIOUS_COLLECTION).size()>0) {
            clickFirstElementByQueryJSExecutor(LOGIN_REGISTER_TOBUY_BUTTON_PREVIOUS_COLLECTION);
            assertTrue(waitForExpectedElement(CHECKOUT_POPUP_EMAIL, 10).isDisplayed());
            clickByElementByQueryJSExecutor(POPUP_CLOSE_BUTTON);
        }
    }

    public void clickOnAddToCartButtonFromPLPWhenLoginRuleIsDisabled() throws Throwable {
        if (getWebDriver().findElements(ADD_TO_CART_BUTTON_LAB).size() > 0) {
            scrollElementIntoView(PLP_STRENGTH_DROPDOWN);
            waitForExpectedElement(PLP_STRENGTH_DROPDOWN).click();
            selectValueFromDropDownByby("Regular", PLP_STRENGTH_DROPDOWN);
            waitForExpectedElement(PLP_QUANTITY_DROPDOWN).click();
            selectValueFromDropDownByby("1", PLP_QUANTITY_DROPDOWN);
            getWebDriver().findElements(PRODUCT_ADD_TO_CART_BUTTON_LYFTSE).get(0).click();
            waitForAjaxElementNotToBePresent(getWebDriver(), 5);
        }
    }

    public boolean assertSurveysForBrandInnovationAndResearch(String SurveyName) {
        boolean blnFlag=false;
        for (int i = 0; i < getWebDriver().findElements(MYLAB_NUMBER_OF_SURVEYS).size(); i++) {
            if (getWebDriver().findElements(MYLAB_INNOVATION_RESEARCH_SURVEY).get(i).getText().contains(SurveyName)){
                blnFlag=true;
                break;}
        }
        return blnFlag;
    }

    public void assertAllSurveysForBrandInnovationAndResearchAreDisplayed() {
        waitForExpectedElement(MYLAB_SURVEY_BLOCK,20);
        if(!isElementPresent(MYLAB_SURVEY_BLOCK))
        {
            waitForExpectedElement(MYLAB_SURVEY_BLOCK,50);
        }
        assertTrue(waitForExpectedElement(MYLAB_SURVEY_BLOCK).isDisplayed());
        scrollElementIntoView(MYLAB_SURVEY_BLOCK);
        assertTrue(assertSurveysForBrandInnovationAndResearch(UrlBuilder.getMessage("myLABInnovationSurvey.key")));
        assertTrue(assertSurveysForBrandInnovationAndResearch(UrlBuilder.getMessage("myLABResearchSurvey.key")));
        for(WebElement ele : getWebDriver().findElements(BUTTON_MYLAB_SURVEYS_OVERVIEW)) {
            assertTrue(ele.isEnabled());
        }
    }

    public void assertSurveyRulesAppearInNoSpecificSequenceForActiveCollection() {
        for(int i=0;i<getWebDriver().findElements(BUTTON_MYLAB_SURVEYS_OVERVIEW).size();i++) {
            clickUsingJS(getWebDriver().findElements(BUTTON_MYLAB_SURVEYS_OVERVIEW).get(i));
            assertTrue(getWebDriver().findElements(MYLAB_SURVEY_CONTENT).get(i).isDisplayed());
            clickUsingJS(BUTTON_CLOSE_MYLAB_SURVEY);
            waitForExpectedElement(MYLAB_SURVEY_BLOCK,10);
        }
    }

    public void assertStrengthForEachProductIsPreSelectedForCollection7() {
        if (doesURLContain("edt-07-collection")) {
            Select lstStrength = new Select(waitForExpectedElement(By.xpath("//div[@class='lab-landing-product-carousel']//following::div[@class='select-box no-selection']//select[@class='super-attribute-select']")));
            assertTrue(getWebDriver().findElements(By.xpath("//div[@class='lab-landing-product-carousel']//following::div[@class='product-main-info-attributes product-options-select']//div[@class='select-box no-selection']")).size() == getWebDriver().findElements(By.xpath("//div[@class='lab-landing-product-carousel']//following::a[@class='product-item-link']")).size());
            List<WebElement> lstSelectedStrength = lstStrength.getAllSelectedOptions();
            for (int i = 0; i < lstSelectedStrength.size(); i++) {
                assertEquals(lstSelectedStrength.get(i).getText(), "Regular");
            }
        }
    }

    public void assertStrengthForEachProductIsPreSelectedForCollection7onPDP() {
        if (doesURLContain("edt-07-collection")) {
            List<WebElement> lstProductsLink = getWebDriver().findElements(By.xpath("//div[@class='lab-landing-product-carousel']//following::a[@class='product-item-link']"));
            for (int i = 1; i <= lstProductsLink.size(); i++) {
                waitForAjaxElementNotToBePresent(getWebDriver(),2);
                clickUsingJS(By.xpath("//div[@class='lab-landing-product-carousel']//following::a[@class='product-item-link']"+"["+i+"]"));
                //Select lstStrength_PDP = new Select(waitForExpectedElement(By.xpath("//div[@class='select-box no-selection']//select[@class='super-attribute-select']"),10));
                List<WebElement> lstSelectedStrength = (List<WebElement>) new Select(waitForExpectedElement(By.xpath("//div[@class='select-box no-selection']//select[@class='super-attribute-select']"),10)).getAllSelectedOptions();
                assertEquals(lstSelectedStrength.get(0).getText(), "Regular");
                hoverOnElement(By.linkText(UrlBuilder.getMessage("theCollectionsLinkHeader.key")));
                waitAndClickByElementByJSExecutor(CURRENT_ACTIVE_COLLECTION_LINK,2);
            }
        }
    }
}
