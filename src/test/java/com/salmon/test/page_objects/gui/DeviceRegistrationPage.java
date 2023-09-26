package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.models.DeviceRegistrationPageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class DeviceRegistrationPage extends PageObject {

    private static final By POST_CODE = By.cssSelector("input#post_code");
    public static final By MY_GLO_NUMBER = By.cssSelector("input#device_id");
    private static final By DEVICE_DROPDOWN = By.cssSelector("select#device_type");
    private static final By DEVICE_COLOUR_DROPDOWN = By.cssSelector("select#device_colour");
    private static final By WHERE_DID_YOU_BUY_DROPDOWN = By.cssSelector("select[name='buy_glo']");
    public static final By DEVICE_REGISTRATION_HEADER = By.cssSelector("div.column.main>div:nth-child(1)");
    private static final By DEVICE_NUMBER_VUSEIT = By.cssSelector("input#device_id");
    public static final By VALIDATE_BUTTON = By.cssSelector("button#validate-device-number");
    public static final By DEVICE_MESSAGE = By.cssSelector("span#device_message");
    public static final By VOTE_MESSAGE=By.cssSelector("#postLoyaltyControls > div.post-lc-disclaimer > span");
    public static final By VOTE_BUTTON=By.cssSelector("#postLoyaltyControls > div.post-lc-buttons > button");
    public void populateFormFields(DeviceRegistrationPageModel model) {
        enterDataAndWait(POST_CODE, model.getPostCode());
        enterDataAndWait(MY_GLO_NUMBER, model.getMyGloNumber());
        selectDeviceDropDownValue(model.getDeviceType());
        selectValueFromDropDownByby(model.getDeviceColour(), DEVICE_COLOUR_DROPDOWN);
        selectValueFromDropDownByby(model.getWhereDidYouBuyGlo(), WHERE_DID_YOU_BUY_DROPDOWN);
    }

    public void selectDeviceDropDownValue(String value) {
        selectValueFromDropDownByby(value, DEVICE_DROPDOWN);
    }

    public List<WebElement> getOptionsFromDeviceColourDropDown() {
        return new Select(waitForExpectedElement(DEVICE_COLOUR_DROPDOWN, 10)).getOptions();
    }

    public String getCssStyleContent(String field) {
        Map<String, String> map = new HashMap<>();
        map.put("postcode", "post_code");
        map.put("device", "code");
        map.put("myGloNumber", "device_type");
        map.put("deviceColour", "device_colour");
        map.put("whereDidYouBuyGlo", "buy_glo");

        String content = null;

        for (String key : map.keySet()) {
            if (key.equalsIgnoreCase(field)) {
                content = getAfterSudoCssContent(map.get(key));
            }
        }
        return content;
    }

    private String getAfterSudoCssContent(String cssSelector) {
        LOG.info("getting the Css Content for: " + cssSelector);
        String css = "label[for=\"" + cssSelector + "\"]";
        return getPropertyValueFromCssPseudoByJsExecutor(css, "::after", "content");
    }

    public void enterDeviceNumber(){
        if(UrlBuilder.getLocale().equalsIgnoreCase("it")){
            waitForExpectedElement(MY_GLO_NUMBER,5).sendKeys(random(9,NUMERIC));
            clickByElementByQueryJSExecutor(VALIDATE_BUTTON);
        }
        else{
            waitForExpectedElement(DEVICE_NUMBER_VUSEIT,5).sendKeys(random(9,NUMERIC));
            clickByElementByQueryJSExecutor(VALIDATE_BUTTON);
        }
    }

    public boolean verifyVoteButtonClickable() {
        boolean flag=false;
        List<WebElement> voteButtons=getWebDriver().findElements(VOTE_BUTTON);
        scrollElementIntoView(VOTE_BUTTON);
        sleepFor(8000);
        for(WebElement butt:voteButtons){
            voteButtons=getWebDriver().findElements(VOTE_BUTTON);
            if (!butt.getAttribute("class").contains("active")) {
                hoverOnElement(butt);
                butt.click();
                waitForAjaxElementNotToBePresent(webDriver,5);
            }
        }
        voteButtons=getWebDriver().findElements(VOTE_BUTTON);
        for(WebElement butt:voteButtons){
            if(butt.getAttribute("class").contains("active")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isPageReadytoVote(String voteMessage) {
        return UrlBuilder.getMessage(voteMessage)
                .equalsIgnoreCase(waitForExpectedElement(VOTE_MESSAGE).getText());
    }
}
