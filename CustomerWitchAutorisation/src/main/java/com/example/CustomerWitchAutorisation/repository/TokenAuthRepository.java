package com.example.CustomerWitchAutorisation.repository;

import com.example.CustomerWitchAutorisation.model.TokenAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenAuthRepository extends JpaRepository<TokenAuth, Long> {
    Optional<TokenAuth> findByToken(String token);
    void deleteByUser_Id(Long userId);
}
