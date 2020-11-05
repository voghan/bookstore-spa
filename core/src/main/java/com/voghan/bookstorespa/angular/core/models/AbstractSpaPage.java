package com.voghan.bookstorespa.angular.core.models;

import com.adobe.aem.spa.project.core.models.Page;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.hierarchy.type.HierarchyTypes;
import com.adobe.cq.wcm.core.components.models.NavigationItem;
import com.day.cq.wcm.api.designer.Style;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voghan.bookstorespa.angular.core.utils.HierarchyUtils;
import com.voghan.bookstorespa.angular.core.utils.RequestUtils;
import com.voghan.bookstorespa.angular.core.utils.StyleUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import org.apache.sling.models.factory.ModelFactory;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Map;

public class AbstractSpaPage implements Page  {

    // Delegated to Page v1
    @ScriptVariable
    @Via(type = ResourceSuperType.class)
    private com.day.cq.wcm.api.Page currentPage;

    // Delegated to Page v1
    @ScriptVariable(injectionStrategy = InjectionStrategy.OPTIONAL)
    @JsonIgnore
    @Via(type = ResourceSuperType.class)
    private Style currentStyle;

    // Delegated to Page v1
    @Inject
    private ModelFactory modelFactory;

    // Delegated to Page v2
    @Self
    @Via(type = ResourceSuperType.class)
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Resource resource;

    // "delegate" object with which methods from Page v1/v2 can be used
    @Self
    @Via(type = ResourceSuperType.class)
    private com.adobe.cq.wcm.core.components.models.Page delegate;

    /**
     * {@link Map} containing the page models with their corresponding paths (as keys)
     */
    private Map<String, ? extends Page> descendedPageModels;

    private com.day.cq.wcm.api.Page rootPage;

    /**
     * Package-private setter for descendedPageModels (required for tests)
     */
    void setDescendedPageModels(Map<String, ? extends Page> descendedPageModels) {
        this.descendedPageModels = descendedPageModels;
    }

    /**
     * Package-private setter for rootPage (required for tests)
     */
    void setRootPage(com.day.cq.wcm.api.Page rootPage) {
        this.rootPage = rootPage;
    }

    @Override
    public String getExportedHierarchyType() {
        return HierarchyTypes.PAGE;
    }

    @Override
    public Map<String, ? extends Page> getExportedChildren() {
        if (descendedPageModels == null) {
            setDescendedPageModels(HierarchyUtils.getDescendantsModels(request, currentPage, currentStyle, modelFactory));
        }

        return descendedPageModels;
    }

    @Override
    public String getExportedPath() {
        return currentPage.getPath();
    }

    @Override
    public String getHierarchyRootJsonExportUrl() {
        if (isRootPage()) {
            return RequestUtils.getPageJsonExportUrl(request, currentPage);
        }

        if (rootPage == null) {
            setRootPage(HierarchyUtils.getRootPage(resource, currentPage));
        }

        if (rootPage != null) {
            return RequestUtils.getPageJsonExportUrl(request, rootPage);
        }
        return null;
    }

    /**
     * Returns the model of the root page which this page is a part of
     *
     * @return Root page model
     */
    @Override
    public Page getHierarchyRootModel() {
        if (isRootPage()) {
            return this;
        }

        if (rootPage == null) {
            setRootPage(HierarchyUtils.getRootPage(resource, currentPage));
        }

        if (rootPage == null) {
            return null;
        }

        return modelFactory.getModelFromWrappedRequest(HierarchyUtils.createHierarchyServletRequest(request, rootPage, currentPage),
                rootPage.getContentResource(), this.getClass());
    }

    private boolean isRootPage() {
        return currentStyle != null && StyleUtils.isRootPage(currentStyle);
    }

    // Delegated to Page v1
    @Override
    public String getLanguage() {
        return delegate.getLanguage();
    }

    // Delegated to Page v1
    @Override
    public Calendar getLastModifiedDate() {
        return delegate.getLastModifiedDate();
    }

    // Delegated to Page v1
    @Override
    @JsonIgnore
    public String[] getKeywords() {
        return delegate.getKeywords();
    }

    // Delegated to Page v1
    @Override
    public String getDesignPath() {
        return delegate.getDesignPath();
    }

    // Delegated to Page v1
    @Override
    public String getStaticDesignPath() {
        return delegate.getStaticDesignPath();
    }

    // Delegated to Page v1
    @Override
    public String getTitle() {
        return delegate.getTitle();
    }

    // Delegated to Page v1
    @Override
    public String getTemplateName() {
        return delegate.getTemplateName();
    }

    // Delegated to Page v1
    @Override
    @JsonIgnore
    public String[] getClientLibCategories() {
        return delegate.getClientLibCategories();
    }

    // Delegated to Page v1
    @Override
    public String getExportedType() {
        return delegate.getExportedType();
    }

//    // Delegated to Page v2
//    @Override
//    public String getMainContentSelector() {
//        return delegate.getMainContentSelector();
//    }

    // Delegated to Page v2
    @Override
    @JsonIgnore
    public String[] getClientLibCategoriesJsBody() {
        return delegate.getClientLibCategoriesJsBody();
    }

    // Delegated to Page v2
    @Override
    @JsonIgnore
    public String[] getClientLibCategoriesJsHead() {
        return delegate.getClientLibCategoriesJsHead();
    }

    // Delegated to Page v2
    @Override
    public String getAppResourcesPath() {
        return delegate.getAppResourcesPath();
    }

    // Delegated to Page v2
    @Override
    public String getCssClassNames() {
        return delegate.getCssClassNames();
    }

    // Delegated to Page v2
    @Override
    public String[] getExportedItemsOrder() {
        return delegate.getExportedItemsOrder();
    }

    // Delegated to Page v2
    @Override
    public Map<String, ? extends ComponentExporter> getExportedItems() {
        return delegate.getExportedItems();
    }

    // Delegated to Page v2
    @Override
    public NavigationItem getRedirectTarget() {
        return delegate.getRedirectTarget();
    }

    // Delegated to Page v2
    @Override
    public boolean hasCloudconfigSupport() {
        return delegate.hasCloudconfigSupport();
    }

//    // Delegated to Page v2
//    @Override
//    public Set<String> getComponentsResourceTypes() {
//        return delegate.getComponentsResourceTypes();
//    }
}
