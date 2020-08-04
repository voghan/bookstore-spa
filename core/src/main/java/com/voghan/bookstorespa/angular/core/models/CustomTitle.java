package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Title;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Component(value = "Title", name = "title",
        inPlaceEditingActive=true, inPlaceEditingEditorType="title",
        inPlaceEditingConfigPath="/apps/core/wcm/components/title/v2/title",
        listeners = @Listener( name="afteredit", value = "REFRESH_PAGE"),
        tabs = { @Tab(title = "Properties")})
@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomComponent.class,
        ComponentExporter.class }, resourceType = CustomTitle.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomTitle implements Title {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/title";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String linkUrl;

    @ValueMapValue
    private boolean linkDisabled;

    @SlingObject
    private ResourceResolver resourceResolver;

    @SlingObject
    private SlingHttpServletRequest servletRequest;

    @PostConstruct
    protected void init() {
        logger.info(".....BFV     servletRequest.... " + servletRequest );

    }

    @DialogField(fieldLabel = "Title", fieldDescription = "Leave empty to use the page title.",
            ranking = 1D)
    @TextField
    @Override
    public String getText() {
        return text;
    }

    @DialogField(fieldLabel = "Link URL", fieldDescription = "Links the title. Path to a content page, external URL or page anchor.",
            ranking = 3D)
    @TextField
    @Override
    public String getLinkURL() {
        return linkUrl;
    }

    @DialogField(fieldLabel = "Link Disabled", fieldDescription = "",
            ranking = 4D)
    @CheckBox(text = "Link Disabled")
    @Override
    public boolean isLinkDisabled() {
        return linkDisabled;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
