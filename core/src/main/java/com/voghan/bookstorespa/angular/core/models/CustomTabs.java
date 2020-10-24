package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ContainerExporter;
import com.adobe.cq.export.json.SlingModelFilter;
import com.adobe.cq.wcm.core.components.models.Tabs;
import com.voghan.bookstorespa.angular.core.models.container.ContainerHelper;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = { CustomTabs.class, ComponentExporter.class, ContainerExporter.class },
        resourceType = CustomTabs.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomTabs implements Tabs {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/tabs";

    private final Logger logger = LoggerFactory.getLogger(CustomTabs.class);

    @SlingObject
    private SlingHttpServletRequest servletRequest;

    @OSGiService
    protected SlingModelFilter slingModelFilter;

    @OSGiService
    protected ModelFactory modelFactory;

    @ValueMapValue
    private String activeItem;

    @ValueMapValue
    private String header;

    private String activeItemName;

    private ContainerHelper containerHelper;

    @Override
    public String getActiveItem() {
        if (this.activeItemName == null) {
            Resource active = this.servletRequest.getResource().getChild(this.activeItem);
            if (active != null) {
                this.activeItemName = this.activeItem;
            }
        }

        return this.activeItemName;
    }

    public String getHeader() {
        return header;
    }

    public Map<String, ? extends ComponentExporter> getExportedItems() {
        return containerHelper.getExportedItems();
    }

    public String[] getExportedItemsOrder() {
        return this.containerHelper.getExportedItemsOrder();
    }

    @PostConstruct
    public void initModel() {
        logger.warn("........create tabs ");
        this.containerHelper = new ContainerHelper(servletRequest, slingModelFilter, modelFactory);
    }

    @Override
    public  String getExportedType() {
        return RESOURCE_TYPE;
    }
}
