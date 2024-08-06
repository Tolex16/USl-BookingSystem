package com.bookingsystem.BookingSystem.Service.ServiceImpl;

import com.bookingsystem.BookingSystem.Dto.LoginRequest;
import com.bookingsystem.BookingSystem.Dto.PassengerDto;
import com.bookingsystem.BookingSystem.Entity.Passengers;
import com.bookingsystem.BookingSystem.Entity.Role;
import com.bookingsystem.BookingSystem.ExceptionHandling.PassengerNotFoundException;
import com.bookingsystem.BookingSystem.ExceptionHandling.UserAlreadyExistsException;
import com.bookingsystem.BookingSystem.Repository.PassengerRepository;
import com.bookingsystem.BookingSystem.Response.LoginResponse;
import com.bookingsystem.BookingSystem.Service.AuthenticationService;
import com.bookingsystem.BookingSystem.Service.JwtService;
import com.bookingsystem.BookingSystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserService userService;

    @Autowired
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PassengerRepository passengerRepository;


    public ResponseEntity<?> passengerRegister(PassengerDto passengerDto){
        if(passengerRepository.existsByEmail(passengerDto.getEmail())){
            try {
                throw new UserAlreadyExistsException("There is a passenger account with this email.");
            } catch (UserAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Passengers passengers = new Passengers();

            passengers.setFirstName(passengerDto.getFirstName());
            passengers.setMiddleName(passengerDto.getMiddleName());
            passengers.setLastName(passengerDto.getLastName());
            passengers.setEmail(passengerDto.getEmail());
            passengers.setAddress(passengerDto.getAddress());
            passengers.setPassword(passwordEncoder.encode(passengerDto.getPassword()));
            passengers.setNextOfKinName(passengerDto.getNextOfKinName());
            passengers.setNextOfKinNo(passengerDto.getNextOfKinNo());
            passengers.setRole(Role.PASSENGER);
            passengerRepository.save(passengers);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Invalid student username or password", e);
        }

        // Try to find the user as a student first
        var passengerOpt =passengerRepository.findByEmail(loginRequest.getEmail());
        if (passengerOpt.isPresent()) {
            var passenger = passengerOpt.get();
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(passenger.getEmail());
            var jwt = jwtService.genToken(userDetails, passenger);

            return new LoginResponse(passenger, jwt);

        } else throw new PassengerNotFoundException("Account not verified for this student, please check your email to verify");
    }

}
