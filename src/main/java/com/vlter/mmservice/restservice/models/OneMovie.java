package com.vlter.mmservice.restservice.models;

import com.vlter.mmservice.restservice.models.Movie;

import java.io.Serializable;

/**
 * Created by Tereshchenko on 26.10.2020.
 */
public class OneMovie implements Serializable{
    private Integer status;
    private Movie movie;

    public OneMovie() {
    }

    public OneMovie(Integer status, Movie movie) {
        this.status = status;
        this.movie = movie;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
