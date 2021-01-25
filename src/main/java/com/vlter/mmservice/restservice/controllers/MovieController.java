package com.vlter.mmservice.restservice.controllers;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

import java.io.Serializable;
import java.util.List;
import com.vlter.mmservice.restservice.exceptions.DeleteMovieException;
import com.vlter.mmservice.restservice.exceptions.ThereIsNoSuchMovieException;
import com.vlter.mmservice.restservice.models.*;
import com.vlter.mmservice.restservice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    // Список всех кинофильмов
    @GetMapping()
    public List getAllMovies() {
        return movieService.movieRepository.findAll();
    }

    // Получить информацию о фильме по id
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        Movie rezMovie = movieService.movieRepository.findById(id).orElse(null);
        if (rezMovie == null) {
            throw new ThereIsNoSuchMovieException();
        }
        return rezMovie;
    }

    // Добавление записи о кинофильме
    @PostMapping()
    public Serializable postMovie(@RequestBody Movie movie) {
        Movie rezultMovie = movieService.addMovie(movie);
        return rezultMovie;
    }

    // Изменение информации о кинофильме с указанным id
    @PatchMapping("/{id}")
    public Serializable updateMovie(@PathVariable Integer id, @RequestBody Movie movieDetails) {
        Movie findRez = movieService.movieRepository.findById(id).orElse(null);
        movieService.updateMovie(id, movieDetails);
        return findRez;
    }

    // Удаление записи о фильме с указанным id
    @DeleteMapping("/{id}")
    public Serializable deleteMovie(@PathVariable Integer id) {
        Movie findRez = movieService.movieRepository.findById(id).orElse(null);
        if (findRez == null) {
            throw new ThereIsNoSuchMovieException();
        }
        else {
            try {
                movieService.deleteMovie(id);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DeleteMovieException();
            }
        }
        return findRez;
    }
}
