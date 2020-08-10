package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.wcm.core.components.models.Component;
import com.adobe.cq.wcm.core.components.models.ListItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionItem implements Component, ListItem {
    private final Logger logger = LoggerFactory.getLogger(ActionItem.class);

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String url;

    protected ActionItem(final Resource actionRes) {
        logger.info(".....ActionItem.....");
        ValueMap ctaProperties = actionRes.getValueMap();
        title = ctaProperties.get("text", String.class);
        url = ctaProperties.get("link", String.class);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getURL() {
        return url;
    }
}
