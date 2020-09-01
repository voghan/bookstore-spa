package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.adobe.cq.wcm.core.components.models.Tabs;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomTabs.class,
        ComponentExporter.class }, resourceType = CustomTabs.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomTabs implements Tabs {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/tabs2";

    private final Logger logger = LoggerFactory.getLogger(CustomTabs.class);

    @ValueMapValue
    private String activeItem;

    @ValueMapValue
    private String accessibilityLabel;

    @ValueMapValue
    private List<ListItem> items;

    @ValueMapValue
    private String backgroundStyle;

    @Override
    public String getActiveItem() {
        return this.activeItem;
    }

    @Override
    public String getAccessibilityLabel() {
        return this.accessibilityLabel;
    }

    @Override
    public List<ListItem> getItems() {
        return this.items;
    }

    @Override
    public String getBackgroundStyle() {
        return this.backgroundStyle;
    }

    @Override
    public  String getExportedType() {
        return RESOURCE_TYPE;
    }
}
