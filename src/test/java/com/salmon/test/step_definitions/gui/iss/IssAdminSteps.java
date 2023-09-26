package com.salmon.test.step_definitions.gui.iss;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.admin.StorePage;
import com.salmon.test.page_objects.gui.iss.IssAdminPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class IssAdminSteps {

    private StorePage storePage;
    private IssAdminPage issAdminPage;
    private String outletId;
    private String uniqueOutletid = UrlBuilder.getMessage("uniqueStoreId");

    public IssAdminSteps(StorePage storePage, IssAdminPage issAdminPage) {
        this.storePage = storePage;
        this.issAdminPage = issAdminPage;
    }

    @And("^user navigates to Manage Staff page$")
    public void userNavigatesToManageStaffPage() {
        storePage.navigateToManageStaffPage();
    }

    @And("^user navigates to add new staff page$")
    public void userNavigatesToAddNewStaffPage() {
        storePage.navigateToAddNewStaffpage();
    }

    @Then("^the staff id label is present against the staff id$")
    public void theStaffIdLabelIsPresentAgainstTheStaffId() {
        assertThat(issAdminPage.staffIdLabelDisplayed())
                .as("ERROR: Staff ID label incorrect on admin page").isTrue();
    }

    @And("^user navigates to Physical Stores page$")
    public void userNavigatesToPhysicalStoresPage() {
        storePage.navigateToPhysicalStoresPage();
    }

    @And("^user attempts to add an already existing outlet  id$")
    public void userAttemptsToAddAnAlreadyExistingOutletId() {
        outletId = issAdminPage.getExistingOutletId();
        issAdminPage.addNewStore(outletId);
    }

    @Then("^an error is reported$")
    public void anErrorIsReported() {
        assertThat(issAdminPage.outletIdAlreadyExistsErrorDisplayed())
                .as("ERROR: outlet id already exists error not displayed").isTrue();
    }

    @And("^I correct the outlet id to a unique value$")
    public void iCorrectTheOutletIdToAUniqueValue() {
        issAdminPage.correctOutletIdToUniqueValue(this.uniqueOutletid);
    }

    @And("^I get a success message$")
    public void iGetASuccessMessage() {
        assertThat(issAdminPage.storeSavedSuccessMessageDisplayed())
                .as("ERROR: store saved success message not displayed").isTrue();
    }

    @Then("^I delete this store$")
    public void iDeleteThisStore() {
        issAdminPage.deleteOutletId(this.uniqueOutletid);
    }

    @And("^user navigates to the Manage Translation page$")
    public void userNavigatesToTheManageTranslationPage() {
        storePage.navigateToManageTranslationPage("english");
    }

    @And("^can see a section titled \"([^\"]*)\"$")
    public void canSeeASectionTitled(String translationSection) throws Throwable {
        assertThat(storePage.translationSectionVisible(translationSection))
                .as("ERROR: translation section not found in admin: "+translationSection).isTrue();
    }

    @And("^user ensures a proposed outlet id is unique$")
    public void userEnsuresAProposedOutletIdIsUnique() {
        issAdminPage.ensureOutletIdIsUnique(this.uniqueOutletid);
    }
}
