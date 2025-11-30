package com.example.test.service;

import com.example.test.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.example.test.dto.UserRequest;
import com.example.test.dto.UserResponse;
import com.example.test.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest request) {

        //Only adult French residents are allowed to create an account
        if (!"FRANCE".equalsIgnoreCase(request.getCountry())) {
            throw new IllegalArgumentException("Only French residents can create an account.");
        }

        int age = Period.between(request.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old.");
        }

        // DTO to Entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setBirthdate(request.getBirthdate());
        user.setCountry(request.getCountry());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());

        User saved = userRepository.save(user);

        // Entity to DTO Response
        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getBirthdate(),
                saved.getCountry(),
                saved.getPhone(),
                saved.getGender()
        );
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found."));

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getBirthdate(),
                user.getCountry(),
                user.getPhone(),
                user.getGender()
        );
    }
}