package com.example.demo.movies.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.movies.dto.CinemaHallUpdateDTO;
import com.example.demo.movies.entity.CinemaHall;
import com.example.demo.movies.service.CinemaHallService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/cinemahalls")
public class CinemaHallController {

	private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }
    
    @PutMapping("/api/v1/movie/{movieId}/{movieSession}")
    public ResponseEntity<String> updateOccupiedSeats(@PathVariable Long movieId, @PathVariable String movieSession, @Valid @RequestBody CinemaHallUpdateDTO updateDTO) {
        cinemaHallService.updateOccupiedSeats(movieId, movieSession, updateDTO);
        return ResponseEntity.ok("Cinema hall updated successfully");
    }

    @GetMapping("/api/v1/movie/{movieId}/{movieSession}")
    public ResponseEntity<?> getUpdatedSeats(@PathVariable Long movieId, @PathVariable String movieSession) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallService.getCinemaHallByMovieIdAndSession(movieId, movieSession);
        if (cinemaHallOptional.isPresent()) {
            CinemaHall cinemaHall = cinemaHallOptional.get();
            return ResponseEntity.ok(cinemaHall.getUpdatedSeats());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cinema hall for movie ID " + movieId + " and session " + movieSession + " not found");
        }
    }

    @GetMapping("/api/v1/getavailableseats/{movieId}/{movieSession}/{money}/{count}")
    public ResponseEntity<?> getAvailableSeats(@PathVariable Long movieId, @PathVariable String movieSession,@PathVariable int money,@PathVariable int count) {
        HashMap<String, Object>  availableSeats = cinemaHallService.getAvailableSeats(movieId, movieSession,money,count);
        if (availableSeats != null) {
            return ResponseEntity.ok().body(availableSeats);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cinema hall for movie ID " + movieId + " and session " + movieSession + " not found");
        }
    }
}
