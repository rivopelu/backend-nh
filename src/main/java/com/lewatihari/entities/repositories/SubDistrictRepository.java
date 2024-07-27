package com.lewatihari.entities.repositories;

import com.lewatihari.entities.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, BigInteger> {
    List<SubDistrict> findALlByDistrictId(BigInteger districtId);

}
