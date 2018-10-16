package com.stackroute.movieservice.services;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    Movie movie;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServiceImp movieServiceImp;
    List<Movie> list = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setMovieId("m8");
        movie.setMovieName("krish");
        movie.setPosterUrl("images/krish");
        movie.setComments("good");
        movie.setRating(3l);
        movie.setYearOfRelease(2018);
        list = new ArrayList<>();
        list.add(movie);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addMovie() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieServiceImp.addMovie(movie);
        System.out.println(movie);
        Assert.assertEquals(movie, savedMovie);
        verify(movieRepository, times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieServiceImp.addMovie(movie);
        System.out.println(movie);
        System.out.println("savedmovie" + savedMovie);
        Assert.assertEquals(movie, savedMovie);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void deleteMovie() throws MovieAlreadyExistsException {

//        when(movieRepository.save((Movie) any())).thenReturn(movie);
//        Movie savedMovie = movieServiceImp.addMovie(movie);
//        System.out.println(movie);
        when(movieRepository.findById(movie.getMovieId())).thenReturn(java.util.Optional.ofNullable(movie));
        System.out.println(movie);
        Boolean deleteMovie = movieServiceImp.deleteMovie(movie.getMovieId());
        System.out.println(deleteMovie);
        System.out.println(movieRepository.getByMovieName(movie.getMovieName()));
        list=movieRepository.findAll();
        System.out.println(list);
        Assert.assertEquals(deleteMovie, movieServiceImp.deleteMovie(movie.getMovieId()));
//        verify(movieRepository, times( 2)).findById(movie.getMovieId());
    }


    @Test
    public void getMovieById() {
        movieRepository.save(movie);
        when(movieRepository.findById(movie.getMovieId())).thenReturn(java.util.Optional.ofNullable(movie));
        System.out.println(movie);
        Movie getMovie = movieServiceImp.getMovieById(movie.getMovieId());
        System.out.println("gettig" +getMovie);
        Assert.assertEquals(getMovie, movieServiceImp.getMovieById(movie.getMovieId()));

    }

    @Test
    public void getAllMovie() {
        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movieList = movieServiceImp.getAllMovie();
        Assert.assertEquals(list, movieList);
    }

    @Test
    public void updateMovie() throws MovieAlreadyExistsException, MovieNotFoundException {

            when(movieRepository.save((Movie)any())).thenReturn(movie);
            Movie returnedMovie= movieServiceImp.addMovie(movie);
            Movie movie1 = new Movie("m8","abcd","www.abcd,jpg",5,2015,"good");
            when(movieRepository.existsById(movie.getMovieId())).thenReturn(true);
            System.out.println(returnedMovie);
            Optional<Movie> movieOptional = Optional.of(movie);
            when(movieRepository.findById(movie.getMovieId())).thenReturn(movieOptional);
            Movie updatedMovie = movieServiceImp.updateMovie(movie1,movie.getMovieId());
            System.out.println(updatedMovie);
            Assert.assertEquals("abcd",updatedMovie.getMovieName());
        }



    @Test
    public void searchMovieByName() throws MovieNotFoundException, MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieServiceImp.addMovie(movie);
        System.out.println(savedMovie);


         when(movieRepository.existsByMovieName(savedMovie.getMovieName())).thenReturn(true);
         System.out.println(movieRepository.existsByMovieName(savedMovie.getMovieName()));
         Movie search=movieServiceImp.searchMovieByName(savedMovie.getMovieName());
         System.out.println(search);
         assertEquals(search,movieServiceImp.searchMovieByName(savedMovie.getMovieName()));





    }
}