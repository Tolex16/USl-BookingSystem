package com.bookingsystem.BookingSystem.Service.ServiceImpl;

import com.bookingsystem.BookingSystem.Dto.TicketDto;
import com.bookingsystem.BookingSystem.Entity.Passengers;
import com.bookingsystem.BookingSystem.Entity.Routes;
import com.bookingsystem.BookingSystem.Entity.Ticket;
import com.bookingsystem.BookingSystem.ExceptionHandling.PassengerNotFoundException;
import com.bookingsystem.BookingSystem.Repository.PassengerRepository;
import com.bookingsystem.BookingSystem.Repository.RouteRepository;
import com.bookingsystem.BookingSystem.Repository.TicketRepository;
import com.bookingsystem.BookingSystem.Service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private final TicketRepository ticketRepository;

    private final PassengerRepository passengerRepository;

    private final RouteRepository routeRepository;

    public ResponseEntity<?> bookTicket(TicketDto ticketDto) {

        try {
            Ticket ticket = new Ticket();
            ticket.setTicketNo(UUID.randomUUID().toString());
            ticket.setBookingDate(ticketDto.getBookingDate());
            ticket.setBookingTime(ticket.getBookingTime());

            Passengers passenger = passengerRepository.findById(ticketDto.getPassengerId()).orElseThrow(() -> new PassengerNotFoundException("Passenger not found"));
            Routes route = routeRepository.findById(ticketDto.getRouteId()).orElseThrow(() -> new RuntimeException("Route not found"));
            ticket.setPassenger(passenger);
            ticket.setRoute(route);
            ticket.setPrice(ticketDto.getPrice());
            ticket.setStatus(ticketDto.getStatus());
            ticketRepository.save(ticket);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<Void> deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<List<Ticket>> getTicketsByPassengerId(Long passengerId) {
        List<Ticket> tickets = ticketRepository.findByPassenger_PassengerId(passengerId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }


}
