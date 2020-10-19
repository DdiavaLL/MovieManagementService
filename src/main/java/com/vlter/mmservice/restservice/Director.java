package com.vlter.mmservice.restservice;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tereshchenko on 19.10.2020.
 */

@Entity
@Table(name = "directors")
public class Director {
    @Id
    private Integer id;

    private String director;

    public Director() {
        super();
    }

    public Director(Integer id, String director) {
        this.id = id;
        this.director = director;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getDirector() {return director;}
    public void setDirector(String director) {this.director = director;}
}
