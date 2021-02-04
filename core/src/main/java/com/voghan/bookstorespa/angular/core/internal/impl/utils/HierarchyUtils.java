package com.voghan.bookstorespa.angular.core.internal.impl.utils;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.Template;
import com.day.cq.wcm.api.TemplatedResource;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.api.policies.ContentPolicy;
import com.day.cq.wcm.api.policies.ContentPolicyManager;
import com.voghan.bookstorespa.angular.core.internal.impl.HierarchyComponentContextWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;
import org.apache.sling.models.factory.ModelFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;

public class HierarchyUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HierarchyUtils.class);

    private HierarchyUtils() {
    }

    public static SlingHttpServletRequest createHierarchyServletRequest(@NotNull SlingHttpServletRequest request, @NotNull Page page, @Nullable Page entryPage) {
        SlingHttpServletRequest wrapperRequest = new SlingHttpServletRequestWrapper(request);
        ComponentContext componentContext = (ComponentContext)request.getAttribute("com.day.cq.wcm.componentcontext");
        HierarchyComponentContextWrapper componentContextWrapper = new HierarchyComponentContextWrapper(componentContext, page);
        wrapperRequest.setAttribute("com.day.cq.wcm.componentcontext", componentContextWrapper);
        wrapperRequest.setAttribute("currentPage", page);
        wrapperRequest.setAttribute("com.adobe.aem.spa.project.core.models.Page.entryPointPage", entryPage);
        return wrapperRequest;
    }

    public static Page getEntryPoint(@NotNull SlingHttpServletRequest request) {
        return (Page)request.getAttribute("com.adobe.aem.spa.project.core.models.Page.entryPointPage");
    }

    public static void addEntryPointPage(SlingHttpServletRequest request, Page currentPage, @NotNull List<Page> descendedPages) {
        if (!Boolean.TRUE.equals(request.getAttribute("com.adobe.aem.spa.project.core.models.Page.isChildPage"))) {
            Page entryPointPage = getEntryPoint(request);
            if (entryPointPage != null) {
                if (!entryPointPage.getPath().equals(currentPage.getPath())) {
                    if (!descendedPages.contains(entryPointPage)) {
                        descendedPages.add(entryPointPage);
                    }
                }
            }
        }
    }

    @NotNull
    public static List<Pattern> getStructurePatterns(@NotNull SlingHttpServletRequest request, Style currentStyle) {
        RequestParameter pageFilterParameter = request.getRequestParameter("structurePatterns".toLowerCase());
        String rawPageFilters = null;
        if (pageFilterParameter != null) {
            rawPageFilters = pageFilterParameter.getString();
        }

        if (currentStyle != null && StringUtils.isBlank(rawPageFilters)) {
            rawPageFilters = (String)currentStyle.get("structurePatterns", String.class);
        }

        if (StringUtils.isBlank(rawPageFilters)) {
            return Collections.emptyList();
        } else {
            String[] pageFilters = rawPageFilters.split(",");
            List<Pattern> pageFilterPatterns = new ArrayList();
            String[] var6 = pageFilters;
            int var7 = pageFilters.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String pageFilter = var6[var8];
                pageFilterPatterns.add(Pattern.compile(pageFilter));
            }

            return pageFilterPatterns;
        }
    }

    public static Page getRootPage(Resource resource, Page currentPage) {
        Page tempRootPage = currentPage;
        ContentPolicyManager contentPolicyManager = (ContentPolicyManager)resource.getResourceResolver().adaptTo(ContentPolicyManager.class);
        if (contentPolicyManager == null) {
            LOGGER.error("Error determining SPA root page: Cannot adapt resource resolver to ContentPolicyManager class");
            return null;
        } else {
            for(; tempRootPage != null; tempRootPage = tempRootPage.getParent()) {
                Template template = tempRootPage.getTemplate();
                if (template != null && template.hasStructureSupport()) {
                    Resource pageContentResource = tempRootPage.getContentResource();
                    if (pageContentResource != null) {
                        ContentPolicy contentPolicy = contentPolicyManager.getPolicy(pageContentResource);
                        if (ContentPolicyUtils.propertyIsTrue(contentPolicy, "isRoot")) {
                            LOGGER.debug("Found SPA root page: {}", tempRootPage.getPath());
                            return tempRootPage;
                        }
                    }
                }
            }

            LOGGER.error("SPA root page not found, returning null");
            return null;
        }
    }

    @NotNull
    public static List<Page> getDescendants(Page page, SlingHttpServletRequest slingRequest, List<Pattern> structurePatterns, int depth) {
        if (page != null && depth != 0 && !Boolean.TRUE.equals(slingRequest.getAttribute("com.adobe.aem.spa.project.core.models.Page.isChildPage"))) {
            List<Page> pages = new ArrayList();
            Iterator<Page> childPagesIterator = page.listChildren();
            if (childPagesIterator != null && childPagesIterator.hasNext()) {
                --depth;
                boolean noPageFilters = structurePatterns.isEmpty();

                while(childPagesIterator.hasNext()) {
                    Page childPage = (Page)childPagesIterator.next();
                    boolean found = noPageFilters;
                    Iterator var9 = structurePatterns.iterator();

                    while(var9.hasNext()) {
                        Pattern pageFilterPattern = (Pattern)var9.next();
                        if (pageFilterPattern.matcher(childPage.getPath()).find()) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        pages.add(childPage);
                        pages.addAll(getDescendants(childPage, slingRequest, structurePatterns, depth));
                    }
                }

                return pages;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    @Nullable
    protected static com.adobe.aem.spa.project.core.models.Page getDescendantModel(Page childPage, SlingHttpServletRequest slingRequestWrapper, ModelFactory modelFactory) {
        Resource childPageContentResource = childPage.getContentResource();
        if (childPageContentResource == null) {
            return null;
        } else {
            TemplatedResource templatedResource = childPageContentResource.adaptTo(TemplatedResource.class);
            if (templatedResource != null) {
                childPageContentResource = templatedResource;
            }

            SlingHttpServletRequest wrapperRequest = createHierarchyServletRequest(slingRequestWrapper, childPage, null);
            Object model = modelFactory.getModelFromWrappedRequest(wrapperRequest, childPageContentResource, com.adobe.aem.spa.project.core.models.Page.class);
            return (com.adobe.aem.spa.project.core.models.Page) model;
        }
    }

    @NotNull
    public static Map<String, com.adobe.aem.spa.project.core.models.Page> getDescendantsModels(SlingHttpServletRequest request, Page currentPage, Style currentStyle, ModelFactory modelFactory) {
        int pageTreeTraversalDepth = StyleUtils.getPageTreeDepth(currentStyle, "structureDepth");
        List<Pattern> pageFilterPatterns = getStructurePatterns(request, currentStyle);
        SlingHttpServletRequest slingRequestWrapper = new SlingHttpServletRequestWrapper(request);
        Map<String, com.adobe.aem.spa.project.core.models.Page> itemWrappers = new LinkedHashMap();
        List<Page> descendants = getDescendants(currentPage, slingRequestWrapper, pageFilterPatterns, pageTreeTraversalDepth);
        addEntryPointPage(request, currentPage, descendants);
        slingRequestWrapper.setAttribute("com.adobe.aem.spa.project.core.models.Page.isChildPage", true);
        Iterator var9 = descendants.iterator();

        while(var9.hasNext()) {
            Page childPage = (Page)var9.next();
            com.adobe.aem.spa.project.core.models.Page descendantModel = getDescendantModel(childPage, slingRequestWrapper, modelFactory);
            if (descendantModel != null) {
                itemWrappers.put(childPage.getPath(), descendantModel);
            }
        }

        return itemWrappers;
    }
}
