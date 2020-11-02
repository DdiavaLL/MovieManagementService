package com.vlter.mmservice.restservice.controllers;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import com.vlter.mmservice.restservice.models.*;
import com.vlter.mmservice.restservice.repositories.DirectorRepository;
import com.vlter.mmservice.restservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorRepository directorRepository;

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    // Список всех кинофильмов
    @GetMapping()
    public ListMovieResponse getAllNotes() {
        ListMovieResponse listMovies = new ListMovieResponse(200, movieRepository.findAll());
        return listMovies;
    }

    // Получить информацию о фильме по id
    /*@GetMapping("/{id}")
    public Serializable getMovieById(@PathVariable (value = "id") Integer movieId) {
        Movie rezMovie = movieRepository.findById(movieId).orElse(null);
        if (rezMovie == null) {
            StatusMessage rezSearch = new StatusMessage(404, "Фильма, с указанным id, не существует!");
            return rezSearch;
        }
        MovieResponse oneMovie = new MovieResponse(200, movieRepository.findById(movieId).orElse(null));
        return oneMovie;
    }*/

    //*******************************
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable (value = "id") Integer movieId) {
        Movie rezMovie = movieRepository.findById(movieId).orElse(null);
        if (rezMovie == null) {
            throw new ThereIsNoSuchMovieException();
        }
        return rezMovie;
    }
    //*******************************


    // Добавление записи о кинофильме
    @RequestMapping(method = RequestMethod.POST)
    public Serializable postMovie(@RequestBody Movie movie) {
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        if (violations != null) {
            for (ConstraintViolation<Movie> violation : violations) {
                if (violation != null) {
                    return new StatusMessage(400, violation.getMessage());
                }
            }
        }
        Director curDir = movie.getDirector();
        if (curDir.getId() != null) {
            Director help = directorRepository.findById(curDir.getId()).orElse(null);
            StatusMessage rezAdding = CorrectAdding(curDir, movie, help);
            if (rezAdding != null)
                return rezAdding;
        }
        else {
            Director help1 = directorRepository.findByDirector(curDir.getDirector());
            StatusMessage rezAdding = CorrectAdding(curDir, movie, help1);
            if (rezAdding != null)
                return rezAdding;
        }
        Director help = directorRepository.findByDirector(movie.getDirector().getDirector() );
        StatusMessage ca = CorrectAdding(movie.getDirector(), movie, help);
        if (ca != null)
            return ca;
        MovieResponse rezMovie = new MovieResponse(200, movieRepository.findById(movie.getId()).orElse(null));
        return rezMovie;
    }

    // Изменение информации о кинофильме с указанным id
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Serializable updateMovie(@PathVariable(value = "id") Integer movieId, @RequestBody Movie movieDetails) {
        Set<ConstraintViolation<Movie>> violations = validator.validate(movieDetails);
        if (violations != null) {
            for (ConstraintViolation<Movie> violation : violations) {
                if (violation != null) {
                    return new StatusMessage(400, violation.getMessage());
                }
            }
        }
        Movie findRez = movieRepository.findById(movieId).orElse(null);
        if (findRez == null) {
            StatusMessage serchRez = new StatusMessage(404, "Фильма, с указанным id, не существует!");
            return serchRez;
        }
        else {
            Director curDir = movieDetails.getDirector();
            if (curDir.getId() != null) {
                Director help = directorRepository.findById(curDir.getId()).orElse(null);
                findRez.setTitle(movieDetails.getTitle());
                findRez.setYear(movieDetails.getYear());
                findRez.setDirector(movieDetails.getDirector());
                findRez.setLength(movieDetails.getLength());
                findRez.setRating(movieDetails.getRating());
                StatusMessage rezAdding = CorrectAdding(curDir, findRez, help);
                if (rezAdding != null)
                    return rezAdding;
            }
            else {
                Director help1 = directorRepository.findByDirector(curDir.getDirector());
                findRez.setTitle(movieDetails.getTitle());
                findRez.setYear(movieDetails.getYear());
                findRez.setDirector(movieDetails.getDirector());
                findRez.setLength(movieDetails.getLength());
                findRez.setRating(movieDetails.getRating());
                StatusMessage rezAdding = CorrectAdding(curDir, findRez, help1);
                if (rezAdding != null)
                    return rezAdding;
            }
        }
        MovieResponse rezMovie = new MovieResponse(200, findRez);
        return rezMovie;
    }

    // Удаление записи с указанным id
    @DeleteMapping("/{id}")
    public Serializable deleteMovie(@PathVariable (value = "id") Integer movieId) {
        Movie findRez = movieRepository.findById(movieId).orElse(null);
        if (findRez == null) {
            StatusMessage serchRez = new StatusMessage(404, "Фильма, с указанным id, не существует!");
            return serchRez;
        }
        else {
            try {
                List<Movie> directorClones = movieRepository.findMoviesContainsDir(findRez.getDirector());
                if (directorClones.size() > 1) {
                    findRez.setDirector(null);
                }
                movieRepository.delete(findRez);
            } catch (Exception e) {
                e.printStackTrace();
                StatusMessage addingMessage = GenerateStatus("Ошибка во время удаления кинофильма; ", e);
                return addingMessage;
            }
        }
        StatusMessage succMessage = new StatusMessage(202, "Запись о кинофильме была удалена!");
        return succMessage;
    }

    private StatusMessage CorrectAdding(Director curDir, Movie newMovie, Director help) {
        if (help != null) {
            try {
                newMovie.setDirector(help);
                movieRepository.save(newMovie);
            } catch (Exception e) {
                e.printStackTrace();
                StatusMessage addingMessage = GenerateStatus("Ошибка во время добавления нового кинофильма; ", e);
                return addingMessage;
            }
        }
        else {
            try {
                directorRepository.save(curDir);
                newMovie.setDirector(curDir);
                movieRepository.save(newMovie);
            } catch (Exception e) {
                e.printStackTrace();
                StatusMessage addingMessage = GenerateStatus("Ошибка во время добавления нового кинофильма; ", e);
                return addingMessage;
            }
        }
        return null;
    }

    private StatusMessage GenerateStatus(String message, Exception e) {
        return new StatusMessage(500, message + e.toString());
    }
}
