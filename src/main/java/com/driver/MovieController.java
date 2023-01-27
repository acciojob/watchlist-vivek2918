package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){ //Api 1
        String response =movieService.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){ // Api 2
        String response =movieService.addDirector(director);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("MovieName") String MovieName, @RequestParam("DirectorName") String DirectorName){ //Api 3
        String response=movieService.addMovieDirectorPair(MovieName,DirectorName);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{MovieName}")
    public ResponseEntity getMovieByName(@PathVariable("MovieName") String MovieName){ //Api 4
        Movie movie = movieService.getMovieByName(MovieName);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{DirectorName}")
    public ResponseEntity getDirectorByName(@PathVariable("DirectorName") String DirectorName){ //Api 5
        Director director = movieService.getDirectorByName(DirectorName);
        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String DirectorName){ // Api 6
        List<String>movieList=new ArrayList<>();

        movieList= movieService.getMoviesByDirectorName(DirectorName);
        return new ResponseEntity<>(movieList,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){ // Api 7
        List<String>movieList=new ArrayList<>();
        movieList=movieService.findAllMovies();
        return new ResponseEntity(movieList,HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("DiectorName") String DirectorName){ // Api 8
        String response=movieService.deleteDirectorByName(DirectorName);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){ // Api 9
        String response=movieService.deleteAllDirectors();
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

}
