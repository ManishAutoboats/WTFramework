package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.BlogPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.ParseException;
import static org.testng.AssertJUnit.*;

public class BlogPageSteps {

    private BlogPage blogPage;

    public BlogPageSteps(BlogPage blogPage) {
        this.blogPage = blogPage;
    }

    @And("^user navigates to the Blog$")
    public void navigateToBlog(){
        blogPage.navigateToBlog();
    }

    @Then("^Count the paginations in blog page$")
    public void validatePaginationBlog(){
        blogPage.validatePaginationBlog();
    }

    @And("^Verify the number of blogs in all the pages$")
    public void validateBlogCount(){
        blogPage.validateBlogCount();
    }

    @And("^user lands on '(.*)' page$")
    public void blogPageLanding(String text){
        blogPage.blogPageLanding(text);
    }

    @And("^select blog type '(.*)' from dropdown$")
    public void selectBlogFromDropdown(String blogToSelectFromDropdown){
        blogPage.selectBlogFromDropdown(blogToSelectFromDropdown);
    }

    @And("^verify blog tiles are displayed$")
    public void blogTiles(){
        blogPage.blogTiles();
    }

    @And("^click on the tile and assert user is navigated to that page$")
    public void navigateToBlogTile(){
        blogPage.navigateToBlogTile();
    }

    @And("^click on pagination$")
    public void paginationBlog(){
        blogPage.paginationBlog();
    }

    @And("^assert user is navigated to '(.*)' page$")
    public void validatePage(String item){
        blogPage.validatePage(item);
    }

    @And("^click on blog link from the post$")
    public void clickBlogLink(){
        blogPage.clickBlogLink();
    }

    @And("^user can view blogs and navigate to it$")
    public void verifyBlogsPresentAndNavigateToBlog(){
        blogPage.verifyBlogsPresentAndNavigate();
    }

    @Then("^blogs are listed in descending order$")
    public void verifyBlogsDateArrangement() throws ParseException {
        blogPage.verifyBlogsDateArrangement(); }

    @Then("^social media links are present on blog page$")
    public void verifySocialMediaLinksOnBlogPage()  {
        assertTrue(blogPage.isElementDisplayedBySeconds(blogPage.SOCIAL_MEDIA_LINKS,4));
    }

    @And("^user clicks on the first article$")
    public void userClicksOnFirstArticle() {
        blogPage.clickOnFirstArticle();
    }

    @And("^user clicks on Facebook Icon$")
    public void users_click_on_Facebook_Icon() {
        blogPage.clickOnFacebookIcon();
    }

    @And("^select allow all from Facebook cookie popup page$")
    public void userSelectAllowAllFromFacebookCookiePopupPage() {
        blogPage.cookieClickAllowAll();
    }

    @Then("^Facebook login page is present$")
    public void facebookLoginPageIsPresent() {
        blogPage.switchToWindow();
        assertTrue(blogPage.isFacebookLoginPagePresent());
    }

    @And("^user clicks on Twitter Icon$")
    public void users_click_on_Twitter_Icon() {
        blogPage.clickOnTwitterIcon();
    }

    @Then("^Twitter login page is present$")
    public void twitterLoginPageIsPresent() {
        assertTrue(blogPage.isTwitterLoginPagePresent());
    }

    @Then("^click on first blog image and verify selected blog page open$")
    public void clickFirstBlogImageVerifyBlogPageOpen()  {
        blogPage.clickFirstBlogImageVerifyBlogPageOpen();
    }

    @Then("^click on first blog link and verify selected blog page open$")
    public void clickFirstBlogLinkVerifyBlogPageOpen()  {
        blogPage.clickFirstBlogLinkVerifyBlogPageOpen();
    }
}
