package com.example.laba_5_sem_2_javafx;


import com.example.laba_5_sem_2_javafx.entity.engine.Engine;
import com.example.laba_5_sem_2_javafx.enums.BodyType;
import com.example.laba_5_sem_2_javafx.enums.Colors;
import com.example.laba_5_sem_2_javafx.enums.Manufacture;

import java.io.Serializable;

public class Car implements Serializable {


    private Engine engine;
    private BodyType bodyType;
    private Colors color;
    private Manufacture manufacture;
    private double price;
    private int yearOfIssue;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public Car(Engine engine, BodyType bodyType, Colors color, Manufacture manufacture, double price, int yearOfIssue) {
        this.engine = engine;
        this.bodyType = bodyType;
        this.color = color;
        this.manufacture = manufacture;
        this.price = price;
        this.yearOfIssue = yearOfIssue;
    }


    @Override
    public String toString() {
        return "\nCar {"
                + " Manufacture: " + manufacture
                + ", Color: " + color
                + ", Body type: " + bodyType
                + ", Year of issue: " + yearOfIssue
                + ", Price: " + String.format("%.2f",price)
                + ", " + engine;
    }

//    public boolean equals(Car car) {
//        return this.manufacture == car.manufacture &&
//                this.price == car.price &&
//                this.bodyType == car.bodyType &&
//                this.color == car.color &&
//                this.yearOfIssue == car.yearOfIssue &&
//                this.engine.getPower() == car.engine.getPower() &&
//                this.engine.getVolume() == car.engine.getVolume() ;
////                this.engine.getEngineType() == car.engine.getEngineType();
//    }

}
