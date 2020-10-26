package com.vlter.mmservice.restservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

@Entity
@Table(name = "movies")
public class Movie implements Serializable{

    @Id
    @SequenceGenerator(name = "moviesSeq", sequenceName = "movies_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moviesSeq")
    private Integer id;

    private String title;
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    private LocalTime length;
    private Integer rating;

    public Movie() {
        super();
    }

    public Movie(Integer id, String title, Integer year, Director director, LocalTime length, Integer rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.length = length;
        this.rating = rating;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public Integer getYear() {return year;}
    public void setYear(Integer year) {this.year = year;}

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    public LocalTime getLength() {return length;}
    public void setLength(LocalTime length) {this.length = length;}

    public Integer getRating() {return rating;}
    public void setRating(Integer rating) {this.rating = rating;}

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
