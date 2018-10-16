package com.stackroute.movieservice.services;


import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieServiceImp implements MovieService {

@Autowired
    MovieRepository movieRepository;

//    @Autowired
//    public MovieServiceImp(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

    @Override
    public Movie addMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getMovieId()))
        {
            throw new MovieAlreadyExistsException("movie already exists");
        }

       Movie addmovie= movieRepository.save(movie);
        if (addmovie==null){
            throw new MovieAlreadyExistsException("movie already exists");
        }
        return addmovie;
    }


    @Override
    public Boolean deleteMovie(String movieId) {
        if (getMovieById(movieId) == null) {
            return false;
        } else {
            System.out.println(movieId);
            movieRepository.deleteById(movieId);
            return true;
        }
    }

    @Override
    public Movie getMovieById(String movieId) {


        Movie movie = movieRepository.findById(movieId).get();
        return movie;
    }

    @Override
    public Movie searchMovieByName(String movieName) throws MovieNotFoundException {
        if(movieRepository.existsByMovieName(movieName)==false)
        {
            throw new MovieNotFoundException("movie not found ");
        }
        System.out.println("in service class " +movieName);
        return movieRepository.getByMovieName(movieName);


    }


    @Override
    public Movie updateMovie( Movie movie,String id) throws MovieNotFoundException{
     if(movieRepository.existsById(id)==false){
         throw new MovieNotFoundException("movie not found");
     }
       // Movie currentMovie = movieRepository.findById(id).get();

//            movie.setMovieId(movie.getMovieId());
            movie.setMovieName(movie.getMovieName());
            movie.setRating(movie.getRating());
            movie.setPosterUrl(movie.getPosterUrl());
            movie.setYearOfRelease(movie.getYearOfRelease());
            movie.setComments(movie.getComments());
            return movieRepository.save(movie);

//        System.out.println("updating movie");

    }

        @Override
        public List<Movie> getAllMovie () {
            List<Movie> allMovieList=movieRepository.findAll();

            System.out.println("the data is in movielist"+Arrays.toString(allMovieList.toArray()));
            return allMovieList;
        }
    }

