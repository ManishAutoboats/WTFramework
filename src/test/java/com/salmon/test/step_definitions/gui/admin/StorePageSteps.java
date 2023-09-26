package com.salmon.test.step_definitions.gui.admin;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.admin.StorePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class StorePageSteps {

    private StorePage storePage;
    public StorePageSteps(StorePage storePage){
    this.storePage=storePage;
}


    @And("^user navigate to Store Configuration page$")
    public void userNavigateToStoreConfigPage() {
    storePage.userNavigateToStoreConfigPage();
}

    @And("^navigate to NEMID Configuration page$")
    public void userNavigateToNEMIDPage() {
        storePage.userNavigateToNEMIDPage();
    }

    @And("^user disables the NemID for selected Stores$")
    public void disableNemID(){
    storePage.disableNemID("Vype Denmark");
    storePage.disableNemID("Lyft Denmark");

    }

    @Then("^3DS subs is turned off for given locale$")
    public void dsSubsIsTurnedOffForGivenLocale() {
        switch (UrlBuilder.getLocale()){
            case "lyftse":
                storePage.ensure3DSisTurnedOff("Lyft Sweden");
                break;
            case "vuseit":
                storePage.ensure3DSisTurnedOff("Vuse Italy");
                break;
            case "vusede":
                storePage.ensure3DSisTurnedOff("Vuse Germany");
                break;
        }
    }
}
