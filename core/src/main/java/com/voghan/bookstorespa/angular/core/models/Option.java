package com.voghan.bookstorespa.angular.core.models;

import java.util.Comparator;

public class Option {
    public static final Comparator<Option> ALPHA = Comparator.comparing(Option::getText);

    public static final Comparator<Option> ALPHA_IGNORE_CASE = (option1, option2) -> {
        return option1.getText().compareToIgnoreCase(option2.getText());
    };

    private final String value;
    private final String text;

    public Option(final String value, final String text) {
        this.value = value;
        this.text = text;
    }


    public String getText() {
        return this.text;
    }

    public String getValue() {
        return this.value;
    }
}
