package com.vehicle.car.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
//@Table(name = "sedans")
@PrimaryKeyJoinColumn(name = "car_id")
public class Sedan extends Car {
    private int year;
    private double manufacturerDiscount;
    private boolean hasSunroof;

    // public Sedan(int year, double manufacturerDiscount, boolean hasSunroof) {
    // this.year = year;
    // this.manufacturerDiscount = manufacturerDiscount;
    // this.hasSunroof = hasSunroof;
    // }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getManufacturerDiscount() {
        return manufacturerDiscount;
    }

    public void setManufacturerDiscount(double manufacturerDiscount) {
        this.manufacturerDiscount = manufacturerDiscount;
    }

    public boolean isHasSunroof() {
        return hasSunroof;
    }

    public void setHasSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }

    @Override
    public double getSalePrice() {
        return Math.max(0, getRegularPrice() - manufacturerDiscount);
    }

    @Override
    public String getDescription() {
        return String.format("%s %s, %d model, %s, %s",
                getColor(),
                getModel(),
                year,
                hasSunroof ? "sunroof" : "no sunroof",
                String.format("₹%.2f discount", manufacturerDiscount));
    }
}
