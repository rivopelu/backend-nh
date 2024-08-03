package com.lewatihari.entities.repositories;


import com.lewatihari.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, String> {
    boolean existsBySlugAndActiveTrue(String slug);

    Optional<Merchant> findBySlugAndActiveTrue(String slug);
}
