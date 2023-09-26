package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.testng.Assert;

public class FAQPage extends PageObject {

    public By expandIcon = By.xpath("(//div[@class='pagebuilder-collapsible']//div[@class='collapsible-title']//span)[8]");
    public By content = By.xpath("//div[@class='pagebuilder-collapsible active']/div[2]");
    public By contactUsCta = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(2) > p:nth-child(4) > a");
    private static final By FAQ_LINK = By.xpath("(//div[@class='footer_top-cmsblock']//div[@class='pagebuilder-column']//div//p//a)[1]");
    private static final By FAQ_LINK2nd = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(2) > p:nth-child(2) > a");
    private static final By FAQ_LINK_FOOTER_MX = By.cssSelector("div.desktop-only.desktop-footer-links > div > div:nth-child(1) > div:nth-child(2) > p:nth-child(4) > a");
    private static final By FAQ_LINK_VELOZA = By.cssSelector("body > div.bat-wrapper > div > div > div:nth-child(2) > div > div > bat-header-avalanche > div > div > header > div.bat-header-menu > div > nav > div > ul > li:nth-child(5) > a");
    public void accordionExpand(){
        scrollElementIntoView(expandIcon);
        clickByElementByQueryJSExecutor(expandIcon);
        Assert.assertTrue(isElementPresent(content));
    }

    public void accordionCollapse(){
        clickByElementByQueryJSExecutor(expandIcon);
        Assert.assertFalse(isElementPresent(content));
    }

    public void clickFaq(){
        switch (UrlBuilder.getLocale()) {
            case "vusede":
                clickByElementByQueryJSExecutor(FAQ_LINK);
                break;
            case "veloza":
                clickByElementByQueryJSExecutor(FAQ_LINK_VELOZA);
                break;
            case "mx":
            case "vusemx":
                scrollElementIntoView(FAQ_LINK_FOOTER_MX);
                clickByElementByQueryJSExecutor(FAQ_LINK_FOOTER_MX);
                break;
            default:
            scrollElementIntoView(FAQ_LINK2nd);
            clickByElementByQueryJSExecutor(FAQ_LINK2nd);
        }
    }

    public void contactUsNavigate(){
        scrollElementIntoView(contactUsCta);
        clickByElementByQueryJSExecutor(contactUsCta);
    }

}
