package com.voghan.bookstorespa.angular.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.google.common.collect.Maps;
import com.voghan.bookstorespa.angular.core.enums.CardStyle;
import com.voghan.bookstorespa.angular.core.models.Option;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.day.cq.commons.jcr.JcrConstants.NT_UNSTRUCTURED;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes= CardStyleOptionsDataSource.RESOURCE_TYPE,
        methods= HttpConstants.METHOD_GET)
@ServiceDescription("Card Style Servlet")
public class CardStyleOptionsDataSource extends SlingSafeMethodsServlet {
    private final Logger logger = LoggerFactory.getLogger(CardStyleOptionsDataSource.class);
    public static final String RESOURCE_TYPE = "bookstore-spa/components/datasource/card-style";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        ResourceResolver resourceResolver = request.getResourceResolver();

        List<Option> options = CardStyle.options();

        // transform the list of options into a list of synthetic resources
        List<Resource> resources = options.stream().map(option -> {
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(options.size());

            map.put("value", option.getValue());
            map.put("text", option.getText());

            ValueMap valueMap = new ValueMapDecorator(map);

            return new ValueMapResource(resourceResolver, new ResourceMetadata(), NT_UNSTRUCTURED, valueMap);
        }).collect(Collectors.toList());

        DataSource dataSource = new SimpleDataSource(resources.iterator());

        request.setAttribute(DataSource.class.getName(), dataSource);
    }

}
