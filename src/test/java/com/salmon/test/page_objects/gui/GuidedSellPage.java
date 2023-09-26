package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuidedSellPage extends PageObject {

    private HomePage homePage;

    public GuidedSellPage() {
    }

    public GuidedSellPage(HomePage homePage) {
        this.homePage = homePage;
    }
    protected static final Logger LOG = LoggerFactory.getLogger(GuidedSellPage.class);
    private final static By DEVICE_QUESTION_OPTION1 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:first-child");
    private final static By DEVICE_QUESTION_OPTION2 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:nth-child(2)");
    private final static By DEVICE_QUESTION_OPTION3 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:nth-child(3)");
    private static final By DEVICE_QUESTION_OPTION4 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:nth-child(4)");
    private static final By DEVICE_QUESTION_OPTION5 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:nth-child(5)");
    private final static By DEVICE_QUESTION_OPTION6 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:nth-child(6)");
    private final static By DEVICE_QUESTION_OPTION7 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:nth-child(7)");
    private final static By DEVICE_QUESTION_OPTION8 = By.cssSelector("div#tab-content-0>div.guidedSell-block>ul>li:nth-child(8)");
    private final static By DEVICE_NEXT_BTN = By.cssSelector("div#tab-content-0>button.next-qst");
    private final static By FLAVOUR_QUESTION_OPTION1 = By.cssSelector("div#tab-content-1>div.guidedSell-block>ul>li:first-child");
    private final static By FLAVOUR_QUESTION_OPTION2 = By.cssSelector("div#tab-content-1>div.guidedSell-block>ul>li:nth-child(2)");
    private final static By FLAVOUR_QUESTION_OPTION3 = By.cssSelector("div#tab-content-1>div.guidedSell-block>ul>li:nth-child(3)");
    private static final By FLAVOUR_QUESTION_OPTION4 = By.cssSelector("div#tab-content-1>div.guidedSell-block>ul>li:nth-child(4)");
    private static final By FLAVOUR_QUESTION_OPTION5 = By.cssSelector("div#tab-content-1>div.guidedSell-block>ul>li:nth-child(5)");
    private final static By FLAVOUR_NEXT_BTN = By.cssSelector("div#tab-content-1>button.next-qst");
    private final static By FLAVOUR_BACK_BTN = By.cssSelector("div.back.active>span.text.prev-qst");
    private final static By STRENGTH_QUESTION_OPTION1 = By.cssSelector("div#tab-content-1>div.guidedSell-block>ul>li:first-child");
    private final static By STRENGTH_QUESTION_OPTION1_LABEL = By.cssSelector("div#tab-content-2>div.guidedSell-block>ul>li:nth-child(1)");
    private final static By STRENGTH_QUESTION_OPTION2 = By.cssSelector("div#tab-content-2>div.guidedSell-block>ul>li:nth-child(2)");
    private final static By STRENGTH_QUESTION_OPTION3 = By.cssSelector("div#tab-content-2>div.guidedSell-block>ul>li:nth-child(3)");
    private final static By STRENGTH_QUESTION_OPTION4 = By.cssSelector("div#tab-content-2>div.guidedSell-block>ul>li:nth-child(4)");
    private final static By STRENGTH_QUESTION_OPTION5 = By.cssSelector("div#tab-content-2>div.guidedSell-block>ul>li:nth-child(5)");
    private final static By STRENGTH_QUESTION_OPTION6 = By.cssSelector("div#tab-content-2>div.guidedSell-block>ul>li:nth-child(6)");
    private final static By STRENGTH_QUESTION_OPTION7 = By.cssSelector("div#tab-content-2>div.guidedSell-block>ul>li:nth-child(7)");
    private final static By STRENGTH_FIND_BTN = By.cssSelector("div#tab-content-2>button.submit");
    private final static By STRENGTH_BACK_BTN = By.cssSelector("div.back.active>span.text.prev-qst");
    private static final By LET_GET_START_BTN = By.cssSelector("div.pagebuilder-button-primary");
    private static final By GUIDED_SELL_BUTTON_FR = By.cssSelector("div.items.sixth ul.navigation-widget__links li:nth-child(5) a");
    private static final By A_PROPOS_BUTTON_FR = By.cssSelector("li.level0.category-item.icon-vype.seventh > a:nth-child(2)");
    private static final By GUIDED_SELL_BUTTON_CO = By.cssSelector("div.vype-image-button.btn-small a.pagebuilder-button-secondary > span:nth-child(1)");
    private static final By GUIDED_SELL_BUTTON_MX = By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[6]/div/div/div/div[1]/div/div/div[1]/div[2]/div/a/span");
    private static final By GUIDED_SELL_BUTTON_UK = By.xpath("//a[contains(@href, '/gb/en/vape-questionnaire/')]");
    private static final By START_GUIDED_SELL = By.cssSelector("div.button>div.pagebuilder-button-primary>span");
    private static final By EZIGARETTEN = By.cssSelector("div.custom-categories > div > div > ul > li.level0.category-item.icon-devices.first > a");
    private static final By GUIDED_SELL_BUTTON_DE = By.cssSelector("div > span.banner-wrapper__title> a");
    private static final By POPUP_CLOSE_BTN = By.cssSelector("aside.modal-popup.migration-popup-container.modal-slide._inner-scroll._show > div.modal-inner-wrap > header > button");
    private static final By ABOUT_BTN = By.cssSelector("div.custom-categories > div > div > ul > li.level0.category-item.icon-vuse.sixth > a:nth-child(2)");
    private static final By GUIDED_SELL_BUTTON_ZA = By.cssSelector("div.more-menu-container > div > div.items.fifth > div > div.pagebuilder-column-group > div.pagebuilder-column.firstcol > div > ul > li:nth-child(2) > a");
    public static final By DEFERRED_PAYMENT_CTA = By.cssSelector("div.deferred-payment");
    public static final By LEARN_MORE_CTA = By.cssSelector("div.learn-more");
    private static final By GUIDED_SELL_POP_UP = By.cssSelector("#guided-sell-popup");
    private static final By POP_UP_REDIRECT_CTA = By.cssSelector(".popup-redirect");
    private static final By M_GUIDED_SELL_BUTTON_FR = By.cssSelector("div.burger-menu-container > div > div > div > div > ol > li:nth-child(6) > div > a");

    public boolean displayAllDeviceOptions() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "vuseuk":
                return (isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION1, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION2, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION3, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION4, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION5, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION6, 5));
            case "vusede":
                return (isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION1, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION2, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION3, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION4, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION5, 5));
            case "vusefr":
                return (isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION1, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION2, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION3, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION4, 5));
            case "vuseco":
            case "vusemx":
                return (isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION1, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION2, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION3, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION4, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION5, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION6, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION7, 5));
            case "vuseza":
                return (isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION1, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION2, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION3, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION4, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION5, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION6, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION7, 5)
                        && isElementDisplayedBySeconds(DEVICE_QUESTION_OPTION8, 5));
            default:
                return false;
        }

    }

    public void selectDeviceOptions(String device) throws Throwable {
        if (device.contains("key")){
           device = UrlBuilder.getMessage(device);
        }
        switch (device) {
            case "deviceOption1":
                waitForExpectedElement(DEVICE_QUESTION_OPTION1, 10).click();
                break;
            case "deviceOption2":
                waitForExpectedElement(DEVICE_QUESTION_OPTION2, 10).click();
                break;
            case "deviceOption3":
                waitForExpectedElement(DEVICE_QUESTION_OPTION3, 10).click();
                break;
            case "deviceOption4":
                waitForExpectedElement(DEVICE_QUESTION_OPTION4, 10).click();
                break;
            case "deviceOption5":
                waitForExpectedElement(DEVICE_QUESTION_OPTION5, 10).click();
                break;
            case "deviceOption6":
                waitForExpectedElement(DEVICE_QUESTION_OPTION6, 10).click();
                break;
            default:
                LOG.info("no device option is selected.");
                break;
        }
    }

    public void clickDeviceNextBtn() throws Throwable {
        waitForExpectedElement(DEVICE_NEXT_BTN,5);
        clickUsingJS(DEVICE_NEXT_BTN);
    }

    public boolean verifySelectedOptions(String option1) throws Throwable {
        switch (option1) {
            case "deviceOption1":
                return (isChecked(DEVICE_QUESTION_OPTION1));
            case "deviceOption2":
                return (isChecked(DEVICE_QUESTION_OPTION2));
            case "deviceOption3":
                return (isChecked(DEVICE_QUESTION_OPTION3));
            case "deviceOption4":
                return (isChecked(DEVICE_QUESTION_OPTION4));
            case "deviceOption5":
                return (isChecked(DEVICE_QUESTION_OPTION5));
            case "deviceOption6":
                return (isChecked(DEVICE_QUESTION_OPTION6));
            default:
                return false;
        }
    }

    public boolean verifyNotSelectedOptions(String option1) throws Throwable {
        switch (option1) {
            case "deviceOption1":
                return (!isChecked(DEVICE_QUESTION_OPTION1));
            case "deviceOption2":
                return (!isChecked(DEVICE_QUESTION_OPTION2));
            case "deviceOption3":
                return (!isChecked(DEVICE_QUESTION_OPTION3));
            case "deviceOption4":
                return (!isChecked(DEVICE_QUESTION_OPTION4));
            case "deviceOption5":
                return (!isChecked(DEVICE_QUESTION_OPTION5));
            case "deviceOption6":
                return (!isChecked(DEVICE_QUESTION_OPTION6));
            default:
                return false;
        }
    }

    public boolean displayAllFlavourOptions() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
            case "vusede":
            case "vuseco":
            case "vusemx":
            case "vuseza":
                return (isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION1, 5)
                        && isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION2, 5)
                        && isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION3, 5)
                        && isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION4, 5)
                        && isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION5, 5)
                );
            case "vuseuk":
                return (isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION1, 5)
                        && isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION2, 5)
                        && isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION4, 5)
                        && isElementDisplayedBySeconds(FLAVOUR_QUESTION_OPTION5, 5)
                );
            default:
                return false;
        }
    }

    public void selectFlavourOptions(String flavour) throws Throwable {
        switch (flavour) {
            case "flavourOption1":
                waitForExpectedElement(FLAVOUR_QUESTION_OPTION1, 10).click();
                break;
            case "flavourOption2":
                waitForExpectedElement(FLAVOUR_QUESTION_OPTION2, 10).click();
                break;
            case "flavourOption3":
                waitForExpectedElement(FLAVOUR_QUESTION_OPTION3, 10).click();
                break;
            case "flavourOption4":
                waitForExpectedElement(FLAVOUR_QUESTION_OPTION4, 10).click();
                break;
            case "flavourOption5":
                waitForExpectedElement(FLAVOUR_QUESTION_OPTION5, 10).click();
                break;
            default:
        }
    }

    public void clickFlavourNextBtn() throws Throwable {
        waitForExpectedElement(FLAVOUR_NEXT_BTN).click();
    }

    public void clickDeviceBackBtn() throws Throwable {
        scrollUpByCoordinator(250);
        waitForExpectedElement(FLAVOUR_BACK_BTN).click();
    }

    public boolean verifySelectedFlavourOptions(String option) throws Throwable {
        switch (option) {
            case "flavourOption1":
                return isChecked(FLAVOUR_QUESTION_OPTION1);
            case "flavourOption2":
                return isChecked(FLAVOUR_QUESTION_OPTION2);
            case "flavourOption3":
                return isChecked(FLAVOUR_QUESTION_OPTION3);
            case "flavourOption4":
                return isChecked(FLAVOUR_QUESTION_OPTION4);
            case "flavourOption5":
                return isChecked(FLAVOUR_QUESTION_OPTION5);
            default:
                return(false);
        }
    }

    public boolean displayAllStrengthOptions() throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
            case "vusede":
            case "vuseco":
            case "vusemx":
            case "vuseuk":
                return (isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION1_LABEL,5)
                        && isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION2,5)
                        && isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION3,5)
                        && isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION4,5)
                );
            case "vuseza":
                return (isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION1_LABEL,5)
                        && isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION2,5)
                        && isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION3,5)
                        && isElementDisplayedBySeconds(STRENGTH_QUESTION_OPTION7,5)
                );
            default:
                return false;
        }
    }

    public void selectStrengthsOptions(String strength) throws Throwable {
        if (UrlBuilder.getLocale().equalsIgnoreCase("vuseza")) {
            switch (strength) {
                case "0mg":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION4,5);
                    clickUsingJS(STRENGTH_QUESTION_OPTION4);
                    break;
                case "mild":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION5, 5).click();
                    break;
                case "big":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION6, 5).click();
                    break;
                case "notSure":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION7, 5).click();
                    break;
                default:
            }
         }
        else {
            switch (strength) {
                case "0mg":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION1,5);
                    clickUsingJS(STRENGTH_QUESTION_OPTION1);
                    break;
                case "mild":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION2, 5).click();
                    break;
                case "big":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION3, 5).click();
                    break;
                case "notSure":
                    waitForExpectedElement(STRENGTH_QUESTION_OPTION5, 5).click();
                    break;
                default:
            }
        }
    }

    public void clickFindVuseBtn() throws Throwable {
        scrollElementIntoView(STRENGTH_FIND_BTN);
        clickUsingJS(STRENGTH_FIND_BTN);
    }

    public void clickFlavourBackBtn() throws Throwable {
        scrollUpByCoordinator(250);
        waitForExpectedElement(STRENGTH_BACK_BTN).click();
    }

    public boolean verifyNotDisplayStrengthOption(String strength) {
        switch (strength) {
            case "0mg":
                return(invisibilityOfElementLocated(STRENGTH_QUESTION_OPTION1));
            case "mild":
                return(invisibilityOfElementLocated(STRENGTH_QUESTION_OPTION2));
            case "big":
                return(invisibilityOfElementLocated(STRENGTH_QUESTION_OPTION3));
            case "notSure":
                return(invisibilityOfElementLocated(STRENGTH_QUESTION_OPTION4));
            default:
                return false;
        }
    }

    public boolean isChecked(By by){
        return waitForExpectedElement(by).getAttribute("class").contains("selected");
    }

    public boolean confirmGuidedSellStart(){
        return (isElementDisplayedBySeconds(LET_GET_START_BTN,5));
    }

    public void clickLetGetStartBtn(){
        waitForExpectedElement(LET_GET_START_BTN).click();
    }

    public void clickGuidedSellMenu() {
        switch (UrlBuilder.getLocale()) {
            case "vuseuk":
                waitForExpectedElement(GUIDED_SELL_BUTTON_UK, 5).click();
                break;
            case "vusefr":
                if(UrlBuilder.isMobile())
                {
                    homePage.clickMobileHamburgerMenu();
                    waitForExpectedElement(M_GUIDED_SELL_BUTTON_FR,10).click();
                }
                else {
                    try {
                        hoverOnElement(A_PROPOS_BUTTON_FR);
                        waitForExpectedElement(GUIDED_SELL_BUTTON_FR, 5).click();
                    } catch (Exception e) {
                        clickByElementByQueryJSExecutor(GUIDED_SELL_BUTTON_FR);
                    }
                }
                break;
            case "vusede":
                waitForExpectedElement(EZIGARETTEN, 5).click();
                waitForExpectedElement(GUIDED_SELL_BUTTON_DE, 5).click();
                break;
            case "vuseco":
                waitForExpectedElement(GUIDED_SELL_BUTTON_CO, 5);
                clickFirstElementByQueryJSExecutor(GUIDED_SELL_BUTTON_CO);
                break;
            case "vusemx":
                //Below line is commented due to Bug# 1029457 (Link is removed and only way to access guided sell is to launch the url directly)
               // waitForExpectedElement(GUIDED_SELL_BUTTON_MX, 5).click();
                getWebDriver().navigate().to(getCurrentUrl() + "guided-sell");
                break;
            case "vuseza":
                hoverOnElement(ABOUT_BTN);
                waitForExpectedElement(GUIDED_SELL_BUTTON_ZA, 5).click();
                break;
            default:
        }
    }

    public void startGuidedSell(){
        switch (UrlBuilder.getLocale()) {
            case "vusefr":
            case "vuseuk":
            case "vusede":
            case "vuseco":
            case "vusemx":
            case "vuseza":
                clickUsingJS(START_GUIDED_SELL);
                break;
            default:

        }
    }

    public void clickDeferredPaymentCTA() throws Throwable {
        waitForExpectedElement(DEFERRED_PAYMENT_CTA, 10).click();
    }

    public void clickLearnMoreCTA() throws Throwable {
        waitForExpectedElement(LEARN_MORE_CTA, 10).click();
    }

    public boolean isGuidedSellPopUpDisplayed() {
        return waitForExpectedElement(GUIDED_SELL_POP_UP, 10).isDisplayed();
    }

    public void clickPopupRedirectCTA() throws Throwable {
        waitForExpectedElement(POP_UP_REDIRECT_CTA, 10).click();
    }
}