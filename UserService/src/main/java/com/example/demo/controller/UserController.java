package com.example.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
//import com.example.demo.movies.entity.Movies;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User newUser) {
        userService.registerUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
    

    @PostMapping("/api/v1/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            User user = userService.getUserByEmail(loginRequest.getEmail());

            if (loginRequest.getPassword().equals(user.getPassword())) {
                LoginResponseDTO response = new LoginResponseDTO("Login successful", user.getName(), user.getId());
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponseDTO("Invalid credentials", null, null));
            }
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDTO("Invalid credentials", null, null));
        }
    }
}
