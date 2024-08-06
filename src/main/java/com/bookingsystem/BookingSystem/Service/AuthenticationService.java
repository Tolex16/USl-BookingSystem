package com.bookingsystem.BookingSystem.Service;

import com.bookingsystem.BookingSystem.Dto.LoginRequest;
import com.bookingsystem.BookingSystem.Dto.PassengerDto;
import com.bookingsystem.BookingSystem.Response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> passengerRegister(PassengerDto passengerDto);

    LoginResponse login(LoginRequest loginRequest);
}
