package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Button;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomButton.class,
        ComponentExporter.class }, resourceType = CustomButton.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomButton implements Button {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/button";

    private final Logger logger = LoggerFactory.getLogger(CustomButton.class);

    @ValueMapValue(name = "jcr:title")
    private String text;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String accessibilityLabel;

    @ValueMapValue
    private String buttonStyle;

    @ScriptVariable
    private PageManager pageManager;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String getAccessibilityLabel() {
        return this.accessibilityLabel;
    }

    public String getButtonStyle() {
        return this.buttonStyle;
    }

    public boolean isRoute() {
        return (pageManager.getPage(this.link) != null);
    }

    @Override
    public String getExportedType() {
        return CustomButton.RESOURCE_TYPE;
    }
}
