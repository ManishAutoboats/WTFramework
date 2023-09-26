package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class FreeDeviceTrialPage extends PageObject {

    public static final By SECTION_TITLE = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main > div.section-title.desktop-only");
    public static final By ACTIVE_STEP = By.cssSelector("li.active");
    public static final By DEVICE_BUTTON = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main > ul > li > button");
    public static final By DEVICE_BUTTON_SELECTED = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main > ul > li > button.action.primary");
    public static final By DEVICE_COLOUR = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main > div.product-add-form > form > div.product-options-wrapper div div.swatch-option.color");
    public static final By DEVICE_COLOUR_OPTIONS = By.cssSelector(".swatch-option.color");
    public static final By CHOOSE_FLAVOURS_BUTTON = By.cssSelector("div#device-trial-products div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main > div.product-add-form > form > div.product.actions.product-item-actions > div.actions-primary > div > button#product-addtocart-button");
    public static final By DEVICE_DESCRIPTION = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main > div.product.description.product-item-description");
    public static final By CARTRIDGE_VUSEUK = By.cssSelector(".products .product-item[data-vuse_flavour_group-id='6243']");
    public static final By CARTRIDGE_VYPEIT = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.consumable-products.device-trial-consumables.products.wrapper.grid.products-grid > ul > li");
    public static final By STRENGTH = By.cssSelector("div.swatch-option.text");
    public static final By BUTTON = By.cssSelector("button");
    public static final By CHECKOUT_BUTTON = By.cssSelector("button.action.primary.device-trial-status-button");
    public static final By PRODUCT_COLOUR_NOT_SELECTED_ERROR = By.cssSelector("div.mage-error");
    public static final By PRODUCT_IMAGE = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product.photo.product-item-photo > span > span > img");
    public static final By PRODUCT_INFO = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main");
    public static final By DISCLAIMER_INFO = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main");
    public static final By PRODUCT_DISCLAIMER = By.cssSelector("#device-trial-products > div.device-trial-product-wrap.active > div.device-trial-main-product.catalog-product-view.product-item > div > div.product-card.product-info-main > div.device-trial-disclaimer");
    public static final By LEARN_ABOUT_STRENGTHS_TEXT_LINK = By.cssSelector("#device-trial-strength-modal-trigger");
    public static final By STRENGTH_TITLE = By.cssSelector("h1.modal-title");
    public static final By STRENGTH_DESCRIPTION = By.cssSelector("#device-trial-strength-modal > div");
    public static final By CHOOSE_CARTRIDGE_HEADING = By.cssSelector("div.device-trial-status-product-text");
    public static final By CLOSE_STRENGTH_MODAL = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.device-trial-strength-modal.modal-slide._show > div.modal-inner-wrap > header > button");
    public static final By FLAVOURS_NOT_SELECTED = By.cssSelector("#device_trial_add_to_cart > ul > li.incomplete");
    public static final By FLAVOURS_SELECTED_AND_NOT_SELECTED = By.cssSelector("#device_trial_add_to_cart > ul > li");
    public static final By UPPER_MORE_TO_GO_MESSAGE = By.cssSelector("#device-trial-status > div.device-trial-status-right");
    public static final By LOWER_MORE_TO_GO_MESSAGE = By.cssSelector("#device_trial_add_to_cart > div");
    public static final By LEFT_STATUS = By.cssSelector("div#device-trial-status div.device-trial-status-left > div");
    public static final By TRIAL_STATUS_BAR = By.cssSelector("#device-trial-steps");
    public static final By MORE_THAN_MAX_ERROR_TITLE = By.cssSelector(".device-trial-max-consumables-alert .modal-title");
    public static final By MORE_THAN_ERROR_CONTENT = By.cssSelector(".device-trial-max-consumables-alert .modal-content");
    public static final By DISMISS_ERROR = By.cssSelector("body > div.modals-wrapper > aside.modal-popup.device-trial-max-consumables-alert._show > div.modal-inner-wrap > header > button");
    public static final By MAX_FLAVOURS_MESSAGE = By.cssSelector("p.note");
    public static final By VYPE_TO_VUSE_ACCEPT = By.cssSelector(".pagebuilder-button-primary.close-migration-popup");
    public static final By VIEW_ORDER_DETAILS_BUTTON = By.cssSelector("#my-orders-table > tbody > tr > td.col.actions > div > a");
    public static final By MIGRATION_POPUP = By.cssSelector("#migration-popup");
    public static final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("a.close-migration-popup");
    // ORDER DETAILS PAGE
    public static final By ORDER_NUMBER = By.cssSelector("#maincontent > div.columns > div.column.main > div.content-inner > div.order-incre-cls");
    public static final By PLACED_DATE = By.cssSelector("#maincontent > div.columns > div.column.main > div.content-inner > div.order-date");
    public static final By ORDER_STATUS = By.cssSelector("#maincontent > div.columns > div.column.main > div.content-inner > div.order-status");
    public static final By DEVICE_TRIAL_DATES = By.cssSelector("table#my-device-trial-table td");
    public static final By DEVICE_TRIAL_DETAILS_HEADING = By.cssSelector("#devicetrial_order_details > div.order-title > strong");
    public static final By TRIAL_ITEM = By.cssSelector("#my-orders-table > tbody >tr");
    public static final By TRIAL_ITEMS_HEADING = By.cssSelector("div.order-title");
    public static final By SUBTOTAL = By.cssSelector("tfoot > tr.subtotal > td.amount");
    public static final By CONSUMABLES_DISCOUNT = By.cssSelector("tfoot > tr.discount > td.amount");
    public static final By SHIPPING_AND_HANDLING = By.cssSelector("tfoot > tr.shipping > td.amount");
    public static final By TAX = By.cssSelector("tfoot > tr.totals-tax > td.amount");
    public static final By AMOUNT_PAID = By.cssSelector("td.amount[data-th='Amount Paid']");
    public static final By AMOUNT_DUE_INCLUDING_TAX = By.cssSelector("tfoot > tr.due > td.amount");
    public static final By PAYMENT_TAKEN_ON = By.cssSelector("tfoot > tr.payment-day > td.amount");
    public static final By PAYMENT_METHOD_DUE_AMOUNT = By.cssSelector("span.devicetrial_zero_formatted");
    public static final int MAX_FLAVOURS = Integer.parseInt(UrlBuilder.getMessage("maximumFlavours.key"));
    public static final By CHOOSE_YOUR_DEVICE_BANNER = By.cssSelector("div.pagebuilder-column.devicetrial-banner-text");
    public final static By CHOOSE_YOUR_DEVICE_SECTION_TITLE = By.cssSelector("div.device-trial-product-wrap.active:nth-child(1) div.product-card.product-info-main > div.section-title.desktop-only");
    public final static By DEVICE_TRIAL_TCs_SIDEBAR = By.cssSelector("#maincontent > div.columns > div.sidebar.sidebar-main > div > ul > li > ul > li:nth-child(3) > strong");
    public final static By DEVICE_TRIAL_TCs_LINK_FOOTER = By.xpath("//div[@class='desktop-only desktop-footer-links']//a[contains(text(),'Device Trial Terms & Conditions')]");
    public final static By DEVICE_TRIAL_LINK_SHOP_MENU = By.xpath("//a[contains(@href,'devicetrial')][@class='navigation-widget__link'][not(@data-unsp-sanitized)]");
    public final static By READ_MORE_LINK = By.xpath("//*[@id=\"device-trial-products\"]/div[1]/div[1]/div/div[3]/div[2]/span");
    public final static By READ_MORE_DESC = By.cssSelector(".product.description.product-item-description");
    public final static By DEVICE_TRIAL_FIRST_ORDER_NO_OPTION=By.cssSelector("div.button-div a.closePopup > span.bottom");
    public final static By DEVICE_TRIAL_FIRST_ORDER_YES_OPTION=By.cssSelector("div.button-div a.redirectHome > span.bottom");
    public final static By DEVICE_TRIAL_CONFIRMATION_POPUP=By.cssSelector("div#device-trial-confirmation-pop-up-overlay");

    int minCartridgeQuantity = Integer.parseInt(UrlBuilder.getMessage("minimumFlavours.key"));

    public LogininPage logininPage;
    public ScenarioContext scenarioContext;

    public FreeDeviceTrialPage(LogininPage logininPage, ScenarioContext scenarioContext) {
        this.logininPage = logininPage;
        this.scenarioContext = scenarioContext;
    }

    @Data
    public class DeviceTrialDates {
        Date placedDate;
        Date startDate;
        Date endDate;
        Date billingDate;
    }

    public boolean sectionTitleContains(String expectedSectionTitle) {
        return waitForExpectedElement(SECTION_TITLE).getText().contains(expectedSectionTitle);
    }

    public boolean stepIsHighlighted(String stepName, boolean highlightExpected) {
            return waitForExpectedElement(ACTIVE_STEP).getText().toLowerCase().contains(stepName.toLowerCase()) == highlightExpected;
    }

    private boolean deviceSelected(WebElement button) {
        return button.getAttribute("class").contains("primary");
    }

    public void selectDevice() {
        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        List<WebElement> deviceButtons = webDriver.findElements(DEVICE_BUTTON);
        if (deviceButtons.size() > 0) {
            clickUsingJS(deviceButtons.get(0));
        }
    }

    public void chooseColour() {
        WebElement selectedColour;
        int colourIndex = 0;
        waitForExpectedElement(DEVICE_COLOUR,15);
        List<WebElement> deviceColours = webDriver.findElements(DEVICE_COLOUR);
        for (WebElement colour: deviceColours) {
            if (colour.getAttribute("option-empty") == null) {
                if (colour.getAttribute("aria-checked").equalsIgnoreCase("false")) {
                    colour.click();
                }
                break;
            }
            colourIndex++;
        }
        selectedColour = webDriver.findElements(DEVICE_COLOUR).get(colourIndex);
        assertThat(selectedColour.getAttribute("aria-checked").equals("true"))
                .as("ERROR chooseColour: colour number "+colourIndex+" not selected").isTrue();
    }

    public void clickChooseFlavoursButton() {
        clickByElementByQueryJSExecutor(CHOOSE_FLAVOURS_BUTTON);
    }

    public void checkDescription() {
        String deviceName = waitForExpectedElement(DEVICE_BUTTON_SELECTED).getText();
        String deviceDescription = waitForExpectedElement(DEVICE_DESCRIPTION).getText();
        assertThat(deviceDescription.toLowerCase().contains(deviceName.toLowerCase()))
                .as("ERROR checkDescription: device description does not contain the device name").isTrue();
    }

    private boolean productAlreadySelected(List<WebElement> strengths) {
        for (WebElement strength: strengths) {
            if (strength.getAttribute("class").toLowerCase().contains("selected")) {
                return true;
            }
        }
        return false;
    }

    private void addNextAvailableCartridge() throws InterruptedException {
        boolean selected = false;
        List<WebElement> products = webDriver.findElements(CARTRIDGE_VUSEUK);
        switch ( UrlBuilder.getLocale()) {
            case "vypeit":
                products = webDriver.findElements(CARTRIDGE_VYPEIT);
                break;
            case "vuseuk":
                products = webDriver.findElements(CARTRIDGE_VUSEUK);
         }
        for (WebElement product: products) {
            List<WebElement> strengths = product.findElements(STRENGTH);
            if (!productAlreadySelected(strengths)) {
                for (WebElement strength : strengths) {
                    if (strength.getAttribute("option-empty") == null){
                            clickUsingJS(strength);
                            scrollToPageTop();
                            clickUsingJS(product.findElement(BUTTON));
                            selected = true;
                            break;
                    } else {
                        selected = false;
                    }
                }
            }
            if (selected) {
                break;
            }
        }
        assertThat(selected).as("ERROR addNextAvailableCartridge: no cartidges are available to add").isTrue();
    }

    public void addCartridges(int qty) throws InterruptedException {
        for (int count=0; count<qty; count++) {
            addNextAvailableCartridge();
        }
    }

    public void addMinimumFlavours(int qtyAlreadyAdded) throws InterruptedException {
        if (qtyAlreadyAdded < minCartridgeQuantity) {
            addCartridges(minCartridgeQuantity - qtyAlreadyAdded);
        }
    }

    public void chooseFlavourBeforeDeviceColour() {
        String actualErrorMessage;
        String expectedErrorMessage = UrlBuilder.getMessage("colourselectError.key");
        List<WebElement> deviceColourOptions = webDriver.findElements(DEVICE_COLOUR_OPTIONS);
        deviceColourOptions.get(0).click();
        waitForExpectedElement(CHOOSE_FLAVOURS_BUTTON).click();
        actualErrorMessage = waitForExpectedElement(PRODUCT_COLOUR_NOT_SELECTED_ERROR).getText();
        assertThat(actualErrorMessage.toLowerCase().equals(expectedErrorMessage.toLowerCase()))
                .as("ERROR chooseFlavourBeforeDeviceColour: expected error message was '"+expectedErrorMessage+"' but got '"+actualErrorMessage+"'").isTrue();
    }

    public void verifyProductImageHasLoaded() {
        assertThat(waitForExpectedElement(PRODUCT_IMAGE,10).isDisplayed());
        String altText = waitForExpectedElement(PRODUCT_IMAGE).getAttribute("alt");
        String selectedButtonText = waitForExpectedElement(DEVICE_BUTTON_SELECTED).getText();
        assertThat(altText.toLowerCase().contains(selectedButtonText.toLowerCase()))
                .as("ERROR verifyProductImageHasLoaded: product image may be incorrect. alt text is "+altText+" button text however is "+selectedButtonText)
                .isTrue();
    }

    public void validateDisclaimerForPriceInclusion() {
        String currencySymbol = UrlBuilder.getMessage("currency.key");
        String price = "0.0";
        int loopVal =0;
        String productDisclaimer = waitForExpectedElement(PRODUCT_DISCLAIMER).getText();
        String[] productDisclaimerArray = productDisclaimer.split(" ");
        for (loopVal =0; loopVal < productDisclaimerArray.length; loopVal++ )  {
            String disclaimerWord =  productDisclaimerArray[loopVal];
            if (disclaimerWord.contains(currencySymbol)) {
                switch (UrlBuilder.getLocale()) {
                    case "vuseuk":
                        price = disclaimerWord.split(currencySymbol)[1];
                        break;
                    case "vusefr":
                        price = productDisclaimerArray[loopVal-1].replace(",",".");
                        break;
                    default:
                        price = disclaimerWord.split(currencySymbol)[1];
                        break;
                }
            }
        }
        assertThat(Float.parseFloat(price) > 0.0)
                .as("ERROR validateDisclaimerForPriceInclusion: did not find a valid price in disclaimer").isTrue();
    }

    private int occurencesOfKeyword(String text, String keyword) {
        int keywordLength;
        int originalTextLength;
        int substituedTextLength;
        if (text == null) {
            return 0;
        } else {
            keywordLength = keyword.length();
            originalTextLength = text.length();
            substituedTextLength = text.replace(keyword, "").length();
            return (originalTextLength - substituedTextLength) / keywordLength;
        }
    }
    public void validateLearnAboutStrengths() {
        String strengthKeyword=UrlBuilder.getMessage("strengthKeyword.key");
        String actualStrengthTitle;
        String strengthDescription;
        String expectedStrengthTitle = UrlBuilder.getMessage("strengthsHeader.key");
        waitForExpectedElement(LEARN_ABOUT_STRENGTHS_TEXT_LINK).click();
        actualStrengthTitle = waitForExpectedElement(STRENGTH_TITLE).getText();
        assertThat(expectedStrengthTitle.equalsIgnoreCase(actualStrengthTitle))
                .as("ERROR validateLearnAboutStrengths: expected strengths modal title to be '"+expectedStrengthTitle+"' but got "+actualStrengthTitle+"'")
                .isTrue();
        strengthDescription = waitForExpectedElement(STRENGTH_DESCRIPTION).getText().toLowerCase();
        assertThat(occurencesOfKeyword(strengthDescription, strengthKeyword) > 0 )
                .as("ERROR validateLearnAboutStrengths: expected to see keyword '"+strengthKeyword+" at least once in the description").isTrue();
        LOG.info("validateLearnAboutStrengths: found "+occurencesOfKeyword(strengthDescription, strengthKeyword)+" occurences of keyword "+strengthKeyword);
    }

    public void validateStepPageHeading(String stepName) {
        String expectedHeading = "";
        String actualHeading;
        if (stepName.toLowerCase().contains("flavour packs")) {
            String expectedminimumFlavours = UrlBuilder.getMessage("minimumFlavours.key");
            expectedHeading = UrlBuilder.getMessage("trialStepTwoHeading.key").replace("%MINFLAVOUR%", expectedminimumFlavours);
            if (expectedHeading.contains("select"))
                expectedHeading = expectedHeading.replace("select","Choose");
        }
        else if (stepName.toLowerCase().contains("choose your device")){
            expectedHeading = UrlBuilder.getMessage("trialStepOneHeading.key");
        }
        else {
            expectedHeading = UrlBuilder.getMessage("trialStepThreeHeading.key");
        }
        actualHeading = waitForExpectedElement(CHOOSE_CARTRIDGE_HEADING).getText();
        if (actualHeading.contains("ePen ") )
            actualHeading=actualHeading.replace("ePen " ,"");
        else if(actualHeading.contains("ePod ")){
            actualHeading=actualHeading.replace("ePod " ,"");
        }
        assertThat(actualHeading.contains(expectedHeading))
                .as("ERROR validateStepPageHeading: expected page heading was '"+expectedHeading+"' but got '"+actualHeading+"'").isTrue();
    }

    public void closeAboutStrengthsModal() {
        waitForExpectedElement(CLOSE_STRENGTH_MODAL).click();
    }

    public void validateChosenFlavours(int actualNumberFlavoursChosen) {
        String notSelectedText = UrlBuilder.getMessage("cartridgeNotSelectedText.key");
        int displayedNumberFlavoursChosen;
        List<WebElement> emptyFlavoursSlots = webDriver.findElements(FLAVOURS_NOT_SELECTED);
        List<WebElement> allFlavoursSlots = webDriver.findElements(FLAVOURS_SELECTED_AND_NOT_SELECTED);
       displayedNumberFlavoursChosen = allFlavoursSlots.size() - emptyFlavoursSlots.size();
                assertThat(actualNumberFlavoursChosen == displayedNumberFlavoursChosen)
                .as("ERROR validateChosenFlavours: "+actualNumberFlavoursChosen+" flavours were selected but display shows "+displayedNumberFlavoursChosen+" chosen")
                .isTrue();
        for (WebElement slot: emptyFlavoursSlots) {
            assertThat(slot.getText().equalsIgnoreCase(notSelectedText))
                    .as("ERROR validateChosenFlavours: empty slot did not contain '"+notSelectedText+" instead contained "+slot.getText()).isTrue();
        }
    }

    public void validateMoreToGo(int cartridgesAdded) {
        String expectedMoreToGoMessage;
        String upperActualMoreToGoMessage;
        String lowerActualMoreToGoMessage;
        int minFlavours = Integer.parseInt(UrlBuilder.getMessage("minimumFlavours.key"));
        int moretoGo = minFlavours - cartridgesAdded;
        if (moretoGo == minFlavours) {
            expectedMoreToGoMessage = UrlBuilder.getMessage("chooseFlavoursTextNoneAdded.key").replace("%QTY%", String.valueOf(minFlavours));
        } else if (moretoGo == 0) {
            expectedMoreToGoMessage = UrlBuilder.getMessage("chooseFlavoursTextMinAdded.key");
        } else {
            expectedMoreToGoMessage = UrlBuilder.getMessage("chooseFlavoursText.key").replace("%QTY%", String.valueOf(cartridgesAdded));
        }
        upperActualMoreToGoMessage = waitForExpectedElement(UPPER_MORE_TO_GO_MESSAGE).getText();
        assertThat(expectedMoreToGoMessage.equalsIgnoreCase(upperActualMoreToGoMessage))
                .as("ERROR validateMoreToGo: expected upper more to go was '"+expectedMoreToGoMessage+"' but got "+upperActualMoreToGoMessage).isTrue();
        lowerActualMoreToGoMessage = waitForExpectedElement(LOWER_MORE_TO_GO_MESSAGE).getText();
        assertThat(expectedMoreToGoMessage.equalsIgnoreCase(lowerActualMoreToGoMessage))
                .as("ERROR validateMoreToGo: expected lower more to go was '"+expectedMoreToGoMessage+"' but got "+lowerActualMoreToGoMessage).isTrue();
        validateLeftStatus(cartridgesAdded, moretoGo);
    }

    private void validateLeftStatus(int cartridgesAdded, int moreToGo) {
        String expectedLeftStatus;
        String actualLeftStatus;
        if (cartridgesAdded == 0) {
            expectedLeftStatus = UrlBuilder.getMessage("leftStatusNoneChosen.key") + " " + Integer.parseInt(UrlBuilder.getMessage("minimumFlavours.key"));
        } else if (moreToGo > 0) {
            expectedLeftStatus = UrlBuilder.getMessage("leftStatusLessThanMinQtyChosen.key").replace("%QTY%",String.valueOf(moreToGo));
        } else {
            expectedLeftStatus = UrlBuilder.getMessage("leftStatusMinQty.key");
            if (UrlBuilder.getMessage("leftStatusMinQty.key").contains("chosen"))
                expectedLeftStatus = UrlBuilder.getMessage("leftStatusMinQty.key").replace("%QTY%",UrlBuilder.getMessage("minimumFlavours.key"));
        }
        actualLeftStatus = waitForExpectedElement(LEFT_STATUS).getText();
        assertThat(actualLeftStatus.toLowerCase().contains(expectedLeftStatus.toLowerCase()))
                .as("ERROR validateLeftStatus: expected left status to contain '"+expectedLeftStatus+"' but found '"+actualLeftStatus).isTrue();
    }

    public void validateCheckoutButton() {
        String expectedCheckoutButtonText = UrlBuilder.getMessage("checkoutButtonText.key");
        String actualCheckoutButtonText = waitForExpectedElement(CHECKOUT_BUTTON).getText();
        assertThat(expectedCheckoutButtonText.toLowerCase().equals(actualCheckoutButtonText.toLowerCase()))
                .as("ERROR validateCheckoutButton: expected checkout button text was '"+expectedCheckoutButtonText+"' but got '"+actualCheckoutButtonText+"'").isTrue();
    }

    public void addMaximumFlavours(int numberAlreadyAdded) throws InterruptedException {
        int qtyToAdd = MAX_FLAVOURS - numberAlreadyAdded;
        addCartridges(qtyToAdd);
    }

    public void validateMoreThanMaxError() {
        String actualErrorTitle;
        String actualErrorContent;
        String expectedErrorTitle;
        String expectedErrorContent;
        actualErrorTitle = waitForExpectedElement(MORE_THAN_MAX_ERROR_TITLE).getText();
        actualErrorContent = waitForExpectedElement(MORE_THAN_ERROR_CONTENT).getText();
        expectedErrorTitle = UrlBuilder.getMessage("moreThanMaxErrorTitle.key");
        String expectedMaximumFlavours = UrlBuilder.getMessage("maximumFlavours.key");
        expectedErrorContent = UrlBuilder.getMessage("moreThanMaxErrorContent.key").replace("%MINFLAVOUR%", expectedMaximumFlavours);
        assertThat(actualErrorTitle.equalsIgnoreCase(expectedErrorTitle))
                .as("ERROR expected error title was '"+expectedErrorTitle+"' but got '"+actualErrorTitle+"'").isTrue();
        assertThat(actualErrorContent.equalsIgnoreCase(expectedErrorContent))
                .as("ERROR expected error content was '"+expectedErrorContent+"' but got '"+actualErrorContent+"'").isTrue();
    }

    public void dismissError() {
        waitForExpectedElement(DISMISS_ERROR).click();
    }

    public void checkMaxFlavoursMessage() {
        String actualMaxFlavoursMessage = waitForExpectedElement(MAX_FLAVOURS_MESSAGE).getText();
        String expectedMaxFlavoursMessage = UrlBuilder.getMessage("maxFlavoursMessage.key") + " " + UrlBuilder.getMessage("maximumFlavours.key");
        assertThat(actualMaxFlavoursMessage.equalsIgnoreCase(expectedMaxFlavoursMessage))
                .as("ERROR checkMaxFlavoursMessage: expected max flavours message was '"+expectedMaxFlavoursMessage+"' but got '"+actualMaxFlavoursMessage+"'").isTrue();
    }

    public void clickCheckoutButton() {
        clickByElementByQueryJSExecutor(CHECKOUT_BUTTON);
    }

    public void assertThatRegisteredMessageIsDisplayed() {
        waitForExpectedElement(logininPage.MESSAGES_BLOCK, 10);
        String actualMessage = waitForExpectedElement(logininPage.MESSAGES_BLOCK, 10).getText();
        String expectedMessage = UrlBuilder.getMessage("needToRegisterMessage.key");
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case VUSEUK:
            case VUSEFR:
                assertThat(actualMessage.contains(expectedMessage))
                        .as("ERROR assertThatRegisteredMessageIsDisplayed: expected message was '" + expectedMessage + " but got '" + actualMessage + "'").isTrue();
                break;
            default:
                assertThat(expectedMessage.equalsIgnoreCase(actualMessage))
                        .as("ERROR assertThatRegisteredMessageIsDisplayed: expected message was '" + expectedMessage + " but got '" + actualMessage + "'").isTrue();
        }
    }

    public void confirmUserOnLoginPage() {
        assertThat(webDriver.getCurrentUrl().contains("/customer/account/login"))
                .as("ERROR confirmUserOnLoginPage: user is not on the login page").isTrue();
    }

    public void viewLatestOrder() {
        if (webDriver.findElements(MIGRATION_POPUP).size()>0) {
            waitForExpectedElement(CONTINUE_SHOPPING_BUTTON).click();
        }
        webDriver.findElements(VIEW_ORDER_DETAILS_BUTTON).get(0).click();
    }

    public void validateOrderDetails(int numberOfCartridgesAdded) {
        validateOrderSummary();
        validateTrialDetails();
        switch (UrlBuilder.getLocale()) {
            case "vuseuk":
                validateTrialItems(numberOfCartridgesAdded);
                validateTotals();
        }

    }

    public void deviceInfoSection() {
        assertThat(waitForExpectedElement(PRODUCT_INFO,10).isDisplayed());
    }

    public void trialStatusBarSection() {
        assertThat(waitForExpectedElement(TRIAL_STATUS_BAR,10).isDisplayed());
    }

    public void trialDisclaimerDetails() {
        assertThat(waitForExpectedElement(DISCLAIMER_INFO,10).isDisplayed());

    }

    private Date addDaysToDate(Date oldDate, int daystoAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DAY_OF_MONTH, daystoAdd);
        return calendar.getTime();
    }

    private DeviceTrialDates evaluateDeviceTrialDates(Date placedDate) {
        int deliveryWindow = Integer.parseInt(UrlBuilder.getMessage("deliveryWindow.key"));
        int trialDuration = Integer.parseInt(UrlBuilder.getMessage("trialDuration.key"));
        int gracePeriod = Integer.parseInt(UrlBuilder.getMessage("gracePeriod.key"));
        int billingWindow = Integer.parseInt(UrlBuilder.getMessage("BillingWindow.key"));
        DeviceTrialDates deviceTrialDates = new DeviceTrialDates();
        deviceTrialDates.setPlacedDate(placedDate);
        Date startDate = addDaysToDate(placedDate, deliveryWindow);
        deviceTrialDates.setStartDate(startDate);
        Date endDate = addDaysToDate(startDate, trialDuration);
        deviceTrialDates.setEndDate(endDate);
        Date billingdate = addDaysToDate(endDate, gracePeriod+billingWindow);
        deviceTrialDates.setBillingDate(billingdate);
        return deviceTrialDates;
    }

    public void validateOrderSummary() {
        Date placedDate = new Date();
        String actualOrderNumber = waitForExpectedElement(ORDER_NUMBER).getText();
        String actualPlacedDate = waitForExpectedElement(PLACED_DATE).getText();
        String actualStatus = waitForExpectedElement(ORDER_STATUS).getText();
        String expectedOrderNumber = (String) scenarioContext.getContext(Context.ORDER_NUMBER);
        SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy");
        String expectedPlacedDate = format.format(placedDate);
        String expectedStatusDeliveryPending = UrlBuilder.getMessage("orderStatusDeliveryPending.key");
        String expectedStatusAwaitingShipment = UrlBuilder.getMessage("orderStatusAwaitingShipment.key");
        assertThat(actualOrderNumber.contains(expectedOrderNumber))
                .as("ERROR validateOrderSummary: expected order number was '"+expectedOrderNumber+"' but actual contained '"+actualOrderNumber+"'").isTrue();
        assertThat(actualStatus.contains(expectedStatusDeliveryPending) || actualStatus.contains(expectedStatusAwaitingShipment))
                .as("ERROR validateOrderSummary: expected status was '"+expectedStatusAwaitingShipment+"' or '"+expectedStatusDeliveryPending+"'  but actual contained '"+actualStatus+"'").isTrue();
    }

    public void validateTrialDates(){
        Date placedDate = new Date();
        DeviceTrialDates deviceTrialDates = evaluateDeviceTrialDates(placedDate);
        SimpleDateFormat format = new SimpleDateFormat(UrlBuilder.getMessage("trialDateFormat.key"));
        String expectedStartDate = format.format(deviceTrialDates.getStartDate());
        String expectedEndDate = format.format(deviceTrialDates.getEndDate());
        String expectedBillingDate = format.format(deviceTrialDates.getBillingDate());
        String actualStartDate = webDriver.findElements(DEVICE_TRIAL_DATES).get(0).getText();
        String actualEndDate = webDriver.findElements(DEVICE_TRIAL_DATES).get(1).getText();
        String actualDuration = webDriver.findElements(DEVICE_TRIAL_DATES).get(2).getText();
        String actualBillingDate = webDriver.findElements(DEVICE_TRIAL_DATES).get(3).getText();
        assertThat(expectedStartDate.equals(actualStartDate))
                .as("ERROR validateTrialDetails: expected start date was '"+expectedStartDate+"' but got '"+actualStartDate+"'").isTrue();
        assertThat(expectedEndDate.equals(actualEndDate))
                .as("ERROR validateTrialDetails: expected end date was '"+expectedEndDate+"' but got '"+actualEndDate+"'").isTrue();
    }

    public void validateTrialDuration(){
        String expectedTrialDuration = UrlBuilder.getMessage("trialDuration.key");
        String expectedDuration = expectedTrialDuration + " " + UrlBuilder.getMessage("trialdays.key");
        String actualDuration = webDriver.findElements(DEVICE_TRIAL_DATES).get(2).getText();
        assertThat(expectedDuration.equals(actualDuration))
                .as("ERROR validateTrialDuration: expected duration was '"+expectedDuration+"' but got '"+actualDuration+"'").isTrue();
    }

     private void validateTrialDetails() {
        Date placedDate = new Date();
        validateTrialDuration();
        validateTrialDates();
        String expectedTrialDuration = UrlBuilder.getMessage("trialDuration.key");
        String expectedDeviceTrialDetailsHeading = UrlBuilder.getMessage("trialDetailsHeading.key").replace("%DURATION%", expectedTrialDuration);
        String actualDeviceTrialDetailsHeading = waitForExpectedElement(DEVICE_TRIAL_DETAILS_HEADING).getText();
        assertThat(expectedDeviceTrialDetailsHeading.equalsIgnoreCase(actualDeviceTrialDetailsHeading))
                .as("ERROR validateTrialDetails: expected device trial details heading was '"+expectedDeviceTrialDetailsHeading+"' but got '"+actualDeviceTrialDetailsHeading+"'").isTrue();
//        assertThat(expectedBillingDate.equals(actualBillingDate))
//                .as("ERROR validateTrialDetails: expected billing date was '"+expectedBillingDate+"' but got '"+actualBillingDate+"'").isTrue();
    }

    private void validateTrialItems(int numberOfCartridgesAdded) {
        float deviceCost= (float) 0.0;
        float cartridgeCost= (float) 0.0;
        int actualDeviceCount = 0;
        int actualCartridgeCount=0;
        int currentQuantity=0;
        String currentItem="";
        String trialItemName;
        String cellContent;
        String actualTrialItemsHeading;
        String currencySymbol = UrlBuilder.getMessage("currency.key");
        String expectedTrialItemsHeading = UrlBuilder.getMessage("trialItemsHeading.key");
        int expectedDeviceQuantity = Integer.parseInt(UrlBuilder.getMessage("deviceQuantity.key"));
        int maxCartridgeQuantity = Integer.parseInt(UrlBuilder.getMessage("maximumFlavours.key"));
        List<WebElement> trialItemRows = webDriver.findElements(TRIAL_ITEM);
        for (WebElement trialItemRow: trialItemRows) {
            List<WebElement> trialItemColumns = trialItemRow.findElements(By.tagName("td"));
            for (WebElement trialItemColumn: trialItemColumns) {
                cellContent = trialItemColumn.getText();
                if (cellContent.toLowerCase().contains("device")) {
                    currentItem = "device";
                } else if (cellContent.toLowerCase().contains("cartridge")) {
                    currentItem = "cartridge";
                } else if (cellContent.toLowerCase().contains("qty")) {
                    currentQuantity = Integer.parseInt(cellContent.replaceAll("\\D+",""));
                } else if (cellContent.contains(UrlBuilder.getMessage("currency.key"))) {
                    if (currentItem.equals("device")) {
                        actualDeviceCount = actualDeviceCount + currentQuantity;
                        deviceCost = deviceCost + Float.parseFloat(cellContent.replaceAll(currencySymbol,""));
                    } else {
                        actualCartridgeCount = actualCartridgeCount + currentQuantity;
                        cartridgeCost = cartridgeCost + Float.parseFloat(cellContent.replaceAll(currencySymbol,""));
                    }
                    currentItem = "";
                }
            }
        }
       actualTrialItemsHeading = webDriver.findElements(TRIAL_ITEMS_HEADING).get(1).getText();
        assertThat(expectedTrialItemsHeading.toLowerCase().equals(actualTrialItemsHeading.toLowerCase()))
                .as("ERROR validateTrialItems: expected trial items heading was '"+expectedTrialItemsHeading+"' but got '"+actualTrialItemsHeading+"'").isTrue();
        assertThat(actualDeviceCount == 1)
                .as("ERROR validateTrialItems: expected device quantity to be 1 but got '"+actualDeviceCount+"'").isTrue();
        assertThat(actualCartridgeCount <= maxCartridgeQuantity && actualCartridgeCount >= minCartridgeQuantity)
                .as("ERROR validateTrialItems: expected cartridge quantity to be between '"+minCartridgeQuantity+"' and '"+maxCartridgeQuantity+"' but got "+actualCartridgeCount).isTrue();
    }

    public void clickCreateNewAccount() {
        logininPage.clickCreateAccountButton();
    }

    public void acceptVypeToVuse() {
        waitForExpectedElement(VYPE_TO_VUSE_ACCEPT).click();
    }

    public void assertThatFirstTimeOnlyTrialMessageIsDisplayed() {
        waitForExpectedElement(logininPage.MESSAGES_BLOCK, 30);
        String actualMessage = waitForExpectedElement(logininPage.MESSAGES_BLOCK).getText();
        String expectedMessage = UrlBuilder.getMessage("existingUserTrialMessage.key");
        assertThat(actualMessage.contains(expectedMessage))
                .as("ERROR assertThatFirstTimeOnlyTrialMessageIsDisplayed: expected message was '"+expectedMessage+" but got '"+actualMessage+"'").isTrue();
    }

    private float convertWebElementToFloat(WebElement webElement) {
        return Float.parseFloat(webElement.getText().replace(UrlBuilder.getMessage("currency.key"),""));
    }

    public void validateTotals() {
        Date placedDate = new Date();
        float consumablesDiscount = 0;
        float subtotal = 0;
        switch (UrlBuilder.getLocale()) {
            case "vuseuk":
                consumablesDiscount = convertWebElementToFloat(waitForExpectedElement(CONSUMABLES_DISCOUNT));
                subtotal = convertWebElementToFloat(waitForExpectedElement(SUBTOTAL));
                break;
            case "vusefr":
                subtotal = Float.parseFloat(waitForExpectedElement(SUBTOTAL).getText().replace(",", "."));
                break;
        }
        float shippingAndHandling = convertWebElementToFloat(waitForExpectedElement(SHIPPING_AND_HANDLING));
        float tax = convertWebElementToFloat(waitForExpectedElement(TAX));
        float actualAmountPaid = convertWebElementToFloat(waitForExpectedElement(AMOUNT_PAID));
        float actualAmountDueIncludingTax = convertWebElementToFloat(waitForExpectedElement(AMOUNT_DUE_INCLUDING_TAX));
        String actualPaymentTakenOn = waitForExpectedElement(PAYMENT_TAKEN_ON).getText();
        float expectedAmountPaid = 0;
        float expectedAmountDueIncludingTax = subtotal + shippingAndHandling + consumablesDiscount;
        float expectedAmountDueIncludingTaxDecimal = BigDecimal.valueOf(expectedAmountDueIncludingTax).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
        DeviceTrialDates deviceTrialDates = evaluateDeviceTrialDates(placedDate);
        String expectedPaymentTakenOn = format.format(deviceTrialDates.getBillingDate());
        assertThat(expectedPaymentTakenOn.equals(actualPaymentTakenOn))
                .as("ERROR validateTotals: expected payment taken on date was '" + expectedPaymentTakenOn + "' but got '" + actualPaymentTakenOn + "'").isTrue();
        assertThat(actualAmountPaid == expectedAmountPaid)
                .as("ERROR validateTotals: expected amount paid was '" + expectedAmountPaid + "' but got '" + actualAmountPaid + "'").isTrue();
        assertThat(actualAmountDueIncludingTax == expectedAmountDueIncludingTaxDecimal)
                .as("ERROR validateTotals: expected amount due is '" + expectedAmountDueIncludingTaxDecimal + "' but got '" + actualAmountDueIncludingTax + "'").isTrue();
    }

    public void assertThatMixedBasketErrorMessageIsDisplayed() {
        waitForExpectedElement(logininPage.MESSAGES_ERROR, 60);
        String actualMessage="";
        String expectedMessage = UrlBuilder.getMessage("mixedTrailAndNonTrialBasketMessage.key");
        switch (Locale.valueOf(getLocale().toUpperCase())) {
            case VUSEUK:
            case VUSEFR:
                actualMessage = waitForExpectedElement(By.cssSelector("div.product-details div.message-error")).getText();
                assertThat(actualMessage.contains(expectedMessage))
                        .as("ERROR expected message was '" + expectedMessage + " but got '" + actualMessage + "'").isTrue();
                break;
            default:
                actualMessage = waitForExpectedElement(logininPage.MESSAGES_ERROR).getText();
                assertThat(expectedMessage.equalsIgnoreCase(actualMessage))
                        .as("ERROR expected message was '" + expectedMessage + " but got '" + actualMessage + "'").isTrue();
        }
    }

    public void assertThatTwoTrialNotPossibleMessageIsDisplayed() {
        waitForExpectedElement(logininPage.MESSAGES_BLOCK, 10);
        String actualMessage = waitForExpectedElement(logininPage.MESSAGES_BLOCK).getText();
        String expectedMessage = UrlBuilder.getMessage("NoTwoTrialItemsonBasket.key");
        assertThat(expectedMessage.equalsIgnoreCase(actualMessage))
                .as("ERROR assertThatTwoTrialNotPossibleMessageIsDisplayed: expected message was '"+expectedMessage+" but got '"+actualMessage+"'").isTrue();
    }

    public void assertThatLimitedTrialMessageIsDisplayed() {
        waitForExpectedElement(logininPage.MESSAGES_BLOCK, 10);
        String actualMessage = waitForExpectedElement(logininPage.MESSAGES_BLOCK).getText();
        String expectedMessage = UrlBuilder.getMessage("limitedTrialMessage.key");
        switch (Locale.valueOf(getLocale().toUpperCase())) {
            case VUSEFR:
            case VUSEUK:
                assertThat(actualMessage.contains(expectedMessage))
                        .as("ERROR: expected message was '" + expectedMessage + " but got '" + actualMessage + "'").isTrue();
                break;
            default:
                assertThat(expectedMessage.equalsIgnoreCase(actualMessage))
                        .as("ERROR assertThatTwoTrialNotPossibleMessageIsDisplayed: expected message was '" + expectedMessage + " but got '" + actualMessage + "'").isTrue();
        }
    }

    public void verifyTrialPaymentMethodDetails() throws IllegalStateException {
        String trialAmount;
        switch (UrlBuilder.getLocale()) {
            case "vuseuk":
                trialAmount = "£0.00";
                break;
            case "vypeit":
            case "vusefr":
                trialAmount = "0,00 €";
                break;
            default:
                throw new IllegalStateException("Unexpected locale & Currency conflict");
        }
        assertThat(waitForExpectedElement(PAYMENT_METHOD_DUE_AMOUNT).getText().contains(trialAmount))
                .as("ERROR Trial Payment method: Payment due today value incorrect").isTrue();

    }

    public void freeDeviceTrialLogin() {
        String userLoginid = (String) scenarioContext.getContext(Context.EMAIL_ID);
        logininPage.login(userLoginid,UrlBuilder.getMessage("loginValidPassword.key"));
    }

    public void clickOnLinkFromInformationSectionAndAssertNavigation(String strExpectedLink,String strExpectedURL) {
        clickByElementByQueryJSExecutor(By.linkText(strExpectedLink));
        assertTrue(doesURLContain(UrlBuilder.getMessage(strExpectedURL)));
    }

}