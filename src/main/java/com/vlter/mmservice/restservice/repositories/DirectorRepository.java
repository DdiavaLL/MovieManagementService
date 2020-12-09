package com.vlter.mmservice.restservice.repositories;

import com.vlter.mmservice.restservice.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tereshchenko on 19.10.2020.
 */

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer>{
    Director findByDirectorEquals(String director);
}
