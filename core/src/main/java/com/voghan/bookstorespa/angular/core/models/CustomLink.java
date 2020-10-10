package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomLink.class,
        ComponentExporter.class }, resourceType = CustomLink.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomLink  implements ComponentExporter {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/link";

    private final Logger logger = LoggerFactory.getLogger(CustomButton.class);

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String accessibilityLabel;

    @ValueMapValue
    private String cssClass;

    @ScriptVariable
    private PageManager pageManager;

    private Page linkPage;

    @PostConstruct
    public void initModel() {
        if(StringUtils.isNotBlank(this.link) && pageManager != null) {
            linkPage = pageManager.getPage(this.link);
        }
    }

    public String getText() {
        if ( this.text == null && this.linkPage != null) {
            text = linkPage.getTitle();
        }
        return text;
    }

    public String getLink() {
        return link;
    }

    public String getIcon() {
        return icon;
    }

    public String getAccessibilityLabel() {
        return accessibilityLabel;
    }

    public String getCssClass() {
        return cssClass;
    }

    public boolean isRoute() {
        return (pageManager.getPage(this.link) != null);
    }

    public String getExportedType() {
        return CustomButton.RESOURCE_TYPE;
    }
}
