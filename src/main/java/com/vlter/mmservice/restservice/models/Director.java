package com.vlter.mmservice.restservice.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Tereshchenko on 19.10.2020.
 */

@Entity
@Table(name = "directors")
public class Director implements Serializable{
    @Id
    @SequenceGenerator(name = "directorsSeq", sequenceName = "directors_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "directorsSeq")
    private Integer id;

    @Size(max = 100, message = "ФИО режиссера кинофильма должно быть не длинее 100 символов!")
    @NotBlank(message = "Во входящем запросе отсутствует режиссер кинофильма!")
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
