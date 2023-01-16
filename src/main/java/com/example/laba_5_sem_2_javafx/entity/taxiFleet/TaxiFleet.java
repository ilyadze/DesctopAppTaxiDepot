package com.example.laba_5_sem_2_javafx.entity.taxiFleet;

import com.example.laba_5_sem_2_javafx.Car;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaxiFleet implements Serializable {
    private List<Car> carsList = new ArrayList<Car>();

    public TaxiFleet() {}

    public TaxiFleet(List<Car> carsList) {
        this.carsList = new ArrayList<Car>(carsList);
    }

    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
    }

    public List<Car> getCarsList() {return carsList;}

    public void addCarToList(Car car) {
        carsList.add(car);
    }

    @Override
    public String toString() {
        return "Taxi fleet: " + carsList;
    }
}
