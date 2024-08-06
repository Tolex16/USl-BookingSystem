package com.bookingsystem.BookingSystem.Repository;

import com.bookingsystem.BookingSystem.Entity.Operator;
import com.bookingsystem.BookingSystem.Entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByEmail(String email);
    //UserEntity findByRole(Role role);
    Boolean existsByEmail(String email);
}
