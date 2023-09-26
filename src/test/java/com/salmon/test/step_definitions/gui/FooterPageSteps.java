package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.constants.Site;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class FooterPageSteps extends PageObject {

    private static final By FOOTER_GENERAL_ITEMS_LIST = By.cssSelector("ul.footer-menu.footer-menu-one li a");
    private static final By FOOTER_CUSTOMER_SERVICE_ITEMS_LIST = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(2) > p> a");
    private static final By FOOTER_ABOUT_GLO_ITEMS_LIST = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(3) > div:nth-child(2) > p > a");
    private static final By FOOTER_NEED_HELP_SECTION = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(5) > div:nth-child(2) > p > a");
    private static final By FOOTER_LEGAL_ITEMS_LIST = By.cssSelector("ul.footer-menu.footer-menu-two li a");
    private static final By FOOTER_SOCIAL_MEDIA_ITEMS_LIST = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(3) > div > a,ul.social-media-icons a");
    private static final By FOOTER_SOCIAL_MEDIA_ITEMS_LIST_GLODE=By.cssSelector("div.pagebuilder-column>div>div.social-footer a");
    public static final By FOOTER_SOCIAL_MEDIA_ICONS = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(3) > div > a.icon-instagram");
    private static final By FOOTER_NAV_ITEMS_LIST_VYPE_MX = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(2) > p > a");
    private static final By FOOTER_NAV_ITEMS_LIST_VYPE = By.cssSelector("ul.footer-menu li a");
    private static final By FOOTER_NAV_ITEMS_LIST_LYFTSE = By.cssSelector(".footer-nav ul.footer-menu li a");
    private static final By FOOTER_PRODUCT_ITEMS_LIST_LYFTSE = By.cssSelector(".footer-product ul.footer-menu li a");
    private static final By FOOTER_SUPPORT_ITEMS_LIST_LYFTSE = By.cssSelector(".footer-support ul.footer-menu li a");
    public static final By COPYRIGHT_SV = By.cssSelector(".copyright_sweden");
    public static final By COPYRIGHT_TEXT_SV = By.cssSelector(".copyright_sweden_text");
    public static final By FOOTER_GENERAL_ITEMS_LIST_PL = By.cssSelector("div.desktop-only.desktop-footer-links > div > div > div > p > a");
    private static final By FOOTER_LEGAL_ITEMS_LIST_PL = By.cssSelector("div:nth-child(3) > div > p > a");
    private static final By LEFT_COLUMN_FOOTER_LINK = By.cssSelector("div.footer.content > div:nth-child(3) > div.column-6.footer-nav.desktop-only > div > div > div> ul > li:nth-child(2) > a");
    private static final By FOOTER_ICE_COOL_STRONG = By.cssSelector("div.footer.content > div:nth-child(3) > div.column-3.footer-product.desktop-only > div > div > div > ul > li:nth-child(3) > a");
    private static final By FOOTER_TERM_CONDITION = By.cssSelector("div.footer.content > div:nth-child(3) > div.column-2.footer-support.desktop-only > div > div > div > ul > li:nth-child(3) > a");
    private List<String> actualLinkUrlList = new LinkedList<>();
    private List<String> expectedLinkUrlList = new LinkedList<>();
    private SoftAssertions softAssertions;

    public FooterPageSteps(SoftAssertions softAssertions) {
        this.softAssertions = softAssertions;
    }




    @And("^(?:Glo|lyft|user) should see (.*) and (.*) under (.*) of Footer$")
    public void userShouldSeeLinkAndLinkContentUnderFooterGeneralSection(String linkTextKey, String linkContentKey, String section) {
        By css = getCss(section);

        List<String> linkTextList = getCommaSeparatedStringConfigAsListForKey(linkTextKey);
        List<String> linkUrlKeyList = UrlBuilder.getMessage(linkContentKey).equals("")?null: getCommaSeparatedStringConfigAsListForKey(linkContentKey);
        if(!UrlBuilder.getMessage(linkContentKey).equals(""))
            linkUrlKeyList.stream().map(this::getExpectedLinkUrl).forEach(s -> expectedLinkUrlList.add(s));

        List<WebElement> actualItems = getWebDriver().findElements(css);
        try {
            scrollElementIntoView(css);
        } catch (Exception ex) {
            scrollToElement(css);
        }
        List<String> actualLinkTextList = actualItems.stream().map(WebElement::getText).map(e->e.trim()).collect(toList());

        actualItems.stream()
                .filter(f->hasAttribute(f,"href"))
                .map(e -> e.getAttribute("href"))
                .filter(s -> !(s.contains("tel:") || s.contains("mailto")))
                .filter(s -> !(s.contains("invype-a-friend") || s.contains("mgm")|| s.contains("karriere")))
                .map(s -> s.endsWith("/") ? s.substring(0, s.length() - 1) : s)
                .forEach(s -> actualLinkUrlList.add(s));

        softAssertions.assertThat(actualLinkTextList)
                .as("Unexpected Links Under "+ section +" with text")
                .hasSameElementsAs(linkTextList);
        softAssertions.assertThat(actualLinkUrlList)
                .as("Unexpected LinkUrl Under "+ section +" with text")
                .hasSameElementsAs(expectedLinkUrlList);
        softAssertions.assertAll();
    }

    private By getCss(String section) {
        By css = null;
        switch (Site.valueOf(UrlBuilder.getSite().toUpperCase())) {
            case GLO:
                if (UrlBuilder.getLocale().equals("pl") || UrlBuilder.getLocale().equals("kz")) {
                    css = section.equalsIgnoreCase("GeneralSection") ? FOOTER_GENERAL_ITEMS_LIST_PL : FOOTER_LEGAL_ITEMS_LIST_PL;
                }else if(UrlBuilder.getLocale().equals("glode")){
                    css = section.equalsIgnoreCase("GeneralSection") ?FOOTER_CUSTOMER_SERVICE_ITEMS_LIST:FOOTER_ABOUT_GLO_ITEMS_LIST;
                    break;
                }else {
                    css = section.equalsIgnoreCase("GeneralSection") ? FOOTER_GENERAL_ITEMS_LIST : FOOTER_LEGAL_ITEMS_LIST;
                    break;
                }
                    case LYFT:
                        switch (section) {
                            case "LeftColumn":
                                css = FOOTER_NAV_ITEMS_LIST_LYFTSE;
                                break;
                            case "ProductsSection":
                                css = FOOTER_PRODUCT_ITEMS_LIST_LYFTSE;
                                break;
                            case "SupportSection":
                                css = FOOTER_SUPPORT_ITEMS_LIST_LYFTSE;
                                break;
                        }
                        break;
            case VUSE:
                switch (UrlBuilder.getLocale()) {
                    case "vuseuk":
                    case "vusede":
                    case "vuseit":
                    switch (section) {
                        case "CustomerService":
                            css = FOOTER_CUSTOMER_SERVICE_ITEMS_LIST;
                            break;
                        case "AboutVuseSection":
                            css = FOOTER_ABOUT_GLO_ITEMS_LIST;
                            break;
                        case "NeedHelpSection":
                            css = FOOTER_NEED_HELP_SECTION;
                            break;
                    }
                    break;
                    default:
                        css = FOOTER_NAV_ITEMS_LIST_VYPE;
                        break;
                }
                break;
            case VYPE:
                switch (UrlBuilder.getLocale()) {
                    case "mx":
                    case "vusemx":
                        css = FOOTER_NAV_ITEMS_LIST_VYPE_MX;
                        break;
                    default:
                    css = FOOTER_NAV_ITEMS_LIST_VYPE;
                    break;
                    }
                }
                return css;
    }

    private LinkedList<String> getCommaSeparatedStringConfigAsListForKey(String linkTextKey) {
        return Stream
                .of(UrlBuilder.getMessage(linkTextKey).split(","))
                .collect(toCollection(LinkedList::new));
    }

    @And("^(?:Glo|lyft|user) should get the success response for the links$")
    public void userShouldGetTheSuccessResponseForTheLinks() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case LYFTSE:
                actualLinkUrlList.forEach(s -> {
                    int actualStatusCode = RestAssured.given().log().all().get(s).statusCode();
                    softAssertions.assertThat(actualStatusCode).as("The response for :" + s).isEqualTo(200);
                });
                break;
            case VUSEDE:
                actualLinkUrlList.forEach(s -> {
                    boolean actualStatusCode = false;
                    try {
                        actualStatusCode = verifyURLStatus(s);
                        if (!actualStatusCode){
                            actualStatusCode = getURLResponseCode(s+"/")== 200;
                        }
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();

                            //actualStatusCode = verifyURLStatus(s+"/");

                    }
                    softAssertions.assertThat(actualStatusCode).as("The response for :" + s).isEqualTo(true);
                });
                break;
            default:
                actualLinkUrlList.forEach(s -> {
                    boolean actualStatusCode = false;
                    try {
                        actualStatusCode = verifyURLStatus(s);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    softAssertions.assertThat(actualStatusCode).as("The response for :" + s).isEqualTo(true);
            });
        }
        softAssertions.assertAll();
    }

    @Then("^(?:Glo|lyft) should not see the footer General Items$")
    public void gloShouldNotSeeTheFooterGeneralItems() {
        assertThat(getWebDriver().findElements(FOOTER_GENERAL_ITEMS_LIST).size()).isEqualTo(0);
    }

    private String getExpectedLinkUrl(String linkContentUrlKey) {
        if (UrlBuilder.url.endsWith("/"))
            return linkContentUrlKey.isEmpty() ? UrlBuilder.url : UrlBuilder.url.concat(linkContentUrlKey);
            if (doesURLContain("from_store=lyft_se_sv_se")) {
                if (linkContentUrlKey.equalsIgnoreCase("se/en"))
                    return linkContentUrlKey.isEmpty() ? UrlBuilder.url : UrlBuilder.url.replace("se/sv", "se/en");
                else
                    return linkContentUrlKey.isEmpty() ? UrlBuilder.url : UrlBuilder.url.concat("/").replace("se/sv", "se/en").concat(linkContentUrlKey);
            }
        else if(linkContentUrlKey.contains("www.glo-zufriedenheitsgarantie.de"))
            return linkContentUrlKey;
        else
            return linkContentUrlKey.isEmpty() ? UrlBuilder.url : UrlBuilder.url.concat("/").concat(linkContentUrlKey);
    }

    @And("^(?:Glo|lyft) should see (.*) under FollowUs Section of Footer$")
    public void userShouldSeeUnderFollowUsSectionOfFooter(String linkContentKey) {
        List<String> expectedUrlList = getCommaSeparatedStringConfigAsListForKey(linkContentKey);
        By footerSocialMediaBy=FOOTER_SOCIAL_MEDIA_ITEMS_LIST;
        if(UrlBuilder.getLocale().equalsIgnoreCase("glode"))
            footerSocialMediaBy=FOOTER_SOCIAL_MEDIA_ITEMS_LIST_GLODE;
        scrollElementIntoView(footerSocialMediaBy);
        List<WebElement> actualSocialMediaItems = getWebDriver().findElements(footerSocialMediaBy);
        actualLinkUrlList = actualSocialMediaItems.stream()
                .map(webElement -> webElement.getAttribute("href"))
                .filter(s -> !s.isEmpty())
                .collect(toCollection(LinkedList::new));

        softAssertions.assertThat(actualLinkUrlList)
                .as("Unexpected Url under follow us section ")
                .hasSameElementsAs(expectedUrlList);
        softAssertions.assertAll();
    }

    @And("^assert copyright (.*) is present and assert (.*) text$")
    public void assertCopyrightIsPresentAndAssertTheText(String language, String text) {
        assertThat(waitForExpectedElement(COPYRIGHT_SV).isDisplayed()).isTrue();
        assertThat(waitForExpectedElement(COPYRIGHT_SV).getText()).isEqualTo(UrlBuilder.getMessage(language));

        assertThat(waitForExpectedElement(COPYRIGHT_TEXT_SV).isDisplayed()).isTrue();
        assertThat(waitForExpectedElement(COPYRIGHT_TEXT_SV).getText()).isEqualTo(UrlBuilder.getMessage(text));
    }

    @And("^user click on footer left column link$")
    public void userClickOnFooterLeftColumnLink() {
        scrollToPageBottom();
        clickUsingJS(LEFT_COLUMN_FOOTER_LINK);
    }

    @And("^user click on footer product link$")
    public void userClickOnFooterProductLink() {
        scrollToPageBottom();
        clickUsingJS(FOOTER_ICE_COOL_STRONG);
    }

    @And("^user click on footer support link$")
    public void userClickOnFooterSupportLink() {
        scrollToPageBottom();
        clickUsingJS(FOOTER_TERM_CONDITION);
    }
}