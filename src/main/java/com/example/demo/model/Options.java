package com.example.demo.model;

public enum Options {
    ADD_TASK("tasks/add"),
    UNDONE("tasks/undone"),
    DONE ("tasks/done"),
    ADD_CATEGORY("categories/add");

    //TODO show

    private String url;

    Options(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
