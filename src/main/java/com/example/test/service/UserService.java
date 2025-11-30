package com.example.test.service;

import com.example.test.dto.UserRequest;
import com.example.test.dto.UserResponse;


public interface UserService {
    UserResponse createUser(UserRequest user);
    UserResponse getUserById(Long id);
}
