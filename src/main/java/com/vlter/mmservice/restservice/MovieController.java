package com.vlter.mmservice.restservice;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    // Получить все записи
    @GetMapping("/api/movies")
    public List getAllNotes() {
        List<Movie> movies = movieRepository.findAll();
        return movieRepository.findAll();
    }

}
