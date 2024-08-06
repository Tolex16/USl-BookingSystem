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

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passengers passenger;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Routes route;
}
