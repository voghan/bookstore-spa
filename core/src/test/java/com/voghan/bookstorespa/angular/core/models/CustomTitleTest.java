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
public class CustomTitleTest {

    private static final String CONTENT_ROOT = "/content/customtitle";
    private static final String TEST_BASE = "/title";
    private static final String TEST_CONTENT_JSON = "/test-content.json";
    private static final String RESOURCE_JCR_TITLE = CONTENT_ROOT + "/jcr:content/root/responsivegrid/title";

    private AemContext context;
    private CustomTitle customTitle;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        this.context = context;

        //load page content via json
        context.load().json(TEST_BASE + TEST_CONTENT_JSON, CONTENT_ROOT);

        // create sling model
        context.currentResource(RESOURCE_JCR_TITLE);
        MockSlingHttpServletRequest request = context.request();
        customTitle = request.adaptTo(CustomTitle.class);
    }

    @Test
    void testExportedTyoe() {
        assertNotNull(customTitle);
        assertEquals(CustomTitle.RESOURCE_TYPE, customTitle.getExportedType());
    }

    @Test
    void testTitle() throws Exception {
        assertNotNull(customTitle);
        assertEquals("Sample Title", customTitle.getText());
    }

    @Test
    void testLinkURL() throws Exception {
        assertNotNull(customTitle);
        assertEquals("/content/bookstore-spa/us/en/home/books", customTitle.getLinkURL());
    }

    @Test
    void testType() throws Exception {
        assertNotNull(customTitle);
        assertEquals("h1", customTitle.getType());
    }
}
