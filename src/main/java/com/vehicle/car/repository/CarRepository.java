package com.vehicle.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.car.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
