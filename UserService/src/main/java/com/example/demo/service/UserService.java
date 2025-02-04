package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;

public interface UserService {
    User registerUser(User newUser);
    User getUserByEmail(String email) throws UserNotFoundException;
}
