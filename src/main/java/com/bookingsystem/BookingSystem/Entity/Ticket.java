package com.bookingsystem.BookingSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    private String ticketNo;

    private String bookingDate;

    private String bookingTime;

    private String estimatedDuration;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passengers passenger;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Routes route;
}
