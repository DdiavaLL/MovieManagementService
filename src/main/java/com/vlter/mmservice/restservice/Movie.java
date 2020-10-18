package com.vlter.mmservice.restservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

/**
 * Created by Tereshchenko on 18.10.2020.
 */
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private Integer id;

    private String title;
    private Integer year;
    private String director_id;
    private LocalTime length;
    private Integer rating;

    public Movie() {
        super();
    }

    public Movie(Integer id, String title, Integer year, String director, LocalTime length, Integer rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director_id = director;
        this.length = length;
        this.rating = rating;
    }

    public int getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public int getYear() {return year;}
    public void setYear(Integer year) {this.year = year;}

    public String getDirector() {return director_id;}
    public void setDirector(String director) {this.director_id = director;}

    public LocalTime getLength() {return length;}
    public void setLength(LocalTime length) {this.length = length;}

    public int getRating() {return rating;}
    public void setRating(Integer rating) {this.rating = rating;}
}
