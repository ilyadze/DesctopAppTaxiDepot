package com.example.laba_5_sem_2_javafx.logic;

import com.example.laba_5_sem_2_javafx.Car;
import com.example.laba_5_sem_2_javafx.User;
import com.example.laba_5_sem_2_javafx.entity.factory.CarFactory;
import com.example.laba_5_sem_2_javafx.entity.taxiFleet.TaxiFleet;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

interface MyInterface {
    int CalculateCost(List<Car> cars);
}

interface SecondInterface {
    int cost(int a,int b);
}

public class Manager {

    public static TaxiFleet createTaxiFleet(TaxiFleet taxiFleet, int countCars) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < countCars; i++){
            cars.add(CarFactory.createCar());
        }
        taxiFleet.setCarsList(cars);
        return taxiFleet;
    }
    public int calculateTotalCarsCost(List<Car> taxiCars) {
        MyInterface totalCost = (cars) -> {
            int cost = 0;
            for (Car car : cars) {
                cost += car.getPrice();
            }
            return cost;
        };
        return totalCost.CalculateCost(taxiCars);
    }

    public static void saveTaxiFleet(TaxiFleet taxiFleet) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("TaxiFleet.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taxiFleet.getCarsList());
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static TaxiFleet readTaxiFleet() {
        TaxiFleet taxiFleet = new TaxiFleet();
        try {
            FileInputStream fileInputStream = new FileInputStream("TaxiFleet.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            taxiFleet.setCarsList((List<Car>) (objectInputStream.readObject()));
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taxiFleet;
    }

    public int func(int x,int y) {
        SecondInterface cost = (a, b) -> a - b;
        return cost.cost(x,y);
    }

    public static List<User> readUserList() {
        List<User> userList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("UsersData.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<User> users = (List<User>) (objectInputStream.readObject());
            for(User user : users) {
                userList.add(user);
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public static void saveUserList(List<User> userList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("UsersData.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
