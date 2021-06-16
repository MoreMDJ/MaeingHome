package com.maeinghome.mybatisstudy;

public class DotMethod {
    protected final String noGetter = "this has no getter";
    protected final String hasGetter = "this has getter";

    public String getHasGetter() {
        System.out.println("using getter!");
        return hasGetter;
    }
}
