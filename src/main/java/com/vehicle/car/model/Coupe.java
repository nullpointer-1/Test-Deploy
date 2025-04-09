package com.vehicle.car.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
//@Table(name = "coupes")
@PrimaryKeyJoinColumn(name = "car_id")
public class Coupe extends Car {
    private boolean isConvertible;
    private int seatCount;

    public Coupe() {
    }

    public Coupe(boolean isConvertible, int seatCount) {
        this.isConvertible = isConvertible;
        this.seatCount = seatCount;
    }

    @Override
    public double getSalePrice() {
        double premium = isConvertible ? 0.15 : 0.05;
        return getRegularPrice() * (1 - premium);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + String.format(", %s, %d seats",
                isConvertible ? "convertible" : "fixed roof", seatCount);
    }

    public boolean isConvertible() {
        return isConvertible;
    }

    public void setConvertible(boolean convertible) {
        isConvertible = convertible;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
