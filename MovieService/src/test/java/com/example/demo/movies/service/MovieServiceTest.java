package com.example.demo.movies.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.movies.entity.Movies;
import com.example.demo.movies.repository.MovieRepository;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    public MovieServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMovie() {
        Movies mockMovie = new Movies(1, "Inception", "English", 100, "Evening", null);
        when(movieRepository.findById(1)).thenReturn(Optional.of(mockMovie));

        Movies movie = movieService.getMovie(1);

        assertNotNull(movie);
    }
}
