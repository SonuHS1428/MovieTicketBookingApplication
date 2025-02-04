package com.example.demo.movies.service;

import java.util.List;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.exception.MovieNotFoundException;

public interface MovieService {
    String addMovie(Movies movies);
    Movies getMovie(int movieId) throws MovieNotFoundException;
    List<Movies> getAllMovies();
    String deleteMovie(int movieId);
    Movies updateMovie(Movies movies);
}
