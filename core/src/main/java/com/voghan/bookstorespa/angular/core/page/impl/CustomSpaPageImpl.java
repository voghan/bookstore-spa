package com.voghan.bookstorespa.angular.core.page.impl;

import com.adobe.aem.spa.project.core.models.Page;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ContainerExporter;
import com.adobe.cq.export.json.hierarchy.HierarchyNodeExporter;
import com.adobe.cq.wcm.core.components.models.NavigationItem;
import com.day.cq.wcm.api.designer.Style;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voghan.bookstorespa.angular.core.models.CustomSpaPage;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import org.apache.sling.models.factory.ModelFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Map;

@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = {Page.class, ContainerExporter.class, CustomSpaPage.class},
        resourceType = {"bookstore-spa/components/structure/page"})
@Exporter(name = "jackson", extensions = {"json"})
public class CustomSpaPageImpl implements CustomSpaPage {
    static final String RESOURCE_TYPE = "bookstore-spa/components/structure/page";

    @ScriptVariable
    @Via(type = ResourceSuperType.class)
    private com.day.cq.wcm.api.Page currentPage;

    @ScriptVariable(injectionStrategy = InjectionStrategy.OPTIONAL)
    @JsonIgnore
    @Via(type = ResourceSuperType.class)
    private Style currentStyle;

    @Inject
    private ModelFactory modelFactory;

    @Self
    @Via(type = ResourceSuperType.class)
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Resource resource;

    @Self
    @Via(type = ResourceSuperType.class)
    private Page delegate;

    private Map<String, ? extends Page> descendedPageModels;
    private com.day.cq.wcm.api.Page rootPage;

    public CustomSpaPageImpl() {
    }

    void setDescendedPageModels(Map<String, ? extends Page> descendedPageModels) {
        this.descendedPageModels = descendedPageModels;
    }

    void setRootPage(com.day.cq.wcm.api.Page rootPage) {
        this.rootPage = rootPage;
    }

    @Nullable
    public String getExportedHierarchyType() {
        return delegate.getExportedHierarchyType();
    }

    @NotNull
    public String getExportedPath() {
        return this.currentPage.getPath();
    }

    @Nullable
    public String getHierarchyRootJsonExportUrl() {
        return delegate.getHierarchyRootJsonExportUrl();
    }

    @Nullable
    public Page getHierarchyRootModel() {
        return delegate.getHierarchyRootModel();
    }

    @Override
    public Map<String, ? extends HierarchyNodeExporter> getExportedChildren() {
        return delegate.getExportedChildren();
    }

    public String getLanguage() {
        return this.delegate.getLanguage();
    }

    public Calendar getLastModifiedDate() {
        return this.delegate.getLastModifiedDate();
    }

    @JsonIgnore
    public String[] getKeywords() {
        return this.delegate.getKeywords();
    }

    public String getDesignPath() {
        return this.delegate.getDesignPath();
    }

    public String getStaticDesignPath() {
        return this.delegate.getStaticDesignPath();
    }

    public String getTitle() {
        return this.delegate.getTitle();
    }

    public String getTemplateName() {
        return this.delegate.getTemplateName();
    }

    @JsonIgnore
    public String[] getClientLibCategories() {
        return this.delegate.getClientLibCategories();
    }

    @NotNull
    public String getExportedType() {
        return this.delegate.getExportedType();
    }

    @JsonIgnore
    public String[] getClientLibCategoriesJsBody() {
        return this.delegate.getClientLibCategoriesJsBody();
    }

    @JsonIgnore
    public String[] getClientLibCategoriesJsHead() {
        return this.delegate.getClientLibCategoriesJsHead();
    }

    public String getAppResourcesPath() {
        return this.delegate.getAppResourcesPath();
    }

    public String getCssClassNames() {
        return this.delegate.getCssClassNames();
    }

    @NotNull
    public String[] getExportedItemsOrder() {
        return this.delegate.getExportedItemsOrder();
    }

    @NotNull
    public Map<String, ? extends ComponentExporter> getExportedItems() {
        return this.delegate.getExportedItems();
    }

    @Nullable
    public NavigationItem getRedirectTarget() {
        return this.delegate.getRedirectTarget();
    }

    public boolean hasCloudconfigSupport() {
        return this.delegate.hasCloudconfigSupport();
    }

    @Override
    public String getPageTitle() {
        return this.currentPage.getPageTitle();
    }

    @Override
    public String getDescription() {
        return this.currentPage.getDescription();
    }

    @Override
    public String getMetaRobots() {
        return "index, follow";
    }
}