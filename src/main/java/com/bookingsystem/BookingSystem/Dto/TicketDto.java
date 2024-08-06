package com.bookingsystem.BookingSystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TicketDto {
    private Long ticketId;

    private String ticketNo;

    private String bookingDate;

    private String bookingTime;

    private String estimatedDuration;

    private Long passengerId;

    private Long routeId;
}
