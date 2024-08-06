package com.bookingsystem.BookingSystem.Service.ServiceImpl;

import com.bookingsystem.BookingSystem.Dto.LoginRequest;
import com.bookingsystem.BookingSystem.Dto.OperatorDto;
import com.bookingsystem.BookingSystem.Dto.PassengerDto;
import com.bookingsystem.BookingSystem.Entity.Admin;
import com.bookingsystem.BookingSystem.Entity.Operator;
import com.bookingsystem.BookingSystem.Entity.Passengers;
import com.bookingsystem.BookingSystem.Entity.Role;
import com.bookingsystem.BookingSystem.ExceptionHandling.PassengerNotFoundException;
import com.bookingsystem.BookingSystem.ExceptionHandling.UserAlreadyExistsException;
import com.bookingsystem.BookingSystem.Repository.AdminRepository;
import com.bookingsystem.BookingSystem.Repository.OperatorRepository;
import com.bookingsystem.BookingSystem.Repository.PassengerRepository;
import com.bookingsystem.BookingSystem.Response.LoginResponse;
import com.bookingsystem.BookingSystem.Service.AuthenticationService;
import com.bookingsystem.BookingSystem.Service.JwtService;
import com.bookingsystem.BookingSystem.Service.UserService;
import jakarta.annotation.PostConstruct;
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
import java.util.Optional;

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

    private final OperatorRepository operatorRepository;

    private final AdminRepository adminRepository;

    public ResponseEntity<?> passengerRegister(PassengerDto passengerDto) {
        if (passengerRepository.existsByEmail(passengerDto.getEmail())) {
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

        // Try to find the user as a passenger first
        var passengerOpt = passengerRepository.findByEmail(loginRequest.getEmail());
        if (passengerOpt.isPresent()) {
            var passenger = passengerOpt.get();
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(passenger.getEmail());
            var jwt = jwtService.genToken(userDetails, passenger);

            return new LoginResponse(passenger, null ,null,jwt);

        } else
            throw new PassengerNotFoundException("Passenger not found");

    }

    public LoginResponse loginOperator(LoginRequest loginRequest) {
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

        // Try to find the user as a passenger first
        var operatorOpt = operatorRepository.findByEmail(loginRequest.getEmail());
        if (operatorOpt.isPresent()) {
            var operator = operatorOpt.get();
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(operator.getEmail());
            var jwt = jwtService.genToken(userDetails, operator);

            return new LoginResponse(null, operator,null,jwt);

        } else
            throw new PassengerNotFoundException("Operator not found");

    }

    public LoginResponse loginAdmin(LoginRequest loginRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Invalid email or password", e);
        }

        // Try to find the user as a passenger first
        var adminOpt = adminRepository.findByEmail(loginRequest.getEmail());
        if (adminOpt.isPresent()) {
            var admin = adminOpt.get();
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(admin.getEmail());
            var jwt = jwtService.genToken(userDetails, admin);

            return new LoginResponse(null, null,admin,jwt);

        } else
            throw new PassengerNotFoundException("Admin not found");

    }

    public ResponseEntity<?> operatorRegister(OperatorDto operatorDto) {
        if (operatorRepository.existsByEmail(operatorDto.getEmail())) {
            try {
                throw new UserAlreadyExistsException("There is a passenger account with this email.");
            } catch (UserAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Operator operator = new Operator();

            operator.setFullName(operatorDto.getFullName());
            operator.setEmail(operatorDto.getEmail());
            operator.setBusModel(operatorDto.getBusModel());
            operator.setSeating(operatorDto.getSeating());
            operator.setRole(Role.OPERATOR);

            operatorRepository.save(operator);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostConstruct
    public void createAdminUsers() {
        Optional<Admin> adminUser = adminRepository.findByEmail("onlinebooking@gmail.com");
        if (adminUser.isEmpty()) {
            Admin createAdmin = new Admin();
            createAdmin.setFirstName("Tochukwu");
            createAdmin.setLastName("Okes");
            createAdmin.setEmail("onlinebooking@gmail.com");
            createAdmin.setPassword(passwordEncoder.encode("Teenwolf156$"));
            createAdmin.setRole(Role.ADMIN);
            adminRepository.save(createAdmin);
        }

    }
}
