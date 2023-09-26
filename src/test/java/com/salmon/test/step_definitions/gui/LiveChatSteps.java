package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.LiveChatPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class LiveChatSteps {

    private LiveChatPage liveChatPage;

    public LiveChatSteps(LiveChatPage liveChatPage) {
        this.liveChatPage = liveChatPage;
    }

    @And("user clicks on the contact icon at the top right of the page$")
    public void userClicksOnContactIconOnTopRightOfThePage() {
        liveChatPage.clickOnContactIconOnTopRightOfThePage();
    }

    @And("assert salesforce Chat pop-up appears$")
    public void assertSalesforceChatPopUp() {
        liveChatPage.assertSalesforceChatPopUp();
    }

    @And("assert Chat pop-up appears with hamburger option displayed$")
    public void assertChatPopUpAppearsWithHamburgerOption() {
        liveChatPage.assertChatPopUpAppearsWithHamburgerOptionDisplayed();
    }

    @And("user click on chat hamburger$")
    public void userClickOnChatHamburger() {
        liveChatPage.clickOnChatHamburger();
    }

    @And("click on speak to agent option if available$")
    public void cClickOnSpeakToAgentOption() {
        liveChatPage.clickOnSpeakToAgentOption();
    }

    @And("user clicks on the Live chat icon at the bottom right of the page$")
    public void userClicksOnLiveChatIconOnBottomRightOfThePage() {
        liveChatPage.clickOnLiveChatIconOnBottomRightOfThePage();
    }


    @And("^assert Live Chat pop-up appears after waiting for the specified delay and assert CTA$")
    public void assertLiveChatPopUpAppearsAfterWaitingForSpecifiedDelayTime() {
        liveChatPage.assertLiveChatPopUpAfterSpecifiedDelayTimeAndAssertCTA();
    }

    @And("user clicks on the Live chat pop-up at the bottom right of the page$")
    public void userClicksOnLiveChatPopUpOnBottomRightOfThePage() {
        liveChatPage.clickOnLiveChatPopUpOnBottomRightOfThePage();
    }

    @And("verify user is able to close the Live Chat pop-up$")
    public void verifyUserIsAbleToCloseLiveChatPopUp() {
        liveChatPage.verifyUserClosesTheLiveChatPopUp();
    }

    @And("assert pop-up no longer appears on any page during the session$")
    public void assertPopUpNoLongerAppearsOnAnyPageDuringTheSession() {
        liveChatPage.assertPopUpNoLongerAppearsOnAnyPage();
    }

    @When("^user clicks on the Whatsapp chat icon at the bottom right of the page$")
    public void userClicksOnTheWhatsappChatIconAtTheBottomRightOfThePage() {
        liveChatPage.clicksWhatsappChatIconAtTheBottomRightOfThePage();
    }

    @And("assert CTA for Live chat icon at the bottom right of the page$")
    public void assertCTAForLiveChatIconAtBottomRightOfThePage() {
        liveChatPage.assertCTAForLiveChatIconAtBottomRightOfThePage();
    }

    @And("assert agent is asking the user first name$")
    public void assertAgentAskingFirstName() {
        assertTrue(liveChatPage.waitForExpectedElement(liveChatPage.FIRST_NAME_TEXT,10).isDisplayed());
    }

    @And("assert username is displayed with expected text$")
    public void assertUserNameAndExpectedText() {
        String firstName = System.getProperty("UserFirstName.key");
        liveChatPage.getTextName();
        assertTrue(liveChatPage.waitForExpectedElement(liveChatPage.FIRST_NAME_TEXT,10).isDisplayed());
    }

    @And("user clicks on the Live chat icon at the bottom right of the page mobile$")
    public void userClicksOnLiveChatIconOnBottomRightOfThePageMobile() {
        liveChatPage.clickOnLiveChatIconOnBottomRightOfThePageMobile();
    }
}
