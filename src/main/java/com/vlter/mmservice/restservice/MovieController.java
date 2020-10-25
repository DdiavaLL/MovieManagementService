package com.vlter.mmservice.restservice;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

import java.io.Serializable;
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

    // Добавление записи о кинофильме
    @RequestMapping(method = RequestMethod.POST)
    public Serializable postMovie(@RequestBody Movie movie) {
        Director curDir = movie.getDirector();
        if (curDir != null) {
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
        }
        else {
            StatusMessage directorMessage = new StatusMessage(500, "Во входящем запросе отсутствует режиссер кинофильма;");
            return directorMessage;
        }
        Director help = directorRepository.findByDirector(movie.getDirector().getDirector() );
        StatusMessage ca = CorrectAdding(movie.getDirector(), movie, help);
        if (ca != null)
            return ca;
        Movie rezMovie = movieRepository.findById(movie.getId()).orElse(null);
        return rezMovie;
    }

    // Изменение информации о кинофильме с указанным id
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Serializable updateMovie(@PathVariable(value = "id") Integer movieId, @RequestBody Movie movieDetails) {
        Movie findRez = movieRepository.findById(movieId).orElse(null);
        if (findRez == null) {
            StatusMessage serchRez = new StatusMessage(404, "Фильма, с указанным id, не существует!");
            return serchRez;
        }
        else {
            Director curDir = movieDetails.getDirector();
            if (curDir != null) {
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
            else {
                StatusMessage directorMessage = new StatusMessage(500, "Во входящем запросе отсутствует режиссер кинофильма;");
                return directorMessage;
            }
        }
        return findRez;
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
                Director newDir = new Director(curDir.getDirector());
                directorRepository.save(newDir);
                newMovie.setDirector(newDir);
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
