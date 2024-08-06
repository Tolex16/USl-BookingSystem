package com.bookingsystem.BookingSystem.Response;

import com.bookingsystem.BookingSystem.Entity.Admin;
import com.bookingsystem.BookingSystem.Entity.Operator;
import com.bookingsystem.BookingSystem.Entity.Passengers;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponse {

    private final Passengers passengers;

    private final Operator operator;

    private final Admin admin;

    @JsonProperty("token")
    private final String token;
}
