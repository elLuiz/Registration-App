package com.example.registrationApp.domain.repository;

import com.example.registrationApp.domain.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
//    @Transactional
//    @Modifying
//    @Query("UPDATE Token t" +
//        "SET t.confirmedAt = ?2" +
//            "WHERE t.token = ?1"
//    )
//    int confirmedAt(String token);
}
