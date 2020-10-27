package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.adobe.cq.wcm.core.components.models.Teaser;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomTeaser.class,
        ComponentExporter.class }, resourceType = CustomTeaser.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomTeaser implements Teaser {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/teaser";

    private final Logger logger = LoggerFactory.getLogger(CustomTeaser.class);

    @ValueMapValue(name = "jcr:title")
    private String title;

    @ValueMapValue(name = "jcr:description")
    private String description;

    @ValueMapValue
    private boolean titleFromPage;

    @ValueMapValue
    private boolean descriptionFromPage;

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private boolean actionsEnabled;

    @ValueMapValue
    private List<ActionItem> actions;

    @ValueMapValue(name = "fileReference")
    private String imagePath;

    @Inject
    private Resource resource;

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private PageManager pageManager;

    private Page targetPage;

    @PostConstruct
    public void initModel() {
        logger.debug("......Teaser created...");
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String getTitle() {
        if (titleFromPage) {
            this.title = getTargetPage().getTitle();
        }
        return this.title;
    }

    @Override
    public String getDescription() {
        if (descriptionFromPage) {
            this.description = getTargetPage().getDescription();
        }
        return this.description;
    }

    @Override
    public String getLinkURL() {
        return this.linkURL;
    }

    @Override
    public boolean isActionsEnabled() {
        return this.actionsEnabled;
    }

    private List<ActionItem> getActionItems() {
        if (actions == null) {
            this.actions = Optional.ofNullable(this.isActionsEnabled() ? this.resource.getChild(Teaser.NN_ACTIONS) : null)
                    .map(Resource::getChildren)
                    .map(Iterable::spliterator)
                    .map(s -> StreamSupport.stream(s, false))
                    .orElseGet(Stream::empty)
                    .map(action -> new ActionItem(action))
                    .collect(Collectors.toList());
        }
        return this.actions;
    }

    @Override
    public List<ListItem> getActions() {
        return Collections.unmodifiableList(this.getActionItems());
    }

    @Override
    public String getExportedType() {
        return CustomTeaser.RESOURCE_TYPE;
    }

    private Page getTargetPage() {
        if (this.targetPage == null && pageManager != null) {
            if (this.actionsEnabled) {
                this.getActionItems().stream().findFirst().flatMap(ActionItem::getOptionalPage).orElse(null);
            } else if(StringUtils.isNoneEmpty(linkURL)) {
                targetPage = pageManager.getPage(this.linkURL);
            }
        }
        return this.targetPage;
    }
}
