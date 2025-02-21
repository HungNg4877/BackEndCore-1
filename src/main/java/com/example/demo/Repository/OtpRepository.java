package com.example.demo.Repository;

import com.example.demo.Entity.Otp;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OtpRepository extends JpaRepository<Otp, UUID> {
    Optional<Otp> findByUserAndOtp(User user, String otp);
}
