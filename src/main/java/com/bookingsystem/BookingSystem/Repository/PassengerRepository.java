package com.bookingsystem.BookingSystem.Repository;

import com.bookingsystem.BookingSystem.Entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passengers, Long> {
 Optional<Passengers> findByEmail(String email);
 //UserEntity findByRole(Role role);
 Boolean existsByEmail(String email);
}
