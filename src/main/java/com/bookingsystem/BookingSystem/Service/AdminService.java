package com.bookingsystem.BookingSystem.Service;

import com.bookingsystem.BookingSystem.Dto.RouteDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<?> createRoute(RouteDto routeDto);

    ResponseEntity<?> updateRoute(RouteDto routeDto);
}
