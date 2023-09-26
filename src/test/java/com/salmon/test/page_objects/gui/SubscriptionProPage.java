package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubscriptionProPage extends PageObject {
    public String discountText;
    public String currentAddressName;

    /** SELECTORS */
    private By subscriptionBlock = By.cssSelector("div.collapsible-subscription-block");
    private By subscriptionBlockOpen = By.cssSelector("div.collapsible-subscription-block.show");
    private By subscriptionBlockLis = By.cssSelector("div.collapsible-subscription-block.show ul li");
    private By pdpSubscriptionContainer = By.cssSelector("div.subscription-container");
    private By pdpSubscriptionChoices = By.cssSelector("div.subscription-container div.choice");
    private By pdpOneTimePurchaseChoice = By.cssSelector("div.choice label[for='option_oneTimePurchase']");
    private By pdpOneTimePurchaseChecked = By.cssSelector("input[id='option_oneTimePurchase']:checked");
    private By pdpSubscribeAndSaveChoice = By.cssSelector("div.choice label[for='option_subscription']");
    private By pdpSubscribeAndSaveChecked = By.cssSelector("input[id='option_subscription']:checked");
    private By subscriptionMcLbl = By.cssSelector(".sliding-panel-sections .subscriptionpro-label");
    private By oneTimePurchaseMcLbl = By.cssSelector(".sliding-panel-sections dl.options dd.values span");
    private By cartOpened = By.cssSelector(".sliding-minicart:not([style*='none'])");
    private By checkoutMinQuantityError = By.cssSelector("#checkout div.messages:not([style*='none']) .error");
    private By checkoutCardPaymentRadio = By.cssSelector(".payment-method #worldpay_cc");
    private By subscriptionWarning = By.cssSelector(".warning");
    private By confirmationPopupOkBtn = By.cssSelector(".confirm._show .action-accept");
    private By saveAddressBtn = By.cssSelector("._show .action-update-shipping-address");
    private By messageContainer = By.cssSelector("#subscriptions-container .messages:not([style*='none']) div.message");
    public By successMsgUpdate = By.cssSelector("#subscriptions-container .messages:not([style*='none']) .success");
    public By successTopMsg = By.cssSelector(".messages:not([style*='none']) .success");
    private By qtySelectbox = By.cssSelector("select[data-bind*='selectedQty']");
    public By skipDeliveryButton = By.cssSelector("#subscriptions-container button[data-bind*=\"skip\"]");
    public By cancelButton = By.cssSelector("td.subscription-actions a[data-bind*=\"Cancel\"]");
    public By removeLink = By.cssSelector("tr[class*='subscribepro-item']:nth-child(1) a[data-bind*='Remove']");
    private By calendarDeliveryImg = By.cssSelector("#subscriptions-container img.ui-datepicker-trigger");
    private By myAccountAddSubscrBtn = By.cssSelector("button#add-subscription-button");
    private By myAccSelectProduct = By.cssSelector("aside._show #add_subscription_product");
    private By myAccSelectProductQty = By.cssSelector("aside._show #add_subscription_qty");
    private By myAccSubmitBtn = By.cssSelector("aside._show button.action-accept");
    public By subscrMyAccountErrorMsg = By.cssSelector("#subscriptions-container div.messages:not([style*='none']) .error");
    public static final By subscrPDPErrorMsg = By.cssSelector("div.messages:not([style*='none']) .error");
    private By loadingMask = By.cssSelector(".loading-mask:not([style*='none'])");
    private By feePopupAcceptButton = By.cssSelector("._show button.action-accept");
    public By productDiscountParagraph = By.cssSelector(".subscription-price");
    public By productContainer = By.cssSelector(".subscribepro-item");
    public By deliveryAddressContainer = By.cssSelector(".delivery-address address");
    private By checkoutProducts = By.cssSelector(".items-in-cart.active .product-item-name");
    private By modifyDeliveryAddressLink = By.cssSelector("a[data-trigger*='change-shipping-address']");
    private By deliveryDateContainer = By.cssSelector("div.next-ship-date");
    public final static By SUBS_PRO_DISCOUNT_TEXT = By.cssSelector("tr.totals.discount span.title");
    public final static By SUBS_PRO_FREE_DEVICE_TEXT = By.cssSelector("#opc-sidebar > div.opc-block-summary > table > tbody > tr.totals.discount > th > span.title");
    private final static By MINICART_BACK_BUTTON = By.cssSelector("button#btn-minicart-close");
    private final static By SUBSSCRIBE_AND_SAVE = By.cssSelector("input#option_subscription");
    private final static By SUBSCRIPTION_MINIMUM_QTY_ERROR= By.cssSelector("div.message.message-notice.notice");
    private final static By PRODUCT_QUANTITY_VIEW_BASKET=By.cssSelector("[title='Qty']");
    private final static By QUANTITY_INCREASE_BUTTON=By.cssSelector("div.cart-actions div.field.qty div.control.qty > input:nth-child(3)");
    private final static By CUSTOM_MESSAGE_SUB=By.cssSelector("#custom-message-subs");

    public boolean isSubscriptionBlockPresent(){
        return waitForExpectedElement(
                subscriptionBlock,30
        ).isDisplayed();
    }

    public boolean isSubscriptionLabelOnProductPresent(){
        String currentProductUrl = UrlBuilder.getMessage("subscribeProProductPath");

        return waitForExpectedElement(
                By.cssSelector("div.subscriptionpro-label-wrap a[href*='" + currentProductUrl + "']")
                ,10
        ).isDisplayed();
    }

    public boolean isSubscriptionContainerPresent(){
        return waitForExpectedElement(
            pdpSubscriptionContainer,30
        ).isDisplayed();
    }

    public boolean isErrorMessagePresent(){
        return waitForExpectedElement(
                pdpSubscriptionContainer,30
        ).isDisplayed();
    }

    public boolean isOneTimePurchaseChecked(){
        return waitForExpectedElement(
            pdpOneTimePurchaseChecked,30
        ).isDisplayed();
    }

    public boolean isOneTimeLblVisibleInCart(){
        return waitForExpectedElement(
            oneTimePurchaseMcLbl,30
        ).isDisplayed();
    }

    public boolean isSubscriptionLblVisibleInCart(){
        return waitForExpectedElement(
            subscriptionMcLbl,30
        ).isDisplayed();
    }

    public boolean isSubscribeAndSaveChecked(){
        return waitForExpectedElement(
            pdpSubscribeAndSaveChecked,30
        ).isDisplayed();
    }

    public boolean isFreeDeviceDisplayed(){
        return waitForExpectedElement(SUBS_PRO_FREE_DEVICE_TEXT).getText().contains("Sigaretta elettronica gratis");
    }

    public void openSubscriptionBlock(){
        waitForExpectedElement(
            subscriptionBlock, 10
        ).click();
    }

    public void selectSubscriptionProduct(String path){
        LOG.info("\n **** PATH : " + path);
        LOG.info("\n *** Is product visible : " + waitForExpectedElement(
            By.cssSelector("div.product a.product-item-link[href*='" + path + "']")).isDisplayed());
        waitForExpectedElement(
                By.cssSelector("div.product a.product-item-link[href*='" + path + "']"),
                30
        ).click();
    }

    public void selectProductWithHref(String path){
        waitForExpectedElement(
                By.cssSelector("div.product a.product-item-link[href*='" + path + "']"),
                10
        ).click();
    }

    public void selectSubscribeAndSave() throws Throwable{
        if (isElementDisplayedBySeconds(MINICART_BACK_BUTTON,2)) {
            try {
                waitForExpectedElement(MINICART_BACK_BUTTON, 10).click();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
        }
        if(!waitForExpectedElement(SUBSSCRIBE_AND_SAVE, 10).isSelected()){
            clickByElementByQueryJSExecutor(pdpSubscribeAndSaveChoice);
        }
    }

    public void selectOneTimePurchase(){
        if (!isElementPresentByby(pdpOneTimePurchaseChoice))
            waitForExpectedElement(
                pdpOneTimePurchaseChoice, 10
            ).click();
    }

    public String subscriptionBlockTitle(){
        return waitForExpectedElement(
                subscriptionBlock, 10
        ).getText();
    }

    public List<WebElement> subscriptionBlockOptions(){
        List<WebElement> lists = getWebDriver().findElements(subscriptionBlockLis);
        return lists;
    }

    public List<WebElement> pdpSubscriptionContainerChoices(){
        waitForExpectedElement(
                pdpSubscriptionContainer,30
        );

        List<WebElement> lists = getWebDriver().findElements(pdpSubscriptionChoices);

        return lists;
    }

    public List<WebElement> currentProductInCart(){
        waitForExpectedElement(
            cartOpened,30
        );

        String currentProductUrl = UrlBuilder.getMessage("subscribeProProductPath");
        By subscriptionProdInCart = By.cssSelector(
            ".sliding-panel-sections .product-item-name a[href*='"+ currentProductUrl +"']"
        );
        List<WebElement> lists = getWebDriver().findElements(subscriptionProdInCart);

        return lists;
    }

    public WebElement subscriptionCheckoutError(){
        return waitForExpectedElement(checkoutMinQuantityError, 10);
    }

    public WebElement subscriptionMyAccountError(){
        return waitForExpectedElement(subscrMyAccountErrorMsg, 10);
    }

    public WebElement subscriptionPDPError(){
        return waitForExpectedElement(subscrPDPErrorMsg, 10);
    }

    public void cancelSubscription(){
        clickByElementByQueryJSExecutor(cancelButton);
    }

    public void saveAddress(){
        waitForExpectedElement(saveAddressBtn).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(saveAddressBtn));
    }

    public void waitAndAcceptFeeFromPopup() {
        waitForExpectedElement(feePopupAcceptButton);
        clickByElementByQueryJSExecutor(feePopupAcceptButton);
        waitForExpectedElement(checkoutCardPaymentRadio,50);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMask));
    }

    public Boolean discountIsDisplayed() {
        if (!isElementPresentByby(qtySelectbox))
            waitForExpectedElement(qtySelectbox);

        final String regex = "\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";
        String currentDiscountText = waitForExpectedElement(productDiscountParagraph).getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(currentDiscountText);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        if (count == 2)
            return true;

        return false;
    }

    public boolean isDiscountTextDifferent() {
        Boolean isDiscountDifferent = true;
        if (isElementPresentByby(productDiscountParagraph)) {
            if (waitForExpectedElement(productDiscountParagraph).getText().equals(discountText))
                isDiscountDifferent = false;
            discountText = waitForExpectedElement(productDiscountParagraph).getText();
        }

        return isDiscountDifferent;
    }

    public void clickModifyDeliveryAddress() {
        currentAddressName = waitForExpectedElement(deliveryAddressContainer).getText();
        waitForExpectedElement(modifyDeliveryAddressLink).click();
        waitForExpectedElement(saveAddressBtn);
    }

    public void updateSubscrQtyInProductPage(String qty){
        if (isElementPresentByby(subscrMyAccountErrorMsg))
            invisibilityOfElementLocated(subscrMyAccountErrorMsg, 10);

        waitForExpectedElement(qtySelectbox);
        selectValueFromDropDownByby(qty, qtySelectbox);

        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(messageContainer),
                        ExpectedConditions.visibilityOfElementLocated(feePopupAcceptButton),
                        ExpectedConditions.invisibilityOfElementLocated(loadingMask)
                )
        );
    }

    public void skipDelivery(Integer times){
        for(int i = 0; i < times; ++i) {
            wait.until(
                    ExpectedConditions.and(
                            ExpectedConditions.invisibilityOfElementLocated(confirmationPopupOkBtn),
                            ExpectedConditions.invisibilityOfElementLocated(loadingMask)
                    )
            );

            if (i < 5)
                clickByElementByQueryJSExecutor(skipDeliveryButton);

            wait.until(
                    ExpectedConditions.or(
                        ExpectedConditions.presenceOfElementLocated(confirmationPopupOkBtn),
                        ExpectedConditions.presenceOfElementLocated(messageContainer)
                    )
            );

            if (isElementPresentByby(confirmationPopupOkBtn))
                waitForExpectedElement(confirmationPopupOkBtn).click();
        }
    }

    public void removeFirstItemSubscription(){
        waitForExpectedElement(removeLink).click();
    }

    public List<WebElement> subscriptionsByDeliveryDate(Integer numberOfMonths){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1+numberOfMonths);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = cal.getTime();

        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.presenceOfElementLocated(subscriptionWarning),
                        ExpectedConditions.presenceOfElementLocated(skipDeliveryButton)
                )
        );

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMask));

        List<WebElement> subs = webDriver.findElements(
                By.xpath("//div[@class='next-ship-date' and contains(.,'" + format.format(dt) + "')]")
        );

        return subs;
    }

    public Boolean productsInCheckout(Integer expectedNumber){
        List<WebElement> subs = webDriver.findElements(checkoutProducts);

        return expectedNumber == subs.size();
    }

    public Boolean subscriptionExists(Integer numberOfMonths){
        return !subscriptionsByDeliveryDate(numberOfMonths).isEmpty();
    }

    public Boolean subscriptionExists(){
        return subscriptionExists(0);
    }

    public Integer numberOfExistingProductSubscriptions(){
        wait.until(
                        ExpectedConditions.invisibilityOfElementLocated(loadingMask)
        );

        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.presenceOfElementLocated(subscriptionWarning),
                        ExpectedConditions.presenceOfElementLocated(skipDeliveryButton)
                )
        );

        return isElementPresentByby(productContainer) ?
                webDriver.findElements(productContainer).size() : 0;
    }

    public void clickAddANewSubscriptionBtn(){
        waitForExpectedElement(myAccountAddSubscrBtn, 10).click();
        waitForExpectedElement(myAccSelectProduct);
    }

    public void fillAndSubmitSubscriptionForm(String numberOfProducts){
        List<String> selects = getAllSelectOptions(myAccSelectProduct);

        Random rand = new Random();
        int list = rand.nextInt(selects.size());

        selectValueFromDropDownByby(selects.get(list), myAccSelectProduct);

        selectValueFromDropDownByby(numberOfProducts, myAccSelectProductQty);

        clickByElementByQueryJSExecutor(myAccSubmitBtn);

        wait.until(
                ExpectedConditions.and(
                        ExpectedConditions.invisibilityOfElementLocated(loadingMask),
                        ExpectedConditions.presenceOfElementLocated(skipDeliveryButton)
                )
        );
    }

    public void selectNewDeliveryDate(String days){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(days));
        SimpleDateFormat dayFormat = new SimpleDateFormat("d");
        SimpleDateFormat monthIntFormat = new SimpleDateFormat("M");
        Date dt = cal.getTime();
        Integer day = Integer.parseInt(dayFormat.format(dt));
        Integer monthInt = Integer.parseInt(monthIntFormat.format(dt))-1;
        String currentDateContainer = waitForExpectedElement(deliveryDateContainer).getText();
LOG.info(currentDateContainer);
        wait.until(
                ExpectedConditions.and(
                    ExpectedConditions.invisibilityOfElementLocated(loadingMask),
                    ExpectedConditions.presenceOfElementLocated(skipDeliveryButton)
                )
        );

        clickByElementByQueryJSExecutor(calendarDeliveryImg);

        if (!isElementPresentByby(By.xpath("//td[@data-month='" + monthInt + "']/a[.='" + day + "']"))) {
            List<WebElement> calendarDays = webDriver.findElements(
                    By.cssSelector("tr:last-child td[data-handler='selectDay']:not([class*='current'])")
            );

            calendarDays.get(calendarDays.size() - 1).click();
        } else {
            waitForExpectedElement(
                By.xpath("//td[@data-month='" + monthInt + "']/a[.='" + day + "']")
            ).click();
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMask));
        LOG.info(waitForExpectedElement(deliveryDateContainer).getText());

        Assert.assertNotEquals(currentDateContainer, waitForExpectedElement(deliveryDateContainer).getText());
    }

    public void userIncreasesProductQtyIfSubsErrorAppearsInCart() {
        if (isElementPresent(SUBSCRIPTION_MINIMUM_QTY_ERROR)) {
            if (waitForExpectedElement(SUBSCRIPTION_MINIMUM_QTY_ERROR).getText().equalsIgnoreCase(UrlBuilder.getMessage("subscriptionMinQtyError.key"))) {
                int intProductQty = Integer.parseInt(getWebDriver().findElements(PRODUCT_QUANTITY_VIEW_BASKET).get(0).getAttribute("value"));
                while (intProductQty < 5) {
                    clickByElementByQueryJSExecutor(QUANTITY_INCREASE_BUTTON);
                    waitForAjaxElementNotToBePresent(getWebDriver(), 5);
                    intProductQty = Integer.parseInt(getWebDriver().findElements(PRODUCT_QUANTITY_VIEW_BASKET).get(0).getAttribute("value"));
                }
            }
        }
        clickByElementByQueryJSExecutor(HomePage.proceedToCheckoutFromBasketPage);
    }

    public void waitForLoaderToDisapear(){
        waitForElementToDisappear(loadingMask,20);
    }

    public String getValidateMessageOnModifySubscriptionModule() { return waitForExpectedElement(CUSTOM_MESSAGE_SUB).getText(); }
}
