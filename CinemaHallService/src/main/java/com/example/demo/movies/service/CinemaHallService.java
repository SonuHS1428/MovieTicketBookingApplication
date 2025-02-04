package com.example.demo.movies.service;

import java.util.HashMap;
import java.util.Optional;

import com.example.demo.movies.dto.CinemaHallUpdateDTO;
import com.example.demo.movies.entity.CinemaHall;

public interface CinemaHallService {
    Optional<CinemaHall> getCinemaHallByMovieIdAndSession(Long movieId, String movieSession);
    void updateOccupiedSeats(Long movieId, String movieSession, CinemaHallUpdateDTO updateDTO);
    HashMap<String, Object>  getAvailableSeats(Long movieId, String movieSession,int money,int count);
}

