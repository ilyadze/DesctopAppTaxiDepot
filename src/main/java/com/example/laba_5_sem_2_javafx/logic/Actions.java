package com.example.laba_5_sem_2_javafx.logic;

import com.example.laba_5_sem_2_javafx.Car;


import java.util.Collections;
import java.util.List;

public class Actions {
    public static void sortByDescendingPrice(List<Car> cars) {
        Runnable sort = () -> {
            int size = cars.size();
            for (int i = 0;i < size;i++) {
                for (int j = i + 1;j < size;j++) {
                    if(cars.get(i).getPrice() < cars.get(j).getPrice()) {
                        Collections.swap(cars, i, j);
                    }
                }

            }

        };
        Thread thread = new Thread(sort);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sortByAscendingPrice(List<Car> cars) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int size = cars.size();
                for (int i = 0;i < size;i++) {
                    for (int j = i + 1;j < size;j++) {
                        if(cars.get(i).getPrice() > cars.get(j).getPrice()) {
                            Collections.swap(cars, i, j);

                        }
                    }

                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    public static List<Car> findStoneByColor(String color, List<Car> cars) {
//        return cars.stream().
//                filter(x -> Colors.valueOf(color).equals(x.getColor()))
//                .toList();
//    }

//    public static List<Car> filtrationList(List<Car> cars, Manufacture manufacture, int startPrice, int lastPrice) {
//        return cars.stream().
//                filter(x -> x .getPrice() >= startPrice && x.getPrice() <= lastPrice).
//                filter(x -> x.getManufacture() == manufacture).
//                toList();
//    }

    public static List<Car> filtrationList(List<Car> cars, int price) {
        return cars.stream().
                filter(x -> x.getPrice() <= price).
                toList();
    }
//    public static List<Car> filtrationList(List<Car> cars, Manufacture manufacture) {
//        return cars.stream().
//                filter(x -> x.getManufacture() == manufacture).
//                toList();
//    }

    public static boolean equalsCars(Car firstCar, Car secondCar) {
        return secondCar.getManufacture()== firstCar.getManufacture() &&
                secondCar.getPrice() == firstCar.getPrice() &&
                secondCar.getBodyType() == firstCar.getBodyType() &&
                secondCar.getColor() == firstCar.getColor() &&
                secondCar.getYearOfIssue() == firstCar.getYearOfIssue() &&
                secondCar.getEngine().getPower() == firstCar.getEngine().getPower() &&
                secondCar.getEngine().getVolume() == firstCar.getEngine().getVolume() ;
    }

}