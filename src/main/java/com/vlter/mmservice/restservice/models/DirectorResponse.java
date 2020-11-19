package com.vlter.mmservice.restservice.models;

import java.io.Serializable;

/**
 * Created by Tereshchenko on 19.11.2020.
 */
public class DirectorResponse implements Serializable {
    private Integer status;
    private Director director;

    public DirectorResponse() {
    }

    public DirectorResponse(Integer status, Director director) {
        this.status = status;
        this.director = director;
    }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Director getDirector() { return director; }

    public void setDirector(Director director) { this.director = director; }
}
