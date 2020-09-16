package com.voghan.bookstorespa.angular.core.enums;

import com.voghan.bookstorespa.angular.core.models.Option;

import java.util.ArrayList;
import java.util.List;

public enum LinkTarget {

    Target("", "Target"),
    Blank("_blank", "New Tab"),
    Self("_self", "Same Tab"),
    Parent("_parent", "Parent Frame" ),
    Top("_top", "Top Frame");

    private String cssClass;
    private String label;

    LinkTarget(String cssClass, String label) {
        this.cssClass = cssClass;
        this.label = label;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getLabel() {
        return label;
    }

    public static List<Option> options() {
        final List<Option> options = new ArrayList<>();

        for (LinkTarget linkTarget : LinkTarget.values()) {
            options.add(new Option(linkTarget.getCssClass(), linkTarget.getLabel()));
        }

        return options;
    }

    public static LinkTarget find(String cssClass) {
        LinkTarget target = null;
        for (LinkTarget linkTarget : LinkTarget.values()) {
            if (linkTarget.getCssClass().equals(cssClass)) {
                target = linkTarget;
            }
        }
        return target;
    }
}
