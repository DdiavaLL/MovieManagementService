package com.vlter.mmservice.restservice.models;

import javax.persistence.*;

/**
 * Created by Tereshchenko on 19.10.2020.
 */

@Entity
@Table(name = "directors")
public class Director {
    @Id
    @SequenceGenerator(name = "directorsSeq", sequenceName = "directors_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "directorsSeq")
    private Integer id;

    private String director;

    public Director() {
        super();
    }

    public Director(Integer id, String director) {
        this.id = id;
        this.director = director;
    }

    public Director(String director) {
        this.director = director;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getDirector() {return director;}
    public void setDirector(String director) {this.director = director;}
}
