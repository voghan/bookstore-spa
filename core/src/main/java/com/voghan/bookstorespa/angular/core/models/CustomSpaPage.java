package com.voghan.bookstorespa.angular.core.models;

import com.adobe.aem.spa.project.core.models.Page;

public interface CustomSpaPage extends Page {

    String getPageTitle();

    String getMeatDescription();

    String getMetaRobots();
}
