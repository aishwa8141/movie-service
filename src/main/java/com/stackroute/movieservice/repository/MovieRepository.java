package com.stackroute.movieservice.repository;


import com.stackroute.movieservice.domain.Movie;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {


   public Movie getByMovieName(String movieName);
   public boolean existsByMovieName(String movieName);
}
