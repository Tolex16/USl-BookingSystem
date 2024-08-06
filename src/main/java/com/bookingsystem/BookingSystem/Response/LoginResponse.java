package com.bookingsystem.BookingSystem.Response;

import com.bookingsystem.BookingSystem.Entity.Passengers;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponse {

    private final Passengers passengers;
    @JsonProperty("token")
    private final String token;
}
