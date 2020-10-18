package com.vlter.mmservice.restservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tereshchenko on 18.10.2020.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
