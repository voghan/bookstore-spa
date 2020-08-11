package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Text;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomText.class,
        ComponentExporter.class }, resourceType = CustomText.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomText implements Text {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/text";

    private final Logger logger = LoggerFactory.getLogger(CustomText.class);

    @ValueMapValue
    private String text;

    @ValueMapValue(name = "textIsRich")
    private boolean richText;

    @PostConstruct
    public void initModel() {
        logger.info("......Text created...");
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public boolean isRichText() {
        return this.richText;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
