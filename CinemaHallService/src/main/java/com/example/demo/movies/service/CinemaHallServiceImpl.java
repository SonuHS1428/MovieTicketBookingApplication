package com.example.demo.movies.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.movies.client.MovieServiceClient;
import com.example.demo.movies.dto.CinemaHallUpdateDTO;
import com.example.demo.movies.dto.MovieDTO;
import com.example.demo.movies.entity.CinemaHall;
import com.example.demo.movies.repository.CinemaHallRepository;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;
    private final MovieServiceClient movieServiceClient;

    public CinemaHallServiceImpl(CinemaHallRepository cinemaHallRepository, MovieServiceClient movieServiceClient) {
        this.cinemaHallRepository = cinemaHallRepository;
        this.movieServiceClient = movieServiceClient;
    }

    @Override
    public Optional<CinemaHall> getCinemaHallByMovieIdAndSession(Long movieId, String movieSession) {
        return cinemaHallRepository.findByMovieIdAndMovieSession(movieId, movieSession);
    }

    @Override
    public void updateOccupiedSeats(Long movieId, String movieSession, CinemaHallUpdateDTO updateDTO) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findByMovieIdAndMovieSession(movieId, movieSession);
        CinemaHall cinemaHall = cinemaHallOptional.orElseGet(CinemaHall::new);

        cinemaHall.setMovieId(movieId);
        cinemaHall.setMovieSession(movieSession);

        // Fetch the current list of booked seats and update it with new bookings
        List<Integer> currentBookedSeats = cinemaHall.getUpdatedSeats();
        if (currentBookedSeats == null) {
            currentBookedSeats = new ArrayList<>();
        }
        if (updateDTO.getUpdatedSeats() != null) {
            currentBookedSeats.addAll(updateDTO.getUpdatedSeats());
        }

        cinemaHall.setUpdatedSeats(currentBookedSeats);

        if (updateDTO.getOrderTime() != null)
            cinemaHall.setOrderTime(updateDTO.getOrderTime());

        cinemaHallRepository.save(cinemaHall);
    }

    @Override
    public HashMap<String, Object> getAvailableSeats(Long movieId, String movieSession, int money, int count) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findByMovieIdAndMovieSession(movieId, movieSession);
        HashMap<String, Object> response = new HashMap<>();
        if (cinemaHallOptional.isPresent()) {
            CinemaHall cinemaHall = cinemaHallOptional.get();

            // Fetch the movie details to get the total seats
            MovieDTO movie = movieServiceClient.getMovieById(movieId.intValue());
            List<Integer> totalSeats = new ArrayList<>(movie.getSeats());

            List<Integer> availableSeats = new ArrayList<>(totalSeats);
            availableSeats.removeAll(cinemaHall.getUpdatedSeats());

            int totalAmount = money * count;
            String msg = "Order booked successfully";

            response.put("totalAmount", totalAmount);
            response.put("msg", msg);
            response.put("availableSeats", availableSeats);

            return response;
        }
        return null;
    }
}
