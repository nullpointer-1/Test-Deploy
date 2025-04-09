package com.vehicle.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.car.model.Hatchback;

public interface HatchbackRepository extends JpaRepository<Hatchback, Long> {
    List<Hatchback> findByBootspaceGreaterThan(double minBootspace);
}