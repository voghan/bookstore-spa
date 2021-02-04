package com.voghan.bookstorespa.angular.core.internal.impl.utils;

import com.day.cq.wcm.api.Page;
import com.voghan.bookstorespa.angular.core.internal.HierarchyConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;

public class RequestUtils {
    private RequestUtils() {
    }

    /**
     * Given a {@link Page}, this method returns the correct URL, taking into account that the provided page might provide a vanity URL
     *
     * @param request The current request, used to determine the server's context path
     * @param page    The page
     * @return The URL of the page identified by the provided path, or the original path if this doesn't identify a {@link Page}
     */
    public static String getURL(SlingHttpServletRequest request, Page page) {
        String contextPath = request.getContextPath();
        String contextPathNotNull = contextPath == null ? "" : contextPath;
        String vanityURL = page.getVanityUrl();
        return StringUtils.isEmpty(vanityURL) ? contextPathNotNull + page.getPath() + ".html" : contextPathNotNull + vanityURL;
    }

    /**
     * Returns a model URL for the given page URL
     *
     * @param url Page URL
     * @return Model URL
     */
    public static String getJsonExportURL(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }

        int dotIndex = url.indexOf('.');

        if (dotIndex < 0) {
            dotIndex = url.length();
        }

        return url.substring(0, dotIndex) + HierarchyConstants.JSON_EXPORT_SUFFIX;
    }

    /**
     * Returns a model URL for the given page URL
     *
     * @param slingRequest The current servlet request
     * @param page         Page for which to get the model URL
     * @return Model URL
     */
    public static String getPageJsonExportUrl(SlingHttpServletRequest slingRequest, com.day.cq.wcm.api.Page page) {
        return RequestUtils.getJsonExportURL(RequestUtils.getURL(slingRequest, page));
    }
}
