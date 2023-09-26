package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class LiveChatPage extends PageObject {

    //Chat Icon
    private static final By WHATSAPP_CHAT_ICON = By.cssSelector(".whatsapp-chat-widget");
    private static final By UNYCO_GUI_CHAT_FRAME = By.cssSelector("iframe#unyco_gui_frame");
    private static final By UNYCO_CHAT_POPUP = By.cssSelector("#app-unyco");
    private static final By UNYCO_MINIFIED_CHAT_FRAME = By.cssSelector("#unyco_minified_chat");
    private static final By CHAT_ICON = By.cssSelector("div#openChat");
    private final static By HEADER_CONTACT_ICON = By.cssSelector("div.column.contact.desktop-only:nth-child(2) a:nth-child(1) > i.material-icons");
    private final static By SF_CHAT_POPUP = By.cssSelector("div.salesforce-chat-popup");
    private final static By LIVE_CHAT_POPUP_VISIBLE = By.xpath("//aside[@role='dialog'][contains(@class,'modal-popup modal salesforce-chat-modal')][@data-type='popup'][@style]");
    private final static By CHAT_CLOSE_BUTTON = By.cssSelector("aside.modal-popup.modal.salesforce-chat-modal.modal-slide._inner-scroll._show div.modal-inner-wrap header.modal-header > button.action-close");
    private final static By OFFLINE_CHAT_ICON = By.cssSelector("a.chat-icon");
    private final static By OFFLINE_CHAT_ICON_VUSEDK = By.cssSelector("#liveagent-offline > a");
    private final static By ONLINE_CHAT_ICON = By.cssSelector("div.chat-icon");
    private final static By ONLINE_CHAT_ICON_DE = By.cssSelector("a#liveagent_button_online");
    private final static By OFFLINE_CHAT_AGENT = By.xpath("//div[@id='liveagent-online'][@style]");
    private final static By ONLINE_CHAT_AGENT = By.xpath("//div[@id='liveagent-offline'][@style]");
    private final static By OFFLINE_CHAT_AGENT_DE = By.xpath("//*[contains(@id,'liveagent_button_offline')][not(contains(@style,'display:none'))]");
    private final static By OFFLINE_CHAT_AGENT_VELODE = By.xpath("(//*[contains(@id,'liveagent_button_offline')][not(contains(@style,'display:none'))])[2]");
    private final static By ONLINE_CHAT_WINDOW = By.cssSelector("div#liveAgentClientChat");
    private final static By ONLINE_CHAT_OPERATOR = By.cssSelector("div.operator");
    private static final By CHAT_ICON_UK = By.cssSelector(".chat-icon");
    private final static By LIVE_CHAT_REMINDER_POPUP = By.cssSelector("div.liveagent-reminder-popup");
    private final static By LIVE_CHAT_REMINDER_POPUP_CLOSE_BUTTON = By.cssSelector("div.liveagent-reminder-popup__logo-wrapper > i.material-icons.close");
    private final static By LIVE_CHAT_REMINDER_POPUP_CLOSED = By.cssSelector("div.chat-action:nth-child(11) > div.liveagent-reminder-popup--hidden");
    private static final By ONLINE_CHAT_AGENT_GLO = By.xpath("//a[contains(@id,'liveagent_button_online')]");
    private static final By OFFLINE_CHAT_AGENT_GLO = By.xpath("//div[contains(@id,'liveagent_button_offline')]");
    private static final By ONLINE_CHAT_AGENT_MX = By.xpath("//div[contains(@id,'liveagent-online')]");
    private static final By ONLINE_CHAT_AGENT_MX_TXT = By.xpath("//a[contains(@id,'liveagent_button_online')]");
    private static final By OFFLINE_CHAT_AGENT_MX = By.xpath("//div[contains(@id,'liveagent_button_offline')]");
    private static final By PRE_CHAT_SUBMIT_BTN = By.cssSelector("#prechat_submit");
    public final static By CHECKOUT_RETURN_BUTTON = By.cssSelector("button#sc-previous");
    private static final By OFFLINE_CHAT_BUTTON_VUSE_FR_UK = By.cssSelector(".uiButton.helpButtonDisabled");
    private static final By ONLINE_CHAT_BUTTON_VUSE_FR_UK = By.cssSelector(".uiButton.helpButtonEnabled");
    private static final By CHAT_BUTTON_VUSE_FR_UK = By.cssSelector("div.embeddedServiceHelpButton > div > button");
    private static final By CHAT_BUTTON_VUSE_DE = By.xpath("//*[contains(@id,'liveagent')]");
    private static final By CHAT_BUTTON_VUSE_ZA = By.cssSelector("#liveagent-offline");
    private static final By CHAT_POPUP_VUSE_FR_UK = By.cssSelector(".dockableContainer.showDockableContainer");
    private static final By CHAT_TEXT_AREA_VUSE_FR_UK = By.cssSelector("textarea.uiInput.uiInputTextArea");
    private static final By CONFIRM_CHAT_CLOSE_BUTTON_VUSE_FR_UK = By.cssSelector(".uiButton.embeddedServiceSidebarButton");
    private static final By CLOSE_CHat_BUTTON_VUSE_FR_UK = By.cssSelector(".endChatButton.closeChatButton");
    private static final String CHAT_ICON_IFRAME_VUSECO = "launcher";
    private static final By OFFLINE_CHAT_BUTTON_VUSECO = By.cssSelector("button[aria-label='Chat']");
    private static final By ONLINE_SUPPORT_BUTTON_VUSECO = By.cssSelector("button[aria-label='Support']");
    private static final String CHAT_POP_UP_VUSECO = "webWidget";
    private static final By OFFLINE_CHAT_WINDOW_VUSECO = By.cssSelector("div[data-embed='chat']");
    private static final By ONLINE_SUPPORT_WINDOW_VUSECO = By.cssSelector("div[data-embed='ticketSubmissionForm']");
    private static final By MINIMISE_CHAT_BTN_VUSECO = By.cssSelector("button[type=button]");
    private static final By CHAT_ICON_VUSECO = By.cssSelector("#launcher");
    private static final By CHAT_HAMBURGER = By.xpath("//*[local-name()='svg'][@class='slds-button__icon']//*[name()='path']");
    private static final By SPEAK_TO_AGENT = By.xpath("//a[span[@class='slds-truncate' and text()='I want to speak to a live agent']]");
    public static final By FIRST_NAME_TEXT = By.xpath("//div[text()='What is your first name?']");
    public static final By FIRST_NAME_TEXT_LOGGEDIN_USER = By.xpath("//div[contains(@class,'embeddedServiceLiveAgentStateChatPlaintextMessageDefaultUI')]/div");
    private static final By CHAT_BUTTON_VUSE_IT = By.cssSelector("#openChat");
    private static final By CHAT_BUTTON_VELO_PL = By.cssSelector("div#liveagent-online  img");

    public void clickOnContactIconOnTopRightOfThePage() {
        waitForExpectedElement(HEADER_CONTACT_ICON).click();
    }

    public void assertLiveChatPopUpAfterSpecifiedDelayTimeAndAssertCTA() {
        if (!System.getProperty("intDelayTime").isEmpty()) {
            waitForExpectedElement(LIVE_CHAT_REMINDER_POPUP, Integer.parseInt(System.getProperty("intDelayTime")));
            assertTrue(getWebDriver().findElements(LIVE_CHAT_REMINDER_POPUP).size() > 0);
            assertTrue(waitForExpectedElement(LIVE_CHAT_REMINDER_POPUP).getText().contains("Any questions?") && waitForExpectedElement(LIVE_CHAT_REMINDER_POPUP).getText().contains("We are happy to help!"));
            clickOnLiveChatPopUpOnBottomRightOfThePage();
            assertSalesforceChatPopUp();
        }
    }

    public void clickOnLiveChatPopUpOnBottomRightOfThePage() {
        waitForExpectedElement(LIVE_CHAT_REMINDER_POPUP).click();
    }

    public void verifyUserClosesTheLiveChatPopUp() {
        if (!System.getProperty("intDelayTime").isEmpty()) {
            waitForExpectedElement(LIVE_CHAT_REMINDER_POPUP_CLOSE_BUTTON, Integer.parseInt(System.getProperty("intDelayTime")));
            if (getWebDriver().findElements(LIVE_CHAT_REMINDER_POPUP_CLOSE_BUTTON).size() > 0)
                clickFirstElementByQueryJSExecutor(LIVE_CHAT_REMINDER_POPUP_CLOSE_BUTTON);
            else
                clickByElementByQueryJSExecutor(LIVE_CHAT_REMINDER_POPUP_CLOSE_BUTTON);
            assertPopUpNoLongerAppearsOnAnyPage();
        }
    }

    public void assertPopUpNoLongerAppearsOnAnyPage() {
        assertTrue(getWebDriver().findElements(LIVE_CHAT_REMINDER_POPUP_CLOSED).size() > 0);
    }

    public void assertCTAForLiveChatIconAtBottomRightOfThePage() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case UK:
            case VUSEUK:
            case VUSEFR:
                assertTrue(isElementDisplayedBySeconds(CHAT_BUTTON_VUSE_FR_UK,10),
                        "chat icon/button is missing in the page");
                break;
            case VUSEDE:
            case VELODE:
                assertTrue(getWebDriver().findElements(CHAT_BUTTON_VUSE_DE).size() > 0,
                        "chat icon/button is missing in the page");
                break;
            case VUSECO:
                assertTrue(waitForExpectedElement(CHAT_ICON_VUSECO, 10).isDisplayed(),
                        "chat icon/button is missing in the page");
                break;
            case VUSEZA:
                assertTrue(getWebDriver().findElements(CHAT_BUTTON_VUSE_ZA).size() > 0,
                        "chat icon/button is missing in the page");
                break;
            case VUSEIT:
                assertTrue(getWebDriver().findElements(CHAT_BUTTON_VUSE_IT).size() > 0,
                        "chat icon/button is missing in the page");
                break;
            case VELOPL:
                assertTrue(getWebDriver().findElements(CHAT_BUTTON_VELO_PL).size() > 0,
                        "chat icon/button is missing in the page");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Locale.valueOf(UrlBuilder.getLocale().toUpperCase()));
        }
    }

    public void clickOnLiveChatIconOnBottomRightOfThePage() {
        if (UrlBuilder.getLocale().equalsIgnoreCase("fr") && getWebDriver().findElements(CHECKOUT_RETURN_BUTTON).size() > 0)
            clickByElementByQueryJSExecutor(CHECKOUT_RETURN_BUTTON);
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case IT:
                WebElement chatFrame = waitForExpectedElement(UNYCO_MINIFIED_CHAT_FRAME, 10);
                getWebDriver().switchTo().frame(chatFrame);
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                waitForExpectedElement(CHAT_ICON, 10).click();
                getWebDriver().switchTo().defaultContent();
                break;
            case VYPEIT:
            case PL:
            case GLODE:
            case LYFTSE:
                if (getWebDriver().findElement(ONLINE_CHAT_AGENT_GLO).getAttribute("style").equals("display: none;")) {
                    assertEquals(waitForExpectedElement(OFFLINE_CHAT_AGENT_GLO).getText(), UrlBuilder.getMessage("offlineChatLinkText.key"));
                } else if (getWebDriver().findElement(OFFLINE_CHAT_AGENT_GLO).getAttribute("style").equals("display: none;")) {
                    assertEquals(waitForExpectedElement(ONLINE_CHAT_AGENT_GLO).getText(), UrlBuilder.getMessage("onlineChatLink.key"));
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("onlineChatLink.key"))).click();
                }
                break;
            case MX:
                if (getWebDriver().findElement(ONLINE_CHAT_AGENT_MX).getAttribute("style").equals("display: none;")) {
                    assertThat(waitForExpectedElement(OFFLINE_CHAT_AGENT_MX).getText()).contains(UrlBuilder.getMessage("offlineChatLinkText.key"));
                } else if (getWebDriver().findElement(OFFLINE_CHAT_AGENT_MX).getAttribute("style").equals("display: none;")) {
                    assertEquals(waitForExpectedElement(ONLINE_CHAT_AGENT_MX_TXT).getText(), UrlBuilder.getMessage("onlineChatLink.key"));
                    waitForExpectedElement(By.linkText(UrlBuilder.getMessage("onlineChatLink.key"))).click();
                }
                break;
            case VUSECO:
                waitForPage(10);
                frameToBeAvailableAndSwitchToIt(CHAT_ICON_IFRAME_VUSECO);
                if (getWebDriver().findElements(OFFLINE_CHAT_BUTTON_VUSECO).size() > 0) {
                    waitForExpectedElement(OFFLINE_CHAT_BUTTON_VUSECO, 10).click();
                } else if (getWebDriver().findElements(ONLINE_SUPPORT_BUTTON_VUSECO).size() > 0) {
                    waitForExpectedElement(ONLINE_SUPPORT_BUTTON_VUSECO, 10).click();
                }
                getWebDriver().switchTo().defaultContent();
            case VUSEUK:
            case VUSEFR:
            case VUSEDE:
            case VUSEIT:
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                waitForExpectedElement(ONLINE_CHAT_BUTTON_VUSE_FR_UK,5);
                if (getWebDriver().findElements(OFFLINE_CHAT_BUTTON_VUSE_FR_UK).size() > 0) {
                    assertThat(waitForExpectedElement(OFFLINE_CHAT_BUTTON_VUSE_FR_UK).getText())
                            .contains(UrlBuilder.getMessage("offlineChatLinkText.key"));
                } else if (getWebDriver().findElements(ONLINE_CHAT_BUTTON_VUSE_FR_UK).size() > 0) {
                    assertThat(waitForExpectedElement(ONLINE_CHAT_BUTTON_VUSE_FR_UK).getText())
                            .contains(UrlBuilder.getMessage("onlineChatLink.key"));
                    getWebDriver().findElement(ONLINE_CHAT_BUTTON_VUSE_FR_UK).click();
                }
                break;
            case VELODE:
                if (getWebDriver().findElements(OFFLINE_CHAT_AGENT_VELODE).size() > 0) {
                    LOG.info("No action performed when Chat agent is offline");
                } else if (getWebDriver().findElements(ONLINE_CHAT_AGENT_GLO).size() > 0) {
                    clickByElementByQueryJSExecutor(ONLINE_CHAT_AGENT_GLO);
                }
                break;
            case VELOPL:
                waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                clickByElementByQueryJSExecutor(CHAT_BUTTON_VELO_PL);
                break;
            case VUSEDK:
                clickChatIcon(OFFLINE_CHAT_ICON_VUSEDK, ONLINE_CHAT_ICON);
                break;
            case UK:
                clickChatIcon(OFFLINE_CHAT_ICON, CHAT_ICON_UK);
                break;
            default:
                clickChatIcon(OFFLINE_CHAT_ICON, ONLINE_CHAT_ICON);
        }
    }

    private void clickChatIcon(By offlineChatIcon, By onlineChatIcon) {
        if (getWebDriver().findElements(OFFLINE_CHAT_AGENT).size() > 0) {
            try {
                waitForExpectedElement(offlineChatIcon, 10).click();
            } catch (Exception e) {
                clickByElementByQueryJSExecutor(offlineChatIcon);
            }
        } else if (getWebDriver().findElements(ONLINE_CHAT_AGENT).size() > 0) {
            try {
                waitForExpectedElement(onlineChatIcon, 10).click();
            } catch (Exception e) {
                clickByElementByQueryJSExecutor(onlineChatIcon);
            }
        }
    }

    public void assertChatPopUpAppearsWithHamburgerOptionDisplayed() {
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        if (getWebDriver().findElements(OFFLINE_CHAT_BUTTON_VUSE_FR_UK).size() > 0) {
            assertFalse(getWebDriver().findElements(CHAT_POPUP_VUSE_FR_UK).size() > 0);
        } else if (getWebDriver().findElements(ONLINE_CHAT_BUTTON_VUSE_FR_UK).size() > 0) {
            assertTrue(waitForExpectedElement(CHAT_POPUP_VUSE_FR_UK, 20).isDisplayed());
            waitForAjaxElementNotToBePresent(getWebDriver(), 10);
        }
    }

    public void assertSalesforceChatPopUp() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case IT:
                WebElement frame = waitForExpectedElement(UNYCO_GUI_CHAT_FRAME, 10);
                getWebDriver().switchTo().frame(frame);
                assertTrue(waitForExpectedElement(UNYCO_CHAT_POPUP, 10).isDisplayed());
                getWebDriver().switchTo().defaultContent();
                break;
            case VYPEIT:
            case PL:
            case GLODE:
            case LYFTSE:
                if (getWebDriver().findElement(ONLINE_CHAT_AGENT_GLO).getAttribute("style").equals("display: none;")) {
                    assertFalse(getWebDriver().findElements(SF_CHAT_POPUP).size() > 0);
                } else if (getWebDriver().findElement(OFFLINE_CHAT_AGENT_GLO).getAttribute("style").equals("display: none;")) {
                    switchBetweenWindowTabs(1);
                    assertTrue(waitForExpectedElement(ONLINE_CHAT_WINDOW, 10).isDisplayed() || waitForExpectedElement(ONLINE_CHAT_OPERATOR, 10).isDisplayed());
                    switchBetweenWindowTabs(0);
                }
                break;
            case VUSEUK:
            case VUSEFR:
            case VUSEDE:
            case VUSEIT:
                waitForAjaxElementNotToBePresent(getWebDriver(),10);
                if (getWebDriver().findElements(OFFLINE_CHAT_BUTTON_VUSE_FR_UK).size() > 0) {
                    assertFalse(getWebDriver().findElements(CHAT_POPUP_VUSE_FR_UK).size() > 0);
                } else if (getWebDriver().findElements(ONLINE_CHAT_BUTTON_VUSE_FR_UK).size() > 0) {
                    try{
                        assertTrue(waitForExpectedElement(CHAT_POPUP_VUSE_FR_UK, 10).isDisplayed());}
                    catch(Exception e)
                    {
                        waitForExpectedElement(CHAT_BUTTON_VUSE_FR_UK).click();
                        assertTrue(waitForExpectedElement(CHAT_POPUP_VUSE_FR_UK, 10).isDisplayed());}
                    waitForAjaxElementNotToBePresent(getWebDriver(), 10);
                    waitForExpectedElement(CHAT_TEXT_AREA_VUSE_FR_UK, 10).sendKeys(Keys.ESCAPE);
                    waitForExpectedElement(CONFIRM_CHAT_CLOSE_BUTTON_VUSE_FR_UK, 10).click();
                    waitForExpectedElement(CLOSE_CHat_BUTTON_VUSE_FR_UK, 10).click();
                }
                break;
            case VELODE:
                if (getWebDriver().findElements(OFFLINE_CHAT_AGENT_DE).size() > 0) {
                    LOG.info("No action performed when Chat agent is offline");
                } else if (getWebDriver().findElements(ONLINE_CHAT_ICON_DE).size() > 0) {
                    switchBetweenWindowTabs(1);
                    assertTrue(waitForExpectedElement(ONLINE_CHAT_WINDOW, 10).isDisplayed() || waitForExpectedElement(ONLINE_CHAT_OPERATOR, 10).isDisplayed());
                    switchBetweenWindowTabs(0);
                }
                break;
            case VUSECO:
                frameToBeAvailableAndSwitchToIt(CHAT_POP_UP_VUSECO);
                if (getWebDriver().findElements(OFFLINE_CHAT_WINDOW_VUSECO).size() > 0) {
                    assertTrue(getWebDriver().findElement(OFFLINE_CHAT_WINDOW_VUSECO).isDisplayed());
                } else if (getWebDriver().findElements(ONLINE_SUPPORT_WINDOW_VUSECO).size() > 0) {
                    assertTrue(getWebDriver().findElement(ONLINE_SUPPORT_WINDOW_VUSECO).isDisplayed());
                }
                getWebDriver().findElement(MINIMISE_CHAT_BTN_VUSECO).click();
                getWebDriver().switchTo().defaultContent();
               break;
            case MX:
                if (getWebDriver().findElement(ONLINE_CHAT_AGENT_MX).getAttribute("style").equals("display: none;")) {
                    assertFalse(getWebDriver().findElements(SF_CHAT_POPUP).get(0).isDisplayed());
                } else if (getWebDriver().findElement(OFFLINE_CHAT_AGENT_MX).getAttribute("style").equals("display: none;")) {
                    switchBetweenWindowTabs(1);
                    if (isElementDisplayedBySeconds(PRE_CHAT_SUBMIT_BTN, 5)) {
                        waitForExpectedElement(PRE_CHAT_SUBMIT_BTN).click();
                    }
                    assertTrue(waitForExpectedElement(ONLINE_CHAT_WINDOW, 10).isDisplayed() || waitForExpectedElement(ONLINE_CHAT_OPERATOR, 10).isDisplayed());
                    switchBetweenWindowTabs(0);
                }
                break;
            default:
                if (getWebDriver().findElements(OFFLINE_CHAT_AGENT).size() > 0) {
                    assertTrue(waitForExpectedElement(SF_CHAT_POPUP, 10).isDisplayed());
                    assertTrue(waitForExpectedElement(LIVE_CHAT_POPUP_VISIBLE, 10).isDisplayed());
                    clickByElementByQueryJSExecutor(CHAT_CLOSE_BUTTON);
                } else if (getWebDriver().findElements(ONLINE_CHAT_AGENT).size() > 0) {
                    switchBetweenWindowTabs(1);
                    assertTrue(waitForExpectedElement(ONLINE_CHAT_WINDOW, 10).isDisplayed() || waitForExpectedElement(ONLINE_CHAT_OPERATOR, 10).isDisplayed());
                    switchBetweenWindowTabs(0);
                }
        }
    }

    public void clicksWhatsappChatIconAtTheBottomRightOfThePage() {
        waitForExpectedElement(WHATSAPP_CHAT_ICON,5);
        assertTrue(getWebDriver().findElement(WHATSAPP_CHAT_ICON).isDisplayed());
        waitForAjaxElementNotToBePresent(getWebDriver(),5);
        waitForItemToBeClickableAndClick(WHATSAPP_CHAT_ICON,5);
    }

    public void clickOnChatHamburger() {
        elementToBeClickable(CHAT_HAMBURGER).click();
    }

    public void clickOnSpeakToAgentOption(){
        if(getWebDriver().findElements(SPEAK_TO_AGENT).size()>0){
            waitForExpectedElement(SPEAK_TO_AGENT,5).click();
            waitForAjaxElementNotToBePresent(getWebDriver(),5);
        }
        else LOG.info("Agent is offline");
    }

    public void getTextName(){
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        List<WebElement> chatElements = getWebDriver().findElements(FIRST_NAME_TEXT_LOGGEDIN_USER);
        String expected= getWebDriver().findElements(FIRST_NAME_TEXT_LOGGEDIN_USER).get(chatElements.size()-1).getText();
        assertTrue(expected.contains(System.getProperty("UserFirstName.key")+" could you give me a brief description"));

    }

    public void clickOnLiveChatIconOnBottomRightOfThePageMobile() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case FR:
            case VUSEFR:
                waitForExpectedElement(CHAT_BUTTON_VUSE_FR_UK,10);
                clickUsingJS(CHAT_BUTTON_VUSE_FR_UK);
                break;
        }
    }
}
