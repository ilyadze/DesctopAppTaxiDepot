package com.example.laba_5_sem_2_javafx;

import com.example.laba_5_sem_2_javafx.entity.taxiFleet.TaxiFleet;
import com.example.laba_5_sem_2_javafx.logic.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("UsersData.txt");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            List<User> users = new ArrayList<>();
//            users.add(new User("admin", "admin", "Not blocked", "admin"));
//            users.add(new User("1", "1", "Not blocked", "not admin"));
//            objectOutputStream.writeObject(users);
//            objectOutputStream.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        TaxiFleet taxiFleet = new TaxiFleet();
//        Manager.createTaxiFleet(taxiFleet, 30);
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("TaxiFleet.txt");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(taxiFleet.getCarsList());
//            objectOutputStream.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        stage.setTitle("Autorization!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}