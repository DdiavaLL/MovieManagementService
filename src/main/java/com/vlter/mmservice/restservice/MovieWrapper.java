package com.vlter.mmservice.restservice;

/**
 * Created by Tereshchenko on 20.10.2020.
 */
public class MovieWrapper {
    private String json;

    public MovieWrapper() {
    }

    public MovieWrapper(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
