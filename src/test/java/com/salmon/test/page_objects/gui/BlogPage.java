package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class BlogPage extends PageObject {

    public By blogTitle = By.xpath("//h1[@class='page-title']//span");
    public By blogDropdown = By.xpath("//div[@class='select-categories page-main-actions']//select[@class='select-category']");
    private By tiles = By.xpath("//ol[@class='post-list']//li[contains(@class,'post-holder')]");
    private By tileLink = By.xpath("//h4[@class='post-title']//a");
    private By postTitle = By.xpath("//h1[@class='post-title']");

    private static final By BLOG_TITLE = By.cssSelector("span.base");
    private static final By VIEW_ALL = By.cssSelector("a.news-view-all-link.action.alink");
    private static final By VIEW_ALL_VELOBE = By.cssSelector("a[href*='/be/en/news-and-information'][class='bat-cta-style arrow-link-dark left icon-right ']");
    private static final By TILES = By.cssSelector("ol.post-list > li");
    private static final By TILE_TANK = By.cssSelector("a.post-item-link");
    private static final By POST_TITLE = By.cssSelector("h1.post-title");
    private static final By BLOG_VUSE = By.cssSelector("ul > li.level0.category-item.icon-megaphone.forth > a:nth-child(2)");
    private static final By BLOG_VELOZA = By.cssSelector("header > div.bat-header-menu > div > nav > div > ul > li:nth-child(4) > a");
    private static final By BLOG_VELOPL = By.cssSelector(".action.alink.news-view-all-link");
    private static final By BLOG_PAGES = By.xpath("//li[@class='item']");
    private static final By BLOG_POST_LIST = By.cssSelector("div.columns > div > div.post-list-wrapper > ol > li.post-holder");
    private static final By NEXT_PAGE = By.cssSelector("ul > li.item.pages-item-next");
    private static final By BLOGS_PRESENT = By.cssSelector("div.post-list-wrapper ");
    private static final By FIRST_BLOG = By.cssSelector("ol.post-list a.post-read-more:nth-child(1)");
    private static final By FIRST_BLOG_IT = By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[3]/ol/li[1]/div/div/div/div[2]/h4");
    private static final By NEXT_BUTTON = By.cssSelector("li.item.pages-item-next");
    private static final By TILE_TANK_FIRST = By.cssSelector("a.post-item-link:nth-child(1)");
    private static final By BLOG_POST_DATE = By.cssSelector(".post-date span.value");
    private static final By BLOG_POST_DATE_LYFTSE = By.cssSelector("div.item.post-posed-date span.value");
    private static final By BLOG_POST_DATE_VELOBE = By.cssSelector("div.responsivegrid.rootTemplateGrid.aem-GridColumn.aem-GridColumn--default--12 > div > div:nth-child(1) div.responsivegrid bat-card-news div.bat-card--news-text p:nth-child(1)");
    public static final By SOCIAL_MEDIA_LINKS = By.cssSelector("div.post-header > div.post-title-holder.clearfix > div");
    private static final By FIRST_BLOG_ARTICLE = By.cssSelector("ol.post-list>li:nth-child(1)");
    private static final By FACEBOOK_LINK = By.cssSelector("div.post-header a.addthis_button_facebook");
    private static final By TWITTER_LINK = By.cssSelector("div.post-header a.addthis_button_twitter");
    private static final By ALLOW_COOKIES_BUTTON = By.cssSelector("button[data-cookiebanner='accept_button']");
    private static final By FACEBOOK_LOGIN_BUTTON = By.cssSelector("#loginbutton");
    private static final By TWITTER_LOGIN = By.cssSelector("[data-testid='IntentLoginSheet_Login_Sheet']");
    private static final By BLOG_LINK = By.cssSelector("div.vype-cms-news>p a.action.alink");
    private static final By BLOG_MENU_GLOIT = By.cssSelector("ul#ui-id-1 li:nth-child(3)");
    private static final By FIRST_BLOG_IMAGE_LYFT = By.cssSelector("ol.post-list>li:nth-child(1) > div.post-content > div > div.post-ftimg-hld a");
    private static final By SELECTED_BLOG_PAGE_LYFT = By.cssSelector("div.post-view");
    private static final By FIRST_BLOG_LINK_LYFT = By.cssSelector("div.post-list-wrapper > ol > li:nth-child(1) > div.post-content > div > a");

    public void navigateToBlog(){
        switch (UrlBuilder.getLocale()) {
            case "mx":
            case "vusemx":
            clickByElementByQueryJSExecutor(VIEW_ALL);
            break;
            case "vuseit":
                hoverOnElement(By.linkText(UrlBuilder.getMessage("scopriVuse.key")));
                waitForExpectedElement(By.linkText(UrlBuilder.getMessage("blog.key"))).click();
            break;
            case "pl":
                jsScrollElementInCenter(waitForExpectedElement(BLOG_LINK));
                waitForExpectedElement(BLOG_LINK).click();
                break;
            case "veloza":
                clickByElementByQueryJSExecutor(BLOG_VELOZA);
                break;
            case "velopl":
                clickByElementByQueryJSExecutor(BLOG_VELOPL);
                break;
            case "it":
                jsScrollElementInCenter(waitForExpectedElement(BLOG_MENU_GLOIT));
                waitForExpectedElement(BLOG_MENU_GLOIT).click();
                break;
            case "velobe":
                clickByElementByQueryJSExecutor(VIEW_ALL_VELOBE);
                break;
            default:
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage("VuseBlog.key"))).click();
        }
    }

    public void blogTiles(){
        List<WebElement> list = presenceOfAllElementsLocatedBy(TILES);
        for(WebElement items : list){
            Assert.assertTrue(items.isDisplayed(),"Blog tiles are displayed");
        }
    }

    public void navigateToBlogTile(){
        String text = waitForExpectedElement(TILE_TANK).getText();
        clickByElementByQueryJSExecutor(TILE_TANK);
        String expected = waitForExpectedElement(POST_TITLE).getText();
        Assert.assertTrue(text.contains(expected));
    }

    public void paginationBlog(){
        clickByElementByQueryJSExecutor(BLOG_PAGES);
    }

    public void validatePaginationBlog(){
        presenceOfAllElementsLocatedBy(BLOG_PAGES);

    }

    public void validateBlogCount(){
        List<WebElement> pages = presenceOfAllElementsLocatedBy(BLOG_PAGES);
        for(int i=0;i<pages.size();i++){
            List<WebElement> blogs = webDriver.findElements(BLOG_POST_LIST);
            for(WebElement element : blogs){
                visibilityOf(element);
            }
        }

    }

    public void blogPageLanding(String text){
        assertTrueWithCustomError(getTextFor(BLOG_TITLE), UrlBuilder.getMessage(text));
    }

    public void selectBlogFromDropdown(String blogToSelectFromDropdown){
        selectValueFromDropDownByby(UrlBuilder.getMessage(blogToSelectFromDropdown),blogDropdown);
        assertEquals(UrlBuilder.getMessage(blogToSelectFromDropdown),BLOG_TITLE);
    }

    public void validatePage(String item){

        String text = waitForExpectedElement(BLOG_TITLE).getAttribute("title");
        assertEquals(UrlBuilder.getMessage(item),text);

    }
    public void clickBlogLink(){
        clickByElementByQueryJSExecutor(BLOG_TITLE);
    }

    public void verifyBlogsPresentAndNavigate(){
        Assert.assertTrue(isElementDisplayedBySeconds(BLOGS_PRESENT,5));
        try
        {
            clickByElementByQueryJSExecutor(FIRST_BLOG);
        }
        catch (Exception e)
        {
            clickByElementByQueryJSExecutor(FIRST_BLOG_IT);
        }
    }

    public void verifyBlogsDateArrangement() throws ParseException {
        List<WebElement> datelist;
        switch(UrlBuilder.getLocale()) {
            case "lyftse":
                datelist = webDriver.findElements(BLOG_POST_DATE_LYFTSE);
                break;
            case "velobe":
                datelist = webDriver.findElements(BLOG_POST_DATE_VELOBE);
                break;
            default:
                datelist = webDriver.findElements(BLOG_POST_DATE);
        }
        List<LocalDate> collectedBlogDates = new ArrayList<LocalDate>();
        List<LocalDate> sortedBlogDates = new ArrayList<LocalDate>();
            for (WebElement result:datelist) {
                String value = result.getText();
                LocalDate d1 = getDateFromString(value);
                collectedBlogDates.add(d1);
                sortedBlogDates.add(d1);
            }

            Collections.sort(sortedBlogDates, Collections.reverseOrder());
            AssertJUnit.assertEquals("*** ERROR - Blogs are not listed in descending order. ", sortedBlogDates, collectedBlogDates);
    }

    public void clickOnFirstArticle(){
        try {
            waitForExpectedElement(FIRST_BLOG_ARTICLE, 5).click();
        } catch (Exception e) {
            clickByElementByQueryJSExecutor(FIRST_BLOG_ARTICLE);
        }
    }

    public void clickOnFacebookIcon(){
        waitAndClickByElementByJSExecutor(FACEBOOK_LINK, 5);
    }

    public void cookieClickAllowAll(){
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchBetweenWindowTabs(1);
        if (waitForExpectedElement(ALLOW_COOKIES_BUTTON, 15).isDisplayed()) {
            waitForExpectedElement(ALLOW_COOKIES_BUTTON, 15).click();
        }
    }

    public boolean isFacebookLoginPagePresent() {
        return (isElementPresent(FACEBOOK_LOGIN_BUTTON, 5));
    }

    public void clickOnTwitterIcon(){
        waitForExpectedElement(TWITTER_LINK, 5).click();
    }

    public boolean isTwitterLoginPagePresent() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchBetweenWindowTabs(1);
        waitForExpectedElement(TWITTER_LOGIN,10);
        return (isElementPresent(TWITTER_LOGIN, 5));
    }

    public void clickFirstBlogImageVerifyBlogPageOpen(){
        waitForExpectedElement(FIRST_BLOG_IMAGE_LYFT);
        clickUsingJS(FIRST_BLOG_IMAGE_LYFT);
        waitForExpectedElement(SELECTED_BLOG_PAGE_LYFT);
        assertTrue(waitForExpectedElement(SELECTED_BLOG_PAGE_LYFT).isDisplayed());
    }

    public void clickFirstBlogLinkVerifyBlogPageOpen(){
        waitForExpectedElement(FIRST_BLOG_LINK_LYFT);
        clickUsingJS(FIRST_BLOG_LINK_LYFT);
        waitForExpectedElement(SELECTED_BLOG_PAGE_LYFT);
        assertTrue(waitForExpectedElement(SELECTED_BLOG_PAGE_LYFT).isDisplayed());
    }
}

