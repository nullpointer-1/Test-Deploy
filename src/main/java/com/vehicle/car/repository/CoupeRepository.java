package com.vehicle.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.car.model.Coupe;

public interface CoupeRepository extends JpaRepository<Coupe, Long> {
    List<Coupe> findByIsConvertible(boolean isConvertible);
}
