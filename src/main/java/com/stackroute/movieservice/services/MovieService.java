package com.stackroute.movieservice.services;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieService {

    public Movie addMovie(Movie movie) throws MovieAlreadyExistsException;
    public Boolean deleteMovie(String movieId);
    public Movie getMovieById(String movieId);
    public List<Movie> getAllMovie();
    public Movie updateMovie(Movie movie,String movieId)throws MovieNotFoundException;
//    @Query("SELECT movieName FROM MOVIE movie WHERE movie.movieName=:movieName" )
    public Movie searchMovieByName(@Param("movieName") String movieName) throws MovieNotFoundException;

}

