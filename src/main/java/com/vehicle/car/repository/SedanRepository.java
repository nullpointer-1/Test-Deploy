package com.vehicle.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.car.model.Sedan;

public interface SedanRepository extends JpaRepository<Sedan, Long> {
    List<Sedan> findByYear(int year);
}