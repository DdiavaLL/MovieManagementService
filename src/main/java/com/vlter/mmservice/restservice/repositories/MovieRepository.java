package com.vlter.mmservice.restservice.repositories;

import com.vlter.mmservice.restservice.models.Director;
import com.vlter.mmservice.restservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Tereshchenko on 18.10.2020.
 */

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
    List<Movie> findByDirectorIs(Director director);
}
