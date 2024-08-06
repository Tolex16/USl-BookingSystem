package com.bookingsystem.BookingSystem.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OperatorDto {
    private Long operatorId;

    private String fullName;

    @Email(message = "Input a valid email address")
    private String email;

    private String busModel;

    private int seating;

    private List<Long> routeIds;
}
