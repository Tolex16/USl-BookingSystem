package com.bookingsystem.BookingSystem.Controller;

import com.bookingsystem.BookingSystem.Dto.LoginRequest;
import com.bookingsystem.BookingSystem.Dto.OperatorDto;
import com.bookingsystem.BookingSystem.Dto.PassengerDto;
import com.bookingsystem.BookingSystem.ExceptionHandling.PassengerNotFoundException;
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
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register-passenger")
    public ResponseEntity<?> registerPassenger(@Valid @RequestBody PassengerDto passengerDto, BindingResult result){
        System.out.println("Has errors?" + result.hasErrors());
        if (result.hasErrors()){ return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        var passengerRegister = authenticationService.passengerRegister(passengerDto);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping("/login-passenger")
    public ResponseEntity <?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        System.out.println("Has errors?" + result.hasErrors());
        if (result.hasErrors()){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        try {
            return new ResponseEntity<>(authenticationService.login(loginRequest),HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException | PassengerNotFoundException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
    @PostMapping("/login-operator")
    public ResponseEntity <?> loginOperator(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        System.out.println("Has errors?" + result.hasErrors());
        if (result.hasErrors()){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        try {
            return new ResponseEntity<>(authenticationService.loginOperator(loginRequest),HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException | PassengerNotFoundException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }

    @PostMapping("/login-admin")
    public ResponseEntity <?> loginAdmin(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        System.out.println("Has errors?" + result.hasErrors());
        if (result.hasErrors()){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        try {
            return new ResponseEntity<>(authenticationService.loginAdmin(loginRequest),HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException | PassengerNotFoundException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }

    @PostMapping("/register-operator")
    public ResponseEntity<?> registerOperator(@Valid @RequestBody OperatorDto operatorDto, BindingResult result){
        System.out.println("Has errors?" + result.hasErrors());
        if (result.hasErrors()){ return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        var operatorRegister = authenticationService.operatorRegister(operatorDto);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
