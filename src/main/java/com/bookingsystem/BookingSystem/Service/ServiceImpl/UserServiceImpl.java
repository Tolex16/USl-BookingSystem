package com.bookingsystem.BookingSystem.Service.ServiceImpl;


import com.bookingsystem.BookingSystem.Repository.AdminRepository;
import com.bookingsystem.BookingSystem.Repository.OperatorRepository;
import com.bookingsystem.BookingSystem.Repository.PassengerRepository;
import com.bookingsystem.BookingSystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PassengerRepository passengerRepository;

    private final AdminRepository adminRepository;
    private final OperatorRepository operatorRepository;


@Override
public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // Try to find the user as a student
            var studentOpt = passengerRepository.findByEmail(username);
            if (studentOpt.isPresent()) {
                return studentOpt.get();
            }

            // If not found as a student, try to find the user as a tutor
            var operatorOpt = operatorRepository.findByEmail(username);
            if (operatorOpt.isPresent()) {
                return operatorOpt.get();
            }

            // If not found as a student, try to find the user as a tutor
            var adminOpt = adminRepository.findByEmail(username);
            if (adminOpt.isPresent()) {
                return adminOpt.get();
            }

            // If neither student nor tutor is found, throw an exception
            throw new UsernameNotFoundException("User not found: " + username);
        }
    };
}

}
