package com.bookingsystem.BookingSystem.Repository;

import com.bookingsystem.BookingSystem.Entity.Admin;
import com.bookingsystem.BookingSystem.Entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Passengers> findByEmail(String email);
    //UserEntity findByRole(Role role);
    Boolean existsByEmail(String email);
}
