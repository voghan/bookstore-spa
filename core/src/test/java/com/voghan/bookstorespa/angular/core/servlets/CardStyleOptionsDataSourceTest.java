package com.voghan.bookstorespa.angular.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.voghan.bookstorespa.angular.core.enums.CardStyle;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
public class CardStyleOptionsDataSourceTest {

    private static final String CONTENT_ROOT = "/content/card";
    private static final String TEST_BASE = "/card";
    private static final String TEST_CONTENT_JSON = "/test-content.json";

    private AemContext context;
    private CardStyleOptionsDataSource dataSource;

    @BeforeEach
    public void setup(AemContext context) throws Exception {
        this.context = context;
        this.dataSource = new CardStyleOptionsDataSource();
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
            CardStyle cardStyle = CardStyle.valueOf(text);
            assertEquals(cardStyle.getCssClass(), value);
        });
    }
}
