package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class WhatWeDoPage extends PageObject {

    private final By featuredProjects=By.cssSelector("div.featured-projects-block.three-col div.wrapper h2.block-title.charcoal");


    public boolean featuredProjectsIsDisplayed() {
       return isElementPresent(featuredProjects);
    }
}
