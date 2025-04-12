package com.vehicle.car.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Car implements Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int speed;
    private double regularPrice;
    private String color;
    private String model;
    private int mileage;
    private String fuelType;
    private String transmission;
    private Boolean isCTSEmployee;


    public boolean getCTSEmployee() {
        return this.isCTSEmployee != null ? this.isCTSEmployee : false; // Default to false if null
    }


    public void setCTSEmployee(Boolean isCTSEmployee) {
        this.isCTSEmployee = isCTSEmployee;
    }

    public abstract double getSalePrice();


    public Car() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Car(Long id, int speed, double regularPrice, String color, String model, int mileage, String fuelType,
            String transmission,Boolean isCTSEmployee) {
        this.id = id;
        this.speed = speed;
        this.regularPrice = regularPrice;
        this.color = color;
        this.model = model;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.isCTSEmployee = isCTSEmployee;
    }

    @Override
    public String getDescription() {
        return String.format("%s %s, %d km, %s, %s transmission",
                color, model, mileage, fuelType, transmission);
    }
}
