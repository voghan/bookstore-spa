package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Title;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomTitle.class,
        ComponentExporter.class }, resourceType = CustomTitle.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomTitle implements Title {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/title";

    private final Logger logger = LoggerFactory.getLogger(CustomTitle.class);

    @ValueMapValue(name = "jcr:title")
    private String text;

    @ValueMapValue(name = "linkURL")
    private String linkUrl;

    @ValueMapValue
    private String type;

    @SlingObject
    private SlingHttpServletRequest servletRequest;

    @ScriptVariable
    PageManager pageManager;

    private Page currentPage;

    private Page linkPage;

    @PostConstruct
    public void initModel() {
        currentPage = pageManager.getContainingPage(servletRequest.getResource().getPath());
        if(StringUtils.isNotBlank(linkUrl) && pageManager != null) {
            linkPage = pageManager.getPage(this.linkUrl);
        }
    }

    @Override
    public String getText() {
        if ( this.text == null) {
            if (StringUtils.isBlank(text) && linkPage != null) {
                text = linkPage.getTitle();
            } else {
                text = StringUtils.defaultIfEmpty(currentPage.getPageTitle(), currentPage.getTitle());
            }
        }
        return text;
    }

    @Override
    public String getLinkURL() {
        return linkUrl;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
