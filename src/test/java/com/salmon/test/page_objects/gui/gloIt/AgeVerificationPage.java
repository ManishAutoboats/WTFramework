package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import org.openqa.selenium.By;

public class AgeVerificationPage extends PageObject {
    private final HomePage homePage;

    public AgeVerificationPage(HomePage homePage) {
        this.homePage = homePage;
    }

    public final static By AGE_CONTENT = By.cssSelector("div.bat-agegate--default-content-greeting-text");
    public final static By AGE_CONTENT_HEADING = By.cssSelector("div.bat-agegate--default-content-greeting-headline");
    public final static By COMBINED_LOGO = By.cssSelector("div.bat-agegate--default-content-image>picture>img");
    public final static By ENTRY_AGE_POPUP = By.cssSelector("div.entry-age-confirmation");
    public final static By HOMEPAGE_ENTRY_AGE_POPUP = By.cssSelector("div.bat-modal-content");
    public final static By ENTRY_AGE_ERROR_JP = By.cssSelector("p.age-error");
    public final static By ENTRY_AGE_ERROR = By.cssSelector(".entry-age-error");
    public final static By BUTTON_AGE_DENY = By.cssSelector("#btn-entry-age-deny");
    public final static By BUTTON_AGE_ALLOW = By.cssSelector("#btn-entry-age-allow");
    private static String URL;
    public void userSelectsNotOver18Years() {
        waitForExpectedElement(BUTTON_AGE_DENY).click();
    }

    public void navigateDirectlyToM2UrlsForAgeVerification(String expected) {
        URL =UrlBuilder.getMessage("ecpageURL.key");
        switch (expected) {
            case "checkout":
                getWebDriver().navigate().to(URL + UrlBuilder.getMessage("checkoutUrl.key"));
                break;
            case "order history":
                getWebDriver().navigate().to(URL + UrlBuilder.getMessage("orderHistoryUrl.key"));
                break;
            case "order detail":
                getWebDriver().navigate().to(URL + UrlBuilder.getMessage("orderDetail.key"));
                break;
            case "historic orders":
                getWebDriver().navigate().to(URL + UrlBuilder.getMessage("historicOrderUrl.key"));
                break;
            case "historic order detail":
                getWebDriver().navigate().to(URL + UrlBuilder.getMessage("historicOrderDetailUrl.key"));
                break;
            case "hyper+ PDP":
                getWebDriver().navigate().to(URL + UrlBuilder.getMessage("hyperPlusUrl.key"));
                break;
            default:
                break;
        }
    }

    public void clickIamOver18() {
        try {
            waitForExpectedElement(BUTTON_AGE_ALLOW, 10);
            homePage.eyesCheckAgeGate();
            waitForItemToBeClickableAndClick(getWebDriver(), 10, BUTTON_AGE_ALLOW);
        } catch (Exception e) {
            LOG.info("Age content box isn't present - continuing");
        }
    }

    public void assertUserLandsOnGivenPagesAfterLogin(String url){
        urlToContainInSeconds(UrlBuilder.getMessage(url), 40);
        assertTrueExpectedTextContainsActualTextWithCustomError(UrlBuilder.getMessage("ecpageURL.key")+UrlBuilder.getMessage(url), getCurrentUrl());
    }
}

