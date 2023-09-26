package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.MailinatorPage;
import com.salmon.test.page_objects.gui.ReferAFriendPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReferAFriendSteps {

    private ReferAFriendPage referAFriendPage;
    private MailinatorPage mailinatorPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private String email;

    public ReferAFriendSteps(ReferAFriendPage referAFriendPage, MailinatorPage mailinatorPage, HomePage homePage, ScenarioContext scenarioContext) {
        this.referAFriendPage = referAFriendPage;
        this.mailinatorPage = mailinatorPage;
        this.homePage = homePage;
        this.scenarioContext = scenarioContext;
    }

    @And("^click on the send invitations link$")
    public void clickOnTheSendInvitationsLink() {
        referAFriendPage.clickSendInvitationLink();
    }

    @And("^enter the email address of the friend$")
    public void enterTheEmailAddressOfTheFriend() {
        email = "auto_"+ RandomGenerator.randomEmailAddress(7);
        referAFriendPage.enterEmailAddressOfFriend(email);
    }

    @When("^click on submit invitation button$")
    public void clickOnSubmitInvitationButton() {
        referAFriendPage.clickOnSubmitInvitationButton();
    }

    @When("^the friend click on the RAF link received in mailinator$")
    public void theFriendClickOnTheRAFLinkReceivedInMailinator() {
        WebDriverHelper.getWebDriver().manage().deleteAllCookies();
        List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(email);
        mailinatorPage.clickOnEmailWithSubject(receivedEmailElements, UrlBuilder.getMessage("rafInvitaionEmailSubject.key"));
        mailinatorPage.switchToMessageBodyIframe();
        mailinatorPage.waitForPage();
        String url = referAFriendPage.waitForExpectedElement(By.partialLinkText(UrlBuilder.getMessage("referFriendInvitationLink.key"))).getText();
        WebDriverHelper.getWebDriver().navigate().to(url);
    }

    @And("^create a new account with RAF link$")
    public void createANewAccountWithRAFLink() {
        referAFriendPage.createAccountWithRAFLink();
    }

    @Then("^assert that the referrer received the (.*) voucher code in mailinator$")
    public void assertThatTheReferrerReceivedTheVoucherCodeInMailinator(String voucherAmount) {
        String email = (String) scenarioContext.getContext(Context.EMAIL_ID_CREATED_VIA_API_CREATE_ACCOUNT);
        List<WebElement> receivedEmailElements = mailinatorPage.getReceivedEmailElements(email);
        mailinatorPage.clickOnEmailWithSubject(receivedEmailElements, UrlBuilder.getMessage("rafVoucherCodeEmailSubject.key"));
        mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
        mailinatorPage.switchToMessageBodyIframe();
        mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
        assertThat(mailinatorPage.getWebDriver().getPageSource()).contains(UrlBuilder.getMessage("rafVoucherCodeTextInEmail.key") + "code");
        assertThat(mailinatorPage.getWebDriver().getPageSource()).contains(UrlBuilder.getMessage(voucherAmount));
    }
}