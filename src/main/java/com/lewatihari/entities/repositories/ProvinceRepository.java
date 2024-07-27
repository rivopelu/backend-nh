package com.lewatihari.entities.repositories;

import com.lewatihari.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProvinceRepository extends JpaRepository<Province, BigInteger> {
}
