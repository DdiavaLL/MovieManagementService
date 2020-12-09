package com.vlter.mmservice.restservice.controllers;

import com.vlter.mmservice.restservice.exceptions.DeleteDirectorException;
import com.vlter.mmservice.restservice.exceptions.ThereIsNoSuchDirectorException;
import com.vlter.mmservice.restservice.models.Director;
import com.vlter.mmservice.restservice.models.DirectorResponse;
import com.vlter.mmservice.restservice.models.ListDirectorResponse;
import com.vlter.mmservice.restservice.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Tereshchenko on 19.11.2020.
 */

@RestController
@RequestMapping(value = "/api/directors")
public class DirectorController {
    @Autowired
    DirectorService directorService;

    // Список всех режиссеров
    @GetMapping()
    public List getAllDirectors() {
        return directorService.directorRepository.findAll();
    }

    // Получить информацию о режиссере по id
    @GetMapping("/{id}")
    public Director getDirectorById(@PathVariable Integer id) {
        Director rezDirector = directorService.directorRepository.findById(id).orElse(null);
        if (rezDirector == null) {
            throw new ThereIsNoSuchDirectorException();
        }
        return rezDirector;
    }

    // Добавление режиссера
    @PostMapping()
    public Serializable postDirector(@RequestBody Director director) {
        directorService.validateDirector(director);
        Director rezultDirector = directorService.addDirector(director);
        return rezultDirector;
    }

    // Изменение информации о режиссере с указанным id
    @PatchMapping("/{id}")
    public Serializable updateDirector(@PathVariable Integer id, @RequestBody Director directorDetails) {
        directorService.validateDirector(directorDetails);
        Director findRez = directorService.directorRepository.findById(id).orElse(null);
        directorService.updateDirector(id, directorDetails);
        return findRez;
    }

    // Удаление записи о режиссере с указанным id
    @DeleteMapping("/{id}")
    public Serializable deleteDirector(@PathVariable Integer id) {
        Director findRez = directorService.directorRepository.findById(id).orElse(null);
        if (findRez == null) {
            throw new ThereIsNoSuchDirectorException();
        }
        else {
            try {
                directorService.deleteDirector(id);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DeleteDirectorException();
            }
        }
        return findRez;
    }
}
