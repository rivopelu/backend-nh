package com.lewatihari.entities.repositories;

import com.lewatihari.entities.Merchant;
import com.lewatihari.entities.MerchantImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantImageRepository extends JpaRepository<MerchantImage, String> {
    List<MerchantImage> findAllByMerchant(Merchant merchant);
}
