package com.bookingsystem.BookingSystem.Controller;

import com.bookingsystem.BookingSystem.Dto.LoginRequest;
import com.bookingsystem.BookingSystem.Dto.PassengerDto;
import com.bookingsystem.BookingSystem.ExceptionHandling.PassengerNotFoundException;
import com.bookingsystem.BookingSystem.ExceptionHandling.UserAlreadyExistsException;
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
@RequestMapping("/api/v1/passenger")
@RequiredArgsConstructor
public class PassengerController {

}
