package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.FAQPage;
import cucumber.api.java.en.And;

public class FAQPageSteps {

    private FAQPage faqVype;

    public FAQPageSteps(FAQPage faqVype) {
        this.faqVype = faqVype;
    }

    @And("^assert content and expands the accordion$")
    public void accordionExpand(){
        faqVype.accordionExpand();
    }

    @And("^users clicks on the FAQ link$")
    public void clickFaq(){
        faqVype.clickFaq();
    }

    @And("^user collapses the accordion$")
    public void accordionCollapse(){
        faqVype.accordionCollapse();
    }

    @And("^user clicks on contact us$")
    public void contactUsNavigate(){
        faqVype.contactUsNavigate();
    }
}
