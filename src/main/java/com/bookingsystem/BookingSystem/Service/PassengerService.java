package com.bookingsystem.BookingSystem.Service;

import com.bookingsystem.BookingSystem.Dto.TicketDto;
import com.bookingsystem.BookingSystem.Entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PassengerService {
    public ResponseEntity<?> bookTicket(TicketDto ticketDto);

    ResponseEntity<Void> deleteTicket(Long id);

    ResponseEntity<List<Ticket>> getTicketsByPassengerId(Long passengerId);
}
