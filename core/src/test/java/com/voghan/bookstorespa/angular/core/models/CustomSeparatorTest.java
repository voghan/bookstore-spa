package com.voghan.bookstorespa.angular.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
public class CustomSeparatorTest {
    private static final String CONTENT_ROOT = "/content/customseparator";
    private static final String TEST_BASE = "/separator";
    private static final String TEST_CONTENT_JSON = "/test-content.json";
    private static final String RESOURCE_JCR_SEPARATOR = CONTENT_ROOT + "/jcr:content/root/responsivegrid/separator";

    private AemContext context;
    private CustomSeparator separator;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        this.context = context;

        //load page content via json
        context.load().json(TEST_BASE + TEST_CONTENT_JSON, CONTENT_ROOT);

        // create sling model
        context.currentResource(RESOURCE_JCR_SEPARATOR);
        MockSlingHttpServletRequest request = context.request();
        separator = request.adaptTo(CustomSeparator.class);
    }

    @Test
    void testExportedType() {
        assertNotNull(separator);
        assertEquals(CustomSeparator.RESOURCE_TYPE, separator.getExportedType());
    }
}
