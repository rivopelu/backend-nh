package com.lewatihari.entities.repositories;

import com.lewatihari.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CityRepository extends JpaRepository<City, BigInteger> {
}
