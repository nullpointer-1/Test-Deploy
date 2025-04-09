package com.vehicle.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.car.model.SUV;

public interface SuvRepository extends JpaRepository<SUV, Long> {
    List<SUV> findByIs4wd(boolean is4wd);
}
