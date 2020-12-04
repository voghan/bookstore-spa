package com.voghan.bookstorespa.angular.core.utils;

import com.day.cq.wcm.api.designer.Style;

public class StyleUtils {
    private StyleUtils() {
    }

    /**
     * Returns the tree depth that can be configured in the policy. Defaults to 0
     *
     * @param style            Style to search in
     * @param pnStructureDepth Name of the structure depth attribute
     * @return The defined traversal depth or 0 if not defined
     */
    public static int getPageTreeDepth(Style style, String pnStructureDepth) {
        // Depth of the tree of pages
        Integer pageTreeTraversalDepth = getStructureDepth(style, pnStructureDepth);

        if (pageTreeTraversalDepth == null) {
            return 0;
        }

        return pageTreeTraversalDepth;
    }

    /**
     * Returns the style's structure depth attribute value
     *
     * @param style            Style to search in
     * @param pnStructureDepth Name of the structure depth attribute
     * @return Structure depth attribute value
     */
    public static Integer getStructureDepth(Style style, String pnStructureDepth) {
        if (style != null) {
            return style.get(pnStructureDepth, Integer.class);
        }

        return null;
    }

    public static boolean isRootPage(Style style) {
        return style.get(HierarchyConstants.PN_IS_ROOT, false);
    }
}

