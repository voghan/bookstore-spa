package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.wcm.core.components.models.Component;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ActionItem implements Component, ListItem {
    private final Logger logger = LoggerFactory.getLogger(ActionItem.class);

    @ValueMapValue
    private final String title;

    @ValueMapValue
    private final String url;

    @ValueMapValue
    private final String target;

    private final Page page;

    protected ActionItem(final Resource resource) {
        logger.info(".....ActionItem.....");
        ValueMap ctaProperties = resource.getValueMap();
        title = ctaProperties.get("text", String.class);
        url = ctaProperties.get("link", String.class);
        target = ctaProperties.get("target", String.class);

        if (url != null && url.startsWith("/")) {
            PageManager pageManager = resource.getResourceResolver().adaptTo(PageManager.class);
            if (pageManager != null) {
                page = pageManager.getPage(url);
            } else {
                page = null;
            }
        } else {
            page = null;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getURL() {
        return url;
    }

    public String getTarget() {
        return target;
    }

    protected Optional<Page> getPage() {
        return Optional.ofNullable(this.page);
    }
}
