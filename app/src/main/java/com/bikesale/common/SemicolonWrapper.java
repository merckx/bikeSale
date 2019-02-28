package com.bikesale.common;

public class SemicolonWrapper {

    private String text;
    public SemicolonWrapper(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return this.text + ": ";
    }
}
