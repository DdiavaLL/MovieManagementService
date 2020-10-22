package com.vlter.mmservice.restservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Tereshchenko on 19.10.2020.
 */

public interface DirectorRepository extends JpaRepository<Director, Integer>{

    @Query("SELECT t FROM Director t WHERE t.director = ?1")
    Director findByDirector(String director);
}
