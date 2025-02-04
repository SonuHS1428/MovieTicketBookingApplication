package com.example.demo.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.movies.entity.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Integer> {
}
