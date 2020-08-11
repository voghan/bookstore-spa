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
public class CustomTextTest {
    private static final String CONTENT_ROOT = "/content/customtext";
    private static final String TEST_BASE = "/text";
    private static final String TEST_CONTENT_JSON = "/test-content.json";
    private static final String RESOURCE_JCR_TEXT = CONTENT_ROOT + "/jcr:content/root/responsivegrid/text";

    private AemContext context;
    private CustomText customText;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        this.context = context;

        //load page content via json
        context.load().json(TEST_BASE + TEST_CONTENT_JSON, CONTENT_ROOT);

        // create sling model
        context.currentResource(RESOURCE_JCR_TEXT);
        MockSlingHttpServletRequest request = context.request();
        customText = request.adaptTo(CustomText.class);
    }

    @Test
    void testExportedType() {
        assertNotNull(customText);
        assertEquals(CustomText.RESOURCE_TYPE, customText.getExportedType());
    }

    @Test
    void testText() {
        assertNotNull(customText);
        assertEquals("<h2>Rich Text</h2>\n" +
                "<p>Here is the<b> rich text</b> component.</p>\n", customText.getText());
    }

    @Test
    void testRichText() {
        assertNotNull(customText);
        assertEquals(true, customText.isRichText());
    }
}
