package com.voghan.bookstorespa.angular.core.utils;

import com.day.cq.wcm.api.policies.ContentPolicy;
import org.apache.sling.api.resource.ValueMap;

public class ContentPolicyUtils {
    private ContentPolicyUtils() {
    }

    /**
     * Utility function which checks whether the provided content policy contains a property and whether its value is {@code true}
     *
     * @param contentPolicy The content policy which should be searched for the specified property
     * @param propertyName  Name of the JCR property to check
     * @return Boolean indicating whether the property is set to {@code true}. If the content policy or the property don't exist, the
     *         function will return {@code false}
     */
    static boolean propertyIsTrue(ContentPolicy contentPolicy, String propertyName) {
        if (contentPolicy != null) {
            ValueMap properties = contentPolicy.getProperties();
            return (properties != null && properties.get(propertyName, false));
        }
        return false;
    }
}
