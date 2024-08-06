package com.bookingsystem.BookingSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Routes")
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String origin;

    private String destination;

    private String dateOfOperation;

    private String timeOfOperation;

    @ManyToMany
    @JoinTable(
            name = "route_operator",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id"))
    private List<Operator> operators;

    @OneToMany(mappedBy = "route")
    private List<Ticket> tickets;
}
