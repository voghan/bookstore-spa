package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { Article.class,
        ComponentExporter.class }, resourceType = Article.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class Article extends CustomText {
    static final String RESOURCE_TYPE = "bookstore-spa/components/content/article";

    private final Logger logger = LoggerFactory.getLogger(CustomText.class);

    @ValueMapValue
    private String articleTitle;

    public String getArticleTitle() {
        return articleTitle;
    }

    @ValueMapValue
    private String titleStyle;



    public String getTitleStyle() {
        return titleStyle;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
