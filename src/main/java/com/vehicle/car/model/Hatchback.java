package com.vehicle.car.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
//@Table(name = "hatchbacks")
@PrimaryKeyJoinColumn(name = "car_id")
public class Hatchback extends Car {
    private double bootspace;

    public Hatchback() {
    }

    public Hatchback(double bootspace) {
        this.bootspace = bootspace;
    }

    @Override
    public double getSalePrice() {
        double discount = (bootspace < 250) ? 0.10 : 0.20;
        if (this.getCTSEmployee()){
            discount += 0.10;
        }
        return getRegularPrice() * (1 - discount);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + String.format(", %.1fL boot", bootspace);
    }

    public double getBootspace() {
        return bootspace;
    }

    public void setBootspace(double bootspace) {
        this.bootspace = bootspace;
    }
}
