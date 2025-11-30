package com.example.test.dto;


import com.example.test.model.Gender;
import jakarta.validation.constraints.*;
        import java.time.LocalDate;

public class UserRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @NotBlank(message = "Country is required")
    private String country;

    @Pattern(
            regexp = "^[0-9]{6,15}$",
            message = "Phone number must contain only digits (6 to 15 digits)"
    )
    private String phone;

    private Gender gender;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", birthdate=" + birthdate +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                '}';
    }

}
