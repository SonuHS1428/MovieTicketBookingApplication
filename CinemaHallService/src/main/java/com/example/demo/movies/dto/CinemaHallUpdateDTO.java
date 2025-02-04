package com.example.demo.movies.dto;

import java.util.List;

import lombok.Data;

@Data
public class CinemaHallUpdateDTO {
	 	public String movieSession;
	    private String orderTime;
	    private List<Integer> updatedSeats;
}
