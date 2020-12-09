package com.vlter.mmservice.restservice.services;

import com.vlter.mmservice.restservice.exceptions.IncorrectMovieSaveException;
import com.vlter.mmservice.restservice.exceptions.ThereIsNoSuchMovieException;
import com.vlter.mmservice.restservice.exceptions.ValidationException;
import com.vlter.mmservice.restservice.models.Director;
import com.vlter.mmservice.restservice.models.Movie;
import com.vlter.mmservice.restservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * Created by Tereshchenko on 17.11.2020.
 */
@Service
public class MovieService {
    @Autowired
    public MovieRepository movieRepository;

    @Autowired
    DirectorService directorService;

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    public void validateMovie(Movie movie) {
        String movieMessage = directorService.validateDirector(movie.getDirector());
        Set<ConstraintViolation<Movie>> violationsMovie = validator.validate(movie);
        if (violationsMovie != null) {
            for (ConstraintViolation<Movie> violation : violationsMovie) {
                if (violation != null) {
                    movieMessage += violation.getMessage() + " ";
                }
            }
        }
        if (movieMessage != "") {
            throw new ValidationException(movieMessage);
        }
    }

    public Movie addMovie(Movie movie) {
        Director help = directorService.addDirector(movie.getDirector());
        return saveMovie(movie, help);
    }

    public void updateMovie(Integer movieId, Movie movieDetails) {
        Movie curMovie = movieRepository.findById(movieId).orElse(null);
        if (curMovie == null) {
            throw new ThereIsNoSuchMovieException();
        }
        else {
            Director curDir = movieDetails.getDirector();
            Director help = null;
            help = directorService.addDirector(curDir);
            curMovie.setTitle(movieDetails.getTitle());
            curMovie.setYear(movieDetails.getYear());
            curMovie.setDirector(movieDetails.getDirector());
            curMovie.setLength(movieDetails.getLength());
            curMovie.setRating(movieDetails.getRating());
            saveMovie(curMovie, help);
        }
    }

    private Movie saveMovie(Movie newMovie, Director help) {
        Movie rezultMovie;
        try {
            newMovie.setDirector(help);
            rezultMovie = movieRepository.save(newMovie);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IncorrectMovieSaveException();
        }
        return rezultMovie;
    }

    public void deleteMovie(Integer movieId) throws Exception{
        Movie findRez = movieRepository.findById(movieId).orElse(null);
        List<Movie> directorClones = movieRepository.findByDirectorIs(findRez.getDirector());
        if (directorClones.size() > 1) {
            findRez.setDirector(null);
        }
        movieRepository.delete(findRez);
    }
}
