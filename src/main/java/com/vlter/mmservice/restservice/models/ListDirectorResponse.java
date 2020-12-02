package com.vlter.mmservice.restservice.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tereshchenko on 01.12.2020.
 */

public class ListDirectorResponse implements Serializable {
    private Integer status;
    private List<Director> list;

    public ListDirectorResponse() {
    }

    public ListDirectorResponse(Integer status, List<Director> list) {
        this.status = status;
        this.list = list;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Director> getList() { return list; }

    public void setList(List<Director> list) {
        this.list = list;
    }
}
