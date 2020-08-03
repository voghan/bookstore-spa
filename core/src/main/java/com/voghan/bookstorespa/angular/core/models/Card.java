package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Image;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { Card.class,
        ComponentExporter.class }, resourceType = Card.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class Card implements Image {
    static final String RESOURCE_TYPE = "bookstore-spa/components/card";

    @Self
    private SlingHttpServletRequest request;

    @Self
    @Via(type = ResourceSuperType.class)
    private Image image;

    @ValueMapValue
    private String cardPath;

    @ValueMapValue
    private String ctaText;

    @ValueMapValue
    private boolean titleFromPage;

    @ValueMapValue
    private String cardTitle;

    /***
     * Made available via https://docs.adobe.com/content/help/en/experience-manager-htl/using/htl/global-objects.html#java-backed-objects
     */
    @ScriptVariable
    PageManager pageManager;

    /***
     * The underlying page linked by the card
     */
    private Page cardPage;

    @PostConstruct
    // PostConstructs are called after all the injection has occurred, but before the Model object is returned for use.
    public void initModel() {
        // Note that @PostConstruct code will always be executed on Model instantiation.
        // If the work done in PostConstruct is expensive and not always used in the consumption of the model, it is
        // better to lazy-execute the logic in the getter and persist the result in  model state if it is requested again.
        if(StringUtils.isNotBlank(cardPath) && pageManager != null) {
            cardPage = pageManager.getPage(this.cardPath);
        }
    }

    @Override
    public String getSrc() {
        return null != image ? image.getSrc() : null;
    }

    @Override
    public String getAlt() {
        return null != image ? image.getAlt() : null;
    }

    @Override
    public String getTitle() {
        if (null == image) {
            return null;
        }
        return image.getTitle();
    }



    @Override
    public String getExportedType() {
        return Card.RESOURCE_TYPE;
    }

    public String getCtaLinkURL() {
        if(cardPage != null) {
            return cardPage.getPath() + ".html";
        }
        return null;
    }

    public String getCtaText() {
        return ctaText;
    }

    public Calendar getCardLastModified() {
       if(cardPage != null) {
           return cardPage.getLastModified();
       }
       return null;
    }

    public String getCardTitle() {
        if(titleFromPage) {
            return cardPage != null ? cardPage.getTitle() : null;
        }
        return cardTitle;
    }
}