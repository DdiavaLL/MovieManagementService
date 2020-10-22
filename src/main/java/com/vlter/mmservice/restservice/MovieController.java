package com.vlter.mmservice.restservice;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorRepository directorRepository;

    ObjectMapper objectMapper = new ObjectMapper();

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
    public Serializable postMovie(@ModelAttribute MovieWrapper movieWrapper, Model model) {
        objectMapper.findAndRegisterModules();
        Movie newMovie = null;
        try {
            newMovie = objectMapper.readValue(movieWrapper.getJson(), Movie.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            StatusMessage dessMessage = GenerateStatus("Ошибка во время десериализации входящего JSON сообщения; ", e);
            return dessMessage;
        }
        Director curDir = newMovie.getDirector();
        if (curDir != null) {
            if (curDir.getId() != null) {
                Director help = directorRepository.findById(curDir.getId()).orElse(null);
                StatusMessage rezAdding = CorrectAdding(curDir, newMovie, help);
                if (rezAdding != null)
                    return rezAdding;
            }
            else {
                Director help1 = directorRepository.findByDirector(curDir.getDirector());
                StatusMessage rezAdding = CorrectAdding(curDir, newMovie, help1);
                if (rezAdding != null)
                    return rezAdding;
            }
        }
        else {
            StatusMessage directorMessage = new StatusMessage(500, "Во входящем запросе отсутствует режиссер кинофильма;");
            return directorMessage;
        }
        Movie rezMovie = movieRepository.findById(newMovie.getId()).orElse(null);
        return rezMovie;
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
