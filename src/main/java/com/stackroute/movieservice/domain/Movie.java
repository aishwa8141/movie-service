package com.stackroute.movieservice.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Document
public class Movie {

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getMovieName(), getPosterUrl(), getRating(), getYearOfRelease(), getComments());
    }

    //    @ApiModelProperty(notes = "The database generated movie ID")
    @Id
    @NotNull
    private String movieId;
   @ApiModelProperty(notes = "name of the movie")
   @NotNull
   @Size(min=2, message="Name should have atleast 2 characters")
    private String movieName;
    @ApiModelProperty(notes = "The image URL of the movie")
    private String posterUrl;
   @ApiModelProperty(notes = "give rating of movie")
    private long rating;
    @ApiModelProperty(notes = "released year")
    private int yearOfRelease;
    @ApiModelProperty(notes = "comments of a movie")
    private String comments;


    public Movie(String movieId, String movieName, String posterUrl, long rating, int yearOfRelease, String comments) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
        this.comments = comments;
    }

    public Movie() {
    }



    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }



    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public long getRating() {
        return rating;
    }
    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", postUrl='" + posterUrl + '\'' +
                ", rating=" + rating +
                ", yearOfRelease=" + yearOfRelease +
                ", comment='" + comments + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getRating() == movie.getRating() &&
                getYearOfRelease() == movie.getYearOfRelease() &&
                Objects.equals(getMovieId(), movie.getMovieId()) &&
                Objects.equals(getMovieName(), movie.getMovieName()) &&
                Objects.equals(getPosterUrl(), movie.getPosterUrl()) &&
                Objects.equals(getComments(), movie.getComments());
    }


}
