package com.vlter.mmservice.restservice.controllers;

import com.vlter.mmservice.restservice.exceptions.DeleteDirectorException;
import com.vlter.mmservice.restservice.exceptions.ThereIsNoSuchDirectorException;
import com.vlter.mmservice.restservice.models.Director;
import com.vlter.mmservice.restservice.models.DirectorResponse;
import com.vlter.mmservice.restservice.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by Tereshchenko on 19.11.2020.
 */

@RestController
@RequestMapping(value = "/api/directors")
public class DirectorController {
    @Autowired
    DirectorService directorService;

    // Добавление режиссера
    @RequestMapping(method = RequestMethod.POST)
    public Serializable postDirector(@RequestBody Director director) {
        directorService.validateDirector(director);
        Director rezultDirector = directorService.addDirector(director);

        DirectorResponse rezDirector = new DirectorResponse(HttpStatus.OK.value(), rezultDirector);
        return rezDirector;
    }

    // Изменение информации о режиссере с указанным id
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Serializable updateDirector(@PathVariable(value = "id") Integer directorId, @RequestBody Director directorDetails) {
        directorService.validateDirector(directorDetails);
        Director findRez = directorService.directorRepository.findById(directorId).orElse(null);
        directorService.updateDirector(directorId, directorDetails);

        DirectorResponse rezDirector = new DirectorResponse(HttpStatus.OK.value(), findRez);
        return rezDirector;
    }

    // Удаление записи о режиссере с указанным id
    @DeleteMapping("/{id}")
    public Serializable deleteDirector(@PathVariable (value = "id") Integer directorId) {
        Director findRez = directorService.directorRepository.findById(directorId).orElse(null);
        if (findRez == null) {
            throw new ThereIsNoSuchDirectorException();
        }
        else {
            try {
                directorService.deleteDirector(directorId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DeleteDirectorException();
            }
        }
        DirectorResponse rezDirector = new DirectorResponse(HttpStatus.OK.value(), findRez);
        return rezDirector;
    }
}
