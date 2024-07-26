package com.lewatihari.repositories;

import com.lewatihari.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByEmailAndActiveTrue(String email);

    boolean existsByEmailAndActiveTrue(String email);
}
