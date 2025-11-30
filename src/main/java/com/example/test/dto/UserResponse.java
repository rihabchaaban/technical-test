package com.example.test.dto;


import com.example.test.model.Gender;
import java.time.LocalDate;

public class UserResponse {

    private Long id;
    private String username;
    private LocalDate birthdate;
    private String country;
    private String phone;
    private Gender gender;

    public UserResponse(Long id, String username, LocalDate birthdate, String country, String phone, Gender gender) {
        this.id = id;
        this.username = username;
        this.birthdate = birthdate;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public Gender getGender() {
        return gender;
    }
    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthdate=" + birthdate +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                '}';
    }

}
