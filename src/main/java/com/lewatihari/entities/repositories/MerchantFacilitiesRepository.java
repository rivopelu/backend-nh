package com.lewatihari.entities.repositories;

import com.lewatihari.entities.Merchant;
import com.lewatihari.entities.MerchantFacilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantFacilitiesRepository extends JpaRepository<MerchantFacilities, String> {
    List<MerchantFacilities> findAllByMerchant(Merchant merchant);
}
