package com.bookingsystem.BookingSystem.Controller;

import com.bookingsystem.BookingSystem.Dto.PassengerDto;
import com.bookingsystem.BookingSystem.Dto.RouteDto;
import com.bookingsystem.BookingSystem.Service.AdminService;
import com.bookingsystem.BookingSystem.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final AdminService adminService;

    @PostMapping("/create-route")
    public ResponseEntity<?> createRoute(@Valid @RequestBody RouteDto routeDto){
        var createRoute = adminService.createRoute(routeDto);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping("/update-route")
    public ResponseEntity<?> updateRoute(@Valid @RequestBody RouteDto routeDto){
        var updateRoute = adminService.updateRoute(routeDto);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
