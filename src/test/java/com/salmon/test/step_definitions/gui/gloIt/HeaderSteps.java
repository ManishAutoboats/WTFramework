package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItCheckoutPage;
import com.salmon.test.page_objects.gui.gloIt.GloItProductListPage;
import com.salmon.test.page_objects.gui.gloIt.HeaderPage;
import com.salmon.test.page_objects.gui.gloIt.OrderHistoryPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.testng.AssertJUnit.assertTrue;

public class HeaderSteps extends PageObject {
    private final HomePage homePage;
    private final GloItCheckoutPage gloItCheckoutPage;
    private final GloItProductListPage gloItProductListPage;
    private final HeaderPage headerPage;

    public HeaderSteps(HomePage homePage, GloItCheckoutPage gloItCheckoutPage, HeaderPage headerPage, GloItProductListPage gloItProductListPage) {
        this.homePage = homePage;
        this.gloItCheckoutPage = gloItCheckoutPage;
        this.headerPage = headerPage;
        this.gloItProductListPage=gloItProductListPage;
    }

    @And("^footer is present and displayed$")
    public void footerIsPresentAndDisplayed() {
        try {
            waitForExpectedElement(HeaderPage.JP_FOOTER,30);
            homePage.scrollToElement(HeaderPage.JP_FOOTER);
        } catch (Exception e) {
            refreshBrowser();gloItProductListPage.waitForLoaderToDisappear();
            waitForExpectedElement(HeaderPage.JP_FOOTER,20);
        }
        assertTrue(homePage.waitForExpectedElement(HeaderPage.JP_FOOTER).isDisplayed());
    }

    @And("^glo velo logo is displayed$")
    public void gloVeloLogoIsDisplayed() {
        try {
            waitForExpectedElement(gloItProductListPage.GLO_VELO_LOGO,10);
        } catch (Exception e) {
            try {
                webDriver.navigate().refresh();
                waitForExpectedElement(gloItProductListPage.GLO_VELO_LOGO,30);
            } catch (Exception exception) {
                refreshBrowser();
                waitForExpectedElement(gloItProductListPage.GLO_VELO_LOGO,30);
            }
        }
        assertTrue(homePage.waitForExpectedElement(gloItProductListPage.GLO_VELO_LOGO).isDisplayed());
    }

    @And("^header is displayed$")
    public void headerIsDisplayed() {
        try {
            homePage.waitForExpectedElement(HeaderPage.JP_HEADER);
        } catch (Exception e) {
            homePage.refreshBrowser();
            gloItProductListPage.waitForLoaderToDisappear();
            homePage.waitForExpectedElement(HeaderPage.JP_HEADER);
        }
        assertTrue(homePage.waitForExpectedElement(HeaderPage.JP_HEADER,30).isDisplayed());
    }

    @And("^assert internal redirect of header link URLs with success status code$")
    public void assertInternalRedirectOfHeaderLinkURLsWithSuccessStatusCode() throws Throwable {
        assertTrue(headerPage.assertInternalRedirectLinksWithStatusCode(HeaderPage.HEADER_REDIRECT_LINKS));
    }

    @And("^assert url links in footer$")
    public void assertUrlLinksInFooter() {
    }

    @And("^assert social media links with success status code$")
    public void assertSocialMediaLinksWithSuccessStatusCode(DataTable url) {
        headerPage.assertSocialMediaUrlLinks(url);
    }

    @And("^assert glo velo contact details are displayed$")
    public void assertGloVeloContactDetailsAreDisplayed() {
        homePage.waitForExpectedElement(HeaderPage.GLO_CONTACT_BLOCK);
        assertTrue(waitForExpectedElement(HeaderPage.GLO_CONTACT_BLOCK).isDisplayed());
        assertTrue(waitForExpectedElement(HeaderPage.VELO_CONTACT_BLOCK).isDisplayed());
        assertTrue(headerPage.linkIsPresent(HeaderPage.FOOTER_GLO_LINKS));
        assertTrue(headerPage.linkIsPresent(HeaderPage.FOOTER_VELO_LINKS));
    }

    @And("^assert basket icon link$")
    public void assertBasketIconLink() throws Throwable {
        assertTrue(headerPage.assertInternalRedirectLinksWithStatusCode(OrderHistoryPage.BASKET_ICON));
        assertTrue(waitForExpectedElement(OrderHistoryPage.BASKET_ICON).getAttribute("href").contains("/basket"));
    }

    @And("^assert content links with success status code$")
    public void assertContentLinksWithSuccessStatusCode() throws Throwable {
        assertTrue(headerPage.assertInternalRedirectLinksWithStatusCode(HeaderPage.CONTENT_LINKS));

    }

    @And("^assert payment icons are displayed$")
    public void assertPaymentIconsAreDisplayed() {
        assertTrue(waitForExpectedElement(HeaderPage.FOOTER_PAYMENT_ICONS).isDisplayed());
    }

    @And("^assert copyright is present and assert text$")
    public void assertCopyrightIsPresentAndAssertText() {
        assertTrue(waitForExpectedElement(HeaderPage.FOOTER_COPYRIGHT).isDisplayed());
    }

    @And("^assert glo and velo links in footer with success status code$")
    public void assertGloAndVeloLinksInFooter() throws Throwable {
        assertTrue(headerPage.assertInternalRedirectLinksWithStatusCode(HeaderPage.FOOTER_GLO_LINKS));
        assertTrue(headerPage.assertInternalRedirectLinksWithStatusCode(HeaderPage.FOOTER_VELO_LINKS));
    }

    @And("^assert links in person icon$")
    public void assertLinksInPersonIcon(DataTable DdtLinks) throws Throwable {
        homePage.waitForItemToBeClickableAndClick(OrderHistoryPage.PERSON_ICON);
        headerPage.assertPersonIconLinks(DdtLinks);
    }

    @And("^assert homepage icon link$")
    public void assertHomepageIconLink() throws Throwable {
        assertTrue(headerPage.assertInternalRedirectLinksWithStatusCode(HeaderPage.HEADER_HOME_ICON));
        assertTrue(getWebDriver().findElement(HeaderPage.HEADER_HOME_ICON).getAttribute("href").contains("/home"));
    }

    @Then("^assert Link contains text and assert Url returns successful response$")
    public void assertLinkContainsTextAndAssertUrlReturnsSuccessfulResponse(DataTable dtLinks) throws Throwable {
        headerPage.assertUrlLink(dtLinks);
    }

    @Then("^user clicks on glo velo Link and assert sub menu links and Url returns successful response$")
    public void userClicksOnGloVeloLinkAndAssertSubMenuLinksAndUrlReturnsSuccessfulResponse(DataTable table) throws Throwable {
        headerPage.clickOnLinksAndAssertSubMenuLinks(table);
    }

}
