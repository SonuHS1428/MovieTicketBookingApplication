package com.example.demo.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotEmpty(message = "Movie title is required")
    private String movieTitle;

    @NotEmpty(message = "Movie language is required")
    private String movieLanguage;

    @NotEmpty(message = "Movie session is required")
    private String movieSession;

    @Min(value = 0, message = "Price should be greater than or equal to 0")
    private double moviePrice;

    @ElementCollection
    @NotEmpty(message = "Seats are required")
    private List<Integer> seat;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
