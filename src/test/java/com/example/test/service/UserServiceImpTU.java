package com.example.test.service;


import com.example.test.dto.UserRequest;
import com.example.test.dto.UserResponse;
import com.example.test.model.Gender;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImpTU {

    private UserRepository userRepository;
    private UserServiceImp userService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImp(userRepository);
    }

    @Test
    void createUser_success() {
        UserRequest request = new UserRequest();
        request.setUsername("Rihab");
        request.setBirthdate(LocalDate.now().minusYears(20));
        request.setCountry("France");
        request.setGender(Gender.FEMALE);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername(request.getUsername());
        savedUser.setBirthdate(request.getBirthdate());
        savedUser.setCountry(request.getCountry());
        savedUser.setGender(request.getGender());

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Rihab", response.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_notFrench() {
        UserRequest request = new UserRequest();
        request.setUsername("Test");
        request.setBirthdate(LocalDate.now().minusYears(25));
        request.setCountry("USA");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                userService.createUser(request));

        assertEquals("Only French residents can create an account.", ex.getMessage());
    }

    @Test
    void createUser_under18() {
        UserRequest request = new UserRequest();
        request.setUsername("Test");
        request.setBirthdate(LocalDate.now().minusYears(16));
        request.setCountry("France");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                userService.createUser(request));

        assertEquals("User must be at least 18 years old.", ex.getMessage());
    }

    @Test
    void getUserById_found() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Rihab");
        user.setBirthdate(LocalDate.now().minusYears(20));
        user.setCountry("France");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponse response = userService.getUserById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void getUserById_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getUserById(1L));
    }
}

