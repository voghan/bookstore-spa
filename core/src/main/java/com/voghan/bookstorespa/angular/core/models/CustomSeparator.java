package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Separator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomSeparator.class,
        ComponentExporter.class }, resourceType = CustomSeparator.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomSeparator implements Separator {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/separator";

    private final Logger logger = LoggerFactory.getLogger(CustomSeparator.class);

    @PostConstruct
    public void initModel() {
        logger.info("......Separator created...");
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
