package com.bookingsystem.BookingSystem.Dto;

import com.bookingsystem.BookingSystem.Config.StrongPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PassengerDto {
    private long studentId;

    private Long passengerId;

    private String firstName;

    private String middleName;

    private String lastName;

    @Email(message = "Input a valid email address")
    private String email;

    private String phoneNumber;

    private String address;

    private String nextOfKinName;

    private String nextOfKinNo;

    @StrongPassword
    private String password;


}
