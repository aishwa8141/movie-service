package com.stackroute.movieservice.repository;


import com.stackroute.movieservice.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataMongoTest

public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp() {
        movie = new Movie();
        movie.setMovieId("m5");
        movie.setMovieName("venum");
        movie.setPosterUrl("images/venum");
        movie.setComments("kk");
        movie.setRating(3l);
        movie.setYearOfRelease(2018);

    }
    @After
    public void tearDown(){
        movieRepository.deleteAll();
    }
    @Test
    public void testSaveMovie(){
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertEquals("m5",fetchMovie.getMovieId());

    }

    @Test
    public void testSaveMovieFailure(){
        Movie testMovie = new Movie("m6","ranna","images/dv",5,2018,"good");
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertNotSame(movie,fetchMovie.getMovieId());

    }

    @Test
    public void getAllMovie(){
        Movie testMovie = new Movie("m7","milana","images/ddsd",5,2018,"good");
//        Movie testMovie2 =new Movie("m1","raazi","image/raazi",5l,2014,"good");
//        Movie testMovie3=  new Movie("m2","harry","himage/harry",2l,2015,"kk");
//        Movie testMovie4=new Movie("m3","ninja","image/ninja",4l,2017,"good");

        movieRepository.save(testMovie);
//        List<Movie> expectedList = new ArrayList<>();
//        expectedList.add(movie);
//        expectedList.add(testMovie);
//        expectedList.add(testMovie4);
//        expectedList.add(testMovie2);
//        expectedList.add(testMovie3);
        List<Movie> list= movieRepository.findAll();
        Assert.assertEquals("m7",list.get(0).getMovieId());
    }

    @Test
    public void testDeleteMovieById(){
        Movie testMovie = new Movie("m6","ranna","images/dv",5,2018,"good");
        movieRepository.save(testMovie);
//        Movie fetchMovie = movieRepository.findById(testMovie.getMovieId()).get();
        System.out.println(testMovie);
        movieRepository.delete(testMovie);

        Assert.assertEquals(null,movieRepository.getByMovieName(movie.getMovieName()));

    }
    @Test
    public void testUpdateMovieById(){
        Movie testMovie = new Movie("m6","ranna","images/dv",5,2018,"good");
        movieRepository.save(testMovie);
        System.out.println(testMovie);
        Movie update = movieRepository.getByMovieName("ranna");
        System.out.println(update);
        update.setMovieName("drama");
        update.setPosterUrl("dkjn");
        update.setRating(5);
        update.setYearOfRelease(2017);
        System.out.println(update);
        Assert.assertNotEquals(update,testMovie);

    }
    @Test
    public void testgetMovieByName(){
        Movie testMovie = new Movie("m6","ranna","images/dv",5,2018,"good");
        movieRepository.save(testMovie);
        System.out.println(testMovie);
        Movie movie=movieRepository.getByMovieName("ranna");
        System.out.println(movie);
        Assert.assertEquals(testMovie,movie);
    }

}



