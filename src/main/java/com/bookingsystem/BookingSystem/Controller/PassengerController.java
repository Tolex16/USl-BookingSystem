package com.bookingsystem.BookingSystem.Controller;

import com.bookingsystem.BookingSystem.Dto.TicketDto;
import com.bookingsystem.BookingSystem.Entity.Ticket;
import com.bookingsystem.BookingSystem.Service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passenger")
@RequiredArgsConstructor
public class PassengerController {
    @Autowired
    private final PassengerService passengerService;

    @PostMapping("/book-ticket")
    public ResponseEntity<?> bookTicket(@Valid @RequestBody TicketDto ticketDto){
        var bookTicket = passengerService.bookTicket(ticketDto);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/ticket/{passengerId}")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable Long passengerId) {
        return passengerService.getTicketsByPassengerId(passengerId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        return passengerService.deleteTicket(id);
    }

}
