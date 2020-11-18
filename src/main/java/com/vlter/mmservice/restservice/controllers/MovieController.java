package com.vlter.mmservice.restservice.controllers;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

import java.io.Serializable;
import com.vlter.mmservice.restservice.exceptions.DeleteException;
import com.vlter.mmservice.restservice.exceptions.ThereIsNoSuchMovieException;
import com.vlter.mmservice.restservice.models.*;
import com.vlter.mmservice.restservice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    // Список всех кинофильмов
    @GetMapping()
    public ListMovieResponse getAllNotes() {
        ListMovieResponse listMovies = new ListMovieResponse(HttpStatus.OK.value(), movieService.movieRepository.findAll());
        return listMovies;
    }

    // Получить информацию о фильме по id
    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable (value = "id") Integer movieId) {
        Movie rezMovie = movieService.movieRepository.findById(movieId).orElse(null);
        if (rezMovie == null) {
            throw new ThereIsNoSuchMovieException();
        }
        return new MovieResponse(HttpStatus.OK.value(), rezMovie);
    }

    // Добавление записи о кинофильме
    @RequestMapping(method = RequestMethod.POST)
    public Serializable postMovie(@RequestBody Movie movie) {
        movieService.validateMovie(movie);
        Movie rezultMovie = movieService.addMovie(movie);

        MovieResponse rezMovie = new MovieResponse(HttpStatus.OK.value(), rezultMovie);
        return rezMovie;
    }

    // Изменение информации о кинофильме с указанным id
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Serializable updateMovie(@PathVariable(value = "id") Integer movieId, @RequestBody Movie movieDetails) {
        movieService.validateMovie(movieDetails);
        Movie findRez = movieService.movieRepository.findById(movieId).orElse(null);
        movieService.updateMovie(movieId, movieDetails);

        MovieResponse rezMovie = new MovieResponse(HttpStatus.OK.value(), findRez);
        return rezMovie;
    }

    // Удаление записи с указанным id
    @DeleteMapping("/{id}")
    public Serializable deleteMovie(@PathVariable (value = "id") Integer movieId) {
        Movie findRez = movieService.movieRepository.findById(movieId).orElse(null);
        if (findRez == null) {
            throw new ThereIsNoSuchMovieException();
        }
        else {
            try {
                movieService.deleteMovie(movieId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DeleteException();
            }
        }
        MovieResponse rezMovie = new MovieResponse(HttpStatus.OK.value(), findRez);
        return rezMovie;
    }
}
