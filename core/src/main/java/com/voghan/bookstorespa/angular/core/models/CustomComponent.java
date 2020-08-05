package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Component(value = "Custom Component", group = "BookStore Spa - Content")
@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomComponent.class,
        ComponentExporter.class }, resourceType = CustomComponent.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomComponent implements ComponentExporter {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/customcomponent";

    private final Logger logger = LoggerFactory.getLogger(CustomComponent.class);
    @ValueMapValue
    private String message;

    @PostConstruct
    public void initModel() {
    }

    @DialogField(fieldLabel = "Title", ranking = 4D, required = true)
    @TextField
    public String getMessage() {
        return StringUtils.isNotBlank(message) ? message.toUpperCase() : "";
    }

    @Override
    public String getExportedType() {
        return CustomComponent.RESOURCE_TYPE;
    }
}
