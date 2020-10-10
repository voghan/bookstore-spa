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
public class CustomLinkTest {

    private static final String CONTENT_ROOT = "/content/customlink";
    private static final String TEST_BASE = "/link";
    private static final String TEST_CONTENT_JSON = "/test-content.json";
    private static final String RESOURCE_JCR_TITLE = CONTENT_ROOT + "/jcr:content/root/responsivegrid/link";

    private CustomLink customLink;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        //load page content via json
        context.load().json(TEST_BASE + TEST_CONTENT_JSON, CONTENT_ROOT);

        // create sling model
        context.currentResource(RESOURCE_JCR_TITLE);
        MockSlingHttpServletRequest request = context.request();
        customLink = request.adaptTo(CustomLink.class);
    }

    @Test
    void testExportedType() {
        assertNotNull(customLink);
        assertEquals(CustomLink.RESOURCE_TYPE, customLink.getExportedType());
    }

    @Test
    void testText()  {
        assertNotNull(customLink);
        assertEquals("Books", customLink.getText());
    }

    @Test
    void testLink()  {
        assertNotNull(customLink);
        assertEquals("/content/bookstore-spa/us/en/home/books", customLink.getLink());
    }
}
