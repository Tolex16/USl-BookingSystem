package com.bookingsystem.BookingSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "Routes")
public class Routes {
    private Long routeId;

    private String to;

    private String fro;

    private String date;

    private String time;
}
