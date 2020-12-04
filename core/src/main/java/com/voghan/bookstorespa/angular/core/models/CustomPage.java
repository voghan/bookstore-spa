package com.voghan.bookstorespa.angular.core.models;

import com.adobe.aem.spa.project.core.models.Page;
import com.adobe.cq.export.json.ContainerExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.NavigationItem;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { Page.class,
        ContainerExporter.class }, resourceType = CustomPage.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomPage extends AbstractSpaPage  {

    static final String RESOURCE_TYPE = "spa-project-core/components/page";

    private final Logger logger = LoggerFactory.getLogger(CustomPage.class);

    @Self
    @Via(type = ResourceSuperType.class)
    private com.adobe.cq.wcm.core.components.models.Page delegate;

    @PostConstruct
    public void initModel() {
        logger.warn("...........My Custom Page Model..........");
    }

    @Override
    public NavigationItem getRedirectTarget() {
        return delegate.getRedirectTarget();
    }
}
