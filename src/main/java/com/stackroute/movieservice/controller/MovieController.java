package com.stackroute.movieservice.controller;



import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.catalina.User;
import org.apache.juli.logging.LogFactory;
//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
//@Api(value = "MovieService",description = "Operations pertaining to movies ")
public class MovieController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MovieService getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }

    public MovieController() {
    }

    @ApiOperation(value = "View a list of available movies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("movies")
    public ResponseEntity<?> getAllMovie() {
        logger.info("All movies = "+movieService.getAllMovie());
        logger.debug("Inside getAllMovie()");
        System.out.println("in getting all movies");
        List<Movie> userList = movieService.getAllMovie();
        ResponseEntity responseEntity = new ResponseEntity<List<Movie>>(userList, HttpStatus.OK);
        return responseEntity;
    }

//    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
//    public Iterable<Movie> list(Model model){
//        Iterable<Movie> movieList = movieService.getAllMovie();
//        return movieList;
//    }

    @ApiOperation(value = "Add a movie")
    @PostMapping("movie")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie) {
        ResponseEntity responseEntity;
        try {
            logger.info("saved Movie = "+movie);
            logger.debug("Inside addMovie()");
            movieService.addMovie(movie);
            responseEntity = new ResponseEntity<String>("successfulled addded", HttpStatus.CREATED);

        } catch (MovieAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

        }
        return responseEntity;
    }


    @ApiOperation(value = "Delete a movie")
    @DeleteMapping("delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") String movieId) {
        Movie movie = movieService.getMovieById(movieId);
        ResponseEntity responseEntity;

        try {
            logger.info("Movie = "+movie+" Id"+movieId);
            logger.debug("Inside deleteMovie()");
            movieService.deleteMovie(movieId);
            return new ResponseEntity<String>("deletion successfull", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("The Movie does't exist");
            return new ResponseEntity<String>("Unable to delete", HttpStatus.CONFLICT);
        }

    }

    @ApiOperation(value = "Search a movie with an ID", response = Movie.class)
    @GetMapping("get/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable("movieId") String movieId) {


        ResponseEntity responseEntity;

        try {
            logger.info("Id"+movieId);
            logger.debug("Inside getMovieById()");

            Movie movie = movieService.getMovieById(movieId);
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("failed to get movie name", HttpStatus.NOT_FOUND);

        }

    }

    @ApiOperation(value = "Search movie by movie name", response = Movie.class)
    @GetMapping("getmovie/{movieName}")
    public ResponseEntity<?> getMovieByname(@PathVariable("movieName") String movieName) throws MovieNotFoundException {
        try {
            Movie movieList = movieService.searchMovieByName(movieName);

            return new ResponseEntity<Movie>(movieList, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Update a movie")
    @PutMapping("update/{movieId}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie,@PathVariable String movieId) throws MovieNotFoundException {
        ResponseEntity responseEntity;

//        if (currentMovie == null) {
//
//            return new ResponseEntity<String>("Unable to upate", HttpStatus.NOT_FOUND);
//
//        }
//        try {
//            Movie currentMovie = movieService.getMovieById(movieId);
//
//            currentMovie.setMovieId(movie.getMovieId());
//            currentMovie.setMovieName(movie.getMovieName());
//            currentMovie.setRating(movie.getRating());
//            currentMovie.setPosterUrl(movie.getPosterUrl());
//            currentMovie.setYearOfRelease(movie.getYearOfRelease());
//            currentMovie.setComments(movie.getComments());
//            movieService.updateMovie(movieId, currentMovie);
//            return new ResponseEntity<Movie>(currentMovie, HttpStatus.OK);
//        }
//        catch (Exception ex){
//            return new ResponseEntity<String>("Unable to upate", HttpStatus.NOT_FOUND);
//        }
        try {
            logger.info("Movie = "+movie);
            logger.debug("Inside updateMovie()");
            movieService.updateMovie(movie,movieId);
            //responseEntity = new ResponseEntity<String>("updated", HttpStatus.CREATED);

            return new ResponseEntity<String>("updated",HttpStatus.OK);

        } catch (MovieNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

        }


    }
}


