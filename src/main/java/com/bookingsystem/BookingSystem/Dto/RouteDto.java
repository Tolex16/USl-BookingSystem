package com.bookingsystem.BookingSystem.Dto;

import com.bookingsystem.BookingSystem.Entity.Operator;
import com.bookingsystem.BookingSystem.Entity.Ticket;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RouteDto {
    private Long routeId;

    private String origin;

    private String destination;

    private String dateOfOperation;

    private String timeOfOperation;

    private List<Long> operatorIds;

    private List<Long> tickets;
}
