package com.voghan.bookstorespa.angular.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.google.common.collect.Maps;
import com.voghan.bookstorespa.angular.core.models.Option;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.day.cq.commons.jcr.JcrConstants.NT_UNSTRUCTURED;

public abstract class AbstractOptionsDataSource extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        ResourceResolver resourceResolver = request.getResourceResolver();

        List<Option> options = getOptions(request, StringUtils.EMPTY);

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

    abstract List<Option> getOptions(SlingHttpServletRequest request, String defaultValue);

}
