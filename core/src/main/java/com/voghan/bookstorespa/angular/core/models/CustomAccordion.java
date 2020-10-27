package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ContainerExporter;
import com.adobe.cq.export.json.SlingModelFilter;
import com.adobe.cq.wcm.core.components.models.Container;
import com.voghan.bookstorespa.angular.core.models.container.ContainerHelper;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.factory.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = { CustomAccordion.class, ComponentExporter.class, ContainerExporter.class },
        resourceType = CustomAccordion.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomAccordion implements Container {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/accordion";

    private final Logger logger = LoggerFactory.getLogger(CustomAccordion.class);

    @SlingObject
    private SlingHttpServletRequest servletRequest;

    @OSGiService
    protected SlingModelFilter slingModelFilter;

    @OSGiService
    protected ModelFactory modelFactory;

    private ContainerHelper containerHelper;

    public Map<String, ? extends ComponentExporter> getExportedItems() {
        return containerHelper.getExportedItems();
    }

    public String[] getExportedItemsOrder() {
        return this.containerHelper.getExportedItemsOrder();
    }

    @PostConstruct
    public void initModel() {
        logger.warn("........create accordion ");
        this.containerHelper = new ContainerHelper(servletRequest, slingModelFilter, modelFactory);
    }

    @Override
    public  String getExportedType() {
        return RESOURCE_TYPE;
    }
}
