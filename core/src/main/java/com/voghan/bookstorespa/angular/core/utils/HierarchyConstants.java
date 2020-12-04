package com.voghan.bookstorespa.angular.core.utils;

public class HierarchyConstants {
    private HierarchyConstants() {
    }

    /**
     * URL extension specific to the Sling Model exporter
     */
    public static final String JSON_EXPORT_SUFFIX = ".model.json";

    /**
     * Is the current model to be considered as a model root
     */
    public static final String PN_IS_ROOT = "isRoot";

    /**
     * Name of the request attribute which is used to flag the child pages. Optionally available as a request attribute
     */
    public static final String ATTR_IS_CHILD_PAGE = "com.adobe.aem.spa.project.core.models.Page.isChildPage";

    /**
     * Name of the request attribute that defines whether the page is an entry point of the request.
     */
    public static final String ATTR_HIERARCHY_ENTRY_POINT_PAGE = "com.adobe.aem.spa.project.core.models.Page.entryPointPage";

    /**
     * Request attribute key of the component context
     */
    public static final String ATTR_COMPONENT_CONTEXT = "com.day.cq.wcm.componentcontext";

    /**
     * Request attribute key of the current page
     */
    public static final String ATTR_CURRENT_PAGE = "currentPage";

    /**
     * List of Regexp patterns to filter the exported tree of pages
     */
    public static final String PN_STRUCTURE_PATTERNS = "structurePatterns";
}
