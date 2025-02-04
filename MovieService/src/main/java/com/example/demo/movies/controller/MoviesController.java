package com.example.demo.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.service.MovieServiceImpl;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MovieServiceImpl moviesService;

    @PostMapping("/addmovie")
    public ResponseEntity<String> saveMovie(@Valid @RequestBody Movies movies) {
        moviesService.addMovie(movies);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Movies> getMovie(@PathVariable("id") int movieId) {
        Movies movie = moviesService.getMovie(movieId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Movies>> getAllMovies() {
        List<Movies> movies = moviesService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") int movieId) {
        moviesService.deleteMovie(movieId);
        return ResponseEntity.ok("Movie deleted successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<Movies> update(@Valid @RequestBody Movies movie) {
        Movies updatedMovie = moviesService.updateMovie(movie);
        return ResponseEntity.ok(updatedMovie);
    }
}
