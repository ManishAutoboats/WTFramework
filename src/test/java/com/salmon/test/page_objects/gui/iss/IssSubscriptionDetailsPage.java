package com.salmon.test.page_objects.gui.iss;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.iss.requests.AgeVerifyRequest;
import com.salmon.test.page_objects.gui.MailinatorPage;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.services.iss.InStoreSubscriptionServices;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class IssSubscriptionDetailsPage extends PageObject  {
    private static final By SUBSCRIPTION_DETAILS_HEADING = By.cssSelector("#app > main div._2pPcnlGQSHz_VQMh5bsnPz > h4");
    private static final By SUBSCRIPTION_DETAILS_ITEM = By.cssSelector("#app > main > div div._30_8X1-iQOCfGcSphXPEoX > div");
    public static final By SUBSCRIPTION_DETAILS_SUBTOTAL = By.cssSelector("#app > main > div div.eUH5m_7PE_Hmf2MKP1W6_");
    public static final By SUBSCRIPTION_DETAILS_TIER_BRONZE_ICON = By.cssSelector("div[class*='Bronze']");
    public static final By SUBSCRIPTION_DETAILS_TIER_SILVER_ICON = By.cssSelector("div[class*='Silver']");
    public static final By SUBSCRIPTION_DETAILS_TIER_GOLD_ICON = By.cssSelector("div[class*='Gold']");
    public static final By SUBSCRIPTION_DETAILS_ITEM_QUANTITY = By.cssSelector("div._3YHRkDTwCGTWmUwQqlHIBn");
    public static final By SUBSCRIPTION_DETAILS_TIER_DESCRIPTION = By.cssSelector("#app > main > div div._3-Kd7jlRlBhDXjSPvqsikW");
    public static final By SUBSCRIPTION_DETAILS_SUBSCRIPTION_PRICE = By.cssSelector("#app > main > div  div.daoEYOzk6YxoWRk63a4_P > div > h4");
    public static final By SUBSCRIPTION_DETAILS_AMEND_SUBSCRIPTION_BUTTON = By.cssSelector(" button:nth-child(1)");
    public static final By SUBSCRIPTION_DETAILS_SENT_TO_CUSTOMER_BUTTON = By.cssSelector(" button:nth-child(2)");
    public static final By SUBSCRIPTION_DETAILS_SET_UP_MONTHLY_PAYMENTS_SECTION = By.cssSelector("#app > main > div > div._1yO-MLYXGOul3c11VLowWN > div");
    public static final By SUBSCRIPTION_DETAILS_SET_UP_MONTHLY_PAYMENTS_HEADING =  By.cssSelector("#app > main > div > div._1yO-MLYXGOul3c11VLowWN > div > div._9tBdPqC7jLvzcH02Sgi5j > h4");
    public static final By SUBSCRIPTION_DETAILS_VAT = By.cssSelector("#app > main div._37zIJBvr_qLmzSqALETDUD");
    public static final By SUBSCRIPTION_DETAILS_SEND_VIA_EMAIL_BUTTON = By.cssSelector("#app > main div._2Y9IU7UQpJTZkZ2LbtQIVl > button.btn.btn__light");
    public static final By SUBSCRIPTION_DETAILS_SEND_EMAIL_BUTTON = By.cssSelector("#app > main form > div._3UZcsOQ6ZKm9nh6uXQIUx5 > button");
    public static final By SUBSCRIPTION_DETAILS_EMAIL_STATUS_MESSAGE = By.cssSelector("#app > main div._23qTmD-JluTeBlo7Sy_N4K > div");
    public static final By SUBSCRIPTION_DETAILS_FINISH_SUBSCRIPTION = By.cssSelector("#app > main > div > div._1yO-MLYXGOul3c11VLowWN > div > div._3QDLa08U07ajJci9vBG3nS > button");
    public static final By SUBSCRIPTION_DETAILS_WANT_TO_LEAVE_MODAL = By.cssSelector("body > div > div > div");
    public static final By SUBSCRIPTION_DETAILS_WANT_TO_LEAVE_YES = By.cssSelector("div > div._3HjzMvQ1Nr8y5jMKk1hwOH button.btn.btn__primary");
    public static final By SET_UP_MONTHLY_PAYMENTS_QR_CODE_SRC_ATTRIBUTE = By.cssSelector("#app > main > div > div._1yO-MLYXGOul3c11VLowWN > div > div > div > img");
    public static final By AGE_VERIFICATION_QR_CODE_SRC_ATTRIBUTE = By.cssSelector("#maincontent > div.columns > div > div.qr_code > div > img");
    public static final By SUBSCRIPTION_DETAILS_PAYMENT_SUCCESS = By.cssSelector("#app > main > div > div._1yO-MLYXGOul3c11VLowWN > div > div._1gxL4GyySvMiObWXQC1ygN");
    public static final By SUBSCRIBE_PRO_SAVED_CARD = By.cssSelector("#checkout-payment-method-load > div div.payment-method-title.field.choice > label");
    public static final By SUBSCRIBE_PRO_AGREEMENT_TICKBOX = By.cssSelector("input#agreement_subscribe_pro_vault_15");
    public static final By VUSE_AGREEMENT_TICKBOX = By.cssSelector("input#agreement_subscribe_pro_vault_1");
    public static final By PLACE_ORDER_CTA = By.cssSelector("#checkout-payment-method-load > div > div:nth-child(1) > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button > span");
    public static final By AV_TEXT_EXPLANATION = By.cssSelector("#maincontent > div.columns > div > div.qr_code > h2");
    public static final By AV_SCAN_BUTTON = By.cssSelector("#app > main div._1KTo7b2abFqSWTYOp1oDID > button");
    public static final By SUBSCRIBE_PRO_CARD = By.cssSelector("#checkout-payment-method-load > div > div > div.payment-method.payment-method-subscribe_pro > div.payment-method-title.field.choice > label > span");
    public static final By PAYMENT_MESSAGE = By.cssSelector("div.instore-subs-inner-block");
    private MailinatorPage mailinatorPage;
    private ScenarioContext scenarioContext;
    private InStoreSubscriptionServices inStoreSubscriptionServices;
    private IssBasketOverlayPage issBasketOverlayPage;
    private IssHomePage issHomePage;

    public IssSubscriptionDetailsPage( MailinatorPage mailinatorPage,
                                       ScenarioContext scenarioContext,
                                       InStoreSubscriptionServices inStoreSubscriptionServices,
                                       IssBasketOverlayPage issBasketOverlayPage,
                                       IssHomePage issHomePage) {
        this.mailinatorPage = mailinatorPage;
        this.scenarioContext = scenarioContext;
        this.inStoreSubscriptionServices = inStoreSubscriptionServices;
        this.issBasketOverlayPage = issBasketOverlayPage;
        this.issHomePage = issHomePage;
    }

    public boolean isSubscriptionDetailsHeadingdisplayed() {
        String expectedSubscriptionDetailsHeading = UrlBuilder.getMessage("subscriptionDetailsPageHeading");
        String actualSubscriptionDetailsHeading = waitForExpectedElement(SUBSCRIPTION_DETAILS_HEADING).getText();
        return expectedSubscriptionDetailsHeading.equalsIgnoreCase(actualSubscriptionDetailsHeading);
    }

    public boolean isPriceDisplayed() {
        List<WebElement> items = webDriver.findElements(SUBSCRIPTION_DETAILS_ITEM);
        String text;
        String currency = UrlBuilder.getMessage("currency.key");
        for (WebElement webElement: items) {
            text = webElement.getText();
            if (!text.contains(currency)
            || text.split(currency)[1].length()<1) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubtotalDisplayed() {
        String expectedSubtotalText = UrlBuilder.getMessage("subscriptionDetailsSubtotalText");
        String actualSubtotalText = waitForExpectedElement(SUBSCRIPTION_DETAILS_SUBTOTAL).getText();
        return expectedSubtotalText.equalsIgnoreCase(actualSubtotalText);
    }

    private String activeTier(int qty) {
        int bronzeThreshold = Integer.parseInt(UrlBuilder.getMessage("bronzeThreshold"));
        int silverThreshold = Integer.parseInt(UrlBuilder.getMessage("silverThreshold"));
        int goldThreshold = Integer.parseInt(UrlBuilder.getMessage("goldThreshold"));
        if(qty >= bronzeThreshold && qty < silverThreshold) {
            return "bronze";
        } else if (qty >= silverThreshold && qty <goldThreshold) {
            return "silver";
        } else if (qty >= goldThreshold) {
            return "gold";
        } else {
            return "none";
        }
    }

    public boolean isTierDiscountDisplayed() {
        By tierDiscountIcon;
        List<WebElement> items = webDriver.findElements(SUBSCRIPTION_DETAILS_ITEM);
        String tier, expectedTierDiscountText, actualTierDiscountText;
        String totalString;
        int itemInt;
        int total = 0;
        for (WebElement webElement: items) {
            totalString = webElement.findElement(SUBSCRIPTION_DETAILS_ITEM_QUANTITY).getText();
            itemInt = Integer.parseInt(totalString.split(" ")[1]);
            total = total + itemInt;
        }
        tier = activeTier(total);
        switch(tier) {
            case "bronze":
                expectedTierDiscountText=UrlBuilder.getMessage("bronzeTierText");
                tierDiscountIcon = SUBSCRIPTION_DETAILS_TIER_BRONZE_ICON;
                break;
            case "silver":
                expectedTierDiscountText=UrlBuilder.getMessage("silverTierText");
                tierDiscountIcon = SUBSCRIPTION_DETAILS_TIER_SILVER_ICON;
                break;
            case "gold":
                expectedTierDiscountText=UrlBuilder.getMessage("goldTierText");
                tierDiscountIcon = SUBSCRIPTION_DETAILS_TIER_GOLD_ICON;
                break;
            default:
                expectedTierDiscountText="";
                tierDiscountIcon = null;
        }
        actualTierDiscountText = waitForExpectedElement(SUBSCRIPTION_DETAILS_TIER_DESCRIPTION).getText();
        if (tier.equals("none")) {
            return true;
        } else {
            return actualTierDiscountText.equalsIgnoreCase(expectedTierDiscountText) && waitForExpectedElement(tierDiscountIcon).isDisplayed();
        }
    }

    public boolean validPrice(String price) {
        String regExp = "£[0-9]+([,.][0-9]{1,2})?";
        final Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    public boolean isSubscriptionPriceDisplayed() {
        List<WebElement> subscriptionPriceInfo = webDriver.findElements(SUBSCRIPTION_DETAILS_SUBSCRIPTION_PRICE);
        String actualSubscriptionPriceHeading = subscriptionPriceInfo.get(0).getText();
        String expectedSubscriptionPriceHeading = UrlBuilder.getMessage("subscriptionPriceHeading");
        String actualsubscriptionPriceValue = subscriptionPriceInfo.get(1).getText();
        return actualSubscriptionPriceHeading.equalsIgnoreCase(expectedSubscriptionPriceHeading) && validPrice(actualsubscriptionPriceValue);
    }

    public boolean ctaAmendSubscriptionIsDisplayed() {
        String actualCtaAmendSubscriptionButtonText = waitForExpectedElement(SUBSCRIPTION_DETAILS_AMEND_SUBSCRIPTION_BUTTON).getText();
        String expectedCtaAmendSubscriptionButtonText = UrlBuilder.getMessage("amendSubscriptionCtaText");
        return actualCtaAmendSubscriptionButtonText.equalsIgnoreCase(expectedCtaAmendSubscriptionButtonText);
    }

    public boolean ctaSendToCustomerIsDisplayed() {
        String actualCtaSendToCustomerButtonText = waitForExpectedElement(SUBSCRIPTION_DETAILS_SENT_TO_CUSTOMER_BUTTON).getText();
        String expectedCtaSendToCustomerButtonText = UrlBuilder.getMessage("sendToCustomerCtaText");
        return actualCtaSendToCustomerButtonText.equalsIgnoreCase(expectedCtaSendToCustomerButtonText);
    }

    public boolean setupMonthlyPaymentsSectionIsDisplayed() {
        boolean displayed = waitForExpectedElement(SUBSCRIPTION_DETAILS_SET_UP_MONTHLY_PAYMENTS_SECTION).isDisplayed();
        String expectedSetUpMonthlyPaymentsHeading = UrlBuilder.getMessage("setUpMonthlyPaymentsHeading");
        String actualSetUpMonthlyPaymentsHeading = waitForExpectedElement(SUBSCRIPTION_DETAILS_SET_UP_MONTHLY_PAYMENTS_HEADING).getText();
        return displayed && expectedSetUpMonthlyPaymentsHeading.equalsIgnoreCase(actualSetUpMonthlyPaymentsHeading);
    }

    public void clickAmendSubscriptionButton() {
        waitForExpectedElement(SUBSCRIPTION_DETAILS_AMEND_SUBSCRIPTION_BUTTON).click();
    }

    public boolean isVatDisplayed() {
        String vatData = waitForExpectedElement(SUBSCRIPTION_DETAILS_VAT).getText();
        if (!vatData.toLowerCase().contains(UrlBuilder.getMessage("vatLabel"))) {
            return false;
        } else if (!vatData.toLowerCase().contains(UrlBuilder.getMessage("currency.key"))) {
            return false;
        } else if (vatData.length() < 4) {
            return false;
        } else {
            return true;
        }
    }

    public void clickSendViaEmail() {
        waitForExpectedElement(SUBSCRIPTION_DETAILS_SEND_VIA_EMAIL_BUTTON).click();
    }

    public void clickSendEmail() {
        waitForExpectedElement(SUBSCRIPTION_DETAILS_SEND_EMAIL_BUTTON).click();
    }

    public void clickOnEmailLinktext(String linkText, String user) {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.open()");
        switchToTab("new");
        mailinatorPage.clickLatestEmailReceivedAndSwitchToMessageBodyIframe(user);
        String link = UrlBuilder.getMessage(linkText);
        try {
            waitForExpectedElement(By.linkText(link)).click();
        } catch (Exception e) {
            clickUsingJS(By.linkText(link));
        }
        mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
    }

    public void switchToTab(String windowDescription) {
        // windowDescription = "new" will go to the last handle
        assertThat(windowDescription.equalsIgnoreCase("app") || windowDescription.equalsIgnoreCase("site")
        || windowDescription.equalsIgnoreCase("email") ||  windowDescription.equalsIgnoreCase("new"))
                .as("ERROR switchToNewTab: invalid parameter "+windowDescription+" supplied").isTrue();
        Set<String> handles=webDriver.getWindowHandles();
        for(String actual: handles) {
                webDriver.switchTo().window(actual);
                if (windowDescription.toLowerCase().contains("app") && webDriver.getCurrentUrl().contains("app")
                    || windowDescription.toLowerCase().contains("email") && webDriver.getCurrentUrl().contains("mailinator")
                    || windowDescription.toLowerCase().contains("site") && !webDriver.getCurrentUrl().contains("mailinator") && !webDriver.getCurrentUrl().contains("app")) {
                    break;
                }
        }
    }

    public boolean userisOnLoginPage() {
        switchToTab("site");
        String currentUrl = webDriver.getCurrentUrl();
        return currentUrl.contains(UrlBuilder.getMessage("url_prefix.key") + "/checkout/customer/login/");
    }

    public void clickOnFinishSubscription() {
        waitForExpectedElement(SUBSCRIPTION_DETAILS_FINISH_SUBSCRIPTION).click();
    }

    public boolean isDoYouWantToLeaveModalDisplayed() {
        return waitForExpectedElement(SUBSCRIPTION_DETAILS_WANT_TO_LEAVE_MODAL).isDisplayed();
    }

    public void clickOnAreYouSureYes() {
        waitForExpectedElement(SUBSCRIPTION_DETAILS_WANT_TO_LEAVE_YES).click();
    }

    public static String decodeQRCode(Object qrCodeImage) {
        Result result = null;
        byte[] decoded;
        try {
            BufferedImage bufferedImage;
            if (((String) qrCodeImage).contains("http")) {
                bufferedImage = ImageIO.read((new URL((String)qrCodeImage)));
            } else {
                decoded = Base64.decodeBase64((String)qrCodeImage);
                bufferedImage = ImageIO.read(new ByteArrayInputStream(decoded));
            }

            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            result = new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException | IOException | com.google.zxing.NotFoundException e) {
            LOG.info("Error reading the QR Code", e);
        }
        return result.getText();
    }

    public String getSetUpMonthlyPaymentsQrCodeUrl() {
        String qrCodeSrcAttribute = waitForExpectedElement(SET_UP_MONTHLY_PAYMENTS_QR_CODE_SRC_ATTRIBUTE).getAttribute("src");
        return decodeQRCode(qrCodeSrcAttribute.split(",")[1]);
    }

    public String getRetrievePreviousQuoteQrCodeUrl() {
        String qrCodeSrcAttribute = waitForExpectedElement(SET_UP_MONTHLY_PAYMENTS_QR_CODE_SRC_ATTRIBUTE).getAttribute("src");
        return decodeQRCode(qrCodeSrcAttribute.split(",")[1]);
    }

    public void navigateToScannedUrl(String url) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.open('"+url+"','_blank');");
        for(String winHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(winHandle);
        }
    }

    public boolean paymentSuccessMessageIsDisplayed() {
        switchToTab("app");
        String successMessages =waitForExpectedElement(SUBSCRIPTION_DETAILS_PAYMENT_SUCCESS).getText() ;
        return successMessages.contains(UrlBuilder.getMessage("pleaseProceedToCheckoutMessage")) &&
                successMessages.contains(UrlBuilder.getMessage("monthlyPaymentSetupMessage"));
    }

    public void payBySubscribePro() {
        waitForElementToAppearAndDisappear(LOADER,5,5);
        List<WebElement> savedCards = waitForExpectedElements(SUBSCRIBE_PRO_SAVED_CARD);
        clickUsingJS(savedCards.get(0));
        waitForElementToAppearAndDisappear(LOADER,3,3);
        clickByElementByQueryJSExecutor(VUSE_AGREEMENT_TICKBOX);
        clickByElementByQueryJSExecutor(SUBSCRIBE_PRO_AGREEMENT_TICKBOX);
        clickByElementByQueryJSExecutor(PLACE_ORDER_CTA);
    }

    public String getAgeVerificationQrCodeUrl() {
        String qrCodeSrcAttribute;
        qrCodeSrcAttribute = waitForExpectedElement(AGE_VERIFICATION_QR_CODE_SRC_ATTRIBUTE).getAttribute("data-src");
        return decodeQRCode(qrCodeSrcAttribute.split(",")[1]);
    }

    public void clickScanAvCodeButton() {
        waitForExpectedElement(AV_SCAN_BUTTON, 10).click();
    }

    public void scanAgeVerificationQrCode() {
        String hash;
        // colleague
        switchToTab("app");
        clickScanAvCodeButton();
        hash = issBasketOverlayPage.getHash();
        // customer
        switchToTab("site");
        String encrypedCustomerData = getAgeVerificationQrCodeUrl();
        AgeVerifyRequest ageVerifyRequest = new AgeVerifyRequest();
        ageVerifyRequest.setIdentifier(hash);
        ageVerifyRequest.setEncrypted_customer_data(encrypedCustomerData);
        // colleague
        switchToTab("app");
        String token = issHomePage.getBearerToken();
        inStoreSubscriptionServices.ageVerification(ageVerifyRequest, token);
        // customer
        switchToTab("site");
        waitForElementToAppearAndDisappear(LOADER,10,10);
        clickByElementByQueryJSExecutor(SUBSCRIBE_PRO_CARD);
    }

    public boolean avTextIsdisplayed(String expectedAvText) {
        return waitForExpectedElement(AV_TEXT_EXPLANATION).getText().toLowerCase().equalsIgnoreCase(expectedAvText);
    }

    public boolean paymentWordingCorrect() {
        String paymentMessage = waitForExpectedElement(PAYMENT_MESSAGE).getText();
        LOG.info("Actual ayment wording = "+paymentMessage);
        return paymentMessage.contains(UrlBuilder.getMessage("paymentMessageOrder"))
                && paymentMessage.contains(UrlBuilder.getMessage("paymentMessageProcessed"))
                && !paymentMessage.contains(UrlBuilder.getMessage("invalidPaymentMessageOrder"))
                && !paymentMessage.contains(UrlBuilder.getMessage("invalidPaymentMessageProcessed"));
    }
}
