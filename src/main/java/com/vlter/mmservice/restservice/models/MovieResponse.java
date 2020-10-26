package com.vlter.mmservice.restservice.models;

import java.io.Serializable;

/**
 * Created by Tereshchenko on 26.10.2020.
 */
public class MovieResponse implements Serializable{
    private Integer status;
    private Movie movie;

    public MovieResponse() {
    }

    public MovieResponse(Integer status, Movie movie) {
        this.status = status;
        this.movie = movie;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) { this.status = status; }

    public Movie getMovie() { return movie; }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
