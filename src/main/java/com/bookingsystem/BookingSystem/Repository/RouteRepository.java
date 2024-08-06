package com.bookingsystem.BookingSystem.Repository;

import com.bookingsystem.BookingSystem.Entity.Operator;
import com.bookingsystem.BookingSystem.Entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Routes, Long> {
}
