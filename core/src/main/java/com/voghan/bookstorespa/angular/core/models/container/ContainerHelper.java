package com.voghan.bookstorespa.angular.core.models.container;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.SlingModelFilter;
import com.adobe.cq.wcm.core.components.models.Container;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.day.cq.wcm.api.TemplatedResource;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentManager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;

import java.util.*;

public class ContainerHelper implements Container {

    protected Resource resource;
    protected SlingHttpServletRequest request;
    protected SlingModelFilter slingModelFilter;
    protected ModelFactory modelFactory;

    protected List<ListItem> items;
    protected List<Resource> childComponents;
    protected List<Resource> filteredChildComponents;
    protected Map<String, ? extends ComponentExporter> itemModels;
    private String[] exportedItemsOrder;

    public ContainerHelper(SlingHttpServletRequest servletRequest, SlingModelFilter slingModelFilter, ModelFactory modelFactory) {
        this.request = servletRequest;
        this.resource = servletRequest.getResource();
        this.slingModelFilter = slingModelFilter;
        this.modelFactory = modelFactory;
    }


    private List<Resource> readChildren() {
        List<Resource> children = new LinkedList();
        Resource effectiveResource = this.getEffectiveResource();
        if (effectiveResource != null) {
            ComponentManager componentManager = (ComponentManager)this.request.getResourceResolver().adaptTo(ComponentManager.class);
            if (componentManager != null) {
                effectiveResource.getChildren().forEach((res) -> {
                    Component component = componentManager.getComponentOfResource(res);
                    if (component != null) {
                        children.add(res);
                    }
                });
            }
        }

        return children;
    }

    protected List<Resource> getChildren() {
        if (this.childComponents == null) {
            this.childComponents = this.readChildren();
        }

        return this.childComponents;
    }

    protected List<Resource> getFilteredChildren() {
        if (this.filteredChildComponents == null) {
            this.filteredChildComponents = new LinkedList();
            Iterable var10000 = this.slingModelFilter.filterChildResources(this.getChildren());
            List var10001 = this.filteredChildComponents;
            var10000.forEach(var10001::add);
        }

        return this.filteredChildComponents;
    }

    public Map<String, ? extends ComponentExporter> getExportedItems() {
        if (this.itemModels == null) {
            this.itemModels = this.getItemModels(this.request, ComponentExporter.class);
        }

        return this.itemModels;
    }

    public String[] getExportedItemsOrder() {
        if (this.exportedItemsOrder == null) {
            Map<String, ? extends ComponentExporter> models = this.getExportedItems();
            if (!models.isEmpty()) {
                this.exportedItemsOrder = (String[])models.keySet().toArray(ArrayUtils.EMPTY_STRING_ARRAY);
            } else {
                this.exportedItemsOrder = ArrayUtils.EMPTY_STRING_ARRAY;
            }
        }

        return (String[]) Arrays.copyOf(this.exportedItemsOrder, this.exportedItemsOrder.length);
    }

    protected Map<String, ComponentExporter> getItemModels(SlingHttpServletRequest request, Class<ComponentExporter> modelClass) {
        Map<String, ComponentExporter> models = new LinkedHashMap();
        this.getFilteredChildren().forEach((child) -> {
            ComponentExporter model = (ComponentExporter)this.modelFactory.getModelFromWrappedRequest(request, child, modelClass);
            if (model != null) {
                models.put(child.getName(), model);
            }

        });
        models.entrySet().forEach((entry) -> {
            ResourceListItem match = (ResourceListItem)this.getItems().stream().filter((item) -> {
                return item != null && StringUtils.isNotEmpty(item.getName()) && StringUtils.equals(item.getName(), (CharSequence)entry.getKey());
            }).findFirst().get();
            if (match != null) {
                entry.setValue(new ContainerHelper.JsonWrapper((ComponentExporter)entry.getValue(), match));
            }

        });
        return models;
    }

    protected Resource getEffectiveResource() {
        if (this.resource != null) {
            return this.resource instanceof TemplatedResource ? this.resource : (Resource)Optional.ofNullable((Resource)this.request.adaptTo(TemplatedResource.class)).orElse(this.resource);
        } else {
            return null;
        }
    }

    @JsonIgnore
    public List<ListItem> getItems() {
        if (this.items == null) {
            this.items = this.readItems();
        }

        return this.items;
    }

    protected List<ListItem> readItems() {
        List<ListItem> items = new LinkedList();
        this.getChildren().forEach((res) -> {
            items.add(new ResourceListItem(this.request, res));
        });
        return items;
    }

    static class JsonWrapper implements ComponentExporter {
        private ComponentExporter inner;
        private String panelTitle;

        JsonWrapper(ComponentExporter inner, ResourceListItem item) {
            this.inner = inner;
            this.panelTitle = item.getPanelTitle();
        }

        @JsonUnwrapped
        public ComponentExporter getInner() {
            return this.inner;
        }

        @JsonProperty("cq:panelTitle")
        public String getPanelTitle() {
            return this.panelTitle;
        }

        public String getExportedType() {
            return this.inner != null ? this.inner.getExportedType() : "";
        }
    }
}
