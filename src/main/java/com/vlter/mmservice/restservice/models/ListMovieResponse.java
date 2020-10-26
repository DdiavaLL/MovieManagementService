package com.vlter.mmservice.restservice.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tereshchenko on 26.10.2020.
 */

public class ListMovieResponse implements Serializable{
    private Integer status;
    private List<Movie> list;

    public ListMovieResponse() {
    }

    public ListMovieResponse(Integer status, List<Movie> list) {
        this.status = status;
        this.list = list;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Movie> getList() { return list; }

    public void setList(List<Movie> list) {
        this.list = list;
    }
}
