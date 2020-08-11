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
public class CustomComponentTest {

    private static final String CONTENT_ROOT = "/content/customcomponent";
    private static final String TEST_BASE = "/custom";
    private static final String TEST_CONTENT_JSON = "/test-content.json";
    private static final String RESOURCE_JCR_CUSTOM = CONTENT_ROOT + "/jcr:content/root/responsivegrid/customcomponent";

    private AemContext context;
    private CustomComponent customComponent;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        this.context = context;

        //load page content via json
        context.load().json(TEST_BASE + TEST_CONTENT_JSON, CONTENT_ROOT);

        // create sling model
        context.currentResource(RESOURCE_JCR_CUSTOM);
        MockSlingHttpServletRequest request = context.request();
        customComponent = request.adaptTo(CustomComponent.class);
    }

    @Test
    void testExportedType() {
        assertNotNull(customComponent);
        assertEquals(CustomComponent.RESOURCE_TYPE, customComponent.getExportedType());
    }

    @Test
    void testMessage() {
        assertNotNull(customComponent);
        assertEquals("Custom Component Message".toUpperCase(), customComponent.getMessage());
    }

    @Test
    void testStyles() {
        assertNotNull(customComponent);
        assertEquals("text__grey", customComponent.getStyles());
    }
}
