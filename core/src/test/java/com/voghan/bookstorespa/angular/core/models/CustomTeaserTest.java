package com.voghan.bookstorespa.angular.core.models;

import com.adobe.cq.wcm.core.components.models.ListItem;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
public class CustomTeaserTest {

    private static final String CONTENT_ROOT = "/content/teaser";
    private static final String TEST_BASE = "/teaser";
    private static final String TEST_CONTENT_JSON = "/test-content.json";
    private static final String RESOURCE_JCR_TEASER = CONTENT_ROOT + "/jcr:content/root/responsivegrid/teaser";

    private AemContext context;
    private CustomTeaser teaser;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        this.context = context;

        //load page content via json
        context.load().json(TEST_BASE + TEST_CONTENT_JSON, CONTENT_ROOT);

        // create sling model
        context.currentResource(RESOURCE_JCR_TEASER);
        MockSlingHttpServletRequest request = context.request();
        teaser = request.adaptTo(CustomTeaser.class);
    }

    @Test
    void testExportedType() {
        assertNotNull(teaser);
        assertEquals(CustomTeaser.RESOURCE_TYPE, teaser.getExportedType());
    }

    @Test
    void testImagePath() {
        assertNotNull(teaser);
        assertEquals("/content/dam/core-components-examples/library/sample-assets/mountain-range.jpg", teaser.getImagePath());
    }

    @Test
    void testTitle() {
        assertNotNull(teaser);
        assertEquals("Sample Teaser", teaser.getTitle());
    }

    @Test
    void testDescription() {
        assertNotNull(teaser);
        assertEquals("<p>Here is some sample description content.</p>", teaser.getDescription());
    }

    @Test
    void testLinkURL() {
        assertNotNull(teaser);
        assertNull(teaser.getLinkURL());
    }

    @Test
    void testActionsEnabled() {
        assertNotNull(teaser);
        assertEquals(true, teaser.isActionsEnabled());
    }

    @Test
    void testActions() {
        assertNotNull(teaser);
        assertEquals(1, teaser.getActions().size());
    }

    @Test
    void testActionItem() {
        assertNotNull(teaser);
        assertEquals(1, teaser.getActions().size());
        ListItem item = teaser.getActions().get(0);
        ActionItem actionItem = (ActionItem)item;
        assertEquals("Books", actionItem.getTitle());
        assertEquals("/content/bookstore-spa/us/en/home/books", actionItem.getURL());
    }
}
