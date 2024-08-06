package com.bookingsystem.BookingSystem.Service;

import com.bookingsystem.BookingSystem.Dto.TicketDto;
import org.springframework.http.ResponseEntity;

public interface PassengerService {
    public ResponseEntity<?> bookTicket(TicketDto ticketDto);
}
