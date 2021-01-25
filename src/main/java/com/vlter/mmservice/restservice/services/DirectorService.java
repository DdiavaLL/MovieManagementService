package com.vlter.mmservice.restservice.services;

import com.vlter.mmservice.restservice.exceptions.IncorrectDirectorSaveException;
import com.vlter.mmservice.restservice.exceptions.ThereIsNoSuchDirectorException;
import com.vlter.mmservice.restservice.models.Director;
import com.vlter.mmservice.restservice.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tereshchenko on 19.11.2020.
 */

@Service
public class DirectorService {
    @Autowired
    public DirectorRepository directorRepository;

    public Director addDirector(Director director) {
        Director help = directorRepository.findByDirectorEquals(director.getDirector());
        if (help == null) {
            help = saveDirector(director);
        }
        return help;
    }

    public void updateDirector(Integer directorId, Director directorDetails) {
        Director curDirector = directorRepository.findById(directorId).orElse(null);
        if (curDirector == null) {
            throw new ThereIsNoSuchDirectorException();
        }
        else {
            curDirector.setDirector(directorDetails.getDirector());
            saveDirector(curDirector);
        }
    }

    private Director saveDirector(Director newDirector) {
        Director rezultDirector;
        try {
            rezultDirector = directorRepository.save(newDirector);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IncorrectDirectorSaveException();
        }
        return rezultDirector;
    }

    public void deleteDirector(Integer directorId) throws Exception{
        directorRepository.delete(directorRepository.findById(directorId).orElse(null));
    }
}
