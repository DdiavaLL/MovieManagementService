package com.vlter.mmservice.restservice;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

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
    public void submit(@ModelAttribute MovieWrapper movieWrapper, Model model) {
        objectMapper.findAndRegisterModules();
        Movie newMovie = null;
        try {
            newMovie = objectMapper.readValue(movieWrapper.getJson(), Movie.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //movieRepository.save(newMovie);
    }


}
