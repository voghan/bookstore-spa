package com.voghan.bookstorespa.angular.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.voghan.bookstorespa.angular.core.enums.CardStyle;
import com.voghan.bookstorespa.angular.core.enums.LinkTarget;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
public class LinkTargetOptionsDataSourceTest {

    private static final String CONTENT_ROOT = "/content/teaser";
    private static final String TEST_BASE = "/teaser";
    private static final String TEST_CONTENT_JSON = "/test-content.json";

    private AemContext context;
    private LinkTargetOptionsDataSource dataSource;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        this.context = context;
        this.dataSource = new LinkTargetOptionsDataSource();
        context.load().json(TEST_BASE + TEST_CONTENT_JSON, CONTENT_ROOT);
    }

    @Test
    void testDataSource() {
        dataSource.doGet(this.context.request(), this.context.response());
        DataSource dataSource = (DataSource) this.context.request().getAttribute(DataSource.class.getName());
        assertNotNull(dataSource);
        assertTrue(dataSource.iterator().hasNext());
        dataSource.iterator().forEachRemaining(resource -> {
            ValueMap props = resource.getValueMap();
            assertNotNull(props);
            String text = (String) props.get("text");
            String value = (String) props.get("value");
            LinkTarget linkTarget = LinkTarget.find(value);
            assertNotNull(linkTarget);
            assertEquals(linkTarget.getLabel(), text);
        });
    }
}
