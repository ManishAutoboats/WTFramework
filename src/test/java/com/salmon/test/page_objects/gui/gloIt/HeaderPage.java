package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.OrderSuccessPage;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class HeaderPage extends PageObject {
    private final OrderSuccessPage orderSuccessPage;
    private final HomePage homePage;
    private final GloItCheckoutPage gloItCheckoutPage;
    private final GloItProductListPage gloItProductListPage;

    public HeaderPage(OrderSuccessPage orderSuccessPage, HomePage homePage, GloItCheckoutPage gloItCheckoutPage, GloItProductListPage gloItProductListPage) {
        this.orderSuccessPage=orderSuccessPage;
        this.homePage = homePage;
        this.gloItCheckoutPage = gloItCheckoutPage;
        this.gloItProductListPage=gloItProductListPage;
    }

    public static final By JP_FOOTER = By.cssSelector("div.bat-footer");
    public static final By JP_HEADER = By.cssSelector("div.bat-header--wrapper");
    public static final By HEADER_REDIRECT_LINKS = By.cssSelector("li.bat-navigation-sub-group-list-item>a");
    public static final By CONTENT_LINKS = By.cssSelector("bat-cta-default.bat.footer-links>div>a");
    public static final By GLO_CONTACT_BLOCK = By.cssSelector("bat-text-default.bat.js-glo-contact");
    public static final By VELO_CONTACT_BLOCK = By.cssSelector("bat-text-default.bat.js-velo-contact");
    public static final By FOOTER_COPYRIGHT = By.cssSelector("bat-text-default.bat.copyright-text>div>p");
    public static final By FOOTER_PAYMENT_ICONS = By.cssSelector("bat-section-default.bat.footer-payment>div>div");
    public static final By INSTA_LINK = By.cssSelector("bat-cta-default.bat.footer-social>div>a:first-child");
    public static final By LINE_LINK = By.cssSelector("bat-cta-default.bat.footer-social>div>a:nth-child(2)");
    public static final By TWITTER_LINK = By.cssSelector("bat-cta-default.bat.footer-social>div>a:nth-child(3)");
    public static final By YOUR_INFORMATION_LINK_GLO = By.cssSelector("div.bat-cta.bat-cta-list--vertical>a:nth-child(1)");
    public static final By YOUR_INFORMATION_LINK_VELO = By.cssSelector("div.bat-cta.bat-cta-list--vertical>a:nth-child(2)");
    public static final By YOUR_REGULAR_DELIVERY_SERVICE = By.cssSelector("div.bat-cta.bat-cta-list--vertical>a:nth-child(3)");
    public static final By MY_ORDERS_LINK = By.cssSelector("div.bat-cta.bat-cta-list--vertical>a:nth-child(4)");
    public static final By DEVICE_REGISTRATION_LINK = By.cssSelector("div.bat-cta.bat-cta-list--vertical>a:nth-child(5)");
    public static final By SIGN_OUT_LINK = By.cssSelector("div.bat-cta.bat-cta-list--vertical>a:nth-child(6)");
    public static final By FOOTER_GLO_LINKS = By.cssSelector("div.footer-main--nav.js-footer-accordion:first-child>ul>li>a");
    public static final By FOOTER_VELO_LINKS = By.cssSelector("div.footer-main--nav.js-footer-accordion:last-child>ul>li>a");
    public static final By HEADER_HOME_ICON = By.cssSelector("div.bat-header--wrapper>div>div>div>div:nth-child(2)>bat-image-default>div>a");
    private static final By VELO_FAQ = By.cssSelector("div.footer-main--nav.js-footer-accordion:last-child>ul>li:first-child>a");
    private static final By VELO_OFFICIAL_WEBSITE = By.cssSelector("div.footer-main--nav.js-footer-accordion:last-child>ul>li:nth-child(2)>a");
    private static final By GLO_FAQ = By.cssSelector("div.footer-main--nav.js-footer-accordion:first-child>ul>li:first-child>a");
    private static final By GLO_OFFICIAL_WEBSITE = By.cssSelector("div.footer-main--nav.js-footer-accordion:first-child>ul>li:nth-child(2)>a");
    private static final By VELO_STORES = By.cssSelector("div.footer-main--nav.js-footer-accordion:last-child>ul>li:nth-child(3)>a");
    private static final By GLO_STORES = By.cssSelector("div.footer-main--nav.js-footer-accordion:first-child>ul>li:nth-child(3)>a");

    public void assertSocialMediaUrlLinks(DataTable url) {
        List<List<String>> lists = url.asLists(String.class);
        for (List<String> list : lists) {
            switch (list.get(0)) {
                case "instagram":
                    String strURL = waitForExpectedElement(INSTA_LINK).getAttribute("href");
                    assertEquals(UrlBuilder.getMessage("instagramURL.key"), strURL);
                    break;
                case "line":
                    strURL = waitForExpectedElement(LINE_LINK).getAttribute("href");
                    assertEquals(UrlBuilder.getMessage("lineURL.key"), strURL);
                    break;
                case "twitter":
                    strURL = waitForExpectedElement(TWITTER_LINK).getAttribute("href");
                    assertEquals(UrlBuilder.getMessage("twitterURL.key"), strURL);
                    break;
                default:
                    break;
            }
        }
    }

    public void assertPersonIconLinks(DataTable url) {
        List<List<String>> lists = url.asLists(String.class);
        for (List<String> list : lists) {
            switch (list.get(0)) {
                case "yourInformation_glo":
                    String strURL = waitForExpectedElement(YOUR_INFORMATION_LINK_GLO).getAttribute("href");
                    assertTrue(strURL.contains(UrlBuilder.getMessage(list.get(1))));
                    break;
                case "yourInformation_velo":
                     strURL = waitForExpectedElement(YOUR_INFORMATION_LINK_VELO).getAttribute("href");
                    assertTrue(strURL.contains(UrlBuilder.getMessage(list.get(1))));
                    break;
                case "yourRegularDelivery":
                    strURL = waitForExpectedElement(YOUR_REGULAR_DELIVERY_SERVICE).getAttribute("href");
                    assertTrue(strURL.contains(UrlBuilder.getMessage(list.get(1))));
                    break;
                case "myOrders":
                    strURL = waitForExpectedElement(MY_ORDERS_LINK).getAttribute("href");
                    assertTrue(strURL.contains(UrlBuilder.getMessage(list.get(1))));
                    break;
                case "deviceRegistration":
                    strURL = waitForExpectedElement(DEVICE_REGISTRATION_LINK).getAttribute("href");
                    assertTrue(strURL.contains(UrlBuilder.getMessage(list.get(1))));
                    break;
                case "signOut":
                    strURL = waitForExpectedElement(SIGN_OUT_LINK).getAttribute("href");
                    assertTrue(strURL.contains(UrlBuilder.getMessage(list.get(1))));
                    break;
                default:
                    break;
            }
        }
    }

    public boolean assertInternalRedirectLinksWithStatusCode(By by) throws Throwable {
        SoftAssert softAssert = new SoftAssert();
        int intCounter = 0;
        int intSize = 0;
        try {
            intSize = getWebDriver().findElements(by).size();
            List<WebElement> lstLinks = getWebDriver().findElements(by);
            for (WebElement eleLink : lstLinks) {
                if (!eleLink.getAttribute("href").contains("javascript:void(0)")) {
                    String link=eleLink.getAttribute("href");
                    softAssert.assertFalse(eleLink.getAttribute("href").isEmpty(),"Element at "+intCounter+" is having href empty");
                   //assertFalse(eleLink.getAttribute("href").isEmpty());
                   // softAssert.assertFalse(orderSuccessPage.getURLStatusCodeIsValid(eleLink.getAttribute("href"));
                    softAssert.assertTrue(orderSuccessPage.getURLStatusCodeIsValid(eleLink.getAttribute("href")));
                    intCounter++;
                }
            }
            softAssert.assertAll();
        } catch (Exception e) {
            LOG.info("Failed to assert URL redirect and status code due to exception" + e.getMessage());
        }
        return intSize == intCounter;
    }

    public void assertUrlLink(DataTable links) throws Throwable {
        List<List<String>> lstLinks = links.raw();
        for (List<String> lstLink : lstLinks) {
            switch (lstLink.get(0)) {
                case "gloFAQ.key":
                    assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(GLO_FAQ).getAttribute("href"),(UrlBuilder.getMessage(lstLink.get(1))));
                    orderSuccessPage.verifyURLStatusCodeIsValid(waitForExpectedElement(GLO_FAQ).getAttribute("href"));
//                    clickByElementByQueryJSExecutor(GLO_FAQ);
//                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(),(UrlBuilder.getMessage(lstLink.get(1))));
//                    getWebDriver().navigate().back();
                    break;
                case "veloFAQ.key":
                    assertTrue(waitForExpectedElement(VELO_FAQ).getAttribute("href").contains(UrlBuilder.getMessage(lstLink.get(1))));
                    orderSuccessPage.verifyURLStatusCodeIsValid(waitForExpectedElement(VELO_FAQ).getAttribute("href"));
//                    clickByElementByQueryJSExecutor(VELO_FAQ);
//                    assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(lstLink.get(1))));
//                    getWebDriver().navigate().back();
                    break;
                case "gloStore.key":
                    assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(GLO_STORES).getAttribute("href"),(UrlBuilder.getMessage(lstLink.get(1))));
//                    orderSuccessPage.verifyURLStatusCodeIsValid(waitForExpectedElement(GLO_STORES).getAttribute("href"));
//                    clickByElementByQueryJSExecutor(GLO_STORES);
//                    assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(lstLink.get(1))));
//                    getWebDriver().navigate().back();
                    break;
                case "veloStore.key":
                    assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(VELO_STORES).getAttribute("href"),(UrlBuilder.getMessage(lstLink.get(1))));
//                    orderSuccessPage.verifyURLStatusCodeIsValid(waitForExpectedElement(VELO_STORES).getAttribute("href"));
//                    clickByElementByQueryJSExecutor(VELO_STORES);
//                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(),(UrlBuilder.getMessage(lstLink.get(1))));
//                    getWebDriver().navigate().back();
                    break;
                case "gloOfficialWebsite.key":
                    assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(GLO_OFFICIAL_WEBSITE).getAttribute("href"),(UrlBuilder.getMessage(lstLink.get(1))));
//                    orderSuccessPage.verifyURLStatusCodeIsValid(waitForExpectedElement(GLO_OFFICIAL_WEBSITE).getAttribute("href"));
//                    clickByElementByQueryJSExecutor(GLO_OFFICIAL_WEBSITE);
//                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(),(UrlBuilder.getMessage(lstLink.get(1))));
//                    getWebDriver().navigate().back();
                    break;
                case "veloOfficialWebsite.key":
                    assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(VELO_OFFICIAL_WEBSITE).getAttribute("href"),(UrlBuilder.getMessage(lstLink.get(1))));
//                    orderSuccessPage.verifyURLStatusCodeIsValid(waitForExpectedElement(VELO_OFFICIAL_WEBSITE).getAttribute("href"));
                    clickByElementByQueryJSExecutor(VELO_OFFICIAL_WEBSITE);
                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(),(UrlBuilder.getMessage("veloOfficialWebsiteURL1.key")));
                    getWebDriver().navigate().back();
                    break;
                default:
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage(lstLink.get(0))),20);
                    assertTrueExpectedTextContainsActualTextWithCustomError(waitForExpectedElement(By.linkText(UrlBuilder.getMessage(lstLink.get(0)))).getAttribute("href"),(UrlBuilder.getMessage(lstLink.get(1))));
                    orderSuccessPage.verifyURLStatusCodeIsValid(waitForExpectedElement(By.linkText(UrlBuilder.getMessage(lstLink.get(0)))).getAttribute("href"));
//                    clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(lstLink.get(0))));
//                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(),(UrlBuilder.getMessage(lstLink.get(1))));
//                    getWebDriver().navigate().back();
                    break;
            }
        }
    }

    public void clickOnLinksAndAssertSubMenuLinks(DataTable links) throws Throwable {
        List<List<String>> lstLinks = links.raw();
        for (List<String> lstLink : lstLinks) {
            switch (lstLink.get(0)) {
                case "velo.key":
                    waitForExpectedElement(gloItProductListPage.PLP_VELO_DROPDOWN);
                    clickByElementByQueryJSExecutor(gloItProductListPage.PLP_VELO_DROPDOWN);
                    homePage.usersClicksOnTheLinkByText(lstLink.get(0));
                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(),(UrlBuilder.getMessage(lstLink.get(1))));
                    orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
                    getWebDriver().navigate().back();
                    break;
                case "yourInformation.key":
                case "myOrders.key":
                case "deviceRegistration.key":
                case "signOut.key":
                    waitForExpectedElement(OrderHistoryPage.PERSON_ICON);
                    clickByElementByQueryJSExecutor(OrderHistoryPage.PERSON_ICON);
                    homePage.usersClicksOnTheLinkByText(lstLink.get(0));
                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(), (UrlBuilder.getMessage(lstLink.get(1))));
                    assertTrueWithCustomError(UrlBuilder.getMessage(lstLink.get(2)), getCurrentPageTitle());
                    getWebDriver().navigate().back();
                    break;
                default:
                    waitForExpectedElement(gloItProductListPage.PLP_GLO_DROPDOWN);
                    clickByElementByQueryJSExecutor(gloItProductListPage.PLP_GLO_DROPDOWN);
                    homePage.usersClicksOnTheLinkByText(lstLink.get(0));
                    assertTrueExpectedTextContainsActualTextWithCustomError(getWebDriver().getCurrentUrl(), (UrlBuilder.getMessage(lstLink.get(1))));
                    orderSuccessPage.verifyURLStatusCodeIsValid(getWebDriver().getCurrentUrl());
                    getWebDriver().navigate().back();
                    break;
            }
        }
    }

    public boolean linkIsPresent(By by) {
        int intCounter = 0;
        int intSize = 0;
        try {
            intSize = waitForExpectedElements(by).size();
            List<WebElement> lstLinks = waitForExpectedElements(by);
            for (WebElement eleLink : lstLinks) {
                if (!eleLink.getAttribute("href").contains("javascript:void(0)")) {
                    assertFalse(eleLink.getAttribute("href").isEmpty());
                    intCounter++;
                }
            }
        } catch (Exception e) {
            LOG.info("Failed to verify link due to " + e.getMessage());
        }
        return intSize == intCounter;

    }
}