package com.example.demo.Repository;

import com.example.demo.Entity.Token;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
    @Query("SELECT t FROM Token t WHERE t.refreshToken = :token AND t.isRevoked != true")
    Optional<Token> findByRefreshToken(@Param("token") String refreshToken);
    @Query("SELECT t FROM Token t WHERE t.isRevoked != true")
    List<Token> findByUser(User user);
    void deleteByRefreshToken(String refreshToken);
}
