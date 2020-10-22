package com.voghan.bookstorespa.angular.core.models.container;

import com.adobe.cq.wcm.core.components.models.ListItem;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class ResourceListItem implements ListItem {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceListItem.class);
    protected String url;
    protected String title;
    protected String description;
    protected Calendar lastModified;
    protected String path;
    protected String name;

    public ResourceListItem(SlingHttpServletRequest request, Resource resource) {
        ValueMap valueMap = (ValueMap)resource.adaptTo(ValueMap.class);
        if (valueMap != null) {
            this.title = (String)valueMap.get("jcr:title", String.class);
            this.description = (String)valueMap.get("jcr:description", String.class);
            this.lastModified = (Calendar)valueMap.get("jcr:lastModified", Calendar.class);
        }

        this.path = resource.getPath();
        this.name = resource.getName();
        this.url = null;
    }

    public String getURL() {
        return this.url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Calendar getLastModified() {
        return this.lastModified;
    }

    public String getPath() {
        return this.path;
    }

    public String getName() {
        return this.name;
    }
}
