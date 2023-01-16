package com.example.laba_5_sem_2_javafx.entity.engine;

import com.example.laba_5_sem_2_javafx.enums.EngineType;


import java.io.Serializable;

public class Engine implements Serializable {
    private int power;
    private double volume;

    private EngineType engineType;


    public Engine() {

    }

    public Engine(int power, double volume,EngineType engineType) {
        this.power = power;
        this.volume = volume;
        this.engineType = engineType;
    }

    public int getPower() {return this.power;}

    public void setPower(int power) {this.power = power;}

//    public EngineType getEngineType() {
//        return engineType;
//    }
//
//    public void setEngineType(EngineType engineType) {
//        this.engineType = engineType;
//    }

    public double getVolume() {return this.volume;}

    public void setVolume(double volume) {this.volume = volume;}


    @Override
    public String toString() {
        return "Engine: {"
                + "Power: " + power
                + ", Volume: " + String.format("%.1f",volume)
                + ", Engine type: " + engineType;
    }
}
