package com.bookingsystem.BookingSystem.Entity;

import com.bookingsystem.BookingSystem.Config.StrongPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Passengers")
public class Passengers implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "first_Name")
    private String firstName;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "middle_Name")
    private String middleName;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Input a valid email address")
    @NotNull(message = "Input a valid email address")
    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "phone_Number")
    private String phoneNumber;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "address")
    private String address;

    @Column(name = "next_of_kin_name")
    private String nextOfKinName;

    @Column(name = "next_of_kin_no")
    private String nextOfKinNo;

    @JsonIgnore
    @NotBlank(message = "Password required")
    @NotNull(message = "password required")
    @StrongPassword
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
