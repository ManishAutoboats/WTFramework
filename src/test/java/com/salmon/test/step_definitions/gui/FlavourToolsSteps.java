package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.FlavourToolsPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import java.util.List;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;

public class FlavourToolsSteps {
    private FlavourToolsPage flavourToolsPage;

    public FlavourToolsSteps(FlavourToolsPage flavourToolsPage) {
        this.flavourToolsPage = flavourToolsPage;
    }

    @And("^user click flavour tools menu$")
    public void userClicksFlavourToolsMenu() throws Throwable {
        flavourToolsPage.clickFlavourToolsMenu();
    }

    @And("^user start flavour tools by clicking lets go$")
    public void userStartFlavourTools() throws Throwable {
        flavourToolsPage.clickLETSGoBtn();
    }

    @And("^user clicks CTA CREATE YOUR FLAVOUR PROFILE$")
    public void userClickYourFlavourProfile() throws Throwable {
        flavourToolsPage.createYourFlavourProfile();
    }

    @And("^user clicks CTA EXPLORE THE RANGE$")
    public void userClickExploreTheRange() throws Throwable {
        flavourToolsPage.exploreRange();
    }

    @And("^user clicks CTA JUMP BACK IN$")
    public void userClickJumpBackIn() throws Throwable {
        flavourToolsPage.jumpBackIn();
    }

    @And("^user is directed to flavour profile submenu$")
    public void assertFlavourProfileDisplay() throws Throwable {
        flavourToolsPage.verifyFlavourProfileMenuDisplay();
    }

    @And("^sizzle video plays$")
    public void sizzleVideoPlays() throws Throwable {
        flavourToolsPage.playSizzleVideo();
    }

    @And("^the '(.*)' '(.*)' '(.*)' display correctly on question page$")
    public void assertTitleOnQuestionPage(String h5_str, String h2_str, String smallTitle) throws Throwable {
        assertTrue(flavourToolsPage.verifyBuyDevicesTitles(h5_str, h2_str, smallTitle), "title display is incorrect on question page ");
    }

    @And("^the '(.*)' '(.*)' display correctly on buy devices page$")
    public void assertTitleOnBuyDevicesPage(String h5_str,String smallTitle) throws Throwable {
        assertTrue(flavourToolsPage.verifyBuyDevicesTitles(h5_str, smallTitle), "title display is incorrect on question page ");
    }

    @And("^the process bar is updated on '(.*)'$")
    public void assertProcessBar(String questionPage) throws Throwable {
        assertTrue(flavourToolsPage.verifyProgressBar(questionPage));
    }

    @And("^user select options from device question page$")
    public void selectDeviceOptions(DataTable dataTable) throws Throwable {
        //flavourToolsPage.closeMigrationPopUp();
        List<List<String>> deviceList = dataTable.raw();
        for (int i = 1; i < deviceList.size(); i++) {
            flavourToolsPage.selectDeviceOptions(deviceList.get(i).get(0));
        }
    }

    @And("^user select options from nicotine question page$")
    public void selectNicotineOptions(DataTable dataTable) throws Throwable {
        List<List<String>> nicotineList = dataTable.raw();
        for (int i = 1; i < nicotineList.size(); i++) {
            flavourToolsPage.selectNicotineOptions(nicotineList.get(i).get(0));
        }
    }

    @And("^the next button displays$")
    public void assertNextButton() throws Throwable {
        flavourToolsPage.verifyNextBtnDisplay();
    }

    @And("^user click Next Btn$")
    public void clickNextButton() throws Throwable {
        flavourToolsPage.clickNextBtn();
    }

    @And("^user select options from taste question page$")
    public void selectTasteOptions(DataTable dataTable) throws Throwable {
        List<List<String>> tasteList = dataTable.raw();
        for (int i = 1; i < tasteList.size(); i++) {
            flavourToolsPage.selectTasteOptions(tasteList.get(i).get(0));
        }
    }

    @And("^user select options from sensation question page$")
    public void selectSensationOptions(DataTable dataTable) throws Throwable {
        List<List<String>> sensationList = dataTable.raw();
        for (int i = 1; i < sensationList.size(); i++) {
            flavourToolsPage.selectSensationOptions(sensationList.get(i).get(0));
        }
    }

    @And("^user select options from flavour question page$")
    public void selectFlavourOptions(DataTable dataTable) throws Throwable {
        List<List<String>> flavourList = dataTable.raw();
        for (int i = 1; i < flavourList.size(); i++) {
            flavourToolsPage.selectFlavourOptions(flavourList.get(i).get(0));
        }
    }

    @And("^user select options from image question page$")
    public void selectImageOptions(DataTable dataTable) throws Throwable {
        List<List<String>> imageList = dataTable.raw();
        for (int i = 1; i < imageList.size(); i++) {
            flavourToolsPage.selectImageOptions(imageList.get(i).get(0));
        }
    }

    @And("^the back button displays$")
    public void assertBackButton() throws Throwable {
        flavourToolsPage.verifyBackBtnDisplay();
    }

    @When("^the user selects shop epod$")
    public void theUserSelectsShopEpod() {
        flavourToolsPage.selectShopEpod();
    }

    @And("^user selects a colour and add to basket$")
    public void userSelectsAColourAndAddToBasket() {
        //flavourToolsPage.selectDeviceColour();
        flavourToolsPage.addToBasket();
    }

    @And("^user selects add to basket$")
    public void userSelectsAddToBasket() throws InterruptedException {
        flavourToolsPage.waitForPage();
        Thread.sleep(3000);
        flavourToolsPage.addToBasket();
        flavourToolsPage.waitForPage();
        Thread.sleep(3000);
    }

    @And("^user enters username '(.*)' and password '(.*)' in the flavourtool login popup$")
    public void userEntersValidSigninDetailsPopup(String email, String password) {
        flavourToolsPage.fillInPopupLoginForm(
                UrlBuilder.getMessage(email),
                UrlBuilder.getMessage(password)
        );
    }

    @And("^user submits the flavourtool login popup form$")
    public void userEntersValidSigninDetailsPopup() {
        flavourToolsPage.submitPopupLoginForm();
    }

    @And("^user clicks continue with flavour profile$")
    public void userClicksContinueWithFlavourProfile() {
        flavourToolsPage.selectContinueWithFlavourProfile();
        flavourToolsPage.waitForPage();
    }

    @And("^user click Back btn$")
    public void userClickBackBtn() throws Throwable {
        flavourToolsPage.clickBackBtn();
    }

    @And("^vuse epod device should be selected$")
    public void vuseEpodDeviceShouldBeSelected() throws Throwable {
        assertTrue(flavourToolsPage.isEpodDeviceSelected());
    }

    @Then("^the count down video displays$")
    public void assertCountDownVideo() throws Throwable {
        flavourToolsPage.verifyCountDownVideoDisplay();
        Thread.sleep(6000);
    }

    @And("^user update your profile$")
    public void clickUpdateYourProfileButton() throws Throwable {
        flavourToolsPage.clickUpdateYourProfileBtn();
    }

    @And("^user save profile$")
    public void clickSaveProfileButton() throws Throwable {
        flavourToolsPage.saveProfile();
    }

    @Then("^verify profiled is saved$")
    public void verifyProfiledSaved() throws Throwable {
        assertTrue(flavourToolsPage.verifyProfileSaved());
    }

    @And("^user click Back btn on popup$")
    public void userClickBackBtnPopup() throws Throwable {
        flavourToolsPage.clickBackBtnPopup();
    }

    @And("^display the top menu$")
    public void scrollUp() throws Throwable {
        flavourToolsPage.dislayTheTopMenu();
    }

    @And("^user click ADD SELECTED FLAVOURS btn$")
    public void userClickAddSelectedFlavour() throws Throwable {
        flavourToolsPage.addSelectedFlavour();
    }

    @And("^user selects weight for each product on recommendation pop up$")
    public void userSelectsWeight() {
        flavourToolsPage.selectWeightOnRecommendationPopUp();
    }

    @And("^user clicks ADD SELECTED FLAVOURS TO BASKET on reommendation pop up$")
    public void userClickAddSelectedFlavourToBasket() throws Throwable {
        flavourToolsPage.clickAddSelectedFlavoursToBasketBtn();
    }

    @And("^user clicks back button on recommendation pop up$")
    public void userClicksBackBtnOnRecomPopup() throws Throwable {
        flavourToolsPage.clickBackBtnRecomPopup();
    }

    @And("^user clicks on Lets go button to initiate Vuse flavour selector$")
    public void userClicksOnLetsGoButtonToInitiateVuseFlavourSelector() throws Throwable {
        flavourToolsPage.waitForExpectedElement(flavourToolsPage.FLAVOUR_TOOL_LETSGO_BTN, 5).click();
    }

    @And("^assert sizzle video is not displayed on Flavours landing page$")
    public void assertSizzleVideoIsNotDisplayedOnFlavoursLandingPage() throws Throwable {
        assertTrue(flavourToolsPage.getWebDriver().findElements(flavourToolsPage.SIZZLE_VIDEO_FLAVOURS_QUIZ).size()==0);
    }

    @And("^user clicks on Start Now button to start the quiz$")
    public void userClicksOnStartNowButtonToStartTheQuiz() throws Throwable {
        flavourToolsPage.waitForExpectedElement(flavourToolsPage.FLAVOUR_TOOL_START_BTN, 5).click();
    }
}
