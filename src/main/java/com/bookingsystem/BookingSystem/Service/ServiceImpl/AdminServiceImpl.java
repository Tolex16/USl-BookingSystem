package com.bookingsystem.BookingSystem.Service.ServiceImpl;

import com.bookingsystem.BookingSystem.Dto.OperatorDto;
import com.bookingsystem.BookingSystem.Dto.RouteDto;
import com.bookingsystem.BookingSystem.Entity.Operator;
import com.bookingsystem.BookingSystem.Entity.Role;
import com.bookingsystem.BookingSystem.Entity.Routes;
import com.bookingsystem.BookingSystem.ExceptionHandling.UserAlreadyExistsException;
import com.bookingsystem.BookingSystem.Repository.OperatorRepository;
import com.bookingsystem.BookingSystem.Repository.PassengerRepository;
import com.bookingsystem.BookingSystem.Repository.RouteRepository;
import com.bookingsystem.BookingSystem.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final RouteRepository routeRepository;

    private final OperatorRepository operatorRepository;

    public ResponseEntity<?> createRoute(RouteDto routeDto) {

        try {
            Routes routes = new Routes();

            routes.setOrigin(routeDto.getOrigin());
            routes.setDestination(routeDto.getDestination());
            routes.setDateOfOperation(routeDto.getDateOfOperation());
            routes.setTimeOfOperation(routeDto.getTimeOfOperation());
            List<Operator> operators = operatorRepository.findAllById(routeDto.getOperatorIds());
            routes.setOperators(operators);

            routeRepository.save(routes);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateRoute(RouteDto routeDto) {

        try {
            Routes routes = routeRepository.findById(routeDto.getRouteId()).orElseThrow(() -> new RuntimeException("Route not found"));

            routes.setOrigin(routeDto.getOrigin());
            routes.setDestination(routeDto.getDestination());
            routes.setDateOfOperation(routeDto.getDateOfOperation());
            routes.setTimeOfOperation(routeDto.getTimeOfOperation());
            List<Operator> operators = operatorRepository.findAllById(routeDto.getOperatorIds());
            routes.setOperators(operators);

            routeRepository.save(routes);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
