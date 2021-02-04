package com.voghan.bookstorespa.angular.core.internal.impl;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.components.AnalyzeContext;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.designer.Cell;
import org.apache.sling.api.resource.Resource;

import java.util.Set;

public class HierarchyComponentContextWrapper {
    private ComponentContext wrappedComponentContext;
    private Page hierarchyPage;

    public HierarchyComponentContextWrapper(ComponentContext wrappedComponentContext, Page hierarchyPage) {
        this.wrappedComponentContext = wrappedComponentContext;
        this.hierarchyPage = hierarchyPage;
    }

    public ComponentContext getParent() {
        return this.wrappedComponentContext.getParent();
    }

    public ComponentContext getRoot() {
        return this.wrappedComponentContext.getRoot();
    }

    public boolean isRoot() {
        return this.wrappedComponentContext.isRoot();
    }

    public Resource getResource() {
        return this.wrappedComponentContext.getResource();
    }

    public Cell getCell() {
        return this.wrappedComponentContext.getCell();
    }

    public EditContext getEditContext() {
        return this.wrappedComponentContext.getEditContext();
    }

    public AnalyzeContext getAnalyzeContext() {
        return this.wrappedComponentContext.getAnalyzeContext();
    }

    public Component getComponent() {
        return this.wrappedComponentContext.getComponent();
    }

    public Page getPage() {
        return this.hierarchyPage;
    }

    public Object getAttribute(String s) {
        return this.wrappedComponentContext.getAttribute(s);
    }

    public Object setAttribute(String s, Object o) {
        return this.wrappedComponentContext.setAttribute(s, o);
    }

    public Set<String> getCssClassNames() {
        return this.wrappedComponentContext.getCssClassNames();
    }

    public boolean hasDecoration() {
        return this.wrappedComponentContext.hasDecoration();
    }

    public void setDecorate(boolean b) {
        this.wrappedComponentContext.setDecorate(b);
    }

    public String getDecorationTagName() {
        return this.wrappedComponentContext.getDecorationTagName();
    }

    public void setDecorationTagName(String s) {
        this.wrappedComponentContext.setDecorationTagName(s);
    }

    public String getDefaultDecorationTagName() {
        return this.wrappedComponentContext.getDefaultDecorationTagName();
    }

    public void setDefaultDecorationTagName(String s) {
        this.wrappedComponentContext.setDecorationTagName(s);
    }
}
