package com.voghan.bookstorespa.angular.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.commons.Filter;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.day.cq.commons.jcr.JcrConstants.NT_UNSTRUCTURED;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes=ButtonTagOptionsDataSource.RESOURCE_TYPE,
        methods= HttpConstants.METHOD_GET)
@ServiceDescription("Button Style Servlet")
public class ButtonTagOptionsDataSource extends AbstractOptionsDataSource {
    private final Logger logger = LoggerFactory.getLogger(ButtonTagOptionsDataSource.class);
    public static final String RESOURCE_TYPE = "bookstore-spa/components/datasource/button-style";
    private static final String FIELD_NAME = "button";

    private static final Filter<Tag> TAG_FILTER_INCLUDE_ALL = tag -> true;

    @Override
    protected List<Option> getOptions(SlingHttpServletRequest request, String defaultOption) {
        TagManager tagManager = request.getResourceResolver().adaptTo(TagManager.class);
        Tag containerTag = tagManager.resolve(getContainerTag());

        List<Option> options = new ArrayList<>();
        if (containerTag != null) {
            options = Lists.newArrayList(containerTag.listChildren(getTagFilter()))
                    .stream()
                    .map(tag -> new Option(tag.getDescription(), tag.getTitle()))
                    .collect(Collectors.toList());
        }

        return sortOptions(options, defaultOption);
    }

    private List<Option> sortOptions(List<Option> options, String defaultValue) {
        final List<Option> sortedOptions = new ArrayList<>();
        final List<Option> alphabetSortedOptions = options.stream().sorted(Option.ALPHA_IGNORE_CASE)
                .collect(Collectors.toList());
        for (final Option option : alphabetSortedOptions) {
            if (option.getValue().equals(defaultValue)) {
                sortedOptions.add(0, option);
            } else {
                sortedOptions.add(option);
            }
        }

        return sortedOptions;
    }

    private String getContainerTag() {
        return "/content/cq:tags/bookstore/styles/" + getFieldName();
    }

    private Filter<Tag> getTagFilter() {
        return TAG_FILTER_INCLUDE_ALL;
    }

    private String getFieldName() {
        return FIELD_NAME;
    }
}
