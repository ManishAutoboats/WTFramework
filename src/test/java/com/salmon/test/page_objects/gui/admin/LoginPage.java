package com.salmon.test.page_objects.gui.admin;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Locale;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.salmon.test.page_objects.gui.constants.Locale.GLODE;

public class LoginPage extends PageObject {
    private DashBoardPage dashboardPage;
    public LoginPage( DashBoardPage dashboardPage){
        this.dashboardPage=dashboardPage;
    }

    // Below is to get around Zscaler Salmon authenication page
    public By userNameInput = By.cssSelector("#username");
    public By passwordInput = By.cssSelector("#login");
    public By signInButton = By.cssSelector("button[class='action-login action-primary']");

    // ******************* PAGE ACTIONS
    public void enterUserName() {
        String username;
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case GLODE:
            case VUSEUK:
            case VUSEIT:
            case VUSEZA:
            case PL:
            case VUSECO:
            case KZ:
            case LYFTSE:
            case EPOK:
            case VUSEFR:
            case VUSEDE:
            case VELOBE:
                username = UrlBuilder.getMessage("magento.admin.username");
                break;
            default:
                username = Props.getProp("magento.admin.username");
        }

        LOG.info("Admin login account in use is "+ username);
        waitForExpectedElement(userNameInput, 10).sendKeys(username);
    }

    public void enterPassword() {
        String password;
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case GLODE:
            case VUSEUK:
            case VUSEIT:
            case VUSEZA:
            case PL:
            case VUSECO:
            case KZ:
            case LYFTSE:
            case EPOK:
            case VUSEFR:
            case VUSEDE:
            case VELOBE:
                password = UrlBuilder.getMessage("magento.admin.password");
                break;
            default:
                password = Props.getProp("magento.admin.password");
        }
        LOG.info("Admin login password: " + password);
        waitForExpectedElement(passwordInput, 10).sendKeys(password);
    }

    public void clickSignInButton() {
        waitForExpectedElement(signInButton, 10);
        clickByElementByQueryJSExecutor(signInButton);
    }
    public void userNavigatesToMagentoAdminHomePage() {
        UrlBuilder.startMagentoAdminHomePage();
    }
    public void userSubmitsUsernameAndPassword() {
        enterUserName();
        enterPassword();
        clickSignInButton();
    }
    public void userIsLoggedInSuccessfully() {
        Assert.assertTrue(dashboardPage.isDashboardPresent());
    }
    public void userLoginsMagentoAdminHomePageSuccessfully() {
        userNavigatesToMagentoAdminHomePage();
        if (!dashboardPage.isDashboardPresent()) {
            userSubmitsUsernameAndPassword();
            userIsLoggedInSuccessfully();
        }
    }
}
