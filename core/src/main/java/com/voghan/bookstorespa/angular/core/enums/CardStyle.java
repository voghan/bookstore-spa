package com.voghan.bookstorespa.angular.core.enums;

import com.voghan.bookstorespa.angular.core.models.Option;

import java.util.ArrayList;
import java.util.List;

public enum CardStyle {

    Large("card__large"),
    Medium("card__medium"),
    Small("card__small");

    private String cssClass;

    CardStyle(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }

    public static List<Option> options() {
        final List<Option> options = new ArrayList<>();

        for (CardStyle cardStyle : CardStyle.values()) {
            options.add(new Option(cardStyle.getCssClass(), cardStyle.name()));
        }

        return options;
    }
}
