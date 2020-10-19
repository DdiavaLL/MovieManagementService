package com.vlter.mmservice.restservice;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorRepository directorRepository;

    // Список всех кинофильмов
    @GetMapping()
    public List getAllNotes() {
        return movieRepository.findAll();
    }

    // Получить информацию о фильме по id
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable (value = "id") Integer movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }
}
