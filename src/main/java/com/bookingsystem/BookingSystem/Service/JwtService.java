package com.bookingsystem.BookingSystem.Service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {
    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    Date extractExpiration(String token);

    boolean isTokenExpired(String token);

    String genToken(UserDetails userDetails, Object o);

}
