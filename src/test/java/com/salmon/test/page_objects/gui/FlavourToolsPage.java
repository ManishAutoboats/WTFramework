package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlavourToolsPage extends PageObject {
    protected static final Logger LOG = LoggerFactory.getLogger(GuidedSellPage.class);
    private final static By FLAVOUR_TOOLS_MENU = By.cssSelector("#next-btn");
    public final static By FLAVOUR_TOOL_LETSGO_BTN = By.cssSelector("#flavour-get-started > span");
    public final static By FLAVOUR_TOOL_START_BTN = By.cssSelector("div#header-mpdal div[class*='background-image']:nth-child(2) a.pagebuilder-button-primary");
    private final static By CREATE_YOUR_FLAOUR_PROFILE_BTN = By.cssSelector("#create-flavour-profile > span");
    private final static By EXPLLORE_RANGE_BTN = By.cssSelector("#explore-range > span");
    private final static By JUMP_BACK_IN_BTN = By.cssSelector("#pick-up-left-off-btn");
    private final static By DEVICE_VUSE_EPOD_BTN = By.cssSelector("#choice-block > li:nth-child(1) > div > span");
    private final static By DEVICE_VUSE_EPEN_BTN = By.cssSelector("#choice-block > li:nth-child(2) > div > span");
    private final static By DONT_HAVE_DEVICE_BTN = By.cssSelector("#choice-block > li:nth-child(3) > div > span");
    private final static By BACK_BTN = By.cssSelector("#back-link");
    private final static By NEXT_BTN = By.cssSelector("#next-btn");
    private final static By HEADER_H5 = By.cssSelector("div.flavour-selector > div.question-wrapper > div.question-container > div.subtitle > h5");
    private final static By HEADER_H2 = By.cssSelector("#question > h2");
    private final static By SMALL_TITLE = By.cssSelector("div.question-container > div.small-title > p");
    private final static By PROCESS_BAR = By.cssSelector("#updateProgress");
    private final static By DEVICE_EPOD = By.cssSelector("#choice-block > li.answer-lbl.choice.question-0.flv-epod.choice-box.hexagon.image.li-item-0");
    private final static By MIGRATION_POP_UP_CLOSE = By.cssSelector(".close-migration-popup");
    private final static By HEADER_BUY_DEVICES_H5 = By.cssSelector("div.device-slidin-page > div.devices-page-wrapper > div.midle-container > div.maintitle > h5");
    private final static By SMALL_BUY_DEVICES_TITLE = By.cssSelector("div.device-slidin-page > div.devices-page-wrapper > div.midle-container > div.small-title > h2");
    private final static By SHOP_EPOD_BUTTON = By.cssSelector("li.device-item[data-type='epod'] > a");
    private final static By ADD_TO_BASKET = By.cssSelector(".slide-add-btn.add_to_basket");
    private final static By FLAVOUR_PROFILE_SUBMENU = By.cssSelector("#account-nav > ul > li.nav.item.current > strong");
    private final static By COUNT_DOWN_VIDEO = By.cssSelector("#counter-desktop");
    private final static By UPDATE_YOUR_PROFILE_BTN = By.cssSelector("#maincontent > div.columns > div > div.product-recommendation > div.flavour-wheel-main-container > div.bottom-button > button");
    private final static By RECOMMENDATION_PAGE = By.cssSelector("#maincontent > div.columns > div > div.product-recommendation > div.recommendation-wrapper > div.right-container > ul > li.result-lbl.right-list.main-page-list-0 > div");
    private final static By SAVE_PROFILE = By.cssSelector("#save-btn");
    private final static By SAVED_PROFILE = By.cssSelector("#recommend-full-content > div.full-flavour-wheel > div > div.large-wheel");
    private final static By FLAVOUR_PROFILE_MENU = By.xpath("//*[text()='Flavour Profile']");
    private final static By POPUP_LOGIN_EMAIL = By.cssSelector("#email");
    private final static By POPUP_LOGIN_PASSWORD= By.cssSelector("#pwd-field");
    private final static By POPUP_SUBMIT_BTN = By.cssSelector("#send2.action.login.primary");
    private final static By CONTINUE_WITH_FLAVOUR_PROFILE = By.cssSelector("#slide-continue-btn");
    private final static By BACK_BTN_POPUP = By.cssSelector("#maincontent > div.columns > div > div.device-slide > div > a");
    private final static By ADD_SELECTED_FLAVOURS = By.cssSelector("#add-btn");
    private final static By WEIGHT_OPTION1_RECOM = By.cssSelector("div.product-slide-inner.active > ul.inner-list-recomm > li:nth-child(1) > div.product-item-details > div.product-attributes > div > div > div > div:nth-child(1)");
    private final static By WEIGHT_OPTION2_RECOM = By.cssSelector("div.product-slide-inner.active > ul.inner-list-recomm > li:nth-child(2) > div.product-item-details > div.product-attributes > div > div > div > div:nth-child(1)");
    private final static By WEIGHT_OPTION3_RECOM = By.cssSelector("div.product-slide-inner.active > ul.inner-list-recomm > li:nth-child(3) > div.product-item-details > div.product-attributes > div > div > div > div:nth-child(1)");
    private final static By ADD_SELECTED_FLAVOURS_TO_BASKET = By.xpath("//*[@id='slide-add-btn' and text()='Add selected flavours to basket']");
    private final static By BACK_BTN_POPUP_RECOM = By.cssSelector("#maincontent > div.columns > div > div.product-slide > div.product-slide-inner.active > div:nth-child(1) > a");
    public final static By SIZZLE_VIDEO_FLAVOURS_QUIZ= By.cssSelector("video#sizzle-land");
    private final static By PROFILE_SAVED_BUTTON=By.cssSelector("button.saved_profile");

    public void clickFlavourToolsMenu() throws Throwable {
       waitForExpectedElement(FLAVOUR_TOOLS_MENU, 5).click();
   }

    public void clickLETSGoBtn() throws Throwable {
        waitForExpectedElement(FLAVOUR_TOOL_LETSGO_BTN, 5).click();
        waitForExpectedElement(FLAVOUR_TOOL_START_BTN, 5).click();
    }

    public void createYourFlavourProfile() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(CREATE_YOUR_FLAOUR_PROFILE_BTN, 5).click();
        }
    }

    public void exploreRange() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(EXPLLORE_RANGE_BTN, 5).click();
        }
    }

    public void jumpBackIn() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(JUMP_BACK_IN_BTN, 5).click();
        }
    }

    public void selectDevice(String deviceOption) throws Throwable {
        switch (deviceOption) {
            case "epod":
                waitForExpectedElement(DEVICE_VUSE_EPOD_BTN, 5).click();
                break;
            case "epen":
                waitForExpectedElement(DEVICE_VUSE_EPEN_BTN, 5).click();
                break;
            case "nodevice":
                waitForExpectedElement(DONT_HAVE_DEVICE_BTN, 5).click();
                break;
            default:
        }
    }

    public void clickBackBtn() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            scrollToPageTop();
            waitForExpectedElement(BACK_BTN, 5).click();
        }
    }

    public void clickNextBtn() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(NEXT_BTN, 5).click();
        }
    }

    public boolean verifyProgressBar(String questionPage) throws Throwable {
        switch (questionPage) {
            case "device":
                return(getAttribute(PROCESS_BAR, "style").contains("17%"));
            case "nicotine":
                return(getAttribute(PROCESS_BAR, "style").contains("33%"));
            case "taste":
                return(getAttribute(PROCESS_BAR, "style").contains("50%"));
            case "sensation":
                return(getAttribute(PROCESS_BAR, "style").contains("67%"));
            case "flavour":
                return(getAttribute(PROCESS_BAR, "style").contains("83%"));
            case "image":
                return(getAttribute(PROCESS_BAR, "style").contains("100%"));
            default:
                return false;
        }
    }

    public boolean verifyBuyDevicesTitles(String headerH5, String headerH2, String smallTitle) throws Throwable {

        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            return (textToBePresentInElementLocated(HEADER_H5, UrlBuilder.getMessage(headerH5)) &&
                    textToBePresentInElementLocated(HEADER_H2, UrlBuilder.getMessage(headerH2)) &&
                    textToBePresentInElementLocated(SMALL_TITLE, UrlBuilder.getMessage(smallTitle)));
        }
        return false;
    }

    public boolean verifyBuyDevicesTitles(String headerH5, String smallTitle) throws Throwable {

        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            return (waitForExpectedElement(HEADER_BUY_DEVICES_H5).getText().equalsIgnoreCase(UrlBuilder.getMessage(headerH5)) &&
                    waitForExpectedElement(SMALL_BUY_DEVICES_TITLE).getText().equalsIgnoreCase("WHAT’S THE DIFFERENCE?"));
        }
        return false;
    }

    public void playSizzleVideo() throws Throwable {
        waitForExpectedElement(DEVICE_VUSE_EPOD_BTN, 20);
    }

    public void selectDeviceOptions(String deviceOption) throws Throwable {
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        String deviceStr = "#choice-block > li.answer-lbl.choice.question-0.flv-"+deviceOption.substring(0,deviceOption.length()-1)+".choice-box.hexagon.image.li-item-"+deviceOption.substring(deviceOption.length()-1);
        waitForExpectedElement(By.cssSelector(deviceStr), 10).click();
    }

    public void selectNicotineOptions(String nicotineOption) throws Throwable {
        String nicotineStr = "#choice-block > li.answer-lbl.choice.question-1.flv-.choice-box.hexagon.image.li-item-"+ nicotineOption.charAt(3) +"> div > span";
        waitForExpectedElement(By.cssSelector(nicotineStr), 10).click();
    }

    public boolean verifyNextBtnDisplay() throws Throwable {
        return isElementPresentByby(NEXT_BTN);
    }

    public void selectTasteOptions(String tasteOption) throws Throwable {
        String tasteStr = "#choice-block > li.answer-lbl.choice.question-2.flv-.choice-box.hexagon.image.li-item-" + tasteOption.charAt(5) +"> div > span";
        waitForExpectedElement(By.cssSelector(tasteStr), 10).click();
    }

    public void selectSensationOptions(String sensationOption) throws Throwable {
        String senStr = "#choice-block > li.answer-lbl.choice.question-3.flv-.choice-box.hexagon.image.li-item-" + sensationOption.charAt(3) + "> div > span";
        waitForExpectedElement(By.cssSelector(senStr), 10).click();
    }

    public void selectFlavourOptions(String flavourOption) throws Throwable {
        String flaStr = "#choice-block > li.answer-lbl.choice.question-4.flv-.choice-box.hexagon.image.li-item-" + flavourOption.charAt(3) + "> div > span";
        waitForExpectedElement(By.cssSelector(flaStr), 10).click();
    }

    public void selectImageOptions(String imageOption) throws Throwable {
        String imageStr = "#choice-block > li.answer-lbl.choice.choice-box.hexagon.image.li-item-"+ imageOption.substring(3) +"> div > img.desktop-img";
        waitForExpectedElement(By.cssSelector(imageStr), 10).click();
    }

    public boolean verifyBackBtnDisplay() throws Throwable {
        return isElementPresentByby(BACK_BTN);
    }

    public boolean isDeviceDescriptionDisplayed() throws Throwable{
        return false;
    }

    public void closeMigrationPopUp() {
       if(isElementPresentByby(MIGRATION_POP_UP_CLOSE)){
           waitForExpectedElement(MIGRATION_POP_UP_CLOSE).click();
       }
    }

    public void selectShopEpod() {
       waitForExpectedElement(SHOP_EPOD_BUTTON).click();
    }

    public void addToBasket() {
       isElementClickable(ADD_TO_BASKET);
       waitAndClickByElementByJSExecutor(ADD_TO_BASKET,20);
    }

    public void fillInPopupLoginForm(String username, String password) {
        waitForAjaxElementNotToBePresent(getWebDriver(),3);
        waitClearAndEnterText(POPUP_LOGIN_EMAIL, username);
        waitClearAndEnterText(POPUP_LOGIN_PASSWORD, password);
    }

    public void submitPopupLoginForm() {
        waitForExpectedElement(POPUP_SUBMIT_BTN, 10).click();
        waitForAjaxElementNotToBePresent(getWebDriver(),5);
        waitForExpectedElement(PROFILE_SAVED_BUTTON,10);
    }

    public void selectContinueWithFlavourProfile() {
       clickByElementByQueryJSExecutor(CONTINUE_WITH_FLAVOUR_PROFILE);
    }

    public boolean isEpodDeviceSelected() {
        scrollUpByCoordinator(200);
        return waitForExpectedElement(DEVICE_EPOD).getAttribute("class").contains("selected");
    }

    public boolean verifyFlavourProfileMenuDisplay() throws Throwable {
        return isElementPresentByby(FLAVOUR_PROFILE_SUBMENU);
    }

    public boolean verifyCountDownVideoDisplay() throws Throwable {
        return isElementPresentByby(COUNT_DOWN_VIDEO);
    }

    public void clickUpdateYourProfileBtn() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            isElementPresentByby(RECOMMENDATION_PAGE);
            scrollDownByCoordinator(800);
            waitForExpectedElement(UPDATE_YOUR_PROFILE_BTN, 15);
            retryingFindClick(UPDATE_YOUR_PROFILE_BTN);
        }
    }

    public void saveProfile() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(SAVE_PROFILE, 15);
            retryingFindClick(SAVE_PROFILE);
        }
    }

    public boolean verifyProfileSaved() throws Throwable {
        waitForExpectedElement(FLAVOUR_PROFILE_MENU, 10).click();
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            return waitForExpectedElement(SAVED_PROFILE, 15).isDisplayed();
        }
        return false;
    }

    public void clickBackBtnPopup() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(BACK_BTN_POPUP, 15);
            retryingFindClick(BACK_BTN_POPUP);
        }
    }

    public void dislayTheTopMenu() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            scrollToPageTop();
        }
    }

    public void addSelectedFlavour() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            isElementPresentByby(RECOMMENDATION_PAGE);
            waitForExpectedElement(ADD_SELECTED_FLAVOURS, 15);
            retryingFindClick(ADD_SELECTED_FLAVOURS);
        }
    }

    public void selectWeightOnRecommendationPopUp() {
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        waitForExpectedElement(WEIGHT_OPTION1_RECOM,8).click();
        waitForExpectedElement(WEIGHT_OPTION2_RECOM,8).click();
        waitForExpectedElement(WEIGHT_OPTION3_RECOM,8).click();
    }

    public void clickAddSelectedFlavoursToBasketBtn() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
            waitForElementToDisappear(POPUP_LOGIN_EMAIL, 15);
            waitForExpectedElement(ADD_SELECTED_FLAVOURS_TO_BASKET, 15).click();
        }
    }

    public void clickBackBtnRecomPopup() throws Throwable {
        if ("vuseuk".equals(UrlBuilder.getLocale())) {
            waitForExpectedElement(BACK_BTN_POPUP_RECOM, 15);
            retryingFindClick(BACK_BTN_POPUP_RECOM);
        }
    }
}
