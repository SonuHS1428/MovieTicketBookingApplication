package com.example.demo.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.exception.MovieNotFoundException;
import com.example.demo.movies.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepo;

    @Override
    public String addMovie(Movies movies) {
        Movies movie = movieRepo.save(movies);
        if (movie != null)
            return "Movie Saved Successfully";
        else
            return "Failed To Save Movie";
    }

    @Override
    public Movies getMovie(int movieId) throws MovieNotFoundException {
        Optional<Movies> optional = movieRepo.findById(movieId);
        return optional.orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + movieId));
    }

    @Override
    public List<Movies> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public String deleteMovie(int movieId) {
        movieRepo.deleteById(movieId);
        return "Movie Deleted Successfully";
    }

    @Override
    public Movies updateMovie(Movies movies) {
        Movies mov = movieRepo.save(movies);
        if (mov != null)
            return mov;
        else
            return null;
    }
}
